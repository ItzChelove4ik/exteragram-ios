package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class GraphicsAtom extends Atom {
    private Atom base;
    private boolean first;
    private int h;
    private int interp;
    private int w;

    public GraphicsAtom(String r1, String r2) {
        this.first = true;
        this.interp = -1;
        draw();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r1) {
        return null;
    }

    public void draw() {
    }
}
