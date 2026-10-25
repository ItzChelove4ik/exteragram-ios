package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.core.MutexToken;
import androidx.camera.camera2.pipe.core.Token;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003JL\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u00052'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\b\tH\u0002¢\u0006\u0004\b\f\u0010\rJ>\u0010\u000f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0004*\u00020\u000e2\"\u0010\n\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0082@¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u000eH\u0080@¢\u0006\u0004\b\u0011\u0010\u0012J\\\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u000527\u0010\u0017\u001a3\b\u0001\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0000¢\u0006\u0004\b\u0018\u0010\rR\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/pipe/internal/GraphSessionLock;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "T", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "block", "Lkotlinx/coroutines/Deferred;", "asyncUndispatched", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/core/Token;", "use", "(Landroidx/camera/camera2/pipe/core/Token;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireToken$camera_camera2_pipe", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireToken", "Lkotlin/ParameterName;", "name", "token", "action", "withTokenInAsync$camera_camera2_pipe", "withTokenInAsync", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGraphSessionLock.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GraphSessionLock.kt\nandroidx/camera/camera2/pipe/internal/GraphSessionLock\n+ 2 Mutexes.kt\nandroidx/camera/camera2/pipe/core/MutexesKt\n*L\n1#1,104:1\n98#2,2:105\n*S KotlinDebug\n*F\n+ 1 GraphSessionLock.kt\nandroidx/camera/camera2/pipe/internal/GraphSessionLock\n*L\n43#1:105,2\n*E\n"})
/* loaded from: classes3.dex */
public final class GraphSessionLock {
    private final Mutex mutex;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.internal.GraphSessionLock", f = "GraphSessionLock.kt", i = {0}, l = {98}, m = "use", n = {"$this$use"}, s = {"L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.internal.GraphSessionLock$use$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ GraphSessionLock this$0;

        public AnonymousClass1(GraphSessionLock r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return GraphSessionLock.access$use(this.this$0, null, null, this);
        }
    }

    /* renamed from: $r8$lambda$Rh7Chtw743aTwAubUmFZvrz-3kQ, reason: not valid java name */
    public static Unit m564$r8$lambda$Rh7Chtw743aTwAubUmFZvrz3kQ(CompletableJob r0, Throwable r1) {
        r0.complete();
        return Unit.INSTANCE;
    }

    public GraphSessionLock() {
        this.mutex = MutexKt.Mutex$default(false, 1, null);
    }

    public static final /* synthetic */ Mutex access$getMutex$p(GraphSessionLock r0) {
        return r0.mutex;
    }

    public static final /* synthetic */ Object access$use(GraphSessionLock r0, Token r1, Function2 r2, Continuation r3) {
        return r0.use(r1, r2, r3);
    }

    private final <T> Deferred<T> asyncUndispatched(CoroutineScope r5, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r6) {
        CompletableJob r4 = JobKt.Job((Job) r5.getCoroutineContext().get(Job.Key));
        Deferred<T> r52 = BuildersKt.async(r5, r5.getCoroutineContext().plus(r4), CoroutineStart.UNDISPATCHED, new GraphSessionLock$asyncUndispatched$result$1(r6, null));
        r52.invokeOnCompletion(new GraphSessionLock$$ExternalSyntheticLambda0(r4));
        return r52;
    }

    private final <T> Object use(Token r5, Function2<? super Token, ? super Continuation<? super T>, ? extends Object> r6, Continuation<? super T> r7) {
        if ((r7 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.result;
        Object r72 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        if (r12 == 0) goto L18;
        if (r12 != 1) goto L16;
        r5 = (Token) r0.L$0;
        ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L14
    L22:
        r5.release();
        return r4;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r4);
        r0.L$0 = r5;     // Catch: Throwable -> L14
        r0.label = 1;     // Catch: Throwable -> L14
        r4 = r6.invoke(r5, r0);     // Catch: Throwable -> L14
        if (r4 != r72) goto L22;
        return r72;
    L14:
        th = move-exception;
        r5.release();
        throw th;
    L7:
        r0 = new AnonymousClass1(this, r7);
        goto L8
    }

    public final Object acquireToken$camera_camera2_pipe(Continuation<? super Token> r6) {
        if ((r6 instanceof GraphSessionLock$acquireToken$1) == false) goto L7;
        GraphSessionLock$acquireToken$1 r0 = (GraphSessionLock$acquireToken$1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Mutex r5 = (Mutex) r0.L$0;
        ResultKt.throwOnFailure(r62);
    L18:
        return new MutexToken(r5);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        r5 = this.mutex;
        r0.L$0 = r5;
        r0.label = 1;
        if (Mutex.DefaultImpls.lock$default(r5, null, r0, 1, null) != r12) goto L18;
        return r12;
    L7:
        r0 = new GraphSessionLock$acquireToken$1(this, r6);
        goto L8
    }

    public final <T> Deferred<T> withTokenInAsync$camera_camera2_pipe(CoroutineScope r3, Function2<? super Token, ? super Continuation<? super Deferred<? extends T>>, ? extends Object> r4) {
        return asyncUndispatched(r3, new GraphSessionLock$withTokenInAsync$1(this, r4, null));
    }
}
