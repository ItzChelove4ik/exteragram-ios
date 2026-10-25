package org.mvel2;

/* loaded from: classes5.dex */
public class ErrorDetail {
    private int column;
    private boolean critical;
    private int cursor;
    private char[] expr;
    private int lineNumber;
    private String message;

    public ErrorDetail(char[] r1, int r2, boolean r3, String r4) {
        this.expr = r1;
        this.cursor = r2;
        this.critical = r3;
        this.message = r4;
        calcRowAndColumn();
    }

    public void calcRowAndColumn() {
        if (this.lineNumber != 0) goto L5;
    L6:
        char[] r0 = this.expr;
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.length == 0) goto L29;
        int r1 = 1;
        int r3 = 0;
        int r2 = 1;
    L13:
        if (r3 >= this.cursor) goto L21;
        char r4 = this.expr[r3];
        if (r4 != '\n') goto L17;
        r1 = r1 + 1;
        r2 = 0;
    L20:
        r3 = r3 + 1;
        goto L13
    L17:
        if (r4 == '\r') goto L20;
        r2 = r2 + 1;
        goto L20
    L21:
        this.lineNumber = r1;
        this.column = r2;
        return;
    L29:
        return;
    L5:
        if (this.column == 0) goto L6;
    }

    public int getColumn() {
        return this.column;
    }

    public int getCursor() {
        return this.cursor;
    }

    public char[] getExpr() {
        return this.expr;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isCritical() {
        return this.critical;
    }

    public void setColumn(int r1) {
        this.column = r1;
    }

    public void setCritical(boolean r1) {
        this.critical = r1;
    }

    public void setCursor(int r1) {
        this.cursor = r1;
    }

    public void setExpr(char[] r1) {
        this.expr = r1;
    }

    public void setLineNumber(int r1) {
        this.lineNumber = r1;
    }

    public void setMessage(String r1) {
        this.message = r1;
    }

    public String toString() {
        boolean r0 = this.critical;
        int r1 = this.lineNumber;
        if (r0 == false) goto L7;
        return "(" + r1 + "," + this.column + ") " + this.message;
    L7:
        return "(" + r1 + "," + this.column + ") WARNING: " + this.message;
    }
}
