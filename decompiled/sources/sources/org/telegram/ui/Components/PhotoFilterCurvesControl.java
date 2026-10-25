package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.PhotoFilterView;

/* loaded from: classes7.dex */
public class PhotoFilterCurvesControl extends View {
    private int activeSegment;
    private RectOld actualArea;
    private boolean checkForMoving;
    private PhotoFilterView.CurvesToolValue curveValue;
    private PhotoFilterCurvesControlDelegate delegate;
    private boolean isMoving;
    private float lastX;
    private float lastY;
    private Paint paint;
    private Paint paintCurve;
    private Paint paintDash;
    private Path path;
    private TextPaint textPaint;

    public interface PhotoFilterCurvesControlDelegate {
        void valueChanged();
    }

    public PhotoFilterCurvesControl(Context r3, PhotoFilterView.CurvesToolValue r4) {
        super(r3);
        this.activeSegment = 0;
        this.checkForMoving = true;
        this.actualArea = new RectOld();
        this.paint = new Paint(1);
        this.paintDash = new Paint(1);
        this.paintCurve = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.path = new Path();
        setWillNotDraw(false);
        this.curveValue = r4;
        this.paint.setColor(-1711276033);
        this.paint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        Paint r32 = this.paint;
        Paint.Style r0 = Paint.Style.STROKE;
        r32.setStyle(r0);
        this.paintDash.setColor(-1711276033);
        this.paintDash.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.paintDash.setStyle(r0);
        this.paintCurve.setColor(-1);
        this.paintCurve.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.paintCurve.setStyle(r0);
        this.textPaint.setColor(-4210753);
        this.textPaint.setTextSize(AndroidUtilities.dp(13.0f));
    }

    private void handlePan(int r11, MotionEvent r12) {
        float r0 = r12.getX();
        float r122 = r12.getY();
        if (r11 != 1) goto L5;
        selectSegmentWithPoint(r0);
        return;
    L5:
        if (r11 == 2) goto L12;
        if (r11 == 3) goto L10;
        if (r11 == 4) goto L10;
        if (r11 == 5) goto L10;
        return;
    L10:
        unselectSegments();
        return;
    L12:
        float r112 = Math.min(2.0f, (this.lastY - r122) / 8.0f);
        PhotoFilterView.CurvesToolValue r6 = this.curveValue;
        int r7 = r6.activeType;
        if (r7 == 0) goto L21;
        if (r7 == 1) goto L20;
        if (r7 == 2) goto L19;
        if (r7 == 3) goto L18;
        PhotoFilterView.CurvesValue r62 = null;
    L22:
        int r72 = this.activeSegment;
        if (r72 == 1) goto L33;
        if (r72 == 2) goto L32;
        if (r72 == 3) goto L31;
        if (r72 == 4) goto L30;
        if (r72 != 5) goto L34;
        r62.whitesLevel = Math.max(0.0f, Math.min(100.0f, r62.whitesLevel + r112));
    L34:
        invalidate();
        PhotoFilterCurvesControlDelegate r113 = this.delegate;
        if (r113 == null) goto L37;
        r113.valueChanged();
    L37:
        this.lastX = r0;
        this.lastY = r122;
        return;
    L30:
        r62.highlightsLevel = Math.max(0.0f, Math.min(100.0f, r62.highlightsLevel + r112));
        goto L34
    L31:
        r62.midtonesLevel = Math.max(0.0f, Math.min(100.0f, r62.midtonesLevel + r112));
        goto L34
    L32:
        r62.shadowsLevel = Math.max(0.0f, Math.min(100.0f, r62.shadowsLevel + r112));
        goto L34
    L33:
        r62.blacksLevel = Math.max(0.0f, Math.min(100.0f, r62.blacksLevel + r112));
        goto L34
    L18:
        r62 = r6.blueCurve;
        goto L22
    L19:
        r62 = r6.greenCurve;
        goto L22
    L20:
        r62 = r6.redCurve;
        goto L22
    L21:
        r62 = r6.luminanceCurve;
        goto L22
    }

    private void selectSegmentWithPoint(float r4) {
        if (this.activeSegment == 0) goto L5;
        return;
    L5:
        float r1 = this.actualArea.width / 5.0f;
        this.activeSegment = (int) Math.floor(((r4 - r0.x) / r1) + 1.0f);
    }

