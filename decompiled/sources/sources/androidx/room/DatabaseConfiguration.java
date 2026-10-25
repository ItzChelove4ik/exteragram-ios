package androidx.room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.util.MigrationUtil;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.TLObject;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\b\u0016\u0018\u00002\u00020\u0001Bí\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0011\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0016\u001a\u00020\r\u0012\u0006\u0010\u0017\u001a\u00020\r\u0012\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e\u0012\b\u0010\"\u001a\u0004\u0018\u00010!\u0012\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\n\u0012\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\n\u0012\u0006\u0010&\u001a\u00020\r\u0012\b\u0010(\u001a\u0004\u0018\u00010'\u0012\b\u0010*\u001a\u0004\u0018\u00010)¢\u0006\u0004\b+\u0010,J\u001f\u0010/\u001a\u00020\r2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0019H\u0016¢\u0006\u0004\b/\u00100J\u009f\u0002\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\r2\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\n2\b\b\u0002\u0010&\u001a\u00020\r2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)H\u0007¢\u0006\u0004\b1\u00102R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u00104R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u00105R\u0014\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\t\u00106R\u001c\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\f\u00107R\u0014\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000e\u00108R\u0014\u0010\u0010\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0010\u00109R\u0014\u0010\u0012\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010:R\u0014\u0010\u0013\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010:R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010;R\u0014\u0010\u0016\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0016\u00108R\u0014\u0010\u0017\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0017\u00108R\"\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001a\u0010<\u001a\u0004\b=\u0010>R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001b\u00104R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010?R\u001c\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b \u0010@R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\n8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b#\u00107R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\n8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b%\u00107R\u0014\u0010&\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b&\u00108R\u0016\u0010(\u001a\u0004\u0018\u00010'8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b(\u0010AR\u0016\u0010*\u001a\u0004\u0018\u00010)8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b*\u0010BR\u0014\u0010C\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\bC\u00108R\"\u0010D\u001a\u00020\r8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bD\u00108\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\"\u0010I\u001a\u00020\u00198\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bI\u0010J\u001a\u0004\bK\u0010L\"\u0004\bM\u0010N¨\u0006O"}, d2 = {"Landroidx/room/DatabaseConfiguration;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "sqliteOpenHelperFactory", "Landroidx/room/RoomDatabase$MigrationContainer;", "migrationContainer", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/RoomDatabase$Callback;", "callbacks", _UrlKt.FRAGMENT_ENCODE_SET, "allowMainThreadQueries", "Landroidx/room/RoomDatabase$JournalMode;", "journalMode", "Ljava/util/concurrent/Executor;", "queryExecutor", "transactionExecutor", "Landroid/content/Intent;", "multiInstanceInvalidationServiceIntent", "requireMigration", "allowDestructiveMigrationOnDowngrade", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "migrationNotRequiredFrom", "copyFromAssetPath", "Ljava/io/File;", "copyFromFile", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "copyFromInputStream", "Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "prepackagedDatabaseCallback", "typeConverters", "Landroidx/room/migration/AutoMigrationSpec;", "autoMigrationSpecs", "allowDestructiveMigrationForAllTables", "Landroidx/sqlite/SQLiteDriver;", "sqliteDriver", "Lkotlin/coroutines/CoroutineContext;", "queryCoroutineContext", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Landroid/content/Intent;ZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;Ljava/util/List;Ljava/util/List;ZLandroidx/sqlite/SQLiteDriver;Lkotlin/coroutines/CoroutineContext;)V", "fromVersion", "toVersion", "isMigrationRequired", "(II)Z", "copy", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Landroid/content/Intent;ZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;Ljava/util/List;Ljava/util/List;ZLandroidx/sqlite/SQLiteDriver;Lkotlin/coroutines/CoroutineContext;)Landroidx/room/DatabaseConfiguration;", "Landroid/content/Context;", "Ljava/lang/String;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "Landroidx/room/RoomDatabase$MigrationContainer;", "Ljava/util/List;", "Z", "Landroidx/room/RoomDatabase$JournalMode;", "Ljava/util/concurrent/Executor;", "Landroid/content/Intent;", "Ljava/util/Set;", "getMigrationNotRequiredFrom$room_runtime", "()Ljava/util/Set;", "Ljava/io/File;", "Ljava/util/concurrent/Callable;", "Landroidx/sqlite/SQLiteDriver;", "Lkotlin/coroutines/CoroutineContext;", "multiInstanceInvalidation", "useTempTrackingTable", "getUseTempTrackingTable$room_runtime", "()Z", "setUseTempTrackingTable$room_runtime", "(Z)V", "preparedStatementCacheSize", "I", "getPreparedStatementCacheSize$room_runtime", "()I", "setPreparedStatementCacheSize$room_runtime", "(I)V", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public class DatabaseConfiguration {

    @JvmField
    public final boolean allowDestructiveMigrationForAllTables;

    @JvmField
    public final boolean allowDestructiveMigrationOnDowngrade;

    @JvmField
    public final boolean allowMainThreadQueries;

    @JvmField
    public final List<AutoMigrationSpec> autoMigrationSpecs;

    @JvmField
    public final List<RoomDatabase.Callback> callbacks;

    @JvmField
    public final Context context;

    @JvmField
    public final String copyFromAssetPath;

    @JvmField
    public final File copyFromFile;

    @JvmField
    public final Callable<InputStream> copyFromInputStream;

    @JvmField
    public final RoomDatabase.JournalMode journalMode;

    @JvmField
    public final RoomDatabase.MigrationContainer migrationContainer;
    private final Set<Integer> migrationNotRequiredFrom;

    @JvmField
    public final boolean multiInstanceInvalidation;

    @JvmField
    public final Intent multiInstanceInvalidationServiceIntent;

    @JvmField
    public final String name;
    private int preparedStatementCacheSize;

    @JvmField
    public final CoroutineContext queryCoroutineContext;

    @JvmField
    public final Executor queryExecutor;

    @JvmField
    public final boolean requireMigration;

    @JvmField
    public final SQLiteDriver sqliteDriver;

    @JvmField
    public final SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory;

    @JvmField
    public final Executor transactionExecutor;

    @JvmField
    public final List<Object> typeConverters;
    private boolean useTempTrackingTable;

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"LambdaLast"})
    public DatabaseConfiguration(Context r1, String r2, SupportSQLiteOpenHelper.Factory r3, RoomDatabase.MigrationContainer r4, List<? extends RoomDatabase.Callback> r5, boolean r6, RoomDatabase.JournalMode r7, Executor r8, Executor r9, Intent r10, boolean r11, boolean r12, Set<Integer> r13, String r14, File r15, Callable<InputStream> r16, RoomDatabase.PrepackagedDatabaseCallback r17, List<? extends Object> r18, List<? extends AutoMigrationSpec> r19, boolean r20, SQLiteDriver r21, CoroutineContext r22) {
        this.context = r1;
        this.name = r2;
        this.sqliteOpenHelperFactory = r3;
        this.migrationContainer = r4;
        this.callbacks = r5;
        this.allowMainThreadQueries = r6;
        this.journalMode = r7;
        this.queryExecutor = r8;
        this.transactionExecutor = r9;
        this.multiInstanceInvalidationServiceIntent = r10;
        this.requireMigration = r11;
        this.allowDestructiveMigrationOnDowngrade = r12;
        this.migrationNotRequiredFrom = r13;
        this.copyFromAssetPath = r14;
        this.copyFromFile = r15;
        this.copyFromInputStream = r16;
        this.typeConverters = r18;
        this.autoMigrationSpecs = r19;
        this.allowDestructiveMigrationForAllTables = r20;
        this.sqliteDriver = r21;
        this.queryCoroutineContext = r22;
        if (r10 == null) goto L5;
        boolean r23 = true;
    L6:
        this.multiInstanceInvalidation = r23;
        this.useTempTrackingTable = true;
        this.preparedStatementCacheSize = 25;
        return;
    L5:
        r23 = false;
        goto L6
    }

    public static /* synthetic */ DatabaseConfiguration copy$default(DatabaseConfiguration r18, Context r19, String r20, SupportSQLiteOpenHelper.Factory r21, RoomDatabase.MigrationContainer r22, List r23, boolean r24, RoomDatabase.JournalMode r25, Executor r26, Executor r27, Intent r28, boolean r29, boolean r30, Set r31, String r32, File r33, Callable r34, RoomDatabase.PrepackagedDatabaseCallback r35, List r36, List r37, boolean r38, SQLiteDriver r39, CoroutineContext r40, int r41, Object r42) {
        if (r42 == null) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: copy");
        return null;
    L5:
        if ((r41 & 1) == 0) goto L7;
        Context r2 = r18.context;
    L9:
        if ((r41 & 2) == 0) goto L11;
        String r3 = r18.name;
    L13:
        if ((r41 & 4) == 0) goto L15;
        SupportSQLiteOpenHelper.Factory r4 = r18.sqliteOpenHelperFactory;
    L17:
        if ((r41 & 8) == 0) goto L19;
        RoomDatabase.MigrationContainer r5 = r18.migrationContainer;
    L21:
        if ((r41 & 16) == 0) goto L23;
        List r6 = r18.callbacks;
    L25:
        if ((r41 & 32) == 0) goto L27;
        boolean r7 = r18.allowMainThreadQueries;
    L29:
        if ((r41 & 64) == 0) goto L31;
        RoomDatabase.JournalMode r8 = r18.journalMode;
    L33:
        if ((r41 & 128) == 0) goto L35;
        Executor r9 = r18.queryExecutor;
    L37:
        if ((r41 & 256) == 0) goto L39;
        Executor r10 = r18.transactionExecutor;
    L41:
        if ((r41 & 512) == 0) goto L43;
        Intent r11 = r18.multiInstanceInvalidationServiceIntent;
    L45:
        if ((r41 & 1024) == 0) goto L47;
        boolean r12 = r18.requireMigration;
    L49:
        if ((r41 & 2048) == 0) goto L51;
        boolean r13 = r18.allowDestructiveMigrationOnDowngrade;
    L53:
        if ((r41 & 4096) == 0) goto L55;
        Set r14 = r18.migrationNotRequiredFrom;
    L57:
        if ((r41 & 8192) == 0) goto L59;
        String r15 = r18.copyFromAssetPath;
    L60:
        Context r192 = r2;
        if ((r41 & 16384) == 0) goto L63;
        File r210 = r18.copyFromFile;
    L65:
        if ((r41 & 32768) == 0) goto L67;
        Callable r1 = r18.copyFromInputStream;
    L69:
        if ((r41 & 65536) == 0) goto L71;
        r18.getClass();
        RoomDatabase.PrepackagedDatabaseCallback r16 = null;
    L72:
        Callable r202 = r1;
        if ((r41 & 131072) == 0) goto L75;
        List r17 = r18.typeConverters;
    L76:
        List r212 = r17;
        if ((r41 & 262144) == 0) goto L79;
        List r110 = r18.autoMigrationSpecs;
    L80:
        List r222 = r110;
        if ((r41 & 524288) == 0) goto L83;
        boolean r111 = r18.allowDestructiveMigrationForAllTables;
    L84:
        boolean r232 = r111;
        if ((r41 & 1048576) == 0) goto L87;
        SQLiteDriver r112 = r18.sqliteDriver;
    L89:
        if ((r41 & TLObject.FLAG_21) == 0) goto L92;
        SQLiteDriver r242 = r112;
        SQLiteDriver r402 = r242;
        CoroutineContext r412 = r18.queryCoroutineContext;
    L94:
        return r18.copy(r192, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r210, r202, r16, r212, r222, r232, r402, r412);
    L92:
        r412 = r40;
        r402 = r112;
        goto L94
    L87:
        r112 = r39;
        goto L89
    L83:
        r111 = r38;
        goto L84
    L79:
        r110 = r37;
        goto L80
    L75:
        r17 = r36;
        goto L76
    L71:
        r16 = r35;
        goto L72
    L67:
        r1 = r34;
        goto L69
    L63:
        r210 = r33;
        goto L65
    L59:
        r15 = r32;
        goto L60
    L55:
        r14 = r31;
        goto L57
    L51:
        r13 = r30;
        goto L53
    L47:
        r12 = r29;
        goto L49
    L43:
        r11 = r28;
        goto L45
    L39:
        r10 = r27;
        goto L41
    L35:
        r9 = r26;
        goto L37
    L31:
        r8 = r25;
        goto L33
    L27:
        r7 = r24;
        goto L29
    L23:
        r6 = r23;
        goto L25
    L19:
        r5 = r22;
        goto L21
    L15:
        r4 = r21;
        goto L17
    L11:
        r3 = r20;
        goto L13
    L7:
        r2 = r19;
        goto L9
    }

    public final DatabaseConfiguration copy(Context r25, String r26, SupportSQLiteOpenHelper.Factory r27, RoomDatabase.MigrationContainer r28, List<? extends RoomDatabase.Callback> r29, boolean r30, RoomDatabase.JournalMode r31, Executor r32, Executor r33, Intent r34, boolean r35, boolean r36, Set<Integer> r37, String r38, File r39, Callable<InputStream> r40, RoomDatabase.PrepackagedDatabaseCallback r41, List<? extends Object> r42, List<? extends AutoMigrationSpec> r43, boolean r44, SQLiteDriver r45, CoroutineContext r46) {
        DatabaseConfiguration r1 = new DatabaseConfiguration(r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46);
        r1.useTempTrackingTable = this.useTempTrackingTable;
        r1.preparedStatementCacheSize = this.preparedStatementCacheSize;
        return r1;
    }

    public final Set<Integer> getMigrationNotRequiredFrom$room_runtime() {
        return this.migrationNotRequiredFrom;
    }

    public final int getPreparedStatementCacheSize$room_runtime() {
        return this.preparedStatementCacheSize;
    }

    public final boolean getUseTempTrackingTable$room_runtime() {
        return this.useTempTrackingTable;
    }

    public boolean isMigrationRequired(int r1, int r2) {
        return MigrationUtil.isMigrationRequired(this, r1, r2);
    }

    public final void setUseTempTrackingTable$room_runtime(boolean r1) {
        this.useTempTrackingTable = r1;
    }
}
