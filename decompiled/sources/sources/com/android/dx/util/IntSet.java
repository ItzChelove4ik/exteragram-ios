package com.android.dx.util;

/* loaded from: classes4.dex */
public interface IntSet {
    void add(int r1);

    int elements();

    boolean has(int r1);

    IntIterator iterator();

    void merge(IntSet r1);

    void remove(int r1);
}
