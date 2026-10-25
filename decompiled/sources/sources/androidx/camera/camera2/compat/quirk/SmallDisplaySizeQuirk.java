package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Size;
import androidx.camera.core.impl.Quirk;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/SmallDisplaySizeQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "displaySize", "Landroid/util/Size;", "getDisplaySize", "()Landroid/util/Size;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class SmallDisplaySizeQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final Map<String, Size> MODEL_TO_DISPLAY_SIZE_MAP = null;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/compat/quirk/SmallDisplaySizeQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MODEL_TO_DISPLAY_SIZE_MAP", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "load", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean load() {
            return SmallDisplaySizeQuirk.access$getMODEL_TO_DISPLAY_SIZE_MAP$cp().containsKey(Build.MODEL.toUpperCase(Locale.ROOT));
        }
    }

    static {
        Companion = new Companion(null);
        MODEL_TO_DISPLAY_SIZE_MAP = MapsKt.mapOf(new Pair[]{TuplesKt.to("REDMI NOTE 8", new Size(1080, 2340)), TuplesKt.to("REDMI NOTE 7", new Size(1080, 2340)), TuplesKt.to("SM-A207M", new Size(720, 1560)), TuplesKt.to("REDMI NOTE 7S", new Size(1080, 2340)), TuplesKt.to("SM-A127F", new Size(720, 1600)), TuplesKt.to("SM-A536E", new Size(1080, 2400)), TuplesKt.to("220233L2I", new Size(720, 1600)), TuplesKt.to("V2149", new Size(720, 1600)), TuplesKt.to("VIVO 1920", new Size(1080, 2340)), TuplesKt.to("CPH2223", new Size(1080, 2400)), TuplesKt.to("V2029", new Size(720, 1600)), TuplesKt.to("CPH1901", new Size(720, 1520)), TuplesKt.to("REDMI Y3", new Size(720, 1520)), TuplesKt.to("SM-A045M", new Size(720, 1600)), TuplesKt.to("SM-A146U", new Size(1080, 2408)), TuplesKt.to("CPH1909", new Size(720, 1520)), TuplesKt.to("NOKIA 4.2", new Size(720, 1520)), TuplesKt.to("SM-G960U1", new Size(1440, 2960)), TuplesKt.to("SM-A137F", new Size(1080, 2408)), TuplesKt.to("VIVO 1816", new Size(720, 1520)), TuplesKt.to("INFINIX X6817", new Size(720, 1612)), TuplesKt.to("SM-A037F", new Size(720, 1600)), TuplesKt.to("NOKIA 2.4", new Size(720, 1600)), TuplesKt.to("SM-A125M", new Size(720, 1600)), TuplesKt.to("INFINIX X670", new Size(1080, 2400))});
    }

    public SmallDisplaySizeQuirk() {
    }

    public static final /* synthetic */ Map access$getMODEL_TO_DISPLAY_SIZE_MAP$cp() {
        return MODEL_TO_DISPLAY_SIZE_MAP;
    }

    public final Size getDisplaySize() {
        return MODEL_TO_DISPLAY_SIZE_MAP.get(Build.MODEL.toUpperCase(Locale.ROOT));
    }
}
