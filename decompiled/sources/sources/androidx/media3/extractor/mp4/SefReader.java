package androidx.media3.extractor.mp4;

import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class SefReader {
    private static final Splitter ASTERISK_SPLITTER = null;
    private static final Splitter COLON_SPLITTER = null;
    private final List<DataReference> dataReferences;
    private int readerState;
    private int tailLength;

    public static final class DataReference {
        public final int dataType;
        public final int size;
        public final long startOffset;

        public DataReference(int r1, long r2, int r4) {
            this.dataType = r1;
            this.startOffset = r2;
            this.size = r4;
        }
    }

    static {
        COLON_SPLITTER = Splitter.on(':');
        ASTERISK_SPLITTER = Splitter.on('*');
    }

    public SefReader() {
        this.dataReferences = new ArrayList();
        this.readerState = 0;
    }

    private void checkForSefData(ExtractorInput r5, PositionHolder r6) {
        ParsableByteArray r0 = new ParsableByteArray(8);
        r5.readFully(r0.getData(), 0, 8);
        this.tailLength = r0.readLittleEndianInt() + 8;
        if (r0.readInt() == 1397048916) goto L6;
        r6.position = 0;
        return;
    L6:
        r6.position = r5.getPosition() - (this.tailLength - 12);
        this.readerState = 2;
    }

    private static int nameToDataType(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1711564334: goto L22;
            case -1332107749: goto L18;
            case -1251387154: goto L14;
            case -830665521: goto L10;
            case 1760745220: goto L6;
            default: goto L25;
        };
    L25:
        switch(r1) {
            case 0: goto L36;
            case 1: goto L34;
            case 2: goto L32;
            case 3: goto L30;
            case 4: goto L28;
            default: goto L27;
        };
    L28:
        return 2817;
    L30:
        return 2820;
    L32:
        return 2816;
    L34:
        return 2819;
    L36:
        return 2192;
    L27:
        throw ParserException.createForMalformedContainer("Invalid SEF name", null);
    L6:
        if (r2.equals("Super_SlowMotion_BGM") == false) goto L25;
        r1 = 4;
        goto L25
    L10:
        if (r2.equals("Super_SlowMotion_Deflickering_On") == false) goto L25;
        r1 = 3;
        goto L25
    L14:
        if (r2.equals("Super_SlowMotion_Data") == false) goto L25;
        r1 = 2;
        goto L25
    L18:
        if (r2.equals("Super_SlowMotion_Edit_Data") == false) goto L25;
        r1 = 1;
        goto L25
    L22:
        if (r2.equals("SlowMotion_Data") == false) goto L25;
        r1 = 0;
        goto L25
    }

    private void readSdrs(ExtractorInput r12, PositionHolder r13) {
        long r0 = r12.getLength();
        int r2 = this.tailLength - 20;
        ParsableByteArray r3 = new ParsableByteArray(r2);
        r12.readFully(r3.getData(), 0, r2);
        int r122 = 0;
    L4:
        if (r122 >= (r2 / 12)) goto L19;
        r3.skipBytes(2);
        short r4 = r3.readLittleEndianShort();
        if (r4 != 2192) goto L8;
    L16:
        this.dataReferences.add(new DataReference(r4, (r0 - this.tailLength) - r3.readLittleEndianInt(), r3.readLittleEndianInt()));
    L17:
        r122 = r122 + 1;
        goto L4
    L8:
        if (r4 == 2816) goto L16;
        if (r4 == 2817) goto L16;
        if (r4 == 2819) goto L16;
        if (r4 == 2820) goto L16;
        r3.skipBytes(8);
        goto L17
    L19:
        if (this.dataReferences.isEmpty() == false) goto L22;
        r13.position = 0;
        return;
    L22:
        this.readerState = 3;
        r13.position = this.dataReferences.get(0).startOffset;
    }

    private void readSefData(ExtractorInput r9, List<Metadata.Entry> r10) {
        long r0 = r9.getPosition();
        int r2 = (int) ((r9.getLength() - r9.getPosition()) - this.tailLength);
        ParsableByteArray r3 = new ParsableByteArray(r2);
        int r5 = 0;
        r9.readFully(r3.getData(), 0, r2);
    L4:
        if (r5 >= this.dataReferences.size()) goto L20;
        DataReference r92 = this.dataReferences.get(r5);
        r3.setPosition((int) (r92.startOffset - r0));
        r3.skipBytes(4);
        int r22 = r3.readLittleEndianInt();
        int r4 = nameToDataType(r3.readString(r22));
        int r93 = r92.size - (r22 + 8);
        if (r4 != 2192) goto L8;
        r10.add(readSlowMotionData(r3, r93));
    L19:
        r5 = r5 + 1;
        goto L4
    L8:
        if (r4 == 2816) goto L19;
        if (r4 == 2817) goto L19;
        if (r4 == 2819) goto L19;
        if (r4 == 2820) goto L19;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    }

    private static SlowMotionData readSlowMotionData(ParsableByteArray r12, int r13) {
        ArrayList r0 = new ArrayList();
        List<String> r122 = ASTERISK_SPLITTER.splitToList(r12.readString(r13));
        int r1 = 0;
    L4:
        if (r1 >= r122.size()) goto L15;
        List<String> r2 = COLON_SPLITTER.splitToList(r122.get(r1));
        if (r2.size() != 3) goto L13;
        r0.add(new SlowMotionData.Segment(Long.parseLong(r2.get(0)), Long.parseLong(r2.get(1)), 1 << (Integer.parseInt(r2.get(2)) - 1)));     // Catch: NumberFormatException -> L9
        r1 = r1 + 1;
    L9:
        e = move-exception;
        throw ParserException.createForMalformedContainer(null, e);
    L13:
        throw ParserException.createForMalformedContainer(null, null);
    L15:
        return new SlowMotionData(r0);
    }

    public int read(ExtractorInput r9, PositionHolder r10, List<Metadata.Entry> r11) {
        int r0 = this.readerState;
        long r1 = 0;
        if (r0 == 0) goto L14;
        if (r0 != 1) goto L6;
        checkForSefData(r9, r10);
    L21:
        return 1;
    L6:
        if (r0 != 2) goto L8;
        readSdrs(r9, r10);
        goto L21
    L8:
        if (r0 != 3) goto L10;
        readSefData(r9, r11);
        r10.position = 0;
        goto L21
    L10:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L14:
        long r4 = r9.getLength();
        if (r4 != (-1)) goto L17;
    L20:
        r10.position = r1;
        this.readerState = 1;
        goto L21
    L17:
        if (r4 < 8) goto L20;
        r1 = r4 - 8;
        goto L20
    }

    public void reset() {
        this.dataReferences.clear();
        this.readerState = 0;
    }
}
