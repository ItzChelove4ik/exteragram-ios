package androidx.media3.exoplayer.hls.playlist;

import androidx.media3.exoplayer.hls.HlsDataSourceFactory;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultHlsPlaylistTracker$$ExternalSyntheticLambda0 implements HlsPlaylistTracker.Factory {
    public /* synthetic */ DefaultHlsPlaylistTracker$$ExternalSyntheticLambda0() {
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.Factory
    public final HlsPlaylistTracker createTracker(HlsDataSourceFactory r1, LoadErrorHandlingPolicy r2, HlsPlaylistParserFactory r3, CmcdConfiguration r4) {
        return new DefaultHlsPlaylistTracker(r1, r2, r3, r4);
    }
}
