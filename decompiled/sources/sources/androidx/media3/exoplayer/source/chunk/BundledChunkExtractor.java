package androidx.media3.exoplayer.source.chunk;

import android.util.SparseArray;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.png.PngExtractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleExtractor;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {

    @Deprecated
    public static final Factory FACTORY = null;
    private static final PositionHolder POSITION_HOLDER = null;
    private final SparseArray<BindingTrackOutput> bindingTrackOutputs;
    private long endTimeUs;
    private final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public static final class BindingTrackOutput implements TrackOutput {
        private long endTimeUs;
        private final DiscardingTrackOutput fakeTrackOutput;
        private final int id;
        private final Format manifestFormat;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public BindingTrackOutput(int r1, int r2, Format r3) {
            this.id = r1;
            this.type = r2;
            this.manifestFormat = r3;
            this.fakeTrackOutput = new DiscardingTrackOutput();
        }

        public void bind(ChunkExtractor.TrackOutputProvider r1, long r2) {
            if (r1 != null) goto L5;
            this.trackOutput = this.fakeTrackOutput;
            return;
        L5:
            this.endTimeUs = r2;
            TrackOutput r12 = r1.track(this.id, this.type);
            this.trackOutput = r12;
            Format r0 = this.sampleFormat;
            if (r0 == null) goto L9;
            r12.format(r0);
            return;
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void format(Format r2) {
            Format r0 = this.manifestFormat;
            if (r0 == null) goto L5;
            r2 = r2.withManifestFormatInfo(r0);
        L5:
            this.sampleFormat = r2;
            ((TrackOutput) Util.castNonNull(this.trackOutput)).format(this.sampleFormat);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader r1, int r2, boolean r3, int r4) {
            return ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(r1, r2, r3);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray r1, int r2, int r3) {
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(r1, r2);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long r8, int r10, int r11, int r12, TrackOutput.CryptoData r13) {
            long r0 = this.endTimeUs;
            if (r0 != (-9223372036854775807L)) goto L5;
        L7:
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleMetadata(r8, r10, r11, r12, r13);
            return;
        L5:
            if (r8 < r0) goto L7;
            this.trackOutput = this.fakeTrackOutput;
            goto L7
        }
    }

    public static final class Factory implements ChunkExtractor.Factory {
        private int codecsToParseWithinGopSampleDependencies;
        private boolean parseSubtitlesDuringExtraction;
        private SubtitleParser.Factory subtitleParserFactory;

        public Factory() {
            this.subtitleParserFactory = new DefaultSubtitleParserFactory();
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public ChunkExtractor createProgressiveMediaExtractor(int r8, Format r9, boolean r10, List<Format> r11, TrackOutput r12, PlayerId r13) {
            String r132 = r9.containerMimeType;
            if (MimeTypes.isText(r132) == true) goto L5;
            int r1 = 1;
            if (MimeTypes.isMatroska(r132) == false) goto L16;
            if (this.parseSubtitlesDuringExtraction == true) goto L14;
            r1 = 3;
        L14:
            Extractor r102 = new MatroskaExtractor(this.subtitleParserFactory, r1);
        L29:
            return new BundledChunkExtractor(r102, r8, r9);
        L16:
            if (Objects.equals(r132, "image/jpeg") == false) goto L19;
            r102 = new JpegExtractor(1);
            goto L29
        L19:
            if (Objects.equals(r132, "image/png") == false) goto L21;
            r102 = new PngExtractor();
            goto L29
        L21:
            if (r10 == false) goto L23;
            int r103 = 4;
        L25:
            if (this.parseSubtitlesDuringExtraction == true) goto L27;
            r103 = r103 | 32;
        L27:
            int r2 = r103 | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies);
            r102 = new FragmentedMp4Extractor(this.subtitleParserFactory, r2, null, null, r11, r12);
            goto L29
        L23:
            r103 = 0;
            goto L25
        L5:
            if (this.parseSubtitlesDuringExtraction == true) goto L8;
            return null;
        L8:
            r102 = new SubtitleExtractor(this.subtitleParserFactory.create(r9), r9);
            goto L29
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
            this.parseSubtitlesDuringExtraction = r1;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public /* bridge */ /* synthetic */ ChunkExtractor.Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
            return experimentalParseSubtitlesDuringExtraction(r1);
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
            this.codecsToParseWithinGopSampleDependencies = r1;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public /* bridge */ /* synthetic */ ChunkExtractor.Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
            return experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
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

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
            this.subtitleParserFactory = (SubtitleParser.Factory) Assertions.checkNotNull(r1);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public /* bridge */ /* synthetic */ ChunkExtractor.Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
            return setSubtitleParserFactory(r1);
        }
    }

    static {
        FACTORY = new Factory();
        POSITION_HOLDER = new PositionHolder();
    }

    public BundledChunkExtractor(Extractor r1, int r2, Format r3) {
        this.extractor = r1;
        this.primaryTrackType = r2;
        this.primaryTrackManifestFormat = r3;
        this.bindingTrackOutputs = new SparseArray();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        Format[] r0 = new Format[this.bindingTrackOutputs.size()];
        int r1 = 0;
    L4:
        if (r1 >= this.bindingTrackOutputs.size()) goto L6;
        r0[r1] = (Format) Assertions.checkStateNotNull(this.bindingTrackOutputs.valueAt(r1).sampleFormat);
        r1 = r1 + 1;
        goto L4
    L6:
        this.sampleFormats = r0;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public ChunkIndex getChunkIndex() {
        SeekMap r1 = this.seekMap;
        if ((r1 instanceof ChunkIndex) == true) goto L5;
        return null;
    L5:
        return (ChunkIndex) r1;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void init(ChunkExtractor.TrackOutputProvider r7, long r8, long r10) {
        this.trackOutputProvider = r7;
        this.endTimeUs = r10;
        boolean r0 = this.extractorInitialized;
        Extractor r1 = this.extractor;
        if (r0 == true) goto L10;
        r1.init(this);
        if (r8 == (-9223372036854775807L)) goto L7;
        this.extractor.seek(0, r8);
    L7:
        this.extractorInitialized = true;
        return;
    L10:
        if (r8 != (-9223372036854775807L)) goto L12;
        r8 = 0;
    L12:
        r1.seek(0, r8);
        int r82 = 0;
    L14:
        if (r82 >= this.bindingTrackOutputs.size()) goto L16;
        this.bindingTrackOutputs.valueAt(r82).bind(r7, r10);
        r82 = r82 + 1;
        goto L14
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public boolean read(ExtractorInput r3) {
        int r2 = this.extractor.read(r3, POSITION_HOLDER);
        if (r2 == 1) goto L5;
        boolean r1 = true;
    L6:
        Assertions.checkState(r1);
        if (r2 != 0) goto L9;
        return true;
    L9:
        return false;
    L5:
        r1 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void release() {
        this.extractor.release();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap r1) {
        this.seekMap = r1;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int r4, int r5) {
        BindingTrackOutput r0 = this.bindingTrackOutputs.get(r4);
        if (r0 == null) goto L5;
    L13:
        return r0;
    L5:
        if (this.sampleFormats != null) goto L7;
        boolean r02 = true;
    L8:
        Assertions.checkState(r02);
        if (r5 != this.primaryTrackType) goto L11;
        Format r1 = this.primaryTrackManifestFormat;
    L12:
        r0 = new BindingTrackOutput(r4, r5, r1);
        r0.bind(this.trackOutputProvider, this.endTimeUs);
        this.bindingTrackOutputs.put(r4, r0);
        goto L13
    L11:
        r1 = null;
        goto L12
    L7:
        r02 = false;
        goto L8
    }
}
