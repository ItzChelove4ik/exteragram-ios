package com.google.android.play.core.integrity;

import android.content.Context;

/* loaded from: classes5.dex */
final class w implements aw {

    /* renamed from: a, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bb f93a;

    /* renamed from: b, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bb f94b;

    /* renamed from: c, reason: collision with root package name */
    private final com.google.android.play.integrity.internal.bb f95c;
    private final com.google.android.play.integrity.internal.bb d;
    private final com.google.android.play.integrity.internal.bb e;
    private final com.google.android.play.integrity.internal.bb f;

    public /* synthetic */ w(Context r4, v r5) {
        com.google.android.play.integrity.internal.ay r42 = com.google.android.play.integrity.internal.az.b(r4);
        this.f93a = r42;
        com.google.android.play.integrity.internal.bb r52 = com.google.android.play.integrity.internal.ax.b(bb.a());
        this.f94b = r52;
        au r1 = new au(r42, n.a());
        this.f95c = r1;
        com.google.android.play.integrity.internal.bb r43 = com.google.android.play.integrity.internal.ax.b(new bp(r42, r52, r1, n.a()));
        this.d = r43;
        com.google.android.play.integrity.internal.bb r53 = com.google.android.play.integrity.internal.ax.b(new bu(r43));
        this.e = r53;
        this.f = com.google.android.play.integrity.internal.ax.b(new ba(r43, r53));
    }

    @Override // com.google.android.play.core.integrity.aw
    public final StandardIntegrityManager a() {
        return (StandardIntegrityManager) this.f.a();
    }
}
