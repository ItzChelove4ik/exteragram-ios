package com.android.dx.util;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class TwoColumnOutput {
    private final StringBuffer leftBuf;
    private final IndentingWriter leftColumn;
    private final int leftWidth;
    private final Writer out;
    private final StringBuffer rightBuf;
    private final IndentingWriter rightColumn;

    public TwoColumnOutput(OutputStream r2, int r3, int r4, String r5) {
        this(new OutputStreamWriter(r2), r3, r4, r5);
    }

    public TwoColumnOutput(Writer r4, int r5, int r6, String r7) {
        if (r4 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("out == null");
        throw null;
    L5:
        if (r5 < 1) goto L14;
        if (r6 < 1) goto L12;
        if (r7 == null) goto L10;
        StringWriter r0 = new StringWriter(1000);
        StringWriter r2 = new StringWriter(1000);
        this.out = r4;
        this.leftWidth = r5;
        this.leftBuf = r0.getBuffer();
        this.rightBuf = r2.getBuffer();
        this.leftColumn = new IndentingWriter(r0, r5);
        this.rightColumn = new IndentingWriter(r2, r6, r7);
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("spacer == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline1.m("rightWidth < 1");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline1.m("leftWidth < 1");
        throw null;
    }

    private static void appendNewlineIfNecessary(StringBuffer r1, Writer r2) {
        int r0 = r1.length();
        if (r0 != 0) goto L5;
        return;
    L5:
        if (r1.charAt(r0 - 1) == '\n') goto L9;
        r2.write(10);
        return;
    }

    private void flushLeft() {
        appendNewlineIfNecessary(this.leftBuf, this.leftColumn);
    L4:
        if (this.leftBuf.length() == 0) goto L6;
        this.rightColumn.write(10);
        outputFullLines();
        goto L4
    }

    private void flushRight() {
        appendNewlineIfNecessary(this.rightBuf, this.rightColumn);
    L4:
        if (this.rightBuf.length() == 0) goto L6;
        this.leftColumn.write(10);
        outputFullLines();
        goto L4
    }

    private void outputFullLines() {
    L2:
        int r0 = this.leftBuf.indexOf("\n");
        if (r0 < 0) goto L7;
        int r1 = this.rightBuf.indexOf("\n");
        if (r1 < 0) goto L18;
        if (r0 == 0) goto L11;
        this.out.write(this.leftBuf.substring(0, r0));
    L11:
        if (r1 == 0) goto L13;
        writeSpaces(this.out, this.leftWidth - r0);
        this.out.write(this.rightBuf.substring(0, r1));
    L13:
        this.out.write(10);
        this.leftBuf.delete(0, r0 + 1);
        this.rightBuf.delete(0, r1 + 1);
        goto L2
    L18:
        return;
    }

    public static String toString(String r3, int r4, String r5, String r6, int r7) {
        StringWriter r2 = new StringWriter((r3.length() + r6.length()) * 3);
        TwoColumnOutput r0 = new TwoColumnOutput(r2, r4, r7, r5);
        r0.getLeft().write(r3);     // Catch: IOException -> L6
        r0.getRight().write(r6);     // Catch: IOException -> L6
        r0.flush();
        return r2.toString();
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("shouldn't happen", e);
        return null;
    }

    private static void writeSpaces(Writer r1, int r2) {
    L2:
        if (r2 <= 0) goto L4;
        r1.write(32);
        r2 = r2 - 1;
        goto L2
    }

    public void flush() {
        appendNewlineIfNecessary(this.leftBuf, this.leftColumn);     // Catch: IOException -> L4
        appendNewlineIfNecessary(this.rightBuf, this.rightColumn);     // Catch: IOException -> L4
        outputFullLines();     // Catch: IOException -> L4
        flushLeft();     // Catch: IOException -> L4
        flushRight();     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public Writer getLeft() {
        return this.leftColumn;
    }

    public Writer getRight() {
        return this.rightColumn;
    }
}
