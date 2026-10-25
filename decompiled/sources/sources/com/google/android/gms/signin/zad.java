package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

/* loaded from: classes5.dex */
public abstract class zad {
    public static final Api.ClientKey zaa = null;
    public static final Api.ClientKey zab = null;
    public static final Api.AbstractClientBuilder zac = null;
    static final Api.AbstractClientBuilder zad = null;
    public static final Scope zae = null;
    public static final Scope zaf = null;
    public static final Api zag = null;
    public static final Api zah = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zaa = r0;
        Api.ClientKey r1 = new Api.ClientKey();
        zab = r1;
        zaa r2 = new zaa();
        zac = r2;
        zab r3 = new zab();
        zad = r3;
        zae = new Scope("profile");
        zaf = new Scope("email");
        zag = new Api("SignIn.API", r2, r0);
        zah = new Api("SignIn.INTERNAL_API", r3, r1);
    }
}
