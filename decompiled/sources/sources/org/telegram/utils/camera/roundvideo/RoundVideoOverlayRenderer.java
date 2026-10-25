package org.telegram.utils.camera.roundvideo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.opengl.GLES20;
import android.util.Size;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Components.RLottieNative;

/* loaded from: classes6.dex */
final class RoundVideoOverlayRenderer {
    private static final String GAUSSIAN_BLUR_FRAGMENT_SHADER = null;
    private static final String SWITCH_GAUSSIAN_BLUR_FRAGMENT_SHADER = null;
    private static WatermarkAtlas cachedWatermark360;
    private static WatermarkAtlas cachedWatermark480;
    private final CameraProgram cameraProgram;
    private FloatBuffer cameraTextureBuffer;
    private final boolean compositionEnabled;
    private final Program copyProgram;
    private final boolean explicitTextureLod;
    private final int[] frameBuffers;
    private final GaussianBlurProgram gaussianBlurProgram;
    private int historyReadTexture;
    private boolean historyValid;
    private int historyWriteTexture;
    private Size inputSize;
    private long lastHistoryFrameNs;
    private int logoFrame;
    private final MixProgram mixProgram;
    private float newSwitchBlurRadius;
    private final int outputSize;
    private FloatBuffer rotatedCameraTextureBuffer;
    private int sourceCropSize;
    private final GaussianBlurProgram switchGaussianBlurProgram;
    private int switchNewBackgroundTexture;
    private final int switchNewBlurSize;
    private boolean switchNewFrameCaptured;
    private int switchOldBackgroundTexture;
    private final TemporalProgram temporalProgram;
    private final FloatBuffer textureBuffer;
    private final int[] textures;
    private final TransitionProgram transitionProgram;
    private final FloatBuffer vertexBuffer;
    private final Program watermarkProgram;

    public static class CameraProgram extends Program {
        final int textureMatrixHandle;

        public CameraProgram(String r2) {
            super("uniform mat4 uTextureMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvarying vec2 vScreenTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = (uTextureMatrix * vec4(aTextureCoord.xy, 0.0, 1.0)).xy;\n    vScreenTextureCoord = aPosition.xy * 0.5 + 0.5;\n}\n", r2);
            this.textureMatrixHandle = GLES20.glGetUniformLocation(this.program, "uTextureMatrix");
        }
    }

    public static class GaussianBlurProgram extends Program {
        final int offsetHandle;

        public GaussianBlurProgram() {
            super("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n", RoundVideoOverlayRenderer.m22169$$Nest$sfgetGAUSSIAN_BLUR_FRAGMENT_SHADER());
            this.offsetHandle = GLES20.glGetUniformLocation(this.program, "texOffset");
        }

        public GaussianBlurProgram(String r2) {
            super("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n", r2);
            this.offsetHandle = GLES20.glGetUniformLocation(this.program, "texOffset");
        }
    }

    public static class MixProgram extends CameraProgram {
        final int blurredTextureHandle;
        final int maskTextureHandle;

        public MixProgram() {
            super("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nvarying vec2 vScreenTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform sampler2D bTexture;\nuniform sampler2D mTexture;\nvoid main() {\n    vec3 sharp = texture2D(sTexture, vTextureCoord).rgb;\n    vec3 blurred = texture2D(bTexture, vScreenTextureCoord).rgb * 0.25;\n    float mask = texture2D(mTexture, vScreenTextureCoord).a;\n    gl_FragColor = vec4(mix(blurred, sharp, mask), 1.0);\n}\n");
            int r0 = GLES20.glGetUniformLocation(this.program, "bTexture");
            this.blurredTextureHandle = r0;
            int r1 = GLES20.glGetUniformLocation(this.program, "mTexture");
            this.maskTextureHandle = r1;
            GLES20.glUseProgram(this.program);
            GLES20.glUniform1i(r0, 1);
            GLES20.glUniform1i(r1, 2);
        }
    }

    public static class Program {
        final int fragmentShader;
        final int positionHandle;
        final int program;
        final int textureCoordinateHandle;
        final int textureHandle;
        final int vertexShader;

        public Program(String r2, String r3) {
            int r22 = RoundVideoOverlayRenderer.m22171$$Nest$smcreateShader(35633, r2);
            this.vertexShader = r22;
            int r32 = RoundVideoOverlayRenderer.m22171$$Nest$smcreateShader(35632, r3);
            this.fragmentShader = r32;
            int r23 = RoundVideoOverlayRenderer.m22170$$Nest$smcreateProgram(r22, r32);
            this.program = r23;
            this.positionHandle = 0;
            this.textureCoordinateHandle = 1;
            int r0 = GLES20.glGetUniformLocation(r23, "sTexture");
            this.textureHandle = r0;
            GLES20.glUseProgram(r23);
            GLES20.glUniform1i(r0, 0);
        }

        public void release() {
            GLES20.glDeleteProgram(this.program);
            GLES20.glDeleteShader(this.vertexShader);
            GLES20.glDeleteShader(this.fragmentShader);
        }
    }

    public static class TemporalProgram extends CameraProgram {
        final int historyTextureHandle;
        final int historyWeightHandle;
        final int sampleStepXHandle;
        final int sampleStepYHandle;

