package okio;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u000bH\u0002¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0010R\"\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u000b0\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lokio/SegmentPool;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lokio/Segment;", "take", "()Lokio/Segment;", "segment", _UrlKt.FRAGMENT_ENCODE_SET, "recycle", "(Lokio/Segment;)V", "Ljava/util/concurrent/atomic/AtomicReference;", "firstRef", "()Ljava/util/concurrent/atomic/AtomicReference;", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_SIZE", "I", "getMAX_SIZE", "()I", "LOCK", "Lokio/Segment;", "HASH_BUCKET_COUNT", _UrlKt.FRAGMENT_ENCODE_SET, "hashBuckets", "[Ljava/util/concurrent/atomic/AtomicReference;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SegmentPool {
    private static final int HASH_BUCKET_COUNT = 0;
    public static final SegmentPool INSTANCE = null;
    private static final Segment LOCK = null;
    private static final int MAX_SIZE = 0;
    private static final AtomicReference<Segment>[] hashBuckets = null;

    static {
        INSTANCE = new SegmentPool();
        MAX_SIZE = 65536;
        int r0 = 0;
        LOCK = new Segment(new byte[0], 0, 0, false, false);
        int r1 = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        HASH_BUCKET_COUNT = r1;
        AtomicReference<Segment>[] r2 = new AtomicReference[r1];
    L3:
        if (r0 >= r1) goto L5;
        r2[r0] = new AtomicReference();
        r0 = r0 + 1;
        goto L3
    L5:
        hashBuckets = r2;
    }

    private SegmentPool() {
    }

    private final AtomicReference<Segment> firstRef() {
        return hashBuckets[(int) (Thread.currentThread().getId() & (HASH_BUCKET_COUNT - 1))];
    }

    @JvmStatic
    public static final void recycle(Segment r5) {
        if (r5.next == null) goto L5;
    L22:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return;
    L5:
        if (r5.prev != null) goto L22;
        if (r5.shared == true) goto L24;
        AtomicReference<Segment> r0 = INSTANCE.firstRef();
        Segment r1 = LOCK;
        Segment r2 = r0.getAndSet(r1);
        if (r2 != r1) goto L13;
        return;
    L13:
        if (r2 == null) goto L15;
        int r3 = r2.limit;
    L17:
        if (r3 < MAX_SIZE) goto L20;
        r0.set(r2);
        return;
    L20:
        r5.next = r2;
        r5.pos = 0;
        r5.limit = r3 + 8192;
        r0.set(r5);
        return;
    L15:
        r3 = 0;
        goto L17
    }

    @JvmStatic
    public static final Segment take() {
        AtomicReference<Segment> r0 = INSTANCE.firstRef();
        Segment r1 = LOCK;
        Segment r2 = r0.getAndSet(r1);
        if (r2 != r1) goto L7;
        return new Segment();
    L7:
        if (r2 != null) goto L10;
        r0.set(null);
        return new Segment();
    L10:
        r0.set(r2.next);
        r2.next = null;
        r2.limit = 0;
        return r2;
    }
}
