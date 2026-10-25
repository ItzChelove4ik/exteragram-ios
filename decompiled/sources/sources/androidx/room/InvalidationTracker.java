package androidx.room;

import android.content.Context;
import android.content.Intent;
import androidx.room.coroutines.RunBlockingUninterruptible_androidKt;
import androidx.room.support.AutoCloser;
import androidx.sqlite.SQLiteConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 d2\u00020\u0001:\u0002edBX\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\b\b0\u0004\u0012\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\n\"\u00020\u0005¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0016\u0010\u0015J\u0015\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001c\u001a\u00020\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0000¢\u0006\u0004\b \u0010!J\u0017\u0010'\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020#H\u0000¢\u0006\u0004\b%\u0010&J\u0010\u0010*\u001a\u00020\u000eH\u0080@¢\u0006\u0004\b(\u0010)J\u000f\u0010,\u001a\u00020\u000eH\u0001¢\u0006\u0004\b+\u0010\u0010J\r\u0010-\u001a\u00020\u000e¢\u0006\u0004\b-\u0010\u0010J9\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0007002\u0012\u0010.\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\n\"\u00020\u00052\b\b\u0002\u0010/\u001a\u00020\u0013H\u0007¢\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0004\b3\u00104J\u0017\u00106\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0017¢\u0006\u0004\b6\u00104J\u000f\u00107\u001a\u00020\u000eH\u0016¢\u0006\u0004\b7\u0010\u0010J\u001d\u00109\u001a\u00020\u000e2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0000¢\u0006\u0004\b8\u0010\u001dJ'\u0010A\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020\u00052\u0006\u0010>\u001a\u00020=H\u0000¢\u0006\u0004\b?\u0010@J\u000f\u0010C\u001a\u00020\u000eH\u0000¢\u0006\u0004\bB\u0010\u0010R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010D\u001a\u0004\bE\u0010FR \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010GR+\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\b\b0\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010GR\"\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\n8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010H\u001a\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR \u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020O0N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010GR\u0018\u0010S\u001a\u00060Qj\u0002`R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010UR\u001a\u0010W\u001a\b\u0012\u0004\u0012\u00020\u000e0V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010XR\u001a\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u000e0V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010XR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u0018\u0010]\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010^R\u0018\u0010`\u001a\u0004\u0018\u00010_8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010aR\u0014\u0010b\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010c¨\u0006f"}, d2 = {"Landroidx/room/InvalidationTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/RoomDatabase;", "database", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "shadowTablesMap", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/jvm/JvmSuppressWildcards;", "viewTables", _UrlKt.FRAGMENT_ENCODE_SET, "tableNames", "<init>", "(Landroidx/room/RoomDatabase;Ljava/util/Map;Ljava/util/Map;[Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "onAutoCloseCallback", "()V", "Landroidx/room/InvalidationTracker$Observer;", "observer", _UrlKt.FRAGMENT_ENCODE_SET, "addObserverOnly", "(Landroidx/room/InvalidationTracker$Observer;)Z", "removeObserverOnly", _UrlKt.FRAGMENT_ENCODE_SET, "getAllObservers", "()Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "tableIds", "notifyInvalidatedObservers", "(Ljava/util/Set;)V", "Landroidx/room/support/AutoCloser;", "autoCloser", "setAutoCloser$room_runtime", "(Landroidx/room/support/AutoCloser;)V", "setAutoCloser", "Landroidx/sqlite/SQLiteConnection;", "connection", "internalInit$room_runtime", "(Landroidx/sqlite/SQLiteConnection;)V", "internalInit", "sync$room_runtime", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sync", "syncBlocking$room_runtime", "syncBlocking", "refreshAsync", "tables", "emitInitialState", "Lkotlinx/coroutines/flow/Flow;", "createFlow", "([Ljava/lang/String;Z)Lkotlinx/coroutines/flow/Flow;", "addRemoteObserver$room_runtime", "(Landroidx/room/InvalidationTracker$Observer;)V", "addRemoteObserver", "removeObserver", "refreshVersionsAsync", "notifyObserversByTableNames$room_runtime", "notifyObserversByTableNames", "Landroid/content/Context;", "context", "name", "Landroid/content/Intent;", "serviceIntent", "initMultiInstanceInvalidation$room_runtime", "(Landroid/content/Context;Ljava/lang/String;Landroid/content/Intent;)V", "initMultiInstanceInvalidation", "stop$room_runtime", "stop", "Landroidx/room/RoomDatabase;", "getDatabase$room_runtime", "()Landroidx/room/RoomDatabase;", "Ljava/util/Map;", "[Ljava/lang/String;", "getTableNames$room_runtime", "()[Ljava/lang/String;", "Landroidx/room/TriggerBasedInvalidationTracker;", "implementation", "Landroidx/room/TriggerBasedInvalidationTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/ObserverWrapper;", "observerMap", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "observerMapLock", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/support/AutoCloser;", "Lkotlin/Function0;", "onRefreshScheduled", "Lkotlin/jvm/functions/Function0;", "onRefreshCompleted", "Landroidx/room/InvalidationLiveDataContainer;", "invalidationLiveDataContainer", "Landroidx/room/InvalidationLiveDataContainer;", "multiInstanceInvalidationIntent", "Landroid/content/Intent;", "Landroidx/room/MultiInstanceInvalidationClient;", "multiInstanceInvalidationClient", "Landroidx/room/MultiInstanceInvalidationClient;", "trackerLock", "Ljava/lang/Object;", "Companion", "Observer", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInvalidationTracker.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.android.kt\nandroidx/room/InvalidationTracker\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ReentrantLock.kt\nandroidx/room/concurrent/ReentrantLockKt\n*L\n1#1,592:1\n827#2:593\n855#2,2:594\n1869#2,2:617\n1869#2,2:624\n1#3:596\n28#4,5:597\n28#4,5:602\n28#4,5:607\n28#4,5:612\n28#4,5:619\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.android.kt\nandroidx/room/InvalidationTracker\n*L\n183#1:593\n183#1:594,2\n351#1:617,2\n365#1:624,2\n274#1:597,5\n318#1:602,5\n322#1:607,5\n350#1:612,5\n364#1:619,5\n*E\n"})
/* loaded from: classes.dex */
public class InvalidationTracker {
    public static final Companion Companion = null;
    private AutoCloser autoCloser;
    private final RoomDatabase database;
    private final TriggerBasedInvalidationTracker implementation;
    private final InvalidationLiveDataContainer invalidationLiveDataContainer;
    private MultiInstanceInvalidationClient multiInstanceInvalidationClient;
    private Intent multiInstanceInvalidationIntent;
    private final Map<Observer, ObserverWrapper> observerMap;
    private final ReentrantLock observerMapLock;
    private final Function0<Unit> onRefreshCompleted;
    private final Function0<Unit> onRefreshScheduled;
    private final Map<String, String> shadowTablesMap;
    private final String[] tableNames;
    private final Object trackerLock;
    private final Map<String, Set<String>> viewTables;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/room/InvalidationTracker$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&¢\u0006\u0004\b\t\u0010\nR\"\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000e8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/room/InvalidationTracker$Observer;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "tables", "<init>", "([Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "onInvalidated", "(Ljava/util/Set;)V", "[Ljava/lang/String;", "getTables$room_runtime", "()[Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "isRemote$room_runtime", "()Z", "isRemote", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static abstract class Observer {
        private final String[] tables;

        public Observer(String[] r1) {
            this.tables = r1;
        }

        public final String[] getTables$room_runtime() {
            return this.tables;
        }

        public abstract boolean isRemote$room_runtime();

        public abstract void onInvalidated(Set<String> r1);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.InvalidationTracker$removeObserver$1", f = "InvalidationTracker.android.kt", i = {}, l = {310}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.InvalidationTracker$removeObserver$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ InvalidationTracker this$0;

        public AnonymousClass1(InvalidationTracker r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
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
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            TriggerBasedInvalidationTracker r42 = InvalidationTracker.access$getImplementation$p(this.this$0);
            this.label = 1;
            if (r42.syncTriggers$room_runtime(this) != r0) goto L12;
            return r0;
        }
    }

    /* renamed from: $r8$lambda$S0GAeZVlYzHdkENrWO-BCuj8x5g, reason: not valid java name */
    public static Unit m941$r8$lambda$S0GAeZVlYzHdkENrWOBCuj8x5g(InvalidationTracker r0) {
        AutoCloser r02 = r0.autoCloser;
        if (r02 == null) goto L6;
        r02.decrementCountAndScheduleClose();
    L6:
        return Unit.INSTANCE;
    }

    public static boolean $r8$lambda$rV938R3HpEbbZMJ5mxkZVyQwHqY(InvalidationTracker r1) {
        if (r1.database.inCompatibilityMode() == true) goto L5;
        return true;
    L5:
        if (r1.database.isOpenInternal$room_runtime() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static Unit $r8$lambda$z_vz9Tqx65AifWN0It6OfeI4bTk(InvalidationTracker r0) {
        AutoCloser r02 = r0.autoCloser;
        if (r02 == null) goto L6;
        r02.incrementCountAndEnsureDbIsOpen();
    L6:
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public InvalidationTracker(RoomDatabase r8, Map<String, String> r9, Map<String, Set<String>> r10, String... r11) {
        this.database = r8;
        this.shadowTablesMap = r9;
        this.viewTables = r10;
        this.tableNames = r11;
        TriggerBasedInvalidationTracker r0 = new TriggerBasedInvalidationTracker(r8, r9, r10, r11, r8.getUseTempTrackingTable$room_runtime(), new InvalidationTracker$implementation$1(this));
        this.implementation = r0;
        this.observerMap = new LinkedHashMap();
        this.observerMapLock = new ReentrantLock();
        this.onRefreshScheduled = new InvalidationTracker$$ExternalSyntheticLambda0(this);
        this.onRefreshCompleted = new InvalidationTracker$$ExternalSyntheticLambda1(this);
        this.invalidationLiveDataContainer = new InvalidationLiveDataContainer(r8);
        this.trackerLock = new Object();
        r0.setOnAllowRefresh$room_runtime(new InvalidationTracker$$ExternalSyntheticLambda2(this));
    }

    public static final /* synthetic */ TriggerBasedInvalidationTracker access$getImplementation$p(InvalidationTracker r0) {
        return r0.implementation;
    }

    public static final /* synthetic */ void access$notifyInvalidatedObservers(InvalidationTracker r0, Set r1) {
        r0.notifyInvalidatedObservers(r1);
    }

    public static final /* synthetic */ void access$onAutoCloseCallback(InvalidationTracker r0) {
        r0.onAutoCloseCallback();
    }

    private final boolean addObserverOnly(Observer r6) {
        Pair<String[], int[]> r0 = this.implementation.validateTableNames$room_runtime(r6.getTables$room_runtime());
        String[] r1 = r0.component1();
        int[] r02 = r0.component2();
        ObserverWrapper r2 = new ObserverWrapper(r6, r02, r1);
        ReentrantLock r12 = this.observerMapLock;
        r12.lock();
        boolean r3 = this.observerMap.containsKey(r6);     // Catch: Throwable -> L7
        Map<Observer, ObserverWrapper> r4 = this.observerMap;
        if (r3 == false) goto L9;
        ObserverWrapper r62 = (ObserverWrapper) MapsKt.getValue(r4, r6);     // Catch: Throwable -> L7
    L10:
        r12.unlock();
        if (r62 == null) goto L13;
        return false;
    L13:
        if (this.implementation.onObserverAdded$room_runtime(r02) == false) goto L21;
        return true;
    L21:
        return false;
    L9:
        r62 = r4.put(r6, r2);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        r12.unlock();
        throw th;
    }

    private final List<Observer> getAllObservers() {
        ReentrantLock r0 = this.observerMapLock;
        r0.lock();
        List<Observer> r1 = CollectionsKt.toList(this.observerMap.keySet());     // Catch: Throwable -> L6
        r0.unlock();
        return r1;
    L6:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    private final void notifyInvalidatedObservers(Set<Integer> r2) {
        ReentrantLock r0 = this.observerMapLock;
        r0.lock();
        List r1 = CollectionsKt.toList(this.observerMap.values());     // Catch: Throwable -> L9
        r0.unlock();
        Iterator r12 = r1.iterator();
    L6:
        if (r12.hasNext() == false) goto L8;
        ((ObserverWrapper) r12.next()).notifyByTableIds$room_runtime(r2);
        goto L6
    L8:
        return;
    L9:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    private final void onAutoCloseCallback() {
        Object r0 = this.trackerLock;
        monitor-enter(r0);
        MultiInstanceInvalidationClient r1 = this.multiInstanceInvalidationClient;     // Catch: Throwable -> L12
        if (r1 == null) goto L17;
        List<Observer> r2 = getAllObservers();     // Catch: Throwable -> L12
        ArrayList r3 = new ArrayList();     // Catch: Throwable -> L12
        Iterator<T> r22 = r2.iterator();     // Catch: Throwable -> L12
    L8:
        if (r22.hasNext() == false) goto L15;
        Object r4 = r22.next();     // Catch: Throwable -> L12
        if (((Observer) r4).isRemote$room_runtime() == true) goto L8;
        r3.add(r4);     // Catch: Throwable -> L12
        goto L8
    L15:
        if (r3.isEmpty() == false) goto L17;
        r1.stop();     // Catch: Throwable -> L12
    L17:
        this.implementation.resetSync$room_runtime();     // Catch: Throwable -> L12
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r0);
        return;
    L12:
        th = move-exception;
        throw th;
    }

    private final boolean removeObserverOnly(Observer r3) {
        ReentrantLock r0 = this.observerMapLock;
        r0.lock();
        ObserverWrapper r32 = this.observerMap.remove(r3);     // Catch: Throwable -> L12
        r0.unlock();
        if (r32 != null) goto L7;
        return false;
    L7:
        if (this.implementation.onObserverRemoved$room_runtime(r32.getTableIds$room_runtime()) == false) goto L17;
        return true;
    L17:
        return false;
    L12:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    public final void addRemoteObserver$room_runtime(Observer r2) {
        if (r2.isRemote$room_runtime() == false) goto L6;
        addObserverOnly(r2);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("isRemote was false of observer argument");
    }

    @JvmOverloads
    public final Flow<Set<String>> createFlow(String[] r3, boolean r4) {
        Pair<String[], int[]> r32 = this.implementation.validateTableNames$room_runtime(r3);
        String[] r0 = r32.component1();
        Flow<Set<String>> r33 = this.implementation.createFlow$room_runtime(r0, r32.component2(), r4);
        MultiInstanceInvalidationClient r2 = this.multiInstanceInvalidationClient;
        if (r2 == null) goto L5;
        Flow<Set<String>> r22 = r2.createFlow(r0);
    L6:
        if (r22 != null) goto L8;
        return r33;
    L8:
        return FlowKt.merge(new Flow[]{r33, r22});
    L5:
        r22 = null;
        goto L6
    }

    public final RoomDatabase getDatabase$room_runtime() {
        return this.database;
    }

    public final String[] getTableNames$room_runtime() {
        return this.tableNames;
    }

    public final void initMultiInstanceInvalidation$room_runtime(Context r1, String r2, Intent r3) {
        this.multiInstanceInvalidationIntent = r3;
        this.multiInstanceInvalidationClient = new MultiInstanceInvalidationClient(r1, r2, this);
    }

    public final void internalInit$room_runtime(SQLiteConnection r2) {
        this.implementation.configureConnection(r2);
        Object r22 = this.trackerLock;
        monitor-enter(r22);
        MultiInstanceInvalidationClient r0 = this.multiInstanceInvalidationClient;     // Catch: Throwable -> L9
        if (r0 == null) goto L13;
        Intent r1 = this.multiInstanceInvalidationIntent;     // Catch: Throwable -> L9
        if (r1 == null) goto L12;
        r0.start(r1);     // Catch: Throwable -> L9
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L9
        goto L13
    L12:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L9
    L13:
        monitor-exit(r22);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final void notifyObserversByTableNames$room_runtime(Set<String> r3) {
        ReentrantLock r0 = this.observerMapLock;
        r0.lock();
        List r2 = CollectionsKt.toList(this.observerMap.values());     // Catch: Throwable -> L11
        r0.unlock();
        Iterator r22 = r2.iterator();
    L6:
        if (r22.hasNext() == false) goto L10;
        ObserverWrapper r02 = (ObserverWrapper) r22.next();
        if (r02.getObserver$room_runtime().isRemote$room_runtime() == true) goto L6;
        r02.notifyByTableNames$room_runtime(r3);
        goto L6
    L10:
        return;
    L11:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    public final void refreshAsync() {
        this.implementation.refreshInvalidationAsync$room_runtime(this.onRefreshScheduled, this.onRefreshCompleted);
    }

    public void refreshVersionsAsync() {
        this.implementation.refreshInvalidationAsync$room_runtime(this.onRefreshScheduled, this.onRefreshCompleted);
    }

    public void removeObserver(Observer r2) {
        if (removeObserverOnly(r2) == false) goto L6;
        RunBlockingUninterruptible_androidKt.runBlockingUninterruptible(new AnonymousClass1(this, null));
        return;
    }

    public final void setAutoCloser$room_runtime(AutoCloser r2) {
        this.autoCloser = r2;
        r2.setAutoCloseCallback(new InvalidationTracker$setAutoCloser$1(this));
    }

    public final void stop$room_runtime() {
        MultiInstanceInvalidationClient r0 = this.multiInstanceInvalidationClient;
        if (r0 == null) goto L6;
        r0.stop();
        return;
    }

    public final Object sync$room_runtime(Continuation<? super Unit> r1) {
        Object r0 = this.implementation.syncTriggers$room_runtime(r1);
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r0;
    L6:
        return Unit.INSTANCE;
    }

    public final void syncBlocking$room_runtime() {
        RunBlockingUninterruptible_androidKt.runBlockingUninterruptible(new InvalidationTracker$syncBlocking$1(this, null));
    }
}
