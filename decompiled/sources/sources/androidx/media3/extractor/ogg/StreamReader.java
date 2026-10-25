package androidx.media3.extractor.ogg;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class StreamReader {
    private long currentGranule;
    private ExtractorOutput extractorOutput;
    private boolean formatSet;
    private long lengthOfReadPacket;
    private final OggPacket oggPacket;
    private OggSeeker oggSeeker;
    private long payloadStartPosition;
    private int sampleRate;
    private boolean seekMapSet;
    private SetupData setupData;
    private int state;
    private long targetGranule;
    private TrackOutput trackOutput;

    /* renamed from: androidx.media3.extractor.ogg.StreamReader$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class SetupData {
        Format format;
        OggSeeker oggSeeker;

        public SetupData() {
        }
    }

    public static final class UnseekableOggSeeker implements OggSeeker {
        private UnseekableOggSeeker() {
        }

        public /* synthetic */ UnseekableOggSeeker(AnonymousClass1 r1) {
            this();
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public SeekMap createSeekMap() {
            return new SeekMap.Unseekable(-9223372036854775807L);
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public long read(ExtractorInput r1) {
            return -1;
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public void startSeek(long r1) {
        }
    }

    public StreamReader() {
        this.oggPacket = new OggPacket();
        this.setupData = new SetupData();
    }

    @EnsuresNonNull({"trackOutput", "extractorOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    @EnsuresNonNullIf(expression = {"setupData.format"}, result = true)
    private boolean readHeaders(ExtractorInput r5) {
    L3:
        if (this.oggPacket.populate(r5) == false) goto L4;
        this.lengthOfReadPacket = r5.getPosition() - this.payloadStartPosition;
        if (readHeaders(this.oggPacket.getPayload(), this.payloadStartPosition, this.setupData) == false) goto L9;
        this.payloadStartPosition = r5.getPosition();
        goto L3
    L9:
        return true;
    L4:
        this.state = 3;
        return false;
    }

    @RequiresNonNull({"trackOutput"})
    private int readHeadersAndUpdateState(ExtractorInput r15) {
        if (readHeaders(r15) == true) goto L6;
        return -1;
    L6:
        Format r0 = this.setupData.format;
        this.sampleRate = r0.sampleRate;
        if (this.formatSet == true) goto L9;
        this.trackOutput.format(r0);
        this.formatSet = true;
    L9:
        OggSeeker r02 = this.setupData.oggSeeker;
        if (r02 == null) goto L13;
        this.oggSeeker = r02;
    L20:
        this.state = 2;
        this.oggPacket.trimPayload();
        return 0;
    L13:
        if (r15.getLength() != (-1)) goto L15;
        this.oggSeeker = new UnseekableOggSeeker(null);
        goto L20
    L15:
        OggPageHeader r03 = this.oggPacket.getPageHeader();
        if ((r03.type & 4) == 0) goto L18;
        boolean r10 = true;
    L19:
        this.oggSeeker = new DefaultOggSeeker(this, this.payloadStartPosition, r15.getLength(), r03.headerSize + r03.bodySize, r03.granulePosition, r10);
        goto L20
    L18:
        r10 = false;
        goto L19
    }

    @RequiresNonNull({"trackOutput", "oggSeeker", "extractorOutput"})
    private int readPayload(ExtractorInput r18, PositionHolder r19) {
        long r2 = this.oggSeeker.read(r18);
        if (r2 < 0) goto L7;
        r19.position = r2;
        return 1;
    L7:
        if (r2 >= (-1)) goto L10;
        onSeekEnd(-(r2 + 2));
    L10:
        if (this.seekMapSet == true) goto L13;
        SeekMap r22 = (SeekMap) Assertions.checkStateNotNull(this.oggSeeker.createSeekMap());
        this.extractorOutput.seekMap(r22);
        this.trackOutput.durationUs(r22.getDurationUs());
        this.seekMapSet = true;
    L13:
        if (this.lengthOfReadPacket <= 0) goto L15;
    L19:
        this.lengthOfReadPacket = 0;
        ParsableByteArray r1 = this.oggPacket.getPayload();
        long r23 = preparePayload(r1);
        if (r23 < 0) goto L24;
        long r4 = this.currentGranule;
        if ((r4 + r23) < this.targetGranule) goto L24;
        long r11 = convertGranuleToTime(r4);
        this.trackOutput.sampleData(r1, r1.limit());
        this.trackOutput.sampleMetadata(r11, 1, r1.limit(), 0, null);
        this.targetGranule = -1;
    L24:
        this.currentGranule += r23;
        return 0;
    L15:
        if (this.oggPacket.populate(r18) == true) goto L19;
        this.state = 3;
        return -1;
    }

    public long convertGranuleToTime(long r3) {
        return (r3 * 1000000) / this.sampleRate;
    }

    public long convertTimeToGranule(long r3) {
        return (this.sampleRate * r3) / 1000000;
    }

    public void init(ExtractorOutput r1, TrackOutput r2) {
        this.extractorOutput = r1;
        this.trackOutput = r2;
        reset(true);
    }

    public void onSeekEnd(long r1) {
        this.currentGranule = r1;
    }

    public abstract long preparePayload(ParsableByteArray r1);

    public final int read(ExtractorInput r4, PositionHolder r5) {
        assertInitialized();
        int r0 = this.state;
        if (r0 == 0) goto L18;
        if (r0 == 1) goto L15;
        if (r0 != 2) goto L8;
        Util.castNonNull(this.oggSeeker);
        return readPayload(r4, r5);
    L8:
        if (r0 != 3) goto L11;
        return -1;
    L11:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L15:
        r4.skipFully((int) this.payloadStartPosition);
        this.state = 2;
        return 0;
    L18:
        return readHeadersAndUpdateState(r4);
    }

    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public abstract boolean readHeaders(ParsableByteArray r1, long r2, SetupData r4);

    public void reset(boolean r5) {
        if (r5 == false) goto L5;
        this.setupData = new SetupData();
        this.payloadStartPosition = 0;
        this.state = 0;
    L6:
        this.targetGranule = -1;
        this.currentGranule = 0;
        return;
    L5:
        this.state = 1;
        goto L6
    }

    public final void seek(long r3, long r5) {
        this.oggPacket.reset();
        if (r3 != 0) goto L7;
        reset(!this.seekMapSet);
        return;
    L7:
        if (this.state == 0) goto L10;
        this.targetGranule = convertTimeToGranule(r5);
        ((OggSeeker) Util.castNonNull(this.oggSeeker)).startSeek(this.targetGranule);
        this.state = 2;
        return;
    }
}
