package androidx.room;

import androidx.room.concurrent.CloseBarrier;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 V2\u00020\u0001:\u0001VBo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u0004\u0012\u000e\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0018\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\tH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J \u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000eH\u0082@¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000eH\u0082@¢\u0006\u0004\b\u001b\u0010\u001aJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007H\u0082@¢\u0006\u0004\b\u001c\u0010\u001dJ\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0082@¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010!\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020 ¢\u0006\u0004\b!\u0010\"J9\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u000bH\u0000¢\u0006\u0004\b(\u0010)J1\u0010.\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u0004\u0012\u00020$0+2\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\tH\u0000¢\u0006\u0004\b,\u0010-J\u0017\u00101\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020$H\u0000¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020$H\u0000¢\u0006\u0004\b2\u00100J\u0010\u00105\u001a\u00020\u000fH\u0080@¢\u0006\u0004\b4\u0010\u001dJ/\u0010;\u001a\u00020\u000f2\u000e\b\u0002\u00107\u001a\b\u0012\u0004\u0012\u00020\u000f062\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u000f06H\u0000¢\u0006\u0004\b9\u0010:J\u000f\u0010>\u001a\u00020\u000fH\u0000¢\u0006\u0004\b<\u0010=R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010?R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010@R&\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010@R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010AR&\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\u0004\u0012\u00020\u000f0\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010BR \u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010@R\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00050\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010N\u001a\u00060Lj\u0002`M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR(\u0010P\u001a\b\u0012\u0004\u0012\u00020\u000b068\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010S\"\u0004\bT\u0010U¨\u0006W"}, d2 = {"Landroidx/room/TriggerBasedInvalidationTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/RoomDatabase;", "database", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "shadowTablesMap", _UrlKt.FRAGMENT_ENCODE_SET, "viewTables", _UrlKt.FRAGMENT_ENCODE_SET, "tableNames", _UrlKt.FRAGMENT_ENCODE_SET, "useTempTable", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "onInvalidatedTablesIds", "<init>", "(Landroidx/room/RoomDatabase;Ljava/util/Map;Ljava/util/Map;[Ljava/lang/String;ZLkotlin/jvm/functions/Function1;)V", "names", "resolveViews", "([Ljava/lang/String;)[Ljava/lang/String;", "Landroidx/room/PooledConnection;", "connection", "tableId", "startTrackingTable", "(Landroidx/room/PooledConnection;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopTrackingTable", "notifyInvalidation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkInvalidatedTables", "(Landroidx/room/PooledConnection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/sqlite/SQLiteConnection;", "configureConnection", "(Landroidx/sqlite/SQLiteConnection;)V", "resolvedTableNames", _UrlKt.FRAGMENT_ENCODE_SET, "tableIds", "emitInitialState", "Lkotlinx/coroutines/flow/Flow;", "createFlow$room_runtime", "([Ljava/lang/String;[IZ)Lkotlinx/coroutines/flow/Flow;", "createFlow", "Lkotlin/Pair;", "validateTableNames$room_runtime", "([Ljava/lang/String;)Lkotlin/Pair;", "validateTableNames", "onObserverAdded$room_runtime", "([I)Z", "onObserverAdded", "onObserverRemoved$room_runtime", "onObserverRemoved", "syncTriggers$room_runtime", "syncTriggers", "Lkotlin/Function0;", "onRefreshScheduled", "onRefreshCompleted", "refreshInvalidationAsync$room_runtime", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "refreshInvalidationAsync", "resetSync$room_runtime", "()V", "resetSync", "Landroidx/room/RoomDatabase;", "Ljava/util/Map;", "Z", "Lkotlin/jvm/functions/Function1;", "tableIdLookup", "tablesNames", "[Ljava/lang/String;", "Landroidx/room/ObservedTableStates;", "observedTableStates", "Landroidx/room/ObservedTableStates;", "Landroidx/room/ObservedTableVersions;", "observedTableVersions", "Landroidx/room/ObservedTableVersions;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Landroidx/room/concurrent/AtomicBoolean;", "pendingRefresh", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onAllowRefresh", "Lkotlin/jvm/functions/Function0;", "getOnAllowRefresh$room_runtime", "()Lkotlin/jvm/functions/Function0;", "setOnAllowRefresh$room_runtime", "(Lkotlin/jvm/functions/Function0;)V", "Companion", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInvalidationTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 6 CloseBarrier.kt\nandroidx/room/concurrent/CloseBarrierKt\n*L\n1#1,640:1\n216#2,2:641\n13472#3:643\n13473#3:645\n12667#3,2:657\n1#4:644\n37#5:646\n36#5,3:647\n99#6,7:650\n99#6,5:659\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker\n*L\n191#1:641,2\n289#1:643\n289#1:645\n374#1:657,2\n293#1:646\n293#1:647,3\n305#1:650,7\n407#1:659,5\n*E\n"})
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker {
    public static final Companion Companion = null;
    private static final String[] TRIGGERS = null;
    private final RoomDatabase database;
    private final ObservedTableStates observedTableStates;
    private final ObservedTableVersions observedTableVersions;
    private Function0<Boolean> onAllowRefresh;
    private final Function1<Set<Integer>, Unit> onInvalidatedTablesIds;
    private final AtomicBoolean pendingRefresh;
    private final Map<String, String> shadowTablesMap;
    private final Map<String, Integer> tableIdLookup;
    private final String[] tablesNames;
    private final boolean useTempTable;
    private final Map<String, Set<String>> viewTables;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/room/TriggerBasedInvalidationTracker$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TRIGGERS", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/String;", "UPDATE_TABLE_NAME", "TABLE_ID_COLUMN_NAME", "INVALIDATED_COLUMN_NAME", "CREATE_TRACKING_TABLE_SQL", "DROP_TRACKING_TABLE_SQL", "SELECT_UPDATED_TABLES_SQL", "RESET_UPDATED_TABLES_SQL", "getTriggerName", "tableName", "triggerType", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ String access$getTriggerName(Companion r0, String r1, String r2) {
            return r0.getTriggerName(r1, r2);
        }

        private final String getTriggerName(String r2, String r3) {
            return "room_table_modification_trigger_" + r2 + '_' + r3;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0, 1}, l = {445, 453}, m = "checkInvalidatedTables", n = {"connection", "invalidatedTableIds"}, s = {"L$0", "L$0"})
    /* renamed from: androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ TriggerBasedInvalidationTracker this$0;

        public AnonymousClass1(TriggerBasedInvalidationTracker r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return TriggerBasedInvalidationTracker.access$checkInvalidatedTables(this.this$0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0}, l = {417}, m = "notifyInvalidation", n = {"$this$ifNotClosed$iv"}, s = {"L$0"})
    /* renamed from: androidx.room.TriggerBasedInvalidationTracker$notifyInvalidation$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01181 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ TriggerBasedInvalidationTracker this$0;

        public C01181(TriggerBasedInvalidationTracker r1, Continuation<? super C01181> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return TriggerBasedInvalidationTracker.access$notifyInvalidation(this.this$0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0, 0, 1, 1, 1}, l = {328, 333}, m = "startTrackingTable", n = {"connection", "tableId", "connection", "tableName", "tableId"}, s = {"L$0", "I$0", "L$0", "L$1", "I$0"})
    /* renamed from: androidx.room.TriggerBasedInvalidationTracker$startTrackingTable$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01191 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ TriggerBasedInvalidationTracker this$0;

        public C01191(TriggerBasedInvalidationTracker r1, Continuation<? super C01191> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return TriggerBasedInvalidationTracker.access$startTrackingTable(this.this$0, null, 0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0, 0}, l = {347}, m = "stopTrackingTable", n = {"connection", "tableName"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01201 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ TriggerBasedInvalidationTracker this$0;

        public C01201(TriggerBasedInvalidationTracker r1, Continuation<? super C01201> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return TriggerBasedInvalidationTracker.access$stopTrackingTable(this.this$0, null, 0, this);
        }
    }

    public static Set $r8$lambda$NRZHa3RQXpIcgcxW1BMkNDbO3A8(SQLiteStatement r3) {
        Set r0 = SetsKt.createSetBuilder();
    L4:
        if (r3.step() == false) goto L7;
        r0.add(Integer.valueOf((int) r3.getLong(0)));
        goto L4
    L7:
        return SetsKt.build(r0);
    }

    public static boolean $r8$lambda$cM81ONhjXKWpAvdfyxzSR0BlDDY() {
        return true;
    }

    static {
        Companion = new Companion(null);
        TRIGGERS = new String[]{"INSERT", "UPDATE", "DELETE"};
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TriggerBasedInvalidationTracker(RoomDatabase r3, Map<String, String> r4, Map<String, ? extends Set<String>> r5, String[] r6, boolean r7, Function1<? super Set<Integer>, Unit> r8) {
        this.database = r3;
        this.shadowTablesMap = r4;
        this.viewTables = r5;
        this.useTempTable = r7;
        this.onInvalidatedTablesIds = r8;
        int r42 = 0;
        this.pendingRefresh = new AtomicBoolean(false);
        this.onAllowRefresh = new TriggerBasedInvalidationTracker$$ExternalSyntheticLambda1();
        this.tableIdLookup = new LinkedHashMap();
        int r32 = r6.length;
        String[] r52 = new String[r32];
    L3:
        if (r42 >= r32) goto L12;
        String r72 = r6[r42];
        Locale r82 = Locale.ROOT;
        String r73 = r72.toLowerCase(r82);
        this.tableIdLookup.put(r73, Integer.valueOf(r42));
        String r0 = this.shadowTablesMap.get(r6[r42]);
        if (r0 == null) goto L7;
        String r83 = r0.toLowerCase(r82);
    L8:
        if (r83 == null) goto L11;
        r73 = r83;
    L11:
        r52[r42] = r73;
        r42 = r42 + 1;
        goto L3
    L7:
        r83 = null;
        goto L8
    L12:
        this.tablesNames = r52;
        Iterator<Map.Entry<String, String>> r33 = this.shadowTablesMap.entrySet().iterator();
    L14:
        if (r33.hasNext() == false) goto L18;
        Map.Entry<String, String> r43 = r33.next();
        String r53 = r43.getValue();
        Locale r62 = Locale.ROOT;
        String r54 = r53.toLowerCase(r62);
        if (this.tableIdLookup.containsKey(r54) == false) goto L14;
        String r44 = r43.getKey().toLowerCase(r62);
        Map<String, Integer> r63 = this.tableIdLookup;
        r63.put(r44, MapsKt.getValue(r63, r54));
        goto L14
    L18:
        this.observedTableStates = new ObservedTableStates(this.tablesNames.length);
        this.observedTableVersions = new ObservedTableVersions(this.tablesNames.length);
    }

    public static final /* synthetic */ Object access$checkInvalidatedTables(TriggerBasedInvalidationTracker r0, PooledConnection r1, Continuation r2) {
        return r0.checkInvalidatedTables(r1, r2);
    }

    public static final /* synthetic */ RoomDatabase access$getDatabase$p(TriggerBasedInvalidationTracker r0) {
        return r0.database;
    }

    public static final /* synthetic */ ObservedTableStates access$getObservedTableStates$p(TriggerBasedInvalidationTracker r0) {
        return r0.observedTableStates;
    }

    public static final /* synthetic */ ObservedTableVersions access$getObservedTableVersions$p(TriggerBasedInvalidationTracker r0) {
        return r0.observedTableVersions;
    }

    public static final /* synthetic */ Object access$notifyInvalidation(TriggerBasedInvalidationTracker r0, Continuation r1) {
        return r0.notifyInvalidation(r1);
    }

    public static final /* synthetic */ Object access$startTrackingTable(TriggerBasedInvalidationTracker r0, PooledConnection r1, int r2, Continuation r3) {
        return r0.startTrackingTable(r1, r2, r3);
    }

    public static final /* synthetic */ Object access$stopTrackingTable(TriggerBasedInvalidationTracker r0, PooledConnection r1, int r2, Continuation r3) {
        return r0.stopTrackingTable(r1, r2, r3);
    }

    private final Object checkInvalidatedTables(PooledConnection r5, Continuation<? super Set<Integer>> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.result;
        Object r62 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        if (r12 == 0) goto L17;
        if (r12 == 1) goto L16;
        if (r12 != 2) goto L14;
        Set r52 = (Set) r0.L$0;
        ResultKt.throwOnFailure(r4);
        return r52;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        r5 = (PooledConnection) r0.L$0;
        ResultKt.throwOnFailure(r4);
    L20:
        Set r42 = (Set) r4;
        if (r42.isEmpty() == true) goto L25;
        r0.L$0 = r42;
        r0.label = 2;
        if (TransactorKt.execSQL(r5, "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1", r0) != r62) goto L25;
    L24:
        return r62;
    L25:
        return r42;
    L17:
        ResultKt.throwOnFailure(r4);
        TriggerBasedInvalidationTracker$$ExternalSyntheticLambda0 r43 = new TriggerBasedInvalidationTracker$$ExternalSyntheticLambda0();
        r0.L$0 = r5;
        r0.label = 1;
        r4 = r5.usePrepared("SELECT * FROM room_table_modification_log WHERE invalidated = 1", r43, r0);
        if (r4 != r62) goto L20;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }

    private final Object notifyInvalidation(Continuation<? super Set<Integer>> r9) {
        if ((r9 instanceof C01181) == false) goto L7;
        C01181 r0 = (C01181) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        CloseBarrier r02 = (CloseBarrier) r0.L$0;
        ResultKt.throwOnFailure(r92);     // Catch: Throwable -> L14
    L36:
        Set<Integer> r93 = (Set) r92;     // Catch: Throwable -> L14
        if (r93.isEmpty() == true) goto L39;
        this.observedTableVersions.increment(r93);     // Catch: Throwable -> L14
        this.onInvalidatedTablesIds.invoke(r93);     // Catch: Throwable -> L14
    L39:
        r02.unblock$room_runtime();
        return r93;
    L14:
        th = th;
    L41:
        r02.unblock$room_runtime();
        throw th;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r92);
        CloseBarrier r94 = this.database.getCloseBarrier$room_runtime();
        if (r94.block$room_runtime() == false) goto L44;
    L25:
        th = th;
        r02 = r94;
        goto L41
    L21:
        if (this.pendingRefresh.compareAndSet(true, false) == true) goto L28;
        Set r8 = SetsKt.emptySet();     // Catch: Throwable -> L25
        r94.unblock$room_runtime();
        return r8;
    L28:
        if (this.onAllowRefresh.invoke().booleanValue() == true) goto L32;
        Set r82 = SetsKt.emptySet();     // Catch: Throwable -> L25
        r94.unblock$room_runtime();
        return r82;
    L32:
        RoomDatabase r22 = this.database;     // Catch: Throwable -> L25
        TriggerBasedInvalidationTracker$notifyInvalidation$2$invalidatedTableIds$1 r6 = new TriggerBasedInvalidationTracker$notifyInvalidation$2$invalidatedTableIds$1(this, null);     // Catch: Throwable -> L25
        r0.L$0 = r94;     // Catch: Throwable -> L25
        r0.label = 1;     // Catch: Throwable -> L25
        Object r03 = r22.useConnection(false, r6, r0);     // Catch: Throwable -> L25
        if (r03 != r12) goto L35;
        return r12;
    L35:
        r02 = r94;
        r92 = r03;
        goto L36
    L44:
        return SetsKt.emptySet();
    L7:
        r0 = new C01181(this, r9);
        goto L8
    }

    private final String[] resolveViews(String[] r8) {
        Set r0 = SetsKt.createSetBuilder();
        int r1 = r8.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L10;
        String r4 = r8[r3];
        Set<String> r5 = this.viewTables.get(r4.toLowerCase(Locale.ROOT));
        if (r5 == null) goto L7;
        r0.addAll(r5);
    L8:
        r3 = r3 + 1;
        goto L3
    L7:
        r0.add(r4);
        goto L8
    L10:
        return (String[]) SetsKt.build(r0).toArray(new String[0]);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00d9 -> B:28:0x00dc). Please report as a decompilation issue!!! */
    private final Object startTrackingTable(PooledConnection r13, int r14, Continuation<? super Unit> r15) {
        if ((r15 instanceof C01191) == false) goto L7;
        C01191 r0 = (C01191) r15;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r152 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        int r132 = r0.I$2;
        int r142 = r0.I$1;
        int r22 = r0.I$0;
        String[] r5 = (String[]) r0.L$2;
        String r6 = (String) r0.L$1;
        PooledConnection r7 = (PooledConnection) r0.L$0;
        ResultKt.throwOnFailure(r152);
    L28:
        r142 = r142 + 1;
    L20:
        if (r142 >= r132) goto L30;
        String r153 = r5[r142];
        if (this.useTempTable == false) goto L24;
        String r8 = "TEMP";
    L25:
        String r154 = "CREATE " + r8 + " TRIGGER IF NOT EXISTS `" + Companion.access$getTriggerName(Companion, r6, r153) + "` AFTER " + r153 + " ON `" + r6 + "` BEGIN UPDATE room_table_modification_log SET invalidated = 1 WHERE table_id = " + r22 + " AND invalidated = 0; END";
        r0.L$0 = r7;
        r0.L$1 = r6;
        r0.L$2 = r5;
        r0.I$0 = r22;
        r0.I$1 = r142;
        r0.I$2 = r132;
        r0.label = 2;
        if (TransactorKt.execSQL(r7, r154, r0) != r12) goto L28;
    L27:
        return r12;
    L24:
        r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L25
    L30:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        r14 = r0.I$0;
        r13 = (PooledConnection) r0.L$0;
        ResultKt.throwOnFailure(r152);
    L19:
        String r155 = this.tablesNames[r14];
        String[] r23 = TRIGGERS;
        r7 = r13;
        r132 = r23.length;
        r5 = r23;
        r22 = r14;
        r142 = 0;
        r6 = r155;
        goto L20
    L16:
        ResultKt.throwOnFailure(r152);
        String r156 = "INSERT OR IGNORE INTO room_table_modification_log VALUES(" + r14 + ", 0)";
        r0.L$0 = r13;
        r0.I$0 = r14;
        r0.label = 1;
        if (TransactorKt.execSQL(r13, r156, r0) != r12) goto L19;
    L7:
        r0 = new C01191(this, r15);
        goto L8
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0080 -> B:19:0x0083). Please report as a decompilation issue!!! */
    private final Object stopTrackingTable(PooledConnection r9, int r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof C01201) == false) goto L7;
        C01201 r0 = (C01201) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        int r8 = r0.I$1;
        int r92 = r0.I$0;
        String[] r102 = (String[]) r0.L$2;
        String r22 = (String) r0.L$1;
        PooledConnection r4 = (PooledConnection) r0.L$0;
        ResultKt.throwOnFailure(r112);
        String[] r113 = r102;
        PooledConnection r103 = r4;
    L19:
        r92 = r92 + 1;
    L15:
        if (r92 >= r8) goto L21;
        String r42 = "DROP TRIGGER IF EXISTS `" + Companion.access$getTriggerName(Companion, r22, r113[r92]) + '`';
        r0.L$0 = r103;
        r0.L$1 = r22;
        r0.L$2 = r113;
        r0.I$0 = r92;
        r0.I$1 = r8;
        r0.label = 1;
        if (TransactorKt.execSQL(r103, r42, r0) != r12) goto L19;
        return r12;
    L21:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r112);
        String r82 = this.tablesNames[r10];
        String[] r104 = TRIGGERS;
        r22 = r82;
        r8 = r104.length;
        r113 = r104;
        r103 = r9;
        r92 = 0;
    L7:
        r0 = new C01201(this, r11);
        goto L8
    }

    public final void configureConnection(SQLiteConnection r8) {
        SQLiteStatement r1 = r8.prepare("PRAGMA query_only");
        r1.step();     // Catch: Throwable -> L12
        boolean r0 = r1.getBoolean(0);     // Catch: Throwable -> L12
        AutoCloseableKt.closeFinally(r1, null);
        if (r0 == true) goto L22;
        SQLite.execSQL(r8, "PRAGMA temp_store = MEMORY");
        SQLite.execSQL(r8, "PRAGMA recursive_triggers = 1");
        SQLite.execSQL(r8, "DROP TABLE IF EXISTS room_table_modification_log");
        if (this.useTempTable == false) goto L9;
        SQLite.execSQL(r8, "CREATE TEMP TABLE IF NOT EXISTS room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
    L10:
        this.observedTableStates.forceNeedSync$room_runtime();
        return;
    L9:
        SQLite.execSQL(r8, StringsKt.replace$default("CREATE TEMP TABLE IF NOT EXISTS room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)", "TEMP", _UrlKt.FRAGMENT_ENCODE_SET, false, 4, null));
        goto L10
    L22:
        return;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    }

    public final Flow<Set<String>> createFlow$room_runtime(String[] r7, int[] r8, boolean r9) {
        return FlowKt.flow(new TriggerBasedInvalidationTracker$createFlow$1(this, r8, r9, r7, null));
    }

    public final boolean onObserverAdded$room_runtime(int[] r1) {
        return this.observedTableStates.onObserverAdded$room_runtime(r1);
    }

    public final boolean onObserverRemoved$room_runtime(int[] r1) {
        return this.observedTableStates.onObserverRemoved$room_runtime(r1);
    }

    public final void refreshInvalidationAsync$room_runtime(Function0<Unit> r7, Function0<Unit> r8) {
        if (this.pendingRefresh.compareAndSet(false, true) == false) goto L6;
        r7.invoke();
        BuildersKt.launch$default(this.database.getCoroutineScope(), new CoroutineName("Room Invalidation Tracker Refresh"), null, new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(this, r8, null), 2, null);
        return;
    }

    public final void resetSync$room_runtime() {
        this.observedTableStates.resetTriggerState$room_runtime();
    }

    public final void setOnAllowRefresh$room_runtime(Function0<Boolean> r1) {
        this.onAllowRefresh = r1;
    }

    public final Object syncTriggers$room_runtime(Continuation<? super Unit> r8) {
        if ((r8 instanceof TriggerBasedInvalidationTracker$syncTriggers$1) == false) goto L7;
        TriggerBasedInvalidationTracker$syncTriggers$1 r0 = (TriggerBasedInvalidationTracker$syncTriggers$1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        CloseBarrier r7 = (CloseBarrier) r0.L$0;
        ResultKt.throwOnFailure(r82);     // Catch: Throwable -> L14
    L24:
        r7.unblock$room_runtime();
    L30:
        return Unit.INSTANCE;
    L14:
        Throwable th = th;
    L27:
        r7.unblock$room_runtime();
        throw th;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r82);
        CloseBarrier r83 = this.database.getCloseBarrier$room_runtime();
        if (r83.block$room_runtime() == false) goto L30;
        RoomDatabase r22 = this.database;     // Catch: Throwable -> L25
        TriggerBasedInvalidationTracker$syncTriggers$2$1 r5 = new TriggerBasedInvalidationTracker$syncTriggers$2$1(this, null);     // Catch: Throwable -> L25
        r0.L$0 = r83;     // Catch: Throwable -> L25
        r0.label = 1;     // Catch: Throwable -> L25
        if (r22.useConnection(false, r5, r0) != r12) goto L23;
        return r12;
    L23:
        r7 = r83;
    L25:
        th = move-exception;
        th = th;
        r7 = r83;
    L7:
        r0 = new TriggerBasedInvalidationTracker$syncTriggers$1(this, r8);
        goto L8
    }

    public final Pair<String[], int[]> validateTableNames$room_runtime(String[] r7) {
        String[] r72 = resolveViews(r7);
        int r0 = r72.length;
        int[] r1 = new int[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        String r3 = r72[r2];
        Integer r4 = this.tableIdLookup.get(r3.toLowerCase(Locale.ROOT));
        if (r4 == null) goto L7;
        r1[r2] = r4.intValue();
        r2 = r2 + 1;
        goto L3
    L7:
        f$$ExternalSyntheticBUOutline1.m("There is no table with name ".concat(r3));
        return null;
    L10:
        return TuplesKt.to(r72, r1);
    }
}
