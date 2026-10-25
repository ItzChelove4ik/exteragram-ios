package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.Api;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.mvel2.MVEL;

/* loaded from: classes4.dex */
public final class zzal {
    public static final Api.ClientKey zza = null;
    public static final Api.ClientKey zzb = null;
    public static final Api.ClientKey zzc = null;
    public static final Api.ClientKey zzd = null;

    static {
        zza = new Api.ClientKey();
        zzb = new Api.ClientKey();
        zzc = new Api.ClientKey();
        zzd = new Api.ClientKey();
        new Api.ClientKey();
        new Api.ClientKey();
        Charset r0 = StandardCharsets.UTF_8;
        String.format("%s%s", new Object[]{"receiver-", MVEL.VERSION_SUB});
        int r02 = CastUtils.zza;
    }
}
