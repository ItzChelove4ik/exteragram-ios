package com.exteragram.messenger.api.db;

import androidx.room.RoomOpenDelegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"com/exteragram/messenger/api/db/ExteraDatabase_Impl$createOpenDelegate$_openDelegate$1", "Landroidx/room/RoomOpenDelegate;", "createAllTables", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "Landroidx/sqlite/SQLiteConnection;", "dropAllTables", "onCreate", "onOpen", "onPreMigrate", "onPostMigrate", "onValidateSchema", "Landroidx/room/RoomOpenDelegate$ValidationResult;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExteraDatabase_Impl$createOpenDelegate$_openDelegate$1 extends RoomOpenDelegate {
    final /* synthetic */ ExteraDatabase_Impl this$0;

    public ExteraDatabase_Impl$createOpenDelegate$_openDelegate$1(ExteraDatabase_Impl r3) {
        this.this$0 = r3;
        super(5, "0e4e0efe20ecd6ef10fbac10ebbb089b", "cb8806bf8ba241f006e47a1ddab85bcf");
    }

    @Override // androidx.room.RoomOpenDelegate
    public void createAllTables(SQLiteConnection r1) {
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `ProfileDTO` (`id` INTEGER NOT NULL, `type` TEXT NOT NULL, `status` TEXT NOT NULL, `badge` TEXT, `nowPlaying` TEXT, `deleted` INTEGER, `canChangeBadge` INTEGER, PRIMARY KEY(`id`))");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `AddedRegDateDTO` (`userId` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS `BoostySubscriberDTO` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `totalAmountRub` TEXT NOT NULL, `totalAmountUsd` TEXT NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(r1, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0e4e0efe20ecd6ef10fbac10ebbb089b')");
    }

    @Override // androidx.room.RoomOpenDelegate
    public void dropAllTables(SQLiteConnection r1) {
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `ProfileDTO`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `AddedRegDateDTO`");
        SQLite.execSQL(r1, "DROP TABLE IF EXISTS `BoostySubscriberDTO`");
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onCreate(SQLiteConnection r1) {
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onOpen(SQLiteConnection r1) {
        ExteraDatabase_Impl.access$internalInitInvalidationTracker(this.this$0, r1);
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onPostMigrate(SQLiteConnection r1) {
    }

    @Override // androidx.room.RoomOpenDelegate
    public void onPreMigrate(SQLiteConnection r1) {
        DBUtil.dropFtsSyncTriggers(r1);
    }

    @Override // androidx.room.RoomOpenDelegate
    public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection r19) {
        LinkedHashMap r1 = new LinkedHashMap();
        r1.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
        r1.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, 1));
        r1.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, 1));
        r1.put("badge", new TableInfo.Column("badge", "TEXT", false, 0, null, 1));
        r1.put("nowPlaying", new TableInfo.Column("nowPlaying", "TEXT", false, 0, null, 1));
        r1.put("deleted", new TableInfo.Column("deleted", "INTEGER", false, 0, null, 1));
        r1.put("canChangeBadge", new TableInfo.Column("canChangeBadge", "INTEGER", false, 0, null, 1));
        TableInfo r5 = new TableInfo("ProfileDTO", r1, new LinkedHashSet(), new LinkedHashSet());
        TableInfo.Companion r12 = TableInfo.Companion;
        TableInfo r2 = r12.read(r19, "ProfileDTO");
        if (r5.equals(r2) == false) goto L5;
        LinkedHashMap r22 = new LinkedHashMap();
        r22.put("userId", new TableInfo.Column("userId", "INTEGER", true, 1, null, 1));
        TableInfo r8 = new TableInfo("AddedRegDateDTO", r22, new LinkedHashSet(), new LinkedHashSet());
        TableInfo r23 = r12.read(r19, "AddedRegDateDTO");
        if (r8.equals(r23) == false) goto L9;
        LinkedHashMap r24 = new LinkedHashMap();
        r24.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
        r24.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
        r24.put("totalAmountRub", new TableInfo.Column("totalAmountRub", "TEXT", true, 0, null, 1));
        r24.put("totalAmountUsd", new TableInfo.Column("totalAmountUsd", "TEXT", true, 0, null, 1));
        TableInfo r52 = new TableInfo("BoostySubscriberDTO", r24, new LinkedHashSet(), new LinkedHashSet());
        TableInfo r0 = r12.read(r19, "BoostySubscriberDTO");
        if (r52.equals(r0) == true) goto L15;
        return new RoomOpenDelegate.ValidationResult(false, "BoostySubscriberDTO(com.exteragram.messenger.api.dto.BoostySubscriberDTO).\n Expected:\n" + r52 + "\n Found:\n" + r0);
    L15:
        return new RoomOpenDelegate.ValidationResult(true, null);
    L9:
        return new RoomOpenDelegate.ValidationResult(false, "AddedRegDateDTO(com.exteragram.messenger.api.dto.AddedRegDateDTO).\n Expected:\n" + r8 + "\n Found:\n" + r23);
    L5:
        return new RoomOpenDelegate.ValidationResult(false, "ProfileDTO(com.exteragram.messenger.api.dto.ProfileDTO).\n Expected:\n" + r5 + "\n Found:\n" + r2);
    }
}
