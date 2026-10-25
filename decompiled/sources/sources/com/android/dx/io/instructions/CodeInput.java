package com.android.dx.io.instructions;

/* loaded from: classes4.dex */
public interface CodeInput extends CodeCursor {
    boolean hasMore();

    int read();

    int readInt();

    long readLong();
}
