package j$.time.zone;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class g {

    /* renamed from: b, reason: collision with root package name */
    public static final CopyOnWriteArrayList f482b = null;

    /* renamed from: c, reason: collision with root package name */
    public static final ConcurrentMap f483c = null;
    public static volatile Set d;

    /* renamed from: a, reason: collision with root package name */
    public final Set f484a;

    static {
        CopyOnWriteArrayList r0 = new CopyOnWriteArrayList();
        f482b = r0;
        f483c = new ConcurrentHashMap(512, 0.75f, 2);
        ArrayList r1 = new ArrayList();
        AccessController.doPrivileged(new j$.sun.security.action.a(r1));
        r0.addAll(r1);
    }

    public g() {
        LinkedHashSet r0 = new LinkedHashSet();
        String[] r1 = TimeZone.getAvailableIDs();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        r0.add(r1[r3]);
        r3 = r3 + 1;
        goto L3
    L5:
        this.f484a = Collections.unmodifiableSet(r0);
    }

    public static ZoneRules a(String r2) {
        Objects.requireNonNull(r2, "zoneId");
        ConcurrentHashMap r0 = (ConcurrentHashMap) f483c;
        g r1 = (g) r0.get(r2);
        if (r1 != null) goto L11;
        if (r0.isEmpty() == false) goto L9;
        throw new f("No time-zone data files registered");
    L9:
        throw new f("Unknown time-zone ID: ".concat(r2));
    L11:
        if (r1.f484a.contains(r2) == false) goto L15;
        return new ZoneRules(TimeZone.getTimeZone(r2));
    L15:
        throw new f("Not a built-in time zone: ".concat(r2));
    }

    public static void b(g r5) {
        Objects.requireNonNull(r5, "provider");
        monitor-enter(g.class);
        Iterator r1 = r5.f484a.iterator();     // Catch: Throwable -> L12
    L6:
        if (r1.hasNext() == false) goto L14;
        String r2 = (String) r1.next();     // Catch: Throwable -> L12
        Objects.requireNonNull(r2, "zoneId");     // Catch: Throwable -> L12
        if (((g) ((ConcurrentHashMap) f483c).putIfAbsent(r2, r5)) == null) goto L6;
        throw new f("Unable to register zone as one already registered with that ID: " + r2 + ", currently loading from provider: " + r5);     // Catch: Throwable -> L12
    L14:
        d = Collections.unmodifiableSet(new HashSet(((ConcurrentHashMap) f483c).keySet()));     // Catch: Throwable -> L12
        monitor-exit(g.class);
        f482b.add(r5);
        return;
    L12:
        th = move-exception;
        throw th;
    }
}
