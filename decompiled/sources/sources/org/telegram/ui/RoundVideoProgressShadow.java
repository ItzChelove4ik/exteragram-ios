package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import androidx.core.graphics.ColorUtils;

/* loaded from: classes6.dex */
public class RoundVideoProgressShadow {
    int lastSizesHash;
    Matrix matrix;
    RadialGradient radialGradient;
    Paint shaderPaint;

    public RoundVideoProgressShadow() {
        this.radialGradient = new RadialGradient(0.0f, 0.0f, 100.0f, new int[]{0, 0, ColorUtils.setAlphaComponent(-16777216, 40)}, new float[]{0.0f, 0.7f, 1.0f}, Shader.TileMode.CLAMP);
        this.shaderPaint = new Paint();
        this.matrix = new Matrix();
        this.shaderPaint.setShader(this.radialGradient);
    }

    public void draw(Canvas r3, float r4, float r5, float r6, float r7) {
        if (((((int) r4) + (((int) r5) << 12)) + (((int) r6) << 24)) == this.lastSizesHash) goto L5;
        this.matrix.reset();
        float r0 = r6 / 100.0f;
        this.matrix.setTranslate(r4, r5);
        this.matrix.preScale(r0, r0);
        this.radialGradient.setLocalMatrix(this.matrix);
    L5:
        this.shaderPaint.setAlpha((int) (r7 * 255.0f));
        r3.drawCircle(r4, r5, r6, this.shaderPaint);
    }
}
