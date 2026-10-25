package androidx.media3.decoder;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.ArrayDeque;

/* loaded from: classes3.dex */
public abstract class SimpleDecoder<I extends DecoderInputBuffer, O extends DecoderOutputBuffer, E extends DecoderException> implements Decoder<I, O, E> {
    private int availableInputBufferCount;
    private final I[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final O[] availableOutputBuffers;
    private final Thread decodeThread;
    private I dequeuedInputBuffer;
    private E exception;
    private boolean flushed;
    private final Object lock;
    private long outputStartTimeUs;
    private final ArrayDeque<I> queuedInputBuffers;
    private final ArrayDeque<O> queuedOutputBuffers;
    private boolean released;
    private int skippedOutputBufferCount;

    /* renamed from: androidx.media3.decoder.SimpleDecoder$1, reason: invalid class name */
    public class AnonymousClass1 extends Thread {
        final /* synthetic */ SimpleDecoder this$0;

        public AnonymousClass1(SimpleDecoder r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            SimpleDecoder.access$000(this.this$0);
        }
    }

    public SimpleDecoder(I[] r4, O[] r5) {
        this.lock = new Object();
        this.outputStartTimeUs = -9223372036854775807L;
        this.queuedInputBuffers = new ArrayDeque();
        this.queuedOutputBuffers = new ArrayDeque();
        this.availableInputBuffers = r4;
        this.availableInputBufferCount = r4.length;
        int r42 = 0;
        int r0 = 0;
    L4:
        if (r0 >= this.availableInputBufferCount) goto L6;
        ((I[]) this.availableInputBuffers)[r0] = createInputBuffer();
        r0 = r0 + 1;
        goto L4
    L6:
        this.availableOutputBuffers = r5;
        this.availableOutputBufferCount = r5.length;
    L8:
        if (r42 >= this.availableOutputBufferCount) goto L10;
        ((O[]) this.availableOutputBuffers)[r42] = createOutputBuffer();
        r42 = r42 + 1;
        goto L8
    L10:
        AnonymousClass1 r43 = new AnonymousClass1(this, "ExoPlayer:SimpleDecoder");
        this.decodeThread = r43;
        r43.start();
    }

    public static /* synthetic */ void access$000(SimpleDecoder r0) {
        r0.run();
    }

    private boolean canDecodeBuffer() {
        if (this.queuedInputBuffers.isEmpty() == false) goto L5;
        return false;
    L5:
        if (this.availableOutputBufferCount <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean decode() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L63:
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.released == true) goto L12;
        if (canDecodeBuffer() == true) goto L12;
        this.lock.wait();     // Catch: Throwable -> L9
    L12:
        if (this.released == false) goto L15;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L15:
        I r1 = this.queuedInputBuffers.removeFirst();     // Catch: Throwable -> L9
        O[] r3 = this.availableOutputBuffers;     // Catch: Throwable -> L9
        int r4 = this.availableOutputBufferCount - 1;     // Catch: Throwable -> L9
        this.availableOutputBufferCount = r4;     // Catch: Throwable -> L9
        O r32 = r3[r4];     // Catch: Throwable -> L9
        boolean r42 = this.flushed;     // Catch: Throwable -> L9
        this.flushed = false;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r1.isEndOfStream() == false) goto L20;
        r32.addFlag(4);
    L41:
        Object r43 = this.lock;
        monitor-enter(r43);
    L46:
        th = move-exception;
        throw th;
    L44:
        if (this.flushed == false) goto L48;
        r32.release();     // Catch: Throwable -> L46
    L54:
        releaseInputBufferInternal(r1);     // Catch: Throwable -> L46
        monitor-exit(r43);     // Catch: Throwable -> L46
        return true;
    L48:
        boolean r02 = r32.shouldBeSkipped;     // Catch: Throwable -> L46
        int r6 = this.skippedOutputBufferCount;
        if (r02 == true) goto L52;
        r32.skippedOutputBufferCount = r6;     // Catch: Throwable -> L46
        this.skippedOutputBufferCount = 0;     // Catch: Throwable -> L46
        this.queuedOutputBuffers.addLast(r32);     // Catch: Throwable -> L46
        goto L54
    L52:
        this.skippedOutputBufferCount = r6 + 1;     // Catch: Throwable -> L46
        r32.release();     // Catch: Throwable -> L46
        goto L54
    L20:
        r32.timeUs = r1.timeUs;
        if (r1.isFirstSample() == false) goto L24;
        r32.addFlag(134217728);
    L24:
        if (isAtLeastOutputStartTimeUs(r1.timeUs) == true) goto L66;
        r32.shouldBeSkipped = true;
    L66:
        E r03 = (E) decode(r1, r32, r42);
    L32:
        if (r03 == null) goto L41;
        Object r44 = this.lock;
        monitor-enter(r44);
        this.exception = r03;     // Catch: Throwable -> L38
        monitor-exit(r44);     // Catch: Throwable -> L38
        return false;
    L38:
        th = move-exception;
        throw th;
    L28:
        e = move-exception;
        r03 = (E) createUnexpectedDecodeException(e);
    L30:
        e = move-exception;
        r03 = (E) createUnexpectedDecodeException(e);
        goto L32
    }

