package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzahn;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzaa extends zzab {
    final /* synthetic */ zzad zza;
    private final com.google.android.gms.internal.measurement.zzff zzh;

    public zzaa(zzad r1, String r2, int r3, com.google.android.gms.internal.measurement.zzff r4) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2, r3);
        this.zzh = r4;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    public final int zza() {
        return this.zzh.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    public final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    public final boolean zzc() {
        return this.zzh.zzh();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zzd(Long r17, Long r18, com.google.android.gms.internal.measurement.zzhs r19, long r20, zzbd r22, boolean r23) {
        zzahn.zza();
        zzad r1 = this.zza;
        zzic r2 = r1.zzu;
        zzal r3 = r2.zzc();
        String r4 = this.zzb;
        boolean r32 = r3.zzp(r4, zzfy.zzaF);
        com.google.android.gms.internal.measurement.zzff r5 = this.zzh;
        if (r5.zzm() == false) goto L5;
        long r6 = r22.zze;
    L6:
        Integer r9 = null;
        Integer r92 = null;
        Boolean r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        r93 = null;
        if (Log.isLoggable(r2.zzaW().zzn(), 2) == false) goto L14;
        zzgs r8 = r2.zzaW().zzk();
        Integer r10 = Integer.valueOf(this.zzc);
        if (r5.zza() == false) goto L11;
        Integer r11 = Integer.valueOf(r5.zzb());
    L12:
        r8.zzd("Evaluating filter. audience, filter, event", r10, r11, r2.zzl().zza(r5.zzc()));
        r2.zzaW().zzk().zzb("Filter definition", r1.zzg.zzp().zzj(r5));
        goto L14
    L11:
        r11 = null;
    L14:
        if (r5.zza() == true) goto L16;
    L153:
        zzgs r0 = r2.zzaW().zze();
        Object r12 = zzgu.zzl(r4);
        if (r5.zza() == false) goto L156;
        r9 = Integer.valueOf(r5.zzb());
    L156:
        r0.zzc("Invalid event filter ID. appId, id", r12, String.valueOf(r9));
        return false;
    L16:
        if (r5.zzb() > 256) goto L153;
        boolean r13 = r5.zzj();
        boolean r42 = r5.zzk();
        boolean r102 = r5.zzm();
        if (r13 == true) goto L22;
        if (r42 == true) goto L22;
        if (r102 == true) goto L22;
        Object[] r14 = false;
    L24:
        if (r23 == false) goto L31;
        if (r14 == true) goto L31;
        zzgs r15 = r2.zzaW().zzk();
        Integer r02 = Integer.valueOf(this.zzc);
        if (r5.zza() == false) goto L29;
        r92 = Integer.valueOf(r5.zzb());
    L29:
        r15.zzc("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", r02, r92);
        return true;
    L31:
        String r43 = r19.zzd();
        if (r5.zzh() == false) goto L39;
        Boolean r62 = zzab.zzg(r6, r5.zzi());
        if (r62 != null) goto L37;
    L124:
        zzgs r24 = r2.zzaW().zzk();
        if (r93 != null) goto L127;
        Object r44 = "null";
    L128:
        r24.zzb("Event filter result", r44);
        if (r93 != null) goto L131;
        return false;
    L131:
        Boolean r25 = Boolean.TRUE;
        this.zzd = r25;
        if (r93.booleanValue() == true) goto L134;
        return true;
    L134:
        this.zze = r25;
        if (r14 == true) goto L137;
    L152:
        return true;
    L137:
        if (r19.zze() == false) goto L152;
        Long r16 = Long.valueOf(r19.zzf());
        if (r5.zzk() == false) goto L146;
        if (r32 == true) goto L142;
    L145:
        this.zzg = r16;
        goto L152
    L142:
        if (r5.zzh() == false) goto L145;
        r16 = r17;
        goto L145
    L146:
        if (r32 == true) goto L148;
    L151:
        this.zzf = r16;
        goto L152
    L148:
        if (r5.zzh() == false) goto L151;
        r16 = r18;
        goto L151
    L127:
        r44 = r93;
        goto L128
    L37:
        if (r62.booleanValue() == true) goto L39;
        r93 = Boolean.FALSE;
    L39:
        HashSet r63 = new HashSet();
        Iterator r7 = r5.zzd().iterator();
    L41:
        if (r7.hasNext() == false) goto L46;
        com.google.android.gms.internal.measurement.zzfh r103 = (com.google.android.gms.internal.measurement.zzfh) r7.next();
        if (r103.zzi().isEmpty() == true) goto L44;
        r63.add(r103.zzi());
        goto L41
    L44:
        r2.zzaW().zze().zzb("null or empty param name in filter. event", r2.zzl().zza(r43));
        goto L124
    L46:
        ArrayMap r72 = new ArrayMap();
        Iterator r104 = r19.zza().iterator();
    L48:
        if (r104.hasNext() == false) goto L69;
        com.google.android.gms.internal.measurement.zzhw r122 = (com.google.android.gms.internal.measurement.zzhw) r104.next();
        if (r63.contains(r122.zzb()) == false) goto L48;
        if (r122.zze() == true) goto L53;
        if (r122.zzj() == true) goto L60;
        if (r122.zzc() == false) goto L68;
        r72.put(r122.zzb(), r122.zzd());
        goto L48
    L68:
        r2.zzaW().zze().zzc("Unknown value for param. event, param", r2.zzl().zza(r43), r2.zzl().zzb(r122.zzb()));
        goto L124
    L60:
        String r132 = r122.zzb();
        if (r122.zzj() == false) goto L63;
        Double r123 = Double.valueOf(r122.zzk());
    L64:
        r72.put(r132, r123);
        goto L48
    L63:
        r123 = null;
        goto L64
    L53:
        String r133 = r122.zzb();
        if (r122.zze() == false) goto L56;
        Long r124 = Long.valueOf(r122.zzf());
    L57:
        r72.put(r133, r124);
        goto L48
    L56:
        r124 = null;
        goto L57
    L69:
        Iterator r64 = r5.zzd().iterator();
    L71:
        if (r64.hasNext() == false) goto L123;
        com.google.android.gms.internal.measurement.zzfh r105 = (com.google.android.gms.internal.measurement.zzfh) r64.next();
        if (r105.zze() == true) goto L75;
    L77:
        boolean r125 = false;
    L78:
        String r134 = r105.zzi();
        if (r134.isEmpty() == true) goto L80;
        V r142 = r72.get(r134);
        if ((r142 instanceof Long) == true) goto L84;
        if ((r142 instanceof Double) == true) goto L95;
        if ((r142 instanceof String) == false) goto L120;
        if (r105.zza() == false) goto L109;
        Boolean r106 = zzab.zzf((String) r142, r105.zzb(), r2.zzaW());
    L113:
        if (r106 == null) goto L124;
        if (r106.booleanValue() != r125) goto L71;
        r93 = Boolean.FALSE;
        goto L124
    L109:
        if (r105.zzc() == false) goto L119;
        String r143 = (String) r142;
        if (zzpk.zzm(r143) == false) goto L118;
        r106 = zzab.zzi(r143, r105.zzd());
        goto L113
    L118:
        r2.zzaW().zze().zzc("Invalid param value for number filter. event, param", r2.zzl().zza(r43), r2.zzl().zzb(r134));
        goto L124
    L119:
        r2.zzaW().zze().zzc("No filter for String param. event, param", r2.zzl().zza(r43), r2.zzl().zzb(r134));
        goto L124
    L120:
        if (r142 != 0) goto L122;
        r2.zzaW().zzk().zzc("Missing param for filter. event, param", r2.zzl().zza(r43), r2.zzl().zzb(r134));
        r93 = Boolean.FALSE;
        goto L124
    L122:
        r2.zzaW().zze().zzc("Unknown param type. event, param", r2.zzl().zza(r43), r2.zzl().zzb(r134));
        goto L124
    L95:
        if (r105.zzc() == false) goto L96;
        Boolean r107 = zzab.zzh(((Double) r142).doubleValue(), r105.zzd());
        if (r107 == null) goto L124;
        if (r107.booleanValue() != r125) goto L71;
        r93 = Boolean.FALSE;
        goto L124
    L96:
        r2.zzaW().zze().zzc("No number filter for double param. event, param", r2.zzl().zza(r43), r2.zzl().zzb(r134));
        goto L124
    L84:
        if (r105.zzc() == false) goto L85;
        Boolean r108 = zzab.zzg(((Long) r142).longValue(), r105.zzd());
        if (r108 == null) goto L124;
        if (r108.booleanValue() != r125) goto L71;
        r93 = Boolean.FALSE;
        goto L124
    L85:
        r2.zzaW().zze().zzc("No number filter for long param. event, param", r2.zzl().zza(r43), r2.zzl().zzb(r134));
        goto L124
    L80:
        r2.zzaW().zze().zzb("Event has empty param name. event", r2.zzl().zza(r43));
        goto L124
    L75:
        if (r105.zzf() == false) goto L77;
        r125 = true;
        goto L78
    L123:
        r93 = Boolean.TRUE;
    L22:
        r14 = true;
        goto L24
    L5:
        r6 = r20;
        goto L6
    }
}
