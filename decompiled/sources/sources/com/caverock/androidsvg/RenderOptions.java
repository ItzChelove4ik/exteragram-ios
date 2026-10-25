package com.caverock.androidsvg;

import com.caverock.androidsvg.CSSParser;
import com.caverock.androidsvg.SVG;

/* loaded from: classes4.dex */
public class RenderOptions {
    CSSParser.Ruleset css;
    PreserveAspectRatio preserveAspectRatio;
    String targetId;
    SVG.Box viewBox;
    String viewId;
    SVG.Box viewPort;

    public RenderOptions() {
        this.css = null;
        this.preserveAspectRatio = null;
        this.targetId = null;
        this.viewBox = null;
        this.viewId = null;
        this.viewPort = null;
    }

    public boolean hasCss() {
        CSSParser.Ruleset r0 = this.css;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.ruleCount() <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasPreserveAspectRatio() {
        if (this.preserveAspectRatio == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasTarget() {
        if (this.targetId == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasView() {
        if (this.viewId == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasViewBox() {
        if (this.viewBox == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasViewPort() {
        if (this.viewPort == null) goto L6;
        return true;
    L6:
        return false;
    }

    public RenderOptions viewPort(float r2, float r3, float r4, float r5) {
        this.viewPort = new SVG.Box(r2, r3, r4, r5);
        return this;
    }
}
