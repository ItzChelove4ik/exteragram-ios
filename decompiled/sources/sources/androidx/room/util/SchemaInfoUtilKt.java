package androidx.room.util;

import androidx.room.util.TableInfo;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a\u0019\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\t\u0010\n\u001a%\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a+\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u001a\u0010\u000e\u001a)\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0004\b\u001e\u0010\u001f\"\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00000 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006#"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "type", _UrlKt.FRAGMENT_ENCODE_SET, "findAffinity", "(Ljava/lang/String;)I", "Landroidx/sqlite/SQLiteConnection;", "connection", "tableName", "Landroidx/room/util/TableInfo;", "readTableInfo", "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/String;)Landroidx/room/util/TableInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/util/TableInfo$ForeignKey;", "readForeignKeys", "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/String;)Ljava/util/Set;", "Landroidx/sqlite/SQLiteStatement;", "stmt", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/util/ForeignKeyWithSequence;", "readForeignKeyFieldMappings", "(Landroidx/sqlite/SQLiteStatement;)Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/util/TableInfo$Column;", "readColumns", "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/String;)Ljava/util/Map;", "Landroidx/room/util/TableInfo$Index;", "readIndices", "name", _UrlKt.FRAGMENT_ENCODE_SET, "unique", "readIndex", "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/String;Z)Landroidx/room/util/TableInfo$Index;", _UrlKt.FRAGMENT_ENCODE_SET, "FTS_OPTIONS", "[Ljava/lang/String;", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSchemaInfoUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SchemaInfoUtil.kt\nandroidx/room/util/SchemaInfoUtilKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 4 Strings.kt\nkotlin/text/StringsKt__StringsKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,378:1\n774#2:379\n865#2,2:380\n1869#2,2:382\n1056#2:384\n1563#2:385\n1634#2,3:386\n1056#2:389\n1563#2:390\n1634#2,3:391\n774#2:420\n865#2:421\n866#2:424\n1188#3,2:394\n1190#3:419\n108#4:396\n80#4,22:397\n12637#5,2:422\n*S KotlinDebug\n*F\n+ 1 SchemaInfoUtil.kt\nandroidx/room/util/SchemaInfoUtilKt\n*L\n94#1:379\n94#1:380,2\n95#1:382,2\n256#1:384\n256#1:385\n256#1:386,3\n257#1:389\n257#1:390\n257#1:391,3\n360#1:420\n360#1:421\n360#1:424\n328#1:394,2\n328#1:419\n348#1:396\n348#1:397,22\n360#1:422,2\n*E\n"})
/* loaded from: classes4.dex */
public abstract class SchemaInfoUtilKt {
    private static final String[] FTS_OPTIONS = null;

