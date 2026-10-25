package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonSpan;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stars.BalanceCloud;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.TON.TONIntroActivity;

/* loaded from: classes6.dex */
public class StakedDiceSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final BalanceCloud balanceCloud;
    private boolean balanceCloudVisible;
    private LinearLayout editView;
    private boolean isOpenAnimationEnd;
    private LinearLayout topView;

    /* renamed from: org.telegram.ui.StakedDiceSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ReplacementSpan {
        final Paint bgPaint;
        final Text text;
        final /* synthetic */ StakedDiceSheet this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(StakedDiceSheet r3, Theme.ResourcesProvider r4) {
            this.this$0 = r3;
            this.val$resourcesProvider = r4;
            this.text = new Text(LocaleController.getString(R.string.StakeDiceTitleBeta), 12.0f, AndroidUtilities.bold());
            this.bgPaint = new Paint(1);
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            float r72 = ((r6 + r8) / 2.0f) + AndroidUtilities.dp(1.0f);
            this.bgPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.val$resourcesProvider));
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(r5, r72 - AndroidUtilities.dp(9.0f), (AndroidUtilities.dp(16.0f) + r5) + this.text.getCurrentWidth(), AndroidUtilities.dp(9.0f) + r72);
            r1.drawRoundRect(r22, AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), this.bgPaint);
            this.text.draw(r1, r5 + AndroidUtilities.dp(8.0f), r72, -1, 1.0f);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (int) (AndroidUtilities.dp(16.0f) + this.text.getCurrentWidth());
        }
    }

    /* renamed from: org.telegram.ui.StakedDiceSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        private boolean ignore;
        final /* synthetic */ StakedDiceSheet this$0;
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ EditTextBoldCursor val$editText;
        final /* synthetic */ OutlineTextContainerView val$editTextContainer;
        final /* synthetic */ int[] val$shakeDp;
        final /* synthetic */ TextView val$subPriceView;

        public AnonymousClass2(StakedDiceSheet r1, int r2, EditTextBoldCursor r3, OutlineTextContainerView r4, int[] r5, TextView r6) {
            this.this$0 = r1;
            this.val$currentAccount = r2;
            this.val$editText = r3;
            this.val$editTextContainer = r4;
            this.val$shakeDp = r5;
            this.val$subPriceView = r6;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r12) {
            if (this.ignore == false) goto L34;
            return;
        L34:
        L19:
            double r5 = 0.0d;
        L20:
            this.ignore = true;
            EditTextBoldCursor r122 = this.val$editText;
            if (r5 > 0.0d) goto L23;
            String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        L24:
            r122.setText(r7);
            EditTextBoldCursor r123 = this.val$editText;
            r123.setSelection(r123.getText().length());
        L25:
            this.ignore = false;
            this.val$editTextContainer.animateSelection(this.val$editText.isFocused(), true ^ TextUtils.isEmpty(this.val$editText.getText()));
            TextView r1 = this.val$subPriceView;
            if (r5 != 0.0d) goto L28;
            r1.animate().alpha(0.0f).start();
            this.val$subPriceView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L28:
            r1.animate().alpha(1.0f).start();
            this.val$subPriceView.setText("≈" + BillingController.getInstance().formatCurrency((long) ((r5 * MessagesController.getInstance(this.val$currentAccount).config.tonUsdRate.get()) * 100.0d), "USD", 2));
            return;
        L23:
            r7 = Double.toString(r5);
            goto L24
        L7:
            if (TextUtils.isEmpty(r12) == false) goto L9;
            r5 = 0.0d;
        L32:
            if (r5 <= (MessagesController.getInstance(this.val$currentAccount).tonStakeddiceStakeAmountMax / 1.0E9d)) goto L14;
            this.ignore = true;     // Catch: Exception -> L30
            r5 = MessagesController.getInstance(this.val$currentAccount).tonStakeddiceStakeAmountMax / 1.0E9d;     // Catch: Exception -> L30
            this.val$editText.setText(Double.toString(r5));     // Catch: Exception -> L30
            EditTextBoldCursor r124 = this.val$editText;     // Catch: Exception -> L30
            r124.setSelection(r124.getText().length());     // Catch: Exception -> L30
            OutlineTextContainerView r125 = this.val$editTextContainer;     // Catch: Exception -> L30
            int[] r72 = this.val$shakeDp;     // Catch: Exception -> L30
            int r8 = -r72[0];
            r72[0] = r8;     // Catch: Exception -> L30
            AndroidUtilities.shakeViewSpring(r125, r8);     // Catch: Exception -> L30
        L31:
        L14:
            if (r5 <= 0.0d) goto L31;
            if (r5 >= (MessagesController.getInstance(this.val$currentAccount).tonStakeddiceStakeAmountMin / 1.0E9d)) goto L31;
            this.ignore = true;     // Catch: Exception -> L30
            r5 = MessagesController.getInstance(this.val$currentAccount).tonStakeddiceStakeAmountMin / 1.0E9d;     // Catch: Exception -> L30
            this.val$editText.setText(Double.toString(r5));     // Catch: Exception -> L30
            EditTextBoldCursor r126 = this.val$editText;     // Catch: Exception -> L30
            r126.setSelection(r126.getText().length());     // Catch: Exception -> L30
            OutlineTextContainerView r127 = this.val$editTextContainer;     // Catch: Exception -> L30
            int[] r73 = this.val$shakeDp;     // Catch: Exception -> L30
            int r82 = -r73[0];
            r73[0] = r82;     // Catch: Exception -> L30
            AndroidUtilities.shakeViewSpring(r127, r82);     // Catch: Exception -> L30
            goto L31
        L9:
            r5 = Double.parseDouble(r12.toString());     // Catch: Exception -> L19
            goto L32
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static /* synthetic */ void $r8$lambda$2XCcAruOv5QszSEYM9slNEERuNA() {
    }

    public static /* synthetic */ void $r8$lambda$8gQUPmCFk18KM4UE7gTZ7WNBZ34(BaseFragment r3, Utilities.Callback r4) {
        new StakedDiceSheet(r3.getContext(), r3.getCurrentAccount(), r3.getResourceProvider(), r4).show();
    }

    /* renamed from: $r8$lambda$Avzvs7HEBCdk-CHlzfUQfHJlva8, reason: not valid java name */
    public static /* synthetic */ TableView.TableRowContent m18856$r8$lambda$Avzvs7HEBCdkCHlzfUQfHJlva8(Context r13, int[] r14, Theme.ResourcesProvider r15, TableView r16, Integer r17, Float r18) {
        LinearLayout r0 = new LinearLayout(r13);
        r0.setOrientation(1);
        LinearLayout r2 = new LinearLayout(r13);
        r0.addView(r2, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 0));
        ImageView r3 = new ImageView(r13);
        r3.setImageResource(r14[r17.intValue() - 1]);
        r3.setScaleType(ImageView.ScaleType.CENTER);
        r2.addView(r3, LayoutHelper.createLinear(24, 24));
        if (r17.intValue() != 7) goto L8;
        int r32 = 0;
    L6:
        if (r32 >= 2) goto L8;
        ImageView r5 = new ImageView(r13);
        r5.setImageResource(r14[r17.intValue() - 1]);
        r5.setScaleType(ImageView.ScaleType.CENTER);
        r2.addView(r5, LayoutHelper.createLinear(24, 24));
        r32 = r32 + 1;
    L8:
        TextView r142 = new TextView(r13);
        r142.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r142.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r15));
        StringBuilder r132 = new StringBuilder("x");
        if (r18.floatValue() > 0.0f) goto L11;
        Object r22 = MVEL.VERSION_SUB;
    L12:
        r132.append(r22);
        r142.setText(r132.toString());
        r142.setTextSize(1, 13.0f);
        r142.setGravity(17);
        r0.addView(r142, LayoutHelper.createLinear(-1, -2, 0.0f, 3.0f, 0.0f, 0.0f));
        return new TableView.TableRowContent(r16, r0, false);
    L11:
        r22 = r18;
        goto L12
    }

    public static /* synthetic */ void $r8$lambda$E7b7UUye4r2wSQn9QW2dD17gL6U(StakedDiceSheet r0, EditTextBoldCursor r1, int r2, OutlineTextContainerView r3, int[] r4, Context r5, Theme.ResourcesProvider r6, Utilities.Callback r7, View r8) {
        r0.lambda$new$6(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$U3QzNjIEL4No-d70FOH84Lt4RXk, reason: not valid java name */
    public static /* synthetic */ View m18857$r8$lambda$U3QzNjIEL4Nod70FOH84Lt4RXk(Context r3, Theme.ResourcesProvider r4, EditTextBoldCursor r5, Long r6) {
        TextView r0 = new TextView(r3);
        r0.setGravity(17);
        r0.setTextSize(1, 13.0f);
        r0.setTypeface(AndroidUtilities.bold());
        int r32 = Theme.key_featuredStickers_addButton;
        r0.setTextColor(Theme.getColor(r32, r4));
        r0.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(13.0f), Theme.multAlpha(Theme.getColor(r32, r4), 0.15f)));
        r0.setText(StarsIntroActivity.replaceDiamond(StarsIntroActivity.formatTON(r6.longValue()) + " 💎", 0.75f));
        ScaleStateListAnimator.apply(r0);
        r0.setOnClickListener(new StakedDiceSheet$$ExternalSyntheticLambda9(r5, r6));
        return r0;
    }

    public static /* synthetic */ void $r8$lambda$ZIiGl5m2my_fYS78YgXguQNOwso(StakedDiceSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$c63LmF-8OlVKvaL0u5PwNP98bV8, reason: not valid java name */
    public static /* synthetic */ void m18858$r8$lambda$c63LmF8OlVKvaL0u5PwNP98bV8(OutlineTextContainerView r0, EditTextBoldCursor r1, View r2, boolean r3) {
        r0.animateSelection(r3, !TextUtils.isEmpty(r1.getText()));
    }

    public static /* synthetic */ void $r8$lambda$hfTFgAeov904or_20m9O9Nsbv2E(Utilities.Callback r0, long r1) {
        r0.run(Long.valueOf(r1));
    }

    public static /* synthetic */ void $r8$lambda$n6xWFveMheAHh3Q6A0LJqBIlKUo(Context r0, Theme.ResourcesProvider r1, View r2) {
        new StarsIntroActivity.StarsOptionsSheet(r0, r1).show();
    }

    public static /* synthetic */ void $r8$lambda$zzvBunEOVdmRliQ0b4YCyQVjPHw(EditTextBoldCursor r0, Long r1, View r2) {
        r0.setText(StarsIntroActivity.formatTON(r1.longValue()));
        r0.setSelection(r0.getText().length());
    }

    public StakedDiceSheet(Context r38, int r39, Theme.ResourcesProvider r40, Utilities.Callback<Long> r41) {
        super(r38, null, true, false, false, BottomSheetWithRecyclerListView.ActionBarType.FADING, r40);
        this.currentAccount = r39;
        this.topPadding = 0.2f;
        this.smoothKeyboardAnimationEnabled = true;
        this.smoothKeyboardByBottom = true;
        BalanceCloud r2 = new BalanceCloud(r38, r39, AmountUtils$Currency.TON, r40);
        this.balanceCloud = r2;
        r2.setScaleX(0.6f);
        r2.setScaleY(0.6f);
        r2.setAlpha(0.0f);
        this.container.addView(r2, 0, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 48.0f, 0.0f, 0.0f));
        ScaleStateListAnimator.apply(r2);
        r2.setOnClickListener(new StakedDiceSheet$$ExternalSyntheticLambda3(r38, r40));
        TLRPC.EmojiGameInfo r22 = MessagesController.getInstance(r39).stakeDiceInfo;
        if ((r22 instanceof TLRPC.TL_emojiGameDiceInfo) == true) goto L5;
        return;
    L5:
        TLRPC.TL_emojiGameDiceInfo r23 = (TLRPC.TL_emojiGameDiceInfo) r22;
        LinearLayout r4 = new LinearLayout(r38);
        this.topView = r4;
        r4.setOrientation(1);
        this.topView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.0f));
        this.topView.setClipChildren(false);
        this.topView.setClipToPadding(false);
        ImageView r42 = new ImageView(r38);
        r42.setImageResource(R.drawable.dice6);
        this.topView.addView(r42, LayoutHelper.createLinear(80, 80, 1, 0, 0, 0, 8));
        int r43 = Theme.key_dialogTextBlack;
        TextView r6 = TextHelper.makeTextView(r38, 20.0f, r43, true);
        r6.setGravity(17);
        SpannableStringBuilder r13 = new SpannableStringBuilder(LocaleController.getString(R.string.StakeDiceTitle));
        r13.append(" ");
        int r14 = r13.length();
        r13.append(LocaleController.getString(R.string.StakeDiceTitleBeta));
        r13.setSpan(new AnonymousClass1(this, r40), r14, r13.length(), 33);
        r6.setText(r13);
        this.topView.addView(r6, LayoutHelper.createLinear(-1, -2, 32.0f, 0.0f, 32.0f, 8.0f));
        TextView r44 = TextHelper.makeTextView(r38, 14.0f, r43, false);
        r44.setGravity(17);
        r44.setText(LocaleController.getString(R.string.StakeDiceText));
        this.topView.addView(r44, LayoutHelper.createLinear(-1, -2, 32.0f, 0.0f, 32.0f, 12.0f));
        LinearLayout r45 = new LinearLayout(r38);
        r45.setOrientation(1);
        TextView r62 = TextHelper.makeTextView(r38, 14.0f, Theme.key_windowBackgroundWhiteBlueHeader, true);
        r62.setText(LocaleController.getString(R.string.StakeDiceReturns));
        r45.addView(r62, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 8.0f));
        TableView r63 = new TableView(r38, r40);
        r45.addView(r63, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        TableRow r132 = new TableRow(r38);
        r63.addView(r132);
        TableRow r142 = new TableRow(r38);
        r63.addView(r142);
        int r18 = R.drawable.dice1;
        int r19 = R.drawable.dice2;
        int r20 = R.drawable.dice3;
        int r21 = R.drawable.dice4;
        int r222 = R.drawable.dice5;
        int r232 = R.drawable.dice6;
        StakedDiceSheet$$ExternalSyntheticLambda4 r5 = new StakedDiceSheet$$ExternalSyntheticLambda4(r38, new int[]{r18, r19, r20, r21, r222, r232, r232}, r40, r63);
        float r12 = 1.0f;
        if (r23.params.size() != 7) goto L9;
        r132.addView((View) r5.run(1, Float.valueOf(r23.params.get(0).intValue() / 1000.0f)), new TableRow.LayoutParams(0, -1, 1.0f));
        r132.addView((View) r5.run(2, Float.valueOf(r23.params.get(1).intValue() / 1000.0f)), new TableRow.LayoutParams(0, -1, 1.0f));
        r132.addView((View) r5.run(3, Float.valueOf(r23.params.get(2).intValue() / 1000.0f)), new TableRow.LayoutParams(0, -1, 1.0f));
        r132.addView((View) r5.run(4, Float.valueOf(r23.params.get(3).intValue() / 1000.0f)), new TableRow.LayoutParams(0, -1, 1.0f));
        r142.addView((View) r5.run(5, Float.valueOf(r23.params.get(4).intValue() / 1000.0f)), new TableRow.LayoutParams(0, -1, 1.0f));
        r142.addView((View) r5.run(6, Float.valueOf(r23.params.get(5).intValue() / 1000.0f)), new TableRow.LayoutParams(0, -1, 1.0f));
        r142.addView((View) r5.run(7, Float.valueOf(r23.params.get(6).intValue() / 1000.0f)), new TableRow.LayoutParams(0, -1, 2.0f));
    L9:
        TextView r0 = TextHelper.makeTextView(r38, 14.0f, Theme.key_windowBackgroundWhiteGrayText, false);
        r0.setGravity(17);
        SpannableStringBuilder r3 = new SpannableStringBuilder("🎲");
        ColoredImageSpan r52 = new ColoredImageSpan(R.drawable.dice6);
        r52.recolorDrawable = false;
        r52.setScale(0.8f, 0.8f);
        r3.setSpan(r52, 0, r3.length(), 33);
        r0.setText(AndroidUtilities.replaceMultipleCharSequence("🎲", LocaleController.getString(R.string.StakeDiceReturnsInfo), r3));
        r45.addView(r0, LayoutHelper.createLinear(-1, -2, 0.0f, 4.0f, 0.0f, 16.0f));
        this.topView.addView(r45, LayoutHelper.createLinear(-1, -2, 8.0f, 0.0f, 8.0f, 0.0f));
        LinearLayout r02 = new LinearLayout(r38);
        this.editView = r02;
        r02.setOrientation(1);
        this.editView.setPadding(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(42.0f), AndroidUtilities.dp(7.0f));
        this.editView.setClipToPadding(false);
        EditTextBoldCursor r32 = new EditTextBoldCursor(r38);
        OutlineTextContainerView r46 = new OutlineTextContainerView(r38, r40);
        r46.setForceForceUseCenter(true);
        r46.setText(LocaleController.getString(R.string.StakeDicePlaceholder));
        r46.setLeftPadding(AndroidUtilities.dp(36.0f));
        r32.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r40));
        r32.setCursorSize(AndroidUtilities.dp(20.0f));
        r32.setCursorWidth(1.5f);
        r32.setBackground(null);
        r32.setTextSize(1, 18.0f);
        r32.setMaxLines(1);
        int r03 = AndroidUtilities.dp(16.0f);
        r32.setPadding(AndroidUtilities.dp(6.0f), r03, r03, r03);
        r32.setInputType(8194);
        r32.setTypeface(Typeface.DEFAULT);
        r32.setSelectAllOnFocus(true);
        r32.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, r40));
        r32.setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor, r40));
        if (LocaleController.isRTL == false) goto L12;
        int r04 = 5;
    L13:
        r32.setGravity(r04);
        r32.setOnFocusChangeListener(new StakedDiceSheet$$ExternalSyntheticLambda5(r46, r32));
        LinearLayout r05 = new LinearLayout(r38);
        r05.setOrientation(0);
        ImageView r53 = new ImageView(r38);
        r53.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r53.setImageResource(R.drawable.diamond);
        r05.addView(r53, LayoutHelper.createLinear(-2, -2, 0.0f, 19, 14, 0, 0, 0));
        r05.addView(r32, LayoutHelper.createLinear(-1, -2, 1.0f, 119));
        r46.attachEditText(r32);
        r46.addView(r05, LayoutHelper.createFrame(-1, -2, 48));
        this.editView.addView(r46, LayoutHelper.createLinear(-1, -2));
        TextView r64 = new TextView(r38);
        r64.setTextSize(1, 16.0f);
        r64.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3));
        r46.addView(r64, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 14.0f, 0.0f));
        long r133 = r23.prev_stake;
        if (r133 > 0) goto L17;
        r133 = 1000000000;
    L17:
        r32.setText(StarsIntroActivity.formatTON(r133));
        r64.setAlpha(1.0f);
        r64.setText("≈" + BillingController.getInstance().formatCurrency((long) (((r133 / 1.0E9d) * MessagesController.getInstance(r39).config.tonUsdRate.get()) * 100.0d), "USD", 2));
        int[] r54 = {2};
        r46.animateSelection(false, TextUtils.isEmpty(r32.getText()) ^ true);
        r32.addTextChangedListener(new AnonymousClass2(this, r39, r32, r46, r54, r64));
        StakedDiceSheet$$ExternalSyntheticLambda6 r06 = new StakedDiceSheet$$ExternalSyntheticLambda6(r38, r40, r32);
        long[] r33 = MessagesController.getInstance(r39).tonStakediceStakeSuggestedAmounts;
        int r65 = 0;
    L18:
        int r134 = 3;
        if (r65 >= Utilities.divCeil(r33.length, 3)) goto L29;
        LinearLayout r8 = new LinearLayout(r38);
        r8.setOrientation(0);
        int r143 = 0;
        float r16 = r12;
    L21:
        int r17 = r65 * 3;
        if (r143 >= Math.min(r134, r33.length - r17)) goto L28;
        View r122 = (View) r06.run(Long.valueOf(r33[r17 + r143]));
        if (r143 != 2) goto L26;
        int r29 = 0;
    L27:
        r8.addView(r122, LayoutHelper.createLinear(0, 26, 1.0f, 112, 0, 0, r29, 0));
        r143 = r143 + 1;
        r134 = 3;
        goto L21
    L26:
        r29 = 6;
        goto L27
    L28:
        this.editView.addView(r8, LayoutHelper.createLinear(-1, -2, 0.0f, 7.0f, 0.0f, 0.0f));
        r65 = r65 + 1;
        r12 = r16;
        goto L18
    L29:
        float r162 = r12;
        ButtonWithCounterView r123 = new ButtonWithCounterView(r38, r40);
        SpannableStringBuilder r07 = new SpannableStringBuilder("🎲");
        ColoredImageSpan r34 = new ColoredImageSpan(R.drawable.mini_roll);
        r34.setTranslateY(AndroidUtilities.dp(r162));
        r07.setSpan(r34, 0, r07.length(), 33);
        r07.append("  ").append(LocaleController.getString(R.string.StakeDiceButton));
        r123.setText(r07, false);
        r123.setOnClickListener(new StakedDiceSheet$$ExternalSyntheticLambda7(this, r32, r39, r46, r54, r38, r40, r41));
        FrameLayout r08 = new FrameLayout(r38);
        r08.addView(r123, LayoutHelper.createLinear(-1, 48, 87, 16, 0, 16, 10));
        ViewGroup r24 = this.containerView;
        int r66 = this.backgroundPaddingLeft;
        r24.addView(r08, LayoutHelper.createFrameMarginPx(-1, -2.0f, 87, r66, 0, r66, 0));
        RecyclerListView r09 = this.recyclerListView;
        int r25 = this.backgroundPaddingLeft;
        r09.setPadding(r25, 0, r25, AndroidUtilities.dp(68.0f));
        this.adapter.update(false);
        return;
    L12:
        r04 = 3;
        goto L13
    }

    private void checkBalanceCloudVisibility() {
        if (this.isOpenAnimationEnd == true) goto L5;
    L9:
        boolean r0 = false;
    L11:
        if (this.balanceCloudVisible == r0) goto L27;
        this.balanceCloudVisible = r0;
        BalanceCloud r1 = this.balanceCloud;
        if (r1 == null) goto L28;
        r1.setEnabled(r0);
        this.balanceCloud.setClickable(r0);
        ViewPropertyAnimator r4 = this.balanceCloud.animate();
        float r12 = 0.6f;
        float r2 = 1.0f;
        if (r0 == false) goto L17;
        float r3 = 1.0f;
    L18:
        ViewPropertyAnimator r42 = r4.scaleX(r3);
        if (r0 == false) goto L21;
        r12 = 1.0f;
    L21:
        ViewPropertyAnimator r43 = r42.scaleY(r12);
        if (r0 == true) goto L25;
        r2 = 0.0f;
    L25:
        r43.alpha(r2).setDuration(180).start();
        return;
    L17:
        r3 = 0.6f;
        goto L18
    L28:
        return;
    L27:
        return;
    L5:
        if (isDismissed() == true) goto L9;
        if (isKeyboardVisible() == true) goto L9;
        r0 = true;
        goto L11
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        LinearLayout r22 = this.topView;
        if (r22 == null) goto L5;
        r1.add(UItem.asCustom(r22));
    L5:
        LinearLayout r0 = this.editView;
        if (r0 == null) goto L9;
        r1.add(UItem.asCustom(r0));
        return;
    }

    private /* synthetic */ void lambda$new$6(EditTextBoldCursor r16, int r17, OutlineTextContainerView r18, int[] r19, Context r20, Theme.ResourcesProvider r21, Utilities.Callback r22, View r23) {
        Editable r2 = r16.getText();
        if (TextUtils.isEmpty(r2) == false) goto L6;
        double r3 = 0.0d;
    L8:
        if (r3 <= (MessagesController.getInstance(r17).tonStakeddiceStakeAmountMax / 1.0E9d)) goto L12;
        r16.setText(Double.toString(MessagesController.getInstance(r17).tonStakeddiceStakeAmountMax / 1.0E9d));
        r16.setSelection(r16.getText().length());
        int r15 = -r19[0];
        r19[0] = r15;
        AndroidUtilities.shakeViewSpring(r18, r15);
        return;
    L12:
        if (TextUtils.isEmpty(r2) == true) goto L18;
        if (r3 >= (MessagesController.getInstance(r17).tonStakeddiceStakeAmountMin / 1.0E9d)) goto L18;
        r16.setText(Double.toString(MessagesController.getInstance(r17).tonStakeddiceStakeAmountMin / 1.0E9d));
        r16.setSelection(r16.getText().length());
        int r152 = -r19[0];
        r19[0] = r152;
        AndroidUtilities.shakeViewSpring(r18, r152);
        return;
    L18:
        if (StarsController.getInstance(r17, true).balance.toDouble() >= r3) goto L21;
        new TONIntroActivity.StarsNeededSheet(r20, r21, AmountUtils$Amount.fromNano((long) (r3 * 1.0E9d), AmountUtils$Currency.TON), true, new StakedDiceSheet$$ExternalSyntheticLambda8());
        return;
    L21:
        r22.run(Long.valueOf((long) (r3 * 1.0E9d)));
        dismiss();
        return;
    L6:
        r3 = Double.parseDouble(r2.toString());     // Catch: Exception -> L23
    }

    public static void showStakeToast(BaseFragment r19, int r20, long r21, Utilities.Callback<Long> r23) {
        if (r19 != null) goto L5;
        BaseFragment r2 = LaunchActivity.getSafeLastFragment();
    L6:
        if (r2 == null) goto L31;
        TLRPC.EmojiGameInfo r3 = MessagesController.getInstance(r2.getCurrentAccount()).stakeDiceInfo;
        if ((r3 instanceof TLRPC.TL_emojiGameDiceInfo) == true) goto L11;
        return;
    L11:
        long r32 = ((TLRPC.TL_emojiGameDiceInfo) r3).prev_stake;
        Bulletin.LottieLayout r5 = new Bulletin.LottieLayout(r2.getContext(), r2.getResourceProvider());
        r5.imageView.setScaleX(1.25f);
        r5.imageView.setScaleY(1.25f);
        if (r20 != 1) goto L14;
        r5.imageView.setImageResource(R.drawable.dice1);
    L29:
        SpannableStringBuilder r0 = new SpannableStringBuilder(LocaleController.getString(R.string.StakeDiceToast));
        r0.append(StarsIntroActivity.formatTON(r32));
        r0.append("  ").append(ButtonSpan.make(LocaleController.getString(R.string.StakeDiceToastChange), new StakedDiceSheet$$ExternalSyntheticLambda0(r2, r23), r2.getResourceProvider()));
        AndroidUtilities.removeFromParent(r5.textView);
        ButtonSpan.TextViewButtons r8 = new ButtonSpan.TextViewButtons(r2.getContext());
        r5.textView = r8;
        r8.setSingleLine();
        r5.textView.setTypeface(Typeface.SANS_SERIF);
        r5.textView.setTextSize(1, 15.0f);
        r5.textView.setEllipsize(TextUtils.TruncateAt.END);
        r5.textView.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        r5.addView(r5.textView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 0.0f, 16.0f, 0.0f));
        r5.textView.setText(StarsIntroActivity.replaceDiamond(r0));
        r5.textView.setLinkTextColor(Theme.getColor(Theme.key_undo_cancelColor, r2.getResourceProvider()));
        r5.setTextColor(Theme.getColor(Theme.key_undo_infoColor, r2.getResourceProvider()));
        r5.textView.setSingleLine(false);
        r5.textView.setMaxLines(2);
        r5.setButton(new Bulletin.UndoButton(r2.getContext(), true, r2.getResourceProvider()).setText(LocaleController.getString(R.string.StakeDiceToastButton)).setUndoAction(new StakedDiceSheet$$ExternalSyntheticLambda1(r23, r32)));
        BulletinFactory.of(r2).create(r5, 2750).show();
        return;
    L14:
        if (r20 != 2) goto L17;
        r5.imageView.setImageResource(R.drawable.dice2);
        goto L29
    L17:
        if (r20 != 3) goto L20;
        r5.imageView.setImageResource(R.drawable.dice3);
        goto L29
    L20:
        if (r20 != 4) goto L23;
        r5.imageView.setImageResource(R.drawable.dice4);
        goto L29
    L23:
        if (r20 != 5) goto L25;
        r5.imageView.setImageResource(R.drawable.dice5);
        goto L29
    L25:
        RLottieImageView r82 = r5.imageView;
        if (r20 != 6) goto L28;
        r82.setImageResource(R.drawable.dice6);
        goto L29
    L28:
        r82.setScaleX(0.8f);
        r5.imageView.setScaleY(0.8f);
        r5.imageView.setImageDrawable(Emoji.getEmojiBigDrawable("🎲"));
        goto L29
    L31:
        return;
    L5:
        r2 = r19;
        goto L6
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r8) {
        UniversalAdapter r0 = new UniversalAdapter(r8, getContext(), this.currentAccount, 0, new StakedDiceSheet$$ExternalSyntheticLambda2(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.StakeDiceTitle);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean isTouchOutside(float r3, float r4) {
        if (r3 < this.balanceCloud.getX()) goto L13;
        if (r3 > (this.balanceCloud.getX() + this.balanceCloud.getWidth())) goto L13;
        if (r4 < this.balanceCloud.getY()) goto L13;
        if (r4 > (this.balanceCloud.getY() + this.balanceCloud.getHeight())) goto L13;
        return false;
    L13:
        return super.isTouchOutside(r3, r4);
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
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        this.isOpenAnimationEnd = true;
        checkBalanceCloudVisibility();
    }
}
