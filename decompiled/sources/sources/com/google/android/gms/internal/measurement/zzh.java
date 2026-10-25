package com.google.android.gms.internal.measurement;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzh {
    public static void zza(String r1, int r2, List r3) {
        if (r3.size() != r2) goto L5;
        return;
    L5:
        FieldSet$$ExternalSyntheticBUOutline0.m("%s operation requires %s parameters found %s", new Object[]{r1, Integer.valueOf(r2), Integer.valueOf(r3.size())});
    }

    public static void zzb(String r1, int r2, List r3) {
        if (r3.size() < r2) goto L5;
        return;
    L5:
        FieldSet$$ExternalSyntheticBUOutline0.m("%s operation requires at least %s parameters found %s", new Object[]{r1, Integer.valueOf(r2), Integer.valueOf(r3.size())});
    }

    public static void zzc(String r1, int r2, List r3) {
        if (r3.size() > r2) goto L5;
        return;
    L5:
        FieldSet$$ExternalSyntheticBUOutline0.m("%s operation requires at most %s parameters found %s", new Object[]{r1, Integer.valueOf(r2), Integer.valueOf(r3.size())});
    }

    public static boolean zzd(zzao r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        Double r52 = r5.zzd();
        if (r52.isNaN() == false) goto L8;
    L13:
        return false;
    L8:
        if (r52.doubleValue() < 0.0d) goto L13;
        if (r52.equals(Double.valueOf(Math.floor(r52.doubleValue()))) == false) goto L13;
        return true;
    }

    public static zzbk zze(String r2) {
        if (r2 != null) goto L5;
    L7:
        zzbk r1 = null;
    L8:
        if (r1 == null) goto L10;
        return r1;
    L10:
        FieldSet$$ExternalSyntheticBUOutline0.m("Unsupported commandId %s", new Object[]{r2});
        return null;
    L5:
        if (r2.isEmpty() == true) goto L7;
        r1 = zzbk.zza(Integer.parseInt(r2));
        goto L8
    }

    public static boolean zzf(zzao r4, zzao r5) {
        if (r4.getClass().equals(r5.getClass()) == true) goto L6;
        return false;
    L6:
        if ((r4 instanceof zzat) == false) goto L8;
    L31:
        return true;
    L8:
        if ((r4 instanceof zzam) == true) goto L31;
        if ((r4 instanceof zzah) == false) goto L21;
        if (Double.isNaN(r4.zzd().doubleValue()) == false) goto L15;
    L19:
        return false;
    L15:
        if (Double.isNaN(r5.zzd().doubleValue()) == true) goto L19;
        return r4.zzd().equals(r5.zzd());
    L21:
        if ((r4 instanceof zzas) == false) goto L25;
        return r4.zzc().equals(r5.zzc());
    L25:
        if ((r4 instanceof zzaf) == true) goto L27;
        if (r4 != r5) goto L30;
        return true;
    L30:
        return false;
    L27:
        return r4.zze().equals(r5.zze());
    }

    public static int zzg(double r2) {
        if (Double.isNaN(r2) == false) goto L5;
        return 0;
    L5:
        if (Double.isInfinite(r2) == false) goto L7;
        return 0;
    L7:
        if (r2 == 0.0d) goto L17;
        if (r2 <= 0.0d) goto L11;
        int r0 = 1;
    L12:
        double r02 = r0;
        return (int) ((r02 * Math.floor(Math.abs(r2))) % 4.294967296E9d);
    L11:
        r0 = -1;
        goto L12
    L17:
        return 0;
    }

    public static long zzh(double r2) {
        return zzg(r2) & 4294967295L;
    }

    public static double zzi(double r3) {
        if (Double.isNaN(r3) == false) goto L6;
        return 0.0d;
    L6:
        if (Double.isInfinite(r3) == false) goto L8;
    L16:
        return r3;
    L8:
        if (r3 == 0.0d) goto L16;
        if (r3 == 0.0d) goto L16;
        if (r3 <= 0.0d) goto L13;
        int r0 = 1;
    L15:
        return r0 * Math.floor(Math.abs(r3));
    L13:
        r0 = -1;
        goto L15
    }

    public static Object zzj(zzao r2) {
        if (zzao.zzg.equals(r2) == false) goto L7;
        return null;
    L7:
        if (zzao.zzf.equals(r2) == false) goto L11;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        if ((r2 instanceof zzal) == false) goto L15;
        return zzk((zzal) r2);
    L15:
        if ((r2 instanceof zzae) == false) goto L24;
        ArrayList r0 = new ArrayList();
        Iterator r22 = ((zzae) r2).iterator();
    L18:
        if (r22.hasNext() == false) goto L22;
        Object r1 = zzj((zzao) r22.next());
        if (r1 == null) goto L18;
        r0.add(r1);
        goto L18
    L22:
        return r0;
    L24:
        if (r2.zzd().isNaN() == true) goto L28;
        return r2.zzd();
    L28:
        return r2.zzc();
    }

    public static Map zzk(zzal r4) {
        HashMap r0 = new HashMap();
        Iterator r1 = r4.zzb().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        String r2 = (String) r1.next();
        Object r3 = zzj(r4.zzk(r2));
        if (r3 == null) goto L4;
        r0.put(r2, r3);
        goto L4
    L8:
        return r0;
    }

    public static int zzl(zzg r5) {
        int r1 = zzg(r5.zzh("runtime.counter").zzd().doubleValue() + 1.0d);
        if (r1 > 1000000) goto L6;
        r5.zze("runtime.counter", new zzah(Double.valueOf(r1)));
        return r1;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Instructions allowed exceeded");
        return 0;
    }
}
