package com.google.android.recaptcha.internal;

import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
final class zztb implements zztd {
    private final AtomicBoolean zza;

    public zztb(boolean r2) {
        this.zza = new AtomicBoolean(false);
    }

    @Override // com.google.android.recaptcha.internal.zztd
    public final boolean zza() {
        return this.zza.get();
    }
}
