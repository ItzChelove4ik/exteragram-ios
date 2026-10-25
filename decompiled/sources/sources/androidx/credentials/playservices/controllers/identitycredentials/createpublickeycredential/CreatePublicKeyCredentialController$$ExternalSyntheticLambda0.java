package androidx.credentials.playservices.controllers.identitycredentials.createpublickeycredential;

import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import com.google.android.gms.identitycredentials.CreateCredentialHandle;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class CreatePublicKeyCredentialController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ CancellationSignal f$0;
    public final /* synthetic */ CreatePublicKeyCredentialController f$1;
    public final /* synthetic */ Executor f$2;
    public final /* synthetic */ CredentialManagerCallback f$3;

    public /* synthetic */ CreatePublicKeyCredentialController$$ExternalSyntheticLambda0(CancellationSignal r1, CreatePublicKeyCredentialController r2, Executor r3, CredentialManagerCallback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r4) {
        return CreatePublicKeyCredentialController.$r8$lambda$qGhktyn4yrwbaYVeRIYX3YUyRnU(this.f$0, this.f$1, this.f$2, this.f$3, (CreateCredentialHandle) r4);
    }
}
