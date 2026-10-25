package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class DoubleFramedAtom extends FBoxAtom {
    public DoubleFramedAtom(Atom r1) {
        super(r1);
    }

    @Override // org.scilab.forge.jlatexmath.FBoxAtom, org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r7) {
        Box r0 = this.base.createBox(r7);
        float r1 = r7.getTeXFont().getDefaultRuleThickness(r7.getStyle());
        float r6 = this.INTERSPACE * SpaceAtom.getFactor(0, r7);
        float r2 = 1.5f * r1;
        float r72 = (SpaceAtom.getFactor(3, r7) * 0.5f) + r2;
        return new FramedBox(new FramedBox(r0, r1 * 0.75f, r6), r2, r72);
    }
}
