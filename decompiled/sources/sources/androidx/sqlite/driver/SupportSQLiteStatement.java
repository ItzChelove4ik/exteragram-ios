package androidx.sqlite.driver;

import android.database.Cursor;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Arrays;
import java.util.Locale;
import kotlin.KotlinNothingValueException;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00132\u00020\u0001:\u0005\u0013\u0014\u0015\u0016\u0017B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u0082\u0001\u0004\u0018\u0019\u001a\u001b¨\u0006\u001c"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement;", "Landroidx/sqlite/SQLiteStatement;", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "sql", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/sqlite/db/SupportSQLiteDatabase;Ljava/lang/String;)V", "getDb", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "getSql", "()Ljava/lang/String;", "isClosed", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "setClosed", "(Z)V", "throwIfClosed", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "TransactionSQLiteStatement", "JournalModeSetStatement", "RowSQLiteStatement", "OtherSQLiteStatement", "Landroidx/sqlite/driver/SupportSQLiteStatement$JournalModeSetStatement;", "Landroidx/sqlite/driver/SupportSQLiteStatement$OtherSQLiteStatement;", "Landroidx/sqlite/driver/SupportSQLiteStatement$RowSQLiteStatement;", "Landroidx/sqlite/driver/SupportSQLiteStatement$TransactionSQLiteStatement;", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class SupportSQLiteStatement implements SQLiteStatement {
    public static final Companion Companion = null;
    private final SupportSQLiteDatabase db;
    private boolean isClosed;
    private final String sql;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\tH\u0002J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0002¨\u0006\u0018"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/sqlite/driver/SupportSQLiteStatement;", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "sql", _UrlKt.FRAGMENT_ENCODE_SET, "getTransactionOperation", "Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$TransactionOperation;", "prefix", "getSpecialOperation", "Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$SpecialOperation;", "isRowStatement", _UrlKt.FRAGMENT_ENCODE_SET, "getStatementPrefix", "getStatementPrefix$sqlite_framework", "getStatementPrefixIndex", _UrlKt.FRAGMENT_ENCODE_SET, "s", "TransactionOperation", "SpecialOperation", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0001\u0005¨\u0006\u0006"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$SpecialOperation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "JournalModeOperation", "Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$SpecialOperation$JournalModeOperation;", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class SpecialOperation {

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$SpecialOperation$JournalModeOperation;", "Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$SpecialOperation;", "<init>", "()V", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class JournalModeOperation extends SpecialOperation {
                public static final JournalModeOperation INSTANCE = null;

                static {
                    INSTANCE = new JournalModeOperation();
                }

                private JournalModeOperation() {
                    super(null);
                }
            }

            private SpecialOperation() {
            }

            public /* synthetic */ SpecialOperation(DefaultConstructorMarker r1) {
                this();
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$TransactionOperation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "END", "ROLLBACK", "BEGIN_EXCLUSIVE", "BEGIN_IMMEDIATE", "BEGIN_DEFERRED", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public enum TransactionOperation extends Enum<TransactionOperation> {
            private static final /* synthetic */ EnumEntries $ENTRIES = null;
            private static final /* synthetic */ TransactionOperation[] $VALUES = null;
            public static final TransactionOperation BEGIN_DEFERRED = null;
            public static final TransactionOperation BEGIN_EXCLUSIVE = null;
            public static final TransactionOperation BEGIN_IMMEDIATE = null;
            public static final TransactionOperation END = null;
            public static final TransactionOperation ROLLBACK = null;

            private static final /* synthetic */ TransactionOperation[] $values() {
                return new TransactionOperation[]{END, ROLLBACK, BEGIN_EXCLUSIVE, BEGIN_IMMEDIATE, BEGIN_DEFERRED};
            }

            static {
                END = new TransactionOperation("END", 0);
                ROLLBACK = new TransactionOperation("ROLLBACK", 1);
                BEGIN_EXCLUSIVE = new TransactionOperation("BEGIN_EXCLUSIVE", 2);
                BEGIN_IMMEDIATE = new TransactionOperation("BEGIN_IMMEDIATE", 3);
                BEGIN_DEFERRED = new TransactionOperation("BEGIN_DEFERRED", 4);
                TransactionOperation[] r0 = $values();
                $VALUES = r0;
                $ENTRIES = EnumEntriesKt.enumEntries(r0);
            }

            TransactionOperation(String r1, int r2) {
            }

            public static TransactionOperation valueOf(String r1) {
                return (TransactionOperation) Enum.valueOf(TransactionOperation.class, r1);
            }

            public static TransactionOperation[] values() {
                return (TransactionOperation[]) $VALUES.clone();
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final SpecialOperation getSpecialOperation(String r3, String r4) {
            if (Intrinsics.areEqual(r3, "PRA") == true) goto L5;
        L8:
            return null;
        L5:
            if (StringsKt.contains$default(StringsKt.substringAfter(r4.toLowerCase(Locale.ROOT), "journal_mode", _UrlKt.FRAGMENT_ENCODE_SET), "=", false, 2, null) == false) goto L8;
            return SpecialOperation.JournalModeOperation.INSTANCE;
        }

        private final int getStatementPrefixIndex(String r11) {
            int r10 = r11.length() - 2;
            if (r10 >= 0) goto L5;
            return -1;
        L5:
            int r1 = 0;
        L6:
            if (r1 >= r10) goto L34;
            char r2 = r11.charAt(r1);
            if (Intrinsics.compare(r2, 32) <= 0) goto L9;
            if (r2 == '-') goto L13;
            String r4 = r11;
            if (r2 != '/') goto L33;
            int r22 = r1 + 1;
            if (r4.charAt(r22) != '*') goto L33;
        L25:
            String r12 = r4;
            r22 = StringsKt.indexOf$default(r12, '*', r22 + 1, false, 4, null);
            r4 = r12;
            if (r22 < 0) goto L27;
            int r13 = r22 + 1;
            if (r13 >= r10) goto L32;
            if (r4.charAt(r13) != '/') goto L25;
        L32:
            r1 = r22 + 2;
        L19:
            r11 = r4;
            goto L6
        L27:
            return -1;
        L33:
            return r1;
        L13:
            if (r11.charAt(r1 + 1) != '-') goto L33;
            r4 = r11;
            int r112 = StringsKt.indexOf$default(r4, '\n', r1 + 2, false, 4, null);
            if (r112 < 0) goto L17;
            r1 = r112 + 1;
            goto L19
        L17:
            return -1;
        L9:
            r1 = r1 + 1;
            goto L6
        L34:
            return -1;
        }

        private final TransactionOperation getTransactionOperation(String r4, String r5) {
            switch(r4.hashCode()) {
                case 65636: goto L22;
                case 66913: goto L17;
                case 68795: goto L14;
                case 81327: goto L6;
                default: goto L23;
            };
        L23:
            return null;
        L6:
            if (r4.equals("ROL") == false) goto L23;
            if (StringsKt.contains$default(r5, " TO ", false, 2, null) == false) goto L12;
            return null;
        L12:
            return TransactionOperation.ROLLBACK;
        L14:
            if (r4.equals("END") == false) goto L23;
        L20:
            return TransactionOperation.END;
        L17:
            if (r4.equals("COM") == true) goto L20;
        L22:
            if (r4.equals("BEG") == false) goto L23;
            if (StringsKt.contains$default(r5, "EXCLUSIVE", false, 2, null) == false) goto L29;
            return TransactionOperation.BEGIN_EXCLUSIVE;
        L29:
            if (StringsKt.contains$default(r5, "IMMEDIATE", false, 2, null) == false) goto L33;
            return TransactionOperation.BEGIN_IMMEDIATE;
        L33:
            return TransactionOperation.BEGIN_DEFERRED;
        }

        private final boolean isRowStatement(String r2) {
            int r1 = r2.hashCode();
            if (r1 == 79487) goto L16;
            if (r1 == 81978) goto L13;
            if (r1 == 85954) goto L10;
            return false;
        L10:
            if (r2.equals("WIT") == false) goto L24;
            return true;
        L24:
            return false;
        L13:
            if (r2.equals("SEL") == false) goto L25;
            return true;
        L25:
            return false;
        L16:
            if (r2.equals("PRA") == true) goto L22;
            return false;
        L22:
            return true;
        }

        public final SupportSQLiteStatement create(SupportSQLiteDatabase r4, String r5) {
            String r0 = StringsKt.trim(r5).toString().toUpperCase(Locale.ROOT);
            String r1 = getStatementPrefix$sqlite_framework(r0);
            if (r1 == null) goto L5;
            TransactionOperation r2 = getTransactionOperation(r1, r0);
            if (r2 == null) goto L11;
            return new TransactionSQLiteStatement(r4, r5, r2);
        L11:
            if ((getSpecialOperation(r1, r0) instanceof SpecialOperation.JournalModeOperation) == false) goto L15;
            return new JournalModeSetStatement(r4, r5, new RowSQLiteStatement(r4, r5));
        L15:
            if (isRowStatement(r1) == false) goto L19;
            return new RowSQLiteStatement(r4, r5);
        L19:
            return new OtherSQLiteStatement(r4, r5);
        L5:
            return new OtherSQLiteStatement(r4, r5);
        }

        public final String getStatementPrefix$sqlite_framework(String r3) {
            int r2 = getStatementPrefixIndex(r3);
            if (r2 >= 0) goto L5;
            return null;
        L5:
            if (r2 <= r3.length()) goto L8;
            return null;
        L8:
            return r3.substring(r2, Math.min(r2 + 3, r3.length()));
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0018\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0001¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\"\u0010\u0012\u001a\u00020\u00112\b\b\u0001\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\"\u0010\u0015\u001a\u00020\u00112\b\b\u0001\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0014H\u0096\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\"\u0010\u0017\u001a\u00020\u00112\b\b\u0001\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0005H\u0096\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u00112\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u000f2\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001d\u001a\u00020\u00142\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010\u001f\u001a\u00020\n2\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010!\u001a\u00020\u00052\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b!\u0010\"J\u001a\u0010#\u001a\u00020\n2\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b#\u0010 J\u0010\u0010$\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010&\u001a\u00020\u00052\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0096\u0001¢\u0006\u0004\b&\u0010\"J\u0010\u0010'\u001a\u00020\u0011H\u0096\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0011H\u0096\u0001¢\u0006\u0004\b)\u0010(J\u0010\u0010*\u001a\u00020\u0011H\u0096\u0001¢\u0006\u0004\b*\u0010(R\u0014\u0010\u0007\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010+¨\u0006,"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$JournalModeSetStatement;", "Landroidx/sqlite/driver/SupportSQLiteStatement;", "Landroidx/sqlite/SQLiteStatement;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "delegate", "<init>", "(Landroidx/sqlite/db/SupportSQLiteDatabase;Ljava/lang/String;Landroidx/sqlite/driver/SupportSQLiteStatement;)V", _UrlKt.FRAGMENT_ENCODE_SET, "step", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "bindBlob", "(I[B)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindLong", "(IJ)V", "bindText", "(ILjava/lang/String;)V", "bindNull", "(I)V", "getBlob", "(I)[B", "getLong", "(I)J", "getBoolean", "(I)Z", "getText", "(I)Ljava/lang/String;", "isNull", "getColumnCount", "()I", "getColumnName", "reset", "()V", "clearBindings", "close", "Landroidx/sqlite/driver/SupportSQLiteStatement;", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class JournalModeSetStatement extends SupportSQLiteStatement implements SQLiteStatement {
        private final SupportSQLiteStatement delegate;

        public JournalModeSetStatement(SupportSQLiteDatabase r2, String r3, SupportSQLiteStatement r4) {
            super(r2, r3, null);
            this.delegate = r4;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindBlob(int r1, byte[] r2) {
            this.delegate.bindBlob(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindLong(int r1, long r2) {
            this.delegate.bindLong(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindNull(int r1) {
            this.delegate.bindNull(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindText(int r1, String r2) {
            this.delegate.bindText(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void clearBindings() {
            this.delegate.clearBindings();
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int r1) {
            return this.delegate.getBlob(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean getBoolean(int r1) {
            return this.delegate.getBoolean(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            return this.delegate.getColumnCount();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int r1) {
            return this.delegate.getColumnName(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int r1) {
            return this.delegate.getLong(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int r1) {
            return this.delegate.getText(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int r1) {
            return this.delegate.isNull(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
            this.delegate.reset();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            boolean r0 = this.delegate.step();
            if (StringsKt.equals(getText(0), "wal", true) == false) goto L6;
            getDb().enableWriteAheadLogging();
            return r0;
        L6:
            getDb().disableWriteAheadLogging();
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b!\u0010\u001bJ\u000f\u0010\"\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\fH\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\fH\u0016¢\u0006\u0004\b&\u0010%J\u000f\u0010'\u001a\u00020\fH\u0016¢\u0006\u0004\b'\u0010%R\u0018\u0010*\u001a\u00060(j\u0002`)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006,"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$OtherSQLiteStatement;", "Landroidx/sqlite/driver/SupportSQLiteStatement;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "<init>", "(Landroidx/sqlite/db/SupportSQLiteDatabase;Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "bindBlob", "(I[B)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindLong", "(IJ)V", "bindText", "(ILjava/lang/String;)V", "bindNull", "(I)V", "getBlob", "(I)[B", "getLong", "(I)J", "getText", "(I)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "isNull", "(I)Z", "getColumnCount", "()I", "getColumnName", "step", "()Z", "reset", "()V", "clearBindings", "close", "Landroidx/sqlite/db/SupportSQLiteStatement;", "Landroidx/sqlite/driver/SupportStatement;", "delegate", "Landroidx/sqlite/db/SupportSQLiteStatement;", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class OtherSQLiteStatement extends SupportSQLiteStatement {
        private final androidx.sqlite.db.SupportSQLiteStatement delegate;

        public OtherSQLiteStatement(SupportSQLiteDatabase r2, String r3) {
            super(r2, r3, null);
            this.delegate = r2.compileStatement(r3);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindBlob(int r1, byte[] r2) {
            throwIfClosed();
            this.delegate.bindBlob(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindLong(int r1, long r2) {
            throwIfClosed();
            this.delegate.bindLong(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindNull(int r1) {
            throwIfClosed();
            this.delegate.bindNull(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindText(int r1, String r2) {
            throwIfClosed();
            this.delegate.bindString(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void clearBindings() {
            throwIfClosed();
            this.delegate.clearBindings();
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
            setClosed(true);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            throwIfClosed();
            return 0;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
            throwIfClosed();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            throwIfClosed();
            this.delegate.execute();
            return false;
        }
    }

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\b\u0002\u0018\u0000 B2\u00020\u0001:\u0001BB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020'2\u0006\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\bH\u0016¢\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b,\u0010&J\u000f\u0010-\u001a\u00020'H\u0016¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u000bH\u0016¢\u0006\u0004\b/\u0010\u000fJ\u000f\u00100\u001a\u00020\u000bH\u0016¢\u0006\u0004\b0\u0010\u000fJ\u000f\u00101\u001a\u00020\u000bH\u0016¢\u0006\u0004\b1\u0010\u000fR\u0016\u00103\u001a\u0002028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00109\u001a\u0002088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u001e\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u001e\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010@\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010A¨\u0006C"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$RowSQLiteStatement;", "Landroidx/sqlite/driver/SupportSQLiteStatement;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "<init>", "(Landroidx/sqlite/db/SupportSQLiteDatabase;Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "columnType", "index", _UrlKt.FRAGMENT_ENCODE_SET, "ensureCapacity", "(II)V", "ensureCursor", "()V", "Landroid/database/Cursor;", "throwIfNoRow", "()Landroid/database/Cursor;", "c", "throwIfInvalidColumn", "(Landroid/database/Cursor;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "bindBlob", "(I[B)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindLong", "(IJ)V", "bindText", "(ILjava/lang/String;)V", "bindNull", "(I)V", "getBlob", "(I)[B", "getLong", "(I)J", "getText", "(I)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "isNull", "(I)Z", "getColumnCount", "()I", "getColumnName", "step", "()Z", "reset", "clearBindings", "close", _UrlKt.FRAGMENT_ENCODE_SET, "bindingTypes", "[I", _UrlKt.FRAGMENT_ENCODE_SET, "longBindings", "[J", _UrlKt.FRAGMENT_ENCODE_SET, "doubleBindings", "[D", _UrlKt.FRAGMENT_ENCODE_SET, "stringBindings", "[Ljava/lang/String;", "blobBindings", "[[B", "cursor", "Landroid/database/Cursor;", "Companion", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RowSQLiteStatement extends SupportSQLiteStatement {
        public static final Companion Companion = null;
        private int[] bindingTypes;
        private byte[][] blobBindings;
        private Cursor cursor;
        private double[] doubleBindings;
        private long[] longBindings;
        private String[] stringBindings;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$RowSQLiteStatement$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

        public RowSQLiteStatement(SupportSQLiteDatabase r2, String r3) {
            super(r2, r3, null);
            this.bindingTypes = new int[0];
            this.longBindings = new long[0];
            this.doubleBindings = new double[0];
            this.stringBindings = new String[0];
            this.blobBindings = new byte[0][];
        }

        public static final /* synthetic */ int[] access$getBindingTypes$p(RowSQLiteStatement r0) {
            return r0.bindingTypes;
        }

        public static final /* synthetic */ byte[][] access$getBlobBindings$p(RowSQLiteStatement r0) {
            return r0.blobBindings;
        }

        public static final /* synthetic */ double[] access$getDoubleBindings$p(RowSQLiteStatement r0) {
            return r0.doubleBindings;
        }

        public static final /* synthetic */ long[] access$getLongBindings$p(RowSQLiteStatement r0) {
            return r0.longBindings;
        }

        public static final /* synthetic */ String[] access$getStringBindings$p(RowSQLiteStatement r0) {
            return r0.stringBindings;
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

        private final void ensureCursor() {
            if (this.cursor != null) goto L6;
            this.cursor = getDb().query(new SupportSQLiteStatement$RowSQLiteStatement$ensureCursor$1(this));
            return;
        }

        private final void throwIfInvalidColumn(Cursor r1, int r2) {
            if (r2 >= 0) goto L4;
        L6:
            SQLite.throwSQLiteException(25, "column index out of range");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L4:
            if (r2 >= r1.getColumnCount()) goto L6;
        }

        private final Cursor throwIfNoRow() {
            Cursor r1 = this.cursor;
            if (r1 == null) goto L5;
            return r1;
        L5:
            SQLite.throwSQLiteException(21, "no row");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindBlob(int r3, byte[] r4) {
            throwIfClosed();
            ensureCapacity(4, r3);
            this.bindingTypes[r3] = 4;
            this.blobBindings[r3] = r4;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindLong(int r3, long r4) {
            throwIfClosed();
            ensureCapacity(1, r3);
            this.bindingTypes[r3] = 1;
            this.longBindings[r3] = r4;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindNull(int r2) {
            throwIfClosed();
            ensureCapacity(5, r2);
            this.bindingTypes[r2] = 5;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindText(int r3, String r4) {
            throwIfClosed();
            ensureCapacity(3, r3);
            this.bindingTypes[r3] = 3;
            this.stringBindings[r3] = r4;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void clearBindings() {
            throwIfClosed();
            this.bindingTypes = new int[0];
            this.longBindings = new long[0];
            this.doubleBindings = new double[0];
            this.stringBindings = new String[0];
            this.blobBindings = new byte[0][];
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            if (isClosed() == true) goto L5;
            clearBindings();
            reset();
        L5:
            setClosed(true);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int r2) {
            throwIfClosed();
            Cursor r0 = throwIfNoRow();
            throwIfInvalidColumn(r0, r2);
            return r0.getBlob(r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            throwIfClosed();
            ensureCursor();
            Cursor r0 = this.cursor;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getColumnCount();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int r2) {
            throwIfClosed();
            ensureCursor();
            Cursor r0 = this.cursor;
            if (r0 == null) goto L6;
            throwIfInvalidColumn(r0, r2);
            return r0.getColumnName(r2);
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int r2) {
            throwIfClosed();
            Cursor r0 = throwIfNoRow();
            throwIfInvalidColumn(r0, r2);
            return r0.getLong(r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int r2) {
            throwIfClosed();
            Cursor r0 = throwIfNoRow();
            throwIfInvalidColumn(r0, r2);
            return r0.getString(r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int r2) {
            throwIfClosed();
            Cursor r0 = throwIfNoRow();
            throwIfInvalidColumn(r0, r2);
            return r0.isNull(r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
            throwIfClosed();
            Cursor r0 = this.cursor;
            if (r0 == null) goto L5;
            r0.close();
        L5:
            this.cursor = null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            throwIfClosed();
            ensureCursor();
            Cursor r0 = this.cursor;
            if (r0 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return false;
        L5:
            return r0.moveToNext();
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\nH\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b#\u0010\u001dJ\u000f\u0010$\u001a\u00020\u001eH\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u000eH\u0016¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u000eH\u0016¢\u0006\u0004\b(\u0010'J\u000f\u0010)\u001a\u00020\u000eH\u0016¢\u0006\u0004\b)\u0010'R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b+\u0010,¨\u0006-"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteStatement$TransactionSQLiteStatement;", "Landroidx/sqlite/driver/SupportSQLiteStatement;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$TransactionOperation;", "operation", "<init>", "(Landroidx/sqlite/db/SupportSQLiteDatabase;Ljava/lang/String;Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$TransactionOperation;)V", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "bindBlob", "(I[B)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindLong", "(IJ)V", "bindText", "(ILjava/lang/String;)V", "bindNull", "(I)V", "getBlob", "(I)[B", "getLong", "(I)J", "getText", "(I)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "isNull", "(I)Z", "getColumnCount", "()I", "getColumnName", "step", "()Z", "reset", "()V", "clearBindings", "close", "Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$TransactionOperation;", "getOperation", "()Landroidx/sqlite/driver/SupportSQLiteStatement$Companion$TransactionOperation;", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TransactionSQLiteStatement extends SupportSQLiteStatement {
        private final Companion.TransactionOperation operation;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[Companion.TransactionOperation.values().length];
                r0[Companion.TransactionOperation.END.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
            L15:
                r0[Companion.TransactionOperation.ROLLBACK.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
            L23:
                r0[Companion.TransactionOperation.BEGIN_EXCLUSIVE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
            L17:
                r0[Companion.TransactionOperation.BEGIN_IMMEDIATE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
            L19:
                r0[Companion.TransactionOperation.BEGIN_DEFERRED.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
            L8:
                $EnumSwitchMapping$0 = r0;
            }
        }

        public TransactionSQLiteStatement(SupportSQLiteDatabase r2, String r3, Companion.TransactionOperation r4) {
            super(r2, r3, null);
            this.operation = r4;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindBlob(int r1, byte[] r2) {
            throwIfClosed();
            SQLite.throwSQLiteException(25, "column index out of range");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindLong(int r1, long r2) {
            throwIfClosed();
            SQLite.throwSQLiteException(25, "column index out of range");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindNull(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(25, "column index out of range");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindText(int r1, String r2) {
            throwIfClosed();
            SQLite.throwSQLiteException(25, "column index out of range");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void clearBindings() {
            throwIfClosed();
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            setClosed(true);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            throwIfClosed();
            return 0;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int r1) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
            throwIfClosed();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            Companion.TransactionOperation r0 = this.operation;
            int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
            if (r02 != 1) goto L5;
            getDb().setTransactionSuccessful();
            getDb().endTransaction();
            return false;
        L5:
            if (r02 != 2) goto L7;
            getDb().endTransaction();
            return false;
        L7:
            if (r02 != 3) goto L9;
            getDb().beginTransaction();
            return false;
        L9:
            if (r02 != 4) goto L11;
            getDb().beginTransactionNonExclusive();
            return false;
        L11:
            if (r02 != 5) goto L13;
            getDb().beginTransactionReadOnly();
            return false;
        L13:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return false;
        }
    }

    static {
        Companion = new Companion(null);
    }

    private SupportSQLiteStatement(SupportSQLiteDatabase r1, String r2) {
        this.db = r1;
        this.sql = r2;
    }

    public /* synthetic */ SupportSQLiteStatement(SupportSQLiteDatabase r1, String r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    public final SupportSQLiteDatabase getDb() {
        return this.db;
    }

    public final String getSql() {
        return this.sql;
    }

    public final boolean isClosed() {
        return this.isClosed;
    }

    public final void setClosed(boolean r1) {
        this.isClosed = r1;
    }

    public final void throwIfClosed() {
        if (this.isClosed == true) goto L5;
        return;
    L5:
        SQLite.throwSQLiteException(21, "statement is closed");
        InstantKt$$ExternalSyntheticBUOutline0.m();
    }
}
