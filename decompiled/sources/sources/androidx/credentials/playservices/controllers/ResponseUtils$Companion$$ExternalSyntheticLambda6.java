package androidx.credentials.playservices.controllers;

import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.playservices.controllers.ResponseUtils;

/* loaded from: classes3.dex */
public final /* synthetic */ class ResponseUtils$Companion$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ CredentialManagerCallback f$0;
    public final /* synthetic */ GetCredentialException f$1;

    public /* synthetic */ ResponseUtils$Companion$$ExternalSyntheticLambda6(CredentialManagerCallback r1, GetCredentialException r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ResponseUtils.Companion.$r8$lambda$u7ojyQKDmKKfz1_tCnTZEKMpmR8(this.f$0, this.f$1);
    }
}
