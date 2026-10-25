package androidx.datastore.core;

import com.android.dx.io.Opcodes;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\b\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0016JX\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0001\u0010\u00162B\u0010\u0017\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0018¢\u0006\u0002\b H\u0096@¢\u0006\u0002\u0010!J=\u0010\"\u001a\u00020\u000b2-\u0010\u0017\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0#¢\u0006\u0002\b H\u0096@¢\u0006\u0002\u0010%J\f\u0010&\u001a\u00020\u000b*\u00020\u0004H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/datastore/core/FileStorageConnection;", "T", "Landroidx/datastore/core/StorageConnection;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "onClose", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/io/File;Landroidx/datastore/core/Serializer;Landroidx/datastore/core/InterProcessCoordinator;Lkotlin/jvm/functions/Function0;)V", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "transactionMutex", "Lkotlinx/coroutines/sync/Mutex;", "checkNotClosed", "close", "readScope", "R", "block", "Lkotlin/Function3;", "Landroidx/datastore/core/ReadScope;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "locked", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeScope", "Lkotlin/Function2;", "Landroidx/datastore/core/WriteScope;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createParentDirectories", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFileStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileStorage.kt\nandroidx/datastore/core/FileStorageConnection\n+ 2 Closeable.kt\nandroidx/datastore/core/CloseableKt\n+ 3 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,208:1\n38#2,23:209\n38#2,23:240\n120#3,8:232\n129#3:263\n1#4:264\n*S KotlinDebug\n*F\n+ 1 FileStorage.kt\nandroidx/datastore/core/FileStorageConnection\n*L\n100#1:209,23\n117#1:240,23\n114#1:232,8\n114#1:263\n*E\n"})
/* loaded from: classes.dex */
public final class FileStorageConnection<T> implements StorageConnection<T> {
    private final AtomicBoolean closed;
    private final InterProcessCoordinator coordinator;
    private final File file;
    private final Function0<Unit> onClose;
    private final Serializer<T> serializer;
    private final Mutex transactionMutex;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.FileStorageConnection", f = "FileStorage.kt", i = {0, 0, 0}, l = {101}, m = "readScope", n = {"this", "$this$use$iv", "lock"}, s = {"L$0", "L$1", "Z$0"})
    /* renamed from: androidx.datastore.core.FileStorageConnection$readScope$1, reason: invalid class name */
    public static final class AnonymousClass1<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FileStorageConnection<T> this$0;

