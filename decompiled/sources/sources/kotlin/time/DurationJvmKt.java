package kotlin.time;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\u001a\u0012\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0082\u0080\u0004\u001a\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0080\u0080\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0010"}, d2 = {"durationAssertionsEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "getDurationAssertionsEnabled", "()Z", "precisionFormats", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/ThreadLocal;", "Ljava/text/DecimalFormat;", "[Ljava/lang/ThreadLocal;", "createFormatForDecimals", "decimals", _UrlKt.FRAGMENT_ENCODE_SET, "formatToExactDecimals", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDurationJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DurationJvm.kt\nkotlin/time/DurationJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,28:1\n1#2:29\n*E\n"})
/* loaded from: classes.dex */
public final class DurationJvmKt {
    private static final boolean durationAssertionsEnabled = false;
    private static final ThreadLocal<DecimalFormat>[] precisionFormats = null;

    static {
        ThreadLocal<DecimalFormat>[] r1 = new ThreadLocal[4];
        int r2 = 0;
    L3:
        if (r2 >= 4) goto L5;
        r1[r2] = new ThreadLocal();
        r2 = r2 + 1;
        goto L3
    L5:
        precisionFormats = r1;
    }

    private static final DecimalFormat createFormatForDecimals(int r2) {
        DecimalFormat r0 = new DecimalFormat(MVEL.VERSION_SUB);
        if (r2 <= 0) goto L5;
        r0.setMinimumFractionDigits(r2);
    L5:
        r0.setRoundingMode(RoundingMode.HALF_UP);
        return r0;
    }

    public static final String formatToExactDecimals(double r2, int r4) {
        ThreadLocal<DecimalFormat>[] r0 = precisionFormats;
        if (r4 >= r0.length) goto L8;
        ThreadLocal<DecimalFormat> r02 = r0[r4];
        DecimalFormat r1 = r02.get();
        if (r1 != null) goto L7;
        r1 = createFormatForDecimals(r4);
        r02.set(r1);
    L7:
        DecimalFormat r12 = r1;
    L10:
        return r12.format(r2);
    L8:
        r12 = createFormatForDecimals(r4);
        goto L10
    }

    public static final boolean getDurationAssertionsEnabled() {
        return durationAssertionsEnabled;
    }
}
