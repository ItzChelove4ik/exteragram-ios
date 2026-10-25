package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;

/* loaded from: classes6.dex */
public class NotificationsCheckCell extends FrameLayout {
    private boolean animationsEnabled;
    private Switch checkBox;
    private int currentHeight;
    private boolean drawLine;
    private ImageView imageView;
    private boolean isMultiline;
    private TextView multilineValueTextView;
    private boolean needDivider;
    private Theme.ResourcesProvider resourcesProvider;
    private final int textOffset;
    private TextView textView;
    private AnimatedTextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.NotificationsCheckCell$1, reason: invalid class name */
    public class AnonymousClass1 extends Switch {
        final /* synthetic */ NotificationsCheckCell this$0;

        public AnonymousClass1(NotificationsCheckCell r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.Switch
        public int processColor(int r1) {
            return this.this$0.processColor(r1);
        }
    }

    public NotificationsCheckCell(Context r7) {
        this(r7, 21, 70, false, null);
    }

    public NotificationsCheckCell(Context r27, int r28, int r29, int r30, boolean r31, Theme.ResourcesProvider r32) {
        super(r27);
        this.drawLine = true;
        this.resourcesProvider = r32;
        int r4 = r30;
        this.textOffset = r4;
        setWillNotDraw(false);
        this.currentHeight = r29;
        int r6 = 3;
        if (r31 == false) goto L9;
        ImageView r8 = new ImageView(r27);
        this.imageView = r8;
        r8.setFocusable(false);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        ImageView r82 = this.imageView;
        if (LocaleController.isRTL == false) goto L7;
        int r9 = 5;
    L8:
        addView(r82, LayoutHelper.createFrame(48, 48.0f, r9 | 16, 9.0f, 0.0f, 7.0f, 0.0f));
        goto L9
    L7:
        r9 = 3;
    L9:
        TextView r83 = new TextView(r27);
        this.textView = r83;
        r83.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r32));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r84 = this.textView;
        if (LocaleController.isRTL == false) goto L12;
        int r92 = 5;
    L13:
        r84.setGravity(r92 | 16);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r85 = this.textView;
        boolean r93 = LocaleController.isRTL;
        if (r93 == false) goto L16;
        int r10 = 5;
    L17:
        int r13 = r10 | 48;
        float r102 = 88.0f;
        if (r93 == false) goto L20;
        float r14 = 88.0f;
    L24:
        float r15 = ((this.currentHeight - 70) / 2) + 13;
        if (r93 == false) goto L30;
        if (r31 == false) goto L28;
        int r94 = r4;
    L29:
        float r16 = r94;
    L31:
        addView(r85, LayoutHelper.createFrame(-1, -2.0f, r13, r14, r15, r16, 0.0f));
        AnimatedTextView r11 = new AnimatedTextView(r27);
        this.valueTextView = r11;
        r11.setAnimationProperties(0.55f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        AnimatedTextView r86 = this.valueTextView;
        int r95 = Theme.key_windowBackgroundWhiteGrayText2;
        r86.setTextColor(Theme.getColor(r95, r32));
        this.valueTextView.setTextSize(AndroidUtilities.dp(13.0f));
        AnimatedTextView r87 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L34;
        int r12 = 5;
    L35:
        r87.setGravity(r12);
        this.valueTextView.setPadding(0, 0, 0, 0);
        this.valueTextView.setEllipsizeByGradient(true);
        AnimatedTextView r88 = this.valueTextView;
        boolean r122 = LocaleController.isRTL;
        if (r122 == false) goto L38;
        int r132 = 5;
    L39:
        int r21 = r132 | 48;
        if (r122 == false) goto L42;
        float r22 = 88.0f;
    L46:
        if (r31 == false) goto L48;
        int r133 = 2;
    L49:
        float r134 = (29 - r133) + ((this.currentHeight - 70) / 2);
        if (r122 == false) goto L55;
        if (r31 == false) goto L53;
        int r123 = r4;
    L54:
        float r24 = r123;
    L56:
        addView(r88, LayoutHelper.createFrame(-1, -2.0f, r21, r22, r134, r24, 0.0f));
        TextView r89 = new TextView(r27);
        this.multilineValueTextView = r89;
        r89.setTextColor(Theme.getColor(r95, r32));
        this.multilineValueTextView.setTextSize(1, 13.0f);
        TextView r3 = this.multilineValueTextView;
        if (LocaleController.isRTL == false) goto L59;
        int r810 = 5;
    L60:
        r3.setGravity(r810);
        this.multilineValueTextView.setLines(0);
        this.multilineValueTextView.setMaxLines(0);
        this.multilineValueTextView.setSingleLine(false);
        this.multilineValueTextView.setEllipsize(null);
        this.multilineValueTextView.setPadding(0, 0, 0, 0);
        this.multilineValueTextView.setVisibility(8);
        TextView r33 = this.multilineValueTextView;
        boolean r811 = LocaleController.isRTL;
        if (r811 == false) goto L63;
        int r96 = 5;
    L64:
        int r135 = r96 | 48;
        if (r811 == false) goto L67;
        float r142 = 88.0f;
    L71:
        if (r31 == false) goto L73;
        int r97 = 2;
    L74:
        float r152 = (38 - r97) + ((this.currentHeight - 70) / 2);
        if (r811 == false) goto L80;
        if (r31 == true) goto L79;
        r4 = r28;
    L79:
        r102 = r4;
    L80:
        addView(r33, LayoutHelper.createFrame(-2, -2.0f, r135, r142, r152, r102, 0.0f));
        AnonymousClass1 r34 = new AnonymousClass1(this, r27, r32);
        this.checkBox = r34;
        int r1 = Theme.key_switchTrack;
        int r2 = Theme.key_switchTrackChecked;
        int r42 = Theme.key_windowBackgroundWhite;
        r34.setColors(r1, r2, r42, r42);
        Switch r17 = this.checkBox;
        if (LocaleController.isRTL == true) goto L84;
        r6 = 5;
    L84:
        addView(r17, LayoutHelper.createFrame(37, 40.0f, r6 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        this.checkBox.setFocusable(false);
        setClipChildren(false);
        return;
    L73:
        r97 = 0;
        goto L74
    L67:
        if (r31 == false) goto L69;
        int r98 = r4;
    L70:
        r142 = r98;
        goto L71
    L69:
        r98 = r28;
        goto L70
    L63:
        r96 = 3;
        goto L64
    L59:
        r810 = 3;
        goto L60
    L53:
        r123 = r28;
        goto L54
    L55:
        r24 = 88.0f;
        goto L56
    L48:
        r133 = 0;
        goto L49
    L42:
        if (r31 == false) goto L44;
        int r136 = r4;
    L45:
        r22 = r136;
        goto L46
    L44:
        r136 = r28;
        goto L45
    L38:
        r132 = 3;
        goto L39
    L34:
        r12 = 3;
        goto L35
    L28:
        r94 = r28;
        goto L29
    L30:
        r16 = 88.0f;
        goto L31
    L20:
        if (r31 == false) goto L22;
        int r112 = r4;
    L23:
        r14 = r112;
        goto L24
    L22:
        r112 = r28;
        goto L23
    L16:
        r10 = 3;
        goto L17
    L12:
        r92 = 3;
        goto L13
    }

    public NotificationsCheckCell(Context r7, int r8, int r9, boolean r10) {
        this(r7, r8, r9, r10, null);
    }

    public NotificationsCheckCell(Context r8, int r9, int r10, boolean r11, Theme.ResourcesProvider r12) {
        this(r8, r9, r10, 64, r11, r12);
    }

    public Switch getCheckBox() {
        return this.checkBox;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public TextView getMultilineValue() {
        return this.multilineValueTextView;
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
    }

    @Override // android.view.View
    public void onDraw(Canvas r17) {
        if (this.needDivider == false) goto L22;
        float r2 = 20.0f;
        if (LocaleController.isRTL == false) goto L9;
        float r1 = 0.0f;
    L7:
        float r5 = r1;
        float r6 = getMeasuredHeight() - 1;
        int r12 = getMeasuredWidth();
        if (LocaleController.isRTL == true) goto L16;
        int r22 = 0;
    L20:
        r17.drawLine(r5, r6, r12 - r22, getMeasuredHeight() - 1, Theme.dividerPaint);
        goto L22
    L16:
        if (this.imageView == null) goto L18;
        r2 = this.textOffset - AndroidUtilities.dp(2.0f);
    L18:
        r22 = AndroidUtilities.dp(r2);
        goto L20
    L9:
        if (this.imageView == null) goto L11;
        float r13 = this.textOffset - AndroidUtilities.dp(2.0f);
    L12:
        r1 = AndroidUtilities.dp(r13);
        goto L7
    L11:
        r13 = 20.0f;
    L22:
        if (this.drawLine == true) goto L24;
        return;
    L24:
        if (LocaleController.isRTL == false) goto L26;
        int r14 = AndroidUtilities.dp(76.0f);
    L27:
        r17.drawRect(r14, (getMeasuredHeight() - AndroidUtilities.dp(22.0f)) / 2, r14 + 2, r0 + AndroidUtilities.dp(22.0f), Theme.dividerPaint);
        return;
    L26:
        r14 = (getMeasuredWidth() - AndroidUtilities.dp(76.0f)) - 1;
        goto L27
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        r4.setClassName("android.widget.Switch");
        StringBuilder r0 = new StringBuilder();
        r0.append(this.textView.getText());
        if (this.isMultiline == false) goto L9;
        TextView r1 = this.multilineValueTextView;
        if (r1 != null) goto L7;
    L14:
        r4.setContentDescription(r0);
        r4.setCheckable(true);
        r4.setChecked(this.checkBox.isChecked());
        return;
    L7:
        if (TextUtils.isEmpty(r1.getText()) == true) goto L14;
        r0.append("\n");
        r0.append(this.multilineValueTextView.getText());
        goto L14
    L9:
        AnimatedTextView r12 = this.valueTextView;
        if (r12 == null) goto L14;
        if (TextUtils.isEmpty(r12.getText()) == true) goto L14;
        r0.append("\n");
        r0.append(this.valueTextView.getText());
        goto L14
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.isMultiline == false) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        return;
    L6:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.currentHeight), TLObject.FLAG_30));
    }

    public int processColor(int r1) {
        return r1;
    }

    public void setAnimationsEnabled(boolean r1) {
        this.animationsEnabled = r1;
    }

    public void setChecked(boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    public void setChecked(boolean r2, int r3) {
        this.checkBox.setChecked(r2, r3, true);
    }

    public void setDrawLine(boolean r1) {
        this.drawLine = r1;
    }

    public void setMultiline(boolean r4) {
        this.isMultiline = r4;
        TextView r0 = this.multilineValueTextView;
        if (r4 == false) goto L6;
        r0.setVisibility(0);
        this.valueTextView.setVisibility(8);
        this.multilineValueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
        return;
    L6:
        r0.setVisibility(8);
        this.valueTextView.setVisibility(0);
        this.valueTextView.setPadding(0, 0, 0, 0);
    }

    public void setTextAndValueAndCheck(CharSequence r9, CharSequence r10, boolean r11, int r12, boolean r13, boolean r14) {
        setTextAndValueAndIconAndCheck(r9, r10, 0, r11, r12, r13, r14);
    }

    public void setTextAndValueAndCheck(CharSequence r8, CharSequence r9, boolean r10, boolean r11) {
        setTextAndValueAndCheck(r8, r9, r10, 0, false, r11);
    }

    public void setTextAndValueAndIconAndCheck(CharSequence r10, CharSequence r11, int r12, boolean r13, int r14, boolean r15, boolean r16) {
        setTextAndValueAndIconAndCheck(r10, r11, r12, r13, r14, r15, r16, false);
    }

    public void setTextAndValueAndIconAndCheck(CharSequence r4, CharSequence r5, int r6, boolean r7, int r8, boolean r9, boolean r10, boolean r11) {
        this.textView.setText(r4);
        ImageView r0 = this.imageView;
        if (r0 == null) goto L5;
        r0.setImageResource(r6);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogIcon), PorterDuff.Mode.MULTIPLY));
    L5:
        this.checkBox.setChecked(r7, r8, this.animationsEnabled);
        setMultiline(r9);
        if (this.isMultiline == false) goto L8;
        this.multilineValueTextView.setText(r5);
    L10:
        if (this.isMultiline == false) goto L12;
        View r52 = this.multilineValueTextView;
    L13:
        r52.setVisibility(0);
        this.checkBox.setContentDescription(r4);
        this.needDivider = r10;
        return;
    L12:
        r52 = this.valueTextView;
        goto L13
    L8:
        this.valueTextView.setText(r5, r11);
        goto L10
    }

    public void setValue(CharSequence r2) {
        if (this.isMultiline == false) goto L6;
        this.multilineValueTextView.setText(r2);
        return;
    L6:
        this.valueTextView.setText(r2, true);
    }
}
