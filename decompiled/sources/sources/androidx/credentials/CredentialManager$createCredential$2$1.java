package androidx.credentials;

import android.os.CancellationSignal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialManager$createCredential$2$1 implements Function1<Throwable, Unit> {
    final /* synthetic */ CancellationSignal $canceller;

    public CredentialManager$createCredential$2$1(CancellationSignal r1) {
        this.$canceller = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r1) {
        this.$canceller.cancel();
    }
}
