package androidx.media3.exoplayer.hls.playlist;

import androidx.media3.exoplayer.offline.FilterableManifest;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class HlsPlaylist implements FilterableManifest<HlsPlaylist> {
    public final String baseUri;
    public final boolean hasIndependentSegments;
    public final List<String> tags;

    public HlsPlaylist(String r1, List<String> r2, boolean r3) {
        this.baseUri = r1;
        this.tags = Collections.unmodifiableList(r2);
        this.hasIndependentSegments = r3;
    }
}
