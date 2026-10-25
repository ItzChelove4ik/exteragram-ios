package androidx.camera.camera2.pipe.core;

import android.os.Handler;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.TimeoutKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\u0004\b\u0011\u0010\u0012JA\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0014\u001a\u00020\u00072\u001c\u0010\u0017\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0015H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ;\u0010\u001d\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u001c\u001a\u00020\u001b2\u001c\u0010\u0017\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0015¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010!R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\t\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b(\u0010$R\u0017\u0010\n\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b)\u0010'R\u0017\u0010\u000b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\"\u001a\u0004\b*\u0010$R\u0017\u0010\f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010%\u001a\u0004\b+\u0010'R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000e0,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010.R\u0011\u0010\u000f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b0\u00101R\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b2\u0010$¨\u00063"}, d2 = {"Landroidx/camera/camera2/pipe/core/Threads;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;", "cameraPipeScope", "cameraPipeDispatchScope", "Ljava/util/concurrent/Executor;", "blockingExecutor", "Lkotlinx/coroutines/CoroutineDispatcher;", "blockingDispatcher", "backgroundExecutor", "backgroundDispatcher", "lightweightExecutor", "lightweightDispatcher", "Lkotlin/Function0;", "Landroid/os/Handler;", "camera2Handler", "camera2Executor", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/Executor;Lkotlinx/coroutines/CoroutineDispatcher;Ljava/util/concurrent/Executor;Lkotlinx/coroutines/CoroutineDispatcher;Ljava/util/concurrent/Executor;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "T", "dispatcher", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "block", "Lkotlinx/coroutines/Deferred;", "runAsyncSupervised", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "timeoutMs", "runBlockingCheckedOrNull", "(JLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlinx/coroutines/CoroutineScope;", "getCameraPipeScope", "()Lkotlinx/coroutines/CoroutineScope;", "Ljava/util/concurrent/Executor;", "getBlockingExecutor", "()Ljava/util/concurrent/Executor;", "Lkotlinx/coroutines/CoroutineDispatcher;", "getBlockingDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "getBackgroundExecutor", "getBackgroundDispatcher", "getLightweightExecutor", "getLightweightDispatcher", "Lkotlin/Lazy;", "_camera2Handler", "Lkotlin/Lazy;", "_camera2Executor", "getCamera2Handler", "()Landroid/os/Handler;", "getCamera2Executor", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nThreads.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Threads.kt\nandroidx/camera/camera2/pipe/core/Threads\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,100:1\n63#2,2:101\n*S KotlinDebug\n*F\n+ 1 Threads.kt\nandroidx/camera/camera2/pipe/core/Threads\n*L\n88#1:101,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Threads {
    private final Lazy<Executor> _camera2Executor;
    private final Lazy<Handler> _camera2Handler;
    private final CoroutineDispatcher backgroundDispatcher;
    private final Executor backgroundExecutor;
    private final CoroutineDispatcher blockingDispatcher;
    private final Executor blockingExecutor;
    private final CoroutineScope cameraPipeDispatchScope;
    private final CoroutineScope cameraPipeScope;
    private final CoroutineDispatcher lightweightDispatcher;
    private final Executor lightweightExecutor;

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.core.Threads$runAsyncSupervised$1", f = "Threads.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.core.Threads$runAsyncSupervised$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function1<Continuation<? super T>, Object> $block;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super Continuation<? super T>, ? extends Object> r1, Continuation<? super AnonymousClass1> r2) {
            this.$block = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.$block, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r4);
            return r4;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r4);
            Function1<Continuation<? super T>, Object> r42 = this.$block;
            this.label = 1;
            Object r3 = r42.invoke(this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.core.Threads$runBlockingCheckedOrNull$1", f = "Threads.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.core.Threads$runBlockingCheckedOrNull$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01001<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function1<Continuation<? super T>, Object> $block;
        final /* synthetic */ long $timeoutMs;
        int label;
        final /* synthetic */ Threads this$0;

        @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.camera.camera2.pipe.core.Threads$runBlockingCheckedOrNull$1$1", f = "Threads.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        /* renamed from: androidx.camera.camera2.pipe.core.Threads$runBlockingCheckedOrNull$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
            final /* synthetic */ Deferred<T> $result;
            int label;

            /* JADX WARN: Multi-variable type inference failed */
            public C00041(Deferred<? extends T> r1, Continuation<? super C00041> r2) {
                this.$result = r1;
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
                return new C00041(this.$result, r2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
                return invoke(r1, (Continuation) r2);
            }

            public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
                return ((C00041) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r4) {
                Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int r1 = this.label;
                if (r1 == 0) goto L9;
                if (r1 != 1) goto L7;
                ResultKt.throwOnFailure(r4);
                return r4;
            L7:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            L9:
                ResultKt.throwOnFailure(r4);
                Deferred<T> r42 = this.$result;
                this.label = 1;
                Object r3 = r42.await(this);
                if (r3 != r0) goto L12;
                return r0;
            L12:
                return r3;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public C01001(Threads r1, Function1<? super Continuation<? super T>, ? extends Object> r2, long r3, Continuation<? super C01001> r5) {
            this.this$0 = r1;
            this.$block = r2;
            this.$timeoutMs = r3;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new C01001(this.this$0, this.$block, this.$timeoutMs, r8);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
            return ((C01001) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r7);
            return r7;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r7);
            Threads r72 = this.this$0;
            Deferred r73 = Threads.access$runAsyncSupervised(r72, r72.getBackgroundDispatcher(), this.$block);
            long r3 = this.$timeoutMs;
            C00041 r12 = new C00041(r73, null);
            this.label = 1;
            Object r6 = TimeoutKt.withTimeoutOrNull(r3, r12, this);
            if (r6 != r0) goto L12;
            return r0;
        L12:
            return r6;
        }
    }

    public static Handler $r8$lambda$TFK4hE4yNW5J9VReSnUURt1j2uc(Function0 r0) {
        return (Handler) r0.invoke();
    }

    /* renamed from: $r8$lambda$z326WYWokXVYn48jwdvxyCW-iTE, reason: not valid java name */
    public static Executor m507$r8$lambda$z326WYWokXVYn48jwdvxyCWiTE(Function0 r0) {
        return (Executor) r0.invoke();
    }

    public Threads(CoroutineScope r1, CoroutineScope r2, Executor r3, CoroutineDispatcher r4, Executor r5, CoroutineDispatcher r6, Executor r7, CoroutineDispatcher r8, Function0<? extends Handler> r9, Function0<? extends Executor> r10) {
        this.cameraPipeScope = r1;
        this.cameraPipeDispatchScope = r2;
        this.blockingExecutor = r3;
        this.blockingDispatcher = r4;
        this.backgroundExecutor = r5;
        this.backgroundDispatcher = r6;
        this.lightweightExecutor = r7;
        this.lightweightDispatcher = r8;
        this._camera2Handler = LazyKt.lazy(new Threads$$ExternalSyntheticLambda0(r9));
        this._camera2Executor = LazyKt.lazy(new Threads$$ExternalSyntheticLambda1(r10));
    }

    public static final /* synthetic */ Deferred access$runAsyncSupervised(Threads r0, CoroutineDispatcher r1, Function1 r2) {
        return r0.runAsyncSupervised(r1, r2);
    }

    private final <T> Deferred<T> runAsyncSupervised(CoroutineDispatcher r7, Function1<? super Continuation<? super T>, ? extends Object> r8) {
        return BuildersKt.async$default(this.cameraPipeDispatchScope, r7, null, new AnonymousClass1(r8, null), 2, null);
    }

    public final CoroutineDispatcher getBackgroundDispatcher() {
        return this.backgroundDispatcher;
    }

    public final CoroutineDispatcher getBlockingDispatcher() {
        return this.blockingDispatcher;
    }

    public final Executor getCamera2Executor() {
        return this._camera2Executor.getValue();
    }

    public final Handler getCamera2Handler() {
        return this._camera2Handler.getValue();
    }

    public final CoroutineScope getCameraPipeScope() {
        return this.cameraPipeScope;
    }

    public final CoroutineDispatcher getLightweightDispatcher() {
        return this.lightweightDispatcher;
    }

    public final Executor getLightweightExecutor() {
        return this.lightweightExecutor;
    }

    public final <T> T runBlockingCheckedOrNull(long r8, Function1<? super Continuation<? super T>, ? extends Object> r10) {
        return (T) BuildersKt.runBlocking(this.blockingDispatcher, new C01001(this, r10, r8, null));
    L4:
        e = move-exception;
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L12;
        android.util.Log.i("CXCP", "runBlockingCheckedOrNull cancelled by thread interruption", e);
        return null;
    L12:
        return null;
    }
}
