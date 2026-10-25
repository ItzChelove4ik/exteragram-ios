package androidx.camera.camera2.adapter;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.Deferred;

/* loaded from: classes3.dex */
public final /* synthetic */ class CoroutineAdaptersKt$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ Deferred f$0;
    public final /* synthetic */ CompletableDeferred f$1;

    public /* synthetic */ CoroutineAdaptersKt$$ExternalSyntheticLambda2(Deferred r1, CompletableDeferred r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return CoroutineAdaptersKt.$r8$lambda$NqijppL2DSYQX2kgqByrGzDCers(this.f$0, this.f$1, (Throwable) r2);
    }
}
