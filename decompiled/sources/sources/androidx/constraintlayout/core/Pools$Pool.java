package androidx.constraintlayout.core;

/* loaded from: classes3.dex */
interface Pools$Pool<T> {
    T acquire();

    boolean release(T r1);

    void releaseAll(T[] r1, int r2);
}
