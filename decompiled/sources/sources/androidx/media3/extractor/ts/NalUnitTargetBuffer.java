package androidx.media3.extractor.ts;

import androidx.media3.common.util.Assertions;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class NalUnitTargetBuffer {
    private boolean isCompleted;
    private boolean isFilling;
    public byte[] nalData;
    public int nalLength;
    private final int targetType;

    public NalUnitTargetBuffer(int r1, int r2) {
        this.targetType = r1;
        byte[] r12 = new byte[r2 + 3];
        this.nalData = r12;
        r12[2] = 1;
    }

    public void appendToNalUnit(byte[] r5, int r6, int r7) {
        if (this.isFilling == true) goto L5;
        return;
    L5:
        int r72 = r7 - r6;
        byte[] r0 = this.nalData;
        int r1 = r0.length;
        int r2 = this.nalLength;
        if (r1 >= (r2 + r72)) goto L8;
        this.nalData = Arrays.copyOf(r0, (r2 + r72) * 2);
    L8:
        System.arraycopy(r5, r6, this.nalData, this.nalLength, r72);
        this.nalLength += r72;
    }

    public boolean endNalUnit(int r3) {
        if (this.isFilling == true) goto L5;
        return false;
    L5:
        this.nalLength -= r3;
        this.isFilling = false;
        this.isCompleted = true;
        return true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void reset() {
        this.isFilling = false;
        this.isCompleted = false;
    }

    public void startNalUnit(int r4) {
        boolean r1 = true;
        Assertions.checkState(!this.isFilling);
        if (r4 == this.targetType) goto L6;
        r1 = false;
    L6:
        this.isFilling = r1;
        if (r1 == false) goto L10;
        this.nalLength = 3;
        this.isCompleted = false;
        return;
    }
}
