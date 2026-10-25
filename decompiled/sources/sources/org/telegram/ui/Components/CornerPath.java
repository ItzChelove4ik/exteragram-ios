package org.telegram.ui.Components;

import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class CornerPath extends Path {
    private static ArrayList<RectF> recycled;
    private boolean isPathCreated;
    private int paddingX;
    private int paddingY;
    private final ArrayList<RectF> rects;
    private float rectsUnionDiffDelta;
    protected boolean useCornerPathImplementation;

    public CornerPath() {
        this.isPathCreated = false;
        this.useCornerPathImplementation = true;
        this.rectsUnionDiffDelta = 0.0f;
        this.rects = new ArrayList(1);
    }

    public CornerPath(int r2) {
        this.isPathCreated = false;
        this.useCornerPathImplementation = true;
        this.rectsUnionDiffDelta = 0.0f;
        this.rects = new ArrayList(r2);
    }

    private void createClosedPathsFromRects(List<RectF> r12) {
        if (r12.isEmpty() == true) goto L52;
        boolean r1 = false;
        if (r12.size() != 1) goto L9;
        super.addRect(r12.get(0).left - this.paddingX, r12.get(0).top - this.paddingY, r12.get(0).right + this.paddingX, r12.get(0).bottom + this.paddingY, Path.Direction.CW);
        return;
    L9:
        RectF r11 = r12.get(0);
        int r0 = r12.size() - 1;
        super.moveTo(r11.left - this.paddingX, r11.top - this.paddingY);
        int r4 = 1;
    L11:
        if (r4 >= r12.size()) goto L28;
        RectF r5 = r12.get(r4);
        if (r5.width() == 0.0f) goto L26;
        float r7 = r11.bottom;
        int r8 = this.paddingY;
        float r72 = r7 + r8;
        float r9 = r5.top;
        if (r72 < (r9 - r8)) goto L27;
        float r73 = r11.left;
        if (r73 > r5.right) goto L27;
        float r82 = r11.right;
        float r10 = r5.left;
        if (r82 < r10) goto L27;
        if (r73 == r10) goto L25;
        super.lineTo(r73 - this.paddingX, r9);
        super.lineTo(r5.left - this.paddingX, r5.top);
    L25:
        r11 = r5;
    L27:
        r1 = true;
        r0 = r4;
    L26:
        r4 = r4 + 1;
    L28:
        super.lineTo(r11.left - this.paddingX, r11.bottom + this.paddingY);
        super.lineTo(r11.right + this.paddingX, r11.bottom + this.paddingY);
        int r2 = r0 - 1;
    L29:
        if (r2 < 0) goto L38;
        RectF r42 = r12.get(r2);
        if (r42.width() == 0.0f) goto L37;
        float r52 = r11.right;
        if (r52 == r42.right) goto L36;
        super.lineTo(r52 + this.paddingX, r11.top);
        super.lineTo(r42.right + this.paddingX, r11.top);
    L36:
        r11 = r42;
    L37:
        r2 = r2 - 1;
        goto L29
    L38:
        super.lineTo(r11.right + this.paddingX, r11.top - this.paddingY);
        super.close();
        if (r1 == false) goto L51;
        createClosedPathsFromRects(r12.subList(r0, r12.size()));
        return;
    L51:
        return;
    }

    private void resetRects() {
        if (recycled != null) goto L5;
        recycled = new ArrayList(this.rects.size());
    L5:
        recycled.addAll(this.rects);
        this.rects.clear();
        this.isPathCreated = false;
    }

    @Override // android.graphics.Path
    public void addRect(float r4, float r5, float r6, float r7, Path.Direction r8) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
    L28:
        int r0 = this.paddingX;
        int r1 = this.paddingY;
        super.addRect(r4 - r0, r5 - r1, r6 + r0, r7 + r1, r8);
        return;
    L5:
        if (this.useCornerPathImplementation == false) goto L28;
        if (this.rects.size() <= 0) goto L13;
        if (this.rects.get(r8.size() - 1).contains(r4, r5, r6, r7) == false) goto L13;
        return;
    L13:
        if (this.rects.size() > 0) goto L15;
    L19:
        ArrayList<RectF> r82 = recycled;
        if (r82 != null) goto L22;
    L24:
        RectF r83 = new RectF();
    L25:
        r83.set(r4, r5, r6, r7);
        this.rects.add(r83);
    L26:
        this.isPathCreated = false;
        return;
    L22:
        if (r82.size() <= 0) goto L24;
        r83 = recycled.remove(0);
        goto L25
    L15:
        if (Math.abs(r5 - this.rects.get(r8.size() - 1).top) > this.rectsUnionDiffDelta) goto L19;
        if (Math.abs(r7 - this.rects.get(r8.size() - 1).bottom) > this.rectsUnionDiffDelta) goto L19;
        this.rects.get(r8.size() - 1).union(r4, r5, r6, r7);
        goto L26
    }

    @Override // android.graphics.Path
    public void addRect(RectF r10, Path.Direction r11) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
    L28:
        float r0 = r10.left;
        int r1 = this.paddingX;
        float r4 = r0 - r1;
        float r02 = r10.top;
        int r2 = this.paddingY;
        super.addRect(r4, r02 - r2, r10.right + r1, r10.bottom + r2, r11);
        return;
    L5:
        if (this.useCornerPathImplementation == false) goto L28;
        if (this.rects.size() <= 0) goto L13;
        if (this.rects.get(r11.size() - 1).contains(r10) == false) goto L13;
        return;
    L13:
        if (this.rects.size() > 0) goto L15;
    L19:
        ArrayList<RectF> r112 = recycled;
        if (r112 != null) goto L22;
    L24:
        RectF r113 = new RectF();
    L25:
        r113.set(r10);
        this.rects.add(r113);
    L26:
        this.isPathCreated = false;
        return;
    L22:
        if (r112.size() <= 0) goto L24;
        r113 = recycled.remove(0);
        goto L25
    L15:
        if (Math.abs(r10.top - this.rects.get(r1.size() - 1).top) > this.rectsUnionDiffDelta) goto L19;
        if (Math.abs(r10.bottom - this.rects.get(r1.size() - 1).bottom) > this.rectsUnionDiffDelta) goto L19;
        this.rects.get(r11.size() - 1).union(r10);
        goto L26
    }

    public void closeRects() {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return;
    L5:
        if (this.useCornerPathImplementation == true) goto L7;
        return;
    L7:
        if (this.isPathCreated == true) goto L12;
        createClosedPathsFromRects(this.rects);
        this.isPathCreated = true;
        return;
    }

    @Override // android.graphics.Path
    public void reset() {
        super.reset();
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return;
    L5:
        if (this.useCornerPathImplementation == false) goto L9;
        resetRects();
        return;
    }

    @Override // android.graphics.Path
    public void rewind() {
        super.rewind();
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return;
    L5:
        if (this.useCornerPathImplementation == false) goto L9;
        resetRects();
        return;
    }

    public void setPadding(int r1, int r2) {
        this.paddingX = r1;
        this.paddingY = r2;
    }

    public void setRectsUnionDiffDelta(float r1) {
        this.rectsUnionDiffDelta = r1;
    }

    public void setUseCornerPathImplementation(boolean r1) {
        this.useCornerPathImplementation = r1;
    }
}
