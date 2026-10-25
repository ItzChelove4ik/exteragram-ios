package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class VlineAtom extends Atom {
    private float height;
    private int n;
    private float shift;

    public VlineAtom(int r1) {
        this.n = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        if (this.n == 0) goto L12;
        float r62 = r6.getTeXFont().getDefaultRuleThickness(r6.getStyle());
        HorizontalRule r0 = new HorizontalRule(this.height, r62, this.shift);
        StrutBox r2 = new StrutBox(r62 * 2.0f, 0.0f, 0.0f, 0.0f);
        HorizontalBox r63 = new HorizontalBox();
        int r1 = 0;
    L5:
        int r3 = this.n;
        if (r1 >= (r3 - 1)) goto L8;
        r63.add(r0);
        r63.add(r2);
        r1 = r1 + 1;
        goto L5
    L8:
        if (r3 <= 0) goto L10;
        r63.add(r0);
    L10:
        return r63;
    L12:
        return new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public float getWidth(TeXEnvironment r2) {
        if (this.n != 0) goto L5;
        return 0.0f;
    L5:
        return r2.getTeXFont().getDefaultRuleThickness(r2.getStyle()) * ((this.n * 3) - 2);
    }

    public void setHeight(float r1) {
        this.height = r1;
    }

    public void setShift(float r1) {
        this.shift = r1;
    }
}
