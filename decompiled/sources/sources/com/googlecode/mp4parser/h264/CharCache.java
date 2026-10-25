package com.googlecode.mp4parser.h264;

/* loaded from: classes5.dex */
public class CharCache {
    private char[] cache;
    private int pos;

    public CharCache(int r1) {
        this.cache = new char[r1];
    }

    public void append(char r4) {
        int r0 = this.pos;
        char[] r1 = this.cache;
        if (r0 >= (r1.length - 1)) goto L6;
        r1[r0] = r4;
        this.pos = r0 + 1;
        return;
    }

    public void clear() {
        this.pos = 0;
    }

    public int length() {
        return this.pos;
    }

    public String toString() {
        return new String(this.cache, 0, this.pos);
    }
}
