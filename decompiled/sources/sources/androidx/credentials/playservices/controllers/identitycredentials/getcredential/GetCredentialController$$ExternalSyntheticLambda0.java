package androidx.credentials.playservices.controllers.identitycredentials.getcredential;

import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.identitycredentials.PendingGetCredentialHandle;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class GetCredentialController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ CancellationSignal f$0;
    public final /* synthetic */ GetCredentialController f$1;
    public final /* synthetic */ Executor f$2;
    public final /* synthetic */ CredentialManagerCallback f$3;

    public /* synthetic */ GetCredentialController$$ExternalSyntheticLambda0(CancellationSignal r1, GetCredentialController r2, Executor r3, CredentialManagerCallback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r4) {
        return GetCredentialController.$r8$lambda$GyDDE5ful1wYW65o8QGlZvX14Gk(this.f$0, this.f$1, this.f$2, this.f$3, (PendingGetCredentialHandle) r4);
    }
}
