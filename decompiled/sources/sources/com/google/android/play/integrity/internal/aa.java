package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class aa extends t {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ IBinder f101a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ ad f102b;

    public aa(ad r1, IBinder r2) {
        this.f101a = r2;
        this.f102b = r1;
    }

    @Override // com.google.android.play.integrity.internal.t
    public final void b() {
        IInterface r0 = (IInterface) ae.g(this.f102b.f104a).a(this.f101a);
        ae.n(this.f102b.f104a, r0);
        ae.r(this.f102b.f104a);
        ae.m(this.f102b.f104a, false);
        Iterator r02 = ae.i(this.f102b.f104a).iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        ((Runnable) r02.next()).run();
        goto L4
    L6:
        ae.i(this.f102b.f104a).clear();
    }
}
