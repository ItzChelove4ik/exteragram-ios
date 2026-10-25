package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class PatternPathMotion extends PathMotion {
    private Path mOriginalPatternPath;
    private final Path mPatternPath;
    private final Matrix mTempMatrix;

    public PatternPathMotion(Path r2) {
        this.mPatternPath = new Path();
        this.mTempMatrix = new Matrix();
        setPatternPath(r2);
    }

    private static float distance(float r0, float r1) {
        return (float) Math.sqrt((r0 * r0) + (r1 * r1));
    }

    @Override // androidx.transition.PathMotion
    public Path getPath(float r4, float r5, float r6, float r7) {
        float r62 = r6 - r4;
        float r72 = r7 - r5;
        float r0 = distance(r62, r72);
        double r63 = Math.atan2(r72, r62);
        this.mTempMatrix.setScale(r0, r0);
        this.mTempMatrix.postRotate((float) Math.toDegrees(r63));
        this.mTempMatrix.postTranslate(r4, r5);
        Path r42 = new Path();
        this.mPatternPath.transform(this.mTempMatrix, r42);
        return r42;
    }

    public void setPatternPath(Path r9) {
        PathMeasure r0 = new PathMeasure(r9, false);
        float[] r3 = new float[2];
        r0.getPosTan(r0.getLength(), r3, null);
        float r2 = r3[0];
        float r6 = r3[1];
        r0.getPosTan(0.0f, r3, null);
        float r02 = r3[0];
        float r1 = r3[1];
        if (r02 == r2) goto L5;
    L9:
        this.mTempMatrix.setTranslate(-r02, -r1);
        float r22 = r2 - r02;
        float r62 = r6 - r1;
        float r12 = 1.0f / distance(r22, r62);
        this.mTempMatrix.postScale(r12, r12);
        this.mTempMatrix.postRotate((float) Math.toDegrees(-Math.atan2(r62, r22)));
        r9.transform(this.mTempMatrix, this.mPatternPath);
        this.mOriginalPatternPath = r9;
        return;
    L5:
        if (r1 != r6) goto L9;
        f$$ExternalSyntheticBUOutline1.m("pattern must not end at the starting point");
    }
}
