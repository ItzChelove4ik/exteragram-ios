package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes3.dex */
public class HeaderCell extends FrameLayout {
    private final boolean animated;
    private AnimatedTextView animatedTextView;
    protected int bottomMargin;
    private int height;
    public int id;
    protected int padding;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView textView;
    private SimpleTextView textView2;

    public HeaderCell(Context r8) {
        this(r8, Theme.key_windowBackgroundWhiteBlueHeader, 18, 6, false, null);
    }

    public HeaderCell(Context r8, int r9) {
        this(r8, Theme.key_windowBackgroundWhiteBlueHeader, r9, 6, false, null);
    }

    public HeaderCell(Context r10, int r11, int r12, int r13, int r14, boolean r15, Theme.ResourcesProvider r16) {
        this(r10, r11, r12, r13, r14, r15, false, r16);
    }

    public HeaderCell(Context r19, int r20, int r21, int r22, int r23, boolean r24, boolean r25, Theme.ResourcesProvider r26) {
        super(r19);
        this.height = 40;
        this.resourcesProvider = r26;
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L5;
        int r4 = 24;
    L6:
        this.padding = r4;
        int r6 = 3;
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L9;
        int r5 = 3;
    L10:
        this.bottomMargin = r5;
        this.animated = r25;
        float r7 = 0.0f;
        if (r25 == false) goto L25;
        AnimatedTextView r3 = new AnimatedTextView(getContext());
        this.animatedTextView = r3;
        r3.setTextSize(AndroidUtilities.dp(14.0f));
        this.animatedTextView.setTypeface(AndroidUtilities.bold());
        AnimatedTextView r32 = this.animatedTextView;
        if (LocaleController.isRTL == false) goto L15;
        int r8 = 5;
    L16:
        r32.setGravity(r8 | 16);
        this.animatedTextView.setTextColor(getThemedColor(r20));
        this.animatedTextView.setTag(Integer.valueOf(r20));
        this.animatedTextView.getDrawable().setHacks(true, true, false);
        AnimatedTextView r1 = this.animatedTextView;
        float r12 = this.height - r22;
        if (LocaleController.isRTL == false) goto L19;
        int r33 = 5;
    L20:
        int r13 = r33 | 48;
        float r14 = r4;
        float r15 = r22;
        if (r24 == true) goto L24;
        r7 = r5;
    L24:
        addView(r1, LayoutHelper.createFrame(-1, r12, r13, r14, r15, r14, r7));
    L38:
        if (r24 == false) goto L48;
        SimpleTextView r16 = new SimpleTextView(getContext());
        this.textView2 = r16;
        r16.setTextSize(13);
        SimpleTextView r17 = this.textView2;
        if (LocaleController.isRTL == false) goto L42;
        int r2 = 3;
    L43:
        r17.setGravity(r2 | 48);
        SimpleTextView r18 = this.textView2;
        if (LocaleController.isRTL == true) goto L47;
        r6 = 5;
    L47:
        float r34 = r4;
        addView(r18, LayoutHelper.createFrame(-1, -1.0f, r6 | 48, r34, 21.0f, r34, r5));
        goto L48
    L42:
        r2 = 5;
    L48:
        ViewCompat.setAccessibilityHeading(this, true);
        return;
    L19:
        r33 = 3;
        goto L20
    L15:
        r8 = 3;
        goto L16
    L25:
        TextView r35 = new TextView(getContext());
        this.textView = r35;
        r35.setTextSize(1, 14.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r36 = this.textView;
        if (LocaleController.isRTL == false) goto L28;
        int r82 = 5;
    L29:
        r36.setGravity(r82 | 16);
        this.textView.setMinHeight(AndroidUtilities.dp(this.height - r22));
        this.textView.setTextColor(getThemedColor(r20));
        this.textView.setTag(Integer.valueOf(r20));
        TextView r110 = this.textView;
        if (LocaleController.isRTL == false) goto L32;
        int r37 = 5;
    L33:
        int r132 = r37 | 48;
        float r142 = r4;
        float r152 = r22;
        if (r24 == true) goto L37;
        r7 = r5;
    L37:
        addView(r110, LayoutHelper.createFrame(-1, -1.0f, r132, r142, r152, r142, r7));
        goto L38
    L32:
        r37 = 3;
        goto L33
    L28:
        r82 = 3;
        goto L29
    L9:
        r5 = r23;
        goto L10
    L5:
        r4 = r21;
        goto L6
    }

    public HeaderCell(Context r8, int r9, int r10, int r11, boolean r12) {
        this(r8, r9, r10, r11, r12, null);
    }

    public HeaderCell(Context r9, int r10, int r11, int r12, boolean r13, Theme.ResourcesProvider r14) {
        this(r9, r10, r11, r12, 0, r13, r14);
    }

    public HeaderCell(Context r8, int r9, Theme.ResourcesProvider r10) {
        this(r8, Theme.key_windowBackgroundWhiteBlueHeader, r9, 6, false, r10);
    }

    public HeaderCell(Context r8, Theme.ResourcesProvider r9) {
        this(r8, Theme.key_windowBackgroundWhiteBlueHeader, 18, 6, false, r9);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public float getAnimatedWidth() {
        return this.animatedTextView.getDrawable().getCurrentWidth();
    }

    public TextView getTextView() {
        return this.textView;
    }

    public SimpleTextView getTextView2() {
        return this.textView2;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
        super.onInitializeAccessibilityNodeInfo(r5);
        if (Build.VERSION.SDK_INT < 28) goto L5;
        r5.setHeading(true);
    L8:
        r5.setEnabled(true);
        return;
    L5:
        AccessibilityNodeInfo.CollectionItemInfo r4 = r5.getCollectionItemInfo();
        if (r4 == null) goto L8;
        r5.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(r4.getRowIndex(), r4.getRowSpan(), r4.getColumnIndex(), r4.getColumnSpan(), true));
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r1), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.View
    public void setBackground(Drawable r2) {
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L5;
        return;
    L5:
        super.setBackground(r2);
    }

    public void setBottomMargin(int r3) {
        if (ExteraConfig.getSectionsSeparatedHeaders() == true) goto L10;
        float r32 = r3;
        ((FrameLayout.LayoutParams) this.textView.getLayoutParams()).bottomMargin = AndroidUtilities.dp(r32);
        SimpleTextView r2 = this.textView2;
        if (r2 == null) goto L9;
        ((FrameLayout.LayoutParams) r2.getLayoutParams()).bottomMargin = AndroidUtilities.dp(r32);
        return;
    L9:
        return;
    }

    public void setEnabled(boolean r3, ArrayList<Animator> r4) {
        TextView r2 = this.textView;
        float r0 = 0.5f;
        if (r4 == null) goto L8;
        if (r3 == false) goto L6;
        r0 = 1.0f;
    L6:
        r4.add(ObjectAnimator.ofFloat(r2, View.ALPHA, new float[]{r0}));
        return;
    L8:
        if (r3 == false) goto L10;
        r0 = 1.0f;
    L10:
        r2.setAlpha(r0);
    }

    public void setEnabled(boolean r3, boolean r4) {
        super.setEnabled(r3);
        TextView r2 = this.textView;
        float r0 = 0.5f;
        if (r4 == false) goto L9;
        ViewPropertyAnimator r22 = r2.animate();
        if (r3 == false) goto L7;
        r0 = 1.0f;
    L7:
        r22.alpha(r0).start();
        return;
    L9:
        if (r3 == false) goto L11;
        r0 = 1.0f;
    L11:
        r2.setAlpha(r0);
    }

    public void setHeight(int r2) {
        if (ExteraConfig.getSectionsSeparatedHeaders() == true) goto L10;
        this.height = r2;
        int r22 = AndroidUtilities.dp(r2) - ((FrameLayout.LayoutParams) this.textView.getLayoutParams()).topMargin;
        if (this.textView.getMinHeight() == r22) goto L9;
        this.textView.setMinHeight(r22);
        requestLayout();
        return;
    L9:
        return;
    }

    public void setOnWidthUpdateListener(Runnable r1) {
        this.animatedTextView.setOnWidthUpdatedListener(r1);
    }

    public void setText(CharSequence r2) {
        setText(r2, false);
    }

    public void setText(CharSequence r5, boolean r6) {
        int r1 = 3;
        if (this.animated == false) goto L9;
        AnimatedTextView r0 = this.animatedTextView;
        if (LocaleController.isRTL == false) goto L7;
        r1 = 5;
    L7:
        r0.setGravity(r1 | 16);
        this.animatedTextView.setText(r5, r6);
        return;
    L9:
        TextView r62 = this.textView;
        if (LocaleController.isRTL == false) goto L12;
        r1 = 5;
    L12:
        r62.setGravity(r1 | 16);
        this.textView.setText(r5);
    }

    public void setText2(CharSequence r1) {
        SimpleTextView r0 = this.textView2;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setText(r1);
    }

    public void setTextColor(int r2) {
        TextView r0 = this.textView;
        if (r0 == null) goto L5;
        r0.setTextColor(r2);
    L5:
        AnimatedTextView r1 = this.animatedTextView;
        if (r1 == null) goto L9;
        r1.setTextColor(r2);
        return;
    }

    public void setTextSize(float r2) {
        if (this.animated == false) goto L6;
        this.animatedTextView.setTextSize(AndroidUtilities.dp(r2));
        return;
    L6:
        this.textView.setTextSize(1, r2);
    }

    public void setTopMargin(int r2) {
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L5;
        return;
    L5:
        ((FrameLayout.LayoutParams) this.textView.getLayoutParams()).topMargin = AndroidUtilities.dp(r2);
        setHeight(this.height);
    }
}