    private void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer() == false) goto L6;
        this.lock.notify();
        return;
    }

    private void maybeThrowException() {
        E r0 = this.exception;
        if (r0 != null) goto L5;
        return;
    L5:
        throw r0;
    }

    private void releaseInputBufferInternal(I r4) {
        r4.clear();
        I[] r0 = this.availableInputBuffers;
        int r1 = this.availableInputBufferCount;
        this.availableInputBufferCount = r1 + 1;
        r0[r1] = r4;
    }

    private void releaseOutputBufferInternal(O r4) {
        r4.clear();
        O[] r0 = this.availableOutputBuffers;
        int r1 = this.availableOutputBufferCount;
        this.availableOutputBufferCount = r1 + 1;
        r0[r1] = r4;
    }

    private void run() {
    L9:
        if (decode() == true) goto L9;
        return;
    L6:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
    }

    public abstract I createInputBuffer();

    public abstract O createOutputBuffer();

    public abstract E createUnexpectedDecodeException(Throwable r1);

    public abstract E decode(I r1, O r2, boolean r3);

    @Override // androidx.media3.decoder.Decoder
    public final I dequeueInputBuffer() {
        Object r0 = this.lock;
        monitor-enter(r0);
        maybeThrowException();     // Catch: Throwable -> L15
        if (this.dequeuedInputBuffer != null) goto L7;
        boolean r1 = true;
    L8:
        Assertions.checkState(r1);     // Catch: Throwable -> L15
        int r12 = this.availableInputBufferCount;     // Catch: Throwable -> L15
        if (r12 != 0) goto L11;
        I r13 = null;
    L12:
        this.dequeuedInputBuffer = r13;     // Catch: Throwable -> L15
        monitor-exit(r0);     // Catch: Throwable -> L15
        return r13;
    L11:
        I[] r3 = this.availableInputBuffers;     // Catch: Throwable -> L15
        int r14 = r12 - 1;     // Catch: Throwable -> L15
        this.availableInputBufferCount = r14;     // Catch: Throwable -> L15
        r13 = r3[r14];     // Catch: Throwable -> L15
        goto L12
    L7:
        r1 = false;
    L15:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ Object dequeueInputBuffer() {
        return dequeueInputBuffer();
    }

    @Override // androidx.media3.decoder.Decoder
    public final O dequeueOutputBuffer() {
        Object r0 = this.lock;
        monitor-enter(r0);
        maybeThrowException();     // Catch: Throwable -> L9
        if (this.queuedOutputBuffers.isEmpty() == true) goto L7;
        O r2 = this.queuedOutputBuffers.removeFirst();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r2;
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L9:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ Object dequeueOutputBuffer() {
        return dequeueOutputBuffer();
    }

    @Override // androidx.media3.decoder.Decoder
    public final void flush() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.flushed = true;     // Catch: Throwable -> L8
        this.skippedOutputBufferCount = 0;     // Catch: Throwable -> L8
        I r1 = this.dequeuedInputBuffer;     // Catch: Throwable -> L8
        if (r1 == null) goto L11;
        releaseInputBufferInternal(r1);     // Catch: Throwable -> L8
        this.dequeuedInputBuffer = null;     // Catch: Throwable -> L8
    L11:
        if (this.queuedInputBuffers.isEmpty() == true) goto L14;
        releaseInputBufferInternal(this.queuedInputBuffers.removeFirst());     // Catch: Throwable -> L8
    L14:
        if (this.queuedOutputBuffers.isEmpty() == true) goto L16;
        this.queuedOutputBuffers.removeFirst().release();     // Catch: Throwable -> L8
        goto L14
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final boolean isAtLeastOutputStartTimeUs(long r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        long r1 = this.outputStartTimeUs;     // Catch: Throwable -> L13
        if (r1 != (-9223372036854775807L)) goto L7;
    L10:
        boolean r5 = true;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L13
        return r5;
    L7:
        if (r6 >= r1) goto L10;
        r5 = false;
    L13:
        th = move-exception;
        throw th;
    }

    public final void queueInputBuffer(I r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        maybeThrowException();     // Catch: Throwable -> L11
        if (r3 != this.dequeuedInputBuffer) goto L7;
        boolean r1 = true;
    L8:
        Assertions.checkArgument(r1);     // Catch: Throwable -> L11
        this.queuedInputBuffers.addLast(r3);     // Catch: Throwable -> L11
        maybeNotifyDecodeLoop();     // Catch: Throwable -> L11
        this.dequeuedInputBuffer = null;     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        r1 = false;
    L11:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(Object r1) {
        queueInputBuffer((DecoderInputBuffer) r1);
    }

    @Override // androidx.media3.decoder.Decoder
    public void release() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.released = true;     // Catch: Throwable -> L11
        this.lock.notify();     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        this.decodeThread.join();     // Catch: InterruptedException -> L9
        return;
    L9:
        Thread.currentThread().interrupt();
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public void releaseOutputBuffer(O r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        releaseOutputBufferInternal(r2);     // Catch: Throwable -> L7
        maybeNotifyDecodeLoop();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void setInitialInputBufferSize(int r4) {
        int r2 = 0;
        if (this.availableInputBufferCount != this.availableInputBuffers.length) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        I[] r3 = this.availableInputBuffers;
        int r02 = r3.length;
    L7:
        if (r2 >= r02) goto L9;
        r3[r2].ensureSpaceForWrite(r4);
        r2 = r2 + 1;
        goto L7
    L9:
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.decoder.Decoder
    public final void setOutputStartTimeUs(long r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.availableInputBufferCount != this.availableInputBuffers.length) goto L7;
    L12:
        boolean r1 = true;
    L13:
        Assertions.checkState(r1);     // Catch: Throwable -> L10
        this.outputStartTimeUs = r4;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L7:
        if (this.flushed == true) goto L12;
        r1 = false;
        goto L13
    }
}
