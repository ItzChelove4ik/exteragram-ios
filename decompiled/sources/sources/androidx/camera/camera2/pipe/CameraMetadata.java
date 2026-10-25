package androidx.camera.camera2.pipe;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.camera2.pipe.compat.Api33Compat;
import androidx.camera.camera2.pipe.compat.Api34Compat;
import androidx.camera.camera2.pipe.compat.Api35Compat;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0001!J&\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H¦\u0002¢\u0006\u0004\b\u0006\u0010\u0007J+\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\"À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CameraMetadata;", "Landroidx/camera/camera2/pipe/Metadata;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "T", "Landroid/hardware/camera2/CameraCharacteristics$Key;", PluginsConstants.Settings.KEY, "get", "(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;", PluginsConstants.Settings.DEFAULT, "getOrDefault", "(Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", "awaitPhysicalMetadata-EfqyGwQ", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitPhysicalMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "extension", "Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "awaitExtensionMetadata", "(I)Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "getCamera-Dz_R5H8", "()Ljava/lang/String;", "camera", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "getSessionKeys", "()Ljava/util/Set;", "sessionKeys", "getPhysicalCameraIds", "physicalCameraIds", "getSupportedExtensions", "supportedExtensions", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraMetadata extends Metadata, UnsafeWrapper {
    public static final Companion Companion = null;

    @kotlin.Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R&\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\b\u0010\tR&\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\f\u0010\u0007\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\r\u0010\tR&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0010\u0010\u0007\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0011\u0010\tR\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0015\u0010\u001d\u001a\u00020\u0013*\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u001f\u001a\u00020\u0013*\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0015\u0010!\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0015\u0010#\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b#\u0010\"R\u0015\u0010$\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0015\u0010&\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b%\u0010\"R\u0015\u0010(\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b'\u0010\"R\u0015\u0010*\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b)\u0010\"R\u0015\u0010,\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b+\u0010\"R\u0015\u00100\u001a\u00020-*\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0015\u00102\u001a\u00020-*\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b1\u0010/R\u0015\u00104\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b3\u0010\"R\u0015\u00106\u001a\u00020 *\u00020\u001a8G¢\u0006\u0006\u001a\u0004\b5\u0010\"¨\u00067"}, d2 = {"Landroidx/camera/camera2/pipe/CameraMetadata$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/Metadata$Key;", "Landroidx/camera/camera2/pipe/CameraStreamConfigurationMap;", "CAMERA_STREAM_CONFIGURATION_MAP", "Landroidx/camera/camera2/pipe/Metadata$Key;", "getCAMERA_STREAM_CONFIGURATION_MAP", "()Landroidx/camera/camera2/pipe/Metadata$Key;", "getCAMERA_STREAM_CONFIGURATION_MAP$annotations", "Landroidx/camera/camera2/pipe/CameraMultiResolutionStreamConfigurationMap;", "CAMERA_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP", "getCAMERA_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP", "getCAMERA_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP$annotations", "Landroidx/camera/camera2/pipe/CameraColorSpaceProfiles;", "CAMERA_AVAILABLE_COLOR_SPACE_PROFILES", "getCAMERA_AVAILABLE_COLOR_SPACE_PROFILES", "getCAMERA_AVAILABLE_COLOR_SPACE_PROFILES$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "EMPTY_INT_ARRAY", "[I", "getEMPTY_INT_ARRAY", "()[I", "setEMPTY_INT_ARRAY", "([I)V", "Landroidx/camera/camera2/pipe/CameraMetadata;", "getAvailableCapabilities", "(Landroidx/camera/camera2/pipe/CameraMetadata;)[I", "availableCapabilities", "getAvailableVideoStabilizationModes", "availableVideoStabilizationModes", _UrlKt.FRAGMENT_ENCODE_SET, "isHardwareLevelExternal", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Z", "isHardwareLevelLegacy", "isHardwareLevelLimited", "getSupportsPrivateReprocessing", "supportsPrivateReprocessing", "getSupportsAutoFocusTrigger", "supportsAutoFocusTrigger", "getSupportsZoomOverride", "supportsZoomOverride", "getSupportsTorchStrength", "supportsTorchStrength", _UrlKt.FRAGMENT_ENCODE_SET, "getMaxTorchStrengthLevel", "(Landroidx/camera/camera2/pipe/CameraMetadata;)I", "maxTorchStrengthLevel", "getDefaultTorchStrengthLevel", "defaultTorchStrengthLevel", "getSupportsLowLightBoost", "supportsLowLightBoost", "getSupportsPreviewStabilization", "supportsPreviewStabilization", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraMetadata.kt\nandroidx/camera/camera2/pipe/CameraMetadata$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Metadata.kt\nandroidx/camera/camera2/pipe/Metadata$Key$Companion\n*L\n1#1,361:1\n1#2:362\n47#3:363\n47#3:364\n47#3:365\n*S KotlinDebug\n*F\n+ 1 CameraMetadata.kt\nandroidx/camera/camera2/pipe/CameraMetadata$Companion\n*L\n83#1:363\n96#1:364\n108#1:365\n*E\n"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;
        private static final Metadata.Key<CameraColorSpaceProfiles> CAMERA_AVAILABLE_COLOR_SPACE_PROFILES = null;
        private static final Metadata.Key<CameraMultiResolutionStreamConfigurationMap> CAMERA_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP = null;
        private static final Metadata.Key<CameraStreamConfigurationMap> CAMERA_STREAM_CONFIGURATION_MAP = null;
        private static int[] EMPTY_INT_ARRAY;

        static {
            $$INSTANCE = new Companion();
            Metadata.Key.Companion r0 = Metadata.Key.Companion;
            CAMERA_STREAM_CONFIGURATION_MAP = r0.create("androidx.camera.camera2.pipe.scalar.streamConfigurationMap", Reflection.getOrCreateKotlinClass(CameraStreamConfigurationMap.class));
            CAMERA_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP = r0.create("androidx.camera.camera2.pipe.scalar.multiResolutionStreamConfigurationMap", Reflection.getOrCreateKotlinClass(CameraMultiResolutionStreamConfigurationMap.class));
            CAMERA_AVAILABLE_COLOR_SPACE_PROFILES = r0.create("androidx.camera.camera2.pipe.request.availableColorSpaceProfilesMap", Reflection.getOrCreateKotlinClass(CameraColorSpaceProfiles.class));
            EMPTY_INT_ARRAY = new int[0];
        }

        private Companion() {
        }

        @JvmStatic
        public final int[] getAvailableCapabilities(CameraMetadata r1) {
            int[] r0 = (int[]) r1.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            if (r0 == null) goto L5;
            return r0;
        L5:
            return EMPTY_INT_ARRAY;
        }

        @JvmStatic
        public final int[] getAvailableVideoStabilizationModes(CameraMetadata r1) {
            int[] r0 = (int[]) r1.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
            if (r0 == null) goto L5;
            return r0;
        L5:
            return EMPTY_INT_ARRAY;
        }

        @JvmStatic
        public final int getDefaultTorchStrengthLevel(CameraMetadata r2) {
            if (Build.VERSION.SDK_INT >= 35) goto L5;
            return 1;
        L5:
            return Api35Compat.getDefaultTorchStrengthLevel(r2);
        }

        @JvmStatic
        public final int getMaxTorchStrengthLevel(CameraMetadata r2) {
            if (Build.VERSION.SDK_INT >= 35) goto L5;
            return 1;
        L5:
            return Api35Compat.getMaxTorchStrengthLevel(r2);
        }

        @JvmStatic
        public final boolean getSupportsAutoFocusTrigger(CameraMetadata r3) {
            Float r2 = (Float) r3.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
            if (r2 != null) goto L5;
            int[] r22 = (int[]) r3.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            if (r22 != null) goto L12;
            return false;
        L12:
            if (ArraysKt.contains(r22, 1) == false) goto L14;
        L21:
            return true;
        L14:
            if (ArraysKt.contains(r22, 2) == true) goto L21;
            if (ArraysKt.contains(r22, 4) == true) goto L21;
            if (ArraysKt.contains(r22, 3) == true) goto L21;
            return false;
        L5:
            if (r2.floatValue() <= 0.0f) goto L7;
            return true;
        L7:
            return false;
        }

        @JvmStatic
        public final boolean getSupportsLowLightBoost(CameraMetadata r1) {
            int[] r0 = (int[]) r1.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
            if (r0 != null) goto L7;
            return false;
        L7:
            return ArraysKt.contains(r0, 6);
        }

        @JvmStatic
        public final boolean getSupportsPreviewStabilization(CameraMetadata r2) {
            if (Build.VERSION.SDK_INT >= 33) goto L5;
            return false;
        L5:
            return Api33Compat.INSTANCE.supportsPreviewStabilization(r2);
        }

        @JvmStatic
        public final boolean getSupportsPrivateReprocessing(CameraMetadata r1) {
            return ArraysKt.contains(getAvailableCapabilities(r1), 4);
        }

        @JvmStatic
        public final boolean getSupportsTorchStrength(CameraMetadata r2) {
            if (Build.VERSION.SDK_INT >= 35) goto L5;
            return false;
        L5:
            if (Api35Compat.isTorchStrengthSupported(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @JvmStatic
        public final boolean getSupportsZoomOverride(CameraMetadata r2) {
            if (Build.VERSION.SDK_INT >= 34) goto L5;
            return false;
        L5:
            if (Api34Compat.isZoomOverrideSupported(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @JvmStatic
        public final boolean isHardwareLevelExternal(CameraMetadata r1) {
            Integer r0 = (Integer) r1.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            if (r0 != null) goto L6;
            return false;
        L6:
            if (r0.intValue() != 4) goto L9;
            return true;
        L9:
            return false;
        }

        @JvmStatic
        public final boolean isHardwareLevelLegacy(CameraMetadata r1) {
            Integer r0 = (Integer) r1.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            if (r0 != null) goto L6;
            return false;
        L6:
            if (r0.intValue() != 2) goto L9;
            return true;
        L9:
            return false;
        }

        @JvmStatic
        public final boolean isHardwareLevelLimited(CameraMetadata r1) {
            Integer r0 = (Integer) r1.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            if (r0 != null) goto L6;
            return false;
        L6:
            if (r0.intValue() != 0) goto L9;
            return true;
        L9:
            return false;
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    CameraExtensionMetadata awaitExtensionMetadata(int r1);

    /* renamed from: awaitPhysicalMetadata-EfqyGwQ, reason: not valid java name */
    CameraMetadata mo236awaitPhysicalMetadataEfqyGwQ(String r1);

    <T> T get(CameraCharacteristics.Key<T> r1);

    /* renamed from: getCamera-Dz_R5H8, reason: not valid java name */
    String mo237getCameraDz_R5H8();

    <T> T getOrDefault(CameraCharacteristics.Key<T> r1, T r2);

    Set<CameraId> getPhysicalCameraIds();

    Set<CaptureRequest.Key<?>> getSessionKeys();

    Set<Integer> getSupportedExtensions();
}
