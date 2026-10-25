package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.s;

/* loaded from: classes5.dex */
final class bk extends bi {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ bn f66c;
    private final s d;
    private final long e;

    public bk(bn r1, TaskCompletionSource r2, long r3) {
        this.f66c = r1;
        super(r1, r2);
        this.d = new s("OnRequestIntegrityTokenCallback");
        this.e = r3;
    }

    @Override // com.google.android.play.core.integrity.bi, com.google.android.play.integrity.internal.k
    public final void c(Bundle r5) {
        super.c(r5);
        this.d.d("onRequestExpressIntegrityToken", new Object[0]);
        ApiException r0 = bn.g(this.f66c).a(r5);
        if (r0 == null) goto L6;
        this.f63a.trySetException(r0);
        return;
    L6:
        long r02 = r5.getLong("request.token.sid");
        bj r3 = new bj(this, bn.j(this.f66c), r02);
        TaskCompletionSource r4 = this.f63a;
        b r03 = new b();
        r03.b(r5.getString("token"));
        r03.a(r3);
        r4.trySetResult(r03.c());
    }
}
