package com.google.android.play.core.integrity;

/* loaded from: classes5.dex */
public final class au implements com.google.android.play.integrity.internal.ay {

    /* renamed from: a, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bd f43a;

    /* renamed from: b, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bd f44b;

    public au(com.google.android.play.integrity.internal.bd r1, com.google.android.play.integrity.internal.bd r2) {
        this.f43a = r1;
        this.f44b = r2;
    }

    @Override // com.google.android.play.integrity.internal.bd
    public final /* bridge */ /* synthetic */ Object a() {
        return b();
    }

    public final at b() {
        return new at(this.f43a, this.f44b);
    }
}