        public TemporalProgram() {
            super("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nvarying vec2 vScreenTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform sampler2D hTexture;\nuniform vec2 sampleStepX;\nuniform vec2 sampleStepY;\nuniform float historyWeight;\nvoid main() {\n    vec3 current = texture2D(sTexture, vTextureCoord).rgb * 0.25;\n    current += (texture2D(sTexture, vTextureCoord + sampleStepX).rgb\n            + texture2D(sTexture, vTextureCoord - sampleStepX).rgb\n            + texture2D(sTexture, vTextureCoord + sampleStepY).rgb\n            + texture2D(sTexture, vTextureCoord - sampleStepY).rgb) * 0.125;\n    current += (texture2D(sTexture, vTextureCoord + sampleStepX + sampleStepY).rgb\n            + texture2D(sTexture, vTextureCoord + sampleStepX - sampleStepY).rgb\n            + texture2D(sTexture, vTextureCoord - sampleStepX + sampleStepY).rgb\n            + texture2D(sTexture, vTextureCoord - sampleStepX - sampleStepY).rgb) * 0.0625;\n    vec3 history = texture2D(hTexture, vScreenTextureCoord).rgb;\n    float difference = dot(abs(current - history), vec3(0.333333));\n    float motion = smoothstep(0.035, 0.18, difference);\n    float weight = mix(historyWeight, historyWeight * 0.65, motion);\n    gl_FragColor = vec4(mix(current, history, weight), 1.0);\n}\n");
            int r0 = GLES20.glGetUniformLocation(this.program, "hTexture");
            this.historyTextureHandle = r0;
            this.sampleStepXHandle = GLES20.glGetUniformLocation(this.program, "sampleStepX");
            this.sampleStepYHandle = GLES20.glGetUniformLocation(this.program, "sampleStepY");
            this.historyWeightHandle = GLES20.glGetUniformLocation(this.program, "historyWeight");
            GLES20.glUseProgram(this.program);
            GLES20.glUniform1i(r0, 1);
        }
    }

    public static class TransitionProgram extends Program {
        final int maskTextureHandle;
        final int mixHandle;
        final int newBackgroundHandle;
        final int newBlurHandle;
        final int newBlurTextureHandle;
        final int newTextureHandle;
        final int oldBackgroundHandle;
        final int oldBlurMixHandle;
        final int oldBlurStepHandle;
        final int oldMipBiasHandle;
        final int oldMipTextureHandle;

        public TransitionProgram(boolean r8, boolean r9) {
            super("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n", RoundVideoOverlayRenderer.m22172$$Nest$smcreateTransitionFragmentShader(r8, r9));
            int r92 = GLES20.glGetUniformLocation(this.program, "omTexture");
            this.oldMipTextureHandle = r92;
            int r0 = GLES20.glGetUniformLocation(this.program, "nTexture");
            this.newTextureHandle = r0;
            int r1 = GLES20.glGetUniformLocation(this.program, "nbTexture");
            this.newBlurTextureHandle = r1;
            int r2 = GLES20.glGetUniformLocation(this.program, "oldBackground");
            this.oldBackgroundHandle = r2;
            int r3 = GLES20.glGetUniformLocation(this.program, "newBackground");
            this.newBackgroundHandle = r3;
            int r4 = GLES20.glGetUniformLocation(this.program, "maskTexture");
            this.maskTextureHandle = r4;
            this.oldBlurStepHandle = GLES20.glGetUniformLocation(this.program, "oldBlurStep");
            this.oldMipBiasHandle = GLES20.glGetUniformLocation(this.program, "oldMipBias");
            this.oldBlurMixHandle = GLES20.glGetUniformLocation(this.program, "oldBlurMix");
            this.newBlurHandle = GLES20.glGetUniformLocation(this.program, "newBlur");
            this.mixHandle = GLES20.glGetUniformLocation(this.program, "mixValue");
            GLES20.glUseProgram(this.program);
            GLES20.glUniform1i(r92, 1);
            GLES20.glUniform1i(r0, 2);
            GLES20.glUniform1i(r1, 3);
            if (r8 == false) goto L6;
            GLES20.glUniform1i(r2, 4);
            GLES20.glUniform1i(r3, 5);
            GLES20.glUniform1i(r4, 6);
            return;
        }
    }

    public static final class WatermarkAtlas {
        final int frameSize;
        final int height;
        final int logoAtlasHeight;
        final ByteBuffer pixels;
        final int textSize;
        final int width;

        public WatermarkAtlas(int r1, int r2, int r3, int r4, int r5, ByteBuffer r6) {
            this.width = r1;
            this.height = r2;
            this.textSize = r3;
            this.frameSize = r4;
            this.logoAtlasHeight = r5;
            this.pixels = r6;
        }
    }

