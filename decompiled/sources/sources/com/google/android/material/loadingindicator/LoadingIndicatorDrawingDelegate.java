package com.google.android.material.loadingindicator;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.graphics.shapes.Morph;
import androidx.graphics.shapes.RoundedPolygon;
import androidx.graphics.shapes.Shapes_androidKt;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.math.MathUtils;
import com.google.android.material.shape.MaterialShapes;

/* loaded from: classes5.dex */
class LoadingIndicatorDrawingDelegate {
    private static final Morph[] INDETERMINATE_MORPH_SEQUENCE = null;
    private static final RoundedPolygon[] INDETERMINATE_SHAPES = null;
    final Path indicatorPath;
    final Matrix indicatorPathTransform;
    LoadingIndicatorSpec specs;

    public static class IndicatorState {
        int color;
        float morphFraction;
        float rotationDegree;

        public IndicatorState() {
        }
    }

    static {
        RoundedPolygon[] r0 = {MaterialShapes.normalize(MaterialShapes.SOFT_BURST, true, new RectF(-1.0f, -1.0f, 1.0f, 1.0f)), MaterialShapes.normalize(MaterialShapes.COOKIE_9, true, new RectF(-1.0f, -1.0f, 1.0f, 1.0f)), MaterialShapes.normalize(MaterialShapes.PENTAGON, true, new RectF(-1.0f, -1.0f, 1.0f, 1.0f)), MaterialShapes.normalize(MaterialShapes.PILL, true, new RectF(-1.0f, -1.0f, 1.0f, 1.0f)), MaterialShapes.normalize(MaterialShapes.SUNNY, true, new RectF(-1.0f, -1.0f, 1.0f, 1.0f)), MaterialShapes.normalize(MaterialShapes.COOKIE_4, true, new RectF(-1.0f, -1.0f, 1.0f, 1.0f)), MaterialShapes.normalize(MaterialShapes.OVAL, true, new RectF(-1.0f, -1.0f, 1.0f, 1.0f))};
        INDETERMINATE_SHAPES = r0;
        INDETERMINATE_MORPH_SEQUENCE = new Morph[r0.length];
        int r02 = 0;
    L3:
        RoundedPolygon[] r1 = INDETERMINATE_SHAPES;
        if (r02 >= r1.length) goto L6;
        int r5 = r02 + 1;
        INDETERMINATE_MORPH_SEQUENCE[r02] = new Morph(r1[r02], r1[r5 % r1.length]);
        r02 = r5;
        goto L3
    }

    public LoadingIndicatorDrawingDelegate(LoadingIndicatorSpec r2) {
        this.indicatorPath = new Path();
        this.indicatorPathTransform = new Matrix();
        this.specs = r2;
    }

    public void adjustCanvas(Canvas r4, Rect r5) {
        r4.translate(r5.centerX(), r5.centerY());
        if (this.specs.scaleToFit == false) goto L5;
        float r52 = Math.min(r5.width() / getPreferredWidth(), r5.height() / getPreferredHeight());
        r4.scale(r52, r52);
    L5:
        r4.clipRect((-getPreferredWidth()) / 2.0f, (-getPreferredHeight()) / 2.0f, getPreferredWidth() / 2.0f, getPreferredHeight() / 2.0f);
        r4.rotate(-90.0f);
    }

    public void drawContainer(Canvas r5, Paint r6, int r7, int r8) {
        LoadingIndicatorSpec r0 = this.specs;
        float r02 = Math.min(r0.containerWidth, r0.containerHeight) / 2.0f;
        r6.setColor(MaterialColors.compositeARGBWithAlpha(r7, r8));
        r6.setStyle(Paint.Style.FILL);
        LoadingIndicatorSpec r4 = this.specs;
        r5.drawRoundRect(new RectF((-r8) / 2.0f, (-r4) / 2.0f, r4.containerWidth / 2.0f, r4.containerHeight / 2.0f), r02, r02, r6);
    }

    public void drawIndicator(Canvas r3, Paint r4, IndicatorState r5, int r6) {
        r4.setColor(MaterialColors.compositeARGBWithAlpha(r5.color, r6));
        r4.setStyle(Paint.Style.FILL);
        r3.save();
        r3.rotate(r5.rotationDegree);
        this.indicatorPath.rewind();
        int r62 = (int) Math.floor(r5.morphFraction);
        Morph[] r0 = INDETERMINATE_MORPH_SEQUENCE;
        int r1 = MathUtils.floorMod(r62, r0.length);
        Shapes_androidKt.toPath(r0[r1], r5.morphFraction - r62, this.indicatorPath);
        Matrix r52 = this.indicatorPathTransform;
        int r63 = this.specs.indicatorSize;
        r52.setScale(r63 / 2.0f, r63 / 2.0f);
        this.indicatorPath.transform(this.indicatorPathTransform);
        r3.drawPath(this.indicatorPath, r4);
        r3.restore();
    }

    public int getPreferredHeight() {
        LoadingIndicatorSpec r1 = this.specs;
        return Math.max(r1.containerWidth, r1.indicatorSize);
    }

    public int getPreferredWidth() {
        LoadingIndicatorSpec r1 = this.specs;
        return Math.max(r1.containerHeight, r1.indicatorSize);
    }
}
