package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 \b2\u00020\u00012\u00020\u0002:\u0001\bB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ImageCaptureFailedWhenVideoCaptureIsBoundQuirk;", "Landroidx/camera/camera2/compat/quirk/CaptureIntentPreviewQuirk;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "workaroundByCaptureIntentPreview", "()Z", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class ImageCaptureFailedWhenVideoCaptureIsBoundQuirk implements CaptureIntentPreviewQuirk, Quirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007R\u0014\u0010\t\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007R\u0014\u0010\u000b\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0007R\u0014\u0010\r\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ImageCaptureFailedWhenVideoCaptureIsBoundQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isBluStudioX10", "()Z", "isItelW6004", "isVivo1805", "isPositivoTwist2Pro", "isPixel4XLApi29", "isMotoE13", "isSamsungTabA8", "isSamsungA53", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ boolean access$isBluStudioX10(Companion r0) {
            return r0.isBluStudioX10();
        }

        public static final /* synthetic */ boolean access$isItelW6004(Companion r0) {
            return r0.isItelW6004();
        }

        public static final /* synthetic */ boolean access$isPositivoTwist2Pro(Companion r0) {
            return r0.isPositivoTwist2Pro();
        }

        public static final /* synthetic */ boolean access$isVivo1805(Companion r0) {
            return r0.isVivo1805();
        }

        private final boolean isBluStudioX10() {
            if (Device.INSTANCE.isBluDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("studio x10", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isItelW6004() {
            if (Device.INSTANCE.isItelDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("itel w6004", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isMotoE13() {
            if (Device.INSTANCE.isMotorolaDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("moto e13", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isPixel4XLApi29() {
            if (StringsKt.equals("pixel 4 xl", Build.MODEL, true) == true) goto L5;
            return false;
        L5:
            if (Build.VERSION.SDK_INT != 29) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isPositivoTwist2Pro() {
            if (Device.INSTANCE.isPositivoDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("twist 2 pro", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungA53() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
        L8:
            return false;
        L5:
            if (StringsKt.startsWith$default(Build.MODEL, "SM-A536", false, 2, null) == false) goto L8;
            return true;
        }

        private final boolean isSamsungTabA8() {
            if (Device.INSTANCE.isSamsungDevice() == false) goto L9;
            String r2 = Build.DEVICE;
            if (StringsKt.equals("gta8", r2, true) == false) goto L7;
        L8:
            return true;
        L7:
            if (StringsKt.equals("gta8wifi", r2, true) == true) goto L8;
            return false;
        L9:
            return false;
        }

        private final boolean isVivo1805() {
            if (Device.INSTANCE.isVivoDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("vivo 1805", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isEnabled() {
            if (isBluStudioX10() == false) goto L5;
            return true;
        L5:
            if (isItelW6004() == false) goto L7;
            return true;
        L7:
            if (isVivo1805() == false) goto L9;
            return true;
        L9:
            if (isPositivoTwist2Pro() == false) goto L11;
            return true;
        L11:
            if (isPixel4XLApi29() == false) goto L13;
            return true;
        L13:
            if (isMotoE13() == false) goto L15;
            return true;
        L15:
            if (isSamsungTabA8() == false) goto L17;
            return true;
        L17:
            if (isSamsungA53() == false) goto L19;
            return true;
        L19:
            if (Device.INSTANCE.isUniSocChipsetDevice() == true) goto L32;
            return false;
        L32:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public ImageCaptureFailedWhenVideoCaptureIsBoundQuirk() {
    }

    @Override // androidx.camera.camera2.compat.quirk.CaptureIntentPreviewQuirk
    public boolean workaroundByCaptureIntentPreview() {
        Companion r1 = Companion;
        if (Companion.access$isBluStudioX10(r1) == false) goto L5;
        return true;
    L5:
        if (Companion.access$isItelW6004(r1) == false) goto L7;
        return true;
    L7:
        if (Companion.access$isVivo1805(r1) == false) goto L9;
        return true;
    L9:
        if (Companion.access$isPositivoTwist2Pro(r1) == true) goto L17;
        return false;
    L17:
        return true;
    }
}
