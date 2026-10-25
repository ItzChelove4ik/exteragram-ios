package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.compat.quirk.AspectRatioLegacyApi21Quirk;
import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.Nexus4AndroidLTargetAspectRatioQuirk;
import androidx.camera.camera2.pipe.CameraMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/workaround/TargetAspectRatio;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;", "streamConfigurationMapCompat", _UrlKt.FRAGMENT_ENCODE_SET, "get", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;)I", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TargetAspectRatio {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/workaround/TargetAspectRatio$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "RATIO_4_3", _UrlKt.FRAGMENT_ENCODE_SET, "RATIO_16_9", "RATIO_MAX_JPEG", "RATIO_ORIGINAL", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public TargetAspectRatio() {
    }

    public final int get(CameraMetadata r1, StreamConfigurationMapCompat r2) {
        CameraQuirks r0 = new CameraQuirks(r1, r2);
        Nexus4AndroidLTargetAspectRatioQuirk r12 = (Nexus4AndroidLTargetAspectRatioQuirk) DeviceQuirks.INSTANCE.get(Nexus4AndroidLTargetAspectRatioQuirk.class);
        if (r12 != null) goto L5;
        AspectRatioLegacyApi21Quirk r02 = (AspectRatioLegacyApi21Quirk) r0.getQuirks().get(AspectRatioLegacyApi21Quirk.class);
        if (r02 != null) goto L9;
        return 3;
    L9:
        return r02.getCorrectedAspectRatio();
    L5:
        return r12.getCorrectedAspectRatio();
    }
}
