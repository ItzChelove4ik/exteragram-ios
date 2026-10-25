package androidx.media3.exoplayer;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class SeekParameters {
    public static final SeekParameters CLOSEST_SYNC = null;
    public static final SeekParameters DEFAULT = null;
    public static final SeekParameters EXACT = null;
    public static final SeekParameters NEXT_SYNC = null;
    public static final SeekParameters PREVIOUS_SYNC = null;
    public final long toleranceAfterUs;
    public final long toleranceBeforeUs;

    static {
        SeekParameters r0 = new SeekParameters(0, 0);
        EXACT = r0;
        CLOSEST_SYNC = new SeekParameters(LongCompanionObject.MAX_VALUE, LongCompanionObject.MAX_VALUE);
        PREVIOUS_SYNC = new SeekParameters(LongCompanionObject.MAX_VALUE, 0);
        NEXT_SYNC = new SeekParameters(0, LongCompanionObject.MAX_VALUE);
        DEFAULT = r0;
    }

    public SeekParameters(long r6, long r8) {
        boolean r3 = false;
        if (r6 < 0) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkArgument(r2);
        if (r8 < 0) goto L9;
        r3 = true;
    L9:
        Assertions.checkArgument(r3);
        this.toleranceBeforeUs = r6;
        this.toleranceAfterUs = r8;
        return;
    L5:
        r2 = false;
        goto L6
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L15:
        return false;
    L8:
        if (SeekParameters.class != r7.getClass()) goto L15;
        SeekParameters r72 = (SeekParameters) r7;
        if (this.toleranceBeforeUs != r72.toleranceBeforeUs) goto L15;
        if (this.toleranceAfterUs != r72.toleranceAfterUs) goto L15;
        return true;
    }

    public int hashCode() {
        return (((int) this.toleranceBeforeUs) * 31) + ((int) this.toleranceAfterUs);
    }

    public long resolveSeekPositionUs(long r8, long r10, long r12) {
        long r2 = this.toleranceBeforeUs;
        if (r2 == 0) goto L5;
    L7:
        long r82 = Util.subtractWithOverflowDefault(r8, r2, Long.MIN_VALUE);
        long r22 = Util.addWithOverflowDefault(r8, this.toleranceAfterUs, LongCompanionObject.MAX_VALUE);
        boolean r4 = false;
        if (r82 <= r10) goto L10;
    L12:
        boolean r7 = false;
    L14:
        if (r82 <= r12) goto L16;
    L18:
        if (r7 == false) goto L23;
        if (r4 == false) goto L23;
        if (Math.abs(r10 - r8) <= Math.abs(r12 - r8)) goto L24;
    L26:
        return r12;
    L24:
        return r10;
    L23:
        if (r7 == true) goto L24;
        if (r4 == true) goto L26;
        return r82;
    L16:
        if (r12 > r22) goto L18;
        r4 = true;
        goto L18
    L10:
        if (r10 > r22) goto L12;
        r7 = true;
        goto L14
    L5:
        if (this.toleranceAfterUs != 0) goto L7;
        return r8;
    }
}
