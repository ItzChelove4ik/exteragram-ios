package com.google.android.gms.internal.clearcut;

import android.database.ContentObserver;
import android.os.Handler;

/* loaded from: classes4.dex */
final class zzz extends ContentObserver {
    public zzz(Handler r1) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean r1) {
        zzy.zze().set(true);
    }
}
