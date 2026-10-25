package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.Writer;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.Arrays;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class UnknownFieldSetLite {
    private static final UnknownFieldSetLite DEFAULT_INSTANCE = null;
    private int count;
    private boolean isMutable;
    private int memoizedSerializedSize;
    private Object[] objects;
    private int[] tags;

    static {
        DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    }

    private UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    private UnknownFieldSetLite(int r2, int[] r3, Object[] r4, boolean r5) {
        this.memoizedSerializedSize = -1;
        this.count = r2;
        this.tags = r3;
        this.objects = r4;
        this.isMutable = r5;
    }

    private void ensureCapacity(int r4) {
        int[] r0 = this.tags;
        if (r4 <= r0.length) goto L13;
        int r1 = this.count;
        int r12 = r1 + (r1 / 2);
        if (r12 < r4) goto L9;
        r4 = r12;
    L9:
        if (r4 >= 8) goto L11;
        r4 = 8;
    L11:
        this.tags = Arrays.copyOf(r0, r4);
        this.objects = Arrays.copyOf(this.objects, r4);
        return;
    }

    public static UnknownFieldSetLite getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static int hashCode(int[] r3, int r4) {
        int r0 = 17;
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L5;
        r0 = (r0 * 31) + r3[r1];
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    private static int hashCode(Object[] r3, int r4) {
        int r0 = 17;
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L5;
        r0 = (r0 * 31) + r3[r1].hashCode();
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    public static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite r6, UnknownFieldSetLite r7) {
        int r0 = r6.count + r7.count;
        int[] r1 = Arrays.copyOf(r6.tags, r0);
        System.arraycopy(r7.tags, 0, r1, r6.count, r7.count);
        Object[] r2 = Arrays.copyOf(r6.objects, r0);
        System.arraycopy(r7.objects, 0, r2, r6.count, r7.count);
        return new UnknownFieldSetLite(r0, r1, r2, true);
    }

    public static UnknownFieldSetLite newInstance() {
        return new UnknownFieldSetLite();
    }

    private static boolean objectsEquals(Object[] r4, Object[] r5, int r6) {
        int r1 = 0;
    L3:
        if (r1 >= r6) goto L8;
        if (r4[r1].equals(r5[r1]) == false) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    private static boolean tagsEquals(int[] r4, int[] r5, int r6) {
        int r1 = 0;
    L3:
        if (r1 >= r6) goto L8;
        if (r4[r1] != r5[r1]) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    private static void writeField(int r2, Object r3, Writer r4) {
        int r0 = WireFormat.getTagFieldNumber(r2);
        int r22 = WireFormat.getTagWireType(r2);
        if (r22 != 0) goto L5;
        r4.writeInt64(r0, ((Long) r3).longValue());
        return;
    L5:
        if (r22 != 1) goto L7;
        r4.writeFixed64(r0, ((Long) r3).longValue());
        return;
    L7:
        if (r22 != 2) goto L9;
        r4.writeBytes(r0, (ByteString) r3);
        return;
    L9:
        if (r22 == 3) goto L17;
        if (r22 != 5) goto L14;
        r4.writeFixed32(r0, ((Integer) r3).intValue());
        return;
    L14:
        HttpUrl$$ExternalSyntheticBUOutline0.m(InvalidProtocolBufferException.invalidWireType());
        return;
    L17:
        if (r4.fieldOrder() != Writer.FieldOrder.ASCENDING) goto L20;
        r4.writeStartGroup(r0);
        ((UnknownFieldSetLite) r3).writeTo(r4);
        r4.writeEndGroup(r0);
        return;
    L20:
        r4.writeEndGroup(r0);
        ((UnknownFieldSetLite) r3).writeTo(r4);
        r4.writeStartGroup(r0);
    }

    public void checkMutable() {
        if (this.isMutable == false) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if (r6 != null) goto L9;
        return false;
    L9:
        if ((r6 instanceof UnknownFieldSetLite) == true) goto L11;
        return false;
    L11:
        UnknownFieldSetLite r62 = (UnknownFieldSetLite) r6;
        int r2 = this.count;
        if (r2 == r62.count) goto L14;
    L19:
        return false;
    L14:
        if (tagsEquals(this.tags, r62.tags, r2) == false) goto L19;
        if (objectsEquals(this.objects, r62.objects, this.count) == false) goto L19;
        return true;
    }

    public int getSerializedSize() {
        int r0 = this.memoizedSerializedSize;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = 0;
        int r1 = 0;
    L7:
        if (r02 >= this.count) goto L27;
        int r2 = this.tags[r02];
        int r3 = WireFormat.getTagFieldNumber(r2);
        int r22 = WireFormat.getTagWireType(r2);
        if (r22 != 0) goto L11;
        int r23 = CodedOutputStream.computeUInt64Size(r3, ((Long) this.objects[r02]).longValue());
    L19:
        r1 = r1 + r23;
        r02 = r02 + 1;
        goto L7
    L11:
        if (r22 != 1) goto L13;
        r23 = CodedOutputStream.computeFixed64Size(r3, ((Long) this.objects[r02]).longValue());
        goto L19
    L13:
        if (r22 != 2) goto L15;
        r23 = CodedOutputStream.computeBytesSize(r3, (ByteString) this.objects[r02]);
        goto L19
    L15:
        if (r22 != 3) goto L17;
        r23 = (CodedOutputStream.computeTagSize(r3) * 2) + ((UnknownFieldSetLite) this.objects[r02]).getSerializedSize();
        goto L19
    L17:
        if (r22 != 5) goto L20;
        r23 = CodedOutputStream.computeFixed32Size(r3, ((Integer) this.objects[r02]).intValue());
        goto L19
    L20:
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(InvalidProtocolBufferException.invalidWireType());
        return 0;
    L27:
        this.memoizedSerializedSize = r1;
        return r1;
    }

    public int getSerializedSizeAsMessageSet() {
        int r0 = this.memoizedSerializedSize;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = 0;
        int r1 = 0;
    L7:
        if (r02 >= this.count) goto L9;
        r1 = r1 + CodedOutputStream.computeRawMessageSetExtensionSize(WireFormat.getTagFieldNumber(this.tags[r02]), (ByteString) this.objects[r02]);
        r02 = r02 + 1;
        goto L7
    L9:
        this.memoizedSerializedSize = r1;
        return r1;
    }

    public int hashCode() {
        int r0 = this.count;
        return ((((527 + r0) * 31) + hashCode(this.tags, r0)) * 31) + hashCode(this.objects, this.count);
    }

    public void makeImmutable() {
        if (this.isMutable == false) goto L6;
        this.isMutable = false;
        return;
    }

    public UnknownFieldSetLite mergeFrom(UnknownFieldSetLite r7) {
        if (r7.equals(getDefaultInstance()) == false) goto L5;
        return this;
    L5:
        checkMutable();
        int r0 = this.count + r7.count;
        ensureCapacity(r0);
        System.arraycopy(r7.tags, 0, this.tags, this.count, r7.count);
        System.arraycopy(r7.objects, 0, this.objects, this.count, r7.count);
        this.count = r0;
        return this;
    }

    public final void printWithIndent(StringBuilder r4, int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.count) goto L6;
        MessageLiteToString.printField(r4, r5, String.valueOf(WireFormat.getTagFieldNumber(this.tags[r0])), this.objects[r0]);
        r0 = r0 + 1;
        goto L4
    }

    public void storeField(int r3, Object r4) {
        checkMutable();
        ensureCapacity(this.count + 1);
        int[] r0 = this.tags;
        int r1 = this.count;
        r0[r1] = r3;
        this.objects[r1] = r4;
        this.count = r1 + 1;
    }

    public void writeAsMessageSetTo(Writer r4) {
        if (r4.fieldOrder() != Writer.FieldOrder.DESCENDING) goto L7;
        int r0 = this.count - 1;
    L5:
        if (r0 < 0) goto L11;
        r4.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[r0]), this.objects[r0]);
        r0 = r0 - 1;
        goto L5
    L11:
        return;
    L7:
        int r02 = 0;
    L9:
        if (r02 >= this.count) goto L14;
        r4.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[r02]), this.objects[r02]);
        r02 = r02 + 1;
        goto L9
    }

    public void writeTo(Writer r4) {
        if (this.count != 0) goto L6;
        return;
    L6:
        if (r4.fieldOrder() != Writer.FieldOrder.ASCENDING) goto L11;
        int r0 = 0;
    L9:
        if (r0 >= this.count) goto L17;
        writeField(this.tags[r0], this.objects[r0], r4);
        r0 = r0 + 1;
        goto L9
    L17:
        return;
    L11:
        int r02 = this.count - 1;
    L12:
        if (r02 < 0) goto L18;
        writeField(this.tags[r02], this.objects[r02], r4);
        r02 = r02 - 1;
        goto L12
    }
}
