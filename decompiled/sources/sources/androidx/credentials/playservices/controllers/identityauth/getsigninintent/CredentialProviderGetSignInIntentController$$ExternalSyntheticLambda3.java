package androidx.credentials.playservices.controllers.identityauth.getsigninintent;

import android.os.CancellationSignal;
import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderGetSignInIntentController$$ExternalSyntheticLambda3 implements OnFailureListener {
    public final /* synthetic */ CredentialProviderGetSignInIntentController f$0;
    public final /* synthetic */ CancellationSignal f$1;

    public /* synthetic */ CredentialProviderGetSignInIntentController$$ExternalSyntheticLambda3(CredentialProviderGetSignInIntentController r1, CancellationSignal r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r2) {
        CredentialProviderGetSignInIntentController.m706$r8$lambda$a0GrM5QukP6bmXo0cH2qcSPXsk(this.f$0, this.f$1, r2);
    }
}
