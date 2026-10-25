package com.google.android.play.integrity.internal;

import java.util.List;

/* loaded from: classes5.dex */
final class aq extends ar {

    /* renamed from: a, reason: collision with root package name */
    final transient int f113a;

    /* renamed from: b, reason: collision with root package name */
    final transient int f114b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ar f115c;

    public aq(ar r1, int r2, int r3) {
        this.f115c = r1;
        this.f113a = r2;
        this.f114b = r3;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final int b() {
        return (this.f115c.c() + this.f113a) + this.f114b;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final int c() {
        return this.f115c.c() + this.f113a;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final Object[] e() {
        return this.f115c.e();
    }

    @Override // com.google.android.play.integrity.internal.ar
    public final ar f(int r2, int r3) {
        al.c(r2, r3, this.f114b);
        int r0 = this.f113a;
        return this.f115c.f(r2 + r0, r3 + r0);
    }

    @Override // java.util.List
    public final Object get(int r3) {
        al.a(r3, this.f114b, "index");
        return this.f115c.get(r3 + this.f113a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f114b;
    }

    @Override // com.google.android.play.integrity.internal.ar, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return f(r1, r2);
    }
}
