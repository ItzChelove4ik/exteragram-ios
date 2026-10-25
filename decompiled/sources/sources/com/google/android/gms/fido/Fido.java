package com.google.android.gms.fido;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.fido.fido2.Fido2ApiClient;
import com.google.android.gms.internal.fido.zzaa;
import com.google.android.gms.internal.fido.zzab;

/* loaded from: classes4.dex */
public abstract class Fido {
    public static final Api.ClientKey zza = null;
    public static final Api zzb = null;
    public static final zzaa zzc = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zzb = new Api("Fido.U2F_ZERO_PARTY_API", new zzab(), r0);
        zzc = new zzaa();
    }

    public static Fido2ApiClient getFido2ApiClient(Context r1) {
        return new Fido2ApiClient(r1);
    }
}
