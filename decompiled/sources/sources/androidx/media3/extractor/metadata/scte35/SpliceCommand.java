package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.Metadata;

/* loaded from: classes4.dex */
public abstract class SpliceCommand implements Metadata.Entry {
    public SpliceCommand() {
    }

    public String toString() {
        return "SCTE-35 splice command: type=".concat(getClass().getSimpleName());
    }
}
