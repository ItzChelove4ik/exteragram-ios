package androidx.media3.common.audio;

import androidx.media3.common.util.Assertions;
import com.google.android.gms.cast.MediaError;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* loaded from: classes3.dex */
final class Sonic {
    private double accumulatedSpeedAdjustmentError;
    private final int channelCount;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequiredFrameCount;
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputFrameCount;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchFrameCount;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public Sonic(int r1, int r2, float r3, float r4, int r5) {
        this.inputSampleRateHz = r1;
        this.channelCount = r2;
        this.speed = r3;
        this.pitch = r4;
        this.rate = r1 / r5;
        this.minPeriod = r1 / MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        int r12 = r1 / 65;
        this.maxPeriod = r12;
        int r13 = r12 * 2;
        this.maxRequiredFrameCount = r13;
        this.downSampleBuffer = new short[r13];
        this.inputBuffer = new short[r13 * r2];
        this.outputBuffer = new short[r13 * r2];
        this.pitchBuffer = new short[r13 * r2];
    }

    private void adjustRate(float r13, int r14) {
        if (this.outputFrameCount != r14) goto L5;
        return;
    L5:
        int r0 = this.inputSampleRateHz;
        long r10 = (long) (r0 / r13);
        long r8 = r0;
    L7:
        if (r10 == 0) goto L15;
        if (r8 == 0) goto L15;
        if ((r10 % 2) != 0) goto L15;
        if ((r8 % 2) != 0) goto L15;
        r10 = r10 / 2;
        r8 = r8 / 2;
    L15:
        moveNewSamplesToPitchBuffer(r14);
        int r142 = 0;
    L16:
        int r02 = this.pitchFrameCount;
        boolean r2 = true;
        if (r142 >= (r02 - 1)) goto L33;
    L18:
        int r03 = this.oldRatePosition;
        long r3 = (r03 + 1) * r10;
        int r1 = this.newRatePosition;
        if (r3 <= (r1 * r8)) goto L25;
        this.outputBuffer = this.ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, 1);
        int r04 = 0;
    L21:
        int r12 = this.channelCount;
        if (r04 >= r12) goto L24;
        Sonic r5 = this;
        this.outputBuffer[(this.outputFrameCount * r12) + r04] = r5.interpolate(this.pitchBuffer, (r12 * r142) + r04, r8, r10);
        r04 = r04 + 1;
        Sonic this = r5;
        goto L21
    L24:
        Sonic r52 = this;
        r52.newRatePosition++;
        r52.outputFrameCount++;
        this = r52;
        goto L18
    L25:
        Sonic r53 = this;
        int r05 = r03 + 1;
        r53.oldRatePosition = r05;
        if (r05 != r8) goto L32;
        r53.oldRatePosition = 0;
        if (r1 == r10) goto L31;
        r2 = false;
    L31:
        Assertions.checkState(r2);
        r53.newRatePosition = 0;
    L32:
        r142 = r142 + 1;
        this = r53;
        goto L16
    L33:
        this.removePitchFrames(r02 - 1);
    }

    private void changeSpeed(double r9) {
        int r0 = this.inputFrameCount;
        if (r0 >= this.maxRequiredFrameCount) goto L5;
        return;
    L5:
        int r4 = 0;
    L7:
        if (this.remainingInputToCopyFrameCount <= 0) goto L9;
        r4 = r4 + this.copyInputToOutput(r4);
        Sonic r2 = this;
        double r5 = r9;
    L14:
        if ((r2.maxRequiredFrameCount + r4) > r0) goto L15;
        Sonic this = r2;
        r9 = r5;
        goto L7
    L15:
        r2.removeProcessedInputFrames(r4);
        return;
    L9:
        int r7 = this.findPitchPeriod(this.inputBuffer, r4);
        short[] r3 = this.inputBuffer;
        if (r9 <= 1.0d) goto L12;
        r2 = this;
        r5 = r9;
        r4 = r4 + (r7 + r2.skipPitchPeriod(r3, r4, r5, r7));
        goto L14
    L12:
        r2 = this;
        r5 = r9;
        r4 = r4 + r2.insertPitchPeriod(r3, r4, r5, r7);
        goto L14
    }

    private int copyInputToOutput(int r3) {
        int r0 = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        copyToOutput(this.inputBuffer, r3, r0);
        this.remainingInputToCopyFrameCount -= r0;
        return r0;
    }

    private void copyToOutput(short[] r4, int r5, int r6) {
        short[] r0 = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, r6);
        this.outputBuffer = r0;
        int r1 = this.channelCount;
        System.arraycopy(r4, r5 * r1, r0, this.outputFrameCount * r1, r1 * r6);
        this.outputFrameCount += r6;
    }

    private void downSampleInput(short[] r7, int r8, int r9) {
        int r0 = this.maxRequiredFrameCount / r9;
        int r1 = this.channelCount;
        int r92 = r9 * r1;
        int r82 = r8 * r1;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        int r3 = 0;
        int r4 = 0;
    L5:
        if (r3 >= r92) goto L7;
        r4 = r4 + r7[((r2 * r92) + r82) + r3];
        r3 = r3 + 1;
        goto L5
    L7:
        this.downSampleBuffer[r2] = (short) (r4 / r92);
        r2 = r2 + 1;
        goto L3
    }

    private short[] ensureSpaceForAdditionalFrames(short[] r2, int r3, int r4) {
        int r0 = r2.length;
        int r1 = this.channelCount;
        int r02 = r0 / r1;
        if ((r3 + r4) > r02) goto L6;
        return r2;
    L6:
        return Arrays.copyOf(r2, (((r02 * 3) / 2) + r4) * r1);
    }

    private int findPitchPeriod(short[] r7, int r8) {
        int r0 = this.inputSampleRateHz;
        if (r0 <= 4000) goto L5;
        int r02 = r0 / 4000;
    L7:
        if (this.channelCount != 1) goto L10;
        if (r02 != 1) goto L10;
        int r72 = findPitchPeriodInRange(r7, r8, this.minPeriod, this.maxPeriod);
    L24:
        if (previousPeriodBetter(this.minDiff, this.maxDiff) == false) goto L26;
        int r82 = this.prevPeriod;
    L27:
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = r72;
        return r82;
    L26:
        r82 = r72;
    L10:
        downSampleInput(r7, r8, r02);
        int r2 = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / r02, this.maxPeriod / r02);
        if (r02 == 1) goto L22;
        int r22 = r2 * r02;
        int r03 = r02 * 4;
        int r3 = r22 - r03;
        int r23 = r22 + r03;
        int r04 = this.minPeriod;
        if (r3 >= r04) goto L15;
        r3 = r04;
    L15:
        int r05 = this.maxPeriod;
        if (r23 <= r05) goto L19;
        r23 = r05;
    L19:
        if (this.channelCount != 1) goto L21;
        r72 = findPitchPeriodInRange(r7, r8, r3, r23);
        goto L24
    L21:
        downSampleInput(r7, r8, 1);
        r72 = findPitchPeriodInRange(this.downSampleBuffer, 0, r3, r23);
        goto L24
    L22:
        r72 = r2;
        goto L24
    L5:
        r02 = 1;
        goto L7
    }

    private int findPitchPeriodInRange(short[] r10, int r11, int r12, int r13) {
        int r112 = r11 * this.channelCount;
        int r1 = 255;
        int r2 = 1;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r12 > r13) goto L14;
        int r5 = 0;
        int r6 = 0;
    L5:
        if (r5 >= r12) goto L8;
        r6 = r6 + Math.abs(r10[r112 + r5] - r10[(r112 + r12) + r5]);
        r5 = r5 + 1;
        goto L5
    L8:
        if ((r6 * r3) >= (r2 * r12)) goto L11;
        r3 = r12;
        r2 = r6;
    L11:
        if ((r6 * r1) <= (r4 * r12)) goto L13;
        r1 = r12;
        r4 = r6;
    L13:
        r12 = r12 + 1;
        goto L3
    L14:
        this.minDiff = r2 / r3;
        this.maxDiff = r4 / r1;
        return r3;
    }

    private int insertPitchPeriod(short[] r18, int r19, double r20, int r22) {
        double r3 = this.accumulatedSpeedAdjustmentError;
        if (r20 >= 0.5d) goto L5;
        double r7 = ((r22 * r20) / (1.0d - r20)) + r3;
        int r2 = (int) Math.round(r7);
        this.accumulatedSpeedAdjustmentError = r7 - r2;
        int r9 = r2;
    L6:
        int r4 = r22 + r9;
        short[] r23 = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, r4);
        this.outputBuffer = r23;
        int r32 = this.channelCount;
        System.arraycopy(r18, r19 * r32, r23, this.outputFrameCount * r32, r32 * r22);
        overlapAdd(r9, this.channelCount, this.outputBuffer, this.outputFrameCount + r22, r18, r19 + r22, r18, r19);
        this.outputFrameCount += r4;
        return r9;
    L5:
        double r72 = ((r22 * ((2.0d * r20) - 1.0d)) / (1.0d - r20)) + r3;
        int r24 = (int) Math.round(r72);
        this.remainingInputToCopyFrameCount = r24;
        this.accumulatedSpeedAdjustmentError = r72 - r24;
        r9 = r22;
        goto L6
    }

    private short interpolate(short[] r6, int r7, long r8, long r10) {
        short r0 = r6[r7];
        short r62 = r6[r7 + this.channelCount];
        long r1 = this.newRatePosition * r8;
        long r3 = (r5 + 1) * r10;
        long r9 = r3 - r1;
        long r32 = r3 - (this.oldRatePosition * r10);
        return (short) (((r0 * r9) + ((r32 - r9) * r62)) / r32);
    }

    private void moveNewSamplesToPitchBuffer(int r7) {
        int r0 = this.outputFrameCount - r7;
        short[] r1 = ensureSpaceForAdditionalFrames(this.pitchBuffer, this.pitchFrameCount, r0);
        this.pitchBuffer = r1;
        short[] r2 = this.outputBuffer;
        int r3 = this.channelCount;
        System.arraycopy(r2, r7 * r3, r1, this.pitchFrameCount * r3, r3 * r0);
        this.outputFrameCount = r7;
        this.pitchFrameCount += r0;
    }

    private static void overlapAdd(int r8, int r9, short[] r10, int r11, short[] r12, int r13, short[] r14, int r15) {
        int r1 = 0;
    L3:
        if (r1 >= r9) goto L8;
        int r2 = (r11 * r9) + r1;
        int r3 = (r15 * r9) + r1;
        int r4 = (r13 * r9) + r1;
        int r5 = 0;
    L5:
        if (r5 >= r8) goto L7;
        r10[r2] = (short) (((r12[r4] * (r8 - r5)) + (r14[r3] * r5)) / r8);
        r2 = r2 + r9;
        r4 = r4 + r9;
        r3 = r3 + r9;
        r5 = r5 + 1;
        goto L5
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private boolean previousPeriodBetter(int r3, int r4) {
        if (r3 != 0) goto L5;
    L15:
        return false;
    L5:
        if (this.prevPeriod == 0) goto L15;
        if (r4 <= (r3 * 3)) goto L11;
        return false;
    L11:
        if ((r3 * 2) > (this.prevMinDiff * 3)) goto L13;
        return false;
    L13:
        return true;
    }

    private void processStreamInput() {
        int r0 = this.outputFrameCount;
        float r1 = this.speed;
        float r2 = this.pitch;
        double r3 = r1 / r2;
        float r12 = this.rate * r2;
        if (r3 <= 1.0000100135803223d) goto L5;
    L8:
        changeSpeed(r3);
    L10:
        if (r12 == 1.0f) goto L13;
        adjustRate(r12, r0);
        return;
    L13:
        return;
    L5:
        if (r3 < 0.9999899864196777d) goto L8;
        copyToOutput(this.inputBuffer, 0, this.inputFrameCount);
        this.inputFrameCount = 0;
        goto L10
    }

    private void removePitchFrames(int r5) {
        if (r5 != 0) goto L4;
        return;
    L4:
        short[] r0 = this.pitchBuffer;
        int r1 = this.channelCount;
        System.arraycopy(r0, r5 * r1, r0, 0, (this.pitchFrameCount - r5) * r1);
        this.pitchFrameCount -= r5;
    }

    private void removeProcessedInputFrames(int r5) {
        int r0 = this.inputFrameCount - r5;
        short[] r1 = this.inputBuffer;
        int r2 = this.channelCount;
        System.arraycopy(r1, r5 * r2, r1, 0, r2 * r0);
        this.inputFrameCount = r0;
    }

    private int skipPitchPeriod(short[] r11, int r12, double r13, int r15) {
        double r4 = this.accumulatedSpeedAdjustmentError;
        if (r13 < 2.0d) goto L5;
        double r1 = (r15 / (r13 - 1.0d)) + r4;
        int r3 = (int) Math.round(r1);
        this.accumulatedSpeedAdjustmentError = r1 - r3;
    L6:
        short[] r2 = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, r3);
        this.outputBuffer = r2;
        int r0 = r3;
        overlapAdd(r0, this.channelCount, r2, this.outputFrameCount, r11, r12, r11, r12 + r15);
        this.outputFrameCount += r0;
        return r0;
    L5:
        double r8 = ((r15 * (2.0d - r13)) / (r13 - 1.0d)) + r4;
        int r14 = (int) Math.round(r8);
        this.remainingInputToCopyFrameCount = r14;
        this.accumulatedSpeedAdjustmentError = r8 - r14;
        r3 = r15;
        goto L6
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.prevMinDiff = 0;
        this.minDiff = 0;
        this.maxDiff = 0;
        this.accumulatedSpeedAdjustmentError = 0.0d;
    }

    public void getOutput(ShortBuffer r5) {
        if (this.outputFrameCount < 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        int r02 = Math.min(r5.remaining() / this.channelCount, this.outputFrameCount);
        r5.put(this.outputBuffer, 0, this.channelCount * r02);
        int r52 = this.outputFrameCount - r02;
        this.outputFrameCount = r52;
        short[] r2 = this.outputBuffer;
        int r4 = this.channelCount;
        System.arraycopy(r2, r02 * r4, r2, 0, r52 * r4);
        return;
    L5:
        r0 = false;
        goto L6
    }

    public int getOutputSize() {
        if (this.outputFrameCount < 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        return (this.outputFrameCount * this.channelCount) * 2;
    L5:
        r0 = false;
        goto L6
    }

    public int getPendingInputBytes() {
        return (this.inputFrameCount * this.channelCount) * 2;
    }

    public void queueEndOfStream() {
        int r0 = this.inputFrameCount;
        float r1 = this.speed;
        float r2 = this.pitch;
        double r3 = r1 / r2;
        int r7 = this.outputFrameCount + ((int) (((((((r0 - r5) / r3) + this.remainingInputToCopyFrameCount) + this.accumulatedSpeedAdjustmentError) + this.pitchFrameCount) / (this.rate * r2)) + 0.5d));
        this.accumulatedSpeedAdjustmentError = 0.0d;
        this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, r0, (this.maxRequiredFrameCount * 2) + r0);
        int r22 = 0;
    L3:
        int r32 = this.maxRequiredFrameCount;
        int r5 = this.channelCount;
        if (r22 >= ((r32 * 2) * r5)) goto L6;
        this.inputBuffer[(r5 * r0) + r22] = 0;
        r22 = r22 + 1;
        goto L3
    L6:
        this.inputFrameCount += r32 * 2;
        processStreamInput();
        if (this.outputFrameCount <= r7) goto L9;
        this.outputFrameCount = Math.max(r7, 0);
    L9:
        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void queueInput(ShortBuffer r6) {
        int r0 = r6.remaining();
        int r1 = this.channelCount;
        int r02 = r0 / r1;
        short[] r2 = ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, r02);
        this.inputBuffer = r2;
        r6.get(r2, this.inputFrameCount * this.channelCount, ((r1 * r02) * 2) / 2);
        this.inputFrameCount += r02;
        processStreamInput();
    }
}
