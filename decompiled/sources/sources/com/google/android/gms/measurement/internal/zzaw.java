package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzahk;
import com.google.android.gms.internal.measurement.zzaif;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;

/* loaded from: classes5.dex */
final class zzaw extends zzos {
    static final String[] zza = null;
    private static final String[] zzb = null;
    private static final String[] zzc = null;
    private static final String[] zzd = null;
    private static final String[] zze = null;
    private static final String[] zzf = null;
    private static final String[] zzh = null;
    private static final String[] zzi = null;
    private static final String[] zzj = null;
    private static final String[] zzk = null;
    private static final String[] zzl = null;
    private final zzav zzm;
    private final zzog zzn;

    static {
        zzb = new String[]{"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
        zza = new String[]{"associated_row_id", "ALTER TABLE upload_queue ADD COLUMN associated_row_id INTEGER;", "last_upload_timestamp", "ALTER TABLE upload_queue ADD COLUMN last_upload_timestamp INTEGER;"};
        zzc = new String[]{"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
        zzd = new String[]{"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;", "daily_registered_triggers_count", "ALTER TABLE apps ADD COLUMN daily_registered_triggers_count INTEGER;", "client_upload_eligibility", "ALTER TABLE apps ADD COLUMN client_upload_eligibility INTEGER;", "gmp_version_for_remote_config", "ALTER TABLE apps ADD COLUMN gmp_version_for_remote_config INTEGER;", "last_diagnostics_signal_upload_timestamp", "ALTER TABLE apps ADD COLUMN last_diagnostics_signal_upload_timestamp INTEGER;"};
        zze = new String[]{"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;", "elapsed_time", "ALTER TABLE raw_events ADD COLUMN elapsed_time INTEGER;"};
        zzf = new String[]{"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
        zzh = new String[]{"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
        zzi = new String[]{"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
        zzj = new String[]{"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
        zzk = new String[]{"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
        zzl = new String[]{"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};
    }

    public zzaw(zzpg r3) {
        super(r3);
        this.zzn = new zzog(this.zzu.zzba());
        this.zzu.zzc();
        this.zzm = new zzav(this, this.zzu.zzaZ(), "google_app_measurement.db");
    }

    private final long zzaA(String r3, String[] r4) {
        Cursor r1 = null;
        Cursor r12 = zze().rawQuery(r3, r4);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r12.moveToFirst() == false) goto L9;
        long r2 = r12.getLong(0);     // Catch: Throwable -> L10 SQLiteException -> L12
        r12.close();
        return r2;
    L9:
        throw new SQLiteException("Database returned empty set");     // Catch: Throwable -> L10 SQLiteException -> L12
    L10:
        th = move-exception;
        if (0 == 0) goto L17;
        r1.close();
    L17:
        throw th;
    L12:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Database error", r3, e);     // Catch: Throwable -> L10
        throw e;     // Catch: Throwable -> L10
    }

    private final long zzaB(String r3, String[] r4, long r5) {
        Cursor r1 = null;
        r1 = zze().rawQuery(r3, r4);     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r1.moveToFirst() == false) goto L6;
        r5 = r1.getLong(0);     // Catch: Throwable -> L8 SQLiteException -> L10
    L6:
        r1.close();
        return r5;
    L10:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Database error", r3, e);     // Catch: Throwable -> L8
        throw e;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        if (r1 == null) goto L15;
        r1.close();
    L15:
        throw th;
    }

    private final String zzaC(String r2, String[] r3, String r4) {
        Cursor r0 = null;
        r0 = zze().rawQuery(r2, r3);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r0.moveToFirst() == false) goto L8;
        String r1 = r0.getString(0);     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.close();
        return r1;
    L8:
        r0.close();
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Database error", r2, e);     // Catch: Throwable -> L10
        throw e;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        if (r0 == null) goto L17;
        r0.close();
    L17:
        throw th;
    }

    private final void zzaD(String r7, String r8, ContentValues r9) {
        SQLiteDatabase r1 = zze();     // Catch: SQLiteException -> L7
        if (r9.getAsString("app_id") != null) goto L9;
        this.zzu.zzaW().zzd().zzb("Value of the primary key is not set.", zzgu.zzl("app_id"));     // Catch: SQLiteException -> L7
        return;
    L9:
        new StringBuilder(10).append("app_id = ?");     // Catch: SQLiteException -> L7
        if (r1.update("consent_settings", r9, r3.toString(), new String[]{r2}) == 0) goto L12;
        return;
    L12:
        if (r1.insertWithOnConflict("consent_settings", null, r9, 5) != (-1)) goto L19;
        this.zzu.zzaW().zzb().zzc("Failed to insert/update table (got -1). key", zzgu.zzl("consent_settings"), zzgu.zzl("app_id"));     // Catch: SQLiteException -> L7
        return;
    L19:
        return;
    L7:
        e = move-exception;
        this.zzu.zzaW().zzb().zzd("Error storing into table. key", zzgu.zzl("consent_settings"), zzgu.zzl("app_id"), e);
    }

    private final zzbd zzaE(String r30, String r31, String r32) {
        Preconditions.checkNotEmpty(r31);
        Preconditions.checkNotEmpty(r32);
        zzg();
        zzay();
        Cursor r2 = null;
        boolean r11 = false;
        Cursor r3 = zze().query(r30, (String[]) new ArrayList(Arrays.asList(new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count"})).toArray(new String[0]), "app_id=? and name=?", new String[]{r31, r32}, null, null, null);     // Catch: Throwable -> L44 SQLiteException -> L46
    L31:
        e = e;
    L48:
        zzic r1 = this.zzu;     // Catch: Throwable -> L29
        r1.zzaW().zzb().zzd("Error querying events. appId", zzgu.zzl(r31), r1.zzl().zza(r32), e);     // Catch: Throwable -> L29
    L49:
        if (r3 == null) goto L51;
        r3.close();
    L51:
        return null;
    L29:
        th = th;
        r2 = r3;
    L52:
        if (r2 == null) goto L54;
        r2.close();
    L54:
        throw th;
    L5:
        if (r3.moveToFirst() == false) goto L49;
        long r15 = r3.getLong(0);     // Catch: Throwable -> L29 SQLiteException -> L31
        long r17 = r3.getLong(1);     // Catch: Throwable -> L29 SQLiteException -> L31
        long r21 = r3.getLong(2);     // Catch: Throwable -> L29 SQLiteException -> L31
        long r6 = 0;
        if (r3.isNull(3) == false) goto L10;
        long r23 = 0;
    L12:
        if (r3.isNull(4) == false) goto L14;
        Long r25 = null;
    L16:
        if (r3.isNull(5) == false) goto L18;
        Long r26 = null;
    L20:
        if (r3.isNull(6) == false) goto L22;
        Long r27 = null;
    L24:
        if (r3.isNull(7) == false) goto L26;
        Boolean r28 = null;
    L35:
        if (r3.isNull(8) == true) goto L38;
        r6 = r3.getLong(8);     // Catch: Throwable -> L29 SQLiteException -> L31
    L38:
        zzbd r12 = new zzbd(r31, r32, r15, r17, r6, r21, r23, r25, r26, r27, r28);     // Catch: Throwable -> L29 SQLiteException -> L31
        if (r3.moveToNext() == false) goto L41;
        this.zzu.zzaW().zzb().zzb("Got multiple records for event aggregates, expected one. appId", zzgu.zzl(r31));     // Catch: Throwable -> L29 SQLiteException -> L31
    L41:
        r3.close();
        return r12;
    L26:
        if (r3.getLong(7) != 1) goto L28;
        r11 = true;
    L28:
        r28 = Boolean.valueOf(r11);     // Catch: Throwable -> L29 SQLiteException -> L31
        goto L35
    L22:
        r27 = Long.valueOf(r3.getLong(6));     // Catch: Throwable -> L29 SQLiteException -> L31
        goto L24
    L18:
        r26 = Long.valueOf(r3.getLong(5));     // Catch: Throwable -> L29 SQLiteException -> L31
        goto L20
    L14:
        r25 = Long.valueOf(r3.getLong(4));     // Catch: Throwable -> L29 SQLiteException -> L31
        goto L16
    L10:
        r23 = r3.getLong(3);     // Catch: Throwable -> L29 SQLiteException -> L31
        goto L12
    L46:
        e = e;
        r3 = null;
    L44:
        th = th;
        goto L52
    }

    private final void zzaF(String r7, zzbd r8) {
        Preconditions.checkNotNull(r8);
        zzg();
        zzay();
        ContentValues r0 = new ContentValues();
        String r1 = r8.zza;
        r0.put("app_id", r1);
        r0.put("name", r8.zzb);
        r0.put("lifetime_count", Long.valueOf(r8.zzc));
        r0.put("current_bundle_count", Long.valueOf(r8.zzd));
        r0.put("last_fire_timestamp", Long.valueOf(r8.zzf));
        r0.put("last_bundled_timestamp", Long.valueOf(r8.zzg));
        r0.put("last_bundled_day", r8.zzh);
        r0.put("last_sampled_complex_event_id", r8.zzi);
        r0.put("last_sampling_rate", r8.zzj);
        r0.put("current_session_count", Long.valueOf(r8.zze));
        Boolean r2 = r8.zzk;
        if (r2 != null) goto L5;
    L7:
        Long r22 = null;
    L8:
        r0.put("last_exempt_from_sampling", r22);
    L13:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing event aggregates. appId", zzgu.zzl(r8.zza), e);
        return;
    L10:
        if (zze().insertWithOnConflict(r7, null, r0, 5) != (-1)) goto L15;
        this.zzu.zzaW().zzb().zzb("Failed to insert/update event aggregates (got -1). appId", zzgu.zzl(r1));     // Catch: SQLiteException -> L13
        return;
    L15:
        return;
    L5:
        if (r2.booleanValue() == false) goto L7;
        r22 = 1L;
        goto L8
    }

    private final void zzaG(String r4, String r5) {
        Preconditions.checkNotEmpty(r5);
        zzg();
        zzay();
        zze().delete(r4, "app_id=?", new String[]{r5});     // Catch: SQLiteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error deleting snapshot. appId", zzgu.zzl(r5), e);
    }

    private final zzpj zzaH(String r14, long r15, byte[] r17, String r18, String r19, int r20, int r21, long r22, long r24, long r26) {
        if (TextUtils.isEmpty(r18) == false) goto L33;
        this.zzu.zzaW().zzj().zza("Upload uri is null or empty. Destination is unknown. Dropping batch. ");
        return null;
    L33:
        com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) zzpk.zzw(com.google.android.gms.internal.measurement.zzib.zzi(), r17);     // Catch: IOException -> L15
        zzls r4 = zzls.zzb(r20);     // Catch: IOException -> L15
        if (r4 != zzls.zzb) goto L9;
    L18:
        HashMap r5 = new HashMap();     // Catch: IOException -> L15
        if (r19 == null) goto L29;
        String[] r0 = r19.split("\r\n");     // Catch: IOException -> L15
        int r6 = r0.length;     // Catch: IOException -> L15
        int r8 = 0;
    L21:
        if (r8 >= r6) goto L29;
        String r9 = r0[r8];     // Catch: IOException -> L15
        if (r9.isEmpty() == true) goto L29;
        String[] r10 = r9.split("=", 2);     // Catch: IOException -> L15
        if (r10.length != 2) goto L27;
        r5.put(r10[0], r10[1]);     // Catch: IOException -> L15
        r8 = r8 + 1;     // Catch: IOException -> L15
        goto L21
    L27:
        this.zzu.zzaW().zzb().zzb("Invalid upload header: ", r9);     // Catch: IOException -> L15
    L29:
        zzpi r02 = new zzpi();     // Catch: IOException -> L15
        r02.zzb(r15);     // Catch: IOException -> L15
        r02.zzc((com.google.android.gms.internal.measurement.zzib) r2.zzbd());     // Catch: IOException -> L15
        r02.zzd(r18);     // Catch: IOException -> L15
        r02.zze(r5);     // Catch: IOException -> L15
        r02.zzf(r4);     // Catch: IOException -> L15
        r02.zzg(r22);     // Catch: IOException -> L15
        r02.zzh(r24);     // Catch: IOException -> L15
        r02.zzi(r26);     // Catch: IOException -> L15
        r02.zzj(r21);     // Catch: IOException -> L15
        return r02.zza();
    L9:
        if (r4 == zzls.zze) goto L18;
        if (r21 <= 0) goto L18;
        ArrayList r52 = new ArrayList();     // Catch: IOException -> L15
        Iterator r62 = r2.zza().iterator();     // Catch: IOException -> L15
    L13:
        if (r62.hasNext() == false) goto L17;
        com.google.android.gms.internal.measurement.zzic r7 = (com.google.android.gms.internal.measurement.zzic) ((com.google.android.gms.internal.measurement.zzid) r62.next()).zzco();     // Catch: IOException -> L15
        r7.zzao(r21);     // Catch: IOException -> L15
        r52.add((com.google.android.gms.internal.measurement.zzid) r7.zzbd());     // Catch: IOException -> L15
        goto L13
    L17:
        r2.zzg();     // Catch: IOException -> L15
        r2.zzf(r52);     // Catch: IOException -> L15
    L15:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Failed to queued MeasurementBatch from upload_queue. appId", r14, e);
        return null;
    }

    private final String zzaI() {
        zzic r6 = this.zzu;
        long r0 = r6.zzba().currentTimeMillis();
        Locale r2 = Locale.US;
        zzls r3 = zzls.zzb;
        Integer r4 = Integer.valueOf(r3.zza());
        Long r02 = Long.valueOf(r0);
        r6.zzc();
        Long r1 = (Long) zzfy.zzS.zzb(null);
        r1.longValue();
        String r12 = String.format(r2, "(upload_type = %d AND ABS(creation_timestamp - %d) > %d)", new Object[]{r4, r02, r1});
        Integer r32 = Integer.valueOf(r3.zza());
        r6.zzc();
        String r62 = String.format(r2, "(upload_type != %d AND ABS(creation_timestamp - %d) > %d)", new Object[]{r32, r02, Long.valueOf(zzal.zzI())});
        int r03 = r12.length();
        int r04 = r03 + 5;
        StringBuilder r33 = new StringBuilder((r04 + r62.length()) + 1);
        r33.append("(");
        r33.append(r12);
        r33.append(" OR ");
        r33.append(r62);
        r33.append(")");
        return r33.toString();
    }

    private static final String zzaJ(List r1) {
        if (r1.isEmpty() == false) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return String.format(" AND (upload_type IN (%s))", new Object[]{TextUtils.join(", ", r1)});
    }

    public static /* synthetic */ String[] zzak() {
        return zzb;
    }

    public static /* synthetic */ String[] zzal() {
        return zzc;
    }

    public static /* synthetic */ String[] zzam() {
        return zzd;
    }

    public static /* synthetic */ String[] zzan() {
        return zze;
    }

    public static /* synthetic */ String[] zzao() {
        return zzf;
    }

    public static /* synthetic */ String[] zzap() {
        return zzh;
    }

    public static /* synthetic */ String[] zzaq() {
        return zzi;
    }

    public static /* synthetic */ String[] zzar() {
        return zzj;
    }

    public static /* synthetic */ String[] zzas() {
        return zzk;
    }

    public static /* synthetic */ String[] zzat() {
        return zzl;
    }

    public static final void zzaw(ContentValues r1, String r2, Object r3) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(r3);
        if ((r3 instanceof String) == false) goto L7;
        r1.put("value", (String) r3);
        return;
    L7:
        if ((r3 instanceof Long) == false) goto L11;
        r1.put("value", (Long) r3);
        return;
    L11:
        if ((r3 instanceof Double) == false) goto L14;
        r1.put("value", (Double) r3);
        return;
    L14:
        f$$ExternalSyntheticBUOutline1.m("Invalid value type");
    }

    public final long zzA(String r12, com.google.android.gms.internal.measurement.zzib r13, String r14, Map r15, zzls r16, Long r17) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r13);
        Preconditions.checkNotEmpty(r12);
        zzg();
        zzay();
        if (zzai() == false) goto L20;
        zzpg r0 = this.zzg;
        long r4 = r0.zzq().zzb.zza();
        zzic r6 = this.zzu;
        long r7 = r6.zzba().elapsedRealtime();
        long r42 = Math.abs(r7 - r4);
        r6.zzc();
        if (r42 <= zzal.zzJ()) goto L20;
        r0.zzq().zzb.zzb(r7);
        zzg();
        zzay();
        if (zzai() == false) goto L13;
        int r02 = zze().delete("upload_queue", zzaI(), new String[0]);
        if (r02 <= 0) goto L13;
        r6.zzaW().zzk().zzb("Deleted stale MeasurementBatch rows from upload_queue. rowsDeleted", Integer.valueOf(r02));
    L13:
        Preconditions.checkNotEmpty(r12);
        zzg();
        zzay();
        int r03 = r6.zzc().zzm(r12, zzfy.zzz);     // Catch: SQLiteException -> L18
        if (r03 <= 0) goto L20;
        zze().delete("upload_queue", "rowid in (SELECT rowid FROM upload_queue WHERE app_id=? ORDER BY rowid DESC LIMIT -1 OFFSET ?)", new String[]{r12, String.valueOf(r03)});     // Catch: SQLiteException -> L18
    L18:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error deleting over the limit queued batches. appId", zzgu.zzl(r12), e);
    L20:
        ArrayList r04 = new ArrayList();
        Iterator r43 = r15.entrySet().iterator();
    L22:
        if (r43.hasNext() == false) goto L24;
        Map.Entry r5 = (Map.Entry) r43.next();
        String r62 = (String) r5.getKey();
        String r52 = (String) r5.getValue();
        StringBuilder r9 = new StringBuilder((String.valueOf(r62).length() + 1) + String.valueOf(r52).length());
        r9.append(r62);
        r9.append("=");
        r9.append(r52);
        r04.add(r9.toString());
        goto L22
    L24:
        byte[] r132 = r13.zzcd();
        ContentValues r44 = new ContentValues();
        r44.put("app_id", r12);
        r44.put("measurement_batch", r132);
        r44.put("upload_uri", r14);
        r44.put("upload_headers", TextUtils.join("\r\n", r04));
        r44.put("upload_type", Integer.valueOf(r16.zza()));
        zzic r133 = this.zzu;
        r44.put("creation_timestamp", Long.valueOf(r133.zzba().currentTimeMillis()));
        r44.put("retry_count", 0);
        if (r17 == null) goto L40;
        r44.put("associated_row_id", r17);
    L40:
        long r3 = zze().insert("upload_queue", null, r44);     // Catch: SQLiteException -> L32
        if (r3 != (-1)) goto L35;
        r133.zzaW().zzb().zzb("Failed to insert MeasurementBatch (got -1) to upload_queue. appId", r12);     // Catch: SQLiteException -> L32
        return -1;
    L35:
        return r3;
    L32:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing MeasurementBatch to upload_queue. appId", r12, e);
        return -1;
    }

    public final zzpj zzB(long r19) {
        zzg();
        zzay();
        Cursor r1 = null;
        Cursor r2 = zze().query("upload_queue", new String[]{"rowId", "app_id", "measurement_batch", "upload_uri", "upload_headers", "upload_type", "retry_count", "creation_timestamp", "associated_row_id", "last_upload_timestamp"}, "rowId=?", new String[]{String.valueOf(r19)}, null, null, null, "1");     // Catch: Throwable -> L16 SQLiteException -> L18
    L12:
        e = e;
    L20:
        this.zzu.zzaW().zzb().zzc("Error to querying MeasurementBatch from upload_queue. rowId", Long.valueOf(r19), e);     // Catch: Throwable -> L10
    L21:
        if (r2 == null) goto L23;
        r2.close();
    L23:
        return null;
    L10:
        th = th;
        r1 = r2;
    L24:
        if (r1 == null) goto L26;
        r1.close();
    L26:
        throw th;
    L5:
        if (r2.moveToFirst() == false) goto L21;
        zzpj r0 = zzaH((String) Preconditions.checkNotNull(r2.getString(1)), r19, r2.getBlob(2), r2.getString(3), r2.getString(4), r2.getInt(5), r2.getInt(6), r2.getLong(7), r2.getLong(8), r2.getLong(9));     // Catch: Throwable -> L10 SQLiteException -> L12
        r2.close();
        return r0;
    L18:
        e = e;
        r2 = null;
    L16:
        th = th;
        goto L24
    }

    public final List zzC(String r19, zzoo r20, int r21) {
        Preconditions.checkNotEmpty(r19);
        zzg();
        zzay();
        Cursor r2 = null;
        SQLiteDatabase r3 = zze();     // Catch: Throwable -> L14 SQLiteException -> L16
        String[] r5 = {"rowId", "app_id", "measurement_batch", "upload_uri", "upload_headers", "upload_type", "retry_count", "creation_timestamp", "associated_row_id", "last_upload_timestamp"};     // Catch: Throwable -> L14 SQLiteException -> L16
        String r6 = zzaJ(r20.zza);     // Catch: Throwable -> L14 SQLiteException -> L16
        String r7 = zzaI();     // Catch: Throwable -> L14 SQLiteException -> L16
        StringBuilder r9 = new StringBuilder((String.valueOf(r6).length() + 17) + r7.length());     // Catch: Throwable -> L14 SQLiteException -> L16
        r9.append("app_id=?");     // Catch: Throwable -> L14 SQLiteException -> L16
        r9.append(r6);     // Catch: Throwable -> L14 SQLiteException -> L16
        r9.append(" AND NOT ");     // Catch: Throwable -> L14 SQLiteException -> L16
        r9.append(r7);     // Catch: Throwable -> L14 SQLiteException -> L16
        String r62 = r9.toString();     // Catch: Throwable -> L14 SQLiteException -> L16
        String[] r72 = {r19};     // Catch: Throwable -> L14 SQLiteException -> L16
        if (r21 <= 0) goto L6;
        String r11 = String.valueOf(r21);     // Catch: Throwable -> L14 SQLiteException -> L16
    L7:
        r2 = r3.query("upload_queue", r5, r62, r72, null, null, "creation_timestamp ASC", r11);     // Catch: Throwable -> L14 SQLiteException -> L16
        List r0 = new ArrayList();     // Catch: Throwable -> L14 SQLiteException -> L16
    L8:
        if (r2.moveToNext() == false) goto L19;
        zzpj r1 = zzaH(r19, r2.getLong(0), r2.getBlob(2), r2.getString(3), r2.getString(4), r2.getInt(5), r2.getInt(6), r2.getLong(7), r2.getLong(8), r2.getLong(9));     // Catch: Throwable -> L14 SQLiteException -> L16
        if (r1 == null) goto L8;
        r0.add(r1);     // Catch: Throwable -> L14 SQLiteException -> L16
    L19:
        if (r2 == null) goto L21;
        r2.close();
    L21:
        return r0;
    L6:
        r11 = null;
    L16:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error to querying MeasurementBatch from upload_queue. appId", r19, e);     // Catch: Throwable -> L14
        r0 = Collections.EMPTY_LIST;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        if (r2 == null) goto L24;
        r2.close();
    L24:
        throw th;
    }

    public final boolean zzD(String r8) {
        zzls[] r0 = {zzls.zzb};
        ArrayList r1 = new ArrayList(1);
        r1.add(Integer.valueOf(r0[0].zza()));
        String r02 = zzaJ(r1);
        String r12 = zzaI();
        int r4 = String.valueOf(r02).length();
        int r42 = r4 + 61;
        StringBuilder r6 = new StringBuilder(r42 + r12.length());
        r6.append("SELECT COUNT(1) > 0 FROM upload_queue WHERE app_id=?");
        r6.append(r02);
        r6.append(" AND NOT ");
        r6.append(r12);
        if (zzaA(r6.toString(), new String[]{r8}) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final void zzE(Long r4) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r4);
    L7:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to delete a MeasurementBatch in a upload_queue table", e);
        throw e;
    L4:
        if (zze().delete("upload_queue", "rowid=?", new String[]{r4.toString()}) == 1) goto L9;
        this.zzu.zzaW().zze().zza("Deleted fewer rows from upload_queue than expected");     // Catch: SQLiteException -> L7
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public final String zzF() {
        SQLiteDatabase r0 = zze();
        ?? r1 = 0;
        Cursor r02 = r0.rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);     // Catch: Throwable -> L14 SQLiteException -> L16
    L11:
        SQLiteException e = e;
    L18:
        this.zzu.zzaW().zzb().zzb("Database error getting next bundle app id", e);     // Catch: Throwable -> L9
    L19:
        if (r02 == null) goto L21;
        r02.close();
    L21:
        return null;
    L5:
        if (r02.moveToFirst() == false) goto L19;
        String r4 = r02.getString(0);     // Catch: Throwable -> L9 SQLiteException -> L11
        r02.close();
        return r4;
    L9:
        th = th;
        r1 = r0;
    L22:
        if (r1 == 0) goto L24;
        r1.close();
    L24:
        throw th;
    L16:
        e = move-exception;
        e = e;
        r02 = null;
    L14:
        th = th;
        goto L22
    }

    public final boolean zzG() {
        if (zzaA("select count(1) > 0 from queue where has_realtime = 1", null) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzH(long r3) {
        zzg();
        zzay();
    L8:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to delete a bundle in a queue table", e);
        throw e;
    L4:
        if (zze().delete("queue", "rowid=?", new String[]{String.valueOf(r3)}) != 1) goto L7;
        return;
    L7:
        throw new SQLiteException("Deleted fewer rows from queue than expected");     // Catch: SQLiteException -> L8
    }

    public final void zzI() {
        zzg();
        zzay();
        if (zzai() == false) goto L16;
        zzpg r0 = this.zzg;
        long r1 = r0.zzq().zza.zza();
        zzic r3 = this.zzu;
        long r4 = r3.zzba().elapsedRealtime();
        long r12 = Math.abs(r4 - r1);
        r3.zzc();
        if (r12 <= zzal.zzJ()) goto L13;
        r0.zzq().zza.zzb(r4);
        zzg();
        zzay();
        if (zzai() == false) goto L14;
        SQLiteDatabase r8 = zze();
        String r02 = String.valueOf(r3.zzba().currentTimeMillis());
        r3.zzc();
        int r82 = r8.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{r02, String.valueOf(zzal.zzI())});
        if (r82 <= 0) goto L15;
        r3.zzaW().zzk().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(r82));
        return;
    L15:
        return;
    L14:
        return;
    L13:
        return;
    }

    public final void zzJ(List r7) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r7);
        Preconditions.checkNotZero(r7.size());
        if (zzai() == true) goto L5;
        return;
    L5:
        String r72 = TextUtils.join(",", r7);
        StringBuilder r3 = new StringBuilder(String.valueOf(r72).length() + 2);
        r3.append("(");
        r3.append(r72);
        r3.append(")");
        String r73 = r3.toString();
        StringBuilder r32 = new StringBuilder(r73.length() + 80);
        r32.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
        r32.append(r73);
        r32.append(" AND retry_count =  2147483647 LIMIT 1");
        if (zzaA(r32.toString(), null) <= 0) goto L13;
        this.zzu.zzaW().zze().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
    L13:
        SQLiteDatabase r2 = zze();     // Catch: SQLiteException -> L10
        StringBuilder r4 = new StringBuilder(r73.length() + 127);     // Catch: SQLiteException -> L10
        r4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");     // Catch: SQLiteException -> L10
        r4.append(r73);     // Catch: SQLiteException -> L10
        r4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");     // Catch: SQLiteException -> L10
        r2.execSQL(r4.toString());     // Catch: SQLiteException -> L10
        return;
    L10:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Error incrementing retry count. error", e);
    }

    public final void zzK(Long r10) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r10);
        if (zzai() == true) goto L5;
        return;
    L5:
        StringBuilder r5 = new StringBuilder(r10.toString().length() + 86);
        r5.append("SELECT COUNT(1) FROM upload_queue WHERE rowid = ");
        r5.append(r10);
        r5.append(" AND retry_count =  2147483647 LIMIT 1");
        if (zzaA(r5.toString(), null) <= 0) goto L13;
        this.zzu.zzaW().zze().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
    L13:
        SQLiteDatabase r4 = zze();     // Catch: SQLiteException -> L10
        long r52 = this.zzu.zzba().currentTimeMillis();     // Catch: SQLiteException -> L10
        StringBuilder r8 = new StringBuilder(String.valueOf(r52).length() + 60);     // Catch: SQLiteException -> L10
        r8.append(" SET retry_count = retry_count + 1, last_upload_timestamp = ");     // Catch: SQLiteException -> L10
        r8.append(r52);     // Catch: SQLiteException -> L10
        String r0 = r8.toString();     // Catch: SQLiteException -> L10
        StringBuilder r6 = new StringBuilder(((r0.length() + 34) + r10.toString().length()) + 29);     // Catch: SQLiteException -> L10
        r6.append("UPDATE upload_queue");     // Catch: SQLiteException -> L10
        r6.append(r0);     // Catch: SQLiteException -> L10
        r6.append(" WHERE rowid = ");     // Catch: SQLiteException -> L10
        r6.append(r10);     // Catch: SQLiteException -> L10
        r6.append(" AND retry_count < 2147483647");     // Catch: SQLiteException -> L10
        r4.execSQL(r6.toString());     // Catch: SQLiteException -> L10
        return;
    L10:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Error incrementing retry count. error", e);
    }

