package androidx.media3.extractor.ogg;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes4.dex */
public class OggExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private ExtractorOutput output;
    private StreamReader streamReader;
    private boolean streamReaderInitialized;

    /* renamed from: $r8$lambda$7kLxSBr0Vl2284opd-coBYMGG9o, reason: not valid java name */
    public static /* synthetic */ Extractor[] m858$r8$lambda$7kLxSBr0Vl2284opdcoBYMGG9o() {
        return new Extractor[]{new OggExtractor()};
    }

    static {
        FACTORY = new OggExtractor$$ExternalSyntheticLambda0();
    }

    public OggExtractor() {
    }

    private static ParsableByteArray resetPosition(ParsableByteArray r1) {
        r1.setPosition(0);
        return r1;
    }

    @EnsuresNonNullIf(expression = {"streamReader"}, result = true)
    private boolean sniffInternal(ExtractorInput r6) {
        OggPageHeader r0 = new OggPageHeader();
        if (r0.populate(r6, true) == true) goto L5;
    L17:
        return false;
    L5:
        if ((r0.type & 2) != 2) goto L17;
        int r02 = Math.min(r0.bodySize, 8);
        ParsableByteArray r2 = new ParsableByteArray(r02);
        r6.peekFully(r2.getData(), 0, r02);
        if (FlacReader.verifyBitstreamType(resetPosition(r2)) == false) goto L11;
        this.streamReader = new FlacReader();
    L16:
        return true;
    L11:
        if (VorbisReader.verifyBitstreamType(resetPosition(r2)) == false) goto L14;
        this.streamReader = new VorbisReader();
        goto L16
    L14:
        if (OpusReader.verifyBitstreamType(resetPosition(r2)) == false) goto L17;
        this.streamReader = new OpusReader();
        goto L16
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r1) {
        this.output = r1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r5, PositionHolder r6) {
        Assertions.checkStateNotNull(this.output);
        if (this.streamReader != null) goto L10;
        if (sniffInternal(r5) == false) goto L8;
        r5.resetPeekPosition();
        goto L10
    L8:
        throw ParserException.createForMalformedContainer("Failed to determine bitstream type", null);
    L10:
        if (this.streamReaderInitialized == true) goto L13;
        TrackOutput r0 = this.output.track(0, 1);
        this.output.endTracks();
        this.streamReader.init(this.output, r0);
        this.streamReaderInitialized = true;
    L13:
        return this.streamReader.read(r5, r6);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        StreamReader r0 = this.streamReader;
        if (r0 == null) goto L6;
        r0.seek(r1, r3);
        return;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r1) {
        return sniffInternal(r1);
    L4:
        return false;
    }
}
