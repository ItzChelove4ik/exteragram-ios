package androidx.media3.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import androidx.mediarouter.media.GlobalMediaRouter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class TsExtractor implements Extractor {

    @Deprecated
    public static final ExtractorsFactory FACTORY = null;
    private int bytesSinceLastSync;
    private final SparseIntArray continuityCounters;
    private final TsDurationReader durationReader;
    private final int extractorFlags;
    private boolean hasOutputSeekMap;
    private TsPayloadReader id3Reader;
    private final int mode;
    private ExtractorOutput output;
    private final TsPayloadReader.Factory payloadReaderFactory;
    private int pcrPid;
    private boolean pendingSeekToStart;
    private int remainingPmts;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final List<TimestampAdjuster> timestampAdjusters;
    private final int timestampSearchBytes;
    private final SparseBooleanArray trackIds;
    private final SparseBooleanArray trackPids;
    private boolean tracksEnded;
    private TsBinarySearchSeeker tsBinarySearchSeeker;
    private final ParsableByteArray tsPacketBuffer;
    private final SparseArray<TsPayloadReader> tsPayloadReaders;

    public class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch;
        final /* synthetic */ TsExtractor this$0;

        public PatReader(TsExtractor r2) {
            this.this$0 = r2;
            this.patScratch = new ParsableBitArray(new byte[4]);
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void consume(ParsableByteArray r10) {
            if (r10.readUnsignedByte() == 0) goto L6;
            return;
        L6:
            if ((r10.readUnsignedByte() & 128) == 0) goto L27;
            r10.skipBytes(6);
            int r0 = r10.bytesLeft() / 4;
            int r3 = 0;
        L9:
            if (r3 >= r0) goto L18;
            r10.readBytes(this.patScratch, 4);
            int r4 = this.patScratch.readBits(16);
            this.patScratch.skipBits(3);
            ParsableBitArray r5 = this.patScratch;
            if (r4 != 0) goto L13;
            r5.skipBits(13);
        L16:
            r3 = r3 + 1;
            goto L9
        L13:
            int r42 = r5.readBits(13);
            if (TsExtractor.access$000(this.this$0).get(r42) != null) goto L16;
            TsExtractor.access$000(this.this$0).put(r42, new SectionReader(new PmtReader(this.this$0, r42)));
            TsExtractor.access$108(this.this$0);
            goto L16
        L18:
            if (TsExtractor.access$200(this.this$0) == 2) goto L25;
            TsExtractor.access$000(this.this$0).remove(0);
            return;
        L25:
            return;
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void init(TimestampAdjuster r1, ExtractorOutput r2, TsPayloadReader.TrackIdGenerator r3) {
        }
    }

    public class PmtReader implements SectionPayloadReader {
        private final int pid;
        private final ParsableBitArray pmtScratch;
        final /* synthetic */ TsExtractor this$0;
        private final SparseIntArray trackIdToPidScratch;
        private final SparseArray<TsPayloadReader> trackIdToReaderScratch;

        public PmtReader(TsExtractor r2, int r3) {
            this.this$0 = r2;
            this.pmtScratch = new ParsableBitArray(new byte[5]);
            this.trackIdToReaderScratch = new SparseArray();
            this.trackIdToPidScratch = new SparseIntArray();
            this.pid = r3;
        }

        private TsPayloadReader.EsInfo readEsInfo(ParsableByteArray r14, int r15) {
            int r13 = r14.getPosition();
            int r152 = r15 + r13;
            int r4 = -1;
            String r5 = null;
            ArrayList r7 = null;
            int r6 = 0;
        L4:
            if (r14.getPosition() >= r152) goto L57;
            int r0 = r14.readUnsignedByte();
            int r3 = r14.getPosition() + r14.readUnsignedByte();
            if (r3 > r152) goto L57;
            if (r0 != 5) goto L23;
            long r02 = r14.readUnsignedInt();
            if (r02 != 1094921523) goto L14;
        L24:
            r4 = 129;
        L56:
            r14.skipBytes(r3 - r14.getPosition());
            goto L4
        L14:
            if (r02 != 1161904947) goto L17;
        L27:
            r4 = 135;
            goto L56
        L17:
            if (r02 != 1094921524) goto L20;
        L18:
            r4 = 172;
            goto L56
        L20:
            if (r02 != 1212503619) goto L56;
            r4 = 36;
            goto L56
        L23:
            if (r0 == 106) goto L24;
            if (r0 == 122) goto L27;
            if (r0 != 127) goto L40;
            int r03 = r14.readUnsignedByte();
            if (r03 == 21) goto L18;
            if (r03 != 14) goto L37;
            r4 = 136;
            goto L56
        L37:
            if (r03 != 33) goto L56;
            r4 = 139;
            goto L56
        L40:
            if (r0 != 123) goto L44;
            int r04 = 138;
        L42:
            r4 = r04;
            goto L56
        L44:
            if (r0 != 10) goto L47;
            String r05 = r14.readString(3).trim();
            r6 = r14.readUnsignedByte();
            r5 = r05;
            goto L56
        L47:
            if (r0 != 89) goto L54;
            ArrayList r06 = new ArrayList();
        L50:
            if (r14.getPosition() >= r3) goto L52;
            String r42 = r14.readString(3).trim();
            int r72 = r14.readUnsignedByte();
            byte[] r10 = new byte[4];
            r14.readBytes(r10, 0, 4);
            r06.add(new TsPayloadReader.DvbSubtitleInfo(r42, r72, r10));
            goto L50
        L52:
            r7 = r06;
            r4 = 89;
            goto L56
        L54:
            if (r0 != 111) goto L56;
            r04 = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        L57:
            r14.setPosition(r152);
            return new TsPayloadReader.EsInfo(r4, r5, r6, r7, Arrays.copyOfRange(r14.getData(), r13, r152));
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void consume(ParsableByteArray r21) {
            if (r21.readUnsignedByte() == 2) goto L6;
            return;
        L6:
            if (TsExtractor.access$200(this.this$0) != 1) goto L8;
        L13:
            TimestampAdjuster r2 = (TimestampAdjuster) TsExtractor.access$300(this.this$0).get(0);
        L15:
            if ((r21.readUnsignedByte() & 128) == 0) goto L81;
            r21.skipBytes(1);
            int r6 = r21.readUnsignedShort();
            int r7 = 3;
            r21.skipBytes(3);
            r21.readBytes(this.pmtScratch, 2);
            this.pmtScratch.skipBits(3);
            int r10 = 13;
            TsExtractor.access$402(this.this$0, this.pmtScratch.readBits(13));
            r21.readBytes(this.pmtScratch, 2);
            int r9 = 4;
            this.pmtScratch.skipBits(4);
            r21.skipBytes(this.pmtScratch.readBits(12));
            if (TsExtractor.access$200(this.this$0) == 2) goto L20;
        L24:
            this.trackIdToReaderScratch.clear();
            this.trackIdToPidScratch.clear();
            int r8 = r21.bytesLeft();
        L25:
            if (r8 <= 0) goto L48;
            r21.readBytes(this.pmtScratch, 5);
            int r4 = this.pmtScratch.readBits(8);
            this.pmtScratch.skipBits(r7);
            int r14 = this.pmtScratch.readBits(r10);
            this.pmtScratch.skipBits(r9);
            int r72 = this.pmtScratch.readBits(12);
            TsPayloadReader.EsInfo r92 = readEsInfo(r21, r72);
            if (r4 == 6) goto L29;
            if (r4 == 5) goto L29;
        L30:
            r8 = r8 - (r72 + 5);
            if (TsExtractor.access$200(this.this$0) != 2) goto L33;
            int r73 = r4;
        L35:
            if (TsExtractor.access$800(this.this$0).get(r73) == true) goto L47;
            if (TsExtractor.access$200(this.this$0) != 2) goto L41;
            if (r4 != 21) goto L41;
            TsPayloadReader r42 = TsExtractor.access$500(this.this$0);
        L43:
            if (TsExtractor.access$200(this.this$0) == 2) goto L45;
        L46:
            this.trackIdToPidScratch.put(r73, r14);
            this.trackIdToReaderScratch.put(r73, r42);
            goto L47
        L45:
            if (r14 >= this.trackIdToPidScratch.get(r73, 8192)) goto L47;
        L41:
            r42 = TsExtractor.access$600(this.this$0).createPayloadReader(r4, r92);
        L47:
            r7 = 3;
            r9 = 4;
            r10 = 13;
            goto L25
        L33:
            r73 = r14;
        L29:
            r4 = r92.streamType;
            goto L30
        L48:
            int r1 = this.trackIdToPidScratch.size();
            int r43 = 0;
        L49:
            if (r43 >= r1) goto L57;
            int r74 = this.trackIdToPidScratch.keyAt(r43);
            int r82 = this.trackIdToPidScratch.valueAt(r43);
            TsExtractor.access$800(this.this$0).put(r74, true);
            TsExtractor.access$900(this.this$0).put(r82, true);
            TsPayloadReader r93 = this.trackIdToReaderScratch.valueAt(r43);
            if (r93 == null) goto L56;
            if (r93 == TsExtractor.access$500(this.this$0)) goto L55;
            r93.init(r2, TsExtractor.access$700(this.this$0), new TsPayloadReader.TrackIdGenerator(r6, r74, 8192));
        L55:
            TsExtractor.access$000(this.this$0).put(r82, r93);
        L56:
            r43 = r43 + 1;
            goto L49
        L57:
            int r12 = TsExtractor.access$200(this.this$0);
            TsExtractor r22 = this.this$0;
            if (r12 == 2) goto L60;
            TsExtractor.access$000(r22).remove(this.pid);
            TsExtractor r13 = this.this$0;
            if (TsExtractor.access$200(r13) != 1) goto L66;
            int r44 = 0;
        L67:
            TsExtractor.access$102(r13, r44);
            if (TsExtractor.access$100(this.this$0) != 0) goto L79;
            TsExtractor.access$700(this.this$0).endTracks();
            TsExtractor.access$1002(this.this$0, true);
            return;
        L79:
            return;
        L66:
            r44 = TsExtractor.access$100(this.this$0) - 1;
            goto L67
        L60:
            if (TsExtractor.access$1000(r22) == true) goto L78;
            TsExtractor.access$700(this.this$0).endTracks();
            TsExtractor.access$102(this.this$0, 0);
            TsExtractor.access$1002(this.this$0, true);
            return;
        L78:
            return;
        L20:
            if (TsExtractor.access$500(this.this$0) != null) goto L24;
            TsPayloadReader.EsInfo r142 = new TsPayloadReader.EsInfo(21, null, 0, null, Util.EMPTY_BYTE_ARRAY);
            TsExtractor r83 = this.this$0;
            TsExtractor.access$502(r83, TsExtractor.access$600(r83).createPayloadReader(21, r142));
            if (TsExtractor.access$500(this.this$0) == null) goto L24;
            TsExtractor.access$500(this.this$0).init(r2, TsExtractor.access$700(this.this$0), new TsPayloadReader.TrackIdGenerator(r6, 21, 8192));
            goto L24
        L81:
            return;
        L8:
            if (TsExtractor.access$200(this.this$0) == 2) goto L13;
            if (TsExtractor.access$100(this.this$0) == 1) goto L13;
            r2 = new TimestampAdjuster(((TimestampAdjuster) TsExtractor.access$300(this.this$0).get(0)).getFirstSampleTimestampUs());
            TsExtractor.access$300(this.this$0).add(r2);
            goto L15
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void init(TimestampAdjuster r1, ExtractorOutput r2, TsPayloadReader.TrackIdGenerator r3) {
        }
    }

    public static /* synthetic */ Extractor[] $r8$lambda$7A6xD9j_oCVPmIlJoNJiJvwSydQ() {
        return new Extractor[]{new TsExtractor(1, SubtitleParser.Factory.UNSUPPORTED)};
    }

    static {
        FACTORY = new TsExtractor$$ExternalSyntheticLambda0();
    }

    public TsExtractor(int r1, int r2, SubtitleParser.Factory r3, TimestampAdjuster r4, TsPayloadReader.Factory r5, int r6) {
        this.payloadReaderFactory = (TsPayloadReader.Factory) Assertions.checkNotNull(r5);
        this.timestampSearchBytes = r6;
        this.mode = r1;
        this.extractorFlags = r2;
        this.subtitleParserFactory = r3;
        if (r1 != 1) goto L5;
    L8:
        this.timestampAdjusters = Collections.singletonList(r4);
    L9:
        this.tsPacketBuffer = new ParsableByteArray(new byte[9400], 0);
        this.trackIds = new SparseBooleanArray();
        this.trackPids = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray();
        this.continuityCounters = new SparseIntArray();
        this.durationReader = new TsDurationReader(r6);
        this.output = ExtractorOutput.PLACEHOLDER;
        this.pcrPid = -1;
        resetPayloadReaders();
        return;
    L5:
        if (r1 == 2) goto L8;
        ArrayList r12 = new ArrayList();
        this.timestampAdjusters = r12;
        r12.add(r4);
        goto L9
    }

    public TsExtractor(int r8, SubtitleParser.Factory r9) {
        this(1, r8, r9, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(0), 112800);
    }

    public static /* synthetic */ SparseArray access$000(TsExtractor r0) {
        return r0.tsPayloadReaders;
    }

    public static /* synthetic */ int access$100(TsExtractor r0) {
        return r0.remainingPmts;
    }

    public static /* synthetic */ boolean access$1000(TsExtractor r0) {
        return r0.tracksEnded;
    }

    public static /* synthetic */ boolean access$1002(TsExtractor r0, boolean r1) {
        r0.tracksEnded = r1;
        return r1;
    }

    public static /* synthetic */ int access$102(TsExtractor r0, int r1) {
        r0.remainingPmts = r1;
        return r1;
    }

    public static /* synthetic */ int access$108(TsExtractor r2) {
        int r0 = r2.remainingPmts;
        r2.remainingPmts = r0 + 1;
        return r0;
    }

    public static /* synthetic */ int access$200(TsExtractor r0) {
        return r0.mode;
    }

    public static /* synthetic */ List access$300(TsExtractor r0) {
        return r0.timestampAdjusters;
    }

    public static /* synthetic */ int access$402(TsExtractor r0, int r1) {
        r0.pcrPid = r1;
        return r1;
    }

    public static /* synthetic */ TsPayloadReader access$500(TsExtractor r0) {
        return r0.id3Reader;
    }

    public static /* synthetic */ TsPayloadReader access$502(TsExtractor r0, TsPayloadReader r1) {
        r0.id3Reader = r1;
        return r1;
    }

    public static /* synthetic */ TsPayloadReader.Factory access$600(TsExtractor r0) {
        return r0.payloadReaderFactory;
    }

    public static /* synthetic */ ExtractorOutput access$700(TsExtractor r0) {
        return r0.output;
    }

    public static /* synthetic */ SparseBooleanArray access$800(TsExtractor r0) {
        return r0.trackIds;
    }

    public static /* synthetic */ SparseBooleanArray access$900(TsExtractor r0) {
        return r0.trackPids;
    }

    private boolean fillBufferWithAtLeastOnePacket(ExtractorInput r7) {
        byte[] r0 = this.tsPacketBuffer.getData();
        if ((9400 - this.tsPacketBuffer.getPosition()) >= 188) goto L9;
        int r1 = this.tsPacketBuffer.bytesLeft();
        if (r1 <= 0) goto L7;
        System.arraycopy(r0, this.tsPacketBuffer.getPosition(), r0, 0, r1);
    L7:
        this.tsPacketBuffer.reset(r0, r1);
    L9:
        if (this.tsPacketBuffer.bytesLeft() >= 188) goto L14;
        int r12 = this.tsPacketBuffer.limit();
        int r4 = r7.read(r0, r12, 9400 - r12);
        if (r4 == (-1)) goto L12;
        this.tsPacketBuffer.setLimit(r12 + r4);
        goto L9
    L12:
        return false;
    L14:
        return true;
    }

    private int findEndOfFirstTsPacketInBuffer() {
        int r0 = this.tsPacketBuffer.getPosition();
        int r1 = this.tsPacketBuffer.limit();
        int r2 = TsUtil.findSyncBytePosition(this.tsPacketBuffer.getData(), r0, r1);
        this.tsPacketBuffer.setPosition(r2);
        int r3 = r2 + 188;
        if (r3 <= r1) goto L12;
        int r12 = this.bytesSinceLastSync + (r2 - r0);
        this.bytesSinceLastSync = r12;
        if (this.mode == 2) goto L7;
    L11:
        return r3;
    L7:
        if (r12 <= 376) goto L11;
        throw ParserException.createForMalformedContainer("Cannot find sync byte. Most likely not a Transport Stream.", null);
    L12:
        this.bytesSinceLastSync = 0;
        return r3;
    }

    private void maybeOutputSeekMap(long r10) {
        if (this.hasOutputSeekMap == true) goto L10;
        this.hasOutputSeekMap = true;
        if (this.durationReader.getDurationUs() == (-9223372036854775807L)) goto L8;
        TsBinarySearchSeeker r1 = new TsBinarySearchSeeker(this.durationReader.getPcrTimestampAdjuster(), this.durationReader.getDurationUs(), r10, this.pcrPid, this.timestampSearchBytes);
        this.tsBinarySearchSeeker = r1;
        this.output.seekMap(r1.getSeekMap());
        return;
    L8:
        this.output.seekMap(new SeekMap.Unseekable(this.durationReader.getDurationUs()));
        return;
    }

    private void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray<TsPayloadReader> r0 = this.payloadReaderFactory.createInitialPayloadReaders();
        int r1 = r0.size();
        int r3 = 0;
    L3:
        SparseArray<TsPayloadReader> r4 = this.tsPayloadReaders;
        if (r3 >= r1) goto L6;
        r4.put(r0.keyAt(r3), r0.valueAt(r3));
        r3 = r3 + 1;
        goto L3
    L6:
        r4.put(0, new SectionReader(new PatReader(this)));
        this.id3Reader = null;
    }

    private boolean shouldConsumePacketPayload(int r3) {
        if (this.mode != 2) goto L5;
        return true;
    L5:
        if (this.tracksEnded == false) goto L7;
        return true;
    L7:
        if (this.trackPids.get(r3, false) == false) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r3) {
        if ((this.extractorFlags & 1) != 0) goto L5;
        r3 = new SubtitleTranscodingExtractorOutput(r3, this.subtitleParserFactory);
    L5:
        this.output = r3;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r18, PositionHolder r19) {
        long r3 = r18.getLength();
        int r7 = 0;
        if (this.mode != 2) goto L5;
        boolean r5 = true;
    L7:
        if (this.tracksEnded == false) goto L28;
        if (r3 == (-1)) goto L15;
        if (r5 == true) goto L15;
        if (this.durationReader.isDurationReadFinished() == true) goto L15;
        return this.durationReader.readDuration(r18, r19, this.pcrPid);
    L15:
        maybeOutputSeekMap(r3);
        if (this.pendingSeekToStart == false) goto L21;
        this.pendingSeekToStart = false;
        seek(0, 0);
        if (r18.getPosition() == 0) goto L21;
        r19.position = 0;
        return 1;
    L21:
        TsBinarySearchSeeker r9 = this.tsBinarySearchSeeker;
        if (r9 == null) goto L28;
        if (r9.isSeeking() == false) goto L28;
        return this.tsBinarySearchSeeker.handlePendingSeek(r18, r19);
    L28:
        if (fillBufferWithAtLeastOnePacket(r18) == false) goto L30;
        int r1 = findEndOfFirstTsPacketInBuffer();
        int r2 = this.tsPacketBuffer.limit();
        if (r1 <= r2) goto L42;
        return 0;
    L42:
        int r52 = this.tsPacketBuffer.readInt();
        if ((8388608 & r52) == 0) goto L47;
        this.tsPacketBuffer.setPosition(r1);
        return 0;
    L47:
        if ((4194304 & r52) == 0) goto L49;
        int r92 = 1;
    L50:
        int r12 = (2096896 & r52) >> 8;
        if ((r52 & 32) == 0) goto L53;
        boolean r13 = true;
    L55:
        if ((r52 & 16) == 0) goto L57;
        TsPayloadReader r14 = this.tsPayloadReaders.get(r12);
    L58:
        if (r14 != null) goto L62;
        this.tsPacketBuffer.setPosition(r1);
        return 0;
    L62:
        if (this.mode == 2) goto L70;
        int r53 = r52 & 15;
        int r16 = 0;
        int r72 = this.continuityCounters.get(r12, r53 - 1);
        this.continuityCounters.put(r12, r53);
        if (r72 != r53) goto L68;
        this.tsPacketBuffer.setPosition(r1);
        return 0;
    L68:
        if (r53 == ((r72 + 1) & 15)) goto L71;
        r14.seek();
    L71:
        if (r13 == false) goto L77;
        int r54 = this.tsPacketBuffer.readUnsignedByte();
        if ((this.tsPacketBuffer.readUnsignedByte() & 64) == 0) goto L75;
        int r73 = 2;
    L76:
        r92 = r92 | r73;
        this.tsPacketBuffer.skipBytes(r54 - 1);
        goto L77
    L75:
        r73 = r16;
    L77:
        boolean r55 = this.tracksEnded;
        if (shouldConsumePacketPayload(r12) == false) goto L81;
        this.tsPacketBuffer.setLimit(r1);
        r14.consume(this.tsPacketBuffer, r92);
        this.tsPacketBuffer.setLimit(r2);
    L81:
        if (this.mode == 2) goto L88;
        if (r55 == true) goto L88;
        if (this.tracksEnded == false) goto L88;
        if (r3 == (-1)) goto L88;
        this.pendingSeekToStart = true;
    L88:
        this.tsPacketBuffer.setPosition(r1);
        return r16;
    L70:
        r16 = 0;
        goto L71
    L57:
        r14 = null;
        goto L58
    L53:
        r13 = false;
        goto L55
    L49:
        r92 = 0;
    L30:
        if (r7 >= this.tsPayloadReaders.size()) goto L37;
        TsPayloadReader r15 = this.tsPayloadReaders.valueAt(r7);
        if ((r15 instanceof PesReader) == false) goto L36;
        PesReader r17 = (PesReader) r15;
        if (r17.canConsumeSynthesizedEmptyPusi(r5) == false) goto L36;
        r17.consume(new ParsableByteArray(), 1);
    L36:
        r7 = r7 + 1;
        goto L30
    L37:
        return -1;
    L5:
        r5 = false;
        goto L7
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r10, long r12) {
        if (this.mode == 2) goto L5;
        boolean r102 = true;
    L6:
        Assertions.checkState(r102);
        int r103 = this.timestampAdjusters.size();
        int r11 = 0;
    L8:
        if (r11 >= r103) goto L26;
        TimestampAdjuster r4 = this.timestampAdjusters.get(r11);
        if (r4.getTimestampOffsetUs() != (-9223372036854775807L)) goto L12;
        boolean r5 = true;
    L13:
        if (r5 == true) goto L22;
        long r52 = r4.getFirstSampleTimestampUs();
        if (r52 != (-9223372036854775807L)) goto L17;
    L21:
        r5 = false;
        goto L22
    L17:
        if (r52 == 0) goto L21;
        if (r52 == r12) goto L21;
        r5 = true;
    L22:
        if (r5 == false) goto L24;
        r4.reset(r12);
    L24:
        r11 = r11 + 1;
        goto L8
    L12:
        r5 = false;
        goto L13
    L26:
        if (r12 == 0) goto L30;
        TsBinarySearchSeeker r104 = this.tsBinarySearchSeeker;
        if (r104 == null) goto L30;
        r104.setSeekTargetUs(r12);
    L30:
        this.tsPacketBuffer.reset(0);
        this.continuityCounters.clear();
        int r105 = 0;
    L32:
        if (r105 >= this.tsPayloadReaders.size()) goto L34;
        this.tsPayloadReaders.valueAt(r105).seek();
        r105 = r105 + 1;
        goto L32
    L34:
        this.bytesSinceLastSync = 0;
        return;
    L5:
        r102 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r6) {
        byte[] r5 = this.tsPacketBuffer.getData();
        r6.peekFully(r5, 0, 940);
        int r0 = 0;
    L4:
        if (r0 >= 188) goto L14;
        int r2 = 0;
    L7:
        if (r2 >= 5) goto L12;
        if (r5[(r2 * 188) + r0] != 71) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        r0 = r0 + 1;
        goto L4
    L12:
        r6.skipFully(r0);
        return true;
    L14:
        return false;
    }
}
