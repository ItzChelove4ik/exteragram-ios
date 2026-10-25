package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import androidx.core.graphics.ColorUtils;

/* loaded from: classes3.dex */
public class DialogsActivityTopBubblesFadeView extends View {
    private float fadeHeight;
    private float fadeStart;
    private int lastColor;
    private final Matrix matrix;
    private final Paint paint;
    private Shader shader;

    public DialogsActivityTopBubblesFadeView(Context r2) {
        super(r2);
        this.paint = new Paint(1);
        this.matrix = new Matrix();
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), this.fadeStart + this.fadeHeight, this.paint);
    }

    public void setColor(int r10) {
        if (this.lastColor == r10) goto L6;
        this.lastColor = r10;
        int r0 = Color.alpha(r10);
        LinearGradient r1 = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, new int[]{ColorUtils.setAlphaComponent(r10, (r0 * 232) / 255), ColorUtils.setAlphaComponent(r10, (r0 * 192) / 255), ColorUtils.setAlphaComponent(r10, (r0 * 144) / 255), ColorUtils.setAlphaComponent(r10, 0)}, null, Shader.TileMode.CLAMP);
        this.shader = r1;
        this.paint.setShader(r1);
        this.shader.setLocalMatrix(this.matrix);
        invalidate();
        return;
    }

    public void setPosition(float r3, float r4) {
        if (this.fadeStart == r3) goto L5;
    L8:
        this.fadeStart = r3;
        this.fadeHeight = r4;
        this.matrix.reset();
        this.matrix.setScale(1.0f, r4);
        this.matrix.postTranslate(0.0f, r3);
        Shader r32 = this.shader;
        if (r32 == null) goto L11;
        r32.setLocalMatrix(this.matrix);
    L11:
        invalidate();
        return;
    L5:
        if (this.fadeHeight != r4) goto L8;
    }
}
