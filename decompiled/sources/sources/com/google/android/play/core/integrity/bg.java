package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import com.google.android.play.integrity.internal.i;

/* loaded from: classes5.dex */
final class bg extends bm {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ StandardIntegrityManager.StandardIntegrityTokenRequest f60a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ long f61b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ long f62c;
    final /* synthetic */ TaskCompletionSource d;
    final /* synthetic */ bn e;

    public bg(bn r1, TaskCompletionSource r2, int r3, StandardIntegrityManager.StandardIntegrityTokenRequest r4, long r5, long r7, TaskCompletionSource r9) {
        this.f60a = r4;
        this.f61b = r5;
        this.f62c = r7;
        this.d = r9;
        this.e = r1;
        super(r1, r2);
    }

    @Override // com.google.android.play.integrity.internal.t
    public final void b() {
        if (bn.l(this.e) == false) goto L7;
        super.a(new StandardIntegrityException(-2, null));
        return;
    L7:
        if (bn.k(this.e, 0) == false) goto L15;
        super.a(new StandardIntegrityException(-14, null));
        return;
    L15:
        bn r3 = this.e;     // Catch: RemoteException -> L12
        ((i) r3.f68a.e()).d(bn.a(r3, this.f60a, this.f61b, this.f62c, 0), new bk(this.e, this.d, this.f61b));     // Catch: RemoteException -> L12
        return;
    L12:
        e = move-exception;
        bn r1 = this.e;
        StandardIntegrityManager.StandardIntegrityTokenRequest r2 = this.f60a;
        bn.i(r1).c(e, "requestExpressIntegrityToken(%s, %s, %s)", new Object[]{r2.requestHash(), this.f60a.verdictOptOut(), Long.valueOf(this.f61b)});
        this.d.trySetException(new StandardIntegrityException(-100, e));
    }
}
