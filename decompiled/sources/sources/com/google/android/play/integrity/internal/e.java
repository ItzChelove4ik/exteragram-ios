package com.google.android.play.integrity.internal;

/* loaded from: classes5.dex */
final class e extends f {

    /* renamed from: a, reason: collision with root package name */
    private final int f127a;

    /* renamed from: b, reason: collision with root package name */
    private final long f128b;

    public e(int r1, long r2) {
        this.f127a = r1;
        this.f128b = r2;
    }

    @Override // com.google.android.play.integrity.internal.f
    public final int a() {
        return this.f127a;
    }

    @Override // com.google.android.play.integrity.internal.f
    public final long b() {
        return this.f128b;
    }

    public final boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof f) == false) goto L12;
        f r62 = (f) r6;
        if (this.f127a != r62.a()) goto L12;
        if (this.f128b != r62.b()) goto L12;
        return true;
    L12:
        return false;
    }

    public final int hashCode() {
        long r0 = this.f128b;
        return ((this.f127a ^ 1000003) * 1000003) ^ ((int) (r0 ^ (r0 >>> 32)));
    }

    public final String toString() {
        return "EventRecord{eventType=" + this.f127a + ", eventTimestamp=" + this.f128b + "}";
    }
}
