package com.android.dx.command.findusages;

import com.android.dex.ClassData;
import com.android.dex.ClassDef;
import com.android.dex.Dex;
import com.android.dex.FieldId;
import com.android.dex.MethodId;
import com.android.dx.io.CodeReader;
import com.android.dx.io.OpcodeInfo;
import com.android.dx.io.instructions.DecodedInstruction;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final class FindUsages {
    private final CodeReader codeReader;
    private ClassDef currentClass;
    private ClassData.Method currentMethod;
    private final Dex dex;
    private final Set<Integer> fieldIds;
    private final Set<Integer> methodIds;
    private final PrintWriter out;

    /* renamed from: com.android.dx.command.findusages.FindUsages$1, reason: invalid class name */
    public class AnonymousClass1 implements CodeReader.Visitor {
        final /* synthetic */ FindUsages this$0;
        final /* synthetic */ Dex val$dex;
        final /* synthetic */ PrintWriter val$out;

        public AnonymousClass1(FindUsages r1, PrintWriter r2, Dex r3) {
            this.this$0 = r1;
            this.val$out = r2;
            this.val$dex = r3;
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r4, DecodedInstruction r5) {
            int r42 = r5.getIndex();
            if (FindUsages.access$000(this.this$0).contains(Integer.valueOf(r42)) == false) goto L6;
            this.val$out.println(FindUsages.access$100(this.this$0) + ": field reference " + this.val$dex.fieldIds().get(r42) + " (" + OpcodeInfo.getName(r5.getOpcode()) + ")");
            return;
        }
    }

    /* renamed from: com.android.dx.command.findusages.FindUsages$2, reason: invalid class name */
    public class AnonymousClass2 implements CodeReader.Visitor {
        final /* synthetic */ FindUsages this$0;
        final /* synthetic */ Dex val$dex;
        final /* synthetic */ PrintWriter val$out;

        public AnonymousClass2(FindUsages r1, PrintWriter r2, Dex r3) {
            this.this$0 = r1;
            this.val$out = r2;
            this.val$dex = r3;
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r4, DecodedInstruction r5) {
            int r42 = r5.getIndex();
            if (FindUsages.access$200(this.this$0).contains(Integer.valueOf(r42)) == false) goto L6;
            this.val$out.println(FindUsages.access$100(this.this$0) + ": method reference " + this.val$dex.methodIds().get(r42) + " (" + OpcodeInfo.getName(r5.getOpcode()) + ")");
            return;
        }
    }

    public FindUsages(Dex r7, String r8, String r9, PrintWriter r10) {
        this.codeReader = new CodeReader();
        this.dex = r7;
        this.out = r10;
        HashSet r0 = new HashSet();
        HashSet r1 = new HashSet();
        Pattern r82 = Pattern.compile(r8);
        Pattern r92 = Pattern.compile(r9);
        List<String> r2 = r7.strings();
        int r3 = 0;
    L4:
        if (r3 >= r2.size()) goto L13;
        String r4 = r2.get(r3);
        if (r82.matcher(r4).matches() == false) goto L9;
        r0.add(Integer.valueOf(r3));
    L9:
        if (r92.matcher(r4).matches() == false) goto L11;
        r1.add(Integer.valueOf(r3));
    L11:
        r3 = r3 + 1;
        goto L4
    L13:
        if (r0.isEmpty() == false) goto L15;
    L26:
        this.fieldIds = null;
        this.methodIds = null;
        return;
    L15:
        if (r1.isEmpty() == true) goto L26;
        this.methodIds = new HashSet();
        this.fieldIds = new HashSet();
        Iterator r83 = r0.iterator();
    L19:
        if (r83.hasNext() == false) goto L24;
        Integer r93 = (Integer) r83.next();
        r93.intValue();
        int r94 = Collections.binarySearch(r7.typeIds(), r93);
        if (r94 < 0) goto L19;
        this.methodIds.addAll(getMethodIds(r7, r1, r94));
        this.fieldIds.addAll(getFieldIds(r7, r1, r94));
        goto L19
    L24:
        this.codeReader.setFieldVisitor(new AnonymousClass1(this, r10, r7));
        this.codeReader.setMethodVisitor(new AnonymousClass2(this, r10, r7));
    }

    public static /* synthetic */ Set access$000(FindUsages r0) {
        return r0.fieldIds;
    }

    public static /* synthetic */ String access$100(FindUsages r0) {
        return r0.location();
    }

    public static /* synthetic */ Set access$200(FindUsages r0) {
        return r0.methodIds;
    }

    private Set<Integer> findAssignableTypes(Dex r5, int r6) {
        HashSet r4 = new HashSet();
        r4.add(Integer.valueOf(r6));
        Iterator<ClassDef> r52 = r5.classDefs().iterator();
    L4:
        if (r52.hasNext() == false) goto L14;
        ClassDef r62 = r52.next();
        if (r4.contains(Integer.valueOf(r62.getSupertypeIndex())) == true) goto L7;
        short[] r0 = r62.getInterfaces();
        int r1 = r0.length;
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L4;
        if (r4.contains(Integer.valueOf(r0[r2])) == true) goto L12;
        r2 = r2 + 1;
        goto L9
    L12:
        r4.add(Integer.valueOf(r62.getTypeIndex()));
        goto L4
    L7:
        r4.add(Integer.valueOf(r62.getTypeIndex()));
        goto L4
    L14:
        return r4;
    }

    private Set<Integer> getFieldIds(Dex r4, Set<Integer> r5, int r6) {
        HashSet r3 = new HashSet();
        Iterator<FieldId> r42 = r4.fieldIds().iterator();
        int r0 = 0;
    L4:
        if (r42.hasNext() == false) goto L11;
        FieldId r1 = r42.next();
        if (r5.contains(Integer.valueOf(r1.getNameIndex())) == false) goto L10;
        if (r6 != r1.getDeclaringClassIndex()) goto L10;
        r3.add(Integer.valueOf(r0));
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return r3;
    }

    private Set<Integer> getMethodIds(Dex r4, Set<Integer> r5, int r6) {
        Set<Integer> r3 = findAssignableTypes(r4, r6);
        HashSet r62 = new HashSet();
        Iterator<MethodId> r42 = r4.methodIds().iterator();
        int r0 = 0;
    L4:
        if (r42.hasNext() == false) goto L11;
        MethodId r1 = r42.next();
        if (r5.contains(Integer.valueOf(r1.getNameIndex())) == false) goto L10;
        if (r3.contains(Integer.valueOf(r1.getDeclaringClassIndex())) == false) goto L10;
        r62.add(Integer.valueOf(r0));
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return r62;
    }

    private String location() {
        String r0 = this.dex.typeNames().get(this.currentClass.getTypeIndex());
        if (this.currentMethod != null) goto L5;
        return r0;
    L5:
        return r0 + "." + this.dex.strings().get(this.dex.methodIds().get(this.currentMethod.getMethodIndex()).getNameIndex());
    }

    public void findUsages() {
        if (this.fieldIds != null) goto L5;
        return;
    L5:
        if (this.methodIds == null) goto L41;
        Iterator<ClassDef> r0 = this.dex.classDefs().iterator();
    L9:
        if (r0.hasNext() == false) goto L28;
        ClassDef r1 = r0.next();
        this.currentClass = r1;
        this.currentMethod = null;
        if (r1.getClassDataOffset() == 0) goto L9;
        ClassData r12 = this.dex.readClassData(r1);
        ClassData.Field[] r2 = r12.allFields();
        int r3 = r2.length;
        int r4 = 0;
        int r5 = 0;
    L14:
        if (r5 >= r3) goto L19;
        int r6 = r2[r5].getFieldIndex();
        if (this.fieldIds.contains(Integer.valueOf(r6)) == false) goto L18;
        this.out.println(location() + " field declared " + this.dex.fieldIds().get(r6));
    L18:
        r5 = r5 + 1;
        goto L14
    L19:
        ClassData.Method[] r13 = r12.allMethods();
        int r22 = r13.length;
    L20:
        if (r4 >= r22) goto L9;
        ClassData.Method r32 = r13[r4];
        this.currentMethod = r32;
        int r52 = r32.getMethodIndex();
        if (this.methodIds.contains(Integer.valueOf(r52)) == false) goto L25;
        this.out.println(location() + " method declared " + this.dex.methodIds().get(r52));
    L25:
        if (r32.getCodeOffset() == 0) goto L27;
        this.codeReader.visitAll(this.dex.readCode(r32).getInstructions());
    L27:
        r4 = r4 + 1;
        goto L20
    L28:
        this.currentClass = null;
        this.currentMethod = null;
        return;
    }
}
