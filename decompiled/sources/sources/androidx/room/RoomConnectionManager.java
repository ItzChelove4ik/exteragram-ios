package androidx.room;

import androidx.room.BaseRoomConnectionManager;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenDelegate;
import androidx.room.coroutines.ConnectionPool;
import androidx.room.coroutines.ConnectionPoolKt;
import androidx.room.coroutines.PassthroughConnectionPool;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.driver.SupportSQLiteConnection;
import androidx.sqlite.driver.SupportSQLiteDriver;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001:\u0002?@BW\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012<\u0010\u000b\u001a8\b\u0001\u0012\u0018\u0012\u0016\b\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006j\u0006\u0012\u0002\b\u0003`\n¢\u0006\u0004\b\f\u0010\rBc\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0007\u0012<\u0010\u000b\u001a8\b\u0001\u0012\u0018\u0012\u0016\b\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006j\u0006\u0012\u0002\b\u0003`\n¢\u0006\u0004\b\f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0007H\u0002¢\u0006\u0004\b\u0016\u0010\u0017JB\u0010\u001d\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00182\u0006\u0010\u001a\u001a\u00020\u00192\"\u0010\u001c\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006H\u0096@¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010#\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0010¢\u0006\u0004\b!\u0010\"J\r\u0010$\u001a\u00020\u0011¢\u0006\u0004\b$\u0010\u0013J\r\u0010%\u001a\u00020\u0019¢\u0006\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u00028\u0014X\u0094\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u001a\u0010\u0005\u001a\u00020\u00048\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u0005\u0010+\u001a\u0004\b,\u0010-R \u00100\u001a\b\u0012\u0004\u0012\u00020/0.8\u0014X\u0094\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u001a\u00105\u001a\u0002048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u001c\u00109\u001a\u0004\u0018\u00010\u000e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>¨\u0006A"}, d2 = {"Landroidx/room/RoomConnectionManager;", "Landroidx/room/BaseRoomConnectionManager;", "Landroidx/room/DatabaseConfiguration;", "config", "Landroidx/room/RoomOpenDelegate;", "openDelegate", "Lkotlin/Function2;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/coroutines/TransactionWrapper;", "transactionWrapper", "<init>", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenDelegate;Lkotlin/jvm/functions/Function2;)V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "supportOpenHelperFactory", "(Landroidx/room/DatabaseConfiguration;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", _UrlKt.FRAGMENT_ENCODE_SET, "init", "()V", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "onOpen", "installOnOpenCallback", "(Landroidx/room/DatabaseConfiguration;Lkotlin/jvm/functions/Function1;)Landroidx/room/DatabaseConfiguration;", "R", _UrlKt.FRAGMENT_ENCODE_SET, "isReadOnly", "Landroidx/room/Transactor;", "block", "useConnection", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", "resolveFileName$room_runtime", "(Ljava/lang/String;)Ljava/lang/String;", "resolveFileName", "close", "isSupportDatabaseOpen", "()Z", "configuration", "Landroidx/room/DatabaseConfiguration;", "getConfiguration", "()Landroidx/room/DatabaseConfiguration;", "Landroidx/room/RoomOpenDelegate;", "getOpenDelegate", "()Landroidx/room/RoomOpenDelegate;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/RoomDatabase$Callback;", "callbacks", "Ljava/util/List;", "getCallbacks", "()Ljava/util/List;", "Landroidx/room/coroutines/ConnectionPool;", "connectionPool", "Landroidx/room/coroutines/ConnectionPool;", "getConnectionPool$room_runtime", "()Landroidx/room/coroutines/ConnectionPool;", "supportOpenHelper", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "getSupportOpenHelper$room_runtime", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "supportDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "SupportOpenHelperCallback", "NoOpOpenDelegate", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RoomConnectionManager extends BaseRoomConnectionManager {
    private final List<RoomDatabase.Callback> callbacks;
    private final DatabaseConfiguration configuration;
    private final ConnectionPool connectionPool;
    private final RoomOpenDelegate openDelegate;
    private SupportSQLiteDatabase supportDatabase;
    private final SupportSQLiteOpenHelper supportOpenHelper;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u000f"}, d2 = {"Landroidx/room/RoomConnectionManager$NoOpOpenDelegate;", "Landroidx/room/RoomOpenDelegate;", "<init>", "()V", "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "Landroidx/sqlite/SQLiteConnection;", "onPreMigrate", "onValidateSchema", "Landroidx/room/RoomOpenDelegate$ValidationResult;", "onPostMigrate", "onOpen", "createAllTables", "dropAllTables", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class NoOpOpenDelegate extends RoomOpenDelegate {
        public NoOpOpenDelegate() {
            super(-1, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        @Override // androidx.room.RoomOpenDelegate
        public void createAllTables(SQLiteConnection r1) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public void dropAllTables(SQLiteConnection r1) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public void onCreate(SQLiteConnection r1) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public void onOpen(SQLiteConnection r1) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public void onPostMigrate(SQLiteConnection r1) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public void onPreMigrate(SQLiteConnection r1) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection r1) {
            throw new IllegalStateException("NOP delegate should never be called");
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0016J \u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000f"}, d2 = {"Landroidx/room/RoomConnectionManager$SupportOpenHelperCallback;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "version", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/room/RoomConnectionManager;I)V", "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "onUpgrade", "oldVersion", "newVersion", "onDowngrade", "onOpen", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class SupportOpenHelperCallback extends SupportSQLiteOpenHelper.Callback {
        final /* synthetic */ RoomConnectionManager this$0;

        public SupportOpenHelperCallback(RoomConnectionManager r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
        public void onCreate(SupportSQLiteDatabase r2) {
            this.this$0.onCreate(new SupportSQLiteConnection(r2));
        }

        @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
        public void onDowngrade(SupportSQLiteDatabase r1, int r2, int r3) {
            onUpgrade(r1, r2, r3);
        }

        @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
        public void onOpen(SupportSQLiteDatabase r3) {
            this.this$0.onOpen(new SupportSQLiteConnection(r3));
            RoomConnectionManager.access$setSupportDatabase$p(this.this$0, r3);
        }

        @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
        public void onUpgrade(SupportSQLiteDatabase r2, int r3, int r4) {
            this.this$0.onMigrate(new SupportSQLiteConnection(r2), r3, r4);
        }
    }

    /* renamed from: $r8$lambda$vdT1_--AQh9BTyGGkFjKYq85q98, reason: not valid java name */
    public static Unit m942$r8$lambda$vdT1_AQh9BTyGGkFjKYq85q98(RoomConnectionManager r0, SupportSQLiteDatabase r1) {
        r0.supportDatabase = r1;
        return Unit.INSTANCE;
    }

    public RoomConnectionManager(DatabaseConfiguration r4, RoomOpenDelegate r5, Function2<? super Function1<? super Continuation<Object>, ? extends Object>, ? super Continuation<Object>, ? extends Object> r6) {
        this.configuration = r4;
        this.openDelegate = r5;
        List<RoomDatabase.Callback> r0 = r4.callbacks;
        if (r0 != null) goto L5;
        r0 = CollectionsKt.emptyList();
    L5:
        this.callbacks = r0;
        SQLiteDriver r02 = r4.sqliteDriver;
        String r2 = ":memory:";
        if (r02 == null) goto L8;
        this.supportOpenHelper = null;
        if (r02.hasConnectionPool() == false) goto L23;
        BaseRoomConnectionManager.DriverWrapper r03 = new BaseRoomConnectionManager.DriverWrapper(this, r4.sqliteDriver);
        String r42 = r4.name;
        if (r42 == null) goto L22;
        r2 = r42;
    L22:
        ConnectionPool r52 = new PassthroughConnectionPool(r03, r2, r6);
    L27:
        this.connectionPool = r52;
    L28:
        init();
        return;
    L23:
        String r53 = r4.name;
        SQLiteDriver r62 = r4.sqliteDriver;
        if (r53 != null) goto L26;
        r52 = ConnectionPoolKt.newSingleConnectionPool(new BaseRoomConnectionManager.DriverWrapper(this, r62), ":memory:", r4.getPreparedStatementCacheSize$room_runtime());
        goto L27
    L26:
        r52 = ConnectionPoolKt.newConnectionPool(new BaseRoomConnectionManager.DriverWrapper(this, r62), r4.name, getMaxNumberOfReaders(r4.journalMode), getMaxNumberOfWriters(r4.journalMode), r4.getPreparedStatementCacheSize$room_runtime());
        goto L27
    L8:
        if (r4.sqliteOpenHelperFactory == null) goto L14;
        SupportSQLiteOpenHelper.Configuration r54 = SupportSQLiteOpenHelper.Configuration.Companion.builder(r4.context).name(r4.name).callback(new SupportOpenHelperCallback(this, r5.getVersion())).build();
        SupportSQLiteOpenHelper r55 = r4.sqliteOpenHelperFactory.create(r54);
        this.supportOpenHelper = r55;
        SupportSQLiteDriver r1 = new SupportSQLiteDriver(r55);
        String r43 = r4.name;
        if (r43 == null) goto L13;
        r2 = r43;
    L13:
        this.connectionPool = new PassthroughConnectionPool(r1, r2, r6);
        goto L28
    L14:
        f$$ExternalSyntheticBUOutline1.m("SQLiteManager was constructed with both null driver and open helper factory!");
        throw null;
    }

    public RoomConnectionManager(DatabaseConfiguration r3, Function1<? super DatabaseConfiguration, ? extends SupportSQLiteOpenHelper> r4, Function2<? super Function1<? super Continuation<Object>, ? extends Object>, ? super Continuation<Object>, ? extends Object> r5) {
        this.configuration = r3;
        this.openDelegate = new NoOpOpenDelegate();
        List<RoomDatabase.Callback> r0 = r3.callbacks;
        if (r0 != null) goto L5;
        r0 = CollectionsKt.emptyList();
    L5:
        this.callbacks = r0;
        SupportSQLiteOpenHelper r42 = r4.invoke(installOnOpenCallback(r3, new RoomConnectionManager$$ExternalSyntheticLambda0(this)));
        this.supportOpenHelper = r42;
        SupportSQLiteDriver r1 = new SupportSQLiteDriver(r42);
        String r32 = r3.name;
        if (r32 != null) goto L8;
        r32 = ":memory:";
    L8:
        this.connectionPool = new PassthroughConnectionPool(r1, r32, r5);
        init();
    }

    public static final /* synthetic */ void access$setSupportDatabase$p(RoomConnectionManager r0, SupportSQLiteDatabase r1) {
        r0.supportDatabase = r1;
    }

    private final void init() {
        if (getConfiguration().journalMode != RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING) goto L5;
        boolean r0 = true;
    L6:
        SupportSQLiteOpenHelper r2 = this.supportOpenHelper;
        if (r2 == null) goto L10;
        r2.setWriteAheadLoggingEnabled(r0);
        return;
    L10:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private final DatabaseConfiguration installOnOpenCallback(DatabaseConfiguration r27, Function1<? super SupportSQLiteDatabase, Unit> r28) {
        List<RoomDatabase.Callback> r0 = r27.callbacks;
        if (r0 != null) goto L6;
        r0 = CollectionsKt.emptyList();
    L6:
        return DatabaseConfiguration.copy$default(r27, null, null, null, null, CollectionsKt.plus(r0, new RoomConnectionManager$installOnOpenCallback$newCallbacks$1(r28)), false, null, null, null, null, false, false, null, null, null, null, null, null, null, false, null, null, 4194287, null);
    }

    public final void close() {
        this.connectionPool.close();
        SupportSQLiteOpenHelper r1 = this.supportOpenHelper;
        if (r1 == null) goto L6;
        r1.close();
        return;
    }

    @Override // androidx.room.BaseRoomConnectionManager
    public List<RoomDatabase.Callback> getCallbacks() {
        return this.callbacks;
    }

    @Override // androidx.room.BaseRoomConnectionManager
    public DatabaseConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // androidx.room.BaseRoomConnectionManager
    public RoomOpenDelegate getOpenDelegate() {
        return this.openDelegate;
    }

    public final SupportSQLiteOpenHelper getSupportOpenHelper$room_runtime() {
        return this.supportOpenHelper;
    }

    public final boolean isSupportDatabaseOpen() {
        SupportSQLiteDatabase r0 = this.supportDatabase;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.isOpen();
    }

    @Override // androidx.room.BaseRoomConnectionManager
    public String resolveFileName$room_runtime(String r2) {
        if (Intrinsics.areEqual(r2, ":memory:") == false) goto L5;
        return r2;
    L5:
        return getConfiguration().context.getDatabasePath(r2).getAbsolutePath();
    }

    public <R> Object useConnection(boolean r1, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r2, Continuation<? super R> r3) {
        return this.connectionPool.useConnection(r1, r2, r3);
    }
}
