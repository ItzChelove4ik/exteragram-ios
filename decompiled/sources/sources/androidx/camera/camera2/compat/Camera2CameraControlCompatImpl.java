package androidx.camera.camera2.compat;

import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.ComboRequestListenerKt;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.CameraControl;
import androidx.camera.core.impl.Config;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J&\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0002J'\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\n8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\n8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/camera/camera2/compat/Camera2CameraControlCompatImpl;", "Landroidx/camera/camera2/compat/Camera2CameraControlCompat;", "<init>", "()V", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "updateSignalLock", "configBuilder", "Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "updateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "Ljava/lang/Void;", "pendingSignal", "addRequestOption", _UrlKt.FRAGMENT_ENCODE_SET, "bundle", "Landroidx/camera/camera2/interop/CaptureRequestOptions;", "getRequestOption", "clearRequestOption", "cancelCurrentTask", "applyAsync", "Lkotlinx/coroutines/Deferred;", "requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "cancelPreviousTask", _UrlKt.FRAGMENT_ENCODE_SET, "cancelSignal", "msg", _UrlKt.FRAGMENT_ENCODE_SET, "onComplete", "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "result", "Landroidx/camera/camera2/pipe/FrameInfo;", "onComplete-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CameraControlCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CameraControlCompat.kt\nandroidx/camera/camera2/compat/Camera2CameraControlCompatImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,171:1\n1#2:172\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CameraControlCompatImpl implements Camera2CameraControlCompat {
    private Camera2ImplConfig.Builder configBuilder;
    private final Object lock;
    private CompletableDeferred<Void> pendingSignal;
    private CompletableDeferred<Void> updateSignal;
    private final Object updateSignalLock;

    public Camera2CameraControlCompatImpl() {
        this.lock = new Object();
        this.updateSignalLock = new Object();
        this.configBuilder = new Camera2ImplConfig.Builder();
    }

    private final CompletableDeferred<Void> cancelSignal(CompletableDeferred<Void> r1, String r2) {
        r1.completeExceptionally(new CameraControl.OperationCanceledException(r2));
        return r1;
    }

    public static /* synthetic */ CompletableDeferred cancelSignal$default(Camera2CameraControlCompatImpl r0, CompletableDeferred r1, String r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r2 = "Camera2CameraControl was updated with new options.";
    L6:
        return r0.cancelSignal(r1, r2);
    }

    @Override // androidx.camera.camera2.compat.Camera2CameraControlCompat
    public void addRequestOption(CaptureRequestOptions r7) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Iterator<Config.Option<?>> r1 = r7.listOptions().iterator();     // Catch: Throwable -> L8
    L6:
        if (r1.hasNext() == false) goto L10;
        Config.Option<?> r2 = r1.next();     // Catch: Throwable -> L8
        this.configBuilder.getMutableConfig().insertOption(r2, Config.OptionPriority.ALWAYS_OVERRIDE, r7.retrieveOption(r2));     // Catch: Throwable -> L8
        goto L6
    L10:
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.compat.Camera2CameraControlCompat
    public Deferred<Void> applyAsync(UseCaseCameraRequestControl r7, boolean r8) {
        CompletableDeferred<Void> r2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        Object r3 = this.lock;
        monitor-enter(r3);
        Camera2ImplConfig r4 = this.configBuilder.build();     // Catch: Throwable -> L26
        monitor-exit(r3);
        Object r32 = this.updateSignalLock;
        monitor-enter(r32);
        if (r7 == null) goto L18;
        CompletableDeferred<Void> r5 = this.updateSignal;
        if (r8 == false) goto L15;
        if (r5 == null) goto L17;
        cancelSignal$default(this, r5, null, 1, null);     // Catch: Throwable -> L13
    L17:
        this.updateSignal = r2;     // Catch: Throwable -> L13
        r7.updateCamera2ConfigAsync(r4, MapsKt.mapOf(TuplesKt.to("Camera2CameraControl.tag", Integer.valueOf(r2.hashCode()))));     // Catch: Throwable -> L13
    L22:
        monitor-exit(r32);
        return r2;
    L15:
        if (r5 == null) goto L17;
        CoroutineAdaptersKt.propagateTo(r2, r5);     // Catch: Throwable -> L13
        goto L17
    L18:
        CompletableDeferred<Void> r72 = this.pendingSignal;     // Catch: Throwable -> L13
        if (r72 == null) goto L21;
        cancelSignal$default(this, r72, null, 1, null);     // Catch: Throwable -> L13
    L21:
        this.pendingSignal = r2;     // Catch: Throwable -> L13
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    L26:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.compat.Camera2CameraControlCompat
    public void cancelCurrentTask() {
        Object r0 = this.updateSignalLock;
        monitor-enter(r0);
        CompletableDeferred<Void> r1 = this.updateSignal;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        this.updateSignal = null;     // Catch: Throwable -> L7
        cancelSignal(r1, "The camera control has became inactive.");     // Catch: Throwable -> L7
    L9:
        CompletableDeferred<Void> r12 = this.pendingSignal;     // Catch: Throwable -> L7
        if (r12 == null) goto L12;
        this.pendingSignal = null;     // Catch: Throwable -> L7
        cancelSignal(r12, "The camera control has became inactive.");     // Catch: Throwable -> L7
    L12:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.compat.Camera2CameraControlCompat
    public void clearRequestOption() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.configBuilder = new Camera2ImplConfig.Builder();     // Catch: Throwable -> L7
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.compat.Camera2CameraControlCompat
    public CaptureRequestOptions getRequestOption() {
        Object r0 = this.lock;
        monitor-enter(r0);
        CaptureRequestOptions r2 = CaptureRequestOptions.Builder.Companion.from(this.configBuilder.build()).build();     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onComplete-CcXjc1I */
    public void mo13onCompleteCcXjc1I(RequestMetadata r2, long r3, FrameInfo r5) {
        Object r32 = this.updateSignalLock;
        monitor-enter(r32);
        CompletableDeferred<Void> r4 = this.updateSignal;     // Catch: Throwable -> L11
        if (r4 != null) goto L7;
    L13:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r32);
        return;
    L7:
        if (ComboRequestListenerKt.containsTag(r2, "Camera2CameraControl.tag", Integer.valueOf(r4.hashCode())) == false) goto L13;
        r4.complete(null);     // Catch: Throwable -> L11
        this.updateSignal = null;     // Catch: Throwable -> L11
        CompletableDeferred<Void> r42 = this.pendingSignal;     // Catch: Throwable -> L11
        if (r42 == null) goto L13;
        r42.complete(null);     // Catch: Throwable -> L11
        this.pendingSignal = null;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }
}
