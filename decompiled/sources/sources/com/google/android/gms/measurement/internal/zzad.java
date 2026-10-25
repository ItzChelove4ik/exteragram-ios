package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzahn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
final class zzad extends zzos {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    public zzad(zzpg r1) {
        super(r1);
    }

    private final zzy zzc(Integer r4) {
        if (this.zzc.containsKey(r4) == true) goto L5;
        zzy r0 = new zzy(this, this.zza, null);
        this.zzc.put(r4, r0);
        return r0;
    L5:
        return (zzy) this.zzc.get(r4);
    }

    private final boolean zzd(int r1, int r2) {
        zzy r0 = (zzy) this.zzc.get(Integer.valueOf(r1));
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.zzc().get(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public final List zzb(String r35, List r36, List r37, Long r38, Long r39, boolean r40) {
        Preconditions.checkNotEmpty(r35);
        Preconditions.checkNotNull(r36);
        Preconditions.checkNotNull(r37);
        this.zza = r35;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = r38;
        this.zze = r39;
        Iterator r0 = r36.iterator();
    L3:
        int r10 = 0;
        int r11 = 1;
        if (r0.hasNext() == false) goto L8;
        if ("_s".equals(((com.google.android.gms.internal.measurement.zzhs) r0.next()).zzd()) == false) goto L3;
        boolean r2 = true;
    L9:
        zzahn.zza();
        zzic r02 = this.zzu;
        boolean r12 = r02.zzc().zzp(this.zza, zzfy.zzaF);
        zzahn.zza();
        boolean r13 = r02.zzc().zzp(this.zza, zzfy.zzaE);
        if (r2 == false) goto L16;
        zzaw r3 = this.zzg.zzj();
        String r4 = this.zza;
        r3.zzay();
        r3.zzg();
        Preconditions.checkNotEmpty(r4);
        ContentValues r03 = new ContentValues();
        r03.put("current_session_count", 0);
        r3.zze().update("events", r03, "app_id = ?", new String[]{r4});     // Catch: SQLiteException -> L14
    L14:
        e = move-exception;
        r3.zzu.zzaW().zzb().zzc("Error resetting session-scoped event counts. appId", zzgu.zzl(r4), e);
    L16:
        Map r04 = Collections.EMPTY_MAP;
        String r32 = "data";
        String r42 = "audience_id";
        if (r13 == false) goto L44;
        if (r12 == false) goto L44;
        zzaw r6 = this.zzg.zzj();
        String r7 = this.zza;
        Preconditions.checkNotEmpty(r7);
        ArrayMap r8 = new ArrayMap();
        Cursor r5 = r6.zze().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{r7}, null, null, null);     // Catch: Throwable -> L45 SQLiteException -> L47
    L33:
        e = e;
    L51:
        r6.zzu.zzaW().zzb().zzc("Database error querying filters. appId", zzgu.zzl(r7), e);     // Catch: Throwable -> L31
        r04 = Collections.EMPTY_MAP;     // Catch: Throwable -> L31
        if (r5 == null) goto L44;
    L43:
        r5.close();
    L31:
        th = th;
    L54:
        if (r5 == null) goto L56;
        r5.close();
    L56:
        throw th;
    L22:
        if (r5.moveToFirst() == false) goto L43;
    L464:
        com.google.android.gms.internal.measurement.zzff r05 = (com.google.android.gms.internal.measurement.zzff) ((com.google.android.gms.internal.measurement.zzfe) zzpk.zzw(com.google.android.gms.internal.measurement.zzff.zzn(), r5.getBlob(r11))).zzbd();     // Catch: Throwable -> L31 SQLiteException -> L33 IOException -> L37
        if (r05.zzh() == false) goto L40;
        Integer r112 = Integer.valueOf(r5.getInt(r10));     // Catch: Throwable -> L31 SQLiteException -> L33
        List r16 = (List) r8.get(r112);     // Catch: Throwable -> L31 SQLiteException -> L33
        if (r16 != null) goto L35;
        List r102 = new ArrayList();     // Catch: Throwable -> L31 SQLiteException -> L33
        r8.put(r112, r102);     // Catch: Throwable -> L31 SQLiteException -> L33
    L36:
        r102.add(r05);     // Catch: Throwable -> L31 SQLiteException -> L33
        goto L40
    L35:
        r102 = r16;
    L40:
        if (r5.moveToNext() == false) goto L41;
        r10 = 0;
        r11 = 1;
        goto L464
    L41:
        r5.close();
        Map r103 = r8;
    L57:
        zzaw r52 = this.zzg.zzj();
        String r62 = this.zza;
        r52.zzay();
        r52.zzg();
        Preconditions.checkNotEmpty(r62);
        Cursor r72 = r52.zze().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{r62}, null, null, null);     // Catch: Throwable -> L92 SQLiteException -> L94
    L63:
        th = th;
        Cursor r53 = r72;
    L432:
        if (r53 == null) goto L434;
        r53.close();
    L434:
        throw th;
    L65:
        e = e;
        boolean r17 = r2;
        String r18 = "data";
    L68:
        String r19 = r42;
    L98:
        r52.zzu.zzaW().zzb().zzc("Database error querying filter results. appId", zzgu.zzl(r62), e);     // Catch: Throwable -> L63
        Map r06 = Collections.EMPTY_MAP;     // Catch: Throwable -> L63
        if (r72 == null) goto L101;
        r72.close();
    L101:
        ?? r113 = r06;
    L103:
        if (r113.isEmpty() == false) goto L105;
    L104:
        String r104 = r18;
        String r114 = r19;
        String r22 = "Skipping failed audience ID";
        if (r36.isEmpty() == true) goto L321;
        zzz r33 = new zzz(this, null);
        ArrayMap r43 = new ArrayMap();
        Iterator r54 = r36.iterator();
    L241:
        if (r54.hasNext() == false) goto L321;
        com.google.android.gms.internal.measurement.zzhs r07 = (com.google.android.gms.internal.measurement.zzhs) r54.next();
        com.google.android.gms.internal.measurement.zzhs r192 = r33.zza(this.zza, r07);
        if (r192 == null) goto L241;
        zzpg r63 = this.zzg;
        zzbd r73 = r63.zzj().zzah(this.zza, r07, r192.zzd());
        r63.zzj().zzh(r73);
        if (r40 == true) goto L241;
        long r122 = r73.zzc;
        String r82 = r192.zzd();
        Map r08 = (Map) r43.get(r82);
        if (r08 != null) goto L302;
        zzaw r64 = r63.zzj();
        zzz r24 = r33;
        String r34 = this.zza;
        r64.zzay();
        r64.zzg();
        Preconditions.checkNotEmpty(r34);
        Preconditions.checkNotEmpty(r82);
        Iterator r362 = r54;
        ArrayMap r55 = new ArrayMap();
        String r162 = r34;
        Cursor r310 = r64.zze().query("event_filters", new String[]{r114, r104}, "app_id=? AND event_name=?", new String[]{r34, r82}, null, null, null);     // Catch: Throwable -> L286 SQLiteException -> L288
        if (r310.moveToFirst() == false) goto L279;
        zzbd r222 = r73;
    L460:
        com.google.android.gms.internal.measurement.zzff r09 = (com.google.android.gms.internal.measurement.zzff) ((com.google.android.gms.internal.measurement.zzfe) zzpk.zzw(com.google.android.gms.internal.measurement.zzff.zzn(), r310.getBlob(1))).zzbd();     // Catch: Throwable -> L269 SQLiteException -> L271 IOException -> L273
        Integer r74 = Integer.valueOf(r310.getInt(0));     // Catch: Throwable -> L269 SQLiteException -> L271
        List r172 = (List) r55.get(r74);     // Catch: Throwable -> L269 SQLiteException -> L271
        if (r172 != null) goto L267;
        Cursor r182 = r310;
        List r311 = new ArrayList();     // Catch: Throwable -> L263 SQLiteException -> L265
        r55.put(r74, r311);     // Catch: Throwable -> L263 SQLiteException -> L265
    L268:
        r311.add(r09);     // Catch: Throwable -> L263 SQLiteException -> L265
    L275:
        if (r182.moveToNext() == false) goto L277;
        r310 = r182;
        goto L460
    L277:
        r182.close();
        r08 = r55;
    L297:
        r43.put(r82, r08);
    L303:
        Iterator r312 = r08.keySet().iterator();
    L305:
        if (r312.hasNext() == false) goto L320;
        Integer r56 = (Integer) r312.next();
        int r65 = r56.intValue();
        if (this.zzb.contains(r56) == true) goto L308;
        Iterator r75 = ((List) r08.get(r56)).iterator();
        boolean r83 = true;
    L311:
        if (r75.hasNext() == false) goto L316;
        com.google.android.gms.internal.measurement.zzff r84 = (com.google.android.gms.internal.measurement.zzff) r75.next();
        Map r25 = r08;
        Iterator r26 = r312;
        zzaa r010 = new zzaa(this, this.zza, r65, r84);
        long r20 = r122;
        r83 = r010.zzd(this.zzd, this.zze, r192, r20, r222, zzd(r65, r84.zzb()));
        if (r83 == false) goto L315;
        zzc(r56).zza(r010);
        r122 = r20;
        r08 = r25;
        r312 = r26;
        goto L311
    L315:
        this.zzb.add(r56);
    L317:
        if (r83 == true) goto L319;
        this.zzb.add(r56);
    L319:
        r122 = r20;
        r08 = r25;
        r312 = r26;
        goto L305
    L316:
        r25 = r08;
        r26 = r312;
        r20 = r122;
        goto L317
    L308:
        this.zzu.zzaW().zzk().zzb("Skipping failed audience ID", r56);
        goto L305
    L320:
        r54 = r362;
        r33 = r24;
        goto L241
    L265:
        e = e;
    L285:
        Cursor r57 = r182;
    L466:
        r64.zzu.zzaW().zzb().zzc("Database error querying filters. appId", zzgu.zzl(r162), e);     // Catch: Throwable -> L298
        r08 = Collections.EMPTY_MAP;     // Catch: Throwable -> L298
        if (r57 == null) goto L297;
        r57.close();
    L298:
        th = th;
    L299:
        if (r57 == null) goto L301;
        r57.close();
    L301:
        throw th;
    L263:
        th = th;
    L282:
        r57 = r182;
        goto L299
    L267:
        r182 = r310;
        r311 = r172;
    L273:
        e = move-exception;
        r182 = r310;
        r64.zzu.zzaW().zzb().zzc("Failed to merge filter. appId", zzgu.zzl(r162), e);     // Catch: Throwable -> L263 SQLiteException -> L265
    L271:
        e = e;
        r182 = r310;
        goto L285
    L279:
        r222 = r73;
        r08 = Collections.EMPTY_MAP;     // Catch: Throwable -> L263 SQLiteException -> L265
        r310.close();
        goto L297
    L269:
        th = th;
        r182 = r310;
    L283:
        e = e;
        r182 = r310;
        r222 = r73;
    L288:
        e = e;
    L289:
        r222 = r73;
        r57 = null;
        goto L466
    L286:
        th = th;
        r57 = null;
    L290:
        e = e;
        r162 = r34;
        goto L289
    L302:
        r24 = r33;
        r362 = r54;
        r222 = r73;
    L321:
        if (r40 == true) goto L431;
        if (r37.isEmpty() == true) goto L414;
        ArrayMap r313 = new ArrayMap();
        Iterator r44 = r37.iterator();
    L327:
        if (r44.hasNext() == false) goto L414;
        com.google.android.gms.internal.measurement.zziu r58 = (com.google.android.gms.internal.measurement.zziu) r44.next();
        String r66 = r58.zzc();
        Map r011 = (Map) r313.get(r66);
        if (r011 != null) goto L376;
        zzaw r76 = this.zzg.zzj();
        String r85 = this.zza;
        r76.zzay();
        r76.zzg();
        Preconditions.checkNotEmpty(r85);
        Preconditions.checkNotEmpty(r66);
        ArrayMap r123 = new ArrayMap();
        Cursor r132 = r76.zze().query("property_filters", new String[]{r114, r104}, "app_id=? AND property_name=?", new String[]{r85, r66}, null, null, null);     // Catch: Throwable -> L363 SQLiteException -> L365
        if (r132.moveToFirst() == true) goto L468;
        Iterator r363 = r44;
        r011 = Collections.EMPTY_MAP;     // Catch: Throwable -> L341 SQLiteException -> L357
    L361:
        r132.close();
    L372:
        r313.put(r66, r011);
    L377:
        Iterator r45 = r011.keySet().iterator();
    L379:
        if (r45.hasNext() == false) goto L383;
        Integer r67 = (Integer) r45.next();
        int r77 = r67.intValue();
        if (this.zzb.contains(r67) == true) goto L382;
        Iterator r86 = ((List) r011.get(r67)).iterator();
        boolean r124 = true;
    L386:
        if (r86.hasNext() == false) goto L410;
        com.google.android.gms.internal.measurement.zzfn r125 = (com.google.android.gms.internal.measurement.zzfn) r86.next();
        zzic r133 = this.zzu;
        Map r372 = r011;
        if (Log.isLoggable(r133.zzaW().zzn(), 2) == false) goto L395;
        zzgs r012 = r133.zzaW().zzk();
        if (r125.zza() == false) goto L393;
        Integer r14 = Integer.valueOf(r125.zzb());
    L392:
        String r163 = r22;
        ArrayMap r402 = r313;
        r012.zzd("Evaluating filter. audience, filter, property", r67, r14, r133.zzl().zzc(r125.zzc()));
        r133.zzaW().zzk().zzb("Filter definition", this.zzg.zzp().zzk(r125));
    L397:
        if (r125.zza() == false) goto L405;
        if (r125.zzb() > 256) goto L405;
        zzac r013 = new zzac(this, this.zza, r77, r125);
        r124 = r013.zzd(this.zzd, this.zze, r58, zzd(r77, r125.zzb()));
        if (r124 == false) goto L404;
        zzc(r67).zza(r013);
        r011 = r372;
        r313 = r402;
        r22 = r163;
        goto L386
    L404:
        this.zzb.add(r67);
    L411:
        if (r124 == true) goto L413;
    L412:
        this.zzb.add(r67);
    L413:
        r011 = r372;
        r313 = r402;
        r22 = r163;
    L405:
        zzgs r014 = r133.zzaW().zze();
        Object r23 = zzgu.zzl(this.zza);
        if (r125.zza() == false) goto L408;
        Integer r314 = Integer.valueOf(r125.zzb());
    L409:
        r014.zzc("Invalid property filter ID. appId, id", r23, String.valueOf(r314));
        goto L412
    L408:
        r314 = null;
        goto L409
    L393:
        r14 = null;
        goto L392
    L395:
        r163 = r22;
        r402 = r313;
        goto L397
    L410:
        r372 = r011;
        r163 = r22;
        r402 = r313;
        goto L411
    L382:
        this.zzu.zzaW().zzk().zzb(r22, r67);
    L383:
        r44 = r363;
    L468:
        com.google.android.gms.internal.measurement.zzfn r015 = (com.google.android.gms.internal.measurement.zzfn) ((com.google.android.gms.internal.measurement.zzfm) zzpk.zzw(com.google.android.gms.internal.measurement.zzfn.zzj(), r132.getBlob(1))).zzbd();     // Catch: Throwable -> L341 SQLiteException -> L347 IOException -> L349
        Integer r142 = Integer.valueOf(r132.getInt(0));     // Catch: Throwable -> L341 SQLiteException -> L347
        List r164 = (List) r123.get(r142);     // Catch: Throwable -> L341 SQLiteException -> L347
        if (r164 != null) goto L345;
        r363 = r44;
        List r46 = new ArrayList();     // Catch: Throwable -> L341 SQLiteException -> L343
        r123.put(r142, r46);     // Catch: Throwable -> L341 SQLiteException -> L343
    L346:
        r46.add(r015);     // Catch: Throwable -> L341 SQLiteException -> L343
        String r165 = r85;
    L353:
        if (r132.moveToNext() == false) goto L355;
        r44 = r363;
        r85 = r165;
        goto L468
    L355:
        r132.close();
        r011 = r123;
    L357:
        e = e;
    L369:
        r76.zzu.zzaW().zzb().zzc("Database error querying filters. appId", zzgu.zzl(r165), e);     // Catch: Throwable -> L341
        r011 = Collections.EMPTY_MAP;     // Catch: Throwable -> L341
        if (r132 == null) goto L372;
    L343:
        e = e;
    L344:
        r165 = r85;
        goto L369
    L345:
        r363 = r44;
        r46 = r164;
    L349:
        e = move-exception;
        r363 = r44;
        r165 = r85;
        r76.zzu.zzaW().zzb().zzc("Failed to merge filter", zzgu.zzl(r165), e);     // Catch: Throwable -> L341 SQLiteException -> L357
        goto L353
    L347:
        e = e;
        r363 = r44;
    L341:
        th = th;
        Cursor r59 = r132;
    L373:
        if (r59 == null) goto L375;
        r59.close();
    L375:
        throw th;
    L365:
        e = e;
        r363 = r44;
        r165 = r85;
        r132 = null;
    L363:
        th = th;
        r59 = null;
        goto L373
    L376:
        r363 = r44;
    L414:
        ArrayList r27 = new ArrayList();
        Set r016 = this.zzc.keySet();
        r016.removeAll(this.zzb);
        Iterator r315 = r016.iterator();
    L416:
        if (r315.hasNext() == false) goto L429;
        Integer r017 = (Integer) r315.next();
        int r47 = r017.intValue();
        zzy r510 = (zzy) this.zzc.get(r017);
        Preconditions.checkNotNull(r510);
        com.google.android.gms.internal.measurement.zzhg r48 = r510.zzb(r47);
        r27.add(r48);
        zzaw r511 = this.zzg.zzj();
        String r68 = this.zza;
        com.google.android.gms.internal.measurement.zzii r49 = r48.zzc();
        r511.zzay();
        r511.zzg();
        Preconditions.checkNotEmpty(r68);
        Preconditions.checkNotNull(r49);
        byte[] r410 = r49.zzcd();
        ContentValues r78 = new ContentValues();
        r78.put("app_id", r68);
        r78.put(r114, r017);
        r78.put("current_results", r410);
        if (r511.zze().insertWithOnConflict("audience_filter_values", null, r78, 5) != (-1)) goto L416;
        r511.zzu.zzaW().zzb().zzb("Failed to insert filter results (got -1). appId", zzgu.zzl(r68));     // Catch: SQLiteException -> L424
    L424:
        e = e;
    L428:
        r511.zzu.zzaW().zzb().zzc("Error storing filter results. appId", zzgu.zzl(r68), e);
    L426:
        e = e;
        goto L428
    L429:
        return r27;
    L431:
        return new ArrayList();
    L105:
        HashSet r28 = new HashSet(r113.keySet());
        if (r17 == false) goto L168;
        String r316 = this.zza;
        zzaw r411 = this.zzg.zzj();
        String r512 = this.zza;
        r411.zzay();
        r411.zzg();
        Preconditions.checkNotEmpty(r512);
        Map r018 = new ArrayMap();
        SQLiteDatabase r69 = r411.zze();
        Cursor r610 = r69.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{r512, r512});     // Catch: Throwable -> L125 SQLiteException -> L127
    L117:
        e = e;
    L131:
        r411.zzu.zzaW().zzb().zzc("Database error querying scoped filters. appId", zzgu.zzl(r512), e);     // Catch: Throwable -> L115
        r018 = Collections.EMPTY_MAP;     // Catch: Throwable -> L115
        if (r610 != null) goto L121;
    L134:
        Preconditions.checkNotEmpty(r316);
        Preconditions.checkNotNull(r113);
        ArrayMap r317 = new ArrayMap();
        if (r113.isEmpty() == false) goto L137;
    L136:
        HashSet r173 = r28;
        ArrayMap r019 = r317;
    L169:
        Iterator r166 = r173.iterator();
        Map r115 = r113;
    L171:
        if (r166.hasNext() == false) goto L104;
        Integer r29 = (Integer) r166.next();
        r29.intValue();
        com.google.android.gms.internal.measurement.zzii r318 = (com.google.android.gms.internal.measurement.zzii) r019.get(r29);
        BitSet r412 = new BitSet();
        BitSet r513 = new BitSet();
        ArrayMap r611 = new ArrayMap();
        if (r318 != null) goto L175;
    L176:
        ArrayMap r202 = r019;
        ArrayMap r79 = new ArrayMap();
        if (r318 != null) goto L190;
    L191:
        com.google.android.gms.internal.measurement.zzii r223 = r318;
        if (r223 == null) goto L212;
        int r020 = 0;
    L203:
        if (r020 >= (r223.zzb() * 64)) goto L212;
        if (zzpk.zzn(r223.zza(), r020) == false) goto L209;
        boolean r174 = r12;
        this.zzu.zzaW().zzk().zzc("Filter already evaluated. audience ID, filter ID", r29, Integer.valueOf(r020));
        r513.set(r020);
        if (zzpk.zzn(r223.zzc(), r020) == false) goto L210;
        r412.set(r020);
    L211:
        r020 = r020 + 1;
        r12 = r174;
    L210:
        r611.remove(Integer.valueOf(r020));
        goto L211
    L209:
        r174 = r12;
    L212:
        boolean r175 = r12;
        com.google.android.gms.internal.measurement.zzii r319 = (com.google.android.gms.internal.measurement.zzii) r115.get(r29);
        if (r13 == false) goto L235;
        if (r175 == false) goto L235;
        List r021 = (List) r103.get(r29);
        if (r021 == null) goto L235;
        if (this.zze == null) goto L235;
        if (this.zzd == null) goto L235;
        Iterator r022 = r021.iterator();
    L224:
        if (r022.hasNext() == false) goto L235;
        com.google.android.gms.internal.measurement.zzff r87 = (com.google.android.gms.internal.measurement.zzff) r022.next();
        int r126 = r87.zzb();
        Iterator r21 = r022;
        long r224 = this.zze.longValue() / 1000;
        if (r87.zzk() == false) goto L228;
        r224 = this.zzd.longValue() / 1000;
    L228:
        Integer r023 = Integer.valueOf(r126);
        if (r611.containsKey(r023) == false) goto L232;
        r611.put(r023, Long.valueOf(r224));
    L232:
        if (r79.containsKey(r023) == false) goto L234;
        r79.put(r023, Long.valueOf(r224));
    L234:
        r022 = r21;
    L235:
        this.zzc.put(r29, new zzy(this, this.zza, r319, r412, r513, r611, r79, null));
        r18 = r18;
        r103 = r103;
        r19 = r19;
        r115 = r115;
        r12 = r175;
        r019 = r202;
        goto L171
    L190:
        if (r318.zzi() == 0) goto L191;
        Iterator r024 = r318.zzh().iterator();
    L194:
        if (r024.hasNext() == false) goto L191;
        com.google.android.gms.internal.measurement.zzik r88 = (com.google.android.gms.internal.measurement.zzik) r024.next();
        if (r88.zza() == false) goto L194;
        if (r88.zzd() <= 0) goto L194;
        r79.put(Integer.valueOf(r88.zzb()), Long.valueOf(r88.zze(r88.zzd() - 1)));
        r024 = r024;
        r318 = r318;
        goto L194
    L175:
        if (r318.zzf() == 0) goto L176;
        Iterator r710 = r318.zze().iterator();
    L179:
        if (r710.hasNext() == false) goto L176;
        com.google.android.gms.internal.measurement.zzhq r89 = (com.google.android.gms.internal.measurement.zzhq) r710.next();
        if (r89.zza() == false) goto L179;
        ArrayMap r203 = r019;
        Integer r025 = Integer.valueOf(r89.zzb());
        if (r89.zzc() == false) goto L185;
        Long r810 = Long.valueOf(r89.zzd());
    L186:
        r611.put(r025, r810);
        r019 = r203;
        goto L179
    L185:
        r810 = null;
        goto L186
    L137:
        Iterator r413 = r113.keySet().iterator();
    L139:
        if (r413.hasNext() == false) goto L136;
        Integer r514 = (Integer) r413.next();
        r514.intValue();
        com.google.android.gms.internal.measurement.zzii r612 = (com.google.android.gms.internal.measurement.zzii) r113.get(r514);
        List r711 = (List) r018.get(r514);
        if (r711 == null) goto L144;
        if (r711.isEmpty() == true) goto L144;
        zzpg r811 = this.zzg;
        Map r167 = r018;
        HashSet r176 = r28;
        List r026 = r811.zzp().zzq(r612.zzc(), r711);
        if (r026.isEmpty() == false) goto L147;
        r018 = r167;
        r28 = r176;
        goto L139
    L147:
        com.google.android.gms.internal.measurement.zzih r210 = (com.google.android.gms.internal.measurement.zzih) r612.zzco();
        r210.zzd();
        r210.zzc(r026);
        List r027 = r811.zzp().zzq(r612.zza(), r711);
        r210.zzb();
        r210.zza(r027);
        ArrayList r028 = new ArrayList();
        Iterator r812 = r612.zze().iterator();
    L149:
        if (r812.hasNext() == false) goto L154;
        Iterator r212 = r413;
        com.google.android.gms.internal.measurement.zzhq r414 = (com.google.android.gms.internal.measurement.zzhq) r812.next();
        Iterator r225 = r812;
        if (r711.contains(Integer.valueOf(r414.zzb())) == true) goto L153;
        r028.add(r414);
    L153:
        r413 = r212;
        r812 = r225;
        goto L149
    L154:
        Iterator r213 = r413;
        r210.zzf();
        r210.zze(r028);
        ArrayList r029 = new ArrayList();
        Iterator r415 = r612.zzh().iterator();
    L156:
        if (r415.hasNext() == false) goto L160;
        com.google.android.gms.internal.measurement.zzik r613 = (com.google.android.gms.internal.measurement.zzik) r415.next();
        if (r711.contains(Integer.valueOf(r613.zzb())) == true) goto L156;
        r029.add(r613);
        goto L156
    L160:
        r210.zzh();
        r210.zzg(r029);
        r317.put(r514, (com.google.android.gms.internal.measurement.zzii) r210.zzbd());
    L161:
        r018 = r167;
        r28 = r176;
        r413 = r213;
    L144:
        r167 = r018;
        r176 = r28;
        r213 = r413;
        r317.put(r514, r612);
    L121:
        r610.close();
        goto L134
    L110:
        if (r610.moveToFirst() == false) goto L122;
    L111:
        Integer r712 = Integer.valueOf(r610.getInt(0));     // Catch: Throwable -> L115 SQLiteException -> L117
        List r813 = (List) r018.get(r712);     // Catch: Throwable -> L115 SQLiteException -> L117
        if (r813 != null) goto L119;
        r813 = new ArrayList();     // Catch: Throwable -> L115 SQLiteException -> L117
        r018.put(r712, r813);     // Catch: Throwable -> L115 SQLiteException -> L117
    L119:
        r813.add(Integer.valueOf(r610.getInt(1)));     // Catch: Throwable -> L115 SQLiteException -> L117
        if (r610.moveToNext() == true) goto L111;
    L122:
        r018 = Collections.EMPTY_MAP;     // Catch: Throwable -> L115 SQLiteException -> L117
        goto L121
    L115:
        th = th;
        ?? r515 = r69;
    L165:
        if (r515 == 0) goto L167;
        r515.close();
    L167:
        throw th;
    L127:
        e = e;
        r610 = null;
    L125:
        th = th;
        r515 = 0;
        goto L165
    L168:
        r173 = r28;
        r019 = r113;
        goto L169
    L60:
        if (r72.moveToFirst() == true) goto L69;
        Map r030 = Collections.EMPTY_MAP;     // Catch: Throwable -> L63 SQLiteException -> L65
        r72.close();
        r113 = r030;
        r17 = r2;
        r18 = "data";
        r19 = "audience_id";
        goto L103
    L69:
        ArrayMap r814 = new ArrayMap();     // Catch: Throwable -> L63 SQLiteException -> L65
    L70:
        int r168 = r72.getInt(0);     // Catch: Throwable -> L63 SQLiteException -> L65
        r814.put(Integer.valueOf(r168), (com.google.android.gms.internal.measurement.zzii) ((com.google.android.gms.internal.measurement.zzih) zzpk.zzw(com.google.android.gms.internal.measurement.zzii.zzj(), r72.getBlob(1))).zzbd());     // Catch: Throwable -> L63 SQLiteException -> L65
        r17 = r2;
        r18 = r32;
        r19 = r42;
    L81:
        if (r72.moveToNext() == false) goto L83;
        r2 = r17;
        r32 = r18;
        r42 = r19;
        goto L70
    L83:
        r72.close();
        r113 = r814;
    L85:
        e = e;
    L73:
        e = move-exception;
        r17 = r2;
        r18 = r32;
        r19 = r42;
        r52.zzu.zzaW().zzb().zzd("Failed to merge filter results. appId, audienceId, error", zzgu.zzl(r62), Integer.valueOf(r168), e);     // Catch: Throwable -> L63 SQLiteException -> L85
    L87:
        e = e;
        goto L68
    L94:
        e = e;
        r17 = r2;
        r18 = "data";
        r19 = "audience_id";
        r72 = null;
    L92:
        th = th;
        r53 = null;
    L37:
        e = move-exception;
        r6.zzu.zzaW().zzb().zzc("Failed to merge filter. appId", zzgu.zzl(r7), e);     // Catch: Throwable -> L31 SQLiteException -> L33
        goto L40
    L47:
        e = e;
        r5 = null;
    L45:
        th = th;
        r5 = null;
    L44:
        r103 = r04;
        goto L57
    L8:
        r2 = false;
        goto L9
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    public final boolean zzbc() {
        return false;
    }
}
