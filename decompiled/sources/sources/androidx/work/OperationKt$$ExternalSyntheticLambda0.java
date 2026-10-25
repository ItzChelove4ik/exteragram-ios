package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final /* synthetic */ class OperationKt$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ Tracer f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Function0 f$3;
    public final /* synthetic */ MutableLiveData f$4;

    public /* synthetic */ OperationKt$$ExternalSyntheticLambda0(Executor r1, Tracer r2, String r3, Function0 r4, MutableLiveData r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r7) {
        return OperationKt.m970$r8$lambda$4AmAQmnwY87AwH_dAIVRwuDub0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
