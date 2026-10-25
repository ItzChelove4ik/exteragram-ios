package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.WireFormat;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class CodedInputStreamReader implements Reader {
    private int endGroupTag;
    private final CodedInputStream input;
    private int nextTag;
    private int tag;

    /* renamed from: androidx.datastore.preferences.protobuf.CodedInputStreamReader$1, reason: invalid class name */
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
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 4;     // Catch: NoSuchFieldError -> L24
        L62:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 5;     // Catch: NoSuchFieldError -> L25
        L46:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;     // Catch: NoSuchFieldError -> L26
        L50:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 7;     // Catch: NoSuchFieldError -> L27
        L70:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 8;     // Catch: NoSuchFieldError -> L28
        L40:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 9;     // Catch: NoSuchFieldError -> L29
        L64:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 10;     // Catch: NoSuchFieldError -> L30
        L66:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 11;     // Catch: NoSuchFieldError -> L31
        L52:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 12;     // Catch: NoSuchFieldError -> L32
        L58:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 13;     // Catch: NoSuchFieldError -> L33
        L42:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 14;     // Catch: NoSuchFieldError -> L34
        L48:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 15;     // Catch: NoSuchFieldError -> L35
        L68:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 16;     // Catch: NoSuchFieldError -> L36
        L38:
            $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 17;     // Catch: NoSuchFieldError -> L37
            return;
        }
    }

    private CodedInputStreamReader(CodedInputStream r2) {
        this.nextTag = 0;
        CodedInputStream r22 = (CodedInputStream) Internal.checkNotNull(r2, PluginsConstants.Settings.TYPE_INPUT);
        this.input = r22;
        r22.wrapper = this;
    }

    public static CodedInputStreamReader forCodedInput(CodedInputStream r1) {
        CodedInputStreamReader r0 = r1.wrapper;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new CodedInputStreamReader(r1);
    }

    private <T> void mergeGroupFieldInternal(T r4, Schema<T> r5, ExtensionRegistryLite r6) {
        int r0 = this.endGroupTag;
        this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
        r5.mergeFrom(r4, this, r6);     // Catch: Throwable -> L9
        if (this.tag != this.endGroupTag) goto L8;
        this.endGroupTag = r0;
        return;
    L8:
        throw InvalidProtocolBufferException.parseFailure();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        this.endGroupTag = r0;
        throw th;
    }

    private <T> void mergeMessageFieldInternal(T r5, Schema<T> r6, ExtensionRegistryLite r7) {
        int r0 = this.input.readUInt32();
        CodedInputStream r1 = this.input;
        if (r1.recursionDepth >= r1.recursionLimit) goto L7;
        int r02 = r1.pushLimit(r0);
        this.input.recursionDepth++;
        r6.mergeFrom(r5, this, r7);
        this.input.checkLastTagWas(0);
        r4.recursionDepth--;
        this.input.popLimit(r02);
        return;
    L7:
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    private Object readField(WireFormat.FieldType r2, Class<?> r3, ExtensionRegistryLite r4) {
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[r2.ordinal()]) {
            case 1: goto L39;
            case 2: goto L37;
            case 3: goto L35;
            case 4: goto L33;
            case 5: goto L31;
            case 6: goto L29;
            case 7: goto L27;
            case 8: goto L25;
            case 9: goto L23;
            case 10: goto L21;
            case 11: goto L19;
            case 12: goto L17;
            case 13: goto L15;
            case 14: goto L13;
            case 15: goto L11;
            case 16: goto L9;
            case 17: goto L7;
            default: goto L4;
        };
    L4:
        f$$ExternalSyntheticBUOutline1.m("unsupported field type.");
        return null;
    L7:
        return Long.valueOf(readUInt64());
    L9:
        return Integer.valueOf(readUInt32());
    L11:
        return readStringRequireUtf8();
    L13:
        return Long.valueOf(readSInt64());
    L15:
        return Integer.valueOf(readSInt32());
    L17:
        return Long.valueOf(readSFixed64());
    L19:
        return Integer.valueOf(readSFixed32());
    L21:
        return readMessage(r3, r4);
    L23:
        return Long.valueOf(readInt64());
    L25:
        return Integer.valueOf(readInt32());
    L27:
        return Float.valueOf(readFloat());
    L29:
        return Long.valueOf(readFixed64());
    L31:
        return Integer.valueOf(readFixed32());
    L33:
        return Integer.valueOf(readEnum());
    L35:
        return Double.valueOf(readDouble());
    L37:
        return readBytes();
    L39:
        return Boolean.valueOf(readBool());
    }

    private <T> T readGroup(Schema<T> r2, ExtensionRegistryLite r3) {
        T r0 = r2.newInstance();
        mergeGroupFieldInternal(r0, r2, r3);
        r2.makeImmutable(r0);
        return r0;
    }

    private <T> T readMessage(Schema<T> r2, ExtensionRegistryLite r3) {
        T r0 = r2.newInstance();
        mergeMessageFieldInternal(r0, r2, r3);
        r2.makeImmutable(r0);
        return r0;
    }

    private void requirePosition(int r1) {
        if (this.input.getTotalBytesRead() != r1) goto L6;
        return;
    L6:
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    private void requireWireType(int r1) {
        if (WireFormat.getTagWireType(this.tag) != r1) goto L6;
        return;
    L6:
        throw InvalidProtocolBufferException.invalidWireType();
    }

    private void verifyPackedFixed32Length(int r1) {
        if ((r1 & 3) != 0) goto L6;
        return;
    L6:
        throw InvalidProtocolBufferException.parseFailure();
    }

    private void verifyPackedFixed64Length(int r1) {
        if ((r1 & 7) != 0) goto L6;
        return;
    L6:
        throw InvalidProtocolBufferException.parseFailure();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int getFieldNumber() {
        int r0 = this.nextTag;
        if (r0 == 0) goto L5;
        this.tag = r0;
        this.nextTag = 0;
    L6:
        int r02 = this.tag;
        if (r02 != 0) goto L9;
        return Integer.MAX_VALUE;
    L9:
        if (r02 != this.endGroupTag) goto L12;
        return Integer.MAX_VALUE;
    L12:
        return WireFormat.getTagFieldNumber(r02);
    L5:
        this.tag = this.input.readTag();
        goto L6
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int getTag() {
        return this.tag;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public <T> void mergeGroupField(T r2, Schema<T> r3, ExtensionRegistryLite r4) {
        requireWireType(3);
        mergeGroupFieldInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public <T> void mergeMessageField(T r2, Schema<T> r3, ExtensionRegistryLite r4) {
        requireWireType(2);
        mergeMessageFieldInternal(r2, r3, r4);
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public boolean readBool() {
        requireWireType(0);
        return this.input.readBool();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readBoolList(List<Boolean> r3) {
        if ((r3 instanceof BooleanArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        int r32 = WireFormat.getTagWireType(this.tag);
        if (r32 == 0) goto L11;
        if (r32 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readBool();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readBool();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r3.add(Boolean.valueOf(this.input.readBool()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r3.add(Boolean.valueOf(this.input.readBool()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public ByteString readBytes() {
        requireWireType(2);
        return this.input.readBytes();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readBytesList(List<ByteString> r3) {
        if (WireFormat.getTagWireType(this.tag) != 2) goto L12;
    L4:
        r3.add(readBytes());
        if (this.input.isAtEnd() == true) goto L6;
        int r0 = this.input.readTag();
        if (r0 == this.tag) goto L4;
        this.nextTag = r0;
        return;
    L6:
        return;
    L12:
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public double readDouble() {
        requireWireType(1);
        return this.input.readDouble();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readDoubleList(List<Double> r5) {
        if ((r5 instanceof DoubleArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = WireFormat.getTagWireType(this.tag);
        if (r52 == 1) goto L11;
        if (r52 != 2) goto L8;
        verifyPackedFixed64Length(this.input.readUInt32());
        this.input.getTotalBytesRead();
        this.input.readDouble();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readDouble();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 1) goto L22;
        if (r0 != 2) goto L21;
        int r02 = this.input.readUInt32();
        verifyPackedFixed64Length(r02);
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r5.add(Double.valueOf(this.input.readDouble()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        return;
    L21:
        throw InvalidProtocolBufferException.invalidWireType();
    L22:
        r5.add(Double.valueOf(this.input.readDouble()));
        if (this.input.isAtEnd() == true) goto L34;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L22;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int readEnum() {
        requireWireType(0);
        return this.input.readEnum();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readEnumList(List<Integer> r3) {
        if ((r3 instanceof IntArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        int r32 = WireFormat.getTagWireType(this.tag);
        if (r32 == 0) goto L11;
        if (r32 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readEnum();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readEnum();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r3.add(Integer.valueOf(this.input.readEnum()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r3.add(Integer.valueOf(this.input.readEnum()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int readFixed32() {
        requireWireType(5);
        return this.input.readFixed32();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readFixed32List(List<Integer> r4) {
        if ((r4 instanceof IntArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        int r42 = WireFormat.getTagWireType(this.tag);
        if (r42 == 2) goto L11;
        if (r42 != 5) goto L8;
        this.input.readFixed32();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        verifyPackedFixed32Length(this.input.readUInt32());
        this.input.getTotalBytesRead();
        this.input.readFixed32();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 2) goto L25;
        if (r0 != 5) goto L24;
    L16:
        r4.add(Integer.valueOf(this.input.readFixed32()));
        if (this.input.isAtEnd() == true) goto L28;
        int r02 = this.input.readTag();
        if (r02 == this.tag) goto L16;
        this.nextTag = r02;
        return;
    L28:
        return;
    L24:
        throw InvalidProtocolBufferException.invalidWireType();
    L25:
        int r03 = this.input.readUInt32();
        verifyPackedFixed32Length(r03);
        int r1 = this.input.getTotalBytesRead() + r03;
    L26:
        r4.add(Integer.valueOf(this.input.readFixed32()));
        if (this.input.getTotalBytesRead() < r1) goto L26;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public long readFixed64() {
        requireWireType(1);
        return this.input.readFixed64();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readFixed64List(List<Long> r5) {
        if ((r5 instanceof LongArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = WireFormat.getTagWireType(this.tag);
        if (r52 == 1) goto L11;
        if (r52 != 2) goto L8;
        verifyPackedFixed64Length(this.input.readUInt32());
        this.input.getTotalBytesRead();
        this.input.readFixed64();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readFixed64();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 1) goto L22;
        if (r0 != 2) goto L21;
        int r02 = this.input.readUInt32();
        verifyPackedFixed64Length(r02);
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r5.add(Long.valueOf(this.input.readFixed64()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        return;
    L21:
        throw InvalidProtocolBufferException.invalidWireType();
    L22:
        r5.add(Long.valueOf(this.input.readFixed64()));
        if (this.input.isAtEnd() == true) goto L34;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L22;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public float readFloat() {
        requireWireType(5);
        return this.input.readFloat();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readFloatList(List<Float> r4) {
        if ((r4 instanceof FloatArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        int r42 = WireFormat.getTagWireType(this.tag);
        if (r42 == 2) goto L11;
        if (r42 != 5) goto L8;
        this.input.readFloat();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        verifyPackedFixed32Length(this.input.readUInt32());
        this.input.getTotalBytesRead();
        this.input.readFloat();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 2) goto L25;
        if (r0 != 5) goto L24;
    L16:
        r4.add(Float.valueOf(this.input.readFloat()));
        if (this.input.isAtEnd() == true) goto L28;
        int r02 = this.input.readTag();
        if (r02 == this.tag) goto L16;
        this.nextTag = r02;
        return;
    L28:
        return;
    L24:
        throw InvalidProtocolBufferException.invalidWireType();
    L25:
        int r03 = this.input.readUInt32();
        verifyPackedFixed32Length(r03);
        int r1 = this.input.getTotalBytesRead() + r03;
    L26:
        r4.add(Float.valueOf(this.input.readFloat()));
        if (this.input.getTotalBytesRead() < r1) goto L26;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.protobuf.Reader
    @Deprecated
    public <T> void readGroupList(List<T> r3, Schema<T> r4, ExtensionRegistryLite r5) {
        if (WireFormat.getTagWireType(this.tag) != 3) goto L15;
        int r0 = this.tag;
    L5:
        r3.add(readGroup(r4, r5));
        if (this.input.isAtEnd() == true) goto L20;
        if (this.nextTag != 0) goto L21;
        int r1 = this.input.readTag();
        if (r1 == r0) goto L5;
        this.nextTag = r1;
        return;
    L21:
        return;
    L20:
        return;
    L15:
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int readInt32() {
        requireWireType(0);
        return this.input.readInt32();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readInt32List(List<Integer> r3) {
        if ((r3 instanceof IntArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        int r32 = WireFormat.getTagWireType(this.tag);
        if (r32 == 0) goto L11;
        if (r32 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readInt32();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readInt32();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r3.add(Integer.valueOf(this.input.readInt32()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r3.add(Integer.valueOf(this.input.readInt32()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public long readInt64() {
        requireWireType(0);
        return this.input.readInt64();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readInt64List(List<Long> r5) {
        if ((r5 instanceof LongArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = WireFormat.getTagWireType(this.tag);
        if (r52 == 0) goto L11;
        if (r52 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readInt64();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readInt64();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r5.add(Long.valueOf(this.input.readInt64()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r5.add(Long.valueOf(this.input.readInt64()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.protobuf.Reader
    public <K, V> void readMap(Map<K, V> r8, MapEntryLite.Metadata<K, V> r9, ExtensionRegistryLite r10) {
        requireWireType(2);
        int r1 = this.input.readUInt32();
        int r12 = this.input.pushLimit(r1);
        Object r2 = r9.defaultKey;
        Object r3 = r9.defaultValue;
    L33:
        int r4 = getFieldNumber();     // Catch: Throwable -> L16
        if (r4 == Integer.MAX_VALUE) goto L26;
        if (this.input.isAtEnd() == true) goto L26;
        if (r4 != 1) goto L10;
        r2 = readField(r9.keyType, null, null);     // Catch: Throwable -> L16 InvalidProtocolBufferException.InvalidWireTypeException -> L21
        goto L33
    L10:
        if (r4 == 2) goto L18;
        if (skipField() == true) goto L33;
        throw new InvalidProtocolBufferException("Unable to parse map entry.");     // Catch: Throwable -> L16 InvalidProtocolBufferException.InvalidWireTypeException -> L21
    L22:
        if (skipField() == true) goto L33;
        throw new InvalidProtocolBufferException("Unable to parse map entry.");     // Catch: Throwable -> L16
    L18:
        r3 = readField(r9.valueType, r9.defaultValue.getClass(), r10);     // Catch: Throwable -> L16 InvalidProtocolBufferException.InvalidWireTypeException -> L21
    L26:
        r8.put(r2, r3);     // Catch: Throwable -> L16
        this.input.popLimit(r12);
        return;
    L16:
        th = move-exception;
        this.input.popLimit(r12);
        throw th;
    }

    public <T> T readMessage(Class<T> r2, ExtensionRegistryLite r3) {
        requireWireType(2);
        return (T) readMessage(Protobuf.getInstance().schemaFor(r2), r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.protobuf.Reader
    public <T> void readMessageList(List<T> r3, Schema<T> r4, ExtensionRegistryLite r5) {
        if (WireFormat.getTagWireType(this.tag) != 2) goto L15;
        int r0 = this.tag;
    L5:
        r3.add(readMessage(r4, r5));
        if (this.input.isAtEnd() == true) goto L20;
        if (this.nextTag != 0) goto L21;
        int r1 = this.input.readTag();
        if (r1 == r0) goto L5;
        this.nextTag = r1;
        return;
    L21:
        return;
    L20:
        return;
    L15:
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int readSFixed32() {
        requireWireType(5);
        return this.input.readSFixed32();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readSFixed32List(List<Integer> r4) {
        if ((r4 instanceof IntArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        int r42 = WireFormat.getTagWireType(this.tag);
        if (r42 == 2) goto L11;
        if (r42 != 5) goto L8;
        this.input.readSFixed32();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        verifyPackedFixed32Length(this.input.readUInt32());
        this.input.getTotalBytesRead();
        this.input.readSFixed32();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 2) goto L25;
        if (r0 != 5) goto L24;
    L16:
        r4.add(Integer.valueOf(this.input.readSFixed32()));
        if (this.input.isAtEnd() == true) goto L28;
        int r02 = this.input.readTag();
        if (r02 == this.tag) goto L16;
        this.nextTag = r02;
        return;
    L28:
        return;
    L24:
        throw InvalidProtocolBufferException.invalidWireType();
    L25:
        int r03 = this.input.readUInt32();
        verifyPackedFixed32Length(r03);
        int r1 = this.input.getTotalBytesRead() + r03;
    L26:
        r4.add(Integer.valueOf(this.input.readSFixed32()));
        if (this.input.getTotalBytesRead() < r1) goto L26;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public long readSFixed64() {
        requireWireType(1);
        return this.input.readSFixed64();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readSFixed64List(List<Long> r5) {
        if ((r5 instanceof LongArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = WireFormat.getTagWireType(this.tag);
        if (r52 == 1) goto L11;
        if (r52 != 2) goto L8;
        verifyPackedFixed64Length(this.input.readUInt32());
        this.input.getTotalBytesRead();
        this.input.readSFixed64();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readSFixed64();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 1) goto L22;
        if (r0 != 2) goto L21;
        int r02 = this.input.readUInt32();
        verifyPackedFixed64Length(r02);
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r5.add(Long.valueOf(this.input.readSFixed64()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        return;
    L21:
        throw InvalidProtocolBufferException.invalidWireType();
    L22:
        r5.add(Long.valueOf(this.input.readSFixed64()));
        if (this.input.isAtEnd() == true) goto L34;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L22;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int readSInt32() {
        requireWireType(0);
        return this.input.readSInt32();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readSInt32List(List<Integer> r3) {
        if ((r3 instanceof IntArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        int r32 = WireFormat.getTagWireType(this.tag);
        if (r32 == 0) goto L11;
        if (r32 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readSInt32();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readSInt32();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r3.add(Integer.valueOf(this.input.readSInt32()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r3.add(Integer.valueOf(this.input.readSInt32()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public long readSInt64() {
        requireWireType(0);
        return this.input.readSInt64();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readSInt64List(List<Long> r5) {
        if ((r5 instanceof LongArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = WireFormat.getTagWireType(this.tag);
        if (r52 == 0) goto L11;
        if (r52 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readSInt64();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readSInt64();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r5.add(Long.valueOf(this.input.readSInt64()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r5.add(Long.valueOf(this.input.readSInt64()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public String readString() {
        requireWireType(2);
        return this.input.readString();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readStringList(List<String> r2) {
        readStringListInternal(r2, false);
    }

    public void readStringListInternal(List<String> r3, boolean r4) {
        if (WireFormat.getTagWireType(this.tag) != 2) goto L26;
        if ((r3 instanceof LazyStringList) == false) goto L15;
        if (r4 == true) goto L15;
        LazyStringList r0 = (LazyStringList) r3;
    L8:
        r0.add(readBytes());
        if (this.input.isAtEnd() == true) goto L20;
        int r32 = this.input.readTag();
        if (r32 == this.tag) goto L8;
        this.nextTag = r32;
        return;
    L20:
        return;
    L15:
        if (r4 == false) goto L17;
        String r02 = readStringRequireUtf8();
    L18:
        r3.add(r02);
        if (this.input.isAtEnd() == true) goto L34;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L15;
        this.nextTag = r03;
        return;
    L34:
        return;
    L17:
        r02 = readString();
        goto L18
    L26:
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readStringListRequireUtf8(List<String> r2) {
        readStringListInternal(r2, true);
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public String readStringRequireUtf8() {
        requireWireType(2);
        return this.input.readStringRequireUtf8();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public int readUInt32() {
        requireWireType(0);
        return this.input.readUInt32();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readUInt32List(List<Integer> r3) {
        if ((r3 instanceof IntArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        int r32 = WireFormat.getTagWireType(this.tag);
        if (r32 == 0) goto L11;
        if (r32 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readUInt32();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readUInt32();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r3.add(Integer.valueOf(this.input.readUInt32()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r3.add(Integer.valueOf(this.input.readUInt32()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public long readUInt64() {
        requireWireType(0);
        return this.input.readUInt64();
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public void readUInt64List(List<Long> r5) {
        if ((r5 instanceof LongArrayList) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = WireFormat.getTagWireType(this.tag);
        if (r52 == 0) goto L11;
        if (r52 != 2) goto L8;
        this.input.readUInt32();
        this.input.getTotalBytesRead();
        this.input.readUInt64();
        throw null;
    L8:
        throw InvalidProtocolBufferException.invalidWireType();
    L11:
        this.input.readUInt64();
        throw null;
    L13:
        int r0 = WireFormat.getTagWireType(this.tag);
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L22;
        int r02 = this.input.readUInt32();
        int r1 = this.input.getTotalBytesRead() + r02;
    L17:
        r5.add(Long.valueOf(this.input.readUInt64()));
        if (this.input.getTotalBytesRead() < r1) goto L17;
        requirePosition(r1);
        return;
    L22:
        throw InvalidProtocolBufferException.invalidWireType();
    L23:
        r5.add(Long.valueOf(this.input.readUInt64()));
        if (this.input.isAtEnd() == true) goto L25;
        int r03 = this.input.readTag();
        if (r03 == this.tag) goto L23;
        this.nextTag = r03;
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Reader
    public boolean skipField() {
        if (this.input.isAtEnd() == true) goto L9;
        int r0 = this.tag;
        if (r0 != this.endGroupTag) goto L8;
        return false;
    L8:
        return this.input.skipField(r0);
    L9:
        return false;
    }
}
