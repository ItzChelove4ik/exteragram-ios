package org.telegram.ui.Components;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class ReportAlert extends BottomSheet {
    private BottomSheetCell clearButton;
    private EditTextBoldCursor editText;

    public static class BottomSheetCell extends FrameLayout {
        private View background;
        private TextView textView;

        /* renamed from: -$$Nest$fgetbackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m12498$$Nest$fgetbackground(BottomSheetCell r0) {
            return r0.background;
        }

        public BottomSheetCell(Context r11, Theme.ResourcesProvider r12) {
            super(r11);
            View r0 = new View(r11);
            this.background = r0;
            r0.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{8.0f}));
            addView(this.background, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, 16.0f, 16.0f, 16.0f));
            TextView r02 = new TextView(r11);
            this.textView = r02;
            r02.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setGravity(17);
            this.textView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r12));
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    /* renamed from: $r8$lambda$V6saGcQY_-5q6dYA3lZy-BhD7d0, reason: not valid java name */
    public static /* synthetic */ boolean m12496$r8$lambda$V6saGcQY_5q6dYA3lZyBhD7d0(ReportAlert r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$glQKEEly6JkeUqg5Oux-v0oNnKg, reason: not valid java name */
    public static /* synthetic */ void m12497$r8$lambda$glQKEEly6JkeUqg5Ouxv0oNnKg(ReportAlert r0, int r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public ReportAlert(Context r20, int r21, Theme.ResourcesProvider r22) {
        super(r20, true, r22);
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        ScrollView r6 = new ScrollView(r20);
        r6.setFillViewport(true);
        setCustomView(r6);
        FrameLayout r7 = new FrameLayout(r20);
        r6.addView(r7, LayoutHelper.createScroll(-1, -2, 51));
        RLottieImageView r62 = new RLottieImageView(r20);
        r62.setAnimation(R.raw.report_police, 120, 120);
        r62.playAnimation();
        r7.addView(r62, LayoutHelper.createFrame(160, 160.0f, 49, 17.0f, 14.0f, 17.0f, 0.0f));
        TextView r63 = new TextView(r20);
        r63.setTypeface(AndroidUtilities.bold());
        r63.setTextSize(1, 24.0f);
        r63.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        int r8 = 5;
        if (r21 != 0) goto L5;
        r63.setText(LocaleController.getString(R.string.ReportTitleSpam));
    L17:
        r7.addView(r63, LayoutHelper.createFrame(-2, -2.0f, 49, 17.0f, 197.0f, 17.0f, 0.0f));
        TextView r64 = new TextView(r20);
        r64.setTextSize(1, 14.0f);
        r64.setTextColor(getThemedColor(Theme.key_dialogTextGray3));
        r64.setGravity(1);
        r64.setText(LocaleController.getString(R.string.ReportInfo));
        r7.addView(r64, LayoutHelper.createFrame(-2, -2.0f, 49, 30.0f, 235.0f, 30.0f, 44.0f));
        EditTextBoldCursor r65 = new EditTextBoldCursor(r20);
        this.editText = r65;
        r65.setTextSize(1, 18.0f);
        this.editText.setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r66 = this.editText;
        int r10 = Theme.key_windowBackgroundWhiteBlackText;
        r66.setTextColor(getThemedColor(r10));
        this.editText.setBackgroundDrawable(null);
        this.editText.setLineColors(getThemedColor(Theme.key_windowBackgroundWhiteInputField), getThemedColor(Theme.key_windowBackgroundWhiteInputFieldActivated), getThemedColor(Theme.key_text_RedRegular));
        this.editText.setMaxLines(1);
        this.editText.setLines(1);
        this.editText.setPadding(0, 0, 0, 0);
        this.editText.setSingleLine(true);
        EditTextBoldCursor r5 = this.editText;
        if (LocaleController.isRTL == true) goto L21;
        r8 = 3;
    L21:
        r5.setGravity(r8);
        this.editText.setInputType(180224);
        this.editText.setImeOptions(6);
        this.editText.setHint(LocaleController.getString(R.string.ReportHint));
        this.editText.setCursorColor(getThemedColor(r10));
        this.editText.setCursorSize(AndroidUtilities.dp(20.0f));
        this.editText.setCursorWidth(1.5f);
        this.editText.setOnEditorActionListener(new ReportAlert$$ExternalSyntheticLambda0(this));
        r7.addView(this.editText, LayoutHelper.createFrame(-1, 36.0f, 51, 17.0f, 305.0f, 17.0f, 0.0f));
        BottomSheetCell r52 = new BottomSheetCell(r20, r22);
        this.clearButton = r52;
        r52.setBackground(null);
        this.clearButton.setText(LocaleController.getString(R.string.ReportSend));
        ScaleStateListAnimator.apply(this.clearButton);
        BottomSheetCell.m12498$$Nest$fgetbackground(this.clearButton).setOnClickListener(new ReportAlert$$ExternalSyntheticLambda1(this, r21));
        r7.addView(this.clearButton, LayoutHelper.createFrame(-1, 50.0f, 51, 0.0f, 357.0f, 0.0f, 0.0f));
        this.smoothKeyboardAnimationEnabled = true;
        return;
    L5:
        if (r21 != 6) goto L7;
        r63.setText(LocaleController.getString(R.string.ReportTitleFake));
        goto L17
    L7:
        if (r21 != 1) goto L10;
        r63.setText(LocaleController.getString(R.string.ReportTitleViolence));
        goto L17
    L10:
        if (r21 != 2) goto L12;
        r63.setText(LocaleController.getString(R.string.ReportTitleChild));
        goto L17
    L12:
        if (r21 != 5) goto L15;
        r63.setText(LocaleController.getString(R.string.ReportTitlePornography));
        goto L17
    L15:
        if (r21 != 100) goto L17;
        r63.setText(LocaleController.getString(R.string.ReportChat));
        goto L17
    }

    private /* synthetic */ boolean lambda$new$0(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        BottomSheetCell.m12498$$Nest$fgetbackground(this.clearButton).callOnClick();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$new$1(int r1, View r2) {
        AndroidUtilities.hideKeyboard(this.editText);
        onSend(r1, this.editText.getText().toString());
        dismiss();
    }

    public abstract void onSend(int r1, String r2);
}
