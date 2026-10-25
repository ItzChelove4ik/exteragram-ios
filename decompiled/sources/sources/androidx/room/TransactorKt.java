package androidx.room;

import androidx.sqlite.SQLiteStatement;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/room/PooledConnection;", _UrlKt.FRAGMENT_ENCODE_SET, "sql", _UrlKt.FRAGMENT_ENCODE_SET, "execSQL", "(Landroidx/room/PooledConnection;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class TransactorKt {
    public static boolean $r8$lambda$VVj_enrVNhrquoXbSKTrpTfnIsI(SQLiteStatement r0) {
        return r0.step();
    }

    public static final Object execSQL(PooledConnection r1, String r2, Continuation<? super Unit> r3) {
        Object r12 = r1.usePrepared(r2, new TransactorKt$$ExternalSyntheticLambda0(), r3);
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r12;
    L6:
        return Unit.INSTANCE;
    }
}
