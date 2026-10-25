package org.telegram.ui.Stars;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$AmountLimits;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.HorizontalRoundTabsLayout;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.TON.TONIntroActivity;

/* loaded from: classes7.dex */
public class GiftOfferSheet extends BottomSheetWithRecyclerListView {
    private static final int[] ALLOWED_DURATIONS = null;
    private UniversalAdapter adapter;
    private final BalanceCloud balanceCloud;
    private boolean balanceCloudVisible;
    private final ButtonWithCounterView buttonView;
    private final Runnable closeParentSheet;
    private final HorizontalRoundTabsLayout currencyTabsView;
    private final long dialogId;
    private final AnimatedTextView dollarsEqView;
    private final String giftName;
    private final TL_stars.TL_starGiftUnique giftUnique;
    private final ImageView iconStars;
    private final ImageView iconTon;
    private AmountUtils$Amount inputAmount;
    private int inputAmountError;
    private final AmountUtils$AmountLimits inputAmountLimits;
    private boolean isFullyVisible;
    private final UItem mainItem;
    private final EditTextBoldCursor publishingTimeField;
    private final TextView publishingTimeHint;
    private int selectedDuration;
    private final ColoredImageSpan[] spanRefStars;
    private final ColoredImageSpan[] spanRefTon;
    private final EditTextBoldCursor starsCountEditField;
    private final TextView starsCountEditHint;
    private final OutlineTextContainerView starsCountEditOutline;

    /* renamed from: org.telegram.ui.Stars.GiftOfferSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        final /* synthetic */ GiftOfferSheet this$0;

