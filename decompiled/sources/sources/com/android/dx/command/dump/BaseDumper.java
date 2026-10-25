package com.android.dx.command.dump;

import com.android.dx.cf.code.ConcreteMethod;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.dex.DexOptions;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import com.android.dx.util.IndentingWriter;
import com.android.dx.util.TwoColumnOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class BaseDumper implements ParseObserver {
    protected Args args;
    private final byte[] bytes;
    protected final DexOptions dexOptions;
    private final String filePath;
    private final int hexCols;
    private int indent;
    private final PrintStream out;
    private final boolean rawBytes;
    private int readBytes;
    private String separator;
    private final boolean strictParse;
    private final int width;

    public BaseDumper(byte[] r1, PrintStream r2, String r3, Args r4) {
        this.bytes = r1;
        boolean r12 = r4.rawBytes;
        this.rawBytes = r12;
        this.out = r2;
        int r22 = r4.width;
        if (r22 > 0) goto L5;
        r22 = 79;
    L5:
        this.width = r22;
        this.filePath = r3;
        this.strictParse = r4.strictParse;
        this.indent = 0;
        if (r12 == false) goto L8;
        String r13 = "|";
    L9:
        this.separator = r13;
        this.readBytes = 0;
        this.args = r4;
        this.dexOptions = new DexOptions();
        int r14 = (((r22 - 5) / 15) + 1) & (-2);
        int r23 = 6;
        if (r14 >= 6) goto L12;
    L11:
        r14 = r23;
    L15:
        this.hexCols = r14;
        return;
    L12:
        r23 = 10;
        if (r14 <= 10) goto L15;
    L8:
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    public static int computeParamWidth(ConcreteMethod r0, boolean r1) {
        return r0.getEffectiveDescriptor().getParameterTypes().getWordCount();
    }

    @Override // com.android.dx.cf.iface.ParseObserver
    public void changeIndent(int r3) {
        this.indent += r3;
        if (this.rawBytes == false) goto L5;
        String r32 = "|";
    L6:
        this.separator = r32;
        int r33 = 0;
    L8:
        if (r33 >= this.indent) goto L10;
        this.separator += "  ";
        r33 = r33 + 1;
        goto L8
    L10:
        return;
    L5:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    @Override // com.android.dx.cf.iface.ParseObserver
    public void endParsingMember(ByteArray r1, int r2, String r3, String r4, Member r5) {
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final boolean getRawBytes() {
        return this.rawBytes;
    }

    public final int getReadBytes() {
        return this.readBytes;
    }

    public final boolean getStrictParse() {
        return this.strictParse;
    }

    public final int getWidth1() {
        if (this.rawBytes == false) goto L6;
        int r1 = this.hexCols;
        return ((r1 * 2) + 5) + (r1 / 2);
    L6:
        return 0;
    }

    public final int getWidth2() {
        if (this.rawBytes == false) goto L5;
        int r0 = getWidth1() + 1;
    L7:
        return (this.width - r0) - (this.indent * 2);
    L5:
        r0 = 0;
        goto L7
    }

    public final String hexDump(int r7, int r8) {
        return Hex.dump(this.bytes, r7, r8, r7, this.hexCols, 4);
    }

    @Override // com.android.dx.cf.iface.ParseObserver
    public void parsed(ByteArray r1, int r2, int r3, String r4) {
        int r12 = r1.underlyingOffset(r2);
        if (getRawBytes() == false) goto L5;
        String r13 = hexDump(r12, r3);
    L6:
        print(twoColumns(r13, r4));
        this.readBytes += r3;
        return;
    L5:
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public final void print(String r1) {
        this.out.print(r1);
    }

    public final void println(String r1) {
        this.out.println(r1);
    }

    @Override // com.android.dx.cf.iface.ParseObserver
    public void startParsingMember(ByteArray r1, int r2, String r3, String r4) {
    }

    public final String twoColumns(String r4, String r5) {
        int r0 = getWidth1();
        int r1 = getWidth2();
        if (r0 != 0) goto L12;
        int r42 = r5.length();     // Catch: IOException -> L13
        StringWriter r02 = new StringWriter(r42 * 2);     // Catch: IOException -> L13
        IndentingWriter r2 = new IndentingWriter(r02, r1, this.separator);     // Catch: IOException -> L13
        r2.write(r5);     // Catch: IOException -> L13
        if (r42 != 0) goto L7;
    L8:
        r2.write(10);     // Catch: IOException -> L13
    L9:
        r2.flush();     // Catch: IOException -> L13
        return r02.toString();
    L7:
        if (r5.charAt(r42 - 1) == '\n') goto L9;
    L12:
        return TwoColumnOutput.toString(r4, r0, this.separator, r5, r1);
    L13:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }
}
