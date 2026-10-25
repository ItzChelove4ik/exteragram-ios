package okhttp3;

import java.io.File;
import java.io.FileDescriptor;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.Internal;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;
import okio.ByteString;
import okio.FileSystem;
import okio.HashingSink;
import okio.Okio;
import okio.Path;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0012"}, d2 = {"Lokhttp3/RequestBody;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "contentType", "Lokhttp3/MediaType;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "writeTo", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/BufferedSink;", "isDuplex", _UrlKt.FRAGMENT_ENCODE_SET, "isOneShot", "sha256", "Lokio/ByteString;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class RequestBody {
    public static final Companion Companion = null;

    @JvmField
    public static final RequestBody EMPTY = null;

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0006\u001a\u00020\u0005*\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ\u001d\u0010\u0006\u001a\u00020\u0005*\u00020\u000b2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ\u001d\u0010\u0006\u001a\u00020\u0005*\u00020\f2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ1\u0010\u0006\u001a\u00020\u0005*\u00020\r2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0002\b\nJ\u001d\u0010\u0011\u001a\u00020\u0005*\u00020\u00122\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ%\u0010\u0011\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\b\nJ\u001a\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\u0007H\u0007J\u001a\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\u000bH\u0007J.\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007J\u001a\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u0012H\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/RequestBody$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMPTY", "Lokhttp3/RequestBody;", "toRequestBody", _UrlKt.FRAGMENT_ENCODE_SET, "contentType", "Lokhttp3/MediaType;", "create", "Lokio/ByteString;", "Ljava/io/FileDescriptor;", _UrlKt.FRAGMENT_ENCODE_SET, "offset", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", "asRequestBody", "Ljava/io/File;", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "content", "file", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ RequestBody create$default(Companion r0, File r1, MediaType r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = null;
        L6:
            return r0.create(r1, r2);
        }

        public static /* synthetic */ RequestBody create$default(Companion r0, FileDescriptor r1, MediaType r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = null;
        L6:
            return r0.create(r1, r2);
        }

        public static /* synthetic */ RequestBody create$default(Companion r0, String r1, MediaType r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = null;
        L6:
            return r0.create(r1, r2);
        }

        public static /* synthetic */ RequestBody create$default(Companion r0, MediaType r1, byte[] r2, int r3, int r4, int r5, Object r6) {
            if ((r5 & 4) == 0) goto L6;
            r3 = 0;
        L6:
            if ((r5 & 8) == 0) goto L9;
            r4 = r2.length;
        L9:
            return r0.create(r1, r2, r3, r4);
        }

        public static /* synthetic */ RequestBody create$default(Companion r0, ByteString r1, MediaType r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = null;
        L6:
            return r0.create(r1, r2);
        }

        public static /* synthetic */ RequestBody create$default(Companion r0, Path r1, FileSystem r2, MediaType r3, int r4, Object r5) {
            if ((r4 & 2) == 0) goto L6;
            r3 = null;
        L6:
            return r0.create(r1, r2, r3);
        }

        public static /* synthetic */ RequestBody create$default(Companion r0, byte[] r1, MediaType r2, int r3, int r4, int r5, Object r6) {
            if ((r5 & 1) == 0) goto L6;
            r2 = null;
        L6:
            if ((r5 & 2) == 0) goto L9;
            r3 = 0;
        L9:
            if ((r5 & 4) == 0) goto L12;
            r4 = r1.length;
        L12:
            return r0.create(r1, r2, r3, r4);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final RequestBody create(File r1, MediaType r2) {
            return new RequestBody$Companion$asRequestBody$1(r2, r1);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final RequestBody create(FileDescriptor r1, MediaType r2) {
            return new RequestBody$Companion$toRequestBody$2(r2, r1);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final RequestBody create(String r3, MediaType r4) {
            Pair<Charset, MediaType> r42 = Internal.chooseCharset(r4);
            Charset r0 = r42.component1();
            MediaType r43 = r42.component2();
            byte[] r32 = r3.getBytes(r0);
            return create(r32, r43, 0, r32.length);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(expression = "file.asRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.asRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType r1, File r2) {
            return create(r2, r1);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType r1, String r2) {
            return create(r2, r1);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        public final RequestBody create(MediaType r1, ByteString r2) {
            return create(r2, r1);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        @JvmOverloads
        public final RequestBody create(MediaType r8, byte[] r9) {
            return create$default(this, r8, r9, 0, 0, 12, null);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        @JvmOverloads
        public final RequestBody create(MediaType r8, byte[] r9, int r10) {
            return create$default(this, r8, r9, r10, 0, 8, null);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
        @JvmStatic
        @JvmOverloads
        public final RequestBody create(MediaType r1, byte[] r2, int r3, int r4) {
            return create(r2, r1, r3, r4);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final RequestBody create(ByteString r1, MediaType r2) {
            return new RequestBody$Companion$toRequestBody$1(r2, r1);
        }

        @JvmStatic
        @JvmName(name = "create")
        public final RequestBody create(Path r1, FileSystem r2, MediaType r3) {
            return new RequestBody$Companion$asRequestBody$2(r3, r2, r1);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name = "create")
        public final RequestBody create(byte[] r8) {
            return create$default(this, r8, null, 0, 0, 7, null);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name = "create")
        public final RequestBody create(byte[] r8, MediaType r9) {
            return create$default(this, r8, r9, 0, 0, 6, null);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name = "create")
        public final RequestBody create(byte[] r8, MediaType r9, int r10) {
            return create$default(this, r8, r9, r10, 0, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name = "create")
        public final RequestBody create(byte[] r7, MediaType r8, int r9, int r10) {
            _UtilCommonKt.checkOffsetAndCount(r7.length, r9, r10);
            return new RequestBody$Companion$toRequestBody$3(r8, r10, r7, r9);
        }
    }

    static {
        Companion r0 = new Companion(null);
        Companion = r0;
        EMPTY = Companion.create$default(r0, ByteString.EMPTY, null, 1, null);
    }

    public RequestBody() {
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final RequestBody create(File r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final RequestBody create(FileDescriptor r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final RequestBody create(String r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(expression = "file.asRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.asRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType r1, File r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType r1, String r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    public static final RequestBody create(MediaType r1, ByteString r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    @JvmOverloads
    public static final RequestBody create(MediaType r1, byte[] r2) {
        return Companion.create(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    @JvmOverloads
    public static final RequestBody create(MediaType r1, byte[] r2, int r3) {
        return Companion.create(r1, r2, r3);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    @JvmOverloads
    public static final RequestBody create(MediaType r1, byte[] r2, int r3, int r4) {
        return Companion.create(r1, r2, r3, r4);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final RequestBody create(ByteString r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    @JvmStatic
    @JvmName(name = "create")
    public static final RequestBody create(Path r1, FileSystem r2, MediaType r3) {
        return Companion.create(r1, r2, r3);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    public static final RequestBody create(byte[] r1) {
        return Companion.create(r1);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    public static final RequestBody create(byte[] r1, MediaType r2) {
        return Companion.create(r1, r2);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    public static final RequestBody create(byte[] r1, MediaType r2, int r3) {
        return Companion.create(r1, r2, r3);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name = "create")
    public static final RequestBody create(byte[] r1, MediaType r2, int r3, int r4) {
        return Companion.create(r1, r2, r3, r4);
    }

    public long contentLength() {
        return -1;
    }

    public abstract MediaType contentType();

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    public final ByteString sha256() {
        HashingSink r0 = HashingSink.Companion.sha256(Okio.blackhole());
        BufferedSink r1 = Okio.buffer(r0);
        writeTo(r1);     // Catch: Throwable -> L6
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r1, null);
        return r0.hash();
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);
        throw th;
    }

    public abstract void writeTo(BufferedSink r1);
}
