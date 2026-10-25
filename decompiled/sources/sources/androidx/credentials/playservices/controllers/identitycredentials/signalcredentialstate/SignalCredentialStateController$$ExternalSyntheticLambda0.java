package androidx.credentials.playservices.controllers.identitycredentials.signalcredentialstate;

import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.identitycredentials.SignalCredentialStateResponse;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class SignalCredentialStateController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ SignalCredentialStateController f$1;
    public final /* synthetic */ CredentialManagerCallback f$2;

    public /* synthetic */ SignalCredentialStateController$$ExternalSyntheticLambda0(Executor r1, SignalCredentialStateController r2, CredentialManagerCallback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r3) {
        return SignalCredentialStateController.$r8$lambda$6KEYjefjbL2D35k9qVaXsXXKNtY(this.f$0, this.f$1, this.f$2, (SignalCredentialStateResponse) r3);
    }
}
