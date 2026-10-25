package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class MlltFrame extends Id3Frame {
    public final int bytesBetweenReference;
    public final int[] bytesDeviations;
    public final int millisecondsBetweenReference;
    public final int[] millisecondsDeviations;
    public final int mpegFramesBetweenReference;

    public MlltFrame(int r2, int r3, int r4, int[] r5, int[] r6) {
        super("MLLT");
        this.mpegFramesBetweenReference = r2;
        this.bytesBetweenReference = r3;
        this.millisecondsBetweenReference = r4;
        this.bytesDeviations = r5;
        this.millisecondsDeviations = r6;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L21:
        return false;
    L8:
        if (MlltFrame.class != r5.getClass()) goto L21;
        MlltFrame r52 = (MlltFrame) r5;
        if (this.mpegFramesBetweenReference != r52.mpegFramesBetweenReference) goto L21;
        if (this.bytesBetweenReference != r52.bytesBetweenReference) goto L21;
        if (this.millisecondsBetweenReference != r52.millisecondsBetweenReference) goto L21;
        if (Arrays.equals(this.bytesDeviations, r52.bytesDeviations) == false) goto L21;
        if (Arrays.equals(this.millisecondsDeviations, r52.millisecondsDeviations) == false) goto L21;
        return true;
    }

    public int hashCode() {
        return ((((((((527 + this.mpegFramesBetweenReference) * 31) + this.bytesBetweenReference) * 31) + this.millisecondsBetweenReference) * 31) + Arrays.hashCode(this.bytesDeviations)) * 31) + Arrays.hashCode(this.millisecondsDeviations);
    }
}
