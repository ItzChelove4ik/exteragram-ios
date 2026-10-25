package androidx.camera.camera2.compat.quirk;

import android.os.Build;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.SurfaceConfig;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExtraCroppingQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "getVerifiedResolution", "Landroid/util/Size;", "configType", "Landroidx/camera/core/impl/SurfaceConfig$ConfigType;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExtraCroppingQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final Map<String, Range<Integer>> SAMSUNG_DISTORTION_MODELS_TO_API_LEVEL_MAP = null;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nR\"\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExtraCroppingQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SAMSUNG_DISTORTION_MODELS_TO_API_LEVEL_MAP", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isSamsungDistortion", "isSamsungDistortion$camera_camera2", "()Z", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled() {
            return isSamsungDistortion$camera_camera2();
        }

        public final boolean isSamsungDistortion$camera_camera2() {
            if (Device.INSTANCE.isSamsungDevice() == false) goto L12;
            Map r3 = ExtraCroppingQuirk.access$getSAMSUNG_DISTORTION_MODELS_TO_API_LEVEL_MAP$cp();
            String r0 = Build.MODEL;
            Locale r1 = Locale.ROOT;
            if (r3.containsKey(r0.toUpperCase(r1)) == false) goto L14;
            Range r32 = (Range) ExtraCroppingQuirk.access$getSAMSUNG_DISTORTION_MODELS_TO_API_LEVEL_MAP$cp().get(r0.toUpperCase(r1));
            if (r32 != null) goto L9;
            return true;
        L9:
            return r32.contains(Integer.valueOf(Build.VERSION.SDK_INT));
        L14:
            return false;
        L12:
            return false;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[SurfaceConfig.ConfigType.values().length];
            r0[SurfaceConfig.ConfigType.PRIV.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[SurfaceConfig.ConfigType.YUV.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[SurfaceConfig.ConfigType.JPEG.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
        SAMSUNG_DISTORTION_MODELS_TO_API_LEVEL_MAP = MapsKt.mutableMapOf(new Pair[]{TuplesKt.to("SM-T580", null), TuplesKt.to("SM-J710MN", new Range(21, 26)), TuplesKt.to("SM-A320FL", null), TuplesKt.to("SM-G570M", null), TuplesKt.to("SM-G610F", null), TuplesKt.to("SM-G610M", new Range(21, 26))});
    }

    public ExtraCroppingQuirk() {
    }

    public static final /* synthetic */ Map access$getSAMSUNG_DISTORTION_MODELS_TO_API_LEVEL_MAP$cp() {
        return SAMSUNG_DISTORTION_MODELS_TO_API_LEVEL_MAP;
    }

    public final Size getVerifiedResolution(SurfaceConfig.ConfigType r2) {
        if (Companion.isSamsungDistortion$camera_camera2() == false) goto L17;
        int r1 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r1 == 1) goto L16;
        if (r1 == 2) goto L14;
        if (r1 == 3) goto L12;
        return null;
    L12:
        return new Size(3264, 1836);
    L14:
        return new Size(1280, 720);
    L16:
        return new Size(1920, 1080);
    L17:
        return null;
    }
}
