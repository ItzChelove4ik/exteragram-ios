package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes6.dex */
public class LanguageCell extends FrameLayout {
    private LocaleController.LocaleInfo currentLocale;
    private int marginEndDp;
    private int marginStartDp;
    private boolean needDivider;
    private RadioButton radioButton;
    private TextView textView;
    public TextView textView2;

    public LanguageCell(Context r15) {
        super(r15);
        this.marginStartDp = 62;
        this.marginEndDp = 23;
        if (Theme.dividerPaint != null) goto L5;
        Theme.createCommonResources(r15);
    L5:
        int r0 = 0;
        setWillNotDraw(false);
        RadioButton r1 = new RadioButton(r15);
        this.radioButton = r1;
        r1.setSize(AndroidUtilities.dp(20.0f));
        this.radioButton.setColor(Theme.getColor(Theme.key_dialogRadioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        RadioButton r12 = this.radioButton;
        boolean r2 = LocaleController.isRTL;
        int r3 = 3;
        if (r2 == false) goto L8;
        int r5 = 5;
    L9:
        int r8 = r5 | 16;
        if (r2 == false) goto L12;
        int r6 = 0;
    L13:
        float r9 = r6;
        if (r2 == false) goto L16;
        r0 = 20;
    L16:
        addView(r12, LayoutHelper.createFrame(22, 22.0f, r8, r9, 0.0f, r0, 0.0f));
        TextView r02 = new TextView(r15);
        this.textView = r02;
        r02.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setSingleLine(true);
        TextView r03 = this.textView;
        TextUtils.TruncateAt r13 = TextUtils.TruncateAt.END;
        r03.setEllipsize(r13);
        TextView r04 = this.textView;
        if (LocaleController.isRTL == false) goto L19;
        int r52 = 5;
    L20:
        r04.setGravity(r52 | 16);
        TextView r05 = this.textView;
        boolean r53 = LocaleController.isRTL;
        if (r53 == false) goto L23;
        int r62 = 5;
    L24:
        int r92 = r62 | 48;
        if (r53 == false) goto L28;
        int r63 = this.marginEndDp;
    L27:
        float r64 = r63;
        if (r53 == false) goto L32;
        int r54 = this.marginStartDp;
    L33:
        addView(r05, LayoutHelper.createFrame(-1, -1.0f, r92, r64, 0.0f, r54, 17.0f));
        TextView r06 = new TextView(r15);
        this.textView2 = r06;
        r06.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
        this.textView2.setTextSize(1, 13.0f);
        this.textView2.setTypeface(AndroidUtilities.regular());
        this.textView2.setSingleLine(true);
        this.textView2.setEllipsize(r13);
        TextView r152 = this.textView2;
        if (LocaleController.isRTL == false) goto L36;
        int r07 = 5;
    L37:
        r152.setGravity(r07 | 16);
        TextView r153 = this.textView2;
        boolean r08 = LocaleController.isRTL;
        if (r08 == false) goto L40;
        r3 = 5;
    L40:
        int r65 = r3 | 48;
        if (r08 == false) goto L44;
        int r14 = this.marginEndDp;
    L43:
        float r16 = r14;
        if (r08 == false) goto L48;
        int r09 = this.marginStartDp;
    L49:
        addView(r153, LayoutHelper.createFrame(-1, -1.0f, r65, r16, 20.0f, r09, 0.0f));
        return;
    L48:
        r09 = this.marginEndDp;
        goto L49
    L44:
        r14 = this.marginStartDp;
        goto L43
    L36:
        r07 = 3;
        goto L37
    L32:
        r54 = this.marginEndDp;
        goto L33
    L28:
        r63 = this.marginStartDp;
        goto L27
    L23:
        r62 = 3;
        goto L24
    L19:
        r52 = 3;
        goto L20
    L12:
        r6 = 20;
        goto L13
    L8:
        r5 = 3;
        goto L9
    }

    public LocaleController.LocaleInfo getCurrentLocale() {
        return this.currentLocale;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r2 = r0;
        float r3 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(this.marginStartDp - 3);
    L13:
        r8.drawLine(r2, r3, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(this.marginStartDp - 3);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void setLanguage(LocaleController.LocaleInfo r2, String r3, boolean r4) {
        TextView r0 = this.textView;
        if (r3 != null) goto L6;
        r3 = r2.name;
    L6:
        r0.setText(r3);
        this.textView2.setText(r2.nameEnglish);
        this.currentLocale = r2;
        this.needDivider = r4;
    }

    public void setLanguageSelected(boolean r1, boolean r2) {
        this.radioButton.setChecked(r1, r2);
    }

    public void setValue(CharSequence r2, CharSequence r3) {
        this.textView.setText(r2);
        this.textView2.setText(r3);
        this.radioButton.setChecked(false, false);
        this.currentLocale = null;
        this.needDivider = false;
    }
}
