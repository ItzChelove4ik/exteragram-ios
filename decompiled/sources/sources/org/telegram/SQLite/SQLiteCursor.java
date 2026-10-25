package org.telegram.SQLite;

import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public class SQLiteCursor {
    public static final int FIELD_TYPE_BYTEARRAY = 4;
    public static final int FIELD_TYPE_FLOAT = 2;
    public static final int FIELD_TYPE_INT = 1;
    public static final int FIELD_TYPE_NULL = 5;
    public static final int FIELD_TYPE_STRING = 3;
    private boolean inRow;
    private SQLitePreparedStatement preparedStatement;

    public SQLiteCursor(SQLitePreparedStatement r2) {
        this.inRow = false;
        this.preparedStatement = r2;
    }

    public byte[] byteArrayValue(int r3) {
        checkRow();
        return columnByteArrayValue(this.preparedStatement.getStatementHandle(), r3);
    }

    public NativeByteBuffer byteBufferValue(int r3) {
        checkRow();
        long r2 = columnByteBufferValue(this.preparedStatement.getStatementHandle(), r3);
        if (r2 != 0) goto L5;
        return null;
    L5:
        return NativeByteBuffer.wrap(r2);
    }

    public void checkRow() {
        if (this.inRow == false) goto L6;
        return;
    L6:
        throw new SQLiteException("You must call next before");
    }

    public native byte[] columnByteArrayValue(long r1, int r3);

    public native long columnByteBufferValue(long r1, int r3);

    public native int columnCount(long r1);

    public native double columnDoubleValue(long r1, int r3);

    public native int columnIntValue(long r1, int r3);

    public native int columnIsNull(long r1, int r3);

    public native long columnLongValue(long r1, int r3);

    public native String columnStringValue(long r1, int r3);

    public native int columnType(long r1, int r3);

    public void dispose() {
        this.preparedStatement.dispose();
    }

    public double doubleValue(int r3) {
        checkRow();
        return columnDoubleValue(this.preparedStatement.getStatementHandle(), r3);
    }

    public int getColumnCount() {
        return columnCount(this.preparedStatement.getStatementHandle());
    }

    public SQLitePreparedStatement getPreparedStatement() {
        return this.preparedStatement;
    }

    public long getStatementHandle() {
        return this.preparedStatement.getStatementHandle();
    }

    public int getTypeOf(int r3) {
        checkRow();
        return columnType(this.preparedStatement.getStatementHandle(), r3);
    }

    public int intValue(int r3) {
        checkRow();
        return columnIntValue(this.preparedStatement.getStatementHandle(), r3);
    }

    public boolean isNull(int r3) {
        checkRow();
        if (columnIsNull(this.preparedStatement.getStatementHandle(), r3) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public long longValue(int r3) {
        checkRow();
        return columnLongValue(this.preparedStatement.getStatementHandle(), r3);
    }

    public boolean next() {
        SQLitePreparedStatement r0 = this.preparedStatement;
        int r02 = r0.step(r0.getStatementHandle());
        if (r02 != (-1)) goto L21;
        int r2 = 6;
    L5:
        int r3 = r2 - 1;
        if (r2 == 0) goto L17;
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("sqlite busy, waiting...");     // Catch: Exception -> L10
    L12:
        Thread.sleep(500);     // Catch: Exception -> L10
        r02 = this.preparedStatement.step();     // Catch: Exception -> L10
        if (r02 == 0) goto L17;
    L15:
        r2 = r3;
    L10:
        e = move-exception;
        FileLog.e(e);
    L17:
        if (r02 != (-1)) goto L21;
        throw new SQLiteException("sqlite busy");
    L21:
        if (r02 != 0) goto L23;
        boolean r03 = true;
    L24:
        this.inRow = r03;
        return r03;
    L23:
        r03 = false;
        goto L24
    }

    public String stringValue(int r3) {
        checkRow();
        return columnStringValue(this.preparedStatement.getStatementHandle(), r3);
    }

    public <T extends TLObject> T tlObjectValue(int r1, Vector.TLDeserializer<T> r2, boolean r3) {
        NativeByteBuffer r0 = byteBufferValue(r1);
        if (r0 != null) goto L12;
        return null;
    L12:
        T r12 = (T) r2.deserialize(r0, r0.readInt32(r3), r3);
        r0.reuse();
        return r12;
    L9:
        th = move-exception;
        r0.reuse();
        throw th;
    }
}
