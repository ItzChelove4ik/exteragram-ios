package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* loaded from: classes5.dex */
public final /* synthetic */ class ay implements SuccessContinuation {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ az f46a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ StandardIntegrityManager.PrepareIntegrityTokenRequest f47b;

    public /* synthetic */ ay(az r1, StandardIntegrityManager.PrepareIntegrityTokenRequest r2) {
        this.f46a = r1;
        this.f47b = r2;
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object r2) {
        return this.f46a.a(this.f47b, (Long) r2);
    }
}
