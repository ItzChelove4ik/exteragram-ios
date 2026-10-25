package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class OutlineTextContainerView extends FrameLayout {
    private static final SimpleFloatPropertyCompat<OutlineTextContainerView> ERROR_PROGRESS_PROPERTY = null;
    private static final SimpleFloatPropertyCompat<OutlineTextContainerView> SELECTION_PROGRESS_PROPERTY = null;
    private static final SimpleFloatPropertyCompat<OutlineTextContainerView> TITLE_PROGRESS_PROPERTY = null;
    private EditText attachedEditText;
    private float errorProgress;
    private final SpringAnimation errorSpring;
    private boolean forceForceUseCenter;
    private boolean forceUseCenter;
    private boolean forceUseCenter2;
    private float leftPadding;
    private String mText;
    private final Paint outlinePaint;
    private final RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private float selectionProgress;
    private final SpringAnimation selectionSpring;
    private float strokeWidthRegular;
    private float strokeWidthSelected;
    private final TextPaint textPaint;
    private float titleProgress;
    private final SpringAnimation titleSpring;
    private boolean useDefaultColor;

    /* renamed from: $r8$lambda$-NTBwYYiqEQB1lX7STPNeYJyj00, reason: not valid java name */
    public static /* synthetic */ float m11315$r8$lambda$NTBwYYiqEQB1lX7STPNeYJyj00(OutlineTextContainerView r0) {
        return r0.selectionProgress;
    }

    public static /* synthetic */ void $r8$lambda$ER1AkNFDFBwjSJrbPmabWBoFiuY(OutlineTextContainerView r3, float r4) {
        r3.selectionProgress = r4;
        if (r3.forceUseCenter == true) goto L5;
    L6:
        r3.outlinePaint.setStrokeWidth(AndroidUtilities.lerp(r3.strokeWidthRegular, r3.strokeWidthSelected, r4));
        r3.updateColor();
    L7:
        r3.invalidate();
        return;
    L5:
        if (r3.forceForceUseCenter == false) goto L7;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$LQNdMi674Af6TQJwnGs3DtH2tfU(OutlineTextContainerView r0, float r1) {
        r0.titleProgress = r1;
        if (r0.forceUseCenter == true) goto L5;
    L6:
        r0.updateColor();
    L7:
        r0.invalidate();
        return;
    L5:
        if (r0.forceForceUseCenter == false) goto L7;
        goto L6
    }

    /* renamed from: $r8$lambda$_HDnhwxl2Y-egjHeIRPL_ae39j8, reason: not valid java name */
    public static /* synthetic */ void m11316$r8$lambda$_HDnhwxl2YegjHeIRPL_ae39j8(OutlineTextContainerView r0, float r1) {
        r0.errorProgress = r1;
        r0.updateColor();
    }

    /* renamed from: $r8$lambda$kMTvxUh2HPhbziatQ-om9Q6u3a8, reason: not valid java name */
    public static /* synthetic */ float m11317$r8$lambda$kMTvxUh2HPhbziatQom9Q6u3a8(OutlineTextContainerView r0) {
        return r0.errorProgress;
    }

    /* renamed from: $r8$lambda$pgwTk6OU-GMh7XGAU4rPriNX0yg, reason: not valid java name */
    public static /* synthetic */ float m11318$r8$lambda$pgwTk6OUGMh7XGAU4rPriNX0yg(OutlineTextContainerView r0) {
        return r0.titleProgress;
    }

    static {
        SELECTION_PROGRESS_PROPERTY = new SimpleFloatPropertyCompat("selectionProgress", new OutlineTextContainerView$$ExternalSyntheticLambda0(), new OutlineTextContainerView$$ExternalSyntheticLambda1()).setMultiplier(100.0f);
        TITLE_PROGRESS_PROPERTY = new SimpleFloatPropertyCompat("titleProgress", new OutlineTextContainerView$$ExternalSyntheticLambda2(), new OutlineTextContainerView$$ExternalSyntheticLambda3()).setMultiplier(100.0f);
        ERROR_PROGRESS_PROPERTY = new SimpleFloatPropertyCompat("errorProgress", new OutlineTextContainerView$$ExternalSyntheticLambda4(), new OutlineTextContainerView$$ExternalSyntheticLambda5()).setMultiplier(100.0f);
    }

    public OutlineTextContainerView(Context r2) {
        this(r2, null);
    }

    public OutlineTextContainerView(Context r4, Theme.ResourcesProvider r5) {
        super(r4);
        this.rect = new RectF();
        this.mText = _UrlKt.FRAGMENT_ENCODE_SET;
        Paint r42 = new Paint(1);
        this.outlinePaint = r42;
        TextPaint r1 = new TextPaint(1);
        this.textPaint = r1;
        this.selectionSpring = new SpringAnimation(this, SELECTION_PROGRESS_PROPERTY);
        this.titleSpring = new SpringAnimation(this, TITLE_PROGRESS_PROPERTY);
        this.errorSpring = new SpringAnimation(this, ERROR_PROGRESS_PROPERTY);
        this.strokeWidthRegular = Math.max(2, AndroidUtilities.dp(0.5f));
        this.strokeWidthSelected = AndroidUtilities.dp(1.6667f);
        this.resourcesProvider = r5;
        setWillNotDraw(false);
        r1.setTextSize(AndroidUtilities.dp(16.0f));
        r1.setTypeface(AndroidUtilities.regular());
        r42.setStyle(Paint.Style.STROKE);
        r42.setStrokeCap(Paint.Cap.ROUND);
        r42.setStrokeWidth(this.strokeWidthRegular);
        updateColor();
        setPadding(0, AndroidUtilities.dp(6.0f), 0, 0);
    }

    private void animateSpring(SpringAnimation r2, float r3) {
        float r32 = r3 * 100.0f;
        if (r2.getSpring() != null) goto L5;
    L7:
        r2.cancel();
        r2.setSpring(new SpringForce(r32).setStiffness(500.0f).setDampingRatio(1.0f).setFinalPosition(r32)).start();
        return;
    L5:
        if (r32 != r2.getSpring().getFinalPosition()) goto L7;
    }

    private void setColor(int r2) {
        this.outlinePaint.setColor(r2);
        invalidate();
    }

    public void animateError(float r2) {
        animateSpring(this.errorSpring, r2);
    }

    public void animateSelection(float r2) {
        animateSelection(r2, r2, true);
    }

    public void animateSelection(float r2, float r3, boolean r4) {
        if (r4 == true) goto L8;
        this.selectionProgress = r2;
        this.titleProgress = r3;
        if (this.forceUseCenter == true) goto L6;
        Paint r32 = this.outlinePaint;
        float r42 = this.strokeWidthRegular;
        r32.setStrokeWidth(r42 + ((this.strokeWidthSelected - r42) * r2));
    L6:
        updateColor();
        return;
    L8:
        animateSpring(this.selectionSpring, r2);
        animateSpring(this.titleSpring, r3);
    }

    public void animateSelection(float r1, boolean r2) {
        animateSelection(r1, r1, r2);
    }

    public void animateSelection(boolean r3, boolean r4) {
        float r0 = 0.0f;
        if (r3 == false) goto L5;
        float r32 = 1.0f;
    L6:
        if (r4 == false) goto L8;
        r0 = 1.0f;
    L8:
        animateSelection(r32, r0, true);
        return;
    L5:
        r32 = 0.0f;
        goto L6
    }

    public void animateSelection(boolean r3, boolean r4, boolean r5) {
        float r0 = 0.0f;
        if (r3 == false) goto L5;
        float r32 = 1.0f;
    L6:
        if (r4 == false) goto L8;
        r0 = 1.0f;
    L8:
        animateSelection(r32, r0, r5);
        return;
    L5:
        r32 = 0.0f;
        goto L6
    }

    public void attachEditText(EditText r1) {
        this.attachedEditText = r1;
        invalidate();
    }

    public EditText getAttachedEditText() {
        return this.attachedEditText;
    }

    @Override // android.view.View
    public void onDraw(Canvas r19) {
        super.onDraw(r19);
        float r4 = getPaddingTop() + ((this.textPaint.getTextSize() / 2.0f) - AndroidUtilities.dp(1.75f));
        float r2 = (getHeight() / 2.0f) + (this.textPaint.getTextSize() / 2.0f);
        EditText r5 = this.attachedEditText;
        if (r5 == null) goto L9;
        if (r5.length() != 0) goto L9;
        if (TextUtils.isEmpty(this.attachedEditText.getHint()) == false) goto L9;
    L15:
        boolean r52 = true;
    L14:
        boolean r7 = r52;
        if (r7 == false) goto L19;
        r4 = r4 + ((r2 - r4) * (1.0f - this.titleProgress));
    L19:
        float r9 = r4;
        if (r7 == false) goto L23;
        float r22 = this.leftPadding * (1.0f - this.titleProgress);
    L22:
        float r10 = r22;
        float r23 = this.outlinePaint.getStrokeWidth();
        float r42 = 0.75f;
        if (r7 == false) goto L27;
        r42 = 0.75f + ((1.0f - this.titleProgress) * 0.25f);
    L27:
        float r11 = r42;
        float r43 = this.textPaint.measureText(this.mText) * r11;
        r19.save();
        this.rect.set(getPaddingLeft() + AndroidUtilities.dp(10.0f), getPaddingTop(), (getWidth() - AndroidUtilities.dp(18.0f)) - getPaddingRight(), getPaddingTop() + (r23 * 2.0f));
        r19.clipRect(this.rect, Region.Op.DIFFERENCE);
        this.rect.set(getPaddingLeft() + r23, getPaddingTop() + r23, (getWidth() - r23) - getPaddingRight(), (getHeight() - r23) - getPaddingBottom());
        r19.drawRoundRect(this.rect, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.outlinePaint);
        r19.restore();
        float r8 = getPaddingLeft() + AndroidUtilities.dp(10.0f);
        float r3 = getPaddingTop() + r23;
        float r53 = ((getWidth() - r23) - getPaddingRight()) - AndroidUtilities.dp(6.0f);
        float r12 = r8 + (r43 / 2.0f);
        float r24 = ((r8 + r43) + AndroidUtilities.dp(10.0f)) - r12;
        if (r7 == false) goto L30;
        float r44 = this.titleProgress;
    L31:
        r19.drawLine((r24 * r44) + r12, r3, r53, r3, this.outlinePaint);
        float r122 = r12 + AndroidUtilities.dp(4.0f);
        float r1 = r8 - r122;
        if (r7 == false) goto L34;
        float r17 = this.titleProgress;
    L35:
        r19.drawLine(r8, r3, r122 + (r1 * r17), r3, this.outlinePaint);
        r19.save();
        r19.scale(r11, r11, getPaddingLeft() + AndroidUtilities.dp(18.0f), r9);
        r19.drawText(this.mText, (getPaddingLeft() + AndroidUtilities.dp(14.0f)) + r10, r9, this.textPaint);
        r19.restore();
        return;
    L34:
        r17 = 1.0f;
        goto L35
    L30:
        r44 = 1.0f;
        goto L31
    L23:
        r22 = 0.0f;
    L9:
        if (this.forceUseCenter == true) goto L15;
        if (this.forceUseCenter2 == true) goto L15;
        r52 = false;
        goto L14
    }

    public void setForceForceUseCenter(boolean r1) {
        this.forceUseCenter = r1;
        this.forceForceUseCenter = r1;
        invalidate();
    }

    public void setForceUseCenter(boolean r1) {
        this.forceUseCenter = r1;
        invalidate();
    }

    public void setForceUseCenter2(boolean r1) {
        this.forceUseCenter2 = r1;
    }

    public void setLeftPadding(float r1) {
        this.leftPadding = r1;
        invalidate();
    }

    public void setText(String r1) {
        this.mText = r1;
        invalidate();
    }

    public void setUseDefaultColor(boolean r1) {
        this.useDefaultColor = r1;
        invalidate();
    }

    public void updateColor() {
        int r1 = -14606047;
        if (this.useDefaultColor == false) goto L5;
        int r0 = -14606047;
    L6:
        int r3 = -15095832;
        if (this.useDefaultColor == false) goto L9;
        int r2 = -15095832;
    L10:
        float r5 = 0.0f;
        if (this.forceUseCenter == true) goto L13;
    L15:
        float r4 = this.titleProgress;
    L16:
        int r02 = ColorUtils.blendARGB(r0, r2, r4);
        if (this.useDefaultColor == true) goto L21;
        r1 = Theme.getColor(Theme.key_windowBackgroundWhiteInputField, this.resourcesProvider);
    L21:
        if (this.useDefaultColor == true) goto L25;
        r3 = Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, this.resourcesProvider);
    L25:
        if (this.forceUseCenter == true) goto L27;
    L29:
        r5 = this.selectionProgress;
    L30:
        int r12 = ColorUtils.blendARGB(r1, r3, r5);
        TextPaint r22 = this.textPaint;
        int r32 = Theme.key_text_RedBold;
        r22.setColor(ColorUtils.blendARGB(r02, Theme.getColor(r32, this.resourcesProvider), this.errorProgress));
        setColor(ColorUtils.blendARGB(r12, Theme.getColor(r32, this.resourcesProvider), this.errorProgress));
        return;
    L27:
        if (this.forceForceUseCenter == true) goto L29;
    L13:
        if (this.forceForceUseCenter == true) goto L15;
        r4 = 0.0f;
        goto L16
    L9:
        r2 = Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, this.resourcesProvider);
        goto L10
    L5:
        r0 = Theme.getColor(Theme.key_windowBackgroundWhiteHintText, this.resourcesProvider);
        goto L6
    }
}
