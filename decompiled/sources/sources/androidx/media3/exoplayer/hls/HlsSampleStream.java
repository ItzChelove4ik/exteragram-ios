package androidx.media3.exoplayer.hls;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.source.SampleStream;

/* loaded from: classes4.dex */
final class HlsSampleStream implements SampleStream {
    private int sampleQueueIndex;
    private final HlsSampleStreamWrapper sampleStreamWrapper;
    private final int trackGroupIndex;

    public HlsSampleStream(HlsSampleStreamWrapper r1, int r2) {
        this.sampleStreamWrapper = r1;
        this.trackGroupIndex = r2;
        this.sampleQueueIndex = -1;
    }

    private boolean hasValidSampleQueueIndex() {
        int r1 = this.sampleQueueIndex;
        if (r1 != (-1)) goto L5;
        return false;
    L5:
        if (r1 != (-3)) goto L7;
        return false;
    L7:
        if (r1 == (-2)) goto L13;
        return true;
    L13:
        return false;
    }

    public void bindSampleQueue() {
        if (this.sampleQueueIndex != (-1)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        this.sampleQueueIndex = this.sampleStreamWrapper.bindSampleQueueToSampleStream(this.trackGroupIndex);
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public boolean isReady() {
        if (this.sampleQueueIndex != (-3)) goto L5;
        return true;
    L5:
        if (hasValidSampleQueueIndex() == true) goto L7;
        return false;
    L7:
        if (this.sampleStreamWrapper.isReady(this.sampleQueueIndex) == true) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public void maybeThrowError() {
        int r0 = this.sampleQueueIndex;
        if (r0 == (-2)) goto L13;
        if (r0 != (-1)) goto L9;
        this.sampleStreamWrapper.maybeThrowError();
        return;
    L9:
        if (r0 == (-3)) goto L14;
        this.sampleStreamWrapper.maybeThrowError(r0);
        return;
    L14:
        return;
    L13:
        throw new SampleQueueMappingException(this.sampleStreamWrapper.getTrackGroups().get(this.trackGroupIndex).getFormat(0).sampleMimeType);
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int readData(FormatHolder r3, DecoderInputBuffer r4, int r5) {
        if (this.sampleQueueIndex != (-3)) goto L7;
        r4.addFlag(4);
        return -4;
    L7:
        if (hasValidSampleQueueIndex() == true) goto L9;
        return -3;
    L9:
        return this.sampleStreamWrapper.readData(this.sampleQueueIndex, r3, r4, r5);
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int skipData(long r2) {
        if (hasValidSampleQueueIndex() == true) goto L5;
        return 0;
    L5:
        return this.sampleStreamWrapper.skipData(this.sampleQueueIndex, r2);
    }

    public void unbindSampleQueue() {
        if (this.sampleQueueIndex == (-1)) goto L6;
        this.sampleStreamWrapper.unbindSampleQueue(this.trackGroupIndex);
        this.sampleQueueIndex = -1;
        return;
    }
}
