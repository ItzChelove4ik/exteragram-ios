package org.webrtc;

import android.graphics.Matrix;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import org.telegram.messenger.FileLog;
import org.webrtc.GlGenericDrawer;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class YuvConverter {
    private static final String FRAGMENT_SHADER = "uniform vec2 xUnit;\nuniform vec4 coeffs;\n\nvoid main() {\n  gl_FragColor.r = coeffs.a + dot(coeffs.rgb,\n      sample(tc - 1.5 * xUnit).rgb);\n  gl_FragColor.g = coeffs.a + dot(coeffs.rgb,\n      sample(tc - 0.5 * xUnit).rgb);\n  gl_FragColor.b = coeffs.a + dot(coeffs.rgb,\n      sample(tc + 0.5 * xUnit).rgb);\n  gl_FragColor.a = coeffs.a + dot(coeffs.rgb,\n      sample(tc + 1.5 * xUnit).rgb);\n}\n";
    private final GlGenericDrawer drawer;
    private final GlTextureFrameBuffer i420TextureFrameBuffer;
    private final ShaderCallbacks shaderCallbacks;
    private final ThreadUtils.ThreadChecker threadChecker;
    private final VideoFrameDrawer videoFrameDrawer;

    public static class ShaderCallbacks implements GlGenericDrawer.ShaderCallbacks {
        private static final float[] uCoeffs = null;
        private static final float[] vCoeffs = null;
        private static final float[] yCoeffs = null;
        private float[] coeffs;
        private int coeffsLoc;
        private float stepSize;
        private int xUnitLoc;

        static {
            yCoeffs = new float[]{0.256788f, 0.504129f, 0.0979059f, 0.0627451f};
            uCoeffs = new float[]{-0.148223f, -0.290993f, 0.439216f, 0.501961f};
            vCoeffs = new float[]{0.439216f, -0.367788f, -0.0714274f, 0.501961f};
        }

        private ShaderCallbacks() {
        }

        public /* synthetic */ ShaderCallbacks(YuvConverterIA r1) {
            this();
        }

        @Override // org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onNewShader(GlShader r2) {
            this.xUnitLoc = r2.getUniformLocation("xUnit");
            this.coeffsLoc = r2.getUniformLocation("coeffs");
        }

        @Override // org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onPrepareShader(GlShader r1, float[] r2, int r3, int r4, int r5, int r6) {
            GLES20.glUniform4fv(this.coeffsLoc, 1, this.coeffs, 0);
            int r12 = this.xUnitLoc;
            float r0 = this.stepSize;
            float r32 = r3;
            GLES20.glUniform2f(r12, (r2[0] * r0) / r32, (r0 * r2[1]) / r32);
        }

        public void setPlaneU() {
            this.coeffs = uCoeffs;
            this.stepSize = 2.0f;
        }

        public void setPlaneV() {
            this.coeffs = vCoeffs;
            this.stepSize = 2.0f;
        }

        public void setPlaneY() {
            this.coeffs = yCoeffs;
            this.stepSize = 1.0f;
        }
    }

    public static /* synthetic */ void $r8$lambda$9fmNeaph03WHym_PhWvLEa_Kzro(ByteBuffer r0) {
        JniCommon.nativeFreeByteBuffer(r0);
    }

    public YuvConverter() {
        this(new VideoFrameDrawer());
    }

    public YuvConverter(VideoFrameDrawer r5) {
        ThreadUtils.ThreadChecker r0 = new ThreadUtils.ThreadChecker();
        this.threadChecker = r0;
        this.i420TextureFrameBuffer = new GlTextureFrameBuffer(6408);
        ShaderCallbacks r1 = new ShaderCallbacks(null);
        this.shaderCallbacks = r1;
        this.drawer = new GlGenericDrawer(FRAGMENT_SHADER, r1);
        this.videoFrameDrawer = r5;
        r0.detachThread();
    }

    public VideoFrame.I420Buffer convert(VideoFrame.TextureBuffer r19) {
        this.threadChecker.checkIsOnValidThread();
        VideoFrame.TextureBuffer r3 = (VideoFrame.TextureBuffer) this.videoFrameDrawer.prepareBufferForViewportSize(r19, r19.getWidth(), r19.getHeight());
        int r5 = r3.getWidth();
        int r6 = r3.getHeight();
        int r1 = ((r5 + 7) / 8) * 8;
        int r14 = (r6 + 1) / 2;
        int r2 = r6 + r14;
        ByteBuffer r15 = JniCommon.nativeAllocateByteBuffer(r1 * r2);
        int r11 = r1 / 4;
        Matrix r4 = new Matrix();
        r4.preTranslate(0.5f, 0.5f);
        r4.preScale(1.0f, -1.0f);
        r4.preTranslate(-0.5f, -0.5f);
        this.i420TextureFrameBuffer.setSize(r11, r2);     // Catch: Exception -> L19
        GLES20.glBindFramebuffer(36160, this.i420TextureFrameBuffer.getFrameBufferId());     // Catch: Exception -> L19
        GlUtil.checkNoGLES2Error("glBindFramebuffer");     // Catch: Exception -> L19
        this.shaderCallbacks.setPlaneY();     // Catch: Exception -> L19
        int r16 = r14;
        int r192 = r1;
        int r12 = 0;
        VideoFrameDrawer.drawTexture(this.drawer, r3, r4, r5, r6, r5, r6, 0, 0, r11, r6, false);     // Catch: Exception -> L15
        this.shaderCallbacks.setPlaneU();     // Catch: Exception -> L15
        VideoFrameDrawer.drawTexture(this.drawer, r3, r4, r5, r6, r5, r6, 0, r6, r11 / 2, r16, false);     // Catch: Exception -> L17
        this.shaderCallbacks.setPlaneV();     // Catch: Exception -> L17
        VideoFrameDrawer.drawTexture(this.drawer, r3, r4, r5, r6, r5, r6, r11 / 2, r6, r11 / 2, r16, false);     // Catch: Exception -> L17
        r16 = r16;
        ByteBuffer r13 = r15;
        GLES20.glReadPixels(0, 0, this.i420TextureFrameBuffer.getWidth(), this.i420TextureFrameBuffer.getHeight(), 6408, 5121, r13);     // Catch: Exception -> L13
        GlUtil.checkNoGLES2Error("YuvConverter.convert");     // Catch: Exception -> L13
        GLES20.glBindFramebuffer(36160, 0);     // Catch: Exception -> L13
    L22:
        int r0 = r192 * r6;
        int r22 = r192 / 2;
        int r42 = r0 + r22;
        r13.position(r12);
        r13.limit(r0);
        ByteBuffer r62 = r13.slice();
        r13.position(r0);
        int r7 = (r192 * (r16 - 1)) + r22;
        r13.limit(r0 + r7);
        ByteBuffer r8 = r13.slice();
        r13.position(r42);
        r13.limit(r42 + r7);
        ByteBuffer r10 = r13.slice();
        r3.release();
        return JavaI420Buffer.wrap(r5, r6, r62, r192, r8, r192, r10, r192, new YuvConverter$$ExternalSyntheticLambda0(r13));
    L13:
        e = e;
    L21:
        FileLog.e(e);
    L17:
        e = e;
        r16 = r16;
    L16:
        r13 = r15;
    L15:
        e = e;
    L19:
        e = e;
        r192 = r1;
        r12 = 0;
        r16 = r14;
        goto L16
    }

    public void release() {
        this.threadChecker.checkIsOnValidThread();
        this.drawer.release();
        this.i420TextureFrameBuffer.release();
        this.videoFrameDrawer.release();
        this.threadChecker.detachThread();
    }
}
