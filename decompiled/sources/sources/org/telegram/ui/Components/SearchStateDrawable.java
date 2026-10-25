package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.google.zxing.common.detector.MathUtils;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class SearchStateDrawable extends Drawable {
    private int alpha;
    private float cx;
    private float cy;
    private Runnable delaySetProgress;
    private int fromState;
    private float mn;
    private Paint paint;
    private Path path;
    private AnimatedFloat progress;
    private float progressAngleFrom;
    private float progressAngleTo;
    private final float progressRadius;
    private RectF progressRect;
    private float[] progressSegments;
    private long progressStart;
    private boolean progressStartedWithOverTo;
    private int toState;
    private boolean waitingForProgressToEnd;
    private boolean wereNotWaitingForProgressToEnd;

    /* renamed from: $r8$lambda$xpAsMriBpkBOX9zap5-0aCfgm94, reason: not valid java name */
    public static /* synthetic */ void m12565$r8$lambda$xpAsMriBpkBOX9zap50aCfgm94(SearchStateDrawable r0, int r1, boolean r2) {
        r0.lambda$setIconState$0(r1, r2);
    }

    public SearchStateDrawable() {
        this.alpha = 255;
        this.path = new Path();
        this.progressRect = new RectF();
        this.progressRadius = 0.25f;
        this.progressStart = -1;
        this.progressAngleFrom = 0.0f;
        this.progressAngleTo = 0.0f;
        this.progressSegments = new float[2];
        this.toState = 0;
        this.waitingForProgressToEnd = false;
        this.progress = new AnimatedFloat(1.0f, new SearchStateDrawable$$ExternalSyntheticLambda0(this), 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        Paint r0 = new Paint(1);
        this.paint = r0;
        r0.setColor(-1);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(AndroidUtilities.dp(1.333f));
    }

    private boolean containsAngle(float r3, float r4, float r5) {
        float r42 = r4 % 360.0f;
        if (r42 >= 0.0f) goto L5;
        r42 = r42 + 360.0f;
    L5:
        float r52 = r5 % 360.0f;
        if (r52 >= 0.0f) goto L9;
        r52 = r52 + 360.0f;
    L9:
        if (r42 <= r52) goto L18;
        if (r3 < r42) goto L13;
    L16:
        return true;
    L13:
        if (r3 <= r52) goto L16;
        return false;
    L18:
        if (r3 >= r42) goto L20;
    L22:
        return false;
    L20:
        if (r3 > r52) goto L22;
        return true;
    }

    private void drawCircle(Canvas r2, float r3, float r4, float r5) {
        if (r5 >= w(0.075f)) goto L5;
        return;
    L5:
        r2.drawCircle(r3, r4, r5, this.paint);
    }

    private void drawLine(Canvas r4, float r5, float r6, float r7, float r8) {
        if (MathUtils.distance(r5, r6, r7, r8) > w(0.075f)) goto L5;
        return;
    L5:
        r4.drawLine(r5, r6, r7, r8, this.paint);
    }

    private void drawLines(Canvas r3, float r4, float r5, float r6, float r7, float r8, float r9) {
        if (Math.max(MathUtils.distance(r4, r5, r6, r7), MathUtils.distance(r8, r9, r6, r7)) > w(0.075f)) goto L5;
        return;
    L5:
        this.path.rewind();
        this.path.moveTo(r4, r5);
        this.path.lineTo(r6, r7);
        this.path.lineTo(r8, r9);
        r3.drawPath(this.path, this.paint);
    }

    private /* synthetic */ void lambda$setIconState$0(int r2, boolean r3) {
        this.delaySetProgress = null;
        setIconState(r2, r3, true);
    }

    private float lerp3(float r1, float r2, float r3, float r4, float r5, float r6) {
        return ((r1 * r4) + (r2 * r5)) + (r3 * r6);
    }

    private void setIconState(int r6, boolean r7, boolean r8) {
        if (getIconState() != r6) goto L7;
        if (r6 == 2) goto L38;
        AndroidUtilities.cancelRunOnUIThread(this.delaySetProgress);
        this.delaySetProgress = null;
        return;
    L38:
        return;
    L7:
        if (r8 == true) goto L13;
        if (r6 != 2) goto L13;
        if (this.delaySetProgress != null) goto L39;
        SearchStateDrawable$$ExternalSyntheticLambda1 r82 = new SearchStateDrawable$$ExternalSyntheticLambda1(this, r6, r7);
        this.delaySetProgress = r82;
        AndroidUtilities.runOnUIThread(r82, 65);
        return;
    L39:
        return;
    L13:
        Runnable r83 = this.delaySetProgress;
        if (r83 == null) goto L16;
        AndroidUtilities.cancelRunOnUIThread(r83);
    L16:
        boolean r2 = false;
        if (this.progress.get() >= 1.0f) goto L21;
        if (r7 == false) goto L21;
        setIconState(this.toState, false);
    L21:
        if (r6 != 2) goto L24;
        this.progressAngleFrom = 180.0f;
        this.progressStart = -1;
    L29:
        if (r7 == false) goto L35;
        int r72 = this.toState;
        this.fromState = r72;
        this.toState = r6;
        if (r72 != 2) goto L34;
        if (r6 == 2) goto L34;
        r2 = true;
    L34:
        this.waitingForProgressToEnd = r2;
        this.progress.set(0.0f, true);
    L36:
        invalidateSelf();
        return;
    L35:
        this.toState = r6;
        this.fromState = r6;
        this.waitingForProgressToEnd = false;
        this.progress.set(1.0f, true);
        goto L36
    L24:
        if (this.toState != 2) goto L29;
        if (r6 != 0) goto L27;
        this.progressAngleTo = -45.0f;
        goto L29
    L27:
        this.progressAngleTo = 0.0f;
        goto L29
    }

    private float w(float r1) {
        return this.mn * r1;
    }

    private float x(float r3) {
        return this.cx - (this.mn * (0.5f - r3));
    }

    private float y(float r3) {
        return this.cy - (this.mn * (0.5f - r3));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r23) {
        Rect r1 = getBounds();
        this.mn = Math.min(r1.width(), r1.height());
        this.cx = r1.centerX();
        this.cy = r1.centerY();
        int r6 = this.alpha;
        if (r6 >= 255) goto L5;
        r23.saveLayerAlpha(r1.left, r1.top, r1.right, r1.bottom, r6, 31);
        Canvas r7 = r23;
    L6:
        AnimatedFloat r12 = this.progress;
        if (this.waitingForProgressToEnd == false) goto L9;
        float r2 = 0.0f;
    L10:
        float r11 = r12.set(r2);
        int r13 = this.toState;
        int r22 = this.fromState;
        if (r13 != 0) goto L15;
        if (r22 != 0) goto L14;
        float r4 = 1.0f;
    L18:
        int r24 = this.fromState;
        if (r13 != 1) goto L23;
        if (r24 != 1) goto L22;
        float r5 = 1.0f;
    L26:
        int r25 = this.fromState;
        if (r13 != 2) goto L31;
        if (r25 != 2) goto L30;
        float r62 = 1.0f;
    L35:
        if (r4 <= 0.0f) goto L37;
        float r16 = 1.0f;
        drawCircle(r7, AndroidUtilities.lerp(x(0.25f), x(0.444f), r4), AndroidUtilities.lerp(y(0.5f), y(0.444f), r4), AndroidUtilities.lerp(0.0f, w(0.208f), r4));
    L39:
        if (r4 <= 0.0f) goto L41;
    L44:
        r7.save();
        float r17 = 45.0f;
        char r18 = 1;
        r7.rotate(r4 * 45.0f, this.cx, this.cy);
        float r14 = x(0.914f);
        float r9 = x(0.7638f);
        if (this.fromState != 2) goto L48;
        float r122 = x(0.75f);
    L47:
        float r92 = 0.2409f;
        float r21 = r122;
        float r123 = 0.75f;
        float r19 = lerp3(r14, r9, r21, r4, r5, r62);
        float r20 = y(0.5f);
        float r15 = x(0.658f);
        float r26 = x(0.2409f);
        if (this.fromState != 2) goto L52;
        float r3 = x(0.75f);
    L53:
        float r110 = lerp3(r15, r26, r3, r4, r5, r62);
        float r27 = r62;
        float r63 = r4;
        float r8 = r27;
        Canvas r111 = r7;
        float r72 = r5;
        drawLine(r111, r19, r20, r110, y(0.5f));
        r111.restore();
    L55:
        if (r72 > 0.0f) goto L57;
        SearchStateDrawable r64 = this;
    L64:
        if (r8 <= 0.0f) goto L93;
        if (r64.progressStart >= 0) goto L71;
        if (r8 <= 0.8f) goto L71;
        r64.progressStart = System.currentTimeMillis();
        r64.wereNotWaitingForProgressToEnd = r64.waitingForProgressToEnd;
    L71:
        if (r64.progressStart <= 0) goto L93;
        CircularProgressDrawable.getSegments((System.currentTimeMillis() - r64.progressStart) % 5400.0f, r64.progressSegments);
        float[] r0 = r64.progressSegments;
        float r28 = r0[0];
        float r02 = r0[r18];
        if (r64.getIconState() != 2) goto L75;
    L77:
        float r32 = r64.progressAngleTo;
        float r42 = r64.progressAngleFrom;
        boolean r33 = r64.containsAngle(r32, r42 + r28, r42 + r02);
        boolean r43 = r64.waitingForProgressToEnd;
        if (r43 == false) goto L83;
        if (r64.wereNotWaitingForProgressToEnd == true) goto L83;
        r64.wereNotWaitingForProgressToEnd = r43;
        r64.progressStartedWithOverTo = r33;
    L83:
        if (r64.progressStartedWithOverTo == false) goto L86;
        if (r33 == true) goto L86;
        r64.progressStartedWithOverTo = false;
    L86:
        if (r43 == false) goto L91;
        if (r33 == false) goto L91;
        if (r64.progressStartedWithOverTo == true) goto L91;
        r64.waitingForProgressToEnd = false;
    L91:
        r64.progressRect.set(r64.x(0.25f), r64.y(0.25f), r64.x(r123), r64.y(r123));
        r23.drawArc(r64.progressRect, r64.progressAngleFrom + r28, r02 - r28, false, r64.paint);
        r64.invalidateSelf();
        goto L93
    L75:
        if (r64.waitingForProgressToEnd == true) goto L77;
        float r34 = Math.max(0.0f, (((float) Math.floor((r28 - 180.0f) / 360.0f)) * 360.0f) + 180.0f);
        r02 = Math.min(r02, r64.progressAngleTo + r34);
        r28 = AndroidUtilities.lerp(r02, Math.min(r28, r34 + r64.progressAngleTo), r8);
    L93:
        if (r64.alpha >= 255) goto L96;
        r23.restore();
    L96:
        if (r11 >= r16) goto L99;
        r64.invalidateSelf();
        return;
    L99:
        return;
    L57:
        if (this.fromState != 2) goto L60;
        float r29 = AndroidUtilities.lerp(x(r123), x(r92), r72);
    L59:
        float r44 = r29;
        r111.save();
        r111.rotate(r63 * r17, this.cx, this.cy);
        drawLines(r111, (x(0.2452f) * r72) + r44, AndroidUtilities.lerp(y(0.5f), y(0.25f), r72), r44, y(0.5f), (x(0.2452f) * r72) + r44, AndroidUtilities.lerp(y(0.5f), y(r123), r72));
        r64 = this;
        r23.restore();
        goto L64
    L60:
        r29 = x(r92);
        goto L59
    L52:
        r3 = x(0.2409f);
        goto L53
    L48:
        r122 = x(0.2409f);
        goto L47
    L41:
        if (r5 > 0.0f) goto L44;
        r8 = r62;
        r111 = r7;
        r17 = 45.0f;
        r18 = 1;
        r92 = 0.2409f;
        r123 = 0.75f;
        r63 = r4;
        r72 = r5;
        goto L55
    L37:
        r16 = 1.0f;
        goto L39
    L30:
        r62 = r11;
        goto L35
    L31:
        if (r25 != 2) goto L33;
        r62 = 1.0f - r11;
        goto L35
    L33:
        r62 = 0.0f;
        goto L35
    L22:
        r5 = r11;
        goto L26
    L23:
        if (r24 != 1) goto L25;
        r5 = 1.0f - r11;
        goto L26
    L25:
        r5 = 0.0f;
        goto L26
    L14:
        r4 = r11;
        goto L18
    L15:
        if (r22 != 0) goto L17;
        r4 = 1.0f - r11;
        goto L18
    L17:
        r4 = 0.0f;
        goto L18
    L9:
        r2 = 1.0f;
        goto L10
    L5:
        r7 = r23;
        goto L6
    }

    public int getIconState() {
        return this.toState;
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
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    public void setColor(int r2) {
        this.paint.setColor(r2);
        this.alpha = this.paint.getAlpha();
        this.paint.setAlpha(255);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }

    public void setIconState(int r2) {
        setIconState(r2, true);
    }

    public void setIconState(int r2, boolean r3) {
        setIconState(r2, r3, false);
    }
}
