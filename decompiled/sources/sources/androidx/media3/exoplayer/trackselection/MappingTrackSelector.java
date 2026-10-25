package androidx.media3.exoplayer.trackselection;

import android.util.Pair;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;

    public static final class MappedTrackInfo {
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final String[] rendererNames;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        public MappedTrackInfo(String[] r1, int[] r2, TrackGroupArray[] r3, int[] r4, int[][][] r5, TrackGroupArray r6) {
            this.rendererNames = r1;
            this.rendererTrackTypes = r2;
            this.rendererTrackGroups = r3;
            this.rendererFormatSupports = r5;
            this.rendererMixedMimeTypeAdaptiveSupports = r4;
            this.unmappedTrackGroups = r6;
            this.rendererCount = r2.length;
        }

        public int getAdaptiveSupport(int r7, int r8, boolean r9) {
            int r0 = this.rendererTrackGroups[r7].get(r8).length;
            int[] r1 = new int[r0];
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r2 >= r0) goto L12;
            int r4 = getTrackSupport(r7, r8, r2);
            if (r4 == 4) goto L9;
            if (r9 == false) goto L10;
            if (r4 == 3) goto L9;
        L10:
            r2 = r2 + 1;
        L9:
            r1[r3] = r2;
            r3 = r3 + 1;
            goto L10
        L12:
            return getAdaptiveSupport(r7, r8, Arrays.copyOf(r1, r3));
        }

        public int getAdaptiveSupport(int r8, int r9, int[] r10) {
            int r0 = 0;
            int r3 = 16;
            String r4 = null;
            boolean r1 = false;
            int r2 = 0;
        L4:
            if (r0 >= r10.length) goto L10;
            String r5 = this.rendererTrackGroups[r8].get(r9).getFormat(r10[r0]).sampleMimeType;
            int r6 = r2 + 1;
            if (r2 != 0) goto L8;
            r4 = r5;
        L9:
            r3 = Math.min(r3, RendererCapabilities.getAdaptiveSupport(this.rendererFormatSupports[r8][r9][r0]));
            r0 = r0 + 1;
            r2 = r6;
            goto L4
        L8:
            r1 = r1 | (!Objects.equals(r4, r5));
            goto L9
        L10:
            if (r1 == true) goto L12;
            return r3;
        L12:
            return Math.min(r3, this.rendererMixedMimeTypeAdaptiveSupports[r8]);
        }

        public int getCapabilities(int r1, int r2, int r3) {
            return this.rendererFormatSupports[r1][r2][r3];
        }

        public int getRendererCount() {
            return this.rendererCount;
        }

        public int getRendererType(int r1) {
            return this.rendererTrackTypes[r1];
        }

        public TrackGroupArray getTrackGroups(int r1) {
            return this.rendererTrackGroups[r1];
        }

        public int getTrackSupport(int r1, int r2, int r3) {
            return RendererCapabilities.getFormatSupport(getCapabilities(r1, r2, r3));
        }

        public TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }
    }

    public MappingTrackSelector() {
    }

    private static int findRenderer(RendererCapabilities[] r10, TrackGroup r11, int[] r12, boolean r13) {
        int r0 = r10.length;
        int r3 = 0;
        int r4 = 0;
        boolean r5 = true;
    L4:
        if (r3 >= r10.length) goto L20;
        RendererCapabilities r6 = r10[r3];
        int r7 = 0;
        int r8 = 0;
    L7:
        if (r7 >= r11.length) goto L10;
        r8 = Math.max(r8, RendererCapabilities.getFormatSupport(r6.supportsFormat(r11.getFormat(r7))));
        r7 = r7 + 1;
        goto L7
    L10:
        if (r12[r3] != 0) goto L12;
        boolean r62 = true;
    L13:
        if (r8 > r4) goto L18;
        if (r8 != r4) goto L19;
        if (r13 == false) goto L19;
        if (r5 == true) goto L19;
        if (r62 == true) goto L18;
    L19:
        r3 = r3 + 1;
    L18:
        r0 = r3;
        r5 = r62;
        r4 = r8;
        goto L19
    L12:
        r62 = false;
        goto L13
    L20:
        return r0;
    }

    private static int[] getFormatSupport(RendererCapabilities r3, TrackGroup r4) {
        int[] r0 = new int[r4.length];
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L6;
        r0[r1] = r3.supportsFormat(r4.getFormat(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] r4) {
        int r0 = r4.length;
        int[] r1 = new int[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = r4[r2].supportsMixedMimeTypeAdaptation();
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public final void onSelectionActivated(Object r1) {
        this.currentMappedTrackInfo = (MappedTrackInfo) r1;
    }

    public abstract Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappedTrackInfo r1, int[][][] r2, int[] r3, MediaSource.MediaPeriodId r4, Timeline r5);

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public final TrackSelectorResult selectTracks(RendererCapabilities[] r14, TrackGroupArray r15, MediaSource.MediaPeriodId r16, Timeline r17) {
        int[] r1 = new int[r14.length + 1];
        int r3 = r14.length + 1;
        TrackGroup[][] r4 = new TrackGroup[r3][];
        int[][][] r11 = new int[r14.length + 1][][];
        int r5 = 0;
        int r6 = 0;
    L3:
        if (r6 >= r3) goto L5;
        int r7 = r15.length;
        r4[r6] = new TrackGroup[r7];
        r11[r6] = new int[r7][];
        r6 = r6 + 1;
        goto L3
    L5:
        int[] r9 = getMixedMimeTypeAdaptationSupports(r14);
        int r32 = 0;
    L7:
        if (r32 >= r15.length) goto L17;
        TrackGroup r62 = r15.get(r32);
        if (r62.type != 5) goto L11;
        boolean r72 = true;
    L12:
        int r73 = findRenderer(r14, r62, r1, r72);
        if (r73 != r14.length) goto L15;
        int[] r8 = new int[r62.length];
    L16:
        int r10 = r1[r73];
        r4[r73][r10] = r62;
        r11[r73][r10] = r8;
        r1[r73] = r10 + 1;
        r32 = r32 + 1;
        goto L7
    L15:
        r8 = getFormatSupport(r14[r73], r62);
        goto L16
    L11:
        r72 = false;
        goto L12
    L17:
        TrackGroupArray[] r0 = new TrackGroupArray[r14.length];
        String[] r74 = new String[r14.length];
        int[] r82 = new int[r14.length];
    L19:
        if (r5 >= r14.length) goto L21;
        int r2 = r1[r5];
        r0[r5] = new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(r4[r5], r2));
        r11[r5] = (int[][]) Util.nullSafeArrayCopy(r11[r5], r2);
        r74[r5] = r14[r5].getName();
        r82[r5] = r14[r5].getTrackType();
        r5 = r5 + 1;
        goto L19
    L21:
        MappedTrackInfo r63 = new MappedTrackInfo(r74, r82, r0, r9, r11, new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(r4[r14.length], r1[r14.length])));
        Pair<RendererConfiguration[], ExoTrackSelection[]> r13 = selectTracks(r63, r11, r9, r16, r17);
        return new TrackSelectorResult((RendererConfiguration[]) r13.first, (ExoTrackSelection[]) r13.second, TrackSelectionUtil.buildTracks(r63, (TrackSelection[]) r13.second), r63);
    }
}
