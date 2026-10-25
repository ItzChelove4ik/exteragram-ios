package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.i;

/* loaded from: classes5.dex */
final class bf extends bm {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ long f57a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f58b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ bn f59c;

    public bf(bn r1, TaskCompletionSource r2, int r3, long r4, TaskCompletionSource r6) {
        this.f57a = r4;
        this.f58b = r6;
        this.f59c = r1;
        super(r1, r2);
    }

    @Override // com.google.android.play.integrity.internal.t
    public final void b() {
        if (bn.l(this.f59c) == false) goto L7;
        super.a(new StandardIntegrityException(-2, null));
        return;
    L7:
        if (bn.k(this.f59c, 0) == false) goto L15;
        super.a(new StandardIntegrityException(-14, null));
        return;
    L15:
        bn r0 = this.f59c;     // Catch: RemoteException -> L12
        ((i) r0.f68a.e()).e(bn.b(r0, this.f57a, 0), new bl(this.f59c, this.f58b));     // Catch: RemoteException -> L12
        return;
    L12:
        e = move-exception;
        bn r1 = this.f59c;
        long r2 = this.f57a;
        bn.i(r1).c(e, "warmUpIntegrityToken(%s)", new Object[]{Long.valueOf(r2)});
        this.f58b.trySetException(new StandardIntegrityException(-100, e));
    }
}
