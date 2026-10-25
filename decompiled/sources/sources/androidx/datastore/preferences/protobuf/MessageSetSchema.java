package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.datastore.preferences.protobuf.FieldSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
final class MessageSetSchema<T> implements Schema<T> {
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema<?, ?> r1, ExtensionSchema<?> r2, MessageLite r3) {
        this.unknownFieldSchema = r1;
        this.hasExtensions = r2.hasExtensions(r3);
        this.extensionSchema = r2;
        this.defaultInstance = r3;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> r1, T r2) {
        return r1.getSerializedSizeAsMessageSet(r1.getFromMessage(r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> r8, ExtensionSchema<ET> r9, T r10, Reader r11, ExtensionRegistryLite r12) {
        UB r6 = r8.getBuilderFromMessage(r10);
        Object r4 = r9.getMutableExtensions(r10);
    L22:
        if (r11.getFieldNumber() == Integer.MAX_VALUE) goto L6;
        MessageSetSchema r0 = this;
        UnknownFieldSchema<UT, UB> r5 = r8;
        ExtensionSchema<ET> r3 = r9;
        Reader r1 = r11;
        ExtensionRegistryLite r2 = r12;
        if (r0.parseMessageSetItemOrUnknownField(r1, r2, r3, r4, r5, r6) == false) goto L12;
        MessageSetSchema<T> this = r0;
        r11 = r1;
        r12 = r2;
        r9 = r3;
        r8 = r5;
        goto L22
    L12:
        r5.setBuilderToMessage(r10, r6);
        return;
    L14:
        th = th;
    L15:
        Throwable r7 = th;
        r5.setBuilderToMessage(r10, r6);
        throw r7;
    L6:
        r8.setBuilderToMessage(r10, r6);
        return;
    L16:
        th = th;
        r5 = r8;
        goto L15
    }

    public static <T> MessageSetSchema<T> newSchema(UnknownFieldSchema<?, ?> r1, ExtensionSchema<?> r2, MessageLite r3) {
        return new MessageSetSchema(r1, r2, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(Reader r7, ExtensionRegistryLite r8, ExtensionSchema<ET> r9, FieldSet<ET> r10, UnknownFieldSchema<UT, UB> r11, UB r12) {
        int r0 = r7.getTag();
        int r2 = 0;
        if (r0 != WireFormat.MESSAGE_SET_ITEM_TAG) goto L5;
        Object r02 = null;
        ByteString r1 = null;
    L16:
        if (r7.getFieldNumber() == Integer.MAX_VALUE) goto L29;
        int r4 = r7.getTag();
        if (r4 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) goto L20;
        if (r4 == WireFormat.MESSAGE_SET_MESSAGE_TAG) goto L23;
        if (r7.skipField() == true) goto L16;
    L23:
        if (r02 != null) goto L24;
        r1 = r7.readBytes();
        goto L16
    L24:
        r9.parseLengthPrefixedMessageSetItem(r7, r02, r8, r10);
        goto L16
    L20:
        r2 = r7.readUInt32();
        r02 = r9.findExtensionByNumber(r8, this.defaultInstance, r2);
    L29:
        if (r7.getTag() != WireFormat.MESSAGE_SET_ITEM_END_TAG) goto L36;
        if (r1 == null) goto L34;
        if (r02 == null) goto L33;
        r9.parseMessageSetItem(r1, r02, r8, r10);
        goto L34
    L33:
        r11.addLengthDelimited(r12, r2, r1);
    L34:
        return true;
    L36:
        throw InvalidProtocolBufferException.invalidEndTag();
    L5:
        if (WireFormat.getTagWireType(r0) != 2) goto L13;
        Object r6 = r9.findExtensionByNumber(r8, this.defaultInstance, WireFormat.getTagFieldNumber(r0));
        if (r6 == null) goto L11;
        r9.parseLengthPrefixedMessageSetItem(r7, r6, r8, r10);
        return true;
    L11:
        return r11.mergeOneFieldFrom(r12, r7, 0);
    L13:
        return r7.skipField();
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> r1, T r2, Writer r3) {
        r1.writeAsMessageSetTo(r1.getFromMessage(r2), r3);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public boolean equals(T r3, T r4) {
        if (this.unknownFieldSchema.getFromMessage(r3).equals(this.unknownFieldSchema.getFromMessage(r4)) == true) goto L7;
        return false;
    L7:
        if (this.hasExtensions == true) goto L9;
        return true;
    L9:
        return this.extensionSchema.getExtensions(r3).equals(this.extensionSchema.getExtensions(r4));
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int getSerializedSize(T r3) {
        int r0 = getUnknownFieldsSerializedSize(this.unknownFieldSchema, r3);
        if (this.hasExtensions == true) goto L5;
        return r0;
    L5:
        return r0 + this.extensionSchema.getExtensions(r3).getMessageSetSerializedSize();
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int hashCode(T r3) {
        int r0 = this.unknownFieldSchema.getFromMessage(r3).hashCode();
        if (this.hasExtensions == true) goto L5;
        return r0;
    L5:
        return (r0 * 53) + this.extensionSchema.getExtensions(r3).hashCode();
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public final boolean isInitialized(T r1) {
        return this.extensionSchema.getExtensions(r1).isInitialized();
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void makeImmutable(T r2) {
        this.unknownFieldSchema.makeImmutable(r2);
        this.extensionSchema.makeImmutable(r2);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T r7, Reader r8, ExtensionRegistryLite r9) {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, r7, r8, r9);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T r2, T r3) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, r2, r3);
        if (this.hasExtensions == false) goto L6;
        SchemaUtil.mergeExtensions(this.extensionSchema, r2, r3);
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public T newInstance() {
        MessageLite r1 = this.defaultInstance;
        if ((r1 instanceof GeneratedMessageLite) == false) goto L7;
        return (T) ((GeneratedMessageLite) r1).newMutableInstance();
    L7:
        return (T) r1.newBuilderForType().buildPartial();
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void writeTo(T r3, Writer r4) {
        Iterator r0 = this.extensionSchema.getExtensions(r3).iterator();
        if (r0.hasNext() == true) goto L6;
        writeUnknownFieldsHelper(this.unknownFieldSchema, r3, r4);
        return;
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) r0.next()).getKey());
        throw null;
    }
}
