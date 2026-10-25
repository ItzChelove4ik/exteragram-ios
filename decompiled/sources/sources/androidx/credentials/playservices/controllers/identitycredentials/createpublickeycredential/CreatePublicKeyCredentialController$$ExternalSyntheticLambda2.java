package androidx.credentials.playservices.controllers.identitycredentials.createpublickeycredential;

import android.os.CancellationSignal;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class CreatePublicKeyCredentialController$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ CreatePublicKeyCredentialRequest f$0;
    public final /* synthetic */ CreatePublicKeyCredentialController f$1;
    public final /* synthetic */ CredentialManagerCallback f$2;
    public final /* synthetic */ Executor f$3;
    public final /* synthetic */ CancellationSignal f$4;

    public /* synthetic */ CreatePublicKeyCredentialController$$ExternalSyntheticLambda2(CreatePublicKeyCredentialRequest r1, CreatePublicKeyCredentialController r2, CredentialManagerCallback r3, Executor r4, CancellationSignal r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r7) {
        CreatePublicKeyCredentialController.$r8$lambda$R0XYJePXIB2wqkqFXoKnFQ23syc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
