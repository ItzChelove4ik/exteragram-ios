package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes4.dex */
public final class TrueHdSampleRechunker {
    private int chunkFlags;
    private int chunkOffset;
    private int chunkSampleCount;
    private int chunkSize;
    private long chunkTimeUs;
    private boolean foundSyncframe;
    private final byte[] syncframePrefix;

    public TrueHdSampleRechunker() {
        this.syncframePrefix = new byte[10];
    }

    public void outputPendingSampleMetadata(TrackOutput r9, TrackOutput.CryptoData r10) {
        if (this.chunkSampleCount <= 0) goto L6;
        r9.sampleMetadata(this.chunkTimeUs, this.chunkFlags, this.chunkSize, this.chunkOffset, r10);
        this.chunkSampleCount = 0;
        return;
    }

    public void reset() {
        this.foundSyncframe = false;
        this.chunkSampleCount = 0;
    }

    public void sampleMetadata(TrackOutput r4, long r5, int r7, int r8, int r9, TrackOutput.CryptoData r10) {
        if (this.chunkOffset > (r8 + r9)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0, "TrueHD chunk samples must be contiguous in the sample queue.");
        if (this.foundSyncframe == false) goto L17;
        int r02 = this.chunkSampleCount;
        int r1 = r02 + 1;
        this.chunkSampleCount = r1;
        if (r02 != 0) goto L12;
        this.chunkTimeUs = r5;
        this.chunkFlags = r7;
        this.chunkSize = 0;
    L12:
        this.chunkSize += r8;
        this.chunkOffset = r9;
        if (r1 < 16) goto L16;
        outputPendingSampleMetadata(r4, r10);
        return;
    L16:
        return;
    L17:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void startSample(ExtractorInput r4) {
        if (this.foundSyncframe == true) goto L10;
        r4.peekFully(this.syncframePrefix, 0, 10);
        r4.resetPeekPosition();
        if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) goto L8;
        return;
    L8:
        this.foundSyncframe = true;
        return;
    }
}
