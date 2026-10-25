package androidx.media3.exoplayer.dash;

import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class DashUtil {
    public static DataSpec buildDataSpec(Representation r2, String r3, RangedUri r4, int r5, Map<String, String> r6) {
        return new DataSpec.Builder().setUri(r4.resolveUri(r3)).setPosition(r4.start).setLength(r4.length).setKey(resolveCacheKey(r2, r4)).setFlags(r5).setHttpRequestHeaders(r6).build();
    }

    public static String resolveCacheKey(Representation r1, RangedUri r2) {
        String r0 = r1.getCacheKey();
        if (r0 == null) goto L6;
        return r0;
    L6:
        return r2.resolveUri(r1.baseUrls.get(0).url).toString();
    }
}
