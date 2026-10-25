package com.android.dx.util;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.io.FilterWriter;
import java.io.Writer;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class IndentingWriter extends FilterWriter {
    private boolean collectingIndent;
    private int column;
    private int indent;
    private final int maxIndent;
    private final String prefix;
    private final int width;

    public IndentingWriter(Writer r2, int r3) {
        this(r2, r3, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public IndentingWriter(Writer r2, int r3, String r4) {
        super(r2);
        if (r2 == null) goto L18;
        if (r3 < 0) goto L16;
        if (r4 == null) goto L14;
        if (r3 == 0) goto L8;
        int r22 = r3;
    L9:
        this.width = r22;
        this.maxIndent = r3 >> 1;
        if (r4.length() != 0) goto L12;
        r4 = null;
    L12:
        this.prefix = r4;
        bol();
        return;
    L8:
        r22 = Integer.MAX_VALUE;
        goto L9
    L14:
        f$$ExternalSyntheticBUOutline2.m("prefix == null");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline1.m("width < 0");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("out == null");
        throw null;
    }

    private void bol() {
        this.column = 0;
        if (this.maxIndent == 0) goto L5;
        boolean r1 = true;
    L6:
        this.collectingIndent = r1;
        this.indent = 0;
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int r7) {
        Object r0 = ((FilterWriter) this).lock;
        monitor-enter(r0);
        int r3 = 0;
        if (this.collectingIndent == false) goto L14;
        if (r7 != 32) goto L12;
        int r1 = this.indent + 1;     // Catch: Throwable -> L10
        this.indent = r1;     // Catch: Throwable -> L10
        int r4 = this.maxIndent;     // Catch: Throwable -> L10
        if (r1 < r4) goto L14;
        this.indent = r4;     // Catch: Throwable -> L10
        this.collectingIndent = false;     // Catch: Throwable -> L10
        goto L14
    L12:
        this.collectingIndent = false;     // Catch: Throwable -> L10
    L14:
        if (this.column != this.width) goto L18;
        if (r7 == 10) goto L18;
        ((FilterWriter) this).out.write(10);     // Catch: Throwable -> L10
        this.column = 0;     // Catch: Throwable -> L10
    L18:
        if (this.column != 0) goto L28;
        String r12 = this.prefix;     // Catch: Throwable -> L10
        if (r12 == null) goto L23;
        ((FilterWriter) this).out.write(r12);     // Catch: Throwable -> L10
    L23:
        if (this.collectingIndent == true) goto L28;
    L24:
        int r13 = this.indent;     // Catch: Throwable -> L10
        if (r3 >= r13) goto L27;
        ((FilterWriter) this).out.write(32);     // Catch: Throwable -> L10
        r3 = r3 + 1;     // Catch: Throwable -> L10
        goto L24
    L27:
        this.column = r13;     // Catch: Throwable -> L10
    L28:
        ((FilterWriter) this).out.write(r7);     // Catch: Throwable -> L10
        if (r7 != 10) goto L31;
        bol();     // Catch: Throwable -> L10
    L32:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L31:
        this.column++;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String r3, int r4, int r5) {
        Object r0 = ((FilterWriter) this).lock;
        monitor-enter(r0);
    L4:
        if (r5 <= 0) goto L8;
        write(r3.charAt(r4));     // Catch: Throwable -> L6
        r4 = r4 + 1;     // Catch: Throwable -> L6
        r5 = r5 - 1;
    L6:
        th = move-exception;
        throw th;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L6
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] r3, int r4, int r5) {
        Object r0 = ((FilterWriter) this).lock;
        monitor-enter(r0);
    L4:
        if (r5 <= 0) goto L8;
        write(r3[r4]);     // Catch: Throwable -> L6
        r4 = r4 + 1;     // Catch: Throwable -> L6
        r5 = r5 - 1;
    L6:
        th = move-exception;
        throw th;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L6
    }
}
