package org.telegram.messenger.camera;

/* loaded from: classes5.dex */
public final class Size {
    public final int mHeight;
    public final int mWidth;

    public Size(int r1, int r2) {
        this.mWidth = r1;
        this.mHeight = r2;
    }

    private static NumberFormatException invalidSize(String r3) {
        throw new NumberFormatException("Invalid Size: \"" + r3 + "\"");
    }

    public static Size parseSize(String r3) {
        int r0 = r3.indexOf(42);
        if (r0 >= 0) goto L5;
        r0 = r3.indexOf(120);
    L5:
        if (r0 < 0) goto L11;
        return new Size(Integer.parseInt(r3.substring(0, r0)), Integer.parseInt(r3.substring(r0 + 1)));
    L9:
        throw invalidSize(r3);
    L11:
        throw invalidSize(r3);
    }

    public boolean equals(Object r5) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (this != r5) goto L9;
        return true;
    L9:
        if ((r5 instanceof Size) == false) goto L15;
        Size r52 = (Size) r5;
        if (this.mWidth != r52.mWidth) goto L15;
        if (this.mHeight != r52.mHeight) goto L15;
        return true;
    L15:
        return false;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int hashCode() {
        int r0 = this.mHeight;
        int r2 = this.mWidth;
        int r1 = r2 << 16;
        return ((r2 >>> 16) | r1) ^ r0;
    }

    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }
}
