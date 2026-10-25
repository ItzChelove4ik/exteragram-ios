package com.google.android.play.integrity.internal;

/* loaded from: classes5.dex */
final class ap extends an {

    /* renamed from: a, reason: collision with root package name */
    private final ar f112a;

    public ap(ar r2, int r3) {
        super(r2.size(), r3);
        this.f112a = r2;
    }

    @Override // com.google.android.play.integrity.internal.an
    public final Object a(int r1) {
        return this.f112a.get(r1);
    }
}
