package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.WireFormat;
import androidx.datastore.preferences.protobuf.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
final class CodedOutputStreamWriter implements Writer {
    private final CodedOutputStream output;

    /* renamed from: androidx.datastore.preferences.protobuf.CodedOutputStreamWriter$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = null;

        static {
            int[] r0 = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0;
            r0[WireFormat.FieldType.BOOL.ordinal()] = 1;     // Catch: NoSuchFieldError -> L16
        L30:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 2;     // Catch: NoSuchFieldError -> L17
        L36:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 3;     // Catch: NoSuchFieldError -> L18
        L50:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 4;     // Catch: NoSuchFieldError -> L19
        L32:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 5;     // Catch: NoSuchFieldError -> L20
        L38:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 6;     // Catch: NoSuchFieldError -> L21
        L42:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 7;     // Catch: NoSuchFieldError -> L22
        L46:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 8;     // Catch: NoSuchFieldError -> L23
        L28:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 9;     // Catch: NoSuchFieldError -> L24
        L34:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 10;     // Catch: NoSuchFieldError -> L25
        L40:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 11;     // Catch: NoSuchFieldError -> L26
        L44:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 12;     // Catch: NoSuchFieldError -> L27
            return;
        }
    }

    private CodedOutputStreamWriter(CodedOutputStream r2) {
        CodedOutputStream r22 = (CodedOutputStream) Internal.checkNotNull(r2, "output");
        this.output = r22;
        r22.wrapper = this;
    }

    public static CodedOutputStreamWriter forCodedOutput(CodedOutputStream r1) {
        CodedOutputStreamWriter r0 = r1.wrapper;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new CodedOutputStreamWriter(r1);
    }

    private void writeBoolListInternal(int r1, BooleanArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeBoolListInternal(int r3, List<Boolean> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeBoolSizeNoTag(r4.get(r32).booleanValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeBoolNoTag(r4.get(r0).booleanValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeBool(r3, r4.get(r0).booleanValue());
        r0 = r0 + 1;
        goto L13
    }

    private <V> void writeDeterministicBooleanMapEntry(int r3, boolean r4, V r5, MapEntryLite.Metadata<Boolean, V> r6) {
        this.output.writeTag(r3, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(r6, Boolean.valueOf(r4), r5));
        MapEntryLite.writeTo(this.output, r6, Boolean.valueOf(r4), r5);
    }

    private <V> void writeDeterministicIntegerMap(int r8, MapEntryLite.Metadata<Integer, V> r9, Map<Integer, V> r10) {
        int r0 = r10.size();
        int[] r1 = new int[r0];
        Iterator<Integer> r2 = r10.keySet().iterator();
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r1[r4] = r2.next().intValue();
        r4 = r4 + 1;
        goto L4
    L6:
        Arrays.sort(r1);
    L7:
        if (r3 >= r0) goto L9;
        int r22 = r1[r3];
        V r42 = r10.get(Integer.valueOf(r22));
        this.output.writeTag(r8, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(r9, Integer.valueOf(r22), r42));
        MapEntryLite.writeTo(this.output, r9, Integer.valueOf(r22), r42);
        r3 = r3 + 1;
        goto L7
    }

    private <V> void writeDeterministicLongMap(int r9, MapEntryLite.Metadata<Long, V> r10, Map<Long, V> r11) {
        int r0 = r11.size();
        long[] r1 = new long[r0];
        Iterator<Long> r2 = r11.keySet().iterator();
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r1[r4] = r2.next().longValue();
        r4 = r4 + 1;
        goto L4
    L6:
        Arrays.sort(r1);
    L7:
        if (r3 >= r0) goto L9;
        long r42 = r1[r3];
        V r22 = r11.get(Long.valueOf(r42));
        this.output.writeTag(r9, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(r10, Long.valueOf(r42), r22));
        MapEntryLite.writeTo(this.output, r10, Long.valueOf(r42), r22);
        r3 = r3 + 1;
        goto L7
    }

    private <K, V> void writeDeterministicMap(int r3, MapEntryLite.Metadata<K, V> r4, Map<K, V> r5) {
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r4.keyType.ordinal()]) {
            case 1: goto L12;
            case 2: goto L10;
            case 3: goto L10;
            case 4: goto L10;
            case 5: goto L10;
            case 6: goto L10;
            case 7: goto L8;
            case 8: goto L8;
            case 9: goto L8;
            case 10: goto L8;
            case 11: goto L8;
            case 12: goto L6;
            default: goto L4;
        };
    L4:
        Buffer$$ExternalSyntheticBUOutline4.m("does not support key type: ", r4.keyType);
        return;
    L6:
        writeDeterministicStringMap(r3, r4, r5);
        return;
    L8:
        writeDeterministicLongMap(r3, r4, r5);
        return;
    L10:
        writeDeterministicIntegerMap(r3, r4, r5);
        return;
    L12:
        V r0 = r5.get(Boolean.FALSE);
        if (r0 == null) goto L15;
        writeDeterministicBooleanMapEntry(r3, false, r0, r4);
    L15:
        V r52 = r5.get(Boolean.TRUE);
        if (r52 == null) goto L19;
        writeDeterministicBooleanMapEntry(r3, true, r52, r4);
        return;
    }

    private <V> void writeDeterministicStringMap(int r8, MapEntryLite.Metadata<String, V> r9, Map<String, V> r10) {
        int r0 = r10.size();
        String[] r1 = new String[r0];
        Iterator<String> r2 = r10.keySet().iterator();
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r1[r4] = r2.next();
        r4 = r4 + 1;
        goto L4
    L6:
        Arrays.sort(r1);
    L7:
        if (r3 >= r0) goto L9;
        String r22 = r1[r3];
        V r42 = r10.get(r22);
        this.output.writeTag(r8, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(r9, r22, r42));
        MapEntryLite.writeTo(this.output, r9, r22, r42);
        r3 = r3 + 1;
        goto L7
    }

    private void writeDoubleListInternal(int r1, DoubleArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeDoubleListInternal(int r4, List<Double> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.output.writeTag(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + CodedOutputStream.computeDoubleSizeNoTag(r5.get(r42).doubleValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.output.writeDoubleNoTag(r5.get(r0).doubleValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.output.writeDouble(r4, r5.get(r0).doubleValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeEnumListInternal(int r1, IntArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeEnumListInternal(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeEnumSizeNoTag(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeEnumNoTag(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeEnum(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeFixed32ListInternal(int r1, IntArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeFixed32ListInternal(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeFixed32SizeNoTag(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeFixed32NoTag(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeFixed32(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeFixed64ListInternal(int r1, LongArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeFixed64ListInternal(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.output.writeTag(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + CodedOutputStream.computeFixed64SizeNoTag(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.output.writeFixed64NoTag(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.output.writeFixed64(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeFloatListInternal(int r1, FloatArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeFloatListInternal(int r3, List<Float> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeFloatSizeNoTag(r4.get(r32).floatValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeFloatNoTag(r4.get(r0).floatValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeFloat(r3, r4.get(r0).floatValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeInt32ListInternal(int r1, IntArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeInt32ListInternal(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeInt32SizeNoTag(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeInt32NoTag(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeInt32(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeInt64ListInternal(int r1, LongArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeInt64ListInternal(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.output.writeTag(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + CodedOutputStream.computeInt64SizeNoTag(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.output.writeInt64NoTag(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.output.writeInt64(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeLazyString(int r2, Object r3) {
        boolean r0 = r3 instanceof String;
        CodedOutputStream r1 = this.output;
        if (r0 == false) goto L6;
        r1.writeString(r2, (String) r3);
        return;
    L6:
        r1.writeBytes(r2, (ByteString) r3);
    }

    private void writeSFixed32ListInternal(int r1, IntArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeSFixed32ListInternal(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeSFixed32SizeNoTag(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeSFixed32NoTag(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeSFixed32(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeSFixed64ListInternal(int r1, LongArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeSFixed64ListInternal(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.output.writeTag(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + CodedOutputStream.computeSFixed64SizeNoTag(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.output.writeSFixed64NoTag(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.output.writeSFixed64(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeSInt32ListInternal(int r1, IntArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeSInt64ListInternal(int r1, LongArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeSInt64ListInternal(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.output.writeTag(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + CodedOutputStream.computeSInt64SizeNoTag(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.output.writeSInt64NoTag(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.output.writeSInt64(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    private void writeUInt32ListInternal(int r1, IntArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeUInt64ListInternal(int r1, LongArrayList r2, boolean r3) {
        if (r3 == false) goto L6;
        this.output.writeTag(r1, 2);
        throw null;
    L6:
        throw null;
    }

    private void writeUInt64ListInternal(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.output.writeTag(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + CodedOutputStream.computeUInt64SizeNoTag(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.output.writeUInt64NoTag(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.output.writeUInt64(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.ASCENDING;
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeBool(int r1, boolean r2) {
        this.output.writeBool(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeBoolList(int r2, List<Boolean> r3, boolean r4) {
        if ((r3 instanceof BooleanArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeBoolListInternal(r2, null, r4);
        return;
    L6:
        writeBoolListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeBytes(int r1, ByteString r2) {
        this.output.writeBytes(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeBytesList(int r4, List<ByteString> r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.output.writeBytes(r4, r5.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeDouble(int r1, double r2) {
        this.output.writeDouble(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeDoubleList(int r2, List<Double> r3, boolean r4) {
        if ((r3 instanceof DoubleArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeDoubleListInternal(r2, null, r4);
        return;
    L6:
        writeDoubleListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    @Deprecated
    public void writeEndGroup(int r2) {
        this.output.writeTag(r2, 4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeEnum(int r1, int r2) {
        this.output.writeEnum(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeEnumList(int r2, List<Integer> r3, boolean r4) {
        if ((r3 instanceof IntArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeEnumListInternal(r2, null, r4);
        return;
    L6:
        writeEnumListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeFixed32(int r1, int r2) {
        this.output.writeFixed32(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeFixed32List(int r2, List<Integer> r3, boolean r4) {
        if ((r3 instanceof IntArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeFixed32ListInternal(r2, null, r4);
        return;
    L6:
        writeFixed32ListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeFixed64(int r1, long r2) {
        this.output.writeFixed64(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeFixed64List(int r2, List<Long> r3, boolean r4) {
        if ((r3 instanceof LongArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeFixed64ListInternal(r2, null, r4);
        return;
    L6:
        writeFixed64ListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeFloat(int r1, float r2) {
        this.output.writeFloat(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeFloatList(int r2, List<Float> r3, boolean r4) {
        if ((r3 instanceof FloatArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeFloatListInternal(r2, null, r4);
        return;
    L6:
        writeFloatListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeGroup(int r1, Object r2, Schema r3) {
        this.output.writeGroup(r1, (MessageLite) r2, r3);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeGroupList(int r3, List<?> r4, Schema r5) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        writeGroup(r3, r4.get(r0), r5);
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeInt32(int r1, int r2) {
        this.output.writeInt32(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeInt32List(int r2, List<Integer> r3, boolean r4) {
        if ((r3 instanceof IntArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeInt32ListInternal(r2, null, r4);
        return;
    L6:
        writeInt32ListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeInt64(int r1, long r2) {
        this.output.writeInt64(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeInt64List(int r2, List<Long> r3, boolean r4) {
        if ((r3 instanceof LongArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeInt64ListInternal(r2, null, r4);
        return;
    L6:
        writeInt64ListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public <K, V> void writeMap(int r5, MapEntryLite.Metadata<K, V> r6, Map<K, V> r7) {
        if (this.output.isSerializationDeterministic() == false) goto L6;
        writeDeterministicMap(r5, r6, r7);
        return;
    L6:
        Iterator<Map.Entry<K, V>> r72 = r7.entrySet().iterator();
    L8:
        if (r72.hasNext() == false) goto L10;
        Map.Entry<K, V> r0 = r72.next();
        this.output.writeTag(r5, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(r6, r0.getKey(), r0.getValue()));
        MapEntryLite.writeTo(this.output, r6, r0.getKey(), r0.getValue());
        goto L8
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeMessage(int r1, Object r2, Schema r3) {
        this.output.writeMessage(r1, (MessageLite) r2, r3);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeMessageList(int r3, List<?> r4, Schema r5) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        writeMessage(r3, r4.get(r0), r5);
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public final void writeMessageSetItem(int r2, Object r3) {
        boolean r0 = r3 instanceof ByteString;
        CodedOutputStream r1 = this.output;
        if (r0 == false) goto L6;
        r1.writeRawMessageSetExtension(r2, (ByteString) r3);
        return;
    L6:
        r1.writeMessageSetExtension(r2, (MessageLite) r3);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSFixed32(int r1, int r2) {
        this.output.writeSFixed32(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSFixed32List(int r2, List<Integer> r3, boolean r4) {
        if ((r3 instanceof IntArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeSFixed32ListInternal(r2, null, r4);
        return;
    L6:
        writeSFixed32ListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSFixed64(int r1, long r2) {
        this.output.writeSFixed64(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSFixed64List(int r2, List<Long> r3, boolean r4) {
        if ((r3 instanceof LongArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeSFixed64ListInternal(r2, null, r4);
        return;
    L6:
        writeSFixed64ListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSInt32(int r1, int r2) {
        this.output.writeSInt32(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSInt32List(int r2, List<Integer> r3, boolean r4) {
        if ((r3 instanceof IntArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeSInt32ListInternal(r2, null, r4);
        return;
    L6:
        writeSInt32ListInternal(r2, r3, r4);
    }

    public void writeSInt32ListInternal(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeSInt32SizeNoTag(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeSInt32NoTag(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeSInt32(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSInt64(int r1, long r2) {
        this.output.writeSInt64(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeSInt64List(int r2, List<Long> r3, boolean r4) {
        if ((r3 instanceof LongArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeSInt64ListInternal(r2, null, r4);
        return;
    L6:
        writeSInt64ListInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    @Deprecated
    public void writeStartGroup(int r2) {
        this.output.writeTag(r2, 3);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeString(int r1, String r2) {
        this.output.writeString(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeStringList(int r4, List<String> r5) {
        int r1 = 0;
        if ((r5 instanceof LazyStringList) == false) goto L9;
        LazyStringList r0 = (LazyStringList) r5;
    L6:
        if (r1 >= r5.size()) goto L11;
        writeLazyString(r4, r0.getRaw(r1));
        r1 = r1 + 1;
        goto L6
    L11:
        return;
    L9:
        if (r1 >= r5.size()) goto L14;
        this.output.writeString(r4, r5.get(r1));
        r1 = r1 + 1;
        goto L9
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeUInt32(int r1, int r2) {
        this.output.writeUInt32(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeUInt32List(int r2, List<Integer> r3, boolean r4) {
        if ((r3 instanceof IntArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeUInt32ListInternal(r2, null, r4);
        return;
    L6:
        writeUInt32ListInternal(r2, r3, r4);
    }

    public void writeUInt32ListInternal(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.output.writeTag(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + CodedOutputStream.computeUInt32SizeNoTag(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.output.writeUInt32NoTag(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.output.writeUInt32NoTag(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.output.writeUInt32(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeUInt64(int r1, long r2) {
        this.output.writeUInt64(r1, r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Writer
    public void writeUInt64List(int r2, List<Long> r3, boolean r4) {
        if ((r3 instanceof LongArrayList) == false) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        writeUInt64ListInternal(r2, null, r4);
        return;
    L6:
        writeUInt64ListInternal(r2, r3, r4);
    }
}
