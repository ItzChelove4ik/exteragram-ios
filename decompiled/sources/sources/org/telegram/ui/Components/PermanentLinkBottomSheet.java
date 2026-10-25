package org.telegram.ui.Components;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.NestedScrollView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ManageLinksActivity;

/* loaded from: classes3.dex */
public class PermanentLinkBottomSheet extends BottomSheet {
    private final long chatId;
    private BaseFragment fragment;
    private final RLottieImageView imageView;
    TLRPC.TL_chatInviteExported invite;
    private final LinkActionView linkActionView;
    boolean linkGenerating;
    private final RLottieDrawable linkIcon;
    private final TextView manage;
    private final TextView subtitle;
    private final TextView titleView;

    /* renamed from: $r8$lambda$-FEZMzH2qeHHGfq1zmvFzE2PkDM, reason: not valid java name */
    public static /* synthetic */ void m11661$r8$lambda$FEZMzH2qeHHGfq1zmvFzE2PkDM(PermanentLinkBottomSheet r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$67RdYrAV-8zEaK8fxTHPZ6Q_RBE, reason: not valid java name */
    public static /* synthetic */ void m11662$r8$lambda$67RdYrAV8zEaK8fxTHPZ6Q_RBE(PermanentLinkBottomSheet r0) {
        r0.updateColors();
    }

    public static /* synthetic */ void $r8$lambda$ElSxiKd4UpvuxSTYG9Qn_hRiirY(PermanentLinkBottomSheet r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
        r0.lambda$generateLink$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Pt6kMpyMHPSQZ01cV9IV5gNfOnc(PermanentLinkBottomSheet r0) {
        r0.lambda$show$5();
    }

    public static /* synthetic */ void $r8$lambda$RbhEBq8rK_UA6xXab2QN35lJ3pg(PermanentLinkBottomSheet r0, TLRPC.ChatFull r1, BaseFragment r2, View r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$S2WKEgjVTz4D0k2CtZ7Far0CgBY(PermanentLinkBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$eJOE5M5hTGEYwuPBIM4xBLLFUCs(PermanentLinkBottomSheet r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$generateLink$4(r1, r2, r3);
    }

    public PermanentLinkBottomSheet(Context r22, boolean r23, BaseFragment r24, TLRPC.ChatFull r25, long r26, boolean r28) {
        super(r22, r23);
        this.chatId = r26;
        setAllowNestedScroll(true);
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        LinearLayout r11 = new LinearLayout(r22);
        r11.setOrientation(1);
        FrameLayout r12 = new FrameLayout(r22);
        r12.addView(r11);
        ImageView r0 = new ImageView(r22);
        r0.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector)));
        r0.setColorFilter(getThemedColor(Theme.key_sheet_other));
        r0.setImageResource(R.drawable.ic_layer_close);
        r0.setOnClickListener(new PermanentLinkBottomSheet$$ExternalSyntheticLambda0(this));
        int r2 = AndroidUtilities.dp(8.0f);
        r0.setPadding(r2, r2, r2, r2);
        r12.addView(r0, LayoutHelper.createFrame(36, 36.0f, 8388661, 6.0f, 8.0f, 8.0f, 0.0f));
        LinkActionView r02 = new LinkActionView(r22, r24, this, r26, true, r28);
        this.linkActionView = r02;
        r02.setPermanent(true);
        RLottieImageView r27 = new RLottieImageView(r22);
        this.imageView = r27;
        RLottieDrawable r14 = new RLottieDrawable(R.raw.shared_link_enter, AndroidUtilities.dp(90.0f), AndroidUtilities.dp(90.0f), false, null);
        this.linkIcon = r14;
        r14.setCustomEndFrame(42);
        r27.setAnimation(r14);
        r02.setUsers(0, null);
        r02.hideRevokeOption(true);
        r02.setDelegate(new PermanentLinkBottomSheet$$ExternalSyntheticLambda1(this));
        TextView r4 = new TextView(r22);
        this.titleView = r4;
        r4.setText(LocaleController.getString(R.string.InviteLink));
        r4.setTypeface(AndroidUtilities.bold());
        r4.setTextSize(1, 20.0f);
        r4.setGravity(1);
        r4.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        TextView r5 = new TextView(r22);
        this.subtitle = r5;
        if (r28 == false) goto L6;
        int r6 = R.string.LinkInfoChannel;
    L7:
        r5.setText(LocaleController.getString(r6));
        r5.setTextSize(1, 14.0f);
        r5.setGravity(1);
        r5.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r5.setLineSpacing(r5.getLineSpacingExtra(), r5.getLineSpacingMultiplier() * 1.1f);
        TextView r7 = new TextView(r22);
        this.manage = r7;
        r7.setText(LocaleController.getString(R.string.ManageInviteLinks));
        r7.setGravity(17);
        r7.setEllipsize(TextUtils.TruncateAt.END);
        r7.setSingleLine(true);
        r7.setTypeface(AndroidUtilities.bold());
        r7.setTextSize(1, 14.0f);
        int r62 = Theme.key_featuredStickers_addButton;
        r7.setTextColor(Theme.getColor(r62));
        r7.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r62), 120)));
        r7.setLetterSpacing(0.025f);
        r7.setOnClickListener(new PermanentLinkBottomSheet$$ExternalSyntheticLambda2(this, r25, r24));
        r11.addView(r27, LayoutHelper.createLinear(90, 90, 1, 0, 33, 0, 0));
        r11.addView(r4, LayoutHelper.createLinear(-1, -2, 1, 60, 10, 60, 0));
        r11.addView(r5, LayoutHelper.createLinear(-1, -2, 1, 28, 7, 28, 2));
        r11.addView(r02, LayoutHelper.createLinear(-1, -2));
        r11.addView(r7, LayoutHelper.createLinear(-1, 48, 1, 14, -2, 14, 6));
        NestedScrollView r29 = new NestedScrollView(r22);
        r29.setVerticalScrollBarEnabled(false);
        r29.addView(r12);
        setCustomView(r29);
        TLRPC.Chat r1 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r26));
        if (r1 != null) goto L10;
    L12:
        if (r25 == null) goto L16;
        TLRPC.TL_chatInviteExported r13 = r25.exported_invite;
        if (r13 == null) goto L16;
        r02.setLink(r13.link);
    L17:
        updateColors();
        return;
    L16:
        generateLink(false);
        goto L17
    L10:
        if (ChatObject.isPublic(r1) == false) goto L12;
        r02.setLink("https://t.me/" + ChatObject.getPublicUsername(r1));
        r7.setVisibility(8);
        goto L17
    L6:
        r6 = R.string.LinkInfo;
        goto L7
    }

    private void generateLink(boolean r5) {
        if (this.linkGenerating == false) goto L5;
        return;
    L5:
        this.linkGenerating = true;
        TLRPC.TL_messages_exportChatInvite r1 = new TLRPC.TL_messages_exportChatInvite();
        r1.legacy_revoke_permanent = true;
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(-this.chatId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new PermanentLinkBottomSheet$$ExternalSyntheticLambda5(this, r5));
    }

    private /* synthetic */ void lambda$generateLink$3(TLRPC.TL_error r3, TLObject r4, boolean r5) {
        if (r3 != null) goto L11;
        this.invite = (TLRPC.TL_chatInviteExported) r4;
        TLRPC.ChatFull r32 = MessagesController.getInstance(this.currentAccount).getChatFull(this.chatId);
        if (r32 == null) goto L6;
        r32.exported_invite = this.invite;
    L6:
        this.linkActionView.setLink(this.invite.link);
        if (r5 == false) goto L11;
        if (this.fragment == null) goto L11;
        AlertDialog.Builder r33 = new AlertDialog.Builder(getContext());
        r33.setMessage(LocaleController.getString(R.string.RevokeAlertNewLink));
        r33.setTitle(LocaleController.getString(R.string.RevokeLink));
        r33.setNegativeButton(LocaleController.getString(R.string.OK), null);
        this.fragment.showDialog(r33.create());
    L11:
        this.linkGenerating = false;
    }

    private /* synthetic */ void lambda$generateLink$4(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PermanentLinkBottomSheet$$ExternalSyntheticLambda6(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1() {
        generateLink(true);
    }

    private /* synthetic */ void lambda$new$2(TLRPC.ChatFull r7, BaseFragment r8, View r9) {
        ManageLinksActivity r0 = new ManageLinksActivity(r7.id, 0, 0);
        r0.setInfo(r7, r7.exported_invite);
        r8.presentFragment(r0);
        dismiss();
    }

    private /* synthetic */ void lambda$show$5() {
        this.linkIcon.start();
    }

    private void updateColors() {
        RLottieImageView r0 = this.imageView;
        int r1 = AndroidUtilities.dp(90.0f);
        int r2 = Theme.key_featuredStickers_addButton;
        r0.setBackground(Theme.createCircleDrawable(r1, Theme.getColor(r2)));
        this.manage.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r2), 120)));
        int r02 = Theme.getColor(Theme.key_featuredStickers_buttonText);
        this.linkIcon.setLayerColor("Top", r02);
        this.linkIcon.setLayerColor("Bottom", r02);
        this.linkIcon.setLayerColor("Center", r02);
        this.linkActionView.updateColors();
        setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        PermanentLinkBottomSheet$$ExternalSyntheticLambda4 r8 = new PermanentLinkBottomSheet$$ExternalSyntheticLambda4(this);
        r1.add(new ThemeDescription(this.titleView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.subtitle, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_dialogTextBlack));
        TextView r12 = this.manage;
        int r13 = ThemeDescription.FLAG_TEXTCOLOR;
        int r18 = Theme.key_featuredStickers_addButton;
        r1.add(new ThemeDescription(r12, r13, null, null, null, null, r18));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, r18));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_featuredStickers_buttonText));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        AndroidUtilities.runOnUIThread(new PermanentLinkBottomSheet$$ExternalSyntheticLambda3(this), 50);
    }
}
