package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.google.android.gms.cast.MediaError;
import java.lang.ref.WeakReference;
import java.util.Random;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class AudioVisualizerDrawable {
    public int ALPHA;
    public float ANIMATION_DURATION;
    public float IDLE_RADIUS;
    final int MAX_SAMPLE_SUM;
    public float WAVE_RADIUS;
    private final float[] animateTo;
    private final float[] current;
    private final CircleBezierDrawable[] drawables;
    private final float[] dt;
    private float idleScale;
    private boolean idleScaleInc;
    private float[] lastAmplitude;
    private int lastAmplitudeCount;
    private int lastAmplitudePointer;
    private final Paint p1;
    private WeakReference<View> parentView;
    private final Random random;
    float rotation;
    private final int[] tmpWaveform;

    public AudioVisualizerDrawable() {
        this.tmpWaveform = new int[3];
        this.animateTo = new float[8];
        this.current = new float[8];
        this.dt = new float[8];
        this.random = new Random();
        this.IDLE_RADIUS = AndroidUtilities.dp(6.0f) * 0.33f;
        this.WAVE_RADIUS = AndroidUtilities.dp(12.0f) * 0.36f;
        this.ANIMATION_DURATION = 120.0f;
        this.ALPHA = 61;
        this.MAX_SAMPLE_SUM = 6;
        this.lastAmplitude = new float[6];
        this.drawables = new CircleBezierDrawable[2];
        int r2 = 0;
    L3:
        if (r2 >= 2) goto L5;
        CircleBezierDrawable[] r3 = this.drawables;
        CircleBezierDrawable r4 = new CircleBezierDrawable(6);
        r3[r2] = r4;
        r4.idleStateDiff = 0.0f;
        r4.radius = AndroidUtilities.dp(24.0f);
        r4.radiusDiff = 0.0f;
        r4.randomK = 1.0f;
        r2 = r2 + 1;
        goto L3
    L5:
        this.p1 = new Paint(1);
    }

    public void draw(Canvas r11, float r12, float r13) {
        if (LiteMode.isEnabled(32) == false) goto L57;
        int r1 = 0;
    L7:
        if (r1 >= 8) goto L25;
        float[] r2 = this.animateTo;
        float r4 = r2[r1];
        float[] r5 = this.current;
        float r6 = r5[r1];
        if (r4 == r6) goto L24;
        float[] r42 = this.dt;
        float r62 = r6 + (r42[r1] * 16.0f);
        r5[r1] = r62;
        float r43 = r42[r1];
        if (r43 <= 0.0f) goto L15;
        if (r62 <= r2[r1]) goto L15;
    L18:
        r5[r1] = r2[r1];
    L19:
        WeakReference<View> r22 = this.parentView;
        if (r22 == null) goto L24;
        if (r22.get() == null) goto L24;
        this.parentView.get().invalidate();
    L15:
        if (r43 >= 0.0f) goto L19;
        if (r62 >= r2[r1]) goto L19;
    L24:
        r1 = r1 + 1;
        goto L7
    L25:
        boolean r14 = this.idleScaleInc;
        float r23 = this.idleScale;
        if (r14 == false) goto L30;
        float r24 = r23 + 0.02f;
        this.idleScale = r24;
        if (r24 <= 1.0f) goto L33;
        this.idleScaleInc = false;
        this.idleScale = 1.0f;
    L33:
        float[] r15 = this.current;
        float r25 = r15[7];
        float r44 = r15[6] * r15[0];
        if (r25 == 0.0f) goto L36;
    L38:
        int r16 = 0;
    L40:
        if (r16 >= 3) goto L42;
        this.tmpWaveform[r16] = (int) (this.current[r16] * this.WAVE_RADIUS);
        r16 = r16 + 1;
        goto L40
    L42:
        this.drawables[0].setAdditionals(this.tmpWaveform);
        int r17 = 0;
    L43:
        if (r17 >= 3) goto L45;
        this.tmpWaveform[r17] = (int) (this.current[r17 + 3] * this.WAVE_RADIUS);
        r17 = r17 + 1;
        goto L43
    L45:
        this.drawables[1].setAdditionals(this.tmpWaveform);
        float r18 = (AndroidUtilities.dp(22.0f) + (AndroidUtilities.dp(4.0f) * r44)) + (this.IDLE_RADIUS * r25);
        if (r18 <= AndroidUtilities.dp(26.0f)) goto L48;
        r18 = AndroidUtilities.dp(26.0f);
    L48:
        CircleBezierDrawable[] r26 = this.drawables;
        CircleBezierDrawable r3 = r26[0];
        r26[1].radius = r18;
        r3.radius = r18;
        r11.save();
        float r19 = (float) (this.rotation + 0.6d);
        this.rotation = r19;
        r11.rotate(r19, r12, r13);
        r11.save();
        float r110 = (this.idleScale * 0.04f) + 1.0f;
        r11.scale(r110, r110, r12, r13);
        this.drawables[0].draw(r12, r13, r11, this.p1);
        r11.restore();
        r11.rotate(60.0f, r12, r13);
        float r0 = ((1.0f - this.idleScale) * 0.04f) + 1.0f;
        r11.scale(r0, r0, r12, r13);
        this.drawables[1].draw(r12, r13, r11, this.p1);
        r11.restore();
        return;
    L36:
        if (r44 != 0.0f) goto L38;
        return;
    L30:
        float r27 = r23 - 0.02f;
        this.idleScale = r27;
        if (r27 >= 0.0f) goto L33;
        this.idleScaleInc = true;
        this.idleScale = 0.0f;
        goto L33
    }

    public void draw(Canvas r1, float r2, float r3, int r4, float r5, Theme.ResourcesProvider r6) {
        if (LiteMode.isEnabled(32) == true) goto L5;
        return;
    L5:
        this.p1.setColor(r4);
        this.p1.setAlpha((int) (this.ALPHA * r5));
        draw(r1, r2, r3);
    }

    public void draw(Canvas r2, float r3, float r4, boolean r5, float r6, Theme.ResourcesProvider r7) {
        if (LiteMode.isEnabled(32) == true) goto L5;
        return;
    L5:
        Paint r0 = this.p1;
        if (r5 == false) goto L8;
        r0.setColor(Theme.getColor(Theme.key_chat_outLoader, r7));
        this.p1.setAlpha((int) (this.ALPHA * r6));
    L9:
        draw(r2, r3, r4);
        return;
    L8:
        r0.setColor(Theme.getColor(Theme.key_chat_inLoader, r7));
        this.p1.setAlpha((int) (this.ALPHA * r6));
        goto L9
    }

    public View getParentView() {
        WeakReference<View> r0 = this.parentView;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get();
    }

    public void setParentView(View r2) {
        this.parentView = new WeakReference(r2);
    }

    public void setWaveform(boolean r10, boolean r11, float[] r12) {
        if (LiteMode.isEnabled(32) == false) goto L11;
        float r0 = 0.0f;
        int r1 = 0;
        if (r10 == true) goto L13;
        if (r11 == true) goto L13;
    L9:
        if (r1 >= 8) goto L74;
        float[] r102 = this.animateTo;
        this.current[r1] = 0.0f;
        r102[r1] = 0.0f;
        r1 = r1 + 1;
        goto L9
    L74:
        return;
    L13:
        if (r12 != null) goto L15;
    L17:
        boolean r3 = false;
    L18:
        if (r12 != null) goto L20;
        float r4 = 0.0f;
    L21:
        if (r12 != null) goto L23;
    L28:
        this.lastAmplitudeCount = 0;
    L29:
        if (r3 == false) goto L33;
        int r2 = 0;
    L31:
        if (r2 >= 6) goto L33;
        r12[r2] = (this.random.nextInt() % MediaError.DetailedErrorCode.SEGMENT_UNKNOWN) / 1000.0f;
        r2 = r2 + 1;
    L33:
        float r22 = this.ANIMATION_DURATION;
        if (r3 == false) goto L37;
        r22 = r22 * 2.0f;
    L37:
        if (this.lastAmplitudeCount <= 6) goto L45;
        float r42 = 0.0f;
        int r32 = 0;
    L39:
        if (r32 >= 6) goto L41;
        r42 = r42 + this.lastAmplitude[r32];
        r32 = r32 + 1;
        goto L39
    L41:
        float r43 = r42 / 6.0f;
        if (r43 <= 0.52f) goto L45;
        r22 = r22 - (this.ANIMATION_DURATION * (r43 - 0.4f));
    L45:
        if (r1 >= 7) goto L61;
        float[] r33 = this.animateTo;
        if (r12 != null) goto L49;
        r33[r1] = 0.0f;
    L50:
        WeakReference<View> r34 = this.parentView;
        if (r34 != null) goto L53;
    L59:
        this.current[r1] = this.animateTo[r1];
    L60:
        r1 = r1 + 1;
        goto L45
    L53:
        if (r34.get() == null) goto L59;
        float[] r35 = this.dt;
        if (r1 != 6) goto L58;
        r35[r1] = (this.animateTo[r1] - this.current[r1]) / (this.ANIMATION_DURATION + 80.0f);
        goto L60
    L58:
        r35[r1] = (this.animateTo[r1] - this.current[r1]) / r22;
        goto L60
    L49:
        r33[r1] = r12[r1];
        goto L50
    L61:
        float[] r112 = this.animateTo;
        if (r10 == false) goto L64;
        r0 = 1.0f;
    L64:
        r112[7] = r0;
        this.dt[7] = (r0 - this.current[7]) / 120.0f;
        return;
    L23:
        if (r4 <= 0.4d) goto L28;
        float[] r5 = this.lastAmplitude;
        int r6 = this.lastAmplitudePointer;
        r5[r6] = r4;
        int r62 = r6 + 1;
        this.lastAmplitudePointer = r62;
        if (r62 <= 5) goto L27;
        this.lastAmplitudePointer = 0;
    L27:
        this.lastAmplitudeCount++;
        goto L29
    L20:
        r4 = r12[6];
        goto L21
    L15:
        if (r12[6] != 0.0f) goto L17;
        r3 = true;
        goto L18
    }
}
