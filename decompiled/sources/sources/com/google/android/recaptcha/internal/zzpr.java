package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzpr extends zzow {
    private final zzpt zza;

    public zzpr(zzpt r2, int r3) {
        super(r2.size(), r3);
        this.zza = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzow
    public final Object zza(int r1) {
        return this.zza.get(r1);
    }
}
