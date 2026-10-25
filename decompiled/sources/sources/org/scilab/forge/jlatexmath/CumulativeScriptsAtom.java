package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class CumulativeScriptsAtom extends Atom {
    private Atom base;
    private RowAtom sub;
    private RowAtom sup;

    public CumulativeScriptsAtom(Atom r4, Atom r5, Atom r6) {
        if ((r4 instanceof CumulativeScriptsAtom) == false) goto L6;
        CumulativeScriptsAtom r42 = (CumulativeScriptsAtom) r4;
        this.base = r42.base;
        r42.sup.add(r6);
        r42.sub.add(r5);
        this.sup = r42.sup;
        this.sub = r42.sub;
        return;
    L6:
        if (r4 != null) goto L8;
        this.base = new PhantomAtom(new CharAtom('M', "mathnormal"), false, true, true);
    L9:
        this.sup = new RowAtom(r6);
        this.sub = new RowAtom(r5);
        return;
    L8:
        this.base = r4;
        goto L9
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        return new ScriptsAtom(this.base, this.sub, this.sup).createBox(r4);
    }
}
