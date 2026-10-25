package com.exteragram.messenger.api.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/api/db/MigrationsKt$MIGRATION_2_3$1", "Landroidx/room/migration/Migration;", "migrate", _UrlKt.FRAGMENT_ENCODE_SET, "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MigrationsKt$MIGRATION_2_3$1 extends Migration {
    public MigrationsKt$MIGRATION_2_3$1() {
        super(2, 3);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase r1) {
        r1.execSQL("CREATE TABLE IF NOT EXISTS `AddedRegDateDTO` (`userId` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
    }
}
