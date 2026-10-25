package androidx.credentials.playservices.controllers;

import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.playservices.controllers.ResponseUtils;

/* loaded from: classes3.dex */
public final /* synthetic */ class ResponseUtils$Companion$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ CredentialManagerCallback f$0;
    public final /* synthetic */ GetCredentialResponse f$1;

    public /* synthetic */ ResponseUtils$Companion$$ExternalSyntheticLambda5(CredentialManagerCallback r1, GetCredentialResponse r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ResponseUtils.Companion.m682$r8$lambda$JEGM6T_V8V7e_XFAqAqKCqwbUo(this.f$0, this.f$1);
    }
}
