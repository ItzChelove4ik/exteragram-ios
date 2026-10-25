package androidx.media3.extractor.mp4;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;

/* loaded from: classes4.dex */
final class TrackFragment {
    public long atomPosition;
    public long auxiliaryDataPosition;
    public long dataPosition;
    public boolean definesEncryptionData;
    public DefaultSampleValues header;
    public long nextFragmentDecodeTime;
    public boolean nextFragmentDecodeTimeIncludesMoov;
    public int sampleCount;
    public final ParsableByteArray sampleEncryptionData;
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable;
    public boolean[] sampleIsSyncFrameTable;
    public long[] samplePresentationTimesUs;
    public int[] sampleSizeTable;
    public TrackEncryptionBox trackEncryptionBox;
    public int trunCount;
    public long[] trunDataPosition;
    public int[] trunLength;

    public TrackFragment() {
        this.trunDataPosition = new long[0];
        this.trunLength = new int[0];
        this.sampleSizeTable = new int[0];
        this.samplePresentationTimesUs = new long[0];
        this.sampleIsSyncFrameTable = new boolean[0];
        this.sampleHasSubsampleEncryptionTable = new boolean[0];
        this.sampleEncryptionData = new ParsableByteArray();
    }

    public void fillEncryptionData(ParsableByteArray r4) {
        r4.readBytes(this.sampleEncryptionData.getData(), 0, this.sampleEncryptionData.limit());
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public void fillEncryptionData(ExtractorInput r4) {
        r4.readFully(this.sampleEncryptionData.getData(), 0, this.sampleEncryptionData.limit());
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public long getSamplePresentationTimeUs(int r3) {
        return this.samplePresentationTimesUs[r3];
    }

    public void initEncryptionData(int r2) {
        this.sampleEncryptionData.reset(r2);
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }

    public void initTables(int r2, int r3) {
        this.trunCount = r2;
        this.sampleCount = r3;
        if (this.trunLength.length >= r2) goto L6;
        this.trunDataPosition = new long[r2];
        this.trunLength = new int[r2];
    L6:
        if (this.sampleSizeTable.length >= r3) goto L9;
        int r32 = (r3 * 125) / 100;
        this.sampleSizeTable = new int[r32];
        this.samplePresentationTimesUs = new long[r32];
        this.sampleIsSyncFrameTable = new boolean[r32];
        this.sampleHasSubsampleEncryptionTable = new boolean[r32];
        return;
    }

    public void reset() {
        this.trunCount = 0;
        this.nextFragmentDecodeTime = 0;
        this.nextFragmentDecodeTimeIncludesMoov = false;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
        this.trackEncryptionBox = null;
    }

    public boolean sampleHasSubsampleEncryptionTable(int r2) {
        if (this.definesEncryptionData == true) goto L5;
        return false;
    L5:
        if (this.sampleHasSubsampleEncryptionTable[r2] == false) goto L10;
        return true;
    L10:
        return false;
    }
}
