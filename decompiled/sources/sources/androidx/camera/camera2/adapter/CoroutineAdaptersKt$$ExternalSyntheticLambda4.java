package androidx.camera.camera2.adapter;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class CoroutineAdaptersKt$$ExternalSyntheticLambda4 implements Function1 {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;

    public /* synthetic */ CoroutineAdaptersKt$$ExternalSyntheticLambda4(CallbackToFutureAdapter.Completer r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r1) {
        return CoroutineAdaptersKt.m18$r8$lambda$yh7VCNLWmlYjZayiRXB7rNOQlQ(this.f$0, (Throwable) r1);
    }
}
