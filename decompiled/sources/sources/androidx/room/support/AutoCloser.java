package androidx.room.support;

import android.os.SystemClock;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 ?2\u00020\u0001:\u0002@?B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0019\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00152\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00028\u00000\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0017¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\n¢\u0006\u0004\b\u001d\u0010\fJ\r\u0010\u001e\u001a\u00020\n¢\u0006\u0004\b\u001e\u0010\fJ\u001b\u0010!\u001a\u00020\n2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\u001f¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010#R\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000e\u0010$R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0012\u0010%R\u001e\u0010&\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R$\u00102\u001a\u0004\u0018\u00010\u00178\u0000@\u0000X\u0081\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u0010\u001c\"\u0004\b5\u00106R\u0016\u00108\u001a\u0002078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R\u0018\u0010;\u001a\u0004\u0018\u00010:8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0011\u0010=\u001a\u0002078F¢\u0006\u0006\u001a\u0004\b=\u0010>¨\u0006A"}, d2 = {"Landroidx/room/support/AutoCloser;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "timeoutAmount", "Ljava/util/concurrent/TimeUnit;", "timeUnit", "Landroidx/room/support/AutoCloser$Watch;", "watch", "<init>", "(JLjava/util/concurrent/TimeUnit;Landroidx/room/support/AutoCloser$Watch;)V", _UrlKt.FRAGMENT_ENCODE_SET, "autoCloseDatabase", "()V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "delegateOpenHelper", "initOpenHelper", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;)V", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "initCoroutineScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "V", "Lkotlin/Function1;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "block", "executeRefCountingFunction", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "incrementCountAndEnsureDbIsOpen", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "decrementCountAndScheduleClose", "closeDatabaseIfOpen", "Lkotlin/Function0;", "onAutoClose", "setAutoCloseCallback", "(Lkotlin/jvm/functions/Function0;)V", "Landroidx/room/support/AutoCloser$Watch;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Lkotlinx/coroutines/CoroutineScope;", "onAutoCloseCallback", "Lkotlin/jvm/functions/Function0;", "lock", "Ljava/lang/Object;", "autoCloseTimeoutInMs", "J", "Ljava/util/concurrent/atomic/AtomicInteger;", "referenceCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "Ljava/util/concurrent/atomic/AtomicLong;", "lastDecrementRefCountTimeStamp", "Ljava/util/concurrent/atomic/AtomicLong;", "delegateDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getDelegateDatabase$room_runtime", "setDelegateDatabase$room_runtime", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", _UrlKt.FRAGMENT_ENCODE_SET, "manuallyClosed", "Z", "Lkotlinx/coroutines/Job;", "autoCloseJob", "Lkotlinx/coroutines/Job;", "isActive", "()Z", "Companion", "Watch", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAutoCloser.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AutoCloser.android.kt\nandroidx/room/support/AutoCloser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,221:1\n1#2:222\n*E\n"})
/* loaded from: classes4.dex */
public final class AutoCloser {
    public static final Companion Companion = null;
    private Job autoCloseJob;
    private final long autoCloseTimeoutInMs;
    private CoroutineScope coroutineScope;
    private SupportSQLiteDatabase delegateDatabase;
    private SupportSQLiteOpenHelper delegateOpenHelper;
    private AtomicLong lastDecrementRefCountTimeStamp;
    private final Object lock;
    private boolean manuallyClosed;
    private Function0<Unit> onAutoCloseCallback;
    private final AtomicInteger referenceCount;
    private final Watch watch;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/room/support/AutoCloser$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "BUG_LINK", _UrlKt.FRAGMENT_ENCODE_SET, "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/room/support/AutoCloser$Watch;", _UrlKt.FRAGMENT_ENCODE_SET, "getMillis", _UrlKt.FRAGMENT_ENCODE_SET, "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Watch {
        long getMillis();
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.support.AutoCloser$decrementCountAndScheduleClose$2", f = "AutoCloser.android.kt", i = {}, l = {167}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.support.AutoCloser$decrementCountAndScheduleClose$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ AutoCloser this$0;

