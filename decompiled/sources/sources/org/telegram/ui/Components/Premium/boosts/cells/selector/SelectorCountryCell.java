package org.telegram.ui.Components.Premium.boosts.cells.selector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.cells.BaseCell;
import org.telegram.ui.Components.RadioButton;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SelectorCountryCell extends BaseCell {
    private final CheckBox2 checkBox;
    private TLRPC.TL_help_country country;
    private TextPaint paint;
    private final Runnable setCountryRunnable;

    public static class SpaceDrawable extends ReplacementSpan {
        private final int size;

        public SpaceDrawable(int r1) {
            this.size = r1;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return AndroidUtilities.dp(this.size);
        }
    }

    public static /* synthetic */ void $r8$lambda$nhS4hHVWjkvLV4P1UpuZEWPtizA(SelectorCountryCell r0) {
        r0.setCountryInternal();
    }

    public SelectorCountryCell(Context r9, Theme.ResourcesProvider r10) {
        super(r9, r10);
        this.paint = new TextPaint();
        this.setCountryRunnable = new SelectorCountryCell$$ExternalSyntheticLambda0(this);
        this.paint.setTextSize(AndroidUtilities.dp(20.0f));
        this.radioButton.setVisibility(8);
        this.imageView.setVisibility(8);
        CheckBox2 r0 = new CheckBox2(r9, 21, r10);
        this.checkBox = r0;
        r0.setColor(Theme.key_dialogRoundCheckBox, Theme.key_checkboxDisabled, Theme.key_dialogRoundCheckBoxCheck);
        r0.setDrawUnchecked(true);
        r0.setDrawBackgroundAsArc(10);
        addView(r0);
        r0.setChecked(false, false);
        if (LocaleController.isRTL == false) goto L5;
        int r8 = 5;
    L6:
        r0.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, r8 | 16, 13.0f, 0.0f, 14.0f, 0.0f));
        return;
    L5:
        r8 = 3;
        goto L6
    }

    private CharSequence getCountryNameWithFlag(TLRPC.TL_help_country r6) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        CharSequence r5 = Emoji.replaceWithRestrictedEmoji(LocaleController.getLanguageFlag(r6.iso2), this.paint.getFontMetricsInt(), 0, this.setCountryRunnable);
        if (r5 == null) goto L5;
        r0.append(r5).append(" ");
        r0.setSpan(new SpaceDrawable(16), r5.length(), r5.length() + 1, 0);
    L6:
        String r52 = LocaleController.getCountryName(r6.iso2);
        if (TextUtils.isEmpty(r52) == false) goto L9;
        r52 = r6.default_name;
    L9:
        r0.append(r52);
        return r0;
    L5:
        r0.append(" ");
        r0.setSpan(new SpaceDrawable(34), 0, 1, 0);
        goto L6
    }

    private void setCountryInternal() {
        this.titleTextView.setText(getCountryNameWithFlag(this.country));
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public int dividerPadding() {
        return 22;
    }

    public TLRPC.TL_help_country getCountry() {
        return this.country;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public int getFullHeight() {
        return 44;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public boolean needCheck() {
        return true;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public void setChecked(boolean r2, boolean r3) {
        if (this.checkBox.getVisibility() != 0) goto L6;
        this.checkBox.setChecked(r2, r3);
        return;
    }

    public void setCountry(TLRPC.TL_help_country r1, boolean r2) {
        this.country = r1;
        setCountryInternal();
        setDivider(r2);
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public void updateLayouts() {
        SimpleTextView r1 = this.titleTextView;
        boolean r2 = LocaleController.isRTL;
        int r3 = 3;
        if (r2 == false) goto L5;
        int r5 = 5;
    L6:
        int r8 = r5 | 16;
        if (r2 == false) goto L9;
        float r9 = 20.0f;
    L10:
        if (r2 == false) goto L12;
        float r11 = 52.0f;
    L13:
        r1.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r8, r9, 0.0f, r11, 0.0f));
        SimpleTextView r12 = this.subtitleTextView;
        boolean r22 = LocaleController.isRTL;
        if (r22 == false) goto L16;
        int r6 = 5;
    L17:
        int r16 = r6 | 16;
        if (r22 == false) goto L20;
        float r17 = 20.0f;
    L21:
        if (r22 == false) goto L23;
        float r19 = 52.0f;
    L24:
        r12.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r16, r17, 0.0f, r19, 0.0f));
        RadioButton r0 = this.radioButton;
        boolean r13 = LocaleController.isRTL;
        if (r13 == false) goto L27;
        r3 = 5;
    L27:
        int r62 = r3 | 16;
        if (r13 == false) goto L30;
        float r7 = 15.0f;
    L31:
        if (r13 == false) goto L33;
        float r92 = 20.0f;
    L34:
        r0.setLayoutParams(LayoutHelper.createFrame(22, 22.0f, r62, r7, 0.0f, r92, 0.0f));
        return;
    L33:
        r92 = 15.0f;
        goto L34
    L30:
        r7 = 20.0f;
        goto L31
    L23:
        r19 = 20.0f;
        goto L24
    L20:
        r17 = 52.0f;
        goto L21
    L16:
        r6 = 3;
        goto L17
    L12:
        r11 = 20.0f;
        goto L13
    L9:
        r9 = 52.0f;
        goto L10
    L5:
        r5 = 3;
        goto L6
    }
}
