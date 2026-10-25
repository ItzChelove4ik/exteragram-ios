package com.exteragram.messenger.api.db;

import androidx.room.migration.Migration;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003¨\u0006\n"}, d2 = {"MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "MIGRATION_2_3", "getMIGRATION_2_3", "MIGRATION_3_4", "getMIGRATION_3_4", "MIGRATION_4_5", "getMIGRATION_4_5", "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MigrationsKt {
    private static final Migration MIGRATION_1_2 = null;
    private static final Migration MIGRATION_2_3 = null;
    private static final Migration MIGRATION_3_4 = null;
    private static final Migration MIGRATION_4_5 = null;

    static {
        MIGRATION_1_2 = new MigrationsKt$MIGRATION_1_2$1();
        MIGRATION_2_3 = new MigrationsKt$MIGRATION_2_3$1();
        MIGRATION_3_4 = new MigrationsKt$MIGRATION_3_4$1();
        MIGRATION_4_5 = new MigrationsKt$MIGRATION_4_5$1();
    }

    public static final Migration getMIGRATION_1_2() {
        return MIGRATION_1_2;
    }

    public static final Migration getMIGRATION_2_3() {
        return MIGRATION_2_3;
    }

    public static final Migration getMIGRATION_3_4() {
        return MIGRATION_3_4;
    }

    public static final Migration getMIGRATION_4_5() {
        return MIGRATION_4_5;
    }
}