        public AnonymousClass1(FileStorageConnection<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readScope(null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.FileStorageConnection", f = "FileStorage.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {Opcodes.OR_INT_LIT16, 118}, m = "writeScope", n = {"this", "block", "$this$withLock_u24default$iv", "this", "$this$withLock_u24default$iv", "scratchFile", "$this$use$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    /* renamed from: androidx.datastore.core.FileStorageConnection$writeScope$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01141 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FileStorageConnection<T> this$0;

        public C01141(FileStorageConnection<T> r1, Continuation<? super C01141> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.writeScope(null, this);
        }
    }

    public FileStorageConnection(File r1, Serializer<T> r2, InterProcessCoordinator r3, Function0<Unit> r4) {
        this.file = r1;
        this.serializer = r2;
        this.coordinator = r3;
        this.onClose = r4;
        this.closed = new AtomicBoolean(false);
        this.transactionMutex = MutexKt.Mutex$default(false, 1, null);
    }

    private final void checkNotClosed() {
        if (this.closed.get() == true) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("StorageConnection has already been disposed.");
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

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
        this.onClose.invoke();
    }

    @Override // androidx.datastore.core.StorageConnection
    public InterProcessCoordinator getCoordinator() {
        return this.coordinator;
    }

    @Override // androidx.datastore.core.StorageConnection
    public <R> Object readScope(Function3<? super ReadScope<T>, ? super Boolean, ? super Continuation<? super R>, ? extends Object> r9, Continuation<? super R> r10) {
        if ((r10 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        boolean r8 = r0.Z$0;
        Closeable r92 = (Closeable) r0.L$1;
        FileStorageConnection<T> r02 = (FileStorageConnection) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L14
    L47:
        r92.close();     // Catch: Throwable -> L26
        th = null;
    L27:
        if (th != null) goto L55;
        if (r8 == false) goto L30;
        Mutex.DefaultImpls.unlock$default(r02.transactionMutex, null, 1, null);
    L30:
        return r102;
    L55:
        throw th;     // Catch: Throwable -> L32
    L32:
        th = th;
        boolean r103 = r8;
        FileStorageConnection<T> this = r02;
    L42:
        if (r103 == false) goto L44;
        Mutex.DefaultImpls.unlock$default(this.transactionMutex, null, 1, null);
    L44:
        throw th;
    L26:
        th = th;
    L14:
        th = move-exception;
        r103 = r8;
        this = r02;
        Throwable r03 = th;
    L51:
        r92.close();     // Catch: Throwable -> L38
    L57:
        throw r03;     // Catch: Throwable -> L41
    L38:
        th = move-exception;
        ExceptionsKt.addSuppressed(r03, th);     // Catch: Throwable -> L41
        throw r03;     // Catch: Throwable -> L41
    L41:
        th = th;
        goto L42
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r102);
        checkNotClosed();
        r103 = Mutex.DefaultImpls.tryLock$default(this.transactionMutex, null, 1, null);
        FileReadScope r22 = new FileReadScope(this.file, this.serializer);     // Catch: Throwable -> L41
        Boolean r5 = Boxing.boxBoolean(r103);     // Catch: Throwable -> L34
        r0.L$0 = this;     // Catch: Throwable -> L34
        r0.L$1 = r22;     // Catch: Throwable -> L34
        r0.Z$0 = r103;     // Catch: Throwable -> L34
        r0.label = 1;     // Catch: Throwable -> L34
        Object r93 = r9.invoke(r22, r5, r0);     // Catch: Throwable -> L34
        if (r93 != r12) goto L23;
        return r12;
    L23:
        r02 = this;
        r8 = r103;
        r102 = r93;
        r92 = r22;
    L34:
        th = move-exception;
        r03 = th;
        r92 = r22;
    L7:
        r0 = new AnonymousClass1(this, r10);
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.File, java.lang.Object] */
    @Override // androidx.datastore.core.StorageConnection
    public Object writeScope(Function2<? super WriteScope<T>, ? super Continuation<? super Unit>, ? extends Object> r9, Continuation<? super Unit> r10) {
        if ((r10 instanceof C01141) == false) goto L7;
        C01141 r1 = (C01141) r10;
        int r2 = r1.label;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L7;
        r1.label = r2 - Integer.MIN_VALUE;
    L8:
        Object r102 = r1.result;
        Object r22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r3 = r1.label;
        if (r3 == 0) goto L20;
        if (r3 == 1) goto L19;
        if (r3 != 2) goto L17;
        Closeable r8 = (Closeable) r1.L$3;
        File r92 = (File) r1.L$2;
        Mutex r23 = (Mutex) r1.L$1;
        FileStorageConnection<T> r12 = (FileStorageConnection) r1.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L15
    L29:
        Unit r103 = Unit.INSTANCE;     // Catch: Throwable -> L15
        r8.close();     // Catch: Throwable -> L32
        th = null;
    L33:
        if (th == null) goto L35;
        throw th;     // Catch: Throwable -> L41 IOException -> L43
    L35:
        if (r92.exists() == true) goto L37;
    L45:
        Unit r82 = Unit.INSTANCE;     // Catch: Throwable -> L41
        r23.unlock(null);
        return Unit.INSTANCE;
    L37:
        if (FileMoves_androidKt.atomicMoveTo(r92, r12.file) == true) goto L45;
        throw new IOException("Unable to rename " + r92 + " to " + r12.file + ". This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file.");     // Catch: Throwable -> L41 IOException -> L43
    L32:
        th = th;
    L15:
        Throwable th = th;
    L69:
        r8.close();     // Catch: Throwable -> L53
    L55:
        throw th;     // Catch: Throwable -> L41 IOException -> L43
    L53:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);     // Catch: Throwable -> L41 IOException -> L43
        goto L55
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        Mutex r83 = (Mutex) r1.L$2;
        r9 = (Function2) r1.L$1;
        FileStorageConnection<T> r32 = (FileStorageConnection) r1.L$0;
        ResultKt.throwOnFailure(r102);
        ?? r104 = r83;
        FileStorageConnection<T> this = r32;
    L73:
        ?? r33 = new File(this.file.getAbsolutePath() + ".tmp");     // Catch: Throwable -> L56
        FileWriteScope r5 = new FileWriteScope(r33, this.serializer);     // Catch: Throwable -> L56 IOException -> L58
        r1.L$0 = this;     // Catch: Throwable -> L49
        r1.L$1 = r104;     // Catch: Throwable -> L49
        r1.L$2 = r33;     // Catch: Throwable -> L49
        r1.L$3 = r5;     // Catch: Throwable -> L49
        r1.label = 2;     // Catch: Throwable -> L49
        if (r9.invoke(r5, r1) != r22) goto L28;
    L27:
        return r22;
    L28:
        r12 = this;
        r23 = r104;
        r92 = r33;
        r8 = r5;
    L49:
        th = move-exception;
        th = th;
        r8 = r5;
    L58:
        e = e;
    L60:
        if (r33.exists() == false) goto L62;
        r33.delete();     // Catch: Throwable -> L56
    L62:
        throw e;     // Catch: Throwable -> L56
    L56:
        th = th;
    L63:
        r104.unlock(null);
        throw th;
    L20:
        ResultKt.throwOnFailure(r102);
        checkNotClosed();
        createParentDirectories(this.file);
        Mutex r105 = this.transactionMutex;
        r1.L$0 = this;
        r1.L$1 = r9;
        r1.L$2 = r105;
        r1.label = 1;
        Object r34 = r105.lock(null, r1);
        r104 = r105;
        if (r34 != r22) goto L73;
    L41:
        th = th;
        r104 = r22;
    L43:
        e = e;
        r33 = r9;
        r104 = r22;
    L7:
        r1 = new C01141(this, r10);
        goto L8
    }
}
