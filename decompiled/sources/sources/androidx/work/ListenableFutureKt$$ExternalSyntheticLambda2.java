package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
public final /* synthetic */ class ListenableFutureKt$$ExternalSyntheticLambda2 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Function0 f$2;

    public /* synthetic */ ListenableFutureKt$$ExternalSyntheticLambda2(Executor r1, String r2, Function0 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r3) {
        return ListenableFutureKt.$r8$lambda$X5HWWttRZ_Ir0xD9aqd6GXUt6fY(this.f$0, this.f$1, this.f$2, r3);
    }
}
