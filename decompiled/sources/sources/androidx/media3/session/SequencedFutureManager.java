package androidx.media3.session;

import android.os.Handler;
import androidx.collection.ArrayMap;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.ArrayList;

/* loaded from: classes4.dex */
final class SequencedFutureManager {
    private boolean isReleased;
    private final Object lock;
    private int nextSequenceNumber;
    private Runnable pendingLazyReleaseCallback;
    private Handler releaseCallbackHandler;
    private final ArrayMap<Integer, SequencedFuture<?>> seqToFutureMap;

    public static final class SequencedFuture<T> extends AbstractFuture<T> {
        private final T resultWhenClosed;
        private final int sequenceNumber;

        private SequencedFuture(int r1, T r2) {
            this.sequenceNumber = r1;
            this.resultWhenClosed = r2;
        }

        public static <T> SequencedFuture<T> create(int r1, T r2) {
            return new SequencedFuture(r1, r2);
        }

        public T getResultWhenClosed() {
            return this.resultWhenClosed;
        }

        public int getSequenceNumber() {
            return this.sequenceNumber;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean set(T r1) {
            return super.set(r1);
        }

        public void setWithTheValueOfResultWhenClosed() {
            set(this.resultWhenClosed);
        }
    }

    public SequencedFutureManager() {
        this.lock = new Object();
        this.seqToFutureMap = new ArrayMap();
    }

    public <T> SequencedFuture<T> createSequencedFuture(T r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        int r1 = obtainNextSequenceNumber();     // Catch: Throwable -> L7
        SequencedFuture<T> r42 = SequencedFuture.create(r1, r4);     // Catch: Throwable -> L7
        if (this.isReleased == false) goto L9;
        r42.setWithTheValueOfResultWhenClosed();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r42;
    L9:
        this.seqToFutureMap.put(Integer.valueOf(r1), r42);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public int obtainNextSequenceNumber() {
        Object r0 = this.lock;
        monitor-enter(r0);
        int r1 = this.nextSequenceNumber;     // Catch: Throwable -> L7
        this.nextSequenceNumber = r1 + 1;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void release() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.isReleased = true;     // Catch: Throwable -> L8
        ArrayList r1 = new ArrayList(this.seqToFutureMap.values());     // Catch: Throwable -> L8
        this.seqToFutureMap.clear();     // Catch: Throwable -> L8
        if (this.pendingLazyReleaseCallback == null) goto L10;
        ((Handler) Assertions.checkNotNull(this.releaseCallbackHandler)).post(this.pendingLazyReleaseCallback);     // Catch: Throwable -> L8
        this.pendingLazyReleaseCallback = null;     // Catch: Throwable -> L8
        this.releaseCallbackHandler = null;     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        int r4 = r1.size();
        int r02 = 0;
    L12:
        if (r02 >= r4) goto L14;
        Object r2 = r1.get(r02);
        r02 = r02 + 1;
        ((SequencedFuture) r2).setWithTheValueOfResultWhenClosed();
        goto L12
    L14:
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public <T> void setFutureResult(int r5, T r6) {
        Object r1 = this.lock;
        monitor-enter(r1);
        SequencedFuture<?> r52 = this.seqToFutureMap.remove(Integer.valueOf(r5));     // Catch: Throwable -> L9
        if (r52 == null) goto L13;
        if (r52.getResultWhenClosed().getClass() != r6.getClass()) goto L11;
        r52.set(r6);     // Catch: Throwable -> L9
        goto L13
    L11:
        Log.w("SequencedFutureManager", "Type mismatch, expected " + r52.getResultWhenClosed().getClass() + ", but was " + r6.getClass());     // Catch: Throwable -> L9
    L13:
        if (this.pendingLazyReleaseCallback != null) goto L15;
    L17:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L15:
        if (this.seqToFutureMap.isEmpty() == false) goto L17;
        release();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }
}
