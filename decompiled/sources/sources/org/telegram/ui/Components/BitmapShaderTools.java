package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class BitmapShaderTools {
    final Bitmap bitmap;
    final RectF bounds;
    final Canvas canvas;
    final Matrix matrix;
    public final Paint paint;
    final Shader shader;

    public BitmapShaderTools() {
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.bounds = new RectF();
        this.matrix = new Matrix();
        Bitmap r1 = Bitmap.createBitmap(30, 40, Bitmap.Config.ARGB_8888);
        this.bitmap = r1;
        this.canvas = new Canvas(r1);
        Shader.TileMode r3 = Shader.TileMode.CLAMP;
        BitmapShader r2 = new BitmapShader(r1, r3, r3);
        this.shader = r2;
        r0.setShader(r2);
        updateBounds();
    }

    public BitmapShaderTools(int r4, int r5) {
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.bounds = new RectF();
        this.matrix = new Matrix();
        Bitmap r42 = Bitmap.createBitmap(Math.max(1, r4), Math.max(1, r5), Bitmap.Config.ARGB_8888);
        this.bitmap = r42;
        this.canvas = new Canvas(r42);
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        BitmapShader r52 = new BitmapShader(r42, r1, r1);
        this.shader = r52;
        r0.setShader(r52);
        updateBounds();
    }

    private void updateBounds() {
        if (this.shader != null) goto L5;
        return;
    L5:
        float r0 = this.bounds.width() / this.bitmap.getWidth();
        float r1 = this.bounds.height() / this.bitmap.getHeight();
        this.matrix.reset();
        Matrix r2 = this.matrix;
        RectF r3 = this.bounds;
        r2.postTranslate(r3.left, r3.top);
        this.matrix.preScale(r0, r1);
        this.shader.setLocalMatrix(this.matrix);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Canvas getCanvas() {
        return this.canvas;
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

    public void setMatrix(float r5, float r6, float r7, float r8) {
        this.matrix.reset();
        this.matrix.postRotate(r8, this.bitmap.getWidth() / 2.0f, this.bitmap.getHeight() / 2.0f);
        this.matrix.postScale(r7, r7);
        this.matrix.postTranslate(r5, r6);
        this.shader.setLocalMatrix(this.matrix);
    }
}
