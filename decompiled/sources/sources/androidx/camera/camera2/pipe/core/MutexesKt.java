package androidx.camera.camera2.pipe.core;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aB\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0014\u0010\r\u001a\u00020\u0005*\u00020\fH\u0082@¢\u0006\u0004\b\r\u0010\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0005*\u00020\fH\u0082@¢\u0006\u0004\b\u000f\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/camera/camera2/pipe/core/CoroutineMutex;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "Lkotlinx/coroutines/Job;", "withLockLaunch", "(Landroidx/camera/camera2/pipe/core/CoroutineMutex;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/sync/Mutex;", "lockWithoutOwner", "(Lkotlinx/coroutines/sync/Mutex;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockAndSuspend", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class MutexesKt {

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.core.MutexesKt$withLockLaunch$1", f = "Mutexes.kt", i = {0, 1}, l = {177, 90}, m = "invokeSuspend", n = {"$this$withLockSuspend$iv", "$this$withLockSuspend$iv"}, s = {"L$0", "L$0"}, v = 1)
    @SourceDebugExtension({"SMAP\nMutexes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mutexes.kt\nandroidx/camera/camera2/pipe/core/MutexesKt$withLockLaunch$1\n+ 2 Mutexes.kt\nandroidx/camera/camera2/pipe/core/MutexesKt\n*L\n1#1,176:1\n148#2,5:177\n*S KotlinDebug\n*F\n+ 1 Mutexes.kt\nandroidx/camera/camera2/pipe/core/MutexesKt$withLockLaunch$1\n*L\n90#1:177,5\n*E\n"})
    /* renamed from: androidx.camera.camera2.pipe.core.MutexesKt$withLockLaunch$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ CoroutineMutex $this_withLockLaunch;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(CoroutineMutex r1, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> r2, Continuation<? super AnonymousClass1> r3) {
            this.$this_withLockLaunch = r1;
            this.$block = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$this_withLockLaunch, this.$block, r4);
            r0.L$0 = r3;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L14;
            if (r1 == 1) goto L13;
            if (r1 != 2) goto L11;
            Mutex r7 = (Mutex) this.L$0;
            ResultKt.throwOnFailure(r8);     // Catch: Throwable -> L9
        L21:
            Unit r82 = Unit.INSTANCE;     // Catch: Throwable -> L9
            Mutex.DefaultImpls.unlock$default(r7, null, 1, null);
            return Unit.INSTANCE;
        L9:
            Throwable th = th;
        L26:
            Mutex.DefaultImpls.unlock$default(r7, null, 1, null);
            throw th;
        L11:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L13:
            Function2<CoroutineScope, Continuation<? super Unit>, Object> r12 = (Function2) this.L$1;
            Mutex r5 = (Mutex) this.L$0;
            ResultKt.throwOnFailure(r8);
            Mutex r83 = r5;
        L28:
            this.L$0 = r83;     // Catch: Throwable -> L24
            this.L$1 = null;     // Catch: Throwable -> L24
            this.label = 2;     // Catch: Throwable -> L24
            if (CoroutineScopeKt.coroutineScope(r12, this) != r0) goto L20;
        L19:
            return r0;
        L20:
            r7 = r83;
        L24:
            th = move-exception;
            Mutex r6 = r83;
            th = th;
            r7 = r6;
            goto L26
        L14:
            ResultKt.throwOnFailure(r8);
            CoroutineScopeKt.ensureActive((CoroutineScope) this.L$0);
            r83 = this.$this_withLockLaunch.getMutex$camera_camera2_pipe();
            r12 = this.$block;
            this.L$0 = r83;
            this.L$1 = r12;
            this.label = 1;
            if (MutexesKt.access$lockAndSuspend(r83, this) != r0) goto L28;
            goto L19
        }
    }

    public static final /* synthetic */ Object access$lockAndSuspend(Mutex r0, Continuation r1) {
        return lockAndSuspend(r0, r1);
    }

    public static final /* synthetic */ Object access$lockWithoutOwner(Mutex r0, Continuation r1) {
        return lockWithoutOwner(r0, r1);
    }

    private static final Object lockAndSuspend(Mutex r1, Continuation<? super Unit> r2) {
        if (IntrinsicsKt.wrapWithContinuationImpl(MutexesKt$lockAndSuspend$lockFn$1.INSTANCE, r1, r2) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        IntrinsicsKt.intercepted(r2).resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
    L5:
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        DebugProbesKt.probeCoroutineSuspended(r2);
    L9:
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L12;
        return r12;
    L12:
        return Unit.INSTANCE;
    }

    private static final Object lockWithoutOwner(Mutex r1, Continuation<? super Unit> r2) {
        Object r12 = r1.lock(null, r2);
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r12;
    L6:
        return Unit.INSTANCE;
    }

    public static final Job withLockLaunch(CoroutineMutex r6, CoroutineScope r7, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> r8) {
        return BuildersKt.launch$default(r7, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(r6, r8, null), 1, null);
    }
}
