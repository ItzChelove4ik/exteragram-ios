package j$.time.format;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class v {

    /* renamed from: a, reason: collision with root package name */
    public final Map f412a;

    /* renamed from: b, reason: collision with root package name */
    public final Map f413b;

    public v(Map r10) {
        this.f412a = r10;
        HashMap r0 = new HashMap();
        ArrayList r1 = new ArrayList();
        Iterator r102 = r10.entrySet().iterator();
    L4:
        if (r102.hasNext() == false) goto L10;
        Map.Entry r2 = (Map.Entry) r102.next();
        HashMap r3 = new HashMap();
        Iterator r4 = ((Map) r2.getValue()).entrySet().iterator();
    L7:
        if (r4.hasNext() == false) goto L9;
        Map.Entry r5 = (Map.Entry) r4.next();
        String r6 = (String) r5.getValue();
        String r7 = (String) r5.getValue();
        Long r52 = (Long) r5.getKey();
        ConcurrentMap r8 = w.f414a;
        r3.put(r6, new AbstractMap.SimpleImmutableEntry(r7, r52));
        goto L7
    L9:
        ArrayList r42 = new ArrayList(r3.values());
        Collections.sort(r42, w.f415b);
        r0.put((TextStyle) r2.getKey(), r42);
        r1.addAll(r42);
        r0.put(null, r1);
        goto L4
    L10:
        Collections.sort(r1, w.f415b);
        this.f413b = r0;
    }

    public final String a(long r1, TextStyle r3) {
        Map r0 = (Map) this.f412a.get(r3);
        if (r0 != null) goto L5;
        return null;
    L5:
        return (String) r0.get(Long.valueOf(r1));
    }
}
