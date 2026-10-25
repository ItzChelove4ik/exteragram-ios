package androidx.media3.exoplayer.trackselection;

import android.os.SystemClock;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public abstract class BaseTrackSelection implements ExoTrackSelection {
    private final long[] excludeUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    private boolean playWhenReady;
    protected final int[] tracks;
    private final int type;

    public static /* synthetic */ int $r8$lambda$_Nx2IO7J9FRVwHR6yPRlPDHVDT4(Format r0, Format r1) {
        return r1.bitrate - r0.bitrate;
    }

    public BaseTrackSelection(TrackGroup r2, int... r3) {
        this(r2, r3, 0);
    }

    public BaseTrackSelection(TrackGroup r4, int[] r5, int r6) {
        if (r5.length <= 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.type = r6;
        this.group = (TrackGroup) Assertions.checkNotNull(r4);
        int r62 = r5.length;
        this.length = r62;
        this.formats = new Format[r62];
        int r63 = 0;
    L7:
        int r02 = r5.length;
        Format[] r2 = this.formats;
        if (r63 >= r02) goto L10;
        r2[r63] = r4.getFormat(r5[r63]);
        r63 = r63 + 1;
        goto L7
    L10:
        Arrays.sort(r2, new BaseTrackSelection$$ExternalSyntheticLambda0());
        this.tracks = new int[this.length];
        int r52 = 0;
    L11:
        int r64 = this.length;
        if (r52 >= r64) goto L14;
        this.tracks[r52] = r4.indexOf(this.formats[r52]);
        r52 = r52 + 1;
        goto L11
    L14:
        this.excludeUntilTimes = new long[r64];
        this.playWhenReady = false;
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void disable() {
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void enable() {
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L15;
        BaseTrackSelection r52 = (BaseTrackSelection) r5;
        if (this.group.equals(r52.group) == false) goto L15;
        if (Arrays.equals(this.tracks, r52.tracks) == false) goto L15;
        return true;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long r1, List<? extends MediaChunk> r3) {
        return r3.size();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public boolean excludeTrack(int r10, long r11) {
        long r0 = SystemClock.elapsedRealtime();
        boolean r2 = isTrackExcluded(r10, r0);
        int r4 = 0;
    L4:
        if (r4 >= this.length) goto L12;
        if (r2 == true) goto L12;
        if (r4 != r10) goto L8;
    L10:
        r2 = false;
    L11:
        r4 = r4 + 1;
        goto L4
    L8:
        if (isTrackExcluded(r4, r0) == true) goto L10;
        r2 = true;
    L12:
        if (r2 == true) goto L14;
        return false;
    L14:
        long[] r9 = this.excludeUntilTimes;
        r9[r10] = Math.max(r9[r10], Util.addWithOverflowDefault(r0, r11, LongCompanionObject.MAX_VALUE));
        return true;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final Format getFormat(int r1) {
        return this.formats[r1];
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int getIndexInTrackGroup(int r1) {
        return this.tracks[r1];
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[getSelectedIndex()];
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L6;
        this.hashCode = (System.identityHashCode(this.group) * 31) + Arrays.hashCode(this.tracks);
    L6:
        return this.hashCode;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int indexOf(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.length) goto L9;
        if (this.tracks[r0] == r3) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int indexOf(Format r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.length) goto L9;
        if (this.formats[r0] == r3) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public boolean isTrackExcluded(int r3, long r4) {
        if (this.excludeUntilTimes[r3] <= r4) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public final int length() {
        return this.tracks.length;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlayWhenReadyChanged(boolean r1) {
        this.playWhenReady = r1;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float r1) {
    }
}
