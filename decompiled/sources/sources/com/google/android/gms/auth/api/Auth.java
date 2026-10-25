package com.google.android.gms.auth.api;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;

/* loaded from: classes4.dex */
public abstract class Auth {
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API = null;
    public static final GoogleSignInApi GoogleSignInApi = null;

    @Deprecated
    public static final Api<AuthProxyOptions> PROXY_API = null;

    @Deprecated
    public static final ProxyApi ProxyApi = null;
    public static final Api.ClientKey zba = null;
    public static final Api.ClientKey zbb = null;
    private static final Api.AbstractClientBuilder zbc = null;
    private static final Api.AbstractClientBuilder zbd = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zba = r0;
        Api.ClientKey r1 = new Api.ClientKey();
        zbb = r1;
        zba r2 = new zba();
        zbc = r2;
        zbb r3 = new zbb();
        zbd = r3;
        PROXY_API = AuthProxy.API;
        new Api("Auth.CREDENTIALS_API", r2, r0);
        GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", r3, r1);
        ProxyApi = AuthProxy.ProxyApi;
        GoogleSignInApi = new com.google.android.gms.auth.api.signin.internal.zbd();
    }
}
