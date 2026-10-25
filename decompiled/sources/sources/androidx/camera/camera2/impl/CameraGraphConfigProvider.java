package androidx.camera.camera2.impl;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.DynamicRangeProfiles;
import android.media.MediaCodec;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.view.SurfaceHolder;
import androidx.camera.camera2.adapter.GraphStateToCameraStateAdapter;
import androidx.camera.camera2.adapter.ZslControl;
import androidx.camera.camera2.compat.DynamicRangeProfilesCompat;
import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.quirk.CaptureSessionStuckQuirk;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.DisableAbortCapturesOnStopQuirk;
import androidx.camera.camera2.compat.quirk.DisableAbortCapturesOnStopWithSessionProcessorQuirk;
import androidx.camera.camera2.compat.quirk.FinalizeSessionOnCloseQuirk;
import androidx.camera.camera2.compat.quirk.QuickSuccessiveImageCaptureFailsRepeatingRequestQuirk;
import androidx.camera.camera2.compat.workaround.CloseCameraOnCameraGraphClose;
import androidx.camera.camera2.compat.workaround.TemplateParamsOverride;
import androidx.camera.camera2.config.CameraConfig;
import androidx.camera.camera2.internal.DynamicRangeConversions;
import androidx.camera.camera2.interop.Camera2CaptureRequestConfiguratorKt;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.InputStream;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.StreamFormat;
import androidx.camera.camera2.pipe.compat.CameraPipeKeys;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001IB[\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015Jk\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\u0014\b\u0002\u0010&\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'2\u0014\b\u0002\u0010*\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'¢\u0006\u0004\b+\u0010,J\u001c\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u0002J5\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00020(2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0002\b7J+\u00108\u001a\u0004\u0018\u0001092\u0006\u00105\u001a\u00020(2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'H\u0002¢\u0006\u0002\b:J\u0018\u0010;\u001a\u00020<2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010=\u001a\u00020!H\u0002J\u0013\u0010>\u001a\u0004\u0018\u00010?*\u00020@H\u0002¢\u0006\u0002\bAJ\f\u0010B\u001a\u00020C*\u00020\u001fH\u0002J\u0017\u0010D\u001a\u0004\u0018\u00010%2\u0006\u0010E\u001a\u00020FH\u0002¢\u0006\u0002\u0010GJ\b\u0010H\u001a\u000202H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Landroidx/camera/camera2/impl/CameraGraphConfigProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "callbackMap", "Landroidx/camera/camera2/impl/CameraCallbackMap;", "requestListener", "Landroidx/camera/camera2/impl/ComboRequestListener;", "cameraConfig", "Landroidx/camera/camera2/config/CameraConfig;", "cameraQuirks", "Landroidx/camera/camera2/compat/quirk/CameraQuirks;", "zslControl", "Landroidx/camera/camera2/adapter/ZslControl;", "templateParamsOverride", "Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "cameraInteropStateCallbackRepository", "Landroidx/camera/camera2/impl/CameraInteropStateCallbackRepository;", "<init>", "(Landroidx/camera/camera2/impl/CameraCallbackMap;Landroidx/camera/camera2/impl/ComboRequestListener;Landroidx/camera/camera2/config/CameraConfig;Landroidx/camera/camera2/compat/quirk/CameraQuirks;Landroidx/camera/camera2/adapter/ZslControl;Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/core/CameraXConfig;Landroidx/camera/camera2/impl/CameraInteropStateCallbackRepository;)V", "closeCameraOnCameraGraphClose", "Landroidx/camera/camera2/compat/workaround/CloseCameraOnCameraGraphClose;", "supportedDynamicRangeProfiles", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "create", "Landroidx/camera/camera2/impl/CameraGraphConfigProvider$CameraGraphCreationResult;", "operatingMode", "Landroidx/camera/camera2/pipe/CameraGraph$OperatingMode;", "sessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "setOutputType", _UrlKt.FRAGMENT_ENCODE_SET, "graphStateToCameraStateAdapter", "Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;", "camera2ExtensionMode", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceToStreamUseCaseMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/DeferrableSurface;", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceToStreamUseHintMap", "create-79VDu0o", "(ILandroidx/camera/core/impl/SessionConfig;ZLandroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;Ljava/lang/Integer;Ljava/util/Map;Ljava/util/Map;)Landroidx/camera/camera2/impl/CameraGraphConfigProvider$CameraGraphCreationResult;", "createPostviewStream", "Landroidx/camera/camera2/pipe/CameraStream$Config;", "postviewConfig", "Landroidx/camera/core/impl/SessionConfig$OutputConfig;", "physicalCameraIdForAllStreams", _UrlKt.FRAGMENT_ENCODE_SET, "getStreamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "deferrableSurface", "mapping", "getStreamUseCase-MhLBY4I", "getStreamUseHint", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "getStreamUseHint-kVKJKLA", "createCameraGraphFlags", "Landroidx/camera/camera2/pipe/CameraGraph$Flags;", "isExtensions", "toDynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "Landroidx/camera/core/DynamicRange;", "toDynamicRangeProfile--zsJmt4", "toCamera2ImplConfig", "Landroidx/camera/camera2/impl/Camera2ImplConfig;", "getVideoStabilizationModeFromCaptureConfig", "captureConfig", "Landroidx/camera/core/impl/CaptureConfig;", "(Landroidx/camera/core/impl/CaptureConfig;)Ljava/lang/Integer;", "toString", "CameraGraphCreationResult", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraGraphConfigProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraGraphConfigProvider.kt\nandroidx/camera/camera2/impl/CameraGraphConfigProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CameraDevices.kt\nandroidx/camera/camera2/pipe/CameraId$Companion\n+ 4 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,493:1\n1#2:494\n172#3:495\n172#3:496\n119#4,4:497\n85#4,4:501\n136#4,4:505\n*S KotlinDebug\n*F\n+ 1 CameraGraphConfigProvider.kt\nandroidx/camera/camera2/impl/CameraGraphConfigProvider\n*L\n152#1:495\n333#1:496\n365#1:497,4\n385#1:501,4\n457#1:505,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraGraphConfigProvider {
    private final CameraCallbackMap callbackMap;
    private final CameraConfig cameraConfig;
    private final CameraInteropStateCallbackRepository cameraInteropStateCallbackRepository;
    private final CameraMetadata cameraMetadata;
    private final CameraQuirks cameraQuirks;
    private final CameraXConfig cameraXConfig;
    private final CloseCameraOnCameraGraphClose closeCameraOnCameraGraphClose;
    private final ComboRequestListener requestListener;
    private final DynamicRangeProfiles supportedDynamicRangeProfiles;
    private final TemplateParamsOverride templateParamsOverride;
    private final ZslControl zslControl;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/impl/CameraGraphConfigProvider$CameraGraphCreationResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "config", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream$Config;", "Landroidx/camera/core/impl/DeferrableSurface;", "streamConfigMap", "<init>", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;Ljava/util/Map;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "getConfig", "()Landroidx/camera/camera2/pipe/CameraGraph$Config;", "Ljava/util/Map;", "getStreamConfigMap", "()Ljava/util/Map;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CameraGraphCreationResult {
        private final CameraGraph.Config config;
        private final Map<CameraStream.Config, DeferrableSurface> streamConfigMap;

        /* JADX WARN: Multi-variable type inference failed */
        public CameraGraphCreationResult(CameraGraph.Config r1, Map<CameraStream.Config, ? extends DeferrableSurface> r2) {
            this.config = r1;
            this.streamConfigMap = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof CameraGraphCreationResult) == true) goto L8;
            return false;
        L8:
            CameraGraphCreationResult r52 = (CameraGraphCreationResult) r5;
            if (Intrinsics.areEqual(this.config, r52.config) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.streamConfigMap, r52.streamConfigMap) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final CameraGraph.Config getConfig() {
            return this.config;
        }

        public final Map<CameraStream.Config, DeferrableSurface> getStreamConfigMap() {
            return this.streamConfigMap;
        }

        public int hashCode() {
            return (this.config.hashCode() * 31) + this.streamConfigMap.hashCode();
        }

        public String toString() {
            return "CameraGraphCreationResult(config=" + this.config + ", streamConfigMap=" + this.streamConfigMap + ')';
        }
    }

    public CameraGraphConfigProvider(CameraCallbackMap r1, ComboRequestListener r2, CameraConfig r3, CameraQuirks r4, ZslControl r5, TemplateParamsOverride r6, CameraMetadata r7, CameraXConfig r8, CameraInteropStateCallbackRepository r9) {
        this.callbackMap = r1;
        this.requestListener = r2;
        this.cameraConfig = r3;
        this.cameraQuirks = r4;
        this.zslControl = r5;
        this.templateParamsOverride = r6;
        this.cameraMetadata = r7;
        this.cameraXConfig = r8;
        this.cameraInteropStateCallbackRepository = r9;
        this.closeCameraOnCameraGraphClose = new CloseCameraOnCameraGraphClose();
        DynamicRangeProfiles r32 = null;
        if (Build.VERSION.SDK_INT < 33) goto L8;
        if (r7 == null) goto L8;
        DynamicRangeProfilesCompat r12 = DynamicRangeProfilesCompat.Companion.fromCameraMetaData(r7);
        if (r12 == null) goto L8;
        r32 = r12.toDynamicRangeProfiles();
    L8:
        this.supportedDynamicRangeProfiles = r32;
    }

    public /* synthetic */ CameraGraphConfigProvider(CameraCallbackMap r2, ComboRequestListener r3, CameraConfig r4, CameraQuirks r5, ZslControl r6, TemplateParamsOverride r7, CameraMetadata r8, CameraXConfig r9, CameraInteropStateCallbackRepository r10, int r11, DefaultConstructorMarker r12) {
        if ((r11 & 128) == 0) goto L6;
        r9 = null;
    L6:
        if ((r11 & 256) == 0) goto L8;
        r10 = null;
    L8:
        this(r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: create-79VDu0o$default, reason: not valid java name */
    public static /* synthetic */ CameraGraphCreationResult m53create79VDu0o$default(CameraGraphConfigProvider r1, int r2, SessionConfig r3, boolean r4, GraphStateToCameraStateAdapter r5, Integer r6, Map r7, Map r8, int r9, Object r10) {
        if ((r9 & 8) == 0) goto L6;
        r5 = null;
    L6:
        if ((r9 & 16) == 0) goto L9;
        r6 = null;
    L9:
        if ((r9 & 32) == 0) goto L12;
        r7 = MapsKt.emptyMap();
    L12:
        if ((r9 & 64) == 0) goto L15;
        r8 = MapsKt.emptyMap();
    L15:
        return r1.m57create79VDu0o(r2, r3, r4, r5, r6, r7, r8);
    }

    private final CameraGraph.Flags createCameraGraphFlags(CameraQuirks r13, boolean r14) {
        if (r13.getQuirks().contains(CaptureSessionStuckQuirk.class) == false) goto L7;
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L7;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CameraPipe should be enabling CaptureSessionStuckQuirk by default");
    L7:
        int r6 = FinalizeSessionOnCloseQuirk.Companion.m30getBehaviorBm6Tfm4();
        boolean r8 = this.closeCameraOnCameraGraphClose.shouldCloseCameraDevice(r14);
        boolean r12 = false;
        if (r14 == false) goto L13;
        if (DeviceQuirks.INSTANCE.get(DisableAbortCapturesOnStopWithSessionProcessorQuirk.class) == null) goto L13;
    L11:
        boolean r3 = r12;
        boolean r122 = r13.getQuirks().contains(QuickSuccessiveImageCaptureFailsRepeatingRequestQuirk.class);
        CameraGraph.RepeatingRequestRequirementsBeforeCapture.CompletionBehavior r132 = CameraGraph.RepeatingRequestRequirementsBeforeCapture.CompletionBehavior.AT_LEAST;
        return new CameraGraph.Flags(false, r3, new CameraGraph.RepeatingRequestRequirementsBeforeCapture(r122 ? 1 : 0, r132, null), null, r6, true, r8, true, 9, null);
    L13:
        if (DeviceQuirks.INSTANCE.get(DisableAbortCapturesOnStopQuirk.class) != null) goto L11;
        if (Build.VERSION.SDK_INT < 30) goto L11;
        r12 = true;
        goto L11
    }

    private final CameraStream.Config createPostviewStream(SessionConfig.OutputConfig r14, String r15) {
        DeferrableSurface r13 = r14.getSurface();
        if (r15 != null) goto L5;
        r15 = r14.getPhysicalCameraId();
    L5:
        int r142 = r14.getMirrorMode();
        OutputStream.Config.Companion r0 = OutputStream.Config.Companion;
        Size r1 = r13.getPrescribedSize();
        int r2 = StreamFormat.m390constructorimpl(r13.getPrescribedStreamFormat());
        if (r15 != null) goto L8;
        String r3 = null;
    L10:
        if (r142 == 0) goto L15;
        if (r142 == 1) goto L13;
        OutputStream.MirrorMode r5 = null;
    L17:
        return CameraStream.Config.Companion.create$default(CameraStream.Config.Companion, OutputStream.Config.Companion.m323createvBYXiEU$default(r0, r1, r2, r3, null, r5, null, null, null, null, null, 1000, null), null, 2, null);
    L13:
        OutputStream.MirrorMode r143 = OutputStream.MirrorMode.m333boximpl(OutputStream.MirrorMode.m334constructorimpl(2));
    L14:
        r5 = r143;
        goto L17
    L15:
        r143 = OutputStream.MirrorMode.m333boximpl(OutputStream.MirrorMode.m334constructorimpl(1));
        goto L14
    L8:
        r3 = CameraId.m228constructorimpl(r15);
        goto L10
    }

    /* renamed from: getStreamUseCase-MhLBY4I, reason: not valid java name */
    private final OutputStream.StreamUseCase m54getStreamUseCaseMhLBY4I(DeferrableSurface r3, Map<DeferrableSurface, Long> r4, CameraMetadata r5) {
        Long r2 = r4.get(r3);
        if (r2 == null) goto L5;
        OutputStream.StreamUseCase r22 = OutputStream.StreamUseCase.m341boximpl(OutputStream.StreamUseCase.m342constructorimpl(r2.longValue()));
    L7:
        if (Build.VERSION.SDK_INT < 33) goto L15;
        if (r22 == null) goto L15;
        if (r5 == null) goto L15;
        long[] r52 = (long[]) r5.get(CameraGraphConfigProvider$$ExternalSyntheticApiModelOutline0.m());
        if (r52 == null) goto L15;
        if (ArraysKt.contains(r52, r22.m347unboximpl()) != true) goto L15;
        return r22;
    L15:
        Camera2Logger r53 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L18;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Expected stream use case for " + r3 + ", " + r22 + " cannot be set!");
    L18:
        return null;
    L5:
        r22 = null;
        goto L7
    }

    /* renamed from: getStreamUseHint-kVKJKLA, reason: not valid java name */
    private final OutputStream.StreamUseHint m55getStreamUseHintkVKJKLA(DeferrableSurface r1, Map<DeferrableSurface, Long> r2) {
        Long r0 = r2.get(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return OutputStream.StreamUseHint.m351boximpl(OutputStream.StreamUseHint.m352constructorimpl(r0.longValue()));
    }

    private final Integer getVideoStabilizationModeFromCaptureConfig(CaptureConfig r3) {
        int r2 = r3.getPreviewStabilizationMode();
        int r32 = r3.getVideoStabilizationMode();
        if (r2 == 1) goto L16;
        if (r32 == 1) goto L16;
        if (r2 == 2) goto L9;
        if (r32 == 2) goto L12;
        return null;
    L12:
        return 1;
    L9:
        return 2;
    L16:
        return 0;
    }

    private final Camera2ImplConfig toCamera2ImplConfig(SessionConfig r1) {
        return new Camera2ImplConfig(r1.getImplementationOptions());
    }

    /* renamed from: toDynamicRangeProfile--zsJmt4, reason: not valid java name */
    private final OutputStream.DynamicRangeProfile m56toDynamicRangeProfilezsJmt4(DynamicRange r4) {
        if (Build.VERSION.SDK_INT < 33) goto L14;
        OutputStream.DynamicRangeProfile r0 = OutputStream.DynamicRangeProfile.m325boximpl(OutputStream.DynamicRangeProfile.Companion.m332getSTANDARDfFAQAUE());
        DynamicRangeProfiles r3 = this.supportedDynamicRangeProfiles;
        if (r3 == null) goto L13;
        Long r32 = DynamicRangeConversions.INSTANCE.dynamicRangeToFirstSupportedProfile(r4, r3);
        if (r32 != null) goto L9;
        Camera2Logger r33 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L13;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Requested dynamic range is not supported. Defaulting to STANDARD dynamic range profile.\nRequested dynamic range:\n " + r4);
        goto L13
    L9:
        return OutputStream.DynamicRangeProfile.m325boximpl(OutputStream.DynamicRangeProfile.m326constructorimpl(r32.longValue()));
    L13:
        return r0;
    L14:
        return null;
    }

    /* renamed from: create-79VDu0o, reason: not valid java name */
    public final CameraGraphCreationResult m57create79VDu0o(int r35, SessionConfig r36, boolean r37, GraphStateToCameraStateAdapter r38, Integer r39, Map<DeferrableSurface, Long> r40, Map<DeferrableSurface, Long> r41) {
        CameraGraph.OperatingMode.Companion r2 = CameraGraph.OperatingMode.Companion;
        boolean r3 = CameraGraph.OperatingMode.m216equalsimpl0(r35, r2.m220getEXTENSION2uNL3no());
        LinkedHashMap r4 = new LinkedHashMap();
        ArrayList r5 = new ArrayList();
        int r6 = 1;
        int r9 = RequestTemplate.m371constructorimpl(1);
        LinkedHashMap r7 = new LinkedHashMap();
        LinkedHashMap r10 = new LinkedHashMap();
        if (r36 == null) goto L71;
        CameraInteropStateCallbackRepository r13 = this.cameraInteropStateCallbackRepository;
        if (r13 == null) goto L8;
        r13.updateCallbacks(r36);
    L8:
        if (r36.getTemplateType() == (-1)) goto L10;
        r9 = RequestTemplate.m371constructorimpl(r36.getTemplateType());
    L10:
        r7.putAll(this.templateParamsOverride.mo35getOverrideParamsxlOpshk(RequestTemplate.m370boximpl(r9)));
        r7.putAll(Camera2ImplConfigKt.toParameters(r36.getImplementationOptions()));
        if (CameraGraph.OperatingMode.m216equalsimpl0(r35, r2.m220getEXTENSION2uNL3no()) == false) goto L13;
        r7.put(CameraPipeKeys.INSTANCE.getCamera2ExtensionMode(), r39);
    L13:
        String r22 = toCamera2ImplConfig(r36).getPhysicalCameraId(null);
        Iterator<SessionConfig.OutputConfig> r132 = r36.getOutputConfigs().iterator();
        CameraStream.Config r15 = null;
    L15:
        if (r132.hasNext() == false) goto L66;
        SessionConfig.OutputConfig r16 = r132.next();
        DeferrableSurface r14 = r16.getSurface();
        if (r22 != null) goto L20;
        String r18 = r16.getPhysicalCameraId();
    L19:
        int r19 = 2;
        DynamicRange r11 = r16.getDynamicRange();
        int r12 = r16.getMirrorMode();
        OutputStream.Config.Companion r21 = OutputStream.Config.Companion;
        OutputStream.DynamicRangeProfile r28 = m56toDynamicRangeProfilezsJmt4(r11);
        Size r222 = r14.getPrescribedSize();
        int r23 = StreamFormat.m390constructorimpl(r14.getPrescribedStreamFormat());
        if (r18 != null) goto L24;
        String r24 = null;
    L25:
        if (r12 == 0) goto L30;
        if (r12 == r6) goto L28;
        OutputStream.MirrorMode r26 = null;
    L31:
        if (r37 == false) goto L43;
        Class<?> r112 = r16.getSurface().getContainerClass();
        if (Intrinsics.areEqual(r112, MediaCodec.class) == false) goto L37;
        OutputStream.OutputType r113 = OutputStream.OutputType.Companion.getMEDIA_CODEC();
    L35:
        OutputStream.OutputType r25 = r113;
        if (r3 == true) goto L46;
        OutputStream.StreamUseCase r29 = m54getStreamUseCaseMhLBY4I(r14, r40, this.cameraMetadata);
    L47:
        if (r3 == true) goto L49;
        OutputStream.StreamUseHint r30 = m55getStreamUseHintkVKJKLA(r14, r41);
    L50:
        OutputStream.Config r62 = OutputStream.Config.Companion.m323createvBYXiEU$default(r21, r222, r23, r24, r25, r26, null, r28, r29, r30, null, 544, null);
        String r392 = r22;
        Iterator r27 = CollectionsKt.plus(r16.getSharedSurfaces(), r14).iterator();
    L52:
        if (r27.hasNext() == false) goto L65;
        Iterator r223 = r27;
        DeferrableSurface r210 = (DeferrableSurface) r27.next();
        int r212 = r9;
        CameraStream.Config r8 = CameraStream.Config.Companion.create$default(CameraStream.Config.Companion, r62, null, r19, null);
        r10.put(r8, r210);
        if (r16.getSurfaceGroupId() == (-1)) goto L60;
        List r92 = (List) r4.get(Integer.valueOf(r16.getSurfaceGroupId()));
        if (r92 != null) goto L58;
        r4.put(Integer.valueOf(r16.getSurfaceGroupId()), CollectionsKt.mutableListOf(new CameraStream.Config[]{r8}));
        goto L60
    L58:
        r92.add(r8);
    L60:
        if (Intrinsics.areEqual(r210, r14) == false) goto L64;
        if (this.zslControl.isZslSurface(r210, r36) == false) goto L64;
        r15 = r8;
        r9 = r212;
        r27 = r223;
        r19 = 2;
    L64:
        r9 = r212;
        r27 = r223;
        r19 = 2;
        goto L52
    L65:
        r22 = r392;
        r6 = 1;
        goto L15
    L49:
        r30 = null;
        goto L50
    L46:
        r29 = null;
        goto L47
    L37:
        if (Intrinsics.areEqual(r112, SurfaceHolder.class) == false) goto L40;
        r113 = OutputStream.OutputType.Companion.getSURFACE_VIEW();
        goto L35
    L40:
        if (Intrinsics.areEqual(r112, SurfaceTexture.class) == false) goto L42;
        r113 = OutputStream.OutputType.Companion.getSURFACE_TEXTURE();
        goto L35
    L42:
        r113 = OutputStream.OutputType.Companion.getSURFACE();
        goto L35
    L43:
        r113 = OutputStream.OutputType.Companion.getSURFACE();
        goto L35
    L28:
        OutputStream.MirrorMode r114 = OutputStream.MirrorMode.m333boximpl(OutputStream.MirrorMode.m334constructorimpl(2));
    L29:
        r26 = r114;
        goto L31
    L30:
        r114 = OutputStream.MirrorMode.m333boximpl(OutputStream.MirrorMode.m334constructorimpl(r6));
        goto L29
    L24:
        r24 = CameraId.m228constructorimpl(r18);
        goto L25
    L20:
        r18 = r22;
        goto L19
    L66:
        int r213 = r9;
        if (r36.getInputConfiguration() == null) goto L70;
        if (r15 == null) goto L70;
        r5.add(new InputStream.Config(r15, 1, ((OutputStream.Config) CollectionsKt.single(r15.getOutputs())).m318getFormat8FPWQzE(), null));
    L70:
        int r63 = r213;
    L72:
        CameraGraph.Flags r17 = createCameraGraphFlags(this.cameraQuirks, r3);
        if (r36 == null) goto L75;
        Integer r115 = getVideoStabilizationModeFromCaptureConfig(r36.getRepeatingCaptureConfig());
    L76:
        if (r36 == null) goto L78;
        Range<Integer> r211 = r36.getExpectedFrameRateRange();
    L80:
        if (Intrinsics.areEqual(r211, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == false) goto L83;
        r211 = null;
    L83:
        Map r82 = MapsKt.createMapBuilder();
        if (r3 == false) goto L86;
        r82.put(CameraPipeKeys.INSTANCE.getIgnore3ARequiredParameters(), Boolean.TRUE);
    L86:
        if (r115 == null) goto L88;
        r82.put(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(r115.intValue()));
    L88:
        r82.put(CameraPipeKeys.INSTANCE.getCamera2CaptureRequestTag(), "android.hardware.camera2.CaptureRequest.setTag.CX");
        if (r211 == null) goto L91;
        r82.put(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, r211);
    L91:
        Map r32 = MapsKt.build(r82);
        if (r211 == null) goto L94;
        r7.put(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, r211);
    L94:
        if (r115 == null) goto L96;
        r7.put(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, r115);
    L96:
        if (r36 == null) goto L104;
        ArrayList r116 = null;
        String r214 = toCamera2ImplConfig(r36).getPhysicalCameraId(null);
        SessionConfig.OutputConfig r1 = r36.getPostviewOutputConfig();
        if (r1 == null) goto L102;
        CameraStream.Config r215 = createPostviewStream(r1, r214);
        if (r215 == null) goto L102;
        r10.put(r215, r1.getSurface());
    L103:
        ArrayList r110 = r5;
        CameraStream.Config r52 = r215;
    L105:
        CameraXConfig r216 = this.cameraXConfig;
        if (r216 == null) goto L108;
        Camera2CaptureRequestConfiguratorKt.getCamera2CaptureRequestConfigurator(r216);
    L108:
        String r217 = this.cameraConfig.m37getCameraIdDz_R5H8();
        List r83 = CollectionsKt.toList(r10.keySet());
        List r42 = CollectionsKt.toList(r4.values());
        if (r110.isEmpty() == true) goto L113;
        r116 = r110;
    L113:
        return new CameraGraphCreationResult(new CameraGraph.Config(r217, r83, r42, r116, r52, r63, r7, r35, 0, r32, CollectionsKt.listOf(new Request.Listener[]{this.callbackMap, this.requestListener}), CollectionsKt.listOfNotNull(r38), null, null, null, null, r17, null, 192768, null), MapsKt.toMap(r10));
    L102:
        r215 = null;
        goto L103
    L104:
        r116 = null;
        r110 = r5;
        r52 = null;
        goto L105
    L78:
        r211 = null;
        goto L80
    L75:
        r115 = null;
        goto L76
    L71:
        r63 = r9;
        goto L72
    }

    public String toString() {
        return "CameraGraphConfigProvider<" + CameraId.m232toStringimpl(this.cameraConfig.m37getCameraIdDz_R5H8()) + Typography.greater;
    }
}
