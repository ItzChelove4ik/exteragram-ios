package okhttp3.internal.publicsuffix;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b \u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lokhttp3/internal/publicsuffix/BasePublicSuffixList;", "Lokhttp3/internal/publicsuffix/PublicSuffixList;", "<init>", "()V", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "bytes", "Lokio/ByteString;", "getBytes", "()Lokio/ByteString;", "setBytes", "(Lokio/ByteString;)V", "exceptionBytes", "getExceptionBytes", "setExceptionBytes", "readFailure", "Ljava/io/IOException;", "readTheList", _UrlKt.FRAGMENT_ENCODE_SET, "listSource", "Lokio/Source;", "ensureLoaded", "path", _UrlKt.FRAGMENT_ENCODE_SET, "getPath", "()Ljava/lang/Object;", "readTheListUninterruptibly", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class BasePublicSuffixList implements PublicSuffixList {
    public ByteString bytes;
    public ByteString exceptionBytes;
    private final AtomicBoolean listRead;
    private final CountDownLatch readCompleteLatch;
    private IOException readFailure;

    public BasePublicSuffixList() {
        this.listRead = new AtomicBoolean(false);
        this.readCompleteLatch = new CountDownLatch(1);
    }

    private final void readTheList() {
        BufferedSource r0 = Okio.buffer(listSource());     // Catch: Throwable -> L11
        ByteString r1 = r0.readByteString(r0.readInt());     // Catch: Throwable -> L16
        ByteString r2 = r0.readByteString(r0.readInt());     // Catch: Throwable -> L16
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L16
    L5:
        CloseableKt.closeFinally(r0, null);     // Catch: Throwable -> L11
        monitor-enter(this);     // Catch: Throwable -> L11
        setBytes(r1);     // Catch: Throwable -> L13
        setExceptionBytes(r2);     // Catch: Throwable -> L13
        monitor-exit(this);     // Catch: Throwable -> L11
        this.readCompleteLatch.countDown();
        return;
    L13:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L16:
        th = move-exception;
        throw th;     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);     // Catch: Throwable -> L11
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        this.readCompleteLatch.countDown();
        throw th;
    }

    private final void readTheListUninterruptibly() {
        boolean r0 = false;
    L18:
        readTheList();     // Catch: Throwable -> L6 IOException -> L8 InterruptedIOException -> L13
    L4:
        if (r0 == false) goto L12;
    L5:
        Thread.currentThread().interrupt();
        return;
    L12:
        return;
    L6:
        th = move-exception;
        if (r0 == false) goto L17;
        Thread.currentThread().interrupt();
    L17:
        throw th;
    L13:
        Thread.interrupted();     // Catch: Throwable -> L6
        r0 = true;
    L8:
        e = move-exception;
        this.readFailure = e;     // Catch: Throwable -> L6
        if (r0 == true) goto L5;
    }

    @Override // okhttp3.internal.publicsuffix.PublicSuffixList
    public void ensureLoaded() {
        if (this.listRead.get() == false) goto L5;
    L15:
        this.readCompleteLatch.await();     // Catch: InterruptedException -> L9
    L11:
        if (this.bytes == null) goto L13;
        return;
    L13:
        IllegalStateException r0 = new IllegalStateException("Unable to load " + getPath() + " resource.");
        r0.initCause(this.readFailure);
        throw r0;
    L9:
        Thread.currentThread().interrupt();
        goto L11
    L5:
        if (this.listRead.compareAndSet(false, true) == false) goto L15;
        readTheListUninterruptibly();
        goto L11
    }

    @Override // okhttp3.internal.publicsuffix.PublicSuffixList
    public ByteString getBytes() {
        ByteString r0 = this.bytes;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    @Override // okhttp3.internal.publicsuffix.PublicSuffixList
    public ByteString getExceptionBytes() {
        ByteString r0 = this.exceptionBytes;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public abstract Object getPath();

    public abstract Source listSource();

    public void setBytes(ByteString r1) {
        this.bytes = r1;
    }

    public void setExceptionBytes(ByteString r1) {
        this.exceptionBytes = r1;
    }
}
