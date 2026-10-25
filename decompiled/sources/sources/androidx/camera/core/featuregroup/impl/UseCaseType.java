package androidx.camera.core.featuregroup.impl;

import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.view.SurfaceHolder;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.featuregroup.impl.feature.FeatureTypeInternal;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.UseCaseUtil;
import androidx.camera.core.streamsharing.StreamSharing;
import java.util.Iterator;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0017\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u0011"}, d2 = {"Landroidx/camera/core/featuregroup/impl/UseCaseType;", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/String;ILjava/lang/Class;)V", "getSurfaceClass", "()Ljava/lang/Class;", "PREVIEW", "IMAGE_CAPTURE", "IMAGE_ANALYSIS", "VIDEO_CAPTURE", "STREAM_SHARING", "UNDEFINED", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum UseCaseType extends Enum<UseCaseType> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ UseCaseType[] $VALUES = null;
    public static final Companion Companion = null;
    public static final UseCaseType IMAGE_ANALYSIS = null;
    public static final UseCaseType IMAGE_CAPTURE = null;
    public static final UseCaseType PREVIEW = null;
    public static final UseCaseType STREAM_SHARING = null;
    public static final UseCaseType UNDEFINED = null;
    public static final UseCaseType VIDEO_CAPTURE = null;
    private final Class<?> surfaceClass;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007J\u0010\u0010\u0004\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u0007H\u0007J\u0013\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u0006H\u0000¢\u0006\u0002\b\nJ\u0014\u0010\u000b\u001a\u00020\f*\u00020\t2\u0006\u0010\r\u001a\u00020\u0006H\u0002J\f\u0010\u000e\u001a\u00020\f*\u00020\u0006H\u0002J\f\u0010\u000f\u001a\u00020\f*\u00020\u0006H\u0002J\f\u0010\u0010\u001a\u00020\f*\u00020\u0006H\u0002J\f\u0010\u0011\u001a\u00020\f*\u00020\u0006H\u0002J\f\u0010\u0012\u001a\u00020\f*\u00020\u0006H\u0002¨\u0006\u0013"}, d2 = {"Landroidx/camera/core/featuregroup/impl/UseCaseType$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getFeatureGroupUseCaseType", "Landroidx/camera/core/featuregroup/impl/UseCaseType;", "Landroidx/camera/core/UseCase;", "Landroidx/camera/core/impl/UseCaseConfig;", "getAppConfiguredGroupableFeatureType", "Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "getAppConfiguredGroupableFeatureType$camera_core", "isConfiguredToUseCaseByApp", _UrlKt.FRAGMENT_ENCODE_SET, "useCase", "isDynamicRangeConfiguredByApp", "isFpsRangeConfiguredByApp", "isStabilizationModeConfiguredByApp", "isImageFormatConfiguredByApp", "isRecordingQualityConfiguredByApp", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nUseCaseType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseType.kt\nandroidx/camera/core/featuregroup/impl/UseCaseType$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,165:1\n1#2:166\n*E\n"})
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;

            static {
                int[] r0 = new int[UseCaseConfigFactory.CaptureType.values().length];
                r0[UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L20
            L34:
                r0[UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L21
            L48:
                r0[UseCaseConfigFactory.CaptureType.PREVIEW.ordinal()] = 3;     // Catch: NoSuchFieldError -> L22
            L36:
                r0[UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L23
            L38:
                r0[UseCaseConfigFactory.CaptureType.STREAM_SHARING.ordinal()] = 5;     // Catch: NoSuchFieldError -> L24
            L12:
                $EnumSwitchMapping$0 = r0;
                int[] r02 = new int[FeatureTypeInternal.values().length];
                r02[FeatureTypeInternal.DYNAMIC_RANGE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L25
            L32:
                r02[FeatureTypeInternal.FPS_RANGE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L26
            L42:
                r02[FeatureTypeInternal.VIDEO_STABILIZATION.ordinal()] = 3;     // Catch: NoSuchFieldError -> L27
            L30:
                r02[FeatureTypeInternal.IMAGE_FORMAT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L28
            L40:
                r02[FeatureTypeInternal.RECORDING_QUALITY.ordinal()] = 5;     // Catch: NoSuchFieldError -> L29
            L18:
                $EnumSwitchMapping$1 = r02;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isConfiguredToUseCaseByApp(FeatureTypeInternal r2, UseCase r3) {
            int r22 = WhenMappings.$EnumSwitchMapping$1[r2.ordinal()];
            if (r22 == 1) goto L23;
            if (r22 == 2) goto L21;
            if (r22 == 3) goto L19;
            if (r22 == 4) goto L17;
            if (r22 == 5) goto L13;
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return false;
        L13:
            return isRecordingQualityConfiguredByApp(r3);
        L17:
            return isImageFormatConfiguredByApp(r3);
        L19:
            return isStabilizationModeConfiguredByApp(r3);
        L21:
            return isFpsRangeConfiguredByApp(r3);
        L23:
            return isDynamicRangeConfiguredByApp(r3);
        }

        private final boolean isDynamicRangeConfiguredByApp(UseCase r1) {
            return r1.getAppConfig().hasDynamicRange();
        }

        private final boolean isFpsRangeConfiguredByApp(UseCase r1) {
            return r1.getAppConfig().hasTargetFrameRate();
        }

        private final boolean isImageFormatConfiguredByApp(UseCase r1) {
            return r1.getAppConfig().containsOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT);
        }

        private final boolean isRecordingQualityConfiguredByApp(UseCase r2) {
            return Intrinsics.areEqual(r2.getAppConfig().retrieveOption(UseCaseConfig.OPTION_IS_VIDEO_QUALITY_SELECTOR_DEFAULT, Boolean.TRUE), Boolean.FALSE);
        }

        private final boolean isStabilizationModeConfiguredByApp(UseCase r2) {
            if (r2.getAppConfig().containsOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE) == false) goto L5;
            return true;
        L5:
            if (r2.getAppConfig().containsOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE) == true) goto L11;
            return false;
        L11:
            return true;
        }

        public final FeatureTypeInternal getAppConfiguredGroupableFeatureType$camera_core(UseCase r4) {
            Iterator<FeatureTypeInternal> r3 = FeatureTypeInternal.getEntries().iterator();
        L4:
            if (r3.hasNext() == false) goto L8;
            FeatureTypeInternal r0 = r3.next();
            Companion r2 = UseCaseType.Companion;
            if (r2.isConfiguredToUseCaseByApp(r0, r4) == false) goto L4;
        L10:
            return r0;
        L8:
            r0 = null;
            goto L10
        }

        @JvmStatic
        public final UseCaseType getFeatureGroupUseCaseType(UseCase r1) {
            if ((r1 instanceof Preview) == false) goto L7;
            return UseCaseType.PREVIEW;
        L7:
            if ((r1 instanceof ImageCapture) == false) goto L11;
            return UseCaseType.IMAGE_CAPTURE;
        L11:
            if (UseCaseUtil.isVideoCapture(r1) == false) goto L15;
            return UseCaseType.VIDEO_CAPTURE;
        L15:
            if ((r1 instanceof StreamSharing) == false) goto L19;
            return UseCaseType.STREAM_SHARING;
        L19:
            return UseCaseType.UNDEFINED;
        }

        @JvmStatic
        public final UseCaseType getFeatureGroupUseCaseType(UseCaseConfig<?> r1) {
            UseCaseConfigFactory.CaptureType r0 = r1.getCaptureType();
            int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
            if (r02 == 1) goto L23;
            if (r02 == 2) goto L21;
            if (r02 == 3) goto L19;
            if (r02 == 4) goto L17;
            if (r02 == 5) goto L15;
            return UseCaseType.UNDEFINED;
        L15:
            return UseCaseType.STREAM_SHARING;
        L17:
            return UseCaseType.VIDEO_CAPTURE;
        L19:
            return UseCaseType.PREVIEW;
        L21:
            return UseCaseType.IMAGE_CAPTURE;
        L23:
            return UseCaseType.IMAGE_ANALYSIS;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[UseCaseType.values().length];
            r0[UseCaseType.PREVIEW.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
        L19:
            r0[UseCaseType.IMAGE_CAPTURE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
        L27:
            r0[UseCaseType.IMAGE_ANALYSIS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
        L21:
            r0[UseCaseType.VIDEO_CAPTURE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[UseCaseType.STREAM_SHARING.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
        L17:
            r0[UseCaseType.UNDEFINED.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
        L9:
            $EnumSwitchMapping$0 = r0;
        }
    }

    private static final /* synthetic */ UseCaseType[] $values() {
        return new UseCaseType[]{PREVIEW, IMAGE_CAPTURE, IMAGE_ANALYSIS, VIDEO_CAPTURE, STREAM_SHARING, UNDEFINED};
    }

    static {
        PREVIEW = new UseCaseType("PREVIEW", 0, SurfaceHolder.class);
        IMAGE_CAPTURE = new UseCaseType("IMAGE_CAPTURE", 1, null);
        IMAGE_ANALYSIS = new UseCaseType("IMAGE_ANALYSIS", 2, null);
        VIDEO_CAPTURE = new UseCaseType("VIDEO_CAPTURE", 3, MediaCodec.class);
        STREAM_SHARING = new UseCaseType("STREAM_SHARING", 4, SurfaceTexture.class);
        UNDEFINED = new UseCaseType("UNDEFINED", 5, null);
        UseCaseType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    UseCaseType(String r1, int r2, Class r3) {
        this.surfaceClass = r3;
    }

    public static UseCaseType valueOf(String r1) {
        return (UseCaseType) Enum.valueOf(UseCaseType.class, r1);
    }

    public static UseCaseType[] values() {
        return (UseCaseType[]) $VALUES.clone();
    }

    public final Class<?> getSurfaceClass() {
        return this.surfaceClass;
    }

    @Override // java.lang.Enum
    public String toString() {
        switch(WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1: goto L16;
            case 2: goto L14;
            case 3: goto L12;
            case 4: goto L10;
            case 5: goto L8;
            case 6: goto L6;
            default: goto L4;
        };
    L4:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L6:
        return "Undefined";
    L8:
        return "StreamSharing";
    L10:
        return "VideoCapture";
    L12:
        return "ImageAnalysis";
    L14:
        return "ImageCapture";
    L16:
        return "Preview";
    }
}
