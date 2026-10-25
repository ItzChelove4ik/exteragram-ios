package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.text.SubtitleParser;

/* loaded from: classes4.dex */
public interface MediaSource {

    public interface Factory {
        public static final Factory UNSUPPORTED = null;

        static {
            UNSUPPORTED = MediaSourceFactory.UNSUPPORTED;
        }

        MediaSource createMediaSource(MediaItem r1);

        @Deprecated
        default Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
            return this;
        }

        default Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
            return this;
        }

        Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r1);

        Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1);

        default Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
            return this;
        }
    }

    public static final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final int nextAdGroupIndex;
        public final Object periodUid;
        public final long windowSequenceNumber;

        public MediaPeriodId(Object r3) {
            this(r3, -1);
        }

        public MediaPeriodId(Object r8, int r9, int r10, long r11) {
            this(r8, r9, r10, r11, -1);
        }

        private MediaPeriodId(Object r1, int r2, int r3, long r4, int r6) {
            this.periodUid = r1;
            this.adGroupIndex = r2;
            this.adIndexInAdGroup = r3;
            this.windowSequenceNumber = r4;
            this.nextAdGroupIndex = r6;
        }

        public MediaPeriodId(Object r8, long r9) {
            this(r8, -1, -1, r9, -1);
        }

        public MediaPeriodId(Object r8, long r9, int r11) {
            this(r8, -1, -1, r9, r11);
        }

        public MediaPeriodId copyWithPeriodUid(Object r9) {
            if (this.periodUid.equals(r9) == false) goto L6;
            return this;
        L6:
            return new MediaPeriodId(r9, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.nextAdGroupIndex);
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof MediaPeriodId) == true) goto L8;
            return false;
        L8:
            MediaPeriodId r82 = (MediaPeriodId) r8;
            if (this.periodUid.equals(r82.periodUid) == true) goto L11;
        L19:
            return false;
        L11:
            if (this.adGroupIndex != r82.adGroupIndex) goto L19;
            if (this.adIndexInAdGroup != r82.adIndexInAdGroup) goto L19;
            if (this.windowSequenceNumber != r82.windowSequenceNumber) goto L19;
            if (this.nextAdGroupIndex != r82.nextAdGroupIndex) goto L19;
            return true;
        }

        public int hashCode() {
            return ((((((((527 + this.periodUid.hashCode()) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + this.nextAdGroupIndex;
        }

        public boolean isAd() {
            if (this.adGroupIndex == (-1)) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public interface MediaSourceCaller {
        void onSourceInfoRefreshed(MediaSource r1, Timeline r2);
    }

    void addDrmEventListener(Handler r1, DrmSessionEventListener r2);

    void addEventListener(Handler r1, MediaSourceEventListener r2);

    default boolean canUpdateMediaItem(MediaItem r1) {
        return false;
    }

    MediaPeriod createPeriod(MediaPeriodId r1, Allocator r2, long r3);

    void disable(MediaSourceCaller r1);

    void enable(MediaSourceCaller r1);

    default Timeline getInitialTimeline() {
        return null;
    }

    MediaItem getMediaItem();

    default boolean isSingleWindow() {
        return true;
    }

    void maybeThrowSourceInfoRefreshError();

    void prepareSource(MediaSourceCaller r1, TransferListener r2, PlayerId r3);

    void releasePeriod(MediaPeriod r1);

    void releaseSource(MediaSourceCaller r1);

    void removeDrmEventListener(DrmSessionEventListener r1);

    void removeEventListener(MediaSourceEventListener r1);

    default void updateMediaItem(MediaItem r1) {
    }
}
