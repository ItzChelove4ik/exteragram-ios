package androidx.credentials.playservices.controllers.blockstore.getrestorecredential;

import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialResponse;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ CredentialProviderGetRestoreCredentialController f$0;
    public final /* synthetic */ CancellationSignal f$1;
    public final /* synthetic */ Executor f$2;
    public final /* synthetic */ CredentialManagerCallback f$3;

    public /* synthetic */ CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda0(CredentialProviderGetRestoreCredentialController r1, CancellationSignal r2, Executor r3, CredentialManagerCallback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r4) {
        return CredentialProviderGetRestoreCredentialController.$r8$lambda$RzDSQNuMWPQN0LmT82FX7UtPaM8(this.f$0, this.f$1, this.f$2, this.f$3, (GetRestoreCredentialResponse) r4);
    }
}
