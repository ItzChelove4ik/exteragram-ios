package androidx.camera.camera2.pipe;

import android.graphics.ColorSpace;
import android.os.Build;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\b\u0087@\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000e\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/CameraColorSpace;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "colorSpaceName", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "Landroid/graphics/ColorSpace$Named;", "toColorSpaceNamed-impl", "(Ljava/lang/String;)Landroid/graphics/ColorSpace$Named;", "toColorSpaceNamed", "toString-impl", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(Ljava/lang/String;)I", "hashCode", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public abstract class CameraColorSpace {
    private static final String ACES = null;
    private static final String ACESCG = null;
    private static final String ADOBE_RGB = null;
    private static final String BT2020 = null;
    private static final String BT2020_HLG = null;
    private static final String BT2020_PQ = null;
    private static final String BT709 = null;
    private static final String CIE_LAB = null;
    private static final String CIE_XYZ = null;
    public static final Companion Companion = null;
    private static final String DCI_P3 = null;
    private static final String DISPLAY_P3 = null;
    private static final String EXTENDED_SRGB = null;
    private static final String LINEAR_EXTENDED_SRGB = null;
    private static final String LINEAR_SRGB = null;
    private static final String NTSC_1953 = null;
    private static final String PRO_PHOTO_RGB = null;
    private static final String SMPTE_C = null;
    private static final String SRGB = null;
    private static final String UNKNOWN = null;

    @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraColorSpace$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        UNKNOWN = m159constructorimpl("UNKNOWN");
        SRGB = m159constructorimpl("SRGB");
        LINEAR_SRGB = m159constructorimpl("LINEAR_SRGB");
        EXTENDED_SRGB = m159constructorimpl("EXTENDED_SRGB");
        LINEAR_EXTENDED_SRGB = m159constructorimpl("LINEAR_EXTENDED_SRGB");
        BT709 = m159constructorimpl("BT709");
        BT2020 = m159constructorimpl("BT2020");
        DCI_P3 = m159constructorimpl("DCI_P3");
        DISPLAY_P3 = m159constructorimpl("DISPLAY_P3");
        NTSC_1953 = m159constructorimpl("NTSC_1953");
        SMPTE_C = m159constructorimpl("SMPTE_C");
        ADOBE_RGB = m159constructorimpl("ADOBE_RGB");
        PRO_PHOTO_RGB = m159constructorimpl("PRO_PHOTO_RGB");
        ACES = m159constructorimpl("ACES");
        ACESCG = m159constructorimpl("ACESCG");
        CIE_XYZ = m159constructorimpl("CIE_XYZ");
        CIE_LAB = m159constructorimpl("CIE_LAB");
        BT2020_HLG = m159constructorimpl("BT2020_HLG");
        BT2020_PQ = m159constructorimpl("BT2020_PQ");
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    private static String m159constructorimpl(String r0) {
        return r0;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m160equalsimpl0(String r0, String r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m161hashCodeimpl(String r0) {
        return r0.hashCode();
    }

    /* renamed from: toColorSpaceNamed-impl, reason: not valid java name */
    public static final ColorSpace.Named m162toColorSpaceNamedimpl(String r3) {
        if (m160equalsimpl0(r3, UNKNOWN) == false) goto L6;
        return null;
    L6:
        if (m160equalsimpl0(r3, SRGB) == false) goto L10;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline0.m();
    L10:
        if (m160equalsimpl0(r3, LINEAR_SRGB) == false) goto L14;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline9.m();
    L14:
        if (m160equalsimpl0(r3, EXTENDED_SRGB) == false) goto L18;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline10.m();
    L18:
        if (m160equalsimpl0(r3, LINEAR_EXTENDED_SRGB) == false) goto L22;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline11.m();
    L22:
        if (m160equalsimpl0(r3, BT709) == false) goto L26;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline12.m();
    L26:
        if (m160equalsimpl0(r3, BT2020) == false) goto L30;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline13.m();
    L30:
        if (m160equalsimpl0(r3, DCI_P3) == false) goto L34;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline14.m();
    L34:
        if (m160equalsimpl0(r3, DISPLAY_P3) == false) goto L38;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline15.m();
    L38:
        if (m160equalsimpl0(r3, NTSC_1953) == false) goto L42;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline16.m();
    L42:
        if (m160equalsimpl0(r3, SMPTE_C) == false) goto L46;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline17.m();
    L46:
        if (m160equalsimpl0(r3, ADOBE_RGB) == false) goto L50;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline1.m();
    L50:
        if (m160equalsimpl0(r3, PRO_PHOTO_RGB) == false) goto L54;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline2.m();
    L54:
        if (m160equalsimpl0(r3, ACES) == false) goto L58;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline3.m();
    L58:
        if (m160equalsimpl0(r3, ACESCG) == false) goto L62;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline4.m();
    L62:
        if (m160equalsimpl0(r3, CIE_XYZ) == false) goto L66;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline5.m();
    L66:
        if (m160equalsimpl0(r3, CIE_LAB) == false) goto L70;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline6.m();
    L70:
        if (Build.VERSION.SDK_INT >= 34) goto L73;
        return null;
    L73:
        if (m160equalsimpl0(r3, BT2020_HLG) == false) goto L77;
        return CameraColorSpace$$ExternalSyntheticApiModelOutline7.m();
    L77:
        if (m160equalsimpl0(r3, BT2020_PQ) == true) goto L79;
        return null;
    L79:
        return CameraColorSpace$$ExternalSyntheticApiModelOutline8.m();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m163toStringimpl(String r2) {
        return "CameraColorSpace(colorSpaceName=" + r2 + ')';
    }
}