    /* renamed from: -$$Nest$sfgetGAUSSIAN_BLUR_FRAGMENT_SHADER, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22169$$Nest$sfgetGAUSSIAN_BLUR_FRAGMENT_SHADER() {
        return GAUSSIAN_BLUR_FRAGMENT_SHADER;
    }

    /* renamed from: -$$Nest$smcreateProgram, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22170$$Nest$smcreateProgram(int r0, int r1) {
        return createProgram(r0, r1);
    }

    /* renamed from: -$$Nest$smcreateShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22171$$Nest$smcreateShader(int r0, String r1) {
        return createShader(r0, r1);
    }

    /* renamed from: -$$Nest$smcreateTransitionFragmentShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22172$$Nest$smcreateTransitionFragmentShader(boolean r0, boolean r1) {
        return createTransitionFragmentShader(r0, r1);
    }

    static {
        GAUSSIAN_BLUR_FRAGMENT_SHADER = createGaussianBlurFragmentShader(4, 1.75f);
        SWITCH_GAUSSIAN_BLUR_FRAGMENT_SHADER = createGaussianBlurFragmentShader(9, 4.0f);
    }

    public RoundVideoOverlayRenderer(int r20, Size r21, int r22, boolean r23) {
        int[] r6 = new int[8];
        this.frameBuffers = r6;
        int[] r7 = new int[10];
        this.textures = r7;
        this.historyReadTexture = 0;
        this.historyWriteTexture = 1;
        this.switchOldBackgroundTexture = 0;
        this.switchNewBackgroundTexture = 1;
        this.newSwitchBlurRadius = -1.0f;
        this.outputSize = r20;
        int r10 = Math.max(1, r20 / 2);
        this.switchNewBlurSize = r10;
        this.inputSize = r21;
        this.sourceCropSize = r22;
        this.compositionEnabled = r23;
        boolean r11 = supportsExplicitTextureLod();
        this.explicitTextureLod = r11;
        this.cameraProgram = new CameraProgram("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        this.copyProgram = new Program("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n", "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        Program r12 = null;
        if (r23 == false) goto L5;
        TemporalProgram r13 = new TemporalProgram();
    L6:
        this.temporalProgram = r13;
        if (r23 == false) goto L9;
        MixProgram r132 = new MixProgram();
    L10:
        this.mixProgram = r132;
        this.transitionProgram = new TransitionProgram(r23, r11);
        this.gaussianBlurProgram = new GaussianBlurProgram();
        this.switchGaussianBlurProgram = new GaussianBlurProgram(SWITCH_GAUSSIAN_BLUR_FRAGMENT_SHADER);
        if (r23 == false) goto L13;
        r12 = new Program("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n", "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = vec4(1.0, 1.0, 1.0, texture2D(sTexture, vTextureCoord).a);\n}\n");
    L13:
        this.watermarkProgram = r12;
        float[] r122 = new float[656];
        setTextureCoordinates(r122, 0, 0.0f, 1.0f, 1.0f, 0.0f);
        float[] r133 = new float[48];
        setVertexCoordinates(r133, 0, -1.0f, 1.0f, 1.0f, -1.0f);
        this.cameraTextureBuffer = createCameraTextureBuffer(r21, r22, false);
        this.rotatedCameraTextureBuffer = createCameraTextureBuffer(r21, r22, true);
        GLES20.glGenTextures(r7.length, r7, 0);
        if (r23 == false) goto L16;
        createColorTexture(0, 48, 48, 9729);
        createColorTexture(1, 48, 48, 9729);
        createMaskTexture();
        createWatermarkAtlas(r133, r122);
        createColorTexture(8, 48, 48, 9729);
    L16:
        createColorTexture(4, r20, r20, 9729);
        createColorTexture(5, 512, 512, 9729);
        createColorTexture(6, r20, r20, 9729);
        createColorTexture(7, r10, r10, 9729);
        createColorTexture(9, r10, r10, 9729);
        GLES20.glBindTexture(3553, 0);
        GLES20.glGenFramebuffers(r6.length, r6, 0);
        if (r23 == false) goto L19;
        attachFrameBuffer(0, 0);
        attachFrameBuffer(1, 1);
        attachFrameBuffer(6, 8);
    L19:
        attachFrameBuffer(2, 4);
        attachFrameBuffer(3, 5);
        attachFrameBuffer(4, 6);
        attachFrameBuffer(5, 7);
        attachFrameBuffer(7, 9);
        GLES20.glBindFramebuffer(36160, 0);
        this.vertexBuffer = createBuffer(r133);
        this.textureBuffer = createBuffer(r122);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glEnableVertexAttribArray(1);
        return;
    L9:
        r132 = null;
        goto L10
    L5:
        r13 = null;
        goto L6
    }

    private void attach(int r4) {
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.textures[r4], 0);
    }

    private void attachFrameBuffer(int r2, int r3) {
        GLES20.glBindFramebuffer(36160, this.frameBuffers[r2]);
        attach(r3);
        int r1 = GLES20.glCheckFramebufferStatus(36160);
        if (r1 != 36053) goto L6;
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        return;
    L6:
        CstInsn$$ExternalSyntheticBUOutline0.m("Incomplete framebuffer: 0x", Integer.toHexString(r1));
    }

    private void bindCameraProgram(CameraProgram r9, float[] r10) {
        GLES20.glUseProgram(r9.program);
        this.vertexBuffer.position(0);
        GLES20.glVertexAttribPointer(r9.positionHandle, 3, 5126, false, 12, this.vertexBuffer);
        if (swapsTextureAxes(r10) == false) goto L6;
        FloatBuffer r8 = this.rotatedCameraTextureBuffer;
    L5:
        FloatBuffer r7 = r8;
        r7.position(0);
        GLES20.glVertexAttribPointer(r9.textureCoordinateHandle, 2, 5126, false, 8, r7);
        GLES20.glUniformMatrix4fv(r9.textureMatrixHandle, 1, false, r10, 0);
        return;
    L6:
        r8 = this.cameraTextureBuffer;
        goto L5
    }

    private static void bindExternalTexture(int r0, int r1, int r2) {
        GLES20.glActiveTexture(r0 + 33984);
        GLES20.glBindTexture(36197, r1);
    }

    private void bindProgram(Program r2, int r3) {
        bindProgram(r2, r3, 0);
    }

    private void bindProgram(Program r8, int r9, int r10) {
        GLES20.glUseProgram(r8.program);
        this.vertexBuffer.position(r10);
        GLES20.glVertexAttribPointer(r8.positionHandle, 3, 5126, false, 12, this.vertexBuffer);
        this.textureBuffer.position(r9);
        GLES20.glVertexAttribPointer(r8.textureCoordinateHandle, 2, 5126, false, 8, this.textureBuffer);
    }

    private void bindTexture(int r1, int r2, int r3) {
        GLES20.glActiveTexture(r1 + 33984);
        GLES20.glBindTexture(3553, this.textures[r2]);
    }

    private static float clamp01(float r1) {
        return Math.max(0.0f, Math.min(1.0f, r1));
    }

    private void configureTexture(int r1, int r2) {
        GLES20.glBindTexture(3553, this.textures[r1]);
        GLES20.glTexParameteri(3553, 10241, r2);
        GLES20.glTexParameteri(3553, 10240, r2);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
    }

    private static FloatBuffer createBuffer(float[] r2) {
        FloatBuffer r0 = ByteBuffer.allocateDirect(r2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        r0.put(r2).position(0);
        return r0;
    }

    private static FloatBuffer createCameraTextureBuffer(Size r3, int r4, boolean r5) {
        int r42 = Math.min(r4, Math.min(r3.getWidth(), r3.getHeight()));
        if (r5 == false) goto L5;
        int r0 = r3.getHeight();
    L6:
        if (r5 == false) goto L8;
        int r32 = r3.getWidth();
    L9:
        float r43 = r42;
        float r52 = r43 / (r0 * 2.0f);
        float r44 = r43 / (r32 * 2.0f);
        float r02 = 0.5f - r52;
        float r1 = 0.5f - r44;
        float r53 = r52 + 0.5f;
        float r45 = r44 + 0.5f;
        return createBuffer(new float[]{r02, r1, r53, r1, r02, r45, r53, r45});
    L8:
        r32 = r3.getHeight();
        goto L9
    L5:
        r0 = r3.getWidth();
        goto L6
    }

    private void createColorTexture(int r10, int r11, int r12, int r13) {
        configureTexture(r10, r13);
        GLES20.glTexImage2D(3553, 0, 6408, r11, r12, 0, 6408, 5121, null);
    }

    private static String createGaussianBlurFragmentShader(int r15, float r16) {
        int r2 = -r15;
        double r3 = 0.0d;
    L3:
        double r5 = 2.0d;
        if (r2 > r15) goto L6;
        double r9 = r16;
        r3 = r3 + Math.exp((-(r2 * r2)) / ((2.0d * r9) * r9));
        r2 = r2 + 1;
        goto L3
    L6:
        StringBuilder r22 = new StringBuilder(4096);
        r22.append("precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform vec2 texOffset;\nvoid main() {\n    vec3 color = texture2D(sTexture, vTextureCoord).rgb * ");
        r22.append(Float.toString((float) (1.0d / r3)));
        r22.append(";\n");
        int r8 = 1;
    L7:
        if (r8 > r15) goto L17;
        double r11 = r16;
        double r13 = (r11 * r5) * r11;
        float r92 = (float) (Math.exp((-(r8 * r8)) / r13) / r3);
        int r10 = r8 + 1;
        if (r10 > r15) goto L11;
        float r52 = (float) (Math.exp((-(r10 * r10)) / r13) / r3);
    L12:
        float r6 = r92 + r52;
        if (r52 != 0.0f) goto L15;
        float r53 = r8;
    L16:
        r22.append("    color += (texture2D(sTexture, vTextureCoord + texOffset * ");
        r22.append(Float.toString(r53));
        r22.append(").rgb + texture2D(sTexture, vTextureCoord - texOffset * ");
        r22.append(Float.toString(r53));
        r22.append(").rgb) * ");
        r22.append(Float.toString(r6));
        r22.append(";\n");
        r8 = r8 + 2;
        r5 = 2.0d;
        goto L7
    L15:
        r53 = ((r8 * r92) + (r10 * r52)) / r6;
        goto L16
    L11:
        r52 = 0.0f;
        goto L12
    L17:
        r22.append("    gl_FragColor = vec4(color, 1.0);\n}\n");
        return r22.toString();
    }

    private void createMaskTexture() {
        configureTexture(2, 9729);
        int r0 = this.outputSize;
        ByteBuffer r9 = ByteBuffer.allocateDirect(r0 * r0);
        float r02 = this.outputSize * 0.5f;
        float r2 = 2.0f + r02;
        int r4 = 0;
    L4:
        if (r4 >= this.outputSize) goto L10;
        float r5 = (r4 + 0.5f) - r02;
        int r6 = 0;
    L7:
        if (r6 >= this.outputSize) goto L9;
        float r7 = (r6 + 0.5f) - r02;
        r9.put((byte) Math.round(Math.max(0.0f, Math.min(1.0f, (r2 + 0.5f) - ((float) Math.sqrt((r7 * r7) + (r5 * r5))))) * 255.0f));
        r6 = r6 + 1;
        goto L7
    L9:
        r4 = r4 + 1;
        goto L4
    L10:
        r9.position(0);
        GLES20.glPixelStorei(3317, 1);
        int r42 = this.outputSize;
        GLES20.glTexImage2D(3553, 0, 6406, r42, r42, 0, 6406, 5121, r9);
        GLES20.glPixelStorei(3317, 4);
    }

    private static int createProgram(int r2, int r3) {
        int r0 = GLES20.glCreateProgram();
        GLES20.glAttachShader(r0, r2);
        GLES20.glAttachShader(r0, r3);
        GLES20.glBindAttribLocation(r0, 0, "aPosition");
        GLES20.glBindAttribLocation(r0, 1, "aTextureCoord");
        GLES20.glLinkProgram(r0);
        int[] r22 = new int[1];
        GLES20.glGetProgramiv(r0, 35714, r22, 0);
        if (r22[0] == 0) goto L5;
        return r0;
    L5:
        String r23 = GLES20.glGetProgramInfoLog(r0);
        GLES20.glDeleteProgram(r0);
        DexMaker$$ExternalSyntheticBUOutline0.m("Unable to link program: ", r23);
        return 0;
    }

    private static int createShader(int r2, String r3) {
        int r22 = GLES20.glCreateShader(r2);
        GLES20.glShaderSource(r22, r3);
        GLES20.glCompileShader(r22);
        int[] r32 = new int[1];
        GLES20.glGetShaderiv(r22, 35713, r32, 0);
        if (r32[0] == 0) goto L5;
        return r22;
    L5:
        String r33 = GLES20.glGetShaderInfoLog(r22);
        GLES20.glDeleteShader(r22);
        DexMaker$$ExternalSyntheticBUOutline0.m("Unable to compile shader: ", r33);
        return 0;
    }

    private static String createTransitionFragmentShader(boolean r2, boolean r3) {
        if (r2 == false) goto L4;
        String r22 = "#ifdef GL_FRAGMENT_PRECISION_HIGH\nprecision highp float;\n#else\nprecision mediump float;\n#endif\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform sampler2D omTexture;\nuniform sampler2D nTexture;\nuniform sampler2D nbTexture;\nuniform sampler2D oldBackground;\nuniform sampler2D newBackground;\nuniform sampler2D maskTexture;\nuniform vec2 oldBlurStep;\nuniform float oldMipBias;\nuniform float oldBlurMix;\nuniform float newBlur;\nuniform float mixValue;\nvec4 sampleOldFrame() {\n    vec2 x = vec2(oldBlurStep.x, 0.0);\n    vec2 diagonalUp = vec2(oldBlurStep.x * 0.5, oldBlurStep.y * 0.8660254);\n    vec2 diagonalDown = vec2(oldBlurStep.x * 0.5, -oldBlurStep.y * 0.8660254);\n    vec4 color = sampleOldMip(omTexture, vTextureCoord, oldMipBias) * 0.25;\n    color += (sampleOldMip(omTexture, vTextureCoord + x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalDown, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalDown, oldMipBias)) * 0.125;\n    return mix(texture2D(sTexture, vTextureCoord), color, oldBlurMix);\n}\nvoid main() {\n    vec4 oldFrame = sampleOldFrame();\n    vec4 newFrame = mix(texture2D(nTexture, vTextureCoord),\n            texture2D(nbTexture, vTextureCoord), newBlur);\n    vec3 camera = mix(oldFrame.rgb, newFrame.rgb, mixValue);\n    vec3 background = mix(texture2D(oldBackground, vTextureCoord).rgb,\n            texture2D(newBackground, vTextureCoord).rgb, mixValue) * 0.25;\n    float mask = texture2D(maskTexture, vTextureCoord).a;\n    gl_FragColor = vec4(mix(background, camera, mask), 1.0);\n}\n";
    L6:
        if (r3 == false) goto L10;
        return "#extension GL_EXT_shader_texture_lod : require\n" + r22.replace("sampleOldMip", "texture2DLodEXT");
    L10:
        return r22.replace("sampleOldMip", "texture2D");
    L4:
        r22 = "#ifdef GL_FRAGMENT_PRECISION_HIGH\nprecision highp float;\n#else\nprecision mediump float;\n#endif\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform sampler2D omTexture;\nuniform sampler2D nTexture;\nuniform sampler2D nbTexture;\nuniform vec2 oldBlurStep;\nuniform float oldMipBias;\nuniform float oldBlurMix;\nuniform float newBlur;\nuniform float mixValue;\nvec4 sampleOldFrame() {\n    vec2 x = vec2(oldBlurStep.x, 0.0);\n    vec2 diagonalUp = vec2(oldBlurStep.x * 0.5, oldBlurStep.y * 0.8660254);\n    vec2 diagonalDown = vec2(oldBlurStep.x * 0.5, -oldBlurStep.y * 0.8660254);\n    vec4 color = sampleOldMip(omTexture, vTextureCoord, oldMipBias) * 0.25;\n    color += (sampleOldMip(omTexture, vTextureCoord + x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalDown, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalDown, oldMipBias)) * 0.125;\n    return mix(texture2D(sTexture, vTextureCoord), color, oldBlurMix);\n}\nvoid main() {\n    vec4 oldFrame = sampleOldFrame();\n    vec4 newFrame = mix(texture2D(nTexture, vTextureCoord),\n            texture2D(nbTexture, vTextureCoord), newBlur);\n    gl_FragColor = mix(oldFrame, newFrame, mixValue);\n}\n";
        goto L6
    }

    private void createWatermarkAtlas(float[] r21, float[] r22) {
        configureTexture(3, 9729);
        WatermarkAtlas r1 = getWatermarkAtlas(this.outputSize);
        int r3 = 0;
    L4:
        if (r3 >= 27) goto L6;
        int r4 = r3 % 8;
        int r6 = r3 / 8;
        int r7 = r3 * 24;
        setTriangleTextureCoordinates(r22, r7 + 8, 0.0f, r1.logoAtlasHeight / r1.height, r1.textSize / r1.width, 1.0f);
        int r15 = r7 + 20;
        int r72 = r1.frameSize;
        int r9 = r1.width;
        int r10 = r1.height;
        setTriangleTextureCoordinates(r22, r15, (r72 * r4) / r9, (r72 * r6) / r10, ((r4 + 1) * r72) / r9, (r72 * (r6 + 1)) / r10);
        r3 = r3 + 1;
        goto L4
    L6:
        float r32 = (r1.textSize / this.outputSize) * 2.0f;
        setTriangleVertexCoordinates(r21, 12, 1.0f - r32, -1.0f, 1.0f, r32 - 1.0f);
        float r17 = ((r1.frameSize / this.outputSize) * 2.0f) - 1.0f;
        setTriangleVertexCoordinates(r21, 30, -1.0f, -1.0f, r17, r17);
        ByteBuffer r14 = r1.pixels.duplicate();
        r14.position(0);
        GLES20.glPixelStorei(3317, 1);
        GLES20.glTexImage2D(3553, 0, 6406, r1.width, r1.height, 0, 6406, 5121, r14);
        GLES20.glPixelStorei(3317, 4);
    }

    private void generateOldSwitchMipmaps() {
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glBindTexture(3553, this.textures[5]);
        GLES20.glGenerateMipmap(3553);
        GLES20.glTexParameteri(3553, 10241, 9987);
    }

    private int getCropSize() {
        return Math.min(this.sourceCropSize, Math.min(this.inputSize.getWidth(), this.inputSize.getHeight()));
    }

    private float getHistoryWeight(long r7) {
        if (this.historyValid == false) goto L14;
        long r2 = this.lastHistoryFrameNs;
        if (r2 == 0) goto L14;
        long r72 = r7 - r2;
        if (r72 <= 0) goto L14;
        if (r72 > 100000000) goto L14;
        return (float) Math.exp((-r72) / 2.8E8d);
    L14:
        return 0.0f;
    }

    private float getSwitchNewMaximumBlurRadiusPx() {
        return 9.0f;
    }

    private float getSwitchOldMipParameter(float r9) {
        float r92 = Math.min(8.0f, (float) (Math.log(((Math.max(1.0f, r9) * 0.625f) * 512.0f) / this.outputSize) / Math.log(2.0d))) + 1.25f;
        if (this.explicitTextureLod == false) goto L7;
        return Math.max(0.0f, r92);
    L7:
        return Math.max(0.0f, r92 - ((float) (Math.log(512.0d / this.outputSize) / Math.log(2.0d))));
    }

    private static synchronized WatermarkAtlas getWatermarkAtlas(int r17) {
        monitor-enter(RoundVideoOverlayRenderer.class);
        if (r17 != 360) goto L9;
        WatermarkAtlas r5 = cachedWatermark360;     // Catch: Throwable -> L7
    L13:
        if (r5 == null) goto L16;
        monitor-exit(RoundVideoOverlayRenderer.class);
        return r5;
    L16:
        float r52 = r17;
        int r11 = Math.round((372.0f * r52) / 1536.0f);     // Catch: Throwable -> L7
        int r6 = Math.round(0.2f * r52);     // Catch: Throwable -> L7
        int r12 = r6 - (Math.round((r52 * 28.0f) / 1536.0f) * 2);     // Catch: Throwable -> L7
        int r13 = r12 * 4;     // Catch: Throwable -> L7
        int r9 = Math.max(r12 * 8, r11);     // Catch: Throwable -> L7
        int r10 = r13 + r11;     // Catch: Throwable -> L7
        RLottieNative r7 = RLottieNative.createFromRawJson(AndroidUtilities.readRes(R.raw.plane_logo_plain));     // Catch: Throwable -> L7
        if (r7 == null) goto L39;
        Bitmap r62 = Bitmap.createBitmap(r6, r6, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L7
        Bitmap r15 = Bitmap.createBitmap(r9, r10, Bitmap.Config.ALPHA_8);     // Catch: Throwable -> L7
        Canvas r8 = new Canvas(r15);     // Catch: Throwable -> L7
        int r2 = 0;
    L21:
        if (r2 >= 27) goto L23;
        r7.getFrame(r2 * 2, r62, true);     // Catch: Throwable -> L7
        r8.drawBitmap(r62, ((r2 % 8) * r12) - r5, ((r2 / 8) * r12) - r5, null);     // Catch: Throwable -> L7
        r2 = r2 + 1;     // Catch: Throwable -> L7
        goto L21
    L23:
        Bitmap r22 = AndroidUtilities.getBitmapFromRaw(R.raw.round_blur_overlay_text);     // Catch: Throwable -> L7
        if (r22 == null) goto L27;
        Bitmap r3 = Bitmap.createScaledBitmap(r22, r11, r11, true);     // Catch: Throwable -> L7
        Bitmap r4 = r3.extractAlpha();     // Catch: Throwable -> L7
        r8.drawBitmap(r4, 0.0f, r13, null);     // Catch: Throwable -> L7
        r4.recycle();     // Catch: Throwable -> L7
        r3.recycle();     // Catch: Throwable -> L7
        r22.recycle();     // Catch: Throwable -> L7
    L27:
        if (r15.getRowBytes() != r9) goto L36;
        ByteBuffer r14 = ByteBuffer.allocateDirect(r9 * r10);     // Catch: Throwable -> L7
        r15.copyPixelsToBuffer(r14);     // Catch: Throwable -> L7
        r14.position(0);     // Catch: Throwable -> L7
        WatermarkAtlas r82 = new WatermarkAtlas(r9, r10, r11, r12, r13, r14);     // Catch: Throwable -> L7
        r15.recycle();     // Catch: Throwable -> L7
        r62.recycle();     // Catch: Throwable -> L7
        r7.recycle();     // Catch: Throwable -> L7
        if (r17 != 360) goto L32;
        cachedWatermark360 = r82;     // Catch: Throwable -> L7
    L34:
        monitor-exit(RoundVideoOverlayRenderer.class);
        return r82;
    L32:
        if (r17 != 480) goto L34;
        cachedWatermark480 = r82;     // Catch: Throwable -> L7
        goto L34
    L36:
        r15.recycle();     // Catch: Throwable -> L7
        r62.recycle();     // Catch: Throwable -> L7
        r7.recycle();     // Catch: Throwable -> L7
        throw new IllegalStateException("Unexpected watermark atlas stride");     // Catch: Throwable -> L7
    L39:
        throw new IllegalStateException("Unable to load watermark animation");     // Catch: Throwable -> L7
    L9:
        if (r17 != 480) goto L12;
        r5 = cachedWatermark480;     // Catch: Throwable -> L7
        goto L13
    L12:
        r5 = null;
    L7:
        th = move-exception;
        throw th;
    }

    private void renderBackgroundBlur(int r15) {
        renderGaussianBlurPass(r15, 6, 48, 48, 0.020833334f, 0.0f);
        renderGaussianBlurPass(8, r15, 48, 48, 0.0f, 0.020833334f);
    }

    private void renderGaussianBlurPass(int r2, int r3, int r4, int r5, float r6, float r7) {
        GLES20.glBindFramebuffer(36160, this.frameBuffers[r3]);
        GLES20.glViewport(0, 0, r4, r5);
        bindProgram(this.gaussianBlurProgram, 0);
        bindTexture(0, r2, this.gaussianBlurProgram.textureHandle);
        GLES20.glUniform2f(this.gaussianBlurProgram.offsetHandle, r6, r7);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.gaussianBlurProgram);
    }

    private void renderMix(int r3, float[] r4, int r5, int r6, int r7) {
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(0, 0, r6, r7);
        bindCameraProgram(this.mixProgram, r4);
        bindExternalTexture(0, r3, this.mixProgram.textureHandle);
        bindTexture(1, r5, this.mixProgram.blurredTextureHandle);
        bindTexture(2, 2, this.mixProgram.maskTextureHandle);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.mixProgram);
    }

    private void renderNewSwitchBlur(float r9) {
        if (Math.abs(this.newSwitchBlurRadius - r9) >= 0.2f) goto L5;
        return;
    L5:
        float r6 = r9 / (this.outputSize * 9);
        int r4 = this.switchNewBlurSize;
        renderSwitchGaussianBlurPass(6, 7, r4, r4, r6, 0.0f);
        int r42 = this.switchNewBlurSize;
        renderSwitchGaussianBlurPass(9, 5, r42, r42, 0.0f, r6);
        this.newSwitchBlurRadius = r9;
    }

    private void renderRawCamera(int r2, float[] r3, int r4, int r5, int r6) {
        GLES20.glBindFramebuffer(36160, this.frameBuffers[r4]);
        GLES20.glViewport(0, 0, r5, r6);
        bindCameraProgram(this.cameraProgram, r3);
        bindExternalTexture(0, r2, this.cameraProgram.textureHandle);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.cameraProgram);
    }

    private void renderRawCameraToOutput(int r3, float[] r4, int r5, int r6) {
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(0, 0, r5, r6);
        bindCameraProgram(this.cameraProgram, r4);
        bindExternalTexture(0, r3, this.cameraProgram.textureHandle);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.cameraProgram);
    }

    private void renderSwitchGaussianBlurPass(int r2, int r3, int r4, int r5, float r6, float r7) {
        GLES20.glBindFramebuffer(36160, this.frameBuffers[r3]);
        GLES20.glViewport(0, 0, r4, r5);
        bindProgram(this.switchGaussianBlurProgram, 0);
        bindTexture(0, r2, this.switchGaussianBlurProgram.textureHandle);
        GLES20.glUniform2f(this.switchGaussianBlurProgram.offsetHandle, r6, r7);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.switchGaussianBlurProgram);
    }

    private void renderTemporalBackground(int r6, float[] r7, int r8, float r9) {
        GLES20.glBindFramebuffer(36160, this.frameBuffers[r8]);
        GLES20.glViewport(0, 0, 48, 48);
        bindCameraProgram(this.temporalProgram, r7);
        bindExternalTexture(0, r6, this.temporalProgram.textureHandle);
        bindTexture(1, this.historyReadTexture, this.temporalProgram.historyTextureHandle);
        boolean r62 = swapsTextureAxes(r7);
        int r82 = getCropSize();
        Size r2 = this.inputSize;
        if (r62 == false) goto L6;
        int r22 = r2.getHeight();
    L5:
        float r23 = r22;
        Size r3 = this.inputSize;
        if (r62 == false) goto L11;
        int r63 = r3.getWidth();
    L12:
        float r83 = r82;
        float r24 = ((r83 / r23) / 48.0f) * 0.45f;
        float r84 = ((r83 / r63) / 48.0f) * 0.45f;
        GLES20.glUniform2f(this.temporalProgram.sampleStepXHandle, r7[0] * r24, r7[1] * r24);
        GLES20.glUniform2f(this.temporalProgram.sampleStepYHandle, r7[4] * r84, r7[5] * r84);
        GLES20.glUniform1f(this.temporalProgram.historyWeightHandle, r9);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.temporalProgram);
        return;
    L11:
        r63 = r3.getHeight();
        goto L12
    L6:
        r22 = r2.getWidth();
        goto L5
    }

    private void renderTextureCopy(int r2, int r3, int r4, int r5) {
        GLES20.glBindFramebuffer(36160, this.frameBuffers[r3]);
        GLES20.glViewport(0, 0, r4, r5);
        bindProgram(this.copyProgram, 0);
        bindTexture(0, r2, this.copyProgram.textureHandle);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.copyProgram);
    }

    private void renderWatermarks() {
        if (this.compositionEnabled == true) goto L5;
        return;
    L5:
        if (this.watermarkProgram == null) goto L10;
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        int r1 = this.logoFrame;
        this.logoFrame = r1 + 1;
        bindProgram(this.watermarkProgram, ((r1 % 27) * 24) + 8, 12);
        bindTexture(0, 3, this.watermarkProgram.textureHandle);
        GLES20.glDrawArrays(4, 0, 12);
        unbindProgram(this.watermarkProgram);
        GLES20.glDisable(3042);
        return;
    }

    private static void setTextureCoordinate(float[] r0, int r1, float r2, float r3) {
        r0[r1] = r2;
        r0[r1 + 1] = r3;
    }

    private static void setTextureCoordinates(float[] r1, int r2, float r3, float r4, float r5, float r6) {
        r1[r2] = r3;
        r1[r2 + 1] = r6;
        r1[r2 + 2] = r5;
        r1[r2 + 3] = r6;
        r1[r2 + 4] = r3;
        r1[r2 + 5] = r4;
        r1[r2 + 6] = r5;
        r1[r2 + 7] = r4;
    }

    private void setTextureMinFilter(int r1, int r2) {
        GLES20.glBindTexture(3553, this.textures[r1]);
        GLES20.glTexParameteri(3553, 10241, r2);
    }

    private static void setTriangleTextureCoordinates(float[] r1, int r2, float r3, float r4, float r5, float r6) {
        setTextureCoordinate(r1, r2, r3, r6);
        setTextureCoordinate(r1, r2 + 2, r5, r6);
        setTextureCoordinate(r1, r2 + 4, r3, r4);
        setTextureCoordinate(r1, r2 + 6, r3, r4);
        setTextureCoordinate(r1, r2 + 8, r5, r6);
        setTextureCoordinate(r1, r2 + 10, r5, r4);
    }

    private static void setTriangleVertexCoordinates(float[] r1, int r2, float r3, float r4, float r5, float r6) {
        setVertex(r1, r2, r3, r4);
        setVertex(r1, r2 + 3, r5, r4);
        setVertex(r1, r2 + 6, r3, r6);
        setVertex(r1, r2 + 9, r3, r6);
        setVertex(r1, r2 + 12, r5, r4);
        setVertex(r1, r2 + 15, r5, r6);
    }

    private static void setVertex(float[] r0, int r1, float r2, float r3) {
        r0[r1] = r2;
        r0[r1 + 1] = r3;
        r0[r1 + 2] = 0.0f;
    }

    private static void setVertexCoordinates(float[] r2, int r3, float r4, float r5, float r6, float r7) {
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

    private static boolean supportsExplicitTextureLod() {
        String r0 = GLES20.glGetString(7939);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.contains("GL_EXT_shader_texture_lod") == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean swapsTextureAxes(float[] r3) {
        if (Math.abs(r3[1]) <= Math.abs(r3[0])) goto L5;
        return true;
    L5:
        return false;
    }

    private static void unbindProgram(Program r0) {
    }

    public void captureSwitchFrame(int r9, float[] r10, boolean r11) {
        GLES20.glDisable(3042);
        if (r11 == true) goto L6;
        setTextureMinFilter(5, 9729);
    L6:
        if (r11 == false) goto L8;
        int r5 = 4;
    L9:
        int r6 = this.outputSize;
        renderRawCamera(r9, r10, r5, r6, r6);
        if (r11 == false) goto L16;
        int r92 = this.historyWriteTexture;
        this.switchNewBackgroundTexture = r92;
        if (this.compositionEnabled == false) goto L14;
        renderTemporalBackground(r9, r10, r92, 0.0f);
        renderBackgroundBlur(this.switchNewBackgroundTexture);
    L14:
        this.newSwitchBlurRadius = -1.0f;
        this.switchNewFrameCaptured = true;
        return;
    L16:
        renderTextureCopy(4, 3, 512, 512);
        generateOldSwitchMipmaps();
        this.switchOldBackgroundTexture = this.historyReadTexture;
        this.switchNewBackgroundTexture = this.historyWriteTexture;
        this.switchNewFrameCaptured = false;
        this.newSwitchBlurRadius = -1.0f;
        return;
    L8:
        r5 = 2;
        goto L9
    }

    public void finishCameraSwitch(long r2) {
        if (this.switchNewFrameCaptured == true) goto L6;
        return;
    L6:
        if (this.compositionEnabled == false) goto L8;
        this.historyReadTexture = this.switchNewBackgroundTexture;
        this.historyWriteTexture = this.switchOldBackgroundTexture;
        this.historyValid = true;
        this.lastHistoryFrameNs = r2;
    L8:
        this.switchNewFrameCaptured = false;
    }

    public float getSwitchTargetBlurRadiusPx() {
        return ((this.outputSize * 4.0f) / 48.0f) * 1.15f;
    }

    public void release() {
        this.cameraProgram.release();
        this.copyProgram.release();
        TemporalProgram r0 = this.temporalProgram;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        MixProgram r02 = this.mixProgram;
        if (r02 == null) goto L8;
        r02.release();
    L8:
        this.transitionProgram.release();
        this.gaussianBlurProgram.release();
        this.switchGaussianBlurProgram.release();
        Program r03 = this.watermarkProgram;
        if (r03 == null) goto L11;
        r03.release();
    L11:
        int[] r04 = this.textures;
        GLES20.glDeleteTextures(r04.length, r04, 0);
        int[] r3 = this.frameBuffers;
        GLES20.glDeleteFramebuffers(r3.length, r3, 0);
    }

    public void render(int r4, float[] r5, long r6, int r8, int r9) {
        GLES20.glDisable(3042);
        if (this.compositionEnabled == false) goto L5;
        renderTemporalBackground(r4, r5, this.historyWriteTexture, getHistoryWeight(r6));
        renderBackgroundBlur(this.historyWriteTexture);
        int r0 = this.historyReadTexture;
        int r62 = this.historyWriteTexture;
        this.historyReadTexture = r62;
        this.historyWriteTexture = r0;
        this.historyValid = true;
        this.lastHistoryFrameNs = r6;
        renderMix(r4, r5, r62, r8, r9);
    L6:
        renderWatermarks();
        return;
    L5:
        renderRawCameraToOutput(r4, r5, r8, r9);
        goto L6
    }

    public void renderSwitch(float r7, float r8, float r9, int r10, int r11) {
        if (this.switchNewFrameCaptured == false) goto L5;
        renderNewSwitchBlur(Math.min(r7, getSwitchNewMaximumBlurRadiusPx()));
    L5:
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(0, 0, r10, r11);
        GLES20.glDisable(3042);
        bindProgram(this.transitionProgram, 0);
        bindTexture(0, 4, this.transitionProgram.textureHandle);
        bindTexture(1, 5, this.transitionProgram.oldMipTextureHandle);
        bindTexture(2, 6, this.transitionProgram.newTextureHandle);
        bindTexture(3, 7, this.transitionProgram.newBlurTextureHandle);
        if (this.compositionEnabled == false) goto L8;
        bindTexture(4, this.switchOldBackgroundTexture, this.transitionProgram.oldBackgroundHandle);
        bindTexture(5, this.switchNewBackgroundTexture, this.transitionProgram.newBackgroundHandle);
        bindTexture(6, 2, this.transitionProgram.maskTextureHandle);
    L8:
        float r102 = (Math.max(0.0f, r7) * 0.625f) / this.outputSize;
        GLES20.glUniform2f(this.transitionProgram.oldBlurStepHandle, r102, r102);
        GLES20.glUniform1f(this.transitionProgram.oldMipBiasHandle, getSwitchOldMipParameter(r7));
        GLES20.glUniform1f(this.transitionProgram.oldBlurMixHandle, clamp01(r7 / 2.0f));
        GLES20.glUniform1f(this.transitionProgram.newBlurHandle, r8);
        GLES20.glUniform1f(this.transitionProgram.mixHandle, r9);
        GLES20.glDrawArrays(5, 0, 4);
        unbindProgram(this.transitionProgram);
        renderWatermarks();
    }

    public void updateInputConfiguration(Size r3, int r4) {
        this.inputSize = r3;
        this.sourceCropSize = r4;
        this.cameraTextureBuffer = createCameraTextureBuffer(r3, r4, false);
        this.rotatedCameraTextureBuffer = createCameraTextureBuffer(r3, r4, true);
        this.historyValid = false;
        this.lastHistoryFrameNs = 0;
    }
}
