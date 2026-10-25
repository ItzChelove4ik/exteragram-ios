package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.PaintTypeface;
import org.telegram.ui.Components.Paint.Swatch;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.RectOld;

/* loaded from: classes7.dex */
public class TextPaintView extends EntityView {
    private int align;
    private int baseFontSize;
    private int currentType;
    private boolean disableAutoresize;
    private EditTextOutline editText;
    private String lastTypefaceKey;
    private int maxFontSize;
    private int minFontSize;
    private Runnable onFontChange;
    private Swatch swatch;
    private PaintTypeface typeface;

    /* renamed from: org.telegram.ui.Components.Paint.Views.TextPaintView$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextOutline {
        final /* synthetic */ TextPaintView this$0;

        public AnonymousClass1(TextPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            EntityView.SelectionView r0 = this.this$0.selectionView;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.getVisibility() == 0) goto L8;
            return false;
        L8:
            return super.dispatchTouchEvent(r2);
        }

        @Override // org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            this.this$0.updateSelectionView();
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            this.this$0.updateSelectionView();
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.TextPaintView$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        boolean pasted;
        final /* synthetic */ TextPaintView this$0;

        public AnonymousClass2(TextPaintView r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            if (this.pasted == true) goto L5;
        L19:
            TextPaintView.m11621$$Nest$mupdateHint(this.this$0);
            return;
        L5:
            if (TextPaintView.m11619$$Nest$fgetminFontSize(this.this$0) <= 0) goto L19;
            if (TextPaintView.m11618$$Nest$fgetmaxFontSize(this.this$0) <= 0) goto L19;
            if (TextPaintView.m11616$$Nest$fgetdisableAutoresize(this.this$0) == true) goto L19;
            if (TextPaintView.m11617$$Nest$fgeteditText(this.this$0).getLayout() == null) goto L19;
            float r0 = AndroidUtilities.displaySize.y / 3.0f;
            float r32 = TextPaintView.m11617$$Nest$fgeteditText(this.this$0).getLayout().getHeight();
            if (r32 <= r0) goto L19;
            int r33 = Utilities.clamp((int) ((r0 / r32) * this.this$0.getBaseFontSize()), TextPaintView.m11618$$Nest$fgetmaxFontSize(this.this$0), TextPaintView.m11619$$Nest$fgetminFontSize(this.this$0));
            if (r33 == this.this$0.getBaseFontSize()) goto L19;
            this.this$0.setBaseFontSize(r33);
            if (TextPaintView.m11620$$Nest$fgetonFontChange(this.this$0) == null) goto L19;
            TextPaintView.m11620$$Nest$fgetonFontChange(this.this$0).run();
            goto L19
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (r4 <= 3) goto L5;
            boolean r12 = true;
        L6:
            this.pasted = r12;
            return;
        L5:
            r12 = false;
            goto L6
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public class TextViewSelectionView extends EntityView.SelectionView {
        private final Paint clearPaint;
        private Path path;
        final /* synthetic */ TextPaintView this$0;

        public TextViewSelectionView(TextPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            Paint r12 = new Paint(1);
            this.clearPaint = r12;
            this.path = new Path();
            r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        @Override // android.view.View
        public void onDraw(Canvas r19) {
            super.onDraw(r19);
            int r8 = r19.getSaveCount();
            float r1 = getShowAlpha();
            if (r1 > 0.0f) goto L6;
            return;
        L6:
            if (r1 >= 1.0f) goto L8;
            int r6 = (int) (r1 * 255.0f);
            Canvas r12 = r19;
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), r6, 31);
        L9:
            float r3 = AndroidUtilities.dp(2.0f);
            float r10 = AndroidUtilities.dpf2(5.66f);
            float r11 = (r3 + r10) + AndroidUtilities.dp(15.0f);
            float r4 = r11 * 2.0f;
            float r32 = getMeasuredWidth() - r4;
            float r5 = getMeasuredHeight() - r4;
            RectF r42 = AndroidUtilities.rectTmp;
            float r122 = r11 + r32;
            float r13 = r11 + r5;
            r42.set(r11, r11, r122, r13);
            float r62 = AndroidUtilities.dp(12.0f);
            float r33 = Math.min(r62, r32 / 2.0f);
            float r52 = r5 / 2.0f;
            float r14 = Math.min(r62, r52);
            this.path.rewind();
            float r34 = r33 * 2.0f;
            float r63 = r11 + r34;
            float r2 = 2.0f * r14;
            float r7 = r11 + r2;
            r42.set(r11, r11, r63, r7);
            this.path.arcTo(r42, 180.0f, 90.0f);
            float r35 = r122 - r34;
            r42.set(r35, r11, r122, r7);
            this.path.arcTo(r42, 270.0f, 90.0f);
            r12.drawPath(this.path, this.paint);
            this.path.rewind();
            float r72 = r13 - r2;
            r42.set(r11, r72, r63, r13);
            this.path.arcTo(r42, 180.0f, -90.0f);
            r42.set(r35, r72, r122, r13);
            this.path.arcTo(r42, 90.0f, -90.0f);
            r12.drawPath(this.path, this.paint);
            float r9 = r11 + r52;
            r12.drawCircle(r11, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r11, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.drawCircle(r122, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r122, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            float r36 = r11 + r14;
            float r53 = r13 - r14;
            r19.drawLine(r11, r36, r11, r53, this.paint);
            r19.drawLine(r122, r36, r122, r53, this.paint);
            r19.drawCircle(r122, r9, (AndroidUtilities.dp(1.0f) + r10) - 1.0f, this.clearPaint);
            r19.drawCircle(r11, r9, (r10 + AndroidUtilities.dp(1.0f)) - 1.0f, this.clearPaint);
            r19.restoreToCount(r8);
            return;
        L8:
            r12 = r19;
            goto L9
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.SelectionView
        public int pointInsideHandle(float r6, float r7) {
            float r0 = AndroidUtilities.dp(1.0f);
            float r1 = AndroidUtilities.dp(19.5f);
            float r02 = r0 + r1;
            float r4 = r02 * 2.0f;
            float r2 = getMeasuredWidth() - r4;
            float r5 = ((getMeasuredHeight() - r4) / 2.0f) + r02;
            if (r6 > (r02 - r1)) goto L5;
        L12:
            float r03 = r02 + r2;
            if (r6 > (r03 - r1)) goto L15;
            return 0;
        L15:
            if (r7 > (r5 - r1)) goto L17;
            return 0;
        L17:
            if (r6 < (r03 + r1)) goto L19;
            return 0;
        L19:
            if (r7 >= (r5 + r1)) goto L26;
            return 2;
        L26:
            return 0;
        L5:
            if (r7 <= (r5 - r1)) goto L12;
            if (r6 >= (r02 + r1)) goto L12;
            if (r7 >= (r5 + r1)) goto L12;
            return 1;
        }
    }

    public static /* synthetic */ void $r8$lambda$6PI9SHratLVOQUFFJAi9BTRB2Cg(TextPaintView r0) {
        r0.lambda$beginEditing$0();
    }

    /* renamed from: -$$Nest$fgetdisableAutoresize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11616$$Nest$fgetdisableAutoresize(TextPaintView r0) {
        return r0.disableAutoresize;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextOutline m11617$$Nest$fgeteditText(TextPaintView r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgetmaxFontSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11618$$Nest$fgetmaxFontSize(TextPaintView r0) {
        return r0.maxFontSize;
    }

    /* renamed from: -$$Nest$fgetminFontSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11619$$Nest$fgetminFontSize(TextPaintView r0) {
        return r0.minFontSize;
    }

    /* renamed from: -$$Nest$fgetonFontChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11620$$Nest$fgetonFontChange(TextPaintView r0) {
        return r0.onFontChange;
    }

    /* renamed from: -$$Nest$mupdateHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11621$$Nest$mupdateHint(TextPaintView r0) {
        r0.updateHint();
    }

    public TextPaintView(Context r4, PointF r5, int r6, CharSequence r7, Swatch r8, int r9) {
        super(r4, r5);
        this.typeface = PaintTypeface.ROBOTO_MEDIUM;
        this.baseFontSize = r6;
        AnonymousClass1 r52 = new AnonymousClass1(this, r4);
        this.editText = r52;
        NotificationCenter.listenEmojiLoading(r52);
        this.editText.setGravity(19);
        this.editText.setBackgroundColor(0);
        this.editText.setPadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
        this.editText.setClickable(false);
        this.editText.setEnabled(false);
        this.editText.setCursorColor(-1);
        this.editText.setTextSize(0, this.baseFontSize);
        this.editText.setCursorSize(AndroidUtilities.dp(this.baseFontSize * 0.4f));
        this.editText.setText(r7);
        updateHint();
        this.editText.setTextColor(r8.color);
        this.editText.setTypeface(null, 1);
        this.editText.setHorizontallyScrolling(false);
        int r42 = Build.VERSION.SDK_INT;
        EditTextOutline r62 = this.editText;
        if (r42 < 26) goto L5;
        r62.setImeOptions(285212672);
    L6:
        this.editText.setFocusableInTouchMode(true);
        this.editText.setInputType(16384);
        this.editText.setSingleLine(false);
        addView(this.editText, LayoutHelper.createFrame(-2, -2, 51));
        EditTextOutline r63 = this.editText;
        if (r42 < 29) goto L9;
        r63.setBreakStrategy(0);
    L10:
        setSwatch(r8);
        setType(r9);
        updatePosition();
        this.editText.addTextChangedListener(new AnonymousClass2(this));
        return;
    L9:
        r63.setBreakStrategy(0);
        goto L10
    L5:
        r62.setImeOptions(268435456);
        goto L6
    }

    public TextPaintView(Context r8, TextPaintView r9, PointF r10) {
        this(r8, r10, r9.baseFontSize, r9.getText(), r9.getSwatch(), r9.currentType);
        setRotation(r9.getRotation());
        setScale(r9.getScale());
        setTypeface(r9.getTypeface());
        setAlign(r9.getAlign());
        int r7 = getAlign();
        int r82 = 2;
        if (r7 == 1) goto L7;
        if (r7 == 2) goto L6;
        int r72 = 19;
    L8:
        this.editText.setGravity(r72);
        int r73 = getAlign();
        if (r73 != 1) goto L11;
        r82 = 4;
    L19:
        this.editText.setTextAlignment(r82);
        return;
    L11:
        if (r73 == 2) goto L16;
        if (LocaleController.isRTL == false) goto L19;
    L14:
        r82 = 3;
        goto L19
    L16:
        if (LocaleController.isRTL == false) goto L14;
    L6:
        r72 = 21;
        goto L8
    L7:
        r72 = 17;
        goto L8
    }

    private /* synthetic */ void lambda$beginEditing$0() {
        AndroidUtilities.showKeyboard(this.editText);
    }

    private void updateHint() {
        int r0 = this.editText.getText().length();
        EditTextOutline r1 = this.editText;
        if (r0 > 0) goto L6;
        r1.setHint(LocaleController.getString(R.string.TextPlaceholder));
        this.editText.setHintTextColor(1627389951);
        return;
    L6:
        r1.setHint(null);
    }

    public void beginEditing() {
        this.editText.setEnabled(true);
        this.editText.setClickable(true);
        this.editText.requestFocus();
        EditTextOutline r0 = this.editText;
        r0.setSelection(r0.getText().length());
        AndroidUtilities.runOnUIThread(new TextPaintView$$ExternalSyntheticLambda0(this), 300);
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public /* bridge */ /* synthetic */ EntityView.SelectionView createSelectionView() {
        return createSelectionView();
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public TextViewSelectionView createSelectionView() {
        return new TextViewSelectionView(this, getContext());
    }

    public void disableAutoresize(boolean r1) {
        this.disableAutoresize = r1;
    }

    public void endEditing() {
        this.editText.clearFocus();
        this.editText.setEnabled(false);
        this.editText.setClickable(false);
        updateSelectionView();
    }

    public int getAlign() {
        return this.align;
    }

    public int getBaseFontSize() {
        return this.baseFontSize;
    }

    public EditTextOutline getEditText() {
        return this.editText;
    }

    public View getFocusedView() {
        return this.editText;
    }

    public Paint.FontMetricsInt getFontMetricsInt() {
        return this.editText.getPaint().getFontMetricsInt();
    }

    public float getFontSize() {
        return this.editText.getTextSize();
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public RectOld getSelectionBounds() {
        ViewGroup r0 = (ViewGroup) getParent();
        if (r0 == null) goto L5;
        float r02 = r0.getScaleX();
        float r1 = (getMeasuredWidth() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        float r2 = (getMeasuredHeight() * getScale()) + (AndroidUtilities.dp(52.0f) / r02);
        float r3 = (getPositionX() - (r1 / 2.0f)) * r02;
        return new RectOld(r3, (getPositionY() - (((r2 - this.editText.getExtendedPaddingTop()) - AndroidUtilities.dpf2(4.0f)) / 2.0f)) * r02, ((r1 * r02) + r3) - r3, (r2 - this.editText.getExtendedPaddingBottom()) * r02);
    L5:
        return new RectOld();
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingBottom() {
        RectF r0 = this.editText.framePadding;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.bottom;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingLeft() {
        RectF r0 = this.editText.framePadding;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.left;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingRight() {
        RectF r0 = this.editText.framePadding;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.right;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingTop() {
        RectF r0 = this.editText.framePadding;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.top;
    }

    public Swatch getSwatch() {
        return this.swatch;
    }

    public CharSequence getText() {
        return this.editText.getText();
    }

    public int getTextSize() {
        return (int) this.editText.getTextSize();
    }

    public int getType() {
        return this.currentType;
    }

    public PaintTypeface getTypeface() {
        return this.typeface;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updatePosition();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        updatePosition();
    }

    public void setAlign(int r1) {
        this.align = r1;
    }

    public void setBaseFontSize(int r6) {
        this.baseFontSize = r6;
        float r62 = r6;
        int r1 = 0;
        this.editText.setTextSize(0, r62);
        this.editText.setCursorSize(AndroidUtilities.dp(r62 * 0.4f));
        if (this.editText.getText() == null) goto L16;
        Editable r63 = this.editText.getText();
        Emoji.EmojiSpan[] r0 = (Emoji.EmojiSpan[]) r63.getSpans(0, r63.length(), Emoji.EmojiSpan.class);
        int r2 = 0;
    L6:
        if (r2 >= r0.length) goto L8;
        r0[r2].replaceFontMetrics(getFontMetricsInt());
        r0[r2].scale = 0.85f;
        r2 = r2 + 1;
        goto L6
    L8:
        AnimatedEmojiSpan[] r64 = (AnimatedEmojiSpan[]) r63.getSpans(0, r63.length(), AnimatedEmojiSpan.class);
    L10:
        if (r1 >= r64.length) goto L12;
        r64[r1].replaceFontMetrics(getFontMetricsInt());
        r1 = r1 + 1;
        goto L10
    L12:
        this.editText.invalidateForce();
        return;
    }

    public void setMaxWidth(int r1) {
        this.editText.setMaxWidth(r1);
    }

    public void setMinMaxFontSize(int r1, int r2, Runnable r3) {
        this.minFontSize = r1;
        this.maxFontSize = r2;
        this.onFontChange = r3;
    }

    public void setSwatch(Swatch r1) {
        this.swatch = r1.m11415clone();
        updateColor();
    }

    public void setText(CharSequence r2) {
        this.editText.setText(r2);
        updateHint();
    }

    public void setType(int r1) {
        this.currentType = r1;
        updateColor();
    }

    public void setTypeface(String r4) {
        Iterator<PaintTypeface> r0 = PaintTypeface.get().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        PaintTypeface r1 = r0.next();
        if (r1.getKey().equals(r4) == false) goto L4;
        setTypeface(r1);
        r4 = null;
    L8:
        this.lastTypefaceKey = r4;
        updateSelectionView();
    }

    public void setTypeface(PaintTypeface r2) {
        this.typeface = r2;
        if (r2 == null) goto L5;
        this.editText.setTypeface(r2.getTypeface());
    L5:
        updateSelectionView();
    }

    public void updateColor() {
        this.editText.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        int r0 = this.swatch.color;
        int r3 = this.currentType;
        int r5 = -1;
        if (r3 != 0) goto L9;
        this.editText.setStrokeColor(0);
        this.editText.setFrameColor(this.swatch.color);
        if (AndroidUtilities.computePerceivedBrightness(this.swatch.color) < 0.721f) goto L7;
    L6:
        r0 = -16777216;
    L27:
        this.editText.setTextColor(r0);
        this.editText.setCursorColor(r0);
        this.editText.setHandlesColor(r0);
        this.editText.setHighlightColor(Theme.multAlpha(r0, 0.4f));
        return;
    L7:
        r0 = -1;
        goto L27
    L9:
        if (r3 != 1) goto L16;
        EditTextOutline r1 = this.editText;
        if (AndroidUtilities.computePerceivedBrightness(r0) < 0.25f) goto L13;
        int r2 = -1728053248;
    L14:
        r1.setFrameColor(r2);
        goto L27
    L13:
        r2 = -1711276033;
        goto L14
    L16:
        if (r3 != 2) goto L21;
        EditTextOutline r12 = this.editText;
        if (AndroidUtilities.computePerceivedBrightness(r0) < 0.25f) goto L20;
        r5 = -16777216;
    L20:
        r12.setFrameColor(r5);
        goto L27
    L21:
        EditTextOutline r7 = this.editText;
        if (r3 != 3) goto L24;
        r7.setStrokeColor(0);
        this.editText.setFrameColor(0);
        goto L27
    L24:
        r7.setFrameColor(0);
        this.editText.setStrokeColor(this.swatch.color);
        this.editText.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        if (AndroidUtilities.computePerceivedBrightness(this.swatch.color) < 0.721f) goto L7;
        goto L7
    }

    public void updateTypeface() {
        String r0 = this.lastTypefaceKey;
        if (r0 == null) goto L6;
        setTypeface(r0);
        return;
    }
}
