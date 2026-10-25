package androidx.camera.core.internal;

import android.graphics.Rect;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceStreamSpecQueryResult;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.UseCaseUtil;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.core.streamsharing.StreamSharing;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016Jb\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016JJ\u0010\u001c\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001f0\u001e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u001e0\u001d2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J^\u0010!\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u001e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020$0\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/camera/core/internal/StreamSpecsCalculatorImpl;", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "useCaseConfigFactory", "Landroidx/camera/core/impl/UseCaseConfigFactory;", "cameraDeviceSurfaceManager", "Landroidx/camera/core/impl/CameraDeviceSurfaceManager;", "<init>", "(Landroidx/camera/core/impl/UseCaseConfigFactory;Landroidx/camera/core/impl/CameraDeviceSurfaceManager;)V", "setCameraDeviceSurfaceManager", _UrlKt.FRAGMENT_ENCODE_SET, "calculateSuggestedStreamSpecs", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "cameraMode", _UrlKt.FRAGMENT_ENCODE_SET, "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "newUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "attachedUseCases", "cameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "sessionType", "targetFrameRate", "Landroid/util/Range;", "isFeatureComboInvocation", _UrlKt.FRAGMENT_ENCODE_SET, "findMaxSupportedFrameRate", "calculateSuggestedStreamSpecsForAttachedUseCases", "Landroid/util/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/StreamSpec;", "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "calculateSuggestedStreamSpecsForNewUseCases", "attachedSurfaceInfoToUseCaseMap", "configPairMap", "Landroidx/camera/core/internal/CameraUseCaseAdapter$ConfigPair;", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StreamSpecsCalculatorImpl implements StreamSpecsCalculator {
    private static final Companion Companion = null;
    private CameraDeviceSurfaceManager cameraDeviceSurfaceManager;
    private final UseCaseConfigFactory useCaseConfigFactory;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/internal/StreamSpecsCalculatorImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$EiSDIaV-oOjJPVqGwZrvYw4fiRY, reason: not valid java name */
    public static UseCaseConfig m604$r8$lambda$EiSDIaVoOjJPVqGwZrvYw4fiRY(Map r1, CameraInfoInternal r2, UseCase r3) {
        Object r12 = r1.get(r3);
        if (r12 == null) goto L6;
        CameraUseCaseAdapter.ConfigPair r13 = (CameraUseCaseAdapter.ConfigPair) r12;
        return r3.mergeConfigs(r2, r13.mExtendedConfig, r13.mCameraConfig);
    L6:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    }

    static {
        Companion = new Companion(null);
    }

    public StreamSpecsCalculatorImpl(UseCaseConfigFactory r1, CameraDeviceSurfaceManager r2) {
        this.useCaseConfigFactory = r1;
        this.cameraDeviceSurfaceManager = r2;
    }

    private final Pair<Map<UseCase, StreamSpec>, Map<AttachedSurfaceInfo, UseCase>> calculateSuggestedStreamSpecsForAttachedUseCases(int r26, CameraInfoInternal r27, List<? extends UseCase> r28) {
        ArrayList r0 = new ArrayList();
        String r3 = r27.getCameraId();
        LinkedHashMap r7 = new LinkedHashMap();
        LinkedHashMap r8 = new LinkedHashMap();
        Iterator<? extends UseCase> r9 = r28.iterator();
    L4:
        if (r9.hasNext() == false) goto L23;
        UseCase r10 = r9.next();
        StreamSpec r11 = r10.getAttachedStreamSpec();
        if (r11 == null) goto L20;
        CameraDeviceSurfaceManager r1 = this.cameraDeviceSurfaceManager;
        if (r1 == null) goto L18;
        int r4 = r10.getImageFormat();
        Size r5 = r10.getAttachedSurfaceResolution();
        if (r5 == null) goto L16;
        SurfaceConfig r15 = r1.transformSurfaceConfig(r26, r3, r4, r5, r10.getCurrentConfig().getStreamUseCase());
        int r16 = r10.getImageFormat();
        Size r17 = r10.getAttachedSurfaceResolution();
        DynamicRange r18 = r11.getDynamicRange();
        List<UseCaseConfigFactory.CaptureType> r19 = StreamSharing.getCaptureTypes(r10);
        Config r20 = r11.getImplementationOptions();
        int r21 = r10.getCurrentConfig().getSessionType(0);
        Range<Integer> r22 = r10.getCurrentConfig().getTargetFrameRate(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED);
        if (r22 == null) goto L14;
        AttachedSurfaceInfo r12 = AttachedSurfaceInfo.create(r15, r16, r17, r18, r19, r20, r21, r22, r10.getCurrentConfig().isStrictFrameRateRequired(), r10.getCurrentConfig().getCustomMaxFrameRate(r10.getAttachedSurfaceResolution()));
        r0.add(r12);
        r8.put(r12, r10);
        r7.put(r10, r11);
        goto L4
    L14:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Attached surface resolution cannot be null for already attached use cases.");
        return null;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L20:
        f$$ExternalSyntheticBUOutline1.m("Attached stream spec cannot be null for already attached use cases.");
        return null;
    L23:
        return new Pair(r7, r8);
    }

    private final StreamSpecQueryResult calculateSuggestedStreamSpecsForNewUseCases(int r16, CameraInfoInternal r17, List<? extends UseCase> r18, Map<AttachedSurfaceInfo, ? extends UseCase> r19, Map<UseCase, ? extends CameraUseCaseAdapter.ConfigPair> r20, boolean r21, boolean r22) {
        String r4 = r17.getCameraId();
        LinkedHashMap r11 = new LinkedHashMap();
        if (r18.isEmpty() == true) goto L42;
        LinkedHashMap r12 = new LinkedHashMap();
        LinkedHashMap r6 = new LinkedHashMap();
        Rect r3 = r17.getSensorRect();     // Catch: NullPointerException -> L7
    L9:
        if (r3 == null) goto L11;
        Size r32 = TransformUtils.rectToSize(r3);
    L12:
        SupportedOutputSizesSorter r5 = new SupportedOutputSizesSorter(r17, r32);
        Iterator<? extends UseCase> r33 = r18.iterator();
    L14:
        if (r33.hasNext() == false) goto L20;
        UseCase r7 = r33.next();
        CameraUseCaseAdapter.ConfigPair r8 = r20.get(r7);
        if (r8 == null) goto L18;
        CameraUseCaseAdapter.ConfigPair r82 = r8;
        UseCaseConfig<?> r83 = r7.mergeConfigs(r17, r82.mExtendedConfig, r82.mCameraConfig);
        r12.put(r83, r7);
        r6.put(r83, r5.getSortedSupportedOutputSizes(r83));
        goto L14
    L18:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L20:
        List<? extends UseCase> r34 = r18;
        VideoStabilization r72 = UseCaseUtil.getVideoStabilization(r34, new StreamSpecsCalculatorImpl$$ExternalSyntheticLambda0(r20, r17));
        CameraDeviceSurfaceManager r2 = this.cameraDeviceSurfaceManager;
        if (r2 == null) goto L40;
        SurfaceStreamSpecQueryResult r15 = r2.getSuggestedStreamSpecs(r16, r4, new ArrayList(r19.keySet()), r6, r72, UseCaseUtil.containsVideoCapture(r34), r21, r22);
        Map<UseCaseConfig<?>, StreamSpec> r0 = r15.component1();
        Map<AttachedSurfaceInfo, StreamSpec> r23 = r15.component2();
        int r152 = r15.component3();
        Iterator r35 = r12.entrySet().iterator();
    L24:
        if (r35.hasNext() == false) goto L30;
        Map.Entry r42 = (Map.Entry) r35.next();
        Object r52 = r42.getValue();
        StreamSpec r43 = r0.get(r42.getKey());
        if (r43 == null) goto L28;
        r11.put(r52, r43);
        goto L24
    L28:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L30:
        Iterator<Map.Entry<AttachedSurfaceInfo, StreamSpec>> r02 = r23.entrySet().iterator();
    L32:
        if (r02.hasNext() == false) goto L44;
        Map.Entry<AttachedSurfaceInfo, StreamSpec> r24 = r02.next();
        if (r19.containsKey(r24.getKey()) == false) goto L32;
        UseCase r36 = r19.get(r24.getKey());
        if (r36 == null) goto L38;
        r11.put(r36, r24.getValue());
        goto L32
    L38:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L44:
        return new StreamSpecQueryResult(r11, r152);
    L40:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L11:
        r32 = null;
    L7:
        r3 = null;
        goto L9
    L42:
        r152 = Integer.MAX_VALUE;
        goto L44
    }

    @Override // androidx.camera.core.internal.StreamSpecsCalculator
    public StreamSpecQueryResult calculateSuggestedStreamSpecs(int r10, CameraInfoInternal r11, List<? extends UseCase> r12, List<? extends UseCase> r13, CameraConfig r14, int r15, Range<Integer> r16, boolean r17, boolean r18) {
        Pair<Map<UseCase, StreamSpec>, Map<AttachedSurfaceInfo, UseCase>> r132 = calculateSuggestedStreamSpecsForAttachedUseCases(r10, r11, r13);
        StreamSpecQueryResult r9 = calculateSuggestedStreamSpecsForNewUseCases(r10, r11, r12, (Map) r132.second, CameraUseCaseAdapter.getConfigs(r12, r14.getUseCaseConfigFactory(), this.useCaseConfigFactory, r15, r16), r17, r18);
        return new StreamSpecQueryResult(MapsKt.plus((Map) r132.first, r9.getStreamSpecs()), r9.getMaxSupportedFrameRate());
    }

    @Override // androidx.camera.core.internal.StreamSpecsCalculator
    public void setCameraDeviceSurfaceManager(CameraDeviceSurfaceManager r1) {
        this.cameraDeviceSurfaceManager = r1;
    }
}
