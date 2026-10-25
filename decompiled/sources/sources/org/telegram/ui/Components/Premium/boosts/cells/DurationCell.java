package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class DurationCell extends BaseCell {
    private Object code;
    protected final SimpleTextView totalTextView;

    public DurationCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10, r11);
        this.imageView.setVisibility(8);
        SimpleTextView r0 = this.subtitleTextView;
        int r1 = Theme.key_windowBackgroundWhiteGrayText4;
        r0.setTextColor(Theme.getColor(r1, r11));
        SimpleTextView r02 = new SimpleTextView(r10);
        this.totalTextView = r02;
        r02.setTextSize(16);
        r02.setTextColor(Theme.getColor(r1, r11));
        int r12 = 5;
        if (LocaleController.isRTL == false) goto L5;
        int r112 = 3;
    L6:
        r02.setGravity(r112);
        addView(r02);
        boolean r9 = LocaleController.isRTL;
        if (r9 == false) goto L9;
        r12 = 3;
    L9:
        int r4 = r12 | 16;
        if (r9 == false) goto L12;
        float r5 = 20.0f;
    L13:
        if (r9 == false) goto L15;
        float r7 = 0.0f;
    L16:
        r02.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r4, r5, 0.0f, r7, 0.0f));
        return;
    L15:
        r7 = 20.0f;
        goto L16
    L12:
        r5 = 0.0f;
        goto L13
    L5:
        r112 = 5;
        goto L6
    }

    public Object getGifCode() {
        return this.code;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public boolean needCheck() {
        return true;
    }

    public void setDuration(Object r5, int r6, int r7, long r8, CharSequence r10, boolean r11, boolean r12) {
        this.code = r5;
        SimpleTextView r52 = this.titleTextView;
        if (r6 < 12) goto L5;
        r52.setText(LocaleController.formatPluralString("Years", 1, new Object[0]));
    L6:
        StringBuilder r53 = new StringBuilder();
        BillingController r62 = BillingController.getInstance();
        if (r7 <= 0) goto L9;
        long r1 = r8 / r7;
    L10:
        r53.append(r62.formatCurrency(r1, r10.toString()));
        r53.append(" x ");
        r53.append(r7);
        setSubtitle(r53.toString());
        SimpleTextView r54 = this.totalTextView;
        BillingController r63 = BillingController.getInstance();
        if (r7 > 0) goto L14;
        r8 = 0;
    L14:
        r54.setText(r63.formatCurrency(r8, r10.toString()));
        setDivider(r11);
        this.radioButton.setChecked(r12, false);
        return;
    L9:
        r1 = r8;
        goto L10
    L5:
        r52.setText(LocaleController.formatPluralString("Months", r6, new Object[0]));
        goto L6
    }
}
