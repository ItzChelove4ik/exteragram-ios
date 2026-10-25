package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.impl.Quirk;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/TorchFlashRequiredFor3aUpdateQuirk;", "Landroidx/camera/core/impl/Quirk;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;)V", "Landroidx/camera/camera2/pipe/CameraMetadata;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class TorchFlashRequiredFor3aUpdateQuirk implements Quirk {
    private static final List<String> AFFECTED_PIXEL_MODELS = null;
    public static final Companion Companion = null;
    private final CameraMetadata cameraMetadata;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\bH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\u00020\b*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/TorchFlashRequiredFor3aUpdateQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "AFFECTED_PIXEL_MODELS", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "isAffectedModel", "isAffectedPixelModel", "isFrontCamera", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Z", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nTorchFlashRequiredFor3aUpdateQuirk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TorchFlashRequiredFor3aUpdateQuirk.kt\nandroidx/camera/camera2/compat/quirk/TorchFlashRequiredFor3aUpdateQuirk$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,85:1\n1869#2,2:86\n*S KotlinDebug\n*F\n+ 1 TorchFlashRequiredFor3aUpdateQuirk.kt\nandroidx/camera/camera2/compat/quirk/TorchFlashRequiredFor3aUpdateQuirk$Companion\n*L\n73#1:86,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isAffectedModel(CameraMetadata r2) {
            if (isAffectedPixelModel() == true) goto L5;
            return false;
        L5:
            if (isFrontCamera(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        private final boolean isAffectedPixelModel() {
            Iterator r3 = TorchFlashRequiredFor3aUpdateQuirk.access$getAFFECTED_PIXEL_MODELS$cp().iterator();
        L4:
            if (r3.hasNext() == false) goto L9;
            String r0 = (String) r3.next();
            if (Intrinsics.areEqual(Build.MODEL.toUpperCase(Locale.ROOT), r0) == false) goto L4;
            return true;
        L9:
            return false;
        }

        private final boolean isFrontCamera(CameraMetadata r1) {
            Integer r0 = (Integer) r1.get(CameraCharacteristics.LENS_FACING);
            if (r0 != null) goto L6;
            return false;
        L6:
            if (r0.intValue() != 0) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isEnabled(CameraMetadata r1) {
            return isAffectedModel(r1);
        }
    }

    static {
        Companion = new Companion(null);
        AFFECTED_PIXEL_MODELS = CollectionsKt.mutableListOf(new String[]{"PIXEL 6A", "PIXEL 6 PRO", "PIXEL 7", "PIXEL 7A", "PIXEL 7 PRO", "PIXEL 8", "PIXEL 8 PRO"});
    }

    public TorchFlashRequiredFor3aUpdateQuirk(CameraMetadata r1) {
        this.cameraMetadata = r1;
    }

    public static final /* synthetic */ List access$getAFFECTED_PIXEL_MODELS$cp() {
        return AFFECTED_PIXEL_MODELS;
    }
}
