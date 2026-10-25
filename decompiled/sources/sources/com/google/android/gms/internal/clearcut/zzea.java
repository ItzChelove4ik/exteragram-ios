package com.google.android.gms.internal.clearcut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes4.dex */
final class zzea {
    private static final zzea zznc = null;
    private final zzeg zznd;
    private final ConcurrentMap<Class<?>, zzef<?>> zzne;

    static {
        zznc = new zzea();
    }

    private zzea() {
        this.zzne = new ConcurrentHashMap();
        String[] r0 = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzeg r1 = null;
        int r3 = 0;
    L3:
        if (r3 > 0) goto L7;
        r1 = zzk(r0[0]);
        if (r1 != null) goto L7;
        r3 = r3 + 1;
    L7:
        if (r1 != null) goto L9;
        r1 = new zzdd();
    L9:
        this.zznd = r1;
    }

    public static zzea zzcm() {
        return zznc;
    }

    private static zzeg zzk(String r1) {
        return (zzeg) Class.forName(r1).getConstructor(null).newInstance(null);
    L5:
        return null;
    }

    public final <T> zzef<T> zze(Class<T> r3) {
        zzci.zza(r3, "messageType");
        zzef<T> r1 = (zzef) this.zzne.get(r3);
        if (r1 != null) goto L7;
        r1 = this.zznd.zzd(r3);
        zzci.zza(r3, "messageType");
        zzci.zza(r1, "schema");
        zzef<T> r2 = (zzef) this.zzne.putIfAbsent(r3, r1);
        if (r2 == null) goto L7;
        return r2;
    L7:
        return r1;
    }

    public final <T> zzef<T> zzp(T r1) {
        return zze(r1.getClass());
    }
}
