package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.ForwardingTrackSelection;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private final HashMap<TrackGroup, TrackGroup> childTrackGroupByMergedTrackGroup;
    private final ArrayList<MediaPeriod> childrenPendingPreparation;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private MediaPeriod[] enabledPeriods;
    private final MediaPeriod[] periods;
    private final boolean[] periodsWithTimeOffsets;
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices;
    private TrackGroupArray trackGroups;

    public static final class MergingMediaPeriodTrackSelection extends ForwardingTrackSelection {
        private final TrackGroup trackGroup;

        public MergingMediaPeriodTrackSelection(ExoTrackSelection r1, TrackGroup r2) {
            super(r1);
            this.trackGroup = r2;
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection
        public boolean equals(Object r2) {
            if (super.equals(r2) == true) goto L5;
            return false;
        L5:
            if ((r2 instanceof MergingMediaPeriodTrackSelection) == true) goto L8;
            return false;
        L8:
            return this.trackGroup.equals(((MergingMediaPeriodTrackSelection) r2).trackGroup);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public Format getFormat(int r2) {
            return this.trackGroup.getFormat(getWrappedInstance().getIndexInTrackGroup(r2));
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Format getSelectedFormat() {
            return this.trackGroup.getFormat(getWrappedInstance().getSelectedIndexInTrackGroup());
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public TrackGroup getTrackGroup() {
            return this.trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.ForwardingTrackSelection
        public int hashCode() {
            return (super.hashCode() * 31) + this.trackGroup.hashCode();
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(Format r2) {
            return getWrappedInstance().indexOf(this.trackGroup.indexOf(r2));
        }
    }

    public static /* synthetic */ List $r8$lambda$_OSX7kD5MuhqDmCs7DjaFeCeIDA(MediaPeriod r0) {
        return r0.getTrackGroups().getTrackTypes();
    }

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory r6, long[] r7, MediaPeriod... r8) {
        this.compositeSequenceableLoaderFactory = r6;
        this.periods = r8;
        this.childrenPendingPreparation = new ArrayList();
        this.childTrackGroupByMergedTrackGroup = new HashMap();
        this.compositeSequenceableLoader = r6.empty();
        this.streamPeriodIndices = new IdentityHashMap();
        int r62 = 0;
        this.enabledPeriods = new MediaPeriod[0];
        this.periodsWithTimeOffsets = new boolean[r8.length];
    L4:
        if (r62 >= r8.length) goto L9;
        long r0 = r7[r62];
        if (r0 == 0) goto L8;
        this.periodsWithTimeOffsets[r62] = true;
        this.periods[r62] = new TimeOffsetMediaPeriod(r8[r62], r0);
    L8:
        r62 = r62 + 1;
        goto L4
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r5) {
        if (this.childrenPendingPreparation.isEmpty() == true) goto L9;
        int r0 = this.childrenPendingPreparation.size();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        this.childrenPendingPreparation.get(r2).continueLoading(r5);
        r2 = r2 + 1;
        goto L5
    L7:
        return false;
    L9:
        return this.compositeSequenceableLoader.continueLoading(r5);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r4, boolean r6) {
        MediaPeriod[] r3 = this.enabledPeriods;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].discardBuffer(r4, r6);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r4, SeekParameters r6) {
        MediaPeriod[] r0 = this.enabledPeriods;
        if (r0.length <= 0) goto L5;
        MediaPeriod r3 = r0[0];
    L7:
        return r3.getAdjustedSeekPositionUs(r4, r6);
    L5:
        r3 = this.periods[0];
        goto L7
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public MediaPeriod getChildPeriod(int r2) {
        boolean r0 = this.periodsWithTimeOffsets[r2];
        MediaPeriod[] r1 = this.periods;
        if (r0 == false) goto L7;
        return ((TimeOffsetMediaPeriod) r1[r2]).getWrappedMediaPeriod();
    L7:
        return r1[r2];
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
        MediaPeriod[] r3 = this.periods;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].maybeThrowPrepareError();
        r1 = r1 + 1;
        goto L3
    }

    public void onContinueLoadingRequested(MediaPeriod r1) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public /* bridge */ /* synthetic */ void onContinueLoadingRequested(SequenceableLoader r1) {
        onContinueLoadingRequested((MediaPeriod) r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod r14) {
        this.childrenPendingPreparation.remove(r14);
        if (this.childrenPendingPreparation.isEmpty() == true) goto L5;
        return;
    L5:
        MediaPeriod[] r142 = this.periods;
        int r0 = r142.length;
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r3 = r3 + r142[r2].getTrackGroups().length;
        r2 = r2 + 1;
        goto L6
    L8:
        TrackGroup[] r143 = new TrackGroup[r3];
        int r02 = 0;
        int r22 = 0;
    L9:
        MediaPeriod[] r32 = this.periods;
        if (r02 >= r32.length) goto L22;
        TrackGroupArray r33 = r32[r02].getTrackGroups();
        int r4 = r33.length;
        int r5 = 0;
    L12:
        if (r5 >= r4) goto L21;
        TrackGroup r6 = r33.get(r5);
        Format[] r7 = new Format[r6.length];
        int r8 = 0;
    L15:
        if (r8 >= r6.length) goto L20;
        Format r9 = r6.getFormat(r8);
        Format.Builder r11 = r9.buildUpon();
        StringBuilder r12 = new StringBuilder();
        r12.append(r02);
        r12.append(":");
        String r92 = r9.id;
        if (r92 != null) goto L19;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        r12.append(r92);
        r7[r8] = r11.setId(r12.toString()).build();
        r8 = r8 + 1;
        goto L15
    L20:
        TrackGroup r82 = new TrackGroup(r02 + ":" + r6.id, r7);
        this.childTrackGroupByMergedTrackGroup.put(r82, r6);
        r143[r22] = r82;
        r5 = r5 + 1;
        r22 = r22 + 1;
        goto L12
    L21:
        r02 = r02 + 1;
        goto L9
    L22:
        this.trackGroups = new TrackGroupArray(r143);
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r4, long r5) {
        this.callback = r4;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        MediaPeriod[] r42 = this.periods;
        int r0 = r42.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r42[r1].prepare(this, r5);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        MediaPeriod[] r1 = this.enabledPeriods;
        int r2 = r1.length;
        long r3 = -9223372036854775807L;
        long r7 = -9223372036854775807L;
        int r6 = 0;
    L3:
        if (r6 >= r2) goto L33;
        MediaPeriod r9 = r1[r6];
        long r10 = r9.readDiscontinuity();
        if (r10 != r3) goto L7;
        long r16 = r3;
        if (r7 == r16) goto L32;
        if (r9.seekToUs(r7) == r7) goto L32;
        Segment$$ExternalSyntheticBUOutline1.m("Unexpected child seekToUs result.");
        return 0;
    L32:
        r6 = r6 + 1;
        r3 = r16;
        goto L3
    L7:
        if (r7 != r3) goto L20;
        MediaPeriod[] r72 = this.enabledPeriods;
        int r8 = r72.length;
        int r12 = 0;
    L9:
        r16 = r3;
        if (r12 >= r8) goto L19;
        MediaPeriod r32 = r72[r12];
        if (r32 == r9) goto L19;
        if (r32.seekToUs(r10) != r10) goto L17;
        r12 = r12 + 1;
        r3 = r16;
        goto L9
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("Unexpected child seekToUs result.");
        return 0;
    L19:
        r7 = r10;
        goto L32
    L20:
        r16 = r3;
        if (r10 == r7) goto L32;
        Segment$$ExternalSyntheticBUOutline1.m("Conflicting discontinuities.");
        return 0;
    L33:
        return r7;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r1) {
        this.compositeSequenceableLoader.reevaluateBuffer(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r4) {
        long r42 = this.enabledPeriods[0].seekToUs(r4);
        int r0 = 1;
    L3:
        MediaPeriod[] r1 = this.enabledPeriods;
        if (r0 >= r1.length) goto L10;
        if (r1[r0].seekToUs(r42) != r42) goto L8;
        r0 = r0 + 1;
        goto L3
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Unexpected child seekToUs result.");
        return 0;
    L10:
        return r42;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r21, boolean[] r22, SampleStream[] r23, boolean[] r24, long r25) {
        int[] r3 = new int[r21.length];
        int[] r4 = new int[r21.length];
        int r5 = 0;
        int r6 = 0;
    L4:
        if (r6 >= r21.length) goto L18;
        SampleStream r7 = r23[r6];
        if (r7 != null) goto L8;
        Integer r8 = null;
    L10:
        if (r8 != null) goto L12;
        int r82 = -1;
    L13:
        r3[r6] = r82;
        ExoTrackSelection r83 = r21[r6];
        if (r83 == null) goto L16;
        String r72 = r83.getTrackGroup().id;
        r4[r6] = Integer.parseInt(r72.substring(0, r72.indexOf(":")));
    L17:
        r6 = r6 + 1;
        goto L4
    L16:
        r4[r6] = -1;
        goto L17
    L12:
        r82 = r8.intValue();
        goto L13
    L8:
        r8 = this.streamPeriodIndices.get(r7);
        goto L10
    L18:
        this.streamPeriodIndices.clear();
        int r62 = r21.length;
        SampleStream[] r73 = new SampleStream[r62];
        SampleStream[] r13 = new SampleStream[r21.length];
        ExoTrackSelection[] r11 = new ExoTrackSelection[r21.length];
        ArrayList r9 = new ArrayList(this.periods.length);
        long r15 = r25;
        int r10 = 0;
    L20:
        if (r10 >= this.periods.length) goto L57;
        int r12 = r5;
    L23:
        if (r12 >= r21.length) goto L33;
        if (r3[r12] != r10) goto L27;
        SampleStream r14 = r23[r12];
    L28:
        r13[r12] = r14;
        if (r4[r12] != r10) goto L31;
        ExoTrackSelection r142 = (ExoTrackSelection) Assertions.checkNotNull(r21[r12]);
        r11[r12] = new MergingMediaPeriodTrackSelection(r142, (TrackGroup) Assertions.checkNotNull(this.childTrackGroupByMergedTrackGroup.get(r142.getTrackGroup())));
    L32:
        r12 = r12 + 1;
        goto L23
    L31:
        r11[r12] = null;
        goto L32
    L27:
        r14 = null;
        goto L28
    L33:
        int r122 = r10;
        long r18 = this.periods[r10].selectTracks(r11, r22, r13, r24, r15);
        if (r122 != 0) goto L37;
        r15 = r18;
    L38:
        int r84 = 0;
        boolean r102 = false;
    L40:
        if (r84 >= r21.length) goto L52;
        if (r4[r84] != r122) goto L45;
        SampleStream r103 = (SampleStream) Assertions.checkNotNull(r13[r84]);
        r73[r84] = r13[r84];
        this.streamPeriodIndices.put(r103, Integer.valueOf(r122));
        r102 = true;
    L51:
        r84 = r84 + 1;
        goto L40
    L45:
        if (r3[r84] != r122) goto L51;
        if (r13[r84] != null) goto L49;
        boolean r143 = true;
    L50:
        Assertions.checkState(r143);
        goto L51
    L49:
        r143 = false;
        goto L50
    L52:
        if (r102 == false) goto L54;
        r9.add(this.periods[r122]);
    L54:
        r10 = r122 + 1;
        r5 = 0;
        goto L20
    L37:
        if (r18 == r15) goto L38;
        Segment$$ExternalSyntheticBUOutline1.m("Children enabled at different positions.");
        return 0;
    L57:
        int r1 = r5;
        System.arraycopy(r73, r1, r23, r1, r62);
        this.enabledPeriods = (MediaPeriod[]) r9.toArray(new MediaPeriod[r1]);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(r9, Lists.transform(r9, new MergingMediaPeriod$$ExternalSyntheticLambda0()));
        return r15;
    }
}