    private void unselectSegments() {
        if (this.activeSegment != 0) goto L5;
        return;
    L5:
        this.activeSegment = 0;
    }

    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas r15) {
        float r6 = this.actualArea.width / 5.0f;
        int r7 = 0;
        int r8 = 0;
    L3:
        RectOld r0 = this.actualArea;
        if (r8 >= 4) goto L6;
        float r1 = r0.x;
        float r3 = r8 * r6;
        float r2 = r0.y;
        r15.drawLine((r1 + r6) + r3, r2, r3 + (r1 + r6), r2 + r0.height, this.paint);
        r8 = r8 + 1;
        goto L3
    L6:
        float r12 = r0.x;
        float r4 = r0.y;
        r15.drawLine(r12, r0.height + r4, r12 + r0.width, r4, this.paintDash);
        int r13 = this.curveValue.activeType;
        if (r13 == 0) goto L15;
        if (r13 == 1) goto L14;
        if (r13 == 2) goto L13;
        if (r13 == 3) goto L12;
        PhotoFilterView.CurvesValue r14 = null;
    L16:
        int r5 = 0;
    L18:
        if (r5 >= 5) goto L32;
        if (r5 == 0) goto L30;
        if (r5 == 1) goto L29;
        if (r5 == 2) goto L28;
        if (r5 == 3) goto L27;
        if (r5 == 4) goto L26;
        String r82 = _UrlKt.FRAGMENT_ENCODE_SET;
    L31:
        float r10 = this.textPaint.measureText(r82);
        RectOld r11 = this.actualArea;
        r15.drawText(r82, (r11.x + ((r6 - r10) / 2.0f)) + (r5 * r6), (r11.y + r11.height) - AndroidUtilities.dp(4.0f), this.textPaint);
        r5 = r5 + 1;
        goto L18
    L26:
        r82 = String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(r14.whitesLevel / 100.0f)});
        goto L31
    L27:
        r82 = String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(r14.highlightsLevel / 100.0f)});
        goto L31
    L28:
        r82 = String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(r14.midtonesLevel / 100.0f)});
        goto L31
    L29:
        r82 = String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(r14.shadowsLevel / 100.0f)});
        goto L31
    L30:
        r82 = String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(r14.blacksLevel / 100.0f)});
        goto L31
    L32:
        float[] r16 = r14.interpolateCurve();
        invalidate();
        this.path.reset();
    L34:
        if (r7 >= (r16.length / 2)) goto L40;
        Path r22 = this.path;
        if (r7 != 0) goto L38;
        RectOld r62 = this.actualArea;
        int r9 = r7 * 2;
        r22.moveTo(r62.x + (r16[r9] * r62.width), r62.y + ((1.0f - r16[r9 + 1]) * r62.height));
    L39:
        r7 = r7 + 1;
        goto L34
    L38:
        RectOld r63 = this.actualArea;
        int r92 = r7 * 2;
        r22.lineTo(r63.x + (r16[r92] * r63.width), r63.y + ((1.0f - r16[r92 + 1]) * r63.height));
        goto L39
    L40:
        r15.drawPath(this.path, this.paintCurve);
        return;
    L12:
        this.paintCurve.setColor(-13404165);
        r14 = this.curveValue.blueCurve;
        goto L16
    L13:
        this.paintCurve.setColor(-15667555);
        r14 = this.curveValue.greenCurve;
        goto L16
    L14:
        this.paintCurve.setColor(-1229492);
        r14 = this.curveValue.redCurve;
        goto L16
    L15:
        this.paintCurve.setColor(-1);
        r14 = this.curveValue.luminanceCurve;
        goto L16
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        int r0 = r8.getActionMasked();
        if (r0 == 0) goto L21;
        if (r0 == 1) goto L17;
        if (r0 == 2) goto L14;
        if (r0 == 3) goto L17;
        if (r0 == 5) goto L21;
        if (r0 == 6) goto L17;
    L41:
        return true;
    L14:
        if (this.isMoving == false) goto L41;
        handlePan(2, r8);
    L17:
        if (this.isMoving == false) goto L19;
        handlePan(3, r8);
        this.isMoving = false;
    L19:
        this.checkForMoving = true;
    L21:
        if (r8.getPointerCount() != 1) goto L39;
        if (this.checkForMoving == false) goto L41;
        if (this.isMoving == true) goto L41;
        float r02 = r8.getX();
        float r2 = r8.getY();
        this.lastX = r02;
        this.lastY = r2;
        RectOld r4 = this.actualArea;
        float r5 = r4.x;
        if (r02 >= r5) goto L29;
    L35:
        this.checkForMoving = false;
        if (this.isMoving == false) goto L41;
        handlePan(1, r8);
        goto L41
    L29:
        if (r02 > (r5 + r4.width)) goto L35;
        float r03 = r4.y;
        if (r2 < r03) goto L35;
        if (r2 > (r03 + r4.height)) goto L35;
        this.isMoving = true;
        goto L35
    L39:
        if (this.isMoving == false) goto L41;
        handlePan(3, r8);
        this.checkForMoving = true;
        this.isMoving = false;
        goto L41
    }

    public void setActualArea(float r1, float r2, float r3, float r4) {
        RectOld r0 = this.actualArea;
        r0.x = r1;
        r0.y = r2;
        r0.width = r3;
        r0.height = r4;
    }

    public void setDelegate(PhotoFilterCurvesControlDelegate r1) {
        this.delegate = r1;
    }
}
