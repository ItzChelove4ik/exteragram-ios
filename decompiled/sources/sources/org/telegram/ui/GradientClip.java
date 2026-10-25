package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import org.mvel2.asm.Opcodes;

/* loaded from: classes6.dex */
public class GradientClip {
    private LinearGradient gradient;
    private final Matrix matrix;
    private final Paint[] paint;

    public GradientClip() {
        this.paint = new Paint[4];
        this.matrix = new Matrix();
    }

    public void clipOut(Canvas r5, RectF r6, float r7) {
        Paint[] r0 = this.paint;
        if (r0[0] != null) goto L5;
        r0[0] = new Paint(1);
        this.paint[0].setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L5:
        this.paint[0].setShader(this.gradient);
        this.paint[0].setAlpha((int) (r7 * 255.0f));
        r5.drawRect(r6, this.paint[0]);
    }

    public void draw(Canvas r11, RectF r12, int r13, float r14) {
        if (r14 > 0.0f) goto L6;
        return;
    L6:
        if (this.gradient != null) goto L8;
        this.gradient = new LinearGradient(0.0f, 0.0f, 0.0f, 16.0f, new int[]{Opcodes.V_PREVIEW, 16711680}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
    L8:
        Paint[] r0 = this.paint;
        if (r0[r13] != null) goto L11;
        r0[r13] = new Paint(1);
        this.paint[r13].setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L11:
        this.paint[r13].setShader(this.gradient);
        this.matrix.reset();
        if (r13 != 0) goto L14;
        this.matrix.postScale(1.0f, r12.width() / 16.0f);
        this.matrix.postRotate(-90.0f);
        this.matrix.postTranslate(r12.left, r12.top);
    L21:
        this.gradient.setLocalMatrix(this.matrix);
        this.paint[r13].setAlpha((int) (r14 * 255.0f));
        r11.drawRect(r12, this.paint[r13]);
        return;
    L14:
        if (r13 != 1) goto L16;
        this.matrix.postScale(1.0f, r12.height() / 16.0f);
        this.matrix.postTranslate(r12.left, r12.top);
        goto L21
    L16:
        if (r13 != 2) goto L19;
        this.matrix.postScale(1.0f, r12.width() / 16.0f);
        this.matrix.postRotate(90.0f);
        this.matrix.postTranslate(r12.right, r12.top);
        goto L21
    L19:
        if (r13 != 3) goto L21;
        this.matrix.postScale(1.0f, r12.height() / 16.0f);
        this.matrix.postScale(1.0f, -1.0f);
        this.matrix.postTranslate(r12.left, r12.bottom);
        goto L21
    }

    public void draw(Canvas r1, RectF r2, boolean r3, float r4) {
        if (r3 == false) goto L4;
        int r32 = 1;
    L5:
        draw(r1, r2, r32, r4);
        return;
    L4:
        r32 = 3;
        goto L5
    }

    public Paint getPaint(int r4, float r5) {
        Paint[] r0 = this.paint;
        if (r0[r4] != null) goto L5;
        r0[r4] = new Paint(1);
        this.paint[r4].setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L5:
        this.paint[r4].setShader(null);
        this.paint[r4].setColor(Opcodes.V_PREVIEW);
        this.paint[r4].setAlpha((int) (r5 * 255.0f));
        return this.paint[r4];
    }
}
