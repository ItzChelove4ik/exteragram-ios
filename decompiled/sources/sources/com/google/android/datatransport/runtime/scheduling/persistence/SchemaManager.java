package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.car.app.AppInfo$$ExternalSyntheticBUOutline0;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
final class SchemaManager extends SQLiteOpenHelper {
    private static final String CREATE_INITIAL_GLOBAL_LOG_EVENT_STATE_VALUE_SQL = null;
    private static final List<Migration> INCREMENTAL_MIGRATIONS = null;
    private static final Migration MIGRATE_TO_V1 = null;
    private static final Migration MIGRATE_TO_V2 = null;
    private static final Migration MIGRATE_TO_V3 = null;
    private static final Migration MIGRATE_TO_V4 = null;
    private static final Migration MIGRATE_TO_V6 = null;
    private static final Migration MIGRATE_TO_V7 = null;
    private static final Migration MIGRATION_TO_V5 = null;
    static int SCHEMA_VERSION;
    private boolean configured;
    private final int schemaVersion;

    public interface Migration {
        void upgrade(SQLiteDatabase r1);
    }

    public static /* synthetic */ void $r8$lambda$6O8JIimDLSwAQqPiJF1tsPggWhY(SQLiteDatabase r1) {
        r1.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY, context_id INTEGER NOT NULL, transport_name TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, uptime_ms INTEGER NOT NULL, payload BLOB NOT NULL, code INTEGER, num_attempts INTEGER NOT NULL,FOREIGN KEY (context_id) REFERENCES transport_contexts(_id) ON DELETE CASCADE)");
        r1.execSQL("CREATE TABLE event_metadata (_id INTEGER PRIMARY KEY, event_id INTEGER NOT NULL, name TEXT NOT NULL, value TEXT NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE)");
        r1.execSQL("CREATE TABLE transport_contexts (_id INTEGER PRIMARY KEY, backend_name TEXT NOT NULL, priority INTEGER NOT NULL, next_request_ms INTEGER NOT NULL)");
        r1.execSQL("CREATE INDEX events_backend_id on events(context_id)");
        r1.execSQL("CREATE UNIQUE INDEX contexts_backend_priority on transport_contexts(backend_name, priority)");
    }

    public static /* synthetic */ void $r8$lambda$_Gnx1vnj32VOEkFIbdCjsyHxuFo(SQLiteDatabase r1) {
        r1.execSQL("ALTER TABLE events ADD COLUMN pseudonymous_id TEXT");
        r1.execSQL("ALTER TABLE events ADD COLUMN experiment_ids_clear_blob BLOB");
        r1.execSQL("ALTER TABLE events ADD COLUMN experiment_ids_encrypted_blob BLOB");
    }

    public static /* synthetic */ void $r8$lambda$cHOim1FpUrPnqVxPZ3CFfyaT3Ho(SQLiteDatabase r1) {
        r1.execSQL("ALTER TABLE events ADD COLUMN payload_encoding TEXT");
    }

    /* renamed from: $r8$lambda$qLPbOYW3vYokeWUTGz-9t89hvNg, reason: not valid java name */
    public static /* synthetic */ void m1818$r8$lambda$qLPbOYW3vYokeWUTGz9t89hvNg(SQLiteDatabase r1) {
        r1.execSQL("DROP TABLE IF EXISTS log_event_dropped");
        r1.execSQL("DROP TABLE IF EXISTS global_log_event_state");
        r1.execSQL("CREATE TABLE log_event_dropped (log_source VARCHAR(45) NOT NULL,reason INTEGER NOT NULL,events_dropped_count BIGINT NOT NULL,PRIMARY KEY(log_source, reason))");
        r1.execSQL("CREATE TABLE global_log_event_state (last_metrics_upload_ms BIGINT PRIMARY KEY)");
        r1.execSQL(CREATE_INITIAL_GLOBAL_LOG_EVENT_STATE_VALUE_SQL);
    }

    public static /* synthetic */ void $r8$lambda$rvrWdk5bxK64ZfDCs7SIT_XqiYc(SQLiteDatabase r1) {
        r1.execSQL("ALTER TABLE events ADD COLUMN product_id INTEGER");
    }

    public static /* synthetic */ void $r8$lambda$sUYLmiPu6mhLbt9d0yj4BIg5XSE(SQLiteDatabase r1) {
        r1.execSQL("ALTER TABLE transport_contexts ADD COLUMN extras BLOB");
        r1.execSQL("CREATE UNIQUE INDEX contexts_backend_priority_extras on transport_contexts(backend_name, priority, extras)");
        r1.execSQL("DROP INDEX contexts_backend_priority");
    }

    /* renamed from: $r8$lambda$y1ozSJfaQLQnaXGsXgjLyyzC-Do, reason: not valid java name */
    public static /* synthetic */ void m1819$r8$lambda$y1ozSJfaQLQnaXGsXgjLyyzCDo(SQLiteDatabase r1) {
        r1.execSQL("ALTER TABLE events ADD COLUMN inline BOOLEAN NOT NULL DEFAULT 1");
        r1.execSQL("DROP TABLE IF EXISTS event_payloads");
        r1.execSQL("CREATE TABLE event_payloads (sequence_num INTEGER NOT NULL, event_id INTEGER NOT NULL, bytes BLOB NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE,PRIMARY KEY (sequence_num, event_id))");
    }

    static {
        CREATE_INITIAL_GLOBAL_LOG_EVENT_STATE_VALUE_SQL = "INSERT INTO global_log_event_state VALUES (" + System.currentTimeMillis() + ")";
        SCHEMA_VERSION = 7;
        SchemaManager$$ExternalSyntheticLambda0 r1 = new SchemaManager$$ExternalSyntheticLambda0();
        MIGRATE_TO_V1 = r1;
        SchemaManager$$ExternalSyntheticLambda1 r2 = new SchemaManager$$ExternalSyntheticLambda1();
        MIGRATE_TO_V2 = r2;
        SchemaManager$$ExternalSyntheticLambda2 r3 = new SchemaManager$$ExternalSyntheticLambda2();
        MIGRATE_TO_V3 = r3;
        SchemaManager$$ExternalSyntheticLambda3 r4 = new SchemaManager$$ExternalSyntheticLambda3();
        MIGRATE_TO_V4 = r4;
        SchemaManager$$ExternalSyntheticLambda4 r5 = new SchemaManager$$ExternalSyntheticLambda4();
        MIGRATION_TO_V5 = r5;
        SchemaManager$$ExternalSyntheticLambda5 r6 = new SchemaManager$$ExternalSyntheticLambda5();
        MIGRATE_TO_V6 = r6;
        SchemaManager$$ExternalSyntheticLambda6 r7 = new SchemaManager$$ExternalSyntheticLambda6();
        MIGRATE_TO_V7 = r7;
        INCREMENTAL_MIGRATIONS = Arrays.asList(new Migration[]{r1, r2, r3, r4, r5, r6, r7});
    }

    public SchemaManager(Context r2, String r3, int r4) {
        super(r2, r3, null, r4);
        this.configured = false;
        this.schemaVersion = r4;
    }

    private void ensureConfigured(SQLiteDatabase r2) {
        if (this.configured == true) goto L6;
        onConfigure(r2);
        return;
    }

    private void onCreate(SQLiteDatabase r2, int r3) {
        ensureConfigured(r2);
        upgrade(r2, 0, r3);
    }

    private void upgrade(SQLiteDatabase r8, int r9, int r10) {
        List<Migration> r7 = INCREMENTAL_MIGRATIONS;
        if (r10 > r7.size()) goto L7;
    L4:
        if (r9 >= r10) goto L6;
        INCREMENTAL_MIGRATIONS.get(r9).upgrade(r8);
        r9 = r9 + 1;
        goto L4
    L6:
        return;
    L7:
        AppInfo$$ExternalSyntheticBUOutline0.m("Migration from ", r9, " to ", r10, " was requested, but cannot be performed. Only ", r7.size(), " migrations are provided");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase r3) {
        this.configured = true;
        r3.rawQuery("PRAGMA busy_timeout=0;", new String[0]).close();
        r3.setForeignKeyConstraintsEnabled(true);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase r2) {
        onCreate(r2, this.schemaVersion);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase r1, int r2, int r3) {
        r1.execSQL("DROP TABLE events");
        r1.execSQL("DROP TABLE event_metadata");
        r1.execSQL("DROP TABLE transport_contexts");
        r1.execSQL("DROP TABLE IF EXISTS event_payloads");
        r1.execSQL("DROP TABLE IF EXISTS log_event_dropped");
        r1.execSQL("DROP TABLE IF EXISTS global_log_event_state");
        onCreate(r1, r3);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase r1) {
        ensureConfigured(r1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase r1, int r2, int r3) {
        ensureConfigured(r1);
        upgrade(r1, r2, r3);
    }
}
