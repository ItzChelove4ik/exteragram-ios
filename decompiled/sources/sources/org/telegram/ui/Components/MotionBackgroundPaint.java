package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.os.Build;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.ColorShader;
import org.telegram.ui.Components.blur3.utils.BitmapChangeTracker;
import org.telegram.ui.Components.blur3.utils.BitmapMemoizedMetadata;

/* loaded from: classes7.dex */
public class MotionBackgroundPaint {
    private static final Matrix tmpInverse = null;
    private static final float[] tmpPts = null;
    private final AgslImpl agslImpl;
    private int gradientHeight;
    private final BitmapMemoizedSoftLight gradientSoftLightBitmapMemo;
    private int gradientWidth;
    private final BitmapMemoizedMetadata<Bitmap> patterAlphaBitmapMemo;
    private int patternHeight;
    private int patternWidth;
    private final ShaderImpl shaderImpl;
    private final Matrix tmpMatrix;
    private final RectF tmpRectF;

    public static class AgslImpl {
        private final BitmapShaderState gradientShader;
        private final BitmapShaderState gradientSoftLightShader;
        private float lastIntensity;
        private int lastMode;
        private final Paint paint;
        private final BitmapShaderState patternShader;
        private final RuntimeShaderState runtimeShaderNegative;
        private final RuntimeShaderState runtimeShaderPositive;
        private final float[] tmpOut;

        public static class RuntimeShaderState {
            private final RuntimeShader shader;
            private final float[] transformGradient;
            private final float[] transformPattern;

            /* renamed from: -$$Nest$fgetshader, reason: not valid java name */
            public static /* bridge */ /* synthetic */ RuntimeShader m11301$$Nest$fgetshader(RuntimeShaderState r0) {
                return r0.shader;
            }

            /* renamed from: -$$Nest$msetMatrixUniforms, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m11302$$Nest$msetMatrixUniforms(RuntimeShaderState r0) {
                r0.setMatrixUniforms();
            }

            public RuntimeShaderState(int r3) {
                this.transformGradient = new float[]{1.0f, 1.0f, 0.0f, 0.0f};
                this.transformPattern = new float[]{1.0f, 1.0f, 0.0f, 0.0f};
                MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline1.m();
                this.shader = MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline0.m(AndroidUtilities.readRes(r3));
            }

            private void setMatrixUniformGradient() {
                this.shader.setFloatUniform("transformGradient", this.transformGradient);
            }

            private void setMatrixUniformPattern() {
                this.shader.setFloatUniform("transformPattern", this.transformPattern);
            }

            private void setMatrixUniforms() {
                setMatrixUniformGradient();
                setMatrixUniformPattern();
            }

            public void setMiniMatrixGradient(float[] r4) {
                if (Arrays.equals(r4, this.transformGradient) == true) goto L6;
                System.arraycopy(r4, 0, this.transformGradient, 0, 4);
                setMatrixUniformGradient();
                return;
            }

            public void setMiniMatrixPattern(float[] r4) {
                if (Arrays.equals(r4, this.transformPattern) == true) goto L6;
                System.arraycopy(r4, 0, this.transformPattern, 0, 4);
                setMatrixUniformPattern();
                return;
            }
        }

        public AgslImpl() {
            Paint r0 = new Paint();
            this.paint = r0;
            Shader.TileMode r2 = Shader.TileMode.CLAMP;
            this.gradientShader = new BitmapShaderState(r2);
            this.gradientSoftLightShader = new BitmapShaderState(r2);
            this.patternShader = new BitmapShaderState(Shader.TileMode.REPEAT);
            this.runtimeShaderPositive = new RuntimeShaderState(R.raw.wallpaper_pos_intensity);
            this.runtimeShaderNegative = new RuntimeShaderState(R.raw.wallpaper_neg_intensity);
            this.tmpOut = new float[4];
            r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }

        public void applyGradientMatrix(Matrix r2) {
            MotionBackgroundPaint.m11300$$Nest$smmatrixToScaleTranslate(r2, this.tmpOut);
            this.runtimeShaderPositive.setMiniMatrixGradient(this.tmpOut);
            this.runtimeShaderNegative.setMiniMatrixGradient(this.tmpOut);
        }

        public void applyPatternMatrix(Matrix r4) {
            MotionBackgroundPaint.m11300$$Nest$smmatrixToScaleTranslate(r4, this.tmpOut);
            BitmapShaderState r42 = this.patternShader;
            boolean r1 = false;
            if (MotionBackgroundPaint.m11299$$Nest$smisOne(this.tmpOut[0]) == true) goto L5;
        L7:
            r42.setUseNearestInterpolation(r1);
            this.runtimeShaderPositive.setMiniMatrixPattern(this.tmpOut);
            this.runtimeShaderNegative.setMiniMatrixPattern(this.tmpOut);
            return;
        L5:
            if (MotionBackgroundPaint.m11299$$Nest$smisOne(this.tmpOut[1]) == false) goto L7;
            r1 = true;
            goto L7
        }

        public Paint getPaint(Bitmap r2, Bitmap r3, Bitmap r4, int r5, int r6) {
            boolean r22 = this.gradientShader.setup(r2) | this.patternShader.setup(r3);
            if (r6 >= 0) goto L5;
            float r42 = MathUtils.clamp((r5 * (-r6)) / 25500.0f, 0.0f, 1.0f);
            if (r22 == false) goto L12;
        L15:
            this.lastMode = 2;
            this.lastIntensity = r42;
            RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderNegative).setInputBuffer("shaderPattern", this.patternShader.shader);
            RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderNegative).setInputBuffer("shaderGradient", this.gradientShader.shader);
            RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderNegative).setFloatUniform("intensity", r42);
            RuntimeShaderState.m11302$$Nest$msetMatrixUniforms(this.runtimeShaderNegative);
            this.paint.setShader(RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderNegative));
        L17:
            return this.paint;
        L12:
            if (this.lastIntensity != r42) goto L15;
            if (this.lastMode == 2) goto L17;
        L5:
            if ((r22 | this.gradientSoftLightShader.setup(r4)) == false) goto L7;
        L8:
            this.lastMode = 1;
            RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderPositive).setInputBuffer("shaderPattern", this.patternShader.shader);
            RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderPositive).setInputBuffer("shaderGradient", this.gradientShader.shader);
            RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderPositive).setInputBuffer("shaderGradientSoftLight", this.gradientSoftLightShader.shader);
            RuntimeShaderState.m11302$$Nest$msetMatrixUniforms(this.runtimeShaderPositive);
            this.paint.setShader(RuntimeShaderState.m11301$$Nest$fgetshader(this.runtimeShaderPositive));
            goto L17
        L7:
            if (this.lastMode == 1) goto L17;
            goto L8
        }
    }

    public static class BitmapMemoizedSoftLight {
        private final BitmapChangeTracker lastBitmap;
        private int lastColor;
        private Bitmap memoized;

        private BitmapMemoizedSoftLight() {
            this.lastBitmap = new BitmapChangeTracker();
        }

        public /* synthetic */ BitmapMemoizedSoftLight(MotionBackgroundPaintIA r1) {
            this();
        }

