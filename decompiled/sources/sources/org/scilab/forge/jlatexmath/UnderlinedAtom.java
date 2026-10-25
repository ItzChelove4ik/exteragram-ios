package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
class UnderlinedAtom extends Atom {
    private final Atom base;

    public UnderlinedAtom(Atom r1) {
        this.base = r1;
        this.type = 0;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r5) {
        float r0 = r5.getTeXFont().getDefaultRuleThickness(r5.getStyle());
        Atom r4 = this.base;
        if (r4 != null) goto L5;
        Box r42 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L6:
        VerticalBox r52 = new VerticalBox();
        r52.add(r42);
        r52.add(new StrutBox(0.0f, 3.0f * r0, 0.0f, 0.0f));
        r52.add(new HorizontalRule(r0, r42.getWidth(), 0.0f));
        r52.setDepth(r42.getDepth() + (r0 * 5.0f));
        r52.setHeight(r42.getHeight());
        return r52;
    L5:
        r42 = r4.createBox(r5);
        goto L6
    }
}
