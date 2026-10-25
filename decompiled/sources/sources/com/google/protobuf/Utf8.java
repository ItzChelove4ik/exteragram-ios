package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class Utf8 {
    private static final Processor processor = null;

    public static class DecodeUtil {
        public static /* synthetic */ void access$1000(byte r0, byte r1, byte r2, byte r3, char[] r4, int r5) {
            handleFourBytes(r0, r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ boolean access$400(byte r0) {
            return isOneByte(r0);
        }

        public static /* synthetic */ void access$500(byte r0, char[] r1, int r2) {
            handleOneByte(r0, r1, r2);
        }

        public static /* synthetic */ boolean access$600(byte r0) {
            return isTwoBytes(r0);
        }

        public static /* synthetic */ void access$700(byte r0, byte r1, char[] r2, int r3) {
            handleTwoBytes(r0, r1, r2, r3);
        }

        public static /* synthetic */ boolean access$800(byte r0) {
            return isThreeBytes(r0);
        }

        public static /* synthetic */ void access$900(byte r0, byte r1, byte r2, char[] r3, int r4) {
            handleThreeBytes(r0, r1, r2, r3, r4);
        }

        private static void handleFourBytes(byte r2, byte r3, byte r4, byte r5, char[] r6, int r7) {
            if (isNotTrailingByte(r3) == true) goto L13;
            if ((((r2 << 28) + (r3 + 112)) >> 30) != 0) goto L13;
            if (isNotTrailingByte(r4) == true) goto L13;
            if (isNotTrailingByte(r5) == true) goto L13;
            int r22 = ((((r2 & 7) << 18) | (trailingByteValue(r3) << 12)) | (trailingByteValue(r4) << 6)) | trailingByteValue(r5);
            r6[r7] = highSurrogate(r22);
            r6[r7 + 1] = lowSurrogate(r22);
            return;
        L13:
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        private static void handleOneByte(byte r0, char[] r1, int r2) {
            r1[r2] = (char) r0;
        }

        private static void handleThreeBytes(byte r2, byte r3, byte r4, char[] r5, int r6) {
            if (isNotTrailingByte(r3) == true) goto L15;
            if (r2 != (-32)) goto L8;
            if (r3 < (-96)) goto L15;
        L8:
            if (r2 != (-19)) goto L11;
            if (r3 >= (-96)) goto L15;
        L11:
            if (isNotTrailingByte(r4) == true) goto L15;
            r5[r6] = (char) ((((r2 & 15) << 12) | (trailingByteValue(r3) << 6)) | trailingByteValue(r4));
            return;
        L15:
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        private static void handleTwoBytes(byte r1, byte r2, char[] r3, int r4) {
            if (r1 < (-62)) goto L9;
            if (isNotTrailingByte(r2) == true) goto L9;
            r3[r4] = (char) (((r1 & 31) << 6) | trailingByteValue(r2));
            return;
        L9:
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        private static char highSurrogate(int r1) {
            return (char) ((r1 >>> 10) + 55232);
        }

        private static boolean isNotTrailingByte(byte r1) {
            if (r1 <= (-65)) goto L6;
            return true;
        L6:
            return false;
        }

        private static boolean isOneByte(byte r0) {
            if (r0 < 0) goto L5;
            return true;
        L5:
            return false;
        }

        private static boolean isThreeBytes(byte r1) {
            if (r1 >= (-16)) goto L6;
            return true;
        L6:
            return false;
        }

        private static boolean isTwoBytes(byte r1) {
            if (r1 >= (-32)) goto L6;
            return true;
        L6:
            return false;
        }

        private static char lowSurrogate(int r1) {
            return (char) ((r1 & 1023) + 56320);
        }

        private static int trailingByteValue(byte r0) {
            return r0 & 63;
        }
    }

    public static abstract class Processor {
        public Processor() {
        }

        private static int partialIsValidUtf8(ByteBuffer r7, int r8, int r9) {
            int r82 = r8 + Utf8.access$200(r7, r8, r9);
        L3:
            if (r82 >= r9) goto L4;
            int r0 = r82 + 1;
            byte r1 = r7.get(r82);
            if (r1 < 0) goto L9;
            r82 = r0;
            goto L3
        L9:
            if (r1 < (-32)) goto L10;
            if (r1 < (-16)) goto L22;
            if (r0 >= (r9 - 2)) goto L41;
            int r2 = r82 + 2;
            byte r02 = r7.get(r0);
            if (r02 > (-65)) goto L50;
            if ((((r1 << 28) + (r02 + 112)) >> 30) != 0) goto L50;
            int r03 = r82 + 3;
            if (r7.get(r2) > (-65)) goto L50;
            r82 = r82 + 4;
            if (r7.get(r03) <= (-65)) goto L3;
        L50:
            return -1;
        L41:
            return Utf8.access$300(r7, r1, r0, r9 - r0);
        L22:
            if (r0 >= (r9 - 1)) goto L24;
            int r5 = r82 + 2;
            byte r04 = r7.get(r0);
            if (r04 > (-65)) goto L37;
            if (r1 != (-32)) goto L31;
            if (r04 < (-96)) goto L37;
        L31:
            if (r1 != (-19)) goto L34;
            if (r04 >= (-96)) goto L37;
        L34:
            if (r7.get(r5) > (-65)) goto L37;
            r82 = r82 + 3;
        L37:
            return -1;
        L24:
            return Utf8.access$300(r7, r1, r0, r9 - r0);
        L10:
            if (r0 >= r9) goto L11;
            if (r1 < (-62)) goto L18;
            if (r7.get(r0) > (-65)) goto L18;
            r82 = r82 + 2;
        L18:
            return -1;
        L11:
            return r1;
        L4:
            return 0;
        }

        public final String decodeUtf8(ByteBuffer r2, int r3, int r4) {
            if (r2.hasArray() == false) goto L7;
            int r0 = r2.arrayOffset();
            return decodeUtf8(r2.array(), r0 + r3, r4);
        L7:
            if (r2.isDirect() == false) goto L11;
            return decodeUtf8Direct(r2, r3, r4);
        L11:
            return decodeUtf8Default(r2, r3, r4);
        }

        public abstract String decodeUtf8(byte[] r1, int r2, int r3);

        public final String decodeUtf8Default(ByteBuffer r7, int r8, int r9) {
            if (((r8 | r9) | ((r7.limit() - r8) - r9)) < 0) goto L41;
            int r6 = r8 + r9;
            char[] r4 = new char[r9];
            int r0 = 0;
        L5:
            if (r8 >= r6) goto L10;
            byte r1 = r7.get(r8);
            if (DecodeUtil.access$400(r1) == false) goto L10;
            r8 = r8 + 1;
            DecodeUtil.access$500(r1, r4, r0);
            r0 = r0 + 1;
        L10:
            int r5 = r0;
        L11:
            if (r8 >= r6) goto L40;
            int r02 = r8 + 1;
            byte r03 = r7.get(r8);
            if (DecodeUtil.access$400(r03) == true) goto L14;
            if (DecodeUtil.access$600(r03) == true) goto L23;
            if (DecodeUtil.access$800(r03) == true) goto L30;
            if (r02 >= (r6 - 2)) goto L38;
            byte r12 = r7.get(r02);
            int r3 = r8 + 3;
            byte r2 = r7.get(r8 + 2);
            r8 = r8 + 4;
            DecodeUtil.access$1000(r03, r12, r2, r7.get(r3), r4, r5);
            r5 = r5 + 2;
            goto L11
        L38:
            throw InvalidProtocolBufferException.invalidUtf8();
        L30:
            if (r02 >= (r6 - 1)) goto L33;
            int r22 = r8 + 2;
            r8 = r8 + 3;
            DecodeUtil.access$900(r03, r7.get(r02), r7.get(r22), r4, r5);
            r5 = r5 + 1;
            goto L11
        L33:
            throw InvalidProtocolBufferException.invalidUtf8();
        L23:
            if (r02 >= r6) goto L26;
            r8 = r8 + 2;
            DecodeUtil.access$700(r03, r7.get(r02), r4, r5);
            r5 = r5 + 1;
            goto L11
        L26:
            throw InvalidProtocolBufferException.invalidUtf8();
        L14:
            int r82 = r5 + 1;
            DecodeUtil.access$500(r03, r4, r5);
            int r04 = r02;
        L15:
            if (r04 >= r6) goto L20;
            byte r13 = r7.get(r04);
            if (DecodeUtil.access$400(r13) == false) goto L20;
            r04 = r04 + 1;
            DecodeUtil.access$500(r13, r4, r82);
            r82 = r82 + 1;
        L20:
            r5 = r82;
            r8 = r04;
            goto L11
        L40:
            return new String(r4, 0, r5);
        L41:
            Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(r7.limit()), Integer.valueOf(r8), Integer.valueOf(r9)});
            return null;
        }

        public abstract String decodeUtf8Direct(ByteBuffer r1, int r2, int r3);

        public abstract int encodeUtf8(CharSequence r1, byte[] r2, int r3, int r4);

        public final boolean isValidUtf8(ByteBuffer r2, int r3, int r4) {
            if (partialIsValidUtf8(0, r2, r3, r4) != 0) goto L6;
            return true;
        L6:
            return false;
        }

        public final boolean isValidUtf8(byte[] r2, int r3, int r4) {
            if (partialIsValidUtf8(0, r2, r3, r4) != 0) goto L6;
            return true;
        L6:
            return false;
        }

        public final int partialIsValidUtf8(int r2, ByteBuffer r3, int r4, int r5) {
            if (r3.hasArray() == false) goto L7;
            int r0 = r3.arrayOffset();
            return partialIsValidUtf8(r2, r3.array(), r4 + r0, r0 + r5);
        L7:
            if (r3.isDirect() == false) goto L11;
            return partialIsValidUtf8Direct(r2, r3, r4, r5);
        L11:
            return partialIsValidUtf8Default(r2, r3, r4, r5);
        }

        public abstract int partialIsValidUtf8(int r1, byte[] r2, int r3, int r4);

        public final int partialIsValidUtf8Default(int r6, ByteBuffer r7, int r8, int r9) {
            if (r6 == 0) goto L55;
            if (r8 < r9) goto L5;
            return r6;
        L5:
            byte r5 = (byte) r6;
            if (r5 >= (-32)) goto L15;
            if (r5 < (-62)) goto L13;
            int r52 = r8 + 1;
            if (r7.get(r8) > (-65)) goto L13;
        L12:
            r8 = r52;
        L13:
            return -1;
        L15:
            if (r5 >= (-16)) goto L33;
            byte r62 = (byte) (~(r6 >> 8));
            if (r62 != 0) goto L23;
            int r63 = r8 + 1;
            byte r82 = r7.get(r8);
            if (r63 >= r9) goto L21;
            r8 = r63;
            r62 = r82;
            goto L23
        L21:
            return Utf8.access$000(r5, r82);
        L23:
            if (r62 <= (-65)) goto L25;
        L32:
            return -1;
        L25:
            if (r5 != (-32)) goto L28;
            if (r62 < (-96)) goto L32;
        L28:
            if (r5 != (-19)) goto L30;
            if (r62 >= (-96)) goto L32;
        L30:
            r52 = r8 + 1;
            if (r7.get(r8) <= (-65)) goto L12;
        L33:
            byte r0 = (byte) (~(r6 >> 8));
            if (r0 != 0) goto L40;
            int r64 = r8 + 1;
            r0 = r7.get(r8);
            if (r64 >= r9) goto L38;
            byte r83 = 0;
        L41:
            if (r83 != 0) goto L47;
            int r84 = r64 + 1;
            byte r65 = r7.get(r64);
            if (r84 >= r9) goto L45;
            r83 = r65;
            r64 = r84;
            goto L47
        L45:
            return Utf8.access$100(r5, r0, r65);
        L47:
            if (r0 <= (-65)) goto L49;
        L53:
            return -1;
        L49:
            if ((((r5 << 28) + (r0 + 112)) >> 30) != 0) goto L53;
            if (r83 > (-65)) goto L53;
            r8 = r64 + 1;
            if (r7.get(r64) <= (-65)) goto L55;
        L38:
            return Utf8.access$000(r5, r0);
        L40:
            r83 = (byte) (r6 >> 16);
            r64 = r8;
        L55:
            return partialIsValidUtf8(r7, r8, r9);
        }

        public abstract int partialIsValidUtf8Direct(int r1, ByteBuffer r2, int r3, int r4);
    }

    public static final class SafeProcessor extends Processor {
        public SafeProcessor() {
        }

        private static int partialIsValidUtf8(byte[] r1, int r2, int r3) {
        L2:
            if (r2 >= r3) goto L6;
            if (r1[r2] < 0) goto L6;
            r2 = r2 + 1;
        L6:
            if (r2 < r3) goto L10;
            return 0;
        L10:
            return partialIsValidUtf8NonAscii(r1, r2, r3);
        }

        private static int partialIsValidUtf8NonAscii(byte[] r7, int r8, int r9) {
        L2:
            if (r8 >= r9) goto L3;
            int r0 = r8 + 1;
            byte r1 = r7[r8];
            if (r1 < 0) goto L8;
            r8 = r0;
            goto L2
        L8:
            if (r1 < (-32)) goto L9;
            if (r1 < (-16)) goto L19;
            if (r0 >= (r9 - 2)) goto L36;
            int r2 = r8 + 2;
            byte r02 = r7[r0];
            if (r02 > (-65)) goto L45;
            if ((((r1 << 28) + (r02 + 112)) >> 30) != 0) goto L45;
            int r03 = r8 + 3;
            if (r7[r2] > (-65)) goto L45;
            r8 = r8 + 4;
            if (r7[r03] <= (-65)) goto L2;
        L45:
            return -1;
        L36:
            return Utf8.access$1100(r7, r0, r9);
        L19:
            if (r0 >= (r9 - 1)) goto L21;
            int r5 = r8 + 2;
            byte r04 = r7[r0];
            if (r04 > (-65)) goto L32;
            if (r1 != (-32)) goto L28;
            if (r04 < (-96)) goto L32;
        L28:
            if (r1 != (-19)) goto L30;
            if (r04 >= (-96)) goto L32;
        L30:
            r8 = r8 + 3;
            if (r7[r5] <= (-65)) goto L2;
        L32:
            return -1;
        L21:
            return Utf8.access$1100(r7, r0, r9);
        L9:
            if (r0 >= r9) goto L10;
            if (r1 < (-62)) goto L15;
            r8 = r8 + 2;
            if (r7[r0] <= (-65)) goto L2;
        L15:
            return -1;
        L10:
            return r1;
        L3:
            return 0;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] r7, int r8, int r9) {
            if (((r8 | r9) | ((r7.length - r8) - r9)) < 0) goto L41;
            int r6 = r8 + r9;
            char[] r4 = new char[r9];
            int r0 = 0;
        L5:
            if (r8 >= r6) goto L10;
            byte r1 = r7[r8];
            if (DecodeUtil.access$400(r1) == false) goto L10;
            r8 = r8 + 1;
            DecodeUtil.access$500(r1, r4, r0);
            r0 = r0 + 1;
        L10:
            int r5 = r0;
        L11:
            if (r8 >= r6) goto L40;
            int r02 = r8 + 1;
            byte r03 = r7[r8];
            if (DecodeUtil.access$400(r03) == true) goto L14;
            if (DecodeUtil.access$600(r03) == true) goto L23;
            if (DecodeUtil.access$800(r03) == true) goto L30;
            if (r02 >= (r6 - 2)) goto L38;
            byte r12 = r7[r02];
            int r3 = r8 + 3;
            byte r2 = r7[r8 + 2];
            r8 = r8 + 4;
            DecodeUtil.access$1000(r03, r12, r2, r7[r3], r4, r5);
            r5 = r5 + 2;
            goto L11
        L38:
            throw InvalidProtocolBufferException.invalidUtf8();
        L30:
            if (r02 >= (r6 - 1)) goto L33;
            int r22 = r8 + 2;
            r8 = r8 + 3;
            DecodeUtil.access$900(r03, r7[r02], r7[r22], r4, r5);
            r5 = r5 + 1;
            goto L11
        L33:
            throw InvalidProtocolBufferException.invalidUtf8();
        L23:
            if (r02 >= r6) goto L26;
            r8 = r8 + 2;
            DecodeUtil.access$700(r03, r7[r02], r4, r5);
            r5 = r5 + 1;
            goto L11
        L26:
            throw InvalidProtocolBufferException.invalidUtf8();
        L14:
            int r82 = r5 + 1;
            DecodeUtil.access$500(r03, r4, r5);
            int r04 = r02;
        L15:
            if (r04 >= r6) goto L20;
            byte r13 = r7[r04];
            if (DecodeUtil.access$400(r13) == false) goto L20;
            r04 = r04 + 1;
            DecodeUtil.access$500(r13, r4, r82);
            r82 = r82 + 1;
        L20:
            r5 = r82;
            r8 = r04;
            goto L11
        L40:
            return new String(r4, 0, r5);
        L41:
            Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(r7.length), Integer.valueOf(r8), Integer.valueOf(r9)});
            return null;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer r1, int r2, int r3) {
            return decodeUtf8Default(r1, r2, r3);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence r8, byte[] r9, int r10, int r11) {
            int r7 = r8.length();
            int r112 = r11 + r10;
            int r1 = 0;
        L4:
            if (r1 >= r7) goto L10;
            int r3 = r1 + r10;
            if (r3 >= r112) goto L10;
            char r4 = r8.charAt(r1);
            if (r4 >= 128) goto L10;
            r9[r3] = (byte) r4;
            r1 = r1 + 1;
        L10:
            if (r1 == r7) goto L12;
            int r102 = r10 + r1;
        L14:
            if (r1 >= r7) goto L52;
            char r32 = r8.charAt(r1);
            if (r32 >= 128) goto L20;
            if (r102 >= r112) goto L20;
            r9[r102] = (byte) r32;
            r102 = r102 + 1;
        L37:
            r1 = r1 + 1;
        L20:
            if (r32 >= 2048) goto L25;
            if (r102 > (r112 - 2)) goto L25;
            int r42 = r102 + 1;
            r9[r102] = (byte) ((r32 >>> 6) | 960);
            r102 = r102 + 2;
            r9[r42] = (byte) ((r32 & '?') | 128);
        L25:
            if (r32 < 55296) goto L28;
            if (57343 < r32) goto L28;
        L31:
            if (r102 > (r112 - 4)) goto L41;
            int r43 = r1 + 1;
            if (r43 == r8.length()) goto L40;
            char r12 = r8.charAt(r43);
            if (Character.isSurrogatePair(r32, r12) == false) goto L38;
            int r13 = Character.toCodePoint(r32, r12);
            r9[r102] = (byte) ((r13 >>> 18) | 240);
            r9[r102 + 1] = (byte) (((r13 >>> 12) & 63) | 128);
            int r33 = r102 + 3;
            r9[r102 + 2] = (byte) (((r13 >>> 6) & 63) | 128);
            r102 = r102 + 4;
            r9[r33] = (byte) ((r13 & 63) | 128);
            r1 = r43;
            goto L37
        L38:
            r1 = r43;
        L40:
            throw new UnpairedSurrogateException(r1 - 1, r7);
        L41:
            if (55296 > r32) goto L50;
            if (r32 > 57343) goto L50;
            int r92 = r1 + 1;
            if (r92 == r8.length()) goto L49;
            if (Character.isSurrogatePair(r32, r8.charAt(r92)) == true) goto L50;
        L49:
            throw new UnpairedSurrogateException(r1, r7);
        L50:
            Utf8$SafeProcessor$$ExternalSyntheticBUOutline0.m(r32, r102);
            return 0;
        L28:
            if (r102 > (r112 - 3)) goto L31;
            r9[r102] = (byte) ((r32 >>> '\f') | 480);
            int r5 = r102 + 2;
            r9[r102 + 1] = (byte) (((r32 >>> 6) & 63) | 128);
            r102 = r102 + 3;
            r9[r5] = (byte) ((r32 & '?') | 128);
            goto L37
        L52:
            return r102;
        L12:
            return r10 + r7;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int partialIsValidUtf8(int r6, byte[] r7, int r8, int r9) {
            if (r6 == 0) goto L55;
            if (r8 < r9) goto L5;
            return r6;
        L5:
            byte r5 = (byte) r6;
            if (r5 >= (-32)) goto L15;
            if (r5 < (-62)) goto L13;
            int r52 = r8 + 1;
            if (r7[r8] > (-65)) goto L13;
        L12:
            r8 = r52;
        L13:
            return -1;
        L15:
            if (r5 >= (-16)) goto L33;
            byte r62 = (byte) (~(r6 >> 8));
            if (r62 != 0) goto L23;
            int r63 = r8 + 1;
            byte r82 = r7[r8];
            if (r63 >= r9) goto L21;
            r8 = r63;
            r62 = r82;
            goto L23
        L21:
            return Utf8.access$000(r5, r82);
        L23:
            if (r62 <= (-65)) goto L25;
        L32:
            return -1;
        L25:
            if (r5 != (-32)) goto L28;
            if (r62 < (-96)) goto L32;
        L28:
            if (r5 != (-19)) goto L30;
            if (r62 >= (-96)) goto L32;
        L30:
            r52 = r8 + 1;
            if (r7[r8] <= (-65)) goto L12;
        L33:
            byte r0 = (byte) (~(r6 >> 8));
            if (r0 != 0) goto L40;
            int r64 = r8 + 1;
            r0 = r7[r8];
            if (r64 >= r9) goto L38;
            byte r83 = 0;
        L41:
            if (r83 != 0) goto L47;
            int r84 = r64 + 1;
            byte r65 = r7[r64];
            if (r84 >= r9) goto L45;
            r83 = r65;
            r64 = r84;
            goto L47
        L45:
            return Utf8.access$100(r5, r0, r65);
        L47:
            if (r0 <= (-65)) goto L49;
        L53:
            return -1;
        L49:
            if ((((r5 << 28) + (r0 + 112)) >> 30) != 0) goto L53;
            if (r83 > (-65)) goto L53;
            r8 = r64 + 1;
            if (r7[r64] <= (-65)) goto L55;
        L38:
            return Utf8.access$000(r5, r0);
        L40:
            r83 = (byte) (r6 >> 16);
            r64 = r8;
        L55:
            return partialIsValidUtf8(r7, r8, r9);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int partialIsValidUtf8Direct(int r1, ByteBuffer r2, int r3, int r4) {
            return partialIsValidUtf8Default(r1, r2, r3, r4);
        }
    }

    public static class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int r3, int r4) {
            super("Unpaired surrogate at index " + r3 + " of " + r4);
        }
    }

    public static final class UnsafeProcessor extends Processor {
        public UnsafeProcessor() {
        }

        public static boolean isAvailable() {
            if (UnsafeUtil.hasUnsafeArrayOperations() == true) goto L5;
            return false;
        L5:
            if (UnsafeUtil.hasUnsafeByteBufferOperations() == false) goto L10;
            return true;
        L10:
            return false;
        }

        private static int partialIsValidUtf8(long r10, int r12) {
            int r0 = unsafeEstimateConsecutiveAscii(r10, r12);
            long r102 = r10 + r0;
            int r122 = r12 - r0;
        L3:
            byte r1 = 0;
        L5:
            if (r122 <= 0) goto L10;
            long r4 = r102 + 1;
            r1 = UnsafeUtil.getByte(r102);
            if (r1 < 0) goto L9;
            r122 = r122 - 1;
            r102 = r4;
            goto L5
        L9:
            r102 = r4;
        L10:
            if (r122 == 0) goto L11;
            int r02 = r122 - 1;
            if (r1 < (-32)) goto L14;
            if (r1 < (-16)) goto L26;
            if (r02 < 3) goto L43;
            r122 = r122 - 4;
            long r2 = 1 + r102;
            byte r03 = UnsafeUtil.getByte(r102);
            if (r03 > (-65)) goto L52;
            if ((((r1 << 28) + (r03 + 112)) >> 30) != 0) goto L52;
            long r8 = 2 + r102;
            if (UnsafeUtil.getByte(r2) > (-65)) goto L52;
            r102 = r102 + 3;
            if (UnsafeUtil.getByte(r8) <= (-65)) goto L3;
        L52:
            return -1;
        L43:
            return unsafeIncompleteStateFor(r102, r1, r02);
        L26:
            if (r02 < 2) goto L28;
            r122 = r122 - 3;
            long r22 = 1 + r102;
            byte r04 = UnsafeUtil.getByte(r102);
            if (r04 > (-65)) goto L39;
            if (r1 != (-32)) goto L35;
            if (r04 < (-96)) goto L39;
        L35:
            if (r1 != (-19)) goto L37;
            if (r04 >= (-96)) goto L39;
        L37:
            r102 = r102 + 2;
            if (UnsafeUtil.getByte(r22) <= (-65)) goto L3;
        L39:
            return -1;
        L28:
            return unsafeIncompleteStateFor(r102, r1, r02);
        L14:
            if (r02 == 0) goto L15;
            r122 = r122 - 2;
            if (r1 < (-62)) goto L22;
            long r23 = 1 + r102;
            if (UnsafeUtil.getByte(r102) > (-65)) goto L22;
            r102 = r23;
        L22:
            return -1;
        L15:
            return r1;
        L11:
            return 0;
        }

        private static int partialIsValidUtf8(byte[] r10, long r11, int r13) {
            int r0 = unsafeEstimateConsecutiveAscii(r10, r11, r13);
            int r132 = r13 - r0;
            long r112 = r11 + r0;
        L3:
            byte r1 = 0;
        L5:
            if (r132 <= 0) goto L10;
            long r4 = r112 + 1;
            r1 = UnsafeUtil.getByte(r10, r112);
            if (r1 < 0) goto L9;
            r132 = r132 - 1;
            r112 = r4;
            goto L5
        L9:
            r112 = r4;
        L10:
            if (r132 == 0) goto L11;
            int r02 = r132 - 1;
            if (r1 < (-32)) goto L14;
            if (r1 < (-16)) goto L26;
            if (r02 < 3) goto L43;
            r132 = r132 - 4;
            long r2 = 1 + r112;
            byte r03 = UnsafeUtil.getByte(r10, r112);
            if (r03 > (-65)) goto L52;
            if ((((r1 << 28) + (r03 + 112)) >> 30) != 0) goto L52;
            long r8 = 2 + r112;
            if (UnsafeUtil.getByte(r10, r2) > (-65)) goto L52;
            r112 = r112 + 3;
            if (UnsafeUtil.getByte(r10, r8) <= (-65)) goto L3;
        L52:
            return -1;
        L43:
            return unsafeIncompleteStateFor(r10, r1, r112, r02);
        L26:
            if (r02 < 2) goto L28;
            r132 = r132 - 3;
            long r22 = 1 + r112;
            byte r04 = UnsafeUtil.getByte(r10, r112);
            if (r04 > (-65)) goto L39;
            if (r1 != (-32)) goto L35;
            if (r04 < (-96)) goto L39;
        L35:
            if (r1 != (-19)) goto L37;
            if (r04 >= (-96)) goto L39;
        L37:
            r112 = r112 + 2;
            if (UnsafeUtil.getByte(r10, r22) <= (-65)) goto L3;
        L39:
            return -1;
        L28:
            return unsafeIncompleteStateFor(r10, r1, r112, r02);
        L14:
            if (r02 == 0) goto L15;
            r132 = r132 - 2;
            if (r1 < (-62)) goto L22;
            long r23 = 1 + r112;
            if (UnsafeUtil.getByte(r10, r112) > (-65)) goto L22;
            r112 = r23;
        L22:
            return -1;
        L15:
            return r1;
        L11:
            return 0;
        }

        private static int unsafeEstimateConsecutiveAscii(long r5, int r7) {
            if (r7 >= 16) goto L6;
            return 0;
        L6:
            int r0 = (int) ((-r5) & 7);
            int r1 = r0;
        L7:
            if (r1 <= 0) goto L13;
            long r2 = 1 + r5;
            if (UnsafeUtil.getByte(r5) < 0) goto L11;
            r1 = r1 - 1;
            r5 = r2;
            goto L7
        L11:
            return r0 - r1;
        L13:
            int r02 = r7 - r0;
        L15:
            if (r02 < 8) goto L20;
            if ((UnsafeUtil.getLong(r5) & (-9187201950435737472L)) != 0) goto L20;
            r5 = r5 + 8;
            r02 = r02 - 8;
        L20:
            return r7 - r02;
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] r8, long r9, int r11) {
            int r1 = 0;
            if (r11 >= 16) goto L5;
            return 0;
        L5:
            int r0 = 8 - (((int) r9) & 7);
        L7:
            if (r1 >= r0) goto L12;
            long r2 = 1 + r9;
            if (UnsafeUtil.getByte(r8, r9) < 0) goto L10;
            r1 = r1 + 1;
            r9 = r2;
            goto L7
        L10:
            return r1;
        L12:
            int r02 = r1 + 8;
            if (r02 > r11) goto L18;
            if ((UnsafeUtil.getLong(r8, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + r9) & (-9187201950435737472L)) != 0) goto L18;
            r9 = r9 + 8;
            r1 = r02;
        L18:
            if (r1 >= r11) goto L23;
            long r4 = r9 + 1;
            if (UnsafeUtil.getByte(r8, r9) < 0) goto L21;
            r1 = r1 + 1;
            r9 = r4;
            goto L18
        L21:
            return r1;
        L23:
            return r11;
        }

        private static int unsafeIncompleteStateFor(long r2, int r4, int r5) {
            if (r5 == 0) goto L14;
            if (r5 == 1) goto L12;
            if (r5 == 2) goto L8;
            AsyncTimeout$$ExternalSyntheticBUOutline0.m();
            return 0;
        L8:
            return Utf8.access$100(r4, UnsafeUtil.getByte(r2), UnsafeUtil.getByte(r2 + 1));
        L12:
            return Utf8.access$000(r4, UnsafeUtil.getByte(r2));
        L14:
            return Utf8.access$1200(r4);
        }

        private static int unsafeIncompleteStateFor(byte[] r2, int r3, long r4, int r6) {
            if (r6 == 0) goto L14;
            if (r6 == 1) goto L12;
            if (r6 == 2) goto L8;
            AsyncTimeout$$ExternalSyntheticBUOutline0.m();
            return 0;
        L8:
            return Utf8.access$100(r3, UnsafeUtil.getByte(r2, r4), UnsafeUtil.getByte(r2, r4 + 1));
        L12:
            return Utf8.access$000(r3, UnsafeUtil.getByte(r2, r4));
        L14:
            return Utf8.access$1200(r3);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] r3, int r4, int r5) {
            Charset r0 = Internal.UTF_8;
            String r2 = new String(r3, r4, r5, r0);
            if (r2.contains("�") == true) goto L6;
        L7:
            return r2;
        L6:
            if (Arrays.equals(r2.getBytes(r0), Arrays.copyOfRange(r3, r4, r5 + r4)) == true) goto L7;
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer r21, int r22, int r23) {
            if (((r22 | r23) | ((r21.limit() - r22) - r23)) < 0) goto L45;
            long r2 = UnsafeUtil.addressOffset(r21) + r22;
            long r4 = r23 + r2;
            char[] r10 = new char[r23];
            int r1 = 0;
        L6:
            if (r2 >= r4) goto L11;
            byte r6 = UnsafeUtil.getByte(r2);
            if (DecodeUtil.access$400(r6) == false) goto L11;
            r2 = r2 + 1;
            DecodeUtil.access$500(r6, r10, r1);
            r1 = r1 + 1;
        L11:
            int r11 = r1;
        L13:
            if (r2 >= r4) goto L44;
            long r62 = r2 + 1;
            byte r63 = UnsafeUtil.getByte(r2);
            if (DecodeUtil.access$400(r63) == true) goto L16;
            if (DecodeUtil.access$600(r63) == true) goto L27;
            if (DecodeUtil.access$800(r63) == true) goto L34;
            if (r62 >= (r4 - 2)) goto L42;
            byte r7 = UnsafeUtil.getByte(r62);
            long r16 = r2 + 3;
            byte r8 = UnsafeUtil.getByte(2 + r2);
            r2 = r2 + 4;
            DecodeUtil.access$1000(r63, r7, r8, UnsafeUtil.getByte(r16), r10, r11);
            r11 = r11 + 2;
            goto L13
        L42:
            throw InvalidProtocolBufferException.invalidUtf8();
        L34:
            if (r62 >= (r4 - 1)) goto L37;
            long r14 = 2 + r2;
            r2 = r2 + 3;
            DecodeUtil.access$900(r63, UnsafeUtil.getByte(r62), UnsafeUtil.getByte(r14), r10, r11);
            r11 = r11 + 1;
            goto L13
        L37:
            throw InvalidProtocolBufferException.invalidUtf8();
        L27:
            if (r62 >= r4) goto L30;
            r2 = r2 + 2;
            DecodeUtil.access$700(r63, UnsafeUtil.getByte(r62), r10, r11);
            r11 = r11 + 1;
            goto L13
        L30:
            throw InvalidProtocolBufferException.invalidUtf8();
        L16:
            int r12 = r11 + 1;
            DecodeUtil.access$500(r63, r10, r11);
            long r64 = r62;
        L18:
            if (r64 >= r4) goto L23;
            byte r24 = UnsafeUtil.getByte(r64);
            if (DecodeUtil.access$400(r24) == false) goto L23;
            r64 = r64 + 1;
            DecodeUtil.access$500(r24, r10, r12);
            r12 = r12 + 1;
        L23:
            r11 = r12;
            r2 = r64;
            goto L13
        L44:
            return new String(r10, 0, r11);
        L45:
            Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(r21.limit()), Integer.valueOf(r22), Integer.valueOf(r23)});
            return null;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence r24, byte[] r25, int r26, int r27) {
            long r4 = r26;
            long r6 = r27 + r4;
            int r8 = r24.length();
            if (r8 > r27) goto L58;
            if ((r25.length - r27) < r26) goto L58;
            int r2 = 0;
        L7:
            long r11 = 1;
            if (r2 >= r8) goto L12;
            char r13 = r24.charAt(r2);
            if (r13 >= 128) goto L12;
            UnsafeUtil.putByte(r25, r4, (byte) r13);
            r2 = r2 + 1;
            r4 = 1 + r4;
        L12:
            if (r2 == r8) goto L14;
        L15:
            if (r2 >= r8) goto L56;
            char r132 = r24.charAt(r2);
            if (r132 >= 128) goto L22;
            if (r4 >= r6) goto L22;
            UnsafeUtil.putByte(r25, r4, (byte) r132);
            long r19 = r6;
            long r262 = r11;
            r4 = r4 + r11;
        L41:
            r2 = r2 + 1;
            r11 = r262;
            r6 = r19;
        L22:
            if (r132 < 2048) goto L24;
        L26:
            r262 = r11;
            if (r132 < 55296) goto L32;
            if (57343 < r132) goto L32;
        L30:
            r19 = r6;
            if (r4 > (r19 - 4)) goto L45;
            int r112 = r2 + 1;
            if (r112 == r8) goto L44;
            char r22 = r24.charAt(r112);
            if (Character.isSurrogatePair(r132, r22) == false) goto L42;
            int r23 = Character.toCodePoint(r132, r22);
            UnsafeUtil.putByte(r25, r4, (byte) ((r23 >>> 18) | 240));
            UnsafeUtil.putByte(r25, r4 + r262, (byte) (((r23 >>> 12) & 63) | 128));
            long r62 = r4 + 3;
            UnsafeUtil.putByte(r25, r4 + 2, (byte) (((r23 >>> 6) & 63) | 128));
            r4 = r4 + 4;
            UnsafeUtil.putByte(r25, r62, (byte) ((r23 & 63) | 128));
            r2 = r112;
            goto L41
        L42:
            r2 = r112;
        L44:
            throw new UnpairedSurrogateException(r2 - 1, r8);
        L45:
            if (55296 > r132) goto L54;
            if (r132 > 57343) goto L54;
            int r1 = r2 + 1;
            if (r1 == r8) goto L52;
            if (Character.isSurrogatePair(r132, r24.charAt(r1)) == true) goto L54;
        L52:
            throw new UnpairedSurrogateException(r2, r8);
        L54:
            throw new ArrayIndexOutOfBoundsException("Failed writing " + r132 + " at index " + r4);
        L32:
            if (r4 > (r6 - 3)) goto L30;
            UnsafeUtil.putByte(r25, r4, (byte) ((r132 >>> '\f') | 480));
            long r14 = r4 + 2;
            r19 = r6;
            UnsafeUtil.putByte(r25, r4 + r262, (byte) (((r132 >>> 6) & 63) | 128));
            r4 = r4 + 3;
            UnsafeUtil.putByte(r25, r14, (byte) ((r132 & '?') | 128));
            goto L41
        L24:
            if (r4 > (r6 - 2)) goto L26;
            r262 = r11;
            long r113 = r4 + r262;
            UnsafeUtil.putByte(r25, r4, (byte) ((r132 >>> 6) | 960));
            r4 = r4 + 2;
            UnsafeUtil.putByte(r25, r113, (byte) ((r132 & '?') | 128));
            r19 = r6;
            goto L41
        L56:
            return (int) r4;
        L14:
            return (int) r4;
        L58:
            throw new ArrayIndexOutOfBoundsException("Failed writing " + r24.charAt(r8 - 1) + " at index " + (r26 + r27));
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int partialIsValidUtf8(int r11, byte[] r12, int r13, int r14) {
            byte r0 = 0;
            if (((r13 | r14) | (r12.length - r14)) < 0) goto L62;
            long r1 = r13;
            long r132 = r14;
            if (r11 == 0) goto L61;
            if (r1 < r132) goto L9;
            return r11;
        L9:
            byte r10 = (byte) r11;
            if (r10 >= (-32)) goto L19;
            if (r10 < (-62)) goto L17;
            long r6 = 1 + r1;
            if (UnsafeUtil.getByte(r12, r1) > (-65)) goto L17;
            r1 = r6;
        L17:
            return -1;
        L19:
            if (r10 >= (-16)) goto L39;
            byte r112 = (byte) (~(r11 >> 8));
            if (r112 != 0) goto L27;
            long r8 = r1 + 1;
            r112 = UnsafeUtil.getByte(r12, r1);
            if (r8 >= r132) goto L25;
            r1 = r8;
            goto L27
        L25:
            return Utf8.access$000(r10, r112);
        L27:
            if (r112 <= (-65)) goto L29;
        L38:
            return -1;
        L29:
            if (r10 != (-32)) goto L32;
            if (r112 < (-96)) goto L38;
        L32:
            if (r10 != (-19)) goto L34;
            if (r112 >= (-96)) goto L38;
        L34:
            long r102 = r1 + 1;
            if (UnsafeUtil.getByte(r12, r1) > (-65)) goto L38;
        L37:
            r1 = r102;
            goto L61
        L39:
            byte r3 = (byte) (~(r11 >> 8));
            if (r3 != 0) goto L46;
            long r82 = r1 + 1;
            r3 = UnsafeUtil.getByte(r12, r1);
            if (r82 >= r132) goto L44;
            r1 = r82;
        L47:
            if (r0 != 0) goto L53;
            long r83 = r1 + 1;
            r0 = UnsafeUtil.getByte(r12, r1);
            if (r83 >= r132) goto L51;
            r1 = r83;
            goto L53
        L51:
            return Utf8.access$100(r10, r3, r0);
        L53:
            if (r3 <= (-65)) goto L55;
        L59:
            return -1;
        L55:
            if ((((r10 << 28) + (r3 + 112)) >> 30) != 0) goto L59;
            if (r0 > (-65)) goto L59;
            r102 = r1 + 1;
            if (UnsafeUtil.getByte(r12, r1) <= (-65)) goto L37;
        L44:
            return Utf8.access$000(r10, r3);
        L46:
            r0 = (byte) (r11 >> 16);
        L61:
            return partialIsValidUtf8(r12, r1, (int) (r132 - r1));
        L62:
            Utf8$Processor$$ExternalSyntheticBUOutline0.m("Array length=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(r12.length), Integer.valueOf(r13), Integer.valueOf(r14)});
            return 0;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int partialIsValidUtf8Direct(int r10, ByteBuffer r11, int r12, int r13) {
            byte r0 = 0;
            if (((r12 | r13) | (r11.limit() - r13)) < 0) goto L62;
            long r1 = UnsafeUtil.addressOffset(r11) + r12;
            long r112 = (r13 - r12) + r1;
            if (r10 == 0) goto L61;
            if (r1 < r112) goto L9;
            return r10;
        L9:
            byte r9 = (byte) r10;
            if (r9 >= (-32)) goto L19;
            if (r9 < (-62)) goto L17;
            long r5 = 1 + r1;
            if (UnsafeUtil.getByte(r1) > (-65)) goto L17;
            r1 = r5;
        L17:
            return -1;
        L19:
            if (r9 >= (-16)) goto L39;
            byte r102 = (byte) (~(r10 >> 8));
            if (r102 != 0) goto L27;
            long r7 = r1 + 1;
            r102 = UnsafeUtil.getByte(r1);
            if (r7 >= r112) goto L25;
            r1 = r7;
            goto L27
        L25:
            return Utf8.access$000(r9, r102);
        L27:
            if (r102 <= (-65)) goto L29;
        L38:
            return -1;
        L29:
            if (r9 != (-32)) goto L32;
            if (r102 < (-96)) goto L38;
        L32:
            if (r9 != (-19)) goto L34;
            if (r102 >= (-96)) goto L38;
        L34:
            long r92 = r1 + 1;
            if (UnsafeUtil.getByte(r1) > (-65)) goto L38;
        L37:
            r1 = r92;
            goto L61
        L39:
            byte r132 = (byte) (~(r10 >> 8));
            if (r132 != 0) goto L46;
            long r72 = r1 + 1;
            r132 = UnsafeUtil.getByte(r1);
            if (r72 >= r112) goto L44;
            r1 = r72;
        L47:
            if (r0 != 0) goto L53;
            long r73 = r1 + 1;
            r0 = UnsafeUtil.getByte(r1);
            if (r73 >= r112) goto L51;
            r1 = r73;
            goto L53
        L51:
            return Utf8.access$100(r9, r132, r0);
        L53:
            if (r132 <= (-65)) goto L55;
        L59:
            return -1;
        L55:
            if ((((r9 << 28) + (r132 + 112)) >> 30) != 0) goto L59;
            if (r0 > (-65)) goto L59;
            r92 = r1 + 1;
            if (UnsafeUtil.getByte(r1) <= (-65)) goto L37;
        L44:
            return Utf8.access$000(r9, r132);
        L46:
            r0 = (byte) (r10 >> 16);
        L61:
            return partialIsValidUtf8(r1, (int) (r112 - r1));
        L62:
            Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(r11.limit()), Integer.valueOf(r12), Integer.valueOf(r13)});
            return 0;
        }
    }

    static {
        if (UnsafeProcessor.isAvailable() == true) goto L5;
    L7:
        Processor r0 = new SafeProcessor();
    L8:
        processor = r0;
        return;
    L5:
        if (Android.isOnAndroidDevice() == true) goto L7;
        r0 = new UnsafeProcessor();
        goto L8
    }

    public static /* synthetic */ int access$000(int r0, int r1) {
        return incompleteStateFor(r0, r1);
    }

    public static /* synthetic */ int access$100(int r0, int r1, int r2) {
        return incompleteStateFor(r0, r1, r2);
    }

    public static /* synthetic */ int access$1100(byte[] r0, int r1, int r2) {
        return incompleteStateFor(r0, r1, r2);
    }

    public static /* synthetic */ int access$1200(int r0) {
        return incompleteStateFor(r0);
    }

    public static /* synthetic */ int access$200(ByteBuffer r0, int r1, int r2) {
        return estimateConsecutiveAscii(r0, r1, r2);
    }

    public static /* synthetic */ int access$300(ByteBuffer r0, int r1, int r2, int r3) {
        return incompleteStateFor(r0, r1, r2, r3);
    }

    public static String decodeUtf8(ByteBuffer r1, int r2, int r3) {
        return processor.decodeUtf8(r1, r2, r3);
    }

    public static String decodeUtf8(byte[] r1, int r2, int r3) {
        return processor.decodeUtf8(r1, r2, r3);
    }

    public static int encode(CharSequence r1, byte[] r2, int r3, int r4) {
        return processor.encodeUtf8(r1, r2, r3, r4);
    }

    public static int encodedLength(CharSequence r5) {
        int r0 = r5.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        if (r5.charAt(r1) >= 128) goto L7;
        r1 = r1 + 1;
    L7:
        int r2 = r0;
    L8:
        if (r1 >= r0) goto L13;
        char r3 = r5.charAt(r1);
        if (r3 >= 2048) goto L12;
        r2 = r2 + ((127 - r3) >>> 31);
        r1 = r1 + 1;
        goto L8
    L12:
        r2 = r2 + encodedLengthGeneral(r5, r1);
    L13:
        if (r2 < r0) goto L16;
        return r2;
    L16:
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (r2 + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence r4, int r5) {
        int r0 = r4.length();
        int r1 = 0;
    L3:
        if (r5 >= r0) goto L17;
        char r2 = r4.charAt(r5);
        if (r2 >= 2048) goto L7;
        r1 = r1 + ((127 - r2) >>> 31);
    L16:
        r5 = r5 + 1;
        goto L3
    L7:
        r1 = r1 + 2;
        if (55296 > r2) goto L16;
        if (r2 > 57343) goto L16;
        if (Character.codePointAt(r4, r5) < 65536) goto L15;
        r5 = r5 + 1;
        goto L16
    L15:
        throw new UnpairedSurrogateException(r5, r0);
    L17:
        return r1;
    }

    private static int estimateConsecutiveAscii(ByteBuffer r5, int r6, int r7) {
        int r72 = r7 - 7;
        int r0 = r6;
    L3:
        if (r0 >= r72) goto L8;
        if ((r5.getLong(r0) & (-9187201950435737472L)) != 0) goto L8;
        r0 = r0 + 8;
    L8:
        return r0 - r6;
    }

    private static int incompleteStateFor(int r1) {
        if (r1 <= (-12)) goto L6;
        return -1;
    L6:
        return r1;
    }

    private static int incompleteStateFor(int r1, int r2) {
        if (r1 <= (-12)) goto L5;
        return -1;
    L5:
        if (r2 <= (-65)) goto L8;
        return -1;
    L8:
        return r1 ^ (r2 << 8);
    }

    private static int incompleteStateFor(int r1, int r2, int r3) {
        if (r1 <= (-12)) goto L5;
        return -1;
    L5:
        if (r2 > (-65)) goto L12;
        if (r3 <= (-65)) goto L9;
        return -1;
    L9:
        return (r1 ^ (r2 << 8)) ^ (r3 << 16);
    L12:
        return -1;
    }

    private static int incompleteStateFor(ByteBuffer r2, int r3, int r4, int r5) {
        if (r5 == 0) goto L14;
        if (r5 == 1) goto L12;
        if (r5 == 2) goto L8;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L8:
        return incompleteStateFor(r3, r2.get(r4), r2.get(r4 + 1));
    L12:
        return incompleteStateFor(r3, r2.get(r4));
    L14:
        return incompleteStateFor(r3);
    }

    private static int incompleteStateFor(byte[] r3, int r4, int r5) {
        byte r0 = r3[r4 - 1];
        int r52 = r5 - r4;
        if (r52 == 0) goto L15;
        if (r52 == 1) goto L13;
        if (r52 == 2) goto L9;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        return incompleteStateFor(r0, r3[r4], r3[r4 + 1]);
    L13:
        return incompleteStateFor(r0, r3[r4]);
    L15:
        return incompleteStateFor(r0);
    }

    public static boolean isValidUtf8(ByteBuffer r3) {
        return processor.isValidUtf8(r3, r3.position(), r3.remaining());
    }

    public static boolean isValidUtf8(byte[] r3) {
        return processor.isValidUtf8(r3, 0, r3.length);
    }

    public static boolean isValidUtf8(byte[] r1, int r2, int r3) {
        return processor.isValidUtf8(r1, r2, r3);
    }
}
