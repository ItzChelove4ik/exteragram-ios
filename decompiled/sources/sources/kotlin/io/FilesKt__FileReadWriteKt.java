package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\"\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0087\u0088\u0004\u001a\u0018\u0010\t\u001a\u00020\n*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\"\u0010\u000b\u001a\u00020\f*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0087\u0088\u0004\u001a\u0018\u0010\r\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0086\u0080\u0004\u001a\u0016\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0010H\u0086\u0080\u0004\u001a\u0016\u0010\u0014\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0010H\u0086\u0080\u0004\u001a\u0018\u0010\u0015\u001a\u00020\u0016*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a \u0010\u0017\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a \u0010\u0019\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u001e\u0010\u001a\u001a\u00020\u0012*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\u0080\u0004\u001a\u0016\u0010\u001c\u001a\n \u001e*\u0004\u0018\u00010\u001d0\u001d*\u00020\u0004H\u0080\u0080\u0004\u001a\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u001dH\u0080\u0080\u0004\u001aF\u0010#\u001a\u00020\u0012*\u00020\u000226\u0010$\u001a2\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\b¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u00120%H\u0086\u0080\u0004\u001aN\u0010#\u001a\u00020\u0012*\u00020\u00022\u0006\u0010*\u001a\u00020\b26\u0010$\u001a2\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\b¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u00120%H\u0086\u0080\u0004\u001a;\u0010+\u001a\u00020\u0012*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00120,H\u0086\u0080\u0004\u001a\u000e\u0010.\u001a\u00020/*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u00100\u001a\u000201*\u00020\u0002H\u0087\u0088\u0004\u001a\u001e\u00102\u001a\b\u0012\u0004\u0012\u00020\u001603*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001aM\u00104\u001a\u0002H5\"\u0004\b\u0000\u00105*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0018\u00106\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001607\u0012\u0004\u0012\u0002H50,H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u00108\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00069"}, d2 = {"reader", "Ljava/io/InputStreamReader;", "Ljava/io/File;", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", _UrlKt.FRAGMENT_ENCODE_SET, "writer", "Ljava/io/OutputStreamWriter;", "bufferedWriter", "Ljava/io/BufferedWriter;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", _UrlKt.FRAGMENT_ENCODE_SET, "writeBytes", _UrlKt.FRAGMENT_ENCODE_SET, "array", "appendBytes", "readText", _UrlKt.FRAGMENT_ENCODE_SET, "writeText", "text", "appendText", "writeTextImpl", "Ljava/io/OutputStream;", "newReplaceEncoder", "Ljava/nio/charset/CharsetEncoder;", "kotlin.jvm.PlatformType", "byteBufferForEncoding", "Ljava/nio/ByteBuffer;", "chunkSize", "encoder", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "readLines", _UrlKt.FRAGMENT_ENCODE_SET, "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/FilesKt")
@SourceDebugExtension({"SMAP\nFileReadWrite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileReadWrite.kt\nkotlin/io/FilesKt__FileReadWriteKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,294:1\n1#2:295\n*E\n"})
/* loaded from: classes.dex */
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    public static /* synthetic */ Unit $r8$lambda$hPJfgTK5s9M7mflij1tos8f0N40(ArrayList r0, String r1) {
        return readLines$lambda$0$FilesKt__FileReadWriteKt(r0, r1);
    }

    public FilesKt__FileReadWriteKt() {
    }

    public static final void appendBytes(File r2, byte[] r3) {
        FileOutputStream r0 = new FileOutputStream(r2, true);
        r0.write(r3);     // Catch: Throwable -> L6
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r0, null);
        return;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    public static final void appendText(File r2, String r3, Charset r4) {
        FileOutputStream r0 = new FileOutputStream(r2, true);
        writeTextImpl(r0, r3, r4);     // Catch: Throwable -> L6
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r0, null);
        return;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    public static /* synthetic */ void appendText$default(File r0, String r1, Charset r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = Charsets.UTF_8;
    L5:
        appendText(r0, r1, r2);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(File r2, Charset r3, int r4) {
        return new BufferedReader(new InputStreamReader(new FileInputStream(r2), r3), r4);
    }

    public static /* synthetic */ BufferedReader bufferedReader$default(File r0, Charset r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = 8192;
    L9:
        return new BufferedReader(new InputStreamReader(new FileInputStream(r0), r1), r2);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(File r2, Charset r3, int r4) {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(r2), r3), r4);
    }

    public static /* synthetic */ BufferedWriter bufferedWriter$default(File r0, Charset r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = 8192;
    L9:
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(r0), r1), r2);
    }

    public static ByteBuffer byteBufferForEncoding(int r2, CharsetEncoder r3) {
        return ByteBuffer.allocate(r2 * ((int) Math.ceil(r3.maxBytesPerChar())));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [byte[], java.lang.Object] */
    public static final void forEachBlock(File r1, int r2, Function2<? super byte[], ? super Integer, Unit> r3) {
        ?? r22 = new byte[RangesKt.coerceAtLeast(r2, 512)];
        FileInputStream r0 = new FileInputStream(r1);
    L18:
        int r12 = r0.read(r22);     // Catch: Throwable -> L8
        if (r12 <= 0) goto L5;
        r3.invoke(r22, Integer.valueOf(r12));     // Catch: Throwable -> L8
        goto L18
    L5:
        Unit r13 = Unit.INSTANCE;     // Catch: Throwable -> L8
        CloseableKt.closeFinally(r0, null);
        return;
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    public static final void forEachBlock(File r1, Function2<? super byte[], ? super Integer, Unit> r2) {
        forEachBlock(r1, 4096, r2);
    }

    public static final void forEachLine(File r3, Charset r4, Function1<? super String, Unit> r5) {
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream(r3), r4)), r5);
    }

    public static /* synthetic */ void forEachLine$default(File r0, Charset r1, Function1 r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L5;
        r1 = Charsets.UTF_8;
    L5:
        forEachLine(r0, r1, r2);
    }

    @InlineOnly
    private static final FileInputStream inputStream(File r1) {
        return new FileInputStream(r1);
    }

    public static CharsetEncoder newReplaceEncoder(Charset r1) {
        CharsetEncoder r12 = r1.newEncoder();
        CodingErrorAction r0 = CodingErrorAction.REPLACE;
        return r12.onMalformedInput(r0).onUnmappableCharacter(r0);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(File r1) {
        return new FileOutputStream(r1);
    }

    @InlineOnly
    private static final PrintWriter printWriter(File r3, Charset r4) {
        return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(r3), r4), 8192));
    }

    public static /* synthetic */ PrintWriter printWriter$default(File r1, Charset r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r2 = Charsets.UTF_8;
    L6:
        return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(r1), r2), 8192));
    }

    public static byte[] readBytes(File r9) {
        FileInputStream r0 = new FileInputStream(r9);
        long r1 = r9.length();     // Catch: Throwable -> L12
        if (r1 > 2147483647L) goto L28;
        int r12 = (int) r1;
        byte[] r2 = new byte[r12];     // Catch: Throwable -> L12
        int r5 = r12;
        int r6 = 0;
    L8:
        if (r5 <= 0) goto L15;
        int r7 = r0.read(r2, r6, r5);     // Catch: Throwable -> L12
        if (r7 < 0) goto L15;
        r5 = r5 - r7;     // Catch: Throwable -> L12
        r6 = r6 + r7;     // Catch: Throwable -> L12
    L15:
        if (r5 <= 0) goto L17;
        r2 = Arrays.copyOf(r2, r6);     // Catch: Throwable -> L12
    L23:
        CloseableKt.closeFinally(r0, null);
        return r2;
    L17:
        int r52 = r0.read();     // Catch: Throwable -> L12
        if (r52 == (-1)) goto L23;
        ExposingBufferByteArrayOutputStream r62 = new ExposingBufferByteArrayOutputStream(8193);     // Catch: Throwable -> L12
        r62.write(r52);     // Catch: Throwable -> L12
        ByteStreamsKt.copyTo$default(r0, r62, 0, 2, null);     // Catch: Throwable -> L12
        int r53 = r62.size() + r12;     // Catch: Throwable -> L12
        if (r53 < 0) goto L26;
        r2 = ArraysKt.copyInto(r62.getBuffer(), Arrays.copyOf(r2, r53), r12, 0, r62.size());     // Catch: Throwable -> L12
        goto L23
    L26:
        throw new OutOfMemoryError("File " + r9 + " is too big to fit in memory.");     // Catch: Throwable -> L12
    L28:
        throw new OutOfMemoryError("File " + r9 + " is too big (" + r1 + " bytes) to fit in memory.");     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L30
    L30:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    public static final List<String> readLines(File r2, Charset r3) {
        ArrayList r0 = new ArrayList();
        forEachLine(r2, r3, new FilesKt__FileReadWriteKt$$ExternalSyntheticLambda0(r0));
        return r0;
    }

    public static /* synthetic */ List readLines$default(File r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return readLines(r0, r1);
    }

    private static final Unit readLines$lambda$0$FilesKt__FileReadWriteKt(ArrayList r0, String r1) {
        r0.add(r1);
        return Unit.INSTANCE;
    }

    public static String readText(File r2, Charset r3) {
        InputStreamReader r0 = new InputStreamReader(new FileInputStream(r2), r3);
        String r22 = TextStreamsKt.readText(r0);     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r0, null);
        return r22;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    public static /* synthetic */ String readText$default(File r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return readText(r0, r1);
    }

    @InlineOnly
    private static final InputStreamReader reader(File r2, Charset r3) {
        return new InputStreamReader(new FileInputStream(r2), r3);
    }

    public static /* synthetic */ InputStreamReader reader$default(File r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new InputStreamReader(new FileInputStream(r0), r1);
    }

    public static final <T> T useLines(File r2, Charset r3, Function1<? super Sequence<String>, ? extends T> r4) {
        BufferedReader r22 = new BufferedReader(new InputStreamReader(new FileInputStream(r2), r3), 8192);
        T r42 = r4.invoke(TextStreamsKt.lineSequence(r22));     // Catch: Throwable -> L6
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, null);
        InlineMarker.finallyEnd(1);
        return r42;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public static /* synthetic */ Object useLines$default(File r1, Charset r2, Function1 r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L5;
        r2 = Charsets.UTF_8;
    L5:
        BufferedReader r12 = new BufferedReader(new InputStreamReader(new FileInputStream(r1), r2), 8192);
        Object r22 = r3.invoke(TextStreamsKt.lineSequence(r12));     // Catch: Throwable -> L9
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r12, null);
        InlineMarker.finallyEnd(1);
        return r22;
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r12, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public static void writeBytes(File r1, byte[] r2) {
        FileOutputStream r0 = new FileOutputStream(r1);
        r0.write(r2);     // Catch: Throwable -> L6
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r0, null);
        return;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    public static final void writeText(File r1, String r2, Charset r3) {
        FileOutputStream r0 = new FileOutputStream(r1);
        writeTextImpl(r0, r2, r3);     // Catch: Throwable -> L6
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r0, null);
        return;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    public static /* synthetic */ void writeText$default(File r0, String r1, Charset r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = Charsets.UTF_8;
    L5:
        writeText(r0, r1, r2);
    }

    public static void writeTextImpl(OutputStream r8, String r9, Charset r10) {
        if (r9.length() >= 16384) goto L6;
        r8.write(r9.getBytes(r10));
        return;
    L6:
        CharsetEncoder r102 = newReplaceEncoder(r10);
        CharBuffer r1 = CharBuffer.allocate(8192);
        ByteBuffer r0 = byteBufferForEncoding(8192, r102);
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r3 >= r9.length()) goto L26;
        int r5 = Math.min(8192 - r4, r9.length() - r3);
        int r6 = r3 + r5;
        r9.getChars(r3, r6, r1.array(), r4);
        r1.limit(r5 + r4);
        r4 = 1;
        if (r6 != r9.length()) goto L12;
        boolean r32 = true;
    L14:
        if (r102.encode(r1, r0, r32).isUnderflow() == false) goto L20;
        r8.write(r0.array(), 0, r0.position());
        if (r1.position() == r1.limit()) goto L18;
        r1.put(0, r1.get());
    L19:
        r1.clear();
        r0.clear();
        r3 = r6;
        goto L8
    L18:
        r4 = 0;
        goto L19
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L12:
        r32 = false;
        goto L14
    }

    @InlineOnly
    private static final OutputStreamWriter writer(File r2, Charset r3) {
        return new OutputStreamWriter(new FileOutputStream(r2), r3);
    }

    public static /* synthetic */ OutputStreamWriter writer$default(File r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new OutputStreamWriter(new FileOutputStream(r0), r1);
    }
}
