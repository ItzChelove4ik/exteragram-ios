package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.s;

/* loaded from: classes5.dex */
final class bl extends bi {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ bn f67c;
    private final s d;

    public bl(bn r1, TaskCompletionSource r2) {
        this.f67c = r1;
        super(r1, r2);
        this.d = new s("OnWarmUpIntegrityTokenCallback");
    }

    @Override // com.google.android.play.core.integrity.bi, com.google.android.play.integrity.internal.k
    public final void e(Bundle r4) {
        super.e(r4);
        this.d.d("onWarmUpExpressIntegrityToken", new Object[0]);
        ApiException r0 = bn.g(this.f67c).a(r4);
        TaskCompletionSource r3 = this.f63a;
        if (r0 == null) goto L6;
        r3.trySetException(r0);
        return;
    L6:
        r3.trySetResult(Long.valueOf(r4.getLong("warm.up.sid")));
    }
}
