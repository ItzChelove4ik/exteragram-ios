package com.google.android.recaptcha.internal;

import android.util.Base64;
import java.security.GeneralSecurityException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzha implements zzgz {
    public zzha() {
        zzaau.zza();
    }

    @Override // com.google.android.recaptcha.internal.zzgz
    public final Object zza(zzalm r8, String r9, Continuation r10) {
        zzst r7 = (zzst) zzsz.zza(Base64.decode(r9, 0)).zzg(zztg.zza(), zzst.class);
        r7.zza(r8.zzb().zzy(), r8.zza().zzy());     // Catch: GeneralSecurityException -> L6
        return Unit.INSTANCE;
    L7:
        throw new zzhg(zzhf.zzb, zzhe.zzan, null, null, 12, null);
    }
}
