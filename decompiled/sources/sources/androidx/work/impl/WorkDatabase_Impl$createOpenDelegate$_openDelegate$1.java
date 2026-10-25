package androidx.work.impl;

import androidx.room.RoomOpenDelegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"androidx/work/impl/WorkDatabase_Impl$createOpenDelegate$_openDelegate$1", "Landroidx/room/RoomOpenDelegate;", "createAllTables", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "Landroidx/sqlite/SQLiteConnection;", "dropAllTables", "onCreate", "onOpen", "onPreMigrate", "onPostMigrate", "onValidateSchema", "Landroidx/room/RoomOpenDelegate$ValidationResult;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WorkDatabase_Impl$createOpenDelegate$_openDelegate$1 extends RoomOpenDelegate {
    final /* synthetic */ WorkDatabase_Impl this$0;

    public WorkDatabase_Impl$createOpenDelegate$_openDelegate$1(WorkDatabase_Impl r3) {
        this.this$0 = r3;
        super(24, "08b926448d86528e697981ddd30459f7", "149fd8ad55885d3fe3549a37a0163243");
    }

    @Override // androidx.room.RoomOpenDelegate
    public void createAllTables(SQLiteConnection r1) {
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        SQLite.execSQL(r1, "CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
        SQLite.execSQL(r1, "CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT NOT NULL, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `last_enqueue_time` INTEGER NOT NULL DEFAULT -1, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `period_count` INTEGER NOT NULL DEFAULT 0, `generation` INTEGER NOT NULL DEFAULT 0, `next_schedule_time_override` INTEGER NOT NULL DEFAULT 9223372036854775807, `next_schedule_time_override_generation` INTEGER NOT NULL DEFAULT 0, `stop_reason` INTEGER NOT NULL DEFAULT -256, `trace_tag` TEXT, `backoff_on_system_interruptions` INTEGER, `required_network_type` INTEGER NOT NULL, `required_network_request` BLOB NOT NULL DEFAULT x'', `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(r1, "CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
        SQLite.execSQL(r1, "CREATE INDEX IF NOT EXISTS `index_WorkSpec_last_enqueue_time` ON `WorkSpec` (`last_enqueue_time`)");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        SQLite.execSQL(r1, "CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `generation` INTEGER NOT NULL DEFAULT 0, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`, `generation`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        SQLite.execSQL(r1, "CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(r1, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '08b926448d86528e697981ddd30459f7')");
    }

    @Override // androidx.room.RoomOpenDelegate
    public void dropAllTables(SQLiteConnection r1) {
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `Dependency`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `WorkSpec`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `WorkTag`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `SystemIdInfo`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `WorkName`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `WorkProgress`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `Preference`");
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onCreate(SQLiteConnection r1) {
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onOpen(SQLiteConnection r2) {
        SQLite.execSQL(r2, "PRAGMA foreign_keys = ON");
        WorkDatabase_Impl.access$internalInitInvalidationTracker(this.this$0, r2);
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onPostMigrate(SQLiteConnection r1) {
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onPreMigrate(SQLiteConnection r1) {
        DBUtil.dropFtsSyncTriggers(r1);
    }

    @Override // androidx.room.RoomOpenDelegate
    public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection r24) {
        LinkedHashMap r1 = new LinkedHashMap();
        r1.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 1, null, 1));
        r1.put("prerequisite_id", new TableInfo.Column("prerequisite_id", "TEXT", true, 2, null, 1));
        LinkedHashSet r4 = new LinkedHashSet();
        r4.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("id")));
        r4.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", CollectionsKt.listOf("prerequisite_id"), CollectionsKt.listOf("id")));
        LinkedHashSet r5 = new LinkedHashSet();
        r5.add(new TableInfo.Index("index_Dependency_work_spec_id", false, CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("ASC")));
        r5.add(new TableInfo.Index("index_Dependency_prerequisite_id", false, CollectionsKt.listOf("prerequisite_id"), CollectionsKt.listOf("ASC")));
        TableInfo r2 = new TableInfo("Dependency", r1, r4, r5);
        TableInfo.Companion r12 = TableInfo.Companion;
        TableInfo r42 = r12.read(r24, "Dependency");
        if (r2.equals(r42) == false) goto L5;
        LinkedHashMap r22 = new LinkedHashMap();
        r22.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
        r22.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, 1));
        r22.put("worker_class_name", new TableInfo.Column("worker_class_name", "TEXT", true, 0, null, 1));
        r22.put("input_merger_class_name", new TableInfo.Column("input_merger_class_name", "TEXT", true, 0, null, 1));
        r22.put(PluginsConstants.Settings.TYPE_INPUT, new TableInfo.Column(PluginsConstants.Settings.TYPE_INPUT, "BLOB", true, 0, null, 1));
        r22.put("output", new TableInfo.Column("output", "BLOB", true, 0, null, 1));
        r22.put("initial_delay", new TableInfo.Column("initial_delay", "INTEGER", true, 0, null, 1));
        r22.put("interval_duration", new TableInfo.Column("interval_duration", "INTEGER", true, 0, null, 1));
        r22.put("flex_duration", new TableInfo.Column("flex_duration", "INTEGER", true, 0, null, 1));
        r22.put("run_attempt_count", new TableInfo.Column("run_attempt_count", "INTEGER", true, 0, null, 1));
        r22.put("backoff_policy", new TableInfo.Column("backoff_policy", "INTEGER", true, 0, null, 1));
        r22.put("backoff_delay_duration", new TableInfo.Column("backoff_delay_duration", "INTEGER", true, 0, null, 1));
        r22.put("last_enqueue_time", new TableInfo.Column("last_enqueue_time", "INTEGER", true, 0, "-1", 1));
        r22.put("minimum_retention_duration", new TableInfo.Column("minimum_retention_duration", "INTEGER", true, 0, null, 1));
        r22.put("schedule_requested_at", new TableInfo.Column("schedule_requested_at", "INTEGER", true, 0, null, 1));
        r22.put("run_in_foreground", new TableInfo.Column("run_in_foreground", "INTEGER", true, 0, null, 1));
        r22.put("out_of_quota_policy", new TableInfo.Column("out_of_quota_policy", "INTEGER", true, 0, null, 1));
        r22.put("period_count", new TableInfo.Column("period_count", "INTEGER", true, 0, MVEL.VERSION_SUB, 1));
        r22.put("generation", new TableInfo.Column("generation", "INTEGER", true, 0, MVEL.VERSION_SUB, 1));
        r22.put("next_schedule_time_override", new TableInfo.Column("next_schedule_time_override", "INTEGER", true, 0, "9223372036854775807", 1));
        r22.put("next_schedule_time_override_generation", new TableInfo.Column("next_schedule_time_override_generation", "INTEGER", true, 0, MVEL.VERSION_SUB, 1));
        r22.put("stop_reason", new TableInfo.Column("stop_reason", "INTEGER", true, 0, "-256", 1));
        r22.put("trace_tag", new TableInfo.Column("trace_tag", "TEXT", false, 0, null, 1));
        r22.put("backoff_on_system_interruptions", new TableInfo.Column("backoff_on_system_interruptions", "INTEGER", false, 0, null, 1));
        r22.put("required_network_type", new TableInfo.Column("required_network_type", "INTEGER", true, 0, null, 1));
        r22.put("required_network_request", new TableInfo.Column("required_network_request", "BLOB", true, 0, "x''", 1));
        r22.put("requires_charging", new TableInfo.Column("requires_charging", "INTEGER", true, 0, null, 1));
        r22.put("requires_device_idle", new TableInfo.Column("requires_device_idle", "INTEGER", true, 0, null, 1));
        r22.put("requires_battery_not_low", new TableInfo.Column("requires_battery_not_low", "INTEGER", true, 0, null, 1));
        r22.put("requires_storage_not_low", new TableInfo.Column("requires_storage_not_low", "INTEGER", true, 0, null, 1));
        r22.put("trigger_content_update_delay", new TableInfo.Column("trigger_content_update_delay", "INTEGER", true, 0, null, 1));
        r22.put("trigger_max_content_delay", new TableInfo.Column("trigger_max_content_delay", "INTEGER", true, 0, null, 1));
        r22.put("content_uri_triggers", new TableInfo.Column("content_uri_triggers", "BLOB", true, 0, null, 1));
        LinkedHashSet r9 = new LinkedHashSet();
        LinkedHashSet r10 = new LinkedHashSet();
        r10.add(new TableInfo.Index("index_WorkSpec_schedule_requested_at", false, CollectionsKt.listOf("schedule_requested_at"), CollectionsKt.listOf("ASC")));
        r10.add(new TableInfo.Index("index_WorkSpec_last_enqueue_time", false, CollectionsKt.listOf("last_enqueue_time"), CollectionsKt.listOf("ASC")));
        TableInfo r43 = new TableInfo("WorkSpec", r22, r9, r10);
        TableInfo r23 = r12.read(r24, "WorkSpec");
        if (r43.equals(r23) == false) goto L9;
        LinkedHashMap r25 = new LinkedHashMap();
        r25.put("tag", new TableInfo.Column("tag", "TEXT", true, 1, null, 1));
        r25.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 2, null, 1));
        LinkedHashSet r44 = new LinkedHashSet();
        r44.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("id")));
        LinkedHashSet r52 = new LinkedHashSet();
        r52.add(new TableInfo.Index("index_WorkTag_work_spec_id", false, CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("ASC")));
        TableInfo r92 = new TableInfo("WorkTag", r25, r44, r52);
        TableInfo r26 = r12.read(r24, "WorkTag");
        if (r92.equals(r26) == false) goto L13;
        LinkedHashMap r27 = new LinkedHashMap();
        r27.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 1, null, 1));
        r27.put("generation", new TableInfo.Column("generation", "INTEGER", true, 2, MVEL.VERSION_SUB, 1));
        r27.put("system_id", new TableInfo.Column("system_id", "INTEGER", true, 0, null, 1));
        LinkedHashSet r45 = new LinkedHashSet();
        r45.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("id")));
        TableInfo r7 = new TableInfo("SystemIdInfo", r27, r45, new LinkedHashSet());
        TableInfo r28 = r12.read(r24, "SystemIdInfo");
        if (r7.equals(r28) == false) goto L17;
        LinkedHashMap r29 = new LinkedHashMap();
        r29.put("name", new TableInfo.Column("name", "TEXT", true, 1, null, 1));
        r29.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 2, null, 1));
        LinkedHashSet r46 = new LinkedHashSet();
        r46.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("id")));
        LinkedHashSet r53 = new LinkedHashSet();
        r53.add(new TableInfo.Index("index_WorkName_work_spec_id", false, CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("ASC")));
        TableInfo r72 = new TableInfo("WorkName", r29, r46, r53);
        TableInfo r210 = r12.read(r24, "WorkName");
        if (r72.equals(r210) == false) goto L21;
        LinkedHashMap r211 = new LinkedHashMap();
        r211.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 1, null, 1));
        r211.put("progress", new TableInfo.Column("progress", "BLOB", true, 0, null, 1));
        LinkedHashSet r47 = new LinkedHashSet();
        r47.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", CollectionsKt.listOf("work_spec_id"), CollectionsKt.listOf("id")));
        TableInfo r54 = new TableInfo("WorkProgress", r211, r47, new LinkedHashSet());
        TableInfo r212 = r12.read(r24, "WorkProgress");
        if (r54.equals(r212) == false) goto L25;
        LinkedHashMap r213 = new LinkedHashMap();
        r213.put(PluginsConstants.Settings.KEY, new TableInfo.Column(PluginsConstants.Settings.KEY, "TEXT", true, 1, null, 1));
        r213.put("long_value", new TableInfo.Column("long_value", "INTEGER", false, 0, null, 1));
        TableInfo r55 = new TableInfo("Preference", r213, new LinkedHashSet(), new LinkedHashSet());
        TableInfo r0 = r12.read(r24, "Preference");
        if (r55.equals(r0) == true) goto L31;
        return new RoomOpenDelegate.ValidationResult(false, "Preference(androidx.work.impl.model.Preference).\n Expected:\n" + r55 + "\n Found:\n" + r0);
    L31:
        return new RoomOpenDelegate.ValidationResult(true, null);
    L25:
        return new RoomOpenDelegate.ValidationResult(false, "WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n" + r54 + "\n Found:\n" + r212);
    L21:
        return new RoomOpenDelegate.ValidationResult(false, "WorkName(androidx.work.impl.model.WorkName).\n Expected:\n" + r72 + "\n Found:\n" + r210);
    L17:
        return new RoomOpenDelegate.ValidationResult(false, "SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n" + r7 + "\n Found:\n" + r28);
    L13:
        return new RoomOpenDelegate.ValidationResult(false, "WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n" + r92 + "\n Found:\n" + r26);
    L9:
        return new RoomOpenDelegate.ValidationResult(false, "WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n" + r43 + "\n Found:\n" + r23);
    L5:
        return new RoomOpenDelegate.ValidationResult(false, "Dependency(androidx.work.impl.model.Dependency).\n Expected:\n" + r2 + "\n Found:\n" + r42);
    }
}