    static {
        FTS_OPTIONS = new String[]{"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    }

    public static final int findAffinity(String r5) {
        if (r5 != null) goto L5;
        return 5;
    L5:
        String r52 = r5.toUpperCase(Locale.ROOT);
        if (StringsKt.contains$default(r52, "INT", false, 2, null) == false) goto L10;
        return 3;
    L10:
        if (StringsKt.contains$default(r52, "CHAR", false, 2, null) == false) goto L12;
    L30:
        return 2;
    L12:
        if (StringsKt.contains$default(r52, "CLOB", false, 2, null) == true) goto L30;
        if (StringsKt.contains$default(r52, "TEXT", false, 2, null) == true) goto L30;
        if (StringsKt.contains$default(r52, "BLOB", false, 2, null) == false) goto L20;
        return 5;
    L20:
        if (StringsKt.contains$default(r52, "REAL", false, 2, null) == false) goto L22;
        return 4;
    L22:
        if (StringsKt.contains$default(r52, "FLOA", false, 2, null) == false) goto L24;
        return 4;
    L24:
        if (StringsKt.contains$default(r52, "DOUB", false, 2, null) == true) goto L32;
        return 1;
    L32:
        return 4;
    }

    private static final Map<String, TableInfo.Column> readColumns(SQLiteConnection r13, String r14) {
        SQLiteStatement r132 = r13.prepare("PRAGMA table_info(`" + r14 + "`)");
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        AutoCloseableKt.closeFinally(r132, th);
        throw th;
    L4:
        if (r132.step() == true) goto L10;
        Map<String, TableInfo.Column> r142 = MapsKt.emptyMap();     // Catch: Throwable -> L8
        AutoCloseableKt.closeFinally(r132, null);
        return r142;
    L10:
        int r143 = SQLiteStatementUtil.columnIndexOf(r132, "name");     // Catch: Throwable -> L8
        int r1 = SQLiteStatementUtil.columnIndexOf(r132, "type");     // Catch: Throwable -> L8
        int r2 = SQLiteStatementUtil.columnIndexOf(r132, "notnull");     // Catch: Throwable -> L8
        int r3 = SQLiteStatementUtil.columnIndexOf(r132, "pk");     // Catch: Throwable -> L8
        int r4 = SQLiteStatementUtil.columnIndexOf(r132, "dflt_value");     // Catch: Throwable -> L8
        Map r5 = MapsKt.createMapBuilder();     // Catch: Throwable -> L8
    L11:
        String r7 = r132.getText(r143);     // Catch: Throwable -> L8
        String r8 = r132.getText(r1);     // Catch: Throwable -> L8
        if (r132.getLong(r2) == 0) goto L15;
        boolean r6 = true;
    L14:
        boolean r9 = r6;
        int r10 = (int) r132.getLong(r3);     // Catch: Throwable -> L8
        if (r132.isNull(r4) == false) goto L19;
        String r11 = null;
    L20:
        r5.put(r7, new TableInfo.Column(r7, r8, r9, r10, r11, 2));     // Catch: Throwable -> L8
        if (r132.step() == true) goto L11;
        Map<String, TableInfo.Column> r144 = MapsKt.build(r5);     // Catch: Throwable -> L8
        AutoCloseableKt.closeFinally(r132, null);
        return r144;
    L19:
        r11 = r132.getText(r4);     // Catch: Throwable -> L8
        goto L20
    L15:
        r6 = false;
        goto L14
    }

    private static final List<ForeignKeyWithSequence> readForeignKeyFieldMappings(SQLiteStatement r10) {
        int r0 = SQLiteStatementUtil.columnIndexOf(r10, "id");
        int r1 = SQLiteStatementUtil.columnIndexOf(r10, "seq");
        int r2 = SQLiteStatementUtil.columnIndexOf(r10, "from");
        int r3 = SQLiteStatementUtil.columnIndexOf(r10, "to");
        List r4 = CollectionsKt.createListBuilder();
    L4:
        if (r10.step() == false) goto L7;
        r4.add(new ForeignKeyWithSequence((int) r10.getLong(r0), (int) r10.getLong(r1), r10.getText(r2), r10.getText(r3)));
        goto L4
    L7:
        return CollectionsKt.sorted(CollectionsKt.build(r4));
    }

    private static final Set<TableInfo.ForeignKey> readForeignKeys(SQLiteConnection r13, String r14) {
        SQLiteStatement r132 = r13.prepare("PRAGMA foreign_key_list(`" + r14 + "`)");
        int r142 = SQLiteStatementUtil.columnIndexOf(r132, "id");     // Catch: Throwable -> L15
        int r0 = SQLiteStatementUtil.columnIndexOf(r132, "seq");     // Catch: Throwable -> L15
        int r1 = SQLiteStatementUtil.columnIndexOf(r132, "table");     // Catch: Throwable -> L15
        int r2 = SQLiteStatementUtil.columnIndexOf(r132, "on_delete");     // Catch: Throwable -> L15
        int r3 = SQLiteStatementUtil.columnIndexOf(r132, "on_update");     // Catch: Throwable -> L15
        List<ForeignKeyWithSequence> r4 = readForeignKeyFieldMappings(r132);     // Catch: Throwable -> L15
        r132.reset();     // Catch: Throwable -> L15
        Set r5 = SetsKt.createSetBuilder();     // Catch: Throwable -> L15
    L5:
        if (r132.step() == false) goto L21;
        if (r132.getLong(r0) != 0) goto L5;
        int r6 = (int) r132.getLong(r142);     // Catch: Throwable -> L15
        ArrayList r11 = new ArrayList();     // Catch: Throwable -> L15
        ArrayList r12 = new ArrayList();     // Catch: Throwable -> L15
        ArrayList r8 = new ArrayList();     // Catch: Throwable -> L15
        Iterator<T> r7 = r4.iterator();     // Catch: Throwable -> L15
    L11:
        if (r7.hasNext() == false) goto L17;
        Object r9 = r7.next();     // Catch: Throwable -> L15
        if (((ForeignKeyWithSequence) r9).getId() != r6) goto L11;
        r8.add(r9);     // Catch: Throwable -> L15
        goto L11
    L17:
        int r62 = r8.size();     // Catch: Throwable -> L15
        int r72 = 0;
    L18:
        if (r72 >= r62) goto L20;
        Object r92 = r8.get(r72);     // Catch: Throwable -> L15
        r72 = r72 + 1;     // Catch: Throwable -> L15
        ForeignKeyWithSequence r93 = (ForeignKeyWithSequence) r92;     // Catch: Throwable -> L15
        r11.add(r93.getFrom());     // Catch: Throwable -> L15
        r12.add(r93.getTo());     // Catch: Throwable -> L15
        goto L18
    L20:
        r5.add(new TableInfo.ForeignKey(r132.getText(r1), r132.getText(r2), r132.getText(r3), r11, r12));     // Catch: Throwable -> L15
        goto L5
    L21:
        Set<TableInfo.ForeignKey> r143 = SetsKt.build(r5);     // Catch: Throwable -> L15
        AutoCloseableKt.closeFinally(r132, null);
        return r143;
    L15:
        th = move-exception;
        throw th;     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        AutoCloseableKt.closeFinally(r132, th);
        throw th;
    }

    private static final TableInfo.Index readIndex(SQLiteConnection r13, String r14, boolean r15) {
        SQLiteStatement r132 = r13.prepare("PRAGMA index_xinfo(`" + r14 + "`)");
        int r0 = SQLiteStatementUtil.columnIndexOf(r132, "seqno");     // Catch: Throwable -> L18
        int r1 = SQLiteStatementUtil.columnIndexOf(r132, "cid");     // Catch: Throwable -> L18
        int r2 = SQLiteStatementUtil.columnIndexOf(r132, "name");     // Catch: Throwable -> L18
        int r3 = SQLiteStatementUtil.columnIndexOf(r132, "desc");     // Catch: Throwable -> L18
        if (r0 == (-1)) goto L33;
        if (r1 == (-1)) goto L33;
        if (r2 == (-1)) goto L33;
        if (r3 == (-1)) goto L33;
        LinkedHashMap r4 = new LinkedHashMap();     // Catch: Throwable -> L18
        LinkedHashMap r6 = new LinkedHashMap();     // Catch: Throwable -> L18
    L11:
        if (r132.step() == false) goto L22;
        if (((int) r132.getLong(r1)) < 0) goto L11;
        int r7 = (int) r132.getLong(r0);     // Catch: Throwable -> L18
        String r8 = r132.getText(r2);     // Catch: Throwable -> L18
        if (r132.getLong(r3) <= 0) goto L20;
        String r9 = "DESC";
    L21:
        r4.put(Integer.valueOf(r7), r8);     // Catch: Throwable -> L18
        r6.put(Integer.valueOf(r7), r9);     // Catch: Throwable -> L18
        goto L11
    L20:
        r9 = "ASC";
        goto L21
    L22:
        List r02 = CollectionsKt.sortedWith(r4.entrySet(), new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1());     // Catch: Throwable -> L18
        ArrayList r12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r02, 10));     // Catch: Throwable -> L18
        Iterator r03 = r02.iterator();     // Catch: Throwable -> L18
    L24:
        if (r03.hasNext() == false) goto L26;
        r12.add((String) ((Map.Entry) r03.next()).getValue());     // Catch: Throwable -> L18
        goto L24
    L26:
        List r04 = CollectionsKt.toList(r12);     // Catch: Throwable -> L18
        List r16 = CollectionsKt.sortedWith(r6.entrySet(), new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$2());     // Catch: Throwable -> L18
        ArrayList r32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r16, 10));     // Catch: Throwable -> L18
        Iterator r17 = r16.iterator();     // Catch: Throwable -> L18
    L28:
        if (r17.hasNext() == false) goto L30;
        r32.add((String) ((Map.Entry) r17.next()).getValue());     // Catch: Throwable -> L18
        goto L28
    L30:
        TableInfo.Index r22 = new TableInfo.Index(r14, r15, r04, CollectionsKt.toList(r32));     // Catch: Throwable -> L18
        AutoCloseableKt.closeFinally(r132, null);
        return r22;
    L33:
        AutoCloseableKt.closeFinally(r132, null);
        return null;
    L18:
        th = move-exception;
        throw th;     // Catch: Throwable -> L36
    L36:
        th = move-exception;
        AutoCloseableKt.closeFinally(r132, th);
        throw th;
    }

    private static final Set<TableInfo.Index> readIndices(SQLiteConnection r10, String r11) {
        SQLiteStatement r112 = r10.prepare("PRAGMA index_list(`" + r11 + "`)");
        int r0 = SQLiteStatementUtil.columnIndexOf(r112, "name");     // Catch: Throwable -> L23
        int r1 = SQLiteStatementUtil.columnIndexOf(r112, "origin");     // Catch: Throwable -> L23
        int r2 = SQLiteStatementUtil.columnIndexOf(r112, "unique");     // Catch: Throwable -> L23
        if (r0 == (-1)) goto L28;
        if (r1 == (-1)) goto L28;
        if (r2 == (-1)) goto L28;
        Set r3 = SetsKt.createSetBuilder();     // Catch: Throwable -> L23
    L10:
        if (r112.step() == false) goto L25;
        if (Intrinsics.areEqual("c", r112.getText(r1)) == false) goto L10;
        String r5 = r112.getText(r0);     // Catch: Throwable -> L23
        if (r112.getLong(r2) != 1) goto L17;
        boolean r6 = true;
    L18:
        TableInfo.Index r52 = readIndex(r10, r5, r6);     // Catch: Throwable -> L23
        if (r52 == null) goto L20;
        r3.add(r52);     // Catch: Throwable -> L23
        goto L10
    L20:
        AutoCloseableKt.closeFinally(r112, null);
        return null;
    L17:
        r6 = false;
        goto L18
    L25:
        Set<TableInfo.Index> r102 = SetsKt.build(r3);     // Catch: Throwable -> L23
        AutoCloseableKt.closeFinally(r112, null);
        return r102;
    L28:
        AutoCloseableKt.closeFinally(r112, null);
        return null;
    L23:
        th = move-exception;
        throw th;     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        AutoCloseableKt.closeFinally(r112, th);
        throw th;
    }

    public static final TableInfo readTableInfo(SQLiteConnection r3, String r4) {
        return new TableInfo(r4, readColumns(r3, r4), readForeignKeys(r3, r4), readIndices(r3, r4));
    }
}
