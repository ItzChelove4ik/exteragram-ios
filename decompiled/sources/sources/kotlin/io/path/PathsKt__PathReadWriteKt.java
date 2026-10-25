package kotlin.io.path;

import j$.nio.file.Files;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.StandardOpenOption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\b\u001a2\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0007\u0010\b\u001a<\u0010\f\u001a\u00020\u000b*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b\f\u0010\r\u001a2\u0010\u000f\u001a\u00020\u000e*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b\u000f\u0010\u0010\u001a<\u0010\u0012\u001a\u00020\u0011*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0014\u0010\u0015\u001a\u00020\u0014*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0016\u001a0\u0010\u0019\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00142\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001c\u0010\u001b\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001d\u0010\u001e\u001a\u00020\u001d*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a9\u0010\"\u001a\u00020\u0018*\u00020\u00002\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0007¢\u0006\u0004\b\"\u0010#\u001a%\u0010$\u001a\u00020\u0018*\u00020\u00002\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b$\u0010%\u001aD\u0010+\u001a\u00020\u0018*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u00180&H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a(\u0010.\u001a\u00020-*\u00020\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b.\u0010/\u001a(\u00101\u001a\u000200*\u00020\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b1\u00102\u001a$\u00104\u001a\b\u0012\u0004\u0012\u00020\u001d03*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b4\u00105\u001aN\u00109\u001a\u00028\u0000\"\u0004\b\u0000\u00106*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0018\u00108\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d07\u0012\u0004\u0012\u00028\u00000&H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b9\u0010:\u001a@\u0010=\u001a\u00020\u0000*\u00020\u00002\f\u0010<\u001a\b\u0012\u0004\u0012\u00020 0;2\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b=\u0010>\u001a@\u0010=\u001a\u00020\u0000*\u00020\u00002\f\u0010<\u001a\b\u0012\u0004\u0012\u00020 072\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004H\u0087\b¢\u0006\u0004\b=\u0010?\u001a,\u0010@\u001a\u00020\u0000*\u00020\u00002\f\u0010<\u001a\b\u0012\u0004\u0012\u00020 0;2\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b@\u0010A\u001a,\u0010@\u001a\u00020\u0000*\u00020\u00002\f\u0010<\u001a\b\u0012\u0004\u0012\u00020 072\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b@\u0010B\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006C"}, d2 = {"j$/nio/file/Path", "Ljava/nio/charset/Charset;", "charset", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/OpenOption", "options", "Ljava/io/InputStreamReader;", "reader", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;[Lj$/nio/file/OpenOption;)Ljava/io/InputStreamReader;", _UrlKt.FRAGMENT_ENCODE_SET, "bufferSize", "Ljava/io/BufferedReader;", "bufferedReader", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;I[Lj$/nio/file/OpenOption;)Ljava/io/BufferedReader;", "Ljava/io/OutputStreamWriter;", "writer", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;[Lj$/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "Ljava/io/BufferedWriter;", "bufferedWriter", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;I[Lj$/nio/file/OpenOption;)Ljava/io/BufferedWriter;", _UrlKt.FRAGMENT_ENCODE_SET, "readBytes", "(Lj$/nio/file/Path;)[B", "array", _UrlKt.FRAGMENT_ENCODE_SET, "writeBytes", "(Lj$/nio/file/Path;[B[Lj$/nio/file/OpenOption;)V", "appendBytes", "(Lj$/nio/file/Path;[B)V", _UrlKt.FRAGMENT_ENCODE_SET, "readText", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "text", "writeText", "(Lj$/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Lj$/nio/file/OpenOption;)V", "appendText", "(Lj$/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "line", "action", "forEachLine", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)V", "Ljava/io/InputStream;", "inputStream", "(Lj$/nio/file/Path;[Lj$/nio/file/OpenOption;)Ljava/io/InputStream;", "Ljava/io/OutputStream;", "outputStream", "(Lj$/nio/file/Path;[Lj$/nio/file/OpenOption;)Ljava/io/OutputStream;", _UrlKt.FRAGMENT_ENCODE_SET, "readLines", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;)Ljava/util/List;", "T", "Lkotlin/sequences/Sequence;", "block", "useLines", "(Lj$/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "lines", "writeLines", "(Lj$/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Lj$/nio/file/OpenOption;)Lj$/nio/file/Path;", "(Lj$/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Lj$/nio/file/OpenOption;)Lj$/nio/file/Path;", "appendLines", "(Lj$/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;)Lj$/nio/file/Path;", "(Lj$/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;)Lj$/nio/file/Path;", "kotlin-stdlib-jdk7"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
@SourceDebugExtension({"SMAP\nPathReadWrite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,338:1\n1#2:339\n1#2:341\n66#3:340\n1342#4,2:342\n*S KotlinDebug\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n*L\n212#1:341\n212#1:340\n212#1:342,2\n*E\n"})
/* loaded from: classes5.dex */
class PathsKt__PathReadWriteKt {
    public PathsKt__PathReadWriteKt() {
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final void appendBytes(Path r3, byte[] r4) {
        Files.write(r3, r4, new OpenOption[]{StandardOpenOption.APPEND});
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path appendLines(Path r3, Iterable<? extends CharSequence> r4, Charset r5) {
        return Files.write(r3, r4, r5, new OpenOption[]{StandardOpenOption.APPEND});
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path appendLines(Path r3, Sequence<? extends CharSequence> r4, Charset r5) {
        return Files.write(r3, SequencesKt.asIterable(r4), r5, new OpenOption[]{StandardOpenOption.APPEND});
    }

    public static /* synthetic */ Path appendLines$default(Path r1, Iterable r2, Charset r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r3 = Charsets.UTF_8;
    L6:
        return Files.write(r1, r2, r3, new OpenOption[]{StandardOpenOption.APPEND});
    }

    public static /* synthetic */ Path appendLines$default(Path r1, Sequence r2, Charset r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r3 = Charsets.UTF_8;
    L6:
        return Files.write(r1, SequencesKt.asIterable(r2), r3, new OpenOption[]{StandardOpenOption.APPEND});
    }

    @SinceKotlin(version = "1.5")
    public static final void appendText(Path r3, CharSequence r4, Charset r5) {
        writeText(r3, r4, r5, new OpenOption[]{StandardOpenOption.APPEND});
    }

    public static /* synthetic */ void appendText$default(Path r0, CharSequence r1, Charset r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = Charsets.UTF_8;
    L5:
        appendText(r0, r1, r2);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final BufferedReader bufferedReader(Path r3, Charset r4, int r5, OpenOption... r6) {
        return new BufferedReader(new InputStreamReader(Files.newInputStream(r3, (OpenOption[]) Arrays.copyOf(r6, r6.length)), r4), r5);
    }

    public static /* synthetic */ BufferedReader bufferedReader$default(Path r1, Charset r2, int r3, OpenOption[] r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r2 = Charsets.UTF_8;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r3 = 8192;
    L9:
        return new BufferedReader(new InputStreamReader(Files.newInputStream(r1, (OpenOption[]) Arrays.copyOf(r4, r4.length)), r2), r3);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final BufferedWriter bufferedWriter(Path r3, Charset r4, int r5, OpenOption... r6) {
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(r3, (OpenOption[]) Arrays.copyOf(r6, r6.length)), r4), r5);
    }

    public static /* synthetic */ BufferedWriter bufferedWriter$default(Path r1, Charset r2, int r3, OpenOption[] r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r2 = Charsets.UTF_8;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r3 = 8192;
    L9:
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(r1, (OpenOption[]) Arrays.copyOf(r4, r4.length)), r2), r3);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final void forEachLine(Path r2, Charset r3, Function1<? super String, Unit> r4) {
        BufferedReader r22 = Files.newBufferedReader(r2, r3);
        Iterator<String> r0 = TextStreamsKt.lineSequence(r22).iterator();     // Catch: Throwable -> L7
    L5:
        if (r0.hasNext() == false) goto L9;
        r4.invoke(r0.next());     // Catch: Throwable -> L7
        goto L5
    L9:
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L7
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, null);
        InlineMarker.finallyEnd(1);
        return;
    L7:
        th = move-exception;
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public static /* synthetic */ void forEachLine$default(Path r0, Charset r1, Function1 r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L5;
        r1 = Charsets.UTF_8;
    L5:
        BufferedReader r02 = Files.newBufferedReader(r0, r1);
        Iterator<String> r12 = TextStreamsKt.lineSequence(r02).iterator();     // Catch: Throwable -> L10
    L8:
        if (r12.hasNext() == false) goto L12;
        r2.invoke(r12.next());     // Catch: Throwable -> L10
        goto L8
    L12:
        Unit r13 = Unit.INSTANCE;     // Catch: Throwable -> L10
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, null);
        InlineMarker.finallyEnd(1);
        return;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final InputStream inputStream(Path r1, OpenOption... r2) {
        return Files.newInputStream(r1, (OpenOption[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final OutputStream outputStream(Path r1, OpenOption... r2) {
        return Files.newOutputStream(r1, (OpenOption[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final byte[] readBytes(Path r0) {
        return Files.readAllBytes(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final List<String> readLines(Path r0, Charset r1) {
        return Files.readAllLines(r0, r1);
    }

    public static /* synthetic */ List readLines$default(Path r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return Files.readAllLines(r0, r1);
    }

    @SinceKotlin(version = "1.5")
    public static final String readText(Path r3, Charset r4) {
        InputStreamReader r2 = new InputStreamReader(Files.newInputStream(r3, (OpenOption[]) Arrays.copyOf(new OpenOption[0], 0)), r4);
        String r32 = TextStreamsKt.readText(r2);     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r2, null);
        return r32;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r2, th);
        throw th;
    }

    public static /* synthetic */ String readText$default(Path r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return readText(r0, r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final InputStreamReader reader(Path r2, Charset r3, OpenOption... r4) {
        return new InputStreamReader(Files.newInputStream(r2, (OpenOption[]) Arrays.copyOf(r4, r4.length)), r3);
    }

    public static /* synthetic */ InputStreamReader reader$default(Path r0, Charset r1, OpenOption[] r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new InputStreamReader(Files.newInputStream(r0, (OpenOption[]) Arrays.copyOf(r2, r2.length)), r1);
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T useLines(Path r1, Charset r2, Function1<? super Sequence<String>, ? extends T> r3) {
        BufferedReader r12 = Files.newBufferedReader(r1, r2);
        T r32 = r3.invoke(TextStreamsKt.lineSequence(r12));     // Catch: Throwable -> L6
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r12, null);
        InlineMarker.finallyEnd(1);
        return r32;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r12, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public static /* synthetic */ Object useLines$default(Path r0, Charset r1, Function1 r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L5;
        r1 = Charsets.UTF_8;
    L5:
        BufferedReader r02 = Files.newBufferedReader(r0, r1);
        Object r12 = r2.invoke(TextStreamsKt.lineSequence(r02));     // Catch: Throwable -> L9
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, null);
        InlineMarker.finallyEnd(1);
        return r12;
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final void writeBytes(Path r1, byte[] r2, OpenOption... r3) {
        Files.write(r1, r2, (OpenOption[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path writeLines(Path r1, Iterable<? extends CharSequence> r2, Charset r3, OpenOption... r4) {
        return Files.write(r1, r2, r3, (OpenOption[]) Arrays.copyOf(r4, r4.length));
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path writeLines(Path r1, Sequence<? extends CharSequence> r2, Charset r3, OpenOption... r4) {
        return Files.write(r1, SequencesKt.asIterable(r2), r3, (OpenOption[]) Arrays.copyOf(r4, r4.length));
    }

    public static /* synthetic */ Path writeLines$default(Path r0, Iterable r1, Charset r2, OpenOption[] r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = Charsets.UTF_8;
    L6:
        return Files.write(r0, r1, r2, (OpenOption[]) Arrays.copyOf(r3, r3.length));
    }

    public static /* synthetic */ Path writeLines$default(Path r0, Sequence r1, Charset r2, OpenOption[] r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = Charsets.UTF_8;
    L6:
        return Files.write(r0, SequencesKt.asIterable(r1), r2, (OpenOption[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    public static final void writeText(Path r3, CharSequence r4, Charset r5, OpenOption... r6) {
        OutputStream r32 = Files.newOutputStream(r3, (OpenOption[]) Arrays.copyOf(r6, r6.length));
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        CloseableKt.closeFinally(r32, th);
        throw th;
    L4:
        if ((r4 instanceof String) == false) goto L8;
        FilesKt.writeTextImpl(r32, (String) r4, r5);     // Catch: Throwable -> L6
    L20:
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r32, null);
        return;
    L8:
        CharsetEncoder r52 = FilesKt.newReplaceEncoder(r5);     // Catch: Throwable -> L6
        if ((r4 instanceof CharBuffer) == false) goto L11;
        CharBuffer r62 = ((CharBuffer) r4).asReadOnlyBuffer();     // Catch: Throwable -> L6
    L12:
        ByteBuffer r43 = FilesKt.byteBufferForEncoding(Math.min(r4.length(), 8192), r52);     // Catch: Throwable -> L6
    L14:
        if (r62.hasRemaining() == false) goto L20;
        if (r52.encode(r62, r43, true).isError() == true) goto L19;
        r32.write(r43.array(), 0, r43.position());     // Catch: Throwable -> L6
        r43.clear();     // Catch: Throwable -> L6
        goto L14
    L19:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L6
    L11:
        r62 = CharBuffer.wrap(r4);     // Catch: Throwable -> L6
        goto L12
    }

    public static /* synthetic */ void writeText$default(Path r0, CharSequence r1, Charset r2, OpenOption[] r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L5;
        r2 = Charsets.UTF_8;
    L5:
        writeText(r0, r1, r2, r3);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final OutputStreamWriter writer(Path r2, Charset r3, OpenOption... r4) {
        return new OutputStreamWriter(Files.newOutputStream(r2, (OpenOption[]) Arrays.copyOf(r4, r4.length)), r3);
    }

    public static /* synthetic */ OutputStreamWriter writer$default(Path r0, Charset r1, OpenOption[] r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new OutputStreamWriter(Files.newOutputStream(r0, (OpenOption[]) Arrays.copyOf(r2, r2.length)), r1);
    }
}
