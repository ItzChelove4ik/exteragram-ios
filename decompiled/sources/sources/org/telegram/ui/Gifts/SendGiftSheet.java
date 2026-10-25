package org.telegram.ui.Gifts;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.EditEmojiTextCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextSuggestionsFix;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.Premium.GiftPremiumBottomSheet$GiftTier;
import org.telegram.ui.Components.Premium.boosts.BoostDialogs;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.Premium.boosts.PremiumPreviewGiftSentBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProviderThemed;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Gifts.AuctionBidSheet;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes7.dex */
public class SendGiftSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate, GiftAuctionController.OnAuctionUpdateListener {
    private final TLRPC.MessageAction action;
    private final ChatActionCell actionCell;
    private UniversalAdapter adapter;
    public final AnimationNotificationsLocker animationsLock;
    public boolean anonymous;
    private GiftAuctionController.Auction auction;
    private final ButtonWithCounterView button;
    private final LinearLayout buttonContainer;
    private final ColoredImageSpan[] cachedStarSpan;
    private final LinearLayout chatLinearLayout;
    private final SizeNotifierFrameLayout chatView;
    private final Runnable closeParentSheet;
    private final int currentAccount;
    private final long dialogId;
    private final boolean forceNotUpgrade;
    private final boolean forceUpgrade;
    boolean isDismissed;
    private final TextView leftTextView;
    private final TextView leftTextView2;
    private final FrameLayout limitContainer;
    private final FrameLayout limitContainerWrapper;
    private final View limitProgressView;
    private EditEmojiTextCell messageEdit;
    private final MessageObject messageObject;
    private final String name;
    private final GiftPremiumBottomSheet$GiftTier premiumTier;
    private final boolean self;
    private final long send_paid_messages_stars;
    private int shakeDp;
    private final TextView soldTextView;
    private final TextView soldTextView2;
    private final TL_stars.StarGift starGift;
    public boolean upgrade;
    public boolean useStars;
    private final FrameLayout valueContainerView;

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements ChatActionCell.ChatActionCellDelegate {
        final /* synthetic */ SendGiftSheet this$0;

        public AnonymousClass1(SendGiftSheet r1) {
            this.this$0 = r1;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        int maxHeight;
        final /* synthetic */ SendGiftSheet this$0;

        public AnonymousClass2(SendGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.maxHeight = -1;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.backgroundView) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isStatusBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void onBackgroundViewInvalidate() {
            super.onBackgroundViewInvalidate();
            SendGiftSheet.access$000(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            SendGiftSheet.m15016$$Nest$fgetchatLinearLayout(this.this$0).setTranslationY(((r5 - r3) - SendGiftSheet.m15016$$Nest$fgetchatLinearLayout(this.this$0).getMeasuredHeight()) / 2.0f);
            SendGiftSheet.m15014$$Nest$fgetactionCell(this.this$0).setVisiblePart(SendGiftSheet.m15016$$Nest$fgetchatLinearLayout(this.this$0).getY() + SendGiftSheet.m15014$$Nest$fgetactionCell(this.this$0).getY(), getBackgroundSizeY());
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            if (this.maxHeight == (-1)) goto L7;
            super.onMeasure(r4, r5);
            int r0 = getMeasuredHeight();
            int r2 = this.maxHeight;
            if (r0 >= r2) goto L7;
            r5 = View.MeasureSpec.makeMeasureSpec(Math.max(r2, getMeasuredHeight()), Integer.MIN_VALUE);
        L7:
            super.onMeasure(r4, r5);
            int r42 = this.maxHeight;
            if (r42 != (-1)) goto L11;
            this.maxHeight = Math.max(r42, getMeasuredHeight());
            return;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean useRootView() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends EditEmojiTextCell {
        final /* synthetic */ SendGiftSheet this$0;
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ BlurredBackgroundDrawable val$msgDrawable;

        public AnonymousClass3(SendGiftSheet r1, Context r2, SizeNotifierFrameLayout r3, String r4, boolean r5, int r6, int r7, Theme.ResourcesProvider r8, BlurredBackgroundDrawable r9, int r10) {
            this.this$0 = r1;
            this.val$msgDrawable = r9;
            this.val$currentAccount = r10;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            this.val$msgDrawable.setBounds(AndroidUtilities.dp(10.0f), 0, getMeasuredWidth() - AndroidUtilities.dp(10.0f), getMeasuredHeight());
            this.val$msgDrawable.draw(r6);
            super.dispatchDraw(r6);
        }

        @Override // org.telegram.ui.Cells.EditEmojiTextCell
        public void onFocusChanged(boolean r1) {
        }

        @Override // org.telegram.ui.Cells.EditEmojiTextCell, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(12.0f), 0);
            super.onMeasure(r4, r5);
        }

        @Override // org.telegram.ui.Cells.EditEmojiTextCell
        public void onTextChanged(CharSequence r5) {
            boolean r52 = SendGiftSheet.m15013$$Nest$fgetaction(this.this$0) instanceof TLRPC.TL_messageActionStarGift;
            SendGiftSheet r0 = this.this$0;
            if (r52 == false) goto L5;
            TLRPC.TL_messageActionStarGift r53 = (TLRPC.TL_messageActionStarGift) SendGiftSheet.m15013$$Nest$fgetaction(r0);
            TLRPC.TL_textWithEntities r02 = new TLRPC.TL_textWithEntities();
            r53.message = r02;
        L11:
            CharSequence[] r2 = {SendGiftSheet.m15018$$Nest$fgetmessageEdit(this.this$0).getText()};
            r02.entities = MediaDataController.getInstance(this.val$currentAccount).getEntities(r2, true);
            r02.text = r2[0].toString();
            SendGiftSheet.m15019$$Nest$fgetmessageObject(this.this$0).setType();
            SendGiftSheet.m15014$$Nest$fgetactionCell(this.this$0).setMessageObject(SendGiftSheet.m15019$$Nest$fgetmessageObject(this.this$0), true);
            SendGiftSheet.m15015$$Nest$fgetadapter(this.this$0).update(true);
            SendGiftSheet.m15020$$Nest$msetButtonText(this.this$0, true);
            return;
        L5:
            boolean r54 = SendGiftSheet.m15013$$Nest$fgetaction(r0) instanceof TLRPC.TL_messageActionGiftCode;
            SendGiftSheet r03 = this.this$0;
            if (r54 == false) goto L9;
            ((TLRPC.TL_messageActionGiftCode) SendGiftSheet.m15013$$Nest$fgetaction(r03)).flags |= 16;
            TLRPC.TL_messageActionGiftCode r55 = (TLRPC.TL_messageActionGiftCode) SendGiftSheet.m15013$$Nest$fgetaction(this.this$0);
            r02 = new TLRPC.TL_textWithEntities();
            r55.message = r02;
            goto L11
        L9:
            if ((SendGiftSheet.m15013$$Nest$fgetaction(r03) instanceof TLRPC.TL_messageActionGiftPremium) == false) goto L13;
            ((TLRPC.TL_messageActionGiftPremium) SendGiftSheet.m15013$$Nest$fgetaction(this.this$0)).flags |= 16;
            TLRPC.TL_messageActionGiftPremium r56 = (TLRPC.TL_messageActionGiftPremium) SendGiftSheet.m15013$$Nest$fgetaction(this.this$0);
            r02 = new TLRPC.TL_textWithEntities();
            r56.message = r02;
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ SendGiftSheet this$0;

        public AnonymousClass4(SendGiftSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            return 0.3f;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final /* synthetic */ SendGiftSheet this$0;
        final /* synthetic */ float val$limitedProgress;
        final /* synthetic */ TL_stars.StarGift val$starGift;

        public AnonymousClass5(SendGiftSheet r1, Context r2, TL_stars.StarGift r3, float r4) {
            this.this$0 = r1;
            this.val$starGift = r3;
            this.val$limitedProgress = r4;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.val$starGift != null) goto L6;
            super.onMeasure(r2, r3);
            return;
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) (View.MeasureSpec.getSize(r2) * this.val$limitedProgress), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends FrameLayout {
        final /* synthetic */ SendGiftSheet this$0;
        final /* synthetic */ float val$limitedProgress;

        public AnonymousClass6(SendGiftSheet r1, Context r2, float r3) {
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

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerView.ItemDecoration {
        final PointF p;
        final /* synthetic */ SendGiftSheet this$0;

        public AnonymousClass7(SendGiftSheet r1) {
            this.this$0 = r1;
            this.p = new PointF();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas r8, RecyclerView r9, RecyclerView.State r10) {
            float r0 = r9.getHeight();
            if (ViewPositionWatcher.computeCoordinatesInParent(SendGiftSheet.m15017$$Nest$fgetchatView(this.this$0), SendGiftSheet.access$100(this.this$0), this.p) == false) goto L5;
            PointF r1 = this.p;
            float r3 = r1.x;
            r0 = Math.min(r0, r1.y);
            float r12 = Math.max(0.0f, this.p.y + SendGiftSheet.m15017$$Nest$fgetchatView(this.this$0).getMeasuredHeight());
        L7:
            if (ViewPositionWatcher.computeCoordinatesInParent(SendGiftSheet.m15018$$Nest$fgetmessageEdit(this.this$0), SendGiftSheet.access$200(this.this$0), this.p) == false) goto L10;
            r0 = Math.min(r0, this.p.y);
            r12 = Math.max(r12, (this.p.y + SendGiftSheet.m15018$$Nest$fgetmessageEdit(this.this$0).getMeasuredHeight()) + AndroidUtilities.dp(12.0f));
        L10:
            if (r0 < r12) goto L12;
        L14:
            super.onDraw(r8, r9, r10);
            return;
        L12:
            if (SendGiftSheet.m15017$$Nest$fgetchatView(this.this$0).backgroundView == null) goto L14;
            float r4 = (r12 - r0) / SendGiftSheet.m15017$$Nest$fgetchatView(this.this$0).backgroundView.getHeight();
            r8.save();
            r8.clipRect(0.0f, r0, r9.getWidth(), r12);
            r8.translate(r3, r0);
            r8.scale(r4, r4);
            SendGiftSheet.m15017$$Nest$fgetchatView(this.this$0).backgroundView.draw(r8);
            r8.restore();
            goto L14
        L5:
            r12 = 0.0f;
            r3 = 0.0f;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SendGiftSheet$8, reason: invalid class name */
    public class AnonymousClass8 extends BaseFragment {
        final /* synthetic */ SendGiftSheet this$0;

        public AnonymousClass8(SendGiftSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            Activity r0 = this.this$0.getOwnerActivity();
            if (r0 != null) goto L5;
            r0 = LaunchActivity.instance;
        L5:
            if (r0 == null) goto L7;
            return r0;
        L7:
            return AndroidUtilities.findActivity(this.this$0.getContext());
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return SendGiftSheet.access$300(this.this$0);
        }
    }

    /* renamed from: $r8$lambda$2zBivikTFH0Tps-adzTJqUukiFY, reason: not valid java name */
    public static /* synthetic */ void m15006$r8$lambda$2zBivikTFH0TpsadzTJqUukiFY(TLRPC.User r1) {
        PremiumPreviewGiftSentBottomSheet.show(new ArrayList(Arrays.asList(new TLRPC.User[]{r1})));
    }

    public static /* synthetic */ void $r8$lambda$6F1U0v3SpurRpJtHihd5cNOrqWA(TLRPC.User r1) {
        PremiumPreviewGiftSentBottomSheet.show(new ArrayList(Arrays.asList(new TLRPC.User[]{r1})));
    }

    public static /* synthetic */ void $r8$lambda$8jN8R2srEN9ZCvXS96IMEsWS_vY(SendGiftSheet r0, TLRPC.User r1, Boolean r2, String r3) {
        r0.lambda$buyPremiumTier$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$DIzWM4xxni3f72kfrPLRhJVQHIY(SendGiftSheet r0, TLRPC.TL_inputStorePaymentGiftPremium r1, TLRPC.TL_payments_canPurchaseStore r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$buyPremiumTier$14(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$DZyrL9mm-qxOqFEGsXAvmy4Ortw, reason: not valid java name */
    public static /* synthetic */ void m15007$r8$lambda$DZyrL9mmqxOqFEGsXAvmy4Ortw(SendGiftSheet r0, TLObject r1, TLRPC.TL_inputStorePaymentGiftPremium r2, TLRPC.TL_error r3, TLRPC.TL_payments_canPurchaseStore r4) {
        r0.lambda$buyPremiumTier$13(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$INoYMfj8xtXLPGGHzLKuji_YbE4(SendGiftSheet r0) {
        r0.lambda$fillItems$16();
    }

    public static /* synthetic */ void $r8$lambda$VLDOR2CooumIq64W5Iw3DsQRBKA(SendGiftSheet r0) {
        r0.lambda$buyPremiumTier$11();
    }

    public static /* synthetic */ void $r8$lambda$WFdPXVM8SpXttp1vVHv9mS1Qdog(SendGiftSheet r0, BillingResult r1) {
        r0.lambda$buyPremiumTier$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$WXnnKbXtJ5No01yU4pBYxP6tUJI(SendGiftSheet r0, TLRPC.User r1, Boolean r2, String r3) {
        r0.lambda$buyPremiumTier$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$_OS_WpnAorRBSULMerfnx6ah09g(SendGiftSheet r0) {
        r0.lambda$fillItems$17();
    }

    public static /* synthetic */ void $r8$lambda$eFTxJugFLYZ91NC5skaqoUy0cYY(SendGiftSheet r0, TLRPC.TL_error r1) {
        r0.lambda$buyPremiumTier$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$hhZapE_wkSSfFAu9voC4AhZnbns(SendGiftSheet r0, long r1, Context r3, Runnable r4, TL_stars.StarGift r5, View r6) {
        r0.lambda$new$0(r1, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$kAw-qgzVFc0Z6U0vJ2PVIBaue5w, reason: not valid java name */
    public static /* synthetic */ void m15008$r8$lambda$kAwqgzVFc0Z6U0vJ2PVIBaue5w(SendGiftSheet r0, TLRPC.User r1, Void r2) {
        r0.lambda$buyPremiumTier$6(r1, r2);
    }

    /* renamed from: $r8$lambda$m7YCEy9ymLbMFThT-WfgzSR-ATg, reason: not valid java name */
    public static /* synthetic */ void m15009$r8$lambda$m7YCEy9ymLbMFThTWfgzSRATg(SendGiftSheet r0) {
        r0.lambda$buyPremiumTier$10();
    }

    /* renamed from: $r8$lambda$nO_St4H3ycYEYJSRUna1kTt0-l4, reason: not valid java name */
    public static /* synthetic */ void m15010$r8$lambda$nO_St4H3ycYEYJSRUna1kTt0l4(SendGiftSheet r0, boolean r1, boolean r2, TL_stars.StarGift r3, GiftPremiumBottomSheet$GiftTier r4, View r5, int r6) {
        r0.lambda$new$1(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$oqjwRp0kzVPvmZhYawyZq7q74-A, reason: not valid java name */
    public static /* synthetic */ void m15011$r8$lambda$oqjwRp0kzVPvmZhYawyZq7q74A(TLRPC.User r1) {
        PremiumPreviewGiftSentBottomSheet.show(new ArrayList(Arrays.asList(new TLRPC.User[]{r1})));
    }

    /* renamed from: $r8$lambda$sZ19KyvZJ4AQxucausXFeI-meRM, reason: not valid java name */
    public static /* synthetic */ void m15012$r8$lambda$sZ19KyvZJ4AQxucausXFeImeRM(SendGiftSheet r0, Boolean r1, String r2) {
        r0.lambda$buyStarGift$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wZ82lIOaUzHFOE8TFP3RqzDRKk4(SendGiftSheet r0) {
        r0.lambda$fillItems$15();
    }

    /* renamed from: -$$Nest$fgetaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.MessageAction m15013$$Nest$fgetaction(SendGiftSheet r0) {
        return r0.action;
    }

    /* renamed from: -$$Nest$fgetactionCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActionCell m15014$$Nest$fgetactionCell(SendGiftSheet r0) {
        return r0.actionCell;
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m15015$$Nest$fgetadapter(SendGiftSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetchatLinearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m15016$$Nest$fgetchatLinearLayout(SendGiftSheet r0) {
        return r0.chatLinearLayout;
    }

    /* renamed from: -$$Nest$fgetchatView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SizeNotifierFrameLayout m15017$$Nest$fgetchatView(SendGiftSheet r0) {
        return r0.chatView;
    }

    /* renamed from: -$$Nest$fgetmessageEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditEmojiTextCell m15018$$Nest$fgetmessageEdit(SendGiftSheet r0) {
        return r0.messageEdit;
    }

    /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m15019$$Nest$fgetmessageObject(SendGiftSheet r0) {
        return r0.messageObject;
    }

    /* renamed from: -$$Nest$msetButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15020$$Nest$msetButtonText(SendGiftSheet r0, boolean r1) {
        r0.setButtonText(r1);
    }

    public SendGiftSheet(Context r11, int r12, TL_stars.StarGift r13, long r14, Runnable r16, boolean r17, boolean r18) {
        this(r11, r12, r13, null, r14, r16, r17, r18);
    }

    private SendGiftSheet(Context r32, int r33, TL_stars.StarGift r34, GiftPremiumBottomSheet$GiftTier r35, long r36, Runnable r38, boolean r39, boolean r40) {
        super(r32, null, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, null);
        this.upgrade = false;
        this.useStars = false;
        this.shakeDp = -2;
        this.animationsLock = new AnimationNotificationsLocker();
        this.cachedStarSpan = new ColoredImageSpan[1];
        this.isDismissed = false;
        if (r36 != UserConfig.getInstance(r33).getClientUserId()) goto L5;
        boolean r5 = true;
    L6:
        this.self = r5;
        setImageReceiverNumLevel(0, 4);
        fixNavigationBar();
        this.headerPaddingTop = AndroidUtilities.dp(4.0f);
        this.headerPaddingBottom = AndroidUtilities.dp(-10.0f);
        if (r5 == false) goto L9;
        this.anonymous = true;
    L9:
        this.currentAccount = r33;
        this.dialogId = r36;
        this.starGift = r34;
        if (r34 != null) goto L12;
    L14:
        float r17 = 4.0f;
    L15:
        this.premiumTier = r35;
        this.closeParentSheet = r38;
        this.forceUpgrade = r39;
        this.forceNotUpgrade = r40;
        if (r39 == false) goto L18;
        this.upgrade = true;
    L20:
        this.topPadding = 0.2f;
        if (r36 < 0) goto L23;
        long r19 = 0;
        this.name = UserObject.getForcedFirstName(MessagesController.getInstance(r33).getUser(Long.valueOf(r36)));
    L28:
        ChatActionCell r3 = new ChatActionCell(r32, false, this.resourcesProvider);
        this.actionCell = r3;
        r3.setDelegate(new AnonymousClass1(this));
        AnonymousClass2 r7 = new AnonymousClass2(this, r32);
        this.chatView = r7;
        boolean r21 = r5;
        Drawable r8 = PreviewView.getBackgroundDrawable(null, r33, r36, Theme.isCurrentThemeDark());
        r7.setBackgroundImage(r8, false);
        BlurredBackgroundSourceColor r52 = new BlurredBackgroundSourceColor();
        if ((r8 instanceof ColorDrawable) == false) goto L32;
        Integer r0 = Integer.valueOf(((ColorDrawable) r8).getColor());
    L42:
        if (r0 == null) goto L44;
        int r02 = r0.intValue();
    L45:
        r52.setColor(r02);
        BlurredBackgroundDrawable r03 = r52.createDrawable();
        Theme.ResourcesProvider r82 = this.resourcesProvider;
        int r4 = Theme.key_dialogBackground;
        r03.setColorProvider(new BlurredBackgroundColorProviderThemed(r82, r4));
        r03.setRadius(AndroidUtilities.dp(20.0f));
        r03.setPadding(AndroidUtilities.dp(r17));
        LinearLayout r53 = new LinearLayout(r32);
        this.chatLinearLayout = r53;
        r53.setOrientation(1);
        if (r34 == null) goto L48;
        TLRPC.TL_messageActionStarGift r83 = new TLRPC.TL_messageActionStarGift();
        r83.gift = r34;
        BlurredBackgroundDrawable r172 = r03;
        r83.flags |= 2;
        r83.message = new TLRPC.TL_textWithEntities();
        r83.convert_stars = r34.convert_stars;
        r83.forceIn = true;
        this.action = r83;
        AnonymousClass2 r16 = r7;
        int r04 = r4;
        LinearLayout r9 = r53;
    L66:
        TLRPC.MessageAction r42 = this.action;
        if ((r42 instanceof TLRPC.TL_messageActionStarGift) == false) goto L87;
        TLRPC.TL_messageActionStarGift r43 = (TLRPC.TL_messageActionStarGift) r42;
        boolean r54 = this.upgrade;
        if (r54 == true) goto L76;
        if (r21 == false) goto L75;
        if (r34 == null) goto L75;
        if (r34.can_upgrade == true) goto L76;
    L75:
        boolean r6 = false;
    L77:
        r43.can_upgrade = r6;
        if (r21 == false) goto L80;
    L79:
        long r62 = r19;
    L82:
        r43.upgrade_stars = r62;
        if (r54 == false) goto L85;
        long r55 = r19;
    L86:
        r43.convert_stars = r55;
        goto L87
    L85:
        r55 = r34.convert_stars;
        goto L86
    L80:
        if (r54 == false) goto L79;
        r62 = r34.upgrade_stars;
    L76:
        r6 = true;
    L87:
        TLRPC.TL_messageService r44 = new TLRPC.TL_messageService();
        r44.id = 1;
        r44.dialog_id = r36;
        r44.from_id = MessagesController.getInstance(r33).getPeer(UserConfig.getInstance(r33).getClientUserId());
        r44.peer_id = MessagesController.getInstance(r33).getPeer(r36);
        r44.action = this.action;
        if (r34 == null) goto L90;
        long r56 = MessagesController.getInstance(r33).getSendPaidMessagesStars(r36);
    L91:
        this.send_paid_messages_stars = r56;
        MessageObject r72 = new MessageObject(r33, r44, false, false);
        this.messageObject = r72;
        r3.setMessageObject(r72, true);
        if (r56 <= r19) goto L94;
        int r27 = 0;
    L95:
        LinearLayout r63 = r9;
        r63.addView(r3, LayoutHelper.createLinear(-1, -1, 119, 0, r27, 0, 8));
        r16.addView(r63, LayoutHelper.createFrame(-1, -1, 119));
        int r64 = r04;
        SizeNotifierFrameLayout r73 = (SizeNotifierFrameLayout) this.containerView;
        if (r34 == null) goto L98;
        int r92 = R.string.Gift2Message;
    L99:
        AnonymousClass3 r05 = new AnonymousClass3(this, r32, r73, LocaleController.getString(r92), true, MessagesController.getInstance(r33).stargiftsMessageLengthMax, 4, this.resourcesProvider, r172, r33);
        this.messageEdit = r05;
        r05.editTextEmoji.getEditText().addTextChangedListener(new EditTextSuggestionsFix());
        this.messageEdit.editTextEmoji.allowEmojisForNonPremium(true, false);
        this.messageEdit.setShowLimitWhenNear(50);
        setEditTextEmoji(this.messageEdit.editTextEmoji);
        this.messageEdit.setShowLimitOnFocus(true);
        this.messageEdit.setDivider(false);
        this.messageEdit.hideKeyboardOnEnter();
        EditEmojiTextCell r06 = this.messageEdit;
        int r37 = this.backgroundPaddingLeft;
        r06.setPadding(r37, 0, r37, 0);
        AnonymousClass4 r07 = new AnonymousClass4(this);
        r07.setDelayAnimations(false);
        r07.setSupportsChangeAnimations(false);
        r07.setDurations(350);
        r07.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r07.setDelayIncrement(40);
        this.recyclerListView.setItemAnimator(r07);
        this.adapter.update(false);
        LinearLayout r08 = new LinearLayout(r32);
        this.buttonContainer = r08;
        r08.setOrientation(1);
        r08.setBackgroundColor(Theme.getColor(r64, this.resourcesProvider));
        int r310 = this.backgroundPaddingLeft;
        r08.setPadding(r310, 0, r310, 0);
        this.containerView.addView(r08, LayoutHelper.createFrame(-1, -2, 87));
        View r311 = new View(r32);
        r311.setBackgroundColor(Theme.getColor(Theme.key_dialogGrayLine, this.resourcesProvider));
        r08.addView(r311, LayoutHelper.createLinear(-1.0f, 1.0f / AndroidUtilities.density, 55));
        if (r34 != null) goto L102;
        float r45 = 0.0f;
    L103:
        float r312 = Utilities.clamp(r45, 1.0f, 0.0f);
        FrameLayout r46 = new FrameLayout(r32);
        this.limitContainer = r46;
        r46.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider)));
        if (r34 != null) goto L106;
    L108:
        if (r34 != null) goto L110;
    L112:
        int r65 = 8;
    L113:
        r46.setVisibility(r65);
        r08.addView(r46, LayoutHelper.createLinear(-1, 30, 10.0f, 10.0f, 10.0f, 0.0f));
        this.limitContainerWrapper = null;
    L114:
        TextView r66 = new TextView(r32);
        this.leftTextView = r66;
        r66.setTextSize(1, 13.0f);
        r66.setGravity(19);
        r66.setTypeface(AndroidUtilities.bold());
        int r10 = Theme.key_windowBackgroundWhiteBlackText;
        r66.setTextColor(Theme.getColor(r10, this.resourcesProvider));
        if (r34 == null) goto L117;
        r66.setText(LocaleController.formatPluralStringComma("Gift2AvailabilityLeft", r34.availability_remains));
    L117:
        r46.addView(r66, LayoutHelper.createFrame(-1, -1.0f, 3, 11.0f, 0.0f, 11.0f, 0.0f));
        TextView r67 = new TextView(r32);
        this.soldTextView = r67;
        r67.setTextSize(1, 13.0f);
        r67.setGravity(21);
        r67.setTypeface(AndroidUtilities.bold());
        r67.setTextColor(Theme.getColor(r10, this.resourcesProvider));
        if (r34 == null) goto L120;
        r67.setText(LocaleController.formatPluralStringComma("Gift2AvailabilitySold", r34.availability_total - r34.availability_remains));
    L120:
        r46.addView(r67, LayoutHelper.createFrame(-1, -1.0f, 5, 11.0f, 0.0f, 11.0f, 0.0f));
        View r68 = new AnonymousClass5(this, r32, r34, r312);
        this.limitProgressView = r68;
        r68.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
        r46.addView(r68, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass6 r69 = new AnonymousClass6(this, r32, r312);
        this.valueContainerView = r69;
        r69.setWillNotDraw(false);
        r46.addView(r69, LayoutHelper.createFrame(-1, -1, 119));
        TextView r313 = new TextView(r32);
        this.leftTextView2 = r313;
        r313.setTextSize(1, 13.0f);
        r313.setGravity(19);
        r313.setTypeface(AndroidUtilities.bold());
        r313.setTextColor(-1);
        if (r34 == null) goto L123;
        r313.setText(LocaleController.formatPluralStringComma("Gift2AvailabilityLeft", r34.availability_remains));
    L123:
        r69.addView(r313, LayoutHelper.createFrame(-1, -1.0f, 3, 11.0f, 0.0f, 11.0f, 0.0f));
        TextView r314 = new TextView(r32);
        this.soldTextView2 = r314;
        r314.setTextSize(1, 13.0f);
        r314.setGravity(21);
        r314.setTypeface(AndroidUtilities.bold());
        r314.setTextColor(-1);
        if (r34 == null) goto L126;
        r314.setText(LocaleController.formatPluralStringComma("Gift2AvailabilitySold", r34.availability_total - r34.availability_remains));
    L126:
        r69.addView(r314, LayoutHelper.createFrame(-1, -1.0f, 5, 11.0f, 0.0f, 11.0f, 0.0f));
        ButtonWithCounterView r74 = new ButtonWithCounterView(r32, this.resourcesProvider);
        this.button = r74;
        r74.setRound();
        setButtonText(false);
        r08.addView(r74, LayoutHelper.createLinear(-1, 48, 119, 10, 10, 10, 10));
        r74.setOnClickListener(new SendGiftSheet$$ExternalSyntheticLambda1(this, r36, r32, r38, r34));
        LinearLayoutManager r09 = this.layoutManager;
        this.reverseLayout = true;
        r09.setReverseLayout(true);
        this.adapter.update(false);
        this.layoutManager.scrollToPositionWithOffset(this.adapter.getItemCount(), AndroidUtilities.dp(200.0f));
        RecyclerListView r010 = this.recyclerListView;
        int r2 = this.backgroundPaddingLeft;
        if (r34 != null) goto L129;
    L133:
        int r315 = 0;
    L134:
        r010.setPadding(r2, 0, r2, AndroidUtilities.dp(68 + r315));
        this.recyclerListView.addItemDecoration(new AnonymousClass7(this));
        this.recyclerListView.setOnItemClickListener(new SendGiftSheet$$ExternalSyntheticLambda2(this, r39, r40, r34, r35));
        this.actionBar.setTitle(getTitle());
        return;
    L129:
        if (r34.limited == false) goto L133;
        if (this.limitContainerWrapper != null) goto L133;
        r315 = 40;
        goto L134
    L110:
        if (r34.limited == false) goto L112;
        r65 = 0;
        goto L113
    L106:
        if (r34.auction == false) goto L108;
        FrameLayout r84 = new FrameLayout(r32);
        this.limitContainerWrapper = r84;
        r84.addView(r46, LayoutHelper.createLinear(-1, 30, 10.0f, 14.0f, 10.0f, 14.0f));
        r84.setBackgroundColor(Theme.getColor(r64, this.resourcesProvider));
        goto L114
    L102:
        r45 = r34.availability_remains / r34.availability_total;
        goto L103
    L98:
        r92 = R.string.Gift2MessageOptional;
        goto L99
    L94:
        r27 = 8;
        goto L95
    L90:
        r56 = r19;
        goto L91
    L48:
        r172 = r03;
        if (r35 != null) goto L51;
    L57:
        r16 = r7;
        r04 = r4;
        if (r35 != null) goto L60;
    L136:
        GlShader$$ExternalSyntheticBUOutline1.m("SendGiftSheet with no star gift and no premium tier");
        throw null;
    L60:
        if (r35.giftOption == null) goto L136;
        TLRPC.TL_messageActionGiftPremium r47 = new TLRPC.TL_messageActionGiftPremium();
        r47.months = r35.getMonths();
        r47.currency = r35.getCurrency();
        long r75 = r35.getPrice();
        r47.amount = r75;
        if (r35.googlePlayProductDetails == null) goto L64;
        r9 = r53;
        r47.amount = (long) (r75 * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r47.currency) - 6));
    L65:
        r47.flags |= 2;
        r47.message = new TLRPC.TL_textWithEntities();
        this.action = r47;
        goto L66
    L64:
        r9 = r53;
        goto L65
    L51:
        if (r35.giftCodeOption == null) goto L57;
        TLRPC.TL_messageActionGiftCode r76 = new TLRPC.TL_messageActionGiftCode();
        r76.unclaimed = true;
        r76.via_giveaway = false;
        r76.months = r35.getMonths();
        r76.flags |= 4;
        r76.currency = r35.getCurrency();
        long r85 = r35.getPrice();
        r76.amount = r85;
        if (r35.googlePlayProductDetails == null) goto L55;
        r16 = r7;
        r04 = r4;
        LinearLayout r610 = r53;
        r76.amount = (long) (r85 * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r76.currency) - 6));
    L56:
        r76.flags |= 16;
        r76.message = new TLRPC.TL_textWithEntities();
        this.action = r76;
        r9 = r610;
        goto L66
    L55:
        r16 = r7;
        r04 = r4;
        r610 = r53;
        goto L56
    L44:
        r02 = getThemedColor(Theme.key_dialogBackground);
        goto L45
    L32:
        if ((r8 instanceof MotionBackgroundDrawable) == false) goto L41;
        MotionBackgroundDrawable r86 = (MotionBackgroundDrawable) r8;
        if (r86.getIntensity() >= 0) goto L36;
        r0 = -16777216;
        goto L42
    L36:
        int[] r011 = r86.getColors();
        if (r011 == null) goto L41;
        if (r011.length <= 0) goto L41;
        r0 = Integer.valueOf(r011[0]);
    L41:
        r0 = null;
        goto L42
    L23:
        r19 = 0;
        TLRPC.Chat r316 = MessagesController.getInstance(r33).getChat(Long.valueOf(-r36));
        if (r316 != null) goto L26;
        String r317 = _UrlKt.FRAGMENT_ENCODE_SET;
    L27:
        this.name = r317;
        goto L28
    L26:
        r317 = r316.title;
        goto L27
    L18:
        if (r40 == false) goto L20;
        this.upgrade = false;
        goto L20
    L12:
        if (r34.auction == false) goto L14;
        r17 = 4.0f;
        this.auction = GiftAuctionController.getInstance(r33).subscribeToGiftAuction(r34.id, this);
        goto L15
    L5:
        r5 = false;
        goto L6
    }

    public SendGiftSheet(Context r11, int r12, GiftPremiumBottomSheet$GiftTier r13, long r14, Runnable r16) {
        this(r11, r12, null, r13, r14, r16, false, false);
    }

    public static /* synthetic */ RecyclerListView access$000(SendGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ RecyclerListView access$100(SendGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ RecyclerListView access$200(SendGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(SendGiftSheet r0) {
        return r0.resourcesProvider;
    }

    private void buyPremiumTier() {
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        if (r0 != null) goto L7;
        this.button.setLoading(false);
        return;
    L7:
        if (this.useStars == true) goto L9;
    L11:
        GiftPremiumBottomSheet$GiftTier r2 = this.premiumTier;
        TLRPC.TL_premiumGiftCodeOption r3 = r2.giftCodeOption;
        if (r3 == null) goto L14;
        Object r1 = r3;
    L18:
        if ((r1 instanceof TLRPC.TL_premiumGiftCodeOption) == false) goto L26;
        TLRPC.TL_premiumGiftCodeOption r5 = (TLRPC.TL_premiumGiftCodeOption) r1;
        if ("XTR".equalsIgnoreCase(r5.currency) == false) goto L23;
        StarsController.getInstance(this.currentAccount).buyPremiumGift(this.dialogId, r5, getMessage(), new SendGiftSheet$$ExternalSyntheticLambda7(this, r0));
        return;
    L23:
        BoostRepository.payGiftCode(new ArrayList(Arrays.asList(new TLRPC.User[]{r0})), r5, null, getMessage(), new AnonymousClass8(this), new SendGiftSheet$$ExternalSyntheticLambda8(this, r0), new SendGiftSheet$$ExternalSyntheticLambda9(this));
        return;
    L26:
        if ((r1 instanceof TLRPC.TL_premiumGiftOption) == false) goto L53;
        TLRPC.TL_premiumGiftOption r7 = (TLRPC.TL_premiumGiftOption) r1;
        if ("XTR".equalsIgnoreCase(r7.currency) == false) goto L32;
        StarsController.getInstance(this.currentAccount).buyPremiumGift(this.dialogId, r7, getMessage(), new SendGiftSheet$$ExternalSyntheticLambda10(this, r0));
        return;
    L32:
        if (BuildVars.useInvoiceBilling() == false) goto L46;
        LaunchActivity r02 = LaunchActivity.instance;
        if (r02 == null) goto L54;
        Uri r12 = Uri.parse(r7.bot_url);
        if (r12.getHost().equals("t.me") == true) goto L38;
    L43:
        Browser.openUrl(r02, this.premiumTier.giftOption.bot_url);
        dismiss();
        return;
    L38:
        if (r12.getPath().startsWith("/$") == false) goto L40;
    L42:
        r02.setNavigateToPremiumGiftCallback(new SendGiftSheet$$ExternalSyntheticLambda11(this));
        goto L43
    L40:
        if (r12.getPath().startsWith("/invoice/") == true) goto L42;
        r02.setNavigateToPremiumBot(true);
        goto L43
    L54:
        return;
    L46:
        if (BillingController.getInstance().isReady() == true) goto L48;
        return;
    L48:
        if (this.premiumTier.googlePlayProductDetails == null) goto L56;
        TLRPC.TL_inputStorePaymentGiftPremium r13 = new TLRPC.TL_inputStorePaymentGiftPremium();
        r13.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r0);
        r13.currency = this.premiumTier.googlePlayProductDetails.getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
        r13.amount = (long) ((r0.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r13.currency)));
        BillingController.getInstance().addResultListener(this.premiumTier.giftOption.store_product, new SendGiftSheet$$ExternalSyntheticLambda12(this));
        TLRPC.TL_payments_canPurchaseStore r03 = new TLRPC.TL_payments_canPurchaseStore();
        r03.purpose = r13;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new SendGiftSheet$$ExternalSyntheticLambda13(this, r13, r03));
        return;
    L56:
        return;
    L53:
        return;
    L14:
        TLRPC.TL_premiumGiftOption r22 = r2.giftOption;
        if (r22 == null) goto L51;
        r1 = r22;
        goto L18
    L51:
        this.button.setLoading(false);
        return;
    L9:
        if (this.premiumTier.isStarsPaymentAvailable() == false) goto L11;
        r1 = this.premiumTier.getStarsOption();
        goto L18
    }

    private void buyStarGift() {
        StarsController.getInstance(this.currentAccount).buyStarGift(this.starGift, this.anonymous, this.upgrade, this.dialogId, getMessage(), new SendGiftSheet$$ExternalSyntheticLambda14(this));
    }

    private TLRPC.TL_textWithEntities getMessage() {
        if (MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(this.dialogId) <= 0) goto L5;
        return null;
    L5:
        TLRPC.MessageAction r4 = this.action;
        if ((r4 instanceof TLRPC.TL_messageActionStarGift) == false) goto L10;
        return ((TLRPC.TL_messageActionStarGift) r4).message;
    L10:
        if ((r4 instanceof TLRPC.TL_messageActionGiftCode) == false) goto L14;
        return ((TLRPC.TL_messageActionGiftCode) r4).message;
    L14:
        if ((r4 instanceof TLRPC.TL_messageActionGiftPremium) == true) goto L16;
        return null;
    L16:
        return ((TLRPC.TL_messageActionGiftPremium) r4).message;
    }

    private /* synthetic */ void lambda$buyPremiumTier$10() {
        onGiftSuccess(false);
    }

    private /* synthetic */ void lambda$buyPremiumTier$11() {
        onGiftSuccess(true);
    }

    private /* synthetic */ void lambda$buyPremiumTier$12(BillingResult r1) {
        if (r1.getResponseCode() != 0) goto L6;
        AndroidUtilities.runOnUIThread(new SendGiftSheet$$ExternalSyntheticLambda19(this));
        return;
    }

    private /* synthetic */ void lambda$buyPremiumTier$13(TLObject r2, TLRPC.TL_inputStorePaymentGiftPremium r3, TLRPC.TL_error r4, TLRPC.TL_payments_canPurchaseStore r5) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        BillingController.getInstance().launchBillingFlow(getBaseFragment().getParentActivity(), AccountInstance.getInstance(this.currentAccount), r3, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(this.premiumTier.googlePlayProductDetails).build()));
        return;
    L6:
        if (r4 == null) goto L9;
        AlertsCreator.processError(this.currentAccount, r4, getBaseFragment(), r5, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$buyPremiumTier$14(TLRPC.TL_inputStorePaymentGiftPremium r7, TLRPC.TL_payments_canPurchaseStore r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new SendGiftSheet$$ExternalSyntheticLambda16(this, r9, r7, r10, r8));
    }

    private /* synthetic */ void lambda$buyPremiumTier$4(TLRPC.User r3, Boolean r4, String r5) {
        if (r4.booleanValue() == false) goto L9;
        Runnable r42 = this.closeParentSheet;
        if (r42 == null) goto L7;
        r42.run();
    L7:
        AndroidUtilities.hideKeyboard(this.messageEdit);
        dismiss();
        AndroidUtilities.runOnUIThread(new SendGiftSheet$$ExternalSyntheticLambda18(r3), 250);
    L11:
        this.button.setLoading(false);
        return;
    L9:
        if (TextUtils.isEmpty(r5) == true) goto L11;
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r5})).show();
        goto L11
    }

    private /* synthetic */ void lambda$buyPremiumTier$6(TLRPC.User r5, Void r6) {
        Runnable r62 = this.closeParentSheet;
        if (r62 == null) goto L5;
        r62.run();
    L5:
        dismiss();
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.giftsToUserSent, new Object[0]);
        AndroidUtilities.runOnUIThread(new SendGiftSheet$$ExternalSyntheticLambda15(r5), 250);
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putBoolean("show_gift_for_" + this.dialogId, true).putBoolean(Calendar.getInstance().get(1) + "show_gift_for_" + this.dialogId, true).apply();
    }

    private /* synthetic */ void lambda$buyPremiumTier$7(TLRPC.TL_error r1) {
        BoostDialogs.showToastError(getContext(), r1);
    }

    private /* synthetic */ void lambda$buyPremiumTier$9(TLRPC.User r3, Boolean r4, String r5) {
        if (r4.booleanValue() == false) goto L9;
        Runnable r42 = this.closeParentSheet;
        if (r42 == null) goto L7;
        r42.run();
    L7:
        AndroidUtilities.hideKeyboard(this.messageEdit);
        dismiss();
        AndroidUtilities.runOnUIThread(new SendGiftSheet$$ExternalSyntheticLambda17(r3), 250);
    L11:
        this.button.setLoading(false);
        return;
    L9:
        if (TextUtils.isEmpty(r5) == true) goto L11;
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r5})).show();
        goto L11
    }

    private /* synthetic */ void lambda$buyStarGift$2(Boolean r2, String r3) {
        if (r2.booleanValue() == false) goto L9;
        Runnable r22 = this.closeParentSheet;
        if (r22 == null) goto L7;
        r22.run();
    L7:
        AndroidUtilities.hideKeyboard(this.messageEdit);
        dismiss();
    L23:
        this.button.setLoading(false);
        return;
    L9:
        if ("STARGIFT_USAGE_LIMITED".equalsIgnoreCase(r3) == false) goto L13;
        AndroidUtilities.hideKeyboard(this.messageEdit);
        dismiss();
        StarsController.getInstance(this.currentAccount).makeStarGiftSoldOut(this.starGift);
        return;
    L13:
        if ("STARGIFT_USER_USAGE_LIMITED".equalsIgnoreCase(r3) == false) goto L23;
        AndroidUtilities.hideKeyboard(this.messageEdit);
        dismiss();
        BulletinFactory r23 = getParentBulletinFactory();
        if (r23 == null) goto L25;
        TL_stars.StarGift r32 = this.starGift;
        if (r32 != null) goto L19;
        return;
    L19:
        if (r32.limited_per_user == false) goto L27;
        r23.createSimpleMultiBulletin(r32.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2PerUserLimit", this.starGift.per_user_total))).show();
        return;
    L27:
        return;
    }

    private /* synthetic */ void lambda$fillItems$15() {
        new StarGiftSheet(getContext(), this.currentAccount, this.dialogId, this.resourcesProvider).openAsLearnMore(this.starGift.id, this.name);
    }

    private /* synthetic */ void lambda$fillItems$16() {
        AuctionJoinSheet.showMoreInfo(getContext(), this.resourcesProvider, this.starGift);
    }

    private /* synthetic */ void lambda$fillItems$17() {
        new StarsIntroActivity.StarsOptionsSheet(getContext(), this.resourcesProvider).show();
    }

    private /* synthetic */ void lambda$new$0(long r2, Context r4, Runnable r5, TL_stars.StarGift r6, View r7) {
        if (this.button.isLoading() == false) goto L6;
        return;
    L6:
        if (this.auction == null) goto L11;
        AuctionBidSheet.Params r62 = new AuctionBidSheet.Params(r2, this.anonymous, getMessage());
        AuctionBidSheet r22 = new AuctionBidSheet(r4, this.resourcesProvider, r62, this.auction);
        r22.show();
        r22.setCloseParentSheet(r5);
        AndroidUtilities.hideKeyboard(this.messageEdit);
        dismiss();
        if (this.isDismissed == true) goto L22;
        AndroidUtilities.runOnUIThread(new SendGiftSheet$$ExternalSyntheticLambda6(this), 500);
        return;
    L22:
        return;
    L11:
        this.button.setLoading(true);
        int r23 = this.messageEdit.editTextEmoji.getEmojiPadding();
        EditEmojiTextCell r42 = this.messageEdit;
        if (r23 <= 0) goto L15;
        r42.editTextEmoji.hidePopup(true);
    L17:
        if (r6 == null) goto L20;
        buyStarGift();
        return;
    L20:
        buyPremiumTier();
        return;
    L15:
        if (r42.editTextEmoji.isKeyboardVisible() == false) goto L17;
        this.messageEdit.editTextEmoji.closeKeyboard();
        goto L17
    }

    private /* synthetic */ void lambda$new$1(boolean r4, boolean r5, TL_stars.StarGift r6, GiftPremiumBottomSheet$GiftTier r7, View r8, int r9) {
        UniversalAdapter r0 = this.adapter;
        if (this.reverseLayout == true) goto L6;
        r9 = r9 - 1;
    L6:
        UItem r92 = r0.getItem(r9);
        if (r92 == null) goto L68;
        int r93 = r92.id;
        if (r93 != 1) goto L17;
        boolean r42 = !this.anonymous;
        this.anonymous = r42;
        TLRPC.MessageAction r52 = this.action;
        if ((r52 instanceof TLRPC.TL_messageActionStarGift) == false) goto L14;
        ((TLRPC.TL_messageActionStarGift) r52).name_hidden = r42;
    L14:
        this.messageObject.updateMessageText();
        this.actionCell.setMessageObject(this.messageObject, true);
        this.adapter.update(true);
        return;
    L17:
        if (r93 != 2) goto L48;
        if (r4 == true) goto L45;
        if (r5 == true) goto L45;
        boolean r43 = this.upgrade;
        this.upgrade = !r43;
        TLRPC.MessageAction r53 = this.action;
        if ((r53 instanceof TLRPC.TL_messageActionStarGift) == false) goto L43;
        TLRPC.TL_messageActionStarGift r54 = (TLRPC.TL_messageActionStarGift) r53;
        if (r43 == true) goto L26;
    L32:
        boolean r62 = true;
    L33:
        r54.can_upgrade = r62;
        long r72 = 0;
        if (this.self == false) goto L36;
    L35:
        long r1 = 0;
    L38:
        r54.upgrade_stars = r1;
        if (r43 == false) goto L42;
        r72 = this.starGift.convert_stars;
    L42:
        r54.convert_stars = r72;
        goto L43
    L36:
        if (r43 == true) goto L35;
        r1 = this.starGift.upgrade_stars;
        goto L38
    L26:
        if (this.self == false) goto L31;
        if (r6 == null) goto L31;
        if (r6.can_upgrade == true) goto L32;
    L31:
        r62 = false;
    L43:
        this.messageObject.updateMessageText();
        this.actionCell.setMessageObject(this.messageObject, true);
        this.adapter.update(true);
        setButtonText(true);
        return;
    L45:
        int r44 = -this.shakeDp;
        this.shakeDp = r44;
        AndroidUtilities.shakeViewSpring(r8, r44);
        return;
    L48:
        if (r93 != 3) goto L67;
        boolean r45 = this.useStars;
        this.useStars = !r45;
        TLRPC.MessageAction r55 = this.action;
        if ((r55 instanceof TLRPC.TL_messageActionGiftPremium) == false) goto L58;
        TLRPC.TL_messageActionGiftPremium r56 = (TLRPC.TL_messageActionGiftPremium) r55;
        if (r45 == true) goto L54;
        r56.currency = "XTR";
        r56.amount = r7.getStarsPrice();
    L65:
        this.messageObject.updateMessageText();
        this.actionCell.setMessageObject(this.messageObject, true);
        this.adapter.update(true);
        setButtonText(true);
        return;
    L54:
        r56.currency = r7.getCurrency();
        long r12 = r7.getPrice();
        r56.amount = r12;
        if (r7.googlePlayProductDetails == null) goto L65;
        r56.amount = (long) (r12 * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r56.currency) - 6));
        goto L65
    L58:
        if ((r55 instanceof TLRPC.TL_messageActionGiftCode) == false) goto L65;
        TLRPC.TL_messageActionGiftCode r57 = (TLRPC.TL_messageActionGiftCode) r55;
        if (r45 == true) goto L62;
        r57.currency = "XTR";
        r57.amount = r7.getStarsPrice();
        goto L65
    L62:
        r57.currency = r7.getCurrency();
        long r13 = r7.getPrice();
        r57.amount = r13;
        if (r7.googlePlayProductDetails == null) goto L65;
        r57.amount = (long) (r13 * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r57.currency) - 6));
        goto L65
    L67:
        return;
    }

    private void onGiftSuccess(boolean r12) {
        TLRPC.UserFull r0 = MessagesController.getInstance(this.currentAccount).getUserFull(this.dialogId);
        TLObject r1 = MessagesController.getInstance(this.currentAccount).getUserOrChat(this.dialogId);
        if (r0 == null) goto L8;
        if ((r1 instanceof TLRPC.User) == false) goto L8;
        TLRPC.User r13 = (TLRPC.User) r1;
        r13.premium = true;
        MessagesController.getInstance(this.currentAccount).putUser(r13, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(r13.id), r0});
    L8:
        if (getBaseFragment() == null) goto L27;
        ArrayList r02 = new ArrayList(((LaunchActivity) getBaseFragment().getParentActivity()).getActionBarLayout().getFragmentStack());
        INavigationLayout r14 = getBaseFragment().getParentLayout();
        int r3 = r02.size();
        ChatActivity r4 = null;
        int r5 = 0;
    L10:
        if (r5 >= r3) goto L23;
        Object r6 = r02.get(r5);
        r5 = r5 + 1;
        BaseFragment r62 = (BaseFragment) r6;
        if ((r62 instanceof ChatActivity) == true) goto L13;
        if ((r62 instanceof ProfileActivity) == false) goto L10;
        if (r12 == false) goto L22;
        if (r14.getLastFragment() != r62) goto L22;
        r62.finishFragment();
    L22:
        r62.removeSelfFromStack();
        goto L10
    L13:
        r4 = (ChatActivity) r62;
        if (r4.getDialogId() == this.dialogId) goto L10;
        r62.removeSelfFromStack();
        goto L10
    L23:
        if (r4 != null) goto L25;
    L26:
        Bundle r122 = new Bundle();
        r122.putLong("user_id", this.dialogId);
        r14.presentFragment(new ChatActivity(r122), true);
        goto L27
    L25:
        if (r4.getDialogId() != this.dialogId) goto L26;
    L27:
        dismiss();
    }

    private void setButtonText(boolean r12) {
        if (this.auction == null) goto L15;
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        boolean r2 = this.auction.isUpcoming(r0);
        GiftAuctionController.Auction r3 = this.auction;
        if (r2 == false) goto L8;
        int r1 = r3.gift.auction_start_date - r0;
        this.button.setText(LocaleController.getString(R.string.Gift2AuctionPlaceAEarlyBid), r12);
        this.button.setSubText(LocaleController.formatString(R.string.Gift2AuctionStartsIn, new Object[]{LocaleController.formatTTLString(r1)}), r12);
        return;
    L8:
        TL_stars.TL_starGiftAuctionState r22 = r3.auctionStateActive;
        if (r22 == null) goto L12;
        int r13 = r22.end_date - r0;
        this.button.setText(LocaleController.getString(R.string.Gift2AuctionPlaceABid), r12);
        this.button.setSubText(LocaleController.formatString(R.string.Gift2AuctionTimeLeft, new Object[]{LocaleController.formatTTLString(r13)}), r12);
        return;
    L12:
        this.button.setText(LocaleController.getString(R.string.Gift2AuctionPlaceABid), r12);
        this.button.setSubText(null, r12);
        return;
    L15:
        if (this.starGift == null) goto L36;
        long r23 = StarsController.getInstance(this.currentAccount).getBalance().amount;
        TL_stars.StarGift r02 = this.starGift;
        long r4 = r02.stars;
        long r7 = 0;
        if (this.upgrade == false) goto L19;
        long r9 = r02.upgrade_stars;
    L20:
        long r42 = r4 + r9;
        if (TextUtils.isEmpty(this.messageEdit.getText()) == true) goto L24;
        r7 = this.send_paid_messages_stars;
    L24:
        long r43 = r42 + r7;
        ButtonWithCounterView r03 = this.button;
        if (this.self == false) goto L27;
        String r6 = "Gift2SendSelf";
    L28:
        r03.setText(StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma(r6, (int) r43), this.cachedStarSpan), r12);
        if (StarsController.getInstance(this.currentAccount).balanceAvailable() == true) goto L31;
    L34:
        this.button.setSubText(null, r12);
        return;
    L31:
        if (r43 <= r23) goto L34;
        this.button.setSubText(LocaleController.formatPluralStringComma("Gift2SendYourBalance", (int) r23), r12);
        return;
    L27:
        r6 = "Gift2Send";
        goto L28
    L19:
        r9 = 0;
        goto L20
    L36:
        GiftPremiumBottomSheet$GiftTier r04 = this.premiumTier;
        if (r04 == null) goto L44;
        boolean r24 = this.useStars;
        ButtonWithCounterView r32 = this.button;
        if (r24 == false) goto L41;
        r32.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2SendPremiumStars, new Object[]{LocaleController.formatNumber(r04.getStarsPrice(), ',')}), 1.0f, this.cachedStarSpan), r12);
        this.cachedStarSpan[0].spaceScaleX = 0.85f;
    L42:
        this.button.setSubText(null, r12);
        return;
    L41:
        r32.setText(new SpannableStringBuilder(LocaleController.formatString(R.string.Gift2SendPremium, new Object[]{this.premiumTier.getFormattedPrice()})), r12);
        goto L42
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new SendGiftSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.starBalanceUpdated) goto L10;
        setButtonText(true);
        UniversalAdapter r22 = this.adapter;
        if (r22 != null) goto L7;
        return;
    L7:
        if (this.premiumTier == null) goto L12;
        r22.update(true);
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        int r0 = this.messageEdit.editTextEmoji.getEmojiPadding();
        EditEmojiTextCell r1 = this.messageEdit;
        if (r0 <= 0) goto L6;
        r1.editTextEmoji.hidePopup(true);
        return;
    L6:
        boolean r02 = r1.editTextEmoji.isKeyboardVisible();
        EditEmojiTextCell r12 = this.messageEdit;
        if (r02 == false) goto L10;
        r12.editTextEmoji.closeKeyboard();
        return;
    L10:
        if (r12 == null) goto L13;
        r12.editTextEmoji.onPause();
    L13:
        if (this.auction == null) goto L15;
        GiftAuctionController.getInstance(this.currentAccount).unsubscribeFromGiftAuction(this.auction.giftId, this);
    L15:
        this.isDismissed = true;
        super.dismiss();
    }

    public void fillItems(ArrayList<UItem> r11, UniversalAdapter r12) {
        long r0 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(this.dialogId);
        r11.add(UItem.asCustom(-1, this.chatView));
        if (r0 > 0) goto L5;
        r11.add(UItem.asCustom(-2, this.messageEdit));
        r11.add(UItem.asSpace(AndroidUtilities.dp(12.0f)));
    L5:
        TL_stars.StarGift r02 = this.starGift;
        boolean r4 = false;
        if (r02 != null) goto L8;
        if (r0 > 0) goto L62;
        r11.add(UItem.asShadow(-3, LocaleController.formatString(R.string.Gift2MessagePremiumInfo, new Object[]{this.name})));
    L62:
        GiftPremiumBottomSheet$GiftTier r122 = this.premiumTier;
        if (r122 != null) goto L65;
    L68:
        if (this.reverseLayout == false) goto L71;
        Collections.reverse(r11);
        return;
    L71:
        return;
    L65:
        if (r122.isStarsPaymentAvailable() == false) goto L68;
        r11.add(UItem.asCheck(3, StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatString(R.string.Gift2MessageStars, new Object[]{Integer.valueOf((int) this.premiumTier.getStarsPrice())}), 0.78f)).setChecked(this.useStars));
        SpannableStringBuilder r123 = new SpannableStringBuilder(LocaleController.formatNumber(StarsController.getInstance(this.currentAccount).getBalance().amount, ','));
        r123.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r123.length(), 33);
        r11.add(UItem.asShadow(-7, TextUtils.concat(new CharSequence[]{StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatSpannable(R.string.Gift2MessageStarsInfo, new Object[]{r123}), 0.66f), " ", AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2MessageStarsInfoLink), new SendGiftSheet$$ExternalSyntheticLambda5(this)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f))})));
        goto L68
    L8:
        if (r02.can_upgrade == true) goto L10;
    L44:
        r11.add(UItem.asShadow(-5, null));
    L46:
        if (this.self == false) goto L48;
        int r124 = R.string.Gift2HideSelf;
    L49:
        r11.add(UItem.asCheck(1, LocaleController.getString(r124)).setChecked(this.anonymous));
        if (this.self == false) goto L54;
        int r125 = R.string.Gift2HideSelfInfo;
    L52:
        String r126 = LocaleController.getString(r125);
    L57:
        r11.add(UItem.asShadow(-6, r126));
        if (this.limitContainerWrapper == null) goto L68;
        int r127 = this.starGift.gifts_per_round;
        CharSequence r128 = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("Gift2AuctionInfoLearnMore2", r127, new Object[]{Integer.valueOf(r127)}), new SendGiftSheet$$ExternalSyntheticLambda4(this)), true);
        r11.add(UItem.asCustom(-43, this.limitContainerWrapper));
        r11.add(UItem.asShadow(-44, r128));
        goto L68
    L54:
        if (this.dialogId >= 0) goto L56;
        r125 = R.string.Gift2HideChannelInfo;
        goto L52
    L56:
        r126 = LocaleController.formatString(R.string.Gift2HideInfo, new Object[]{this.name});
        goto L57
    L48:
        r124 = R.string.Gift2Hide;
        goto L49
    L10:
        if (this.self == true) goto L44;
        r11.add(UItem.asShadow(-3, null));
        if (this.self == false) goto L14;
        int r129 = R.string.Gift2UpgradeSelf;
    L15:
        r11.add(UItem.asCheck(2, StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatString(r129, new Object[]{Integer.valueOf((int) this.starGift.upgrade_stars)}), 0.78f)).setChecked(this.upgrade));
        if (this.forceNotUpgrade == false) goto L23;
        if (this.dialogId >= 0) goto L20;
        int r1210 = R.string.Gift2NoUpgradeChannelForcedInfo;
    L21:
        CharSequence r1211 = LocaleController.formatString(r1210, new Object[]{this.name});
    L38:
        UItem r1212 = UItem.asShadow(-5, r1211);
        if (this.forceUpgrade == false) goto L41;
    L43:
        r11.add(r1212.setEnabled(r4));
        goto L46
    L41:
        if (this.forceNotUpgrade == true) goto L43;
        r4 = true;
        goto L43
    L20:
        r1210 = R.string.Gift2NoUpgradeForcedInfo;
        goto L21
    L23:
        if (this.forceUpgrade == false) goto L30;
        if (this.dialogId >= 0) goto L27;
        int r1213 = R.string.Gift2UpgradeChannelForcedInfo;
    L28:
        r1211 = LocaleController.formatString(r1213, new Object[]{this.name});
        goto L38
    L27:
        r1213 = R.string.Gift2UpgradeForcedInfo;
        goto L28
    L30:
        if (this.self == false) goto L33;
        String r1214 = LocaleController.getString(R.string.Gift2UpgradeSelfInfo);
    L37:
        r1211 = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(r1214, new SendGiftSheet$$ExternalSyntheticLambda3(this)), true);
        goto L38
    L33:
        if (this.dialogId < 0) goto L35;
        int r1215 = R.string.Gift2UpgradeInfo;
    L36:
        r1214 = LocaleController.formatString(r1215, new Object[]{this.name});
        goto L37
    L35:
        r1215 = R.string.Gift2UpgradeChannelInfo;
        goto L36
    L14:
        r129 = R.string.Gift2Upgrade;
        goto L15
    }

    public BulletinFactory getParentBulletinFactory() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L7;
        return null;
    L7:
        return BulletinFactory.of(r0);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.self == false) goto L5;
        int r0 = R.string.Gift2TitleSelf2;
    L7:
        return LocaleController.getString(r0);
    L5:
        r0 = R.string.Gift2Title;
        goto L7
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        int r0 = this.messageEdit.editTextEmoji.getEmojiPadding();
        EditEmojiTextCell r1 = this.messageEdit;
        if (r0 <= 0) goto L7;
        r1.editTextEmoji.hidePopup(true);
        return;
    L7:
        if (r1.editTextEmoji.isKeyboardVisible() == false) goto L10;
        this.messageEdit.editTextEmoji.closeKeyboard();
        return;
    L10:
        super.onBackPressed();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        this.recyclerListView.invalidateItemDecorations();
    }

    @Override // org.telegram.messenger.GiftAuctionController.OnAuctionUpdateListener
    public void onUpdate(GiftAuctionController.Auction r1) {
        this.auction = r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        EditEmojiTextCell r0 = this.messageEdit;
        if (r0 == null) goto L5;
        r0.editTextEmoji.onResume();
    L5:
        super.show();
    }
}
