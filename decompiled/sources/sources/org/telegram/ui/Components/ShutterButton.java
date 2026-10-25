package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.Keep;
import com.exteragram.messenger.plugins.PluginsConstants;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public class ShutterButton extends View {
    private ShutterButtonDelegate delegate;
    private final DecelerateInterpolator interpolator;
    private long lastUpdateTime;
    private final Runnable longPressed;
    private boolean pressed;
    private boolean processRelease;
    private final Paint redPaint;
    private float redProgress;
    private final Drawable shadowDrawable;
    private State state;
    private long totalTime;
    private final Paint whitePaint;

    /* renamed from: org.telegram.ui.Components.ShutterButton$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ShutterButton this$0;

        public AnonymousClass1(ShutterButton r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ShutterButton.m13006$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (ShutterButton.m13006$$Nest$fgetdelegate(this.this$0).shutterLongPressed() == true) goto L9;
            ShutterButton.m13007$$Nest$fputprocessRelease(this.this$0, false);
            return;
        }
    }

    public interface ShutterButtonDelegate {
        boolean onTranslationChanged(float r1, float r2);

        void shutterCancel();

        boolean shutterLongPressed();

        void shutterReleased();
    }

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State DEFAULT = null;
        public static final State RECORDING = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{DEFAULT, RECORDING};
        }

        static {
            DEFAULT = new State(PluginsConstants.Strategy.DEFAULT, 0);
            RECORDING = new State("RECORDING", 1);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShutterButtonDelegate m13006$$Nest$fgetdelegate(ShutterButton r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fputprocessRelease, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13007$$Nest$fputprocessRelease(ShutterButton r0, boolean r1) {
        r0.processRelease = r1;
    }

    public ShutterButton(Context r4) {
        super(r4);
        this.interpolator = new DecelerateInterpolator();
        this.longPressed = new AnonymousClass1(this);
        this.shadowDrawable = getResources().getDrawable(R.drawable.camera_btn);
        Paint r42 = new Paint(1);
        this.whitePaint = r42;
        Paint.Style r1 = Paint.Style.FILL;
        r42.setStyle(r1);
        r42.setColor(-1);
        Paint r43 = new Paint(1);
        this.redPaint = r43;
        r43.setStyle(r1);
        r43.setColor(-3324089);
        this.state = State.DEFAULT;
    }

    private void setHighlighted(boolean r8) {
        AnimatorSet r0 = new AnimatorSet();
        Property r4 = View.SCALE_Y;
        Property r5 = View.SCALE_X;
        if (r8 == false) goto L5;
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r5, new float[]{1.06f}), ObjectAnimator.ofFloat(this, r4, new float[]{1.06f})});
    L6:
        r0.setDuration(120);
        r0.setInterpolator(this.interpolator);
        r0.start();
        return;
    L5:
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r5, new float[]{1.0f}), ObjectAnimator.ofFloat(this, r4, new float[]{1.0f})});
        r0.setStartDelay(40);
        goto L6
    }

    public ShutterButtonDelegate getDelegate() {
        return this.delegate;
    }

    public State getState() {
        return this.state;
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        int r0 = getMeasuredWidth() / 2;
        int r1 = getMeasuredHeight() / 2;
        this.shadowDrawable.setBounds(r0 - AndroidUtilities.dp(36.0f), r1 - AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f) + r0, AndroidUtilities.dp(36.0f) + r1);
        this.shadowDrawable.draw(r10);
        if (this.pressed == false) goto L5;
    L11:
        float r2 = (getScaleX() - 1.0f) / 0.06f;
        this.whitePaint.setAlpha((int) (255.0f * r2));
        float r02 = r0;
        float r12 = r1;
        r10.drawCircle(r02, r12, AndroidUtilities.dp(26.0f), this.whitePaint);
        State r5 = this.state;
        State r6 = State.RECORDING;
        float r7 = this.redProgress;
        if (r5 != r6) goto L25;
        if (r7 == 1.0f) goto L22;
        long r3 = Math.abs(System.currentTimeMillis() - this.lastUpdateTime);
        if (r3 <= 17) goto L18;
        r3 = 17;
    L18:
        long r52 = this.totalTime + r3;
        this.totalTime = r52;
        if (r52 <= 120) goto L21;
        this.totalTime = 120;
    L21:
        this.redProgress = this.interpolator.getInterpolation(this.totalTime / 120.0f);
        invalidate();
    L22:
        r10.drawCircle(r02, r12, (AndroidUtilities.dp(26.5f) * r2) * this.redProgress, this.redPaint);
        return;
    L25:
        if (r7 == 0.0f) goto L29;
        r10.drawCircle(r02, r12, AndroidUtilities.dp(26.5f) * r2, this.redPaint);
        return;
    L29:
        return;
    L5:
        if (getScaleX() != 1.0f) goto L11;
        if (this.redProgress == 0.0f) goto L28;
        this.redProgress = 0.0f;
        return;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setClassName("android.widget.Button");
        r3.setClickable(true);
        r3.setLongClickable(true);
        r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId(), LocaleController.getString(R.string.AccActionTakePicture)));
        r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK.getId(), LocaleController.getString(R.string.AccActionRecordVideo)));
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(AndroidUtilities.dp(84.0f), AndroidUtilities.dp(84.0f));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        float r0 = r6.getX();
        float r1 = r6.getY();
        int r62 = r6.getAction();
        if (r62 != 0) goto L5;
        AndroidUtilities.runOnUIThread(this.longPressed, 800);
        this.pressed = true;
        this.processRelease = true;
        setHighlighted(true);
    L31:
        return true;
    L5:
        if (r62 != 1) goto L7;
        setHighlighted(false);
        AndroidUtilities.cancelRunOnUIThread(this.longPressed);
        if (this.processRelease == false) goto L31;
        this.delegate.shutterReleased();
        goto L31
    L7:
        if (r62 == 2) goto L13;
        if (r62 != 3) goto L31;
        setHighlighted(false);
        this.pressed = false;
        goto L31
    L13:
        if (r0 < 0.0f) goto L18;
        if (r0 > getMeasuredWidth()) goto L18;
        r0 = 0.0f;
    L18:
        if (r1 < 0.0f) goto L23;
        if (r1 > getMeasuredHeight()) goto L23;
        r1 = 0.0f;
    L23:
        if (this.delegate.onTranslationChanged(r0, r1) == false) goto L31;
        AndroidUtilities.cancelRunOnUIThread(this.longPressed);
        if (this.state != State.RECORDING) goto L31;
        this.processRelease = false;
        setHighlighted(false);
        this.delegate.shutterCancel();
        setState(State.DEFAULT, true);
        goto L31
    }

    public void setDelegate(ShutterButtonDelegate r1) {
        this.delegate = r1;
    }

    @Override // android.view.View
    @Keep
    public void setScaleX(float r1) {
        super.setScaleX(r1);
        invalidate();
    }

    public void setState(State r2, boolean r3) {
        if (this.state == r2) goto L15;
        this.state = r2;
        if (r3 == false) goto L10;
        this.lastUpdateTime = System.currentTimeMillis();
        this.totalTime = 0;
        if (this.state == State.RECORDING) goto L13;
        this.redProgress = 0.0f;
    L13:
        invalidate();
        return;
    L10:
        if (r2 != State.RECORDING) goto L12;
        this.redProgress = 1.0f;
        goto L13
    L12:
        this.redProgress = 0.0f;
        goto L13
    }
}
