package com.google.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.protobuf.Internal;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class ArrayDecoders {

    public static final class Registers {
        public final ExtensionRegistryLite extensionRegistry;
        public int int1;
        public long long1;
        public Object object1;

        public Registers(ExtensionRegistryLite r1) {
            r1.getClass();
            this.extensionRegistry = r1;
        }
    }

    public static int decodeBoolList(int r7, byte[] r8, int r9, int r10, Internal.ProtobufList<?> r11, Registers r12) {
        BooleanArrayList r112 = (BooleanArrayList) r11;
        int r92 = decodeVarint64(r8, r9, r12);
        if (r12.long1 == 0) goto L5;
        boolean r0 = true;
    L6:
        r112.addBoolean(r0);
    L7:
        if (r92 >= r10) goto L16;
        int r02 = decodeVarint32(r8, r92, r12);
        if (r7 != r12.int1) goto L16;
        r92 = decodeVarint64(r8, r02, r12);
        if (r12.long1 == 0) goto L14;
        boolean r03 = true;
    L15:
        r112.addBoolean(r03);
        goto L7
    L14:
        r03 = false;
    L16:
        return r92;
    L5:
        r0 = false;
        goto L6
    }

    public static int decodeBytes(byte[] r2, int r3, Registers r4) {
        int r32 = decodeVarint32(r2, r3, r4);
        int r0 = r4.int1;
        if (r0 < 0) goto L14;
        if (r0 > (r2.length - r32)) goto L12;
        if (r0 != 0) goto L9;
        r4.object1 = ByteString.EMPTY;
        return r32;
    L9:
        r4.object1 = ByteString.copyFrom(r2, r32, r0);
        return r32 + r0;
    L12:
        throw InvalidProtocolBufferException.truncatedMessage();
    L14:
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int decodeBytesList(int r2, byte[] r3, int r4, int r5, Internal.ProtobufList<?> r6, Registers r7) {
        int r42 = decodeVarint32(r3, r4, r7);
        int r0 = r7.int1;
        if (r0 < 0) goto L29;
        if (r0 > (r3.length - r42)) goto L27;
        if (r0 != 0) goto L8;
        r6.add(ByteString.EMPTY);
    L10:
        if (r42 >= r5) goto L25;
        int r02 = decodeVarint32(r3, r42, r7);
        if (r2 != r7.int1) goto L25;
        r42 = decodeVarint32(r3, r02, r7);
        int r03 = r7.int1;
        if (r03 < 0) goto L24;
        if (r03 > (r3.length - r42)) goto L22;
        if (r03 == 0) goto L19;
        r6.add(ByteString.copyFrom(r3, r42, r03));
        r42 = r42 + r03;
        goto L10
    L19:
        r6.add(ByteString.EMPTY);
        goto L10
    L22:
        throw InvalidProtocolBufferException.truncatedMessage();
    L24:
        throw InvalidProtocolBufferException.negativeSize();
    L25:
        return r42;
    L8:
        r6.add(ByteString.copyFrom(r3, r42, r0));
        r42 = r42 + r0;
        goto L10
    L27:
        throw InvalidProtocolBufferException.truncatedMessage();
    L29:
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static double decodeDouble(byte[] r0, int r1) {
        return Double.longBitsToDouble(decodeFixed64(r0, r1));
    }

    public static int decodeDoubleList(int r3, byte[] r4, int r5, int r6, Internal.ProtobufList<?> r7, Registers r8) {
        DoubleArrayList r72 = (DoubleArrayList) r7;
        r72.addDouble(decodeDouble(r4, r5));
        int r52 = r5 + 8;
    L3:
        if (r52 >= r6) goto L8;
        int r0 = decodeVarint32(r4, r52, r8);
        if (r3 != r8.int1) goto L8;
        r72.addDouble(decodeDouble(r4, r0));
        r52 = r0 + 8;
    L8:
        return r52;
    }

    public static int decodeExtensionOrUnknownField(int r6, byte[] r7, int r8, int r9, Object r10, MessageLite r11, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> r12, Registers r13) {
        ExtensionRegistryLite r0 = r13.extensionRegistry;
        if (r0.findLiteExtensionByNumber(r11, r6 >>> 3) == null) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r10);
        throw null;
    L5:
        return decodeUnknownField(r6, r7, r8, r9, MessageSchema.getMutableUnknownFields(r10), r13);
    }

    public static int decodeFixed32(byte[] r2, int r3) {
        int r0 = ((r2[r3] & UByte.MAX_VALUE) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 8)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 16);
        return ((r2[r3 + 3] & UByte.MAX_VALUE) << 24) | r0;
    }

    public static int decodeFixed32List(int r2, byte[] r3, int r4, int r5, Internal.ProtobufList<?> r6, Registers r7) {
        IntArrayList r62 = (IntArrayList) r6;
        r62.addInt(decodeFixed32(r3, r4));
        int r42 = r4 + 4;
    L3:
        if (r42 >= r5) goto L8;
        int r0 = decodeVarint32(r3, r42, r7);
        if (r2 != r7.int1) goto L8;
        r62.addInt(decodeFixed32(r3, r0));
        r42 = r0 + 4;
    L8:
        return r42;
    }

    public static long decodeFixed64(byte[] r7, int r8) {
        return ((r7[r8 + 7] & 255) << 56) | (((((((r7[r8] & 255) | ((r7[r8 + 1] & 255) << 8)) | ((r7[r8 + 2] & 255) << 16)) | ((r7[r8 + 3] & 255) << 24)) | ((r7[r8 + 4] & 255) << 32)) | ((r7[r8 + 5] & 255) << 40)) | ((r7[r8 + 6] & 255) << 48));
    }

    public static int decodeFixed64List(int r3, byte[] r4, int r5, int r6, Internal.ProtobufList<?> r7, Registers r8) {
        LongArrayList r72 = (LongArrayList) r7;
        r72.addLong(decodeFixed64(r4, r5));
        int r52 = r5 + 8;
    L3:
        if (r52 >= r6) goto L8;
        int r0 = decodeVarint32(r4, r52, r8);
        if (r3 != r8.int1) goto L8;
        r72.addLong(decodeFixed64(r4, r0));
        r52 = r0 + 8;
    L8:
        return r52;
    }

    public static float decodeFloat(byte[] r0, int r1) {
        return Float.intBitsToFloat(decodeFixed32(r0, r1));
    }

    public static int decodeFloatList(int r2, byte[] r3, int r4, int r5, Internal.ProtobufList<?> r6, Registers r7) {
        FloatArrayList r62 = (FloatArrayList) r6;
        r62.addFloat(decodeFloat(r3, r4));
        int r42 = r4 + 4;
    L3:
        if (r42 >= r5) goto L8;
        int r0 = decodeVarint32(r3, r42, r7);
        if (r2 != r7.int1) goto L8;
        r62.addFloat(decodeFloat(r3, r0));
        r42 = r0 + 4;
    L8:
        return r42;
    }

    public static int decodeGroupField(Schema r7, byte[] r8, int r9, int r10, int r11, Registers r12) {
        Object r0 = r7.newInstance();
        int r72 = mergeGroupField(r0, r7, r8, r9, r10, r11, r12);
        r7.makeImmutable(r0);
        r12.object1 = r0;
        return r72;
    }

    public static int decodeGroupList(Schema r7, int r8, byte[] r9, int r10, int r11, Internal.ProtobufList<?> r12, Registers r13) {
        int r5 = (r8 & (-8)) | 4;
        int r72 = decodeGroupField(r7, r9, r10, r11, r5, r13);
        r12.add(r13.object1);
    L3:
        if (r72 >= r11) goto L8;
        int r3 = decodeVarint32(r9, r72, r13);
        if (r8 != r13.int1) goto L8;
        r72 = decodeGroupField(r7, r9, r3, r11, r5, r13);
        r12.add(r13.object1);
    L8:
        return r72;
    }

    public static int decodeMessageField(Schema r6, byte[] r7, int r8, int r9, Registers r10) {
        Object r0 = r6.newInstance();
        int r62 = mergeMessageField(r0, r6, r7, r8, r9, r10);
        r6.makeImmutable(r0);
        r10.object1 = r0;
        return r62;
    }

    public static int decodeMessageList(Schema<?> r2, int r3, byte[] r4, int r5, int r6, Internal.ProtobufList<?> r7, Registers r8) {
        int r52 = decodeMessageField(r2, r4, r5, r6, r8);
        r7.add(r8.object1);
    L3:
        if (r52 >= r6) goto L8;
        int r0 = decodeVarint32(r4, r52, r8);
        if (r3 != r8.int1) goto L8;
        r52 = decodeMessageField(r2, r4, r0, r6, r8);
        r7.add(r8.object1);
    L8:
        return r52;
    }

    public static int decodePackedBoolList(byte[] r5, int r6, Internal.ProtobufList<?> r7, Registers r8) {
        BooleanArrayList r72 = (BooleanArrayList) r7;
        int r62 = decodeVarint32(r5, r6, r8);
        int r0 = r8.int1 + r62;
    L3:
        if (r62 >= r0) goto L9;
        r62 = decodeVarint64(r5, r62, r8);
        if (r8.long1 == 0) goto L7;
        boolean r1 = true;
    L8:
        r72.addBoolean(r1);
        goto L3
    L7:
        r1 = false;
        goto L8
    L9:
        if (r62 != r0) goto L12;
        return r62;
    L12:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedDoubleList(byte[] r2, int r3, Internal.ProtobufList<?> r4, Registers r5) {
        DoubleArrayList r42 = (DoubleArrayList) r4;
        int r32 = decodeVarint32(r2, r3, r5);
        int r52 = r5.int1 + r32;
    L3:
        if (r32 >= r52) goto L5;
        r42.addDouble(decodeDouble(r2, r32));
        r32 = r32 + 8;
        goto L3
    L5:
        if (r32 != r52) goto L8;
        return r32;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFixed32List(byte[] r1, int r2, Internal.ProtobufList<?> r3, Registers r4) {
        IntArrayList r32 = (IntArrayList) r3;
        int r22 = decodeVarint32(r1, r2, r4);
        int r42 = r4.int1 + r22;
    L3:
        if (r22 >= r42) goto L5;
        r32.addInt(decodeFixed32(r1, r22));
        r22 = r22 + 4;
        goto L3
    L5:
        if (r22 != r42) goto L8;
        return r22;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFixed64List(byte[] r2, int r3, Internal.ProtobufList<?> r4, Registers r5) {
        LongArrayList r42 = (LongArrayList) r4;
        int r32 = decodeVarint32(r2, r3, r5);
        int r52 = r5.int1 + r32;
    L3:
        if (r32 >= r52) goto L5;
        r42.addLong(decodeFixed64(r2, r32));
        r32 = r32 + 8;
        goto L3
    L5:
        if (r32 != r52) goto L8;
        return r32;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFloatList(byte[] r1, int r2, Internal.ProtobufList<?> r3, Registers r4) {
        FloatArrayList r32 = (FloatArrayList) r3;
        int r22 = decodeVarint32(r1, r2, r4);
        int r42 = r4.int1 + r22;
    L3:
        if (r22 >= r42) goto L5;
        r32.addFloat(decodeFloat(r1, r22));
        r22 = r22 + 4;
        goto L3
    L5:
        if (r22 != r42) goto L8;
        return r22;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedSInt32List(byte[] r2, int r3, Internal.ProtobufList<?> r4, Registers r5) {
        IntArrayList r42 = (IntArrayList) r4;
        int r32 = decodeVarint32(r2, r3, r5);
        int r0 = r5.int1 + r32;
    L3:
        if (r32 >= r0) goto L5;
        r32 = decodeVarint32(r2, r32, r5);
        r42.addInt(CodedInputStream.decodeZigZag32(r5.int1));
        goto L3
    L5:
        if (r32 != r0) goto L8;
        return r32;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedSInt64List(byte[] r3, int r4, Internal.ProtobufList<?> r5, Registers r6) {
        LongArrayList r52 = (LongArrayList) r5;
        int r42 = decodeVarint32(r3, r4, r6);
        int r0 = r6.int1 + r42;
    L3:
        if (r42 >= r0) goto L5;
        r42 = decodeVarint64(r3, r42, r6);
        r52.addLong(CodedInputStream.decodeZigZag64(r6.long1));
        goto L3
    L5:
        if (r42 != r0) goto L8;
        return r42;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedVarint32List(byte[] r2, int r3, Internal.ProtobufList<?> r4, Registers r5) {
        IntArrayList r42 = (IntArrayList) r4;
        int r32 = decodeVarint32(r2, r3, r5);
        int r0 = r5.int1 + r32;
    L3:
        if (r32 >= r0) goto L5;
        r32 = decodeVarint32(r2, r32, r5);
        r42.addInt(r5.int1);
        goto L3
    L5:
        if (r32 != r0) goto L8;
        return r32;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedVarint64List(byte[] r3, int r4, Internal.ProtobufList<?> r5, Registers r6) {
        LongArrayList r52 = (LongArrayList) r5;
        int r42 = decodeVarint32(r3, r4, r6);
        int r0 = r6.int1 + r42;
    L3:
        if (r42 >= r0) goto L5;
        r42 = decodeVarint64(r3, r42, r6);
        r52.addLong(r6.long1);
        goto L3
    L5:
        if (r42 != r0) goto L8;
        return r42;
    L8:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodeSInt32List(int r2, byte[] r3, int r4, int r5, Internal.ProtobufList<?> r6, Registers r7) {
        IntArrayList r62 = (IntArrayList) r6;
        int r42 = decodeVarint32(r3, r4, r7);
        r62.addInt(CodedInputStream.decodeZigZag32(r7.int1));
    L3:
        if (r42 >= r5) goto L8;
        int r0 = decodeVarint32(r3, r42, r7);
        if (r2 != r7.int1) goto L8;
        r42 = decodeVarint32(r3, r0, r7);
        r62.addInt(CodedInputStream.decodeZigZag32(r7.int1));
    L8:
        return r42;
    }

    public static int decodeSInt64List(int r2, byte[] r3, int r4, int r5, Internal.ProtobufList<?> r6, Registers r7) {
        LongArrayList r62 = (LongArrayList) r6;
        int r42 = decodeVarint64(r3, r4, r7);
        r62.addLong(CodedInputStream.decodeZigZag64(r7.long1));
    L3:
        if (r42 >= r5) goto L8;
        int r0 = decodeVarint32(r3, r42, r7);
        if (r2 != r7.int1) goto L8;
        r42 = decodeVarint64(r3, r0, r7);
        r62.addLong(CodedInputStream.decodeZigZag64(r7.long1));
    L8:
        return r42;
    }

    public static int decodeString(byte[] r3, int r4, Registers r5) {
        int r42 = decodeVarint32(r3, r4, r5);
        int r0 = r5.int1;
        if (r0 < 0) goto L10;
        if (r0 != 0) goto L7;
        r5.object1 = _UrlKt.FRAGMENT_ENCODE_SET;
        return r42;
    L7:
        r5.object1 = new String(r3, r42, r0, Internal.UTF_8);
        return r42 + r0;
    L10:
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int decodeStringList(int r4, byte[] r5, int r6, int r7, Internal.ProtobufList<?> r8, Registers r9) {
        int r62 = decodeVarint32(r5, r6, r9);
        int r0 = r9.int1;
        if (r0 < 0) goto L22;
        if (r0 != 0) goto L7;
        r8.add(_UrlKt.FRAGMENT_ENCODE_SET);
    L9:
        if (r62 >= r7) goto L20;
        int r02 = decodeVarint32(r5, r62, r9);
        if (r4 != r9.int1) goto L20;
        r62 = decodeVarint32(r5, r02, r9);
        int r03 = r9.int1;
        if (r03 < 0) goto L19;
        if (r03 == 0) goto L16;
        r8.add(new String(r5, r62, r03, Internal.UTF_8));
        r62 = r62 + r03;
        goto L9
    L16:
        r8.add(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L9
    L19:
        throw InvalidProtocolBufferException.negativeSize();
    L20:
        return r62;
    L7:
        r8.add(new String(r5, r62, r0, Internal.UTF_8));
        r62 = r62 + r0;
        goto L9
    L22:
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int decodeStringListRequireUtf8(int r5, byte[] r6, int r7, int r8, Internal.ProtobufList<?> r9, Registers r10) {
        int r72 = decodeVarint32(r6, r7, r10);
        int r0 = r10.int1;
        if (r0 < 0) goto L30;
        if (r0 != 0) goto L7;
        r9.add(_UrlKt.FRAGMENT_ENCODE_SET);
    L11:
        if (r72 >= r8) goto L26;
        int r02 = decodeVarint32(r6, r72, r10);
        if (r5 != r10.int1) goto L26;
        r72 = decodeVarint32(r6, r02, r10);
        int r03 = r10.int1;
        if (r03 < 0) goto L25;
        if (r03 == 0) goto L18;
        int r2 = r72 + r03;
        if (Utf8.isValidUtf8(r6, r72, r2) == false) goto L23;
        r9.add(new String(r6, r72, r03, Internal.UTF_8));
        r72 = r2;
        goto L11
    L23:
        throw InvalidProtocolBufferException.invalidUtf8();
    L18:
        r9.add(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L11
    L25:
        throw InvalidProtocolBufferException.negativeSize();
    L26:
        return r72;
    L7:
        int r22 = r72 + r0;
        if (Utf8.isValidUtf8(r6, r72, r22) == false) goto L28;
        r9.add(new String(r6, r72, r0, Internal.UTF_8));
        r72 = r22;
        goto L11
    L28:
        throw InvalidProtocolBufferException.invalidUtf8();
    L30:
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int decodeStringRequireUtf8(byte[] r1, int r2, Registers r3) {
        int r22 = decodeVarint32(r1, r2, r3);
        int r0 = r3.int1;
        if (r0 < 0) goto L10;
        if (r0 != 0) goto L7;
        r3.object1 = _UrlKt.FRAGMENT_ENCODE_SET;
        return r22;
    L7:
        r3.object1 = Utf8.decodeUtf8(r1, r22, r0);
        return r22 + r0;
    L10:
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int decodeUnknownField(int r7, byte[] r8, int r9, int r10, UnknownFieldSetLite r11, Registers r12) {
        if (WireFormat.getTagFieldNumber(r7) == 0) goto L49;
        int r0 = WireFormat.getTagWireType(r7);
        if (r0 != 0) goto L7;
        int r82 = decodeVarint64(r8, r9, r12);
        r11.storeField(r7, Long.valueOf(r12.long1));
        return r82;
    L7:
        if (r0 != 1) goto L9;
        r11.storeField(r7, Long.valueOf(decodeFixed64(r8, r9)));
        return r9 + 8;
    L9:
        if (r0 != 2) goto L11;
        int r83 = decodeVarint32(r8, r9, r12);
        int r92 = r12.int1;
        if (r92 < 0) goto L43;
        if (r92 > (r8.length - r83)) goto L41;
        if (r92 != 0) goto L37;
        r11.storeField(r7, ByteString.EMPTY);
    L39:
        return r83 + r92;
    L37:
        r11.storeField(r7, ByteString.copyFrom(r8, r83, r92));
        goto L39
    L41:
        throw InvalidProtocolBufferException.truncatedMessage();
    L43:
        throw InvalidProtocolBufferException.negativeSize();
    L11:
        if (r0 != 3) goto L13;
        UnknownFieldSetLite r4 = UnknownFieldSetLite.newInstance();
        int r6 = (r7 & (-8)) | 4;
        int r02 = 0;
    L19:
        if (r9 >= r10) goto L25;
        int r2 = decodeVarint32(r8, r9, r12);
        r02 = r12.int1;
        if (r02 == r6) goto L22;
        r9 = decodeUnknownField(r02, r8, r2, r10, r4, r12);
        goto L19
    L22:
        r9 = r2;
    L25:
        if (r9 > r10) goto L30;
        if (r02 != r6) goto L30;
        r11.storeField(r7, r4);
        return r9;
    L30:
        throw InvalidProtocolBufferException.parseFailure();
    L13:
        if (r0 != 5) goto L17;
        r11.storeField(r7, Integer.valueOf(decodeFixed32(r8, r9)));
        return r9 + 4;
    L17:
        throw InvalidProtocolBufferException.invalidTag();
    L49:
        throw InvalidProtocolBufferException.invalidTag();
    }

    public static int decodeVarint32(int r2, byte[] r3, int r4, Registers r5) {
        int r22 = r2 & 127;
        int r0 = r4 + 1;
        byte r1 = r3[r4];
        if (r1 < 0) goto L6;
        r5.int1 = r22 | (r1 << 7);
        return r0;
    L6:
        int r23 = r22 | ((r1 & ByteCompanionObject.MAX_VALUE) << 7);
        int r12 = r4 + 2;
        byte r02 = r3[r0];
        if (r02 < 0) goto L10;
        r5.int1 = r23 | (r02 << 14);
        return r12;
    L10:
        int r24 = r23 | ((r02 & ByteCompanionObject.MAX_VALUE) << 14);
        int r03 = r4 + 3;
        byte r13 = r3[r12];
        if (r13 < 0) goto L14;
        r5.int1 = r24 | (r13 << 21);
        return r03;
    L14:
        int r25 = r24 | ((r13 & ByteCompanionObject.MAX_VALUE) << 21);
        int r42 = r4 + 4;
        byte r04 = r3[r03];
        if (r04 < 0) goto L18;
        r5.int1 = r25 | (r04 << 28);
        return r42;
    L18:
        int r26 = r25 | ((r04 & ByteCompanionObject.MAX_VALUE) << 28);
    L19:
        int r05 = r42 + 1;
        if (r3[r42] >= 0) goto L22;
        r42 = r05;
        goto L19
    L22:
        r5.int1 = r26;
        return r05;
    }

    public static int decodeVarint32(byte[] r1, int r2, Registers r3) {
        int r0 = r2 + 1;
        byte r22 = r1[r2];
        if (r22 < 0) goto L7;
        r3.int1 = r22;
        return r0;
    L7:
        return decodeVarint32(r22, r1, r0, r3);
    }

    public static int decodeVarint32List(int r2, byte[] r3, int r4, int r5, Internal.ProtobufList<?> r6, Registers r7) {
        IntArrayList r62 = (IntArrayList) r6;
        int r42 = decodeVarint32(r3, r4, r7);
        r62.addInt(r7.int1);
    L3:
        if (r42 >= r5) goto L8;
        int r0 = decodeVarint32(r3, r42, r7);
        if (r2 != r7.int1) goto L8;
        r42 = decodeVarint32(r3, r0, r7);
        r62.addInt(r7.int1);
    L8:
        return r42;
    }

    public static int decodeVarint64(long r7, byte[] r9, int r10, Registers r11) {
        int r0 = r10 + 1;
        byte r102 = r9[r10];
        long r72 = (r7 & 127) | ((r102 & ByteCompanionObject.MAX_VALUE) << 7);
        int r1 = 7;
    L3:
        if (r102 >= 0) goto L5;
        int r103 = r0 + 1;
        byte r02 = r9[r0];
        r1 = r1 + 7;
        r72 = r72 | ((r02 & ByteCompanionObject.MAX_VALUE) << r1);
        r0 = r103;
        r102 = r02;
        goto L3
    L5:
        r11.long1 = r72;
        return r0;
    }

    public static int decodeVarint64(byte[] r5, int r6, Registers r7) {
        int r0 = r6 + 1;
        long r1 = r5[r6];
        if (r1 < 0) goto L7;
        r7.long1 = r1;
        return r0;
    L7:
        return decodeVarint64(r1, r5, r0, r7);
    }

    public static int decodeVarint64List(int r2, byte[] r3, int r4, int r5, Internal.ProtobufList<?> r6, Registers r7) {
        LongArrayList r62 = (LongArrayList) r6;
        int r42 = decodeVarint64(r3, r4, r7);
        r62.addLong(r7.long1);
    L3:
        if (r42 >= r5) goto L8;
        int r0 = decodeVarint32(r3, r42, r7);
        if (r2 != r7.int1) goto L8;
        r42 = decodeVarint64(r3, r0, r7);
        r62.addLong(r7.long1);
    L8:
        return r42;
    }

    public static int mergeGroupField(Object r1, Schema r2, byte[] r3, int r4, int r5, int r6, Registers r7) {
        int r12 = ((MessageSchema) r2).parseProto2Message(r1, r3, r4, r5, r6, r7);
        r7.object1 = r1;
        return r12;
    }

    public static int mergeMessageField(Object r6, Schema r7, byte[] r8, int r9, int r10, Registers r11) {
        int r0 = r9 + 1;
        int r92 = r8[r9];
        if (r92 >= 0) goto L5;
        r0 = decodeVarint32(r92, r8, r0, r11);
        r92 = r11.int1;
    L5:
        int r3 = r0;
        if (r92 < 0) goto L12;
        if (r92 > (r10 - r3)) goto L12;
        int r4 = r3 + r92;
        r7.mergeFrom(r6, r8, r3, r4, r11);
        r11.object1 = r6;
        return r4;
    L12:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int skipField(int r2, byte[] r3, int r4, int r5, Registers r6) {
        if (WireFormat.getTagFieldNumber(r2) == 0) goto L36;
        int r0 = WireFormat.getTagWireType(r2);
        if (r0 == 0) goto L34;
        if (r0 == 1) goto L32;
        if (r0 == 2) goto L30;
        if (r0 != 3) goto L13;
        int r22 = (r2 & (-8)) | 4;
        int r02 = 0;
    L19:
        if (r4 >= r5) goto L24;
        r4 = decodeVarint32(r3, r4, r6);
        r02 = r6.int1;
        if (r02 == r22) goto L24;
        r4 = skipField(r02, r3, r4, r5, r6);
    L24:
        if (r4 > r5) goto L28;
        if (r02 != r22) goto L28;
        return r4;
    L28:
        throw InvalidProtocolBufferException.parseFailure();
    L13:
        if (r0 != 5) goto L17;
        return r4 + 4;
    L17:
        throw InvalidProtocolBufferException.invalidTag();
    L30:
        return decodeVarint32(r3, r4, r6) + r6.int1;
    L32:
        return r4 + 8;
    L34:
        return decodeVarint64(r3, r4, r6);
    L36:
        throw InvalidProtocolBufferException.invalidTag();
    }
}
