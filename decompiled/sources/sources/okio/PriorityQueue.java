package okio;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00138\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lokio/PriorityQueue;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "vacantIndex", "Lokio/AsyncTimeout;", "node", _UrlKt.FRAGMENT_ENCODE_SET, "heapifyUp", "(ILokio/AsyncTimeout;)V", "heapifyDown", "first", "()Lokio/AsyncTimeout;", "add", "(Lokio/AsyncTimeout;)V", "remove", "size", "I", _UrlKt.FRAGMENT_ENCODE_SET, "array", "[Lokio/AsyncTimeout;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/PriorityQueue\n*L\n1#1,514:1\n509#1,3:515\n509#1,3:518\n509#1,3:521\n509#1,3:524\n*S KotlinDebug\n*F\n+ 1 AsyncTimeout.kt\nokio/PriorityQueue\n*L\n415#1:515,3\n448#1:518,3\n481#1:521,3\n491#1:524,3\n*E\n"})
/* loaded from: classes.dex */
public final class PriorityQueue {

    @JvmField
    public AsyncTimeout[] array;

    @JvmField
    public int size;

    public PriorityQueue() {
        this.array = new AsyncTimeout[8];
    }

    private final void heapifyDown(int r10, AsyncTimeout r11) {
    L2:
        int r0 = r10 << 1;
        int r1 = r0 + 1;
        int r2 = this.size;
        if (r1 > r2) goto L8;
        AsyncTimeout[] r22 = this.array;
        AsyncTimeout r02 = r22[r0];
        AsyncTimeout r12 = r22[r1];
        long r5 = r02.getTimeoutAt$okio();
        if (Intrinsics.compare(0, r12.getTimeoutAt$okio() - r5) < 0) goto L10;
        r02 = r12;
    L10:
        long r13 = r11.getTimeoutAt$okio();
        if (Intrinsics.compare(0, r02.getTimeoutAt$okio() - r13) <= 0) goto L13;
        int r14 = r02.index;
        r02.index = r10;
        this.array[r10] = r02;
        r10 = r14;
    L13:
        this.array[r10] = r11;
        r11.index = r10;
        return;
    L8:
        if (r0 > r2) goto L13;
        r02 = this.array[r0];
        goto L10
    }

    private final void heapifyUp(int r9, AsyncTimeout r10) {
    L2:
        int r0 = r9 >> 1;
        if (r0 == 0) goto L7;
        AsyncTimeout r1 = this.array[r0];
        long r2 = r1.getTimeoutAt$okio();
        if (Intrinsics.compare(0, r10.getTimeoutAt$okio() - r2) <= 0) goto L7;
        r1.index = r9;
        this.array[r9] = r1;
        r9 = r0;
    L7:
        this.array[r9] = r10;
        r10.index = r9;
    }

    public final void add(AsyncTimeout r9) {
        int r0 = this.size + 1;
        this.size = r0;
        AsyncTimeout[] r1 = this.array;
        if (r0 != r1.length) goto L5;
        AsyncTimeout[] r2 = new AsyncTimeout[r0 * 2];
        ArraysKt.copyInto$default(r1, r2, 0, 0, 0, 14, null);
        this.array = r2;
    L5:
        heapifyUp(r0, r9);
    }

    public final AsyncTimeout first() {
        return this.array[1];
    }

    public final void remove(AsyncTimeout r10) {
        int r0 = r10.index;
        if (r0 == (-1)) goto L16;
        int r2 = this.size;
        AsyncTimeout[] r3 = this.array;
        AsyncTimeout r4 = r3[r2];
        r10.index = -1;
        r3[r2] = null;
        this.size = r2 - 1;
        if (r10 != r4) goto L7;
        return;
    L7:
        int r102 = Intrinsics.compare(0, r4.getTimeoutAt$okio() - r10.getTimeoutAt$okio());
        if (r102 != 0) goto L11;
        this.array[r0] = r4;
        r4.index = r0;
        return;
    L11:
        if (r102 >= 0) goto L14;
        heapifyDown(r0, r4);
        return;
    L14:
        heapifyUp(r0, r4);
        return;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
    }
}
