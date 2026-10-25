package com.google.android.gms.phenotype;

import android.net.Uri;
import com.google.android.gms.common.api.Api;

/* loaded from: classes5.dex */
public abstract class Phenotype {

    @Deprecated
    private static final Api<Api.ApiOptions.NoOptions> API = null;
    private static final Api.AbstractClientBuilder<com.google.android.gms.internal.phenotype.zze, Api.ApiOptions.NoOptions> CLIENT_BUILDER = null;
    private static final Api.ClientKey<com.google.android.gms.internal.phenotype.zze> CLIENT_KEY = null;

    @Deprecated
    private static final zzm zzaj = null;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.gms.internal.phenotype.zzd, com.google.android.gms.phenotype.zzm] */
    static {
        Api.ClientKey<com.google.android.gms.internal.phenotype.zze> r0 = new Api.ClientKey();
        CLIENT_KEY = r0;
        zzl r1 = new zzl();
        CLIENT_BUILDER = r1;
        API = new Api("Phenotype.API", r1, r0);
        zzaj = new com.google.android.gms.internal.phenotype.zzd();
    }

    public static Uri getContentProviderUri(String r2) {
        String r22 = String.valueOf(Uri.encode(r2));
        if (r22.length() == 0) goto L5;
        String r23 = "content://com.google.android.gms.phenotype/".concat(r22);
    L7:
        return Uri.parse(r23);
    L5:
        r23 = new String("content://com.google.android.gms.phenotype/");
        goto L7
    }
}
