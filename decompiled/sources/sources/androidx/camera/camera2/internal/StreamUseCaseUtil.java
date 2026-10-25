package androidx.camera.camera2.internal;

import android.os.Build;
import android.util.Log;
import androidx.camera.camera2.adapter.SupportedSurfaceCombination;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraGraphConfigProvider$$ExternalSyntheticApiModelOutline0;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.streamsharing.StreamSharingConfig;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J+\u0010\u0017\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001a\u0010\u0003J=\u0010 \u001a\u00020\n2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\b2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0\b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H\u0002¢\u0006\u0004\b \u0010!J\u001f\u0010$\u001a\u00020\"2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"H\u0003¢\u0006\u0004\b$\u0010%JA\u0010-\u001a\u00020\u00192\f\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&2\u0010\u0010)\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0&2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00060*¢\u0006\u0004\b-\u0010.J\u0019\u00101\u001a\u0002002\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u001d¢\u0006\u0004\b1\u00102J\u0015\u00105\u001a\u00020\n2\u0006\u00104\u001a\u000203¢\u0006\u0004\b5\u00106J\u0015\u00109\u001a\u00020\n2\u0006\u00108\u001a\u000207¢\u0006\u0004\b9\u0010:JO\u0010>\u001a\u00020\n2\u0006\u00104\u001a\u0002032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\b2\u0016\u0010<\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0012\u0004\u0012\u00020;0*2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020;0*¢\u0006\u0004\b>\u0010?J#\u0010B\u001a\u00020\n2\u0006\u00104\u001a\u0002032\f\u0010A\u001a\b\u0012\u0004\u0012\u00020@0\b¢\u0006\u0004\bB\u0010CJI\u0010H\u001a\u00020\n2\u0014\u0010E\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0006\u0012\u0004\u0018\u00010\u001b0D2\u0016\u0010F\u001a\u0012\u0012\u0004\u0012\u00020\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0D2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020@0\b¢\u0006\u0004\bH\u0010IJs\u0010J\u001a\u00020\u00192\u0016\u0010<\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0012\u0004\u0012\u00020;0*2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020;0*2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001b0D2\u0016\u0010F\u001a\u0012\u0012\u0004\u0012\u00020\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0D2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020@0\b¢\u0006\u0004\bJ\u0010KJ-\u0010L\u001a\u00020\n2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\b2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0\b¢\u0006\u0004\bL\u0010MR&\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00060N8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\bO\u0010P\u0012\u0004\bS\u0010\u0003\u001a\u0004\bQ\u0010RR&\u0010T\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00140D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010UR&\u0010V\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00140D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010U¨\u0006W"}, d2 = {"Landroidx/camera/camera2/internal/StreamUseCaseUtil;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/core/impl/UseCaseConfigFactory$CaptureType;", "captureType", _UrlKt.FRAGMENT_ENCODE_SET, "streamUseCase", _UrlKt.FRAGMENT_ENCODE_SET, "streamSharingTypes", _UrlKt.FRAGMENT_ENCODE_SET, "isEligibleCaptureType", "(Landroidx/camera/core/impl/UseCaseConfigFactory$CaptureType;JLjava/util/List;)Z", "Landroidx/camera/core/impl/Config;", "oldImplementationOptions", "getUpdatedImplementationOptionsWithUseCaseStreamSpecOption", "(Landroidx/camera/core/impl/Config;Ljava/lang/Long;)Landroidx/camera/core/impl/Config;", "config", "isZslUseCase", "(Landroidx/camera/core/impl/Config;Landroidx/camera/core/impl/UseCaseConfigFactory$CaptureType;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "availableStreamUseCasesSet", "streamUseCases", "areStreamUseCasesAvailable", "(Ljava/util/Set;Ljava/util/Set;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "throwInvalidCamera2InteropOverrideException", "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "attachedSurfaces", "Landroidx/camera/core/impl/UseCaseConfig;", "newUseCaseConfigs", "availableStreamUseCases", "isValidCamera2InteropOverride", "(Ljava/util/List;Ljava/util/List;Ljava/util/Set;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "captureMode", "getSessionConfigTemplateType", "(Landroidx/camera/core/impl/UseCaseConfigFactory$CaptureType;I)I", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SessionConfig;", "sessionConfigs", "useCaseConfigs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/DeferrableSurface;", "streamUseCaseMap", "populateSurfaceToStreamUseCaseMapping", "(Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Map;)V", "useCaseConfig", "Landroidx/camera/camera2/impl/Camera2ImplConfig;", "getStreamSpecImplementationOptions", "(Landroidx/camera/core/impl/UseCaseConfig;)Landroidx/camera/camera2/impl/Camera2ImplConfig;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "isStreamUseCaseSupported", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Z", "Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$FeatureSettings;", "featureSettings", "shouldUseStreamUseCase", "(Landroidx/camera/camera2/adapter/SupportedSurfaceCombination$FeatureSettings;)Z", "Landroidx/camera/core/impl/StreamSpec;", "suggestedStreamSpecMap", "attachedSurfaceStreamSpecMap", "populateStreamUseCaseStreamSpecOptionWithInteropOverride", "(Landroidx/camera/camera2/pipe/CameraMetadata;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;)Z", "Landroidx/camera/core/impl/SurfaceConfig;", "surfaceConfigs", "areStreamUseCasesAvailableForSurfaceConfigs", "(Landroidx/camera/camera2/pipe/CameraMetadata;Ljava/util/List;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceConfigIndexAttachedSurfaceInfoMap", "surfaceConfigIndexUseCaseConfigMap", "surfaceConfigsWithStreamUseCase", "areCaptureTypesEligible", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/List;)Z", "populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;)V", "containsZslUseCase", "(Ljava/util/List;Ljava/util/List;)Z", "Landroidx/camera/core/impl/Config$Option;", "STREAM_USE_CASE_STREAM_SPEC_OPTION", "Landroidx/camera/core/impl/Config$Option;", "getSTREAM_USE_CASE_STREAM_SPEC_OPTION", "()Landroidx/camera/core/impl/Config$Option;", "getSTREAM_USE_CASE_STREAM_SPEC_OPTION$annotations", "STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP", "Ljava/util/Map;", "STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamUseCaseUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamUseCaseUtil.kt\nandroidx/camera/camera2/internal/StreamUseCaseUtil\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,634:1\n136#2,4:635\n85#2,4:639\n*S KotlinDebug\n*F\n+ 1 StreamUseCaseUtil.kt\nandroidx/camera/camera2/internal/StreamUseCaseUtil\n*L\n122#1:635,4\n167#1:639,4\n*E\n"})
/* loaded from: classes3.dex */
public final class StreamUseCaseUtil {
    public static final StreamUseCaseUtil INSTANCE = null;
    private static final Config.Option<Long> STREAM_USE_CASE_STREAM_SPEC_OPTION = null;
    private static final Map<Long, Set<UseCaseConfigFactory.CaptureType>> STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP = null;
    private static final Map<Long, Set<UseCaseConfigFactory.CaptureType>> STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP = null;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[UseCaseConfigFactory.CaptureType.values().length];
            r0[UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L15:
            r0[UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L23:
            r0[UseCaseConfigFactory.CaptureType.STREAM_SHARING.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[UseCaseConfigFactory.CaptureType.PREVIEW.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L19:
            r0[UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L8:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        INSTANCE = new StreamUseCaseUtil();
        STREAM_USE_CASE_STREAM_SPEC_OPTION = Config.Option.create("camera2.streamSpec.streamUseCase", Long.TYPE);
        Map r2 = MapsKt.createMapBuilder();
        int r3 = Build.VERSION.SDK_INT;
        if (r3 < 33) goto L5;
        UseCaseConfigFactory.CaptureType r5 = UseCaseConfigFactory.CaptureType.PREVIEW;
        UseCaseConfigFactory.CaptureType r6 = UseCaseConfigFactory.CaptureType.METERING_REPEATING;
        UseCaseConfigFactory.CaptureType r7 = UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS;
        r2.put(4L, SetsKt.setOf(new UseCaseConfigFactory.CaptureType[]{r5, r6, r7}));
        r2.put(1L, SetsKt.setOf(new UseCaseConfigFactory.CaptureType[]{r5, r6, r7}));
        r2.put(2L, SetsKt.setOf(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE));
        r2.put(3L, SetsKt.setOf(UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE));
    L5:
        STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP = MapsKt.build(r2);
        Map r22 = MapsKt.createMapBuilder();
        if (r3 < 33) goto L8;
        UseCaseConfigFactory.CaptureType r32 = UseCaseConfigFactory.CaptureType.PREVIEW;
        UseCaseConfigFactory.CaptureType r4 = UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE;
        UseCaseConfigFactory.CaptureType r52 = UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE;
        r22.put(4L, SetsKt.setOf(new UseCaseConfigFactory.CaptureType[]{r32, r4, r52}));
        r22.put(3L, SetsKt.setOf(new UseCaseConfigFactory.CaptureType[]{r32, r52}));
    L8:
        STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP = MapsKt.build(r22);
    }

    private StreamUseCaseUtil() {
    }

    private final boolean areStreamUseCasesAvailable(Set<Long> r3, Set<Long> r4) {
        Iterator<Long> r2 = r4.iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        if (r3.contains(Long.valueOf(r2.next().longValue())) == true) goto L4;
        return false;
    L9:
        return true;
    }

    private final int getSessionConfigTemplateType(UseCaseConfigFactory.CaptureType r2, int r3) {
        int r1 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r1 != 1) goto L5;
        if (r3 != 2) goto L12;
        return 5;
    L12:
        return 1;
    L5:
        if (r1 == 2) goto L8;
        if (r1 == 3) goto L8;
        return 1;
    L8:
        return 3;
    }

    private final Config getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(Config r2, Long r3) {
        Config.Option<Long> r1 = STREAM_USE_CASE_STREAM_SPEC_OPTION;
        if (r2.containsOption(r1) == true) goto L5;
    L8:
        MutableOptionsBundle r22 = MutableOptionsBundle.from(r2);
        r22.insertOption(r1, r3);
        return new Camera2ImplConfig(r22);
    L5:
        if (Intrinsics.areEqual(r2.retrieveOption(r1), r3) == false) goto L8;
        return null;
    }

    private final boolean isEligibleCaptureType(UseCaseConfigFactory.CaptureType r3, long r4, List<? extends UseCaseConfigFactory.CaptureType> r6) {
        if (Build.VERSION.SDK_INT >= 33) goto L6;
        return false;
    L6:
        if (r3 != UseCaseConfigFactory.CaptureType.STREAM_SHARING) goto L20;
        Map<Long, Set<UseCaseConfigFactory.CaptureType>> r2 = STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP;
        if (r2.containsKey(Long.valueOf(r4)) == true) goto L10;
        return false;
    L10:
        Set<UseCaseConfigFactory.CaptureType> r22 = r2.get(Long.valueOf(r4));
        if (r6.size() == r22.size()) goto L13;
        return false;
    L13:
        Iterator<? extends UseCaseConfigFactory.CaptureType> r32 = r6.iterator();
    L15:
        if (r32.hasNext() == false) goto L19;
        if (r22.contains(r32.next()) == true) goto L15;
        return false;
    L19:
        return true;
    L20:
        Map<Long, Set<UseCaseConfigFactory.CaptureType>> r23 = STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP;
        if (r23.containsKey(Long.valueOf(r4)) == true) goto L23;
    L25:
        return false;
    L23:
        if (r23.get(Long.valueOf(r4)).contains(r3) == false) goto L25;
        return true;
    }

    private final boolean isValidCamera2InteropOverride(List<? extends AttachedSurfaceInfo> r10, List<? extends UseCaseConfig<?>> r11, Set<Long> r12) {
        LinkedHashSet r0 = new LinkedHashSet();
        Iterator<? extends AttachedSurfaceInfo> r102 = r10.iterator();
        if (r102.hasNext() == false) goto L11;
        AttachedSurfaceInfo r103 = r102.next();
        Config r1 = r103.getImplementationOptions();
        Config.Option<Long> r6 = Camera2ImplConfig.STREAM_USE_CASE_OPTION;
        if (r1.containsOption(r6) == true) goto L8;
    L6:
        boolean r13 = true;
        boolean r104 = false;
    L12:
        Iterator<? extends UseCaseConfig<?>> r112 = r11.iterator();
    L14:
        if (r112.hasNext() == false) goto L27;
        UseCaseConfig<?> r62 = r112.next();
        Config.Option<?> r7 = Camera2ImplConfig.STREAM_USE_CASE_OPTION;
        if (r62.containsOption(r7) == false) goto L17;
        long r63 = ((Number) r62.retrieveOption(r7)).longValue();
        if (r63 == 0) goto L22;
        if (r13 == false) goto L26;
        throwInvalidCamera2InteropOverrideException();
    L26:
        r0.add(Long.valueOf(r63));
        r104 = true;
        goto L14
    L22:
        if (r104 == true) goto L23;
    L19:
        r13 = true;
        goto L14
    L23:
        throwInvalidCamera2InteropOverrideException();
        goto L19
    L17:
        if (r104 == false) goto L19;
        throwInvalidCamera2InteropOverrideException();
        goto L19
    L27:
        if (r13 == false) goto L29;
    L31:
        return false;
    L29:
        if (areStreamUseCasesAvailable(r12, r0) == false) goto L31;
        return true;
    L8:
        if (((Number) r103.getImplementationOptions().retrieveOption(r6)).longValue() == 0) goto L6;
        r104 = true;
        r13 = false;
        goto L12
    L11:
        r104 = false;
        r13 = false;
        goto L12
    }

    private final boolean isZslUseCase(Config r4, UseCaseConfigFactory.CaptureType r5) {
        if (((Boolean) r4.retrieveOption(UseCaseConfig.OPTION_ZSL_DISABLED, Boolean.FALSE)).booleanValue() == false) goto L5;
        return false;
    L5:
        Config.Option<Integer> r0 = ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE;
        if (r4.containsOption(r0) == true) goto L9;
        return false;
    L9:
        if (getSessionConfigTemplateType(r5, ((Number) r4.retrieveOption(r0)).intValue()) != 5) goto L12;
        return true;
    L12:
        return false;
    }

    private final void throwInvalidCamera2InteropOverrideException() {
        throw new IllegalArgumentException("Either all use cases must have non-default stream use case assigned or none should have it");
    }

    public final boolean areCaptureTypesEligible(Map<Integer, ? extends AttachedSurfaceInfo> r10, Map<Integer, ? extends UseCaseConfig<?>> r11, List<SurfaceConfig> r12) {
        int r0 = r12.size();
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L26;
        long r4 = r12.get(r2).getStreamUseCase().getValue();
        if (r10.containsKey(Integer.valueOf(r2)) == false) goto L15;
        AttachedSurfaceInfo r6 = r10.get(Integer.valueOf(r2));
        if (r6.getCaptureTypes().size() != 1) goto L10;
        UseCaseConfigFactory.CaptureType r3 = r6.getCaptureTypes().get(0);
    L12:
        if (isEligibleCaptureType(r3, r4, r6.getCaptureTypes()) == true) goto L23;
        return false;
    L23:
        r2 = r2 + 1;
        goto L4
    L10:
        r3 = UseCaseConfigFactory.CaptureType.STREAM_SHARING;
        goto L12
    L15:
        if (r11.containsKey(Integer.valueOf(r2)) == false) goto L24;
        UseCaseConfig<?> r32 = r11.get(Integer.valueOf(r2));
        UseCaseConfigFactory.CaptureType r62 = r32.getCaptureType();
        if (r32.getCaptureType() != UseCaseConfigFactory.CaptureType.STREAM_SHARING) goto L19;
        List<UseCaseConfigFactory.CaptureType> r33 = ((StreamSharingConfig) r32).getCaptureTypes();
    L21:
        if (isEligibleCaptureType(r62, r4, r33) == true) goto L23;
        return false;
    L19:
        r33 = CollectionsKt.emptyList();
        goto L21
    L24:
        Buffer$$ExternalSyntheticBUOutline2.m("SurfaceConfig does not map to any use case");
        return false;
    L26:
        return true;
    }

    public final boolean areStreamUseCasesAvailableForSurfaceConfigs(CameraMetadata r6, List<SurfaceConfig> r7) {
        if (Build.VERSION.SDK_INT >= 33) goto L5;
        return false;
    L5:
        long[] r5 = (long[]) r6.get(CameraGraphConfigProvider$$ExternalSyntheticApiModelOutline0.m());
        if (r5 != null) goto L8;
    L21:
        return false;
    L8:
        if (r5.length == 0) goto L21;
        HashSet r62 = new HashSet();
        int r0 = r5.length;
        int r2 = 0;
    L11:
        if (r2 >= r0) goto L13;
        r62.add(Long.valueOf(r5[r2]));
        r2 = r2 + 1;
        goto L11
    L13:
        Iterator<SurfaceConfig> r52 = r7.iterator();
    L15:
        if (r52.hasNext() == false) goto L19;
        if (r62.contains(Long.valueOf(r52.next().getStreamUseCase().getValue())) == true) goto L15;
        return false;
    L19:
        return true;
    }

    public final boolean containsZslUseCase(List<? extends AttachedSurfaceInfo> r5, List<? extends UseCaseConfig<?>> r6) {
        Iterator<? extends AttachedSurfaceInfo> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        AttachedSurfaceInfo r0 = r52.next();
        UseCaseConfigFactory.CaptureType r2 = r0.getCaptureTypes().get(0);
        if (isZslUseCase(r0.getImplementationOptions(), r2) == false) goto L4;
        return true;
    L8:
        Iterator<? extends UseCaseConfig<?>> r53 = r6.iterator();
    L10:
        if (r53.hasNext() == false) goto L14;
        UseCaseConfig<?> r62 = r53.next();
        if (isZslUseCase(r62, r62.getCaptureType()) == false) goto L10;
        return true;
    L14:
        return false;
    }

    public final Camera2ImplConfig getStreamSpecImplementationOptions(UseCaseConfig<?> r3) {
        MutableOptionsBundle r2 = MutableOptionsBundle.create();
        Config.Option<?> r0 = Camera2ImplConfig.STREAM_USE_CASE_OPTION;
        if (r3.containsOption(r0) == false) goto L5;
        r2.insertOption(r0, r3.retrieveOption(r0));
    L5:
        Config.Option<?> r02 = UseCaseConfig.OPTION_ZSL_DISABLED;
        if (r3.containsOption(r02) == false) goto L8;
        r2.insertOption(r02, r3.retrieveOption(r02));
    L8:
        Config.Option<?> r03 = ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE;
        if (r3.containsOption(r03) == false) goto L11;
        r2.insertOption(r03, r3.retrieveOption(r03));
    L11:
        Config.Option<?> r04 = ImageInputConfig.OPTION_INPUT_FORMAT;
        if (r3.containsOption(r04) == false) goto L15;
        r2.insertOption(r04, r3.retrieveOption(r04));
    L15:
        return new Camera2ImplConfig(r2);
    }

    public final boolean isStreamUseCaseSupported(CameraMetadata r3) {
        if (Build.VERSION.SDK_INT >= 33) goto L5;
        return false;
    L5:
        long[] r2 = (long[]) r3.get(CameraGraphConfigProvider$$ExternalSyntheticApiModelOutline0.m());
        if (r2 != null) goto L8;
    L12:
        return false;
    L8:
        if (r2.length == 0) goto L12;
        return true;
    }

    public final boolean populateStreamUseCaseStreamSpecOptionWithInteropOverride(CameraMetadata r8, List<? extends AttachedSurfaceInfo> r9, Map<UseCaseConfig<?>, StreamSpec> r10, Map<AttachedSurfaceInfo, StreamSpec> r11) {
        int r2 = 0;
        if (Build.VERSION.SDK_INT >= 33) goto L5;
        return false;
    L5:
        ArrayList r0 = new ArrayList(r10.keySet());
        Iterator<? extends AttachedSurfaceInfo> r1 = r9.iterator();
    L7:
        if (r1.hasNext() == false) goto L13;
        if (r1.next().getImplementationOptions() != null) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return false;
    L13:
        int r12 = r0.size();
        int r3 = 0;
    L14:
        if (r3 >= r12) goto L24;
        Object r5 = r0.get(r3);
        r3 = r3 + 1;
        StreamSpec r52 = r10.get((UseCaseConfig) r5);
        if (r52 == null) goto L22;
        if (r52.getImplementationOptions() != null) goto L14;
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return false;
    L22:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return false;
    L24:
        long[] r82 = (long[]) r8.get(CameraGraphConfigProvider$$ExternalSyntheticApiModelOutline0.m());
        if (r82 != null) goto L27;
    L47:
        return false;
    L27:
        if (r82.length == 0) goto L47;
        Set<Long> r13 = new HashSet();
        int r32 = r82.length;
        int r4 = 0;
    L30:
        if (r4 >= r32) goto L33;
        r13.add(Long.valueOf(r82[r4]));
        r4 = r4 + 1;
        goto L30
    L33:
        if (isValidCamera2InteropOverride(r9, r0, r13) == false) goto L47;
        Iterator<? extends AttachedSurfaceInfo> r83 = r9.iterator();
    L36:
        if (r83.hasNext() == false) goto L40;
        AttachedSurfaceInfo r92 = r83.next();
        Config r14 = r92.getImplementationOptions();
        Config r15 = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(r14, (Long) r14.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION));
        if (r15 == null) goto L36;
        r11.put(r92, r92.toStreamSpec(r15));
        goto L36
    L40:
        int r84 = r0.size();
    L41:
        if (r2 >= r84) goto L45;
        Object r93 = r0.get(r2);
        r2 = r2 + 1;
        UseCaseConfig<?> r94 = (UseCaseConfig) r93;
        StreamSpec r112 = r10.get(r94);
        Config r16 = r112.getImplementationOptions();
        Config r17 = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(r16, (Long) r16.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION));
        if (r17 == null) goto L41;
        r10.put(r94, r112.toBuilder().setImplementationOptions(r17).build());
        goto L41
    L45:
        return true;
    }

    public final void populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs(Map<UseCaseConfig<?>, StreamSpec> r8, Map<AttachedSurfaceInfo, StreamSpec> r9, Map<Integer, ? extends AttachedSurfaceInfo> r10, Map<Integer, ? extends UseCaseConfig<?>> r11, List<SurfaceConfig> r12) {
        int r0 = r12.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L23;
        long r2 = r12.get(r1).getStreamUseCase().getValue();
        if (r10.containsKey(Integer.valueOf(r1)) == false) goto L10;
        AttachedSurfaceInfo r4 = r10.get(Integer.valueOf(r1));
        Config r22 = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(r4.getImplementationOptions(), Long.valueOf(r2));
        if (r22 == null) goto L14;
        r9.put(r4, r4.toStreamSpec(r22));
    L14:
        r1 = r1 + 1;
        goto L3
    L10:
        if (r11.containsKey(Integer.valueOf(r1)) == false) goto L15;
        UseCaseConfig<?> r42 = r11.get(Integer.valueOf(r1));
        StreamSpec r5 = r8.get(r42);
        Config r23 = getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(r5.getImplementationOptions(), Long.valueOf(r2));
        if (r23 == null) goto L14;
        r8.put(r42, r5.toBuilder().setImplementationOptions(r23).build());
        goto L14
    L15:
        Buffer$$ExternalSyntheticBUOutline2.m("SurfaceConfig does not map to any use case");
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void populateSurfaceToStreamUseCaseMapping(Collection<SessionConfig> r8, Collection<? extends UseCaseConfig<?>> r9, Map<DeferrableSurface, Long> r10) {
        ArrayList r7 = new ArrayList(r9);
        Iterator<SessionConfig> r92 = r8.iterator();
    L4:
        if (r92.hasNext() == false) goto L27;
        SessionConfig r0 = r92.next();
        Config r2 = r0.getImplementationOptions();
        Config.Option<Long> r3 = STREAM_USE_CASE_STREAM_SPEC_OPTION;
        if (r2.containsOption(r3) == false) goto L14;
        if (r0.getSurfaces().size() == 1) goto L14;
        Camera2Logger r72 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L40;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "StreamUseCaseUtil: SessionConfig has stream use case but also contains " + r0.getSurfaces().size() + " surfaces, abort populateSurfaceToStreamUseCaseMapping().");
        return;
    L40:
        return;
    L14:
        if (r0.getImplementationOptions().containsOption(r3) == false) goto L4;
        Iterator<SessionConfig> r82 = r8.iterator();
        int r02 = 0;
    L17:
        if (r82.hasNext() == false) goto L27;
        SessionConfig r22 = r82.next();
        if (((UseCaseConfig) r7.get(r02)).getCaptureType() != UseCaseConfigFactory.CaptureType.METERING_REPEATING) goto L21;
        Preconditions.checkState(!r22.getSurfaces().isEmpty(), "MeteringRepeating should contain a surface");
        r10.put(r22.getSurfaces().get(0), 1L);
    L26:
        r02 = r02 + 1;
        goto L17
    L21:
        Config r32 = r22.getImplementationOptions();
        Config.Option<Long> r5 = STREAM_USE_CASE_STREAM_SPEC_OPTION;
        if (r32.containsOption(r5) == false) goto L26;
        if (r22.getSurfaces().isEmpty() == true) goto L26;
        r10.put(r22.getSurfaces().get(0), r22.getImplementationOptions().retrieveOption(r5));
    L27:
        Camera2Logger r73 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L41;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "populateSurfaceToStreamUseCaseMapping() - streamUseCaseMap = " + r10);
        return;
    }

    public final boolean shouldUseStreamUseCase(SupportedSurfaceCombination.FeatureSettings r2) {
        if (r2.getCameraMode() == 0) goto L5;
        return false;
    L5:
        if (r2.getRequiredMaxBitDepth() == 8) goto L7;
        return false;
    L7:
        if (r2.isHighSpeedOn() == true) goto L13;
        return true;
    L13:
        return false;
    }
}
