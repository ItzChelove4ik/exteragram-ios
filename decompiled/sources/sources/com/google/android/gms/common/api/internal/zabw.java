package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* loaded from: classes4.dex */
final /* synthetic */ class zabw implements Runnable {
    private final /* synthetic */ ListenerHolder zaa;
    private final /* synthetic */ ListenerHolder.Notifier zab;

    public /* synthetic */ zabw(ListenerHolder r1, ListenerHolder.Notifier r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zaa.zaa(this.zab);
    }
}
