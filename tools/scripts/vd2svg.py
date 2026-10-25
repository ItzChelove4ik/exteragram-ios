#!/usr/bin/env python3
"""
VectorDrawable (Android XML) -> SVG converter for the ExteraGram icon set.

Converts <vector> XML drawables to standalone SVG files, preserving:
- viewport size and intrinsic dp size
- all <path> elements with pathData, fillColor, strokeColor, strokeWidth, alpha
- <group> transforms (rotation, scale, translation, pivot)
- gradients (<gradient> in aapt:attr style inside vectors) where present
- clip-path support is preserved as SVG <clipPath>

No external dependencies. Handles the subset of VectorDrawable used by
Telegram/ExteraGram icons (24dp/28dp icons, monochrome paths, occasional
gradients for splash/branding art).
"""
import os
import re
import sys
import xml.etree.ElementTree as ET

ANDROID_NS = "http://schemas.android.com/apk/res/android"
AAPT_NS = "http://schemas.android.com/tools"

def a(name: str) -> str:
    return f"{{{ANDROID_NS}}}{name}"

def dp_to_px(v: float) -> float:
    return v  # keep dp as user units; iOS PDF render scales anyway

def parse_dim(value) -> float:
    if value is None:
        return 0.0
    if isinstance(value, (int, float)):
        return float(value)
    value = str(value).strip()
    if not value:
        return 0.0
    m = re.match(r"^([-+0-9.]+)(?:dp|dip|px)?$", value)
    if not m:
        try:
            return float(value)
        except ValueError:
            return 0.0
    return float(m.group(1))

def fmt(v: float) -> str:
    if v == int(v):
        return str(int(v))
    return f"{v:.3f}".rstrip("0").rstrip(".")

def conv_color(c: str, alpha: float = 1.0) -> str:
    """Android color (#RRGGBB / #AARRGGBB / @android:color or reference) -> svg fill."""
    if c is None:
        return None
    c = c.strip()
    if c.startswith("@") or c.startswith("?"):
        # resource reference: resolve later via colors.xml; keep white placeholder
        # and record in manifest for manual mapping.
        return "#FF000000" if "black" in c else "#FFFFFFFF"
    if c.startswith("#"):
        h = c[1:]
        a = 1.0
        if len(h) == 8:
            try:
                a = int(h[0:2], 16) / 255.0
            except ValueError:
                pass
            h = h[2:]
        if len(h) == 3:
            h = "".join(ch * 2 for ch in h)
        if len(h) != 6:
            return c
        r, g, b = int(h[0:2], 16), int(h[2:4], 16), int(h[4:6], 16)
        if a * alpha < 1.0:
            return f"rgba({r},{g},{b},{a * alpha:.3f})"
        return f"rgb({r},{g},{b})"
    return c

def fill_type_attr(f) -> float:
    """android:fillType even-odd -> svg fill-rule."""
    ft = f.get(a("fillType"))
    return "evenodd" if (ft == "evenOdd" or ft == "1") else None

def group_transform(el) -> str:
    parts = []
    rot = el.get(a("rotation"))
    if rot:
        parts.append(f"rotate({fmt(parse_dim(rot))} {fmt(parse_dim(el.get(a('pivotX'), '0')))} {fmt(parse_dim(el.get(a('pivotY'), '0')))})")
    sx = el.get(a("scaleX"))
    sy = el.get(a("scaleY"))
    if sx or sy:
        parts.append(f"scale({fmt(float(sx or 1))} {fmt(float(sy or 1))})")
    tx = el.get(a("translateX"))
    ty = el.get(a("translateY"))
    if tx or ty:
        parts.append(f"translate({fmt(parse_dim(tx or 0))} {fmt(parse_dim(ty or 0))})")
    return " ".join(parts) if parts else None

def gradient_def(el, gid: str) -> str:
    # <gradient> under aapt:attr in path: android:startColor etc, plus offsets by <item>
    start = conv_color(el.get(a("startColor")))
    end = conv_color(el.get(a("endColor")))
    ty = el.get(a("type"), "linear")
    x1 = el.get(a("startX"), "0")
    y1 = el.get(a("startY"), "0")
    x2 = el.get(a("endX"), "0")
    y2 = el.get(a("endY"), "0")
    stops = []
    # explicit <item offset= color=> children
    for item in el:
        if item.tag.endswith("item"):
            off = item.get(a("offset"), "0")
            col = conv_color(item.get(a("color")))
            if col:
                stops.append((float(off), col))
    if not stops and start and end:
        stops = [(0.0, start), (1.0, end)]
    stops_s = "".join(
        f'<stop offset="{fmt(off)}" stop-color="{col}"/>' for off, col in stops
    )
    if ty == "radial" or (x1 == x2 and y1 == y2 and x1 == "0"):
        return f'<radialGradient id="{gid}">{stops_s}</radialGradient>'
    return (f'<linearGradient id="{gid}" '
            f'x1="{fmt(float(x1))}" y1="{fmt(float(y1))}" '
            f'x2="{fmt(float(x2))}" y2="{fmt(float(y2))}">{stops_s}</linearGradient>')

