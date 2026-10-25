package org.telegram.ui.Gifts;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class AcquiredGiftsSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final GiftAuctionController.Auction auction;
    private final List<TL_stars.TL_StarGiftAuctionAcquiredGift> gifts;

    public static class AcquiredGiftsCell extends FrameLayout {
        private final int currentAccount;
        private final Theme.ResourcesProvider resourcesProvider;

        public static class Factory extends UItem.UItemFactory<AcquiredGiftsCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            private Factory() {
            }

            public static UItem as(TL_stars.TL_StarGiftAuctionAcquiredGift r1, GiftAuctionController.Auction r2, View.OnClickListener r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.object2 = r2;
                r0.clickCallback = r3;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                AcquiredGiftsCell.m14857$$Nest$mbind((AcquiredGiftsCell) r1, (GiftAuctionController.Auction) r2.object2, (TL_stars.TL_StarGiftAuctionAcquiredGift) r2.object, r2.clickCallback);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public AcquiredGiftsCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                AcquiredGiftsCell r0 = new AcquiredGiftsCell(r1, r5, r3);
                r0.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f));
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }
        }

        /* renamed from: $r8$lambda$2ZwwojGXji8m_bkuai2mnK5s-7k, reason: not valid java name */
        public static /* synthetic */ void m14856$r8$lambda$2ZwwojGXji8m_bkuai2mnK5s7k(AcquiredGiftsCell r0, View.OnClickListener r1) {
            r0.lambda$bind$0(r1);
        }

        /* renamed from: -$$Nest$mbind, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14857$$Nest$mbind(AcquiredGiftsCell r0, GiftAuctionController.Auction r1, TL_stars.TL_StarGiftAuctionAcquiredGift r2, View.OnClickListener r3) {
            r0.bind(r1, r2, r3);
        }

        public AcquiredGiftsCell(Context r2, Theme.ResourcesProvider r3, int r4) {
            super(r2);
            this.currentAccount = r4;
            this.resourcesProvider = r3;
            setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(9.0f));
        }

        private void bind(GiftAuctionController.Auction r9, TL_stars.TL_StarGiftAuctionAcquiredGift r10, View.OnClickListener r11) {
            removeAllViews();
            SpannableStringBuilder r0 = new SpannableStringBuilder("*");
            r0.setSpan(new AnimatedEmojiSpan(r9.giftDocumentId, Theme.chat_actionTextPaint.getFontMetricsInt()), 0, r0.length(), 33);
            r0.append(' ');
            r0.append(LocaleController.formatString(R.string.Gift2AuctionsAcquiredRound2, new Object[]{r9.gift.title, Integer.valueOf(r10.gift_num), Integer.valueOf(r10.round)}));
            r0.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r0.length(), 33);
            SpannableStringBuilder r92 = new SpannableStringBuilder();
            r92.append(StarsIntroActivity.replaceStarsWithPlain("⭐️" + LocaleController.formatNumber(r10.bid_amount, ','), 0.75f));
            String r1 = LocaleController.formatString(R.string.Gift2AuctionsAcquiredTop, new Object[]{Integer.valueOf(r10.pos)});
            TableView r2 = new TableView(getContext(), this.resourcesProvider);
            r2.addFullRow(r0).setFilled(true);
            r2.addRowUser(LocaleController.getString(R.string.Gift2AuctionsAcquiredRecipient), this.currentAccount, DialogObject.getPeerDialogId(r10.peer), new AcquiredGiftsSheet$AcquiredGiftsCell$$ExternalSyntheticLambda0(this, r11));
            r2.addRowDateTime(LocaleController.getString(R.string.Gift2AuctionsAcquiredDate), r10.date);
            r2.addRow(LocaleController.getString(R.string.Gift2AuctionsAcquiredAcceptedBid), r92, r1, null);
            addView(r2, LayoutHelper.createFrame(-1, -2.0f));
        }

        private /* synthetic */ void lambda$bind$0(View.OnClickListener r1) {
            r1.onClick(this);
        }
    }

    public static /* synthetic */ void $r8$lambda$HLA2HufTkWAEylodHDmfcb8x3Rs(View r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$VSYb4LSu1EMRYGfegyrI6WVFMt0(AcquiredGiftsSheet r0, TL_stars.TL_StarGiftAuctionAcquiredGift r1, View r2) {
        r0.lambda$fillItems$2(r1, r2);
    }

    /* renamed from: $r8$lambda$Xn6u7QZTcfKUIvfnF0-g10gh-Gw, reason: not valid java name */
    public static /* synthetic */ void m14855$r8$lambda$Xn6u7QZTcfKUIvfnF0g10ghGw(AcquiredGiftsSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$jLjSCF9HYgrbTmup8grpxrKanLo(AcquiredGiftsSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public AcquiredGiftsSheet(Context r10, Theme.ResourcesProvider r11, GiftAuctionController.Auction r12, List<TL_stars.TL_StarGiftAuctionAcquiredGift> r13) {
        super(r10, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r11);
        this.auction = r12;
        this.gifts = r13;
        this.topPadding = 0.2f;
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        this.actionBar.setTitle(getTitle());
        fixNavigationBar();
        this.recyclerListView.setPadding(this.backgroundPaddingLeft, AndroidUtilities.dp(9.0f), this.backgroundPaddingLeft, AndroidUtilities.dp(64.0f));
        this.recyclerListView.setOnItemClickListener(new AcquiredGiftsSheet$$ExternalSyntheticLambda0());
        this.recyclerListView.setOverScrollMode(2);
        ButtonWithCounterView r102 = new ButtonWithCounterView(r10, r11);
        r102.setOnClickListener(new AcquiredGiftsSheet$$ExternalSyntheticLambda1(this));
        r102.setText(LocaleController.getString(R.string.OK), false);
        FrameLayout.LayoutParams r112 = LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 16.0f, 16.0f, 16.0f);
        int r122 = r112.leftMargin;
        int r132 = this.backgroundPaddingLeft;
        r112.leftMargin = r122 + r132;
        r112.rightMargin += r132;
        this.containerView.addView(r102, r112);
        this.adapter.update(false);
    }

    private void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        List<TL_stars.TL_StarGiftAuctionAcquiredGift> r52 = this.gifts;
        if (r52 != null) goto L5;
        return;
    L5:
        Iterator<TL_stars.TL_StarGiftAuctionAcquiredGift> r53 = r52.iterator();
    L7:
        if (r53.hasNext() == false) goto L9;
        TL_stars.TL_StarGiftAuctionAcquiredGift r0 = r53.next();
        r4.add(AcquiredGiftsCell.Factory.as(r0, this.auction, new AcquiredGiftsSheet$$ExternalSyntheticLambda3(this, r0)));
        goto L7
    L9:
        r4.add(UItem.asSpace(AndroidUtilities.dp(16.0f)));
    }

    private /* synthetic */ void lambda$fillItems$2(TL_stars.TL_StarGiftAuctionAcquiredGift r1, View r2) {
        openProfile(DialogObject.getPeerDialogId(r1.peer));
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
    }

    private void openProfile(long r7) {
        dismiss();
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        if (UserObject.isService(r7) == true) goto L16;
        Bundle r1 = new Bundle();
        if (r7 <= 0) goto L12;
        r1.putLong("user_id", r7);
        if (r7 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L13;
        r1.putBoolean("my_profile", true);
    L13:
        r1.putBoolean("open_gifts", true);
        r0.presentFragment(new ProfileActivity(r1));
        return;
    L12:
        r1.putLong("chat_id", -r7);
        goto L13
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new AcquiredGiftsSheet$$ExternalSyntheticLambda2(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        List<TL_stars.TL_StarGiftAuctionAcquiredGift> r2 = this.gifts;
        if (r2 != null) goto L7;
        return null;
    L7:
        return LocaleController.formatPluralString("Gift2AuctionsAcquiredGifts", r2.size(), new Object[0]);
    }
}
