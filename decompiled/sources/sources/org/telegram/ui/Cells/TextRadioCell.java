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
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes6.dex */
public class TextRadioCell extends FrameLayout {
    public static final Property<TextRadioCell, Float> ANIMATION_PROGRESS = null;
    private int animatedColorBackground;
    private Paint animationPaint;
    private float animationProgress;
    private int height;
    private boolean isMultiline;
    private boolean isRTL;
    private float lastTouchX;
    private boolean needDivider;
    private int padding;
    private RadioButton radioButton;
    private TextView textView;
    private TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.TextRadioCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<TextRadioCell> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Float get(TextRadioCell r1) {
            return Float.valueOf(TextRadioCell.m7064$$Nest$fgetanimationProgress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((TextRadioCell) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(TextRadioCell r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(TextRadioCell r1, float r2) {
            TextRadioCell.m7065$$Nest$msetAnimationProgress(r1, r2);
            r1.invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetanimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7064$$Nest$fgetanimationProgress(TextRadioCell r0) {
        return r0.animationProgress;
    }

    /* renamed from: -$$Nest$msetAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7065$$Nest$msetAnimationProgress(TextRadioCell r0, float r1) {
        r0.setAnimationProgress(r1);
    }

    static {
        ANIMATION_PROGRESS = new AnonymousClass1("animationProgress");
    }

    public TextRadioCell(Context r2) {
        this(r2, 21);
    }

    public TextRadioCell(Context r2, int r3) {
        this(r2, r3, false);
    }

    public TextRadioCell(Context r18, int r19, boolean r20) {
        super(r18);
        this.height = 50;
        this.padding = r19;
        TextView r3 = new TextView(r18);
        this.textView = r3;
        if (r20 == false) goto L5;
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
        float r13 = r19;
    L18:
        if (r8 == false) goto L20;
        float r15 = 64.0f;
    L21:
        addView(r34, LayoutHelper.createFrame(-1, -1.0f, r12, r13, 0.0f, r15, 0.0f));
        TextView r35 = new TextView(r18);
        this.valueTextView = r35;
        if (r20 == false) goto L24;
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
        int r84 = 5;
    L33:
        int r122 = r84 | 48;
        if (r44 == false) goto L36;
        float r132 = r19;
    L37:
        if (r44 == true) goto L40;
        r92 = r19;
    L40:
        addView(r37, LayoutHelper.createFrame(-2, -2.0f, r122, r132, 36.0f, r92, 0.0f));
        RadioButton r2 = new RadioButton(r18);
        this.radioButton = r2;
        r2.setSize(AndroidUtilities.dp(20.0f));
        this.radioButton.setColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_radioBackgroundChecked));
        RadioButton r1 = this.radioButton;
        if (LocaleController.isRTL == false) goto L43;
        r6 = 5;
    L43:
        addView(r1, LayoutHelper.createFrame(20, 20.0f, r6 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        this.isRTL = LocaleController.isRTL;
        setClipChildren(false);
        return;
    L36:
        r132 = 64.0f;
        goto L37
    L32:
        r84 = 3;
        goto L33
    L28:
        r83 = 3;
        goto L29
    L24:
        r82 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L25
    L20:
        r15 = r19;
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
        r3.setCheckable(true);
        r3.setChecked(this.radioButton.isChecked());
        if (this.radioButton.isChecked() == false) goto L6;
        int r0 = R.string.NotificationsOn;
    L7:
        r3.setContentDescription(LocaleController.getString(r0));
        StringBuilder r02 = new StringBuilder();
        r02.append(this.textView.getText());
        if (TextUtils.isEmpty(this.valueTextView.getText()) == true) goto L10;
        r02.append("\n");
        r02.append(this.valueTextView.getText());
    L10:
        r3.setContentDescription(r02);
        r3.setClassName("android.widget.RadioButton");
        return;
    L6:
        r0 = R.string.NotificationsOff;
        goto L7
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

    public void setChecked(boolean r2) {
        this.radioButton.setChecked(r2, true);
    }

    public void setHeight(int r1) {
        this.height = r1;
    }

    @Override // android.view.View
    public void setPressed(boolean r1) {
        super.setPressed(r1);
    }

    public void setTextAndValueAndCheck(String r2, String r3, boolean r4, boolean r5, boolean r6) {
        this.textView.setText(r2);
        this.valueTextView.setText(r3);
        this.radioButton.setChecked(r4, false);
        this.needDivider = r6;
        this.valueTextView.setVisibility(0);
        this.isMultiline = r5;
        TextView r22 = this.valueTextView;
        if (r5 == false) goto L5;
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
        setWillNotDraw(!r6);
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

    public void updateRTL() {
        boolean r0 = this.isRTL;
        boolean r1 = LocaleController.isRTL;
        if (r0 != r1) goto L5;
        return;
    L5:
        this.isRTL = r1;
        TextView r02 = this.textView;
        int r2 = 3;
        if (r1 == false) goto L8;
        int r12 = 5;
    L9:
        r02.setGravity(r12 | 16);
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        boolean r13 = LocaleController.isRTL;
        if (r13 == false) goto L12;
        int r4 = 5;
    L13:
        r03.gravity = r4 | 48;
        float r42 = 64.0f;
        if (r13 == false) goto L16;
        float r14 = this.padding;
    L17:
        r03.leftMargin = AndroidUtilities.dp(r14);
        if (LocaleController.isRTL == false) goto L20;
        float r15 = 64.0f;
    L21:
        r03.rightMargin = AndroidUtilities.dp(r15);
        this.textView.setLayoutParams(r03);
        TextView r04 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L24;
        int r16 = 5;
    L25:
        r04.setGravity(r16);
        FrameLayout.LayoutParams r05 = (FrameLayout.LayoutParams) this.valueTextView.getLayoutParams();
        boolean r17 = LocaleController.isRTL;
        if (r17 == false) goto L28;
        int r5 = 5;
    L29:
        r05.gravity = r5 | 48;
        if (r17 == false) goto L32;
        float r18 = this.padding;
    L33:
        r05.leftMargin = AndroidUtilities.dp(r18);
        if (LocaleController.isRTL == true) goto L37;
        r42 = this.padding;
    L37:
        r05.rightMargin = AndroidUtilities.dp(r42);
        this.valueTextView.setLayoutParams(r05);
        FrameLayout.LayoutParams r06 = (FrameLayout.LayoutParams) this.radioButton.getLayoutParams();
        if (LocaleController.isRTL == false) goto L40;
        r2 = 5;
    L40:
        r06.gravity = r2 | 16;
        this.radioButton.setLayoutParams(r06);
        return;
    L32:
        r18 = 64.0f;
        goto L33
    L28:
        r5 = 3;
        goto L29
    L24:
        r16 = 3;
        goto L25
    L20:
        r15 = this.padding;
        goto L21
    L16:
        r14 = 64.0f;
        goto L17
    L12:
        r4 = 3;
        goto L13
    L8:
        r12 = 3;
        goto L9
    }
}
