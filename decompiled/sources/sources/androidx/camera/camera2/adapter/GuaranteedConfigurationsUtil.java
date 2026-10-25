package androidx.camera.camera2.adapter;

import android.os.Build;
import android.util.Size;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.impl.StreamUseCase;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceSizeDefinition;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J&\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0007J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0007J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)H\u0002J#\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0000¢\u0006\u0002\b0R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\b¨\u00061"}, d2 = {"Landroidx/camera/camera2/adapter/GuaranteedConfigurationsUtil;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "QUERYABLE_VIC_FCQ_COMBINATIONS", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SurfaceCombination;", "getQUERYABLE_VIC_FCQ_COMBINATIONS", "()Ljava/util/List;", "QUERYABLE_VIC_FCQ_COMBINATIONS$delegate", "Lkotlin/Lazy;", "QUERYABLE_BAKLAVA_FCQ_COMBINATIONS", "getQUERYABLE_BAKLAVA_FCQ_COMBINATIONS", "QUERYABLE_BAKLAVA_FCQ_COMBINATIONS$delegate", "getLegacySupportedCombinationList", "getLimitedSupportedCombinationList", "getFullSupportedCombinationList", "getRAWSupportedCombinationList", "getBurstSupportedCombinationList", "getLevel3SupportedCombinationList", "getUltraHighResolutionSupportedCombinationList", "getUltraHdrSupportedCombinationList", "getConcurrentSupportedCombinationList", "generateSupportedCombinationList", "hardwareLevel", _UrlKt.FRAGMENT_ENCODE_SET, "isRawSupported", _UrlKt.FRAGMENT_ENCODE_SET, "isBurstCaptureSupported", "get10BitSupportedCombinationList", "getStreamUseCaseSupportedCombinationList", "getPreviewStabilizationSupportedCombinationList", "generateHighSpeedSupportedCombinationList", "maxSupportedSize", "Landroid/util/Size;", "surfaceSizeDefinition", "Landroidx/camera/core/impl/SurfaceSizeDefinition;", "generateVicQueryableFcqCombinations", "generateBaklavaQueryableFcqCombinations", "createPrivJpegXCombinations", "privSize", "Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", "jpegXSize", "getQueryableFcqCombinations", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "videoStabilization", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "getQueryableFcqCombinations$camera_camera2", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGuaranteedConfigurationsUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GuaranteedConfigurationsUtil.kt\nandroidx/camera/camera2/adapter/GuaranteedConfigurationsUtil\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1120:1\n1#2:1121\n*E\n"})
/* loaded from: classes3.dex */
public final class GuaranteedConfigurationsUtil {
    public static final GuaranteedConfigurationsUtil INSTANCE = null;
    private static final Lazy QUERYABLE_BAKLAVA_FCQ_COMBINATIONS$delegate = null;
    private static final Lazy QUERYABLE_VIC_FCQ_COMBINATIONS$delegate = null;

    /* renamed from: $r8$lambda$Y8-NjHh-iVV6h-z3yrfA8qrdcx4, reason: not valid java name */
    public static List m19$r8$lambda$Y8NjHhiVV6hz3yrfA8qrdcx4() {
        return INSTANCE.generateBaklavaQueryableFcqCombinations();
    }

    public static List $r8$lambda$aXIGl4MOIgYKcgTzwCnBNg4LaSo() {
        return INSTANCE.generateVicQueryableFcqCombinations();
    }

    static {
        INSTANCE = new GuaranteedConfigurationsUtil();
        QUERYABLE_VIC_FCQ_COMBINATIONS$delegate = LazyKt.lazy(new GuaranteedConfigurationsUtil$$ExternalSyntheticLambda1());
        QUERYABLE_BAKLAVA_FCQ_COMBINATIONS$delegate = LazyKt.lazy(new GuaranteedConfigurationsUtil$$ExternalSyntheticLambda2());
    }

    private GuaranteedConfigurationsUtil() {
    }