    public final Object zzL(Cursor r4, int r5) {
        int r0 = r4.getType(r5);
        if (r0 != 0) goto L5;
        this.zzu.zzaW().zzb().zza("Loaded invalid null value from database");
        return null;
    L5:
        if (r0 == 1) goto L21;
        if (r0 == 2) goto L19;
        if (r0 == 3) goto L17;
        zzic r3 = this.zzu;
        if (r0 == 4) goto L14;
        r3.zzaW().zzb().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(r0));
        return null;
    L14:
        r3.zzaW().zzb().zza("Loaded invalid blob type value, ignoring it");
        return null;
    L17:
        return r4.getString(r5);
    L19:
        return Double.valueOf(r4.getDouble(r5));
    L21:
        return Long.valueOf(r4.getLong(r5));
    }

    public final long zzM() {
        return zzaB("select max(bundle_end_timestamp) from queue", null, 0);
    }

    public final long zzN(String r12, String r13) {
        Preconditions.checkNotEmpty(r12);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzay();
        SQLiteDatabase r1 = zze();
        r1.beginTransaction();
        long r2 = 0;
        StringBuilder r4 = new StringBuilder(48);     // Catch: Throwable -> L10 SQLiteException -> L12
        r4.append("select first_open_count from app2 where app_id=?");     // Catch: Throwable -> L10 SQLiteException -> L12
        long r5 = -1;
        long r7 = zzaB(r4.toString(), new String[]{r12}, -1);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r7 != (-1)) goto L30;
        ContentValues r132 = new ContentValues();     // Catch: Throwable -> L10 SQLiteException -> L12
        r132.put("app_id", r12);     // Catch: Throwable -> L10 SQLiteException -> L12
        r132.put("first_open_count", 0);     // Catch: Throwable -> L10 SQLiteException -> L12
        r132.put("previous_install_count", 0);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r1.insertWithOnConflict("app2", null, r132, 5) != (-1)) goto L14;
        this.zzu.zzaW().zzb().zzc("Failed to insert column (got -1). appId", zzgu.zzl(r12), "first_open_count");     // Catch: Throwable -> L10 SQLiteException -> L12
    L25:
        r1.endTransaction();
        return r5;
    L14:
        r7 = 0;
    L30:
        ContentValues r133 = new ContentValues();     // Catch: Throwable -> L10 SQLiteException -> L18
        r133.put("app_id", r12);     // Catch: Throwable -> L10 SQLiteException -> L18
        r133.put("first_open_count", Long.valueOf(1 + r7));     // Catch: Throwable -> L10 SQLiteException -> L18
        if (r1.update("app2", r133, "app_id = ?", new String[]{r12}) != 0) goto L20;
        this.zzu.zzaW().zzb().zzc("Failed to update column (got 0). appId", zzgu.zzl(r12), "first_open_count");     // Catch: Throwable -> L10 SQLiteException -> L18
    L29:
        goto L25
    L20:
        r1.setTransactionSuccessful();     // Catch: Throwable -> L10 SQLiteException -> L18
        r5 = r7;
    L18:
        e = e;
        r2 = r7;
    L23:
        this.zzu.zzaW().zzb().zzd("Error inserting column. appId", zzgu.zzl(r12), "first_open_count", e);     // Catch: Throwable -> L10
        r5 = r2;
    L10:
        th = move-exception;
        r1.endTransaction();
        throw th;
    L12:
        e = e;
        goto L23
    }

    public final long zzO() {
        return zzaB("select max(timestamp) from raw_events", null, 0);
    }

    public final boolean zzP() {
        if (zzaA("select count(1) > 0 from raw_events", null) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzQ(String r3, String r4) {
        if (zzaA("select count(1) from raw_events where app_id = ? and name = ?", new String[]{r3, r4}) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzR() {
        if (zzaA("select count(1) > 0 from raw_events where realtime = 1", null) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzS(List r5) {
        Preconditions.checkNotNull(r5);
        zzg();
        zzay();
        StringBuilder r0 = new StringBuilder("rowid in (");
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L8;
        if (r1 == 0) goto L7;
        r0.append(",");
    L7:
        r0.append(((Long) r5.get(r1)).longValue());
        r1 = r1 + 1;
        goto L4
    L8:
        r0.append(")");
        int r02 = zze().delete("raw_events", r0.toString(), null);
        if (r02 == r5.size()) goto L15;
        this.zzu.zzaW().zzb().zzc("Deleted fewer rows from raw events table than expected", Integer.valueOf(r02), Integer.valueOf(r5.size()));
        return;
    }

    public final long zzT(String r4) {
        Preconditions.checkNotEmpty(r4);
        return zzaB("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{r4}, 0);
    }

    public final boolean zzU(String r6, Long r7, long r8, com.google.android.gms.internal.measurement.zzhs r10) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r10);
        Preconditions.checkNotEmpty(r6);
        Preconditions.checkNotNull(r7);
        zzic r0 = this.zzu;
        byte[] r102 = r10.zzcd();
        r0.zzaW().zzk().zzc("Saving complex main event, appId, data size", r0.zzl().zza(r6), Integer.valueOf(r102.length));
        ContentValues r1 = new ContentValues();
        r1.put("app_id", r6);
        r1.put("event_id", r7);
        r1.put("children_to_process", Long.valueOf(r8));
        r1.put("main_event", r102);
    L7:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing complex main event. appId", zzgu.zzl(r6), e);
        return false;
    L4:
        if (zze().insertWithOnConflict("main_event_params", null, r1, 5) != (-1)) goto L9;
        r0.zzaW().zzb().zzb("Failed to insert complex main event (got -1). appId", zzgu.zzl(r6));     // Catch: SQLiteException -> L7
        return false;
    L9:
        return true;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x006a: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:19:0x006a */
    public final Bundle zzV(String r6) {
        zzg();
        zzay();
        Cursor r0 = null;
        Cursor r1 = zze().rawQuery("select parameters from default_event_params where app_id=?", new String[]{r6});     // Catch: Throwable -> L20 SQLiteException -> L22
    L9:
        e = e;
    L24:
        this.zzu.zzaW().zzb().zzb("Error selecting default event parameters", e);     // Catch: Throwable -> L7
    L25:
        if (r1 == null) goto L27;
        r1.close();
    L27:
        return null;
    L5:
        if (r1.moveToFirst() == true) goto L31;
        this.zzu.zzaW().zzk().zza("Default event parameters not found");     // Catch: Throwable -> L7 SQLiteException -> L9
        goto L25
    L31:
        com.google.android.gms.internal.measurement.zzhs r2 = (com.google.android.gms.internal.measurement.zzhs) ((com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzp(), r1.getBlob(0))).zzbd();     // Catch: Throwable -> L7 SQLiteException -> L9 IOException -> L16
        this.zzg.zzp();     // Catch: Throwable -> L7 SQLiteException -> L9
        Bundle r5 = zzpk.zzH(r2.zza());     // Catch: Throwable -> L7 SQLiteException -> L9
        r1.close();
        return r5;
    L16:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Failed to retrieve default event parameters. appId", zzgu.zzl(r6), e);     // Catch: Throwable -> L7 SQLiteException -> L9
        goto L25
    L7:
        th = th;
        r0 = r1;
    L28:
        if (r0 == null) goto L30;
        r0.close();
    L30:
        throw th;
    L22:
        e = e;
        r1 = null;
    L20:
        th = th;
        goto L28
    }

    public final boolean zzW(String r6, long r7) {
    L12:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Error checking backfill conditions", e);
        return false;
    L4:
        if (zzaB("select count(*) from raw_events where app_id=? and timestamp >= ? and name not like '!_%' escape '!' limit 1;", new String[]{r6, String.valueOf(r7)}, 0) <= 0) goto L6;
        return false;
    L6:
        if (zzaB("select count(*) from raw_events where app_id=? and timestamp >= ? and name like '!_%' escape '!' limit 1;", new String[]{r6, String.valueOf(r7)}, 0) <= 0) goto L11;
        return true;
    L11:
        return false;
    }

    public final void zzX(String r26, Long r27, String r28, Bundle r29) {
        String r5 = r26;
        Preconditions.checkNotNull(r29);
        zzg();
        zzay();
        if (r27 == null) goto L6;
        zzau r0 = new zzau(this, r5, r27.longValue());
    L5:
        zzau r14 = r0;
        List r02 = r14.zza();
    L9:
        if (r02.isEmpty() == true) goto L101;
        Iterator r15 = r02.iterator();
    L12:
        if (r15.hasNext() == false) goto L100;
        zzat r2 = (zzat) r15.next();
        if (TextUtils.isEmpty(r28) == true) goto L60;
        Cursor r6 = null;
        com.google.android.gms.internal.measurement.zzid r62 = null;
        Cursor r63 = null;
        Cursor r3 = zze().query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{r5, Long.toString(r2.zzb)}, null, null, "rowid", "2");     // Catch: Throwable -> L42 SQLiteException -> L44
        if (r3.moveToFirst() == true) goto L106;
        this.zzu.zzaW().zzb().zzb("Raw event metadata record is missing. appId", zzgu.zzl(r5));     // Catch: Throwable -> L21 SQLiteException -> L23
    L20:
        r3.close();
    L49:
        if (r62 == null) goto L60;
        Iterator r03 = r62.zzf().iterator();
    L52:
        if (r03.hasNext() == false) goto L60;
        if (((com.google.android.gms.internal.measurement.zziu) r03.next()).zzc().equals(r28) == false) goto L52;
    L106:
        com.google.android.gms.internal.measurement.zzid r4 = (com.google.android.gms.internal.measurement.zzid) ((com.google.android.gms.internal.measurement.zzic) zzpk.zzw(com.google.android.gms.internal.measurement.zzid.zzaE(), r3.getBlob(0))).zzbd();     // Catch: Throwable -> L21 SQLiteException -> L23 IOException -> L37
        if (r3.moveToNext() == false) goto L33;
        this.zzu.zzaW().zze().zzb("Get multiple raw event metadata records, expected one. appId", zzgu.zzl(r5));     // Catch: Throwable -> L21 SQLiteException -> L31
    L33:
        r3.close();     // Catch: Throwable -> L21 SQLiteException -> L31
        r3.close();
    L35:
        r62 = r4;
    L31:
        e = e;
    L36:
        r63 = r3;
    L46:
        this.zzu.zzaW().zzb().zzc("Data loss. Error selecting raw event. appId", zzgu.zzl(r5), e);     // Catch: Throwable -> L42
        if (r63 == null) goto L35;
        r63.close();
    L37:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Data loss. Failed to merge raw event metadata. appId", zzgu.zzl(r5), e);     // Catch: Throwable -> L21 SQLiteException -> L23
        goto L20
    L21:
        th = th;
        r6 = r3;
    L57:
        if (r6 == null) goto L59;
        r6.close();
    L59:
        throw th;
    L23:
        e = e;
        r4 = null;
        goto L36
    L44:
        e = e;
        r4 = null;
    L42:
        th = th;
    L60:
        zzpg r04 = this.zzg;
        zzpk r32 = r04.zzp();
        com.google.android.gms.internal.measurement.zzhs r64 = r2.zzd;
        Bundle r7 = new Bundle();
        Iterator r8 = r64.zza().iterator();
    L62:
        if (r8.hasNext() == false) goto L79;
        com.google.android.gms.internal.measurement.zzhw r9 = (com.google.android.gms.internal.measurement.zzhw) r8.next();
        if (r9.zzj() == true) goto L65;
        if (r9.zzh() == true) goto L68;
        if (r9.zze() == true) goto L71;
        if (r9.zzc() == true) goto L74;
        if (r9.zzl().isEmpty() == false) goto L77;
        r32.zzu.zzaW().zzb().zzb("Unexpected parameter type for parameter", r9);
        goto L62
    L77:
        r7.putParcelableArray(r9.zzb(), zzpk.zzy(r9.zzl()));
        goto L62
    L74:
        r7.putString(r9.zzb(), r9.zzd());
        goto L62
    L71:
        r7.putLong(r9.zzb(), r9.zzf());
        goto L62
    L68:
        r7.putFloat(r9.zzb(), r9.zzi());
        goto L62
    L65:
        r7.putDouble(r9.zzb(), r9.zzk());
        goto L62
    L79:
        String r82 = r7.getString("_o");
        r7.remove("_o");
        String r17 = r64.zzd();
        if (r82 != null) goto L82;
        r82 = _UrlKt.FRAGMENT_ENCODE_SET;
    L82:
        zzgv r16 = new zzgv(r17, r82, r7, r64.zzf(), r64.zzo());
        zzic r72 = this.zzu;
        Bundle r13 = r16.zze;
        String r83 = r16.zza;
        zzpp r92 = r72.zzk();
        if (r83.equals("_cmp") == true) goto L86;
        Bundle r84 = r29;
    L85:
        zzpg r162 = r04;
        r92.zzK(r13, r84);
        zzau r272 = r14;
        zzbc r22 = new zzbc(this.zzu, r16.zzb, r5, r64.zzd(), r64.zzf(), r64.zzo(), r64.zzi(), r13);
        long r33 = r2.zza;
        long r52 = r2.zzb;
        boolean r73 = r2.zzc;
        zzg();
        zzay();
        Preconditions.checkNotNull(r22);
        String r85 = r22.zza;
        Preconditions.checkNotEmpty(r85);
        byte[] r93 = r162.zzp().zzh(r22).zzcd();
        ContentValues r10 = new ContentValues();
        r10.put("app_id", r85);
        r10.put("name", r22.zzb);
        r10.put("timestamp", Long.valueOf(r22.zzd));
        r10.put("metadata_fingerprint", Long.valueOf(r52));
        r10.put("data", r93);
        r10.put("realtime", Integer.valueOf(r73 ? 1 : 0));
        r10.put("elapsed_time", Long.valueOf(r22.zze));
        long r34 = zze().update("raw_events", r10, "rowid = ?", new String[]{String.valueOf(r33)});     // Catch: SQLiteException -> L98
        if (r34 == 1) goto L97;
        r72.zzaW().zzb().zzc("Failed to update raw event. appId, updatedRows", zzgu.zzl(r85), Long.valueOf(r34));     // Catch: SQLiteException -> L98
    L97:
        r5 = r26;
        r14 = r272;
    L98:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error updating raw event. appId", zzgu.zzl(r22.zza), e);
        goto L97
    L86:
        r84 = new Bundle(r29);
        Iterator<String> r11 = r29.keySet().iterator();
    L88:
        if (r11.hasNext() == false) goto L85;
        String r12 = r11.next();
        zzpg r163 = r04;
        if (r12.startsWith("gad_") == false) goto L92;
        r84.remove(r12);
    L92:
        r04 = r163;
        goto L88
    L100:
        r02 = r14.zza();
        r5 = r26;
        goto L9
    L101:
        return;
    L6:
        r0 = new zzau(this, r5);
        goto L5
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v7, types: [android.database.Cursor] */
    public final zzjl zzY(String r4) {
        Preconditions.checkNotNull(r4);
        zzg();
        zzay();
        ?? r42 = {r4};
        Cursor r1 = null;
        zzjl r12 = null;
        r12 = null;
        r12 = null;
        r42 = zze().rawQuery("select consent_state, consent_source from consent_settings where app_id=? limit 1;", r42);     // Catch: Throwable -> L16 SQLiteException -> L18
    L10:
        SQLiteException e = e;
    L20:
        this.zzu.zzaW().zzb().zzb("Error querying database.", e);     // Catch: Throwable -> L8
        if (r42 != 0) goto L7;
    L23:
        if (r12 == null) goto L25;
        return r12;
    L25:
        return zzjl.zza;
    L7:
        r42.close();
        goto L23
    L5:
        if (r42.moveToFirst() == true) goto L13;
        this.zzu.zzaW().zzk().zza("No data found");     // Catch: Throwable -> L8 SQLiteException -> L10
    L32:
        goto L7
    L13:
        r12 = zzjl.zzf(r42.getString(0), r42.getInt(1));     // Catch: Throwable -> L8 SQLiteException -> L10
        goto L32
    L8:
        th = th;
        r1 = r42;
    L27:
        if (r1 == null) goto L29;
        r1.close();
    L29:
        throw th;
    L18:
        e = move-exception;
        e = e;
        r42 = 0;
    L16:
        th = th;
        goto L27
    }

    public final boolean zzZ(String r10, zzoh r11) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r11);
        Preconditions.checkNotEmpty(r10);
        zzic r0 = this.zzu;
        long r1 = r0.zzba().currentTimeMillis();
        zzfx r3 = zzfy.zzau;
        long r5 = r1 - ((Long) r3.zzb(null)).longValue();
        long r7 = r11.zzb;
        if (r7 >= r5) goto L5;
    L6:
        r0.zzaW().zze().zzd("Storing trigger URI outside of the max retention time span. appId, now, timestamp", zzgu.zzl(r10), Long.valueOf(r1), Long.valueOf(r7));
    L7:
        r0.zzaW().zzk().zza("Saving trigger URI");
        ContentValues r12 = new ContentValues();
        r12.put("app_id", r10);
        r12.put("trigger_uri", r11.zza);
        r12.put("source", Integer.valueOf(r11.zzc));
        r12.put("timestamp_millis", Long.valueOf(r7));
    L12:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing trigger URI. appId", zzgu.zzl(r10), e);
        return false;
    L9:
        if (zze().insert("trigger_uris", null, r12) != (-1)) goto L14;
        r0.zzaW().zzb().zzb("Failed to insert trigger URI (got -1). appId", zzgu.zzl(r10));     // Catch: SQLiteException -> L12
        return false;
    L14:
        return true;
    L5:
        if (r7 <= (((Long) r3.zzb(null)).longValue() + r1)) goto L7;
        goto L6
    }

    public final void zzaa(String r4, zzjl r5) {
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r5);
        zzg();
        zzay();
        ContentValues r0 = new ContentValues();
        r0.put("app_id", r4);
        r0.put("consent_state", r5.zzl());
        r0.put("consent_source", Integer.valueOf(r5.zzb()));
        zzaD("consent_settings", "app_id", r0);
    }

    public final zzba zzab(String r3) {
        Preconditions.checkNotNull(r3);
        zzg();
        zzay();
        return zzba.zzg(zzaC("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{r3}, _UrlKt.FRAGMENT_ENCODE_SET));
    }

    public final List zzac(String r12) {
        zzg();
        zzay();
        List r0 = new ArrayList();
        SQLiteDatabase r1 = zze();     // Catch: SQLiteException -> L36
        r1.beginTransaction();
        Cursor r10 = null;
        r10 = r1.query("diagnostic_signals", new String[]{"signal_name", "metadata", NotificationBadge.NewHtcHomeBadger.COUNT}, "app_id=?", new String[]{r12}, null, null, "rowid", null);     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r10.moveToFirst() == true) goto L12;
        r1.setTransactionSuccessful();     // Catch: Throwable -> L8 SQLiteException -> L10
    L28:
        if (r10 == null) goto L30;
        r10.close();
    L30:
        r1.endTransaction();
        return r0;
    L12:
        boolean r2 = r12.isEmpty();     // Catch: Throwable -> L8 SQLiteException -> L10
    L13:
        String r3 = r10.getString(0);     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r10.isNull(1) == false) goto L16;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        if (r3 != null) goto L19;
        this.zzu.zzaW().zzb().zzb("Read null value from diagnostic signals table, ignoring it. appId", zzgu.zzl(r12));     // Catch: Throwable -> L8 SQLiteException -> L10
    L24:
        if (r10.moveToNext() == true) goto L13;
        r1.delete("diagnostic_signals", "app_id=?", new String[]{r12});     // Catch: Throwable -> L8 SQLiteException -> L10
        r1.setTransactionSuccessful();     // Catch: Throwable -> L8 SQLiteException -> L10
        goto L28
    L19:
        long r6 = r10.getLong(2);     // Catch: Throwable -> L8 SQLiteException -> L10
        com.google.android.gms.internal.measurement.zzfa r8 = com.google.android.gms.internal.measurement.zzfb.zza();     // Catch: Throwable -> L8 SQLiteException -> L10
        r8.zza(r3);     // Catch: Throwable -> L8 SQLiteException -> L10
        r8.zzd(r6);     // Catch: Throwable -> L8 SQLiteException -> L10
        r8.zzc(r5);     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r2 == false) goto L22;
        r8.zzb(true);     // Catch: Throwable -> L8 SQLiteException -> L10
    L22:
        r0.add((com.google.android.gms.internal.measurement.zzfb) r8.zzbd());     // Catch: Throwable -> L8 SQLiteException -> L10
        goto L24
    L16:
        r5 = (String) Preconditions.checkNotNull(r10.getString(1));     // Catch: Throwable -> L8 SQLiteException -> L10
    L8:
        th = move-exception;
        if (0 == 0) goto L34;
        r10.close();
    L34:
        r1.endTransaction();
        throw th;
    L10:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error querying or deleting diagnostic signals. appId", zzgu.zzl(r12), e);     // Catch: Throwable -> L8
        r0 = Collections.EMPTY_LIST;     // Catch: Throwable -> L8
    L36:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error opening database for diagnostic signals. appId", zzgu.zzl(r12), e);
        return Collections.EMPTY_LIST;
    }

    public final void zzad(String r3, zzba r4) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r4);
        zzg();
        zzay();
        zzjl r0 = zzY(r3);
        zzjl r1 = zzjl.zza;
        if (r0 != r1) goto L5;
        zzaa(r3, r1);
    L5:
        ContentValues r02 = new ContentValues();
        r02.put("app_id", r3);
        r02.put("dma_consent_settings", r4.zze());
        zzaD("consent_settings", "app_id", r02);
    }

    public final void zzae(String r3, zzjl r4) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r4);
        zzg();
        zzay();
        zzaa(r3, zzY(r3));
        ContentValues r0 = new ContentValues();
        r0.put("app_id", r3);
        r0.put("storage_consent_at_bundling", r4.zzl());
        zzaD("consent_settings", "app_id", r0);
    }

    public final zzjl zzaf(String r3) {
        Preconditions.checkNotNull(r3);
        zzg();
        zzay();
        return zzjl.zzf(zzaC("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{r3}, _UrlKt.FRAGMENT_ENCODE_SET), 100);
    }

    public final void zzag(String r23, List r24) {
        Preconditions.checkNotNull(r24);
        int r8 = 0;
    L4:
        if (r8 >= r24.size()) goto L36;
        com.google.android.gms.internal.measurement.zzfc r9 = (com.google.android.gms.internal.measurement.zzfc) ((com.google.android.gms.internal.measurement.zzfd) r24.get(r8)).zzco();
        if (r9.zzd() == 0) goto L27;
        int r10 = 0;
    L9:
        if (r10 >= r9.zzd()) goto L27;
        com.google.android.gms.internal.measurement.zzfe r11 = (com.google.android.gms.internal.measurement.zzfe) r9.zze(r10).zzco();
        com.google.android.gms.internal.measurement.zzfe r12 = (com.google.android.gms.internal.measurement.zzfe) r11.zzbb();
        String r13 = zzjm.zzb(r11.zza());
        if (r13 == null) goto L13;
        r12.zzb(r13);
        boolean r132 = true;
    L14:
        int r15 = 0;
    L16:
        if (r15 >= r11.zzc()) goto L23;
        com.google.android.gms.internal.measurement.zzfh r14 = r11.zzd(r15);
        com.google.android.gms.internal.measurement.zzfe r17 = r11;
        boolean r18 = r132;
        String r7 = zzlt.zzc(r14.zzi(), zzjn.zza, zzjn.zzb);
        if (r7 == null) goto L20;
        com.google.android.gms.internal.measurement.zzfg r112 = (com.google.android.gms.internal.measurement.zzfg) r14.zzco();
        r112.zza(r7);
        r12.zze(r15, (com.google.android.gms.internal.measurement.zzfh) r112.zzbd());
        r132 = true;
    L21:
        r15 = r15 + 1;
        r11 = r17;
        goto L16
    L20:
        r132 = r18;
        goto L21
    L23:
        if (r132 == false) goto L25;
        r9.zzf(r10, r12);
        r24.set(r8, (com.google.android.gms.internal.measurement.zzfd) r9.zzbd());
    L25:
        r10 = r10 + 1;
        goto L9
    L13:
        r132 = false;
    L27:
        if (r9.zza() == 0) goto L35;
        int r72 = 0;
    L30:
        if (r72 >= r9.zza()) goto L35;
        com.google.android.gms.internal.measurement.zzfn r102 = r9.zzb(r72);
        String r113 = zzlt.zzc(r102.zzc(), zzjo.zza, zzjo.zzb);
        if (r113 == null) goto L34;
        com.google.android.gms.internal.measurement.zzfm r103 = (com.google.android.gms.internal.measurement.zzfm) r102.zzco();
        r103.zza(r113);
        r9.zzc(r72, r103);
        r24.set(r8, (com.google.android.gms.internal.measurement.zzfd) r9.zzbd());
    L34:
        r72 = r72 + 1;
    L35:
        r8 = r8 + 1;
        goto L4
    L36:
        zzay();
        zzg();
        Preconditions.checkNotEmpty(r23);
        Preconditions.checkNotNull(r24);
        SQLiteDatabase r73 = zze();
        r73.beginTransaction();
        zzay();     // Catch: Throwable -> L43
        zzg();     // Catch: Throwable -> L43
        Preconditions.checkNotEmpty(r23);     // Catch: Throwable -> L43
        SQLiteDatabase r82 = zze();     // Catch: Throwable -> L43
        r82.delete("property_filters", "app_id=?", new String[]{r23});     // Catch: Throwable -> L43
        r82.delete("event_filters", "app_id=?", new String[]{r23});     // Catch: Throwable -> L43
        Iterator r83 = r24.iterator();     // Catch: Throwable -> L43
    L38:
        if (r83.hasNext() == false) goto L118;
        com.google.android.gms.internal.measurement.zzfd r0 = (com.google.android.gms.internal.measurement.zzfd) r83.next();     // Catch: Throwable -> L43
        zzay();     // Catch: Throwable -> L43
        zzg();     // Catch: Throwable -> L43
        Preconditions.checkNotEmpty(r23);     // Catch: Throwable -> L43
        Preconditions.checkNotNull(r0);     // Catch: Throwable -> L43
        if (r0.zza() == false) goto L42;
        int r104 = r0.zzb();     // Catch: Throwable -> L43
        Iterator r114 = r0.zzf().iterator();     // Catch: Throwable -> L43
    L47:
        if (r114.hasNext() == false) goto L51;
        if (((com.google.android.gms.internal.measurement.zzff) r114.next()).zza() == true) goto L47;
        this.zzu.zzaW().zze().zzc("Event filter with no ID. Audience definition ignored. appId, audienceId", zzgu.zzl(r23), Integer.valueOf(r104));     // Catch: Throwable -> L43
        goto L38
    L51:
        Iterator r115 = r0.zzc().iterator();     // Catch: Throwable -> L43
    L53:
        if (r115.hasNext() == false) goto L57;
        if (((com.google.android.gms.internal.measurement.zzfn) r115.next()).zza() == true) goto L53;
        this.zzu.zzaW().zze().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", zzgu.zzl(r23), Integer.valueOf(r104));     // Catch: Throwable -> L43
        goto L38
    L57:
        Iterator r116 = r0.zzf().iterator();     // Catch: Throwable -> L43
    L59:
        com.google.android.gms.internal.measurement.zzfd r19 = r0;
        String r02 = "app_id";
        if (r116.hasNext() == false) goto L89;
        com.google.android.gms.internal.measurement.zzff r122 = (com.google.android.gms.internal.measurement.zzff) r116.next();     // Catch: Throwable -> L43
        zzay();     // Catch: Throwable -> L43
        zzg();     // Catch: Throwable -> L43
        Preconditions.checkNotEmpty(r23);     // Catch: Throwable -> L43
        Preconditions.checkNotNull(r122);     // Catch: Throwable -> L43
        if (r122.zzc().isEmpty() == true) goto L63;
        byte[] r3 = r122.zzcd();     // Catch: Throwable -> L43
        SQLiteDatabase r20 = r73;
        ContentValues r74 = new ContentValues();     // Catch: Throwable -> L73
        r74.put("app_id", r23);     // Catch: Throwable -> L73
        r74.put("audience_id", Integer.valueOf(r104));     // Catch: Throwable -> L73
        if (r122.zza() == false) goto L75;
        Integer r03 = Integer.valueOf(r122.zzb());     // Catch: Throwable -> L73
    L76:
        r74.put("filter_id", r03);     // Catch: Throwable -> L73
        r74.put("event_name", r122.zzc());     // Catch: Throwable -> L73
        if (r122.zzl() == false) goto L79;
        Boolean r04 = Boolean.valueOf(r122.zzm());     // Catch: Throwable -> L73
    L80:
        r74.put("session_scoped", r04);     // Catch: Throwable -> L73
        r74.put("data", r3);     // Catch: Throwable -> L73
        if (zze().insertWithOnConflict("event_filters", null, r74, 5) != (-1)) goto L87;
        this.zzu.zzaW().zzb().zzb("Failed to insert event filter (got -1). appId", zzgu.zzl(r23));     // Catch: Throwable -> L73 SQLiteException -> L85
    L87:
        r0 = r19;
        r73 = r20;
        goto L59
    L85:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing event filter. appId", zzgu.zzl(r23), e);     // Catch: Throwable -> L73
    L116:
        zzay();     // Catch: Throwable -> L73
        zzg();     // Catch: Throwable -> L73
        Preconditions.checkNotEmpty(r23);     // Catch: Throwable -> L73
        SQLiteDatabase r05 = zze();     // Catch: Throwable -> L73
        r05.delete("property_filters", "app_id=? and audience_id=?", new String[]{r23, String.valueOf(r104)});     // Catch: Throwable -> L73
        r05.delete("event_filters", "app_id=? and audience_id=?", new String[]{r23, String.valueOf(r104)});     // Catch: Throwable -> L73
    L117:
        r73 = r20;
        goto L38
    L79:
        r04 = null;
        goto L80
    L75:
        r03 = null;
    L73:
        th = th;
    L143:
        r20.endTransaction();
        throw th;
    L63:
        zzgs r06 = this.zzu.zzaW().zze();     // Catch: Throwable -> L43
        Object r117 = zzgu.zzl(r23);     // Catch: Throwable -> L43
        Integer r133 = Integer.valueOf(r104);     // Catch: Throwable -> L43
        if (r122.zza() == false) goto L66;
        Integer r16 = Integer.valueOf(r122.zzb());     // Catch: Throwable -> L43
    L67:
        r06.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r117, r133, String.valueOf(r16));     // Catch: Throwable -> L43
        r20 = r73;
        goto L116
    L66:
        r16 = null;
        goto L67
    L89:
        r20 = r73;
        Iterator r32 = r19.zzc().iterator();     // Catch: Throwable -> L73
    L91:
        if (r32.hasNext() == false) goto L117;
        com.google.android.gms.internal.measurement.zzfn r75 = (com.google.android.gms.internal.measurement.zzfn) r32.next();     // Catch: Throwable -> L73
        zzay();     // Catch: Throwable -> L73
        zzg();     // Catch: Throwable -> L73
        Preconditions.checkNotEmpty(r23);     // Catch: Throwable -> L73
        Preconditions.checkNotNull(r75);     // Catch: Throwable -> L73
        if (r75.zzc().isEmpty() == true) goto L94;
        byte[] r118 = r75.zzcd();     // Catch: Throwable -> L73
        ContentValues r123 = new ContentValues();     // Catch: Throwable -> L73
        r123.put(r02, r23);     // Catch: Throwable -> L73
        String r192 = r02;
        r123.put("audience_id", Integer.valueOf(r104));     // Catch: Throwable -> L73
        if (r75.zza() == false) goto L102;
        Integer r07 = Integer.valueOf(r75.zzb());     // Catch: Throwable -> L73
    L103:
        r123.put("filter_id", r07);     // Catch: Throwable -> L73
        Iterator r21 = r32;
        r123.put("property_name", r75.zzc());     // Catch: Throwable -> L73
        if (r75.zzh() == false) goto L106;
        Boolean r08 = Boolean.valueOf(r75.zzi());     // Catch: Throwable -> L73
    L107:
        r123.put("session_scoped", r08);     // Catch: Throwable -> L73
        r123.put("data", r118);     // Catch: Throwable -> L73
        if (zze().insertWithOnConflict("property_filters", null, r123, 5) == (-1)) goto L110;
        r02 = r192;
        r32 = r21;
        goto L91
    L110:
        this.zzu.zzaW().zzb().zzb("Failed to insert property filter (got -1). appId", zzgu.zzl(r23));     // Catch: Throwable -> L73 SQLiteException -> L112
        goto L116
    L112:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing property filter. appId", zzgu.zzl(r23), e);     // Catch: Throwable -> L73
        goto L116
    L106:
        r08 = null;
        goto L107
    L102:
        r07 = null;
        goto L103
    L94:
        zzgs r09 = this.zzu.zzaW().zze();     // Catch: Throwable -> L73
        Object r92 = zzgu.zzl(r23);     // Catch: Throwable -> L73
        Integer r119 = Integer.valueOf(r104);     // Catch: Throwable -> L73
        if (r75.zza() == false) goto L97;
        Integer r162 = Integer.valueOf(r75.zzb());     // Catch: Throwable -> L73
    L98:
        r09.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r92, r119, String.valueOf(r162));     // Catch: Throwable -> L73
        goto L116
    L97:
        r162 = null;
        goto L98
    L42:
        this.zzu.zzaW().zze().zzb("Audience with no ID. appId", zzgu.zzl(r23));     // Catch: Throwable -> L43
        goto L38
    L118:
        r20 = r73;
        ArrayList r010 = new ArrayList();     // Catch: Throwable -> L73
        Iterator r4 = r24.iterator();     // Catch: Throwable -> L73
    L120:
        if (r4.hasNext() == false) goto L126;
        com.google.android.gms.internal.measurement.zzfd r5 = (com.google.android.gms.internal.measurement.zzfd) r4.next();     // Catch: Throwable -> L73
        if (r5.zza() == false) goto L124;
        Integer r93 = Integer.valueOf(r5.zzb());     // Catch: Throwable -> L73
    L125:
        r010.add(r93);     // Catch: Throwable -> L73
        goto L120
    L124:
        r93 = null;
        goto L125
    L126:
        Preconditions.checkNotEmpty(r23);     // Catch: Throwable -> L73
        zzay();     // Catch: Throwable -> L73
        zzg();     // Catch: Throwable -> L73
        SQLiteDatabase r76 = zze();     // Catch: Throwable -> L73
        long r84 = zzaA("select count(1) from audience_filter_values where app_id=?", new String[]{r23});     // Catch: Throwable -> L73 SQLiteException -> L138
        int r105 = 0;
        int r1 = Math.max(0, Math.min(CastStatusCodes.AUTHENTICATION_FAILED, this.zzu.zzc().zzm(r23, zzfy.zzU)));     // Catch: Throwable -> L73
        if (r84 <= r1) goto L140;
        ArrayList r85 = new ArrayList();     // Catch: Throwable -> L73
    L133:
        if (r105 >= r010.size()) goto L137;
        Integer r94 = (Integer) r010.get(r105);     // Catch: Throwable -> L73
        if (r94 == null) goto L140;
        r85.add(Integer.toString(r94.intValue()));     // Catch: Throwable -> L73
        r105 = r105 + 1;     // Catch: Throwable -> L73
        goto L133
    L137:
        String r011 = TextUtils.join(",", r85);     // Catch: Throwable -> L73
        StringBuilder r95 = new StringBuilder(String.valueOf(r011).length() + 2);     // Catch: Throwable -> L73
        r95.append("(");     // Catch: Throwable -> L73
        r95.append(r011);     // Catch: Throwable -> L73
        r95.append(")");     // Catch: Throwable -> L73
        String r012 = r95.toString();     // Catch: Throwable -> L73
        StringBuilder r86 = new StringBuilder(r012.length() + 140);     // Catch: Throwable -> L73
        r86.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");     // Catch: Throwable -> L73
        r86.append(r012);     // Catch: Throwable -> L73
        r86.append(" order by rowid desc limit -1 offset ?)");     // Catch: Throwable -> L73
        r76.delete("audience_filter_values", r86.toString(), new String[]{r23, Integer.toString(r1)});     // Catch: Throwable -> L73
    L140:
        r20.setTransactionSuccessful();     // Catch: Throwable -> L73
        r20.endTransaction();
        return;
    L138:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Database error querying filters. appId", zzgu.zzl(r23), e);     // Catch: Throwable -> L73
    L43:
        th = th;
        r20 = r73;
        goto L143
    }

    public final zzbd zzah(String r24, com.google.android.gms.internal.measurement.zzhs r25, String r26) {
        zzbd r1 = zzaE("events", r24, r25.zzd());
        if (r1 != null) goto L6;
        zzic r0 = this.zzu;
        r0.zzaW().zze().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzgu.zzl(r24), r0.zzl().zza(r26));
        return new zzbd(r24, r25.zzd(), 1, 1, 1, r25.zzf(), 0, null, null, null, null);
    L6:
        long r13 = r1.zze + 1;
        long r11 = r1.zzd + 1;
        long r9 = r1.zzc + 1;
        return new zzbd(r1.zza, r1.zzb, r9, r11, r13, r1.zzf, r1.zzg, r1.zzh, r1.zzi, r1.zzj, r1.zzk);
    }

    public final boolean zzai() {
        zzic r1 = this.zzu;
        Context r0 = r1.zzaZ();
        r1.zzc();
        return r0.getDatabasePath("google_app_measurement.db").exists();
    }

    public final /* synthetic */ long zzaj(String r1, String[] r2, long r3) {
        return zzaB("select rowid from raw_events where app_id = ? and timestamp < ? order by rowid desc limit 1", r2, -1);
    }

    public final /* synthetic */ zzog zzau() {
        return this.zzn;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v5 */
    public final void zzav(String r20, long r21, long r23, zzpc r25) {
        Preconditions.checkNotNull(r25);
        zzg();
        zzay();
        Cursor r6 = null;
        SQLiteDatabase r7 = zze();     // Catch: SQLiteException -> L9 Throwable -> L21
        ?? r8 = TextUtils.isEmpty(r20);     // Catch: SQLiteException -> L9 Throwable -> L21
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r8 == 0) goto L93;
        if (r23 == (-1)) goto L11;
        String[] r3 = {String.valueOf(r23), String.valueOf(r21)};     // Catch: SQLiteException -> L9 Throwable -> L21
    L12:
        if (r23 == (-1)) goto L14;
        r10 = "rowid <= ? and ";
    L14:
        StringBuilder r82 = new StringBuilder(r10.length() + 148);     // Catch: SQLiteException -> L9 Throwable -> L21
        r82.append("select app_id, metadata_fingerprint from raw_events where ");     // Catch: SQLiteException -> L9 Throwable -> L21
        r82.append(r10);     // Catch: SQLiteException -> L9 Throwable -> L21
        r82.append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;");     // Catch: SQLiteException -> L9 Throwable -> L21
        r6 = r7.rawQuery(r82.toString(), r3);     // Catch: SQLiteException -> L9 Throwable -> L21
    L25:
        e = e;
        String r32 = r20;
    L82:
        this.zzu.zzaW().zzb().zzc("Data loss. Error selecting raw event. appId", zzgu.zzl(r32), e);     // Catch: Throwable -> L21
    L83:
        if (r6 == null) goto L103;
        r6.close();
        return;
    L103:
        return;
    L16:
        if (r6.moveToFirst() == false) goto L83;
        r32 = r6.getString(0);     // Catch: Throwable -> L21 SQLiteException -> L25
        String r0 = r6.getString(1);     // Catch: Throwable -> L21 SQLiteException -> L23
        r6.close();     // Catch: Throwable -> L21 SQLiteException -> L23
    L40:
        r6 = r7.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{r32, r0}, null, null, "rowid", "2");     // Catch: Throwable -> L21 SQLiteException -> L23
        if (r6.moveToFirst() == true) goto L89;
        this.zzu.zzaW().zzb().zzb("Raw event metadata record is missing. appId", zzgu.zzl(r32));     // Catch: Throwable -> L21 SQLiteException -> L23
        goto L83
    L89:
        com.google.android.gms.internal.measurement.zzid r83 = (com.google.android.gms.internal.measurement.zzid) ((com.google.android.gms.internal.measurement.zzic) zzpk.zzw(com.google.android.gms.internal.measurement.zzid.zzaE(), r6.getBlob(0))).zzbd();     // Catch: Throwable -> L21 SQLiteException -> L23 IOException -> L77
        if (r6.moveToNext() == false) goto L48;
        this.zzu.zzaW().zze().zzb("Get multiple raw event metadata records, expected one. appId", zzgu.zzl(r32));     // Catch: Throwable -> L21 SQLiteException -> L23
    L48:
        r6.close();     // Catch: Throwable -> L21 SQLiteException -> L23
        Preconditions.checkNotNull(r83);     // Catch: Throwable -> L21 SQLiteException -> L23
        r25.zza = r83;     // Catch: Throwable -> L21 SQLiteException -> L23
        long r84 = zzaB("select (rowid - 1) as max_rowid from raw_events where app_id = ? and metadata_fingerprint != ? order by rowid limit 1;", new String[]{r32, r0}, -1);     // Catch: Throwable -> L21 SQLiteException -> L23
        if (r23 == (-1)) goto L51;
        long r12 = r23;
    L57:
        if (r12 != (-1)) goto L59;
    L61:
        if (r12 == (-1)) goto L63;
        r84 = r12;
    L63:
        String r102 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
        String[] r02 = {r32, r0, String.valueOf(r84)};     // Catch: Throwable -> L21 SQLiteException -> L23
    L64:
        r6 = r7.query("raw_events", new String[]{"rowid", "name", "timestamp", "data", "elapsed_time"}, r102, r02, null, null, "rowid", null);     // Catch: Throwable -> L21 SQLiteException -> L23
        if (r6.moveToFirst() == false) goto L76;
    L66:
        long r72 = r6.getLong(0);     // Catch: Throwable -> L21 SQLiteException -> L23
        byte[] r03 = r6.getBlob(3);     // Catch: Throwable -> L21 SQLiteException -> L23
        long r9 = r6.getLong(4);     // Catch: Throwable -> L21 SQLiteException -> L23
        com.google.android.gms.internal.measurement.zzhr r04 = (com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzp(), r03);     // Catch: Throwable -> L21 SQLiteException -> L23 IOException -> L71
        r04.zzl(r6.getString(1));     // Catch: Throwable -> L21 SQLiteException -> L23
        r04.zzo(r6.getLong(2));     // Catch: Throwable -> L21 SQLiteException -> L23
        r04.zzw(r9);     // Catch: Throwable -> L21 SQLiteException -> L23
        if (r25.zza(r72, (com.google.android.gms.internal.measurement.zzhs) r04.zzbd()) == false) goto L83;
    L74:
        if (r6.moveToNext() == true) goto L66;
    L71:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Data loss. Failed to merge raw event. appId", zzgu.zzl(r32), e);     // Catch: Throwable -> L21 SQLiteException -> L23
        goto L74
    L76:
        this.zzu.zzaW().zze().zzb("Raw event data disappeared while in transaction. appId", zzgu.zzl(r32));     // Catch: Throwable -> L21 SQLiteException -> L23
        goto L83
    L59:
        if (r84 == (-1)) goto L61;
        r84 = Math.min(r12, r84);     // Catch: Throwable -> L21 SQLiteException -> L23
        goto L63
    L51:
        if (r84 == (-1)) goto L53;
        r12 = -1;
        goto L57
    L53:
        r02 = new String[]{r32, r0};     // Catch: Throwable -> L21 SQLiteException -> L23
        r102 = "app_id = ? and metadata_fingerprint = ?";
    L77:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Data loss. Failed to merge raw event metadata. appId", zzgu.zzl(r32), e);     // Catch: Throwable -> L21 SQLiteException -> L23
    L23:
        e = e;
        goto L82
    L11:
        r3 = new String[]{String.valueOf(r21)};     // Catch: SQLiteException -> L9 Throwable -> L21
        goto L12
    L93:
        if (r23 != (-1)) goto L30;
        String r85 = r20;
        String[] r5 = {r85};     // Catch: Throwable -> L21 SQLiteException -> L80
        r8 = r85;
    L33:
        if (r23 == (-1)) goto L35;
        r10 = " and rowid <= ?";
    L35:
        StringBuilder r14 = new StringBuilder(r10.length() + 84);     // Catch: Throwable -> L21 SQLiteException -> L80
        r14.append("select metadata_fingerprint from raw_events where app_id = ?");     // Catch: Throwable -> L21 SQLiteException -> L80
        r14.append(r10);     // Catch: Throwable -> L21 SQLiteException -> L80
        r14.append(" order by rowid limit 1;");     // Catch: Throwable -> L21 SQLiteException -> L80
        r6 = r7.rawQuery(r14.toString(), r5);     // Catch: Throwable -> L21 SQLiteException -> L80
        if (r6.moveToFirst() == false) goto L83;
        r0 = r6.getString(0);     // Catch: Throwable -> L21 SQLiteException -> L80
        r6.close();     // Catch: Throwable -> L21 SQLiteException -> L80
        r32 = r8;
        goto L40
    L30:
        String r86 = r20;
        r5 = new String[]{r86, String.valueOf(r23)};     // Catch: Throwable -> L21 SQLiteException -> L80
        r8 = r86;
    L80:
        e = e;
    L81:
        r32 = r8;
    L9:
        e = e;
        r8 = r20;
    L21:
        th = move-exception;
        if (0 == 0) goto L88;
        r6.close();
    L88:
        throw th;
    }

    public final void zzb() {
        zzay();
        zze().beginTransaction();
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    public final boolean zzbc() {
        zzic r0 = this.zzu;
        if (r0.zzc().zzp(null, zzfy.zzbe) == false) goto L7;
        r0.zzaX().zzj(new zzas(this));
        return false;
    L7:
        return false;
    }

    public final void zzc() {
        zzay();
        zze().setTransactionSuccessful();
    }

    public final void zzd() {
        zzay();
        zze().endTransaction();
    }

    public final SQLiteDatabase zze() {
        zzg();
        return this.zzm.getWritableDatabase();
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error opening database", e);
        throw e;
    }

    public final zzbd zzf(String r2, String r3) {
        return zzaE("events", r2, r3);
    }

    public final void zzh(zzbd r2) {
        zzaF("events", r2);
    }

    public final void zzi(String r13) {
        zzaG("events_snapshot", r13);
        List r1 = Collections.singletonList("name");
        Cursor r2 = null;
        r2 = zze().query("events", (String[]) r1.toArray(new String[0]), "app_id=?", new String[]{r13}, null, null, null);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r2.moveToFirst() == false) goto L18;
    L5:
        String r12 = r2.getString(0);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r12 == null) goto L15;
        zzbd r14 = zzaE("events", r13, r12);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r14 == null) goto L15;
        zzaF("events_snapshot", r14);     // Catch: Throwable -> L10 SQLiteException -> L12
    L15:
        if (r2.moveToNext() == true) goto L5;
    L18:
        if (r2 == null) goto L28;
        r2.close();
        return;
    L28:
        return;
    L10:
        th = move-exception;
        if (r2 == null) goto L29;
        r2.close();
        throw th;
    L29:
        throw th;
    L12:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error creating snapshot. appId", zzgu.zzl(r13), e);     // Catch: Throwable -> L10
        goto L18
    }

    public final void zzj(String r20) {
        ArrayList r0 = new ArrayList(Arrays.asList(new String[]{"name", "lifetime_count"}));
        zzbd r6 = zzaE("events", r20, "_f");
        zzbd r8 = zzaE("events", r20, "_v");
        zzaG("events", r20);
        Cursor r9 = null;
        boolean r10 = false;
        r9 = zze().query("events_snapshot", (String[]) r0.toArray(new String[0]), "app_id=?", new String[]{r20}, null, null, null);     // Catch: Throwable -> L32 SQLiteException -> L34
        if (r9.moveToFirst() == true) goto L10;
        r9.close();
        if (r6 == null) goto L8;
    L7:
        zzaF("events", r6);
    L46:
        zzaG("events_snapshot", r20);
        return;
    L8:
        if (r8 == null) goto L46;
    L9:
        zzaF("events", r8);
        goto L46
    L10:
        boolean r11 = false;
        boolean r12 = false;
    L64:
        String r02 = r9.getString(0);     // Catch: Throwable -> L23 SQLiteException -> L25
        if (r9.getLong(1) >= 1) goto L14;
    L19:
        if (r02 == null) goto L28;
        zzbd r03 = zzaE("events_snapshot", r20, r02);     // Catch: Throwable -> L23 SQLiteException -> L25
        if (r03 == null) goto L28;
        zzaF("events", r03);     // Catch: Throwable -> L23 SQLiteException -> L25
    L28:
        if (r9.moveToNext() == true) goto L64;
    L38:
        if (r9 == null) goto L40;
        r9.close();
    L40:
        if (r11 == true) goto L43;
        if (r6 != null) goto L7;
    L43:
        if (r12 == true) goto L46;
        if (r8 == null) goto L46;
    L14:
        if ("_f".equals(r02) == false) goto L17;
        r11 = true;
        goto L19
    L17:
        if ("_v".equals(r02) == false) goto L19;
        r12 = true;
        goto L19
    L25:
        e = e;
        r10 = r11;
    L60:
        this.zzu.zzaW().zzb().zzc("Error querying snapshot. appId", zzgu.zzl(r20), e);     // Catch: Throwable -> L48
        r11 = r10;
    L48:
        th = th;
    L49:
        if (r9 == null) goto L51;
        r9.close();
    L51:
        if (r10 == true) goto L55;
        if (r6 == null) goto L55;
        zzaF("events", r6);
    L58:
        zzaG("events_snapshot", r20);
        throw th;
    L55:
        if (r12 == true) goto L58;
        if (r8 == null) goto L58;
        zzaF("events", r8);
    L23:
        th = th;
        r10 = r11;
        goto L49
    L34:
        e = e;
        r12 = false;
    L32:
        th = th;
        r12 = false;
        goto L49
    }

    public final void zzk(String r5, String r6) {
        Preconditions.checkNotEmpty(r5);
        Preconditions.checkNotEmpty(r6);
        zzg();
        zzay();
        zze().delete("user_attributes", "app_id=? and name=?", new String[]{r5, r6});     // Catch: SQLiteException -> L5
        return;
    L5:
        e = move-exception;
        zzic r4 = this.zzu;
        r4.zzaW().zzb().zzd("Error deleting user property. appId", zzgu.zzl(r5), r4.zzl().zzc(r6), e);
    }

    public final boolean zzl(zzpn r10) {
        Preconditions.checkNotNull(r10);
        zzg();
        zzay();
        String r0 = r10.zza;
        String r1 = r10.zzc;
        if (zzm(r0, r1) == null) goto L5;
    L15:
        ContentValues r2 = new ContentValues();
        r2.put("app_id", r0);
        r2.put("origin", r10.zzb);
        r2.put("name", r1);
        r2.put("set_timestamp", Long.valueOf(r10.zzd));
        zzaw(r2, "value", r10.zze);
    L20:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing user property. appId", zzgu.zzl(r10.zza), e);
        return true;
    L17:
        if (zze().insertWithOnConflict("user_attributes", null, r2, 5) != (-1)) goto L26;
        this.zzu.zzaW().zzb().zzb("Failed to insert/update user property (got -1). appId", zzgu.zzl(r0));     // Catch: SQLiteException -> L20
        return true;
    L26:
        return true;
    L5:
        if (zzpp.zzh(r1) == false) goto L11;
        if (zzaA("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{r0}) < this.zzu.zzc().zzn(r0, zzfy.zzV, 25, 100)) goto L15;
        return false;
    L11:
        if ("_npa".equals(r1) == true) goto L15;
        long r4 = zzaA("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{r0, r10.zzb});
        this.zzu.zzc();
        if (r4 < 25) goto L15;
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0076: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:24:0x0076 */
    public final zzpn zzm(String r11, String r12) {
        Preconditions.checkNotEmpty(r11);
        Preconditions.checkNotEmpty(r12);
        zzg();
        zzay();
        Cursor r1 = null;
        Cursor r2 = zze().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{r11, r12}, null, null, null);     // Catch: Throwable -> L25 SQLiteException -> L27
    L22:
        e = e;
        String r4 = r11;
        String r6 = r12;
    L19:
        SQLiteException r112 = e;
    L29:
        zzic r10 = this.zzu;     // Catch: Throwable -> L16
        r10.zzaW().zzb().zzd("Error querying user property. appId", zzgu.zzl(r4), r10.zzl().zzc(r6), r112);     // Catch: Throwable -> L16
    L30:
        if (r2 == null) goto L32;
        r2.close();
    L32:
        return null;
    L5:
        if (r2.moveToFirst() == false) goto L30;
        long r7 = r2.getLong(0);     // Catch: Throwable -> L16 SQLiteException -> L22
        Object r9 = zzL(r2, 1);     // Catch: Throwable -> L16 SQLiteException -> L22
        if (r9 == null) goto L30;
        r4 = r11;
        r6 = r12;
        zzpn r3 = new zzpn(r4, r2.getString(2), r6, r7, r9);     // Catch: Throwable -> L16 SQLiteException -> L18
        if (r2.moveToNext() == false) goto L20;
        this.zzu.zzaW().zzb().zzb("Got multiple records for user property, expected one. appId", zzgu.zzl(r4));     // Catch: Throwable -> L16 SQLiteException -> L18
    L20:
        r2.close();
        return r3;
    L18:
        e = e;
        goto L19
    L16:
        th = move-exception;
        Throwable r102 = th;
        r1 = r2;
    L33:
        if (r1 == null) goto L42;
        r1.close();
        throw r102;
    L42:
        throw r102;
    L27:
        e = move-exception;
        r4 = r11;
        r6 = r12;
        r112 = e;
        r2 = null;
    L25:
        th = move-exception;
        r102 = th;
        goto L33
    }

    public final List zzn(String r13) {
        Preconditions.checkNotEmpty(r13);
        zzg();
        zzay();
        List r0 = new ArrayList();
        Cursor r10 = null;
        zzic r11 = this.zzu;     // Catch: Throwable -> L10 SQLiteException -> L26
        r11.zzc();     // Catch: Throwable -> L10 SQLiteException -> L26
        r10 = zze().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{r13}, null, null, "rowid", "1000");     // Catch: Throwable -> L10 SQLiteException -> L26
    L12:
        e = e;
        String r3 = r13;
    L28:
        this.zzu.zzaW().zzb().zzc("Error querying user properties. appId", zzgu.zzl(r3), e);     // Catch: Throwable -> L10
        r0 = Collections.EMPTY_LIST;     // Catch: Throwable -> L10
    L29:
        if (r10 == null) goto L31;
        r10.close();
    L31:
        return r0;
    L5:
        if (r10.moveToFirst() == false) goto L29;
    L6:
        String r5 = r10.getString(0);     // Catch: Throwable -> L10 SQLiteException -> L12
        String r1 = r10.getString(1);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r1 != null) goto L9;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        String r4 = r1;
        long r6 = r10.getLong(2);     // Catch: Throwable -> L10 SQLiteException -> L12
        Object r8 = zzL(r10, 3);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r8 != null) goto L18;
        r11.zzaW().zzb().zzb("Read invalid user property value, ignoring it. appId", zzgu.zzl(r13));     // Catch: Throwable -> L10 SQLiteException -> L12
        r3 = r13;
    L20:
        if (r10.moveToNext() == false) goto L29;
        r13 = r3;
    L24:
        e = e;
        goto L28
    L18:
        r3 = r13;
        r0.add(new zzpn(r3, r4, r5, r6, r8));     // Catch: Throwable -> L10 SQLiteException -> L24
    L26:
        e = e;
        r3 = r13;
    L10:
        th = move-exception;
        if (r10 == null) goto L43;
        r10.close();
        throw th;
    L43:
        throw th;
    }

    public final List zzo(String r23, String r24, String r25) {
        Preconditions.checkNotEmpty(r23);
        zzg();
        zzay();
        List r2 = new ArrayList();
        ArrayList r4 = new ArrayList(3);     // Catch: Throwable -> L7 SQLiteException -> L42
        String r15 = r23;
        r4.add(r15);     // Catch: Throwable -> L7 SQLiteException -> L42
        StringBuilder r5 = new StringBuilder("app_id=?");     // Catch: Throwable -> L7 SQLiteException -> L42
        if (TextUtils.isEmpty(r24) == true) goto L11;
        String r14 = r24;
        r4.add(r14);     // Catch: Throwable -> L7 SQLiteException -> L9
        r5.append(" and origin=?");     // Catch: Throwable -> L7 SQLiteException -> L9
    L13:
        if (TextUtils.isEmpty(r25) == true) goto L15;
        StringBuilder r8 = new StringBuilder(String.valueOf(r25).length() + 1);     // Catch: Throwable -> L7 SQLiteException -> L9
        r8.append(r25);     // Catch: Throwable -> L7 SQLiteException -> L9
        r8.append("*");     // Catch: Throwable -> L7 SQLiteException -> L9
        r4.add(r8.toString());     // Catch: Throwable -> L7 SQLiteException -> L9
        r5.append(" and name glob ?");     // Catch: Throwable -> L7 SQLiteException -> L9
    L15:
        String[] r3 = (String[]) r4.toArray(new String[r4.size()]);     // Catch: Throwable -> L7 SQLiteException -> L9
        String r52 = r5.toString();     // Catch: Throwable -> L7 SQLiteException -> L9
        zzic r82 = this.zzu;     // Catch: Throwable -> L7 SQLiteException -> L9
        r82.zzc();     // Catch: Throwable -> L7 SQLiteException -> L9
        Cursor r32 = zze().query("user_attributes", new String[]{"name", "set_timestamp", "value", "origin"}, r52, r3, null, null, "rowid", "1001");     // Catch: Throwable -> L7 SQLiteException -> L9
    L64:
    L40:
        e = e;
        Cursor r12 = r32;
    L55:
        this.zzu.zzaW().zzb().zzd("(2)Error querying user properties", zzgu.zzl(r23), r14, e);     // Catch: Throwable -> L51
        r2 = Collections.EMPTY_LIST;     // Catch: Throwable -> L51
        r32 = r12;
    L48:
        if (r32 == null) goto L50;
        r32.close();
    L50:
        return r2;
    L51:
        th = th;
    L52:
        if (r12 == null) goto L54;
        r12.close();
    L54:
        throw th;
    L22:
        th = th;
        r12 = r32;
        goto L52
    L17:
        if (r32.moveToFirst() == false) goto L48;
    L19:
        int r42 = r2.size();     // Catch: Throwable -> L22 SQLiteException -> L40
        r82.zzc();     // Catch: Throwable -> L22 SQLiteException -> L40
        if (r42 >= 1000) goto L21;
        String r17 = r32.getString(0);     // Catch: Throwable -> L22 SQLiteException -> L40
        long r18 = r32.getLong(1);     // Catch: Throwable -> L22 SQLiteException -> L40
        Object r20 = zzL(r32, 2);     // Catch: Throwable -> L22 SQLiteException -> L40
        String r43 = r32.getString(3);     // Catch: Throwable -> L22 SQLiteException -> L40
        if (r20 != null) goto L30;
        r82.zzaW().zzb().zzd("(2)Read invalid user property value, ignoring it", zzgu.zzl(r15), r43, r25);     // Catch: Throwable -> L22 SQLiteException -> L27
        String r16 = r43;
    L32:
        if (r32.moveToNext() == false) goto L48;
        r15 = r23;
        r14 = r16;
    L36:
        e = e;
    L37:
        r12 = r32;
        r14 = r16;
    L27:
        e = e;
        r16 = r43;
        goto L37
    L30:
        r16 = r43;
        r2.add(new zzpn(r15, r16, r17, r18, r20));     // Catch: Throwable -> L22 SQLiteException -> L36
        goto L32
    L21:
        zzgs r0 = r82.zzaW().zzb();     // Catch: Throwable -> L22 SQLiteException -> L40
        r82.zzc();     // Catch: Throwable -> L22 SQLiteException -> L40
        r0.zzb("Read more than the max allowed user properties, ignoring excess", 1000);     // Catch: Throwable -> L22 SQLiteException -> L40
    L9:
        e = e;
    L45:
        r12 = null;
        goto L55
    L11:
        r14 = r24;
    L7:
        th = th;
        r12 = null;
    L42:
        e = e;
        r14 = r24;
        goto L45
    }

    public final boolean zzp(zzah r8) {
        Preconditions.checkNotNull(r8);
        zzg();
        zzay();
        String r0 = r8.zza;
        Preconditions.checkNotNull(r0);
        if (zzm(r0, r8.zzc.zzb) != null) goto L8;
        long r1 = zzaA("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{r0});
        this.zzu.zzc();
        if (r1 < 1000) goto L8;
        return false;
    L8:
        ContentValues r12 = new ContentValues();
        r12.put("app_id", r0);
        r12.put("origin", r8.zzb);
        r12.put("name", r8.zzc.zzb);
        zzaw(r12, "value", Preconditions.checkNotNull(r8.zzc.zza()));
        r12.put("active", Boolean.valueOf(r8.zze));
        r12.put("trigger_event_name", r8.zzf);
        r12.put("trigger_timeout", Long.valueOf(r8.zzh));
        zzic r2 = this.zzu;
        zzbh r3 = r8.zzg;
        r12.put("timed_out_event", r2.zzk().zzah(r3));
        r12.put("creation_timestamp", Long.valueOf(r8.zzd));
        r12.put("triggered_event", r2.zzk().zzah(r8.zzi));
        r12.put("triggered_timestamp", Long.valueOf(r8.zzc.zzc));
        r12.put("time_to_live", Long.valueOf(r8.zzj));
        zzbh r82 = r8.zzk;
        r12.put("expired_event", r2.zzk().zzah(r82));
    L13:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing conditional user property", zzgu.zzl(r0), e);
        return true;
    L10:
        if (zze().insertWithOnConflict("conditional_properties", null, r12, 5) != (-1)) goto L19;
        r2.zzaW().zzb().zzb("Failed to insert/update conditional user property (got -1)", zzgu.zzl(r0));     // Catch: SQLiteException -> L13
        return true;
    L19:
        return true;
    }

    public final zzah zzq(String r26, String r27) {
        Preconditions.checkNotEmpty(r26);
        Preconditions.checkNotEmpty(r27);
        zzg();
        zzay();
        Cursor r8 = null;
        Cursor r9 = zze().query("conditional_properties", new String[]{"origin", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{r26, r27}, null, null, null);     // Catch: Throwable -> L30 SQLiteException -> L32
    L13:
        e = e;
        String r3 = r27;
    L34:
        zzic r1 = this.zzu;     // Catch: Throwable -> L11
        r1.zzaW().zzb().zzd("Error querying conditional property", zzgu.zzl(r26), r1.zzl().zzc(r3), e);     // Catch: Throwable -> L11
    L35:
        if (r9 == null) goto L37;
        r9.close();
    L37:
        return null;
    L11:
        th = th;
        r8 = r9;
    L38:
        if (r8 == null) goto L40;
        r8.close();
    L40:
        throw th;
    L5:
        if (r9.moveToFirst() == false) goto L35;
        String r2 = r9.getString(0);     // Catch: Throwable -> L11 SQLiteException -> L13
        if (r2 != null) goto L10;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        String r7 = r2;
        Object r6 = zzL(r9, 1);     // Catch: Throwable -> L11 SQLiteException -> L13
        if (r9.getInt(2) == 0) goto L18;
        boolean r16 = true;
    L19:
        String r17 = r9.getString(3);     // Catch: Throwable -> L11 SQLiteException -> L13
        long r19 = r9.getLong(4);     // Catch: Throwable -> L11 SQLiteException -> L13
        zzpg r0 = this.zzg;     // Catch: Throwable -> L11 SQLiteException -> L13
        zzpk r22 = r0.zzp();     // Catch: Throwable -> L11 SQLiteException -> L13
        byte[] r32 = r9.getBlob(5);     // Catch: Throwable -> L11 SQLiteException -> L13
        Parcelable.Creator<zzbh> r4 = zzbh.CREATOR;     // Catch: Throwable -> L11 SQLiteException -> L13
        zzbh r18 = (zzbh) r22.zzl(r32, r4);     // Catch: Throwable -> L11 SQLiteException -> L13
        long r14 = r9.getLong(6);     // Catch: Throwable -> L11 SQLiteException -> L13
        zzbh r21 = (zzbh) r0.zzp().zzl(r9.getBlob(7), r4);     // Catch: Throwable -> L11 SQLiteException -> L13
        long r23 = r9.getLong(8);     // Catch: Throwable -> L11 SQLiteException -> L13
        r3 = r27;
        zzah r10 = new zzah(r26, r7, new zzpl(r3, r23, r6, r7), r14, r16, r17, r18, r19, r21, r9.getLong(9), (zzbh) r0.zzp().zzl(r9.getBlob(10), r4));     // Catch: Throwable -> L11 SQLiteException -> L25
        if (r9.moveToNext() == false) goto L27;
        zzic r02 = this.zzu;     // Catch: Throwable -> L11 SQLiteException -> L25
        r02.zzaW().zzb().zzc("Got multiple records for conditional property, expected one", zzgu.zzl(r26), r02.zzl().zzc(r3));     // Catch: Throwable -> L11 SQLiteException -> L25
    L27:
        r9.close();
        return r10;
    L25:
        e = e;
        goto L34
    L18:
        r16 = false;
        goto L19
    L32:
        e = e;
        r3 = r27;
        r9 = null;
    L30:
        th = th;
        goto L38
    }

    public final int zzr(String r5, String r6) {
        Preconditions.checkNotEmpty(r5);
        Preconditions.checkNotEmpty(r6);
        zzg();
        zzay();
        return zze().delete("conditional_properties", "app_id=? and name=?", new String[]{r5, r6});
    L5:
        e = move-exception;
        zzic r4 = this.zzu;
        r4.zzaW().zzb().zzd("Error deleting conditional property", zzgu.zzl(r5), r4.zzl().zzc(r6), e);
        return 0;
    }

    public final List zzs(String r3, String r4, String r5) {
        Preconditions.checkNotEmpty(r3);
        zzg();
        zzay();
        ArrayList r0 = new ArrayList(3);
        r0.add(r3);
        StringBuilder r32 = new StringBuilder("app_id=?");
        if (TextUtils.isEmpty(r4) == true) goto L6;
        r0.add(r4);
        r32.append(" and origin=?");
    L6:
        if (TextUtils.isEmpty(r5) == true) goto L8;
        r0.add(String.valueOf(r5).concat("*"));
        r32.append(" and name glob ?");
    L8:
        String[] r42 = (String[]) r0.toArray(new String[r0.size()]);
        return zzt(r32.toString(), r42);
    }

    public final List zzt(String r29, String[] r30) {
        zzg();
        zzay();
        List r0 = new ArrayList();
        Cursor r11 = null;
        zzic r12 = this.zzu;     // Catch: Throwable -> L8 SQLiteException -> L10
        r12.zzc();     // Catch: Throwable -> L8 SQLiteException -> L10
        r11 = zze().query("conditional_properties", new String[]{"app_id", "origin", "name", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, r29, r30, null, null, "rowid", "1001");     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r11.moveToFirst() == false) goto L20;
    L5:
        int r2 = r0.size();     // Catch: Throwable -> L8 SQLiteException -> L10
        r12.zzc();     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r2 >= 1000) goto L7;
        String r14 = r11.getString(0);     // Catch: Throwable -> L8 SQLiteException -> L10
        String r15 = r11.getString(1);     // Catch: Throwable -> L8 SQLiteException -> L10
        String r5 = r11.getString(2);     // Catch: Throwable -> L8 SQLiteException -> L10
        Object r8 = zzL(r11, 3);     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r11.getInt(4) == 0) goto L15;
        boolean r19 = true;
    L16:
        String r20 = r11.getString(5);     // Catch: Throwable -> L8 SQLiteException -> L10
        long r22 = r11.getLong(6);     // Catch: Throwable -> L8 SQLiteException -> L10
        zzpg r23 = this.zzg;     // Catch: Throwable -> L8 SQLiteException -> L10
        zzpk r3 = r23.zzp();     // Catch: Throwable -> L8 SQLiteException -> L10
        byte[] r4 = r11.getBlob(7);     // Catch: Throwable -> L8 SQLiteException -> L10
        Parcelable.Creator<zzbh> r6 = zzbh.CREATOR;     // Catch: Throwable -> L8 SQLiteException -> L10
        zzbh r21 = (zzbh) r3.zzl(r4, r6);     // Catch: Throwable -> L8 SQLiteException -> L10
        long r17 = r11.getLong(8);     // Catch: Throwable -> L8 SQLiteException -> L10
        zzbh r24 = (zzbh) r23.zzp().zzl(r11.getBlob(9), r6);     // Catch: Throwable -> L8 SQLiteException -> L10
        long r32 = r11.getLong(10);     // Catch: Throwable -> L8 SQLiteException -> L10
        r0.add(new zzah(r14, r15, new zzpl(r5, r32, r8, r15), r17, r19, r20, r21, r22, r24, r11.getLong(11), (zzbh) r23.zzp().zzl(r11.getBlob(12), r6)));     // Catch: Throwable -> L8 SQLiteException -> L10
        if (r11.moveToNext() == true) goto L5;
    L15:
        r19 = false;
        goto L16
    L7:
        zzgs r25 = r12.zzaW().zzb();     // Catch: Throwable -> L8 SQLiteException -> L10
        r12.zzc();     // Catch: Throwable -> L8 SQLiteException -> L10
        r25.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);     // Catch: Throwable -> L8 SQLiteException -> L10
    L20:
        if (r11 == null) goto L22;
        r11.close();
    L22:
        return r0;
    L8:
        th = move-exception;
        if (r11 == null) goto L25;
        r11.close();
    L25:
        throw th;
    L10:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Error querying conditional user property value", e);     // Catch: Throwable -> L8
        r0 = Collections.EMPTY_LIST;     // Catch: Throwable -> L8
        goto L20
    }

    public final zzh zzu(String r53) {
        Preconditions.checkNotEmpty(r53);
        zzg();
        zzay();
        Cursor r3 = null;
        Cursor r4 = zze().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id", "session_stitching_token", "sgtm_upload_enabled", "target_os_version", "session_stitching_token_hash", "ad_services_version", "unmatched_first_open_without_ad_id", "npa_metadata_value", "attribution_eligibility_status", "sgtm_preview_key", "dma_consent_state", "daily_realtime_dcu_count", "bundle_delivery_index", "serialized_npa_metadata", "unmatched_pfo", "unmatched_uwa", "ad_campaign_info", "client_upload_eligibility", "last_diagnostics_signal_upload_timestamp"}, "app_id=?", new String[]{r53}, null, null, null);     // Catch: Throwable -> L89 SQLiteException -> L91
    L12:
        e = e;
    L93:
        this.zzu.zzaW().zzb().zzc("Error querying app. appId", zzgu.zzl(r53), e);     // Catch: Throwable -> L10
    L94:
        if (r4 == null) goto L96;
        r4.close();
    L96:
        return null;
    L10:
        th = th;
        r3 = r4;
    L97:
        if (r3 == null) goto L99;
        r3.close();
    L99:
        throw th;
    L5:
        if (r4.moveToFirst() == false) goto L94;
        zzpg r5 = this.zzg;     // Catch: Throwable -> L10 SQLiteException -> L12
        zzh r0 = new zzh(r5.zzah(), r53);     // Catch: Throwable -> L10 SQLiteException -> L12
        zzjl r6 = r5.zzB(r53);     // Catch: Throwable -> L10 SQLiteException -> L12
        zzjk r7 = zzjk.zzb;     // Catch: Throwable -> L10 SQLiteException -> L12
        boolean r8 = false;
        if (r6.zzo(r7) == false) goto L14;
        r0.zze(r4.getString(0));     // Catch: Throwable -> L10 SQLiteException -> L12
    L14:
        r0.zzg(r4.getString(1));     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r5.zzB(r53).zzo(zzjk.zza) == false) goto L17;
        r0.zzk(r4.getString(2));     // Catch: Throwable -> L10 SQLiteException -> L12
    L17:
        r0.zzF(r4.getLong(3));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzo(r4.getLong(4));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzq(r4.getLong(5));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzs(r4.getString(6));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzw(r4.getString(7));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzy(r4.getLong(8));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzA(r4.getLong(9));     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(10) == false) goto L20;
    L21:
        boolean r9 = true;
    L23:
        r0.zzE(r9);     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzO(r4.getLong(11));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzQ(r4.getLong(12));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzS(r4.getLong(13));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzU(r4.getLong(14));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzI(r4.getLong(15));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzK(r4.getLong(16));     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(17) == false) goto L26;
        long r92 = -2147483648L;
    L27:
        r0.zzu(r92);     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzm(r4.getString(18));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzY(r4.getLong(19));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzW(r4.getLong(20));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzab(r4.getString(21));     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(23) == false) goto L30;
    L31:
        boolean r93 = true;
    L33:
        r0.zzad(r93);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(25) == false) goto L36;
        long r94 = 0;
    L37:
        r0.zzC(r94);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(26) == true) goto L41;
        r0.zzah(Arrays.asList(r4.getString(26).split(",", -1)));     // Catch: Throwable -> L10 SQLiteException -> L12
    L41:
        if (r5.zzB(r53).zzo(r7) == false) goto L44;
        r0.zzi(r4.getString(28));     // Catch: Throwable -> L10 SQLiteException -> L12
    L44:
        if (r4.isNull(29) == false) goto L46;
    L48:
        boolean r52 = false;
    L49:
        r0.zzaj(r52);     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzaE(r4.getLong(39));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzaz(r4.getString(36));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzal(r4.getLong(30));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzan(r4.getLong(31));     // Catch: Throwable -> L10 SQLiteException -> L12
        zzaif.zza();     // Catch: Throwable -> L10 SQLiteException -> L12
        zzic r54 = this.zzu;     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r54.zzc().zzp(r53, zzfy.zzaO) == false) goto L53;
        r0.zzap(r4.getInt(32));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzax(r4.getLong(35));     // Catch: Throwable -> L10 SQLiteException -> L12
    L53:
        if (r4.isNull(33) == false) goto L55;
    L57:
        boolean r72 = false;
    L58:
        r0.zzar(r72);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(34) == false) goto L62;
        Boolean r62 = null;
    L65:
        r0.zzaf(r62);     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzaB(r4.getInt(37));     // Catch: Throwable -> L10 SQLiteException -> L12
        r0.zzaD(r4.getInt(38));     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(40) == false) goto L68;
        String r63 = _UrlKt.FRAGMENT_ENCODE_SET;
    L69:
        r0.zzaG(r63);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(41) == true) goto L73;
        r0.zzat(Long.valueOf(r4.getLong(41)));     // Catch: Throwable -> L10 SQLiteException -> L12
    L73:
        if (r4.isNull(42) == true) goto L75;
        r0.zzav(Long.valueOf(r4.getLong(42)));     // Catch: Throwable -> L10 SQLiteException -> L12
    L75:
        r0.zzaI(r4.getBlob(43));     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.isNull(44) == true) goto L79;
        r0.zzaK(r4.getInt(44));     // Catch: Throwable -> L10 SQLiteException -> L12
    L79:
        if (r54.zzc().zzp(r53, zzfy.zzbj) == true) goto L81;
    L83:
        r0.zzb();     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r4.moveToNext() == false) goto L86;
        r54.zzaW().zzb().zzb("Got multiple records for app, expected one. appId", zzgu.zzl(r53));     // Catch: Throwable -> L10 SQLiteException -> L12
    L86:
        r4.close();
        return r0;
    L81:
        if (r4.isNull(45) == true) goto L83;
        r0.zzaM(r4.getLong(45));     // Catch: Throwable -> L10 SQLiteException -> L12
        goto L83
    L68:
        r63 = (String) Preconditions.checkNotNull(r4.getString(40));     // Catch: Throwable -> L10 SQLiteException -> L12
        goto L69
    L62:
        if (r4.getInt(34) == 0) goto L64;
        r8 = true;
    L64:
        r62 = Boolean.valueOf(r8);     // Catch: Throwable -> L10 SQLiteException -> L12
        goto L65
    L55:
        if (r4.getInt(33) == 0) goto L57;
        r72 = true;
        goto L58
    L46:
        if (r4.getInt(29) == 0) goto L48;
        r52 = true;
        goto L49
    L36:
        r94 = r4.getLong(25);     // Catch: Throwable -> L10 SQLiteException -> L12
        goto L37
    L30:
        if (r4.getInt(23) != 0) goto L31;
        r93 = false;
        goto L33
    L26:
        r92 = r4.getInt(17);     // Catch: Throwable -> L10 SQLiteException -> L12
        goto L27
    L20:
        if (r4.getInt(10) != 0) goto L21;
        r9 = false;
        goto L23
    L91:
        e = e;
        r4 = null;
    L89:
        th = th;
        goto L97
    }

    public final void zzv(zzh r9, boolean r10, boolean r11) {
        Preconditions.checkNotNull(r9);
        zzg();
        zzay();
        String r0 = r9.zzc();
        Preconditions.checkNotNull(r0);
        ContentValues r1 = new ContentValues();
        r1.put("app_id", r0);
        if (r10 == false) goto L6;
        r1.put("app_instance_id", null);
    L8:
        r1.put("gmp_app_id", r9.zzf());
        zzpg r102 = this.zzg;
        if (r102.zzB(r0).zzo(zzjk.zza) == false) goto L11;
        r1.put("resettable_device_id_hash", r9.zzj());
    L11:
        r1.put("last_bundle_index", Long.valueOf(r9.zzG()));
        r1.put("last_bundle_start_timestamp", Long.valueOf(r9.zzn()));
        r1.put("last_bundle_end_timestamp", Long.valueOf(r9.zzp()));
        r1.put("app_version", r9.zzr());
        r1.put("app_store", r9.zzv());
        r1.put("gmp_version", Long.valueOf(r9.zzx()));
        r1.put("dev_cert_hash", Long.valueOf(r9.zzz()));
        r1.put("measurement_enabled", Boolean.valueOf(r9.zzD()));
        r1.put("day", Long.valueOf(r9.zzN()));
        r1.put("daily_public_events_count", Long.valueOf(r9.zzP()));
        r1.put("daily_events_count", Long.valueOf(r9.zzR()));
        r1.put("daily_conversions_count", Long.valueOf(r9.zzT()));
        r1.put("config_fetched_time", Long.valueOf(r9.zzH()));
        r1.put("failed_config_fetch_time", Long.valueOf(r9.zzJ()));
        r1.put("app_version_int", Long.valueOf(r9.zzt()));
        r1.put("firebase_instance_id", r9.zzl());
        r1.put("daily_error_events_count", Long.valueOf(r9.zzX()));
        r1.put("daily_realtime_events_count", Long.valueOf(r9.zzV()));
        r1.put("health_monitor_sample", r9.zzZ());
        r1.put("android_id", 0L);
        r1.put("adid_reporting_enabled", Boolean.valueOf(r9.zzac()));
        r1.put("dynamite_version", Long.valueOf(r9.zzB()));
        if (r102.zzB(r0).zzo(zzjk.zzb) == false) goto L14;
        r1.put("session_stitching_token", r9.zzh());
    L14:
        r1.put("sgtm_upload_enabled", Boolean.valueOf(r9.zzai()));
        r1.put("target_os_version", Long.valueOf(r9.zzak()));
        r1.put("session_stitching_token_hash", Long.valueOf(r9.zzam()));
        zzaif.zza();
        zzic r103 = this.zzu;
        if (r103.zzc().zzp(r0, zzfy.zzaO) == false) goto L17;
        r1.put("ad_services_version", Integer.valueOf(r9.zzao()));
        r1.put("attribution_eligibility_status", Long.valueOf(r9.zzaw()));
    L17:
        r1.put("unmatched_first_open_without_ad_id", Boolean.valueOf(r9.zzaq()));
        r1.put("npa_metadata_value", r9.zzae());
        r1.put("bundle_delivery_index", Long.valueOf(r9.zzaF()));
        r1.put("sgtm_preview_key", r9.zzay());
        r1.put("dma_consent_state", Integer.valueOf(r9.zzaA()));
        r1.put("daily_realtime_dcu_count", Integer.valueOf(r9.zzaC()));
        r1.put("serialized_npa_metadata", r9.zzaH());
        r1.put("client_upload_eligibility", Integer.valueOf(r9.zzaL()));
        List r2 = r9.zzag();
        if (r2 != null) goto L20;
    L23:
        zzahk.zza();
        if (r103.zzc().zzp(null, zzfy.zzaK) == true) goto L26;
    L28:
        r1.put("unmatched_pfo", r9.zzas());
        r1.put("unmatched_uwa", r9.zzau());
        r1.put("ad_campaign_info", r9.zzaJ());
        if (r103.zzc().zzp(r0, zzfy.zzbj) == false) goto L42;
        r1.put("last_diagnostics_signal_upload_timestamp", Long.valueOf(r9.zzaN()));
    L42:
        SQLiteDatabase r92 = zze();     // Catch: SQLiteException -> L37
        if (r92.update("apps", r1, "app_id = ?", new String[]{r0}) == 0) goto L34;
        return;
    L34:
        if (r92.insertWithOnConflict("apps", null, r1, 5) != (-1)) goto L44;
        r103.zzaW().zzb().zzb("Failed to insert/update app (got -1). appId", zzgu.zzl(r0));     // Catch: SQLiteException -> L37
        return;
    L44:
        return;
    L37:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing app. appId", zzgu.zzl(r0), e);
        return;
    L26:
        if (r1.containsKey("safelisted_events") == true) goto L28;
        r1.put("safelisted_events", null);
        goto L28
    L20:
        if (r2.isEmpty() == false) goto L22;
        r103.zzaW().zze().zzb("Safelisted events should not be an empty list. appId", r0);
        goto L23
    L22:
        r1.put("safelisted_events", TextUtils.join(",", r2));
        goto L23
    L6:
        if (this.zzg.zzB(r0).zzo(zzjk.zzb) == false) goto L8;
        r1.put("app_instance_id", r9.zzd());
        goto L8
    }

    public final zzar zzw(long r14, String r16, boolean r17, boolean r18, boolean r19, boolean r20, boolean r21, boolean r22, boolean r23) {
        return zzx(r14, r16, 1, false, false, r19, false, r21, r22, r23);
    }

    public final zzar zzx(long r14, String r16, long r17, boolean r19, boolean r20, boolean r21, boolean r22, boolean r23, boolean r24, boolean r25) {
        Preconditions.checkNotEmpty(r16);
        zzg();
        zzay();
        String[] r0 = {r16};
        zzar r1 = new zzar();
        Cursor r2 = null;
        SQLiteDatabase r3 = zze();     // Catch: Throwable -> L6 SQLiteException -> L8
        r2 = r3.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count", "daily_realtime_dcu_count", "daily_registered_triggers_count"}, "app_id=?", new String[]{r16}, null, null, null);     // Catch: Throwable -> L6 SQLiteException -> L8
        if (r2.moveToFirst() == true) goto L11;
        this.zzu.zzaW().zze().zzb("Not updating daily counts, app is not known. appId", zzgu.zzl(r16));     // Catch: Throwable -> L6 SQLiteException -> L8
    L30:
        if (r2 == null) goto L32;
        r2.close();
    L32:
        return r1;
    L11:
        if (r2.getLong(0) != r14) goto L13;
        r1.zzb = r2.getLong(1);     // Catch: Throwable -> L6 SQLiteException -> L8
        r1.zza = r2.getLong(2);     // Catch: Throwable -> L6 SQLiteException -> L8
        r1.zzc = r2.getLong(3);     // Catch: Throwable -> L6 SQLiteException -> L8
        r1.zzd = r2.getLong(4);     // Catch: Throwable -> L6 SQLiteException -> L8
        r1.zze = r2.getLong(5);     // Catch: Throwable -> L6 SQLiteException -> L8
        r1.zzf = r2.getLong(6);     // Catch: Throwable -> L6 SQLiteException -> L8
        r1.zzg = r2.getLong(7);     // Catch: Throwable -> L6 SQLiteException -> L8
    L13:
        if (r19 == false) goto L15;
        r1.zzb += r17;
    L15:
        if (r20 == false) goto L17;
        r1.zza += r17;
    L17:
        if (r21 == false) goto L19;
        r1.zzc += r17;
    L19:
        if (r22 == false) goto L21;
        r1.zzd += r17;
    L21:
        if (r23 == false) goto L23;
        r1.zze += r17;
    L23:
        if (r24 == false) goto L25;
        r1.zzf += r17;
    L25:
        if (r25 == false) goto L27;
        r1.zzg += r17;
    L27:
        ContentValues r4 = new ContentValues();     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("day", Long.valueOf(r14));     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("daily_public_events_count", Long.valueOf(r1.zza));     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("daily_events_count", Long.valueOf(r1.zzb));     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("daily_conversions_count", Long.valueOf(r1.zzc));     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("daily_error_events_count", Long.valueOf(r1.zzd));     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("daily_realtime_events_count", Long.valueOf(r1.zze));     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("daily_realtime_dcu_count", Long.valueOf(r1.zzf));     // Catch: Throwable -> L6 SQLiteException -> L8
        r4.put("daily_registered_triggers_count", Long.valueOf(r1.zzg));     // Catch: Throwable -> L6 SQLiteException -> L8
        r3.update("apps", r4, "app_id=?", r0);     // Catch: Throwable -> L6 SQLiteException -> L8
    L8:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error updating daily counts. appId", zzgu.zzl(r16), e);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        if (0 == 0) goto L38;
        r2.close();
        throw th;
    L38:
        throw th;
    }

    public final zzaq zzy(String r11) {
        Preconditions.checkNotEmpty(r11);
        zzg();
        zzay();
        Cursor r1 = null;
        Cursor r2 = zze().query("apps", new String[]{"remote_config", "config_last_modified_time", "e_tag"}, "app_id=?", new String[]{r11}, null, null, null);     // Catch: Throwable -> L20 SQLiteException -> L22
    L12:
        e = e;
    L24:
        this.zzu.zzaW().zzb().zzc("Error querying remote config. appId", zzgu.zzl(r11), e);     // Catch: Throwable -> L10
    L25:
        if (r2 == null) goto L27;
        r2.close();
    L27:
        return null;
    L10:
        th = move-exception;
        Throwable r10 = th;
        r1 = r2;
    L28:
        if (r1 == null) goto L36;
        r1.close();
        throw r10;
    L36:
        throw r10;
    L5:
        if (r2.moveToFirst() == false) goto L25;
        byte[] r0 = r2.getBlob(0);     // Catch: Throwable -> L10 SQLiteException -> L12
        String r3 = r2.getString(1);     // Catch: Throwable -> L10 SQLiteException -> L12
        String r4 = r2.getString(2);     // Catch: Throwable -> L10 SQLiteException -> L12
        if (r2.moveToNext() == false) goto L14;
        this.zzu.zzaW().zzb().zzb("Got multiple records for app config, expected one. appId", zzgu.zzl(r11));     // Catch: Throwable -> L10 SQLiteException -> L12
    L14:
        if (r0 == null) goto L25;
        zzaq r5 = new zzaq(r0, r3, r4);     // Catch: Throwable -> L10 SQLiteException -> L12
        r2.close();
        return r5;
    L22:
        e = e;
        r2 = null;
    L20:
        th = move-exception;
        r10 = th;
        goto L28
    }

    public final boolean zzz(com.google.android.gms.internal.measurement.zzid r8, boolean r9) {
        zzg();
        zzay();
        Preconditions.checkNotNull(r8);
        Preconditions.checkNotEmpty(r8.zzA());
        Preconditions.checkState(r8.zzn());
        zzI();
        zzic r0 = this.zzu;
        long r1 = r0.zzba().currentTimeMillis();
        long r3 = r8.zzo();
        r0.zzc();
        if (r3 < (r1 - zzal.zzI())) goto L6;
        long r32 = r8.zzo();
        r0.zzc();
        if (r32 > (zzal.zzI() + r1)) goto L6;
    L7:
        byte[] r02 = r8.zzcd();
        byte[] r03 = this.zzg.zzp().zzv(r02);     // Catch: IOException -> L22
        zzic r2 = this.zzu;
        r2.zzaW().zzk().zzb("Saving bundle, size", Integer.valueOf(r03.length));
        ContentValues r33 = new ContentValues();
        r33.put("app_id", r8.zzA());
        r33.put("bundle_end_timestamp", Long.valueOf(r8.zzo()));
        r33.put("data", r03);
        r33.put("has_realtime", Integer.valueOf(r9 ? 1 : 0));
        if (r8.zzaa() == false) goto L27;
        r33.put("retry_count", Integer.valueOf(r8.zzab()));
    L27:
    L16:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Error storing bundle. appId", zzgu.zzl(r8.zzA()), e);
        return false;
    L13:
        if (zze().insert("queue", null, r33) != (-1)) goto L18;
        r2.zzaW().zzb().zzb("Failed to insert bundle (got -1). appId", zzgu.zzl(r8.zzA()));     // Catch: SQLiteException -> L16
        return false;
    L18:
        return true;
    L22:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Data loss. Failed to serialize bundle. appId", zzgu.zzl(r8.zzA()), e);
        return false;
    L6:
        r0.zzaW().zze().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzgu.zzl(r8.zzA()), Long.valueOf(r1), Long.valueOf(r8.zzo()));
        goto L7
    }
}
