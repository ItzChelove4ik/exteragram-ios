#!/usr/bin/env python3
"""
Build iOS Assets.xcassets structure from ExteraGram Android resources.

Sources:
- decompiled/resources/res/drawable*  (PNG/WebP rasters, per-density)
- assets/svg/vd/*.svg                  (converted vector drawables)

Output:
- assets/Icons.xcassets/<name>.imageset/...  raster icons: @1x/@2x/@3x from mdpi/hdpi/xhdpi/xxhdpi/xxxhdpi
  Android density -> iOS scale mapping: mdpi=1x, hdpi/xhdpi=2x (pick best), xxhdpi=3x, xxxhdpi=3x(hq)
- assets/VectorIcons/<Name>.svg        vector icons staged for PDF conversion / template rendering
- docs/icons-registry.json             machine-readable registry: name -> files, type, densities

Monochrome template icons (fill #fff) are marked "template": true so iOS
tinting matches Android tint behavior.
"""
import json
import os
import shutil
import sys
from PIL import Image

RES = "decompiled/resources/res"
OUT_ASSETS = "assets/Icons.xcassets"
OUT_VECTOR = "assets/VectorIcons"
REGISTRY = "docs/icons-registry.json"

# density -> (ios scale, priority)
DENSITY_MAP = {
    "drawable-mdpi": ("1x", 1),
    "drawable-hdpi": ("2x", 2),
    "drawable-xhdpi": ("2x", 3),
    "drawable-xxhdpi": ("3x", 4),
    "drawable-xxxhdpi": ("3x", 5),
}
# night variants map onto the same imageset with dark appearance
NIGHT_DIRS = {
    "drawable-night-mdpi": "drawable-mdpi",
    "drawable-night-hdpi": "drawable-hdpi",
    "drawable-night-xhdpi": "drawable-xhdpi",
    "drawable-night-xxhdpi": "drawable-xxhdpi",
}
RASTER_EXT = (".png", ".webp")

def slug(name: str) -> str:
    return name.replace("-", "_")

def main():
    os.makedirs(OUT_ASSETS, exist_ok=True)
    os.makedirs(OUT_VECTOR, exist_ok=True)
    # root Contents.json
    with open(os.path.join(OUT_ASSETS, "Contents.json"), "w") as f:
        json.dump({"info": {"author": "xcode", "version": 1}}, f)

    registry = {}

    # ---- raster imagesets ----
    best = {}  # name -> {scale: (priority, srcpath)}
    night = {}  # name -> {scale: srcpath}
    for d, (scale, prio) in DENSITY_MAP.items():
        dpath = os.path.join(RES, d)
        if not os.path.isdir(dpath):
            continue
        for fn in os.listdir(dpath):
            base, ext = os.path.splitext(fn)
            if ext.lower() not in RASTER_EXT:
                continue
            cur = best.setdefault(base, {}).get(scale)
            if cur is None or prio > cur[0]:
                best[base][scale] = (prio, os.path.join(dpath, fn))
    for d, mapped in NIGHT_DIRS.items():
        dpath = os.path.join(RES, d)
        if not os.path.isdir(dpath):
            continue
        for fn in os.listdir(dpath):
            base, ext = os.path.splitext(fn)
            if ext.lower() not in RASTER_EXT:
                continue
            night.setdefault(base, {})["2x" if "hdpi" in d else "3x" if "xxhdpi" in d else "1x"] = os.path.join(dpath, fn)

    def is_template(src: str) -> bool:
        try:
            im = Image.open(src).convert("RGBA")
            # sample alpha>0 pixels: if all are white/gray (r==g==b) -> template
            w, h = im.size
            px = im.load()
            checked = 0
            for y in range(0, h, max(1, h // 24)):
                for x in range(0, w, max(1, w // 24)):
                    r, g, b, a = px[x, y]
                    if a > 8:
                        if abs(r - g) > 6 or abs(g - b) > 6:
                            return False
                        checked += 1
            return checked > 0
        except Exception:
            return False

    for name, scales in best.items():
        iset = os.path.join(OUT_ASSETS, slug(name) + ".imageset")
        os.makedirs(iset, exist_ok=True)
        images = []
        entry = {"name": name, "type": "raster", "scales": {}}
        for scale, (prio, src) in scales.items():
            ext = os.path.splitext(src)[1].lower()
            dst = os.path.join(iset, f"{slug(name)}{'' if scale == '1x' else '@' + scale}{'.png' if ext == '.webp' else ext}")
            im = Image.open(src)
            if ext == ".webp":
                im.save(dst, "PNG")
            else:
                shutil.copyfile(src, dst)
            images.append({"filename": os.path.basename(dst), "idiom": "universal", "scale": scale})
            entry["scales"][scale] = os.path.basename(dst)
        # dark appearance
        dark_images = []
        if name in night:
            entry["night"] = True
            for scale, src in night[name].items():
                ext = os.path.splitext(src)[1].lower()
                dst = os.path.join(iset, f"{slug(name)}_dark{'' if scale == '1x' else '@' + scale}.png")
                Image.open(src).save(dst, "PNG")
                dark_images.append({"filename": os.path.basename(dst), "idiom": "universal", "scale": scale, "appearances": [{"appearance": "luminosity", "value": "dark"}]})
        template = is_template(os.path.join(iset, images[0]["filename"]))
        entry["template"] = template
        for im_d in images + dark_images:
            if template:
                im_d.setdefault("properties", {})["template-rendering-intent"] = "template"
        contents = {
            "images": images + dark_images,
            "info": {"author": "xcode", "version": 1},
        }
        with open(os.path.join(iset, "Contents.json"), "w") as f:
            json.dump(contents, f, indent=2)
        registry[name] = entry

    # ---- vector icons ----
    vcount = 0
    svg_root = "assets/svg/vd"
    for root, _, files in os.walk(svg_root):
        for fn in files:
            if not fn.endswith(".svg"):
                continue
            rel = os.path.relpath(os.path.join(root, fn), svg_root)[:-4]
            name = os.path.basename(rel)
            dst = os.path.join(OUT_VECTOR, slug(name) + ".svg")
            shutil.copyfile(os.path.join(root, fn), dst)
            # vector drawables are white-filled -> template by design (tint at runtime)
            registry.setdefault(name, {"name": name, "type": "vector", "svg": slug(name) + ".svg", "template": True, "pack_variants": {}})
            vcount += 1

    # ---- icon pack variants (remix/solar) ----
    for name, entry in registry.items():
        if entry.get("type") == "vector":
            for pack in ("remix", "solar"):
                variant = f"{name}_{pack}"
                if variant in registry:
                    entry["pack_variants"][pack] = variant

    with open(REGISTRY, "w", encoding="utf-8") as f:
        json.dump(registry, f, indent=1, ensure_ascii=False)
    print(f"imagesets={sum(1 for v in registry.values() if v['type']=='raster')} "
          f"vector={vcount} registry={len(registry)}")

if __name__ == "__main__":
    main()
