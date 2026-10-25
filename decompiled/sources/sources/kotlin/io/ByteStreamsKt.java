package kotlin.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.collections.ByteIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0082\u0004\u001a\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\b\u001a\u00020\u0004*\u00020\tH\u0087\u0088\u0004\u001a\u001e\u0010\b\u001a\u00020\u0004*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0087\u0088\u0004\u001a\u0018\u0010\r\u001a\u00020\u0002*\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000bH\u0087\u0088\u0004\u001a\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u000e2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u000e2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u0018\u0010\r\u001a\u00020\u0014*\u00020\u00152\b\b\u0002\u0010\u000f\u001a\u00020\u000bH\u0087\u0088\u0004\u001a\u0018\u0010\u0016\u001a\u00020\u0017*\u00020\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u0018\u0010\u0018\u001a\u00020\u0019*\u00020\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004\u001a \u0010\u001a\u001a\u00020\u001b*\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010\u000f\u001a\u00020\u000bH\u0087\u0080\b\u001a\u0018\u0010\u001d\u001a\u00020\t*\u00020\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\u000bH\u0087\u0080\u0004\u001a\u000e\u0010\u001d\u001a\u00020\t*\u00020\u000eH\u0087\u0080\u0004¨\u0006\u001f"}, d2 = {"iterator", "Lkotlin/collections/ByteIterator;", "Ljava/io/BufferedInputStream;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", _UrlKt.FRAGMENT_ENCODE_SET, "charset", "Ljava/nio/charset/Charset;", "inputStream", _UrlKt.FRAGMENT_ENCODE_SET, "offset", _UrlKt.FRAGMENT_ENCODE_SET, "length", "buffered", "Ljava/io/InputStream;", "bufferSize", "reader", "Ljava/io/InputStreamReader;", "bufferedReader", "Ljava/io/BufferedReader;", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "writer", "Ljava/io/OutputStreamWriter;", "bufferedWriter", "Ljava/io/BufferedWriter;", "copyTo", _UrlKt.FRAGMENT_ENCODE_SET, "out", "readBytes", "estimatedSize", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "ByteStreamsKt")
/* loaded from: classes.dex */
public final class ByteStreamsKt {

    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0011\u001a\u00020\u0012H\u0082\u0080\u0004J\n\u0010\u0013\u001a\u00020\tH\u0096\u0082\u0004J\n\u0010\u0002\u001a\u00020\u0014H\u0096\u0080\u0004R\u001b\u0010\u0002\u001a\u00020\u0003X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\tX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\tX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\r¨\u0006\u0015"}, d2 = {"kotlin/io/ByteStreamsKt$iterator$1", "Lkotlin/collections/ByteIterator;", "nextByte", _UrlKt.FRAGMENT_ENCODE_SET, "getNextByte", "()I", "setNextByte", "(I)V", "nextPrepared", _UrlKt.FRAGMENT_ENCODE_SET, "getNextPrepared", "()Z", "setNextPrepared", "(Z)V", "finished", "getFinished", "setFinished", "prepareNext", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.io.ByteStreamsKt$iterator$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 extends ByteIterator {
        final /* synthetic */ BufferedInputStream $this_iterator;
        private boolean finished;
        private int nextByte;
        private boolean nextPrepared;

        public AnonymousClass1(BufferedInputStream r1) {
            this.$this_iterator = r1;
            this.nextByte = -1;
        }

        private final void prepareNext() {
            if (this.nextPrepared == false) goto L5;
            return;
        L5:
            if (this.finished == true) goto L13;
            int r0 = this.$this_iterator.read();
            this.nextByte = r0;
            boolean r1 = true;
            this.nextPrepared = true;
            if (r0 == (-1)) goto L10;
            r1 = false;
        L10:
            this.finished = r1;
            return;
        }

        public final boolean getFinished() {
            return this.finished;
        }

        public final int getNextByte() {
            return this.nextByte;
        }

        public final boolean getNextPrepared() {
            return this.nextPrepared;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            prepareNext();
            return !this.finished;
        }

        @Override // kotlin.collections.ByteIterator
        public byte nextByte() {
            prepareNext();
            if (this.finished == true) goto L6;
            byte r0 = (byte) this.nextByte;
            this.nextPrepared = false;
            return r0;
        L6:
            UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("Input stream is over.");
            return 0;
        }