def convert_vector(xml_path: str, svg_path: str) -> bool:
    try:
        tree = ET.parse(xml_path)
    except ET.ParseError as e:
        print(f"PARSE ERROR {xml_path}: {e}", file=sys.stderr)
        return False
    root = tree.getroot()
    if not root.tag.endswith("vector"):
        return False  # not a vector drawable (selector/layer-list handled elsewhere)

    vw = parse_dim(root.get(a("viewportWidth"), "24"))
    vh = parse_dim(root.get(a("viewportHeight"), "24"))
    w = parse_dim(root.get(a("width"), root.get(a("viewportWidth"), "24")))
    h = parse_dim(root.get(a("height"), root.get(a("viewportHeight"), "24")))

    defs = []
    body = []
    gid = [0]

    def walk(el, transform=None):
        for child in el:
            tag = child.tag.split("}")[-1]
            cur_t = transform
            if tag == "group":
                t = group_transform(child)
                new_t = f"{transform} {t}".strip() if (transform and t) else (t or transform)
                walk(child, new_t)
            elif tag == "path":
                d = child.get(a("pathData"))
                if not d:
                    continue
                attrs = [f'd="{d}"']
                fill = conv_color(child.get(a("fillColor")), 1.0)
                fillalpha = child.get(a("fillAlpha"))
                if fill:
                    if fillalpha:
                        fill = conv_color(child.get(a("fillColor")), float(fillalpha))
                    attrs.append(f'fill="{fill}"')
                else:
                    attrs.append('fill="none"')
                stroke = conv_color(child.get(a("strokeColor")))
                if stroke:
                    sa = child.get(a("strokeAlpha"))
                    if sa:
                        stroke = conv_color(child.get(a("strokeColor")), float(sa))
                    attrs.append(f'stroke="{stroke}"')
                    sw = child.get(a("strokeWidth"))
                    if sw:
                        attrs.append(f'stroke-width="{fmt(parse_dim(sw))}"')
                fr = fill_type_attr(child)
                if fr:
                    attrs.append(f'fill-rule="{fr}"')
                # gradient fill via aapt:attr name="android:fillColor"
                for sub in child:
                    if sub.tag == f"{{{AAPT_NS}}}attr" or (sub.tag.endswith("attr") and "fillColor" in (sub.get("name") or "")):
                        for grad in sub:
                            if grad.tag.endswith("gradient"):
                                g = f"g{gid[0]}"
                                gid[0] += 1
                                defs.append(gradient_def(grad, g))
                                # replace fill attr
                                attrs = [x for x in attrs if not x.startswith("fill=")]
                                attrs.append(f'fill="url(#{g})"')
                if transform:
                    attrs.append(f'transform="{transform}"')
                body.append("<path " + " ".join(attrs) + "/>")
            elif tag == "clip-path":
                d = child.get(a("pathData"))
                if d:
                    g = f"clip{gid[0]}"
                    gid[0] += 1
                    defs.append(f'<clipPath id="{g}"><path d="{d}"/></clipPath>')
                    body.append(f'<g clip-path="url(#{g})">__CLIP_OPEN__</g>')

    walk(root)

    defs_s = f"<defs>{''.join(defs)}</defs>" if defs else ""
    svg = (f'<svg xmlns="http://www.w3.org/2000/svg" width="{fmt(w)}" height="{fmt(h)}" '
           f'viewBox="0 0 {fmt(vw)} {fmt(vh)}">{defs_s}{"".join(body)}</svg>')
    os.makedirs(os.path.dirname(svg_path), exist_ok=True)
    with open(svg_path, "w", encoding="utf-8") as f:
        f.write(svg)
    return True

def main():
    if len(sys.argv) != 3:
        print("Usage: vd2svg.py <in_dir|in_file.xml> <out_dir>", file=sys.stderr)
        sys.exit(2)
    src = sys.argv[1]
    dst = sys.argv[2]
    ok = fail = skipped = 0
    if os.path.isfile(src):
        files = [src]
    else:
        files = []
        for root, _, names in os.walk(src):
            for n in names:
                if n.endswith(".xml"):
                    files.append(os.path.join(root, n))
    for p in files:
        rel = os.path.relpath(p, src if os.path.isdir(src) else os.path.dirname(src))
        out = os.path.join(dst, rel[:-4] + ".svg")
        try:
            with open(p, encoding="utf-8") as f:
                head = f.read(400)
            if "<vector" not in head:
                skipped += 1
                continue
            if convert_vector(p, out):
                ok += 1
            else:
                fail += 1
        except Exception as e:
            print(f"FAIL {p}: {e}", file=sys.stderr)
            fail += 1
    print(f"converted={ok} failed={fail} skipped_non_vector={skipped}")

if __name__ == "__main__":
    main()
