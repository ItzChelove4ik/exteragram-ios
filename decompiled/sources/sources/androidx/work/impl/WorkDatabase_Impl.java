package androidx.work.impl;

import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.RoomOpenDelegateMarker;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.SQLiteConnection;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.DependencyDao_Impl;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.PreferenceDao_Impl;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.RawWorkInfoDao_Impl;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoDao_Impl;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkNameDao_Impl;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkProgressDao_Impl;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.model.WorkTagDao_Impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\u0003J)\u0010\u000f\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u000e0\fH\u0014¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00120\r0\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J1\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e2\u001a\u0010\u0015\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00120\r\u0012\u0004\u0012\u00020\u00120\fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$J\u000f\u0010&\u001a\u00020%H\u0016¢\u0006\u0004\b&\u0010'J\u000f\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*J\u000f\u0010,\u001a\u00020+H\u0016¢\u0006\u0004\b,\u0010-R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00190.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020\u001c0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00100R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u001f0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00100R\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020\"0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00100R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020%0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00100R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020(0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00100R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020+0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00100R\u001a\u00108\u001a\b\u0012\u0004\u0012\u0002070.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00100¨\u00069"}, d2 = {"Landroidx/work/impl/WorkDatabase_Impl;", "Landroidx/work/impl/WorkDatabase;", "<init>", "()V", "Landroidx/room/RoomOpenDelegate;", "createOpenDelegate", "()Landroidx/room/RoomOpenDelegate;", "Landroidx/room/InvalidationTracker;", "createInvalidationTracker", "()Landroidx/room/InvalidationTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "clearAllTables", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", _UrlKt.FRAGMENT_ENCODE_SET, "getRequiredTypeConverterClasses", "()Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/migration/AutoMigrationSpec;", "getRequiredAutoMigrationSpecClasses", "()Ljava/util/Set;", "autoMigrationSpecs", "Landroidx/room/migration/Migration;", "createAutoMigrations", "(Ljava/util/Map;)Ljava/util/List;", "Landroidx/work/impl/model/WorkSpecDao;", "workSpecDao", "()Landroidx/work/impl/model/WorkSpecDao;", "Landroidx/work/impl/model/DependencyDao;", "dependencyDao", "()Landroidx/work/impl/model/DependencyDao;", "Landroidx/work/impl/model/WorkTagDao;", "workTagDao", "()Landroidx/work/impl/model/WorkTagDao;", "Landroidx/work/impl/model/SystemIdInfoDao;", "systemIdInfoDao", "()Landroidx/work/impl/model/SystemIdInfoDao;", "Landroidx/work/impl/model/WorkNameDao;", "workNameDao", "()Landroidx/work/impl/model/WorkNameDao;", "Landroidx/work/impl/model/WorkProgressDao;", "workProgressDao", "()Landroidx/work/impl/model/WorkProgressDao;", "Landroidx/work/impl/model/PreferenceDao;", "preferenceDao", "()Landroidx/work/impl/model/PreferenceDao;", "Lkotlin/Lazy;", "_workSpecDao", "Lkotlin/Lazy;", "_dependencyDao", "_workTagDao", "_systemIdInfoDao", "_workNameDao", "_workProgressDao", "_preferenceDao", "Landroidx/work/impl/model/RawWorkInfoDao;", "_rawWorkInfoDao", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WorkDatabase_Impl extends WorkDatabase {
    private final Lazy<DependencyDao> _dependencyDao;
    private final Lazy<PreferenceDao> _preferenceDao;
    private final Lazy<RawWorkInfoDao> _rawWorkInfoDao;
    private final Lazy<SystemIdInfoDao> _systemIdInfoDao;
    private final Lazy<WorkNameDao> _workNameDao;
    private final Lazy<WorkProgressDao> _workProgressDao;
    private final Lazy<WorkSpecDao> _workSpecDao;
    private final Lazy<WorkTagDao> _workTagDao;

    public static WorkTagDao_Impl $r8$lambda$232E_xBZ836NRYMgTAYNO5qULN0(WorkDatabase_Impl r1) {
        return new WorkTagDao_Impl(r1);
    }

    /* renamed from: $r8$lambda$9rVe6bMn1RRzNP-xGG5NcgNi1Xs, reason: not valid java name */
    public static WorkNameDao_Impl m975$r8$lambda$9rVe6bMn1RRzNPxGG5NcgNi1Xs(WorkDatabase_Impl r1) {
        return new WorkNameDao_Impl(r1);
    }

    public static WorkSpecDao_Impl $r8$lambda$EBCBMQRVkN1sQSZgY0tCFd1jjgA(WorkDatabase_Impl r1) {
        return new WorkSpecDao_Impl(r1);
    }

    /* renamed from: $r8$lambda$Q4e7dMsbchyXlG7si6nJ2S-X0Hg, reason: not valid java name */
    public static PreferenceDao_Impl m976$r8$lambda$Q4e7dMsbchyXlG7si6nJ2SX0Hg(WorkDatabase_Impl r1) {
        return new PreferenceDao_Impl(r1);
    }

    /* renamed from: $r8$lambda$UR3dJSsh-z5ZBBq2DPAP4EXV70s, reason: not valid java name */
    public static DependencyDao_Impl m977$r8$lambda$UR3dJSshz5ZBBq2DPAP4EXV70s(WorkDatabase_Impl r1) {
        return new DependencyDao_Impl(r1);
    }

    public static SystemIdInfoDao_Impl $r8$lambda$VD7PkCdHr0pD6cL8vVPjuCZZsCo(WorkDatabase_Impl r1) {
        return new SystemIdInfoDao_Impl(r1);
    }

    public static WorkProgressDao_Impl $r8$lambda$YXFG2V7GSqY1Fh14uyLBpxHvsJg(WorkDatabase_Impl r1) {
        return new WorkProgressDao_Impl(r1);
    }

    public static RawWorkInfoDao_Impl $r8$lambda$nTzsr7UbqjB1fFswZZ1aP5SIdIc(WorkDatabase_Impl r1) {
        return new RawWorkInfoDao_Impl(r1);
    }

    public WorkDatabase_Impl() {
        this._workSpecDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda0(this));
        this._dependencyDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda1(this));
        this._workTagDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda2(this));
        this._systemIdInfoDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda3(this));
        this._workNameDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda4(this));
        this._workProgressDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda5(this));
        this._preferenceDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda6(this));
        this._rawWorkInfoDao = LazyKt.lazy(new WorkDatabase_Impl$$ExternalSyntheticLambda7(this));
    }

    public static final /* synthetic */ void access$internalInitInvalidationTracker(WorkDatabase_Impl r0, SQLiteConnection r1) {
        r0.internalInitInvalidationTracker(r1);
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(true, new String[]{"Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference"});
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> r1) {
        ArrayList r0 = new ArrayList();
        r0.add(new WorkDatabase_AutoMigration_13_14_Impl());
        r0.add(new WorkDatabase_AutoMigration_14_15_Impl());
        r0.add(new WorkDatabase_AutoMigration_16_17_Impl());
        r0.add(new WorkDatabase_AutoMigration_17_18_Impl());
        r0.add(new WorkDatabase_AutoMigration_18_19_Impl());
        r0.add(new WorkDatabase_AutoMigration_19_20_Impl());
        r0.add(new WorkDatabase_AutoMigration_20_21_Impl());
        r0.add(new WorkDatabase_AutoMigration_22_23_Impl());
        r0.add(new WorkDatabase_AutoMigration_23_24_Impl());
        return r0;
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new LinkedHashMap(), new LinkedHashMap(), new String[]{"Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference"});
    }

    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        return new WorkDatabase_Impl$createOpenDelegate$_openDelegate$1(this);
    }

    @Override // androidx.room.RoomDatabase
    public /* bridge */ /* synthetic */ RoomOpenDelegateMarker createOpenDelegate() {
        return createOpenDelegate();
    }

    @Override // androidx.work.impl.WorkDatabase
    public DependencyDao dependencyDao() {
        return this._dependencyDao.getValue();
    }

    @Override // androidx.room.RoomDatabase
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        return new LinkedHashSet();
    }

    @Override // androidx.room.RoomDatabase
    public Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        LinkedHashMap r2 = new LinkedHashMap();
        r2.put(Reflection.getOrCreateKotlinClass(WorkSpecDao.class), WorkSpecDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(DependencyDao.class), DependencyDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(WorkTagDao.class), WorkTagDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(SystemIdInfoDao.class), SystemIdInfoDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(WorkNameDao.class), WorkNameDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(WorkProgressDao.class), WorkProgressDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(PreferenceDao.class), PreferenceDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(RawWorkInfoDao.class), RawWorkInfoDao_Impl.Companion.getRequiredConverters());
        return r2;
    }

    @Override // androidx.work.impl.WorkDatabase
    public PreferenceDao preferenceDao() {
        return this._preferenceDao.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public SystemIdInfoDao systemIdInfoDao() {
        return this._systemIdInfoDao.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkNameDao workNameDao() {
        return this._workNameDao.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkProgressDao workProgressDao() {
        return this._workProgressDao.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkSpecDao workSpecDao() {
        return this._workSpecDao.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkTagDao workTagDao() {
        return this._workTagDao.getValue();
    }
}
