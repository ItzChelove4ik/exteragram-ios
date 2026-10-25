package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ClickableAnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class BusinessBotButton extends FrameLayout {
    private final AvatarDrawable avatarDrawable;
    private final BackupImageView avatarView;
    private long botId;
    private final int currentAccount;
    private long dialogId;
    private int flags;
    private float leftMargin;
    private String manageUrl;
    private final ImageView menuView;
    private final ClickableAnimatedTextView pauseButton;
    private boolean paused;
    private final AnimatedTextView subtitleView;
    private final LinearLayout textLayout;
    private final AnimatedTextView titleView;

    public static /* synthetic */ void $r8$lambda$AVn2eMyutRlIIF1VDegUWCReRzk(BusinessBotButton r0) {
        r0.updateTextRightPadding();
    }

    public static /* synthetic */ void $r8$lambda$Exu7B7IiwxNOELj1OcV7tAtJBBg(BusinessBotButton r0, ChatActivity r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$M86IVEUyEk9hO24eGSg4VpVefoA(BusinessBotButton r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$XffgbQkIyAfDSKUAoGKW3GD_Osc(BusinessBotButton r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$ukU6zZcXoPllW-QQrb2_9fUxPm0, reason: not valid java name */
    public static /* synthetic */ void m6313$r8$lambda$ukU6zZcXoPllWQQrb2_9fUxPm0(BusinessBotButton r0) {
        r0.lambda$new$2();
    }

    public BusinessBotButton(Context r20, ChatActivity r21, Theme.ResourcesProvider r22) {
        super(r20);
        this.currentAccount = r21.getCurrentAccount();
        this.paused = false;
        BackupImageView r4 = new BackupImageView(r20);
        this.avatarView = r4;
        TLRPC.User r5 = r21.getMessagesController().getUser(Long.valueOf(this.botId));
        AvatarDrawable r6 = new AvatarDrawable();
        this.avatarDrawable = r6;
        r6.setInfo(r5);
        r4.setRoundRadius(ExteraConfig.getAvatarCorners(32.0f));
        r4.setForUserOrChat(r5, r6);
        addView(r4, LayoutHelper.createFrame(32, 32.0f, 19, 10.0f, 0.0f, 10.0f, 0.0f));
        LinearLayout r42 = new LinearLayout(r20);
        this.textLayout = r42;
        r42.setOrientation(1);
        AnimatedTextView r7 = new AnimatedTextView(r20);
        this.titleView = r7;
        r7.adaptWidth = false;
        r7.getDrawable().setHacks(true, true, false);
        r7.setTypeface(AndroidUtilities.bold());
        r7.setTextSize(AndroidUtilities.dp(14.0f));
        r7.setText(UserObject.getUserName(r5));
        r7.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r22));
        r7.setEllipsizeByGradient(true);
        r42.addView(r7, LayoutHelper.createLinear(-1, 17, 0.0f, 0.0f, 0.0f, 1.0f));
        AnimatedTextView r52 = new AnimatedTextView(r20);
        this.subtitleView = r52;
        r52.adaptWidth = false;
        r52.getDrawable().setHacks(true, true, false);
        r52.setTextSize(AndroidUtilities.dp(13.0f));
        r52.setText(LocaleController.getString(R.string.BizBotStatusManages));
        r52.setTextColor(Theme.getColor(Theme.key_chat_topPanelMessage, r22));
        r52.setEllipsizeByGradient(true);
        r42.addView(r52, LayoutHelper.createLinear(-1, 17));
        addView(r42, LayoutHelper.createFrame(-2, -2.0f, 16, 52.0f, 0.0f, 49.0f, 0.0f));
        ClickableAnimatedTextView r11 = new ClickableAnimatedTextView(r20);
        this.pauseButton = r11;
        r11.getDrawable().setHacks(true, true, true);
        r11.setAnimationProperties(0.75f, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        r11.setScaleProperty(0.6f);
        r11.setTypeface(AndroidUtilities.bold());
        int r43 = AndroidUtilities.dp(14.0f);
        int r53 = Theme.key_featuredStickers_addButton;
        r11.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(r43, Theme.getColor(r53, r22), Theme.blendOver(Theme.getColor(r53, r22), Theme.multAlpha(-1, 0.12f))));
        r11.setTextSize(AndroidUtilities.dp(14.0f));
        r11.setGravity(5);
        r11.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r22));
        r11.setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), 0);
        r11.setOnClickListener(new BusinessBotButton$$ExternalSyntheticLambda0(this));
        r11.setOnWidthUpdatedListener(new BusinessBotButton$$ExternalSyntheticLambda1(this));
        if (this.paused == false) goto L5;
        int r44 = R.string.BizBotStart;
    L6:
        r11.setText(LocaleController.getString(r44));
        addView(r11, LayoutHelper.createFrame(64, 28.0f, 21, 0.0f, 0.0f, 46.0f, 0.0f));
        ImageView r45 = new ImageView(r20);
        this.menuView = r45;
        r45.setScaleType(ImageView.ScaleType.CENTER);
        r45.setImageResource(R.drawable.msg_mini_customize);
        r45.setBackground(Theme.createCircleSelectorDrawable(Theme.getColor(Theme.key_listSelector, r22), 0, 0));
        r45.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_topPanelClose, r22), PorterDuff.Mode.MULTIPLY));
        r45.setOnClickListener(new BusinessBotButton$$ExternalSyntheticLambda2(this, r21, r22));
        addView(r45, LayoutHelper.createFrame(32, 32.0f, 21, 8.0f, 0.0f, 6.0f, 0.0f));
        return;
    L5:
        r44 = R.string.BizBotStop;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r4) {
        boolean r42 = this.paused;
        this.paused = !r42;
        ClickableAnimatedTextView r0 = this.pauseButton;
        if (r42 == true) goto L5;
        int r43 = R.string.BizBotStart;
    L6:
        r0.setText(LocaleController.getString(r43), true);
        this.subtitleView.cancelAnimation();
        AnimatedTextView r44 = this.subtitleView;
        if (this.paused == false) goto L9;
        int r02 = R.string.BizBotStatusStopped;
    L10:
        r44.setText(LocaleController.getString(r02), true);
        boolean r45 = this.paused;
        int r03 = this.flags;
        if (r45 == false) goto L13;
        this.flags = r03 | 1;
    L14:
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putInt("dialog_botflags" + this.dialogId, this.flags).apply();
        TL_account.toggleConnectedBotPaused r46 = new TL_account.toggleConnectedBotPaused();
        r46.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r46.paused = this.paused;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r46, null);
        return;
    L13:
        this.flags = r03 & (-2);
        goto L14
    L9:
        r02 = R.string.BizBotStatusManages;
        goto L10
    L5:
        r43 = R.string.BizBotStop;
        goto L6
    }

    private /* synthetic */ void lambda$new$1() {
        TL_account.disablePeerConnectedBot r0 = new TL_account.disablePeerConnectedBot();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        MessagesController.getNotificationsSettings(this.currentAccount).edit().remove("dialog_botid" + this.dialogId).remove("dialog_boturl" + this.dialogId).remove("dialog_botflags" + this.dialogId).apply();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.peerSettingsDidLoad, new Object[]{Long.valueOf(this.dialogId)});
        BusinessChatbotController.getInstance(this.currentAccount).invalidate(false);
    }

    private /* synthetic */ void lambda$new$2() {
        Browser.openUrl(getContext(), this.manageUrl);
    }

    private /* synthetic */ void lambda$new$3(ChatActivity r3, Theme.ResourcesProvider r4, View r5) {
        ItemOptions r32 = ItemOptions.makeOptions(r3.getLayoutContainer(), r4, this.menuView);
        r32.add(R.drawable.msg_cancel, LocaleController.getString(R.string.BizBotRemove), true, new BusinessBotButton$$ExternalSyntheticLambda3(this)).makeMultiline(false);
        if (this.manageUrl == null) goto L5;
        r32.add(R.drawable.msg_settings, LocaleController.getString(R.string.BizBotManage), new BusinessBotButton$$ExternalSyntheticLambda4(this));
    L5:
        r32.translate(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(7.0f));
        r32.setDimAlpha(0);
        r32.show();
    }

    private void updateTextRightPadding() {
        float r0 = (((this.leftMargin + this.pauseButton.getPaddingLeft()) + this.pauseButton.getDrawable().getCurrentWidth()) + this.pauseButton.getPaddingRight()) + AndroidUtilities.dp(12.0f);
        this.titleView.setRightPadding(r0);
        this.subtitleView.setRightPadding(r0);
    }

    public void set(long r1, long r3, String r5, int r6) {
        this.dialogId = r1;
        this.botId = r3;
        this.manageUrl = r5;
        this.flags = r6;
        boolean r12 = true;
        if ((r6 & 1) != 0) goto L6;
        r12 = false;
    L6:
        this.paused = r12;
        TLRPC.User r13 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r3));
        this.avatarDrawable.setInfo(r13);
        this.avatarView.setForUserOrChat(r13, this.avatarDrawable);
        this.titleView.setText(UserObject.getUserName(r13));
        AnimatedTextView r14 = this.subtitleView;
        if (this.paused == false) goto L9;
        int r2 = R.string.BizBotStatusStopped;
    L10:
        r14.setText(LocaleController.getString(r2));
        ClickableAnimatedTextView r15 = this.pauseButton;
        if (this.paused == false) goto L13;
        int r0 = R.string.BizBotStart;
    L14:
        r15.setText(LocaleController.getString(r0));
        return;
    L13:
        r0 = R.string.BizBotStop;
        goto L14
    L9:
        r2 = R.string.BizBotStatusManages;
        goto L10
    }

    public void setLeftMargin(float r2) {
        this.leftMargin = r2;
        this.avatarView.setTranslationX(r2);
        this.textLayout.setTranslationX(r2);
        updateTextRightPadding();
    }
}
