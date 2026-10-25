package com.google.common.io;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import kotlin.UByte;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = null;
    private static final BaseEncoding BASE32 = null;
    private static final BaseEncoding BASE32_HEX = null;
    private static final BaseEncoding BASE64 = null;
    private static final BaseEncoding BASE64_URL = null;

    public static final class Alphabet {
        final int bitsPerChar;
        final int bytesPerChunk;
        private final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        private final boolean ignoreCase;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        public Alphabet(String r3, char[] r4) {
            this(r3, r4, decodabetFor(r4), false);
        }

        private Alphabet(String r4, char[] r5, byte[] r6, boolean r7) {
            this.name = (String) Preconditions.checkNotNull(r4);
            this.chars = (char[]) Preconditions.checkNotNull(r5);
            int r42 = IntMath.log2(r5.length, RoundingMode.UNNECESSARY);     // Catch: ArithmeticException -> L10
            this.bitsPerChar = r42;     // Catch: ArithmeticException -> L10
            int r0 = Integer.numberOfTrailingZeros(r42);
            int r1 = 1 << (3 - r0);
            this.charsPerChunk = r1;
            this.bytesPerChunk = r42 >> r0;
            this.mask = r5.length - 1;
            this.decodabet = r6;
            boolean[] r43 = new boolean[r1];
            int r52 = 0;
        L6:
            if (r52 >= this.bytesPerChunk) goto L8;
            r43[IntMath.divide(r52 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
            r52 = r52 + 1;
            goto L6
        L8:
            this.validPadding = r43;
            this.ignoreCase = r7;
            return;
        L10:
            e = move-exception;
            throw new IllegalArgumentException("Illegal alphabet length " + r5.length, e);
        }

        public static /* synthetic */ char[] access$000(Alphabet r0) {
            return r0.chars;
        }

        private static byte[] decodabetFor(char[] r9) {
            byte[] r1 = new byte[128];
            Arrays.fill(r1, (byte) -1);
            int r4 = 0;
        L4:
            if (r4 >= r9.length) goto L14;
            char r5 = r9[r4];
            boolean r6 = true;
            if (r5 >= 128) goto L8;
            boolean r7 = true;
        L9:
            Preconditions.checkArgument(r7, "Non-ASCII character: %s", r5);
            if (r1[r5] == (-1)) goto L13;
            r6 = false;
        L13:
            Preconditions.checkArgument(r6, "Duplicate character: %s", r5);
            r1[r5] = (byte) r4;
            r4 = r4 + 1;
            goto L4
        L8:
            r7 = false;
            goto L9
        L14:
            return r1;
        }

        public char encode(int r1) {
            return this.chars[r1];
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof Alphabet) == false) goto L10;
            Alphabet r42 = (Alphabet) r4;
            if (this.ignoreCase != r42.ignoreCase) goto L10;
            if (Arrays.equals(this.chars, r42.chars) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            int r0 = Arrays.hashCode(this.chars);
            if (this.ignoreCase == false) goto L5;
            int r1 = 1231;
        L7:
            return r0 + r1;
        L5:
            r1 = 1237;
            goto L7
        }

        public boolean matches(char r2) {
            byte[] r1 = this.decodabet;
            if (r2 < r1.length) goto L5;
            return false;
        L5:
            if (r1[r2] == (-1)) goto L10;
            return true;
        L10:
            return false;
        }

        public String toString() {
            return this.name;
        }
    }

    public static final class Base16Encoding extends StandardBaseEncoding {
        final char[] encoding;

        private Base16Encoding(Alphabet r5) {
            super(r5, null);
            this.encoding = new char[512];
            int r2 = 0;
            if (Alphabet.access$000(r5).length != 16) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0);
        L8:
            if (r2 >= 256) goto L10;
            this.encoding[r2] = r5.encode(r2 >>> 4);
            this.encoding[r2 | 256] = r5.encode(r2 & 15);
            r2 = r2 + 1;
            goto L8
        L10:
            return;
        L5:
            r0 = false;
            goto L6
        }

        public Base16Encoding(String r2, String r3) {
            this(new Alphabet(r2, r3.toCharArray()));
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public void encodeTo(Appendable r4, byte[] r5, int r6, int r7) {
            Preconditions.checkNotNull(r4);
            Preconditions.checkPositionIndexes(r6, r6 + r7, r5.length);
            int r0 = 0;
        L3:
            if (r0 >= r7) goto L5;
            int r1 = r5[r6 + r0] & UByte.MAX_VALUE;
            r4.append(this.encoding[r1]);
            r4.append(this.encoding[r1 | 256]);
            r0 = r0 + 1;
            goto L3
        }
    }

    public static final class Base64Encoding extends StandardBaseEncoding {
        private Base64Encoding(Alphabet r1, Character r2) {
            super(r1, r2);
            if (Alphabet.access$000(r1).length != 64) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0);
            return;
        L5:
            r0 = false;
            goto L6
        }

        public Base64Encoding(String r2, String r3, Character r4) {
            this(new Alphabet(r2, r3.toCharArray()), r4);
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public void encodeTo(Appendable r5, byte[] r6, int r7, int r8) {
            Preconditions.checkNotNull(r5);
            int r0 = r7 + r8;
            Preconditions.checkPositionIndexes(r7, r0, r6.length);
        L4:
            if (r8 < 3) goto L6;
            int r3 = r7 + 2;
            int r1 = ((r6[r7 + 1] & UByte.MAX_VALUE) << 8) | ((r6[r7] & UByte.MAX_VALUE) << 16);
            r7 = r7 + 3;
            int r12 = r1 | (r6[r3] & UByte.MAX_VALUE);
            r5.append(this.alphabet.encode(r12 >>> 18));
            r5.append(this.alphabet.encode((r12 >>> 12) & 63));
            r5.append(this.alphabet.encode((r12 >>> 6) & 63));
            r5.append(this.alphabet.encode(r12 & 63));
            r8 = r8 - 3;
            goto L4
        L6:
            if (r7 >= r0) goto L10;
            encodeChunkTo(r5, r6, r7, r0 - r7);
            return;
        }
    }

    public static class StandardBaseEncoding extends BaseEncoding {
        final Alphabet alphabet;
        final Character paddingChar;

        public StandardBaseEncoding(Alphabet r2, Character r3) {
            this.alphabet = (Alphabet) Preconditions.checkNotNull(r2);
            if (r3 != null) goto L5;
        L8:
            boolean r22 = true;
        L9:
            Preconditions.checkArgument(r22, "Padding character %s was already in alphabet", r3);
            this.paddingChar = r3;
            return;
        L5:
            if (r2.matches(r3.charValue()) == false) goto L8;
            r22 = false;
            goto L9
        }

        public StandardBaseEncoding(String r2, String r3, Character r4) {
            this(new Alphabet(r2, r3.toCharArray()), r4);
        }

        public void encodeChunkTo(Appendable r8, byte[] r9, int r10, int r11) {
            Preconditions.checkNotNull(r8);
            Preconditions.checkPositionIndexes(r10, r10 + r11, r9.length);
            int r1 = 0;
            if (r11 > this.alphabet.bytesPerChunk) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0);
            long r2 = 0;
            int r02 = 0;
        L8:
            if (r02 >= r11) goto L10;
            r2 = (r2 | (r9[r10 + r02] & UByte.MAX_VALUE)) << 8;
            r02 = r02 + 1;
            goto L8
        L10:
            int r92 = ((r11 + 1) * 8) - this.alphabet.bitsPerChar;
        L12:
            if (r1 >= (r11 * 8)) goto L15;
            Alphabet r03 = this.alphabet;
            r8.append(r03.encode(((int) (r2 >>> (r92 - r1))) & r03.mask));
            r1 = r1 + this.alphabet.bitsPerChar;
            goto L12
        L15:
            if (this.paddingChar != null) goto L17;
            return;
        L17:
            if (r1 >= (this.alphabet.bytesPerChunk * 8)) goto L23;
            r8.append(this.paddingChar.charValue());
            r1 = r1 + this.alphabet.bitsPerChar;
            goto L17
        L23:
            return;
        L5:
            r0 = false;
            goto L6
        }

        @Override // com.google.common.io.BaseEncoding
        public void encodeTo(Appendable r5, byte[] r6, int r7, int r8) {
            Preconditions.checkNotNull(r5);
            Preconditions.checkPositionIndexes(r7, r7 + r8, r6.length);
            int r0 = 0;
        L3:
            if (r0 >= r8) goto L5;
            encodeChunkTo(r5, r6, r7 + r0, Math.min(this.alphabet.bytesPerChunk, r8 - r0));
            r0 = r0 + this.alphabet.bytesPerChunk;
            goto L3
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof StandardBaseEncoding) == false) goto L10;
            StandardBaseEncoding r42 = (StandardBaseEncoding) r4;
            if (this.alphabet.equals(r42.alphabet) == false) goto L10;
            if (Objects.equals(this.paddingChar, r42.paddingChar) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            int r0 = this.alphabet.hashCode();
            return Objects.hashCode(this.paddingChar) ^ r0;
        }

        @Override // com.google.common.io.BaseEncoding
        public int maxEncodedSize(int r3) {
            Alphabet r2 = this.alphabet;
            return r2.charsPerChunk * IntMath.divide(r3, r2.bytesPerChunk, RoundingMode.CEILING);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("BaseEncoding.");
            r0.append(this.alphabet);
            if ((8 % this.alphabet.bitsPerChar) == 0) goto L9;
            if (this.paddingChar != null) goto L7;
            r0.append(".omitPadding()");
            goto L9
        L7:
            r0.append(".withPadChar('");
            r0.append(this.paddingChar);
            r0.append("')");
        L9:
            return r0.toString();
        }
    }

    static {
        Character r1 = Character.valueOf(SignatureVisitor.INSTANCEOF);
        BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", r1);
        BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", r1);
        BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", r1);
        BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", r1);
        BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");
    }

    public BaseEncoding() {
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public String encode(byte[] r3) {
        return encode(r3, 0, r3.length);
    }

    public final String encode(byte[] r3, int r4, int r5) {
        Preconditions.checkPositionIndexes(r4, r4 + r5, r3.length);
        StringBuilder r0 = new StringBuilder(maxEncodedSize(r5));
        encodeTo(r0, r3, r4, r5);     // Catch: IOException -> L6
        return r0.toString();
    L6:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    public abstract void encodeTo(Appendable r1, byte[] r2, int r3, int r4);

    public abstract int maxEncodedSize(int r1);
}
