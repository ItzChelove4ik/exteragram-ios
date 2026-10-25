package androidx.camera.camera2.adapter;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Deferred;

/* loaded from: classes3.dex */
public final /* synthetic */ class CoroutineAdaptersKt$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;
    public final /* synthetic */ Deferred f$1;

    public /* synthetic */ CoroutineAdaptersKt$$ExternalSyntheticLambda5(CallbackToFutureAdapter.Completer r1, Deferred r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return CoroutineAdaptersKt.m17$r8$lambda$jVJCEkJI0ZZBOGnan7ETARyhJY(this.f$0, this.f$1, (Throwable) r2);
    }
}
