package com.google.protobuf;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes5.dex */
abstract class SchemaUtil {
    private static final Class<?> GENERATED_MESSAGE_CLASS = null;
    private static final UnknownFieldSchema<?, ?> PROTO2_UNKNOWN_FIELD_SET_SCHEMA = null;
    private static final UnknownFieldSchema<?, ?> PROTO3_UNKNOWN_FIELD_SET_SCHEMA = null;
    private static final UnknownFieldSchema<?, ?> UNKNOWN_FIELD_SET_LITE_SCHEMA = null;

    static {
        GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
        PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
        PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
        UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();
    }

    public static int computeSizeBoolList(int r0, List<?> r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L6;
        return 0;
    L6:
        if (r2 == false) goto L10;
        return CodedOutputStream.computeTagSize(r0) + CodedOutputStream.computeLengthDelimitedFieldSize(r12);
    L10:
        return r12 * CodedOutputStream.computeBoolSize(r0, true);
    }

    public static int computeSizeBoolListNoTag(List<?> r0) {
        return r0.size();
    }

    public static int computeSizeByteStringList(int r2, List<ByteString> r3) {
        int r0 = r3.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r02 = r0 * CodedOutputStream.computeTagSize(r2);
    L7:
        if (r1 >= r3.size()) goto L9;
        r02 = r02 + CodedOutputStream.computeBytesSizeNoTag(r3.get(r1));
        r1 = r1 + 1;
        goto L7
    L9:
        return r02;
    }

