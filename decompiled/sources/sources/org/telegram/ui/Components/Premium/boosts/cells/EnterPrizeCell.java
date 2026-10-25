package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class EnterPrizeCell extends LinearLayout {
    private AfterTextChangedListener afterTextChangedListener;
    private final EditTextCaption editText;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView textView;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.EnterPrizeCell$1, reason: invalid class name */
    public class AnonymousClass1 extends InputFilter.LengthFilter {
        final /* synthetic */ EnterPrizeCell this$0;

        public AnonymousClass1(EnterPrizeCell r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.text.InputFilter.LengthFilter, android.text.InputFilter
        public CharSequence filter(CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
            CharSequence r12 = super.filter(r1, r2, r3, r4, r5, r6);
            if (r12 != null) goto L5;
        L7:
            return r12;
        L5:
            if (r12.length() != 0) goto L7;
            AndroidUtilities.shakeView(EnterPrizeCell.m12160$$Nest$fgeteditText(this.this$0));
            BotWebViewVibrationEffect.APP_ERROR.vibrate();
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.EnterPrizeCell$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ EnterPrizeCell this$0;

        public AnonymousClass2(EnterPrizeCell r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (EnterPrizeCell.m12159$$Nest$fgetafterTextChangedListener(this.this$0) == null) goto L6;
            EnterPrizeCell.m12159$$Nest$fgetafterTextChangedListener(this.this$0).afterTextChanged(r2.toString().trim());
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public interface AfterTextChangedListener {
        void afterTextChanged(String r1);
    }

    /* renamed from: -$$Nest$fgetafterTextChangedListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AfterTextChangedListener m12159$$Nest$fgetafterTextChangedListener(EnterPrizeCell r0) {
        return r0.afterTextChangedListener;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCaption m12160$$Nest$fgeteditText(EnterPrizeCell r0) {
        return r0.editText;
    }

    public EnterPrizeCell(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        this.resourcesProvider = r13;
        setOrientation(0);
        EditTextCaption r1 = new EditTextCaption(r12, r13);
        this.editText = r1;
        r1.setLines(1);
        r1.setSingleLine(true);
        InputFilter[] r4 = {new AnonymousClass1(this, 128)};
        r1.setInputType(16384);
        r1.setFilters(r4);
        r1.setTextSize(1, 16.0f);
        r1.setTextColor(Theme.getColor(Theme.key_chat_messagePanelText, r13));
        r1.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkOut, r13));
        r1.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, r13));
        int r3 = Theme.key_chat_messagePanelHint;
        r1.setHintColor(Theme.getColor(r3, r13));
        r1.setHintTextColor(Theme.getColor(r3, r13));
        r1.setCursorColor(Theme.getColor(Theme.key_chat_messagePanelCursor, r13));
        r1.setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor, r13));
        r1.setBackground(null);
        r1.setHint(LocaleController.getString(R.string.BoostingGiveawayEnterYourPrize));
        r1.addTextChangedListener(new AnonymousClass2(this));
        r1.setImeOptions(6);
        TextView r32 = new TextView(r12);
        this.textView = r32;
        r32.setTextSize(1, 16.0f);
        r32.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r13));
        if (LocaleController.isRTL == false) goto L6;
        LinearLayout.LayoutParams r122 = LayoutHelper.createLinear(-1, -2, 16, 20, 0, 36, 0);
        r122.weight = 1.0f;
        addView(r1, r122);
        addView(r32, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 20, 0));
        return;
    L6:
        addView(r32, LayoutHelper.createLinear(-2, -2, 16, 20, 0, 0, 0));
        addView(r1, LayoutHelper.createLinear(-1, -2, 16, 36, 0, 20, 0));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
    }

    public void setAfterTextChangedListener(AfterTextChangedListener r1) {
        this.afterTextChangedListener = r1;
    }

    public void setCount(int r1) {
        this.textView.setText(String.valueOf(r1));
    }
}
