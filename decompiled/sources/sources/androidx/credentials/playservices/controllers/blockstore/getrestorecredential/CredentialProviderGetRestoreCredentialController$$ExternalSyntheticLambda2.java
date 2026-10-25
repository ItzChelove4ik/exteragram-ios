package androidx.credentials.playservices.controllers.blockstore.getrestorecredential;

import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ CancellationSignal f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ CredentialManagerCallback f$2;

    public /* synthetic */ CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda2(CancellationSignal r1, Executor r2, CredentialManagerCallback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r3) {
        CredentialProviderGetRestoreCredentialController.m688$r8$lambda$tO6y8ElnBXVfZNoZjIXJpiQLZI(this.f$0, this.f$1, this.f$2, r3);
    }
}
