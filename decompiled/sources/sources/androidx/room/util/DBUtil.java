package androidx.room.util;

import androidx.room.RoomDatabase;
import androidx.sqlite.SQLiteConnection;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"androidx/room/util/DBUtil__DBUtilKt", "androidx/room/util/DBUtil__DBUtil_androidKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class DBUtil {
    public static final void dropFtsSyncTriggers(SQLiteConnection r0) {
        DBUtil__DBUtilKt.dropFtsSyncTriggers(r0);
    }

    public static final Object getCoroutineContext(RoomDatabase r0, boolean r1, Continuation<? super CoroutineContext> r2) {
        return DBUtil__DBUtil_androidKt.getCoroutineContext(r0, r1, r2);
    }

    public static final <R> R performBlocking(RoomDatabase r0, boolean r1, boolean r2, Function1<? super SQLiteConnection, ? extends R> r3) {
        return (R) DBUtil__DBUtil_androidKt.performBlocking(r0, r1, r2, r3);
    }

    public static final <R> Object performInTransactionSuspending(RoomDatabase r0, Function1<? super Continuation<? super R>, ? extends Object> r1, Continuation<? super R> r2) {
        return DBUtil__DBUtil_androidKt.performInTransactionSuspending(r0, r1, r2);
    }

    public static final <R> Object performSuspending(RoomDatabase r0, boolean r1, boolean r2, Function1<? super SQLiteConnection, ? extends R> r3, Continuation<? super R> r4) {
        return DBUtil__DBUtil_androidKt.performSuspending(r0, r1, r2, r3, r4);
    }

    public static final int readVersion(File r0) {
        return DBUtil__DBUtil_androidKt.readVersion(r0);
    }
}
