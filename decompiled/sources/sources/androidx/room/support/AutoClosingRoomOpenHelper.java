package androidx.room.support;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.room.DelegatingOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0003 !\"B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0096\u0001¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u00018\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001b\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001c8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006#"}, d2 = {"Landroidx/room/support/AutoClosingRoomOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/DelegatingOpenHelper;", "delegate", "Landroidx/room/support/AutoCloser;", "autoCloser", "<init>", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;Landroidx/room/support/AutoCloser;)V", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "enabled", "setWriteAheadLoggingEnabled", "(Z)V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "getDelegate", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/support/AutoCloser;", "getAutoCloser$room_runtime", "()Landroidx/room/support/AutoCloser;", "Landroidx/room/support/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "autoClosingDb", "Landroidx/room/support/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getWritableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", _UrlKt.FRAGMENT_ENCODE_SET, "getDatabaseName", "()Ljava/lang/String;", "databaseName", "AutoClosingSupportSQLiteDatabase", "KeepAliveCursor", "AutoClosingSupportSQLiteStatement", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AutoClosingRoomOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    private final AutoCloser autoCloser;
    private final AutoClosingSupportSQLiteDatabase autoClosingDb;
    private final SupportSQLiteOpenHelper delegate;

    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000e\u0010\bJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\bJ\u000f\u0010\u0010\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\bJ\u000f\u0010\u0011\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\bJ\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0018J!\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0017¢\u0006\u0004\b\u0016\u0010\u001bJE\u0010%\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\t2\u0012\u0010$\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010#\u0018\u00010\"H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b'\u0010(J)\u0010'\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0010\u0010)\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010#0\"H\u0016¢\u0006\u0004\b'\u0010*J\u000f\u0010+\u001a\u00020\u0012H\u0016¢\u0006\u0004\b+\u0010\u0014J\u000f\u0010,\u001a\u00020\u0006H\u0016¢\u0006\u0004\b,\u0010\bJ\u000f\u0010-\u001a\u00020\u0006H\u0016¢\u0006\u0004\b-\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010.R\u0014\u0010/\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0014R\u0016\u00102\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u00103\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u0014R(\u00108\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t05\u0018\u0001048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107¨\u00069"}, d2 = {"Landroidx/room/support/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "Landroidx/room/support/AutoCloser;", "autoCloser", "<init>", "(Landroidx/room/support/AutoCloser;)V", _UrlKt.FRAGMENT_ENCODE_SET, "pokeOpen", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Landroidx/sqlite/db/SupportSQLiteStatement;", "compileStatement", "(Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteStatement;", "beginTransaction", "beginTransactionNonExclusive", "endTransaction", "setTransactionSuccessful", _UrlKt.FRAGMENT_ENCODE_SET, "inTransaction", "()Z", "Landroidx/sqlite/db/SupportSQLiteQuery;", "query", "Landroid/database/Cursor;", "(Landroidx/sqlite/db/SupportSQLiteQuery;)Landroid/database/Cursor;", "Landroid/os/CancellationSignal;", "cancellationSignal", "(Landroidx/sqlite/db/SupportSQLiteQuery;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "table", _UrlKt.FRAGMENT_ENCODE_SET, "conflictAlgorithm", "Landroid/content/ContentValues;", "values", "whereClause", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "whereArgs", PluginsConstants.UPDATE, "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "execSQL", "(Ljava/lang/String;)V", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "enableWriteAheadLogging", "disableWriteAheadLogging", "close", "Landroidx/room/support/AutoCloser;", "isOpen", "getPath", "()Ljava/lang/String;", "path", "isWriteAheadLoggingEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Pair;", "getAttachedDbs", "()Ljava/util/List;", "attachedDbs", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class AutoClosingSupportSQLiteDatabase implements SupportSQLiteDatabase {
        private final AutoCloser autoCloser;

        public static Object $r8$lambda$1KwocTIe2h12KCeNKknTWMa2_GQ(SupportSQLiteDatabase r0) {
            return null;
        }

        public static Unit $r8$lambda$SMNH0gv9S0v8WmyrE5xRwPUUMRQ(String r0, Object[] r1, SupportSQLiteDatabase r2) {
            r2.execSQL(r0, r1);
            return Unit.INSTANCE;
        }

        /* renamed from: $r8$lambda$nw6NXngkG-BpMQBYJ1OvmC_QIwY, reason: not valid java name */
        public static Unit m948$r8$lambda$nw6NXngkGBpMQBYJ1OvmC_QIwY(String r0, SupportSQLiteDatabase r1) {
            r1.execSQL(r0);
            return Unit.INSTANCE;
        }

        public static int $r8$lambda$t6vekZ07UyW_LkprcrJYW9Tn6e8(String r1, int r2, ContentValues r3, String r4, Object[] r5, SupportSQLiteDatabase r6) {
            return r6.update(r1, r2, r3, r4, r5);
        }

        public AutoClosingSupportSQLiteDatabase(AutoCloser r1) {
            this.autoCloser = r1;
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public void beginTransaction() {
            this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransaction();     // Catch: Throwable -> L5
            return;
        L5:
            th = move-exception;
            this.autoCloser.decrementCountAndScheduleClose();
            throw th;
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public void beginTransactionNonExclusive() {
            this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionNonExclusive();     // Catch: Throwable -> L5
            return;
        L5:
            th = move-exception;
            this.autoCloser.decrementCountAndScheduleClose();
            throw th;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.autoCloser.closeDatabaseIfOpen();
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public SupportSQLiteStatement compileStatement(String r2) {
            return new AutoClosingSupportSQLiteStatement(r2, this.autoCloser);
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public void disableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean enableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public void endTransaction() {
            this.autoCloser.getDelegateDatabase$room_runtime().endTransaction();     // Catch: Throwable -> L5
            this.autoCloser.decrementCountAndScheduleClose();
            return;
        L5:
            th = move-exception;
            this.autoCloser.decrementCountAndScheduleClose();
            throw th;
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public void execSQL(String r2) {
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda1(r2));
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public void execSQL(String r2, Object[] r3) {
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda2(r2, r3));
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public List<Pair<String, String>> getAttachedDbs() {
            return (List) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1.INSTANCE);
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public String getPath() {
            return (String) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1.INSTANCE);
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean inTransaction() {
            if (this.autoCloser.getDelegateDatabase$room_runtime() != null) goto L7;
            return false;
        L7:
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$inTransaction$1.INSTANCE)).booleanValue();
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isOpen() {
            return this.autoCloser.isActive();
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isWriteAheadLoggingEnabled() {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isWriteAheadLoggingEnabled$1.INSTANCE)).booleanValue();
        }

        public final void pokeOpen() {
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda3());
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public Cursor query(SupportSQLiteQuery r2) {
            Cursor r22 = this.autoCloser.incrementCountAndEnsureDbIsOpen().query(r2);     // Catch: Throwable -> L5
            return new KeepAliveCursor(r22, this.autoCloser);
        L5:
            th = move-exception;
            this.autoCloser.decrementCountAndScheduleClose();
            throw th;
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public Cursor query(SupportSQLiteQuery r2, CancellationSignal r3) {
            Cursor r22 = this.autoCloser.incrementCountAndEnsureDbIsOpen().query(r2, r3);     // Catch: Throwable -> L5
            return new KeepAliveCursor(r22, this.autoCloser);
        L5:
            th = move-exception;
            this.autoCloser.decrementCountAndScheduleClose();
            throw th;
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public void setTransactionSuccessful() {
            this.autoCloser.getDelegateDatabase$room_runtime().setTransactionSuccessful();
        }

        @Override // androidx.sqlite.db.SupportSQLiteDatabase
        public int update(String r7, int r8, ContentValues r9, String r10, Object[] r11) {
            return ((Number) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda0(r7, r8, r9, r10, r11))).intValue();
        }
    }

    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\b\u0002\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00028\u00000\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0019\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010 \u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b \u0010!J\u001f\u0010#\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$J\u001f\u0010%\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b%\u0010&J\u001f\u0010(\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u0010H\u0016¢\u0006\u0004\b*\u0010\u0018R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010+R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010,R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00101\u001a\u0002008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00104\u001a\u0002038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u001e\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u001e\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010'068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:¨\u0006<"}, d2 = {"Landroidx/room/support/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteStatement;", "Landroidx/sqlite/db/SupportSQLiteStatement;", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Landroidx/room/support/AutoCloser;", "autoCloser", "<init>", "(Ljava/lang/String;Landroidx/room/support/AutoCloser;)V", "T", "Lkotlin/Function1;", "block", "executeWithRefCount", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "columnType", "index", _UrlKt.FRAGMENT_ENCODE_SET, "ensureCapacity", "(II)V", "Landroidx/sqlite/db/SupportSQLiteProgram;", "query", "bindTo", "(Landroidx/sqlite/db/SupportSQLiteProgram;)V", "close", "()V", "execute", "executeUpdateDelete", "()I", "bindNull", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "bindLong", "(IJ)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindDouble", "(ID)V", "bindString", "(ILjava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindBlob", "(I[B)V", "clearBindings", "Ljava/lang/String;", "Landroidx/room/support/AutoCloser;", _UrlKt.FRAGMENT_ENCODE_SET, "bindingTypes", "[I", _UrlKt.FRAGMENT_ENCODE_SET, "longBindings", "[J", _UrlKt.FRAGMENT_ENCODE_SET, "doubleBindings", "[D", _UrlKt.FRAGMENT_ENCODE_SET, "stringBindings", "[Ljava/lang/String;", "blobBindings", "[[B", "Companion", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class AutoClosingSupportSQLiteStatement implements SupportSQLiteStatement {
        public static final Companion Companion = null;
        private final AutoCloser autoCloser;
        private int[] bindingTypes;
        private byte[][] blobBindings;
        private double[] doubleBindings;
        private long[] longBindings;
        private final String sql;
        private String[] stringBindings;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/room/support/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteStatement$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "COLUMN_TYPE_LONG", _UrlKt.FRAGMENT_ENCODE_SET, "COLUMN_TYPE_DOUBLE", "COLUMN_TYPE_STRING", "COLUMN_TYPE_BLOB", "COLUMN_TYPE_NULL", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        public static int $r8$lambda$3Aj18OO32YNz9ckWPVlCTVv0gQg(SupportSQLiteStatement r0) {
            return r0.executeUpdateDelete();
        }

        public static Unit $r8$lambda$NJZOnSlUgCdURprekACDKbL2tn0(SupportSQLiteStatement r0) {
            r0.execute();
            return Unit.INSTANCE;
        }

        public static Object $r8$lambda$o_YyUWDlwh2377MTf9skJYHZRd4(AutoClosingSupportSQLiteStatement r1, Function1 r2, SupportSQLiteDatabase r3) {
            SupportSQLiteStatement r32 = r3.compileStatement(r1.sql);
            r1.bindTo(r32);
            return r2.invoke(r32);
        }

        static {
            Companion = new Companion(null);
        }

        public AutoClosingSupportSQLiteStatement(String r1, AutoCloser r2) {
            this.sql = r1;
            this.autoCloser = r2;
            this.bindingTypes = new int[0];
            this.longBindings = new long[0];
            this.doubleBindings = new double[0];
            this.stringBindings = new String[0];
            this.blobBindings = new byte[0][];
        }

        private final void bindTo(SupportSQLiteProgram r7) {
            int r0 = this.bindingTypes.length;
            int r2 = 1;
        L3:
            if (r2 >= r0) goto L21;
            int r3 = this.bindingTypes[r2];
            if (r3 != 1) goto L7;
            r7.bindLong(r2, this.longBindings[r2]);
        L20:
            r2 = r2 + 1;
            goto L3
        L7:
            if (r3 != 2) goto L9;
            r7.bindDouble(r2, this.doubleBindings[r2]);
            goto L20
        L9:
            if (r3 != 3) goto L11;
            r7.bindString(r2, this.stringBindings[r2]);
            goto L20
        L11:
            if (r3 != 4) goto L13;
            r7.bindBlob(r2, this.blobBindings[r2]);
            goto L20
        L13:
            if (r3 != 5) goto L20;
            r7.bindNull(r2);
            goto L20
        }

        private final void ensureCapacity(int r4, int r5) {
            int r52 = r5 + 1;
            int[] r1 = this.bindingTypes;
            if (r1.length >= r52) goto L5;
            this.bindingTypes = Arrays.copyOf(r1, r52);
        L5:
            if (r4 != 1) goto L7;
            long[] r42 = this.longBindings;
            if (r42.length >= r52) goto L32;
            this.longBindings = Arrays.copyOf(r42, r52);
            return;
        L32:
            return;
        L7:
            if (r4 != 2) goto L9;
            double[] r43 = this.doubleBindings;
            if (r43.length >= r52) goto L31;
            this.doubleBindings = Arrays.copyOf(r43, r52);
            return;
        L31:
            return;
        L9:
            if (r4 != 3) goto L11;
            String[] r44 = this.stringBindings;
            if (r44.length >= r52) goto L30;
            this.stringBindings = (String[]) Arrays.copyOf(r44, r52);
            return;
        L30:
            return;
        L11:
            if (r4 != 4) goto L33;
            byte[][] r45 = this.blobBindings;
            if (r45.length >= r52) goto L29;
            this.blobBindings = (byte[][]) Arrays.copyOf(r45, r52);
            return;
        L29:
            return;
        }

        private final <T> T executeWithRefCount(Function1<? super SupportSQLiteStatement, ? extends T> r3) {
            return (T) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteStatement$$ExternalSyntheticLambda2(this, r3));
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindBlob(int r3, byte[] r4) {
            ensureCapacity(4, r3);
            this.bindingTypes[r3] = 4;
            this.blobBindings[r3] = r4;
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindDouble(int r3, double r4) {
            ensureCapacity(2, r3);
            this.bindingTypes[r3] = 2;
            this.doubleBindings[r3] = r4;
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindLong(int r3, long r4) {
            ensureCapacity(1, r3);
            this.bindingTypes[r3] = 1;
            this.longBindings[r3] = r4;
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindNull(int r2) {
            ensureCapacity(5, r2);
            this.bindingTypes[r2] = 5;
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindString(int r3, String r4) {
            ensureCapacity(3, r3);
            this.bindingTypes[r3] = 3;
            this.stringBindings[r3] = r4;
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void clearBindings() {
            this.bindingTypes = new int[0];
            this.longBindings = new long[0];
            this.doubleBindings = new double[0];
            this.stringBindings = new String[0];
            this.blobBindings = new byte[0][];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            clearBindings();
        }

        @Override // androidx.sqlite.db.SupportSQLiteStatement
        public void execute() {
            executeWithRefCount(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteStatement$$ExternalSyntheticLambda0());
        }

        @Override // androidx.sqlite.db.SupportSQLiteStatement
        public int executeUpdateDelete() {
            return ((Number) executeWithRefCount(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteStatement$$ExternalSyntheticLambda1())).intValue();
        }
    }

    @Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J!\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J\t\u0010\u000f\u001a\u00020\bH\u0097\u0001J\u0019\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u00110\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\t\u0010\u0012\u001a\u00020\u000bH\u0096\u0001J\u0019\u0010\u0013\u001a\u00020\u000b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010\u00140\u0014H\u0096\u0001J\u0019\u0010\u0015\u001a\u00020\u000b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010\u00140\u0014H\u0096\u0001J\u0019\u0010\u0016\u001a\n \u000e*\u0004\u0018\u00010\u00140\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J4\u0010\u0017\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00140\u0014 \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u00140\u0014\u0018\u00010\u00180\u0018H\u0096\u0001¢\u0006\u0002\u0010\u0019J\t\u0010\u001a\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010\u001d\u001a\n \u000e*\u0004\u0018\u00010\u001e0\u001eH\u0096\u0001J\u0011\u0010\u001f\u001a\u00020 2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010!\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010\"\u001a\u00020#2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010$\u001a\n \u000e*\u0004\u0018\u00010%0%H\u0096\u0001J\t\u0010&\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010'\u001a\u00020(2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0019\u0010)\u001a\n \u000e*\u0004\u0018\u00010\u00140\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0011\u0010*\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\t\u0010+\u001a\u00020,H\u0096\u0001J\t\u0010-\u001a\u00020,H\u0096\u0001J\t\u0010.\u001a\u00020,H\u0096\u0001J\t\u0010/\u001a\u00020,H\u0096\u0001J\t\u00100\u001a\u00020,H\u0096\u0001J\t\u00101\u001a\u00020,H\u0096\u0001J\u0011\u00102\u001a\u00020,2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0011\u00103\u001a\u00020,2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\t\u00104\u001a\u00020,H\u0096\u0001J\t\u00105\u001a\u00020,H\u0096\u0001J\t\u00106\u001a\u00020,H\u0096\u0001J\u0011\u00107\u001a\u00020,2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J\t\u00108\u001a\u00020,H\u0096\u0001J\u0019\u00109\u001a\u00020\b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010:0:H\u0096\u0001J\u0019\u0010;\u001a\u00020\b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010<0<H\u0096\u0001J\t\u0010=\u001a\u00020,H\u0097\u0001J!\u0010>\u001a\n \u000e*\u0004\u0018\u00010\u001e0\u001e2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010\u001e0\u001eH\u0096\u0001J\u0019\u0010?\u001a\u00020\b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010\u001e0\u001eH\u0096\u0001J)\u0010@\u001a\u00020\b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010A0A2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010%0%H\u0096\u0001J\u0019\u0010B\u001a\u00020\b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010:0:H\u0096\u0001J\u0019\u0010C\u001a\u00020\b2\u000e\u0010\n\u001a\n \u000e*\u0004\u0018\u00010<0<H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Landroidx/room/support/AutoClosingRoomOpenHelper$KeepAliveCursor;", "Landroid/database/Cursor;", "delegate", "autoCloser", "Landroidx/room/support/AutoCloser;", "<init>", "(Landroid/database/Cursor;Landroidx/room/support/AutoCloser;)V", "close", _UrlKt.FRAGMENT_ENCODE_SET, "copyStringToBuffer", "p0", _UrlKt.FRAGMENT_ENCODE_SET, "p1", "Landroid/database/CharArrayBuffer;", "kotlin.jvm.PlatformType", "deactivate", "getBlob", _UrlKt.FRAGMENT_ENCODE_SET, "getColumnCount", "getColumnIndex", _UrlKt.FRAGMENT_ENCODE_SET, "getColumnIndexOrThrow", "getColumnName", "getColumnNames", _UrlKt.FRAGMENT_ENCODE_SET, "()[Ljava/lang/String;", "getCount", "getDouble", _UrlKt.FRAGMENT_ENCODE_SET, "getExtras", "Landroid/os/Bundle;", "getFloat", _UrlKt.FRAGMENT_ENCODE_SET, "getInt", "getLong", _UrlKt.FRAGMENT_ENCODE_SET, "getNotificationUri", "Landroid/net/Uri;", "getPosition", "getShort", _UrlKt.FRAGMENT_ENCODE_SET, "getString", "getType", "getWantsAllOnMoveCalls", _UrlKt.FRAGMENT_ENCODE_SET, "isAfterLast", "isBeforeFirst", "isClosed", "isFirst", "isLast", "isNull", "move", "moveToFirst", "moveToLast", "moveToNext", "moveToPosition", "moveToPrevious", "registerContentObserver", "Landroid/database/ContentObserver;", "registerDataSetObserver", "Landroid/database/DataSetObserver;", "requery", "respond", "setExtras", "setNotificationUri", "Landroid/content/ContentResolver;", "unregisterContentObserver", "unregisterDataSetObserver", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class KeepAliveCursor implements Cursor {
        private final AutoCloser autoCloser;
        private final Cursor delegate;

        public KeepAliveCursor(Cursor r1, AutoCloser r2) {
            this.delegate = r1;
            this.autoCloser = r2;
        }

        @Override // android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
            this.autoCloser.decrementCountAndScheduleClose();
        }

        @Override // android.database.Cursor
        public void copyStringToBuffer(int r1, CharArrayBuffer r2) {
            this.delegate.copyStringToBuffer(r1, r2);
        }

        @Override // android.database.Cursor
        @Deprecated(message = "Deprecated in Java")
        public void deactivate() {
            this.delegate.deactivate();
        }

        @Override // android.database.Cursor
        public byte[] getBlob(int r1) {
            return this.delegate.getBlob(r1);
        }

        @Override // android.database.Cursor
        public int getColumnCount() {
            return this.delegate.getColumnCount();
        }

        @Override // android.database.Cursor
        public int getColumnIndex(String r1) {
            return this.delegate.getColumnIndex(r1);
        }

        @Override // android.database.Cursor
        public int getColumnIndexOrThrow(String r1) {
            return this.delegate.getColumnIndexOrThrow(r1);
        }

        @Override // android.database.Cursor
        public String getColumnName(int r1) {
            return this.delegate.getColumnName(r1);
        }

        @Override // android.database.Cursor
        public String[] getColumnNames() {
            return this.delegate.getColumnNames();
        }

        @Override // android.database.Cursor
        public int getCount() {
            return this.delegate.getCount();
        }

        @Override // android.database.Cursor
        public double getDouble(int r1) {
            return this.delegate.getDouble(r1);
        }

        @Override // android.database.Cursor
        public Bundle getExtras() {
            return this.delegate.getExtras();
        }

        @Override // android.database.Cursor
        public float getFloat(int r1) {
            return this.delegate.getFloat(r1);
        }

        @Override // android.database.Cursor
        public int getInt(int r1) {
            return this.delegate.getInt(r1);
        }

        @Override // android.database.Cursor
        public long getLong(int r1) {
            return this.delegate.getLong(r1);
        }

        @Override // android.database.Cursor
        public Uri getNotificationUri() {
            return this.delegate.getNotificationUri();
        }

        @Override // android.database.Cursor
        public int getPosition() {
            return this.delegate.getPosition();
        }

        @Override // android.database.Cursor
        public short getShort(int r1) {
            return this.delegate.getShort(r1);
        }

        @Override // android.database.Cursor
        public String getString(int r1) {
            return this.delegate.getString(r1);
        }

        @Override // android.database.Cursor
        public int getType(int r1) {
            return this.delegate.getType(r1);
        }

        @Override // android.database.Cursor
        public boolean getWantsAllOnMoveCalls() {
            return this.delegate.getWantsAllOnMoveCalls();
        }

        @Override // android.database.Cursor
        public boolean isAfterLast() {
            return this.delegate.isAfterLast();
        }

        @Override // android.database.Cursor
        public boolean isBeforeFirst() {
            return this.delegate.isBeforeFirst();
        }

        @Override // android.database.Cursor
        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        @Override // android.database.Cursor
        public boolean isFirst() {
            return this.delegate.isFirst();
        }

        @Override // android.database.Cursor
        public boolean isLast() {
            return this.delegate.isLast();
        }

        @Override // android.database.Cursor
        public boolean isNull(int r1) {
            return this.delegate.isNull(r1);
        }

        @Override // android.database.Cursor
        public boolean move(int r1) {
            return this.delegate.move(r1);
        }

        @Override // android.database.Cursor
        public boolean moveToFirst() {
            return this.delegate.moveToFirst();
        }

        @Override // android.database.Cursor
        public boolean moveToLast() {
            return this.delegate.moveToLast();
        }

        @Override // android.database.Cursor
        public boolean moveToNext() {
            return this.delegate.moveToNext();
        }

        @Override // android.database.Cursor
        public boolean moveToPosition(int r1) {
            return this.delegate.moveToPosition(r1);
        }

        @Override // android.database.Cursor
        public boolean moveToPrevious() {
            return this.delegate.moveToPrevious();
        }

        @Override // android.database.Cursor
        public void registerContentObserver(ContentObserver r1) {
            this.delegate.registerContentObserver(r1);
        }

        @Override // android.database.Cursor
        public void registerDataSetObserver(DataSetObserver r1) {
            this.delegate.registerDataSetObserver(r1);
        }

        @Override // android.database.Cursor
        @Deprecated(message = "Deprecated in Java")
        public boolean requery() {
            return this.delegate.requery();
        }

        @Override // android.database.Cursor
        public Bundle respond(Bundle r1) {
            return this.delegate.respond(r1);
        }

        @Override // android.database.Cursor
        public void setExtras(Bundle r1) {
            this.delegate.setExtras(r1);
        }

        @Override // android.database.Cursor
        public void setNotificationUri(ContentResolver r1, Uri r2) {
            this.delegate.setNotificationUri(r1, r2);
        }

        @Override // android.database.Cursor
        public void unregisterContentObserver(ContentObserver r1) {
            this.delegate.unregisterContentObserver(r1);
        }

        @Override // android.database.Cursor
        public void unregisterDataSetObserver(DataSetObserver r1) {
            this.delegate.unregisterDataSetObserver(r1);
        }
    }

    public AutoClosingRoomOpenHelper(SupportSQLiteOpenHelper r1, AutoCloser r2) {
        this.delegate = r1;
        this.autoCloser = r2;
        this.autoClosingDb = new AutoClosingSupportSQLiteDatabase(r2);
        r2.initOpenHelper(getDelegate());
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.autoClosingDb.close();
    }

    public final AutoCloser getAutoCloser$room_runtime() {
        return this.autoCloser;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.delegate.getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    public SupportSQLiteOpenHelper getDelegate() {
        return this.delegate;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        this.autoClosingDb.pokeOpen();
        return this.autoClosingDb;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean r1) {
        this.delegate.setWriteAheadLoggingEnabled(r1);
    }
}
