package androidx.sqlite.db;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000eR \u0010\u0006\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Landroidx/sqlite/db/SimpleSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteQuery;", _UrlKt.FRAGMENT_ENCODE_SET, "query", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "bindArgs", "<init>", "(Ljava/lang/String;[Ljava/lang/Object;)V", "Landroidx/sqlite/db/SupportSQLiteProgram;", "statement", _UrlKt.FRAGMENT_ENCODE_SET, "bindTo", "(Landroidx/sqlite/db/SupportSQLiteProgram;)V", "Ljava/lang/String;", "[Ljava/lang/Object;", "getSql", "()Ljava/lang/String;", "sql", "Companion", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    public static final Companion Companion = null;
    private final Object[] bindArgs;
    private final String query;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\nJ\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002¨\u0006\u000e"}, d2 = {"Landroidx/sqlite/db/SimpleSQLiteQuery$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/db/SupportSQLiteProgram;", "bindArgs", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroidx/sqlite/db/SupportSQLiteProgram;[Ljava/lang/Object;)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "arg", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final void bind(SupportSQLiteProgram r3, int r4, Object r5) {
            if (r5 != null) goto L6;
            r3.bindNull(r4);
            return;
        L6:
            if ((r5 instanceof byte[]) == false) goto L10;
            r3.bindBlob(r4, (byte[]) r5);
            return;
        L10:
            if ((r5 instanceof Float) == false) goto L14;
            r3.bindDouble(r4, ((Number) r5).floatValue());
            return;
        L14:
            if ((r5 instanceof Double) == false) goto L18;
            r3.bindDouble(r4, ((Number) r5).doubleValue());
            return;
        L18:
            if ((r5 instanceof Long) == false) goto L22;
            r3.bindLong(r4, ((Number) r5).longValue());
            return;
        L22:
            if ((r5 instanceof Integer) == false) goto L26;
            r3.bindLong(r4, ((Number) r5).intValue());
            return;
        L26:
            if ((r5 instanceof Short) == false) goto L30;
            r3.bindLong(r4, ((Number) r5).shortValue());
            return;
        L30:
            if ((r5 instanceof Byte) == false) goto L34;
            r3.bindLong(r4, ((Number) r5).byteValue());
            return;
        L34:
            if ((r5 instanceof String) == false) goto L38;
            r3.bindString(r4, (String) r5);
            return;
        L38:
            if ((r5 instanceof Boolean) == false) goto L46;
            if (((Boolean) r5).booleanValue() == false) goto L42;
            long r0 = 1;
        L43:
            r3.bindLong(r4, r0);
            return;
        L42:
            r0 = 0;
            goto L43
        L46:
            throw new IllegalArgumentException("Cannot bind " + r5 + " at index " + r4 + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
        }

        @JvmStatic
        public final void bind(SupportSQLiteProgram r4, Object[] r5) {
            if (r5 == null) goto L7;
            int r0 = r5.length;
            int r1 = 0;
        L5:
            if (r1 >= r0) goto L9;
            Object r2 = r5[r1];
            r1 = r1 + 1;
            bind(r4, r1, r2);
            goto L5
        L9:
            return;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public SimpleSQLiteQuery(String r1, Object[] r2) {
        this.query = r1;
        this.bindArgs = r2;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram r2) {
        Companion.bind(r2, this.bindArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.query;
    }
}
