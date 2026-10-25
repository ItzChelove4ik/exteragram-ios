package androidx.camera.camera2.adapter;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.ZslDisablerQuirk;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.SizesKt;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.MetadataImageReader;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.utils.ZslRingBuffer;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 @2\u00020\u0001:\u0001@B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001a\u0010\u0017J\u000f\u0010\u001b\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001b\u0010\u0019J\u0011\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001f\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u001b\u0010)\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R \u0010+\u001a\u00020*8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b+\u0010,\u0012\u0004\b/\u0010\b\u001a\u0004\b-\u0010.R\u0016\u00100\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u0010\u001b\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u00101R\u0016\u00102\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00101R*\u00104\u001a\u0004\u0018\u0001038\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b4\u00105\u0012\u0004\b:\u0010\b\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0018\u0010<\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010>\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?¨\u0006A"}, d2 = {"Landroidx/camera/camera2/adapter/ZslControlImpl;", "Landroidx/camera/camera2/adapter/ZslControl;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;)V", _UrlKt.FRAGMENT_ENCODE_SET, "reset", "()V", "clearRingBuffer", "Landroidx/camera/core/impl/SessionConfig$Builder;", "sessionConfigBuilder", "addZslConfig", "(Landroidx/camera/core/impl/SessionConfig$Builder;)V", "Landroidx/camera/core/impl/DeferrableSurface;", "surface", "Landroidx/camera/core/impl/SessionConfig;", "sessionConfig", _UrlKt.FRAGMENT_ENCODE_SET, "isZslSurface", "(Landroidx/camera/core/impl/DeferrableSurface;Landroidx/camera/core/impl/SessionConfig;)Z", "disabled", "setZslDisabledByUserCaseConfig", "(Z)V", "isZslDisabledByUserCaseConfig", "()Z", "setZslDisabledByFlashMode", "isZslDisabledByFlashMode", "Landroidx/camera/core/ImageProxy;", "dequeueImageFromBuffer", "()Landroidx/camera/core/ImageProxy;", "clearZslConfig", "Landroidx/camera/camera2/impl/CameraProperties;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "streamConfigurationMap$delegate", "Lkotlin/Lazy;", "getStreamConfigurationMap", "()Landroid/hardware/camera2/params/StreamConfigurationMap;", "streamConfigurationMap", "Landroidx/camera/core/internal/utils/ZslRingBuffer;", "zslRingBuffer", "Landroidx/camera/core/internal/utils/ZslRingBuffer;", "getZslRingBuffer$camera_camera2", "()Landroidx/camera/core/internal/utils/ZslRingBuffer;", "getZslRingBuffer$camera_camera2$annotations", "isZslDisabledByUseCaseConfig", "Z", "isZslDisabledByQuirks", "Landroidx/camera/core/SafeCloseImageReaderProxy;", "reprocessingImageReader", "Landroidx/camera/core/SafeCloseImageReaderProxy;", "getReprocessingImageReader$camera_camera2", "()Landroidx/camera/core/SafeCloseImageReaderProxy;", "setReprocessingImageReader$camera_camera2", "(Landroidx/camera/core/SafeCloseImageReaderProxy;)V", "getReprocessingImageReader$camera_camera2$annotations", "Landroidx/camera/core/impl/CameraCaptureCallback;", "metadataMatchingCaptureCallback", "Landroidx/camera/core/impl/CameraCaptureCallback;", "reprocessingImageDeferrableSurface", "Landroidx/camera/core/impl/DeferrableSurface;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nZslControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZslControl.kt\nandroidx/camera/camera2/adapter/ZslControlImpl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,315:1\n102#2,4:316\n119#2,4:334\n85#2,4:338\n119#2,4:342\n119#2,4:346\n136#2,4:350\n1969#3,14:320\n*S KotlinDebug\n*F\n+ 1 ZslControl.kt\nandroidx/camera/camera2/adapter/ZslControlImpl\n*L\n150#1:316,4\n157#1:334,4\n160#1:338,4\n165#1:342,4\n247#1:346,4\n180#1:350,4\n155#1:320,14\n*E\n"})
/* loaded from: classes3.dex */
public final class ZslControlImpl implements ZslControl {
    public static final Companion Companion = null;
    private final CameraMetadata cameraMetadata;
    private final CameraProperties cameraProperties;
    private boolean isZslDisabledByFlashMode;
    private boolean isZslDisabledByQuirks;
    private boolean isZslDisabledByUseCaseConfig;
    private CameraCaptureCallback metadataMatchingCaptureCallback;
    private DeferrableSurface reprocessingImageDeferrableSurface;
    private SafeCloseImageReaderProxy reprocessingImageReader;
    private final Lazy streamConfigurationMap$delegate;
    private final ZslRingBuffer zslRingBuffer;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/adapter/ZslControlImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "FORMAT", "I", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static void $r8$lambda$7lfc5XsGJyHuvsU6TFnj5hkeOqY(SafeCloseImageReaderProxy r0) {
        r0.safeClose();
    }

