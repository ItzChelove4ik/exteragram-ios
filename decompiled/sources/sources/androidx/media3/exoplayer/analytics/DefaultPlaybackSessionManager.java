package androidx.media3.exoplayer.analytics;

import android.util.Base64;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlaybackSessionManager;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.base.Supplier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class DefaultPlaybackSessionManager implements PlaybackSessionManager {
    public static final Supplier<String> DEFAULT_SESSION_ID_GENERATOR = null;
    private static final Random RANDOM = null;
    private String currentSessionId;
    private Timeline currentTimeline;
    private long lastRemovedCurrentWindowSequenceNumber;
    private PlaybackSessionManager.Listener listener;
    private final Timeline.Period period;
    private final Supplier<String> sessionIdGenerator;
    private final HashMap<String, SessionDescriptor> sessions;
    private final Timeline.Window window;

    public final class SessionDescriptor {
        private MediaSource.MediaPeriodId adMediaPeriodId;
        private boolean isActive;
        private boolean isCreated;
        private final String sessionId;
        final /* synthetic */ DefaultPlaybackSessionManager this$0;
        private int windowIndex;
        private long windowSequenceNumber;

        public SessionDescriptor(DefaultPlaybackSessionManager r1, String r2, int r3, MediaSource.MediaPeriodId r4) {
            this.this$0 = r1;
            this.sessionId = r2;
            this.windowIndex = r3;
            if (r4 != null) goto L5;
            long r12 = -1;
        L6:
            this.windowSequenceNumber = r12;
            if (r4 != null) goto L9;
            return;
        L9:
            if (r4.isAd() == false) goto L13;
            this.adMediaPeriodId = r4;
            return;
        L13:
            return;
        L5:
            r12 = r4.windowSequenceNumber;
            goto L6
        }

        public static /* synthetic */ String access$000(SessionDescriptor r0) {
            return r0.sessionId;
        }

        public static /* synthetic */ long access$100(SessionDescriptor r2) {
            return r2.windowSequenceNumber;
        }

        public static /* synthetic */ int access$200(SessionDescriptor r0) {
            return r0.windowIndex;
        }

        public static /* synthetic */ boolean access$300(SessionDescriptor r0) {
            return r0.isCreated;
        }

        public static /* synthetic */ boolean access$302(SessionDescriptor r0, boolean r1) {
            r0.isCreated = r1;
            return r1;
        }

        public static /* synthetic */ boolean access$400(SessionDescriptor r0) {
            return r0.isActive;
        }

        public static /* synthetic */ boolean access$402(SessionDescriptor r0, boolean r1) {
            r0.isActive = r1;
            return r1;
        }

        public static /* synthetic */ MediaSource.MediaPeriodId access$500(SessionDescriptor r0) {
            return r0.adMediaPeriodId;
        }

        private int resolveWindowIndexToNewTimeline(Timeline r3, Timeline r4, int r5) {
            if (r5 >= r3.getWindowCount()) goto L5;
            r3.getWindow(r5, DefaultPlaybackSessionManager.access$700(this.this$0));
            int r52 = DefaultPlaybackSessionManager.access$700(this.this$0).firstPeriodIndex;
        L10:
            if (r52 > DefaultPlaybackSessionManager.access$700(this.this$0).lastPeriodIndex) goto L16;
            int r0 = r4.getIndexOfPeriod(r3.getUidOfPeriod(r52));
            if (r0 != (-1)) goto L14;
            r52 = r52 + 1;
            goto L10
        L14:
            return r4.getPeriod(r0, DefaultPlaybackSessionManager.access$800(this.this$0)).windowIndex;
        L16:
            return -1;
        L5:
            if (r5 >= r4.getWindowCount()) goto L7;
            return r5;
        L7:
            return -1;
        }

        public boolean belongsToSession(int r7, MediaSource.MediaPeriodId r8) {
            if (r8 == null) goto L5;
            MediaSource.MediaPeriodId r72 = this.adMediaPeriodId;
            if (r72 != null) goto L17;
            if (r8.isAd() == false) goto L13;
        L15:
            return false;
        L13:
            if (r8.windowSequenceNumber != this.windowSequenceNumber) goto L15;
            return true;
        L17:
            if (r8.windowSequenceNumber == r72.windowSequenceNumber) goto L19;
        L23:
            return false;
        L19:
            if (r8.adGroupIndex != r72.adGroupIndex) goto L23;
            if (r8.adIndexInAdGroup != r72.adIndexInAdGroup) goto L23;
            return true;
        L5:
            if (r7 != this.windowIndex) goto L7;
            return true;
        L7:
            return false;
        }

        public boolean isFinishedAtEventTime(AnalyticsListener.EventTime r10) {
            MediaSource.MediaPeriodId r0 = r10.mediaPeriodId;
            if (r0 == null) goto L5;
            long r3 = this.windowSequenceNumber;
            if (r3 != (-1)) goto L12;
            return false;
        L12:
            if (r0.windowSequenceNumber <= r3) goto L15;
            return true;
        L15:
            if (this.adMediaPeriodId != null) goto L17;
            return false;
        L17:
            int r02 = r10.timeline.getIndexOfPeriod(r0.periodUid);
            int r32 = r10.timeline.getIndexOfPeriod(this.adMediaPeriodId.periodUid);
            MediaSource.MediaPeriodId r4 = r10.mediaPeriodId;
            if (r4.windowSequenceNumber < this.adMediaPeriodId.windowSequenceNumber) goto L40;
            if (r02 < r32) goto L40;
            if (r02 <= r32) goto L23;
            return true;
        L23:
            boolean r03 = r4.isAd();
            MediaSource.MediaPeriodId r102 = r10.mediaPeriodId;
            if (r03 == false) goto L33;
            int r04 = r102.adGroupIndex;
            int r103 = r102.adIndexInAdGroup;
            MediaSource.MediaPeriodId r9 = this.adMediaPeriodId;
            int r33 = r9.adGroupIndex;
            if (r04 > r33) goto L32;
            if (r04 == r33) goto L29;
        L31:
            return false;
        L29:
            if (r103 <= r9.adIndexInAdGroup) goto L31;
        L32:
            return true;
        L33:
            int r104 = r102.nextAdGroupIndex;
            if (r104 != (-1)) goto L36;
        L39:
            return true;
        L36:
            if (r104 > this.adMediaPeriodId.adGroupIndex) goto L39;
            return false;
        L40:
            return false;
        L5:
            if (this.windowIndex == r10.windowIndex) goto L7;
            return true;
        L7:
            return false;
        }

        public void maybeSetWindowSequenceNumber(int r5, MediaSource.MediaPeriodId r6) {
            if (this.windowSequenceNumber == (-1)) goto L5;
            return;
        L5:
            if (r5 != this.windowIndex) goto L12;
            if (r6 != null) goto L8;
            return;
        L8:
            if (r6.windowSequenceNumber < DefaultPlaybackSessionManager.access$600(this.this$0)) goto L14;
            this.windowSequenceNumber = r6.windowSequenceNumber;
            return;
        L14:
            return;
        }

        public boolean tryResolvingToNewTimeline(Timeline r3, Timeline r4) {
            int r32 = resolveWindowIndexToNewTimeline(r3, r4, this.windowIndex);
            this.windowIndex = r32;
            if (r32 != (-1)) goto L5;
            return false;
        L5:
            MediaSource.MediaPeriodId r2 = this.adMediaPeriodId;
            if (r2 != null) goto L9;
            return true;
        L9:
            if (r4.getIndexOfPeriod(r2.periodUid) == (-1)) goto L11;
            return true;
        L11:
            return false;
        }
    }

    public static /* synthetic */ String $r8$lambda$EuouJd6Wgl4KHPnNDIH2Q_wUwdk() {
        return generateDefaultSessionId();
    }

    static {
        DEFAULT_SESSION_ID_GENERATOR = new DefaultPlaybackSessionManager$$ExternalSyntheticLambda0();
        RANDOM = new Random();
    }

    public DefaultPlaybackSessionManager() {
        this(DEFAULT_SESSION_ID_GENERATOR);
    }

    public DefaultPlaybackSessionManager(Supplier<String> r3) {
        this.sessionIdGenerator = r3;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.sessions = new HashMap();
        this.currentTimeline = Timeline.EMPTY;
        this.lastRemovedCurrentWindowSequenceNumber = -1;
    }

    public static /* synthetic */ long access$600(DefaultPlaybackSessionManager r2) {
        return r2.getMinWindowSequenceNumber();
    }

    public static /* synthetic */ Timeline.Window access$700(DefaultPlaybackSessionManager r0) {
        return r0.window;
    }

    public static /* synthetic */ Timeline.Period access$800(DefaultPlaybackSessionManager r0) {
        return r0.period;
    }

    private void clearCurrentSession(SessionDescriptor r5) {
        if (SessionDescriptor.access$100(r5) == (-1)) goto L5;
        this.lastRemovedCurrentWindowSequenceNumber = SessionDescriptor.access$100(r5);
    L5:
        this.currentSessionId = null;
    }

    private static String generateDefaultSessionId() {
        byte[] r0 = new byte[12];
        RANDOM.nextBytes(r0);
        return Base64.encodeToString(r0, 10);
    }

    private long getMinWindowSequenceNumber() {
        SessionDescriptor r0 = this.sessions.get(this.currentSessionId);
        if (r0 == null) goto L9;
        if (SessionDescriptor.access$100(r0) == (-1)) goto L9;
        return SessionDescriptor.access$100(r0);
    L9:
        return this.lastRemovedCurrentWindowSequenceNumber + 1;
    }

    private SessionDescriptor getOrAddSession(int r10, MediaSource.MediaPeriodId r11) {
        Iterator<SessionDescriptor> r0 = this.sessions.values().iterator();
        SessionDescriptor r1 = null;
        long r2 = LongCompanionObject.MAX_VALUE;
    L4:
        if (r0.hasNext() == false) goto L19;
        SessionDescriptor r4 = r0.next();
        r4.maybeSetWindowSequenceNumber(r10, r11);
        if (r4.belongsToSession(r10, r11) == false) goto L4;
        long r5 = SessionDescriptor.access$100(r4);
        if (r5 == (-1)) goto L18;
        if (r5 < r2) goto L18;
        if (r5 != r2) goto L4;
        if (SessionDescriptor.access$500((SessionDescriptor) Util.castNonNull(r1)) == null) goto L4;
        if (SessionDescriptor.access$500(r4) == null) goto L4;
        r1 = r4;
    L18:
        r1 = r4;
        r2 = r5;
        goto L4
    L19:
        if (r1 != null) goto L37;
        String r02 = this.sessionIdGenerator.get();
        SessionDescriptor r12 = new SessionDescriptor(this, r02, r10, r11);
        this.sessions.put(r02, r12);
        return r12;
    L37:
        return r1;
    }

    @RequiresNonNull({"listener"})
    private void updateCurrentSession(AnalyticsListener.EventTime r7) {
        if (r7.timeline.isEmpty() == false) goto L8;
        String r72 = this.currentSessionId;
        if (r72 == null) goto L23;
        clearCurrentSession((SessionDescriptor) Assertions.checkNotNull(this.sessions.get(r72)));
        return;
    L23:
        return;
    L8:
        SessionDescriptor r0 = this.sessions.get(this.currentSessionId);
        SessionDescriptor r1 = getOrAddSession(r7.windowIndex, r7.mediaPeriodId);
        this.currentSessionId = SessionDescriptor.access$000(r1);
        updateSessions(r7);
        MediaSource.MediaPeriodId r2 = r7.mediaPeriodId;
        if (r2 != null) goto L11;
        return;
    L11:
        if (r2.isAd() == false) goto L25;
        if (r0 != null) goto L14;
    L21:
        MediaSource.MediaPeriodId r22 = r7.mediaPeriodId;
        MediaSource.MediaPeriodId r02 = new MediaSource.MediaPeriodId(r22.periodUid, r22.windowSequenceNumber);
        SessionDescriptor r03 = getOrAddSession(r7.windowIndex, r02);
        this.listener.onAdPlaybackStarted(r7, SessionDescriptor.access$000(r03), SessionDescriptor.access$000(r1));
        return;
    L14:
        if (SessionDescriptor.access$100(r0) != r7.mediaPeriodId.windowSequenceNumber) goto L21;
        if (SessionDescriptor.access$500(r0) == null) goto L21;
        if (SessionDescriptor.access$500(r0).adGroupIndex != r7.mediaPeriodId.adGroupIndex) goto L21;
        if (SessionDescriptor.access$500(r0).adIndexInAdGroup != r7.mediaPeriodId.adIndexInAdGroup) goto L21;
        return;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager
    public synchronized void finishAllSessions(AnalyticsListener.EventTime r5) {
        monitor-enter(this);
        String r0 = this.currentSessionId;     // Catch: Throwable -> L6
        if (r0 == null) goto L8;
        clearCurrentSession((SessionDescriptor) Assertions.checkNotNull(this.sessions.get(r0)));     // Catch: Throwable -> L6
    L8:
        Iterator<SessionDescriptor> r02 = this.sessions.values().iterator();     // Catch: Throwable -> L6
    L9:
        if (r02.hasNext() == false) goto L17;
        SessionDescriptor r1 = r02.next();     // Catch: Throwable -> L6
        r02.remove();     // Catch: Throwable -> L6
        if (SessionDescriptor.access$300(r1) == false) goto L9;
        PlaybackSessionManager.Listener r2 = this.listener;     // Catch: Throwable -> L6
        if (r2 == null) goto L9;
        r2.onSessionFinished(r5, SessionDescriptor.access$000(r1), false);     // Catch: Throwable -> L6
        goto L9
    L17:
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager
    public synchronized String getActiveSessionId() {
        monitor-enter(this);
        String r0 = this.currentSessionId;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager
    public synchronized String getSessionForMediaPeriodId(Timeline r3, MediaSource.MediaPeriodId r4) {
        monitor-enter(this);
        String r32 = SessionDescriptor.access$000(getOrAddSession(r3.getPeriodByUid(r4.periodUid, this.period).windowIndex, r4));     // Catch: Throwable -> L6
        monitor-exit(this);
        return r32;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager
    public void setListener(PlaybackSessionManager.Listener r1) {
        this.listener = r1;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager
    public synchronized void updateSessions(AnalyticsListener.EventTime r22) {
        monitor-enter(this);
        Assertions.checkNotNull(this.listener);     // Catch: Throwable -> L23
        if (r22.timeline.isEmpty() == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        MediaSource.MediaPeriodId r2 = r22.mediaPeriodId;     // Catch: Throwable -> L23
        if (r2 != null) goto L12;
    L25:
        SessionDescriptor r23 = getOrAddSession(r22.windowIndex, r22.mediaPeriodId);     // Catch: Throwable -> L23
        if (this.currentSessionId != null) goto L28;
        this.currentSessionId = SessionDescriptor.access$000(r23);     // Catch: Throwable -> L23
    L28:
        MediaSource.MediaPeriodId r3 = r22.mediaPeriodId;     // Catch: Throwable -> L23
        if (r3 == null) goto L36;
        if (r3.isAd() == false) goto L36;
        MediaSource.MediaPeriodId r32 = r22.mediaPeriodId;     // Catch: Throwable -> L23
        MediaSource.MediaPeriodId r10 = new MediaSource.MediaPeriodId(r32.periodUid, r32.windowSequenceNumber, r32.adGroupIndex);     // Catch: Throwable -> L23
        SessionDescriptor r33 = getOrAddSession(r22.windowIndex, r10);     // Catch: Throwable -> L23
        if (SessionDescriptor.access$300(r33) == true) goto L36;
        SessionDescriptor.access$302(r33, true);     // Catch: Throwable -> L23
        r22.timeline.getPeriodByUid(r22.mediaPeriodId.periodUid, this.period);     // Catch: Throwable -> L23
        long r11 = Math.max(0, Util.usToMs(this.period.getAdGroupTimeUs(r22.mediaPeriodId.adGroupIndex)) + this.period.getPositionInWindowMs());     // Catch: Throwable -> L23
        AnalyticsListener.EventTime r5 = new AnalyticsListener.EventTime(r22.realtimeMs, r22.timeline, r22.windowIndex, r10, r11, r22.currentTimeline, r22.currentWindowIndex, r22.currentMediaPeriodId, r22.currentPlaybackPositionMs, r22.totalBufferedDurationMs);     // Catch: Throwable -> L23
        this.listener.onSessionCreated(r5, SessionDescriptor.access$000(r33));     // Catch: Throwable -> L23
    L36:
        if (SessionDescriptor.access$300(r23) == true) goto L39;
        SessionDescriptor.access$302(r23, true);     // Catch: Throwable -> L23
        this.listener.onSessionCreated(r22, SessionDescriptor.access$000(r23));     // Catch: Throwable -> L23
    L39:
        if (SessionDescriptor.access$000(r23).equals(this.currentSessionId) == true) goto L41;
    L43:
        monitor-exit(this);
        return;
    L41:
        if (SessionDescriptor.access$400(r23) == true) goto L43;
        SessionDescriptor.access$402(r23, true);     // Catch: Throwable -> L23
        this.listener.onSessionActive(r22, SessionDescriptor.access$000(r23));     // Catch: Throwable -> L23
        goto L43
    L12:
        if (r2.windowSequenceNumber >= getMinWindowSequenceNumber()) goto L15;
        monitor-exit(this);
        return;
    L15:
        SessionDescriptor r24 = this.sessions.get(this.currentSessionId);     // Catch: Throwable -> L23
        if (r24 == null) goto L25;
        if (SessionDescriptor.access$100(r24) != (-1)) goto L25;
        if (SessionDescriptor.access$200(r24) == r22.windowIndex) goto L25;
        monitor-exit(this);
        return;
    L23:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager
    public synchronized void updateSessionsWithDiscontinuity(AnalyticsListener.EventTime r7, int r8) {
        monitor-enter(this);
        Assertions.checkNotNull(this.listener);     // Catch: Throwable -> L20
        if (r8 != 0) goto L6;
        boolean r82 = true;
    L7:
        Iterator<SessionDescriptor> r2 = this.sessions.values().iterator();     // Catch: Throwable -> L20
    L9:
        if (r2.hasNext() == false) goto L26;
        SessionDescriptor r3 = r2.next();     // Catch: Throwable -> L20
        if (r3.isFinishedAtEventTime(r7) == false) goto L9;
        r2.remove();     // Catch: Throwable -> L20
        if (SessionDescriptor.access$300(r3) == false) goto L9;
        boolean r4 = SessionDescriptor.access$000(r3).equals(this.currentSessionId);     // Catch: Throwable -> L20
        if (r82 == false) goto L22;
        if (r4 == false) goto L22;
        if (SessionDescriptor.access$400(r3) == false) goto L22;
        boolean r5 = true;
    L23:
        if (r4 == false) goto L25;
        clearCurrentSession(r3);     // Catch: Throwable -> L20
    L25:
        this.listener.onSessionFinished(r7, SessionDescriptor.access$000(r3), r5);     // Catch: Throwable -> L20
    L22:
        r5 = false;
        goto L23
    L26:
        updateCurrentSession(r7);     // Catch: Throwable -> L20
        monitor-exit(this);
        return;
    L6:
        r82 = false;
    L20:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager
    public synchronized void updateSessionsWithTimelineChange(AnalyticsListener.EventTime r6) {
        monitor-enter(this);
        Assertions.checkNotNull(this.listener);     // Catch: Throwable -> L11
        Timeline r0 = this.currentTimeline;     // Catch: Throwable -> L11
        this.currentTimeline = r6.timeline;     // Catch: Throwable -> L11
        Iterator<SessionDescriptor> r1 = this.sessions.values().iterator();     // Catch: Throwable -> L11
    L5:
        if (r1.hasNext() == false) goto L19;
        SessionDescriptor r2 = r1.next();     // Catch: Throwable -> L11
        if (r2.tryResolvingToNewTimeline(r0, this.currentTimeline) == false) goto L13;
        if (r2.isFinishedAtEventTime(r6) == false) goto L5;
    L13:
        r1.remove();     // Catch: Throwable -> L11
        if (SessionDescriptor.access$300(r2) == false) goto L5;
        if (SessionDescriptor.access$000(r2).equals(this.currentSessionId) == false) goto L18;
        clearCurrentSession(r2);     // Catch: Throwable -> L11
    L18:
        this.listener.onSessionFinished(r6, SessionDescriptor.access$000(r2), false);     // Catch: Throwable -> L11
        goto L5
    L19:
        updateCurrentSession(r6);     // Catch: Throwable -> L11
        monitor-exit(this);
        return;
    L11:
        th = move-exception;
        throw th;
    }
}
