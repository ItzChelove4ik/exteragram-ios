package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.common.FileTypes;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.amr.AmrExtractor;
import androidx.media3.extractor.avi.AviExtractor;
import androidx.media3.extractor.avif.AvifExtractor;
import androidx.media3.extractor.bmp.BmpExtractor;
import androidx.media3.extractor.flac.FlacExtractor;
import androidx.media3.extractor.flv.FlvExtractor;
import androidx.media3.extractor.heif.HeifExtractor;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.ogg.OggExtractor;
import androidx.media3.extractor.png.PngExtractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.extractor.wav.WavExtractor;
import androidx.media3.extractor.webp.WebpExtractor;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = null;
    private static final ExtensionLoader FLAC_EXTENSION_LOADER = null;
    private static final ExtensionLoader MIDI_EXTENSION_LOADER = null;
    private int adtsFlags;
    private int amrFlags;
    private int codecsToParseWithinGopSampleDependencies;
    private boolean constantBitrateSeekingAlwaysEnabled;
    private boolean constantBitrateSeekingEnabled;
    private int flacFlags;
    private int fragmentedMp4Flags;
    private int jpegFlags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private SubtitleParser.Factory subtitleParserFactory;
    private boolean textTrackTranscodingEnabled;
    private int tsFlags;
    private int tsMode;
    private ImmutableList<Format> tsSubtitleFormats;
    private int tsTimestampSearchBytes;

    public static final class ExtensionLoader {
        private final ConstructorSupplier constructorSupplier;
        private final AtomicBoolean extensionLoaded;
        private Constructor<? extends Extractor> extractorConstructor;

        public interface ConstructorSupplier {
            Constructor<? extends Extractor> getConstructor();
        }

        public ExtensionLoader(ConstructorSupplier r2) {
            this.constructorSupplier = r2;
            this.extensionLoaded = new AtomicBoolean(false);
        }

        private Constructor<? extends Extractor> maybeLoadExtractorConstructor() {
            AtomicBoolean r0 = this.extensionLoaded;
            monitor-enter(r0);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (this.extensionLoaded.get() == false) goto L23;
            Constructor<? extends Extractor> r3 = this.extractorConstructor;     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
            return r3;
        L23:
            Constructor<? extends Extractor> r32 = this.constructorSupplier.getConstructor();     // Catch: Throwable -> L9 Exception -> L14 ClassNotFoundException -> L17
            monitor-exit(r0);     // Catch: Throwable -> L9
            return r32;
        L17:
            this.extensionLoaded.set(true);     // Catch: Throwable -> L9
            return this.extractorConstructor;
        L14:
            e = move-exception;
            throw new RuntimeException("Error instantiating extension", e);     // Catch: Throwable -> L9
        }

        public Extractor getExtractor(Object... r2) {
            Constructor<? extends Extractor> r1 = maybeLoadExtractorConstructor();
            if (r1 != null) goto L10;
            return null;
        L10:
            return r1.newInstance(r2);
        L7:
            e = move-exception;
            Constants$$ExternalSyntheticBUOutline0.m("Unexpected error creating extractor", e);
            return null;
        }
    }

    public static /* synthetic */ Constructor $r8$lambda$F3_j6dVylL4MI0cE_5Js_FX16M4() {
        return getFlacExtractorConstructor();
    }

    /* renamed from: $r8$lambda$f2WO-QnNuJkSXUlyLvKOvkcEmrY, reason: not valid java name */
    public static /* synthetic */ Constructor m854$r8$lambda$f2WOQnNuJkSXUlyLvKOvkcEmrY() {
        return getMidiExtractorConstructor();
    }

    static {
        DEFAULT_EXTRACTOR_ORDER = new int[]{5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14, 17, 18, 19, 20, 21};
        FLAC_EXTENSION_LOADER = new ExtensionLoader(new DefaultExtractorsFactory$$ExternalSyntheticLambda0());
        MIDI_EXTENSION_LOADER = new ExtensionLoader(new DefaultExtractorsFactory$$ExternalSyntheticLambda1());
    }

    public DefaultExtractorsFactory() {
        this.constantBitrateSeekingEnabled = true;
        this.tsMode = 1;
        this.tsTimestampSearchBytes = 112800;
        this.subtitleParserFactory = new DefaultSubtitleParserFactory();
        this.textTrackTranscodingEnabled = true;
    }

    private void addExtractorsForFileType(int r8, List<Extractor> r9) {
        int r0 = 2;
        int r1 = 0;
        switch(r8) {
            case 0: goto L81;
            case 1: goto L79;
            case 2: goto L73;
            case 3: goto L67;
            case 4: goto L61;
            case 5: goto L59;
            case 6: goto L54;
            case 7: goto L48;
            case 8: goto L38;
            case 9: goto L36;
            case 10: goto L34;
            case 11: goto L30;
            case 12: goto L27;
            case 13: goto L86;
            case 14: goto L25;
            case 15: goto L21;
            case 16: goto L19;
            case 17: goto L17;
            case 18: goto L15;
            case 19: goto L13;
            case 20: goto L7;
            case 21: goto L5;
            default: goto L86;
        };
    L5:
        r9.add(new AvifExtractor());
        return;
    L7:
        int r7 = this.mp4Flags;
        if ((r7 & 2) == 0) goto L10;
        return;
    L10:
        if ((r7 & 4) != 0) goto L84;
        r9.add(new HeifExtractor());
        return;
    L84:
        return;
    L13:
        r9.add(new BmpExtractor());
        return;
    L15:
        r9.add(new WebpExtractor());
        return;
    L17:
        r9.add(new PngExtractor());
        return;
    L19:
        r9.add(new AviExtractor(!this.textTrackTranscodingEnabled ? 1 : 0, this.subtitleParserFactory));
        return;
    L21:
        Extractor r72 = MIDI_EXTENSION_LOADER.getExtractor(new Object[0]);
        if (r72 == null) goto L85;
        r9.add(r72);
        return;
    L85:
        return;
    L25:
        r9.add(new JpegExtractor(this.jpegFlags));
        return;
    L27:
        r9.add(new WavExtractor());
        return;
    L34:
        r9.add(new PsExtractor());
        return;
    L36:
        r9.add(new OggExtractor());
        return;
    L38:
        SubtitleParser.Factory r02 = this.subtitleParserFactory;
        int r2 = this.fragmentedMp4Flags | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies);
        if (this.textTrackTranscodingEnabled == false) goto L41;
        int r3 = 0;
    L42:
        r9.add(new FragmentedMp4Extractor(r02, r2 | r3));
        SubtitleParser.Factory r03 = this.subtitleParserFactory;
        int r22 = this.mp4Flags | Mp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies);
        if (this.textTrackTranscodingEnabled == true) goto L46;
        r1 = 16;
    L46:
        r9.add(new Mp4Extractor(r03, r22 | r1));
        return;
    L41:
        r3 = 32;
        goto L42
    L48:
        int r23 = this.mp3Flags | (this.constantBitrateSeekingEnabled ? 1 : 0);
        if (this.constantBitrateSeekingAlwaysEnabled == true) goto L52;
        r0 = 0;
    L52:
        r9.add(new Mp3Extractor(r23 | r0));
        return;
    L54:
        SubtitleParser.Factory r24 = this.subtitleParserFactory;
        int r32 = this.matroskaFlags;
        if (this.textTrackTranscodingEnabled == false) goto L57;
        r0 = 0;
    L57:
        r9.add(new MatroskaExtractor(r24, r32 | r0));
        return;
    L59:
        r9.add(new FlvExtractor());
        return;
    L61:
        Extractor r82 = FLAC_EXTENSION_LOADER.getExtractor(new Object[]{Integer.valueOf(this.flacFlags)});
        if (r82 == null) goto L65;
        r9.add(r82);
        return;
    L65:
        r9.add(new FlacExtractor(this.flacFlags));
        return;
    L67:
        int r25 = this.amrFlags | (this.constantBitrateSeekingEnabled ? 1 : 0);
        if (this.constantBitrateSeekingAlwaysEnabled == true) goto L71;
        r0 = 0;
    L71:
        r9.add(new AmrExtractor(r25 | r0));
        return;
    L73:
        int r26 = this.adtsFlags | (this.constantBitrateSeekingEnabled ? 1 : 0);
        if (this.constantBitrateSeekingAlwaysEnabled == true) goto L77;
        r0 = 0;
    L77:
        r9.add(new AdtsExtractor(r26 | r0));
        return;
    L79:
        r9.add(new Ac4Extractor());
        return;
    L81:
        r9.add(new Ac3Extractor());
        return;
    L86:
        return;
    L30:
        if (this.tsSubtitleFormats != null) goto L32;
        this.tsSubtitleFormats = ImmutableList.of();
    L32:
        r9.add(new TsExtractor(this.tsMode, !this.textTrackTranscodingEnabled ? 1 : 0, this.subtitleParserFactory, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(this.tsFlags, this.tsSubtitleFormats), this.tsTimestampSearchBytes));
    }

    private static Constructor<? extends Extractor> getFlacExtractorConstructor() {
        if (Boolean.TRUE.equals(Class.forName("androidx.media3.decoder.flac.FlacLibrary").getMethod("isAvailable", null).invoke(null, null)) == true) goto L5;
        return null;
    L5:
        return Class.forName("androidx.media3.decoder.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(new Class[]{Integer.TYPE});
    }

    private static Constructor<? extends Extractor> getMidiExtractorConstructor() {
        return Class.forName("androidx.media3.decoder.midi.MidiExtractor").asSubclass(Extractor.class).getConstructor(null);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors() {
        monitor-enter(this);
        Extractor[] r0 = createExtractors(Uri.EMPTY, new HashMap());     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors(Uri r7, Map<String, List<String>> r8) {
        monitor-enter(this);
        int[] r1 = DEFAULT_EXTRACTOR_ORDER;     // Catch: Throwable -> L6
        ArrayList r0 = new ArrayList(r1.length);     // Catch: Throwable -> L6
        int r82 = FileTypes.inferFileTypeFromResponseHeaders(r8);     // Catch: Throwable -> L6
        if (r82 == (-1)) goto L8;
        addExtractorsForFileType(r82, r0);     // Catch: Throwable -> L6
    L8:
        int r72 = FileTypes.inferFileTypeFromUri(r7);     // Catch: Throwable -> L6
        if (r72 == (-1)) goto L12;
        if (r72 == r82) goto L12;
        addExtractorsForFileType(r72, r0);     // Catch: Throwable -> L6
    L12:
        int r2 = r1.length;     // Catch: Throwable -> L6
        int r4 = 0;
    L13:
        if (r4 >= r2) goto L19;
        int r5 = r1[r4];     // Catch: Throwable -> L6
        if (r5 == r82) goto L18;
        if (r5 == r72) goto L18;
        addExtractorsForFileType(r5, r0);     // Catch: Throwable -> L6
    L18:
        r4 = r4 + 1;     // Catch: Throwable -> L6
        goto L13
    L19:
        Extractor[] r73 = (Extractor[]) r0.toArray(new Extractor[0]);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r73;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized DefaultExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
        monitor-enter(this);
        this.codecsToParseWithinGopSampleDependencies = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return this;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* bridge */ /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
        return experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    @Deprecated
    public synchronized DefaultExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean r1) {
        monitor-enter(this);
        this.textTrackTranscodingEnabled = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return this;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    @Deprecated
    public /* bridge */ /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean r1) {
        return experimentalSetTextTrackTranscodingEnabled(r1);
    }

    public synchronized DefaultExtractorsFactory setJpegExtractorFlags(int r1) {
        monitor-enter(this);
        this.jpegFlags = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return this;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized DefaultExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory r1) {
        monitor-enter(this);
        this.subtitleParserFactory = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return this;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* bridge */ /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory r1) {
        return setSubtitleParserFactory(r1);
    }
}
