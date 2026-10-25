package org.webrtc;

/* loaded from: classes6.dex */
public class Size {
    public int height;
    public int width;

    public Size(int r1, int r2) {
        this.width = r1;
        this.height = r2;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof Size) == true) goto L5;
        return false;
    L5:
        Size r42 = (Size) r4;
        if (this.width == r42.width) goto L8;
    L11:
        return false;
    L8:
        if (this.height != r42.height) goto L11;
        return true;
    }

    public int hashCode() {
        return ((this.width * 65537) + 1) + this.height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
