package org.telegram.ui.Components.Premium;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.GenericProvider;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CheckBoxBase;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes7.dex */
public class PremiumTierCell extends ViewGroup {
    private CheckBox2 checkBox;
    private int color0;
    private int color1;
    private int colorKey1;
    private int colorKey2;
    protected TextView discountView;
    private PremiumTierCell globalGradientView;
    private LinearGradient gradient;
    private int gradientWidth;
    private boolean hasDivider;
    private boolean isDrawingGradient;
    private long lastUpdateTime;
    private int leftPaddingToCheckboxDp;
    private int leftPaddingToTextDp;
    private Matrix matrix;
    private Paint paint;
    private int parentWidth;
    private float parentXOffset;
    private TextView pricePerMonthView;
    private TextView pricePerYearStrikeView;
    private TextView pricePerYearView;
    protected PremiumPreviewFragment.SubscriptionTier tier;
    private TextView titleView;
    private int totalTranslation;

    public PremiumTierCell(Context r18) {
        super(r18);
        this.leftPaddingToTextDp = 12;
        this.leftPaddingToCheckboxDp = 8;
        this.colorKey1 = Theme.key_windowBackgroundWhite;
        this.colorKey2 = Theme.key_windowBackgroundGray;
        this.paint = new Paint();
        this.matrix = new Matrix();
        CheckBox2 r2 = new CheckBox2(r18, 24);
        this.checkBox = r2;
        r2.setDrawBackgroundAsArc(10);
        CheckBox2 r22 = this.checkBox;
        int r3 = Theme.key_radioBackground;
        r22.setColor(r3, r3, Theme.key_checkboxCheck);
        addView(this.checkBox);
        TextView r23 = new TextView(r18);
        this.titleView = r23;
        r23.setTextSize(1, 16.0f);
        this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setSingleLine();
        TextView r24 = this.titleView;
        int r5 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r32 = 5;
    L6:
        addView(r24, LayoutHelper.createFrame(-2, -2.0f, r32 | 48, 0.0f, 8.0f, 0.0f, 0.0f));
        TextView r25 = new TextView(r18);
        this.discountView = r25;
        r25.setTextSize(1, 14.0f);
        this.discountView.setTextColor(-1);
        this.discountView.setPadding(AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f), 0);
        this.discountView.setTypeface(AndroidUtilities.bold());
        TextView r26 = this.discountView;
        if (LocaleController.isRTL == false) goto L9;
        int r7 = 5;
    L10:
        addView(r26, LayoutHelper.createFrame(-2, -2.0f, r7 | 80, 0.0f, 0.0f, 0.0f, 8.0f));
        TextView r27 = new TextView(r18);
        this.pricePerYearStrikeView = r27;
        r27.setTextSize(1, 14.0f);
        TextView r28 = this.pricePerYearStrikeView;
        int r72 = Theme.key_windowBackgroundWhiteGrayText;
        r28.setTextColor(Theme.getColor(r72));
        this.pricePerYearStrikeView.getPaint().setStrikeThruText(true);
        this.pricePerYearStrikeView.setSingleLine();
        TextView r29 = this.pricePerYearStrikeView;
        if (LocaleController.isRTL == false) goto L13;
        int r8 = 5;
    L14:
        addView(r29, LayoutHelper.createFrame(-2, -2.0f, r8 | 80, 0.0f, 0.0f, 0.0f, 8.0f));
        TextView r210 = new TextView(r18);
        this.pricePerYearView = r210;
        r210.setTextSize(1, 14.0f);
        this.pricePerYearView.setTextColor(Theme.getColor(r72));
        this.pricePerYearView.setSingleLine();
        TextView r211 = this.pricePerYearView;
        if (LocaleController.isRTL == false) goto L17;
        r5 = 5;
    L17:
        addView(r211, LayoutHelper.createFrame(-2, -2.0f, r5 | 80, 0.0f, 0.0f, 0.0f, 8.0f));
        TextView r212 = new TextView(r18);
        this.pricePerMonthView = r212;
        r212.setTextSize(1, 15.0f);
        this.pricePerMonthView.setTextColor(Theme.getColor(r72));
        this.pricePerMonthView.setSingleLine();
        addView(this.pricePerMonthView, LayoutHelper.createFrame(-2, -2, 8388613));
        setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f));
        setClipToPadding(false);
        setWillNotDraw(false);
        return;
    L13:
        r8 = 3;
        goto L14
    L9:
        r7 = 3;
        goto L10
    L5:
        r32 = 3;
        goto L6
    }

    private void checkRtlAndLayout(View r5) {
        Rect r0 = AndroidUtilities.rectTmp2;
        r0.right = r0.left + r5.getMeasuredWidth();
        r0.bottom = r0.top + r5.getMeasuredHeight();
        if (LocaleController.isRTL == false) goto L5;
        int r1 = r0.right;
        r0.right = getWidth() - r0.left;
        r0.left = getWidth() - r1;
    L5:
        r5.layout(r0.left, r0.top, r0.right, r0.bottom);
    }

    @SuppressLint({"SetTextI18n"})
    public void bind(PremiumPreviewFragment.SubscriptionTier r6, boolean r7) {
        this.tier = r6;
        this.hasDivider = r7;
        int r72 = r6.getMonths();
        boolean r1 = true;
        if (r72 != 1) goto L5;
        this.titleView.setText(LocaleController.getString(R.string.PremiumTierMonthly));
    L19:
        if (BuildVars.useInvoiceBilling() == false) goto L21;
    L25:
        r1 = false;
    L26:
        this.isDrawingGradient = r1;
        if (r1 == true) goto L35;
        int r73 = r6.getDiscount();
        TextView r12 = this.discountView;
        if (r73 > 0) goto L31;
        r12.setVisibility(8);
        this.pricePerYearStrikeView.setVisibility(8);
        this.pricePerYearView.setVisibility(8);
    L32:
        this.pricePerYearStrikeView.setText(r6.getFormattedPricePerYearRegular());
        this.pricePerYearView.setText(LocaleController.formatString(R.string.PricePerYear, new Object[]{r6.getFormattedPricePerYear()}));
        this.pricePerMonthView.setText(LocaleController.formatString(R.string.PricePerMonthMe, new Object[]{r6.getFormattedPricePerMonth()}));
        if (r6.subscriptionOption.current == false) goto L36;
        this.pricePerYearView.setVisibility(0);
        this.pricePerYearView.setText(LocaleController.getString(R.string.YourCurrentPlan));
    L36:
        requestLayout();
        return;
    L31:
        r12.setText(LocaleController.formatString(R.string.GiftPremiumOptionDiscount, new Object[]{Integer.valueOf(r6.getDiscount())}));
        this.discountView.setVisibility(0);
        this.pricePerYearStrikeView.setVisibility(0);
        this.pricePerYearView.setVisibility(0);
        goto L32
    L35:
        this.discountView.setText(LocaleController.formatString(R.string.GiftPremiumOptionDiscount, new Object[]{10}));
        this.discountView.setVisibility(0);
        this.pricePerYearStrikeView.setVisibility(0);
        this.pricePerYearView.setVisibility(0);
        this.pricePerYearStrikeView.setText("USD00.00");
        this.pricePerYearView.setText(LocaleController.formatString(R.string.PricePerYear, new Object[]{1000}));
        this.pricePerMonthView.setText(LocaleController.formatString(R.string.PricePerMonthMe, new Object[]{100}));
        goto L36
    L21:
        if (BillingController.getInstance().isReady() == false) goto L26;
        if (r6.getOfferDetails() != null) goto L25;
    L5:
        if (r72 == 6) goto L16;
        TextView r2 = this.titleView;
        if (r72 != 12) goto L9;
        r2.setText(LocaleController.getString(R.string.PremiumTierAnnual));
        goto L19
    L9:
        if (r6.getMonths() > 12) goto L11;
    L13:
        String r74 = LocaleController.formatPluralString("Months", r6.getMonths(), new Object[0]);
    L14:
        r2.setText(r74);
        goto L19
    L11:
        if ((r6.getMonths() % 12) != 0) goto L13;
        r74 = LocaleController.formatPluralString("PremiumTierAnnualYears", r6.getMonths() / 12, new Object[0]);
        goto L14
    L16:
        this.titleView.setText(LocaleController.getString(R.string.PremiumTierSemiannual));
        goto L19
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r10) {
        if (this.isDrawingGradient == false) goto L9;
        Paint r0 = this.paint;
        PremiumTierCell r1 = this.globalGradientView;
        if (r1 == null) goto L7;
        r0 = r1.paint;
    L7:
        drawChild(r10, this.checkBox, getDrawingTime());
        updateColors();
        updateGradient();
        RectF r12 = AndroidUtilities.rectTmp;
        r12.set(this.pricePerMonthView.getLeft(), this.pricePerMonthView.getTop() + AndroidUtilities.dp(4.0f), this.pricePerMonthView.getRight(), this.pricePerMonthView.getBottom() - AndroidUtilities.dp(4.0f));
        r10.drawRoundRect(r12, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), r0);
        r12.set(this.pricePerYearStrikeView.getLeft(), this.pricePerYearStrikeView.getTop() + AndroidUtilities.dp(3.0f), this.pricePerYearStrikeView.getRight(), this.pricePerYearStrikeView.getBottom() - AndroidUtilities.dp(3.0f));
        r10.drawRoundRect(r12, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), r0);
        r12.set(this.titleView.getLeft(), this.titleView.getTop() + AndroidUtilities.dp(4.0f), this.titleView.getRight(), this.titleView.getBottom() - AndroidUtilities.dp(4.0f));
        r10.drawRoundRect(r12, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), r0);
        invalidate();
        return;
    L9:
        super.dispatchDraw(r10);
    }

    public PremiumPreviewFragment.SubscriptionTier getTier() {
        return this.tier;
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        super.onDraw(r14);
        if (this.hasDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        r14.drawLine(0.0f, getHeight() - 1, this.titleView.getRight(), getHeight() - 1, Theme.dividerPaint);
        return;
    L8:
        r14.drawLine(this.titleView.getLeft(), getHeight() - 1, getWidth(), getHeight() - 1, Theme.dividerPaint);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        Rect r32 = AndroidUtilities.rectTmp2;
        r32.set(AndroidUtilities.dp(this.leftPaddingToCheckboxDp) + getPaddingLeft(), (int) ((getMeasuredHeight() - this.checkBox.getMeasuredHeight()) / 2.0f), 0, 0);
        checkRtlAndLayout(this.checkBox);
        int r42 = (int) ((getMeasuredHeight() - this.pricePerMonthView.getMeasuredHeight()) / 2.0f);
        int r52 = AndroidUtilities.dp((this.leftPaddingToCheckboxDp + this.leftPaddingToTextDp) + 24) + this.checkBox.getMeasuredWidth();
        if (this.pricePerYearStrikeView.getVisibility() != 0) goto L5;
        int r0 = this.pricePerYearStrikeView.getMeasuredWidth();
    L7:
        if ((((r52 + r0) + this.pricePerYearView.getMeasuredWidth()) + getPaddingLeft()) > (getMeasuredWidth() - this.pricePerMonthView.getMeasuredWidth())) goto L9;
    L11:
        r32.set(((getMeasuredWidth() - this.pricePerMonthView.getMeasuredWidth()) - AndroidUtilities.dp(16.0f)) - getPaddingRight(), r42, 0, 0);
        checkRtlAndLayout(this.pricePerMonthView);
        int r43 = (AndroidUtilities.dp(this.leftPaddingToCheckboxDp + this.leftPaddingToTextDp) + this.checkBox.getMeasuredWidth()) + getPaddingLeft();
        if (this.pricePerYearView.getVisibility() != 8) goto L14;
        int r53 = (int) ((getMeasuredHeight() - this.titleView.getMeasuredHeight()) / 2.0f);
    L15:
        r32.set(r43, r53, 0, 0);
        checkRtlAndLayout(this.titleView);
        if (this.discountView.getVisibility() != 0) goto L18;
        r32.set(((AndroidUtilities.dp((this.leftPaddingToCheckboxDp + this.leftPaddingToTextDp) + 6) + this.checkBox.getMeasuredWidth()) + getPaddingLeft()) + this.titleView.getMeasuredWidth(), getPaddingTop() + AndroidUtilities.dp(2.0f), 0, 0);
        checkRtlAndLayout(this.discountView);
    L18:
        r32.set((AndroidUtilities.dp(this.leftPaddingToCheckboxDp + this.leftPaddingToTextDp) + this.checkBox.getMeasuredWidth()) + getPaddingLeft(), (getMeasuredHeight() - this.pricePerYearStrikeView.getMeasuredHeight()) - getPaddingBottom(), 0, 0);
        checkRtlAndLayout(this.pricePerYearStrikeView);
        int r44 = AndroidUtilities.dp(this.leftPaddingToCheckboxDp + this.leftPaddingToTextDp) + this.checkBox.getMeasuredWidth();
        if (this.pricePerYearStrikeView.getVisibility() != 0) goto L21;
        int r54 = this.pricePerYearStrikeView.getMeasuredWidth() + AndroidUtilities.dp(6.0f);
    L22:
        r32.set((r44 + r54) + getPaddingLeft(), (getMeasuredHeight() - this.pricePerYearView.getMeasuredHeight()) - getPaddingBottom(), 0, 0);
        checkRtlAndLayout(this.pricePerYearView);
        return;
    L21:
        r54 = 0;
        goto L22
    L14:
        r53 = getPaddingTop();
        goto L15
    L9:
        if (this.discountView.getVisibility() != 0) goto L11;
        r42 = getPaddingTop() + AndroidUtilities.dp(2.0f);
        goto L11
    L5:
        r0 = 0;
        goto L7
    }

    @Override // android.view.View
    public void onMeasure(int r6, int r7) {
        int r62 = View.MeasureSpec.getSize(r6);
        int r72 = AndroidUtilities.dp(58.0f);
        int r0 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30);
        this.checkBox.measure(r0, r0);
        this.pricePerMonthView.measure(View.MeasureSpec.makeMeasureSpec(r62 - this.checkBox.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        this.titleView.measure(View.MeasureSpec.makeMeasureSpec((r62 - this.checkBox.getMeasuredWidth()) - this.pricePerMonthView.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        int r02 = this.discountView.getVisibility();
        TextView r2 = this.discountView;
        int r4 = 0;
        if (r02 != 0) goto L5;
        r2.measure(View.MeasureSpec.makeMeasureSpec((r62 - this.checkBox.getMeasuredWidth()) - this.pricePerMonthView.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
    L6:
        this.pricePerYearStrikeView.measure(View.MeasureSpec.makeMeasureSpec(r62 - this.checkBox.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        TextView r03 = this.pricePerYearView;
        int r1 = r62 - this.checkBox.getMeasuredWidth();
        if (this.pricePerYearStrikeView.getVisibility() != 0) goto L9;
        r4 = this.pricePerYearStrikeView.getMeasuredWidth();
    L9:
        r03.measure(View.MeasureSpec.makeMeasureSpec((r1 - r4) - AndroidUtilities.dp(6.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        if (this.pricePerYearView.getVisibility() == 0) goto L12;
        r72 = r72 - AndroidUtilities.dp(8.0f);
    L12:
        setMeasuredDimension(r62, r72);
        return;
    L5:
        r2.measure(View.MeasureSpec.makeMeasureSpec(0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, TLObject.FLAG_30));
        goto L6
    }

    public void setChecked(boolean r1, boolean r2) {
        this.checkBox.setChecked(r1, r2);
    }

    public void setCirclePaintProvider(GenericProvider<Void, Paint> r1) {
        this.checkBox.setCirclePaintProvider(r1);
    }

    @Override // android.view.View
    public void setEnabled(boolean r5) {
        super.setEnabled(r5);
        TextView r0 = this.titleView;
        float r1 = 0.6f;
        if (r5 == false) goto L5;
        float r3 = 1.0f;
    L6:
        r0.setAlpha(r3);
        TextView r02 = this.pricePerMonthView;
        if (r5 == false) goto L9;
        float r32 = 1.0f;
    L10:
        r02.setAlpha(r32);
        CheckBox2 r4 = this.checkBox;
        if (r5 == false) goto L13;
        r1 = 1.0f;
    L13:
        r4.setAlpha(r1);
        return;
    L9:
        r32 = 0.6f;
        goto L10
    L5:
        r3 = 0.6f;
        goto L6
    }

    public void setGlobalGradientView(PremiumTierCell r1) {
        this.globalGradientView = r1;
    }

    public void setParentXOffset(float r1) {
        this.parentXOffset = r1;
    }

    public void setProgressDelegate(CheckBoxBase.ProgressDelegate r1) {
        this.checkBox.setProgressDelegate(r1);
    }

    public void updateColors() {
        PremiumTierCell r0 = this.globalGradientView;
        if (r0 == null) goto L6;
        r0.updateColors();
        return;
    L6:
        int r02 = Theme.getColor(this.colorKey1);
        int r1 = Theme.getColor(this.colorKey2);
        if (this.color1 == r1) goto L9;
    L12:
        this.color0 = r02;
        this.color1 = r1;
        int r2 = AndroidUtilities.dp(200.0f);
        this.gradientWidth = r2;
        LinearGradient r3 = new LinearGradient(0.0f, 0.0f, r2, 0.0f, new int[]{r1, r02, r02, r1}, new float[]{0.0f, 0.4f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
        this.gradient = r3;
        this.paint.setShader(r3);
        return;
    L9:
        if (this.color0 != r02) goto L12;
    }

    public void updateGradient() {
        PremiumTierCell r0 = this.globalGradientView;
        if (r0 == null) goto L6;
        r0.updateGradient();
        return;
    L6:
        long r02 = SystemClock.elapsedRealtime();
        long r2 = Math.abs(this.lastUpdateTime - r02);
        if (r2 <= 17) goto L10;
        r2 = 16;
    L10:
        if (r2 >= 4) goto L12;
        r2 = 0;
    L12:
        int r4 = this.parentWidth;
        if (r4 != 0) goto L15;
        r4 = getMeasuredWidth();
    L15:
        this.lastUpdateTime = r02;
        int r03 = (int) (this.totalTranslation + ((r2 * r4) / 400.0f));
        this.totalTranslation = r03;
        if (r03 < (r4 * 4)) goto L18;
        this.totalTranslation = (-this.gradientWidth) * 2;
    L18:
        this.matrix.setTranslate(this.totalTranslation + this.parentXOffset, 0.0f);
        LinearGradient r04 = this.gradient;
        if (r04 == null) goto L22;
        r04.setLocalMatrix(this.matrix);
        return;
    }
}
