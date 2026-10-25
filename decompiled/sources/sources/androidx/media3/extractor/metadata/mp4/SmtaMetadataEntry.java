package androidx.media3.extractor.metadata.mp4;

import androidx.media3.common.Metadata;
import com.google.common.primitives.Floats;

/* loaded from: classes4.dex */
public final class SmtaMetadataEntry implements Metadata.Entry {
    public final float captureFrameRate;
    public final int svcTemporalLayerCount;

    public SmtaMetadataEntry(float r1, int r2) {
        this.captureFrameRate = r1;
        this.svcTemporalLayerCount = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (SmtaMetadataEntry.class != r5.getClass()) goto L15;
        SmtaMetadataEntry r52 = (SmtaMetadataEntry) r5;
        if (this.captureFrameRate != r52.captureFrameRate) goto L15;
        if (this.svcTemporalLayerCount != r52.svcTemporalLayerCount) goto L15;
        return true;
    }

    public int hashCode() {
        return ((527 + Floats.hashCode(this.captureFrameRate)) * 31) + this.svcTemporalLayerCount;
    }

    public String toString() {
        return "smta: captureFrameRate=" + this.captureFrameRate + ", svcTemporalLayerCount=" + this.svcTemporalLayerCount;
    }
}
