package androidx.media3.exoplayer;

import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.MediaSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class MediaPeriodInfo {
    public final long durationUs;
    public final long endPositionUs;
    public final MediaSource.MediaPeriodId id;
    public final boolean isFinal;
    public final boolean isFollowedByTransitionToSameStream;
    public final boolean isLastInTimelinePeriod;
    public final boolean isLastInTimelineWindow;
    public final boolean isPrecededByTransitionFromSameStream;
    public final long requestedContentPositionUs;
    public final long startPositionUs;

    public MediaPeriodInfo(MediaSource.MediaPeriodId r8, long r9, long r11, long r13, long r15, boolean r17, boolean r18, boolean r19, boolean r20, boolean r21) {
        boolean r4 = true;
        if (r21 == false) goto L7;
        if (r19 == true) goto L7;
        boolean r6 = false;
    L8:
        Assertions.checkArgument(r6);
        if (r20 == false) goto L13;
        if (r19 == true) goto L13;
        boolean r62 = false;
    L14:
        Assertions.checkArgument(r62);
        if (r18 == false) goto L21;
        if (r19 == true) goto L20;
        if (r20 == true) goto L20;
        if (r21 == false) goto L21;
    L20:
        r4 = false;
    L21:
        Assertions.checkArgument(r4);
        this.id = r8;
        this.startPositionUs = r9;
        this.requestedContentPositionUs = r11;
        this.endPositionUs = r13;
        this.durationUs = r15;
        this.isPrecededByTransitionFromSameStream = r17;
        this.isFollowedByTransitionToSameStream = r18;
        this.isLastInTimelinePeriod = r19;
        this.isLastInTimelineWindow = r20;
        this.isFinal = r21;
        return;
    L13:
        r62 = true;
    L7:
        r6 = true;
        goto L8
    }

    public MediaPeriodInfo copyWithRequestedContentPositionUs(long r18) {
        if (r18 != this.requestedContentPositionUs) goto L6;
        return this;
    L6:
        return new MediaPeriodInfo(this.id, this.startPositionUs, r18, this.endPositionUs, this.durationUs, this.isPrecededByTransitionFromSameStream, this.isFollowedByTransitionToSameStream, this.isLastInTimelinePeriod, this.isLastInTimelineWindow, this.isFinal);
    }

    public MediaPeriodInfo copyWithStartPositionUs(long r18) {
        if (r18 != this.startPositionUs) goto L6;
        return this;
    L6:
        return new MediaPeriodInfo(this.id, r18, this.requestedContentPositionUs, this.endPositionUs, this.durationUs, this.isPrecededByTransitionFromSameStream, this.isFollowedByTransitionToSameStream, this.isLastInTimelinePeriod, this.isLastInTimelineWindow, this.isFinal);
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L31:
        return false;
    L8:
        if (MediaPeriodInfo.class != r7.getClass()) goto L31;
        MediaPeriodInfo r72 = (MediaPeriodInfo) r7;
        if (this.startPositionUs != r72.startPositionUs) goto L31;
        if (this.requestedContentPositionUs != r72.requestedContentPositionUs) goto L31;
        if (this.endPositionUs != r72.endPositionUs) goto L31;
        if (this.durationUs != r72.durationUs) goto L31;
        if (this.isPrecededByTransitionFromSameStream != r72.isPrecededByTransitionFromSameStream) goto L31;
        if (this.isFollowedByTransitionToSameStream != r72.isFollowedByTransitionToSameStream) goto L31;
        if (this.isLastInTimelinePeriod != r72.isLastInTimelinePeriod) goto L31;
        if (this.isLastInTimelineWindow != r72.isLastInTimelineWindow) goto L31;
        if (this.isFinal != r72.isFinal) goto L31;
        if (Objects.equals(this.id, r72.id) == false) goto L31;
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((527 + this.id.hashCode()) * 31) + ((int) this.startPositionUs)) * 31) + ((int) this.requestedContentPositionUs)) * 31) + ((int) this.endPositionUs)) * 31) + ((int) this.durationUs)) * 31) + (this.isPrecededByTransitionFromSameStream ? 1 : 0)) * 31) + (this.isFollowedByTransitionToSameStream ? 1 : 0)) * 31) + (this.isLastInTimelinePeriod ? 1 : 0)) * 31) + (this.isLastInTimelineWindow ? 1 : 0)) * 31) + (this.isFinal ? 1 : 0);
    }
}
