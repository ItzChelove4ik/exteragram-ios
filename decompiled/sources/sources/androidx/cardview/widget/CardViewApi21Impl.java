package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* loaded from: classes3.dex */
class CardViewApi21Impl implements CardViewImpl {
    public CardViewApi21Impl() {
    }

    private RoundRectDrawable getCardBackground(CardViewDelegate r1) {
        return (RoundRectDrawable) r1.getCardBackground();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public ColorStateList getBackgroundColor(CardViewDelegate r1) {
        return getCardBackground(r1).getColor();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float getElevation(CardViewDelegate r1) {
        return r1.getCardView().getElevation();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float getMaxElevation(CardViewDelegate r1) {
        return getCardBackground(r1).getPadding();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float getMinHeight(CardViewDelegate r1) {
        return getRadius(r1) * 2.0f;
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float getMinWidth(CardViewDelegate r1) {
        return getRadius(r1) * 2.0f;
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public float getRadius(CardViewDelegate r1) {
        return getCardBackground(r1).getRadius();
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void initStatic() {
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void initialize(CardViewDelegate r1, Context r2, ColorStateList r3, float r4, float r5, float r6) {
        r1.setCardBackground(new RoundRectDrawable(r3, r4));
        View r22 = r1.getCardView();
        r22.setClipToOutline(true);
        r22.setElevation(r5);
        setMaxElevation(r1, r6);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void onCompatPaddingChanged(CardViewDelegate r2) {
        setMaxElevation(r2, getMaxElevation(r2));
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void onPreventCornerOverlapChanged(CardViewDelegate r2) {
        setMaxElevation(r2, getMaxElevation(r2));
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void setBackgroundColor(CardViewDelegate r1, ColorStateList r2) {
        getCardBackground(r1).setColor(r2);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void setElevation(CardViewDelegate r1, float r2) {
        r1.getCardView().setElevation(r2);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void setMaxElevation(CardViewDelegate r4, float r5) {
        getCardBackground(r4).setPadding(r5, r4.getUseCompatPadding(), r4.getPreventCornerOverlap());
        updatePadding(r4);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void setRadius(CardViewDelegate r1, float r2) {
        getCardBackground(r1).setRadius(r2);
    }

    @Override // androidx.cardview.widget.CardViewImpl
    public void updatePadding(CardViewDelegate r5) {
        if (r5.getUseCompatPadding() == true) goto L6;
        r5.setShadowPadding(0, 0, 0, 0);
        return;
    L6:
        float r0 = getMaxElevation(r5);
        float r4 = getRadius(r5);
        int r1 = (int) Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(r0, r4, r5.getPreventCornerOverlap()));
        int r42 = (int) Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(r0, r4, r5.getPreventCornerOverlap()));
        r5.setShadowPadding(r1, r42, r1, r42);
    }
}
