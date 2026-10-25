package com.google.android.gms.internal.vision;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.vision.zziw;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class zziu<T extends zziw<T>> {
    private static final zziu zzd = null;
    final zzlh<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    static {
        zzd = new zziu(true);
    }

    private zziu() {
        this.zza = zzlh.zza(16);
    }

    private zziu(zzlh<T, Object> r1) {
        this.zza = r1;
        zzb();
    }

    private zziu(boolean r1) {
        this(zzlh.zza(0));
        zzb();
    }

    public static int zza(zzml r1, int r2, Object r3) {
        int r22 = zzii.zze(r2);
        if (r1 != zzml.zzj) goto L6;
        zzjf.zza((zzkk) r3);
        r22 = r22 << 1;
    L6:
        return r22 + zza(r1, r3);
    }

    private static int zza(zzml r1, Object r2) {
        switch(zzit.zzb[r1.ordinal()]) {
            case 1: goto L53;
            case 2: goto L51;
            case 3: goto L49;
            case 4: goto L47;
            case 5: goto L45;
            case 6: goto L43;
            case 7: goto L41;
            case 8: goto L39;
            case 9: goto L37;
            case 10: goto L35;
            case 11: goto L29;
            case 12: goto L23;
            case 13: goto L21;
            case 14: goto L19;
            case 15: goto L17;
            case 16: goto L15;
            case 17: goto L13;
            case 18: goto L7;
            default: goto L4;
        };
    L4:
        GlShader$$ExternalSyntheticBUOutline1.m("There is no way to get here, but the compiler thinks otherwise.");
        return 0;
    L7:
        if ((r2 instanceof zzje) == false) goto L11;
        return zzii.zzk(((zzje) r2).zza());
    L11:
        return zzii.zzk(((Integer) r2).intValue());
    L13:
        return zzii.zzf(((Long) r2).longValue());
    L15:
        return zzii.zzh(((Integer) r2).intValue());
    L17:
        return zzii.zzh(((Long) r2).longValue());
    L19:
        return zzii.zzj(((Integer) r2).intValue());
    L21:
        return zzii.zzg(((Integer) r2).intValue());
    L23:
        if ((r2 instanceof zzht) == false) goto L27;
        return zzii.zzb((zzht) r2);
    L27:
        return zzii.zzb((byte[]) r2);
    L29:
        if ((r2 instanceof zzht) == false) goto L33;
        return zzii.zzb((zzht) r2);
    L33:
        return zzii.zzb((String) r2);
    L35:
        return zzii.zzb((zzkk) r2);
    L37:
        return zzii.zzc((zzkk) r2);
    L39:
        return zzii.zzb(((Boolean) r2).booleanValue());
    L41:
        return zzii.zzi(((Integer) r2).intValue());
    L43:
        return zzii.zzg(((Long) r2).longValue());
    L45:
        return zzii.zzf(((Integer) r2).intValue());
    L47:
        return zzii.zze(((Long) r2).longValue());
    L49:
        return zzii.zzd(((Long) r2).longValue());
    L51:
        return zzii.zzb(((Float) r2).floatValue());
    L53:
        return zzii.zzb(((Double) r2).doubleValue());
    }

    private static <T extends zziw<T>> boolean zza(Map.Entry<T, Object> r0) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.getKey());
        throw null;
    }

    private final void zzb(Map.Entry<T, Object> r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.getKey());
        r1.getValue();
        throw null;
    }

    public static int zzc(zziw<?> r3, Object r4) {
        zzml r0 = r3.zzb();
        int r1 = r3.zza();
        if (r3.zzd() == false) goto L18;
        int r2 = 0;
        if (r3.zze() == false) goto L12;
        Iterator r32 = ((List) r4).iterator();
    L8:
        if (r32.hasNext() == false) goto L11;
        r2 = r2 + zza(r0, r32.next());
        goto L8
    L11:
        return (zzii.zze(r1) + r2) + zzii.zzl(r2);
    L12:
        Iterator r33 = ((List) r4).iterator();
    L14:
        if (r33.hasNext() == false) goto L16;
        r2 = r2 + zza(r0, r1, r33.next());
        goto L14
    L16:
        return r2;
    L18:
        return zza(r0, r1, r4);
    }

    private static int zzc(Map.Entry<T, Object> r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.getKey());
        r1.getValue();
        throw null;
    }

    private static void zzd(T r3, Object r4) {
        zzml r0 = r3.zzb();
        zzjf.zza(r4);
        boolean r1 = true;
        switch(zzit.zza[r0.zza().ordinal()]) {
            case 1: goto L23;
            case 2: goto L22;
            case 3: goto L21;
            case 4: goto L20;
            case 5: goto L19;
            case 6: goto L18;
            case 7: goto L14;
            case 8: goto L9;
            case 9: goto L6;
            default: goto L4;
        };
    L4:
        r1 = false;
    L24:
        if (r1 == false) goto L26;
        return;
    L26:
        FieldSet$$ExternalSyntheticBUOutline0.m("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(r3.zza()), r3.zzb().zza(), r4.getClass().getName()});
        return;
    L18:
        r1 = r4 instanceof String;
        goto L24
    L19:
        r1 = r4 instanceof Boolean;
        goto L24
    L20:
        r1 = r4 instanceof Double;
        goto L24
    L21:
        r1 = r4 instanceof Float;
        goto L24
    L22:
        r1 = r4 instanceof Long;
        goto L24
    L23:
        r1 = r4 instanceof Integer;
        goto L24
    L6:
        if ((r4 instanceof zzkk) == true) goto L24;
    L9:
        if ((r4 instanceof Integer) == true) goto L24;
        if ((r4 instanceof zzje) == false) goto L4;
    L14:
        if ((r4 instanceof zzht) == true) goto L24;
        if ((r4 instanceof byte[]) == false) goto L4;
        goto L4
    }

    public final /* synthetic */ Object clone() {
        zziu r0 = new zziu();
        int r1 = 0;
    L3:
        int r2 = this.zza.zzc();
        zzlh<T, Object> r3 = this.zza;
        if (r1 >= r2) goto L6;
        Map.Entry<K, Object> r22 = r3.zzb(r1);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.getKey());
        r0.zza(null, r22.getValue());
        r1 = r1 + 1;
        goto L3
    L6:
        Iterator r12 = r3.zzd().iterator();
    L8:
        if (r12.hasNext() == false) goto L10;
        Map.Entry r23 = (Map.Entry) r12.next();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r23.getKey());
        r0.zza(null, r23.getValue());
        goto L8
    L10:
        r0.zzc = this.zzc;
        return r0;
    }

    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof zziu) == true) goto L10;
        return false;
    L10:
        return this.zza.equals(((zziu) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zza(zziu<T> r4) {
        int r0 = 0;
    L3:
        int r1 = r4.zza.zzc();
        zzlh<T, Object> r2 = r4.zza;
        if (r0 >= r1) goto L6;
        zzb(r2.zzb(r0));
        r0 = r0 + 1;
        goto L3
    L6:
        Iterator r42 = r2.zzd().iterator();
    L8:
        if (r42.hasNext() == false) goto L10;
        zzb((Map.Entry) r42.next());
        goto L8
    }

    public final void zza(T r4, Object r5) {
        if (r4.zzd() == true) goto L5;
        zzd(r4, r5);
    L13:
        this.zza.zza(r4, r5);
        return;
    L5:
        if ((r5 instanceof List) == false) goto L10;
        ArrayList r0 = new ArrayList();
        r0.addAll((List) r5);
        int r52 = r0.size();
        int r1 = 0;
    L7:
        if (r1 >= r52) goto L9;
        Object r2 = r0.get(r1);
        r1 = r1 + 1;
        zzd(r4, r2);
        goto L7
    L9:
        r5 = r0;
        goto L13
    L10:
        f$$ExternalSyntheticBUOutline1.m("Wrong object type used with protocol message reflection.");
    }

    public final void zzb() {
        if (this.zzb == false) goto L5;
        return;
    L5:
        this.zza.zza();
        this.zzb = true;
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        boolean r0 = this.zzc;
        zzlh<T, Object> r1 = this.zza;
        if (r0 == false) goto L7;
        return new zzjq(r1.entrySet().iterator());
    L7:
        return r1.entrySet().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zze() {
        boolean r0 = this.zzc;
        zzlh<T, Object> r1 = this.zza;
        if (r0 == false) goto L7;
        return new zzjq(r1.zze().iterator());
    L7:
        return r1.zze().iterator();
    }

    public final boolean zzf() {
        int r1 = 0;
    L3:
        int r2 = this.zza.zzc();
        zzlh<T, Object> r3 = this.zza;
        if (r1 >= r2) goto L9;
        if (zza(r3.zzb(r1)) == false) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return false;
    L9:
        Iterator r4 = r3.zzd().iterator();
    L11:
        if (r4.hasNext() == false) goto L15;
        if (zza((Map.Entry) r4.next()) == true) goto L11;
        return false;
    L15:
        return true;
    }

    public final int zzg() {
        int r0 = 0;
        int r1 = 0;
    L3:
        int r2 = this.zza.zzc();
        zzlh<T, Object> r3 = this.zza;
        if (r0 >= r2) goto L6;
        r1 = r1 + zzc(r3.zzb(r0));
        r0 = r0 + 1;
        goto L3
    L6:
        Iterator r4 = r3.zzd().iterator();
    L8:
        if (r4.hasNext() == false) goto L10;
        r1 = r1 + zzc((Map.Entry) r4.next());
        goto L8
    L10:
        return r1;
    }
}
