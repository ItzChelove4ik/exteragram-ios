package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class ShadowAtom extends FBoxAtom {
    public ShadowAtom(Atom r1) {
        super(r1);
    }

    @Override // org.scilab.forge.jlatexmath.FBoxAtom, org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        return new ShadowBox((FramedBox) super.createBox(r3), r3.getTeXFont().getDefaultRuleThickness(r3.getStyle()) * 4.0f);
    }
}
