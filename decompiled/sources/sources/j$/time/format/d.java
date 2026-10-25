package j$.time.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class d implements e {

    /* renamed from: a, reason: collision with root package name */
    public final e[] f381a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f382b;

    public d(List r2, boolean r3) {
        ArrayList r22 = (ArrayList) r2;
        this((e[]) r22.toArray(new e[r22.size()]), r3);
    }

    public d(e[] r1, boolean r2) {
        this.f381a = r1;
        this.f382b = r2;
    }

    @Override // j$.time.format.e
    public final int D(r r7, CharSequence r8, int r9) {
        boolean r0 = this.f382b;
        e[] r6 = this.f381a;
        int r1 = 0;
        if (r0 == false) goto L13;
        ArrayList r02 = r7.d;
        y r2 = r7.c();
        r2.getClass();
        y r3 = new y();
        ((HashMap) r3.f418a).putAll(r2.f418a);
        r3.f419b = r2.f419b;
        r3.f420c = r2.f420c;
        r3.d = r2.d;
        r02.add(r3);
        int r03 = r6.length;
        int r22 = r9;
    L5:
        if (r1 >= r03) goto L11;
        r22 = r6[r1].D(r7, r8, r22);
        if (r22 < 0) goto L8;
        r1 = r1 + 1;
        goto L5
    L8:
        r7.d.remove(r6.size() - 1);
        return r9;
    L11:
        r7.d.remove(r6.size() - 2);
        return r22;
    L13:
        int r04 = r6.length;
    L14:
        if (r1 >= r04) goto L19;
        r9 = r6[r1].D(r7, r8, r9);
        if (r9 < 0) goto L17;
        r1 = r1 + 1;
        goto L14
    L17:
        return r9;
    L19:
        return r9;
    }

    @Override // j$.time.format.e
    public final boolean s(t r7, StringBuilder r8) {
        int r0 = r8.length();
        boolean r2 = this.f382b;
        if (r2 == false) goto L22;
        r7.f411c++;
    L22:
        e[] r6 = this.f381a;     // Catch: Throwable -> L13
        int r3 = r6.length;     // Catch: Throwable -> L13
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L16;
        if (r6[r4].s(r7, r8) == false) goto L9;
        r4 = r4 + 1;
        goto L6
    L9:
        r8.setLength(r0);     // Catch: Throwable -> L13
        if (r2 == false) goto L18;
    L11:
        r7.f411c--;
        return true;
    L18:
        return true;
    L16:
        if (r2 == false) goto L18;
    L13:
        th = move-exception;
        if (r2 == false) goto L21;
        r7.f411c--;
    L21:
        throw th;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder();
        e[] r1 = this.f381a;
        if (r1 == null) goto L16;
        boolean r5 = this.f382b;
        if (r5 == false) goto L7;
        String r2 = "[";
    L8:
        r0.append(r2);
        int r22 = r1.length;
        int r3 = 0;
    L9:
        if (r3 >= r22) goto L11;
        r0.append(r1[r3]);
        r3 = r3 + 1;
        goto L9
    L11:
        if (r5 == false) goto L13;
        String r52 = "]";
    L14:
        r0.append(r52);
        goto L16
    L13:
        r52 = ")";
        goto L14
    L7:
        r2 = "(";
    L16:
        return r0.toString();
    }
}
