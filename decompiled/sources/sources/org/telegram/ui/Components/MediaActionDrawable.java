package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.text.TextPaint;
import android.util.SparseArray;
import android.view.animation.DecelerateInterpolator;
import com.exteragram.messenger.plugins.PluginsController;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class MediaActionDrawable extends Drawable {
    private float animatedDownloadProgress;
    private boolean animatingTransition;
    private Paint backPaint;
    private ColorFilter colorFilter;
    private int currentIcon;
    private final SparseArray<Drawable[]> customIconDrawables;
    private MediaActionDrawableDelegate delegate;
    private float downloadIconScale;
    private float downloadProgress;
    private float downloadProgressAnimationStart;
    private float downloadProgressTime;
    private float downloadRadOffset;
    public boolean drawProgressCircle;
    private Paint drawablePaint;
    private LinearGradient gradientDrawable;
    private Matrix gradientMatrix;
    private boolean hasOverlayImage;
    private DecelerateInterpolator interpolator;
    private boolean isMini;
    private long lastAnimationTime;
    private int lastPercent;
    private MessageDrawable messageDrawable;
    private int nextIcon;
    private float overrideAlpha;
    public Paint paint;
    public Paint paint2;
    private Paint paint3;
    private String percentString;
    private int percentStringWidth;
    private RectF rect;
    private float savedTransitionProgress;
    private float scale;
    private TextPaint textPaint;
    private float transitionAnimationTime;
    private float transitionProgress;
    private final ColorFilter whiteColorFilter;

    /* loaded from: classes7.dex */
    public interface MediaActionDrawableDelegate {
        void invalidate();
    }

    public MediaActionDrawable() {
        this.textPaint = new TextPaint(1);
        this.paint = new Paint(1);
        this.backPaint = new Paint(1);
        this.paint2 = new Paint(1);
        this.paint3 = new Paint(1);
        this.drawablePaint = new Paint(1);
        this.rect = new RectF();
        this.whiteColorFilter = new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN);
        this.customIconDrawables = new SparseArray();
        this.scale = 1.0f;
        this.interpolator = new DecelerateInterpolator();
        this.transitionAnimationTime = 400.0f;
        this.lastPercent = -1;
        this.overrideAlpha = 1.0f;
        this.transitionProgress = 1.0f;
        this.drawProgressCircle = true;
        this.downloadIconScale = 1.0f;
        this.paint.setColor(-1);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint3.setColor(-1);
        this.textPaint.setTypeface(AndroidUtilities.bold());
        this.textPaint.setTextSize(AndroidUtilities.dp(13.0f));
        this.textPaint.setColor(-1);
        this.paint2.setColor(-1);
    }

    private void drawTintedDrawable(Canvas r10, Drawable r11, int r12, int r13, float r14, int r15) {
        int r0 = (int) (r11.getIntrinsicWidth() * r14);
        int r02 = r0 / 2;
        int r1 = r12 - r02;
        int r142 = ((int) (r11.getIntrinsicHeight() * r14)) / 2;
        int r2 = r13 - r142;
        int r122 = r12 + r02;
        int r132 = r13 + r142;
        applyShaderMatrix(true);
        float r4 = r1;
        float r5 = r2;
        float r6 = r122;
        float r7 = r132;
        int r102 = r10.saveLayer(r4, r5, r6, r7, null);
        r11.setColorFilter(this.whiteColorFilter);
        r11.setAlpha(255);
        r11.setBounds(r1, r2, r122, r132);
        r11.draw(r10);
        this.drawablePaint.set(this.paint2);
        this.drawablePaint.setAlpha(r15);
        this.drawablePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        r10.drawRect(r4, r5, r6, r7, this.drawablePaint);
        this.drawablePaint.setXfermode(null);
        r10.restoreToCount(r102);
    }

    public static float getCircleValue(float r2) {
    L3:
        if (r2 <= 360.0f) goto L5;
        r2 = r2 - 360.0f;
        goto L3
    L5:
        return r2;
    }

    private Drawable getCustomIconDrawable(int r4) {
        if (r4 != 16) goto L6;
        Drawable r0 = Theme.chat_pluginIcon;
    L12:
        if (r0 != null) goto L15;
        return null;
    L15:
        Drawable[] r1 = this.customIconDrawables.get(r4);
        if (r1 != null) goto L18;
    L19:
        Drawable.ConstantState r12 = r0.getConstantState();
        if (r12 == null) goto L22;
        Drawable r13 = r12.newDrawable().mutate();
    L23:
        r1 = new Drawable[]{r0, r13};
        this.customIconDrawables.put(r4, r1);
    L25:
        return r1[1];
    L22:
        r13 = r0;
        goto L23
    L18:
        if (r1[0] == r0) goto L25;
    L6:
        if (r4 != 17) goto L9;
        r0 = Theme.chat_settingsIcon;
        goto L12
    L9:
        if (r4 != 18) goto L11;
        r0 = Theme.chat_stickersIcon;
        goto L12
    L11:
        r0 = PluginsController.getPluginFileIconDrawable(r4);
        goto L12
    }

    public static boolean isCustomFileIcon(int r1) {
        if (r1 != 16) goto L5;
        return true;
    L5:
        if (r1 != 17) goto L7;
        return true;
    L7:
        if (r1 != 18) goto L9;
        return true;
    L9:
        if (PluginsController.isPluginFileIcon(r1) == true) goto L17;
        return false;
    L17:
        return true;
    }

    public void applyShaderMatrix(boolean r5) {
        MessageDrawable r0 = this.messageDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.hasGradient() == true) goto L7;
        return;
    L7:
        if (this.hasOverlayImage == true) goto L16;
        Rect r02 = getBounds();
        Shader r1 = this.messageDrawable.getGradientShader();
        Matrix r2 = this.messageDrawable.getMatrix();
        r2.reset();
        this.messageDrawable.applyMatrixScale();
        if (r5 == false) goto L11;
        r2.postTranslate(-r02.centerX(), (-this.messageDrawable.getTopY()) + r02.top);
    L12:
        r1.setLocalMatrix(r2);
        return;
    L11:
        r2.postTranslate(0.0f, -this.messageDrawable.getTopY());
        goto L12
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r38) {
        Canvas r1 = r38;
        Rect r7 = getBounds();
        MessageDrawable r2 = this.messageDrawable;
        if (r2 == null) goto L10;
        if (r2.hasGradient() == false) goto L10;
        if (this.hasOverlayImage == true) goto L10;
        Shader r22 = this.messageDrawable.getGradientShader();
        this.paint.setShader(r22);
        this.paint2.setShader(r22);
        this.paint3.setShader(r22);
    L15:
        int r10 = r7.centerX();
        int r11 = r7.centerY();
        int r23 = this.nextIcon;
        if (r23 != 4) goto L23;
        int r24 = this.currentIcon;
        if (r24 == 3) goto L22;
        if (r24 == 14) goto L22;
        int r25 = r1.save();
        float r6 = 1.0f - this.transitionProgress;
        float r17 = 0.0f;
        r1.scale(r6, r6, r10, r11);
    L21:
        int r8 = r25;
    L30:
        AndroidUtilities.dp(3.0f);
        float r19 = 90.0f;
        if (this.currentIcon != 2) goto L33;
    L34:
        applyShaderMatrix(false);
        if (this.drawProgressCircle == false) goto L108;
        float r26 = r11;
        float r3 = r26 - (AndroidUtilities.dp(9.0f) * this.scale);
        float r62 = (AndroidUtilities.dp(9.0f) * this.scale) + r26;
        float r12 = (AndroidUtilities.dp(12.0f) * this.scale) + r26;
        int r14 = this.currentIcon;
        if (r14 == 3) goto L40;
        if (r14 == 14) goto L40;
    L42:
        int r5 = this.nextIcon;
        if (r5 == 3) goto L48;
        if (r5 == 14) goto L48;
        if (r5 == 2) goto L48;
        this.paint.setAlpha((int) ((Math.min(1.0f, this.savedTransitionProgress / 0.5f) * 255.0f) * (1.0f - this.transitionProgress)));
        float r4 = this.savedTransitionProgress;
    L47:
        float r9 = r4;
        float r33 = 1.0f;
        float r142 = r26 + (AndroidUtilities.dp(1.0f) * this.scale);
    L51:
        if (this.animatingTransition == false) goto L98;
        int r42 = this.nextIcon;
        if (r42 == 2) goto L93;
        if (r9 <= 0.5f) goto L93;
        float r32 = AndroidUtilities.dp(13.0f);
        float r43 = this.scale;
        float r34 = (r32 * r43) * r43;
        if (this.isMini == false) goto L60;
        int r44 = AndroidUtilities.dp(2.0f);
    L61:
        float r35 = r34 + r44;
        float r92 = r9 - 0.5f;
        float r45 = r92 / 0.5f;
        if (r92 <= 0.2f) goto L64;
        float r28 = (r92 - 0.2f) / 0.3f;
        float r93 = r33;
    L65:
        float r63 = r10;
        float r13 = r63 - r35;
        float r36 = r35 / 2.0f;
        this.rect.set(r13, r12 - r36, r63, r36 + r12);
        float r37 = r28 * 100.0f;
        r1.drawArc(this.rect, r37, (r45 * 104.0f) - r37, false, this.paint);
        float r143 = r142 + ((r12 - r142) * r93);
        if (r28 > r17) goto L68;
    L92:
        r62 = r12;
        float r15 = r63;
        float r27 = r15;
        r3 = r143;
    L97:
        float r94 = r15;
        float r52 = r62;
        float r132 = r12;
        float r122 = r27;
        if (r3 == r52) goto L103;
        float r29 = r10;
        r38.drawLine(r29, r3, r29, r52, this.paint);
    L103:
        float r46 = r10;
        if (r122 == r46) goto L106;
        r1 = r38;
        r1.drawLine(r122, r132, r46, r52, this.paint);
        r1.drawLine(r94, r132, r46, r52, this.paint);
    L149:
        int r210 = this.currentIcon;
        if (r210 != 3) goto L152;
    L181:
        int r123 = 15;
        boolean r211 = false;
    L182:
        applyShaderMatrix(r211);
        int r212 = this.nextIcon;
        if (r212 == 2) goto L185;
        if (r212 == r123) goto L206;
        if (r212 == 0) goto L206;
        if (r212 == 1) goto L206;
        if (r212 == 5) goto L206;
        if (isCustomFileIcon(r212) == true) goto L206;
        int r213 = this.nextIcon;
        if (r213 == 8) goto L206;
        if (r213 == 9) goto L206;
        if (r213 == 7) goto L206;
        if (r213 == 6) goto L206;
        if (r213 != 4) goto L216;
        float r53 = 1.0f - this.transitionProgress;
        float r214 = AndroidUtilities.dp(7.0f) * this.scale;
        int r47 = (int) (r53 * 255.0f);
        if (this.currentIcon != 14) goto L213;
        float r39 = r7.left;
        int r64 = r7.top;
    L214:
        int r133 = r47;
        float r282 = 360.0f;
        float r48 = r17;
        float r95 = r64;
    L191:
        float r65 = 1.0f;
    L237:
        if (r53 == r65) goto L240;
        r1.save();
        r1.scale(r53, r53, r39, r95);
    L240:
        if (r48 == r17) goto L242;
        r1.save();
        r1.rotate(r48, r10, r11);
    L242:
        if (r133 == 0) goto L250;
        float r49 = r133;
        this.paint.setAlpha((int) (this.overrideAlpha * r49));
        if (this.currentIcon != 14) goto L246;
    L247:
        float r144 = 4.0f;
        this.paint3.setAlpha((int) (r49 * this.overrideAlpha));
        this.rect.set(r10 - AndroidUtilities.dp(3.5f), r11 - AndroidUtilities.dp(3.5f), AndroidUtilities.dp(3.5f) + r10, AndroidUtilities.dp(3.5f) + r11);
        r1.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.paint3);
    L251:
        if (r48 == r17) goto L253;
        r1.restore();
    L253:
        if (r53 == r65) goto L255;
        r1.restore();
    L255:
        int r215 = this.currentIcon;
        if (r215 != 3) goto L258;
    L264:
        if (r133 != 0) goto L266;
    L279:
        int r216 = this.currentIcon;
        if (r216 != this.nextIcon) goto L283;
        float r96 = 1.0f;
        float r134 = 1.0f;
    L292:
        int r217 = this.nextIcon;
        if (r217 != r123) goto L297;
        Path[] r310 = Theme.chat_updatePath;
    L295:
        Path[] r410 = null;
    L300:
        if (r217 != 5) goto L304;
        r310 = Theme.chat_filePath;
    L302:
        Path[] r124 = r310;
        Path[] r145 = r410;
        if (isCustomFileIcon(r217) == false) goto L309;
        Drawable r218 = getCustomIconDrawable(this.nextIcon);
    L311:
        if (isCustomFileIcon(this.currentIcon) == false) goto L313;
        Drawable r16 = getCustomIconDrawable(this.currentIcon);
    L314:
        int r311 = this.nextIcon;
        if (r311 != 7) goto L319;
        r218 = Theme.chat_flameIcon;
    L321:
        if (r311 != 8) goto L325;
        r218 = Theme.chat_gifIcon;
    L323:
        Drawable r152 = r218;
        Drawable r219 = r16;
        if (this.currentIcon == 9) goto L332;
        if (r311 == 9) goto L332;
        Rect r283 = r7;
        Drawable r72 = r219;
    L344:
        if (this.currentIcon == 12) goto L349;
        if (this.nextIcon == 12) goto L349;
        float r222 = r134;
        int r135 = 13;
    L367:
        if (this.currentIcon == r135) goto L372;
        if (this.nextIcon == r135) goto L372;
        float r18 = 5.0f;
    L390:
        int r220 = this.currentIcon;
        if (r220 == 0) goto L396;
        if (r220 == 1) goto L396;
        int r312 = this.nextIcon;
        if (r312 == 0) goto L396;
        if (r312 == 1) goto L396;
    L464:
        if (this.currentIcon == 6) goto L469;
        if (this.nextIcon == 6) goto L469;
        float r162 = r96;
    L492:
        if (r72 == null) goto L502;
        if (r72 == r152) goto L502;
        int r110 = this.currentIcon;
        if (r110 != this.nextIcon) goto L497;
        int r66 = 255;
    L499:
        if (isCustomFileIcon(r110) == false) goto L501;
        Canvas r111 = r38;
        int r313 = r10;
        int r411 = r11;
        float r54 = r222;
        drawTintedDrawable(r111, r72, r313, r411, r54, r66);
        float r73 = r54;
    L503:
        if (r152 == null) goto L512;
        int r221 = this.currentIcon;
        int r55 = this.nextIcon;
        if (r221 != r55) goto L507;
        int r67 = 255;
    L509:
        if (isCustomFileIcon(r55) == false) goto L511;
        float r56 = r162;
        drawTintedDrawable(r111, r152, r313, r411, r56, r67);
    L514:
        if (r145 == null) goto L527;
        if (r145 == r124) goto L527;
        int r68 = AndroidUtilities.dp(24.0f);
        this.paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint r97 = this.paint2;
        if (this.currentIcon != this.nextIcon) goto L519;
        int r102 = 255;
    L520:
        r97.setAlpha(r102);
        applyShaderMatrix(true);
        r111.save();
        r111.translate(r313, r411);
        r111.scale(r73, r73);
        float r69 = (-r68) / 2;
        r111.translate(r69, r69);
        Path r610 = r145[0];
        if (r610 == null) goto L523;
        r111.drawPath(r610, this.paint2);
    L523:
        Path r611 = r145[1];
        if (r611 == null) goto L526;
        r111.drawPath(r611, this.backPaint);
    L526:
        r111.restore();
        goto L527
    L519:
        r102 = (int) ((1.0f - this.transitionProgress) * 255.0f);
    L527:
        if (r124 == null) goto L547;
        int r223 = AndroidUtilities.dp(24.0f);
        if (this.currentIcon != this.nextIcon) goto L531;
        int r612 = 255;
    L532:
        this.paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint2.setAlpha(r612);
        applyShaderMatrix(true);
        r111.save();
        r111.translate(r313, r411);
        r111.scale(r56, r56);
        float r224 = (-r223) / 2;
        r111.translate(r224, r224);
        Path r225 = r124[0];
        if (r225 == null) goto L536;
        r111.drawPath(r225, this.paint2);
    L536:
        if (r124.length < 3) goto L540;
        Path r226 = r124[2];
        if (r226 == null) goto L540;
        r111.drawPath(r226, this.paint);
    L540:
        Path r227 = r124[1];
        if (r227 == null) goto L546;
        Paint r314 = this.backPaint;
        if (r612 == 255) goto L545;
        int r228 = r314.getAlpha();
        this.backPaint.setAlpha((int) (r228 * (r612 / 255.0f)));
        r111.drawPath(r124[1], this.backPaint);
        this.backPaint.setAlpha(r228);
        goto L546
    L545:
        r111.drawPath(r227, r314);
    L546:
        r111.restore();
        goto L547
    L531:
        r612 = (int) (this.transitionProgress * 255.0f);
    L547:
        long r229 = System.currentTimeMillis();
        long r412 = r229 - this.lastAnimationTime;
        if (r412 <= 17) goto L550;
        r412 = 17;
    L550:
        this.lastAnimationTime = r229;
        int r230 = this.currentIcon;
        if (r230 != 3) goto L553;
    L562:
        float r231 = this.downloadRadOffset + ((360 * r412) / 2500.0f);
        this.downloadRadOffset = r231;
        this.downloadRadOffset = getCircleValue(r231);
        if (this.nextIcon == 2) goto L570;
        float r232 = this.downloadProgress;
        float r315 = this.downloadProgressAnimationStart;
        float r613 = r232 - r315;
        if (r613 <= 0.0f) goto L570;
        float r98 = this.downloadProgressTime + r412;
        this.downloadProgressTime = r98;
        if (r98 < 200.0f) goto L569;
        this.animatedDownloadProgress = r232;
        this.downloadProgressAnimationStart = r232;
        this.downloadProgressTime = 0.0f;
        goto L570
    L569:
        this.animatedDownloadProgress = r315 + (r613 * this.interpolator.getInterpolation(r98 / 200.0f));
    L570:
        invalidateSelf();
    L572:
        if (this.animatingTransition == false) goto L580;
        float r233 = this.transitionProgress;
        if (r233 >= 1.0f) goto L580;
        float r234 = r233 + (r412 / this.transitionAnimationTime);
        this.transitionProgress = r234;
        if (r234 < 1.0f) goto L578;
        this.currentIcon = this.nextIcon;
        this.transitionProgress = 1.0f;
        this.animatingTransition = false;
    L578:
        invalidateSelf();
    L580:
        if (r8 < 1) goto L583;
        r111.restoreToCount(r8);
        return;
    L583:
        return;
    L553:
        if (r230 == 14) goto L562;
        if (r230 != 4) goto L559;
        if (this.nextIcon == 14) goto L562;
    L559:
        if (r230 == 10) goto L562;
        if (r230 != 13) goto L572;
    L511:
        r56 = r162;
        r152.setColorFilter(this.colorFilter);
        r152.setAlpha(r67);
        int r99 = ((int) (r152.getIntrinsicWidth() * r56)) / 2;
        int r103 = ((int) (r152.getIntrinsicHeight() * r56)) / 2;
        r152.setBounds(r313 - r99, r411 - r103, r99 + r313, r103 + r411);
        r152.draw(r111);
        goto L514
    L507:
        r67 = (int) (this.transitionProgress * 255.0f);
        goto L509
    L512:
        r56 = r162;
        goto L514
    L501:
        r111 = r38;
        Drawable r235 = r72;
        r313 = r10;
        r411 = r11;
        r73 = r222;
        r235.setColorFilter(this.colorFilter);
        r235.setAlpha(r66);
        int r57 = ((int) (r235.getIntrinsicWidth() * r73)) / 2;
        int r910 = ((int) (r235.getIntrinsicHeight() * r73)) / 2;
        r235.setBounds(r313 - r57, r411 - r910, r57 + r313, r411 + r910);
        r235.draw(r111);
        goto L503
    L497:
        r66 = (int) ((1.0f - this.transitionProgress) * 255.0f);
    L502:
        r111 = r38;
        r313 = r10;
        r411 = r11;
        r73 = r222;
    L469:
        applyShaderMatrix(false);
        if (this.currentIcon == 6) goto L479;
        float r236 = this.transitionProgress;
        if (r236 <= 0.5f) goto L477;
        float r237 = (r236 - 0.5f) / 0.5f;
        float r58 = 1.0f - Math.min(1.0f, r237 / 0.5f);
        if (r237 <= 0.5f) goto L476;
        float r238 = (r237 - 0.5f) / 0.5f;
    L478:
        this.paint.setAlpha(255);
        float r182 = r238;
    L484:
        int r239 = AndroidUtilities.dp(7.0f) + r11;
        int r316 = r10 - AndroidUtilities.dp(3.0f);
        if (r58 >= 1.0f) goto L488;
        int r136 = r239;
        r162 = r96;
        int r911 = r316;
        r38.drawLine(r316 - AndroidUtilities.dp(6.0f), r239 - AndroidUtilities.dp(6.0f), r316 - (AndroidUtilities.dp(6.0f) * r58), r239 - (AndroidUtilities.dp(6.0f) * r58), this.paint);
    L490:
        if (r182 <= 0.0f) goto L492;
        float r240 = r911;
        float r317 = r136;
        r38.drawLine(r240, r317, r240 + (AndroidUtilities.dp(12.0f) * r182), r317 - (AndroidUtilities.dp(12.0f) * r182), this.paint);
        goto L492
    L488:
        r136 = r239;
        r162 = r96;
        r911 = r316;
        goto L490
    L476:
        r238 = 0.0f;
        goto L478
    L477:
        r238 = 0.0f;
        r58 = 1.0f;
        goto L478
    L479:
        int r241 = this.nextIcon;
        Paint r318 = this.paint;
        if (r241 == 6) goto L482;
        r318.setAlpha((int) ((1.0f - this.transitionProgress) * 255.0f));
    L483:
        r58 = 0.0f;
        r182 = 1.0f;
        goto L484
    L482:
        r318.setAlpha(255);
    L396:
        if (r220 == 0) goto L398;
    L399:
        if (r220 == 1) goto L401;
        int r319 = 1;
    L414:
        if (r220 == r319) goto L410;
    L411:
        float r59 = 0.0f;
    L416:
        int r413 = this.nextIcon;
        if (r413 == 0) goto L419;
        if (r413 == r319) goto L419;
    L421:
        Paint r320 = this.paint2;
        if (r413 != 4) goto L425;
        r320.setAlpha((int) ((1.0f - this.transitionProgress) * 255.0f));
    L430:
        applyShaderMatrix(true);
        r1.save();
        r1.translate(r283.centerX() + (AndroidUtilities.dp(1.0f) * (1.0f - r59)), r283.centerY());
        float r510 = r59 * 500.0f;
        int r242 = this.currentIcon;
        if (r242 != 1) goto L433;
        float r321 = 90.0f;
    L435:
        if (r242 == 0) goto L437;
    L446:
        if (r242 == 1) goto L448;
    L455:
        r1.rotate(r321);
        int r243 = this.currentIcon;
        if (r243 == 0) goto L460;
        if (r243 == 1) goto L460;
    L461:
        r1.scale(r96, r96);
    L462:
        Theme.playPauseAnimator.draw(r1, this.paint2, r510);
        r1.scale(1.0f, -1.0f);
        Theme.playPauseAnimator.draw(r1, this.paint2, r510);
        r1.restore();
    L460:
        if (r243 != 4) goto L462;
    L448:
        if (this.nextIcon != 0) goto L455;
        if (r510 >= 100.0f) goto L453;
        r19 = CubicBezierInterpolator.EASE_BOTH.getInterpolation(r510 / 100.0f) * (-5.0f);
    L445:
        r321 = r19;
        goto L455
    L453:
        if (r510 >= 484.0f) goto L445;
        r19 = (CubicBezierInterpolator.EASE_BOTH.getInterpolation((r510 - 100.0f) / 384.0f) * 95.0f) - 5.0f;
        goto L445
    L437:
        if (this.nextIcon != 1) goto L446;
        if (r510 >= 384.0f) goto L442;
        r19 = CubicBezierInterpolator.EASE_BOTH.getInterpolation(r510 / 384.0f) * 95.0f;
    L444:
        r510 = r510 + 100.0f;
        goto L445
    L442:
        if (r510 >= 484.0f) goto L444;
        r19 = 95.0f - (CubicBezierInterpolator.EASE_BOTH.getInterpolation((r510 - 384.0f) / 100.0f) * r18);
        goto L444
    L433:
        r321 = 0.0f;
        goto L435
    L425:
        if (r220 != r413) goto L427;
        int r244 = 255;
    L428:
        r320.setAlpha(r244);
        goto L430
    L427:
        r244 = (int) (this.transitionProgress * 255.0f);
    L419:
        if (r220 == 0) goto L429;
        if (r220 != r319) goto L421;
    L429:
        this.paint2.setAlpha(255);
    L410:
        r59 = 1.0f;
        goto L416
    L401:
        if (this.nextIcon != 0) goto L412;
    L402:
        boolean r322 = this.animatingTransition;
        int r414 = this.nextIcon;
        if (r322 == false) goto L408;
        float r323 = this.transitionProgress;
        if (r414 != 0) goto L407;
        r323 = 1.0f - r323;
    L407:
        r59 = r323;
        r319 = 1;
        goto L416
    L408:
        r319 = 1;
        if (r414 != 1) goto L411;
    L412:
        r319 = 1;
        goto L414
    L398:
        if (this.nextIcon == 1) goto L402;
    L372:
        applyShaderMatrix(false);
        int r245 = this.currentIcon;
        int r415 = this.nextIcon;
        if (r245 != r415) goto L375;
        float r511 = 1.0f;
    L379:
        this.textPaint.setAlpha((int) (r511 * 255.0f));
        int r246 = AndroidUtilities.dp(5.0f) + r11;
        int r416 = r10 - (this.percentStringWidth / 2);
        r18 = 5.0f;
        if (this.currentIcon == this.nextIcon) goto L382;
        r1.save();
        r1.scale(r511, r511, r10, r11);
    L382:
        int r324 = (int) (this.animatedDownloadProgress * 100.0f);
        if (this.percentString != null) goto L385;
    L386:
        this.lastPercent = r324;
        this.percentString = String.format("%d%%", new Object[]{Integer.valueOf(r324)});
        this.percentStringWidth = (int) Math.ceil(this.textPaint.measureText(r3));
    L387:
        r1.drawText(this.percentString, r416, r246, this.textPaint);
        if (this.currentIcon == this.nextIcon) goto L390;
        r1.restore();
        goto L390
    L385:
        if (r324 == this.lastPercent) goto L387;
    L375:
        r511 = this.transitionProgress;
        if (r415 == r135) goto L379;
        r511 = 1.0f - r511;
    L349:
        applyShaderMatrix(false);
        int r247 = this.currentIcon;
        int r325 = this.nextIcon;
        if (r247 != r325) goto L352;
        int r417 = 13;
        float r512 = 1.0f;
    L356:
        Paint r614 = this.paint;
        if (r247 != r325) goto L359;
        int r248 = 255;
    L360:
        r614.setAlpha(r248);
        AndroidUtilities.dp(7.0f);
        AndroidUtilities.dp(3.0f);
        if (this.currentIcon == this.nextIcon) goto L363;
        r1.save();
        r1.scale(r512, r512, r10, r11);
    L363:
        float r249 = AndroidUtilities.dp(7.0f) * this.scale;
        float r326 = r10;
        float r250 = r326 - r249;
        float r615 = r11;
        float r327 = r615 - r249;
        float r183 = r326 + r249;
        float r513 = r249 + r615;
        r222 = r134;
        r135 = r417;
        r1.drawLine(r250, r327, r183, r513, this.paint);
        r1 = r38;
        r1.drawLine(r183, r327, r250, r513, this.paint);
        if (this.currentIcon == this.nextIcon) goto L367;
        r1.restore();
        goto L367
    L359:
        r248 = (int) (r512 * 255.0f);
        goto L360
    L352:
        r512 = this.transitionProgress;
        r417 = 13;
        if (r325 == 13) goto L356;
        r512 = 1.0f - r512;
    L332:
        applyShaderMatrix(false);
        Paint r328 = this.paint;
        if (this.currentIcon != this.nextIcon) goto L335;
        int r418 = 255;
    L336:
        r328.setAlpha(r418);
        int r329 = AndroidUtilities.dp(7.0f) + r11;
        int r419 = r10 - AndroidUtilities.dp(3.0f);
        if (this.currentIcon == this.nextIcon) goto L339;
        r1.save();
        float r514 = this.transitionProgress;
        Drawable r184 = r219;
        r1.scale(r514, r514, r10, r11);
    L340:
        float r420 = r419;
        float r515 = r329;
        r283 = r7;
        r72 = r184;
        r1.drawLine(r419 - AndroidUtilities.dp(6.0f), r329 - AndroidUtilities.dp(6.0f), r420, r515, this.paint);
        r1 = r38;
        r1.drawLine(r420, r515, r419 + AndroidUtilities.dp(12.0f), r329 - AndroidUtilities.dp(12.0f), this.paint);
        if (this.currentIcon == this.nextIcon) goto L344;
        r1.restore();
        goto L344
    L339:
        r184 = r219;
        goto L340
    L335:
        r418 = (int) (this.transitionProgress * 255.0f);
        goto L336
    L325:
        if (this.currentIcon != 8) goto L323;
        r16 = Theme.chat_gifIcon;
        goto L323
    L319:
        if (this.currentIcon != 7) goto L321;
        r16 = Theme.chat_flameIcon;
        goto L321
    L313:
        r16 = null;
        goto L314
    L309:
        r218 = null;
        goto L311
    L304:
        if (this.currentIcon != 5) goto L302;
        r410 = Theme.chat_filePath;
        goto L302
    L297:
        if (this.currentIcon != r123) goto L299;
        r410 = Theme.chat_updatePath;
        r310 = null;
        goto L300
    L299:
        r310 = null;
        goto L295
    L283:
        if (r216 == 4) goto L291;
        if (r216 == 3) goto L291;
        if (r216 == 14) goto L291;
        float r516 = Math.min(1.0f, this.transitionProgress / 0.5f);
        float r251 = Math.max(r17, 1.0f - (this.transitionProgress / 0.5f));
    L290:
        r134 = r251;
        r96 = r516;
    L291:
        r516 = this.transitionProgress;
        r251 = 1.0f - r516;
        goto L290
    L266:
        if (this.drawProgressCircle == false) goto L279;
        float r912 = Math.max(r144, this.animatedDownloadProgress * r282);
        if (this.isMini == false) goto L270;
        float r616 = 2.0f;
    L271:
        int r252 = AndroidUtilities.dp(r616);
        this.rect.set(r7.left + r252, r7.top + r252, r7.right - r252, r7.bottom - r252);
        int r253 = this.currentIcon;
        if (r253 != 14) goto L274;
    L277:
        this.paint.setAlpha((int) ((r133 * 0.15f) * this.overrideAlpha));
        r1.drawArc(this.rect, 0.0f, 360.0f, false, this.paint);
        this.paint.setAlpha(r133);
    L278:
        r1 = r38;
        r1.drawArc(this.rect, this.downloadRadOffset, r912, false, this.paint);
        goto L279
    L274:
        if (r253 != 4) goto L278;
        if (this.nextIcon != 14) goto L278;
    L270:
        r616 = r144;
        goto L271
    L258:
        if (r215 == 14) goto L264;
        if (r215 != 4) goto L279;
        int r254 = this.nextIcon;
        if (r254 == 14) goto L264;
        if (r254 != 3) goto L279;
    L246:
        if (this.nextIcon == 14) goto L247;
        float r330 = r10;
        float r421 = r330 - r214;
        float r517 = r11;
        float r331 = r517 - r214;
        float r617 = r330 + r214;
        float r518 = r517 + r214;
        r144 = 4.0f;
        r1.drawLine(r421, r331, r617, r518, this.paint);
        r1 = r38;
        r1.drawLine(r617, r331, r421, r518, this.paint);
        goto L251
    L250:
        r144 = 4.0f;
        goto L251
    L213:
        r39 = r7.centerX();
        r64 = r7.centerY();
        goto L214
    L216:
        if (r213 != 14) goto L218;
    L221:
        float r519 = this.transitionProgress;
        float r255 = 1.0f - r519;
        if (this.currentIcon != 4) goto L224;
        float r256 = r519;
        float r332 = r17;
    L225:
        float r422 = AndroidUtilities.dp(7.0f) * this.scale;
        int r520 = (int) (r519 * 255.0f);
        r282 = 360.0f;
        if (this.nextIcon != 14) goto L229;
        float r618 = r7.left;
        int r913 = r7.top;
    L228:
        r95 = r913;
        r133 = r520;
        r53 = r256;
        r214 = r422;
        r48 = r332;
        r39 = r618;
        goto L191
    L229:
        r618 = r7.centerX();
        r913 = r7.centerY();
        goto L228
    L224:
        r332 = r255 * 45.0f;
        r256 = 1.0f;
        goto L225
    L218:
        if (r213 == 3) goto L221;
        r214 = AndroidUtilities.dp(7.0f) * this.scale;
        r282 = 360.0f;
        r39 = r17;
        r48 = r39;
        r95 = r48;
        r53 = 1.0f;
        r65 = 1.0f;
        r133 = 255;
    L206:
        r282 = 360.0f;
        int r257 = this.nextIcon;
        float r333 = this.transitionProgress;
        if (r257 != 6) goto L234;
        r65 = 1.0f;
        r333 = Math.min(1.0f, r333 / 0.5f);
    L235:
        r53 = r65 - r333;
        float r258 = r7.centerX();
        float r334 = r7.centerY();
        float r423 = (AndroidUtilities.dp(7.0f) * r53) * this.scale;
        r133 = (int) (Math.min(r65, r53 * 2.0f) * 255.0f);
        r95 = r334;
        r39 = r258;
        r214 = r423;
        r48 = r17;
        goto L237
    L234:
        r65 = 1.0f;
        goto L235
    L185:
        if (this.drawProgressCircle == false) goto L189;
        float r259 = this.transitionProgress;
        if (r259 > 0.5f) goto L189;
        float r521 = 1.0f - (r259 / 0.5f);
        r214 = (AndroidUtilities.dp(7.0f) * r521) * this.scale;
        int r424 = (int) (r521 * 255.0f);
    L190:
        r133 = r424;
        r282 = 360.0f;
        r39 = r17;
        r48 = r39;
        r95 = r48;
        r53 = 1.0f;
    L189:
        r214 = r17;
        r424 = 0;
        goto L190
    L152:
        if (r210 == 14) goto L181;
        if (r210 != 4) goto L160;
        int r335 = this.nextIcon;
        if (r335 == 14) goto L158;
        if (r335 != 3) goto L160;
    L158:
        r211 = false;
        r123 = 15;
    L160:
        if (r210 != 10) goto L162;
    L166:
        int r260 = this.nextIcon;
        if (r260 != 4) goto L169;
    L172:
        int r261 = (int) ((1.0f - this.transitionProgress) * 255.0f);
    L173:
        if (r261 != 0) goto L174;
    L165:
        r123 = 15;
        goto L279
    L174:
        applyShaderMatrix(false);
        this.paint.setAlpha((int) (r261 * this.overrideAlpha));
        if (this.drawProgressCircle == false) goto L165;
        float r262 = Math.max(4.0f, this.animatedDownloadProgress * 360.0f);
        if (this.isMini == false) goto L179;
        float r619 = 2.0f;
    L180:
        int r336 = AndroidUtilities.dp(r619);
        this.rect.set(r7.left + r336, r7.top + r336, r7.right - r336, r7.bottom - r336);
        r123 = 15;
        r1.drawArc(this.rect, this.downloadRadOffset, r262, false, this.paint);
        goto L279
    L179:
        r619 = 4.0f;
        goto L180
    L169:
        if (r260 == 6) goto L172;
        r261 = 255;
        goto L173
    L162:
        if (this.nextIcon == 10) goto L166;
        if (r210 != 13) goto L165;
    L106:
        r1 = r38;
        goto L149
    L68:
        if (this.nextIcon != 14) goto L70;
        float r263 = r17;
    L71:
        float r337 = (AndroidUtilities.dp(7.0f) * r28) * this.scale;
        int r425 = (int) (r28 * 255.0f);
        int r522 = this.nextIcon;
        if (r522 == 3) goto L77;
        if (r522 == 14) goto L77;
        if (r522 == 2) goto L77;
        float r620 = r33;
        r425 = (int) (r425 * (r620 - Math.min(r620, this.transitionProgress / 0.5f)));
    L77:
        int r914 = r425;
        if (r263 == r17) goto L80;
        r1.save();
        float r523 = r26;
        r1.rotate(r263, r63, r523);
    L81:
        if (r914 == 0) goto L90;
        this.paint.setAlpha(r914);
        if (this.nextIcon != 14) goto L89;
        this.paint3.setAlpha(r914);
        this.rect.set(r10 - AndroidUtilities.dp(3.5f), r11 - AndroidUtilities.dp(3.5f), AndroidUtilities.dp(3.5f) + r10, AndroidUtilities.dp(3.5f) + r11);
        r1.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.paint3);
        this.paint.setAlpha((int) (r914 * 0.15f));
        if (this.isMini == false) goto L87;
        float r621 = 2.0f;
    L88:
        int r264 = AndroidUtilities.dp(r621);
        this.rect.set(r7.left + r264, r7.top + r264, r7.right - r264, r7.bottom - r264);
        r1.drawArc(this.rect, 0.0f, 360.0f, false, this.paint);
        this.paint.setAlpha(r914);
        goto L90
    L87:
        r621 = 4.0f;
        goto L88
    L89:
        float r265 = r63 - r337;
        float r338 = r523 - r337;
        float r426 = r63 + r337;
        float r524 = r523 + r337;
        r38.drawLine(r265, r338, r426, r524, this.paint);
        r38.drawLine(r426, r338, r265, r524, this.paint);
    L90:
        if (r263 == r17) goto L92;
        r38.restore();
        goto L92
    L80:
        r523 = r26;
        goto L81
    L70:
        r263 = (-45.0f) * (r33 - r28);
        goto L71
    L64:
        r93 = r92 / 0.2f;
        r28 = r17;
        goto L65
    L60:
        r44 = 0;
    L93:
        if (r42 != 2) goto L95;
        float r525 = 1.0f - r9;
    L96:
        r3 = r3 + ((r142 - r3) * r525);
        r62 = r62 + ((r12 - r62) * r525);
        float r112 = r10;
        r27 = r112 - ((AndroidUtilities.dp(8.0f) * r9) * this.scale);
        r15 = r112 + ((AndroidUtilities.dp(8.0f) * r9) * this.scale);
        float r427 = AndroidUtilities.dp(8.0f) * r9;
        float r526 = this.scale;
    L99:
        r12 = r62 - (r427 * r526);
        goto L97
    L95:
        r525 = r9 / 0.5f;
        r9 = 1.0f - r525;
        goto L96
    L98:
        float r113 = r10;
        r27 = r113 - (AndroidUtilities.dp(8.0f) * this.scale);
        r15 = r113 + (AndroidUtilities.dp(8.0f) * this.scale);
        r427 = AndroidUtilities.dp(8.0f);
        r526 = this.scale;
    L48:
        this.paint.setAlpha(255);
        r4 = this.transitionProgress;
    L40:
        if (this.nextIcon != 2) goto L42;
        this.paint.setAlpha((int) (Math.min(1.0f, this.transitionProgress / 0.5f) * 255.0f));
        r9 = this.transitionProgress;
        r142 = (AndroidUtilities.dp(12.0f) * this.scale) + r26;
        r33 = 1.0f;
        goto L51
    L108:
        if (this.currentIcon != 14) goto L110;
    L113:
        boolean r339 = true;
    L114:
        int r266 = this.nextIcon;
        float r428 = this.transitionProgress;
        if (r266 != 2) goto L117;
        float r915 = 1.0f - r428;
    L118:
        float r267 = this.scale * this.downloadIconScale;
        float r125 = AndroidUtilities.dp(8.0f) * r267;
        float r137 = AndroidUtilities.dp(1.0f) * r267;
        float r146 = AndroidUtilities.dp(7.0f) * r267;
        float r153 = AndroidUtilities.dp(9.0f) * r267;
        float r284 = AndroidUtilities.dp(2.0f) * r267;
        float r268 = AndroidUtilities.dp(3.5f);
        if (r339 == true) goto L142;
        int r269 = this.currentIcon;
        if (r269 == 3) goto L125;
        if (this.nextIcon == 3) goto L125;
    L132:
        if (this.animatingTransition == false) goto L136;
        float r527 = r915;
    L139:
        float r528 = 1.0f - r527;
        int r270 = (int) (r528 * 255.0f);
        if (r270 <= 0) goto L149;
        r1.save();
        float r429 = r10;
        float r340 = r11;
        r1.scale(r528, r528, r429, r340);
        this.paint.setAlpha(r270);
        float r529 = r340 + r153;
        float r916 = r529 - r125;
        r1.drawLine(r429, r340 - r153, r429, r529, this.paint);
        r38.drawLine(r429 - r125, r916, r429, r529, this.paint);
        r1 = r38;
        r1.drawLine(r429 + r125, r916, r429, r529, this.paint);
        r1.restore();
        goto L149
    L136:
        if (r269 != 2) goto L138;
        r527 = r17;
        goto L139
    L138:
        r527 = 1.0f;
    L125:
        if (this.animatingTransition == false) goto L132;
        r1.save();
        float r341 = r10;
        float r430 = r11;
        r1.rotate(r915 * 90.0f, r341, r430);
        float r530 = 1.0f - r915;
        if (r530 <= r17) goto L129;
        this.paint.setAlpha((int) (r530 * 255.0f));
        float r531 = r530 * r153;
        float r292 = r430;
        r1.drawLine(r341, r430 - r531, r341, r531 + r430, this.paint);
        float r31 = r341;
    L130:
        this.paint.setAlpha(255);
        float r342 = (r292 + r137) - (r125 * r915);
        float r532 = (r292 + r153) - (r284 * r915);
        float r138 = r137 * r915;
        float r147 = r146 * r915;
        r38.drawLine((r31 - r125) + r138, r342, r31 + r147, r532, this.paint);
        r1 = r38;
        r1.drawLine((r31 + r125) - r138, r342, r31 - r147, r532, this.paint);
        r1.restore();
        goto L149
    L129:
        r31 = r341;
        r292 = r430;
        goto L130
    L142:
        float r533 = 1.0f - r915;
        if (r533 <= r17) goto L145;
        r1.save();
        float r431 = r10;
        float r343 = r11;
        r1.scale(r533, r533, r431, r343);
        this.paint.setAlpha((int) ((r533 * 255.0f) * this.overrideAlpha));
        float r534 = r343 + r153;
        float r139 = r534 - r125;
        float r148 = r268;
        r1.drawLine(r431, r343 - r153, r431, r534, this.paint);
        r38.drawLine(r431 - r125, r139, r431, r534, this.paint);
        r1 = r38;
        r1.drawLine(r431 + r125, r139, r431, r534, this.paint);
        r1.restore();
    L147:
        if (r915 <= r17) goto L149;
        r1.save();
        float r271 = r10;
        float r344 = r11;
        r1.scale(r915, r915, r271, r344);
        this.paint3.setAlpha((int) ((r915 * 255.0f) * this.overrideAlpha));
        this.rect.set(r271 - r148, r344 - r148, r271 + r148, r344 + r148);
        r1.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.paint3);
        r1.restore();
        goto L149
    L145:
        r148 = r268;
        goto L147
    L117:
        r915 = r428;
        goto L118
    L110:
        if (this.nextIcon == 14) goto L113;
        r339 = false;
        goto L114
    L33:
        if (this.nextIcon != 2) goto L149;
    L22:
        r17 = 0.0f;
    L29:
        r8 = 0;
        goto L30
    L23:
        r17 = 0.0f;
        if (r23 == 6) goto L27;
        if (r23 != 10) goto L29;
    L27:
        if (this.currentIcon != 4) goto L29;
        r25 = r1.save();
        float r622 = this.transitionProgress;
        r1.scale(r622, r622, r10, r11);
    L10:
        if (this.gradientDrawable != null) goto L12;
    L14:
        this.paint.setShader(null);
        this.paint2.setShader(null);
        this.paint3.setShader(null);
        goto L15
    L12:
        if (this.hasOverlayImage == true) goto L14;
        this.gradientMatrix.reset();
        this.gradientMatrix.setTranslate(0.0f, r7.top);
        this.gradientDrawable.setLocalMatrix(this.gradientMatrix);
        this.paint.setShader(this.gradientDrawable);
        this.paint2.setShader(this.gradientDrawable);
        this.paint3.setShader(this.gradientDrawable);
        goto L15
    }

    public int getCurrentIcon() {
        return this.nextIcon;
    }

    @Keep
    public float getDownloadIconScale() {
        return this.downloadIconScale;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(48.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(48.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return AndroidUtilities.dp(48.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return AndroidUtilities.dp(48.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int getPreviousIcon() {
        return this.currentIcon;
    }

    public float getProgress() {
        return this.downloadProgress;
    }

    public float getTransitionProgress() {
        if (this.animatingTransition == true) goto L5;
        return 1.0f;
    L5:
        return this.transitionProgress;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        MediaActionDrawableDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    public void setBackColor(int r2) {
        this.backPaint.setColor(r2 | (-16777216));
    }

    public void setBackgroundDrawable(MessageDrawable r1) {
        this.messageDrawable = r1;
    }

    public void setBackgroundGradientDrawable(LinearGradient r1) {
        this.gradientDrawable = r1;
        this.gradientMatrix = new Matrix();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r1, int r2, int r3, int r4) {
        super.setBounds(r1, r2, r3, r4);
        float r12 = (r3 - r1) / getIntrinsicWidth();
        this.scale = r12;
        if (r12 >= 0.7f) goto L6;
        this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        return;
    }

    public void setColor(int r3) {
        int r1 = (-16777216) | r3;
        this.paint.setColor(r1);
        this.paint2.setColor(r1);
        this.paint3.setColor(r1);
        this.textPaint.setColor(r1);
        this.colorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.colorFilter = r2;
        this.paint.setColorFilter(r2);
        this.paint2.setColorFilter(r2);
        this.paint3.setColorFilter(r2);
        this.textPaint.setColorFilter(r2);
    }

    public void setDelegate(MediaActionDrawableDelegate r1) {
        this.delegate = r1;
    }

    public void setDownloadIconScale(float r1) {
        this.downloadIconScale = r1;
    }

    public void setHasOverlayImage(boolean r1) {
        this.hasOverlayImage = r1;
    }

    public boolean setIcon(int r8, boolean r9) {
        if (this.currentIcon != r8) goto L7;
        int r0 = this.nextIcon;
        if (r0 == r8) goto L7;
        this.currentIcon = r0;
        this.transitionProgress = 1.0f;
    L7:
        int r02 = this.currentIcon;
        if (r9 == false) goto L43;
        if (r02 == r8) goto L42;
        int r92 = this.nextIcon;
        if (r92 == r8) goto L42;
        if (r02 != 0) goto L15;
        if (r8 != 1) goto L15;
    L17:
        this.transitionAnimationTime = 300.0f;
    L39:
        if (this.animatingTransition == false) goto L41;
        this.currentIcon = r92;
    L41:
        this.animatingTransition = true;
        this.nextIcon = r8;
        this.savedTransitionProgress = this.transitionProgress;
        this.transitionProgress = 0.0f;
    L46:
        if (r8 == 3) goto L48;
        if (r8 == 14) goto L48;
    L49:
        invalidateSelf();
        return true;
    L48:
        this.downloadRadOffset = 112.0f;
        this.animatedDownloadProgress = 0.0f;
        this.downloadProgressAnimationStart = 0.0f;
        this.downloadProgressTime = 0.0f;
    L15:
        if (r02 != 1) goto L19;
        if (r8 == 0) goto L17;
    L19:
        if (r02 != 2) goto L28;
        if (r8 == 3) goto L23;
        if (r8 != 14) goto L28;
    L23:
        if (this.drawProgressCircle == false) goto L25;
        float r03 = 400.0f;
    L26:
        this.transitionAnimationTime = r03;
        goto L39
    L25:
        r03 = 250.0f;
    L28:
        if (r02 != 4) goto L30;
    L32:
        if (r02 != 4) goto L34;
        if (r8 != 14) goto L34;
    L36:
        this.transitionAnimationTime = 160.0f;
    L34:
        if (r02 != 14) goto L37;
        if (r8 == 4) goto L36;
    L37:
        this.transitionAnimationTime = 220.0f;
        goto L39
    L30:
        if (r8 != 6) goto L32;
        this.transitionAnimationTime = 360.0f;
    L42:
        return false;
    L43:
        if (r02 != r8) goto L45;
        return false;
    L45:
        this.animatingTransition = false;
        this.nextIcon = r8;
        this.currentIcon = r8;
        this.savedTransitionProgress = this.transitionProgress;
        this.transitionProgress = 1.0f;
        goto L46
    }

    public void setMini(boolean r1) {
        this.isMini = r1;
        Paint r0 = this.paint;
        if (r1 == false) goto L5;
        float r12 = 2.0f;
    L6:
        r0.setStrokeWidth(AndroidUtilities.dp(r12));
        return;
    L5:
        r12 = 3.0f;
        goto L6
    }

    public void setOverrideAlpha(float r1) {
        this.overrideAlpha = r1;
    }

    public void setProgress(float r2, boolean r3) {
        if (this.downloadProgress != r2) goto L5;
        return;
    L5:
        if (r3 == true) goto L8;
        this.animatedDownloadProgress = r2;
        this.downloadProgressAnimationStart = r2;
    L11:
        this.downloadProgress = r2;
        this.downloadProgressTime = 0.0f;
        invalidateSelf();
        return;
    L8:
        if (this.animatedDownloadProgress <= r2) goto L10;
        this.animatedDownloadProgress = r2;
    L10:
        this.downloadProgressAnimationStart = this.animatedDownloadProgress;
        goto L11
    }
}
