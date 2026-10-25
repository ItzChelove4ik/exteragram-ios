package androidx.room;

import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ThreadContextElementKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a8\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u001c\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002H\u0080@¢\u0006\u0004\b\u0006\u0010\u0007\u001aC\u0010\u000e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\b¢\u0006\u0002\b\nH\u0082@¢\u0006\u0004\b\f\u0010\r\u001a\u001b\u0010\u0014\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a8\u0010\u0015\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u001c\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002H\u0080@¢\u0006\u0004\b\u0015\u0010\u0007¨\u0006\u0016"}, d2 = {"R", "Landroidx/room/RoomDatabase;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "block", "withTransactionContext", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/ExtensionFunctionType;", "transactionBlock", "startTransactionCoroutine$RoomDatabaseKt__RoomDatabase_androidKt", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startTransactionCoroutine", "Lkotlin/coroutines/ContinuationInterceptor;", "dispatcher", "Lkotlin/coroutines/CoroutineContext;", "createTransactionContext$RoomDatabaseKt__RoomDatabase_androidKt", "(Landroidx/room/RoomDatabase;Lkotlin/coroutines/ContinuationInterceptor;)Lkotlin/coroutines/CoroutineContext;", "createTransactionContext", "compatTransactionCoroutineExecute", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/RoomDatabaseKt")
@SourceDebugExtension({"SMAP\nRoomDatabase.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabaseKt__RoomDatabase_androidKt\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,2191:1\n318#2,11:2192\n*S KotlinDebug\n*F\n+ 1 RoomDatabase.android.kt\nandroidx/room/RoomDatabaseKt__RoomDatabase_androidKt\n*L\n2077#1:2192,11\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class RoomDatabaseKt__RoomDatabase_androidKt {
    public static final /* synthetic */ CoroutineContext access$createTransactionContext(RoomDatabase r0, ContinuationInterceptor r1) {
        return createTransactionContext$RoomDatabaseKt__RoomDatabase_androidKt(r0, r1);
    }

    public static final <R> Object compatTransactionCoroutineExecute(RoomDatabase r2, Function1<? super Continuation<? super R>, ? extends Object> r3, Continuation<? super R> r4) {
        if (r2.inCompatibilityMode() == false) goto L11;
        if (r2.isOpenInternal$room_runtime() == false) goto L11;
        if (r2.inTransaction() == false) goto L11;
        return r3.invoke(r4);
    L11:
        if (r4.getContext().get(RoomExternalOperationElement.INSTANCE) != null) goto L15;
        return r3.invoke(r4);
    L15:
        return RoomDatabaseKt.withTransactionContext(r2, r3, r4);
    }

    private static final CoroutineContext createTransactionContext$RoomDatabaseKt__RoomDatabase_androidKt(RoomDatabase r1, ContinuationInterceptor r2) {
        CoroutineContext r22 = r2.plus(new TransactionElement(r2));
        return r22.plus(ThreadContextElementKt.asContextElement(r1.getSuspendingTransactionContext(), r22));
    }

    private static final <R> Object startTransactionCoroutine$RoomDatabaseKt__RoomDatabase_androidKt(RoomDatabase r3, Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> r4, Continuation<? super R> r5) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r5), 1);
        r0.initCancellability();
        r3.getTransactionExecutor().execute(new RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1(r0, r3, r4));     // Catch: RejectedExecutionException -> L5
    L7:
        Object r32 = r0.getResult();
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        DebugProbesKt.probeCoroutineSuspended(r5);
    L10:
        return r32;
    L5:
        e = move-exception;
        r0.cancel(new IllegalStateException("Unable to acquire a thread to perform the database transaction.", e));
        goto L7
    }

    public static final <R> Object withTransactionContext(RoomDatabase r3, Function1<? super Continuation<? super R>, ? extends Object> r4, Continuation<? super R> r5) {
        ContinuationInterceptor r1 = null;
        RoomDatabaseKt__RoomDatabase_androidKt$withTransactionContext$transactionBlock$1 r0 = new RoomDatabaseKt__RoomDatabase_androidKt$withTransactionContext$transactionBlock$1(r4, null);
        TransactionElement r42 = (TransactionElement) r5.getContext().get(TransactionElement.Key);
        if (r42 == null) goto L5;
        r1 = r42.getTransactionDispatcher$room_runtime();
    L5:
        if (r1 == null) goto L9;
        return BuildersKt.withContext(r1, r0, r5);
    L9:
        return startTransactionCoroutine$RoomDatabaseKt__RoomDatabase_androidKt(r3, r0, r5);
    }
}
