package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public class GradientTools {
    RectF bounds;
    int color1;
    int color2;
    int color3;
    int color4;
    int[] colors;
    Bitmap gradientBitmap;
    public boolean isDiagonal;
    public boolean isLinear;
    public boolean isRotate;
    Matrix matrix;
    public Paint paint;
    Shader shader;

    public GradientTools() {
        this.paint = new Paint(1);
        this.bounds = new RectF();
        this.matrix = new Matrix();
        this.gradientBitmap = null;
        this.colors = new int[4];
    }

    public int getAverageColor() {
        int r0 = this.color1;
        int r1 = this.color2;
        if (r1 == 0) goto L5;
        r0 = ColorUtils.blendARGB(r0, r1, 0.5f);
    L5:
        int r12 = this.color3;
        if (r12 == 0) goto L8;
        r0 = ColorUtils.blendARGB(r0, r12, 0.5f);
    L8:
        int r3 = this.color4;
        if (r3 != 0) goto L11;
        return r0;
    L11:
        return ColorUtils.blendARGB(r0, r3, 0.5f);
    }

    public void setBounds(float r2, float r3, float r4, float r5) {
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r2, r3, r4, r5);
        setBounds(r0);
    }

    public void setBounds(RectF r4) {
        RectF r0 = this.bounds;
        if (r0.top == r4.top) goto L5;
    L11:
        r0.set(r4);
        updateBounds();
        return;
    L5:
        if (r0.bottom != r4.bottom) goto L11;
        if (r0.left != r4.left) goto L11;
        if (r0.right != r4.right) goto L11;
    }

    public void setColors(int r2, int r3) {
        setColors(r2, r3, 0, 0);
    }

    public void setColors(int r11, int r12, int r13, int r14) {
        if (this.shader != null) goto L5;
    L13:
        int[] r0 = this.colors;
        this.color1 = r11;
        r0[0] = r11;
        this.color2 = r12;
        r0[1] = r12;
        this.color3 = r13;
        r0[2] = r13;
        this.color4 = r14;
        r0[3] = r14;
        if (r12 != 0) goto L17;
        Paint r122 = this.paint;
        this.shader = null;
        r122.setShader(null);
        this.paint.setColor(r11);
    L44:
        updateBounds();
        return;
    L17:
        if (r13 != 0) goto L29;
        if (this.isDiagonal == true) goto L21;
    L23:
        Paint r132 = this.paint;
        if (this.isDiagonal == false) goto L26;
        float r2 = 80.0f;
    L27:
        LinearGradient r1 = new LinearGradient(r2, 0.0f, 0.0f, 80.0f, new int[]{r11, r12}, null, Shader.TileMode.CLAMP);
        this.shader = r1;
        r132.setShader(r1);
        goto L44
    L26:
        r2 = 0.0f;
        goto L27
    L21:
        if (this.isRotate == false) goto L23;
        Paint r133 = this.paint;
        LinearGradient r02 = new LinearGradient(0.0f, 0.0f, 80.0f, 80.0f, new int[]{r11, r12}, null, Shader.TileMode.CLAMP);
        this.shader = r02;
        r133.setShader(r02);
        goto L44
    L29:
        if (this.isLinear == false) goto L41;
        if (this.isDiagonal == true) goto L33;
    L35:
        Paint r15 = this.paint;
        if (this.isDiagonal == false) goto L38;
        float r3 = 80.0f;
    L39:
        LinearGradient r22 = new LinearGradient(r3, 0.0f, 0.0f, 80.0f, new int[]{r11, r12, r13}, null, Shader.TileMode.CLAMP);
        this.shader = r22;
        r15.setShader(r22);
        goto L44
    L38:
        r3 = 0.0f;
        goto L39
    L33:
        if (this.isRotate == false) goto L35;
        Paint r142 = this.paint;
        LinearGradient r03 = new LinearGradient(0.0f, 0.0f, 80.0f, 80.0f, new int[]{r11, r12, r13}, null, Shader.TileMode.CLAMP);
        this.shader = r03;
        r142.setShader(r03);
        goto L44
    L41:
        if (this.gradientBitmap != null) goto L43;
        this.gradientBitmap = Bitmap.createBitmap(60, 80, Bitmap.Config.ARGB_8888);
    L43:
        Utilities.generateGradient(this.gradientBitmap, 0, 0.0f, this.colors);
        Paint r112 = this.paint;
        Bitmap r134 = this.gradientBitmap;
        Shader.TileMode r143 = Shader.TileMode.CLAMP;
        BitmapShader r123 = new BitmapShader(r134, r143, r143);
        this.shader = r123;
        r112.setShader(r123);
        goto L44
    L5:
        if (this.color1 != r11) goto L13;
        if (this.color2 != r12) goto L13;
        if (this.color3 != r13) goto L13;
        if (this.color4 != r14) goto L13;
    }

    public void updateBounds() {
        if (this.shader != null) goto L5;
        return;
    L5:
        float r0 = this.bounds.width() / 60.0f;
        float r1 = this.bounds.height() / 80.0f;
        this.matrix.reset();
        Matrix r2 = this.matrix;
        RectF r3 = this.bounds;
        r2.postTranslate(r3.left, r3.top);
        this.matrix.preScale(r0, r1);
        this.shader.setLocalMatrix(this.matrix);
    }
}
