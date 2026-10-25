package org.telegram.SQLite;

import android.os.SystemClock;
import java.nio.ByteBuffer;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
public class SQLitePreparedStatement {
    private boolean isFinalized;
    private String query;
    private long sqliteStatementHandle;
    private long startTime;

    public SQLitePreparedStatement(SQLiteDatabase r3, String r4) {
        this.isFinalized = false;
        this.sqliteStatementHandle = prepare(r3.getSQLiteHandle(), r4);
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        this.query = r4;
        this.startTime = SystemClock.elapsedRealtime();
        return;
    }

    public void bindByteBuffer(int r7, ByteBuffer r8) {
        bindByteBuffer(this.sqliteStatementHandle, r7, r8, r8.limit());
    }

    public void bindByteBuffer(int r7, NativeByteBuffer r8) {
        bindByteBuffer(this.sqliteStatementHandle, r7, r8.buffer, r8.limit());
    }

    public native void bindByteBuffer(long r1, int r3, ByteBuffer r4, int r5);

    public void bindDouble(int r7, double r8) {
        bindDouble(this.sqliteStatementHandle, r7, r8);
    }

    public native void bindDouble(long r1, int r3, double r4);

    public native void bindInt(long r1, int r3, int r4);

    public void bindInteger(int r3, int r4) {
        bindInt(this.sqliteStatementHandle, r3, r4);
    }

    public void bindLong(int r7, long r8) {
        bindLong(this.sqliteStatementHandle, r7, r8);
    }

    public native void bindLong(long r1, int r3, long r4);

    public void bindNull(int r3) {
        bindNull(this.sqliteStatementHandle, r3);
    }

    public native void bindNull(long r1, int r3);

    public void bindString(int r3, String r4) {
        bindString(this.sqliteStatementHandle, r3, r4);
    }

    public native void bindString(long r1, int r3, String r4);

    public void bindTlObject(int r3, TLObject r4) {
        NativeByteBuffer r0 = new NativeByteBuffer(r4.getObjectSize());
        r4.serializeToStream(r0);     // Catch: Throwable -> L6
        bindByteBuffer(r3, r0);     // Catch: Throwable -> L6
        r0.reuse();
        return;
    L6:
        th = move-exception;
        r0.reuse();
        throw th;
    }

    public void checkFinalized() {
        if (this.isFinalized == true) goto L6;
        return;
    L6:
        throw new SQLiteException("Prepared query finalized");
    }

    public void dispose() {
        finalizeQuery();
    }

    public native void finalize(long r1);

    public void finalizeQuery() {
        if (this.isFinalized == false) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        long r0 = SystemClock.elapsedRealtime() - this.startTime;
        if (r0 <= 500) goto L18;
        FileLog.d("sqlite query " + this.query + " took " + r0 + "ms");
    L18:
        this.isFinalized = true;     // Catch: SQLiteException -> L13
        finalize(this.sqliteStatementHandle);     // Catch: SQLiteException -> L13
        return;
    L13:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L20;
        FileLog.e(e.getMessage(), e);
        return;
    }

    public long getStatementHandle() {
        return this.sqliteStatementHandle;
    }

    public native long prepare(long r1, String r3);

    public SQLiteCursor query(Object[] r10) {
        if (r10 == null) goto L28;
        checkFinalized();
        reset(this.sqliteStatementHandle);
        int r2 = 0;
        int r6 = 1;
    L6:
        if (r2 >= r10.length) goto L27;
        Object r1 = r10[r2];
        if (r1 != null) goto L12;
        this.bindNull(this.sqliteStatementHandle, r6);
    L10:
        SQLitePreparedStatement r3 = this;
    L23:
        r6 = r6 + 1;
        r2 = r2 + 1;
        SQLitePreparedStatement this = r3;
        goto L6
    L12:
        if ((r1 instanceof Integer) == false) goto L15;
        this.bindInt(this.sqliteStatementHandle, r6, ((Integer) r1).intValue());
        goto L10
    L15:
        if ((r1 instanceof Double) == false) goto L17;
        r3 = this;
        r3.bindDouble(this.sqliteStatementHandle, r6, ((Double) r1).doubleValue());
        goto L23
    L17:
        r3 = this;
        if ((r1 instanceof String) == false) goto L21;
        r3.bindString(r3.sqliteStatementHandle, r6, (String) r1);
        goto L23
    L21:
        if ((r1 instanceof Long) == false) goto L24;
        r3.bindLong(r3.sqliteStatementHandle, r6, ((Long) r1).longValue());
        goto L23
    L24:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L27:
        return new SQLiteCursor(this);
    L28:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public void requery() {
        checkFinalized();
        reset(this.sqliteStatementHandle);
    }

    public native void reset(long r1);

    public int step() {
        return step(this.sqliteStatementHandle);
    }

    public native int step(long r1);

    public SQLitePreparedStatement stepThis() {
        step(this.sqliteStatementHandle);
        return this;
    }
}
