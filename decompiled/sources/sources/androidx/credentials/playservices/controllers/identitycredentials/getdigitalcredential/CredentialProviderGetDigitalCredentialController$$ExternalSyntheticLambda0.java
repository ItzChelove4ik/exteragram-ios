package androidx.credentials.playservices.controllers.identitycredentials.getdigitalcredential;

import android.os.CancellationSignal;
import com.google.android.gms.identitycredentials.PendingGetCredentialHandle;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ CancellationSignal f$0;
    public final /* synthetic */ CredentialProviderGetDigitalCredentialController f$1;

    public /* synthetic */ CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda0(CancellationSignal r1, CredentialProviderGetDigitalCredentialController r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return CredentialProviderGetDigitalCredentialController.m720$r8$lambda$42KrmWHVmTbDLoSyLGZlQ0lLkg(this.f$0, this.f$1, (PendingGetCredentialHandle) r2);
    }
}
