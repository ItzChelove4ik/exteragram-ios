package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* loaded from: classes5.dex */
public final /* synthetic */ class bs implements StandardIntegrityManager.StandardIntegrityTokenProvider {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ bt f77a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ long f78b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ long f79c;

    public /* synthetic */ bs(bt r1, long r2, long r4, int r6) {
        this.f77a = r1;
        this.f78b = r2;
        this.f79c = r4;
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenProvider
    public final Task request(StandardIntegrityManager.StandardIntegrityTokenRequest r8) {
        return this.f77a.a(this.f78b, this.f79c, 0, r8);
    }
}
