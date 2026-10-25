package okio;

import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001*B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u001a\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u0018H$¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0007H$¢\u0006\u0004\b\u001c\u0010\u000fJ\u000f\u0010\u001d\u001a\u00020\u0013H$¢\u0006\u0004\b\u001d\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\u001eR\u0016\u0010\"\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u001b\u0010&\u001a\u00060$j\u0002`%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lokio/FileHandle;", "Ljava/io/Closeable;", "Lokio/Closeable;", _UrlKt.FRAGMENT_ENCODE_SET, "readWrite", "<init>", "(Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "fileOffset", "Lokio/Buffer;", "sink", "byteCount", "readNoCloseCheck", "(JLokio/Buffer;J)J", "size", "()J", "Lokio/Source;", "source", "(J)Lokio/Source;", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "array", _UrlKt.FRAGMENT_ENCODE_SET, "arrayOffset", "protectedRead", "(J[BII)I", "protectedSize", "protectedClose", "Z", "getReadWrite", "()Z", "closed", "openStreamCount", "I", "Ljava/util/concurrent/locks/ReentrantLock;", "Lokio/Lock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "FileHandleSource", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle\n+ 2 -JvmPlatform.kt\nokio/_JvmPlatformKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 5 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 6 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,444:1\n40#2:445\n40#2:447\n40#2:448\n40#2:449\n40#2:450\n40#2:451\n40#2:452\n40#2:453\n40#2:457\n40#2:459\n1#3:446\n63#4:454\n63#4:455\n63#4:456\n51#5:458\n85#6:460\n85#6:461\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle\n*L\n69#1:445\n81#1:447\n92#1:448\n105#1:449\n119#1:450\n129#1:451\n139#1:452\n151#1:453\n221#1:457\n287#1:459\n169#1:454\n195#1:455\n202#1:456\n248#1:458\n345#1:460\n374#1:461\n*E\n"})
/* loaded from: classes5.dex */
public abstract class FileHandle implements Closeable {
    private boolean closed;
    private final ReentrantLock lock;
    private int openStreamCount;
    private final boolean readWrite;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006\""}, d2 = {"Lokio/FileHandle$FileHandleSource;", "Lokio/Source;", "Lokio/FileHandle;", "fileHandle", _UrlKt.FRAGMENT_ENCODE_SET, "position", "<init>", "(Lokio/FileHandle;J)V", "Lokio/Buffer;", "sink", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "Lokio/FileHandle;", "getFileHandle", "()Lokio/FileHandle;", "J", "getPosition", "()J", "setPosition", "(J)V", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "Z", "getClosed", "()Z", "setClosed", "(Z)V", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -JvmPlatform.kt\nokio/_JvmPlatformKt\n*L\n1#1,444:1\n1#2:445\n40#3:446\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSource\n*L\n436#1:446\n*E\n"})
    public static final class FileHandleSource implements Source {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSource(FileHandle r1, long r2) {
            this.fileHandle = r1;
            this.position = r2;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed == false) goto L5;
            return;
        L5:
            this.closed = true;
            ReentrantLock r0 = this.fileHandle.getLock();
            r0.lock();
            FileHandle.access$setOpenStreamCount$p(this.fileHandle, FileHandle.access$getOpenStreamCount$p(r1) - 1);     // Catch: Throwable -> L14
            if (FileHandle.access$getOpenStreamCount$p(this.fileHandle) == 0) goto L9;
        L16:
            r0.unlock();
            return;
        L9:
            if (FileHandle.access$getClosed$p(this.fileHandle) == false) goto L16;
            Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L14
            r0.unlock();
            this.fileHandle.protectedClose();
            return;
        L14:
            th = move-exception;
            r0.unlock();
            throw th;
        }

        @Override // okio.Source
        public long read(Buffer r8, long r9) {
            if (this.closed == true) goto L8;
            long r82 = FileHandle.access$readNoCloseCheck(this.fileHandle, this.position, r8, r9);
            if (r82 == (-1)) goto L7;
            this.position += r82;
        L7:
            return r82;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("closed");
            return 0;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return Timeout.NONE;
        }
    }

    public FileHandle(boolean r1) {
        this.readWrite = r1;
        this.lock = _JvmPlatformKt.newLock();
    }

    public static final /* synthetic */ boolean access$getClosed$p(FileHandle r0) {
        return r0.closed;
    }

    public static final /* synthetic */ int access$getOpenStreamCount$p(FileHandle r0) {
        return r0.openStreamCount;
    }

    public static final /* synthetic */ long access$readNoCloseCheck(FileHandle r0, long r1, Buffer r3, long r4) {
        return r0.readNoCloseCheck(r1, r3, r4);
    }

    public static final /* synthetic */ void access$setOpenStreamCount$p(FileHandle r0, int r1) {
        r0.openStreamCount = r1;
    }

    private final long readNoCloseCheck(long r15, Buffer r17, long r18) {
        if (r18 < 0) goto L19;
        long r1 = r18 + r15;
        long r4 = r15;
    L6:
        if (r4 >= r1) goto L18;
        Segment r9 = r17.writableSegment$okio(1);
        int r6 = protectedRead(r4, r9.data, r9.limit, (int) Math.min(r1 - r4, 8192 - r7));
        if (r6 == (-1)) goto L10;
        r9.limit += r6;
        long r62 = r6;
        r4 = r4 + r62;
        r17.setSize$okio(r17.size() + r62);
        goto L6
    L10:
        if (r9.pos != r9.limit) goto L13;
        r17.head = r9.pop();
        SegmentPool.recycle(r9);
    L13:
        if (r15 != r4) goto L18;
        return -1;
    L18:
        return r4 - r15;
    L19:
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r18);
        return 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ReentrantLock r0 = this.lock;
        r0.lock();
    L15:
        th = move-exception;
        r0.unlock();
        throw th;
    L4:
        if (this.closed == false) goto L8;
        r0.unlock();
        return;
    L8:
        this.closed = true;     // Catch: Throwable -> L15
        if (this.openStreamCount == 0) goto L12;
        r0.unlock();
        return;
    L12:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L15
        r0.unlock();
        protectedClose();
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    public abstract void protectedClose();

    public abstract int protectedRead(long r1, byte[] r3, int r4, int r5);

    public abstract long protectedSize();

    public final long size() {
        ReentrantLock r0 = this.lock;
        r0.lock();
    L8:
        th = move-exception;
        r0.unlock();
        throw th;
    L4:
        if (this.closed == true) goto L11;
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L8
        r0.unlock();
        return protectedSize();
    L11:
        throw new IllegalStateException("closed");     // Catch: Throwable -> L8
    }

    public final Source source(long r3) {
        ReentrantLock r0 = this.lock;
        r0.lock();
    L8:
        th = move-exception;
        r0.unlock();
        throw th;
    L4:
        if (this.closed == true) goto L11;
        this.openStreamCount++;
        r0.unlock();
        return new FileHandleSource(this, r3);
    L11:
        throw new IllegalStateException("closed");     // Catch: Throwable -> L8
    }
}
