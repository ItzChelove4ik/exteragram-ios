package com.google.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.android.dx.dex.code.LocalList$$ExternalSyntheticBUOutline0;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int[] EMPTY_INT_ARRAY = null;
    private static final Unsafe UNSAFE = null;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;

    /* renamed from: com.google.protobuf.MessageSchema$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = null;

        static {
            int[] r0 = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0;
            r0[WireFormat.FieldType.BOOL.ordinal()] = 1;     // Catch: NoSuchFieldError -> L21
        L60:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;     // Catch: NoSuchFieldError -> L22
        L44:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L23
        L56:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;     // Catch: NoSuchFieldError -> L24
        L62:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;     // Catch: NoSuchFieldError -> L25
        L46:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;     // Catch: NoSuchFieldError -> L26
        L50:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;     // Catch: NoSuchFieldError -> L27
        L70:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;     // Catch: NoSuchFieldError -> L28
        L40:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;     // Catch: NoSuchFieldError -> L29
        L64:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;     // Catch: NoSuchFieldError -> L30
        L66:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;     // Catch: NoSuchFieldError -> L31
        L52:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;     // Catch: NoSuchFieldError -> L32
        L58:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;     // Catch: NoSuchFieldError -> L33
        L42:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;     // Catch: NoSuchFieldError -> L34
        L48:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;     // Catch: NoSuchFieldError -> L35
        L68:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;     // Catch: NoSuchFieldError -> L36
        L38:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;     // Catch: NoSuchFieldError -> L37
            return;
        }
    }

    static {
        EMPTY_INT_ARRAY = new int[0];
        UNSAFE = UnsafeUtil.getUnsafe();
    }

    private MessageSchema(int[] r1, Object[] r2, int r3, int r4, MessageLite r5, boolean r6, boolean r7, int[] r8, int r9, int r10, NewInstanceSchema r11, ListFieldSchema r12, UnknownFieldSchema<?, ?> r13, ExtensionSchema<?> r14, MapFieldSchema r15) {
        this.buffer = r1;
        this.objects = r2;
        this.minFieldNumber = r3;
        this.maxFieldNumber = r4;
        this.lite = r5 instanceof GeneratedMessageLite;
        this.proto3 = r6;
        if (r14 != null) goto L5;
    L7:
        boolean r16 = false;
    L8:
        this.hasExtensions = r16;
        this.useCachedSizeField = r7;
        this.intArray = r8;
        this.checkInitializedCount = r9;
        this.repeatedFieldOffsetStart = r10;
        this.newInstanceSchema = r11;
        this.listFieldSchema = r12;
        this.unknownFieldSchema = r13;
        this.extensionSchema = r14;
        this.defaultInstance = r5;
        this.mapFieldSchema = r15;
        return;
    L5:
        if (r14.hasExtensions(r5) == false) goto L7;
        r16 = true;
        goto L8
    }

    private boolean arePresentForEquals(T r1, T r2, int r3) {
        if (isFieldPresent(r1, r3) != isFieldPresent(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private static <T> boolean booleanAt(T r0, long r1) {
        return UnsafeUtil.getBoolean(r0, r1);
    }

    private static void checkMutable(Object r1) {
        if (isMutable(r1) == false) goto L5;
        return;
    L5:
        Native$$ExternalSyntheticBUOutline5.m("Mutating immutable message: ", r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] r11, int r12, int r13, MapEntryLite.Metadata<K, V> r14, Map<K, V> r15, ArrayDecoders.Registers r16) {
        int r122 = ArrayDecoders.decodeVarint32(r11, r12, r16);
        int r0 = r16.int1;
        if (r0 < 0) goto L29;
        if (r0 > (r13 - r122)) goto L29;
        int r7 = r122 + r0;
        K r02 = r14.defaultKey;
        Object r8 = r02;
        Object r9 = r14.defaultValue;
    L7:
        if (r122 >= r7) goto L23;
        int r03 = r122 + 1;
        int r123 = r11[r122];
        if (r123 >= 0) goto L11;
        r03 = ArrayDecoders.decodeVarint32(r123, r11, r03, r16);
        r123 = r16.int1;
    L11:
        int r2 = r03;
        int r04 = r123 >>> 3;
        int r1 = r123 & 7;
        if (r04 != 1) goto L14;
        if (r1 != r14.keyType.getWireType()) goto L22;
        r122 = decodeMapEntryValue(r11, r2, r13, r14.keyType, null, r16);
        r8 = r16.object1;
    L22:
        r122 = ArrayDecoders.skipField(r123, r11, r2, r13, r16);
        goto L7
    L14:
        if (r04 != 2) goto L22;
        if (r1 != r14.valueType.getWireType()) goto L22;
        r122 = decodeMapEntryValue(r11, r2, r13, r14.valueType, r14.defaultValue.getClass(), r16);
        r9 = r16.object1;
        goto L7
    L23:
        if (r122 != r7) goto L27;
        r15.put(r8, r9);
        return r7;
    L27:
        throw InvalidProtocolBufferException.parseFailure();
    L29:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    private int decodeMapEntryValue(byte[] r3, int r4, int r5, WireFormat.FieldType r6, Class<?> r7, ArrayDecoders.Registers r8) {
        int r2 = AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r6.ordinal()];
        boolean r62 = false;
        switch(r2) {
            case 1: goto L28;
            case 2: goto L27;
            case 3: goto L24;
            case 4: goto L22;
            case 5: goto L22;
            case 6: goto L20;
            case 7: goto L20;
            case 8: goto L18;
            case 9: goto L16;
            case 10: goto L16;
            case 11: goto L16;
            case 12: goto L14;
            case 13: goto L14;
            case 14: goto L13;
            case 15: goto L10;
            case 16: goto L8;
            case 17: goto L7;
            default: goto L4;
        };
    L4:
        GlShader$$ExternalSyntheticBUOutline1.m("unsupported field type.");
        return 0;
    L8:
        int r22 = ArrayDecoders.decodeVarint64(r3, r4, r8);
        r8.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(r8.long1));
        return r22;
    L10:
        int r23 = ArrayDecoders.decodeVarint32(r3, r4, r8);
        r8.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(r8.int1));
        return r23;
    L14:
        int r24 = ArrayDecoders.decodeVarint64(r3, r4, r8);
        r8.object1 = Long.valueOf(r8.long1);
        return r24;
    L16:
        int r25 = ArrayDecoders.decodeVarint32(r3, r4, r8);
        r8.object1 = Integer.valueOf(r8.int1);
        return r25;
    L18:
        r8.object1 = Float.valueOf(ArrayDecoders.decodeFloat(r3, r4));
        return r4 + 4;
    L20:
        r8.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(r3, r4));
        return r4 + 8;
    L22:
        r8.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(r3, r4));
        return r4 + 4;
    L24:
        r8.object1 = Double.valueOf(ArrayDecoders.decodeDouble(r3, r4));
        return r4 + 8;
    L28:
        int r26 = ArrayDecoders.decodeVarint64(r3, r4, r8);
        if (r8.long1 == 0) goto L31;
        r62 = true;
    L31:
        r8.object1 = Boolean.valueOf(r62);
        return r26;
    L7:
        return ArrayDecoders.decodeStringRequireUtf8(r3, r4, r8);
    L13:
        return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(r7), r3, r4, r5, r8);
    L27:
        return ArrayDecoders.decodeBytes(r3, r4, r8);
    }

    private static <T> double doubleAt(T r0, long r1) {
        return UnsafeUtil.getDouble(r0, r1);
    }

    private boolean equals(T r6, T r7, int r8) {
        int r0 = typeAndOffsetAt(r8);
        long r1 = offset(r0);
        switch(type(r0)) {
            case 0: goto L118;
            case 1: goto L112;
            case 2: goto L106;
            case 3: goto L100;
            case 4: goto L94;
            case 5: goto L88;
            case 6: goto L82;
            case 7: goto L76;
            case 8: goto L70;
            case 9: goto L64;
            case 10: goto L58;
            case 11: goto L52;
            case 12: goto L46;
            case 13: goto L40;
            case 14: goto L34;
            case 15: goto L28;
            case 16: goto L22;
            case 17: goto L16;
            case 18: goto L14;
            case 19: goto L14;
            case 20: goto L14;
            case 21: goto L14;
            case 22: goto L14;
            case 23: goto L14;
            case 24: goto L14;
            case 25: goto L14;
            case 26: goto L14;
            case 27: goto L14;
            case 28: goto L14;
            case 29: goto L14;
            case 30: goto L14;
            case 31: goto L14;
            case 32: goto L14;
            case 33: goto L14;
            case 34: goto L14;
            case 35: goto L14;
            case 36: goto L14;
            case 37: goto L14;
            case 38: goto L14;
            case 39: goto L14;
            case 40: goto L14;
            case 41: goto L14;
            case 42: goto L14;
            case 43: goto L14;
            case 44: goto L14;
            case 45: goto L14;
            case 46: goto L14;
            case 47: goto L14;
            case 48: goto L14;
            case 49: goto L14;
            case 50: goto L12;
            case 51: goto L6;
            case 52: goto L6;
            case 53: goto L6;
            case 54: goto L6;
            case 55: goto L6;
            case 56: goto L6;
            case 57: goto L6;
            case 58: goto L6;
            case 59: goto L6;
            case 60: goto L6;
            case 61: goto L6;
            case 62: goto L6;
            case 63: goto L6;
            case 64: goto L6;
            case 65: goto L6;
            case 66: goto L6;
            case 67: goto L6;
            case 68: goto L6;
            default: goto L4;
        };
    L4:
        return true;
    L6:
        if (isOneofCaseEqual(r6, r7, r8) == true) goto L8;
    L10:
        return false;
    L8:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L10;
        return true;
    L12:
        return SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1));
    L14:
        return SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1));
    L16:
        if (arePresentForEquals(r6, r7, r8) == true) goto L18;
    L20:
        return false;
    L18:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L20;
        return true;
    L22:
        if (arePresentForEquals(r6, r7, r8) == true) goto L24;
    L26:
        return false;
    L24:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L26;
        return true;
    L28:
        if (arePresentForEquals(r6, r7, r8) == true) goto L30;
    L32:
        return false;
    L30:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L32;
        return true;
    L34:
        if (arePresentForEquals(r6, r7, r8) == true) goto L36;
    L38:
        return false;
    L36:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L38;
        return true;
    L40:
        if (arePresentForEquals(r6, r7, r8) == true) goto L42;
    L44:
        return false;
    L42:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L44;
        return true;
    L46:
        if (arePresentForEquals(r6, r7, r8) == true) goto L48;
    L50:
        return false;
    L48:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L50;
        return true;
    L52:
        if (arePresentForEquals(r6, r7, r8) == true) goto L54;
    L56:
        return false;
    L54:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L56;
        return true;
    L58:
        if (arePresentForEquals(r6, r7, r8) == true) goto L60;
    L62:
        return false;
    L60:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L62;
        return true;
    L64:
        if (arePresentForEquals(r6, r7, r8) == true) goto L66;
    L68:
        return false;
    L66:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L68;
        return true;
    L70:
        if (arePresentForEquals(r6, r7, r8) == true) goto L72;
    L74:
        return false;
    L72:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L74;
        return true;
    L76:
        if (arePresentForEquals(r6, r7, r8) == true) goto L78;
    L80:
        return false;
    L78:
        if (UnsafeUtil.getBoolean(r6, r1) != UnsafeUtil.getBoolean(r7, r1)) goto L80;
        return true;
    L82:
        if (arePresentForEquals(r6, r7, r8) == true) goto L84;
    L86:
        return false;
    L84:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L86;
        return true;
    L88:
        if (arePresentForEquals(r6, r7, r8) == true) goto L90;
    L92:
        return false;
    L90:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L92;
        return true;
    L94:
        if (arePresentForEquals(r6, r7, r8) == true) goto L96;
    L98:
        return false;
    L96:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L98;
        return true;
    L100:
        if (arePresentForEquals(r6, r7, r8) == true) goto L102;
    L104:
        return false;
    L102:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L104;
        return true;
    L106:
        if (arePresentForEquals(r6, r7, r8) == true) goto L108;
    L110:
        return false;
    L108:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L110;
        return true;
    L112:
        if (arePresentForEquals(r6, r7, r8) == true) goto L114;
    L116:
        return false;
    L114:
        if (Float.floatToIntBits(UnsafeUtil.getFloat(r6, r1)) != Float.floatToIntBits(UnsafeUtil.getFloat(r7, r1))) goto L116;
        return true;
    L118:
        if (arePresentForEquals(r6, r7, r8) == true) goto L120;
    L122:
        return false;
    L120:
        if (Double.doubleToLongBits(UnsafeUtil.getDouble(r6, r1)) != Double.doubleToLongBits(UnsafeUtil.getDouble(r7, r1))) goto L122;
        return true;
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object r1, int r2, UB r3, UnknownFieldSchema<UT, UB> r4, Object r5) {
        numberAt(r2);
        if (UnsafeUtil.getObject(r1, offset(typeAndOffsetAt(r2))) != null) goto L5;
        return r3;
    L5:
        getEnumFieldVerifier(r2);
        return r3;
    }

    private static <T> float floatAt(T r0, long r1) {
        return UnsafeUtil.getFloat(r0, r1);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.objects[((r1 / 3) * 2) + 1]);
        return null;
    }

    private Object getMapFieldDefaultEntry(int r1) {
        return this.objects[(r1 / 3) * 2];
    }

    private Schema getMessageFieldSchema(int r4) {
        int r42 = (r4 / 3) * 2;
        Schema r0 = (Schema) this.objects[r42];
        if (r0 == null) goto L5;
        return r0;
    L5:
        Schema<T> r02 = Protobuf.getInstance().schemaFor((Class) this.objects[r42 + 1]);
        this.objects[r42] = r02;
        return r02;
    }

    public static UnknownFieldSetLite getMutableUnknownFields(Object r2) {
        GeneratedMessageLite r22 = (GeneratedMessageLite) r2;
        UnknownFieldSetLite r0 = r22.unknownFields;
        if (r0 != UnknownFieldSetLite.getDefaultInstance()) goto L6;
        UnknownFieldSetLite r02 = UnknownFieldSetLite.newInstance();
        r22.unknownFields = r02;
        return r02;
    L6:
        return r0;
    }

    private int getSerializedSizeProto2(T r17) {
        Unsafe r2 = UNSAFE;
        int r4 = 1048575;
        int r7 = 1048575;
        int r5 = 0;
        int r6 = 0;
        int r8 = 0;
    L4:
        if (r5 >= this.buffer.length) goto L245;
        int r9 = typeAndOffsetAt(r5);
        int r10 = numberAt(r5);
        int r11 = type(r9);
        if (r11 > 17) goto L11;
        int r12 = this.buffer[r5 + 2];
        int r14 = r12 & r4;
        int r15 = 1 << (r12 >>> 20);
        if (r14 == r7) goto L19;
        r8 = r2.getInt(r17, r14);
        r7 = r14;
    L19:
        long r13 = offset(r9);
        switch(r11) {
            case 0: goto L242;
            case 1: goto L239;
            case 2: goto L236;
            case 3: goto L233;
            case 4: goto L230;
            case 5: goto L227;
            case 6: goto L223;
            case 7: goto L220;
            case 8: goto L214;
            case 9: goto L211;
            case 10: goto L208;
            case 11: goto L205;
            case 12: goto L202;
            case 13: goto L199;
            case 14: goto L196;
            case 15: goto L193;
            case 16: goto L190;
            case 17: goto L187;
            case 18: goto L185;
            case 19: goto L184;
            case 20: goto L183;
            case 21: goto L182;
            case 22: goto L181;
            case 23: goto L180;
            case 24: goto L179;
            case 25: goto L178;
            case 26: goto L177;
            case 27: goto L176;
            case 28: goto L175;
            case 29: goto L174;
            case 30: goto L173;
            case 31: goto L172;
            case 32: goto L171;
            case 33: goto L170;
            case 34: goto L168;
            case 35: goto L162;
            case 36: goto L156;
            case 37: goto L150;
            case 38: goto L144;
            case 39: goto L138;
            case 40: goto L132;
            case 41: goto L126;
            case 42: goto L120;
            case 43: goto L114;
            case 44: goto L108;
            case 45: goto L102;
            case 46: goto L96;
            case 47: goto L90;
            case 48: goto L83;
            case 49: goto L82;
            case 50: goto L81;
            case 51: goto L79;
            case 52: goto L76;
            case 53: goto L73;
            case 54: goto L70;
            case 55: goto L67;
            case 56: goto L64;
            case 57: goto L61;
            case 58: goto L58;
            case 59: goto L52;
            case 60: goto L49;
            case 61: goto L46;
            case 62: goto L43;
            case 63: goto L40;
            case 64: goto L37;
            case 65: goto L34;
            case 66: goto L31;
            case 67: goto L28;
            case 68: goto L23;
            default: goto L244;
        };
    L81:
        int r3 = this.mapFieldSchema.getSerializedSize(r10, r2.getObject(r17, r13), getMapFieldDefaultEntry(r5));
    L25:
        r6 = r6 + r3;
        goto L244
    L82:
        r3 = SchemaUtil.computeSizeGroupList(r10, (List) r2.getObject(r17, r13), getMessageFieldSchema(r5));
        goto L25
    L83:
        int r32 = SchemaUtil.computeSizeSInt64ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L88;
        r2.putInt(r17, r12, r32);
    L88:
        int r42 = CodedOutputStream.computeTagSize(r10);
        int r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
    L89:
        r6 = r6 + ((r42 + r92) + r32);
        goto L244
    L90:
        r32 = SchemaUtil.computeSizeSInt32ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L95;
        r2.putInt(r17, r12, r32);
    L95:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L96:
        r32 = SchemaUtil.computeSizeFixed64ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L101;
        r2.putInt(r17, r12, r32);
    L101:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L102:
        r32 = SchemaUtil.computeSizeFixed32ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L107;
        r2.putInt(r17, r12, r32);
    L107:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L108:
        r32 = SchemaUtil.computeSizeEnumListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L113;
        r2.putInt(r17, r12, r32);
    L113:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L114:
        r32 = SchemaUtil.computeSizeUInt32ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L119;
        r2.putInt(r17, r12, r32);
    L119:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L120:
        r32 = SchemaUtil.computeSizeBoolListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L125;
        r2.putInt(r17, r12, r32);
    L125:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L126:
        r32 = SchemaUtil.computeSizeFixed32ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L131;
        r2.putInt(r17, r12, r32);
    L131:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L132:
        r32 = SchemaUtil.computeSizeFixed64ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L137;
        r2.putInt(r17, r12, r32);
    L137:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L138:
        r32 = SchemaUtil.computeSizeInt32ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L143;
        r2.putInt(r17, r12, r32);
    L143:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L144:
        r32 = SchemaUtil.computeSizeUInt64ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L149;
        r2.putInt(r17, r12, r32);
    L149:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L150:
        r32 = SchemaUtil.computeSizeInt64ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L155;
        r2.putInt(r17, r12, r32);
    L155:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L156:
        r32 = SchemaUtil.computeSizeFixed32ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L161;
        r2.putInt(r17, r12, r32);
    L161:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L162:
        r32 = SchemaUtil.computeSizeFixed64ListNoTag((List) r2.getObject(r17, r13));
        if (r32 <= 0) goto L244;
        if (this.useCachedSizeField == false) goto L167;
        r2.putInt(r17, r12, r32);
    L167:
        r42 = CodedOutputStream.computeTagSize(r10);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r32);
        goto L89
    L168:
        boolean r93 = false;
        int r33 = SchemaUtil.computeSizeSInt64List(r10, (List) r2.getObject(r17, r13), false);
    L169:
        r6 = r6 + r33;
        goto L244
    L170:
        r93 = false;
        r33 = SchemaUtil.computeSizeSInt32List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L171:
        r93 = false;
        r33 = SchemaUtil.computeSizeFixed64List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L172:
        r93 = false;
        r33 = SchemaUtil.computeSizeFixed32List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L173:
        r93 = false;
        r33 = SchemaUtil.computeSizeEnumList(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L174:
        r3 = SchemaUtil.computeSizeUInt32List(r10, (List) r2.getObject(r17, r13), false);
        goto L25
    L175:
        r3 = SchemaUtil.computeSizeByteStringList(r10, (List) r2.getObject(r17, r13));
        goto L25
    L176:
        r3 = SchemaUtil.computeSizeMessageList(r10, (List) r2.getObject(r17, r13), getMessageFieldSchema(r5));
        goto L25
    L177:
        r3 = SchemaUtil.computeSizeStringList(r10, (List) r2.getObject(r17, r13));
        goto L25
    L178:
        r93 = false;
        r33 = SchemaUtil.computeSizeBoolList(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L179:
        r93 = false;
        r33 = SchemaUtil.computeSizeFixed32List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L180:
        r93 = false;
        r33 = SchemaUtil.computeSizeFixed64List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L181:
        r93 = false;
        r33 = SchemaUtil.computeSizeInt32List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L182:
        r93 = false;
        r33 = SchemaUtil.computeSizeUInt64List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L183:
        r93 = false;
        r33 = SchemaUtil.computeSizeInt64List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L184:
        r93 = false;
        r33 = SchemaUtil.computeSizeFixed32List(r10, (List) r2.getObject(r17, r13), false);
        goto L169
    L185:
        r3 = SchemaUtil.computeSizeFixed64List(r10, (List) r2.getObject(r17, r13), false);
        goto L25
    L23:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeGroupSize(r10, (MessageLite) r2.getObject(r17, r13), getMessageFieldSchema(r5));
        goto L25
    L28:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeSInt64Size(r10, oneofLongAt(r17, r13));
        goto L25
    L31:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeSInt32Size(r10, oneofIntAt(r17, r13));
        goto L25
    L34:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeSFixed64Size(r10, 0);
        goto L25
    L37:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeSFixed32Size(r10, 0);
        goto L25
    L40:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeEnumSize(r10, oneofIntAt(r17, r13));
        goto L25
    L43:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeUInt32Size(r10, oneofIntAt(r17, r13));
        goto L25
    L46:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeBytesSize(r10, (ByteString) r2.getObject(r17, r13));
        goto L25
    L49:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = SchemaUtil.computeSizeMessage(r10, r2.getObject(r17, r13), getMessageFieldSchema(r5));
        goto L25
    L52:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        Object r34 = r2.getObject(r17, r13);
        if ((r34 instanceof ByteString) == false) goto L56;
        r3 = CodedOutputStream.computeBytesSize(r10, (ByteString) r34);
        goto L25
    L56:
        r3 = CodedOutputStream.computeStringSize(r10, (String) r34);
        goto L25
    L58:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeBoolSize(r10, true);
        goto L25
    L61:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeFixed32Size(r10, 0);
        goto L25
    L64:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeFixed64Size(r10, 0);
        goto L25
    L67:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeInt32Size(r10, oneofIntAt(r17, r13));
        goto L25
    L70:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeUInt64Size(r10, oneofLongAt(r17, r13));
        goto L25
    L73:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeInt64Size(r10, oneofLongAt(r17, r13));
        goto L25
    L76:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeFloatSize(r10, 0.0f);
        goto L25
    L79:
        if (isOneofPresent(r17, r10, r5) == false) goto L244;
        r3 = CodedOutputStream.computeDoubleSize(r10, 0.0d);
        goto L25
    L187:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeGroupSize(r10, (MessageLite) r2.getObject(r17, r13), getMessageFieldSchema(r5));
        goto L25
    L190:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeSInt64Size(r10, r2.getLong(r17, r13));
        goto L25
    L193:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeSInt32Size(r10, r2.getInt(r17, r13));
        goto L25
    L196:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeSFixed64Size(r10, 0);
        goto L25
    L199:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeSFixed32Size(r10, 0);
        goto L25
    L202:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeEnumSize(r10, r2.getInt(r17, r13));
        goto L25
    L205:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeUInt32Size(r10, r2.getInt(r17, r13));
        goto L25
    L208:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeBytesSize(r10, (ByteString) r2.getObject(r17, r13));
        goto L25
    L211:
        if ((r8 & r15) == 0) goto L244;
        r3 = SchemaUtil.computeSizeMessage(r10, r2.getObject(r17, r13), getMessageFieldSchema(r5));
        goto L25
    L214:
        if ((r8 & r15) == 0) goto L244;
        Object r35 = r2.getObject(r17, r13);
        if ((r35 instanceof ByteString) == false) goto L218;
        r3 = CodedOutputStream.computeBytesSize(r10, (ByteString) r35);
        goto L25
    L218:
        r3 = CodedOutputStream.computeStringSize(r10, (String) r35);
        goto L25
    L220:
        if ((r8 & r15) == 0) goto L244;
        r3 = CodedOutputStream.computeBoolSize(r10, true);
        goto L25
    L223:
        if ((r8 & r15) == 0) goto L244;
        int r36 = CodedOutputStream.computeFixed32Size(r10, 0);
    L225:
        r6 = r6 + r36;
        goto L244
    L227:
        if ((r8 & r15) == 0) goto L244;
        r36 = CodedOutputStream.computeFixed64Size(r10, 0);
        goto L225
    L230:
        if ((r8 & r15) == 0) goto L244;
        r36 = CodedOutputStream.computeInt32Size(r10, r2.getInt(r17, r13));
        goto L225
    L233:
        if ((r8 & r15) == 0) goto L244;
        r36 = CodedOutputStream.computeUInt64Size(r10, r2.getLong(r17, r13));
        goto L225
    L236:
        if ((r8 & r15) == 0) goto L244;
        r36 = CodedOutputStream.computeInt64Size(r10, r2.getLong(r17, r13));
        goto L225
    L239:
        if ((r8 & r15) == 0) goto L244;
        r36 = CodedOutputStream.computeFloatSize(r10, 0.0f);
        goto L225
    L242:
        if ((r8 & r15) == 0) goto L244;
        r36 = CodedOutputStream.computeDoubleSize(r10, 0.0d);
    L244:
        r5 = r5 + 3;
        r4 = 1048575;
        goto L4
    L11:
        if (this.useCachedSizeField == true) goto L13;
    L18:
        r12 = 0;
    L17:
        r15 = 0;
        goto L19
    L13:
        if (r11 < FieldType.DOUBLE_LIST_PACKED.id()) goto L18;
        if (r11 > FieldType.SINT64_LIST_PACKED.id()) goto L18;
        r12 = this.buffer[r5 + 2] & r4;
        goto L17
    L245:
        int r62 = r6 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, r17);
        if (this.hasExtensions == true) goto L248;
        return r62;
    L248:
        return r62 + this.extensionSchema.getExtensions(r17).getSerializedSize();
    }

    private int getSerializedSizeProto3(T r16) {
        Unsafe r2 = UNSAFE;
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r4 >= this.buffer.length) goto L235;
        int r6 = typeAndOffsetAt(r4);
        int r7 = type(r6);
        int r8 = numberAt(r4);
        long r9 = offset(r6);
        if (r7 >= FieldType.DOUBLE_LIST_PACKED.id()) goto L8;
    L10:
        int r62 = 0;
    L12:
        switch(r7) {
            case 0: goto L231;
            case 1: goto L228;
            case 2: goto L225;
            case 3: goto L222;
            case 4: goto L219;
            case 5: goto L216;
            case 6: goto L213;
            case 7: goto L210;
            case 8: goto L204;
            case 9: goto L201;
            case 10: goto L198;
            case 11: goto L195;
            case 12: goto L192;
            case 13: goto L189;
            case 14: goto L186;
            case 15: goto L183;
            case 16: goto L180;
            case 17: goto L177;
            case 18: goto L175;
            case 19: goto L174;
            case 20: goto L173;
            case 21: goto L172;
            case 22: goto L171;
            case 23: goto L170;
            case 24: goto L169;
            case 25: goto L168;
            case 26: goto L167;
            case 27: goto L166;
            case 28: goto L165;
            case 29: goto L164;
            case 30: goto L163;
            case 31: goto L162;
            case 32: goto L161;
            case 33: goto L160;
            case 34: goto L159;
            case 35: goto L153;
            case 36: goto L147;
            case 37: goto L141;
            case 38: goto L135;
            case 39: goto L129;
            case 40: goto L123;
            case 41: goto L117;
            case 42: goto L111;
            case 43: goto L105;
            case 44: goto L99;
            case 45: goto L93;
            case 46: goto L87;
            case 47: goto L81;
            case 48: goto L74;
            case 49: goto L73;
            case 50: goto L72;
            case 51: goto L70;
            case 52: goto L67;
            case 53: goto L64;
            case 54: goto L61;
            case 55: goto L58;
            case 56: goto L55;
            case 57: goto L52;
            case 58: goto L49;
            case 59: goto L43;
            case 60: goto L40;
            case 61: goto L37;
            case 62: goto L34;
            case 63: goto L31;
            case 64: goto L28;
            case 65: goto L25;
            case 66: goto L22;
            case 67: goto L19;
            case 68: goto L15;
            default: goto L233;
        };
    L72:
        int r63 = this.mapFieldSchema.getSerializedSize(r8, UnsafeUtil.getObject(r16, r9), getMapFieldDefaultEntry(r4));
    L17:
        r5 = r5 + r63;
        goto L233
    L73:
        r63 = SchemaUtil.computeSizeGroupList(r8, listAt(r16, r9), getMessageFieldSchema(r4));
        goto L17
    L74:
        int r72 = SchemaUtil.computeSizeSInt64ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L79;
        r2.putInt(r16, r62, r72);
    L79:
        int r64 = CodedOutputStream.computeTagSize(r8);
        int r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
    L80:
        r63 = (r64 + r82) + r72;
        goto L17
    L81:
        r72 = SchemaUtil.computeSizeSInt32ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L86;
        r2.putInt(r16, r62, r72);
    L86:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L87:
        r72 = SchemaUtil.computeSizeFixed64ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L92;
        r2.putInt(r16, r62, r72);
    L92:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L93:
        r72 = SchemaUtil.computeSizeFixed32ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L98;
        r2.putInt(r16, r62, r72);
    L98:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L99:
        r72 = SchemaUtil.computeSizeEnumListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L104;
        r2.putInt(r16, r62, r72);
    L104:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L105:
        r72 = SchemaUtil.computeSizeUInt32ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L110;
        r2.putInt(r16, r62, r72);
    L110:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L111:
        r72 = SchemaUtil.computeSizeBoolListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L116;
        r2.putInt(r16, r62, r72);
    L116:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L117:
        r72 = SchemaUtil.computeSizeFixed32ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L122;
        r2.putInt(r16, r62, r72);
    L122:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L123:
        r72 = SchemaUtil.computeSizeFixed64ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L128;
        r2.putInt(r16, r62, r72);
    L128:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L129:
        r72 = SchemaUtil.computeSizeInt32ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L134;
        r2.putInt(r16, r62, r72);
    L134:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L135:
        r72 = SchemaUtil.computeSizeUInt64ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L140;
        r2.putInt(r16, r62, r72);
    L140:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L141:
        r72 = SchemaUtil.computeSizeInt64ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L146;
        r2.putInt(r16, r62, r72);
    L146:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L147:
        r72 = SchemaUtil.computeSizeFixed32ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L152;
        r2.putInt(r16, r62, r72);
    L152:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L153:
        r72 = SchemaUtil.computeSizeFixed64ListNoTag((List) r2.getObject(r16, r9));
        if (r72 <= 0) goto L233;
        if (this.useCachedSizeField == false) goto L158;
        r2.putInt(r16, r62, r72);
    L158:
        r64 = CodedOutputStream.computeTagSize(r8);
        r82 = CodedOutputStream.computeUInt32SizeNoTag(r72);
        goto L80
    L159:
        r63 = SchemaUtil.computeSizeSInt64List(r8, listAt(r16, r9), false);
        goto L17
    L160:
        r63 = SchemaUtil.computeSizeSInt32List(r8, listAt(r16, r9), false);
        goto L17
    L161:
        r63 = SchemaUtil.computeSizeFixed64List(r8, listAt(r16, r9), false);
        goto L17
    L162:
        r63 = SchemaUtil.computeSizeFixed32List(r8, listAt(r16, r9), false);
        goto L17
    L163:
        r63 = SchemaUtil.computeSizeEnumList(r8, listAt(r16, r9), false);
        goto L17
    L164:
        r63 = SchemaUtil.computeSizeUInt32List(r8, listAt(r16, r9), false);
        goto L17
    L165:
        r63 = SchemaUtil.computeSizeByteStringList(r8, listAt(r16, r9));
        goto L17
    L166:
        r63 = SchemaUtil.computeSizeMessageList(r8, listAt(r16, r9), getMessageFieldSchema(r4));
        goto L17
    L167:
        r63 = SchemaUtil.computeSizeStringList(r8, listAt(r16, r9));
        goto L17
    L168:
        r63 = SchemaUtil.computeSizeBoolList(r8, listAt(r16, r9), false);
        goto L17
    L169:
        r63 = SchemaUtil.computeSizeFixed32List(r8, listAt(r16, r9), false);
        goto L17
    L170:
        r63 = SchemaUtil.computeSizeFixed64List(r8, listAt(r16, r9), false);
        goto L17
    L171:
        r63 = SchemaUtil.computeSizeInt32List(r8, listAt(r16, r9), false);
        goto L17
    L172:
        r63 = SchemaUtil.computeSizeUInt64List(r8, listAt(r16, r9), false);
        goto L17
    L173:
        r63 = SchemaUtil.computeSizeInt64List(r8, listAt(r16, r9), false);
        goto L17
    L174:
        r63 = SchemaUtil.computeSizeFixed32List(r8, listAt(r16, r9), false);
        goto L17
    L175:
        r63 = SchemaUtil.computeSizeFixed64List(r8, listAt(r16, r9), false);
        goto L17
    L15:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeGroupSize(r8, (MessageLite) UnsafeUtil.getObject(r16, r9), getMessageFieldSchema(r4));
        goto L17
    L19:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSInt64Size(r8, oneofLongAt(r16, r9));
        goto L17
    L22:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSInt32Size(r8, oneofIntAt(r16, r9));
        goto L17
    L25:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSFixed64Size(r8, 0);
        goto L17
    L28:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSFixed32Size(r8, 0);
        goto L17
    L31:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeEnumSize(r8, oneofIntAt(r16, r9));
        goto L17
    L34:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeUInt32Size(r8, oneofIntAt(r16, r9));
        goto L17
    L37:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeBytesSize(r8, (ByteString) UnsafeUtil.getObject(r16, r9));
        goto L17
    L40:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = SchemaUtil.computeSizeMessage(r8, UnsafeUtil.getObject(r16, r9), getMessageFieldSchema(r4));
        goto L17
    L43:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        Object r65 = UnsafeUtil.getObject(r16, r9);
        if ((r65 instanceof ByteString) == false) goto L47;
        r63 = CodedOutputStream.computeBytesSize(r8, (ByteString) r65);
        goto L17
    L47:
        r63 = CodedOutputStream.computeStringSize(r8, (String) r65);
        goto L17
    L49:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeBoolSize(r8, true);
        goto L17
    L52:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeFixed32Size(r8, 0);
        goto L17
    L55:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeFixed64Size(r8, 0);
        goto L17
    L58:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeInt32Size(r8, oneofIntAt(r16, r9));
        goto L17
    L61:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeUInt64Size(r8, oneofLongAt(r16, r9));
        goto L17
    L64:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeInt64Size(r8, oneofLongAt(r16, r9));
        goto L17
    L67:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeFloatSize(r8, 0.0f);
        goto L17
    L70:
        if (isOneofPresent(r16, r8, r4) == false) goto L233;
        r63 = CodedOutputStream.computeDoubleSize(r8, 0.0d);
        goto L17
    L177:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeGroupSize(r8, (MessageLite) UnsafeUtil.getObject(r16, r9), getMessageFieldSchema(r4));
        goto L17
    L180:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSInt64Size(r8, UnsafeUtil.getLong(r16, r9));
        goto L17
    L183:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSInt32Size(r8, UnsafeUtil.getInt(r16, r9));
        goto L17
    L186:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSFixed64Size(r8, 0);
        goto L17
    L189:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeSFixed32Size(r8, 0);
        goto L17
    L192:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeEnumSize(r8, UnsafeUtil.getInt(r16, r9));
        goto L17
    L195:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeUInt32Size(r8, UnsafeUtil.getInt(r16, r9));
        goto L17
    L198:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeBytesSize(r8, (ByteString) UnsafeUtil.getObject(r16, r9));
        goto L17
    L201:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = SchemaUtil.computeSizeMessage(r8, UnsafeUtil.getObject(r16, r9), getMessageFieldSchema(r4));
        goto L17
    L204:
        if (isFieldPresent(r16, r4) == false) goto L233;
        Object r66 = UnsafeUtil.getObject(r16, r9);
        if ((r66 instanceof ByteString) == false) goto L208;
        r63 = CodedOutputStream.computeBytesSize(r8, (ByteString) r66);
        goto L17
    L208:
        r63 = CodedOutputStream.computeStringSize(r8, (String) r66);
        goto L17
    L210:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeBoolSize(r8, true);
        goto L17
    L213:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeFixed32Size(r8, 0);
        goto L17
    L216:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeFixed64Size(r8, 0);
        goto L17
    L219:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeInt32Size(r8, UnsafeUtil.getInt(r16, r9));
        goto L17
    L222:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeUInt64Size(r8, UnsafeUtil.getLong(r16, r9));
        goto L17
    L225:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeInt64Size(r8, UnsafeUtil.getLong(r16, r9));
        goto L17
    L228:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeFloatSize(r8, 0.0f);
        goto L17
    L231:
        if (isFieldPresent(r16, r4) == false) goto L233;
        r63 = CodedOutputStream.computeDoubleSize(r8, 0.0d);
    L233:
        r4 = r4 + 3;
        goto L4
    L8:
        if (r7 > FieldType.SINT64_LIST_PACKED.id()) goto L10;
        r62 = this.buffer[r4 + 2] & 1048575;
        goto L12
    L235:
        return r5 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, r16);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> r1, T r2) {
        return r1.getSerializedSize(r1.getFromMessage(r2));
    }

    private static <T> int intAt(T r0, long r1) {
        return UnsafeUtil.getInt(r0, r1);
    }

    private static boolean isEnforceUtf8(int r1) {
        if ((r1 & 536870912) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isFieldPresent(T r7, int r8) {
        int r0 = presenceMaskAndOffsetAt(r8);
        long r1 = 1048575 & r0;
        if (r1 != 1048575) goto L82;
        int r6 = typeAndOffsetAt(r8);
        long r02 = offset(r6);
        switch(type(r6)) {
            case 0: goto L78;
            case 1: goto L74;
            case 2: goto L70;
            case 3: goto L66;
            case 4: goto L62;
            case 5: goto L58;
            case 6: goto L54;
            case 7: goto L52;
            case 8: goto L43;
            case 9: goto L40;
            case 10: goto L36;
            case 11: goto L33;
            case 12: goto L29;
            case 13: goto L25;
            case 14: goto L21;
            case 15: goto L17;
            case 16: goto L13;
            case 17: goto L9;
            default: goto L6;
        };
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L36:
        boolean r62 = ByteString.EMPTY.equals(UnsafeUtil.getObject(r7, r02));
    L38:
        return !r62;
    L43:
        Object r63 = UnsafeUtil.getObject(r7, r02);
        if ((r63 instanceof String) == false) goto L47;
        r62 = ((String) r63).isEmpty();
        goto L38
    L47:
        if ((r63 instanceof ByteString) == false) goto L49;
        r62 = ByteString.EMPTY.equals(r63);
        goto L38
    L49:
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L9:
        if (UnsafeUtil.getObject(r7, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L40:
        if (UnsafeUtil.getObject(r7, r02) == null) goto L42;
        return true;
    L42:
        return false;
    L52:
        return UnsafeUtil.getBoolean(r7, r02);
    L54:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L56;
        return true;
    L56:
        return false;
    L58:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L60;
        return true;
    L60:
        return false;
    L62:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L64;
        return true;
    L64:
        return false;
    L66:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L68;
        return true;
    L68:
        return false;
    L70:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L72;
        return true;
    L72:
        return false;
    L74:
        if (Float.floatToRawIntBits(UnsafeUtil.getFloat(r7, r02)) == 0) goto L76;
        return true;
    L76:
        return false;
    L78:
        if (Double.doubleToRawLongBits(UnsafeUtil.getDouble(r7, r02)) == 0) goto L80;
        return true;
    L80:
        return false;
    L82:
        if (((1 << (r0 >>> 20)) & UnsafeUtil.getInt(r7, r1)) == 0) goto L84;
        return true;
    L84:
        return false;
    }

    private boolean isFieldPresent(T r2, int r3, int r4, int r5, int r6) {
        if (r4 != 1048575) goto L7;
        return isFieldPresent(r2, r3);
    L7:
        if ((r5 & r6) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object r2, int r3, Schema r4) {
        return r4.isInitialized(UnsafeUtil.getObject(r2, offset(r3)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object r3, int r4, int r5) {
        List r32 = (List) UnsafeUtil.getObject(r3, offset(r4));
        if (r32.isEmpty() == false) goto L5;
        return true;
    L5:
        Schema r2 = getMessageFieldSchema(r5);
        int r52 = 0;
    L7:
        if (r52 >= r32.size()) goto L12;
        if (r2.isInitialized(r32.get(r52)) == false) goto L10;
        r52 = r52 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    private boolean isMapInitialized(T r4, int r5, int r6) {
        Map<?, ?> r42 = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(r4, offset(r5)));
        if (r42.isEmpty() == false) goto L5;
        return true;
    L5:
        Object r52 = getMapFieldDefaultEntry(r6);
        if (this.mapFieldSchema.forMapMetadata(r52).valueType.getJavaType() == WireFormat.JavaType.MESSAGE) goto L8;
        return true;
    L8:
        Iterator<?> r3 = r42.values().iterator();
        ?? r43 = 0;
    L10:
        if (r3.hasNext() == false) goto L18;
        Object r53 = r3.next();
        r43 = r43;
        if (r43 != 0) goto L15;
        r43 = Protobuf.getInstance().schemaFor(r53.getClass());
    L15:
        if (r43.isInitialized(r53) == true) goto L10;
        return false;
    L18:
        return true;
    }

    private static boolean isMutable(Object r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        if ((r1 instanceof GeneratedMessageLite) == true) goto L8;
        return true;
    L8:
        return ((GeneratedMessageLite) r1).isMutable();
    }

    private boolean isOneofCaseEqual(T r3, T r4, int r5) {
        long r0 = presenceMaskAndOffsetAt(r5) & 1048575;
        if (UnsafeUtil.getInt(r3, r0) != UnsafeUtil.getInt(r4, r0)) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isOneofPresent(T r3, int r4, int r5) {
        if (UnsafeUtil.getInt(r3, presenceMaskAndOffsetAt(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isRequired(int r1) {
        if ((r1 & 268435456) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static List<?> listAt(Object r0, long r1) {
        return (List) UnsafeUtil.getObject(r0, r1);
    }

    private static <T> long longAt(T r0, long r1) {
        return UnsafeUtil.getLong(r0, r1);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB> r17, com.google.protobuf.ExtensionSchema<ET> r18, T r19, com.google.protobuf.Reader r20, com.google.protobuf.ExtensionRegistryLite r21) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0000: MOVE (r1v0 ?? I:??[OBJECT, ARRAY]) = (r16v0 'this' ?? I:??[OBJECT, ARRAY] A[IMMUTABLE_TYPE, THIS]) A[DECLARE_VAR] in method: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB>, com.google.protobuf.ExtensionSchema<ET extends com.google.protobuf.FieldSet$FieldDescriptorLite<ET>>, T, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void, file: classes5.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 1998
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, java.lang.Object, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void");
    }

    private final <K, V> void mergeMap(Object r5, int r6, Object r7, ExtensionRegistryLite r8, Reader r9) {
        long r0 = offset(typeAndOffsetAt(r6));
        Object r62 = UnsafeUtil.getObject(r5, r0);
        MapFieldSchema r2 = this.mapFieldSchema;
        if (r62 != null) goto L6;
        r62 = r2.newMapField(r7);
        UnsafeUtil.putObject(r5, r0, r62);
    L8:
        r9.readMap(this.mapFieldSchema.forMutableMapData(r62), this.mapFieldSchema.forMapMetadata(r7), r8);
        return;
    L6:
        if (r2.isImmutable(r62) == false) goto L8;
        Object r22 = this.mapFieldSchema.newMapField(r7);
        this.mapFieldSchema.mergeFrom(r22, r62);
        UnsafeUtil.putObject(r5, r0, r22);
        r62 = r22;
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T r6, T r7, int r8) {
        if (isFieldPresent(r7, r8) == true) goto L5;
        return;
    L5:
        long r0 = offset(typeAndOffsetAt(r8));
        Unsafe r2 = UNSAFE;
        Object r3 = r2.getObject(r7, r0);
        if (r3 == null) goto L20;
        Schema r72 = getMessageFieldSchema(r8);
        if (isFieldPresent(r6, r8) == false) goto L10;
        Object r5 = r2.getObject(r6, r0);
        if (isMutable(r5) == true) goto L18;
        Object r82 = r72.newInstance();
        r72.mergeFrom(r82, r5);
        r2.putObject(r6, r0, r82);
        r5 = r82;
    L18:
        r72.mergeFrom(r5, r3);
        return;
    L10:
        if (isMutable(r3) == true) goto L12;
        r2.putObject(r6, r0, r3);
    L13:
        setFieldPresent(r6, r8);
        return;
    L12:
        Object r4 = r72.newInstance();
        r72.mergeFrom(r4, r3);
        r2.putObject(r6, r0, r4);
        goto L13
    L20:
        MessageSchema$$ExternalSyntheticBUOutline0.m("Source subfield ", numberAt(r8), " is present but null: ", r7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T r7, T r8, int r9) {
        int r0 = numberAt(r9);
        if (isOneofPresent(r8, r0, r9) == true) goto L5;
        return;
    L5:
        long r1 = offset(typeAndOffsetAt(r9));
        Unsafe r3 = UNSAFE;
        Object r4 = r3.getObject(r8, r1);
        if (r4 == null) goto L20;
        Schema r82 = getMessageFieldSchema(r9);
        if (isOneofPresent(r7, r0, r9) == false) goto L10;
        Object r6 = r3.getObject(r7, r1);
        if (isMutable(r6) == true) goto L18;
        Object r92 = r82.newInstance();
        r82.mergeFrom(r92, r6);
        r3.putObject(r7, r1, r92);
        r6 = r92;
    L18:
        r82.mergeFrom(r6, r4);
        return;
    L10:
        if (isMutable(r4) == true) goto L12;
        r3.putObject(r7, r1, r4);
    L13:
        setOneofPresent(r7, r0, r9);
        return;
    L12:
        Object r5 = r82.newInstance();
        r82.mergeFrom(r5, r4);
        r3.putObject(r7, r1, r5);
        goto L13
    L20:
        MessageSchema$$ExternalSyntheticBUOutline0.m("Source subfield ", numberAt(r9), " is present but null: ", r8);
    }

    private void mergeSingleField(T r6, T r7, int r8) {
        int r0 = typeAndOffsetAt(r8);
        long r1 = offset(r0);
        int r3 = numberAt(r8);
        switch(type(r0)) {
            case 0: goto L86;
            case 1: goto L82;
            case 2: goto L78;
            case 3: goto L74;
            case 4: goto L70;
            case 5: goto L66;
            case 6: goto L62;
            case 7: goto L58;
            case 8: goto L54;
            case 9: goto L51;
            case 10: goto L48;
            case 11: goto L44;
            case 12: goto L40;
            case 13: goto L36;
            case 14: goto L32;
            case 15: goto L28;
            case 16: goto L24;
            case 17: goto L21;
            case 18: goto L19;
            case 19: goto L19;
            case 20: goto L19;
            case 21: goto L19;
            case 22: goto L19;
            case 23: goto L19;
            case 24: goto L19;
            case 25: goto L19;
            case 26: goto L19;
            case 27: goto L19;
            case 28: goto L19;
            case 29: goto L19;
            case 30: goto L19;
            case 31: goto L19;
            case 32: goto L19;
            case 33: goto L19;
            case 34: goto L19;
            case 35: goto L19;
            case 36: goto L19;
            case 37: goto L19;
            case 38: goto L19;
            case 39: goto L19;
            case 40: goto L19;
            case 41: goto L19;
            case 42: goto L19;
            case 43: goto L19;
            case 44: goto L19;
            case 45: goto L19;
            case 46: goto L19;
            case 47: goto L19;
            case 48: goto L19;
            case 49: goto L19;
            case 50: goto L17;
            case 51: goto L14;
            case 52: goto L14;
            case 53: goto L14;
            case 54: goto L14;
            case 55: goto L14;
            case 56: goto L14;
            case 57: goto L14;
            case 58: goto L14;
            case 59: goto L14;
            case 60: goto L11;
            case 61: goto L8;
            case 62: goto L8;
            case 63: goto L8;
            case 64: goto L8;
            case 65: goto L8;
            case 66: goto L8;
            case 67: goto L8;
            case 68: goto L5;
            default: goto L107;
        };
    L5:
        mergeOneofMessage(r6, r7, r8);
        return;
    L11:
        mergeOneofMessage(r6, r7, r8);
        return;
    L17:
        SchemaUtil.mergeMap(this.mapFieldSchema, r6, r7, r1);
        return;
    L19:
        this.listFieldSchema.mergeListsAt(r6, r7, r1);
        return;
    L21:
        mergeMessage(r6, r7, r8);
        return;
    L51:
        mergeMessage(r6, r7, r8);
        return;
    L107:
        return;
    L8:
        if (isOneofPresent(r7, r3, r8) == false) goto L89;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setOneofPresent(r6, r3, r8);
        return;
    L89:
        return;
    L14:
        if (isOneofPresent(r7, r3, r8) == false) goto L90;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setOneofPresent(r6, r3, r8);
        return;
    L90:
        return;
    L24:
        if (isFieldPresent(r7, r8) == false) goto L91;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L91:
        return;
    L28:
        if (isFieldPresent(r7, r8) == false) goto L92;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L92:
        return;
    L32:
        if (isFieldPresent(r7, r8) == false) goto L93;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L93:
        return;
    L36:
        if (isFieldPresent(r7, r8) == false) goto L94;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L94:
        return;
    L40:
        if (isFieldPresent(r7, r8) == false) goto L95;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L95:
        return;
    L44:
        if (isFieldPresent(r7, r8) == false) goto L96;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L96:
        return;
    L48:
        if (isFieldPresent(r7, r8) == false) goto L97;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L97:
        return;
    L54:
        if (isFieldPresent(r7, r8) == false) goto L98;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L98:
        return;
    L58:
        if (isFieldPresent(r7, r8) == false) goto L99;
        UnsafeUtil.putBoolean(r6, r1, UnsafeUtil.getBoolean(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L99:
        return;
    L62:
        if (isFieldPresent(r7, r8) == false) goto L100;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L100:
        return;
    L66:
        if (isFieldPresent(r7, r8) == false) goto L101;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L101:
        return;
    L70:
        if (isFieldPresent(r7, r8) == false) goto L102;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L102:
        return;
    L74:
        if (isFieldPresent(r7, r8) == false) goto L103;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L103:
        return;
    L78:
        if (isFieldPresent(r7, r8) == false) goto L104;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L104:
        return;
    L82:
        if (isFieldPresent(r7, r8) == false) goto L105;
        UnsafeUtil.putFloat(r6, r1, UnsafeUtil.getFloat(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L105:
        return;
    L86:
        if (isFieldPresent(r7, r8) == false) goto L106;
        UnsafeUtil.putDouble(r6, r1, UnsafeUtil.getDouble(r7, r1));
        setFieldPresent(r6, r8);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T r4, int r5) {
        Schema r0 = getMessageFieldSchema(r5);
        long r1 = offset(typeAndOffsetAt(r5));
        if (isFieldPresent(r4, r5) == false) goto L5;
        Object r3 = UNSAFE.getObject(r4, r1);
        if (isMutable(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.newInstance();
        if (r3 == null) goto L12;
        r0.mergeFrom(r42, r3);
    L12:
        return r42;
    L5:
        return r0.newInstance();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T r4, int r5, int r6) {
        Schema r0 = getMessageFieldSchema(r6);
        if (isOneofPresent(r4, r5, r6) == false) goto L5;
        Object r3 = UNSAFE.getObject(r4, offset(typeAndOffsetAt(r6)));
        if (isMutable(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.newInstance();
        if (r3 == null) goto L12;
        r0.mergeFrom(r42, r3);
    L12:
        return r42;
    L5:
        return r0.newInstance();
    }

    public static <T> MessageSchema<T> newSchema(Class<T> r0, MessageInfo r1, NewInstanceSchema r2, ListFieldSchema r3, UnknownFieldSchema<?, ?> r4, ExtensionSchema<?> r5, MapFieldSchema r6) {
        if ((r1 instanceof RawMessageInfo) == true) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        return newSchemaForMessageInfo(null, r2, r3, r4, r5, r6);
    L5:
        return newSchemaForRawMessageInfo((RawMessageInfo) r1, r2, r3, r4, r5, r6);
    }

    public static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo r0, NewInstanceSchema r1, ListFieldSchema r2, UnknownFieldSchema<?, ?> r3, ExtensionSchema<?> r4, MapFieldSchema r5) {
        throw null;
    }

    public static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo r33, NewInstanceSchema r34, ListFieldSchema r35, UnknownFieldSchema<?, ?> r36, ExtensionSchema<?> r37, MapFieldSchema r38) {
        if (r33.getSyntax() != ProtoSyntax.PROTO3) goto L5;
        boolean r10 = true;
    L6:
        String r0 = r33.getStringInfo();
        int r1 = r0.length();
        char r5 = 55296;
        if (r0.charAt(0) < 55296) goto L12;
        int r4 = 1;
    L9:
        int r6 = r4 + 1;
        if (r0.charAt(r4) < 55296) goto L13;
        r4 = r6;
    L13:
        int r42 = r6 + 1;
        int r62 = r0.charAt(r6);
        if (r62 < 55296) goto L20;
        int r63 = r62 & 8191;
        int r8 = 13;
    L16:
        int r9 = r42 + 1;
        char r43 = r0.charAt(r42);
        if (r43 < 55296) goto L19;
        r63 = r63 | ((r43 & 8191) << r8);
        r8 = r8 + 13;
        r42 = r9;
        goto L16
    L19:
        r62 = r63 | (r43 << r8);
        r42 = r9;
    L20:
        if (r62 != 0) goto L22;
        int r82 = 0;
        int r92 = 0;
        int r11 = 0;
        int r13 = 0;
        int r14 = 0;
        int r16 = 0;
        int[] r12 = EMPTY_INT_ARRAY;
        int r64 = 0;
    L79:
        Unsafe r15 = UNSAFE;
        Object[] r17 = r33.getObjects();
        Class<?> r2 = r33.getDefaultInstance().getClass();
        int[] r3 = new int[r11 * 3];
        Object[] r112 = new Object[r11 * 2];
        int r132 = r13 + r14;
        int r23 = r132;
        int r22 = r14;
        int r20 = 0;
        int r21 = 0;
    L80:
        if (r42 >= r1) goto L193;
        int r24 = r42 + 1;
        int r44 = r0.charAt(r42);
        if (r44 < r5) goto L88;
        int r45 = r44 & 8191;
        int r7 = r24;
        int r242 = 13;
    L84:
        int r25 = r7 + 1;
        char r72 = r0.charAt(r7);
        if (r72 < r5) goto L87;
        r45 = r45 | ((r72 & 8191) << r242);
        r242 = r242 + 13;
        r7 = r25;
        goto L84
    L87:
        r44 = r45 | (r72 << r242);
        int r73 = r25;
    L89:
        int r243 = r73 + 1;
        int r74 = r0.charAt(r73);
        if (r74 < r5) goto L96;
        int r75 = r74 & 8191;
        int r52 = r243;
        int r244 = 13;
    L92:
        int r26 = r52 + 1;
        char r53 = r0.charAt(r52);
        int r27 = r1;
        if (r53 < 55296) goto L95;
        r75 = r75 | ((r53 & 8191) << r244);
        r244 = r244 + 13;
        r52 = r26;
        r1 = r27;
        goto L92
    L95:
        r74 = r75 | (r53 << r244);
        int r18 = r26;
    L97:
        int r54 = r74 & 255;
        int[] r245 = r3;
        if ((r74 & 1024) == 0) goto L100;
        r12[r20] = r21;
        r20 = r20 + 1;
    L100:
        int r29 = r44;
        if (r54 < 51) goto L130;
        int r32 = r18 + 1;
        int r19 = r0.charAt(r18);
        char r46 = 55296;
        if (r19 < 55296) goto L109;
        int r110 = r19 & 8191;
        int r31 = 13;
    L105:
        int r322 = r32 + 1;
        char r39 = r0.charAt(r32);
        if (r39 < r46) goto L108;
        r110 = r110 | ((r39 & 8191) << r31);
        r31 = r31 + 13;
        r32 = r322;
        r46 = 55296;
        goto L105
    L108:
        r19 = r110 | (r39 << r31);
        r32 = r322;
    L109:
        int r47 = r54 - 51;
        int r312 = r19;
        if (r47 != 9) goto L112;
    L119:
        int r48 = r16 + 1;
        r112[((r21 / 3) * 2) + 1] = r17[r16];
    L118:
        r16 = r48;
    L120:
        int r111 = r312 * 2;
        Object r49 = r17[r111];
        if ((r49 instanceof Field) == false) goto L124;
        Field r410 = (Field) r49;
    L123:
        int r113 = r32;
        int r310 = (int) r15.objectFieldOffset(r410);
        int r411 = r111 + 1;
        Object r114 = r17[r411];
        if ((r114 instanceof Field) == false) goto L128;
        Field r115 = (Field) r114;
    L129:
        String r30 = r0;
        int r02 = (int) r15.objectFieldOffset(r115);
        r42 = r113;
        int r311 = r310;
        int r116 = 0;
    L183:
        int r262 = r21 + 1;
        r245[r21] = r29;
        int r28 = r21 + 2;
        int r292 = r02;
        if ((r74 & 512) == 0) goto L186;
        int r03 = 536870912;
    L188:
        if ((r74 & 256) == 0) goto L190;
        int r76 = 268435456;
    L191:
        r245[r262] = ((r03 | r76) | (r54 << 20)) | r311;
        r21 = r21 + 3;
        r245[r28] = (r116 << 20) | r292;
        r3 = r245;
        r1 = r27;
        r0 = r30;
        r5 = 55296;
        goto L80
    L190:
        r76 = 0;
        goto L191
    L186:
        r03 = 0;
        goto L188
    L128:
        r115 = reflectField(r2, (String) r114);
        r17[r411] = r115;
        goto L129
    L124:
        r410 = reflectField(r2, (String) r49);
        r17[r111] = r410;
        goto L123
    L112:
        if (r47 == 17) goto L119;
        if (r47 != 12) goto L120;
        if (r10 == true) goto L120;
        r48 = r16 + 1;
        r112[((r21 / 3) * 2) + 1] = r17[r16];
        goto L118
    L130:
        int r313 = r16 + 1;
        Field r412 = reflectField(r2, (String) r17[r16]);
        if (r54 != 9) goto L133;
    L157:
        r112[((r21 / 3) * 2) + 1] = r412.getType();
    L158:
        r16 = r313;
    L159:
        int r314 = (int) r15.objectFieldOffset(r412);
        if ((r74 & 4096) == 4096) goto L162;
    L176:
        r30 = r0;
        r02 = 1048575;
        int r282 = r18;
        r116 = 0;
    L178:
        if (r54 >= 18) goto L180;
    L182:
        r311 = r314;
        r42 = r282;
        goto L183
    L180:
        if (r54 > 49) goto L182;
        r12[r23] = r314;
        r23 = r23 + 1;
        goto L182
    L162:
        if (r54 > 17) goto L176;
        int r315 = r18 + 1;
        int r117 = r0.charAt(r18);
        if (r117 < 55296) goto L170;
        int r118 = r117 & 8191;
        int r252 = 13;
    L166:
        r282 = r315 + 1;
        char r316 = r0.charAt(r315);
        if (r316 < 55296) goto L169;
        r118 = r118 | ((r316 & 8191) << r252);
        r252 = r252 + 13;
        r315 = r282;
        goto L166
    L169:
        r117 = r118 | (r316 << r252);
    L171:
        int r317 = (r64 * 2) + (r117 / 32);
        Object r413 = r17[r317];
        r30 = r0;
        if ((r413 instanceof Field) == false) goto L174;
        Field r414 = (Field) r413;
    L175:
        r02 = (int) r15.objectFieldOffset(r414);
        r116 = r117 % 32;
        goto L178
    L174:
        r414 = reflectField(r2, (String) r413);
        r17[r317] = r414;
        goto L175
    L170:
        r282 = r315;
        goto L171
    L133:
        if (r54 == 17) goto L157;
        if (r54 != 27) goto L138;
    L156:
        r16 = r16 + 2;
        r112[((r21 / 3) * 2) + 1] = r17[r313];
        goto L159
    L138:
        if (r54 == 49) goto L156;
        if (r54 != 12) goto L143;
    L154:
        if (r10 == true) goto L158;
        r16 = r16 + 2;
        r112[((r21 / 3) * 2) + 1] = r17[r313];
        goto L159
    L143:
        if (r54 == 30) goto L154;
        if (r54 == 44) goto L154;
        if (r54 != 50) goto L158;
        int r318 = r22 + 1;
        r12[r22] = r21;
        int r222 = (r21 / 3) * 2;
        int r263 = r16 + 2;
        r112[r222] = r17[r313];
        if ((r74 & 2048) == 0) goto L153;
        r112[r222 + 1] = r17[r263];
        r16 = r16 + 3;
    L152:
        r22 = r318;
        goto L159
    L153:
        r16 = r263;
        goto L152
    L96:
        r27 = r1;
        r18 = r243;
        goto L97
    L88:
        r73 = r24;
        goto L89
    L193:
        return new MessageSchema(r3, r112, r82, r92, r33.getDefaultInstance(), r10, false, r12, r14, r132, r34, r35, r36, r37, r38);
    L22:
        int r65 = r42 + 1;
        int r415 = r0.charAt(r42);
        if (r415 < 55296) goto L29;
        int r416 = r415 & 8191;
        int r83 = 13;
    L25:
        int r93 = r65 + 1;
        char r66 = r0.charAt(r65);
        if (r66 < 55296) goto L28;
        r416 = r416 | ((r66 & 8191) << r83);
        r83 = r83 + 13;
        r65 = r93;
        goto L25
    L28:
        r415 = r416 | (r66 << r83);
        r65 = r93;
    L29:
        int r84 = r65 + 1;
        int r67 = r0.charAt(r65);
        if (r67 < 55296) goto L36;
        int r68 = r67 & 8191;
        int r94 = 13;
    L32:
        int r119 = r84 + 1;
        char r85 = r0.charAt(r84);
        if (r85 < 55296) goto L35;
        r68 = r68 | ((r85 & 8191) << r94);
        r94 = r94 + 13;
        r84 = r119;
        goto L32
    L35:
        r67 = r68 | (r85 << r94);
        r84 = r119;
    L36:
        int r95 = r84 + 1;
        r82 = r0.charAt(r84);
        if (r82 < 55296) goto L43;
        int r86 = r82 & 8191;
        int r1110 = 13;
    L39:
        int r122 = r95 + 1;
        char r96 = r0.charAt(r95);
        if (r96 < 55296) goto L42;
        r86 = r86 | ((r96 & 8191) << r1110);
        r1110 = r1110 + 13;
        r95 = r122;
        goto L39
    L42:
        r82 = r86 | (r96 << r1110);
        r95 = r122;
    L43:
        int r1111 = r95 + 1;
        r92 = r0.charAt(r95);
        if (r92 < 55296) goto L50;
        int r97 = r92 & 8191;
        int r123 = 13;
    L46:
        int r133 = r1111 + 1;
        char r1112 = r0.charAt(r1111);
        if (r1112 < 55296) goto L49;
        r97 = r97 | ((r1112 & 8191) << r123);
        r123 = r123 + 13;
        r1111 = r133;
        goto L46
    L49:
        r92 = r97 | (r1112 << r123);
        r1111 = r133;
    L50:
        int r124 = r1111 + 1;
        r11 = r0.charAt(r1111);
        if (r11 < 55296) goto L57;
        int r1113 = r11 & 8191;
        int r134 = 13;
    L53:
        int r142 = r124 + 1;
        char r125 = r0.charAt(r124);
        if (r125 < 55296) goto L56;
        r1113 = r1113 | ((r125 & 8191) << r134);
        r134 = r134 + 13;
        r124 = r142;
        goto L53
    L56:
        r11 = r1113 | (r125 << r134);
        r124 = r142;
    L57:
        int r135 = r124 + 1;
        int r126 = r0.charAt(r124);
        if (r126 < 55296) goto L64;
        int r127 = r126 & 8191;
        int r143 = 13;
    L60:
        int r152 = r135 + 1;
        char r136 = r0.charAt(r135);
        if (r136 < 55296) goto L63;
        r127 = r127 | ((r136 & 8191) << r143);
        r143 = r143 + 13;
        r135 = r152;
        goto L60
    L63:
        r126 = r127 | (r136 << r143);
        r135 = r152;
    L64:
        int r144 = r135 + 1;
        int r137 = r0.charAt(r135);
        if (r137 < 55296) goto L71;
        int r138 = r137 & 8191;
        int r153 = 13;
    L67:
        int r162 = r144 + 1;
        char r145 = r0.charAt(r144);
        if (r145 < 55296) goto L70;
        r138 = r138 | ((r145 & 8191) << r153);
        r153 = r153 + 13;
        r144 = r162;
        goto L67
    L70:
        r137 = r138 | (r145 << r153);
        r144 = r162;
    L71:
        int r154 = r144 + 1;
        r14 = r0.charAt(r144);
        if (r14 < 55296) goto L78;
        int r146 = r14 & 8191;
        int r163 = 13;
    L74:
        int r172 = r154 + 1;
        char r155 = r0.charAt(r154);
        if (r155 < 55296) goto L77;
        r146 = r146 | ((r155 & 8191) << r163);
        r163 = r163 + 13;
        r154 = r172;
        goto L74
    L77:
        r14 = r146 | (r155 << r163);
        r154 = r172;
    L78:
        int[] r139 = new int[(r14 + r126) + r137];
        r16 = (r415 * 2) + r67;
        r13 = r126;
        r12 = r139;
        r64 = r415;
        r42 = r154;
        goto L79
    L12:
        r6 = 1;
        goto L13
    L5:
        r10 = false;
        goto L6
    }

    private int numberAt(int r1) {
        return this.buffer[r1];
    }

    private static long offset(int r2) {
        return r2 & 1048575;
    }

    private static <T> boolean oneofBooleanAt(T r0, long r1) {
        return ((Boolean) UnsafeUtil.getObject(r0, r1)).booleanValue();
    }

    private static <T> double oneofDoubleAt(T r0, long r1) {
        return ((Double) UnsafeUtil.getObject(r0, r1)).doubleValue();
    }

    private static <T> float oneofFloatAt(T r0, long r1) {
        return ((Float) UnsafeUtil.getObject(r0, r1)).floatValue();
    }

    private static <T> int oneofIntAt(T r0, long r1) {
        return ((Integer) UnsafeUtil.getObject(r0, r1)).intValue();
    }

    private static <T> long oneofLongAt(T r0, long r1) {
        return ((Long) UnsafeUtil.getObject(r0, r1)).longValue();
    }

    private <K, V> int parseMapField(T r10, byte[] r11, int r12, int r13, int r14, long r15, ArrayDecoders.Registers r17) {
        Unsafe r4 = UNSAFE;
        Object r5 = getMapFieldDefaultEntry(r14);
        Object r6 = r4.getObject(r10, r15);
        if (this.mapFieldSchema.isImmutable(r6) == false) goto L6;
        Object r7 = this.mapFieldSchema.newMapField(r5);
        this.mapFieldSchema.mergeFrom(r7, r6);
        r4.putObject(r10, r15, r7);
        r6 = r7;
    L6:
        return decodeMapEntry(r11, r12, r13, this.mapFieldSchema.forMapMetadata(r5), this.mapFieldSchema.forMutableMapData(r6), r17);
    }

    private int parseOneofField(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, ArrayDecoders.Registers r29) {
        Unsafe r5 = UNSAFE;
        long r6 = this.buffer[r28 + 2] & 1048575;
        boolean r11 = true;
        switch(r25) {
            case 51: goto L74;
            case 52: goto L70;
            case 53: goto L66;
            case 54: goto L66;
            case 55: goto L62;
            case 56: goto L58;
            case 57: goto L54;
            case 58: goto L46;
            case 59: goto L31;
            case 60: goto L26;
            case 61: goto L22;
            case 62: goto L62;
            case 63: goto L18;
            case 64: goto L54;
            case 65: goto L58;
            case 66: goto L14;
            case 67: goto L9;
            case 68: goto L6;
            default: goto L77;
        };
    L9:
        int r8 = r19;
        if (r23 != 0) goto L78;
        int r0 = ArrayDecoders.decodeVarint64(r18, r8, r29);
        r5.putObject(r17, r26, Long.valueOf(CodedInputStream.decodeZigZag64(r29.long1)));
        r5.putInt(r17, r6, r22);
        return r0;
    L78:
        return r8;
    L14:
        r8 = r19;
        if (r23 != 0) goto L78;
        int r02 = ArrayDecoders.decodeVarint32(r18, r8, r29);
        r5.putObject(r17, r26, Integer.valueOf(CodedInputStream.decodeZigZag32(r29.int1)));
        r5.putInt(r17, r6, r22);
        return r02;
    L18:
        r8 = r19;
        if (r23 != 0) goto L78;
        int r2 = ArrayDecoders.decodeVarint32(r18, r8, r29);
        int r82 = r29.int1;
        getEnumFieldVerifier(r28);
        r5.putObject(r17, r26, Integer.valueOf(r82));
        r5.putInt(r17, r6, r22);
        return r2;
    L22:
        r8 = r19;
        if (r23 != 2) goto L78;
        int r03 = ArrayDecoders.decodeBytes(r18, r8, r29);
        r5.putObject(r17, r26, r29.object1);
        r5.putInt(r17, r6, r22);
        return r03;
    L26:
        r8 = r19;
        if (r23 != 2) goto L78;
        Object r27 = mutableOneofMessageFieldForMerge(r17, r22, r28);
        int r3 = ArrayDecoders.mergeMessageField(r27, getMessageFieldSchema(r28), r18, r8, r20, r29);
        storeOneofMessageField(r17, r22, r28, r27);
        return r3;
    L77:
        return r19;
    L6:
        if (r23 != 3) goto L77;
        Object r210 = mutableOneofMessageFieldForMerge(r17, r22, r28);
        int r32 = ArrayDecoders.mergeGroupField(r210, getMessageFieldSchema(r28), r18, r19, r20, (r21 & (-8)) | 4, r29);
        storeOneofMessageField(r17, r22, r28, r210);
        return r32;
    L31:
        if (r23 != 2) goto L79;
        int r04 = ArrayDecoders.decodeVarint32(r18, r19, r29);
        int r211 = r29.int1;
        if (r211 != 0) goto L36;
        r5.putObject(r17, r26, _UrlKt.FRAGMENT_ENCODE_SET);
    L43:
        r5.putInt(r17, r6, r22);
        return r04;
    L36:
        if ((r24 & 536870912) != 0) goto L38;
    L42:
        r5.putObject(r17, r26, new String(r18, r04, r211, Internal.UTF_8));
        r04 = r04 + r211;
        goto L43
    L38:
        if (Utf8.isValidUtf8(r18, r04, r04 + r211) == true) goto L42;
        throw InvalidProtocolBufferException.invalidUtf8();
    L79:
        return r19;
    L46:
        if (r23 != 0) goto L80;
        int r05 = ArrayDecoders.decodeVarint64(r18, r19, r29);
        if (r29.long1 != 0) goto L51;
        r11 = false;
    L51:
        r5.putObject(r17, r26, Boolean.valueOf(r11));
        r5.putInt(r17, r6, r22);
        return r05;
    L80:
        return r19;
    L54:
        if (r23 != 5) goto L81;
        r5.putObject(r17, r26, Integer.valueOf(ArrayDecoders.decodeFixed32(r18, r19)));
        int r06 = r19 + 4;
        r5.putInt(r17, r6, r22);
        return r06;
    L81:
        return r19;
    L58:
        if (r23 != 1) goto L82;
        r5.putObject(r17, r26, Long.valueOf(ArrayDecoders.decodeFixed64(r18, r19)));
        int r07 = r19 + 8;
        r5.putInt(r17, r6, r22);
        return r07;
    L82:
        return r19;
    L62:
        if (r23 != 0) goto L83;
        int r08 = ArrayDecoders.decodeVarint32(r18, r19, r29);
        r5.putObject(r17, r26, Integer.valueOf(r29.int1));
        r5.putInt(r17, r6, r22);
        return r08;
    L83:
        return r19;
    L66:
        if (r23 != 0) goto L84;
        int r09 = ArrayDecoders.decodeVarint64(r18, r19, r29);
        r5.putObject(r17, r26, Long.valueOf(r29.long1));
        r5.putInt(r17, r6, r22);
        return r09;
    L84:
        return r19;
    L70:
        if (r23 != 5) goto L85;
        r5.putObject(r17, r26, Float.valueOf(ArrayDecoders.decodeFloat(r18, r19)));
        int r010 = r19 + 4;
        r5.putInt(r17, r6, r22);
        return r010;
    L85:
        return r19;
    L74:
        if (r23 != 1) goto L86;
        r5.putObject(r17, r26, Double.valueOf(ArrayDecoders.decodeDouble(r18, r19)));
        int r011 = r19 + 8;
        r5.putInt(r17, r6, r22);
        return r011;
    L86:
        return r19;
    }

    private int parseProto3Message(T r28, byte[] r29, int r30, int r31, ArrayDecoders.Registers r32) {
        MessageSchema<T> r0 = this;
        T r1 = r28;
        byte[] r7 = r29;
        int r8 = r31;
        ArrayDecoders.Registers r13 = r32;
        checkMutable(r1);
        Unsafe r2 = UNSAFE;
        int r9 = -1;
        int r3 = r30;
        int r4 = -1;
        int r5 = 0;
        int r11 = 0;
        int r10 = 1048575;
    L3:
        if (r3 >= r8) goto L122;
        int r6 = r3 + 1;
        int r33 = r7[r3];
        if (r33 >= 0) goto L7;
        r6 = ArrayDecoders.decodeVarint32(r33, r7, r6, r13);
        r33 = r13.int1;
    L7:
        int r12 = r33 >>> 3;
        int r14 = r33 & 7;
        if (r12 <= r4) goto L10;
        int r42 = r0.positionForFieldNumber(r12, r5 / 3);
    L11:
        if (r42 == r9) goto L12;
        int r52 = r0.buffer[r42 + 1];
        int r92 = type(r52);
        int r18 = r33;
        int r19 = r42;
        long r34 = offset(r52);
        if (r92 <= 17) goto L15;
        int r82 = r6;
        byte[] r132 = r7;
        if (r92 == 27) goto L90;
        Unsafe r83 = r2;
        if (r92 > 49) goto L108;
        int r24 = r11;
        Unsafe r25 = r83;
        int r17 = -1;
        int r15 = r10;
        int r72 = r0.parseRepeatedField(r28, r29, r82, r31, r18, r12, r14, r19, r52, r92, r34, r32);
        int r93 = r12;
        int r192 = r19;
        if (r72 == r82) goto L105;
        r0 = this;
        r1 = r28;
        r8 = r31;
        r13 = r32;
        r3 = r72;
        r4 = r93;
        r10 = r15;
        r9 = -1;
        r5 = r192;
        r11 = r24;
        r2 = r25;
        r7 = r29;
        goto L3
    L105:
        T r84 = r28;
        int r22 = r72;
    L106:
        int r62 = r93;
    L107:
        r10 = r15;
        r11 = r24;
    L121:
        int r02 = ArrayDecoders.decodeUnknownField(r18, r29, r22, r31, getMutableUnknownFields(r84), r32);
        r7 = r29;
        r13 = r32;
        r4 = r62;
        r1 = r84;
        r9 = r17;
        r5 = r192;
        r2 = r25;
        r8 = r31;
        r3 = r02;
        r0 = this;
        goto L3
    L108:
        r25 = r83;
        r24 = r11;
        r192 = r19;
        r17 = -1;
        r15 = r10;
        r93 = r12;
        int r35 = r82;
        if (r92 != 50) goto L117;
        if (r14 != 2) goto L116;
        int r63 = parseMapField(r28, r29, r35, r31, r192, r34, r32);
        if (r63 == r35) goto L115;
        r0 = this;
        r1 = r28;
        r7 = r29;
        r8 = r31;
        r13 = r32;
        r3 = r63;
        r4 = r93;
        r10 = r15;
        r9 = -1;
        r5 = r192;
        r11 = r24;
        r2 = r25;
        goto L3
    L115:
        r84 = r28;
        r22 = r63;
    L116:
        r84 = r28;
        r22 = r35;
        goto L106
    L117:
        r62 = r93;
        int r73 = parseOneofField(r28, r29, r35, r31, r18, r62, r14, r52, r92, r34, r192, r32);
        r84 = r28;
        if (r73 == r35) goto L120;
        r0 = this;
        r13 = r32;
        r4 = r62;
        r3 = r73;
        r1 = r84;
        r10 = r15;
        r9 = -1;
        r5 = r192;
        r11 = r24;
        r2 = r25;
        r7 = r29;
    L49:
        r8 = r31;
        goto L3
    L120:
        r22 = r73;
        goto L107
    L90:
        if (r14 != 2) goto L99;
        Internal.ProtobufList r53 = (Internal.ProtobufList) r2.getObject(r1, r34);
        if (r53.isModifiable() == true) goto L98;
        int r64 = r53.size();
        if (r64 != 0) goto L96;
        int r65 = 10;
    L97:
        r53 = r53.mutableCopyWithCapacity(r65);
        r2.putObject(r1, r34, r53);
        goto L98
    L96:
        r65 = r64 * 2;
    L98:
        int r16 = ArrayDecoders.decodeMessageList(r0.getMessageFieldSchema(r19), r18, r132, r82, r31, r53, r32);
        r7 = r29;
        r13 = r32;
        r3 = r16;
        r2 = r2;
        r4 = r12;
        r5 = r19;
        r9 = -1;
        r1 = r28;
        goto L49
    L99:
        r35 = r82;
        r25 = r2;
        r24 = r11;
        r93 = r12;
        r192 = r19;
        r17 = -1;
        r15 = r10;
        goto L116
    L15:
        int r43 = r0.buffer[r19 + 2];
        int r152 = 1 << (r43 >>> 20);
        int r44 = r43 & 1048575;
        if (r44 == r10) goto L25;
        if (r10 == 1048575) goto L20;
        r2.putInt(r1, r10, r11);
        int r36 = r44;
        int r45 = 1048575;
    L21:
        if (r36 == r45) goto L23;
        r11 = r2.getInt(r1, r36);
    L23:
        r10 = r36;
        goto L25
    L20:
        r45 = 1048575;
        r36 = r44;
    L25:
        switch(r92) {
            case 0: goto L83;
            case 1: goto L80;
            case 2: goto L77;
            case 3: goto L77;
            case 4: goto L74;
            case 5: goto L69;
            case 6: goto L66;
            case 7: goto L59;
            case 8: goto L51;
            case 9: goto L43;
            case 10: goto L40;
            case 11: goto L74;
            case 12: goto L37;
            case 13: goto L66;
            case 14: goto L69;
            case 15: goto L30;
            case 16: goto L27;
            default: goto L26;
        };
    L26:
        int r85 = r6;
        int r94 = r19;
    L86:
        r25 = r2;
        r22 = r85;
        r192 = r94;
        r62 = r12;
        r17 = -1;
        r84 = r1;
        goto L121
    L43:
        r94 = r19;
        if (r14 != 2) goto L50;
        T r37 = r1;
        Object r110 = r0.mutableMessageFieldForMerge(r37, r94);
        byte[] r54 = r7;
        T r74 = r37;
        int r55 = r8;
        Unsafe r86 = r2;
        int r46 = r6;
        ArrayDecoders.Registers r66 = r13;
        int r23 = ArrayDecoders.mergeMessageField(r110, r0.getMessageFieldSchema(r94), r54, r46, r55, r66);
        byte[] r133 = r54;
        ArrayDecoders.Registers r111 = r66;
        r0.storeMessageField(r74, r94, r110);
    L46:
        r11 = r11 | r152;
        byte[] r38 = r133;
        r13 = r111;
        r1 = r74;
        r7 = r38;
        r3 = r23;
    L47:
        r2 = r86;
    L48:
        r5 = r94;
        r4 = r12;
        r9 = -1;
        goto L49
    L50:
        r1 = r1;
        r85 = r6;
        goto L86
    L51:
        byte[] r39 = r7;
        r74 = r1;
        r111 = r13;
        r133 = r39;
        r86 = r2;
        int r26 = r6;
        r94 = r19;
        if (r14 != 2) goto L58;
        if ((r52 & 536870912) != 0) goto L56;
        r23 = ArrayDecoders.decodeString(r133, r26, r111);
    L57:
        r86.putObject(r74, r34, r111.object1);
        goto L46
    L56:
        r23 = ArrayDecoders.decodeStringRequireUtf8(r133, r26, r111);
    L58:
        Unsafe r112 = r86;
        r85 = r26;
        r2 = r112;
        r1 = r74;
        goto L86
    L59:
        byte[] r310 = r7;
        r74 = r1;
        r111 = r13;
        r133 = r310;
        r86 = r2;
        r26 = r6;
        r94 = r19;
        if (r14 != 0) goto L58;
        r23 = ArrayDecoders.decodeVarint64(r133, r26, r111);
        if (r111.long1 == 0) goto L64;
        boolean r56 = true;
    L65:
        UnsafeUtil.putBoolean(r74, r34, r56);
        goto L46
    L64:
        r56 = false;
        goto L65
    L66:
        byte[] r47 = r7;
        r74 = r1;
        ArrayDecoders.Registers r113 = r13;
        r86 = r2;
        r26 = r6;
        r94 = r19;
        if (r14 != 5) goto L58;
        r86.putInt(r74, r34, ArrayDecoders.decodeFixed32(r47, r26));
        r3 = r26 + 4;
        r11 = r11 | r152;
        r13 = r113;
        r1 = r74;
        r7 = r47;
        goto L47
    L77:
        ArrayDecoders.Registers r311 = r13;
        byte[] r134 = r7;
        r85 = r6;
        r94 = r19;
        if (r14 != 0) goto L86;
        int r87 = ArrayDecoders.decodeVarint64(r134, r85, r311);
        Unsafe r262 = r2;
        T r27 = r1;
        r262.putLong(r27, r34, r311.long1);
        r2 = r262;
        r1 = r27;
        r11 = r11 | r152;
        r13 = r311;
        r7 = r134;
        r3 = r87;
        goto L48
    L83:
        byte[] r135 = r7;
        r85 = r6;
        r94 = r19;
        if (r14 != 1) goto L86;
        UnsafeUtil.putDouble(r1, r34, ArrayDecoders.decodeDouble(r135, r85));
        r3 = r85 + 8;
        r11 = r11 | r152;
        r8 = r31;
        r5 = r94;
        r4 = r12;
        r7 = r135;
        r9 = -1;
        r13 = r32;
        goto L3
    L27:
        if (r14 != 0) goto L26;
        int r95 = ArrayDecoders.decodeVarint64(r7, r6, r13);
        Unsafe r312 = r2;
        T r210 = r1;
        r312.putLong(r210, r34, CodedInputStream.decodeZigZag64(r13.long1));
        r2 = r312;
        r1 = r210;
        r11 = r11 | r152;
        r3 = r95;
        r4 = r12;
        r5 = r19;
    L29:
        r9 = -1;
        goto L3
    L30:
        r94 = r19;
        if (r14 != 0) goto L36;
        int r57 = ArrayDecoders.decodeVarint32(r7, r6, r13);
        r2.putInt(r1, r34, CodedInputStream.decodeZigZag32(r13.int1));
    L33:
        r11 = r11 | r152;
    L34:
        r3 = r57;
    L35:
        r5 = r94;
        r4 = r12;
    L36:
        r85 = r6;
        goto L86
    L37:
        r94 = r19;
        if (r14 != 0) goto L36;
        r57 = ArrayDecoders.decodeVarint32(r7, r6, r13);
        r2.putInt(r1, r34, r13.int1);
        goto L33
    L40:
        r94 = r19;
        if (r14 != 2) goto L36;
        r57 = ArrayDecoders.decodeBytes(r7, r6, r13);
        r2.putObject(r1, r34, r13.object1);
        goto L33
    L69:
        byte[] r313 = r7;
        T r75 = r1;
        ArrayDecoders.Registers r114 = r13;
        byte[] r136 = r313;
        Unsafe r88 = r2;
        int r211 = r6;
        r94 = r19;
        if (r14 != 1) goto L73;
        ArrayDecoders.Registers r76 = r114;
        r88.putLong(r75, r34, ArrayDecoders.decodeFixed64(r136, r211));
        r2 = r88;
        r1 = r75;
        r3 = r211 + 8;
    L72:
        r11 = r11 | r152;
        byte[] r48 = r136;
        r13 = r76;
        r7 = r48;
        r8 = r31;
        goto L35
    L73:
        r1 = r75;
        r85 = r211;
        r2 = r88;
        goto L86
    L74:
        ArrayDecoders.Registers r314 = r13;
        byte[] r137 = r7;
        r85 = r6;
        r94 = r19;
        if (r14 != 0) goto L86;
        r57 = ArrayDecoders.decodeVarint32(r137, r85, r314);
        r2.putInt(r1, r34, r314.int1);
        r11 = r11 | r152;
        r13 = r314;
        r7 = r137;
        r8 = r31;
        goto L34
    L80:
        ArrayDecoders.Registers r49 = r13;
        r136 = r7;
        r76 = r49;
        r85 = r6;
        r94 = r19;
        if (r14 != 5) goto L86;
        UnsafeUtil.putFloat(r1, r34, ArrayDecoders.decodeFloat(r136, r85));
        r3 = r85 + 4;
        goto L72
    L12:
        r84 = r1;
        r25 = r2;
        r18 = r33;
        r22 = r6;
        r17 = r9;
        r62 = r12;
        r192 = 0;
        goto L121
    L10:
        r42 = r0.positionForFieldNumber(r12);
        goto L11
    L122:
        Unsafe r252 = r2;
        int r410 = r8;
        int r153 = r10;
        int r242 = r11;
        T r89 = r1;
        if (r153 == 1048575) goto L125;
        r252.putInt(r89, r153, r242);
    L125:
        if (r3 != r410) goto L128;
        return r3;
    L128:
        throw InvalidProtocolBufferException.parseFailure();
    }

    private int parseRepeatedField(T r9, byte[] r10, int r11, int r12, int r13, int r14, int r15, int r16, long r17, int r19, long r20, ArrayDecoders.Registers r22) {
        Unsafe r4 = UNSAFE;
        Internal.ProtobufList r5 = (Internal.ProtobufList) r4.getObject(r9, r20);
        if (r5.isModifiable() == true) goto L9;
        int r6 = r5.size();
        if (r6 != 0) goto L7;
        int r62 = 10;
    L8:
        r5 = r5.mutableCopyWithCapacity(r62);
        r4.putObject(r9, r20, r5);
        goto L9
    L7:
        r62 = r6 * 2;
    L9:
        Internal.ProtobufList r63 = r5;
        switch(r19) {
            case 18: goto L93;
            case 19: goto L86;
            case 20: goto L79;
            case 21: goto L79;
            case 22: goto L72;
            case 23: goto L65;
            case 24: goto L58;
            case 25: goto L51;
            case 26: goto L43;
            case 27: goto L40;
            case 28: goto L37;
            case 29: goto L72;
            case 30: goto L31;
            case 31: goto L58;
            case 32: goto L65;
            case 33: goto L24;
            case 34: goto L17;
            case 35: goto L93;
            case 36: goto L86;
            case 37: goto L79;
            case 38: goto L79;
            case 39: goto L72;
            case 40: goto L65;
            case 41: goto L58;
            case 42: goto L51;
            case 43: goto L72;
            case 44: goto L31;
            case 45: goto L58;
            case 46: goto L65;
            case 47: goto L24;
            case 48: goto L17;
            case 49: goto L13;
            default: goto L99;
        };
    L37:
        if (r15 != 2) goto L99;
        return ArrayDecoders.decodeBytesList(r13, r10, r11, r12, r63, r22);
    L40:
        if (r15 != 2) goto L99;
        return ArrayDecoders.decodeMessageList(getMessageFieldSchema(r16), r13, r10, r11, r12, r63, r22);
    L43:
        if (r15 != 2) goto L99;
        if ((r17 & 536870912) != 0) goto L49;
        return ArrayDecoders.decodeStringList(r13, r10, r11, r12, r63, r22);
    L49:
        return ArrayDecoders.decodeStringListRequireUtf8(r13, r10, r11, r12, r63, r22);
    L99:
        return r11;
    L13:
        if (r15 != 3) goto L99;
        return ArrayDecoders.decodeGroupList(getMessageFieldSchema(r16), r13, r10, r11, r12, r63, r22);
    L17:
        if (r15 == 2) goto L19;
        if (r15 != 0) goto L99;
        return ArrayDecoders.decodeSInt64List(r13, r10, r11, r12, r63, r22);
    L19:
        return ArrayDecoders.decodePackedSInt64List(r10, r11, r63, r22);
    L24:
        if (r15 == 2) goto L26;
        if (r15 != 0) goto L99;
        return ArrayDecoders.decodeSInt32List(r13, r10, r11, r12, r63, r22);
    L26:
        return ArrayDecoders.decodePackedSInt32List(r10, r11, r63, r22);
    L31:
        if (r15 != 2) goto L33;
        int r102 = ArrayDecoders.decodePackedVarint32List(r10, r11, r63, r22);
    L35:
        getEnumFieldVerifier(r16);
        SchemaUtil.filterUnknownEnumList(r9, r14, r63, null, null, this.unknownFieldSchema);
        return r102;
    L33:
        if (r15 != 0) goto L99;
        r102 = ArrayDecoders.decodeVarint32List(r13, r10, r11, r12, r63, r22);
        goto L35
    L51:
        if (r15 == 2) goto L53;
        if (r15 != 0) goto L99;
        return ArrayDecoders.decodeBoolList(r13, r10, r11, r12, r63, r22);
    L53:
        return ArrayDecoders.decodePackedBoolList(r10, r11, r63, r22);
    L58:
        if (r15 == 2) goto L60;
        if (r15 != 5) goto L99;
        return ArrayDecoders.decodeFixed32List(r13, r10, r11, r12, r63, r22);
    L60:
        return ArrayDecoders.decodePackedFixed32List(r10, r11, r63, r22);
    L65:
        if (r15 == 2) goto L67;
        if (r15 != 1) goto L99;
        return ArrayDecoders.decodeFixed64List(r13, r10, r11, r12, r63, r22);
    L67:
        return ArrayDecoders.decodePackedFixed64List(r10, r11, r63, r22);
    L72:
        if (r15 == 2) goto L74;
        if (r15 != 0) goto L99;
        return ArrayDecoders.decodeVarint32List(r13, r10, r11, r12, r63, r22);
    L74:
        return ArrayDecoders.decodePackedVarint32List(r10, r11, r63, r22);
    L79:
        if (r15 == 2) goto L81;
        if (r15 != 0) goto L99;
        return ArrayDecoders.decodeVarint64List(r13, r10, r11, r12, r63, r22);
    L81:
        return ArrayDecoders.decodePackedVarint64List(r10, r11, r63, r22);
    L86:
        if (r15 == 2) goto L88;
        if (r15 != 5) goto L99;
        return ArrayDecoders.decodeFloatList(r13, r10, r11, r12, r63, r22);
    L88:
        return ArrayDecoders.decodePackedFloatList(r10, r11, r63, r22);
    L93:
        if (r15 == 2) goto L95;
        if (r15 != 1) goto L99;
        return ArrayDecoders.decodeDoubleList(r13, r10, r11, r12, r63, r22);
    L95:
        return ArrayDecoders.decodePackedDoubleList(r10, r11, r63, r22);
    }

    private int positionForFieldNumber(int r2) {
        if (r2 >= this.minFieldNumber) goto L5;
        return -1;
    L5:
        if (r2 <= this.maxFieldNumber) goto L7;
        return -1;
    L7:
        return slowPositionForFieldNumber(r2, 0);
    }

    private int positionForFieldNumber(int r2, int r3) {
        if (r2 >= this.minFieldNumber) goto L5;
        return -1;
    L5:
        if (r2 <= this.maxFieldNumber) goto L7;
        return -1;
    L7:
        return slowPositionForFieldNumber(r2, r3);
    }

    private int presenceMaskAndOffsetAt(int r1) {
        return this.buffer[r1 + 2];
    }

    private <E> void readGroupList(Object r1, long r2, Reader r4, Schema<E> r5, ExtensionRegistryLite r6) {
        r4.readGroupList(this.listFieldSchema.mutableListAt(r1, r2), r5, r6);
    }

    private <E> void readMessageList(Object r3, int r4, Reader r5, Schema<E> r6, ExtensionRegistryLite r7) {
        long r0 = offset(r4);
        r5.readMessageList(this.listFieldSchema.mutableListAt(r3, r0), r6, r7);
    }

    private void readString(Object r3, int r4, Reader r5) {
        if (isEnforceUtf8(r4) == false) goto L7;
        UnsafeUtil.putObject(r3, offset(r4), r5.readStringRequireUtf8());
        return;
    L7:
        if (this.lite == false) goto L10;
        UnsafeUtil.putObject(r3, offset(r4), r5.readString());
        return;
    L10:
        UnsafeUtil.putObject(r3, offset(r4), r5.readBytes());
    }

    private void readStringList(Object r3, int r4, Reader r5) {
        boolean r0 = isEnforceUtf8(r4);
        ListFieldSchema r2 = this.listFieldSchema;
        if (r0 == false) goto L6;
        r5.readStringListRequireUtf8(r2.mutableListAt(r3, offset(r4)));
        return;
    L6:
        r5.readStringList(r2.mutableListAt(r3, offset(r4)));
    }

    private static Field reflectField(Class<?> r9, String r10) {
        return r9.getDeclaredField(r10);
    L4:
        Field[] r0 = r9.getDeclaredFields();
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        Field r3 = r0[r2];
        if (r10.equals(r3.getName()) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        return r3;
    L10:
        LocalList$$ExternalSyntheticBUOutline0.m("Field ", r10, " for ", r9.getName(), " not found. Known fields are ", Arrays.toString(r0));
        return null;
    }

    private void setFieldPresent(T r5, int r6) {
        int r4 = presenceMaskAndOffsetAt(r6);
        long r0 = 1048575 & r4;
        if (r0 != 1048575) goto L5;
        return;
    L5:
        UnsafeUtil.putInt(r5, r0, (1 << (r4 >>> 20)) | UnsafeUtil.getInt(r5, r0));
    }

    private void setOneofPresent(T r3, int r4, int r5) {
        UnsafeUtil.putInt(r3, presenceMaskAndOffsetAt(r5) & 1048575, r4);
    }

    private int slowPositionForFieldNumber(int r5, int r6) {
        int r0 = (this.buffer.length / 3) - 1;
    L3:
        if (r6 > r0) goto L10;
        int r1 = (r0 + r6) >>> 1;
        int r2 = r1 * 3;
        int r3 = numberAt(r2);
        if (r5 == r3) goto L6;
        if (r5 < r3) goto L8;
        r6 = r1 + 1;
        goto L3
    L8:
        r0 = r1 - 1;
        goto L3
    L6:
        return r2;
    L10:
        return -1;
    }

    private void storeMessageField(T r4, int r5, Object r6) {
        UNSAFE.putObject(r4, offset(typeAndOffsetAt(r5)), r6);
        setFieldPresent(r4, r5);
    }

    private void storeOneofMessageField(T r4, int r5, int r6, Object r7) {
        UNSAFE.putObject(r4, offset(typeAndOffsetAt(r6)), r7);
        setOneofPresent(r4, r5, r6);
    }

    private static int type(int r1) {
        return (r1 & 267386880) >>> 20;
    }

    private int typeAndOffsetAt(int r1) {
        return this.buffer[r1 + 1];
    }

    private void writeFieldsInAscendingOrderProto2(T r19, Writer r20) {
        if (this.hasExtensions == false) goto L7;
        FieldSet<T> r3 = this.extensionSchema.getExtensions(r19);
        if (r3.isEmpty() == true) goto L7;
        Iterator r32 = r3.iterator();
        Map.Entry<?, ?> r5 = (Map.Entry) r32.next();
    L8:
        int r6 = this.buffer.length;
        Unsafe r7 = UNSAFE;
        int r10 = 0;
        int r11 = 1048575;
        int r12 = 0;
    L9:
        if (r10 >= r6) goto L169;
        int r13 = typeAndOffsetAt(r10);
        int r14 = numberAt(r10);
        int r15 = type(r13);
        if (r15 > 17) goto L16;
        int r4 = this.buffer[r10 + 2];
        int r9 = r4 & 1048575;
        if (r9 == r11) goto L15;
        r12 = r7.getInt(r19, r9);
        r11 = r9;
    L15:
        int r42 = 1 << (r4 >>> 20);
    L17:
        if (r5 == null) goto L24;
        if (this.extensionSchema.extensionNumber(r5) > r14) goto L24;
        this.extensionSchema.serializeExtension(r20, r5);
        if (r32.hasNext() == true) goto L22;
        r5 = null;
        goto L17
    L22:
        r5 = (Map.Entry) r32.next();
    L24:
        long r8 = offset(r13);
        switch(r15) {
            case 0: goto L166;
            case 1: goto L163;
            case 2: goto L160;
            case 3: goto L157;
            case 4: goto L154;
            case 5: goto L151;
            case 6: goto L148;
            case 7: goto L145;
            case 8: goto L142;
            case 9: goto L139;
            case 10: goto L136;
            case 11: goto L133;
            case 12: goto L130;
            case 13: goto L127;
            case 14: goto L124;
            case 15: goto L121;
            case 16: goto L118;
            case 17: goto L115;
            case 18: goto L113;
            case 19: goto L112;
            case 20: goto L111;
            case 21: goto L110;
            case 22: goto L109;
            case 23: goto L108;
            case 24: goto L107;
            case 25: goto L106;
            case 26: goto L105;
            case 27: goto L104;
            case 28: goto L103;
            case 29: goto L102;
            case 30: goto L101;
            case 31: goto L100;
            case 32: goto L99;
            case 33: goto L98;
            case 34: goto L97;
            case 35: goto L96;
            case 36: goto L95;
            case 37: goto L94;
            case 38: goto L93;
            case 39: goto L92;
            case 40: goto L91;
            case 41: goto L90;
            case 42: goto L89;
            case 43: goto L88;
            case 44: goto L87;
            case 45: goto L86;
            case 46: goto L85;
            case 47: goto L84;
            case 48: goto L83;
            case 49: goto L82;
            case 50: goto L81;
            case 51: goto L79;
            case 52: goto L76;
            case 53: goto L73;
            case 54: goto L70;
            case 55: goto L67;
            case 56: goto L64;
            case 57: goto L61;
            case 58: goto L58;
            case 59: goto L55;
            case 60: goto L52;
            case 61: goto L49;
            case 62: goto L46;
            case 63: goto L43;
            case 64: goto L40;
            case 65: goto L37;
            case 66: goto L34;
            case 67: goto L31;
            case 68: goto L28;
            default: goto L168;
        };
    L81:
        writeMapHelper(r20, r14, r7.getObject(r19, r8), r10);
        goto L168
    L82:
        SchemaUtil.writeGroupList(numberAt(r10), (List) r7.getObject(r19, r8), r20, getMessageFieldSchema(r10));
        goto L168
    L83:
        SchemaUtil.writeSInt64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L84:
        SchemaUtil.writeSInt32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L85:
        SchemaUtil.writeSFixed64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L86:
        SchemaUtil.writeSFixed32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L87:
        SchemaUtil.writeEnumList(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L88:
        SchemaUtil.writeUInt32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L89:
        SchemaUtil.writeBoolList(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L90:
        SchemaUtil.writeFixed32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L91:
        SchemaUtil.writeFixed64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L92:
        SchemaUtil.writeInt32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L93:
        SchemaUtil.writeUInt64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L94:
        SchemaUtil.writeInt64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L95:
        SchemaUtil.writeFloatList(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L96:
        SchemaUtil.writeDoubleList(numberAt(r10), (List) r7.getObject(r19, r8), r20, true);
        goto L168
    L97:
        SchemaUtil.writeSInt64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L98:
        SchemaUtil.writeSInt32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L99:
        SchemaUtil.writeSFixed64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L100:
        SchemaUtil.writeSFixed32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L101:
        SchemaUtil.writeEnumList(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L102:
        SchemaUtil.writeUInt32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L103:
        SchemaUtil.writeBytesList(numberAt(r10), (List) r7.getObject(r19, r8), r20);
        goto L168
    L104:
        SchemaUtil.writeMessageList(numberAt(r10), (List) r7.getObject(r19, r8), r20, getMessageFieldSchema(r10));
        goto L168
    L105:
        SchemaUtil.writeStringList(numberAt(r10), (List) r7.getObject(r19, r8), r20);
        goto L168
    L106:
        SchemaUtil.writeBoolList(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L107:
        SchemaUtil.writeFixed32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L108:
        SchemaUtil.writeFixed64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L109:
        SchemaUtil.writeInt32List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L110:
        SchemaUtil.writeUInt64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L111:
        SchemaUtil.writeInt64List(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L112:
        SchemaUtil.writeFloatList(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
        goto L168
    L113:
        SchemaUtil.writeDoubleList(numberAt(r10), (List) r7.getObject(r19, r8), r20, false);
    L168:
        r10 = r10 + 3;
        goto L9
    L28:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeGroup(r14, r7.getObject(r19, r8), getMessageFieldSchema(r10));
        goto L168
    L31:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeSInt64(r14, oneofLongAt(r19, r8));
        goto L168
    L34:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeSInt32(r14, oneofIntAt(r19, r8));
        goto L168
    L37:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeSFixed64(r14, oneofLongAt(r19, r8));
        goto L168
    L40:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeSFixed32(r14, oneofIntAt(r19, r8));
        goto L168
    L43:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeEnum(r14, oneofIntAt(r19, r8));
        goto L168
    L46:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeUInt32(r14, oneofIntAt(r19, r8));
        goto L168
    L49:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeBytes(r14, (ByteString) r7.getObject(r19, r8));
        goto L168
    L52:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeMessage(r14, r7.getObject(r19, r8), getMessageFieldSchema(r10));
        goto L168
    L55:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        writeString(r14, r7.getObject(r19, r8), r20);
        goto L168
    L58:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeBool(r14, oneofBooleanAt(r19, r8));
        goto L168
    L61:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeFixed32(r14, oneofIntAt(r19, r8));
        goto L168
    L64:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeFixed64(r14, oneofLongAt(r19, r8));
        goto L168
    L67:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeInt32(r14, oneofIntAt(r19, r8));
        goto L168
    L70:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeUInt64(r14, oneofLongAt(r19, r8));
        goto L168
    L73:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeInt64(r14, oneofLongAt(r19, r8));
        goto L168
    L76:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeFloat(r14, oneofFloatAt(r19, r8));
        goto L168
    L79:
        if (isOneofPresent(r19, r14, r10) == false) goto L168;
        r20.writeDouble(r14, oneofDoubleAt(r19, r8));
        goto L168
    L115:
        if ((r42 & r12) == 0) goto L168;
        r20.writeGroup(r14, r7.getObject(r19, r8), getMessageFieldSchema(r10));
        goto L168
    L118:
        if ((r42 & r12) == 0) goto L168;
        r20.writeSInt64(r14, r7.getLong(r19, r8));
        goto L168
    L121:
        if ((r42 & r12) == 0) goto L168;
        r20.writeSInt32(r14, r7.getInt(r19, r8));
        goto L168
    L124:
        if ((r42 & r12) == 0) goto L168;
        r20.writeSFixed64(r14, r7.getLong(r19, r8));
        goto L168
    L127:
        if ((r42 & r12) == 0) goto L168;
        r20.writeSFixed32(r14, r7.getInt(r19, r8));
        goto L168
    L130:
        if ((r42 & r12) == 0) goto L168;
        r20.writeEnum(r14, r7.getInt(r19, r8));
        goto L168
    L133:
        if ((r42 & r12) == 0) goto L168;
        r20.writeUInt32(r14, r7.getInt(r19, r8));
        goto L168
    L136:
        if ((r42 & r12) == 0) goto L168;
        r20.writeBytes(r14, (ByteString) r7.getObject(r19, r8));
        goto L168
    L139:
        if ((r42 & r12) == 0) goto L168;
        r20.writeMessage(r14, r7.getObject(r19, r8), getMessageFieldSchema(r10));
        goto L168
    L142:
        if ((r42 & r12) == 0) goto L168;
        writeString(r14, r7.getObject(r19, r8), r20);
        goto L168
    L145:
        if ((r42 & r12) == 0) goto L168;
        r20.writeBool(r14, booleanAt(r19, r8));
        goto L168
    L148:
        if ((r42 & r12) == 0) goto L168;
        r20.writeFixed32(r14, r7.getInt(r19, r8));
        goto L168
    L151:
        if ((r42 & r12) == 0) goto L168;
        r20.writeFixed64(r14, r7.getLong(r19, r8));
        goto L168
    L154:
        if ((r42 & r12) == 0) goto L168;
        r20.writeInt32(r14, r7.getInt(r19, r8));
        goto L168
    L157:
        if ((r42 & r12) == 0) goto L168;
        r20.writeUInt64(r14, r7.getLong(r19, r8));
        goto L168
    L160:
        if ((r42 & r12) == 0) goto L168;
        r20.writeInt64(r14, r7.getLong(r19, r8));
        goto L168
    L163:
        if ((r42 & r12) == 0) goto L168;
        r20.writeFloat(r14, floatAt(r19, r8));
        goto L168
    L166:
        if ((r42 & r12) == 0) goto L168;
        r20.writeDouble(r14, doubleAt(r19, r8));
        goto L168
    L16:
        r42 = 0;
    L169:
        if (r5 == null) goto L174;
        this.extensionSchema.serializeExtension(r20, r5);
        if (r32.hasNext() == true) goto L172;
        r5 = null;
        goto L169
    L172:
        r5 = (Map.Entry) r32.next();
        goto L169
    L174:
        writeUnknownInMessageTo(this.unknownFieldSchema, r19, r20);
        return;
    L7:
        r32 = null;
        r5 = null;
        goto L8
    }

    private void writeFieldsInAscendingOrderProto3(T r13, Writer r14) {
        if (this.hasExtensions == false) goto L7;
        FieldSet<T> r0 = this.extensionSchema.getExtensions(r13);
        if (r0.isEmpty() == true) goto L7;
        Iterator r02 = r0.iterator();
        Map.Entry<?, ?> r2 = (Map.Entry) r02.next();
    L8:
        int r3 = this.buffer.length;
        int r5 = 0;
    L9:
        if (r5 >= r3) goto L163;
        int r6 = typeAndOffsetAt(r5);
        int r7 = numberAt(r5);
    L11:
        if (r2 == null) goto L19;
        if (this.extensionSchema.extensionNumber(r2) > r7) goto L19;
        this.extensionSchema.serializeExtension(r14, r2);
        if (r02.hasNext() == true) goto L16;
        r2 = null;
        goto L11
    L16:
        r2 = (Map.Entry) r02.next();
    L19:
        switch(type(r6)) {
            case 0: goto L160;
            case 1: goto L157;
            case 2: goto L154;
            case 3: goto L151;
            case 4: goto L148;
            case 5: goto L145;
            case 6: goto L142;
            case 7: goto L139;
            case 8: goto L136;
            case 9: goto L133;
            case 10: goto L130;
            case 11: goto L127;
            case 12: goto L124;
            case 13: goto L121;
            case 14: goto L118;
            case 15: goto L115;
            case 16: goto L112;
            case 17: goto L109;
            case 18: goto L107;
            case 19: goto L106;
            case 20: goto L105;
            case 21: goto L104;
            case 22: goto L103;
            case 23: goto L102;
            case 24: goto L101;
            case 25: goto L100;
            case 26: goto L99;
            case 27: goto L98;
            case 28: goto L97;
            case 29: goto L96;
            case 30: goto L95;
            case 31: goto L94;
            case 32: goto L93;
            case 33: goto L92;
            case 34: goto L91;
            case 35: goto L90;
            case 36: goto L89;
            case 37: goto L88;
            case 38: goto L87;
            case 39: goto L86;
            case 40: goto L85;
            case 41: goto L84;
            case 42: goto L83;
            case 43: goto L82;
            case 44: goto L81;
            case 45: goto L80;
            case 46: goto L79;
            case 47: goto L78;
            case 48: goto L77;
            case 49: goto L76;
            case 50: goto L75;
            case 51: goto L73;
            case 52: goto L70;
            case 53: goto L67;
            case 54: goto L64;
            case 55: goto L61;
            case 56: goto L58;
            case 57: goto L55;
            case 58: goto L52;
            case 59: goto L49;
            case 60: goto L46;
            case 61: goto L43;
            case 62: goto L40;
            case 63: goto L37;
            case 64: goto L34;
            case 65: goto L31;
            case 66: goto L28;
            case 67: goto L25;
            case 68: goto L22;
            default: goto L162;
        };
    L75:
        writeMapHelper(r14, r7, UnsafeUtil.getObject(r13, offset(r6)), r5);
        goto L162
    L76:
        SchemaUtil.writeGroupList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, getMessageFieldSchema(r5));
        goto L162
    L77:
        SchemaUtil.writeSInt64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L78:
        SchemaUtil.writeSInt32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L79:
        SchemaUtil.writeSFixed64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L80:
        SchemaUtil.writeSFixed32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L81:
        SchemaUtil.writeEnumList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L82:
        SchemaUtil.writeUInt32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L83:
        SchemaUtil.writeBoolList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L84:
        SchemaUtil.writeFixed32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L85:
        SchemaUtil.writeFixed64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L86:
        SchemaUtil.writeInt32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L87:
        SchemaUtil.writeUInt64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L88:
        SchemaUtil.writeInt64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L89:
        SchemaUtil.writeFloatList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L90:
        SchemaUtil.writeDoubleList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, true);
        goto L162
    L91:
        SchemaUtil.writeSInt64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L92:
        SchemaUtil.writeSInt32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L93:
        SchemaUtil.writeSFixed64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L94:
        SchemaUtil.writeSFixed32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L95:
        SchemaUtil.writeEnumList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L96:
        SchemaUtil.writeUInt32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L97:
        SchemaUtil.writeBytesList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14);
        goto L162
    L98:
        SchemaUtil.writeMessageList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, getMessageFieldSchema(r5));
        goto L162
    L99:
        SchemaUtil.writeStringList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14);
        goto L162
    L100:
        SchemaUtil.writeBoolList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L101:
        SchemaUtil.writeFixed32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L102:
        SchemaUtil.writeFixed64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L103:
        SchemaUtil.writeInt32List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L104:
        SchemaUtil.writeUInt64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L105:
        SchemaUtil.writeInt64List(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L106:
        SchemaUtil.writeFloatList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L107:
        SchemaUtil.writeDoubleList(numberAt(r5), (List) UnsafeUtil.getObject(r13, offset(r6)), r14, false);
        goto L162
    L22:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeGroup(r7, UnsafeUtil.getObject(r13, offset(r6)), getMessageFieldSchema(r5));
        goto L162
    L25:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeSInt64(r7, oneofLongAt(r13, offset(r6)));
        goto L162
    L28:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeSInt32(r7, oneofIntAt(r13, offset(r6)));
        goto L162
    L31:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeSFixed64(r7, oneofLongAt(r13, offset(r6)));
        goto L162
    L34:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeSFixed32(r7, oneofIntAt(r13, offset(r6)));
        goto L162
    L37:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeEnum(r7, oneofIntAt(r13, offset(r6)));
        goto L162
    L40:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeUInt32(r7, oneofIntAt(r13, offset(r6)));
        goto L162
    L43:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeBytes(r7, (ByteString) UnsafeUtil.getObject(r13, offset(r6)));
        goto L162
    L46:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeMessage(r7, UnsafeUtil.getObject(r13, offset(r6)), getMessageFieldSchema(r5));
        goto L162
    L49:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        writeString(r7, UnsafeUtil.getObject(r13, offset(r6)), r14);
        goto L162
    L52:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeBool(r7, oneofBooleanAt(r13, offset(r6)));
        goto L162
    L55:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeFixed32(r7, oneofIntAt(r13, offset(r6)));
        goto L162
    L58:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeFixed64(r7, oneofLongAt(r13, offset(r6)));
        goto L162
    L61:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeInt32(r7, oneofIntAt(r13, offset(r6)));
        goto L162
    L64:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeUInt64(r7, oneofLongAt(r13, offset(r6)));
        goto L162
    L67:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeInt64(r7, oneofLongAt(r13, offset(r6)));
        goto L162
    L70:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeFloat(r7, oneofFloatAt(r13, offset(r6)));
        goto L162
    L73:
        if (isOneofPresent(r13, r7, r5) == false) goto L162;
        r14.writeDouble(r7, oneofDoubleAt(r13, offset(r6)));
        goto L162
    L109:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeGroup(r7, UnsafeUtil.getObject(r13, offset(r6)), getMessageFieldSchema(r5));
        goto L162
    L112:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeSInt64(r7, longAt(r13, offset(r6)));
        goto L162
    L115:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeSInt32(r7, intAt(r13, offset(r6)));
        goto L162
    L118:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeSFixed64(r7, longAt(r13, offset(r6)));
        goto L162
    L121:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeSFixed32(r7, intAt(r13, offset(r6)));
        goto L162
    L124:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeEnum(r7, intAt(r13, offset(r6)));
        goto L162
    L127:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeUInt32(r7, intAt(r13, offset(r6)));
        goto L162
    L130:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeBytes(r7, (ByteString) UnsafeUtil.getObject(r13, offset(r6)));
        goto L162
    L133:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeMessage(r7, UnsafeUtil.getObject(r13, offset(r6)), getMessageFieldSchema(r5));
        goto L162
    L136:
        if (isFieldPresent(r13, r5) == false) goto L162;
        writeString(r7, UnsafeUtil.getObject(r13, offset(r6)), r14);
        goto L162
    L139:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeBool(r7, booleanAt(r13, offset(r6)));
        goto L162
    L142:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeFixed32(r7, intAt(r13, offset(r6)));
        goto L162
    L145:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeFixed64(r7, longAt(r13, offset(r6)));
        goto L162
    L148:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeInt32(r7, intAt(r13, offset(r6)));
        goto L162
    L151:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeUInt64(r7, longAt(r13, offset(r6)));
        goto L162
    L154:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeInt64(r7, longAt(r13, offset(r6)));
        goto L162
    L157:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeFloat(r7, floatAt(r13, offset(r6)));
        goto L162
    L160:
        if (isFieldPresent(r13, r5) == false) goto L162;
        r14.writeDouble(r7, doubleAt(r13, offset(r6)));
    L162:
        r5 = r5 + 3;
    L163:
        if (r2 == null) goto L168;
        this.extensionSchema.serializeExtension(r14, r2);
        if (r02.hasNext() == true) goto L166;
        r2 = null;
        goto L163
    L166:
        r2 = (Map.Entry) r02.next();
        goto L163
    L168:
        writeUnknownInMessageTo(this.unknownFieldSchema, r13, r14);
        return;
    L7:
        r02 = null;
        r2 = null;
        goto L8
    }

    private void writeFieldsInDescendingOrder(T r11, Writer r12) {
        writeUnknownInMessageTo(this.unknownFieldSchema, r11, r12);
        if (this.hasExtensions == false) goto L7;
        FieldSet<T> r0 = this.extensionSchema.getExtensions(r11);
        if (r0.isEmpty() == true) goto L7;
        Iterator r02 = r0.descendingIterator();
        Map.Entry<?, ?> r2 = (Map.Entry) r02.next();
    L8:
        int r3 = this.buffer.length - 3;
    L9:
        if (r3 < 0) goto L163;
        int r4 = typeAndOffsetAt(r3);
        int r5 = numberAt(r3);
    L11:
        if (r2 == null) goto L19;
        if (this.extensionSchema.extensionNumber(r2) <= r5) goto L19;
        this.extensionSchema.serializeExtension(r12, r2);
        if (r02.hasNext() == true) goto L16;
        r2 = null;
        goto L11
    L16:
        r2 = (Map.Entry) r02.next();
    L19:
        switch(type(r4)) {
            case 0: goto L160;
            case 1: goto L157;
            case 2: goto L154;
            case 3: goto L151;
            case 4: goto L148;
            case 5: goto L145;
            case 6: goto L142;
            case 7: goto L139;
            case 8: goto L136;
            case 9: goto L133;
            case 10: goto L130;
            case 11: goto L127;
            case 12: goto L124;
            case 13: goto L121;
            case 14: goto L118;
            case 15: goto L115;
            case 16: goto L112;
            case 17: goto L109;
            case 18: goto L107;
            case 19: goto L106;
            case 20: goto L105;
            case 21: goto L104;
            case 22: goto L103;
            case 23: goto L102;
            case 24: goto L101;
            case 25: goto L100;
            case 26: goto L99;
            case 27: goto L98;
            case 28: goto L97;
            case 29: goto L96;
            case 30: goto L95;
            case 31: goto L94;
            case 32: goto L93;
            case 33: goto L92;
            case 34: goto L91;
            case 35: goto L90;
            case 36: goto L89;
            case 37: goto L88;
            case 38: goto L87;
            case 39: goto L86;
            case 40: goto L85;
            case 41: goto L84;
            case 42: goto L83;
            case 43: goto L82;
            case 44: goto L81;
            case 45: goto L80;
            case 46: goto L79;
            case 47: goto L78;
            case 48: goto L77;
            case 49: goto L76;
            case 50: goto L75;
            case 51: goto L73;
            case 52: goto L70;
            case 53: goto L67;
            case 54: goto L64;
            case 55: goto L61;
            case 56: goto L58;
            case 57: goto L55;
            case 58: goto L52;
            case 59: goto L49;
            case 60: goto L46;
            case 61: goto L43;
            case 62: goto L40;
            case 63: goto L37;
            case 64: goto L34;
            case 65: goto L31;
            case 66: goto L28;
            case 67: goto L25;
            case 68: goto L22;
            default: goto L162;
        };
    L75:
        writeMapHelper(r12, r5, UnsafeUtil.getObject(r11, offset(r4)), r3);
        goto L162
    L76:
        SchemaUtil.writeGroupList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, getMessageFieldSchema(r3));
        goto L162
    L77:
        SchemaUtil.writeSInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L78:
        SchemaUtil.writeSInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L79:
        SchemaUtil.writeSFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L80:
        SchemaUtil.writeSFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L81:
        SchemaUtil.writeEnumList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L82:
        SchemaUtil.writeUInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L83:
        SchemaUtil.writeBoolList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L84:
        SchemaUtil.writeFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L85:
        SchemaUtil.writeFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L86:
        SchemaUtil.writeInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L87:
        SchemaUtil.writeUInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L88:
        SchemaUtil.writeInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L89:
        SchemaUtil.writeFloatList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L90:
        SchemaUtil.writeDoubleList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L91:
        SchemaUtil.writeSInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L92:
        SchemaUtil.writeSInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L93:
        SchemaUtil.writeSFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L94:
        SchemaUtil.writeSFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L95:
        SchemaUtil.writeEnumList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L96:
        SchemaUtil.writeUInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L97:
        SchemaUtil.writeBytesList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L98:
        SchemaUtil.writeMessageList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, getMessageFieldSchema(r3));
        goto L162
    L99:
        SchemaUtil.writeStringList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L100:
        SchemaUtil.writeBoolList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L101:
        SchemaUtil.writeFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L102:
        SchemaUtil.writeFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L103:
        SchemaUtil.writeInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L104:
        SchemaUtil.writeUInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L105:
        SchemaUtil.writeInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L106:
        SchemaUtil.writeFloatList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L107:
        SchemaUtil.writeDoubleList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L22:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeGroup(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L25:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSInt64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L28:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSInt32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L31:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSFixed64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L34:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSFixed32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L37:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeEnum(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L40:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeUInt32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L43:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeBytes(r5, (ByteString) UnsafeUtil.getObject(r11, offset(r4)));
        goto L162
    L46:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeMessage(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L49:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        writeString(r5, UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L52:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeBool(r5, oneofBooleanAt(r11, offset(r4)));
        goto L162
    L55:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeFixed32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L58:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeFixed64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L61:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeInt32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L64:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeUInt64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L67:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeInt64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L70:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeFloat(r5, oneofFloatAt(r11, offset(r4)));
        goto L162
    L73:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeDouble(r5, oneofDoubleAt(r11, offset(r4)));
        goto L162
    L109:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeGroup(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L112:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSInt64(r5, longAt(r11, offset(r4)));
        goto L162
    L115:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSInt32(r5, intAt(r11, offset(r4)));
        goto L162
    L118:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSFixed64(r5, longAt(r11, offset(r4)));
        goto L162
    L121:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSFixed32(r5, intAt(r11, offset(r4)));
        goto L162
    L124:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeEnum(r5, intAt(r11, offset(r4)));
        goto L162
    L127:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeUInt32(r5, intAt(r11, offset(r4)));
        goto L162
    L130:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeBytes(r5, (ByteString) UnsafeUtil.getObject(r11, offset(r4)));
        goto L162
    L133:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeMessage(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L136:
        if (isFieldPresent(r11, r3) == false) goto L162;
        writeString(r5, UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L139:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeBool(r5, booleanAt(r11, offset(r4)));
        goto L162
    L142:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeFixed32(r5, intAt(r11, offset(r4)));
        goto L162
    L145:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeFixed64(r5, longAt(r11, offset(r4)));
        goto L162
    L148:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeInt32(r5, intAt(r11, offset(r4)));
        goto L162
    L151:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeUInt64(r5, longAt(r11, offset(r4)));
        goto L162
    L154:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeInt64(r5, longAt(r11, offset(r4)));
        goto L162
    L157:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeFloat(r5, floatAt(r11, offset(r4)));
        goto L162
    L160:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeDouble(r5, doubleAt(r11, offset(r4)));
    L162:
        r3 = r3 - 3;
    L163:
        if (r2 == null) goto L168;
        this.extensionSchema.serializeExtension(r12, r2);
        if (r02.hasNext() == true) goto L166;
        r2 = null;
        goto L163
    L166:
        r2 = (Map.Entry) r02.next();
        goto L163
    L168:
        return;
    L7:
        r02 = null;
        r2 = null;
        goto L8
    }

    private <K, V> void writeMapHelper(Writer r2, int r3, Object r4, int r5) {
        if (r4 == null) goto L5;
        r2.writeMap(r3, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(r5)), this.mapFieldSchema.forMapData(r4));
        return;
    }

    private void writeString(int r1, Object r2, Writer r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.writeString(r1, (String) r2);
        return;
    L6:
        r3.writeBytes(r1, (ByteString) r2);
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> r1, T r2, Writer r3) {
        r1.writeTo(r1.getFromMessage(r2), r3);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T r5, T r6) {
        int r0 = this.buffer.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (equals(r5, r6, r2) == false) goto L6;
        r2 = r2 + 3;
        goto L3
    L6:
        return false;
    L9:
        if (this.unknownFieldSchema.getFromMessage(r5).equals(this.unknownFieldSchema.getFromMessage(r6)) == true) goto L12;
        return false;
    L12:
        if (this.hasExtensions == true) goto L14;
        return true;
    L14:
        return this.extensionSchema.getExtensions(r5).equals(this.extensionSchema.getExtensions(r6));
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T r2) {
        if (this.proto3 == false) goto L7;
        return getSerializedSizeProto3(r2);
    L7:
        return getSerializedSizeProto2(r2);
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T r9) {
        int r0 = this.buffer.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L88;
        int r3 = typeAndOffsetAt(r1);
        int r4 = numberAt(r1);
        long r5 = offset(r3);
        int r7 = 37;
        switch(type(r3)) {
            case 0: goto L86;
            case 1: goto L85;
            case 2: goto L84;
            case 3: goto L83;
            case 4: goto L82;
            case 5: goto L81;
            case 6: goto L80;
            case 7: goto L79;
            case 8: goto L78;
            case 9: goto L75;
            case 10: goto L74;
            case 11: goto L73;
            case 12: goto L72;
            case 13: goto L71;
            case 14: goto L70;
            case 15: goto L69;
            case 16: goto L68;
            case 17: goto L64;
            case 18: goto L63;
            case 19: goto L63;
            case 20: goto L63;
            case 21: goto L63;
            case 22: goto L63;
            case 23: goto L63;
            case 24: goto L63;
            case 25: goto L63;
            case 26: goto L63;
            case 27: goto L63;
            case 28: goto L63;
            case 29: goto L63;
            case 30: goto L63;
            case 31: goto L63;
            case 32: goto L63;
            case 33: goto L63;
            case 34: goto L63;
            case 35: goto L63;
            case 36: goto L63;
            case 37: goto L63;
            case 38: goto L63;
            case 39: goto L63;
            case 40: goto L63;
            case 41: goto L63;
            case 42: goto L63;
            case 43: goto L63;
            case 44: goto L63;
            case 45: goto L63;
            case 46: goto L63;
            case 47: goto L63;
            case 48: goto L63;
            case 49: goto L63;
            case 50: goto L62;
            case 51: goto L60;
            case 52: goto L57;
            case 53: goto L54;
            case 54: goto L51;
            case 55: goto L48;
            case 56: goto L45;
            case 57: goto L42;
            case 58: goto L39;
            case 59: goto L36;
            case 60: goto L33;
            case 61: goto L30;
            case 62: goto L27;
            case 63: goto L24;
            case 64: goto L21;
            case 65: goto L18;
            case 66: goto L15;
            case 67: goto L12;
            case 68: goto L8;
            default: goto L87;
        };
    L62:
        int r22 = r2 * 53;
        int r32 = UnsafeUtil.getObject(r9, r5).hashCode();
    L10:
        r2 = r22 + r32;
        goto L87
    L63:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L64:
        Object r33 = UnsafeUtil.getObject(r9, r5);
        if (r33 == null) goto L67;
        r7 = r33.hashCode();
    L67:
        r2 = (r2 * 53) + r7;
        goto L87
    L68:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L69:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L70:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L71:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L72:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L73:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L74:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L75:
        Object r34 = UnsafeUtil.getObject(r9, r5);
        if (r34 == null) goto L67;
        r7 = r34.hashCode();
        goto L67
    L78:
        r22 = r2 * 53;
        r32 = ((String) UnsafeUtil.getObject(r9, r5)).hashCode();
        goto L10
    L79:
        r22 = r2 * 53;
        r32 = Internal.hashBoolean(UnsafeUtil.getBoolean(r9, r5));
        goto L10
    L80:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L81:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L82:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L83:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L84:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L85:
        r22 = r2 * 53;
        r32 = Float.floatToIntBits(UnsafeUtil.getFloat(r9, r5));
        goto L10
    L86:
        r22 = r2 * 53;
        r32 = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(r9, r5)));
        goto L10
    L8:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L12:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L15:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L18:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L21:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L24:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L27:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L30:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L33:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L36:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = ((String) UnsafeUtil.getObject(r9, r5)).hashCode();
        goto L10
    L39:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashBoolean(oneofBooleanAt(r9, r5));
        goto L10
    L42:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L45:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L48:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L51:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L54:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L57:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Float.floatToIntBits(oneofFloatAt(r9, r5));
        goto L10
    L60:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(r9, r5)));
    L87:
        r1 = r1 + 3;
        goto L3
    L88:
        int r23 = (r2 * 53) + this.unknownFieldSchema.getFromMessage(r9).hashCode();
        if (this.hasExtensions == true) goto L91;
        return r23;
    L91:
        return (r23 * 53) + this.extensionSchema.getExtensions(r9).hashCode();
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(T r15) {
        int r3 = 1048575;
        int r2 = 0;
        int r4 = 0;
    L4:
        if (r2 >= this.checkInitializedCount) goto L48;
        int r9 = this.intArray[r2];
        int r5 = this.numberAt(r9);
        int r13 = this.typeAndOffsetAt(r9);
        int r7 = this.buffer[r9 + 2];
        int r8 = r7 & 1048575;
        int r12 = 1 << (r7 >>> 20);
        if (r8 == r3) goto L10;
        if (r8 == 1048575) goto L9;
        r4 = UNSAFE.getInt(r15, r8);
    L9:
        int r11 = r4;
        int r10 = r8;
    L11:
        MessageSchema<T> r72 = this;
        T r82 = r15;
        if (isRequired(r13) == true) goto L14;
    L16:
        int r14 = type(r13);
        if (r14 == 9) goto L43;
        if (r14 == 17) goto L43;
        if (r14 == 27) goto L40;
        if (r14 == 60) goto L35;
        if (r14 == 68) goto L35;
        if (r14 == 49) goto L40;
        if (r14 != 50) goto L47;
        if (r72.isMapInitialized(r82, r13, r9) == true) goto L47;
        return false;
    L47:
        r2 = r2 + 1;
        MessageSchema<T> this = r72;
        r15 = r82;
        r3 = r10;
        r4 = r11;
    L35:
        if (r72.isOneofPresent(r82, r5, r9) == false) goto L47;
        if (isInitialized(r82, r13, r72.getMessageFieldSchema(r9)) == true) goto L47;
        return false;
    L40:
        if (r72.isListInitialized(r82, r13, r9) == true) goto L47;
        return false;
    L43:
        if (r72.isFieldPresent(r82, r9, r10, r11, r12) == false) goto L47;
        if (isInitialized(r82, r13, r72.getMessageFieldSchema(r9)) == true) goto L47;
        return false;
    L14:
        if (r72.isFieldPresent(r82, r9, r10, r11, r12) == true) goto L16;
        return false;
    L10:
        r10 = r3;
        r11 = r4;
        goto L11
    L48:
        MessageSchema<T> r73 = this;
        T r83 = r15;
        if (r73.hasExtensions == true) goto L51;
    L53:
        return true;
    L51:
        if (r73.extensionSchema.getExtensions(r83).isInitialized() == true) goto L53;
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.Schema
    public void makeImmutable(T r8) {
        if (isMutable(r8) == true) goto L6;
        return;
    L6:
        if ((r8 instanceof GeneratedMessageLite) == false) goto L8;
        GeneratedMessageLite r0 = (GeneratedMessageLite) r8;
        r0.clearMemoizedSerializedSize();
        r0.clearMemoizedHashCode();
        r0.markImmutable();
    L8:
        int r02 = this.buffer.length;
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L29;
        int r2 = typeAndOffsetAt(r1);
        long r3 = offset(r2);
        int r22 = type(r2);
        if (r22 == 9) goto L26;
        if (r22 == 60) goto L23;
        if (r22 == 68) goto L23;
        switch(r22) {
            case 17: goto L26;
            case 18: goto L21;
            case 19: goto L21;
            case 20: goto L21;
            case 21: goto L21;
            case 22: goto L21;
            case 23: goto L21;
            case 24: goto L21;
            case 25: goto L21;
            case 26: goto L21;
            case 27: goto L21;
            case 28: goto L21;
            case 29: goto L21;
            case 30: goto L21;
            case 31: goto L21;
            case 32: goto L21;
            case 33: goto L21;
            case 34: goto L21;
            case 35: goto L21;
            case 36: goto L21;
            case 37: goto L21;
            case 38: goto L21;
            case 39: goto L21;
            case 40: goto L21;
            case 41: goto L21;
            case 42: goto L21;
            case 43: goto L21;
            case 44: goto L21;
            case 45: goto L21;
            case 46: goto L21;
            case 47: goto L21;
            case 48: goto L21;
            case 49: goto L21;
            case 50: goto L18;
            default: goto L28;
        };
    L18:
        Unsafe r23 = UNSAFE;
        Object r5 = r23.getObject(r8, r3);
        if (r5 == null) goto L28;
        r23.putObject(r8, r3, this.mapFieldSchema.toImmutable(r5));
        goto L28
    L21:
        this.listFieldSchema.makeImmutableListAt(r8, r3);
    L28:
        r1 = r1 + 3;
    L23:
        if (isOneofPresent(r8, numberAt(r1), r1) == false) goto L28;
        getMessageFieldSchema(r1).makeImmutable(UNSAFE.getObject(r8, r3));
    L26:
        if (isFieldPresent(r8, r1) == false) goto L28;
        getMessageFieldSchema(r1).makeImmutable(UNSAFE.getObject(r8, r3));
        goto L28
    L29:
        this.unknownFieldSchema.makeImmutable(r8);
        if (this.hasExtensions == false) goto L42;
        this.extensionSchema.makeImmutable(r8);
        return;
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T r7, Reader r8, ExtensionRegistryLite r9) {
        r9.getClass();
        checkMutable(r7);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, r7, r8, r9);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T r3, T r4) {
        checkMutable(r3);
        r4.getClass();
        int r0 = 0;
    L4:
        if (r0 >= this.buffer.length) goto L6;
        mergeSingleField(r3, r4, r0);
        r0 = r0 + 3;
        goto L4
    L6:
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, r3, r4);
        if (this.hasExtensions == false) goto L11;
        SchemaUtil.mergeExtensions(this.extensionSchema, r3, r4);
        return;
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T r9, byte[] r10, int r11, int r12, ArrayDecoders.Registers r13) {
        if (this.proto3 == false) goto L6;
        parseProto3Message(r9, r10, r11, r12, r13);
        return;
    L6:
        parseProto2Message(r9, r10, r11, r12, 0, r13);
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    public int parseProto2Message(T r27, byte[] r28, int r29, int r30, int r31, ArrayDecoders.Registers r32) {
        MessageSchema<T> r0 = this;
        T r1 = r27;
        byte[] r2 = r28;
        int r4 = r30;
        ArrayDecoders.Registers r7 = r32;
        checkMutable(r1);
        Unsafe r9 = UNSAFE;
        int r11 = -1;
        int r3 = r29;
        int r5 = -1;
        int r6 = 0;
        int r12 = 0;
        int r13 = 0;
        int r8 = 1048575;
    L3:
        if (r3 >= r4) goto L143;
        int r132 = r3 + 1;
        int r33 = r2[r3];
        if (r33 >= 0) goto L7;
        r132 = ArrayDecoders.decodeVarint32(r33, r2, r132, r7);
        r33 = r7.int1;
    L7:
        int r25 = r132;
        int r133 = r33;
        int r14 = r133 >>> 3;
        int r72 = r133 & 7;
        if (r14 <= r5) goto L10;
        int r52 = r0.positionForFieldNumber(r14, r6 / 3);
    L11:
        if (r52 == r11) goto L12;
        int r62 = r0.buffer[r52 + 1];
        int r18 = r11;
        int r112 = type(r62);
        long r34 = offset(r62);
        if (r112 <= 17) goto L15;
        int r21 = r133;
        int r134 = r52;
        Unsafe r35 = r9;
        if (r112 == 27) goto L99;
        int r36 = r25;
        if (r112 <= 49) goto L111;
        r21 = r21;
        Unsafe r24 = r35;
        int r92 = r14;
        int r19 = r18;
        int r17 = r8;
        int r182 = r12;
        int r122 = r134;
        if (r112 == 50) goto L120;
        int r73 = parseOneofField(r27, r28, r36, r30, r21, r92, r72, r62, r112, r34, r122, r32);
        MessageSchema<T> r82 = this;
        int r02 = r21;
        r92 = r92;
        ArrayDecoders.Registers r142 = r32;
        if (r73 == r36) goto L130;
        r1 = r27;
        r2 = r28;
        r4 = r30;
        r13 = r02;
        r3 = r73;
        r0 = r82;
        r5 = r92;
        r6 = r122;
        r7 = r142;
        r8 = r17;
        r12 = r182;
        r11 = r19;
    L124:
        r9 = r24;
        goto L3
    L130:
        int r22 = r73;
    L131:
        if (r02 != r31) goto L136;
        if (r31 == 0) goto L136;
        T r63 = r27;
        int r74 = r30;
        r13 = r02;
        int r93 = r22;
        int r03 = r17;
        r12 = r182;
    L144:
        if (r03 == 1048575) goto L146;
        r24.putInt(r63, r03, r12);
    L146:
        int r10 = r82.checkInitializedCount;
        UnknownFieldSetLite r37 = null;
    L148:
        if (r10 >= r82.repeatedFieldOffsetStart) goto L150;
        MessageSchema<T> r04 = r82;
        r37 = (UnknownFieldSetLite) r04.filterMapUnknownEnumValues(r63, r82.intArray[r10], r37, r82.unknownFieldSchema, r27);
        r10 = r10 + 1;
        goto L148
    L150:
        T r15 = r63;
        MessageSchema<T> r05 = r82;
        if (r37 == null) goto L153;
        r05.unknownFieldSchema.setBuilderToMessage(r15, r37);
    L153:
        if (r31 != 0) goto L158;
        if (r93 != r74) goto L157;
    L160:
        return r93;
    L157:
        throw InvalidProtocolBufferException.parseFailure();
    L158:
        if (r93 > r74) goto L162;
        if (r13 == r31) goto L160;
    L162:
        throw InvalidProtocolBufferException.parseFailure();
    L136:
        if (r82.hasExtensions == true) goto L138;
    L141:
        T r64 = r27;
        int r23 = ArrayDecoders.decodeUnknownField(r02, r28, r22, r30, getMutableUnknownFields(r64), r32);
        int r75 = r30;
    L140:
        r3 = r23;
        r2 = r28;
        r13 = r02;
        r1 = r64;
        r4 = r75;
        r0 = r82;
        r5 = r92;
        r6 = r122;
        r8 = r17;
        r12 = r182;
        r11 = r19;
    L114:
        r9 = r24;
        r7 = r32;
        goto L3
    L138:
        if (r142.extensionRegistry == ExtensionRegistryLite.getEmptyRegistry()) goto L141;
        r23 = ArrayDecoders.decodeExtensionOrUnknownField(r02, r28, r22, r30, r27, r82.defaultInstance, r82.unknownFieldSchema, r142);
        r64 = r27;
        r75 = r30;
        goto L140
    L120:
        if (r72 != 2) goto L126;
        int r65 = parseMapField(r27, r28, r36, r30, r122, r34, r32);
        if (r65 == r36) goto L125;
        r0 = this;
        r1 = r27;
        r2 = r28;
        r4 = r30;
        r7 = r32;
        r3 = r65;
        r5 = r92;
        r6 = r122;
        r8 = r17;
        r12 = r182;
        r11 = r19;
        r13 = r21;
        goto L124
    L125:
        r82 = this;
        r142 = r32;
        r22 = r65;
    L116:
        r02 = r21;
    L126:
        r82 = this;
        r142 = r32;
        r22 = r36;
        goto L116
    L111:
        r24 = r35;
        r19 = r18;
        r17 = r8;
        r182 = r12;
        int r76 = r0.parseRepeatedField(r27, r28, r36, r30, r21, r14, r72, r134, r62, r112, r34, r32);
        r21 = r21;
        r92 = r14;
        r122 = r134;
        if (r76 == r36) goto L115;
        r0 = this;
        r1 = r27;
        r2 = r28;
        r4 = r30;
        r3 = r76;
        r5 = r92;
        r6 = r122;
        r8 = r17;
        r12 = r182;
        r11 = r19;
        r13 = r21;
        goto L114
    L115:
        r82 = this;
        r142 = r32;
        r22 = r76;
        goto L116
    L99:
        if (r72 != 2) goto L108;
        Internal.ProtobufList r26 = (Internal.ProtobufList) r35.getObject(r1, r34);
        if (r26.isModifiable() == true) goto L107;
        int r66 = r26.size();
        if (r66 != 0) goto L105;
        int r67 = 10;
    L106:
        r26 = r26.mutableCopyWithCapacity(r67);
        r35.putObject(r1, r34, r26);
        goto L107
    L105:
        r67 = r66 * 2;
    L107:
        r9 = r35;
        r4 = r30;
        r7 = r32;
        r3 = ArrayDecoders.decodeMessageList(r0.getMessageFieldSchema(r134), r21, r28, r25, r30, r26, r32);
        r6 = r134;
        r5 = r14;
        r11 = r18;
        r1 = r27;
        r13 = r21;
        r2 = r28;
        goto L3
    L108:
        r24 = r35;
        r92 = r14;
        r36 = r25;
        r17 = r8;
        r19 = r18;
        r182 = r12;
        r122 = r134;
        goto L126
    L15:
        int r102 = r0.buffer[r52 + 2];
        boolean r210 = true;
        int r20 = 1 << (r102 >>> 20);
        int r103 = r102 & 1048575;
        if (r103 == r8) goto L21;
        int r172 = r14;
        if (r8 == 1048575) goto L20;
        r9.putInt(r1, r8, r12);
    L20:
        r12 = r9.getInt(r1, r103);
    L23:
        switch(r112) {
            case 0: goto L92;
            case 1: goto L88;
            case 2: goto L85;
            case 3: goto L85;
            case 4: goto L82;
            case 5: goto L75;
            case 6: goto L71;
            case 7: goto L64;
            case 8: goto L56;
            case 9: goto L49;
            case 10: goto L46;
            case 11: goto L82;
            case 12: goto L43;
            case 13: goto L71;
            case 14: goto L75;
            case 15: goto L38;
            case 16: goto L32;
            case 17: goto L27;
            default: goto L24;
        };
    L24:
        int r135 = r52;
    L25:
        Unsafe r38 = r9;
        int r113 = r25;
        ArrayDecoders.Registers r94 = r32;
    L95:
        r82 = r0;
        r24 = r38;
        r142 = r94;
        r22 = r113;
        r92 = r172;
        r19 = r18;
        r02 = r133;
        r17 = r103;
        r182 = r12;
        r122 = r135;
        goto L131
    L75:
        byte[] r143 = r28;
        r135 = r52;
        Unsafe r114 = r9;
        T r95 = r1;
        if (r72 != 1) goto L80;
        r94 = r32;
        r114.putLong(r95, r34, ArrayDecoders.decodeFixed64(r143, r25));
        Unsafe r211 = r114;
        r1 = r95;
        r3 = r25 + 8;
        r12 = r12 | r20;
        r4 = r30;
    L78:
        r7 = r94;
        r8 = r103;
        r6 = r135;
        r5 = r172;
        r11 = r18;
        r13 = r133;
        r9 = r211;
    L79:
        r2 = r143;
        goto L3
    L80:
        r211 = r114;
        r113 = r25;
        r94 = r32;
    L81:
        r38 = r211;
        goto L95
    L82:
        r143 = r28;
        r135 = r52;
        r211 = r9;
        r113 = r25;
        r94 = r32;
        if (r72 != 0) goto L81;
        int r53 = ArrayDecoders.decodeVarint32(r143, r113, r94);
        r211.putInt(r1, r34, r94.int1);
        r12 = r12 | r20;
        r4 = r30;
        r3 = r53;
        goto L78
    L88:
        r143 = r28;
        r135 = r52;
        r113 = r25;
        r38 = r9;
        r94 = r32;
        if (r72 != 5) goto L95;
        UnsafeUtil.putFloat(r1, r34, ArrayDecoders.decodeFloat(r143, r113));
        int r212 = r113 + 4;
    L91:
        r12 = r12 | r20;
        r4 = r30;
        r7 = r94;
        r8 = r103;
        r6 = r135;
        r5 = r172;
        r11 = r18;
        r13 = r133;
        r9 = r38;
        r3 = r212;
        goto L79
    L92:
        r143 = r28;
        r135 = r52;
        r113 = r25;
        r38 = r9;
        r94 = r32;
        if (r72 != 1) goto L95;
        UnsafeUtil.putDouble(r1, r34, ArrayDecoders.decodeDouble(r143, r113));
        r212 = r113 + 8;
        goto L91
    L27:
        if (r72 != 3) goto L31;
        Object r213 = r0.mutableMessageFieldForMerge(r1, r52);
        r135 = r52;
        r3 = ArrayDecoders.mergeGroupField(r213, r0.getMessageFieldSchema(r52), r28, r25, r30, (r172 << 3) | 4, r32);
        ArrayDecoders.Registers r144 = r32;
        r0.storeMessageField(r1, r135, r213);
        r12 = r12 | r20;
        r4 = r30;
        r2 = r28;
        r8 = r103;
    L29:
        r6 = r135;
        r7 = r144;
    L30:
        r5 = r172;
        r11 = r18;
        r13 = r133;
        goto L3
    L31:
        r135 = r52;
        goto L25
    L32:
        byte[] r83 = r28;
        r144 = r32;
        r135 = r52;
        if (r72 != 0) goto L36;
        int r77 = ArrayDecoders.decodeVarint64(r83, r25, r144);
        T r214 = r1;
        Unsafe r16 = r9;
        r16.putLong(r214, r34, CodedInputStream.decodeZigZag64(r144.long1));
        Unsafe r115 = r16;
        T r96 = r214;
        r12 = r12 | r20;
        r4 = r30;
        r3 = r77;
    L35:
        r2 = r83;
        r1 = r96;
        r8 = r103;
        r9 = r115;
        goto L29
    L36:
        r113 = r25;
        r38 = r9;
        r94 = r144;
        goto L95
    L38:
        r83 = r28;
        r144 = r32;
        r135 = r52;
        r115 = r9;
        int r54 = r25;
        r96 = r1;
        if (r72 != 0) goto L42;
        int r110 = ArrayDecoders.decodeVarint32(r83, r54, r144);
        r115.putInt(r96, r34, CodedInputStream.decodeZigZag32(r144.int1));
    L41:
        r12 = r12 | r20;
        r4 = r30;
        r3 = r110;
    L42:
        r38 = r115;
        r94 = r144;
        r113 = r54;
        goto L95
    L43:
        r83 = r28;
        r144 = r32;
        r135 = r52;
        r115 = r9;
        r54 = r25;
        r96 = r1;
        if (r72 != 0) goto L42;
        r110 = ArrayDecoders.decodeVarint32(r83, r54, r144);
        int r215 = r144.int1;
        r0.getEnumFieldVerifier(r135);
        r115.putInt(r96, r34, r215);
        goto L41
    L46:
        r83 = r28;
        r144 = r32;
        r135 = r52;
        r115 = r9;
        r54 = r25;
        r96 = r1;
        if (r72 != 2) goto L42;
        r110 = ArrayDecoders.decodeBytes(r83, r54, r144);
        r115.putObject(r96, r34, r144.object1);
        goto L41
    L49:
        r135 = r52;
        Unsafe r116 = r9;
        T r97 = r1;
        if (r72 != 2) goto L54;
        Object r111 = r0.mutableMessageFieldForMerge(r97, r135);
        int r216 = ArrayDecoders.mergeMessageField(r111, r0.getMessageFieldSchema(r135), r28, r25, r30, r32);
        byte[] r145 = r28;
        ArrayDecoders.Registers r55 = r32;
        r0.storeMessageField(r97, r135, r111);
        r12 = r12 | r20;
        r4 = r30;
        r3 = r216;
    L52:
        r7 = r55;
        r1 = r97;
        r8 = r103;
        r9 = r116;
    L53:
        r6 = r135;
        r2 = r145;
        goto L30
    L54:
        int r84 = r25;
        r55 = r32;
    L55:
        r38 = r116;
        r94 = r55;
        r113 = r84;
        goto L95
    L56:
        r145 = r28;
        r135 = r52;
        r116 = r9;
        r84 = r25;
        r55 = r32;
        r97 = r1;
        if (r72 != 2) goto L55;
        if ((536870912 & r62) != 0) goto L61;
        int r117 = ArrayDecoders.decodeString(r145, r84, r55);
    L62:
        r116.putObject(r97, r34, r55.object1);
    L63:
        r12 = r12 | r20;
        r4 = r30;
        r3 = r117;
        goto L52
    L61:
        r117 = ArrayDecoders.decodeStringRequireUtf8(r145, r84, r55);
        goto L62
    L64:
        r145 = r28;
        r135 = r52;
        r116 = r9;
        r84 = r25;
        r55 = r32;
        r97 = r1;
        if (r72 != 0) goto L55;
        r117 = ArrayDecoders.decodeVarint64(r145, r84, r55);
        if (r55.long1 != 0) goto L70;
        r210 = false;
    L70:
        UnsafeUtil.putBoolean(r97, r34, r210);
        goto L63
    L71:
        r145 = r28;
        r135 = r52;
        r116 = r9;
        r55 = r32;
        r97 = r1;
        if (r72 != 5) goto L74;
        r116.putInt(r97, r34, ArrayDecoders.decodeFixed32(r145, r25));
        r3 = r25 + 4;
        r12 = r12 | r20;
        r4 = r30;
        goto L52
    L74:
        r38 = r116;
        r113 = r25;
        r94 = r55;
        goto L95
    L85:
        r145 = r28;
        r135 = r52;
        r211 = r9;
        r113 = r25;
        r94 = r32;
        if (r72 != 0) goto L81;
        int r78 = ArrayDecoders.decodeVarint64(r145, r113, r94);
        T r217 = r1;
        r211.putLong(r217, r34, r94.long1);
        r1 = r217;
        r12 = r12 | r20;
        r9 = r211;
        r3 = r78;
        r7 = r94;
        r4 = r30;
        r8 = r103;
        goto L53
    L21:
        r172 = r14;
        r103 = r8;
        goto L23
    L12:
        r22 = r25;
        r17 = r8;
        r24 = r9;
        r19 = r11;
        r182 = r12;
        r92 = r14;
        r122 = 0;
        r142 = r32;
        r82 = r0;
        r02 = r133;
        goto L131
    L10:
        r52 = r0.positionForFieldNumber(r14);
        goto L11
    L143:
        r63 = r1;
        r74 = r4;
        int r173 = r8;
        r24 = r9;
        r82 = r0;
        r93 = r3;
        r03 = r173;
        goto L144
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T r3, Writer r4) {
        if (r4.fieldOrder() != Writer.FieldOrder.DESCENDING) goto L7;
        writeFieldsInDescendingOrder(r3, r4);
        return;
    L7:
        if (this.proto3 == false) goto L10;
        writeFieldsInAscendingOrderProto3(r3, r4);
        return;
    L10:
        writeFieldsInAscendingOrderProto2(r3, r4);
    }
}
