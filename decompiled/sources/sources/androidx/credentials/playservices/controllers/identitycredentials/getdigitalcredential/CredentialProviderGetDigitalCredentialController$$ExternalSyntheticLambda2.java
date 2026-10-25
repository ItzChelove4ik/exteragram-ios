package androidx.credentials.playservices.controllers.identitycredentials.getdigitalcredential;

import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ CredentialProviderGetDigitalCredentialController f$0;
    public final /* synthetic */ CancellationSignal f$1;
    public final /* synthetic */ Executor f$2;
    public final /* synthetic */ CredentialManagerCallback f$3;

    public /* synthetic */ CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda2(CredentialProviderGetDigitalCredentialController r1, CancellationSignal r2, Executor r3, CredentialManagerCallback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r4) {
        CredentialProviderGetDigitalCredentialController.$r8$lambda$g1NK9BtcsObnn4c8oTppRxi0Lyw(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
