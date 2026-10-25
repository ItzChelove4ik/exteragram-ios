package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.datastore.preferences.protobuf.FieldSet.FieldDescriptorLite;
import androidx.datastore.preferences.protobuf.WireFormat;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class FieldSet<T extends FieldDescriptorLite<T>> {
    private static final FieldSet<?> DEFAULT_INSTANCE = null;
    private final SmallSortedMap<T, Object> fields;
    private boolean hasLazyField;
    private boolean isImmutable;

    /* renamed from: androidx.datastore.preferences.protobuf.FieldSet$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = null;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$JavaType = null;

        static {
            int[] r0 = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0;
            r0[WireFormat.FieldType.DOUBLE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L40
        L81:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L41
        L97:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;     // Catch: NoSuchFieldError -> L42
        L105:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;     // Catch: NoSuchFieldError -> L43
        L109:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;     // Catch: NoSuchFieldError -> L44
        L69:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;     // Catch: NoSuchFieldError -> L45
        L71:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 7;     // Catch: NoSuchFieldError -> L46
        L89:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 8;     // Catch: NoSuchFieldError -> L47
        L91:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 9;     // Catch: NoSuchFieldError -> L48
        L111:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 10;     // Catch: NoSuchFieldError -> L49
        L115:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 11;     // Catch: NoSuchFieldError -> L50
        L73:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 12;     // Catch: NoSuchFieldError -> L51
        L79:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 13;     // Catch: NoSuchFieldError -> L52
        L95:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 14;     // Catch: NoSuchFieldError -> L53
        L101:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 15;     // Catch: NoSuchFieldError -> L54
        L119:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 16;     // Catch: NoSuchFieldError -> L55
        L67:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 17;     // Catch: NoSuchFieldError -> L56
        L83:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 18;     // Catch: NoSuchFieldError -> L57
        L29:
            int[] r9 = new int[WireFormat.JavaType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$JavaType = r9;
            r9[WireFormat.JavaType.INT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L58
        L103:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.LONG.ordinal()] = 2;     // Catch: NoSuchFieldError -> L59
        L107:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.FLOAT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L60
        L117:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.DOUBLE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L61
        L75:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.BOOLEAN.ordinal()] = 5;     // Catch: NoSuchFieldError -> L62
        L85:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.STRING.ordinal()] = 6;     // Catch: NoSuchFieldError -> L63
        L87:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.BYTE_STRING.ordinal()] = 7;     // Catch: NoSuchFieldError -> L64
        L93:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.ENUM.ordinal()] = 8;     // Catch: NoSuchFieldError -> L65
        L113:
            $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.MESSAGE.ordinal()] = 9;     // Catch: NoSuchFieldError -> L66
            return;
        }
    }

    /* loaded from: classes3.dex */
    public interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
        WireFormat.FieldType getLiteType();

        int getNumber();

        boolean isPacked();

        boolean isRepeated();
    }

    static {
        DEFAULT_INSTANCE = new FieldSet(true);
    }

    private FieldSet() {
        this.fields = SmallSortedMap.newFieldMap();
    }

    private FieldSet(SmallSortedMap<T, Object> r1) {
        this.fields = r1;
        makeImmutable();
    }

    private FieldSet(boolean r1) {
        this(SmallSortedMap.newFieldMap());
        makeImmutable();
    }

    public static int computeElementSize(WireFormat.FieldType r1, int r2, Object r3) {
        int r22 = CodedOutputStream.computeTagSize(r2);
        if (r1 != WireFormat.FieldType.GROUP) goto L6;
        r22 = r22 * 2;
    L6:
        return r22 + computeElementSizeNoTag(r1, r3);
    }

    public static int computeElementSizeNoTag(WireFormat.FieldType r1, Object r2) {
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r1.ordinal()]) {
            case 1: goto L49;
            case 2: goto L47;
            case 3: goto L45;
            case 4: goto L43;
            case 5: goto L41;
            case 6: goto L39;
            case 7: goto L37;
            case 8: goto L35;
            case 9: goto L33;
            case 10: goto L31;
            case 11: goto L25;
            case 12: goto L19;
            case 13: goto L17;
            case 14: goto L15;
            case 15: goto L13;
            case 16: goto L11;
            case 17: goto L9;
            case 18: goto L7;
            default: goto L4;
        };
    L4:
        GlShader$$ExternalSyntheticBUOutline1.m("There is no way to get here, but the compiler thinks otherwise.");
        return 0;
    L7:
        return CodedOutputStream.computeEnumSizeNoTag(((Integer) r2).intValue());
    L9:
        return CodedOutputStream.computeSInt64SizeNoTag(((Long) r2).longValue());
    L11:
        return CodedOutputStream.computeSInt32SizeNoTag(((Integer) r2).intValue());
    L13:
        return CodedOutputStream.computeSFixed64SizeNoTag(((Long) r2).longValue());
    L15:
        return CodedOutputStream.computeSFixed32SizeNoTag(((Integer) r2).intValue());
    L17:
        return CodedOutputStream.computeUInt32SizeNoTag(((Integer) r2).intValue());
    L19:
        if ((r2 instanceof ByteString) == false) goto L23;
        return CodedOutputStream.computeBytesSizeNoTag((ByteString) r2);
    L23:
        return CodedOutputStream.computeByteArraySizeNoTag((byte[]) r2);
    L25:
        if ((r2 instanceof ByteString) == false) goto L29;
        return CodedOutputStream.computeBytesSizeNoTag((ByteString) r2);
    L29:
        return CodedOutputStream.computeStringSizeNoTag((String) r2);
    L31:
        return CodedOutputStream.computeMessageSizeNoTag((MessageLite) r2);
    L33:
        return CodedOutputStream.computeGroupSizeNoTag((MessageLite) r2);
    L35:
        return CodedOutputStream.computeBoolSizeNoTag(((Boolean) r2).booleanValue());
    L37:
        return CodedOutputStream.computeFixed32SizeNoTag(((Integer) r2).intValue());
    L39:
        return CodedOutputStream.computeFixed64SizeNoTag(((Long) r2).longValue());
    L41:
        return CodedOutputStream.computeInt32SizeNoTag(((Integer) r2).intValue());
    L43:
        return CodedOutputStream.computeUInt64SizeNoTag(((Long) r2).longValue());
    L45:
        return CodedOutputStream.computeInt64SizeNoTag(((Long) r2).longValue());
    L47:
        return CodedOutputStream.computeFloatSizeNoTag(((Float) r2).floatValue());
    L49:
        return CodedOutputStream.computeDoubleSizeNoTag(((Double) r2).doubleValue());
    }

    public static int computeFieldSize(FieldDescriptorLite<?> r5, Object r6) {
        WireFormat.FieldType r0 = r5.getLiteType();
        int r1 = r5.getNumber();
        if (r5.isRepeated() == false) goto L19;
        List r62 = (List) r6;
        int r2 = r62.size();
        int r3 = 0;
        if (r5.isPacked() == true) goto L7;
        int r52 = 0;
    L15:
        if (r3 >= r2) goto L17;
        r52 = r52 + computeElementSize(r0, r1, r62.get(r3));
        r3 = r3 + 1;
        goto L15
    L17:
        return r52;
    L7:
        if (r62.isEmpty() == false) goto L9;
        return 0;
    L9:
        int r53 = 0;
    L10:
        if (r3 >= r2) goto L13;
        r53 = r53 + computeElementSizeNoTag(r0, r62.get(r3));
        r3 = r3 + 1;
        goto L10
    L13:
        return (CodedOutputStream.computeTagSize(r1) + r53) + CodedOutputStream.computeUInt32SizeNoTag(r53);
    L19:
        return computeElementSize(r0, r1, r6);
    }

    private int getMessageSetSerializedSize(Map.Entry<T, Object> r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.getKey());
        r1.getValue();
        throw null;
    }

    public static int getWireFormatForFieldType(WireFormat.FieldType r0, boolean r1) {
        if (r1 == false) goto L6;
        return 2;
    L6:
        return r0.getWireType();
    }

    private static <T extends FieldDescriptorLite<T>> boolean isInitialized(Map.Entry<T, Object> r0) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.getKey());
        throw null;
    }

    private static boolean isValidType(WireFormat.FieldType r2, Object r3) {
        Internal.checkNotNull(r3);
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[r2.getJavaType().ordinal()]) {
            case 1: goto L31;
            case 2: goto L29;
            case 3: goto L27;
            case 4: goto L25;
            case 5: goto L23;
            case 6: goto L21;
            case 7: goto L14;
            case 8: goto L10;
            case 9: goto L6;
            default: goto L4;
        };
    L4:
        return false;
    L6:
        if ((r3 instanceof MessageLite) == true) goto L8;
        return false;
    L8:
        return true;
    L10:
        if ((r3 instanceof Integer) == true) goto L12;
        return false;
    L12:
        return true;
    L14:
        if ((r3 instanceof ByteString) == false) goto L16;
    L19:
        return true;
    L16:
        if ((r3 instanceof byte[]) == true) goto L19;
        return false;
    L21:
        return r3 instanceof String;
    L23:
        return r3 instanceof Boolean;
    L25:
        return r3 instanceof Double;
    L27:
        return r3 instanceof Float;
    L29:
        return r3 instanceof Long;
    L31:
        return r3 instanceof Integer;
    }

    private void mergeFromField(Map.Entry<T, Object> r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.getKey());
        r1.getValue();
        throw null;
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> newFieldSet() {
        return new FieldSet();
    }

    private void verifyType(T r1, Object r2) {
        if (isValidType(r1.getLiteType(), r2) == false) goto L5;
        return;
    L5:
        FieldSet$$ExternalSyntheticBUOutline0.m("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(r1.getNumber()), r1.getLiteType().getJavaType(), r2.getClass().getName()});
    }

    public static void writeElement(CodedOutputStream r1, WireFormat.FieldType r2, int r3, Object r4) {
        if (r2 != WireFormat.FieldType.GROUP) goto L6;
        r1.writeGroup(r3, (MessageLite) r4);
        return;
    L6:
        r1.writeTag(r3, getWireFormatForFieldType(r2, false));
        writeElementNoTag(r1, r2, r4);
    }

    public static void writeElementNoTag(CodedOutputStream r1, WireFormat.FieldType r2, Object r3) {
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r2.ordinal()]) {
            case 1: goto L47;
            case 2: goto L45;
            case 3: goto L43;
            case 4: goto L41;
            case 5: goto L39;
            case 6: goto L37;
            case 7: goto L35;
            case 8: goto L33;
            case 9: goto L31;
            case 10: goto L29;
            case 11: goto L24;
            case 12: goto L18;
            case 13: goto L15;
            case 14: goto L13;
            case 15: goto L11;
            case 16: goto L9;
            case 17: goto L7;
            case 18: goto L5;
            default: goto L4;
        };
    L4:
        return;
    L5:
        r1.writeEnumNoTag(((Integer) r3).intValue());
        return;
    L7:
        r1.writeSInt64NoTag(((Long) r3).longValue());
        return;
    L9:
        r1.writeSInt32NoTag(((Integer) r3).intValue());
        return;
    L11:
        r1.writeSFixed64NoTag(((Long) r3).longValue());
        return;
    L13:
        r1.writeSFixed32NoTag(((Integer) r3).intValue());
        return;
    L15:
        r1.writeUInt32NoTag(((Integer) r3).intValue());
        return;
    L29:
        r1.writeMessageNoTag((MessageLite) r3);
        return;
    L31:
        r1.writeGroupNoTag((MessageLite) r3);
        return;
    L33:
        r1.writeBoolNoTag(((Boolean) r3).booleanValue());
        return;
    L35:
        r1.writeFixed32NoTag(((Integer) r3).intValue());
        return;
    L37:
        r1.writeFixed64NoTag(((Long) r3).longValue());
        return;
    L39:
        r1.writeInt32NoTag(((Integer) r3).intValue());
        return;
    L41:
        r1.writeUInt64NoTag(((Long) r3).longValue());
        return;
    L43:
        r1.writeInt64NoTag(((Long) r3).longValue());
        return;
    L45:
        r1.writeFloatNoTag(((Float) r3).floatValue());
        return;
    L47:
        r1.writeDoubleNoTag(((Double) r3).doubleValue());
        return;
    L18:
        if ((r3 instanceof ByteString) == false) goto L21;
        r1.writeBytesNoTag((ByteString) r3);
        return;
    L21:
        r1.writeByteArrayNoTag((byte[]) r3);
        return;
    L24:
        if ((r3 instanceof ByteString) == false) goto L27;
        r1.writeBytesNoTag((ByteString) r3);
        return;
    L27:
        r1.writeStringNoTag((String) r3);
    }

    public FieldSet<T> clone() {
        FieldSet<T> r0 = newFieldSet();
        int r1 = this.fields.getNumArrayEntries();
        int r2 = 0;
    L3:
        SmallSortedMap<T, Object> r3 = this.fields;
        if (r2 >= r1) goto L6;
        Map.Entry<K, Object> r32 = r3.getArrayEntryAt(r2);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r32.getKey());
        r0.setField(null, r32.getValue());
        r2 = r2 + 1;
        goto L3
    L6:
        Iterator r12 = r3.getOverflowEntries().iterator();
    L8:
        if (r12.hasNext() == false) goto L10;
        Map.Entry r22 = (Map.Entry) r12.next();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.getKey());
        r0.setField(null, r22.getValue());
        goto L8
    L10:
        r0.hasLazyField = this.hasLazyField;
        return r0;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m722clone() {
        return clone();
    }

    public Iterator<Map.Entry<T, Object>> descendingIterator() {
        if (isEmpty() == true) goto L5;
        boolean r0 = this.hasLazyField;
        SmallSortedMap<T, Object> r1 = this.fields;
        if (r0 == false) goto L11;
        return new LazyField$LazyIterator(r1.descendingEntrySet().iterator());
    L11:
        return r1.descendingEntrySet().iterator();
    L5:
        return Collections.emptyIterator();
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof FieldSet) == true) goto L10;
        return false;
    L10:
        return this.fields.equals(((FieldSet) r2).fields);
    }

    public int getMessageSetSerializedSize() {
        int r0 = this.fields.getNumArrayEntries();
        int r1 = 0;
        int r2 = 0;
    L3:
        SmallSortedMap<T, Object> r3 = this.fields;
        if (r1 >= r0) goto L6;
        r2 = r2 + getMessageSetSerializedSize(r3.getArrayEntryAt(r1));
        r1 = r1 + 1;
        goto L3
    L6:
        Iterator r02 = r3.getOverflowEntries().iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        r2 = r2 + getMessageSetSerializedSize((Map.Entry) r02.next());
        goto L8
    L10:
        return r2;
    }

    public int getSerializedSize() {
        int r0 = this.fields.getNumArrayEntries();
        int r1 = 0;
        int r2 = 0;
    L3:
        SmallSortedMap<T, Object> r3 = this.fields;
        if (r1 >= r0) goto L6;
        Map.Entry<K, Object> r32 = r3.getArrayEntryAt(r1);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r32.getKey());
        r2 = r2 + computeFieldSize(null, r32.getValue());
        r1 = r1 + 1;
        goto L3
    L6:
        Iterator r6 = r3.getOverflowEntries().iterator();
    L8:
        if (r6.hasNext() == false) goto L10;
        Map.Entry r02 = (Map.Entry) r6.next();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.getKey());
        r2 = r2 + computeFieldSize(null, r02.getValue());
        goto L8
    L10:
        return r2;
    }

    public int hashCode() {
        return this.fields.hashCode();
    }

    public boolean isEmpty() {
        return this.fields.isEmpty();
    }

    public boolean isInitialized() {
        int r0 = this.fields.getNumArrayEntries();
        int r2 = 0;
    L3:
        SmallSortedMap<T, Object> r3 = this.fields;
        if (r2 >= r0) goto L9;
        if (isInitialized(r3.getArrayEntryAt(r2)) == false) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        return false;
    L9:
        Iterator r4 = r3.getOverflowEntries().iterator();
    L11:
        if (r4.hasNext() == false) goto L15;
        if (isInitialized((Map.Entry) r4.next()) == true) goto L11;
        return false;
    L15:
        return true;
    }

    public Iterator<Map.Entry<T, Object>> iterator() {
        if (isEmpty() == true) goto L5;
        boolean r0 = this.hasLazyField;
        SmallSortedMap<T, Object> r1 = this.fields;
        if (r0 == false) goto L11;
        return new LazyField$LazyIterator(r1.entrySet().iterator());
    L11:
        return r1.entrySet().iterator();
    L5:
        return Collections.emptyIterator();
    }

    public void makeImmutable() {
        if (this.isImmutable == false) goto L5;
        return;
    L5:
        int r0 = this.fields.getNumArrayEntries();
        int r1 = 0;
    L6:
        SmallSortedMap<T, Object> r2 = this.fields;
        if (r1 >= r0) goto L12;
        Map.Entry<K, Object> r22 = r2.getArrayEntryAt(r1);
        if ((r22.getValue() instanceof GeneratedMessageLite) == false) goto L11;
        ((GeneratedMessageLite) r22.getValue()).makeImmutable();
    L11:
        r1 = r1 + 1;
        goto L6
    L12:
        r2.makeImmutable();
        this.isImmutable = true;
    }

    public void mergeFrom(FieldSet<T> r4) {
        int r0 = r4.fields.getNumArrayEntries();
        int r1 = 0;
    L3:
        SmallSortedMap<T, Object> r2 = r4.fields;
        if (r1 >= r0) goto L6;
        mergeFromField(r2.getArrayEntryAt(r1));
        r1 = r1 + 1;
        goto L3
    L6:
        Iterator r42 = r2.getOverflowEntries().iterator();
    L8:
        if (r42.hasNext() == false) goto L10;
        mergeFromField((Map.Entry) r42.next());
        goto L8
    }

    public void setField(T r4, Object r5) {
        if (r4.isRepeated() == true) goto L5;
        verifyType(r4, r5);
    L13:
        this.fields.put(r4, r5);
        return;
    L5:
        if ((r5 instanceof List) == false) goto L10;
        ArrayList r0 = new ArrayList();
        r0.addAll((List) r5);
        int r52 = r0.size();
        int r1 = 0;
    L7:
        if (r1 >= r52) goto L9;
        Object r2 = r0.get(r1);
        r1 = r1 + 1;
        verifyType(r4, r2);
        goto L7
    L9:
        r5 = r0;
        goto L13
    L10:
        f$$ExternalSyntheticBUOutline1.m("Wrong object type used with protocol message reflection.");
    }
}
