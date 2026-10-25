package androidx.media3.common;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class BasePlayer implements Player {
    protected final Timeline.Window window;

    public BasePlayer() {
        this.window = new Timeline.Window();
    }

    private int getRepeatModeForNavigation() {
        int r1 = getRepeatMode();
        if (r1 != 1) goto L6;
        return 0;
    L6:
        return r1;
    }

    private void ignoreSeek(int r7) {
        seekTo(-1, -9223372036854775807L, r7, false);
    }

    private void repeatCurrentMediaItem(int r7) {
        seekTo(getCurrentMediaItemIndex(), -9223372036854775807L, r7, true);
    }

    private void seekToCurrentItem(long r7, int r9) {
        seekTo(getCurrentMediaItemIndex(), r7, r9, false);
    }

    private void seekToDefaultPositionInternal(int r7, int r8) {
        seekTo(r7, -9223372036854775807L, r8, false);
    }

    private void seekToNextMediaItemInternal(int r3) {
        int r0 = getNextMediaItemIndex();
        if (r0 != (-1)) goto L7;
        ignoreSeek(r3);
        return;
    L7:
        if (r0 != getCurrentMediaItemIndex()) goto L10;
        repeatCurrentMediaItem(r3);
        return;
    L10:
        seekToDefaultPositionInternal(r0, r3);
    }

    private void seekToOffset(long r5, int r7) {
        long r0 = getCurrentPosition() + r5;
        long r52 = getDuration();
        if (r52 == (-9223372036854775807L)) goto L5;
        r0 = Math.min(r0, r52);
    L5:
        seekToCurrentItem(Math.max(r0, 0), r7);
    }

    private void seekToPreviousMediaItemInternal(int r3) {
        int r0 = getPreviousMediaItemIndex();
        if (r0 != (-1)) goto L7;
        ignoreSeek(r3);
        return;
    L7:
        if (r0 != getCurrentMediaItemIndex()) goto L10;
        repeatCurrentMediaItem(r3);
        return;
    L10:
        seekToDefaultPositionInternal(r0, r3);
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItems(List<MediaItem> r2) {
        addMediaItems(Integer.MAX_VALUE, r2);
    }

    @Override // androidx.media3.common.Player
    public final boolean canAdvertiseSession() {
        return true;
    }

    @Override // androidx.media3.common.Player
    public final void clearMediaItems() {
        removeMediaItems(0, Integer.MAX_VALUE);
    }

    @Override // androidx.media3.common.Player
    public final int getBufferedPercentage() {
        long r0 = getBufferedPosition();
        long r2 = getDuration();
        if (r0 != (-9223372036854775807L)) goto L5;
    L12:
        return 0;
    L5:
        if (r2 == (-9223372036854775807L)) goto L12;
        if (r2 != 0) goto L11;
        return 100;
    L11:
        return Util.constrainValue(Util.percentInt(r0, r2), 0, 100);
    }

    @Override // androidx.media3.common.Player
    public final long getContentDuration() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L7;
        return -9223372036854775807L;
    L7:
        return r0.getWindow(getCurrentMediaItemIndex(), this.window).getDurationMs();
    }

    @Override // androidx.media3.common.Player
    public final long getCurrentLiveOffset() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L6;
        return -9223372036854775807L;
    L6:
        if (r0.getWindow(getCurrentMediaItemIndex(), this.window).windowStartTimeMs != (-9223372036854775807L)) goto L9;
        return -9223372036854775807L;
    L9:
        return (this.window.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - getContentPosition();
    }

    @Override // androidx.media3.common.Player
    public final MediaItem getCurrentMediaItem() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L7;
        return null;
    L7:
        return r0.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem;
    }

    public final int getNextMediaItemIndex() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L7;
        return -1;
    L7:
        return r0.getNextWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    public final int getPreviousMediaItemIndex() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L7;
        return -1;
    L7:
        return r0.getPreviousWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    @Override // androidx.media3.common.Player
    public final boolean hasNextMediaItem() {
        if (getNextMediaItemIndex() == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.common.Player
    public final boolean hasPreviousMediaItem() {
        if (getPreviousMediaItemIndex() == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.common.Player
    public final boolean isCommandAvailable(int r1) {
        return getAvailableCommands().contains(r1);
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemDynamic() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L5;
        return false;
    L5:
        if (r0.getWindow(getCurrentMediaItemIndex(), this.window).isDynamic == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemLive() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L5;
        return false;
    L5:
        if (r0.getWindow(getCurrentMediaItemIndex(), this.window).isLive() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemSeekable() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == false) goto L5;
        return false;
    L5:
        if (r0.getWindow(getCurrentMediaItemIndex(), this.window).isSeekable == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.common.Player
    public final boolean isPlaying() {
        if (getPlaybackState() == 3) goto L5;
        return false;
    L5:
        if (getPlayWhenReady() == true) goto L7;
        return false;
    L7:
        if (getPlaybackSuppressionReason() != 0) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.media3.common.Player
    public final void moveMediaItem(int r2, int r3) {
        if (r2 == r3) goto L5;
        moveMediaItems(r2, r2 + 1, r3);
        return;
    }

    @Override // androidx.media3.common.Player
    public final void pause() {
        setPlayWhenReady(false);
    }

    @Override // androidx.media3.common.Player
    public final void play() {
        setPlayWhenReady(true);
    }

    @Override // androidx.media3.common.Player
    public final void removeMediaItem(int r2) {
        removeMediaItems(r2, r2 + 1);
    }

    @Override // androidx.media3.common.Player
    public final void replaceMediaItem(int r2, MediaItem r3) {
        replaceMediaItems(r2, r2 + 1, ImmutableList.of(r3));
    }

    @Override // androidx.media3.common.Player
    public final void seekBack() {
        seekToOffset(-getSeekBackIncrement(), 11);
    }

    @Override // androidx.media3.common.Player
    public final void seekForward() {
        seekToOffset(getSeekForwardIncrement(), 12);
    }

    @Override // androidx.media3.common.Player
    public final void seekTo(int r7, long r8) {
        seekTo(r7, r8, 10, false);
    }

    public abstract void seekTo(int r1, long r2, int r4, boolean r5);

    @Override // androidx.media3.common.Player
    public final void seekTo(long r2) {
        seekToCurrentItem(r2, 5);
    }

    @Override // androidx.media3.common.Player
    public final void seekToDefaultPosition() {
        seekToDefaultPositionInternal(getCurrentMediaItemIndex(), 4);
    }

    @Override // androidx.media3.common.Player
    public final void seekToDefaultPosition(int r2) {
        seekToDefaultPositionInternal(r2, 10);
    }

    @Override // androidx.media3.common.Player
    public final void seekToNext() {
        if (getCurrentTimeline().isEmpty() == false) goto L5;
    L19:
        ignoreSeek(9);
        return;
    L5:
        if (isPlayingAd() == true) goto L19;
        if (hasNextMediaItem() == false) goto L12;
        seekToNextMediaItemInternal(9);
        return;
    L12:
        if (isCurrentMediaItemLive() == true) goto L14;
    L17:
        ignoreSeek(9);
        return;
    L14:
        if (isCurrentMediaItemDynamic() == false) goto L17;
        seekToDefaultPositionInternal(getCurrentMediaItemIndex(), 9);
    }

    @Override // androidx.media3.common.Player
    public final void seekToNextMediaItem() {
        seekToNextMediaItemInternal(8);
    }

    @Override // androidx.media3.common.Player
    public final void seekToPrevious() {
        if (getCurrentTimeline().isEmpty() == false) goto L5;
    L23:
        ignoreSeek(7);
        return;
    L5:
        if (isPlayingAd() == true) goto L23;
        boolean r0 = hasPreviousMediaItem();
        if (isCurrentMediaItemLive() == true) goto L10;
    L16:
        if (r0 == true) goto L18;
    L21:
        seekToCurrentItem(0, 7);
        return;
    L18:
        if (getCurrentPosition() > getMaxSeekToPreviousPosition()) goto L21;
        seekToPreviousMediaItemInternal(7);
        return;
    L10:
        if (isCurrentMediaItemSeekable() == true) goto L16;
        if (r0 == false) goto L14;
        seekToPreviousMediaItemInternal(7);
        return;
    L14:
        ignoreSeek(7);
    }

    @Override // androidx.media3.common.Player
    public final void seekToPreviousMediaItem() {
        seekToPreviousMediaItemInternal(6);
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem r1) {
        setMediaItems(ImmutableList.of(r1));
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem r2, long r3) {
        setMediaItems(ImmutableList.of(r2), 0, r3);
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem r1, boolean r2) {
        setMediaItems(ImmutableList.of(r1), r2);
    }

    public final void setMediaItems(List<MediaItem> r2) {
        setMediaItems(r2, true);
    }

    @Override // androidx.media3.common.Player
    public final void setPlaybackSpeed(float r2) {
        setPlaybackParameters(getPlaybackParameters().withSpeed(r2));
    }
}
