package androidx.datastore.preferences.protobuf;

import com.google.protobuf.Utf8$Processor$$ExternalSyntheticBUOutline0;
import com.google.protobuf.Utf8$SafeProcessor$$ExternalSyntheticBUOutline0;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes.dex */
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

        public abstract String decodeUtf8(byte[] r1, int r2, int r3);

        public abstract int encodeUtf8(String r1, byte[] r2, int r3, int r4);
    }

    public static final class SafeProcessor extends Processor {
        public SafeProcessor() {
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
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

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        public int encodeUtf8(String r8, byte[] r9, int r10, int r11) {
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

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] r3, int r4, int r5) {
            Charset r0 = Internal.UTF_8;
            String r2 = new String(r3, r4, r5, r0);
            if (r2.indexOf(65533) >= 0) goto L6;
        L7:
            return r2;
        L6:
            if (Arrays.equals(r2.getBytes(r0), Arrays.copyOfRange(r3, r4, r5 + r4)) == true) goto L7;
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        public int encodeUtf8(String r24, byte[] r25, int r26, int r27) {
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

    public static String decodeUtf8(byte[] r1, int r2, int r3) {
        return processor.decodeUtf8(r1, r2, r3);
    }

    public static int encode(String r1, byte[] r2, int r3, int r4) {
        return processor.encodeUtf8(r1, r2, r3, r4);
    }

    public static int encodedLength(String r5) {
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

    private static int encodedLengthGeneral(String r4, int r5) {
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
}
