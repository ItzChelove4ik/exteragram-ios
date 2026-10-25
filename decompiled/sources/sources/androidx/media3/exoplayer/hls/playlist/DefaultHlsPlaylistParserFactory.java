package androidx.media3.exoplayer.hls.playlist;

import androidx.media3.exoplayer.upstream.ParsingLoadable;

/* loaded from: classes4.dex */
public final class DefaultHlsPlaylistParserFactory implements HlsPlaylistParserFactory {
    public DefaultHlsPlaylistParserFactory() {
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistParserFactory
    public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser() {
        return new HlsPlaylistParser();
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistParserFactory
    public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(HlsMultivariantPlaylist r1, HlsMediaPlaylist r2) {
        return new HlsPlaylistParser(r1, r2);
    }
}
