package com.exteragram.messenger.nowplaying.ui.components;

import androidx.media3.common.Player;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1", "Landroidx/media3/common/Player$Listener;", "onIsPlayingChanged", _UrlKt.FRAGMENT_ENCODE_SET, "isPlaying", _UrlKt.FRAGMENT_ENCODE_SET, "onPlaybackStateChanged", "playbackState", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingCard$initializePlayer$1$1 implements Player.Listener {
    final /* synthetic */ NowPlayingCard this$0;

    public NowPlayingCard$initializePlayer$1$1(NowPlayingCard r1) {
        this.this$0 = r1;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onIsPlayingChanged(boolean r2) {
        NowPlayingCard.access$setPlaying$p(this.this$0, r2);
        NowPlayingCard.access$updatePlayPauseButton(this.this$0);
        if (r2 == true) goto L6;
        NowPlayingCard.access$abandonAudioFocus(this.this$0);
        return;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlaybackStateChanged(int r2) {
        if (r2 != 4) goto L6;
        NowPlayingCard.access$abandonAudioFocus(this.this$0);
        return;
    }
}
