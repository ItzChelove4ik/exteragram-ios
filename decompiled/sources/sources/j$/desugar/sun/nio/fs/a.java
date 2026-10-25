package j$.desugar.sun.nio.fs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public Set f144a;

    /* renamed from: b, reason: collision with root package name */
    public Map f145b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f146c;

    public final void a(String r1, Object r2) {
        ((HashMap) this.f145b).put(r1, r2);
    }

    public final boolean b(String r2) {
        if (this.f146c == false) goto L5;
        return true;
    L5:
        if (((HashSet) this.f144a).contains(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
