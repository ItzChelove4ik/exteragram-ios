package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.StandardIntegrityManager;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class c extends StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder {

    /* renamed from: a, reason: collision with root package name */
    private long f82a;

    /* renamed from: b, reason: collision with root package name */
    private byte f83b;

    public c() {
    }

    public final StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder a(int r1) {
        this.f83b = (byte) (this.f83b | 2);
        return this;
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder
    public final StandardIntegrityManager.PrepareIntegrityTokenRequest build() {
        if (this.f83b == 3) goto L13;
        StringBuilder r0 = new StringBuilder();
        if ((this.f83b & 1) != 0) goto L8;
        r0.append(" cloudProjectNumber");
    L8:
        if ((this.f83b & 2) != 0) goto L10;
        r0.append(" webViewRequestMode");
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0.toString()));
        return null;
    L13:
        return new e(this.f82a, 0, null);
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder
    public final StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder setCloudProjectNumber(long r1) {
        this.f82a = r1;
        this.f83b = (byte) (this.f83b | 1);
        return this;
    }
}