    private final List<SurfaceCombination> createPrivJpegXCombinations(SurfaceConfig.ConfigSize r10, SurfaceConfig.ConfigSize r11) {
        ArrayList r9 = new ArrayList();
        SurfaceCombination r0 = new SurfaceCombination();
        SurfaceConfig.Companion r1 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r2 = SurfaceConfig.ConfigType.PRIV;
        r0.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r10, null, 4, null));
        r0.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, SurfaceConfig.ConfigType.JPEG, r11, null, 4, null));
        r9.add(r0);
        SurfaceCombination r102 = new SurfaceCombination();
        r102.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r10, null, 4, null));
        r102.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, SurfaceConfig.ConfigType.JPEG_R, r11, null, 4, null));
        r9.add(r102);
        return r9;
    }

    private final List<SurfaceCombination> generateBaklavaQueryableFcqCombinations() {
        ArrayList r8 = new ArrayList();
        SurfaceCombination r0 = new SurfaceCombination();
        SurfaceConfig.Companion r1 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r2 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r3 = SurfaceConfig.ConfigSize.S1080P_16_9;
        r0.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r3, null, 4, null));
        r0.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r3, null, 4, null));
        r8.add(r0);
        SurfaceCombination r02 = new SurfaceCombination();
        r02.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r3, null, 4, null));
        r02.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, SurfaceConfig.ConfigSize.S1440P_16_9, null, 4, null));
        r8.add(r02);
        SurfaceCombination r03 = new SurfaceCombination();
        r03.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r3, null, 4, null));
        r03.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, SurfaceConfig.ConfigSize.UHD, null, 4, null));
        r8.add(r03);
        SurfaceCombination r04 = new SurfaceCombination();
        r04.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r3, null, 4, null));
        r04.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, SurfaceConfig.ConfigType.YUV, r3, null, 4, null));
        r04.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r3, null, 4, null));
        r8.add(r04);
        return r8;
    }

    @JvmStatic
    public static final List<SurfaceCombination> generateHighSpeedSupportedCombinationList(Size r10, SurfaceSizeDefinition r11) {
        ArrayList r0 = new ArrayList();
        SurfaceConfig r102 = SurfaceConfig.Companion.transformSurfaceConfig$default(SurfaceConfig.Companion, 34, r10, r11, 0, null, null, 56, null);
        SurfaceCombination r112 = new SurfaceCombination();
        r112.addSurfaceConfig(r102);
        r0.add(r112);
        SurfaceCombination r113 = new SurfaceCombination();
        r113.addSurfaceConfig(r102);
        r113.addSurfaceConfig(r102);
        r0.add(r113);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> generateSupportedCombinationList(int r4, boolean r5, boolean r6) {
        ArrayList r0 = new ArrayList();
        r0.addAll(getLegacySupportedCombinationList());
        if (r4 == 0) goto L9;
        if (r4 == 1) goto L9;
        if (r4 == 3) goto L9;
        if (r4 == 4) goto L9;
    L10:
        if (r4 == 1) goto L13;
        if (r4 == 3) goto L13;
    L14:
        if (r5 == false) goto L16;
        r0.addAll(getRAWSupportedCombinationList());
    L16:
        if (r6 == false) goto L19;
        if (r4 != 0) goto L19;
        r0.addAll(getBurstSupportedCombinationList());
    L19:
        if (r4 != 3) goto L21;
        r0.addAll(getLevel3SupportedCombinationList());
    L21:
        return r0;
    L13:
        r0.addAll(getFullSupportedCombinationList());
    L9:
        r0.addAll(getLimitedSupportedCombinationList());
        goto L10
    }

    private final List<SurfaceCombination> generateVicQueryableFcqCombinations() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.S1080P_16_9;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.S720P_16_9;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r0.add(r12);
        SurfaceConfig.ConfigSize r13 = SurfaceConfig.ConfigSize.MAXIMUM_16_9;
        r0.addAll(createPrivJpegXCombinations(r4, r13));
        SurfaceConfig.ConfigSize r22 = SurfaceConfig.ConfigSize.UHD;
        r0.addAll(createPrivJpegXCombinations(r4, r22));
        r0.addAll(createPrivJpegXCombinations(r4, SurfaceConfig.ConfigSize.S1440P_16_9));
        r0.addAll(createPrivJpegXCombinations(r4, r4));
        r0.addAll(createPrivJpegXCombinations(r42, r13));
        r0.addAll(createPrivJpegXCombinations(r42, r22));
        r0.addAll(createPrivJpegXCombinations(r42, r4));
        SurfaceConfig.ConfigSize r14 = SurfaceConfig.ConfigSize.X_VGA;
        SurfaceConfig.ConfigSize r23 = SurfaceConfig.ConfigSize.MAXIMUM_4_3;
        r0.addAll(createPrivJpegXCombinations(r14, r23));
        r0.addAll(createPrivJpegXCombinations(SurfaceConfig.ConfigSize.S1080P_4_3, r23));
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> get10BitSupportedCombinationList() {
        SurfaceCombination r0 = new SurfaceCombination();
        SurfaceConfig.Companion r1 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r2 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r3 = SurfaceConfig.ConfigSize.MAXIMUM;
        r0.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r3, null, 4, null));
        Unit r22 = Unit.INSTANCE;
        SurfaceCombination r8 = new SurfaceCombination();
        SurfaceConfig.ConfigType r23 = SurfaceConfig.ConfigType.YUV;
        r8.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r23, r3, null, 4, null));
        SurfaceCombination r11 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r32 = SurfaceConfig.ConfigSize.PREVIEW;
        r11.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r32, null, 4, null));
        SurfaceConfig.ConfigType r24 = SurfaceConfig.ConfigType.JPEG;
        r11.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r24, r3, null, 4, null));
        SurfaceCombination r14 = new SurfaceCombination();
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r32, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r23, r3, null, 4, null));
        SurfaceCombination r10 = new SurfaceCombination();
        r10.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r23, r32, null, 4, null));
        r10.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r23, r3, null, 4, null));
        SurfaceCombination r15 = new SurfaceCombination();
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r32, null, 4, null));
        SurfaceConfig.ConfigSize r33 = SurfaceConfig.ConfigSize.RECORD;
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r33, null, 4, null));
        SurfaceCombination r6 = new SurfaceCombination();
        r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r32, null, 4, null));
        r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r33, null, 4, null));
        r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r23, r33, null, 4, null));
        SurfaceCombination r7 = new SurfaceCombination();
        r7.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r32, null, 4, null));
        r7.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r2, r33, null, 4, null));
        r7.addSurfaceConfig(SurfaceConfig.Companion.create$default(r1, r24, r33, null, 4, null));
        return CollectionsKt.listOf(new SurfaceCombination[]{r0, r8, r11, r14, r10, r15, r6, r7});
    }

    @JvmStatic
    public static final List<SurfaceCombination> getBurstSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.PREVIEW;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.MAXIMUM;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.YUV;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r13);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getConcurrentSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.YUV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.S1440P_4_3;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.PRIV;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        SurfaceConfig.ConfigType r33 = SurfaceConfig.ConfigType.JPEG;
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r0.add(r13);
        SurfaceCombination r14 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.S720P_16_9;
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r0.add(r14);
        SurfaceCombination r15 = new SurfaceCombination();
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r0.add(r15);
        SurfaceCombination r16 = new SurfaceCombination();
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r16);
        SurfaceCombination r17 = new SurfaceCombination();
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r17);
        SurfaceCombination r18 = new SurfaceCombination();
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r18);
        SurfaceCombination r19 = new SurfaceCombination();
        r19.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r19.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r19);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getFullSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.PREVIEW;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.MAXIMUM;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.YUV;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r13);
        SurfaceCombination r14 = new SurfaceCombination();
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, SurfaceConfig.ConfigType.JPEG, r42, null, 4, null));
        r0.add(r14);
        SurfaceCombination r15 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r43 = SurfaceConfig.ConfigSize.VGA;
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r15);
        SurfaceCombination r16 = new SurfaceCombination();
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r16);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getLegacySupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.MAXIMUM;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.JPEG;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        SurfaceConfig.ConfigType r33 = SurfaceConfig.ConfigType.YUV;
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r0.add(r13);
        SurfaceCombination r14 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.PREVIEW;
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r14);
        SurfaceCombination r15 = new SurfaceCombination();
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r15);
        SurfaceCombination r16 = new SurfaceCombination();
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r0.add(r16);
        SurfaceCombination r17 = new SurfaceCombination();
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r0.add(r17);
        SurfaceCombination r18 = new SurfaceCombination();
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r18);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getLevel3SupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.PREVIEW;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.VGA;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.YUV;
        SurfaceConfig.ConfigSize r43 = SurfaceConfig.ConfigSize.MAXIMUM;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        SurfaceConfig.ConfigType r33 = SurfaceConfig.ConfigType.RAW;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r43, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, SurfaceConfig.ConfigType.JPEG, r43, null, 4, null));
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r43, null, 4, null));
        r0.add(r12);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getLimitedSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.PREVIEW;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.RECORD;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.YUV;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r13);
        SurfaceCombination r14 = new SurfaceCombination();
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r42, null, 4, null));
        SurfaceConfig.ConfigType r33 = SurfaceConfig.ConfigType.JPEG;
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r0.add(r14);
        SurfaceCombination r15 = new SurfaceCombination();
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r0.add(r15);
        SurfaceCombination r16 = new SurfaceCombination();
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, SurfaceConfig.ConfigSize.MAXIMUM, null, 4, null));
        r0.add(r16);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getPreviewStabilizationSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.S1440P_4_3;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.YUV;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigType r33 = SurfaceConfig.ConfigType.JPEG;
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.MAXIMUM;
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r0.add(r13);
        SurfaceCombination r14 = new SurfaceCombination();
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r0.add(r14);
        SurfaceCombination r15 = new SurfaceCombination();
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r15);
        SurfaceCombination r16 = new SurfaceCombination();
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r0.add(r16);
        SurfaceCombination r17 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r43 = SurfaceConfig.ConfigSize.PREVIEW;
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r43, null, 4, null));
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r17);
        SurfaceCombination r18 = new SurfaceCombination();
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r18);
        SurfaceCombination r19 = new SurfaceCombination();
        r19.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r43, null, 4, null));
        r19.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r19);
        SurfaceCombination r110 = new SurfaceCombination();
        r110.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        r110.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r4, null, 4, null));
        r0.add(r110);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getRAWSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.RAW;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.MAXIMUM;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.PREVIEW;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        SurfaceConfig.ConfigType r33 = SurfaceConfig.ConfigType.YUV;
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r13);
        SurfaceCombination r14 = new SurfaceCombination();
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r14);
        SurfaceCombination r15 = new SurfaceCombination();
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r15);
        SurfaceCombination r16 = new SurfaceCombination();
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r16);
        SurfaceCombination r17 = new SurfaceCombination();
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        SurfaceConfig.ConfigType r34 = SurfaceConfig.ConfigType.JPEG;
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r4, null, 4, null));
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r17);
        SurfaceCombination r18 = new SurfaceCombination();
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r42, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r4, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r18);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getUltraHdrSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.JPEG_R;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.MAXIMUM;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW, null, 4, null));
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r0.add(r12);
        return r0;
    }

    @JvmStatic
    public static final List<SurfaceCombination> getUltraHighResolutionSupportedCombinationList() {
        ArrayList r0 = new ArrayList();
        SurfaceCombination r1 = new SurfaceCombination();
        SurfaceConfig.Companion r2 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r3 = SurfaceConfig.ConfigType.YUV;
        SurfaceConfig.ConfigSize r4 = SurfaceConfig.ConfigSize.ULTRA_MAXIMUM;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        SurfaceConfig.ConfigType r32 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.PREVIEW;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        SurfaceConfig.ConfigSize r43 = SurfaceConfig.ConfigSize.RECORD;
        r1.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        r0.add(r1);
        SurfaceCombination r12 = new SurfaceCombination();
        SurfaceConfig.ConfigType r33 = SurfaceConfig.ConfigType.JPEG;
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r12.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        r0.add(r12);
        SurfaceCombination r13 = new SurfaceCombination();
        SurfaceConfig.ConfigType r34 = SurfaceConfig.ConfigType.RAW;
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r4, null, 4, null));
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r13.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r43, null, 4, null));
        r0.add(r13);
        SurfaceCombination r14 = new SurfaceCombination();
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        SurfaceConfig.ConfigSize r44 = SurfaceConfig.ConfigSize.MAXIMUM;
        r14.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r44, null, 4, null));
        r0.add(r14);
        SurfaceCombination r15 = new SurfaceCombination();
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r15.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r44, null, 4, null));
        r0.add(r15);
        SurfaceCombination r16 = new SurfaceCombination();
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r4, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r16.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r44, null, 4, null));
        r0.add(r16);
        SurfaceCombination r17 = new SurfaceCombination();
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r17.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r44, null, 4, null));
        r0.add(r17);
        SurfaceCombination r18 = new SurfaceCombination();
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r18.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r44, null, 4, null));
        r0.add(r18);
        SurfaceCombination r19 = new SurfaceCombination();
        r19.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r4, null, 4, null));
        r19.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r19.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r44, null, 4, null));
        r0.add(r19);
        SurfaceCombination r110 = new SurfaceCombination();
        r110.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r3, r4, null, 4, null));
        r110.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r110.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r44, null, 4, null));
        r0.add(r110);
        SurfaceCombination r111 = new SurfaceCombination();
        r111.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r33, r4, null, 4, null));
        r111.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r111.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r44, null, 4, null));
        r0.add(r111);
        SurfaceCombination r112 = new SurfaceCombination();
        r112.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r4, null, 4, null));
        r112.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r32, r42, null, 4, null));
        r112.addSurfaceConfig(SurfaceConfig.Companion.create$default(r2, r34, r44, null, 4, null));
        r0.add(r112);
        return r0;
    }

    public final List<SurfaceCombination> getQUERYABLE_BAKLAVA_FCQ_COMBINATIONS() {
        return (List) QUERYABLE_BAKLAVA_FCQ_COMBINATIONS$delegate.getValue();
    }

    public final List<SurfaceCombination> getQUERYABLE_VIC_FCQ_COMBINATIONS() {
        return (List) QUERYABLE_VIC_FCQ_COMBINATIONS$delegate.getValue();
    }

    public final List<SurfaceCombination> getQueryableFcqCombinations$camera_camera2(CameraMetadata r4, VideoStabilization r5) {
        ArrayList r0 = new ArrayList();
        if (Build.VERSION.SDK_INT < 35) goto L19;
        Object r42 = r4.get(GuaranteedConfigurationsUtil$$ExternalSyntheticApiModelOutline0.m());
        if (r42 == null) goto L17;
        int r43 = ((Number) r42).intValue();
        if (r43 < 35) goto L12;
        if (r5 == VideoStabilization.ON) goto L12;
        r0.addAll(getQUERYABLE_VIC_FCQ_COMBINATIONS());
    L12:
        if (r43 < 36) goto L19;
        if (r5 == VideoStabilization.PREVIEW) goto L19;
        r0.addAll(getQUERYABLE_BAKLAVA_FCQ_COMBINATIONS());
        return r0;
    L17:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L19:
        return r0;
    }

    public final List<SurfaceCombination> getStreamUseCaseSupportedCombinationList() {
        SurfaceCombination r0 = new SurfaceCombination();
        SurfaceConfig.Companion r1 = SurfaceConfig.Companion;
        SurfaceConfig.ConfigType r2 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize r3 = SurfaceConfig.ConfigSize.S1440P_4_3;
        StreamUseCase r4 = StreamUseCase.PREVIEW_VIDEO_STILL;
        r0.addSurfaceConfig(r1.create(r2, r3, r4));
        Unit r5 = Unit.INSTANCE;
        SurfaceCombination r52 = new SurfaceCombination();
        SurfaceConfig.ConfigType r6 = SurfaceConfig.ConfigType.YUV;
        r52.addSurfaceConfig(r1.create(r6, r3, r4));
        SurfaceCombination r32 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r42 = SurfaceConfig.ConfigSize.RECORD;
        StreamUseCase r7 = StreamUseCase.VIDEO_RECORD;
        r32.addSurfaceConfig(r1.create(r2, r42, r7));
        SurfaceCombination r33 = new SurfaceCombination();
        r33.addSurfaceConfig(r1.create(r6, r42, r7));
        SurfaceCombination r9 = new SurfaceCombination();
        SurfaceConfig.ConfigType r10 = SurfaceConfig.ConfigType.JPEG;
        SurfaceConfig.ConfigSize r11 = SurfaceConfig.ConfigSize.MAXIMUM;
        StreamUseCase r12 = StreamUseCase.STILL_CAPTURE;
        r9.addSurfaceConfig(r1.create(r10, r11, r12));
        SurfaceCombination r53 = new SurfaceCombination();
        r53.addSurfaceConfig(r1.create(r6, r11, r12));
        SurfaceCombination r14 = new SurfaceCombination();
        SurfaceConfig.ConfigSize r15 = SurfaceConfig.ConfigSize.PREVIEW;
        StreamUseCase r02 = StreamUseCase.PREVIEW;
        r14.addSurfaceConfig(r1.create(r2, r15, r02));
        r14.addSurfaceConfig(r1.create(r10, r11, r12));
        SurfaceCombination r34 = new SurfaceCombination();
        r34.addSurfaceConfig(r1.create(r2, r15, r02));
        r34.addSurfaceConfig(r1.create(r6, r11, r12));
        SurfaceCombination r8 = new SurfaceCombination();
        r8.addSurfaceConfig(r1.create(r2, r15, r02));
        r8.addSurfaceConfig(r1.create(r2, r42, r7));
        SurfaceCombination r92 = new SurfaceCombination();
        r92.addSurfaceConfig(r1.create(r2, r15, r02));
        r92.addSurfaceConfig(r1.create(r6, r42, r7));
        SurfaceCombination r35 = new SurfaceCombination();
        r35.addSurfaceConfig(r1.create(r2, r15, r02));
        r35.addSurfaceConfig(r1.create(r6, r15, r02));
        SurfaceCombination r54 = new SurfaceCombination();
        r54.addSurfaceConfig(r1.create(r2, r15, r02));
        r54.addSurfaceConfig(r1.create(r2, r42, r7));
        r54.addSurfaceConfig(r1.create(r10, r42, r12));
        SurfaceCombination r36 = new SurfaceCombination();
        r36.addSurfaceConfig(r1.create(r2, r15, r02));
        r36.addSurfaceConfig(r1.create(r6, r42, r7));
        r36.addSurfaceConfig(r1.create(r10, r42, r12));
        SurfaceCombination r13 = new SurfaceCombination();
        r13.addSurfaceConfig(r1.create(r2, r15, r02));
        r13.addSurfaceConfig(r1.create(r6, r15, r02));
        r13.addSurfaceConfig(r1.create(r10, r11, r12));
        return CollectionsKt.listOf(new SurfaceCombination[]{r0, r52, r32, r33, r9, r53, r14, r34, r8, r92, r35, r54, r36, r13});
    }
}
