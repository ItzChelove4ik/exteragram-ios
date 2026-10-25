package org.telegram.messenger;

import android.os.Looper;
import android.util.LongSparseArray;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLiteException;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.CacheByChatsController;
import org.telegram.ui.Storage.CacheModel;

/* loaded from: classes.dex */
public class FilePathDatabase {
    private static final String DATABASE_BACKUP_NAME = "file_to_path_backup";
    private static final String DATABASE_NAME = "file_to_path";
    public static final int FLAG_LOCALLY_CREATED = 1;
    private static final int LAST_DB_VERSION = 7;
    public static final int MESSAGE_TYPE_VIDEO_MESSAGE = 0;
    private final String NULL_PATH;
    private final ConcurrentHashMap<String, String> cache;
    private File cacheFile;
    private final int currentAccount;
    private SQLiteDatabase database;
    boolean databaseCreated;
    private DispatchQueue dispatchQueue;
    private final FileMeta metaTmp;
    private File shmCacheFile;

    public static class FileMeta {
        public long dialogId;
        public int messageId;
        public long messageSize;
        public int messageType;

        public FileMeta() {
        }
    }

    /* loaded from: classes5.dex */
    public static class PathData {
        public final int dc;
        public final long id;
        public final int type;

        public PathData(long r1, int r3, int r4) {
            this.id = r1;
            this.dc = r3;
            this.type = r4;
        }
    }

