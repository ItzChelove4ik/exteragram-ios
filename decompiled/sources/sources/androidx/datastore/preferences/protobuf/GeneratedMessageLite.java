package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.AbstractMessageLite;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite.Builder;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.MessageLite;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
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

        public final MessageType build() {
            MessageType r1 = (MessageType) buildPartial();
            if (r1.isInitialized() == false) goto L6;
            return r1;
        L6:
            throw AbstractMessageLite.Builder.newUninitializedMessageException(r1);
        }

        @Override // androidx.datastore.preferences.protobuf.MessageLite.Builder
        public MessageType buildPartial() {
            boolean r0 = this.instance.isMutable();
            MessageType r1 = this.instance;
            if (r0 == true) goto L5;
            return r1;
        L5:
            r1.makeImmutable();
            return this.instance;
        }

        @Override // androidx.datastore.preferences.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite buildPartial() {
            return buildPartial();
        }

        public BuilderType clone() {
            BuilderType r0 = (BuilderType) getDefaultInstanceForType().newBuilderForType();
            r0.instance = (MessageType) buildPartial();
            return r0;
        }

        /* renamed from: clone, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object m723clone() {
            return clone();
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

        @Override // androidx.datastore.preferences.protobuf.MessageLiteOrBuilder
        public MessageType getDefaultInstanceForType() {
            return this.defaultInstance;
        }

        @Override // androidx.datastore.preferences.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }
    }

    /* loaded from: classes3.dex */
    public static class DefaultInstanceBasedParser<T extends GeneratedMessageLite<T, ?>> extends AbstractParser<T> {
        private final T defaultInstance;

        public DefaultInstanceBasedParser(T r1) {
            this.defaultInstance = r1;
        }
    }

    /* loaded from: classes3.dex */
    public static class GeneratedExtension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {
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

    public static <E> Internal.ProtobufList<E> emptyProtobufList() {
        return ProtobufArrayList.emptyList();
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

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T r1, InputStream r2) {
        return (T) checkMessageInitialized(parsePartialFrom(r1, CodedInputStream.newInstance(r2), ExtensionRegistryLite.getEmptyRegistry()));
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

    @Override // androidx.datastore.preferences.protobuf.MessageLiteOrBuilder
    public final MessageType getDefaultInstanceForType() {
        return (MessageType) dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    @Override // androidx.datastore.preferences.protobuf.MessageLiteOrBuilder
    public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
        return getDefaultInstanceForType();
    }

    public int getMemoizedHashCode() {
        return this.memoizedHashCode;
    }

    public int getMemoizedSerializedSize() {
        return this.memoizedSerializedSize & Integer.MAX_VALUE;
    }

    @Override // androidx.datastore.preferences.protobuf.MessageLite
    public int getSerializedSize() {
        return getSerializedSize(null);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractMessageLite
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

    @Override // androidx.datastore.preferences.protobuf.MessageLite
    public final BuilderType newBuilderForType() {
        return (BuilderType) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    @Override // androidx.datastore.preferences.protobuf.MessageLite
    public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
        return newBuilderForType();
    }

    public MessageType newMutableInstance() {
        return (MessageType) dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
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

    public String toString() {
        return MessageLiteToString.toString(this, super.toString());
    }

    @Override // androidx.datastore.preferences.protobuf.MessageLite
    public void writeTo(CodedOutputStream r2) {
        Protobuf.getInstance().schemaFor(this).writeTo(this, CodedOutputStreamWriter.forCodedOutput(r2));
    }
}