    /* renamed from: $r8$lambda$HjVdnBFbCz6juHI-elcMFwa4YdI, reason: not valid java name */
    public static void m25$r8$lambda$HjVdnBFbCz6juHIelcMFwa4YdI(ZslControlImpl r0, ImageReaderProxy r1) {
        ImageProxy r12 = r1.acquireLatestImage();     // Catch: IllegalStateException -> L6
        if (r12 == null) goto L12;
        r0.zslRingBuffer.enqueue(r12);     // Catch: IllegalStateException -> L6
        return;
    L12:
        return;
    L6:
        Camera2Logger r02 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L13;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to acquire latest image");
        return;
    }

    public static void $r8$lambda$K1SuIs8xKcwXeqIjEU3appq_RmM(SafeCloseImageReaderProxy r0) {
        r0.safeClose();
    }

    public static StreamConfigurationMap $r8$lambda$LKuaWDI4O8yhkS0DoPfPSTmRaJI(ZslControlImpl r1) {
        Object r12 = r1.cameraMetadata.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (r12 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L5:
        return (StreamConfigurationMap) r12;
    }

    /* renamed from: $r8$lambda$i5aP0xhWzFmN8Aev1pir-7yn51c, reason: not valid java name */
    public static void m26$r8$lambda$i5aP0xhWzFmN8Aev1pir7yn51c(ImageProxy r0) {
        r0.close();
    }

    static {
        Companion = new Companion(null);
    }

    public ZslControlImpl(CameraProperties r3) {
        this.cameraProperties = r3;
        this.cameraMetadata = r3.getMetadata();
        this.streamConfigurationMap$delegate = LazyKt.lazy(new ZslControlImpl$$ExternalSyntheticLambda0(this));
        this.zslRingBuffer = new ZslRingBuffer(3, new ZslControlImpl$$ExternalSyntheticLambda1());
        if (DeviceQuirks.INSTANCE.get(ZslDisablerQuirk.class) == null) goto L5;
        boolean r32 = true;
    L6:
        this.isZslDisabledByQuirks = r32;
        return;
    L5:
        r32 = false;
        goto L6
    }

    private final void clearRingBuffer() {
        ZslRingBuffer r1 = this.zslRingBuffer;
    L4:
        if (r1.isEmpty() == true) goto L6;
        r1.dequeue().close();
        goto L4
    }

    private final StreamConfigurationMap getStreamConfigurationMap() {
        return (StreamConfigurationMap) this.streamConfigurationMap$delegate.getValue();
    }

    private final void reset() {
        DeferrableSurface r0 = this.reprocessingImageDeferrableSurface;
        if (r0 == null) goto L8;
        SafeCloseImageReaderProxy r1 = this.reprocessingImageReader;
        if (r1 == null) goto L7;
        r0.getTerminationFuture().addListener(new ZslControlImpl$$ExternalSyntheticLambda4(r1), CameraXExecutors.mainThreadExecutor());
        r1.clearOnImageAvailableListener();
        this.reprocessingImageReader = null;
    L7:
        r0.close();
        this.reprocessingImageDeferrableSurface = null;
    L8:
        clearRingBuffer();
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void addZslConfig(SessionConfig.Builder r9) {
        reset();
        if (this.isZslDisabledByUseCaseConfig == false) goto L7;
        r9.setTemplateType(1);
        return;
    L7:
        if (this.isZslDisabledByQuirks == false) goto L11;
        r9.setTemplateType(1);
        return;
    L11:
        if (CameraMetadata.Companion.getSupportsPrivateReprocessing(this.cameraMetadata) == true) goto L17;
        Camera2Logger r8 = Camera2Logger.INSTANCE;
        if (Logger.isInfoEnabled("CXCP") == false) goto L15;
        Log.i(Camera2Logger.access$getTRUNCATED_TAG$p(), "ZslControlImpl: Private reprocessing isn't supported");
    L15:
        r9.setTemplateType(1);
        return;
    L17:
        Iterator r0 = ArraysKt.toList(getStreamConfigurationMap().getInputSizes(34)).iterator();
        if (r0.hasNext() == false) goto L49;
        Object r3 = r0.next();
        if (r0.hasNext() == false) goto L28;
        int r4 = SizesKt.area((Size) r3);
    L23:
        Object r5 = r0.next();
        int r6 = SizesKt.area((Size) r5);
        if (r4 >= r6) goto L27;
        r3 = r5;
        r4 = r6;
    L27:
        if (r0.hasNext() == true) goto L23;
    L28:
        Size r32 = (Size) r3;
        if (r32 != null) goto L34;
        Camera2Logger r82 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L53;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "ZslControlImpl: Unable to find a supported size for ZSL");
        return;
    L53:
        return;
    L34:
        Camera2Logger r02 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L38;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "ZslControlImpl: Selected ZSL size: " + r32);
    L38:
        if (ArraysKt.contains(getStreamConfigurationMap().getValidOutputFormatsForInput(34), 256) == false) goto L40;
        MetadataImageReader r03 = new MetadataImageReader(r32.getWidth(), r32.getHeight(), 34, 9);
        CameraCaptureCallback r2 = r03.getCameraCaptureCallback();
        SafeCloseImageReaderProxy r33 = new SafeCloseImageReaderProxy(r03);
        r03.setOnImageAvailableListener(new ZslControlImpl$$ExternalSyntheticLambda2(this), CameraXExecutors.ioExecutor());
        Surface r42 = r33.getSurface();
        if (r42 == null) goto L47;
        ImmediateSurface r04 = new ImmediateSurface(r42, new Size(r33.getWidth(), r33.getHeight()), 34);
        r04.getTerminationFuture().addListener(new ZslControlImpl$$ExternalSyntheticLambda3(r33), CameraXExecutors.mainThreadExecutor());
        r9.addSurface(r04);
        r9.addCameraCaptureCallback(r2);
        r9.setInputConfiguration(new InputConfiguration(r33.getWidth(), r33.getHeight(), r33.getImageFormat()));
        this.metadataMatchingCaptureCallback = r2;
        this.reprocessingImageReader = r33;
        this.reprocessingImageDeferrableSurface = r04;
        return;
    L47:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L40:
        if (Logger.isWarnEnabled("CXCP") == false) goto L54;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "ZslControlImpl: JPEG isn't valid output for ZSL format");
        return;
    L54:
        return;
    L49:
        Utils$$ExternalSyntheticBUOutline0.m();
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void clearZslConfig() {
        reset();
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public ImageProxy dequeueImageFromBuffer() {
        return this.zslRingBuffer.dequeue();
    L4:
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L11;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "ZslControlImpl#dequeueImageFromBuffer: No such element");
        return null;
    L11:
        return null;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public boolean isZslDisabledByFlashMode() {
        return this.isZslDisabledByFlashMode;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public boolean isZslDisabledByUserCaseConfig() {
        return this.isZslDisabledByUseCaseConfig;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public boolean isZslSurface(DeferrableSurface r2, SessionConfig r3) {
        InputConfiguration r1 = r3.getInputConfiguration();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r2.getPrescribedStreamFormat() == r1.getFormat()) goto L7;
        return false;
    L7:
        if (r2.getPrescribedSize().getWidth() == r1.getWidth()) goto L9;
        return false;
    L9:
        if (r2.getPrescribedSize().getHeight() != r1.getHeight()) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void setZslDisabledByFlashMode(boolean r1) {
        this.isZslDisabledByFlashMode = r1;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void setZslDisabledByUserCaseConfig(boolean r2) {
        if (this.isZslDisabledByUseCaseConfig == r2) goto L6;
        if (r2 == false) goto L6;
        clearRingBuffer();
    L6:
        this.isZslDisabledByUseCaseConfig = r2;
    }
}
