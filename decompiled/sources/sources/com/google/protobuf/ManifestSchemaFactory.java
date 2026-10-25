package com.google.protobuf;

/* loaded from: classes5.dex */
final class ManifestSchemaFactory implements SchemaFactory {
    private static final MessageInfoFactory EMPTY_FACTORY = null;
    private final MessageInfoFactory messageInfoFactory;

    /* renamed from: com.google.protobuf.ManifestSchemaFactory$1, reason: invalid class name */
    public class AnonymousClass1 implements MessageInfoFactory {
        public AnonymousClass1() {
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public boolean isSupported(Class<?> r1) {
            return false;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class<?> r1) {
            throw new IllegalStateException("This should never be called.");
        }
    }

    public static class CompositeMessageInfoFactory implements MessageInfoFactory {
        private MessageInfoFactory[] factories;

        public CompositeMessageInfoFactory(MessageInfoFactory... r1) {
            this.factories = r1;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public boolean isSupported(Class<?> r5) {
            MessageInfoFactory[] r4 = this.factories;
            int r0 = r4.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L9;
            if (r4[r2].isSupported(r5) == true) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return true;
        L9:
            return false;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class<?> r5) {
            MessageInfoFactory[] r4 = this.factories;
            int r0 = r4.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L9;
            MessageInfoFactory r2 = r4[r1];
            if (r2.isSupported(r5) == true) goto L7;
            r1 = r1 + 1;
            goto L3
        L7:
            return r2.messageInfoFor(r5);
        L9:
            okio.ByteString$$ExternalSyntheticBUOutline0.m("No factory is available for message type: ".concat(r5.getName()));
            return null;
        }
    }

    static {
        EMPTY_FACTORY = new AnonymousClass1();
    }

    public ManifestSchemaFactory() {
        this(getDefaultMessageInfoFactory());
    }

    private ManifestSchemaFactory(MessageInfoFactory r2) {
        this.messageInfoFactory = (MessageInfoFactory) Internal.checkNotNull(r2, "messageInfoFactory");
    }

    private static MessageInfoFactory getDefaultMessageInfoFactory() {
        return new CompositeMessageInfoFactory(new MessageInfoFactory[]{GeneratedMessageInfoFactory.getInstance(), getDescriptorMessageInfoFactory()});
    }

    private static MessageInfoFactory getDescriptorMessageInfoFactory() {
        return (MessageInfoFactory) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
    L5:
        return EMPTY_FACTORY;
    }

    private static boolean isProto2(MessageInfo r1) {
        if (r1.getSyntax() != ProtoSyntax.PROTO2) goto L6;
        return true;
    L6:
        return false;
    }

    private static <T> Schema<T> newSchema(Class<T> r8, MessageInfo r9) {
        if (GeneratedMessageLite.class.isAssignableFrom(r8) == false) goto L11;
        if (isProto2(r9) == false) goto L9;
        return MessageSchema.newSchema(r8, r9, NewInstanceSchemas.lite(), ListFieldSchema.lite(), SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.lite(), MapFieldSchemas.lite());
    L9:
        return MessageSchema.newSchema(r8, r9, NewInstanceSchemas.lite(), ListFieldSchema.lite(), SchemaUtil.unknownFieldSetLiteSchema(), null, MapFieldSchemas.lite());
    L11:
        if (isProto2(r9) == false) goto L15;
        return MessageSchema.newSchema(r8, r9, NewInstanceSchemas.full(), ListFieldSchema.full(), SchemaUtil.proto2UnknownFieldSetSchema(), ExtensionSchemas.full(), MapFieldSchemas.full());
    L15:
        return MessageSchema.newSchema(r8, r9, NewInstanceSchemas.full(), ListFieldSchema.full(), SchemaUtil.proto3UnknownFieldSetSchema(), null, MapFieldSchemas.full());
    }

    @Override // com.google.protobuf.SchemaFactory
    public <T> Schema<T> createSchema(Class<T> r2) {
        SchemaUtil.requireGeneratedMessage(r2);
        MessageInfo r1 = this.messageInfoFactory.messageInfoFor(r2);
        if (r1.isMessageSetWireFormat() == false) goto L11;
        if (GeneratedMessageLite.class.isAssignableFrom(r2) == false) goto L9;
        return MessageSetSchema.newSchema(SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.lite(), r1.getDefaultInstance());
    L9:
        return MessageSetSchema.newSchema(SchemaUtil.proto2UnknownFieldSetSchema(), ExtensionSchemas.full(), r1.getDefaultInstance());
    L11:
        return newSchema(r2, r1);
    }
}
