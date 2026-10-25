package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzadk {
    private static final zzadk zzd = null;
    final zzafv zza;
    boolean zzb;
    boolean zzc;

    static {
        zzd = new zzadk(true);
    }

    private zzadk() {
        this.zza = new zzafr();
    }

    private zzadk(boolean r1) {
        this.zza = new zzafr();
        zzb();
        zzb();
    }

    public static void zzf(zzada r2, zzagm r3, int r4, Object r5) {
        if (r3 == zzagm.zzj) goto L55;
        r2.zzb(r4, r3.zzb());
        zzagn r42 = zzagn.zza;
        switch(r3.ordinal()) {
            case 0: goto L53;
            case 1: goto L51;
            case 2: goto L49;
            case 3: goto L47;
            case 4: goto L45;
            case 5: goto L43;
            case 6: goto L41;
            case 7: goto L39;
            case 8: goto L34;
            case 9: goto L31;
            case 10: goto L29;
            case 11: goto L24;
            case 12: goto L21;
            case 13: goto L16;
            case 14: goto L13;
            case 15: goto L11;
            case 16: goto L9;
            case 17: goto L7;
            default: goto L6;
        };
    L6:
        return;
    L7:
        long r32 = ((Long) r5).longValue();
        long r0 = r32 + r32;
        r2.zzt((r32 >> 63) ^ r0);
        return;
    L9:
        int r33 = ((Integer) r5).intValue();
        int r43 = r33 + r33;
        r2.zzr((r33 >> 31) ^ r43);
        return;
    L11:
        r2.zzu(((Long) r5).longValue());
        return;
    L13:
        r2.zzs(((Integer) r5).intValue());
        return;
    L21:
        r2.zzr(((Integer) r5).intValue());
        return;
    L29:
        r2.zzo((zzafc) r5);
        return;
    L31:
        ((zzafc) r5).zzcH(r2);
        return;
    L39:
        r2.zzp(((Boolean) r5).booleanValue() ? 1 : 0);
        return;
    L41:
        r2.zzs(((Integer) r5).intValue());
        return;
    L43:
        r2.zzu(((Long) r5).longValue());
        return;
    L45:
        r2.zzq(((Integer) r5).intValue());
        return;
    L47:
        r2.zzt(((Long) r5).longValue());
        return;
    L49:
        r2.zzt(((Long) r5).longValue());
        return;
    L51:
        r2.zzs(Float.floatToRawIntBits(((Float) r5).floatValue()));
        return;
    L53:
        r2.zzu(Double.doubleToRawLongBits(((Double) r5).doubleValue()));
        return;
    L16:
        if ((r5 instanceof zzady) == false) goto L19;
        r2.zzq(((zzady) r5).zza());
        return;
    L19:
        r2.zzq(((Integer) r5).intValue());
        return;
    L24:
        if ((r5 instanceof zzacr) == false) goto L27;
        r2.zzk((zzacr) r5);
        return;
    L27:
        byte[] r52 = (byte[]) r5;
        r2.zzl(r52, 0, r52.length);
        return;
    L34:
        if ((r5 instanceof zzacr) == false) goto L37;
        r2.zzk((zzacr) r5);
        return;
    L37:
        r2.zzw((String) r5);
        return;
    L55:
        r2.zzb(r4, 3);
        ((zzafc) r5).zzcH(r2);
        r2.zzb(r4, 4);
    }

    public static int zzh(zzagm r1, int r2, Object r3) {
        int r22 = zzada.zzE(r2 << 3);
        if (r1 != zzagm.zzj) goto L6;
        r22 = r22 + r22;
    L6:
        return r22 + zzi(r1, r3);
    }

    public static int zzi(zzagm r3, Object r4) {
        zzagm r0 = zzagm.zza;
        zzagn r02 = zzagn.zza;
        switch(r3.ordinal()) {
            case 0: goto L50;
            case 1: goto L48;
            case 2: goto L47;
            case 3: goto L45;
            case 4: goto L43;
            case 5: goto L40;
            case 6: goto L38;
            case 7: goto L36;
            case 8: goto L33;
            case 9: goto L31;
            case 10: goto L29;
            case 11: goto L23;
            case 12: goto L21;
            case 13: goto L15;
            case 14: goto L12;
            case 15: goto L10;
            case 16: goto L8;
            case 17: goto L6;
            default: goto L4;
        };
    L4:
        GlShader$$ExternalSyntheticBUOutline1.m("There is no way to get here, but the compiler thinks otherwise.");
        return 0;
    L6:
        long r32 = ((Long) r4).longValue();
        return zzada.zzF((r32 >> 63) ^ (r32 + r32));
    L8:
        int r33 = ((Integer) r4).intValue();
        return zzada.zzE((r33 >> 31) ^ (r33 + r33));
    L10:
        ((Long) r4).getClass();
        int r34 = zzada.$r8$clinit;
        return 8;
    L12:
        ((Integer) r4).getClass();
        int r35 = zzada.$r8$clinit;
        return 4;
    L36:
        ((Boolean) r4).getClass();
        int r36 = zzada.$r8$clinit;
        return 1;
    L38:
        ((Integer) r4).getClass();
        int r37 = zzada.$r8$clinit;
        return 4;
    L40:
        ((Long) r4).getClass();
        int r38 = zzada.$r8$clinit;
        return 8;
    L48:
        ((Float) r4).getClass();
        int r39 = zzada.$r8$clinit;
        return 4;
    L50:
        ((Double) r4).getClass();
        int r310 = zzada.$r8$clinit;
        return 8;
    L15:
        if ((r4 instanceof zzady) == false) goto L19;
        return zzada.zzF(((zzady) r4).zza());
    L19:
        return zzada.zzF(((Integer) r4).intValue());
    L21:
        return zzada.zzE(((Integer) r4).intValue());
    L23:
        if ((r4 instanceof zzacr) == false) goto L27;
        int r311 = zzada.$r8$clinit;
        int r312 = ((zzacr) r4).zzb();
        int r42 = zzada.zzE(r312);
    L26:
        return r42 + r312;
    L27:
        int r313 = zzada.$r8$clinit;
        r312 = ((byte[]) r4).length;
        r42 = zzada.zzE(r312);
        goto L26
    L29:
        return zzada.zzG((zzafc) r4);
    L31:
        return ((zzafc) r4).zzcq();
    L33:
        if ((r4 instanceof zzacr) == false) goto L35;
        int r314 = zzada.$r8$clinit;
        r312 = ((zzacr) r4).zzb();
        r42 = zzada.zzE(r312);
        goto L26
    L35:
        int r315 = zzada.$r8$clinit;
        r312 = zzagl.zzb((String) r4);
        r42 = zzada.zzE(r312);
        goto L26
    L43:
        return zzada.zzF(((Integer) r4).intValue());
    L45:
        return zzada.zzF(((Long) r4).longValue());
    L47:
        return zzada.zzF(((Long) r4).longValue());
    }

    private static final void zzn(zzadj r2, Object r3) {
        zzagm r0 = r2.zzb();
        r3.getClass();
        zzagm r1 = zzagm.zza;
        zzagn r12 = zzagn.zza;
        switch(r0.zza().ordinal()) {
            case 0: goto L23;
            case 1: goto L22;
            case 2: goto L21;
            case 3: goto L20;
            case 4: goto L19;
            case 5: goto L18;
            case 6: goto L14;
            case 7: goto L9;
            case 8: goto L6;
            default: goto L26;
        };
    L18:
        boolean r02 = r3 instanceof String;
    L24:
        if (r02 == false) goto L26;
        return;
    L19:
        r02 = r3 instanceof Boolean;
        goto L24
    L20:
        r02 = r3 instanceof Double;
        goto L24
    L21:
        r02 = r3 instanceof Float;
        goto L24
    L22:
        r02 = r3 instanceof Long;
        goto L24
    L23:
        r02 = r3 instanceof Integer;
    L26:
        FieldSet$$ExternalSyntheticBUOutline0.m("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(r2.zza()), r2.zzb().zza(), r3.getClass().getName()});
        return;
    L6:
        if ((r3 instanceof zzafc) == false) goto L26;
        return;
    L9:
        if ((r3 instanceof Integer) == false) goto L11;
        return;
    L11:
        if ((r3 instanceof zzady) == false) goto L26;
        return;
    L14:
        if ((r3 instanceof zzacr) == false) goto L16;
        return;
    L16:
        if ((r3 instanceof byte[]) == false) goto L26;
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        zzadk r0 = new zzadk();
        zzafv r1 = this.zza;
        int r2 = r1.zzc();
        int r3 = 0;
    L4:
        if (r3 >= r2) goto L6;
        Map.Entry r5 = r1.zzd(r3);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((zzafs) r5).zza());
        r0.zzd(null, r5.getValue());
        r3 = r3 + 1;
        goto L4
    L6:
        Iterator r12 = r1.zze().iterator();
    L8:
        if (r12.hasNext() == false) goto L10;
        Map.Entry r22 = (Map.Entry) r12.next();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.getKey());
        r0.zzd(null, r22.getValue());
        goto L8
    L10:
        r0.zzc = this.zzc;
        return r0;
    }

    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzadk) == true) goto L10;
        return false;
    L10:
        return this.zza.equals(((zzadk) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzb == false) goto L5;
        return;
    L5:
        zzafv r0 = this.zza;
        int r1 = r0.zzc();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        Object r3 = r0.zzd(r2).getValue();
        if ((r3 instanceof zzadu) == false) goto L10;
        ((zzadu) r3).zzcm();
    L10:
        r2 = r2 + 1;
        goto L6
    L11:
        Iterator r12 = r0.zze().iterator();
    L13:
        if (r12.hasNext() == false) goto L17;
        Object r22 = ((Map.Entry) r12.next()).getValue();
        if ((r22 instanceof zzadu) == false) goto L13;
        ((zzadu) r22).zzcm();
        goto L13
    L17:
        r0.zza();
        this.zzb = true;
    }

    public final void zzd(zzadj r5, Object r6) {
        if (r5.zzd() == true) goto L5;
        zzn(r5, r6);
    L13:
        this.zza.zzf(r5, r6);
        return;
    L5:
        if ((r6 instanceof List) == false) goto L10;
        List r62 = (List) r6;
        int r0 = r62.size();
        ArrayList r1 = new ArrayList(r0);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        Object r3 = r62.get(r2);
        zzn(r5, r3);
        r1.add(r3);
        r2 = r2 + 1;
        goto L7
    L9:
        r6 = r1;
        goto L13
    L10:
        f$$ExternalSyntheticBUOutline1.m("Wrong object type used with protocol message reflection.");
    }
}
