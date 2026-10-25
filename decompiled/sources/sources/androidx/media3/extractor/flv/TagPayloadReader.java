package androidx.media3.extractor.flv;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes4.dex */
abstract class TagPayloadReader {
    protected final TrackOutput output;

    public static final class UnsupportedFormatException extends ParserException {
        public UnsupportedFormatException(String r4) {
            super(r4, null, false, 1);
        }
    }

    public TagPayloadReader(TrackOutput r1) {
        this.output = r1;
    }

    public final boolean consume(ParsableByteArray r2, long r3) {
        if (parseHeader(r2) == true) goto L5;
        return false;
    L5:
        if (parsePayload(r2, r3) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public abstract boolean parseHeader(ParsableByteArray r1);

    public abstract boolean parsePayload(ParsableByteArray r1, long r2);
}
