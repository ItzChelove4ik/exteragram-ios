package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
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
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public class TextInfoPrivacyCell extends FrameLayout {
    private int bottomPadding;
    private int fixedSize;
    private boolean isRTL;
    private int linkTextColorKey;
    private Integer linkTextRippleColor;
    private LinkSpanDrawable.LinkCollector links;
    private final Theme.ResourcesProvider resourcesProvider;
    private CharSequence text;
    private LinkSpanDrawable.LinksTextView textView;
    private int topPadding;

    /* renamed from: org.telegram.ui.Cells.TextInfoPrivacyCell$1, reason: invalid class name */
    public class AnonymousClass1 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ TextInfoPrivacyCell this$0;

        public AnonymousClass1(TextInfoPrivacyCell r1, Context r2, LinkSpanDrawable.LinkCollector r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView, android.view.View
        public void onDraw(Canvas r2) {
            this.this$0.onTextDraw();
            super.onDraw(r2);
            this.this$0.afterTextDraw();
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView
        public int overrideColor() {
            if (TextInfoPrivacyCell.m7063$$Nest$fgetlinkTextRippleColor(this.this$0) == null) goto L7;
            return TextInfoPrivacyCell.m7063$$Nest$fgetlinkTextRippleColor(this.this$0).intValue();
        L7:
            return super.overrideColor();
        }
    }

    /* renamed from: -$$Nest$fgetlinkTextRippleColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Integer m7063$$Nest$fgetlinkTextRippleColor(TextInfoPrivacyCell r0) {
        return r0.linkTextRippleColor;
    }

    public TextInfoPrivacyCell(Context r3) {
        this(r3, 24, null);
    }

    public TextInfoPrivacyCell(Context r2, int r3) {
        this(r2, r3, null);
    }

    public TextInfoPrivacyCell(Context r12, int r13, Theme.ResourcesProvider r14) {
        super(r12);
        this.linkTextColorKey = Theme.key_windowBackgroundWhiteLinkText;
        this.topPadding = 10;
        this.bottomPadding = 17;
        this.resourcesProvider = r14;
        LinkSpanDrawable.LinkCollector r1 = new LinkSpanDrawable.LinkCollector(this);
        this.links = r1;
        AnonymousClass1 r0 = new AnonymousClass1(this, r12, r1, r14);
        this.textView = r0;
        r0.setTextSize(1, 14.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        LinkSpanDrawable.LinksTextView r122 = this.textView;
        int r02 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r142 = 5;
    L6:
        r122.setGravity(r142);
        this.textView.setPadding(0, AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(17.0f));
        this.textView.setMovementMethod(LinkMovementMethod.getInstance());
        LinkSpanDrawable.LinksTextView r123 = this.textView;
        int r143 = Theme.key_windowBackgroundWhiteGrayText4;
        r123.setTextColor(getThemedColor(r143));
        this.textView.setEmojiColor(getThemedColor(r143));
        this.textView.setLinkTextColor(getThemedColor(this.linkTextColorKey));
        this.textView.setImportantForAccessibility(2);
        LinkSpanDrawable.LinksTextView r124 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        r02 = 5;
    L9:
        float r7 = r13;
        addView(r124, LayoutHelper.createFrame(-1, -2.0f, r02 | 48, r7, 0.0f, r7, 0.0f));
        this.isRTL = LocaleController.isRTL;
        setWillNotDraw(false);
        return;
    L5:
        r142 = 3;
        goto L6
    }

    public TextInfoPrivacyCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, 24, r3);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public void afterTextDraw() {
    }

    public int getFixedSize() {
        return this.fixedSize;
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    public LinkSpanDrawable.LinksTextView getTextView() {
        return this.textView;
    }

    public int length() {
        return this.textView.length();
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        if (this.links == null) goto L8;
        r3.save();
        r3.translate(this.textView.getLeft(), this.textView.getTop());
        if (this.links.draw(r3) == false) goto L7;
        invalidate();
    L7:
        r3.restore();
    L8:
        super.onDraw(r3);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName(TextView.class.getName());
        r2.setText(this.text);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        int r52 = this.fixedSize;
        if (r52 != (-1)) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, TLObject.FLAG_30));
        return;
    L6:
        if (r52 == 0) goto L9;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.fixedSize), TLObject.FLAG_30));
        return;
    L9:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void onTextDraw() {
    }

    public void setBottomPadding(int r1) {
        this.bottomPadding = r1;
    }

    public void setEnabled(boolean r3, ArrayList<Animator> r4) {
        LinkSpanDrawable.LinksTextView r2 = this.textView;
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

    public void setFixedSize(int r1) {
        this.fixedSize = r1;
    }

    public void setLinkTextColorKey(int r1) {
        this.linkTextColorKey = r1;
    }

    public void setLinkTextRippleColor(Integer r1) {
        this.linkTextRippleColor = r1;
    }

    public void setText(CharSequence r8) {
        if (TextUtils.equals(r8, this.text) == true) goto L30;
        this.text = r8;
        LinkSpanDrawable.LinksTextView r0 = this.textView;
        int r1 = 0;
        if (r8 != null) goto L7;
        r0.setPadding(0, AndroidUtilities.dp(2.0f), 0, 0);
    L8:
        SpannableString r02 = null;
        if (r8 == null) goto L21;
        int r2 = r8.length();
    L12:
        if (r1 >= (r2 - 1)) goto L21;
        if (r8.charAt(r1) != '\n') goto L20;
        int r4 = r1 + 1;
        if (r8.charAt(r4) != '\n') goto L20;
        if (r02 != null) goto L19;
        r02 = new SpannableString(r8);
    L19:
        r02.setSpan(new AbsoluteSizeSpan(10, true), r4, r1 + 2, 33);
    L20:
        r1 = r1 + 1;
    L21:
        LinkSpanDrawable.LinksTextView r7 = this.textView;
        if (r02 == null) goto L24;
        r8 = r02;
    L24:
        r7.setText(r8);
        return;
    L7:
        r0.setPadding(0, AndroidUtilities.dp(this.topPadding), 0, AndroidUtilities.dp(this.bottomPadding));
        goto L8
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }

    public void setTextColorByKey(int r3) {
        this.textView.setTextColor(getThemedColor(r3));
        this.textView.setTag(Integer.valueOf(r3));
    }

    public void setTextGravity(int r1) {
        this.textView.setGravity(r1);
    }

    public void setTopPadding(int r1) {
        this.topPadding = r1;
    }

    public void updateRTL() {
        boolean r0 = this.isRTL;
        boolean r1 = LocaleController.isRTL;
        if (r0 != r1) goto L5;
        return;
    L5:
        this.isRTL = r1;
        LinkSpanDrawable.LinksTextView r02 = this.textView;
        int r2 = 3;
        if (r1 == false) goto L8;
        int r12 = 5;
    L9:
        r02.setGravity(r12);
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        if (LocaleController.isRTL == false) goto L12;
        r2 = 5;
    L12:
        r03.gravity = r2 | 48;
        this.textView.setLayoutParams(r03);
        return;
    L8:
        r12 = 3;
        goto L9
    }
}
