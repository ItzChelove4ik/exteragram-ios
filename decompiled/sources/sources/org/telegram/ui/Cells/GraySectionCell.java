package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class GraySectionCell extends FrameLayout implements Theme.Colorable {
    private int layerHeight;
    private boolean noBackground;
    private final Theme.ResourcesProvider resourcesProvider;
    private AnimatedTextView rightTextView;
    private FrameLayout.LayoutParams rightTextViewLayoutParams;
    private AnimatedEmojiSpan.TextViewEmojis textView;

    /* renamed from: org.telegram.ui.Cells.GraySectionCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedTextView {
        final /* synthetic */ GraySectionCell this$0;

        public AnonymousClass1(GraySectionCell r1, Context r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    public GraySectionCell(Context r2) {
        this(r2, null);
    }

    public GraySectionCell(Context r23, int r24, Theme.ResourcesProvider r25) {
        super(r23);
        this.layerHeight = 32;
        this.resourcesProvider = r25;
        setBackgroundColor(getThemedColor(Theme.key_graySection));
        AnimatedEmojiSpan.TextViewEmojis r0 = new AnimatedEmojiSpan.TextViewEmojis(getContext());
        this.textView = r0;
        r0.setTextSize(1, 14.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        AnimatedEmojiSpan.TextViewEmojis r02 = this.textView;
        int r8 = Theme.key_graySectionText;
        r02.setTextColor(getThemedColor(r8));
        AnimatedEmojiSpan.TextViewEmojis r03 = this.textView;
        int r9 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 5;
    L6:
        r03.setGravity(r2 | 16);
        View r04 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r22 = 5;
    L10:
        float r14 = r24;
        addView(r04, LayoutHelper.createFrame(-1, -1.0f, r22 | 48, r14, 0.0f, r14, 0.0f));
        AnonymousClass1 r05 = new AnonymousClass1(this, getContext(), true, true, true);
        this.rightTextView = r05;
        r05.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.rightTextView.setAnimationProperties(0.9f, 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.rightTextView.setTextSize(AndroidUtilities.dp(14.0f));
        this.rightTextView.setTextColor(getThemedColor(r8));
        AnimatedTextView r06 = this.rightTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r26 = 3;
    L14:
        r06.setGravity(r26);
        View r07 = this.rightTextView;
        if (LocaleController.isRTL == true) goto L18;
        r9 = 5;
    L18:
        FrameLayout.LayoutParams r27 = LayoutHelper.createFrame(-2, -1.0f, r9 | 48, r14, 0.0f, r14, 0.0f);
        this.rightTextViewLayoutParams = r27;
        addView(r07, r27);
        ViewCompat.setAccessibilityHeading(this, true);
        return;
    L13:
        r26 = 5;
        goto L14
    L9:
        r22 = 3;
        goto L10
    L5:
        r2 = 3;
        goto L6
    }

    public GraySectionCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, 16, r3);
    }

    public static void createThemeDescriptions(List<ThemeDescription> r20, RecyclerListView r21) {
        int r19 = Theme.key_graySectionText;
        r20.add(new ThemeDescription(r21, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, r19));
        r20.add(new ThemeDescription(r21, 0, new Class[]{GraySectionCell.class}, new String[]{"rightTextView"}, null, null, null, r19));
        r20.add(new ThemeDescription(r21, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    public TextView getTextView() {
        return this.textView;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.layerHeight), TLObject.FLAG_30));
    }

    public void setLayerHeight(int r1) {
        this.layerHeight = r1;
        requestLayout();
    }

    public void setNoBackground(boolean r1) {
        this.noBackground = r1;
        updateColors();
    }

    public void setRightText(CharSequence r2) {
        setRightText(r2, true);
    }

    public void setRightText(CharSequence r3, View.OnClickListener r4) {
        this.rightTextView.setText(r3, false);
        this.rightTextView.setOnClickListener(r4);
        this.rightTextView.setVisibility(0);
    }

    public void setRightText(CharSequence r3, boolean r4) {
        this.rightTextView.setText(r3, true, r4);
        this.rightTextView.setVisibility(0);
    }

    public void setRightText(CharSequence r3, boolean r4, View.OnClickListener r5) {
        this.rightTextView.setText(r3, true, r4);
        this.rightTextView.setOnClickListener(r5);
        this.rightTextView.setVisibility(0);
    }

    public void setRightTextMargin(int r3) {
        float r32 = r3;
        this.rightTextViewLayoutParams.leftMargin = AndroidUtilities.dp(r32);
        this.rightTextViewLayoutParams.rightMargin = AndroidUtilities.dp(r32);
        this.rightTextView.setLayoutParams(this.rightTextViewLayoutParams);
    }

    public void setText(CharSequence r2) {
        this.textView.setText(r2);
        this.rightTextView.setVisibility(8);
        this.rightTextView.setOnClickListener(null);
    }

    public void setText(CharSequence r2, CharSequence r3, View.OnClickListener r4) {
        this.textView.setText(r2);
        this.rightTextView.setText(r3, false);
        this.rightTextView.setOnClickListener(r4);
        this.rightTextView.setVisibility(0);
    }

    public void setTextColor(int r2) {
        int r22 = getThemedColor(r2);
        this.textView.setTextColor(r22);
        this.rightTextView.setTextColor(r22);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        if (this.noBackground == false) goto L5;
        int r0 = 0;
    L6:
        setBackgroundColor(r0);
        AnimatedEmojiSpan.TextViewEmojis r02 = this.textView;
        int r1 = Theme.key_graySectionText;
        r02.setTextColor(getThemedColor(r1));
        this.rightTextView.setTextColor(getThemedColor(r1));
        return;
    L5:
        r0 = getThemedColor(Theme.key_graySection);
        goto L6
    }
}
