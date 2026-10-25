package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;

/* loaded from: classes4.dex */
public abstract class WrappingMediaSource extends CompositeMediaSource<Void> {
    private static final Void CHILD_SOURCE_ID = null;
    protected final MediaSource mediaSource;

    static {
    }

    public WrappingMediaSource(MediaSource r1) {
        this.mediaSource = r1;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r1) {
        return this.mediaSource.canUpdateMediaItem(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public Timeline getInitialTimeline() {
        return this.mediaSource.getInitialTimeline();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId r1) {
        return r1;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void r1, MediaSource.MediaPeriodId r2) {
        return getMediaPeriodIdForChildMediaPeriodId2(r1, r2);
    }

    /* renamed from: getMediaPeriodIdForChildMediaPeriodId, reason: avoid collision after fix types in other method */
    public final MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId2(Void r1, MediaSource.MediaPeriodId r2) {
        return getMediaPeriodIdForChildMediaPeriodId(r2);
    }

    public long getMediaTimeForChildMediaTime(long r1, MediaSource.MediaPeriodId r3) {
        return r1;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ long getMediaTimeForChildMediaTime(Void r1, long r2, MediaSource.MediaPeriodId r4) {
        return getMediaTimeForChildMediaTime2(r1, r2, r4);
    }

    /* renamed from: getMediaTimeForChildMediaTime, reason: avoid collision after fix types in other method */
    public final long getMediaTimeForChildMediaTime2(Void r1, long r2, MediaSource.MediaPeriodId r4) {
        return getMediaTimeForChildMediaTime(r2, r4);
    }

    public int getWindowIndexForChildWindowIndex(int r1) {
        return r1;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ int getWindowIndexForChildWindowIndex(Void r1, int r2) {
        return getWindowIndexForChildWindowIndex2(r1, r2);
    }

    /* renamed from: getWindowIndexForChildWindowIndex, reason: avoid collision after fix types in other method */
    public final int getWindowIndexForChildWindowIndex2(Void r1, int r2) {
        return getWindowIndexForChildWindowIndex(r2);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public boolean isSingleWindow() {
        return this.mediaSource.isSingleWindow();
    }

    public abstract void onChildSourceInfoRefreshed(Timeline r1);

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ void onChildSourceInfoRefreshed(Void r1, MediaSource r2, Timeline r3) {
        onChildSourceInfoRefreshed2(r1, r2, r3);
    }

    /* renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method */
    public final void onChildSourceInfoRefreshed2(Void r1, MediaSource r2, Timeline r3) {
        onChildSourceInfoRefreshed(r3);
    }

    public final void prepareChildSource() {
        prepareChildSource(CHILD_SOURCE_ID, this.mediaSource);
    }

    public void prepareSourceInternal() {
        prepareChildSource();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public final void prepareSourceInternal(TransferListener r1) {
        super.prepareSourceInternal(r1);
        prepareSourceInternal();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem r1) {
        this.mediaSource.updateMediaItem(r1);
    }
}
