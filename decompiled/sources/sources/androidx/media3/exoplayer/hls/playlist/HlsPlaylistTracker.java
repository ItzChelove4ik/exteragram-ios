package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import androidx.media3.exoplayer.hls.HlsDataSourceFactory;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.io.IOException;

/* loaded from: classes4.dex */
public interface HlsPlaylistTracker {

    public interface Factory {
        HlsPlaylistTracker createTracker(HlsDataSourceFactory r1, LoadErrorHandlingPolicy r2, HlsPlaylistParserFactory r3, CmcdConfiguration r4);
    }

    public interface PlaylistEventListener {
        void onPlaylistChanged();

        boolean onPlaylistError(Uri r1, LoadErrorHandlingPolicy.LoadErrorInfo r2, boolean r3);
    }

    public static final class PlaylistResetException extends IOException {
        public final Uri url;

        public PlaylistResetException(Uri r1) {
            this.url = r1;
        }
    }

    public static final class PlaylistStuckException extends IOException {
        public final Uri url;

        public PlaylistStuckException(Uri r1) {
            this.url = r1;
        }
    }

    public interface PrimaryPlaylistListener {
        void onPrimaryPlaylistRefreshed(HlsMediaPlaylist r1);
    }

    void addListener(PlaylistEventListener r1);

    void deactivatePlaylistForPlayback(Uri r1);

    boolean excludeMediaPlaylist(Uri r1, long r2);

    long getInitialStartTimeUs();

    HlsMultivariantPlaylist getMultivariantPlaylist();

    HlsMediaPlaylist getPlaylistSnapshot(Uri r1, boolean r2);

    boolean isLive();

    boolean isSnapshotValid(Uri r1);

    void maybeThrowPlaylistRefreshError(Uri r1);

    void maybeThrowPrimaryPlaylistRefreshError();

    void refreshPlaylist(Uri r1);

    void removeListener(PlaylistEventListener r1);

    void start(Uri r1, MediaSourceEventListener.EventDispatcher r2, PrimaryPlaylistListener r3);

    void stop();
}
