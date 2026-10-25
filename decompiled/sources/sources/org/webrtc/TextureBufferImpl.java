package org.webrtc;

import android.graphics.Matrix;
import android.os.Handler;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class TextureBufferImpl implements VideoFrame.TextureBuffer {
    private final int height;
    private final int id;
    private final RefCountDelegate refCountDelegate;
    private final RefCountMonitor refCountMonitor;
    private final Handler toI420Handler;
    private final Matrix transformMatrix;
    private final VideoFrame.TextureBuffer.Type type;
    private final int unscaledHeight;
    private final int unscaledWidth;
    private final int width;
    private final YuvConverter yuvConverter;

    /* renamed from: org.webrtc.TextureBufferImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements RefCountMonitor {
        final /* synthetic */ Runnable val$releaseCallback;

        public AnonymousClass1(Runnable r1) {
            this.val$releaseCallback = r1;
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onDestroy(TextureBufferImpl r1) {
            Runnable r0 = this.val$releaseCallback;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onRelease(TextureBufferImpl r1) {
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onRetain(TextureBufferImpl r1) {
        }
    }

    /* renamed from: org.webrtc.TextureBufferImpl$2, reason: invalid class name */
    public class AnonymousClass2 implements RefCountMonitor {
        final /* synthetic */ TextureBufferImpl this$0;

        public AnonymousClass2(TextureBufferImpl r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onDestroy(TextureBufferImpl r1) {
            this.this$0.release();
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onRelease(TextureBufferImpl r1) {
            TextureBufferImpl.m22391$$Nest$fgetrefCountMonitor(this.this$0).onRelease(this.this$0);
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onRetain(TextureBufferImpl r1) {
            TextureBufferImpl.m22391$$Nest$fgetrefCountMonitor(this.this$0).onRetain(this.this$0);
        }
    }

    public interface RefCountMonitor {
        void onDestroy(TextureBufferImpl r1);

        void onRelease(TextureBufferImpl r1);

        void onRetain(TextureBufferImpl r1);
    }

    /* renamed from: $r8$lambda$GZBqCDxM32Xs9JFjkfh9p7-jRuE, reason: not valid java name */
    public static /* synthetic */ VideoFrame.I420Buffer m22389$r8$lambda$GZBqCDxM32Xs9JFjkfh9p7jRuE(TextureBufferImpl r0) {
        return r0.lambda$toI420$1();
    }

    /* renamed from: $r8$lambda$J39vvbks0RGNcrde1f-mZRcP-5I, reason: not valid java name */
    public static /* synthetic */ void m22390$r8$lambda$J39vvbks0RGNcrde1fmZRcP5I(TextureBufferImpl r0, RefCountMonitor r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetrefCountMonitor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RefCountMonitor m22391$$Nest$fgetrefCountMonitor(TextureBufferImpl r0) {
        return r0.refCountMonitor;
    }

    private TextureBufferImpl(int r1, int r2, int r3, int r4, VideoFrame.TextureBuffer.Type r5, int r6, Matrix r7, Handler r8, YuvConverter r9, RefCountMonitor r10) {
        this.unscaledWidth = r1;
        this.unscaledHeight = r2;
        this.width = r3;
        this.height = r4;
        this.type = r5;
        this.id = r6;
        this.transformMatrix = r7;
        this.toI420Handler = r8;
        this.yuvConverter = r9;
        this.refCountDelegate = new RefCountDelegate(new TextureBufferImpl$$ExternalSyntheticLambda0(this, r10));
        this.refCountMonitor = r10;
    }

    public TextureBufferImpl(int r12, int r13, VideoFrame.TextureBuffer.Type r14, int r15, Matrix r16, Handler r17, YuvConverter r18, Runnable r19) {
        this(r12, r13, r12, r13, r14, r15, r16, r17, r18, new AnonymousClass1(r19));
    }

    public TextureBufferImpl(int r12, int r13, VideoFrame.TextureBuffer.Type r14, int r15, Matrix r16, Handler r17, YuvConverter r18, RefCountMonitor r19) {
        this(r12, r13, r12, r13, r14, r15, r16, r17, r18, r19);
    }

    private TextureBufferImpl applyTransformMatrix(Matrix r12, int r13, int r14, int r15, int r16) {
        Matrix r7 = new Matrix(this.transformMatrix);
        r7.preConcat(r12);
        retain();
        return new TextureBufferImpl(r13, r14, r15, r16, this.type, this.id, r7, this.toI420Handler, this.yuvConverter, new AnonymousClass2(this));
    }

    private /* synthetic */ void lambda$new$0(RefCountMonitor r1) {
        r1.onDestroy(this);
    }

    private /* synthetic */ VideoFrame.I420Buffer lambda$toI420$1() {
        return this.yuvConverter.convert(this);
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public TextureBufferImpl applyTransformMatrix(Matrix r7, int r8, int r9) {
        return applyTransformMatrix(r7, r8, r9, r8, r9);
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public /* bridge */ /* synthetic */ VideoFrame.TextureBuffer applyTransformMatrix(Matrix r1, int r2, int r3) {
        return applyTransformMatrix(r1, r2, r3);
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int r4, int r5, int r6, int r7, int r8, int r9) {
        Matrix r42 = new Matrix();
        r42.preTranslate(r4 / this.width, (r1 - (r5 + r7)) / this.height);
        r42.preScale(r6 / this.width, r7 / this.height);
        return applyTransformMatrix(r42, Math.round((this.unscaledWidth * r6) / this.width), Math.round((this.unscaledHeight * r7) / this.height), r8, r9);
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public int getTextureId() {
        return this.id;
    }

    public Handler getToI420Handler() {
        return this.toI420Handler;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public Matrix getTransformMatrix() {
        return this.transformMatrix;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public VideoFrame.TextureBuffer.Type getType() {
        return this.type;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public int getUnscaledHeight() {
        return this.unscaledHeight;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public int getUnscaledWidth() {
        return this.unscaledWidth;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    public YuvConverter getYuvConverter() {
        return this.yuvConverter;
    }

    @Override // org.webrtc.VideoFrame.Buffer, org.webrtc.RefCounted
    public void release() {
        this.refCountMonitor.onRelease(this);
        this.refCountDelegate.release();
    }

    @Override // org.webrtc.VideoFrame.Buffer, org.webrtc.RefCounted
    public void retain() {
        this.refCountMonitor.onRetain(this);
        this.refCountDelegate.retain();
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        return (VideoFrame.I420Buffer) ThreadUtils.invokeAtFrontUninterruptibly(this.toI420Handler, new TextureBufferImpl$$ExternalSyntheticLambda1(this));
    }
}
