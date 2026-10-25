package j$.time;

import j$.time.zone.ZoneRules;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class r extends ZoneId {
    public static final /* synthetic */ int d = 0;
    private static final long serialVersionUID = 8386373296231747096L;

    /* renamed from: b, reason: collision with root package name */
    public final String f439b;

    /* renamed from: c, reason: collision with root package name */
    public final transient ZoneRules f440c;

    public r(String r1, ZoneRules r2) {
        this.f439b = r1;
        this.f440c = r2;
    }

    public static r Y(String r6, boolean r7) {
        Objects.requireNonNull(r6, "zoneId");
        int r0 = r6.length();
        ZoneRules r2 = null;
        if (r0 < 2) goto L55;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L57;
        char r4 = r6.charAt(r1);
        if (r4 < 'a') goto L12;
        if (r4 > 'z') goto L12;
    L45:
        r1 = r1 + 1;
    L12:
        if (r4 < 'A') goto L17;
        if (r4 <= 'Z') goto L45;
    L17:
        if (r4 != '/') goto L21;
        if (r1 != 0) goto L45;
    L21:
        if (r4 < '0') goto L27;
        if (r4 > '9') goto L27;
        if (r1 != 0) goto L45;
    L27:
        if (r4 != '~') goto L31;
        if (r1 != 0) goto L45;
    L31:
        if (r4 != '.') goto L35;
        if (r1 != 0) goto L45;
    L35:
        if (r4 != '_') goto L39;
        if (r1 != 0) goto L45;
    L39:
        if (r4 != '+') goto L43;
        if (r1 != 0) goto L45;
    L43:
        if (r4 != '-') goto L46;
        if (r1 != 0) goto L45;
    L46:
        j$.nio.file.b.h("Invalid ID for region-based ZoneId, invalid format: ".concat(r6));
        return null;
    L57:
        r2 = j$.time.zone.g.a(r6);     // Catch: j$.time.zone.f -> L50
    L53:
        return new r(r6, r2);
    L50:
        e = move-exception;
        if (r7 == false) goto L53;
        throw e;
    L55:
        j$.nio.file.b.h("Invalid ID for region-based ZoneId, invalid format: ".concat(r6));
        return null;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 7, this);
    }

    @Override // j$.time.ZoneId
    public final void S(DataOutput r2) {
        r2.writeByte(7);
        r2.writeUTF(this.f439b);
    }

    @Override // j$.time.ZoneId
    public final String getId() {
        return this.f439b;
    }

    @Override // j$.time.ZoneId
    public final ZoneRules getRules() {
        ZoneRules r0 = this.f440c;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return j$.time.zone.g.a(this.f439b);
    }
}
