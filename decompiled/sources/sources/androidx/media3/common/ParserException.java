package androidx.media3.common;

import java.io.IOException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class ParserException extends IOException {
    public final boolean contentIsMalformed;
    public final int dataType;

    public ParserException(String r1, Throwable r2, boolean r3, int r4) {
        super(r1, r2);
        this.contentIsMalformed = r3;
        this.dataType = r4;
    }

    public static ParserException createForMalformedContainer(String r2, Throwable r3) {
        return new ParserException(r2, r3, true, 1);
    }

    public static ParserException createForMalformedDataOfUnknownType(String r3, Throwable r4) {
        return new ParserException(r3, r4, true, 0);
    }

    public static ParserException createForMalformedManifest(String r3, Throwable r4) {
        return new ParserException(r3, r4, true, 4);
    }

    public static ParserException createForUnsupportedContainerFeature(String r4) {
        return new ParserException(r4, null, false, 1);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String r0 = super.getMessage();
        StringBuilder r1 = new StringBuilder();
        if (r0 == null) goto L5;
        String r02 = r0.concat(" ");
    L6:
        r1.append(r02);
        r1.append("{contentIsMalformed=");
        r1.append(this.contentIsMalformed);
        r1.append(", dataType=");
        r1.append(this.dataType);
        r1.append("}");
        return r1.toString();
    L5:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