        public Bitmap get(Bitmap r3, int r4) {
            if (this.lastBitmap.isInvalidated(r3) == false) goto L5;
        L8:
            Bitmap r0 = this.memoized;
            if (r0 != null) goto L11;
        L14:
            this.memoized = Bitmap.createBitmap(r3);
        L15:
            Utilities.applySoftLight(r3, this.memoized, r4);
            this.lastBitmap.set(r3);
            this.lastColor = r4;
        L17:
            return this.memoized;
        L11:
            if (r0.getWidth() != r3.getWidth()) goto L14;
            if (this.memoized.getHeight() == r3.getHeight()) goto L15;
        L5:
            if (r4 != this.lastColor) goto L8;
            if (this.memoized != null) goto L17;
            goto L8
        }
    }

    public static class BitmapShaderState {
        WeakReference<Bitmap> bitmap;
        final Matrix localMatrix;
        BitmapShader shader;
        final Shader.TileMode tileMode;
        boolean useNearestInterpolation;

        public BitmapShaderState(Shader.TileMode r2) {
            this.localMatrix = new Matrix();
            this.tileMode = r2;
        }

        public void setLocalMatrix(Matrix r2) {
            this.localMatrix.set(r2);
            BitmapShader r1 = this.shader;
            if (r1 == null) goto L6;
            r1.setLocalMatrix(r2);
            return;
        }

        public void setUseNearestInterpolation(boolean r3) {
            if (this.useNearestInterpolation == r3) goto L13;
            this.useNearestInterpolation = r3;
            if (Build.VERSION.SDK_INT < 33) goto L14;
            BitmapShader r2 = this.shader;
            if (r2 == null) goto L15;
            if (r3 == false) goto L10;
            int r32 = 1;
        L11:
            r2.setFilterMode(r32);
            return;
        L10:
            r32 = 2;
            goto L11
        L15:
            return;
        L14:
            return;
        }

