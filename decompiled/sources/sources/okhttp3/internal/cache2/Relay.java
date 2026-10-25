package okhttp3.internal.cache2;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.File;
import java.io.RandomAccessFile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ByteString;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Source;
import okio.Timeout;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 <2\u00020\u0001:\u0002;<B5\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ \u00103\u001a\u0002042\u0006\u00105\u001a\u00020\t2\u0006\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u0007H\u0002J\u0010\u00108\u001a\u0002042\u0006\u00106\u001a\u00020\u0007H\u0002J\u000e\u00109\u001a\u0002042\u0006\u00106\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u0010:\u001a\u0004\u0018\u00010\u0005R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u001a\u0010,\u001a\u00020-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0011\u00102\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b2\u0010'¨\u0006="}, d2 = {"Lokhttp3/internal/cache2/Relay;", "Lokhttp3/internal/concurrent/Lockable;", "file", "Ljava/io/RandomAccessFile;", "upstream", "Lokio/Source;", "upstreamPos", _UrlKt.FRAGMENT_ENCODE_SET, "metadata", "Lokio/ByteString;", "bufferMaxSize", "<init>", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "getUpstream", "()Lokio/Source;", "setUpstream", "(Lokio/Source;)V", "getUpstreamPos", "()J", "setUpstreamPos", "(J)V", "getBufferMaxSize", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "upstreamBuffer", "Lokio/Buffer;", "getUpstreamBuffer", "()Lokio/Buffer;", "complete", _UrlKt.FRAGMENT_ENCODE_SET, "getComplete", "()Z", "setComplete", "(Z)V", "buffer", "getBuffer", "sourceCount", _UrlKt.FRAGMENT_ENCODE_SET, "getSourceCount", "()I", "setSourceCount", "(I)V", "isClosed", "writeHeader", _UrlKt.FRAGMENT_ENCODE_SET, "prefix", "upstreamSize", "metadataSize", "writeMetadata", "commit", "newSource", "RelaySource", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Relay implements Lockable {
    public static final Companion Companion = null;
    private static final long FILE_HEADER_SIZE = 32;

    @JvmField
    public static final ByteString PREFIX_CLEAN = null;

    @JvmField
    public static final ByteString PREFIX_DIRTY = null;
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    private final Buffer buffer;
    private final long bufferMaxSize;
    private boolean complete;
    private RandomAccessFile file;
    private final ByteString metadata;
    private int sourceCount;
    private Source upstream;
    private final Buffer upstreamBuffer;
    private long upstreamPos;
    private Thread upstreamReader;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/cache2/Relay$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SOURCE_UPSTREAM", _UrlKt.FRAGMENT_ENCODE_SET, "SOURCE_FILE", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "FILE_HEADER_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "edit", "Lokhttp3/internal/cache2/Relay;", "file", "Ljava/io/File;", "upstream", "Lokio/Source;", "metadata", "bufferMaxSize", "read", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Relay edit(File r10, Source r11, ByteString r12, long r13) {
            RandomAccessFile r1 = new RandomAccessFile(r10, "rw");
            Relay r0 = new Relay(r1, r11, 0, r12, r13, null);
            r1.setLength(0);
            Relay.access$writeHeader(r0, Relay.PREFIX_DIRTY, -1, -1);
            return r0;
        }

        public final Relay read(File r10) {
            RandomAccessFile r1 = new RandomAccessFile(r10, "rw");
            FileOperator r2 = new FileOperator(r1.getChannel());
            Buffer r5 = new Buffer();
            r2.read(0, r5, 32);
            if (Intrinsics.areEqual(r5.readByteString(r9.size()), Relay.PREFIX_CLEAN) == false) goto L6;
            long r9 = r5.readLong();
            long r6 = r5.readLong();
            Buffer r52 = new Buffer();
            r2.read(32 + r9, r52, r6);
            return new Relay(r1, null, r9, r52.readByteString(), 0, null);
        L6:
            Model$$ExternalSyntheticBUOutline0.m("unreadable cache file");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "<init>", "(Lokhttp3/internal/cache2/Relay;)V", "timeout", "Lokio/Timeout;", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", _UrlKt.FRAGMENT_ENCODE_SET, "read", "sink", "Lokio/Buffer;", "byteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRelay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Relay.kt\nokhttp3/internal/cache2/Relay$RelaySource\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,362:1\n38#2:363\n*S KotlinDebug\n*F\n+ 1 Relay.kt\nokhttp3/internal/cache2/Relay$RelaySource\n*L\n272#1:363\n*E\n"})
    public final class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        final /* synthetic */ Relay this$0;
        private final Timeout timeout;

        public RelaySource(Relay r2) {
            this.this$0 = r2;
            this.timeout = new Timeout();
            this.fileOperator = new FileOperator(r2.getFile().getChannel());
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.fileOperator == null) goto L22;
            RandomAccessFile r0 = null;
            this.fileOperator = null;
            Relay r2 = this.this$0;
            monitor-enter(r2);
            r2.setSourceCount(r2.getSourceCount() - 1);     // Catch: Throwable -> L10
            if (r2.getSourceCount() != 0) goto L12;
            RandomAccessFile r1 = r2.getFile();     // Catch: Throwable -> L10
            r2.setFile(null);     // Catch: Throwable -> L10
            r0 = r1;
        L12:
            Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L10
            monitor-exit(r2);
            if (r0 == null) goto L21;
            _UtilCommonKt.closeQuietly(r0);
            return;
        L21:
            return;
        L10:
            th = move-exception;
            throw th;
        }

        @Override // okio.Source
        public long read(Buffer r23, long r24) {
            if (this.fileOperator == null) goto L73;
            Relay r4 = this.this$0;
            monitor-enter(r4);
        L82:
        L15:
            th = move-exception;
            throw th;
        L7:
            if (this.sourcePos != r4.getUpstreamPos()) goto L18;
            if (r4.getComplete() == true) goto L10;
            if (r4.getUpstreamReader() == null) goto L17;
            this.timeout.waitUntilNotified(r4);     // Catch: Throwable -> L15
            goto L82
        L17:
            r4.setUpstreamReader(Thread.currentThread());     // Catch: Throwable -> L15
            char r0 = 1;
        L22:
            monitor-exit(r4);
            Relay r42 = this.this$0;
            if (r0 != 2) goto L77;
            long r14 = Math.min(r24, r42.getUpstreamPos() - this.sourcePos);
            this.fileOperator.read(this.sourcePos + 32, r23, r14);
            this.sourcePos += r14;
            return r14;
        L77:
            long r10 = r42.getUpstream().read(this.this$0.getUpstreamBuffer(), this.this$0.getBufferMaxSize());     // Catch: Throwable -> L39
            if (r10 != (-1)) goto L41;
            Relay r02 = this.this$0;     // Catch: Throwable -> L39
            r02.commit(r02.getUpstreamPos());     // Catch: Throwable -> L39
            Relay r1 = this.this$0;
            monitor-enter(r1);
            r1.setUpstreamReader(null);     // Catch: Throwable -> L36
            r1.notifyAll();     // Catch: Throwable -> L36
            Unit r03 = Unit.INSTANCE;     // Catch: Throwable -> L36
            monitor-exit(r1);
            return -1;
        L36:
            th = move-exception;
            throw th;
        L41:
            long r20 = Math.min(r10, r24);     // Catch: Throwable -> L39
            this.this$0.getUpstreamBuffer().copyTo(r23, 0, r20);     // Catch: Throwable -> L39
            this.sourcePos += r20;
            this.fileOperator.write(this.this$0.getUpstreamPos() + 32, this.this$0.getUpstreamBuffer().m3757clone(), r10);     // Catch: Throwable -> L39
            Relay r43 = this.this$0;     // Catch: Throwable -> L39
            monitor-enter(r43);     // Catch: Throwable -> L39
            r43.getBuffer().write(r43.getUpstreamBuffer(), r10);     // Catch: Throwable -> L46
            if (r43.getBuffer().size() <= r43.getBufferMaxSize()) goto L48;
            r43.getBuffer().skip(r43.getBuffer().size() - r43.getBufferMaxSize());     // Catch: Throwable -> L46
        L48:
            r43.setUpstreamPos(r43.getUpstreamPos() + r10);     // Catch: Throwable -> L46
            Unit r04 = Unit.INSTANCE;     // Catch: Throwable -> L46
            monitor-exit(r43);     // Catch: Throwable -> L39
            Relay r12 = this.this$0;
            monitor-enter(r12);
            r12.setUpstreamReader(null);     // Catch: Throwable -> L55
            r12.notifyAll();     // Catch: Throwable -> L55
            monitor-exit(r12);
            return r20;
        L55:
            th = move-exception;
            throw th;
        L46:
            th = move-exception;
            throw th;     // Catch: Throwable -> L39
        L39:
            th = move-exception;
            Relay r13 = this.this$0;
            monitor-enter(r13);
            r13.setUpstreamReader(null);     // Catch: Throwable -> L65
            r13.notifyAll();     // Catch: Throwable -> L65
            Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L65
            throw th;
        L65:
            th = move-exception;
            throw th;
        L10:
            monitor-exit(r4);
            return -1;
        L18:
            long r8 = r4.getUpstreamPos() - r4.getBuffer().size();     // Catch: Throwable -> L15
            if (this.sourcePos >= r8) goto L68;
            r0 = 2;
            goto L22
        L68:
            long r22 = Math.min(r24, r4.getUpstreamPos() - this.sourcePos);     // Catch: Throwable -> L15
            r4.getBuffer().copyTo(r23, this.sourcePos - r8, r22);     // Catch: Throwable -> L15
            this.sourcePos += r22;
            monitor-exit(r4);
            return r22;
        L73:
            Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
            return 0;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    static {
        Companion = new Companion(null);
        ByteString.Companion r0 = ByteString.Companion;
        PREFIX_CLEAN = r0.encodeUtf8("OkHttp cache v1\n");
        PREFIX_DIRTY = r0.encodeUtf8("OkHttp DIRTY :(\n");
    }

    private Relay(RandomAccessFile r1, Source r2, long r3, ByteString r5, long r6) {
        this.file = r1;
        this.upstream = r2;
        this.upstreamPos = r3;
        this.metadata = r5;
        this.bufferMaxSize = r6;
        this.upstreamBuffer = new Buffer();
        if (this.upstream != null) goto L5;
        boolean r12 = true;
    L6:
        this.complete = r12;
        this.buffer = new Buffer();
        return;
    L5:
        r12 = false;
        goto L6
    }

    public /* synthetic */ Relay(RandomAccessFile r1, Source r2, long r3, ByteString r5, long r6, DefaultConstructorMarker r8) {
        this(r1, r2, r3, r5, r6);
    }

    public static final /* synthetic */ void access$writeHeader(Relay r0, ByteString r1, long r2, long r4) {
        r0.writeHeader(r1, r2, r4);
    }

    private final void writeHeader(ByteString r3, long r4, long r6) {
        Buffer r5 = new Buffer();
        r5.write(r3);
        r5.writeLong(r4);
        r5.writeLong(r6);
        if (r5.size() != 32) goto L6;
        new FileOperator(this.file.getChannel()).write(0, r5, 32);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
    }

    private final void writeMetadata(long r7) {
        Buffer r3 = new Buffer();
        r3.write(this.metadata);
        new FileOperator(this.file.getChannel()).write(32 + r7, r3, this.metadata.size());
    }

    public final void commit(long r9) {
        writeMetadata(r9);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, r9, this.metadata.size());
        this.file.getChannel().force(false);
        monitor-enter(this);
        this.complete = true;     // Catch: Throwable -> L12
        Unit r8 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(this);
        Source r82 = this.upstream;
        if (r82 == null) goto L10;
        _UtilCommonKt.closeQuietly(r82);
    L10:
        this.upstream = null;
        return;
    L12:
        th = move-exception;
        throw th;
    }

    public final Buffer getBuffer() {
        return this.buffer;
    }

    public final long getBufferMaxSize() {
        return this.bufferMaxSize;
    }

    public final boolean getComplete() {
        return this.complete;
    }

    public final RandomAccessFile getFile() {
        return this.file;
    }

    public final int getSourceCount() {
        return this.sourceCount;
    }

    public final Source getUpstream() {
        return this.upstream;
    }

    public final Buffer getUpstreamBuffer() {
        return this.upstreamBuffer;
    }

    public final long getUpstreamPos() {
        return this.upstreamPos;
    }

    public final Thread getUpstreamReader() {
        return this.upstreamReader;
    }

    public final boolean isClosed() {
        if (this.file != null) goto L6;
        return true;
    L6:
        return false;
    }

    public final ByteString metadata() {
        return this.metadata;
    }

    public final Source newSource() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.file != null) goto L8;
        monitor-exit(this);
        return null;
    L8:
        this.sourceCount++;
        monitor-exit(this);
        return new RelaySource(this);
    }

    public final void setComplete(boolean r1) {
        this.complete = r1;
    }

    public final void setFile(RandomAccessFile r1) {
        this.file = r1;
    }

    public final void setSourceCount(int r1) {
        this.sourceCount = r1;
    }

    public final void setUpstream(Source r1) {
        this.upstream = r1;
    }

    public final void setUpstreamPos(long r1) {
        this.upstreamPos = r1;
    }

    public final void setUpstreamReader(Thread r1) {
        this.upstreamReader = r1;
    }
}
