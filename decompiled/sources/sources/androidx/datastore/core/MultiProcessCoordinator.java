package androidx.datastore.core;

import com.android.dx.io.Opcodes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\f*\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\f*\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ4\u0010\u0015\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00102\u001c\u0010\u0014\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011H\u0096@¢\u0006\u0004\b\u0015\u0010\u0016J:\u0010\u0019\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00102\"\u0010\u0014\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0017H\u0096@¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bH\u0096@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u001bH\u0096@¢\u0006\u0004\b\u001e\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u001a\u0010\u0005\u001a\u00020\u00048\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\"R \u0010$\u001a\b\u0012\u0004\u0012\u00020\f0#8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020\b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b+\u0010)R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u001b\u00102\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u0010\"R\u001a\u00105\u001a\b\u0012\u0004\u0012\u000204038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00100R\u001b\u0010:\u001a\u0002048BX\u0082\u0084\u0002¢\u0006\f\u001a\u0004\b6\u00107*\u0004\b8\u00109¨\u0006<"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator;", "Landroidx/datastore/core/InterProcessCoordinator;", "Lkotlin/coroutines/CoroutineContext;", "context", "Ljava/io/File;", "file", "<init>", "(Lkotlin/coroutines/CoroutineContext;Ljava/io/File;)V", _UrlKt.FRAGMENT_ENCODE_SET, "suffix", "fileWithSuffix", "(Ljava/lang/String;)Ljava/io/File;", _UrlKt.FRAGMENT_ENCODE_SET, "createIfNotExists", "(Ljava/io/File;)V", "createParentDirectories", "T", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "block", "lock", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "tryLock", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "getVersion", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementAndGetVersion", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/File;", "getFile", "()Ljava/io/File;", "Lkotlinx/coroutines/flow/Flow;", "updateNotifications", "Lkotlinx/coroutines/flow/Flow;", "getUpdateNotifications", "()Lkotlinx/coroutines/flow/Flow;", "LOCK_SUFFIX", "Ljava/lang/String;", "VERSION_SUFFIX", "LOCK_ERROR_MESSAGE", "Lkotlinx/coroutines/sync/Mutex;", "inMemoryMutex", "Lkotlinx/coroutines/sync/Mutex;", "lockFile$delegate", "Lkotlin/Lazy;", "getLockFile", "lockFile", "Lkotlin/Lazy;", "Landroidx/datastore/core/SharedCounter;", "lazySharedCounter", "getSharedCounter", "()Landroidx/datastore/core/SharedCounter;", "getSharedCounter$delegate", "(Landroidx/datastore/core/MultiProcessCoordinator;)Ljava/lang/Object;", "sharedCounter", "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMultiProcessCoordinator.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiProcessCoordinator.android.kt\nandroidx/datastore/core/MultiProcessCoordinator\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 3 MutexUtils.kt\nandroidx/datastore/core/MutexUtilsKt\n*L\n1#1,205:1\n159#1,8:226\n159#1,8:234\n120#2,10:206\n32#3,10:216\n*S KotlinDebug\n*F\n+ 1 MultiProcessCoordinator.android.kt\nandroidx/datastore/core/MultiProcessCoordinator\n*L\n99#1:226,8\n106#1:234,8\n43#1:206,10\n60#1:216,10\n*E\n"})
/* loaded from: classes.dex */
public final class MultiProcessCoordinator implements InterProcessCoordinator {
    public static final Companion Companion = null;
    private static final String DEADLOCK_ERROR_MESSAGE = null;
    private static final long INITIAL_WAIT_MILLIS = 0;
    private static final long MAX_WAIT_MILLIS = 0;
    private final String LOCK_ERROR_MESSAGE;
    private final String LOCK_SUFFIX;
    private final String VERSION_SUFFIX;
    private final CoroutineContext context;
    private final File file;
    private final Mutex inMemoryMutex;
    private final Lazy<SharedCounter> lazySharedCounter;
    private final Lazy lockFile$delegate;
    private final Flow<Unit> updateNotifications;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "DEADLOCK_ERROR_MESSAGE", _UrlKt.FRAGMENT_ENCODE_SET, "INITIAL_WAIT_MILLIS", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_WAIT_MILLIS", "getExclusiveFileLockWithRetryIfDeadlock", "Ljava/nio/channels/FileLock;", "lockFileStream", "Ljava/io/FileOutputStream;", "(Ljava/io/FileOutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ Object access$getExclusiveFileLockWithRetryIfDeadlock(Companion r0, FileOutputStream r1, Continuation r2) {
            return r0.getExclusiveFileLockWithRetryIfDeadlock(r1, r2);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0075 -> B:27:0x0078). Please report as a decompilation issue!!! */
        private final Object getExclusiveFileLockWithRetryIfDeadlock(FileOutputStream r13, Continuation<? super FileLock> r14) {
            if ((r14 instanceof MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1) == false) goto L7;
            MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 r0 = (MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1) r14;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r12 = r0.result;
            Object r142 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r15 = r0.label;
            if (r15 == 0) goto L14;
            if (r15 != 1) goto L12;
            long r4 = r0.J$0;
            r13 = (FileOutputStream) r0.L$0;
            ResultKt.throwOnFailure(r12);
            MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 r122 = r0;
        L27:
            r4 = r4 * 2;
        L16:
            if (r4 > MultiProcessCoordinator.access$getMAX_WAIT_MILLIS$cp()) goto L30;
            return r13.getChannel().lock(0, LongCompanionObject.MAX_VALUE, false);
        L19:
            e = move-exception;
            String r16 = e.getMessage();
            if (r16 != null) goto L23;
        L28:
            throw e;
        L23:
            if (StringsKt.contains$default(r16, MultiProcessCoordinator.access$getDEADLOCK_ERROR_MESSAGE$cp(), false, 2, null) != true) goto L28;
            r122.L$0 = r13;
            r122.J$0 = r4;
            r122.label = 1;
            if (DelayKt.delay(r4, r122) != r142) goto L27;
            return r142;
        L30:
            return r13.getChannel().lock(0, LongCompanionObject.MAX_VALUE, false);
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L14:
            ResultKt.throwOnFailure(r12);
            r4 = MultiProcessCoordinator.access$getINITIAL_WAIT_MILLIS$cp();
            r122 = r0;
        L7:
            r0 = new MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1(this, r14);
            goto L8
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.MultiProcessCoordinator", f = "MultiProcessCoordinator.android.kt", i = {0, 0, 0, 1, 1, 2, 2}, l = {Opcodes.DIV_INT_LIT16, 47, 48}, m = "lock", n = {"this", "block", "$this$withLock_u24default$iv", "block", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "lock"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$2"})
    /* renamed from: androidx.datastore.core.MultiProcessCoordinator$lock$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ MultiProcessCoordinator this$0;

        public AnonymousClass1(MultiProcessCoordinator r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.lock(null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.MultiProcessCoordinator", f = "MultiProcessCoordinator.android.kt", i = {0, 0, 1, 1, 1}, l = {62, 87}, m = "tryLock", n = {"$this$withTryLock_u24default$iv", "locked$iv", "$this$withTryLock_u24default$iv", "lock", "locked$iv"}, s = {"L$0", "Z$0", "L$0", "L$2", "Z$0"})
    /* renamed from: androidx.datastore.core.MultiProcessCoordinator$tryLock$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes3.dex */
    public static final class C01151<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ MultiProcessCoordinator this$0;

