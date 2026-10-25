package org.webrtc;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.Matrix;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.telegram.messenger.FileLog;
import org.webrtc.RendererCommon;

/* loaded from: classes6.dex */
public class GlGenericDrawer implements RendererCommon.GlDrawer {
    private static final String DEFAULT_VERTEX_SHADER_STRING = "varying vec2 tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\nuniform mat4 tex_mat;\nvoid main() {\n  gl_Position = in_pos;\n  tc = (tex_mat * in_tc).xy;\n}\n";
    private static final FloatBuffer FULL_RECTANGLE_BUFFER = null;
    private static final FloatBuffer FULL_RECTANGLE_TEXTURE_BUFFER = null;
    private static final String INPUT_TEXTURE_COORDINATE_NAME = "in_tc";
    private static final String INPUT_VERTEX_COORDINATE_NAME = "in_pos";
    private static final int OES = 0;
    private static final int RGB = 1;
    private static final String TEXTURE_MATRIX_NAME = "tex_mat";
    private static final int YUV = 2;
    private GlShader[][] currentShader;
    private final String genericFragmentSource;
    private int[][] inPosLocation;
    private int[][] inTcLocation;
    private int[] renderFrameBuffer;
    private float[] renderMatrix;
    private int[] renderTexture;
    private float renderTextureDownscale;
    private int[] renderTextureHeight;
    private int[] renderTextureWidth;
    private final ShaderCallbacks shaderCallbacks;
    private int[][] texMatrixLocation;
    private int[][] texelLocation;
    private float[] textureMatrix;
    private final String vertexShader;

    public interface ShaderCallbacks {
        void onNewShader(GlShader r1);

        void onPrepareShader(GlShader r1, float[] r2, int r3, int r4, int r5, int r6);
    }

    public interface TextureCallback {
        void run(Bitmap r1, int r2);
    }

    static {
        FULL_RECTANGLE_BUFFER = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
        FULL_RECTANGLE_TEXTURE_BUFFER = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    }

    public GlGenericDrawer(String r7, String r8, ShaderCallbacks r9) {
        this.currentShader = (GlShader[][]) Array.newInstance(GlShader.class, new int[]{3, 3});
        Class r5 = Integer.TYPE;
        this.inPosLocation = (int[][]) Array.newInstance(r5, new int[]{3, 3});
        this.inTcLocation = (int[][]) Array.newInstance(r5, new int[]{3, 3});
        this.texMatrixLocation = (int[][]) Array.newInstance(r5, new int[]{3, 3});
        this.texelLocation = (int[][]) Array.newInstance(r5, new int[]{3, 3});
        this.renderTexture = new int[2];
        this.renderTextureWidth = new int[2];
        this.renderTextureHeight = new int[2];
        this.vertexShader = r7;
        this.genericFragmentSource = r8;
        this.shaderCallbacks = r9;
    }

    public GlGenericDrawer(String r2, ShaderCallbacks r3) {
        this(DEFAULT_VERTEX_SHADER_STRING, r2, r3);
    }

    public static String createFragmentShaderString(String r2, int r3, boolean r4) {
        StringBuilder r0 = new StringBuilder();
        if (r3 != 0) goto L5;
        r0.append("#extension GL_OES_EGL_image_external : require\n");
    L5:
        r0.append("precision highp float;\n");
        if (r4 == true) goto L9;
        r0.append("varying vec2 tc;\n");
    L9:
        if (r3 != 2) goto L11;
        r0.append("uniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\nvec4 sample(vec2 p) {\n  float y = texture2D(y_tex, p).r * 1.16438;\n  float u = texture2D(u_tex, p).r;\n  float v = texture2D(v_tex, p).r;\n  return vec4(y + 1.59603 * v - 0.874202,\n    y - 0.391762 * u - 0.812968 * v + 0.531668,\n    y + 2.01723 * u - 1.08563, 1);\n}\n");
        r0.append(r2);
    L19:
        return r0.toString();
    L11:
        if (r3 != 0) goto L13;
        String r32 = "samplerExternalOES";
    L14:
        r0.append("uniform ");
        r0.append(r32);
        r0.append(" tex;\n");
        if (r4 == false) goto L17;
        r0.append("precision mediump float;\nvarying vec2 tc;\nconst mediump vec3 satLuminanceWeighting = vec3(0.2126, 0.7152, 0.0722);\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\nvoid main(){\nint rad = 3;\nint diameter = 2 * rad + 1;\nvec4 sampleTex = vec4(0, 0, 0, 0);\nvec3 col = vec3(0, 0, 0);\nfloat weightSum = 0.0;\nfor(int i = 0; i < diameter; i++) {\nvec2 offset = vec2(float(i - rad) * texelWidthOffset, float(i - rad) * texelHeightOffset);\nsampleTex = vec4(texture2D(tex, tc.st+offset));\nfloat index = float(i);\nfloat boxWeight = float(rad) + 1.0 - abs(index - float(rad));\ncol += sampleTex.rgb * boxWeight;\nweightSum += boxWeight;\n}\nvec3 result = col / weightSum;\nlowp float satLuminance = dot(result.rgb, satLuminanceWeighting);\nlowp vec3 greyScaleColor = vec3(satLuminance);\ngl_FragColor = vec4(clamp(mix(greyScaleColor, result.rgb, 1.1), 0.0, 1.0), 1.0);\n}\n");
        goto L19
    L17:
        r0.append(r2.replace("sample(", "texture2D(tex, "));
        goto L19
    L13:
        r32 = "sampler2D";
        goto L14
    }

