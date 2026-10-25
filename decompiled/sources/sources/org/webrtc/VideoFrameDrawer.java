package org.webrtc;

import android.graphics.Matrix;
import android.graphics.Point;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import org.webrtc.GlGenericDrawer;
import org.webrtc.RendererCommon;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class VideoFrameDrawer {
    public static final String TAG = "VideoFrameDrawer";
    static final float[] srcPoints = null;
    private final float[] dstPoints;
    private VideoFrame lastI420Frame;
    private int renderHeight;
    private final Matrix renderMatrix;
    private final Matrix renderRotateMatrix;
    private final Point renderSize;
    private int renderWidth;
    private final YuvUploader yuvUploader;

    /* renamed from: org.webrtc.VideoFrameDrawer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$VideoFrame$TextureBuffer$Type = null;

        static {
            int[] r0 = new int[VideoFrame.TextureBuffer.Type.values().length];
            $SwitchMap$org$webrtc$VideoFrame$TextureBuffer$Type = r0;
            r0[VideoFrame.TextureBuffer.Type.OES.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$org$webrtc$VideoFrame$TextureBuffer$Type[VideoFrame.TextureBuffer.Type.RGB.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    public static class YuvUploader {
        private ByteBuffer copyBuffer;
        private int[] yuvTextures;

        private YuvUploader() {
        }

        public /* synthetic */ YuvUploader(VideoFrameDrawerIA r1) {
            this();
        }

        public int[] getYuvTextures() {
            return this.yuvTextures;
        }

        public void release() {
            this.copyBuffer = null;
            int[] r1 = this.yuvTextures;
            if (r1 == null) goto L6;
            GLES20.glDeleteTextures(3, r1, 0);
            this.yuvTextures = null;
            return;
        }

        public int[] uploadFromBuffer(VideoFrame.I420Buffer r5) {
            int[] r0 = {r5.getStrideY(), r5.getStrideU(), r5.getStrideV()};
            ByteBuffer[] r1 = {r5.getDataY(), r5.getDataU(), r5.getDataV()};
            return uploadYuvData(r5.getWidth(), r5.getHeight(), r0, r1);
        }

        public int[] uploadYuvData(int r17, int r18, int[] r19, ByteBuffer[] r20) {
            int r3 = r17 / 2;
            int[] r1 = {r17, r3, r3};
            int r32 = r18 / 2;
            int[] r2 = {r18, r32, r32};
            int r33 = 0;
            int r4 = 0;
            int r5 = 0;
        L4:
            if (r4 >= 3) goto L9;
            int r6 = r19[r4];
            int r7 = r1[r4];
            if (r6 <= r7) goto L8;
            r5 = Math.max(r5, r7 * r2[r4]);
        L8:
            r4 = r4 + 1;
            goto L4
        L9:
            if (r5 <= 0) goto L16;
            ByteBuffer r42 = this.copyBuffer;
            if (r42 != null) goto L13;
        L14:
            this.copyBuffer = ByteBuffer.allocateDirect(r5);
            goto L16
        L13:
            if (r42.capacity() < r5) goto L14;
        L16:
            if (this.yuvTextures != null) goto L20;
            this.yuvTextures = new int[3];
            int r43 = 0;
        L18:
            if (r43 >= 3) goto L20;
            this.yuvTextures[r43] = GlUtil.generateTexture(3553);
            r43 = r43 + 1;
        L20:
            if (r33 >= 3) goto L28;
            GLES20.glActiveTexture(33984 + r33);
            GLES20.glBindTexture(3553, this.yuvTextures[r33]);
            int r8 = r19[r33];
            int r10 = r1[r33];
            if (r8 != r10) goto L25;
            ByteBuffer r44 = r20[r33];
        L26:
            GLES20.glTexImage2D(3553, 0, 6409, r1[r33], r2[r33], 0, 6409, 5121, r44);
            r33 = r33 + 1;
            goto L20
        L25:
            YuvHelper.copyPlane(r20[r33], r8, this.copyBuffer, r10, r10, r2[r33]);
            r44 = this.copyBuffer;
            goto L26
        L28:
            return this.yuvTextures;
        }
    }

    static {
        srcPoints = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f};
    }

    public VideoFrameDrawer() {
        this.dstPoints = new float[6];
        this.renderSize = new Point();
        this.yuvUploader = new YuvUploader(null);
        this.renderMatrix = new Matrix();
        this.renderRotateMatrix = new Matrix();
    }

    private void calculateTransformedRenderSize(int r7, int r8, Matrix r9) {
        if (r9 != null) goto L5;
        this.renderWidth = r7;
        this.renderHeight = r8;
        return;
    L5:
        r9.mapPoints(this.dstPoints, srcPoints);
        int r0 = 0;
    L6:
        float[] r1 = this.dstPoints;
        if (r0 >= 3) goto L9;
        int r2 = r0 * 2;
        r1[r2] = r1[r2] * r7;
        int r22 = r2 + 1;
        r1[r22] = r1[r22] * r8;
        r0 = r0 + 1;
        goto L6
    L9:
        this.renderWidth = distance(r1[0], r1[1], r1[2], r1[3]);
        float[] r72 = this.dstPoints;
        this.renderHeight = distance(r72[0], r72[1], r72[4], r72[5]);
    }

    private static int distance(float r2, float r3, float r4, float r5) {
        return (int) Math.round(Math.hypot(r4 - r2, r5 - r3));
    }

    public static void drawTexture(RendererCommon.GlDrawer r15, VideoFrame.TextureBuffer r16, Matrix r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, boolean r26) {
        Matrix r0 = new Matrix(r16.getTransformMatrix());
        r0.preConcat(r17);
        float[] r7 = RendererCommon.convertMatrixFromAndroidGraphicsMatrix(r0);
        int r02 = AnonymousClass1.$SwitchMap$org$webrtc$VideoFrame$TextureBuffer$Type[r16.getType().ordinal()];
        if (r02 != 1) goto L5;
        r15.drawOes(r16.getTextureId(), r16.getWidth(), r16.getHeight(), r18, r19, r7, r20, r21, r22, r23, r24, r25, r26);
        return;
    L5:
        if (r02 != 2) goto L8;
        r15.drawRgb(r16.getTextureId(), r16.getWidth(), r16.getHeight(), r18, r19, r7, r20, r21, r22, r23, r24, r25, r26);
        return;
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("Unknown texture type.");
    }

    public void drawFrame(VideoFrame r2, RendererCommon.GlDrawer r3) {
        drawFrame(r2, r3, null);
    }

    public void drawFrame(VideoFrame r11, RendererCommon.GlDrawer r12, Matrix r13) {
        drawFrame(r11, r12, r13, 0, 0, r11.getRotatedWidth(), r11.getRotatedHeight(), false, false);
    }

    public void drawFrame(VideoFrame r25, RendererCommon.GlDrawer r26, Matrix r27, int r28, int r29, int r30, int r31, boolean r32, boolean r33) {
        if (r32 == false) goto L5;
        int r3 = r25.getRotatedHeight();
    L6:
        if (r32 == false) goto L8;
        int r4 = r25.getRotatedWidth();
    L9:
        calculateTransformedRenderSize(r3, r4, r27);
        if (this.renderWidth > 0) goto L12;
    L28:
        Logging.w(TAG, "Illegal frame size: " + this.renderWidth + "x" + this.renderHeight);
        return;
    L12:
        if (this.renderHeight <= 0) goto L28;
        boolean r34 = r25.getBuffer() instanceof VideoFrame.TextureBuffer;
        this.renderMatrix.reset();
        this.renderMatrix.preTranslate(0.5f, 0.5f);
        if (r34 == true) goto L17;
        this.renderMatrix.preScale(1.0f, -1.0f);
    L17:
        this.renderMatrix.preRotate(r25.getRotation());
        this.renderMatrix.preTranslate(-0.5f, -0.5f);
        if (r27 == null) goto L20;
        this.renderMatrix.preConcat(r27);
    L20:
        if (r34 == false) goto L24;
        this.lastI420Frame = null;
        drawTexture(r26, (VideoFrame.TextureBuffer) r25.getBuffer(), this.renderMatrix, r25.getRotatedWidth(), r25.getRotatedHeight(), this.renderWidth, this.renderHeight, r28, r29, r30, r31, r33);
        return;
    L24:
        if (r25 == this.lastI420Frame) goto L26;
        this.lastI420Frame = r25;
        VideoFrame.I420Buffer r2 = r25.getBuffer().toI420();
        this.yuvUploader.uploadFromBuffer(r2);
        r2.release();
    L26:
        r26.drawYuv(this.yuvUploader.getYuvTextures(), r25.getBuffer().getWidth(), r25.getBuffer().getHeight(), r25.getRotatedWidth(), r25.getRotatedHeight(), RendererCommon.convertMatrixFromAndroidGraphicsMatrix(this.renderMatrix), this.renderWidth, this.renderHeight, r28, r29, r30, r31, r33);
        return;
    L8:
        r4 = r25.getRotatedHeight();
        goto L9
    L5:
        r3 = r25.getRotatedWidth();
        goto L6
    }

    public void getRenderBufferBitmap(RendererCommon.GlDrawer r1, int r2, GlGenericDrawer.TextureCallback r3) {
        if ((r1 instanceof GlGenericDrawer) == false) goto L6;
        ((GlGenericDrawer) r1).getRenderBufferBitmap(r2, r3);
        return;
    }

    public VideoFrame.Buffer prepareBufferForViewportSize(VideoFrame.Buffer r1, int r2, int r3) {
        r1.retain();
        return r1;
    }

    public void release() {
        this.yuvUploader.release();
        this.lastI420Frame = null;
    }
}
