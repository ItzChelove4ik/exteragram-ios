package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes3.dex */
public class RadioColorCell extends FrameLayout {
    public int heightDp;
    private RadioButton radioButton;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView text2View;
    private TextView textView;

    public RadioColorCell(Context r2) {
        this(r2, null);
    }

    public RadioColorCell(Context r14, Theme.ResourcesProvider r15) {
        super(r14);
        this.heightDp = 50;
        this.resourcesProvider = r15;
        RadioButton r152 = new RadioButton(r14);
        this.radioButton = r152;
        r152.setSize(AndroidUtilities.dp(20.0f));
        this.radioButton.setColor(getThemedColor(Theme.key_dialogRadioBackground), getThemedColor(Theme.key_dialogRadioBackgroundChecked));
        RadioButton r153 = this.radioButton;
        boolean r0 = LocaleController.isRTL;
        int r1 = 3;
        if (r0 == false) goto L5;
        int r3 = 5;
    L6:
        int r6 = r3 | 48;
        int r32 = 18;
        if (r0 == false) goto L9;
        int r5 = 0;
    L10:
        float r7 = r5;
        if (r0 == true) goto L14;
        r32 = 0;
    L14:
        addView(r153, LayoutHelper.createFrame(22, 22.0f, r6, r7, 14.0f, r32, 0.0f));
        TextView r154 = new TextView(r14);
        this.textView = r154;
        r154.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r155 = this.textView;
        if (LocaleController.isRTL == false) goto L17;
        int r02 = 5;
    L18:
        r155.setGravity(r02 | 16);
        TextView r156 = this.textView;
        boolean r03 = LocaleController.isRTL;
        if (r03 == false) goto L21;
        int r4 = 5;
    L22:
        int r72 = r4 | 48;
        int r42 = 51;
        if (r03 == false) goto L25;
        int r52 = 21;
    L26:
        float r8 = r52;
        if (r03 == false) goto L29;
        int r04 = 51;
    L30:
        addView(r156, LayoutHelper.createFrame(-2, -2.0f, r72, r8, 13.0f, r04, 0.0f));
        TextView r157 = new TextView(r14);
        this.text2View = r157;
        r157.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        this.text2View.setTextSize(1, 14.0f);
        TextView r142 = this.text2View;
        if (LocaleController.isRTL == false) goto L33;
        int r158 = 5;
    L34:
        r142.setGravity(r158 | 16);
        this.text2View.setVisibility(8);
        TextView r143 = this.text2View;
        boolean r159 = LocaleController.isRTL;
        if (r159 == false) goto L37;
        r1 = 5;
    L37:
        int r73 = r1 | 48;
        if (r159 == false) goto L40;
        int r05 = 21;
    L41:
        float r82 = r05;
        if (r159 == true) goto L45;
        r42 = 21;
    L45:
        addView(r143, LayoutHelper.createFrame(-2, -2.0f, r73, r82, 37.0f, r42, 0.0f));
        return;
    L40:
        r05 = 51;
        goto L41
    L33:
        r158 = 3;
        goto L34
    L29:
        r04 = 21;
        goto L30
    L25:
        r52 = 51;
        goto L26
    L21:
        r4 = 3;
        goto L22
    L17:
        r02 = 3;
        goto L18
    L9:
        r5 = 18;
        goto L10
    L5:
        r3 = 3;
        goto L6
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.RadioButton");
        r2.setCheckable(true);
        r2.setChecked(this.radioButton.isChecked());
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        if (this.text2View.getVisibility() != 0) goto L5;
        this.text2View.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5) - AndroidUtilities.dp(72.0f), TLObject.FLAG_30), r6);
    L5:
        int r52 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30);
        int r62 = AndroidUtilities.dp(this.heightDp);
        if (this.text2View.getVisibility() != 0) goto L8;
        int r0 = AndroidUtilities.dp(4.0f) + this.text2View.getMeasuredHeight();
    L9:
        super.onMeasure(r52, View.MeasureSpec.makeMeasureSpec(r62 + r0, TLObject.FLAG_30));
        return;
    L8:
        r0 = 0;
        goto L9
    }

    public void setCheckColor(int r1, int r2) {
        this.radioButton.setColor(r1, r2);
    }

    public void setChecked(boolean r1, boolean r2) {
        this.radioButton.setChecked(r1, r2);
    }

    public void setTextAndText2AndValue(CharSequence r2, CharSequence r3, boolean r4) {
        this.textView.setText(r2);
        this.text2View.setVisibility(0);
        this.text2View.setText(r3);
        this.radioButton.setChecked(r4, false);
    }

    public void setTextAndValue(CharSequence r2, boolean r3) {
        this.textView.setText(r2);
        this.text2View.setVisibility(8);
        this.radioButton.setChecked(r3, false);
    }
}
