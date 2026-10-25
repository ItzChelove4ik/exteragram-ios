package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.MediaPeriodHolder;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
final class MediaPeriodQueue {
    private final AnalyticsCollector analyticsCollector;
    private final HandlerWrapper analyticsCollectorHandler;
    private int length;
    private MediaPeriodHolder loading;
    private final MediaPeriodHolder.Factory mediaPeriodHolderFactory;
    private long nextWindowSequenceNumber;
    private Object oldFrontPeriodUid;
    private long oldFrontPeriodWindowSequenceNumber;
    private final Timeline.Period period;
    private MediaPeriodHolder playing;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private List<MediaPeriodHolder> preloadPriorityList;
    private MediaPeriodHolder preloading;
    private MediaPeriodHolder prewarming;
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private final Timeline.Window window;

    public static /* synthetic */ void $r8$lambda$fC2Yvo68fonTcmK4OlepW6eTvFM(MediaPeriodQueue r0, ImmutableList.Builder r1, MediaSource.MediaPeriodId r2) {
        r0.analyticsCollector.updateMediaPeriodQueueInfo(r1.build(), r2);
    }

    public MediaPeriodQueue(AnalyticsCollector r1, HandlerWrapper r2, MediaPeriodHolder.Factory r3, ExoPlayer.PreloadConfiguration r4) {
        this.analyticsCollector = r1;
        this.analyticsCollectorHandler = r2;
        this.mediaPeriodHolderFactory = r3;
        this.preloadConfiguration = r4;
        this.period = new Timeline.Period();
        this.window = new Timeline.Window();
        this.preloadPriorityList = new ArrayList();
    }

