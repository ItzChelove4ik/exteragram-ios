package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class BreakMarkAtom extends Atom {
    public BreakMarkAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r1) {
        return new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    }
}
