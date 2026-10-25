package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class PreviewButtons extends FrameLayout {
    private ValueAnimator appearAnimator;
    private float appearT;
    private boolean appearing;
    private ArrayList<ButtonView> buttons;
    private boolean isShareEnabled;
    private Utilities.Callback<Integer> onClickListener;
    private View shadowView;
    private boolean shareArrow;
    public ShareButtonView shareButton;
    private String shareText;

    public class ButtonView extends ImageView {
        public final int id;
        final /* synthetic */ PreviewButtons this$0;

        /* renamed from: $r8$lambda$X61UEv-BdMu-CWepUIy8T5K_D2s, reason: not valid java name */
        public static /* synthetic */ void m20146$r8$lambda$X61UEvBdMuCWepUIy8T5K_D2s(ButtonView r0, int r1, View r2) {
            r0.lambda$new$0(r1, r2);
        }

        public ButtonView(PreviewButtons r1, Context r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2);
            this.id = r3;
            setBackground(Theme.createSelectorDrawable(1090519039));
            setScaleType(ImageView.ScaleType.CENTER);
            setImageResource(r4);
            setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
            setOnClickListener(new PreviewButtons$ButtonView$$ExternalSyntheticLambda0(this, r3));
        }

        private /* synthetic */ void lambda$new$0(int r1, View r2) {
            if (PreviewButtons.m20144$$Nest$fgetappearing(this.this$0) == true) goto L5;
            return;
        L5:
            if (PreviewButtons.m20145$$Nest$fgetonClickListener(this.this$0) == null) goto L9;
            PreviewButtons.m20145$$Nest$fgetonClickListener(this.this$0).run(Integer.valueOf(r1));
            return;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setClassName("android.widget.Button");
        }

        @Override // android.widget.ImageView, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
        }
    }

    public class ShareButtonView extends View {
        private boolean arrow;
        ValueAnimator backAnimator;
        private final Paint buttonPaint;
        private final Paint darkenPaint;
        public boolean enabled;
        private AnimatedFloat enabledT;
        private int h;
        private float left;
        float pressedProgress;
        private final StaticLayout staticLayout;
        private final TextPaint textPaint;
        final /* synthetic */ PreviewButtons this$0;
        private int w;
        private float width;

        /* renamed from: org.telegram.ui.Stories.recorder.PreviewButtons$ShareButtonView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ ShareButtonView this$1;

            public AnonymousClass1(ShareButtonView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$1.backAnimator = null;
            }
        }

        /* renamed from: $r8$lambda$FWZsgku8O-BUNBzWhCX6bAcjt4g, reason: not valid java name */
        public static /* synthetic */ void m20147$r8$lambda$FWZsgku8OBUNBzWhCX6bAcjt4g(ShareButtonView r0, ValueAnimator r1) {
            r0.lambda$setPressed$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$UtyujDRyVkeKmGCfTSDkcemx80s(ShareButtonView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public ShareButtonView(PreviewButtons r11, Context r12, String r13, boolean r14) {
            this.this$0 = r11;
            super(r12);
            TextPaint r2 = new TextPaint(1);
            this.textPaint = r2;
            Paint r122 = new Paint(1);
            this.buttonPaint = r122;
            Paint r0 = new Paint(1);
            this.darkenPaint = r0;
            this.enabledT = new AnimatedFloat(this, 0, 220, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.enabled = true;
            this.arrow = r14;
            r122.setColor(-15098625);
            r0.setColor(1610612736);
            r2.setTextSize(AndroidUtilities.dp(13.0f));
            r2.setColor(-1);
            r2.setTypeface(AndroidUtilities.bold());
            if (r14 == false) goto L9;
            SpannableString r02 = new SpannableString(">");
            Drawable r1 = getResources().getDrawable(R.drawable.attach_arrow_right).mutate();
            r1.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            r1.setBounds(0, 0, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
            r02.setSpan(new ImageSpan(r1, 2), 0, r02.length(), 33);
            if (LocaleController.isRTL == false) goto L7;
            CharSequence r112 = new SpannableStringBuilder(r02).append("\u2009").append(r13.toUpperCase());
        L10:
            StaticLayout r03 = new StaticLayout(r112, r2, AndroidUtilities.dp(180.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.staticLayout = r03;
            float r132 = 0.0f;
            if (r03.getLineCount() <= 0) goto L13;
            float r113 = r03.getLineLeft(0);
        L14:
            this.left = r113;
            if (r03.getLineCount() <= 0) goto L17;
            r132 = r03.getLineWidth(0);
        L17:
            this.width = r132;
            this.w = ((int) r132) + AndroidUtilities.dp(48.0f);
            if (r14 == true) goto L20;
            this.w = Math.max(AndroidUtilities.dp(80.0f), this.w);
        L20:
            this.h = AndroidUtilities.dp(40.0f);
            setOnClickListener(new PreviewButtons$ShareButtonView$$ExternalSyntheticLambda0(this));
            return;
        L13:
            r113 = 0.0f;
            goto L14
        L7:
            r112 = new SpannableStringBuilder(r13.toUpperCase()).append("\u2009").append(r02);
            goto L10
        L9:
            r112 = r13.toUpperCase();
            goto L10
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            if (PreviewButtons.m20144$$Nest$fgetappearing(this.this$0) == true) goto L5;
            return;
        L5:
            if (PreviewButtons.m20145$$Nest$fgetonClickListener(this.this$0) == null) goto L9;
            PreviewButtons.m20145$$Nest$fgetonClickListener(this.this$0).run(5);
            return;
        }

        private /* synthetic */ void lambda$setPressed$1(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            float r8 = 0.0f;
            if (isPressed() == false) goto L7;
            float r1 = this.pressedProgress;
            if (r1 == 1.0f) goto L7;
            float r12 = r1 + (Math.min(40.0f, 1000.0f / AndroidUtilities.screenRefreshRate) / 80.0f);
            this.pressedProgress = r12;
            this.pressedProgress = Utilities.clamp(r12, 1.0f, 0.0f);
            invalidate();
        L7:
            AnimatedFloat r14 = this.enabledT;
            if (this.enabled == false) goto L10;
            float r2 = 1.0f;
        L11:
            float r15 = r14.set(r2);
            int r10 = r13.getSaveCount();
            if (r15 >= 1.0f) goto L14;
            r13.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r15 * 255.0f), 31);
        L14:
            float r9 = ((1.0f - this.pressedProgress) * 0.1f) + 0.9f;
            r13.save();
            r13.scale(r9, r9, getWidth() / 2.0f, getHeight() / 2.0f);
            r13.drawRect(AndroidUtilities.dp(25.0f), AndroidUtilities.dp(4.0f), getWidth() - AndroidUtilities.dp(25.0f), getHeight() - AndroidUtilities.dp(4.0f), this.darkenPaint);
            r13.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(4.0f), getWidth() - AndroidUtilities.dp(10.0f), getHeight() - AndroidUtilities.dp(4.0f));
            r13.drawRoundRect(r22, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), this.buttonPaint);
            r13.save();
            float r23 = (this.w - this.width) / 2.0f;
            if (this.arrow == false) goto L17;
            r8 = 3.0f;
        L17:
            r13.translate((r23 + AndroidUtilities.dp(r8)) - this.left, (getHeight() - this.staticLayout.getHeight()) / 2.0f);
            this.staticLayout.draw(r13);
            r13.restore();
            r13.restoreToCount(r10);
            return;
        L10:
            r2 = 0.5f;
            goto L11
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setClassName("android.widget.Button");
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.w, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.h, TLObject.FLAG_30));
        }

        @Override // android.view.View
        public void setPressed(boolean r4) {
            if (isPressed() == r4) goto L14;
            super.setPressed(r4);
            invalidate();
            if (r4 == false) goto L9;
            ValueAnimator r0 = this.backAnimator;
            if (r0 == null) goto L9;
            r0.removeAllListeners();
            this.backAnimator.cancel();
        L9:
            if (r4 == true) goto L15;
            float r42 = this.pressedProgress;
            if (r42 == 0.0f) goto L16;
            ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, 0.0f});
            this.backAnimator = r43;
            r43.addUpdateListener(new PreviewButtons$ShareButtonView$$ExternalSyntheticLambda1(this));
            this.backAnimator.addListener(new AnonymousClass1(this));
            this.backAnimator.setInterpolator(new OvershootInterpolator(1.5f));
            this.backAnimator.setDuration(350);
            this.backAnimator.start();
            return;
        L16:
            return;
        L15:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$SuPhkGr6CKbKlEoM3BMFGZEUMbg(PreviewButtons r0, ValueAnimator r1) {
        r0.lambda$appear$0(r1);
    }

    /* renamed from: -$$Nest$fgetappearing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20144$$Nest$fgetappearing(PreviewButtons r0) {
        return r0.appearing;
    }

    /* renamed from: -$$Nest$fgetonClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m20145$$Nest$fgetonClickListener(PreviewButtons r0) {
        return r0.onClickListener;
    }

    public PreviewButtons(Context r7) {
        super(r7);
        this.buttons = new ArrayList();
        this.shareArrow = true;
        this.isShareEnabled = true;
        View r1 = new View(r7);
        this.shadowView = r1;
        r1.setBackground(new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{1711276032, 0}));
        addView(this.shadowView, LayoutHelper.createFrame(-1, -1, 119));
        addButton(0, R.drawable.media_draw, LocaleController.getString(R.string.AccDescrPaint));
        addButton(2, R.drawable.msg_photo_sticker, LocaleController.getString(R.string.AccDescrStickers));
        addButton(1, R.drawable.msg_photo_text2, LocaleController.getString(R.string.AccDescrPlaceText));
        addButton(3, R.drawable.media_crop, LocaleController.getString(R.string.Crop));
        addButton(4, R.drawable.msg_photo_settings, LocaleController.getString(R.string.AccDescrPhotoAdjust));
        String r2 = LocaleController.getString(R.string.Send);
        this.shareText = r2;
        this.shareArrow = true;
        ShareButtonView r12 = new ShareButtonView(this, r7, r2, true);
        this.shareButton = r12;
        r12.setContentDescription(LocaleController.getString(R.string.Send));
        addView(this.shareButton, LayoutHelper.createFrame(-2, -2.0f));
        updateAppearT();
    }

    private void addButton(int r3, int r4, CharSequence r5) {
        ButtonView r0 = new ButtonView(this, getContext(), r3, r4);
        r0.setContentDescription(r5);
        this.buttons.add(r0);
        addView(r0);
    }

    private boolean isButtonVisible(int r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.buttons.size()) goto L13;
        ButtonView r2 = this.buttons.get(r1);
        if (r2.id == r5) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        if (r2.getVisibility() != 0) goto L11;
        return true;
    L11:
        return false;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$appear$0(ValueAnimator r1) {
        this.appearT = ((Float) r1.getAnimatedValue()).floatValue();
        updateAppearT();
    }

    private void updateAppearT() {
        this.shadowView.setAlpha(this.appearT);
        this.shadowView.setTranslationY((1.0f - this.appearT) * AndroidUtilities.dp(16.0f));
        int r1 = 1;
    L4:
        if (r1 >= getChildCount()) goto L9;
        View r3 = getChildAt(r1);
        float r4 = this.appearT;
        if (this.appearing == false) goto L8;
        r4 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(AndroidUtilities.cascade(r4, r1 - 1, getChildCount() - 1, 3.0f));
    L8:
        r3.setAlpha(r4);
        r3.setTranslationY((1.0f - r4) * AndroidUtilities.dp(24.0f));
        r1 = r1 + 1;
        goto L4
    }

    public void appear(boolean r3, boolean r4) {
        if (this.appearing != r3) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.appearAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.appearing = r3;
        float r02 = 0.0f;
        if (r4 == false) goto L19;
        float r42 = this.appearT;
        if (r3 == false) goto L13;
        r02 = 1.0f;
    L13:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r42, r02});
        this.appearAnimator = r32;
        r32.addUpdateListener(new PreviewButtons$$ExternalSyntheticLambda0(this));
        boolean r33 = this.appearing;
        ValueAnimator r43 = this.appearAnimator;
        if (r33 == false) goto L16;
        r43.setDuration(450);
        this.appearAnimator.setInterpolator(new LinearInterpolator());
    L17:
        this.appearAnimator.start();
        return;
    L16:
        r43.setDuration(350);
        this.appearAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        goto L17
    L19:
        if (r3 == false) goto L21;
        r02 = 1.0f;
    L21:
        this.appearT = r02;
        updateAppearT();
    }

    public boolean isShareEnabled() {
        return this.isShareEnabled;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        int r92 = r9 - r7;
        int r102 = r10 - r8;
        int r72 = 0;
        this.shadowView.layout(0, 0, r92, r102);
        ShareButtonView r62 = this.shareButton;
        r62.layout(r92 - r62.getMeasuredWidth(), (r102 - this.shareButton.getMeasuredHeight()) / 2, r92, (this.shareButton.getMeasuredHeight() + r102) / 2);
        int r93 = (r92 - AndroidUtilities.dp(32.33f)) - this.shareButton.getMeasuredWidth();
        int r63 = 0;
        int r82 = 0;
    L4:
        if (r63 >= this.buttons.size()) goto L10;
        if (this.buttons.get(r63).getVisibility() != 0) goto L8;
        r82 = r82 + 1;
    L8:
        r63 = r63 + 1;
        goto L4
    L10:
        if (r82 >= 2) goto L12;
        int r0 = 0;
    L14:
        if (isButtonVisible(4) == false) goto L16;
        float r3 = 20.0f;
    L17:
        int r02 = Math.min(AndroidUtilities.dp(r3), r0);
        int r32 = (r102 - AndroidUtilities.dp(40.0f)) / 2;
        int r103 = (r102 + AndroidUtilities.dp(40.0f)) / 2;
        int r4 = AndroidUtilities.dp(12.33f);
        if (isButtonVisible(4) == true) goto L20;
        int r94 = ((r93 - (AndroidUtilities.dp(40.0f) * r82)) - ((r82 - 1) * r02)) / 2;
    L21:
        int r42 = r4 + r94;
    L23:
        if (r72 >= this.buttons.size()) goto L29;
        if (this.buttons.get(r72).getVisibility() != 0) goto L28;
        this.buttons.get(r72).layout(r42, r32, AndroidUtilities.dp(40.0f) + r42, r103);
        r42 = r42 + (AndroidUtilities.dp(40.0f) + r02);
    L28:
        r72 = r72 + 1;
        goto L23
    L29:
        return;
    L20:
        r94 = 0;
        goto L21
    L16:
        r3 = 30.0f;
        goto L17
    L12:
        r0 = (r93 - (AndroidUtilities.dp(40.0f) * r82)) / (r82 - 1);
        goto L14
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
    }

    public void setButtonVisible(int r5, boolean r6) {
        int r1 = 0;
    L4:
        if (r1 >= this.buttons.size()) goto L12;
        ButtonView r2 = this.buttons.get(r1);
        if (r2.id != r5) goto L11;
        if (r6 == false) goto L9;
        int r3 = 0;
    L10:
        r2.setVisibility(r3);
        goto L11
    L9:
        r3 = 8;
    L11:
        r1 = r1 + 1;
        goto L4
    }

    public void setOnClickListener(Utilities.Callback<Integer> r1) {
        this.onClickListener = r1;
    }

    public void setShareEnabled(boolean r2) {
        if (this.isShareEnabled == r2) goto L6;
        this.isShareEnabled = r2;
        ShareButtonView r1 = this.shareButton;
        r1.enabled = r2;
        r1.invalidate();
        return;
    }

    public void setShareText(String r3, boolean r4) {
        if (TextUtils.equals(r3, this.shareText) == true) goto L5;
    L7:
        removeView(this.shareButton);
        Context r1 = getContext();
        this.shareText = r3;
        this.shareArrow = r4;
        ShareButtonView r0 = new ShareButtonView(this, r1, r3, r4);
        this.shareButton = r0;
        r0.setContentDescription(r3);
        addView(this.shareButton, LayoutHelper.createFrame(-2, -2.0f));
        updateAppearT();
        return;
    L5:
        if (r4 != this.shareArrow) goto L7;
    }
}
