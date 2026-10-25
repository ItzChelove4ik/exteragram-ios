package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Internal;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\tH&J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\rH&J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011JD\u0010\u0012\u001a\u0002H\u0013\"\b\b\u0000\u0010\u0013*\u00020\u0014*\u00020\u00002\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\u00130\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u00020\u00180\u0016H\u0082\b¢\u0006\u0002\u0010\u0019J\u0006\u0010\u001a\u001a\u00020\u0005J\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lokhttp3/ResponseBody;", "Ljava/io/Closeable;", "<init>", "()V", "reader", "Ljava/io/Reader;", "contentType", "Lokhttp3/MediaType;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "byteStream", "Ljava/io/InputStream;", "source", "Lokio/BufferedSource;", "bytes", _UrlKt.FRAGMENT_ENCODE_SET, "byteString", "Lokio/ByteString;", "consumeSource", "T", _UrlKt.FRAGMENT_ENCODE_SET, "consumer", "Lkotlin/Function1;", "sizeMapper", _UrlKt.FRAGMENT_ENCODE_SET, "(Lokhttp3/ResponseBody;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "charStream", "string", _UrlKt.FRAGMENT_ENCODE_SET, "charset", "Ljava/nio/charset/Charset;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "BomAwareReader", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nResponseBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResponseBody.kt\nokhttp3/ResponseBody\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,343:1\n141#1,6:344\n147#1,5:373\n141#1,6:378\n147#1,5:407\n72#2:350\n58#2,22:351\n72#2:384\n58#2,22:385\n72#2:412\n58#2,22:413\n72#2:435\n58#2,22:436\n*S KotlinDebug\n*F\n+ 1 ResponseBody.kt\nokhttp3/ResponseBody\n*L\n125#1:344,6\n125#1:373,5\n135#1:378,6\n135#1:407,5\n125#1:350\n125#1:351,22\n135#1:384\n135#1:385,22\n146#1:412\n146#1:413,22\n189#1:435\n189#1:436,22\n*E\n"})
/* loaded from: classes.dex */
public abstract class ResponseBody implements Closeable {
    public static final Companion Companion = null;

    @JvmField
    public static final ResponseBody EMPTY = null;
    private Reader reader;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokhttp3/ResponseBody$BomAwareReader;", "Ljava/io/Reader;", "source", "Lokio/BufferedSource;", "charset", "Ljava/nio/charset/Charset;", "<init>", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)V", "closed", _UrlKt.FRAGMENT_ENCODE_SET, "delegate", "read", _UrlKt.FRAGMENT_ENCODE_SET, "cbuf", _UrlKt.FRAGMENT_ENCODE_SET, "off", "len", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nResponseBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResponseBody.kt\nokhttp3/ResponseBody$BomAwareReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,343:1\n1#2:344\n*E\n"})
    public static final class BomAwareReader extends Reader {
        private final Charset charset;
        private boolean closed;
        private Reader delegate;
        private final BufferedSource source;

        public BomAwareReader(BufferedSource r1, Charset r2) {
            this.source = r1;
            this.charset = r2;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.closed = true;
            Reader r0 = this.delegate;
            if (r0 == null) goto L6;
            r0.close();
            return;
        L6:
            this.source.close();
        }

        @Override // java.io.Reader
        public int read(char[] r5, int r6, int r7) {
            if (this.closed == true) goto L9;
            Reader r0 = this.delegate;
            if (r0 != null) goto L8;
            r0 = new InputStreamReader(this.source.inputStream(), _UtilJvmKt.readBomAsCharset(this.source, this.charset));
            this.delegate = r0;
        L8:
            return r0.read(r5, r6, r7);
        L9:
            Model$$ExternalSyntheticBUOutline0.m("Stream closed");
            return 0;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0006\u001a\u00020\u0005*\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ\u001d\u0010\u0006\u001a\u00020\u0005*\u00020\u000b2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ\u001d\u0010\u0006\u001a\u00020\u0005*\u00020\f2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ'\u0010\r\u001a\u00020\u0005*\u00020\u000e2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\b\nJ\u001a\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u0007H\u0007J\u001a\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u000bH\u0007J\u001a\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\fH\u0007J\"\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/ResponseBody$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMPTY", "Lokhttp3/ResponseBody;", "toResponseBody", _UrlKt.FRAGMENT_ENCODE_SET, "contentType", "Lokhttp3/MediaType;", "create", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/ByteString;", "asResponseBody", "Lokio/BufferedSource;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "content", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ ResponseBody create$default(Companion r0, String r1, MediaType r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = null;
        L6:
            return r0.create(r1, r2);
        }

        public static /* synthetic */ ResponseBody create$default(Companion r0, BufferedSource r1, MediaType r2, long r3, int r5, Object r6) {
            if ((r5 & 1) == 0) goto L6;
            r2 = null;
        L6:
            if ((r5 & 2) == 0) goto L9;
            r3 = -1;
        L9:
            return r0.create(r1, r2, r3);
        }

        public static /* synthetic */ ResponseBody create$default(Companion r0, ByteString r1, MediaType r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = null;
        L6:
            return r0.create(r1, r2);
        }

