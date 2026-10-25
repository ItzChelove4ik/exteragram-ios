package com.android.dx.merge;

import com.android.dex.DexIndexOverflowException;
import com.android.dx.io.CodeReader;
import com.android.dx.io.instructions.DecodedInstruction;
import com.android.dx.io.instructions.ShortArrayCodeOutput;

/* loaded from: classes4.dex */
final class InstructionTransformer {
    private IndexMap indexMap;
    private int mappedAt;
    private DecodedInstruction[] mappedInstructions;
    private final CodeReader reader;

    /* renamed from: com.android.dx.merge.InstructionTransformer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class CallSiteVisitor implements CodeReader.Visitor {
        final /* synthetic */ InstructionTransformer this$0;

        private CallSiteVisitor(InstructionTransformer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ CallSiteVisitor(InstructionTransformer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r2, DecodedInstruction r3) {
            int r22 = r3.getIndex();
            int r23 = InstructionTransformer.access$900(this.this$0).adjustCallSite(r22);
            InstructionTransformer.access$700(this.this$0)[InstructionTransformer.access$808(this.this$0)] = r3.withIndex(r23);
        }
    }

    public class FieldVisitor implements CodeReader.Visitor {
        final /* synthetic */ InstructionTransformer this$0;

        private FieldVisitor(InstructionTransformer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ FieldVisitor(InstructionTransformer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r3, DecodedInstruction r4) {
            int r32 = r4.getIndex();
            int r33 = InstructionTransformer.access$900(this.this$0).adjustField(r32);
            if (r4.getOpcode() != 27) goto L5;
            boolean r0 = true;
        L6:
            InstructionTransformer.access$1000(r0, r33);
            InstructionTransformer.access$700(this.this$0)[InstructionTransformer.access$808(this.this$0)] = r4.withIndex(r33);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public class GenericVisitor implements CodeReader.Visitor {
        final /* synthetic */ InstructionTransformer this$0;

        private GenericVisitor(InstructionTransformer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ GenericVisitor(InstructionTransformer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r1, DecodedInstruction r2) {
            InstructionTransformer.access$700(this.this$0)[InstructionTransformer.access$808(this.this$0)] = r2;
        }
    }

    public class MethodAndProtoVisitor implements CodeReader.Visitor {
        final /* synthetic */ InstructionTransformer this$0;

        private MethodAndProtoVisitor(InstructionTransformer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MethodAndProtoVisitor(InstructionTransformer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r5, DecodedInstruction r6) {
            int r52 = r6.getIndex();
            short r0 = r6.getProtoIndex();
            InstructionTransformer.access$700(this.this$0)[InstructionTransformer.access$808(this.this$0)] = r6.withProtoIndex(InstructionTransformer.access$900(this.this$0).adjustMethod(r52), InstructionTransformer.access$900(this.this$0).adjustProto(r0));
        }
    }

    public class MethodVisitor implements CodeReader.Visitor {
        final /* synthetic */ InstructionTransformer this$0;

        private MethodVisitor(InstructionTransformer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MethodVisitor(InstructionTransformer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r3, DecodedInstruction r4) {
            int r32 = r4.getIndex();
            int r33 = InstructionTransformer.access$900(this.this$0).adjustMethod(r32);
            if (r4.getOpcode() != 27) goto L5;
            boolean r0 = true;
        L6:
            InstructionTransformer.access$1000(r0, r33);
            InstructionTransformer.access$700(this.this$0)[InstructionTransformer.access$808(this.this$0)] = r4.withIndex(r33);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public class StringVisitor implements CodeReader.Visitor {
        final /* synthetic */ InstructionTransformer this$0;

        private StringVisitor(InstructionTransformer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ StringVisitor(InstructionTransformer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r3, DecodedInstruction r4) {
            int r32 = r4.getIndex();
            int r33 = InstructionTransformer.access$900(this.this$0).adjustString(r32);
            if (r4.getOpcode() != 27) goto L5;
            boolean r0 = true;
        L6:
            InstructionTransformer.access$1000(r0, r33);
            InstructionTransformer.access$700(this.this$0)[InstructionTransformer.access$808(this.this$0)] = r4.withIndex(r33);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public class TypeVisitor implements CodeReader.Visitor {
        final /* synthetic */ InstructionTransformer this$0;

        private TypeVisitor(InstructionTransformer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TypeVisitor(InstructionTransformer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r3, DecodedInstruction r4) {
            int r32 = r4.getIndex();
            int r33 = InstructionTransformer.access$900(this.this$0).adjustType(r32);
            if (r4.getOpcode() != 27) goto L5;
            boolean r0 = true;
        L6:
            InstructionTransformer.access$1000(r0, r33);
            InstructionTransformer.access$700(this.this$0)[InstructionTransformer.access$808(this.this$0)] = r4.withIndex(r33);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public InstructionTransformer() {
        CodeReader r0 = new CodeReader();
        this.reader = r0;
        r0.setAllVisitors(new GenericVisitor(this, null));
        r0.setStringVisitor(new StringVisitor(this, null));
        r0.setTypeVisitor(new TypeVisitor(this, null));
        r0.setFieldVisitor(new FieldVisitor(this, null));
        r0.setMethodVisitor(new MethodVisitor(this, null));
        r0.setMethodAndProtoVisitor(new MethodAndProtoVisitor(this, null));
        r0.setCallSiteVisitor(new CallSiteVisitor(this, null));
    }

    public static /* synthetic */ void access$1000(boolean r0, int r1) {
        jumboCheck(r0, r1);
    }

    public static /* synthetic */ DecodedInstruction[] access$700(InstructionTransformer r0) {
        return r0.mappedInstructions;
    }

    public static /* synthetic */ int access$808(InstructionTransformer r2) {
        int r0 = r2.mappedAt;
        r2.mappedAt = r0 + 1;
        return r0;
    }

    public static /* synthetic */ IndexMap access$900(InstructionTransformer r0) {
        return r0.indexMap;
    }

    private static void jumboCheck(boolean r2, int r3) {
        if (r2 == false) goto L4;
        return;
    L4:
        if (r3 > 65535) goto L7;
        return;
    L7:
        throw new DexIndexOverflowException("Cannot merge new index " + r3 + " into a non-jumbo instruction!");
    }

    public short[] transform(IndexMap r4, short[] r5) {
        DecodedInstruction[] r52 = DecodedInstruction.decodeAll(r5);
        int r0 = r52.length;
        this.indexMap = r4;
        this.mappedInstructions = new DecodedInstruction[r0];
        int r42 = 0;
        this.mappedAt = 0;
        this.reader.visitAll(r52);
        ShortArrayCodeOutput r53 = new ShortArrayCodeOutput(r0);
        DecodedInstruction[] r02 = this.mappedInstructions;
        int r1 = r02.length;
    L3:
        if (r42 >= r1) goto L8;
        DecodedInstruction r2 = r02[r42];
        if (r2 == null) goto L7;
        r2.encode(r53);
    L7:
        r42 = r42 + 1;
        goto L3
    L8:
        this.indexMap = null;
        return r53.getArray();
    }
}
