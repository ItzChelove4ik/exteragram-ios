package androidx.media3.extractor.text;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import kotlin.jvm.internal.LongCompanionObject;
import okio.Buffer$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class SubtitleTranscodingTrackOutput implements TrackOutput {
    private final CueEncoder cueEncoder;
    private Format currentFormat;
    private SubtitleParser currentSubtitleParser;
    private final TrackOutput delegate;
    private final ParsableByteArray parsableScratch;
    private byte[] sampleData;
    private int sampleDataEnd;
    private int sampleDataStart;
    private boolean shouldSuppressParsingErrors;
    private final SubtitleParser.Factory subtitleParserFactory;

    /* renamed from: $r8$lambda$wg-p9htx6ejjhxbV2aeAKDyxIm0, reason: not valid java name */
    public static /* synthetic */ void m859$r8$lambda$wgp9htx6ejjhxbV2aeAKDyxIm0(SubtitleTranscodingTrackOutput r0, long r1, int r3, CuesWithTiming r4) {
        r0.outputSample(r4, r1, r3);
    }

    public SubtitleTranscodingTrackOutput(TrackOutput r1, SubtitleParser.Factory r2) {
        this.delegate = r1;
        this.subtitleParserFactory = r2;
        this.cueEncoder = new CueEncoder();
        this.sampleDataStart = 0;
        this.sampleDataEnd = 0;
        this.sampleData = Util.EMPTY_BYTE_ARRAY;
        this.parsableScratch = new ParsableByteArray();
    }

    private void ensureSampleDataCapacity(int r5) {
        int r0 = this.sampleData.length;
        int r1 = this.sampleDataEnd;
        if ((r0 - r1) < r5) goto L5;
        return;
    L5:
        int r12 = r1 - this.sampleDataStart;
        int r52 = Math.max(r12 * 2, r5 + r12);
        byte[] r02 = this.sampleData;
        if (r52 > r02.length) goto L8;
        byte[] r53 = r02;
    L9:
        System.arraycopy(r02, this.sampleDataStart, r53, 0, r12);
        this.sampleDataStart = 0;
        this.sampleDataEnd = r12;
        this.sampleData = r53;
        return;
    L8:
        r53 = new byte[r52];
        goto L9
    }

    private void outputSample(CuesWithTiming r13, long r14, int r16) {
        Assertions.checkStateNotNull(this.currentFormat);
        byte[] r0 = this.cueEncoder.encode(r13.cues, r13.durationUs);
        this.parsableScratch.reset(r0);
        this.delegate.sampleData(this.parsableScratch, r0.length);
        long r1 = r13.startTimeUs;
        Format r3 = this.currentFormat;
        if (r1 == (-9223372036854775807L)) goto L5;
        long r7 = r3.subsampleOffsetUs;
        if (r7 != LongCompanionObject.MAX_VALUE) goto L13;
        long r12 = r1 + r14;
    L12:
        long r6 = r12;
    L14:
        this.delegate.sampleMetadata(r6, r16 | 1, r0.length, 0, null);
        return;
    L13:
        r12 = r1 + r7;
        goto L12
    L5:
        if (r3.subsampleOffsetUs != LongCompanionObject.MAX_VALUE) goto L7;
        boolean r132 = true;
    L8:
        Assertions.checkState(r132);
        r6 = r14;
        goto L14
    L7:
        r132 = false;
        goto L8
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void format(Format r5) {
        Assertions.checkNotNull(r5.sampleMimeType);
        if (MimeTypes.getTrackType(r5.sampleMimeType) != 3) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r5.equals(this.currentFormat) == true) goto L13;
        this.currentFormat = r5;
        if (this.subtitleParserFactory.supportsFormat(r5) == false) goto L11;
        SubtitleParser r02 = this.subtitleParserFactory.create(r5);
    L12:
        this.currentSubtitleParser = r02;
        goto L13
    L11:
        r02 = null;
    L13:
        SubtitleParser r03 = this.currentSubtitleParser;
        TrackOutput r1 = this.delegate;
        if (r03 != null) goto L17;
        r1.format(r5);
        return;
    L17:
        r1.format(r5.buildUpon().setSampleMimeType("application/x-media3-cues").setCodecs(r5.sampleMimeType).setSubsampleOffsetUs(LongCompanionObject.MAX_VALUE).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(r5)).build());
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader r2, int r3, boolean r4, int r5) {
        if (this.currentSubtitleParser == null) goto L5;
        ensureSampleDataCapacity(r3);
        int r22 = r2.read(this.sampleData, this.sampleDataEnd, r3);
        if (r22 != (-1)) goto L12;
        if (r4 == false) goto L10;
        return -1;
    L10:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L12:
        this.sampleDataEnd += r22;
        return r22;
    L5:
        return this.delegate.sampleData(r2, r3, r4, r5);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleData(ParsableByteArray r2, int r3, int r4) {
        if (this.currentSubtitleParser != null) goto L6;
        this.delegate.sampleData(r2, r3, r4);
        return;
    L6:
        ensureSampleDataCapacity(r3);
        r2.readBytes(this.sampleData, this.sampleDataEnd, r3);
        this.sampleDataEnd += r3;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleMetadata(long r5, int r7, int r8, int r9, TrackOutput.CryptoData r10) {
        if (this.currentSubtitleParser != null) goto L7;
        this.delegate.sampleMetadata(r5, r7, r8, r9, r10);
        return;
    L7:
        if (r10 != null) goto L9;
        boolean r102 = true;
    L10:
        Assertions.checkArgument(r102, "DRM on subtitles is not supported");
        int r103 = (this.sampleDataEnd - r9) - r8;
        int r72 = r103;
        this.currentSubtitleParser.parse(this.sampleData, r72, r8, SubtitleParser.OutputOptions.allCues(), new SubtitleTranscodingTrackOutput$$ExternalSyntheticLambda0(this, r5, r7));     // Catch: RuntimeException -> L15
    L22:
        int r104 = r72 + r8;
        this.sampleDataStart = r104;
        if (r104 != this.sampleDataEnd) goto L31;
        this.sampleDataStart = 0;
        this.sampleDataEnd = 0;
        return;
    L31:
        return;
    L15:
        e = e;
    L16:
        RuntimeException r52 = e;
        if (this.shouldSuppressParsingErrors == false) goto L26;
        Log.w("SubtitleTranscodingTO", "Parsing subtitles failed, ignoring sample.", r52);
        goto L22
    L26:
        throw r52;
    L17:
        e = e;
        r72 = r103;
        goto L16
    L9:
        r102 = false;
        goto L10
    }

    public void shouldSuppressParsingErrors(boolean r1) {
        this.shouldSuppressParsingErrors = r1;
    }
}
