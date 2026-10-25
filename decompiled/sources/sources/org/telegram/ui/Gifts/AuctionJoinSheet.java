package org.telegram.ui.Gifts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.PremiumFeatureCell;
import org.telegram.ui.Stars.BagRandomizer;
import org.telegram.ui.Stars.StarGiftPreviewSheet;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public class AuctionJoinSheet extends BottomSheetWithRecyclerListView implements GiftAuctionController.OnAuctionUpdateListener {
    private static final ButtonSpan.TextViewButtons[] ref = null;
    private static final TableView.TableRowTitle[] ref2 = null;
    private UniversalAdapter adapter;
    private GiftAuctionController.Auction auction;
    private final ButtonSpan.TextViewButtons auctionRowAvailabilityText;
    private final TableView.TableRowTitle auctionRowAvailabilityTitle;
    private final TableRow auctionRowAveragePrice;
    private final ButtonSpan.TextViewButtons auctionRowAveragePriceText;
    private final ButtonSpan.TextViewButtons auctionRowEndTimeText;
    private final ButtonSpan.TextViewButtons auctionRowStartTimeText;
    private final ButtonWithCounterView buttonView;
    private final CharSequence emojiGiftText;
    private final long giftId;
    private final FrameLayout headerContainer;
    private TextView headerStatus;
    private final LinkSpanDrawable.LinksTextView itemsBought;
    private final LinearLayout linearLayout;
    private final Utilities.Callback2<View, CharSequence> showHint;
    private final TL_stars.StarGift starGift;
    private final LinkSpanDrawable.LinksTextView subtitleTextView;

    /* renamed from: org.telegram.ui.Gifts.AuctionJoinSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends GiftSheet.GiftCell {
        final /* synthetic */ AuctionJoinSheet this$0;

        public AnonymousClass1(AuctionJoinSheet r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionJoinSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends SendGiftSheet {
        final /* synthetic */ AuctionJoinSheet this$0;

        public AnonymousClass2(AuctionJoinSheet r1, Context r2, int r3, TL_stars.StarGift r4, long r5, Runnable r7, boolean r8, boolean r9) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r7, r8, r9);
        }

        @Override // org.telegram.ui.Gifts.SendGiftSheet
        public BulletinFactory getParentBulletinFactory() {
            return BulletinFactory.of(this.container, this.resourcesProvider);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionJoinSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends StarGiftSheet.TopView {
        Path path;
        float[] r;
        final /* synthetic */ AuctionJoinSheet this$0;

        public AnonymousClass3(AuctionJoinSheet r1, Context r2, Theme.ResourcesProvider r3, Runnable r4, View.OnClickListener r5, View.OnClickListener r6, View.OnClickListener r7, View.OnClickListener r8, View.OnClickListener r9, View.OnClickListener r10, View.OnClickListener r11) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
            this.path = new Path();
            this.r = new float[8];
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            r2.save();
            r2.clipPath(this.path);
            super.dispatchDraw(r2);
            r2.restore();
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public int getFinalHeight() {
            return AndroidUtilities.dp(288.0f);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public float getRealHeight() {
            return AndroidUtilities.dp(288.0f);
        }

        @Override // android.view.View
        public void onSizeChanged(int r8, int r9, int r10, int r11) {
            super.onSizeChanged(r8, r9, r10, r11);
            float[] r102 = this.r;
            float r112 = AndroidUtilities.dp(12.0f);
            r102[3] = r112;
            r102[2] = r112;
            r102[1] = r112;
            r102[0] = r112;
            this.path.rewind();
            this.path.addRoundRect(0.0f, 0.0f, r8, r9, this.r, Path.Direction.CW);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public void updateButtonsBackgrounds(int r3) {
            super.updateButtonsBackgrounds(r3);
            if (AuctionJoinSheet.m14880$$Nest$fgetheaderStatus(this.this$0) != null) goto L5;
            return;
        L5:
            if (Theme.setSelectorDrawableColor(AuctionJoinSheet.m14880$$Nest$fgetheaderStatus(this.this$0).getBackground(), r3, false) == false) goto L9;
            AuctionJoinSheet.m14880$$Nest$fgetheaderStatus(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionJoinSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ Context val$context;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ TL_stars.StarGift val$starGift;

        public AnonymousClass4(TL_stars.StarGift r1, Context r2, Theme.ResourcesProvider r3) {
            this.val$starGift = r1;
            this.val$context = r2;
            this.val$resourcesProvider = r3;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r10) {
            if (r10 != 3) goto L5;
        L11:
            String r5 = MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix + "/auction/" + this.val$starGift.auction_slug;
            if (r10 != 3) goto L15;
            AndroidUtilities.addToClipboard(r5);
            return;
        L15:
            ShareAlert.createShareAlert(this.val$context, null, r5, false, r5, false).show();
            return;
        L5:
            if (r10 == 2) goto L11;
            if (r10 != 4) goto L17;
            AuctionJoinSheet.showMoreInfo(this.val$context, this.val$resourcesProvider, this.val$starGift);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$1DUxjYFtE69U_qFQRtChVoxzGr8(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$2EVmfd1d_kak7CKnbJW35tZeKeA(AuctionJoinSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$6BJrnfi3ZJ3cJ-s3Bf_9BJtCOLU, reason: not valid java name */
    public static /* synthetic */ void m14870$r8$lambda$6BJrnfi3ZJ3cJs3Bf_9BJtCOLU(View r0) {
    }

    /* renamed from: $r8$lambda$97o8MJIdFx-c08aYc_50NzCZEyk, reason: not valid java name */
    public static /* synthetic */ void m14871$r8$lambda$97o8MJIdFxc08aYc_50NzCZEyk(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$CxxkC8uJiBttkJbJuTu75ujymws(AuctionJoinSheet r0, boolean[] r1, Theme.ResourcesProvider r2, List r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$JcX6kb84Lg-ijmzBt4tWat7zGg8, reason: not valid java name */
    public static /* synthetic */ void m14872$r8$lambda$JcX6kb84LgijmzBt4tWat7zGg8(AuctionJoinSheet r0, View r1) {
        r0.lambda$new$13(r1);
    }

    /* renamed from: $r8$lambda$T1f2CELWL8CscJ6oxS2W7b-6Vw8, reason: not valid java name */
    public static /* synthetic */ void m14873$r8$lambda$T1f2CELWL8CscJ6oxS2W7b6Vw8(AuctionJoinSheet r0, Context r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$THL9fkScNWoWK7QtHACEqU8ziSg(AuctionJoinSheet r0, HintView2[] r1, FrameLayout r2, View r3, CharSequence r4) {
        r0.lambda$new$2(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$XHNQ_i9eyMoDPYdmvZZ-O1OQw2k, reason: not valid java name */
    public static /* synthetic */ void m14874$r8$lambda$XHNQ_i9eyMoDPYdmvZZO1OQw2k(HintView2 r0) {
        AndroidUtilities.removeFromParent(r0);
    }

    /* renamed from: $r8$lambda$Y40U-MTaIQYWUknFAdPFz_UM8fk, reason: not valid java name */
    public static /* synthetic */ void m14875$r8$lambda$Y40UMTaIQYWUknFAdPFz_UM8fk(Context r0, Theme.ResourcesProvider r1, TL_stars.StarGift r2) {
        showMoreInfo(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YeUq2LReHHi1r1GAtZp0wUyzLtQ(View r0) {
    }

    /* renamed from: $r8$lambda$_AmuCjii1B-RD-D1h5poia39kI0, reason: not valid java name */
    public static /* synthetic */ void m14876$r8$lambda$_AmuCjii1BRDD1h5poia39kI0(View r0) {
    }

    /* renamed from: $r8$lambda$aZfOuhyyBKKVjOTt-D2MIxtFfgI, reason: not valid java name */
    public static /* synthetic */ void m14877$r8$lambda$aZfOuhyyBKKVjOTtD2MIxtFfgI(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$dDa2q_zbTHJj1NbnC5pex70NVCg(AuctionJoinSheet r0, long r1, Context r3, Theme.ResourcesProvider r4, Runnable r5, View r6) {
        r0.lambda$new$6(r1, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$e7cgPEaZBrYbkZddUVCopfrR498(AuctionJoinSheet r0) {
        r0.showAveragePriceHint();
    }

    public static /* synthetic */ void $r8$lambda$evhnpTfZlwrtcMSOiMiajabj5Lo(View r0) {
    }

    /* renamed from: $r8$lambda$hP_lRU-uYCaP75u8lhXvsZQZlQw, reason: not valid java name */
    public static /* synthetic */ void m14878$r8$lambda$hP_lRUuYCaP75u8lhXvsZQZlQw(Context r1, Theme.ResourcesProvider r2, int r3, long r4, Runnable r6, GiftAuctionController.Auction r7, TLRPC.TL_error r8) {
        if (r7 == null) goto L5;
        show(r1, r2, r3, r4, r7, r6);
        return;
    }

    /* renamed from: $r8$lambda$lOauVwazGWP_yO4vu_vKA-8mJfY, reason: not valid java name */
    public static /* synthetic */ void m14879$r8$lambda$lOauVwazGWP_yO4vu_vKA8mJfY(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$vREF91nUhRTAmgxZXIckpx3QXvQ(AuctionJoinSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$w0u5U47kKL8zQwoJQVMpiAV288A(Context r9, int r10, GiftAuctionController.Auction r11, long r12, Runnable r14) {
        new SendGiftSheet(r9, r10, r11.gift, r12, r14, false, false).show();
    }

    public static /* synthetic */ void $r8$lambda$wMaeh9StmFHYb6a_3WWJC8WmxWU(AuctionJoinSheet r0, boolean[] r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$5(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetheaderStatus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m14880$$Nest$fgetheaderStatus(AuctionJoinSheet r0) {
        return r0.headerStatus;
    }

    static {
        ref = new ButtonSpan.TextViewButtons[1];
        ref2 = new TableView.TableRowTitle[1];
    }

    private AuctionJoinSheet(Context r35, Theme.ResourcesProvider r36, long r37, TL_stars.StarGift r39, Runnable r40) {
        super(r35, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.FADING, r36);
        this.starGift = r39;
        long r11 = r39.id;
        this.giftId = r11;
        this.headerMoveTop = AndroidUtilities.dp(6.0f);
        this.topPadding = 0.2f;
        fixNavigationBar();
        String r0 = r39.title;
        if (r0 == null) goto L5;
    L4:
        String r13 = r0;
        LinearLayout r14 = new LinearLayout(r35);
        this.linearLayout = r14;
        r14.setOrientation(1);
        r14.setClipChildren(false);
        r14.setClipToPadding(false);
        r14.setClickable(true);
        ActionBar r1 = new ActionBar(r35, r36);
        r1.setItemsColor(-1, false);
        r1.setOccupyStatusBar(false);
        initActionBar(r1, r35, r36, this.currentAccount, r39);
        FrameLayout r3 = new FrameLayout(r35);
        this.headerContainer = r3;
        r3.addView(r1, LayoutHelper.createLinear(-1, -2));
        r14.addView(r3);
        AnonymousClass1 r02 = new AnonymousClass1(this, r35, this.currentAccount, r36);
        r02.setPriorityAuction();
        r02.setStarsGift(r39, false, false, false, false, false);
        r02.setImageSize(AndroidUtilities.dp(100.0f));
        r02.setImageLayer(7);
        r02.hidePrice();
        r3.addView(r02, LayoutHelper.createFrame(130, 130.0f, 17, 0.0f, 18.0f, 0.0f, 14.0f));
        TextView r9 = new TextView(r35);
        r9.setTypeface(AndroidUtilities.bold());
        r9.setGravity(17);
        r9.setText(r13);
        r9.setTextSize(1, 20.0f);
        int r32 = Theme.key_windowBackgroundWhiteBlackText;
        r9.setTextColor(Theme.getColor(r32, r36));
        r14.addView(r9, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 6));
        LinkSpanDrawable.LinksTextView r4 = new LinkSpanDrawable.LinksTextView(r35);
        this.subtitleTextView = r4;
        r4.setGravity(17);
        r4.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceTags(LocaleController.formatPluralString("Gift2AuctionInfo2", r39.gifts_per_round, new Object[]{r13})), " ", AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2AuctionInfoLearnMore), new AuctionJoinSheet$$ExternalSyntheticLambda3(r35, r36, r39)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f))}));
        r4.setTextSize(1, 14.0f);
        r4.setTextColor(Theme.getColor(r32, r36));
        int r12 = Theme.key_windowBackgroundWhiteLinkText;
        r4.setLinkTextColor(Theme.getColor(r12, r36));
        r14.addView(r4, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TableView r2 = new TableView(r35, r36);
        String r33 = LocaleController.getString(R.string.Gift2AuctionTableStarted);
        ButtonSpan.TextViewButtons[] r42 = ref;
        r2.addRow(r33, _UrlKt.FRAGMENT_ENCODE_SET, r42);
        this.auctionRowStartTimeText = r42[0];
        r2.addRow(LocaleController.getString(R.string.Gift2AuctionTableEnded), _UrlKt.FRAGMENT_ENCODE_SET, r42);
        this.auctionRowEndTimeText = r42[0];
        FrameLayout r34 = new FrameLayout(getContext());
        r34.setClipChildren(false);
        r34.setClipToPadding(false);
        r34.addView(r2, LayoutHelper.createFrame(-1, -2, 119));
        this.showHint = new AuctionJoinSheet$$ExternalSyntheticLambda8(this, new HintView2[1], r34);
        TableRow r6 = r2.addRow(LocaleController.getString(R.string.GiftValueAveragePrice), _UrlKt.FRAGMENT_ENCODE_SET, r42);
        this.auctionRowAveragePrice = r6;
        r6.setOnClickListener(new AuctionJoinSheet$$ExternalSyntheticLambda9(this));
        this.auctionRowAveragePriceText = r42[0];
        TableView.TableRowTitle[] r62 = ref2;
        r2.addRow(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, r62, r42);
        this.auctionRowAvailabilityText = r42[0];
        this.auctionRowAvailabilityTitle = r62[0];
        r14.addView(r34, LayoutHelper.createLinear(-1, -2, 16.0f, 16.0f, 14.0f, 18.0f));
        LinkSpanDrawable.LinksTextView r43 = new LinkSpanDrawable.LinksTextView(r35, r36);
        this.itemsBought = r43;
        r43.setGravity(17);
        r43.setTextSize(1, 16.0f);
        r43.setTextColor(Theme.getColor(r12, r36));
        r43.setLinkTextColor(Theme.getColor(r12, r36));
        r43.setOnClickListener(new AuctionJoinSheet$$ExternalSyntheticLambda10(this, new boolean[1], r36));
        ScaleStateListAnimator.apply(r43, 0.02f, 1.5f);
        if (r39.sticker == null) goto L9;
        SpannableStringBuilder r5 = new SpannableStringBuilder("*");
        r5.setSpan(new AnimatedEmojiSpan(r39.sticker, r43.getPaint().getFontMetricsInt()), 0, r5.length(), 33);
        this.emojiGiftText = r5;
    L10:
        ButtonWithCounterView r15 = new ButtonWithCounterView(r35, r36);
        this.buttonView = r15;
        r15.setRound();
        r15.setOnClickListener(new AuctionJoinSheet$$ExternalSyntheticLambda11(this, r37, r35, r36, r40));
        FrameLayout.LayoutParams r03 = LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 16.0f, 16.0f, 16.0f);
        int r22 = r03.leftMargin;
        int r38 = this.backgroundPaddingLeft;
        r03.leftMargin = r22 + r38;
        r03.rightMargin += r38;
        this.containerView.addView(r15, r03);
        RecyclerListView r04 = this.recyclerListView;
        int r23 = this.backgroundPaddingLeft;
        r04.setPadding(r23, 0, r23, AndroidUtilities.dp(64.0f));
        this.adapter.update(false);
        GiftAuctionController.Auction r05 = GiftAuctionController.getInstance(this.currentAccount).subscribeToGiftAuction(r11, this);
        this.auction = r05;
        if (r05 == null) goto L18;
        TL_stars.TL_starGiftAuctionState r06 = r05.auctionStateActive;
        if (r06 == null) goto L18;
        if (r06.start_date <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L17;
        r2.addRow(LocaleController.getString(R.string.Gift2AuctionTableCurrentRounds), LocaleController.formatNumber(this.auction.auctionStateActive.total_rounds, ','));
        goto L18
    L17:
        r2.addRow(LocaleController.getString(R.string.Gift2AuctionTableCurrentRound), LocaleController.formatString(R.string.OfS, new Object[]{LocaleController.formatNumber(this.auction.auctionStateActive.current_round, ','), LocaleController.formatNumber(this.auction.auctionStateActive.total_rounds, ',')}));
    L18:
        GiftAuctionController.Auction r07 = this.auction;
        if (r07 == null) goto L39;
        TL_stars.TL_starGiftAuctionState r08 = r07.auctionStateActive;
        if (r08 == null) goto L39;
        ArrayList<TL_stars.StarGiftAuctionRound> r09 = r08.rounds;
        if (r09 == null) goto L39;
        int r010 = r09.size();
        int r24 = 0;
    L25:
        if (r24 >= r010) goto L39;
        TL_stars.StarGiftAuctionRound r310 = this.auction.auctionStateActive.rounds.get(r24);
        int r44 = r010 - 1;
        GiftAuctionController.Auction r52 = this.auction;
        if (r24 >= r44) goto L29;
        int r45 = r52.auctionStateActive.rounds.get(r24 + 1).num - 1;
    L30:
        int r53 = r310.num;
        if (r53 != r45) goto L33;
        String r54 = LocaleController.formatString(R.string.Gift2AuctionTableCurrentRoundsOne, new Object[]{Integer.valueOf(r53)});
    L34:
        int r63 = r310.num;
        int r8 = r310.duration;
        if (r63 != r45) goto L37;
        String r311 = LocaleController.formatString(R.string.Gift2AuctionTableCurrentRoundsOneDuration, new Object[]{LocaleController.formatTTLString(r8), LocaleController.formatTTLString(r310.current_window), Integer.valueOf(r310.extend_top)});
    L38:
        r2.addRow(r54, r311);
        r24 = r24 + 1;
        goto L25
    L37:
        r311 = LocaleController.formatPluralString("Gift2AuctionTableCurrentRoundsTwoDuration", r8 / 60, new Object[0]);
        goto L38
    L33:
        r54 = LocaleController.formatString(R.string.Gift2AuctionTableCurrentRoundsTwo, new Object[]{Integer.valueOf(r53), Integer.valueOf(r45)});
        goto L34
    L29:
        r45 = r52.auctionStateActive.total_rounds;
    L39:
        GiftAuctionController.Auction r011 = this.auction;
        if (r011 != null) goto L42;
    L58:
        boolean r142 = false;
    L59:
        this.linearLayout.addView(this.itemsBought, LayoutHelper.createLinear(-1, -2, 16.0f, 0.0f, 14.0f, 18.0f));
        updateTable(r142);
        return;
    L42:
        if (r011.previewAttributes == null) goto L58;
        r142 = false;
        AnonymousClass3 r012 = new AnonymousClass3(this, r35, r36, new AuctionJoinSheet$$ExternalSyntheticLambda12(this), new AuctionJoinSheet$$ExternalSyntheticLambda13(), null, new AuctionJoinSheet$$ExternalSyntheticLambda14(), new AuctionJoinSheet$$ExternalSyntheticLambda15(), new AuctionJoinSheet$$ExternalSyntheticLambda16(), new AuctionJoinSheet$$ExternalSyntheticLambda4(), new AuctionJoinSheet$$ExternalSyntheticLambda5());
        r012.onSwitchPage(new StarGiftSheet.PageTransition(1, 1, 1.0f));
        r012.setPreviewingAttributes(this.auction.previewAttributes);
        r012.hideCloseButton();
        this.headerContainer.addView(r012, 0, LayoutHelper.createFrame(-1, 288, 48));
        TextView r013 = new TextView(r35);
        this.headerStatus = r013;
        r013.setGravity(17);
        this.headerStatus.setTypeface(AndroidUtilities.bold());
        this.headerStatus.setTextColor(-1);
        this.headerStatus.setTextSize(1, 12.0f);
        GiftAuctionController.Auction r014 = this.auction;
        if (r014.auctionStateFinished == null) goto L46;
        this.headerStatus.setText(LocaleController.getString(R.string.Gift2AuctionEndedNoDot));
    L50:
        this.headerStatus.setBackground(Theme.createRadSelectorDrawable(0, 285212671, 13, 13));
        this.headerStatus.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        this.headerContainer.addView(this.headerStatus, LayoutHelper.createFrame(-2, 26.0f, 81, 16.0f, 0.0f, 16.0f, 77.0f));
        TextView r015 = new TextView(r35);
        r015.setTypeface(AndroidUtilities.bold());
        r015.setTextSize(1, 21.0f);
        r015.setText(r13);
        r015.setGravity(17);
        r015.setTextColor(-1);
        this.headerContainer.addView(r015, LayoutHelper.createFrame(-1, -2.0f, 87, 16.0f, 0.0f, 16.0f, 40.0f));
        TextView r016 = new TextView(r35);
        r016.setTextSize(1, 13.0f);
        r016.setText(AndroidUtilities.replaceArrows(LocaleController.getString(R.string.Gift2AuctionLearnMore2), false, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)));
        r016.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r016.setGravity(17);
        r016.setTextColor(-1342177281);
        r016.setOnClickListener(new AuctionJoinSheet$$ExternalSyntheticLambda6(this));
        ScaleStateListAnimator.apply(r016, 0.02f, 1.5f);
        this.headerContainer.addView(r016, LayoutHelper.createFrame(-1, -2.0f, 87, 16.0f, 0.0f, 16.0f, 12.0f));
        r02.setVisibility(8);
        r9.setVisibility(8);
        this.subtitleTextView.setVisibility(8);
        LinkSpanDrawable.LinksTextView r017 = new LinkSpanDrawable.LinksTextView(r35, r36);
        r017.setGravity(17);
        r017.setTextSize(1, 16.0f);
        int r46 = Theme.key_windowBackgroundWhiteLinkText;
        r017.setTextColor(Theme.getColor(r46, r36));
        r017.setLinkTextColor(Theme.getColor(r46, r36));
        r017.setOnClickListener(new AuctionJoinSheet$$ExternalSyntheticLambda7(this, r35, r36));
        ScaleStateListAnimator.apply(r017, 0.02f, 1.5f);
        this.linearLayout.addView(r017, LayoutHelper.createLinear(-1, -2, 16.0f, 0.0f, 14.0f, 18.0f));
        BagRandomizer r25 = new BagRandomizer(TlUtils.findAllInstances(this.auction.previewAttributes, TL_stars.starGiftAttributeModel.class));
        long r312 = r39.upgrade_variants;
        SpannableStringBuilder r55 = new SpannableStringBuilder();
        int r64 = 0;
    L51:
        if (r64 >= 3) goto L57;
        TL_stars.starGiftAttributeModel r82 = (TL_stars.starGiftAttributeModel) r25.next();
        if (r82 == null) goto L56;
        r55.append('*');
        r55.setSpan(new AnimatedEmojiSpan(r82.document, r017.getPaint().getFontMetricsInt()), r64, r64 + 1, 33);
    L56:
        r64 = r64 + 1;
        goto L51
    L57:
        r017.setText(AndroidUtilities.replaceArrows(LocaleController.formatSpannable(R.string.Gift2AuctionVariants, new Object[]{r55, LocaleController.formatNumber(r312, ',')}), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)));
        goto L59
    L46:
        boolean r018 = r014.isUpcoming();
        TextView r47 = this.headerStatus;
        if (r018 == false) goto L49;
        r47.setText(LocaleController.getString(R.string.Gift2LinkUpcomingAuction));
        goto L50
    L49:
        r47.setText(LocaleController.getString(R.string.Gift2LinkGiftAuction));
        goto L50
    L9:
        this.emojiGiftText = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    L5:
        r0 = "Gift";
        goto L4
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        r1.add(UItem.asCustom(-1, this.linearLayout));
    }

    public static void initActionBar(ActionBar r0, Context r1, Theme.ResourcesProvider r2, int r3, TL_stars.StarGift r4) {
        r0.setActionBarMenuOnItemClick(new AnonymousClass4(r4, r1, r2));
        ActionBarMenuItem r02 = r0.createMenu().addItem(0, R.drawable.ic_ab_other);
        r02.setContentDescription(LocaleController.getString("AccDescrMoreOptions", R.string.AccDescrMoreOptions));
        r02.addSubItem(4, R.drawable.msg_info, LocaleController.getString(R.string.MoreInfo));
        r02.addSubItem(3, R.drawable.menu_feature_links, LocaleController.getString(R.string.CopyLink));
        r02.addSubItem(2, R.drawable.msg_share, LocaleController.getString(R.string.ShareLink));
    }

    private /* synthetic */ void lambda$new$13(View r4) {
        new PremiumFeatureBottomSheet(getContext(), 40, true, null).show();
    }

    private /* synthetic */ void lambda$new$14(Context r8, Theme.ResourcesProvider r9, View r10) {
        int r3 = this.currentAccount;
        GiftAuctionController.Auction r102 = this.auction;
        new StarGiftPreviewSheet(r8, r9, r3, r102.gift.title, r102.previewAttributes, false).show();
        dismiss();
    }

    private /* synthetic */ void lambda$new$2(HintView2[] r7, FrameLayout r8, View r9, CharSequence r10) {
        HintView2 r1 = r7[0];
        if (r1 == null) goto L5;
        r1.hide();
    L5:
        CharSequence r102 = AndroidUtilities.replaceTags(r10);
        float r12 = (r9.getX() + ((View) r9.getParent()).getX()) + ((View) ((View) r9.getParent()).getParent()).getX();
        float r2 = (r9.getY() + ((View) r9.getParent()).getY()) + ((View) ((View) r9.getParent()).getParent()).getY();
        if ((r9 instanceof ButtonSpan.TextViewButtons) == false) goto L14;
        Layout r92 = ((ButtonSpan.TextViewButtons) r9).getLayout();
        CharSequence r3 = r92.getText();
        if ((r3 instanceof Spanned) == false) goto L14;
        Spanned r4 = (Spanned) r3;
        ButtonSpan[] r32 = (ButtonSpan[]) r4.getSpans(0, r3.length(), ButtonSpan.class);
        if (r32.length <= 0) goto L14;
        ButtonSpan r5 = r32[0];
        if (r5 == null) goto L14;
        r12 = r12 + (r92.getPrimaryHorizontal(r4.getSpanStart(r5)) + (r32[0].getSize() / 2));
        r2 = r2 + r92.getLineTop(r92.getLineForOffset(r4));
    L14:
        HintView2 r93 = new HintView2(getContext(), 3);
        r7[0] = r93;
        r93.setMultilineText(true);
        r93.setInnerPadding(11.0f, 8.0f, 11.0f, 7.0f);
        r93.setRounding(10.0f);
        r93.setText(r102);
        r93.setOnHiddenListener(new AuctionJoinSheet$$ExternalSyntheticLambda20(r93));
        r93.setTranslationY((-AndroidUtilities.dp(100.0f)) + r2);
        r93.setMaxWidthPx(AndroidUtilities.dp(300.0f));
        r93.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r93.setJointPx(0.0f, r12 - AndroidUtilities.dp(4.0f));
        r8.addView(r93, LayoutHelper.createFrame(-1, 100, 55));
        r93.show();
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        showAveragePriceHint();
    }

    private /* synthetic */ void lambda$new$4(boolean[] r3, Theme.ResourcesProvider r4, List r5) {
        r3[0] = false;
        if (this.auction == null) goto L6;
        new AcquiredGiftsSheet(getContext(), r4, this.auction, r5).show();
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$new$5(boolean[] r4, Theme.ResourcesProvider r5, View r6) {
        if (r4[0] == false) goto L5;
        return;
    L5:
        r4[0] = true;
        GiftAuctionController.getInstance(this.currentAccount).getOrRequestAcquiredGifts(this.giftId, new AuctionJoinSheet$$ExternalSyntheticLambda21(this, r4, r5));
    }

    private /* synthetic */ void lambda$new$6(long r12, Context r14, Theme.ResourcesProvider r15, Runnable r16, View r17) {
        GiftAuctionController.Auction r0 = this.auction;
        if (r0 != null) goto L5;
    L14:
        dismiss();
        return;
    L5:
        if (r0.isFinished() == true) goto L14;
        if (r12 != 0) goto L9;
    L10:
        GiftAuctionController.Auction r02 = this.auction;
        ArrayList<TL_stars.StarGiftAttribute> r8 = r02.previewAttributes;
        if (r8 == null) goto L13;
        new AuctionWearingSheet(r14, r15, r12, r02.gift, r8, r16, false).show();
    L13:
        new AnonymousClass2(this, r14, this.currentAccount, this.auction.gift, r12, r16, false, false).show();
        goto L14
    L9:
        if (r12 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L13;
        goto L10
    }

    private static void openAuctionTransferAlert(Context r19, Theme.ResourcesProvider r20, int r21, long r22, long r24, Runnable r26) {
        if (r22 < 0) goto L5;
        TLObject r8 = MessagesController.getInstance(r21).getUser(Long.valueOf(r22));
    L7:
        if (r24 < 0) goto L9;
        TLObject r6 = MessagesController.getInstance(r21).getUser(Long.valueOf(r24));
    L10:
        LinearLayout r7 = new LinearLayout(r19);
        r7.setOrientation(1);
        r7.addView(new StarGiftSheet.UserToUserTransferTopView(r19, r8, r6), LayoutHelper.createLinear(-1, -2, 48, 0, -4, 0, 0));
        TextView r62 = new TextView(r19);
        NotificationCenter.listenEmojiLoading(r62);
        r62.setText(LocaleController.getString(R.string.Gift2AuctionsChangeRecipient));
        int r82 = Theme.key_dialogTextBlack;
        r62.setTextColor(Theme.getColor(r82, r20));
        r62.setTextSize(1, 20.0f);
        r62.setTypeface(AndroidUtilities.bold());
        int r11 = 3;
        if (LocaleController.isRTL == false) goto L13;
        int r10 = 5;
    L14:
        r62.setGravity(r10);
        if (LocaleController.isRTL == false) goto L17;
        r11 = 5;
    L17:
        r7.addView(r62, LayoutHelper.createFrame(-2, -2.0f, r11 | 48, 24.0f, 19.0f, 24.0f, 2.0f));
        TextView r63 = new TextView(r19);
        r63.setTextColor(Theme.getColor(r82, r20));
        r63.setTextSize(1, 16.0f);
        r63.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2AuctionsChangeRecipient2, new Object[]{DialogObject.getShortName(r22), DialogObject.getShortName(r24)})));
        r7.addView(r63, LayoutHelper.createLinear(-1, -2, 48, 24, 4, 24, 4));
        new AlertDialog.Builder(r19, r20).setView(r7).setPositiveButton(LocaleController.getString(R.string.Continue), new AuctionJoinSheet$$ExternalSyntheticLambda17(r26)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create().show();
        return;
    L13:
        r10 = 3;
        goto L14
    L9:
        r6 = MessagesController.getInstance(r21).getChat(Long.valueOf(-r24));
        goto L10
    L5:
        r8 = MessagesController.getInstance(r21).getChat(Long.valueOf(-r22));
        goto L7
    }

    public static void show(Context r8, Theme.ResourcesProvider r9, int r10, long r11, long r13, Runnable r15) {
        GiftAuctionController.getInstance(r10).getOrRequestAuction(r13, new AuctionJoinSheet$$ExternalSyntheticLambda0(r8, r9, r10, r11, r15));
    }

    private static void show(Context r18, Theme.ResourcesProvider r19, int r20, long r21, GiftAuctionController.Auction r23, Runnable r24) {
        if (r23 != null) goto L5;
        return;
    L5:
        long r0 = UserConfig.getInstance(r20).clientUserId;
        long r7 = DialogObject.getPeerDialogId(r23.auctionUserState.peer);
        if (r21 == r7) goto L14;
        if (r21 == 0) goto L14;
        if (r7 == 0) goto L14;
        openAuctionTransferAlert(r18, r19, r20, r7, r21, new AuctionJoinSheet$$ExternalSyntheticLambda1(r18, r20, r23, r21, r24));
        return;
    L14:
        if (r23.auctionUserState.bid_date > 0) goto L16;
    L19:
        new AuctionJoinSheet(r18, r19, r21, r23.gift, r24).show();
        return;
    L16:
        if (r23.isFinished() == true) goto L19;
        AuctionBidSheet r02 = new AuctionBidSheet(r18, r19, null, r23);
        r02.setCloseParentSheet(r24);
        r02.show();
    }

    private void showAveragePriceHint() {
        GiftAuctionController.Auction r0 = this.auction;
        if (r0 == null) goto L10;
        TL_stars.TL_starGiftAuctionStateFinished r1 = r0.auctionStateFinished;
        if (r1 != null) goto L7;
        return;
    L7:
        if (r0.gift.title == null) goto L12;
        this.showHint.run(this.auctionRowAveragePriceText, LocaleController.formatString(R.string.Gift2AveragePriceHint, new Object[]{Long.valueOf(r1.average_price), this.auction.gift.title}));
        return;
    L12:
        return;
    }

    public static void showMoreInfo(Context r19, Theme.ResourcesProvider r20, TL_stars.StarGift r21) {
        if (r19 == null) goto L8;
        if (r21 == null) goto L9;
        BottomSheet.Builder r3 = new BottomSheet.Builder(r19);
        Runnable r4 = r3.getDismissRunnable();
        LinearLayout r5 = new LinearLayout(r19);
        r5.setOrientation(1);
        r5.setClipChildren(false);
        r5.setClipToPadding(false);
        ImageView r8 = new ImageView(r19);
        r8.setPadding(AndroidUtilities.dp(17.0f), AndroidUtilities.dp(17.0f), AndroidUtilities.dp(17.0f), AndroidUtilities.dp(17.0f));
        r8.setImageResource(R.drawable.filled_gift_sell_24);
        ShapeDrawable r9 = new ShapeDrawable(new OvalShape());
        r9.getPaint().setColor(Theme.getColor(Theme.key_featuredStickers_addButton, r20));
        r8.setBackground(r9);
        r5.addView(r8, LayoutHelper.createLinear(80, 80, 17, 0, 21, 0, 16));
        TextView r82 = new TextView(r19);
        r82.setTypeface(AndroidUtilities.bold());
        r82.setGravity(17);
        r82.setText(LocaleController.getString(R.string.GiftAuctionInfoHeader));
        r82.setTextSize(1, 20.0f);
        int r10 = Theme.key_windowBackgroundWhiteBlackText;
        r82.setTextColor(Theme.getColor(r10, r20));
        r5.addView(r82, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 6));
        TextView r83 = new TextView(r19);
        r83.setGravity(17);
        r83.setText(LocaleController.getString(R.string.GiftAuctionInfoText));
        r83.setTextSize(1, 14.0f);
        r83.setTextColor(Theme.getColor(r10, r20));
        r5.addView(r83, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 16));
        PremiumFeatureCell r6 = new PremiumFeatureCell(r19, r20);
        SimpleTextView r84 = r6.title;
        int r92 = r21.gifts_per_round;
        r84.setText(LocaleController.formatPluralString("GiftAuctionInfo1Header", r92, new Object[]{Integer.valueOf(r92)}));
        TextView r85 = r6.description;
        int r93 = r21.gifts_per_round;
        r85.setText(LocaleController.formatPluralString("GiftAuctionInfo1Text", r93, new Object[]{Integer.valueOf(r93)}));
        r6.nextIcon.setVisibility(8);
        r6.imageView.setImageResource(R.drawable.menu_top_bidders_24);
        r6.imageView.setColorFilter(Theme.getColor(r10, r20));
        r5.addView(r6, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, -2.0f));
        PremiumFeatureCell r62 = new PremiumFeatureCell(r19, r20);
        r62.title.setText(LocaleController.getString(R.string.GiftAuctionInfo2Header));
        r62.description.setText(LocaleController.formatPluralString("GiftAuctionInfo2Text", r21.gifts_per_round, new Object[0]));
        r62.nextIcon.setVisibility(8);
        r62.imageView.setImageResource(R.drawable.menu_carryover_24);
        r62.imageView.setColorFilter(Theme.getColor(r10, r20));
        r5.addView(r62, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, -2.0f));
        PremiumFeatureCell r2 = new PremiumFeatureCell(r19, r20);
        r2.title.setText(LocaleController.getString(R.string.GiftAuctionInfo3Header));
        r2.description.setText(LocaleController.getString(R.string.GiftAuctionInfo3Text));
        r2.nextIcon.setVisibility(8);
        r2.imageView.setImageResource(R.drawable.menu_bid_refund_24);
        r2.imageView.setColorFilter(Theme.getColor(r10, r20));
        r5.addView(r2, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, 8.0f));
        ButtonWithCounterView r22 = new ButtonWithCounterView(r19, r20);
        r22.setOnClickListener(new AuctionJoinSheet$$ExternalSyntheticLambda2(r4));
        r22.setText(StarGiftSheet.replaceUnderstood(LocaleController.getString(R.string.Understood)), false);
        r5.addView(r22, LayoutHelper.createLinear(-1, 48, 16.0f, 10.0f, 16.0f, 8.0f));
        r3.setCustomView(r5);
        r3.show();
        return;
    L9:
        return;
    }

    private void updateTable(boolean r9) {
        GiftAuctionController.Auction r0 = this.auction;
        if (r0 != null) goto L5;
    L7:
        if (r0 != null) goto L9;
    L14:
        GiftAuctionController.Auction r02 = this.auction;
        if (r02 != null) goto L17;
    L22:
        int r03 = this.starGift.availability_remains;
    L23:
        int r4 = this.starGift.availability_total;
        TableView.TableRowTitle r5 = this.auctionRowAvailabilityTitle;
        if (r03 != r4) goto L26;
        r5.setText(LocaleController.getString(R.string.Gift2AuctionTableCurrentQuantity));
        this.auctionRowAvailabilityText.setText(LocaleController.formatNumber(r4, ','));
    L27:
        int r04 = this.auction.auctionUserState.acquired_count;
        LinkSpanDrawable.LinksTextView r1 = this.itemsBought;
        if (r04 <= 0) goto L30;
        r1.setVisibility(0);
        this.itemsBought.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceArrows(LocaleController.formatPluralSpannable("Gift2AuctionsItemsBought2", r04, new CharSequence[]{this.emojiGiftText}), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f))}));
    L31:
        GiftAuctionController.Auction r05 = this.auction;
        if (r05 == null) goto L36;
        if (r05.auctionStateFinished == null) goto L36;
    L37:
        this.subtitleTextView.setText(LocaleController.getString(R.string.Gift2AuctionEnded));
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
        this.auctionRowAveragePrice.setVisibility(0);
        this.buttonView.setText(LocaleController.getString(R.string.OK), r9);
        this.buttonView.setSubText(null, r9);
        return;
    L36:
        if (this.starGift.sold_out == true) goto L37;
        this.auctionRowAveragePrice.setVisibility(8);
        this.buttonView.setText(LocaleController.getString(R.string.Gift2AuctionJoin), r9);
        return;
    L30:
        r1.setVisibility(8);
        goto L31
    L26:
        r5.setText(LocaleController.getString(R.string.Gift2AuctionTableCurrentAvailability));
        this.auctionRowAvailabilityText.setText(LocaleController.formatPluralString("Gift2Availability4Value", r03, new Object[]{LocaleController.formatNumber(r4, ',')}));
        goto L27
    L17:
        if (r02.isFinished() == false) goto L19;
        r03 = 0;
        goto L23
    L19:
        TL_stars.TL_starGiftAuctionState r06 = this.auction.auctionStateActive;
        if (r06 == null) goto L22;
        r03 = r06.gifts_left;
        goto L23
    L9:
        if (r0.auctionStateActive == null) goto L14;
        this.auctionRowStartTimeText.setText(LocaleController.formatDateTime(r0.start_date, true));
        this.auctionRowEndTimeText.setText(LocaleController.formatDateTime(this.auction.auctionStateActive.end_date, true));
        int r07 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        boolean r3 = this.auction.isUpcoming(r07);
        GiftAuctionController.Auction r42 = this.auction;
        if (r3 == false) goto L13;
        this.buttonView.setSubText(LocaleController.formatString(R.string.Gift2AuctionStartsIn, new Object[]{LocaleController.formatTTLString(r42.auctionStateActive.start_date - r07)}), r9);
        goto L14
    L13:
        this.buttonView.setSubText(LocaleController.formatString(R.string.Gift2AuctionTimeLeft, new Object[]{LocaleController.formatTTLString(r42.auctionStateActive.end_date - r07)}), r9);
        goto L14
    L5:
        if (r0.auctionStateFinished == null) goto L7;
        this.auctionRowStartTimeText.setText(LocaleController.formatDateTime(r3.start_date, true));
        this.auctionRowEndTimeText.setText(LocaleController.formatDateTime(this.auction.auctionStateFinished.end_date, true));
        SpannableStringBuilder r08 = new SpannableStringBuilder(StarsIntroActivity.replaceStarsWithPlain("⭐️ " + LocaleController.formatNumber(this.auction.auctionStateFinished.average_price, ','), 0.8f));
        r08.append(" ").append(ButtonSpan.make("?", new AuctionJoinSheet$$ExternalSyntheticLambda18(this), this.resourcesProvider));
        this.auctionRowAveragePriceText.setText(r08);
        goto L14
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new AuctionJoinSheet$$ExternalSyntheticLambda19(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        GiftAuctionController.getInstance(this.currentAccount).unsubscribeFromGiftAuction(this.giftId, this);
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // org.telegram.messenger.GiftAuctionController.OnAuctionUpdateListener
    public void onUpdate(GiftAuctionController.Auction r1) {
        this.auction = r1;
        updateTable(true);
    }
}