        public final void setFinished(boolean r1) {
            this.finished = r1;
        }

        public final void setNextByte(int r1) {
            this.nextByte = r1;
        }

        public final void setNextPrepared(boolean r1) {
            this.nextPrepared = r1;
        }
    }

    @InlineOnly
    private static final BufferedInputStream buffered(InputStream r1, int r2) {
        if ((r1 instanceof BufferedInputStream) == false) goto L7;
        return (BufferedInputStream) r1;
    L7:
        return new BufferedInputStream(r1, r2);
    }

    @InlineOnly
    private static final BufferedOutputStream buffered(OutputStream r1, int r2) {
        if ((r1 instanceof BufferedOutputStream) == false) goto L7;
        return (BufferedOutputStream) r1;
    L7:
        return new BufferedOutputStream(r1, r2);
    }

    public static /* synthetic */ BufferedInputStream buffered$default(InputStream r0, int r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = 8192;
    L6:
        if ((r0 instanceof BufferedInputStream) == false) goto L10;
        return (BufferedInputStream) r0;
    L10:
        return new BufferedInputStream(r0, r1);
    }

    public static /* synthetic */ BufferedOutputStream buffered$default(OutputStream r0, int r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = 8192;
    L6:
        if ((r0 instanceof BufferedOutputStream) == false) goto L10;
        return (BufferedOutputStream) r0;
    L10:
        return new BufferedOutputStream(r0, r1);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(InputStream r1, Charset r2) {
        return new BufferedReader(new InputStreamReader(r1, r2), 8192);
    }

    public static /* synthetic */ BufferedReader bufferedReader$default(InputStream r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new BufferedReader(new InputStreamReader(r0, r1), 8192);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(OutputStream r1, Charset r2) {
        return new BufferedWriter(new OutputStreamWriter(r1, r2), 8192);
    }

    public static /* synthetic */ BufferedWriter bufferedWriter$default(OutputStream r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new BufferedWriter(new OutputStreamWriter(r0, r1), 8192);
    }

    @InlineOnly
    private static final ByteArrayInputStream byteInputStream(String r1, Charset r2) {
        return new ByteArrayInputStream(r1.getBytes(r2));
    }

    public static /* synthetic */ ByteArrayInputStream byteInputStream$default(String r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new ByteArrayInputStream(r0.getBytes(r1));
    }

    @IgnorableReturnValue
    public static final long copyTo(InputStream r5, OutputStream r6, int r7) {
        byte[] r72 = new byte[r7];
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

    public static /* synthetic */ long copyTo$default(InputStream r0, OutputStream r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 8192;
    L6:
        return copyTo(r0, r1, r2);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] r1) {
        return new ByteArrayInputStream(r1);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] r1, int r2, int r3) {
        return new ByteArrayInputStream(r1, r2, r3);
    }

    public static final ByteIterator iterator(BufferedInputStream r1) {
        return new AnonymousClass1(r1);
    }

    @SinceKotlin(version = "1.3")
    public static final byte[] readBytes(InputStream r4) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream(Math.max(8192, r4.available()));
        copyTo$default(r4, r0, 0, 2, null);
        return r0.toByteArray();
    }

    @Deprecated(message = "Use readBytes() overload without estimatedSize parameter", replaceWith = @ReplaceWith(expression = "readBytes()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = MVEL.VERSION, warningSince = "1.3")
    public static final /* synthetic */ byte[] readBytes(InputStream r3, int r4) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream(Math.max(r4, r3.available()));
        copyTo$default(r3, r0, 0, 2, null);
        return r0.toByteArray();
    }

    public static /* synthetic */ byte[] readBytes$default(InputStream r0, int r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = 8192;
    L6:
        return readBytes(r0, r1);
    }

    @InlineOnly
    private static final InputStreamReader reader(InputStream r1, Charset r2) {
        return new InputStreamReader(r1, r2);
    }

    public static /* synthetic */ InputStreamReader reader$default(InputStream r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new InputStreamReader(r0, r1);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(OutputStream r1, Charset r2) {
        return new OutputStreamWriter(r1, r2);
    }

    public static /* synthetic */ OutputStreamWriter writer$default(OutputStream r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return new OutputStreamWriter(r0, r1);
    }
}
