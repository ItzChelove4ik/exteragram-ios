package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.file.MixedItemSection;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstBaseMethodRef;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstEnumRef;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.ByteArrayAnnotatedOutput;
import java.io.OutputStream;
import java.io.Writer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Adler32;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.math.MathProcessor$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class DexFile {
    private final MixedItemSection byteData;
    private final CallSiteIdsSection callSiteIds;
    private final MixedItemSection classData;
    private final ClassDefsSection classDefs;
    private final DexOptions dexOptions;
    private int dumpWidth;
    private final FieldIdsSection fieldIds;
    private int fileSize;
    private final HeaderSection header;
    private final MixedItemSection map;
    private final MethodHandlesSection methodHandles;
    private final MethodIdsSection methodIds;
    private final ProtoIdsSection protoIds;
    private final Section[] sections;
    private final MixedItemSection stringData;
    private final StringIdsSection stringIds;
    private final TypeIdsSection typeIds;
    private final MixedItemSection typeLists;
    private final MixedItemSection wordData;

    /* loaded from: classes4.dex */
    public static final class Storage {
        byte[] storage;

        public Storage(byte[] r1) {
            this.storage = r1;
        }

        public byte[] getStorage(int r5) {
            if (this.storage.length >= r5) goto L6;
            Logger.getAnonymousLogger().log(Level.FINER, "DexFile storage too small  " + this.storage.length + " vs " + r5);
            this.storage = new byte[r5];
        L6:
            return this.storage;
        }
    }

    public DexFile(DexOptions r31) {
        this.dexOptions = r31;
        HeaderSection r2 = new HeaderSection(this);
        this.header = r2;
        MixedItemSection.SortType r4 = MixedItemSection.SortType.NONE;
        MixedItemSection r3 = new MixedItemSection(null, this, 4, r4);
        this.typeLists = r3;
        MixedItemSection.SortType r8 = MixedItemSection.SortType.TYPE;
        MixedItemSection r7 = new MixedItemSection("word_data", this, 4, r8);
        this.wordData = r7;
        MixedItemSection r9 = new MixedItemSection("string_data", this, 1, MixedItemSection.SortType.INSTANCE);
        this.stringData = r9;
        MixedItemSection r10 = new MixedItemSection(null, this, 1, r4);
        this.classData = r10;
        MixedItemSection r11 = new MixedItemSection("byte_data", this, 1, r8);
        this.byteData = r11;
        StringIdsSection r82 = new StringIdsSection(this);
        this.stringIds = r82;
        TypeIdsSection r13 = new TypeIdsSection(this);
        this.typeIds = r13;
        ProtoIdsSection r14 = new ProtoIdsSection(this);
        this.protoIds = r14;
        FieldIdsSection r15 = new FieldIdsSection(this);
        this.fieldIds = r15;
        MethodIdsSection r12 = new MethodIdsSection(this);
        this.methodIds = r12;
        ClassDefsSection r5 = new ClassDefsSection(this);
        this.classDefs = r5;
        MixedItemSection r22 = new MixedItemSection("map", this, 4, r4);
        this.map = r22;
        if (r31.apiIsSupported(26) == false) goto L5;
        CallSiteIdsSection r1 = new CallSiteIdsSection(this);
        this.callSiteIds = r1;
        MethodHandlesSection r42 = new MethodHandlesSection(this);
        this.methodHandles = r42;
        this.sections = new Section[]{r2, r82, r13, r14, r15, r12, r5, r1, r42, r7, r3, r9, r11, r10, r22};
    L6:
        this.fileSize = -1;
        this.dumpWidth = 79;
        return;
    L5:
        this.callSiteIds = null;
        this.methodHandles = null;
        this.sections = new Section[]{r2, r82, r13, r14, r15, r12, r5, r7, r3, r9, r11, r10, r22};
        goto L6
    }

    private static void calcChecksum(byte[] r2, int r3) {
        Adler32 r0 = new Adler32();
        r0.update(r2, 12, r3 - 12);
        int r32 = (int) r0.getValue();
        r2[8] = (byte) r32;
        r2[9] = (byte) (r32 >> 8);
        r2[10] = (byte) (r32 >> 16);
        r2[11] = (byte) (r32 >> 24);
    }

    private static void calcSignature(byte[] r3, int r4) {
        MessageDigest r1 = MessageDigest.getInstance("SHA-1");     // Catch: NoSuchAlgorithmException -> L13
        r1.update(r3, 32, r4 - 32);
        int r32 = r1.digest(r3, 12, 20);     // Catch: DigestException -> L10
        if (r32 != 20) goto L9;
        return;
    L9:
        throw new RuntimeException("unexpected digest write: " + r32 + " bytes");     // Catch: DigestException -> L10
    L10:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    L13:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    private ByteArrayAnnotatedOutput toDex0(boolean r9, boolean r10, Storage r11) {
        this.classDefs.prepare();
        this.classData.prepare();
        this.wordData.prepare();
        if (this.dexOptions.apiIsSupported(26) == false) goto L5;
        this.callSiteIds.prepare();
    L5:
        this.byteData.prepare();
        if (this.dexOptions.apiIsSupported(26) == false) goto L8;
        this.methodHandles.prepare();
    L8:
        this.methodIds.prepare();
        this.fieldIds.prepare();
        this.protoIds.prepare();
        this.typeLists.prepare();
        this.typeIds.prepare();
        this.stringIds.prepare();
        this.stringData.prepare();
        this.header.prepare();
        int r0 = this.sections.length;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L10:
        if (r2 >= r0) goto L35;
        Section r6 = this.sections[r2];
        if (r6 == this.callSiteIds) goto L16;
        if (r6 == this.methodHandles) goto L16;
    L18:
        int r7 = r6.setFileOffset(r3);
        if (r7 < r3) goto L33;
        MixedItemSection r32 = this.map;     // Catch: RuntimeException -> L23
        if (r6 != r32) goto L26;
        MapItem.addMap(this.sections, r32);     // Catch: RuntimeException -> L23
        this.map.prepare();     // Catch: RuntimeException -> L23
    L26:
        if ((r6 instanceof MixedItemSection) == false) goto L29;
        ((MixedItemSection) r6).placeItems();     // Catch: RuntimeException -> L23
    L29:
        r3 = r6.writeSize() + r7;
    L30:
        r2 = r2 + 1;
    L23:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while writing section " + r2);
    L33:
        MathProcessor$$ExternalSyntheticBUOutline0.m("bogus placement for section ", r2);
        return null;
    L16:
        if (r6.items().isEmpty() == false) goto L18;
    L35:
        this.fileSize = r3;
        if (r11 != null) goto L38;
        byte[] r112 = new byte[r3];
    L39:
        ByteArrayAnnotatedOutput r22 = new ByteArrayAnnotatedOutput(r112);
        if (r9 == false) goto L42;
        r22.enableAnnotations(this.dumpWidth, r10);
    L42:
        if (r1 >= r0) goto L66;
        Section r102 = this.sections[r1];     // Catch: RuntimeException -> L48
        if (r102 == this.callSiteIds) goto L51;
        if (r102 == this.methodHandles) goto L51;
    L53:
        int r33 = r102.getFileOffset() - r22.getCursor();     // Catch: RuntimeException -> L48
        if (r33 < 0) goto L58;
        r22.writeZeroes(r33);     // Catch: RuntimeException -> L48
        r102.writeTo(r22);     // Catch: RuntimeException -> L48
    L56:
        r1 = r1 + 1;     // Catch: RuntimeException -> L48
        goto L42
    L58:
        throw new ExceptionWithContext("excess write of " + (-r33));     // Catch: RuntimeException -> L48
    L51:
        if (r102.items().isEmpty() == false) goto L53;
    L48:
        e = move-exception;
        if ((e instanceof ExceptionWithContext) == false) goto L62;
        ExceptionWithContext r8 = (ExceptionWithContext) e;
    L63:
        r8.addContext("...while writing section " + r1);
        throw r8;
    L62:
        r8 = new ExceptionWithContext(e);
        goto L63
    L66:
        if (r22.getCursor() != this.fileSize) goto L71;
        calcSignature(r112, r22.getCursor());
        calcChecksum(r112, r22.getCursor());
        if (r9 == false) goto L70;
        this.wordData.writeIndexAnnotation(r22, ItemType.TYPE_CODE_ITEM, "\nmethod code index:\n\n");
        getStatistics().writeAnnotation(r22);
        r22.finishAnnotating();
    L70:
        return r22;
    L71:
        GlShader$$ExternalSyntheticBUOutline1.m("foreshortened write");
        return null;
    L38:
        r112 = r11.getStorage(r3);
        goto L39
    }

    public void add(ClassDefItem r1) {
        this.classDefs.add(r1);
    }

    public IndexedItem findItemOrNull(Constant r2) {
        if ((r2 instanceof CstString) == false) goto L7;
        return this.stringIds.get(r2);
    L7:
        if ((r2 instanceof CstType) == false) goto L11;
        return this.typeIds.get(r2);
    L11:
        if ((r2 instanceof CstBaseMethodRef) == false) goto L15;
        return this.methodIds.get(r2);
    L15:
        if ((r2 instanceof CstFieldRef) == false) goto L19;
        return this.fieldIds.get(r2);
    L19:
        if ((r2 instanceof CstEnumRef) == false) goto L23;
        return this.fieldIds.intern(((CstEnumRef) r2).getFieldRef());
    L23:
        if ((r2 instanceof CstProtoRef) == false) goto L27;
        return this.protoIds.get(r2);
    L27:
        if ((r2 instanceof CstMethodHandle) == false) goto L31;
        return this.methodHandles.get(r2);
    L31:
        if ((r2 instanceof CstCallSiteRef) == true) goto L33;
        return null;
    L33:
        return this.callSiteIds.get(r2);
    }

    public MixedItemSection getByteData() {
        return this.byteData;
    }

    public CallSiteIdsSection getCallSiteIds() {
        return this.callSiteIds;
    }

    public MixedItemSection getClassData() {
        return this.classData;
    }

    public ClassDefsSection getClassDefs() {
        return this.classDefs;
    }

    public ClassDefItem getClassOrNull(String r2) {
        Type r22 = Type.internClassName(r2);     // Catch: IllegalArgumentException -> L4
        return (ClassDefItem) this.classDefs.get(new CstType(r22));
    L4:
        return null;
    }

    public DexOptions getDexOptions() {
        return this.dexOptions;
    }

    public FieldIdsSection getFieldIds() {
        return this.fieldIds;
    }

    public int getFileSize() {
        int r0 = this.fileSize;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("file size not yet known");
        return 0;
    }

    public Section getFirstDataSection() {
        return this.wordData;
    }

    public Section getLastDataSection() {
        return this.map;
    }

    public MixedItemSection getMap() {
        return this.map;
    }

    public MethodHandlesSection getMethodHandles() {
        return this.methodHandles;
    }

    public MethodIdsSection getMethodIds() {
        return this.methodIds;
    }

    public ProtoIdsSection getProtoIds() {
        return this.protoIds;
    }

    public Statistics getStatistics() {
        Statistics r0 = new Statistics();
        Section[] r4 = this.sections;
        int r1 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.addAll(r4[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    public MixedItemSection getStringData() {
        return this.stringData;
    }

    public StringIdsSection getStringIds() {
        return this.stringIds;
    }

    public TypeIdsSection getTypeIds() {
        return this.typeIds;
    }

    public MixedItemSection getTypeLists() {
        return this.typeLists;
    }

    public MixedItemSection getWordData() {
        return this.wordData;
    }

    public void internIfAppropriate(Constant r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return;
    L4:
        if ((r2 instanceof CstString) == false) goto L8;
        this.stringIds.intern((CstString) r2);
        return;
    L8:
        if ((r2 instanceof CstType) == false) goto L12;
        this.typeIds.intern((CstType) r2);
        return;
    L12:
        if ((r2 instanceof CstBaseMethodRef) == false) goto L16;
        this.methodIds.intern((CstBaseMethodRef) r2);
        return;
    L16:
        if ((r2 instanceof CstFieldRef) == false) goto L20;
        this.fieldIds.intern((CstFieldRef) r2);
        return;
    L20:
        if ((r2 instanceof CstEnumRef) == false) goto L24;
        this.fieldIds.intern(((CstEnumRef) r2).getFieldRef());
        return;
    L24:
        if ((r2 instanceof CstProtoRef) == false) goto L28;
        this.protoIds.intern(((CstProtoRef) r2).getPrototype());
        return;
    L28:
        if ((r2 instanceof CstMethodHandle) == false) goto L33;
        this.methodHandles.intern((CstMethodHandle) r2);
        return;
    }

    public boolean isEmpty() {
        return this.classDefs.items().isEmpty();
    }

    public void setDumpWidth(int r2) {
        if (r2 < 40) goto L6;
        this.dumpWidth = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("dumpWidth < 40");
    }

    public byte[] toDex(Writer r3, boolean r4) {
        if (r3 == null) goto L4;
        boolean r0 = true;
    L5:
        ByteArrayAnnotatedOutput r2 = toDex0(r0, r4, null);
        if (r0 == false) goto L9;
        r2.writeAnnotationsTo(r3);
    L9:
        return r2.getArray();
    L4:
        r0 = false;
        goto L5
    }

    public ByteArrayAnnotatedOutput writeTo(Storage r2) {
        return toDex0(false, false, r2);
    }

    public void writeTo(OutputStream r2, Storage r3, Writer r4, boolean r5) {
        if (r4 == null) goto L4;
        boolean r0 = true;
    L5:
        ByteArrayAnnotatedOutput r1 = toDex0(r0, r5, r3);
        if (r2 == null) goto L8;
        r2.write(r1.getArray());
    L8:
        if (r0 == false) goto L11;
        r1.writeAnnotationsTo(r4);
        return;
    L11:
        return;
    L4:
        r0 = false;
        goto L5
    }

    public void writeTo(OutputStream r2, Writer r3, boolean r4) {
        writeTo(r2, null, r3, r4);
    }
}
