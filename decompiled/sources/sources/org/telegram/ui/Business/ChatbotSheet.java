package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class ChatbotSheet extends BottomSheetWithRecyclerListView {
    private static final int RADIO_EXCLUDE = 0;
    private static final int RADIO_INCLUDE = 0;
    private static int ids;
    private UniversalAdapter adapter;
    private final AvatarDrawable avatarDrawable;
    private final TL_account.TL_connectedBot bot;
    private final FrameLayout buttonContainer;
    public boolean exclude;
    private Boolean hadChanges;
    private final BackupImageView imageView;
    private final TextView linkView;
    private final BusinessRecipientsHelper recipientsHelper;
    private final TextView subtitleView;
    private final ButtonWithCounterView terminateButton;
    private final TextView titleView;
    private final LinearLayout topView;
    private final ButtonWithCounterView updateButton;
    private final TLRPC.User user;

    /* renamed from: $r8$lambda$-KcuTuxwdRtDs3fPIg9RCzChsT0, reason: not valid java name */
    public static /* synthetic */ void m6346$r8$lambda$KcuTuxwdRtDs3fPIg9RCzChsT0(ChatbotSheet r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$BseAZtmhYuE8ujaKUwDyBXIcF_c(ChatbotSheet r0, boolean r1) {
        r0.lambda$checkDone$9(r1);
    }

    /* renamed from: $r8$lambda$JzmSwRvftyhEEpj7dkX0PfoQ6-4, reason: not valid java name */
    public static /* synthetic */ void m6347$r8$lambda$JzmSwRvftyhEEpj7dkX0PfoQ64(ChatbotSheet r0, boolean r1) {
        r0.lambda$checkDone$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$PRaicjy7nsm56ZHaP7kpAjSSnig(ChatbotSheet r0, TL_account.TL_connectedBot r1, TL_account.TL_businessBotRecipients r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_9qPwMjD9_DyqBjCpGa61RnCV2k(ChatbotSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$jkO0Y_W-iSZx2nVKVw3GAjkfkwY, reason: not valid java name */
    public static /* synthetic */ void m6348$r8$lambda$jkO0Y_WiSZx2nVKVw3GAjkfkwY(ChatbotSheet r0, Runnable r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$m6Kc_JtvtDAQhB1zJo8WSgVbcQ0(ChatbotSheet r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nxXdDk_EWKDt9GKnCZKNRusAi8I(ChatbotSheet r0, TL_account.TL_connectedBot r1, View r2) {
        r0.lambda$new$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sxCmLRDBRaET3eM0LmXT_JTpYWw(ChatbotSheet r0, TL_account.TL_connectedBot r1, TL_account.TL_businessBotRecipients r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$new$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$vcrEFAMH5ujPbXvjKwkehW988eE(ChatbotSheet r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zL1arWNN31eLzuKTbtqwCxhCErE(ChatbotSheet r0, TL_account.TL_connectedBot r1, Runnable r2, View r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    static {
        int r1 = 0 - 1;
        RADIO_EXCLUDE = r1;
        int r12 = r1 - 1;
        ids = r12;
        RADIO_INCLUDE = r12;
    }

    public ChatbotSheet(Context r19, TL_account.TL_connectedBot r20, Runnable r21, Theme.ResourcesProvider r22) {
        super(r19, null, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r22);
        this.bot = r20;
        TLRPC.User r2 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r20.bot_id));
        this.user = r2;
        this.headerMoveTop = AndroidUtilities.dp(36.0f);
        this.topPadding = 0.15f;
        BusinessRecipientsHelper r3 = new BusinessRecipientsHelper(r19, this.currentAccount, new ChatbotSheet$$ExternalSyntheticLambda0(this), r22);
        this.recipientsHelper = r3;
        TL_account.TL_businessBotRecipients r4 = r20.recipients;
        this.exclude = r4.exclude_selected;
        r3.setValue(r4);
        LinearLayout r32 = new LinearLayout(r19);
        this.topView = r32;
        r32.setOrientation(1);
        AvatarDrawable r5 = new AvatarDrawable();
        this.avatarDrawable = r5;
        BackupImageView r6 = new BackupImageView(r19);
        this.imageView = r6;
        r6.setRoundRadius(AndroidUtilities.dp(40.0f));
        r5.setInfo(r2);
        r6.setForUserOrChat(r2, r5);
        r32.addView(r6, LayoutHelper.createLinear(80, 80, 1, 0, 0, 0, 0));
        TextView r52 = new TextView(r19);
        this.titleView = r52;
        r52.setTextSize(1, 20.0f);
        r52.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r52.setGravity(17);
        r52.setTypeface(AndroidUtilities.bold());
        r52.setText(UserObject.getUserName(r2));
        r32.addView(r52, LayoutHelper.createLinear(-1, -2, 1, 32.0f, 15.66f, 32.0f, 3.66f));
        this.actionBar.setTitle(UserObject.getUserName(r2));
        TextView r53 = new TextView(r19);
        this.subtitleView = r53;
        r53.setTextSize(1, 14.0f);
        r53.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        r53.setGravity(17);
        r53.setText(LocaleController.getString(R.string.SessionBot));
        r32.addView(r53, LayoutHelper.createLinear(-1, -2, 1, 32.0f, 0.0f, 32.0f, 3.66f));
        String r23 = UserObject.getPublicUsername(r2);
        if (TextUtils.isEmpty(r23) == true) goto L5;
        TextView r54 = new TextView(r19);
        this.linkView = r54;
        r54.setTextSize(1, 14.0f);
        r54.setTextColor(getThemedColor(Theme.key_chat_messageLinkIn));
        r54.setText("@" + r23);
        r54.setGravity(17);
        r32.addView(r54, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 18));
    L6:
        int r24 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r24));
        fixNavigationBar(getThemedColor(r24));
        RecyclerListView r33 = this.recyclerListView;
        int r42 = this.backgroundPaddingLeft;
        r33.setPadding(r42, 0, r42, AndroidUtilities.dp(72.0f));
        this.recyclerListView.setSections();
        this.recyclerListView.setOnItemClickListener(new ChatbotSheet$$ExternalSyntheticLambda1(this));
        FrameLayout r34 = new FrameLayout(r19);
        this.buttonContainer = r34;
        r34.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        r34.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Theme.multAlpha(getThemedColor(r24), 0.0f), getThemedColor(r24), getThemedColor(r24)}));
        ButtonWithCounterView r25 = new ButtonWithCounterView(r19, r22).setRound();
        this.terminateButton = r25;
        r25.setColor(getThemedColor(Theme.key_color_red));
        r25.setText(LocaleController.getString(R.string.TerminateSession));
        r25.setOnClickListener(new ChatbotSheet$$ExternalSyntheticLambda2(this, r20, r21));
        r34.addView(r25, LayoutHelper.createFrame(-1, 48.0f, 87, 0.0f, 0.0f, 0.0f, 0.0f));
        ButtonWithCounterView r1 = new ButtonWithCounterView(r19, r22).setRound();
        this.updateButton = r1;
        r1.setText(LocaleController.getString(R.string.BusinessBotUpdate));
        r1.setOnClickListener(new ChatbotSheet$$ExternalSyntheticLambda3(this, r20));
        r34.addView(r1, LayoutHelper.createFrame(-1, 48.0f, 87, 0.0f, 0.0f, 0.0f, 0.0f));
        checkDone(false);
        FrameLayout.LayoutParams r12 = LayoutHelper.createFrame(-1, -2, 80);
        int r26 = r12.leftMargin;
        int r43 = this.backgroundPaddingLeft;
        r12.leftMargin = r26 + r43;
        r12.rightMargin += r43;
        this.containerView.addView(r34, r12);
        DefaultItemAnimator r13 = new DefaultItemAnimator();
        r13.setSupportsChangeAnimations(false);
        r13.setDelayAnimations(false);
        r13.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r13.setDurations(350);
        this.recyclerListView.setItemAnimator(r13);
        UniversalAdapter r0 = this.adapter;
        if (r0 == null) goto L10;
        r0.update(false);
        return;
    L10:
        return;
    L5:
        this.linkView = null;
        goto L6
    }

    private void checkDone(boolean r10) {
        BusinessRecipientsHelper r0 = this.recipientsHelper;
        int r1 = 0;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        Boolean r2 = this.hadChanges;
        if (r2 != null) goto L11;
    L13:
        this.hadChanges = Boolean.valueOf(r02);
        ButtonWithCounterView r22 = this.updateButton;
        float r3 = 0.0f;
        float r4 = 0.8f;
        if (r10 == false) goto L16;
        r22.setVisibility(0);
        ViewPropertyAnimator r102 = this.updateButton.animate();
        if (r02 == false) goto L50;
        float r23 = 1.0f;
    L51:
        ViewPropertyAnimator r103 = r102.alpha(r23);
        if (r02 == false) goto L54;
        float r24 = 1.0f;
    L55:
        ViewPropertyAnimator r104 = r103.scaleX(r24);
        if (r02 == false) goto L58;
        float r25 = 1.0f;
    L59:
        ViewPropertyAnimator r105 = r104.scaleY(r25).setDuration(320);
        CubicBezierInterpolator r26 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r105.setInterpolator(r26).withEndAction(new ChatbotSheet$$ExternalSyntheticLambda5(this, r02)).start();
        this.terminateButton.setVisibility(0);
        ViewPropertyAnimator r106 = this.terminateButton.animate();
        if (r02 == true) goto L62;
        r3 = 1.0f;
    L62:
        ViewPropertyAnimator r107 = r106.alpha(r3);
        if (r02 == true) goto L65;
        float r12 = 1.0f;
    L66:
        ViewPropertyAnimator r108 = r107.scaleX(r12);
        if (r02 == true) goto L69;
        r4 = 1.0f;
    L69:
        r108.scaleY(r4).setDuration(320).setInterpolator(r26).withEndAction(new ChatbotSheet$$ExternalSyntheticLambda6(this, r02)).start();
        return;
    L65:
        r12 = 0.8f;
        goto L66
    L58:
        r25 = 0.8f;
        goto L59
    L54:
        r24 = 0.8f;
        goto L55
    L50:
        r23 = 0.0f;
        goto L51
    L16:
        if (r02 == false) goto L18;
        int r6 = 0;
    L19:
        r22.setVisibility(r6);
        this.updateButton.animate().cancel();
        ButtonWithCounterView r27 = this.updateButton;
        if (r02 == false) goto L22;
        float r62 = 1.0f;
    L23:
        r27.setAlpha(r62);
        ButtonWithCounterView r28 = this.updateButton;
        if (r02 == false) goto L26;
        float r63 = 1.0f;
    L27:
        r28.setScaleX(r63);
        ButtonWithCounterView r29 = this.updateButton;
        if (r02 == false) goto L30;
        float r64 = 1.0f;
    L31:
        r29.setScaleY(r64);
        ButtonWithCounterView r210 = this.terminateButton;
        if (r02 == false) goto L35;
        r1 = 8;
    L35:
        r210.setVisibility(r1);
        this.terminateButton.animate().cancel();
        ButtonWithCounterView r109 = this.terminateButton;
        if (r02 == true) goto L38;
        r3 = 1.0f;
    L38:
        r109.setAlpha(r3);
        ButtonWithCounterView r1010 = this.terminateButton;
        if (r02 == true) goto L41;
        float r13 = 1.0f;
    L42:
        r1010.setScaleX(r13);
        ButtonWithCounterView r9 = this.terminateButton;
        if (r02 == true) goto L45;
        r4 = 1.0f;
    L45:
        r9.setScaleY(r4);
        return;
    L41:
        r13 = 0.8f;
        goto L42
    L30:
        r64 = 0.8f;
        goto L31
    L26:
        r63 = 0.8f;
        goto L27
    L22:
        r62 = 0.0f;
        goto L23
    L18:
        r6 = 8;
        goto L19
    L11:
        if (r2.booleanValue() != r02) goto L13;
        return;
    L5:
        if (r0.hasChanges() == false) goto L7;
        r02 = true;
        goto L8
    }

    private void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        r8.itemsOffset = 1;
        r7.add(UItem.asCustomShadow(-5, this.topView));
        TL_account.TL_connectedBot r1 = this.bot;
        if (r1 != null) goto L5;
        return;
    L5:
        if (TLObject.hasFlag(r1.flags, 1) == false) goto L7;
    L10:
        r7.add(UItem.asHeader(LocaleController.getString(R.string.SessionBotConnectedFrom)));
        if (TLObject.hasFlag(this.bot.flags, 1) == false) goto L14;
        r7.add(UItem.asButton(1, LocaleController.getString(R.string.SessionBotDevice), this.bot.device));
    L14:
        if (TLObject.hasFlag(this.bot.flags, 4) == false) goto L17;
        r7.add(UItem.asButton(2, LocaleController.getString(R.string.SessionBotLocation), this.bot.location));
    L17:
        if (TLObject.hasFlag(this.bot.flags, 2) == false) goto L19;
        r7.add(UItem.asButton(3, LocaleController.getString(R.string.SessionBotDate), LocaleController.formatDateTime(this.bot.date, false)));
    L19:
        r7.add(UItem.asShadow(null));
    L20:
        r8.whiteSectionStart();
        r7.add(UItem.asHeader(LocaleController.getString(R.string.BusinessBotChats2)));
        r7.add(UItem.asRadio(RADIO_EXCLUDE, LocaleController.getString(R.string.BusinessChatsAllPrivateExcept2)).setChecked(this.exclude));
        r7.add(UItem.asRadio(RADIO_INCLUDE, LocaleController.getString(R.string.BusinessChatsOnlySelected2)).setChecked(!this.exclude));
        r8.whiteSectionEnd();
        r7.add(UItem.asShadow(null));
        BusinessRecipientsHelper r6 = this.recipientsHelper;
        if (r6 == null) goto L23;
        r6.fillItems(r7, r8, true);
    L23:
        r7.add(UItem.asShadow(LocaleController.getString(R.string.BusinessBotChatsInfo2)));
        return;
    L7:
        if (TLObject.hasFlag(this.bot.flags, 2) == true) goto L10;
        if (TLObject.hasFlag(this.bot.flags, 4) == false) goto L20;
        goto L10
    }

    private /* synthetic */ void lambda$checkDone$8(boolean r1) {
        if (r1 == true) goto L5;
        this.updateButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$checkDone$9(boolean r1) {
        if (r1 == false) goto L5;
        this.terminateButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        UniversalAdapter r0 = this.adapter;
        if (r0 == null) goto L5;
        r0.update(true);
    L5:
        checkDone(true);
    }

    private /* synthetic */ void lambda$new$1(View r3, int r4) {
        UItem r0 = this.adapter.getItem(r4 - 1);
        if (r0 != null) goto L5;
        return;
    L5:
        onItemClick(r0, r3, r4);
    }

    private /* synthetic */ void lambda$new$2(Runnable r3) {
        BusinessChatbotController.getInstance(this.currentAccount).invalidate(true);
        if (r3 == null) goto L5;
        r3.run();
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$new$3(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChatbotSheet$$ExternalSyntheticLambda9(this, r1));
    }

    private /* synthetic */ void lambda$new$4(TL_account.TL_connectedBot r4, Runnable r5, View r6) {
        if (this.terminateButton.isLoading() == false) goto L5;
        return;
    L5:
        this.terminateButton.setLoading(true);
        TL_account.updateConnectedBot r62 = new TL_account.updateConnectedBot();
        r62.deleted = true;
        r62.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r4.bot_id);
        r62.recipients = new TL_account.TL_inputBusinessBotRecipients();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r62, new ChatbotSheet$$ExternalSyntheticLambda8(this, r5));
    }

    private /* synthetic */ void lambda$new$5(TL_account.TL_connectedBot r3, TL_account.TL_businessBotRecipients r4) {
        BusinessChatbotController.getInstance(this.currentAccount).invalidate(true);
        dismiss();
        r3.recipients = r4;
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 == null) goto L6;
        BulletinFactory.of(r32).createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.BusinessBotUpdated, new Object[]{UserObject.getUserName(this.user)})).show();
        return;
    }

    private /* synthetic */ void lambda$new$6(TL_account.TL_connectedBot r1, TL_account.TL_businessBotRecipients r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ChatbotSheet$$ExternalSyntheticLambda10(this, r1, r2));
    }

    private /* synthetic */ void lambda$new$7(TL_account.TL_connectedBot r4, View r5) {
        if (this.updateButton.isLoading() == false) goto L5;
        return;
    L5:
        this.updateButton.setLoading(true);
        TL_account.updateConnectedBot r52 = new TL_account.updateConnectedBot();
        r52.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r4.bot_id);
        r52.recipients = this.recipientsHelper.getBotInputValue();
        TL_account.TL_businessBotRecipients r0 = this.recipientsHelper.getBotValue();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new ChatbotSheet$$ExternalSyntheticLambda7(this, r4, r0));
    }

    private void onItemClick(UItem r1, View r2, int r3) {
        if (this.recipientsHelper.onClick(r1) == true) goto L14;
        int r12 = r1.id;
        if (r12 != RADIO_EXCLUDE) goto L10;
        BusinessRecipientsHelper r13 = this.recipientsHelper;
        this.exclude = true;
        r13.setExclude(true);
        this.adapter.update(true);
        checkDone(true);
        return;
    L10:
        if (r12 != RADIO_INCLUDE) goto L13;
        BusinessRecipientsHelper r14 = this.recipientsHelper;
        this.exclude = false;
        r14.setExclude(false);
        this.adapter.update(true);
        checkDone(true);
        return;
    L13:
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView, org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        BusinessRecipientsHelper r0 = this.recipientsHelper;
        if (r0 == null) goto L9;
        if (r0.hasChanges() == false) goto L9;
        return false;
    L9:
        return super.canDismissWithSwipe();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithTouchOutside() {
        BusinessRecipientsHelper r0 = this.recipientsHelper;
        if (r0 == null) goto L9;
        if (r0.hasChanges() == false) goto L9;
        return false;
    L9:
        return super.canDismissWithTouchOutside();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new ChatbotSheet$$ExternalSyntheticLambda4(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return null;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onActionBarAlpha(float r1) {
        SimpleTextView r0 = this.actionBar.getTitleTextView();
        if (r0 == null) goto L6;
        r0.setAlpha(r1);
        return;
    }
}
