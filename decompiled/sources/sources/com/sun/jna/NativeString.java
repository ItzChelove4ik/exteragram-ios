package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
class NativeString implements CharSequence, Comparable {
    static final String WIDE_STRING = "--WIDE-STRING--";
    private String encoding;
    private Pointer pointer;

    public class StringMemory extends Memory {
        final /* synthetic */ NativeString this$0;

        public StringMemory(NativeString r1, long r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // com.sun.jna.Memory, com.sun.jna.Pointer
        public String toString() {
            return this.this$0.toString();
        }
    }

    public NativeString(WString r2) {
        this(r2.toString(), WIDE_STRING);
    }

    public NativeString(String r2) {
        this(r2, Native.getDefaultStringEncoding());
    }

    public NativeString(String r10, String r11) {
        if (r10 == null) goto L10;
        this.encoding = r11;
        if (WIDE_STRING.equals(r11) == false) goto L8;
        StringMemory r0 = new StringMemory(this, (r10.length() + 1) * Native.WCHAR_SIZE);
        this.pointer = r0;
        r0.setWideString(0, r10);
        return;
    L8:
        byte[] r6 = Native.getBytes(r10, r11);
        StringMemory r3 = new StringMemory(this, r6.length + 1);
        this.pointer = r3;
        r3.write(0, r6, 0, r6.length);
        this.pointer.setByte(r6.length, (byte) 0);
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("String must not be null");
        throw null;
    }

    public NativeString(String r1, boolean r2) {
        if (r2 == false) goto L4;
        String r22 = WIDE_STRING;
    L5:
        this(r1, r22);
        return;
    L4:
        r22 = Native.getDefaultStringEncoding();
        goto L5
    }

    @Override // java.lang.CharSequence
    public char charAt(int r1) {
        return toString().charAt(r1);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object r1) {
        if (r1 != null) goto L6;
        return 1;
    L6:
        return toString().compareTo(r1.toString());
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof CharSequence) == true) goto L5;
    L8:
        return false;
    L5:
        if (compareTo(r3) != 0) goto L8;
        return true;
    }

    public Pointer getPointer() {
        return this.pointer;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // java.lang.CharSequence
    public int length() {
        return toString().length();
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int r1, int r2) {
        return toString().subSequence(r1, r2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        boolean r0 = WIDE_STRING.equals(this.encoding);
        Pointer r1 = this.pointer;
        if (r0 == false) goto L7;
        return r1.getWideString(0);
    L7:
        return r1.getString(0, this.encoding);
    }
}
