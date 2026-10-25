package androidx.room;

import androidx.room.RoomDatabase;
import androidx.room.RoomOpenDelegate;
import androidx.room.concurrent.ExclusiveLock;
import androidx.room.migration.Migration;
import androidx.room.util.MigrationUtil;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.SQLiteStatement;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u0000 82\u00020\u0001:\u000298B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\bJ\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\f\u0010\bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\bJ\u0017\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0011\u0010\bJ\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0012\u0010\bJ\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\bJ\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0014\u0010\u000fJ\u0017\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0015\u0010\bJ\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0016\u0010\bJ\u0017\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0017\u0010\bJ\u0017\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0010¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0004¢\u0006\u0004\b\u001d\u0010\bJ'\u0010!\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0004¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0004¢\u0006\u0004\b#\u0010\bJ\u0013\u0010%\u001a\u00020\u001e*\u00020$H\u0004¢\u0006\u0004\b%\u0010&J\u0013\u0010'\u001a\u00020\u001e*\u00020$H\u0004¢\u0006\u0004\b'\u0010&R\u0016\u0010(\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010.\u001a\u00020+8$X¤\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0014\u00102\u001a\u00020/8$X¤\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u001a\u00107\u001a\b\u0012\u0004\u0012\u000204038$X¤\u0004¢\u0006\u0006\u001a\u0004\b5\u00106¨\u0006:"}, d2 = {"Landroidx/room/BaseRoomConnectionManager;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/sqlite/SQLiteConnection;", "connection", _UrlKt.FRAGMENT_ENCODE_SET, "configureDatabase", "(Landroidx/sqlite/SQLiteConnection;)V", "configurationConnection", "configureJournalMode", "configureSynchronousFlag", "configureBusyTimeout", _UrlKt.FRAGMENT_ENCODE_SET, "hasEmptySchema", "(Landroidx/sqlite/SQLiteConnection;)Z", "updateIdentity", "createMasterTableIfNotExists", "dropAllTables", "checkIdentity", "hasRoomMasterTable", "invokeCreateCallback", "invokeDestructiveMigrationCallback", "invokeOpenCallback", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", "resolveFileName$room_runtime", "(Ljava/lang/String;)Ljava/lang/String;", "resolveFileName", "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "oldVersion", "newVersion", "onMigrate", "(Landroidx/sqlite/SQLiteConnection;II)V", "onOpen", "Landroidx/room/RoomDatabase$JournalMode;", "getMaxNumberOfReaders", "(Landroidx/room/RoomDatabase$JournalMode;)I", "getMaxNumberOfWriters", "isConfigured", "Z", "isInitializing", "Landroidx/room/DatabaseConfiguration;", "getConfiguration", "()Landroidx/room/DatabaseConfiguration;", "configuration", "Landroidx/room/RoomOpenDelegate;", "getOpenDelegate", "()Landroidx/room/RoomOpenDelegate;", "openDelegate", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/RoomDatabase$Callback;", "getCallbacks", "()Ljava/util/List;", "callbacks", "Companion", "DriverWrapper", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRoomConnectionManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoomConnectionManager.kt\nandroidx/room/BaseRoomConnectionManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,365:1\n1#2:366\n1869#3,2:367\n1869#3,2:369\n1869#3,2:371\n1869#3,2:373\n1869#3,2:375\n*S KotlinDebug\n*F\n+ 1 RoomConnectionManager.kt\nandroidx/room/BaseRoomConnectionManager\n*L\n212#1:367,2\n256#1:369,2\n345#1:371,2\n349#1:373,2\n353#1:375,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class BaseRoomConnectionManager {
    public static final Companion Companion = null;
    private boolean isConfigured;
    private boolean isInitializing;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/room/BaseRoomConnectionManager$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "BUSY_TIMEOUT_MS", _UrlKt.FRAGMENT_ENCODE_SET, "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0084\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/room/BaseRoomConnectionManager$DriverWrapper;", "Landroidx/sqlite/SQLiteDriver;", "actual", "<init>", "(Landroidx/room/BaseRoomConnectionManager;Landroidx/sqlite/SQLiteDriver;)V", "open", "Landroidx/sqlite/SQLiteConnection;", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "openLocked", "filename", "hasConnectionPool", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public final class DriverWrapper implements SQLiteDriver {
        private final SQLiteDriver actual;
        final /* synthetic */ BaseRoomConnectionManager this$0;

        public static SQLiteConnection $r8$lambda$npawJpzGW_LQCHyPkGm5j4d35mo(BaseRoomConnectionManager r1, DriverWrapper r2, String r3) {
            if (BaseRoomConnectionManager.access$isInitializing$p(r1) == true) goto L15;
            SQLiteConnection r22 = r2.actual.open(r3);
            if (BaseRoomConnectionManager.access$isConfigured$p(r1) == false) goto L17;
            BaseRoomConnectionManager.access$configurationConnection(r1, r22);
            return r22;
        L17:
            BaseRoomConnectionManager.access$setInitializing$p(r1, true);     // Catch: Throwable -> L10
            BaseRoomConnectionManager.access$configureDatabase(r1, r22);     // Catch: Throwable -> L10
            BaseRoomConnectionManager.access$setInitializing$p(r1, false);
            return r22;
        L10:
            th = move-exception;
            BaseRoomConnectionManager.access$setInitializing$p(r1, false);
            throw th;
        L15:
            Segment$$ExternalSyntheticBUOutline1.m("Recursive database initialization detected. Did you try to use the database instance during initialization? Maybe in one of the callbacks?");
            return null;
        }

        public DriverWrapper(BaseRoomConnectionManager r1, SQLiteDriver r2) {
            this.this$0 = r1;
            this.actual = r2;
        }

        private final SQLiteConnection openLocked(String r4) {
            if (BaseRoomConnectionManager.access$isConfigured$p(this.this$0) == false) goto L5;
        L9:
            boolean r1 = false;
        L11:
            return (SQLiteConnection) new ExclusiveLock(r4, r1).withLock(new BaseRoomConnectionManager$DriverWrapper$$ExternalSyntheticLambda0(this.this$0, this, r4), new BaseRoomConnectionManager$DriverWrapper$openLocked$2(r4));
        L5:
            if (BaseRoomConnectionManager.access$isInitializing$p(this.this$0) == true) goto L9;
            if (Intrinsics.areEqual(r4, ":memory:") == true) goto L9;
            r1 = true;
            goto L11
        }

        @Override // androidx.sqlite.SQLiteDriver
        @JvmName(name = "hasConnectionPool")
        public boolean hasConnectionPool() {
            return this.actual.hasConnectionPool();
        }

        @Override // androidx.sqlite.SQLiteDriver
        public SQLiteConnection open(String r2) {
            return openLocked(this.this$0.resolveFileName$room_runtime(r2));
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[RoomDatabase.JournalMode.values().length];
            r0[RoomDatabase.JournalMode.TRUNCATE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L9:
            r0[RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L5:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public BaseRoomConnectionManager() {
    }

    public static final /* synthetic */ void access$configurationConnection(BaseRoomConnectionManager r0, SQLiteConnection r1) {
        r0.configurationConnection(r1);
    }

    public static final /* synthetic */ void access$configureDatabase(BaseRoomConnectionManager r0, SQLiteConnection r1) {
        r0.configureDatabase(r1);
    }

    public static final /* synthetic */ boolean access$isConfigured$p(BaseRoomConnectionManager r0) {
        return r0.isConfigured;
    }

    public static final /* synthetic */ boolean access$isInitializing$p(BaseRoomConnectionManager r0) {
        return r0.isInitializing;
    }

    public static final /* synthetic */ void access$setInitializing$p(BaseRoomConnectionManager r0, boolean r1) {
        r0.isInitializing = r1;
    }

    private final void checkIdentity(SQLiteConnection r4) {
        if (hasRoomMasterTable(r4) == false) goto L24;
        SQLiteStatement r42 = r4.prepare("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1");
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        AutoCloseableKt.closeFinally(r42, th);
        throw th;
    L6:
        if (r42.step() == false) goto L11;
        String r0 = r42.getText(0);     // Catch: Throwable -> L9
    L12:
        AutoCloseableKt.closeFinally(r42, null);
        if (Intrinsics.areEqual(getOpenDelegate().getIdentityHash(), r0) == false) goto L15;
        return;
    L15:
        if (Intrinsics.areEqual(getOpenDelegate().getLegacyIdentityHash(), r0) == false) goto L18;
        return;
    L18:
        throw new IllegalStateException(("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: " + getOpenDelegate().getIdentityHash() + ", found: " + r0).toString());
    L11:
        r0 = null;
        goto L12
    L24:
        SQLite.execSQL(r4, "BEGIN EXCLUSIVE TRANSACTION");
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L28
        RoomOpenDelegate.ValidationResult r12 = getOpenDelegate().onValidateSchema(r4);     // Catch: Throwable -> L28
        if (r12.isValid == false) goto L31;
        getOpenDelegate().onPostMigrate(r4);     // Catch: Throwable -> L28
        updateIdentity(r4);     // Catch: Throwable -> L28
        Object r3 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L28
    L34:
        if (Result.m2025isSuccessimpl(r3) == false) goto L36;
        Unit r02 = (Unit) r3;
        SQLite.execSQL(r4, "END TRANSACTION");
    L36:
        Throwable r03 = Result.m2021exceptionOrNullimpl(r3);
        if (r03 != null) goto L40;
        Result.m2017boximpl(r3);
        return;
    L40:
        SQLite.execSQL(r4, "ROLLBACK TRANSACTION");
        throw r03;
    L31:
        throw new IllegalStateException(("Pre-packaged database has an invalid schema: " + r12.expectedFoundMsg).toString());     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        Result.Companion r04 = Result.Companion;
        r3 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L34
    }

    private final void configurationConnection(SQLiteConnection r1) {
        configureBusyTimeout(r1);
        configureSynchronousFlag(r1);
        getOpenDelegate().onOpen(r1);
    }

    private final void configureBusyTimeout(SQLiteConnection r5) {
        SQLiteStatement r4 = r5.prepare("PRAGMA busy_timeout");
        r4.step();     // Catch: Throwable -> L8
        long r0 = r4.getLong(0);     // Catch: Throwable -> L8
        AutoCloseableKt.closeFinally(r4, null);
        if (r0 >= 3000) goto L17;
        SQLite.execSQL(r5, "PRAGMA busy_timeout = 3000");
        return;
    L17:
        return;
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        AutoCloseableKt.closeFinally(r4, th);
        throw th;
    }

    private final void configureDatabase(SQLiteConnection r5) {
        configureBusyTimeout(r5);
        configureJournalMode(r5);
        configureSynchronousFlag(r5);
        SQLiteStatement r1 = r5.prepare("PRAGMA user_version");
        r1.step();     // Catch: Throwable -> L26
        int r2 = (int) r1.getLong(0);
        AutoCloseableKt.closeFinally(r1, null);
        if (r2 == getOpenDelegate().getVersion()) goto L24;
        SQLite.execSQL(r5, "BEGIN EXCLUSIVE TRANSACTION");
        Result.Companion r12 = Result.Companion;     // Catch: Throwable -> L10
        if (r2 != 0) goto L12;
        onCreate(r5);     // Catch: Throwable -> L10
    L13:
        SQLite.execSQL(r5, "PRAGMA user_version = " + getOpenDelegate().getVersion());     // Catch: Throwable -> L10
        Object r0 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L10
    L17:
        if (Result.m2025isSuccessimpl(r0) == false) goto L19;
        Unit r13 = (Unit) r0;
        SQLite.execSQL(r5, "END TRANSACTION");
    L19:
        Throwable r02 = Result.m2021exceptionOrNullimpl(r0);
        if (r02 == null) goto L24;
        SQLite.execSQL(r5, "ROLLBACK TRANSACTION");
        throw r02;
    L12:
        onMigrate(r5, r2, getOpenDelegate().getVersion());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        Result.Companion r14 = Result.Companion;
        r0 = Result.m2018constructorimpl(ResultKt.createFailure(th));
    L24:
        onOpen(r5);
        return;
    L26:
        th = move-exception;
        throw th;     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    }

    private final void configureJournalMode(SQLiteConnection r2) {
        if (getConfiguration().journalMode != RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING) goto L6;
        SQLite.execSQL(r2, "PRAGMA journal_mode = WAL");
        return;
    L6:
        SQLite.execSQL(r2, "PRAGMA journal_mode = TRUNCATE");
    }

    private final void configureSynchronousFlag(SQLiteConnection r2) {
        if (getConfiguration().journalMode != RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING) goto L6;
        SQLite.execSQL(r2, "PRAGMA synchronous = NORMAL");
        return;
    L6:
        SQLite.execSQL(r2, "PRAGMA synchronous = FULL");
    }

    private final void createMasterTableIfNotExists(SQLiteConnection r1) {
        SQLite.execSQL(r1, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private final void dropAllTables(SQLiteConnection r7) {
        if (getConfiguration().allowDestructiveMigrationForAllTables == false) goto L29;
        SQLiteStatement r6 = r7.prepare("SELECT name, type FROM sqlite_master WHERE type = 'table' OR type = 'view'");
        List r0 = CollectionsKt.createListBuilder();     // Catch: Throwable -> L14
    L7:
        if (r6.step() == false) goto L16;
        String r3 = r6.getText(0);     // Catch: Throwable -> L14
        if (StringsKt.startsWith$default(r3, "sqlite_", false, 2, null) == true) goto L7;
        if (Intrinsics.areEqual(r3, "android_metadata") == true) goto L7;
        r0.add(TuplesKt.to(r3, Boolean.valueOf(Intrinsics.areEqual(r6.getText(1), PluginsConstants.Settings.VIEW))));     // Catch: Throwable -> L14
        goto L7
    L16:
        List r02 = CollectionsKt.build(r0);     // Catch: Throwable -> L14
        AutoCloseableKt.closeFinally(r6, null);
        Iterator r62 = r02.iterator();
    L19:
        if (r62.hasNext() == false) goto L24;
        Pair r03 = (Pair) r62.next();
        String r1 = (String) r03.component1();
        if (((Boolean) r03.component2()).booleanValue() == true) goto L22;
        SQLite.execSQL(r7, "DROP TABLE IF EXISTS `" + r1 + '`');
        goto L19
    L22:
        SQLite.execSQL(r7, "DROP VIEW IF EXISTS `" + r1 + '`');
        goto L19
    L24:
        return;
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        AutoCloseableKt.closeFinally(r6, th);
        throw th;
    L29:
        getOpenDelegate().dropAllTables(r7);
    }

    private final boolean hasEmptySchema(SQLiteConnection r6) {
        SQLiteStatement r5 = r6.prepare("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        boolean r0 = false;
        if (r5.step() == false) goto L11;
        if (r5.getLong(0) != 0) goto L11;
        r0 = true;
    L11:
        AutoCloseableKt.closeFinally(r5, null);
        return r0;
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        AutoCloseableKt.closeFinally(r5, th);
        throw th;
    }

    private final boolean hasRoomMasterTable(SQLiteConnection r6) {
        SQLiteStatement r5 = r6.prepare("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name = 'room_master_table'");
        boolean r0 = false;
        if (r5.step() == false) goto L11;
        if (r5.getLong(0) == 0) goto L11;
        r0 = true;
    L11:
        AutoCloseableKt.closeFinally(r5, null);
        return r0;
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        AutoCloseableKt.closeFinally(r5, th);
        throw th;
    }

    private final void invokeCreateCallback(SQLiteConnection r2) {
        Iterator<T> r1 = getCallbacks().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RoomDatabase.Callback) r1.next()).onCreate(r2);
        goto L4
    }

    private final void invokeDestructiveMigrationCallback(SQLiteConnection r2) {
        Iterator<T> r1 = getCallbacks().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RoomDatabase.Callback) r1.next()).onDestructiveMigration(r2);
        goto L4
    }

    private final void invokeOpenCallback(SQLiteConnection r2) {
        Iterator<T> r1 = getCallbacks().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RoomDatabase.Callback) r1.next()).onOpen(r2);
        goto L4
    }

    private final void updateIdentity(SQLiteConnection r1) {
        createMasterTableIfNotExists(r1);
        SQLite.execSQL(r1, RoomMasterTable.createInsertQuery(getOpenDelegate().getIdentityHash()));
    }

    public abstract List<RoomDatabase.Callback> getCallbacks();

    public abstract DatabaseConfiguration getConfiguration();

    public final int getMaxNumberOfReaders(RoomDatabase.JournalMode r2) {
        int r1 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r1 != 1) goto L5;
        return 1;
    L5:
        if (r1 != 2) goto L8;
        return 4;
    L8:
        BaseRoomConnectionManager$$ExternalSyntheticBUOutline0.m("Can't get max number of reader for journal mode '", r2, 39);
        return 0;
    }

    public final int getMaxNumberOfWriters(RoomDatabase.JournalMode r3) {
        int r2 = WhenMappings.$EnumSwitchMapping$0[r3.ordinal()];
        if (r2 != 1) goto L5;
    L9:
        return 1;
    L5:
        if (r2 == 2) goto L9;
        BaseRoomConnectionManager$$ExternalSyntheticBUOutline0.m("Can't get max number of writers for journal mode '", r3, 39);
        return 0;
    }

    public abstract RoomOpenDelegate getOpenDelegate();

    public final void onCreate(SQLiteConnection r3) {
        boolean r0 = hasEmptySchema(r3);
        getOpenDelegate().createAllTables(r3);
        if (r0 == true) goto L9;
        RoomOpenDelegate.ValidationResult r02 = getOpenDelegate().onValidateSchema(r3);
        if (r02.isValid == true) goto L9;
        throw new IllegalStateException(("Pre-packaged database has an invalid schema: " + r02.expectedFoundMsg).toString());
    L9:
        updateIdentity(r3);
        getOpenDelegate().onCreate(r3);
        invokeCreateCallback(r3);
    }

    public final void onMigrate(SQLiteConnection r2, int r3, int r4) {
        List<Migration> r0 = MigrationUtil.findMigrationPath(getConfiguration().migrationContainer, r3, r4);
        if (r0 == null) goto L15;
        getOpenDelegate().onPreMigrate(r2);
        Iterator<T> r32 = r0.iterator();
    L6:
        if (r32.hasNext() == false) goto L8;
        ((Migration) r32.next()).migrate(r2);
        goto L6
    L8:
        RoomOpenDelegate.ValidationResult r33 = getOpenDelegate().onValidateSchema(r2);
        if (r33.isValid == false) goto L13;
        getOpenDelegate().onPostMigrate(r2);
        updateIdentity(r2);
        return;
    L13:
        throw new IllegalStateException(("Migration didn't properly handle: " + r33.expectedFoundMsg).toString());
    L15:
        if (MigrationUtil.isMigrationRequired(getConfiguration(), r3, r4) == true) goto L19;
        dropAllTables(r2);
        invokeDestructiveMigrationCallback(r2);
        getOpenDelegate().createAllTables(r2);
        return;
    L19:
        throw new IllegalStateException(("A migration from " + r3 + " to " + r4 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* functions.").toString());
    }

    public final void onOpen(SQLiteConnection r2) {
        checkIdentity(r2);
        getOpenDelegate().onOpen(r2);
        invokeOpenCallback(r2);
        this.isConfigured = true;
    }

    public abstract String resolveFileName$room_runtime(String r1);
}
