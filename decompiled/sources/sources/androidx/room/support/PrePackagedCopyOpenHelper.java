package androidx.room.support;

import android.content.Context;
import android.util.Log;
import androidx.room.DatabaseConfiguration;
import androidx.room.DelegatingOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.FileUtil;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BC\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u001a\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001c\u0010\u0015J\u000f\u0010\u001d\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010!\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010#R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010$R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010%R\u001c\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010&R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010'R\u001a\u0010\u000e\u001a\u00020\u00018\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010(\u001a\u0004\b)\u0010*R\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b \u0010+R\u0016\u0010,\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u00100\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00104\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103¨\u00065"}, d2 = {"Landroidx/room/support/PrePackagedCopyOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/DelegatingOpenHelper;", "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "copyFromAssetPath", "Ljava/io/File;", "copyFromFile", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "copyFromInputStream", _UrlKt.FRAGMENT_ENCODE_SET, "databaseVersion", "delegate", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;ILandroidx/sqlite/db/SupportSQLiteOpenHelper;)V", _UrlKt.FRAGMENT_ENCODE_SET, "writable", _UrlKt.FRAGMENT_ENCODE_SET, "verifyDatabaseFile", "(Z)V", "destinationFile", "copyDatabaseFile", "(Ljava/io/File;Z)V", "databaseFile", "dispatchOnOpenPrepackagedDatabase", "enabled", "setWriteAheadLoggingEnabled", "close", "()V", "Landroidx/room/DatabaseConfiguration;", "databaseConfiguration", "setDatabaseConfiguration", "(Landroidx/room/DatabaseConfiguration;)V", "Landroid/content/Context;", "Ljava/lang/String;", "Ljava/io/File;", "Ljava/util/concurrent/Callable;", "I", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "getDelegate", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/DatabaseConfiguration;", "verified", "Z", "getDatabaseName", "()Ljava/lang/String;", "databaseName", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getWritableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PrePackagedCopyOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    private final Context context;
    private final String copyFromAssetPath;
    private final File copyFromFile;
    private final Callable<InputStream> copyFromInputStream;
    private DatabaseConfiguration databaseConfiguration;
    private final int databaseVersion;
    private final SupportSQLiteOpenHelper delegate;
    private boolean verified;

    public PrePackagedCopyOpenHelper(Context r1, String r2, File r3, Callable<InputStream> r4, int r5, SupportSQLiteOpenHelper r6) {
        this.context = r1;
        this.copyFromAssetPath = r2;
        this.copyFromFile = r3;
        this.copyFromInputStream = r4;
        this.databaseVersion = r5;
        this.delegate = r6;
    }

    private final void copyDatabaseFile(File r5, boolean r6) {
        if (this.copyFromAssetPath == null) goto L6;
        ReadableByteChannel r0 = Channels.newChannel(this.context.getAssets().open(this.copyFromAssetPath));
    L12:
        File r1 = File.createTempFile("room-copy-helper", ".tmp", this.context.getCacheDir());
        r1.deleteOnExit();
        FileUtil.copy(r0, new FileOutputStream(r1).getChannel());
        File r02 = r5.getParentFile();
        if (r02 != null) goto L15;
    L21:
        dispatchOnOpenPrepackagedDatabase(r1, r6);
        if (r1.renameTo(r5) == false) goto L25;
        return;
    L25:
        throw new IOException("Failed to move intermediate file (" + r1.getAbsolutePath() + ") to destination (" + r5.getAbsolutePath() + ").");
    L15:
        if (r02.exists() == true) goto L21;
        if (r02.mkdirs() == true) goto L21;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Failed to create directories for ", r5.getAbsolutePath());
        return;
    L6:
        if (this.copyFromFile == null) goto L8;
        r0 = new FileInputStream(this.copyFromFile).getChannel();
        goto L12
    L8:
        Callable<InputStream> r03 = this.copyFromInputStream;
        if (r03 != null) goto L31;
        Segment$$ExternalSyntheticBUOutline1.m("copyFromAssetPath, copyFromFile and copyFromInputStream are all null!");
        return;
    L31:
        r0 = Channels.newChannel(r03.call());
    L26:
        e = move-exception;
        throw new IOException("inputStreamCallable exception on call", e);
    }

    private final void dispatchOnOpenPrepackagedDatabase(File r1, boolean r2) {
        DatabaseConfiguration r0 = this.databaseConfiguration;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.getClass();
    }

    private final void verifyDatabaseFile(boolean r11) {
        String r2 = getDatabaseName();
        if (r2 == null) goto L54;
        File r3 = this.context.getDatabasePath(r2);
        DatabaseConfiguration r4 = this.databaseConfiguration;
        DatabaseConfiguration r5 = null;
        if (r4 != null) goto L7;
        r4 = null;
    L7:
        ProcessLock r6 = new ProcessLock(r2, this.context.getFilesDir(), r4.multiInstanceInvalidation);
        ProcessLock.lock$default(r6, false, 1, null);     // Catch: Throwable -> L14
        if (r3.exists() == true) goto L58;
        copyDatabaseFile(r3, r11);     // Catch: Throwable -> L14 IOException -> L16
        r6.unlock();
        return;
    L16:
        e = move-exception;
        throw new RuntimeException("Unable to copy database file.", e);     // Catch: Throwable -> L14
    L58:
        int r42 = DBUtil.readVersion(r3);     // Catch: Throwable -> L14 IOException -> L48
        int r8 = this.databaseVersion;     // Catch: Throwable -> L14
        if (r42 != r8) goto L24;
        r6.unlock();
        return;
    L24:
        DatabaseConfiguration r9 = this.databaseConfiguration;     // Catch: Throwable -> L14
        if (r9 != null) goto L28;
        r9 = null;
    L28:
        if (r9.migrationContainer.findMigrationPath(r42, r8) == null) goto L31;
        r6.unlock();
        return;
    L31:
        DatabaseConfiguration r82 = this.databaseConfiguration;     // Catch: Throwable -> L14
        if (r82 == null) goto L36;
        r5 = r82;
    L36:
        if (r5.isMigrationRequired(r42, this.databaseVersion) == false) goto L40;
        r6.unlock();
        return;
    L40:
        if (this.context.deleteDatabase(r2) == true) goto L61;
        Log.w("ROOM", "Failed to delete database file (" + r2 + ") for a copy destructive migration.");     // Catch: Throwable -> L14
    L46:
        r6.unlock();
        return;
    L61:
        copyDatabaseFile(r3, r11);     // Catch: Throwable -> L14 IOException -> L43
        Unit r10 = Unit.INSTANCE;     // Catch: Throwable -> L14 IOException -> L43
    L43:
        e = move-exception;
        Log.w("ROOM", "Unable to copy database file.", e);     // Catch: Throwable -> L14
        goto L46
    L48:
        e = move-exception;
        Log.w("ROOM", "Unable to read database version.", e);     // Catch: Throwable -> L14
        r6.unlock();
        return;
    L14:
        th = move-exception;
        r6.unlock();
        throw th;
    L54:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        monitor-enter(this);
        getDelegate().close();     // Catch: Throwable -> L6
        this.verified = false;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return getDelegate().getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    public SupportSQLiteOpenHelper getDelegate() {
        return this.delegate;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        if (this.verified == true) goto L6;
        verifyDatabaseFile(true);
        this.verified = true;
    L6:
        return getDelegate().getWritableDatabase();
    }

    public final void setDatabaseConfiguration(DatabaseConfiguration r1) {
        this.databaseConfiguration = r1;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean r1) {
        getDelegate().setWriteAheadLoggingEnabled(r1);
    }
}
