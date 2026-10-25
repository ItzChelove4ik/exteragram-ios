package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Size;
import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.Quirk;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CamcorderProfileResolutionQuirk;", "Landroidx/camera/core/impl/Quirk;", "streamConfigurationMapCompat", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;", "<init>", "(Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;)V", "supportedResolution", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "getSupportedResolution", "()Ljava/util/List;", "supportedResolution$delegate", "Lkotlin/Lazy;", "getSupportedResolutions", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
@SourceDebugExtension({"SMAP\nCamcorderProfileResolutionQuirk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CamcorderProfileResolutionQuirk.kt\nandroidx/camera/camera2/compat/quirk/CamcorderProfileResolutionQuirk\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,76:1\n85#2,4:77\n*S KotlinDebug\n*F\n+ 1 CamcorderProfileResolutionQuirk.kt\nandroidx/camera/camera2/compat/quirk/CamcorderProfileResolutionQuirk\n*L\n62#1:77,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CamcorderProfileResolutionQuirk implements Quirk {
    public static final Companion Companion = null;
    private final StreamConfigurationMapCompat streamConfigurationMapCompat;
    private final Lazy supportedResolution$delegate;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CamcorderProfileResolutionQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled(CameraMetadata r1) {
            return CameraMetadata.Companion.isHardwareLevelLegacy(r1);
        }
    }

    public static List $r8$lambda$bXAkRXoJlahpxssqlwQqYY8ViaU(CamcorderProfileResolutionQuirk r3) {
        Size[] r32 = r3.streamConfigurationMapCompat.getOutputSizes(34);
        if (r32 == null) goto L6;
        List r33 = ArraysKt.asList(r32);
        if (r33 == null) goto L6;
    L7:
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L10;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "supportedResolutions = " + r33);
    L10:
        return r33;
    L6:
        r33 = CollectionsKt.emptyList();
        goto L7
    }

    static {
        Companion = new Companion(null);
    }

    public CamcorderProfileResolutionQuirk(StreamConfigurationMapCompat r1) {
        this.streamConfigurationMapCompat = r1;
        this.supportedResolution$delegate = LazyKt.lazy(new CamcorderProfileResolutionQuirk$$ExternalSyntheticLambda0(this));
    }

    private final List<Size> getSupportedResolution() {
        return (List) this.supportedResolution$delegate.getValue();
    }

    public final List<Size> getSupportedResolutions() {
        return CollectionsKt.toList(getSupportedResolution());
    }
}
