package androidx.media3.exoplayer.drm;

import android.util.Pair;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class WidevineUtil {
    private static long getDurationRemainingSec(Map<String, String> r0, String r1) {
        if (r0 != null) goto L10;
        return -9223372036854775807L;
    L10:
        String r02 = r0.get(r1);     // Catch: NumberFormatException -> L9
        if (r02 == null) goto L12;
        return Long.parseLong(r02);
    L12:
        return -9223372036854775807L;
    L13:
        return -9223372036854775807L;
    }

    public static Pair<Long, Long> getLicenseDurationRemainingSec(DrmSession r4) {
        Map<String, String> r42 = r4.queryKeyStatus();
        if (r42 != null) goto L7;
        return null;
    L7:
        return new Pair(Long.valueOf(getDurationRemainingSec(r42, "LicenseDurationRemaining")), Long.valueOf(getDurationRemainingSec(r42, "PlaybackDurationRemaining")));
    }
}
