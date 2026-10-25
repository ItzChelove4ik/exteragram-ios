package androidx.work.impl.model;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
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

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ!\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/work/impl/model/SystemIdInfoDao_Impl;", "Landroidx/work/impl/model/SystemIdInfoDao;", "Landroidx/room/RoomDatabase;", "__db", "<init>", "(Landroidx/room/RoomDatabase;)V", "Landroidx/work/impl/model/SystemIdInfo;", "systemIdInfo", _UrlKt.FRAGMENT_ENCODE_SET, "insertSystemIdInfo", "(Landroidx/work/impl/model/SystemIdInfo;)V", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecId", _UrlKt.FRAGMENT_ENCODE_SET, "generation", "getSystemIdInfo", "(Ljava/lang/String;I)Landroidx/work/impl/model/SystemIdInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "getWorkSpecIds", "()Ljava/util/List;", "removeSystemIdInfo", "(Ljava/lang/String;)V", "Landroidx/room/RoomDatabase;", "Landroidx/room/EntityInsertAdapter;", "__insertAdapterOfSystemIdInfo", "Landroidx/room/EntityInsertAdapter;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SystemIdInfoDao_Impl implements SystemIdInfoDao {
    public static final Companion Companion = null;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<SystemIdInfo> __insertAdapterOfSystemIdInfo;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"androidx/work/impl/model/SystemIdInfoDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Landroidx/work/impl/model/SystemIdInfo;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.work.impl.model.SystemIdInfoDao_Impl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EntityInsertAdapter<SystemIdInfo> {
        public AnonymousClass1() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r3, SystemIdInfo r4) {
            r3.bindText(1, r4.workSpecId);
            r3.bindLong(2, r4.getGeneration());
            r3.bindLong(3, r4.systemId);
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, SystemIdInfo r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/model/SystemIdInfoDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

    public static Unit $r8$lambda$JorSSkfx0VKpVUcKGGbvimtM424(String r0, String r1, SQLiteConnection r2) {
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

    /* renamed from: $r8$lambda$NU7j-t1lTm0KFasmFmR5rCrby_s, reason: not valid java name */
    public static List m986$r8$lambda$NU7jt1lTm0KFasmFmR5rCrby_s(String r1, SQLiteConnection r2) {
        SQLiteStatement r12 = r2.prepare(r1);
        ArrayList r22 = new ArrayList();     // Catch: Throwable -> L8
    L4:
        if (r12.step() == false) goto L10;
        r22.add(r12.getText(0));     // Catch: Throwable -> L8
        goto L4
    L10:
        r12.close();
        return r22;
    L8:
        th = move-exception;
        r12.close();
        throw th;
    }

    public static SystemIdInfo $r8$lambda$_Qoh73xPLN2MyFaWI5Zm_sd1gdE(String r2, String r3, int r4, SQLiteConnection r5) {
        SQLiteStatement r22 = r5.prepare(r2);
        r22.bindText(1, r3);     // Catch: Throwable -> L7
        r22.bindLong(2, r4);     // Catch: Throwable -> L7
        int r32 = SQLiteStatementUtil.getColumnIndexOrThrow(r22, "work_spec_id");     // Catch: Throwable -> L7
        int r42 = SQLiteStatementUtil.getColumnIndexOrThrow(r22, "generation");     // Catch: Throwable -> L7
        int r52 = SQLiteStatementUtil.getColumnIndexOrThrow(r22, "system_id");     // Catch: Throwable -> L7
        if (r22.step() == false) goto L9;
        SystemIdInfo r0 = new SystemIdInfo(r22.getText(r32), (int) r22.getLong(r42), (int) r22.getLong(r52));     // Catch: Throwable -> L7
    L10:
        r22.close();
        return r0;
    L9:
        r0 = null;
    L7:
        th = move-exception;
        r22.close();
        throw th;
    }

    public static Unit $r8$lambda$cNkqBNf5uI5fNlIEL4Q8wrQs27Q(SystemIdInfoDao_Impl r0, SystemIdInfo r1, SQLiteConnection r2) {
        r0.__insertAdapterOfSystemIdInfo.insert(r2, r1);
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public SystemIdInfoDao_Impl(RoomDatabase r1) {
        this.__db = r1;
        this.__insertAdapterOfSystemIdInfo = new AnonymousClass1();
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public SystemIdInfo getSystemIdInfo(String r3, int r4) {
        return (SystemIdInfo) DBUtil.performBlocking(this.__db, true, false, new SystemIdInfoDao_Impl$$ExternalSyntheticLambda2("SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?", r3, r4));
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public List<String> getWorkSpecIds() {
        return (List) DBUtil.performBlocking(this.__db, true, false, new SystemIdInfoDao_Impl$$ExternalSyntheticLambda1("SELECT DISTINCT work_spec_id FROM SystemIdInfo"));
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public void insertSystemIdInfo(SystemIdInfo r3) {
        DBUtil.performBlocking(this.__db, false, true, new SystemIdInfoDao_Impl$$ExternalSyntheticLambda0(this, r3));
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(String r3) {
        DBUtil.performBlocking(this.__db, false, true, new SystemIdInfoDao_Impl$$ExternalSyntheticLambda3("DELETE FROM SystemIdInfo where work_spec_id=?", r3));
    }
}
