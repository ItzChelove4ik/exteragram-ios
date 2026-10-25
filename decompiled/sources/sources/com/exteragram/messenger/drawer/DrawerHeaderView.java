package com.exteragram.messenger.drawer;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes.dex */
public class DrawerHeaderView extends FrameLayout {
    private static final int COLOR_KEY_ICON = 0;
    private static final int COLOR_KEY_STATUS = 0;
    private static final int COLOR_KEY_SUBTITLE = 0;
    private static final int COLOR_KEY_TEXT = 0;
    private final AvatarDrawable avatarDrawable;
    private final BackupImageView avatarView;
    private boolean chevronExpanded;
    private final ImageView chevronView;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable exteraBadgeDrawable;
    private int lastProxyColor;
    private int lastProxyState;
    private final SimpleTextView nameView;
    private Runnable onBadgeClick;
    private Runnable onChevronClick;
    private Runnable onNavigateToProfile;
    private Runnable onProxyClick;
    private Runnable onStatusClick;
    private Runnable onThemeToggle;
    private Runnable onThemeToggleLongClick;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable premiumStatusDrawable;
    private final FrameLayout proxyButton;
    private final ImageView proxyIcon;
    private final AnimatedTextView proxyTextView;
    private final SimpleTextView subtitleView;
    private final RLottieDrawable sunDrawable;
    private final FrameLayout themeToggleBg;
    private final RLottieImageView themeToggleView;

