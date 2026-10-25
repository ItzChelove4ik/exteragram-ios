package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkerKt$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ Function0 f$1;

    public /* synthetic */ WorkerKt$$ExternalSyntheticLambda0(Executor r1, Function0 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return WorkerKt.m972$r8$lambda$stbDiVrUcYsUSVFbJy_5j0wuK4(this.f$0, this.f$1, r2);
    }
}
