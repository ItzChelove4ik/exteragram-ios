package androidx.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.util.Pair;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0003\u0011\u0012\u0013J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\f\u001a\u0004\u0018\u00010\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Ljava/io/Closeable;", _UrlKt.FRAGMENT_ENCODE_SET, "enabled", _UrlKt.FRAGMENT_ENCODE_SET, "setWriteAheadLoggingEnabled", "(Z)V", "close", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "getDatabaseName", "()Ljava/lang/String;", "databaseName", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getWritableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "Callback", "Configuration", "Factory", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public interface SupportSQLiteOpenHelper extends Closeable {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J \u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H&J \u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", _UrlKt.FRAGMENT_ENCODE_SET, "version", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "onConfigure", _UrlKt.FRAGMENT_ENCODE_SET, "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "onCreate", "onUpgrade", "oldVersion", "newVersion", "onDowngrade", "onOpen", "onCorruption", "deleteDatabaseFile", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSupportSQLiteOpenHelper.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SupportSQLiteOpenHelper.android.kt\nandroidx/sqlite/db/SupportSQLiteOpenHelper$Callback\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n1#1,366:1\n1#2:367\n1869#3,2:368\n108#4:370\n80#4,22:371\n*S KotlinDebug\n*F\n+ 1 SupportSQLiteOpenHelper.android.kt\nandroidx/sqlite/db/SupportSQLiteOpenHelper$Callback\n*L\n220#1:368,2\n228#1:370\n228#1:371,22\n*E\n"})
    public static abstract class Callback {
        public static final Companion Companion = null;

        @JvmField
        public final int version;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Companion = new Companion(null);
        }

        public Callback(int r1) {
            this.version = r1;
        }

        private final void deleteDatabaseFile(String r7) {
            if (StringsKt.equals(r7, ":memory:", true) == true) goto L41;
            int r6 = r7.length() - 1;
            int r2 = 0;
            boolean r3 = false;
        L5:
            if (r2 > r6) goto L21;
            if (r3 == true) goto L8;
            int r4 = r2;
        L10:
            if (Intrinsics.compare(r7.charAt(r4), 32) > 0) goto L12;
            boolean r42 = true;
        L13:
            if (r3 == false) goto L14;
            if (r42 == false) goto L21;
            r6 = r6 - 1;
            goto L5
        L14:
            if (r42 == false) goto L15;
            r2 = r2 + 1;
            goto L5
        L15:
            r3 = true;
            goto L5
        L12:
            r42 = false;
            goto L13
        L8:
            r4 = r6;
        L21:
            if (r7.subSequence(r2, r6 + 1).toString().length() != 0) goto L23;
            return;
        L23:
            Log.w("SupportSQLite", "deleting the database file: " + r7);
            SQLiteDatabase.deleteDatabase(new File(r7));     // Catch: Exception -> L26
            return;
        L26:
            e = move-exception;
            Log.w("SupportSQLite", "delete failed: ", e);
            return;
        }

        public void onConfigure(SupportSQLiteDatabase r1) {
        }

        public void onCorruption(SupportSQLiteDatabase r3) {
            Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + r3 + ".path");
            if (r3.isOpen() == true) goto L8;
            String r32 = r3.getPath();
            if (r32 == null) goto L41;
            deleteDatabaseFile(r32);
            return;
        L41:
            return;
        L8:
            List<Pair<String, String>> r0 = null;
            r0 = r3.getAttachedDbs();     // Catch: Throwable -> L11 SQLiteException -> L33
        L11:
            th = move-exception;
            if (r0 == null) goto L20;
            Iterator<T> r33 = r0.iterator();
        L18:
            if (r33.hasNext() == false) goto L23;
            deleteDatabaseFile((String) ((Pair) r33.next()).second);
        L23:
            throw th;
        L20:
            String r34 = r3.getPath();
            if (r34 == null) goto L23;
            deleteDatabaseFile(r34);
        L35:
            r3.close();     // Catch: Throwable -> L11 IOException -> L34
        L24:
            if (r0 == null) goto L29;
            Iterator<T> r35 = r0.iterator();
        L27:
            if (r35.hasNext() == false) goto L43;
            deleteDatabaseFile((String) ((Pair) r35.next()).second);
            goto L27
        L43:
            return;
        L29:
            String r36 = r3.getPath();
            if (r36 == null) goto L42;
            deleteDatabaseFile(r36);
            return;
        }

        public abstract void onCreate(SupportSQLiteDatabase r1);

        public abstract void onDowngrade(SupportSQLiteDatabase r1, int r2, int r3);

        public abstract void onOpen(SupportSQLiteDatabase r1);

        public abstract void onUpgrade(SupportSQLiteDatabase r1, int r2, int r3);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u000e2\u00020\u0001:\u0002\r\u000eB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "name", _UrlKt.FRAGMENT_ENCODE_SET, com.sun.jna.Callback.METHOD_NAME, "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "useNoBackupDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "allowDataLossOnRecovery", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;ZZ)V", "Builder", "Companion", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Configuration {
        public static final Companion Companion = null;

        @JvmField
        public final boolean allowDataLossOnRecovery;

        @JvmField
        public final Callback callback;

        @JvmField
        public final Context context;

        @JvmField
        public final String name;

        @JvmField
        public final boolean useNoBackupDirectory;

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u0006\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "name", _UrlKt.FRAGMENT_ENCODE_SET, com.sun.jna.Callback.METHOD_NAME, "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "useNoBackupDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "allowDataLossOnRecovery", "build", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "noBackupDirectory", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nSupportSQLiteOpenHelper.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SupportSQLiteOpenHelper.android.kt\nandroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,366:1\n1#2:367\n*E\n"})
        public static class Builder {
            private boolean allowDataLossOnRecovery;
            private Callback callback;
            private final Context context;
            private String name;
            private boolean useNoBackupDirectory;

            public Builder(Context r1) {
                this.context = r1;
            }

            public Builder allowDataLossOnRecovery(boolean r1) {
                this.allowDataLossOnRecovery = r1;
                return this;
            }

            public Configuration build() {
                Callback r3 = this.callback;
                if (r3 != null) goto L5;
                f$$ExternalSyntheticBUOutline1.m("Must set a callback to create the configuration.");
                return null;
            L5:
                if (this.useNoBackupDirectory == false) goto L14;
                String r1 = this.name;
                if (r1 != null) goto L9;
            L11:
                f$$ExternalSyntheticBUOutline1.m("Must set a non-null database name to a configuration that uses the no backup directory.");
                return null;
            L9:
                if (r1.length() == 0) goto L11;
            L14:
                return new Configuration(this.context, this.name, r3, this.useNoBackupDirectory, this.allowDataLossOnRecovery);
            }

            public Builder callback(Callback r1) {
                this.callback = r1;
                return this;
            }

            public Builder name(String r1) {
                this.name = r1;
                return this;
            }

            public Builder noBackupDirectory(boolean r1) {
                this.useNoBackupDirectory = r1;
                return this;
            }
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "builder", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Builder;", "context", "Landroid/content/Context;", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final Builder builder(Context r1) {
                return new Builder(r1);
            }
        }

        static {
            Companion = new Companion(null);
        }

        public Configuration(Context r1, String r2, Callback r3, boolean r4, boolean r5) {
            this.context = r1;
            this.name = r2;
            this.callback = r3;
            this.useNoBackupDirectory = r4;
            this.allowDataLossOnRecovery = r5;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", _UrlKt.FRAGMENT_ENCODE_SET, "create", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "configuration", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration r1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean r1);
}
