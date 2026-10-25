package androidx.camera.core.processing.util;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.processing.ShaderProvider;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class GLUtils {
    public static final String DEFAULT_VERTEX_SHADER = null;
    public static final int[] EMPTY_ATTRIBS = null;
    public static final String HDR_VERTEX_SHADER = null;
    public static final int[] HLG_SURFACE_ATTRIBS = null;
    public static final OutputSurface NO_OUTPUT_SURFACE = null;
    private static final ShaderProvider SHADER_PROVIDER_DEFAULT = null;
    private static final ShaderProvider SHADER_PROVIDER_HDR_DEFAULT = null;
    private static final ShaderProvider SHADER_PROVIDER_HDR_YUV = null;
    public static final FloatBuffer TEX_BUF = null;
    public static final float[] TEX_COORDS = null;
    public static final FloatBuffer VERTEX_BUF = null;
    public static final float[] VERTEX_COORDS = null;

    /* renamed from: androidx.camera.core.processing.util.GLUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements ShaderProvider {
        public AnonymousClass1() {
        }

        @Override // androidx.camera.core.processing.ShaderProvider
        public String createFragmentShader(String r2, String r3) {
            return String.format(Locale.US, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 %s;\nuniform samplerExternalOES %s;\nuniform float uAlphaScale;\nvoid main() {\n    vec4 src = texture2D(%s, %s);\n    gl_FragColor = vec4(src.rgb, src.a * uAlphaScale);\n}\n", new Object[]{r3, r2, r2, r3});
        }
    }

    /* renamed from: androidx.camera.core.processing.util.GLUtils$2, reason: invalid class name */
    public class AnonymousClass2 implements ShaderProvider {
        public AnonymousClass2() {
        }

        @Override // androidx.camera.core.processing.ShaderProvider
        public String createFragmentShader(String r2, String r3) {
            return String.format(Locale.US, "#version 300 es\n#extension GL_OES_EGL_image_external_essl3 : require\nprecision mediump float;\nuniform samplerExternalOES %s;\nuniform float uAlphaScale;\nin vec2 %s;\nout vec4 outColor;\n\nvoid main() {\n  vec4 src = texture(%s, %s);\n  outColor = vec4(src.rgb, src.a * uAlphaScale);\n}", new Object[]{r2, r3, r2, r3});
        }
    }

    /* renamed from: androidx.camera.core.processing.util.GLUtils$3, reason: invalid class name */
    public class AnonymousClass3 implements ShaderProvider {
        public AnonymousClass3() {
        }

        @Override // androidx.camera.core.processing.ShaderProvider
        public String createFragmentShader(String r2, String r3) {
            return String.format(Locale.US, "#version 300 es\n#extension GL_EXT_YUV_target : require\nprecision mediump float;\nuniform __samplerExternal2DY2YEXT %s;\nuniform float uAlphaScale;\nin vec2 %s;\nout vec4 outColor;\n\nvec3 yuvToRgb(vec3 yuv) {\n  const vec3 yuvOffset = vec3(0.0625, 0.5, 0.5);\n  const mat3 yuvToRgbColorMat = mat3(\n    1.1689f, 1.1689f, 1.1689f,\n    0.0000f, -0.1881f, 2.1502f,\n    1.6853f, -0.6530f, 0.0000f\n  );\n  return clamp(yuvToRgbColorMat * (yuv - yuvOffset), 0.0, 1.0);\n}\n\nvoid main() {\n  vec3 srcYuv = texture(%s, %s).xyz;\n  vec3 srcRgb = yuvToRgb(srcYuv);\n  outColor = vec4(srcRgb, uAlphaScale);\n}", new Object[]{r2, r3, r2, r3});
        }
    }

    public static class BlankShaderProgram extends Program2D {
        public BlankShaderProgram() {
            super("uniform mat4 uTransMatrix;\nattribute vec4 aPosition;\nvoid main() {\n    gl_Position = uTransMatrix * aPosition;\n}\n", "precision mediump float;\nuniform float uAlphaScale;\nvoid main() {\n    gl_FragColor = vec4(0.0, 0.0, 0.0, uAlphaScale);\n}\n");
        }
    }

    public enum InputFormat extends Enum<InputFormat> {
        private static final /* synthetic */ InputFormat[] $VALUES = null;
        public static final InputFormat DEFAULT = null;
        public static final InputFormat UNKNOWN = null;
        public static final InputFormat YUV = null;

        private static /* synthetic */ InputFormat[] $values() {
            return new InputFormat[]{UNKNOWN, DEFAULT, YUV};
        }

        static {
            UNKNOWN = new InputFormat("UNKNOWN", 0);
            DEFAULT = new InputFormat(PluginsConstants.Strategy.DEFAULT, 1);
            YUV = new InputFormat("YUV", 2);
            $VALUES = $values();
        }

        InputFormat(String r1, int r2) {
        }

        public static InputFormat valueOf(String r1) {
            return (InputFormat) Enum.valueOf(InputFormat.class, r1);
        }

        public static InputFormat[] values() {
            return (InputFormat[]) $VALUES.clone();
        }
    }

    public static abstract class Program2D {
        protected int mAlphaScaleLoc;
        protected int mPositionLoc;
        protected int mProgramHandle;
        protected int mTransMatrixLoc;

        public Program2D(String r8, String r9) {
            this.mTransMatrixLoc = -1;
            this.mAlphaScaleLoc = -1;
            this.mPositionLoc = -1;
            int r82 = GLUtils.loadShader(35633, r8);     // Catch: IllegalArgumentException -> L27 Throwable -> L29
            int r92 = GLUtils.loadShader(35632, r9);     // Catch: IllegalArgumentException -> L22 Throwable -> L25
            int r3 = GLES20.glCreateProgram();     // Catch: Throwable -> L18 IllegalStateException -> L20
            GLUtils.checkGlErrorOrThrow("glCreateProgram");     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            GLES20.glAttachShader(r3, r82);     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            GLUtils.checkGlErrorOrThrow("glAttachShader");     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            GLES20.glAttachShader(r3, r92);     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            GLUtils.checkGlErrorOrThrow("glAttachShader");     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            GLES20.glLinkProgram(r3);     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            int[] r4 = new int[1];     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            GLES20.glGetProgramiv(r3, 35714, r4, 0);     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            if (r4[0] != 1) goto L17;
            this.mProgramHandle = r3;     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
            loadLocations();
            return;
        L17:
            throw new IllegalStateException("Could not link program: " + GLES20.glGetProgramInfoLog(r3));     // Catch: IllegalArgumentException -> L12 IllegalStateException -> L14
        L12:
            e = e;
        L31:
            if (r82 == (-1)) goto L33;
            GLES20.glDeleteShader(r82);
        L33:
            if (r92 == (-1)) goto L35;
            GLES20.glDeleteShader(r92);
        L35:
            if (r3 == (-1)) goto L37;
            GLES20.glDeleteProgram(r3);
        L37:
            throw e;
        L14:
            e = e;
        L18:
            e = e;
            r3 = -1;
        L25:
            e = e;
            r92 = -1;
        L24:
            r3 = r92;
        L29:
            e = e;
            r82 = -1;
            r92 = -1;
            goto L24
        }

        public static /* synthetic */ void access$100(Program2D r0) {
            r0.loadLocations();
        }

        private void loadLocations() {
            int r0 = GLES20.glGetAttribLocation(this.mProgramHandle, "aPosition");
            this.mPositionLoc = r0;
            GLUtils.checkLocationOrThrow(r0, "aPosition");
            int r02 = GLES20.glGetUniformLocation(this.mProgramHandle, "uTransMatrix");
            this.mTransMatrixLoc = r02;
            GLUtils.checkLocationOrThrow(r02, "uTransMatrix");
            int r03 = GLES20.glGetUniformLocation(this.mProgramHandle, "uAlphaScale");
            this.mAlphaScaleLoc = r03;
            GLUtils.checkLocationOrThrow(r03, "uAlphaScale");
        }

        public void delete() {
            GLES20.glDeleteProgram(this.mProgramHandle);
        }

        public void updateAlpha(float r1) {
            GLES20.glUniform1f(this.mAlphaScaleLoc, r1);
            GLUtils.checkGlErrorOrThrow("glUniform1f");
        }

        public void updateTransformMatrix(float[] r3) {
            GLES20.glUniformMatrix4fv(this.mTransMatrixLoc, 1, false, r3, 0);
            GLUtils.checkGlErrorOrThrow("glUniformMatrix4fv");
        }

        public void use() {
            GLES20.glUseProgram(this.mProgramHandle);
            GLUtils.checkGlErrorOrThrow("glUseProgram");
            GLES20.glEnableVertexAttribArray(this.mPositionLoc);
            GLUtils.checkGlErrorOrThrow("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.mPositionLoc, 2, 5126, false, 0, GLUtils.VERTEX_BUF);
            GLUtils.checkGlErrorOrThrow("glVertexAttribPointer");
            updateTransformMatrix(GLUtils.create4x4IdentityMatrix());
            updateAlpha(1.0f);
        }
    }

    public static class SamplerShaderProgram extends Program2D {
        private int mSamplerLoc;
        private int mTexCoordLoc;
        private int mTexMatrixLoc;

        public SamplerShaderProgram(DynamicRange r1, ShaderProvider r2) {
            if (r1.is10BitHdr() == false) goto L5;
            String r12 = GLUtils.HDR_VERTEX_SHADER;
        L6:
            super(r12, GLUtils.access$000(r2));
            this.mSamplerLoc = -1;
            this.mTexMatrixLoc = -1;
            this.mTexCoordLoc = -1;
            loadLocations();
            return;
        L5:
            r12 = GLUtils.DEFAULT_VERTEX_SHADER;
            goto L6
        }

        public SamplerShaderProgram(DynamicRange r1, InputFormat r2) {
            this(r1, resolveDefaultShaderProvider(r1, r2));
        }

        private void loadLocations() {
            Program2D.access$100(this);
            int r0 = GLES20.glGetUniformLocation(this.mProgramHandle, "sTexture");
            this.mSamplerLoc = r0;
            GLUtils.checkLocationOrThrow(r0, "sTexture");
            int r02 = GLES20.glGetAttribLocation(this.mProgramHandle, "aTextureCoord");
            this.mTexCoordLoc = r02;
            GLUtils.checkLocationOrThrow(r02, "aTextureCoord");
            int r03 = GLES20.glGetUniformLocation(this.mProgramHandle, "uTexMatrix");
            this.mTexMatrixLoc = r03;
            GLUtils.checkLocationOrThrow(r03, "uTexMatrix");
        }

        private static ShaderProvider resolveDefaultShaderProvider(DynamicRange r2, InputFormat r3) {
            if (r2.is10BitHdr() == false) goto L15;
            if (r3 == InputFormat.UNKNOWN) goto L7;
            boolean r22 = true;
        L8:
            Preconditions.checkArgument(r22, "No default sampler shader available for" + r3);
            if (r3 != InputFormat.YUV) goto L13;
            return GLUtils.access$200();
        L13:
            return GLUtils.access$300();
        L7:
            r22 = false;
            goto L8
        L15:
            return GLUtils.access$400();
        }

        public void updateTextureMatrix(float[] r3) {
            GLES20.glUniformMatrix4fv(this.mTexMatrixLoc, 1, false, r3, 0);
            GLUtils.checkGlErrorOrThrow("glUniformMatrix4fv");
        }

        @Override // androidx.camera.core.processing.util.GLUtils.Program2D
        public void use() {
            super.use();
            GLES20.glUniform1i(this.mSamplerLoc, 0);
            GLES20.glEnableVertexAttribArray(this.mTexCoordLoc);
            GLUtils.checkGlErrorOrThrow("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.mTexCoordLoc, 2, 5126, false, 0, GLUtils.TEX_BUF);
            GLUtils.checkGlErrorOrThrow("glVertexAttribPointer");
        }
    }

    static {
        EMPTY_ATTRIBS = new int[]{12344};
        HLG_SURFACE_ATTRIBS = new int[]{12445, 13632, 12344};
        Locale r0 = Locale.US;
        DEFAULT_VERTEX_SHADER = String.format(r0, "uniform mat4 uTexMatrix;\nuniform mat4 uTransMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 %s;\nvoid main() {\n    gl_Position = uTransMatrix * aPosition;\n    %s = (uTexMatrix * aTextureCoord).xy;\n}\n", new Object[]{"vTextureCoord", "vTextureCoord"});
        HDR_VERTEX_SHADER = String.format(r0, "#version 300 es\nin vec4 aPosition;\nin vec4 aTextureCoord;\nuniform mat4 uTexMatrix;\nuniform mat4 uTransMatrix;\nout vec2 %s;\nvoid main() {\n  gl_Position = uTransMatrix * aPosition;\n  %s = (uTexMatrix * aTextureCoord).xy;\n}\n", new Object[]{"vTextureCoord", "vTextureCoord"});
        SHADER_PROVIDER_DEFAULT = new AnonymousClass1();
        SHADER_PROVIDER_HDR_DEFAULT = new AnonymousClass2();
        SHADER_PROVIDER_HDR_YUV = new AnonymousClass3();
        float[] r1 = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
        VERTEX_COORDS = r1;
        VERTEX_BUF = createFloatBuffer(r1);
        float[] r02 = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        TEX_COORDS = r02;
        TEX_BUF = createFloatBuffer(r02);
        NO_OUTPUT_SURFACE = OutputSurface.of(EGL14.EGL_NO_SURFACE, 0, 0);
    }

    public static /* synthetic */ String access$000(ShaderProvider r0) {
        return getFragmentShaderSource(r0);
    }

    public static /* synthetic */ ShaderProvider access$200() {
        return SHADER_PROVIDER_HDR_YUV;
    }

    public static /* synthetic */ ShaderProvider access$300() {
        return SHADER_PROVIDER_HDR_DEFAULT;
    }

    public static /* synthetic */ ShaderProvider access$400() {
        return SHADER_PROVIDER_DEFAULT;
    }

    public static void checkEglErrorOrLog(String r2) {
        checkEglErrorOrThrow(r2);     // Catch: IllegalStateException -> L4
        return;
    L4:
        e = move-exception;
        Logger.e("GLUtils", e.toString(), e);
    }

    public static void checkEglErrorOrThrow(String r2) {
        int r0 = EGL14.eglGetError();
        if (r0 != 12288) goto L5;
        return;
    L5:
        GLUtils$$ExternalSyntheticBUOutline0.m(r2, ": EGL error: 0x", Integer.toHexString(r0));
    }

    public static void checkGlErrorOrThrow(String r2) {
        int r0 = GLES20.glGetError();
        if (r0 != 0) goto L5;
        return;
    L5:
        GLUtils$$ExternalSyntheticBUOutline0.m(r2, ": GL error 0x", Integer.toHexString(r0));
    }

    public static void checkGlThreadOrThrow(Thread r1) {
        if (r1 != Thread.currentThread()) goto L5;
        boolean r12 = true;
    L6:
        Preconditions.checkState(r12, "Method call must be called on the GL thread.");
        return;
    L5:
        r12 = false;
        goto L6
    }

    public static void checkInitializedOrThrow(AtomicBoolean r0, boolean r1) {
        if (r1 != r0.get()) goto L5;
        boolean r02 = true;
    L6:
        if (r1 == false) goto L8;
        String r12 = "OpenGlRenderer is not initialized";
    L9:
        Preconditions.checkState(r02, r12);
        return;
    L8:
        r12 = "OpenGlRenderer is already initialized";
        goto L9
    L5:
        r02 = false;
        goto L6
    }

    public static void checkLocationOrThrow(int r1, String r2) {
        if (r1 < 0) goto L4;
        return;
    L4:
        LiveData$$ExternalSyntheticBUOutline0.m("Unable to locate '", r2, "' in program");
    }

    public static int[] chooseSurfaceAttrib(String r2, DynamicRange r3) {
        int[] r0 = EMPTY_ATTRIBS;
        if (r3.getEncoding() == 3) goto L5;
    L9:
        return r0;
    L5:
        if (r2.contains("EGL_EXT_gl_colorspace_bt2020_hlg") == true) goto L7;
        Logger.w("GLUtils", "Dynamic range uses HLG encoding, but device does not support EGL_EXT_gl_colorspace_bt2020_hlg.Fallback to default colorspace.");
        goto L9
    L7:
        return HLG_SURFACE_ATTRIBS;
    }

    public static float[] create4x4IdentityMatrix() {
        float[] r0 = new float[16];
        Matrix.setIdentityM(r0, 0);
        return r0;
    }

    public static FloatBuffer createFloatBuffer(float[] r2) {
        ByteBuffer r0 = ByteBuffer.allocateDirect(r2.length * 4);
        r0.order(ByteOrder.nativeOrder());
        FloatBuffer r02 = r0.asFloatBuffer();
        r02.put(r2);
        r02.position(0);
        return r02;
    }

    public static EGLSurface createPBufferSurface(EGLDisplay r3, EGLConfig r4, int r5, int r6) {
        EGLSurface r32 = EGL14.eglCreatePbufferSurface(r3, r4, new int[]{12375, r5, 12374, r6, 12344}, 0);
        checkEglErrorOrThrow("eglCreatePbufferSurface");
        if (r32 == null) goto L5;
        return r32;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("surface was null");
        return null;
    }

    public static Map<InputFormat, Program2D> createPrograms(DynamicRange r10, Map<InputFormat, ShaderProvider> r11) {
        HashMap r0 = new HashMap();
        InputFormat[] r1 = InputFormat.values();
        int r2 = r1.length;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L25;
        InputFormat r5 = r1[r4];
        ShaderProvider r6 = r11.get(r5);
        if (r6 == null) goto L8;
        Object r7 = new SamplerShaderProgram(r10, r6);
    L24:
        Log.d("GLUtils", "Shader program for input format " + r5 + " created: " + r7);
        r0.put(r5, r7);
        r4 = r4 + 1;
        goto L3
    L8:
        if (r5 == InputFormat.YUV) goto L23;
        InputFormat r62 = InputFormat.DEFAULT;
        if (r5 == r62) goto L23;
        if (r5 != InputFormat.UNKNOWN) goto L15;
        boolean r72 = true;
    L16:
        Preconditions.checkState(r72, "Unhandled input format: " + r5);
        if (r10.is10BitHdr() == false) goto L19;
        r7 = new BlankShaderProgram();
        goto L24
    L19:
        ShaderProvider r73 = r11.get(r62);
        if (r73 == null) goto L22;
        r7 = new SamplerShaderProgram(r10, r73);
        goto L24
    L22:
        r7 = new SamplerShaderProgram(r10, r62);
        goto L24
    L15:
        r72 = false;
    L23:
        r7 = new SamplerShaderProgram(r10, r5);
        goto L24
    L25:
        return r0;
    }

    public static int createTexture() {
        int[] r1 = new int[1];
        GLES20.glGenTextures(1, r1, 0);
        checkGlErrorOrThrow("glGenTextures");
        int r0 = r1[0];
        GLES20.glBindTexture(36197, r0);
        checkGlErrorOrThrow("glBindTexture " + r0);
        GLES20.glTexParameteri(36197, 10241, 9729);
        GLES20.glTexParameteri(36197, 10240, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        checkGlErrorOrThrow("glTexParameter");
        return r0;
    }

    public static EGLSurface createWindowSurface(EGLDisplay r1, EGLConfig r2, Surface r3, int[] r4) {
        EGLSurface r12 = EGL14.eglCreateWindowSurface(r1, r2, r3, r4, 0);
        checkEglErrorOrThrow("eglCreateWindowSurface");
        if (r12 == null) goto L5;
        return r12;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("surface was null");
        return null;
    }

    public static void deleteFbo(int r2) {
        GLES20.glDeleteFramebuffers(1, new int[]{r2}, 0);
        checkGlErrorOrThrow("glDeleteFramebuffers");
    }

    public static void deleteTexture(int r2) {
        GLES20.glDeleteTextures(1, new int[]{r2}, 0);
        checkGlErrorOrThrow("glDeleteTextures");
    }

    public static int generateFbo() {
        int[] r1 = new int[1];
        GLES20.glGenFramebuffers(1, r1, 0);
        checkGlErrorOrThrow("glGenFramebuffers");
        return r1[0];
    }

    public static int generateTexture() {
        int[] r1 = new int[1];
        GLES20.glGenTextures(1, r1, 0);
        checkGlErrorOrThrow("glGenTextures");
        return r1[0];
    }

    private static String getFragmentShaderSource(ShaderProvider r2) {
        String r22 = r2.createFragmentShader("sTexture", "vTextureCoord");     // Catch: Throwable -> L12
        if (r22 == null) goto L11;
        if (r22.contains("vTextureCoord") == false) goto L11;
        if (r22.contains("sTexture") == false) goto L11;
        return r22;
    L11:
        throw new IllegalArgumentException("Invalid fragment shader");     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        if ((th instanceof IllegalArgumentException) == false) goto L17;
        throw th;
    L17:
        throw new IllegalArgumentException("Unable retrieve fragment shader source", th);
    }

    public static String getGlVersionNumber() {
        Matcher r0 = Pattern.compile("OpenGL ES ([0-9]+)\\.([0-9]+).*").matcher(GLES20.glGetString(7938));
        if (r0.find() == true) goto L5;
        return "0.0";
    L5:
        return ((String) Preconditions.checkNotNull(r0.group(1))) + "." + ((String) Preconditions.checkNotNull(r0.group(2)));
    }

    public static Size getSurfaceSize(EGLDisplay r2, EGLSurface r3) {
        return new Size(querySurface(r2, r3, 12375), querySurface(r2, r3, 12374));
    }

    public static int loadShader(int r4, String r5) {
        int r0 = GLES20.glCreateShader(r4);
        checkGlErrorOrThrow("glCreateShader type=" + r4);
        GLES20.glShaderSource(r0, r5);
        GLES20.glCompileShader(r0);
        int[] r1 = new int[1];
        GLES20.glGetShaderiv(r0, 35713, r1, 0);
        if (r1[0] == 0) goto L5;
        return r0;
    L5:
        Logger.w("GLUtils", "Could not compile shader: " + r5);
        String r52 = GLES20.glGetShaderInfoLog(r0);
        GLES20.glDeleteShader(r0);
        throw new IllegalStateException("Could not compile shader type " + r4 + ":" + r52);
    }

    public static int querySurface(EGLDisplay r2, EGLSurface r3, int r4) {
        int[] r0 = new int[1];
        EGL14.eglQuerySurface(r2, r3, r4, r0, 0);
        return r0[0];
    }
}
