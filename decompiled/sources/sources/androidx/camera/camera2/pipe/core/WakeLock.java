package androidx.camera.camera2.pipe.core;

import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0019B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0006\u0010\u0015\u001a\u00020\u0007J\r\u0010\u0016\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0017J\b\u0010\u0018\u001a\u00020\nH\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/pipe/core/WakeLock;", _UrlKt.FRAGMENT_ENCODE_SET, "scope", "Lkotlinx/coroutines/CoroutineScope;", "timeout", _UrlKt.FRAGMENT_ENCODE_SET, "startTimeoutOnCreation", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/coroutines/CoroutineScope;JZLkotlin/jvm/functions/Function0;)V", "lock", NotificationBadge.NewHtcHomeBadger.COUNT, _UrlKt.FRAGMENT_ENCODE_SET, "timeoutJob", "Lkotlinx/coroutines/Job;", "closed", "acquire", "Landroidx/camera/camera2/pipe/core/Token;", BuildConfig.BUILD_TYPE, "releaseToken", "releaseToken$camera_camera2_pipe", "startTimeout", "WakeLockToken", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWakeLock.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WakeLock.kt\nandroidx/camera/camera2/pipe/core/WakeLock\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,131:1\n1#2:132\n*E\n"})
/* loaded from: classes3.dex */
public final class WakeLock {
    private final Function0<Unit> callback;
    private boolean closed;
    private int count;
    private final Object lock;
    private final CoroutineScope scope;
    private final boolean startTimeoutOnCreation;
    private final long timeout;
    private Job timeoutJob;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/core/WakeLock$WakeLockToken;", "Landroidx/camera/camera2/pipe/core/Token;", "<init>", "(Landroidx/camera/camera2/pipe/core/WakeLock;)V", "_released", "Lkotlinx/atomicfu/AtomicBoolean;", "released", _UrlKt.FRAGMENT_ENCODE_SET, "getReleased", "()Z", BuildConfig.BUILD_TYPE, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class WakeLockToken implements Token {
        private final AtomicBoolean _released;
        final /* synthetic */ WakeLock this$0;

        public WakeLockToken(WakeLock r1) {
            this.this$0 = r1;
            this._released = AtomicFU.atomic(false);
        }

        @Override // androidx.camera.camera2.pipe.core.Token
        public boolean getReleased() {
            return this._released.getValue();
        }

        @Override // androidx.camera.camera2.pipe.core.Token
        public boolean release() {
            if (this._released.compareAndSet(false, true) == false) goto L6;
            this.this$0.releaseToken$camera_camera2_pipe();
            return true;
        L6:
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.core.WakeLock$release$2", f = "WakeLock.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.core.WakeLock$release$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ WakeLock this$0;

        public AnonymousClass2(WakeLock r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r2);
            WakeLock.access$getCallback$p(this.this$0).invoke();
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.core.WakeLock$startTimeout$1", f = "WakeLock.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.core.WakeLock$startTimeout$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ WakeLock this$0;

        public AnonymousClass1(WakeLock r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
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
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r7);
        L11:
            Object r72 = WakeLock.access$getLock$p(this.this$0);
            WakeLock r02 = this.this$0;
            monitor-enter(r72);
        L22:
            th = move-exception;
            throw th;
        L14:
            if (WakeLock.access$getClosed$p(r02) == false) goto L16;
        L24:
            Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L22
            monitor-exit(r72);
            return r6;
        L16:
            if (WakeLock.access$getCount$p(r02) != 0) goto L24;
            WakeLock.access$setTimeoutJob$p(r02, null);     // Catch: Throwable -> L22
            WakeLock.access$setClosed$p(r02, true);     // Catch: Throwable -> L22
            Unit r03 = Unit.INSTANCE;     // Catch: Throwable -> L22
            monitor-exit(r72);
            WakeLock.access$getCallback$p(this.this$0).invoke();
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            long r4 = WakeLock.access$getTimeout$p(this.this$0);
            this.label = 1;
            if (DelayKt.delay(r4, this) != r0) goto L11;
            return r0;
        }
    }

    public WakeLock(CoroutineScope r1, long r2, boolean r4, Function0<Unit> r5) {
        this.scope = r1;
        this.timeout = r2;
        this.startTimeoutOnCreation = r4;
        this.callback = r5;
        Object r12 = new Object();
        this.lock = r12;
        if (r4 == false) goto L11;
        monitor-enter(r12);
        startTimeout();     // Catch: Throwable -> L8
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r12);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public static final /* synthetic */ Function0 access$getCallback$p(WakeLock r0) {
        return r0.callback;
    }

    public static final /* synthetic */ boolean access$getClosed$p(WakeLock r0) {
        return r0.closed;
    }

    public static final /* synthetic */ int access$getCount$p(WakeLock r0) {
        return r0.count;
    }

    public static final /* synthetic */ Object access$getLock$p(WakeLock r0) {
        return r0.lock;
    }

    public static final /* synthetic */ long access$getTimeout$p(WakeLock r2) {
        return r2.timeout;
    }

    public static final /* synthetic */ void access$setClosed$p(WakeLock r0, boolean r1) {
        r0.closed = r1;
    }

    public static final /* synthetic */ void access$setTimeoutJob$p(WakeLock r0, Job r1) {
        r0.timeoutJob = r1;
    }

    private final void startTimeout() {
        this.timeoutJob = BuildersKt.launch$default(this.scope, null, null, new AnonymousClass1(this, null), 3, null);
    }

    public final Token acquire() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L14:
        th = move-exception;
        throw th;
    L6:
        if (this.closed == false) goto L9;
        monitor-exit(r0);
        return null;
    L9:
        int r1 = this.count + 1;     // Catch: Throwable -> L14
        this.count = r1;     // Catch: Throwable -> L14
        if (r1 != 1) goto L17;
        Job r12 = this.timeoutJob;     // Catch: Throwable -> L14
        if (r12 == null) goto L16;
        Job.DefaultImpls.cancel$default(r12, null, 1, null);     // Catch: Throwable -> L14
    L16:
        this.timeoutJob = null;     // Catch: Throwable -> L14
    L17:
        Unit r13 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(r0);
        return new WakeLockToken(this);
    }

    public final boolean release() {
        Object r1 = this.lock;
        monitor-enter(r1);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L10;
        monitor-exit(r1);
        return false;
    L10:
        this.closed = true;     // Catch: Throwable -> L13
        Job r2 = this.timeoutJob;     // Catch: Throwable -> L13
        if (r2 == null) goto L15;
        Job.DefaultImpls.cancel$default(r2, null, 1, null);     // Catch: Throwable -> L13
    L15:
        this.timeoutJob = null;     // Catch: Throwable -> L13
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r1);
        BuildersKt.launch$default(this.scope, null, null, new AnonymousClass2(this, null), 3, null);
        return true;
    }

    public final void releaseToken$camera_camera2_pipe() {
        Object r0 = this.lock;
        monitor-enter(r0);
        int r1 = this.count - 1;
        this.count = r1;     // Catch: Throwable -> L9
        if (r1 == 0) goto L7;
    L11:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L7:
        if (this.closed == true) goto L11;
        startTimeout();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }
}
