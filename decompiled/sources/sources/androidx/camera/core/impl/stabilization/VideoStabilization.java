package androidx.camera.core.impl.stabilization;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Landroidx/camera/core/impl/stabilization/VideoStabilization;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "UNSPECIFIED", "OFF", "ON", "PREVIEW", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum VideoStabilization extends Enum<VideoStabilization> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ VideoStabilization[] $VALUES = null;
    public static final Companion Companion = null;
    public static final VideoStabilization OFF = null;
    public static final VideoStabilization ON = null;
    public static final VideoStabilization PREVIEW = null;
    public static final VideoStabilization UNSPECIFIED = null;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/camera/core/impl/stabilization/VideoStabilization$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "from", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "previewStabilizationMode", _UrlKt.FRAGMENT_ENCODE_SET, "videoStabilizationMode", "from$camera_core", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final VideoStabilization from$camera_core(int r1, int r2) {
            if (r1 == 1) goto L16;
            if (r2 == 1) goto L16;
            if (r1 == 2) goto L9;
            if (r2 != 2) goto L14;
            return VideoStabilization.ON;
        L14:
            return VideoStabilization.UNSPECIFIED;
        L9:
            return VideoStabilization.PREVIEW;
        L16:
            return VideoStabilization.OFF;
        }
    }

    private static final /* synthetic */ VideoStabilization[] $values() {
        return new VideoStabilization[]{UNSPECIFIED, OFF, ON, PREVIEW};
    }

    static {
        UNSPECIFIED = new VideoStabilization("UNSPECIFIED", 0);
        OFF = new VideoStabilization("OFF", 1);
        ON = new VideoStabilization("ON", 2);
        PREVIEW = new VideoStabilization("PREVIEW", 3);
        VideoStabilization[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    VideoStabilization(String r1, int r2) {
    }

    public static VideoStabilization valueOf(String r1) {
        return (VideoStabilization) Enum.valueOf(VideoStabilization.class, r1);
    }

    public static VideoStabilization[] values() {
        return (VideoStabilization[]) $VALUES.clone();
    }
}
