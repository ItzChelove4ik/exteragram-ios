package androidx.media3.exoplayer.hls;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.FileTypes;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public final class DefaultHlsExtractorFactory implements HlsExtractorFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = null;
    private int codecsToParseWithinGopSampleDependencies;
    private final boolean exposeCea608WhenMissingDeclarations;
    private boolean parseSubtitlesDuringExtraction;
    private final int payloadReaderFactoryFlags;
    private SubtitleParser.Factory subtitleParserFactory;

    static {
        DEFAULT_EXTRACTOR_ORDER = new int[]{8, 13, 11, 2, 0, 1, 7};
    }

    public DefaultHlsExtractorFactory() {
        this(0, true);
    }

    public DefaultHlsExtractorFactory(int r1, boolean r2) {
        this.payloadReaderFactoryFlags = r1;
        this.exposeCea608WhenMissingDeclarations = r2;
        this.subtitleParserFactory = new DefaultSubtitleParserFactory();
    }

    private static void addFileTypeIfValidAndNotPresent(int r2, List<Integer> r3) {
        if (Ints.indexOf(DEFAULT_EXTRACTOR_ORDER, r2) != (-1)) goto L5;
        return;
    L5:
        if (r3.contains(Integer.valueOf(r2)) == true) goto L10;
        r3.add(Integer.valueOf(r2));
        return;
    }

    @SuppressLint({"SwitchIntDef"})
    private Extractor createExtractorByFileType(int r9, Format r10, List<Format> r11, TimestampAdjuster r12) {
        if (r9 == 0) goto L30;
        if (r9 == 1) goto L28;
        if (r9 == 2) goto L26;
        if (r9 == 7) goto L24;
        if (r9 == 8) goto L22;
        if (r9 == 11) goto L20;
        if (r9 == 13) goto L18;
        return null;
    L18:
        return new WebvttExtractor(r10.language, r12, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    L20:
        return createTsExtractor(this.payloadReaderFactoryFlags, this.exposeCea608WhenMissingDeclarations, r10, r11, r12, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    L22:
        return createFragmentedMp4Extractor(this.subtitleParserFactory, this.parseSubtitlesDuringExtraction, r12, r10, r11, this.codecsToParseWithinGopSampleDependencies);
    L24:
        return new Mp3Extractor(0, 0);
    L26:
        return new AdtsExtractor();
    L28:
        return new Ac4Extractor();
    L30:
        return new Ac3Extractor();
    }

    private static FragmentedMp4Extractor createFragmentedMp4Extractor(SubtitleParser.Factory r7, boolean r8, TimestampAdjuster r9, Format r10, List<Format> r11, int r12) {
        if (isFmp4Variant(r10) == false) goto L5;
        int r102 = 4;
    L6:
        if (r8 == true) goto L8;
        r7 = SubtitleParser.Factory.UNSUPPORTED;
        r102 = r102 | 32;
    L8:
        SubtitleParser.Factory r1 = r7;
        int r2 = r102 | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(r12);
        if (r11 != null) goto L13;
        r11 = ImmutableList.of();
    L13:
        return new FragmentedMp4Extractor(r1, r2, r9, null, r11, null);
    L5:
        r102 = 0;
        goto L6
    }

    private static TsExtractor createTsExtractor(int r8, boolean r9, Format r10, List<Format> r11, TimestampAdjuster r12, SubtitleParser.Factory r13, boolean r14) {
        int r0 = r8 | 16;
        if (r11 == null) goto L5;
        r0 = r8 | 48;
    L8:
        String r82 = r10.codecs;
        if (TextUtils.isEmpty(r82) == false) goto L11;
    L16:
        if (r14 == true) goto L19;
        r13 = SubtitleParser.Factory.UNSUPPORTED;
        int r83 = 1;
    L18:
        int r3 = r83;
        SubtitleParser.Factory r4 = r13;
        return new TsExtractor(2, r3, r4, r12, new DefaultTsPayloadReaderFactory(r0, r11), 112800);
    L19:
        r83 = 0;
        goto L18
    L11:
        if (MimeTypes.containsCodecsCorrespondingToMimeType(r82, MediaController.AUDIO_MIME_TYPE) == true) goto L14;
        r0 = r0 | 2;
    L14:
        if (MimeTypes.containsCodecsCorrespondingToMimeType(r82, MediaController.VIDEO_MIME_TYPE) == true) goto L16;
        r0 = r0 | 4;
        goto L16
    L5:
        if (r9 == false) goto L7;
        r11 = Collections.singletonList(new Format.Builder().setSampleMimeType("application/cea-608").build());
        goto L8
    L7:
        r11 = Collections.EMPTY_LIST;
        goto L8
    }

    private static boolean isFmp4Variant(Format r4) {
        Metadata r42 = r4.metadata;
        if (r42 != null) goto L5;
        return false;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r42.length()) goto L13;
        if ((r42.get(r1) instanceof HlsTrackMetadataEntry) == true) goto L11;
        r1 = r1 + 1;
        goto L7
    L11:
        return !((HlsTrackMetadataEntry) r2).variantInfos.isEmpty();
    L13:
        return false;
    }

    private static boolean sniffQuietly(Extractor r0, ExtractorInput r1) {
        boolean r02 = r0.sniff(r1);     // Catch: Throwable -> L5 EOFException -> L8
        r1.resetPeekPosition();
        return r02;
    L8:
        r1.resetPeekPosition();
        return false;
    L5:
        th = move-exception;
        r1.resetPeekPosition();
        throw th;
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public BundledHlsMediaChunkExtractor createExtractor(Uri r13, Format r14, List<Format> r15, TimestampAdjuster r16, Map<String, List<String>> r17, ExtractorInput r18, PlayerId r19) {
        int r0 = FileTypes.inferFileTypeFromMimeType(r14.sampleMimeType);
        int r1 = FileTypes.inferFileTypeFromResponseHeaders(r17);
        int r132 = FileTypes.inferFileTypeFromUri(r13);
        int[] r4 = DEFAULT_EXTRACTOR_ORDER;
        ArrayList r3 = new ArrayList(r4.length);
        addFileTypeIfValidAndNotPresent(r0, r3);
        addFileTypeIfValidAndNotPresent(r1, r3);
        addFileTypeIfValidAndNotPresent(r132, r3);
        int r5 = r4.length;
        int r6 = 0;
        int r7 = 0;
    L3:
        if (r7 >= r5) goto L5;
        addFileTypeIfValidAndNotPresent(r4[r7], r3);
        r7 = r7 + 1;
        goto L3
    L5:
        r18.resetPeekPosition();
        Extractor r42 = null;
    L7:
        if (r6 >= r3.size()) goto L21;
        int r52 = ((Integer) r3.get(r6)).intValue();
        ArrayList r8 = r3;
        Extractor r9 = (Extractor) Assertions.checkNotNull(createExtractorByFileType(r52, r14, r15, r16));
        if (sniffQuietly(r9, r18) == true) goto L11;
        if (r42 != null) goto L19;
        if (r52 == r0) goto L18;
        if (r52 == r1) goto L18;
        if (r52 == r132) goto L18;
        if (r52 != 11) goto L19;
    L18:
        r42 = r9;
    L19:
        r6 = r6 + 1;
        r3 = r8;
        goto L7
    L11:
        return new BundledHlsMediaChunkExtractor(r9, r14, r16, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    L21:
        return new BundledHlsMediaChunkExtractor((Extractor) Assertions.checkNotNull(r42), r14, r16, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* bridge */ /* synthetic */ HlsMediaChunkExtractor createExtractor(Uri r1, Format r2, List r3, TimestampAdjuster r4, Map r5, ExtractorInput r6, PlayerId r7) {
        return createExtractor(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public DefaultHlsExtractorFactory experimentalParseSubtitlesDuringExtraction(boolean r1) {
        this.parseSubtitlesDuringExtraction = r1;
        return this;
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* bridge */ /* synthetic */ HlsExtractorFactory experimentalParseSubtitlesDuringExtraction(boolean r1) {
        return experimentalParseSubtitlesDuringExtraction(r1);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public DefaultHlsExtractorFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
        this.codecsToParseWithinGopSampleDependencies = r1;
        return this;
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* bridge */ /* synthetic */ HlsExtractorFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
        return experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public Format getOutputTextFormat(Format r4) {
        if (this.parseSubtitlesDuringExtraction == true) goto L5;
    L12:
        return r4;
    L5:
        if (this.subtitleParserFactory.supportsFormat(r4) == false) goto L12;
        Format.Builder r3 = r4.buildUpon().setSampleMimeType("application/x-media3-cues").setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(r4));
        StringBuilder r0 = new StringBuilder();
        r0.append(r4.sampleMimeType);
        if (r4.codecs == null) goto L9;
        String r42 = " " + r4.codecs;
    L10:
        r0.append(r42);
        return r3.setCodecs(r0.toString()).setSubsampleOffsetUs(LongCompanionObject.MAX_VALUE).build();
    L9:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public DefaultHlsExtractorFactory setSubtitleParserFactory(SubtitleParser.Factory r1) {
        this.subtitleParserFactory = r1;
        return this;
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* bridge */ /* synthetic */ HlsExtractorFactory setSubtitleParserFactory(SubtitleParser.Factory r1) {
        return setSubtitleParserFactory(r1);
    }
}
