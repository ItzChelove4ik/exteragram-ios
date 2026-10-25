package com.google.android.play.integrity.internal;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class au extends as {

    /* renamed from: a, reason: collision with root package name */
    static final au f120a = null;
    private static final Object[] d = null;

    /* renamed from: b, reason: collision with root package name */
    final transient Object[] f121b;

    /* renamed from: c, reason: collision with root package name */
    final transient Object[] f122c;

    static {
        Object[] r2 = new Object[0];
        d = r2;
        f120a = new au(r2, 0, r2, 0, 0);
    }

    public au(Object[] r1, int r2, Object[] r3, int r4, int r5) {
        this.f121b = r1;
        this.f122c = r3;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final int a(Object[] r1, int r2) {
        System.arraycopy(this.f121b, 0, r1, 0, 0);
        return 0;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final int b() {
        return 0;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final int c() {
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r1) {
        if (r1 == null) goto L6;
        int r0 = this.f122c.length;
        return false;
    L6:
        return false;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final Object[] e() {
        return this.f121b;
    }

    @Override // com.google.android.play.integrity.internal.as
    public final ar g() {
        int r0 = ar.$r8$clinit;
        return at.f118a;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return 0;
    }

    @Override // com.google.android.play.integrity.internal.as
    public final boolean i() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return f().g(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 0;
    }
}
