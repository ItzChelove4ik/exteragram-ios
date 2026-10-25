package org.mvel2.util;

import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public class StringAppender implements CharSequence {
    private static final int DEFAULT_SIZE = 15;
    private byte[] btr;
    private int capacity;
    private String encoding;
    private int size;
    private char[] str;

    public StringAppender() {
        this.size = 0;
        this.capacity = 15;
        this.str = new char[15];
    }

    public StringAppender(char r3) {
        this.size = 0;
        this.capacity = 15;
        char[] r1 = new char[15];
        this.str = r1;
        r1[0] = r3;
    }

    public StringAppender(int r2) {
        this.size = 0;
        this.capacity = r2;
        this.str = new char[r2];
    }

    public StringAppender(int r2, String r3) {
        this.size = 0;
        this.capacity = r2;
        this.str = new char[r2];
        this.encoding = r3;
    }

    public StringAppender(CharSequence r4) {
        int r0 = 0;
        this.size = 0;
        int r1 = r4.length();
        this.size = r1;
        this.capacity = r1;
        this.str = new char[r1];
    L3:
        char[] r12 = this.str;
        if (r0 >= r12.length) goto L6;
        r12[r0] = r4.charAt(r0);
        r0 = r0 + 1;
        goto L3
    }

    public StringAppender(String r2) {
        this.size = 0;
        char[] r22 = r2.toCharArray();
        this.str = r22;
        int r23 = r22.length;
        this.size = r23;
        this.capacity = r23;
    }

    public StringAppender(char[] r2) {
        this.size = 0;
        this.str = r2;
        int r22 = r2.length;
        this.size = r22;
        this.capacity = r22;
    }

    private void grow(int r4) {
        if (this.capacity != 0) goto L5;
        this.capacity = 15;
    L5:
        int r0 = this.capacity + (r4 * 2);
        this.capacity = r0;
        char[] r42 = new char[r0];
        System.arraycopy(this.str, 0, r42, 0, this.size);
        this.str = r42;
    }

    private void growByte(int r4) {
        int r0 = this.capacity + r4;
        this.capacity = r0;
        byte[] r42 = new byte[r0];
        System.arraycopy(this.btr, 0, r42, 0, this.size);
        this.btr = r42;
    }

    public StringAppender append(byte r4) {
        if (this.btr != null) goto L5;
        this.capacity = 15;
        this.btr = new byte[15];
    L5:
        int r0 = this.size;
        if (r0 < this.capacity) goto L8;
        growByte(r0 * 2);
    L8:
        byte[] r02 = this.btr;
        int r1 = this.size;
        this.size = r1 + 1;
        r02[r1] = r4;
        return this;
    }

    public StringAppender append(char r4) {
        int r0 = this.size;
        if (r0 < this.capacity) goto L5;
        grow(r0);
    L5:
        char[] r02 = this.str;
        int r1 = this.size;
        this.size = r1 + 1;
        r02[r1] = r4;
        return this;
    }

    public StringAppender append(CharSequence r5) {
        if (r5.length() <= (this.capacity - this.size)) goto L5;
        grow(r5.length());
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r5.length()) goto L9;
        this.str[this.size] = r5.charAt(r0);
        this.size++;
        r0 = r0 + 1;
        goto L7
    L9:
        return this;
    }

    public StringAppender append(Object r1) {
        return append(String.valueOf(r1));
    }

    public StringAppender append(String r5) {
        if (r5 != null) goto L4;
        return this;
    L4:
        int r0 = r5.length();
        if (r0 <= (this.capacity - this.size)) goto L7;
        grow(r0);
    L7:
        r5.getChars(0, r0, this.str, this.size);
        this.size += r0;
        return this;
    }

    public StringAppender append(byte[] r5) {
        if (r5.length <= (this.capacity - this.size)) goto L5;
        grow(r5.length);
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r5.length) goto L9;
        char[] r1 = this.str;
        int r2 = this.size;
        int r3 = r0 + 1;
        r1[r2] = (char) r5[r0];
        this.size = r2 + 1;
        r0 = r3;
        goto L7
    L9:
        return this;
    }

    public StringAppender append(byte[] r4, int r5, int r6) {
        if (r6 <= (this.capacity - this.size)) goto L5;
        grow(r6);
    L5:
        int r62 = r6 + r5;
    L6:
        if (r5 >= r62) goto L8;
        char[] r0 = this.str;
        int r1 = this.size;
        this.size = r1 + 1;
        r0[r1] = (char) r4[r5];
        r5 = r5 + 1;
        goto L6
    L8:
        return this;
    }

    public StringAppender append(char[] r5) {
        if (r5.length <= (this.capacity - this.size)) goto L5;
        grow(r5.length);
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r5.length) goto L9;
        char[] r1 = this.str;
        int r2 = this.size;
        int r3 = r0 + 1;
        r1[r2] = r5[r0];
        this.size = r2 + 1;
        r0 = r3;
        goto L7
    L9:
        return this;
    }

    public StringAppender append(char[] r4, int r5, int r6) {
        if (r6 <= (this.capacity - this.size)) goto L5;
        grow(r6);
    L5:
        int r62 = r6 + r5;
    L6:
        if (r5 >= r62) goto L8;
        char[] r0 = this.str;
        int r1 = this.size;
        this.size = r1 + 1;
        r0[r1] = r4[r5];
        r5 = r5 + 1;
        goto L6
    L8:
        return this;
    }

    @Override // java.lang.CharSequence
    public char charAt(int r1) {
        return this.str[r1];
    }

    public void getChars(int r3, int r4, char[] r5, int r6) {
    L2:
        if (r3 >= r4) goto L4;
        r5[r6] = this.str[r3];
        r3 = r3 + 1;
        r6 = r6 + 1;
        goto L2
    }

    public char[] getChars(int r3, int r4) {
        char[] r0 = new char[r4];
        System.arraycopy(this.str, r3, r0, 0, r4);
        return r0;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.size;
    }

    public void reset() {
        this.size = 0;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int r1, int r2) {
        return substring(r1, r2);
    }

    public CharSequence substring(int r2, int r3) {
        return new String(this.str, r2, r3 - r2);
    }

    public char[] toChars() {
        if (this.btr != null) goto L5;
        int r0 = this.size;
        char[] r1 = new char[r0];
        System.arraycopy(this.str, 0, r1, 0, r0);
        return r1;
    L5:
        if (this.encoding != null) goto L14;
        this.encoding = System.getProperty("file.encoding");
    L14:
        String r02 = new String(this.btr, this.encoding);     // Catch: UnsupportedEncodingException -> L9
    L11:
        return r02.toCharArray();
    L9:
        r02 = new String(this.btr);
        goto L11
    }

    @Override // java.lang.CharSequence
    public String toString() {
        if (this.btr != null) goto L5;
        int r0 = this.size;
        int r2 = this.capacity;
        char[] r5 = this.str;
        if (r0 != r2) goto L16;
        return new String(r5);
    L16:
        return new String(r5, 0, r0);
    L5:
        if (this.encoding != null) goto L17;
        this.encoding = System.getProperty("file.encoding");
    L17:
        return new String(this.btr, 0, this.size, this.encoding);
    L10:
        return new String(this.btr, 0, this.size);
    }
}
