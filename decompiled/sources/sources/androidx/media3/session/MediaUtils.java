package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserServiceCompat$BrowserRoot;
import com.google.common.collect.ImmutableList;

/* loaded from: classes4.dex */
abstract class MediaUtils {
    public static final MediaBrowserServiceCompat$BrowserRoot defaultBrowserRoot = null;

    static {
        defaultBrowserRoot = new MediaBrowserServiceCompat$BrowserRoot("androidx.media3.session.MediaLibraryService", null);
    }

    public static boolean areSessionPositionInfosInSamePeriodOrAd(SessionPositionInfo r2, SessionPositionInfo r3) {
        Player.PositionInfo r22 = r2.positionInfo;
        int r0 = r22.mediaItemIndex;
        Player.PositionInfo r32 = r3.positionInfo;
        if (r0 == r32.mediaItemIndex) goto L5;
        return false;
    L5:
        if (r22.periodIndex == r32.periodIndex) goto L7;
        return false;
    L7:
        if (r22.adGroupIndex == r32.adGroupIndex) goto L9;
        return false;
    L9:
        if (r22.adIndexInAdGroup != r32.adIndexInAdGroup) goto L16;
        return true;
    L16:
        return false;
    }

    public static Player.Commands intersect(Player.Commands r3, Player.Commands r4) {
        if (r3 == null) goto L15;
        if (r4 == null) goto L15;
        Player.Commands.Builder r0 = new Player.Commands.Builder();
        int r1 = 0;
    L7:
        if (r1 >= r3.size()) goto L13;
        if (r4.contains(r3.get(r1)) == false) goto L11;
        r0.add(r3.get(r1));
    L11:
        r1 = r1 + 1;
        goto L7
    L13:
        return r0.build();
    L15:
        return Player.Commands.EMPTY;
    }

    public static void setMediaItemsWithStartIndexAndPosition(Player r4, MediaSession.MediaItemsWithStartPosition r5) {
        if (r5.startIndex != (-1)) goto L12;
        boolean r0 = r4.isCommandAvailable(20);
        ImmutableList<MediaItem> r1 = r5.mediaItems;
        if (r0 == false) goto L9;
        r4.setMediaItems(r1, true);
        return;
    L9:
        if (r1.isEmpty() == true) goto L20;
        r4.setMediaItem(r5.mediaItems.get(0), true);
        return;
    L20:
        return;
    L12:
        boolean r02 = r4.isCommandAvailable(20);
        ImmutableList<MediaItem> r12 = r5.mediaItems;
        if (r02 == false) goto L17;
        r4.setMediaItems(r12, r5.startIndex, r5.startPositionMs);
        return;
    L17:
        if (r12.isEmpty() == true) goto L21;
        r4.setMediaItem(r5.mediaItems.get(0), r5.startPositionMs);
        return;
    }
}
