package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;

/* loaded from: classes6.dex */
public class GroupCreateUserCell extends FrameLayout {
    private ValueAnimator animator;
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private TL_account.RequirementToContact blockedOverridden;
    private CheckBox2 checkBox;
    private int checkBoxType;
    private float checkProgress;
    private int currentAccount;
    public boolean currentMiniapps;
    private CharSequence currentName;
    private Object currentObject;
    public boolean currentPremium;
    private CharSequence currentStatus;
    private boolean drawDivider;
    private boolean forceDarkTheme;
    private boolean isChecked;
    private TLRPC.FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private Paint lockBackgroundPaint;
    private Drawable lockDrawable;
    private SimpleTextView nameTextView;
    private ImageView openChatView;
    private int padding;
    private Paint paint;
    private boolean premiumBlocked;
    private final AnimatedFloat premiumBlockedT;
    private PremiumGradient.PremiumGradientTools premiumGradient;
    Theme.ResourcesProvider resourcesProvider;
    private boolean showPremiumBlocked;
    private boolean showSelfAsSaved;
    private final AnimatedFloat starsBlockedT;
    private long starsPriceBlocked;
    private SimpleTextView statusTextView;

    /* renamed from: org.telegram.ui.Cells.GroupCreateUserCell$1, reason: invalid class name */
    public class AnonymousClass1 extends SimpleTextView {
        final /* synthetic */ GroupCreateUserCell this$0;

