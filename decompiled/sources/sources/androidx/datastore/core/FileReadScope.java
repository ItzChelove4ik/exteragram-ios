package androidx.datastore.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u000e\u0010\u0011\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Landroidx/datastore/core/FileReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "(Ljava/io/File;Landroidx/datastore/core/Serializer;)V", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getFile", "()Ljava/io/File;", "getSerializer", "()Landroidx/datastore/core/Serializer;", "checkNotClosed", _UrlKt.FRAGMENT_ENCODE_SET, "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFileStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileStorage.kt\nandroidx/datastore/core/FileReadScope\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,208:1\n1#2:209\n*E\n"})
/* loaded from: classes.dex */
public class FileReadScope<T> implements ReadScope<T> {
    private final AtomicBoolean closed;
    private final File file;
    private final Serializer<T> serializer;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.FileReadScope", f = "FileStorage.kt", i = {0}, l = {169, 178}, m = "readData$suspendImpl", n = {"$this"}, s = {"L$0"})
    /* renamed from: androidx.datastore.core.FileReadScope$readData$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FileReadScope<T> this$0;

        public AnonymousClass1(FileReadScope<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return FileReadScope.readData$suspendImpl(this.this$0, this);
        }
    }

    public FileReadScope(File r1, Serializer<T> r2) {
        this.file = r1;
        this.serializer = r2;
        this.closed = new AtomicBoolean(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11, types: [androidx.datastore.core.FileReadScope] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static /* synthetic */ <T> Object readData$suspendImpl(FileReadScope<T> r7, Continuation<? super T> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = r0.label;
        if (r2 == 0) goto L24;
        if (r2 == 1) goto L19;
        if (r2 != 2) goto L17;
        java.io.Closeable r72 = (java.io.Closeable) r0.L$0;
        ResultKt.throwOnFailure(r82);     // Catch: Throwable -> L15
    L46:
        CloseableKt.closeFinally(r72, null);
        return r82;
    L15:
        Throwable th = th;
    L59:
        throw th;     // Catch: Throwable -> L51
    L51:
        th = move-exception;
        CloseableKt.closeFinally(r72, th);
        throw th;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        java.io.Closeable r73 = (java.io.Closeable) r0.L$1;
        r2 = (FileReadScope) r0.L$0;
        ResultKt.throwOnFailure(r82);     // Catch: Throwable -> L22
    L30:
        CloseableKt.closeFinally(r73, null);     // Catch: FileNotFoundException -> L32
        return r82;
    L22:
        Throwable th2 = th;
    L61:
        throw th2;     // Catch: Throwable -> L36
    L36:
        th = move-exception;
        CloseableKt.closeFinally(r73, th2);     // Catch: FileNotFoundException -> L32
        throw th;     // Catch: FileNotFoundException -> L32
    L24:
        ResultKt.throwOnFailure(r82);
        r7.checkNotClosed();
        FileInputStream r83 = new FileInputStream(((FileReadScope) r7).file);     // Catch: FileNotFoundException -> L56
        Serializer<T> r22 = ((FileReadScope) r7).serializer;     // Catch: Throwable -> L33
        r0.L$0 = r7;     // Catch: Throwable -> L33
        r0.L$1 = r83;     // Catch: Throwable -> L33
        r0.label = 1;     // Catch: Throwable -> L33
        Object r23 = r22.readFrom(r83, r0);     // Catch: Throwable -> L33
        if (r23 == r12) goto L44;
        r73 = r83;
        r82 = r23;
    L44:
        return r12;
    L33:
        th = move-exception;
        r2 = r7;
        r73 = r83;
        th2 = th;
    L40:
        if (((FileReadScope) r7).file.exists() == false) goto L55;
        FileInputStream r84 = new FileInputStream(((FileReadScope) r7).file);
        Serializer<T> r74 = ((FileReadScope) r7).serializer;     // Catch: Throwable -> L48
        r0.L$0 = r84;     // Catch: Throwable -> L48
        r0.L$1 = null;     // Catch: Throwable -> L48
        r0.label = 2;     // Catch: Throwable -> L48
        Object r75 = r74.readFrom(r84, r0);     // Catch: Throwable -> L48
        if (r75 == r12) goto L44;
        r82 = r75;
        r72 = r84;
    L48:
        th = move-exception;
        th = th;
        r72 = r84;
        goto L59
    L55:
        return ((FileReadScope) r7).serializer.getDefaultValue();
    L32:
        r7 = r2;
    L7:
        r0 = new AnonymousClass1(r7, r8);
        goto L8
    }

    public final void checkNotClosed() {
        if (this.closed.get() == true) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("This scope has already been closed.");
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    public final File getFile() {
        return this.file;
    }

    public final Serializer<T> getSerializer() {
        return this.serializer;
    }

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> r1) {
        return readData$suspendImpl(this, r1);
    }
}
