package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zabh implements Runnable {
    final /* synthetic */ int zaa;
    final /* synthetic */ zabk zab;

    public zabh(zabk r1, int r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zau(this.zaa);
    }
}
