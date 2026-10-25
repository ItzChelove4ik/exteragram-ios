package org.telegram.ui.Components.blur3.source;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableSource;

/* loaded from: classes7.dex */
public class BlurredBackgroundSourceBitmap implements BlurredBackgroundSource {
    protected int actionBarHeight;
    private Bitmap bitmap;
    private Bitmap bitmapInternal;
    private final Matrix bitmapMatrix;
    private final Paint bitmapPaint;
    private BitmapShader bitmapShader;
    private final Matrix matrixForDraw;
    protected int parentHeight;
    protected int parentWidth;

    public BlurredBackgroundSourceBitmap() {
        Paint r0 = new Paint(3);
        this.bitmapPaint = r0;
        this.bitmapMatrix = new Matrix();
        this.matrixForDraw = new Matrix();
        r0.setFilterBitmap(true);
    }

    private static void buildCenterCropMatrix(Matrix r2, int r3, int r4, int r5, int r6, int r7) {
        r2.reset();
        int r62 = r6 - r7;
        if (r3 <= 0) goto L10;
        if (r4 <= 0) goto L11;
        if (r5 <= 0) goto L12;
        if (r62 <= 0) goto L13;
        float r52 = r5;
        float r32 = r3;
        float r63 = r62;
        float r42 = r4;
        float r0 = Math.max(r52 / r32, r63 / r42);
        r2.setScale(r0, r0);
        r2.postTranslate((r52 - (r32 * r0)) * 0.5f, ((r63 - (r42 * r0)) * 0.5f) + r7);
        return;
    L13:
        return;
    L12:
        return;
    L11:
        return;
    }

    private void updateMatrix() {
        Bitmap r0 = this.bitmap;
        Matrix r1 = this.bitmapMatrix;
        if (r0 != null) goto L6;
        r1.reset();
        return;
    L6:
        buildCenterCropMatrix(r1, r0.getWidth(), this.bitmap.getHeight(), this.parentWidth, this.parentHeight, this.actionBarHeight);
    }

    public Canvas beginRecording(int r2, int r3) {
        return beginRecording(r2, r3, 1.0f);
    }

    public Canvas beginRecording(int r4, int r5, float r6) {
        float r42 = r4;
        float r62 = r42 / r6;
        int r0 = Math.round(r62);
        int r63 = Math.round(r62);
        Bitmap r1 = this.bitmapInternal;
        if (r1 != null) goto L5;
    L12:
        this.bitmapInternal = Bitmap.createBitmap(r0, r63, Bitmap.Config.ARGB_8888);
    L13:
        Canvas r12 = new Canvas(this.bitmapInternal);
        r12.scale(r42 / r0, r5 / r63);
        return r12;
    L5:
        if (r1.isRecycled() == true) goto L12;
        if (this.bitmapInternal.getWidth() != r63) goto L12;
        if (this.bitmapInternal.getHeight() != r63) goto L12;
        this.bitmapInternal.eraseColor(0);
        goto L13
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public BlurredBackgroundDrawable createDrawable() {
        return new BlurredBackgroundDrawableSource(this);
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public void draw(Canvas r4, float r5, float r6, float r7, float r8) {
        Bitmap r0 = this.bitmap;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isRecycled() == false) goto L7;
        return;
    L7:
        if (this.bitmapShader == null) goto L13;
        this.matrixForDraw.set(this.bitmapMatrix);
        this.matrixForDraw.postTranslate(r5, r6);
        this.bitmapShader.setLocalMatrix(this.bitmapMatrix);
        r4.drawRect(r5, r6, r7, r8, this.bitmapPaint);
        return;
    }

    public void endRecording() {
        setBitmap(this.bitmapInternal);
        this.bitmapInternal = null;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Matrix getMatrix() {
        return this.bitmapMatrix;
    }

    public void setBitmap(Bitmap r3) {
        if (this.bitmap == r3) goto L10;
        this.bitmap = r3;
        this.bitmapPaint.setShader(null);
        this.bitmapShader = null;
        if (r3 == null) goto L9;
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        BitmapShader r0 = new BitmapShader(r3, r1, r1);
        this.bitmapShader = r0;
        this.bitmapPaint.setShader(r0);
        updateMatrix();
        return;
    L9:
        return;
    }

    public void setMatrix(Matrix r1) {
        this.bitmapMatrix.set(r1);
    }

    public final void setParentSize(int r2, int r3, int r4) {
        if (this.parentWidth == r2) goto L5;
    L10:
        this.parentWidth = r2;
        this.parentHeight = r3;
        this.actionBarHeight = r4;
        updateMatrix();
        return;
    L5:
        if (this.parentHeight != r3) goto L10;
        if (this.actionBarHeight != r4) goto L10;
    }
}
