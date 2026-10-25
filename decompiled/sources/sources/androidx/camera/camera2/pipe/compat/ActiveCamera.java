package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Token;
import androidx.camera.camera2.pipe.core.WakeLock;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J \u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u0018H\u0086@¢\u0006\u0002\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\u000bJ\u000e\u0010\u001e\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/camera/camera2/pipe/compat/ActiveCamera;", _UrlKt.FRAGMENT_ENCODE_SET, "androidCameraState", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "allCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "closeCallback", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/camera/camera2/pipe/compat/AndroidCameraState;Ljava/util/Set;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;)V", "getAllCameraIds$camera_camera2_pipe", "()Ljava/util/Set;", "cameraId", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "current", "Landroidx/camera/camera2/pipe/compat/VirtualCameraState;", "wakelock", "Landroidx/camera/camera2/pipe/core/WakeLock;", "acquire", "Landroidx/camera/camera2/pipe/core/Token;", "connectTo", "virtualCameraState", "token", "(Landroidx/camera/camera2/pipe/compat/VirtualCameraState;Landroidx/camera/camera2/pipe/core/Token;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "awaitClosed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ActiveCamera {
    private final Set<CameraId> allCameraIds;
    private final AndroidCameraState androidCameraState;
    private VirtualCameraState current;
    private final WakeLock wakelock;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.ActiveCamera$1", f = "Camera2DeviceManager.kt", i = {}, l = {137}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.ActiveCamera$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ ActiveCamera this$0;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Landroidx/camera/camera2/pipe/compat/CameraState;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.ActiveCamera$1$1", f = "Camera2DeviceManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        /* renamed from: androidx.camera.camera2.pipe.compat.ActiveCamera$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00011 extends SuspendLambda implements Function2<CameraState, Continuation<? super Boolean>, Object> {
            /* synthetic */ Object L$0;
            int label;

            public C00011(Continuation<? super C00011> r2) {
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
                C00011 r0 = new C00011(r2);
                r0.L$0 = r1;
                return r0;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CameraState r1, Continuation<? super Boolean> r2) {
                return ((C00011) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CameraState r1, Continuation<? super Boolean> r2) {
                return invoke2(r1, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r2) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L13;
                ResultKt.throwOnFailure(r2);
                CameraState r1 = (CameraState) this.L$0;
                if ((r1 instanceof CameraStateClosing) == false) goto L7;
            L10:
                boolean r12 = true;
            L12:
                return Boxing.boxBoolean(r12);
            L7:
                if ((r1 instanceof CameraStateClosed) == true) goto L10;
                r12 = false;
                goto L12
            L13:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public AnonymousClass1(ActiveCamera r1, Continuation<? super AnonymousClass1> r2) {
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
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r5);
        L11:
            ActiveCamera.access$getWakelock$p(this.this$0).release();
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r5);
            StateFlow<CameraState> r52 = ActiveCamera.access$getAndroidCameraState$p(this.this$0).getState();
            C00011 r12 = new C00011(null);
            this.label = 1;
            if (FlowKt.first(r52, r12, this) != r0) goto L11;
            return r0;
        }
    }

    /* renamed from: $r8$lambda$Pft0woi-3wOq-0lzMTXAHXs4ZXo, reason: not valid java name */
    public static Unit m408$r8$lambda$Pft0woi3wOq0lzMTXAHXs4ZXo(Function1 r0, ActiveCamera r1) {
        r0.invoke(r1);
        return Unit.INSTANCE;
    }

    public ActiveCamera(AndroidCameraState r13, Set<CameraId> r14, CoroutineScope r15, Function1<? super ActiveCamera, Unit> r16) {
        this.androidCameraState = r13;
        this.allCameraIds = r14;
        this.wakelock = new WakeLock(r15, 1000, true, new ActiveCamera$$ExternalSyntheticLambda0(r16, this));
        BuildersKt.launch$default(r15, null, null, new AnonymousClass1(this, null), 3, null);
    }

    public static final /* synthetic */ AndroidCameraState access$getAndroidCameraState$p(ActiveCamera r0) {
        return r0.androidCameraState;
    }

    public static final /* synthetic */ WakeLock access$getWakelock$p(ActiveCamera r0) {
        return r0.wakelock;
    }

    public final Token acquire() {
        return this.wakelock.acquire();
    }

    public final Object awaitClosed(Continuation<? super Unit> r1) {
        Object r0 = this.androidCameraState.awaitClosed(r1);
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r0;
    L6:
        return Unit.INSTANCE;
    }

    public final void close() {
        this.wakelock.release();
        this.androidCameraState.close();
    }

    public final Object connectTo(VirtualCameraState r4, Token r5, Continuation<? super Unit> r6) {
        VirtualCameraState r0 = this.current;
        this.current = r4;
        if (r0 == null) goto L5;
        VirtualCamera.m485disconnectTPqeGZw$default(r0, null, 1, null);
    L5:
        Object r3 = r4.connect$camera_camera2_pipe(this.androidCameraState.getState(), r5, r6);
        if (r3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        return r3;
    L9:
        return Unit.INSTANCE;
    }

    public final Set<CameraId> getAllCameraIds$camera_camera2_pipe() {
        return this.allCameraIds;
    }

    /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
    public final String m409getCameraIdDz_R5H8() {
        return this.androidCameraState.m417getCameraIdDz_R5H8();
    }

    public String toString() {
        return "ActiveCamera(cameraId=" + CameraId.m232toStringimpl(m409getCameraIdDz_R5H8()) + ")@" + Integer.toString(super.hashCode(), CharsKt.checkRadix(16));
    }
}
