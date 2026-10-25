package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.sqlite.db.SupportSQLiteCompat$Api21Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import com.sun.jna.Callback;
import java.io.File;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 (2\u00020\u0001:\u0003)(*B7\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0015R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0016R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0016R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0016R\u001b\u0010 \u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d*\u0004\b\u001e\u0010\u001fR\u0016\u0010#\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010'\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006+"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", Callback.METHOD_NAME, _UrlKt.FRAGMENT_ENCODE_SET, "useNoBackupDirectory", "allowDataLossOnRecovery", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;ZZ)V", "enabled", _UrlKt.FRAGMENT_ENCODE_SET, "setWriteAheadLoggingEnabled", "(Z)V", "close", "()V", "Landroid/content/Context;", "Ljava/lang/String;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "Z", "Lkotlin/Lazy;", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "lazyDelegate", "Lkotlin/Lazy;", "writeAheadLoggingEnabled", "getDelegate", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "getDelegate$delegate", "(Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper;)Ljava/lang/Object;", "delegate", "getDatabaseName", "()Ljava/lang/String;", "databaseName", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getWritableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "Companion", "OpenHelper", "DBRefHolder", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    public static final Companion Companion = null;
    private final boolean allowDataLossOnRecovery;
    private final SupportSQLiteOpenHelper.Callback callback;
    private final Context context;
    private final Lazy<OpenHelper> lazyDelegate;
    private final String name;
    private final boolean useNoBackupDirectory;
    private boolean writeAheadLoggingEnabled;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\t"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", _UrlKt.FRAGMENT_ENCODE_SET, "db", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "<init>", "(Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;)V", "getDb", "()Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "setDb", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DBRefHolder {
        private FrameworkSQLiteDatabase db;

        public DBRefHolder(FrameworkSQLiteDatabase r1) {
            this.db = r1;
        }

        public final FrameworkSQLiteDatabase getDb() {
            return this.db;
        }

        public final void setDb(FrameworkSQLiteDatabase r1) {
            this.db = r1;
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000 92\u00020\u0001:\u0003:;9B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\u0015\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u000f¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010 \u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u000fH\u0016¢\u0006\u0004\b#\u0010\u001cJ'\u0010$\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016¢\u0006\u0004\b$\u0010!J\u0017\u0010%\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u000fH\u0016¢\u0006\u0004\b%\u0010\u001cJ\u000f\u0010&\u001a\u00020\u001aH\u0016¢\u0006\u0004\b&\u0010'R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00101\u001a\u0004\b2\u00103R\u0016\u00104\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00101R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00101¨\u0006<"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "dbRef", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", Callback.METHOD_NAME, _UrlKt.FRAGMENT_ENCODE_SET, "allowDataLossOnRecovery", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;Z)V", "writable", "Landroid/database/sqlite/SQLiteDatabase;", "innerGetDatabase", "(Z)Landroid/database/sqlite/SQLiteDatabase;", "getWritableOrReadableDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getSupportDatabase", "(Z)Landroidx/sqlite/db/SupportSQLiteDatabase;", "sqLiteDatabase", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "getWrappedDb", "(Landroid/database/sqlite/SQLiteDatabase;)Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", _UrlKt.FRAGMENT_ENCODE_SET, "onCreate", "(Landroid/database/sqlite/SQLiteDatabase;)V", _UrlKt.FRAGMENT_ENCODE_SET, "oldVersion", "newVersion", "onUpgrade", "(Landroid/database/sqlite/SQLiteDatabase;II)V", "db", "onConfigure", "onDowngrade", "onOpen", "close", "()V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "getDbRef", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "getCallback", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "Z", "getAllowDataLossOnRecovery", "()Z", "migrated", "Landroidx/sqlite/util/ProcessLock;", "lock", "Landroidx/sqlite/util/ProcessLock;", "opened", "Companion", "CallbackException", "CallbackName", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class OpenHelper extends SQLiteOpenHelper {
        public static final Companion Companion = null;
        private final boolean allowDataLossOnRecovery;
        private final SupportSQLiteOpenHelper.Callback callback;
        private final Context context;
        private final DBRefHolder dbRef;
        private final ProcessLock lock;
        private boolean migrated;
        private boolean opened;

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "callbackName", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;Ljava/lang/Throwable;)V", "getCallbackName", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "getCause", "()Ljava/lang/Throwable;", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class CallbackException extends RuntimeException {
            private final CallbackName callbackName;
            private final Throwable cause;

            public CallbackException(CallbackName r1, Throwable r2) {
                super(r2);
                this.callbackName = r1;
                this.cause = r2;
            }

            public final CallbackName getCallbackName() {
                return this.callbackName;
            }

            @Override // java.lang.Throwable
            public Throwable getCause() {
                return this.cause;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "ON_CONFIGURE", "ON_CREATE", "ON_UPGRADE", "ON_DOWNGRADE", "ON_OPEN", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public enum CallbackName extends Enum<CallbackName> {
            private static final /* synthetic */ EnumEntries $ENTRIES = null;
            private static final /* synthetic */ CallbackName[] $VALUES = null;
            public static final CallbackName ON_CONFIGURE = null;
            public static final CallbackName ON_CREATE = null;
            public static final CallbackName ON_DOWNGRADE = null;
            public static final CallbackName ON_OPEN = null;
            public static final CallbackName ON_UPGRADE = null;

            private static final /* synthetic */ CallbackName[] $values() {
                return new CallbackName[]{ON_CONFIGURE, ON_CREATE, ON_UPGRADE, ON_DOWNGRADE, ON_OPEN};
            }

            static {
                ON_CONFIGURE = new CallbackName("ON_CONFIGURE", 0);
                ON_CREATE = new CallbackName("ON_CREATE", 1);
                ON_UPGRADE = new CallbackName("ON_UPGRADE", 2);
                ON_DOWNGRADE = new CallbackName("ON_DOWNGRADE", 3);
                ON_OPEN = new CallbackName("ON_OPEN", 4);
                CallbackName[] r0 = $values();
                $VALUES = r0;
                $ENTRIES = EnumEntriesKt.enumEntries(r0);
            }

            CallbackName(String r1, int r2) {
            }

            public static CallbackName valueOf(String r1) {
                return (CallbackName) Enum.valueOf(CallbackName.class, r1);
            }

            public static CallbackName[] values() {
                return (CallbackName[]) $VALUES.clone();
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getWrappedDb", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "refHolder", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "sqLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nFrameworkSQLiteOpenHelper.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameworkSQLiteOpenHelper.android.kt\nandroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,337:1\n1#2:338\n*E\n"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final FrameworkSQLiteDatabase getWrappedDb(DBRefHolder r2, SQLiteDatabase r3) {
                FrameworkSQLiteDatabase r1 = r2.getDb();
                if (r1 != null) goto L5;
            L8:
                FrameworkSQLiteDatabase r12 = new FrameworkSQLiteDatabase(r3);
                r2.setDb(r12);
                return r12;
            L5:
                if (r1.isDelegate$sqlite_framework(r3) == false) goto L8;
                return r1;
            }
        }

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[CallbackName.values().length];
                r0[CallbackName.ON_CONFIGURE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
            L15:
                r0[CallbackName.ON_CREATE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
            L23:
                r0[CallbackName.ON_UPGRADE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
            L17:
                r0[CallbackName.ON_DOWNGRADE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
            L19:
                r0[CallbackName.ON_OPEN.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
            L8:
                $EnumSwitchMapping$0 = r0;
            }
        }

        /* renamed from: $r8$lambda$HkATHkNnDWiWKR94KFirzR7ST-8, reason: not valid java name */
        public static void m963$r8$lambda$HkATHkNnDWiWKR94KFirzR7ST8(SupportSQLiteOpenHelper.Callback r1, DBRefHolder r2, SQLiteDatabase r3) {
            r1.onCorruption(Companion.getWrappedDb(r2, r3));
        }

        static {
            Companion = new Companion(null);
        }

        public OpenHelper(Context r7, String r8, DBRefHolder r9, SupportSQLiteOpenHelper.Callback r10, boolean r11) {
            super(r7, r8, null, r10.version, new FrameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda0(r10, r9));
            this.context = r7;
            this.dbRef = r9;
            this.callback = r10;
            this.allowDataLossOnRecovery = r11;
            if (r8 != null) goto L5;
            String r82 = UUID.randomUUID().toString();
        L6:
            this.lock = new ProcessLock(r82, r7.getCacheDir(), false);
            return;
        L5:
            r82 = r8;
            goto L6
        }

        private final SQLiteDatabase getWritableOrReadableDatabase(boolean r1) {
            if (r1 == false) goto L6;
            return super.getWritableDatabase();
        L6:
            return super.getReadableDatabase();
        }

        private final SQLiteDatabase innerGetDatabase(boolean r5) {
            String r0 = getDatabaseName();
            boolean r1 = this.opened;
            if (r0 == null) goto L51;
            if (r1 == true) goto L51;
            File r12 = this.context.getDatabasePath(r0).getParentFile();
            if (r12 == null) goto L51;
            r12.mkdirs();
            if (r12.isDirectory() == true) goto L51;
            Log.w("SupportSQLite", "Invalid database parent file, not a directory: " + r12);
        L51:
            return getWritableOrReadableDatabase(r5);
        L55:
            Thread.sleep(500);     // Catch: InterruptedException -> L48
        L49:
            return this.getWritableOrReadableDatabase(r5);
        L16:
            th = th;
            if ((th instanceof CallbackException) == false) goto L37;
            CallbackException r13 = (CallbackException) th;
            Throwable r2 = r13.getCause();
            int r14 = WhenMappings.$EnumSwitchMapping$0[r13.getCallbackName().ordinal()];
            if (r14 != 1) goto L22;
            throw r2;
        L22:
            if (r14 != 2) goto L24;
            throw r2;
        L24:
            if (r14 != 3) goto L26;
            throw r2;
        L26:
            if (r14 != 4) goto L28;
            throw r2;
        L28:
            if (r14 == 5) goto L30;
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L30:
            if ((r2 instanceof SQLiteException) == false) goto L32;
            th = r2;
            goto L37
        L32:
            throw r2;
        L37:
            if ((th instanceof SQLiteException) == false) goto L47;
            if (r0 == null) goto L47;
            if (this.allowDataLossOnRecovery == false) goto L47;
            this.context.deleteDatabase(r0);
            return this.getWritableOrReadableDatabase(r5);
        L44:
            e = move-exception;
            throw e.getCause();
        L47:
            throw th;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public void close() {
            ProcessLock.lock$default(this.lock, false, 1, null);     // Catch: Throwable -> L5
            super.close();     // Catch: Throwable -> L5
            this.dbRef.setDb(null);     // Catch: Throwable -> L5
            this.opened = false;     // Catch: Throwable -> L5
            this.lock.unlock();
            return;
        L5:
            th = move-exception;
            this.lock.unlock();
            throw th;
        }

        public final SupportSQLiteDatabase getSupportDatabase(boolean r4) {
            ProcessLock r0 = this.lock;     // Catch: Throwable -> L7
            if (this.opened == false) goto L5;
        L9:
            boolean r1 = false;
        L10:
            r0.lock(r1);     // Catch: Throwable -> L7
            this.migrated = false;     // Catch: Throwable -> L7
            SQLiteDatabase r02 = innerGetDatabase(r4);     // Catch: Throwable -> L7
            if (this.migrated == false) goto L15;
            close();     // Catch: Throwable -> L7
            SupportSQLiteDatabase r42 = getSupportDatabase(r4);     // Catch: Throwable -> L7
        L13:
            this.lock.unlock();
            return r42;
        L15:
            r42 = getWrappedDb(r02);     // Catch: Throwable -> L7
            goto L13
        L5:
            if (getDatabaseName() == null) goto L9;
            r1 = true;
        L7:
            th = move-exception;
            this.lock.unlock();
            throw th;
        }

        public final FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase r2) {
            return Companion.getWrappedDb(this.dbRef, r2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase r3) {
            if (this.migrated == false) goto L5;
        L12:
            this.callback.onConfigure(getWrappedDb(r3));     // Catch: Throwable -> L9
            return;
        L9:
            th = move-exception;
            throw new CallbackException(CallbackName.ON_CONFIGURE, th);
        L5:
            if (this.callback.version == r3.getVersion()) goto L12;
            r3.setMaxSqlCacheSize(1);
            goto L12
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase r2) {
            this.callback.onCreate(getWrappedDb(r2));     // Catch: Throwable -> L4
            return;
        L4:
            th = move-exception;
            throw new CallbackException(CallbackName.ON_CREATE, th);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase r2, int r3, int r4) {
            this.migrated = true;
            this.callback.onDowngrade(getWrappedDb(r2), r3, r4);     // Catch: Throwable -> L5
            return;
        L5:
            th = move-exception;
            throw new CallbackException(CallbackName.ON_DOWNGRADE, th);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase r2) {
            if (this.migrated == false) goto L11;
        L9:
            this.opened = true;
            return;
        L11:
            this.callback.onOpen(getWrappedDb(r2));     // Catch: Throwable -> L6
        L6:
            th = move-exception;
            throw new CallbackException(CallbackName.ON_OPEN, th);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase r2, int r3, int r4) {
            this.migrated = true;
            this.callback.onUpgrade(getWrappedDb(r2), r3, r4);     // Catch: Throwable -> L5
            return;
        L5:
            th = move-exception;
            throw new CallbackException(CallbackName.ON_UPGRADE, th);
        }
    }

    public static OpenHelper $r8$lambda$bMXVQYZgPq9YSTqMFpOzwjrmmJ8(FrameworkSQLiteOpenHelper r11) {
        if (r11.name != null) goto L5;
    L7:
        OpenHelper r4 = new OpenHelper(r11.context, r11.name, new DBRefHolder(null), r11.callback, r11.allowDataLossOnRecovery);
    L8:
        r4.setWriteAheadLoggingEnabled(r11.writeAheadLoggingEnabled);
        return r4;
    L5:
        if (r11.useNoBackupDirectory == false) goto L7;
        File r0 = new File(SupportSQLiteCompat$Api21Impl.getNoBackupFilesDir(r11.context), r11.name);
        r4 = new OpenHelper(r11.context, r0.getAbsolutePath(), new DBRefHolder(null), r11.callback, r11.allowDataLossOnRecovery);
        goto L8
    }

    static {
        Companion = new Companion(null);
    }

    @JvmOverloads
    public FrameworkSQLiteOpenHelper(Context r1, String r2, SupportSQLiteOpenHelper.Callback r3, boolean r4, boolean r5) {
        this.context = r1;
        this.name = r2;
        this.callback = r3;
        this.useNoBackupDirectory = r4;
        this.allowDataLossOnRecovery = r5;
        this.lazyDelegate = LazyKt.lazy(new FrameworkSQLiteOpenHelper$$ExternalSyntheticLambda0(this));
    }

    private final OpenHelper getDelegate() {
        return this.lazyDelegate.getValue();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.lazyDelegate.isInitialized() == false) goto L6;
        getDelegate().close();
        return;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.name;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        return getDelegate().getSupportDatabase(true);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean r2) {
        if (this.lazyDelegate.isInitialized() == false) goto L5;
        getDelegate().setWriteAheadLoggingEnabled(r2);
    L5:
        this.writeAheadLoggingEnabled = r2;
    }
}
