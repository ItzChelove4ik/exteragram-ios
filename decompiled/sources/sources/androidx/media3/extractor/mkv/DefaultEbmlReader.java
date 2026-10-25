package androidx.media3.extractor.mkv;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.ExtractorInput;
import java.util.ArrayDeque;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
final class DefaultEbmlReader implements EbmlReader {
    private long elementContentSize;
    private int elementId;
    private int elementState;
    private final ArrayDeque<MasterElement> masterElementsStack;
    private EbmlProcessor processor;
    private final byte[] scratch;
    private final VarintReader varintReader;

    /* renamed from: androidx.media3.extractor.mkv.DefaultEbmlReader$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class MasterElement {
        private final long elementEndPosition;
        private final int elementId;

        private MasterElement(int r1, long r2) {
            this.elementId = r1;
            this.elementEndPosition = r2;
        }

        public /* synthetic */ MasterElement(int r1, long r2, AnonymousClass1 r4) {
            this(r1, r2);
        }

        public static /* synthetic */ long access$000(MasterElement r2) {
            return r2.elementEndPosition;
        }

        public static /* synthetic */ int access$100(MasterElement r0) {
            return r0.elementId;
        }
    }

    public DefaultEbmlReader() {
        this.scratch = new byte[8];
        this.masterElementsStack = new ArrayDeque();
        this.varintReader = new VarintReader();
    }

    @RequiresNonNull({"processor"})
    private long maybeResyncToNextLevel1Element(ExtractorInput r5) {
        r5.resetPeekPosition();
    L3:
        r5.peekFully(this.scratch, 0, 4);
        int r0 = VarintReader.parseUnsignedVarintLength(this.scratch[0]);
        if (r0 == (-1)) goto L10;
        if (r0 > 4) goto L10;
        int r1 = (int) VarintReader.assembleVarint(this.scratch, r0, false);
        if (this.processor.isLevel1Element(r1) == false) goto L10;
        r5.skipFully(r0);
        return r1;
    L10:
        r5.skipFully(1);
        goto L3
    }

    private double readFloat(ExtractorInput r2, int r3) {
        long r1 = readInteger(r2, r3);
        if (r3 != 4) goto L7;
        return Float.intBitsToFloat((int) r1);
    L7:
        return Double.longBitsToDouble(r1);
    }

    private long readInteger(ExtractorInput r7, int r8) {
        int r1 = 0;
        r7.readFully(this.scratch, 0, r8);
        long r2 = 0;
    L3:
        if (r1 >= r8) goto L5;
        r2 = (r2 << 8) | (this.scratch[r1] & UByte.MAX_VALUE);
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    private static String readString(ExtractorInput r2, int r3) {
        if (r3 != 0) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        byte[] r0 = new byte[r3];
        r2.readFully(r0, 0, r3);
    L6:
        if (r3 <= 0) goto L11;
        if (r0[r3 - 1] != 0) goto L11;
        r3 = r3 - 1;
    L11:
        return new String(r0, 0, r3);
    }

    @Override // androidx.media3.extractor.mkv.EbmlReader
    public void init(EbmlProcessor r1) {
        this.processor = r1;
    }

    @Override // androidx.media3.extractor.mkv.EbmlReader
    public boolean read(ExtractorInput r13) {
        Assertions.checkStateNotNull(this.processor);
    L3:
        MasterElement r0 = this.masterElementsStack.peek();
        if (r0 == null) goto L10;
        if (r13.getPosition() < MasterElement.access$000(r0)) goto L10;
        this.processor.endMasterElement(MasterElement.access$100(this.masterElementsStack.pop()));
        return true;
    L10:
        if (this.elementState != 0) goto L19;
        long r4 = this.varintReader.readUnsignedVarint(r13, true, false, 4);
        if (r4 != (-2)) goto L15;
        r4 = maybeResyncToNextLevel1Element(r13);
    L15:
        if (r4 == (-1)) goto L16;
        this.elementId = (int) r4;
        this.elementState = 1;
        goto L19
    L16:
        return false;
    L19:
        if (this.elementState != 1) goto L21;
        this.elementContentSize = this.varintReader.readUnsignedVarint(r13, false, true, 8);
        this.elementState = 2;
    L21:
        int r02 = this.processor.getElementType(this.elementId);
        if (r02 != 0) goto L24;
        r13.skipFully((int) this.elementContentSize);
        this.elementState = 0;
        goto L3
    L24:
        if (r02 != 1) goto L26;
        long r6 = r13.getPosition();
        this.masterElementsStack.push(new MasterElement(this.elementId, this.elementContentSize + r6, null));
        this.processor.startMasterElement(this.elementId, r6, this.elementContentSize);
        this.elementState = 0;
        return true;
    L26:
        if (r02 != 2) goto L28;
        long r8 = this.elementContentSize;
        if (r8 > 8) goto L56;
        this.processor.integerElement(this.elementId, readInteger(r13, (int) r8));
        this.elementState = 0;
        return true;
    L56:
        throw ParserException.createForMalformedContainer("Invalid integer size: " + this.elementContentSize, null);
    L28:
        if (r02 == 3) goto L45;
        if (r02 != 4) goto L31;
        this.processor.binaryElement(this.elementId, (int) this.elementContentSize, r13);
        this.elementState = 0;
        return true;
    L31:
        if (r02 != 5) goto L42;
        long r82 = this.elementContentSize;
        if (r82 != 4) goto L35;
    L39:
        this.processor.floatElement(this.elementId, readFloat(r13, (int) r82));
        this.elementState = 0;
        return true;
    L35:
        if (r82 == 8) goto L39;
        throw ParserException.createForMalformedContainer("Invalid float size: " + this.elementContentSize, null);
    L42:
        throw ParserException.createForMalformedContainer("Invalid element type " + r02, null);
    L45:
        long r62 = this.elementContentSize;
        if (r62 > 2147483647L) goto L50;
        this.processor.stringElement(this.elementId, readString(r13, (int) r62));
        this.elementState = 0;
        return true;
    L50:
        throw ParserException.createForMalformedContainer("String element size: " + this.elementContentSize, null);
    }

    @Override // androidx.media3.extractor.mkv.EbmlReader
    public void reset() {
        this.elementState = 0;
        this.masterElementsStack.clear();
        this.varintReader.reset();
    }
}