        public C01151(MultiProcessCoordinator r1, Continuation<? super C01151> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.tryLock(null, this);
        }
    }

    static {
        Companion = new Companion(null);
        DEADLOCK_ERROR_MESSAGE = "Resource deadlock would occur";
        INITIAL_WAIT_MILLIS = 10;
        MAX_WAIT_MILLIS = 60000;
    }

    public MultiProcessCoordinator(CoroutineContext r2, File r3) {
        this.context = r2;
        this.file = r3;
        this.updateNotifications = MulticastFileObserver.Companion.observe(r3);
        this.LOCK_SUFFIX = ".lock";
        this.VERSION_SUFFIX = ".version";
        this.LOCK_ERROR_MESSAGE = "fcntl failed: EAGAIN";
        this.inMemoryMutex = MutexKt.Mutex$default(false, 1, null);
        this.lockFile$delegate = LazyKt.lazy(new MultiProcessCoordinator$lockFile$2(this));
        this.lazySharedCounter = LazyKt.lazy(new MultiProcessCoordinator$lazySharedCounter$1(this));
    }

    public static final /* synthetic */ void access$createIfNotExists(MultiProcessCoordinator r0, File r1) {
        r0.createIfNotExists(r1);
    }

    public static final /* synthetic */ File access$fileWithSuffix(MultiProcessCoordinator r0, String r1) {
        return r0.fileWithSuffix(r1);
    }

    public static final /* synthetic */ String access$getDEADLOCK_ERROR_MESSAGE$cp() {
        return DEADLOCK_ERROR_MESSAGE;
    }

    public static final /* synthetic */ long access$getINITIAL_WAIT_MILLIS$cp() {
        return INITIAL_WAIT_MILLIS;
    }

    public static final /* synthetic */ String access$getLOCK_SUFFIX$p(MultiProcessCoordinator r0) {
        return r0.LOCK_SUFFIX;
    }

    public static final /* synthetic */ long access$getMAX_WAIT_MILLIS$cp() {
        return MAX_WAIT_MILLIS;
    }

    public static final /* synthetic */ SharedCounter access$getSharedCounter(MultiProcessCoordinator r0) {
        return r0.getSharedCounter();
    }

    public static final /* synthetic */ String access$getVERSION_SUFFIX$p(MultiProcessCoordinator r0) {
        return r0.VERSION_SUFFIX;
    }

    private final void createIfNotExists(File r1) {
        createParentDirectories(r1);
        if (r1.exists() == true) goto L6;
        r1.createNewFile();
        return;
    }

    private final void createParentDirectories(File r1) {
        File r0 = r1.getCanonicalFile().getParentFile();
        if (r0 == null) goto L9;
        r0.mkdirs();
        if (r0.isDirectory() == true) goto L10;
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("Unable to create parent directories of ", r1);
        return;
    L10:
        return;
    }

    private final File fileWithSuffix(String r3) {
        return new File(this.file.getAbsolutePath() + r3);
    }

    private final File getLockFile() {
        return (File) this.lockFile$delegate.getValue();
    }

    private final SharedCounter getSharedCounter() {
        return this.lazySharedCounter.getValue();
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Flow<Unit> getUpdateNotifications() {
        return this.updateNotifications;
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object getVersion(Continuation<? super Integer> r4) {
        if (this.lazySharedCounter.isInitialized() == false) goto L7;
        return Boxing.boxInt(getSharedCounter().getValue());
    L7:
        return BuildersKt.withContext(this.context, new MultiProcessCoordinator$getVersion$$inlined$withLazyCounter$1(this, null), r4);
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object incrementAndGetVersion(Continuation<? super Integer> r4) {
        if (this.lazySharedCounter.isInitialized() == false) goto L7;
        return Boxing.boxInt(getSharedCounter().incrementAndGetValue());
    L7:
        return BuildersKt.withContext(this.context, new MultiProcessCoordinator$incrementAndGetVersion$$inlined$withLazyCounter$1(this, null), r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.io.Closeable, java.lang.Object, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object>] */
    @Override // androidx.datastore.core.InterProcessCoordinator
    public <T> Object lock(Function1<? super Continuation<? super T>, ? extends Object> r9, Continuation<? super T> r10) {
        if ((r10 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        ?? r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L27;
        if (r2 == 1) goto L26;
        if (r2 == 2) goto L20;
        if (r2 != 3) goto L18;
        FileLock r8 = (FileLock) r0.L$2;
        java.io.Closeable r92 = (java.io.Closeable) r0.L$1;
        Mutex r02 = (Mutex) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L16
        Object r103 = r102;
    L40:
        if (r8 == null) goto L69;
        r8.release();     // Catch: Throwable -> L43
    L69:
        CloseableKt.closeFinally(r92, null);     // Catch: Throwable -> L48
        r02.unlock(null);
        return r103;
    L48:
        th = th;
        r102 = r02;
    L62:
        r102.unlock(null);
        throw th;
    L16:
        Throwable th = th;
    L54:
        if (r8 == null) goto L56;
        r8.release();     // Catch: Throwable -> L43
    L56:
        throw th;     // Catch: Throwable -> L43
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        r92 = (java.io.Closeable) r0.L$2;
        Mutex r82 = (Mutex) r0.L$1;
        Function1 r22 = (Function1) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L23
        Object r104 = r102;
    L35:
        FileLock r105 = (FileLock) r104;     // Catch: Throwable -> L23
        r0.L$0 = r82;     // Catch: Throwable -> L50
        r0.L$1 = r92;     // Catch: Throwable -> L50
        r0.L$2 = r105;     // Catch: Throwable -> L50
        r0.label = 3;     // Catch: Throwable -> L50
        Object r03 = r22.invoke(r0);     // Catch: Throwable -> L50
        if (r03 != r12) goto L39;
    L38:
        return r12;
    L39:
        r02 = r82;
        r8 = r105;
        r103 = r03;
    L50:
        th = move-exception;
        r8 = r105;
        th = th;
    L23:
        th = th;
    L25:
        r8 = null;
        goto L54
    L26:
        Mutex r83 = (Mutex) r0.L$2;
        Function1 r93 = (Function1<? super Continuation<? super T>, ? extends Object>) ((Function1) r0.L$1);
        MultiProcessCoordinator r23 = (MultiProcessCoordinator) r0.L$0;
        ResultKt.throwOnFailure(r102);
        r102 = r83;
        MultiProcessCoordinator this = r23;
        Function1 r94 = r93;
    L66:
        FileOutputStream r24 = new FileOutputStream(this.getLockFile());     // Catch: Throwable -> L61
        Companion r84 = Companion;     // Catch: Throwable -> L52
        r0.L$0 = r94;     // Catch: Throwable -> L52
        r0.L$1 = r102;     // Catch: Throwable -> L52
        r0.L$2 = r24;     // Catch: Throwable -> L52
        r0.label = 2;     // Catch: Throwable -> L52
        Object r85 = Companion.access$getExclusiveFileLockWithRetryIfDeadlock(r84, r24, r0);     // Catch: Throwable -> L52
        if (r85 == r12) goto L38;
        Mutex r7 = r102;
        r104 = r85;
        r82 = r7;
        r22 = r94;
        r92 = r24;
    L52:
        th = move-exception;
        th = th;
        goto L25
    L27:
        ResultKt.throwOnFailure(r102);
        Mutex r106 = this.inMemoryMutex;
        r0.L$0 = this;
        r0.L$1 = r9;
        r0.L$2 = r106;
        r0.label = 1;
        Object r25 = r106.lock(null, r0);
        r94 = r9;
        r102 = r106;
        if (r25 != r12) goto L66;
    L43:
        th = move-exception;
        r102 = r0;
        throw th;     // Catch: Throwable -> L58
    L58:
        th = move-exception;
        CloseableKt.closeFinally(r9, th);     // Catch: Throwable -> L61
        throw th;     // Catch: Throwable -> L61
    L61:
        th = th;
    L7:
        r0 = new AnonymousClass1(this, r10);
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.datastore.core.MultiProcessCoordinator$tryLock$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.Closeable, java.lang.Object] */
    @Override // androidx.datastore.core.InterProcessCoordinator
    public <T> Object tryLock(Function2<? super Boolean, ? super Continuation<? super T>, ? extends Object> r19, Continuation<? super T> r20) {
        if ((r20 instanceof C01151) == false) goto L7;
        C01151 r3 = (C01151) r20;
        int r4 = r3.label;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L7;
        r3.label = r4 - Integer.MIN_VALUE;
        ?? r32 = r3;
    L8:
        Object r0 = ((C01151) r32).result;
        ?? r42 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r5 = ((C01151) r32).label;
        boolean r7 = true;
        if (r5 == 0) goto L24;
        if (r5 == 1) goto L19;
        if (r5 != 2) goto L17;
        boolean r1 = ((C01151) r32).Z$0;
        FileLock r2 = (FileLock) ((C01151) r32).L$2;
        java.io.Closeable r43 = (java.io.Closeable) ((C01151) r32).L$1;
        Mutex r33 = (Mutex) ((C01151) r32).L$0;
        ResultKt.throwOnFailure(r0);     // Catch: Throwable -> L15
        ?? r12 = r1;
        r32 = r33;
    L59:
        if (r2 == null) goto L93;
        r2.release();     // Catch: Throwable -> L62
    L93:
        CloseableKt.closeFinally(r43, null);     // Catch: Throwable -> L68
        if (r12 == 0) goto L67;
        ((Mutex) r32).unlock(null);
    L67:
        return r0;
    L68:
        th = th;
    L69:
        Mutex r22 = r32;
    L82:
        if (r12 == 0) goto L84;
        r22.unlock(null);
    L84:
        throw th;
    L15:
        th = th;
    L73:
        if (r2 == null) goto L75;
        r2.release();     // Catch: Throwable -> L62
    L75:
        throw th;     // Catch: Throwable -> L62
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        r12 = ((C01151) r32).Z$0;
        r22 = (Mutex) ((C01151) r32).L$0;
        ResultKt.throwOnFailure(r0);     // Catch: Throwable -> L22
        boolean r13 = r12;
    L30:
        if (r13 == false) goto L32;
        r22.unlock(null);
    L32:
        return r0;
    L22:
        th = th;
        goto L82
    L24:
        ResultKt.throwOnFailure(r0);
        Mutex r52 = this.inMemoryMutex;
        boolean r9 = r52.tryLock(null);
        if (r9 == true) goto L35;
        Boolean r02 = Boxing.boxBoolean(false);     // Catch: Throwable -> L33
        ((C01151) r32).L$0 = r52;     // Catch: Throwable -> L33
        ((C01151) r32).Z$0 = r9;     // Catch: Throwable -> L33
        ((C01151) r32).label = 1;     // Catch: Throwable -> L33
        r0 = r19.invoke(r02, (Object) r32);     // Catch: Throwable -> L33
        if (r0 == r42) goto L100;
        r22 = r52;
        r13 = r9;
    L100:
        return r42;
    L35:
        FileInputStream r11 = new FileInputStream(getLockFile());     // Catch: Throwable -> L33
        FileLock r14 = r11.getChannel().tryLock(0, LongCompanionObject.MAX_VALUE, true);     // Catch: Throwable -> L38 IOException -> L41
    L52:
        if (r14 != null) goto L95;
        r7 = false;
    L95:
        Boolean r03 = Boxing.boxBoolean(r7);     // Catch: Throwable -> L70
        ((C01151) r32).L$0 = r52;     // Catch: Throwable -> L70
        ((C01151) r32).L$1 = r11;     // Catch: Throwable -> L70
        ((C01151) r32).L$2 = r14;     // Catch: Throwable -> L70
        ((C01151) r32).Z$0 = r9;     // Catch: Throwable -> L70
        ((C01151) r32).label = 2;     // Catch: Throwable -> L70
        r0 = r19.invoke(r03, (Object) r32);     // Catch: Throwable -> L70
        if (r0 == r42) goto L100;
        r2 = r14;
        r32 = r52;
        r12 = r9;
        r43 = r11;
    L70:
        th = th;
        r2 = r14;
        goto L73
    L41:
        e = move-exception;
        String r122 = e.getMessage();     // Catch: Throwable -> L38
        if (r122 != null) goto L45;
    L47:
        String r15 = e.getMessage();     // Catch: Throwable -> L38
        if (r15 != null) goto L50;
    L72:
        throw e;     // Catch: Throwable -> L38
    L50:
        if (StringsKt.startsWith$default(r15, DEADLOCK_ERROR_MESSAGE, false, 2, null) != true) goto L72;
    L51:
        r14 = null;
        goto L52
    L45:
        if (StringsKt.startsWith$default(r122, this.LOCK_ERROR_MESSAGE, false, 2, null) != true) goto L47;
    L38:
        th = th;
        r2 = null;
    L33:
        th = th;
        r22 = r52;
        r12 = r9;
    L62:
        th = move-exception;
        throw th;     // Catch: Throwable -> L77
    L77:
        th = move-exception;
        CloseableKt.closeFinally(r42, th);     // Catch: Throwable -> L80
        throw th;     // Catch: Throwable -> L80
    L80:
        th = th;
        r12 = this;
    L7:
        r32 = new C01151(this, r20);
        goto L8
    }
}
