package androidx.camera.camera2.compat;

import android.hardware.camera2.params.DynamicRangeProfiles;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.DynamicRange;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00122\u00020\u0001:\u0002\u0013\u0012B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000eR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat$DynamicRangeProfilesCompatImpl;", "impl", "<init>", "(Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat$DynamicRangeProfilesCompatImpl;)V", "Landroidx/camera/core/DynamicRange;", "dynamicRange", _UrlKt.FRAGMENT_ENCODE_SET, "getDynamicRangeCaptureRequestConstraints", "(Landroidx/camera/core/DynamicRange;)Ljava/util/Set;", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "toDynamicRangeProfiles", "()Landroid/hardware/camera2/params/DynamicRangeProfiles;", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat$DynamicRangeProfilesCompatImpl;", "getSupportedDynamicRanges", "()Ljava/util/Set;", "supportedDynamicRanges", "Companion", "DynamicRangeProfilesCompatImpl", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDynamicRangeProfilesCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DynamicRangeProfilesCompat.kt\nandroidx/camera/camera2/compat/DynamicRangeProfilesCompat\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/DebugKt\n*L\n1#1,148:1\n253#2,4:149\n*S KotlinDebug\n*F\n+ 1 DynamicRangeProfilesCompat.kt\nandroidx/camera/camera2/compat/DynamicRangeProfilesCompat\n*L\n86#1:149,4\n*E\n"})
/* loaded from: classes3.dex */
public final class DynamicRangeProfilesCompat {
    public static final Companion Companion = null;
    private final DynamicRangeProfilesCompatImpl impl;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromCameraMetaData", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "toDynamicRangesCompat", "dynamicRangeProfiles", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDynamicRangeProfilesCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DynamicRangeProfilesCompat.kt\nandroidx/camera/camera2/compat/DynamicRangeProfilesCompat$Companion\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/DebugKt\n*L\n1#1,148:1\n253#2,4:149\n*S KotlinDebug\n*F\n+ 1 DynamicRangeProfilesCompat.kt\nandroidx/camera/camera2/compat/DynamicRangeProfilesCompat$Companion\n*L\n137#1:149,4\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final DynamicRangeProfilesCompat fromCameraMetaData(CameraMetadata r3) {
            if (Build.VERSION.SDK_INT < 33) goto L5;
            DynamicRangeProfilesCompat r2 = toDynamicRangesCompat(DynamicRangeProfilesCompat$Companion$$ExternalSyntheticApiModelOutline1.m(r3.get(DynamicRangeProfilesCompat$Companion$$ExternalSyntheticApiModelOutline0.m())));
        L6:
            if (r2 == null) goto L8;
            return r2;
        L8:
            return DynamicRangeProfilesCompatBaseImpl.Companion.getCOMPAT_INSTANCE();
        L5:
            r2 = null;
            goto L6
        }

        public final DynamicRangeProfilesCompat toDynamicRangesCompat(DynamicRangeProfiles r3) {
            if (r3 != null) goto L5;
            return null;
        L5:
            int r0 = Build.VERSION.SDK_INT;
            if (r0 >= 33) goto L8;
            DynamicRangeProfilesCompat$$ExternalSyntheticBUOutline0.m("DynamicRangeProfiles can only be converted to DynamicRangesCompat on API 33 or higher. is not supported on API ", r0, " (requires API 33)");
            return null;
        L8:
            return new DynamicRangeProfilesCompat(new DynamicRangeProfilesCompatApi33Impl(r3));
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001J\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat$DynamicRangeProfilesCompatImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/DynamicRange;", "dynamicRange", _UrlKt.FRAGMENT_ENCODE_SET, "getDynamicRangeCaptureRequestConstraints", "(Landroidx/camera/core/DynamicRange;)Ljava/util/Set;", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "unwrap", "()Landroid/hardware/camera2/params/DynamicRangeProfiles;", "getSupportedDynamicRanges", "()Ljava/util/Set;", "supportedDynamicRanges", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface DynamicRangeProfilesCompatImpl {
        Set<DynamicRange> getDynamicRangeCaptureRequestConstraints(DynamicRange r1);

        Set<DynamicRange> getSupportedDynamicRanges();

        DynamicRangeProfiles unwrap();
    }

    static {
        Companion = new Companion(null);
    }

    public DynamicRangeProfilesCompat(DynamicRangeProfilesCompatImpl r1) {
        this.impl = r1;
    }

    public final Set<DynamicRange> getDynamicRangeCaptureRequestConstraints(DynamicRange r1) {
        return this.impl.getDynamicRangeCaptureRequestConstraints(r1);
    }

    public final Set<DynamicRange> getSupportedDynamicRanges() {
        return this.impl.getSupportedDynamicRanges();
    }

    public final DynamicRangeProfiles toDynamicRangeProfiles() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 33) goto L5;
        DynamicRangeProfilesCompat$$ExternalSyntheticBUOutline0.m("DynamicRangesCompat can only be converted to DynamicRangeProfiles on API 33 or higher. is not supported on API ", r0, " (requires API 33)");
        return null;
    L5:
        return this.impl.unwrap();
    }
}
