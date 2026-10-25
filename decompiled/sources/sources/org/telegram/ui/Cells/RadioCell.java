package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes6.dex */
public class RadioCell extends FrameLayout {
    private boolean needDivider;
    private RadioButton radioButton;
    private Theme.ResourcesProvider resourcesProvider;
    private TextView textView;

    public RadioCell(Context r3) {
        this(r3, false, 21);
    }

    public RadioCell(Context r3, Theme.ResourcesProvider r4) {
        this(r3, false, 21, r4);
    }

    public RadioCell(Context r2, boolean r3, int r4) {
        this(r2, r3, r4, null);
    }

    public RadioCell(Context r17, boolean r18, int r19, Theme.ResourcesProvider r20) {
        super(r17);
        this.resourcesProvider = r20;
        TextView r4 = new TextView(r17);
        this.textView = r4;
        if (r18 == false) goto L5;
        r4.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r20));
    L6:
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r42 = this.textView;
        int r7 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r5 = 5;
    L10:
        r42.setGravity(r5 | 16);
        TextView r43 = this.textView;
        if (LocaleController.isRTL == false) goto L13;
        int r52 = 5;
    L14:
        float r12 = r19;
        addView(r43, LayoutHelper.createFrame(-1, -1.0f, r52 | 48, r12, 0.0f, r12, 0.0f));
        RadioButton r44 = new RadioButton(r17);
        this.radioButton = r44;
        r44.setSize(AndroidUtilities.dp(20.0f));
        RadioButton r1 = this.radioButton;
        if (r18 == false) goto L17;
        r1.setColor(Theme.getColor(Theme.key_dialogRadioBackground, r20), Theme.getColor(Theme.key_dialogRadioBackgroundChecked, r20));
    L18:
        RadioButton r13 = this.radioButton;
        boolean r3 = LocaleController.isRTL;
        if (r3 == true) goto L22;
        r7 = 5;
    L22:
        int r10 = r7 | 48;
        int r45 = 0;
        if (r3 == false) goto L25;
        int r53 = r19 + 1;
    L26:
        float r11 = r53;
        if (r3 == true) goto L30;
        r45 = r19 + 1;
    L30:
        addView(r13, LayoutHelper.createFrame(22, 22.0f, r10, r11, 14.0f, r45, 0.0f));
        return;
    L25:
        r53 = 0;
        goto L26
    L17:
        r1.setColor(Theme.getColor(Theme.key_radioBackground, r20), Theme.getColor(Theme.key_radioBackgroundChecked, r20));
        goto L18
    L13:
        r52 = 3;
        goto L14
    L9:
        r5 = 3;
        goto L10
    L5:
        r4.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r20));
        goto L6
    }

    public void hideRadioButton() {
        this.radioButton.setVisibility(8);
    }

    public boolean isChecked() {
        return this.radioButton.isChecked();
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(20.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(20.0f);
        goto L7
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.RadioButton");
        r2.setCheckable(true);
        r2.setChecked(isChecked());
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        setMeasuredDimension(View.MeasureSpec.getSize(r4), AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0));
        int r42 = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(34.0f);
        this.radioButton.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22.0f), TLObject.FLAG_30));
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
    }

    public void setChecked(boolean r1, boolean r2) {
        this.radioButton.setChecked(r1, r2);
    }

    public void setEnabled(boolean r8, ArrayList<Animator> r9) {
        super.setEnabled(r8);
        TextView r0 = this.textView;
        float r1 = 0.5f;
        if (r9 == null) goto L12;
        if (r8 == false) goto L6;
        float r3 = 1.0f;
    L7:
        float[] r5 = {r3};
        Property r32 = View.ALPHA;
        r9.add(ObjectAnimator.ofFloat(r0, r32, r5));
        RadioButton r7 = this.radioButton;
        if (r8 == false) goto L10;
        r1 = 1.0f;
    L10:
        r9.add(ObjectAnimator.ofFloat(r7, r32, new float[]{r1}));
        return;
    L6:
        r3 = 0.5f;
        goto L7
    L12:
        if (r8 == false) goto L14;
        float r92 = 1.0f;
    L15:
        r0.setAlpha(r92);
        RadioButton r72 = this.radioButton;
        if (r8 == false) goto L18;
        r1 = 1.0f;
    L18:
        r72.setAlpha(r1);
        return;
    L14:
        r92 = 0.5f;
        goto L15
    }

    public void setRadioIcon(Drawable r1) {
        this.radioButton.setIcon(r1);
    }

    public void setText(CharSequence r2, boolean r3, boolean r4) {
        this.textView.setText(r2);
        this.radioButton.setChecked(r3, false);
        this.needDivider = r4;
        setWillNotDraw(!r4);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }
}
