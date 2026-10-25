package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\"\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nH\u0086\u0080\u0004\u001a\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r*\u00020\u0002H\u0086\u0080\u0004\u001aC\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f*\u00020\u00022\u0018\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0011\u0012\u0004\u0012\u0002H\u000f0\nH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0012\u001a\u000e\u0010\u0013\u001a\u00020\u0014*\u00020\u000bH\u0087\u0088\u0004\u001a\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0016\u001a\u00020\u000b*\u00020\u0002H\u0086\u0080\u0004\u001a \u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\b\u001a\u0018\u0010\u0016\u001a\u00020\u000b*\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0087\u0088\u0004\u001a\u000e\u0010\u001d\u001a\u00020\u001e*\u00020\u001aH\u0086\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "forEachLine", _UrlKt.FRAGMENT_ENCODE_SET, "action", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "readLines", _UrlKt.FRAGMENT_ENCODE_SET, "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "reader", "Ljava/io/StringReader;", "lineSequence", "readText", "copyTo", _UrlKt.FRAGMENT_ENCODE_SET, "out", "Ljava/net/URL;", "charset", "Ljava/nio/charset/Charset;", "readBytes", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "TextStreamsKt")
@SourceDebugExtension({"SMAP\nReadWrite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,166:1\n66#1:167\n1#2:168\n1#2:171\n1342#3,2:169\n*S KotlinDebug\n*F\n+ 1 ReadWrite.kt\nkotlin/io/TextStreamsKt\n*L\n43#1:167\n43#1:168\n43#1:169,2\n*E\n"})
/* loaded from: classes5.dex */
public final class TextStreamsKt {
    public static Unit $r8$lambda$3AhJ2UCBpcTyB1jZJdphp_uKhXo(ArrayList r0, String r1) {
        r0.add(r1);
        return Unit.INSTANCE;
    }

    @InlineOnly
    private static final BufferedReader buffered(Reader r1, int r2) {
        if ((r1 instanceof BufferedReader) == false) goto L7;
        return (BufferedReader) r1;
    L7:
        return new BufferedReader(r1, r2);
    }

    @InlineOnly
    private static final BufferedWriter buffered(Writer r1, int r2) {
        if ((r1 instanceof BufferedWriter) == false) goto L7;
        return (BufferedWriter) r1;
    L7:
        return new BufferedWriter(r1, r2);
    }

    public static /* synthetic */ BufferedReader buffered$default(Reader r0, int r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = 8192;
    L6:
        if ((r0 instanceof BufferedReader) == false) goto L10;
        return (BufferedReader) r0;
    L10:
        return new BufferedReader(r0, r1);
    }

    public static /* synthetic */ BufferedWriter buffered$default(Writer r0, int r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = 8192;
    L6:
        if ((r0 instanceof BufferedWriter) == false) goto L10;
        return (BufferedWriter) r0;
    L10:
        return new BufferedWriter(r0, r1);
    }

    @IgnorableReturnValue
    public static final long copyTo(Reader r5, Writer r6, int r7) {
        char[] r72 = new char[r7];
        int r0 = r5.read(r72);
        long r1 = 0;
    L3:
        if (r0 < 0) goto L5;
        r6.write(r72, 0, r0);
        r1 = r1 + r0;
        r0 = r5.read(r72);
        goto L3
    L5:
        return r1;
    }

    public static /* synthetic */ long copyTo$default(Reader r0, Writer r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 8192;
    L6:
        return copyTo(r0, r1, r2);
    }

    public static final void forEachLine(Reader r2, Function1<? super String, Unit> r3) {
        if ((r2 instanceof BufferedReader) == false) goto L5;
        BufferedReader r22 = (BufferedReader) r2;
    L19:
        Iterator<String> r0 = lineSequence(r22).iterator();     // Catch: Throwable -> L10
    L8:
        if (r0.hasNext() == false) goto L12;
        r3.invoke(r0.next());     // Catch: Throwable -> L10
        goto L8
    L12:
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L10
        CloseableKt.closeFinally(r22, null);
        return;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        CloseableKt.closeFinally(r22, th);
        throw th;
    L5:
        r22 = new BufferedReader(r2, 8192);
        goto L19
    }

    public static final Sequence<String> lineSequence(BufferedReader r1) {
        return SequencesKt.constrainOnce(new LinesSequence(r1));
    }

    public static final byte[] readBytes(URL r2) {
        InputStream r22 = r2.openStream();
        byte[] r0 = ByteStreamsKt.readBytes(r22);     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r22, null);
        return r0;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r22, th);
        throw th;
    }

    public static final List<String> readLines(Reader r2) {
        ArrayList r0 = new ArrayList();
        forEachLine(r2, new TextStreamsKt$$ExternalSyntheticLambda0(r0));
        return r0;
    }

    public static final String readText(Reader r4) {
        StringWriter r0 = new StringWriter();
        copyTo$default(r4, r0, 0, 2, null);
        return r0.toString();
    }

    @InlineOnly
    private static final String readText(URL r1, Charset r2) {
        return new String(readBytes(r1), r2);
    }

    public static /* synthetic */ String readText$default(URL r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new String(readBytes(r0), r1);
    }

    @InlineOnly
    private static final StringReader reader(String r1) {
        return new StringReader(r1);
    }

    @IgnorableReturnValue
    public static final <T> T useLines(Reader r2, Function1<? super Sequence<String>, ? extends T> r3) {
        if ((r2 instanceof BufferedReader) == false) goto L5;
        BufferedReader r22 = (BufferedReader) r2;
    L15:
        T r32 = r3.invoke(lineSequence(r22));     // Catch: Throwable -> L10
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, null);
        InlineMarker.finallyEnd(1);
        return r32;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, th);
        InlineMarker.finallyEnd(1);
        throw th;
    L5:
        r22 = new BufferedReader(r2, 8192);
        goto L15
    }
}
