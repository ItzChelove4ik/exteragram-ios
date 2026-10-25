package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import androidx.annotation.Keep;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes7.dex */
public class ClippingImageView extends View {
    private static float[] radii;
    private float additionalTranslationX;
    private float additionalTranslationY;
    private float animationProgress;
    private float[][] animationValues;
    private RectF bitmapRect;
    private BitmapShader bitmapShader;
    private ImageReceiver.BitmapHolder bmp;
    private int clipBottom;
    private int clipLeft;
    private int clipRight;
    private int clipTop;
    private RectF drawRect;
    private boolean fade;
    private int imageX;
    private int imageY;
    private boolean in;
    private int invert;
    private Matrix matrix;
    private boolean needRadius;
    private int orientation;
    private Paint paint;
    private int[] radius;
    private Paint roundPaint;
    private Path roundPath;
    private RectF roundRect;
    private Matrix shaderMatrix;

    static {
        radii = new float[8];
    }

    public ClippingImageView(Context r2) {
        super(r2);
        this.radius = new int[4];
        this.roundPath = new Path();
        Paint r22 = new Paint(2);
        this.paint = r22;
        r22.setFilterBitmap(true);
        this.matrix = new Matrix();
        this.drawRect = new RectF();
        this.bitmapRect = new RectF();
        this.roundPaint = new Paint(3);
        this.roundRect = new RectF();
        this.shaderMatrix = new Matrix();
    }

    @Keep
    public float getAnimationProgress() {
        return this.animationProgress;
    }

    public Bitmap getBitmap() {
        ImageReceiver.BitmapHolder r0 = this.bmp;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.bitmap;
    }

    public ImageReceiver.BitmapHolder getBitmapHolder() {
        return this.bmp;
    }

    public float getCenterX() {
        float r0 = getScaleY();
        return getTranslationX() + ((((this.clipLeft / r0) + (getWidth() - (this.clipRight / r0))) / 2.0f) * getScaleX());
    }

    public float getCenterY() {
        float r0 = getScaleY();
        return getTranslationY() + ((((this.clipTop / r0) + (getHeight() - (this.clipBottom / r0))) / 2.0f) * getScaleY());
    }

    public int getClipBottom() {
        return this.clipBottom;
    }

    public int getClipHorizontal() {
        return this.clipRight;
    }

    public int getClipLeft() {
        return this.clipLeft;
    }

    public int getClipRight() {
        return this.clipRight;
    }

    public int getClipTop() {
        return this.clipTop;
    }

