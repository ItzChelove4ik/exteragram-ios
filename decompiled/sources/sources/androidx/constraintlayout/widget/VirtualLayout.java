package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

/* loaded from: classes3.dex */
public abstract class VirtualLayout extends ConstraintHelper {
    private boolean mApplyElevationOnAttach;
    private boolean mApplyVisibilityOnAttach;

    public VirtualLayout(Context r1) {
        super(r1);
    }

    public VirtualLayout(Context r1, AttributeSet r2) {
        super(r1, r2);
    }

    public VirtualLayout(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void applyLayoutFeaturesInConstraintSet(ConstraintLayout r1) {
        applyLayoutFeatures(r1);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet r6) {
        super.init(r6);
        if (r6 == null) goto L19;
        TypedArray r62 = getContext().obtainStyledAttributes(r6, R$styleable.ConstraintLayout_Layout);
        int r0 = r62.getIndexCount();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L13;
        int r2 = r62.getIndex(r1);
        if (r2 != R$styleable.ConstraintLayout_Layout_android_visibility) goto L10;
        this.mApplyVisibilityOnAttach = true;
    L12:
        r1 = r1 + 1;
        goto L5
    L10:
        if (r2 != R$styleable.ConstraintLayout_Layout_android_elevation) goto L12;
        this.mApplyElevationOnAttach = true;
        goto L12
    L13:
        r62.recycle();
        return;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mApplyVisibilityOnAttach == false) goto L5;
    L6:
        ViewParent r0 = getParent();
        if ((r0 instanceof ConstraintLayout) == false) goto L28;
        ConstraintLayout r02 = (ConstraintLayout) r0;
        int r1 = getVisibility();
        float r2 = getElevation();
        int r3 = 0;
    L10:
        if (r3 >= this.mCount) goto L29;
        View r4 = r02.getViewById(this.mIds[r3]);
        if (r4 == null) goto L21;
        if (this.mApplyVisibilityOnAttach == false) goto L17;
        r4.setVisibility(r1);
    L17:
        if (this.mApplyElevationOnAttach == false) goto L21;
        if (r2 <= 0.0f) goto L21;
        r4.setTranslationZ(r4.getTranslationZ() + r2);
    L21:
        r3 = r3 + 1;
        goto L10
    L29:
        return;
    L28:
        return;
    L5:
        if (this.mApplyElevationOnAttach == true) goto L6;
    }

    public abstract void onMeasure(androidx.constraintlayout.core.widgets.VirtualLayout r1, int r2, int r3);

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public void setVisibility(int r1) {
        super.setVisibility(r1);
        applyLayoutFeatures();
    }
}
