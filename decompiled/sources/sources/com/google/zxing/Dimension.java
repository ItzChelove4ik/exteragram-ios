package com.google.zxing;

import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class Dimension {
    private final int height;
    private final int width;

    public Dimension(int r1, int r2) {
        if (r1 < 0) goto L7;
        if (r2 < 0) goto L7;
        this.width = r1;
        this.height = r2;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof Dimension) == false) goto L10;
        Dimension r42 = (Dimension) r4;
        if (this.width != r42.width) goto L10;
        if (this.height != r42.height) goto L10;
        return true;
    L10:
        return false;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (this.width * 32713) + this.height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