    public static boolean areDurationsCompatible(long r2, long r4) {
        if (r2 != (-9223372036854775807L)) goto L5;
        return true;
    L5:
        if (r2 == r4) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean canKeepMediaPeriodHolder(MediaPeriodInfo r5, MediaPeriodInfo r6) {
        if (r5.startPositionUs == r6.startPositionUs) goto L5;
        return false;
    L5:
        if (r5.id.equals(r6.id) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private Pair<Object, Long> getDefaultPeriodPositionOfNextWindow(Timeline r11, Object r12, long r13) {
        int r5 = r11.getNextWindowIndex(r11.getPeriodByUid(r12, this.period).windowIndex, this.repeatMode, this.shuffleModeEnabled);
        if (r5 != (-1)) goto L5;
        return null;
    L5:
        return r11.getPeriodPositionUs(this.window, this.period, r5, -9223372036854775807L, r13);
    }

    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo r8) {
        return getMediaPeriodInfo(r8.timeline, r8.periodId, r8.requestedContentPositionUs, r8.positionUs);
    }

    private MediaPeriodInfo getFirstMediaPeriodInfoOfNextPeriod(Timeline r17, MediaPeriodHolder r18, long r19) {
        MediaPeriodInfo r10 = r18.info;
        int r2 = r17.getNextPeriodIndex(r17.getIndexOfPeriod(r10.id.periodUid), this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
        if (r2 != (-1)) goto L5;
        return null;
    L5:
        int r4 = r17.getPeriod(r2, this.period, true).windowIndex;
        Object r3 = Assertions.checkNotNull(this.period.uid);
        long r5 = r10.id.windowSequenceNumber;
        long r14 = 0;
        if (r17.getWindow(r4, this.window).firstPeriodIndex != r2) goto L20;
        Pair<Object, Long> r22 = r17.getPeriodPositionUs(this.window, this.period, r4, -9223372036854775807L, Math.max(0, r19));
        if (r22 != null) goto L10;
        return null;
    L10:
        Object r32 = r22.first;
        long r142 = ((Long) r22.second).longValue();
        MediaPeriodHolder r1 = r18.getNext();
        if (r1 != null) goto L13;
    L16:
        long r12 = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(r32);
        if (r12 != (-1)) goto L19;
        r12 = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + r12;
    L19:
        r5 = r12;
    L15:
        Object r23 = r32;
        long r33 = r142;
        r14 = -9223372036854775807L;
    L21:
        MediaSource.MediaPeriodId r24 = resolveMediaPeriodIdForAds(r17, r23, r33, r5, this.window, this.period);
        if (r14 == (-9223372036854775807L)) goto L33;
        if (r10.requestedContentPositionUs == (-9223372036854775807L)) goto L33;
        boolean r52 = hasServerSideInsertedAds(r10.id.periodUid, r17);
        if (r24.isAd() == false) goto L30;
        if (r52 == false) goto L30;
        r14 = r10.requestedContentPositionUs;
    L30:
        if (r52 == false) goto L33;
        r33 = r10.requestedContentPositionUs;
    L33:
        return getMediaPeriodInfo(r17, r24, r14, r33);
    L13:
        if (r1.uid.equals(r32) == false) goto L16;
        r5 = r1.info.id.windowSequenceNumber;
        goto L15
    L20:
        r23 = r3;
        r33 = 0;
        goto L21
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfo(Timeline r6, MediaPeriodHolder r7, long r8) {
        MediaPeriodInfo r0 = r7.info;
        long r1 = (r7.getRendererOffset() + r0.durationUs) - r8;
        if (r0.isLastInTimelinePeriod == false) goto L7;
        return getFirstMediaPeriodInfoOfNextPeriod(r6, r7, r1);
    L7:
        return getFollowingMediaPeriodInfoOfCurrentPeriod(r6, r7, r1);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfoOfCurrentPeriod(Timeline r13, MediaPeriodHolder r14, long r15) {
        MediaPeriodInfo r8 = r14.info;
        MediaSource.MediaPeriodId r9 = r8.id;
        r13.getPeriodByUid(r9.periodUid, this.period);
        boolean r3 = r8.isFollowedByTransitionToSameStream;
        if (r9.isAd() == false) goto L19;
        int r32 = r9.adGroupIndex;
        int r2 = this.period.getAdCountInAdGroup(r32);
        if (r2 != (-1)) goto L7;
        return null;
    L7:
        int r5 = this.period.getNextAdIndexToPlay(r32, r9.adIndexInAdGroup);
        if (r5 < r2) goto L10;
        long r0 = r8.requestedContentPositionUs;
        if (r0 != (-9223372036854775807L)) goto L17;
        Timeline.Window r1 = this.window;
        Timeline.Period r22 = this.period;
        Pair<Object, Long> r12 = r13.getPeriodPositionUs(r1, r22, r22.windowIndex, -9223372036854775807L, Math.max(0, r15));
        if (r12 != null) goto L16;
        return null;
    L16:
        r0 = ((Long) r12.second).longValue();
    L17:
        long r33 = getMinStartPositionAfterAdGroupUs(r13, r9.periodUid, r9.adGroupIndex);
        return getMediaPeriodInfoForContent(r13, r9.periodUid, Math.max(r33, r0), r8.requestedContentPositionUs, r9.windowSequenceNumber, r3);
    L10:
        return getMediaPeriodInfoForAd(r13, r9.periodUid, r32, r5, r8.requestedContentPositionUs, r9.windowSequenceNumber, r3);
    L19:
        int r16 = r9.nextAdGroupIndex;
        if (r16 != (-1)) goto L22;
    L25:
        int r4 = this.period.getFirstAdIndexToPlay(r9.nextAdGroupIndex);
        if (this.period.isServerSideInsertedAdGroup(r9.nextAdGroupIndex) == true) goto L28;
    L30:
        boolean r17 = false;
    L32:
        if (r4 == this.period.getAdCountInAdGroup(r9.nextAdGroupIndex)) goto L37;
        if (r17 == true) goto L37;
        return getMediaPeriodInfoForAd(r13, r9.periodUid, r9.nextAdGroupIndex, r4, r8.durationUs, r9.windowSequenceNumber, r3);
    L37:
        long r34 = getMinStartPositionAfterAdGroupUs(r13, r9.periodUid, r9.nextAdGroupIndex);
        return getMediaPeriodInfoForContent(r13, r9.periodUid, r34, r8.durationUs, r9.windowSequenceNumber, false);
    L28:
        if (this.period.getAdState(r9.nextAdGroupIndex, r4) != 3) goto L30;
        r17 = true;
        goto L32
    L22:
        if (this.period.isLivePostrollPlaceholder(r16) == false) goto L25;
        return getFirstMediaPeriodInfoOfNextPeriod(r13, r14, r15);
    }

    private MediaPeriodInfo getMediaPeriodInfo(Timeline r12, MediaSource.MediaPeriodId r13, long r14, long r16) {
        r12.getPeriodByUid(r13.periodUid, this.period);
        boolean r0 = r13.isAd();
        Object r3 = r13.periodUid;
        if (r0 == false) goto L7;
        return getMediaPeriodInfoForAd(r12, r3, r13.adGroupIndex, r13.adIndexInAdGroup, r14, r13.windowSequenceNumber, false);
    L7:
        return getMediaPeriodInfoForContent(r12, r3, r16, r14, r13.windowSequenceNumber, false);
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(Timeline r16, Object r17, int r18, int r19, long r20, long r22, boolean r24) {
        MediaSource.MediaPeriodId r0 = new MediaSource.MediaPeriodId(r17, r18, r19, r22);
        long r8 = r16.getPeriodByUid(r0.periodUid, this.period).getAdDurationUs(r0.adGroupIndex, r0.adIndexInAdGroup);
        if (r19 != this.period.getFirstAdIndexToPlay(r18)) goto L5;
        long r4 = this.period.getAdResumePositionUs();
    L6:
        boolean r11 = this.period.isServerSideInsertedAdGroup(r0.adGroupIndex);
        if (r8 == (-9223372036854775807L)) goto L12;
        if (r4 < r8) goto L12;
        r4 = Math.max(0, r8 - 1);
    L12:
        return new MediaPeriodInfo(r0, r4, r20, -9223372036854775807L, r8, r24, r11, false, false, false);
    L5:
        r4 = 0;
        goto L6
    }

    private MediaPeriodInfo getMediaPeriodInfoForContent(Timeline r26, Object r27, long r28, long r30, long r32, boolean r34) {
        r26.getPeriodByUid(r27, this.period);
        int r5 = this.period.getAdGroupIndexAfterPositionUs(r28);
        Timeline.Period r6 = this.period;
        int r7 = 1;
        if (r5 != (-1)) goto L10;
        if (r6.getAdGroupCount() <= 0) goto L16;
        Timeline.Period r62 = this.period;
        if (r62.isServerSideInsertedAdGroup(r62.getRemovedAdGroupCount()) == false) goto L16;
        boolean r63 = true;
    L17:
        MediaSource.MediaPeriodId r11 = new MediaSource.MediaPeriodId(r27, r32, r5);
        boolean r2 = isLastInPeriod(r11);
        boolean r23 = isLastInWindow(r26, r11);
        boolean r24 = isLastInTimeline(r26, r11, r2);
        if (r5 != (-1)) goto L20;
    L24:
        boolean r21 = false;
    L25:
        if (r5 != (-1)) goto L27;
    L31:
        boolean r1 = false;
    L33:
        if (r5 == (-1)) goto L37;
        if (r1 == true) goto L37;
        long r9 = this.period.getAdGroupTimeUs(r5);
    L36:
        long r16 = r9;
    L41:
        if (r16 != (-9223372036854775807L)) goto L43;
    L46:
        long r18 = this.period.durationUs;
    L48:
        if (r18 != (-9223372036854775807L)) goto L50;
    L56:
        long r12 = r28;
    L58:
        return new MediaPeriodInfo(r11, r12, r30, r16, r18, r34, r21, r2, r23, r24);
    L50:
        if (r28 < r18) goto L56;
        if (r24 == true) goto L55;
        if (r63 == false) goto L55;
        r7 = 0;
    L55:
        r12 = Math.max(0, r18 - r7);
        goto L58
    L43:
        if (r16 == Long.MIN_VALUE) goto L46;
        r18 = r16;
    L37:
        if (r63 == false) goto L39;
        r9 = this.period.durationUs;
        goto L36
    L39:
        r16 = -9223372036854775807L;
        goto L41
    L27:
        if (this.period.isLivePostrollPlaceholder(r5) == false) goto L31;
        if (this.period.isServerSideInsertedAdGroup(r5) == false) goto L31;
        r1 = true;
        goto L33
    L20:
        if (this.period.isServerSideInsertedAdGroup(r5) == false) goto L24;
        if (this.period.isLivePostrollPlaceholder(r5) == true) goto L24;
        r21 = true;
    L16:
        r63 = false;
        goto L17
    L10:
        if (r6.isServerSideInsertedAdGroup(r5) == false) goto L16;
        long r10 = this.period.getAdGroupTimeUs(r5);
        Timeline.Period r64 = this.period;
        if (r10 != r64.durationUs) goto L16;
        if (r64.hasPlayedAdGroup(r5) == false) goto L16;
        r63 = true;
        r5 = -1;
        goto L17
    }

    private MediaPeriodInfo getMediaPeriodInfoForPeriodPosition(Timeline r11, Object r12, long r13, long r15) {
        MediaSource.MediaPeriodId r122 = resolveMediaPeriodIdForAds(r11, r12, r13, r15, this.window, this.period);
        boolean r0 = r122.isAd();
        Object r2 = r122.periodUid;
        if (r0 == false) goto L7;
        return getMediaPeriodInfoForAd(r11, r2, r122.adGroupIndex, r122.adIndexInAdGroup, r13, r122.windowSequenceNumber, false);
    L7:
        return getMediaPeriodInfoForContent(r11, r2, r13, -9223372036854775807L, r122.windowSequenceNumber, false);
    }

    private long getMinStartPositionAfterAdGroupUs(Timeline r3, Object r4, int r5) {
        r3.getPeriodByUid(r4, this.period);
        long r32 = this.period.getAdGroupTimeUs(r5);
        Timeline.Period r2 = this.period;
        if (r32 != Long.MIN_VALUE) goto L7;
        return r2.durationUs;
    L7:
        return r32 + r2.getContentResumeOffsetUs(r5);
    }

    private boolean hasServerSideInsertedAds(Object r4, Timeline r5) {
        int r42 = r5.getPeriodByUid(r4, this.period).getAdGroupCount();
        int r52 = this.period.getRemovedAdGroupCount();
        if (r42 > 0) goto L5;
        return false;
    L5:
        if (this.period.isServerSideInsertedAdGroup(r52) == true) goto L7;
        return false;
    L7:
        if (r42 <= 1) goto L9;
    L10:
        return true;
    L9:
        if (this.period.getAdGroupTimeUs(r52) != Long.MIN_VALUE) goto L10;
        return false;
    }

    private boolean isLastInPeriod(MediaSource.MediaPeriodId r1) {
        if (r1.isAd() == false) goto L5;
        return false;
    L5:
        if (r1.nextAdGroupIndex != (-1)) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isLastInTimeline(Timeline r7, MediaSource.MediaPeriodId r8, boolean r9) {
        int r1 = r7.getIndexOfPeriod(r8.periodUid);
        if (r7.getWindow(r7.getPeriod(r1, this.period).windowIndex, this.window).isDynamic == false) goto L5;
        return false;
    L5:
        if (r7.isLastPeriod(r1, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) == false) goto L11;
        if (r9 == false) goto L12;
        return true;
    L12:
        return false;
    L11:
        return false;
    }

    private boolean isLastInWindow(Timeline r4, MediaSource.MediaPeriodId r5) {
        if (isLastInPeriod(r5) == true) goto L5;
        return false;
    L5:
        int r0 = r4.getPeriodByUid(r5.periodUid, this.period).windowIndex;
        int r52 = r4.getIndexOfPeriod(r5.periodUid);
        if (r4.getWindow(r0, this.window).lastPeriodIndex != r52) goto L9;
        return true;
    L9:
        return false;
    }

    private static boolean isSkippableAdPeriod(Timeline.Period r8) {
        int r0 = r8.getAdGroupCount();
        if (r0 != 0) goto L5;
    L26:
        return false;
    L5:
        if (r0 != 1) goto L9;
        if (r8.isLivePostrollPlaceholder(0) == true) goto L26;
    L9:
        if (r8.isServerSideInsertedAdGroup(r8.getRemovedAdGroupCount()) == false) goto L26;
        long r3 = 0;
        if (r8.getAdGroupIndexForPositionUs(0) != (-1)) goto L26;
        if (r8.durationUs != 0) goto L17;
        return true;
    L17:
        if (r8.isLivePostrollPlaceholder(r0 - 1) == false) goto L19;
        int r5 = 2;
    L20:
        int r02 = r0 - r5;
        int r52 = 0;
    L21:
        if (r52 > r02) goto L24;
        r3 = r3 + r8.getContentResumeOffsetUs(r52);
        r52 = r52 + 1;
        goto L21
    L24:
        if (r8.durationUs > r3) goto L26;
        return true;
    L19:
        r5 = 1;
        goto L20
    }

    private void notifyQueueUpdate() {
        ImmutableList.Builder r0 = ImmutableList.builder();
        MediaPeriodHolder r1 = this.playing;
    L3:
        if (r1 == null) goto L5;
        r0.add(r1.info.id);
        r1 = r1.getNext();
        goto L3
    L5:
        MediaPeriodHolder r12 = this.reading;
        if (r12 != null) goto L8;
        MediaSource.MediaPeriodId r13 = null;
    L9:
        this.analyticsCollectorHandler.post(new MediaPeriodQueue$$ExternalSyntheticLambda0(this, r0, r13));
        return;
    L8:
        r13 = r12.info.id;
        goto L9
    }

    private void releaseAndResetPreloadPriorityList(List<MediaPeriodHolder> r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.preloadPriorityList.size()) goto L6;
        this.preloadPriorityList.get(r0).release();
        r0 = r0 + 1;
        goto L4
    L6:
        this.preloadPriorityList = r3;
        this.preloading = null;
        maybeUpdatePreloadMediaPeriodHolder();
    }

    private MediaPeriodHolder removePreloadedMediaPeriodHolder(MediaPeriodInfo r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.preloadPriorityList.size()) goto L10;
        if (this.preloadPriorityList.get(r0).canBeUsedForMediaPeriodInfo(r3) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.preloadPriorityList.remove(r0);
    L10:
        return null;
    }

    private static MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline r2, Object r3, long r4, long r6, Timeline.Window r8, Timeline.Period r9) {
        r2.getPeriodByUid(r3, r9);
        r2.getWindow(r9.windowIndex, r8);
        int r0 = r2.getIndexOfPeriod(r3);
    L4:
        if (isSkippableAdPeriod(r9) == false) goto L8;
        if (r0 > r8.lastPeriodIndex) goto L8;
        r2.getPeriod(r0, r9, true);
        r3 = Assertions.checkNotNull(r9.uid);
        r0 = r0 + 1;
    L8:
        r2.getPeriodByUid(r3, r9);
        int r42 = r9.getAdGroupIndexForPositionUs(r4);
        if (r42 != (-1)) goto L13;
        return new MediaSource.MediaPeriodId(r3, r6, r9.getAdGroupIndexAfterPositionUs(r4));
    L13:
        return new MediaSource.MediaPeriodId(r3, r42, r9.getFirstAdIndexToPlay(r42), r6);
    }

    private long resolvePeriodUidToWindowSequenceNumber(Timeline r6, Object r7) {
        int r0 = r6.getPeriodByUid(r7, this.period).windowIndex;
        Object r1 = this.oldFrontPeriodUid;
        if (r1 == null) goto L10;
        int r12 = r6.getIndexOfPeriod(r1);
        if (r12 == (-1)) goto L10;
        if (r6.getPeriod(r12, this.period).windowIndex != r0) goto L10;
        return this.oldFrontPeriodWindowSequenceNumber;
    L10:
        MediaPeriodHolder r13 = this.playing;
    L11:
        if (r13 == null) goto L17;
        if (r13.uid.equals(r7) == true) goto L15;
        r13 = r13.getNext();
        goto L11
    L15:
        return r13.info.id.windowSequenceNumber;
    L17:
        MediaPeriodHolder r14 = this.playing;
    L18:
        if (r14 == null) goto L26;
        int r3 = r6.getIndexOfPeriod(r14.uid);
        if (r3 == (-1)) goto L25;
        if (r6.getPeriod(r3, this.period).windowIndex != r0) goto L25;
        return r14.info.id.windowSequenceNumber;
    L25:
        r14 = r14.getNext();
        goto L18
    L26:
        long r02 = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(r7);
        if (r02 == (-1)) goto L29;
        return r02;
    L29:
        long r03 = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + r03;
        if (this.playing != null) goto L32;
        this.oldFrontPeriodUid = r7;
        this.oldFrontPeriodWindowSequenceNumber = r03;
    L32:
        return r03;
    }

    private long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(Object r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.preloadPriorityList.size()) goto L10;
        MediaPeriodHolder r1 = this.preloadPriorityList.get(r0);
        if (r1.uid.equals(r4) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r1.info.id.windowSequenceNumber;
    L10:
        return -1;
    }

    private int updateForPlaybackModeChange(Timeline r8) {
        MediaPeriodHolder r0 = this.playing;
        if (r0 != null) goto L6;
        return 0;
    L6:
        int r2 = r8.getIndexOfPeriod(r0.uid);
    L7:
        Timeline r1 = r8;
        r2 = r1.getNextPeriodIndex(r2, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
    L9:
        if (((MediaPeriodHolder) Assertions.checkNotNull(r0)).getNext() == null) goto L13;
        if (r0.info.isLastInTimelinePeriod == true) goto L13;
        r0 = r0.getNext();
    L13:
        MediaPeriodHolder r82 = r0.getNext();
        if (r2 == (-1)) goto L21;
        if (r82 == null) goto L21;
        if (r1.getIndexOfPeriod(r82.uid) != r2) goto L21;
        r0 = r82;
        r8 = r1;
    L21:
        int r83 = removeAfter(r0);
        r0.info = getUpdatedMediaPeriodInfo(r1, r0.info);
        return r83;
    }

    public MediaPeriodHolder advancePlayingPeriod() {
        MediaPeriodHolder r0 = this.playing;
        if (r0 != null) goto L6;
        return null;
    L6:
        if (r0 != this.reading) goto L8;
        this.reading = r0.getNext();
    L8:
        MediaPeriodHolder r02 = this.playing;
        if (r02 != this.prewarming) goto L11;
        this.prewarming = r02.getNext();
    L11:
        this.playing.release();
        int r03 = this.length - 1;
        this.length = r03;
        if (r03 != 0) goto L14;
        this.loading = null;
        MediaPeriodHolder r04 = this.playing;
        this.oldFrontPeriodUid = r04.uid;
        this.oldFrontPeriodWindowSequenceNumber = r04.info.id.windowSequenceNumber;
    L14:
        this.playing = this.playing.getNext();
        notifyQueueUpdate();
        return this.playing;
    }

    public MediaPeriodHolder advancePrewarmingPeriod() {
        this.prewarming = ((MediaPeriodHolder) Assertions.checkStateNotNull(this.prewarming)).getNext();
        notifyQueueUpdate();
        return (MediaPeriodHolder) Assertions.checkStateNotNull(this.prewarming);
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        MediaPeriodHolder r0 = this.prewarming;
        MediaPeriodHolder r1 = this.reading;
        if (r0 != r1) goto L5;
        this.prewarming = ((MediaPeriodHolder) Assertions.checkStateNotNull(r1)).getNext();
    L5:
        this.reading = ((MediaPeriodHolder) Assertions.checkStateNotNull(this.reading)).getNext();
        notifyQueueUpdate();
        return (MediaPeriodHolder) Assertions.checkStateNotNull(this.reading);
    }

    public void clear() {
        if (this.length != 0) goto L5;
        return;
    L5:
        MediaPeriodHolder r0 = (MediaPeriodHolder) Assertions.checkStateNotNull(this.playing);
        this.oldFrontPeriodUid = r0.uid;
        this.oldFrontPeriodWindowSequenceNumber = r0.info.id.windowSequenceNumber;
    L6:
        if (r0 == null) goto L8;
        r0.release();
        r0 = r0.getNext();
        goto L6
    L8:
        this.playing = null;
        this.loading = null;
        this.reading = null;
        this.prewarming = null;
        this.length = 0;
        notifyQueueUpdate();
    }

    public MediaPeriodHolder enqueueNextMediaPeriodHolder(MediaPeriodInfo r5) {
        MediaPeriodHolder r0 = this.loading;
        if (r0 != null) goto L5;
        long r02 = 1000000000000L;
    L6:
        MediaPeriodHolder r2 = removePreloadedMediaPeriodHolder(r5);
        if (r2 != null) goto L9;
        r2 = this.mediaPeriodHolderFactory.create(r5, r02);
    L10:
        MediaPeriodHolder r52 = this.loading;
        if (r52 == null) goto L13;
        r52.setNext(r2);
    L14:
        this.oldFrontPeriodUid = null;
        this.loading = r2;
        this.length++;
        notifyQueueUpdate();
        return r2;
    L13:
        this.playing = r2;
        this.reading = r2;
        this.prewarming = r2;
        goto L14
    L9:
        r2.info = r5;
        r2.setRendererOffset(r02);
        goto L10
    L5:
        r02 = (r0.getRendererOffset() + this.loading.info.durationUs) - r5.startPositionUs;
        goto L6
    }

    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    public MediaPeriodInfo getNextMediaPeriodInfo(long r2, PlaybackInfo r4) {
        MediaPeriodHolder r0 = this.loading;
        if (r0 != null) goto L7;
        return getFirstMediaPeriodInfo(r4);
    L7:
        return getFollowingMediaPeriodInfo(r4.timeline, r0, r2);
    }

    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    public MediaPeriodHolder getPreloadHolderByMediaPeriod(MediaPeriod r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.preloadPriorityList.size()) goto L9;
        MediaPeriodHolder r1 = this.preloadPriorityList.get(r0);
        if (r1.mediaPeriod == r4) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public MediaPeriodHolder getPreloadingPeriod() {
        return this.preloading;
    }

    public MediaPeriodHolder getPrewarmingPeriod() {
        return this.prewarming;
    }

    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    public MediaPeriodInfo getUpdatedMediaPeriodInfo(Timeline r18, MediaPeriodInfo r19) {
        MediaSource.MediaPeriodId r3 = r19.id;
        boolean r12 = isLastInPeriod(r3);
        boolean r13 = isLastInWindow(r18, r3);
        boolean r14 = isLastInTimeline(r18, r3, r12);
        r18.getPeriodByUid(r19.id.periodUid, this.period);
        if (r3.isAd() == true) goto L8;
        int r1 = r3.nextAdGroupIndex;
        if (r1 == (-1)) goto L8;
        long r7 = this.period.getAdGroupTimeUs(r1);
    L10:
        if (r3.isAd() == false) goto L13;
        long r4 = this.period.getAdDurationUs(r3.adGroupIndex, r3.adIndexInAdGroup);
    L20:
        if (r3.isAd() == false) goto L23;
        boolean r0 = this.period.isServerSideInsertedAdGroup(r3.adGroupIndex);
    L22:
        boolean r11 = r0;
        long r8 = r4;
        return new MediaPeriodInfo(r3, r19.startPositionUs, r19.requestedContentPositionUs, r7, r8, r19.isPrecededByTransitionFromSameStream, r11, r12, r13, r14);
    L23:
        int r15 = r3.nextAdGroupIndex;
        if (r15 != (-1)) goto L26;
    L28:
        r0 = false;
        goto L22
    L26:
        if (this.period.isServerSideInsertedAdGroup(r15) == false) goto L28;
        r0 = true;
        goto L22
    L13:
        if (r7 != (-9223372036854775807L)) goto L15;
    L18:
        r4 = this.period.getDurationUs();
        goto L20
    L15:
        if (r7 == Long.MIN_VALUE) goto L18;
        r4 = r7;
    L8:
        r7 = -9223372036854775807L;
        goto L10
    }

    public void invalidatePreloadPool(Timeline r15) {
        if (this.preloadConfiguration.targetPreloadDurationUs == (-9223372036854775807L)) goto L21;
        MediaPeriodHolder r0 = this.loading;
        if (r0 == null) goto L21;
        ArrayList r1 = new ArrayList();
        Pair<Object, Long> r2 = getDefaultPeriodPositionOfNextWindow(r15, r0.info.id.periodUid, 0);
        if (r2 != null) goto L10;
    L18:
        MediaPeriodQueue r7 = this;
    L19:
        r7.releaseAndResetPreloadPriorityList(r1);
        return;
    L10:
        if (r15.getWindow(r15.getPeriodByUid(r2.first, this.period).windowIndex, this.window).isLive() == true) goto L18;
        long r3 = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(r2.first);
        if (r3 != (-1)) goto L14;
        r3 = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + r3;
    L14:
        Object r9 = r2.first;
        long r10 = ((Long) r2.second).longValue();
        r7 = this;
        MediaPeriodInfo r14 = r7.getMediaPeriodInfoForPeriodPosition(r15, r9, r10, r3);
        MediaPeriodHolder r152 = r7.removePreloadedMediaPeriodHolder(r14);
        if (r152 != null) goto L17;
        long r22 = (r0.getRendererOffset() + r0.info.durationUs) - r14.startPositionUs;
        r152 = r7.mediaPeriodHolderFactory.create(r14, r22);
    L17:
        r1.add(r152);
    L21:
        releasePreloadPool();
    }

    public boolean isLoading(MediaPeriod r1) {
        MediaPeriodHolder r0 = this.loading;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.mediaPeriod != r1) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPreloading(MediaPeriod r1) {
        MediaPeriodHolder r0 = this.preloading;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.mediaPeriod != r1) goto L10;
        return true;
    L10:
        return false;
    }

    public void maybeUpdatePreloadMediaPeriodHolder() {
        MediaPeriodHolder r0 = this.preloading;
        if (r0 != null) goto L5;
    L7:
        this.preloading = null;
        int r02 = 0;
    L9:
        if (r02 >= this.preloadPriorityList.size()) goto L18;
        MediaPeriodHolder r1 = this.preloadPriorityList.get(r02);
        if (r1.isFullyPreloaded() == false) goto L12;
        r02 = r02 + 1;
        goto L9
    L12:
        this.preloading = r1;
        return;
    L18:
        return;
    L5:
        if (r0.isFullyPreloaded() == true) goto L7;
    }

    public void reevaluateBuffer(long r1) {
        MediaPeriodHolder r0 = this.loading;
        if (r0 == null) goto L6;
        r0.reevaluateBuffer(r1);
        return;
    }

    public void releasePreloadPool() {
        if (this.preloadPriorityList.isEmpty() == true) goto L6;
        releaseAndResetPreloadPriorityList(new ArrayList());
        return;
    }

    public int removeAfter(MediaPeriodHolder r3) {
        Assertions.checkStateNotNull(r3);
        int r1 = 0;
        if (r3.equals(this.loading) == false) goto L5;
        return 0;
    L5:
        this.loading = r3;
    L7:
        if (r3.getNext() == null) goto L15;
        r3 = (MediaPeriodHolder) Assertions.checkNotNull(r3.getNext());
        if (r3 != this.reading) goto L12;
        MediaPeriodHolder r0 = this.playing;
        this.reading = r0;
        this.prewarming = r0;
        r1 = 3;
    L12:
        if (r3 != this.prewarming) goto L14;
        this.prewarming = this.reading;
        r1 = r1 | 2;
    L14:
        r3.release();
        this.length--;
        goto L7
    L15:
        ((MediaPeriodHolder) Assertions.checkNotNull(this.loading)).setNext(null);
        notifyQueueUpdate();
        return r1;
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange(Timeline r11, Object r12, long r13) {
        long r4 = resolvePeriodUidToWindowSequenceNumber(r11, r12);
        r11.getPeriodByUid(r12, this.period);
        r11.getWindow(this.period.windowIndex, this.window);
        int r0 = r11.getIndexOfPeriod(r12);
        boolean r2 = false;
    L4:
        if (r0 < this.window.firstPeriodIndex) goto L19;
        boolean r6 = true;
        r11.getPeriod(r0, this.period, true);
        if (this.period.getAdGroupCount() > 0) goto L9;
        r6 = false;
    L9:
        r2 = r2 | r6;
        Timeline.Period r3 = this.period;
        if (r3.getAdGroupIndexForPositionUs(r3.durationUs) == (-1)) goto L12;
        r12 = Assertions.checkNotNull(this.period.uid);
    L12:
        if (r2 == false) goto L17;
        if (r6 == false) goto L19;
        if (this.period.durationUs != 0) goto L19;
    L17:
        r0 = r0 - 1;
    L19:
        return resolveMediaPeriodIdForAds(r11, r12, r13, r4, this.window, this.period);
    }

    public boolean shouldLoadNextMediaPeriod() {
        MediaPeriodHolder r0 = this.loading;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.info.isFinal == false) goto L7;
        return false;
    L7:
        if (r0.isFullyBuffered() == true) goto L9;
        return false;
    L9:
        if (this.loading.info.durationUs != (-9223372036854775807L)) goto L11;
        return false;
    L11:
        if (this.length < 100) goto L20;
        return false;
    L20:
        return true;
    }

    public void updatePreloadConfiguration(Timeline r1, ExoPlayer.PreloadConfiguration r2) {
        this.preloadConfiguration = r2;
        invalidatePreloadPool(r1);
    }

    public int updateQueuedPeriods(Timeline r18, long r19, long r21, long r23) {
        MediaPeriodHolder r2 = this.playing;
        MediaPeriodHolder r3 = null;
    L3:
        if (r2 == null) goto L63;
        MediaPeriodInfo r5 = r2.info;
        if (r3 != null) goto L7;
        MediaPeriodInfo r32 = getUpdatedMediaPeriodInfo(r18, r5);
    L13:
        r2.info = r32.copyWithRequestedContentPositionUs(r5.requestedContentPositionUs);
        if (r5.durationUs != r32.durationUs) goto L15;
        r3 = r2;
        r2 = r2.getNext();
        goto L3
    L15:
        r2.updateClipping();
        long r6 = r32.durationUs;
        if (r6 != (-9223372036854775807L)) goto L18;
        long r62 = LongCompanionObject.MAX_VALUE;
    L20:
        if (r2 == this.reading) goto L22;
    L28:
        boolean r1 = false;
    L30:
        if (r2 == this.prewarming) goto L32;
    L36:
        boolean r63 = false;
    L37:
        int r0 = removeAfter(r2);
        if (r0 == 0) goto L40;
        return r0;
    L40:
        long r13 = r5.durationUs;
        if (r13 == (-9223372036854775807L)) goto L43;
    L49:
        boolean r02 = false;
    L50:
        if (r1 == true) goto L52;
    L55:
        int r4 = 0;
    L56:
        if (r63 == true) goto L58;
        return r4;
    L58:
        return r4 | 2;
    L52:
        if (r13 != (-9223372036854775807L)) goto L54;
        if (r02 == false) goto L55;
    L54:
        r4 = 1;
        goto L56
    L43:
        if (r5.endPositionUs != Long.MIN_VALUE) goto L49;
        long r22 = r32.endPositionUs;
        if (r22 == (-9223372036854775807L)) goto L49;
        if (r22 == Long.MIN_VALUE) goto L49;
        r02 = true;
        goto L50
    L32:
        if (r23 != Long.MIN_VALUE) goto L34;
    L35:
        r63 = true;
        goto L37
    L34:
        if (r23 < r62) goto L36;
    L22:
        if (r2.info.isFollowedByTransitionToSameStream == true) goto L28;
        if (r21 != Long.MIN_VALUE) goto L26;
    L27:
        r1 = true;
        goto L30
    L26:
        if (r21 < r62) goto L28;
    L18:
        r62 = r2.toRendererTime(r6);
        goto L20
    L7:
        MediaPeriodInfo r8 = getFollowingMediaPeriodInfo(r18, r3, r19);
        if (r8 == null) goto L62;
        if (canKeepMediaPeriodHolder(r5, r8) == false) goto L62;
        r32 = r8;
    L62:
        return removeAfter(r3);
    L63:
        return 0;
    }

    public int updateRepeatMode(Timeline r1, int r2) {
        this.repeatMode = r2;
        return updateForPlaybackModeChange(r1);
    }

    public int updateShuffleModeEnabled(Timeline r1, boolean r2) {
        this.shuffleModeEnabled = r2;
        return updateForPlaybackModeChange(r1);
    }
}
