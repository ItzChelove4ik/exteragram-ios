package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0082\u0080\u0004¨\u0006\u0006"}, d2 = {"constructMessage", _UrlKt.FRAGMENT_ENCODE_SET, "file", "Ljava/io/File;", "other", "reason", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ExceptionsKt {
    public static final /* synthetic */ String access$constructMessage(File r0, File r1, String r2) {
        return constructMessage(r0, r1, r2);
    }

    private static final String constructMessage(File r2, File r3, String r4) {
        StringBuilder r0 = new StringBuilder(r2.toString());
        if (r3 == null) goto L5;
        r0.append(" -> " + r3);
    L5:
        if (r4 == null) goto L8;
        r0.append(": ".concat(r4));
    L8:
        return r0.toString();
    }
}