    public static int computeSizeEnumList(int r1, List<Integer> r2, boolean r3) {
        int r0 = r2.size();
        if (r0 != 0) goto L6;
        return 0;
    L6:
        int r22 = computeSizeEnumListNoTag(r2);
        if (r3 == false) goto L11;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r22);
    L11:
        return r22 + (r0 * CodedOutputStream.computeTagSize(r1));
    }

    public static int computeSizeEnumListNoTag(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof IntArrayList) == false) goto L11;
        IntArrayList r42 = (IntArrayList) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + CodedOutputStream.computeEnumSizeNoTag(r42.getInt(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + CodedOutputStream.computeEnumSizeNoTag(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int computeSizeFixed32List(int r1, List<?> r2, boolean r3) {
        int r22 = r2.size();
        if (r22 != 0) goto L5;
        return 0;
    L5:
        if (r3 == false) goto L9;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r22 * 4);
    L9:
        return r22 * CodedOutputStream.computeFixed32Size(r1, 0);
    }

    public static int computeSizeFixed32ListNoTag(List<?> r0) {
        return r0.size() * 4;
    }

    public static int computeSizeFixed64List(int r2, List<?> r3, boolean r4) {
        int r32 = r3.size();
        if (r32 != 0) goto L6;
        return 0;
    L6:
        if (r4 == false) goto L10;
        return CodedOutputStream.computeTagSize(r2) + CodedOutputStream.computeLengthDelimitedFieldSize(r32 * 8);
    L10:
        return r32 * CodedOutputStream.computeFixed64Size(r2, 0);
    }

    public static int computeSizeFixed64ListNoTag(List<?> r0) {
        return r0.size() * 8;
    }

    public static int computeSizeGroupList(int r4, List<MessageLite> r5, Schema r6) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r2 = 0;
    L6:
        if (r1 >= r0) goto L8;
        r2 = r2 + CodedOutputStream.computeGroupSize(r4, r5.get(r1), r6);
        r1 = r1 + 1;
        goto L6
    L8:
        return r2;
    }

    public static int computeSizeInt32List(int r1, List<Integer> r2, boolean r3) {
        int r0 = r2.size();
        if (r0 != 0) goto L6;
        return 0;
    L6:
        int r22 = computeSizeInt32ListNoTag(r2);
        if (r3 == false) goto L11;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r22);
    L11:
        return r22 + (r0 * CodedOutputStream.computeTagSize(r1));
    }

    public static int computeSizeInt32ListNoTag(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof IntArrayList) == false) goto L11;
        IntArrayList r42 = (IntArrayList) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + CodedOutputStream.computeInt32SizeNoTag(r42.getInt(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + CodedOutputStream.computeInt32SizeNoTag(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int computeSizeInt64List(int r1, List<Long> r2, boolean r3) {
        if (r2.size() != 0) goto L6;
        return 0;
    L6:
        int r0 = computeSizeInt64ListNoTag(r2);
        if (r3 == false) goto L11;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r0);
    L11:
        return r0 + (r2.size() * CodedOutputStream.computeTagSize(r1));
    }

    public static int computeSizeInt64ListNoTag(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof LongArrayList) == false) goto L11;
        LongArrayList r52 = (LongArrayList) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + CodedOutputStream.computeInt64SizeNoTag(r52.getLong(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + CodedOutputStream.computeInt64SizeNoTag(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int computeSizeMessage(int r0, Object r1, Schema r2) {
        return CodedOutputStream.computeMessageSize(r0, (MessageLite) r1, r2);
    }

    public static int computeSizeMessageList(int r3, List<?> r4, Schema r5) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r32 = CodedOutputStream.computeTagSize(r3) * r0;
    L6:
        if (r1 >= r0) goto L8;
        r32 = r32 + CodedOutputStream.computeMessageSizeNoTag((MessageLite) r4.get(r1), r5);
        r1 = r1 + 1;
        goto L6
    L8:
        return r32;
    }

    public static int computeSizeSInt32List(int r1, List<Integer> r2, boolean r3) {
        int r0 = r2.size();
        if (r0 != 0) goto L6;
        return 0;
    L6:
        int r22 = computeSizeSInt32ListNoTag(r2);
        if (r3 == false) goto L11;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r22);
    L11:
        return r22 + (r0 * CodedOutputStream.computeTagSize(r1));
    }

    public static int computeSizeSInt32ListNoTag(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof IntArrayList) == false) goto L11;
        IntArrayList r42 = (IntArrayList) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + CodedOutputStream.computeSInt32SizeNoTag(r42.getInt(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + CodedOutputStream.computeSInt32SizeNoTag(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int computeSizeSInt64List(int r1, List<Long> r2, boolean r3) {
        int r0 = r2.size();
        if (r0 != 0) goto L6;
        return 0;
    L6:
        int r22 = computeSizeSInt64ListNoTag(r2);
        if (r3 == false) goto L11;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r22);
    L11:
        return r22 + (r0 * CodedOutputStream.computeTagSize(r1));
    }

    public static int computeSizeSInt64ListNoTag(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof LongArrayList) == false) goto L11;
        LongArrayList r52 = (LongArrayList) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + CodedOutputStream.computeSInt64SizeNoTag(r52.getLong(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + CodedOutputStream.computeSInt64SizeNoTag(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int computeSizeStringList(int r4, List<?> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r42 = CodedOutputStream.computeTagSize(r4) * r0;
        if ((r5 instanceof LazyStringList) == false) goto L16;
        LazyStringList r52 = (LazyStringList) r5;
    L8:
        if (r1 >= r0) goto L15;
        Object r2 = r52.getRaw(r1);
        if ((r2 instanceof ByteString) == false) goto L13;
        int r22 = CodedOutputStream.computeBytesSizeNoTag((ByteString) r2);
    L12:
        r42 = r42 + r22;
        r1 = r1 + 1;
        goto L8
    L13:
        r22 = CodedOutputStream.computeStringSizeNoTag((String) r2);
        goto L12
    L15:
        return r42;
    L16:
        if (r1 >= r0) goto L23;
        Object r23 = r5.get(r1);
        if ((r23 instanceof ByteString) == false) goto L21;
        int r24 = CodedOutputStream.computeBytesSizeNoTag((ByteString) r23);
    L20:
        r42 = r42 + r24;
        r1 = r1 + 1;
        goto L16
    L21:
        r24 = CodedOutputStream.computeStringSizeNoTag((String) r23);
        goto L20
    L23:
        return r42;
    }

    public static int computeSizeUInt32List(int r1, List<Integer> r2, boolean r3) {
        int r0 = r2.size();
        if (r0 != 0) goto L6;
        return 0;
    L6:
        int r22 = computeSizeUInt32ListNoTag(r2);
        if (r3 == false) goto L11;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r22);
    L11:
        return r22 + (r0 * CodedOutputStream.computeTagSize(r1));
    }

    public static int computeSizeUInt32ListNoTag(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof IntArrayList) == false) goto L11;
        IntArrayList r42 = (IntArrayList) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + CodedOutputStream.computeUInt32SizeNoTag(r42.getInt(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + CodedOutputStream.computeUInt32SizeNoTag(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int computeSizeUInt64List(int r1, List<Long> r2, boolean r3) {
        int r0 = r2.size();
        if (r0 != 0) goto L6;
        return 0;
    L6:
        int r22 = computeSizeUInt64ListNoTag(r2);
        if (r3 == false) goto L11;
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(r22);
    L11:
        return r22 + (r0 * CodedOutputStream.computeTagSize(r1));
    }

    public static int computeSizeUInt64ListNoTag(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof LongArrayList) == false) goto L11;
        LongArrayList r52 = (LongArrayList) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + CodedOutputStream.computeUInt64SizeNoTag(r52.getLong(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + CodedOutputStream.computeUInt64SizeNoTag(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static <UT, UB> UB filterUnknownEnumList(Object r4, int r5, List<Integer> r6, Internal.EnumLiteMap<?> r7, UB r8, UnknownFieldSchema<UT, UB> r9) {
        if (r7 != null) goto L5;
        return r8;
    L5:
        if ((r6 instanceof RandomAccess) == false) goto L12;
        int r0 = r6.size();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        int r3 = r6.get(r2).intValue();
        r7.findValueByNumber(r3);
        r8 = (UB) storeUnknownEnum(r4, r5, r3, r8, r9);
        r2 = r2 + 1;
        goto L7
    L9:
        if (r0 == 0) goto L11;
        r6.subList(0, r0).clear();
    L11:
        return r8;
    L12:
        Iterator<Integer> r62 = r6.iterator();
    L14:
        if (r62.hasNext() == false) goto L16;
        int r02 = r62.next().intValue();
        r7.findValueByNumber(r02);
        r8 = (UB) storeUnknownEnum(r4, r5, r02, r8, r9);
        r62.remove();
        goto L14
    L16:
        return r8;
    }

    public static <UT, UB> UB filterUnknownEnumList(Object r6, int r7, List<Integer> r8, Internal.EnumVerifier r9, UB r10, UnknownFieldSchema<UT, UB> r11) {
        if (r9 != null) goto L5;
        return r10;
    L5:
        if ((r8 instanceof RandomAccess) == false) goto L18;
        int r0 = r8.size();
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= r0) goto L15;
        Integer r3 = r8.get(r1);
        int r4 = r3.intValue();
        if (r9.isInRange(r4) == false) goto L13;
        if (r1 == r2) goto L12;
        r8.set(r2, r3);
    L12:
        r2 = r2 + 1;
    L14:
        r1 = r1 + 1;
        goto L7
    L13:
        r10 = (UB) storeUnknownEnum(r6, r7, r4, r10, r11);
        goto L14
    L15:
        if (r2 == r0) goto L17;
        r8.subList(r2, r0).clear();
    L17:
        return r10;
    L18:
        Iterator<Integer> r82 = r8.iterator();
    L20:
        if (r82.hasNext() == false) goto L24;
        int r02 = r82.next().intValue();
        if (r9.isInRange(r02) == true) goto L20;
        r10 = (UB) storeUnknownEnum(r6, r7, r02, r10, r11);
        r82.remove();
        goto L20
    L24:
        return r10;
    }

    private static Class<?> getGeneratedMessageClass() {
        return Class.forName("com.google.protobuf.GeneratedMessageV3");
    L4:
        return null;
    }

    private static UnknownFieldSchema<?, ?> getUnknownFieldSetSchema(boolean r3) {
        Class<?> r1 = getUnknownFieldSetSchemaClass();     // Catch: Throwable -> L8
        if (r1 != null) goto L6;
        return null;
    L6:
        return (UnknownFieldSchema) r1.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(r3)});
    L8:
        return null;
    }

    private static Class<?> getUnknownFieldSetSchemaClass() {
        return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
    L4:
        return null;
    }

    public static <T, FT extends FieldSet.FieldDescriptorLite<FT>> void mergeExtensions(ExtensionSchema<FT> r1, T r2, T r3) {
        FieldSet<T> r32 = r1.getExtensions(r3);
        if (r32.isEmpty() == true) goto L6;
        r1.getMutableExtensions(r2).mergeFrom(r32);
        return;
    }

    public static <T> void mergeMap(MapFieldSchema r1, T r2, T r3, long r4) {
        UnsafeUtil.putObject(r2, r4, r1.mergeFrom(UnsafeUtil.getObject(r2, r4), UnsafeUtil.getObject(r3, r4)));
    }

    public static <T, UT, UB> void mergeUnknownFields(UnknownFieldSchema<UT, UB> r1, T r2, T r3) {
        r1.setToMessage(r2, r1.merge(r1.getFromMessage(r2), r1.getFromMessage(r3)));
    }

    public static UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema() {
        return PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> proto3UnknownFieldSetSchema() {
        return PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static void requireGeneratedMessage(Class<?> r1) {
        if (GeneratedMessageLite.class.isAssignableFrom(r1) == true) goto L11;
        Class<?> r0 = GENERATED_MESSAGE_CLASS;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isAssignableFrom(r1) == true) goto L13;
        f$$ExternalSyntheticBUOutline1.m("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        return;
    L13:
        return;
    }

    public static boolean safeEquals(Object r0, Object r1) {
        if (r0 == r1) goto L9;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.equals(r1) == true) goto L12;
        return false;
    L12:
        return true;
    L9:
        return true;
    }

    public static <UT, UB> UB storeUnknownEnum(Object r2, int r3, int r4, UB r5, UnknownFieldSchema<UT, UB> r6) {
        if (r5 != null) goto L4;
        r5 = r6.getBuilderFromMessage(r2);
    L4:
        r6.addVarint(r5, r3, r4);
        return r5;
    }

    public static UnknownFieldSchema<?, ?> unknownFieldSetLiteSchema() {
        return UNKNOWN_FIELD_SET_LITE_SCHEMA;
    }

    public static void writeBoolList(int r1, List<Boolean> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeBoolList(r1, r2, r4);
        return;
    }

    public static void writeBytesList(int r1, List<ByteString> r2, Writer r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeBytesList(r1, r2);
        return;
    }

    public static void writeDoubleList(int r1, List<Double> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeDoubleList(r1, r2, r4);
        return;
    }

    public static void writeEnumList(int r1, List<Integer> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeEnumList(r1, r2, r4);
        return;
    }

    public static void writeFixed32List(int r1, List<Integer> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeFixed32List(r1, r2, r4);
        return;
    }

    public static void writeFixed64List(int r1, List<Long> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeFixed64List(r1, r2, r4);
        return;
    }

    public static void writeFloatList(int r1, List<Float> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeFloatList(r1, r2, r4);
        return;
    }

    public static void writeGroupList(int r1, List<?> r2, Writer r3, Schema r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeGroupList(r1, r2, r4);
        return;
    }

    public static void writeInt32List(int r1, List<Integer> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeInt32List(r1, r2, r4);
        return;
    }

    public static void writeInt64List(int r1, List<Long> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeInt64List(r1, r2, r4);
        return;
    }

    public static void writeMessageList(int r1, List<?> r2, Writer r3, Schema r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeMessageList(r1, r2, r4);
        return;
    }

    public static void writeSFixed32List(int r1, List<Integer> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeSFixed32List(r1, r2, r4);
        return;
    }

    public static void writeSFixed64List(int r1, List<Long> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeSFixed64List(r1, r2, r4);
        return;
    }

    public static void writeSInt32List(int r1, List<Integer> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeSInt32List(r1, r2, r4);
        return;
    }

    public static void writeSInt64List(int r1, List<Long> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeSInt64List(r1, r2, r4);
        return;
    }

    public static void writeStringList(int r1, List<String> r2, Writer r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeStringList(r1, r2);
        return;
    }

    public static void writeUInt32List(int r1, List<Integer> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeUInt32List(r1, r2, r4);
        return;
    }

    public static void writeUInt64List(int r1, List<Long> r2, Writer r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.writeUInt64List(r1, r2, r4);
        return;
    }
}
