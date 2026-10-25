package kotlin.time;

import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u000bH\u0086\u0088\u0004ø\u0001\u0000JA\u0010\u0010\u001a\u00020\t*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u000bH\u0082\u0088\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"Lkotlin/time/FractionalParser;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "parse", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "endIndex", _UrlKt.FRAGMENT_ENCODE_SET, "parseDigits", "maxDigits", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/FractionalParser\n+ 2 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n1#1,1613:1\n1351#1,14:1614\n1351#1,14:1628\n1656#2,3:1642\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/FractionalParser\n*L\n1343#1:1614,14\n1344#1:1628,14\n1345#1:1642,3\n*E\n"})
/* loaded from: classes5.dex */
public final class FractionalParser {
    public static final FractionalParser INSTANCE = null;

    static {
        INSTANCE = new FractionalParser();
    }

    private FractionalParser() {
    }

    private final int parseDigits(String r6, int r7, int r8, Function1<? super Integer, Unit> r9) {
        int r5 = Math.min(r7 + r8, r6.length());
        int r0 = 0;
        int r1 = r7;
        int r2 = 0;
    L3:
        if (r1 >= r5) goto L10;
        char r3 = r6.charAt(r1);
        if ('0' > r3) goto L10;
        if (r3 >= ':') goto L10;
        r2 = ((r2 << 3) + (r2 << 1)) + (r3 - '0');
        r1 = r1 + 1;
    L10:
        if (r0 >= (r8 - (r1 - r7))) goto L12;
        r2 = (r2 << 3) + (r2 << 1);
        r0 = r0 + 1;
        goto L10
    L12:
        r9.invoke(Integer.valueOf(r1));
        return r2;
    }

    public final long parse(String r9, int r10, Function1<? super Integer, Unit> r11) {
        int r8 = Math.min(r10 + 6, r9.length());
        int r0 = 0;
        int r1 = r10;
        int r2 = 0;
    L4:
        if (r1 >= r8) goto L9;
        char r5 = r9.charAt(r1);
        if ('0' > r5) goto L9;
        if (r5 >= ':') goto L9;
        r2 = ((r2 << 3) + (r2 << 1)) + (r5 - '0');
        r1 = r1 + 1;
    L9:
        int r82 = 6 - (r1 - r10);
        int r102 = 0;
    L10:
        if (r102 >= r82) goto L12;
        r2 = (r2 << 1) + (r2 << 3);
        r102 = r102 + 1;
        goto L10
    L12:
        int r83 = Math.min(r1 + 9, r9.length());
        int r52 = 0;
        int r103 = r1;
    L13:
        if (r103 >= r83) goto L18;
        char r6 = r9.charAt(r103);
        if ('0' > r6) goto L18;
        if (r6 >= ':') goto L18;
        r52 = ((r52 << 3) + (r52 << 1)) + (r6 - '0');
        r103 = r103 + 1;
    L18:
        int r84 = 9 - (r103 - r1);
    L19:
        if (r0 >= r84) goto L22;
        r52 = (r52 << 1) + (r52 << 3);
        r0 = r0 + 1;
    L22:
        if (r103 >= r9.length()) goto L27;
        char r85 = r9.charAt(r103);
        if ('0' > r85) goto L27;
        if (r85 >= ':') goto L27;
        r103 = r103 + 1;
    L27:
        r11.invoke(Integer.valueOf(r103));
        return (r2 * 1000000000) + r52;
    }
}