        public AnonymousClass2(AutoCloser r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L11:
            AutoCloser.access$autoCloseDatabase(this.this$0);
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            long r3 = AutoCloser.access$getAutoCloseTimeoutInMs$p(this.this$0);
            this.label = 1;
            if (DelayKt.delay(r3, this) != r0) goto L11;
            return r0;
        }
    }

    public static long $r8$lambda$kteDf46nNvZlEOtTzdE1rOmyvhI() {
        return SystemClock.uptimeMillis();
    }

    static {
        Companion = new Companion(null);
    }

    public AutoCloser(long r2, TimeUnit r4, Watch r5) {
        this.watch = r5;
        this.lock = new Object();
        this.autoCloseTimeoutInMs = r4.toMillis(r2);
        this.referenceCount = new AtomicInteger(0);
        this.lastDecrementRefCountTimeStamp = new AtomicLong(r5.getMillis());
    }

    public /* synthetic */ AutoCloser(long r1, TimeUnit r3, Watch r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 4) == 0) goto L5;
        r4 = new AutoCloser$$ExternalSyntheticLambda0();
    L5:
        this(r1, r3, r4);
    }

    public static final /* synthetic */ void access$autoCloseDatabase(AutoCloser r0) {
        r0.autoCloseDatabase();
    }

    public static final /* synthetic */ long access$getAutoCloseTimeoutInMs$p(AutoCloser r2) {
        return r2.autoCloseTimeoutInMs;
    }

    private final void autoCloseDatabase() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L20:
        th = move-exception;
        throw th;
    L6:
        if ((this.watch.getMillis() - this.lastDecrementRefCountTimeStamp.get()) >= this.autoCloseTimeoutInMs) goto L10;
        monitor-exit(r0);
        return;
    L10:
        if (this.referenceCount.get() == 0) goto L13;
        monitor-exit(r0);
        return;
    L13:
        Function0<Unit> r1 = this.onAutoCloseCallback;     // Catch: Throwable -> L20
        if (r1 == null) goto L26;
        r1.invoke();     // Catch: Throwable -> L20
        SupportSQLiteDatabase r12 = this.delegateDatabase;     // Catch: Throwable -> L20
        if (r12 != null) goto L18;
    L22:
        this.delegateDatabase = null;     // Catch: Throwable -> L20
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L20
        monitor-exit(r0);
        return;
    L18:
        if (r12.isOpen() == false) goto L22;
        r12.close();     // Catch: Throwable -> L20
        goto L22
    L26:
        throw new IllegalStateException("onAutoCloseCallback is null but it should  have been set before use. Please file a bug against Room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568");     // Catch: Throwable -> L20
    }

    public final void closeDatabaseIfOpen() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.manuallyClosed = true;     // Catch: Throwable -> L8
        Job r2 = this.autoCloseJob;     // Catch: Throwable -> L8
        if (r2 == null) goto L10;
        Job.DefaultImpls.cancel$default(r2, null, 1, null);     // Catch: Throwable -> L8
    L10:
        this.autoCloseJob = null;     // Catch: Throwable -> L8
        SupportSQLiteDatabase r1 = this.delegateDatabase;     // Catch: Throwable -> L8
        if (r1 == null) goto L13;
        r1.close();     // Catch: Throwable -> L8
    L13:
        this.delegateDatabase = null;     // Catch: Throwable -> L8
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final void decrementCountAndScheduleClose() {
        int r0 = this.referenceCount.decrementAndGet();
        if (r0 < 0) goto L12;
        this.lastDecrementRefCountTimeStamp.set(this.watch.getMillis());
        if (r0 != 0) goto L14;
        CoroutineScope r02 = this.coroutineScope;
        if (r02 != null) goto L9;
        CoroutineScope r2 = null;
    L10:
        this.autoCloseJob = BuildersKt.launch$default(r2, null, null, new AnonymousClass2(this, null), 3, null);
        return;
    L9:
        r2 = r02;
        goto L10
    L14:
        return;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Unbalanced reference count.");
    }

    public final <V> V executeRefCountingFunction(Function1<? super SupportSQLiteDatabase, ? extends V> r2) {
        V r22 = r2.invoke(incrementCountAndEnsureDbIsOpen());     // Catch: Throwable -> L5
        decrementCountAndScheduleClose();
        return r22;
    L5:
        th = move-exception;
        decrementCountAndScheduleClose();
        throw th;
    }

    public final SupportSQLiteDatabase getDelegateDatabase$room_runtime() {
        return this.delegateDatabase;
    }

    public final SupportSQLiteDatabase incrementCountAndEnsureDbIsOpen() {
        Job r0 = this.autoCloseJob;
        SupportSQLiteOpenHelper r1 = null;
        if (r0 == null) goto L5;
        Job.DefaultImpls.cancel$default(r0, null, 1, null);
    L5:
        this.autoCloseJob = null;
        this.referenceCount.incrementAndGet();
        if (this.manuallyClosed == true) goto L26;
        Object r02 = this.lock;
        monitor-enter(r02);
        SupportSQLiteDatabase r2 = this.delegateDatabase;     // Catch: Throwable -> L15
        if (r2 != null) goto L12;
    L17:
        SupportSQLiteOpenHelper r22 = this.delegateOpenHelper;     // Catch: Throwable -> L15
        if (r22 == null) goto L21;
        r1 = r22;
    L21:
        SupportSQLiteDatabase r12 = r1.getWritableDatabase();     // Catch: Throwable -> L15
        this.delegateDatabase = r12;     // Catch: Throwable -> L15
        monitor-exit(r02);
        return r12;
    L12:
        if (r2.isOpen() == false) goto L17;
        monitor-exit(r02);
        return r2;
    L15:
        th = move-exception;
        throw th;
    L26:
        Segment$$ExternalSyntheticBUOutline1.m("Attempting to open already closed database.");
        return null;
    }

    public final void initCoroutineScope(CoroutineScope r1) {
        this.coroutineScope = r1;
    }

    public final void initOpenHelper(SupportSQLiteOpenHelper r2) {
        if ((r2 instanceof AutoClosingRoomOpenHelper) == true) goto L6;
        this.delegateOpenHelper = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
    }

    public final boolean isActive() {
        return !this.manuallyClosed;
    }

    public final void setAutoCloseCallback(Function0<Unit> r1) {
        this.onAutoCloseCallback = r1;
    }
}
