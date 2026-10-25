package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.geom.AffineTransform;

/* loaded from: classes5.dex */
public class OverUnderBox extends Box {
    private final Box base;
    private final Box del;
    private final float kern;
    private final boolean over;
    private final Box script;

    public OverUnderBox(Box r5, Box r6, Box r7, float r8, boolean r9) {
        this.base = r5;
        this.del = r6;
        this.script = r7;
        this.kern = r8;
        this.over = r9;
        this.width = r5.getWidth();
        float r0 = r5.height;
        float r1 = 0.0f;
        if (r9 == false) goto L5;
        float r2 = r6.getWidth();
    L6:
        float r02 = r0 + r2;
        if (r9 == false) goto L10;
        if (r7 == null) goto L10;
        float r22 = (r7.height + r7.depth) + r8;
    L11:
        this.height = r02 + r22;
        float r52 = r5.depth;
        if (r9 == false) goto L14;
        float r62 = 0.0f;
    L15:
        float r53 = r52 + r62;
        if (r9 == true) goto L19;
        if (r7 == null) goto L19;
        r1 = (r7.height + r7.depth) + r8;
    L19:
        this.depth = r53 + r1;
        return;
    L14:
        r62 = r6.getWidth();
    L10:
        r22 = 0.0f;
        goto L11
    L5:
        r2 = 0.0f;
        goto L6
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r12, float r13, float r14) {
        drawDebug(r12, r13, r14);
        this.base.draw(r12, r13, r14);
        float r0 = (r14 - this.base.height) - this.del.getWidth();
        Box r1 = this.del;
        r1.setDepth(r1.getHeight() + this.del.getDepth());
        this.del.setHeight(0.0f);
        if (this.over == false) goto L7;
        Box r15 = this.del;
        AffineTransform r16 = r12.getTransform();
        r12.translate(r13 + ((r15.height + r15.depth) * 0.75d), r0);
        r12.rotate(1.5707963267948966d);
        this.del.draw(r12, 0.0f, 0.0f);
        r12.setTransform(r16);
        Box r17 = this.script;
        if (r17 == null) goto L7;
        r17.draw(r12, r13, (r0 - this.kern) - r17.depth);
    L7:
        float r142 = r14 + this.base.depth;
        if (this.over == true) goto L13;
        AffineTransform r7 = r12.getTransform();
        r12.translate(r13 + ((this.del.getHeight() + this.del.depth) * 0.75d), r142);
        r12.rotate(1.5707963267948966d);
        this.del.draw(r12, 0.0f, 0.0f);
        r12.setTransform(r7);
        float r143 = r142 + this.del.getWidth();
        Box r02 = this.script;
        if (r02 == null) goto L14;
        r02.draw(r12, r13, (r143 + this.kern) + r02.height);
        return;
    L14:
        return;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.base.getLastFontId();
    }
}
