package androidx.media3.common.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedInts;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.text.Regex$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class ParsableByteArray {
    private static final char[] CR_AND_LF = null;
    private static final char[] LF = null;
    private static final ImmutableSet<Charset> SUPPORTED_CHARSETS_FOR_READLINE = null;
    private byte[] data;
    private int limit;
    private int position;

    static {
        CR_AND_LF = new char[]{'\r', '\n'};
        LF = new char[]{'\n'};
        SUPPORTED_CHARSETS_FOR_READLINE = ImmutableSet.of(StandardCharsets.US_ASCII, StandardCharsets.UTF_8, StandardCharsets.UTF_16, StandardCharsets.UTF_16BE, StandardCharsets.UTF_16LE);
    }

    public ParsableByteArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    public ParsableByteArray(int r2) {
        this.data = new byte[r2];
        this.limit = r2;
    }

    public ParsableByteArray(byte[] r1) {
        this.data = r1;
        this.limit = r1.length;
    }

    public ParsableByteArray(byte[] r1, int r2) {
        this.data = r1;
        this.limit = r2;
    }

    private static int decodeUtf8CodeUnit(int r2, int r3, int r4, int r5) {
        byte r42 = (byte) r4;
        return Ints.fromBytes((byte) 0, UnsignedBytes.checkedCast(((r2 & 7) << 2) | ((r3 & 48) >> 4)), UnsignedBytes.checkedCast(((((byte) r3) & 15) << 4) | ((r42 & 60) >> 2)), UnsignedBytes.checkedCast(((r42 & 3) << 6) | (((byte) r5) & 63)));
    }

    private int findNextLineTerminator(Charset r5) {
        if (r5.equals(StandardCharsets.UTF_8) == false) goto L5;
    L17:
        int r0 = 1;
    L18:
        int r1 = this.position;
    L19:
        int r2 = this.limit;
        if (r1 >= (r2 - (r0 - 1))) goto L45;
        if (r5.equals(StandardCharsets.UTF_8) == true) goto L26;
        if (r5.equals(StandardCharsets.US_ASCII) == true) goto L26;
    L29:
        if (r5.equals(StandardCharsets.UTF_16) == false) goto L31;
    L32:
        byte[] r22 = this.data;
        if (r22[r1] == 0) goto L35;
    L38:
        if (r5.equals(StandardCharsets.UTF_16LE) == false) goto L44;
        byte[] r23 = this.data;
        if (r23[r1 + 1] != 0) goto L44;
        if (Util.isLinebreak(r23[r1]) == false) goto L44;
    L43:
        return r1;
    L44:
        r1 = r1 + r0;
        goto L19
    L35:
        if (Util.isLinebreak(r22[r1 + 1]) == false) goto L38;
    L31:
        if (r5.equals(StandardCharsets.UTF_16BE) == false) goto L38;
    L26:
        if (Util.isLinebreak(this.data[r1]) == false) goto L29;
    L45:
        return r2;
    L5:
        if (r5.equals(StandardCharsets.US_ASCII) == true) goto L17;
        if (r5.equals(StandardCharsets.UTF_16) == false) goto L10;
    L16:
        r0 = 2;
        goto L18
    L10:
        if (r5.equals(StandardCharsets.UTF_16LE) == true) goto L16;
        if (r5.equals(StandardCharsets.UTF_16BE) == true) goto L16;
        Native$$ExternalSyntheticBUOutline5.m("Unsupported charset: ", r5);
        return 0;
    }

    private static int getSmallestCodeUnitSize(Charset r3) {
        Assertions.checkArgument(SUPPORTED_CHARSETS_FOR_READLINE.contains(r3), "Unsupported charset: " + r3);
        if (r3.equals(StandardCharsets.UTF_8) == false) goto L5;
        return 1;
    L5:
        if (r3.equals(StandardCharsets.US_ASCII) == true) goto L11;
        return 2;
    L11:
        return 1;
    }

    private static boolean isUtf8ContinuationByte(byte r1) {
        if ((r1 & 192) != 128) goto L6;
        return true;
    L6:
        return false;
    }

    private char peekChar(ByteOrder r3, int r4) {
        ByteOrder r0 = ByteOrder.BIG_ENDIAN;
        byte[] r1 = this.data;
        if (r3 != r0) goto L6;
        int r2 = this.position;
        return Chars.fromBytes(r1[r2 + r4], r1[(r2 + r4) + 1]);
    L6:
        int r22 = this.position;
        return Chars.fromBytes(r1[(r22 + r4) + 1], r1[r22 + r4]);
    }

    private int peekCodePointAndSize(Charset r6) {
        Assertions.checkArgument(SUPPORTED_CHARSETS_FOR_READLINE.contains(r6), "Unsupported charset: " + r6);
        if (bytesLeft() < getSmallestCodeUnitSize(r6)) goto L36;
        byte r1 = 1;
        if (r6.equals(StandardCharsets.US_ASCII) == false) goto L11;
        byte r5 = this.data[this.position];
        if ((r5 & ByteCompanionObject.MIN_VALUE) == 0) goto L9;
        return 0;
    L9:
        int r52 = UnsignedBytes.toInt(r5);
    L35:
        return (r52 << 8) | r1;
    L11:
        if (r6.equals(StandardCharsets.UTF_8) == false) goto L25;
        byte r62 = peekUtf8CodeUnitSize();
        if (r62 == 1) goto L23;
        if (r62 != 2) goto L16;
        byte[] r0 = this.data;
        int r53 = this.position;
        r52 = decodeUtf8CodeUnit(0, 0, r0[r53], r0[r53 + 1]);
    L20:
        r1 = r62;
        goto L35
    L16:
        if (r62 == 3) goto L21;
        if (r62 == 4) goto L19;
        return 0;
    L19:
        byte[] r12 = this.data;
        int r54 = this.position;
        r52 = decodeUtf8CodeUnit(r12[r54], r12[r54 + 1], r12[r54 + 2], r12[r54 + 3]);
        goto L20
    L21:
        byte[] r02 = this.data;
        int r55 = this.position;
        r52 = decodeUtf8CodeUnit(0, r02[r55] & 15, r02[r55 + 1], r02[r55 + 2]);
        goto L20
    L23:
        r52 = UnsignedBytes.toInt(this.data[this.position]);
        goto L20
    L25:
        if (r6.equals(StandardCharsets.UTF_16LE) == false) goto L27;
        ByteOrder r63 = ByteOrder.LITTLE_ENDIAN;
    L28:
        char r03 = peekChar(r63, 0);
        if (Character.isHighSurrogate(r03) == true) goto L31;
    L33:
        r52 = r03;
        r1 = 2;
        goto L35
    L31:
        if (bytesLeft() < 4) goto L33;
        r52 = Character.toCodePoint(r03, peekChar(r63, 2));
        r1 = 4;
        goto L35
    L27:
        r63 = ByteOrder.BIG_ENDIAN;
        goto L28
    L36:
        Regex$$ExternalSyntheticBUOutline0.m("position=", this.position, ", limit=", this.limit);
        return 0;
    }

    private byte peekUtf8CodeUnitSize() {
        byte r0 = this.data[this.position];
        if ((r0 & ByteCompanionObject.MIN_VALUE) != 0) goto L6;
        return 1;
    L6:
        if ((r0 & 224) != 192) goto L13;
        if (bytesLeft() < 2) goto L13;
        if (isUtf8ContinuationByte(this.data[this.position + 1]) == false) goto L13;
        return 2;
    L13:
        if ((this.data[this.position] & 240) != 224) goto L22;
        if (bytesLeft() < 3) goto L22;
        if (isUtf8ContinuationByte(this.data[this.position + 1]) == false) goto L22;
        if (isUtf8ContinuationByte(this.data[this.position + 2]) == false) goto L22;
        return 3;
    L22:
        if ((this.data[this.position] & 248) == 240) goto L24;
        return 0;
    L24:
        if (bytesLeft() >= 4) goto L26;
        return 0;
    L26:
        if (isUtf8ContinuationByte(this.data[this.position + 1]) == true) goto L28;
        return 0;
    L28:
        if (isUtf8ContinuationByte(this.data[this.position + 2]) == true) goto L30;
        return 0;
    L30:
        if (isUtf8ContinuationByte(this.data[this.position + 3]) == false) goto L37;
        return 4;
    L37:
        return 0;
    }

    private char readCharacterIfInList(Charset r4, char[] r5) {
        if (bytesLeft() >= getSmallestCodeUnitSize(r4)) goto L6;
        return 0;
    L6:
        if (peekCodePointAndSize(r4) != 0) goto L8;
        return 0;
    L8:
        int r0 = UnsignedInts.checkedCast(r4 >>> 8);
        if (Character.isSupplementaryCodePoint(r0) == false) goto L11;
        return 0;
    L11:
        char r02 = Chars.checkedCast(r0);
        if (Chars.contains(r5, r02) == false) goto L15;
        this.position += Ints.checkedCast(r4 & 255);
        return r02;
    L15:
        return 0;
    }

    private void skipLineTerminator(Charset r3) {
        if (readCharacterIfInList(r3, CR_AND_LF) != '\r') goto L6;
        readCharacterIfInList(r3, LF);
        return;
    }

    public int bytesLeft() {
        return Math.max(this.limit - this.position, 0);
    }

    public int capacity() {
        return this.data.length;
    }

    public void ensureCapacity(int r2) {
        if (r2 <= capacity()) goto L6;
        this.data = Arrays.copyOf(this.data, r2);
        return;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getPosition() {
        return this.position;
    }

    public int limit() {
        return this.limit;
    }

    public int peekCodePoint(Charset r1) {
        if (peekCodePointAndSize(r1) != 0) goto L5;
        return 1114112;
    L5:
        return Ints.checkedCast(r0 >>> 8);
    }

    public int peekUnsignedByte() {
        return this.data[this.position] & UByte.MAX_VALUE;
    }

    public void readBytes(ParsableBitArray r3, int r4) {
        readBytes(r3.data, 0, r4);
        r3.setPosition(0);
    }

    public void readBytes(byte[] r3, int r4, int r5) {
        System.arraycopy(this.data, this.position, r3, r4, r5);
        this.position += r5;
    }

    public String readDelimiterTerminatedString(char r4) {
        if (bytesLeft() != 0) goto L6;
        return null;
    L6:
        int r0 = this.position;
    L8:
        if (r0 >= this.limit) goto L12;
        if (this.data[r0] == r4) goto L12;
        r0 = r0 + 1;
    L12:
        byte[] r42 = this.data;
        int r1 = this.position;
        String r43 = Util.fromUtf8Bytes(r42, r1, r0 - r1);
        this.position = r0;
        if (r0 >= this.limit) goto L15;
        this.position = r0 + 1;
    L15:
        return r43;
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public int readInt() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = (r0[r1] & UByte.MAX_VALUE) << 24;
        int r4 = r1 + 2;
        this.position = r4;
        int r22 = ((r0[r2] & UByte.MAX_VALUE) << 16) | r3;
        int r32 = r1 + 3;
        this.position = r32;
        int r23 = r22 | ((r0[r4] & UByte.MAX_VALUE) << 8);
        this.position = r1 + 4;
        return (r0[r32] & UByte.MAX_VALUE) | r23;
    }

    public int readInt24() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = ((r0[r1] & UByte.MAX_VALUE) << 24) >> 8;
        int r4 = r1 + 2;
        this.position = r4;
        int r22 = ((r0[r2] & UByte.MAX_VALUE) << 8) | r3;
        this.position = r1 + 3;
        return (r0[r4] & UByte.MAX_VALUE) | r22;
    }

    public String readLine() {
        return readLine(StandardCharsets.UTF_8);
    }

    public String readLine(Charset r4) {
        Assertions.checkArgument(SUPPORTED_CHARSETS_FOR_READLINE.contains(r4), "Unsupported charset: " + r4);
        if (bytesLeft() != 0) goto L7;
        return null;
    L7:
        if (r4.equals(StandardCharsets.US_ASCII) == true) goto L9;
        readUtfCharsetFromBom();
    L9:
        String r0 = readString(findNextLineTerminator(r4) - this.position, r4);
        if (this.position != this.limit) goto L12;
        return r0;
    L12:
        skipLineTerminator(r4);
        return r0;
    }

    public int readLittleEndianInt() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r0[r1] & UByte.MAX_VALUE;
        int r4 = r1 + 2;
        this.position = r4;
        int r22 = ((r0[r2] & UByte.MAX_VALUE) << 8) | r3;
        int r32 = r1 + 3;
        this.position = r32;
        int r23 = r22 | ((r0[r4] & UByte.MAX_VALUE) << 16);
        this.position = r1 + 4;
        return ((r0[r32] & UByte.MAX_VALUE) << 24) | r23;
    }

    public long readLittleEndianLong() {
        byte[] r0 = this.data;
        int r1 = this.position;
        this.position = r1 + 1;
        this.position = r1 + 2;
        this.position = r1 + 3;
        long r3 = ((r0[r1] & 255) | ((r0[r2] & 255) << 8)) | ((r0[r7] & 255) << 16);
        this.position = r1 + 4;
        long r32 = r3 | ((r0[r8] & 255) << 24);
        this.position = r1 + 5;
        long r33 = r32 | ((r0[r7] & 255) << 32);
        this.position = r1 + 6;
        long r34 = r33 | ((r0[r8] & 255) << 40);
        this.position = r1 + 7;
        long r35 = r34 | ((r0[r7] & 255) << 48);
        this.position = r1 + 8;
        return ((r0[r8] & 255) << 56) | r35;
    }

    public short readLittleEndianShort() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r0[r1] & UByte.MAX_VALUE;
        this.position = r1 + 2;
        return (short) (((r0[r2] & UByte.MAX_VALUE) << 8) | r3);
    }

    public long readLittleEndianUnsignedInt() {
        byte[] r0 = this.data;
        int r1 = this.position;
        this.position = r1 + 1;
        this.position = r1 + 2;
        this.position = r1 + 3;
        long r2 = ((r0[r1] & 255) | ((r0[r2] & 255) << 8)) | ((r0[r7] & 255) << 16);
        this.position = r1 + 4;
        return ((r0[r4] & 255) << 24) | r2;
    }

    public int readLittleEndianUnsignedIntToInt() {
        int r1 = readLittleEndianInt();
        if (r1 < 0) goto L5;
        return r1;
    L5:
        JsonArray$$ExternalSyntheticBUOutline0.m("Top bit not zero: ", r1);
        return 0;
    }

    public int readLittleEndianUnsignedShort() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r0[r1] & UByte.MAX_VALUE;
        this.position = r1 + 2;
        return ((r0[r2] & UByte.MAX_VALUE) << 8) | r3;
    }

    public long readLong() {
        byte[] r0 = this.data;
        int r1 = this.position;
        this.position = r1 + 1;
        this.position = r1 + 2;
        this.position = r1 + 3;
        long r2 = (((r0[r1] & 255) << 56) | ((r0[r2] & 255) << 48)) | ((r0[r7] & 255) << 40);
        this.position = r1 + 4;
        long r22 = r2 | ((r0[r4] & 255) << 32);
        this.position = r1 + 5;
        long r23 = r22 | ((r0[r7] & 255) << 24);
        this.position = r1 + 6;
        long r24 = r23 | ((r0[r4] & 255) << 16);
        this.position = r1 + 7;
        long r25 = r24 | ((r0[r7] & 255) << 8);
        this.position = r1 + 8;
        return (r0[r4] & 255) | r25;
    }

    public String readNullTerminatedString() {
        return readDelimiterTerminatedString(0);
    }

    public String readNullTerminatedString(int r4) {
        if (r4 != 0) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        int r0 = this.position;
        int r1 = (r0 + r4) - 1;
        if (r1 < this.limit) goto L8;
    L10:
        int r12 = r4;
    L11:
        String r02 = Util.fromUtf8Bytes(this.data, r0, r12);
        this.position += r4;
        return r02;
    L8:
        if (this.data[r1] != 0) goto L10;
        r12 = r4 - 1;
        goto L11
    }

    public short readShort() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = (r0[r1] & UByte.MAX_VALUE) << 8;
        this.position = r1 + 2;
        return (short) ((r0[r2] & UByte.MAX_VALUE) | r3);
    }

    public String readString(int r2) {
        return readString(r2, StandardCharsets.UTF_8);
    }

    public String readString(int r4, Charset r5) {
        String r0 = new String(this.data, this.position, r4, r5);
        this.position += r4;
        return r0;
    }

    public int readSynchSafeInt() {
        int r0 = readUnsignedByte();
        int r1 = readUnsignedByte();
        int r2 = readUnsignedByte();
        return readUnsignedByte() | (((r0 << 21) | (r1 << 14)) | (r2 << 7));
    }

    public int readUnsignedByte() {
        byte[] r0 = this.data;
        int r1 = this.position;
        this.position = r1 + 1;
        return r0[r1] & UByte.MAX_VALUE;
    }

    public int readUnsignedFixedPoint1616() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = (r0[r1] & UByte.MAX_VALUE) << 8;
        this.position = r1 + 2;
        int r02 = (r0[r2] & UByte.MAX_VALUE) | r3;
        this.position = r1 + 4;
        return r02;
    }

    public long readUnsignedInt() {
        byte[] r0 = this.data;
        int r1 = this.position;
        this.position = r1 + 1;
        this.position = r1 + 2;
        this.position = r1 + 3;
        long r2 = (((r0[r1] & 255) << 24) | ((r0[r2] & 255) << 16)) | ((r0[r7] & 255) << 8);
        this.position = r1 + 4;
        return (r0[r4] & 255) | r2;
    }

    public int readUnsignedInt24() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = (r0[r1] & UByte.MAX_VALUE) << 16;
        int r4 = r1 + 2;
        this.position = r4;
        int r22 = ((r0[r2] & UByte.MAX_VALUE) << 8) | r3;
        this.position = r1 + 3;
        return (r0[r4] & UByte.MAX_VALUE) | r22;
    }

    public int readUnsignedIntToInt() {
        int r1 = readInt();
        if (r1 < 0) goto L5;
        return r1;
    L5:
        JsonArray$$ExternalSyntheticBUOutline0.m("Top bit not zero: ", r1);
        return 0;
    }

    public int readUnsignedLeb128ToInt() {
        return Ints.checkedCast(readUnsignedLeb128ToLong());
    }

    public long readUnsignedLeb128ToLong() {
        int r2 = 0;
        long r3 = 0;
    L4:
        if (r2 >= 9) goto L13;
        if (this.position == this.limit) goto L11;
        long r5 = readUnsignedByte();
        r3 = r3 | ((127 & r5) << (r2 * 7));
        if ((r5 & 128) == 0) goto L9;
        r2 = r2 + 1;
        goto L4
    L9:
        return r3;
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("Attempting to read a byte over the limit.");
        return 0;
    L13:
        return r3;
    }

    public long readUnsignedLongToLong() {
        long r0 = readLong();
        if (r0 < 0) goto L6;
        return r0;
    L6:
        throw new IllegalStateException("Top bit not zero: " + r0);
    }

    public int readUnsignedShort() {
        byte[] r0 = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = (r0[r1] & UByte.MAX_VALUE) << 8;
        this.position = r1 + 2;
        return (r0[r2] & UByte.MAX_VALUE) | r3;
    }

    public long readUtf8EncodedLong() {
        long r0 = this.data[this.position];
        int r3 = 7;
    L3:
        int r5 = 1;
        if (r3 < 0) goto L12;
        if (((1 << r3) & r0) == 0) goto L7;
        r3 = r3 - 1;
        goto L3
    L7:
        if (r3 >= 6) goto L9;
        r0 = r0 & (r6 - 1);
        int r2 = 7 - r3;
    L13:
        if (r2 == 0) goto L23;
    L14:
        if (r5 >= r2) goto L20;
        if ((this.data[this.position + r5] & 192) != 128) goto L19;
        r0 = (r0 << 6) | (r3 & 63);
        r5 = r5 + 1;
        goto L14
    L19:
        throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + r0);
    L20:
        this.position += r2;
        return r0;
    L23:
        throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + r0);
    L9:
        if (r3 != 7) goto L12;
        r2 = 1;
    L12:
        r2 = 0;
        goto L13
    }

    public Charset readUtfCharsetFromBom() {
        if (bytesLeft() < 3) goto L13;
        byte[] r0 = this.data;
        int r2 = this.position;
        if (r0[r2] != (-17)) goto L13;
        if (r0[r2 + 1] != (-69)) goto L13;
        if (r0[r2 + 2] != (-65)) goto L13;
        this.position = r2 + 3;
        return StandardCharsets.UTF_8;
    L13:
        if (bytesLeft() < 2) goto L25;
        byte[] r02 = this.data;
        int r22 = this.position;
        byte r3 = r02[r22];
        if (r3 == (-2)) goto L17;
    L20:
        if (r3 == (-1)) goto L22;
        return null;
    L22:
        if (r02[r22 + 1] != (-2)) goto L28;
        this.position = r22 + 2;
        return StandardCharsets.UTF_16LE;
    L28:
        return null;
    L17:
        if (r02[r22 + 1] != (-1)) goto L20;
        this.position = r22 + 2;
        return StandardCharsets.UTF_16BE;
    L25:
        return null;
    }

    public void reset(int r2) {
        if (capacity() >= r2) goto L5;
        byte[] r0 = new byte[r2];
    L6:
        reset(r0, r2);
        return;
    L5:
        r0 = this.data;
        goto L6
    }

    public void reset(byte[] r2) {
        reset(r2, r2.length);
    }

    public void reset(byte[] r1, int r2) {
        this.data = r1;
        this.limit = r2;
        this.position = 0;
    }

    public void setLimit(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        boolean r0 = false;
    L7:
        Assertions.checkArgument(r0);
        this.limit = r2;
        return;
    L4:
        if (r2 > this.data.length) goto L6;
        r0 = true;
        goto L7
    }

    public void setPosition(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        boolean r0 = false;
    L7:
        Assertions.checkArgument(r0);
        this.position = r2;
        return;
    L4:
        if (r2 > this.limit) goto L6;
        r0 = true;
        goto L7
    }

    public void skipBytes(int r2) {
        setPosition(this.position + r2);
    }

    public void skipLeb128() {
    L3:
        if ((readUnsignedByte() & 128) != 0) goto L3;
    }
}
