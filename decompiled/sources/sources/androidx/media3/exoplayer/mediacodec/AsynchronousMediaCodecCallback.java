package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.collection.CircularIntArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import java.util.ArrayDeque;

/* loaded from: classes4.dex */
final class AsynchronousMediaCodecCallback extends MediaCodec.Callback {
    private final CircularIntArray availableInputBuffers;
    private final CircularIntArray availableOutputBuffers;
    private final ArrayDeque<MediaCodec.BufferInfo> bufferInfos;
    private final HandlerThread callbackThread;
    private MediaFormat currentFormat;
    private final ArrayDeque<MediaFormat> formats;
    private Handler handler;
    private IllegalStateException internalException;
    private final Object lock;
    private MediaCodec.CryptoException mediaCodecCryptoException;
    private MediaCodec.CodecException mediaCodecException;
    private MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener;
    private long pendingFlushCount;
    private MediaFormat pendingOutputFormat;
    private boolean shutDown;

    /* renamed from: $r8$lambda$Fdco1oD1dm50W-d6ae2eyfideq4, reason: not valid java name */
    public static /* synthetic */ void m831$r8$lambda$Fdco1oD1dm50Wd6ae2eyfideq4(AsynchronousMediaCodecCallback r0) {
        r0.onFlushCompleted();
    }

    public AsynchronousMediaCodecCallback(HandlerThread r2) {
        this.lock = new Object();
        this.callbackThread = r2;
        this.availableInputBuffers = new CircularIntArray();
        this.availableOutputBuffers = new CircularIntArray();
        this.bufferInfos = new ArrayDeque();
        this.formats = new ArrayDeque();
    }

    private void addOutputFormat(MediaFormat r3) {
        this.availableOutputBuffers.addLast(-2);
        this.formats.add(r3);
    }

    private void flushInternal() {
        if (this.formats.isEmpty() == true) goto L5;
        this.pendingOutputFormat = this.formats.getLast();
    L5:
        this.availableInputBuffers.clear();
        this.availableOutputBuffers.clear();
        this.bufferInfos.clear();
        this.formats.clear();
    }

    private boolean isFlushingOrShutdown() {
        if (this.pendingFlushCount <= 0) goto L5;
        return true;
    L5:
        if (this.shutDown == true) goto L11;
        return false;
    L11:
        return true;
    }

    private void maybeThrowException() {
        maybeThrowInternalException();
        maybeThrowMediaCodecException();
        maybeThrowMediaCodecCryptoException();
    }

    private void maybeThrowInternalException() {
        IllegalStateException r0 = this.internalException;
        if (r0 != null) goto L5;
        return;
    L5:
        this.internalException = null;
        throw r0;
    }

    private void maybeThrowMediaCodecCryptoException() {
        MediaCodec.CryptoException r0 = this.mediaCodecCryptoException;
        if (r0 != null) goto L5;
        return;
    L5:
        this.mediaCodecCryptoException = null;
        throw r0;
    }

    private void maybeThrowMediaCodecException() {
        MediaCodec.CodecException r0 = this.mediaCodecException;
        if (r0 != null) goto L5;
        return;
    L5:
        this.mediaCodecException = null;
        throw r0;
    }

