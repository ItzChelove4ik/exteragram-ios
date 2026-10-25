package org.telegram.ui.Gifts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Gifts.AuctionBidSheet;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.PremiumFeatureCell;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class AuctionWearingSheet extends BottomSheetWithRecyclerListView implements GiftAuctionController.OnAuctionUpdateListener {
    private UniversalAdapter adapter;
    private GiftAuctionController.Auction auction;
    private final ButtonWithCounterView buttonView;
    private final GiftSheet.GiftCell giftCell2;
    private final long giftId;
    private final TextView giftNameTextView;
    private final FrameLayout headerContainer;
    private final LinearLayout linearLayout;
    private final TL_stars.StarGift starGift;
    private final StarGiftSheet.TopView topView;

    /* renamed from: org.telegram.ui.Gifts.AuctionWearingSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        RectF rectF;
        RectF rectF2;
        final /* synthetic */ AuctionWearingSheet this$0;

        public AnonymousClass1(AuctionWearingSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rectF = new RectF();
            this.rectF2 = new RectF();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            super.dispatchDraw(r5);
            if (ViewPositionWatcher.computeRectInParent(AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).imageLayout, this, this.rectF) == true) goto L6;
            return;
        L6:
            if (ViewPositionWatcher.computeRectInParent(AuctionWearingSheet.m14884$$Nest$fgetgiftNameTextView(this.this$0), this, this.rectF2) == false) goto L14;
            float r0 = this.rectF2.right - AndroidUtilities.dp(32.0f);
            float r2 = this.rectF2.centerY() - AndroidUtilities.dp(16.0f);
            if (this.rectF.isEmpty() == true) goto L12;
            r5.save();
            r5.translate(r0, r2);
            r5.scale(AndroidUtilities.dp(32.0f) / this.rectF.width(), AndroidUtilities.dp(32.0f) / this.rectF.height());
            AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).imageLayout.draw(r5);
            r5.restore();
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionWearingSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends StarGiftSheet.TopView {
        Path path;
        float[] r;
        final /* synthetic */ AuctionWearingSheet this$0;
        final /* synthetic */ int val$topHeightDp;

        public AnonymousClass2(AuctionWearingSheet r1, Context r2, Theme.ResourcesProvider r3, Runnable r4, View.OnClickListener r5, View.OnClickListener r6, View.OnClickListener r7, View.OnClickListener r8, View.OnClickListener r9, View.OnClickListener r10, View.OnClickListener r11, int r12) {
            this.this$0 = r1;
            this.val$topHeightDp = r12;
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

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.imageLayout) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public int getFinalHeight() {
            return AndroidUtilities.dp(this.val$topHeightDp);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public float getRealHeight() {
            return AndroidUtilities.dp(this.val$topHeightDp);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (AuctionWearingSheet.m14883$$Nest$fgetgiftCell2(this.this$0) == null) goto L6;
            AuctionWearingSheet.m14883$$Nest$fgetgiftCell2(this.this$0).invalidate();
            return;
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
        public void updateButtonsBackgrounds(int r1) {
            super.updateButtonsBackgrounds(r1);
            AuctionWearingSheet.m14883$$Nest$fgetgiftCell2(this.this$0).setRibbonColor(r1);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionWearingSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends GiftSheet.GiftCell {
        final /* synthetic */ AuctionWearingSheet this$0;

        public AnonymousClass3(AuctionWearingSheet r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionWearingSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends GiftSheet.GiftCell {
        Path path;
        RectF rectF;
        RectF rectF2;
        final /* synthetic */ AuctionWearingSheet this$0;

        public AnonymousClass4(AuctionWearingSheet r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.rectF = new RectF();
            this.rectF2 = new RectF();
            this.path = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r13, View r14, long r15) {
            boolean r7 = super.drawChild(r13, r14, r15);
            if (r14 == this.card) goto L5;
        L13:
            return r7;
        L5:
            if (ViewPositionWatcher.computeRectInParent(AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).imageLayout, AuctionWearingSheet.m14885$$Nest$fgetheaderContainer(this.this$0), this.rectF) == true) goto L8;
            return true;
        L8:
            if (ViewPositionWatcher.computeRectInParent(this.card, this, this.rectF2) == true) goto L10;
            return true;
        L10:
            float r8 = this.rectF2.centerX() - AndroidUtilities.dp(40.0f);
            float r9 = this.rectF2.centerY() - AndroidUtilities.dp(40.0f);
            if (this.rectF.isEmpty() == true) goto L13;
            r13.save();
            r13.clipPath(this.path);
            r13.scale(0.6f, 0.6f, this.rectF2.centerX(), this.rectF2.centerY());
            r13.translate(this.rectF2.centerX() - (AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getWidth() / 2.0f), this.rectF2.centerY() - (AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getHeight() / 2.0f));
            AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).drawBackground(r13, AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getWidth() / 2.0f, AndroidUtilities.dp(104.0f), AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getWidth(), AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getHeight());
            AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).drawPattern(r13, AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getWidth() / 2.0f, AndroidUtilities.dp(104.0f), AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getWidth(), AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).getHeight());
            r13.restore();
            r13.save();
            r13.translate(r8, r9);
            r13.scale(AndroidUtilities.dp(80.0f) / this.rectF.width(), AndroidUtilities.dp(80.0f) / this.rectF.height());
            AuctionWearingSheet.m14886$$Nest$fgettopView(this.this$0).imageLayout.draw(r13);
            r13.restore();
            goto L13
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            this.path.rewind();
            this.rectF.set(0.0f, 0.0f, r1, r2);
            this.rectF.inset(AndroidUtilities.dp(3.33f), AndroidUtilities.dp(4.0f));
            this.path.addRoundRect(this.rectF, AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), Path.Direction.CW);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionWearingSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final /* synthetic */ AuctionWearingSheet this$0;
        final /* synthetic */ float val$limitedProgress;

        public AnonymousClass5(AuctionWearingSheet r1, Context r2, float r3) {
            this.this$0 = r1;
            this.val$limitedProgress = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) (View.MeasureSpec.getSize(r2) * this.val$limitedProgress), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.AuctionWearingSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends FrameLayout {
        final /* synthetic */ AuctionWearingSheet this$0;
        final /* synthetic */ float val$limitedProgress;

        public AnonymousClass6(AuctionWearingSheet r1, Context r2, float r3) {
            this.this$0 = r1;
            this.val$limitedProgress = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            r4.save();
            r4.clipRect(0.0f, 0.0f, getWidth() * this.val$limitedProgress, getHeight());
            super.dispatchDraw(r4);
            r4.restore();
        }
    }

    /* renamed from: $r8$lambda$-AX2U6PWOLa-fHwuoAwv5mgot2o, reason: not valid java name */
    public static /* synthetic */ void m14881$r8$lambda$AX2U6PWOLafHwuoAwv5mgot2o(Context r0, Theme.ResourcesProvider r1, TL_stars.StarGift r2) {
        AuctionJoinSheet.showMoreInfo(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1WzR89pQ8oRw_h_ADgB0pOZ6Yzg(Context r9, Theme.ResourcesProvider r10, long r11, TL_stars.StarGift r13, ArrayList r14, View r15) {
        new AuctionWearingSheet(r9, r10, r11, r13, r14, null, true).show();
    }

    public static /* synthetic */ void $r8$lambda$1dE0Ue2ZVkL_qNb0B3OVvB86LgY(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$8pqS2XKsbnveudOYL0RukDwECTY(View r0) {
    }

    /* renamed from: $r8$lambda$8vikXe8TUk-2ICKP9NCEvNWbGS0, reason: not valid java name */
    public static /* synthetic */ void m14882$r8$lambda$8vikXe8TUk2ICKP9NCEvNWbGS0(AuctionWearingSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AQeINEFzbZNcynMq9Ua49ICHRDY(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$QCBj6l67jWNMoq3_5EsJ0EYgUEU(AuctionWearingSheet r0, long r1, Context r3, Theme.ResourcesProvider r4, Runnable r5, View r6) {
        r0.lambda$new$9(r1, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$QgMvPEWw3PTzd_LyMdx3dKiSER8(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$tFJbMCLOH1FF87uxFHNVccD1TdI(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$wr3lR8TLA2959ZiJLZxJa26X7qA(AuctionWearingSheet r0, View r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$xpPhtK2alAo5v1jQQnMtJfcDSuw(View r0) {
    }

    /* renamed from: -$$Nest$fgetgiftCell2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GiftSheet.GiftCell m14883$$Nest$fgetgiftCell2(AuctionWearingSheet r0) {
        return r0.giftCell2;
    }

    /* renamed from: -$$Nest$fgetgiftNameTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m14884$$Nest$fgetgiftNameTextView(AuctionWearingSheet r0) {
        return r0.giftNameTextView;
    }

    /* renamed from: -$$Nest$fgetheaderContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m14885$$Nest$fgetheaderContainer(AuctionWearingSheet r0) {
        return r0.headerContainer;
    }

    /* renamed from: -$$Nest$fgettopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarGiftSheet.TopView m14886$$Nest$fgettopView(AuctionWearingSheet r0) {
        return r0.topView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AuctionWearingSheet(Context r35, Theme.ResourcesProvider r36, long r37, TL_stars.StarGift r39, ArrayList<TL_stars.StarGiftAttribute> r40, Runnable r41, boolean r42) {
        super(r35, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.FADING, r36);
        this.starGift = r39;
        long r3 = r39.id;
        this.giftId = r3;
        this.headerMoveTop = AndroidUtilities.dp(6.0f);
        this.topPadding = 0.2f;
        setBackgroundColor(getBackgroundColor());
        fixNavigationBar();
        LinearLayout r5 = new LinearLayout(r35);
        this.linearLayout = r5;
        r5.setOrientation(1);
        r5.setClipChildren(false);
        r5.setClipToPadding(false);
        r5.setClickable(true);
        AnonymousClass1 r9 = new AnonymousClass1(this, r35);
        this.headerContainer = r9;
        r5.addView(r9);
        ButtonWithCounterView r10 = new ButtonWithCounterView(r35, r36);
        this.buttonView = r10;
        r10.setRound();
        FrameLayout.LayoutParams r11 = LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 16.0f, 16.0f, 16.0f);
        int r12 = r11.leftMargin;
        int r0 = this.backgroundPaddingLeft;
        r11.leftMargin = r12 + r0;
        r11.rightMargin += r0;
        this.containerView.addView(r10, r11);
        RecyclerListView r02 = this.recyclerListView;
        int r112 = this.backgroundPaddingLeft;
        r02.setPadding(r112, 0, r112, AndroidUtilities.dp(64.0f));
        this.adapter.update(false);
        if (r42 == false) goto L6;
        int r03 = Opcodes.REM_INT_LIT8;
    L5:
        int r122 = r03;
        this.auction = GiftAuctionController.getInstance(this.currentAccount).subscribeToGiftAuction(r3, this);
        AnonymousClass2 r04 = new AnonymousClass2(this, r35, r36, new AuctionWearingSheet$$ExternalSyntheticLambda1(this), new AuctionWearingSheet$$ExternalSyntheticLambda3(), null, new AuctionWearingSheet$$ExternalSyntheticLambda4(), new AuctionWearingSheet$$ExternalSyntheticLambda5(), new AuctionWearingSheet$$ExternalSyntheticLambda6(), new AuctionWearingSheet$$ExternalSyntheticLambda7(), new AuctionWearingSheet$$ExternalSyntheticLambda8(), r122);
        this.topView = r04;
        r04.onSwitchPage(new StarGiftSheet.PageTransition(1, 1, 1.0f));
        r04.setPreviewingAttributes(r40);
        r04.hideCloseButton();
        r9.addView(r04, 0, LayoutHelper.createFrame(-1, r122, 48));
        BackupImageView r05 = new BackupImageView(r35);
        r05.setRoundRadius(AndroidUtilities.dp(45.0f));
        r9.addView(r05, LayoutHelper.createFrame(90, 90.0f, 49, 0.0f, 42.0f, 0.0f, 0.0f));
        if (r37 != 0) goto L11;
        TLRPC.User r32 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
        AvatarDrawable r4 = new AvatarDrawable(r32);
        TLRPC.Chat r33 = r32;
    L10:
        AvatarDrawable r102 = r4;
        long r43 = r37;
        TLRPC.Chat r34 = r33;
    L15:
        r05.setForUserOrChat(r34, r102);
        TextView r06 = new TextView(r35);
        this.giftNameTextView = r06;
        r06.setTypeface(AndroidUtilities.bold());
        r06.setTextSize(1, 21.0f);
        if (r37 == 0) goto L18;
        long r2 = r43;
    L19:
        r06.setText(DialogObject.getShortName(r2));
        r06.setGravity(17);
        r06.setTextColor(-1);
        r06.setPadding(0, 0, AndroidUtilities.dp(36.0f), 0);
        r06.setSingleLine();
        r06.setEllipsize(TextUtils.TruncateAt.END);
        r06.setMaxLines(1);
        r9.addView(r06, LayoutHelper.createFrame(-2, -2.0f, 81, 16.0f, 0.0f, 16.0f, 40.0f));
        TextView r113 = new TextView(r35);
        r113.setTextSize(1, 13.0f);
        r113.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r113.setGravity(17);
        r113.setTextColor(-1342177281);
        if (r42 == false) goto L22;
        r113.setText(LocaleController.getString(R.string.GiftAuctionWearInfoOnline));
        Context r07 = r35;
        Theme.ResourcesProvider r1 = r36;
    L23:
        r9.addView(r113, LayoutHelper.createFrame(-1, -2.0f, 87, 16.0f, 0.0f, 16.0f, 12.0f));
        LinearLayout r114 = new LinearLayout(r07);
        r114.setOrientation(0);
        r114.setGravity(17);
        r114.setClickable(true);
        AnonymousClass3 r22 = new AnonymousClass3(this, r07, this.currentAccount, r1);
        r22.setPriorityAuction();
        Theme.ResourcesProvider r92 = r1;
        r22.setStarsGift(r39, true, false, false, false, false);
        r22.setImageSize(AndroidUtilities.dp(84.0f));
        r22.setImageLayer(7);
        r22.hidePrice();
        r22.cardBackground.setStrokeColors(null);
        r22.setRibbonTextOneOf(this.auction.gift.availability_total);
        r114.addView(r22, LayoutHelper.createLinear(116, 116, 0.0f));
        ImageView r08 = new ImageView(r35);
        r08.setImageResource(R.drawable.ic_ab_back);
        r08.setScaleX(-1.0f);
        r08.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.SRC_IN));
        r114.addView(r08, LayoutHelper.createLinear(24, 24, 0.0f, 16, 12, 0, 12, 0));
        AnonymousClass4 r09 = new AnonymousClass4(this, r35, this.currentAccount, r92);
        this.giftCell2 = r09;
        r09.removeImage();
        r09.setPriorityAuction();
        r09.setStarsGift(r39, true, false, false, false, false);
        r09.setImageSize(AndroidUtilities.dp(100.0f));
        r09.setImageLayer(7);
        r09.hidePrice();
        r09.cardBackground.setStrokeColors(null);
        r09.setRibbonTextOneOf(this.auction.gift.availability_total);
        r09.setRibbonText(LocaleController.getString(R.string.Gift2AuctionUpgradedShort));
        r114.addView(r09, LayoutHelper.createLinear(116, 116, 0.0f));
        TextView r010 = new TextView(r35);
        r010.setTextSize(1, 13.0f);
        r010.setGravity(17);
        r010.setText(LocaleController.getString(R.string.Gift2WearingHint));
        int r23 = Theme.key_windowBackgroundWhiteGrayText;
        r010.setTextColor(getThemedColor(r23));
        float r38 = Utilities.clamp(r39.availability_remains / r39.availability_total, 1.0f, 0.0f);
        FrameLayout r44 = new FrameLayout(r35);
        int r6 = AndroidUtilities.dp(14.0f);
        int r8 = Theme.getColor(Theme.key_windowBackgroundWhite, r92);
        int r103 = Theme.key_windowBackgroundWhiteBlackText;
        r44.setBackground(Theme.createRoundRectDrawable(r6, ColorUtils.blendARGB(r8, Theme.getColor(r103, r92), 0.2f)));
        TextView r52 = new TextView(r35);
        r52.setTextSize(1, 13.0f);
        r52.setGravity(19);
        r52.setTypeface(AndroidUtilities.bold());
        r52.setTextColor(Theme.getColor(r103, r92));
        r52.setText(LocaleController.formatPluralStringComma("Gift2AvailabilityLeft", r39.availability_remains));
        r44.addView(r52, LayoutHelper.createFrame(-1, -1.0f, 3, 11.0f, 0.0f, 11.0f, 0.0f));
        TextView r53 = new TextView(r35);
        r53.setTextSize(1, 13.0f);
        r53.setGravity(21);
        r53.setTypeface(AndroidUtilities.bold());
        r53.setTextColor(Theme.getColor(r103, r92));
        r53.setText(LocaleController.formatPluralStringComma("Gift2AvailabilitySold", r39.availability_total - r39.availability_remains));
        r44.addView(r53, LayoutHelper.createFrame(-1, -1.0f, 5, 11.0f, 0.0f, 11.0f, 0.0f));
        View r54 = new AnonymousClass5(this, r35, r38);
        r54.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(14.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r92)));
        r44.addView(r54, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass6 r55 = new AnonymousClass6(this, r35, r38);
        r55.setWillNotDraw(false);
        r44.addView(r55, LayoutHelper.createFrame(-1, -1, 119));
        TextView r310 = new TextView(r35);
        r310.setTextSize(1, 13.0f);
        r310.setGravity(19);
        r310.setTypeface(AndroidUtilities.bold());
        r310.setTextColor(-1);
        r310.setText(LocaleController.formatPluralStringComma("Gift2AvailabilityLeft", r39.availability_remains));
        r55.addView(r310, LayoutHelper.createFrame(-1, -1.0f, 3, 11.0f, 0.0f, 11.0f, 0.0f));
        TextView r311 = new TextView(r35);
        r311.setTextSize(1, 13.0f);
        r311.setGravity(21);
        r311.setTypeface(AndroidUtilities.bold());
        r311.setTextColor(-1);
        r311.setText(LocaleController.formatPluralStringComma("Gift2AvailabilitySold", r39.availability_total - r39.availability_remains));
        r55.addView(r311, LayoutHelper.createFrame(-1, -1.0f, 5, 11.0f, 0.0f, 11.0f, 0.0f));
        GiftAuctionController.Auction r312 = this.auction;
        if (r312 != null) goto L26;
    L28:
        LinkSpanDrawable.LinksTextView r313 = null;
    L29:
        if (r42 == false) goto L31;
        showWearingMoreInfo(r35, r92, r5, r39);
        r10.setText(StarGiftSheet.replaceUnderstood(LocaleController.getString(R.string.Understood)), false);
        r10.setOnClickListener(new AuctionWearingSheet$$ExternalSyntheticLambda11(this));
        AuctionWearingSheet r13 = this;
        boolean r115 = false;
    L50:
        r13.updateTable(r115);
        return;
    L31:
        r5.addView(r114, LayoutHelper.createLinear(-1, -2, 0.0f, 20.0f, 0.0f, 10.0f));
        r5.addView(r010, LayoutHelper.createLinear(-1, -2, 40.0f, 0.0f, 40.0f, 15.0f));
        r5.addView(r44, LayoutHelper.createLinear(-1, 28, 14.0f, 18.0f, 14.0f, 10.0f));
        if (r313 == null) goto L34;
        r5.addView(r313, LayoutHelper.createLinear(-1, -2, 40.0f, 0.0f, 40.0f, 32.0f));
    L34:
        int r011 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        GiftAuctionController.Auction r14 = this.auction;
        if (r14 != null) goto L37;
    L39:
        r10.setText(LocaleController.getString(R.string.Gift2AuctionPlaceABid), false);
    L40:
        GiftAuctionController.Auction r15 = this.auction;
        if (r15 != null) goto L43;
    L48:
        r115 = false;
    L49:
        r13 = this;
        r10.setOnClickListener(new AuctionWearingSheet$$ExternalSyntheticLambda2(r13, r37, r35, r92, r41));
        goto L50
    L43:
        if (r15.auctionStateActive == null) goto L48;
        boolean r16 = r15.isUpcoming(r011);
        GiftAuctionController.Auction r24 = this.auction;
        if (r16 == false) goto L47;
        r115 = false;
        r10.setSubText(LocaleController.formatString(R.string.Gift2AuctionStartsIn, new Object[]{LocaleController.formatTTLString(r24.auctionStateActive.start_date - r011)}), false);
        goto L49
    L47:
        r115 = false;
        r10.setSubText(LocaleController.formatString(R.string.Gift2AuctionTimeLeft, new Object[]{LocaleController.formatTTLString(r24.auctionStateActive.end_date - r011)}), false);
        goto L49
    L37:
        if (r14.isUpcoming(r011) == false) goto L39;
        r10.setText(LocaleController.getString(R.string.Gift2AuctionPlaceAEarlyBid), false);
        goto L40
    L26:
        if (r312.auctionStateActive == null) goto L28;
        r313 = new LinkSpanDrawable.LinksTextView(r35);
        r313.setTextSize(1, 13.0f);
        r313.setGravity(17);
        r313.setTextColor(getThemedColor(r23));
        r313.setText(AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.Gift2AuctionInfo3, new Object[]{LocaleController.formatNumber(r39.availability_total, ','), Integer.valueOf(this.auction.auctionStateActive.total_rounds), Integer.valueOf(r39.gifts_per_round), AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2AuctionInfoLearnMore), new AuctionWearingSheet$$ExternalSyntheticLambda10(r35, r92, r39)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f))})));
        r313.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, r92));
        goto L29
    L22:
        r113.setText(AndroidUtilities.replaceArrows(LocaleController.getString(R.string.Gift2AuctionLearnMore3), false, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)));
        r07 = r35;
        r1 = r36;
        r113.setOnClickListener(new AuctionWearingSheet$$ExternalSyntheticLambda9(r35, r36, r43, r39, r40));
        ScaleStateListAnimator.apply(r113, 0.02f, 1.5f);
        goto L23
    L18:
        r2 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        goto L19
    L11:
        int r314 = this.currentAccount;
        if (r37 <= 0) goto L14;
        TLRPC.User r315 = MessagesController.getInstance(r314).getUser(Long.valueOf(r37));
        r4 = new AvatarDrawable(r315);
        r33 = r315;
        goto L10
    L14:
        r43 = r37;
        TLRPC.Chat r316 = MessagesController.getInstance(r314).getChat(Long.valueOf(-r43));
        r102 = new AvatarDrawable(r316);
        r34 = r316;
        goto L15
    L6:
        r03 = Opcodes.ADD_INT_LIT16;
        goto L5
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        r1.add(UItem.asCustom(-1, this.linearLayout));
    }

    private int getBackgroundColor() {
        return ColorUtils.blendARGB(getThemedColor(Theme.key_dialogBackgroundGray), getThemedColor(Theme.key_dialogBackground), 0.1f);
    }

    private /* synthetic */ void lambda$new$8(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$9(long r3, Context r5, Theme.ResourcesProvider r6, Runnable r7, View r8) {
        AuctionBidSheet r32 = new AuctionBidSheet(r5, r6, new AuctionBidSheet.Params(r3, true, null), this.auction);
        r32.show();
        r32.setCloseParentSheet(r7);
        dismiss();
    }

    private static void showWearingMoreInfo(Context r11, Theme.ResourcesProvider r12, LinearLayout r13, TL_stars.StarGift r14) {
        if (r11 == null) goto L7;
        if (r14 == null) goto L8;
        TextView r0 = new TextView(r11);
        r0.setTypeface(AndroidUtilities.bold());
        r0.setGravity(17);
        r0.setText(LocaleController.formatString(R.string.GiftAuctionWearInfoHeader, new Object[]{r14.title}));
        r0.setTextSize(1, 20.0f);
        int r142 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTextColor(Theme.getColor(r142, r12));
        r13.addView(r0, LayoutHelper.createLinear(-1, -2, 17, 20, 14, 20, 6));
        TextView r02 = new TextView(r11);
        r02.setGravity(17);
        r02.setText(LocaleController.getString(R.string.GiftAuctionWearInfoText));
        r02.setTextSize(1, 14.0f);
        r02.setTextColor(Theme.getColor(r142, r12));
        r13.addView(r02, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 16));
        PremiumFeatureCell r03 = new PremiumFeatureCell(r11, r12);
        r03.title.setText(LocaleController.getString(R.string.GiftAuctionWearInfo1Header));
        r03.description.setText(LocaleController.getString(R.string.GiftAuctionWearInfo1Text));
        r03.nextIcon.setVisibility(8);
        r03.imageView.setImageResource(R.drawable.msg_emoji_gem);
        r03.imageView.setColorFilter(Theme.getColor(r142, r12));
        r13.addView(r03, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, -2.0f));
        PremiumFeatureCell r04 = new PremiumFeatureCell(r11, r12);
        r04.title.setText(LocaleController.getString(R.string.GiftAuctionWearInfo2Header));
        r04.description.setText(LocaleController.getString(R.string.GiftAuctionWearInfo2Text));
        r04.nextIcon.setVisibility(8);
        r04.imageView.setImageResource(R.drawable.menu_feature_cover_24);
        r04.imageView.setColorFilter(Theme.getColor(r142, r12));
        r13.addView(r04, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, -2.0f));
        PremiumFeatureCell r05 = new PremiumFeatureCell(r11, r12);
        r05.title.setText(LocaleController.getString(R.string.GiftAuctionWearInfo3Header));
        r05.description.setText(LocaleController.getString(R.string.GiftAuctionWearInfo3Text));
        r05.nextIcon.setVisibility(8);
        r05.imageView.setImageResource(R.drawable.menu_verification);
        r05.imageView.setColorFilter(Theme.getColor(r142, r12));
        r13.addView(r05, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, 14.0f));
        return;
    L8:
        return;
    }

    private void updateTable(boolean r1) {
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new AuctionWearingSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
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
