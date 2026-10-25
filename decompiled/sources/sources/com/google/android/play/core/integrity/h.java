package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.StandardIntegrityManager;
import java.util.Set;

/* loaded from: classes5.dex */
final class h extends StandardIntegrityManager.StandardIntegrityTokenRequest {

    /* renamed from: a, reason: collision with root package name */
    private final String f87a;

    /* renamed from: b, reason: collision with root package name */
    private final Set f88b;

    public /* synthetic */ h(String r1, Set r2, g r3) {
        this.f87a = r1;
        this.f88b = r2;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof StandardIntegrityManager.StandardIntegrityTokenRequest) == false) goto L17;
        StandardIntegrityManager.StandardIntegrityTokenRequest r52 = (StandardIntegrityManager.StandardIntegrityTokenRequest) r5;
        String r1 = this.f87a;
        if (r1 != null) goto L13;
        if (r52.requestHash() != null) goto L17;
    L15:
        if (this.f88b.equals(r52.verdictOptOut()) == false) goto L17;
        return true;
    L13:
        if (r1.equals(r52.requestHash()) == true) goto L15;
    L17:
        return false;
    }

    public final int hashCode() {
        String r0 = this.f87a;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r2 = this.f88b.hashCode();
        return r2 ^ ((r02 ^ 1000003) * 1000003);
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest
    public final String requestHash() {
        return this.f87a;
    }

    public final String toString() {
        return "StandardIntegrityTokenRequest{requestHash=" + this.f87a + ", verdictOptOut=" + this.f88b.toString() + "}";
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest
    public final Set<Integer> verdictOptOut() {
        return this.f88b;
    }
}
