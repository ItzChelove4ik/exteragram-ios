package org.telegram.ui.Stars;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationBarView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckbox2Cell;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class SellGiftEnterPriceSheet extends BottomSheet {
    private final ButtonWithCounterView buttonView;
    private final AnimatedTextView dollarsEqView;
    private final ImageView iconStars;
    private final ImageView iconTon;
    private AmountUtils$Amount inputAmount;
    private int inputAmountError;
    private final AmountUtils$Amount inputAmountMaxStars;
    private final AmountUtils$Amount inputAmountMaxTON;
    private final AmountUtils$Amount inputAmountMinStars;
    private final AmountUtils$Amount inputAmountMinTON;
    private final TextCheckbox2Cell radioButtonCell;
    private final EditTextBoldCursor starsCountEditField;
    private final TextView starsCountEditHint;
    private final OutlineTextContainerView starsCountEditOutline;
    private final AnimatedTextView titleView;

    /* renamed from: org.telegram.ui.Stars.SellGiftEnterPriceSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ SellGiftEnterPriceSheet this$0;

        public AnonymousClass1(SellGiftEnterPriceSheet r1) {
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
            AmountUtils$Amount r82 = AmountUtils$Amount.fromDecimal(r8.toString(), SellGiftEnterPriceSheet.m18906$$Nest$fgetinputAmount(this.this$0).currency);
        L20:
            SellGiftEnterPriceSheet.m18909$$Nest$msetAmount(this.this$0, r82, false, false, true);
            SellGiftEnterPriceSheet.m18908$$Nest$fgetstarsCountEditOutline(this.this$0).animateSelection(SellGiftEnterPriceSheet.m18907$$Nest$fgetstarsCountEditField(this.this$0).isFocused(), !TextUtils.isEmpty(SellGiftEnterPriceSheet.m18907$$Nest$fgetstarsCountEditField(this.this$0).getText()));
            return;
        L19:
            r82 = AmountUtils$Amount.fromNano(0, SellGiftEnterPriceSheet.m18906$$Nest$fgetinputAmount(this.this$0).currency);
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

    public static /* synthetic */ void $r8$lambda$2_dJV7pSlhNDR9SNP0Uz54XE13w(SellGiftEnterPriceSheet r0, View r1, boolean r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GJZCc8xUoRyDA6p2P9RWmsseUJU(SellGiftEnterPriceSheet r0) {
        r0.lambda$show$3();
    }

    public static /* synthetic */ void $r8$lambda$Wmo4IjO62yLtT9XTqPyYMzttf6Q(SellGiftEnterPriceSheet r0, Utilities.Callback r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$inwXGGXdDFN7SAgFd3MiqdBg_T0(SellGiftEnterPriceSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetinputAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AmountUtils$Amount m18906$$Nest$fgetinputAmount(SellGiftEnterPriceSheet r0) {
        return r0.inputAmount;
    }

    /* renamed from: -$$Nest$fgetstarsCountEditField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m18907$$Nest$fgetstarsCountEditField(SellGiftEnterPriceSheet r0) {
        return r0.starsCountEditField;
    }

    /* renamed from: -$$Nest$fgetstarsCountEditOutline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OutlineTextContainerView m18908$$Nest$fgetstarsCountEditOutline(SellGiftEnterPriceSheet r0) {
        return r0.starsCountEditOutline;
    }

    /* renamed from: -$$Nest$msetAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18909$$Nest$msetAmount(SellGiftEnterPriceSheet r0, AmountUtils$Amount r1, boolean r2, boolean r3, boolean r4) {
        r0.setAmount(r1, r2, r3, r4);
    }

    public SellGiftEnterPriceSheet(Context r26, Theme.ResourcesProvider r27, int r28, AmountUtils$Amount r29, Utilities.Callback<AmountUtils$Amount> r30) {
        super(r26, true, r27);
        this.currentAccount = r28;
        this.smoothKeyboardAnimationEnabled = true;
        this.waitingKeyboard = true;
        AppGlobalConfig r5 = MessagesController.getInstance(r28).config;
        long r6 = Math.max(r5.tonStarGiftResaleAmountMin.get(), 10000000);
        AmountUtils$Currency r8 = AmountUtils$Currency.TON;
        this.inputAmountMinTON = AmountUtils$Amount.fromNano(r6, r8);
        this.inputAmountMaxTON = AmountUtils$Amount.fromNano(r5.tonStarGiftResaleAmountMax.get(), r8);
        long r62 = r5.starsStarGiftResaleAmountMin.get();
        AmountUtils$Currency r82 = AmountUtils$Currency.STARS;
        this.inputAmountMinStars = AmountUtils$Amount.fromDecimal(r62, r82);
        this.inputAmountMaxStars = AmountUtils$Amount.fromDecimal(r5.starsStarGiftResaleAmountMax.get(), r82);
        fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r27));
        LinearLayout r52 = new LinearLayout(r26);
        r52.setOrientation(1);
        LinearLayout r63 = new LinearLayout(r26);
        r63.setOrientation(0);
        r52.addView(r63, LayoutHelper.createLinear(-1, 56, 55, 0, 0, 0, 0));
        AnimatedTextView r9 = new AnimatedTextView(r26);
        this.titleView = r9;
        int r10 = Theme.key_windowBackgroundWhiteBlackText;
        r9.setTextColor(Theme.getColor(r10));
        r9.setTextSize(AndroidUtilities.dp(20.0f));
        r9.setGravity(NavigationBarView.ITEM_GRAVITY_START_CENTER);
        r9.setTypeface(AndroidUtilities.bold());
        r63.addView(r9, LayoutHelper.createLinear(-1, -1, 1.0f, 119, 22, 0, 22, 0));
        LinearLayout r64 = new LinearLayout(r26);
        r64.setOrientation(1);
        r52.addView(r64, LayoutHelper.createLinear(-1, -2, 1.0f));
        OutlineTextContainerView r92 = new OutlineTextContainerView(r26);
        this.starsCountEditOutline = r92;
        EditTextBoldCursor r14 = new EditTextBoldCursor(r26);
        this.starsCountEditField = r14;
        r14.setCursorSize(AndroidUtilities.dp(20.0f));
        r14.setCursorWidth(1.5f);
        r14.setImeOptions(268435462);
        r14.setTextSize(1, 17.0f);
        r14.setMaxLines(1);
        r14.setBackground(null);
        r14.setPadding(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r14.setTextColor(Theme.getColor(r10));
        r14.requestFocus();
        r92.setLeftPadding(AndroidUtilities.dp(28.0f));
        r92.attachEditText(r14);
        if (r29 != null) goto L5;
    L7:
        boolean r102 = false;
    L8:
        r92.animateSelection(true, r102, false);
        r92.setForceUseCenter2(true);
        r14.setOnFocusChangeListener(new SellGiftEnterPriceSheet$$ExternalSyntheticLambda1(this));
        r92.addView(r14, LayoutHelper.createFrame(-1, -2, 48));
        r64.addView(r92, LayoutHelper.createLinear(-1, 58, 18.0f, 0.0f, 18.0f, 0.0f));
        ImageView r103 = new ImageView(r26);
        this.iconStars = r103;
        r103.setImageResource(R.drawable.star_small_inner);
        r92.addView(r103, LayoutHelper.createFrame(22, 22.0f, 19, 14.0f, 0.0f, 0.0f, 0.0f));
        ImageView r104 = new ImageView(r26);
        this.iconTon = r104;
        r104.setImageResource(R.drawable.mini_gram_72);
        r104.setColorFilter(-13397548);
        r92.addView(r104, LayoutHelper.createFrame(22, 22.0f, 19, 14.0f, 0.0f, 0.0f, 0.0f));
        AnimatedTextView r105 = new AnimatedTextView(r26);
        this.dollarsEqView = r105;
        int r11 = Theme.key_windowBackgroundWhiteGrayText;
        r105.setTextColor(Theme.getColor(r11));
        r105.setTextSize(AndroidUtilities.dp(13.0f));
        r105.setGravity(5);
        r92.addView(r105, LayoutHelper.createFrame(-2, -1.0f, 21, 0.0f, 0.0f, 16.0f, 0.0f));
        TextView r93 = new TextView(r26);
        this.starsCountEditHint = r93;
        r93.setTextColor(Theme.getColor(r11));
        r93.setTextSize(1, 13.0f);
        r64.addView(r93, LayoutHelper.createLinear(-1, -2, 55, 33, 4, 33, 0));
        TextCheckbox2Cell r94 = new TextCheckbox2Cell(r26);
        this.radioButtonCell = r94;
        r94.setCheckboxGravityTop();
        r94.setTextAndValue(LocaleController.getString(R.string.ResellGiftPriceOnlyTON), LocaleController.getString(R.string.ResellGiftPriceHintOnlyTON), true, false);
        r94.setOnClickListener(new SellGiftEnterPriceSheet$$ExternalSyntheticLambda2(this));
        r64.addView(r94, LayoutHelper.createLinear(-1, -2, 55, 0, 16, 0, 16));
        LinearLayout r65 = new LinearLayout(r26);
        r65.setOrientation(1);
        r52.addView(r65, LayoutHelper.createLinear(-1, -2, 80));
        ButtonWithCounterView r1 = new ButtonWithCounterView(r26, r27).setRound();
        this.buttonView = r1;
        r1.setOnClickListener(new SellGiftEnterPriceSheet$$ExternalSyntheticLambda3(this, r30));
        r1.setText(LocaleController.getString(R.string.ResellGiftButton), false);
        r65.addView(r1, LayoutHelper.createLinear(-1, 48, 18.0f, 0.0f, 18.0f, 8.0f));
        if (r29 == null) goto L11;
        setAmount(AmountUtils$Amount.fromNano(r29.asNano(), r29.currency), !r29.isZero(), true, false);
    L12:
        setCustomView(r52);
        r14.addTextChangedListener(new AnonymousClass1(this));
        return;
    L11:
        setAmount(AmountUtils$Amount.fromNano(0, r82), false, true, false);
        goto L12
    L5:
        if (r29.isZero() == true) goto L7;
        r102 = true;
        goto L8
    }

    private void checkAmountInputText(boolean r2) {
        int r22 = this.inputAmountError;
        if ((r22 & 4) == 0) goto L7;
        this.starsCountEditOutline.setText(LocaleController.formatString(R.string.ResellGiftPriceTooMuch, new Object[]{getInputAmountMax().formatAsDecimalSpaced()}));
        return;
    L7:
        if ((r22 & 2) == 0) goto L11;
        this.starsCountEditOutline.setText(LocaleController.formatString(R.string.ResellGiftPriceTooSmall, new Object[]{getInputAmountMin().formatAsDecimalSpaced()}));
        return;
    L11:
        if (this.inputAmount.currency != AmountUtils$Currency.STARS) goto L13;
        int r23 = R.string.ResellGiftPriceTitle;
    L14:
        this.starsCountEditOutline.setText(LocaleController.getString(r23));
        return;
    L13:
        r23 = R.string.ResellGiftPriceTitleTON;
        goto L14
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

    private void checkRateText(boolean r7) {
        AppGlobalConfig r0 = MessagesController.getInstance(this.currentAccount).config;
        AmountUtils$Amount r1 = this.inputAmount;
        AmountUtils$Currency r2 = r1.currency;
        if (r2 != AmountUtils$Currency.STARS) goto L6;
        this.starsCountEditHint.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("ResellGiftInfo", (int) r1.applyPerMille(r0.starsStarGiftResaleCommissionPermille.get()).asDecimal(), new Object[0])));
    L8:
        StringBuilder r02 = new StringBuilder(10);
        r02.append('~');
        AmountUtils$Currency r12 = this.inputAmount.currency;
        AmountUtils$Currency r22 = AmountUtils$Currency.TON;
        int r3 = this.currentAccount;
        if (r12 != r22) goto L11;
        double r13 = MessagesController.getInstance(r3).config.tonUsdRate.get();
    L12:
        r02.append(BillingController.getInstance().formatCurrency((long) ((this.inputAmount.asDouble() * r13) * 100.0d), "USD", 2));
        this.dollarsEqView.setText(r02, r7);
        return;
    L11:
        r13 = MessagesController.getInstance(r3).starsUsdWithdrawRate1000 * 1.0E-5d;
        goto L12
    L6:
        if (r2 != AmountUtils$Currency.TON) goto L8;
        this.starsCountEditHint.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.ResellGiftInfoTON, new Object[]{r1.applyPerMille(r0.tonStarGiftResaleCommissionPermille.get()).asDecimalString()})));
        goto L8
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

    private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
        this.starsCountEditOutline.animateSelection(r2, !TextUtils.isEmpty(this.starsCountEditField.getText()));
    }

    private /* synthetic */ void lambda$new$1(View r4) {
        AmountUtils$Currency r42 = this.inputAmount.currency;
        AmountUtils$Currency r0 = AmountUtils$Currency.TON;
        if (r42 != r0) goto L5;
        r0 = AmountUtils$Currency.STARS;
    L5:
        setAmount(AmountUtils$Amount.fromNano(0, r0), true, false, true);
        this.starsCountEditField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private /* synthetic */ void lambda$new$2(Utilities.Callback r2, View r3) {
        if (this.buttonView.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.buttonView.isLoading() == true) goto L10;
        AndroidUtilities.hideKeyboard(this.starsCountEditField);
        this.buttonView.setLoading(true);
        r2.run(this.inputAmount);
        return;
    }

    private /* synthetic */ void lambda$show$3() {
        AndroidUtilities.showKeyboard(this.starsCountEditField);
    }

    private void onCurrencyChanged(boolean r8) {
        AmountUtils$Currency r0 = this.inputAmount.currency;
        AmountUtils$Currency r1 = AmountUtils$Currency.STARS;
        boolean r2 = false;
        if (r0 != r1) goto L6;
        this.titleView.setText(LocaleController.getString(R.string.ResellGiftTitle), r8);
        this.starsCountEditField.setInputType(2);
        this.starsCountEditField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Long.toString(getInputAmountMax().asDecimal()).length())});
    L8:
        CheckBox2 r02 = this.radioButtonCell.checkbox;
        AmountUtils$Currency r4 = this.inputAmount.currency;
        AmountUtils$Currency r5 = AmountUtils$Currency.TON;
        if (r4 != r5) goto L11;
        r2 = true;
    L11:
        r02.setChecked(r2, r8);
        ImageView r03 = this.iconStars;
        float r22 = 0.0f;
        if (r8 == false) goto L39;
        ViewPropertyAnimator r82 = r03.animate();
        if (this.inputAmount.currency != r1) goto L16;
        float r04 = 1.0f;
    L17:
        ViewPropertyAnimator r83 = r82.alpha(r04);
        if (this.inputAmount.currency != r1) goto L20;
        float r05 = 1.0f;
    L21:
        ViewPropertyAnimator r84 = r83.scaleX(r05);
        if (this.inputAmount.currency != r1) goto L24;
        float r06 = 1.0f;
    L25:
        r84.scaleY(r06).setDuration(180).start();
        ViewPropertyAnimator r85 = this.iconTon.animate();
        if (this.inputAmount.currency != r5) goto L28;
        float r42 = 1.0f;
    L29:
        ViewPropertyAnimator r86 = r85.alpha(r42);
        if (this.inputAmount.currency != r5) goto L32;
        float r43 = 1.0f;
    L33:
        ViewPropertyAnimator r87 = r86.scaleX(r43);
        if (this.inputAmount.currency != r5) goto L36;
        r22 = 1.0f;
    L36:
        r87.scaleY(r22).setDuration(180).start();
        return;
    L32:
        r43 = 0.0f;
        goto L33
    L28:
        r42 = 0.0f;
        goto L29
    L24:
        r06 = 0.0f;
        goto L25
    L20:
        r05 = 0.0f;
        goto L21
    L16:
        r04 = 0.0f;
        goto L17
    L39:
        if (this.inputAmount.currency != r1) goto L41;
        float r88 = 1.0f;
    L42:
        r03.setAlpha(r88);
        ImageView r89 = this.iconTon;
        if (this.inputAmount.currency != r5) goto L45;
        r22 = 1.0f;
    L45:
        r89.setAlpha(r22);
        return;
    L41:
        r88 = 0.0f;
        goto L42
    L6:
        if (r0 != AmountUtils$Currency.TON) goto L8;
        this.titleView.setText(LocaleController.getString(R.string.ResellGiftTitleTON), r8);
        this.starsCountEditField.setInputType(8194);
        this.starsCountEditField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Long.toString(getInputAmountMax().asDecimal()).length() + 3)});
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

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        AndroidUtilities.runOnUIThread(new SellGiftEnterPriceSheet$$ExternalSyntheticLambda0(this), 50);
    }
}
