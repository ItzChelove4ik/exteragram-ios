package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextCheckbox2Cell extends FrameLayout {
    public static final Property<TextCheckbox2Cell, Float> ANIMATION_PROGRESS = null;
    private int animatedColorBackground;
    private Paint animationPaint;
    private float animationProgress;
    public CheckBox2 checkbox;
    private int height;
    private boolean isMultiline;
    private float lastTouchX;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.TextCheckbox2Cell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<TextCheckbox2Cell> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Float get(TextCheckbox2Cell r1) {
            return Float.valueOf(TextCheckbox2Cell.m7059$$Nest$fgetanimationProgress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((TextCheckbox2Cell) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(TextCheckbox2Cell r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(TextCheckbox2Cell r1, float r2) {
            TextCheckbox2Cell.m7060$$Nest$msetAnimationProgress(r1, r2);
            r1.invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetanimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7059$$Nest$fgetanimationProgress(TextCheckbox2Cell r0) {
        return r0.animationProgress;
    }

    /* renamed from: -$$Nest$msetAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7060$$Nest$msetAnimationProgress(TextCheckbox2Cell r0, float r1) {
        r0.setAnimationProgress(r1);
    }

    static {
        ANIMATION_PROGRESS = new AnonymousClass1("animationProgress");
    }

    public TextCheckbox2Cell(Context r2) {
        this(r2, 21);
    }

    public TextCheckbox2Cell(Context r2, int r3) {
        this(r2, r3, false);
    }

    public TextCheckbox2Cell(Context r19, int r20, boolean r21) {
        super(r19);
        this.height = 50;
        TextView r3 = new TextView(r19);
        this.textView = r3;
        if (r21 == false) goto L5;
        int r4 = Theme.key_dialogTextBlack;
    L6:
        r3.setTextColor(Theme.getColor(r4));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r32 = this.textView;
        int r6 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r42 = 5;
    L10:
        r32.setGravity(r42 | 16);
        TextView r33 = this.textView;
        TextUtils.TruncateAt r43 = TextUtils.TruncateAt.END;
        r33.setEllipsize(r43);
        TextView r34 = this.textView;
        boolean r8 = LocaleController.isRTL;
        if (r8 == false) goto L13;
        int r9 = 5;
    L14:
        int r12 = r9 | 48;
        float r92 = 64.0f;
        if (r8 == false) goto L17;
        float r13 = r20;
    L18:
        if (r8 == false) goto L20;
        float r15 = 64.0f;
    L21:
        addView(r34, LayoutHelper.createFrame(-1, -1.0f, r12, r13, 0.0f, r15, 0.0f));
        TextView r35 = new TextView(r19);
        this.valueTextView = r35;
        if (r21 == false) goto L24;
        int r82 = Theme.key_dialogIcon;
    L25:
        r35.setTextColor(Theme.getColor(r82));
        this.valueTextView.setTextSize(1, 13.0f);
        this.valueTextView.setTypeface(AndroidUtilities.regular());
        TextView r36 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L28;
        int r83 = 5;
    L29:
        r36.setGravity(r83);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
        this.valueTextView.setEllipsize(r43);
        TextView r37 = this.valueTextView;
        boolean r44 = LocaleController.isRTL;
        if (r44 == false) goto L32;
        int r10 = 5;
    L33:
        int r132 = r10 | 48;
        if (r44 == false) goto L36;
        float r14 = r20;
    L37:
        if (r44 == true) goto L40;
        r92 = r20;
    L40:
        addView(r37, LayoutHelper.createFrame(-2, -2.0f, r132, r14, 36.0f, r92, 0.0f));
        CheckBox2 r2 = new CheckBox2(r19, 21);
        this.checkbox = r2;
        r2.setDrawUnchecked(true);
        this.checkbox.setDrawBackgroundAsArc(10);
        this.checkbox.setDuration(100);
        this.checkbox.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        CheckBox2 r1 = this.checkbox;
        if (LocaleController.isRTL == false) goto L43;
        r6 = 5;
    L43:
        addView(r1, LayoutHelper.createFrame(20, 20.0f, r6 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        setClipChildren(false);
        return;
    L36:
        r14 = 64.0f;
        goto L37
    L32:
        r10 = 3;
        goto L33
    L28:
        r83 = 3;
        goto L29
    L24:
        r82 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L25
    L20:
        r15 = r20;
        goto L21
    L17:
        r13 = 64.0f;
        goto L18
    L13:
        r9 = 3;
        goto L14
    L9:
        r42 = 3;
        goto L10
    L5:
        r4 = Theme.key_windowBackgroundWhiteBlackText;
        goto L6
    }

    private void setAnimationProgress(float r3) {
        this.animationProgress = r3;
        Math.max(this.lastTouchX, getMeasuredWidth() - this.lastTouchX);
        AndroidUtilities.dp(40.0f);
        getMeasuredHeight();
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.animatedColorBackground == 0) goto L6;
        float r0 = Math.max(this.lastTouchX, getMeasuredWidth() - this.lastTouchX) + AndroidUtilities.dp(40.0f);
        r9.drawCircle(this.lastTouchX, getMeasuredHeight() / 2, r0 * this.animationProgress, this.animationPaint);
    L6:
        if (this.needDivider == true) goto L8;
        return;
    L8:
        if (LocaleController.isRTL == false) goto L11;
        float r02 = 0.0f;
    L10:
        float r3 = r02;
        float r4 = getMeasuredHeight() - 1;
        int r03 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L15;
        int r1 = AndroidUtilities.dp(64.0f);
    L16:
        r9.drawLine(r3, r4, r03 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L15:
        r1 = 0;
        goto L16
    L11:
        r02 = AndroidUtilities.dp(64.0f);
        goto L10
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setClassName("android.widget.checkbox");
        r3.setCheckable(true);
        r3.setChecked(this.checkbox.isChecked());
        StringBuilder r0 = new StringBuilder();
        r0.append(this.textView.getText());
        if (this.valueTextView == null) goto L5;
        r0.append("\n");
        r0.append(this.valueTextView.getText());
    L5:
        r3.setText(r0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        if (this.isMultiline == false) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        return;
    L6:
        int r32 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
        if (this.valueTextView.getVisibility() != 0) goto L9;
        float r42 = 64.0f;
    L10:
        super.onMeasure(r32, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r42) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        return;
    L9:
        r42 = this.height;
        goto L10
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        this.lastTouchX = r2.getX();
        return super.onTouchEvent(r2);
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        clearAnimation();
        this.animatedColorBackground = 0;
        super.setBackgroundColor(r2);
    }

    public void setCheckboxGravityTop() {
        CheckBox2 r8 = this.checkbox;
        if (LocaleController.isRTL == false) goto L5;
        int r0 = 5;
    L6:
        r8.setLayoutParams(LayoutHelper.createFrame(20, 20.0f, r0 | 48, 22.0f, 22.0f, 22.0f, 0.0f));
        return;
    L5:
        r0 = 3;
        goto L6
    }

    public void setChecked(boolean r2) {
        this.checkbox.setChecked(r2, true);
    }

    public void setHeight(int r1) {
        this.height = r1;
    }

    @Override // android.view.View
    public void setPressed(boolean r1) {
        super.setPressed(r1);
    }

    public void setTextAndValue(String r2, String r3, boolean r4, boolean r5) {
        this.textView.setText(r2);
        this.valueTextView.setText(r3);
        this.needDivider = r5;
        this.valueTextView.setVisibility(0);
        this.isMultiline = r4;
        TextView r22 = this.valueTextView;
        if (r4 == false) goto L5;
        r22.setLines(0);
        this.valueTextView.setMaxLines(0);
        this.valueTextView.setSingleLine(false);
        this.valueTextView.setEllipsize(null);
        this.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(11.0f));
    L6:
        FrameLayout.LayoutParams r23 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        r23.height = -2;
        r23.topMargin = AndroidUtilities.dp(10.0f);
        this.textView.setLayoutParams(r23);
        setWillNotDraw(!r5);
        return;
    L5:
        r22.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.valueTextView.setPadding(0, 0, 0, 0);
        goto L6
    }

    public void setTypeface(Typeface r1) {
        this.textView.setTypeface(r1);
    }
}
