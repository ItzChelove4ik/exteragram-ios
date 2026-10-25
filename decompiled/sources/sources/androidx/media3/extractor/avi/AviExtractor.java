package androidx.media3.extractor.avi;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.NoOpExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.android.gms.cast.MediaStatus;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class AviExtractor implements Extractor {
    private AviMainHeaderChunk aviHeader;
    private final ChunkHeaderHolder chunkHeaderHolder;
    private ChunkReader[] chunkReaders;
    private ChunkReader currentChunkReader;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int hdrlSize;
    private int idx1BodySize;
    private long moviEnd;
    private long moviStart;
    private final boolean parseSubtitlesDuringExtraction;
    private long pendingReposition;
    private final ParsableByteArray scratch;
    private boolean seekMapHasBeenOutput;
    private int state;
    private final SubtitleParser.Factory subtitleParserFactory;

    /* renamed from: androidx.media3.extractor.avi.AviExtractor$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class AviSeekMap implements SeekMap {
        private final long durationUs;
        final /* synthetic */ AviExtractor this$0;

        public AviSeekMap(AviExtractor r1, long r2) {
            this.this$0 = r1;
            this.durationUs = r2;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long r8) {
            SeekMap.SeekPoints r0 = AviExtractor.access$100(this.this$0)[0].getSeekPoints(r8);
            int r1 = 1;
        L4:
            if (r1 >= AviExtractor.access$100(this.this$0).length) goto L9;
            SeekMap.SeekPoints r2 = AviExtractor.access$100(this.this$0)[r1].getSeekPoints(r8);
            if (r2.first.position >= r0.first.position) goto L8;
            r0 = r2;
        L8:
            r1 = r1 + 1;
            goto L4
        L9:
            return r0;
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }
    }

    public static class ChunkHeaderHolder {
        public int chunkType;
        public int listType;
        public int size;

        private ChunkHeaderHolder() {
        }

        public /* synthetic */ ChunkHeaderHolder(AnonymousClass1 r1) {
            this();
        }

        public void populateFrom(ParsableByteArray r2) {
            this.chunkType = r2.readLittleEndianInt();
            this.size = r2.readLittleEndianInt();
            this.listType = 0;
        }

        public void populateWithListHeaderFrom(ParsableByteArray r3) {
            populateFrom(r3);
            if (this.chunkType != 1414744396) goto L7;
            this.listType = r3.readLittleEndianInt();
            return;
        L7:
            throw ParserException.createForMalformedContainer("LIST expected, found: " + this.chunkType, null);
        }
    }

    public AviExtractor(int r2, SubtitleParser.Factory r3) {
        this.subtitleParserFactory = r3;
        boolean r32 = true;
        if ((r2 & 1) == 0) goto L6;
        r32 = false;
    L6:
        this.parseSubtitlesDuringExtraction = r32;
        this.scratch = new ParsableByteArray(12);
        this.chunkHeaderHolder = new ChunkHeaderHolder(null);
        this.extractorOutput = new NoOpExtractorOutput();
        this.chunkReaders = new ChunkReader[0];
        this.moviStart = -1;
        this.moviEnd = -1;
        this.hdrlSize = -1;
        this.durationUs = -9223372036854775807L;
    }

    public static /* synthetic */ ChunkReader[] access$100(AviExtractor r0) {
        return r0.chunkReaders;
    }

    private static void alignInputToEvenPosition(ExtractorInput r4) {
        if ((r4.getPosition() & 1) != 1) goto L6;
        r4.skipFully(1);
        return;
    }

    private ChunkReader getChunkReader(int r5) {
        ChunkReader[] r4 = this.chunkReaders;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        ChunkReader r2 = r4[r1];
        if (r2.handlesChunkId(r5) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return null;
    }

    private void parseHdrlBody(ParsableByteArray r7) {
        ListChunk r72 = ListChunk.parseFrom(1819436136, r7);
        if (r72.getType() != 1819436136) goto L20;
        AviMainHeaderChunk r0 = (AviMainHeaderChunk) r72.getChild(AviMainHeaderChunk.class);
        if (r0 == null) goto L18;
        this.aviHeader = r0;
        this.durationUs = r0.totalFrames * r0.frameDurationUs;
        ArrayList r02 = new ArrayList();
        UnmodifiableIterator<AviChunk> r73 = r72.children.iterator();
        int r2 = 0;
    L8:
        if (r73.hasNext() == false) goto L15;
        AviChunk r3 = r73.next();
        if (r3.getType() != 1819440243) goto L8;
        int r4 = r2 + 1;
        ChunkReader r22 = processStreamList((ListChunk) r3, r2);
        if (r22 == null) goto L14;
        r02.add(r22);
    L14:
        r2 = r4;
        goto L8
    L15:
        this.chunkReaders = (ChunkReader[]) r02.toArray(new ChunkReader[0]);
        this.extractorOutput.endTracks();
        return;
    L18:
        throw ParserException.createForMalformedContainer("AviHeader not found", null);
    L20:
        throw ParserException.createForMalformedContainer("Unexpected header list type " + r72.getType(), null);
    }

    private void parseIdx1Body(ParsableByteArray r11) {
        long r0 = peekSeekOffset(r11);
    L3:
        int r3 = 0;
        boolean r32 = false;
        if (r11.bytesLeft() < 16) goto L12;
        int r2 = r11.readLittleEndianInt();
        int r6 = r11.readLittleEndianInt();
        long r7 = r11.readLittleEndianInt() + r0;
        r11.skipBytes(4);
        ChunkReader r22 = getChunkReader(r2);
        if (r22 == null) goto L3;
        if ((r6 & 16) != 16) goto L11;
        r32 = true;
    L11:
        r22.appendIndexChunk(r7, r32);
        goto L3
    L12:
        ChunkReader[] r112 = this.chunkReaders;
        int r02 = r112.length;
    L13:
        if (r3 >= r02) goto L15;
        r112[r3].commitIndex();
        r3 = r3 + 1;
        goto L13
    L15:
        this.seekMapHasBeenOutput = true;
        int r113 = this.chunkReaders.length;
        ExtractorOutput r03 = this.extractorOutput;
        if (r113 != 0) goto L19;
        r03.seekMap(new SeekMap.Unseekable(this.durationUs));
        return;
    L19:
        r03.seekMap(new AviSeekMap(this, this.durationUs));
    }

    private long peekSeekOffset(ParsableByteArray r9) {
        long r2 = 0;
        if (r9.bytesLeft() >= 16) goto L5;
        return 0;
    L5:
        int r0 = r9.getPosition();
        r9.skipBytes(8);
        long r4 = r9.readLittleEndianInt();
        long r6 = this.moviStart;
        if (r4 > r6) goto L9;
        r2 = r6 + 8;
    L9:
        r9.setPosition(r0);
        return r2;
    }

    private ChunkReader processStreamList(ListChunk r8, int r9) {
        AviStreamHeaderChunk r0 = (AviStreamHeaderChunk) r8.getChild(AviStreamHeaderChunk.class);
        StreamFormatChunk r1 = (StreamFormatChunk) r8.getChild(StreamFormatChunk.class);
        if (r0 != null) goto L6;
        Log.w("AviExtractor", "Missing Stream Header");
        return null;
    L6:
        if (r1 != null) goto L9;
        Log.w("AviExtractor", "Missing Stream Format");
        return null;
    L9:
        long r4 = r0.getDurationUs();
        Format r12 = r1.format;
        Format.Builder r2 = r12.buildUpon();
        r2.setId(r9);
        int r6 = r0.suggestedBufferSize;
        if (r6 == 0) goto L12;
        r2.setMaxInputSize(r6);
    L12:
        StreamNameChunk r82 = (StreamNameChunk) r8.getChild(StreamNameChunk.class);
        if (r82 == null) goto L15;
        r2.setLabel(r82.name);
    L15:
        int r83 = MimeTypes.getTrackType(r12.sampleMimeType);
        if (r83 != 1) goto L18;
    L21:
        TrackOutput r84 = this.extractorOutput.track(r9, r83);
        r84.format(r2.build());
        r84.durationUs(r4);
        this.durationUs = Math.max(this.durationUs, r4);
        return new ChunkReader(r9, r0, r84);
    L18:
        if (r83 == 2) goto L21;
        return null;
    }

    private int readMoviChunks(ExtractorInput r8) {
        if (r8.getPosition() < this.moviEnd) goto L6;
        return -1;
    L6:
        ChunkReader r0 = this.currentChunkReader;
        if (r0 != null) goto L9;
        alignInputToEvenPosition(r8);
        int r2 = 12;
        r8.peekFully(this.scratch.getData(), 0, 12);
        this.scratch.setPosition(0);
        int r02 = this.scratch.readLittleEndianInt();
        ParsableByteArray r3 = this.scratch;
        if (r02 != 1414744396) goto L19;
        r3.setPosition(8);
        if (this.scratch.readLittleEndianInt() == 1769369453) goto L17;
        r2 = 8;
    L17:
        r8.skipFully(r2);
        r8.resetPeekPosition();
        return 0;
    L19:
        int r22 = r3.readLittleEndianInt();
        if (r02 != 1263424842) goto L23;
        this.pendingReposition = (r8.getPosition() + r22) + 8;
        return 0;
    L23:
        r8.skipFully(8);
        r8.resetPeekPosition();
        ChunkReader r03 = getChunkReader(r02);
        if (r03 != null) goto L27;
        this.pendingReposition = r8.getPosition() + r22;
        return 0;
    L27:
        r03.onChunkStart(r22);
        this.currentChunkReader = r03;
    L28:
        return 0;
    L9:
        if (r0.onChunkData(r8) == false) goto L28;
        this.currentChunkReader = null;
        goto L28
    }

    private boolean resolvePendingReposition(ExtractorInput r9, PositionHolder r10) {
        if (this.pendingReposition == (-1)) goto L11;
        long r0 = r9.getPosition();
        long r4 = this.pendingReposition;
        if (r4 >= r0) goto L7;
    L10:
        r10.position = r4;
        boolean r92 = true;
    L12:
        this.pendingReposition = -1;
        return r92;
    L7:
        if (r4 > (MediaStatus.COMMAND_STREAM_TRANSFER + r0)) goto L10;
        r9.skipFully((int) (r4 - r0));
    L11:
        r92 = false;
        goto L12
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r3) {
        this.state = 0;
        if (this.parseSubtitlesDuringExtraction == false) goto L5;
        r3 = new SubtitleTranscodingExtractorOutput(r3, this.subtitleParserFactory);
    L5:
        this.extractorOutput = r3;
        this.pendingReposition = -1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r13, PositionHolder r14) {
        if (resolvePendingReposition(r13, r14) == false) goto L6;
        return 1;
    L6:
        switch(this.state) {
            case 0: goto L53;
            case 1: goto L46;
            case 2: goto L44;
            case 3: goto L19;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L10;
            default: goto L7;
        };
    L7:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        ParsableByteArray r142 = new ParsableByteArray(this.idx1BodySize);
        r13.readFully(r142.getData(), 0, this.idx1BodySize);
        parseIdx1Body(r142);
        this.state = 6;
        this.pendingReposition = this.moviStart;
        return 0;
    L13:
        r13.readFully(this.scratch.getData(), 0, 8);
        this.scratch.setPosition(0);
        int r143 = this.scratch.readLittleEndianInt();
        int r0 = this.scratch.readLittleEndianInt();
        if (r143 != 829973609) goto L16;
        this.state = 5;
        this.idx1BodySize = r0;
    L17:
        return 0;
    L16:
        this.pendingReposition = r13.getPosition() + r0;
        goto L17
    L44:
        int r144 = this.hdrlSize - 4;
        ParsableByteArray r02 = new ParsableByteArray(r144);
        r13.readFully(r02.getData(), 0, r144);
        parseHdrlBody(r02);
        this.state = 3;
        return 0;
    L46:
        r13.readFully(this.scratch.getData(), 0, 12);
        this.scratch.setPosition(0);
        this.chunkHeaderHolder.populateWithListHeaderFrom(this.scratch);
        ChunkHeaderHolder r132 = this.chunkHeaderHolder;
        if (r132.listType != 1819436136) goto L51;
        this.hdrlSize = r132.size;
        this.state = 2;
        return 0;
    L51:
        throw ParserException.createForMalformedContainer("hdrl expected, found: " + this.chunkHeaderHolder.listType, null);
    L10:
        return readMoviChunks(r13);
    L19:
        if (this.moviStart == (-1)) goto L24;
        long r6 = r13.getPosition();
        long r8 = this.moviStart;
        if (r6 == r8) goto L24;
        this.pendingReposition = r8;
        return 0;
    L24:
        r13.peekFully(this.scratch.getData(), 0, 12);
        r13.resetPeekPosition();
        this.scratch.setPosition(0);
        this.chunkHeaderHolder.populateFrom(this.scratch);
        int r145 = this.scratch.readLittleEndianInt();
        int r1 = this.chunkHeaderHolder.chunkType;
        if (r1 != 1179011410) goto L29;
        r13.skipFully(12);
        return 0;
    L29:
        if (r1 == 1414744396) goto L31;
    L42:
        this.pendingReposition = (r13.getPosition() + this.chunkHeaderHolder.size) + 8;
        return 0;
    L31:
        if (r145 != 1769369453) goto L42;
        long r82 = r13.getPosition();
        this.moviStart = r82;
        this.moviEnd = (r82 + this.chunkHeaderHolder.size) + 8;
        if (this.seekMapHasBeenOutput == false) goto L36;
    L40:
        this.pendingReposition = r13.getPosition() + 12;
        this.state = 6;
        return 0;
    L36:
        if (((AviMainHeaderChunk) Assertions.checkNotNull(this.aviHeader)).hasIndex() == false) goto L39;
        this.state = 4;
        this.pendingReposition = this.moviEnd;
        return 0;
    L39:
        this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
        this.seekMapHasBeenOutput = true;
        goto L40
    L53:
        if (sniff(r13) == false) goto L57;
        r13.skipFully(12);
        this.state = 1;
        return 0;
    L57:
        throw ParserException.createForMalformedContainer("AVI Header List not found", null);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r4, long r6) {
        this.pendingReposition = -1;
        this.currentChunkReader = null;
        ChunkReader[] r62 = this.chunkReaders;
        int r7 = r62.length;
        int r1 = 0;
    L3:
        if (r1 >= r7) goto L6;
        r62[r1].seekToPosition(r4);
        r1 = r1 + 1;
        goto L3
    L6:
        if (r4 == 0) goto L8;
        this.state = 6;
        return;
    L8:
        if (this.chunkReaders.length != 0) goto L11;
        this.state = 0;
        return;
    L11:
        this.state = 3;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r4) {
        r4.peekFully(this.scratch.getData(), 0, 12);
        this.scratch.setPosition(0);
        if (this.scratch.readLittleEndianInt() == 1179011410) goto L5;
        return false;
    L5:
        this.scratch.skipBytes(4);
        if (this.scratch.readLittleEndianInt() != 541677121) goto L9;
        return true;
    L9:
        return false;
    }
}
