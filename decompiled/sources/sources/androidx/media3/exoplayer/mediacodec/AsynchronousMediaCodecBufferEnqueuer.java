package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoInfo;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
class AsynchronousMediaCodecBufferEnqueuer implements MediaCodecBufferEnqueuer {
    private static final ArrayDeque<MessageParams> MESSAGE_PARAMS_INSTANCE_POOL = null;
    private static final Object QUEUE_SECURE_LOCK = null;
    private final MediaCodec codec;
    private final ConditionVariable conditionVariable;
    private Handler handler;
    private final HandlerThread handlerThread;
    private final AtomicReference<RuntimeException> pendingRuntimeException;
    private boolean started;

    /* renamed from: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$1, reason: invalid class name */
    public class AnonymousClass1 extends Handler {
        final /* synthetic */ AsynchronousMediaCodecBufferEnqueuer this$0;

        public AnonymousClass1(AsynchronousMediaCodecBufferEnqueuer r1, Looper r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message r1) {
            AsynchronousMediaCodecBufferEnqueuer.access$000(this.this$0, r1);
        }
    }

    public static class MessageParams {
        public final MediaCodec.CryptoInfo cryptoInfo;
        public int flags;
        public int index;
        public int offset;
        public long presentationTimeUs;
        public int size;

        public MessageParams() {
            this.cryptoInfo = new MediaCodec.CryptoInfo();
        }

        public void setQueueParams(int r1, int r2, int r3, long r4, int r6) {
            this.index = r1;
            this.offset = r2;
            this.size = r3;
            this.presentationTimeUs = r4;
            this.flags = r6;
        }
    }

    static {
        MESSAGE_PARAMS_INSTANCE_POOL = new ArrayDeque();
        QUEUE_SECURE_LOCK = new Object();
    }

    public AsynchronousMediaCodecBufferEnqueuer(MediaCodec r2, HandlerThread r3) {
        this(r2, r3, new ConditionVariable());
    }

    public AsynchronousMediaCodecBufferEnqueuer(MediaCodec r1, HandlerThread r2, ConditionVariable r3) {
        this.codec = r1;
        this.handlerThread = r2;
        this.conditionVariable = r3;
        this.pendingRuntimeException = new AtomicReference();
    }

    public static /* synthetic */ void access$000(AsynchronousMediaCodecBufferEnqueuer r0, Message r1) {
        r0.doHandleMessage(r1);
    }

    private void blockUntilHandlerThreadIsIdle() {
        this.conditionVariable.close();
        ((Handler) Assertions.checkNotNull(this.handler)).obtainMessage(3).sendToTarget();
        this.conditionVariable.block();
    }

    private static void copy(CryptoInfo r2, MediaCodec.CryptoInfo r3) {
        r3.numSubSamples = r2.numSubSamples;
        r3.numBytesOfClearData = copy(r2.numBytesOfClearData, r3.numBytesOfClearData);
        r3.numBytesOfEncryptedData = copy(r2.numBytesOfEncryptedData, r3.numBytesOfEncryptedData);
        r3.key = (byte[]) Assertions.checkNotNull(copy(r2.key, r3.key));
        r3.iv = (byte[]) Assertions.checkNotNull(copy(r2.iv, r3.iv));
        r3.mode = r2.mode;
        r3.setPattern(new MediaCodec.CryptoInfo.Pattern(r2.encryptedBlocks, r2.clearBlocks));
    }

    private static byte[] copy(byte[] r2, byte[] r3) {
        if (r2 != null) goto L4;
        return r3;
    L4:
        if (r3 == null) goto L11;
        if (r3.length < r2.length) goto L11;
        System.arraycopy(r2, 0, r3, 0, r2.length);
        return r3;
    L11:
        return Arrays.copyOf(r2, r2.length);
    }

    private static int[] copy(int[] r2, int[] r3) {
        if (r2 != null) goto L4;
        return r3;
    L4:
        if (r3 == null) goto L11;
        if (r3.length < r2.length) goto L11;
        System.arraycopy(r2, 0, r3, 0, r2.length);
        return r3;
    L11:
        return Arrays.copyOf(r2, r2.length);
    }

