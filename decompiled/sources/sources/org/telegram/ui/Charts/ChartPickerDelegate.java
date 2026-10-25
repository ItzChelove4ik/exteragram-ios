package org.telegram.ui.Charts;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.MotionEvent;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes6.dex */
public class ChartPickerDelegate {
    CapturesData[] capturedStates;
    public boolean disabled;
    public Rect leftPickerArea;
    public Rect middlePickerArea;
    public float minDistance;
    ValueAnimator moveToAnimator;
    public float moveToX;
    public float moveToY;
    public float pickerEnd;
    public float pickerStart;
    public float pickerWidth;
    public Rect rightPickerArea;
    public long startTapTime;
    public boolean tryMoveTo;
    Listener view;

    public class CapturesData {

        /* renamed from: a, reason: collision with root package name */
        ValueAnimator f505a;
        public float aValue;
        public int capturedX;
        public float end;
        ValueAnimator jumpToAnimator;
        public int lastMovingX;
        public float start;
        public final int state;
        final /* synthetic */ ChartPickerDelegate this$0;

        /* renamed from: $r8$lambda$IjDs-lL7VpKYJMU8TN70Aw3_WhM, reason: not valid java name */
        public static /* synthetic */ void m7373$r8$lambda$IjDslL7VpKYJMU8TN70Aw3_WhM(CapturesData r0, ValueAnimator r1) {
            r0.lambda$captured$0(r1);
        }

        public CapturesData(ChartPickerDelegate r1, int r2) {
            this.this$0 = r1;
            this.aValue = 0.0f;
            this.state = r2;
        }

        private /* synthetic */ void lambda$captured$0(ValueAnimator r1) {
            this.aValue = ((Float) r1.getAnimatedValue()).floatValue();
            this.this$0.view.invalidate();
        }

        public void captured() {
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.f505a = r0;
            r0.setDuration(600);
            this.f505a.setInterpolator(BaseChartView.INTERPOLATOR);
            this.f505a.addUpdateListener(new ChartPickerDelegate$CapturesData$$ExternalSyntheticLambda0(this));
            this.f505a.start();
        }

        public void uncapture() {
            ValueAnimator r0 = this.f505a;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            ValueAnimator r1 = this.jumpToAnimator;
            if (r1 == null) goto L9;
            r1.cancel();
            return;
        }
    }

    public interface Listener {
        void invalidate();

        void onPickerDataChanged();

        void onPickerJumpTo(float r1, float r2, boolean r3);
    }

    /* renamed from: $r8$lambda$UMItj2me-15pV1Ht8LETe_W99OY, reason: not valid java name */
    public static /* synthetic */ void m7372$r8$lambda$UMItj2me15pV1Ht8LETe_W99OY(ChartPickerDelegate r0, float r1, float r2, float r3, float r4, ValueAnimator r5) {
        r0.lambda$uncapture$0(r1, r2, r3, r4, r5);
    }

    public ChartPickerDelegate(Listener r2) {
        this.leftPickerArea = new Rect();
        this.rightPickerArea = new Rect();
        this.middlePickerArea = new Rect();
        this.pickerStart = 0.7f;
        this.pickerEnd = 1.0f;
        this.minDistance = 0.1f;
        this.capturedStates = new CapturesData[]{null, null};
        this.view = r2;
    }

    private /* synthetic */ void lambda$uncapture$0(float r2, float r3, float r4, float r5, ValueAnimator r6) {
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        this.pickerStart = r2 + ((r3 - r2) * r62);
        this.pickerEnd = r4 + ((r5 - r4) * r62);
        this.view.onPickerJumpTo(r3, r5, false);
    }

