package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes6.dex */
public class TextSettingsCell extends FrameLayout {
    private boolean betterLayout;
    private boolean canDisable;
    private int changeProgressStartDelay;
    private boolean drawLoading;
    private float drawLoadingProgress;
    private ImageView imageView;
    private boolean imageViewIsColorful;
    private boolean incrementLoadingProgress;
    private float loadingProgress;
    private int loadingSize;
    private boolean measureDelay;
    public boolean needDivider;
    private int padding;
    Paint paint;
    private Theme.ResourcesProvider resourcesProvider;
    private TextView textView;
    private BackupImageView valueBackupImageView;
    private ImageView valueImageView;
    private AnimatedTextView valueTextView;

    public TextSettingsCell(Context r2) {
        this(r2, 21);
    }

    public TextSettingsCell(Context r2, int r3) {
        this(r2, r3, null);
    }

    public TextSettingsCell(Context r22, int r23, Theme.ResourcesProvider r24) {
        super(r22);
        this.betterLayout = BuildVars.DEBUG_PRIVATE_VERSION;
        this.resourcesProvider = r24;
        this.padding = r23;
        TextView r4 = new TextView(r22);
        this.textView = r4;
        r4.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r42 = this.textView;
        int r8 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r7 = 5;
    L6:
        r42.setGravity(r7 | 16);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r24));
        TextView r43 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r72 = 5;
    L10:
        float r13 = r23;
        addView(r43, LayoutHelper.createFrame(-1, -1.0f, r72 | 48, r13, 0.0f, r13, 0.0f));
        AnimatedTextView r14 = new AnimatedTextView(r22, true, true, !LocaleController.isRTL);
        this.valueTextView = r14;
        r14.setAnimationProperties(0.55f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.valueTextView.setTextSize(AndroidUtilities.dp(16.0f));
        AnimatedTextView r44 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r5 = 3;
    L14:
        r44.setGravity(r5 | 16);
        this.valueTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText, r24));
        AnimatedTextView r45 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L17;
        int r52 = 3;
    L18:
        float r2 = r23 - 4;
        addView(r45, LayoutHelper.createFrame(-2, -1.0f, r52 | 48, r2, 0.0f, r2, 0.0f));
        RLottieImageView r25 = new RLottieImageView(r22);
        this.imageView = r25;
        ImageView.ScaleType r46 = ImageView.ScaleType.CENTER;
        r25.setScaleType(r46);
        ImageView r26 = this.imageView;
        int r73 = Theme.key_windowBackgroundWhiteGrayIcon;
        int r10 = Theme.getColor(r73, r24);
        PorterDuff.Mode r11 = PorterDuff.Mode.MULTIPLY;
        r26.setColorFilter(new PorterDuffColorFilter(r10, r11));
        this.imageView.setVisibility(8);
        ImageView r27 = this.imageView;
        if (LocaleController.isRTL == false) goto L21;
        int r6 = 5;
    L22:
        addView(r27, LayoutHelper.createFrame(-2, -2.0f, r6 | 16, 16.0f, 0.0f, 16.0f, 0.0f));
        ImageView r28 = new ImageView(r22);
        this.valueImageView = r28;
        r28.setScaleType(r46);
        this.valueImageView.setVisibility(4);
        this.valueImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r73, r24), r11));
        ImageView r1 = this.valueImageView;
        if (LocaleController.isRTL == true) goto L26;
        r8 = 5;
    L26:
        addView(r1, LayoutHelper.createFrame(-2, -2.0f, r8 | 16, r13, 0.0f, r13, 0.0f));
        return;
    L21:
        r6 = 3;
        goto L22
    L17:
        r52 = 5;
        goto L18
    L13:
        r5 = 5;
        goto L14
    L9:
        r72 = 3;
        goto L10
    L5:
        r7 = 3;
        goto L6
    }

    public TextSettingsCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, 21, r3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r12) {
        int r1 = 0;
        float r3 = 1.0f;
        float r4 = 0.0f;
        if (this.drawLoading == true) goto L7;
        if (this.drawLoadingProgress != 0.0f) goto L7;
    L37:
        AnimatedTextView r0 = this.valueTextView;
        float r5 = 1.0f - this.drawLoadingProgress;
        if (isEnabled() == false) goto L40;
    L43:
        r0.setAlpha(r5 * r3);
        super.dispatchDraw(r12);
        if (this.needDivider == true) goto L46;
        return;
    L46:
        if (this.imageView.getVisibility() != 0) goto L48;
        float r02 = 58.0f;
    L49:
        int r03 = AndroidUtilities.dp(r02);
        if (LocaleController.isRTL == false) goto L52;
    L51:
        float r6 = r4;
        float r7 = getMeasuredHeight() - 1;
        int r32 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L56;
        r1 = r03;
    L56:
        r12.drawLine(r6, r7, r32 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L52:
        r4 = r03;
        goto L51
    L48:
        r02 = 20.0f;
        goto L49
    L40:
        if (this.canDisable == false) goto L43;
        r3 = 0.5f;
    L7:
        if (this.paint != null) goto L9;
        Paint r04 = new Paint(1);
        this.paint = r04;
        r04.setColor(Theme.getColor(Theme.key_dialogSearchBackground, this.resourcesProvider));
    L9:
        boolean r05 = this.incrementLoadingProgress;
        float r52 = this.loadingProgress;
        if (r05 == false) goto L14;
        float r53 = r52 + 0.016f;
        this.loadingProgress = r53;
        if (r53 <= 1.0f) goto L17;
        this.loadingProgress = 1.0f;
        this.incrementLoadingProgress = false;
    L17:
        int r06 = this.changeProgressStartDelay;
        if (r06 <= 0) goto L20;
        this.changeProgressStartDelay = r06 - 15;
    L33:
        this.paint.setAlpha((int) ((((this.loadingProgress * 0.4f) + 0.6f) * this.drawLoadingProgress) * 255.0f));
        int r07 = getMeasuredHeight() >> 1;
        RectF r54 = AndroidUtilities.rectTmp;
        r54.set((getMeasuredWidth() - AndroidUtilities.dp(this.padding)) - AndroidUtilities.dp(this.loadingSize), r07 - AndroidUtilities.dp(3.0f), getMeasuredWidth() - AndroidUtilities.dp(this.padding), r07 + AndroidUtilities.dp(3.0f));
        if (LocaleController.isRTL == false) goto L36;
        r54.left = getMeasuredWidth() - r54.left;
        r54.right = getMeasuredWidth() - r54.right;
    L36:
        r12.drawRoundRect(r54, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.paint);
        invalidate();
        goto L37
    L20:
        boolean r08 = this.drawLoading;
        if (r08 == false) goto L27;
        float r62 = this.drawLoadingProgress;
        if (r62 == 1.0f) goto L27;
        float r63 = r62 + 0.10666667f;
        this.drawLoadingProgress = r63;
        if (r63 <= 1.0f) goto L33;
        this.drawLoadingProgress = 1.0f;
    L27:
        if (r08 == true) goto L33;
        float r09 = this.drawLoadingProgress;
        if (r09 == 0.0f) goto L33;
        float r010 = r09 - 0.10666667f;
        this.drawLoadingProgress = r010;
        if (r010 >= 0.0f) goto L33;
        this.drawLoadingProgress = 0.0f;
        goto L33
    L14:
        float r55 = r52 - 0.016f;
        this.loadingProgress = r55;
        if (r55 >= 0.0f) goto L17;
        this.loadingProgress = 0.0f;
        this.incrementLoadingProgress = true;
        goto L17
    }

    public TextView getTextView() {
        return this.textView;
    }

    public BackupImageView getValueBackupImageView() {
        if (this.valueBackupImageView != null) goto L10;
        BackupImageView r0 = new BackupImageView(getContext());
        this.valueBackupImageView = r0;
        if (LocaleController.isRTL == false) goto L7;
        int r1 = 3;
    L8:
        int r4 = r1 | 16;
        int r12 = this.padding;
        addView(r0, LayoutHelper.createFrame(24, 24.0f, r4, r12 - 4, 0.0f, r12 - 4, 0.0f));
        goto L10
    L7:
        r1 = 5;
    L10:
        return this.valueBackupImageView;
    }

    public ImageView getValueImageView() {
        return this.valueImageView;
    }

    public AnimatedTextView getValueTextView() {
        return this.valueTextView;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BackupImageView r0 = this.valueBackupImageView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getImageReceiver() != null) goto L7;
        return;
    L7:
        if ((this.valueBackupImageView.getImageReceiver().getDrawable() instanceof AnimatedEmojiDrawable) == false) goto L12;
        ((AnimatedEmojiDrawable) this.valueBackupImageView.getImageReceiver().getDrawable()).removeView(this);
        return;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        StringBuilder r0 = new StringBuilder();
        r0.append(this.textView.getText());
        AnimatedTextView r1 = this.valueTextView;
        if (r1 != null) goto L5;
    L7:
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r0.append(r12);
        r4.setText(r0.toString());
        r4.setEnabled(isEnabled());
        return;
    L5:
        if (r1.getVisibility() != 0) goto L7;
        r12 = "\n" + this.valueTextView.getText();
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (this.measureDelay == true) goto L5;
        return;
    L5:
        if (getParent() == null) goto L9;
        this.changeProgressStartDelay = (int) ((getTop() / ((View) getParent()).getMeasuredHeight()) * 150.0f);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        setMeasuredDimension(View.MeasureSpec.getSize(r7), AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0));
        int r72 = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(34.0f);
        if (this.betterLayout == false) goto L5;
        int r82 = r72;
    L7:
        if (this.valueImageView.getVisibility() != 0) goto L10;
        this.valueImageView.measure(View.MeasureSpec.makeMeasureSpec(r82, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
    L10:
        if (this.imageView.getVisibility() != 0) goto L18;
        boolean r0 = this.imageViewIsColorful;
        ImageView r4 = this.imageView;
        if (r0 == false) goto L14;
        r4.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30));
    L16:
        if (this.betterLayout == false) goto L18;
        r82 = r82 - (this.imageView.getMeasuredWidth() + AndroidUtilities.dp(8.0f));
        goto L18
    L14:
        r4.measure(View.MeasureSpec.makeMeasureSpec(r82, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE));
    L18:
        BackupImageView r02 = this.valueBackupImageView;
        if (r02 == null) goto L24;
        r02.measure(View.MeasureSpec.makeMeasureSpec(r02.getLayoutParams().height, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.valueBackupImageView.getLayoutParams().width, TLObject.FLAG_30));
        if (this.betterLayout == false) goto L24;
        r82 = r82 - (this.valueBackupImageView.getMeasuredWidth() + AndroidUtilities.dp(8.0f));
    L24:
        if (this.valueTextView.getVisibility() != 0) goto L35;
        this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(r82, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        boolean r03 = this.betterLayout;
        AnimatedTextView r1 = this.valueTextView;
        if (r03 == false) goto L28;
        r72 = r82 - (r1.getMeasuredWidth() + AndroidUtilities.dp(8.0f));
    L30:
        if (this.valueImageView.getVisibility() != 0) goto L35;
        ViewGroup.MarginLayoutParams r83 = (ViewGroup.MarginLayoutParams) this.valueImageView.getLayoutParams();
        boolean r04 = LocaleController.isRTL;
        int r12 = this.padding;
        if (r04 == false) goto L34;
        r83.leftMargin = AndroidUtilities.dp(r12 + 4) + this.valueTextView.getMeasuredWidth();
        goto L35
    L34:
        r83.rightMargin = AndroidUtilities.dp(r12 + 4) + this.valueTextView.getMeasuredWidth();
        goto L35
    L28:
        r72 = (r72 - r1.getMeasuredWidth()) - AndroidUtilities.dp(8.0f);
    L35:
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        return;
    L5:
        r82 = r72 / 2;
        goto L7
    }

    public void setBetterLayout(boolean r1) {
        this.betterLayout = r1;
    }

    public void setCanDisable(boolean r1) {
        this.canDisable = r1;
    }

    public void setDrawLoading(boolean r1, int r2, boolean r3) {
        this.drawLoading = r1;
        this.loadingSize = r2;
        if (r3 == true) goto L8;
        if (r1 == false) goto L6;
        float r12 = 1.0f;
    L7:
        this.drawLoadingProgress = r12;
    L9:
        invalidate();
        return;
    L6:
        r12 = 0.0f;
        goto L7
    L8:
        this.measureDelay = true;
        goto L9
    }

    @Override // android.view.View
    public void setEnabled(boolean r5) {
        super.setEnabled(r5);
        TextView r0 = this.textView;
        float r1 = 1.0f;
        if (r5 == false) goto L5;
    L8:
        float r3 = 1.0f;
    L9:
        r0.setAlpha(r3);
        if (this.valueTextView.getVisibility() != 0) goto L20;
        AnimatedTextView r02 = this.valueTextView;
        if (r5 == false) goto L14;
    L17:
        float r32 = 1.0f;
    L18:
        r02.setAlpha(r32);
        goto L20
    L14:
        if (this.canDisable == false) goto L17;
        r32 = 0.5f;
    L20:
        if (this.valueImageView.getVisibility() != 0) goto L29;
        ImageView r03 = this.valueImageView;
        if (r5 == false) goto L24;
    L27:
        r03.setAlpha(r1);
        return;
    L24:
        if (this.canDisable == false) goto L27;
        r1 = 0.5f;
        goto L27
    L29:
        return;
    L5:
        if (this.canDisable == false) goto L8;
        r3 = 0.5f;
        goto L9
    }

    public void setEnabled(boolean r9, ArrayList<Animator> r10) {
        setEnabled(r9);
        TextView r0 = this.textView;
        float r1 = 0.5f;
        if (r10 == null) goto L21;
        if (r9 == false) goto L6;
        float r3 = 1.0f;
    L7:
        r10.add(ObjectAnimator.ofFloat(r0, "alpha", new float[]{r3}));
        if (this.valueTextView.getVisibility() != 0) goto L15;
        AnimatedTextView r02 = this.valueTextView;
        if (r9 == false) goto L12;
        float r5 = 1.0f;
    L13:
        r10.add(ObjectAnimator.ofFloat(r02, "alpha", new float[]{r5}));
        goto L15
    L12:
        r5 = 0.5f;
    L15:
        if (this.valueImageView.getVisibility() != 0) goto L38;
        ImageView r8 = this.valueImageView;
        if (r9 == false) goto L19;
        r1 = 1.0f;
    L19:
        r10.add(ObjectAnimator.ofFloat(r8, "alpha", new float[]{r1}));
        return;
    L38:
        return;
    L6:
        r3 = 0.5f;
        goto L7
    L21:
        if (r9 == false) goto L23;
        float r102 = 1.0f;
    L24:
        r0.setAlpha(r102);
        if (this.valueTextView.getVisibility() != 0) goto L32;
        AnimatedTextView r103 = this.valueTextView;
        if (r9 == false) goto L29;
        float r03 = 1.0f;
    L30:
        r103.setAlpha(r03);
        goto L32
    L29:
        r03 = 0.5f;
    L32:
        if (this.valueImageView.getVisibility() != 0) goto L39;
        ImageView r82 = this.valueImageView;
        if (r9 == false) goto L36;
        r1 = 1.0f;
    L36:
        r82.setAlpha(r1);
        return;
    L39:
        return;
    L23:
        r102 = 0.5f;
        goto L24
    }

    public void setIcon(int r6) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) this.textView.getLayoutParams();
        this.imageViewIsColorful = false;
        ImageView r2 = this.imageView;
        if (r6 != 0) goto L10;
        r2.setVisibility(8);
        boolean r62 = LocaleController.isRTL;
        int r5 = this.padding;
        if (r62 == false) goto L8;
        r0.rightMargin = AndroidUtilities.dp(r5);
        return;
    L8:
        r0.leftMargin = AndroidUtilities.dp(r5);
        return;
    L10:
        r2.setImageResource(r6);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
        this.imageView.setBackground(null);
        this.imageView.setVisibility(0);
        if (LocaleController.isRTL == false) goto L14;
        r0.rightMargin = AndroidUtilities.dp(58.0f);
        return;
    L14:
        r0.leftMargin = AndroidUtilities.dp(58.0f);
    }

    public void setText(CharSequence r2, boolean r3) {
        this.textView.setText(r2);
        this.valueTextView.setVisibility(4);
        this.valueImageView.setVisibility(4);
        this.needDivider = r3;
        setWillNotDraw(!r3);
    }

    public void setTextAndValue(CharSequence r2, CharSequence r3, boolean r4) {
        setTextAndValue(r2, r3, false, r4);
    }

    public void setTextAndValue(CharSequence r2, CharSequence r3, boolean r4, boolean r5) {
        this.textView.setText(r2);
        this.valueImageView.setVisibility(4);
        AnimatedTextView r22 = this.valueTextView;
        if (r3 == null) goto L5;
        r22.setText(r3, r4);
        this.valueTextView.setVisibility(0);
    L6:
        this.needDivider = r5;
        setWillNotDraw(!r5);
        requestLayout();
        return;
    L5:
        r22.setVisibility(4);
        goto L6
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }

    public void setTextValueColor(int r1) {
        this.valueTextView.setTextColor(r1);
    }

    public void setValue(CharSequence r1, boolean r2) {
        this.valueTextView.setText(r1, r2);
    }

    public void updateRTL() {
        TextView r0 = this.textView;
        int r2 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 5;
    L6:
        r0.setGravity(r1 | 16);
        removeView(this.textView);
        TextView r02 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r12 = 5;
    L10:
        int r6 = r12 | 48;
        int r13 = this.padding;
        addView(r02, LayoutHelper.createFrame(-1, -1.0f, r6, r13, 0.0f, r13, 0.0f));
        AnimatedTextView r03 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r14 = 3;
    L14:
        r03.setGravity(r14 | 16);
        removeView(this.valueTextView);
        AnimatedTextView r04 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L17;
        int r15 = 3;
    L18:
        int r62 = r15 | 48;
        int r16 = this.padding;
        addView(r04, LayoutHelper.createFrame(-2, -1.0f, r62, r16, 0.0f, r16, 0.0f));
        removeView(this.imageView);
        ImageView r05 = this.imageView;
        if (LocaleController.isRTL == false) goto L21;
        int r17 = 5;
    L22:
        addView(r05, LayoutHelper.createFrame(-2, -2.0f, r17 | 16, 21.0f, 0.0f, 21.0f, 0.0f));
        removeView(this.valueImageView);
        ImageView r06 = this.valueImageView;
        if (LocaleController.isRTL == true) goto L26;
        r2 = 5;
    L26:
        int r18 = this.padding;
        addView(r06, LayoutHelper.createFrame(-2, -2.0f, r2 | 16, r18, 0.0f, r18, 0.0f));
        return;
    L21:
        r17 = 3;
        goto L22
    L17:
        r15 = 5;
        goto L18
    L13:
        r14 = 5;
        goto L14
    L9:
        r12 = 3;
        goto L10
    L5:
        r1 = 3;
        goto L6
    }
}
