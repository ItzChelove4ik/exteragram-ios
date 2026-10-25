package org.telegram.messenger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.GradientClip;

/* loaded from: classes5.dex */
public class BotFullscreenButtons extends View {
    private final AnimatedFloat animatedBack;
    private final AnimatedFloat animatedDownloading;
    private final AnimatedFloat animatedPreview;
    private boolean back;
    private final Text backText;
    private final Paint backgroundPaint;
    private final Path backgroundPath;
    private RenderNode blurNode;
    private final ButtonBounce closeBounce;
    private final RectF closeRect;
    private final RectF closeRectArea;
    private final Text closeText;
    private final ButtonBounce collapseBounce;
    private final RectF collapseClickRect;
    private final RectF collapseRect;
    private final Paint downloadPaint;
    private final Path downloadPath;
    private boolean downloading;
    private final Runnable hidePreview;
    private final Paint iconPaint;
    private final Paint iconStrokePaint;
    private final RectF insets;
    private final RectF leftMenu;
    private final ButtonBounce menuBounce;
    private final RectF menuClickRect;
    private final RectF menuRect;
    private final ButtonBounce nullBounce;
    public Runnable onCloseClickListener;
    public Runnable onCollapseClickListener;
    public Runnable onMenuClickListener;
    public Object parentRenderNode;
    int pressed;
    private boolean preview;
    private final GradientClip previewClip;
    private Text previewText;
    private final RectF rightMenu;
    private final long start;
    private Drawable verifiedBackground;
    private Drawable verifiedForeground;
    public WebView webView;

    public static class OptionsIcon extends Drawable {
        private final AnimatedFloat animatedDownloading;
        private final Paint downloadPaint;
        private final Path downloadPath;
        private boolean downloading;
        private final Drawable drawable;
        private final long start;

