package org.telegram.ui.Gifts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.CountdownTimer;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class ActiveAuctionsSheet extends BottomSheetWithRecyclerListView implements GiftAuctionController.OnActiveAuctionsUpdateListeners {
    private final LongSparseArray<ActiveAuctionCell> activeAuctionCells;
    private List<GiftAuctionController.Auction> activeAuctions;
    private UniversalAdapter adapter;
    private final UItem headerItem;
    private boolean isOpenAnimationEnd;

    public static class ActiveAuctionCell extends FrameLayout {
        private final GiftAuctionController.Auction auction;
        private final ButtonWithCounterView buttonView;
        private final ColoredImageSpan cs;
        private final AnimatedTextView messageView;
        private final Paint paint;
        private final ColoredImageSpan[] spanRefStars;
        private final CountdownTimer timer;
        private final AnimatedTextView titleView;

        public static /* synthetic */ void $r8$lambda$B0KvTVwXww15G1ikr2egis9Twz4(ActiveAuctionCell r0, long r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgetbuttonView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ButtonWithCounterView m14859$$Nest$fgetbuttonView(ActiveAuctionCell r0) {
            return r0.buttonView;
        }

        /* renamed from: -$$Nest$fgettimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CountdownTimer m14860$$Nest$fgettimer(ActiveAuctionCell r0) {
            return r0.timer;
        }

        /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14861$$Nest$mupdateButton(ActiveAuctionCell r0, long r1, boolean r3) {
            r0.updateButton(r1, r3);
        }

        public ActiveAuctionCell(Context r12, Theme.ResourcesProvider r13, GiftAuctionController.Auction r14) {
            super(r12);
            Paint r0 = new Paint(1);
            this.paint = r0;
            this.timer = new CountdownTimer(new ActiveAuctionsSheet$ActiveAuctionCell$$ExternalSyntheticLambda0(this));
            this.cs = new ColoredImageSpan(R.drawable.filled_gift_sell_24);
            this.spanRefStars = new ColoredImageSpan[1];
            this.auction = r14;
            setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(9.0f));
            r0.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, 0.0f, 536870912);
            r0.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            ButtonWithCounterView r02 = new ButtonWithCounterView(r12, r13);
            this.buttonView = r02;
            r02.setTextHacks(false, true, true, true);
            RLottieImageView r1 = new RLottieImageView(r12);
            AnimatedTextView r3 = new AnimatedTextView(r12);
            this.titleView = r3;
            r3.setTextSize(AndroidUtilities.dp(14.0f));
            r3.setTypeface(AndroidUtilities.bold());
            r3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            AnimatedTextView r2 = new AnimatedTextView(r12);
            this.messageView = r2;
            r2.setTextSize(AndroidUtilities.dp(12.0f));
            TLRPC.Document r122 = r14.gift.sticker;
            if (r122 == null) goto L5;
            r1.setAnimation(r122, 44, 44);
        L5:
            addView(r3, LayoutHelper.createFrame(-1, 18.0f, 51, 64.0f, 15.0f, 15.0f, 0.0f));
            addView(r2, LayoutHelper.createFrame(-1, 17.0f, 51, 64.0f, 34.0f, 15.0f, 0.0f));
            addView(r1, LayoutHelper.createFrame(44, 44.0f, 51, 14.0f, 11.0f, 0.0f, 0.0f));
            addView(r02, LayoutHelper.createFrame(-1, 44.0f, 80, 15.0f, 0.0f, 15.0f, 15.0f));
            updateStatus(false);
        }

        private /* synthetic */ void lambda$new$0(long r2) {
            updateButton(r2, true);
        }

        private void updateButton(long r5, boolean r7) {
            String r52 = AndroidUtilities.formatDurationNoHours((int) r5, false);
            SpannableStringBuilder r0 = new SpannableStringBuilder("*");
            r0.setSpan(this.cs, 0, r0.length(), 33);
            r0.append("  ");
            r0.append(LocaleController.getString(R.string.Gift2ActiveAuctionsActiveRaiseBid));
            r0.append("  ");
            r0.append(r52);
            this.buttonView.setText(r0, r7);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            r11.drawRoundRect(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(9.0f), getMeasuredWidth() - AndroidUtilities.dp(14.0f), getMeasuredHeight() - AndroidUtilities.dp(9.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.paint);
            super.dispatchDraw(r11);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.timer.stop();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, LayoutHelper.measureSpecExactlyDp(146));
        }

        public void updateStatus(boolean r7) {
            if (this.auction.auctionStateActive == null) goto L5;
            this.titleView.setText(LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveRound, new Object[]{LocaleController.formatNumber(r0.current_round, ','), LocaleController.formatNumber(this.auction.auctionStateActive.total_rounds, ',')}), r7);
        L5:
            String r0 = "⭐️" + LocaleController.formatNumber(this.auction.auctionUserState.bid_amount, ',');
            boolean r1 = this.auction.getBidStatus().isOutbid();
            AnimatedTextView r2 = this.messageView;
            if (r1 == false) goto L9;
            r2.setText(StarsIntroActivity.replaceStarsWithPlain(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveBidOutbid, new Object[]{r0})), 0.66f, this.spanRefStars), r7);
            this.messageView.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L9:
            r2.setText(StarsIntroActivity.replaceStarsWithPlain(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveBidActive, new Object[]{r0, Integer.valueOf(this.auction.getApproximatedMyPlace())})), 0.66f, this.spanRefStars), r7);
            this.messageView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        }
    }

    /* renamed from: $r8$lambda$1mfmf-_EIbejaYdMauFE7FX4ihI, reason: not valid java name */
    public static /* synthetic */ void m14858$r8$lambda$1mfmf_EIbejaYdMauFE7FX4ihI(ActiveAuctionsSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6ITgbbJC8F6rhw011WBWFT7FnSQ(ActiveAuctionsSheet r0, Context r1, Theme.ResourcesProvider r2, GiftAuctionController.Auction r3, View r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public ActiveAuctionsSheet(Context r12, Theme.ResourcesProvider r13) {
        super(r12, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r13);
        this.activeAuctionCells = new LongSparseArray();
        this.activeAuctions = new ArrayList();
        int r11 = Theme.key_windowBackgroundGray;
        setBackgroundColor(Theme.getColor(r11));
        GiftAuctionController.getInstance(this.currentAccount).subscribeToActiveAuctionsUpdates(this);
        int r122 = 0;
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        fixNavigationBar(Theme.getColor(r11));
        LinearLayout r112 = new LinearLayout(r12);
        r112.setOrientation(1);
        r112.setClipChildren(false);
        r112.setClipToPadding(false);
        r112.setClickable(true);
        this.headerItem = UItem.asCustom(-1, r112);
        this.recyclerListView.setPadding(this.backgroundPaddingLeft, AndroidUtilities.dp(9.0f), this.backgroundPaddingLeft, AndroidUtilities.dp(9.0f));
        this.recyclerListView.setOverScrollMode(2);
        this.adapter.update(false);
        ArrayList<GiftAuctionController.Auction> r2 = GiftAuctionController.getInstance(this.currentAccount).getActiveAuctions();
        int r3 = r2.size();
    L3:
        if (r122 >= r3) goto L5;
        GiftAuctionController.Auction r4 = r2.get(r122);
        r122 = r122 + 1;
        GiftAuctionController.Auction r42 = r4;
        ActiveAuctionCell r5 = new ActiveAuctionCell(r12, r13, r42);
        ActiveAuctionCell.m14859$$Nest$fgetbuttonView(r5).setOnClickListener(new ActiveAuctionsSheet$$ExternalSyntheticLambda1(this, r12, r13, r42));
        r112.addView(r5, LayoutHelper.createLinear(-1, -2));
        this.activeAuctionCells.put(r42.giftId, r5);
        goto L3
    L5:
        onActiveAuctionsUpdate(r2);
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        r1.add(this.headerItem);
    }

    private /* synthetic */ void lambda$new$0(Context r2, Theme.ResourcesProvider r3, GiftAuctionController.Auction r4, View r5) {
        new AuctionBidSheet(r2, r3, null, r4).show();
        dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new ActiveAuctionsSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        GiftAuctionController.getInstance(this.currentAccount).unsubscribeFromActiveAuctionsUpdates(this);
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        List<GiftAuctionController.Auction> r1 = this.activeAuctions;
        if (r1 != null) goto L7;
        return null;
    L7:
        return LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveAuctionsTitle, new Object[]{Integer.valueOf(r1.size())});
    }

    @Override // org.telegram.messenger.GiftAuctionController.OnActiveAuctionsUpdateListeners
    public void onActiveAuctionsUpdate(List<GiftAuctionController.Auction> r7) {
        this.activeAuctions = new ArrayList(r7);
        this.actionBar.setTitle(getTitle());
        Iterator<GiftAuctionController.Auction> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L12;
        GiftAuctionController.Auction r0 = r72.next();
        TL_stars.TL_starGiftAuctionState r1 = r0.auctionStateActive;
        if (r1 == null) goto L8;
        int r12 = r1.next_round_at;
    L9:
        ActiveAuctionCell r02 = this.activeAuctionCells.get(r0.giftId);
        if (r02 == null) goto L4;
        r02.updateStatus(this.isOpenAnimationEnd);
        long r13 = Math.max(0, r12 - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        ActiveAuctionCell.m14861$$Nest$mupdateButton(r02, r13, this.isOpenAnimationEnd);
        ActiveAuctionCell.m14860$$Nest$fgettimer(r02).start(r13);
        goto L4
    L8:
        r12 = 0;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        this.isOpenAnimationEnd = true;
    }
}