    public boolean capture(int r5, int r6, int r7) {
        if (this.disabled == false) goto L6;
        return false;
    L6:
        if (r7 == 0) goto L8;
        if (r7 != 1) goto L66;
        CapturesData r72 = this.capturedStates[0];
        if (r72 != null) goto L47;
        return false;
    L47:
        if (r72.state != 4) goto L50;
        return false;
    L50:
        if (this.leftPickerArea.contains(r5, r6) == false) goto L58;
        CapturesData[] r73 = this.capturedStates;
        if (r73[0].state == 1) goto L58;
        r73[1] = new CapturesData(this, 1);
        CapturesData r62 = this.capturedStates[1];
        r62.start = this.pickerStart;
        r62.capturedX = r5;
        r62.lastMovingX = r5;
        r62.captured();
        ValueAnimator r4 = this.moveToAnimator;
        if (r4 == null) goto L56;
        r4.cancel();
    L56:
        return true;
    L58:
        if (this.rightPickerArea.contains(r5, r6) == false) goto L66;
        CapturesData[] r63 = this.capturedStates;
        if (r63[0].state != 2) goto L62;
        return false;
    L62:
        r63[1] = new CapturesData(this, 2);
        CapturesData r64 = this.capturedStates[1];
        r64.end = this.pickerEnd;
        r64.capturedX = r5;
        r64.lastMovingX = r5;
        r64.captured();
        ValueAnimator r42 = this.moveToAnimator;
        if (r42 == null) goto L65;
        r42.cancel();
    L65:
        return true;
    L66:
        return false;
    L8:
        if (this.leftPickerArea.contains(r5, r6) == false) goto L17;
        CapturesData[] r65 = this.capturedStates;
        CapturesData r74 = r65[0];
        if (r74 == null) goto L12;
        r65[1] = r74;
    L12:
        r65[0] = new CapturesData(this, 1);
        CapturesData r66 = this.capturedStates[0];
        r66.start = this.pickerStart;
        r66.capturedX = r5;
        r66.lastMovingX = r5;
        r66.captured();
        ValueAnimator r43 = this.moveToAnimator;
        if (r43 == null) goto L15;
        r43.cancel();
    L15:
        return true;
    L17:
        if (this.rightPickerArea.contains(r5, r6) == false) goto L26;
        CapturesData[] r67 = this.capturedStates;
        CapturesData r75 = r67[0];
        if (r75 == null) goto L21;
        r67[1] = r75;
    L21:
        r67[0] = new CapturesData(this, 2);
        CapturesData r68 = this.capturedStates[0];
        r68.end = this.pickerEnd;
        r68.capturedX = r5;
        r68.lastMovingX = r5;
        r68.captured();
        ValueAnimator r44 = this.moveToAnimator;
        if (r44 == null) goto L24;
        r44.cancel();
    L24:
        return true;
    L26:
        if (this.middlePickerArea.contains(r5, r6) == false) goto L31;
        this.capturedStates[0] = new CapturesData(this, 4);
        CapturesData r69 = this.capturedStates[0];
        r69.end = this.pickerEnd;
        r69.start = this.pickerStart;
        r69.capturedX = r5;
        r69.lastMovingX = r5;
        r69.captured();
        ValueAnimator r45 = this.moveToAnimator;
        if (r45 == null) goto L30;
        r45.cancel();
    L30:
        return true;
    L31:
        Rect r76 = this.leftPickerArea;
        if (r6 >= r76.bottom) goto L66;
        if (r6 <= r76.top) goto L66;
        this.tryMoveTo = true;
        this.moveToX = r5;
        this.moveToY = r6;
        this.startTapTime = System.currentTimeMillis();
        ValueAnimator r52 = this.moveToAnimator;
        if (r52 != null) goto L38;
    L41:
        return true;
    L38:
        if (r52.isRunning() == false) goto L40;
        this.view.onPickerJumpTo(this.pickerStart, this.pickerEnd, true);
    L40:
        this.moveToAnimator.cancel();
        goto L41
    }

    public boolean captured() {
        if (this.capturedStates[0] == null) goto L5;
        return true;
    L5:
        if (this.tryMoveTo == true) goto L10;
        return false;
    L10:
        return true;
    }

    public CapturesData getLeftCaptured() {
        CapturesData[] r3 = this.capturedStates;
        CapturesData r0 = r3[0];
        if (r0 != null) goto L5;
    L7:
        CapturesData r32 = r3[1];
        if (r32 != null) goto L10;
        return null;
    L10:
        if (r32.state != 1) goto L14;
        return r32;
    L14:
        return null;
    L5:
        if (r0.state != 1) goto L7;
        return r0;
    }

    public CapturesData getMiddleCaptured() {
        CapturesData[] r3 = this.capturedStates;
        CapturesData r0 = r3[0];
        if (r0 != null) goto L5;
    L7:
        CapturesData r32 = r3[1];
        if (r32 != null) goto L10;
        return null;
    L10:
        if (r32.state != 4) goto L14;
        return r32;
    L14:
        return null;
    L5:
        if (r0.state != 4) goto L7;
        return r0;
    }

    public CapturesData getRightCaptured() {
        CapturesData[] r3 = this.capturedStates;
        CapturesData r0 = r3[0];
        if (r0 != null) goto L5;
    L7:
        CapturesData r32 = r3[1];
        if (r32 != null) goto L10;
        return null;
    L10:
        if (r32.state != 2) goto L14;
        return r32;
    L14:
        return null;
    L5:
        if (r0.state != 2) goto L7;
        return r0;
    }

