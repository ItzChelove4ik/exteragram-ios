package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.utils.ui.UIUtil;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes3.dex */
public class ActionBarMenuSubItem extends FrameLayout {
    private BackupImageView backupImageView;
    boolean bottom;
    public CheckBox2 checkView;
    public boolean checkViewLeft;
    private boolean enabled;
    private ValueAnimator enabledAnimator;
    boolean expandIfMultiline;
    private int iconColor;
    private PorterDuff.Mode iconColorMode;
    private int iconResId;
    public RLottieImageView imageView;
    private int itemHeight;
    public Runnable openSwipeBackLayout;
    protected final Theme.ResourcesProvider resourcesProvider;
    private ImageView rightIcon;
    private int selectorColor;
    int selectorRad;
    public TextView subtextView;
    private int textColor;
    public AnimatedEmojiSpan.TextViewEmojis textView;
    boolean top;

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuSubItem$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarMenuSubItem this$0;
        final /* synthetic */ int val$colorDisabled;
        final /* synthetic */ int val$colorEnabled;
        final /* synthetic */ boolean val$enabled;

        public AnonymousClass1(ActionBarMenuSubItem r1, boolean r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$enabled = r2;
            this.val$colorDisabled = r3;
            this.val$colorEnabled = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (this.val$enabled == false) goto L5;
            float r42 = 1.0f;
        L6:
            this.this$0.setTextColor(ColorUtils.blendARGB(this.val$colorDisabled, this.val$colorEnabled, r42));
            this.this$0.setIconColor(ColorUtils.blendARGB(this.val$colorDisabled, this.val$colorEnabled, r42));
            return;
        L5:
            r42 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuSubItem$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarMenuSubItem this$0;
        final /* synthetic */ int val$colorEnabled;
        final /* synthetic */ boolean val$enabled;
        final /* synthetic */ int val$iconColorDisabled;
        final /* synthetic */ int val$textColorDisabled;

        public AnonymousClass2(ActionBarMenuSubItem r1, boolean r2, int r3, int r4, int r5) {
            this.this$0 = r1;
            this.val$enabled = r2;
            this.val$textColorDisabled = r3;
            this.val$colorEnabled = r4;
            this.val$iconColorDisabled = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (this.val$enabled == false) goto L5;
            float r42 = 1.0f;
        L6:
            this.this$0.setTextColor(ColorUtils.blendARGB(this.val$textColorDisabled, this.val$colorEnabled, r42));
            this.this$0.setIconColor(ColorUtils.blendARGB(this.val$iconColorDisabled, this.val$colorEnabled, r42));
            return;
        L5:
            r42 = 0.0f;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$Oy0gQwKljpSzEuktR4pWI2vuKvo(ActionBarMenuSubItem r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$setEnabledByColor$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$eFjM0ZHxbSXJsP0B_MBVfpX4r2k(ActionBarMenuSubItem r0, int r1, int r2, int r3, ValueAnimator r4) {
        r0.lambda$setEnabledByColor$1(r1, r2, r3, r4);
    }

    public ActionBarMenuSubItem(Context r4, int r5, boolean r6, boolean r7, Theme.ResourcesProvider r8) {
        super(r4);
        this.selectorRad = 12;
        this.itemHeight = 48;
        this.resourcesProvider = r8;
        this.top = r6;
        this.bottom = r7;
        this.textColor = getThemedColor(Theme.key_actionBarDefaultSubmenuItem);
        this.iconColor = getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon);
        this.iconColorMode = PorterDuff.Mode.MULTIPLY;
        this.selectorColor = getThemedColor(Theme.key_dialogButtonSelector);
        updateBackground();
        setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        RLottieImageView r62 = new RLottieImageView(r4);
        this.imageView = r62;
        r62.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(this.iconColor, PorterDuff.Mode.SRC_IN));
        RLottieImageView r63 = this.imageView;
        int r82 = 5;
        if (LocaleController.isRTL == false) goto L5;
        int r72 = 5;
    L6:
        addView(r63, LayoutHelper.createFrame(-2, 40, r72 | 16));
        AnimatedEmojiSpan.TextViewEmojis r64 = new AnimatedEmojiSpan.TextViewEmojis(r4);
        this.textView = r64;
        r64.setLines(1);
        this.textView.setSingleLine(true);
        this.textView.setGravity(3);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        this.textView.setTextColor(this.textColor);
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        AnimatedEmojiSpan.TextViewEmojis r42 = this.textView;
        if (LocaleController.isRTL == true) goto L10;
        r82 = 3;
    L10:
        addView(r42, LayoutHelper.createFrame(-2, -2, r82 | 16));
        this.checkViewLeft = LocaleController.isRTL;
        makeCheckView(r5);
        return;
    L5:
        r72 = 3;
        goto L6
    }

    public ActionBarMenuSubItem(Context r2, boolean r3, boolean r4) {
        this(r2, false, r3, r4);
    }

    public ActionBarMenuSubItem(Context r7, boolean r8, boolean r9, Theme.ResourcesProvider r10) {
        this(r7, 0, r8, r9, r10);
    }

    public ActionBarMenuSubItem(Context r7, boolean r8, boolean r9, boolean r10) {
        this(r7, r8 ? 1 : 0, r9, r10, null);
    }

    public ActionBarMenuSubItem(Context r1, boolean r2, boolean r3, boolean r4, Theme.ResourcesProvider r5) {
        this(r1, r2 ? 1 : 0, r3, r4, r5);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$setEnabledByColor$0(int r2, int r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        setTextColor(ColorUtils.blendARGB(r2, r3, r42));
        setIconColor(ColorUtils.blendARGB(r2, r3, r42));
    }

    private /* synthetic */ void lambda$setEnabledByColor$1(int r1, int r2, int r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        setTextColor(ColorUtils.blendARGB(r1, r2, r42));
        setIconColor(ColorUtils.blendARGB(r3, r2, r42));
    }

    public CheckBox2 getCheckView() {
        return this.checkView;
    }

    public int getIconResId() {
        return this.iconResId;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public ImageView getRightIcon() {
        return this.rightIcon;
    }

    public TextView getSubtextView() {
        return this.subtextView;
    }

    public AnimatedEmojiSpan.TextViewEmojis getTextView() {
        return this.textView;
    }

    public void makeCheckView(int r8) {
        if (r8 <= 0) goto L32;
        CheckBox2 r0 = new CheckBox2(getContext(), 26, this.resourcesProvider);
        this.checkView = r0;
        r0.setDrawUnchecked(false);
        this.checkView.setColor(-1, -1, Theme.key_actionBarDefaultSubmenuItem);
        this.checkView.setDrawBackgroundAsArc(-1);
        int r02 = 3;
        if (r8 != 1) goto L18;
        boolean r82 = LocaleController.isRTL;
        this.checkViewLeft = !r82;
        CheckBox2 r5 = this.checkView;
        if (r82 == false) goto L8;
        r02 = 5;
    L8:
        addView(r5, LayoutHelper.createFrame(26, -1, r02 | 16));
        AnimatedEmojiSpan.TextViewEmojis r7 = this.textView;
        if (LocaleController.isRTL == true) goto L11;
        int r83 = AndroidUtilities.dp(34.0f);
    L13:
        if (LocaleController.isRTL == true) goto L15;
        int r03 = 0;
    L16:
        r7.setPadding(r83, 0, r03, 0);
        return;
    L15:
        r03 = AndroidUtilities.dp(34.0f);
        goto L16
    L11:
        r83 = 0;
        goto L13
    L18:
        CheckBox2 r84 = this.checkView;
        if (LocaleController.isRTL == true) goto L22;
        r02 = 5;
    L22:
        addView(r84, LayoutHelper.createFrame(26, -1, r02 | 16));
        AnimatedEmojiSpan.TextViewEmojis r72 = this.textView;
        if (LocaleController.isRTL == false) goto L25;
        int r85 = AndroidUtilities.dp(34.0f);
    L27:
        if (LocaleController.isRTL == false) goto L29;
        int r04 = 0;
    L30:
        r72.setPadding(r85, 0, r04, 0);
        return;
    L29:
        r04 = AndroidUtilities.dp(34.0f);
        goto L30
    L25:
        r85 = 0;
        goto L27
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setEnabled(isEnabled());
        CheckBox2 r0 = this.checkView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isChecked() == false) goto L9;
        r2.setCheckable(true);
        r2.setChecked(this.checkView.isChecked());
        r2.setClassName("android.widget.CheckBox");
        return;
    }

    public void onItemShown() {
        if (this.imageView.getAnimatedDrawable() == null) goto L6;
        this.imageView.getAnimatedDrawable().start();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.itemHeight), TLObject.FLAG_30));
        if (this.expandIfMultiline == true) goto L5;
        return;
    L5:
        if (this.textView.getLayout().getLineCount() <= 1) goto L9;
        super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.itemHeight + 8), TLObject.FLAG_30));
        return;
    }

    public void openSwipeBack() {
        Runnable r0 = this.openSwipeBackLayout;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    public void setAnimatedIcon(int r2) {
        this.iconResId = 0;
        this.imageView.setAnimation(r2, 24, 24);
    }

    public void setCheckColor(int r2) {
        this.checkView.setColor(-1, -1, r2);
    }

    public void setChecked(boolean r2) {
        CheckBox2 r1 = this.checkView;
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setChecked(r2, true);
    }

    public ActionBarMenuSubItem setColors(int r1, int r2) {
        setTextColor(r1);
        setIconColor(r2);
        return this;
    }

    public void setEmojiCacheType(int r1) {
        this.textView.setCacheType(r1);
    }

    public void setEnabledByColor(boolean r5, int r6, int r7) {
        ValueAnimator r0 = this.enabledAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r1 = 0.0f;
        if (this.enabled == false) goto L8;
        float r02 = 1.0f;
    L9:
        if (r5 == false) goto L11;
        r1 = 1.0f;
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.enabledAnimator = r03;
        this.enabled = r5;
        r03.addUpdateListener(new ActionBarMenuSubItem$$ExternalSyntheticLambda0(this, r6, r7));
        this.enabledAnimator.addListener(new AnonymousClass1(this, r5, r6, r7));
        this.enabledAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.enabledAnimator.start();
        return;
    L8:
        r02 = 0.0f;
        goto L9
    }

    public void setEnabledByColor(boolean r8, int r9, int r10, int r11) {
        ValueAnimator r0 = this.enabledAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r1 = 0.0f;
        if (this.enabled == false) goto L8;
        float r02 = 1.0f;
    L9:
        if (r8 == false) goto L11;
        r1 = 1.0f;
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.enabledAnimator = r03;
        this.enabled = r8;
        r03.addUpdateListener(new ActionBarMenuSubItem$$ExternalSyntheticLambda1(this, r9, r11, r10));
        this.enabledAnimator.addListener(new AnonymousClass2(this, r8, r9, r11, r10));
        this.enabledAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.enabledAnimator.start();
        return;
    L8:
        r02 = 0.0f;
        goto L9
    }

    public void setIcon(int r2) {
        RLottieImageView r0 = this.imageView;
        this.iconResId = r2;
        r0.setImageResource(r2);
    }

    public void setIcon(Drawable r2) {
        this.iconResId = 0;
        this.imageView.setImageDrawable(r2);
    }

    public void setIconColor(int r2) {
        setIconColor(r2, PorterDuff.Mode.SRC_IN);
    }

    public void setIconColor(int r3, PorterDuff.Mode r4) {
        if (this.iconColor == r3) goto L5;
    L8:
        RLottieImageView r0 = this.imageView;
        this.iconColor = r3;
        this.iconColorMode = r4;
        r0.setColorFilter(new PorterDuffColorFilter(r3, r4));
        return;
    L5:
        if (this.iconColorMode != r4) goto L8;
    }

    public void setIconColorImage(int r3) {
        BackupImageView r2 = this.backupImageView;
        if (r2 == null) goto L6;
        r2.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
        return;
    }

    public void setImageSize(int r2, int r3) {
        BackupImageView r1 = this.backupImageView;
        if (r1 != null) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L7;
        int r0 = 5;
    L8:
        r1.setLayoutParams(LayoutHelper.createFrame(r2, r3, r0 | 16));
        return;
    L7:
        r0 = 3;
        goto L8
    }

    public void setItemHeight(int r1) {
        this.itemHeight = r1;
    }

    public void setMultiline(boolean r3) {
        this.textView.setLines(2);
        if (r3 == false) goto L5;
        this.textView.setTextSize(1, 14.0f);
    L6:
        this.textView.setSingleLine(false);
        this.textView.setGravity(16);
        return;
    L5:
        this.expandIfMultiline = true;
        goto L6
    }

    public void setRightIcon(int r6) {
        if (this.rightIcon != null) goto L12;
        ImageView r0 = new ImageView(getContext());
        this.rightIcon = r0;
        r0.setScaleType(ImageView.ScaleType.CENTER);
        this.rightIcon.setColorFilter(this.iconColor, PorterDuff.Mode.MULTIPLY);
        if (LocaleController.isRTL == false) goto L7;
        this.rightIcon.setScaleX(-1.0f);
    L7:
        ImageView r02 = this.rightIcon;
        if (LocaleController.isRTL == false) goto L10;
        int r1 = 3;
    L11:
        addView(r02, LayoutHelper.createFrame(24, -1, r1 | 16));
        goto L12
    L10:
        r1 = 5;
    L12:
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        boolean r12 = LocaleController.isRTL;
        ImageView r2 = this.rightIcon;
        if (r12 == false) goto L18;
        if (r2 == null) goto L16;
        int r13 = AndroidUtilities.dp(32.0f);
    L17:
        r03.leftMargin = r13;
    L22:
        this.textView.setLayoutParams(r03);
        float r14 = 18.0f;
        if (LocaleController.isRTL == false) goto L25;
        float r04 = 8.0f;
    L26:
        int r05 = AndroidUtilities.dp(r04);
        if (LocaleController.isRTL == true) goto L30;
        r14 = 8.0f;
    L30:
        setPadding(r05, 0, AndroidUtilities.dp(r14), 0);
        ImageView r06 = this.rightIcon;
        if (r6 != 0) goto L34;
        r06.setVisibility(8);
        return;
    L34:
        r06.setVisibility(0);
        this.rightIcon.setImageResource(r6);
        return;
    L25:
        r04 = 18.0f;
        goto L26
    L16:
        r13 = 0;
        goto L17
    L18:
        if (r2 == null) goto L20;
        int r15 = AndroidUtilities.dp(32.0f);
    L21:
        r03.rightMargin = r15;
        goto L22
    L20:
        r15 = 0;
        goto L21
    }

    public void setSelectorColor(int r2) {
        if (this.selectorColor == r2) goto L6;
        this.selectorColor = r2;
        updateBackground();
        return;
    }

    public void setSubtext(CharSequence r13) {
        int r1 = 8;
        int r2 = 0;
        boolean r3 = true;
        if (this.subtextView != null) goto L8;
        TextView r0 = new TextView(getContext());
        this.subtextView = r0;
        r0.setLines(1);
        this.subtextView.setSingleLine(true);
        int r4 = 3;
        this.subtextView.setGravity(3);
        this.subtextView.setEllipsize(TextUtils.TruncateAt.END);
        this.subtextView.setTextColor(getThemedColor(Theme.key_groupcreate_sectionText));
        this.subtextView.setVisibility(8);
        this.subtextView.setTextSize(1, 13.0f);
        this.subtextView.setPadding(this.textView.getPaddingLeft(), 0, this.textView.getPaddingRight(), 0);
        TextView r02 = this.subtextView;
        if (LocaleController.isRTL == false) goto L7;
        r4 = 5;
    L7:
        addView(r02, LayoutHelper.createFrame(-2, -2.0f, r4 | 16, 0.0f, 10.0f, 0.0f, 0.0f));
    L8:
        boolean r03 = TextUtils.isEmpty(r13);
        boolean r42 = !r03;
        if (this.subtextView.getVisibility() == 0) goto L12;
        r3 = false;
    L12:
        if (r42 == r3) goto L20;
        TextView r32 = this.subtextView;
        if (r03 == true) goto L16;
        r1 = 0;
    L16:
        r32.setVisibility(r1);
        FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        if (r03 == true) goto L19;
        r2 = AndroidUtilities.dp(10.0f);
    L19:
        r12.bottomMargin = r2;
        this.textView.setLayoutParams(r12);
    L20:
        this.subtextView.setText(r13);
    }

    public void setSubtextColor(int r1) {
        TextView r0 = this.subtextView;
        if (r0 == null) goto L6;
        r0.setTextColor(r1);
        return;
    }

    public void setText(CharSequence r1) {
        this.textView.setText(r1);
    }

    public void setTextAndIcon(CharSequence r2, int r3) {
        setTextAndIcon(r2, r3, null);
    }

    public void setTextAndIcon(CharSequence r6, int r7, Drawable r8) {
        this.textView.setText(r6);
        if (r7 != 0) goto L9;
        if (r8 != null) goto L9;
        if (this.checkView != null) goto L9;
        this.iconResId = 0;
        this.imageView.setVisibility(4);
        this.textView.setPadding(0, 0, 0, 0);
    L35:
        TextView r72 = this.subtextView;
        if (r72 == null) goto L39;
        r72.setPadding(this.textView.getPaddingLeft(), 0, this.textView.getPaddingRight(), 0);
        return;
    L39:
        return;
    L9:
        if (r8 == null) goto L11;
        this.iconResId = 0;
        this.imageView.setImageDrawable(r8);
    L12:
        this.imageView.setVisibility(0);
        AnimatedEmojiSpan.TextViewEmojis r0 = this.textView;
        float r2 = 0.0f;
        if (this.checkViewLeft == true) goto L15;
        if (r7 != 0) goto L22;
        if (r8 != null) goto L22;
        float r1 = 0.0f;
    L23:
        int r12 = AndroidUtilities.dp(r1);
    L25:
        if (this.checkViewLeft == false) goto L31;
        if (r7 != 0) goto L28;
        if (r8 != null) goto L28;
    L29:
        int r73 = AndroidUtilities.dp(r2);
    L34:
        r0.setPadding(r12, 0, r73, 0);
    L28:
        r2 = 43.0f;
        goto L29
    L31:
        if (this.checkView == null) goto L33;
        r73 = AndroidUtilities.dp(43.0f);
        goto L34
    L33:
        r73 = 0;
    L22:
        r1 = 43.0f;
        goto L23
    L15:
        if (this.checkView == null) goto L17;
        r12 = AndroidUtilities.dp(43.0f);
        goto L25
    L17:
        r12 = 0;
        goto L25
    L11:
        this.iconResId = r7;
        this.imageView.setImageResource(r7);
        goto L12
    }

    public void setTextAndIcon(CharSequence r5, ImageLocation r6, String r7, Drawable r8, Object r9) {
        this.textView.setText(r5);
        AnimatedEmojiSpan.TextViewEmojis r52 = this.textView;
        if (this.checkViewLeft == true) goto L5;
    L6:
        int r0 = AndroidUtilities.dp(43.0f);
    L9:
        if (this.checkViewLeft == false) goto L12;
    L10:
        int r2 = AndroidUtilities.dp(43.0f);
    L15:
        r52.setPadding(r0, 0, r2, 0);
        if (this.backupImageView != null) goto L22;
        BackupImageView r53 = new BackupImageView(getContext());
        this.backupImageView = r53;
        r53.setRoundRadius(AndroidUtilities.dp(5.0f));
        BackupImageView r54 = this.backupImageView;
        if (LocaleController.isRTL == false) goto L20;
        int r02 = 5;
    L21:
        addView(r54, LayoutHelper.createFrame(28, 28, r02 | 16));
        goto L22
    L20:
        r02 = 3;
    L22:
        this.imageView.setVisibility(4);
        this.backupImageView.setImage(r6, r7, r8, r9);
        return;
    L12:
        if (this.checkView != null) goto L10;
        r2 = 0;
        goto L15
    L5:
        if (this.checkView != null) goto L6;
        r0 = 0;
        goto L9
    }

    public void setTextColor(int r2) {
        if (this.textColor == r2) goto L6;
        AnimatedEmojiSpan.TextViewEmojis r0 = this.textView;
        this.textColor = r2;
        r0.setTextColor(r2);
        return;
    }

    public void updateBackground() {
        int r0 = this.selectorColor;
        int r1 = this.selectorRad;
        setBackground(Theme.createRadSelectorDrawable(r0, r1, r1));
        UIUtil.applyScaleStateListAnimator(this, this.selectorRad, this.top, this.bottom, 3, 0.04f, 1.5f);
    }

    public void updateSelectorBackground(boolean r2, boolean r3) {
        if (this.top == r2) goto L5;
    L7:
        this.top = r2;
        this.bottom = r3;
        updateBackground();
        return;
    L5:
        if (this.bottom != r3) goto L7;
    }

    public void updateSelectorBackground(boolean r2, boolean r3, int r4) {
        if (this.top == r2) goto L5;
    L9:
        this.top = r2;
        this.bottom = r3;
        this.selectorRad = r4;
        updateBackground();
        return;
    L5:
        if (this.bottom != r3) goto L9;
        if (this.selectorRad != r4) goto L9;
    }
}
