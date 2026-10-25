package org.telegram.ui.Cells;

/* loaded from: classes6.dex */
class Requirement {
    public int padding;
    public CharSequence text;

    private Requirement(CharSequence r1, int r2) {
        this.text = r1;
        this.padding = r2;
    }

    public static Requirement make(int r1, CharSequence r2) {
        return new Requirement(r2, r1);
    }

    public static Requirement make(CharSequence r2) {
        return new Requirement(r2, 0);
    }
}
