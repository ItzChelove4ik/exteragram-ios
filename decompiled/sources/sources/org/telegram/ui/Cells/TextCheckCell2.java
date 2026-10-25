package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.utils.ui.SwitchUiHelper;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;

/* loaded from: classes3.dex */
public class TextCheckCell2 extends FrameLayout {
    private AnimatedTextView animatedTextView;
    private Switch checkBox;
    private View checkBoxClickArea;
    private LinearLayout collapseViewContainer;
    private View collapsedArrow;
    public int id;
    private boolean isMultiline;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.TextCheckCell2$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends View {
        final /* synthetic */ TextCheckCell2 this$0;

        public AnonymousClass1(TextCheckCell2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            super.onDraw(r9);
            r9.drawLine(0.0f, AndroidUtilities.dp(14.0f), 0.0f, getMeasuredHeight() - AndroidUtilities.dp(14.0f), Theme.dividerPaint);
        }
    }

    public static /* synthetic */ void $r8$lambda$00Pu2OWW2McQif8LMe1_CXX4roE(Runnable r0, View r1) {
        r0.run();
    }

    public TextCheckCell2(Context r2) {
        this(r2, null);
    }

    public TextCheckCell2(Context r19, Theme.ResourcesProvider r20) {
        super(r19);
        TextView r3 = new TextView(r19);
        this.textView = r3;
        r3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r20));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r32 = this.textView;
        int r6 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r4 = 5;
    L6:
        r32.setGravity(r4 | 16);
        TextView r33 = this.textView;
        TextUtils.TruncateAt r42 = TextUtils.TruncateAt.END;
        r33.setEllipsize(r42);
        TextView r34 = this.textView;
        boolean r8 = LocaleController.isRTL;
        if (r8 == false) goto L9;
        int r9 = 5;
    L10:
        int r12 = r9 | 48;
        if (r8 == false) goto L13;
        float r13 = 64.0f;
    L14:
        if (r8 == false) goto L16;
        float r15 = 21.0f;
    L17:
        addView(r34, LayoutHelper.createFrame(-2, -1.0f, r12, r13, 0.0f, r15, 0.0f));
        TextView r35 = new TextView(r19);
        this.valueTextView = r35;
        r35.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r20));
        this.valueTextView.setTextSize(1, 13.0f);
        this.valueTextView.setTypeface(AndroidUtilities.regular());
        TextView r2 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L20;
        int r36 = 5;
    L21:
        r2.setGravity(r36);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
        this.valueTextView.setEllipsize(r42);
        TextView r22 = this.valueTextView;
        boolean r43 = LocaleController.isRTL;
        if (r43 == false) goto L24;
        int r82 = 5;
    L25:
        int r122 = r82 | 48;
        if (r43 == false) goto L28;
        float r132 = 64.0f;
    L29:
        if (r43 == false) goto L31;
        float r152 = 21.0f;
    L32:
        addView(r22, LayoutHelper.createFrame(-2, -2.0f, r122, r132, 35.0f, r152, 0.0f));
        Switch r23 = new Switch(r19);
        this.checkBox = r23;
        r23.setDrawIconType(1);
        Switch r1 = this.checkBox;
        if (LocaleController.isRTL == true) goto L36;
        r6 = 5;
    L36:
        addView(r1, LayoutHelper.createFrame(37, 40.0f, r6 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        setClipChildren(false);
        return;
    L31:
        r152 = 64.0f;
        goto L32
    L28:
        r132 = 21.0f;
        goto L29
    L24:
        r82 = 3;
        goto L25
    L20:
        r36 = 3;
        goto L21
    L16:
        r15 = 64.0f;
        goto L17
    L13:
        r13 = 21.0f;
        goto L14
    L9:
        r9 = 3;
        goto L10
    L5:
        r4 = 3;
        goto L6
    }

    public Switch getCheckBox() {
        return this.checkBox;
    }

    public View getCollapseContainer() {
        return this.collapseViewContainer;
    }

    public boolean hasIcon() {
        return this.checkBox.hasIcon();
    }

    public void hideCollapseArrow() {
        LinearLayout r1 = this.collapseViewContainer;
        if (r1 == null) goto L6;
        r1.setVisibility(8);
        return;
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
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
        r2.setClassName("android.widget.Switch");
        r2.setCheckable(true);
        r2.setChecked(this.checkBox.isChecked());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        LinearLayout r12 = this.collapseViewContainer;
        if (r12 == null) goto L10;
        boolean r22 = LocaleController.isRTL;
        TextView r32 = this.textView;
        if (r22 == false) goto L8;
        r12.setTranslationX((r32.getLeft() - this.collapseViewContainer.getMeasuredWidth()) - AndroidUtilities.dp(8.0f));
        return;
    L8:
        r12.setTranslationX(r32.getRight() + AndroidUtilities.dp(8.0f));
        return;
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
        r42 = 50.0f;
        goto L10
    }

    public void reset() {
        this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.valueTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.valueTextView.setVisibility(8);
        View r0 = this.checkBoxClickArea;
        if (r0 == null) goto L5;
        r0.setOnClickListener(null);
    L5:
        this.checkBox.setIcon(0);
        this.isMultiline = false;
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        r02.height = -1;
        r02.topMargin = 0;
        this.textView.setLayoutParams(r02);
    }

    public void setChecked(boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    public void setCollapseArrow(String r13, boolean r14, Runnable r15) {
        if (this.collapseViewContainer != null) goto L9;
        LinearLayout r0 = new LinearLayout(getContext());
        this.collapseViewContainer = r0;
        r0.setOrientation(0);
        AnimatedTextView r02 = new AnimatedTextView(getContext(), false, true, true);
        this.animatedTextView = r02;
        r02.setTextSize(AndroidUtilities.dp(14.0f));
        this.animatedTextView.getDrawable().setAllowCancel(true);
        AnimatedTextView r03 = this.animatedTextView;
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        r03.setTextColor(Theme.getColor(r3));
        this.animatedTextView.setTypeface(AndroidUtilities.bold());
        this.animatedTextView.setAnimationProperties(0.4f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.collapseViewContainer.addView(this.animatedTextView, LayoutHelper.createFrame(-2, 20.0f));
        this.collapsedArrow = new View(getContext());
        Drawable r04 = getContext().getResources().getDrawable(R.drawable.arrow_more).mutate();
        r04.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3), PorterDuff.Mode.MULTIPLY));
        this.collapsedArrow.setBackground(r04);
        this.collapseViewContainer.addView(this.collapsedArrow, LayoutHelper.createLinear(16, 16, 16));
        this.collapseViewContainer.setClipChildren(false);
        setClipChildren(false);
        addView(this.collapseViewContainer, LayoutHelper.createFrame(-2, -2, 16));
        AnonymousClass1 r05 = new AnonymousClass1(this, getContext());
        this.checkBoxClickArea = r05;
        if (LocaleController.isRTL == false) goto L7;
        int r2 = 3;
    L8:
        addView(r05, LayoutHelper.createFrame(76, -1, r2));
        goto L9
    L7:
        r2 = 5;
    L9:
        this.animatedTextView.setText(r13, true);
        this.collapsedArrow.animate().cancel();
        ViewPropertyAnimator r132 = this.collapsedArrow.animate();
        if (r14 == false) goto L12;
        float r142 = 0.0f;
    L13:
        r132.rotation(r142).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        this.checkBoxClickArea.setOnClickListener(new TextCheckCell2$$ExternalSyntheticLambda0(r15));
        return;
    L12:
        r142 = 180.0f;
        goto L13
    }

    public void setCollapsed(boolean r3) {
        this.collapsedArrow.animate().cancel();
        ViewPropertyAnimator r2 = this.collapsedArrow.animate();
        if (r3 == false) goto L5;
        float r32 = 0.0f;
    L6:
        r2.rotation(r32).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L5:
        r32 = 180.0f;
        goto L6
    }

    @Override // android.view.View
    public void setEnabled(boolean r4) {
        super.setEnabled(r4);
        this.textView.clearAnimation();
        this.valueTextView.clearAnimation();
        this.checkBox.clearAnimation();
        LinearLayout r0 = this.collapseViewContainer;
        if (r0 == null) goto L5;
        r0.clearAnimation();
    L5:
        if (r4 == false) goto L15;
        this.textView.setAlpha(1.0f);
        this.valueTextView.setAlpha(1.0f);
        this.checkBox.setAlpha(1.0f);
        AnimatedTextView r02 = this.animatedTextView;
        if (r02 == null) goto L9;
        r02.setAlpha(1.0f);
    L9:
        View r03 = this.collapsedArrow;
        if (r03 == null) goto L12;
        r03.setAlpha(1.0f);
    L12:
        LinearLayout r04 = this.collapseViewContainer;
        if (r04 == null) goto L24;
        r04.setAlpha(1.0f);
    L24:
        View r3 = this.checkBoxClickArea;
        if (r3 == null) goto L28;
        r3.setEnabled(r4);
        return;
    L28:
        return;
    L15:
        this.checkBox.setAlpha(0.5f);
        this.textView.setAlpha(0.5f);
        this.valueTextView.setAlpha(0.5f);
        AnimatedTextView r05 = this.animatedTextView;
        if (r05 == null) goto L18;
        r05.setAlpha(0.6f);
    L18:
        View r06 = this.collapsedArrow;
        if (r06 == null) goto L21;
        r06.setAlpha(0.6f);
    L21:
        LinearLayout r07 = this.collapseViewContainer;
        if (r07 == null) goto L24;
        r07.setAlpha(0.5f);
        goto L24
    }

    public void setEnabled(boolean r4, boolean r5) {
        super.setEnabled(r4);
        float r0 = 0.5f;
        if (r5 == false) goto L25;
        this.textView.clearAnimation();
        this.valueTextView.clearAnimation();
        this.checkBox.clearAnimation();
        LinearLayout r52 = this.collapseViewContainer;
        if (r52 == null) goto L11;
        r52.clearAnimation();
        ViewPropertyAnimator r53 = this.collapseViewContainer.animate();
        if (r4 == false) goto L9;
        float r2 = 1.0f;
    L10:
        r53.alpha(r2).start();
        goto L11
    L9:
        r2 = 0.5f;
    L11:
        ViewPropertyAnimator r54 = this.textView.animate();
        if (r4 == false) goto L14;
        float r22 = 1.0f;
    L15:
        r54.alpha(r22).start();
        ViewPropertyAnimator r55 = this.valueTextView.animate();
        if (r4 == false) goto L18;
        float r23 = 1.0f;
    L19:
        r55.alpha(r23).start();
        if (SwitchUiHelper.isMaterial3SwitchStyle() == true) goto L32;
        ViewPropertyAnimator r56 = this.checkBox.animate();
        if (r4 == false) goto L24;
        r0 = 1.0f;
    L24:
        r56.alpha(r0).start();
    L32:
        View r3 = this.checkBoxClickArea;
        if (r3 == null) goto L36;
        r3.setEnabled(r4);
        return;
    L36:
        return;
    L18:
        r23 = 0.5f;
        goto L19
    L14:
        r22 = 0.5f;
        goto L15
    L25:
        if (r4 == false) goto L29;
        this.textView.setAlpha(1.0f);
        this.valueTextView.setAlpha(1.0f);
        this.checkBox.setAlpha(1.0f);
        LinearLayout r57 = this.collapseViewContainer;
        if (r57 == null) goto L32;
        r57.setAlpha(1.0f);
        goto L32
    L29:
        this.checkBox.setAlpha(0.5f);
        this.textView.setAlpha(0.5f);
        this.valueTextView.setAlpha(0.5f);
        LinearLayout r58 = this.collapseViewContainer;
        if (r58 == null) goto L32;
        r58.setAlpha(0.5f);
        goto L32
    }

    public void setIcon(int r1) {
        this.checkBox.setIcon(r1);
    }

    public void setTextAndCheck(CharSequence r2, boolean r3, boolean r4) {
        setTextAndCheck(r2, r3, r4, false);
    }

    public void setTextAndCheck(CharSequence r2, boolean r3, boolean r4, boolean r5) {
        this.textView.setText(r2);
        this.isMultiline = false;
        this.checkBox.setChecked(r3, r5);
        this.needDivider = r4;
        this.valueTextView.setVisibility(8);
        FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        r32.height = -1;
        r32.topMargin = 0;
        this.textView.setLayoutParams(r32);
        setWillNotDraw(!r4);
    }
}
