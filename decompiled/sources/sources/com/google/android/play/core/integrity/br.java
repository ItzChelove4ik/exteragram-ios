package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.StandardIntegrityManager;

/* loaded from: classes5.dex */
final class br extends StandardIntegrityManager.StandardIntegrityToken {

    /* renamed from: a, reason: collision with root package name */
    private final String f75a;

    /* renamed from: b, reason: collision with root package name */
    private final y f76b;

    public br(String r1, y r2) {
        this.f75a = r1;
        this.f76b = r2;
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityToken
    public final String token() {
        return this.f75a;
    }
}
