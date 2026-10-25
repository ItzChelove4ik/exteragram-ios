package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class HdotsforAtom extends MulticolumnAtom {
    private static final Atom ldotp = null;
    private static final Atom thin = null;
    private float coeff;

    static {
        ldotp = SymbolAtom.get("ldotp");
        thin = new SpaceAtom(1);
    }

    public HdotsforAtom(int r3, float r4) {
        super(r3, "c", ldotp);
        this.coeff = r4;
    }

    @Override // org.scilab.forge.jlatexmath.MulticolumnAtom, org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        StrutBox r0 = new StrutBox(this.coeff * thin.createBox(r6).getWidth(), 0.0f, 0.0f, 0.0f);
        HorizontalBox r1 = new HorizontalBox(r0);
        r1.add(ldotp.createBox(r6));
        r1.add(r0);
        if (this.w == 0.0f) goto L21;
        float r62 = r1.getWidth();
        float r5 = this.w;
        if (r62 > 0.0f) goto L7;
    L10:
        r5 = 0.0f;
    L11:
        HorizontalBox r63 = new HorizontalBox(r1);
        int r02 = 0;
    L13:
        if (r63.getWidth() >= r5) goto L18;
        int r3 = r02 + 1;
        if (r02 >= 65536) goto L18;
        r63.add(r1);
        r02 = r3;
    L18:
        if (r5 == 0.0f) goto L20;
        r1 = new HorizontalBox(r63, r5, 2);
        goto L21
    L20:
        r1 = r63;
        goto L21
    L7:
        if (Float.isFinite(r62) == false) goto L10;
        if (r5 <= 65536.0f) goto L11;
    L21:
        r1.type = 12;
        return r1;
    }
}
