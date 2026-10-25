package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.view.Surface;
import androidx.camera.camera2.adapter.CameraUseCaseAdapter;
import androidx.camera.camera2.adapter.CaptureResultAdapter;
import androidx.camera.camera2.compat.Api24Compat;
import androidx.camera.camera2.compat.Api34Compat;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.TagBundle;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\b\u0007\u0018\u0000 I2\u00020\u0001:\u0001IB\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0005*\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u001b\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010 \u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ'\u0010%\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010(\u001a\u00020\u00102\u0006\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b(\u0010)J'\u0010.\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010/\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b/\u00100J\u001f\u00103\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b1\u00102J'\u00108\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u00105\u001a\u000204H\u0016¢\u0006\u0004\b6\u00107J\u001f\u0010:\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0005H\u0016¢\u0006\u0004\b:\u0010;J'\u0010>\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u00105\u001a\u00020<H\u0016¢\u0006\u0004\b=\u00107R \u0010@\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u001b\u0010F\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER\"\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0G8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010A¨\u0006J"}, d2 = {"Landroidx/camera/camera2/impl/CameraCallbackMap;", "Landroidx/camera/camera2/pipe/Request$Listener;", "<init>", "()V", "Landroidx/camera/camera2/pipe/RequestMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "getCaptureConfigId", "(Landroidx/camera/camera2/pipe/RequestMetadata;)I", "requestMetadata", "Landroid/hardware/camera2/CameraCaptureSession;", "getCameraCaptureSession", "(Landroidx/camera/camera2/pipe/RequestMetadata;)Landroid/hardware/camera2/CameraCaptureSession;", "Landroidx/camera/core/impl/CameraCaptureCallback;", Callback.METHOD_NAME, "Ljava/util/concurrent/Executor;", "executor", _UrlKt.FRAGMENT_ENCODE_SET, "addCaptureCallback", "(Landroidx/camera/core/impl/CameraCaptureCallback;Ljava/util/concurrent/Executor;)V", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "Landroidx/camera/camera2/pipe/OutputId;", "outputId", "onBufferLost-iiEMlm4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JII)V", "onBufferLost", "Landroidx/camera/camera2/pipe/FrameInfo;", "result", "onComplete-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onComplete", "Landroidx/camera/camera2/pipe/RequestFailure;", "requestFailure", "onFailed-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "onFailed", "Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, "onAborted", "(Landroidx/camera/camera2/pipe/Request;)V", "Landroidx/camera/camera2/pipe/FrameMetadata;", "captureResult", "onPartialCaptureResult-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameMetadata;)V", "onPartialCaptureResult", "onRequestSequenceAborted", "(Landroidx/camera/camera2/pipe/RequestMetadata;)V", "onRequestSequenceCompleted-RuT0dZU", "(Landroidx/camera/camera2/pipe/RequestMetadata;J)V", "onRequestSequenceCompleted", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "timestamp", "onStarted-uGKBvU4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JJ)V", "onStarted", "progress", "onCaptureProgress", "(Landroidx/camera/camera2/pipe/RequestMetadata;I)V", "Landroidx/camera/camera2/pipe/SensorTimestamp;", "onReadoutStarted-mP9r-9w", "onReadoutStarted", _UrlKt.FRAGMENT_ENCODE_SET, "callbackMap", "Ljava/util/Map;", "rejectOperationCameraCaptureSession$delegate", "Lkotlin/Lazy;", "getRejectOperationCameraCaptureSession", "()Landroid/hardware/camera2/CameraCaptureSession;", "rejectOperationCameraCaptureSession", _UrlKt.FRAGMENT_ENCODE_SET, "callbacks", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraCallbackMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraCallbackMap.kt\nandroidx/camera/camera2/impl/CameraCallbackMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,344:1\n1#2:345\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraCallbackMap implements Request.Listener {
    public static final Companion Companion = null;
    private final Map<CameraCaptureCallback, Executor> callbackMap;
    private volatile Map<CameraCaptureCallback, ? extends Executor> callbacks;
    private final Lazy rejectOperationCameraCaptureSession$delegate;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/impl/CameraCallbackMap$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "createFor", "Landroidx/camera/camera2/impl/CameraCallbackMap;", "callbacks", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/CameraCaptureCallback;", "executor", "Ljava/util/concurrent/Executor;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraCallbackMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraCallbackMap.kt\nandroidx/camera/camera2/impl/CameraCallbackMap$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,344:1\n1869#2,2:345\n*S KotlinDebug\n*F\n+ 1 CameraCallbackMap.kt\nandroidx/camera/camera2/impl/CameraCallbackMap$Companion\n*L\n339#1:345,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CameraCallbackMap createFor(Collection<? extends CameraCaptureCallback> r2, Executor r3) {
            CameraCallbackMap r1 = new CameraCallbackMap();
            Iterator<T> r22 = r2.iterator();
        L4:
            if (r22.hasNext() == false) goto L6;
            r1.addCaptureCallback((CameraCaptureCallback) r22.next(), r3);
            goto L4
        L6:
            return r1;
        }
    }

    /* renamed from: $r8$lambda$2TNcBedz6WxtH3TknruzYJ-HzYM, reason: not valid java name */
    public static void m41$r8$lambda$2TNcBedz6WxtH3TknruzYJHzYM(CameraCaptureCallback r7, CameraCaptureSession r8, CaptureRequest r9, long r10, long r12) {
        ((CameraUseCaseAdapter.CaptureCallbackContainer) r7).getCaptureCallback().onCaptureStarted(r8, r9, r10, r12);
    }

    public static void $r8$lambda$8qSmfZCHZ8mRvFr4xV1KvDj03c0(CameraCaptureCallback r0, int r1) {
        r0.onCaptureCancelled(r1);
    }

    /* renamed from: $r8$lambda$BIAQlY489x3WLkAeaNePlac-wYs, reason: not valid java name */
    public static void m42$r8$lambda$BIAQlY489x3WLkAeaNePlacwYs(CameraCaptureCallback r0, CameraCaptureSession r1, CaptureRequest r2, CaptureResult r3) {
        ((CameraUseCaseAdapter.CaptureCallbackContainer) r0).getCaptureCallback().onCaptureProgressed(r1, r2, r3);
    }

    public static void $r8$lambda$Iw0pZtqtAFlD20zeLvBxONw4I5k(CameraCaptureCallback r0, CameraCallbackMap r1, RequestMetadata r2, CaptureResultAdapter r3) {
        r0.onCaptureCompleted(r1.getCaptureConfigId(r2), r3);
    }

    public static void $r8$lambda$J52EctizhL40aRynqNGgThn8LMo(CameraCaptureCallback r0, CameraCaptureSession r1, CaptureRequest r2, CaptureFailure r3) {
        ((CameraUseCaseAdapter.CaptureCallbackContainer) r0).getCaptureCallback().onCaptureFailed(r1, r2, r3);
    }

    public static void $r8$lambda$KQI8f9OIaJOzCCL54Z1iNtGDsFg(CameraCaptureCallback r0, CameraCallbackMap r1, RequestMetadata r2, CameraCaptureFailure r3) {
        r0.onCaptureFailed(r1.getCaptureConfigId(r2), r3);
    }

    /* renamed from: $r8$lambda$M670Wl8LnPSOrY9T-6VP-TV-WEY, reason: not valid java name */
    public static void m43$r8$lambda$M670Wl8LnPSOrY9T6VPTVWEY(CameraCaptureCallback r0, CameraCallbackMap r1, RequestMetadata r2) {
        r0.onCaptureCancelled(r1.getCaptureConfigId(r2));
    }

    public static void $r8$lambda$PMVjMzWyrx1ZzXBkbMyYMV5Eld4(CameraCaptureCallback r7, CameraCaptureSession r8, CaptureRequest r9, long r10, long r12) {
        Api34Compat.onReadoutStarted(((CameraUseCaseAdapter.CaptureCallbackContainer) r7).getCaptureCallback(), r8, r9, r10, r12);
    }

    /* renamed from: $r8$lambda$QRXFgL4pZ1oKUmL181-seJInXCA, reason: not valid java name */
    public static void m44$r8$lambda$QRXFgL4pZ1oKUmL181seJInXCA(CameraCaptureCallback r0, CameraCallbackMap r1, RequestMetadata r2, int r3) {
        r0.onCaptureProcessProgressed(r1.getCaptureConfigId(r2), r3);
    }

    public static RejectOperationCameraCaptureSession $r8$lambda$Wqwind4PXz1dS9HICx1XpdO2JjQ() {
        return new RejectOperationCameraCaptureSession();
    }

    public static void $r8$lambda$XTdOnnnHsk89bx5uDW3KaDeuITs(CameraCaptureCallback r1, CameraCaptureSession r2, long r3) {
        ((CameraUseCaseAdapter.CaptureCallbackContainer) r1).getCaptureCallback().onCaptureSequenceCompleted(r2, -1, r3);
    }

    /* renamed from: $r8$lambda$fOIs-Catyf0iIb5E-P61FtWeu-s, reason: not valid java name */
    public static void m45$r8$lambda$fOIsCatyf0iIb5EP61FtWeus(CameraCaptureCallback r0, CameraCaptureSession r1, CaptureRequest r2, TotalCaptureResult r3) {
        ((CameraUseCaseAdapter.CaptureCallbackContainer) r0).getCaptureCallback().onCaptureCompleted(r1, r2, r3);
    }

    public static void $r8$lambda$hNQmOr210V_9UdLd15fDwjTXneg(CameraCaptureCallback r0, CameraCaptureSession r1, CaptureRequest r2, CaptureResult r3) {
        ((CameraUseCaseAdapter.CaptureCallbackContainer) r0).getCaptureCallback().onCaptureProgressed(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$rG3mWWpycq50ysOQ-rBg-rpSISs, reason: not valid java name */
    public static void m46$r8$lambda$rG3mWWpycq50ysOQrBgrpSISs(CameraCaptureCallback r1, CameraCaptureSession r2) {
        ((CameraUseCaseAdapter.CaptureCallbackContainer) r1).getCaptureCallback().onCaptureSequenceAborted(r2, -1);
    }

    /* renamed from: $r8$lambda$sePtB5vsRnRT3T8jI-K71hutC_E, reason: not valid java name */
    public static void m47$r8$lambda$sePtB5vsRnRT3T8jIK71hutC_E(CameraCaptureCallback r6, CameraCaptureSession r7, CaptureRequest r8, Surface r9, long r10) {
        Api24Compat.onCaptureBufferLost(((CameraUseCaseAdapter.CaptureCallbackContainer) r6).getCaptureCallback(), r7, r8, r9, r10);
    }

    public static void $r8$lambda$t_nGHsesMsNeWZiLJugjYcMoTyA(CameraCaptureCallback r0, CameraCallbackMap r1, RequestMetadata r2) {
        r0.onCaptureStarted(r1.getCaptureConfigId(r2));
    }

    static {
        Companion = new Companion(null);
    }

    public CameraCallbackMap() {
        this.callbackMap = new LinkedHashMap();
        this.rejectOperationCameraCaptureSession$delegate = LazyKt.lazy(new CameraCallbackMap$$ExternalSyntheticLambda2());
        this.callbacks = MapsKt.emptyMap();
    }

    private final CameraCaptureSession getCameraCaptureSession(RequestMetadata r4) {
        CameraCaptureSession r0 = (CameraCaptureSession) r4.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCaptureSession.class));
        if (r0 == null) goto L5;
        return r0;
    L5:
        if (Build.VERSION.SDK_INT >= 31) goto L7;
    L10:
        return null;
    L7:
        if (CameraCallbackMap$$ExternalSyntheticApiModelOutline1.m(r4.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCallbackMap$$ExternalSyntheticApiModelOutline0.m()))) == null) goto L10;
        return getRejectOperationCameraCaptureSession();
    }

    private final int getCaptureConfigId(RequestMetadata r2) {
        TagBundle r1 = (TagBundle) r2.get(TagsKt.getCAMERAX_TAG_BUNDLE());
        Integer r22 = null;
        if (r1 == null) goto L5;
        Object r12 = r1.getTag("CAPTURE_CONFIG_ID_KEY");
    L7:
        if ((r12 instanceof Integer) == false) goto L9;
        r22 = (Integer) r12;
    L9:
        if (r22 != null) goto L11;
        return -1;
    L11:
        return r22.intValue();
    L5:
        r12 = null;
        goto L7
    }

    private final CameraCaptureSession getRejectOperationCameraCaptureSession() {
        return (CameraCaptureSession) this.rejectOperationCameraCaptureSession$delegate.getValue();
    }

    public final void addCaptureCallback(CameraCaptureCallback r3, Executor r4) {
        if (this.callbacks.containsKey(r3) == true) goto L12;
        Map<CameraCaptureCallback, Executor> r0 = this.callbackMap;
        monitor-enter(r0);
        this.callbackMap.put(r3, r4);     // Catch: Throwable -> L9
        this.callbacks = MapsKt.toMap(this.callbackMap);     // Catch: Throwable -> L9
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L9:
        th = move-exception;
        throw th;
    L12:
        _UtilJvmKt$$ExternalSyntheticBUOutline0.m(r3, " was already registered!");
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onAborted(Request r6) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r5 = this.callbacks.entrySet().iterator();
    L4:
        if (r5.hasNext() == false) goto L19;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r0 = r5.next();
        CameraCaptureCallback r1 = r0.getKey();
        Executor r02 = r0.getValue();
        Object r2 = r6.getExtras().get(TagsKt.getCAMERAX_TAG_BUNDLE());
        Integer r4 = null;
        if ((r2 instanceof TagBundle) == false) goto L8;
        TagBundle r22 = (TagBundle) r2;
    L9:
        if (r22 == null) goto L11;
        Object r23 = r22.getTag("CAPTURE_CONFIG_ID_KEY");
    L13:
        if ((r23 instanceof Integer) == false) goto L15;
        r4 = (Integer) r23;
    L15:
        if (r4 == null) goto L17;
        int r24 = r4.intValue();
    L18:
        r02.execute(new CameraCallbackMap$$ExternalSyntheticLambda3(r1, r24));
        goto L4
    L17:
        r24 = -1;
        goto L18
    L11:
        r23 = null;
        goto L13
    L8:
        r22 = null;
        goto L9
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onBufferLost-iiEMlm4, reason: not valid java name */
    public void mo48onBufferLostiiEMlm4(RequestMetadata r9, long r10, int r12, int r13) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r8 = this.callbacks.entrySet().iterator();
    L4:
        if (r8.hasNext() == false) goto L12;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r132 = r8.next();
        CameraCaptureCallback r2 = r132.getKey();
        Executor r133 = r132.getValue();
        if ((r2 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == false) goto L4;
        CameraCaptureSession r3 = (CameraCaptureSession) r9.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCaptureSession.class));
        CaptureRequest r4 = (CaptureRequest) r9.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        Surface r5 = r9.getStreams().get(StreamId.m401boximpl(r12));
        if (r3 == null) goto L4;
        if (r4 == null) goto L4;
        if (r5 == null) goto L4;
        r133.execute(new CameraCallbackMap$$ExternalSyntheticLambda10(r2, r3, r4, r5, r10));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onCaptureProgress(RequestMetadata r8, int r9) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r0 = this.callbacks.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L13;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r1 = r0.next();
        CameraCaptureCallback r2 = r1.getKey();
        Executor r12 = r1.getValue();
        if ((r2 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == true) goto L7;
        r12.execute(new CameraCallbackMap$$ExternalSyntheticLambda5(r2, this, r8, r9));
        goto L4
    L7:
        CameraCaptureSession r3 = (CameraCaptureSession) r8.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCaptureSession.class));
        CaptureRequest r4 = (CaptureRequest) r8.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        CaptureResult r5 = (CaptureResult) r8.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureResult.class));
        if (r3 == null) goto L4;
        if (r4 == null) goto L4;
        if (r5 == null) goto L4;
        r12.execute(new CameraCallbackMap$$ExternalSyntheticLambda4(r2, r3, r4, r5));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onComplete-CcXjc1I */
    public void mo13onCompleteCcXjc1I(RequestMetadata r10, long r11, FrameInfo r13) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r6 = this.callbacks.entrySet().iterator();
    L4:
        if (r6.hasNext() == false) goto L13;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r0 = r6.next();
        CameraCaptureCallback r7 = r0.getKey();
        Executor r8 = r0.getValue();
        if ((r7 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == true) goto L7;
        r8.execute(new CameraCallbackMap$$ExternalSyntheticLambda15(r7, this, r10, new CaptureResultAdapter(r10, r11, r13, null)));
        goto L4
    L7:
        CameraCaptureSession r02 = getCameraCaptureSession(r10);
        CaptureRequest r2 = (CaptureRequest) r10.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        TotalCaptureResult r3 = (TotalCaptureResult) r13.unwrapAs(Reflection.getOrCreateKotlinClass(TotalCaptureResult.class));
        if (r02 == null) goto L4;
        if (r2 == null) goto L4;
        if (r3 == null) goto L4;
        r8.execute(new CameraCallbackMap$$ExternalSyntheticLambda14(r7, r02, r2, r3));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onFailed-CcXjc1I */
    public void mo14onFailedCcXjc1I(RequestMetadata r6, long r7, RequestFailure r9) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r72 = this.callbacks.entrySet().iterator();
    L4:
        if (r72.hasNext() == false) goto L13;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r8 = r72.next();
        CameraCaptureCallback r0 = r8.getKey();
        Executor r82 = r8.getValue();
        if ((r0 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == true) goto L7;
        r82.execute(new CameraCallbackMap$$ExternalSyntheticLambda7(r0, this, r6, new CameraCaptureFailure(CameraCaptureFailure.Reason.ERROR)));
        goto L4
    L7:
        CameraCaptureSession r1 = getCameraCaptureSession(r6);
        CaptureRequest r2 = (CaptureRequest) r6.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        CaptureFailure r3 = (CaptureFailure) r9.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureFailure.class));
        if (r1 == null) goto L4;
        if (r2 == null) goto L4;
        if (r3 == null) goto L4;
        r82.execute(new CameraCallbackMap$$ExternalSyntheticLambda6(r0, r1, r2, r3));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onPartialCaptureResult-CcXjc1I, reason: not valid java name */
    public void mo49onPartialCaptureResultCcXjc1I(RequestMetadata r5, long r6, FrameMetadata r8) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r4 = this.callbacks.entrySet().iterator();
    L4:
        if (r4.hasNext() == false) goto L12;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r62 = r4.next();
        CameraCaptureCallback r7 = r62.getKey();
        Executor r63 = r62.getValue();
        if ((r7 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == false) goto L4;
        CameraCaptureSession r0 = (CameraCaptureSession) r5.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCaptureSession.class));
        CaptureRequest r1 = (CaptureRequest) r5.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        CaptureResult r2 = (CaptureResult) r8.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureResult.class));
        if (r0 == null) goto L4;
        if (r1 == null) goto L4;
        if (r2 == null) goto L4;
        r63.execute(new CameraCallbackMap$$ExternalSyntheticLambda8(r7, r0, r1, r2));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onReadoutStarted-mP9r-9w, reason: not valid java name */
    public void mo50onReadoutStartedmP9r9w(RequestMetadata r11, long r12, long r14) {
        if (Build.VERSION.SDK_INT < 34) goto L14;
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r10 = this.callbacks.entrySet().iterator();
    L7:
        if (r10.hasNext() == false) goto L24;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r0 = r10.next();
        CameraCaptureCallback r3 = r0.getKey();
        Executor r02 = r0.getValue();
        if ((r3 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == false) goto L7;
        CameraCaptureSession r4 = (CameraCaptureSession) r11.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCaptureSession.class));
        CaptureRequest r5 = (CaptureRequest) r11.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        if (r4 == null) goto L7;
        if (r5 == null) goto L7;
        r02.execute(new CameraCallbackMap$$ExternalSyntheticLambda9(r3, r4, r5, r14, r12));
        goto L7
    L24:
        return;
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onRequestSequenceAborted(RequestMetadata r6) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r0 = this.callbacks.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r1 = r0.next();
        CameraCaptureCallback r2 = r1.getKey();
        Executor r12 = r1.getValue();
        if ((r2 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == true) goto L7;
        r12.execute(new CameraCallbackMap$$ExternalSyntheticLambda17(r2, this, r6));
        goto L4
    L7:
        CameraCaptureSession r3 = (CameraCaptureSession) r6.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCaptureSession.class));
        CaptureRequest r4 = (CaptureRequest) r6.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        if (r3 == null) goto L4;
        if (r4 == null) goto L4;
        r12.execute(new CameraCallbackMap$$ExternalSyntheticLambda16(r2, r3));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onRequestSequenceCompleted-RuT0dZU, reason: not valid java name */
    public void mo51onRequestSequenceCompletedRuT0dZU(RequestMetadata r6, long r7) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r0 = this.callbacks.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r1 = r0.next();
        CameraCaptureCallback r2 = r1.getKey();
        Executor r12 = r1.getValue();
        if ((r2 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == false) goto L4;
        CameraCaptureSession r3 = getCameraCaptureSession(r6);
        CaptureRequest r4 = (CaptureRequest) r6.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        if (r3 == null) goto L4;
        if (r4 == null) goto L4;
        r12.execute(new CameraCallbackMap$$ExternalSyntheticLambda11(r2, r3, r7));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onStarted-uGKBvU4, reason: not valid java name */
    public void mo52onStarteduGKBvU4(RequestMetadata r12, long r13, long r15) {
        Iterator<Map.Entry<CameraCaptureCallback, ? extends Executor>> r0 = this.callbacks.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        Map.Entry<CameraCaptureCallback, ? extends Executor> r1 = r0.next();
        CameraCaptureCallback r4 = r1.getKey();
        Executor r14 = r1.getValue();
        if ((r4 instanceof CameraUseCaseAdapter.CaptureCallbackContainer) == true) goto L7;
        r14.execute(new CameraCallbackMap$$ExternalSyntheticLambda13(r4, this, r12));
        goto L4
    L7:
        CameraCaptureSession r5 = getCameraCaptureSession(r12);
        CaptureRequest r6 = (CaptureRequest) r12.unwrapAs(Reflection.getOrCreateKotlinClass(CaptureRequest.class));
        if (r5 == null) goto L4;
        if (r6 == null) goto L4;
        r14.execute(new CameraCallbackMap$$ExternalSyntheticLambda12(r4, r5, r6, r15, r13));
        goto L4
    }
}
