package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class OverUnderDelimiter extends Atom {
    private final Atom base;
    private final SpaceAtom kern;
    private final boolean over;
    private Atom script;
    private final SymbolAtom symbol;

    public OverUnderDelimiter(Atom r2, Atom r3, SymbolAtom r4, int r5, float r6, boolean r7) {
        this.type = 7;
        this.base = r2;
        this.script = r3;
        this.symbol = r4;
        this.kern = new SpaceAtom(r5, 0.0f, r6, 0.0f);
        this.over = r7;
    }

    private static float getMaxWidth(Box r1, Box r2, Box r3) {
        float r12 = Math.max(r1.getWidth(), r2.getHeight() + r2.getDepth());
        if (r3 != null) goto L5;
        return r12;
    L5:
        return Math.max(r12, r3.getWidth());
    }

    public void addScript(Atom r1) {
        this.script = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r14) {
        Atom r0 = this.base;
        if (r0 != null) goto L5;
        Box r02 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L6:
        Box r1 = DelimiterFactory.create(this.symbol.getName(), r14, r02.getWidth());
        Atom r2 = this.script;
        if (r2 != null) goto L9;
        Box r22 = null;
    L14:
        float r3 = getMaxWidth(r02, r1, r22);
        if ((r3 - r02.getWidth()) <= 1.0E-7f) goto L17;
        Box r8 = new HorizontalBox(r02, r3, 2);
    L18:
        VerticalBox r9 = new VerticalBox(r1, r3, 2);
        if (r22 != null) goto L21;
    L23:
        Box r10 = r22;
    L25:
        return new OverUnderBox(r8, r9, r10, this.kern.createBox(r14).getHeight(), this.over);
    L21:
        if ((r3 - r22.getWidth()) <= 1.0E-7f) goto L23;
        r10 = new HorizontalBox(r22, r3, 2);
        goto L25
    L17:
        r8 = r02;
        goto L18
    L9:
        if (this.over == false) goto L11;
        TeXEnvironment r32 = r14.supStyle();
    L12:
        r22 = r2.createBox(r32);
        goto L14
    L11:
        r32 = r14.subStyle();
        goto L12
    L5:
        r02 = r0.createBox(r14);
        goto L6
    }

    public boolean isOver() {
        return this.over;
    }
}
