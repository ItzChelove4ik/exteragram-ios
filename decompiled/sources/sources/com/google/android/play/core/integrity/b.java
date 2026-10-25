package com.google.android.play.core.integrity;

import c.f$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class b extends bq {

    /* renamed from: a, reason: collision with root package name */
    private String f50a;

    /* renamed from: b, reason: collision with root package name */
    private y f51b;

    public b() {
    }

    @Override // com.google.android.play.core.integrity.bq
    public final bq a(y r1) {
        this.f51b = r1;
        return this;
    }

    @Override // com.google.android.play.core.integrity.bq
    public final bq b(String r1) {
        if (r1 == null) goto L5;
        this.f50a = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null token");
        return null;
    }

    @Override // com.google.android.play.core.integrity.bq
    public final br c() {
        String r0 = this.f50a;
        if (r0 == null) goto L9;
        y r1 = this.f51b;
        if (r1 == null) goto L9;
        return new br(r0, r1);
    L9:
        StringBuilder r02 = new StringBuilder();
        if (this.f50a != null) goto L13;
        r02.append(" token");
    L13:
        if (this.f51b != null) goto L15;
        r02.append(" integrityDialogWrapper");
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r02.toString()));
        return null;
    }
}
