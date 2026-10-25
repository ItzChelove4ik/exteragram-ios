package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;

/* loaded from: classes3.dex */
public class Text {
    private Layout.Alignment align;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojis;
    private int animatedEmojisCacheType;
    private ColorFilter animatedEmojisColorFilter;
    private int animatedEmojisColorFilterColor;
    private boolean doNotSave;
    private boolean drawAnimatedEmojis;
    private LinearGradient ellipsizeGradient;
    private Matrix ellipsizeMatrix;
    private Paint ellipsizePaint;
    private float ellipsizeWidth;
    private boolean hackClipBounds;
    private StaticLayout layout;
    private float left;
    private float lineSpacingAdd;
    private int maxLines;
    private float maxWidth;
    public final TextPaint paint;
    private View parentView;
    private int vertPad;
    private float width;

    /* renamed from: org.telegram.ui.Components.Text$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ Text this$0;
        final /* synthetic */ View val$view;

        public AnonymousClass1(Text r1, View r2) {
            this.this$0 = r1;
            this.val$view = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r6) {
            Text r62 = this.this$0;
            Text.m13215$$Nest$fputanimatedEmojis(r62, AnimatedEmojiSpan.update(Text.m13213$$Nest$fgetanimatedEmojisCacheType(r62), this.val$view, Text.m13212$$Nest$fgetanimatedEmojis(this.this$0), new Layout[]{Text.m13214$$Nest$fgetlayout(this.this$0)}));
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            AnimatedEmojiSpan.release(this.val$view, Text.m13212$$Nest$fgetanimatedEmojis(this.this$0));
        }
    }

    /* renamed from: -$$Nest$fgetanimatedEmojis, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiSpan.EmojiGroupedSpans m13212$$Nest$fgetanimatedEmojis(Text r0) {
        return r0.animatedEmojis;
    }

    /* renamed from: -$$Nest$fgetanimatedEmojisCacheType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13213$$Nest$fgetanimatedEmojisCacheType(Text r0) {
        return r0.animatedEmojisCacheType;
    }

    /* renamed from: -$$Nest$fgetlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m13214$$Nest$fgetlayout(Text r0) {
        return r0.layout;
    }

    /* renamed from: -$$Nest$fputanimatedEmojis, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13215$$Nest$fputanimatedEmojis(Text r0, AnimatedEmojiSpan.EmojiGroupedSpans r1) {
        r0.animatedEmojis = r1;
    }

    public Text(CharSequence r2, float r3) {
        this(r2, r3, null);
    }

    public Text(CharSequence r3, float r4, Typeface r5) {
        this.maxWidth = 9999.0f;
        this.maxLines = 1;
        this.align = Layout.Alignment.ALIGN_NORMAL;
        this.animatedEmojisCacheType = 0;
        this.ellipsizeWidth = -1.0f;
        TextPaint r1 = new TextPaint(1);
        this.paint = r1;
        r1.setTextSize(AndroidUtilities.dp(r4));
        r1.setTypeface(r5);
        setText(r3);
    }

    public Text(CharSequence r2, TextPaint r3) {
        this.maxWidth = 9999.0f;
        this.maxLines = 1;
        this.align = Layout.Alignment.ALIGN_NORMAL;
        this.animatedEmojisCacheType = 0;
        this.ellipsizeWidth = -1.0f;
        this.paint = r3;
        setText(r2);
    }

    public Text align(Layout.Alignment r2) {
        if (this.align == r2) goto L5;
        this.align = r2;
        setText(this.layout.getText());
    L5:
        return this;
    }

    public float calculateRealWidth() {
        float r0 = 0.0f;
        int r1 = 0;
    L4:
        if (r1 >= this.layout.getLineCount()) goto L6;
        r0 = Math.max(r0, this.layout.getLineWidth(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public void detach() {
        AnimatedEmojiSpan.release(this.parentView, this.animatedEmojis);
    }

    public void draw(Canvas r13) {
        if (this.layout != null) goto L6;
        return;
    L6:
        if (this.doNotSave == true) goto L12;
        float r2 = this.ellipsizeWidth;
        if (r2 < 0.0f) goto L12;
        if (this.width <= r2) goto L12;
        r13.saveLayerAlpha(0.0f, -this.vertPad, r2 - 1.0f, r1.getHeight() + this.vertPad, 255, 31);
    L12:
        r13.save();
        r13.translate(-this.left, 0.0f);
        boolean r22 = this.hackClipBounds;
        StaticLayout r3 = this.layout;
        if (r22 == false) goto L15;
        r13.drawText(r3.getText().toString(), 0.0f, -this.paint.getFontMetricsInt().ascent, this.paint);
    L17:
        if (this.drawAnimatedEmojis == true) goto L19;
    L24:
        r13.restore();
        if (this.doNotSave == true) goto L35;
        float r1 = this.ellipsizeWidth;
        if (r1 >= 0.0f) goto L29;
        return;
    L29:
        if (this.width > r1) goto L31;
        return;
    L31:
        if (this.ellipsizeGradient != null) goto L33;
        this.ellipsizeGradient = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(8.0f), 0.0f, new int[]{16777215, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.ellipsizeMatrix = new Matrix();
        Paint r12 = new Paint(1);
        this.ellipsizePaint = r12;
        r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.ellipsizePaint.setShader(this.ellipsizeGradient);
    L33:
        r13.save();
        this.ellipsizeMatrix.reset();
        this.ellipsizeMatrix.postTranslate(this.ellipsizeWidth - AndroidUtilities.dp(8.0f), 0.0f);
        this.ellipsizeGradient.setLocalMatrix(this.ellipsizeMatrix);
        r13.drawRect(this.ellipsizeWidth - AndroidUtilities.dp(8.0f), 0.0f, this.ellipsizeWidth, this.layout.getHeight(), this.ellipsizePaint);
        r13.restore();
        r13.restore();
        return;
    L35:
        return;
    L19:
        if (this.animatedEmojisColorFilter != null) goto L21;
    L22:
        int r32 = this.paint.getColor();
        this.animatedEmojisColorFilterColor = r32;
        this.animatedEmojisColorFilter = new PorterDuffColorFilter(r32, PorterDuff.Mode.SRC_IN);
    L23:
        AnimatedEmojiSpan.drawAnimatedEmojis(r13, this.layout, this.animatedEmojis, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, this.animatedEmojisColorFilter);
        goto L24
    L21:
        if (this.paint.getColor() == this.animatedEmojisColorFilterColor) goto L23;
    L15:
        r3.draw(r13);
        goto L17
    }

    public void draw(Canvas r2, float r3, float r4) {
        draw(r2, r3, r4, 1.0f);
    }

    public void draw(Canvas r3, float r4, float r5, float r6) {
        if (this.layout != null) goto L6;
        return;
    L6:
        if (this.doNotSave == true) goto L9;
        r3.save();
    L9:
        if (this.maxLines <= 1) goto L11;
        float r0 = 0.0f;
    L12:
        r3.translate(r4, r5 - r0);
        int r42 = this.paint.getAlpha();
        this.paint.setAlpha((int) (r42 * r6));
        draw(r3);
        this.paint.setAlpha(r42);
        if (this.doNotSave == true) goto L16;
        r3.restore();
        return;
    L16:
        return;
    L11:
        r0 = this.layout.getHeight() / 2.0f;
        goto L12
    }

    public void draw(Canvas r3, float r4, float r5, int r6, float r7) {
        if (this.layout != null) goto L5;
        return;
    L5:
        this.paint.setColor(r6);
        TextPaint r0 = this.paint;
        r0.linkColor = r6;
        int r62 = r0.getAlpha();
        if (r7 == 1.0f) goto L9;
        this.paint.setAlpha((int) (r62 * r7));
    L9:
        if (this.doNotSave == true) goto L12;
        r3.save();
    L12:
        if (isMultiline() == false) goto L14;
        float r72 = 0.0f;
    L15:
        r3.translate(r4, r5 - r72);
        draw(r3);
        if (this.doNotSave == true) goto L18;
        r3.restore();
    L18:
        this.paint.setAlpha(r62);
        return;
    L14:
        r72 = this.layout.getHeight() / 2.0f;
        goto L15
    }

    public Text ellipsize(float r1) {
        this.ellipsizeWidth = r1;
        return this;
    }

    public float getCurrentWidth() {
        return this.width;
    }

    public Paint.FontMetricsInt getFontMetricsInt() {
        return this.paint.getFontMetricsInt();
    }

    public float getHeight() {
        return this.layout.getHeight();
    }

    public Layout getLayout() {
        return this.layout;
    }

    public int getLineCount() {
        return this.layout.getLineCount();
    }

    public CharSequence getText() {
        StaticLayout r0 = this.layout;
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (r0.getText() != null) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return this.layout.getText();
    }

    public float getTextSize() {
        return this.paint.getTextSize();
    }

    public float getWidth() {
        float r0 = this.ellipsizeWidth;
        float r2 = this.width;
        if (r0 >= 0.0f) goto L5;
        return r2;
    L5:
        return Math.min(r0, r2);
    }

    public Text hackClipBounds() {
        this.hackClipBounds = true;
        return this;
    }

    public boolean isMultiline() {
        if (this.maxLines <= 1) goto L5;
        return true;
    L5:
        return false;
    }

    public Text lineSpacing(float r2) {
        if (this.lineSpacingAdd == r2) goto L5;
        this.lineSpacingAdd = r2;
        setText(this.layout.getText());
    L5:
        return this;
    }

    public Text multiline(int r1) {
        this.maxLines = r1;
        setText(this.layout.getText());
        return this;
    }

    public Text setAlpha(int r2) {
        this.paint.setAlpha(r2);
        return this;
    }

    public Text setColor(int r2) {
        this.paint.setColor(r2);
        return this;
    }

    public Text setEmojiCacheType(int r6) {
        if (this.animatedEmojisCacheType == r6) goto L7;
        this.animatedEmojisCacheType = r6;
        if (this.drawAnimatedEmojis == false) goto L7;
        AnimatedEmojiSpan.release(this.parentView, this.animatedEmojis);
        this.animatedEmojis = AnimatedEmojiSpan.update(this.animatedEmojisCacheType, this.parentView, this.animatedEmojis, new Layout[]{this.layout});
    L7:
        return this;
    }

    public Text setMaxWidth(float r1) {
        this.maxWidth = r1;
        setText(this.layout.getText());
        return this;
    }

    public Text setShadow(float r5) {
        this.paint.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(0.66f), Theme.multAlpha(-16777216, r5));
        return this;
    }

    public void setText(CharSequence r13) {
        if (this.maxLines <= 1) goto L5;
        this.layout = StaticLayout.Builder.obtain(r13, 0, r13.length(), this.paint, (int) Math.max(this.maxWidth, 1.0f)).setAlignment(this.align).setMaxLines(this.maxLines).setLineSpacing(this.lineSpacingAdd, 1.0f).build();
    L7:
        if (this.align != Layout.Alignment.ALIGN_CENTER) goto L9;
        this.width = this.layout.getWidth();
        this.left = 0.0f;
    L13:
        View r132 = this.parentView;
        if (r132 != null) goto L16;
        return;
    L16:
        if (r132.isAttachedToWindow() == false) goto L21;
        this.animatedEmojis = AnimatedEmojiSpan.update(this.animatedEmojisCacheType, this.parentView, this.animatedEmojis, new Layout[]{this.layout});
        return;
    L21:
        return;
    L9:
        this.width = 0.0f;
        this.left = this.layout.getWidth();
        int r133 = 0;
    L11:
        if (r133 >= this.layout.getLineCount()) goto L13;
        this.width = Math.max(this.width, this.layout.getLineWidth(r133));
        this.left = Math.min(this.left, this.layout.getLineLeft(r133));
        r133 = r133 + 1;
        goto L11
    L5:
        this.layout = new StaticLayout(AndroidUtilities.replaceNewLines(r13), this.paint, (int) Math.max(this.maxWidth, 1.0f), this.align, 1.0f, this.lineSpacingAdd, false);
        goto L7
    }

    public Text setTextSizePx(float r2) {
        this.paint.setTextSize(r2);
        return this;
    }

    public Text setVerticalClipPadding(int r1) {
        this.vertPad = r1;
        return this;
    }

    public Text supportAnimatedEmojis(View r6) {
        this.drawAnimatedEmojis = true;
        this.parentView = r6;
        if (r6.isAttachedToWindow() == false) goto L5;
        this.animatedEmojis = AnimatedEmojiSpan.update(this.animatedEmojisCacheType, r6, this.animatedEmojis, new Layout[]{this.layout});
    L5:
        r6.addOnAttachStateChangeListener(new AnonymousClass1(this, r6));
        return this;
    }
}