    public static /* synthetic */ void $r8$lambda$3K_jyjq0V83In8Mya0aWQ4uYfjQ(FilePathDatabase r0, String r1, boolean[] r2, CountDownLatch r3) {
        r0.lambda$hasAnotherRefOnFile$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$6PYebqesT6JWlfPlQcpXJTLCrNY(FilePathDatabase r0, ArrayList r1, LongSparseArray r2, CountDownLatch r3) {
        r0.lambda$lookupFiles$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$A1VqQtOHVRaeYLo_PYoXmQXmTas(FilePathDatabase r0, long r1, int r3, int r4, String r5, int r6) {
        r0.lambda$putPath$1(r1, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$CLAaegZgOLHLzrAGX0a1v_B8xdw(FilePathDatabase r0, Runnable r1) {
        r0.postToFrontRunnable(r1);
    }

    public static /* synthetic */ void $r8$lambda$L3nURaNCPRfYlyOeqSTde0Xcct8(FilePathDatabase r0, String r1, boolean[] r2, CountDownLatch r3) {
        r0.lambda$isLocallyCreated$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$amVBHCuUjedk6dVg5QBGa6mLwK4(FilePathDatabase r0, List r1) {
        r0.lambda$removeFiles$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$dyrEK6II0AidDB8dDn3IMPUESVs(FilePathDatabase r0, Runnable r1) {
        r0.postRunnable(r1);
    }

    public static /* synthetic */ void $r8$lambda$j5TEW10KM2zPBlrP1O_dEY3FBEo(FilePathDatabase r0, long r1, int r3, int r4, String[] r5, long r6, CountDownLatch r8) {
        r0.lambda$getPath$0(r1, r3, r4, r5, r6, r8);
    }

    /* renamed from: $r8$lambda$jvcZT-fp5t4orjxphpbQXgFKTfw, reason: not valid java name */
    public static /* synthetic */ void m4059$r8$lambda$jvcZTfp5t4orjxphpbQXgFKTfw(FilePathDatabase r0) {
        r0.lambda$clear$3();
    }

    public static /* synthetic */ void $r8$lambda$pP4Qp44mYBQaD4awCEtLowFXNIg(FilePathDatabase r0, File r1, FileMeta r2) {
        r0.lambda$saveFileDialogId$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sCjsA8v8FOBY0rQXCUYWKxxAycw(FilePathDatabase r0, ArrayList r1, long[] r2, CountDownLatch r3) {
        r0.lambda$checkMediaExistance$2(r1, r2, r3);
    }

    public FilePathDatabase(int r2) {
        this.NULL_PATH = "~null~";
        this.cache = new ConcurrentHashMap();
        this.metaTmp = new FileMeta();
        this.currentAccount = r2;
    }

    private void createBackup() {
        File r1 = ApplicationLoader.getFilesDirFixed();
        if (this.currentAccount == 0) goto L5;
        File r2 = new File(r1, "account" + this.currentAccount + "/");
        r2.mkdirs();
        r1 = r2;
    L5:
        File r22 = new File(r1, "file_to_path_backup.db");
        AndroidUtilities.copyFile(this.cacheFile, r22);     // Catch: IOException -> L8
        FileLog.d("file db backup created " + r22.getAbsolutePath());     // Catch: IOException -> L8
        return;
    L8:
        e = move-exception;
        e.printStackTrace();
    }

    private void ensureQueueExist() {
        if (this.dispatchQueue != null) goto L14;
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L6:
        if (this.dispatchQueue != null) goto L10;
        DispatchQueue r1 = new DispatchQueue("files_database_queue_" + this.currentAccount);     // Catch: Throwable -> L8
        this.dispatchQueue = r1;     // Catch: Throwable -> L8
        r1.setPriority(10);     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
        return;
    }

    private /* synthetic */ void lambda$checkMediaExistance$2(ArrayList r5, long[] r6, CountDownLatch r7) {
        long r0 = System.currentTimeMillis();
        ensureDatabaseCreated();
        int r2 = 0;
    L17:
    L6:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L14
        r7.countDown();
        return;
    L14:
        th = move-exception;
        r7.countDown();
        throw th;
    L4:
        if (r2 >= r5.size()) goto L8;
        ((MessageObject) r5.get(r2)).checkMediaExistance(false);     // Catch: Throwable -> L6
        r2 = r2 + 1;     // Catch: Throwable -> L6
        goto L17
    L8:
        r6[0] = System.currentTimeMillis() - r0;     // Catch: Throwable -> L6
        r7.countDown();
    }

    private /* synthetic */ void lambda$clear$3() {
        ensureDatabaseCreated();
        this.database.executeFast("DELETE FROM paths WHERE 1").stepThis().dispose();     // Catch: Exception -> L5
        this.database.executeFast("DELETE FROM paths_by_dialog_id WHERE 1").stepThis().dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$getPath$0(long r6, int r8, int r9, String[] r10, long r11, CountDownLatch r13) {
        ensureDatabaseCreated();
        SQLiteDatabase r5 = this.database;
        if (r5 == null) goto L21;
        SQLiteCursor r2 = null;
        r2 = r5.queryFinalized("SELECT path FROM paths WHERE document_id = " + r6 + " AND dc_id = " + r8 + " AND type = " + r9, new Object[0]);     // Catch: Throwable -> L11
        if (r2.next() == false) goto L13;
        r10[0] = r2.stringValue(0);     // Catch: Throwable -> L11
        if (BuildVars.DEBUG_VERSION == false) goto L13;
        FileLog.d("get file path id=" + r6 + " dc=" + r8 + " type=" + r9 + " path=" + r10[0] + " in " + (System.currentTimeMillis() - r11) + "ms");     // Catch: Throwable -> L11
    L13:
        r2.dispose();
    L11:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L17
        if (r2 == null) goto L21;
    L17:
        th = move-exception;
        if (r2 == null) goto L20;
        r2.dispose();
    L20:
        throw th;
    L21:
        r13.countDown();
    }

    private /* synthetic */ void lambda$hasAnotherRefOnFile$4(String r3, boolean[] r4, CountDownLatch r5) {
        ensureDatabaseCreated();
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L7
        r5.countDown();
        return;
    L7:
        th = move-exception;
        r5.countDown();
        throw th;
    L4:
        if (this.database.queryFinalized("SELECT document_id FROM paths WHERE path = '" + r3 + "'", new Object[0]).next() == false) goto L11;
        r4[0] = true;     // Catch: Throwable -> L7 Exception -> L9
    L11:
        r5.countDown();
    }

    private /* synthetic */ void lambda$isLocallyCreated$8(String r4, boolean[] r5, CountDownLatch r6) {
        ensureDatabaseCreated();
        SQLiteCursor r1 = null;
        r1 = this.database.queryFinalized("SELECT flags FROM paths WHERE path = '" + r4 + "'", new Object[0]);     // Catch: Throwable -> L11 Exception -> L13
        if (r1.next() == false) goto L15;
        boolean r42 = true;
        if ((r1.intValue(0) & 1) != 0) goto L9;
        r42 = false;
    L9:
        r5[0] = r42;     // Catch: Throwable -> L11 Exception -> L13
    L15:
        r6.countDown();
        r1.dispose();
        return;
    L11:
        th = move-exception;
        r6.countDown();
        if (r1 == null) goto L25;
        r1.dispose();
    L25:
        throw th;
    L13:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L11
        r6.countDown();
        if (r1 == null) goto L28;
        r1.dispose();
        return;
    }

    private /* synthetic */ void lambda$lookupFiles$7(ArrayList r9, LongSparseArray r10, CountDownLatch r11) {
        ensureDatabaseCreated();     // Catch: Throwable -> L12
        FileMeta r0 = new FileMeta();     // Catch: Throwable -> L12
        int r2 = 0;
    L3:
        if (r2 >= r9.size()) goto L20;
        FileMeta r3 = getFileDialogId(((CacheByChatsController.KeepMediaFile) r9.get(r2)).file, r0);     // Catch: Throwable -> L12
        if (r3 == null) goto L19;
        long r4 = r3.dialogId;     // Catch: Throwable -> L12
        if (r4 == 0) goto L19;
        ArrayList r42 = (ArrayList) r10.get(r4);     // Catch: Throwable -> L12
        if (r42 != null) goto L14;
        r42 = new ArrayList();     // Catch: Throwable -> L12
        r10.put(r3.dialogId, r42);     // Catch: Throwable -> L12
    L14:
        CacheByChatsController.KeepMediaFile r5 = (CacheByChatsController.KeepMediaFile) r9.get(r2);     // Catch: Throwable -> L12
        if (r3.messageType != 23) goto L17;
        boolean r32 = true;
    L18:
        r5.isStory = r32;     // Catch: Throwable -> L12
        r42.add((CacheByChatsController.KeepMediaFile) r9.get(r2));     // Catch: Throwable -> L12
        goto L19
    L17:
        r32 = false;
    L19:
        r2 = r2 + 1;
        goto L3
    L20:
        r11.countDown();
        return;
    L12:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L25
        r11.countDown();
        return;
    L25:
        th = move-exception;
        r11.countDown();
        throw th;
    }

    private /* synthetic */ void lambda$putPath$1(long r7, int r9, int r10, String r11, int r12) {
        if (BuildVars.DEBUG_VERSION == false) goto L5;
        FileLog.d("put file path id=" + r7 + " dc=" + r9 + " type=" + r10 + " path=" + r11);
    L5:
        ensureDatabaseCreated();
        SQLiteDatabase r1 = this.database;
        if (r1 == null) goto L49;
        SQLitePreparedStatement r2 = null;
        if (r11 == null) goto L22;
        SQLitePreparedStatement r0 = r1.executeFast("DELETE FROM paths WHERE path = ?");     // Catch: Throwable -> L18 SQLiteException -> L20
        r0.bindString(1, r11);     // Catch: Throwable -> L14 SQLiteException -> L16
        r0.step();     // Catch: Throwable -> L14 SQLiteException -> L16
        r2 = this.database.executeFast("REPLACE INTO paths VALUES(?, ?, ?, ?, ?)");     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.requery();     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.bindLong(1, r7);     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.bindInteger(2, r9);     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.bindInteger(3, r10);     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.bindString(4, r11);     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.bindInteger(5, r12);     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.step();     // Catch: Throwable -> L14 SQLiteException -> L16
        r2.dispose();     // Catch: Throwable -> L14 SQLiteException -> L16
        this.cache.put(r7 + "_" + r9 + "_" + r10, r11);     // Catch: Throwable -> L14 SQLiteException -> L16
        SQLitePreparedStatement r6 = r2;
        r2 = r0;
    L24:
        if (r2 == null) goto L26;
        r2.dispose();
    L26:
        if (r6 == null) goto L47;
        r6.dispose();
        return;
    L47:
        return;
    L16:
        e = e;
        SQLitePreparedStatement r72 = r2;
        r2 = r0;
    L41:
        FileLog.e(e);     // Catch: Throwable -> L35
        if (r2 == null) goto L32;
        r2.dispose();
    L32:
        if (r72 == null) goto L48;
        r72.dispose();
        return;
    L48:
        return;
    L35:
        th = th;
    L36:
        if (r2 == null) goto L38;
        r2.dispose();
    L38:
        if (r72 == null) goto L40;
        r72.dispose();
    L40:
        throw th;
    L14:
        th = th;
        r72 = r2;
        r2 = r0;
        goto L36
    L22:
        r1.executeFast("DELETE FROM paths WHERE document_id = " + r7 + " AND dc_id = " + r9 + " AND type = " + r10).stepThis().dispose();     // Catch: Throwable -> L18 SQLiteException -> L20
        this.cache.put(r7 + "_" + r9 + "_" + r10, "~null~");     // Catch: Throwable -> L18 SQLiteException -> L20
        r6 = null;
    L20:
        e = e;
        r72 = r2;
    L18:
        th = th;
        r72 = r2;
        goto L36
    }

    private /* synthetic */ void lambda$removeFiles$6(List r5) {
        ensureDatabaseCreated();     // Catch: Throwable -> L8
        this.database.beginTransaction();     // Catch: Throwable -> L8
        int r0 = 0;
    L3:
        int r1 = r5.size();     // Catch: Throwable -> L8
        SQLiteDatabase r2 = this.database;
        if (r0 >= r1) goto L10;
        r2.executeFast("DELETE FROM paths_by_dialog_id WHERE path = '" + shield(((CacheModel.FileInfo) r5.get(r0)).file.getPath()) + "'").stepThis().dispose();     // Catch: Throwable -> L8
        r0 = r0 + 1;
        goto L3
    L10:
        r2.commitTransaction();
        return;
    L8:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L15
        this.database.commitTransaction();
        return;
    L15:
        th = move-exception;
        this.database.commitTransaction();
        throw th;
    }

    private /* synthetic */ void lambda$saveFileDialogId$5(File r4, FileMeta r5) {
        ensureDatabaseCreated();
        SQLitePreparedStatement r0 = null;
        r0 = this.database.executeFast("REPLACE INTO paths_by_dialog_id VALUES(?, ?, ?, ?)");     // Catch: Throwable -> L6 Exception -> L8
        r0.requery();     // Catch: Throwable -> L6 Exception -> L8
        r0.bindString(1, shield(r4.getPath()));     // Catch: Throwable -> L6 Exception -> L8
        r0.bindLong(2, r5.dialogId);     // Catch: Throwable -> L6 Exception -> L8
        r0.bindInteger(3, r5.messageId);     // Catch: Throwable -> L6 Exception -> L8
        r0.bindInteger(4, r5.messageType);     // Catch: Throwable -> L6 Exception -> L8
        r0.step();     // Catch: Throwable -> L6 Exception -> L8
        r0.dispose();
        return;
    L6:
        th = move-exception;
        if (r0 == null) goto L15;
        r0.dispose();
    L15:
        throw th;
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        if (r0 == null) goto L18;
        r0.dispose();
        return;
    }

    private void migrateDatabase(int r3) {
        if (r3 != 1) goto L6;
        this.database.executeFast("CREATE INDEX IF NOT EXISTS path_in_paths ON paths(path);").stepThis().dispose();
        this.database.executeFast("PRAGMA user_version = 2").stepThis().dispose();
        r3 = 2;
    L6:
        if (r3 != 2) goto L9;
        this.database.executeFast("CREATE TABLE paths_by_dialog_id(path TEXT PRIMARY KEY, dialog_id INTEGER);").stepThis().dispose();
        this.database.executeFast("PRAGMA user_version = 3").stepThis().dispose();
        r3 = 3;
    L9:
        if (r3 != 3) goto L11;
        this.database.executeFast("ALTER TABLE paths_by_dialog_id ADD COLUMN message_id INTEGER default 0").stepThis().dispose();
        this.database.executeFast("ALTER TABLE paths_by_dialog_id ADD COLUMN message_type INTEGER default 0").stepThis().dispose();
        this.database.executeFast("PRAGMA user_version = 4").stepThis().dispose();
        r3 = 4;
    L11:
        if (r3 != 4) goto L13;
    L24:
        this.database.executeFast("ALTER TABLE paths ADD COLUMN flags INTEGER default 0").stepThis().dispose();     // Catch: Throwable -> L20
    L22:
        this.database.executeFast("PRAGMA user_version = 7").stepThis().dispose();
        return;
    L20:
        th = move-exception;
        FileLog.e(th);
        goto L22
    L13:
        if (r3 == 5) goto L24;
        if (r3 == 6) goto L24;
    }

    private void postRunnable(Runnable r1) {
        ensureQueueExist();
        this.dispatchQueue.postRunnable(r1);
    }

    private void postToFrontRunnable(Runnable r1) {
        ensureQueueExist();
        this.dispatchQueue.postToFrontRunnable(r1);
    }

    private boolean restoreBackup() {
        File r0 = ApplicationLoader.getFilesDirFixed();
        if (this.currentAccount == 0) goto L5;
        File r1 = new File(r0, "account" + this.currentAccount + "/");
        r1.mkdirs();
        r0 = r1;
    L5:
        File r12 = new File(r0, "file_to_path_backup.db");
        if (r12.exists() == true) goto L13;
        return false;
    L13:
        return AndroidUtilities.copyFile(r12, this.cacheFile);
    L10:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    private String shield(String r2) {
        return r2.replace("'", _UrlKt.FRAGMENT_ENCODE_SET).replace("\"", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public void checkMediaExistance(ArrayList<MessageObject> r7) {
        if (r7.isEmpty() == true) goto L20;
        ArrayList r0 = new ArrayList(r7);
        CountDownLatch r1 = new CountDownLatch(1);
        long r3 = System.currentTimeMillis();
        long[] r2 = new long[1];
        postToFrontRunnable(new FilePathDatabase$$ExternalSyntheticLambda10(this, r0, r2, r1));
        r1.await();     // Catch: InterruptedException -> L8
    L10:
        FileLog.d("checkMediaExistance size=" + r7.size() + " time=" + (System.currentTimeMillis() - r3) + " thread_time=" + r2[0]);
        if (BuildVars.DEBUG_VERSION == true) goto L13;
        return;
    L13:
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) goto L19;
        FileLog.e(new Exception("warning, not allowed in main thread"));
        return;
    L19:
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    public void clear() {
        this.cache.clear();
        postRunnable(new FilePathDatabase$$ExternalSyntheticLambda8(this));
    }

    public void createDatabase(int r7, boolean r8) {
        File r2 = ApplicationLoader.getFilesDirFixed();
        if (this.currentAccount == 0) goto L5;
        File r3 = new File(r2, "account" + this.currentAccount + "/");
        r3.mkdirs();
        r2 = r3;
    L5:
        this.cacheFile = new File(r2, "file_to_path.db");
        this.shmCacheFile = new File(r2, "file_to_path.db-shm");
        boolean r22 = this.cacheFile.exists();
        SQLiteDatabase r4 = new SQLiteDatabase(this.cacheFile.getPath());     // Catch: Exception -> L10
        this.database = r4;     // Catch: Exception -> L10
        r4.executeFast("PRAGMA secure_delete = ON").stepThis().dispose();     // Catch: Exception -> L10
        this.database.executeFast("PRAGMA temp_store = MEMORY").stepThis().dispose();     // Catch: Exception -> L10
        SQLiteDatabase r42 = this.database;
        if (r22 == true) goto L12;
        r42.executeFast("CREATE TABLE paths(document_id INTEGER, dc_id INTEGER, type INTEGER, path TEXT, flags INTEGER, PRIMARY KEY(document_id, dc_id, type));").stepThis().dispose();     // Catch: Exception -> L10
        this.database.executeFast("CREATE INDEX IF NOT EXISTS path_in_paths ON paths(path);").stepThis().dispose();     // Catch: Exception -> L10
        this.database.executeFast("CREATE TABLE paths_by_dialog_id(path TEXT PRIMARY KEY, dialog_id INTEGER, message_id INTEGER, message_type INTEGER);").stepThis().dispose();     // Catch: Exception -> L10
        this.database.executeFast("PRAGMA user_version = 7").stepThis().dispose();     // Catch: Exception -> L10
    L17:
        if (r8 == true) goto L19;
        createBackup();     // Catch: Exception -> L10
    L19:
        FileLog.d("files db created from_backup= " + r8);     // Catch: Exception -> L10
        return;
    L12:
        int r23 = r42.executeInt("PRAGMA user_version", new Object[0]).intValue();     // Catch: Exception -> L10
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.d("current files db version = " + r23);     // Catch: Exception -> L10
    L15:
        if (r23 == 0) goto L22;
        migrateDatabase(r23);     // Catch: Exception -> L10
        goto L17
    L22:
        throw new Exception("malformed");     // Catch: Exception -> L10
    L10:
        e = move-exception;
        if (r7 >= 4) goto L33;
        if (r8 == false) goto L28;
    L31:
        this.cacheFile.delete();
        this.shmCacheFile.delete();
        createDatabase(r7 + 1, false);
        goto L33
    L28:
        if (restoreBackup() == false) goto L31;
        createDatabase(r7 + 1, true);
        return;
    L33:
        if (BuildVars.DEBUG_VERSION == false) goto L37;
        FileLog.e(e);
        return;
    }

    public void ensureDatabaseCreated() {
        if (this.databaseCreated == false) goto L5;
        return;
    L5:
        if (NativeLoader.loaded() == true) goto L16;
        int r0 = 0;
    L8:
        if (NativeLoader.loaded() == true) goto L16;
        Thread.sleep(1000);     // Catch: InterruptedException -> L12
    L14:
        r0 = r0 + 1;
        if (r0 <= 5) goto L8;
    L12:
        e = move-exception;
        e.printStackTrace();
    L16:
        createDatabase(0, false);
        this.databaseCreated = true;
    }

    public FileMeta getFileDialogId(File r9, FileMeta r10) {
        SQLiteCursor r1 = null;
        if (r9 != null) goto L5;
        return null;
    L5:
        if (r10 != null) goto L7;
        r10 = this.metaTmp;
    L7:
        int r2 = 0;
        long r3 = 0;
        r1 = this.database.queryFinalized("SELECT dialog_id, message_id, message_type FROM paths_by_dialog_id WHERE path = '" + shield(r9.getPath()) + "'", new Object[0]);     // Catch: Throwable -> L14 Exception -> L18
        if (r1.next() == false) goto L20;
        r3 = r1.longValue(0);     // Catch: Throwable -> L14 Exception -> L18
        int r8 = r1.intValue(1);     // Catch: Throwable -> L14 Exception -> L18
        r2 = r8;
        int r82 = r1.intValue(2);     // Catch: Throwable -> L14 Exception -> L16
    L21:
        r1.dispose();
    L26:
        r10.dialogId = r3;
        r10.messageId = r2;
        r10.messageType = r82;
        return r10;
    L16:
        e = e;
    L22:
        FileLog.e(e);     // Catch: Throwable -> L14
        if (r1 == null) goto L25;
        r1.dispose();
    L25:
        r2 = r8;
        r82 = 0;
        goto L26
    L20:
        r82 = 0;
    L18:
        e = e;
        r8 = 0;
    L14:
        th = move-exception;
        if (0 == 0) goto L30;
        r1.dispose();
    L30:
        throw th;
    }

    public String getPath(long r21, int r23, int r24, boolean r25) {
        long r7 = System.currentTimeMillis();
        String r10 = r21 + "_" + r23 + "_" + r24;
        String r9 = this.cache.get(r10);
        if (r9 != "~null~") goto L9;
        if (BuildVars.DEBUG_VERSION == false) goto L7;
        FileLog.d("get file path cached null id=" + r21 + " dc=" + r23 + " type=" + r24 + " path=null in " + (System.currentTimeMillis() - r7) + "ms");
    L7:
        return null;
    L9:
        if (r9 != null) goto L11;
        DispatchQueue r8 = this.dispatchQueue;
        if (r8 != null) goto L17;
    L21:
        boolean r82 = r25;
    L22:
        if (r82 == false) goto L36;
        CountDownLatch r92 = new CountDownLatch(1);
        String[] r6 = new String[1];
        if (Thread.currentThread() != ApplicationLoader.applicationHandler.getLooper().getThread()) goto L27;
        Consumer r0 = new FilePathDatabase$$ExternalSyntheticLambda4(this);
    L28:
        r0.accept(new FilePathDatabase$$ExternalSyntheticLambda6(this, r21, r23, r24, r6, r7, r92));
        r92.await();     // Catch: Exception -> L77
    L30:
        String r02 = r6[0];
        ConcurrentHashMap<String, String> r1 = this.cache;
        if (r02 == null) goto L33;
        r1.put(r10, r02);
    L35:
        return r6[0];
    L33:
        r1.put(r10, "~null~");
        goto L35
    L27:
        r0 = new FilePathDatabase$$ExternalSyntheticLambda5(this);
        goto L28
    L36:
        SQLiteDatabase r93 = this.database;
        if (r93 != null) goto L83;
        return null;
    L83:
        String r19 = "~null~";
        SQLiteCursor r62 = r93.queryFinalized("SELECT path FROM paths WHERE document_id = " + r21 + " AND dc_id = " + r23 + " AND type = " + r24, new Object[0]);     // Catch: Throwable -> L58 SQLiteException -> L60
    L54:
        e = e;
        SQLiteCursor r14 = r62;
        String r83 = null;
    L87:
        FileLog.e(e);     // Catch: Throwable -> L73
        if (r14 == null) goto L67;
        r14.dispose();
    L67:
        String r142 = r83;
    L68:
        ConcurrentHashMap<String, String> r03 = this.cache;
        if (r142 == null) goto L71;
        r03.put(r10, r142);
    L72:
        return r142;
    L71:
        r03.put(r10, r19);
    L73:
        th = th;
    L74:
        if (r14 == null) goto L76;
        r14.dispose();
    L76:
        throw th;
    L49:
        th = th;
        r14 = r62;
        goto L74
    L43:
        if (r62.next() == false) goto L56;
        r83 = r62.stringValue(0);     // Catch: Throwable -> L49 SQLiteException -> L54
    L51:
        e = e;
        r14 = r62;
        goto L87
    L46:
        if (BuildVars.DEBUG_VERSION == false) goto L53;
        FileLog.d("get file path id=" + r21 + " dc=" + r23 + " type=" + r24 + " path=" + r83 + " in " + (System.currentTimeMillis() - r7) + "ms");     // Catch: Throwable -> L49 SQLiteException -> L51
    L53:
        r142 = r83;
    L57:
        r62.dispose();
        goto L68
    L56:
        r142 = null;
        goto L57
    L60:
        e = e;
    L61:
        r83 = null;
        r14 = null;
    L62:
        e = e;
        r19 = "~null~";
    L58:
        th = th;
        r14 = null;
        goto L74
    L17:
        if (r8.getHandler() == null) goto L21;
        if (Thread.currentThread() != this.dispatchQueue.getHandler().getLooper().getThread()) goto L21;
        r82 = false;
        goto L22
    L11:
        if (BuildVars.DEBUG_VERSION == false) goto L13;
        FileLog.d("get file path cached id=" + r21 + " dc=" + r23 + " type=" + r24 + " path=" + r9 + " in " + (System.currentTimeMillis() - r7) + "ms");
    L13:
        return r9;
    }

    public DispatchQueue getQueue() {
        ensureQueueExist();
        return this.dispatchQueue;
    }

    public boolean hasAnotherRefOnFile(String r5) {
        CountDownLatch r0 = new CountDownLatch(1);
        boolean[] r1 = {false};
        postRunnable(new FilePathDatabase$$ExternalSyntheticLambda3(this, r5, r1, r0));
        r0.await();     // Catch: InterruptedException -> L5
    L8:
        return r1[0];
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public boolean isLocallyCreated(String r5) {
        CountDownLatch r0 = new CountDownLatch(1);
        boolean[] r1 = {false};
        postRunnable(new FilePathDatabase$$ExternalSyntheticLambda0(this, r5, r1, r0));
        r0.await();     // Catch: InterruptedException -> L5
    L8:
        return r1[0];
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public LongSparseArray<ArrayList<CacheByChatsController.KeepMediaFile>> lookupFiles(ArrayList<? extends CacheByChatsController.KeepMediaFile> r4) {
        CountDownLatch r0 = new CountDownLatch(1);
        LongSparseArray<ArrayList<CacheByChatsController.KeepMediaFile>> r1 = new LongSparseArray();
        postRunnable(new FilePathDatabase$$ExternalSyntheticLambda1(this, r4, r1, r0));
        r0.await();     // Catch: InterruptedException -> L5
        return r1;
    L5:
        e = move-exception;
        FileLog.e(e);
        return r1;
    }

    public void putPath(long r9, int r11, int r12, int r13, String r14) {
        postRunnable(new FilePathDatabase$$ExternalSyntheticLambda2(this, r9, r11, r12, r14, r13));
    }

    public void removeFiles(List<CacheModel.FileInfo> r2) {
        postRunnable(new FilePathDatabase$$ExternalSyntheticLambda7(this, r2));
    }

    public void saveFileDialogId(File r2, FileMeta r3) {
        if (r2 == null) goto L7;
        if (r3 == null) goto L8;
        postRunnable(new FilePathDatabase$$ExternalSyntheticLambda9(this, r2, r3));
        return;
    L8:
        return;
    }
}
