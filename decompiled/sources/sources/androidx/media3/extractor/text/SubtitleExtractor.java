package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class SubtitleExtractor implements Extractor {
    private int bytesRead;
    private final CueEncoder cueEncoder;
    private final Format format;
    private final List<Sample> samples;
    private final ParsableByteArray scratchSampleArray;
    private long seekTimeUs;
    private int state;
    private byte[] subtitleData;
    private final SubtitleParser subtitleParser;
    private long[] timestamps;
    private TrackOutput trackOutput;

    /* renamed from: androidx.media3.extractor.text.SubtitleExtractor$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Sample implements Comparable<Sample> {
        private final byte[] data;
        private final long timeUs;

        private Sample(long r1, byte[] r3) {
            this.timeUs = r1;
            this.data = r3;
        }

        public /* synthetic */ Sample(long r1, byte[] r3, AnonymousClass1 r4) {
            this(r1, r3);
        }

        public static /* synthetic */ long access$000(Sample r2) {
            return r2.timeUs;
        }

        public static /* synthetic */ byte[] access$100(Sample r0) {
            return r0.data;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Sample r3) {
            return Long.compare(this.timeUs, r3.timeUs);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Sample r1) {
            return compareTo2(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$pFuqHDLwE3sysQy_6HAWupcu5YY(SubtitleExtractor r7, CuesWithTiming r8) {
        r7.getClass();
        Sample r0 = new Sample(r8.startTimeUs, r7.cueEncoder.encode(r8.cues, r8.durationUs), null);
        r7.samples.add(r0);
        long r1 = r7.seekTimeUs;
        if (r1 != (-9223372036854775807L)) goto L5;
    L8:
        r7.writeToOutput(r0);
        return;
    L5:
        if (r8.endTimeUs >= r1) goto L8;
    }

    public SubtitleExtractor(SubtitleParser r3, Format r4) {
        this.subtitleParser = r3;
        this.cueEncoder = new CueEncoder();
        this.subtitleData = Util.EMPTY_BYTE_ARRAY;
        this.scratchSampleArray = new ParsableByteArray();
        if (r4 == null) goto L5;
        Format r32 = r4.buildUpon().setSampleMimeType("application/x-media3-cues").setCodecs(r4.sampleMimeType).setCueReplacementBehavior(r3.getCueReplacementBehavior()).build();
    L6:
        this.format = r32;
        this.samples = new ArrayList();
        this.state = 0;
        this.timestamps = Util.EMPTY_LONG_ARRAY;
        this.seekTimeUs = -9223372036854775807L;
        return;
    L5:
        r32 = null;
        goto L6
    }

    private void parseAndWriteToOutput() {
        long r0 = this.seekTimeUs;     // Catch: RuntimeException -> L13
        if (r0 == (-9223372036854775807L)) goto L6;
        SubtitleParser.OutputOptions r02 = SubtitleParser.OutputOptions.cuesAfterThenRemainingCuesBefore(r0);     // Catch: RuntimeException -> L13
    L5:
        SubtitleParser.OutputOptions r5 = r02;
        this.subtitleParser.parse(this.subtitleData, 0, this.bytesRead, r5, new SubtitleExtractor$$ExternalSyntheticLambda0(this));     // Catch: RuntimeException -> L13
        Collections.sort(this.samples);     // Catch: RuntimeException -> L13
        this.timestamps = new long[this.samples.size()];     // Catch: RuntimeException -> L13
        int r03 = 0;
    L9:
        if (r03 >= this.samples.size()) goto L11;
        this.timestamps[r03] = Sample.access$000(this.samples.get(r03));     // Catch: RuntimeException -> L13
        r03 = r03 + 1;     // Catch: RuntimeException -> L13
        goto L9
    L11:
        this.subtitleData = Util.EMPTY_BYTE_ARRAY;     // Catch: RuntimeException -> L13
        return;
    L6:
        r02 = SubtitleParser.OutputOptions.allCues();     // Catch: RuntimeException -> L13
    L13:
        e = move-exception;
        throw ParserException.createForMalformedContainer("SubtitleParser failed.", e);
    }

    private boolean readFromInput(ExtractorInput r7) {
        byte[] r0 = this.subtitleData;
        if (r0.length != this.bytesRead) goto L5;
        this.subtitleData = Arrays.copyOf(r0, r0.length + 1024);
    L5:
        byte[] r02 = this.subtitleData;
        int r1 = this.bytesRead;
        int r03 = r7.read(r02, r1, r02.length - r1);
        if (r03 == (-1)) goto L8;
        this.bytesRead += r03;
    L8:
        long r2 = r7.getLength();
        if (r2 != (-1)) goto L11;
    L12:
        if (r03 == (-1)) goto L17;
        return false;
    L17:
        return true;
    L11:
        if (this.bytesRead != r2) goto L12;
        return true;
    }

    private boolean skipInput(ExtractorInput r5) {
        if (r5.getLength() == (-1)) goto L5;
        int r4 = Ints.checkedCast(r5.getLength());
    L7:
        if (r5.skip(r4) != (-1)) goto L10;
        return true;
    L10:
        return false;
    L5:
        r4 = 1024;
        goto L7
    }

    private void writeToOutput() {
        long r0 = this.seekTimeUs;
        if (r0 != (-9223372036854775807L)) goto L5;
        int r02 = 0;
    L7:
        if (r02 >= this.samples.size()) goto L9;
        writeToOutput(this.samples.get(r02));
        r02 = r02 + 1;
        goto L7
    L9:
        return;
    L5:
        r02 = Util.binarySearchFloor(this.timestamps, r0, true, true);
        goto L7
    }

    private void writeToOutput(Sample r9) {
        Assertions.checkStateNotNull(this.trackOutput);
        int r5 = Sample.access$100(r9).length;
        this.scratchSampleArray.reset(Sample.access$100(r9));
        this.trackOutput.sampleData(this.scratchSampleArray, r5);
        this.trackOutput.sampleMetadata(Sample.access$000(r9), 1, r5, 0, null);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r8) {
        if (this.state != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        TrackOutput r02 = r8.track(0, 3);
        this.trackOutput = r02;
        Format r3 = this.format;
        if (r3 == null) goto L9;
        r02.format(r3);
        r8.endTracks();
        r8.seekMap(new IndexSeekMap(new long[]{0}, new long[]{0}, -9223372036854775807L));
    L9:
        this.state = 1;
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r8, PositionHolder r9) {
        int r92 = this.state;
        if (r92 != 0) goto L5;
    L7:
        boolean r93 = false;
    L8:
        Assertions.checkState(r93);
        if (this.state != 1) goto L19;
        if (r8.getLength() == (-1)) goto L13;
        int r94 = Ints.checkedCast(r8.getLength());
    L15:
        if (r94 <= this.subtitleData.length) goto L17;
        this.subtitleData = new byte[r94];
    L17:
        this.bytesRead = 0;
        this.state = 2;
        goto L19
    L13:
        r94 = 1024;
    L19:
        if (this.state != 2) goto L24;
        if (readFromInput(r8) == false) goto L24;
        parseAndWriteToOutput();
        this.state = 4;
    L24:
        if (this.state != 3) goto L29;
        if (skipInput(r8) == false) goto L29;
        writeToOutput();
        this.state = 4;
    L29:
        if (this.state != 4) goto L32;
        return -1;
    L32:
        return 0;
    L5:
        if (r92 == 5) goto L7;
        r93 = true;
        goto L8
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        if (this.state != 5) goto L5;
        return;
    L5:
        this.subtitleParser.reset();
        this.state = 5;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r2, long r4) {
        int r22 = this.state;
        if (r22 != 0) goto L5;
    L7:
        boolean r23 = false;
    L8:
        Assertions.checkState(r23);
        this.seekTimeUs = r4;
        if (this.state != 2) goto L12;
        this.state = 1;
    L12:
        if (this.state != 4) goto L15;
        this.state = 3;
        return;
    L15:
        return;
    L5:
        if (r22 == 5) goto L7;
        r23 = true;
        goto L8
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r1) {
        return true;
    }
}
