package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

/* loaded from: classes4.dex */
class ViewUtilsApi29 extends ViewUtilsApi23 {
    public ViewUtilsApi29() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    public float getTransitionAlpha(View r1) {
        return r1.getTransitionAlpha();
    }

    @Override // androidx.transition.ViewUtilsApi22, androidx.transition.ViewUtilsApi19
    public void setLeftTopRightBottom(View r1, int r2, int r3, int r4, int r5) {
        r1.setLeftTopRightBottom(r2, r3, r4, r5);
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void setTransitionAlpha(View r1, float r2) {
        r1.setTransitionAlpha(r2);
    }

    @Override // androidx.transition.ViewUtilsApi23, androidx.transition.ViewUtilsApi19
    public void setTransitionVisibility(View r1, int r2) {
        r1.setTransitionVisibility(r2);
    }

    @Override // androidx.transition.ViewUtilsApi21, androidx.transition.ViewUtilsApi19
    public void transformMatrixToGlobal(View r1, Matrix r2) {
        r1.transformMatrixToGlobal(r2);
    }

    @Override // androidx.transition.ViewUtilsApi21, androidx.transition.ViewUtilsApi19
    public void transformMatrixToLocal(View r1, Matrix r2) {
        r1.transformMatrixToLocal(r2);
    }
}
