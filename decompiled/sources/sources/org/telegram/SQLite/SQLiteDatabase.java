package org.telegram.SQLite;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
public class SQLiteDatabase {
    private boolean inTransaction;
    private boolean isOpen;
    private final long sqliteHandle;

    public SQLiteDatabase(String r3) {
        this.sqliteHandle = opendb(r3, ApplicationLoader.getFilesDirFixed().getPath());
        this.isOpen = true;
    }

    public void beginTransaction() {
        if (this.inTransaction == true) goto L5;
    L9:
        this.inTransaction = true;
        beginTransaction(this.sqliteHandle);
        return;
    L5:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L8;
        commitTransaction();
        goto L9
    L8:
        throw new SQLiteException("database already in transaction");
    }

    public native void beginTransaction(long r1);

    public void checkOpened() {
        if (this.isOpen == false) goto L6;
        return;
    L6:
        throw new SQLiteException("Database closed");
    }

    public void close() {
        if (this.isOpen == false) goto L14;
        commitTransaction();     // Catch: SQLiteException -> L6
        closedb(this.sqliteHandle);     // Catch: SQLiteException -> L6
    L10:
        this.isOpen = false;
        return;
    L6:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        FileLog.e(e.getMessage(), e);
        goto L10
    }

    public native void closedb(long r1);

    public void commitTransaction() {
        if (this.inTransaction == true) goto L5;
        return;
    L5:
        this.inTransaction = false;
        commitTransaction(this.sqliteHandle);
    }

    public native void commitTransaction(long r1);

    public SQLitePreparedStatement executeFast(String r2) {
        return new SQLitePreparedStatement(this, r2);
    }

    public Integer executeInt(String r1, Object... r2) {
        checkOpened();
        SQLiteCursor r0 = queryFinalized(r1, r2);
    L11:
        th = move-exception;
        r0.dispose();
        throw th;
    L4:
        if (r0.next() == true) goto L8;
        r0.dispose();
        return null;
    L8:
        Integer r12 = Integer.valueOf(r0.intValue(0));     // Catch: Throwable -> L11
        r0.dispose();
        return r12;
    }

    public void explainQuery(String r4, Object... r5) {
        checkOpened();
        SQLiteCursor r3 = new SQLitePreparedStatement(this, "EXPLAIN QUERY PLAN " + r4).query(r5);
    L4:
        if (r3.next() == false) goto L9;
        int r42 = r3.getColumnCount();
        StringBuilder r52 = new StringBuilder();
        int r0 = 0;
    L6:
        if (r0 >= r42) goto L8;
        r52.append(r3.stringValue(r0));
        r52.append(", ");
        r0 = r0 + 1;
        goto L6
    L8:
        FileLog.d("EXPLAIN QUERY PLAN ".concat(r52.toString()));
        goto L4
    L9:
        r3.dispose();
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public long getSQLiteHandle() {
        return this.sqliteHandle;
    }

    public native long opendb(String r1, String r2);

    public SQLiteCursor queryFinalized(String r2, Object... r3) {
        checkOpened();
        return new SQLitePreparedStatement(this, r2).query(r3);
    }

    public boolean tableExists(String r2) {
        checkOpened();
        if (executeInt("SELECT rowid FROM sqlite_master WHERE type='table' AND name=?;", new Object[]{r2}) == null) goto L6;
        return true;
    L6:
        return false;
    }
}
