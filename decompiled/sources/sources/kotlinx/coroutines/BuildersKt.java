package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"kotlinx/coroutines/BuildersKt__BuildersKt", "kotlinx/coroutines/BuildersKt__Builders_commonKt", "kotlinx/coroutines/BuildersKt__Builders_concurrentKt"}, k = 4, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class BuildersKt {
    public static final <T> Deferred<T> async(CoroutineScope r0, CoroutineContext r1, CoroutineStart r2, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r3) {
        return BuildersKt__Builders_commonKt.async(r0, r1, r2, r3);
    }

    public static /* synthetic */ Deferred async$default(CoroutineScope r0, CoroutineContext r1, CoroutineStart r2, Function2 r3, int r4, Object r5) {
        return BuildersKt__Builders_commonKt.async$default(r0, r1, r2, r3, r4, r5);
    }

    public static final Job launch(CoroutineScope r0, CoroutineContext r1, CoroutineStart r2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> r3) {
        return BuildersKt__Builders_commonKt.launch(r0, r1, r2, r3);
    }

    public static /* synthetic */ Job launch$default(CoroutineScope r0, CoroutineContext r1, CoroutineStart r2, Function2 r3, int r4, Object r5) {
        return BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5);
    }

    @LowPriorityInOverloadResolution
    public static final <T> T runBlocking(CoroutineContext r0, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r1) {
        return (T) BuildersKt__BuildersKt.runBlocking(r0, r1);
    }

    public static /* synthetic */ Object runBlocking$default(CoroutineContext r0, Function2 r1, int r2, Object r3) {
        return BuildersKt__BuildersKt.runBlocking$default(r0, r1, r2, r3);
    }

    public static final <T> T runBlockingImpl(CoroutineContext r0, EventLoop r1, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r2) {
        return (T) BuildersKt__BuildersKt.runBlockingImpl(r0, r1, r2);
    }

    @JvmName(name = "runBlockingK")
    public static final <T> T runBlockingK(CoroutineContext r0, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r1) {
        return (T) BuildersKt__Builders_concurrentKt.runBlockingK(r0, r1);
    }

    public static /* synthetic */ Object runBlockingK$default(CoroutineContext r0, Function2 r1, int r2, Object r3) {
        return BuildersKt__Builders_concurrentKt.runBlockingK$default(r0, r1, r2, r3);
    }

    public static final <T> Object withContext(CoroutineContext r0, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r1, Continuation<? super T> r2) {
        return BuildersKt__Builders_commonKt.withContext(r0, r1, r2);
    }
}
