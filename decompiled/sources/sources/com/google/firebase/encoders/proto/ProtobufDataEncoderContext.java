package com.google.firebase.encoders.proto;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.proto.Protobuf;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private static final ObjectEncoder<Map.Entry<Object, Object>> DEFAULT_MAP_ENCODER = null;
    private static final FieldDescriptor MAP_KEY_DESC = null;
    private static final FieldDescriptor MAP_VALUE_DESC = null;
    private static final Charset UTF_8 = null;
    private final ObjectEncoder<Object> fallbackEncoder;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    /* renamed from: com.google.firebase.encoders.proto.ProtobufDataEncoderContext$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding = null;

        static {
            int[] r0 = new int[Protobuf.IntEncoding.values().length];
            $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding = r0;
            r0[Protobuf.IntEncoding.DEFAULT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[Protobuf.IntEncoding.SIGNED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[Protobuf.IntEncoding.FIXED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    /* renamed from: $r8$lambda$HMpWU6Ryr444SahjVbEVRqty-xs, reason: not valid java name */
    public static /* synthetic */ void m1952$r8$lambda$HMpWU6Ryr444SahjVbEVRqtyxs(Map.Entry r2, ObjectEncoderContext r3) {
        r3.add(MAP_KEY_DESC, r2.getKey());
        r3.add(MAP_VALUE_DESC, r2.getValue());
    }

    static {
        UTF_8 = Charset.forName("UTF-8");
        MAP_KEY_DESC = FieldDescriptor.builder(PluginsConstants.Settings.KEY).withProperty(AtProtobuf.builder().tag(1).build()).build();
        MAP_VALUE_DESC = FieldDescriptor.builder("value").withProperty(AtProtobuf.builder().tag(2).build()).build();
        DEFAULT_MAP_ENCODER = new ProtobufDataEncoderContext$$ExternalSyntheticLambda0();
    }

    public ProtobufDataEncoderContext(OutputStream r2, Map<Class<?>, ObjectEncoder<?>> r3, Map<Class<?>, ValueEncoder<?>> r4, ObjectEncoder<Object> r5) {
        this.valueEncoderContext = new ProtobufValueEncoderContext(this);
        this.output = r2;
        this.objectEncoders = r3;
        this.valueEncoders = r4;
        this.fallbackEncoder = r5;
    }

    private static ByteBuffer allocateBuffer(int r1) {
        return ByteBuffer.allocate(r1).order(ByteOrder.LITTLE_ENDIAN);
    }

    private <T> long determineSize(ObjectEncoder<T> r3, T r4) {
        LengthCountingOutputStream r0 = new LengthCountingOutputStream();
        OutputStream r1 = this.output;     // Catch: Throwable -> L8
        this.output = r0;     // Catch: Throwable -> L8
        r3.encode(r4, this);     // Catch: Throwable -> L10
        this.output = r1;     // Catch: Throwable -> L8
        long r2 = r0.getLength();     // Catch: Throwable -> L8
        r0.close();
        return r2;
    L10:
        th = move-exception;
        this.output = r1;     // Catch: Throwable -> L8
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L15
    L17:
        throw th;
    L15:
        th = move-exception;
        th.addSuppressed(th);
        goto L17
    }

    private <T> ProtobufDataEncoderContext doEncode(ObjectEncoder<T> r5, FieldDescriptor r6, T r7, boolean r8) {
        long r0 = determineSize(r5, r7);
        if (r8 == true) goto L5;
    L7:
        writeVarInt32((getTag(r6) << 3) | 2);
        writeVarInt64(r0);
        r5.encode(r7, this);
        return this;
    L5:
        if (r0 != 0) goto L7;
        return this;
    }

    private <T> ProtobufDataEncoderContext doEncode(ValueEncoder<T> r2, FieldDescriptor r3, T r4, boolean r5) {
        this.valueEncoderContext.resetContext(r3, r5);
        r2.encode(r4, this.valueEncoderContext);
        return this;
    }

    private static Protobuf getProtobuf(FieldDescriptor r1) {
        Protobuf r12 = (Protobuf) r1.getProperty(Protobuf.class);
        if (r12 == null) goto L6;
        return r12;
    L6:
        throw new EncodingException("Field has no @Protobuf config");
    }

    private static int getTag(FieldDescriptor r1) {
        Protobuf r12 = (Protobuf) r1.getProperty(Protobuf.class);
        if (r12 == null) goto L7;
        return r12.tag();
    L7:
        throw new EncodingException("Field has no @Protobuf config");
    }

    private void writeVarInt32(int r5) {
    L2:
        long r0 = r5 & (-128);
        OutputStream r1 = this.output;
        if (r0 == 0) goto L5;
        r1.write((r5 & 127) | 128);
        r5 = r5 >>> 7;
        goto L2
    L5:
        r1.write(r5 & 127);
    }

    private void writeVarInt64(long r5) {
    L2:
        long r0 = (-128) & r5;
        OutputStream r1 = this.output;
        if (r0 == 0) goto L5;
        r1.write((((int) r5) & 127) | 128);
        r5 = r5 >>> 7;
        goto L2
    L5:
        r1.write(((int) r5) & 127);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor r2, double r3) {
        return add(r2, r3, true);
    }

    public ObjectEncoderContext add(FieldDescriptor r3, double r4, boolean r6) {
        if (r6 == true) goto L4;
    L6:
        writeVarInt32((getTag(r3) << 3) | 1);
        this.output.write(allocateBuffer(8).putDouble(r4).array());
        return this;
    L4:
        if (r4 != 0.0d) goto L6;
        return this;
    }

    public ObjectEncoderContext add(FieldDescriptor r1, float r2, boolean r3) {
        if (r3 == true) goto L4;
    L6:
        writeVarInt32((getTag(r1) << 3) | 5);
        this.output.write(allocateBuffer(4).putFloat(r2).array());
        return this;
    L4:
        if (r2 != 0.0f) goto L6;
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public /* bridge */ /* synthetic */ ObjectEncoderContext add(FieldDescriptor r1, int r2) {
        return add(r1, r2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public /* bridge */ /* synthetic */ ObjectEncoderContext add(FieldDescriptor r1, long r2) {
        return add(r1, r2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor r2, Object r3) {
        return add(r2, r3, true);
    }

    public ObjectEncoderContext add(FieldDescriptor r3, Object r4, boolean r5) {
        if (r4 != null) goto L5;
    L47:
        return this;
    L5:
        if ((r4 instanceof CharSequence) == false) goto L14;
        CharSequence r42 = (CharSequence) r4;
        if (r5 == true) goto L9;
    L11:
        writeVarInt32((getTag(r3) << 3) | 2);
        byte[] r32 = r42.toString().getBytes(UTF_8);
        writeVarInt32(r32.length);
        this.output.write(r32);
        return this;
    L9:
        if (r42.length() != 0) goto L11;
    L14:
        if ((r4 instanceof Collection) == false) goto L20;
        Iterator r43 = ((Collection) r4).iterator();
    L17:
        if (r43.hasNext() == false) goto L47;
        add(r3, r43.next(), false);
        goto L17
    L20:
        if ((r4 instanceof Map) == false) goto L26;
        Iterator r44 = ((Map) r4).entrySet().iterator();
    L23:
        if (r44.hasNext() == false) goto L47;
        Map.Entry r52 = (Map.Entry) r44.next();
        doEncode(DEFAULT_MAP_ENCODER, r3, r52, false);
        goto L23
    L26:
        if ((r4 instanceof Double) == false) goto L30;
        return add(r3, ((Double) r4).doubleValue(), r5);
    L30:
        if ((r4 instanceof Float) == false) goto L34;
        return add(r3, ((Float) r4).floatValue(), r5);
    L34:
        if ((r4 instanceof Number) == false) goto L38;
        return add(r3, ((Number) r4).longValue(), r5);
    L38:
        if ((r4 instanceof Boolean) == false) goto L42;
        return add(r3, ((Boolean) r4).booleanValue(), r5);
    L42:
        if ((r4 instanceof byte[]) == false) goto L50;
        byte[] r45 = (byte[]) r4;
        if (r5 == true) goto L46;
    L48:
        writeVarInt32((getTag(r3) << 3) | 2);
        writeVarInt32(r45.length);
        this.output.write(r45);
        return this;
    L46:
        if (r45.length != 0) goto L48;
    L50:
        ObjectEncoder<?> r0 = this.objectEncoders.get(r4.getClass());
        if (r0 != null) goto L53;
        ValueEncoder<?> r02 = this.valueEncoders.get(r4.getClass());
        if (r02 == null) goto L59;
        return doEncode(r02, r3, r4, r5);
    L59:
        if ((r4 instanceof ProtoEnum) == false) goto L63;
        return add(r3, ((ProtoEnum) r4).getNumber());
    L63:
        if ((r4 instanceof Enum) == false) goto L67;
        return add(r3, ((Enum) r4).ordinal());
    L67:
        return doEncode(this.fallbackEncoder, r3, r4, r5);
    L53:
        return doEncode(r0, r3, r4, r5);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public /* bridge */ /* synthetic */ ObjectEncoderContext add(FieldDescriptor r1, boolean r2) {
        return add(r1, r2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor r2, int r3) {
        return add(r2, r3, true);
    }

    public ProtobufDataEncoderContext add(FieldDescriptor r3, int r4, boolean r5) {
        if (r5 == false) goto L5;
        if (r4 != 0) goto L5;
    L10:
        return this;
    L5:
        Protobuf r32 = getProtobuf(r3);
        int r52 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[r32.intEncoding().ordinal()];
        if (r52 != 1) goto L8;
        writeVarInt32(r32.tag() << 3);
        writeVarInt32(r4);
        return this;
    L8:
        if (r52 == 2) goto L13;
        if (r52 != 3) goto L10;
        writeVarInt32((r32.tag() << 3) | 5);
        this.output.write(allocateBuffer(4).putInt(r4).array());
        return this;
    L13:
        writeVarInt32(r32.tag() << 3);
        writeVarInt32((r4 << 1) ^ (r4 >> 31));
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor r2, long r3) {
        return add(r2, r3, true);
    }

    public ProtobufDataEncoderContext add(FieldDescriptor r4, long r5, boolean r7) {
        if (r7 == true) goto L4;
    L6:
        Protobuf r42 = getProtobuf(r4);
        int r72 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[r42.intEncoding().ordinal()];
        if (r72 != 1) goto L9;
        writeVarInt32(r42.tag() << 3);
        writeVarInt64(r5);
        return this;
    L9:
        if (r72 == 2) goto L14;
        if (r72 == 3) goto L12;
    L11:
        return this;
    L12:
        writeVarInt32((r42.tag() << 3) | 1);
        this.output.write(allocateBuffer(8).putLong(r5).array());
        return this;
    L14:
        writeVarInt32(r42.tag() << 3);
        long r43 = r5 >> 63;
        writeVarInt64(r43 ^ (r5 << 1));
        return this;
    L4:
        if (r5 != 0) goto L6;
        goto L6
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor r2, boolean r3) {
        return add(r2, r3, true);
    }

    public ProtobufDataEncoderContext add(FieldDescriptor r1, boolean r2, boolean r3) {
        return add(r1, r2 ? 1 : 0, r3);
    }

    public ProtobufDataEncoderContext encode(Object r3) {
        if (r3 != null) goto L4;
        return this;
    L4:
        ObjectEncoder<?> r0 = this.objectEncoders.get(r3.getClass());
        if (r0 == null) goto L9;
        r0.encode(r3, this);
        return this;
    L9:
        throw new EncodingException("No encoder for " + r3.getClass());
    }
}
