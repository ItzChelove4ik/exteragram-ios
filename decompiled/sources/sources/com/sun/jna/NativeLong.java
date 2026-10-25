package com.sun.jna;

/* loaded from: classes5.dex */
public class NativeLong extends IntegerType {
    public static final int SIZE = 0;
    private static final long serialVersionUID = 1;

    static {
        SIZE = Native.LONG_SIZE;
    }

    public NativeLong() {
        this(0);
    }

    public NativeLong(long r2) {
        this(r2, false);
    }

    public NativeLong(long r2, boolean r4) {
        super(SIZE, r2, r4);
    }
}
