package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public abstract class CharSymbol extends Atom {
    private boolean textSymbol;

    public CharSymbol() {
        this.textSymbol = false;
    }

    public abstract CharFont getCharFont(TeXFont r1);

    public boolean isMarkedAsTextSymbol() {
        return this.textSymbol;
    }

    public void markAsTextSymbol() {
        this.textSymbol = true;
    }

    public void removeMark() {
        this.textSymbol = false;
    }
}
