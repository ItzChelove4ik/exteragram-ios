package com.google.android.recaptcha.internal;

import java.util.function.Supplier;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzab implements Supplier {
    static final /* synthetic */ zzab zza = null;
    public static final /* synthetic */ zzab zzb = null;
    private final /* synthetic */ int zzc;

    static {
        zzb = new zzab(1);
        zza = new zzab(0);
    }

    private /* synthetic */ zzab(int r1) {
        this.zzc = r1;
    }

    @Override // java.util.function.Supplier
    public final /* synthetic */ Object get() {
        if (this.zzc == 0) goto L7;
        return zzar.zza(null);
    L7:
        return new zzae();
    }
}
