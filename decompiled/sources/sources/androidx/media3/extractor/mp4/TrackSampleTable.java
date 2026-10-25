package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
public final class TrackSampleTable {
    public final long durationUs;
    public final int[] flags;
    public final int maximumSize;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final long[] timestampsUs;
    public final Track track;

    public TrackSampleTable(Track r5, long[] r6, int[] r7, int r8, long[] r9, int[] r10, long r11) {
        boolean r2 = false;
        if (r7.length != r9.length) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r6.length != r9.length) goto L9;
        boolean r02 = true;
    L10:
        Assertions.checkArgument(r02);
        if (r10.length != r9.length) goto L13;
        r2 = true;
    L13:
        Assertions.checkArgument(r2);
        this.track = r5;
        this.offsets = r6;
        this.sizes = r7;
        this.maximumSize = r8;
        this.timestampsUs = r9;
        this.flags = r10;
        this.durationUs = r11;
        this.sampleCount = r6.length;
        if (r10.length <= 0) goto L17;
        int r4 = r10.length - 1;
        r10[r4] = r10[r4] | 536870912;
        return;
    L17:
        return;
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    public int getIndexOfEarlierOrEqualSynchronizationSample(long r4) {
        int r42 = Util.binarySearchFloor(this.timestampsUs, r4, true, false);
    L3:
        if (r42 < 0) goto L8;
        if ((this.flags[r42] & 1) != 0) goto L6;
        r42 = r42 - 1;
        goto L3
    L6:
        return r42;
    L8:
        return -1;
    }

    public int getIndexOfLaterOrEqualSynchronizationSample(long r4) {
        int r42 = Util.binarySearchCeil(this.timestampsUs, r4, true, false);
    L4:
        if (r42 >= this.timestampsUs.length) goto L9;
        if ((this.flags[r42] & 1) != 0) goto L7;
        r42 = r42 + 1;
        goto L4
    L7:
        return r42;
    L9:
        return -1;
    }
}
