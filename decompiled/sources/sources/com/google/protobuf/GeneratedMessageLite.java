package com.google.protobuf;

import androidx.room.util.KClassUtil$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite<MessageType, BuilderType> {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Object, GeneratedMessageLite<?, ?>> defaultInstanceMap;
    private int memoizedSerializedSize;
    protected UnknownFieldSetLite unknownFields;

    public static abstract class Builder<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite.Builder<MessageType, BuilderType> {
        private final MessageType defaultInstance;
        protected MessageType instance;

        public Builder(MessageType r1) {
            this.defaultInstance = r1;
            if (r1.isMutable() == true) goto L6;
            this.instance = (MessageType) newMutableInstance();
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Default instance must be immutable.");
            throw null;
        }

        private static <MessageType> void mergeFromInstance(MessageType r1, MessageType r2) {
            Protobuf.getInstance().schemaFor(r1).mergeFrom(r1, r2);
        }

        private MessageType newMutableInstance() {
            return (MessageType) this.defaultInstance.newMutableInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public final MessageType build() {
            MessageType r1 = (MessageType) buildPartial();
            if (r1.isInitialized() == false) goto L6;
            return r1;
        L6:
            throw AbstractMessageLite.Builder.newUninitializedMessageException(r1);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite build() {
            return build();
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public MessageType buildPartial() {
            boolean r0 = this.instance.isMutable();
            MessageType r1 = this.instance;
            if (r0 == true) goto L5;
            return r1;
        L5:
            r1.makeImmutable();
            return this.instance;
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite buildPartial() {
            return buildPartial();
        }

        public final BuilderType clear() {
            if (this.defaultInstance.isMutable() == true) goto L6;
            this.instance = (MessageType) newMutableInstance();
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Default instance must be immutable.");
            return null;
        }

        /* renamed from: clear, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1999clear() {
            return clear();
        }

        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder clone() {
            return m2000clone();
        }

        /* renamed from: clone, reason: collision with other method in class */
        public BuilderType m2000clone() {
            BuilderType r0 = (BuilderType) getDefaultInstanceForType().newBuilderForType();
            r0.instance = (MessageType) buildPartial();
            return r0;
        }

        /* renamed from: clone, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m2001clone() {
            return m2000clone();
        }

        /* renamed from: clone, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object m2002clone() {
            return m2000clone();
        }

        public final void copyOnWrite() {
            if (this.instance.isMutable() == true) goto L6;
            copyOnWriteInternal();
            return;
        }

        public void copyOnWriteInternal() {
            MessageType r0 = (MessageType) newMutableInstance();
            mergeFromInstance(r0, this.instance);
            this.instance = r0;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public MessageType getDefaultInstanceForType() {
            return this.defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite r1) {
            return internalMergeFrom((GeneratedMessageLite) r1);
        }

        public BuilderType internalMergeFrom(MessageType r1) {
            return (BuilderType) mergeFrom(r1);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return GeneratedMessageLite.isInitialized(this.instance, false);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream r1, ExtensionRegistryLite r2) {
            return mergeFrom(r1, r2);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] r1, int r2, int r3) {
            return mergeFrom(r1, r2, r3);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] r1, int r2, int r3, ExtensionRegistryLite r4) {
            return mergeFrom(r1, r2, r3, r4);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(CodedInputStream r3, ExtensionRegistryLite r4) {
            copyOnWrite();
            Protobuf.getInstance().schemaFor(this.instance).mergeFrom(this.instance, CodedInputStreamReader.forCodedInput(r3), r4);     // Catch: RuntimeException -> L5
            return this;
        L5:
            e = move-exception;
            if ((e.getCause() instanceof IOException) == true) goto L9;
            throw e;
        L9:
            throw ((IOException) e.getCause());
        }

        public BuilderType mergeFrom(MessageType r2) {
            if (getDefaultInstanceForType().equals(r2) == false) goto L5;
            return this;
        L5:
            copyOnWrite();
            mergeFromInstance(this.instance, r2);
            return this;
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        public BuilderType mergeFrom(byte[] r2, int r3, int r4) {
            return (BuilderType) mergeFrom(r2, r3, r4, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        public BuilderType mergeFrom(byte[] r9, int r10, int r11, ExtensionRegistryLite r12) {
            copyOnWrite();
            Protobuf.getInstance().schemaFor(this.instance).mergeFrom(this.instance, r9, r10, r10 + r11, new ArrayDecoders.Registers(r12));     // Catch: IOException -> L5 IndexOutOfBoundsException -> L8 InvalidProtocolBufferException -> L10
            return this;
        L10:
            e = move-exception;
            throw e;
        L5:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Reading from byte array should not throw IOException.", e);
            return null;
        L9:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream r1, ExtensionRegistryLite r2) {
            return mergeFrom(r1, r2);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m2003mergeFrom(byte[] r1, int r2, int r3) {
            return mergeFrom(r1, r2, r3);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m2004mergeFrom(byte[] r1, int r2, int r3, ExtensionRegistryLite r4) {
            return mergeFrom(r1, r2, r3, r4);
        }
    }

    public static class DefaultInstanceBasedParser<T extends GeneratedMessageLite<T, ?>> extends AbstractParser<T> {
        private final T defaultInstance;

        public DefaultInstanceBasedParser(T r1) {
            this.defaultInstance = r1;
        }
    }

    public static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
        final boolean isPacked;
        final boolean isRepeated;
        final int number;
        final WireFormat.FieldType type;

        public ExtensionDescriptor(Internal.EnumLiteMap<?> r1, int r2, WireFormat.FieldType r3, boolean r4, boolean r5) {
            this.number = r2;
            this.type = r3;
            this.isRepeated = r4;
            this.isPacked = r5;
        }

        public int compareTo(ExtensionDescriptor r1) {
            return this.number - r1.number;
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object r1) {
            return compareTo((ExtensionDescriptor) r1);
        }

        public Internal.EnumLiteMap<?> getEnumType() {
            return null;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return this.type;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.number;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder r1, MessageLite r2) {
            return ((Builder) r1).mergeFrom((GeneratedMessageLite) r2);
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            return this.isPacked;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.isRepeated;
        }
    }

    public static class GeneratedExtension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {
        final ContainingType containingTypeDefaultInstance;
        final Type defaultValue;
        final ExtensionDescriptor descriptor;
        final MessageLite messageDefaultInstance;

        public GeneratedExtension(ContainingType r3, Type r4, MessageLite r5, ExtensionDescriptor r6, Class r7) {
            if (r3 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Null containingTypeDefaultInstance");
            throw null;
        L5:
            if (r6.getLiteType() != WireFormat.FieldType.MESSAGE) goto L10;
            if (r5 != null) goto L10;
            f$$ExternalSyntheticBUOutline1.m("Null messageDefaultInstance");
            throw null;
        L10:
            this.containingTypeDefaultInstance = r3;
            this.defaultValue = r4;
            this.messageDefaultInstance = r5;
            this.descriptor = r6;
        }

        public WireFormat.FieldType getLiteType() {
            return this.descriptor.getLiteType();
        }

        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        public int getNumber() {
            return this.descriptor.getNumber();
        }

        public boolean isRepeated() {
            return this.descriptor.isRepeated;
        }
    }

    public enum MethodToInvoke extends Enum<MethodToInvoke> {
        private static final /* synthetic */ MethodToInvoke[] $VALUES = null;
        public static final MethodToInvoke BUILD_MESSAGE_INFO = null;
        public static final MethodToInvoke GET_DEFAULT_INSTANCE = null;
        public static final MethodToInvoke GET_MEMOIZED_IS_INITIALIZED = null;
        public static final MethodToInvoke GET_PARSER = null;
        public static final MethodToInvoke NEW_BUILDER = null;
        public static final MethodToInvoke NEW_MUTABLE_INSTANCE = null;
        public static final MethodToInvoke SET_MEMOIZED_IS_INITIALIZED = null;

        static {
            MethodToInvoke r0 = new MethodToInvoke("GET_MEMOIZED_IS_INITIALIZED", 0);
            GET_MEMOIZED_IS_INITIALIZED = r0;
            MethodToInvoke r1 = new MethodToInvoke("SET_MEMOIZED_IS_INITIALIZED", 1);
            SET_MEMOIZED_IS_INITIALIZED = r1;
            MethodToInvoke r2 = new MethodToInvoke("BUILD_MESSAGE_INFO", 2);
            BUILD_MESSAGE_INFO = r2;
            MethodToInvoke r3 = new MethodToInvoke("NEW_MUTABLE_INSTANCE", 3);
            NEW_MUTABLE_INSTANCE = r3;
            MethodToInvoke r4 = new MethodToInvoke("NEW_BUILDER", 4);
            NEW_BUILDER = r4;
            MethodToInvoke r5 = new MethodToInvoke("GET_DEFAULT_INSTANCE", 5);
            GET_DEFAULT_INSTANCE = r5;
            MethodToInvoke r6 = new MethodToInvoke("GET_PARSER", 6);
            GET_PARSER = r6;
            $VALUES = new MethodToInvoke[]{r0, r1, r2, r3, r4, r5, r6};
        }

        MethodToInvoke(String r1, int r2) {
        }

        public static MethodToInvoke valueOf(String r1) {
            return (MethodToInvoke) Enum.valueOf(MethodToInvoke.class, r1);
        }

        public static MethodToInvoke[] values() {
            return (MethodToInvoke[]) $VALUES.clone();
        }
    }

    static {
        defaultInstanceMap = new ConcurrentHashMap();
    }

    public GeneratedMessageLite() {
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSetLite.getDefaultInstance();
    }

    public static /* synthetic */ GeneratedExtension access$000(ExtensionLite r0) {
        return checkIsLite(r0);
    }

    public static /* synthetic */ GeneratedMessageLite access$200(GeneratedMessageLite r0, byte[] r1, int r2, int r3, ExtensionRegistryLite r4) {
        return parsePartialFrom(r0, r1, r2, r3, r4);
    }

    private static <MessageType, BuilderType, T> GeneratedExtension<MessageType, T> checkIsLite(ExtensionLite<MessageType, T> r1) {
        if (r1.isLite() == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Expected a lite extension.");
        return null;
    L5:
        return (GeneratedExtension) r1;
    }

    private static <T extends GeneratedMessageLite<T, ?>> T checkMessageInitialized(T r1) {
        if (r1 != null) goto L4;
    L8:
        return r1;
    L4:
        if (r1.isInitialized() == true) goto L8;
        throw r1.newUninitializedMessageException().asInvalidProtocolBufferException().setUnfinishedMessage(r1);
    }

    private int computeSerializedSize(Schema<?> r1) {
        if (r1 != null) goto L6;
        return Protobuf.getInstance().schemaFor(this).getSerializedSize(this);
    L6:
        return r1.getSerializedSize(this);
    }

    public static Internal.BooleanList emptyBooleanList() {
        return BooleanArrayList.emptyList();
    }

    public static Internal.DoubleList emptyDoubleList() {
        return DoubleArrayList.emptyList();
    }

    public static Internal.FloatList emptyFloatList() {
        return FloatArrayList.emptyList();
    }

    public static Internal.IntList emptyIntList() {
        return IntArrayList.emptyList();
    }

    public static Internal.LongList emptyLongList() {
        return LongArrayList.emptyList();
    }

    public static <E> Internal.ProtobufList<E> emptyProtobufList() {
        return ProtobufArrayList.emptyList();
    }

    private void ensureUnknownFieldsInitialized() {
        if (this.unknownFields != UnknownFieldSetLite.getDefaultInstance()) goto L6;
        this.unknownFields = UnknownFieldSetLite.newInstance();
        return;
    }

    public static <T extends GeneratedMessageLite<?, ?>> T getDefaultInstance(Class<T> r4) {
        T r0 = (T) defaultInstanceMap.get(r4);
        if (r0 == null) goto L17;
    L9:
        if (r0 != null) goto L16;
        T r02 = (T) ((GeneratedMessageLite) UnsafeUtil.allocateInstance(r4)).getDefaultInstanceForType();
        if (r02 == null) goto L14;
        defaultInstanceMap.put(r4, r02);
        return r02;
    L14:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L16:
        return r0;
    L17:
        Class.forName(r4.getName(), true, r4.getClassLoader());     // Catch: ClassNotFoundException -> L6
        r0 = (T) defaultInstanceMap.get(r4);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public static Method getMethodOrDie(Class r6, String r7, Class... r8) {
        return r6.getMethod(r7, r8);
    L4:
        e = move-exception;
        KClassUtil$$ExternalSyntheticBUOutline1.m("Generated message class \"", r6.getName(), "\" missing method \"", r7, "\".", e);
        return null;
    }

    public static Object invokeOrDie(Method r1, Object r2, Object... r3) {
        return r1.invoke(r2, r3);
    L16:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Couldn't use Java reflection to implement protocol message reflection.", e);
        return null;
    L5:
        e = move-exception;
        Throwable r12 = e.getCause();
        if ((r12 instanceof RuntimeException) == true) goto L15;
        if ((r12 instanceof Error) == true) goto L13;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unexpected exception thrown by generated accessor method.", r12);
        return null;
    L13:
        throw ((Error) r12);
    L15:
        throw ((RuntimeException) r12);
    }

    public static final <T extends GeneratedMessageLite<T, ?>> boolean isInitialized(T r2, boolean r3) {
        byte r0 = ((Byte) r2.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
        if (r0 != 1) goto L5;
        return true;
    L5:
        if (r0 != 0) goto L8;
        return false;
    L8:
        boolean r02 = Protobuf.getInstance().schemaFor(r2).isInitialized(r2);
        if (r3 == false) goto L15;
        MethodToInvoke r32 = MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED;
        if (r02 == false) goto L13;
        Object r1 = r2;
    L14:
        r2.dynamicMethod(r32, r1);
        goto L15
    L13:
        r1 = null;
    L15:
        return r02;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$BooleanList] */
    public static Internal.BooleanList mutableCopy(Internal.BooleanList r1) {
        int r0 = r1.size();
        if (r0 != 0) goto L5;
        int r02 = 10;
    L7:
        return r1.mutableCopyWithCapacity(r02);
    L5:
        r02 = r0 * 2;
        goto L7
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$DoubleList] */
    public static Internal.DoubleList mutableCopy(Internal.DoubleList r1) {
        int r0 = r1.size();
        if (r0 != 0) goto L5;
        int r02 = 10;
    L7:
        return r1.mutableCopyWithCapacity(r02);
    L5:
        r02 = r0 * 2;
        goto L7
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$FloatList] */
    public static Internal.FloatList mutableCopy(Internal.FloatList r1) {
        int r0 = r1.size();
        if (r0 != 0) goto L5;
        int r02 = 10;
    L7:
        return r1.mutableCopyWithCapacity(r02);
    L5:
        r02 = r0 * 2;
        goto L7
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$IntList] */
    public static Internal.IntList mutableCopy(Internal.IntList r1) {
        int r0 = r1.size();
        if (r0 != 0) goto L5;
        int r02 = 10;
    L7:
        return r1.mutableCopyWithCapacity(r02);
    L5:
        r02 = r0 * 2;
        goto L7
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.Internal$LongList] */
    public static Internal.LongList mutableCopy(Internal.LongList r1) {
        int r0 = r1.size();
        if (r0 != 0) goto L5;
        int r02 = 10;
    L7:
        return r1.mutableCopyWithCapacity(r02);
    L5:
        r02 = r0 * 2;
        goto L7
    }

    public static <E> Internal.ProtobufList<E> mutableCopy(Internal.ProtobufList<E> r1) {
        int r0 = r1.size();
        if (r0 != 0) goto L5;
        int r02 = 10;
    L7:
        return r1.mutableCopyWithCapacity(r02);
    L5:
        r02 = r0 * 2;
        goto L7
    }

    public static Object newMessageInfo(MessageLite r1, String r2, Object[] r3) {
        return new RawMessageInfo(r1, r2, r3);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType r6, MessageLite r7, Internal.EnumLiteMap<?> r8, int r9, WireFormat.FieldType r10, boolean r11, Class r12) {
        return new GeneratedExtension(r6, Collections.EMPTY_LIST, r7, new ExtensionDescriptor(r8, r9, r10, true, r11), r12);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType r6, Type r7, MessageLite r8, Internal.EnumLiteMap<?> r9, int r10, WireFormat.FieldType r11, Class r12) {
        return new GeneratedExtension(r6, r7, r8, new ExtensionDescriptor(r9, r10, r11, false, false), r12);
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T r1, InputStream r2) {
        return (T) checkMessageInitialized(parsePartialDelimitedFrom(r1, r2, ExtensionRegistryLite.getEmptyRegistry()));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T r0, InputStream r1, ExtensionRegistryLite r2) {
        return (T) checkMessageInitialized(parsePartialDelimitedFrom(r0, r1, r2));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r1, ByteString r2) {
        return (T) checkMessageInitialized(parseFrom(r1, r2, ExtensionRegistryLite.getEmptyRegistry()));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r0, ByteString r1, ExtensionRegistryLite r2) {
        return (T) checkMessageInitialized(parsePartialFrom(r0, r1, r2));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r1, CodedInputStream r2) {
        return (T) parseFrom(r1, r2, ExtensionRegistryLite.getEmptyRegistry());
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r0, CodedInputStream r1, ExtensionRegistryLite r2) {
        return (T) checkMessageInitialized(parsePartialFrom(r0, r1, r2));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r1, InputStream r2) {
        return (T) checkMessageInitialized(parsePartialFrom(r1, CodedInputStream.newInstance(r2), ExtensionRegistryLite.getEmptyRegistry()));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r0, InputStream r1, ExtensionRegistryLite r2) {
        return (T) checkMessageInitialized(parsePartialFrom(r0, CodedInputStream.newInstance(r1), r2));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r1, ByteBuffer r2) {
        return (T) parseFrom(r1, r2, ExtensionRegistryLite.getEmptyRegistry());
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r0, ByteBuffer r1, ExtensionRegistryLite r2) {
        return (T) checkMessageInitialized(parseFrom(r0, CodedInputStream.newInstance(r1), r2));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r3, byte[] r4) {
        return (T) checkMessageInitialized(parsePartialFrom(r3, r4, 0, r4.length, ExtensionRegistryLite.getEmptyRegistry()));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r2, byte[] r3, ExtensionRegistryLite r4) {
        return (T) checkMessageInitialized(parsePartialFrom(r2, r3, 0, r3.length, r4));
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialDelimitedFrom(T r2, InputStream r3, ExtensionRegistryLite r4) {
        int r0 = r3.read();     // Catch: IOException -> L13 InvalidProtocolBufferException -> L16
        if (r0 != (-1)) goto L6;
        return null;
    L6:
        CodedInputStream r32 = CodedInputStream.newInstance(new AbstractMessageLite.Builder.LimitedInputStream(r3, CodedInputStream.readRawVarint32(r0, r3)));
        T r22 = (T) parsePartialFrom(r2, r32, r4);
        r32.checkLastTagWas(0);     // Catch: InvalidProtocolBufferException -> L10
        return r22;
    L10:
        e = move-exception;
        throw e.setUnfinishedMessage(r22);
    L16:
        e = move-exception;
        if (e.getThrownFromInputStream() == true) goto L20;
        throw e;
    L20:
        throw new InvalidProtocolBufferException(e);
    L13:
        e = move-exception;
        throw new InvalidProtocolBufferException(e);
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T r0, ByteString r1, ExtensionRegistryLite r2) {
        CodedInputStream r12 = r1.newCodedInput();
        T r02 = (T) parsePartialFrom(r0, r12, r2);
        r12.checkLastTagWas(0);     // Catch: InvalidProtocolBufferException -> L5
        return r02;
    L5:
        e = move-exception;
        throw e.setUnfinishedMessage(r02);
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T r1, CodedInputStream r2) {
        return (T) parsePartialFrom(r1, r2, ExtensionRegistryLite.getEmptyRegistry());
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T r1, CodedInputStream r2, ExtensionRegistryLite r3) {
        T r12 = (T) r1.newMutableInstance();
        Schema r0 = Protobuf.getInstance().schemaFor(r12);     // Catch: IOException -> L5 UninitializedMessageException -> L7 InvalidProtocolBufferException -> L9 RuntimeException -> L11
        r0.mergeFrom(r12, CodedInputStreamReader.forCodedInput(r2), r3);     // Catch: IOException -> L5 UninitializedMessageException -> L7 InvalidProtocolBufferException -> L9 RuntimeException -> L11
        r0.makeImmutable(r12);     // Catch: IOException -> L5 UninitializedMessageException -> L7 InvalidProtocolBufferException -> L9 RuntimeException -> L11
        return r12;
    L9:
        e = e;
        if (e.getThrownFromInputStream() == false) goto L29;
        e = new InvalidProtocolBufferException(e);
    L29:
        throw e.setUnfinishedMessage(r12);
    L7:
        e = move-exception;
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(r12);
    L5:
        e = move-exception;
        if ((e.getCause() instanceof InvalidProtocolBufferException) == false) goto L22;
        throw ((InvalidProtocolBufferException) e.getCause());
    L22:
        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(r12);
    L11:
        e = move-exception;
        if ((e.getCause() instanceof InvalidProtocolBufferException) == true) goto L15;
        throw e;
    L15:
        throw ((InvalidProtocolBufferException) e.getCause());
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T r6, byte[] r7, int r8, int r9, ExtensionRegistryLite r10) {
        T r1 = (T) r6.newMutableInstance();
        Schema r0 = Protobuf.getInstance().schemaFor(r1);     // Catch: IOException -> L5 UninitializedMessageException -> L7 InvalidProtocolBufferException -> L9 IndexOutOfBoundsException -> L11
        r0.mergeFrom(r1, r7, r8, r8 + r9, new ArrayDecoders.Registers(r10));     // Catch: IOException -> L5 UninitializedMessageException -> L7 InvalidProtocolBufferException -> L9 IndexOutOfBoundsException -> L11
        r0.makeImmutable(r1);     // Catch: IOException -> L5 UninitializedMessageException -> L7 InvalidProtocolBufferException -> L9 IndexOutOfBoundsException -> L11
        return r1;
    L9:
        e = move-exception;
        InvalidProtocolBufferException r62 = e;
        if (r62.getThrownFromInputStream() == false) goto L25;
        r62 = new InvalidProtocolBufferException(r62);
    L25:
        throw r62.setUnfinishedMessage(r1);
    L7:
        e = move-exception;
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(r1);
    L5:
        e = move-exception;
        if ((e.getCause() instanceof InvalidProtocolBufferException) == false) goto L18;
        throw ((InvalidProtocolBufferException) e.getCause());
    L18:
        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(r1);
    L12:
        throw InvalidProtocolBufferException.truncatedMessage().setUnfinishedMessage(r1);
    }

    public static <T extends GeneratedMessageLite<?, ?>> void registerDefaultInstance(Class<T> r1, T r2) {
        r2.markImmutable();
        defaultInstanceMap.put(r1, r2);
    }

    public Object buildMessageInfo() {
        return dynamicMethod(MethodToInvoke.BUILD_MESSAGE_INFO);
    }

    public void clearMemoizedHashCode() {
        this.memoizedHashCode = 0;
    }

    public void clearMemoizedSerializedSize() {
        setMemoizedSerializedSize(Integer.MAX_VALUE);
    }

    public int computeHashCode() {
        return Protobuf.getInstance().schemaFor(this).hashCode(this);
    }

    public final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder() {
        return (BuilderType) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    public final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder(MessageType r1) {
        return (BuilderType) createBuilder().mergeFrom(r1);
    }

    public Object dynamicMethod(MethodToInvoke r2) {
        return dynamicMethod(r2, null, null);
    }

    public Object dynamicMethod(MethodToInvoke r2, Object r3) {
        return dynamicMethod(r2, r3, null);
    }

    public abstract Object dynamicMethod(MethodToInvoke r1, Object r2, Object r3);

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (getClass() == r4.getClass()) goto L12;
        return false;
    L12:
        return Protobuf.getInstance().schemaFor(this).equals(this, (GeneratedMessageLite) r4);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public final MessageType getDefaultInstanceForType() {
        return (MessageType) dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
        return getDefaultInstanceForType();
    }

    public int getMemoizedHashCode() {
        return this.memoizedHashCode;
    }

    public int getMemoizedSerializedSize() {
        return this.memoizedSerializedSize & Integer.MAX_VALUE;
    }

    public final Parser<MessageType> getParserForType() {
        return (Parser) dynamicMethod(MethodToInvoke.GET_PARSER);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        return getSerializedSize(null);
    }

    @Override // com.google.protobuf.AbstractMessageLite
    public int getSerializedSize(Schema r3) {
        if (isMutable() == false) goto L10;
        int r2 = computeSerializedSize(r3);
        if (r2 < 0) goto L7;
        return r2;
    L7:
        JsonArray$$ExternalSyntheticBUOutline0.m("serialized size must be non-negative, was ", r2);
        return 0;
    L10:
        if (getMemoizedSerializedSize() != Integer.MAX_VALUE) goto L12;
        int r32 = computeSerializedSize(r3);
        setMemoizedSerializedSize(r32);
        return r32;
    L12:
        return getMemoizedSerializedSize();
    }

    public int hashCode() {
        if (isMutable() == false) goto L7;
        return computeHashCode();
    L7:
        if (hashCodeIsNotMemoized() == false) goto L10;
        setMemoizedHashCode(computeHashCode());
    L10:
        return getMemoizedHashCode();
    }

    public boolean hashCodeIsNotMemoized() {
        if (getMemoizedHashCode() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        return isInitialized(this, true);
    }

    public boolean isMutable() {
        if ((this.memoizedSerializedSize & Integer.MIN_VALUE) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void makeImmutable() {
        Protobuf.getInstance().schemaFor(this).makeImmutable(this);
        markImmutable();
    }

    public void markImmutable() {
        this.memoizedSerializedSize &= Integer.MAX_VALUE;
    }

    public void mergeLengthDelimitedField(int r1, ByteString r2) {
        ensureUnknownFieldsInitialized();
        this.unknownFields.mergeLengthDelimitedField(r1, r2);
    }

    public final void mergeUnknownFields(UnknownFieldSetLite r2) {
        this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, r2);
    }

    public void mergeVarintField(int r1, int r2) {
        ensureUnknownFieldsInitialized();
        this.unknownFields.mergeVarintField(r1, r2);
    }

    @Override // com.google.protobuf.MessageLite
    public final BuilderType newBuilderForType() {
        return (BuilderType) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    @Override // com.google.protobuf.MessageLite
    public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
        return newBuilderForType();
    }

    public MessageType newMutableInstance() {
        return (MessageType) dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
    }

    public boolean parseUnknownField(int r3, CodedInputStream r4) {
        if (WireFormat.getTagWireType(r3) != 4) goto L6;
        return false;
    L6:
        ensureUnknownFieldsInitialized();
        return this.unknownFields.mergeFieldFrom(r3, r4);
    }

    public void setMemoizedHashCode(int r1) {
        this.memoizedHashCode = r1;
    }

    public void setMemoizedSerializedSize(int r3) {
        if (r3 < 0) goto L5;
        int r32 = r3 & Integer.MAX_VALUE;
        this.memoizedSerializedSize = r32 | (this.memoizedSerializedSize & Integer.MIN_VALUE);
        return;
    L5:
        JsonArray$$ExternalSyntheticBUOutline0.m("serialized size must be non-negative, was ", r3);
    }

    @Override // com.google.protobuf.MessageLite
    public final BuilderType toBuilder() {
        return (BuilderType) ((Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER)).mergeFrom(this);
    }

    @Override // com.google.protobuf.MessageLite
    public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
        return toBuilder();
    }

    public String toString() {
        return MessageLiteToString.toString(this, super.toString());
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream r2) {
        Protobuf.getInstance().schemaFor(this).writeTo(this, CodedOutputStreamWriter.forCodedOutput(r2));
    }
}
