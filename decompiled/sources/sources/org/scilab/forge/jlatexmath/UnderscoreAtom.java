package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class UnderscoreAtom extends Atom {
    public static SpaceAtom s;
    public static SpaceAtom w;

    static {
        w = new SpaceAtom(0, 0.7f, 0.0f, 0.0f);
        s = new SpaceAtom(0, 0.06f, 0.0f, 0.0f);
    }

    public UnderscoreAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        float r3 = r4.getTeXFont().getDefaultRuleThickness(r4.getStyle());
        HorizontalBox r0 = new HorizontalBox(s.createBox(r4));
        r0.add(new HorizontalRule(r3, w.createBox(r4).getWidth(), 0.0f));
        return r0;
    }
}
