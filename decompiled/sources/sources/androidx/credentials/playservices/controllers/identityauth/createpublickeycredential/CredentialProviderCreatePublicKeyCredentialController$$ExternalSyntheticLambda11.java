package androidx.credentials.playservices.controllers.identityauth.createpublickeycredential;

import android.os.CancellationSignal;
import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda11 implements OnFailureListener {
    public final /* synthetic */ CredentialProviderCreatePublicKeyCredentialController f$0;
    public final /* synthetic */ CancellationSignal f$1;

    public /* synthetic */ CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda11(CredentialProviderCreatePublicKeyCredentialController r1, CancellationSignal r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r2) {
        CredentialProviderCreatePublicKeyCredentialController.$r8$lambda$todgVpRpq5O2Vis5Z7Mgc5ediXo(this.f$0, this.f$1, r2);
    }
}
