package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;

/* loaded from: classes4.dex */
public final class DecoderReuseEvaluation {
    public final String decoderName;
    public final int discardReasons;
    public final Format newFormat;
    public final Format oldFormat;
    public final int result;

    public DecoderReuseEvaluation(String r2, Format r3, Format r4, int r5, int r6) {
        if (r5 == 0) goto L7;
        if (r6 == 0) goto L7;
        boolean r0 = false;
    L8:
        Assertions.checkArgument(r0);
        this.decoderName = Assertions.checkNotEmpty(r2);
        this.oldFormat = (Format) Assertions.checkNotNull(r3);
        this.newFormat = (Format) Assertions.checkNotNull(r4);
        this.result = r5;
        this.discardReasons = r6;
        return;
    L7:
        r0 = true;
        goto L8
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L21:
        return false;
    L8:
        if (DecoderReuseEvaluation.class != r5.getClass()) goto L21;
        DecoderReuseEvaluation r52 = (DecoderReuseEvaluation) r5;
        if (this.result != r52.result) goto L21;
        if (this.discardReasons != r52.discardReasons) goto L21;
        if (this.decoderName.equals(r52.decoderName) == false) goto L21;
        if (this.oldFormat.equals(r52.oldFormat) == false) goto L21;
        if (this.newFormat.equals(r52.newFormat) == false) goto L21;
        return true;
    }

    public int hashCode() {
        return ((((((((527 + this.result) * 31) + this.discardReasons) * 31) + this.decoderName.hashCode()) * 31) + this.oldFormat.hashCode()) * 31) + this.newFormat.hashCode();
    }
}
