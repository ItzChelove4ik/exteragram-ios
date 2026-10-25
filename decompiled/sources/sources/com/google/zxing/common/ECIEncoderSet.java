package com.google.zxing.common;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class ECIEncoderSet {
    private static final List<CharsetEncoder> ENCODERS = null;
    private final CharsetEncoder[] encoders;
    private final int priorityEncoderIndex;

    static {
        ENCODERS = new ArrayList();
        String[] r0 = {"IBM437", "ISO-8859-2", "ISO-8859-3", "ISO-8859-4", "ISO-8859-5", "ISO-8859-6", "ISO-8859-7", "ISO-8859-8", "ISO-8859-9", "ISO-8859-10", "ISO-8859-11", "ISO-8859-13", "ISO-8859-14", "ISO-8859-15", "ISO-8859-16", "windows-1250", "windows-1251", "windows-1252", "windows-1256", "Shift_JIS"};
        int r1 = 0;
    L4:
        if (r1 >= 20) goto L9;
        String r2 = r0[r1];
        if (CharacterSetECI.getCharacterSetECIByName(r2) == null) goto L8;
        ENCODERS.add(Charset.forName(r2).newEncoder());     // Catch: UnsupportedCharsetException -> L10
    L8:
        r1 = r1 + 1;
        goto L4
    }

    public ECIEncoderSet(String r10, Charset r11, int r12) {
        ArrayList r0 = new ArrayList();
        r0.add(StandardCharsets.ISO_8859_1.newEncoder());
        int r1 = 0;
        if (r11 != null) goto L5;
    L7:
        boolean r3 = false;
    L8:
        int r4 = 0;
    L10:
        if (r4 >= r10.length()) goto L30;
        int r5 = r0.size();
        int r6 = 0;
    L12:
        if (r6 >= r5) goto L18;
        Object r7 = r0.get(r6);
        r6 = r6 + 1;
        CharsetEncoder r72 = (CharsetEncoder) r7;
        char r8 = r10.charAt(r4);
        if (r8 == r12) goto L17;
        if (r72.canEncode(r8) == false) goto L12;
    L17:
        boolean r52 = true;
    L19:
        if (r52 == true) goto L26;
        Iterator<CharsetEncoder> r62 = ENCODERS.iterator();
    L22:
        if (r62.hasNext() == false) goto L26;
        CharsetEncoder r73 = r62.next();
        if (r73.canEncode(r10.charAt(r4)) == false) goto L22;
        r0.add(r73);
        r52 = true;
    L26:
        if (r52 == true) goto L28;
        r3 = true;
    L28:
        r4 = r4 + 1;
        goto L10
    L18:
        r52 = false;
        goto L19
    L30:
        if (r0.size() != 1) goto L33;
        if (r3 == true) goto L33;
        this.encoders = new CharsetEncoder[]{(CharsetEncoder) r0.get(0)};
    L37:
        if (r11 == null) goto L46;
    L38:
        CharsetEncoder[] r102 = this.encoders;
        if (r1 >= r102.length) goto L46;
        if (r102[r1] == null) goto L45;
        if (r11.name().equals(this.encoders[r1].charset().name()) == false) goto L45;
    L47:
        this.priorityEncoderIndex = r1;
        return;
    L45:
        r1 = r1 + 1;
    L46:
        r1 = -1;
    L33:
        this.encoders = new CharsetEncoder[r0.size() + 2];
        int r103 = r0.size();
        int r122 = 0;
        int r32 = 0;
    L34:
        if (r32 >= r103) goto L36;
        Object r42 = r0.get(r32);
        r32 = r32 + 1;
        this.encoders[r122] = (CharsetEncoder) r42;
        r122 = r122 + 1;
        goto L34
    L36:
        this.encoders[r122] = StandardCharsets.UTF_8.newEncoder();
        this.encoders[r122 + 1] = StandardCharsets.UTF_16BE.newEncoder();
        goto L37
    L5:
        if (r11.name().startsWith("UTF") == false) goto L7;
        r3 = true;
        goto L8
    }

    public boolean canEncode(char r2, int r3) {
        return this.encoders[r3].canEncode(_UrlKt.FRAGMENT_ENCODE_SET + r2);
    }

    public byte[] encode(String r1, int r2) {
        return r1.getBytes(this.encoders[r2].charset());
    }

    public Charset getCharset(int r1) {
        return this.encoders[r1].charset();
    }

    public int getECIValue(int r1) {
        return CharacterSetECI.getCharacterSetECI(this.encoders[r1].charset()).getValue();
    }

    public int getPriorityEncoderIndex() {
        return this.priorityEncoderIndex;
    }

    public int length() {
        return this.encoders.length;
    }
}
