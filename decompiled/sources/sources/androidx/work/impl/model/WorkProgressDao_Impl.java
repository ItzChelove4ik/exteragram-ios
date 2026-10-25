package androidx.work.impl.model;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import androidx.work.Data;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Landroidx/work/impl/model/WorkProgressDao_Impl;", "Landroidx/work/impl/model/WorkProgressDao;", "Landroidx/room/RoomDatabase;", "__db", "<init>", "(Landroidx/room/RoomDatabase;)V", "Landroidx/work/impl/model/WorkProgress;", "progress", _UrlKt.FRAGMENT_ENCODE_SET, "insert", "(Landroidx/work/impl/model/WorkProgress;)V", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecId", "delete", "(Ljava/lang/String;)V", "deleteAll", "()V", "Landroidx/room/RoomDatabase;", "Landroidx/room/EntityInsertAdapter;", "__insertAdapterOfWorkProgress", "Landroidx/room/EntityInsertAdapter;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WorkProgressDao_Impl implements WorkProgressDao {
    public static final Companion Companion = null;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<WorkProgress> __insertAdapterOfWorkProgress;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"androidx/work/impl/model/WorkProgressDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Landroidx/work/impl/model/WorkProgress;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.work.impl.model.WorkProgressDao_Impl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EntityInsertAdapter<WorkProgress> {
        public AnonymousClass1() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r2, WorkProgress r3) {
            r2.bindText(1, r3.getWorkSpecId());
            r2.bindBlob(2, Data.Companion.toByteArrayInternalV1(r3.getProgress()));
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, WorkProgress r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/model/WorkProgressDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }

    /* renamed from: $r8$lambda$EO0gVZoCNSdI-rokJ-pZCcUBsgk, reason: not valid java name */
    public static Unit m988$r8$lambda$EO0gVZoCNSdIrokJpZCcUBsgk(String r0, String r1, SQLiteConnection r2) {
        SQLiteStatement r02 = r2.prepare(r0);
        r02.bindText(1, r1);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    /* renamed from: $r8$lambda$HxEzdbtI1meBd-aXYgHdDeKTIRA, reason: not valid java name */
    public static Unit m989$r8$lambda$HxEzdbtI1meBdaXYgHdDeKTIRA(String r0, SQLiteConnection r1) {
        SQLiteStatement r02 = r1.prepare(r0);
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    /* renamed from: $r8$lambda$PINVEeB__LLptu-NxhS4Ei1b1_s, reason: not valid java name */
    public static Unit m990$r8$lambda$PINVEeB__LLptuNxhS4Ei1b1_s(WorkProgressDao_Impl r0, WorkProgress r1, SQLiteConnection r2) {
        r0.__insertAdapterOfWorkProgress.insert(r2, r1);
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public WorkProgressDao_Impl(RoomDatabase r1) {
        this.__db = r1;
        this.__insertAdapterOfWorkProgress = new AnonymousClass1();
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void delete(String r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkProgressDao_Impl$$ExternalSyntheticLambda1("DELETE from WorkProgress where work_spec_id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void deleteAll() {
        DBUtil.performBlocking(this.__db, false, true, new WorkProgressDao_Impl$$ExternalSyntheticLambda0("DELETE FROM WorkProgress"));
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void insert(WorkProgress r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkProgressDao_Impl$$ExternalSyntheticLambda2(this, r3));
    }
}