    public void getClippedVisibleRect(RectF r4) {
        r4.left = getTranslationX();
        r4.top = getTranslationY();
        r4.right = r4.left + (getMeasuredWidth() * getScaleX());
        float r0 = r4.top + (getMeasuredHeight() * getScaleY());
        r4.left += this.clipLeft;
        r4.top += this.clipTop;
        r4.right -= this.clipRight;
        r4.bottom = r0 - this.clipBottom;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public int[] getRadius() {
        return this.radius;
    }

    @Override // android.view.View
    public float getTranslationY() {
        return super.getTranslationY() - this.additionalTranslationY;
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        if (getVisibility() != 0) goto L53;
        ImageReceiver.BitmapHolder r0 = this.bmp;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isRecycled() == true) goto L52;
        float r02 = getScaleY();
        r13.save();
        if (this.needRadius == false) goto L16;
        this.shaderMatrix.reset();
        this.roundRect.set(this.imageX / r02, this.imageY / r02, getWidth() - (this.imageX / r02), getHeight() - (this.imageY / r02));
        this.bitmapRect.set(0.0f, 0.0f, this.bmp.getWidth(), this.bmp.getHeight());
        AndroidUtilities.setRectToRect(this.shaderMatrix, this.bitmapRect, this.roundRect, this.orientation, this.invert, false);
        this.bitmapShader.setLocalMatrix(this.shaderMatrix);
        r13.clipRect(this.clipLeft / r02, this.clipTop / r02, getWidth() - (this.clipRight / r02), getHeight() - (this.clipBottom / r02));
        int r03 = 0;
    L12:
        int[] r1 = this.radius;
        if (r03 >= r1.length) goto L15;
        float[] r3 = radii;
        int r4 = r03 * 2;
        int r12 = r1[r03];
        r3[r4] = r12;
        r3[r4 + 1] = r12;
        r03 = r03 + 1;
        goto L12
    L15:
        this.roundPath.reset();
        this.roundPath.addRoundRect(this.roundRect, radii, Path.Direction.CW);
        this.roundPath.close();
        r13.drawPath(this.roundPath, this.roundPaint);
    L46:
        r13.restore();
        return;
    L16:
        int r14 = this.orientation;
        if (r14 != 90) goto L19;
    L35:
        this.drawRect.set((-getHeight()) / 2, (-getWidth()) / 2, getHeight() / 2, getWidth() / 2);
        this.matrix.setRectToRect(this.bitmapRect, this.drawRect, Matrix.ScaleToFit.FILL);
        int r15 = this.invert;
        if (r15 != 1) goto L38;
        this.matrix.postScale(-1.0f, 1.0f);
    L40:
        this.matrix.postRotate(this.orientation, 0.0f, 0.0f);
        this.matrix.postTranslate(getWidth() / 2, getHeight() / 2);
    L41:
        r13.clipRect(this.clipLeft / r02, this.clipTop / r02, getWidth() - (this.clipRight / r02), getHeight() - (this.clipBottom / r02));
        r13.drawBitmap(this.bmp.bitmap, this.matrix, this.paint);     // Catch: Exception -> L44
    L44:
        e = move-exception;
        FileLog.e(e);
        goto L46
    L38:
        if (r15 != 2) goto L40;
        this.matrix.postScale(1.0f, -1.0f);
        goto L40
    L19:
        if (r14 == 270) goto L35;
        RectF r42 = this.drawRect;
        if (r14 != 180) goto L29;
        r42.set((-getWidth()) / 2, (-getHeight()) / 2, getWidth() / 2, getHeight() / 2);
        this.matrix.setRectToRect(this.bitmapRect, this.drawRect, Matrix.ScaleToFit.FILL);
        int r16 = this.invert;
        if (r16 != 1) goto L26;
        this.matrix.postScale(-1.0f, 1.0f);
    L28:
        this.matrix.postRotate(this.orientation, 0.0f, 0.0f);
        this.matrix.postTranslate(getWidth() / 2, getHeight() / 2);
        goto L41
    L26:
        if (r16 != 2) goto L28;
        this.matrix.postScale(1.0f, -1.0f);
        goto L28
    L29:
        r42.set(0.0f, 0.0f, getWidth(), getHeight());
        int r17 = this.invert;
        if (r17 != 1) goto L32;
        this.matrix.postScale(-1.0f, 1.0f, getWidth() / 2, getHeight() / 2);
    L34:
        this.matrix.setRectToRect(this.bitmapRect, this.drawRect, Matrix.ScaleToFit.FILL);
        goto L41
    L32:
        if (r17 != 2) goto L34;
        this.matrix.postScale(1.0f, -1.0f, getWidth() / 2, getHeight() / 2);
        goto L34
    L52:
        return;
    }

    public void setAdditionalTranslationX(float r1) {
        this.additionalTranslationX = r1;
    }

    public void setAdditionalTranslationY(float r1) {
        this.additionalTranslationY = r1;
    }

