package androidx.room;

import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"androidx/room/RoomDatabaseKt__RoomDatabaseKt", "androidx/room/RoomDatabaseKt__RoomDatabase_androidKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class RoomDatabaseKt {
    public static final <R> Object compatTransactionCoroutineExecute(RoomDatabase r0, Function1<? super Continuation<? super R>, ? extends Object> r1, Continuation<? super R> r2) {
        return RoomDatabaseKt__RoomDatabase_androidKt.compatTransactionCoroutineExecute(r0, r1, r2);
    }

    public static final void validateAutoMigrations(RoomDatabase r0, DatabaseConfiguration r1) {
        RoomDatabaseKt__RoomDatabaseKt.validateAutoMigrations(r0, r1);
    }

    public static final void validateMigrationsNotRequired(Set<Integer> r0, Set<Integer> r1) {
        RoomDatabaseKt__RoomDatabaseKt.validateMigrationsNotRequired(r0, r1);
    }

    public static final void validateTypeConverters(RoomDatabase r0, DatabaseConfiguration r1) {
        RoomDatabaseKt__RoomDatabaseKt.validateTypeConverters(r0, r1);
    }

    public static final <R> Object withTransactionContext(RoomDatabase r0, Function1<? super Continuation<? super R>, ? extends Object> r1, Continuation<? super R> r2) {
        return RoomDatabaseKt__RoomDatabase_androidKt.withTransactionContext(r0, r1, r2);
    }
}
