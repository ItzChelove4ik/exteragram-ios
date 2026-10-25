package com.google.android.play.integrity.internal;

import android.os.IBinder;

/* loaded from: classes5.dex */
public final /* synthetic */ class u implements IBinder.DeathRecipient {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ae f131a;

    public /* synthetic */ u(ae r1) {
        this.f131a = r1;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        ae.k(this.f131a);
    }
}
