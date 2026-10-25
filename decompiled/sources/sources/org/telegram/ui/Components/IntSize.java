package org.telegram.ui.Components;

/* loaded from: classes7.dex */
public class IntSize {
    public int height;
    public int width;

    public IntSize(int r1, int r2) {
        this.width = r1;
        this.height = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L16:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L16;
        IntSize r52 = (IntSize) r5;
        if (this.width == r52.width) goto L14;
        return false;
    L14:
        if (this.height != r52.height) goto L16;
        return true;
    }

    public int hashCode() {
        return (this.width * 31) + this.height;
    }

    public String toString() {
        return "IntSize(" + this.width + ", " + this.height + ")";
    }
}
