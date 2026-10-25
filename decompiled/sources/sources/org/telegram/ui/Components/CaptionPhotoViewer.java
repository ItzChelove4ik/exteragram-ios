package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.CaptionContainerView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public abstract class CaptionPhotoViewer extends CaptionContainerView {
    private final int SHOW_ONCE;
    private final ImageView addPhotoButton;
    private boolean addPhotoVisible;
    private ImageView aiButton;
    private AiButtonDrawable aiButtonIcon;
    public HintView2 aiHint;
    private final Runnable applyCaption;
    private BlurredBackgroundDrawable backgroundForCaptionButton;
    private final Runnable collapseMoveButton;
    private final HintView2 hint;
    private boolean isVideo;
    private final AnimatedFloat lineCountAnimated;
    private final AnimatedFloat moveButtonAnimated;
    private final ButtonBounce moveButtonBounce;
    private final RectF moveButtonBounds;
    private boolean moveButtonExpanded;
    private final AnimatedFloat moveButtonExpandedAnimated;
    private Drawable moveButtonIcon;
    private final AnimatedTextView.AnimatedTextDrawable moveButtonText;
    private boolean moveButtonVisible;
    private Utilities.Callback<Integer> onTTLChange;
    private boolean shownAiButton;
    private int timer;
    private final ImageView timerButton;
    private final CaptionContainerView.PeriodDrawable timerDrawable;
    private ItemOptions timerPopup;
    private boolean timerVisible;
    private final int[] values;

    /* renamed from: org.telegram.ui.Components.CaptionPhotoViewer$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ CaptionPhotoViewer this$0;

        public AnonymousClass1(CaptionPhotoViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            CaptionPhotoViewer r2 = this.this$0;
            if (r2.editText.getEditText().getLineCount() <= 2) goto L8;
            if (r3 == null) goto L8;
            if (TextUtils.isEmpty(r3.toString().trim()) == true) goto L8;
            boolean r32 = true;
        L9:
            CaptionPhotoViewer.m9111$$Nest$mshowAiButton(r2, r32);
            return;
        L8:
            r32 = false;
            goto L9
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static /* synthetic */ void $r8$lambda$C_7bjcWE_E9wCAiiZGzaprUin4c(CaptionPhotoViewer r0, FrameLayout r1, View r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Fu_YwRSWOxsgVVMifRZt8PfGK3U(CaptionPhotoViewer r0, boolean r1) {
        r0.lambda$setAddPhotoVisible$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$GkBuewaAEF7LKzSIswbb8JYp_AY(CaptionPhotoViewer r0, CharSequence r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$NZ2chP2BcGHOGnrhAhzEF-fZD58, reason: not valid java name */
    public static /* synthetic */ void m9109$r8$lambda$NZ2chP2BcGHOGnrhAhzEFfZD58(CaptionPhotoViewer r0, boolean r1) {
        r0.lambda$setTimerVisible$7(r1);
    }

    /* renamed from: $r8$lambda$b4PRJ-dLxkv7kGlSiuxEmNgwJZc, reason: not valid java name */
    public static /* synthetic */ void m9110$r8$lambda$b4PRJdLxkv7kGlSiuxEmNgwJZc(CaptionPhotoViewer r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$clTeodF3us8xX5ztFxJPqkyDtPs(CaptionPhotoViewer r0) {
        r0.lambda$new$5();
    }

    public static /* synthetic */ void $r8$lambda$lXJ7HWCf87q_MoLF49QhtgfjVeY(CaptionPhotoViewer r0, boolean r1) {
        r0.lambda$showAiButton$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$pw1b_lTlxTlbOX7CTIkGFJNz91I(CaptionPhotoViewer r0, HintView2 r1) {
        r0.lambda$showAiButton$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$v6TjZ0DTSZIyInnClJZJnpFAIsA(CaptionPhotoViewer r0, CharSequence r1, Integer r2, Integer r3, Boolean r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$xaHpmUoITN66mttwdtU57dYgLJ0(CaptionPhotoViewer r0, int r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: -$$Nest$mshowAiButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9111$$Nest$mshowAiButton(CaptionPhotoViewer r0, boolean r1) {
        r0.showAiButton(r1);
    }

    public CaptionPhotoViewer(Context r22, FrameLayout r23, SizeNotifierFrameLayout r24, FrameLayout r25, Theme.ResourcesProvider r26, BlurringShader.BlurManager r27, Runnable r28) {
        super(r22, r23, r24, r25, r26, r27);
        this.timer = 0;
        this.SHOW_ONCE = Integer.MAX_VALUE;
        int r9 = 3;
        this.values = new int[]{Integer.MAX_VALUE, 3, 10, 30, 0};
        this.moveButtonBounds = new RectF();
        AnimatedTextView.AnimatedTextDrawable r10 = new AnimatedTextView.AnimatedTextDrawable();
        this.moveButtonText = r10;
        this.moveButtonBounce = new ButtonBounce(this);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.lineCountAnimated = new AnimatedFloat(this, 0, 350, r6);
        this.moveButtonAnimated = new AnimatedFloat(this, 0, 350, r6);
        this.moveButtonExpandedAnimated = new AnimatedFloat(this, 0, 350, r6);
        this.collapseMoveButton = new CaptionPhotoViewer$$ExternalSyntheticLambda0(this);
        this.applyCaption = r28;
        r10.setTextSize(AndroidUtilities.dp(14.0f));
        r10.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        r10.setTextColor(-1);
        if (isAtTop() == false) goto L5;
        r10.setText(LocaleController.getString(R.string.MoveCaptionDown));
        this.moveButtonIcon = r22.getResources().getDrawable(R.drawable.menu_link_below);
    L6:
        ImageView r2 = new ImageView(r22);
        this.addPhotoButton = r2;
        r2.setImageResource(R.drawable.filled_add_photo);
        ImageView.ScaleType r3 = ImageView.ScaleType.CENTER;
        r2.setScaleType(r3);
        r2.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r2.setBackground(Theme.createSelectorDrawable(1090519039, 1, AndroidUtilities.dp(18.0f)));
        setAddPhotoVisible(false, false);
        int r11 = 48;
        if (isAtTop() == false) goto L9;
        int r5 = 48;
    L10:
        int r15 = r5 | 3;
        if (isAtTop() == false) goto L13;
        float r17 = 6.0f;
    L15:
        if (isAtTop() == false) goto L17;
        float r52 = 0.0f;
        float r19 = 0.0f;
    L18:
        addView(r2, LayoutHelper.createFrame(44, 44.0f, r15, 14.0f, r17, 0.0f, r19));
        ImageView r29 = new ImageView(r22);
        this.timerButton = r29;
        CaptionContainerView.PeriodDrawable r13 = new CaptionContainerView.PeriodDrawable();
        this.timerDrawable = r13;
        r29.setImageDrawable(r13);
        r29.setBackground(Theme.createSelectorDrawable(1090519039, 1, AndroidUtilities.dp(18.0f)));
        r29.setScaleType(r3);
        setTimerVisible(false, false);
        if (isAtTop() == false) goto L21;
        int r4 = 48;
    L22:
        int r152 = r4 | 5;
        if (isAtTop() == false) goto L25;
        float r172 = 6.0f;
    L27:
        if (isAtTop() == false) goto L29;
        float r192 = r52;
    L30:
        addView(r29, LayoutHelper.createFrame(44, 44.0f, r152, 0.0f, r172, 10.0f, r192));
        if (isAtTop() == false) goto L33;
        r9 = 1;
    L33:
        HintView2 r42 = new HintView2(r22, r9);
        this.hint = r42;
        r42.setRounding(12.0f);
        int r92 = AndroidUtilities.dp(12.0f);
        float r14 = 8.0f;
        if (isAtTop() == false) goto L36;
        float r132 = 8.0f;
    L37:
        int r133 = AndroidUtilities.dp(r132);
        int r8 = AndroidUtilities.dp(12.0f);
        if (isAtTop() == false) goto L40;
        r14 = r52;
    L40:
        r42.setPadding(r92, r133, r8, AndroidUtilities.dp(r14));
        r42.setJoint(1.0f, -21.0f);
        r42.setMultilineText(true);
        if (isAtTop() == true) goto L44;
        r11 = 80;
    L44:
        addView(r42, LayoutHelper.createFrame(-1, 80, r11 | 5));
        ImageView r0 = new ImageView(r22);
        this.aiButton = r0;
        AiButtonDrawable r43 = new AiButtonDrawable(r22);
        this.aiButtonIcon = r43;
        r0.setImageDrawable(r43);
        this.aiButton.setScaleType(r3);
        this.aiButton.setColorFilter(new PorterDuffColorFilter(-1140850689, PorterDuff.Mode.MULTIPLY));
        this.aiButton.setBackground(Theme.createSelectorDrawable(1090519039, 1, AndroidUtilities.dp(16.0f)));
        addView(this.aiButton, LayoutHelper.createFrame(44, 44.0f, 53, 8.0f, 0.0f, 8.0f, 0.0f));
        this.aiButton.setContentDescription(LocaleController.getString(R.string.AIEditor));
        ScaleStateListAnimator.apply(this.aiButton);
        this.editText.getEditText().addTextChangedListener(new AnonymousClass1(this));
        this.aiButton.setVisibility(8);
        this.aiButton.setAlpha(r52);
        this.aiButton.setScaleX(0.6f);
        this.aiButton.setScaleY(0.6f);
        this.aiButton.setOnClickListener(new CaptionPhotoViewer$$ExternalSyntheticLambda1(this));
        r29.setOnClickListener(new CaptionPhotoViewer$$ExternalSyntheticLambda2(this, r23));
        return;
    L36:
        r132 = r52;
        goto L37
    L29:
        r192 = 6.0f;
        goto L30
    L25:
        r172 = r52;
        goto L27
    L21:
        r4 = 80;
        goto L22
    L17:
        r52 = 0.0f;
        r19 = 6.0f;
        goto L18
    L13:
        r17 = 0.0f;
        goto L15
    L9:
        r5 = 80;
        goto L10
    L5:
        r10.setText(LocaleController.getString(R.string.MoveCaptionUp));
        this.moveButtonIcon = r22.getResources().getDrawable(R.drawable.menu_link_above);
        goto L6
    }

    private void changeTimer(int r10) {
        if (this.timer == r10) goto L38;
        setTimer(r10);
        Utilities.Callback<Integer> r0 = this.onTTLChange;
        if (r0 == null) goto L8;
        r0.run(Integer.valueOf(r10));
    L8:
        float r2 = 1.0f;
        if (r10 != 0) goto L16;
        if (this.isVideo == false) goto L13;
        int r6 = R.string.TimerPeriodVideoKeep;
    L14:
        CharSequence r62 = LocaleController.getString(r6);
        this.hint.setMaxWidthPx(getMeasuredWidth());
        this.hint.setMultilineText(false);
        this.hint.setInnerPadding(13.0f, 4.0f, 10.0f, 4.0f);
        this.hint.setIconMargin(0);
        this.hint.setIconTranslate(0.0f, -AndroidUtilities.dp(1.0f));
    L28:
        HintView2 r02 = this.hint;
        float r3 = (-Math.min(AndroidUtilities.dp(34.0f), getEditTextHeight())) - AndroidUtilities.dp(14.0f);
        if (isAtTop() == false) goto L31;
        r2 = -1.0f;
    L31:
        r02.setTranslationY(r3 * r2);
        this.hint.setText(r62);
        if (r10 <= 0) goto L34;
        int r102 = R.raw.fire_on;
    L35:
        RLottieDrawable r03 = new RLottieDrawable(r102, AndroidUtilities.dp(34.0f), AndroidUtilities.dp(34.0f));
        r03.start();
        this.hint.setIcon(r03);
        this.hint.show();
        this.moveButtonExpanded = false;
        AndroidUtilities.cancelRunOnUIThread(this.collapseMoveButton);
        invalidate();
        return;
    L34:
        r102 = R.raw.fire_off;
        goto L35
    L13:
        r6 = R.string.TimerPeriodPhotoKeep;
        goto L14
    L16:
        if (r10 == Integer.MAX_VALUE) goto L18;
        if (r10 > 0) goto L24;
        return;
    L24:
        if (this.isVideo == false) goto L26;
        String r04 = "TimerPeriodVideoSetSeconds";
    L27:
        r62 = AndroidUtilities.replaceTags(LocaleController.formatPluralString(r04, r10, new Object[0]));
        this.hint.setMultilineText(true);
        HintView2 r05 = this.hint;
        r05.setMaxWidthPx(HintView2.cutInFancyHalf(r62, r05.getTextPaint()));
        this.hint.setInnerPadding(12.0f, 7.0f, 11.0f, 7.0f);
        this.hint.setIconMargin(2);
        this.hint.setIconTranslate(0.0f, 0.0f);
        goto L28
    L26:
        r04 = "TimerPeriodPhotoSetSeconds";
        goto L27
    L18:
        if (this.isVideo == false) goto L20;
        int r63 = R.string.TimerPeriodVideoSetOnce;
    L21:
        r62 = LocaleController.getString(r63);
        this.hint.setMaxWidthPx(getMeasuredWidth());
        this.hint.setMultilineText(false);
        this.hint.setInnerPadding(13.0f, 4.0f, 10.0f, 4.0f);
        this.hint.setIconMargin(0);
        this.hint.setIconTranslate(0.0f, -AndroidUtilities.dp(1.0f));
        goto L28
    L20:
        r63 = R.string.TimerPeriodPhotoSetOnce;
        goto L21
    }

    private /* synthetic */ void lambda$new$0(CharSequence r2) {
        this.editText.setText(r2);
        this.editText.setSelection(r2.length(), r2.length());
    }

    private /* synthetic */ void lambda$new$1(CharSequence r1, Integer r2, Integer r3, Boolean r4) {
        this.editText.setText(r1);
        done();
    }

    private /* synthetic */ void lambda$new$2(View r4) {
        MessagesController.getGlobalMainSettings().edit().putInt("aihintshown", 3).apply();
        new AIEditorAlert(getContext(), new DarkThemeResourceProvider()).setText(this.editText.getText()).setOnUse(new CaptionPhotoViewer$$ExternalSyntheticLambda4(this)).setOnSend(0, true, new CaptionPhotoViewer$$ExternalSyntheticLambda5(this)).show();
    }

    private /* synthetic */ void lambda$new$3(int r1) {
        changeTimer(r1);
    }

    private /* synthetic */ void lambda$new$4(FrameLayout r7, View r8) {
        ItemOptions r82 = this.timerPopup;
        if (r82 != null) goto L5;
    L8:
        this.hint.hide();
        ItemOptions r72 = ItemOptions.makeOptions(r7, new DarkThemeResourceProvider(), this.timerButton);
        this.timerPopup = r72;
        r72.setDimAlpha(0);
        this.timerPopup.addText(LocaleController.getString(R.string.TimerPeriodHint), 13, AndroidUtilities.dp(200.0f));
        this.timerPopup.addGap();
        int[] r73 = this.values;
        int r0 = r73.length;
        int r1 = 0;
    L9:
        if (r1 >= r0) goto L21;
        int r2 = r73[r1];
        if (r2 != 0) goto L14;
        String r3 = LocaleController.getString(R.string.TimerPeriodDoNotDelete);
    L17:
        this.timerPopup.add(0, r3, new CaptionPhotoViewer$$ExternalSyntheticLambda6(this, r2));
        if (this.timer != r2) goto L20;
        this.timerPopup.putCheck();
    L20:
        r1 = r1 + 1;
        goto L9
    L14:
        if (r2 != Integer.MAX_VALUE) goto L16;
        r3 = LocaleController.getString(R.string.TimerPeriodOnce);
        goto L17
    L16:
        r3 = LocaleController.formatPluralString("Seconds", r2, new Object[0]);
        goto L17
    L21:
        this.timerPopup.show();
        return;
    L5:
        if (r82.isShown() == false) goto L8;
        this.timerPopup.dismiss();
        this.timerPopup = null;
    }

    private /* synthetic */ void lambda$new$5() {
        if (this.moveButtonExpanded == false) goto L6;
        this.moveButtonExpanded = false;
        invalidate();
        return;
    }

    private /* synthetic */ void lambda$setAddPhotoVisible$6(boolean r1) {
        if (r1 == true) goto L5;
        this.timerButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$setTimerVisible$7(boolean r1) {
        if (r1 == true) goto L5;
        this.timerButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$showAiButton$8(boolean r1) {
        if (r1 == true) goto L5;
        this.aiButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$showAiButton$9(HintView2 r1) {
        removeView(r1);
    }

    private void showAiButton(boolean r12) {
        if (r12 == true) goto L5;
    L7:
        boolean r122 = false;
    L9:
        if (this.shownAiButton == r122) goto L39;
        if (r122 == false) goto L13;
        MessagesController.getInstance(this.currentAccount).getTonesController().load();
    L13:
        this.shownAiButton = r122;
        this.aiButton.setVisibility(0);
        ViewPropertyAnimator r2 = this.aiButton.animate();
        if (r122 == false) goto L16;
        float r4 = 1.0f;
    L17:
        ViewPropertyAnimator r22 = r2.alpha(r4);
        float r42 = 0.6f;
        if (r122 == false) goto L20;
        float r5 = 1.0f;
    L21:
        ViewPropertyAnimator r23 = r22.scaleX(r5);
        if (r122 == false) goto L24;
        r42 = 1.0f;
    L24:
        r23.scaleY(r42).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(420).withEndAction(new CaptionPhotoViewer$$ExternalSyntheticLambda8(this, r122)).start();
        if (r122 == false) goto L33;
        ImageView r123 = this.aiButton;
        AiButtonDrawable r43 = this.aiButtonIcon;
        Objects.requireNonNull(r43);
        r123.postDelayed(new CaptionPhotoViewer$$ExternalSyntheticLambda9(r43), 220);
        HintView2 r124 = this.aiHint;
        if (r124 == null) goto L30;
        r124.hide();
        this.aiHint = null;
    L30:
        if (MessagesController.getGlobalMainSettings().getInt("aihintshown", 0) >= 3) goto L37;
        HintView2 r125 = new HintView2(getContext(), 3);
        this.aiHint = r125;
        r125.setMultilineText(true);
        this.aiHint.setText(LocaleController.getString(R.string.AIEditorHint));
        this.aiHint.setJointPx(1.0f, ((-this.aiButton.getWidth()) / 2.0f) + AndroidUtilities.dp(4.0f));
        addView(this.aiHint, LayoutHelper.createFrame(-1, 200.0f, 48, 0.0f, -196.0f, 0.0f, 0.0f));
        this.aiHint.setOnHiddenListener(new CaptionPhotoViewer$$ExternalSyntheticLambda10(this, r125));
        this.aiHint.setDuration(4000);
        this.aiHint.show();
        MessagesController.getGlobalMainSettings().edit().putInt("aihintshown", MessagesController.getGlobalMainSettings().getInt("aihintshown", 0) + 1).apply();
        return;
    L37:
        return;
    L33:
        HintView2 r126 = this.aiHint;
        if (r126 == null) goto L38;
        r126.hide();
        this.aiHint = null;
        return;
    L38:
        return;
    L20:
        r5 = 0.6f;
        goto L21
    L16:
        r4 = 0.0f;
        goto L17
    L39:
        return;
    L5:
        if (ExteraConfig.getTelegramAiEditor() == false) goto L7;
        r122 = true;
        goto L9
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int additionalKeyboardHeight() {
        return 0;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void afterUpdateShownKeyboard(boolean r5) {
        ImageView r0 = this.timerButton;
        int r1 = 0;
        if (r5 == false) goto L5;
    L7:
        int r3 = 8;
    L8:
        r0.setVisibility(r3);
        ImageView r02 = this.addPhotoButton;
        if (r5 == false) goto L11;
    L13:
        r1 = 8;
    L14:
        r02.setVisibility(r1);
        if (r5 == false) goto L18;
        this.timerButton.setVisibility(8);
        this.addPhotoButton.setVisibility(8);
        return;
    L18:
        return;
    L11:
        if (this.addPhotoVisible == false) goto L13;
    L5:
        if (this.timerVisible == false) goto L7;
        r3 = 0;
        goto L8
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void beforeUpdateShownKeyboard(boolean r4) {
        if (r4 == true) goto L11;
        ImageView r42 = this.timerButton;
        int r1 = 8;
        if (this.timerVisible == false) goto L6;
        int r0 = 0;
    L7:
        r42.setVisibility(r0);
        ImageView r43 = this.addPhotoButton;
        if (this.addPhotoVisible == false) goto L10;
        r1 = 0;
    L10:
        r43.setVisibility(r1);
        goto L11
    L6:
        r0 = 8;
    L11:
        HintView2 r3 = this.hint;
        if (r3 == null) goto L15;
        r3.hide();
        return;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public boolean clipChild(View r1) {
        if (r1 == this.hint) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r14) {
        super.dispatchDraw(r14);
        ImageView r0 = this.aiButton;
        if (r0 == null) goto L13;
        r0.setTranslationX(-AndroidUtilities.dp((1.0f - this.keyboardT) * 4.0f));
        ImageView r02 = this.aiButton;
        boolean r5 = isAtTop();
        RectF r6 = this.bounds;
        if (r5 == false) goto L7;
        float r52 = r6.bottom - AndroidUtilities.dp(44.0f);
    L9:
        if (isAtTop() == false) goto L11;
        int r62 = 1;
    L12:
        r02.setTranslationY(r52 + ((r62 * AndroidUtilities.dp(3.0f)) * Utilities.clamp01((-this.lineCountAnimated.set(this.editText.getEditText().getLineCount())) + 4.0f)));
        goto L13
    L11:
        r62 = -1;
        goto L12
    L7:
        r52 = r6.top;
    L13:
        float r03 = this.moveButtonAnimated.set(this.moveButtonVisible, true ^ showMoveButton());
        float r1 = this.moveButtonExpandedAnimated.set(this.moveButtonExpanded);
        if (r03 <= 0.0f) goto L31;
        float r53 = this.moveButtonBounce.getScale(0.03f);
        int r4 = AndroidUtilities.dp((1.0f - this.keyboardT) * 4.0f);
        boolean r63 = isAtTop();
        RectF r7 = this.moveButtonBounds;
        if (r63 == false) goto L18;
        r7.set(AndroidUtilities.dp(7.0f) + r4, this.bounds.bottom + AndroidUtilities.dp(10.0f), (r4 + AndroidUtilities.dp(44.0f)) + ((this.moveButtonText.getCurrentWidth() + AndroidUtilities.dp(11.0f)) * r1), this.bounds.bottom + AndroidUtilities.dp(42.0f));
    L20:
        if (r03 >= 1.0f) goto L22;
        r14.saveLayerAlpha(this.moveButtonBounds, (int) (r03 * 255.0f), 31);
    L23:
        r14.scale(r53, r53, this.moveButtonBounds.centerX(), this.moveButtonBounds.centerY());
        r14.clipRect(this.moveButtonBounds);
        AndroidUtilities.dpf2(16.0f);
        BlurredBackgroundDrawableViewFactory r2 = this.factoryForMentions;
        if (r2 != null) goto L26;
    L29:
        this.moveButtonIcon.setBounds((int) (this.moveButtonBounds.left + AndroidUtilities.dp(9.0f)), (int) (this.moveButtonBounds.centerY() - AndroidUtilities.dp(10.0f)), (int) (this.moveButtonBounds.left + AndroidUtilities.dp(29.0f)), (int) (this.moveButtonBounds.centerY() + AndroidUtilities.dp(10.0f)));
        this.moveButtonIcon.draw(r14);
        AnimatedTextView.AnimatedTextDrawable r04 = this.moveButtonText;
        float r22 = this.moveButtonBounds.left + AndroidUtilities.dp(37.0f);
        RectF r42 = this.moveButtonBounds;
        r04.setBounds(r22, r42.top, r42.right, r42.bottom);
        this.moveButtonText.setAlpha((int) (r1 * 255.0f));
        this.moveButtonText.draw(r14);
        r14.restore();
        return;
    L26:
        if (this.backgroundForCaptionButton != null) goto L28;
        this.backgroundForCaptionButton = r2.create(this).setColorProvider(BlurredBackgroundProviderImpl.photoViewer(this.resourcesProvider)).setPadding(AndroidUtilities.dp(5.0f)).setRadius(AndroidUtilities.dp(16.0f));
    L28:
        RectF r05 = this.moveButtonBounds;
        Rect r23 = AndroidUtilities.rectTmp2;
        r05.round(r23);
        r23.inset(-AndroidUtilities.dp(5.0f), -AndroidUtilities.dp(5.0f));
        this.backgroundForCaptionButton.setBounds(r23);
        this.backgroundForCaptionButton.draw(r14);
        goto L29
    L22:
        r14.save();
        goto L23
    L18:
        r7.set(AndroidUtilities.dp(7.0f) + r4, this.bounds.top - AndroidUtilities.dp(42.0f), (r4 + AndroidUtilities.dp(44.0f)) + ((this.moveButtonText.getCurrentWidth() + AndroidUtilities.dp(11.0f)) * r1), this.bounds.top - AndroidUtilities.dp(10.0f));
        goto L20
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r7) {
        if (r7.getAction() != 0) goto L12;
        ButtonBounce r0 = this.moveButtonBounce;
        if (this.moveButtonAnimated.get() > 0.0f) goto L7;
    L9:
        boolean r1 = false;
    L10:
        r0.setPressed(r1);
    L36:
        if (this.moveButtonBounce.isPressed() == false) goto L38;
    L41:
        return true;
    L38:
        if (super.dispatchTouchEvent(r7) == true) goto L41;
        return false;
    L7:
        if (this.moveButtonBounds.contains(r7.getX(), r7.getY()) == false) goto L9;
        r1 = true;
        goto L10
    L12:
        if (r7.getAction() != 2) goto L21;
        if (this.moveButtonBounce.isPressed() == false) goto L36;
        if (this.moveButtonAnimated.get() > 0.0f) goto L18;
    L19:
        this.moveButtonBounce.setPressed(false);
        goto L36
    L18:
        if (this.moveButtonBounds.contains(r7.getX(), r7.getY()) == true) goto L36;
    L21:
        if (r7.getAction() == 1) goto L25;
        if (r7.getAction() != 3) goto L36;
    L25:
        if (this.moveButtonBounce.isPressed() == false) goto L36;
        if (r7.getAction() != 1) goto L33;
        onMoveButtonClick();
        AnimatedTextView.AnimatedTextDrawable r72 = this.moveButtonText;
        if (isAtTop() == false) goto L31;
        int r02 = R.string.MoveCaptionDown;
    L32:
        r72.setText(LocaleController.getString(r02), true);
        goto L33
    L31:
        r02 = R.string.MoveCaptionUp;
    L33:
        this.moveButtonBounce.setPressed(false);
        return true;
    }

    public void expandMoveButton() {
        AndroidUtilities.cancelRunOnUIThread(this.collapseMoveButton);
        boolean r0 = MessagesController.getInstance(this.currentAccount).shouldShowMoveCaptionHint();
        this.moveButtonExpanded = r0;
        if (r0 == false) goto L6;
        MessagesController.getInstance(this.currentAccount).incrementMoveCaptionHint();
        invalidate();
        AndroidUtilities.runOnUIThread(this.collapseMoveButton, 5000);
        return;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getCaptionDefaultLimit() {
        return MessagesController.getInstance(this.currentAccount).captionLengthLimitDefault;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getCaptionLimit() {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L7;
        return getCaptionPremiumLimit();
    L7:
        return getCaptionDefaultLimit();
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getCaptionPremiumLimit() {
        return MessagesController.getInstance(this.currentAccount).captionLengthLimitPremium;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getEditTextHeight() {
        return super.getEditTextHeight();
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getEditTextLeft() {
        if (this.addPhotoVisible == true) goto L5;
        return 0;
    L5:
        return AndroidUtilities.dp(31.0f);
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public int getEditTextStyle() {
        return 3;
    }

    public boolean hasTimer() {
        if (this.timerVisible == true) goto L5;
        return false;
    L5:
        if (this.timer <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void onEditHeightChange(int r3) {
        HintView2 r0 = this.hint;
        float r32 = (-Math.min(AndroidUtilities.dp(34.0f), r3)) - AndroidUtilities.dp(10.0f);
        if (isAtTop() == false) goto L5;
        float r2 = -1.0f;
    L6:
        r0.setTranslationY(r32 * r2);
        return;
    L5:
        r2 = 1.0f;
        goto L6
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void onLineCountChanged(int r5, int r6) {
        CharSequence r0 = getText();
        boolean r2 = false;
        if (r6 <= 2) goto L8;
        if (r0 == null) goto L8;
        if (TextUtils.isEmpty(r0.toString().trim()) == true) goto L8;
        boolean r02 = true;
    L9:
        showAiButton(r02);
        if (this.shownAiButton == true) goto L12;
        return;
    L12:
        if (r5 >= 3) goto L14;
        boolean r52 = true;
    L15:
        if (r6 >= 3) goto L17;
        r2 = true;
    L17:
        if (r52 == r2) goto L21;
        invalidate();
        return;
    L21:
        return;
    L14:
        r52 = false;
    L8:
        r02 = false;
        goto L9
    }

    public abstract void onMoveButtonClick();

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void onTextChange() {
        Runnable r0 = this.applyCaption;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void onUpdateShowKeyboard(float r3) {
        float r1 = 1.0f - r3;
        this.timerButton.setAlpha(r1);
        this.addPhotoButton.setAlpha(r1);
    }

    public abstract void openedKeyboard();

    public void setAddPhotoVisible(boolean r6, boolean r7) {
        this.addPhotoVisible = r6;
        this.addPhotoButton.animate().cancel();
        ImageView r0 = this.addPhotoButton;
        float r2 = 1.0f;
        int r3 = 0;
        float r4 = 0.0f;
        if (r7 == false) goto L13;
        r0.setVisibility(0);
        ViewPropertyAnimator r72 = this.addPhotoButton.animate();
        if (r6 == true) goto L8;
        r2 = 0.0f;
    L8:
        ViewPropertyAnimator r73 = r72.alpha(r2);
        if (r6 == true) goto L12;
        r4 = AndroidUtilities.dp(-8.0f);
    L12:
        r73.translationX(r4).withEndAction(new CaptionPhotoViewer$$ExternalSyntheticLambda3(this, r6)).start();
    L25:
        updateEditTextLeft();
        ViewGroup.MarginLayoutParams r62 = (ViewGroup.MarginLayoutParams) this.editText.getLayoutParams();
        if (this.addPhotoVisible == true) goto L28;
    L30:
        r62.rightMargin = AndroidUtilities.dp(32 + r3);
        this.editText.setLayoutParams(r62);
        return;
    L28:
        if (this.timerVisible == false) goto L30;
        r3 = 33;
        goto L30
    L13:
        if (r6 == false) goto L15;
        int r74 = 0;
    L16:
        r0.setVisibility(r74);
        ImageView r75 = this.addPhotoButton;
        if (r6 == true) goto L20;
        r2 = 0.0f;
    L20:
        r75.setAlpha(r2);
        ImageView r76 = this.addPhotoButton;
        if (r6 == true) goto L24;
        r4 = AndroidUtilities.dp(-8.0f);
    L24:
        r76.setTranslationX(r4);
        goto L25
    L15:
        r74 = 8;
        goto L16
    }

    public void setIsVideo(boolean r1) {
        this.isVideo = r1;
    }

    public void setOnAddPhotoClick(View.OnClickListener r1) {
        this.addPhotoButton.setOnClickListener(r1);
    }

    public void setOnTimerChange(Utilities.Callback<Integer> r1) {
        this.onTTLChange = r1;
    }

    public void setShowMoveButtonVisible(boolean r2, boolean r3) {
        if (this.moveButtonVisible != r2) goto L6;
        if (r3 == false) goto L6;
        return;
    L6:
        this.moveButtonVisible = r2;
        if (r3 == true) goto L9;
        this.moveButtonAnimated.set(r2, true);
    L9:
        invalidate();
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void setText(CharSequence r1) {
        super.setText(r1);
    }

    public void setTimer(int r4) {
        this.timer = r4;
        CaptionContainerView.PeriodDrawable r0 = this.timerDrawable;
        if (r4 != Integer.MAX_VALUE) goto L5;
        int r42 = 1;
    L7:
        if (this.timer <= 0) goto L9;
        boolean r1 = true;
    L10:
        r0.setValue(r42, r1, true);
        HintView2 r3 = this.hint;
        if (r3 == null) goto L14;
        r3.hide();
        return;
    L14:
        return;
    L9:
        r1 = false;
        goto L10
    L5:
        r42 = Math.max(1, r4);
        goto L7
    }

    public void setTimerVisible(boolean r6, boolean r7) {
        this.timerVisible = r6;
        this.timerButton.animate().cancel();
        ImageView r0 = this.timerButton;
        float r2 = 1.0f;
        int r3 = 0;
        float r4 = 0.0f;
        if (r7 == false) goto L13;
        r0.setVisibility(0);
        ViewPropertyAnimator r72 = this.timerButton.animate();
        if (r6 == true) goto L8;
        r2 = 0.0f;
    L8:
        ViewPropertyAnimator r73 = r72.alpha(r2);
        if (r6 == true) goto L12;
        r4 = AndroidUtilities.dp(8.0f);
    L12:
        r73.translationX(r4).withEndAction(new CaptionPhotoViewer$$ExternalSyntheticLambda7(this, r6)).start();
    L25:
        ViewGroup.MarginLayoutParams r62 = (ViewGroup.MarginLayoutParams) this.editText.getLayoutParams();
        if (this.addPhotoVisible == true) goto L28;
    L30:
        r62.rightMargin = AndroidUtilities.dp(32 + r3);
        this.editText.setLayoutParams(r62);
        return;
    L28:
        if (this.timerVisible == false) goto L30;
        r3 = 33;
        goto L30
    L13:
        if (r6 == false) goto L15;
        int r74 = 0;
    L16:
        r0.setVisibility(r74);
        ImageView r75 = this.timerButton;
        if (r6 == true) goto L20;
        r2 = 0.0f;
    L20:
        r75.setAlpha(r2);
        ImageView r76 = this.timerButton;
        if (r6 == true) goto L24;
        r4 = AndroidUtilities.dp(8.0f);
    L24:
        r76.setTranslationX(r4);
        goto L25
    L15:
        r74 = 8;
        goto L16
    }

    public abstract boolean showMoveButton();

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void updateColors(Theme.ResourcesProvider r2) {
        super.updateColors(r2);
        this.timerDrawable.updateColors(-1, Theme.getColor(Theme.key_chat_editMediaButton, r2), -1);
    }

    @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
    public void updateKeyboard(int r2) {
        boolean r0 = this.toKeyboardShow;
        super.updateKeyboard(r2);
        if (r0 == false) goto L5;
        return;
    L5:
        if (this.keyboardNotifier.keyboardVisible() == false) goto L9;
        openedKeyboard();
        return;
    }
}
