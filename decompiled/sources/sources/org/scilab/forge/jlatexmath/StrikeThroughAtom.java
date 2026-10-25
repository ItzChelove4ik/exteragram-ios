package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class StrikeThroughAtom extends Atom {
    private Atom at;

    public StrikeThroughAtom(Atom r1) {
        this.at = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r5) {
        TeXFont r0 = r5.getTeXFont();
        int r1 = r5.getStyle();
        float r2 = r0.getAxisHeight(r1);
        float r02 = r0.getDefaultRuleThickness(r1);
        Box r4 = this.at.createBox(r5);
        HorizontalRule r52 = new HorizontalRule(r02, r4.getWidth(), (-r2) + r02, false);
        HorizontalBox r03 = new HorizontalBox();
        r03.add(r4);
        r03.add(new StrutBox(-r4.getWidth(), 0.0f, 0.0f, 0.0f));
        r03.add(r52);
        return r03;
    }
}
