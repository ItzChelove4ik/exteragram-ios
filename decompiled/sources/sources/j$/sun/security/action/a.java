package j$.sun.security.action;

import j$.time.zone.g;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class a implements PrivilegedAction {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f278a;

    /* renamed from: b, reason: collision with root package name */
    public Object f279b;

    public /* synthetic */ a() {
        this.f278a = 0;
    }

    public a(List r2) {
        this.f278a = 1;
        this.f279b = r2;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        switch(this.f278a) {
            case 0: goto L13;
            default: goto L4;
        };
    L4:
        String r2 = System.getProperty("java.time.zone.DefaultZoneRulesProvider");
        if (r2 != null) goto L18;
        g.b(new g());
    L12:
        return null;
    L18:
        g r0 = (g) g.class.cast(Class.forName(r2, true, g.class.getClassLoader()).newInstance());     // Catch: Exception -> L8
        g.b(r0);     // Catch: Exception -> L8
        ((ArrayList) ((List) this.f279b)).add(r0);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        throw new Error(e);
    L13:
        String r5 = System.getProperty((String) this.f279b);
        if (r5 != null) goto L17;
        return null;
    L17:
        return r5;
    }
}
