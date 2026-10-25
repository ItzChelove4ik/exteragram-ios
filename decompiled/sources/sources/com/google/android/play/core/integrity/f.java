package com.google.android.play.core.integrity;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import java.util.Set;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class f extends StandardIntegrityManager.StandardIntegrityTokenRequest.Builder {

    /* renamed from: a, reason: collision with root package name */
    private String f85a;

    /* renamed from: b, reason: collision with root package name */
    private Set f86b;

    public f() {
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest.Builder
    public final StandardIntegrityManager.StandardIntegrityTokenRequest build() {
        Set r0 = this.f86b;
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: verdictOptOut");
        return null;
    L5:
        return new h(this.f85a, r0, null);
    }

    @Override // com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest.Builder
    public final StandardIntegrityManager.StandardIntegrityTokenRequest.Builder setRequestHash(String r1) {
        this.f85a = r1;
        return this;
    }

    public final StandardIntegrityManager.StandardIntegrityTokenRequest.Builder setVerdictOptOut(Set<Integer> r1) {
        if (r1 == null) goto L5;
        this.f86b = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null verdictOptOut");
        return null;
    }
}
