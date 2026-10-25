package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes3.dex */
public class Barrier extends ConstraintHelper {
    private androidx.constraintlayout.core.widgets.Barrier mBarrier;
    private int mIndicatedType;
    private int mResolvedType;

    public Barrier(Context r1) {
        super(r1);
        super.setVisibility(8);
    }

    private void updateType(ConstraintWidget r5, int r6, boolean r7) {
        this.mResolvedType = r6;
        int r62 = this.mIndicatedType;
        if (r7 == false) goto L8;
        if (r62 != 5) goto L6;
        this.mResolvedType = 1;
    L13:
        if ((r5 instanceof androidx.constraintlayout.core.widgets.Barrier) == false) goto L16;
        ((androidx.constraintlayout.core.widgets.Barrier) r5).setBarrierType(this.mResolvedType);
        return;
    L16:
        return;
    L6:
        if (r62 != 6) goto L13;
        this.mResolvedType = 0;
        goto L13
    L8:
        if (r62 != 5) goto L10;
        this.mResolvedType = 0;
        goto L13
    L10:
        if (r62 != 6) goto L13;
        this.mResolvedType = 1;
        goto L13
    }

    public boolean getAllowsGoneWidget() {
        return this.mBarrier.getAllowsGoneWidget();
    }

    public int getMargin() {
        return this.mBarrier.getMargin();
    }

    public int getType() {
        return this.mIndicatedType;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet r7) {
        super.init(r7);
        this.mBarrier = new androidx.constraintlayout.core.widgets.Barrier();
        if (r7 == null) goto L17;
        TypedArray r72 = getContext().obtainStyledAttributes(r7, R$styleable.ConstraintLayout_Layout);
        int r0 = r72.getIndexCount();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L16;
        int r3 = r72.getIndex(r2);
        if (r3 != R$styleable.ConstraintLayout_Layout_barrierDirection) goto L10;
        setType(r72.getInt(r3, 0));
    L15:
        r2 = r2 + 1;
        goto L5
    L10:
        if (r3 != R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) goto L13;
        this.mBarrier.setAllowsGoneWidget(r72.getBoolean(r3, true));
        goto L15
    L13:
        if (r3 != R$styleable.ConstraintLayout_Layout_barrierMargin) goto L15;
        this.mBarrier.setMargin(r72.getDimensionPixelSize(r3, 0));
        goto L15
    L16:
        r72.recycle();
    L17:
        this.mHelperWidget = this.mBarrier;
        validateParams();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void resolveRtl(ConstraintWidget r2, boolean r3) {
        updateType(r2, this.mIndicatedType, r3);
    }

    public void setAllowsGoneWidget(boolean r1) {
        this.mBarrier.setAllowsGoneWidget(r1);
    }

    public void setDpMargin(int r2) {
        this.mBarrier.setMargin((int) ((r2 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int r1) {
        this.mBarrier.setMargin(r1);
    }

    public void setType(int r1) {
        this.mIndicatedType = r1;
    }
}
