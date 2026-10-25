package androidx.work.impl;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.work.Clock;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H&¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H&¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Landroidx/work/impl/WorkDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "Landroidx/work/impl/model/WorkSpecDao;", "workSpecDao", "()Landroidx/work/impl/model/WorkSpecDao;", "Landroidx/work/impl/model/DependencyDao;", "dependencyDao", "()Landroidx/work/impl/model/DependencyDao;", "Landroidx/work/impl/model/WorkTagDao;", "workTagDao", "()Landroidx/work/impl/model/WorkTagDao;", "Landroidx/work/impl/model/SystemIdInfoDao;", "systemIdInfoDao", "()Landroidx/work/impl/model/SystemIdInfoDao;", "Landroidx/work/impl/model/WorkNameDao;", "workNameDao", "()Landroidx/work/impl/model/WorkNameDao;", "Landroidx/work/impl/model/WorkProgressDao;", "workProgressDao", "()Landroidx/work/impl/model/WorkProgressDao;", "Landroidx/work/impl/model/PreferenceDao;", "preferenceDao", "()Landroidx/work/impl/model/PreferenceDao;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class WorkDatabase extends RoomDatabase {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007¨\u0006\u000e"}, d2 = {"Landroidx/work/impl/WorkDatabase$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/work/impl/WorkDatabase;", "context", "Landroid/content/Context;", "queryExecutor", "Ljava/util/concurrent/Executor;", "clock", "Landroidx/work/Clock;", "useTestDatabase", _UrlKt.FRAGMENT_ENCODE_SET, "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public static SupportSQLiteOpenHelper $r8$lambda$d9uKx9JAMiDvrmcMenix4D379lU(Context r1, SupportSQLiteOpenHelper.Configuration r2) {
            SupportSQLiteOpenHelper.Configuration.Builder r12 = SupportSQLiteOpenHelper.Configuration.Companion.builder(r1);
            r12.name(r2.name).callback(r2.callback).noBackupDirectory(true).allowDataLossOnRecovery(true);
            return new FrameworkSQLiteOpenHelperFactory().create(r12.build());
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final WorkDatabase create(Context r3, Executor r4, Clock r5, boolean r6) {
            if (r6 == false) goto L5;
            RoomDatabase.Builder r2 = Room.inMemoryDatabaseBuilder(r3, WorkDatabase.class).allowMainThreadQueries();
        L7:
            return (WorkDatabase) r2.setQueryExecutor(r4).addCallback(new CleanupCallback(r5)).addMigrations(new Migration[]{Migration_1_2.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(r3, 2, 3)}).addMigrations(new Migration[]{Migration_3_4.INSTANCE}).addMigrations(new Migration[]{Migration_4_5.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(r3, 5, 6)}).addMigrations(new Migration[]{Migration_6_7.INSTANCE}).addMigrations(new Migration[]{Migration_7_8.INSTANCE}).addMigrations(new Migration[]{Migration_8_9.INSTANCE}).addMigrations(new Migration[]{new WorkMigration9To10(r3)}).addMigrations(new Migration[]{new RescheduleMigration(r3, 10, 11)}).addMigrations(new Migration[]{Migration_11_12.INSTANCE}).addMigrations(new Migration[]{Migration_12_13.INSTANCE}).addMigrations(new Migration[]{Migration_15_16.INSTANCE}).addMigrations(new Migration[]{Migration_16_17.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(r3, 21, 22)}).fallbackToDestructiveMigration(true).build();
        L5:
            r2 = Room.databaseBuilder(r3, WorkDatabase.class, "androidx.work.workdb").openHelperFactory(new WorkDatabase$Companion$$ExternalSyntheticLambda0(r3));
            goto L7
        }
    }

    static {
        Companion = new Companion(null);
    }

    public WorkDatabase() {
    }

    public abstract DependencyDao dependencyDao();

    public abstract PreferenceDao preferenceDao();

    public abstract SystemIdInfoDao systemIdInfoDao();

    public abstract WorkNameDao workNameDao();

    public abstract WorkProgressDao workProgressDao();

    public abstract WorkSpecDao workSpecDao();

    public abstract WorkTagDao workTagDao();
}
