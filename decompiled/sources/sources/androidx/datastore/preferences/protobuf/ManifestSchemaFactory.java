package androidx.datastore.preferences.protobuf;

import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
final class ManifestSchemaFactory implements SchemaFactory {
    private static final MessageInfoFactory EMPTY_FACTORY = null;
    private final MessageInfoFactory messageInfoFactory;

    /* renamed from: androidx.datastore.preferences.protobuf.ManifestSchemaFactory$1, reason: invalid class name */
    public class AnonymousClass1 implements MessageInfoFactory {
        public AnonymousClass1() {
        }

        @Override // androidx.datastore.preferences.protobuf.MessageInfoFactory
        public boolean isSupported(Class<?> r1) {
            return false;
        }

        @Override // androidx.datastore.preferences.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class<?> r1) {
            throw new IllegalStateException("This should never be called.");
        }
    }

    /* renamed from: androidx.datastore.preferences.protobuf.ManifestSchemaFactory$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$ProtoSyntax = null;

        static {
            int[] r0 = new int[ProtoSyntax.values().length];
            $SwitchMap$com$google$protobuf$ProtoSyntax = r0;
            r0[ProtoSyntax.PROTO3.ordinal()] = 1;     // Catch: NoSuchFieldError -> L5
            return;
        }
    }

    public static class CompositeMessageInfoFactory implements MessageInfoFactory {
        private MessageInfoFactory[] factories;

        public CompositeMessageInfoFactory(MessageInfoFactory... r1) {
            this.factories = r1;
        }

        @Override // androidx.datastore.preferences.protobuf.MessageInfoFactory
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

        @Override // androidx.datastore.preferences.protobuf.MessageInfoFactory
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
            ByteString$$ExternalSyntheticBUOutline0.m("No factory is available for message type: ".concat(r5.getName()));
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

    private static boolean allowExtensions(MessageInfo r1) {
        if (AnonymousClass2.$SwitchMap$com$google$protobuf$ProtoSyntax[r1.getSyntax().ordinal()] == 1) goto L5;
        return true;
    L5:
        return false;
    }

    private static MessageInfoFactory getDefaultMessageInfoFactory() {
        return new CompositeMessageInfoFactory(new MessageInfoFactory[]{GeneratedMessageInfoFactory.getInstance(), getDescriptorMessageInfoFactory()});
    }

    private static MessageInfoFactory getDescriptorMessageInfoFactory() {
        if (Protobuf.assumeLiteRuntime == true) goto L5;
        return (MessageInfoFactory) Class.forName("androidx.datastore.preferences.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
    L9:
        return EMPTY_FACTORY;
    L5:
        return EMPTY_FACTORY;
    }

    private static <T> Schema<T> newSchema(Class<T> r9, MessageInfo r10) {
        ExtensionSchema<?> r1 = null;
        if (useLiteRuntime(r9) == false) goto L9;
        NewInstanceSchema r4 = NewInstanceSchemas.lite();
        ListFieldSchema r5 = ListFieldSchemas.lite();
        UnknownFieldSchema<?, ?> r6 = SchemaUtil.unknownFieldSetLiteSchema();
        if (allowExtensions(r10) == false) goto L8;
        r1 = ExtensionSchemas.lite();
    L8:
        return MessageSchema.newSchema(r9, r10, r4, r5, r6, r1, MapFieldSchemas.lite());
    L9:
        NewInstanceSchema r2 = NewInstanceSchemas.full();
        ExtensionSchema<?> r92 = null;
        ListFieldSchema r3 = ListFieldSchemas.full();
        UnknownFieldSchema<?, ?> r42 = SchemaUtil.unknownFieldSetFullSchema();
        if (allowExtensions(r10) == false) goto L13;
        r92 = ExtensionSchemas.full();
    L13:
        return MessageSchema.newSchema(r9, r10, r2, r3, r42, r92, MapFieldSchemas.full());
    }

    private static boolean useLiteRuntime(Class<?> r1) {
        if (Protobuf.assumeLiteRuntime == false) goto L5;
        return true;
    L5:
        if (GeneratedMessageLite.class.isAssignableFrom(r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.datastore.preferences.protobuf.SchemaFactory
    public <T> Schema<T> createSchema(Class<T> r2) {
        SchemaUtil.requireGeneratedMessage(r2);
        MessageInfo r1 = this.messageInfoFactory.messageInfoFor(r2);
        if (r1.isMessageSetWireFormat() == false) goto L11;
        if (useLiteRuntime(r2) == false) goto L9;
        return MessageSetSchema.newSchema(SchemaUtil.unknownFieldSetLiteSchema(), ExtensionSchemas.lite(), r1.getDefaultInstance());
    L9:
        return MessageSetSchema.newSchema(SchemaUtil.unknownFieldSetFullSchema(), ExtensionSchemas.full(), r1.getDefaultInstance());
    L11:
        return newSchema(r2, r1);
    }
}
