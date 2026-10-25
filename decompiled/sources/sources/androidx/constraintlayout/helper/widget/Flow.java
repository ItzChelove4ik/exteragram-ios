package androidx.constraintlayout.helper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;

/* loaded from: classes3.dex */
public class Flow extends VirtualLayout {
    private androidx.constraintlayout.core.widgets.Flow mFlow;

    public Flow(Context r1) {
        super(r1);
    }

    public Flow(Context r1, AttributeSet r2) {
        super(r1, r2);
    }

    public Flow(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet r7) {
        super.init(r7);
        this.mFlow = new androidx.constraintlayout.core.widgets.Flow();
        if (r7 == null) goto L86;
        TypedArray r72 = getContext().obtainStyledAttributes(r7, R$styleable.ConstraintLayout_Layout);
        int r0 = r72.getIndexCount();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L85;
        int r3 = r72.getIndex(r2);
        if (r3 != R$styleable.ConstraintLayout_Layout_android_orientation) goto L10;
        this.mFlow.setOrientation(r72.getInt(r3, 0));
    L84:
        r2 = r2 + 1;
        goto L5
    L10:
        if (r3 != R$styleable.ConstraintLayout_Layout_android_padding) goto L13;
        this.mFlow.setPadding(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L13:
        if (r3 != R$styleable.ConstraintLayout_Layout_android_paddingStart) goto L16;
        this.mFlow.setPaddingStart(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L16:
        if (r3 != R$styleable.ConstraintLayout_Layout_android_paddingEnd) goto L19;
        this.mFlow.setPaddingEnd(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L19:
        if (r3 != R$styleable.ConstraintLayout_Layout_android_paddingLeft) goto L22;
        this.mFlow.setPaddingLeft(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L22:
        if (r3 != R$styleable.ConstraintLayout_Layout_android_paddingTop) goto L25;
        this.mFlow.setPaddingTop(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L25:
        if (r3 != R$styleable.ConstraintLayout_Layout_android_paddingRight) goto L28;
        this.mFlow.setPaddingRight(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L28:
        if (r3 != R$styleable.ConstraintLayout_Layout_android_paddingBottom) goto L31;
        this.mFlow.setPaddingBottom(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L31:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_wrapMode) goto L34;
        this.mFlow.setWrapMode(r72.getInt(r3, 0));
        goto L84
    L34:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) goto L37;
        this.mFlow.setHorizontalStyle(r72.getInt(r3, 0));
        goto L84
    L37:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_verticalStyle) goto L40;
        this.mFlow.setVerticalStyle(r72.getInt(r3, 0));
        goto L84
    L40:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) goto L43;
        this.mFlow.setFirstHorizontalStyle(r72.getInt(r3, 0));
        goto L84
    L43:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) goto L46;
        this.mFlow.setLastHorizontalStyle(r72.getInt(r3, 0));
        goto L84
    L46:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) goto L49;
        this.mFlow.setFirstVerticalStyle(r72.getInt(r3, 0));
        goto L84
    L49:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) goto L52;
        this.mFlow.setLastVerticalStyle(r72.getInt(r3, 0));
        goto L84
    L52:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_horizontalBias) goto L55;
        this.mFlow.setHorizontalBias(r72.getFloat(r3, 0.5f));
        goto L84
    L55:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) goto L58;
        this.mFlow.setFirstHorizontalBias(r72.getFloat(r3, 0.5f));
        goto L84
    L58:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) goto L61;
        this.mFlow.setLastHorizontalBias(r72.getFloat(r3, 0.5f));
        goto L84
    L61:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) goto L64;
        this.mFlow.setFirstVerticalBias(r72.getFloat(r3, 0.5f));
        goto L84
    L64:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) goto L67;
        this.mFlow.setLastVerticalBias(r72.getFloat(r3, 0.5f));
        goto L84
    L67:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_verticalBias) goto L70;
        this.mFlow.setVerticalBias(r72.getFloat(r3, 0.5f));
        goto L84
    L70:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) goto L73;
        this.mFlow.setHorizontalAlign(r72.getInt(r3, 2));
        goto L84
    L73:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_verticalAlign) goto L76;
        this.mFlow.setVerticalAlign(r72.getInt(r3, 2));
        goto L84
    L76:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_horizontalGap) goto L79;
        this.mFlow.setHorizontalGap(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L79:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_verticalGap) goto L82;
        this.mFlow.setVerticalGap(r72.getDimensionPixelSize(r3, 0));
        goto L84
    L82:
        if (r3 != R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) goto L84;
        this.mFlow.setMaxElementsWrap(r72.getInt(r3, -1));
        goto L84
    L85:
        r72.recycle();
    L86:
        this.mHelperWidget = this.mFlow;
        validateParams();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int r2, int r3) {
        onMeasure(this.mFlow, r2, r3);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void onMeasure(androidx.constraintlayout.core.widgets.VirtualLayout r3, int r4, int r5) {
        int r0 = View.MeasureSpec.getMode(r4);
        int r42 = View.MeasureSpec.getSize(r4);
        int r1 = View.MeasureSpec.getMode(r5);
        int r52 = View.MeasureSpec.getSize(r5);
        if (r3 == null) goto L6;
        r3.measure(r0, r42, r1, r52);
        setMeasuredDimension(r3.getMeasuredWidth(), r3.getMeasuredHeight());
        return;
    L6:
        setMeasuredDimension(0, 0);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void resolveRtl(ConstraintWidget r1, boolean r2) {
        this.mFlow.applyRtl(r2);
    }

    public void setFirstHorizontalBias(float r2) {
        this.mFlow.setFirstHorizontalBias(r2);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int r2) {
        this.mFlow.setFirstHorizontalStyle(r2);
        requestLayout();
    }

    public void setFirstVerticalBias(float r2) {
        this.mFlow.setFirstVerticalBias(r2);
        requestLayout();
    }

    public void setFirstVerticalStyle(int r2) {
        this.mFlow.setFirstVerticalStyle(r2);
        requestLayout();
    }

    public void setHorizontalAlign(int r2) {
        this.mFlow.setHorizontalAlign(r2);
        requestLayout();
    }

    public void setHorizontalBias(float r2) {
        this.mFlow.setHorizontalBias(r2);
        requestLayout();
    }

    public void setHorizontalGap(int r2) {
        this.mFlow.setHorizontalGap(r2);
        requestLayout();
    }

    public void setHorizontalStyle(int r2) {
        this.mFlow.setHorizontalStyle(r2);
        requestLayout();
    }

    public void setLastHorizontalBias(float r2) {
        this.mFlow.setLastHorizontalBias(r2);
        requestLayout();
    }

    public void setLastHorizontalStyle(int r2) {
        this.mFlow.setLastHorizontalStyle(r2);
        requestLayout();
    }

    public void setLastVerticalBias(float r2) {
        this.mFlow.setLastVerticalBias(r2);
        requestLayout();
    }

    public void setLastVerticalStyle(int r2) {
        this.mFlow.setLastVerticalStyle(r2);
        requestLayout();
    }

    public void setMaxElementsWrap(int r2) {
        this.mFlow.setMaxElementsWrap(r2);
        requestLayout();
    }

    public void setOrientation(int r2) {
        this.mFlow.setOrientation(r2);
        requestLayout();
    }

    public void setPadding(int r2) {
        this.mFlow.setPadding(r2);
        requestLayout();
    }

    public void setPaddingBottom(int r2) {
        this.mFlow.setPaddingBottom(r2);
        requestLayout();
    }

    public void setPaddingLeft(int r2) {
        this.mFlow.setPaddingLeft(r2);
        requestLayout();
    }

    public void setPaddingRight(int r2) {
        this.mFlow.setPaddingRight(r2);
        requestLayout();
    }

    public void setPaddingTop(int r2) {
        this.mFlow.setPaddingTop(r2);
        requestLayout();
    }

    public void setVerticalAlign(int r2) {
        this.mFlow.setVerticalAlign(r2);
        requestLayout();
    }

    public void setVerticalBias(float r2) {
        this.mFlow.setVerticalBias(r2);
        requestLayout();
    }

    public void setVerticalGap(int r2) {
        this.mFlow.setVerticalGap(r2);
        requestLayout();
    }

    public void setVerticalStyle(int r2) {
        this.mFlow.setVerticalStyle(r2);
        requestLayout();
    }

    public void setWrapMode(int r2) {
        this.mFlow.setWrapMode(r2);
        requestLayout();
    }
}
