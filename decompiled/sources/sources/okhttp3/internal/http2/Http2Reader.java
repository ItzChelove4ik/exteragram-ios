package okhttp3.internal.http2;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.http2.Hpack;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline0;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 &2\u00020\u0001:\u0003$%&B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fJ(\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J.\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001d\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001e\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010 \u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010!\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\"\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010#\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lokhttp3/internal/http2/Http2Reader;", "Ljava/io/Closeable;", "source", "Lokio/BufferedSource;", "client", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokio/BufferedSource;Z)V", "continuation", "Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "hpackReader", "Lokhttp3/internal/http2/Hpack$Reader;", "readConnectionPreface", _UrlKt.FRAGMENT_ENCODE_SET, "handler", "Lokhttp3/internal/http2/Http2Reader$Handler;", "nextFrame", "requireSettings", "readHeaders", "length", _UrlKt.FRAGMENT_ENCODE_SET, "flags", "streamId", "readHeaderBlock", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "padding", "readData", "readPriority", "readRstStream", "readSettings", "readPushPromise", "readPing", "readGoAway", "readWindowUpdate", "close", "ContinuationSource", "Handler", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Http2Reader implements Closeable {
    public static final Companion Companion = null;
    private static final Logger logger = null;
    private final boolean client;
    private final ContinuationSource continuation;
    private final Hpack.Reader hpackReader;
    private final BufferedSource source;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "lengthWithoutPadding", _UrlKt.FRAGMENT_ENCODE_SET, "length", "flags", "padding", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Logger getLogger() {
            return Http2Reader.access$getLogger$cp();
        }

        public final int lengthWithoutPadding(int r2, int r3, int r4) {
            if ((r3 & 8) == 0) goto L5;
            r2 = r2 - 1;
        L5:
            if (r4 > r2) goto L9;
            return r2 - r4;
        L9:
            throw new IOException("PROTOCOL_ERROR padding " + r4 + " > remaining length " + r2);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020 H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000b¨\u0006\""}, d2 = {"Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "<init>", "(Lokio/BufferedSource;)V", "length", _UrlKt.FRAGMENT_ENCODE_SET, "getLength", "()I", "setLength", "(I)V", "flags", "getFlags", "setFlags", "streamId", "getStreamId", "setStreamId", "left", "getLeft", "setLeft", "padding", "getPadding", "setPadding", "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "readContinuationHeader", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ContinuationSource implements Source {
        private int flags;
        private int left;
        private int length;
        private int padding;
        private final BufferedSource source;
        private int streamId;

        public ContinuationSource(BufferedSource r1) {
            this.source = r1;
        }

        private final void readContinuationHeader() {
            int r0 = this.streamId;
            int r1 = _UtilCommonKt.readMedium(this.source);
            this.left = r1;
            this.length = r1;
            int r7 = _UtilCommonKt.and(this.source.readByte(), 255);
            this.flags = _UtilCommonKt.and(this.source.readByte(), 255);
            Companion r12 = Http2Reader.Companion;
            if (r12.getLogger().isLoggable(Level.FINE) == false) goto L5;
            r12.getLogger().fine(Http2.INSTANCE.frameLog(true, this.streamId, this.length, r7, this.flags));
        L5:
            int r13 = this.source.readInt() & Integer.MAX_VALUE;
            this.streamId = r13;
            if (r7 != 9) goto L12;
            if (r13 != r0) goto L9;
            return;
        L9:
            Model$$ExternalSyntheticBUOutline0.m("TYPE_CONTINUATION streamId changed");
            return;
        L12:
            throw new IOException(r7 + " != TYPE_CONTINUATION");
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        public final int getFlags() {
            return this.flags;
        }

        public final int getLeft() {
            return this.left;
        }

        public final int getLength() {
            return this.length;
        }

        public final int getPadding() {
            return this.padding;
        }

        public final int getStreamId() {
            return this.streamId;
        }

        @Override // okio.Source
        public long read(Buffer r7, long r8) {
        L2:
            int r0 = this.left;
            BufferedSource r1 = this.source;
            if (r0 != 0) goto L8;
            r1.skip(this.padding);
            this.padding = 0;
            if ((this.flags & 4) != 0) goto L6;
            readContinuationHeader();
            goto L2
        L6:
            return -1;
        L8:
            long r72 = r1.read(r7, Math.min(r8, r0));
            if (r72 != (-1)) goto L11;
            return -1;
        L11:
            this.left -= (int) r72;
            return r72;
        }

        public final void setFlags(int r1) {
            this.flags = r1;
        }

        public final void setLeft(int r1) {
            this.left = r1;
        }

        public final void setLength(int r1) {
            this.length = r1;
        }

        public final void setPadding(int r1) {
            this.padding = r1;
        }

        public final void setStreamId(int r1) {
            this.streamId = r1;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H&J.\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0003H&J \u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H&J \u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH&J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!H&J(\u0010\"\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0005H&J&\u0010&\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH&J8\u0010)\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020!H&¨\u00060À\u0006\u0003"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Handler;", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "inFinished", _UrlKt.FRAGMENT_ENCODE_SET, "streamId", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/BufferedSource;", "length", "headers", "associatedStreamId", "headerBlock", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "rstStream", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "settings", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "ackSettings", "ping", "ack", "payload1", "payload2", "goAway", "lastGoodStreamId", "debugData", "Lokio/ByteString;", "windowUpdate", "windowSizeIncrement", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.MenuItemProperties.PRIORITY, "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "alternateService", "origin", _UrlKt.FRAGMENT_ENCODE_SET, "protocol", "host", "port", "maxAge", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Handler {
        void ackSettings();

        void alternateService(int r1, String r2, ByteString r3, String r4, int r5, long r6);

        void data(boolean r1, int r2, BufferedSource r3, int r4);

        void goAway(int r1, ErrorCode r2, ByteString r3);

        void headers(boolean r1, int r2, int r3, List<Header> r4);

        void ping(boolean r1, int r2, int r3);

        void priority(int r1, int r2, int r3, boolean r4);

        void pushPromise(int r1, int r2, List<Header> r3);

        void rstStream(int r1, ErrorCode r2);

        void settings(boolean r1, Settings r2);

        void windowUpdate(int r1, long r2);
    }

    static {
        Companion = new Companion(null);
        logger = Logger.getLogger(Http2.class.getName());
    }

    public Http2Reader(BufferedSource r7, boolean r8) {
        this.source = r7;
        this.client = r8;
        ContinuationSource r1 = new ContinuationSource(r7);
        this.continuation = r1;
        this.hpackReader = new Hpack.Reader(r1, 4096, 0, 4, null);
    }

    public static final /* synthetic */ Logger access$getLogger$cp() {
        return logger;
    }

    private final void readData(Handler r4, int r5, int r6, int r7) {
        if (r7 == 0) goto L16;
        int r1 = 0;
        if ((r6 & 1) == 0) goto L6;
        boolean r0 = true;
    L8:
        if ((r6 & 32) == 0) goto L10;
        Model$$ExternalSyntheticBUOutline0.m("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        return;
    L10:
        if ((r6 & 8) == 0) goto L12;
        r1 = _UtilCommonKt.and(this.source.readByte(), 255);
    L12:
        r4.data(r0, r7, this.source, Companion.lengthWithoutPadding(r5, r6, r1));
        this.source.skip(r1);
        return;
    L6:
        r0 = false;
        goto L8
    L16:
        Model$$ExternalSyntheticBUOutline0.m("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
    }

    private final void readGoAway(Handler r3, int r4, int r5, int r6) {
        if (r4 < 8) goto L16;
        if (r6 != 0) goto L14;
        int r62 = this.source.readInt();
        int r0 = this.source.readInt();
        int r42 = r4 - 8;
        ErrorCode r52 = ErrorCode.Companion.fromHttp2(r0);
        if (r52 == null) goto L12;
        ByteString r02 = ByteString.EMPTY;
        if (r42 <= 0) goto L10;
        r02 = this.source.readByteString(r42);
    L10:
        r3.goAway(r62, r52, r02);
        return;
    L12:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("TYPE_GOAWAY unexpected error code: ", r0);
        return;
    L14:
        Model$$ExternalSyntheticBUOutline0.m("TYPE_GOAWAY streamId != 0");
        return;
    L16:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("TYPE_GOAWAY length < 8: ", r4);
    }

    private final List<Header> readHeaderBlock(int r2, int r3, int r4, int r5) {
        this.continuation.setLeft(r2);
        ContinuationSource r22 = this.continuation;
        r22.setLength(r22.getLeft());
        this.continuation.setPadding(r3);
        this.continuation.setFlags(r4);
        this.continuation.setStreamId(r5);
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private final void readHeaders(Handler r4, int r5, int r6, int r7) {
        if (r7 == 0) goto L15;
        int r1 = 0;
        if ((r6 & 1) == 0) goto L6;
        boolean r0 = true;
    L8:
        if ((r6 & 8) == 0) goto L11;
        r1 = _UtilCommonKt.and(this.source.readByte(), 255);
    L11:
        if ((r6 & 32) == 0) goto L13;
        readPriority(r4, r7);
        r5 = r5 - 5;
    L13:
        r4.headers(r0, r7, -1, readHeaderBlock(Companion.lengthWithoutPadding(r5, r6, r1), r1, r6, r7));
        return;
    L6:
        r0 = false;
        goto L8
    L15:
        Model$$ExternalSyntheticBUOutline0.m("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
    }

    private final void readPing(Handler r2, int r3, int r4, int r5) {
        if (r3 != 8) goto L13;
        if (r5 != 0) goto L11;
        int r32 = this.source.readInt();
        int r1 = this.source.readInt();
        boolean r52 = true;
        if ((r4 & 1) != 0) goto L9;
        r52 = false;
    L9:
        r2.ping(r52, r32, r1);
        return;
    L11:
        Model$$ExternalSyntheticBUOutline0.m("TYPE_PING streamId != 0");
        return;
    L13:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("TYPE_PING length != 8: ", r3);
    }

    private final void readPriority(Handler r5, int r6) {
        int r0 = this.source.readInt();
        if ((Integer.MIN_VALUE & r0) == 0) goto L5;
        boolean r1 = true;
    L6:
        r5.priority(r6, r0 & Integer.MAX_VALUE, _UtilCommonKt.and(this.source.readByte(), 255) + 1, r1);
        return;
    L5:
        r1 = false;
        goto L6
    }

    private final void readPriority(Handler r1, int r2, int r3, int r4) {
        if (r2 != 5) goto L10;
        if (r4 == 0) goto L7;
        readPriority(r1, r4);
        return;
    L7:
        Model$$ExternalSyntheticBUOutline0.m("TYPE_PRIORITY streamId == 0");
        return;
    L10:
        throw new IOException("TYPE_PRIORITY length: " + r2 + " != 5");
    }

    private final void readPushPromise(Handler r4, int r5, int r6, int r7) {
        if (r7 != 0) goto L4;
        Model$$ExternalSyntheticBUOutline0.m("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        return;
    L4:
        if ((r6 & 8) == 0) goto L6;
        int r0 = _UtilCommonKt.and(this.source.readByte(), 255);
    L7:
        r4.pushPromise(r7, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(Companion.lengthWithoutPadding(r5 - 4, r6, r0), r0, r6, r7));
        return;
    L6:
        r0 = 0;
        goto L7
    }

    private final void readRstStream(Handler r1, int r2, int r3, int r4) {
        if (r2 != 4) goto L14;
        if (r4 == 0) goto L11;
        int r0 = this.source.readInt();
        ErrorCode r22 = ErrorCode.Companion.fromHttp2(r0);
        if (r22 == null) goto L9;
        r1.rstStream(r4, r22);
        return;
    L9:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("TYPE_RST_STREAM unexpected error code: ", r0);
        return;
    L11:
        Model$$ExternalSyntheticBUOutline0.m("TYPE_RST_STREAM streamId == 0");
        return;
    L14:
        throw new IOException("TYPE_RST_STREAM length: " + r2 + " != 4");
    }

    private final void readSettings(Handler r7, int r8, int r9, int r10) {
        if (r10 == 0) goto L4;
        Model$$ExternalSyntheticBUOutline0.m("TYPE_SETTINGS streamId != 0");
        return;
    L4:
        if ((r9 & 1) == 0) goto L11;
        if (r8 != 0) goto L8;
        r7.ackSettings();
        return;
    L8:
        Model$$ExternalSyntheticBUOutline0.m("FRAME_SIZE_ERROR ack frame should be empty!");
        return;
    L11:
        if ((r8 % 6) != 0) goto L45;
        Settings r92 = new Settings();
        IntProgression r82 = RangesKt.step(RangesKt.until(0, r8), 6);
        int r1 = r82.getFirst();
        int r2 = r82.getLast();
        int r83 = r82.getStep();
        if (r83 <= 0) goto L15;
        if (r1 > r2) goto L15;
    L17:
        int r3 = _UtilCommonKt.and(this.source.readShort(), 65535);
        int r4 = this.source.readInt();
        if (r3 != 2) goto L20;
        if (r4 == 0) goto L40;
        if (r4 == 1) goto L40;
        Model$$ExternalSyntheticBUOutline0.m("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
        return;
    L40:
        r92.set(r3, r4);
        if (r1 == r2) goto L43;
        r1 = r1 + r83;
    L43:
        r7.settings(false, r92);
        return;
    L20:
        if (r3 != 4) goto L22;
        if (r4 >= 0) goto L40;
        Model$$ExternalSyntheticBUOutline0.m("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
        return;
    L22:
        if (r3 != 5) goto L40;
        if (r4 < 16384) goto L29;
        if (r4 <= 16777215) goto L40;
    L29:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ", r4);
        return;
    L15:
        if (r83 >= 0) goto L43;
        if (r2 > r1) goto L43;
    L45:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("TYPE_SETTINGS length % 6 != 0: ", r8);
    }

    private final void readWindowUpdate(Handler r9, int r10, int r11, int r12) {
        if (r10 != 4) goto L21;
        long r6 = _UtilCommonKt.and(this.source.readInt(), 2147483647L);     // Catch: Exception -> L18
        if (r6 == 0) goto L15;
        Logger r8 = logger;
        if (r8.isLoggable(Level.FINE) == false) goto L10;
        int r2 = r12;
        r8.fine(Http2.INSTANCE.frameLogWindowUpdate(true, r12, r10, r6));
    L11:
        r9.windowUpdate(r2, r6);
        return;
    L10:
        r2 = r12;
        goto L11
    L15:
        throw new IOException("windowSizeIncrement was 0");     // Catch: Exception -> L16
    L18:
        e = e;
    L17:
        Exception r82 = e;
        logger.fine(Http2.INSTANCE.frameLog(true, r12, r10, 8, r11));
        throw r82;
    L21:
        throw new IOException("TYPE_WINDOW_UPDATE length !=4: " + r10);     // Catch: Exception -> L16
    L16:
        e = e;
        goto L17
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    public final boolean nextFrame(boolean r8, Handler r9) {
        this.source.require(9);     // Catch: EOFException -> L31
        int r4 = _UtilCommonKt.readMedium(this.source);
        if (r4 > 16384) goto L30;
        int r5 = _UtilCommonKt.and(this.source.readByte(), 255);
        int r6 = _UtilCommonKt.and(this.source.readByte(), 255);
        int r3 = this.source.readInt() & Integer.MAX_VALUE;
        if (r5 == 8) goto L10;
        Logger r0 = logger;
        if (r0.isLoggable(Level.FINE) == false) goto L10;
        r0.fine(Http2.INSTANCE.frameLog(true, r3, r4, r5, r6));
    L10:
        if (r8 == true) goto L12;
    L17:
        switch(r5) {
            case 0: goto L27;
            case 1: goto L26;
            case 2: goto L25;
            case 3: goto L24;
            case 4: goto L23;
            case 5: goto L22;
            case 6: goto L21;
            case 7: goto L20;
            case 8: goto L19;
            default: goto L18;
        };
    L18:
        this.source.skip(r4);
        return true;
    L19:
        readWindowUpdate(r9, r4, r6, r3);
        return true;
    L20:
        readGoAway(r9, r4, r6, r3);
        return true;
    L21:
        readPing(r9, r4, r6, r3);
        return true;
    L22:
        readPushPromise(r9, r4, r6, r3);
        return true;
    L23:
        readSettings(r9, r4, r6, r3);
        return true;
    L24:
        readRstStream(r9, r4, r6, r3);
        return true;
    L25:
        readPriority(r9, r4, r6, r3);
        return true;
    L26:
        readHeaders(r9, r4, r6, r3);
        return true;
    L27:
        readData(r9, r4, r6, r3);
        return true;
    L12:
        if (r5 == 4) goto L17;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Expected a SETTINGS frame but was ", Http2.INSTANCE.formattedType$okhttp(r5));
        return false;
    L30:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("FRAME_SIZE_ERROR: ", r4);
        return false;
    L31:
        return false;
    }

    public final void readConnectionPreface(Handler r4) {
        if (this.client == true) goto L5;
        BufferedSource r3 = this.source;
        ByteString r42 = Http2.CONNECTION_PREFACE;
        ByteString r32 = r3.readByteString(r42.size());
        Logger r0 = logger;
        if (r0.isLoggable(Level.FINE) == false) goto L13;
        r0.fine(_UtilJvmKt.format("<< CONNECTION " + r32.hex(), new Object[0]));
    L13:
        if (Intrinsics.areEqual(r42, r32) == false) goto L15;
        return;
    L15:
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Expected a connection header but was ", r32.utf8());
        return;
    L5:
        if (nextFrame(true, r4) == true) goto L17;
        Model$$ExternalSyntheticBUOutline0.m("Required SETTINGS preface not received");
        return;
    }
}
