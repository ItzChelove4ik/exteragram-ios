package androidx.work.impl.model;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/model/WorkNameDao_Impl;", "Landroidx/work/impl/model/WorkNameDao;", "Landroidx/room/RoomDatabase;", "__db", "<init>", "(Landroidx/room/RoomDatabase;)V", "Landroidx/work/impl/model/WorkName;", "workName", _UrlKt.FRAGMENT_ENCODE_SET, "insert", "(Landroidx/work/impl/model/WorkName;)V", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecId", _UrlKt.FRAGMENT_ENCODE_SET, "getNamesForWorkSpecId", "(Ljava/lang/String;)Ljava/util/List;", "Landroidx/room/RoomDatabase;", "Landroidx/room/EntityInsertAdapter;", "__insertAdapterOfWorkName", "Landroidx/room/EntityInsertAdapter;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WorkNameDao_Impl implements WorkNameDao {
    public static final Companion Companion = null;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<WorkName> __insertAdapterOfWorkName;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"androidx/work/impl/model/WorkNameDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Landroidx/work/impl/model/WorkName;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.work.impl.model.WorkNameDao_Impl$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends EntityInsertAdapter<WorkName> {
        public AnonymousClass1() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r2, WorkName r3) {
            r2.bindText(1, r3.getName());
            r2.bindText(2, r3.getWorkSpecId());
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, WorkName r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/model/WorkNameDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

    /* renamed from: $r8$lambda$-fsgc83kSCsDR3Yh-aHZVVR12EY, reason: not valid java name */
    public static List m987$r8$lambda$fsgc83kSCsDR3YhaHZVVR12EY(String r0, String r1, SQLiteConnection r2) {
        SQLiteStatement r02 = r2.prepare(r0);
        r02.bindText(1, r1);     // Catch: Throwable -> L8
        ArrayList r12 = new ArrayList();     // Catch: Throwable -> L8
    L4:
        if (r02.step() == false) goto L10;
        r12.add(r02.getText(0));     // Catch: Throwable -> L8
        goto L4
    L10:
        r02.close();
        return r12;
    L8:
        th = move-exception;
        r02.close();
        throw th;
    }

    public static Unit $r8$lambda$dEb2KnOmeWOQKPp7ENoTNKTrqo0(WorkNameDao_Impl r0, WorkName r1, SQLiteConnection r2) {
        r0.__insertAdapterOfWorkName.insert(r2, r1);
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public WorkNameDao_Impl(RoomDatabase r1) {
        this.__db = r1;
        this.__insertAdapterOfWorkName = new AnonymousClass1();
    }

    @Override // androidx.work.impl.model.WorkNameDao
    public List<String> getNamesForWorkSpecId(String r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkNameDao_Impl$$ExternalSyntheticLambda1("SELECT name FROM workname WHERE work_spec_id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkNameDao
    public void insert(WorkName r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkNameDao_Impl$$ExternalSyntheticLambda0(this, r3));
    }
}
