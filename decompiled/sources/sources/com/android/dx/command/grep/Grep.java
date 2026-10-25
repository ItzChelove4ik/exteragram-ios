package com.android.dx.command.grep;

import com.android.dex.ClassData;
import com.android.dex.ClassDef;
import com.android.dex.Dex;
import com.android.dex.EncodedValueReader;
import com.android.dx.io.CodeReader;
import com.android.dx.io.instructions.DecodedInstruction;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final class Grep {
    private final CodeReader codeReader;
    private int count;
    private ClassDef currentClass;
    private ClassData.Method currentMethod;
    private final Dex dex;
    private final PrintWriter out;
    private final Set<Integer> stringIds;

    /* renamed from: com.android.dx.command.grep.Grep$1, reason: invalid class name */
    public class AnonymousClass1 implements CodeReader.Visitor {
        final /* synthetic */ Grep this$0;

        public AnonymousClass1(Grep r1) {
            this.this$0 = r1;
        }

        @Override // com.android.dx.io.CodeReader.Visitor
        public void visit(DecodedInstruction[] r1, DecodedInstruction r2) {
            Grep.access$000(this.this$0, r2.getIndex());
        }
    }

    public Grep(Dex r3, Pattern r4, PrintWriter r5) {
        CodeReader r0 = new CodeReader();
        this.codeReader = r0;
        this.count = 0;
        this.dex = r3;
        this.out = r5;
        this.stringIds = getStringIds(r3, r4);
        r0.setStringVisitor(new AnonymousClass1(this));
    }

    public static /* synthetic */ void access$000(Grep r0, int r1) {
        r0.encounterString(r1);
    }

    private void encounterString(int r4) {
        if (this.stringIds.contains(Integer.valueOf(r4)) == false) goto L6;
        this.out.println(location() + " " + this.dex.strings().get(r4));
        this.count = this.count + 1;
        return;
    }

    private Set<Integer> getStringIds(Dex r3, Pattern r4) {
        HashSet r2 = new HashSet();
        Iterator<String> r32 = r3.strings().iterator();
        int r0 = 0;
    L4:
        if (r32.hasNext() == false) goto L9;
        if (r4.matcher(r32.next()).find() == false) goto L8;
        r2.add(Integer.valueOf(r0));
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r2;
    }

    private String location() {
        String r0 = this.dex.typeNames().get(this.currentClass.getTypeIndex());
        if (this.currentMethod != null) goto L5;
        return r0;
    L5:
        return r0 + "." + this.dex.strings().get(this.dex.methodIds().get(this.currentMethod.getMethodIndex()).getNameIndex());
    }

    private void readArray(EncodedValueReader r5) {
        int r0 = r5.readArray();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        int r2 = r5.peek();
        if (r2 != 23) goto L7;
        encounterString(r5.readString());
    L11:
        r1 = r1 + 1;
        goto L3
    L7:
        if (r2 != 28) goto L11;
        readArray(r5);
        goto L11
    }

    public int grep() {
        Iterator<ClassDef> r0 = this.dex.classDefs().iterator();
    L4:
        if (r0.hasNext() == false) goto L17;
        ClassDef r1 = r0.next();
        this.currentClass = r1;
        this.currentMethod = null;
        if (r1.getClassDataOffset() == 0) goto L4;
        ClassData r2 = this.dex.readClassData(r1);
        int r12 = r1.getStaticValuesOffset();
        if (r12 == 0) goto L11;
        readArray(new EncodedValueReader(this.dex.open(r12)));
    L11:
        ClassData.Method[] r13 = r2.allMethods();
        int r22 = r13.length;
        int r3 = 0;
    L12:
        if (r3 >= r22) goto L4;
        ClassData.Method r4 = r13[r3];
        this.currentMethod = r4;
        if (r4.getCodeOffset() == 0) goto L16;
        this.codeReader.visitAll(this.dex.readCode(r4).getInstructions());
    L16:
        r3 = r3 + 1;
        goto L12
    L17:
        this.currentClass = null;
        this.currentMethod = null;
        return this.count;
    }
}
