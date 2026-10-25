package com.google.android.play.core.integrity;

import android.content.Context;

/* loaded from: classes5.dex */
final class u implements av {

    /* renamed from: a, reason: collision with root package name */
    private Context f92a;

    public /* synthetic */ u(t r1) {
    }

    public final u a(Context r1) {
        r1.getClass();
        this.f92a = r1;
        return this;
    }

    @Override // com.google.android.play.core.integrity.av
    public final aw b() {
        com.google.android.play.integrity.internal.ba.a(this.f92a, Context.class);
        return new w(this.f92a, null);
    }
}
