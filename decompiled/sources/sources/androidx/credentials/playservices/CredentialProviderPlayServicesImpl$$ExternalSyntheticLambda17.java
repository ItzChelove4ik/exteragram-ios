package androidx.credentials.playservices;

import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda17 implements Function1 {
    public final /* synthetic */ CancellationSignal f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ CredentialManagerCallback f$2;

    public /* synthetic */ CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda17(CancellationSignal r1, Executor r2, CredentialManagerCallback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r3) {
        return CredentialProviderPlayServicesImpl.$r8$lambda$CkXA6uyZF5r3Uy4uE_kF2MrG3TY(this.f$0, this.f$1, this.f$2, (Void) r3);
    }
}
