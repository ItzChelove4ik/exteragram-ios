package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
public final /* synthetic */ class ListenableFutureKt$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;
    public final /* synthetic */ Function0 f$2;

    public /* synthetic */ ListenableFutureKt$$ExternalSyntheticLambda4(AtomicBoolean r1, CallbackToFutureAdapter.Completer r2, Function0 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListenableFutureKt.$r8$lambda$2J7WTnmfyHeSyx3GGU57K1DCNjw(this.f$0, this.f$1, this.f$2);
    }
}
