package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzgl extends zzg {
    private static final String[] zza = null;
    private final zzgj zzb;
    private boolean zzc;

    static {
        zza = new String[]{"app_version", "ALTER TABLE messages ADD COLUMN app_version TEXT;", "app_version_int", "ALTER TABLE messages ADD COLUMN app_version_int INTEGER;"};
    }

    public zzgl(zzic r3) {
        super(r3);
        Context r0 = this.zzu.zzaZ();
        this.zzu.zzc();
        this.zzb = new zzgj(this, r0, "google_app_measurement_local.db");
    }

    public static /* synthetic */ String[] zzr() {
        return zza;
    }

    private final boolean zzs(int r19, byte[] r20) {
        zzg();
        boolean r2 = false;
        r2 = false;
        if (this.zzc == true) goto L19;
        zzic r3 = this.zzu;
        zzal r0 = r3.zzc();
        zzfx r4 = zzfy.zzaW;
        Cursor r5 = null;
        if (r0.zzp(null, r4) == false) goto L8;
        zzr r02 = this.zzu.zzv().zzh(null);
    L9:
        ContentValues r6 = new ContentValues();
        r6.put("type", Integer.valueOf(r19));
        r6.put("entry", r20);
        if (r3.zzc().zzp(null, r4) == false) goto L13;
        if (r02 == null) goto L13;
        r6.put("app_version", r02.zzc);
        r6.put("app_version_int", Long.valueOf(r02.zzj));
    L13:
        r3.zzc();
        int r42 = 5;
        int r7 = 0;
        int r8 = 5;
    L14:
        if (r7 >= r42) goto L104;
        SQLiteDatabase r10 = zzp();     // Catch: Throwable -> L68 SQLiteException -> L70 SQLiteDatabaseLockedException -> L81 SQLiteFullException -> L91
        if (r10 != null) goto L114;
        this.zzc = true;     // Catch: Throwable -> L20 SQLiteException -> L22 SQLiteDatabaseLockedException -> L24 SQLiteFullException -> L25
    L24:
        boolean r17 = r2 ? 1 : 0;
        Cursor r11 = null;
    L83:
        SystemClock.sleep(r8);     // Catch: Throwable -> L89
        r8 = r8 + 20;
        if (r11 == null) goto L87;
        r11.close();
    L87:
        if (r10 == null) goto L98;
    L88:
        r10.close();
    L98:
        r7 = r7 + 1;
        r2 = r17;
        r42 = 5;
    L25:
        e = e;
        r17 = r2 ? 1 : 0;
        r11 = null;
    L93:
        this.zzu.zzaW().zzb().zzb("Error writing entry; local database full", e);     // Catch: Throwable -> L89
        this.zzc = true;     // Catch: Throwable -> L89
        if (r11 == null) goto L96;
        r11.close();
    L96:
        if (r10 == null) goto L98;
    L22:
        e = e;
        r17 = r2 ? 1 : 0;
        boolean r202 = true;
        r11 = null;
    L72:
        if (r10 != null) goto L107;
    L76:
        this.zzu.zzaW().zzb().zzb("Error writing entry to local database", e);     // Catch: Throwable -> L89
        this.zzc = r202;     // Catch: Throwable -> L89
        if (r11 == null) goto L79;
        r11.close();
    L79:
        if (r10 == null) goto L98;
    L107:
    L89:
        th = th;
        r5 = r11;
    L99:
        if (r5 == null) goto L101;
        r5.close();
    L101:
        if (r10 == null) goto L103;
        r10.close();
    L103:
        throw th;
    L74:
        if (r10.inTransaction() == false) goto L76;
        r10.endTransaction();     // Catch: Throwable -> L89
    L20:
        th = th;
        goto L99
    L114:
        r10.beginTransaction();     // Catch: Throwable -> L20 SQLiteException -> L22 SQLiteDatabaseLockedException -> L24 SQLiteFullException -> L25
        r11 = r10.rawQuery("select count(1) from messages", null);     // Catch: Throwable -> L20 SQLiteException -> L22 SQLiteDatabaseLockedException -> L24 SQLiteFullException -> L25
        long r12 = 0;
        if (r11 == null) goto L42;
        if (r11.moveToFirst() == false) goto L42;
        r12 = r11.getLong(r2 ? 1 : 0);     // Catch: SQLiteException -> L34 SQLiteDatabaseLockedException -> L37 SQLiteFullException -> L38 Throwable -> L89
        goto L42
    L37:
        r17 = r2 ? 1 : 0;
    L38:
        e = e;
        r17 = r2 ? 1 : 0;
    L34:
        e = e;
        r17 = r2 ? 1 : 0;
    L36:
        r202 = true;
    L42:
        if (r12 < 100000) goto L58;
        r3.zzaW().zzb().zza("Data loss, local db full");     // Catch: SQLiteException -> L34 SQLiteDatabaseLockedException -> L37 SQLiteFullException -> L38 Throwable -> L89
        long r15 = 100001 - r12;     // Catch: SQLiteException -> L34 SQLiteDatabaseLockedException -> L37 SQLiteFullException -> L38 Throwable -> L89
        long r122 = r10.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(r15)});     // Catch: SQLiteException -> L34 SQLiteDatabaseLockedException -> L37 SQLiteFullException -> L38 Throwable -> L89
        if (r122 == r15) goto L58;
        zzgs r03 = r3.zzaW().zzb();     // Catch: SQLiteException -> L34 SQLiteDatabaseLockedException -> L37 SQLiteFullException -> L38 Throwable -> L89
        r17 = r2 ? 1 : 0;
        r202 = true;
        r03.zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(r15), Long.valueOf(r122), Long.valueOf(r15 - r122));     // Catch: SQLiteException -> L50 SQLiteFullException -> L52 SQLiteDatabaseLockedException -> L106 Throwable -> L89
    L59:
        r10.insertOrThrow("messages", null, r6);     // Catch: SQLiteException -> L50 SQLiteFullException -> L52 SQLiteDatabaseLockedException -> L106 Throwable -> L89
        r10.setTransactionSuccessful();     // Catch: SQLiteException -> L50 SQLiteFullException -> L52 SQLiteDatabaseLockedException -> L106 Throwable -> L89
        r10.endTransaction();     // Catch: SQLiteException -> L50 SQLiteFullException -> L52 SQLiteDatabaseLockedException -> L106 Throwable -> L89
        goto L83
    L60:
        if (r11 == null) goto L62;
        r11.close();
    L62:
        r10.close();
        return r202;
    L52:
        e = e;
    L50:
        e = e;
        goto L72
    L56:
        e = e;
    L54:
        e = e;
    L58:
        r17 = r2 ? 1 : 0;
        r202 = true;
        goto L59
    L81:
        r17 = r2 ? 1 : 0;
        r10 = null;
        r11 = null;
    L91:
        e = e;
        r17 = r2 ? 1 : 0;
        r10 = null;
        r11 = null;
    L70:
        e = e;
        r17 = r2 ? 1 : 0;
        r202 = true;
        r10 = null;
        r11 = null;
    L68:
        th = th;
        r10 = null;
        goto L99
    L104:
        boolean r172 = r2 ? 1 : 0;
        this.zzu.zzaW().zzk().zza("Failed to write entry to local database");
        return r172;
    L8:
        r02 = null;
    L19:
        return r2;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zze() {
        return false;
    }

    public final void zzh() {
        zzg();
        SQLiteDatabase r0 = zzp();     // Catch: SQLiteException -> L9
        if (r0 == null) goto L11;
        int r02 = r0.delete("messages", null, null);     // Catch: SQLiteException -> L9
        if (r02 <= 0) goto L16;
        this.zzu.zzaW().zzk().zzb("Reset local analytics data. records", Integer.valueOf(r02));     // Catch: SQLiteException -> L9
        return;
    L16:
        return;
    L11:
        return;
    L9:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Error resetting local analytics data. error", e);
    }

    public final boolean zzi(zzbh r4) {
        Parcel r0 = Parcel.obtain();
        zzbi.zza(r4, r0, 0);
        byte[] r42 = r0.marshall();
        r0.recycle();
        if (r42.length <= 131072) goto L7;
        this.zzu.zzaW().zzc().zza("Event is too long for local database. Sending event directly to service");
        return false;
    L7:
        return zzs(0, r42);
    }

    public final boolean zzj(zzpl r4) {
        Parcel r0 = Parcel.obtain();
        zzpm.zza(r4, r0, 0);
        byte[] r42 = r0.marshall();
        r0.recycle();
        if (r42.length <= 131072) goto L7;
        this.zzu.zzaW().zzc().zza("User property too long for local database. Sending directly to service");
        return false;
    L7:
        return zzs(1, r42);
    }

    public final boolean zzk(zzah r4) {
        zzic r0 = this.zzu;
        byte[] r42 = r0.zzk().zzah(r4);
        if (r42.length <= 131072) goto L7;
        r0.zzaW().zzc().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    L7:
        return zzs(2, r42);
    }

    public final boolean zzl(zzbf r5) {
        zzic r0 = this.zzu;
        byte[] r52 = r0.zzk().zzah(r5);
        if (r52 != null) goto L7;
        r0.zzaW().zzc().zza("Null default event parameters; not writing to database");
        return false;
    L7:
        if (r52.length <= 131072) goto L11;
        r0.zzaW().zzc().zza("Default event parameters too long for local database. Sending directly to service");
        return false;
    L11:
        return zzs(4, r52);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.String, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v13 */
    public final List zzm(int r30) {
        String r3 = "entry";
        String r4 = "type";
        String r5 = "rowid";
        zzg();
        ?? r6 = 0;
        if (this.zzc == false) goto L5;
        return null;
    L5:
        ArrayList r7 = new ArrayList();
        if (zzq() == false) goto L214;
        int r8 = 5;
        int r9 = 0;
        int r11 = 5;
        int r10 = 0;
    L8:
        if (r10 >= r8) goto L212;
        SQLiteDatabase r13 = zzp();     // Catch: Throwable -> L176 SQLiteException -> L178 SQLiteDatabaseLockedException -> L189 SQLiteFullException -> L199
        if (r13 != null) goto L245;
        this.zzc = true;     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
    L18:
        String r17 = r3;
        String r19 = r4;
        String r21 = r5;
        int r62 = r9;
    L174:
        Cursor r14 = null;
    L191:
        SystemClock.sleep(r11);     // Catch: Throwable -> L197
        r11 = r11 + 20;
        if (r14 == null) goto L195;
        r14.close();
    L195:
        if (r13 == null) goto L206;
    L196:
        r13.close();
    L206:
        r10 = r10 + 1;
        r9 = r62;
        r3 = r17;
        r4 = r19;
        r5 = r21;
        r6 = 0;
        r8 = 5;
    L19:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
    L175:
        r14 = null;
    L201:
        this.zzu.zzaW().zzb().zzb("Error reading entries from local database", e);     // Catch: Throwable -> L197
        this.zzc = true;     // Catch: Throwable -> L197
        if (r14 == null) goto L204;
        r14.close();
    L204:
        if (r13 == null) goto L206;
    L16:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
    L173:
        r14 = null;
    L180:
        if (r13 != null) goto L224;
    L184:
        this.zzu.zzaW().zzb().zzb("Error reading entries from local database", e);     // Catch: Throwable -> L197
        this.zzc = true;     // Catch: Throwable -> L197
        if (r14 == null) goto L187;
        r14.close();
    L187:
        if (r13 == null) goto L206;
    L224:
    L197:
        th = th;
        Cursor r63 = r14;
    L207:
        if (r63 == null) goto L209;
        r63.close();
    L209:
        if (r13 == null) goto L211;
        r13.close();
    L211:
        throw th;
    L182:
        if (r13.inTransaction() == false) goto L184;
        r13.endTransaction();     // Catch: Throwable -> L197
        goto L184
    L13:
        return r6;
    L245:
        r13.beginTransaction();     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        Cursor r142 = r13.query("messages", new String[]{r5}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");     // Catch: Throwable -> L163
        long r22 = -1;
        if (r142.moveToFirst() == false) goto L29;
        long r15 = r142.getLong(r9);     // Catch: Throwable -> L27
        r142.close();     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
    L31:
        if (r15 == (-1)) goto L33;
        String[] r143 = new String[1];     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        r143[r9] = String.valueOf(r15);     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        String r16 = "rowid<?";
        String[] r172 = r143;
    L34:
        String[] r0 = {r5, r4, r3};     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        zzic r144 = this.zzu;     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        zzal r152 = r144.zzc();     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        int r302 = 1;
        zzfx r12 = zzfy.zzaW;     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        boolean r153 = r152.zzp(r6, r12);     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        int r25 = 4;
        int r26 = 3;
        int r64 = 2;
        if (r153 == false) goto L37;
        r0 = new String[r8];     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        r0[r9] = r5;     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        r0[1] = r4;     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        r0[2] = r3;     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        r0[3] = "app_version";     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        r0[4] = "app_version_int";     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
    L37:
        String[] r154 = r0;
        zzic r02 = r144;
        r14 = r13.query("messages", r154, r16, r172, null, null, "rowid asc", Integer.toString(100));     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
    L252:
        if (r14.moveToNext() == false) goto L157;
        r22 = r14.getLong(r9);     // Catch: SQLiteException -> L151 SQLiteDatabaseLockedException -> L153 SQLiteFullException -> L154 Throwable -> L197
        int r82 = r14.getInt(r302);     // Catch: SQLiteException -> L151 SQLiteDatabaseLockedException -> L153 SQLiteFullException -> L154 Throwable -> L197
        byte[] r155 = r14.getBlob(r64);     // Catch: SQLiteException -> L151 SQLiteDatabaseLockedException -> L153 SQLiteFullException -> L154 Throwable -> L197
        if (r02.zzc().zzp(null, r12) == false) goto L54;
        String r92 = r14.getString(r26);     // Catch: SQLiteException -> L46 SQLiteDatabaseLockedException -> L49 SQLiteFullException -> L51 Throwable -> L197
        long r192 = r14.getLong(r25);     // Catch: SQLiteException -> L46 SQLiteDatabaseLockedException -> L49 SQLiteFullException -> L51 Throwable -> L197
    L45:
        String r65 = r3;
        long r27 = r192;
        r19 = r4;
        if (r82 == 0) goto L254;
        zzic r20 = r02;
        r21 = r5;
        if (r82 != 1) goto L99;
        Parcel r52 = Parcel.obtain();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        r52.unmarshall(r155, 0, r155.length);     // Catch: Throwable -> L91 SafeParcelReader.ParseException -> L93
        r52.setDataPosition(0);     // Catch: Throwable -> L91 SafeParcelReader.ParseException -> L93
        zzpl r03 = zzpl.CREATOR.createFromParcel(r52);     // Catch: Throwable -> L91 SafeParcelReader.ParseException -> L93
        r52.recycle();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
    L95:
        if (r03 != null) goto L96;
    L64:
        r17 = r65;
        int r04 = 2;
    L65:
        int r42 = 3;
        r62 = 0;
    L150:
        r26 = r42;
        r9 = r62;
        r3 = r17;
        r4 = r19;
        r5 = r21;
        r302 = 1;
        r25 = 4;
        r64 = r04;
        r02 = r20;
        goto L252
    L96:
        r7.add(new zzgk(r03, r92, r27));     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        goto L64
    L91:
        th = move-exception;
        r52.recycle();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        throw th;     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
    L93:
        this.zzu.zzaW().zzb().zza("Failed to load user property from local database");     // Catch: Throwable -> L91
        r52.recycle();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        r03 = null;
        goto L95
    L99:
        r04 = 2;
        if (r82 != 2) goto L122;
        Parcel r53 = Parcel.obtain();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        r17 = r65;
        r53.unmarshall(r155, 0, r155.length);     // Catch: Throwable -> L111 SafeParcelReader.ParseException -> L219
        r53.setDataPosition(0);     // Catch: Throwable -> L111 SafeParcelReader.ParseException -> L219
        zzah r66 = zzah.CREATOR.createFromParcel(r53);     // Catch: Throwable -> L111 SafeParcelReader.ParseException -> L219
        goto L116
    L250:
        r53.recycle();     // Catch: SQLiteDatabaseLockedException -> L216 SQLiteException -> L107 SQLiteFullException -> L109 Throwable -> L197
    L118:
        if (r66 == null) goto L65;
        r7.add(new zzgk(r66, r92, r27));     // Catch: SQLiteDatabaseLockedException -> L216 SQLiteException -> L107 SQLiteFullException -> L109 Throwable -> L197
    L109:
        e = e;
    L53:
        r62 = 0;
    L107:
        e = e;
    L48:
        r62 = 0;
    L50:
        r62 = 0;
    L111:
        th = th;
    L120:
        r53.recycle();     // Catch: SQLiteDatabaseLockedException -> L216 SQLiteException -> L107 SQLiteFullException -> L109 Throwable -> L197
        throw th;     // Catch: SQLiteDatabaseLockedException -> L216 SQLiteException -> L107 SQLiteFullException -> L109 Throwable -> L197
    L116:
        this.zzu.zzaW().zzb().zza("Failed to load conditional user property from local database");     // Catch: Throwable -> L111
        r53.recycle();     // Catch: SQLiteDatabaseLockedException -> L216 SQLiteException -> L107 SQLiteFullException -> L109 Throwable -> L197
        r66 = null;
        goto L118
    L115:
        r17 = r65;
    L113:
        th = th;
        r17 = r65;
        goto L120
    L122:
        r17 = r65;
        if (r82 != 4) goto L146;
        Parcel r54 = Parcel.obtain();     // Catch: SQLiteDatabaseLockedException -> L216 SQLiteException -> L107 SQLiteFullException -> L109 Throwable -> L197
        r62 = 0;
        r54.unmarshall(r155, 0, r155.length);     // Catch: Throwable -> L134 SafeParcelReader.ParseException -> L220
        r54.setDataPosition(0);     // Catch: Throwable -> L134 SafeParcelReader.ParseException -> L220
        zzbf r83 = zzbf.CREATOR.createFromParcel(r54);     // Catch: Throwable -> L134 SafeParcelReader.ParseException -> L220
        goto L139
    L247:
        r54.recycle();     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
    L141:
        if (r83 == null) goto L143;
        r7.add(new zzgk(r83, r92, r27));     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
    L143:
        r42 = 3;
    L132:
        e = e;
    L130:
        e = e;
    L134:
        th = th;
    L144:
        r54.recycle();     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
        throw th;     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
    L139:
        this.zzu.zzaW().zzb().zza("Failed to load default event parameters from local database");     // Catch: Throwable -> L134
        r54.recycle();     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
        r83 = null;
        goto L141
    L138:
        r62 = 0;
    L136:
        th = th;
        goto L144
    L146:
        r62 = 0;
        zzic r32 = this.zzu;
        r42 = 3;
        if (r82 != 3) goto L149;
        r32.zzaW().zzk().zza("Skipping app launch break");     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
        goto L150
    L149:
        r32.zzaW().zzb().zza("Unknown record type in local database");     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
        goto L150
    L254:
        Parcel r84 = Parcel.obtain();     // Catch: SQLiteException -> L80 SQLiteDatabaseLockedException -> L82 SQLiteFullException -> L83 Throwable -> L197
        r20 = r02;
        r21 = r5;
        r84.unmarshall(r155, 0, r155.length);     // Catch: Throwable -> L71 SafeParcelReader.ParseException -> L218
        r84.setDataPosition(0);     // Catch: Throwable -> L71 SafeParcelReader.ParseException -> L218
        zzbh r05 = zzbh.CREATOR.createFromParcel(r84);     // Catch: Throwable -> L71 SafeParcelReader.ParseException -> L218
        goto L76
    L242:
        r84.recycle();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        if (r05 == null) goto L64;
        r7.add(new zzgk(r05, r92, r27));     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
    L69:
        e = e;
    L70:
        r17 = r65;
    L66:
        e = e;
    L67:
        r17 = r65;
    L68:
        r17 = r65;
    L71:
        th = th;
    L78:
        r84.recycle();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        throw th;     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
    L76:
        this.zzu.zzaW().zzb().zza("Failed to load event from local database");     // Catch: Throwable -> L71
        r84.recycle();     // Catch: SQLiteException -> L66 SQLiteDatabaseLockedException -> L215 SQLiteFullException -> L69 Throwable -> L197
        goto L64
    L75:
        r21 = r5;
    L73:
        th = th;
        goto L78
    L82:
        r21 = r5;
    L83:
        e = e;
        r21 = r5;
    L80:
        e = e;
        r21 = r5;
        goto L67
    L54:
        r192 = 0;
        r92 = null;
        goto L45
    L49:
        r17 = r3;
        r19 = r4;
        r21 = r5;
    L51:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
    L46:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        goto L48
    L157:
        if (r13.delete("messages", "rowid <= ?", new String[]{Long.toString(r22)}) >= r7.size()) goto L159;
        this.zzu.zzaW().zzb().zza("Fewer entries removed from local database than expected");     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
    L159:
        r13.setTransactionSuccessful();     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
        r13.endTransaction();     // Catch: SQLiteException -> L130 SQLiteFullException -> L132 SQLiteDatabaseLockedException -> L217 Throwable -> L197
        r14.close();
        r13.close();
        return r7;
    L153:
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
    L154:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
    L151:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
        goto L180
    L33:
        r16 = r6;
        r172 = r16;
        goto L34
    L29:
        r142.close();     // Catch: Throwable -> L14 SQLiteException -> L16 SQLiteDatabaseLockedException -> L18 SQLiteFullException -> L19
        r15 = -1;
    L27:
        th = th;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
    L165:
        if (r142 != null) goto L248;
    L171:
        throw th;     // Catch: Throwable -> L14 SQLiteException -> L167 SQLiteFullException -> L169 SQLiteDatabaseLockedException -> L221
    L169:
        e = e;
    L167:
        e = e;
        goto L173
    L248:
        r142.close();     // Catch: Throwable -> L14 SQLiteException -> L167 SQLiteFullException -> L169 SQLiteDatabaseLockedException -> L221
    L163:
        th = th;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
        r142 = null;
    L14:
        th = th;
        r63 = null;
        goto L207
    L189:
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
        r13 = null;
    L199:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
        r13 = null;
    L178:
        e = e;
        r17 = r3;
        r19 = r4;
        r21 = r5;
        r62 = r9;
        r13 = null;
    L176:
        th = th;
        r63 = null;
        r13 = null;
        goto L207
    L212:
        this.zzu.zzaW().zze().zza("Failed to read events from database in reasonable time");
        return null;
    L214:
        return r7;
    }

    public final boolean zzn() {
        return zzs(3, new byte[0]);
    }

    public final boolean zzo() {
        zzg();
        if (this.zzc == false) goto L6;
    L39:
        return false;
    L6:
        if (zzq() == false) goto L39;
        int r4 = 5;
        int r3 = 0;
    L8:
        if (r3 >= 5) goto L38;
        SQLiteDatabase r5 = null;
        SQLiteDatabase r52 = zzp();     // Catch: Throwable -> L16 SQLiteException -> L18 SQLiteDatabaseLockedException -> L26 SQLiteFullException -> L30
        if (r52 != null) goto L13;
        this.zzc = true;     // Catch: Throwable -> L16 SQLiteException -> L18 SQLiteDatabaseLockedException -> L26 SQLiteFullException -> L30
        goto L39
    L13:
        r52.beginTransaction();     // Catch: Throwable -> L16 SQLiteException -> L18 SQLiteDatabaseLockedException -> L26 SQLiteFullException -> L30
        r52.delete("messages", "type == ?", new String[]{Integer.toString(3)});     // Catch: Throwable -> L16 SQLiteException -> L18 SQLiteDatabaseLockedException -> L26 SQLiteFullException -> L30
        r52.setTransactionSuccessful();     // Catch: Throwable -> L16 SQLiteException -> L18 SQLiteDatabaseLockedException -> L26 SQLiteFullException -> L30
        r52.endTransaction();     // Catch: Throwable -> L16 SQLiteException -> L18 SQLiteDatabaseLockedException -> L26 SQLiteFullException -> L30
        r52.close();
        return true;
    L18:
        e = move-exception;
        if (0 != 0) goto L40;
    L23:
        this.zzu.zzaW().zzb().zzb("Error deleting app launch break from local database", e);     // Catch: Throwable -> L16
        this.zzc = true;     // Catch: Throwable -> L16
        if (0 == 0) goto L34;
    L29:
        r5.close();
    L34:
        r3 = r3 + 1;
        goto L8
    L40:
        if (r5.inTransaction() == false) goto L23;
        r5.endTransaction();     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        if (0 == 0) goto L37;
        r5.close();
    L37:
        throw th;
    L30:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Error deleting app launch break from local database", e);     // Catch: Throwable -> L16
        this.zzc = true;     // Catch: Throwable -> L16
        if (0 == 0) goto L34;
    L26:
        SystemClock.sleep(r4);     // Catch: Throwable -> L16
        r4 = r4 + 20;
        if (0 == 0) goto L34;
    L38:
        this.zzu.zzaW().zze().zza("Error deleting app launch break from local database in reasonable time");
        goto L39
    }

    public final SQLiteDatabase zzp() {
        if (this.zzc == false) goto L5;
        return null;
    L5:
        SQLiteDatabase r0 = this.zzb.getWritableDatabase();
        if (r0 != null) goto L9;
        this.zzc = true;
        return null;
    L9:
        return r0;
    }

    public final boolean zzq() {
        zzic r1 = this.zzu;
        Context r0 = r1.zzaZ();
        r1.zzc();
        return r0.getDatabasePath("google_app_measurement_local.db").exists();
    }
}
