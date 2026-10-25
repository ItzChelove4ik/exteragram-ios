package org.telegram.ui.ActionBar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.MediaActionDrawable;

/* loaded from: classes3.dex */
public class MenuDrawable extends Drawable {
    public static int TYPE_DEFAULT = 0;
    public static int TYPE_UDPATE_AVAILABLE = 1;
    public static int TYPE_UDPATE_DOWNLOADING = 2;
    private int alpha;
    private float animatedDownloadProgress;
    private int backColor;
    private final Paint backPaint;
    private int currentAnimationTime;
    private float currentRotation;
    private float downloadProgress;
    private float downloadProgressAnimationStart;
    private float downloadProgressTime;
    private float downloadRadOffset;
    private float finalRotation;
    private int iconColor;
    private DecelerateInterpolator interpolator;
    private long lastFrameTime;
    private boolean miniIcon;
    private final Paint paint;
    private int previousType;
    private RectF rect;
    private boolean reverseAngle;
    private boolean rotateToBack;
    private boolean roundCap;
    private int type;
    private float typeAnimationProgress;

    static {
    }

    public MenuDrawable() {
        this(TYPE_DEFAULT);
    }

    public MenuDrawable(int r5) {
        Paint r0 = new Paint(1);
        this.paint = r0;
        Paint r2 = new Paint(1);
        this.backPaint = r2;
        this.rotateToBack = true;
        this.interpolator = new DecelerateInterpolator();
        this.rect = new RectF();
        this.alpha = 255;
        r0.setStrokeWidth(AndroidUtilities.dp(2.0f));
        Paint.Cap r1 = Paint.Cap.ROUND;
        r0.setStrokeCap(r1);
        r2.setStrokeWidth(AndroidUtilities.density * 1.66f);
        r2.setStrokeCap(r1);
        r2.setStyle(Paint.Style.STROKE);
        this.previousType = TYPE_DEFAULT;
        this.type = r5;
        this.typeAnimationProgress = 1.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r24) {
        long r2 = SystemClock.elapsedRealtime();
        long r4 = this.lastFrameTime;
        long r7 = r2 - r4;
        float r6 = this.currentRotation;
        float r9 = this.finalRotation;
        if (r6 != r9) goto L5;
    L14:
        float r42 = this.typeAnimationProgress;
        if (r42 >= 1.0f) goto L20;
        float r43 = r42 + (r7 / 200.0f);
        this.typeAnimationProgress = r43;
        if (r43 <= 1.0f) goto L19;
        this.typeAnimationProgress = 1.0f;
    L19:
        invalidateSelf();
    L20:
        this.lastFrameTime = r2;
        r24.save();
        r24.translate(((getIntrinsicWidth() / 2) - AndroidUtilities.dp(9.0f)) - (AndroidUtilities.dp(1.0f) * this.currentRotation), getIntrinsicHeight() / 2);
        int r22 = this.iconColor;
        if (r22 != 0) goto L23;
        r22 = Theme.getColor(Theme.key_actionBarDefaultIcon);
    L23:
        int r3 = this.backColor;
        if (r3 != 0) goto L26;
        r3 = Theme.getColor(Theme.key_actionBarDefault);
    L26:
        int r10 = r3;
        int r32 = this.type;
        int r44 = TYPE_DEFAULT;
        int r5 = this.previousType;
        if (r32 != r44) goto L32;
        if (r5 == r44) goto L31;
        float r33 = AndroidUtilities.dp(9.0f) * (1.0f - this.typeAnimationProgress);
        float r45 = AndroidUtilities.dp(7.0f);
        float r52 = this.typeAnimationProgress;
    L30:
        float r46 = r45 * (1.0f - r52);
        float r15 = r33;
    L35:
        boolean r34 = this.rotateToBack;
        float r53 = this.currentRotation;
        if (r34 == false) goto L54;
        if (this.reverseAngle == false) goto L40;
        int r35 = -180;
    L41:
        r24.rotate(r53 * r35, AndroidUtilities.dp(9.0f), 0.0f);
        this.paint.setColor(r22);
        this.paint.setAlpha(this.alpha);
        if (this.roundCap == false) goto L44;
        float r23 = (AndroidUtilities.dp(0.5f) * this.currentRotation) + ((this.paint.getStrokeWidth() / 2.0f) * (1.0f - this.currentRotation));
    L45:
        float r36 = (AndroidUtilities.dp(18.0f) - (AndroidUtilities.dp(3.0f) * this.currentRotation)) - r46;
        if (this.roundCap == false) goto L48;
        float r47 = (this.paint.getStrokeWidth() / 2.0f) * (1.0f - this.currentRotation);
    L49:
        r24.drawLine(r23, 0.0f, r36 - r47, 0.0f, this.paint);
        float r25 = (AndroidUtilities.dp(5.0f) * (1.0f - Math.abs(this.currentRotation))) - (AndroidUtilities.dp(0.5f) * Math.abs(this.currentRotation));
        float r37 = AndroidUtilities.dp(18.0f) - (AndroidUtilities.dp(2.5f) * Math.abs(this.currentRotation));
        float r48 = AndroidUtilities.dp(5.0f) + (AndroidUtilities.dp(2.0f) * Math.abs(this.currentRotation));
        float r54 = AndroidUtilities.dp(7.5f) * Math.abs(this.currentRotation);
        if (this.roundCap == false) goto L52;
        r54 = r54 + ((this.paint.getStrokeWidth() / 2.0f) * (1.0f - this.currentRotation));
        float r26 = r25 + (AndroidUtilities.dp(0.5f) * this.currentRotation);
        r37 = r37 - ((AndroidUtilities.dp(0.5f) * this.currentRotation) + ((this.paint.getStrokeWidth() / 2.0f) * (1.0f - this.currentRotation)));
        r48 = r48 - (AndroidUtilities.dp(0.25f) * this.currentRotation);
        r25 = r26 + (AndroidUtilities.dp(0.25f) * this.currentRotation);
    L52:
        float r92 = r25;
        float r27 = r54;
        int r13 = r10;
        float r102 = r48;
        float r49 = r37;
        boolean r1 = this.miniIcon;
        Paint r62 = this.paint;
        if (r1 == false) goto L64;
        Canvas r12 = r24;
        r12.drawLine(r27, -r102, r49, -r92, r62);
        r12.drawLine(r27, r102, r49, r92, this.paint);
    L65:
        int r28 = this.type;
        int r38 = TYPE_DEFAULT;
        if (r28 == r38) goto L70;
        if (this.currentRotation == 1.0f) goto L70;
    L73:
        float r29 = AndroidUtilities.dp(17.0f);
        float r55 = -AndroidUtilities.dp(4.5f);
        float r39 = AndroidUtilities.density * 5.5f;
        float r410 = this.currentRotation;
        r12.scale(1.0f - r410, 1.0f - r410, r29, r55);
        if (this.type != TYPE_DEFAULT) goto L76;
        r39 = r39 * (1.0f - this.typeAnimationProgress);
    L76:
        this.backPaint.setColor(r13);
        this.backPaint.setAlpha(this.alpha);
        r12.drawCircle(r29, r55, r39, this.paint);
        int r310 = this.type;
        int r411 = TYPE_UDPATE_AVAILABLE;
        if (r310 != r411) goto L79;
    L80:
        this.backPaint.setStrokeWidth(AndroidUtilities.density * 1.66f);
        int r311 = this.previousType;
        int r412 = TYPE_UDPATE_AVAILABLE;
        Paint r63 = this.backPaint;
        if (r311 != r412) goto L83;
        r63.setAlpha((int) (this.alpha * (1.0f - this.typeAnimationProgress)));
    L84:
        r12.drawLine(r29, r55 - AndroidUtilities.dp(2.0f), r29, r55, this.backPaint);
        r12.drawPoint(r29, AndroidUtilities.dp(2.5f) + r55, this.backPaint);
    L85:
        int r312 = this.type;
        int r413 = TYPE_UDPATE_DOWNLOADING;
        if (r312 != r413) goto L88;
    L89:
        this.backPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        int r313 = this.previousType;
        int r414 = TYPE_UDPATE_DOWNLOADING;
        Paint r64 = this.backPaint;
        if (r313 != r414) goto L92;
        r64.setAlpha((int) (this.alpha * (1.0f - this.typeAnimationProgress)));
    L93:
        float r415 = Math.max(4.0f, this.animatedDownloadProgress * 360.0f);
        this.rect.set(r29 - AndroidUtilities.dp(3.0f), r55 - AndroidUtilities.dp(3.0f), r29 + AndroidUtilities.dp(3.0f), r55 + AndroidUtilities.dp(3.0f));
        r12.drawArc(this.rect, this.downloadRadOffset, r415, false, this.backPaint);
        float r14 = this.downloadRadOffset + ((360 * r7) / 2500.0f);
        this.downloadRadOffset = r14;
        this.downloadRadOffset = MediaActionDrawable.getCircleValue(r14);
        float r16 = this.downloadProgress;
        float r210 = this.downloadProgressAnimationStart;
        float r314 = r16 - r210;
        if (r314 <= 0.0f) goto L99;
        float r416 = this.downloadProgressTime + r7;
        this.downloadProgressTime = r416;
        if (r416 < 200.0f) goto L98;
        this.animatedDownloadProgress = r16;
        this.downloadProgressAnimationStart = r16;
        this.downloadProgressTime = 0.0f;
        goto L99
    L98:
        this.animatedDownloadProgress = r210 + (r314 * this.interpolator.getInterpolation(r416 / 200.0f));
    L99:
        invalidateSelf();
    L100:
        r24.restore();
        return;
    L92:
        r64.setAlpha(this.alpha);
        goto L93
    L88:
        if (this.previousType != r413) goto L100;
    L83:
        r63.setAlpha(this.alpha);
        goto L84
    L79:
        if (this.previousType != r411) goto L85;
    L70:
        if (this.previousType == r38) goto L100;
        if (this.typeAnimationProgress == 1.0f) goto L100;
    L64:
        r12 = r24;
        r12.drawLine(r27, -r102, r49 - r15, -r92, r62);
        r12.drawLine(r27, r102, r49, r92, this.paint);
        goto L65
    L48:
        r47 = 0.0f;
        goto L49
    L44:
        r23 = 0.0f;
        goto L45
    L40:
        r35 = 180;
        goto L41
    L54:
        if (this.reverseAngle == false) goto L56;
        int r315 = -225;
    L57:
        r24.rotate(r53 * r315, AndroidUtilities.dp(9.0f), 0.0f);
        if (this.miniIcon == false) goto L60;
        this.paint.setColor(r22);
        this.paint.setAlpha(this.alpha);
        r24.drawLine((AndroidUtilities.dpf2(2.0f) * (1.0f - Math.abs(this.currentRotation))) + (AndroidUtilities.dp(1.0f) * this.currentRotation), 0.0f, ((AndroidUtilities.dpf2(16.0f) * (1.0f - this.currentRotation)) + (AndroidUtilities.dp(17.0f) * this.currentRotation)) - r46, 0.0f, this.paint);
        r25 = (AndroidUtilities.dpf2(5.0f) * (1.0f - Math.abs(this.currentRotation))) - (AndroidUtilities.dpf2(0.5f) * Math.abs(this.currentRotation));
        r37 = (AndroidUtilities.dpf2(9.0f) * Math.abs(this.currentRotation)) + (AndroidUtilities.dpf2(16.0f) * (1.0f - Math.abs(this.currentRotation)));
        r48 = (AndroidUtilities.dpf2(3.0f) * Math.abs(this.currentRotation)) + AndroidUtilities.dpf2(5.0f);
        r54 = (AndroidUtilities.dpf2(7.0f) * Math.abs(this.currentRotation)) + AndroidUtilities.dpf2(2.0f);
        goto L52
    L60:
        int r17 = Theme.getColor(Theme.key_actionBarActionModeDefaultIcon);
        r10 = AndroidUtilities.getOffsetColor(r10, Theme.getColor(Theme.key_actionBarActionModeDefault), this.currentRotation, 1.0f);
        this.paint.setColor(AndroidUtilities.getOffsetColor(r22, r17, this.currentRotation, 1.0f));
        this.paint.setAlpha(this.alpha);
        r24.drawLine(this.currentRotation * AndroidUtilities.dp(1.0f), 0.0f, (AndroidUtilities.dp(18.0f) - (AndroidUtilities.dp(1.0f) * this.currentRotation)) - r46, 0.0f, this.paint);
        r25 = (AndroidUtilities.dp(5.0f) * (1.0f - Math.abs(this.currentRotation))) - (AndroidUtilities.dp(0.5f) * Math.abs(this.currentRotation));
        r37 = AndroidUtilities.dp(18.0f) - (AndroidUtilities.dp(9.0f) * Math.abs(this.currentRotation));
        r48 = (AndroidUtilities.dp(3.0f) * Math.abs(this.currentRotation)) + AndroidUtilities.dp(5.0f);
        r54 = Math.abs(this.currentRotation) * AndroidUtilities.dp(9.0f);
        goto L52
    L56:
        r315 = 135;
        goto L57
    L31:
        r46 = 0.0f;
        r15 = 0.0f;
        goto L35
    L32:
        if (r5 != r44) goto L34;
        r33 = (AndroidUtilities.dp(9.0f) * this.typeAnimationProgress) * (1.0f - this.currentRotation);
        r45 = AndroidUtilities.dp(7.0f) * this.typeAnimationProgress;
        r52 = this.currentRotation;
        goto L30
    L34:
        r33 = AndroidUtilities.dp(9.0f) * (1.0f - this.currentRotation);
        r45 = AndroidUtilities.dp(7.0f);
        r52 = this.currentRotation;
        goto L30
    L5:
        if (r4 == 0) goto L13;
        int r417 = (int) (this.currentAnimationTime + r7);
        this.currentAnimationTime = r417;
        if (r417 < 200) goto L9;
        this.currentRotation = r9;
        goto L13
    L9:
        DecelerateInterpolator r65 = this.interpolator;
        if (r6 >= r9) goto L12;
        this.currentRotation = r65.getInterpolation(r417 / 200.0f) * this.finalRotation;
        goto L13
    L12:
        this.currentRotation = 1.0f - r65.getInterpolation(r417 / 200.0f);
    L13:
        invalidateSelf();
        goto L14
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (this.alpha == r2) goto L6;
        this.alpha = r2;
        this.paint.setAlpha(r2);
        this.backPaint.setAlpha(r2);
        invalidateSelf();
        return;
    }

    public void setBackColor(int r1) {
        this.backColor = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setIconColor(int r1) {
        this.iconColor = r1;
    }

    public void setMiniIcon(boolean r1) {
        this.miniIcon = r1;
    }

    public void setRotateToBack(boolean r1) {
        this.rotateToBack = r1;
    }

    public void setRotation(float r6, boolean r7) {
        this.lastFrameTime = 0;
        float r2 = this.currentRotation;
        if (r2 != 1.0f) goto L6;
        this.reverseAngle = true;
    L8:
        this.lastFrameTime = 0;
        if (r7 == true) goto L11;
        this.currentRotation = r6;
        this.finalRotation = r6;
    L16:
        invalidateSelf();
        return;
    L11:
        if (r2 >= r6) goto L13;
        this.currentAnimationTime = (int) (r2 * 200.0f);
    L14:
        this.lastFrameTime = SystemClock.elapsedRealtime();
        this.finalRotation = r6;
        goto L16
    L13:
        this.currentAnimationTime = (int) ((1.0f - r2) * 200.0f);
        goto L14
    L6:
        if (r2 != 0.0f) goto L8;
        this.reverseAngle = false;
        goto L8
    }

    public void setRoundCap() {
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.roundCap = true;
    }
}
