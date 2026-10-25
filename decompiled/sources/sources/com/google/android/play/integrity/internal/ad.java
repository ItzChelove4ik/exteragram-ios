package com.google.android.play.integrity.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* loaded from: classes5.dex */
final class ad implements ServiceConnection {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ ae f104a;

    public /* synthetic */ ad(ae r1, ac r2) {
        this.f104a = r1;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r3, IBinder r4) {
        ae.f(this.f104a).d("ServiceConnectionImpl.onServiceConnected(%s)", new Object[]{r3});
        aa r32 = new aa(this, r4);
        this.f104a.c().post(r32);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r3) {
        ae.f(this.f104a).d("ServiceConnectionImpl.onServiceDisconnected(%s)", new Object[]{r3});
        ab r32 = new ab(this);
        this.f104a.c().post(r32);
    }
}
