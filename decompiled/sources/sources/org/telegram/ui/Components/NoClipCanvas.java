package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;
import android.os.Build;

/* loaded from: classes3.dex */
public class NoClipCanvas extends Canvas {
    public Canvas canvas;
    public boolean disableReject;

    public NoClipCanvas() {
    }

    @Override // android.graphics.Canvas
    public boolean clipOutPath(Path r3) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return false;
    L5:
        return this.canvas.clipOutPath(r3);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(float r3, float r4, float r5, float r6) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return false;
    L5:
        return this.canvas.clipOutRect(r3, r4, r5, r6);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(int r3, int r4, int r5, int r6) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return false;
    L5:
        return this.canvas.clipOutRect(r3, r4, r5, r6);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(Rect r3) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return false;
    L5:
        return this.canvas.clipOutRect(r3);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(RectF r3) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return false;
    L5:
        return this.canvas.clipOutRect(r3);
    }

    @Override // android.graphics.Canvas
    public boolean clipPath(Path r1) {
        return this.canvas.clipPath(r1);
    }

    @Override // android.graphics.Canvas
    public boolean clipPath(Path r1, Region.Op r2) {
        return this.canvas.clipPath(r1, r2);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float r1, float r2, float r3, float r4) {
        return false;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float r1, float r2, float r3, float r4, Region.Op r5) {
        return false;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(int r1, int r2, int r3, int r4) {
        return false;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect r1) {
        return false;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect r1, Region.Op r2) {
        return false;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF r1) {
        return false;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF r1, Region.Op r2) {
        return false;
    }

    @Override // android.graphics.Canvas
    public void concat(Matrix r1) {
        this.canvas.concat(r1);
    }

    @Override // android.graphics.Canvas
    public void disableZ() {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.disableZ();
        return;
    }

    @Override // android.graphics.Canvas
    public void drawARGB(int r1, int r2, int r3, int r4) {
        this.canvas.drawARGB(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawArc(float r1, float r2, float r3, float r4, float r5, float r6, boolean r7, Paint r8) {
        this.canvas.drawArc(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    @Override // android.graphics.Canvas
    public void drawArc(RectF r1, float r2, float r3, boolean r4, Paint r5) {
        this.canvas.drawArc(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r1, float r2, float r3, Paint r4) {
        this.canvas.drawBitmap(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r1, Matrix r2, Paint r3) {
        this.canvas.drawBitmap(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r1, Rect r2, Rect r3, Paint r4) {
        this.canvas.drawBitmap(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r1, Rect r2, RectF r3, Paint r4) {
        this.canvas.drawBitmap(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(int[] r1, int r2, int r3, float r4, float r5, int r6, int r7, boolean r8, Paint r9) {
        this.canvas.drawBitmap(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(int[] r1, int r2, int r3, int r4, int r5, int r6, int r7, boolean r8, Paint r9) {
        this.canvas.drawBitmap(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.graphics.Canvas
    public void drawBitmapMesh(Bitmap r1, int r2, int r3, float[] r4, int r5, int[] r6, int r7, Paint r8) {
        this.canvas.drawBitmapMesh(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    @Override // android.graphics.Canvas
    public void drawCircle(float r1, float r2, float r3, Paint r4) {
        this.canvas.drawCircle(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int r1) {
        this.canvas.drawColor(r1);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int r3, BlendMode r4) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.drawColor(r3, r4);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawColor(int r1, PorterDuff.Mode r2) {
        this.canvas.drawColor(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawColor(long r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.drawColor(r3);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawColor(long r3, BlendMode r5) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.drawColor(r3, r5);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawDoubleRoundRect(RectF r3, float r4, float r5, RectF r6, float r7, float r8, Paint r9) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.drawDoubleRoundRect(r3, r4, r5, r6, r7, r8, r9);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawDoubleRoundRect(RectF r3, float[] r4, RectF r5, float[] r6, Paint r7) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.drawDoubleRoundRect(r3, r4, r5, r6, r7);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawGlyphs(int[] r1, int r2, float[] r3, int r4, int r5, Font r6, Paint r7) {
        this.canvas.drawGlyphs(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // android.graphics.Canvas
    public void drawLine(float r1, float r2, float r3, float r4, Paint r5) {
        this.canvas.drawLine(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] r1, int r2, int r3, Paint r4) {
        this.canvas.drawLines(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] r1, Paint r2) {
        this.canvas.drawLines(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawOval(float r1, float r2, float r3, float r4, Paint r5) {
        this.canvas.drawOval(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawOval(RectF r1, Paint r2) {
        this.canvas.drawOval(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawPaint(Paint r1) {
        this.canvas.drawPaint(r1);
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch r3, Rect r4, Paint r5) {
        if (Build.VERSION.SDK_INT < 31) goto L6;
        this.canvas.drawPatch(r3, r4, r5);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch r3, RectF r4, Paint r5) {
        if (Build.VERSION.SDK_INT < 31) goto L6;
        this.canvas.drawPatch(r3, r4, r5);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawPath(Path r1, Paint r2) {
        this.canvas.drawPath(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture r1) {
        this.canvas.drawPicture(r1);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture r1, Rect r2) {
        this.canvas.drawPicture(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture r1, RectF r2) {
        this.canvas.drawPicture(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawPoint(float r1, float r2, Paint r3) {
        this.canvas.drawPoint(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] r1, int r2, int r3, Paint r4) {
        this.canvas.drawPoints(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] r1, Paint r2) {
        this.canvas.drawPoints(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawPosText(String r1, float[] r2, Paint r3) {
        this.canvas.drawPosText(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawPosText(char[] r1, int r2, int r3, float[] r4, Paint r5) {
        this.canvas.drawPosText(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawRGB(int r1, int r2, int r3) {
        this.canvas.drawRGB(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawRect(float r1, float r2, float r3, float r4, Paint r5) {
        this.canvas.drawRect(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawRect(Rect r1, Paint r2) {
        this.canvas.drawRect(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawRect(RectF r1, Paint r2) {
        this.canvas.drawRect(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawRenderNode(RenderNode r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.drawRenderNode(r3);
        return;
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(float r1, float r2, float r3, float r4, float r5, float r6, Paint r7) {
        this.canvas.drawRoundRect(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(RectF r1, float r2, float r3, Paint r4) {
        this.canvas.drawRoundRect(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawText(CharSequence r1, int r2, int r3, float r4, float r5, Paint r6) {
        this.canvas.drawText(r1, r2, r3, r4, r5, r6);
    }

    @Override // android.graphics.Canvas
    public void drawText(String r1, float r2, float r3, Paint r4) {
        this.canvas.drawText(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawText(String r1, int r2, int r3, float r4, float r5, Paint r6) {
        this.canvas.drawText(r1, r2, r3, r4, r5, r6);
    }

    @Override // android.graphics.Canvas
    public void drawText(char[] r1, int r2, int r3, float r4, float r5, Paint r6) {
        this.canvas.drawText(r1, r2, r3, r4, r5, r6);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(String r1, Path r2, float r3, float r4, Paint r5) {
        this.canvas.drawTextOnPath(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(char[] r1, int r2, int r3, Path r4, float r5, float r6, Paint r7) {
        this.canvas.drawTextOnPath(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(MeasuredText r1, int r2, int r3, int r4, int r5, float r6, float r7, boolean r8, Paint r9) {
        this.canvas.drawTextRun(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(CharSequence r1, int r2, int r3, int r4, int r5, float r6, float r7, boolean r8, Paint r9) {
        this.canvas.drawTextRun(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(char[] r1, int r2, int r3, int r4, int r5, float r6, float r7, boolean r8, Paint r9) {
        this.canvas.drawTextRun(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.graphics.Canvas
    public void drawVertices(Canvas.VertexMode r1, int r2, float[] r3, int r4, float[] r5, int r6, int[] r7, int r8, short[] r9, int r10, int r11, Paint r12) {
        this.canvas.drawVertices(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.graphics.Canvas
    public void enableZ() {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.canvas.enableZ();
        return;
    }

    @Override // android.graphics.Canvas
    public boolean getClipBounds(Rect r1) {
        return this.canvas.getClipBounds(r1);
    }

    @Override // android.graphics.Canvas
    public int getDensity() {
        return this.canvas.getDensity();
    }

    @Override // android.graphics.Canvas
    public DrawFilter getDrawFilter() {
        return this.canvas.getDrawFilter();
    }

    @Override // android.graphics.Canvas
    public int getHeight() {
        return this.canvas.getHeight();
    }

    @Override // android.graphics.Canvas
    public void getMatrix(Matrix r1) {
        this.canvas.getMatrix(r1);
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapHeight() {
        return this.canvas.getMaximumBitmapHeight();
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapWidth() {
        return this.canvas.getMaximumBitmapWidth();
    }

    @Override // android.graphics.Canvas
    public int getSaveCount() {
        return this.canvas.getSaveCount();
    }

    @Override // android.graphics.Canvas
    public int getWidth() {
        return this.canvas.getWidth();
    }

    @Override // android.graphics.Canvas
    public boolean isOpaque() {
        return this.canvas.isOpaque();
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(float r4, float r5, float r6, float r7) {
        if (this.disableReject == false) goto L6;
        return false;
    L6:
        if (Build.VERSION.SDK_INT >= 30) goto L8;
        return false;
    L8:
        return this.canvas.quickReject(r4, r5, r6, r7);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(float r7, float r8, float r9, float r10, Canvas.EdgeType r11) {
        if (this.disableReject == false) goto L7;
        return false;
    L7:
        return this.canvas.quickReject(r7, r8, r9, r10, r11);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(Path r4) {
        if (this.disableReject == false) goto L6;
        return false;
    L6:
        if (Build.VERSION.SDK_INT >= 30) goto L8;
        return false;
    L8:
        return this.canvas.quickReject(r4);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(Path r2, Canvas.EdgeType r3) {
        if (this.disableReject == false) goto L7;
        return false;
    L7:
        return this.canvas.quickReject(r2, r3);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(RectF r4) {
        if (this.disableReject == false) goto L6;
        return false;
    L6:
        if (Build.VERSION.SDK_INT >= 30) goto L8;
        return false;
    L8:
        return this.canvas.quickReject(r4);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(RectF r2, Canvas.EdgeType r3) {
        if (this.disableReject == false) goto L7;
        return false;
    L7:
        return this.canvas.quickReject(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void restore() {
        this.canvas.restore();
    }

    @Override // android.graphics.Canvas
    public void restoreToCount(int r1) {
        this.canvas.restoreToCount(r1);
    }

    @Override // android.graphics.Canvas
    public void rotate(float r1) {
        this.canvas.rotate(r1);
    }

    @Override // android.graphics.Canvas
    public int save() {
        return this.canvas.save();
    }

    @Override // android.graphics.Canvas
    public int saveLayer(float r1, float r2, float r3, float r4, Paint r5) {
        return this.canvas.saveLayer(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(float r1, float r2, float r3, float r4, Paint r5, int r6) {
        return this.canvas.saveLayer(r1, r2, r3, r4, r5, r6);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(RectF r1, Paint r2) {
        return this.canvas.saveLayer(r1, r2);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(RectF r1, Paint r2, int r3) {
        return this.canvas.saveLayer(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(float r1, float r2, float r3, float r4, int r5) {
        return this.canvas.saveLayerAlpha(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(float r1, float r2, float r3, float r4, int r5, int r6) {
        return this.canvas.saveLayerAlpha(r1, r2, r3, r4, r5, r6);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(RectF r1, int r2) {
        return this.canvas.saveLayerAlpha(r1, r2);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(RectF r1, int r2, int r3) {
        return this.canvas.saveLayerAlpha(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public void scale(float r1, float r2) {
        this.canvas.scale(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void setBitmap(Bitmap r1) {
        this.canvas.setBitmap(r1);
    }

    @Override // android.graphics.Canvas
    public void setDensity(int r1) {
        this.canvas.setDensity(r1);
    }

    @Override // android.graphics.Canvas
    public void setDrawFilter(DrawFilter r1) {
        this.canvas.setDrawFilter(r1);
    }

    @Override // android.graphics.Canvas
    public void setMatrix(Matrix r1) {
        this.canvas.setMatrix(r1);
    }

    @Override // android.graphics.Canvas
    public void skew(float r1, float r2) {
        this.canvas.skew(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void translate(float r1, float r2) {
        this.canvas.translate(r1, r2);
    }
}
