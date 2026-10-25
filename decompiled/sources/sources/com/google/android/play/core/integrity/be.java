package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ai;

/* loaded from: classes5.dex */
final class be extends com.google.android.play.integrity.internal.t {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Context f55a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ bn f56b;

    public be(bn r1, TaskCompletionSource r2, Context r3) {
        this.f55a = r3;
        this.f56b = r1;
        super(r2);
    }

    @Override // com.google.android.play.integrity.internal.t
    public final void b() {
        Context r0 = this.f55a;
        bn.f(this.f56b).trySetResult(Integer.valueOf(ai.a(r0)));
    }
}
