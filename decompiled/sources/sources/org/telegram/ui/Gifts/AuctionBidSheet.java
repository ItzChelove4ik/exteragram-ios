package org.telegram.ui.Gifts;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.android.dx.AppDataDirGuesser;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.time.DurationKt;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.CountdownTimer;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.BalanceCloud;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.Stories.HighlightMessageSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class AuctionBidSheet extends BottomSheetWithRecyclerListView implements GiftAuctionController.OnAuctionUpdateListener {
    private UniversalAdapter adapter;
    private AnimatedEmojiSpan animatedEmojiSpan;
    private GiftAuctionController.Auction auction;
    private final BalanceCloud balanceCloud;
    private boolean balanceCloudVisible;
    private boolean bidIsPending;
    private final FrameLayout bulletinContainer;
    private final ButtonWithCounterView buttonView;
    private Runnable closeParentSheet;
    private final long giftId;
    private final InfoCell giftsLeftCell;
    private final UItem headerItem;
    private boolean isFirstCheck;
    private boolean isOpenAnimationEnd;
    private long lastAcquiredCount;
    private long lastRecipientDialogId;
    private final InfoCell minimumBidCell;
    private final InfoCell nextRoundCell;
    private final BoolAnimator outbidColor;
    private final Params params;
    private final ColoredImageSpan[] refS;
    private final BidderCell selfBidderCell;
    private final AnimatedTextView selfBidderFutureGift;
    private final HeaderCell selfBidderHeader;
    private final StarsReactionsSheet.StarsSlider slider;
    private final ColoredImageSpan[] spanRefStars;
    private final CountdownTimer timer;
    private final BidderCell[] topBidderCells;
    private final BoolAnimator winningColor;

    /* renamed from: org.telegram.ui.Gifts.AuctionBidSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends StarsReactionsSheet.StarsSlider {
        final /* synthetic */ AuctionBidSheet this$0;

        public AnonymousClass1(AuctionBidSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stars.StarsReactionsSheet.StarsSlider, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L9;
            if (r4.getY() <= (getMeasuredHeight() - AndroidUtilities.dp(48.0f))) goto L9;
            return false;
        L9:
            return super.dispatchTouchEvent(r4);
        }

        @Override // org.telegram.ui.Stars.StarsReactionsSheet.StarsSlider
        public boolean onTapCustom(float r5, float r6) {
            if (getProgress() <= 0.99d) goto L5;
        L9:
            AuctionBidSheet.m14867$$Nest$mshowCustomPlaceABid(this.this$0);
            return true;
        L5:
            if (r5 > (getMeasuredWidth() * 0.9f)) goto L9;
            return false;
        }

        @Override // org.telegram.ui.Stars.StarsReactionsSheet.StarsSlider
        public void onValueChanged(int r1) {
            super.onValueChanged(r1);
            AuctionBidSheet.m14866$$Nest$monSliderValueChanged(this.this$0, r1);
        }

        @Override // org.telegram.ui.Stars.StarsReactionsSheet.StarsSlider
        public void setValue(int r1) {
            super.setValue(r1);
            AuctionBidSheet.m14866$$Nest$monSliderValueChanged(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionBidSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ButtonWithCounterView {
        final /* synthetic */ AuctionBidSheet this$0;

        public AnonymousClass2(AuctionBidSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (isEnabled() == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionBidSheet$3, reason: invalid class name */
    public class AnonymousClass3 implements Bulletin.Delegate {
        final /* synthetic */ AuctionBidSheet this$0;

        public AnonymousClass3(AuctionBidSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.dp(64.0f);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionBidSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends EditTextCaption {
        final /* synthetic */ AuctionBidSheet this$0;
        final /* synthetic */ Drawable val$drawable;

        public AnonymousClass4(AuctionBidSheet r1, Context r2, Theme.ResourcesProvider r3, Drawable r4) {
            this.this$0 = r1;
            this.val$drawable = r4;
            super(r2, r3);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            this.val$drawable.setBounds(0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(28.0f));
            this.val$drawable.draw(r6);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionBidSheet$5, reason: invalid class name */
    public class AnonymousClass5 implements TextWatcher {
        final /* synthetic */ AuctionBidSheet this$0;
        final /* synthetic */ View[] val$buttonPositive;

        public AnonymousClass5(AuctionBidSheet r1, View[] r2) {
            this.this$0 = r1;
            this.val$buttonPositive = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
        L12:
            th = move-exception;
            FileLog.e(th);
            return;
        L3:
            if (Integer.parseInt(r5.toString()) < AuctionBidSheet.m14865$$Nest$fgetauction(this.this$0).getMinimumBid()) goto L5;
            boolean r52 = true;
        L6:
            ViewPropertyAnimator r1 = this.val$buttonPositive[0].animate();     // Catch: Throwable -> L12
            if (r52 == false) goto L9;
            float r2 = 1.0f;
        L10:
            r1.alpha(r2).setDuration(180).start();     // Catch: Throwable -> L12
            this.val$buttonPositive[0].setEnabled(r52);     // Catch: Throwable -> L12
            this.val$buttonPositive[0].setClickable(r52);     // Catch: Throwable -> L12
            return;
        L9:
            r2 = 0.6f;
            goto L10
        L5:
            r52 = false;
            goto L6
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static class BidderCell extends LinearLayout implements NotificationCenter.NotificationCenterDelegate {
        private final BackupImageView backupImageView;
        private final AnimatedTextView bidTextView;
        private boolean drawDivider;
        private final AnimatedTextView nameTextView;
        private final AnimatedTextView placeTextView;
        private final ColoredImageSpan[] ref;

        /* renamed from: -$$Nest$fgetplaceTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedTextView m14868$$Nest$fgetplaceTextView(BidderCell r0) {
            return r0.placeTextView;
        }

        /* renamed from: -$$Nest$fputdrawDivider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14869$$Nest$fputdrawDivider(BidderCell r0, boolean r1) {
            r0.drawDivider = r1;
        }

        public BidderCell(Context r12, Theme.ResourcesProvider r13) {
            super(r12);
            this.ref = new ColoredImageSpan[1];
            setOrientation(0);
            AnimatedTextView r2 = new AnimatedTextView(r12);
            this.nameTextView = r2;
            int r3 = Theme.key_windowBackgroundWhiteBlackText;
            r2.setTextColor(Theme.getColor(r3, r13));
            r2.setTextSize(AndroidUtilities.dp(15.0f));
            r2.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            r2.setEllipsizeByGradient(true);
            AnimatedTextView r0 = new AnimatedTextView(r12);
            this.bidTextView = r0;
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r13));
            r0.setTextSize(AndroidUtilities.dp(15.0f));
            BackupImageView r5 = new BackupImageView(r12);
            this.backupImageView = r5;
            AnimatedTextView r6 = new AnimatedTextView(r12);
            this.placeTextView = r6;
            r6.setTextSize(AndroidUtilities.dp(15.0f));
            r6.setPadding(AndroidUtilities.dp(20.0f), 0, 0, 0);
            r6.setTextColor(Theme.getColor(r3, r13));
            r6.setTypeface(AndroidUtilities.bold());
            r6.setGravity(17);
            addView(r6, LayoutHelper.createLinear(66, -2, 0.0f, 16));
            addView(r5, LayoutHelper.createLinear(32, 32, 0.0f, 16));
            addView(r2, LayoutHelper.createLinear(0, -2, 1.0f, 16));
            addView(r0, LayoutHelper.createLinear(-2, -2, 0.0f, 16, 0, 0, 20, 0));
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            this.placeTextView.invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.drawDivider == false) goto L6;
            r8.drawLine(AndroidUtilities.dp(112.0f), getMeasuredHeight() - 1, getMeasuredWidth() - AndroidUtilities.dp(16.0f), getMeasuredHeight(), Theme.dividerPaint);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
        }

        public void setBid(long r4, boolean r6) {
            this.bidTextView.setText(StarsIntroActivity.replaceStarsWithPlain("⭐️" + LocaleController.formatNumber((int) r4, ','), 0.78f, this.ref), r6);
        }

        public void setPlace(int r3, boolean r4, boolean r5) {
            if (r4 == false) goto L17;
            if (r3 > 3) goto L17;
            if (r3 != 1) goto L10;
            AnimatedTextView r2 = this.placeTextView;
            r2.setText(Emoji.replaceWithRestrictedEmoji("🥇", r2.getPaint().getFontMetricsInt(), null), r5);
            return;
        L10:
            if (r3 != 2) goto L13;
            AnimatedTextView r22 = this.placeTextView;
            r22.setText(Emoji.replaceWithRestrictedEmoji("🥈", r22.getPaint().getFontMetricsInt(), null), r5);
            return;
        L13:
            if (r3 != 3) goto L25;
            AnimatedTextView r23 = this.placeTextView;
            r23.setText(Emoji.replaceWithRestrictedEmoji("🥉", r23.getPaint().getFontMetricsInt(), null), r5);
            return;
        L25:
            return;
        L17:
            if (r3 < 10000) goto L19;
            this.placeTextView.setTextSize(AndroidUtilities.dp(12.0f));
        L23:
            this.placeTextView.setText(Integer.toString(r3), r5);
            return;
        L19:
            AnimatedTextView r42 = this.placeTextView;
            if (r3 < 1000) goto L22;
            r42.setTextSize(AndroidUtilities.dp(14.0f));
            goto L23
        L22:
            r42.setTextSize(AndroidUtilities.dp(15.0f));
            goto L23
        }

        public void setUser(TLRPC.User r2, boolean r3) {
            AvatarDrawable r32 = new AvatarDrawable();
            r32.setInfo(r2);
            this.backupImageView.setForUserOrChat(r2, r32);
            this.backupImageView.setRoundRadius(AndroidUtilities.dp(16.0f));
            this.nameTextView.setText(UserObject.getUserName(r2));
        }
    }

    public static class InfoCell extends FrameLayout {
        public final AnimatedTextView infoView;
        public final TextView titleView;

        public InfoCell(Context r7, Theme.ResourcesProvider r8) {
            super(r7);
            LinearLayout r0 = new LinearLayout(r7);
            r0.setOrientation(1);
            AnimatedTextView r2 = new AnimatedTextView(r7);
            this.infoView = r2;
            int r3 = Theme.key_windowBackgroundWhiteBlackText;
            r2.setTextColor(Theme.getColor(r3, r8));
            r2.setTextSize(AndroidUtilities.dp(17.0f));
            r2.setTypeface(AndroidUtilities.bold());
            r0.addView(r2, LayoutHelper.createLinear(-2, 23, 1));
            TextView r22 = new TextView(r7);
            this.titleView = r22;
            r22.setTextSize(1, 11.0f);
            r22.setTextColor(Theme.getColor(r3, r8));
            r22.setSingleLine();
            r22.setMaxLines(1);
            r22.setEllipsize(TextUtils.TruncateAt.END);
            r0.addView(r22, LayoutHelper.createLinear(-2, -2, 1));
            addView(r0, LayoutHelper.createFrame(-2, -2, 17));
        }
    }

    public static class Params {
        public final long dialogId;
        public final boolean hideName;
        public final TLRPC.TL_textWithEntities message;

        public Params(long r1, boolean r3, TLRPC.TL_textWithEntities r4) {
            this.dialogId = r1;
            this.hideName = r3;
            this.message = r4;
        }
    }

    public static /* synthetic */ void $r8$lambda$1t5tRbF56fBM7LrSRY3JXqAHN40(AuctionBidSheet r0, View r1) {
        r0.lambda$updateButtonText$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$24lyZcqfwBGohxfukBZsn5QqNAc(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$3H2sZSZ6p9m5W_7TQ96JI8Ydpjg(EditTextCaption r0, BaseFragment r1, Activity r2, DialogInterface r3) {
        AndroidUtilities.hideKeyboard(r0);
        if (r1 == null) goto L6;
        AndroidUtilities.requestAdjustResize(r2, r1.getClassGuid());
        return;
    }

    public static /* synthetic */ void $r8$lambda$6fdQswPwxK1vc2cvjmPwM4MvWa0(AuctionBidSheet r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$CxaMEZtoG_dbvLPikNS1un1wtpU(AuctionBidSheet r0, boolean[] r1, Theme.ResourcesProvider r2, List r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$EM9Jw2hySZo1w_6dVoqs6ZVL1b0(AuctionBidSheet r0, long r1, View r3) {
        r0.lambda$updateTable$7(r1, r3);
    }

    /* renamed from: $r8$lambda$G2Xuq_D6dRrzaq-7lKN44Yo7tEw, reason: not valid java name */
    public static /* synthetic */ void m14862$r8$lambda$G2Xuq_D6dRrzaq7lKN44Yo7tEw(View r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$Gjs5MOYaCq2CPw6S1guYlA55XXM(EditTextCaption r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$IIj2P-ZTkJF1E8WqX-D-Ps1JXd4, reason: not valid java name */
    public static /* synthetic */ void m14863$r8$lambda$IIj2PZTkJF1E8WqXDPs1JXd4(AuctionBidSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ik5406Sat7ABbWU3N8AeN8Xh5tQ(AuctionBidSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$S6XrCLsBCbXjqXHOx5SfYL0vCEY(AuctionBidSheet r0, boolean[] r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$U37NOVbIbEr2SNmu7jMcTEXb7hA(Context r0, Theme.ResourcesProvider r1, View r2) {
        new StarsIntroActivity.StarsOptionsSheet(r0, r1).show();
    }

    public static /* synthetic */ void $r8$lambda$fcP6zPcnpddSGquwa2CznhOefeo(AuctionBidSheet r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.onColorFactorChanged(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$kEbIuDGYjHhgjjOWj68BRjfmH0Q(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$kmw4bPfXOHb0s4CajJWEdu3QSmo(AuctionBidSheet r0, View r1) {
        r0.lambda$updateButtonText$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$tX1MsFpkMWRGDcJ3JYNbe_vmhoQ(AuctionBidSheet r0, long r1) {
        r0.updateCountdownCell(r1);
    }

    public static /* synthetic */ void $r8$lambda$wNn6CUF2aemwk8UCg_PhRB9z4og(AuctionBidSheet r0, long r1, Boolean r3, String r4) {
        r0.lambda$sendBid$10(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$xETck-vMRpIw2JfZGa1G3zGdMmE, reason: not valid java name */
    public static /* synthetic */ void m14864$r8$lambda$xETckvMRpIw2JfZGa1G3zGdMmE(AuctionBidSheet r0, EditTextCaption r1, AlertDialog r2, int r3) {
        r0.lambda$showCustomPlaceABid$11(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetauction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GiftAuctionController.Auction m14865$$Nest$fgetauction(AuctionBidSheet r0) {
        return r0.auction;
    }

    /* renamed from: -$$Nest$monSliderValueChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14866$$Nest$monSliderValueChanged(AuctionBidSheet r0, int r1) {
        r0.onSliderValueChanged(r1);
    }

    /* renamed from: -$$Nest$mshowCustomPlaceABid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14867$$Nest$mshowCustomPlaceABid(AuctionBidSheet r0) {
        r0.showCustomPlaceABid();
    }

    public AuctionBidSheet(Context r25, Theme.ResourcesProvider r26, Params r27, GiftAuctionController.Auction r28) {
        super(r25, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r26);
        this.topBidderCells = new BidderCell[3];
        this.isFirstCheck = true;
        this.refS = new ColoredImageSpan[1];
        AuctionBidSheet$$ExternalSyntheticLambda0 r4 = new AuctionBidSheet$$ExternalSyntheticLambda0(this);
        CubicBezierInterpolator r15 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.winningColor = new BoolAnimator(0, r4, r15, 380);
        this.outbidColor = new BoolAnimator(0, new AuctionBidSheet$$ExternalSyntheticLambda0(this), r15, 380);
        this.spanRefStars = new ColoredImageSpan[1];
        this.auction = r28;
        this.params = r27;
        long r2 = r28.giftId;
        this.giftId = r2;
        this.centerTitle = true;
        this.topPadding = 0.2f;
        GiftAuctionController.Auction r9 = GiftAuctionController.getInstance(this.currentAccount).subscribeToGiftAuction(r2, this);
        this.timer = new CountdownTimer(new AuctionBidSheet$$ExternalSyntheticLambda1(this));
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        fixNavigationBar();
        AuctionJoinSheet.initActionBar(this.actionBar, r25, r26, this.currentAccount, r9.gift);
        TLRPC.User r13 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
        LinearLayout r14 = new LinearLayout(r25);
        r14.setOrientation(1);
        r14.setClipChildren(false);
        r14.setClipToPadding(false);
        r14.setClickable(true);
        this.headerItem = UItem.asCustom(-1, r14);
        AnonymousClass1 r22 = new AnonymousClass1(this, r25, r26);
        this.slider = r22;
        r22.drawPlus = true;
        setSliderValues();
        r14.addView(r22, LayoutHelper.createLinear(-1, -2, 0, 0, -40, 0, -48));
        LinearLayout r23 = new LinearLayout(r25);
        r23.setOrientation(0);
        InfoCell r3 = new InfoCell(r25, r26);
        this.minimumBidCell = r3;
        int r42 = AndroidUtilities.dp(12.0f);
        int r5 = Theme.key_windowBackgroundGray;
        r3.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r42, getThemedColor(r5), ColorUtils.compositeColors(getThemedColor(Theme.key_listSelector), getThemedColor(r5))));
        r3.setOnClickListener(new AuctionBidSheet$$ExternalSyntheticLambda2(this));
        r3.titleView.setText(LocaleController.getString(R.string.Gift2AuctionBidInfoMinimumBid));
        InfoCell r0 = new InfoCell(r25, r26);
        this.nextRoundCell = r0;
        r0.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), getThemedColor(r5)));
        r0.titleView.setText(LocaleController.getString(R.string.Gift2AuctionBidInfoUntilNextRound));
        InfoCell r43 = new InfoCell(r25, r26);
        this.giftsLeftCell = r43;
        r43.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), getThemedColor(r5)));
        r43.titleView.setText(LocaleController.getString(R.string.Gift2AuctionBidInfoLeft));
        r23.addView(r3, LayoutHelper.createLinear(0, -1, 1.0f));
        r23.addView(new View(r25), LayoutHelper.createLinear(10, -1, 0.0f));
        r23.addView(r0, LayoutHelper.createLinear(0, -1, 1.0f));
        r23.addView(new View(r25), LayoutHelper.createLinear(10, -1, 0.0f));
        r23.addView(r43, LayoutHelper.createLinear(0, -1, 1.0f));
        r14.addView(r23, LayoutHelper.createLinear(-1, 56, 16.0f, 0.0f, 16.0f, 15.0f));
        if (r9.auctionUserState.acquired_count <= 0) goto L5;
        LinkSpanDrawable.LinksTextView r32 = new LinkSpanDrawable.LinksTextView(r25, r26);
        r32.setGravity(17);
        r32.setTextSize(1, 16.0f);
        int r02 = Theme.key_windowBackgroundWhiteLinkText;
        r32.setTextColor(Theme.getColor(r02, r26));
        r32.setLinkTextColor(Theme.getColor(r02, r26));
        r32.setOnClickListener(new AuctionBidSheet$$ExternalSyntheticLambda3(this, new boolean[1], r26));
        SpannableStringBuilder r03 = new SpannableStringBuilder("*");
        r03.setSpan(new AnimatedEmojiSpan(r9.giftDocumentId, r32.getPaint().getFontMetricsInt()), 0, r03.length(), 33);
        r32.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceArrows(LocaleController.formatPluralSpannable("Gift2AuctionsItemsBought2", r9.auctionUserState.acquired_count, new CharSequence[]{r03}), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f))}));
        ScaleStateListAnimator.apply(r32, 0.02f, 1.5f);
        r14.addView(r32, LayoutHelper.createLinear(-1, -2, 16.0f, 4.0f, 16.0f, 4.0f));
    L5:
        int r24 = Theme.key_windowBackgroundWhiteBlueHeader;
        HeaderCell r04 = new HeaderCell(r25, r24, 21, 0, 0, false, true, r26);
        this.selfBidderHeader = r04;
        r14.addView(r04, LayoutHelper.createLinear(-1, -2, 0.0f, 5.0f, 0.0f, 0.0f));
        AnimatedTextView r33 = new AnimatedTextView(r25);
        this.selfBidderFutureGift = r33;
        r33.setTextSize(AndroidUtilities.dp(12.5f));
        r33.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r33.setSizeableBackground(Theme.createRadSelectorDrawable(0, 0, 9, 9));
        r33.setHideBackgroundIfEmpty(true);
        r04.setOnWidthUpdateListener(new AuctionBidSheet$$ExternalSyntheticLambda4(this));
        r04.addView(r33, LayoutHelper.createFrame(-1, 17.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        BidderCell r05 = new BidderCell(r25, r26);
        this.selfBidderCell = r05;
        BidderCell.m14868$$Nest$fgetplaceTextView(r05).setTextColor(getThemedColor(r24));
        r05.setUser(r13, false);
        r14.addView(r05, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, -7.0f));
        HeaderCell r06 = new HeaderCell(r25, r24, 21, 15, 0, false, r26);
        r06.setText(LocaleController.getString(R.string.Gift2AuctionTop3Winners));
        r14.addView(r06, LayoutHelper.createLinear(-1, -2));
        int r07 = 0;
    L6:
        BidderCell[] r34 = this.topBidderCells;
        if (r07 >= r34.length) goto L13;
        r34[r07] = new BidderCell(r25, r26);
        int r44 = r07 + 1;
        this.topBidderCells[r07].setPlace(r44, true, false);
        this.topBidderCells[r07].setBackground(Theme.getSelectorDrawable(false));
        BidderCell r35 = this.topBidderCells[r07];
        if (r07 >= 2) goto L11;
        boolean r52 = true;
    L12:
        BidderCell.m14869$$Nest$fputdrawDivider(r35, r52);
        this.topBidderCells[r07].setOnClickListener(new AuctionBidSheet$$ExternalSyntheticLambda5());
        r14.addView(this.topBidderCells[r07], LayoutHelper.createLinear(-1, -2));
        r07 = r44;
        goto L6
    L11:
        r52 = false;
        goto L12
    L13:
        AnonymousClass2 r08 = new AnonymousClass2(this, r25, r26);
        this.buttonView = r08;
        r08.setRound();
        FrameLayout.LayoutParams r29 = LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 16.0f, 16.0f, 16.0f);
        int r36 = r29.leftMargin;
        int r45 = this.backgroundPaddingLeft;
        r29.leftMargin = r36 + r45;
        r29.rightMargin += r45;
        this.containerView.addView(r08, r29);
        RecyclerListView r09 = this.recyclerListView;
        int r210 = this.backgroundPaddingLeft;
        r09.setPadding(r210, 0, r210, AndroidUtilities.dp(64.0f));
        this.recyclerListView.setOnItemClickListener(new AuctionBidSheet$$ExternalSyntheticLambda6());
        long r211 = r9.auctionUserState.bid_amount;
        StarsReactionsSheet.StarsSlider r46 = this.slider;
        if (r211 <= 0) goto L16;
        r46.setValue((int) r211);
    L17:
        updateTable(false);
        this.recyclerListView.setOverScrollMode(2);
        BalanceCloud r010 = new BalanceCloud(r25, this.currentAccount, r26);
        this.balanceCloud = r010;
        r010.setScaleX(0.6f);
        r010.setScaleY(0.6f);
        r010.setAlpha(0.0f);
        r010.setEnabled(false);
        r010.setClickable(false);
        this.container.addView(r010, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 48.0f, 0.0f, 0.0f));
        ScaleStateListAnimator.apply(r010);
        r010.setOnClickListener(new AuctionBidSheet$$ExternalSyntheticLambda7(r25, r26));
        FrameLayout r011 = new FrameLayout(r25);
        this.bulletinContainer = r011;
        this.container.addView(r011, LayoutHelper.createFrame(-1, 100, 48));
        updateColors();
        this.adapter.update(false);
        return;
    L16:
        r46.setValue((int) r9.getMinimumBid());
        goto L17
    }

    private void checkAuctionParams() {
        long r0 = DialogObject.getPeerDialogId(this.auction.auctionUserState.peer);
        long r2 = this.auction.auctionUserState.acquired_count;
        if (this.lastAcquiredCount >= r2) goto L15;
        if (this.isFirstCheck == true) goto L15;
        BaseFragment r4 = LaunchActivity.getLastFragment();
        if (r4 == null) goto L15;
        long r7 = this.lastRecipientDialogId;
        if (r7 == 0) goto L15;
        ChatActivity r72 = ChatActivity.of(r7);
        Objects.requireNonNull(r72);
        r72.whenFullyVisible(new AuctionBidSheet$$ExternalSyntheticLambda10(r72));
        r4.presentFragment(r72);
        Runnable r42 = this.closeParentSheet;
        if (r42 == null) goto L13;
        r42.run();
    L13:
        dismiss();
    L15:
        if (r0 == 0) goto L17;
        this.lastRecipientDialogId = r0;
    L17:
        this.lastAcquiredCount = r2;
        this.isFirstCheck = false;
    }

    private void checkBalanceCloudVisibility() {
        if (this.isOpenAnimationEnd == true) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.balanceCloudVisible == r0) goto L25;
        this.balanceCloudVisible = r0;
        BalanceCloud r1 = this.balanceCloud;
        if (r1 == null) goto L26;
        r1.setEnabled(r0);
        this.balanceCloud.setClickable(r0);
        ViewPropertyAnimator r4 = this.balanceCloud.animate();
        float r12 = 0.6f;
        float r2 = 1.0f;
        if (r0 == false) goto L15;
        float r3 = 1.0f;
    L16:
        ViewPropertyAnimator r42 = r4.scaleX(r3);
        if (r0 == false) goto L19;
        r12 = 1.0f;
    L19:
        ViewPropertyAnimator r43 = r42.scaleY(r12);
        if (r0 == true) goto L23;
        r2 = 0.0f;
    L23:
        r43.alpha(r2).setDuration(180).start();
        return;
    L15:
        r3 = 0.6f;
        goto L16
    L26:
        return;
    L25:
        return;
    L5:
        if (isDismissed() == true) goto L7;
        r0 = true;
        goto L9
    }

    private void checkSliderSubText() {
        int r0 = this.slider.getValue();
        if (this.slider.getProgress() <= 0.99f) goto L6;
        this.slider.setCounterSubText(LocaleController.getString(R.string.Gift2AuctionTapToBidMore), true);
        return;
    L6:
        long r02 = r0;
        TL_stars.TL_StarGiftAuctionUserState r3 = this.auction.auctionUserState;
        long r4 = r3.bid_amount;
        if (r02 != r4) goto L11;
        this.slider.setCounterSubText(LocaleController.getString(R.string.Gift2AuctionYourBid), true);
        return;
    L11:
        if (r4 > 0) goto L13;
    L20:
        this.slider.setCounterSubText(null, true);
        return;
    L13:
        if (r3.returned == true) goto L20;
        long r03 = r02 - r4;
        StarsReactionsSheet.StarsSlider r10 = this.slider;
        if (r03 <= 0) goto L18;
        r10.setCounterSubText("+" + LocaleController.formatNumber(r03, ','), true);
        return;
    L18:
        r10.setCounterSubText(null, true);
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        r1.add(this.headerItem);
        r1.add(UItem.asSpace(AndroidUtilities.dp(16.0f)));
    }

    public static String formatDuration(long r2) {
        if (r2 < 3600) goto L7;
        return AndroidUtilities.formatFullDuration((int) r2);
    L7:
        return AndroidUtilities.formatDurationNoHours((int) r2, true);
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        this.slider.setValueAnimated((int) this.auction.getMinimumBid());
    }

    private /* synthetic */ void lambda$new$1(boolean[] r2, Theme.ResourcesProvider r3, List r4) {
        r2[0] = false;
        new AcquiredGiftsSheet(getContext(), r3, this.auction, r4).show();
    }

    private /* synthetic */ void lambda$new$2(boolean[] r4, Theme.ResourcesProvider r5, View r6) {
        if (r4[0] == false) goto L5;
        return;
    L5:
        r4[0] = true;
        GiftAuctionController.getInstance(this.currentAccount).getOrRequestAcquiredGifts(this.giftId, new AuctionBidSheet$$ExternalSyntheticLambda11(this, r4, r5));
    }

    private /* synthetic */ void lambda$new$3() {
        this.selfBidderFutureGift.setTranslationX(this.selfBidderHeader.getAnimatedWidth() + AndroidUtilities.dp(28.0f));
    }

    private /* synthetic */ void lambda$sendBid$10(long r5, Boolean r7, String r8) {
        this.buttonView.setLoading(false);
        this.bidIsPending = false;
        if (r7 != null) goto L5;
    L9:
        if (r8 == null) goto L12;
        updateBulletinContainerPosition();
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r8})).show();
        return;
    L12:
        return;
    L5:
        if (r5 <= 0) goto L7;
        boolean r52 = true;
    L8:
        showBidSuccessBulletin(r52);
        StarsController.getInstance(this.currentAccount).getBalance(false, null, true);
        goto L9
    L7:
        r52 = false;
        goto L8
    }

    private /* synthetic */ void lambda$showCustomPlaceABid$11(EditTextCaption r1, AlertDialog r2, int r3) {
        int r32 = Integer.parseInt(r1.getText().toString().trim());     // Catch: Throwable -> L6
        sendBid(r32);     // Catch: Throwable -> L6
        this.slider.setValue(r32);     // Catch: Throwable -> L6
        r2.dismiss();
        return;
    L6:
        th = move-exception;
        AndroidUtilities.shakeView(r1);
        FileLog.e(th);
    }

    private /* synthetic */ void lambda$updateButtonText$8(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$updateButtonText$9(View r4) {
        int r42 = this.slider.getValue();
        int r0 = (int) this.auction.getMinimumBid();
        if (r42 >= r0) goto L6;
        AndroidUtilities.shakeView(this.buttonView);
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletin(R.raw.info, AndroidUtilities.replaceTags(LocaleController.formatPluralString("Gift2AuctionMinimumBidIncreased", r0, new Object[0]))).show();
        return;
    L6:
        sendBid(r42);
    }

    private /* synthetic */ void lambda$updateTable$7(long r1, View r3) {
        openProfile(r1);
    }

    private void onColorFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        updateColors();
    }

    private void onSliderValueChanged(int r5) {
        this.slider.setColor(HighlightMessageSheet.getTierOption(this.currentAccount, r5, HighlightMessageSheet.TIER_COLOR1), HighlightMessageSheet.getTierOption(this.currentAccount, r5, HighlightMessageSheet.TIER_COLOR2), true);
        updateSelfBidderCell(this.isOpenAnimationEnd);
        updateSelfBidderHeader(this.isOpenAnimationEnd);
        updateButtonText(this.isOpenAnimationEnd);
        checkSliderSubText();
    }

    private void openProfile(long r7) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
    L14:
        Runnable r72 = this.closeParentSheet;
        if (r72 == null) goto L17;
        r72.run();
    L17:
        dismiss();
        return;
    L5:
        if (UserObject.isService(r7) == false) goto L7;
        return;
    L7:
        Bundle r1 = new Bundle();
        if (r7 <= 0) goto L12;
        r1.putLong("user_id", r7);
        if (r7 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L13;
        r1.putBoolean("my_profile", true);
    L13:
        r1.putBoolean("open_gifts", true);
        r0.presentFragment(new ProfileActivity(r1));
        goto L14
    L12:
        r1.putLong("chat_id", -r7);
        goto L13
    }

    private void sendBid(int r15) {
        if (this.bidIsPending == false) goto L5;
        return;
    L5:
        long r0 = this.auction.auctionUserState.bid_amount;
        if (r0 <= 0) goto L9;
        long r2 = r15 - r0;
    L8:
        long r7 = r2;
        if (StarsController.getInstance(this.currentAccount).balanceAvailable() == true) goto L13;
    L16:
        this.bidIsPending = true;
        this.buttonView.setLoading(true);
        GiftAuctionController.getInstance(this.currentAccount).sendBid(this.giftId, this.params, r15, new AuctionBidSheet$$ExternalSyntheticLambda14(this, r0));
        return;
    L13:
        if (StarsController.getInstance(this.currentAccount).getBalance(false) >= r7) goto L16;
        new StarsIntroActivity.StarsNeededSheet(getContext(), this.resourcesProvider, r7, 14, null, null, 0).show();
        return;
    L9:
        r2 = r15;
        goto L8
    }

    private void setSliderValues() {
        this.auction.getMinimumBid();
        this.auction.getCurrentMyBid();
        long r0 = this.auction.getCurrentTopBid();
        if (r0 <= 100000) goto L6;
        int r02 = ((((int) r0) * 3) / CastStatusCodes.AUTHENTICATION_FAILED) * 1000;
    L9:
        int[] r2 = {50, 100, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000, CastStatusCodes.AUTHENTICATION_FAILED, 5000, 7500, 10000, 25000, 50000, AppDataDirGuesser.PER_USER_RANGE, 500000, DurationKt.NANOS_IN_MILLIS, MediaController.VIDEO_BITRATE_480, 10000000};
        ArrayList r3 = new ArrayList();
        int r4 = 0;
        int r5 = 0;
        boolean r6 = false;
    L11:
        if (r5 >= 15) goto L23;
        int r9 = r2[r5];
        if (r9 >= 50) goto L15;
        r6 = true;
    L22:
        r5 = r5 + 1;
        goto L11
    L15:
        if (r9 != 50) goto L17;
        r6 = false;
    L17:
        if (r9 > r02) goto L18;
        r3.add(Integer.valueOf(r9));
        if (r2[r5] != r02) goto L22;
    L18:
        r3.add(Integer.valueOf(r02));
    L23:
        if (r6 == false) goto L26;
        r3.add(0, 50);
    L26:
        if (r3.size() >= 2) goto L28;
        r3.clear();
        r3.add(1);
        r3.add(10000);
    L28:
        int[] r03 = new int[r3.size()];
    L30:
        if (r4 >= r3.size()) goto L32;
        r03[r4] = ((Integer) r3.get(r4)).intValue();
        r4 = r4 + 1;
        goto L30
    L32:
        this.slider.setSteps(100, r03);
        return;
    L6:
        if (r0 <= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L8;
        r02 = AppDataDirGuesser.PER_USER_RANGE;
        goto L9
    L8:
        r02 = 50000;
        goto L9
    }

    private void showBidSuccessBulletin(boolean r4) {
        Bulletin.TwoLineLayout r0 = new Bulletin.TwoLineLayout(getContext(), this.resourcesProvider);
        r0.imageView.setImageResource(R.drawable.filled_gift_sell_24);
        TextView r1 = r0.titleTextView;
        if (r4 == false) goto L5;
        int r42 = R.string.Gift2AuctionsBidHasBeenIncreased;
    L6:
        r1.setText(LocaleController.getString(r42));
        r0.titleTextView.setSingleLine(true);
        r0.titleTextView.setTextSize(1, 15.0f);
        r0.titleTextView.setMaxLines(1);
        r0.titleTextView.setTypeface(AndroidUtilities.bold());
        r0.subtitleTextView.setText(LocaleController.formatString(R.string.Gift2AuctionPlaceACustomBidHint, new Object[]{Integer.valueOf(this.auction.gift.gifts_per_round)}));
        r0.subtitleTextView.setSingleLine(false);
        r0.subtitleTextView.setMaxLines(5);
        updateBulletinContainerPosition();
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).create(r0, 2750).show();
        return;
    L5:
        r42 = R.string.Gift2AuctionsBidHasBeenPlaced;
        goto L6
    }

    private void showCustomPlaceABid() {
        Context r1 = getContext();
        Activity r2 = AndroidUtilities.findActivity(r1);
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r2 == null) goto L5;
        r2.getCurrentFocus();
    L5:
        View[] r5 = new View[1];
        AlertDialog.Builder r6 = new AlertDialog.Builder(r1, this.resourcesProvider);
        r6.setTitle(LocaleController.getString(R.string.Gift2AuctionPlaceACustomBid));
        r6.setMessage(LocaleController.formatString(R.string.Gift2AuctionPlaceACustomBidHint, new Object[]{Integer.valueOf(this.auction.gift.gifts_per_round)}));
        AnonymousClass4 r8 = new AnonymousClass4(this, r1, this.resourcesProvider, r1.getResources().getDrawable(R.drawable.star_small_inner).mutate());
        r8.setTextSize(1, 18.0f);
        r8.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r8.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, this.resourcesProvider));
        r8.setHintText(LocaleController.getString(R.string.Gift2AuctionPlaceACustomBidHint2));
        r8.setFocusable(true);
        r8.setInputType(2);
        r8.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
        r8.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, this.resourcesProvider), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, this.resourcesProvider), Theme.getColor(Theme.key_text_RedRegular, this.resourcesProvider));
        r8.setImeOptions(268435462);
        r8.setBackgroundDrawable(null);
        r8.hintLayoutOffset = AndroidUtilities.dp(24.0f);
        r8.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        r8.addTextChangedListener(new AnonymousClass5(this, r5));
        LinearLayout r7 = new LinearLayout(r1);
        r7.setOrientation(1);
        r7.addView(r8, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        r6.makeCustomMaxHeight();
        r6.setView(r7);
        r6.setWidth(AndroidUtilities.dp(300.0f));
        r6.setPositiveButton(LocaleController.getString(R.string.Gift2AuctionPlaceABid), new AuctionBidSheet$$ExternalSyntheticLambda15(this, r8));
        r6.setNegativeButton(LocaleController.getString(R.string.Cancel), new AuctionBidSheet$$ExternalSyntheticLambda16());
        AlertDialog[] r0 = {r6.create()};
        if (r3 == null) goto L8;
        AndroidUtilities.requestAdjustNothing(r2, r3.getClassGuid());
    L8:
        r0[0].setOnDismissListener(new AuctionBidSheet$$ExternalSyntheticLambda17(r8, r3, r2));
        r0[0].setOnShowListener(new AuctionBidSheet$$ExternalSyntheticLambda18(r8));
        r0[0].show();
        View r12 = r0[0].getButton(-1);
        r5[0] = r12;
        r12.setAlpha(0.6f);
        r0[0].setDismissDialogByButtons(false);
        r8.setSelection(r8.getText().length());
    }

    private void updateBulletinContainerPosition() {
        if (this.shadowDrawable != null) goto L5;
        return;
    L5:
        if (this.containerView == null) goto L12;
        FrameLayout r1 = this.bulletinContainer;
        if (r1 == null) goto L13;
        r1.setTranslationY(Math.max(0.0f, ((r0.getBounds().top + this.containerView.getY()) - this.bulletinContainer.getMeasuredHeight()) + AndroidUtilities.dp(10.0f)));
        return;
    L13:
        return;
    }

    private void updateButtonText(boolean r8) {
        long r0 = this.slider.getValue();
        if (r0 != this.auction.getCurrentMyBid()) goto L6;
        this.buttonView.setText(LocaleController.getString(R.string.OK), r8);
        this.buttonView.setOnClickListener(new AuctionBidSheet$$ExternalSyntheticLambda12(this));
        return;
    L6:
        TL_stars.TL_StarGiftAuctionUserState r2 = this.auction.auctionUserState;
        long r3 = r2.bid_amount;
        if (r3 < r0) goto L9;
    L11:
        this.buttonView.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2AuctionPlaceBid, new Object[]{LocaleController.formatNumber(r0, ',')}), this.spanRefStars), r8);
    L12:
        this.buttonView.setOnClickListener(new AuctionBidSheet$$ExternalSyntheticLambda13(this));
        return;
    L9:
        if (r2.returned == true) goto L11;
        this.buttonView.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2AuctionPlaceBidAdd, new Object[]{LocaleController.formatNumber(r0 - r3, ',')}), this.spanRefStars), r8);
        goto L12
    }

    private void updateColors() {
        int r0 = ColorUtils.blendARGB(ColorUtils.blendARGB(getThemedColor(Theme.key_windowBackgroundWhiteBlueHeader), getThemedColor(Theme.key_text_RedBold), this.outbidColor.getFloatValue()), getThemedColor(Theme.key_color_green), this.winningColor.getFloatValue());
        this.selfBidderHeader.setTextColor(r0);
        this.selfBidderFutureGift.setTextColor(r0);
        BidderCell.m14868$$Nest$fgetplaceTextView(this.selfBidderCell).setTextColor(r0);
        if (Theme.setSelectorDrawableColor(this.selfBidderFutureGift.getSizeableBackground(), Theme.multAlpha(r0, 0.15f), false) == false) goto L6;
        this.selfBidderFutureGift.invalidate();
        return;
    }

    private void updateCountdownCell(long r2) {
        updateCountdownCell(r2, this.isOpenAnimationEnd);
    }

    private void updateCountdownCell(long r1, boolean r3) {
        this.nextRoundCell.infoView.setText(formatDuration(r1), r3);
    }

    private void updateSelfBidderCell(boolean r8) {
        long r0 = this.slider.getValue();
        int r2 = this.auction.getApproximatedMyPlace();
        int r3 = this.auction.approximatePlaceFromStars(r0);
        this.selfBidderCell.setBid(Math.max(r0, this.auction.getCurrentMyBid()), false);
        if (r2 <= 0) goto L5;
        r3 = Math.min(r2, r3);
    L5:
        this.selfBidderCell.setPlace(r3, false, r8);
        GiftAuctionController.Auction r82 = this.auction;
        if (r82.auctionStateActive == null) goto L18;
        if (r3 <= 0) goto L18;
        if (r82.gift.title == null) goto L18;
        if (r82.getBidStatus() != GiftAuctionController.Auction.BidStatus.WINNING) goto L18;
        if (this.auction.isUpcoming() == true) goto L18;
        GiftAuctionController.Auction r83 = this.auction;
        int r02 = r83.auctionStateActive.last_gift_num + r3;
        if (r02 > r83.gift.availability_total) goto L18;
        this.selfBidderFutureGift.setText(this.auction.gift.title + " #" + LocaleController.formatNumber(r02, ','));
        return;
    L18:
        this.selfBidderFutureGift.setText(null);
    }

    private void updateSelfBidderHeader(boolean r7) {
        GiftAuctionController.Auction.BidStatus r0 = this.auction.getBidStatus();
        boolean r2 = false;
        if (this.slider.getValue() <= this.auction.auctionUserState.bid_amount) goto L5;
        this.selfBidderHeader.setText(LocaleController.getString(R.string.Gift2AuctionBidStatusFuture), r7);
    L15:
        boolean r3 = false;
    L16:
        this.winningColor.setValue(r2, r7);
        this.outbidColor.setValue(r3, r7);
        return;
    L5:
        r3 = true;
        if (r0 != GiftAuctionController.Auction.BidStatus.OUTBID) goto L9;
        this.selfBidderHeader.setText(LocaleController.getString(R.string.Gift2AuctionBidStatusOutbid), r7);
        goto L16
    L9:
        if (r0 != GiftAuctionController.Auction.BidStatus.RETURNED) goto L11;
        this.selfBidderHeader.setText(LocaleController.getString(R.string.Gift2AuctionBidStatusOutbid), r7);
        goto L16
    L11:
        GiftAuctionController.Auction.BidStatus r1 = GiftAuctionController.Auction.BidStatus.WINNING;
        HeaderCell r4 = this.selfBidderHeader;
        if (r0 != r1) goto L14;
        r4.setText(LocaleController.getString(R.string.Gift2AuctionBidStatusWinning), r7);
        r3 = false;
        r2 = true;
        goto L16
    L14:
        r4.setText(LocaleController.getString(R.string.Gift2AuctionBidStatusFuture), r7);
        goto L15
    }

    private void updateTable(boolean r10) {
        this.minimumBidCell.infoView.setText(StarsIntroActivity.replaceStarsWithPlain("⭐️" + LocaleController.formatNumberWithMillion((int) this.auction.getMinimumBid(), ','), 0.78f, this.refS), r10);
        if (this.auction.auctionStateActive == null) goto L31;
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        boolean r2 = this.auction.isUpcoming(r0);
        GiftAuctionController.Auction r3 = this.auction;
        if (r2 == false) goto L7;
        long r5 = Math.max(0, r3.auctionStateActive.start_date - r0);
        this.timer.start(r5);
        updateCountdownCell(r5, r10);
    L9:
        if (this.animatedEmojiSpan == null) goto L11;
    L13:
        SpannableStringBuilder r02 = new SpannableStringBuilder();
        if (this.animatedEmojiSpan == null) goto L16;
        r02.append("* ");
        r02.setSpan(this.animatedEmojiSpan, 0, 1, 33);
    L16:
        r02.append(LocaleController.formatNumber(this.auction.auctionStateActive.gifts_left, ','));
        this.giftsLeftCell.infoView.setText(r02, r10);
        TextView r03 = this.nextRoundCell.titleView;
        if (this.auction.isUpcoming() == false) goto L19;
        int r22 = R.string.Gift2AuctionBidInfoUntilStart;
    L23:
        r03.setText(LocaleController.getString(r22));
        int r04 = Math.min(this.topBidderCells.length, this.auction.auctionStateActive.top_bidders.size());
        if (r04 <= 0) goto L31;
        int r23 = 0;
    L26:
        if (r23 >= r04) goto L31;
        int r32 = r23 + 1;
        Long r4 = this.auction.auctionStateActive.top_bidders.get(r23);
        long r52 = r4.longValue();
        TLRPC.User r42 = MessagesController.getInstance(this.currentAccount).getUser(r4);
        if (r42 == null) goto L30;
        this.topBidderCells[r23].setUser(r42, r10);
    L30:
        this.topBidderCells[r23].setBid(this.auction.approximateBidAmountFromPlace(r32), r10);
        this.topBidderCells[r23].setOnClickListener(new AuctionBidSheet$$ExternalSyntheticLambda8(this, r52));
        r23 = r32;
        goto L26
    L19:
        TL_stars.TL_starGiftAuctionState r24 = this.auction.auctionStateActive;
        if (r24.current_round != r24.total_rounds) goto L22;
        r22 = R.string.Gift2AuctionBidInfoUntilEndRound;
        goto L23
    L22:
        r22 = R.string.Gift2AuctionBidInfoUntilNextRound;
        goto L23
    L11:
        if (this.auction.gift.sticker == null) goto L13;
        this.animatedEmojiSpan = new AnimatedEmojiSpan(this.auction.gift.sticker.id, this.giftsLeftCell.infoView.getPaint().getFontMetricsInt());
        goto L13
    L7:
        long r53 = Math.max(0, r3.auctionStateActive.next_round_at - r0);
        this.timer.start(r53);
        updateCountdownCell(r53, r10);
    L31:
        StarsReactionsSheet.StarsSlider r05 = this.slider;
        GiftAuctionController.Auction r25 = this.auction;
        r05.setStarsTop(r25.approximateBidAmountFromPlace(r25.gift.gifts_per_round) + 1);
        this.slider.setTopText(LocaleController.formatPluralString("StarsReactionTopX", this.auction.gift.gifts_per_round, new Object[0]));
        updateSelfBidderCell(r10);
        updateSelfBidderHeader(r10);
        updateButtonText(r10);
        checkSliderSubText();
        checkAuctionParams();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new AuctionBidSheet$$ExternalSyntheticLambda9(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        GiftAuctionController.getInstance(this.currentAccount).unsubscribeFromGiftAuction(this.giftId, this);
        this.timer.stop();
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.Gift2AuctionPlaceABidTitle);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerTranslationYChanged(float r1) {
        super.onContainerTranslationYChanged(r1);
        checkBalanceCloudVisibility();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onDismissAnimationStart() {
        super.onDismissAnimationStart();
        this.isOpenAnimationEnd = false;
        checkBalanceCloudVisibility();
        Bulletin.removeDelegate(this.container);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        this.isOpenAnimationEnd = true;
        checkBalanceCloudVisibility();
        Bulletin.addDelegate(this.container, new AnonymousClass3(this));
    }

    @Override // org.telegram.messenger.GiftAuctionController.OnAuctionUpdateListener
    public void onUpdate(GiftAuctionController.Auction r1) {
        this.auction = r1;
        updateTable(this.isOpenAnimationEnd);
    }

    public void setCloseParentSheet(Runnable r1) {
        this.closeParentSheet = r1;
    }
}
