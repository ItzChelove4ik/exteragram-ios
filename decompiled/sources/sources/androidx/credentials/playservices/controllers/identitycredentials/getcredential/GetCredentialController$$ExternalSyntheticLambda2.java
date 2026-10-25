package androidx.credentials.playservices.controllers.identitycredentials.getcredential;

import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class GetCredentialController$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ GetCredentialRequest f$0;
    public final /* synthetic */ GetCredentialController f$1;
    public final /* synthetic */ CredentialManagerCallback f$2;
    public final /* synthetic */ Executor f$3;
    public final /* synthetic */ CancellationSignal f$4;

    public /* synthetic */ GetCredentialController$$ExternalSyntheticLambda2(GetCredentialRequest r1, GetCredentialController r2, CredentialManagerCallback r3, Executor r4, CancellationSignal r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r7) {
        GetCredentialController.m719$r8$lambda$uOxAj9j1JzqXonniXxyEcPY2CA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
