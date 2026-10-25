package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.Transactor;
import androidx.room.concurrent.CloseBarrier;
import androidx.room.coroutines.RunBlockingUninterruptible_androidKt;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.support.AutoCloser;
import androidx.room.support.AutoClosingRoomOpenHelper;
import androidx.room.support.AutoClosingRoomOpenHelperFactory;
import androidx.room.support.PrePackagedCopyOpenHelper;
import androidx.room.support.PrePackagedCopyOpenHelperFactory;
import androidx.room.util.DBUtil;
import androidx.room.util.KClassUtil;
import androidx.room.util.MigrationUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.sqlite.driver.SupportSQLiteConnection;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmSuppressWildcards;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0013\b&\u0018\u0000 µ\u00012\u00020\u0001:\f¶\u0001·\u0001¸\u0001¹\u0001º\u0001µ\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\u0003J#\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u000f\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u000f\u001a\u00028\u0000\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0007¢\u0006\u0004\b\u000f\u0010\u0012J#\u0010\u0017\u001a\u00020\u00042\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0014\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0018H\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u0018H\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ1\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#2\u001a\u0010\"\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020!0\r\u0012\u0004\u0012\u00020!0 H\u0017¢\u0006\u0004\b%\u0010&J1\u0010'\u001a\b\u0012\u0004\u0012\u00020$0#2\u001a\u0010\"\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020!0\u0011\u0012\u0004\u0012\u00020!0 H\u0017¢\u0006\u0004\b'\u0010&J\u0017\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020\u0018H\u0015¢\u0006\u0004\b*\u0010+J\u000f\u0010-\u001a\u00020,H\u0015¢\u0006\u0004\b-\u0010.J\u000f\u00100\u001a\u00020/H$¢\u0006\u0004\b0\u00101J\u000f\u00103\u001a\u000202H\u0007¢\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u000205H\u0007¢\u0006\u0004\b6\u00107J\u000f\u00109\u001a\u000205H\u0000¢\u0006\u0004\b8\u00107J)\u0010:\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0#0 H\u0015¢\u0006\u0004\b:\u0010;J)\u0010<\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110#0 H\u0015¢\u0006\u0004\b<\u0010;J\u001d\u0010>\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020!0\r0=H\u0017¢\u0006\u0004\b>\u0010?J\u001d\u0010@\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020!0\u00110=H\u0017¢\u0006\u0004\b@\u0010?J\u000f\u0010A\u001a\u00020\u0004H'¢\u0006\u0004\bA\u0010\u0003J+\u0010G\u001a\u00020\u00042\u0006\u0010C\u001a\u00020B2\u0012\u0010F\u001a\n\u0012\u0006\b\u0001\u0012\u00020E0D\"\u00020EH\u0005¢\u0006\u0004\bG\u0010HJ\u000f\u0010I\u001a\u00020\u0004H\u0016¢\u0006\u0004\bI\u0010\u0003J\u000f\u0010J\u001a\u00020\u0004H\u0017¢\u0006\u0004\bJ\u0010\u0003J\u000f\u0010K\u001a\u00020\u0004H\u0017¢\u0006\u0004\bK\u0010\u0003JB\u0010R\u001a\u00028\u0000\"\u0004\b\u0000\u0010L2\u0006\u0010M\u001a\u00020B2\"\u0010Q\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020O\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000P\u0012\u0006\u0012\u0004\u0018\u00010\u00010NH\u0087@¢\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020BH\u0007¢\u0006\u0004\bT\u0010UJ+\u0010V\u001a\u00020X2\u0006\u0010V\u001a\u00020E2\u0012\u0010W\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010DH\u0016¢\u0006\u0004\bV\u0010YJ#\u0010V\u001a\u00020X2\u0006\u0010V\u001a\u00020Z2\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010[H\u0017¢\u0006\u0004\bV\u0010]J\u0017\u0010`\u001a\u00020_2\u0006\u0010^\u001a\u00020EH\u0016¢\u0006\u0004\b`\u0010aJ\u000f\u0010b\u001a\u00020\u0004H\u0017¢\u0006\u0004\bb\u0010\u0003J\u000f\u0010c\u001a\u00020\u0004H\u0017¢\u0006\u0004\bc\u0010\u0003J\u000f\u0010d\u001a\u00020\u0004H\u0017¢\u0006\u0004\bd\u0010\u0003J\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020eH\u0016¢\u0006\u0004\b\u000b\u0010fJ#\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010g2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000hH\u0016¢\u0006\u0004\b\u000b\u0010iJ\u0017\u0010l\u001a\u00020\u00042\u0006\u0010k\u001a\u00020jH\u0015¢\u0006\u0004\bl\u0010mJ\u0017\u0010l\u001a\u00020\u00042\u0006\u0010o\u001a\u00020nH\u0005¢\u0006\u0004\bl\u0010pJ\u000f\u0010q\u001a\u00020BH\u0016¢\u0006\u0004\bq\u0010UR\u001e\u0010r\u001a\u0004\u0018\u00010j8\u0004@\u0004X\u0085\u000e¢\u0006\f\n\u0004\br\u0010s\u0012\u0004\bt\u0010\u0003R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0019\u0010uR\u0016\u0010v\u001a\u0002028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u0002058\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bx\u0010yR\u0016\u0010{\u001a\u00020z8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020z8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b}\u0010|R\u0016\u0010~\u001a\u00020\u001c8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u0019\u0010\u0080\u0001\u001a\u00020/8\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R \u0010\u0083\u0001\u001a\u00030\u0082\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0083\u0001\u0010\u0084\u0001\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0019\u0010\u0087\u0001\u001a\u00020B8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R)\u0010\u008a\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0089\u0001\u0018\u00010#8\u0004@\u0004X\u0085\u000e¢\u0006\u000f\n\u0006\b\u008a\u0001\u0010\u008b\u0001\u0012\u0005\b\u008c\u0001\u0010\u0003R\u001c\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008d\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R#\u0010\u0091\u0001\u001a\t\u0012\u0004\u0012\u0002050\u0090\u00018G¢\u0006\u0010\n\u0006\b\u0091\u0001\u0010\u0092\u0001\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R(\u0010\u0096\u0001\u001a\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u0004\u0012\u00020\u00010\u0095\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001R(\u0010\u0098\u0001\u001a\u00020B8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0006\b\u0098\u0001\u0010\u0088\u0001\u001a\u0005\b\u0099\u0001\u0010U\"\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0016\u0010\u009c\u0001\u001a\u00020B8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u009c\u0001\u0010UR\u0016\u0010\u009f\u0001\u001a\u0004\u0018\u00010E8G¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0017\u0010¢\u0001\u001a\u00020z8VX\u0096\u0004¢\u0006\b\u001a\u0006\b \u0001\u0010¡\u0001R\u0017\u0010¤\u0001\u001a\u00020z8VX\u0096\u0004¢\u0006\b\u001a\u0006\b£\u0001\u0010¡\u0001R\u0017\u0010§\u0001\u001a\u00020)8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¥\u0001\u0010¦\u0001R\u0015\u0010«\u0001\u001a\u00030¨\u00018G¢\u0006\b\u001a\u0006\b©\u0001\u0010ª\u0001R\u0016\u0010\u00ad\u0001\u001a\u00020/8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¬\u0001\u00101R0\u0010¯\u0001\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110#0 8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b®\u0001\u0010;R\u0016\u0010°\u0001\u001a\u00020B8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b°\u0001\u0010UR\u0016\u0010²\u0001\u001a\u00020B8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b±\u0001\u0010UR\u0016\u0010´\u0001\u001a\u00020B8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b³\u0001\u0010U¨\u0006»\u0001"}, d2 = {"Landroidx/room/RoomDatabase;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "onClosed", "internalBeginTransaction", "internalEndTransaction", "T", "Lkotlin/Function0;", "body", "runInTransaction", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Ljava/lang/Class;", "klass", "getTypeConverter", "(Ljava/lang/Class;)Ljava/lang/Object;", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "kclass", "converter", "addTypeConverter$room_runtime", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)V", "addTypeConverter", "Landroidx/room/DatabaseConfiguration;", "configuration", "init", "(Landroidx/room/DatabaseConfiguration;)V", "Landroidx/room/RoomConnectionManager;", "createConnectionManager$room_runtime", "(Landroidx/room/DatabaseConfiguration;)Landroidx/room/RoomConnectionManager;", "createConnectionManager", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/migration/AutoMigrationSpec;", "autoMigrationSpecs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/migration/Migration;", "getAutoMigrations", "(Ljava/util/Map;)Ljava/util/List;", "createAutoMigrations", "config", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "createOpenHelper", "(Landroidx/room/DatabaseConfiguration;)Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/RoomOpenDelegateMarker;", "createOpenDelegate", "()Landroidx/room/RoomOpenDelegateMarker;", "Landroidx/room/InvalidationTracker;", "createInvalidationTracker", "()Landroidx/room/InvalidationTracker;", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "getQueryContext", "()Lkotlin/coroutines/CoroutineContext;", "getTransactionContext$room_runtime", "getTransactionContext", "getRequiredTypeConverters", "()Ljava/util/Map;", "getRequiredTypeConverterClasses", _UrlKt.FRAGMENT_ENCODE_SET, "getRequiredAutoMigrationSpecs", "()Ljava/util/Set;", "getRequiredAutoMigrationSpecClasses", "clearAllTables", _UrlKt.FRAGMENT_ENCODE_SET, "hasForeignKeys", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "tableNames", "performClear", "(Z[Ljava/lang/String;)V", "close", "assertNotMainThread", "assertNotSuspendingTransaction", "R", "isReadOnly", "Lkotlin/Function2;", "Landroidx/room/Transactor;", "Lkotlin/coroutines/Continuation;", "block", "useConnection", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "inCompatibilityMode", "()Z", "query", "args", "Landroid/database/Cursor;", "(Ljava/lang/String;[Ljava/lang/Object;)Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "Landroid/os/CancellationSignal;", "signal", "(Landroidx/sqlite/db/SupportSQLiteQuery;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "sql", "Landroidx/sqlite/db/SupportSQLiteStatement;", "compileStatement", "(Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteStatement;", "beginTransaction", "endTransaction", "setTransactionSuccessful", "Ljava/lang/Runnable;", "(Ljava/lang/Runnable;)V", "V", "Ljava/util/concurrent/Callable;", "(Ljava/util/concurrent/Callable;)Ljava/lang/Object;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "internalInitInvalidationTracker", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "Landroidx/sqlite/SQLiteConnection;", "connection", "(Landroidx/sqlite/SQLiteConnection;)V", "inTransaction", "mDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getMDatabase$annotations", "Landroidx/room/DatabaseConfiguration;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "transactionContext", "Lkotlin/coroutines/CoroutineContext;", "Ljava/util/concurrent/Executor;", "internalQueryExecutor", "Ljava/util/concurrent/Executor;", "internalTransactionExecutor", "connectionManager", "Landroidx/room/RoomConnectionManager;", "internalTracker", "Landroidx/room/InvalidationTracker;", "Landroidx/room/concurrent/CloseBarrier;", "closeBarrier", "Landroidx/room/concurrent/CloseBarrier;", "getCloseBarrier$room_runtime", "()Landroidx/room/concurrent/CloseBarrier;", "allowMainThreadQueries", "Z", "Landroidx/room/RoomDatabase$Callback;", "mCallbacks", "Ljava/util/List;", "getMCallbacks$annotations", "Landroidx/room/support/AutoCloser;", "autoCloser", "Landroidx/room/support/AutoCloser;", "Ljava/lang/ThreadLocal;", "suspendingTransactionContext", "Ljava/lang/ThreadLocal;", "getSuspendingTransactionContext", "()Ljava/lang/ThreadLocal;", _UrlKt.FRAGMENT_ENCODE_SET, "typeConverters", "Ljava/util/Map;", "useTempTrackingTable", "getUseTempTrackingTable$room_runtime", "setUseTempTrackingTable$room_runtime", "(Z)V", "isThreadInSuspendingTransaction", "getPath", "()Ljava/lang/String;", "path", "getQueryExecutor", "()Ljava/util/concurrent/Executor;", "queryExecutor", "getTransactionExecutor", "transactionExecutor", "getOpenHelper", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "openHelper", "Landroidx/sqlite/SQLiteDriver;", "getDriver", "()Landroidx/sqlite/SQLiteDriver;", "driver", "getInvalidationTracker", "invalidationTracker", "getRequiredTypeConverterClassesMap$room_runtime", "requiredTypeConverterClassesMap", "isOpen", "isOpenInternal$room_runtime", "isOpenInternal", "isMainThread$room_runtime", "isMainThread", "Companion", "JournalMode", "Builder", "MigrationContainer", "Callback", "PrepackagedDatabaseCallback", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRoomDatabase.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,2191:1\n379#1,15:2193\n379#1,15:2208\n1#2:2192\n480#3:2223\n426#3:2224\n1252#4,4:2225\n1193#4,2:2229\n1267#4,2:2231\n1563#4:2233\n1634#4,3:2234\n1270#4:2237\n1563#4:2238\n1634#4,3:2239\n*S KotlinDebug\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabase\n*L\n287#1:2193,15\n291#1:2208,15\n364#1:2223\n364#1:2224\n364#1:2225,4\n474#1:2229,2\n474#1:2231,2\n475#1:2233\n475#1:2234,3\n474#1:2237\n502#1:2238\n502#1:2239,3\n*E\n"})
/* loaded from: classes.dex */
public abstract class RoomDatabase {
    public static final Companion Companion = null;
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean allowMainThreadQueries;
    private AutoCloser autoCloser;
    private final CloseBarrier closeBarrier;
    private DatabaseConfiguration configuration;
    private RoomConnectionManager connectionManager;
    private CoroutineScope coroutineScope;
    private Executor internalQueryExecutor;
    private InvalidationTracker internalTracker;
    private Executor internalTransactionExecutor;

