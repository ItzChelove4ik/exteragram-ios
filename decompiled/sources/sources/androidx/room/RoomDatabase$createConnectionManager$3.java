package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* synthetic */ class RoomDatabase$createConnectionManager$3 extends FunctionReferenceImpl implements Function2<Function1<? super Continuation<? super Object>, ? extends Object>, Continuation<? super Object>, Object>, SuspendFunction {
    public RoomDatabase$createConnectionManager$3(Object r8) {
        super(2, r8, RoomDatabaseKt__RoomDatabase_androidKt.class, "compatTransactionCoroutineExecute", "compatTransactionCoroutineExecute(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Function1<? super Continuation<? super Object>, ? extends Object> r1, Continuation<? super Object> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Function1<? super Continuation<Object>, ? extends Object> r1, Continuation<Object> r2) {
        return RoomDatabaseKt.compatTransactionCoroutineExecute((RoomDatabase) this.receiver, r1, r2);
    }
}
