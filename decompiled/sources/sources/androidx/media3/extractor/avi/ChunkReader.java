package androidx.media3.extractor.avi;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class ChunkReader {
    private final int alternativeChunkId;
    private int bytesRemainingInCurrentChunk;
    private int chunkCount;
    private final int chunkId;
    private int currentChunkIndex;
    private int currentChunkSize;
    private final long durationUs;
    private long firstIndexChunkOffset;
    private int indexChunkCount;
    private int indexSize;
    private int[] keyFrameIndices;
    private long[] keyFrameOffsets;
    private final AviStreamHeaderChunk streamHeaderChunk;
    private final TrackOutput trackOutput;

    public ChunkReader(int r5, AviStreamHeaderChunk r6, TrackOutput r7) {
        this.streamHeaderChunk = r6;
        int r0 = r6.getTrackType();
        boolean r2 = true;
        if (r0 == 1) goto L7;
        if (r0 == 2) goto L7;
        r2 = false;
    L7:
        Assertions.checkArgument(r2);
        if (r0 != 2) goto L10;
        int r22 = 1667497984;
    L11:
        this.chunkId = getChunkIdFourCc(r5, r22);
        this.durationUs = r6.getDurationUs();
        this.trackOutput = r7;
        if (r0 != 2) goto L14;
        int r52 = getChunkIdFourCc(r5, 1650720768);
    L15:
        this.alternativeChunkId = r52;
        this.firstIndexChunkOffset = -1;
        this.keyFrameOffsets = new long[512];
        this.keyFrameIndices = new int[512];
        this.chunkCount = r6.length;
        return;
    L14:
        r52 = -1;
        goto L15
    L10:
        r22 = 1651965952;
        goto L11
    }

    private static int getChunkIdFourCc(int r1, int r2) {
        int r0 = r1 / 10;
        return ((((r1 % 10) + 48) << 8) | (r0 + 48)) | r2;
    }

    private long getChunkTimestampUs(int r5) {
        return (this.durationUs * r5) / this.chunkCount;
    }

    private SeekPoint getSeekPoint(int r6) {
        return new SeekPoint(this.keyFrameIndices[r6] * getFrameDurationUs(), this.keyFrameOffsets[r6]);
    }

    public void advanceCurrentChunk() {
        this.currentChunkIndex++;
    }

    public void appendIndexChunk(long r5, boolean r7) {
        if (this.firstIndexChunkOffset != (-1)) goto L5;
        this.firstIndexChunkOffset = r5;
    L5:
        if (r7 == true) goto L7;
    L10:
        this.indexChunkCount++;
        return;
    L7:
        if (this.indexSize != this.keyFrameIndices.length) goto L9;
        long[] r72 = this.keyFrameOffsets;
        this.keyFrameOffsets = Arrays.copyOf(r72, (r72.length * 3) / 2);
        int[] r73 = this.keyFrameIndices;
        this.keyFrameIndices = Arrays.copyOf(r73, (r73.length * 3) / 2);
    L9:
        long[] r74 = this.keyFrameOffsets;
        int r0 = this.indexSize;
        r74[r0] = r5;
        this.keyFrameIndices[r0] = this.indexChunkCount;
        this.indexSize = r0 + 1;
        goto L10
    }

    public void commitIndex() {
        this.keyFrameOffsets = Arrays.copyOf(this.keyFrameOffsets, this.indexSize);
        this.keyFrameIndices = Arrays.copyOf(this.keyFrameIndices, this.indexSize);
        if (isAudio() == true) goto L5;
        return;
    L5:
        if (this.streamHeaderChunk.sampleSize == 0) goto L11;
        int r0 = this.indexSize;
        if (r0 <= 0) goto L12;
        this.chunkCount = r0;
        return;
    L12:
        return;
    }

    public long getCurrentChunkTimestampUs() {
        return getChunkTimestampUs(this.currentChunkIndex);
    }

    public long getFrameDurationUs() {
        return getChunkTimestampUs(1);
    }

    public SeekMap.SeekPoints getSeekPoints(long r5) {
        if (this.indexSize == 0) goto L5;
        int r52 = (int) (r5 / getFrameDurationUs());
        int r6 = Util.binarySearchFloor(this.keyFrameIndices, r52, true, true);
        if (this.keyFrameIndices[r6] == r52) goto L9;
        SeekPoint r53 = getSeekPoint(r6);
        int r62 = r6 + 1;
        if (r62 >= this.keyFrameOffsets.length) goto L15;
        return new SeekMap.SeekPoints(r53, getSeekPoint(r62));
    L15:
        return new SeekMap.SeekPoints(r53);
    L9:
        return new SeekMap.SeekPoints(getSeekPoint(r6));
    L5:
        return new SeekMap.SeekPoints(new SeekPoint(0, this.firstIndexChunkOffset));
    }

    public boolean handlesChunkId(int r2) {
        if (this.chunkId != r2) goto L5;
        return true;
    L5:
        if (this.alternativeChunkId == r2) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isAudio() {
        if ((this.chunkId & 1651965952) != 1651965952) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isCurrentFrameAKeyFrame() {
        if (Arrays.binarySearch(this.keyFrameIndices, this.currentChunkIndex) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean onChunkData(ExtractorInput r11) {
        int r0 = this.bytesRemainingInCurrentChunk;
        boolean r2 = false;
        int r02 = r0 - this.trackOutput.sampleData(r11, r0, false);
        this.bytesRemainingInCurrentChunk = r02;
        if (r02 != 0) goto L5;
        r2 = true;
    L5:
        if (r2 == true) goto L7;
    L10:
        return r2;
    L7:
        if (this.currentChunkSize <= 0) goto L9;
        TrackOutput r3 = this.trackOutput;
        long r4 = getCurrentChunkTimestampUs();
        boolean r6 = isCurrentFrameAKeyFrame();
        int r7 = this.currentChunkSize;
        r3.sampleMetadata(r4, r6 ? 1 : 0, r7, 0, null);
    L9:
        advanceCurrentChunk();
        goto L10
    }

    public void onChunkStart(int r1) {
        this.currentChunkSize = r1;
        this.bytesRemainingInCurrentChunk = r1;
    }

    public void seekToPosition(long r3) {
        if (this.indexSize != 0) goto L6;
        this.currentChunkIndex = 0;
        return;
    L6:
        int r32 = Util.binarySearchFloor(this.keyFrameOffsets, r3, true, true);
        this.currentChunkIndex = this.keyFrameIndices[r32];
    }
}
