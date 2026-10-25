package androidx.room.util;

import androidx.room.util.TableInfo;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\u001a\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0000\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\bH\u0002\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\tH\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\rH\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\rH\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u000e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u000eH\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u000eH\u0000\u001a\u0014\u0010\u000f\u001a\u00020\b2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0000\u001a\u0010\u0010\u0012\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u0011H\u0002\u001a\u0010\u0010\u0013\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u0011H\u0002¨\u0006\u0014"}, d2 = {"equalsCommon", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/util/TableInfo;", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCodeCommon", _UrlKt.FRAGMENT_ENCODE_SET, "toStringCommon", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/util/TableInfo$Column;", "defaultValueEqualsCommon", "current", "containsSurroundingParenthesis", "Landroidx/room/util/TableInfo$ForeignKey;", "Landroidx/room/util/TableInfo$Index;", "formatString", "collection", _UrlKt.FRAGMENT_ENCODE_SET, "joinToStringMiddleWithIndent", "joinToStringEndWithIndent", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTableInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TableInfo.kt\nandroidx/room/util/TableInfoKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,395:1\n1056#2:396\n1056#2:397\n1188#3,3:398\n*S KotlinDebug\n*F\n+ 1 TableInfo.kt\nandroidx/room/util/TableInfoKt\n*L\n192#1:396\n194#1:397\n261#1:398,3\n*E\n"})
/* loaded from: classes4.dex */
public abstract class TableInfoKt {
    private static final boolean containsSurroundingParenthesis(String r8) {
        if (r8.length() != 0) goto L5;
        return false;
    L5:
        int r0 = 0;
        int r2 = 0;
        int r3 = 0;
    L7:
        if (r0 >= r8.length()) goto L23;
        char r4 = r8.charAt(r0);
        int r6 = r3 + 1;
        if (r3 != 0) goto L12;
        if (r4 == '(') goto L12;
        return false;
    L12:
        if (r4 != '(') goto L14;
        r2 = r2 + 1;
    L22:
        r0 = r0 + 1;
        r3 = r6;
        goto L7
    L14:
        if (r4 != ')') goto L22;
        r2 = r2 - 1;
        if (r2 != 0) goto L22;
        if (r3 == (r8.length() - 1)) goto L22;
        return false;
    L23:
        if (r2 != 0) goto L25;
        return true;
    L25:
        return false;
    }

    public static final boolean defaultValueEqualsCommon(String r2, String r3) {
        if (Intrinsics.areEqual(r2, r3) == false) goto L6;
        return true;
    L6:
        if (containsSurroundingParenthesis(r2) == true) goto L8;
        return false;
    L8:
        return Intrinsics.areEqual(StringsKt.trim(r2.substring(1, r2.length() - 1)).toString(), r3);
    }

