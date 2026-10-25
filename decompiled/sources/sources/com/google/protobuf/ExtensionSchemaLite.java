package com.google.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class ExtensionSchemaLite extends ExtensionSchema<GeneratedMessageLite.ExtensionDescriptor> {

    /* renamed from: com.google.protobuf.ExtensionSchemaLite$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = null;

        static {
            int[] r0 = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0;
            r0[WireFormat.FieldType.DOUBLE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L22
        L62:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L23
        L46:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;     // Catch: NoSuchFieldError -> L24
        L58:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;     // Catch: NoSuchFieldError -> L25
        L64:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;     // Catch: NoSuchFieldError -> L26
        L48:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;     // Catch: NoSuchFieldError -> L27
        L52:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 7;     // Catch: NoSuchFieldError -> L28
        L74:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 8;     // Catch: NoSuchFieldError -> L29
        L42:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 9;     // Catch: NoSuchFieldError -> L30
        L68:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 10;     // Catch: NoSuchFieldError -> L31
        L70:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 11;     // Catch: NoSuchFieldError -> L32
        L54:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 12;     // Catch: NoSuchFieldError -> L33
        L60:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 13;     // Catch: NoSuchFieldError -> L34
        L44:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 14;     // Catch: NoSuchFieldError -> L35
        L50:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 15;     // Catch: NoSuchFieldError -> L36
        L72:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 16;     // Catch: NoSuchFieldError -> L37
        L40:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 17;     // Catch: NoSuchFieldError -> L38
        L66:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 18;     // Catch: NoSuchFieldError -> L39
            return;
        }
    }

    public ExtensionSchemaLite() {
    }

    @Override // com.google.protobuf.ExtensionSchema
    public int extensionNumber(Map.Entry<?, ?> r1) {
        return ((GeneratedMessageLite.ExtensionDescriptor) r1.getKey()).getNumber();
    }

    @Override // com.google.protobuf.ExtensionSchema
    public Object findExtensionByNumber(ExtensionRegistryLite r1, MessageLite r2, int r3) {
        return r1.findLiteExtensionByNumber(r2, r3);
    }

    @Override // com.google.protobuf.ExtensionSchema
    public FieldSet<GeneratedMessageLite.ExtensionDescriptor> getExtensions(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public FieldSet<GeneratedMessageLite.ExtensionDescriptor> getMutableExtensions(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public boolean hasExtensions(MessageLite r1) {
        return false;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void makeImmutable(Object r1) {
        getExtensions(r1).makeImmutable();
    }

    @Override // com.google.protobuf.ExtensionSchema
    public <UT, UB> UB parseExtension(Object r7, Reader r8, Object r9, ExtensionRegistryLite r10, FieldSet<GeneratedMessageLite.ExtensionDescriptor> r11, UB r12, UnknownFieldSchema<UT, UB> r13) {
        GeneratedMessageLite.GeneratedExtension r92 = (GeneratedMessageLite.GeneratedExtension) r9;
        int r1 = r92.getNumber();
        Object r0 = null;
        if (r92.descriptor.isRepeated() == false) goto L27;
        if (r92.descriptor.isPacked() == false) goto L27;
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r92.getLiteType().ordinal()]) {
            case 1: goto L23;
            case 2: goto L22;
            case 3: goto L21;
            case 4: goto L20;
            case 5: goto L19;
            case 6: goto L18;
            case 7: goto L17;
            case 8: goto L16;
            case 9: goto L15;
            case 10: goto L14;
            case 11: goto L13;
            case 12: goto L12;
            case 13: goto L11;
            case 14: goto L10;
            default: goto L8;
        };
    L8:
        CstInsn$$ExternalSyntheticBUOutline0.m("Type cannot be packed: ", r92.descriptor.getLiteType());
        return null;
    L10:
        ArrayList r2 = new ArrayList();
        r8.readEnumList(r2);
        r92.descriptor.getEnumType();
        r12 = (UB) SchemaUtil.filterUnknownEnumList(r7, r1, r2, null, r12, r13);
    L24:
        r11.setField(r92.descriptor, r2);
        return r12;
    L11:
        r2 = new ArrayList();
        r8.readSInt64List(r2);
        goto L24
    L12:
        r2 = new ArrayList();
        r8.readSInt32List(r2);
        goto L24
    L13:
        r2 = new ArrayList();
        r8.readSFixed64List(r2);
        goto L24
    L14:
        r2 = new ArrayList();
        r8.readSFixed32List(r2);
        goto L24
    L15:
        r2 = new ArrayList();
        r8.readUInt32List(r2);
        goto L24
    L16:
        r2 = new ArrayList();
        r8.readBoolList(r2);
        goto L24
    L17:
        r2 = new ArrayList();
        r8.readFixed32List(r2);
        goto L24
    L18:
        r2 = new ArrayList();
        r8.readFixed64List(r2);
        goto L24
    L19:
        r2 = new ArrayList();
        r8.readInt32List(r2);
        goto L24
    L20:
        r2 = new ArrayList();
        r8.readUInt64List(r2);
        goto L24
    L21:
        r2 = new ArrayList();
        r8.readInt64List(r2);
        goto L24
    L22:
        r2 = new ArrayList();
        r8.readFloatList(r2);
        goto L24
    L23:
        r2 = new ArrayList();
        r8.readDoubleList(r2);
    L27:
        if (r92.getLiteType() == WireFormat.FieldType.ENUM) goto L82;
        int[] r6 = AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType;
        switch(r6[r92.getLiteType().ordinal()]) {
            case 1: goto L67;
            case 2: goto L66;
            case 3: goto L65;
            case 4: goto L64;
            case 5: goto L63;
            case 6: goto L62;
            case 7: goto L61;
            case 8: goto L60;
            case 9: goto L59;
            case 10: goto L58;
            case 11: goto L57;
            case 12: goto L56;
            case 13: goto L55;
            case 14: goto L53;
            case 15: goto L52;
            case 16: goto L51;
            case 17: goto L42;
            case 18: goto L32;
            default: goto L69;
        };
    L51:
        r0 = r8.readString();
        goto L69
    L52:
        r0 = r8.readBytes();
        goto L69
    L53:
        Segment$$ExternalSyntheticBUOutline1.m("Shouldn't reach here.");
        return null;
    L55:
        r0 = Long.valueOf(r8.readSInt64());
        goto L69
    L56:
        r0 = Integer.valueOf(r8.readSInt32());
        goto L69
    L57:
        r0 = Long.valueOf(r8.readSFixed64());
        goto L69
    L58:
        r0 = Integer.valueOf(r8.readSFixed32());
        goto L69
    L59:
        r0 = Integer.valueOf(r8.readUInt32());
        goto L69
    L60:
        r0 = Boolean.valueOf(r8.readBool());
        goto L69
    L61:
        r0 = Integer.valueOf(r8.readFixed32());
        goto L69
    L62:
        r0 = Long.valueOf(r8.readFixed64());
        goto L69
    L63:
        r0 = Integer.valueOf(r8.readInt32());
        goto L69
    L64:
        r0 = Long.valueOf(r8.readUInt64());
        goto L69
    L65:
        r0 = Long.valueOf(r8.readInt64());
        goto L69
    L66:
        r0 = Float.valueOf(r8.readFloat());
        goto L69
    L67:
        r0 = Double.valueOf(r8.readDouble());
        goto L69
    L32:
        if (r92.isRepeated() == true) goto L40;
        Object r72 = r11.getField(r92.descriptor);
        if ((r72 instanceof GeneratedMessageLite) == false) goto L40;
        Schema r62 = Protobuf.getInstance().schemaFor(r72);
        if (((GeneratedMessageLite) r72).isMutable() == true) goto L38;
        Object r122 = r62.newInstance();
        r62.mergeFrom(r122, r72);
        r11.setField(r92.descriptor, r122);
        r72 = r122;
    L38:
        r8.mergeMessageField(r72, r62, r10);
        return r12;
    L40:
        r0 = r8.readMessage(r92.getMessageDefaultInstance().getClass(), r10);
        goto L69
    L42:
        if (r92.isRepeated() == true) goto L50;
        Object r73 = r11.getField(r92.descriptor);
        if ((r73 instanceof GeneratedMessageLite) == false) goto L50;
        Schema r63 = Protobuf.getInstance().schemaFor(r73);
        if (((GeneratedMessageLite) r73).isMutable() == true) goto L48;
        Object r123 = r63.newInstance();
        r63.mergeFrom(r123, r73);
        r11.setField(r92.descriptor, r123);
        r73 = r123;
    L48:
        r8.mergeGroupField(r73, r63, r10);
        return r12;
    L50:
        r0 = r8.readGroup(r92.getMessageDefaultInstance().getClass(), r10);
    L69:
        if (r92.isRepeated() == false) goto L72;
        r11.addRepeatedField(r92.descriptor, r0);
        return r12;
    L72:
        int r64 = r6[r92.getLiteType().ordinal()];
        if (r64 != 17) goto L75;
    L77:
        Object r65 = r11.getField(r92.descriptor);
        if (r65 == null) goto L80;
        r0 = Internal.mergeMessage(r65, r0);
    L80:
        r11.setField(r92.descriptor, r0);
        return r12;
    L75:
        if (r64 == 18) goto L77;
    L82:
        r8.readInt32();
        r92.descriptor.getEnumType();
        throw null;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void parseLengthPrefixedMessageSetItem(Reader r1, Object r2, ExtensionRegistryLite r3, FieldSet<GeneratedMessageLite.ExtensionDescriptor> r4) {
        GeneratedMessageLite.GeneratedExtension r22 = (GeneratedMessageLite.GeneratedExtension) r2;
        Object r0 = r1.readMessage(r22.getMessageDefaultInstance().getClass(), r3);
        r4.setField(r22.descriptor, r0);
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void parseMessageSetItem(ByteString r1, Object r2, ExtensionRegistryLite r3, FieldSet<GeneratedMessageLite.ExtensionDescriptor> r4) {
        GeneratedMessageLite.GeneratedExtension r22 = (GeneratedMessageLite.GeneratedExtension) r2;
        MessageLite.Builder r0 = r22.getMessageDefaultInstance().newBuilderForType();
        CodedInputStream r12 = r1.newCodedInput();
        r0.mergeFrom(r12, r3);
        r4.setField(r22.descriptor, r0.buildPartial());
        r12.checkLastTagWas(0);
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void serializeExtension(Writer r4, Map.Entry<?, ?> r5) {
        GeneratedMessageLite.ExtensionDescriptor r3 = (GeneratedMessageLite.ExtensionDescriptor) r5.getKey();
        if (r3.isRepeated() == false) goto L52;
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r3.getLiteType().ordinal()]) {
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
            case 11: goto L29;
            case 12: goto L27;
            case 13: goto L25;
            case 14: goto L23;
            case 15: goto L21;
            case 16: goto L19;
            case 17: goto L13;
            case 18: goto L7;
            default: goto L94;
        };
    L7:
        List r0 = (List) r5.getValue();
        if (r0 != null) goto L10;
        return;
    L10:
        if (r0.isEmpty() == true) goto L90;
        SchemaUtil.writeMessageList(r3.getNumber(), (List) r5.getValue(), r4, Protobuf.getInstance().schemaFor(r0.get(0).getClass()));
        return;
    L90:
        return;
    L13:
        List r02 = (List) r5.getValue();
        if (r02 != null) goto L16;
        return;
    L16:
        if (r02.isEmpty() == true) goto L92;
        SchemaUtil.writeGroupList(r3.getNumber(), (List) r5.getValue(), r4, Protobuf.getInstance().schemaFor(r02.get(0).getClass()));
        return;
    L92:
        return;
    L19:
        SchemaUtil.writeStringList(r3.getNumber(), (List) r5.getValue(), r4);
        return;
    L21:
        SchemaUtil.writeBytesList(r3.getNumber(), (List) r5.getValue(), r4);
        return;
    L23:
        SchemaUtil.writeInt32List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L25:
        SchemaUtil.writeSInt64List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L27:
        SchemaUtil.writeSInt32List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L29:
        SchemaUtil.writeSFixed64List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L31:
        SchemaUtil.writeSFixed32List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L33:
        SchemaUtil.writeUInt32List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L35:
        SchemaUtil.writeBoolList(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L37:
        SchemaUtil.writeFixed32List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L39:
        SchemaUtil.writeFixed64List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L41:
        SchemaUtil.writeInt32List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L43:
        SchemaUtil.writeUInt64List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L45:
        SchemaUtil.writeInt64List(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L47:
        SchemaUtil.writeFloatList(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L49:
        SchemaUtil.writeDoubleList(r3.getNumber(), (List) r5.getValue(), r4, r3.isPacked());
        return;
    L94:
        return;
    L52:
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r3.getLiteType().ordinal()]) {
            case 1: goto L88;
            case 2: goto L86;
            case 3: goto L84;
            case 4: goto L82;
            case 5: goto L80;
            case 6: goto L78;
            case 7: goto L76;
            case 8: goto L74;
            case 9: goto L72;
            case 10: goto L70;
            case 11: goto L68;
            case 12: goto L66;
            case 13: goto L64;
            case 14: goto L62;
            case 15: goto L60;
            case 16: goto L58;
            case 17: goto L56;
            case 18: goto L54;
            default: goto L93;
        };
    L54:
        r4.writeMessage(r3.getNumber(), r5.getValue(), Protobuf.getInstance().schemaFor(r5.getValue().getClass()));
        return;
    L56:
        r4.writeGroup(r3.getNumber(), r5.getValue(), Protobuf.getInstance().schemaFor(r5.getValue().getClass()));
        return;
    L58:
        r4.writeString(r3.getNumber(), (String) r5.getValue());
        return;
    L60:
        r4.writeBytes(r3.getNumber(), (ByteString) r5.getValue());
        return;
    L62:
        r4.writeInt32(r3.getNumber(), ((Integer) r5.getValue()).intValue());
        return;
    L64:
        r4.writeSInt64(r3.getNumber(), ((Long) r5.getValue()).longValue());
        return;
    L66:
        r4.writeSInt32(r3.getNumber(), ((Integer) r5.getValue()).intValue());
        return;
    L68:
        r4.writeSFixed64(r3.getNumber(), ((Long) r5.getValue()).longValue());
        return;
    L70:
        r4.writeSFixed32(r3.getNumber(), ((Integer) r5.getValue()).intValue());
        return;
    L72:
        r4.writeUInt32(r3.getNumber(), ((Integer) r5.getValue()).intValue());
        return;
    L74:
        r4.writeBool(r3.getNumber(), ((Boolean) r5.getValue()).booleanValue());
        return;
    L76:
        r4.writeFixed32(r3.getNumber(), ((Integer) r5.getValue()).intValue());
        return;
    L78:
        r4.writeFixed64(r3.getNumber(), ((Long) r5.getValue()).longValue());
        return;
    L80:
        r4.writeInt32(r3.getNumber(), ((Integer) r5.getValue()).intValue());
        return;
    L82:
        r4.writeUInt64(r3.getNumber(), ((Long) r5.getValue()).longValue());
        return;
    L84:
        r4.writeInt64(r3.getNumber(), ((Long) r5.getValue()).longValue());
        return;
    L86:
        r4.writeFloat(r3.getNumber(), ((Float) r5.getValue()).floatValue());
        return;
    L88:
        r4.writeDouble(r3.getNumber(), ((Double) r5.getValue()).doubleValue());
        return;
    }
}
