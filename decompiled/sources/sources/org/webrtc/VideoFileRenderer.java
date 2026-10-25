package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.webrtc.EglBase;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class VideoFileRenderer implements VideoSink {
    private static final String TAG = "VideoFileRenderer";
    private EglBase eglBase;
    private final HandlerThread fileThread;
    private final Handler fileThreadHandler;
    private int frameCount;
    private final int outputFileHeight;
    private final String outputFileName;
    private final int outputFileWidth;
    private final ByteBuffer outputFrameBuffer;
    private final int outputFrameSize;
    private final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private final FileOutputStream videoOutFile;
    private YuvConverter yuvConverter;

    /* renamed from: org.webrtc.VideoFileRenderer$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ VideoFileRenderer this$0;
        final /* synthetic */ EglBase.Context val$sharedContext;

        public AnonymousClass1(VideoFileRenderer r1, EglBase.Context r2) {
            this.this$0 = r1;
            this.val$sharedContext = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoFileRenderer.m22404$$Nest$fputeglBase(this.this$0, EglBase.create(this.val$sharedContext, EglBase.CONFIG_PIXEL_BUFFER));
            VideoFileRenderer.m22403$$Nest$fgeteglBase(this.this$0).createDummyPbufferSurface();
            VideoFileRenderer.m22403$$Nest$fgeteglBase(this.this$0).makeCurrent();
            VideoFileRenderer.m22405$$Nest$fputyuvConverter(this.this$0, new YuvConverter());
        }
    }

    public static /* synthetic */ void $r8$lambda$I6NkLncGR9UCpq2xtzYD0Ddr_aw(VideoFileRenderer r0, VideoFrame r1) {
        r0.lambda$onFrame$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$Wzn6O_CcKXJgUe_iJxX7GsuGRWQ(VideoFileRenderer r0) {
        r0.lambda$release$3();
    }

    /* renamed from: $r8$lambda$lmgoJNFn-yMviyv6VLMnSS0SH78, reason: not valid java name */
    public static /* synthetic */ void m22401$r8$lambda$lmgoJNFnyMviyv6VLMnSS0SH78(VideoFileRenderer r0, CountDownLatch r1) {
        r0.lambda$release$2(r1);
    }

    /* renamed from: $r8$lambda$thBQkU5jAxiF_o-70UUp1dGNcyw, reason: not valid java name */
    public static /* synthetic */ void m22402$r8$lambda$thBQkU5jAxiF_o70UUp1dGNcyw(VideoFileRenderer r0, VideoFrame.I420Buffer r1, VideoFrame r2) {
        r0.lambda$renderFrameOnRenderThread$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgeteglBase, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EglBase m22403$$Nest$fgeteglBase(VideoFileRenderer r0) {
        return r0.eglBase;
    }

    /* renamed from: -$$Nest$fputeglBase, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22404$$Nest$fputeglBase(VideoFileRenderer r0, EglBase r1) {
        r0.eglBase = r1;
    }

    /* renamed from: -$$Nest$fputyuvConverter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22405$$Nest$fputyuvConverter(VideoFileRenderer r0, YuvConverter r1) {
        r0.yuvConverter = r1;
    }

    public VideoFileRenderer(String r3, int r4, int r5, EglBase.Context r6) {
        if ((r4 % 2) != 1) goto L5;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Does not support uneven width or height");
        throw null;
    L5:
        if ((r5 % 2) == 1) goto L8;
        this.outputFileName = r3;
        this.outputFileWidth = r4;
        this.outputFileHeight = r5;
        int r0 = ((r4 * r5) * 3) / 2;
        this.outputFrameSize = r0;
        this.outputFrameBuffer = ByteBuffer.allocateDirect(r0);
        FileOutputStream r02 = new FileOutputStream(r3);
        this.videoOutFile = r02;
        r02.write(("YUV4MPEG2 C420 W" + r4 + " H" + r5 + " Ip F30:1 A1:1\n").getBytes(Charset.forName("US-ASCII")));
        HandlerThread r32 = new HandlerThread("VideoFileRendererRenderThread");
        this.renderThread = r32;
        r32.start();
        Handler r42 = new Handler(r32.getLooper());
        this.renderThreadHandler = r42;
        HandlerThread r33 = new HandlerThread("VideoFileRendererFileThread");
        this.fileThread = r33;
        r33.start();
        this.fileThreadHandler = new Handler(r33.getLooper());
        ThreadUtils.invokeAtFrontUninterruptibly(r42, new AnonymousClass1(this, r6));
    }

    private /* synthetic */ void lambda$onFrame$0(VideoFrame r1) {
        renderFrameOnRenderThread(r1);
    }

    private /* synthetic */ void lambda$release$2(CountDownLatch r2) {
        this.yuvConverter.release();
        this.eglBase.release();
        this.renderThread.quit();
        r2.countDown();
    }

    private /* synthetic */ void lambda$release$3() {
        this.videoOutFile.close();     // Catch: IOException -> L6
        Logging.d(TAG, "Video written to disk as " + this.outputFileName + ". The number of frames is " + this.frameCount + " and the dimensions of the frames are " + this.outputFileWidth + "x" + this.outputFileHeight + ".");     // Catch: IOException -> L6
        this.fileThread.quit();
        return;
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error closing output file", e);
    }

    private /* synthetic */ void lambda$renderFrameOnRenderThread$1(VideoFrame.I420Buffer r11, VideoFrame r12) {
        YuvHelper.I420Rotate(r11.getDataY(), r11.getStrideY(), r11.getDataU(), r11.getStrideU(), r11.getDataV(), r11.getStrideV(), this.outputFrameBuffer, r11.getWidth(), r11.getHeight(), r12.getRotation());
        r11.release();
        this.videoOutFile.write("FRAME\n".getBytes(Charset.forName("US-ASCII")));     // Catch: IOException -> L6
        this.videoOutFile.write(this.outputFrameBuffer.array(), this.outputFrameBuffer.arrayOffset(), this.outputFrameSize);     // Catch: IOException -> L6
        this.frameCount++;
        return;
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error writing video to disk", e);
    }

    private void renderFrameOnRenderThread(VideoFrame r9) {
        VideoFrame.Buffer r0 = r9.getBuffer();
        if ((r9.getRotation() % 180) != 0) goto L6;
        int r1 = this.outputFileWidth;
    L5:
        int r5 = r1;
        if ((r9.getRotation() % 180) != 0) goto L11;
        int r12 = this.outputFileHeight;
    L10:
        int r6 = r12;
        float r13 = r0.getWidth() / r0.getHeight();
        float r2 = r5 / r6;
        int r3 = r0.getWidth();
        int r4 = r0.getHeight();
        if (r2 <= r13) goto L15;
        r4 = (int) (r4 * (r13 / r2));
    L16:
        VideoFrame.Buffer r02 = r0.cropAndScale((r0.getWidth() - r3) / 2, (r0.getHeight() - r4) / 2, r3, r4, r5, r6);
        r9.release();
        VideoFrame.I420Buffer r14 = r02.toI420();
        r02.release();
        this.fileThreadHandler.post(new VideoFileRenderer$$ExternalSyntheticLambda3(this, r14, r9));
        return;
    L15:
        r3 = (int) (r3 * (r2 / r13));
        goto L16
    L11:
        r12 = this.outputFileWidth;
        goto L10
    L6:
        r1 = this.outputFileHeight;
        goto L5
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame r3) {
        r3.retain();
        this.renderThreadHandler.post(new VideoFileRenderer$$ExternalSyntheticLambda0(this, r3));
    }

    public void release() {
        CountDownLatch r0 = new CountDownLatch(1);
        this.renderThreadHandler.post(new VideoFileRenderer$$ExternalSyntheticLambda1(this, r0));
        ThreadUtils.awaitUninterruptibly(r0);
        this.fileThreadHandler.post(new VideoFileRenderer$$ExternalSyntheticLambda2(this));
        this.fileThread.join();     // Catch: InterruptedException -> L5
        return;
    L5:
        e = move-exception;
        Thread.currentThread().interrupt();
        Logging.e(TAG, "Interrupted while waiting for the write to disk to complete.", e);
    }
}
