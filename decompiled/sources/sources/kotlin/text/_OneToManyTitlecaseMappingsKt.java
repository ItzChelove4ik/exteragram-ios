package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\f\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\u0080\u0004¨\u0006\u0003"}, d2 = {"titlecaseImpl", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class _OneToManyTitlecaseMappingsKt {
    public static final String titlecaseImpl(char r4) {
        String r0 = String.valueOf(r4);
        Locale r1 = Locale.ROOT;
        String r02 = r0.toUpperCase(r1);
        if (r02.length() <= 1) goto L10;
        if (r4 != 329) goto L8;
        return r02;
    L8:
        return r02.charAt(0) + r02.substring(1).toLowerCase(r1);
    L10:
        return String.valueOf(Character.toTitleCase(r4));
    }
}
