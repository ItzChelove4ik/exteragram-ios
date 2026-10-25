package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zabg implements Runnable {
    final /* synthetic */ zabk zaa;

    public zabg(zabk r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zat();
    }
}
