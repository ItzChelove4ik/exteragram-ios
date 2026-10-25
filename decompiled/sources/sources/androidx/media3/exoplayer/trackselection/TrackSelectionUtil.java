package androidx.media3.exoplayer.trackselection;

import android.graphics.Point;
import android.os.SystemClock;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class TrackSelectionUtil {
    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo r3, TrackSelection[] r4) {
        List[] r0 = new List[r4.length];
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L11;
        TrackSelection r2 = r4[r1];
        if (r2 == null) goto L8;
        ImmutableList r22 = ImmutableList.of(r2);
    L9:
        r0[r1] = r22;
        r1 = r1 + 1;
        goto L4
    L8:
        r22 = ImmutableList.of();
        goto L9
    L11:
        return buildTracks(r3, r0);
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo r16, List<? extends TrackSelection>[] r17) {
        ImmutableList.Builder r1 = new ImmutableList.Builder();
        int r3 = 0;
    L4:
        if (r3 >= r16.getRendererCount()) goto L28;
        TrackGroupArray r4 = r16.getTrackGroups(r3);
        List<? extends TrackSelection> r5 = r17[r3];
        int r6 = 0;
    L7:
        if (r6 >= r4.length) goto L27;
        TrackGroup r7 = r4.get(r6);
        if (r16.getAdaptiveSupport(r3, r6, false) == 0) goto L11;
        boolean r8 = true;
    L12:
        int r10 = r7.length;
        int[] r11 = new int[r10];
        boolean[] r102 = new boolean[r10];
        int r12 = 0;
    L14:
        if (r12 >= r7.length) goto L26;
        r11[r12] = r16.getTrackSupport(r3, r6, r12);
        int r13 = 0;
    L17:
        if (r13 >= r5.size()) goto L24;
        TrackSelection r14 = r5.get(r13);
        if (r14.getTrackGroup().equals(r7) == false) goto L23;
        if (r14.indexOf(r12) == (-1)) goto L23;
        boolean r132 = true;
    L25:
        r102[r12] = r132;
        r12 = r12 + 1;
    L23:
        r13 = r13 + 1;
        goto L17
    L24:
        r132 = false;
        goto L25
    L26:
        r1.add(new Tracks.Group(r7, r8, r11, r102));
        r6 = r6 + 1;
        goto L7
    L11:
        r8 = false;
        goto L12
    L27:
        r3 = r3 + 1;
        goto L4
    L28:
        TrackGroupArray r0 = r16.getUnmappedTrackGroups();
        int r32 = 0;
    L30:
        if (r32 >= r0.length) goto L33;
        TrackGroup r42 = r0.get(r32);
        int[] r52 = new int[r42.length];
        Arrays.fill(r52, 0);
        r1.add(new Tracks.Group(r42, false, r52, new boolean[r42.length]));
        r32 = r32 + 1;
        goto L30
    L33:
        return new Tracks(r1.build());
    }

    public static LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection r7) {
        long r0 = SystemClock.elapsedRealtime();
        int r2 = r7.length();
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r2) goto L9;
        if (r7.isTrackExcluded(r4, r0) == false) goto L7;
        r5 = r5 + 1;
    L7:
        r4 = r4 + 1;
        goto L3
    L9:
        return new LoadErrorHandlingPolicy.FallbackOptions(1, 0, r2, r5);
    }

    public static Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        if (r3 == false) goto L11;
        boolean r32 = false;
        if (r6 <= r7) goto L6;
        boolean r1 = true;
    L7:
        if (r4 <= r5) goto L9;
        r32 = true;
    L9:
        if (r1 == r32) goto L11;
    L12:
        int r33 = r6 * r4;
        int r0 = r7 * r5;
        if (r33 < r0) goto L17;
        return new Point(r5, Util.ceilDivide(r0, r6));
    L17:
        return new Point(Util.ceilDivide(r33, r7), r4);
    L6:
        r1 = false;
    L11:
        r5 = r4;
        r4 = r5;
        goto L12
    }
}