    private void doHandleMessage(Message r9) {
        int r2 = r9.what;
        if (r2 != 1) goto L5;
        MessageParams r7 = (MessageParams) r9.obj;
        doQueueInputBuffer(r7.index, r7.offset, r7.size, r7.presentationTimeUs, r7.flags);
    L14:
        MessageParams r4 = r7;
    L16:
        if (r4 == null) goto L19;
        recycleMessageParams(r4);
        return;
    L19:
        return;
    L5:
        if (r2 == 2) goto L13;
        r4 = null;
        if (r2 != 3) goto L9;
        this.conditionVariable.open();
        goto L16
    L9:
        if (r2 == 4) goto L11;
        AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, null, new IllegalStateException(String.valueOf(r2)));
        goto L16
    L11:
        doSetParameters((Bundle) r9.obj);
        goto L16
    L13:
        r7 = (MessageParams) r9.obj;
        doQueueSecureInputBuffer(r7.index, r7.offset, r7.cryptoInfo, r7.presentationTimeUs, r7.flags);
        goto L14
    }

    private void doQueueInputBuffer(int r8, int r9, int r10, long r11, int r13) {
        this.codec.queueInputBuffer(r8, r9, r10, r11, r13);     // Catch: RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, null, e);
    }

    private void doQueueSecureInputBuffer(int r10, int r11, MediaCodec.CryptoInfo r12, long r13, int r15) {
        Object r1 = QUEUE_SECURE_LOCK;     // Catch: RuntimeException -> L11
        monitor-enter(r1);     // Catch: RuntimeException -> L11
        this.codec.queueSecureInputBuffer(r10, r11, r12, r13, r15);     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;     // Catch: RuntimeException -> L11
    L11:
        e = move-exception;
        AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, null, e);
    }

    private void doSetParameters(Bundle r2) {
        this.codec.setParameters(r2);     // Catch: RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, null, e);
    }

    private void flushHandlerThread() {
        ((Handler) Assertions.checkNotNull(this.handler)).removeCallbacksAndMessages(null);
        blockUntilHandlerThreadIsIdle();
    }

    private static MessageParams getMessageParams() {
        ArrayDeque<MessageParams> r0 = MESSAGE_PARAMS_INSTANCE_POOL;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (r0.isEmpty() == false) goto L11;
        MessageParams r1 = new MessageParams();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r1;
    L11:
        MessageParams r12 = r0.removeFirst();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    }

    private static void recycleMessageParams(MessageParams r1) {
        ArrayDeque<MessageParams> r0 = MESSAGE_PARAMS_INSTANCE_POOL;
        monitor-enter(r0);
        r0.add(r1);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecBufferEnqueuer
    public void flush() {
        if (this.started == false) goto L11;
        flushHandlerThread();     // Catch: InterruptedException -> L6
        return;
    L6:
        e = move-exception;
        Thread.currentThread().interrupt();
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecBufferEnqueuer
    public void maybeThrowException() {
        RuntimeException r1 = this.pendingRuntimeException.getAndSet(null);
        if (r1 != null) goto L5;
        return;
    L5:
        throw r1;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecBufferEnqueuer
    public void queueInputBuffer(int r8, int r9, int r10, long r11, int r13) {
        maybeThrowException();
        MessageParams r0 = getMessageParams();
        r0.setQueueParams(r8, r9, r10, r11, r13);
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(1, r0).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecBufferEnqueuer
    public void queueSecureInputBuffer(int r8, int r9, CryptoInfo r10, long r11, int r13) {
        maybeThrowException();
        MessageParams r0 = getMessageParams();
        r0.setQueueParams(r8, r9, 0, r11, r13);
        copy(r10, r0.cryptoInfo);
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(2, r0).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecBufferEnqueuer
    public void setParameters(Bundle r2) {
        maybeThrowException();
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(4, r2).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecBufferEnqueuer
    public void shutdown() {
        if (this.started == false) goto L5;
        flush();
        this.handlerThread.quit();
    L5:
        this.started = false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecBufferEnqueuer
    public void start() {
        if (this.started == true) goto L6;
        this.handlerThread.start();
        this.handler = new AnonymousClass1(this, this.handlerThread.getLooper());
        this.started = true;
        return;
    }
}
