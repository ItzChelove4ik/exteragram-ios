package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class Dummy {
    private Atom el;
    private boolean textSymbol;
    private int type;

    public Dummy(Atom r2) {
        this.textSymbol = false;
        this.type = -1;
        this.el = r2;
    }

    public void changeAtom(FixedCharAtom r2) {
        this.textSymbol = false;
        this.type = -1;
        this.el = r2;
    }

    public Box createBox(TeXEnvironment r2) {
        if (this.textSymbol == false) goto L5;
        ((CharSymbol) this.el).markAsTextSymbol();
    L5:
        Box r22 = this.el.createBox(r2);
        if (this.textSymbol == false) goto L8;
        ((CharSymbol) this.el).removeMark();
    L8:
        return r22;
    }

    public CharFont getCharFont(TeXFont r1) {
        return ((CharSymbol) this.el).getCharFont(r1);
    }

    public int getLeftType() {
        int r0 = this.type;
        if (r0 < 0) goto L6;
        return r0;
    L6:
        return this.el.getLeftType();
    }

    public int getRightType() {
        int r0 = this.type;
        if (r0 < 0) goto L6;
        return r0;
    L6:
        return this.el.getRightType();
    }

    public int getType() {
        return this.type;
    }

    public boolean isCharInMathMode() {
        Atom r1 = this.el;
        if ((r1 instanceof CharAtom) == true) goto L5;
        return false;
    L5:
        if (((CharAtom) r1).isMathMode() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isCharSymbol() {
        return this.el instanceof CharSymbol;
    }

    public boolean isKern() {
        return this.el instanceof SpaceAtom;
    }

    public void markAsTextSymbol() {
        this.textSymbol = true;
    }

    public void setPreviousAtom(Dummy r2) {
        Cloneable r1 = this.el;
        if ((r1 instanceof Row) == false) goto L6;
        ((Row) r1).setPreviousAtom(r2);
        return;
    }

    public void setType(int r1) {
        this.type = r1;
    }
}
