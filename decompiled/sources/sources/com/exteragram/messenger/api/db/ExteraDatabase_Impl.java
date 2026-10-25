package com.exteragram.messenger.api.db;

import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.RoomOpenDelegateMarker;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.SQLiteConnection;
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

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\"\u0010\u0011\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u00140\u0012H\u0014J\u0016\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u00130\u0016H\u0016J*\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142\u001a\u0010\u001a\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0013\u0012\u0004\u0012\u00020\u00170\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0016J\b\u0010\u001c\u001a\u00020\bH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/exteragram/messenger/api/db/ExteraDatabase_Impl;", "Lcom/exteragram/messenger/api/db/ExteraDatabase;", "<init>", "()V", "_profileDao", "Lkotlin/Lazy;", "Lcom/exteragram/messenger/api/db/ProfileDao;", "_addedRegDateDao", "Lcom/exteragram/messenger/api/db/AddedRegDateDao;", "_boostySubscriberDao", "Lcom/exteragram/messenger/api/db/BoostySubscriberDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", _UrlKt.FRAGMENT_ENCODE_SET, "getRequiredTypeConverterClasses", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", _UrlKt.FRAGMENT_ENCODE_SET, "getRequiredAutoMigrationSpecClasses", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "profileDao", "addedRegDateDao", "boostySubscriberDao", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExteraDatabase_Impl extends ExteraDatabase {
    private final Lazy<AddedRegDateDao> _addedRegDateDao;
    private final Lazy<BoostySubscriberDao> _boostySubscriberDao;
    private final Lazy<ProfileDao> _profileDao;

    public static ProfileDao_Impl $r8$lambda$JPRoYntD9n_OlGUDgtg2Biw9RoU(ExteraDatabase_Impl r1) {
        return new ProfileDao_Impl(r1);
    }

    /* renamed from: $r8$lambda$by-CBGkXWshS-FfQllL1S6sPi9A, reason: not valid java name */
    public static BoostySubscriberDao_Impl m1098$r8$lambda$byCBGkXWshSFfQllL1S6sPi9A(ExteraDatabase_Impl r1) {
        return new BoostySubscriberDao_Impl(r1);
    }

    public static AddedRegDateDao_Impl $r8$lambda$kCb3Um99G1vpZAMPPvBe5zhUCwE(ExteraDatabase_Impl r1) {
        return new AddedRegDateDao_Impl(r1);
    }

    public ExteraDatabase_Impl() {
        this._profileDao = LazyKt.lazy(new ExteraDatabase_Impl$$ExternalSyntheticLambda0(this));
        this._addedRegDateDao = LazyKt.lazy(new ExteraDatabase_Impl$$ExternalSyntheticLambda1(this));
        this._boostySubscriberDao = LazyKt.lazy(new ExteraDatabase_Impl$$ExternalSyntheticLambda2(this));
    }

    public static final /* synthetic */ void access$internalInitInvalidationTracker(ExteraDatabase_Impl r0, SQLiteConnection r1) {
        r0.internalInitInvalidationTracker(r1);
    }

    @Override // com.exteragram.messenger.api.db.ExteraDatabase
    public AddedRegDateDao addedRegDateDao() {
        return this._addedRegDateDao.getValue();
    }

    @Override // com.exteragram.messenger.api.db.ExteraDatabase
    public BoostySubscriberDao boostySubscriberDao() {
        return this._boostySubscriberDao.getValue();
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(false, new String[]{"ProfileDTO", "AddedRegDateDTO", "BoostySubscriberDTO"});
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> r1) {
        return new ArrayList();
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new LinkedHashMap(), new LinkedHashMap(), new String[]{"ProfileDTO", "AddedRegDateDTO", "BoostySubscriberDTO"});
    }

    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        return new ExteraDatabase_Impl$createOpenDelegate$_openDelegate$1(this);
    }

    @Override // androidx.room.RoomDatabase
    public /* bridge */ /* synthetic */ RoomOpenDelegateMarker createOpenDelegate() {
        return createOpenDelegate();
    }

    @Override // androidx.room.RoomDatabase
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        return new LinkedHashSet();
    }

    @Override // androidx.room.RoomDatabase
    public Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        LinkedHashMap r2 = new LinkedHashMap();
        r2.put(Reflection.getOrCreateKotlinClass(ProfileDao.class), ProfileDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(AddedRegDateDao.class), AddedRegDateDao_Impl.Companion.getRequiredConverters());
        r2.put(Reflection.getOrCreateKotlinClass(BoostySubscriberDao.class), BoostySubscriberDao_Impl.Companion.getRequiredConverters());
        return r2;
    }

    @Override // com.exteragram.messenger.api.db.ExteraDatabase
    public ProfileDao profileDao() {
        return this._profileDao.getValue();
    }
}