        public static /* synthetic */ ResponseBody create$default(Companion r0, byte[] r1, MediaType r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = null;
        L6:
            return r0.create(r1, r2);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final ResponseBody create(String r3, MediaType r4) {
            Pair<Charset, MediaType> r42 = Internal.chooseCharset(r4);
            Charset r0 = r42.component1();
            MediaType r43 = r42.component2();
            Buffer r32 = new Buffer().writeString(r3, r0);
            return create(r32, r43, r32.size());
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType r1, long r2, BufferedSource r4) {
            return create(r4, r1, r2);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType r1, String r2) {
            return create(r2, r1);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType r1, ByteString r2) {
            return create(r2, r1);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType r1, byte[] r2) {
            return create(r2, r1);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final ResponseBody create(BufferedSource r1, MediaType r2, long r3) {
            return new ResponseBody$Companion$asResponseBody$1(r2, r3, r1);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final ResponseBody create(ByteString r4, MediaType r5) {
            return create(new Buffer().write(r4), r5, r4.size());
        }

        @JvmStatic
        @JvmName(name = "create")
        public final ResponseBody create(byte[] r4, MediaType r5) {
            return create(new Buffer().write(r4), r5, r4.length);
        }
    }

    static {
        Companion r0 = new Companion(null);
        Companion = r0;
        EMPTY = Companion.create$default(r0, ByteString.EMPTY, null, 1, null);
    }

    public ResponseBody() {
    }

    private final Charset charset() {
        return Internal.charsetOrUtf8(contentType());
    }

    private final <T> T consumeSource(ResponseBody r5, Function1<? super BufferedSource, ? extends T> r6, Function1<? super T, Integer> r7) {
        long r0 = r5.contentLength();
        if (r0 > 2147483647L) goto L33;
        BufferedSource r4 = r5.source();
        T r62 = r6.invoke(r4);     // Catch: Throwable -> L14
        InlineMarker.finallyStart(1);
        if (r4 != null) goto L36;
    L12:
        Throwable th = null;
    L13:
        InlineMarker.finallyEnd(1);
    L22:
        if (th != null) goto L31;
        int r42 = r7.invoke(r62).intValue();
        if (r0 != (-1)) goto L26;
    L30:
        return r62;
    L26:
        if (r0 == r42) goto L30;
        ResponseBody$$ExternalSyntheticBUOutline0.m(r0, r42);
        return null;
    L31:
        throw th;
    L36:
        r4.close();     // Catch: Throwable -> L10
    L10:
        th = th;
    L14:
        th = move-exception;
        InlineMarker.finallyStart(1);
        if (r4 != null) goto L38;
    L21:
        InlineMarker.finallyEnd(1);
        th = th;
        r62 = (Object) null;
        goto L22
    L38:
        r4.close();     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L21
    L33:
        throw new IOException("Cannot buffer entire body for content length: " + r0);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final ResponseBody create(String r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType r1, long r2, BufferedSource r4) {
        return Companion.create(r1, r2, r4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType r1, String r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType r1, ByteString r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType r1, byte[] r2) {
        return Companion.create(r1, r2);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final ResponseBody create(BufferedSource r1, MediaType r2, long r3) {
        return Companion.create(r1, r2, r3);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final ResponseBody create(ByteString r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final ResponseBody create(byte[] r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    public final InputStream byteStream() {
        return source().inputStream();
    }

    public final ByteString byteString() {
        long r0 = contentLength();
        if (r0 > 2147483647L) goto L30;
        BufferedSource r6 = source();
        ByteString r3 = r6.readByteString();     // Catch: Throwable -> L12
        if (r6 != null) goto L33;
    L11:
        Throwable th = null;
    L19:
        if (th != null) goto L28;
        int r62 = r3.size();
        if (r0 != (-1)) goto L23;
    L27:
        return r3;
    L23:
        if (r0 == r62) goto L27;
        ResponseBody$$ExternalSyntheticBUOutline0.m(r0, r62);
        return null;
    L28:
        throw th;
    L33:
        r6.close();     // Catch: Throwable -> L9
    L9:
        th = th;
    L12:
        th = move-exception;
        if (r6 != null) goto L31;
    L18:
        th = th;
        r3 = null;
        goto L19
    L31:
        r6.close();     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L18
    L30:
        throw new IOException("Cannot buffer entire body for content length: " + r0);
    }

    public final byte[] bytes() {
        long r0 = contentLength();
        if (r0 > 2147483647L) goto L30;
        BufferedSource r6 = source();
        byte[] r3 = r6.readByteArray();     // Catch: Throwable -> L12
        if (r6 != null) goto L33;
    L11:
        Throwable th = null;
    L19:
        if (th != null) goto L28;
        int r62 = r3.length;
        if (r0 != (-1)) goto L23;
    L27:
        return r3;
    L23:
        if (r0 == r62) goto L27;
        ResponseBody$$ExternalSyntheticBUOutline0.m(r0, r62);
        return null;
    L28:
        throw th;
    L33:
        r6.close();     // Catch: Throwable -> L9
    L9:
        th = th;
    L12:
        th = move-exception;
        if (r6 != null) goto L31;
    L18:
        th = th;
        r3 = null;
        goto L19
    L31:
        r6.close();     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L18
    L30:
        throw new IOException("Cannot buffer entire body for content length: " + r0);
    }

    public final Reader charStream() {
        Reader r0 = this.reader;
        if (r0 != null) goto L6;
        BomAwareReader r02 = new BomAwareReader(source(), charset());
        this.reader = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        _UtilCommonKt.closeQuietly(source());
    }

    public abstract long contentLength();

    public abstract MediaType contentType();

    public abstract BufferedSource source();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v6 */
    public final String string() {
        BufferedSource r0 = source();
        String th = null;
        String r3 = r0.readString(_UtilJvmKt.readBomAsCharset(r0, charset()));     // Catch: Throwable -> L9
        if (r0 != null) goto L20;
    L8:
        String r2 = th;
        th = r3;
        th = r2;
    L15:
        if (th != 0) goto L17;
        return th;
    L17:
        throw th;
    L20:
        r0.close();     // Catch: Throwable -> L7
    L7:
        th = th;
    L9:
        th = th;
        if (r0 == null) goto L15;
        r0.close();     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L15
    }
}
