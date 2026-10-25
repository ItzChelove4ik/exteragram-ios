package com.google.android.recaptcha.internal;

import java.security.KeyStore;
import kotlin.jvm.functions.Function0;

/* loaded from: classes5.dex */
final /* synthetic */ class zznj implements Function0 {
    static final /* synthetic */ zznj zza = null;

    static {
        zza = new zznj();
    }

    private /* synthetic */ zznj() {
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        KeyStore r1 = KeyStore.getInstance("AndroidKeyStore");
        r1.load(null);
        return r1;
    }
}
