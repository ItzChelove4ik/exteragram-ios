package com.google.android.material.transition;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.transition.MaterialContainerTransform;

/* loaded from: classes5.dex */
class MaskEvaluator {
    private ShapeAppearanceModel currentShapeAppearanceModel;
    private final Path endPath;
    private final Path path;
    private final ShapeAppearancePathProvider pathProvider;
    private final Path startPath;

    public MaskEvaluator() {
        this.path = new Path();
        this.startPath = new Path();
        this.endPath = new Path();
        this.pathProvider = ShapeAppearancePathProvider.getInstance();
    }

    public void clip(Canvas r1) {
        r1.clipPath(this.path);
    }

    public void evaluate(float r8, ShapeAppearanceModel r9, ShapeAppearanceModel r10, RectF r11, RectF r12, RectF r13, MaterialContainerTransform.ProgressThresholds r14) {
        ShapeAppearanceModel r82 = TransitionUtils.lerp(r9, r10, r11, r13, r14.getStart(), r14.getEnd(), r8);
        this.currentShapeAppearanceModel = r82;
        this.pathProvider.calculatePath(r82, 1.0f, r12, this.startPath);
        this.pathProvider.calculatePath(this.currentShapeAppearanceModel, 1.0f, r13, this.endPath);
        this.path.op(this.startPath, this.endPath, Path.Op.UNION);
    }

    public ShapeAppearanceModel getCurrentShapeAppearanceModel() {
        return this.currentShapeAppearanceModel;
    }

    public Path getPath() {
        return this.path;
    }
}
