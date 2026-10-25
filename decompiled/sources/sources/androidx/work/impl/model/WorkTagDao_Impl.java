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

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/work/impl/model/WorkTagDao_Impl;", "Landroidx/work/impl/model/WorkTagDao;", "Landroidx/room/RoomDatabase;", "__db", "<init>", "(Landroidx/room/RoomDatabase;)V", "Landroidx/work/impl/model/WorkTag;", "workTag", _UrlKt.FRAGMENT_ENCODE_SET, "insert", "(Landroidx/work/impl/model/WorkTag;)V", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "getTagsForWorkSpecId", "(Ljava/lang/String;)Ljava/util/List;", "deleteByWorkSpecId", "(Ljava/lang/String;)V", "Landroidx/room/RoomDatabase;", "Landroidx/room/EntityInsertAdapter;", "__insertAdapterOfWorkTag", "Landroidx/room/EntityInsertAdapter;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WorkTagDao_Impl implements WorkTagDao {
    public static final Companion Companion = null;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<WorkTag> __insertAdapterOfWorkTag;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"androidx/work/impl/model/WorkTagDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Landroidx/work/impl/model/WorkTag;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.work.impl.model.WorkTagDao_Impl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EntityInsertAdapter<WorkTag> {
        public AnonymousClass1() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r2, WorkTag r3) {
            r2.bindText(1, r3.getTag());
            r2.bindText(2, r3.getWorkSpecId());
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, WorkTag r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/model/WorkTagDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

    /* renamed from: $r8$lambda$3mSVYlBFYAbyMDyxo-NuxxzbzUM, reason: not valid java name */
    public static Unit m998$r8$lambda$3mSVYlBFYAbyMDyxoNuxxzbzUM(WorkTagDao_Impl r0, WorkTag r1, SQLiteConnection r2) {
        r0.__insertAdapterOfWorkTag.insert(r2, r1);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$7aU4e-xkilK3ltTBUxu2zfncHvo, reason: not valid java name */
    public static List m999$r8$lambda$7aU4exkilK3ltTBUxu2zfncHvo(String r0, String r1, SQLiteConnection r2) {
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

    /* renamed from: $r8$lambda$YL6Dh5dgDwRNAe3gmNOuWiGz0-E, reason: not valid java name */
    public static Unit m1000$r8$lambda$YL6Dh5dgDwRNAe3gmNOuWiGz0E(String r0, String r1, SQLiteConnection r2) {
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

    static {
        Companion = new Companion(null);
    }

    public WorkTagDao_Impl(RoomDatabase r1) {
        this.__db = r1;
        this.__insertAdapterOfWorkTag = new AnonymousClass1();
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public void deleteByWorkSpecId(String r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkTagDao_Impl$$ExternalSyntheticLambda2("DELETE FROM worktag WHERE work_spec_id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public List<String> getTagsForWorkSpecId(String r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkTagDao_Impl$$ExternalSyntheticLambda1("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public void insert(WorkTag r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkTagDao_Impl$$ExternalSyntheticLambda0(this, r3));
    }
}