        public boolean setup(Bitmap r3) {
            WeakReference<Bitmap> r0 = this.bitmap;
            if (r0 != null) goto L5;
        L8:
            this.bitmap = new WeakReference(r3);
            Shader.TileMode r1 = this.tileMode;
            BitmapShader r02 = new BitmapShader(r3, r1, r1);
            this.shader = r02;
            r02.setLocalMatrix(this.localMatrix);
            if (Build.VERSION.SDK_INT < 33) goto L15;
            BitmapShader r32 = this.shader;
            if (this.useNearestInterpolation == false) goto L13;
            int r2 = 1;
        L14:
            r32.setFilterMode(r2);
            goto L15
        L13:
            r2 = 2;
        L15:
            return true;
        L5:
            if (r0.get() != r3) goto L8;
            return false;
        }
    }

    public static class ShaderImpl {
        private final ColorShaderState alphaShader;
        private final ColorShaderState colorShader;
        private final BitmapShaderState gradientShader;
        private final BitmapShaderState gradientSoftLightShader;
        private int lastMode;
        private final Paint paint;
        private final BitmapShaderState patternShader;
        private final float[] tmpOut;

        public static class ColorShaderState {
            int color;
            ColorShader shader;

            private ColorShaderState() {
            }

            public /* synthetic */ ColorShaderState(MotionBackgroundPaintIA r1) {
                this();
            }

            public boolean setup(int r2) {
                if (this.shader != null) goto L5;
            L9:
                this.color = r2;
                this.shader = new ColorShader(r2);
                return true;
            L5:
                if (this.color != r2) goto L9;
                return false;
            }
        }

        public ShaderImpl() {
            Paint r0 = new Paint();
            this.paint = r0;
            Shader.TileMode r2 = Shader.TileMode.CLAMP;
            this.gradientShader = new BitmapShaderState(r2);
            this.gradientSoftLightShader = new BitmapShaderState(r2);
            this.patternShader = new BitmapShaderState(Shader.TileMode.REPEAT);
            this.colorShader = new ColorShaderState(null);
            this.alphaShader = new ColorShaderState(null);
            this.tmpOut = new float[4];
            r0.setFilterBitmap(true);
            r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }

        public void applyGradientMatrix(Matrix r2) {
            this.gradientShader.setLocalMatrix(r2);
            this.gradientSoftLightShader.setLocalMatrix(r2);
        }

        public void applyPatternMatrix(Matrix r3) {
            MotionBackgroundPaint.m11300$$Nest$smmatrixToScaleTranslate(r3, this.tmpOut);
            this.patternShader.setLocalMatrix(r3);
            BitmapShaderState r32 = this.patternShader;
            boolean r1 = false;
            if (MotionBackgroundPaint.m11299$$Nest$smisOne(this.tmpOut[0]) == true) goto L5;
        L7:
            r32.setUseNearestInterpolation(r1);
            return;
        L5:
            if (MotionBackgroundPaint.m11299$$Nest$smisOne(this.tmpOut[1]) == false) goto L7;
            r1 = true;
            goto L7
        }

        public Paint getPaint(Bitmap r4, Bitmap r5, Bitmap r6, int r7, int r8) {
            boolean r42 = this.gradientShader.setup(r4) | this.patternShader.setup(r5);
            if (r8 >= 0) goto L5;
            int r72 = (r7 * (-r8)) / 100;
            if (((r42 | this.alphaShader.setup(ColorUtils.setAlphaComponent(-1, r72))) | this.colorShader.setup(-16777216)) == false) goto L12;
        L13:
            this.lastMode = 2;
            this.paint.setShader(new ComposeShader(this.colorShader.shader, new ComposeShader(new ComposeShader(this.gradientShader.shader, this.patternShader.shader, PorterDuff.Mode.DST_IN), this.alphaShader.shader, PorterDuff.Mode.MULTIPLY), PorterDuff.Mode.SRC_OVER));
        L15:
            return this.paint;
        L12:
            if (this.lastMode == 2) goto L15;
        L5:
            if ((r42 | this.gradientSoftLightShader.setup(r6)) == false) goto L7;
        L8:
            this.lastMode = 1;
            this.paint.setShader(new ComposeShader(this.gradientShader.shader, new ComposeShader(this.gradientSoftLightShader.shader, this.patternShader.shader, PorterDuff.Mode.DST_IN), PorterDuff.Mode.SRC_OVER));
            goto L15
        L7:
            if (this.lastMode == 1) goto L15;
            goto L8
        }
    }

