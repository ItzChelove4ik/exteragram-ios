package com.google.android.play.core.integrity;

/* loaded from: classes5.dex */
public final class ba implements com.google.android.play.integrity.internal.ay {

    /* renamed from: a, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bd f52a;

    /* renamed from: b, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bd f53b;

    public ba(com.google.android.play.integrity.internal.bd r1, com.google.android.play.integrity.internal.bd r2) {
        this.f52a = r1;
        this.f53b = r2;
    }

    @Override // com.google.android.play.integrity.internal.bd
    public final /* bridge */ /* synthetic */ Object a() {
        com.google.android.play.integrity.internal.bd r0 = this.f53b;
        Object r2 = this.f52a.a();
        return new az((bn) r2, (bt) r0.a());
    }
}
