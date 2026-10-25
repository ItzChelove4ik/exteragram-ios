package androidx.work.impl.model;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/model/PreferenceDao_Impl;", "Landroidx/work/impl/model/PreferenceDao;", "Landroidx/room/RoomDatabase;", "__db", "<init>", "(Landroidx/room/RoomDatabase;)V", "Landroidx/work/impl/model/Preference;", "preference", _UrlKt.FRAGMENT_ENCODE_SET, "insertPreference", "(Landroidx/work/impl/model/Preference;)V", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "getLongValue", "(Ljava/lang/String;)Ljava/lang/Long;", "Landroidx/room/RoomDatabase;", "Landroidx/room/EntityInsertAdapter;", "__insertAdapterOfPreference", "Landroidx/room/EntityInsertAdapter;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PreferenceDao_Impl implements PreferenceDao {
    public static final Companion Companion = null;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<Preference> __insertAdapterOfPreference;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"androidx/work/impl/model/PreferenceDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Landroidx/work/impl/model/Preference;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.work.impl.model.PreferenceDao_Impl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EntityInsertAdapter<Preference> {
        public AnonymousClass1() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r3, Preference r4) {
            r3.bindText(1, r4.getKey());
            Long r2 = r4.getValue();
            if (r2 != null) goto L6;
            r3.bindNull(2);
            return;
        L6:
            r3.bindLong(2, r2.longValue());
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, Preference r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/model/PreferenceDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

    /* renamed from: $r8$lambda$WOdbRCbcU9bF-o_A_B2dKOhXNig, reason: not valid java name */
    public static Long m985$r8$lambda$WOdbRCbcU9bFo_A_B2dKOhXNig(String r1, String r2, SQLiteConnection r3) {
        SQLiteStatement r12 = r3.prepare(r1);
        r12.bindText(1, r2);     // Catch: Throwable -> L10
        Long r32 = null;
        if (r12.step() == true) goto L6;
    L12:
        r12.close();
        return r32;
    L6:
        if (r12.isNull(0) == true) goto L12;
        r32 = Long.valueOf(r12.getLong(0));     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        r12.close();
        throw th;
    }

    public static Unit $r8$lambda$pV7ZAv1ABN_GLbYRS_jnXDCXzyk(PreferenceDao_Impl r0, Preference r1, SQLiteConnection r2) {
        r0.__insertAdapterOfPreference.insert(r2, r1);
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public PreferenceDao_Impl(RoomDatabase r1) {
        this.__db = r1;
        this.__insertAdapterOfPreference = new AnonymousClass1();
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public Long getLongValue(String r3) {
        return (Long) DBUtil.performBlocking(this.__db, true, false, new PreferenceDao_Impl$$ExternalSyntheticLambda1("SELECT long_value FROM Preference where `key`=?", r3));
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public void insertPreference(Preference r3) {
        DBUtil.performBlocking(this.__db, false, true, new PreferenceDao_Impl$$ExternalSyntheticLambda0(this, r3));
    }
}
