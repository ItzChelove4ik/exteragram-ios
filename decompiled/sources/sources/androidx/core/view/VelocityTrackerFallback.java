package androidx.core.view;

import android.view.MotionEvent;

/* loaded from: classes3.dex */
class VelocityTrackerFallback {
    private int mDataPointsBufferLastUsedIndex;
    private int mDataPointsBufferSize;
    private final long[] mEventTimes;
    private float mLastComputedVelocity;
    private final float[] mMovements;

    public VelocityTrackerFallback() {
        this.mMovements = new float[20];
        this.mEventTimes = new long[20];
        this.mLastComputedVelocity = 0.0f;
        this.mDataPointsBufferSize = 0;
        this.mDataPointsBufferLastUsedIndex = 0;
    }

    private void clear() {
        this.mDataPointsBufferSize = 0;
        this.mLastComputedVelocity = 0.0f;
    }

    private float getCurrentVelocity() {
        int r0 = this.mDataPointsBufferSize;
        if (r0 >= 2) goto L5;
        return 0.0f;
    L5:
        int r3 = this.mDataPointsBufferLastUsedIndex;
        int r4 = ((r3 + 20) - (r0 - 1)) % 20;
        long r6 = this.mEventTimes[r3];
    L6:
        long[] r02 = this.mEventTimes;
        long r8 = r02[r4];
        long r10 = r6 - r8;
        int r102 = this.mDataPointsBufferSize;
        if (r10 <= 100) goto L9;
        this.mDataPointsBufferSize = r102 - 1;
        r4 = (r4 + 1) % 20;
        goto L6
    L9:
        if (r102 >= 2) goto L11;
        return 0.0f;
    L11:
        if (r102 != 2) goto L17;
        int r42 = (r4 + 1) % 20;
        if (r8 != r02[r42]) goto L16;
        return 0.0f;
    L16:
        return this.mMovements[r42] / (r2 - r8);
    L17:
        int r03 = 0;
        float r2 = 0.0f;
        int r1 = 0;
    L19:
        if (r03 >= (this.mDataPointsBufferSize - 1)) goto L28;
        int r32 = r03 + r4;
        long[] r62 = this.mEventTimes;
        long r7 = r62[r32 % 20];
        int r33 = (r32 + 1) % 20;
        if (r62[r33] == r7) goto L26;
        r1 = r1 + 1;
        float r63 = kineticEnergyToVelocity(r2);
        float r9 = this.mMovements[r33] / (this.mEventTimes[r33] - r7);
        r2 = r2 + ((r9 - r63) * Math.abs(r9));
        if (r1 != 1) goto L26;
        r2 = r2 * 0.5f;
    L26:
        r03 = r03 + 1;
        goto L19
    L28:
        return kineticEnergyToVelocity(r2);
    }

    private static float kineticEnergyToVelocity(float r3) {
        if (r3 >= 0.0f) goto L5;
        float r0 = -1.0f;
    L7:
        return r0 * ((float) Math.sqrt(Math.abs(r3) * 2.0f));
    L5:
        r0 = 1.0f;
        goto L7
    }

    public void addMovement(MotionEvent r7) {
        long r0 = r7.getEventTime();
        if (this.mDataPointsBufferSize != 0) goto L5;
    L7:
        int r2 = (this.mDataPointsBufferLastUsedIndex + 1) % 20;
        this.mDataPointsBufferLastUsedIndex = r2;
        int r4 = this.mDataPointsBufferSize;
        if (r4 == 20) goto L10;
        this.mDataPointsBufferSize = r4 + 1;
    L10:
        this.mMovements[r2] = r7.getAxisValue(26);
        this.mEventTimes[this.mDataPointsBufferLastUsedIndex] = r0;
        return;
    L5:
        if ((r0 - this.mEventTimes[this.mDataPointsBufferLastUsedIndex]) <= 40) goto L7;
        clear();
        goto L7
    }

    public void computeCurrentVelocity(int r2, float r3) {
        float r0 = getCurrentVelocity() * r2;
        this.mLastComputedVelocity = r0;
        if (r0 >= (-Math.abs(r3))) goto L7;
        this.mLastComputedVelocity = -Math.abs(r3);
        return;
    L7:
        if (this.mLastComputedVelocity <= Math.abs(r3)) goto L10;
        this.mLastComputedVelocity = Math.abs(r3);
        return;
    }

    public float getAxisVelocity(int r2) {
        if (r2 == 26) goto L7;
        return 0.0f;
    L7:
        return this.mLastComputedVelocity;
    }
}
