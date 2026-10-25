package androidx.camera.camera2.adapter;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.Deferred;

/* loaded from: classes3.dex */
public final /* synthetic */ class CoroutineAdaptersKt$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ Deferred f$0;
    public final /* synthetic */ CompletableDeferred f$1;
    public final /* synthetic */ Function1 f$2;

    public /* synthetic */ CoroutineAdaptersKt$$ExternalSyntheticLambda6(Deferred r1, CompletableDeferred r2, Function1 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r3) {
        return CoroutineAdaptersKt.$r8$lambda$V0luSTHRidOVmS9hfgdjliJRNUE(this.f$0, this.f$1, this.f$2, (Throwable) r3);
    }
}
