package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.HashMap;
import java.util.Iterator;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.mvel2.MVEL;

/* loaded from: classes4.dex */
public class DefaultLoadControl implements LoadControl {
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private final HashMap<PlayerId, PlayerLoadingState> loadingStates;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private final int targetBufferBytesOverwrite;
    private long threadId;

    /* renamed from: androidx.media3.exoplayer.DefaultLoadControl$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private DefaultAllocator allocator;
        private int backBufferDurationMs;
        private int bufferForPlaybackAfterRebufferMs;
        private int bufferForPlaybackMs;
        private boolean buildCalled;
        private int maxBufferMs;
        private int minBufferMs;
        private boolean prioritizeTimeOverSizeThresholds;
        private boolean retainBackBufferFromKeyframe;
        private int targetBufferBytes;

        public Builder() {
            this.minBufferMs = 50000;
            this.maxBufferMs = 50000;
            this.bufferForPlaybackMs = 1000;
            this.bufferForPlaybackAfterRebufferMs = CastStatusCodes.AUTHENTICATION_FAILED;
            this.targetBufferBytes = -1;
            this.prioritizeTimeOverSizeThresholds = false;
            this.backBufferDurationMs = 0;
            this.retainBackBufferFromKeyframe = false;
        }

        public DefaultLoadControl build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.allocator != null) goto L6;
            this.allocator = new DefaultAllocator(true, 65536);
        L6:
            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.backBufferDurationMs, this.retainBackBufferFromKeyframe);
        }

        public Builder setAllocator(DefaultAllocator r2) {
            Assertions.checkState(!this.buildCalled);
            this.allocator = r2;
            return this;
        }

        public Builder setBackBuffer(int r4, boolean r5) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.access$000(r4, 0, "backBufferDurationMs", MVEL.VERSION_SUB);
            this.backBufferDurationMs = r4;
            this.retainBackBufferFromKeyframe = r5;
            return this;
        }

        public Builder setBufferDurationsMs(int r5, int r6, int r7, int r8) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.access$000(r7, 0, "bufferForPlaybackMs", MVEL.VERSION_SUB);
            DefaultLoadControl.access$000(r8, 0, "bufferForPlaybackAfterRebufferMs", MVEL.VERSION_SUB);
            DefaultLoadControl.access$000(r5, r7, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.access$000(r5, r8, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.access$000(r6, r5, "maxBufferMs", "minBufferMs");
            this.minBufferMs = r5;
            this.maxBufferMs = r6;
            this.bufferForPlaybackMs = r7;
            this.bufferForPlaybackAfterRebufferMs = r8;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean r2) {
            Assertions.checkState(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholds = r2;
            return this;
        }

        public Builder setTargetBufferBytes(int r2) {
            Assertions.checkState(!this.buildCalled);
            this.targetBufferBytes = r2;
            return this;
        }
    }

    public static class PlayerLoadingState {
        public boolean isLoading;
        public int targetBufferBytes;

        private PlayerLoadingState() {
        }

        public /* synthetic */ PlayerLoadingState(AnonymousClass1 r1) {
            this();
        }
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536), 50000, 50000, 1000, CastStatusCodes.AUTHENTICATION_FAILED, -1, false, 0, false);
    }

    public DefaultLoadControl(DefaultAllocator r6, int r7, int r8, int r9, int r10, int r11, boolean r12, int r13, boolean r14) {
        assertGreaterOrEqual(r9, 0, "bufferForPlaybackMs", MVEL.VERSION_SUB);
        assertGreaterOrEqual(r10, 0, "bufferForPlaybackAfterRebufferMs", MVEL.VERSION_SUB);
        assertGreaterOrEqual(r7, r9, "minBufferMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(r7, r10, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(r8, r7, "maxBufferMs", "minBufferMs");
        assertGreaterOrEqual(r13, 0, "backBufferDurationMs", MVEL.VERSION_SUB);
        this.allocator = r6;
        this.minBufferUs = Util.msToUs(r7);
        this.maxBufferUs = Util.msToUs(r8);
        this.bufferForPlaybackUs = Util.msToUs(r9);
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs(r10);
        this.targetBufferBytesOverwrite = r11;
        this.prioritizeTimeOverSizeThresholds = r12;
        this.backBufferDurationUs = Util.msToUs(r13);
        this.retainBackBufferFromKeyframe = r14;
        this.loadingStates = new HashMap();
        this.threadId = -1;
    }

    public static /* synthetic */ void access$000(int r0, int r1, String r2, String r3) {
        assertGreaterOrEqual(r0, r1, r2, r3);
    }

    private static void assertGreaterOrEqual(int r0, int r1, String r2, String r3) {
        if (r0 < r1) goto L4;
        boolean r02 = true;
    L5:
        Assertions.checkArgument(r02, r2 + " cannot be less than " + r3);
        return;
    L4:
        r02 = false;
        goto L5
    }

    private static int getDefaultBufferSize(int r2) {
        switch(r2) {
            case -2: goto L16;
            case -1: goto L15;
            case 0: goto L13;
            case 1: goto L12;
            case 2: goto L10;
            case 3: goto L9;
            case 4: goto L7;
            case 5: goto L6;
            case 6: goto L6;
            default: goto L4;
        };
    L4:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L6:
        return 131072;
    L7:
        return 26214400;
    L9:
        return 131072;
    L10:
        return 131072000;
    L12:
        return 13107200;
    L13:
        return 144310272;
    L15:
        return 13107200;
    L16:
        return 0;
    }

    private void removePlayer(PlayerId r2) {
        if (this.loadingStates.remove(r2) == null) goto L6;
        updateAllocator();
        return;
    }

    private void resetPlayerLoadingState(PlayerId r2) {
        PlayerLoadingState r22 = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(r2));
        int r1 = this.targetBufferBytesOverwrite;
        if (r1 != (-1)) goto L5;
        r1 = 13107200;
    L5:
        r22.targetBufferBytes = r1;
        r22.isLoading = false;
    }

    private void updateAllocator() {
        boolean r0 = this.loadingStates.isEmpty();
        DefaultAllocator r1 = this.allocator;
        if (r0 == false) goto L6;
        r1.reset();
        return;
    L6:
        r1.setTargetBufferSize(calculateTotalTargetBufferBytes());
    }

    public int calculateTargetBufferBytes(ExoTrackSelection[] r4) {
        int r3 = r4.length;
        int r0 = 0;
        int r1 = 0;
    L3:
        if (r0 >= r3) goto L9;
        ExoTrackSelection r2 = r4[r0];
        if (r2 == null) goto L7;
        r1 = r1 + getDefaultBufferSize(r2.getTrackGroup().type);
    L7:
        r0 = r0 + 1;
        goto L3
    L9:
        return Math.max(13107200, r1);
    }

    public int calculateTotalTargetBufferBytes() {
        Iterator<PlayerLoadingState> r2 = this.loadingStates.values().iterator();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r0 = r0 + r2.next().targetBufferBytes;
        goto L4
    L6:
        return r0;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public long getBackBufferDurationUs(PlayerId r1) {
        return this.backBufferDurationUs;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onPrepared(PlayerId r7) {
        long r0 = Thread.currentThread().getId();
        long r2 = this.threadId;
        if (r2 != (-1)) goto L5;
    L8:
        boolean r22 = true;
    L9:
        Assertions.checkState(r22, "Players that share the same LoadControl must share the same playback thread. See ExoPlayer.Builder.setPlaybackLooper(Looper).");
        this.threadId = r0;
        if (this.loadingStates.containsKey(r7) == true) goto L12;
        this.loadingStates.put(r7, new PlayerLoadingState(null));
    L12:
        resetPlayerLoadingState(r7);
        return;
    L5:
        if (r2 == r0) goto L8;
        r22 = false;
        goto L9
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onReleased(PlayerId r3) {
        removePlayer(r3);
        if (this.loadingStates.isEmpty() == false) goto L6;
        this.threadId = -1;
        return;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onStopped(PlayerId r1) {
        removePlayer(r1);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onTracksSelected(LoadControl.Parameters r2, TrackGroupArray r3, ExoTrackSelection[] r4) {
        PlayerLoadingState r22 = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(r2.playerId));
        int r32 = this.targetBufferBytesOverwrite;
        if (r32 != (-1)) goto L5;
        r32 = calculateTargetBufferBytes(r4);
    L5:
        r22.targetBufferBytes = r32;
        updateAllocator();
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean retainBackBufferFromKeyframe(PlayerId r1) {
        return this.retainBackBufferFromKeyframe;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldContinueLoading(LoadControl.Parameters r12) {
        PlayerLoadingState r0 = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(r12.playerId));
        boolean r3 = true;
        if (this.allocator.getTotalBytesAllocated() < calculateTotalTargetBufferBytes()) goto L5;
        boolean r1 = true;
    L6:
        long r5 = this.minBufferUs;
        float r2 = r12.playbackSpeed;
        if (r2 <= 1.0f) goto L9;
        r5 = Math.min(Util.getMediaDurationForPlayoutDuration(r5, r2), this.maxBufferUs);
    L9:
        long r52 = Math.max(r5, 500000);
        long r9 = r12.bufferedDurationUs;
        if (r9 >= r52) goto L22;
        if (this.prioritizeTimeOverSizeThresholds == true) goto L16;
        if (r1 == false) goto L16;
        r3 = false;
    L16:
        r0.isLoading = r3;
        if (r3 == true) goto L26;
        if (r9 >= 500000) goto L26;
        Log.w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
    L26:
        return r0.isLoading;
    L22:
        if (r9 >= this.maxBufferUs) goto L24;
        if (r1 == false) goto L26;
    L24:
        r0.isLoading = false;
        goto L26
    L5:
        r1 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldContinuePreloading(Timeline r1, MediaSource.MediaPeriodId r2, long r3) {
        Iterator<PlayerLoadingState> r0 = this.loadingStates.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        if (r0.next().isLoading == false) goto L4;
        return false;
    L9:
        return true;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldStartPlayback(LoadControl.Parameters r9) {
        long r0 = Util.getPlayoutDurationForMediaDuration(r9.bufferedDurationUs, r9.playbackSpeed);
        if (r9.rebuffering == false) goto L5;
        long r2 = this.bufferForPlaybackAfterRebufferUs;
    L6:
        long r4 = r9.targetLiveOffsetUs;
        if (r4 == (-9223372036854775807L)) goto L10;
        r2 = Math.min(r4 / 2, r2);
    L10:
        if (r2 > 0) goto L12;
        return true;
    L12:
        if (r0 < r2) goto L14;
        return true;
    L14:
        if (this.prioritizeTimeOverSizeThresholds == false) goto L16;
        return false;
    L16:
        if (this.allocator.getTotalBytesAllocated() >= calculateTotalTargetBufferBytes()) goto L24;
        return false;
    L24:
        return true;
    L5:
        r2 = this.bufferForPlaybackUs;
        goto L6
    }
}