    public static final boolean equalsCommon(TableInfo.Column r6, Object r7) {
        if (r6 != r7) goto L6;
        return true;
    L6:
        if ((r7 instanceof TableInfo.Column) == true) goto L8;
        return false;
    L8:
        TableInfo.Column r72 = (TableInfo.Column) r7;
        if (r6.isPrimaryKey() == r72.isPrimaryKey()) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(r6.name, r72.name) == true) goto L15;
        return false;
    L15:
        if (r6.notNull == r72.notNull) goto L17;
        return false;
    L17:
        String r1 = r6.defaultValue;
        String r3 = r72.defaultValue;
        if (r6.createdFrom != 1) goto L26;
        if (r72.createdFrom != 2) goto L26;
        if (r1 == null) goto L26;
        if (defaultValueEqualsCommon(r1, r3) == true) goto L26;
        return false;
    L26:
        if (r6.createdFrom == 2) goto L28;
    L33:
        int r4 = r6.createdFrom;
        if (r4 == 0) goto L44;
        if (r4 != r72.createdFrom) goto L44;
        if (r1 != null) goto L39;
        if (r3 == null) goto L44;
    L42:
        return false;
    L39:
        if (defaultValueEqualsCommon(r1, r3) == false) goto L42;
    L44:
        if (r6.affinity != r72.affinity) goto L46;
        return true;
    L46:
        return false;
    L28:
        if (r72.createdFrom != 1) goto L33;
        if (r3 == null) goto L33;
        if (defaultValueEqualsCommon(r3, r1) == true) goto L33;
        return false;
    }

    public static final boolean equalsCommon(TableInfo.ForeignKey r3, Object r4) {
        if (r3 != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof TableInfo.ForeignKey) == true) goto L8;
        return false;
    L8:
        TableInfo.ForeignKey r42 = (TableInfo.ForeignKey) r4;
        if (Intrinsics.areEqual(r3.referenceTable, r42.referenceTable) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(r3.onDelete, r42.onDelete) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(r3.onUpdate, r42.onUpdate) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(r3.columnNames, r42.columnNames) == true) goto L21;
        return false;
    L21:
        return Intrinsics.areEqual(r3.referenceColumnNames, r42.referenceColumnNames);
    }

    public static final boolean equalsCommon(TableInfo.Index r5, Object r6) {
        if (r5 != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof TableInfo.Index) == true) goto L8;
        return false;
    L8:
        TableInfo.Index r62 = (TableInfo.Index) r6;
        if (r5.unique == r62.unique) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(r5.columns, r62.columns) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(r5.orders, r62.orders) == true) goto L18;
        return false;
    L18:
        if (StringsKt.startsWith$default(r5.name, "index_", false, 2, null) == false) goto L22;
        return StringsKt.startsWith$default(r62.name, "index_", false, 2, null);
    L22:
        return Intrinsics.areEqual(r5.name, r62.name);
    }

    public static final boolean equalsCommon(TableInfo r4, Object r5) {
        if (r4 != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof TableInfo) == true) goto L8;
        return false;
    L8:
        TableInfo r52 = (TableInfo) r5;
        if (Intrinsics.areEqual(r4.name, r52.name) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(r4.columns, r52.columns) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(r4.foreignKeys, r52.foreignKeys) == true) goto L17;
        return false;
    L17:
        Set<TableInfo.Index> r42 = r4.indices;
        if (r42 == null) goto L24;
        Set<TableInfo.Index> r53 = r52.indices;
        if (r53 == null) goto L24;
        return Intrinsics.areEqual(r42, r53);
    L24:
        return true;
    }

    public static final String formatString(Collection<?> r10) {
        if (r10.isEmpty() == false) goto L5;
        return " }";
    L5:
        return StringsKt.prependIndent$default(CollectionsKt.joinToString$default(r10, ",\n", "\n", "\n", 0, null, null, 56, null), null, 1, null) + "},";
    }

    public static final int hashCodeCommon(TableInfo.Column r2) {
        int r0 = ((r2.name.hashCode() * 31) + r2.affinity) * 31;
        if (r2.notNull == false) goto L5;
        int r1 = 1231;
    L7:
        return ((r0 + r1) * 31) + r2.primaryKeyPosition;
    L5:
        r1 = 1237;
        goto L7
    }

    public static final int hashCodeCommon(TableInfo.ForeignKey r2) {
        return (((((((r2.referenceTable.hashCode() * 31) + r2.onDelete.hashCode()) * 31) + r2.onUpdate.hashCode()) * 31) + r2.columnNames.hashCode()) * 31) + r2.referenceColumnNames.hashCode();
    }

    public static final int hashCodeCommon(TableInfo.Index r5) {
        if (StringsKt.startsWith$default(r5.name, "index_", false, 2, null) == false) goto L5;
        int r0 = -1184239155;
    L7:
        return (((((r0 * 31) + (r5.unique ? 1 : 0)) * 31) + r5.columns.hashCode()) * 31) + r5.orders.hashCode();
    L5:
        r0 = r5.name.hashCode();
        goto L7
    }

    public static final int hashCodeCommon(TableInfo r2) {
        return (((r2.name.hashCode() * 31) + r2.columns.hashCode()) * 31) + r2.foreignKeys.hashCode();
    }

    private static final String joinToStringEndWithIndent(Collection<?> r10) {
        return StringsKt.prependIndent$default(CollectionsKt.joinToString$default(r10, ",", null, null, 0, null, null, 62, null), null, 1, null) + StringsKt.prependIndent$default(" }", null, 1, null);
    }

    private static final String joinToStringMiddleWithIndent(Collection<?> r10) {
        return StringsKt.prependIndent$default(CollectionsKt.joinToString$default(r10, ",", null, null, 0, null, null, 62, null), null, 1, null) + StringsKt.prependIndent$default("},", null, 1, null);
    }

    public static final String toStringCommon(TableInfo.Column r2) {
        StringBuilder r0 = new StringBuilder("\n            |Column {\n            |   name = '");
        r0.append(r2.name);
        r0.append("',\n            |   type = '");
        r0.append(r2.type);
        r0.append("',\n            |   affinity = '");
        r0.append(r2.affinity);
        r0.append("',\n            |   notNull = '");
        r0.append(r2.notNull);
        r0.append("',\n            |   primaryKeyPosition = '");
        r0.append(r2.primaryKeyPosition);
        r0.append("',\n            |   defaultValue = '");
        String r22 = r2.defaultValue;
        if (r22 != null) goto L5;
        r22 = "undefined";
    L5:
        r0.append(r22);
        r0.append("'\n            |}\n        ");
        return StringsKt.prependIndent$default(StringsKt.trimMargin$default(r0.toString(), null, 1, null), null, 1, null);
    }

    public static final String toStringCommon(TableInfo.ForeignKey r2) {
        return StringsKt.prependIndent$default(StringsKt.trimMargin$default("\n            |ForeignKey {\n            |   referenceTable = '" + r2.referenceTable + "',\n            |   onDelete = '" + r2.onDelete + "',\n            |   onUpdate = '" + r2.onUpdate + "',\n            |   columnNames = {" + joinToStringMiddleWithIndent(CollectionsKt.sorted(r2.columnNames)) + "\n            |   referenceColumnNames = {" + joinToStringEndWithIndent(CollectionsKt.sorted(r2.referenceColumnNames)) + "\n            |}\n        ", null, 1, null), null, 1, null);
    }

    public static final String toStringCommon(TableInfo.Index r2) {
        return StringsKt.prependIndent$default(StringsKt.trimMargin$default("\n            |Index {\n            |   name = '" + r2.name + "',\n            |   unique = '" + r2.unique + "',\n            |   columns = {" + joinToStringMiddleWithIndent(r2.columns) + "\n            |   orders = {" + joinToStringEndWithIndent(r2.orders) + "\n            |}\n        ", null, 1, null), null, 1, null);
    }

    public static final String toStringCommon(TableInfo r3) {
        StringBuilder r0 = new StringBuilder("\n            |TableInfo {\n            |    name = '");
        r0.append(r3.name);
        r0.append("',\n            |    columns = {");
        r0.append(formatString(CollectionsKt.sortedWith(r3.columns.values(), new TableInfoKt$toStringCommon$$inlined$sortedBy$1())));
        r0.append("\n            |    foreignKeys = {");
        r0.append(formatString(r3.foreignKeys));
        r0.append("\n            |    indices = {");
        Set<TableInfo.Index> r32 = r3.indices;
        if (r32 == null) goto L6;
        List r33 = CollectionsKt.sortedWith(r32, new TableInfoKt$toStringCommon$$inlined$sortedBy$2());
        if (r33 == null) goto L6;
    L7:
        r0.append(formatString(r33));
        r0.append("\n            |}\n        ");
        return StringsKt.trimMargin$default(r0.toString(), null, 1, null);
    L6:
        r33 = CollectionsKt.emptyList();
        goto L7
    }
}
