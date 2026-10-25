package j$.time.format;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class a extends w {
    public final /* synthetic */ v d;

    public a(v r1) {
        this.d = r1;
    }

    @Override // j$.time.format.w
    public final String b(j$.time.chrono.k r1, j$.time.temporal.p r2, long r3, TextStyle r5, Locale r6) {
        return this.d.a(r3, r5);
    }

    @Override // j$.time.format.w
    public final String c(j$.time.temporal.p r1, long r2, TextStyle r4, Locale r5) {
        return this.d.a(r2, r4);
    }

    @Override // j$.time.format.w
    public final Iterator d(j$.time.chrono.k r1, j$.time.temporal.p r2, TextStyle r3, Locale r4) {
        List r0 = (List) ((HashMap) this.d.f413b).get(r3);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.iterator();
    }

    @Override // j$.time.format.w
    public final Iterator e(j$.time.temporal.p r1, TextStyle r2, Locale r3) {
        List r0 = (List) ((HashMap) this.d.f413b).get(r2);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.iterator();
    }
}
