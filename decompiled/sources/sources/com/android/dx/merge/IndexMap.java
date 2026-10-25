package com.android.dx.merge;

import com.android.dex.Annotation;
import com.android.dex.CallSiteId;
import com.android.dex.ClassDef;
import com.android.dex.Dex;
import com.android.dex.EncodedValue;
import com.android.dex.EncodedValueCodec;
import com.android.dex.EncodedValueReader;
import com.android.dex.FieldId;
import com.android.dex.Leb128;
import com.android.dex.MethodHandle;
import com.android.dex.MethodId;
import com.android.dex.ProtoId;
import com.android.dex.TableOfContents;
import com.android.dex.TypeList;
import com.android.dex.util.ByteOutput;
import com.android.dx.util.ByteArrayAnnotatedOutput;
import java.util.HashMap;
import kotlin.UShort;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class IndexMap {
    private final HashMap<Integer, Integer> annotationDirectoryOffsets;
    private final HashMap<Integer, Integer> annotationOffsets;
    private final HashMap<Integer, Integer> annotationSetOffsets;
    private final HashMap<Integer, Integer> annotationSetRefListOffsets;
    public final int[] callSiteIds;
    private final HashMap<Integer, Integer> encodedArrayValueOffset;
    public final short[] fieldIds;
    public final HashMap<Integer, Integer> methodHandleIds;
    public final short[] methodIds;
    public final short[] protoIds;
    public final int[] stringIds;
    private final Dex target;
    public final short[] typeIds;
    private final HashMap<Integer, Integer> typeListOffsets;

    public final class EncodedValueTransformer {
        private final ByteOutput out;
        final /* synthetic */ IndexMap this$0;

        public EncodedValueTransformer(IndexMap r1, ByteOutput r2) {
            this.this$0 = r1;
            this.out = r2;
        }

        public static /* synthetic */ void access$000(EncodedValueTransformer r0, EncodedValueReader r1) {
            r0.transformArray(r1);
        }

        public static /* synthetic */ void access$100(EncodedValueTransformer r0, EncodedValueReader r1) {
            r0.transformAnnotation(r1);
        }

        private void transformAnnotation(EncodedValueReader r6) {
            int r0 = r6.readAnnotation();
            Leb128.writeUnsignedLeb128(this.out, this.this$0.adjustType(r6.getAnnotationType()));
            Leb128.writeUnsignedLeb128(this.out, r0);
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            Leb128.writeUnsignedLeb128(this.out, this.this$0.adjustString(r6.readAnnotationName()));
            transform(r6);
            r1 = r1 + 1;
            goto L3
        }

        private void transformArray(EncodedValueReader r3) {
            int r0 = r3.readArray();
            Leb128.writeUnsignedLeb128(this.out, r0);
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            transform(r3);
            r1 = r1 + 1;
            goto L3
        }

        private void writeTypeAndArg(int r1, int r2) {
            this.out.writeByte(r1 | (r2 << 5));
        }

        public void transform(EncodedValueReader r5) {
            int r0 = r5.peek();
            if (r0 != 0) goto L5;
            EncodedValueCodec.writeSignedIntegralValue(this.out, 0, r5.readByte());
            return;
        L5:
            if (r0 != 6) goto L7;
            EncodedValueCodec.writeSignedIntegralValue(this.out, 6, r5.readLong());
            return;
        L7:
            if (r0 != 2) goto L9;
            EncodedValueCodec.writeSignedIntegralValue(this.out, 2, r5.readShort());
            return;
        L9:
            if (r0 != 3) goto L11;
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 3, r5.readChar());
            return;
        L11:
            if (r0 != 4) goto L13;
            EncodedValueCodec.writeSignedIntegralValue(this.out, 4, r5.readInt());
            return;
        L13:
            if (r0 != 16) goto L15;
            EncodedValueCodec.writeRightZeroExtendedValue(this.out, 16, Float.floatToIntBits(r5.readFloat()) << 32);
            return;
        L15:
            if (r0 == 17) goto L41;
            switch(r0) {
                case 21: goto L39;
                case 22: goto L37;
                case 23: goto L35;
                case 24: goto L33;
                case 25: goto L31;
                case 26: goto L29;
                case 27: goto L27;
                case 28: goto L25;
                case 29: goto L23;
                case 30: goto L21;
                case 31: goto L19;
                default: goto L17;
            };
        L17:
            DexMerger$$ExternalSyntheticBUOutline0.m("Unexpected type: ", Integer.toHexString(r5.peek()));
            return;
        L19:
            writeTypeAndArg(31, r5.readBoolean() ? 1 : 0);
            return;
        L21:
            r5.readNull();
            writeTypeAndArg(30, 0);
            return;
        L23:
            writeTypeAndArg(29, 0);
            transformAnnotation(r5);
            return;
        L25:
            writeTypeAndArg(28, 0);
            transformArray(r5);
            return;
        L27:
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 27, this.this$0.adjustField(r5.readEnum()));
            return;
        L29:
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 26, this.this$0.adjustMethod(r5.readMethod()));
            return;
        L31:
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 25, this.this$0.adjustField(r5.readField()));
            return;
        L33:
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 24, this.this$0.adjustType(r5.readType()));
            return;
        L35:
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 23, this.this$0.adjustString(r5.readString()));
            return;
        L37:
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 22, this.this$0.adjustMethodHandle(r5.readMethodHandle()));
            return;
        L39:
            EncodedValueCodec.writeUnsignedIntegralValue(this.out, 21, this.this$0.adjustProto(r5.readMethodType()));
            return;
        L41:
            EncodedValueCodec.writeRightZeroExtendedValue(this.out, 17, Double.doubleToLongBits(r5.readDouble()));
        }
    }

    public IndexMap(Dex r3, TableOfContents r4) {
        this.target = r3;
        this.stringIds = new int[r4.stringIds.size];
        this.typeIds = new short[r4.typeIds.size];
        this.protoIds = new short[r4.protoIds.size];
        this.fieldIds = new short[r4.fieldIds.size];
        this.methodIds = new short[r4.methodIds.size];
        this.callSiteIds = new int[r4.callSiteIds.size];
        this.methodHandleIds = new HashMap();
        HashMap<Integer, Integer> r32 = new HashMap();
        this.typeListOffsets = r32;
        this.annotationOffsets = new HashMap();
        HashMap<Integer, Integer> r42 = new HashMap();
        this.annotationSetOffsets = r42;
        this.annotationSetRefListOffsets = new HashMap();
        HashMap<Integer, Integer> r0 = new HashMap();
        this.annotationDirectoryOffsets = r0;
        HashMap<Integer, Integer> r1 = new HashMap();
        this.encodedArrayValueOffset = r1;
        r32.put(0, 0);
        r42.put(0, 0);
        r0.put(0, 0);
        r1.put(0, 0);
    }

    public Annotation adjust(Annotation r4) {
        ByteArrayAnnotatedOutput r0 = new ByteArrayAnnotatedOutput(32);
        EncodedValueTransformer.access$100(new EncodedValueTransformer(this, r0), r4.getReader());
        return new Annotation(this.target, r4.getVisibility(), new EncodedValue(r0.toByteArray()));
    }

    public CallSiteId adjust(CallSiteId r3) {
        return new CallSiteId(this.target, adjustEncodedArray(r3.getCallSiteOffset()));
    }

    public ClassDef adjust(ClassDef r12) {
        return new ClassDef(this.target, r12.getOffset(), adjustType(r12.getTypeIndex()), r12.getAccessFlags(), adjustType(r12.getSupertypeIndex()), adjustTypeListOffset(r12.getInterfacesOffset()), r12.getSourceFileIndex(), r12.getAnnotationsOffset(), r12.getClassDataOffset(), r12.getStaticValuesOffset());
    }

    public FieldId adjust(FieldId r5) {
        return new FieldId(this.target, adjustType(r5.getDeclaringClassIndex()), adjustType(r5.getTypeIndex()), adjustString(r5.getNameIndex()));
    }

    public MethodHandle adjust(MethodHandle r7) {
        Dex r1 = this.target;
        MethodHandle.MethodHandleType r2 = r7.getMethodHandleType();
        int r3 = r7.getUnused1();
        if (r7.getMethodHandleType().isField() == false) goto L6;
        int r6 = adjustField(r7.getFieldOrMethodId());
    L5:
        int r4 = r6;
        return new MethodHandle(r1, r2, r3, r4, r7.getUnused2());
    L6:
        r6 = adjustMethod(r7.getFieldOrMethodId());
        goto L5
    }

    public MethodId adjust(MethodId r5) {
        return new MethodId(this.target, adjustType(r5.getDeclaringClassIndex()), adjustProto(r5.getProtoIndex()), adjustString(r5.getNameIndex()));
    }

    public ProtoId adjust(ProtoId r5) {
        return new ProtoId(this.target, adjustString(r5.getShortyIndex()), adjustType(r5.getReturnTypeIndex()), adjustTypeListOffset(r5.getParametersOffset()));
    }

    public SortableType adjust(SortableType r4) {
        return new SortableType(r4.getDex(), r4.getIndexMap(), adjust(r4.getClassDef()));
    }

    public int adjustAnnotation(int r1) {
        return this.annotationOffsets.get(Integer.valueOf(r1)).intValue();
    }

    public int adjustAnnotationDirectory(int r1) {
        return this.annotationDirectoryOffsets.get(Integer.valueOf(r1)).intValue();
    }

    public int adjustAnnotationSet(int r1) {
        return this.annotationSetOffsets.get(Integer.valueOf(r1)).intValue();
    }

    public int adjustAnnotationSetRefList(int r1) {
        return this.annotationSetRefListOffsets.get(Integer.valueOf(r1)).intValue();
    }

    public int adjustCallSite(int r1) {
        return this.callSiteIds[r1];
    }

    public int adjustEncodedArray(int r1) {
        return this.encodedArrayValueOffset.get(Integer.valueOf(r1)).intValue();
    }

    public EncodedValue adjustEncodedArray(EncodedValue r4) {
        ByteArrayAnnotatedOutput r0 = new ByteArrayAnnotatedOutput(32);
        EncodedValueTransformer.access$000(new EncodedValueTransformer(this, r0), new EncodedValueReader(r4, 28));
        return new EncodedValue(r0.toByteArray());
    }

    public EncodedValue adjustEncodedValue(EncodedValue r3) {
        ByteArrayAnnotatedOutput r0 = new ByteArrayAnnotatedOutput(32);
        new EncodedValueTransformer(this, r0).transform(new EncodedValueReader(r3));
        return new EncodedValue(r0.toByteArray());
    }

    public int adjustField(int r1) {
        return this.fieldIds[r1] & UShort.MAX_VALUE;
    }

    public int adjustMethod(int r1) {
        return this.methodIds[r1] & UShort.MAX_VALUE;
    }

    public int adjustMethodHandle(int r1) {
        return this.methodHandleIds.get(Integer.valueOf(r1)).intValue();
    }

    public int adjustProto(int r1) {
        return this.protoIds[r1] & UShort.MAX_VALUE;
    }

    public int adjustString(int r2) {
        if (r2 != (-1)) goto L6;
        return -1;
    L6:
        return this.stringIds[r2];
    }

    public int adjustType(int r2) {
        if (r2 != (-1)) goto L6;
        return -1;
    L6:
        return this.typeIds[r2] & UShort.MAX_VALUE;
    }

    public TypeList adjustTypeList(TypeList r3) {
        if (r3 != TypeList.EMPTY) goto L5;
        return r3;
    L5:
        short[] r32 = (short[]) r3.getTypes().clone();
        int r0 = 0;
    L7:
        if (r0 >= r32.length) goto L10;
        r32[r0] = (short) adjustType(r32[r0]);
        r0 = r0 + 1;
        goto L7
    L10:
        return new TypeList(this.target, r32);
    }

    public int adjustTypeListOffset(int r1) {
        return this.typeListOffsets.get(Integer.valueOf(r1)).intValue();
    }

    public void putAnnotationDirectoryOffset(int r1, int r2) {
        if (r1 <= 0) goto L6;
        if (r2 <= 0) goto L6;
        this.annotationDirectoryOffsets.put(Integer.valueOf(r1), Integer.valueOf(r2));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public void putAnnotationOffset(int r1, int r2) {
        if (r1 <= 0) goto L6;
        if (r2 <= 0) goto L6;
        this.annotationOffsets.put(Integer.valueOf(r1), Integer.valueOf(r2));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public void putAnnotationSetOffset(int r1, int r2) {
        if (r1 <= 0) goto L6;
        if (r2 <= 0) goto L6;
        this.annotationSetOffsets.put(Integer.valueOf(r1), Integer.valueOf(r2));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public void putAnnotationSetRefListOffset(int r1, int r2) {
        if (r1 <= 0) goto L6;
        if (r2 <= 0) goto L6;
        this.annotationSetRefListOffsets.put(Integer.valueOf(r1), Integer.valueOf(r2));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public void putEncodedArrayValueOffset(int r1, int r2) {
        if (r1 <= 0) goto L6;
        if (r2 <= 0) goto L6;
        this.encodedArrayValueOffset.put(Integer.valueOf(r1), Integer.valueOf(r2));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public void putTypeListOffset(int r1, int r2) {
        if (r1 <= 0) goto L6;
        if (r2 <= 0) goto L6;
        this.typeListOffsets.put(Integer.valueOf(r1), Integer.valueOf(r2));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }
}
