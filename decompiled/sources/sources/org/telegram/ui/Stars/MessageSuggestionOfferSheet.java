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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageSuggestionParams;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.HorizontalRoundTabsLayout;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.TON.TONIntroActivity;

/* loaded from: classes7.dex */
public class MessageSuggestionOfferSheet extends BottomSheet {
    private final BalanceCloud balanceCloud;
    private boolean balanceCloudVisible;
    private final ButtonWithCounterView buttonView;
    private final HorizontalRoundTabsLayout currencyTabsView;
    private final AnimatedTextView dollarsEqView;
    private final ImageView iconStars;
    private final ImageView iconTon;
    private AmountUtils$Amount inputAmount;
    private int inputAmountError;
    private final AmountUtils$Amount inputAmountMaxStars;
    private final AmountUtils$Amount inputAmountMaxTON;
    private final AmountUtils$Amount inputAmountMinStars;
    private final AmountUtils$Amount inputAmountMinTON;
    private boolean isFullyVisible;
    private final boolean isMonoForumAdmin;
    private final int mode;
    private final EditTextBoldCursor publishingTimeField;
    private long selectedTime;
    private final ColoredImageSpan[] spanRefStars;
    private final ColoredImageSpan[] spanRefTon;
    private final EditTextBoldCursor starsCountEditField;
    private final TextView starsCountEditHint;
    private final OutlineTextContainerView starsCountEditOutline;

    /* renamed from: org.telegram.ui.Stars.MessageSuggestionOfferSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        final /* synthetic */ MessageSuggestionOfferSheet this$0;

