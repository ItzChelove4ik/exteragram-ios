package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.core.MutexToken;
import androidx.camera.camera2.pipe.core.MutexesKt;
import androidx.camera.camera2.pipe.core.Token;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Mutex;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.internal.GraphSessionLock$withTokenInAsync$1", f = "GraphSessionLock.kt", i = {0, 0, 1}, l = {105, 64, 67}, m = "invokeSuspend", n = {"$this$asyncUndispatched", "$this$acquireTokenAndSuspend$iv", "$this$asyncUndispatched"}, s = {"L$0", "L$1", "L$0"}, v = 1)
@SourceDebugExtension({"SMAP\nGraphSessionLock.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GraphSessionLock.kt\nandroidx/camera/camera2/pipe/internal/GraphSessionLock$withTokenInAsync$1\n+ 2 Mutexes.kt\nandroidx/camera/camera2/pipe/core/MutexesKt\n*L\n1#1,104:1\n107#2,2:105\n*S KotlinDebug\n*F\n+ 1 GraphSessionLock.kt\nandroidx/camera/camera2/pipe/internal/GraphSessionLock$withTokenInAsync$1\n*L\n64#1:105,2\n*E\n"})
/* loaded from: classes3.dex */
public final class GraphSessionLock$withTokenInAsync$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function2<Token, Continuation<? super Deferred<? extends T>>, Object> $action;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ GraphSessionLock this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public GraphSessionLock$withTokenInAsync$1(GraphSessionLock r1, Function2<? super Token, ? super Continuation<? super Deferred<? extends T>>, ? extends Object> r2, Continuation<? super GraphSessionLock$withTokenInAsync$1> r3) {
        this.this$0 = r1;
        this.$action = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        GraphSessionLock$withTokenInAsync$1 r0 = new GraphSessionLock$withTokenInAsync$1(this.this$0, this.$action, r4);
        r0.L$0 = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
        return invoke(r1, (Continuation) r2);
    }

    public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
        return ((GraphSessionLock$withTokenInAsync$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L13;
        if (r1 == 1) goto L12;
        if (r1 == 2) goto L11;
        if (r1 != 3) goto L9;
        ResultKt.throwOnFailure(r10);
        return r10;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        CoroutineScope r12 = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(r10);
    L21:
        CoroutineScopeKt.ensureActive(r12);
        this.L$0 = null;
        this.label = 3;
        Object r9 = ((Deferred) r10).await(this);
        if (r9 != r0) goto L24;
    L23:
        return r0;
    L24:
        return r9;
    L12:
        GraphSessionLock r13 = (GraphSessionLock) this.L$2;
        Mutex r4 = (Mutex) this.L$1;
        CoroutineScope r6 = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(r10);
        CoroutineScope r102 = r6;
    L17:
        MutexToken r62 = new MutexToken(r4);
        GraphSessionLock$withTokenInAsync$1$deferred$1 r42 = new GraphSessionLock$withTokenInAsync$1$deferred$1(this.$action, null);
        this.L$0 = r102;
        this.L$1 = null;
        this.L$2 = null;
        this.label = 2;
        Object r14 = GraphSessionLock.access$use(r13, r62, r42, this);
        if (r14 == r0) goto L23;
        r12 = r102;
        r10 = r14;
        goto L21
    L13:
        ResultKt.throwOnFailure(r10);
        r102 = (CoroutineScope) this.L$0;
        r13 = this.this$0;
        Mutex r63 = GraphSessionLock.access$getMutex$p(r13);
        this.L$0 = r102;
        this.L$1 = r63;
        this.L$2 = r13;
        this.label = 1;
        if (MutexesKt.access$lockAndSuspend(r63, this) == r0) goto L23;
        r4 = r63;
        goto L17
    }
}
