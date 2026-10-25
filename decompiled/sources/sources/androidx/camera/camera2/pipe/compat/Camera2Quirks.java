package androidx.camera.camera2.pipe.compat;

import android.os.Build;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.StrictMode;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u0017\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0013\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0015\u0010\u0011J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", _UrlKt.FRAGMENT_ENCODE_SET, "metadataProvider", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;", "strictMode", "Landroidx/camera/camera2/pipe/StrictMode;", "<init>", "(Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;Landroidx/camera/camera2/pipe/StrictMode;)V", "shouldWaitForRepeatingRequestStartOnDisconnect", _UrlKt.FRAGMENT_ENCODE_SET, "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "shouldWaitForRepeatingRequestStartOnDisconnect$camera_camera2_pipe", "shouldCreateEmptyCaptureSessionBeforeClosing", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "shouldCreateEmptyCaptureSessionBeforeClosing-EfqyGwQ$camera_camera2_pipe", "(Ljava/lang/String;)Z", "shouldWaitForCameraDeviceOnClosed", "shouldWaitForCameraDeviceOnClosed-EfqyGwQ$camera_camera2_pipe", "shouldCloseCameraBeforeCreatingCaptureSession", "shouldCloseCameraBeforeCreatingCaptureSession-EfqyGwQ$camera_camera2_pipe", "getRepeatingRequestFrameCountForCapture", _UrlKt.FRAGMENT_ENCODE_SET, "graphConfigFlags", "Landroidx/camera/camera2/pipe/CameraGraph$Flags;", "getRepeatingRequestFrameCountForCapture$camera_camera2_pipe", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2Quirks {
    public static final Companion Companion = null;
    private static final Map<String, Set<String>> SHOULD_WAIT_FOR_REPEATING_DEVICE_MAP = null;
    private static final Map<String, Set<String>> SM8150_DEVICES = null;
    private final Camera2MetadataProvider metadataProvider;
    private final StrictMode strictMode;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bR \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2Quirks$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SHOULD_WAIT_FOR_REPEATING_DEVICE_MAP", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "SM8150_DEVICES", "shouldCloseCaptureSessionOnDisconnect", _UrlKt.FRAGMENT_ENCODE_SET, "shouldCloseCaptureSessionOnDisconnect$camera_camera2_pipe", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean shouldCloseCaptureSessionOnDisconnect$camera_camera2_pipe() {
            int r3 = Build.VERSION.SDK_INT;
            if (r3 > 27) goto L5;
            return true;
        L5:
            String r0 = Build.HARDWARE;
            if (Intrinsics.areEqual(r0, "samsungexynos7870") == false) goto L9;
            return true;
        L9:
            if (StringsKt.equals(r0, "qcom", true) == true) goto L11;
        L12:
            Map r32 = Camera2Quirks.access$getSM8150_DEVICES$cp();
            String r02 = Build.BRAND;
            Locale r2 = Locale.ROOT;
            Set r33 = (Set) r32.get(r02.toLowerCase(r2));
            if (r33 != null) goto L15;
            return false;
        L15:
            if (r33.contains(Build.MODEL.toLowerCase(r2)) != true) goto L19;
        L16:
            return true;
        L19:
            return false;
        L11:
            if (r3 <= 31) goto L16;
            goto L12
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[CameraGraph.RepeatingRequestRequirementsBeforeCapture.CompletionBehavior.values().length];
            r0[CameraGraph.RepeatingRequestRequirementsBeforeCapture.CompletionBehavior.AT_LEAST.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L9:
            r0[CameraGraph.RepeatingRequestRequirementsBeforeCapture.CompletionBehavior.EXACT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L5:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
        SHOULD_WAIT_FOR_REPEATING_DEVICE_MAP = MapsKt.mapOf(TuplesKt.to("Google", SetsKt.setOf(new String[]{"oriole", "raven", "bluejay", "panther", "cheetah", "lynx"})));
        SM8150_DEVICES = MapsKt.mapOf(new Pair[]{TuplesKt.to("google", SetsKt.setOf(new String[]{"pixel 4", "pixel 4 xl"})), TuplesKt.to("samsung", SetsKt.setOf("sm-g770f"))});
    }

    public Camera2Quirks(Camera2MetadataProvider r1, StrictMode r2) {
        this.metadataProvider = r1;
        this.strictMode = r2;
    }

    public static final /* synthetic */ Map access$getSM8150_DEVICES$cp() {
        return SM8150_DEVICES;
    }

    public final int getRepeatingRequestFrameCountForCapture$camera_camera2_pipe(CameraGraph.Flags r5) {
        if (this.strictMode.getEnabled() == false) goto L5;
        return 0;
    L5:
        CameraGraph.RepeatingRequestRequirementsBeforeCapture r4 = r5.getAwaitRepeatingRequestBeforeCapture();
        Set<String> r52 = SHOULD_WAIT_FOR_REPEATING_DEVICE_MAP.get(Build.MANUFACTURER);
        if (r52 != null) goto L8;
    L12:
        int r53 = 0;
    L13:
        int r2 = WhenMappings.$EnumSwitchMapping$0[r4.getCompletionBehavior().ordinal()];
        if (r2 == 1) goto L22;
        if (r2 == 2) goto L18;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L18:
        return r4.m223getRepeatingFramesToCompletepVg5ArA();
    L22:
        return Math.max(r53, r4.m223getRepeatingFramesToCompletepVg5ArA());
    L8:
        if (r52.contains(Build.DEVICE) != true) goto L12;
        if (Build.VERSION.SDK_INT >= 34) goto L12;
        r53 = Math.max(0, 10);
        goto L13
    }

    /* renamed from: shouldCloseCameraBeforeCreatingCaptureSession-EfqyGwQ$camera_camera2_pipe, reason: not valid java name */
    public final boolean m459x552c1673(String r5) {
        if (this.strictMode.getEnabled() == false) goto L6;
        return false;
    L6:
        if (Build.VERSION.SDK_INT <= 32) goto L8;
    L10:
        boolean r4 = false;
    L12:
        if (StringsKt.equals("motorola", Build.BRAND, true) == true) goto L14;
    L18:
        boolean r52 = false;
    L19:
        if (r4 == true) goto L23;
        if (r52 == true) goto L23;
        return false;
    L23:
        return true;
    L14:
        if (StringsKt.equals("moto e20", Build.MODEL, true) == false) goto L18;
        if (Intrinsics.areEqual(r5, "1") == false) goto L18;
        r52 = true;
        goto L19
    L8:
        if (CameraMetadata.Companion.isHardwareLevelLegacy(this.metadataProvider.mo456awaitCameraMetadataEfqyGwQ(r5)) == false) goto L10;
        r4 = true;
        goto L12
    }

    /* renamed from: shouldCreateEmptyCaptureSessionBeforeClosing-EfqyGwQ$camera_camera2_pipe, reason: not valid java name */
    public final boolean m460xfcf3eba9(String r4) {
        if (this.strictMode.getEnabled() == false) goto L6;
        return false;
    L6:
        if (Build.VERSION.SDK_INT < 29) goto L8;
    L11:
        return false;
    L8:
        if (CameraMetadata.Companion.isHardwareLevelLegacy(this.metadataProvider.mo456awaitCameraMetadataEfqyGwQ(r4)) == false) goto L11;
        return true;
    }

    /* renamed from: shouldWaitForCameraDeviceOnClosed-EfqyGwQ$camera_camera2_pipe, reason: not valid java name */
    public final boolean m461shouldWaitForCameraDeviceOnClosedEfqyGwQ$camera_camera2_pipe(String r2) {
        if (this.strictMode.getEnabled() == false) goto L7;
        return false;
    L7:
        return CameraMetadata.Companion.isHardwareLevelLegacy(this.metadataProvider.mo456awaitCameraMetadataEfqyGwQ(r2));
    }

    public final boolean shouldWaitForRepeatingRequestStartOnDisconnect$camera_camera2_pipe(CameraGraph.Config r2) {
        if (this.strictMode.getEnabled() == false) goto L6;
        return false;
    L6:
        Boolean r0 = r2.getFlags().getAwaitRepeatingRequestOnDisconnect();
        if (r0 == null) goto L11;
        return r0.booleanValue();
    L11:
        return CameraMetadata.Companion.isHardwareLevelLegacy(this.metadataProvider.mo456awaitCameraMetadataEfqyGwQ(r2.m200getCameraDz_R5H8()));
    }
}
