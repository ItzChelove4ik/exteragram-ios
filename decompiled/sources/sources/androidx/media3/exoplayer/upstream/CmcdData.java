package androidx.media3.exoplayer.upstream;

import android.net.Uri;
import androidx.media3.common.util.UriUtil;
import androidx.media3.datasource.DataSpec;
import com.google.common.base.Joiner;

/* loaded from: classes4.dex */
public abstract class CmcdData {
    private static final Joiner COMMA_JOINER = null;

    public static final class Factory {
        public abstract CmcdData createCmcdData();

        public abstract Factory setChunkDurationUs(long r1);

        public abstract Factory setNextObjectRequest(String r1);

        public abstract Factory setNextRangeRequest(String r1);

        public abstract Factory setObjectType(String r1);
    }

    static {
        COMMA_JOINER = Joiner.on(",");
    }

    public static Uri removeFromUri(Uri r2) {
        if (r2.getQueryParameter("CMCD") != null) goto L5;
        return r2;
    L5:
        return UriUtil.removeQueryParameter(r2, "CMCD");
    }

    public abstract DataSpec addToDataSpec(DataSpec r1);
}