    private void onFlushCompleted() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.shutDown == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        long r1 = this.pendingFlushCount - 1;     // Catch: Throwable -> L8
        this.pendingFlushCount = r1;     // Catch: Throwable -> L8
        if (r1 <= 0) goto L15;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L15:
        if (r1 >= 0) goto L19;
        setInternalException(new IllegalStateException());     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L19:
        flushInternal();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }

    private void setInternalException(IllegalStateException r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.internalException = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public int dequeueInputBufferIndex() {
        Object r0 = this.lock;
        monitor-enter(r0);
        maybeThrowException();     // Catch: Throwable -> L8
        int r2 = -1;
        if (isFlushingOrShutdown() == false) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return -1;
    L11:
        if (this.availableInputBuffers.isEmpty() == true) goto L14;
        r2 = this.availableInputBuffers.popFirst();     // Catch: Throwable -> L8
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r2;
    L8:
        th = move-exception;
        throw th;
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo r9) {
        Object r1 = this.lock;
        monitor-enter(r1);
        maybeThrowException();     // Catch: Throwable -> L8
        if (isFlushingOrShutdown() == false) goto L11;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return -1;
    L11:
        if (this.availableOutputBuffers.isEmpty() == false) goto L14;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return -1;
    L14:
        int r0 = this.availableOutputBuffers.popFirst();     // Catch: Throwable -> L8
        if (r0 < 0) goto L18;
        Assertions.checkStateNotNull(this.currentFormat);     // Catch: Throwable -> L8
        MediaCodec.BufferInfo r8 = this.bufferInfos.remove();     // Catch: Throwable -> L8
        r9.set(r8.offset, r8.size, r8.presentationTimeUs, r8.flags);     // Catch: Throwable -> L8
    L20:
        monitor-exit(r1);     // Catch: Throwable -> L8
        return r0;
    L18:
        if (r0 != (-2)) goto L20;
        this.currentFormat = this.formats.remove();     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public void flush() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.pendingFlushCount++;
        ((Handler) Util.castNonNull(this.handler)).post(new AsynchronousMediaCodecCallback$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaFormat getOutputFormat() {
        Object r0 = this.lock;
        monitor-enter(r0);
        MediaFormat r1 = this.currentFormat;     // Catch: Throwable -> L8
        if (r1 == null) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r1;
    L11:
        throw new IllegalStateException();     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public void initialize(MediaCodec r3) {
        if (this.handler != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.callbackThread.start();
        Handler r02 = new Handler(this.callbackThread.getLooper());
        r3.setCallback(this, r02);
        this.handler = r02;
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.media.MediaCodec.Callback
    public void onCryptoError(MediaCodec r1, MediaCodec.CryptoException r2) {
        Object r12 = this.lock;
        monitor-enter(r12);
        this.mediaCodecCryptoException = r2;     // Catch: Throwable -> L7
        monitor-exit(r12);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(MediaCodec r1, MediaCodec.CodecException r2) {
        Object r12 = this.lock;
        monitor-enter(r12);
        this.mediaCodecException = r2;     // Catch: Throwable -> L7
        monitor-exit(r12);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(MediaCodec r2, int r3) {
        Object r22 = this.lock;
        monitor-enter(r22);
        this.availableInputBuffers.addLast(r3);     // Catch: Throwable -> L7
        MediaCodecAdapter.OnBufferAvailableListener r1 = this.onBufferAvailableListener;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.onInputBufferAvailable();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r22);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(MediaCodec r2, int r3, MediaCodec.BufferInfo r4) {
        Object r22 = this.lock;
        monitor-enter(r22);
        MediaFormat r0 = this.pendingOutputFormat;     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        addOutputFormat(r0);     // Catch: Throwable -> L7
        this.pendingOutputFormat = null;     // Catch: Throwable -> L7
    L9:
        this.availableOutputBuffers.addLast(r3);     // Catch: Throwable -> L7
        this.bufferInfos.add(r4);     // Catch: Throwable -> L7
        MediaCodecAdapter.OnBufferAvailableListener r1 = this.onBufferAvailableListener;     // Catch: Throwable -> L7
        if (r1 == null) goto L12;
        r1.onOutputBufferAvailable();     // Catch: Throwable -> L7
    L12:
        monitor-exit(r22);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(MediaCodec r1, MediaFormat r2) {
        Object r12 = this.lock;
        monitor-enter(r12);
        addOutputFormat(r2);     // Catch: Throwable -> L7
        this.pendingOutputFormat = null;     // Catch: Throwable -> L7
        monitor-exit(r12);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setOnBufferAvailableListener(MediaCodecAdapter.OnBufferAvailableListener r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.onBufferAvailableListener = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void shutdown() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.shutDown = true;     // Catch: Throwable -> L8
        this.callbackThread.quit();     // Catch: Throwable -> L8
        flushInternal();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