        public AnonymousClass1(MessageSuggestionOfferSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stars.MessageSuggestionOfferSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ MessageSuggestionOfferSheet this$0;

        public AnonymousClass2(MessageSuggestionOfferSheet r1) {
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
            AmountUtils$Amount r82 = AmountUtils$Amount.fromDecimal(r8.toString(), MessageSuggestionOfferSheet.m18902$$Nest$fgetinputAmount(this.this$0).currency);
        L20:
            MessageSuggestionOfferSheet.m18905$$Nest$msetAmount(this.this$0, r82, false, false, true);
            MessageSuggestionOfferSheet.m18904$$Nest$fgetstarsCountEditOutline(this.this$0).animateSelection(MessageSuggestionOfferSheet.m18903$$Nest$fgetstarsCountEditField(this.this$0).isFocused(), !TextUtils.isEmpty(MessageSuggestionOfferSheet.m18903$$Nest$fgetstarsCountEditField(this.this$0).getText()));
            return;
        L19:
            r82 = AmountUtils$Amount.fromNano(0, MessageSuggestionOfferSheet.m18902$$Nest$fgetinputAmount(this.this$0).currency);
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

    public static /* synthetic */ void $r8$lambda$1qEgzhMkm8fBXeV8JVsez8Dh31Q(MessageSuggestionOfferSheet r0, boolean r1, int r2, int r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$2bOnrsbL3YDVAfxyj5Upff_CoBo(MessageSuggestionOfferSheet r0, View r1, boolean r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4ps7ik52eEha18cz1Zq8vRbgCPU(MessageSuggestionOfferSheet r0, ChatActivity r1, int r2, Context r3, Theme.ResourcesProvider r4, long r5, Utilities.Callback r7, View r8) {
        r0.lambda$new$6(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$5QsL1X16xfBV8miPys7okcfBybk(MessageSuggestionOfferSheet r0, Context r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Lv6OqVzLUEp2vL_4rGc9atuzKQw(MessageSuggestionOfferSheet r0, Context r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Sqfbq2EjzSxrVUr-JtD0kLxMAfc, reason: not valid java name */
    public static /* synthetic */ void m18900$r8$lambda$Sqfbq2EjzSxrVUrJtD0kLxMAfc(MessageSuggestionOfferSheet r0) {
        r0.lambda$show$7();
    }

    /* renamed from: $r8$lambda$TRyLRViHMg-AQFRmVo-qiunBx0I, reason: not valid java name */
    public static /* synthetic */ void m18901$r8$lambda$TRyLRViHMgAQFRmVoqiunBx0I(MessageSuggestionOfferSheet r0, int r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$TqfroI0JqNStzAQWAefBa8G5bAc(MessageSuggestionOfferSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetinputAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AmountUtils$Amount m18902$$Nest$fgetinputAmount(MessageSuggestionOfferSheet r0) {
        return r0.inputAmount;
    }

    /* renamed from: -$$Nest$fgetstarsCountEditField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m18903$$Nest$fgetstarsCountEditField(MessageSuggestionOfferSheet r0) {
        return r0.starsCountEditField;
    }

    /* renamed from: -$$Nest$fgetstarsCountEditOutline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OutlineTextContainerView m18904$$Nest$fgetstarsCountEditOutline(MessageSuggestionOfferSheet r0) {
        return r0.starsCountEditOutline;
    }

    /* renamed from: -$$Nest$msetAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18905$$Nest$msetAmount(MessageSuggestionOfferSheet r0, AmountUtils$Amount r1, boolean r2, boolean r3, boolean r4) {
        r0.setAmount(r1, r2, r3, r4);
    }

    public MessageSuggestionOfferSheet(Context r34, int r35, long r36, MessageSuggestionParams r38, ChatActivity r39, Theme.ResourcesProvider r40, int r41, Utilities.Callback<MessageSuggestionParams> r42) {
        super(r34, true, r40);
        this.selectedTime = -1;
        this.spanRefStars = new ColoredImageSpan[1];
        this.spanRefTon = new ColoredImageSpan[1];
        this.mode = r41;
        this.waitingKeyboard = true;
        this.smoothKeyboardAnimationEnabled = true;
        boolean r0 = ChatObject.canManageMonoForum(r35, r36);
        this.isMonoForumAdmin = r0;
        if (r0 == false) goto L5;
    L8:
        boolean r2 = true;
    L9:
        AppGlobalConfig r3 = MessagesController.getInstance(r35).config;
        long r6 = r3.tonSuggestedPostAmountMin.get();
        AmountUtils$Currency r8 = AmountUtils$Currency.TON;
        this.inputAmountMinTON = AmountUtils$Amount.fromNano(r6, r8);
        this.inputAmountMaxTON = AmountUtils$Amount.fromNano(r3.tonSuggestedPostAmountMax.get(), r8);
        long r62 = r3.starsSuggestedPostAmountMin.get();
        AmountUtils$Currency r13 = AmountUtils$Currency.STARS;
        this.inputAmountMinStars = AmountUtils$Amount.fromDecimal(r62, r13);
        this.inputAmountMaxStars = AmountUtils$Amount.fromDecimal(r3.starsSuggestedPostAmountMax.get(), r13);
        if (r0 == true) goto L12;
        BalanceCloud r02 = new BalanceCloud(r34, r35, r40);
        this.balanceCloud = r02;
        r02.setScaleX(0.6f);
        r02.setScaleY(0.6f);
        r02.setAlpha(0.0f);
        r02.setEnabled(false);
        r02.setClickable(false);
        this.container.addView(r02, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 48.0f, 0.0f, 0.0f));
        ScaleStateListAnimator.apply(r02);
        r02.setOnClickListener(new MessageSuggestionOfferSheet$$ExternalSyntheticLambda0(this, r34, r40));
    L13:
        fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r40));
        LinearLayout r14 = new LinearLayout(r34);
        r14.setOrientation(1);
        LinearLayout r03 = new LinearLayout(r34);
        r03.setOrientation(0);
        r14.addView(r03, LayoutHelper.createLinear(-1, 56, 55, 0, 0, 0, 0));
        TextView r7 = new TextView(r34);
        int r82 = Theme.key_windowBackgroundWhiteBlackText;
        r7.setTextColor(Theme.getColor(r82));
        r7.setTextSize(1, 20.0f);
        r7.setGravity(NavigationBarView.ITEM_GRAVITY_START_CENTER);
        if (r41 != 0) goto L16;
        int r15 = R.string.PostSuggestionsOfferTitle;
    L17:
        r7.setText(LocaleController.getString(r15));
        r7.setTypeface(AndroidUtilities.bold());
        r7.setEllipsize(TextUtils.TruncateAt.END);
        r03.addView(r7, LayoutHelper.createLinear(-1, -1, 1.0f, 119, 22, 0, 22, 0));
        ImageView r72 = new ImageView(r34);
        r72.setScaleType(ImageView.ScaleType.CENTER);
        r72.setImageResource(R.drawable.ic_close_white);
        int r12 = Theme.key_dialogEmptyImage;
        int r11 = Theme.getColor(r12, r40);
        PorterDuff.Mode r32 = PorterDuff.Mode.SRC_IN;
        r72.setColorFilter(new PorterDuffColorFilter(r11, r32));
        ScaleStateListAnimator.apply(r72);
        r72.setOnClickListener(new MessageSuggestionOfferSheet$$ExternalSyntheticLambda1(this));
        r03.addView(r72, LayoutHelper.createLinear(48, 48, 0.0f, 21, 0, 0, 6, 0));
        EditTextBoldCursor r112 = new EditTextBoldCursor(r34);
        this.starsCountEditField = r112;
        if (r2 == false) goto L20;
        HorizontalRoundTabsLayout r04 = new HorizontalRoundTabsLayout(r34, r40);
        this.currencyTabsView = r04;
        ArrayList<CharSequence> r22 = new ArrayList();
        r22.add(LocaleController.getString(R.string.SuggestedOfferStars));
        r22.add(LocaleController.getString(R.string.SuggestedOfferTON));
        r04.setTabs(r22, new MessageSuggestionOfferSheet$$ExternalSyntheticLambda2(this));
        r14.addView(r04, LayoutHelper.createLinear(-1, -2, 18.0f, 0.0f, 18.0f, 12.0f));
    L21:
        LinearLayout r05 = new LinearLayout(r34);
        r05.setOrientation(1);
        r14.addView(r05, LayoutHelper.createLinear(-1, -2, 1.0f));
        OutlineTextContainerView r23 = new OutlineTextContainerView(r34);
        this.starsCountEditOutline = r23;
        r112.setCursorSize(AndroidUtilities.dp(20.0f));
        r112.setCursorWidth(1.5f);
        r112.setImeOptions(268435462);
        r112.setTextSize(1, 17.0f);
        r112.setMaxLines(1);
        r112.setBackground(null);
        r112.setPadding(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r112.setTextColor(Theme.getColor(r82));
        r112.requestFocus();
        r23.setLeftPadding(AndroidUtilities.dp(28.0f));
        r23.attachEditText(r112);
        AmountUtils$Amount r63 = r38.amount;
        if (r63 != null) goto L24;
    L27:
        boolean r64 = false;
    L28:
        r23.animateSelection(true, r64, false);
        r23.setForceUseCenter2(true);
        r112.setOnFocusChangeListener(new MessageSuggestionOfferSheet$$ExternalSyntheticLambda3(this));
        r23.addView(r112, LayoutHelper.createFrame(-1, -2, 48));
        r05.addView(r23, LayoutHelper.createLinear(-1, 58, 18.0f, 0.0f, 18.0f, 0.0f));
        ImageView r65 = new ImageView(r34);
        this.iconStars = r65;
        r65.setImageResource(R.drawable.star_small_inner);
        r23.addView(r65, LayoutHelper.createFrame(22, 22.0f, 19, 14.0f, 0.0f, 0.0f, 0.0f));
        ImageView r66 = new ImageView(r34);
        this.iconTon = r66;
        r66.setImageResource(R.drawable.mini_gram_72);
        r66.setColorFilter(-13397548);
        r23.addView(r66, LayoutHelper.createFrame(22, 22.0f, 19, 14.0f, 0.0f, 0.0f, 0.0f));
        AnimatedTextView r67 = new AnimatedTextView(r34);
        this.dollarsEqView = r67;
        int r73 = Theme.key_windowBackgroundWhiteGrayText;
        r67.setTextColor(Theme.getColor(r73));
        r67.setTextSize(AndroidUtilities.dp(13.0f));
        r67.setGravity(5);
        r23.addView(r67, LayoutHelper.createFrame(-2, -1.0f, 21, 0.0f, 0.0f, 16.0f, 0.0f));
        TextView r24 = new TextView(r34);
        this.starsCountEditHint = r24;
        r24.setTextColor(Theme.getColor(r73));
        r24.setTextSize(1, 13.0f);
        r05.addView(r24, LayoutHelper.createLinear(-1, -2, 55, 33, 4, 33, 0));
        AnonymousClass1 r25 = new AnonymousClass1(this, r34);
        this.publishingTimeField = r25;
        r25.setCursorSize(AndroidUtilities.dp(20.0f));
        r25.setCursorWidth(1.5f);
        r25.setTextSize(1, 17.0f);
        r25.setMaxLines(1);
        r25.setBackground(null);
        r25.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r25.setTextColor(Theme.getColor(r82));
        r25.setFocusable(false);
        r25.setClickable(false);
        r25.setEnabled(false);
        OutlineTextContainerView r68 = new OutlineTextContainerView(r34);
        r68.setText(LocaleController.getString(R.string.PostSuggestionsOfferTitleTime));
        r68.attachEditText(r25);
        r68.addView(r25, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, 0.0f, 48.0f, 0.0f));
        ScaleStateListAnimator.apply(r68, 0.02f, 1.2f);
        r68.setOnClickListener(new MessageSuggestionOfferSheet$$ExternalSyntheticLambda4(this, r34, r40));
        r05.addView(r68, LayoutHelper.createLinear(-1, 58, 18.0f, 24.0f, 18.0f, 0.0f));
        ImageView r26 = new ImageView(r34);
        r26.setImageResource(R.drawable.arrow_more);
        r26.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r12, r40), r32));
        r68.addView(r26, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 14.0f, 0.0f));
        TextView r27 = new TextView(r34);
        r27.setTextColor(Theme.getColor(r73));
        r27.setTextSize(1, 13.0f);
        SpannableStringBuilder r33 = new SpannableStringBuilder();
        r33.append(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PostSuggestionsAddTimeHint)));
        r33.append(' ');
        r33.append(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.PostSuggestionsAddTimeHint2, new Object[]{Long.valueOf(MessagesController.getInstance(r35).config.starsSuggestedPostAgeMin.get(TimeUnit.HOURS))})));
        r27.setText(r33);
        r05.addView(r27, LayoutHelper.createLinear(-1, -2, 55, 33, 4, 33, 24));
        LinearLayout r122 = new LinearLayout(r34);
        r122.setOrientation(1);
        r14.addView(r122, LayoutHelper.createLinear(-1, -2, 80));
        ButtonWithCounterView r06 = new ButtonWithCounterView(r34, r40);
        this.buttonView = r06;
        r06.setOnClickListener(new MessageSuggestionOfferSheet$$ExternalSyntheticLambda5(this, r39, r35, r34, r40, r36, r42));
        if (r41 != 1) goto L31;
        r06.setText(LocaleController.getString(R.string.PostSuggestionsOfferChangeUpdateTerms), false);
    L31:
        r122.addView(r06, LayoutHelper.createLinear(-1, 48, 18.0f, 0.0f, 18.0f, 8.0f));
        AmountUtils$Amount r07 = r38.amount;
        if (r07 == null) goto L34;
        boolean r74 = false;
        setAmount(AmountUtils$Amount.fromNano(r07.asNano(), r38.amount.currency), !r38.amount.isZero(), true, false);
    L35:
        setSelectedTime(r38.time, r74);
        setCustomView(r14);
        r112.addTextChangedListener(new AnonymousClass2(this));
        return;
    L34:
        r74 = false;
        setAmount(AmountUtils$Amount.fromNano(0, r13), false, true, false);
        goto L35
    L24:
        if (r63.isZero() == true) goto L27;
        r64 = true;
        goto L28
    L20:
        this.currencyTabsView = null;
        goto L21
    L16:
        r15 = R.string.PostSuggestionsOfferChangeTitle;
        goto L17
    L12:
        this.balanceCloud = null;
        goto L13
    L5:
        if (StarsController.getTonInstance(r35).canUseTon() == true) goto L8;
        r2 = false;
        goto L9
    }

    private void checkAmountInputText(boolean r2) {
        int r22 = this.inputAmountError;
        if ((r22 & 4) == 0) goto L7;
        this.starsCountEditOutline.setText(LocaleController.formatString(R.string.SuggestAPostTooMuch, new Object[]{getInputAmountMax().formatAsDecimalSpaced()}));
        return;
    L7:
        if ((r22 & 2) == 0) goto L11;
        this.starsCountEditOutline.setText(LocaleController.formatString(R.string.SuggestAPostTooSmall, new Object[]{getInputAmountMin().formatAsDecimalSpaced()}));
        return;
    L11:
        if (this.inputAmount.currency != AmountUtils$Currency.STARS) goto L13;
        int r23 = R.string.PostSuggestionsOfferTitlePriceStars;
    L14:
        this.starsCountEditOutline.setText(LocaleController.getString(r23));
        return;
    L13:
        r23 = R.string.PostSuggestionsOfferTitlePriceTON;
        goto L14
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
    L9:
        boolean r0 = false;
    L11:
        if (this.buttonView.isEnabled() == r0) goto L23;
        this.buttonView.setEnabled(r0);
        this.buttonView.setClickable(r0);
        ButtonWithCounterView r4 = this.buttonView;
        float r1 = 0.6f;
        if (r5 == false) goto L19;
        ViewPropertyAnimator r42 = r4.animate();
        if (r0 == false) goto L17;
        r1 = 1.0f;
    L17:
        r42.alpha(r1).setDuration(180).start();
        return;
    L19:
        if (r0 == false) goto L21;
        r1 = 1.0f;
    L21:
        r4.setAlpha(r1);
        return;
    L23:
        return;
    L5:
        if (this.inputAmount.asNano() < 0) goto L7;
    L8:
        r0 = true;
        goto L11
    L7:
        if (this.selectedTime <= 0) goto L9;
        goto L8
    }

    private void checkButtonOfferText(boolean r7) {
        if (this.mode == 0) goto L5;
        this.buttonView.setText(LocaleController.getString(R.string.PostSuggestionsOfferChangeUpdateTerms), r7);
        return;
    L5:
        if (this.inputAmount.isZero() == true) goto L20;
        AmountUtils$Amount r0 = this.inputAmount;
        if (r0.currency != AmountUtils$Currency.TON) goto L9;
        boolean r1 = true;
    L10:
        ButtonWithCounterView r2 = this.buttonView;
        int r3 = R.string.PostSuggestionsOfferStars;
        if (r1 == false) goto L13;
        String r02 = r0.asDecimalString();
    L14:
        String r03 = LocaleController.formatString(r3, new Object[]{r02});
        if (r1 == false) goto L17;
        ColoredImageSpan[] r6 = this.spanRefTon;
    L18:
        r2.setText(StarsIntroActivity.replaceStars(r1, r03, r6), r7);
        return;
    L17:
        r6 = this.spanRefStars;
        goto L18
    L13:
        r02 = LocaleController.formatNumber(r0.asDecimal(), ',');
        goto L14
    L9:
        r1 = false;
        goto L10
    L20:
        this.buttonView.setText(LocaleController.getString(R.string.PostSuggestionsOfferForFree), r7);
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

    public static String formatDateTime(long r2) {
        if (r2 <= 0) goto L5;
        String r22 = LocaleController.formatDateTime(r2, true);
        if (r22.isEmpty() == false) goto L9;
        return r22;
    L9:
        return Character.toUpperCase(r22.charAt(0)) + r22.substring(1);
    L5:
        return LocaleController.getString(R.string.PostSuggestionsAnytime);
    }

    private AmountUtils$Amount getInputAmountMax() {
        if (this.inputAmount.currency != AmountUtils$Currency.TON) goto L7;
        return this.inputAmountMaxTON;
    L7:
        return this.inputAmountMaxStars;
    }

    private AmountUtils$Amount getInputAmountMin() {
        if (this.inputAmount.currency != AmountUtils$Currency.TON) goto L7;
        return this.inputAmountMinTON;
    L7:
        return this.inputAmountMinStars;
    }

    private /* synthetic */ void lambda$new$0(Context r1, Theme.ResourcesProvider r2, View r3) {
        if (this.inputAmount.currency != AmountUtils$Currency.STARS) goto L6;
        new StarsIntroActivity.StarsOptionsSheet(r1, r2).show();
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$2(int r3) {
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

    private /* synthetic */ void lambda$new$3(View r1, boolean r2) {
        this.starsCountEditOutline.animateSelection(r2, !TextUtils.isEmpty(this.starsCountEditField.getText()));
    }

    private /* synthetic */ void lambda$new$4(boolean r1, int r2, int r3) {
        if (r1 == false) goto L5;
        setSelectedTime(r2, true);
        return;
    }

    private /* synthetic */ void lambda$new$5(Context r7, Theme.ResourcesProvider r8, View r9) {
        AlertsCreator.createSuggestedMessageDatePickerDialog(r7, this.selectedTime, new MessageSuggestionOfferSheet$$ExternalSyntheticLambda7(this), r8, 0).show();
    }

    private /* synthetic */ void lambda$new$6(ChatActivity r18, int r19, Context r20, Theme.ResourcesProvider r21, long r22, Utilities.Callback r24, View r25) {
        if (r18 != null) goto L5;
        return;
    L5:
        if (this.buttonView.isEnabled() == true) goto L8;
        return;
    L8:
        if (MessagesController.getInstance(r19).isFrozen() == false) goto L11;
        AccountFrozenAlert.show(r19);
        return;
    L11:
        StarsController r2 = StarsController.getInstance(r19, this.inputAmount.currency);
        if (r2.balanceAvailable() == false) goto L14;
        AmountUtils$Amount r23 = AmountUtils$Amount.of(r2.getBalance());
    L16:
        if (this.isMonoForumAdmin == true) goto L28;
        if (r23 != null) goto L19;
    L20:
        AmountUtils$Amount r6 = this.inputAmount;
        AmountUtils$Currency r0 = r6.currency;
        if (r0 != AmountUtils$Currency.STARS) goto L25;
        new StarsIntroActivity.StarsNeededSheet(r20, r21, r6.asDecimal(), 13, ForumUtilities.getMonoForumTitle(r19, r22, true), null, r22).show();
        return;
    L25:
        if (r0 != AmountUtils$Currency.TON) goto L31;
        new TONIntroActivity.StarsNeededSheet(r20, r21, r6, true, null).show();
        return;
    L31:
        return;
    L19:
        if (r23.asNano() < this.inputAmount.asNano()) goto L20;
    L28:
        r24.run(MessageSuggestionParams.of(this.inputAmount, this.selectedTime));
        dismiss();
        return;
    L14:
        r23 = null;
        goto L16
    }

    private /* synthetic */ void lambda$show$7() {
        AndroidUtilities.showKeyboard(this.starsCountEditField);
    }

    private void onCurrencyChanged(boolean r8) {
        HorizontalRoundTabsLayout r0 = this.currencyTabsView;
        if (r0 != null) goto L5;
    L9:
        AmountUtils$Currency r02 = this.inputAmount.currency;
        AmountUtils$Currency r3 = AmountUtils$Currency.STARS;
        if (r02 != r3) goto L13;
        this.starsCountEditHint.setText(LocaleController.getString(R.string.PostSuggestionsOfferSubtitleStars));
        this.starsCountEditField.setInputType(2);
        this.starsCountEditField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Long.toString(getInputAmountMax().asDecimal()).length())});
    L15:
        ImageView r03 = this.iconStars;
        float r1 = 0.0f;
        if (r8 == false) goto L42;
        ViewPropertyAnimator r04 = r03.animate();
        if (this.inputAmount.currency != r3) goto L20;
        float r4 = 1.0f;
    L21:
        ViewPropertyAnimator r05 = r04.alpha(r4);
        if (this.inputAmount.currency != r3) goto L24;
        float r42 = 1.0f;
    L25:
        ViewPropertyAnimator r06 = r05.scaleX(r42);
        if (this.inputAmount.currency != r3) goto L28;
        float r32 = 1.0f;
    L29:
        r06.scaleY(r32).setDuration(180).start();
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
        r32 = 0.0f;
        goto L29
    L24:
        r42 = 0.0f;
        goto L25
    L20:
        r4 = 0.0f;
        goto L21
    L42:
        if (this.inputAmount.currency != r3) goto L44;
        float r33 = 1.0f;
    L45:
        r03.setAlpha(r33);
        ImageView r011 = this.iconTon;
        if (this.inputAmount.currency != AmountUtils$Currency.TON) goto L48;
        r1 = 1.0f;
    L48:
        r011.setAlpha(r1);
        goto L49
    L44:
        r33 = 0.0f;
        goto L45
    L13:
        if (r02 != AmountUtils$Currency.TON) goto L15;
        this.starsCountEditHint.setText(LocaleController.getString(R.string.PostSuggestionsOfferSubtitleTON));
        this.starsCountEditField.setInputType(8194);
        this.starsCountEditField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Long.toString(getInputAmountMax().asDecimal()).length() + 3)});
        goto L15
    L5:
        if (this.inputAmount.currency != AmountUtils$Currency.STARS) goto L7;
        int r34 = 0;
    L8:
        r0.setSelectedIndex(r34, r8);
        goto L9
    L7:
        r34 = 1;
        goto L8
    }

    private void setAmount(AmountUtils$Amount r9, boolean r10, boolean r11, boolean r12) {
        AmountUtils$Amount r0 = this.inputAmount;
        int r1 = this.inputAmountError;
        boolean r2 = false;
        this.inputAmountError = 0;
        if (r9 == null) goto L5;
        this.inputAmount = r9;
    L7:
        if (getInputAmountMax().asNano() >= this.inputAmount.asNano()) goto L10;
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
        if (r2 == false) goto L36;
        OutlineTextContainerView r112 = this.starsCountEditOutline;
        if ((this.inputAmountError & (-9)) != 0) goto L34;
        float r13 = 0.0f;
    L35:
        r112.animateError(r13);
        goto L36
    L34:
        r13 = 1.0f;
    L36:
        if (r92 == false) goto L38;
        onCurrencyChanged(r12);
    L38:
        if (r92 == true) goto L40;
        if (r2 == true) goto L40;
    L41:
        if (r92 == true) goto L44;
        if (r02 == true) goto L44;
        if (r2 == true) goto L44;
    L45:
        if (r92 == true) goto L47;
        if (r02 == true) goto L47;
    L48:
        if (r10 == false) goto L52;
        if (r02 == false) goto L53;
        String r93 = this.inputAmount.asDecimalString();
        this.starsCountEditField.setText(r93);
        this.starsCountEditField.setSelection(r93.length());
        return;
    L53:
        return;
    L52:
        return;
    L47:
        checkRateText(r12);
    L44:
        checkButtonOfferText(r12);
        checkButtonEnabled(r12);
    L40:
        checkAmountInputText(r12);
        goto L41
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
        if (getInputAmountMin().asNano() <= this.inputAmount.asNano()) goto L14;
        this.inputAmountError |= 2;
        goto L14
    L5:
        this.inputAmount = AmountUtils$Amount.fromNano(0, r0.currency);
        this.inputAmountError |= 1;
        goto L7
    }

    private void setSelectedTime(long r3, boolean r5) {
        if (this.selectedTime == r3) goto L5;
        this.selectedTime = r3;
        this.publishingTimeField.setText(formatDateTime(r3));
    L5:
        checkButtonEnabled(r5);
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
        AndroidUtilities.runOnUIThread(new MessageSuggestionOfferSheet$$ExternalSyntheticLambda6(this), 50);
    }
}
