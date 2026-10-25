package com.google.android.gms.common;

import java.util.concurrent.Callable;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzx extends zzy {
    private final Callable zze;

    public /* synthetic */ zzx(Callable r10, byte[] r11) {
        super(false, 1, 5, null, null, -1, null);
        this.zze = r10;
    }

    @Override // com.google.android.gms.common.zzy
    public final String zza() {
        return (String) this.zze.call();
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }
}
