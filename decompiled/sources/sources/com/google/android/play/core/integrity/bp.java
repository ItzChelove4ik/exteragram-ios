package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.s;

/* loaded from: classes5.dex */
public final class bp implements com.google.android.play.integrity.internal.ay {

    /* renamed from: a, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bd f72a;

    /* renamed from: b, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bd f73b;

    /* renamed from: c, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bd f74c;

    public bp(com.google.android.play.integrity.internal.bd r1, com.google.android.play.integrity.internal.bd r2, com.google.android.play.integrity.internal.bd r3, com.google.android.play.integrity.internal.bd r4) {
        this.f72a = r1;
        this.f73b = r2;
        this.f74c = r3;
    }

    @Override // com.google.android.play.integrity.internal.bd
    public final /* bridge */ /* synthetic */ Object a() {
        return new bn((Context) this.f72a.a(), (s) this.f73b.a(), ((au) this.f74c).b(), new j());
    }
}
