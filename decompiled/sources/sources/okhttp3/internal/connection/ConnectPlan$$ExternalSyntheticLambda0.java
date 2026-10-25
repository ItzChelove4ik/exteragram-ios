package okhttp3.internal.connection;

import kotlin.jvm.functions.Function0;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Handshake;

/* loaded from: classes.dex */
public final /* synthetic */ class ConnectPlan$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ CertificatePinner f$0;
    public final /* synthetic */ Handshake f$1;
    public final /* synthetic */ Address f$2;

    public /* synthetic */ ConnectPlan$$ExternalSyntheticLambda0(CertificatePinner r1, Handshake r2, Address r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return ConnectPlan.$r8$lambda$806SqObLv8d6l4DhU5N2BQVd1eM(this.f$0, this.f$1, this.f$2);
    }
}
