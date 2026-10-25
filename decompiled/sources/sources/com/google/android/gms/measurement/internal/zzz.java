package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzz {
    final /* synthetic */ zzad zza;
    private com.google.android.gms.internal.measurement.zzhs zzb;
    private Long zzc;
    private long zzd;

    public /* synthetic */ zzz(zzad r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    public final com.google.android.gms.internal.measurement.zzhs zza(String r18, com.google.android.gms.internal.measurement.zzhs r19) {
        String r6 = r19.zzd();
        List r9 = r19.zza();
        zzad r0 = this.zza;
        zzpg r2 = r0.zzg;
        r2.zzp();
        Long r5 = (Long) zzpk.zzM(r19, "_eid");
        if (r5 != null) goto L5;
    L76:
        com.google.android.gms.internal.measurement.zzhr r02 = (com.google.android.gms.internal.measurement.zzhr) r19.zzco();
        r02.zzl(r6);
        r02.zzi();
        r02.zzh(r9);
        return (com.google.android.gms.internal.measurement.zzhs) r02.zzbd();
    L5:
        if (r6.equals("_ep") == false) goto L72;
        Preconditions.checkNotNull(r5);
        r2.zzp();
        String r12 = (String) zzpk.zzM(r19, "_en");
        ?? r62 = TextUtils.isEmpty(r12);
        Cursor r7 = null;
        if (r62 == 0) goto L11;
        r0.zzu.zzaW().zzc().zzb("Extra parameter without an event name. eventId", r5);
        return null;
    L11:
        if (this.zzb != null) goto L13;
    L16:
        zzaw r22 = r2.zzj();
        r22.zzg();
        r22.zzay();
        Cursor r63 = r22.zze().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{r18, r5.toString()});     // Catch: Throwable -> L36 SQLiteException -> L38
    L25:
        e = e;
    L40:
        r22.zzu.zzaW().zzb().zzb("Error selecting main event", e);     // Catch: Throwable -> L23
        if (r63 != null) goto L21;
    L22:
        Pair r03 = null;
    L43:
        if (r03 == null) goto L67;
        Object r23 = r03.first;
        if (r23 == null) goto L67;
        this.zzb = (com.google.android.gms.internal.measurement.zzhs) r23;
        this.zzd = ((Long) r03.second).longValue();
        this.zza.zzg.zzp();
        this.zzc = (Long) zzpk.zzM(this.zzb, "_eid");
    L48:
        long r64 = this.zzd - 1;
        this.zzd = r64;
        zzad r24 = this.zza;
        if (r64 > 0) goto L55;
        zzaw r25 = r24.zzg.zzj();
        r25.zzg();
        r25.zzu.zzaW().zzk().zzb("Clearing complex main event info. appId", r18);
        r25.zze().execSQL("delete from main_event_params where app_id=?", new String[]{r18});     // Catch: SQLiteException -> L53
    L56:
        ArrayList r04 = new ArrayList();
        Iterator r26 = this.zzb.zza().iterator();
    L58:
        if (r26.hasNext() == false) goto L63;
        com.google.android.gms.internal.measurement.zzhw r3 = (com.google.android.gms.internal.measurement.zzhw) r26.next();
        this.zza.zzg.zzp();
        if (zzpk.zzI(r19, r3.zzb()) != null) goto L58;
        r04.add(r3);
        goto L58
    L63:
        if (r04.isEmpty() == true) goto L65;
        r04.addAll(r9);
        r9 = r04;
    L66:
        r6 = r12;
        goto L76
    L65:
        this.zza.zzu.zzaW().zzc().zzb("No unique parameters in main event. eventName", r12);
    L53:
        e = move-exception;
        r25.zzu.zzaW().zzb().zzb("Error clearing complex main event", e);
        goto L56
    L55:
        r24.zzg.zzj().zzU(r18, r5, this.zzd, this.zzb);
    L67:
        this.zza.zzu.zzaW().zzc().zzc("Extra parameter without existing main event. eventName, eventId", r12, r5);
        return null;
    L21:
        r63.close();
        goto L22
    L19:
        if (r63.moveToFirst() == true) goto L28;
        r22.zzu.zzaW().zzk().zza("Main event not found");     // Catch: Throwable -> L23 SQLiteException -> L25
        goto L21
    L28:
        byte[] r05 = r63.getBlob(0);     // Catch: Throwable -> L23 SQLiteException -> L25
        r03 = Pair.create((com.google.android.gms.internal.measurement.zzhs) ((com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzp(), r05)).zzbd(), Long.valueOf(r63.getLong(1)));     // Catch: Throwable -> L23 SQLiteException -> L25
        r63.close();
    L32:
        e = move-exception;
        r22.zzu.zzaW().zzb().zzd("Failed to merge main event. appId, eventId", zzgu.zzl(r18), r5, e);     // Catch: Throwable -> L23 SQLiteException -> L25
        goto L21
    L23:
        th = th;
        r7 = r62;
    L69:
        if (r7 == null) goto L71;
        r7.close();
    L71:
        throw th;
    L38:
        e = e;
        r63 = null;
    L36:
        th = th;
        goto L69
    L13:
        if (this.zzc == null) goto L16;
        if (r5.longValue() == this.zzc.longValue()) goto L48;
    L72:
        this.zzc = r5;
        this.zzb = r19;
        r2.zzp();
        long r122 = ((Long) zzpk.zzN(r19, "_epc", 0L)).longValue();
        this.zzd = r122;
        if (r122 > 0) goto L75;
        r0.zzu.zzaW().zzc().zzb("Complex event with zero extra param count. eventName", r6);
        goto L76
    L75:
        r2.zzj().zzU(r18, (Long) Preconditions.checkNotNull(r5), this.zzd, r19);
        goto L76
    }
}
