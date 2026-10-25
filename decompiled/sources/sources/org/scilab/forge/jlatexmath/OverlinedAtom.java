package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class OverlinedAtom extends Atom {
    private final Atom base;

    public OverlinedAtom(Atom r1) {
        this.base = r1;
        this.type = 0;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        float r0 = r3.getTeXFont().getDefaultRuleThickness(r3.getStyle());
        Atom r2 = this.base;
        if (r2 != null) goto L5;
        Box r22 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L6:
        OverBar r32 = new OverBar(r22, 3.0f * r0, r0);
        r32.setDepth(r22.getDepth());
        r32.setHeight(r22.getHeight() + (r0 * 5.0f));
        return r32;
    L5:
        r22 = r2.createBox(r3.crampStyle());
        goto L6
    }
}
