package com.exteragram.messenger.math;

import java.math.BigDecimal;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/math/MathFormat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "decimalSeparator", _UrlKt.FRAGMENT_ENCODE_SET, "format", "(DC)Ljava/lang/String;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MathFormat {
    public static final MathFormat INSTANCE = null;

    static {
        INSTANCE = new MathFormat();
    }

    private MathFormat() {
    }

    public final String format(double r8, char r10) {
        if (Double.isNaN(r8) == false) goto L5;
    L27:
        return null;
    L5:
        if (Double.isInfinite(r8) == true) goto L27;
        double r1 = Math.abs(r8);
        if (r1 < 1.0E12d) goto L11;
        return null;
    L11:
        if (r1 != 0.0d) goto L14;
    L16:
        String r7 = MathFormat$$ExternalSyntheticBackportWithForwarding0.m(BigDecimal.valueOf(r8).round(new MathContext(12))).toPlainString();
        if (Intrinsics.areEqual(r7, "-0") == false) goto L19;
        r7 = MVEL.VERSION_SUB;
    L19:
        String r12 = r7;
        if (r12.length() <= 24) goto L23;
        return null;
    L23:
        if (r10 != '.') goto L26;
        return r12;
    L26:
        return StringsKt.replace$default(r12, '.', r10, false, 4, null);
    L14:
        if (r1 >= 1.0E-9d) goto L16;
        return null;
    }
}
