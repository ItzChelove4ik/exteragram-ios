package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;

/* loaded from: classes4.dex */
public final class TimelineWithUpdatedMediaItem extends ForwardingTimeline {
    private final MediaItem updatedMediaItem;

    public TimelineWithUpdatedMediaItem(Timeline r1, MediaItem r2) {
        super(r1);
        this.updatedMediaItem = r2;
    }

    @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
    public Timeline.Window getWindow(int r1, Timeline.Window r2, long r3) {
        super.getWindow(r1, r2, r3);
        MediaItem r0 = this.updatedMediaItem;
        r2.mediaItem = r0;
        MediaItem.LocalConfiguration r02 = r0.localConfiguration;
        if (r02 == null) goto L5;
        Object r03 = r02.tag;
    L6:
        r2.tag = r03;
        return r2;
    L5:
        r03 = null;
        goto L6
    }
}