    /* renamed from: $r8$lambda$0_aMaLNozxiEjs2wZb6-fjnYrPo, reason: not valid java name */
    public static /* synthetic */ void m1305$r8$lambda$0_aMaLNozxiEjs2wZb6fjnYrPo(DrawerHeaderView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$5yy0insYkI8BeSsbg4dcZmkdCJ0(DrawerHeaderView r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$FiUqVZAF5csa7ivWEDdebTf-oo0, reason: not valid java name */
    public static /* synthetic */ void m1306$r8$lambda$FiUqVZAF5csa7ivWEDdebTfoo0(DrawerHeaderView r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$OvbOPzxfOwMyXfLXl1QIqKio0aE(DrawerHeaderView r0, View r1) {
        return r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$VaBL14oHnIpUMf_n61L4tSGP5A4(DrawerHeaderView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$e-ArYVaRq31HIG1hlFe_B3_D3fQ, reason: not valid java name */
    public static /* synthetic */ void m1307$r8$lambda$eArYVaRq31HIG1hlFe_B3_D3fQ(DrawerHeaderView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$vIADq04SM4GiDLOqH9zmMAgqtWM(DrawerHeaderView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    static {
        COLOR_KEY_TEXT = Theme.key_windowBackgroundWhiteBlackText;
        COLOR_KEY_SUBTITLE = Theme.key_windowBackgroundWhiteGrayText2;
        COLOR_KEY_ICON = Theme.key_windowBackgroundWhiteGrayIcon;
        COLOR_KEY_STATUS = Theme.key_profile_verifiedBackground;
    }

    public DrawerHeaderView(Context r15) {
        super(r15);
        this.lastProxyState = -1;
        this.lastProxyColor = -1;
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r0 = new BackupImageView(r15);
        this.avatarView = r0;
        r0.setRoundRadius(ExteraConfig.getAvatarCorners(72.0f));
        addView(r0, LayoutHelper.createFrame(72, 72.0f, 51, 16.0f, 16.0f, 0.0f, 0.0f));
        r0.setOnClickListener(new DrawerHeaderView$$ExternalSyntheticLambda0(this));
        FrameLayout r02 = new FrameLayout(r15);
        this.themeToggleBg = r02;
        ScaleStateListAnimator.apply(r02);
        r02.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), getThemeToggleBackgroundColor()));
        addView(r02, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 16.0f, 16.0f, 0.0f));
        RLottieDrawable r3 = new RLottieDrawable(R.raw.sun, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), true, null);
        this.sunDrawable = r3;
        r3.setPlayInDirectionOfCustomEndFrame(true);
        RLottieImageView r4 = new RLottieImageView(r15);
        this.themeToggleView = r4;
        r4.setAnimation(r3);
        ImageView.ScaleType r32 = ImageView.ScaleType.CENTER;
        r4.setScaleType(r32);
        r02.addView(r4, LayoutHelper.createFrame(24, 24, 17));
        setThemeToggleStaticState(Theme.isCurrentThemeDark());
        r02.setOnClickListener(new DrawerHeaderView$$ExternalSyntheticLambda1(this));
        r02.setOnLongClickListener(new DrawerHeaderView$$ExternalSyntheticLambda2(this));
        updateThemeToggleColors();
        FrameLayout r03 = new FrameLayout(r15);
        this.proxyButton = r03;
        ScaleStateListAnimator.apply(r03);
        r03.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), getThemeToggleBackgroundColor()));
        addView(r03, LayoutHelper.createFrame(-2, 36.0f, 53, 0.0f, 16.0f, 60.0f, 0.0f));
        r03.setOnClickListener(new DrawerHeaderView$$ExternalSyntheticLambda3(this));
        LinearLayout r1 = new LinearLayout(r15);
        r1.setOrientation(0);
        r1.setGravity(17);
        r1.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), 0);
        r03.addView(r1, LayoutHelper.createFrame(-2, -1.0f));
        ImageView r04 = new ImageView(r15);
        this.proxyIcon = r04;
        r04.setScaleType(r32);
        r1.addView(r04, LayoutHelper.createLinear(24, 24));
        AnimatedTextView r05 = new AnimatedTextView(r15, true, true, true);
        this.proxyTextView = r05;
        r05.setTextSize(AndroidUtilities.dp(13.0f));
        r05.adaptWidth = true;
        r05.setTypeface(AndroidUtilities.bold());
        r05.setTextColor(Theme.getColor(COLOR_KEY_ICON));
        r05.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r05.setVisibility(8);
        r1.addView(r05, LayoutHelper.createLinear(-2, -2, 16));
        FrameLayout r06 = new FrameLayout(r15);
        addView(r06, LayoutHelper.createFrame(-1, 50.0f, 51, 0.0f, 100.0f, 0.0f, 0.0f));
        r06.setOnClickListener(new DrawerHeaderView$$ExternalSyntheticLambda4(this));
        SimpleTextView r12 = new SimpleTextView(r15);
        this.nameView = r12;
        r12.setTextSize(15);
        r12.setTypeface(AndroidUtilities.bold());
        r12.setTextColor(Theme.getColor(COLOR_KEY_TEXT));
        r12.setGravity(19);
        r12.setEllipsizeByGradient(true);
        r12.setCanHideRightDrawable(false);
        r12.setRightDrawableOutside(true);
        r12.setClickable(false);
        r06.addView(r12, LayoutHelper.createFrame(-1, 24.0f, 51, 16.0f, 0.0f, 64.0f, 0.0f));
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r12, AndroidUtilities.dp(22.0f));
        this.premiumStatusDrawable = r5;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r7 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r12, AndroidUtilities.dp(22.0f));
        this.exteraBadgeDrawable = r7;
        r12.setRightDrawable(r5);
        r12.setRightDrawable2(r7);
        r12.setRightDrawableOnClick(new DrawerHeaderView$$ExternalSyntheticLambda5(this));
        r12.setRightDrawable2OnClick(new DrawerHeaderView$$ExternalSyntheticLambda6(this));
        SimpleTextView r13 = new SimpleTextView(r15);
        this.subtitleView = r13;
        r13.setTextSize(12);
        int r52 = COLOR_KEY_SUBTITLE;
        r13.setTextColor(Theme.getColor(r52));
        r13.setMaxLines(1);
        r13.setClickable(false);
        r06.addView(r13, LayoutHelper.createFrame(-2, -2.0f, 51, 16.0f, 26.0f, 64.0f, 0.0f));
        ImageView r14 = new ImageView(r15);
        this.chevronView = r14;
        r14.setImageResource(R.drawable.msg_expand);
        r14.setScaleType(r32);
        r14.setColorFilter(createColorFilter(r52));
        r06.addView(r14, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 22.0f, 0.0f));
    }

    private void applyNameDrawables(Drawable r3, Drawable r4) {
        if (r3 != null) goto L4;
    L6:
        this.nameView.setRightDrawable(r3);
        this.nameView.setRightDrawable2(r4);
        return;
    L4:
        if (r3 != this.nameView.getRightDrawable2()) goto L6;
        this.nameView.setRightDrawable2(null);
        goto L6
    }

    private void applyThemeToggleColors(RLottieDrawable r2, int r3) {
        r2.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
        r2.beginApplyLayerColors();
        r2.setLayerColor("Sunny", r3);
        r2.setLayerColor("Path 6", r3);
        r2.setLayerColor("Path", r3);
        r2.setLayerColor("Path 5", r3);
        r2.commitApplyLayerColors();
    }

    private void clearBadgeDrawables(boolean r4) {
        this.exteraBadgeDrawable.set(null, r4);
        this.exteraBadgeDrawable.setParticles(false, r4);
        this.exteraBadgeDrawable.setColor(null);
    }

    private static PorterDuffColorFilter createColorFilter(int r2) {
        return new PorterDuffColorFilter(Theme.getColor(r2), PorterDuff.Mode.SRC_IN);
    }

    private static int getThemeToggleBackgroundColor() {
        return Theme.multAlpha(Theme.getColor(COLOR_KEY_ICON), 0.075f);
    }

    private int getThemeToggleCurrentFrame(boolean r1) {
        if (r1 == true) goto L4;
        return 0;
    L4:
        return this.sunDrawable.getFramesCount() - 1;
    }

    private int getThemeToggleEndFrame(boolean r1) {
        if (r1 == true) goto L4;
        return 0;
    L4:
        return this.sunDrawable.getFramesCount();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        Runnable r0 = this.onNavigateToProfile;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        resetThemeTogglePressAnimation();
        Runnable r0 = this.onThemeToggle;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ boolean lambda$new$2(View r1) {
        Runnable r0 = this.onThemeToggleLongClick;
        if (r0 == null) goto L6;
        r0.run();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        Runnable r0 = this.onProxyClick;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        Runnable r0 = this.onChevronClick;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$new$5(View r1) {
        Runnable r0 = this.onStatusClick;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$new$6(View r1) {
        Runnable r0 = this.onBadgeClick;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private void resetThemeTogglePressAnimation() {
        this.themeToggleBg.setPressed(false);
        this.themeToggleBg.setScaleX(1.0f);
        this.themeToggleBg.setScaleY(1.0f);
    }

    private void setThemeToggleStaticState(boolean r3) {
        this.sunDrawable.stop();
        this.sunDrawable.setCurrentFrame(getThemeToggleCurrentFrame(r3));
        this.sunDrawable.setCustomEndFrame(getThemeToggleEndFrame(r3));
        this.themeToggleView.invalidate();
    }

    private void syncThemeToggle(boolean r2) {
        syncThemeToggle(Theme.isCurrentThemeDark(), r2);
    }

    private void syncThemeToggle(boolean r3, boolean r4) {
        if (this.sunDrawable.getFramesCount() > 0) goto L5;
        return;
    L5:
        int r0 = getThemeToggleCurrentFrame(r3);
        int r1 = getThemeToggleEndFrame(r3);
        if (r4 == false) goto L10;
        this.sunDrawable.setCustomEndFrame(r1);
        this.themeToggleView.playAnimation();
        return;
    L10:
        if (isAttachedToWindow() == true) goto L13;
        setThemeToggleStaticState(r3);
        return;
    L13:
        this.sunDrawable.stop();
        this.sunDrawable.setCurrentFrame(r0, false, true);
        this.sunDrawable.setCustomEndFrame(r0);
        this.themeToggleView.invalidate();
    }

    private Drawable updateBadgeDrawable(BadgeDTO r4, boolean r5, boolean r6, int r7) {
        if (r4 != null) goto L5;
        clearBadgeDrawables(r5);
        return null;
    L5:
        this.exteraBadgeDrawable.set(r4.getDocumentId(), r5);
        this.exteraBadgeDrawable.setParticles(r6, r5);
        this.exteraBadgeDrawable.setColor(Integer.valueOf(r7));
        return this.exteraBadgeDrawable;
    }

    private void updateThemeToggleColors() {
        RLottieDrawable r0 = this.sunDrawable;
        int r1 = COLOR_KEY_ICON;
        applyThemeToggleColors(r0, Theme.getColor(r1));
        this.themeToggleView.setColorFilter(createColorFilter(r1));
        this.themeToggleView.invalidate();
    }

    public void animateThemeToggle(boolean r2) {
        syncThemeToggle(r2, true);
    }

    public SimpleTextView getNameView() {
        return this.nameView;
    }

    public int[] getThemeTogglePosition() {
        int[] r1 = new int[2];
        this.themeToggleBg.getLocationInWindow(r1);
        r1[0] = r1[0] + (this.themeToggleBg.getMeasuredWidth() / 2);
        r1[1] = r1[1] + (this.themeToggleBg.getMeasuredHeight() / 2);
        return r1;
    }

    public RLottieImageView getThemeToggleView() {
        return this.themeToggleView;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.premiumStatusDrawable.attach();
        this.exteraBadgeDrawable.attach();
        if (this.themeToggleView.isPlaying() == false) goto L5;
        return;
    L5:
        if (DialogsActivity.switchingTheme == true) goto L9;
        syncThemeToggle(false);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.premiumStatusDrawable.detach();
        this.exteraBadgeDrawable.detach();
    }

    public void setChevronExpanded(boolean r3) {
        if (this.chevronExpanded != r3) goto L5;
        return;
    L5:
        this.chevronExpanded = r3;
        this.chevronView.animate().cancel();
        ViewPropertyAnimator r2 = this.chevronView.animate();
        if (r3 == false) goto L8;
        float r32 = 180.0f;
    L9:
        r2.rotation(r32).setDuration(250).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    L8:
        r32 = 0.0f;
        goto L9
    }

    public void setOnBadgeClick(Runnable r1) {
        this.onBadgeClick = r1;
    }

    public void setOnChevronClick(Runnable r1) {
        this.onChevronClick = r1;
    }

    public void setOnNavigateToProfile(Runnable r1) {
        this.onNavigateToProfile = r1;
    }

    public void setOnProxyClick(Runnable r1) {
        this.onProxyClick = r1;
    }

    public void setOnStatusClick(Runnable r1) {
        this.onStatusClick = r1;
    }

    public void setOnThemeToggle(Runnable r1) {
        this.onThemeToggle = r1;
    }

    public void setOnThemeToggleLongClick(Runnable r1) {
        this.onThemeToggleLongClick = r1;
    }

    public void updateColors() {
        this.nameView.setTextColor(Theme.getColor(COLOR_KEY_TEXT));
        SimpleTextView r0 = this.subtitleView;
        int r1 = COLOR_KEY_SUBTITLE;
        r0.setTextColor(Theme.getColor(r1));
        this.chevronView.setColorFilter(createColorFilter(r1));
        this.themeToggleBg.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), getThemeToggleBackgroundColor()));
        this.lastProxyState = -1;
        this.lastProxyColor = -1;
        updateUserInfo();
        if (this.themeToggleView.isPlaying() == true) goto L8;
        if (DialogsActivity.switchingTheme == true) goto L8;
        syncThemeToggle(false);
    L8:
        if (DialogsActivity.switchingTheme == true) goto L10;
    L13:
        updateThemeToggleColors();
        return;
    L10:
        if (Theme.isCurrentThemeDark() == true) goto L13;
    }

    public void updateProxyStatus() {
        boolean r0 = SharedConfig.isProxyEnabled();
        int r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getConnectionState();
        if (r1 != 3) goto L5;
    L8:
        boolean r12 = true;
    L10:
        if (r0 == false) goto L18;
        SharedConfig.ProxyInfo r7 = SharedConfig.currentProxy;
        if (r7 == null) goto L18;
        if (r12 == false) goto L18;
        long r72 = Utilities.clamp(r7.ping, 9999, 0);
        if (r72 <= 0) goto L20;
        int r5 = 2;
    L23:
        if (r5 == this.lastProxyState) goto L25;
        TransitionManager.beginDelayedTransition(this, new ChangeBounds().setDuration(150));
        this.lastProxyState = r5;
    L25:
        FrameLayout r6 = this.proxyButton;
        if (r5 <= 0) goto L46;
        r6.setVisibility(0);
        AnimatedTextView r62 = this.proxyTextView;
        if (r5 != 2) goto L30;
        r62.setVisibility(0);
        this.proxyTextView.setText(LocaleController.formatString(R.string.NavigationDrawerProxyPingShort, new Object[]{Long.valueOf(r72)}), true);
    L31:
        if (r0 == false) goto L35;
        if (r12 == false) goto L35;
        int r2 = Theme.key_windowBackgroundWhiteGreenText;
    L34:
        int r22 = Theme.getColor(r2);
        if (r22 == this.lastProxyColor) goto L39;
        this.lastProxyColor = r22;
        this.proxyButton.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), Theme.multAlpha(r22, 0.075f)));
        this.proxyIcon.setColorFilter(new PorterDuffColorFilter(r22, PorterDuff.Mode.SRC_IN));
        this.proxyTextView.setTextColor(r22);
    L39:
        ImageView r14 = this.proxyIcon;
        if (r0 == false) goto L43;
        if (r12 == false) goto L43;
        int r02 = R.drawable.drawer_proxy_on;
    L44:
        r14.setImageResource(r02);
        return;
    L43:
        r02 = R.drawable.drawer_proxy_off;
    L35:
        r2 = COLOR_KEY_ICON;
        goto L34
    L30:
        r62.setVisibility(8);
        goto L31
    L46:
        r6.setVisibility(8);
        return;
    L20:
        r5 = 1;
    L18:
        if (SharedConfig.proxyList.isEmpty() == true) goto L21;
        r72 = 0;
        goto L20
    L21:
        r72 = 0;
        r5 = 0;
        goto L23
    L5:
        if (r1 == 5) goto L8;
        r12 = false;
        goto L10
    }

    public void updateUserInfo() {
        updateUserInfo(null);
    }

    public void updateUserInfo(BadgeDTO r10) {
        int r0 = UserConfig.selectedAccount;
        TLRPC.User r1 = UserConfig.getInstance(r0).getCurrentUser();
        if (r1 != null) goto L5;
        return;
    L5:
        this.avatarDrawable.setInfo(r0, r1);
        this.avatarView.setRoundRadius(ExteraConfig.getAvatarCorners(72.0f));
        this.avatarView.getImageReceiver().setCurrentAccount(r0);
        this.avatarView.setForUserOrChat(r1, this.avatarDrawable);
        this.nameView.setText(ContactsController.formatName(r1.first_name, r1.last_name));
        this.premiumStatusDrawable.setCurrentAccount(r0);
        this.exteraBadgeDrawable.setCurrentAccount(r0);
        String r2 = DialogObject.getPublicUsername(r1);
        if (r2 != null) goto L8;
    L10:
        String r22 = r1.phone;
        if (r22 != null) goto L13;
    L18:
        this.subtitleView.setText(LocaleController.getString(R.string.NumberUnknown));
    L19:
        long r23 = DialogObject.getEmojiStatusDocumentId(r1.emoji_status);
        boolean r02 = MessagesController.getInstance(r0).isPremiumUser(r1);
        int r4 = Theme.getColor(COLOR_KEY_STATUS);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r6 = null;
        if (r23 == 0) goto L22;
        this.premiumStatusDrawable.set(r23, true);
    L26:
        this.premiumStatusDrawable.setParticles(DialogObject.isEmojiStatusCollectible(r1.emoji_status), true);
        this.premiumStatusDrawable.setColor(Integer.valueOf(r4));
        if (r10 != null) goto L30;
        r10 = BadgesController.INSTANCE.getBadge(r1);
    L30:
        Drawable r102 = updateBadgeDrawable(r10, true, true, r4);
        if (r23 != 0) goto L33;
        if (r02 == true) goto L33;
    L34:
        applyNameDrawables(r6, r102);
        updateProxyStatus();
        return;
    L33:
        r6 = this.premiumStatusDrawable;
        goto L34
    L22:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r24 = this.premiumStatusDrawable;
        if (r02 == false) goto L25;
        r24.set(PremiumGradient.getInstance().premiumStarDrawableMini, true);
        goto L26
    L25:
        r24.set(null, true);
        goto L26
    L13:
        if (r22.isEmpty() == true) goto L18;
        boolean r25 = ExteraConfig.getHidePhoneNumber();
        SimpleTextView r3 = this.subtitleView;
        if (r25 == false) goto L17;
        r3.setText(LocaleController.getString(R.string.MobileHidden));
        goto L19
    L17:
        r3.setText(PhoneFormat.getInstance().format("+" + r1.phone));
        goto L19
    L8:
        if (r2.isEmpty() == true) goto L10;
        this.subtitleView.setText("@".concat(r2));
        goto L19
    }
}
