package okhttp3;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaStatus;
import java.io.Closeable;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.http1.HeadersReader;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import okio.Buffer$$ExternalSyntheticBUOutline3;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001d\u001e\u001fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0018\u00010\u0015R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lokhttp3/MultipartReader;", "Ljava/io/Closeable;", "source", "Lokio/BufferedSource;", "boundary", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokio/BufferedSource;Ljava/lang/String;)V", PluginsConstants.RESPONSE, "Lokhttp3/ResponseBody;", "(Lokhttp3/ResponseBody;)V", "()Ljava/lang/String;", "dashDashBoundary", "Lokio/ByteString;", "crlfDashDashBoundary", "partCount", _UrlKt.FRAGMENT_ENCODE_SET, "closed", _UrlKt.FRAGMENT_ENCODE_SET, "noMoreParts", "currentPart", "Lokhttp3/MultipartReader$PartSource;", "nextPart", "Lokhttp3/MultipartReader$Part;", "currentPartBytesRemaining", _UrlKt.FRAGMENT_ENCODE_SET, "maxByteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "PartSource", "Part", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMultipartReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartReader.kt\nokhttp3/MultipartReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,228:1\n1#2:229\n*E\n"})
/* loaded from: classes5.dex */
public final class MultipartReader implements Closeable {
    public static final Companion Companion = null;
    private static final Options afterBoundaryOptions = null;
    private final String boundary;
    private boolean closed;
    private final ByteString crlfDashDashBoundary;
    private PartSource currentPart;
    private final ByteString dashDashBoundary;
    private boolean noMoreParts;
    private int partCount;
    private final BufferedSource source;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lokhttp3/MultipartReader$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "afterBoundaryOptions", "Lokio/Options;", "getAfterBoundaryOptions", "()Lokio/Options;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Options getAfterBoundaryOptions() {
            return MultipartReader.access$getAfterBoundaryOptions$cp();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0001R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\f"}, d2 = {"Lokhttp3/MultipartReader$Part;", "Ljava/io/Closeable;", "headers", "Lokhttp3/Headers;", "body", "Lokio/BufferedSource;", "<init>", "(Lokhttp3/Headers;Lokio/BufferedSource;)V", "()Lokhttp3/Headers;", "()Lokio/BufferedSource;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Part implements Closeable {
        private final BufferedSource body;
        private final Headers headers;

        public Part(Headers r1, BufferedSource r2) {
            this.headers = r1;
            this.body = r2;
        }

        @JvmName(name = "body")
        public final BufferedSource body() {
            return this.body;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.body.close();
        }

        @JvmName(name = "headers")
        public final Headers headers() {
            return this.headers;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/MultipartReader$PartSource;", "Lokio/Source;", "<init>", "(Lokhttp3/MultipartReader;)V", "timeout", "Lokio/Timeout;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMultipartReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartReader.kt\nokhttp3/MultipartReader$PartSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Timeout.kt\nokio/Timeout\n*L\n1#1,228:1\n1#2:229\n302#3,26:230\n*S KotlinDebug\n*F\n+ 1 MultipartReader.kt\nokhttp3/MultipartReader$PartSource\n*L\n168#1:230,26\n*E\n"})
    public final class PartSource implements Source {
        final /* synthetic */ MultipartReader this$0;
        private final Timeout timeout;

        public PartSource(MultipartReader r1) {
            this.this$0 = r1;
            this.timeout = new Timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (Intrinsics.areEqual(MultipartReader.access$getCurrentPart$p(this.this$0), this) == false) goto L6;
            MultipartReader.access$setCurrentPart$p(this.this$0, null);
            return;
        }

        @Override // okio.Source
        public long read(Buffer r18, long r19) {
            if (r19 >= 0) goto L5;
            Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r19);
            return 0;
        L5:
            if (Intrinsics.areEqual(MultipartReader.access$getCurrentPart$p(this.this$0), this) == false) goto L41;
            Timeout r6 = MultipartReader.access$getSource$p(this.this$0).timeout();
            Timeout r7 = this.timeout;
            MultipartReader r0 = this.this$0;
            long r8 = r6.timeoutNanos();
            long r10 = Timeout.Companion.minTimeout(r7.timeoutNanos(), r6.timeoutNanos());
            TimeUnit r12 = TimeUnit.NANOSECONDS;
            r6.timeout(r10, r12);
            if (r6.hasDeadline() == false) goto L26;
            long r102 = r6.deadlineNanoTime();
            if (r7.hasDeadline() == false) goto L11;
            long r15 = 0;
            r6.deadlineNanoTime(Math.min(r6.deadlineNanoTime(), r7.deadlineNanoTime()));
        L47:
            long r2 = MultipartReader.access$currentPartBytesRemaining(r0, r19);     // Catch: Throwable -> L20
            if (r2 != r15) goto L15;
            long r13 = -1;
        L16:
            r6.timeout(r8, r12);
            if (r7.hasDeadline() == false) goto L19;
            r6.deadlineNanoTime(r102);
        L19:
            return r13;
        L15:
            r13 = MultipartReader.access$getSource$p(r0).read(r18, r2);     // Catch: Throwable -> L20
        L20:
            th = move-exception;
            r6.timeout(r8, r12);
            if (r7.hasDeadline() == false) goto L24;
            r6.deadlineNanoTime(r102);
        L24:
            throw th;
        L11:
            r15 = 0;
            goto L47
        L26:
            if (r7.hasDeadline() == false) goto L45;
            r6.deadlineNanoTime(r7.deadlineNanoTime());
        L45:
            long r22 = MultipartReader.access$currentPartBytesRemaining(r0, r19);     // Catch: Throwable -> L36
            if (r22 != 0) goto L31;
            long r132 = -1;
        L32:
            r6.timeout(r8, r12);
            if (r7.hasDeadline() == false) goto L35;
            r6.clearDeadline();
        L35:
            return r132;
        L31:
            r132 = MultipartReader.access$getSource$p(r0).read(r18, r22);     // Catch: Throwable -> L36
        L36:
            th = move-exception;
            r6.timeout(r8, r12);
            if (r7.hasDeadline() == false) goto L40;
            r6.clearDeadline();
        L40:
            throw th;
        L41:
            Segment$$ExternalSyntheticBUOutline1.m("closed");
            return 0;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    static {
        Companion = new Companion(null);
        Options.Companion r0 = Options.Companion;
        ByteString.Companion r1 = ByteString.Companion;
        afterBoundaryOptions = r0.of(new ByteString[]{r1.encodeUtf8("\r\n"), r1.encodeUtf8("--"), r1.encodeUtf8(" "), r1.encodeUtf8("\t")});
    }

    public MultipartReader(ResponseBody r3) {
        BufferedSource r0 = r3.source();
        MediaType r32 = r3.contentType();
        if (r32 == null) goto L9;
        String r33 = r32.parameter("boundary");
        if (r33 == null) goto L9;
        this(r0, r33);
        return;
    L9:
        throw new ProtocolException("expected the Content-Type to have a boundary parameter");
    }

    public MultipartReader(BufferedSource r2, String r3) {
        this.source = r2;
        this.boundary = r3;
        this.dashDashBoundary = new Buffer().writeUtf8("--").writeUtf8(r3).readByteString();
        this.crlfDashDashBoundary = new Buffer().writeUtf8("\r\n--").writeUtf8(r3).readByteString();
    }

    public static final /* synthetic */ long access$currentPartBytesRemaining(MultipartReader r0, long r1) {
        return r0.currentPartBytesRemaining(r1);
    }

    public static final /* synthetic */ Options access$getAfterBoundaryOptions$cp() {
        return afterBoundaryOptions;
    }

    public static final /* synthetic */ PartSource access$getCurrentPart$p(MultipartReader r0) {
        return r0.currentPart;
    }

    public static final /* synthetic */ BufferedSource access$getSource$p(MultipartReader r0) {
        return r0.source;
    }

    public static final /* synthetic */ void access$setCurrentPart$p(MultipartReader r0, PartSource r1) {
        r0.currentPart = r1;
    }

    private final long currentPartBytesRemaining(long r11) {
        long r8 = Math.min(this.source.getBuffer().size(), r11) + 1;
        long r0 = this.source.indexOf(this.crlfDashDashBoundary, 0, r8);
        if (r0 == (-1)) goto L6;
        return r0;
    L6:
        if (this.source.getBuffer().size() >= r8) goto L8;
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L8:
        return Math.min(r8, r11);
    }

    @JvmName(name = "boundary")
    public final String boundary() {
        return this.boundary;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed == false) goto L5;
        return;
    L5:
        this.closed = true;
        this.currentPart = null;
        this.source.close();
    }

    public final Part nextPart() {
        if (this.closed == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    L5:
        if (this.noMoreParts == false) goto L8;
        return null;
    L8:
        if (this.partCount != 0) goto L12;
        if (this.source.rangeEquals(0, this.dashDashBoundary) == false) goto L12;
        this.source.skip(this.dashDashBoundary.size());
    L16:
        boolean r0 = false;
    L17:
        int r2 = this.source.select(afterBoundaryOptions);
        if (r2 == (-1)) goto L40;
        if (r2 == 0) goto L37;
        if (r2 == 1) goto L28;
        if (r2 == 2) goto L27;
        if (r2 != 3) goto L17;
    L27:
        r0 = true;
        goto L17
    L28:
        if (r0 == true) goto L36;
        if (this.partCount == 0) goto L34;
        this.noMoreParts = true;
        return null;
    L34:
        throw new ProtocolException("expected at least 1 part");
    L36:
        throw new ProtocolException("unexpected characters after boundary");
    L37:
        this.partCount++;
        Headers r02 = new HeadersReader(this.source).readHeaders();
        PartSource r1 = new PartSource(this);
        this.currentPart = r1;
        return new Part(r02, Okio.buffer(r1));
    L40:
        throw new ProtocolException("unexpected characters after boundary");
    L12:
        long r4 = currentPartBytesRemaining(MediaStatus.COMMAND_PLAYBACK_RATE);
        BufferedSource r6 = this.source;
        if (r4 == 0) goto L15;
        r6.skip(r4);
        goto L12
    L15:
        r6.skip(this.crlfDashDashBoundary.size());
        goto L16
    }
}
