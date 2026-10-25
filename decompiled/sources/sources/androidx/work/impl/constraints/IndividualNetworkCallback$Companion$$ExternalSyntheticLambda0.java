package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import androidx.work.impl.constraints.IndividualNetworkCallback;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;

/* loaded from: classes4.dex */
public final /* synthetic */ class IndividualNetworkCallback$Companion$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ Ref.BooleanRef f$0;
    public final /* synthetic */ ConnectivityManager f$1;
    public final /* synthetic */ IndividualNetworkCallback f$2;

    public /* synthetic */ IndividualNetworkCallback$Companion$$ExternalSyntheticLambda0(Ref.BooleanRef r1, ConnectivityManager r2, IndividualNetworkCallback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return IndividualNetworkCallback.Companion.m982$r8$lambda$1NfazpWnJwNEPqnnRro2tZElFI(this.f$0, this.f$1, this.f$2);
    }
}