    @JvmField
    protected List<? extends Callback> mCallbacks;

    @JvmField
    protected volatile SupportSQLiteDatabase mDatabase;
    private final ThreadLocal<CoroutineContext> suspendingTransactionContext;
    private CoroutineContext transactionContext;
    private final Map<KClass<?>, Object> typeConverters;
    private boolean useTempTrackingTable;

    @Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B)\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00028\u0000H\u0016¢\u0006\u0004\b#\u0010$R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010&R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010'R\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010(R\u001c\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010*R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001f0+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00030+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010-R\u0018\u0010/\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0018\u00101\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00100R\u0018\u00102\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u0010\u0015\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u00104R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010<\u001a\u00020;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010?\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00020E0D8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010GR\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020E0D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010GR\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020I0+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010-R\u0016\u0010K\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u00104R\u0016\u0010L\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u00104R\u0016\u0010M\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u00104R\u0018\u0010N\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010(R\u0018\u0010P\u001a\u0004\u0018\u00010O8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010QR\u001e\u0010T\u001a\n\u0012\u0004\u0012\u00020S\u0018\u00010R8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u0018\u0010W\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Z\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u00104¨\u0006]"}, d2 = {"Landroidx/room/RoomDatabase$Builder;", "Landroidx/room/RoomDatabase;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "context", "Ljava/lang/Class;", "klass", _UrlKt.FRAGMENT_ENCODE_SET, "name", "<init>", "(Landroid/content/Context;Ljava/lang/Class;Ljava/lang/String;)V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", PluginsConstants.Settings.FACTORY, "openHelperFactory", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;)Landroidx/room/RoomDatabase$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/migration/Migration;", "migrations", "addMigrations", "([Landroidx/room/migration/Migration;)Landroidx/room/RoomDatabase$Builder;", "allowMainThreadQueries", "()Landroidx/room/RoomDatabase$Builder;", "Ljava/util/concurrent/Executor;", "executor", "setQueryExecutor", "(Ljava/util/concurrent/Executor;)Landroidx/room/RoomDatabase$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "dropAllTables", "fallbackToDestructiveMigration", "(Z)Landroidx/room/RoomDatabase$Builder;", "Landroidx/room/RoomDatabase$Callback;", com.sun.jna.Callback.METHOD_NAME, "addCallback", "(Landroidx/room/RoomDatabase$Callback;)Landroidx/room/RoomDatabase$Builder;", "build", "()Landroidx/room/RoomDatabase;", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/KClass;", "Landroid/content/Context;", "Ljava/lang/String;", "Lkotlin/Function0;", "Lkotlin/jvm/functions/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "callbacks", "Ljava/util/List;", "typeConverters", "queryExecutor", "Ljava/util/concurrent/Executor;", "transactionExecutor", "supportOpenHelperFactory", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "Z", "Landroidx/room/RoomDatabase$JournalMode;", "journalMode", "Landroidx/room/RoomDatabase$JournalMode;", "Landroid/content/Intent;", "multiInstanceInvalidationIntent", "Landroid/content/Intent;", _UrlKt.FRAGMENT_ENCODE_SET, "autoCloseTimeout", "J", "Ljava/util/concurrent/TimeUnit;", "autoCloseTimeUnit", "Ljava/util/concurrent/TimeUnit;", "Landroidx/room/RoomDatabase$MigrationContainer;", "migrationContainer", "Landroidx/room/RoomDatabase$MigrationContainer;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "migrationsNotRequiredFrom", "Ljava/util/Set;", "migrationStartAndEndVersions", "Landroidx/room/migration/AutoMigrationSpec;", "autoMigrationSpecs", "requireMigration", "allowDestructiveMigrationOnDowngrade", "allowDestructiveMigrationForAllTables", "copyFromAssetPath", "Ljava/io/File;", "copyFromFile", "Ljava/io/File;", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "copyFromInputStream", "Ljava/util/concurrent/Callable;", "Landroidx/sqlite/SQLiteDriver;", "driver", "Landroidx/sqlite/SQLiteDriver;", "Lkotlin/coroutines/CoroutineContext;", "queryCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "inMemoryTrackingTableMode", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRoomDatabase.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabase$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,2191:1\n1#2:2192\n*E\n"})
    public static class Builder<T extends RoomDatabase> {
        private boolean allowDestructiveMigrationForAllTables;
        private boolean allowDestructiveMigrationOnDowngrade;
        private boolean allowMainThreadQueries;
        private TimeUnit autoCloseTimeUnit;
        private long autoCloseTimeout;
        private final List<AutoMigrationSpec> autoMigrationSpecs;
        private final List<Callback> callbacks;
        private final Context context;
        private String copyFromAssetPath;
        private File copyFromFile;
        private Callable<InputStream> copyFromInputStream;
        private SQLiteDriver driver;
        private final Function0<T> factory;
        private boolean inMemoryTrackingTableMode;
        private JournalMode journalMode;
        private final KClass<T> klass;
        private final MigrationContainer migrationContainer;
        private final Set<Integer> migrationStartAndEndVersions;
        private Set<Integer> migrationsNotRequiredFrom;
        private Intent multiInstanceInvalidationIntent;
        private final String name;
        private CoroutineContext queryCoroutineContext;
        private Executor queryExecutor;
        private boolean requireMigration;
        private SupportSQLiteOpenHelper.Factory supportOpenHelperFactory;
        private Executor transactionExecutor;
        private final List<Object> typeConverters;

        public Builder(Context r3, Class<T> r4, String r5) {
            this.callbacks = new ArrayList();
            this.typeConverters = new ArrayList();
            this.journalMode = JournalMode.AUTOMATIC;
            this.autoCloseTimeout = -1;
            this.migrationContainer = new MigrationContainer();
            this.migrationsNotRequiredFrom = new LinkedHashSet();
            this.migrationStartAndEndVersions = new LinkedHashSet();
            this.autoMigrationSpecs = new ArrayList();
            this.requireMigration = true;
            this.inMemoryTrackingTableMode = true;
            this.klass = JvmClassMappingKt.getKotlinClass(r4);
            this.context = r3;
            this.name = r5;
            this.factory = null;
        }

        public Builder<T> addCallback(Callback r2) {
            this.callbacks.add(r2);
            return this;
        }

        public Builder<T> addMigrations(Migration... r6) {
            int r0 = r6.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            Migration r2 = r6[r1];
            this.migrationStartAndEndVersions.add(Integer.valueOf(r2.startVersion));
            this.migrationStartAndEndVersions.add(Integer.valueOf(r2.endVersion));
            r1 = r1 + 1;
            goto L3
        L5:
            this.migrationContainer.addMigrations((Migration[]) Arrays.copyOf(r6, r6.length));
            return this;
        }

        public Builder<T> allowMainThreadQueries() {
            this.allowMainThreadQueries = true;
            return this;
        }

        public T build() {
            Executor r1 = this.queryExecutor;
            if (r1 == null) goto L5;
        L7:
            if (r1 != null) goto L9;
        L11:
            if (r1 != null) goto L13;
            this.queryExecutor = this.transactionExecutor;
        L13:
            RoomDatabaseKt.validateMigrationsNotRequired(this.migrationStartAndEndVersions, this.migrationsNotRequiredFrom);
            SQLiteDriver r12 = this.driver;
            if (r12 == null) goto L16;
        L18:
            SupportSQLiteOpenHelper.Factory r3 = this.supportOpenHelperFactory;
            if (r12 != null) goto L21;
            SupportSQLiteOpenHelper.Factory r13 = r3;
        L23:
            int r4 = 0;
            if (this.autoCloseTimeout <= 0) goto L26;
            boolean r32 = true;
        L28:
            if (this.copyFromAssetPath == null) goto L30;
        L35:
            boolean r6 = true;
        L37:
            if (r13 == null) goto L71;
            if (r32 == true) goto L40;
        L48:
            if (r6 == true) goto L50;
        L70:
            SupportSQLiteOpenHelper.Factory r11 = r13;
        L72:
            if (r11 != null) goto L80;
            if (r32 == true) goto L78;
            if (r6 == false) goto L80;
            f$$ExternalSyntheticBUOutline1.m("Pre-Package Database is not supported when an SQLiteDriver is configured.");
            return null;
        L78:
            f$$ExternalSyntheticBUOutline1.m("Auto Closing Database is not supported when an SQLiteDriver is configured.");
            return null;
        L80:
            Context r9 = this.context;
            String r10 = this.name;
            MigrationContainer r122 = this.migrationContainer;
            List<Callback> r132 = this.callbacks;
            boolean r14 = this.allowMainThreadQueries;
            JournalMode r15 = this.journalMode.resolve$room_runtime(r9);
            Executor r16 = this.queryExecutor;
            if (r16 == null) goto L93;
            Executor r33 = this.transactionExecutor;
            if (r33 == null) goto L91;
            DatabaseConfiguration r8 = new DatabaseConfiguration(r9, r10, r11, r122, r132, r14, r15, r16, r33, this.multiInstanceInvalidationIntent, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, null, this.typeConverters, this.autoMigrationSpecs, this.allowDestructiveMigrationForAllTables, this.driver, this.queryCoroutineContext);
            r8.setUseTempTrackingTable$room_runtime(this.inMemoryTrackingTableMode);
            Function0<T> r17 = this.factory;
            if (r17 == null) goto L88;
            T r18 = r17.invoke();
            if (r18 == null) goto L88;
        L89:
            r18.init(r8);
            return r18;
        L88:
            r18 = (T) KClassUtil.findAndInstantiateDatabaseImpl$default(JvmClassMappingKt.getJavaClass(this.klass), null, 2, null);
            goto L89
        L91:
            f$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return null;
        L93:
            f$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return null;
        L50:
            if (this.name == null) goto L68;
            String r82 = this.copyFromAssetPath;
            if (r82 != null) goto L54;
            int r92 = 0;
        L55:
            File r102 = this.copyFromFile;
            if (r102 != null) goto L58;
            int r112 = 0;
        L59:
            Callable<InputStream> r123 = this.copyFromInputStream;
            if (r123 == null) goto L64;
            r4 = 1;
        L64:
            if (((r92 + r112) + r4) != 1) goto L66;
            r13 = new PrePackagedCopyOpenHelperFactory(r82, r102, r123, r13);
            goto L70
        L66:
            f$$ExternalSyntheticBUOutline1.m("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.");
            return null;
        L58:
            r112 = 1;
            goto L59
        L54:
            r92 = 1;
            goto L55
        L68:
            f$$ExternalSyntheticBUOutline1.m("Cannot create from asset or file for an in-memory database.");
            return null;
        L40:
            if (this.name == null) goto L46;
            long r103 = this.autoCloseTimeout;
            TimeUnit r124 = this.autoCloseTimeUnit;
            if (r124 == null) goto L44;
            r13 = new AutoClosingRoomOpenHelperFactory(r13, new AutoCloser(r103, r124, null, 4, null));
            goto L48
        L44:
            f$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return null;
        L46:
            f$$ExternalSyntheticBUOutline1.m("Cannot create auto-closing database for an in-memory database.");
            return null;
        L71:
            r11 = null;
            goto L72
        L30:
            if (this.copyFromFile != null) goto L35;
            if (this.copyFromInputStream != null) goto L35;
            r6 = false;
            goto L37
        L26:
            r32 = false;
            goto L28
        L21:
            if (r3 != null) goto L95;
            r13 = null;
            goto L23
        L95:
            f$$ExternalSyntheticBUOutline1.m("A RoomDatabase cannot be configured with both a SQLiteDriver and a SupportOpenHelper.Factory.");
            return null;
        L16:
            if (this.supportOpenHelperFactory != null) goto L18;
            r13 = new FrameworkSQLiteOpenHelperFactory();
            goto L23
        L9:
            if (this.transactionExecutor != null) goto L11;
            this.transactionExecutor = r1;
            goto L13
        L5:
            if (this.transactionExecutor != null) goto L7;
            Executor r19 = ArchTaskExecutor.getIOThreadExecutor();
            this.transactionExecutor = r19;
            this.queryExecutor = r19;
            goto L13
        }

        public final Builder<T> fallbackToDestructiveMigration(boolean r2) {
            this.requireMigration = false;
            this.allowDestructiveMigrationOnDowngrade = true;
            this.allowDestructiveMigrationForAllTables = r2;
            return this;
        }

        public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory r1) {
            this.supportOpenHelperFactory = r1;
            return this;
        }

        public Builder<T> setQueryExecutor(Executor r2) {
            if (this.queryCoroutineContext != null) goto L6;
            this.queryExecutor = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("This builder has already been configured with a CoroutineContext. A RoomDatabasecan only be configured with either an Executor or a CoroutineContext.");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Landroidx/room/RoomDatabase$Callback;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "connection", "Landroidx/sqlite/SQLiteConnection;", "onDestructiveMigration", "onOpen", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Callback {
        public Callback() {
        }

        public void onCreate(SQLiteConnection r2) {
            if ((r2 instanceof SupportSQLiteConnection) == false) goto L6;
            onCreate(((SupportSQLiteConnection) r2).getDb());
            return;
        }

        public void onCreate(SupportSQLiteDatabase r1) {
        }

        public void onDestructiveMigration(SQLiteConnection r2) {
            if ((r2 instanceof SupportSQLiteConnection) == false) goto L6;
            onDestructiveMigration(((SupportSQLiteConnection) r2).getDb());
            return;
        }

        public void onDestructiveMigration(SupportSQLiteDatabase r1) {
        }

        public void onOpen(SQLiteConnection r2) {
            if ((r2 instanceof SupportSQLiteConnection) == false) goto L6;
            onOpen(((SupportSQLiteConnection) r2).getDb());
            return;
        }

        public void onOpen(SupportSQLiteDatabase r1) {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/room/RoomDatabase$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAX_BIND_PARAMETER_CNT", _UrlKt.FRAGMENT_ENCODE_SET, "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u000b"}, d2 = {"Landroidx/room/RoomDatabase$JournalMode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "AUTOMATIC", "TRUNCATE", "WRITE_AHEAD_LOGGING", "resolve", "context", "Landroid/content/Context;", "resolve$room_runtime", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum JournalMode extends Enum<JournalMode> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ JournalMode[] $VALUES = null;
        public static final JournalMode AUTOMATIC = null;
        public static final JournalMode TRUNCATE = null;
        public static final JournalMode WRITE_AHEAD_LOGGING = null;

        private static final /* synthetic */ JournalMode[] $values() {
            return new JournalMode[]{AUTOMATIC, TRUNCATE, WRITE_AHEAD_LOGGING};
        }

        static {
            AUTOMATIC = new JournalMode("AUTOMATIC", 0);
            TRUNCATE = new JournalMode("TRUNCATE", 1);
            WRITE_AHEAD_LOGGING = new JournalMode("WRITE_AHEAD_LOGGING", 2);
            JournalMode[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        JournalMode(String r1, int r2) {
        }

        public static JournalMode valueOf(String r1) {
            return (JournalMode) Enum.valueOf(JournalMode.class, r1);
        }

        public static JournalMode[] values() {
            return (JournalMode[]) $VALUES.clone();
        }

        public final JournalMode resolve$room_runtime(Context r2) {
            if (this == AUTOMATIC) goto L5;
            return this;
        L5:
            Object r1 = r2.getSystemService("activity");
            if ((r1 instanceof ActivityManager) == false) goto L8;
            ActivityManager r12 = (ActivityManager) r1;
        L9:
            if (r12 == null) goto L15;
            if (r12.isLowRamDevice() == true) goto L15;
            return WRITE_AHEAD_LOGGING;
        L15:
            return TRUNCATE;
        L8:
            r12 = null;
            goto L9
        }
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\b\u001a\u00020\u00072\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\r0\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00132\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e¢\u0006\u0004\b\u0019\u0010\u001aJ7\u0010 \u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001d\u0018\u00010\u001c2\u0006\u0010\u001b\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ7\u0010\"\u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001d\u0018\u00010\u001c2\u0006\u0010\u001b\u001a\u00020\u000eH\u0000¢\u0006\u0004\b!\u0010\u001fR,\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050$0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010%¨\u0006&"}, d2 = {"Landroidx/room/RoomDatabase$MigrationContainer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/migration/Migration;", "migrations", _UrlKt.FRAGMENT_ENCODE_SET, "addMigrations", "([Landroidx/room/migration/Migration;)V", "migration", "addMigration", "(Landroidx/room/migration/Migration;)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getMigrations", "()Ljava/util/Map;", "start", "end", _UrlKt.FRAGMENT_ENCODE_SET, "findMigrationPath", "(II)Ljava/util/List;", "startVersion", "endVersion", _UrlKt.FRAGMENT_ENCODE_SET, "contains", "(II)Z", "migrationStart", "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "getSortedNodes$room_runtime", "(I)Lkotlin/Pair;", "getSortedNodes", "getSortedDescendingNodes$room_runtime", "getSortedDescendingNodes", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/TreeMap;", "Ljava/util/Map;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRoomDatabase.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabase$MigrationContainer\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,2191:1\n13472#2,2:2192\n1869#3,2:2194\n384#4,7:2196\n*S KotlinDebug\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabase$MigrationContainer\n*L\n1830#1:2192,2\n1840#1:2194,2\n1853#1:2196,7\n*E\n"})
    public static class MigrationContainer {
        private final Map<Integer, TreeMap<Integer, Migration>> migrations;

        public MigrationContainer() {
            this.migrations = new LinkedHashMap();
        }

        public final void addMigration(Migration r4) {
            int r0 = r4.startVersion;
            int r1 = r4.endVersion;
            Map<Integer, TreeMap<Integer, Migration>> r3 = this.migrations;
            Integer r02 = Integer.valueOf(r0);
            TreeMap<Integer, Migration> r2 = r3.get(r02);
            if (r2 != null) goto L5;
            r2 = new TreeMap();
            r3.put(r02, r2);
        L5:
            TreeMap<Integer, Migration> r22 = r2;
            if (r22.containsKey(Integer.valueOf(r1)) == false) goto L8;
            Log.w("ROOM", "Overriding migration " + r22.get(Integer.valueOf(r1)) + " with " + r4);
        L8:
            r22.put(Integer.valueOf(r1), r4);
        }

        public void addMigrations(Migration... r4) {
            int r0 = r4.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            addMigration(r4[r1]);
            r1 = r1 + 1;
            goto L3
        }

        public final boolean contains(int r1, int r2) {
            return MigrationUtil.contains(this, r1, r2);
        }

        public List<Migration> findMigrationPath(int r1, int r2) {
            return MigrationUtil.findMigrationPath(this, r1, r2);
        }

        public Map<Integer, Map<Integer, Migration>> getMigrations() {
            return this.migrations;
        }

        public final Pair<Map<Integer, Migration>, Iterable<Integer>> getSortedDescendingNodes$room_runtime(int r1) {
            TreeMap<Integer, Migration> r0 = this.migrations.get(Integer.valueOf(r1));
            if (r0 != null) goto L7;
            return null;
        L7:
            return TuplesKt.to(r0, r0.descendingKeySet());
        }

        public final Pair<Map<Integer, Migration>, Iterable<Integer>> getSortedNodes$room_runtime(int r1) {
            TreeMap<Integer, Migration> r0 = this.migrations.get(Integer.valueOf(r1));
            if (r0 != null) goto L7;
            return null;
        L7:
            return TuplesKt.to(r0, r0.keySet());
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b&\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", _UrlKt.FRAGMENT_ENCODE_SET, "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static abstract class PrepackagedDatabaseCallback {
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1", f = "RoomDatabase.android.kt", i = {}, l = {531}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.RoomDatabase$performClear$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $hasForeignKeys;
        final /* synthetic */ String[] $tableNames;
        int label;
        final /* synthetic */ RoomDatabase this$0;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "Landroidx/room/Transactor;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1$1", f = "RoomDatabase.android.kt", i = {0, 1, 2, 3, 4}, l = {532, 533, 535, 541, 542, 543}, m = "invokeSuspend", n = {"connection", "connection", "connection", "connection", "connection"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0"})
        /* renamed from: androidx.room.RoomDatabase$performClear$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00071 extends SuspendLambda implements Function2<Transactor, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $hasForeignKeys;
            final /* synthetic */ String[] $tableNames;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ RoomDatabase this$0;

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/TransactionScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1$1$1", f = "RoomDatabase.android.kt", i = {0, 1, 1}, l = {537, 539}, m = "invokeSuspend", n = {"$this$withTransaction", "$this$withTransaction", "$this$forEach$iv"}, s = {"L$0", "L$0", "L$1"})
            @SourceDebugExtension({"SMAP\nRoomDatabase.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabase$performClear$1$1$1\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,2191:1\n13472#2,2:2192\n*S KotlinDebug\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabase$performClear$1$1$1\n*L\n539#1:2192,2\n*E\n"})
            /* renamed from: androidx.room.RoomDatabase$performClear$1$1$1, reason: invalid class name and collision with other inner class name */
            public static final class C00081 extends SuspendLambda implements Function2<TransactionScope<Unit>, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $hasForeignKeys;
                final /* synthetic */ String[] $tableNames;
                int I$0;
                int I$1;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;

                public C00081(boolean r1, String[] r2, Continuation<? super C00081> r3) {
                    this.$hasForeignKeys = r1;
                    this.$tableNames = r2;
                    super(2, r3);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
                    C00081 r0 = new C00081(this.$hasForeignKeys, this.$tableNames, r4);
                    r0.L$0 = r3;
                    return r0;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final Object invoke2(TransactionScope<Unit> r1, Continuation<? super Unit> r2) {
                    return ((C00081) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(TransactionScope<Unit> r1, Continuation<? super Unit> r2) {
                    return invoke2(r1, r2);
                }

                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0073 -> B:20:0x0076). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object r10) {
                    Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int r1 = this.label;
                    if (r1 == 0) goto L10;
                    if (r1 == 1) goto L9;
                    if (r1 != 2) goto L7;
                    int r12 = this.I$1;
                    int r4 = this.I$0;
                    String[] r5 = (String[]) this.L$1;
                    TransactionScope r6 = (TransactionScope) this.L$0;
                    ResultKt.throwOnFailure(r10);
                L20:
                    r4 = r4 + 1;
                L16:
                    if (r4 >= r12) goto L22;
                    String r102 = "DELETE FROM `" + r5[r4] + '`';
                    this.L$0 = r6;
                    this.L$1 = r5;
                    this.I$0 = r4;
                    this.I$1 = r12;
                    this.label = 2;
                    if (TransactorKt.execSQL(r6, r102, this) != r0) goto L20;
                L19:
                    return r0;
                L22:
                    return Unit.INSTANCE;
                L7:
                    Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                    return null;
                L9:
                    TransactionScope r13 = (TransactionScope) this.L$0;
                    ResultKt.throwOnFailure(r10);
                L15:
                    String[] r103 = this.$tableNames;
                    r6 = r13;
                    r12 = r103.length;
                    r4 = 0;
                    r5 = r103;
                    goto L16
                L10:
                    ResultKt.throwOnFailure(r10);
                    r13 = (TransactionScope) this.L$0;
                    if (this.$hasForeignKeys == false) goto L15;
                    this.L$0 = r13;
                    this.label = 1;
                    if (TransactorKt.execSQL(r13, "PRAGMA defer_foreign_keys = TRUE", this) != r0) goto L15;
                    goto L15
                }
            }

            public C00071(RoomDatabase r1, boolean r2, String[] r3, Continuation<? super C00071> r4) {
                this.this$0 = r1;
                this.$hasForeignKeys = r2;
                this.$tableNames = r3;
                super(2, r4);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
                C00071 r0 = new C00071(this.this$0, this.$hasForeignKeys, this.$tableNames, r5);
                r0.L$0 = r4;
                return r0;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(Transactor r1, Continuation<? super Unit> r2) {
                return ((C00071) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Transactor r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r8) {
                Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: goto L12;
                    case 1: goto L11;
                    case 2: goto L10;
                    case 3: goto L9;
                    case 4: goto L8;
                    case 5: goto L7;
                    case 6: goto L6;
                    default: goto L4;
                };
            L4:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            L6:
                ResultKt.throwOnFailure(r8);
            L35:
                this.this$0.getInvalidationTracker().refreshAsync();
            L37:
                return Unit.INSTANCE;
            L7:
                Transactor r1 = (Transactor) this.L$0;
                ResultKt.throwOnFailure(r8);
            L32:
                this.L$0 = null;
                this.label = 6;
                if (TransactorKt.execSQL(r1, "VACUUM", this) != r0) goto L35;
            L34:
                return r0;
            L8:
                r1 = (Transactor) this.L$0;
                ResultKt.throwOnFailure(r8);
            L28:
                if (((Boolean) r8).booleanValue() == true) goto L37;
                this.L$0 = r1;
                this.label = 5;
                if (TransactorKt.execSQL(r1, "PRAGMA wal_checkpoint(FULL)", this) != r0) goto L32;
            L9:
                r1 = (Transactor) this.L$0;
                ResultKt.throwOnFailure(r8);
            L24:
                this.L$0 = r1;
                this.label = 4;
                r8 = r1.inTransaction(this);
                if (r8 != r0) goto L28;
            L10:
                r1 = (Transactor) this.L$0;
                ResultKt.throwOnFailure(r8);
            L21:
                Transactor.SQLiteTransactionType r82 = Transactor.SQLiteTransactionType.IMMEDIATE;
                C00081 r3 = new C00081(this.$hasForeignKeys, this.$tableNames, null);
                this.L$0 = r1;
                this.label = 3;
                if (r1.withTransaction(r82, r3, this) != r0) goto L24;
            L11:
                r1 = (Transactor) this.L$0;
                ResultKt.throwOnFailure(r8);
            L17:
                if (((Boolean) r8).booleanValue() == true) goto L21;
                InvalidationTracker r83 = this.this$0.getInvalidationTracker();
                this.L$0 = r1;
                this.label = 2;
                if (r83.sync$room_runtime(this) != r0) goto L21;
            L12:
                ResultKt.throwOnFailure(r8);
                Transactor r84 = (Transactor) this.L$0;
                this.L$0 = r84;
                this.label = 1;
                Object r12 = r84.inTransaction(this);
                if (r12 == r0) goto L34;
                r1 = r84;
                r8 = r12;
                goto L17
            }
        }

        public AnonymousClass1(RoomDatabase r1, boolean r2, String[] r3, Continuation<? super AnonymousClass1> r4) {
            this.this$0 = r1;
            this.$hasForeignKeys = r2;
            this.$tableNames = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new AnonymousClass1(this.this$0, this.$hasForeignKeys, this.$tableNames, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r8);
        L15:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r8);
            RoomConnectionManager r82 = RoomDatabase.access$getConnectionManager$p(this.this$0);
            if (r82 != null) goto L11;
            r82 = null;
        L11:
            C00071 r12 = new C00071(this.this$0, this.$hasForeignKeys, this.$tableNames, null);
            this.label = 1;
            if (r82.useConnection(false, r12, this) != r0) goto L15;
            return r0;
        }
    }

    /* renamed from: $r8$lambda$6MGYE43iSeDShiNg5-eYEczLGjs, reason: not valid java name */
    public static Object m943$r8$lambda$6MGYE43iSeDShiNg5eYEczLGjs(Function0 r0, SQLiteConnection r1) {
        return r0.invoke();
    }

    /* renamed from: $r8$lambda$A2u6_8X-j1AdhDIbxnjbR7Trwkg, reason: not valid java name */
    public static Object m944$r8$lambda$A2u6_8Xj1AdhDIbxnjbR7Trwkg(Callable r0) {
        return r0.call();
    }

    /* renamed from: $r8$lambda$POcKqU42ARGeTbGQYNjzs2-OAdI, reason: not valid java name */
    public static SupportSQLiteOpenHelper m945$r8$lambda$POcKqU42ARGeTbGQYNjzs2OAdI(RoomDatabase r0, DatabaseConfiguration r1) {
        return r0.createOpenHelper(r1);
    }

    public static Unit $r8$lambda$bNnW08bMruDX4DuvntvGeRm0ICM(Runnable r0) {
        r0.run();
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public RoomDatabase() {
        this.closeBarrier = new CloseBarrier(new RoomDatabase$closeBarrier$1(this));
        this.suspendingTransactionContext = new ThreadLocal();
        this.typeConverters = new LinkedHashMap();
        this.useTempTrackingTable = true;
    }

    public static final /* synthetic */ RoomConnectionManager access$getConnectionManager$p(RoomDatabase r0) {
        return r0.connectionManager;
    }

    public static final /* synthetic */ void access$onClosed(RoomDatabase r0) {
        r0.onClosed();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This property is always null and will be removed in a future version.")
    public static /* synthetic */ void getMCallbacks$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This property is always null and will be removed in a future version.")
    public static /* synthetic */ void getMDatabase$annotations() {
    }

    private final void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase r0 = getOpenHelper().getWritableDatabase();
        if (r0.inTransaction() == true) goto L6;
        getInvalidationTracker().syncBlocking$room_runtime();
    L6:
        if (r0.isWriteAheadLoggingEnabled() == false) goto L9;
        r0.beginTransactionNonExclusive();
        return;
    L9:
        r0.beginTransaction();
    }

    private final void internalEndTransaction() {
        getOpenHelper().getWritableDatabase().endTransaction();
        if (inTransaction() == true) goto L6;
        getInvalidationTracker().refreshVersionsAsync();
        return;
    }

    private final boolean isThreadInSuspendingTransaction() {
        CoroutineContext r1 = this.suspendingTransactionContext.get();
        if (r1 == null) goto L5;
        TransactionElement r12 = (TransactionElement) r1.get(TransactionElement.Key);
    L6:
        if (r12 == null) goto L9;
        return true;
    L9:
        return false;
    L5:
        r12 = null;
        goto L6
    }

    private final void onClosed() {
        CoroutineScope r0 = this.coroutineScope;
        RoomConnectionManager r1 = null;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        CoroutineScopeKt.cancel$default(r0, null, 1, null);
        getInvalidationTracker().stop$room_runtime();
        RoomConnectionManager r3 = this.connectionManager;
        if (r3 == null) goto L9;
        r1 = r3;
    L9:
        r1.close();
    }

    public static /* synthetic */ Cursor query$default(RoomDatabase r0, SupportSQLiteQuery r1, CancellationSignal r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: query");
        return null;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = null;
    L7:
        return r0.query(r1, r2);
    }

    private final <T> T runInTransaction(Function0<? extends T> r3) {
        if (inCompatibilityMode() == false) goto L12;
        beginTransaction();
        T r32 = r3.invoke();     // Catch: Throwable -> L8
        setTransactionSuccessful();     // Catch: Throwable -> L8
        endTransaction();
        return r32;
    L8:
        th = move-exception;
        endTransaction();
        throw th;
    L12:
        return (T) DBUtil.performBlocking(this, false, true, new RoomDatabase$$ExternalSyntheticLambda3(r3));
    }

    public final void addTypeConverter$room_runtime(KClass<?> r1, Object r2) {
        this.typeConverters.put(r1, r2);
    }

    public void assertNotMainThread() {
        if (this.allowMainThreadQueries == false) goto L6;
        return;
    L6:
        if (isMainThread$room_runtime() == true) goto L8;
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
    }

    public void assertNotSuspendingTransaction() {
        if (inCompatibilityMode() == true) goto L5;
        return;
    L5:
        if (inTransaction() == false) goto L7;
        return;
    L7:
        if (isThreadInSuspendingTransaction() == false) goto L13;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        return;
    }

    @Deprecated(message = "beginTransaction() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void beginTransaction() {
        assertNotMainThread();
        internalBeginTransaction();
    }

    public abstract void clearAllTables();

    public void close() {
        this.closeBarrier.close$room_runtime();
    }

    public SupportSQLiteStatement compileStatement(String r1) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().compileStatement(r1);
    }

    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> r4) {
        LinkedHashMap r0 = new LinkedHashMap(MapsKt.mapCapacity(r4.size()));
        Iterator<T> r42 = r4.entrySet().iterator();
    L4:
        if (r42.hasNext() == false) goto L7;
        Map.Entry r1 = (Map.Entry) r42.next();
        r0.put(JvmClassMappingKt.getJavaClass((KClass) r1.getKey()), r1.getValue());
        goto L4
    L7:
        return getAutoMigrations(r0);
    }

    public final RoomConnectionManager createConnectionManager$room_runtime(DatabaseConfiguration r4) {
        RoomOpenDelegate r0 = (RoomOpenDelegate) createOpenDelegate();     // Catch: NotImplementedError -> L4
    L5:
        if (r0 != null) goto L8;
        return new RoomConnectionManager(r4, new RoomDatabase$$ExternalSyntheticLambda1(this), new RoomDatabase$createConnectionManager$2(this));
    L8:
        return new RoomConnectionManager(r4, r0, new RoomDatabase$createConnectionManager$3(this));
    L4:
        r0 = null;
        goto L5
    }

    public abstract InvalidationTracker createInvalidationTracker();

    public RoomOpenDelegateMarker createOpenDelegate() {
        throw new NotImplementedError(null, 1, null);
    }

    @Deprecated(message = "No longer implemented by generated")
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration r2) {
        throw new NotImplementedError(null, 1, null);
    }

    @Deprecated(message = "endTransaction() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void endTransaction() {
        internalEndTransaction();
    }

    @Deprecated(message = "No longer implemented by generated")
    @JvmSuppressWildcards
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> r1) {
        return CollectionsKt.emptyList();
    }

    public final CloseBarrier getCloseBarrier$room_runtime() {
        return this.closeBarrier;
    }

    public final CoroutineScope getCoroutineScope() {
        CoroutineScope r0 = this.coroutineScope;
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0;
    }

    public final SQLiteDriver getDriver() {
        DatabaseConfiguration r1 = this.configuration;
        if (r1 != null) goto L5;
        r1 = null;
    L5:
        SQLiteDriver r12 = r1.sqliteDriver;
        if (r12 == null) goto L8;
        return r12;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("No SQLiteDriver was configured with Room.");
        return null;
    }

    public InvalidationTracker getInvalidationTracker() {
        InvalidationTracker r0 = this.internalTracker;
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0;
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        RoomConnectionManager r1 = this.connectionManager;
        if (r1 != null) goto L5;
        r1 = null;
    L5:
        SupportSQLiteOpenHelper r12 = r1.getSupportOpenHelper$room_runtime();
        if (r12 == null) goto L8;
        return r12;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot return a SupportSQLiteOpenHelper since no SupportSQLiteOpenHelper.Factory was configured with Room.");
        return null;
    }

    public final String getPath() {
        DatabaseConfiguration r2 = this.configuration;
        if (r2 != null) goto L5;
        DatabaseConfiguration r1 = null;
    L6:
        String r12 = r1.name;
        if (r12 == null) goto L12;
        if (r2 != null) goto L11;
        r2 = null;
    L11:
        return r2.context.getDatabasePath(r12).getPath();
    L12:
        return null;
    L5:
        r1 = r2;
        goto L6
    }

    public final CoroutineContext getQueryContext() {
        CoroutineScope r0 = this.coroutineScope;
        if (r0 != null) goto L6;
        r0 = null;
    L6:
        return r0.getCoroutineContext();
    }

    public Executor getQueryExecutor() {
        Executor r0 = this.internalQueryExecutor;
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0;
    }

    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        Set<Class<? extends AutoMigrationSpec>> r2 = getRequiredAutoMigrationSpecs();
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r2, 10));
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        r0.add(JvmClassMappingKt.getKotlinClass((Class) r22.next()));
        goto L4
    L7:
        return CollectionsKt.toSet(r0);
    }

    @Deprecated(message = "No longer implemented by generated")
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return SetsKt.emptySet();
    }

    public Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        Set<Map.Entry<Class<?>, List<Class<?>>>> r6 = getRequiredTypeConverters().entrySet();
        LinkedHashMap r2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r6, 10)), 16));
        Iterator<T> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L10;
        Map.Entry r1 = (Map.Entry) r62.next();
        Class r3 = (Class) r1.getKey();
        List r12 = (List) r1.getValue();
        KClass r32 = JvmClassMappingKt.getKotlinClass(r3);
        List r13 = r12;
        ArrayList r4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r13, 10));
        Iterator r14 = r13.iterator();
    L7:
        if (r14.hasNext() == false) goto L9;
        r4.add(JvmClassMappingKt.getKotlinClass((Class) r14.next()));
        goto L7
    L9:
        Pair r15 = TuplesKt.to(r32, r4);
        r2.put(r15.getFirst(), r15.getSecond());
        goto L4
    L10:
        return r2;
    }

    public final Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClassesMap$room_runtime() {
        return getRequiredTypeConverterClasses();
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return MapsKt.emptyMap();
    }

    public final ThreadLocal<CoroutineContext> getSuspendingTransactionContext() {
        return this.suspendingTransactionContext;
    }

    public final CoroutineContext getTransactionContext$room_runtime() {
        CoroutineContext r0 = this.transactionContext;
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0;
    }

    public Executor getTransactionExecutor() {
        Executor r0 = this.internalTransactionExecutor;
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0;
    }

    @Deprecated(message = "No longer called by generated implementation")
    public <T> T getTypeConverter(Class<T> r1) {
        return (T) this.typeConverters.get(JvmClassMappingKt.getKotlinClass(r1));
    }

    public final <T> T getTypeConverter(KClass<T> r1) {
        return (T) this.typeConverters.get(r1);
    }

    public final boolean getUseTempTrackingTable$room_runtime() {
        return this.useTempTrackingTable;
    }

    public final boolean inCompatibilityMode() {
        RoomConnectionManager r0 = this.connectionManager;
        if (r0 != null) goto L6;
        r0 = null;
    L6:
        if (r0.getSupportOpenHelper$room_runtime() == null) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean inTransaction() {
        if (isOpenInternal$room_runtime() == true) goto L5;
        return false;
    L5:
        if (getOpenHelper().getWritableDatabase().inTransaction() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void init(DatabaseConfiguration r6) {
        this.configuration = r6;
        this.useTempTrackingTable = r6.getUseTempTrackingTable$room_runtime();
        this.connectionManager = createConnectionManager$room_runtime(r6);
        this.internalTracker = createInvalidationTracker();
        RoomDatabaseKt.validateAutoMigrations(this, r6);
        RoomDatabaseKt.validateTypeConverters(this, r6);
        CoroutineContext r0 = r6.queryCoroutineContext;
        CoroutineScope r2 = null;
        if (r0 == null) goto L16;
        CoroutineDispatcher r02 = (CoroutineDispatcher) r0.get(ContinuationInterceptor.Key);
        Executor r3 = ExecutorsKt.asExecutor(r02);
        this.internalQueryExecutor = r3;
        if (r3 != null) goto L7;
        r3 = null;
    L7:
        this.internalTransactionExecutor = new TransactionExecutor(r3);
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(r6.queryCoroutineContext.plus(SupervisorKt.SupervisorJob((Job) r6.queryCoroutineContext.get(Job.Key))));
        boolean r32 = inCompatibilityMode();
        CoroutineScope r4 = this.coroutineScope;
        if (r32 == false) goto L12;
        if (r4 != null) goto L11;
        r4 = null;
    L11:
        CoroutineContext r03 = r4.getCoroutineContext().plus(r02.limitedParallelism(1));
    L15:
        this.transactionContext = r03;
    L26:
        this.allowMainThreadQueries = r6.allowMainThreadQueries;
        RoomConnectionManager r04 = this.connectionManager;
        if (r04 != null) goto L29;
        r04 = null;
    L29:
        SupportSQLiteOpenHelper r05 = r04.getSupportOpenHelper$room_runtime();
        if (r05 != null) goto L33;
    L31:
        r05 = null;
    L38:
        PrePackagedCopyOpenHelper r06 = (PrePackagedCopyOpenHelper) r05;
        if (r06 == null) goto L41;
        r06.setDatabaseConfiguration(r6);
    L41:
        RoomConnectionManager r07 = this.connectionManager;
        if (r07 != null) goto L44;
        r07 = null;
    L44:
        SupportSQLiteOpenHelper r08 = r07.getSupportOpenHelper$room_runtime();
        if (r08 != null) goto L48;
    L46:
        r08 = null;
    L53:
        AutoClosingRoomOpenHelper r09 = (AutoClosingRoomOpenHelper) r08;
        if (r09 == null) goto L61;
        this.autoCloser = r09.getAutoCloser$room_runtime();
        AutoCloser r1 = r09.getAutoCloser$room_runtime();
        CoroutineScope r33 = this.coroutineScope;
        if (r33 == null) goto L59;
        r2 = r33;
    L59:
        r1.initCoroutineScope(r2);
        getInvalidationTracker().setAutoCloser$room_runtime(r09.getAutoCloser$room_runtime());
    L61:
        if (r6.multiInstanceInvalidationServiceIntent != null) goto L63;
        return;
    L63:
        if (r6.name == null) goto L66;
        getInvalidationTracker().initMultiInstanceInvalidation$room_runtime(r6.context, r6.name, r6.multiInstanceInvalidationServiceIntent);
        return;
    L66:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L48:
        if ((r08 instanceof AutoClosingRoomOpenHelper) == true) goto L53;
        if ((r08 instanceof DelegatingOpenHelper) == false) goto L46;
        r08 = ((DelegatingOpenHelper) r08).getDelegate();
    L33:
        if ((r05 instanceof PrePackagedCopyOpenHelper) == true) goto L38;
        if ((r05 instanceof DelegatingOpenHelper) == false) goto L31;
        r05 = ((DelegatingOpenHelper) r05).getDelegate();
        goto L33
    L12:
        if (r4 != null) goto L14;
        r4 = null;
    L14:
        r03 = r4.getCoroutineContext();
        goto L15
    L16:
        this.internalQueryExecutor = r6.queryExecutor;
        this.internalTransactionExecutor = new TransactionExecutor(r6.transactionExecutor);
        Executor r010 = this.internalQueryExecutor;
        if (r010 != null) goto L19;
        r010 = null;
    L19:
        CoroutineScope r011 = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(r010).plus(SupervisorKt.SupervisorJob$default(null, 1, null)));
        this.coroutineScope = r011;
        if (r011 != null) goto L22;
        r011 = null;
    L22:
        CoroutineContext r012 = r011.getCoroutineContext();
        Executor r12 = this.internalTransactionExecutor;
        if (r12 != null) goto L25;
        r12 = null;
    L25:
        this.transactionContext = r012.plus(ExecutorsKt.from(r12));
        goto L26
    }

    public final void internalInitInvalidationTracker(SQLiteConnection r1) {
        getInvalidationTracker().internalInit$room_runtime(r1);
    }

    @Deprecated(message = "No longer called by generated")
    public void internalInitInvalidationTracker(SupportSQLiteDatabase r2) {
        internalInitInvalidationTracker(new SupportSQLiteConnection(r2));
    }

    public final boolean isMainThread$room_runtime() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isOpen() {
        AutoCloser r0 = this.autoCloser;
        if (r0 != null) goto L5;
        RoomConnectionManager r1 = this.connectionManager;
        if (r1 != null) goto L10;
        r1 = null;
    L10:
        return r1.isSupportDatabaseOpen();
    L5:
        return r0.isActive();
    }

    public final boolean isOpenInternal$room_runtime() {
        AutoCloser r0 = this.autoCloser;
        if (r0 == null) goto L10;
        SupportSQLiteDatabase r1 = r0.getDelegateDatabase$room_runtime();
        if (r1 != null) goto L7;
        return false;
    L7:
        return r1.isOpen();
    L10:
        RoomConnectionManager r12 = this.connectionManager;
        if (r12 != null) goto L14;
        r12 = null;
    L14:
        return r12.isSupportDatabaseOpen();
    }

    public final void performClear(boolean r3, String... r4) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        RunBlockingUninterruptible_androidKt.runBlockingUninterruptible(new AnonymousClass1(this, r3, r4, null));
    }

    @JvmOverloads
    public final Cursor query(SupportSQLiteQuery r3) {
        return query$default(this, r3, null, 2, null);
    }

    @JvmOverloads
    public Cursor query(SupportSQLiteQuery r1, CancellationSignal r2) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (r2 == null) goto L7;
        return getOpenHelper().getWritableDatabase().query(r1, r2);
    L7:
        return getOpenHelper().getWritableDatabase().query(r1);
    }

    public Cursor query(String r2, Object[] r3) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().query(new SimpleSQLiteQuery(r2, r3));
    }

    public <V> V runInTransaction(Callable<V> r2) {
        return (V) runInTransaction(new RoomDatabase$$ExternalSyntheticLambda2(r2));
    }

    public void runInTransaction(Runnable r2) {
        runInTransaction(new RoomDatabase$$ExternalSyntheticLambda0(r2));
    }

    @Deprecated(message = "setTransactionSuccessful() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void setTransactionSuccessful() {
        getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    public final void setUseTempTrackingTable$room_runtime(boolean r1) {
        this.useTempTrackingTable = r1;
    }

    public final <R> Object useConnection(boolean r1, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r2, Continuation<? super R> r3) {
        RoomConnectionManager r0 = this.connectionManager;
        if (r0 != null) goto L6;
        r0 = null;
    L6:
        return r0.useConnection(r1, r2, r3);
    }
}
