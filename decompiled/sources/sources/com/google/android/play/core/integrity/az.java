package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* loaded from: classes5.dex */
final class az implements StandardIntegrityManager {

    /* renamed from: a, reason: collision with root package name */
    private final bn f48a;

    /* renamed from: b, reason: collision with root package name */
    private final bt f49b;

    public az(bn r1, bt r2) {
        this.f48a = r1;
        this.f49b = r2;
    }

    public final /* synthetic */ Task a(StandardIntegrityManager.PrepareIntegrityTokenRequest r8, Long r9) {
        long r2 = r8.b();
        long r4 = r9.longValue();
        r8.a();
        return Tasks.forResult(new bs(this.f49b, r2, r4, 0));
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager
    public final Task<StandardIntegrityManager.StandardIntegrityTokenProvider> prepareIntegrityToken(StandardIntegrityManager.PrepareIntegrityTokenRequest r5) {
        long r0 = r5.b();
        r5.a();
        return this.f48a.e(r0, 0).onSuccessTask(new ay(this, r5));
    }
}