        public AnonymousClass1(GroupCreateUserCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public boolean setText(CharSequence r3, boolean r4) {
            return super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Cells.GroupCreateUserCell$2, reason: invalid class name */
    public class AnonymousClass2 extends Drawable {
        final /* synthetic */ PremiumGradient.PremiumGradientTools val$gradientTools;

        public AnonymousClass2(PremiumGradient.PremiumGradientTools r1) {
            this.val$gradientTools = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r5) {
            this.val$gradientTools.gradientMatrix(getBounds());
            r5.drawCircle(getBounds().centerX(), getBounds().centerY(), Math.min(getBounds().width(), getBounds().height()) / 2.0f, this.val$gradientTools.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: org.telegram.ui.Cells.GroupCreateUserCell$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCreateUserCell this$0;

        public AnonymousClass3(GroupCreateUserCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            GroupCreateUserCell.m6952$$Nest$fputanimator(this.this$0, null);
        }
    }

    public static /* synthetic */ void $r8$lambda$JheSkoT0rNf03q0y09kpgIGdO7E(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$bLD87MONlUAJwW0Ry4K3Mujz5Cw(GroupCreateUserCell r0, ValueAnimator r1) {
        r0.lambda$setChecked$2(r1);
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6952$$Nest$fputanimator(GroupCreateUserCell r0, ValueAnimator r1) {
        r0.animator = r1;
    }

    public GroupCreateUserCell(Context r8, int r9, int r10, boolean r11) {
        this(r8, r9, r10, r11, false, null);
    }

    public GroupCreateUserCell(Context r28, int r29, int r30, boolean r31, boolean r32, Theme.ResourcesProvider r33) {
        super(r28);
        this.currentAccount = UserConfig.selectedAccount;
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.premiumBlockedT = new AnimatedFloat(this, 0, 350, r6);
        this.starsBlockedT = new AnimatedFloat(this, 0, 350, r6);
        this.resourcesProvider = r33;
        this.checkBoxType = r29;
        this.forceDarkTheme = r32;
        this.drawDivider = false;
        this.padding = r30;
        this.showSelfAsSaved = r31;
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r2 = new BackupImageView(r28);
        this.avatarImageView = r2;
        r2.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f));
        BackupImageView r22 = this.avatarImageView;
        boolean r3 = LocaleController.isRTL;
        int r4 = 5;
        if (r3 == false) goto L5;
        int r62 = 5;
    L6:
        int r12 = r62 | 48;
        float r63 = 0.0f;
        if (r3 == false) goto L9;
        float r13 = 0.0f;
    L10:
        if (r3 == false) goto L12;
        float r15 = this.padding + 13;
    L13:
        addView(r22, LayoutHelper.createFrame(46, 46.0f, r12, r13, 6.0f, r15, 0.0f));
        AnonymousClass1 r23 = new AnonymousClass1(this, r28);
        this.nameTextView = r23;
        NotificationCenter.listenEmojiLoading(r23);
        SimpleTextView r24 = this.nameTextView;
        if (this.forceDarkTheme == false) goto L16;
        int r34 = Theme.key_voipgroup_nameText;
    L17:
        r24.setTextColor(Theme.getColor(r34, r33));
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        this.nameTextView.setTextSize(16);
        SimpleTextView r25 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L20;
        int r10 = 5;
    L21:
        r25.setGravity(r10 | 48);
        SimpleTextView r26 = this.nameTextView;
        boolean r102 = LocaleController.isRTL;
        if (r102 == false) goto L24;
        int r11 = 5;
    L25:
        int r14 = r11 | 48;
        int r112 = 72;
        if (r102 == false) goto L28;
        int r122 = 28;
    L29:
        float r152 = r122 + this.padding;
        if (r102 == false) goto L32;
        int r103 = 72;
    L33:
        addView(r26, LayoutHelper.createFrame(-1, 20.0f, r14, r152, 10.0f, r103 + r13, 0.0f));
        SimpleTextView r27 = new SimpleTextView(r28);
        this.statusTextView = r27;
        r27.setTextSize(14);
        SimpleTextView r210 = this.statusTextView;
        if (LocaleController.isRTL == false) goto L36;
        int r104 = 5;
    L37:
        r210.setGravity(r104 | 48);
        SimpleTextView r211 = this.statusTextView;
        boolean r105 = LocaleController.isRTL;
        if (r105 == false) goto L40;
        int r123 = 5;
    L41:
        int r222 = r123 | 48;
        if (r105 == false) goto L44;
        int r124 = 28;
    L45:
        float r125 = r124 + this.padding;
        if (r105 == true) goto L49;
        r112 = 28;
    L49:
        addView(r211, LayoutHelper.createFrame(-1, 20.0f, r222, r125, 32.0f, r112 + r13, 0.0f));
        ImageView r212 = new ImageView(r28);
        this.openChatView = r212;
        r212.setImageResource(R.drawable.msg_arrow_forward);
        this.openChatView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_player_actionBarSelector)));
        this.openChatView.setScaleType(ImageView.ScaleType.CENTER);
        this.openChatView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
        this.openChatView.setVisibility(8);
        ImageView r213 = this.openChatView;
        boolean r106 = LocaleController.isRTL;
        if (r106 == false) goto L52;
        int r113 = 3;
    L53:
        int r142 = r113 | 16;
        if (r106 == false) goto L56;
        float r153 = 10.0f;
    L57:
        if (r106 == false) goto L59;
        float r17 = 0.0f;
    L60:
        addView(r213, LayoutHelper.createFrame(40, 40.0f, r142, r153, 0.0f, r17, 0.0f));
        if (r29 != 1) goto L74;
        CheckBox2 r214 = new CheckBox2(r28, 21, r33);
        this.checkBox = r214;
        r214.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(3);
        CheckBox2 r215 = this.checkBox;
        boolean r35 = LocaleController.isRTL;
        if (r35 == true) goto L66;
        r4 = 3;
    L66:
        int r9 = r4 | 48;
        if (r35 == false) goto L69;
        float r107 = 0.0f;
    L70:
        if (r35 == false) goto L72;
        r63 = this.padding + 39;
    L72:
        addView(r215, LayoutHelper.createFrame(24, 24.0f, r9, r107, 33.0f, r63, 0.0f));
    L76:
        setWillNotDraw(false);
        return;
    L69:
        r107 = this.padding + 40;
        goto L70
    L74:
        if (r29 != 2) goto L76;
        Paint r36 = new Paint(1);
        this.paint = r36;
        r36.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        goto L76
    L59:
        r17 = 10.0f;
        goto L60
    L56:
        r153 = 0.0f;
        goto L57
    L52:
        r113 = 5;
        goto L53
    L44:
        r124 = 72;
        goto L45
    L40:
        r123 = 3;
        goto L41
    L36:
        r104 = 3;
        goto L37
    L32:
        r103 = 28;
        goto L33
    L28:
        r122 = 72;
        goto L29
    L24:
        r11 = 3;
        goto L25
    L20:
        r10 = 3;
        goto L21
    L16:
        r34 = Theme.key_windowBackgroundWhiteBlackText;
        goto L17
    L12:
        r15 = 0.0f;
        goto L13
    L9:
        r13 = this.padding + 13;
        goto L10
    L5:
        r62 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$setChecked$2(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        if (this.isChecked == false) goto L5;
        float r0 = 1.0f - (0.18f * r42);
    L6:
        this.avatarImageView.setScaleX(r0);
        this.avatarImageView.setScaleY(r0);
        if (this.isChecked == true) goto L10;
        r42 = 1.0f - r42;
    L10:
        this.checkProgress = r42;
        invalidate();
        return;
    L5:
        r0 = 0.82f + (0.18f * r42);
        goto L6
    }

    public static Drawable makeMiniAppsDrawable(Context r1, boolean r2) {
        AvatarDrawable r12 = new AvatarDrawable();
        r12.setAvatarType(8);
        if (r2 == false) goto L5;
        float r22 = 0.8f;
    L6:
        r12.setScaleSize(r22);
        r12.setColor(Theme.getColor(Theme.key_avatar_backgroundBlue), Theme.getColor(Theme.key_avatar_background2Blue));
        return r12;
    L5:
        r22 = 1.1f;
        goto L6
    }

    public static Drawable makePremiumUsersDrawable(Context r7, boolean r8) {
        CombinedDrawable r0 = new CombinedDrawable(new AnonymousClass2(new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient2, Theme.key_premiumGradient1, -1, -1, -1, null)), r7.getResources().getDrawable(R.drawable.msg_settings_premium), 0, 0);
        if (r8 == false) goto L5;
        r0.setIconSize(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
    L5:
        return r0;
    }

    private void updatePremiumBlocked(boolean r7) {
        TL_account.RequirementToContact r1 = null;
        if (this.showPremiumBlocked == false) goto L11;
        TL_account.RequirementToContact r0 = this.blockedOverridden;
        if (r0 == null) goto L8;
        r1 = r0;
        goto L11
    L8:
        if ((this.currentObject instanceof TLRPC.User) == false) goto L11;
        r1 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(((TLRPC.User) this.currentObject).id);
    L11:
        if (this.premiumBlocked == DialogObject.isPremiumBlocked(r1)) goto L13;
    L16:
        this.premiumBlocked = DialogObject.isPremiumBlocked(r1);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r1);
        if (r7 == true) goto L19;
        this.premiumBlockedT.set(this.premiumBlocked, true);
    L19:
        invalidate();
        return;
    L13:
        if (this.starsPriceBlocked != DialogObject.getMessagesStarsPrice(r1)) goto L16;
    }

    private void updateTextMargins() {
        int r1 = 28;
        if (LocaleController.isRTL == false) goto L6;
        int r0 = 72;
    L9:
        int r02 = r0 + this.padding;
        if (LocaleController.isRTL == true) goto L12;
        r1 = 72;
    L15:
        int r12 = r1 + this.padding;
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) this.nameTextView.getLayoutParams();
        float r03 = r02;
        r2.rightMargin = AndroidUtilities.dp(r03);
        float r13 = r12;
        r2.leftMargin = AndroidUtilities.dp(r13);
        FrameLayout.LayoutParams r5 = (FrameLayout.LayoutParams) this.statusTextView.getLayoutParams();
        r5.rightMargin = AndroidUtilities.dp(r03);
        r5.leftMargin = AndroidUtilities.dp(r13);
        return;
    L12:
        if (this.openChatView.getVisibility() != 0) goto L15;
        r1 = 64;
        goto L15
    L6:
        if (this.openChatView.getVisibility() != 0) goto L8;
        r0 = 64;
        goto L9
    L8:
        r0 = 28;
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r15) {
        super.dispatchDraw(r15);
        float r0 = this.premiumBlockedT.set(this.premiumBlocked);
        if (r0 <= 0.0f) goto L19;
        float r1 = (this.avatarImageView.getY() + (this.avatarImageView.getHeight() / 2.0f)) + AndroidUtilities.dp(18.0f);
        float r4 = (this.avatarImageView.getX() + (this.avatarImageView.getWidth() / 2.0f)) + AndroidUtilities.dp(18.0f);
        r15.save();
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        r15.drawCircle(r4, r1, AndroidUtilities.dp(11.33f) * r0, Theme.dialogs_onlineCirclePaint);
        if (this.blockedOverridden != null) goto L11;
        if (this.premiumGradient != null) goto L9;
        this.premiumGradient = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, this.resourcesProvider);
    L9:
        this.premiumGradient.gradientMatrix((int) (r4 - AndroidUtilities.dp(10.0f)), (int) (r1 - AndroidUtilities.dp(10.0f)), (int) (AndroidUtilities.dp(10.0f) + r4), (int) (AndroidUtilities.dp(10.0f) + r1), 0.0f, 0.0f);
        Paint r2 = this.premiumGradient.paint;
    L14:
        r15.drawCircle(r4, r1, AndroidUtilities.dp(10.0f) * r0, r2);
        if (this.lockDrawable != null) goto L17;
        Drawable r22 = getContext().getResources().getDrawable(R.drawable.msg_mini_lock2).mutate();
        this.lockDrawable = r22;
        r22.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L17:
        this.lockDrawable.setBounds((int) (r4 - (((r2.getIntrinsicWidth() / 2.0f) * 0.875f) * r0)), (int) (r1 - (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r0)), (int) (r4 + (((this.lockDrawable.getIntrinsicWidth() / 2.0f) * 0.875f) * r0)), (int) (r1 + (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r0)));
        this.lockDrawable.setAlpha((int) (r0 * 255.0f));
        this.lockDrawable.draw(r15);
        r15.restore();
        return;
    L11:
        if (this.lockBackgroundPaint != null) goto L13;
        this.lockBackgroundPaint = new Paint();
    L13:
        this.lockBackgroundPaint.setColor(Theme.getColor(Theme.key_avatar_backgroundGray, this.resourcesProvider));
        r2 = this.lockBackgroundPaint;
        goto L14
    }

