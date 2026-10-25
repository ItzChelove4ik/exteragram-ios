package org.telegram.ui.Components.Premium.boosts.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Date;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Premium.boosts.BoostDialogs;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.Premium.boosts.cells.ActionBtnCell;
import org.telegram.ui.Components.Premium.boosts.cells.HeaderCell;
import org.telegram.ui.Components.Premium.boosts.cells.LinkCell;
import org.telegram.ui.Components.Premium.boosts.cells.TableCell;
import org.telegram.ui.Components.Premium.boosts.cells.TextInfoCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public abstract class GiftInfoAdapter extends RecyclerListView.SelectionAdapter {
    private BaseFragment baseFragment;
    private FrameLayout container;
    private TLRPC.TL_payments_checkedGiftCode giftCode;
    private boolean isUnused;
    private final Theme.ResourcesProvider resourcesProvider;
    private String slug;

    /* renamed from: $r8$lambda$4-Nbaj00BVv3olzbKpE92TUw1ZY, reason: not valid java name */
    public static /* synthetic */ void m12155$r8$lambda$4Nbaj00BVv3olzbKpE92TUw1ZY(GiftInfoAdapter r0, ActionBtnCell r1, TLRPC.TL_error r2) {
        r0.lambda$onBindViewHolder$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NYtuUwyFjszlkyC6y5yLSTrIUkU(GiftInfoAdapter r0, View r1) {
        r0.lambda$onBindViewHolder$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$bja9AWN_bmqsrolOfqAGUYfKY_M(GiftInfoAdapter r0, ActionBtnCell r1, Void r2) {
        r0.lambda$onBindViewHolder$0(r1, r2);
    }

    /* renamed from: $r8$lambda$dqGoxNeljcEE5inb5G4LWvF-MT0, reason: not valid java name */
    public static /* synthetic */ void m12156$r8$lambda$dqGoxNeljcEE5inb5G4LWvFMT0(GiftInfoAdapter r0) {
        r0.share();
    }

    public static /* synthetic */ void $r8$lambda$hi2mW33UjIIJhnQGIAI__Ibv6_c(GiftInfoAdapter r0, ActionBtnCell r1, View r2) {
        r0.lambda$onBindViewHolder$2(r1, r2);
    }

    /* renamed from: $r8$lambda$qtb-kXXwP1PDahbvBzbWUfBILN4, reason: not valid java name */
    public static /* synthetic */ boolean m12157$r8$lambda$qtbkXXwP1PDahbvBzbWUfBILN4(GiftInfoAdapter r0, String r1, DialogsActivity r2, ArrayList r3, CharSequence r4, boolean r5, boolean r6, int r7, int r8, TopicsFragment r9) {
        return r0.lambda$share$4(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public GiftInfoAdapter(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    private /* synthetic */ void lambda$onBindViewHolder$0(ActionBtnCell r1, Void r2) {
        r1.updateLoading(false);
        afterCodeApplied();
        dismiss();
    }

    private /* synthetic */ void lambda$onBindViewHolder$1(ActionBtnCell r3, TLRPC.TL_error r4) {
        r3.updateLoading(false);
        BoostDialogs.processApplyGiftCodeError(r4, this.container, this.resourcesProvider, new GiftInfoAdapter$$ExternalSyntheticLambda2(this));
    }

    private /* synthetic */ void lambda$onBindViewHolder$2(ActionBtnCell r3, View r4) {
        if (this.isUnused == true) goto L5;
        dismiss();
        return;
    L5:
        if (r3.isLoading() == false) goto L7;
        return;
    L7:
        r3.updateLoading(true);
        BoostRepository.applyGiftCode(this.slug, new GiftInfoAdapter$$ExternalSyntheticLambda6(this, r3), new GiftInfoAdapter$$ExternalSyntheticLambda7(this, r3));
    }

    private /* synthetic */ void lambda$onBindViewHolder$3(View r1) {
        dismiss();
    }

    private /* synthetic */ boolean lambda$share$4(String r20, DialogsActivity r21, ArrayList r22, CharSequence r23, boolean r24, boolean r25, int r26, int r27, TopicsFragment r28) {
        long r0 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r22.size()) goto L6;
        long r5 = ((MessagesStorage.TopicKey) r22.get(r2)).dialogId;
        this.baseFragment.getSendMessagesHelper().sendMessage(SendMessagesHelper.SendMessageParams.of(r20, r5, null, null, null, true, null, null, null, true, 0, 0, null, false));
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
        r2.setDelegate(new GiftInfoAdapter$$ExternalSyntheticLambda5(this, r0));
        this.baseFragment.presentFragment(r2);
        dismiss();
    }

    public abstract void afterCodeApplied();

    public abstract void dismiss();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 5;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r1) {
        if (r1 != 0) goto L4;
        return 0;
    L4:
        if (r1 != 1) goto L6;
        return 1;
    L6:
        if (r1 != 2) goto L8;
        return 2;
    L8:
        if (r1 != 3) goto L10;
        return 3;
    L10:
        if (r1 == 4) goto L18;
        return 5;
    L18:
        return 4;
    }

    public void init(BaseFragment r2, TLRPC.TL_payments_checkedGiftCode r3, String r4, FrameLayout r5) {
        if (r3.used_date != 0) goto L5;
        boolean r0 = true;
    L6:
        this.isUnused = r0;
        this.baseFragment = r2;
        this.giftCode = r3;
        this.slug = r4;
        this.container = r5;
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
        int r62 = r5.getItemViewType();
        if (r62 != 0) goto L5;
        HeaderCell r52 = (HeaderCell) r5.itemView;
        if (this.isUnused == false) goto L58;
        r52.setGiftLinkText();
    L59:
        TLRPC.TL_payments_checkedGiftCode r63 = this.giftCode;
        if (r63.boost == null) goto L63;
        r52.setGiftLinkToUserText(r63.to_id, new GiftInfoAdapter$$ExternalSyntheticLambda0(this));
    L63:
        if (this.giftCode.to_id != (-1)) goto L69;
        r52.setUnclaimedText();
        return;
    L69:
        return;
    L58:
        r52.setUsedGiftLinkText();
        goto L59
    L5:
        if (r62 != 1) goto L7;
        LinkCell r53 = (LinkCell) r5.itemView;
        r53.setSlug(this.slug);
        if (this.giftCode.boost != null) goto L45;
    L47:
        String r64 = this.slug;
        if (r64 == null) goto L52;
        if (r64.isEmpty() == true) goto L52;
        return;
    L52:
        if (this.giftCode.to_id != (-1)) goto L68;
        r53.hideSlug(new GiftInfoAdapter$$ExternalSyntheticLambda1(this));
        return;
    L68:
        return;
    L45:
        if (this.slug != null) goto L47;
        r53.hideSlug(new GiftInfoAdapter$$ExternalSyntheticLambda1(this));
        goto L47
    L7:
        if (r62 != 2) goto L9;
        ((TableCell) r5.itemView).setData(this.giftCode, new GiftInfoAdapter$$ExternalSyntheticLambda0(this));
        return;
    L9:
        if (r62 != 3) goto L11;
        TextInfoCell r54 = (TextInfoCell) r5.itemView;
        r54.setTextGravity(17);
        r54.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r54.setTopPadding(14);
        r54.setBottomPadding(15);
        TLRPC.TL_payments_checkedGiftCode r2 = this.giftCode;
        if (r2.boost == null) goto L31;
        String r4 = this.slug;
        if (r4 != null) goto L24;
    L28:
        r54.setText(LocaleController.getString(R.string.BoostingLinkNotActivated));
        return;
    L24:
        if (r4.isEmpty() == true) goto L28;
        r54.setFixedSize(14);
        r54.setText(null);
        return;
    L31:
        if (this.isUnused == true) goto L33;
        Date r65 = new Date(this.giftCode.used_date * 1000);
        r54.setText(LocaleController.formatString("BoostingUsedLinkDate", R.string.BoostingUsedLinkDate, new Object[]{LocaleController.formatString("formatDateAtTime", R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(r65), LocaleController.getInstance().getFormatterDay().format(r65)})}));
        return;
    L33:
        if (r2.to_id != (-1)) goto L35;
        String r66 = LocaleController.getString(R.string.BoostingSendLinkToAnyone);
    L36:
        r54.setText(AndroidUtilities.replaceSingleTag(r66, Theme.key_chat_messageLinkIn, 0, new GiftInfoAdapter$$ExternalSyntheticLambda2(this), this.resourcesProvider));
        return;
    L35:
        r66 = LocaleController.getString(R.string.BoostingSendLinkToFriends);
        goto L36
    L11:
        if (r62 != 4) goto L70;
        ActionBtnCell r55 = (ActionBtnCell) r5.itemView;
        r55.setOkStyle(this.isUnused);
        r55.setOnClickListener(new GiftInfoAdapter$$ExternalSyntheticLambda3(this, r55));
        TLRPC.TL_payments_checkedGiftCode r67 = this.giftCode;
        if (r67.boost == null) goto L16;
    L17:
        r55.setCloseStyle();
        r55.setOnClickListener(new GiftInfoAdapter$$ExternalSyntheticLambda4(this));
        return;
    L16:
        if (r67.flags == (-1)) goto L17;
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
        Context r22 = r2.getContext();
        if (r3 != 1) goto L5;
        View r32 = new LinkCell(r22, this.baseFragment, this.resourcesProvider);
    L18:
        r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        return new RecyclerListView.Holder(r32);
    L5:
        if (r3 != 2) goto L7;
        r32 = new TableCell(r22, this.resourcesProvider);
        goto L18
    L7:
        if (r3 != 3) goto L9;
        r32 = new TextInfoCell(r22, this.resourcesProvider);
        goto L18
    L9:
        if (r3 != 4) goto L11;
        r32 = new ActionBtnCell(r22, this.resourcesProvider);
        r32.setPadding(0, 0, 0, AndroidUtilities.dp(14.0f));
        goto L18
    L11:
        if (r3 == 5) goto L13;
        r32 = new HeaderCell(r22, this.resourcesProvider);
        goto L18
    L13:
        r32 = new View(r22);
        goto L18
    }

    public abstract void onHiddenLinkClicked();

    public abstract void onObjectClicked(TLObject r1);
}
