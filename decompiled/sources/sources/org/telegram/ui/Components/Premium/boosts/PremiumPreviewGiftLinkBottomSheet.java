package org.telegram.ui.Components.Premium.boosts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.GiftPremiumBottomSheet$GiftTier;
import org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet;
import org.telegram.ui.Components.Premium.boosts.cells.ActionBtnCell;
import org.telegram.ui.Components.Premium.boosts.cells.LinkCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public class PremiumPreviewGiftLinkBottomSheet extends PremiumPreviewBottomSheet {
    private static PremiumPreviewGiftLinkBottomSheet instance;
    private ActionBtnCell actionBtn;
    private final boolean isUsed;
    private final String slug;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.PremiumPreviewGiftLinkBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements Bulletin.Delegate {
        final /* synthetic */ PremiumPreviewGiftLinkBottomSheet this$0;

        public AnonymousClass1(PremiumPreviewGiftLinkBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.dp(68.0f);
        }
    }

    public static /* synthetic */ void $r8$lambda$4nZFqwFsbyQLXTMh6CELOOcCdKA(PremiumPreviewGiftLinkBottomSheet r0, Void r1) {
        r0.lambda$init$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$5IfRqIlE064qTo_4DDaHCofOx2s(PremiumPreviewGiftLinkBottomSheet r0, TLRPC.TL_error r1) {
        r0.lambda$init$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$9Tx9sfxWIWzsCClRM9sxf28E0kY(PremiumPreviewGiftLinkBottomSheet r0) {
        r0.lambda$init$1();
    }

    /* renamed from: $r8$lambda$BegJXpDgeaiue66vg7wr-PRe9q8, reason: not valid java name */
    public static /* synthetic */ void m12111$r8$lambda$BegJXpDgeaiue66vg7wrPRe9q8(PremiumPreviewGiftLinkBottomSheet r0, View r1) {
        r0.lambda$init$4(r1);
    }

    /* renamed from: $r8$lambda$hvrJ8vsi-xKBZKsWQv_0JXxp9_E, reason: not valid java name */
    public static /* synthetic */ boolean m12112$r8$lambda$hvrJ8vsixKBZKsWQv_0JXxp9_E(PremiumPreviewGiftLinkBottomSheet r0, String r1, DialogsActivity r2, ArrayList r3, CharSequence r4, boolean r5, boolean r6, int r7, int r8, TopicsFragment r9) {
        return r0.lambda$share$0(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$kwfpVyReJNf0ldBXp5CCVUM0_8k(PremiumPreviewGiftLinkBottomSheet r0) {
        r0.share();
    }

    public PremiumPreviewGiftLinkBottomSheet(BaseFragment r8, int r9, TLRPC.User r10, GiftPremiumBottomSheet$GiftTier r11, String r12, boolean r13, Theme.ResourcesProvider r14) {
        super(r8, r9, r10, r11, null, r14);
        this.slug = r12;
        this.isUsed = r13;
        init();
    }

    private void init() {
        Bulletin.addDelegate((FrameLayout) this.containerView, new AnonymousClass1(this));
        if (this.isUsed == true) goto L5;
        RecyclerListView r0 = this.recyclerListView;
        int r1 = this.backgroundPaddingLeft;
        r0.setPadding(r1, 0, r1, AndroidUtilities.dp(68.0f));
        ActionBtnCell r02 = new ActionBtnCell(getContext(), this.resourcesProvider);
        this.actionBtn = r02;
        r02.setOnClickListener(new PremiumPreviewGiftLinkBottomSheet$$ExternalSyntheticLambda0(this));
        this.actionBtn.setActivateForFreeStyle();
        this.containerView.addView(this.actionBtn, LayoutHelper.createFrame(-1, 68.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
    L5:
        fixNavigationBar();
    }

    private /* synthetic */ void lambda$init$1() {
        PremiumPreviewBottomSheet r0 = new PremiumPreviewBottomSheet(getBaseFragment(), UserConfig.selectedAccount, null, null, null, this.resourcesProvider).setAnimateConfetti(true).setAnimateConfettiWithStars(true).setOutboundGift(true);
        getBaseFragment().showDialog(r0);
    }

    private /* synthetic */ void lambda$init$2(Void r3) {
        this.actionBtn.updateLoading(false);
        dismiss();
        AndroidUtilities.runOnUIThread(new PremiumPreviewGiftLinkBottomSheet$$ExternalSyntheticLambda5(this), 200);
    }

    private /* synthetic */ void lambda$init$3(TLRPC.TL_error r4) {
        this.actionBtn.updateLoading(false);
        BoostDialogs.processApplyGiftCodeError(r4, (FrameLayout) this.containerView, this.resourcesProvider, new PremiumPreviewGiftLinkBottomSheet$$ExternalSyntheticLambda1(this));
    }

    private /* synthetic */ void lambda$init$4(View r3) {
        if (this.actionBtn.isLoading() == false) goto L5;
        return;
    L5:
        this.actionBtn.updateLoading(true);
        BoostRepository.applyGiftCode(this.slug, new PremiumPreviewGiftLinkBottomSheet$$ExternalSyntheticLambda3(this), new PremiumPreviewGiftLinkBottomSheet$$ExternalSyntheticLambda4(this));
    }

    private /* synthetic */ boolean lambda$share$0(String r20, DialogsActivity r21, ArrayList r22, CharSequence r23, boolean r24, boolean r25, int r26, int r27, TopicsFragment r28) {
        long r0 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r22.size()) goto L6;
        long r5 = ((MessagesStorage.TopicKey) r22.get(r2)).dialogId;
        getBaseFragment().getSendMessagesHelper().sendMessage(SendMessagesHelper.SendMessageParams.of(r20, r5, null, null, null, true, null, null, null, true, 0, 0, null, false));
        r2 = r2 + 1;
        r0 = r5;
        goto L4
    L6:
        r21.finishFragment();
        BoostDialogs.showGiftLinkForwardedBulletin(r0);
        return true;
    }

    private void share() {
        String r0 = "https://t.me/giftcode/" + this.slug;
        Bundle r1 = new Bundle();
        r1.putBoolean("onlySelect", true);
        r1.putInt("dialogsType", 3);
        DialogsActivity r2 = new DialogsActivity(r1);
        r2.setDelegate(new PremiumPreviewGiftLinkBottomSheet$$ExternalSyntheticLambda2(this, r0));
        getBaseFragment().presentFragment(r2);
        dismiss();
    }

    public static void show(String r1, Browser.Progress r2) {
        GiftInfoBottomSheet.show(LaunchActivity.getLastFragment(), r1, r2);
    }

    public static void show(String r8, TLRPC.TL_premiumGiftOption r9, TLRPC.User r10, boolean r11) {
        BaseFragment r1 = LaunchActivity.getLastFragment();
        if (r1 != null) goto L5;
        return;
    L5:
        if (instance != null) goto L10;
        GiftPremiumBottomSheet$GiftTier r4 = new GiftPremiumBottomSheet$GiftTier(r9, null);
        PremiumPreviewGiftLinkBottomSheet r0 = new PremiumPreviewGiftLinkBottomSheet(r1, UserConfig.selectedAccount, r10, r4, r8, r11, r1.getResourceProvider());
        r0.show();
        instance = r0;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        Bulletin.removeDelegate(this.container);
        instance = null;
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public int getAdditionItemViewType(int r1) {
        return 6;
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public void onBindAdditionCell(View r1, int r2) {
        ((LinkCell) r1).setSlug(this.slug);
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public View onCreateAdditionCell(int r2, Context r3) {
        if (r2 != 6) goto L6;
        LinkCell r22 = new LinkCell(r3, getBaseFragment(), this.resourcesProvider);
        r22.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
        return r22;
    L6:
        return null;
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public void setTitle(boolean r4) {
        super.setTitle(r4);
        this.subtitleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        ((ViewGroup.MarginLayoutParams) this.subtitleView.getLayoutParams()).bottomMargin = AndroidUtilities.dp(14.0f);
        ((ViewGroup.MarginLayoutParams) this.subtitleView.getLayoutParams()).topMargin = AndroidUtilities.dp(12.0f);
        this.subtitleView.setText(AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceSingleTag(LocaleController.getString("GiftPremiumAboutThisLink", R.string.GiftPremiumAboutThisLink), Theme.key_chat_messageLinkIn, 0, new PremiumPreviewGiftLinkBottomSheet$$ExternalSyntheticLambda1(this)), AndroidUtilities.replaceTags(LocaleController.getString("GiftPremiumAboutThisLinkEnd", R.string.GiftPremiumAboutThisLinkEnd))));
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public void updateRows() {
        int r0 = this.rowCount;
        this.paddingRow = r0;
        this.additionStartRow = r0 + 1;
        int r02 = r0 + 2;
        this.rowCount = r02;
        this.additionEndRow = r02;
        this.featuresStartRow = r02;
        int r03 = r02 + this.premiumFeatures.size();
        this.featuresEndRow = r03;
        this.rowCount = r03 + 1;
        this.sectionRow = r03;
    }
}
