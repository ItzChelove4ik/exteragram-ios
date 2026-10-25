package androidx.sqlite.db.framework;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.Pair;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 B2\u00020\u0001:\u0001BB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0014\u0010\nJ\u000f\u0010\u0015\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\u0011J\u000f\u0010\u0016\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0016\u0010\u0011J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001dJ!\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0016¢\u0006\u0004\b\u001b\u0010 JE\u0010*\u001a\u00020\"2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u000b2\u0012\u0010)\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010(\u0018\u00010'H\u0016¢\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b,\u0010-J)\u0010,\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0010\u0010.\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010(0'H\u0016¢\u0006\u0004\b,\u0010/J\u000f\u00100\u001a\u00020\u0017H\u0016¢\u0006\u0004\b0\u0010\u0019J\u000f\u00101\u001a\u00020\bH\u0016¢\u0006\u0004\b1\u0010\u0011J\u000f\u00102\u001a\u00020\bH\u0016¢\u0006\u0004\b2\u0010\u0011J\u0017\u00106\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u0002H\u0000¢\u0006\u0004\b4\u00105R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00107R\u0014\u00108\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u0010\u0019R\u0016\u0010;\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u0019R(\u0010A\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0>\u0018\u00010=8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@¨\u0006C"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "Landroid/database/sqlite/SQLiteDatabase;", "delegate", "<init>", "(Landroid/database/sqlite/SQLiteDatabase;)V", "Landroid/database/sqlite/SQLiteTransactionListener;", "transactionListener", _UrlKt.FRAGMENT_ENCODE_SET, "internalBeginTransactionWithListenerReadOnly", "(Landroid/database/sqlite/SQLiteTransactionListener;)V", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Landroidx/sqlite/db/SupportSQLiteStatement;", "compileStatement", "(Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteStatement;", "beginTransaction", "()V", "beginTransactionNonExclusive", "beginTransactionReadOnly", "beginTransactionWithListener", "endTransaction", "setTransactionSuccessful", _UrlKt.FRAGMENT_ENCODE_SET, "inTransaction", "()Z", "Landroidx/sqlite/db/SupportSQLiteQuery;", "query", "Landroid/database/Cursor;", "(Landroidx/sqlite/db/SupportSQLiteQuery;)Landroid/database/Cursor;", "Landroid/os/CancellationSignal;", "cancellationSignal", "(Landroidx/sqlite/db/SupportSQLiteQuery;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "table", _UrlKt.FRAGMENT_ENCODE_SET, "conflictAlgorithm", "Landroid/content/ContentValues;", "values", "whereClause", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "whereArgs", PluginsConstants.UPDATE, "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "execSQL", "(Ljava/lang/String;)V", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "enableWriteAheadLogging", "disableWriteAheadLogging", "close", "sqLiteDatabase", "isDelegate$sqlite_framework", "(Landroid/database/sqlite/SQLiteDatabase;)Z", "isDelegate", "Landroid/database/sqlite/SQLiteDatabase;", "isOpen", "getPath", "()Ljava/lang/String;", "path", "isWriteAheadLoggingEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Pair;", "getAttachedDbs", "()Ljava/util/List;", "attachedDbs", "Companion", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFrameworkSQLiteDatabase.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameworkSQLiteDatabase.android.kt\nandroidx/sqlite/db/framework/FrameworkSQLiteDatabase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,369:1\n1#2:370\n*E\n"})
/* loaded from: classes.dex */
public final class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    private static final String[] CONFLICT_VALUES = null;
    private static final Companion Companion = null;
    private static final String[] EMPTY_STRING_ARRAY = null;
    private static final Lazy<Method> beginTransactionMethod$delegate = null;
    private static final Lazy<Method> getThreadSessionMethod$delegate = null;
    private final SQLiteDatabase delegate;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u001d\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000f\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0010\u0010\f¨\u0006\u0012"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CONFLICT_VALUES", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/String;", "EMPTY_STRING_ARRAY", "getThreadSessionMethod", "Ljava/lang/reflect/Method;", "getGetThreadSessionMethod", "()Ljava/lang/reflect/Method;", "getThreadSessionMethod$delegate", "Lkotlin/Lazy;", "beginTransactionMethod", "getBeginTransactionMethod", "beginTransactionMethod$delegate", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ Method access$getBeginTransactionMethod(Companion r0) {
            return r0.getBeginTransactionMethod();
        }

        public static final /* synthetic */ Method access$getGetThreadSessionMethod(Companion r0) {
            return r0.getGetThreadSessionMethod();
        }

        private final Method getBeginTransactionMethod() {
            return (Method) FrameworkSQLiteDatabase.access$getBeginTransactionMethod$delegate$cp().getValue();
        }

        private final Method getGetThreadSessionMethod() {
            return (Method) FrameworkSQLiteDatabase.access$getGetThreadSessionMethod$delegate$cp().getValue();
        }
    }

    public static Method $r8$lambda$4rXSxUDV5Jwg_myyvvGbHMZMbGM() {
        Method r0 = Companion.access$getGetThreadSessionMethod(Companion);     // Catch: Throwable -> L10
        if (r0 == null) goto L8;
        Class<?> r02 = r0.getReturnType();     // Catch: Throwable -> L10
        if (r02 == null) goto L13;
        Class r2 = Integer.TYPE;     // Catch: Throwable -> L10
        return r02.getDeclaredMethod("beginTransaction", new Class[]{r2, SQLiteTransactionListener.class, r2, CancellationSignal.class});
    L13:
        return null;
    L8:
        return null;
    L14:
        return null;
    }

    /* renamed from: $r8$lambda$TcD1j6gYKY4LbAnrkC-XD1zFcJk, reason: not valid java name */
    public static Cursor m962$r8$lambda$TcD1j6gYKY4LbAnrkCXD1zFcJk(Function4 r0, SQLiteDatabase r1, SQLiteCursorDriver r2, String r3, SQLiteQuery r4) {
        return (Cursor) r0.invoke(r1, r2, r3, r4);
    }

    public static Cursor $r8$lambda$VAdvOeOMNnZUpsfStEPetT5hHTM(SupportSQLiteQuery r0, SQLiteDatabase r1, SQLiteCursorDriver r2, String r3, SQLiteQuery r4) {
        r0.bindTo(new FrameworkSQLiteProgram(r4));
        return new SQLiteCursor(r2, r3, r4);
    }

    public static Method $r8$lambda$dKqImjZeKtSaT9NYGZ8XQAtPq7s() {
        Method r1 = SQLiteDatabase.class.getDeclaredMethod("getThreadSession", null);     // Catch: Throwable -> L5
        r1.setAccessible(true);     // Catch: Throwable -> L5
        return r1;
    L5:
        return null;
    }

    public static SQLiteCursor $r8$lambda$lJwI5rfEgbbousRT0mbuHaL1K3E(SupportSQLiteQuery r0, SQLiteDatabase r1, SQLiteCursorDriver r2, String r3, SQLiteQuery r4) {
        r0.bindTo(new FrameworkSQLiteProgram(r4));
        return new SQLiteCursor(r2, r3, r4);
    }

    static {
        Companion = new Companion(null);
        CONFLICT_VALUES = new String[]{_UrlKt.FRAGMENT_ENCODE_SET, " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
        EMPTY_STRING_ARRAY = new String[0];
        LazyThreadSafetyMode r0 = LazyThreadSafetyMode.PUBLICATION;
        getThreadSessionMethod$delegate = LazyKt.lazy(r0, new FrameworkSQLiteDatabase$$ExternalSyntheticLambda3());
        beginTransactionMethod$delegate = LazyKt.lazy(r0, new FrameworkSQLiteDatabase$$ExternalSyntheticLambda4());
    }

    public FrameworkSQLiteDatabase(SQLiteDatabase r1) {
        this.delegate = r1;
    }

    public static final /* synthetic */ Lazy access$getBeginTransactionMethod$delegate$cp() {
        return beginTransactionMethod$delegate;
    }

    public static final /* synthetic */ Lazy access$getGetThreadSessionMethod$delegate$cp() {
        return getThreadSessionMethod$delegate;
    }

    @SuppressLint({"BanUncheckedReflection"})
    private final void internalBeginTransactionWithListenerReadOnly(SQLiteTransactionListener r5) {
        Companion r1 = Companion;
        if (Companion.access$getBeginTransactionMethod(r1) != null) goto L5;
    L12:
        if (r5 == null) goto L15;
        beginTransactionWithListener(r5);
        return;
    L15:
        beginTransaction();
        return;
    L5:
        if (Companion.access$getGetThreadSessionMethod(r1) == null) goto L12;
        Method r2 = Companion.access$getBeginTransactionMethod(r1);
        Object r4 = Companion.access$getGetThreadSessionMethod(r1).invoke(this.delegate, null);
        if (r4 == null) goto L10;
        r2.invoke(r4, new Object[]{0, r5, 0, null});
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransaction() {
        this.delegate.beginTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionNonExclusive() {
        this.delegate.beginTransactionNonExclusive();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionReadOnly() {
        internalBeginTransactionWithListenerReadOnly(null);
    }

    public void beginTransactionWithListener(SQLiteTransactionListener r1) {
        this.delegate.beginTransactionWithListener(r1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public SupportSQLiteStatement compileStatement(String r2) {
        return new FrameworkSQLiteStatement(this.delegate.compileStatement(r2));
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void disableWriteAheadLogging() {
        this.delegate.disableWriteAheadLogging();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean enableWriteAheadLogging() {
        return this.delegate.enableWriteAheadLogging();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void endTransaction() {
        this.delegate.endTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(String r1) {
        this.delegate.execSQL(r1);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(String r1, Object[] r2) {
        this.delegate.execSQL(r1, r2);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public List<Pair<String, String>> getAttachedDbs() {
        return this.delegate.getAttachedDbs();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public String getPath() {
        return this.delegate.getPath();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    public final boolean isDelegate$sqlite_framework(SQLiteDatabase r1) {
        return Intrinsics.areEqual(this.delegate, r1);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isOpen() {
        return this.delegate.isOpen();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isWriteAheadLoggingEnabled() {
        return this.delegate.isWriteAheadLoggingEnabled();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(SupportSQLiteQuery r4) {
        FrameworkSQLiteDatabase$$ExternalSyntheticLambda1 r0 = new FrameworkSQLiteDatabase$$ExternalSyntheticLambda1(r4);
        return this.delegate.rawQueryWithFactory(new FrameworkSQLiteDatabase$$ExternalSyntheticLambda2(r0), r4.getSql(), EMPTY_STRING_ARRAY, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(SupportSQLiteQuery r7, CancellationSignal r8) {
        return this.delegate.rawQueryWithFactory(new FrameworkSQLiteDatabase$$ExternalSyntheticLambda0(r7), r7.getSql(), EMPTY_STRING_ARRAY, null, r8);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setTransactionSuccessful() {
        this.delegate.setTransactionSuccessful();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public int update(String r7, int r8, ContentValues r9, String r10, Object[] r11) {
        int r1 = 0;
        if (r9.size() == 0) goto L25;
        int r0 = r9.size();
        if (r11 != null) goto L7;
        int r2 = r0;
    L8:
        Object[] r3 = new Object[r2];
        StringBuilder r4 = new StringBuilder("UPDATE ");
        r4.append(CONFLICT_VALUES[r8]);
        r4.append(r7);
        r4.append(" SET ");
        Iterator<String> r72 = r9.keySet().iterator();
    L10:
        if (r72.hasNext() == false) goto L16;
        String r82 = r72.next();
        if (r1 <= 0) goto L14;
        String r5 = ",";
    L15:
        r4.append(r5);
        r4.append(r82);
        r3[r1] = r9.get(r82);
        r4.append("=?");
        r1 = r1 + 1;
        goto L10
    L14:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L15
    L16:
        if (r11 == null) goto L21;
        int r73 = r0;
    L18:
        if (r73 >= r2) goto L21;
        r3[r73] = r11[r73 - r0];
        r73 = r73 + 1;
    L21:
        if (TextUtils.isEmpty(r10) == true) goto L23;
        r4.append(" WHERE ");
        r4.append(r10);
    L23:
        SupportSQLiteStatement r6 = compileStatement(r4.toString());
        SimpleSQLiteQuery.Companion.bind(r6, r3);
        return r6.executeUpdateDelete();
    L7:
        r2 = r11.length + r0;
        goto L8
    L25:
        f$$ExternalSyntheticBUOutline1.m("Empty values");
        return 0;
    }
}
