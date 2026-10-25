package org.telegram.SQLite;

/* loaded from: classes.dex */
public class SQLiteException extends Exception {
    private static final long serialVersionUID = -2398298479089615621L;
    public final int errorCode;

    public SQLiteException() {
        this.errorCode = 0;
    }

    public SQLiteException(int r1, String r2) {
        super(r2);
        this.errorCode = r1;
    }

    public SQLiteException(String r2) {
        this(0, r2);
    }
}