    public CheckBox2 getCheckBox() {
        return this.checkBox;
    }

    public Object getObject() {
        return this.currentObject;
    }

    public SimpleTextView getStatusTextView() {
        return this.statusTextView;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isBlocked() {
        return this.premiumBlocked;
    }

    public boolean isChecked() {
        CheckBox2 r0 = this.checkBox;
        if (r0 == null) goto L7;
        return r0.isChecked();
    L7:
        return this.isChecked;
    }

    @Override // android.view.View
    public void onDraw(Canvas r22) {
        super.onDraw(r22);
        float r2 = 0.0f;
        if (this.premiumBlockedT.set(this.premiumBlocked) > 0.0f) goto L13;
        if (this.checkBoxType != 2) goto L13;
        if (this.isChecked == false) goto L10;
    L11:
        this.paint.setColor(Theme.getColor(Theme.key_checkboxSquareBackground, this.resourcesProvider));
        float r1 = this.avatarImageView.getLeft() + (this.avatarImageView.getMeasuredWidth() / 2);
        float r3 = this.avatarImageView.getTop() + (this.avatarImageView.getMeasuredHeight() / 2);
        float r4 = AndroidUtilities.dp(18.0f) + (AndroidUtilities.dp(4.0f) * this.checkProgress);
        float r5 = this.avatarImageView.getScaleX();
        float r12 = Math.max(0.0f, ((this.avatarImageView.getImageReceiver().getRoundRadius()[0] * r5) + r4) - ((this.avatarImageView.getMeasuredWidth() / 2.0f) * r5));
        r22.drawRoundRect(r1 - r4, r3 - r4, r1 + r4, r3 + r4, r12, r12, this.paint);
        goto L13
    L10:
        if (this.checkProgress > 0.0f) goto L11;
    L13:
        if (this.drawDivider == true) goto L15;
        return;
    L15:
        if (LocaleController.isRTL == false) goto L17;
        float r13 = 0.0f;
    L18:
        int r14 = AndroidUtilities.dp(r13);
        int r32 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L22;
        r2 = this.padding + 72;
    L22:
        int r33 = r32 - AndroidUtilities.dp(r2);
        if (this.forceDarkTheme == false) goto L26;
        Theme.dividerExtraPaint.setColor(Theme.getColor(Theme.key_voipgroup_actionBar, this.resourcesProvider));
        r22.drawRect(r14, getMeasuredHeight() - 1, r33, getMeasuredHeight(), Theme.dividerExtraPaint);
        return;
    L26:
        r22.drawRect(r14, getMeasuredHeight() - 1, r33, getMeasuredHeight(), Theme.getThemePaint("paintDivider", this.resourcesProvider));
        return;
    L17:
        r13 = this.padding + 72;
        goto L18
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        if (isChecked() == false) goto L6;
        r1.setCheckable(true);
        r1.setChecked(true);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r32 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
        Object r0 = this.currentObject;
        if ((r0 instanceof String) == true) goto L5;
    L9:
        float r02 = 58.0f;
    L10:
        super.onMeasure(r32, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r02), TLObject.FLAG_30));
        return;
    L5:
        if ("premium".equalsIgnoreCase((String) r0) == true) goto L9;
        if ("miniapps".equalsIgnoreCase((String) this.currentObject) == true) goto L9;
        r02 = 50.0f;
        goto L10
    }

    public void overridePremiumBlocked(TL_account.RequirementToContact r2, boolean r3) {
        this.showPremiumBlocked = true;
        this.blockedOverridden = r2;
        updatePremiumBlocked(r3);
    }

    public void recycle() {
        this.avatarImageView.getImageReceiver().cancelLoadImage();
    }

    public void setCheckBoxEnabled(boolean r1) {
        CheckBox2 r0 = this.checkBox;
        if (r0 == null) goto L6;
        r0.setEnabled(r1);
        return;
    }

    public void setChecked(boolean r3, boolean r4) {
        CheckBox2 r0 = this.checkBox;
        if (r0 == null) goto L7;
        r0.setChecked(r3, r4);
        return;
    L7:
        if (this.checkBoxType == 2) goto L9;
        return;
    L9:
        if (this.isChecked == r3) goto L32;
        this.isChecked = r3;
        ValueAnimator r32 = this.animator;
        if (r32 == null) goto L14;
        r32.cancel();
    L14:
        if (r4 == false) goto L16;
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r33;
        r33.addUpdateListener(new GroupCreateUserCell$$ExternalSyntheticLambda1(this));
        this.animator.addListener(new AnonymousClass3(this));
        this.animator.setDuration(180);
        this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.animator.start();
    L29:
        invalidate();
        return;
    L16:
        BackupImageView r34 = this.avatarImageView;
        float r02 = 0.82f;
        float r1 = 1.0f;
        if (this.isChecked == false) goto L19;
        float r42 = 0.82f;
    L20:
        r34.setScaleX(r42);
        BackupImageView r35 = this.avatarImageView;
        if (this.isChecked == true) goto L24;
        r02 = 1.0f;
    L24:
        r35.setScaleY(r02);
        if (this.isChecked == true) goto L28;
        r1 = 0.0f;
    L28:
        this.checkProgress = r1;
        goto L29
    L19:
        r42 = 1.0f;
        goto L20
    }

    public void setDrawDivider(boolean r1) {
        this.drawDivider = r1;
        invalidate();
    }

    public void setForbiddenCheck(boolean r1) {
        this.checkBox.setForbidden(r1);
    }

    public void setMiniapps() {
        this.currentMiniapps = true;
        this.currentObject = "miniapps";
        this.avatarImageView.setImageDrawable(makeMiniAppsDrawable(getContext(), false));
        this.nameTextView.setText(LocaleController.getString(R.string.PrivacyMiniapps));
        SimpleTextView r0 = this.statusTextView;
        int r1 = Theme.key_windowBackgroundWhiteGrayText;
        r0.setTag(Integer.valueOf(r1));
        SimpleTextView r02 = this.statusTextView;
        if (this.forceDarkTheme == false) goto L5;
        r1 = Theme.key_voipgroup_lastSeenText;
    L5:
        r02.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        SimpleTextView r03 = this.statusTextView;
        r03.setEmojiColor(r03.getTextColor());
        this.statusTextView.setText(LocaleController.getString(R.string.PrivacyMiniappsText));
        this.openChatView.setVisibility(8);
        updateTextMargins();
    }

    public void setObject(Object r1, CharSequence r2, CharSequence r3) {
        this.currentObject = r1;
        this.currentStatus = r3;
        this.currentName = r2;
        this.drawDivider = false;
        this.currentPremium = false;
        this.currentMiniapps = false;
        this.openChatView.setVisibility(8);
        updateTextMargins();
        update(0);
    }

    public void setObject(TLObject r1, CharSequence r2, CharSequence r3, boolean r4) {
        setObject(r1, r2, r3);
        this.drawDivider = r4;
    }

    public void setOpenChatView(Runnable r3) {
        this.openChatView.setVisibility(0);
        this.openChatView.setOnClickListener(new GroupCreateUserCell$$ExternalSyntheticLambda0(r3));
        updateTextMargins();
    }

    public void setPremium() {
        this.currentPremium = true;
        this.currentObject = "premium";
        this.avatarImageView.setImageDrawable(makePremiumUsersDrawable(getContext(), false));
        this.nameTextView.setText(LocaleController.getString(R.string.PrivacyPremium));
        SimpleTextView r0 = this.statusTextView;
        int r1 = Theme.key_windowBackgroundWhiteGrayText;
        r0.setTag(Integer.valueOf(r1));
        SimpleTextView r02 = this.statusTextView;
        if (this.forceDarkTheme == false) goto L5;
        r1 = Theme.key_voipgroup_lastSeenText;
    L5:
        r02.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        SimpleTextView r03 = this.statusTextView;
        r03.setEmojiColor(r03.getTextColor());
        this.statusTextView.setText(LocaleController.getString(R.string.PrivacyPremiumText));
        this.openChatView.setVisibility(8);
        updateTextMargins();
    }

    public void update(int r14) {
        Object r0 = this.currentObject;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.currentPremium == false) goto L7;
        return;
    L7:
        if (this.currentMiniapps == true) goto L263;
        TLRPC.Chat r6 = null;
        if ((r0 instanceof String) == false) goto L73;
        ((FrameLayout.LayoutParams) this.nameTextView.getLayoutParams()).topMargin = AndroidUtilities.dp(15.0f);
        ViewGroup.LayoutParams r142 = this.avatarImageView.getLayoutParams();
        ViewGroup.LayoutParams r02 = this.avatarImageView.getLayoutParams();
        int r4 = AndroidUtilities.dp(38.0f);
        r02.height = r4;
        r142.width = r4;
        CheckBox2 r143 = this.checkBox;
        if (r143 == null) goto L17;
        ((FrameLayout.LayoutParams) r143.getLayoutParams()).topMargin = AndroidUtilities.dp(25.0f);
        boolean r144 = LocaleController.isRTL;
        CheckBox2 r03 = this.checkBox;
        if (r144 == false) goto L16;
        ((FrameLayout.LayoutParams) r03.getLayoutParams()).rightMargin = AndroidUtilities.dp(31.0f);
        goto L17
    L16:
        ((FrameLayout.LayoutParams) r03.getLayoutParams()).leftMargin = AndroidUtilities.dp(32.0f);
    L17:
        String r145 = (String) this.currentObject;
        r145.getClass();
        char r12 = 65535;
        switch(r145.hashCode()) {
            case -1716307998: goto L57;
            case -1237460524: goto L53;
            case -1197490811: goto L49;
            case -567451565: goto L45;
            case -268161860: goto L41;
            case 3029900: goto L37;
            case 3496342: goto L33;
            case 104264043: goto L29;
            case 151051367: goto L25;
            case 1432626128: goto L21;
            default: goto L60;
        };
    L60:
        switch(r12) {
            case 0: goto L71;
            case 1: goto L70;
            case 2: goto L69;
            case 3: goto L68;
            case 4: goto L67;
            case 5: goto L66;
            case 6: goto L65;
            case 7: goto L64;
            case 8: goto L63;
            case 9: goto L62;
            default: goto L72;
        };
    L62:
        this.avatarDrawable.setAvatarType(7);
        goto L72
    L63:
        this.avatarDrawable.setAvatarType(23);
        goto L72
    L64:
        this.avatarDrawable.setAvatarType(9);
        goto L72
    L65:
        this.avatarDrawable.setAvatarType(10);
        goto L72
    L66:
        this.avatarDrawable.setAvatarType(8);
        goto L72
    L67:
        this.avatarDrawable.setAvatarType(24);
        goto L72
    L68:
        this.avatarDrawable.setAvatarType(4);
        goto L72
    L69:
        this.avatarDrawable.setAvatarType(5);
        goto L72
    L70:
        this.avatarDrawable.setAvatarType(6);
        goto L72
    L71:
        this.avatarDrawable.setAvatarType(11);
    L72:
        this.lastName = null;
        this.nameTextView.setText(this.currentName, true);
        this.statusTextView.setText(null);
        this.avatarImageView.setImage(null, "50_50", this.avatarDrawable);
    L247:
        BackupImageView r146 = this.avatarImageView;
        if (r6 != null) goto L250;
    L252:
        boolean r04 = false;
    L253:
        r146.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f, false, r04));
        CharSequence r147 = this.currentStatus;
        if (r147 == null) goto L259;
        this.statusTextView.setText(r147, true);
        SimpleTextView r148 = this.statusTextView;
        int r05 = Theme.key_windowBackgroundWhiteGrayText;
        r148.setTag(Integer.valueOf(r05));
        SimpleTextView r149 = this.statusTextView;
        if (this.forceDarkTheme == false) goto L258;
        r05 = Theme.key_voipgroup_lastSeenText;
    L258:
        r149.setTextColor(Theme.getColor(r05, this.resourcesProvider));
        SimpleTextView r1410 = this.statusTextView;
        r1410.setEmojiColor(r1410.getTextColor());
    L259:
        updatePremiumBlocked(false);
        return;
    L250:
        if (r6.forum == false) goto L252;
        r04 = true;
        goto L253
    L21:
        if (r145.equals("channels") == false) goto L60;
        r12 = '\t';
        goto L60
    L25:
        if (r145.equals("existing_chats") == false) goto L60;
        r12 = '\b';
        goto L60
    L29:
        if (r145.equals("muted") == false) goto L60;
        r12 = 7;
        goto L60
    L33:
        if (r145.equals("read") == false) goto L60;
        r12 = 6;
        goto L60
    L37:
        if (r145.equals("bots") == false) goto L60;
        r12 = 5;
        goto L60
    L41:
        if (r145.equals("new_chats") == false) goto L60;
        r12 = 4;
        goto L60
    L45:
        if (r145.equals("contacts") == false) goto L60;
        r12 = 3;
        goto L60
    L49:
        if (r145.equals("non_contacts") == false) goto L60;
        r12 = 2;
        goto L60
    L53:
        if (r145.equals("groups") == false) goto L60;
        r12 = 1;
        goto L60
    L57:
        if (r145.equals("archived") == false) goto L60;
        r12 = 0;
        goto L60
    L73:
        CharSequence r06 = this.currentStatus;
        if (r06 != null) goto L76;
    L78:
        ((FrameLayout.LayoutParams) this.nameTextView.getLayoutParams()).topMargin = AndroidUtilities.dp(10.0f);
    L79:
        ViewGroup.LayoutParams r07 = this.avatarImageView.getLayoutParams();
        ViewGroup.LayoutParams r9 = this.avatarImageView.getLayoutParams();
        int r10 = AndroidUtilities.dp(46.0f);
        r9.height = r10;
        r07.width = r10;
        CheckBox2 r08 = this.checkBox;
        if (r08 == null) goto L85;
        ((FrameLayout.LayoutParams) r08.getLayoutParams()).topMargin = AndroidUtilities.dp(31.0f) + this.padding;
        boolean r09 = LocaleController.isRTL;
        CheckBox2 r3 = this.checkBox;
        if (r09 == false) goto L84;
        ((FrameLayout.LayoutParams) r3.getLayoutParams()).rightMargin = AndroidUtilities.dp(38.0f) + this.padding;
        goto L85
    L84:
        ((FrameLayout.LayoutParams) r3.getLayoutParams()).leftMargin = AndroidUtilities.dp(38.0f) + this.padding;
    L85:
        Object r010 = this.currentObject;
        if ((r010 instanceof TLRPC.User) == false) goto L176;
        TLRPC.User r011 = (TLRPC.User) r010;
        if (this.showSelfAsSaved == true) goto L90;
    L93:
        TLRPC.UserProfilePhoto r2 = r011.photo;
        if (r2 == null) goto L96;
        TLRPC.FileLocation r22 = r2.photo_small;
    L97:
        if (r14 != 0) goto L99;
        String r1411 = null;
    L139:
        this.avatarDrawable.setInfo(this.currentAccount, r011);
        TLRPC.UserStatus r23 = r011.status;
        if (r23 == null) goto L142;
        int r24 = r23.expires;
    L143:
        this.lastStatus = r24;
        CharSequence r25 = this.currentName;
        if (r25 == null) goto L146;
        this.lastName = null;
        this.nameTextView.setText(r25, true);
    L150:
        if (this.currentStatus == null) goto L152;
    L175:
        this.avatarImageView.setForUserOrChat(r011, this.avatarDrawable);
        goto L247
    L152:
        if (r011.bot == false) goto L158;
        SimpleTextView r1412 = this.statusTextView;
        int r26 = Theme.key_windowBackgroundWhiteGrayText;
        r1412.setTag(Integer.valueOf(r26));
        SimpleTextView r1413 = this.statusTextView;
        if (this.forceDarkTheme == false) goto L156;
        r26 = Theme.key_voipgroup_lastSeenText;
    L156:
        r1413.setTextColor(Theme.getColor(r26, this.resourcesProvider));
        this.statusTextView.setText(LocaleController.getString(R.string.Bot));
    L174:
        SimpleTextView r1414 = this.statusTextView;
        r1414.setEmojiColor(r1414.getTextColor());
        goto L175
    L158:
        if (r011.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L170;
        TLRPC.UserStatus r1415 = r011.status;
        if (r1415 == null) goto L164;
        if (r1415.expires > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L170;
    L164:
        if (MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Long.valueOf(r011.id)) == true) goto L170;
        SimpleTextView r1416 = this.statusTextView;
        int r27 = Theme.key_windowBackgroundWhiteGrayText;
        r1416.setTag(Integer.valueOf(r27));
        SimpleTextView r1417 = this.statusTextView;
        if (this.forceDarkTheme == false) goto L169;
        r27 = Theme.key_voipgroup_lastSeenText;
    L169:
        r1417.setTextColor(Theme.getColor(r27, this.resourcesProvider));
        this.statusTextView.setText(LocaleController.formatUserStatus(this.currentAccount, r011));
    L170:
        SimpleTextView r1418 = this.statusTextView;
        int r28 = Theme.key_windowBackgroundWhiteBlueText;
        r1418.setTag(Integer.valueOf(r28));
        SimpleTextView r1419 = this.statusTextView;
        if (this.forceDarkTheme == false) goto L173;
        r28 = Theme.key_voipgroup_listeningText;
    L173:
        r1419.setTextColor(Theme.getColor(r28, this.resourcesProvider));
        this.statusTextView.setText(LocaleController.getString(R.string.Online));
        goto L174
    L146:
        if (r1411 != null) goto L148;
        r1411 = UserObject.getUserName(r011);
    L148:
        this.lastName = r1411;
        this.nameTextView.setText(r1411);
        goto L150
    L142:
        r24 = 0;
        goto L143
    L99:
        if ((MessagesController.UPDATE_MASK_AVATAR & r14) == 0) goto L112;
        TLRPC.FileLocation r32 = this.lastAvatar;
        if (r32 == null) goto L103;
        if (r22 != null) goto L103;
    L111:
        boolean r29 = true;
    L114:
        if (this.currentStatus != null) goto L125;
        if (r29 == true) goto L125;
        if ((MessagesController.UPDATE_MASK_STATUS & r14) == 0) goto L125;
        TLRPC.UserStatus r33 = r011.status;
        if (r33 == null) goto L121;
        int r34 = r33.expires;
    L123:
        if (r34 == this.lastStatus) goto L125;
        r29 = true;
        goto L125
    L121:
        r34 = 0;
    L125:
        if (r29 == false) goto L127;
    L135:
        r1411 = null;
    L136:
        if (r29 == true) goto L139;
        return;
    L127:
        if (this.currentName != null) goto L135;
        if (this.lastName == null) goto L135;
        if ((r14 & MessagesController.UPDATE_MASK_NAME) == 0) goto L135;
        r1411 = UserObject.getUserName(r011);
        if (r1411.equals(this.lastName) == true) goto L136;
        r29 = true;
    L103:
        if (r32 != null) goto L105;
        if (r22 != null) goto L111;
    L105:
        if (r32 == null) goto L112;
        if (r22 == null) goto L112;
        if (r32.volume_id != r22.volume_id) goto L111;
        if (r32.local_id != r22.local_id) goto L111;
    L112:
        r29 = false;
        goto L114
    L96:
        r22 = null;
        goto L97
    L90:
        if (UserObject.isUserSelf(r011) == false) goto L93;
        this.nameTextView.setText(LocaleController.getString(R.string.SavedMessages), true);
        this.statusTextView.setText(null);
        this.avatarDrawable.setAvatarType(1);
        this.avatarImageView.setImage(null, "50_50", this.avatarDrawable, r011);
        ((FrameLayout.LayoutParams) this.nameTextView.getLayoutParams()).topMargin = AndroidUtilities.dp(19.0f);
        return;
    L176:
        TLRPC.Chat r012 = (TLRPC.Chat) r010;
        TLRPC.ChatPhoto r210 = r012.photo;
        if (r210 == null) goto L179;
        TLRPC.FileLocation r211 = r210.photo_small;
    L180:
        if (r14 != 0) goto L182;
        String r1420 = null;
    L210:
        this.avatarDrawable.setInfo(this.currentAccount, r012);
        CharSequence r212 = this.currentName;
        if (r212 == null) goto L213;
        this.lastName = null;
        this.nameTextView.setText(r212, true);
    L217:
        if (this.currentStatus != null) goto L246;
        SimpleTextView r1421 = this.statusTextView;
        int r213 = Theme.key_windowBackgroundWhiteGrayText;
        r1421.setTag(Integer.valueOf(r213));
        SimpleTextView r1422 = this.statusTextView;
        if (this.forceDarkTheme == false) goto L221;
        r213 = Theme.key_voipgroup_lastSeenText;
    L221:
        r1422.setTextColor(Theme.getColor(r213));
        SimpleTextView r1423 = this.statusTextView;
        r1423.setEmojiColor(r1423.getTextColor());
        if (r012.participants_count == 0) goto L230;
        if (ChatObject.isChannel(r012) == true) goto L226;
    L228:
        this.statusTextView.setText(LocaleController.formatPluralString("Members", r012.participants_count, new Object[0]));
        goto L246
    L226:
        if (r012.megagroup == true) goto L228;
        this.statusTextView.setText(LocaleController.formatPluralString("Subscribers", r012.participants_count, new Object[0]));
        goto L246
    L230:
        if (r012.has_geo == false) goto L233;
        this.statusTextView.setText(LocaleController.getString(R.string.MegaLocation));
        goto L246
    L233:
        if (ChatObject.isPublic(r012) == true) goto L241;
        if (ChatObject.isChannel(r012) == true) goto L237;
    L239:
        this.statusTextView.setText(LocaleController.getString(R.string.MegaPrivate));
        goto L246
    L237:
        if (r012.megagroup == true) goto L239;
        this.statusTextView.setText(LocaleController.getString(R.string.ChannelPrivate));
        goto L246
    L241:
        if (ChatObject.isChannel(r012) == true) goto L243;
    L245:
        this.statusTextView.setText(LocaleController.getString(R.string.MegaPublic));
        goto L246
    L243:
        if (r012.megagroup == true) goto L245;
        this.statusTextView.setText(LocaleController.getString(R.string.ChannelPublic));
    L246:
        this.avatarImageView.setForUserOrChat(r012, this.avatarDrawable);
        r6 = r012;
        goto L247
    L213:
        if (r1420 != null) goto L215;
        r1420 = r012.title;
    L215:
        this.lastName = r1420;
        this.nameTextView.setText(r1420);
        goto L217
    L182:
        if ((MessagesController.UPDATE_MASK_AVATAR & r14) == 0) goto L195;
        TLRPC.FileLocation r35 = this.lastAvatar;
        if (r35 == null) goto L186;
        if (r211 != null) goto L186;
    L194:
        boolean r214 = true;
    L196:
        if (r214 == false) goto L198;
    L206:
        r1420 = null;
    L207:
        if (r214 == true) goto L210;
        return;
    L198:
        if (this.currentName != null) goto L206;
        String r36 = this.lastName;
        if (r36 == null) goto L206;
        if ((r14 & MessagesController.UPDATE_MASK_NAME) == 0) goto L206;
        r1420 = r012.title;
        if (r1420.equals(r36) == true) goto L207;
        r214 = true;
    L186:
        if (r35 != null) goto L188;
        if (r211 != null) goto L194;
    L188:
        if (r35 == null) goto L195;
        if (r211 == null) goto L195;
        if (r35.volume_id != r211.volume_id) goto L194;
        if (r35.local_id != r211.local_id) goto L194;
    L195:
        r214 = false;
        goto L196
    L179:
        r211 = null;
        goto L180
    L76:
        if (TextUtils.isEmpty(r06) == false) goto L78;
        ((FrameLayout.LayoutParams) this.nameTextView.getLayoutParams()).topMargin = AndroidUtilities.dp(19.0f);
        goto L79
    }
}
