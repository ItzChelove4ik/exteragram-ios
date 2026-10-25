package com.google.android.gms.auth.api;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzbt;

/* loaded from: classes4.dex */
public abstract class AuthProxy {
    public static final Api<AuthProxyOptions> API = null;
    public static final ProxyApi ProxyApi = null;
    public static final Api.ClientKey zza = null;
    private static final Api.AbstractClientBuilder zzb = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zza r1 = new zza();
        zzb = r1;
        API = new Api("Auth.PROXY_API", r1, r0);
        ProxyApi = new zzbt();
    }
}
