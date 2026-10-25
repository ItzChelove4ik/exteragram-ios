package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.cardview.R$color;
import androidx.cardview.R$style;
import androidx.cardview.R$styleable;

/* loaded from: classes3.dex */
public abstract class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = null;
    private static final CardViewImpl IMPL = null;
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    /* renamed from: androidx.cardview.widget.CardView$1, reason: invalid class name */
    public class AnonymousClass1 implements CardViewDelegate {
        private Drawable mCardBackground;
        final /* synthetic */ CardView this$0;

        public AnonymousClass1(CardView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.cardview.widget.CardViewDelegate
        public Drawable getCardBackground() {
            return this.mCardBackground;
        }

        @Override // androidx.cardview.widget.CardViewDelegate
        public View getCardView() {
            return this.this$0;
        }

        @Override // androidx.cardview.widget.CardViewDelegate
        public boolean getPreventCornerOverlap() {
            return this.this$0.getPreventCornerOverlap();
        }

        @Override // androidx.cardview.widget.CardViewDelegate
        public boolean getUseCompatPadding() {
            return this.this$0.getUseCompatPadding();
        }

        @Override // androidx.cardview.widget.CardViewDelegate
        public void setCardBackground(Drawable r1) {
            this.mCardBackground = r1;
            this.this$0.setBackgroundDrawable(r1);
        }

        @Override // androidx.cardview.widget.CardViewDelegate
        public void setShadowPadding(int r3, int r4, int r5, int r6) {
            this.this$0.mShadowBounds.set(r3, r4, r5, r6);
            CardView r2 = this.this$0;
            Rect r0 = r2.mContentPadding;
            CardView.access$001(r2, r3 + r0.left, r4 + r0.top, r5 + r0.right, r6 + r0.bottom);
        }
    }

    static {
        COLOR_BACKGROUND_ATTR = new int[]{R.attr.colorBackground};
        CardViewApi21Impl r0 = new CardViewApi21Impl();
        IMPL = r0;
        r0.initStatic();
    }

    public CardView(Context r10, AttributeSet r11, int r12) {
        super(r10, r11, r12);
        Rect r0 = new Rect();
        this.mContentPadding = r0;
        this.mShadowBounds = new Rect();
        AnonymousClass1 r3 = new AnonymousClass1(this);
        this.mCardViewDelegate = r3;
        TypedArray r112 = r10.obtainStyledAttributes(r11, R$styleable.CardView, r12, R$style.CardView);
        if (r112.hasValue(R$styleable.CardView_cardBackgroundColor) == false) goto L6;
        ColorStateList r122 = r112.getColorStateList(R$styleable.CardView_cardBackgroundColor);
    L5:
        ColorStateList r5 = r122;
        float r6 = r112.getDimension(R$styleable.CardView_cardCornerRadius, 0.0f);
        float r7 = r112.getDimension(R$styleable.CardView_cardElevation, 0.0f);
        float r123 = r112.getDimension(R$styleable.CardView_cardMaxElevation, 0.0f);
        this.mCompatPadding = r112.getBoolean(R$styleable.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = r112.getBoolean(R$styleable.CardView_cardPreventCornerOverlap, true);
        int r2 = r112.getDimensionPixelSize(R$styleable.CardView_contentPadding, 0);
        r0.left = r112.getDimensionPixelSize(R$styleable.CardView_contentPaddingLeft, r2);
        r0.top = r112.getDimensionPixelSize(R$styleable.CardView_contentPaddingTop, r2);
        r0.right = r112.getDimensionPixelSize(R$styleable.CardView_contentPaddingRight, r2);
        r0.bottom = r112.getDimensionPixelSize(R$styleable.CardView_contentPaddingBottom, r2);
        if (r7 <= r123) goto L14;
        float r8 = r7;
    L15:
        this.mUserSetMinWidth = r112.getDimensionPixelSize(R$styleable.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = r112.getDimensionPixelSize(R$styleable.CardView_android_minHeight, 0);
        r112.recycle();
        IMPL.initialize(r3, r10, r5, r6, r7, r8);
        return;
    L14:
        r8 = r123;
        goto L15
    L6:
        TypedArray r124 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
        int r22 = r124.getColor(0, 0);
        r124.recycle();
        float[] r125 = new float[3];
        Color.colorToHSV(r22, r125);
        if (r125[2] <= 0.5f) goto L9;
        int r126 = getResources().getColor(R$color.cardview_light_background);
    L10:
        r122 = ColorStateList.valueOf(r126);
        goto L5
    L9:
        r126 = getResources().getColor(R$color.cardview_dark_background);
        goto L10
    }

    public static /* synthetic */ void access$001(CardView r0, int r1, int r2, int r3, int r4) {
        super.setPadding(r1, r2, r3, r4);
    }

    public static /* synthetic */ void access$101(CardView r0, int r1) {
        super.setMinimumWidth(r1);
    }

    public static /* synthetic */ void access$201(CardView r0, int r1) {
        super.setMinimumHeight(r1);
    }

    public ColorStateList getCardBackgroundColor() {
        return IMPL.getBackgroundColor(this.mCardViewDelegate);
    }

    public float getCardElevation() {
        return IMPL.getElevation(this.mCardViewDelegate);
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public float getMaxCardElevation() {
        return IMPL.getMaxElevation(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public float getRadius() {
        return IMPL.getRadius(this.mCardViewDelegate);
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        if ((IMPL instanceof CardViewApi21Impl) == true) goto L16;
        int r1 = View.MeasureSpec.getMode(r7);
        if (r1 == Integer.MIN_VALUE) goto L8;
        if (r1 == 1073741824) goto L8;
    L9:
        int r12 = View.MeasureSpec.getMode(r8);
        if (r12 == Integer.MIN_VALUE) goto L13;
        if (r12 == 1073741824) goto L13;
    L14:
        super.onMeasure(r7, r8);
        return;
    L13:
        r8 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.getMinHeight(this.mCardViewDelegate)), View.MeasureSpec.getSize(r8)), r12);
    L8:
        r7 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.getMinWidth(this.mCardViewDelegate)), View.MeasureSpec.getSize(r7)), r1);
        goto L9
    L16:
        super.onMeasure(r7, r8);
    }

    public void setCardBackgroundColor(int r2) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(r2));
    }

    public void setCardBackgroundColor(ColorStateList r2) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, r2);
    }

    public void setCardElevation(float r2) {
        IMPL.setElevation(this.mCardViewDelegate, r2);
    }

    public void setContentPadding(int r2, int r3, int r4, int r5) {
        this.mContentPadding.set(r2, r3, r4, r5);
        IMPL.updatePadding(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float r2) {
        IMPL.setMaxElevation(this.mCardViewDelegate, r2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int r1) {
        this.mUserSetMinHeight = r1;
        super.setMinimumHeight(r1);
    }

    @Override // android.view.View
    public void setMinimumWidth(int r1) {
        this.mUserSetMinWidth = r1;
        super.setMinimumWidth(r1);
    }

    @Override // android.view.View
    public void setPadding(int r1, int r2, int r3, int r4) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int r1, int r2, int r3, int r4) {
    }

    public void setPreventCornerOverlap(boolean r2) {
        if (r2 == this.mPreventCornerOverlap) goto L6;
        this.mPreventCornerOverlap = r2;
        IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        return;
    }

    public void setRadius(float r2) {
        IMPL.setRadius(this.mCardViewDelegate, r2);
    }

    public void setUseCompatPadding(boolean r2) {
        if (this.mCompatPadding == r2) goto L6;
        this.mCompatPadding = r2;
        IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        return;
    }
}
