package androidx.media3.common.util;

/* loaded from: classes3.dex */
public final class Size {
    public static final Size UNKNOWN = null;
    public static final Size ZERO = null;
    private final int height;
    private final int width;

    static {
        UNKNOWN = new Size(-1, -1);
        ZERO = new Size(0, 0);
    }

    public Size(int r2, int r3) {
        if (r2 == (-1)) goto L5;
        if (r2 >= 0) goto L5;
    L8:
        boolean r0 = false;
    L10:
        Assertions.checkArgument(r0);
        this.width = r2;
        this.height = r3;
        return;
    L5:
        if (r3 == (-1)) goto L9;
        if (r3 < 0) goto L8;
    L9:
        r0 = true;
        goto L10
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
        if (this.width != r52.width) goto L15;
        if (this.height != r52.height) goto L15;
        return true;
    L15:
        return false;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int r0 = this.height;
        int r2 = this.width;
        int r1 = r2 << 16;
        return ((r2 >>> 16) | r1) ^ r0;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
