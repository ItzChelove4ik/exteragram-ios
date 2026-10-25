package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public class InstantCameraVideoEncoderOverlayHelper {
    private final FloatBuffer attributeTextureBuffer;
    private final FloatBuffer attributeVertexBuffer;
    private final int[] glFrameBuffers;
    private final int[] glTextures;
    private long logoPhaseNs;
    private final BlurProgram programRenderBlur;
    private final MixProgram programRenderMixed;
    private final Program programRenderTexture;
    private final Program programRenderWatermark;
    private final int videoHeight;
    private final int videoWidth;

    public static class BlurProgram extends Program {
        final int uniformOffsetHandle;

        public BlurProgram() {
            super(R.raw.round_blur_stage_1_frag);
            this.uniformOffsetHandle = GLES20.glGetUniformLocation(this.program, "texOffset");
        }
    }

    public static class MixProgram extends Program {
        final int uniformBlurredTextureHandle;
        final int uniformHalfResolutionHandle;

        public MixProgram() {
            super(R.raw.round_blur_stage_2_frag);
            this.uniformBlurredTextureHandle = GLES20.glGetUniformLocation(this.program, "bTexture");
            this.uniformHalfResolutionHandle = GLES20.glGetUniformLocation(this.program, "center");
        }
    }

    public static class Program {
        final int attributePositionHandle;
        final int attributeTextureHandle;
        final int fragmentShader;
        final int program;
        final int uniformTextureHandle;
        final int vertexShader;

        public Program(int r3) {
            int r0 = InstantCameraVideoEncoderOverlayHelper.m10923$$Nest$smcreateShader(35633, R.raw.round_blur_vert);
            this.vertexShader = r0;
            int r32 = InstantCameraVideoEncoderOverlayHelper.m10923$$Nest$smcreateShader(35632, r3);
            this.fragmentShader = r32;
            int r33 = InstantCameraVideoEncoderOverlayHelper.m10922$$Nest$smcreateProgram(r0, r32);
            this.program = r33;
            this.attributePositionHandle = GLES20.glGetAttribLocation(r33, "aPosition");
            this.attributeTextureHandle = GLES20.glGetAttribLocation(r33, "aTextureCoord");
            this.uniformTextureHandle = GLES20.glGetUniformLocation(r33, "sTexture");
        }

        public void destroy() {
            GLES20.glDeleteProgram(this.program);
            GLES20.glDeleteShader(this.vertexShader);
            GLES20.glDeleteShader(this.fragmentShader);
        }
    }

    /* renamed from: -$$Nest$smcreateProgram, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10922$$Nest$smcreateProgram(int r0, int r1) {
        return createProgram(r0, r1);
    }

    /* renamed from: -$$Nest$smcreateShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10923$$Nest$smcreateShader(int r0, int r1) {
        return createShader(r0, r1);
    }

    public InstantCameraVideoEncoderOverlayHelper(int r22, int r23) {
        this.programRenderTexture = new Program(R.raw.round_blur_stage_0_frag);
        this.programRenderWatermark = new Program(R.raw.round_blur_stage_3_frag);
        this.programRenderBlur = new BlurProgram();
        this.programRenderMixed = new MixProgram();
        this.glFrameBuffers = new int[1];
        int r3 = 5;
        int[] r4 = new int[5];
        this.glTextures = r4;
        this.videoWidth = r22;
        this.videoHeight = r23;
        float[] r6 = new float[232];
        setTextureCords(r6, 0, 0.0f, 1.0f, 1.0f, 0.0f);
        setTextureCords(r6, 8, 0.0f, 0.0f, 1.0f, 1.0f);
        float[] r7 = new float[36];
        setVertexCords(r7, 0, -1.0f, 1.0f, 1.0f, -1.0f);
        float[] r5 = r7;
        int r13 = 0;
        GLES20.glGenTextures(5, r4, 0);
        int r42 = 0;
    L4:
        if (r42 >= r3) goto L39;
        GLES20.glBindTexture(3553, this.glTextures[r42]);
        int r72 = 9728;
        if (r42 >= 2) goto L8;
        int r10 = 9729;
    L9:
        GLES20.glTexParameteri(3553, 10241, r10);
        if (r42 >= 2) goto L12;
        r72 = 9729;
    L12:
        GLES20.glTexParameteri(3553, 10240, r72);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        int r8 = 4;
        if (r42 != 4) goto L26;
        int r9 = Math.round(r22 * 0.2f);
        int r16 = Math.round((r22 * 28) / 1536.0f);
        int r73 = (r9 - r16) - r16;
        RLottieNative r102 = RLottieNative.createFromRawJson(AndroidUtilities.readRes(R.raw.plane_logo_plain));
        Bitmap r92 = Bitmap.createBitmap(r9, r9, Bitmap.Config.ARGB_8888);
        Bitmap r32 = Bitmap.createBitmap(r73 * 8, r73 * 4, Bitmap.Config.ALPHA_8);
        Canvas r12 = new Canvas(r32);
        int r11 = r13;
    L16:
        if (r11 >= 8) goto L25;
        int r15 = r13;
    L18:
        if (r15 >= r8) goto L24;
        int r132 = (r15 * 8) + r11;
        if (r132 < 27) goto L22;
        float[] r18 = r5;
        int r2 = r73;
        Bitmap r52 = r92;
        RLottieNative r14 = r102;
        int r19 = r11;
        int r20 = 4;
    L23:
        r15 = r15 + 1;
        r73 = r2;
        r92 = r52;
        r102 = r14;
        r5 = r18;
        r11 = r19;
        r8 = r20;
        goto L18
    L22:
        int r202 = (r132 * 8) + 16;
        r18 = r5;
        r52 = r92;
        r14 = r102;
        r19 = r11;
        r2 = r73;
        r20 = 4;
        setTextureCords(r6, r202, r11 / 8.0f, r15 / 4.0f, (r11 + 1) / 8.0f, (r15 + 1) / 4.0f);
        r14.getFrame(r132 * 2, r52, true);
        r12.drawBitmap(r52, (r2 * r19) - r16, (r2 * r15) - r16, null);
        goto L23
    L24:
        r11 = r11 + 1;
        r5 = r5;
        r13 = 0;
        goto L16
    L25:
        float[] r182 = r5;
        RLottieNative r142 = r102;
        float r103 = ((r73 / this.videoWidth) * 2.0f) - 1.0f;
        setVertexCords(r182, 24, -1.0f, r103, r103, -1.0f);
        GLUtils.texImage2D(3553, 0, r32, 0);
        r32.recycle();
        r92.recycle();
        r142.recycle();
        float[] r74 = r182;
    L38:
        r42 = r42 + 1;
        r5 = r74;
        r3 = 5;
        r13 = 0;
        goto L4
    L26:
        float[] r183 = r5;
        if (r42 != 3) goto L31;
        int r24 = Math.round((r22 * 372.0f) / 1536.0f);
        float r33 = (r24 / this.videoWidth) * 2.0f;
        r74 = r183;
        setVertexCords(r74, 12, 1.0f - r33, r33 - 1.0f, 1.0f, -1.0f);
        Bitmap r34 = AndroidUtilities.getBitmapFromRaw(R.raw.round_blur_overlay_text);
        if (r34 == null) goto L38;
        Bitmap r25 = Bitmap.createScaledBitmap(r34, r24, r24, true);
        Bitmap r53 = r25.extractAlpha();
        GLUtils.texImage2D(3553, 0, r53, 0);
        r53.recycle();
        r25.recycle();
        r34.recycle();
        goto L38
    L31:
        r74 = r183;
        int r26 = 48;
        if (r42 != 0) goto L34;
        int r112 = this.videoWidth;
    L35:
        if (r42 != 0) goto L37;
        r26 = this.videoHeight;
    L37:
        GLES20.glTexImage2D(3553, 0, 6408, r112, r26, 0, 6408, 5121, null);
        goto L38
    L34:
        r112 = 48;
        goto L35
    L8:
        r10 = 9728;
        goto L9
    L39:
        int r93 = r13;
        GLES20.glBindTexture(3553, r93);
        GLES20.glGenFramebuffers(1, this.glFrameBuffers, r93);
        FloatBuffer r1 = ByteBuffer.allocateDirect(144).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.attributeVertexBuffer = r1;
        r1.put(r5).position(r93);
        FloatBuffer r17 = ByteBuffer.allocateDirect(928).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.attributeTextureBuffer = r17;
        r17.put(r6).position(r93);
    }

    private static int createProgram(int r2, int r3) {
        int r0 = GLES20.glCreateProgram();
        GLES20.glAttachShader(r0, r2);
        GLES20.glAttachShader(r0, r3);
        GLES20.glLinkProgram(r0);
        int[] r22 = new int[1];
        GLES20.glGetProgramiv(r0, 35714, r22, 0);
        if (r22[0] != 0) goto L6;
        GLES20.glDeleteProgram(r0);
        return 0;
    L6:
        return r0;
    }

    private static int createShader(int r3, int r4) {
        int r32 = GLES20.glCreateShader(r3);
        if (r32 != 0) goto L5;
        return 0;
    L5:
        GLES20.glShaderSource(r32, AndroidUtilities.readRes(r4));
        GLES20.glCompileShader(r32);
        int[] r42 = new int[1];
        GLES20.glGetShaderiv(r32, 35713, r42, 0);
        if (r42[0] != 0) goto L9;
        FileLog.e("GlUtils: compile shader error: " + GLES20.glGetShaderInfoLog(r32));
        GLES20.glDeleteShader(r32);
        return 0;
    L9:
        return r32;
    }

    private static void setTextureCords(float[] r1, int r2, float r3, float r4, float r5, float r6) {
        r1[r2] = r3;
        r1[r2 + 1] = r6;
        r1[r2 + 2] = r5;
        r1[r2 + 3] = r6;
        r1[r2 + 4] = r3;
        r1[r2 + 5] = r4;
        r1[r2 + 6] = r5;
        r1[r2 + 7] = r4;
    }

    private static void setVertexCords(float[] r2, int r3, float r4, float r5, float r6, float r7) {
        r2[r3] = r4;
        r2[r3 + 1] = r7;
        r2[r3 + 2] = 0.0f;
        r2[r3 + 3] = r6;
        r2[r3 + 4] = r7;
        r2[r3 + 5] = 0.0f;
        r2[r3 + 6] = r4;
        r2[r3 + 7] = r5;
        r2[r3 + 8] = 0.0f;
        r2[r3 + 9] = r6;
        r2[r3 + 10] = r5;
        r2[r3 + 11] = 0.0f;
    }

    public void bind() {
        GLES20.glBindFramebuffer(36160, this.glFrameBuffers[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.glTextures[0], 0);
        GLES20.glViewport(0, 0, this.videoWidth, this.videoHeight);
    }

    public void destroy() {
        this.programRenderTexture.destroy();
        this.programRenderBlur.destroy();
        this.programRenderMixed.destroy();
        this.programRenderWatermark.destroy();
        GLES20.glDeleteTextures(5, this.glTextures, 0);
        GLES20.glDeleteFramebuffers(1, this.glFrameBuffers, 0);
    }

    public void render(long r22) {
        this.logoPhaseNs += Math.max(0, r22);
        int r1 = 3042;
        GLES20.glDisable(3042);
        Program r2 = this.programRenderTexture;
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.glTextures[1], 0);
        GLES20.glViewport(0, 0, 48, 48);
        GLES20.glUseProgram(r2.program);
        GLES20.glVertexAttribPointer(r2.attributePositionHandle, 3, 5126, false, 12, this.attributeVertexBuffer.position(0));
        GLES20.glEnableVertexAttribArray(r2.attributePositionHandle);
        GLES20.glVertexAttribPointer(r2.attributeTextureHandle, 2, 5126, false, 8, this.attributeTextureBuffer.position(0));
        GLES20.glEnableVertexAttribArray(r2.attributeTextureHandle);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.glTextures[0]);
        GLES20.glUniform1i(r2.uniformTextureHandle, 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisableVertexAttribArray(r2.attributeTextureHandle);
        GLES20.glDisableVertexAttribArray(r2.attributePositionHandle);
        GLES20.glUseProgram(0);
        int r23 = 0;
    L3:
        char r12 = 2;
        if (r23 >= 2) goto L19;
        BlurProgram r13 = this.programRenderBlur;
        int[] r14 = this.glTextures;
        if (r23 != 0) goto L8;
        char r15 = 2;
    L9:
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, r14[r15], 0);
        GLES20.glViewport(0, 0, 48, 48);
        GLES20.glUseProgram(r13.program);
        GLES20.glVertexAttribPointer(r13.attributePositionHandle, 3, 5126, false, 12, this.attributeVertexBuffer.position(0));
        GLES20.glEnableVertexAttribArray(r13.attributePositionHandle);
        GLES20.glVertexAttribPointer(r13.attributeTextureHandle, 2, 5126, false, 8, this.attributeTextureBuffer.position(0));
        GLES20.glEnableVertexAttribArray(r13.attributeTextureHandle);
        GLES20.glActiveTexture(33984);
        int[] r142 = this.glTextures;
        if (r23 != 0) goto L12;
        r12 = 1;
    L12:
        GLES20.glBindTexture(3553, r142[r12]);
        GLES20.glUniform1i(r13.uniformTextureHandle, 0);
        int r122 = r13.uniformOffsetHandle;
        float r143 = 0.0f;
        int r222 = r1;
        if (r23 != 0) goto L15;
        float r16 = 0.020833334f;
    L16:
        if (r23 != 1) goto L18;
        r143 = 0.020833334f;
    L18:
        GLES20.glUniform2f(r122, r16, r143);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisableVertexAttribArray(r13.attributeTextureHandle);
        GLES20.glDisableVertexAttribArray(r13.attributePositionHandle);
        GLES20.glUseProgram(0);
        r23 = r23 + 1;
        r1 = r222;
        goto L3
    L15:
        r16 = 0.0f;
        goto L16
    L8:
        r15 = 1;
        goto L9
    L19:
        int r223 = r1;
        MixProgram r17 = this.programRenderMixed;
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.glTextures[1], 0);
        GLES20.glViewport(0, 0, this.videoWidth, this.videoHeight);
        GLES20.glUseProgram(r17.program);
        GLES20.glVertexAttribPointer(r17.attributePositionHandle, 3, 5126, false, 12, this.attributeVertexBuffer.position(0));
        GLES20.glEnableVertexAttribArray(r17.attributePositionHandle);
        GLES20.glVertexAttribPointer(r17.attributeTextureHandle, 2, 5126, false, 8, this.attributeTextureBuffer.position(0));
        GLES20.glEnableVertexAttribArray(r17.attributeTextureHandle);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.glTextures[1]);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.glTextures[0]);
        GLES20.glUniform1i(r17.uniformTextureHandle, 0);
        GLES20.glUniform1i(r17.uniformBlurredTextureHandle, 1);
        GLES20.glUniform2f(r17.uniformHalfResolutionHandle, this.videoWidth / 2.0f, this.videoHeight / 2.0f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisableVertexAttribArray(r17.attributeTextureHandle);
        GLES20.glDisableVertexAttribArray(r17.attributePositionHandle);
        GLES20.glUseProgram(0);
        Program r18 = this.programRenderWatermark;
        GLES20.glEnable(r223);
        GLES20.glUseProgram(r18.program);
        GLES20.glActiveTexture(33984);
        int r24 = 0;
    L20:
        if (r24 >= 2) goto L26;
        if (r24 != 0) goto L24;
        GLES20.glVertexAttribPointer(r18.attributePositionHandle, 3, 5126, false, 12, this.attributeVertexBuffer.position(12));
        GLES20.glEnableVertexAttribArray(r18.attributePositionHandle);
        GLES20.glVertexAttribPointer(r18.attributeTextureHandle, 2, 5126, false, 8, this.attributeTextureBuffer.position(8));
        GLES20.glEnableVertexAttribArray(r18.attributeTextureHandle);
        GLES20.glBindTexture(3553, this.glTextures[3]);
    L25:
        GLES20.glUniform1i(r18.uniformTextureHandle, 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisableVertexAttribArray(r18.attributeTextureHandle);
        GLES20.glDisableVertexAttribArray(r18.attributePositionHandle);
        r24 = r24 + 1;
        goto L20
    L24:
        int r4 = (int) ((this.logoPhaseNs / 33333333) % 27);
        GLES20.glVertexAttribPointer(r18.attributePositionHandle, 3, 5126, false, 12, this.attributeVertexBuffer.position(24));
        GLES20.glEnableVertexAttribArray(r18.attributePositionHandle);
        GLES20.glVertexAttribPointer(r18.attributeTextureHandle, 2, 5126, false, 8, this.attributeTextureBuffer.position((r4 * 8) + 16));
        GLES20.glEnableVertexAttribArray(r18.attributeTextureHandle);
        GLES20.glBindTexture(3553, this.glTextures[4]);
        goto L25
    L26:
        GLES20.glUseProgram(0);
        GLES20.glDisable(r223);
    }
}