        public AnonymousClass1(GiftOfferSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stars.GiftOfferSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ GiftOfferSheet this$0;

        public AnonymousClass2(GiftOfferSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r8) {
            if (r8 != null) goto L5;
        L10:
            boolean r2 = true;
        L11:
            if (r2 == true) goto L17;
            String r3 = r8.toString();
            int r4 = r3.indexOf(46);
            if (r4 < 0) goto L17;
            if (((r3.length() - r4) - 1) <= 2) goto L17;
            r8.delete(r4 + 3, r3.length());
        L17:
            if (r2 == true) goto L19;
            AmountUtils$Amount r82 = AmountUtils$Amount.fromDecimal(r8.toString(), GiftOfferSheet.m18896$$Nest$fgetinputAmount(this.this$0).currency);
        L20:
            GiftOfferSheet.m18899$$Nest$msetAmount(this.this$0, r82, false, false, true);
            GiftOfferSheet.m18898$$Nest$fgetstarsCountEditOutline(this.this$0).animateSelection(GiftOfferSheet.m18897$$Nest$fgetstarsCountEditField(this.this$0).isFocused(), !TextUtils.isEmpty(GiftOfferSheet.m18897$$Nest$fgetstarsCountEditField(this.this$0).getText()));
            return;
        L19:
            r82 = AmountUtils$Amount.fromNano(0, GiftOfferSheet.m18896$$Nest$fgetinputAmount(this.this$0).currency);
            goto L20
        L5:
            if (r8.toString().isEmpty() == true) goto L10;
            if (".".equals(r8.toString()) == true) goto L10;
            r2 = false;
            goto L11
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static /* synthetic */ void $r8$lambda$6IW4JYnJAIVMSJ7MtVUiiBMYm5A(GiftOfferSheet r0, View r1, boolean r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$6X7h-RlCkD9TFjgIv9joNX4QTqM, reason: not valid java name */
    public static /* synthetic */ void m18891$r8$lambda$6X7hRlCkD9TFjgIv9joNX4QTqM(GiftOfferSheet r0, Context r1, View r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6tpq5ytAQ8ndFp1dEggwIR1Iwck(GiftOfferSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7wt7clI_NqKw_KKtwM617i5ff6A(GiftOfferSheet r0, long r1, boolean r3, AmountUtils$Amount r4, long r5, AlertDialog r7, int r8) {
        r0.lambda$openConfirmAlert$9(r1, r3, r4, r5, r7, r8);
    }

    /* renamed from: $r8$lambda$IzHiAye-p2_oXhadHlpCz1iaQ-M, reason: not valid java name */
    public static /* synthetic */ void m18892$r8$lambda$IzHiAyep2_oXhadHlpCz1iaQM(int r1, BaseFragment r2, Browser.Progress r3, AlertDialog r4, TLRPC.Updates r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L5;
        if (r6 != null) goto L5;
        MessagesController.getInstance(r1).processUpdates(r5, false);
    L5:
        AndroidUtilities.runOnUIThread(new GiftOfferSheet$$ExternalSyntheticLambda9(r2, r6, r3, r4));
    }

    public static /* synthetic */ void $r8$lambda$NdFIGatlhvXkBaKt5B5omeZlgtQ(GiftOfferSheet r0, Integer r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZF4uMjXRdLhH0w0WuqIx__2m8a8(GiftOfferSheet r0, Context r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$ZhRIWHi2Ie9_smWgCZje_c2-NZE, reason: not valid java name */
    public static /* synthetic */ void m18893$r8$lambda$ZhRIWHi2Ie9_smWgCZje_c2NZE(GiftOfferSheet r0, Browser.Progress r1, AlertDialog r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        r0.lambda$openConfirmAlert$8(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$aDhcoXcjT1naWqNx4SfyPR0QPMc(BaseFragment r1, TLRPC.TL_error r2, Browser.Progress r3, AlertDialog r4) {
        if (r1 == null) goto L6;
        if (r2 == null) goto L6;
        BulletinFactory.of(r1).showForError(r2);
    L6:
        if ((r1 instanceof ChatActivity) == false) goto L9;
        if (r2 != null) goto L9;
        ((ChatActivity) r1).startFireworks();
    L9:
        r3.end();
        r4.dismiss();
    }

    /* renamed from: $r8$lambda$bdy6mMF-BTu5UGnn_RscG2LJwjA, reason: not valid java name */
    public static /* synthetic */ void m18894$r8$lambda$bdy6mMFBTu5UGnn_RscG2LJwjA(GiftOfferSheet r0, int r1, Context r2, Theme.ResourcesProvider r3, long r4, View r6) {
        r0.lambda$new$5(r1, r2, r3, r4, r6);
    }

    public static /* synthetic */ void $r8$lambda$gwQEkKs_SOAXz1zUreKS8Dm31cc(int r2, int r3, BaseFragment r4, AlertDialog r5, int r6) {
        Browser.Progress r62 = r5.makeButtonLoading(-1);
        r62.init();
        TL_payments.TL_resolveStarGiftOffer r0 = new TL_payments.TL_resolveStarGiftOffer();
        r0.offer_msg_id = r2;
        ConnectionsManager.getInstance(r3).sendRequestTyped(r0, new GiftOfferSheet$$ExternalSyntheticLambda1(r3, r4, r62, r5));
    }

    public static /* synthetic */ void $r8$lambda$jpXlIMyd72zcjKyKg7XFu3LTrBI(GiftOfferSheet r0, int r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$m5aaIZxJ6ocDKRKbZh4dRKRfAa8(GiftOfferSheet r0, Browser.Progress r1, AlertDialog r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        r0.lambda$openConfirmAlert$7(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$q-8Ixy7NGE2ONUB9VZdtJSdHrGI, reason: not valid java name */
    public static /* synthetic */ void m18895$r8$lambda$q8Ixy7NGE2ONUB9VZdtJSdHrGI(GiftOfferSheet r0) {
        r0.lambda$show$6();
    }

    /* renamed from: -$$Nest$fgetinputAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AmountUtils$Amount m18896$$Nest$fgetinputAmount(GiftOfferSheet r0) {
        return r0.inputAmount;
    }

    /* renamed from: -$$Nest$fgetstarsCountEditField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m18897$$Nest$fgetstarsCountEditField(GiftOfferSheet r0) {
        return r0.starsCountEditField;
    }

    /* renamed from: -$$Nest$fgetstarsCountEditOutline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OutlineTextContainerView m18898$$Nest$fgetstarsCountEditOutline(GiftOfferSheet r0) {
        return r0.starsCountEditOutline;
    }

    /* renamed from: -$$Nest$msetAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18899$$Nest$msetAmount(GiftOfferSheet r0, AmountUtils$Amount r1, boolean r2, boolean r3, boolean r4) {
        r0.setAmount(r1, r2, r3, r4);
    }

    static {
        ALLOWED_DURATIONS = new int[]{21600, 43200, 86400, 129600, 172800, 259200};
    }

    public GiftOfferSheet(Context r29, int r30, long r31, TL_stars.TL_starGiftUnique r33, Theme.ResourcesProvider r34, Runnable r35) {
        super(r29, null, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r34);
        AmountUtils$AmountLimits r2 = new AmountUtils$AmountLimits();
        this.inputAmountLimits = r2;
        this.spanRefStars = new ColoredImageSpan[1];
        this.spanRefTon = new ColoredImageSpan[1];
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        this.topPadding = 0.2f;
        this.dialogId = r31;
        this.giftUnique = r33;
        this.giftName = r33.title + " #" + LocaleController.formatNumber(r33.num, ',');
        this.closeParentSheet = r35;
        this.waitingKeyboard = true;
        this.smoothKeyboardAnimationEnabled = true;
        boolean r4 = StarsController.getTonInstance(r30).canUseTon();
        if (r31 > 0) goto L5;
    L9:
        AppGlobalConfig r5 = MessagesController.getInstance(r30).config;
        long r3 = r33.offer_min_stars;
        AmountUtils$Currency r15 = AmountUtils$Currency.STARS;
        AmountUtils$Amount r32 = AmountUtils$Amount.fromDecimal(r3, r15);
        AmountUtils$Amount r42 = AmountUtils$Amount.fromDecimal(Math.max(r32.asDecimal() * 2, r5.starsStarGiftResaleAmountMax.get()), r15);
        AmountUtils$Currency r6 = AmountUtils$Currency.TON;
        AmountUtils$Amount r7 = AmountUtils$Amount.fromNano(Math.max(r32.convertTo(r6).round(2).asNano(), r5.tonStarGiftResaleAmountMin.get()), r6);
        AmountUtils$Amount r52 = AmountUtils$Amount.fromNano(Math.max(r7.asNano() * 2, r5.tonStarGiftResaleAmountMax.get()), r6);
        r2.set(r32, r42);
        r2.set(r7, r52);
        BalanceCloud r22 = new BalanceCloud(r29, r30, r34);
        this.balanceCloud = r22;
        r22.setScaleX(0.6f);
        r22.setScaleY(0.6f);
        r22.setAlpha(0.0f);
        r22.setEnabled(false);
        r22.setClickable(false);
        this.container.addView(r22, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 48.0f, 0.0f, 0.0f));
        ScaleStateListAnimator.apply(r22);
        r22.setOnClickListener(new GiftOfferSheet$$ExternalSyntheticLambda2(this, r29, r34));
        fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r34));
        LinearLayout r72 = new LinearLayout(r29);
        r72.setClickable(true);
        r72.setOrientation(1);
        r72.setPadding(0, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(16.0f));
        EditTextBoldCursor r9 = new EditTextBoldCursor(r29);
        this.starsCountEditField = r9;
        if (r4 == false) goto L12;
        HorizontalRoundTabsLayout r53 = new HorizontalRoundTabsLayout(r29, r34);
        this.currencyTabsView = r53;
        ArrayList<CharSequence> r62 = new ArrayList();
        r62.add(LocaleController.getString(R.string.SuggestedOfferStars));
        r62.add(LocaleController.getString(R.string.SuggestedOfferTON));
        r53.setTabs(r62, new GiftOfferSheet$$ExternalSyntheticLambda3(this));
        r72.addView(r53, LayoutHelper.createLinear(-1, -2, 18.0f, 0.0f, 18.0f, 18.0f));
    L13:
        LinearLayout r54 = new LinearLayout(r29);
        r54.setOrientation(1);
        r72.addView(r54, LayoutHelper.createLinear(-1, -2, 1.0f));
        OutlineTextContainerView r63 = new OutlineTextContainerView(r29);
        this.starsCountEditOutline = r63;
        r9.setCursorSize(AndroidUtilities.dp(20.0f));
        r9.setCursorWidth(1.5f);
        r9.setImeOptions(268435462);
        r9.setTextSize(1, 17.0f);
        r9.setMaxLines(1);
        r9.setBackground(null);
        r9.setPadding(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        int r10 = Theme.key_windowBackgroundWhiteBlackText;
        r9.setTextColor(Theme.getColor(r10));
        r9.requestFocus();
        r63.setLeftPadding(AndroidUtilities.dp(28.0f));
        r63.attachEditText(r9);
        r63.animateSelection(true, false, false);
        r63.setForceUseCenter2(true);
        r9.setOnFocusChangeListener(new GiftOfferSheet$$ExternalSyntheticLambda4(this));
        r63.addView(r9, LayoutHelper.createFrame(-1, -2, 48));
        r54.addView(r63, LayoutHelper.createLinear(-1, 58, 18.0f, 0.0f, 18.0f, 0.0f));
        ImageView r11 = new ImageView(r29);
        this.iconStars = r11;
        r11.setImageResource(R.drawable.star_small_inner);
        r63.addView(r11, LayoutHelper.createFrame(22, 22.0f, 19, 14.0f, 0.0f, 0.0f, 0.0f));
        ImageView r112 = new ImageView(r29);
        this.iconTon = r112;
        r112.setImageResource(R.drawable.mini_gram_72);
        r112.setColorFilter(-13397548);
        r63.addView(r112, LayoutHelper.createFrame(22, 22.0f, 19, 14.0f, 0.0f, 0.0f, 0.0f));
        AnimatedTextView r113 = new AnimatedTextView(r29);
        this.dollarsEqView = r113;
        int r13 = Theme.key_windowBackgroundWhiteGrayText;
        r113.setTextColor(Theme.getColor(r13));
        r113.setTextSize(AndroidUtilities.dp(13.0f));
        r113.setGravity(5);
        r63.addView(r113, LayoutHelper.createFrame(-2, -1.0f, 21, 0.0f, 0.0f, 16.0f, 0.0f));
        TextView r64 = new TextView(r29);
        this.starsCountEditHint = r64;
        r64.setTextSize(1, 13.0f);
        r54.addView(r64, LayoutHelper.createLinear(-1, -2, 55, 33, 4, 33, 0));
        AnonymousClass1 r65 = new AnonymousClass1(this, r29);
        this.publishingTimeField = r65;
        r65.setCursorSize(AndroidUtilities.dp(20.0f));
        r65.setCursorWidth(1.5f);
        r65.setTextSize(1, 17.0f);
        r65.setMaxLines(1);
        r65.setBackground(null);
        r65.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r65.setTextColor(Theme.getColor(r10));
        r65.setFocusable(false);
        r65.setClickable(false);
        r65.setEnabled(false);
        OutlineTextContainerView r23 = new OutlineTextContainerView(r29);
        r23.setText(LocaleController.getString(R.string.GiftOfferDuration));
        r23.attachEditText(r65);
        r23.addView(r65, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, 0.0f, 48.0f, 0.0f));
        ScaleStateListAnimator.apply(r23, 0.02f, 1.2f);
        r23.setOnClickListener(new GiftOfferSheet$$ExternalSyntheticLambda5(this, r29));
        r54.addView(r23, LayoutHelper.createLinear(-1, 58, 18.0f, 18.0f, 18.0f, 0.0f));
        ImageView r43 = new ImageView(r29);
        r43.setImageResource(R.drawable.arrow_more);
        r43.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogEmptyImage, r34), PorterDuff.Mode.SRC_IN));
        r23.addView(r43, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 14.0f, 0.0f));
        TextView r24 = new TextView(r29);
        this.publishingTimeHint = r24;
        r24.setTextColor(Theme.getColor(r13));
        r24.setTextSize(1, 13.0f);
        r54.addView(r24, LayoutHelper.createLinear(-1, -2, 55, 33, 4, 33, 0));
        ButtonWithCounterView r102 = new ButtonWithCounterView(r29, r34);
        this.buttonView = r102;
        r102.setOnClickListener(new GiftOfferSheet$$ExternalSyntheticLambda6(this, r30, r29, r34, r31));
        setAmount(AmountUtils$Amount.fromNano(0, r15), false, true, false);
        setSelectedDuration(86400, false);
        r9.addTextChangedListener(new AnonymousClass2(this));
        FrameLayout.LayoutParams r1 = LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 16.0f, 16.0f, 16.0f);
        int r25 = r1.leftMargin;
        int r36 = this.backgroundPaddingLeft;
        r1.leftMargin = r25 + r36;
        r1.rightMargin += r36;
        this.containerView.addView(r102, r1);
        RecyclerListView r12 = this.recyclerListView;
        int r26 = this.backgroundPaddingLeft;
        r12.setPadding(r26, 0, r26, AndroidUtilities.dp(64.0f));
        this.recyclerListView.setOverScrollMode(2);
        this.mainItem = UItem.asCustom(r72);
        this.adapter.update(false);
        return;
    L12:
        this.currencyTabsView = null;
        goto L13
    L5:
        if (MessagesController.getInstance(r30).getUserFull(r31) != null) goto L9;
        TLRPC.User r55 = MessagesController.getInstance(r30).getUser(Long.valueOf(r31));
        if (r55 == null) goto L9;
        MessagesController.getInstance(r30).loadFullUser(r55, 0, false);
        goto L9
    }

    private void checkAmountInputText(boolean r2) {
        if (this.inputAmount.currency != AmountUtils$Currency.STARS) goto L5;
        int r22 = R.string.GiftOfferStarsToOffer;
    L6:
        this.starsCountEditOutline.setText(LocaleController.getString(r22));
        return;
    L5:
        r22 = R.string.GiftOfferTONToOffer;
        goto L6
    }

    private void checkAmountInputTextHint(boolean r4) {
        AmountUtils$Currency r42 = this.inputAmount.currency;
        int r0 = this.inputAmountError;
        if ((r0 & 4) == 0) goto L10;
        if (r42 != AmountUtils$Currency.STARS) goto L7;
        int r02 = R.string.GiftOfferStarsToOfferInfoIsHigh;
    L8:
        this.starsCountEditHint.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r02, new Object[]{this.inputAmountLimits.getMax(r42).asFormatString(), this.giftName})));
    L21:
        TextView r43 = this.starsCountEditHint;
        if ((this.inputAmountError & (-9)) != 0) goto L24;
        int r03 = Theme.key_windowBackgroundWhiteGrayText;
    L25:
        r43.setTextColor(getThemedColor(r03));
        return;
    L24:
        r03 = Theme.key_text_RedBold;
        goto L25
    L7:
        r02 = R.string.GiftOfferTONToOfferInfoIsHigh;
        goto L8
    L10:
        if ((r0 & 2) == 0) goto L17;
        if (r42 != AmountUtils$Currency.STARS) goto L14;
        int r04 = R.string.GiftOfferStarsToOfferInfoIsLow;
    L15:
        this.starsCountEditHint.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r04, new Object[]{this.inputAmountLimits.getMin(r42).asFormatString(), this.giftName})));
        goto L21
    L14:
        r04 = R.string.GiftOfferTONToOfferInfoIsLow;
        goto L15
    L17:
        if (r42 != AmountUtils$Currency.STARS) goto L19;
        int r44 = R.string.GiftOfferStarsToOfferInfo;
    L20:
        this.starsCountEditHint.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r44, new Object[]{this.giftName})));
        goto L21
    L19:
        r44 = R.string.GiftOfferTONToOfferInfo;
        goto L20
    }

    private void checkBalanceCloudVisibility() {
        if (this.isFullyVisible == false) goto L11;
        if (isDismissed() == true) goto L11;
        if (this.balanceCloud == null) goto L11;
        if (this.containerView.getY() <= AndroidUtilities.dp(32.0f)) goto L11;
    L12:
        boolean r0 = true;
    L15:
        if (this.balanceCloudVisible == r0) goto L31;
        this.balanceCloudVisible = r0;
        BalanceCloud r1 = this.balanceCloud;
        if (r1 == null) goto L32;
        r1.setEnabled(r0);
        this.balanceCloud.setClickable(r0);
        ViewPropertyAnimator r4 = this.balanceCloud.animate();
        float r12 = 0.6f;
        float r2 = 1.0f;
        if (r0 == false) goto L21;
        float r3 = 1.0f;
    L22:
        ViewPropertyAnimator r42 = r4.scaleX(r3);
        if (r0 == false) goto L25;
        r12 = 1.0f;
    L25:
        ViewPropertyAnimator r43 = r42.scaleY(r12);
        if (r0 == true) goto L29;
        r2 = 0.0f;
    L29:
        r43.alpha(r2).setDuration(180).start();
        return;
    L21:
        r3 = 0.6f;
        goto L22
    L32:
        return;
    L31:
        return;
    L11:
        if (this.currencyTabsView == null) goto L12;
        r0 = false;
        goto L15
    }

    private void checkButtonEnabled(boolean r5) {
        if (this.inputAmountError == 0) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.buttonView.isEnabled() == r0) goto L21;
        this.buttonView.setEnabled(r0);
        this.buttonView.setClickable(r0);
        ButtonWithCounterView r4 = this.buttonView;
        float r1 = 0.6f;
        if (r5 == false) goto L17;
        ViewPropertyAnimator r42 = r4.animate();
        if (r0 == false) goto L15;
        r1 = 1.0f;
    L15:
        r42.alpha(r1).setDuration(180).start();
        return;
    L17:
        if (r0 == false) goto L19;
        r1 = 1.0f;
    L19:
        r4.setAlpha(r1);
        return;
    L21:
        return;
    L5:
        if (this.inputAmount.asNano() <= 0) goto L7;
        r0 = true;
        goto L9
    }

    private void checkButtonOfferText(boolean r7) {
        AmountUtils$Amount r0 = this.inputAmount;
        if (r0.currency != AmountUtils$Currency.TON) goto L5;
        boolean r1 = true;
    L6:
        ButtonWithCounterView r2 = this.buttonView;
        int r3 = R.string.GiftOfferButtonStars;
        if (r1 == false) goto L9;
        String r02 = r0.asDecimalString();
    L10:
        String r03 = LocaleController.formatString(r3, new Object[]{r02});
        if (r1 == false) goto L13;
        ColoredImageSpan[] r6 = this.spanRefTon;
    L14:
        r2.setText(StarsIntroActivity.replaceStars(r1, r03, r6), r7);
        return;
    L13:
        r6 = this.spanRefStars;
        goto L14
    L9:
        r02 = LocaleController.formatNumber(r0.asDecimal(), ',');
        goto L10
    L5:
        r1 = false;
        goto L6
    }

    private void checkRateText(boolean r7) {
        StringBuilder r0 = new StringBuilder(10);
        r0.append('~');
        AmountUtils$Currency r1 = this.inputAmount.currency;
        AmountUtils$Currency r2 = AmountUtils$Currency.TON;
        int r3 = this.currentAccount;
        if (r1 != r2) goto L5;
        double r12 = MessagesController.getInstance(r3).config.tonUsdRate.get();
    L6:
        r0.append(BillingController.getInstance().formatCurrency((long) ((this.inputAmount.asDouble() * r12) * 100.0d), "USD", 2));
        this.dollarsEqView.setText(r0, r7);
        return;
    L5:
        r12 = MessagesController.getInstance(r3).starsUsdWithdrawRate1000 * 1.0E-5d;
        goto L6
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        UItem r0 = this.mainItem;
        if (r0 == null) goto L6;
        r1.add(r0);
        return;
    }

    private static AmountUtils$Amount getAmountMinusFee(int r3, AmountUtils$Amount r4) {
        AmountUtils$Currency r0 = r4.currency;
        if (r0 != AmountUtils$Currency.STARS) goto L5;
        int r32 = MessagesController.getInstance(r3).config.starsStarGiftResaleCommissionPermille.get();
    L7:
        return AmountUtils$Amount.fromNano((r4.asNano() * r32) / 1000, r0);
    L5:
        r32 = MessagesController.getInstance(r3).config.tonStarGiftResaleCommissionPermille.get();
        goto L7
    }

    private /* synthetic */ void lambda$new$0(Context r1, Theme.ResourcesProvider r2, View r3) {
        if (this.inputAmount.currency != AmountUtils$Currency.STARS) goto L6;
        new StarsIntroActivity.StarsOptionsSheet(r1, r2).show();
        return;
    }

    private /* synthetic */ void lambda$new$1(int r3) {
        if (r3 != 0) goto L4;
        AmountUtils$Currency r32 = AmountUtils$Currency.STARS;
    L5:
        setAmount(AmountUtils$Amount.fromNano(0, r32), true, false, true);
        this.starsCountEditField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L4:
        r32 = AmountUtils$Currency.TON;
        goto L5
    }

    private /* synthetic */ void lambda$new$2(View r1, boolean r2) {
        this.starsCountEditOutline.animateSelection(r2, !TextUtils.isEmpty(this.starsCountEditField.getText()));
    }

    private /* synthetic */ void lambda$new$3(Integer r2) {
        setSelectedDuration(ALLOWED_DURATIONS[r2.intValue()], true);
    }

    private /* synthetic */ void lambda$new$4(Context r8, View r9) {
        String[] r92 = new String[ALLOWED_DURATIONS.length];
        int r1 = 0;
        int r2 = 0;
    L3:
        int[] r3 = ALLOWED_DURATIONS;
        if (r1 >= r3.length) goto L9;
        r92[r1] = LocaleController.formatPluralString("GiftOfferHours", r3[r1] / 3600, new Object[0]);
        if (r3[r1] != this.selectedDuration) goto L8;
        r2 = r1;
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        AlertsCreator.createCustomPicker(r8, LocaleController.getString(R.string.GiftOfferDuration), r2, r92, new GiftOfferSheet$$ExternalSyntheticLambda11(this));
    }

    private /* synthetic */ void lambda$new$5(int r17, Context r18, Theme.ResourcesProvider r19, long r20, View r22) {
        if (this.buttonView.isEnabled() == true) goto L6;
        return;
    L6:
        if (MessagesController.getInstance(r17).isFrozen() == false) goto L9;
        AccountFrozenAlert.show(r17);
        return;
    L9:
        StarsController r1 = StarsController.getInstance(r17, this.inputAmount.currency);
        if (r1.balanceAvailable() == false) goto L12;
        AmountUtils$Amount r12 = AmountUtils$Amount.of(r1.getBalance());
    L13:
        if (r12 != null) goto L15;
    L19:
        AmountUtils$Amount r5 = this.inputAmount;
        AmountUtils$Currency r0 = r5.currency;
        if (r0 != AmountUtils$Currency.STARS) goto L24;
        new StarsIntroActivity.StarsNeededSheet(r18, r19, r5.asDecimal(), 14, null, null, r20).show();
        return;
    L24:
        if (r0 != AmountUtils$Currency.TON) goto L27;
        new TONIntroActivity.StarsNeededSheet(r18, r19, r5, true, null).show();
        return;
    L27:
        return;
    L15:
        if (r12.asNano() < this.inputAmount.asNano()) goto L19;
        openConfirmAlert();
        return;
    L12:
        r12 = null;
        goto L13
    }

    private /* synthetic */ void lambda$openConfirmAlert$7(Browser.Progress r4, AlertDialog r5, TLRPC.Updates r6, TLRPC.TL_error r7) {
        Runnable r0 = this.closeParentSheet;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        r4.end();
        r5.dismiss();
        dismiss();
        BaseFragment r42 = LaunchActivity.getSafeLastFragment();
        if (r42 == null) goto L12;
        if (r6 == null) goto L10;
        BulletinFactory.of(r42).createSimpleBulletin(R.raw.forward, LocaleController.getString(R.string.GiftOfferSentTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftOfferSentText, new Object[]{this.giftName, DialogObject.getShortName(this.dialogId)}))).ignoreDetach().show();
        return;
    L10:
        BulletinFactory.of(r42).showForError(r7);
        return;
    }

    private /* synthetic */ void lambda$openConfirmAlert$8(Browser.Progress r9, AlertDialog r10, TLRPC.Updates r11, TLRPC.TL_error r12) {
        if (r11 == null) goto L5;
        if (r12 != null) goto L5;
        MessagesController.getInstance(this.currentAccount).processUpdates(r11, false);
    L5:
        AndroidUtilities.runOnUIThread(new GiftOfferSheet$$ExternalSyntheticLambda13(this, r9, r10, r11, r12));
    }

    private /* synthetic */ void lambda$openConfirmAlert$9(long r18, boolean r20, AmountUtils$Amount r21, long r22, AlertDialog r24, int r25) {
        if (r18 <= 0) goto L16;
        int r5 = this.currentAccount;
        AmountUtils$Currency r6 = AmountUtils$Currency.STARS;
        StarsController r52 = StarsController.getInstance(r5, r6);
        if (r52.balanceAvailable() == false) goto L7;
        AmountUtils$Amount r53 = AmountUtils$Amount.of(r52.getBalance());
    L8:
        if (r20 == false) goto L10;
        AmountUtils$Amount r62 = AmountUtils$Amount.fromDecimal(r18, r6);
    L11:
        if (r53 != null) goto L13;
    L14:
        new StarsIntroActivity.StarsNeededSheet(getContext(), this.resourcesProvider, r62.asDecimal(), 14, null, null, this.dialogId).show();
        return;
    L13:
        if (r53.asNano() >= r62.asNano()) goto L16;
    L10:
        r62 = AmountUtils$Amount.fromNano(this.inputAmount.asNano() + r21.asNano(), r6);
        goto L11
    L7:
        r53 = null;
    L16:
        Browser.Progress r54 = r24.makeButtonLoading(-1);
        r54.init();
        TL_payments.TL_sendStarGiftOffer r63 = new TL_payments.TL_sendStarGiftOffer();
        r63.price = this.inputAmount.toTl();
        r63.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r63.duration = this.selectedDuration;
        r63.slug = this.giftUnique.slug;
        r63.random_id = r22;
        if (r18 <= 0) goto L19;
        r63.flags |= 1;
        r63.allow_paid_stars = r18;
    L19:
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r63, new GiftOfferSheet$$ExternalSyntheticLambda12(this, r54, r24));
    }

    private /* synthetic */ void lambda$show$6() {
        AndroidUtilities.showKeyboard(this.starsCountEditField);
    }

    private void onCurrencyChanged(boolean r8) {
        HorizontalRoundTabsLayout r0 = this.currencyTabsView;
        if (r0 != null) goto L5;
    L9:
        String r02 = DialogObject.getShortName(this.dialogId);
        AmountUtils$Currency r3 = this.inputAmount.currency;
        AmountUtils$Currency r4 = AmountUtils$Currency.STARS;
        if (r3 != r4) goto L13;
        this.publishingTimeHint.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftOfferDurationInfoStars, new Object[]{r02})));
        this.starsCountEditField.setInputType(2);
        this.starsCountEditField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Long.toString(this.inputAmountLimits.getMax(this.inputAmount.currency).asDecimal()).length())});
    L15:
        ImageView r03 = this.iconStars;
        float r1 = 0.0f;
        if (r8 == false) goto L42;
        ViewPropertyAnimator r04 = r03.animate();
        if (this.inputAmount.currency != r4) goto L20;
        float r32 = 1.0f;
    L21:
        ViewPropertyAnimator r05 = r04.alpha(r32);
        if (this.inputAmount.currency != r4) goto L24;
        float r33 = 1.0f;
    L25:
        ViewPropertyAnimator r06 = r05.scaleX(r33);
        if (this.inputAmount.currency != r4) goto L28;
        float r34 = 1.0f;
    L29:
        r06.scaleY(r34).setDuration(180).start();
        ViewPropertyAnimator r07 = this.iconTon.animate();
        AmountUtils$Currency r5 = this.inputAmount.currency;
        AmountUtils$Currency r6 = AmountUtils$Currency.TON;
        if (r5 != r6) goto L32;
        float r52 = 1.0f;
    L33:
        ViewPropertyAnimator r08 = r07.alpha(r52);
        if (this.inputAmount.currency != r6) goto L36;
        float r53 = 1.0f;
    L37:
        ViewPropertyAnimator r09 = r08.scaleX(r53);
        if (this.inputAmount.currency != r6) goto L40;
        r1 = 1.0f;
    L40:
        r09.scaleY(r1).setDuration(180).start();
    L49:
        BalanceCloud r010 = this.balanceCloud;
        if (r010 == null) goto L53;
        r010.setCurrency(this.inputAmount.currency, r8);
        return;
    L53:
        return;
    L36:
        r53 = 0.0f;
        goto L37
    L32:
        r52 = 0.0f;
        goto L33
    L28:
        r34 = 0.0f;
        goto L29
    L24:
        r33 = 0.0f;
        goto L25
    L20:
        r32 = 0.0f;
        goto L21
    L42:
        if (this.inputAmount.currency != r4) goto L44;
        float r35 = 1.0f;
    L45:
        r03.setAlpha(r35);
        ImageView r011 = this.iconTon;
        if (this.inputAmount.currency != AmountUtils$Currency.TON) goto L48;
        r1 = 1.0f;
    L48:
        r011.setAlpha(r1);
        goto L49
    L44:
        r35 = 0.0f;
        goto L45
    L13:
        if (r3 != AmountUtils$Currency.TON) goto L15;
        this.publishingTimeHint.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftOfferDurationInfoTON, new Object[]{r02})));
        this.starsCountEditField.setInputType(8194);
        this.starsCountEditField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Long.toString(this.inputAmountLimits.getMax(this.inputAmount.currency).asDecimal()).length() + 3)});
        goto L15
    L5:
        if (this.inputAmount.currency != AmountUtils$Currency.STARS) goto L7;
        int r36 = 0;
    L8:
        r0.setSelectedIndex(r36, r8);
        goto L9
    L7:
        r36 = 1;
        goto L8
    }

    private void openConfirmAlert() {
        String r0 = this.inputAmount.asFormatString();
        if (this.inputAmount.currency != AmountUtils$Currency.TON) goto L5;
        boolean r2 = true;
    L6:
        LinearLayout r3 = new LinearLayout(getContext());
        r3.setOrientation(1);
        TextView r5 = new TextView(getContext());
        r5.setText(LocaleController.getString(R.string.GiftOfferConfirmSend));
        int r6 = Theme.key_dialogTextBlack;
        r5.setTextColor(Theme.getColor(r6, this.resourcesProvider));
        r5.setTextSize(1, 20.0f);
        r5.setTypeface(AndroidUtilities.bold());
        r3.addView(r5, LayoutHelper.createLinear(-1, -2, 48, 24, 4, 24, 14));
        TextView r52 = new TextView(getContext());
        r52.setTextColor(Theme.getColor(r6, this.resourcesProvider));
        r52.setTextSize(1, 16.0f);
        AmountUtils$Currency r62 = this.inputAmount.currency;
        AmountUtils$Currency r7 = AmountUtils$Currency.STARS;
        long r9 = this.dialogId;
        if (r62 != r7) goto L9;
        String r63 = LocaleController.formatString(R.string.GiftOfferTransferInfoTextStars, new Object[]{r0, DialogObject.getShortName(r9), this.giftName});
    L10:
        r52.setText(AndroidUtilities.replaceTags(r63));
        r3.addView(r52, LayoutHelper.createLinear(-1, -2, 48, 24, 4, 24, 4));
        TableView r53 = new TableView(getContext(), this.resourcesProvider);
        long r92 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(this.dialogId);
        AmountUtils$Amount r64 = AmountUtils$Amount.fromDecimal(r92, r7);
        r53.addRow(LocaleController.getString(R.string.GiftOfferRowOffer), StarsIntroActivity.replaceStarsWithPlain(r2, LocaleController.formatString(R.string.GiftOfferAmount, new Object[]{r0}), 0.8f));
        if (r92 <= 0) goto L13;
        r53.addRow(LocaleController.getString(R.string.GiftOfferRowFee), StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatString(R.string.GiftOfferAmount, new Object[]{r64.asFormatString()}), 0.8f));
    L13:
        r53.addRow(LocaleController.getString(R.string.GiftOfferRowDuration), LocaleController.formatPluralString("GiftOfferHours", this.selectedDuration / 3600, new Object[0]));
        r3.addView(r53, LayoutHelper.createLinear(-1, -2, 48, 23, 16, 23, 4));
        long r4 = SendMessagesHelper.getInstance(this.currentAccount).getNextRandomId();
        SpannableStringBuilder r12 = new SpannableStringBuilder();
        if (r92 != 0) goto L16;
        r12.append(StarsIntroActivity.replaceStars(r2, LocaleController.formatString(R.string.GiftOfferPay, new Object[]{r0})));
    L19:
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setView(r3).setPositiveButton(r12, new GiftOfferSheet$$ExternalSyntheticLambda10(this, r92, r2, r64, r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create().setShowStarsBalance(true).show();
        return;
    L16:
        if (r2 == false) goto L18;
        r12.append(LocaleController.formatSpannable(R.string.GiftOfferPayMulti, new Object[]{StarsIntroActivity.replaceStars(true, LocaleController.formatString(R.string.GiftOfferPayMultiPart, new Object[]{r0})), StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.GiftOfferPayMultiPart, new Object[]{r64.asFormatString()}))}));
        goto L19
    L18:
        r12.append(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.GiftOfferPay, new Object[]{AmountUtils$Amount.fromNano(this.inputAmount.asNano() + r64.asNano(), r7).asFormatString()})));
        goto L19
    L9:
        r63 = LocaleController.formatString(R.string.GiftOfferTransferInfoTextTON, new Object[]{r0, DialogObject.getShortName(r9), this.giftName});
        goto L10
    L5:
        r2 = false;
        goto L6
    }

    public static void openOfferAcceptAlert(BaseFragment r26, Context r27, Theme.ResourcesProvider r28, int r29, long r30, int r32, TLRPC.TL_messageActionStarGiftPurchaseOffer r33) {
        AmountUtils$Amount r6 = AmountUtils$Amount.ofSafe(r33.price);
        AmountUtils$Amount r7 = getAmountMinusFee(r29, r6);
        TL_stars.StarGift r5 = r33.gift;
        String r8 = r5.title + " #" + LocaleController.formatNumber(r5.num, ',');
        if (r30 < 0) goto L5;
        TLObject r11 = MessagesController.getInstance(r29).getUser(Long.valueOf(r30));
    L6:
        String r12 = r6.asFormatString();
        String r13 = r7.asFormatString();
        if (r6.currency != AmountUtils$Currency.TON) goto L9;
        boolean r14 = true;
    L10:
        LinearLayout r15 = new LinearLayout(r27);
        r15.setOrientation(1);
        r15.addView(new StarGiftSheet.GiftTransferTopView(r27, r5, r11), LayoutHelper.createLinear(-1, -2, 48, 0, -4, 0, 0));
        TextView r9 = new TextView(r27);
        r9.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r28));
        r9.setTextSize(1, 16.0f);
        if (r6.currency != AmountUtils$Currency.STARS) goto L13;
        String r3 = LocaleController.formatString(R.string.GiftOfferTransferInfoTextSellStars, new Object[]{r12, DialogObject.getShortName(r30), r8, r13});
    L14:
        r9.setText(AndroidUtilities.replaceTags(r3));
        r15.addView(r9, LayoutHelper.createLinear(-1, -2, 48, 24, 4, 24, 4));
        FrameLayout r34 = new FrameLayout(r27);
        r34.setClipChildren(false);
        r34.setClipToPadding(false);
        TableView r82 = new TableView(r27, r28);
        r34.addView(r82, LayoutHelper.createFrame(-1, -1, 119));
        StarGiftSheet.addAttributeRow(r82, StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributeModel.class));
        StarGiftSheet.addAttributeRow(r82, StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributeBackdrop.class));
        StarGiftSheet.addAttributeRow(r82, StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributePattern.class));
        r15.addView(r34, LayoutHelper.createLinear(-1, -2, 48, 23, 16, 23, 4));
        AmountUtils$Amount r35 = AmountUtils$Amount.fromUsd(r5.value_usd_amount / Math.pow(10.0d, BillingController.getInstance().getCurrencyExp("USD")), r6.currency);
        if (r35.asDouble() > 0.0d) goto L17;
    L30:
        new AlertDialog.Builder(r27, r28).setView(r15).setPositiveButton(StarsIntroActivity.replaceStars(r14, LocaleController.formatString(R.string.GiftOfferSellFor, new Object[]{r13})), new GiftOfferSheet$$ExternalSyntheticLambda0(r32, r29, r26)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create().show();
        return;
    L17:
        if (r5.value_usd_amount <= 0) goto L30;
        if (r35.asNano() < r7.asNano()) goto L23;
        int r36 = (int) Math.round((1.0d - (r7.asDouble() / r35.asDouble())) * 100.0d);
        SpannableStringBuilder r52 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftOfferAmountLowerHint2, new Object[]{r36 + "%", r5.title}));
        if (r36 <= 10) goto L24;
        boolean r92 = true;
    L25:
        TextView r37 = new TextView(r27);
        r37.setTextSize(1, 13.0f);
        r37.setGravity(17);
        r37.setText(r52);
        if (r92 == false) goto L28;
        int r4 = Theme.key_text_RedRegular;
    L29:
        r37.setTextColor(Theme.getColor(r4, r28));
        r15.addView(r37, LayoutHelper.createLinear(-1, -2, 49, 40, 12, 40, 9));
        goto L30
    L28:
        r4 = Theme.key_windowBackgroundWhiteGrayText;
    L24:
        r92 = false;
        goto L25
    L23:
        int r38 = (int) Math.round(((r7.asDouble() / r35.asDouble()) - 1.0d) * 100.0d);
        r52 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftOfferAmountHigherHint2, new Object[]{r38 + "%", r5.title}));
        goto L24
    L13:
        r3 = LocaleController.formatString(R.string.GiftOfferTransferInfoTextSellTON, new Object[]{r12, DialogObject.getShortName(r30), r8, r13});
        goto L14
    L9:
        r14 = false;
        goto L10
    L5:
        r11 = MessagesController.getInstance(r29).getChat(Long.valueOf(-r30));
        goto L6
    }

    private void setAmount(AmountUtils$Amount r9, boolean r10, boolean r11, boolean r12) {
        AmountUtils$Amount r0 = this.inputAmount;
        int r1 = this.inputAmountError;
        boolean r2 = false;
        this.inputAmountError = 0;
        if (r9 == null) goto L5;
        this.inputAmount = r9;
    L7:
        if (this.inputAmountLimits.getMax(this.inputAmount.currency).asNano() >= this.inputAmount.asNano()) goto L10;
        this.inputAmountError |= 4;
    L10:
        if (this.inputAmount.isZero() == false) goto L12;
    L14:
        if (r11 == false) goto L16;
    L19:
        boolean r92 = true;
    L20:
        if (r11 == false) goto L22;
    L25:
        boolean r02 = true;
    L26:
        if (r11 == false) goto L28;
    L29:
        r2 = true;
    L30:
        if (r92 == false) goto L32;
        onCurrencyChanged(r12);
    L32:
        if (r92 == true) goto L34;
        if (r2 == true) goto L34;
    L35:
        if (r92 == true) goto L38;
        if (r02 == true) goto L38;
        if (r2 == true) goto L38;
    L39:
        if (r92 == true) goto L41;
        if (r02 == true) goto L41;
    L42:
        if (r10 == false) goto L46;
        if (r02 == false) goto L47;
        String r93 = this.inputAmount.asDecimalString();
        this.starsCountEditField.setText(r93);
        this.starsCountEditField.setSelection(r93.length());
        return;
    L47:
        return;
    L46:
        return;
    L41:
        checkRateText(r12);
    L38:
        checkButtonOfferText(r12);
        checkButtonEnabled(r12);
    L34:
        checkAmountInputText(r12);
        checkAmountInputTextHint(r12);
        goto L35
    L28:
        if (r1 == this.inputAmountError) goto L30;
    L22:
        if (r0.asNano() != this.inputAmount.asNano()) goto L25;
        r02 = false;
        goto L26
    L16:
        if (r0.currency != this.inputAmount.currency) goto L19;
        r92 = false;
        goto L20
    L12:
        if (this.inputAmountLimits.getMin(this.inputAmount.currency).asNano() <= this.inputAmount.asNano()) goto L14;
        this.inputAmountError |= 2;
        goto L14
    L5:
        this.inputAmount = AmountUtils$Amount.fromNano(0, r0.currency);
        this.inputAmountError |= 1;
        goto L7
    }

    private void setSelectedDuration(int r4, boolean r5) {
        if (this.selectedDuration == r4) goto L5;
        this.selectedDuration = r4;
        this.publishingTimeField.setText(LocaleController.formatPluralString("GiftOfferHours", r4 / 3600, new Object[0]));
    L5:
        checkButtonEnabled(r5);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new GiftOfferSheet$$ExternalSyntheticLambda8(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.GiftOfferToBuyTitle);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean isTouchOutside(float r3, float r4) {
        if (this.balanceCloudVisible == false) goto L17;
        BalanceCloud r0 = this.balanceCloud;
        if (r0 == null) goto L17;
        if (r3 < r0.getX()) goto L17;
        if (r3 > (this.balanceCloud.getX() + this.balanceCloud.getWidth())) goto L17;
        if (r4 < this.balanceCloud.getY()) goto L17;
        if (r4 > (this.balanceCloud.getY() + this.balanceCloud.getHeight())) goto L17;
        return false;
    L17:
        return super.isTouchOutside(r3, r4);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerTranslationYChanged(float r1) {
        super.onContainerTranslationYChanged(r1);
        checkBalanceCloudVisibility();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        this.isFullyVisible = true;
        checkBalanceCloudVisibility();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        AndroidUtilities.runOnUIThread(new GiftOfferSheet$$ExternalSyntheticLambda7(this), 50);
    }
}
