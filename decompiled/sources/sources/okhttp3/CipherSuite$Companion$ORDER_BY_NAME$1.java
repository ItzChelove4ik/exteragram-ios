package okhttp3;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"okhttp3/CipherSuite$Companion$ORDER_BY_NAME$1", "Ljava/util/Comparator;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Comparator;", "compare", _UrlKt.FRAGMENT_ENCODE_SET, "a", "b", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CipherSuite$Companion$ORDER_BY_NAME$1 implements Comparator<String> {
    public CipherSuite$Companion$ORDER_BY_NAME$1() {
    }

    @Override // java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(String r1, String r2) {
        return compare2(r1, r2);
    }

    /* renamed from: compare, reason: avoid collision after fix types in other method */
    public int compare2(String r6, String r7) {
        int r5 = Math.min(r6.length(), r7.length());
        int r0 = 4;
    L4:
        if (r0 >= r5) goto L12;
        char r3 = r6.charAt(r0);
        char r4 = r7.charAt(r0);
        if (r3 != r4) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        if (Intrinsics.compare(r3, r4) >= 0) goto L10;
        return -1;
    L10:
        return 1;
    L12:
        int r52 = r6.length();
        int r62 = r7.length();
        if (r52 == r62) goto L17;
        if (r52 >= r62) goto L16;
        return -1;
    L16:
        return 1;
    L17:
        return 0;
    }
}
