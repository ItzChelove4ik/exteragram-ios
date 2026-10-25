package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exteragram.messenger.utils.system.VibratorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

/* loaded from: classes6.dex */
public class TextDetailCell extends FrameLayout {
    private boolean contentDescriptionValueFirst;
    private final ImageView imageView;
    private boolean multiline;
    private boolean needDivider;
    private Theme.ResourcesProvider resourcesProvider;
    public final LinkSpanDrawable.LinksTextView rightValueTextView;
    private final TextView showMoreTextView;
    public final SpoilersTextView textView;
    public final LinkSpanDrawable.LinksTextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.TextDetailCell$1, reason: invalid class name */
    public class AnonymousClass1 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ TextDetailCell this$0;

        public AnonymousClass1(TextDetailCell r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView
        public int overrideColor() {
            return processColor(super.overrideColor());
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView
        public int processColor(int r1) {
            return this.this$0.processColor(r1);
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextDetailCell$2, reason: invalid class name */
    public class AnonymousClass2 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ TextDetailCell this$0;

        public AnonymousClass2(TextDetailCell r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView
        public int overrideColor() {
            return processColor(super.overrideColor());
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView
        public int processColor(int r1) {
            return this.this$0.processColor(r1);
        }
    }

    public static final class Factory extends UItem.UItemFactory<TextDetailCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            r1.setId(r2.id);
            ((TextDetailCell) r1).setTextAndValue(r2.text, r2.subtext, r3);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public TextDetailCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new TextDetailCell(r1, 18, r5, false, false);
        }
    }

    /* renamed from: $r8$lambda$-hP911aC-J0KDMdvV3-94T625sM, reason: not valid java name */
    public static /* synthetic */ void m7061$r8$lambda$hP911aCJ0KDMdvV394T625sM(TextDetailCell r0, ClickableSpan r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$68pfTmCuNRy3f8u0NrG07HAHW_k(TextDetailCell r0, ClickableSpan r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$_YEOlPf4T6nYxVh-W3_9FTAfZrc, reason: not valid java name */
    public static /* synthetic */ void m7062$r8$lambda$_YEOlPf4T6nYxVhW3_9FTAfZrc(TextDetailCell r0, ClickableSpan r1) {
        r0.lambda$new$0(r1);
    }

    public TextDetailCell(Context r2) {
        this(r2, null);
    }

    public TextDetailCell(Context r26, int r27, Theme.ResourcesProvider r28, boolean r29, boolean r30) {
        super(r26);
        this.showMoreTextView = null;
        this.resourcesProvider = r28;
        if (r29 == true) goto L7;
        if (r30 == true) goto L7;
        boolean r6 = false;
    L8:
        this.multiline = r6;
        SpoilersTextView r62 = new SpoilersTextView(r26, r28);
        this.textView = r62;
        r62.setOnLinkLongPressListener(new TextDetailCell$$ExternalSyntheticLambda0(this));
        r62.setTextSize(1, 16.0f);
        r62.setTypeface(AndroidUtilities.regular());
        int r8 = 3;
        if (LocaleController.isRTL == false) goto L11;
        int r7 = 5;
    L12:
        r62.setGravity(r7);
        if (r29 == false) goto L15;
        setMinimumHeight(AndroidUtilities.dp(60.0f));
    L16:
        TextUtils.TruncateAt r10 = TextUtils.TruncateAt.END;
        r62.setEllipsize(r10);
        r62.setImportantForAccessibility(2);
        r62.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(5.0f));
        if (LocaleController.isRTL == false) goto L19;
        int r20 = 5;
    L20:
        float r72 = r27 - 6;
        if (r29 == false) goto L24;
        float r13 = 27.0f;
    L25:
        addView(r62, LayoutHelper.createFrame(-2, -2.0f, r20, r72, 6.0f, r72, r13));
        AnonymousClass1 r63 = new AnonymousClass1(this, r26, r28);
        this.valueTextView = r63;
        r63.setOnLinkLongPressListener(new TextDetailCell$$ExternalSyntheticLambda1(this));
        if (r30 == false) goto L28;
        setMinimumHeight(AndroidUtilities.dp(60.0f));
    L29:
        r63.setTextSize(1, 13.0f);
        r63.setTypeface(AndroidUtilities.regular());
        if (LocaleController.isRTL == false) goto L32;
        int r132 = 5;
    L33:
        r63.setGravity(r132);
        r63.setImportantForAccessibility(2);
        r63.setEllipsize(r10);
        r63.setPadding(0, AndroidUtilities.dp(1.0f), 0, AndroidUtilities.dp(6.0f));
        if (r29 == false) goto L41;
        if (LocaleController.isRTL == false) goto L38;
        int r14 = 5;
    L39:
        int r202 = r14 | 80;
        float r142 = r27;
        addView(r63, LayoutHelper.createFrame(-1, -2.0f, r202, r142, 32.0f, r142, 4.0f));
    L45:
        AnonymousClass2 r64 = new AnonymousClass2(this, r26, r28);
        this.rightValueTextView = r64;
        r64.setOnLinkLongPressListener(new TextDetailCell$$ExternalSyntheticLambda2(this));
        boolean r3 = this.multiline;
        this.multiline = r3;
        if (r3 == false) goto L48;
        setMinimumHeight(AndroidUtilities.dp(60.0f));
    L49:
        r64.setTextSize(1, 13.0f);
        if (LocaleController.isRTL == false) goto L52;
        int r32 = 3;
    L53:
        r64.setGravity(r32);
        r64.setImportantForAccessibility(2);
        r64.setEllipsize(r10);
        r64.setPadding(0, AndroidUtilities.dp(1.0f), 0, AndroidUtilities.dp(6.0f));
        if (r29 == false) goto L60;
        if (LocaleController.isRTL == false) goto L58;
        r8 = 5;
    L58:
        float r15 = r27;
        addView(r64, LayoutHelper.createFrame(-1, -2.0f, r8 | 80, r15, 32.0f, r15, 4.0f));
    L64:
        updateColors();
        ImageView r2 = new ImageView(r26);
        this.imageView = r2;
        r2.setImportantForAccessibility(2);
        r2.setScaleType(ImageView.ScaleType.CENTER);
        addView(r2, LayoutHelper.createFrameRelatively(48.0f, 48.0f, 8388629, 0.0f, 0.0f, 12.0f, 0.0f));
        return;
    L60:
        if (LocaleController.isRTL == false) goto L62;
        int r143 = 5;
    L63:
        float r152 = r27;
        addView(r64, LayoutHelper.createFrame(-1, -2.0f, r143, r152, 32.0f, r152, 4.0f));
        goto L64
    L62:
        r143 = 3;
        goto L63
    L52:
        r32 = 5;
        goto L53
    L48:
        r64.setLines(1);
        r64.setSingleLine(true);
        goto L49
    L38:
        r14 = 3;
        goto L39
    L41:
        if (LocaleController.isRTL == false) goto L43;
        int r203 = 5;
    L44:
        float r144 = r27;
        addView(r63, LayoutHelper.createFrame(-1, -2.0f, r203, r144, 32.0f, r144, 4.0f));
        goto L45
    L43:
        r203 = 3;
        goto L44
    L32:
        r132 = 3;
        goto L33
    L28:
        r63.setLines(1);
        r63.setSingleLine(true);
        goto L29
    L24:
        r13 = 0.0f;
        goto L25
    L19:
        r20 = 3;
        goto L20
    L15:
        r62.setLines(1);
        r62.setMaxLines(1);
        r62.setSingleLine(true);
        goto L16
    L11:
        r7 = 3;
    L7:
        r6 = true;
        goto L8
    }

    public TextDetailCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, r3, false, false);
    }

    public TextDetailCell(Context r7, Theme.ResourcesProvider r8, boolean r9, boolean r10) {
        this(r7, 23, r8, r9, r10);
    }

    private /* synthetic */ void lambda$new$0(ClickableSpan r3) {
        if (r3 != null) goto L8;
        return;
    L8:
        performHapticFeedback(0, 1);     // Catch: Exception -> L7
    L5:
        r3.onClick(this.textView);
    }

    private /* synthetic */ void lambda$new$1(ClickableSpan r3) {
        if (r3 != null) goto L8;
        return;
    L8:
        performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L7
    L5:
        r3.onClick(this.valueTextView);
    }

    private /* synthetic */ void lambda$new$2(ClickableSpan r3) {
        if (r3 != null) goto L8;
        return;
    L8:
        performHapticFeedback(0, 1);     // Catch: Exception -> L7
    L5:
        r3.onClick(this.valueTextView);
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public String getText() {
        return (String) this.textView.getText();
    }

    public TextView getTextView() {
        return this.textView;
    }

    public boolean hasImage() {
        if (this.imageView.getDrawable() == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.textView.invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.needDivider == false) goto L21;
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        Paint r02 = r0.getPaint("paintDivider");
    L8:
        if (r02 != null) goto L10;
        r02 = Theme.dividerPaint;
    L10:
        Paint r6 = r02;
        if (LocaleController.isRTL == false) goto L14;
        float r03 = 0.0f;
    L13:
        float r2 = r03;
        float r3 = getMeasuredHeight() - 1;
        int r04 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L18;
        int r1 = AndroidUtilities.dp(20.0f);
    L19:
        r8.drawLine(r2, r3, r04 - r1, getMeasuredHeight() - 1, r6);
        return;
    L18:
        r1 = 0;
        goto L19
    L14:
        r03 = AndroidUtilities.dp(20.0f);
        goto L13
    L7:
        r02 = Theme.dividerPaint;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
        super.onInitializeAccessibilityNodeInfo(r5);
        CharSequence r0 = this.textView.getText();
        CharSequence r1 = this.valueTextView.getText();
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r1) == true) goto L17;
        StringBuilder r2 = new StringBuilder();
        if (this.contentDescriptionValueFirst == false) goto L9;
        CharSequence r3 = r1;
    L10:
        r2.append(r3);
        r2.append(": ");
        if (this.contentDescriptionValueFirst == true) goto L14;
        r0 = r1;
    L14:
        r2.append(r0);
        r5.setText(r2.toString());
        return;
    L9:
        r3 = r0;
        goto L10
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r32 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
        if (this.multiline == true) goto L6;
        r4 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30);
    L6:
        super.onMeasure(r32, r4);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        boolean r1 = false;
        if (this.valueTextView.hit(((int) r7.getX()) - this.valueTextView.getLeft(), ((int) r7.getY()) - this.valueTextView.getTop()) == null) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
    L11:
        if (r0 == false) goto L14;
        return true;
    L14:
        return super.onTouchEvent(r7);
    L8:
        if (this.textView.hit(((int) r7.getX()) - this.textView.getLeft(), ((int) r7.getY()) - this.textView.getTop()) == null) goto L10;
        r1 = true;
    L10:
        r0 = r1;
        goto L11
    L5:
        r0 = false;
        goto L6
    }

    public int processColor(int r1) {
        return r1;
    }

    public void setContentDescriptionValueFirst(boolean r1) {
        this.contentDescriptionValueFirst = r1;
    }

    public void setImage(Drawable r2) {
        setImage(r2, null);
    }

    public void setImage(Drawable r8, CharSequence r9) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) this.valueTextView.getLayoutParams();
        if (LocaleController.isRTL == true) goto L6;
        if (r8 == null) goto L6;
        int r1 = AndroidUtilities.dp(58.0f);
    L7:
        r0.rightMargin = r1;
        this.imageView.setImageDrawable(r8);
        ImageView r02 = this.imageView;
        int r3 = 0;
        if (r8 == null) goto L10;
        boolean r4 = true;
    L11:
        r02.setFocusable(r4);
        this.imageView.setContentDescription(r9);
        ImageView r92 = this.imageView;
        if (r8 != null) goto L14;
        r92.setBackground(null);
        this.imageView.setImportantForAccessibility(2);
    L15:
        int r93 = AndroidUtilities.dp(23.0f);
        if (r8 == null) goto L19;
        r3 = AndroidUtilities.dp(48.0f);
    L19:
        int r94 = r93 + r3;
        boolean r82 = LocaleController.isRTL;
        SpoilersTextView r03 = this.textView;
        if (r82 == false) goto L22;
        ((ViewGroup.MarginLayoutParams) r03.getLayoutParams()).leftMargin = r94;
    L23:
        this.textView.requestLayout();
        return;
    L22:
        ((ViewGroup.MarginLayoutParams) r03.getLayoutParams()).rightMargin = r94;
        goto L23
    L14:
        r92.setBackground(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(48.0f), 0, Theme.getColor(Theme.key_listSelector, this.resourcesProvider)));
        this.imageView.setImportantForAccessibility(1);
        goto L15
    L10:
        r4 = false;
    L6:
        r1 = AndroidUtilities.dp(23.0f);
        goto L7
    }

    public void setImageClickListener(View.OnClickListener r2) {
        this.imageView.setOnClickListener(r2);
        if (r2 != null) goto L6;
        this.imageView.setClickable(false);
        return;
    }

    public void setTextAndValue(CharSequence r2, CharSequence r3, CharSequence r4, boolean r5) {
        this.textView.setText(r2);
        this.valueTextView.setText(r3);
        this.rightValueTextView.setVisibility(0);
        this.rightValueTextView.setText(r4);
        this.needDivider = r5;
        setWillNotDraw(!r5);
    }

    public void setTextAndValue(CharSequence r2, CharSequence r3, boolean r4) {
        this.textView.setText(r2);
        this.valueTextView.setText(r3);
        this.rightValueTextView.setVisibility(8);
        this.needDivider = r4;
        setWillNotDraw(!r4);
    }

    public void updateColors() {
        SpoilersTextView r0 = this.textView;
        int r1 = Theme.key_chat_messageLinkIn;
        r0.setLinkTextColor(processColor(Theme.getColor(r1, this.resourcesProvider)));
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        this.textView.invalidate();
        this.valueTextView.setLinkTextColor(processColor(Theme.getColor(r1, this.resourcesProvider)));
        LinkSpanDrawable.LinksTextView r02 = this.valueTextView;
        int r12 = Theme.key_windowBackgroundWhiteGrayText2;
        r02.setTextColor(Theme.getColor(r12, this.resourcesProvider));
        this.rightValueTextView.setTextColor(Theme.getColor(r12, this.resourcesProvider));
        this.valueTextView.invalidate();
    }
}
