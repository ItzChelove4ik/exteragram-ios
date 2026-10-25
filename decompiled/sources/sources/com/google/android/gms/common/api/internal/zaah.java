package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zaah implements Runnable {
    final /* synthetic */ zaar zaa;

    public zaah(zaar r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zaar r1 = this.zaa;
        r1.zau().cancelAvailabilityErrorNotifications(r1.zat());
    }
}