    @Keep
    public void setAnimationProgress(float r8) {
        this.animationProgress = r8;
        float[][] r0 = this.animationValues;
        float r2 = r0[0][0];
        setScaleX(r2 + ((r0[1][0] - r2) * r8));
        float[][] r02 = this.animationValues;
        float r22 = r02[0][1];
        setScaleY(r22 + ((r02[1][1] - r22) * this.animationProgress));
        float[][] r03 = this.animationValues;
        float r23 = r03[0][2];
        float r5 = this.additionalTranslationX;
        setTranslationX((r23 + r5) + ((((r03[1][2] + r5) - r23) - r5) * this.animationProgress));
        float[][] r04 = this.animationValues;
        float r24 = r04[0][3];
        setTranslationY(r24 + ((r04[1][3] - r24) * this.animationProgress));
        float[][] r05 = this.animationValues;
        float r25 = r05[0][4];
        setClipHorizontal((int) (r25 + ((r05[1][4] - r25) * this.animationProgress)));
        float[][] r06 = this.animationValues;
        float r26 = r06[0][5];
        setClipTop((int) (r26 + ((r06[1][5] - r26) * this.animationProgress)));
        float[][] r07 = this.animationValues;
        float r27 = r07[0][6];
        setClipBottom((int) (r27 + ((r07[1][6] - r27) * this.animationProgress)));
        int r08 = 0;
    L3:
        int[] r28 = this.radius;
        int r4 = r28.length;
        float[][] r52 = this.animationValues;
        if (r08 >= r4) goto L6;
        int r6 = r08 + 7;
        float r42 = r52[0][r6];
        r28[r08] = (int) (r42 + ((r52[1][r6] - r42) * this.animationProgress));
        setRadius(r28);
        r08 = r08 + 1;
        goto L3
    L6:
        float[] r09 = r52[0];
        if (r09.length <= 11) goto L10;
        float r010 = r09[11];
        setImageY((int) (r010 + ((r52[1][11] - r010) * this.animationProgress)));
        float[][] r011 = this.animationValues;
        float r1 = r011[0][12];
        setImageX((int) (r1 + ((r011[1][12] - r1) * this.animationProgress)));
    L10:
        if (this.fade == true) goto L12;
    L16:
        invalidate();
        return;
    L12:
        if (this.in == true) goto L15;
        r8 = 1.0f - r8;
    L15:
        setAlpha(r8);
        goto L16
    }

    public void setAnimationValues(float[][] r1, boolean r2, boolean r3) {
        this.animationValues = r1;
        this.in = r2;
        this.fade = r3;
    }

    public void setClipBottom(int r1) {
        this.clipBottom = r1;
        invalidate();
    }

    public void setClipHorizontal(int r1) {
        this.clipRight = r1;
        this.clipLeft = r1;
        invalidate();
    }

    public void setClipLeft(int r1) {
        this.clipLeft = r1;
        invalidate();
    }

    public void setClipRight(int r1) {
        this.clipRight = r1;
        invalidate();
    }

    public void setClipTop(int r1) {
        this.clipTop = r1;
        invalidate();
    }

    public void setClipVertical(int r1) {
        this.clipBottom = r1;
        this.clipTop = r1;
        invalidate();
    }

    public void setImageBitmap(ImageReceiver.BitmapHolder r6) {
        ImageReceiver.BitmapHolder r0 = this.bmp;
        if (r0 == null) goto L5;
        r0.release();
        this.bitmapShader = null;
    L5:
        if (r6 != null) goto L7;
    L9:
        this.bmp = r6;
        if (r6 != null) goto L12;
    L14:
        if (r6 != null) goto L18;
        this.bitmapShader = null;
        Paint r62 = this.roundPaint;
        if (r62 == null) goto L18;
        r62.setShader(null);
    L18:
        invalidate();
        return;
    L12:
        if (r6.bitmap == null) goto L14;
        this.bitmapRect.set(0.0f, 0.0f, r6.getWidth(), r6.getHeight());
        Bitmap r2 = this.bmp.bitmap;
        Shader.TileMode r3 = Shader.TileMode.CLAMP;
        BitmapShader r02 = new BitmapShader(r2, r3, r3);
        this.bitmapShader = r02;
        this.roundPaint.setShader(r02);
        goto L14
    L7:
        if (r6.isRecycled() == false) goto L9;
        r6 = null;
        goto L9
    }

    public void setImageX(int r1) {
        this.imageX = r1;
    }

    public void setImageY(int r1) {
        this.imageY = r1;
    }

    public void setOrientation(int r1) {
        this.orientation = r1;
        this.invert = 0;
    }

    public void setOrientation(int r1, int r2) {
        this.orientation = r1;
        this.invert = r2;
    }

    public void setRadius(int[] r4) {
        int r0 = 0;
        if (r4 != null) goto L6;
        this.needRadius = false;
        Arrays.fill(this.radius, 0);
        return;
    L6:
        System.arraycopy(r4, 0, this.radius, 0, r4.length);
        this.needRadius = false;
    L8:
        if (r0 >= r4.length) goto L14;
        if (r4[r0] != 0) goto L11;
        r0 = r0 + 1;
        goto L8
    L11:
        this.needRadius = true;
        return;
    }

    @Override // android.view.View
    public void setTranslationY(float r2) {
        super.setTranslationY(r2 + this.additionalTranslationY);
    }
}
