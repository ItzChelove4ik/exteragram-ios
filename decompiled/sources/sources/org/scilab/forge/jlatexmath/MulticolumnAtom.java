package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class MulticolumnAtom extends Atom {
    protected int afterVlines;
    protected int align;
    protected int beforeVlines;
    protected int col;
    protected Atom cols;
    protected int n;
    protected int row;
    protected float w;

    public MulticolumnAtom(int r2, String r3, Atom r4) {
        this.w = 0.0f;
        if (r2 >= 1) goto L6;
        r2 = 1;
    L6:
        this.n = r2;
        this.cols = r4;
        this.align = parseAlign(r3);
    }

    private int parseAlign(String r11) {
        int r0 = r11.length();
        int r4 = 0;
        int r5 = 2;
        boolean r6 = true;
    L3:
        if (r4 >= r0) goto L30;
        char r7 = r11.charAt(r4);
        if (r7 != 'c') goto L7;
        r6 = false;
        r5 = 2;
    L29:
        r4 = r4 + 1;
        goto L3
    L7:
        if (r7 != 'l') goto L9;
        r5 = 0;
        r6 = false;
        goto L29
    L9:
        if (r7 != 'r') goto L11;
        r6 = false;
        r5 = 1;
        goto L29
    L11:
        if (r7 != '|') goto L29;
        if (r6 == false) goto L15;
        this.beforeVlines = 1;
    L16:
        int r72 = r4 + 1;
        if (r72 >= r0) goto L25;
        if (r11.charAt(r72) != '|') goto L29;
        if (r6 == false) goto L23;
        this.beforeVlines++;
    L24:
        r4 = r72;
        goto L16
    L23:
        this.afterVlines++;
        goto L24
    L25:
        r4 = r72;
        goto L29
    L15:
        this.afterVlines = 1;
        goto L16
    L30:
        return r5;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        if (this.w != 0.0f) goto L5;
        Box r2 = this.cols.createBox(r3);
    L6:
        r2.type = 12;
        return r2;
    L5:
        r2 = new HorizontalBox(this.cols.createBox(r3), this.w, this.align);
        goto L6
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public int getSkipped() {
        return this.n;
    }

    public boolean hasRightVline() {
        if (this.afterVlines == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void setRowColumn(int r1, int r2) {
        this.row = r1;
        this.col = r2;
    }

    public void setWidth(float r1) {
        this.w = r1;
    }
}
