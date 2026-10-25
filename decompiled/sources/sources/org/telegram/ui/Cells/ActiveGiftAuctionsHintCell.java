package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.CountdownTimer;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Gifts.ActiveAuctionsSheet;
import org.telegram.ui.Gifts.AuctionBidSheet;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class ActiveGiftAuctionsHintCell extends FrameLayout implements GiftAuctionController.OnActiveAuctionsUpdateListeners {
    private List<GiftAuctionController.Auction> activeAuctions;
    private final int currentAccount;
    private boolean isOutbid;
    private final AnimatedTextView messageTextView;
    private final CountDown timerView;
    private final AnimatedTextView titleTextView;

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes6.dex */
    public static class CountDown extends FrameLayout {
        private final int currentAccount;
        private final Drawable drawable;
        private int endTime;
        private final Paint fillPaint;
        public final AnimatedTextView.AnimatedTextDrawable textView;
        private final CountdownTimer timer;

        /* renamed from: $r8$lambda$4D_6L4a_6ZcodNFT0PXgHTu1-zs, reason: not valid java name */
        public static /* synthetic */ void m6612$r8$lambda$4D_6L4a_6ZcodNFT0PXgHTu1zs(CountDown r0, long r1) {
            r0.updateTimer(r1);
        }

        /* renamed from: -$$Nest$mupdateTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6613$$Nest$mupdateTimer(CountDown r0, long r1) {
            r0.updateTimer(r1);
        }

        public CountDown(Context r10, int r11) {
            super(r10);
            Paint r0 = new Paint(1);
            this.fillPaint = r0;
            this.timer = new CountdownTimer(new ActiveGiftAuctionsHintCell$CountDown$$ExternalSyntheticLambda0(this));
            this.currentAccount = r11;
            this.drawable = r10.getResources().getDrawable(R.drawable.filled_gift_sell_24).mutate();
            AnimatedTextView.AnimatedTextDrawable r102 = new AnimatedTextView.AnimatedTextDrawable();
            this.textView = r102;
            r102.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r102.setCallback(this);
            r102.setTypeface(AndroidUtilities.bold());
            r102.setTextSize(AndroidUtilities.dp(14.0f));
            r102.setTextColor(-1);
            r102.setGravity(3);
            r0.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(72.0f), 0.0f, new int[]{-13460514, -10042885}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        }

        private void updateTimer(long r5) {
            AnimatedTextView.AnimatedTextDrawable r1 = this.textView;
            if (r5 != 0) goto L7;
            r1.setText(LocaleController.getString(R.string.Gift2AuctionPriceView));
            return;
        L7:
            if (r5 <= 3600) goto L9;
            String r52 = AndroidUtilities.formatDuration((int) r5, false);
        L10:
            r1.setText(r52, isAttachedToWindow());
            return;
        L9:
            r52 = AndroidUtilities.formatDurationNoHours((int) r5, false);
            goto L10
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r13) {
            int r0 = (getMeasuredWidth() - AndroidUtilities.dp(14.0f)) - ((int) this.textView.getCurrentWidth());
            int r2 = r0 - AndroidUtilities.dp(30.0f);
            r13.save();
            r13.translate(r2, 0.0f);
            r13.drawRoundRect(0.0f, 0.0f, getWidth() - r2, getHeight(), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.fillPaint);
            r13.restore();
            this.textView.setBounds(r0, 0, getMeasuredWidth() - AndroidUtilities.dp(8.0f), getMeasuredHeight() - AndroidUtilities.dp(1.0f));
            this.textView.draw(r13);
            this.drawable.setBounds(AndroidUtilities.dp(-22.0f) + r0, AndroidUtilities.dp(5.0f), r0 + AndroidUtilities.dp(-4.0f), AndroidUtilities.dp(23.0f));
            this.drawable.draw(r13);
            super.dispatchDraw(r13);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            start(this.endTime);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.timer.stop();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(LayoutHelper.measureSpecExactlyDp(172), LayoutHelper.measureSpecExactlyDp(28));
        }

        public void start(int r4) {
            this.endTime = r4;
            if (isAttachedToWindow() == false) goto L6;
            long r1 = Math.max(0, r4 - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
            this.timer.start(r1);
            updateTimer(r1);
            return;
        }

        public void stop() {
            this.endTime = 0;
            this.timer.stop();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.textView) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$31hBbmeKjJyGeVavUYvcSsYn8ws(ActiveGiftAuctionsHintCell r0, View r1) {
        r0.onClick(r1);
    }

    public ActiveGiftAuctionsHintCell(Context r10, int r11) {
        super(r10);
        this.activeAuctions = new ArrayList();
        this.currentAccount = r11;
        LinearLayout r0 = new LinearLayout(r10);
        r0.setOrientation(1);
        AnimatedTextView r1 = new AnimatedTextView(r10);
        this.titleTextView = r1;
        r1.setTextSize(AndroidUtilities.dp(14.0f));
        r1.setTypeface(AndroidUtilities.bold());
        r1.setTranslationY(-AndroidUtilities.dp(1.0f));
        r0.addView(r1, LayoutHelper.createLinear(-1, 18));
        AnimatedTextView r12 = new AnimatedTextView(r10);
        this.messageTextView = r12;
        r12.setTextSize(AndroidUtilities.dp(13.0f));
        r0.addView(r12, LayoutHelper.createLinear(-1, 17, 2.0f, 0.0f, 2.0f, 0.0f));
        CountDown r13 = new CountDown(r10, r11);
        this.timerView = r13;
        CountDown.m6613$$Nest$mupdateTimer(r13, 299);
        addView(r0, LayoutHelper.createFrame(-1, -2.0f, 16, 14.0f, 0.0f, 90.0f, 0.0f));
        addView(r13, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 11.0f, 0.0f));
        updateColors();
        setOnClickListener(new ActiveGiftAuctionsHintCell$$ExternalSyntheticLambda0(this));
    }

    private static String formatPlace(int r2) {
        int r0 = r2 % 100;
        if (r0 >= 11) goto L5;
    L8:
        int r02 = r2 % 10;
        if (r02 == 1) goto L21;
        if (r02 == 2) goto L19;
        if (r02 == 3) goto L17;
        return LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveStatusWinningOtherTh, new Object[]{Integer.valueOf(r2)});
    L17:
        return LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveStatusWinningOtherRd, new Object[]{Integer.valueOf(r2)});
    L19:
        return LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveStatusWinningOtherNd, new Object[]{Integer.valueOf(r2)});
    L21:
        return LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveStatusWinningOtherSt, new Object[]{Integer.valueOf(r2)});
    L5:
        if (r0 > 13) goto L8;
        return LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveStatusWinningOtherTh, new Object[]{Integer.valueOf(r2)});
    }

    private void onClick(View r4) {
        if (this.activeAuctions.size() != 1) goto L6;
        new AuctionBidSheet(getContext(), null, null, this.activeAuctions.get(0)).show();
        return;
    L6:
        new ActiveAuctionsSheet(getContext(), null).show();
    }

    private void update(boolean r14) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        int r1 = this.activeAuctions.size();
        if (r1 != 0) goto L5;
        return;
    L5:
        int r2 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r4 = 0;
        boolean r5 = false;
        boolean r6 = false;
    L6:
        boolean r7 = true;
        if (r4 >= r1) goto L18;
        GiftAuctionController.Auction r8 = this.activeAuctions.get(r4);
        r5 = r5 | r8.isUpcoming(r2);
        if (r8.giftDocumentId == 0) goto L11;
        r0.append("*");
        r0.setSpan(new AnimatedEmojiSpan(r8.giftDocumentId, this.titleTextView.getPaint().getFontMetricsInt()), r0.length() - 1, r0.length(), 33);
    L11:
        GiftAuctionController.Auction.BidStatus r82 = r8.getBidStatus();
        if (r82 == GiftAuctionController.Auction.BidStatus.OUTBID) goto L17;
        if (r82 == GiftAuctionController.Auction.BidStatus.RETURNED) goto L17;
        r7 = false;
    L17:
        r6 = r6 | r7;
        r4 = r4 + 1;
        goto L6
    L18:
        r0.append(' ');
        if (r5 == false) goto L24;
        if (r1 != 1) goto L22;
        String r22 = LocaleController.getString(R.string.Gift2ActiveAuctionsUpcomingAuctionTitle);
    L23:
        r0.append(r22);
    L28:
        this.titleTextView.setText(r0, r14);
        this.isOutbid = false;
        if (r5 == false) goto L31;
        this.messageTextView.setText(LocaleController.getString(R.string.Gift2ActiveAuctionsActiveStatusEarly));
    L46:
        updateColors();
        return;
    L31:
        if (r6 == false) goto L33;
        this.messageTextView.setText(LocaleController.getString(R.string.Gift2ActiveAuctionsActiveStatusOutbid));
        this.isOutbid = true;
        goto L46
    L33:
        if (r1 <= 1) goto L35;
        this.messageTextView.setText(LocaleController.getString(R.string.Gift2ActiveAuctionsActiveStatusWinningAll));
        goto L46
    L35:
        int r142 = this.activeAuctions.get(0).getApproximatedMyPlace();
        if (r142 != 1) goto L39;
        String r143 = LocaleController.getString(R.string.Gift2ActiveAuctionsActiveStatusWinning1Place);
    L45:
        this.messageTextView.setText(LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveStatusWinningOne, new Object[]{r143}));
        goto L46
    L39:
        if (r142 != 2) goto L42;
        r143 = LocaleController.getString(R.string.Gift2ActiveAuctionsActiveStatusWinning2Place);
        goto L45
    L42:
        if (r142 != 3) goto L44;
        r143 = LocaleController.getString(R.string.Gift2ActiveAuctionsActiveStatusWinning3Place);
        goto L45
    L44:
        r143 = formatPlace(r142);
        goto L45
    L22:
        r22 = LocaleController.formatString(R.string.Gift2ActiveAuctionsUpcomingAuctionsTitle, new Object[]{Integer.valueOf(r1)});
        goto L23
    L24:
        if (r1 != 1) goto L26;
        String r23 = LocaleController.getString(R.string.Gift2ActiveAuctionsActiveAuctionTitle);
    L27:
        r0.append(r23);
        goto L28
    L26:
        r23 = LocaleController.formatString(R.string.Gift2ActiveAuctionsActiveAuctionsTitle, new Object[]{Integer.valueOf(r1)});
        goto L27
    }

    @Override // org.telegram.messenger.GiftAuctionController.OnActiveAuctionsUpdateListeners
    public void onActiveAuctionsUpdate(List<GiftAuctionController.Auction> r4) {
        ArrayList r0 = new ArrayList(r4);
        this.activeAuctions = r0;
        if (r0.size() != 1) goto L11;
        int r1 = 0;
        GiftAuctionController.Auction r42 = this.activeAuctions.get(0);
        if (r42.isUpcoming() == false) goto L7;
        this.timerView.start(r42.gift.auction_start_date);
    L12:
        update(true);
        return;
    L7:
        TL_stars.TL_starGiftAuctionState r43 = r42.auctionStateActive;
        if (r43 == null) goto L10;
        r1 = Math.max(0, r43.next_round_at);
    L10:
        this.timerView.start(r1);
        goto L12
    L11:
        this.timerView.stop();
        this.timerView.textView.setText(LocaleController.getString(R.string.Gift2AuctionPriceView), true);
        goto L12
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        GiftAuctionController.getInstance(this.currentAccount).subscribeToActiveAuctionsUpdates(this);
        onActiveAuctionsUpdate(GiftAuctionController.getInstance(this.currentAccount).getActiveAuctions());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GiftAuctionController.getInstance(this.currentAccount).unsubscribeFromActiveAuctionsUpdates(this);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
    }

    public void updateColors() {
        setBackground(Theme.getSelectorDrawable(false));
        this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        AnimatedTextView r0 = this.messageTextView;
        if (this.isOutbid == false) goto L5;
        int r1 = Theme.key_text_RedBold;
    L6:
        r0.setTextColor(Theme.getColor(r1));
        invalidate();
        return;
    L5:
        r1 = Theme.key_windowBackgroundWhiteGrayText;
        goto L6
    }
}
