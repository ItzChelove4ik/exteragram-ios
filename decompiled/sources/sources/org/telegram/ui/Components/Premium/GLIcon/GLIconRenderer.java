package org.telegram.ui.Components.Premium.GLIcon;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import androidx.core.graphics.ColorUtils;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class GLIconRenderer implements GLSurfaceView.Renderer {
    public float angleX;
    public float angleX2;
    public float angleX3;
    public float angleY;
    Bitmap backgroundBitmap;
    int color1;
    int color2;
    public int colorKey1;
    public int colorKey2;
    Context context;
    private float dt;
    public boolean forceNight;
    public float golden;
    public int goldenColorKey1;
    public int goldenColorKey2;
    public float gradientScaleX;
    public float gradientScaleY;
    public float gradientStartX;
    public float gradientStartY;
    public boolean isDarkBackground;
    private int mHeight;
    private final float[] mMVPMatrix;
    private final float[] mProjectionMatrix;
    private final float[] mRotationMatrix;
    private final float[] mViewMatrix;
    private int mWidth;
    public Icon3D model;
    boolean night;
    private final int style;
    private final int type;
    public float white;

    public GLIconRenderer(Context r3, int r4, int r5) {
        this.angleX = 0.0f;
        this.angleX2 = 0.0f;
        this.angleX3 = 0.0f;
        this.angleY = 0.0f;
        this.white = 0.0f;
        this.golden = 0.0f;
        this.mMVPMatrix = new float[16];
        this.mProjectionMatrix = new float[16];
        this.mViewMatrix = new float[16];
        this.mRotationMatrix = new float[16];
        this.colorKey1 = Theme.key_premiumStarGradient1;
        this.colorKey2 = Theme.key_premiumStarGradient2;
        this.goldenColorKey1 = Theme.key_starsGradient1;
        this.goldenColorKey2 = Theme.key_starsGradient2;
        this.context = r3;
        this.style = r4;
        this.type = r5;
        if (r5 != 2) goto L5;
        this.golden = 1.0f;
    L5:
        updateColors();
    }

    public static int loadShader(int r3, String r4) {
        int[] r0 = new int[1];
        int r32 = GLES20.glCreateShader(r3);
        if (r32 != 0) goto L5;
        return 0;
    L5:
        GLES20.glShaderSource(r32, r4);
        GLES20.glCompileShader(r32);
        GLES20.glGetShaderiv(r32, 35713, r0, 0);
        if (r0[0] == 0) goto L8;
        return r32;
    L8:
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("Could not compile program: ", GLES20.glGetShaderInfoLog(r32), " ", r4);
        return 0;
    }

    public void destroy() {
        Icon3D r0 = this.model;
        if (r0 == null) goto L6;
        r0.destroy();
        this.model = null;
        return;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 r26) {
        GLES20.glClear(16640);
        GLES20.glEnable(2929);
        float[] r2 = this.mViewMatrix;
        if (this.type != 4) goto L5;
        float r5 = 40.0f;
    L6:
        Matrix.setLookAtM(r2, 0, 0.0f, r5, 100.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        Matrix.setIdentityM(this.mRotationMatrix, 0);
        Matrix.translateM(this.mRotationMatrix, 0, 0.0f, this.angleX2, 0.0f);
        Matrix.rotateM(this.mRotationMatrix, 0, -this.angleY, 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(this.mRotationMatrix, 0, (-this.angleX) - this.angleX3, 0.0f, 1.0f, 0.0f);
        Matrix.multiplyMM(this.mMVPMatrix, 0, this.mViewMatrix, 0, this.mRotationMatrix, 0);
        float[] r7 = this.mMVPMatrix;
        Matrix.multiplyMM(r7, 0, this.mProjectionMatrix, 0, r7, 0);
        Icon3D r13 = this.model;
        if (r13 == null) goto L10;
        r13.night = this.night;
        r13.gradientColor1 = this.color1;
        r13.gradientColor2 = this.color2;
        r13.draw(this.mMVPMatrix, this.mRotationMatrix, this.mWidth, this.mHeight, this.gradientStartX, this.gradientScaleX, this.gradientStartY, this.gradientScaleY, this.white, this.golden, this.dt);
        return;
    L10:
        return;
    L5:
        r5 = 0.0f;
        goto L6
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 r7, int r8, int r9) {
        this.mWidth = r8;
        this.mHeight = r9;
        GLES20.glViewport(0, 0, r8, r9);
        float r3 = r8 / r9;
        if (this.type != 4) goto L6;
        float r72 = 12.0f;
    L7:
        Matrix.perspectiveM(this.mProjectionMatrix, 0, r72, r3, 1.0f, 200.0f);
        return;
    L6:
        r72 = 53.13f;
        goto L7
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 r2, EGLConfig r3) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Icon3D r22 = this.model;
        if (r22 == null) goto L5;
        r22.destroy();
    L5:
        Icon3D r23 = new Icon3D(this.context, this.type);
        this.model = r23;
        Bitmap r32 = this.backgroundBitmap;
        if (r32 == null) goto L9;
        r23.setBackground(r32);
    L9:
        if (this.isDarkBackground == false) goto L12;
        Icon3D r1 = this.model;
        r1.spec1 = 1.0f;
        r1.spec2 = 0.2f;
        return;
    }

    public void setBackground(Bitmap r2) {
        Icon3D r0 = this.model;
        if (r0 == null) goto L5;
        r0.setBackground(r2);
    L5:
        this.backgroundBitmap = r2;
    }

    public void setDeltaTime(float r1) {
        this.dt = r1;
    }

    public void updateColors() {
        boolean r1 = false;
        if (this.forceNight == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        this.night = r0;
        this.color1 = ColorUtils.blendARGB(Theme.getColor(this.colorKey1), Theme.getColor(this.goldenColorKey1), this.golden);
        this.color2 = ColorUtils.blendARGB(Theme.getColor(this.colorKey2), Theme.getColor(this.goldenColorKey2), this.golden);
        if (this.style == 1) goto L12;
    L14:
        this.isDarkBackground = r1;
        return;
    L12:
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_dialogBackground)) >= 0.5d) goto L14;
        r1 = true;
        goto L14
    L5:
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_dialogBackground)) < 0.5d) goto L8;
        r0 = false;
        goto L9
    }
}
