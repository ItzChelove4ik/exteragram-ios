package androidx.media3.exoplayer.hls;

import android.text.TextUtils;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.text.webvtt.WebvttParserUtil;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class WebvttExtractor implements Extractor {
    private static final Pattern LOCAL_TIMESTAMP = null;
    private static final Pattern MEDIA_TIMESTAMP = null;
    private final String language;
    private ExtractorOutput output;
    private final boolean parseSubtitlesDuringExtraction;
    private byte[] sampleData;
    private final ParsableByteArray sampleDataWrapper;
    private int sampleSize;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final TimestampAdjuster timestampAdjuster;

    static {
        LOCAL_TIMESTAMP = Pattern.compile("LOCAL:([^,]+)");
        MEDIA_TIMESTAMP = Pattern.compile("MPEGTS:(-?\\d+)");
    }

    public WebvttExtractor(String r1, TimestampAdjuster r2, SubtitleParser.Factory r3, boolean r4) {
        this.language = r1;
        this.timestampAdjuster = r2;
        this.sampleDataWrapper = new ParsableByteArray();
        this.sampleData = new byte[1024];
        this.subtitleParserFactory = r3;
        this.parseSubtitlesDuringExtraction = r4;
    }

    @RequiresNonNull({"output"})
    private TrackOutput buildTrackOutput(long r4) {
        TrackOutput r0 = this.output.track(0, 3);
        r0.format(new Format.Builder().setSampleMimeType("text/vtt").setLanguage(this.language).setSubsampleOffsetUs(r4).build());
        this.output.endTracks();
        return r0;
    }

    @RequiresNonNull({"output"})
    private void processSample() {
        ParsableByteArray r0 = new ParsableByteArray(this.sampleData);
        WebvttParserUtil.validateWebvttHeaderLine(r0);
        String r1 = r0.readLine();
        long r4 = 0;
        long r6 = 0;
    L4:
        if (TextUtils.isEmpty(r1) == true) goto L17;
        if (r1.startsWith("X-TIMESTAMP-MAP") == false) goto L16;
        Matcher r42 = LOCAL_TIMESTAMP.matcher(r1);
        if (r42.find() == false) goto L15;
        Matcher r5 = MEDIA_TIMESTAMP.matcher(r1);
        if (r5.find() == false) goto L13;
        r6 = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(r42.group(1)));
        r4 = TimestampAdjuster.ptsToUs(Long.parseLong((String) Assertions.checkNotNull(r5.group(1))));
        goto L16
    L13:
        throw ParserException.createForMalformedContainer("X-TIMESTAMP-MAP doesn't contain media timestamp: ".concat(r1), null);
    L15:
        throw ParserException.createForMalformedContainer("X-TIMESTAMP-MAP doesn't contain local timestamp: ".concat(r1), null);
    L16:
        r1 = r0.readLine();
        goto L4
    L17:
        Matcher r02 = WebvttParserUtil.findNextCueHeader(r0);
        if (r02 != null) goto L21;
        buildTrackOutput(0);
        return;
    L21:
        long r03 = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(r02.group(1)));
        long r62 = this.timestampAdjuster.adjustTsTimestamp(TimestampAdjuster.usToWrappedPts((r4 + r03) - r6));
        TrackOutput r52 = buildTrackOutput(r62 - r03);
        this.sampleDataWrapper.reset(this.sampleData, this.sampleSize);
        r52.sampleData(this.sampleDataWrapper, this.sampleSize);
        r52.sampleMetadata(r62, 1, this.sampleSize, 0, null);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r3) {
        if (this.parseSubtitlesDuringExtraction == false) goto L5;
        r3 = new SubtitleTranscodingExtractorOutput(r3, this.subtitleParserFactory);
    L5:
        this.output = r3;
        r3.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r5, PositionHolder r6) {
        Assertions.checkNotNull(this.output);
        int r62 = (int) r5.getLength();
        int r0 = this.sampleSize;
        byte[] r1 = this.sampleData;
        if (r0 != r1.length) goto L8;
        if (r62 == (-1)) goto L6;
        int r02 = r62;
    L7:
        this.sampleData = Arrays.copyOf(r1, (r02 * 3) / 2);
        goto L8
    L6:
        r02 = r1.length;
    L8:
        byte[] r03 = this.sampleData;
        int r12 = this.sampleSize;
        int r52 = r5.read(r03, r12, r03.length - r12);
        if (r52 == (-1)) goto L15;
        int r04 = this.sampleSize + r52;
        this.sampleSize = r04;
        if (r62 == (-1)) goto L13;
        if (r04 == r62) goto L15;
        return 0;
    L13:
        return 0;
    L15:
        processSample();
        return -1;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        throw new IllegalStateException();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r5) {
        r5.peekFully(this.sampleData, 0, 6, false);
        this.sampleDataWrapper.reset(this.sampleData, 6);
        if (WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper) == false) goto L6;
        return true;
    L6:
        r5.peekFully(this.sampleData, 6, 3, false);
        this.sampleDataWrapper.reset(this.sampleData, 9);
        return WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper);
    }
}
