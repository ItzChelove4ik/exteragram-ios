package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class HlineAtom extends Atom {
    private float shift;
    private float width;

    public HlineAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        HorizontalRule r0 = new HorizontalRule(r4.getTeXFont().getDefaultRuleThickness(r4.getStyle()), this.width, this.shift, false);
        VerticalBox r3 = new VerticalBox();
        r3.add(r0);
        r3.type = 13;
        return r3;
    }

    public void setShift(float r1) {
        this.shift = r1;
    }

    public void setWidth(float r1) {
        this.width = r1;
    }
}
