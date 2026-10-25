package androidx.credentials.playservices.controllers.identitycredentials.signalcredentialstate;

import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class SignalCredentialStateController$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ CredentialManagerCallback f$1;

    public /* synthetic */ SignalCredentialStateController$$ExternalSyntheticLambda2(Executor r1, CredentialManagerCallback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r2) {
        SignalCredentialStateController.$r8$lambda$tFkdh6pwndChndQeiErFyM9E6N8(this.f$0, this.f$1, r2);
    }
}