    public static /* synthetic */ Bitmap $r8$lambda$0AgtaGyogqspHFSXzfi5DOAUxHw(Bitmap r0) {
        return getAlphaChannel(r0);
    }

    /* renamed from: -$$Nest$smisOne, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11299$$Nest$smisOne(float r0) {
        return isOne(r0);
    }

    /* renamed from: -$$Nest$smmatrixToScaleTranslate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11300$$Nest$smmatrixToScaleTranslate(Matrix r0, float[] r1) {
        matrixToScaleTranslate(r0, r1);
    }

    static {
        tmpPts = new float[4];
        tmpInverse = new Matrix();
    }

    public MotionBackgroundPaint() {
        this.patterAlphaBitmapMemo = new BitmapMemoizedMetadata(new MotionBackgroundPaint$$ExternalSyntheticLambda0());
        this.gradientSoftLightBitmapMemo = new BitmapMemoizedSoftLight(null);
        this.shaderImpl = new ShaderImpl();
        this.tmpMatrix = new Matrix();
        this.tmpRectF = new RectF();
        if (Build.VERSION.SDK_INT < 33) goto L6;
        this.agslImpl = new AgslImpl();
        return;
    L6:
        this.agslImpl = null;
    }

    private static Bitmap getAlphaChannel(Bitmap r2) {
        if (r2.getConfig() != Bitmap.Config.ALPHA_8) goto L6;
        return r2;
    L6:
        return r2.extractAlpha();
    }

    private static boolean isOne(float r1) {
        if (Math.abs(r1 - 1.0f) > 1.0E-4f) goto L6;
        return true;
    L6:
        return false;
    }

    private static void matrixToScaleTranslate(Matrix r6, float[] r7) {
        Matrix r0 = tmpInverse;
        r6.invert(r0);
        float[] r62 = tmpPts;
        r62[0] = 0.0f;
        r62[1] = 0.0f;
        r62[2] = 1.0f;
        r62[3] = 1.0f;
        r0.mapPoints(r62);
        r7[0] = r62[2] - r62[0];
        r7[1] = r62[3] - r62[1];
        r7[2] = r62[0];
        r7[3] = r62[1];
    }

    public void applyGradientMatrix(RectF r5) {
        this.tmpRectF.set(0.0f, 0.0f, this.gradientWidth, this.gradientHeight);
        this.tmpMatrix.setRectToRect(this.tmpRectF, r5, Matrix.ScaleToFit.FILL);
        this.shaderImpl.applyGradientMatrix(this.tmpMatrix);
        AgslImpl r52 = this.agslImpl;
        if (r52 != null) goto L5;
        return;
    L5:
        if (Build.VERSION.SDK_INT < 33) goto L9;
        r52.applyGradientMatrix(this.tmpMatrix);
        return;
    }

    public void applyPatternMatrix(Matrix r3) {
        this.shaderImpl.applyPatternMatrix(r3);
        AgslImpl r2 = this.agslImpl;
        if (r2 != null) goto L5;
        return;
    L5:
        if (Build.VERSION.SDK_INT < 33) goto L9;
        r2.applyPatternMatrix(r3);
        return;
    }

    public void applyPatternMatrix(RectF r5) {
        this.tmpRectF.set(0.0f, 0.0f, this.patternWidth, this.patternHeight);
        this.tmpMatrix.setRectToRect(this.tmpRectF, r5, Matrix.ScaleToFit.FILL);
        applyPatternMatrix(this.tmpMatrix);
    }

    public Paint getPaint(Bitmap r7, Bitmap r8, int r9, int r10, int r11, boolean r12) {
        Bitmap r2 = this.patterAlphaBitmapMemo.get(r8);
        if (r11 < 0) goto L6;
        int r82 = ColorUtils.setAlphaComponent(r9, ((Color.alpha(r9) * r10) * r11) / 25500);
        Bitmap r83 = this.gradientSoftLightBitmapMemo.get(r7, r82);
    L5:
        Bitmap r3 = r83;
        this.gradientWidth = r7.getWidth();
        this.gradientHeight = r7.getHeight();
        this.patternWidth = r2.getWidth();
        this.patternHeight = r2.getHeight();
        AgslImpl r0 = this.agslImpl;
        if (r0 == null) goto L15;
        if (r12 == false) goto L15;
        if (Build.VERSION.SDK_INT < 33) goto L15;
        return r0.getPaint(r7, r2, r3, r10, r11);
    L15:
        return this.shaderImpl.getPaint(r7, r2, r3, r10, r11);
    L6:
        r83 = null;
        goto L5
    }
}
