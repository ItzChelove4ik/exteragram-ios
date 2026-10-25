package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class UnreadCounterTextView extends View {
    boolean animatedFromBottom;
    private int circleWidth;
    int counterColor;
    private int currentCounter;
    private String currentCounterString;
    private int horizontalPadding;
    private Drawable icon;
    private Drawable iconOut;
    CharSequence lastText;
    private TextPaint layoutPaint;
    private int layoutTextWidth;
    private int maxLines;
    private Paint paint;
    int panelBackgroundColor;
    private RectF rect;
    ValueAnimator replaceAnimator;
    float replaceProgress;
    private int rippleColor;
    Drawable selectableBackground;
    private boolean singleLine;
    int textColor;
    int textColorKey;
    private StaticLayout textLayout;
    private StaticLayout textLayoutOut;
    private TextPaint textPaint;
    private int textWidth;

    public static /* synthetic */ void $r8$lambda$2HHCh4fxs2uvz7SHdQkFFAm04gc(UnreadCounterTextView r0, ValueAnimator r1) {
        r0.lambda$setText$0(r1);
    }

    public UnreadCounterTextView(Context r2) {
        super(r2);
        this.textPaint = new TextPaint(1);
        this.paint = new Paint(1);
        this.rect = new RectF();
        this.layoutPaint = new TextPaint(1);
        this.replaceProgress = 1.0f;
        this.textColorKey = Theme.key_chat_fieldOverlayText;
        this.singleLine = true;
        this.maxLines = 1;
        this.textPaint.setTextSize(AndroidUtilities.dp(13.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        this.layoutPaint.setTextSize(AndroidUtilities.dp(15.0f));
        this.layoutPaint.setTypeface(AndroidUtilities.bold());
    }

    private /* synthetic */ void lambda$setText$0(ValueAnimator r1) {
        this.replaceProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float r1, float r2) {
        super.drawableHotspotChanged(r1, r2);
        Drawable r0 = this.selectableBackground;
        if (r0 == null) goto L6;
        r0.setHotspot(r1, r2);
        return;
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable r0 = this.selectableBackground;
        if (r0 == null) goto L6;
        r0.setState(getDrawableState());
        return;
    }

    public Theme.ResourcesProvider getResourceProvider() {
        return null;
    }

    public CharSequence getText() {
        return this.lastText;
    }

    public float getTopOffset() {
        return 0.0f;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.selectableBackground;
        if (r0 == null) goto L6;
        r0.jumpToCurrentState();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        StaticLayout r0 = this.textLayout;
        if (isEnabled() == false) goto L5;
        int r1 = this.textColorKey;
    L6:
        int r12 = Theme.getColor(r1, getResourceProvider());
        if (this.textColor == r12) goto L9;
        TextPaint r2 = this.layoutPaint;
        this.textColor = r12;
        r2.setColor(r12);
    L9:
        int r13 = Theme.getColor(Theme.key_chat_messagePanelBackground, getResourceProvider());
        if (this.panelBackgroundColor == r13) goto L12;
        TextPaint r22 = this.textPaint;
        this.panelBackgroundColor = r13;
        r22.setColor(r13);
    L12:
        int r14 = Theme.getColor(Theme.key_chat_goDownButtonCounterBackground, getResourceProvider());
        if (this.counterColor == r14) goto L16;
        Paint r23 = this.paint;
        this.counterColor = r14;
        r23.setColor(r14);
    L16:
        if (getParent() == null) goto L31;
        int r16 = getMeasuredWidth();
        int r3 = (getMeasuredWidth() - r16) / 2;
        if (this.rippleColor == Theme.getColor(this.textColorKey, getResourceProvider())) goto L20;
    L21:
        int r4 = AndroidUtilities.dp(60.0f);
        int r5 = Theme.getColor(this.textColorKey, getResourceProvider());
        this.rippleColor = r5;
        Drawable r42 = Theme.createSimpleSelectorCircleDrawable(r4, 0, ColorUtils.setAlphaComponent(r5, 26));
        this.selectableBackground = r42;
        r42.setCallback(this);
    L23:
        if ((getLeft() + r3) > 0) goto L25;
        int r43 = r3 - AndroidUtilities.dp(20.0f);
    L26:
        int r32 = r3 + r16;
        if (r32 <= ((View) getParent()).getMeasuredWidth()) goto L29;
        r32 = r32 + AndroidUtilities.dp(20.0f);
    L29:
        int r17 = r16 / 2;
        this.selectableBackground.setBounds(r43, (getMeasuredHeight() / 2) - r17, r32, (getMeasuredHeight() / 2) + r17);
        this.selectableBackground.draw(r15);
        goto L31
    L25:
        r43 = r3;
        goto L26
    L20:
        if (this.selectableBackground != null) goto L23;
    L31:
        if (this.textLayout == null) goto L68;
        r15.save();
        if (this.replaceProgress != 1.0f) goto L35;
    L58:
        int r18 = ((getMeasuredWidth() - this.layoutTextWidth) / 2) - (this.circleWidth / 2);
        Drawable r6 = this.icon;
        if (r6 == null) goto L61;
        int r62 = (r6.getIntrinsicWidth() / 2) + AndroidUtilities.dp(3.0f);
    L62:
        r15.translate(r18 + r62, ((getMeasuredHeight() - this.textLayout.getHeight()) / 2) + getTopOffset());
        Drawable r19 = this.icon;
        if (r19 == null) goto L65;
        r19.setBounds((-r19.getIntrinsicWidth()) - AndroidUtilities.dp(6.0f), ((this.textLayout.getHeight() - this.icon.getIntrinsicHeight()) / 2) + AndroidUtilities.dp(1.0f), -AndroidUtilities.dp(6.0f), ((this.textLayout.getHeight() + this.icon.getIntrinsicHeight()) / 2) + AndroidUtilities.dp(1.0f));
        this.icon.setAlpha(255);
        this.icon.draw(r15);
    L65:
        this.textLayout.draw(r15);
    L66:
        r15.restore();
        goto L68
    L61:
        r62 = 0;
        goto L62
    L35:
        if (this.textLayoutOut == null) goto L58;
        int r110 = this.layoutPaint.getAlpha();
        r15.save();
        r15.translate(((getMeasuredWidth() - this.textLayoutOut.getWidth()) / 2) - (this.circleWidth / 2), ((getMeasuredHeight() - this.textLayout.getHeight()) / 2) + getTopOffset());
        Drawable r63 = this.iconOut;
        if (r63 == null) goto L39;
        int r64 = (r63.getIntrinsicWidth() / 2) + AndroidUtilities.dp(3.0f);
    L40:
        float r65 = r64;
        float r8 = -1.0f;
        if (this.animatedFromBottom == false) goto L43;
        float r7 = -1.0f;
    L44:
        r15.translate(r65, (r7 * AndroidUtilities.dp(18.0f)) * this.replaceProgress);
        Drawable r66 = this.iconOut;
        if (r66 == null) goto L47;
        r66.setBounds((-r66.getIntrinsicWidth()) - AndroidUtilities.dp(6.0f), ((this.textLayout.getHeight() - this.iconOut.getIntrinsicHeight()) / 2) + AndroidUtilities.dp(1.0f), -AndroidUtilities.dp(6.0f), ((this.textLayout.getHeight() + this.iconOut.getIntrinsicHeight()) / 2) + AndroidUtilities.dp(1.0f));
        this.iconOut.setAlpha((int) (r110 * (1.0f - this.replaceProgress)));
        this.iconOut.draw(r15);
    L47:
        float r72 = r110;
        this.layoutPaint.setAlpha((int) ((1.0f - this.replaceProgress) * r72));
        this.textLayoutOut.draw(r15);
        r15.restore();
        r15.save();
        r15.translate(((getMeasuredWidth() - this.layoutTextWidth) / 2) - (this.circleWidth / 2), ((getMeasuredHeight() - this.textLayout.getHeight()) / 2) + getTopOffset());
        Drawable r67 = this.icon;
        if (r67 == null) goto L50;
        int r68 = (r67.getIntrinsicWidth() / 2) + AndroidUtilities.dp(3.0f);
    L51:
        float r52 = r68;
        if (this.animatedFromBottom == false) goto L54;
        r8 = 1.0f;
    L54:
        r15.translate(r52, (r8 * AndroidUtilities.dp(18.0f)) * (1.0f - this.replaceProgress));
        Drawable r53 = this.icon;
        if (r53 == null) goto L57;
        r53.setBounds((-r53.getIntrinsicWidth()) - AndroidUtilities.dp(6.0f), ((this.textLayout.getHeight() - this.icon.getIntrinsicHeight()) / 2) + AndroidUtilities.dp(1.0f), -AndroidUtilities.dp(6.0f), ((this.textLayout.getHeight() + this.icon.getIntrinsicHeight()) / 2) + AndroidUtilities.dp(1.0f));
        this.icon.setAlpha((int) (this.replaceProgress * r72));
        this.icon.draw(r15);
    L57:
        this.layoutPaint.setAlpha((int) (r72 * this.replaceProgress));
        this.textLayout.draw(r15);
        r15.restore();
        this.layoutPaint.setAlpha(r110);
        goto L66
    L50:
        r68 = 0;
        goto L51
    L43:
        r7 = 1.0f;
        goto L44
    L39:
        r64 = 0;
    L68:
        if (this.currentCounterString == null) goto L72;
        if (r0 == null) goto L73;
        int r02 = (int) Math.ceil(r0.getLineWidth(0));
        this.rect.set(((((getMeasuredWidth() - r02) / 2) + r02) - (this.circleWidth / 2)) + AndroidUtilities.dp(6.0f), (getMeasuredHeight() / 2) - AndroidUtilities.dp(10.0f), r1 + this.circleWidth, (getMeasuredHeight() / 2) + AndroidUtilities.dp(10.0f));
        r15.drawRoundRect(this.rect, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.paint);
        r15.drawText(this.currentCounterString, this.rect.centerX() - (this.textWidth / 2.0f), this.rect.top + AndroidUtilities.dp(14.5f), this.textPaint);
        return;
    L73:
        return;
    L72:
        return;
    L5:
        r1 = Theme.key_windowBackgroundWhiteGrayText;
        goto L6
    }

    public void setCounter(int r3) {
        if (this.currentCounter == r3) goto L12;
        this.currentCounter = r3;
        if (r3 != 0) goto L7;
        this.currentCounterString = null;
        this.circleWidth = 0;
    L10:
        invalidate();
        return;
    L7:
        this.currentCounterString = AndroidUtilities.formatWholeNumber(r3, 0);
        this.textWidth = (int) Math.ceil(this.textPaint.measureText(r3));
        int r32 = Math.max(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(12.0f) + this.textWidth);
        if (this.circleWidth == r32) goto L10;
        this.circleWidth = r32;
        goto L10
    }

    public void setHorizontalPadding(int r1) {
        this.horizontalPadding = r1;
        invalidate();
    }

    public void setMaxLines(int r2) {
        this.maxLines = r2;
        if (r2 <= 1) goto L5;
        this.singleLine = false;
    L5:
        requestLayout();
    }

    public void setSingleLine(boolean r1) {
        this.singleLine = r1;
        if (r1 == false) goto L5;
        this.maxLines = 1;
    L5:
        requestLayout();
    }

    public void setText(CharSequence r12) {
        if (this.lastText != r12) goto L5;
        return;
    L5:
        this.lastText = r12;
        this.layoutPaint.setTypeface(AndroidUtilities.bold());
        int r3 = AndroidUtilities.displaySize.x - (this.horizontalPadding * 2);
        int r8 = 0;
        if (this.singleLine == false) goto L8;
        this.layoutTextWidth = (int) Math.ceil(this.layoutPaint.measureText(r12, 0, r12.length()));
    L12:
        this.icon = null;
        TextPaint r1 = this.layoutPaint;
        int r2 = this.layoutTextWidth;
        this.textLayout = StaticLayoutEx.createStaticLayout(r12, r1, r2, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r2, this.maxLines, false);
        setContentDescription(r12);
        invalidate();
        return;
    L8:
        this.layoutTextWidth = 0;
        StaticLayout r0 = new StaticLayout(r12, this.layoutPaint, r3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    L10:
        if (r8 >= Math.min(r0.getLineCount(), this.maxLines)) goto L12;
        this.layoutTextWidth = Math.max(this.layoutTextWidth, (int) Math.ceil(r0.getLineWidth(r8)));
        r8 = r8 + 1;
        goto L10
    }

    public void setText(CharSequence r14, boolean r15) {
        if (this.lastText == r14) goto L24;
        this.lastText = r14;
        this.animatedFromBottom = r15;
        this.textLayoutOut = this.textLayout;
        this.iconOut = this.icon;
        this.layoutPaint.setTypeface(AndroidUtilities.bold());
        int r4 = AndroidUtilities.displaySize.x - (this.horizontalPadding * 2);
        int r9 = 0;
        if (this.singleLine == false) goto L8;
        this.layoutTextWidth = (int) Math.ceil(this.layoutPaint.measureText(r14, 0, r14.length()));
    L12:
        this.icon = null;
        TextPaint r2 = this.layoutPaint;
        int r3 = this.layoutTextWidth;
        this.textLayout = StaticLayoutEx.createStaticLayout(r14, r2, r3, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r3, this.maxLines, false);
        setContentDescription(r14);
        invalidate();
        if (this.textLayoutOut == null) goto L15;
    L18:
        ValueAnimator r1 = this.replaceAnimator;
        if (r1 == null) goto L21;
        r1.cancel();
    L21:
        this.replaceProgress = 0.0f;
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.replaceAnimator = r12;
        r12.addUpdateListener(new UnreadCounterTextView$$ExternalSyntheticLambda0(this));
        this.replaceAnimator.setDuration(150);
        this.replaceAnimator.start();
        return;
    L15:
        if (this.iconOut != null) goto L18;
        return;
    L8:
        this.layoutTextWidth = 0;
        StaticLayout r13 = new StaticLayout(r14, this.layoutPaint, r4, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    L10:
        if (r9 >= Math.min(r13.getLineCount(), this.maxLines)) goto L12;
        this.layoutTextWidth = Math.max(this.layoutTextWidth, (int) Math.ceil(r13.getLineWidth(r9)));
        r9 = r9 + 1;
        goto L10
    }

    public void setTextColorKey(int r1) {
        this.textColorKey = r1;
        invalidate();
    }

    public void setTextInfo(Drawable r12, CharSequence r13) {
        if (this.lastText != r13) goto L5;
        return;
    L5:
        this.lastText = r13;
        this.layoutPaint.setTypeface(null);
        int r3 = AndroidUtilities.displaySize.x - (this.horizontalPadding * 2);
        int r8 = 0;
        if (this.singleLine == false) goto L8;
        this.layoutTextWidth = (int) Math.ceil(this.layoutPaint.measureText(r13, 0, r13.length()));
    L12:
        this.icon = r12;
        TextPaint r1 = this.layoutPaint;
        int r0 = this.layoutTextWidth;
        this.textLayout = StaticLayoutEx.createStaticLayout(r13, r1, r0 + 1, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r0 + 1, this.maxLines, false);
        setContentDescription(r13);
        invalidate();
        return;
    L8:
        this.layoutTextWidth = 0;
        StaticLayout r02 = new StaticLayout(r13, this.layoutPaint, r3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    L10:
        if (r8 >= Math.min(r02.getLineCount(), this.maxLines)) goto L12;
        this.layoutTextWidth = Math.max(this.layoutTextWidth, (int) Math.ceil(r02.getLineWidth(r8)));
        r8 = r8 + 1;
        goto L10
    }

    public void setTextInfo(CharSequence r12) {
        if (this.lastText != r12) goto L5;
        return;
    L5:
        this.lastText = r12;
        this.layoutPaint.setTypeface(null);
        int r3 = AndroidUtilities.displaySize.x - (this.horizontalPadding * 2);
        int r9 = 0;
        if (this.singleLine == false) goto L8;
        this.layoutTextWidth = (int) Math.ceil(this.layoutPaint.measureText(r12, 0, r12.length()));
    L12:
        this.icon = null;
        TextPaint r1 = this.layoutPaint;
        int r0 = this.layoutTextWidth;
        this.textLayout = StaticLayoutEx.createStaticLayout(r12, r1, r0 + 1, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r0 + 1, this.maxLines, false);
        setContentDescription(r12);
        invalidate();
        return;
    L8:
        this.layoutTextWidth = 0;
        StaticLayout r02 = new StaticLayout(r12, this.layoutPaint, r3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    L10:
        if (r9 >= Math.min(r02.getLineCount(), this.maxLines)) goto L12;
        this.layoutTextWidth = Math.max(this.layoutTextWidth, (int) Math.ceil(r02.getLineWidth(r9)));
        r9 = r9 + 1;
        goto L10
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        Drawable r0 = this.selectableBackground;
        if (r0 == null) goto L13;
        if (r0 != r2) goto L6;
        return true;
    L6:
        if (super.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    L13:
        return super.verifyDrawable(r2);
    }
}