        public OptionsIcon(Context r11) {
            Paint r0 = new Paint(1);
            this.downloadPaint = r0;
            Path r1 = new Path();
            this.downloadPath = r1;
            this.downloading = false;
            this.animatedDownloading = new AnimatedFloat(new BotFullscreenButtons$OptionsIcon$$ExternalSyntheticLambda0(this), 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.start = System.currentTimeMillis();
            this.drawable = r11.getResources().getDrawable(R.drawable.ic_ab_other).mutate();
            r0.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(1.0f)));
            r1.rewind();
            r1.moveTo(-AndroidUtilities.dpf2(1.33f), AndroidUtilities.dpf2(0.16f));
            r1.lineTo(-AndroidUtilities.dpf2(1.33f), -AndroidUtilities.dpf2(3.5f));
            r1.lineTo(AndroidUtilities.dpf2(1.33f), -AndroidUtilities.dpf2(3.5f));
            r1.lineTo(AndroidUtilities.dpf2(1.33f), AndroidUtilities.dpf2(0.16f));
            r1.lineTo(AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(0.16f));
            r1.lineTo(0.0f, AndroidUtilities.dpf2(3.5f));
            r1.lineTo(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(0.16f));
            r1.close();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r11) {
            this.drawable.setBounds(getBounds());
            this.drawable.draw(r11);
            float r0 = this.animatedDownloading.set(this.downloading);
            if (r0 <= 0.0f) goto L9;
            r11.save();
            r11.translate(getBounds().centerX(), getBounds().centerY());
            r11.translate(-AndroidUtilities.dpf2(8.166f), AndroidUtilities.dpf2(5.0f));
            float r02 = (r0 * 0.5f) + 0.5f;
            r11.scale(r02, r02);
            this.downloadPaint.setColor(Theme.multAlpha(-1, 0.4f));
            r11.drawPath(this.downloadPath, this.downloadPaint);
            float r03 = ((System.currentTimeMillis() - this.start) % 450) / 450.0f;
            float r2 = 0.5f + r03;
            r11.save();
            r11.clipRect(-AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), r03), AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), r2));
            this.downloadPaint.setColor(Theme.multAlpha(-1, 1.0f));
            r11.drawPath(this.downloadPath, this.downloadPaint);
            r11.restore();
            if (r2 <= 1.0f) goto L7;
            r11.save();
            r11.clipRect(-AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), 0.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), r2 - 1.0f));
            this.downloadPaint.setColor(Theme.multAlpha(-1, 1.0f));
            r11.drawPath(this.downloadPath, this.downloadPaint);
            r11.restore();
        L7:
            r11.restore();
            invalidateSelf();
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.drawable.getIntrinsicHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.drawable.getIntrinsicWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.drawable.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r2) {
            this.downloadPaint.setColorFilter(r2);
            this.drawable.setColorFilter(r2);
        }

        public void setDownloading(boolean r2) {
            if (this.downloading != r2) goto L5;
            return;
        L5:
            this.downloading = r2;
            invalidateSelf();
        }
    }

    /* renamed from: $r8$lambda$S-6xYwuxf-xpVG5G64sbL_DdDrA, reason: not valid java name */
    public static /* synthetic */ void m3913$r8$lambda$S6xYwuxfxpVG5G64sbL_DdDrA(BotFullscreenButtons r0) {
        r0.lambda$new$0();
    }

    public BotFullscreenButtons(Context r13) {
        super(r13);
        this.backgroundPaint = new Paint(1);
        this.iconPaint = new Paint(1);
        Paint r132 = new Paint(1);
        this.iconStrokePaint = r132;
        this.backgroundPath = new Path();
        Paint r1 = new Paint(1);
        this.downloadPaint = r1;
        Path r2 = new Path();
        this.downloadPath = r2;
        this.insets = new RectF();
        this.leftMenu = new RectF();
        this.nullBounce = new ButtonBounce(null);
        this.closeRect = new RectF();
        this.closeRectArea = new RectF();
        this.closeBounce = new ButtonBounce(this);
        this.rightMenu = new RectF();
        this.collapseRect = new RectF();
        this.collapseClickRect = new RectF();
        this.collapseBounce = new ButtonBounce(this);
        this.menuRect = new RectF();
        this.menuClickRect = new RectF();
        this.menuBounce = new ButtonBounce(this);
        CubicBezierInterpolator r11 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatedBack = new AnimatedFloat(this, 0, 320, r11);
        this.preview = true;
        this.animatedPreview = new AnimatedFloat(this, 0, 420, r11);
        this.downloading = false;
        this.animatedDownloading = new AnimatedFloat(this, 0, 420, r11);
        this.previewClip = new GradientClip();
        this.hidePreview = new BotFullscreenButtons$$ExternalSyntheticLambda1(this);
        this.start = System.currentTimeMillis();
        r132.setStyle(Paint.Style.STROKE);
        r132.setStrokeCap(Paint.Cap.ROUND);
        r132.setStrokeJoin(Paint.Join.ROUND);
        this.backText = new Text(LocaleController.getString(R.string.BotFullscreenBack), 13.0f, AndroidUtilities.bold());
        this.closeText = new Text(LocaleController.getString(R.string.BotFullscreenClose), 13.0f, AndroidUtilities.bold());
        r1.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(1.0f)));
        r2.rewind();
        r2.moveTo(-AndroidUtilities.dpf2(1.33f), AndroidUtilities.dpf2(0.16f));
        r2.lineTo(-AndroidUtilities.dpf2(1.33f), -AndroidUtilities.dpf2(3.5f));
        r2.lineTo(AndroidUtilities.dpf2(1.33f), -AndroidUtilities.dpf2(3.5f));
        r2.lineTo(AndroidUtilities.dpf2(1.33f), AndroidUtilities.dpf2(0.16f));
        r2.lineTo(AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(0.16f));
        r2.lineTo(0.0f, AndroidUtilities.dpf2(3.5f));
        r2.lineTo(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(0.16f));
        r2.close();
    }

    private ButtonBounce getBounce(int r2) {
        if (r2 == 1) goto L15;
        if (r2 == 2) goto L13;
        if (r2 == 3) goto L11;
        return this.nullBounce;
    L11:
        return this.menuBounce;
    L13:
        return this.collapseBounce;
    L15:
        return this.closeBounce;
    }

    private int getButton(MotionEvent r4) {
        if (this.closeRectArea.contains(r4.getX(), r4.getY()) == false) goto L7;
        return 1;
    L7:
        if (this.collapseClickRect.contains(r4.getX(), r4.getY()) == false) goto L11;
        return 2;
    L11:
        if (this.menuClickRect.contains(r4.getX(), r4.getY()) == false) goto L14;
        return 3;
    L14:
        return 0;
    }

    private /* synthetic */ void lambda$new$0() {
        setPreview(false, true);
    }

    @Override // android.view.View
    public void onDraw(Canvas r24) {
        super.onDraw(r24);
        this.iconPaint.setColor(-1);
        this.iconStrokePaint.setColor(-1);
        this.iconStrokePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.backgroundPath.rewind();
        this.rightMenu.set((getWidth() - this.insets.right) - AndroidUtilities.dp(79.66f), this.insets.top + AndroidUtilities.dp(8.0f), (getWidth() - this.insets.right) - AndroidUtilities.dp(8.0f), this.insets.top + AndroidUtilities.dp(38.0f));
        RectF r2 = this.collapseRect;
        RectF r3 = this.rightMenu;
        r2.set(r3.left, r3.top, r3.centerX(), this.rightMenu.bottom);
        RectF r22 = this.collapseClickRect;
        float r32 = this.collapseRect.left - AndroidUtilities.dp(8.0f);
        float r4 = this.collapseRect.top - AndroidUtilities.dp(8.0f);
        RectF r6 = this.collapseRect;
        r22.set(r32, r4, r6.right, r6.bottom + AndroidUtilities.dp(8.0f));
        RectF r23 = this.menuRect;
        float r33 = this.rightMenu.centerX();
        RectF r42 = this.rightMenu;
        r23.set(r33, r42.top, r42.right, r42.bottom);
        RectF r25 = this.menuClickRect;
        RectF r34 = this.menuRect;
        r25.set(r34.left, r34.top - AndroidUtilities.dp(8.0f), this.menuRect.right + AndroidUtilities.dp(8.0f), this.menuRect.bottom + AndroidUtilities.dp(8.0f));
        Path r26 = this.backgroundPath;
        RectF r35 = this.rightMenu;
        float r62 = AndroidUtilities.dp(15.0f);
        float r7 = AndroidUtilities.dp(15.0f);
        Path.Direction r11 = Path.Direction.CW;
        r26.addRoundRect(r35, r62, r7, r11);
        float r12 = this.animatedBack.set(this.back);
        float r13 = this.animatedPreview.set(this.preview);
        float r27 = (this.rightMenu.left - AndroidUtilities.dp(18.0f)) - (this.insets.left + AndroidUtilities.dp(38.0f));
        Text r63 = this.previewText;
        if (r63 != null) goto L5;
        float r16 = 18.0f;
        float r64 = 0.0f;
    L10:
        float r28 = Math.min(r27, r64);
        this.leftMenu.set(this.insets.left + AndroidUtilities.dp(8.0f), this.insets.top + AndroidUtilities.dp(8.0f), (this.insets.left + AndroidUtilities.dp(38.0f)) + AndroidUtilities.lerp(AndroidUtilities.lerp(this.closeText.getCurrentWidth(), this.backText.getCurrentWidth(), r12) + AndroidUtilities.dp(12.0f), r28, r13), this.insets.top + AndroidUtilities.dp(38.0f));
        RectF r36 = this.closeRect;
        RectF r43 = this.leftMenu;
        float r5 = r43.left;
        r36.set(r5, r43.top, AndroidUtilities.dp(30.0f) + r5, this.leftMenu.bottom);
        this.closeRectArea.set(this.closeRect);
        this.closeRectArea.right = AndroidUtilities.lerp(this.leftMenu.right, this.closeRect.left + AndroidUtilities.dp(30.0f), r13);
        this.closeRectArea.inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
        this.backgroundPath.addRoundRect(this.leftMenu, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), r11);
        if (this.parentRenderNode != null) goto L13;
    L21:
        float r20 = 12.0f;
        float r162 = 16.0f;
        this.backgroundPaint.setColor(Theme.multAlpha(-16777216, 0.35f));
        r24.drawPath(this.backgroundPath, this.backgroundPaint);
    L28:
        r24.save();
        r24.translate(this.closeRect.centerX(), this.closeRect.centerY());
        float r37 = this.closeBounce.getScale(0.1f);
        r24.scale(r37, r37);
        r24.translate((-AndroidUtilities.dp(6.5f)) * r12, 0.0f);
        float r44 = AndroidUtilities.lerp(AndroidUtilities.dp(4.66f), AndroidUtilities.dp(5.5f), r12);
        float r14 = -r44;
        r24.drawLine(AndroidUtilities.lerp(r14, 0.0f, r12), AndroidUtilities.lerp(r14, 0.0f, r12), r44, r44, this.iconStrokePaint);
        r24.drawLine(AndroidUtilities.lerp(r14, 0.0f, r12), AndroidUtilities.lerp(r44, 0.0f, r12), r44, r14, this.iconStrokePaint);
        if (r12 <= 0.0f) goto L31;
        r24.drawLine(0.0f, 0.0f, AndroidUtilities.dp(11.6f) * r12, 0.0f, this.iconStrokePaint);
    L31:
        r24.restore();
        float r29 = (this.leftMenu.left + AndroidUtilities.dp(30.0f)) - AndroidUtilities.dp(10.0f);
        RectF r1 = this.leftMenu;
        float r38 = r1.top;
        float r45 = r1.right;
        float r52 = r1.bottom;
        Canvas r15 = r24;
        r15.saveLayerAlpha(r29, r38, r45, r52, 255, 31);
        if (r13 > 0.0f) goto L34;
    L46:
        float r17 = r13;
        float r132 = 1.0f;
    L48:
        if (r17 >= r132) goto L56;
        r15.save();
        float r210 = this.closeBounce.getScale(0.1f);
        r15.scale(r210, r210, this.closeRect.centerX(), this.closeRect.centerY());
        float r10 = r132 - r12;
        if (r10 <= 0.0f) goto L52;
        this.closeText.draw(r24, (AndroidUtilities.dp(32.0f) * r17) + ((this.closeRect.left + AndroidUtilities.dp(30.0f)) - (AndroidUtilities.dp(r20) * r12)), this.closeRect.centerY(), -1, r10 * (r132 - r17));
    L52:
        if (r12 <= 0.0f) goto L54;
        this.backText.draw(r24, (AndroidUtilities.dp(32.0f) * r17) + ((this.closeRect.left + AndroidUtilities.dp(30.0f)) + (AndroidUtilities.dp(r20) * r10)), this.closeRect.centerY(), -1, r12 * (r132 - r17));
        r15 = r24;
    L55:
        r15.restore();
        goto L56
    L54:
        r15 = r24;
    L56:
        r15.restore();
        r15.save();
        r15.translate(this.collapseRect.centerX() + AndroidUtilities.dp(2.0f), this.collapseRect.centerY());
        float r211 = this.collapseBounce.getScale(0.1f);
        r15.scale(r211, r211);
        float r102 = AndroidUtilities.dp(6.0f);
        float r39 = AndroidUtilities.dp(3.0f);
        float r53 = -r39;
        r15.drawLine(-r102, r53, 0.0f, r39, this.iconStrokePaint);
        r24.drawLine(0.0f, r39, r102, r53, this.iconStrokePaint);
        r24.restore();
        r24.save();
        r24.translate(this.menuRect.centerX() + AndroidUtilities.dp(r132), this.menuRect.centerY());
        float r212 = this.menuBounce.getScale(0.1f);
        r24.scale(r212, r212);
        r24.drawCircle(0.0f, -AndroidUtilities.dp(5.0f), AndroidUtilities.dp(1.66f), this.iconPaint);
        r24.drawCircle(0.0f, 0.0f, AndroidUtilities.dp(1.66f), this.iconPaint);
        r24.drawCircle(0.0f, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(1.66f), this.iconPaint);
        float r213 = this.animatedDownloading.set(this.downloading);
        if (r213 <= 0.0f) goto L62;
        r24.translate(-AndroidUtilities.dpf2(8.166f), AndroidUtilities.dpf2(3.5f));
        float r214 = (r213 * 0.5f) + 0.5f;
        r24.scale(r214, r214);
        this.downloadPaint.setColor(Theme.multAlpha(-1, 0.4f));
        r24.drawPath(this.downloadPath, this.downloadPaint);
        float r215 = ((System.currentTimeMillis() - this.start) % 450) / 450.0f;
        float r310 = 0.5f + r215;
        r24.save();
        r24.clipRect(-AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), r215), AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), r310));
        this.downloadPaint.setColor(Theme.multAlpha(-1, r132));
        r24.drawPath(this.downloadPath, this.downloadPaint);
        r24.restore();
        if (r310 <= r132) goto L61;
        r24.save();
        r24.clipRect(-AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), 0.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.lerp(-AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(3.5f), r310 - r132));
        this.downloadPaint.setColor(Theme.multAlpha(-1, r132));
        r24.drawPath(this.downloadPath, this.downloadPaint);
        r24.restore();
    L61:
        invalidate();
    L62:
        r24.restore();
        return;
    L34:
        if (this.previewText == null) goto L46;
        r15.save();
        r15.translate((this.leftMenu.left + AndroidUtilities.dp(30.0f)) - ((1.0f - r13) * r28), this.leftMenu.centerY());
        Text r216 = this.previewText;
        float r46 = this.leftMenu.right;
        if (this.verifiedBackground == null) goto L38;
        float r54 = 30.0f;
    L39:
        r132 = 1.0f;
        r216.ellipsize(((r46 - AndroidUtilities.dp(r54)) - (this.leftMenu.left + AndroidUtilities.dp(30.0f))) + 2.0f).draw(r15, 0.0f, 0.0f, -1, r13);
        r15 = r15;
        r17 = r13;
        r15.translate(this.previewText.getWidth() + AndroidUtilities.dp(5.0f), 0.0f);
        int r217 = AndroidUtilities.dp(r162);
        Drawable r311 = this.verifiedBackground;
        if (r311 == null) goto L42;
        r311.setBounds(0, (-r217) / 2, r217, r217 / 2);
        this.verifiedBackground.setAlpha((int) (75.0f * r17));
        this.verifiedBackground.draw(r15);
    L42:
        Drawable r312 = this.verifiedForeground;
        if (r312 == null) goto L45;
        r312.setBounds(0, (-r217) / 2, r217, r217 / 2);
        this.verifiedForeground.setAlpha((int) (255.0f * r17));
        this.verifiedForeground.draw(r15);
    L45:
        RectF r218 = AndroidUtilities.rectTmp;
        float r313 = (this.leftMenu.left + AndroidUtilities.dp(30.0f)) - AndroidUtilities.dp(10.0f);
        RectF r47 = this.leftMenu;
        r218.set(r313, r47.top, r47.left + AndroidUtilities.dp(30.0f), this.leftMenu.bottom);
        this.previewClip.draw(r15, r218, 2, 1.0f);
        r15.restore();
        goto L48
    L38:
        r54 = r20;
        goto L39
    L13:
        if (Build.VERSION.SDK_INT < 31) goto L21;
        if (r24.isHardwareAccelerated() == false) goto L21;
        WebView r314 = this.webView;
        if (r314 == null) goto L23;
        if (r314.getLayerType() != 2) goto L21;
    L23:
        if (this.blurNode != null) goto L25;
        RenderNode r315 = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("bot_fullscreen_blur");
        this.blurNode = r315;
        float r55 = AndroidUtilities.dp(r16);
        float r65 = AndroidUtilities.dp(r16);
        r162 = 16.0f;
        r315.setRenderEffect(RenderEffect.createBlurEffect(r55, r65, Shader.TileMode.CLAMP));
    L26:
        RenderNode r316 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(this.parentRenderNode);
        r20 = 12.0f;
        this.blurNode.setPosition(0, 0, Math.max(1, r316.getWidth() - AndroidUtilities.dp(r162)), Math.max(1, (int) Math.min(this.insets.top + AndroidUtilities.dp(46.0f), r316.getHeight())));
        RecordingCanvas r56 = this.blurNode.beginRecording();
        r56.translate(-AndroidUtilities.dp(8.0f), 0.0f);
        r56.drawRenderNode(r316);
        this.blurNode.endRecording();
        r24.save();
        r24.clipPath(this.backgroundPath);
        r24.save();
        r24.translate(AndroidUtilities.dp(8.0f), 0.0f);
        r24.drawRenderNode(this.blurNode);
        r24.restore();
        this.backgroundPaint.setColor(Theme.multAlpha(-16777216, 0.22f));
        r24.drawPaint(this.backgroundPaint);
        r24.restore();
        goto L28
    L25:
        r162 = 16.0f;
        goto L26
    L5:
        float r66 = r63.getCurrentWidth();
        r16 = 18.0f;
        if (this.verifiedBackground == null) goto L8;
        float r317 = 30.0f;
    L9:
        r64 = r66 + AndroidUtilities.dp(r317);
        goto L10
    L8:
        r317 = 12.0f;
        goto L9
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        if (r6.getAction() != 0) goto L6;
        getBounce(this.pressed).setPressed(false);
        int r62 = getButton(r6);
        this.pressed = r62;
        getBounce(r62).setPressed(true);
    L30:
        if (this.pressed == 0) goto L32;
        return true;
    L32:
        return false;
    L6:
        if (r6.getAction() != 2) goto L11;
        if (getButton(r6) == this.pressed) goto L30;
        this.pressed = 0;
        getBounce(0).setPressed(false);
        goto L30
    L11:
        if (r6.getAction() != 1) goto L27;
        int r63 = this.pressed;
        if (r63 != 1) goto L17;
        Runnable r0 = this.onCloseClickListener;
        if (r0 == null) goto L17;
        r0.run();
    L25:
        getBounce(this.pressed).setPressed(false);
        this.pressed = 0;
    L17:
        if (r63 != 2) goto L21;
        Runnable r02 = this.onCollapseClickListener;
        if (r02 == null) goto L21;
        r02.run();
    L21:
        if (r63 != 3) goto L25;
        Runnable r64 = this.onMenuClickListener;
        if (r64 == null) goto L25;
        r64.run();
        goto L25
    L27:
        if (r6.getAction() != 3) goto L30;
        getBounce(this.pressed).setPressed(false);
        this.pressed = 0;
        goto L30
    }

    public void setBack(boolean r2) {
        setBack(r2, true);
    }

    public void setBack(boolean r1, boolean r2) {
        this.back = r1;
        if (r2 == true) goto L5;
        this.animatedBack.set(r1);
    L5:
        invalidate();
    }

    public void setDownloading(boolean r2) {
        if (this.downloading != r2) goto L5;
        return;
    L5:
        this.downloading = r2;
        invalidate();
    }

    public void setInsets(Rect r1) {
        this.insets.set(r1);
    }

    public void setInsets(RectF r1) {
        this.insets.set(r1);
    }

    public void setName(String r4, boolean r5) {
        this.previewText = new Text(r4, 13.0f, AndroidUtilities.bold());
        if (r5 == true) goto L6;
        this.verifiedBackground = null;
        this.verifiedForeground = null;
        return;
    L6:
        this.verifiedBackground = getContext().getResources().getDrawable(R.drawable.verified_area).mutate();
        this.verifiedForeground = getContext().getResources().getDrawable(R.drawable.verified_check).mutate();
    }

    public void setOnCloseClickListener(Runnable r1) {
        this.onCloseClickListener = r1;
    }

    public void setOnCollapseClickListener(Runnable r1) {
        this.onCollapseClickListener = r1;
    }

    public void setOnMenuClickListener(Runnable r1) {
        this.onMenuClickListener = r1;
    }

    public void setParentRenderNode(Object r1) {
        this.parentRenderNode = r1;
    }

    public void setPreview(boolean r2, boolean r3) {
        AndroidUtilities.cancelRunOnUIThread(this.hidePreview);
        this.preview = r2;
        if (r3 == true) goto L5;
        this.animatedPreview.set(r2, true);
    L5:
        invalidate();
        if (r2 == false) goto L9;
        AndroidUtilities.runOnUIThread(this.hidePreview, 2500);
        return;
    }

    public void setWebView(WebView r1) {
        this.webView = r1;
    }
}
