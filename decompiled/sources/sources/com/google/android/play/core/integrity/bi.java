package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
abstract class bi extends com.google.android.play.integrity.internal.j {

    /* renamed from: a, reason: collision with root package name */
    final TaskCompletionSource f63a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ bn f64b;

    public bi(bn r1, TaskCompletionSource r2) {
        this.f64b = r1;
        this.f63a = r2;
    }

    @Override // com.google.android.play.integrity.internal.k
    public final void b(Bundle r1) {
        this.f64b.f68a.v(this.f63a);
    }

    @Override // com.google.android.play.integrity.internal.k
    public void c(Bundle r1) {
        this.f64b.f68a.v(this.f63a);
    }

    @Override // com.google.android.play.integrity.internal.k
    public final void d(Bundle r1) {
        this.f64b.f68a.v(this.f63a);
    }

    @Override // com.google.android.play.integrity.internal.k
    public void e(Bundle r1) {
        this.f64b.f68a.v(this.f63a);
    }
}