    private void ensureRenderTargetCreated(int r12, int r13, int r14) {
        if (this.renderFrameBuffer != null) goto L10;
        int[] r2 = new int[2];
        this.renderFrameBuffer = r2;
        GLES20.glGenFramebuffers(2, r2, 0);
        GLES20.glGenTextures(2, this.renderTexture, 0);
        int r0 = 0;
    L5:
        int[] r22 = this.renderTexture;
        if (r0 >= r22.length) goto L8;
        GLES20.glBindTexture(3553, r22[r0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        r0 = r0 + 1;
        goto L5
    L8:
        float[] r02 = new float[16];
        this.renderMatrix = r02;
        Matrix.setIdentityM(r02, 0);
    L10:
        if (this.renderTextureWidth[r14] == r12) goto L14;
        this.renderTextureDownscale = Math.max(1.0f, Math.max(r12, r13) / 50.0f);
        GLES20.glBindTexture(3553, this.renderTexture[r14]);
        float r1 = this.renderTextureDownscale;
        GLES20.glTexImage2D(3553, 0, 6408, (int) (r12 / r1), (int) (r13 / r1), 0, 6408, 5121, null);
        this.renderTextureWidth[r14] = r12;
        this.renderTextureHeight[r14] = r13;
        return;
    }

    private void prepareShader(int r19, float[] r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27) {
        if (r27 == 0) goto L5;
        boolean r5 = true;
    L6:
        GlShader r6 = this.currentShader[r19][r27];
        if (r6 == null) goto L33;
    L8:
        GlShader r11 = r6;
        r11.useProgram();
        if (r5 == false) goto L28;
        int r52 = this.texelLocation[r19][0];
        float r62 = 0.0f;
        if (r27 != 1) goto L23;
        float r9 = 1.0f / r21;
    L24:
        GLES20.glUniform1f(r52, r9);
        int r53 = this.texelLocation[r19][1];
        if (r27 != 2) goto L27;
        r62 = 1.0f / r22;
    L27:
        GLES20.glUniform1f(r53, r62);
        goto L28
    L23:
        r9 = 0.0f;
    L28:
        GLES20.glEnableVertexAttribArray(this.inPosLocation[r19][r27]);
        GLES20.glVertexAttribPointer(this.inPosLocation[r19][r27], 2, 5126, false, 0, FULL_RECTANGLE_BUFFER);
        GLES20.glEnableVertexAttribArray(this.inTcLocation[r19][r27]);
        GLES20.glVertexAttribPointer(this.inTcLocation[r19][r27], 2, 5126, false, 0, FULL_RECTANGLE_TEXTURE_BUFFER);
        GLES20.glUniformMatrix4fv(this.texMatrixLocation[r19][r27], 1, false, r20, 0);
        this.shaderCallbacks.onPrepareShader(r11, r20, r23, r24, r25, r26);
        GlUtil.checkNoGLES2Error("Prepare shader");
        return;
    L33:
        r6 = createShader(r19, r5);     // Catch: Exception -> L30
        this.currentShader[r19][r27] = r6;
        r6.useProgram();
        if (r19 != 2) goto L13;
        GLES20.glUniform1i(r6.getUniformLocation("y_tex"), 0);
        GLES20.glUniform1i(r6.getUniformLocation("u_tex"), 1);
        GLES20.glUniform1i(r6.getUniformLocation("v_tex"), 2);
    L14:
        GlUtil.checkNoGLES2Error("Create shader");
        this.shaderCallbacks.onNewShader(r6);
        if (r5 == false) goto L17;
        this.texelLocation[r19][0] = r6.getUniformLocation("texelWidthOffset");
        this.texelLocation[r19][1] = r6.getUniformLocation("texelHeightOffset");
    L17:
        this.texMatrixLocation[r19][r27] = r6.getUniformLocation(TEXTURE_MATRIX_NAME);
        this.inPosLocation[r19][r27] = r6.getAttribLocation(INPUT_VERTEX_COORDINATE_NAME);
        this.inTcLocation[r19][r27] = r6.getAttribLocation(INPUT_TEXTURE_COORDINATE_NAME);
        goto L8
    L13:
        GLES20.glUniform1i(r6.getUniformLocation("tex"), 0);
    L30:
        e = move-exception;
        FileLog.e(e);
        return;
    L5:
        r5 = false;
        goto L6
    }

    public GlShader createShader(int r3, boolean r4) {
        return new GlShader(this.vertexShader, createFragmentShaderString(this.genericFragmentSource, r3, r4));
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawOes(int r22, int r23, int r24, int r25, int r26, float[] r27, int r28, int r29, int r30, int r31, int r32, int r33, boolean r34) {
        if (r34 == false) goto L25;
        ensureRenderTargetCreated(r23, r24, 1);
        this.textureMatrix = r27;
        float r5 = this.renderTextureDownscale;
        int r4 = (int) (r23 / r5);
        int r52 = (int) (r24 / r5);
        GLES20.glViewport(0, 0, r4, r52);
        int r17 = r4;
        int r18 = r52;
        prepareShader(0, this.renderMatrix, r25, r26, r28, r29, r32, r33, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, r22);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[1]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[1], 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(36197, 0);
        GLES20.glBindFramebuffer(36160, 0);
        if (r25 != r23) goto L8;
        r18 = r17;
        r17 = r18;
    L8:
        ensureRenderTargetCreated(r23, r24, 0);
        float[] r2 = this.renderMatrix;
        if (r25 == r23) goto L11;
        int r3 = r17;
    L13:
        if (r25 == r23) goto L15;
        int r42 = r18;
    L16:
        prepareShader(1, r2, r3, r42, r28, r29, r32, r33, 1);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[1]);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(r30, r31, r32, r33);
        if (r25 == r23) goto L19;
        int r35 = r17;
    L20:
        if (r25 == r23) goto L22;
        int r43 = r18;
    L23:
        prepareShader(1, r27, r35, r43, r28, r29, r32, r33, 2);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[0]);
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L22:
        r43 = r17;
        goto L23
    L19:
        r35 = r18;
        goto L20
    L15:
        r42 = r17;
        goto L16
    L11:
        r3 = r18;
        goto L13
    L25:
        prepareShader(0, r27, r25, r26, r28, r29, r32, r33, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, r22);
        GLES20.glViewport(r30, r31, r32, r33);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(36197, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawRgb(int r11, int r12, int r13, int r14, int r15, float[] r16, int r17, int r18, int r19, int r20, int r21, int r22, boolean r23) {
        prepareShader(1, r16, r14, r15, r17, r18, r21, r22, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r11);
        GLES20.glViewport(r19, r20, r21, r22);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(3553, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawYuv(int[] r22, int r23, int r24, int r25, int r26, float[] r27, int r28, int r29, int r30, int r31, int r32, int r33, boolean r34) {
        if (r34 == false) goto L33;
        if (r23 <= 0) goto L33;
        if (r24 <= 0) goto L33;
        this.textureMatrix = r27;
        ensureRenderTargetCreated(r23, r24, 1);
        float r6 = this.renderTextureDownscale;
        int r5 = (int) (r23 / r6);
        int r62 = (int) (r24 / r6);
        GLES20.glViewport(0, 0, r5, r62);
        int r17 = r5;
        int r18 = r62;
        prepareShader(2, this.renderMatrix, r25, r26, r28, r29, r32, r33, 0);
        int r2 = 0;
    L7:
        if (r2 >= 3) goto L9;
        GLES20.glActiveTexture(r2 + 33984);
        GLES20.glBindTexture(3553, r22[r2]);
        r2 = r2 + 1;
        goto L7
    L9:
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[1]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[1], 0);
        GLES20.glDrawArrays(5, 0, 4);
        int r210 = 0;
    L10:
        if (r210 >= 3) goto L12;
        GLES20.glActiveTexture(r210 + 33984);
        GLES20.glBindTexture(3553, 0);
        r210 = r210 + 1;
        goto L10
    L12:
        GLES20.glBindFramebuffer(36160, 0);
        if (r25 != r23) goto L16;
        r18 = r17;
        r17 = r18;
    L16:
        ensureRenderTargetCreated(r23, r24, 0);
        float[] r211 = this.renderMatrix;
        if (r25 == r23) goto L19;
        int r3 = r17;
    L21:
        if (r25 == r23) goto L23;
        int r4 = r18;
    L24:
        prepareShader(1, r211, r3, r4, r28, r29, r32, r33, 1);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[1]);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(r30, r31, r32, r33);
        if (r25 == r23) goto L27;
        int r35 = r17;
    L28:
        if (r25 == r23) goto L30;
        int r42 = r18;
    L31:
        prepareShader(1, r27, r35, r42, r28, r29, r32, r33, 2);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[0]);
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L30:
        r42 = r17;
        goto L31
    L27:
        r35 = r18;
        goto L28
    L23:
        r4 = r17;
        goto L24
    L19:
        r3 = r18;
    L33:
        prepareShader(2, r27, r25, r26, r28, r29, r32, r33, 0);
        int r212 = 0;
    L34:
        if (r212 >= 3) goto L36;
        GLES20.glActiveTexture(r212 + 33984);
        GLES20.glBindTexture(3553, r22[r212]);
        r212 = r212 + 1;
        goto L34
    L36:
        GLES20.glViewport(r30, r31, r32, r33);
        GLES20.glDrawArrays(5, 0, 4);
        int r213 = 0;
    L37:
        if (r213 >= 3) goto L39;
        GLES20.glActiveTexture(r213 + 33984);
        GLES20.glBindTexture(3553, 0);
        r213 = r213 + 1;
        goto L37
    }

    public void getRenderBufferBitmap(int r11, TextureCallback r12) {
        if (this.renderFrameBuffer != null) goto L5;
    L14:
        r12.run(null, 0);
        return;
    L5:
        if (this.textureMatrix == null) goto L14;
        double r2 = Math.asin(r0[2]);
        if (r2 < 1.5707963267948966d) goto L10;
    L12:
        float r0 = this.renderTextureWidth[0];
        float r22 = this.renderTextureDownscale;
        int r5 = (int) (r0 / r22);
        int r6 = (int) (this.renderTextureHeight[0] / r22);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
        ByteBuffer r9 = ByteBuffer.allocateDirect((r5 * r6) * 4);
        GLES20.glReadPixels(0, 0, r5, r6, 6408, 5121, r9);
        Bitmap r10 = Bitmap.createBitmap(r5, r6, Bitmap.Config.ARGB_8888);
        r10.copyPixelsFromBuffer(r9);
        GLES20.glBindFramebuffer(36160, 0);
        r12.run(r10, r11);
        return;
    L10:
        if (r2 <= (-1.5707963267948966d)) goto L12;
        float[] r112 = this.textureMatrix;
        r11 = (int) ((-Math.atan((-r112[1]) / r112[0])) / 0.017453292519943295d);
        goto L12
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void release() {
        int r1 = 0;
    L4:
        if (r1 >= this.currentShader.length) goto L13;
        int r2 = 0;
    L6:
        GlShader[] r3 = this.currentShader[r1];
        if (r2 >= r3.length) goto L12;
        GlShader r32 = r3[r2];
        if (r32 == null) goto L11;
        r32.release();
        this.currentShader[r1][r2] = null;
    L11:
        r2 = r2 + 1;
        goto L6
    L12:
        r1 = r1 + 1;
        goto L4
    L13:
        int[] r12 = this.renderFrameBuffer;
        if (r12 == null) goto L21;
        GLES20.glDeleteFramebuffers(2, r12, 0);
        GLES20.glDeleteTextures(2, this.renderTexture, 0);
        return;
    }
}