    public boolean move(int r9, int r10, int r11) {
        boolean r0 = false;
        if (this.tryMoveTo == false) goto L5;
        return false;
    L5:
        CapturesData r102 = this.capturedStates[r11];
        if (r102 != null) goto L8;
        return false;
    L8:
        int r112 = r102.state;
        float r1 = r102.start;
        float r2 = r102.end;
        int r3 = r102.capturedX;
        r102.lastMovingX = r9;
        if (r112 != 1) goto L18;
        float r02 = r1 - ((r3 - r9) / this.pickerWidth);
        this.pickerStart = r02;
        if (r02 >= 0.0f) goto L13;
        this.pickerStart = 0.0f;
    L13:
        float r03 = this.pickerEnd;
        float r5 = r03 - this.pickerStart;
        float r6 = this.minDistance;
        if (r5 >= r6) goto L16;
        this.pickerStart = r03 - r6;
    L16:
        r0 = true;
    L18:
        if (r112 != 2) goto L27;
        float r04 = r2 - ((r3 - r9) / this.pickerWidth);
        this.pickerEnd = r04;
        if (r04 <= 1.0f) goto L22;
        this.pickerEnd = 1.0f;
    L22:
        float r05 = this.pickerEnd;
        float r52 = this.pickerStart;
        float r06 = r05 - r52;
        float r7 = this.minDistance;
        if (r06 >= r7) goto L25;
        this.pickerEnd = r52 + r7;
    L25:
        r0 = true;
    L27:
        if (r112 != 4) goto L35;
        float r92 = r3 - r9;
        float r113 = this.pickerWidth;
        float r07 = r1 - (r92 / r113);
        this.pickerStart = r07;
        this.pickerEnd = r2 - (r92 / r113);
        if (r07 >= 0.0f) goto L32;
        this.pickerStart = 0.0f;
        this.pickerEnd = r2 - r1;
    L32:
        if (this.pickerEnd <= 1.0f) goto L34;
        this.pickerEnd = 1.0f;
        this.pickerStart = 1.0f - (r2 - r1);
    L34:
        r0 = true;
    L35:
        if (r0 == false) goto L37;
        this.view.onPickerDataChanged();
    L37:
        return true;
    }

    public void set(float r1, float r2) {
        this.pickerStart = r1;
        this.pickerEnd = r2;
        this.view.onPickerDataChanged();
    }

    public void uncapture() {
        CapturesData r0 = this.capturedStates[0];
        if (r0 == null) goto L5;
        r0.uncapture();
    L5:
        CapturesData r02 = this.capturedStates[1];
        if (r02 == null) goto L8;
        r02.uncapture();
    L8:
        CapturesData[] r3 = this.capturedStates;
        r3[0] = null;
        r3[1] = null;
    }

    public boolean uncapture(MotionEvent r10, int r11) {
        if (r11 == 0) goto L5;
        CapturesData r9 = this.capturedStates[1];
        if (r9 == null) goto L31;
        r9.uncapture();
    L31:
        this.capturedStates[1] = null;
    L32:
        return false;
    L5:
        if (this.tryMoveTo == false) goto L22;
        this.tryMoveTo = false;
        float r112 = this.moveToX - r10.getX();
        float r0 = this.moveToY - r10.getY();
        if (r10.getAction() == 1) goto L9;
    L21:
        return true;
    L9:
        if ((System.currentTimeMillis() - this.startTapTime) >= 300) goto L21;
        if (Math.sqrt((r112 * r112) + (r0 * r0)) >= AndroidUtilities.dp(10.0f)) goto L21;
        float r102 = (this.moveToX - BaseChartView.HORIZONTAL_PADDING) / this.pickerWidth;
        float r7 = this.pickerEnd;
        float r5 = this.pickerStart;
        float r113 = r7 - r5;
        float r02 = r113 / 2.0f;
        float r1 = r102 - r02;
        float r103 = r102 + r02;
        if (r1 >= 0.0f) goto L16;
        float r8 = r113;
        float r6 = 0.0f;
    L20:
        this.moveToAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.view.onPickerJumpTo(r6, r8, true);
        this.moveToAnimator.addUpdateListener(new ChartPickerDelegate$$ExternalSyntheticLambda0(this, r5, r6, r7, r8));
        this.moveToAnimator.setInterpolator(BaseChartView.INTERPOLATOR);
        this.moveToAnimator.start();
        goto L21
    L16:
        if (r103 <= 1.0f) goto L19;
        r1 = 1.0f - r113;
        r8 = 1.0f;
    L18:
        r6 = r1;
        goto L20
    L19:
        r8 = r103;
        goto L18
    L22:
        CapturesData r92 = this.capturedStates[0];
        if (r92 == null) goto L25;
        r92.uncapture();
    L25:
        CapturesData[] r93 = this.capturedStates;
        r93[0] = null;
        CapturesData r104 = r93[1];
        if (r104 == null) goto L32;
        r93[0] = r104;
        r93[1] = null;
        goto L32
    }
}
