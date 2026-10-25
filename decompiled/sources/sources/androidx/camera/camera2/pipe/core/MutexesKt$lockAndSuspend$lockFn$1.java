package androidx.camera.camera2.pipe.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.sync.Mutex;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* synthetic */ class MutexesKt$lockAndSuspend$lockFn$1 extends FunctionReferenceImpl implements Function2<Mutex, Continuation<? super Unit>, Object>, SuspendFunction {
    public static final MutexesKt$lockAndSuspend$lockFn$1 INSTANCE = null;

    static {
        INSTANCE = new MutexesKt$lockAndSuspend$lockFn$1();
    }

    public MutexesKt$lockAndSuspend$lockFn$1() {
        super(2, MutexesKt.class, "lockWithoutOwner", "lockWithoutOwner(Lkotlinx/coroutines/sync/Mutex;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Mutex r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Mutex r1, Continuation<? super Unit> r2) {
        return MutexesKt.access$lockWithoutOwner(r1, r2);
    }
}
