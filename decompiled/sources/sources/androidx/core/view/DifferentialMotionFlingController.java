package androidx.core.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* loaded from: classes3.dex */
public class DifferentialMotionFlingController {
    private final Context mContext;
    private final int[] mFlingVelocityThresholds;
    private float mLastFlingVelocity;
    private int mLastProcessedAxis;
    private int mLastProcessedDeviceId;
    private int mLastProcessedSource;
    private final DifferentialMotionFlingTarget mTarget;
    private final DifferentialVelocityProvider mVelocityProvider;
    private final FlingVelocityThresholdCalculator mVelocityThresholdCalculator;
    private VelocityTracker mVelocityTracker;

    public interface DifferentialVelocityProvider {
        float getCurrentVelocity(VelocityTracker r1, MotionEvent r2, int r3);
    }

    public interface FlingVelocityThresholdCalculator {
        void calculateFlingVelocityThresholds(Context r1, int[] r2, MotionEvent r3, int r4);
    }

    public static /* synthetic */ void $r8$lambda$lv23FpWloyumz6IvnMabmJR9BpE(Context r0, int[] r1, MotionEvent r2, int r3) {
        calculateFlingVelocityThresholds(r0, r1, r2, r3);
    }

    public static /* synthetic */ float $r8$lambda$rCNQ1h5KBIaVcUXhwymI_1638W0(VelocityTracker r0, MotionEvent r1, int r2) {
        return getCurrentVelocity(r0, r1, r2);
    }

    public DifferentialMotionFlingController(Context r3, DifferentialMotionFlingTarget r4) {
        this(r3, r4, new DifferentialMotionFlingController$$ExternalSyntheticLambda0(), new DifferentialMotionFlingController$$ExternalSyntheticLambda1());
    }

    public DifferentialMotionFlingController(Context r3, DifferentialMotionFlingTarget r4, FlingVelocityThresholdCalculator r5, DifferentialVelocityProvider r6) {
        this.mLastProcessedAxis = -1;
        this.mLastProcessedSource = -1;
        this.mLastProcessedDeviceId = -1;
        this.mFlingVelocityThresholds = new int[]{Integer.MAX_VALUE, 0};
        this.mContext = r3;
        this.mTarget = r4;
        this.mVelocityThresholdCalculator = r5;
        this.mVelocityProvider = r6;
    }

    private static void calculateFlingVelocityThresholds(Context r3, int[] r4, MotionEvent r5, int r6) {
        ViewConfiguration r0 = ViewConfiguration.get(r3);
        r4[0] = ViewConfigurationCompat.getScaledMinimumFlingVelocity(r3, r0, r5.getDeviceId(), r6, r5.getSource());
        r4[1] = ViewConfigurationCompat.getScaledMaximumFlingVelocity(r3, r0, r5.getDeviceId(), r6, r5.getSource());
    }

    private boolean calculateFlingVelocityThresholds(MotionEvent r6, int r7) {
        int r0 = r6.getSource();
        int r1 = r6.getDeviceId();
        if (this.mLastProcessedSource == r0) goto L5;
    L11:
        this.mVelocityThresholdCalculator.calculateFlingVelocityThresholds(this.mContext, this.mFlingVelocityThresholds, r6, r7);
        this.mLastProcessedSource = r0;
        this.mLastProcessedDeviceId = r1;
        this.mLastProcessedAxis = r7;
        return true;
    L5:
        if (this.mLastProcessedDeviceId != r1) goto L11;
        if (this.mLastProcessedAxis != r7) goto L11;
        return false;
    }

    private float getCurrentVelocity(MotionEvent r2, int r3) {
        if (this.mVelocityTracker != null) goto L6;
        this.mVelocityTracker = VelocityTracker.obtain();
    L6:
        return this.mVelocityProvider.getCurrentVelocity(this.mVelocityTracker, r2, r3);
    }

    private static float getCurrentVelocity(VelocityTracker r0, MotionEvent r1, int r2) {
        VelocityTrackerCompat.addMovement(r0, r1);
        VelocityTrackerCompat.computeCurrentVelocity(r0, 1000);
        return VelocityTrackerCompat.getAxisVelocity(r0, r2);
    }

    public void onMotionEvent(MotionEvent r5, int r6) {
        boolean r0 = calculateFlingVelocityThresholds(r5, r6);
        if (this.mFlingVelocityThresholds[0] != Integer.MAX_VALUE) goto L8;
        VelocityTracker r52 = this.mVelocityTracker;
        if (r52 == null) goto L17;
        r52.recycle();
        this.mVelocityTracker = null;
        return;
    L17:
        return;
    L8:
        float r53 = getCurrentVelocity(r5, r6) * this.mTarget.getScaledScrollFactor();
        float r62 = Math.signum(r53);
        float r1 = 0.0f;
        if (r0 == false) goto L11;
    L14:
        this.mTarget.stopDifferentialMotionFling();
    L15:
        float r63 = Math.abs(r53);
        int[] r02 = this.mFlingVelocityThresholds;
        if (r63 < r02[0]) goto L23;
        float r54 = Math.max(-r6, Math.min(r53, r02[1]));
        if (this.mTarget.startDifferentialMotionFling(r54) == false) goto L21;
        r1 = r54;
    L21:
        this.mLastFlingVelocity = r1;
        return;
    L23:
        return;
    L11:
        if (r62 == Math.signum(this.mLastFlingVelocity)) goto L15;
        if (r62 == 0.0f) goto L15;
        goto L14
    }
}
