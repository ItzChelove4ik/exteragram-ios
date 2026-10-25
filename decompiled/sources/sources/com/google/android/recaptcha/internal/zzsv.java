package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzsv implements zzsc {
    public zzsv() {
    }

    @Override // com.google.android.recaptcha.internal.zzsc
    public final Object zzb(Class r2) {
        if (r2.equals(zzsq.class) == false) goto L7;
        return r2.cast(zzsx.zzb());
    L7:
        if (r2.equals(zzvz.class) == true) goto L9;
        return null;
    L9:
        if (zztc.zza.zza() == false) goto L12;
        return null;
    L12:
        return r2.cast(new zzsu(this));
    }
}
