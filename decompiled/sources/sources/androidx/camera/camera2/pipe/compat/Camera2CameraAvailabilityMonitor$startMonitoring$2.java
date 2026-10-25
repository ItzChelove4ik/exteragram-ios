package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.compat.CameraAvailabilityMonitor;
import androidx.camera.camera2.pipe.core.Log;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"androidx/camera/camera2/pipe/compat/Camera2CameraAvailabilityMonitor$startMonitoring$2", "Landroidx/camera/camera2/pipe/compat/CameraAvailabilityMonitor$Session;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "awaitAvailableCamera", _UrlKt.FRAGMENT_ENCODE_SET, "timeoutMillis", _UrlKt.FRAGMENT_ENCODE_SET, "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2CameraAvailabilityMonitor$startMonitoring$2 implements CameraAvailabilityMonitor.Session {
    private final CopyOnWriteArrayList<CompletableDeferred<Unit>> listeners;
    private final CoroutineScope scope;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraAvailabilityMonitor$startMonitoring$2$1", f = "RetryingCameraStateOpener.kt", i = {}, l = {170}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraAvailabilityMonitor$startMonitoring$2$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $cameraId;
        int label;
        final /* synthetic */ Camera2CameraAvailabilityMonitor this$0;
        final /* synthetic */ Camera2CameraAvailabilityMonitor$startMonitoring$2 this$1;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraAvailabilityMonitor$startMonitoring$2$1$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n50#2,2:666\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraAvailabilityMonitor$startMonitoring$2$1$1\n*L\n172#1:666,2\n*E\n"})
        /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraAvailabilityMonitor$startMonitoring$2$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00021<T> implements FlowCollector {
            final /* synthetic */ String $cameraId;
            final /* synthetic */ Camera2CameraAvailabilityMonitor$startMonitoring$2 this$0;

            public C00021(String r1, Camera2CameraAvailabilityMonitor$startMonitoring$2 r2) {
                this.$cameraId = r1;
                this.this$0 = r2;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
                return m428emit0r8Bogc(((CameraId) r1).m233unboximpl(), r2);
            }

            /* renamed from: emit-0r8Bogc, reason: not valid java name */
            public final Object m428emit0r8Bogc(String r1, Continuation<? super Unit> r2) {
                if (CameraId.m230equalsimpl0(r1, this.$cameraId) == false) goto L12;
                if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L7;
                android.util.Log.d("CXCP", CameraId.m232toStringimpl(r1) + " has become available! Notifying listeners...");
            L7:
                Iterator r0 = Camera2CameraAvailabilityMonitor$startMonitoring$2.access$getListeners$p(this.this$0).iterator();
            L9:
                if (r0.hasNext() == false) goto L12;
                ((CompletableDeferred) r0.next()).complete(Unit.INSTANCE);
            L12:
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Camera2CameraAvailabilityMonitor r1, String r2, Camera2CameraAvailabilityMonitor$startMonitoring$2 r3, Continuation<? super AnonymousClass1> r4) {
            this.this$0 = r1;
            this.$cameraId = r2;
            this.this$1 = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new AnonymousClass1(this.this$0, this.$cameraId, this.this$1, r4);
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
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            Flow r62 = Camera2CameraAvailabilityMonitor.access$getAvailableCameraFlow$p(this.this$0);
            C00021 r12 = new C00021(this.$cameraId, this.this$1);
            this.label = 1;
            if (r62.collect(r12, this) != r0) goto L12;
            return r0;
        }
    }

    public Camera2CameraAvailabilityMonitor$startMonitoring$2(Camera2CameraAvailabilityMonitor r8, String r9) {
        CoroutineScope r1 = CoroutineScopeKt.CoroutineScope(Camera2CameraAvailabilityMonitor.access$getThreads$p(r8).getBackgroundDispatcher().plus(SupervisorKt.SupervisorJob(Camera2CameraAvailabilityMonitor.access$getCameraPipeJob$p(r8))));
        this.scope = r1;
        this.listeners = new CopyOnWriteArrayList();
        BuildersKt.launch$default(r1, null, null, new AnonymousClass1(r8, r9, this, null), 3, null);
    }

    public static final /* synthetic */ CopyOnWriteArrayList access$getListeners$p(Camera2CameraAvailabilityMonitor$startMonitoring$2 r0) {
        return r0.listeners;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraAvailabilityMonitor.Session
    public Object awaitAvailableCamera(long r7, Continuation<? super Boolean> r9) {
        if ((r9 instanceof Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$1) == false) goto L7;
        Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$1 r0 = (Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$1) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r4 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        CompletableDeferred<Unit> r72 = (CompletableDeferred) r0.L$0;
        ResultKt.throwOnFailure(r92);
    L18:
        if (r92 != null) goto L21;
        r4 = false;
    L21:
        this.listeners.remove(r72);
        return Boxing.boxBoolean(r4);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r92);
        CompletableDeferred<Unit> r93 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.listeners.add(r93);
        Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$success$1 r22 = new Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$success$1(r93, null);
        r0.L$0 = r93;
        r0.label = 1;
        Object r73 = TimeoutKt.withTimeoutOrNull(r7, r22, r0);
        if (r73 != r12) goto L17;
        return r12;
    L17:
        r92 = r73;
        r72 = r93;
    L7:
        r0 = new Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$1(this, r9);
        goto L8
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
    }
}
