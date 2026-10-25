package androidx.credentials.playservices.controllers.identityauth.beginsignin;

import android.os.CancellationSignal;
import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderBeginSignInController$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ CredentialProviderBeginSignInController f$0;
    public final /* synthetic */ CancellationSignal f$1;

    public /* synthetic */ CredentialProviderBeginSignInController$$ExternalSyntheticLambda2(CredentialProviderBeginSignInController r1, CancellationSignal r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r2) {
        CredentialProviderBeginSignInController.$r8$lambda$X7C_ZlfIYxWiFWlSmjScArT1Nl0(this.f$0, this.f$1, r2);
    }
}
