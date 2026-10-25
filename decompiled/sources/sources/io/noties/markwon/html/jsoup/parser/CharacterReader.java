package io.noties.markwon.html.jsoup.parser;

import io.noties.markwon.html.jsoup.UncheckedIOException;
import io.noties.markwon.html.jsoup.helper.Validate;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.CharCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class CharacterReader {
    private int bufLength;
    private int bufMark;
    private int bufPos;
    private int bufSplitPoint;
    private final char[] charBuf;
    private final Reader reader;
    private int readerPos;
    private final String[] stringCache;

    public CharacterReader(Reader r1, int r2) {
        this.stringCache = new String[128];
        Validate.notNull(r1);
        Validate.isTrue(r1.markSupported());
        this.reader = r1;
        this.charBuf = new char[4096];
        bufferUp();
    }

    public CharacterReader(String r2) {
        this(new StringReader(r2), r2.length());
    }

    private void bufferUp() {
        int r0 = this.bufPos;
        if (r0 < this.bufSplitPoint) goto L18;
        this.reader.skip(r0);     // Catch: IOException -> L12
        this.reader.mark(4096);     // Catch: IOException -> L12
        int r02 = this.reader.read(this.charBuf);     // Catch: IOException -> L12
        this.reader.reset();     // Catch: IOException -> L12
        if (r02 == (-1)) goto L17;
        this.bufLength = r02;     // Catch: IOException -> L12
        this.readerPos += this.bufPos;
        this.bufPos = 0;     // Catch: IOException -> L12
        this.bufMark = 0;     // Catch: IOException -> L12
        if (r02 <= 3072) goto L10;
        r02 = 3072;
    L10:
        this.bufSplitPoint = r02;     // Catch: IOException -> L12
        return;
    L17:
        return;
    L12:
        e = move-exception;
        throw new UncheckedIOException(e);
    }

    private static String cacheString(char[] r5, String[] r6, int r7, int r8) {
        if (r8 <= 12) goto L7;
        return new String(r5, r7, r8);
    L7:
        if (r8 >= 1) goto L10;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        int r1 = 0;
        int r3 = r7;
        int r2 = 0;
    L11:
        if (r1 >= r8) goto L13;
        r2 = (r2 * 31) + r5[r3];
        r1 = r1 + 1;
        r3 = r3 + 1;
        goto L11
    L13:
        int r0 = r2 & (r6.length - 1);
        String r12 = r6[r0];
        if (r12 != null) goto L18;
        String r13 = new String(r5, r7, r8);
        r6[r0] = r13;
        return r13;
    L18:
        if (rangeEquals(r5, r7, r8, r12) == false) goto L20;
        return r12;
    L20:
        String r14 = new String(r5, r7, r8);
        r6[r0] = r14;
        return r14;
    }

    private boolean isEmptyNoBufferUp() {
        if (this.bufPos < this.bufLength) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean rangeEquals(char[] r4, int r5, int r6, String r7) {
        if (r6 != r7.length()) goto L13;
        int r0 = 0;
    L5:
        int r2 = r6 - 1;
        if (r6 == 0) goto L11;
        int r62 = r5 + 1;
        int r3 = r0 + 1;
        if (r4[r5] != r7.charAt(r0)) goto L9;
        r5 = r62;
        r6 = r2;
        r0 = r3;
        goto L5
    L9:
        return false;
    L11:
        return true;
    L13:
        return false;
    }

    public void advance() {
        this.bufPos++;
    }

    public char consume() {
        bufferUp();
        if (isEmptyNoBufferUp() == false) goto L5;
        char r0 = CharCompanionObject.MAX_VALUE;
    L6:
        this.bufPos++;
        return r0;
    L5:
        r0 = this.charBuf[this.bufPos];
        goto L6
    }

    public String consumeData() {
        bufferUp();
        int r0 = this.bufPos;
        int r1 = this.bufLength;
        char[] r2 = this.charBuf;
    L3:
        int r3 = this.bufPos;
        if (r3 >= r1) goto L12;
        char r4 = r2[r3];
        if (r4 == '&') goto L12;
        if (r4 == '<') goto L12;
        if (r4 == 0) goto L12;
        this.bufPos = r3 + 1;
    L12:
        if (r3 > r0) goto L14;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        return cacheString(this.charBuf, this.stringCache, r0, r3 - r0);
    }

    public String consumeDigitSequence() {
        bufferUp();
        int r0 = this.bufPos;
    L3:
        int r1 = this.bufPos;
        if (r1 >= this.bufLength) goto L11;
        char r2 = this.charBuf[r1];
        if (r2 < '0') goto L11;
        if (r2 > '9') goto L11;
        this.bufPos = r1 + 1;
    L11:
        return cacheString(this.charBuf, this.stringCache, r0, r1 - r0);
    }

    public String consumeHexSequence() {
        bufferUp();
        int r0 = this.bufPos;
    L3:
        int r1 = this.bufPos;
        if (r1 >= this.bufLength) goto L19;
        char r2 = this.charBuf[r1];
        if (r2 < '0') goto L10;
        if (r2 > '9') goto L10;
    L17:
        this.bufPos = r1 + 1;
    L10:
        if (r2 < 'A') goto L14;
        if (r2 <= 'F') goto L17;
    L14:
        if (r2 < 'a') goto L19;
        if (r2 <= 'f') goto L17;
    L19:
        return cacheString(this.charBuf, this.stringCache, r0, r1 - r0);
    }

    public String consumeLetterSequence() {
        bufferUp();
        int r0 = this.bufPos;
    L3:
        int r1 = this.bufPos;
        if (r1 >= this.bufLength) goto L17;
        char r12 = this.charBuf[r1];
        if (r12 < 'A') goto L10;
        if (r12 > 'Z') goto L10;
    L15:
        this.bufPos++;
    L10:
        if (r12 < 'a') goto L14;
        if (r12 <= 'z') goto L15;
    L14:
        if (Character.isLetter(r12) == true) goto L15;
    L17:
        return cacheString(this.charBuf, this.stringCache, r0, this.bufPos - r0);
    }

    public String consumeLetterThenDigitSequence() {
        bufferUp();
        int r0 = this.bufPos;
    L3:
        int r1 = this.bufPos;
        if (r1 >= this.bufLength) goto L17;
        char r12 = this.charBuf[r1];
        if (r12 < 'A') goto L10;
        if (r12 > 'Z') goto L10;
    L15:
        this.bufPos++;
    L10:
        if (r12 < 'a') goto L14;
        if (r12 <= 'z') goto L15;
    L14:
        if (Character.isLetter(r12) == true) goto L15;
    L17:
        if (isEmptyNoBufferUp() == true) goto L24;
        char[] r13 = this.charBuf;
        int r2 = this.bufPos;
        char r14 = r13[r2];
        if (r14 < '0') goto L24;
        if (r14 > '9') goto L24;
        this.bufPos = r2 + 1;
    L24:
        return cacheString(this.charBuf, this.stringCache, r0, this.bufPos - r0);
    }

    public String consumeTagName() {
        bufferUp();
        int r0 = this.bufPos;
        int r1 = this.bufLength;
        char[] r2 = this.charBuf;
    L3:
        int r3 = this.bufPos;
        if (r3 >= r1) goto L22;
        char r4 = r2[r3];
        if (r4 == '\t') goto L22;
        if (r4 == '\n') goto L22;
        if (r4 == '\r') goto L22;
        if (r4 == '\f') goto L22;
        if (r4 == ' ') goto L22;
        if (r4 == '/') goto L22;
        if (r4 == '>') goto L22;
        if (r4 == 0) goto L22;
        this.bufPos = r3 + 1;
    L22:
        if (r3 > r0) goto L24;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        return cacheString(this.charBuf, this.stringCache, r0, r3 - r0);
    }

    public String consumeTo(char r4) {
        int r42 = nextIndexOf(r4);
        if (r42 == (-1)) goto L7;
        String r0 = cacheString(this.charBuf, this.stringCache, this.bufPos, r42);
        this.bufPos += r42;
        return r0;
    L7:
        return consumeToEnd();
    }

    public String consumeTo(String r4) {
        int r42 = nextIndexOf(r4);
        if (r42 == (-1)) goto L7;
        String r0 = cacheString(this.charBuf, this.stringCache, this.bufPos, r42);
        this.bufPos += r42;
        return r0;
    L7:
        return consumeToEnd();
    }

    public String consumeToAny(char... r8) {
        bufferUp();
        int r0 = this.bufPos;
        int r1 = this.bufLength;
        char[] r2 = this.charBuf;
    L4:
        if (this.bufPos >= r1) goto L12;
        int r3 = r8.length;
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L11;
        if (r2[this.bufPos] == r8[r4]) goto L12;
        r4 = r4 + 1;
        goto L6
    L11:
        this.bufPos++;
    L12:
        int r82 = this.bufPos;
        if (r82 > r0) goto L15;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        return cacheString(this.charBuf, this.stringCache, r0, r82 - r0);
    }

    public String consumeToAnySorted(char... r5) {
        bufferUp();
        int r0 = this.bufPos;
        int r1 = this.bufLength;
        char[] r2 = this.charBuf;
    L3:
        int r3 = this.bufPos;
        if (r3 >= r1) goto L9;
        if (Arrays.binarySearch(r5, r2[r3]) >= 0) goto L9;
        this.bufPos++;
    L9:
        int r52 = this.bufPos;
        if (r52 > r0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return cacheString(this.charBuf, this.stringCache, r0, r52 - r0);
    }

    public String consumeToEnd() {
        bufferUp();
        char[] r0 = this.charBuf;
        String[] r1 = this.stringCache;
        int r2 = this.bufPos;
        String r02 = cacheString(r0, r1, r2, this.bufLength - r2);
        this.bufPos = this.bufLength;
        return r02;
    }

    public boolean containsIgnoreCase(String r3) {
        Locale r0 = Locale.ENGLISH;
        String r1 = r3.toLowerCase(r0);
        String r32 = r3.toUpperCase(r0);
        if (nextIndexOf(r1) <= (-1)) goto L5;
        return true;
    L5:
        if (nextIndexOf(r32) > (-1)) goto L11;
        return false;
    L11:
        return true;
    }

    public char current() {
        bufferUp();
        if (isEmptyNoBufferUp() == false) goto L7;
        return CharCompanionObject.MAX_VALUE;
    L7:
        return this.charBuf[this.bufPos];
    }

    public boolean isEmpty() {
        bufferUp();
        if (this.bufPos < this.bufLength) goto L6;
        return true;
    L6:
        return false;
    }

    public void mark() {
        this.bufMark = this.bufPos;
    }

    public boolean matchConsume(String r2) {
        bufferUp();
        if (matches(r2) == false) goto L6;
        this.bufPos += r2.length();
        return true;
    L6:
        return false;
    }

    public boolean matchConsumeIgnoreCase(String r2) {
        if (matchesIgnoreCase(r2) == false) goto L6;
        this.bufPos += r2.length();
        return true;
    L6:
        return false;
    }

    public boolean matches(char r2) {
        if (isEmpty() == false) goto L5;
        return false;
    L5:
        if (this.charBuf[this.bufPos] != r2) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean matches(String r7) {
        bufferUp();
        int r0 = r7.length();
        if (r0 <= (this.bufLength - this.bufPos)) goto L5;
        return false;
    L5:
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L11;
        if (r7.charAt(r1) != this.charBuf[this.bufPos + r1]) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    public boolean matchesAny(char... r5) {
        if (isEmpty() == false) goto L5;
        return false;
    L5:
        bufferUp();
        char r4 = this.charBuf[this.bufPos];
        int r0 = r5.length;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L12;
        if (r5[r2] == r4) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public boolean matchesAnySorted(char[] r2) {
        bufferUp();
        if (isEmpty() == false) goto L5;
        return false;
    L5:
        if (Arrays.binarySearch(r2, this.charBuf[this.bufPos]) < 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean matchesDigit() {
        if (isEmpty() == false) goto L5;
        return false;
    L5:
        char r2 = this.charBuf[this.bufPos];
        if (r2 >= '0') goto L8;
    L11:
        return false;
    L8:
        if (r2 > '9') goto L11;
        return true;
    }

    public boolean matchesIgnoreCase(String r7) {
        bufferUp();
        int r0 = r7.length();
        if (r0 <= (this.bufLength - this.bufPos)) goto L5;
        return false;
    L5:
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L11;
        if (Character.toUpperCase(r7.charAt(r1)) != Character.toUpperCase(this.charBuf[this.bufPos + r1])) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    public boolean matchesLetter() {
        if (isEmpty() == false) goto L5;
        return false;
    L5:
        char r2 = this.charBuf[this.bufPos];
        if (r2 < 'A') goto L10;
        if (r2 > 'Z') goto L10;
        return true;
    L10:
        if (r2 < 'a') goto L14;
        if (r2 > 'z') goto L14;
        return true;
    L14:
        if (Character.isLetter(r2) == true) goto L19;
        return false;
    L19:
        return true;
    }

    public int nextIndexOf(char r3) {
        bufferUp();
        int r0 = this.bufPos;
    L4:
        if (r0 >= this.bufLength) goto L10;
        if (r3 == this.charBuf[r0]) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r0 - this.bufPos;
    L10:
        return -1;
    }

    public int nextIndexOf(CharSequence r9) {
        bufferUp();
        char r0 = r9.charAt(0);
        int r1 = this.bufPos;
    L4:
        if (r1 >= this.bufLength) goto L24;
        int r3 = 1;
        if (r0 == this.charBuf[r1]) goto L12;
    L7:
        r1 = r1 + 1;
        if (r1 >= this.bufLength) goto L12;
        if (r0 != this.charBuf[r1]) goto L7;
    L12:
        int r2 = r1 + 1;
        int r4 = (r9.length() + r2) - 1;
        int r5 = this.bufLength;
        if (r1 >= r5) goto L23;
        if (r4 > r5) goto L23;
        int r52 = r2;
    L16:
        if (r52 >= r4) goto L20;
        if (r9.charAt(r3) != this.charBuf[r52]) goto L20;
        r52 = r52 + 1;
        r3 = r3 + 1;
    L20:
        if (r52 != r4) goto L23;
        return r1 - this.bufPos;
    L23:
        r1 = r2;
        goto L4
    L24:
        return -1;
    }

    public int pos() {
        return this.readerPos + this.bufPos;
    }

    public void rewindToMark() {
        this.bufPos = this.bufMark;
    }

    public String toString() {
        char[] r1 = this.charBuf;
        int r2 = this.bufPos;
        return new String(r1, r2, this.bufLength - r2);
    }

    public void unconsume() {
        this.bufPos--;
    }
}
