package org.telegram.ui.Components.Premium.GLIcon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class Icon3D {
    private static final String[] coinModel = null;
    private static final String[] dealModel = null;
    private static final String[] diamondModel = null;
    private static final String[] starModel = null;
    public final int N;
    private int alphaHandle;
    Bitmap backgroundBitmap;
    int behindHandle;
    private int[] buffers;
    public float diffuse;
    int diffuseHandle;
    float enterAlpha;
    private int goldenHandle;
    public int gradientColor1;
    int gradientColor1Handle;
    public int gradientColor2;
    int gradientColor2Handle;
    int gradientPositionHandle;
    private int mBackgroundTextureHandle;
    private int mBackgroundTextureUniformHandle;
    private int mMVPMatrixHandle;
    private int mNormalCoordinateHandle;
    private int mNormalMapUniformHandle;
    private FloatBuffer[] mNormals;
    private int mProgramObject;
    private int mTextureCoordinateHandle;
    private int mTextureDataHandle;
    private int mTextureUniformHandle;
    private FloatBuffer[] mTextures;
    private FloatBuffer[] mVertices;
    private int mVerticesHandle;
    private int mWorldMatrixHandle;
    int modelIndex2Handle;
    int modelIndexHandle;
    public boolean night;
    int nightHandle;
    public float normalSpec;
    public int normalSpecColor;
    int normalSpecColorHandle;
    int normalSpecHandle;
    int resolutionHandle;
    public float spec1;
    public float spec2;
    public int specColor;
    int specColorHandle;
    int specHandleBottom;
    int specHandleTop;
    Bitmap texture;
    private float time;
    int timeHandle;
    int[] trianglesCount;
    public final int type;
    int typeHandle;
    private int whiteHandle;
    float xOffset;
    private int xOffsetHandle;

    static {
        starModel = new String[]{"models/star.binobj"};
        diamondModel = new String[]{"models/diamond_outer_2.binobj", "models/diamond_outer.binobj", "models/diamond.binobj"};
        coinModel = new String[]{"models/coin_outer.binobj", "models/coin_inner.binobj", "models/coin_logo.binobj", "models/coin_stars.binobj"};
        dealModel = new String[]{"models/coin_outer.binobj", "models/coin_inner.binobj", "models/deal_logo.binobj", "models/coin_stars.binobj"};
    }

    public Icon3D(Context r12, int r13) {
        this.enterAlpha = 0.0f;
        this.spec1 = 2.0f;
        this.spec2 = 0.13f;
        float r1 = 1.0f;
        this.diffuse = 1.0f;
        this.normalSpec = 0.2f;
        this.normalSpecColor = -1;
        this.specColor = -1;
        this.time = 0.0f;
        this.type = r13;
        if (r13 != 1) goto L6;
        String[] r5 = coinModel;
    L15:
        int r6 = r5.length;
        this.N = r6;
        this.mVertices = new FloatBuffer[r6];
        this.mTextures = new FloatBuffer[r6];
        this.mNormals = new FloatBuffer[r6];
        this.trianglesCount = new int[r6];
        int r62 = 0;
    L17:
        if (r62 >= this.N) goto L19;
        ObjLoader r7 = new ObjLoader(r12, r5[r62], r1);
        this.mVertices[r62] = ByteBuffer.allocateDirect(r7.positions.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mVertices[r62].put(r7.positions).position(0);
        this.mTextures[r62] = ByteBuffer.allocateDirect(r7.textureCoordinates.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mTextures[r62].put(r7.textureCoordinates).position(0);
        this.mNormals[r62] = ByteBuffer.allocateDirect(r7.normals.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mNormals[r62].put(r7.normals).position(0);
        this.trianglesCount[r62] = r7.positions.length;
        r62 = r62 + 1;
        goto L17
    L19:
        generateTexture();
        int[] r14 = new int[1];
        if (r13 == 0) goto L26;
        if (r13 == 2) goto L26;
        if (r13 != 4) goto L25;
        String r132 = "shaders/fragment5.glsl";
    L27:
        int r0 = GLIconRenderer.loadShader(35633, preprocessShader(loadFromAsset(r12, "shaders/vertex2.glsl")));
        int r133 = GLIconRenderer.loadShader(35632, preprocessShader(loadFromAsset(r12, r132)));
        int r2 = GLES20.glCreateProgram();
        GLES20.glAttachShader(r2, r0);
        GLES20.glAttachShader(r2, r133);
        GLES20.glLinkProgram(r2);
        GLES20.glGetProgramiv(r2, 35714, r14, 0);
        this.mProgramObject = r2;
        init(r12);
        return;
    L25:
        r132 = "shaders/fragment3.glsl";
    L26:
        r132 = "shaders/fragment4.glsl";
        goto L27
    L6:
        if (r13 != 3) goto L8;
        r5 = dealModel;
        goto L15
    L8:
        if (r13 == 0) goto L14;
        if (r13 == 2) goto L14;
        if (r13 != 4) goto L13;
        r5 = diamondModel;
        r1 = 8.0f;
        goto L15
    L13:
        r5 = new String[0];
    L14:
        r5 = starModel;
        goto L15
    }

    private void drawModel(int r10, boolean r11) {
        int r1 = r10 * 3;
        GLES20.glBindBuffer(34962, this.buffers[r1]);
        GLES20.glVertexAttribPointer(this.mTextureCoordinateHandle, 2, 5126, false, 0, 0);
        GLES20.glBindBuffer(34962, this.buffers[r1 + 1]);
        GLES20.glVertexAttribPointer(this.mNormalCoordinateHandle, 3, 5126, false, 0, 0);
        GLES20.glBindBuffer(34962, this.buffers[r1 + 2]);
        GLES20.glVertexAttribPointer(this.mVerticesHandle, 3, 5126, false, 0, 0);
        GLES20.glUniform1i(this.modelIndexHandle, r10);
        GLES20.glUniform1i(this.modelIndex2Handle, r10);
        GLES20.glUniform1i(this.behindHandle, r11 ? 1 : 0);
        GLES20.glUniform1i(this.typeHandle, this.type);
        GLES20.glDrawArrays(4, 0, this.trianglesCount[r10] / 3);
    }

    private void generateTexture() {
        this.texture = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas r1 = new Canvas(this.texture);
        Paint r6 = new Paint();
        r6.setShader(new LinearGradient(0.0f, 100.0f, 150.0f, 0.0f, new int[]{Theme.getColor(Theme.key_premiumGradient1), Theme.getColor(Theme.key_premiumGradient2), Theme.getColor(Theme.key_premiumGradient3), Theme.getColor(Theme.key_premiumGradient4)}, new float[]{0.0f, 0.5f, 0.78f, 1.0f}, Shader.TileMode.CLAMP));
        r1.drawRect(0.0f, 0.0f, 100.0f, 100.0f, r6);
        int[] r12 = new int[1];
        GLES20.glGenTextures(1, r12, 0);
        GLES20.glBindTexture(3553, r12[0]);
        GLES20.glTexParameteri(3553, 10241, 9728);
        GLES20.glTexParameteri(3553, 10240, 9728);
        GLUtils.texImage2D(3553, 0, this.texture, 0);
        this.mTextureDataHandle = r12[0];
    }

    public static Bitmap getBitmapFromAsset(Context r0, String r1) {
        return BitmapFactory.decodeStream(r0.getAssets().open(r1));
    L5:
        return null;
    }

    private void init(Context r13) {
        GLES20.glUseProgram(this.mProgramObject);
        this.mVerticesHandle = GLES20.glGetAttribLocation(this.mProgramObject, "vPosition");
        this.mTextureCoordinateHandle = GLES20.glGetAttribLocation(this.mProgramObject, "a_TexCoordinate");
        this.mNormalCoordinateHandle = GLES20.glGetAttribLocation(this.mProgramObject, "a_Normal");
        this.mTextureUniformHandle = GLES20.glGetUniformLocation(this.mProgramObject, "u_Texture");
        this.mNormalMapUniformHandle = GLES20.glGetUniformLocation(this.mProgramObject, "u_NormalMap");
        this.mBackgroundTextureUniformHandle = GLES20.glGetUniformLocation(this.mProgramObject, "u_BackgroundTexture");
        this.xOffsetHandle = GLES20.glGetUniformLocation(this.mProgramObject, "f_xOffset");
        this.alphaHandle = GLES20.glGetUniformLocation(this.mProgramObject, "f_alpha");
        this.mMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgramObject, "uMVPMatrix");
        this.mWorldMatrixHandle = GLES20.glGetUniformLocation(this.mProgramObject, "world");
        this.whiteHandle = GLES20.glGetUniformLocation(this.mProgramObject, "white");
        this.goldenHandle = GLES20.glGetUniformLocation(this.mProgramObject, "golden");
        this.specHandleTop = GLES20.glGetUniformLocation(this.mProgramObject, "spec1");
        this.specHandleBottom = GLES20.glGetUniformLocation(this.mProgramObject, "spec2");
        this.diffuseHandle = GLES20.glGetUniformLocation(this.mProgramObject, "u_diffuse");
        this.gradientColor1Handle = GLES20.glGetUniformLocation(this.mProgramObject, "gradientColor1");
        this.gradientColor2Handle = GLES20.glGetUniformLocation(this.mProgramObject, "gradientColor2");
        this.normalSpecColorHandle = GLES20.glGetUniformLocation(this.mProgramObject, "normalSpecColor");
        this.normalSpecHandle = GLES20.glGetUniformLocation(this.mProgramObject, "normalSpec");
        this.specColorHandle = GLES20.glGetUniformLocation(this.mProgramObject, "specColor");
        this.resolutionHandle = GLES20.glGetUniformLocation(this.mProgramObject, "resolution");
        this.gradientPositionHandle = GLES20.glGetUniformLocation(this.mProgramObject, "gradientPosition");
        this.modelIndexHandle = GLES20.glGetUniformLocation(this.mProgramObject, "modelIndex");
        this.modelIndex2Handle = GLES20.glGetUniformLocation(this.mProgramObject, "modelIndex2");
        this.behindHandle = GLES20.glGetUniformLocation(this.mProgramObject, "behind");
        this.typeHandle = GLES20.glGetUniformLocation(this.mProgramObject, "type");
        this.nightHandle = GLES20.glGetUniformLocation(this.mProgramObject, "night");
        this.timeHandle = GLES20.glGetUniformLocation(this.mProgramObject, "time");
        int r0 = this.N;
        int[] r1 = new int[r0 * 3];
        this.buffers = r1;
        GLES20.glGenBuffers(r0 * 3, r1, 0);
        int r02 = 0;
    L4:
        if (r02 >= this.N) goto L6;
        int r7 = r02 * 3;
        GLES20.glBindBuffer(34962, this.buffers[r7]);
        this.mTextures[r02].position(0);
        GLES20.glBufferData(34962, this.mTextures[r02].capacity() * 4, this.mTextures[r02], 35044);
        GLES20.glEnableVertexAttribArray(this.mTextureCoordinateHandle);
        this.mTextures[r02].clear();
        GLES20.glBindBuffer(34962, this.buffers[r7 + 1]);
        this.mNormals[r02].position(0);
        GLES20.glBufferData(34962, this.mNormals[r02].capacity() * 4, this.mNormals[r02], 35044);
        GLES20.glEnableVertexAttribArray(this.mNormalCoordinateHandle);
        this.mNormals[r02].clear();
        GLES20.glBindBuffer(34962, this.buffers[r7 + 2]);
        this.mVertices[r02].position(0);
        GLES20.glBufferData(34962, this.mVertices[r02].capacity() * 4, this.mVertices[r02], 35044);
        GLES20.glEnableVertexAttribArray(this.mVerticesHandle);
        this.mVertices[r02].clear();
        r02 = r02 + 1;
        goto L4
    L6:
        GLES20.glBindBuffer(34962, 0);
        int[] r12 = new int[1];
        GLES20.glGenTextures(1, r12, 0);
        int r14 = r12[0];
        this.mTextureDataHandle = r14;
        GLES20.glBindTexture(3553, r14);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glBindTexture(3553, this.mTextureDataHandle);
        Bitmap r9 = getBitmapFromAsset(r13, "flecks.png");
        int[] r10 = new int[1];
        GLES20.glGenTextures(1, r10, 0);
        GLES20.glBindTexture(3553, r10[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLUtils.texImage2D(3553, 0, r9, 0);
        r9.recycle();
        int[] r92 = new int[1];
        GLES20.glGenTextures(1, r92, 0);
        int r11 = r92[0];
        this.mBackgroundTextureHandle = r11;
        GLES20.glBindTexture(3553, r11);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glBindTexture(3553, this.mBackgroundTextureHandle);
        int r112 = this.type;
        if (r112 == 0) goto L15;
        if (r112 == 2) goto L15;
        if (r112 != 1) goto L12;
        Bitmap r132 = getBitmapFromAsset(r13, "models/coin_border.png");
    L16:
        if (r132 == null) goto L18;
        int[] r2 = new int[1];
        GLES20.glGenTextures(1, r2, 0);
        GLES20.glBindTexture(3553, r2[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLUtils.texImage2D(3553, 0, r132, 0);
        r132.recycle();
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r2[0]);
        GLES20.glUniform1i(this.mTextureUniformHandle, 0);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, r10[0]);
        GLES20.glUniform1i(this.mNormalMapUniformHandle, 1);
    L18:
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(3553, r92[0]);
        GLES20.glUniform1i(this.mBackgroundTextureUniformHandle, 2);
        if (this.type != 4) goto L23;
        GLES20.glEnable(2884);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        return;
    L23:
        return;
    L12:
        if (r112 != 3) goto L14;
        r132 = getBitmapFromAsset(r13, "models/deal_border.png");
        goto L16
    L14:
        r132 = null;
    L15:
        r132 = SvgHelper.getBitmap(R.raw.start_texture, 240, 240, -1);
        goto L16
    }

    private String preprocessShader(String r9) {
        Matcher r8 = Pattern.compile("RGB#([0-9a-fA-F]{6})").matcher(r9);
        StringBuffer r92 = new StringBuffer();
    L4:
        if (r8.find() == false) goto L6;
        String r0 = r8.group(1);
        r8.appendReplacement(r92, String.format(Locale.US, "vec3(%.3f, %.3f, %.3f)", new Object[]{Double.valueOf(Integer.parseInt(r0.substring(0, 2), 16) / 255.0d), Double.valueOf(Integer.parseInt(r0.substring(2, 4), 16) / 255.0d), Double.valueOf(Integer.parseInt(r0.substring(4, 6), 16) / 255.0d)}));
        goto L4
    L6:
        r8.appendTail(r92);
        return r92.toString();
    }

    public void destroy() {
        GLES20.glDeleteProgram(this.mProgramObject);
    }

    public void draw(float[] r4, float[] r5, int r6, int r7, float r8, float r9, float r10, float r11, float r12, float r13, float r14) {
        if (this.backgroundBitmap == null) goto L5;
        GLES20.glBindTexture(3553, this.mBackgroundTextureHandle);
        GLUtils.texImage2D(3553, 0, this.backgroundBitmap, 0);
        this.backgroundBitmap = null;
    L5:
        GLES20.glUniform1i(this.mTextureUniformHandle, 0);
        GLES20.glUniform1f(this.xOffsetHandle, this.xOffset);
        GLES20.glUniform1f(this.alphaHandle, this.enterAlpha);
        GLES20.glUniform1f(this.whiteHandle, r12);
        GLES20.glUniform1f(this.goldenHandle, r13);
        GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, r4, 0);
        GLES20.glUniformMatrix4fv(this.mWorldMatrixHandle, 1, false, r5, 0);
        GLES20.glUniform1f(this.specHandleTop, this.spec1);
        GLES20.glUniform1f(this.specHandleBottom, this.spec2);
        GLES20.glUniform1f(this.diffuseHandle, this.diffuse);
        GLES20.glUniform1f(this.normalSpecHandle, this.normalSpec);
        GLES20.glUniform3f(this.gradientColor1Handle, Color.red(this.gradientColor1) / 255.0f, Color.green(this.gradientColor1) / 255.0f, Color.blue(this.gradientColor1) / 255.0f);
        GLES20.glUniform3f(this.gradientColor2Handle, Color.red(this.gradientColor2) / 255.0f, Color.green(this.gradientColor2) / 255.0f, Color.blue(this.gradientColor2) / 255.0f);
        GLES20.glUniform3f(this.normalSpecColorHandle, Color.red(this.normalSpecColor) / 255.0f, Color.green(this.normalSpecColor) / 255.0f, Color.blue(this.normalSpecColor) / 255.0f);
        GLES20.glUniform3f(this.specColorHandle, Color.red(this.specColor) / 255.0f, Color.green(this.specColor) / 255.0f, Color.blue(this.specColor) / 255.0f);
        GLES20.glUniform2f(this.resolutionHandle, r6, r7);
        GLES20.glUniform4f(this.gradientPositionHandle, r8, r9, r10, r11);
        GLES20.glUniform1i(this.nightHandle, this.night ? 1 : 0);
        float r42 = this.time + r14;
        this.time = r42;
        GLES20.glUniform1f(this.timeHandle, r42);
        if (this.type != 4) goto L8;
        drawModel(0, true);
        GLES20.glClear(256);
        drawModel(1, true);
        GLES20.glClear(256);
        drawModel(2, false);
        drawModel(1, false);
        drawModel(0, false);
    L12:
        float r43 = this.enterAlpha;
        if (r43 >= 1.0f) goto L17;
        float r44 = r43 + 0.07272727f;
        this.enterAlpha = r44;
        if (r44 <= 1.0f) goto L17;
        this.enterAlpha = 1.0f;
    L17:
        float r45 = this.xOffset + 5.0E-4f;
        this.xOffset = r45;
        if (r45 <= 1.0f) goto L22;
        this.xOffset = r45 - 1.0f;
        return;
    L22:
        return;
    L8:
        int r46 = 0;
    L10:
        if (r46 >= this.N) goto L12;
        drawModel(r46, false);
        r46 = r46 + 1;
        goto L10
    }

    public String loadFromAsset(Context r3, String r4) {
        StringBuilder r2 = new StringBuilder();
        InputStream r32 = r3.getAssets().open(r4);     // Catch: IOException -> L7
        BufferedReader r42 = new BufferedReader(new InputStreamReader(r32, StandardCharsets.UTF_8));     // Catch: IOException -> L7
    L4:
        String r0 = r42.readLine();     // Catch: IOException -> L7
        if (r0 == null) goto L9;
        r2.append(r0);     // Catch: IOException -> L7
        r2.append("\n");     // Catch: IOException -> L7
        goto L4
    L9:
        r42.close();     // Catch: IOException -> L7
        r32.close();     // Catch: IOException -> L7
    L13:
        return r2.toString();
    L7:
        e = move-exception;
        e.printStackTrace();
        goto L13
    }

    public void setBackground(Bitmap r1) {
        this.backgroundBitmap = r1;
    }
}
