package com.android.dex;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.ClassData;
import com.android.dex.Code;
import com.android.dex.MethodHandle;
import com.android.dex.util.ByteInput;
import com.android.dex.util.ByteOutput;
import com.android.dex.util.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.zip.Adler32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.UShort;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class Dex {
    static final short[] EMPTY_SHORT_ARRAY = null;
    private ByteBuffer data;
    private final FieldIdTable fieldIds;
    private final MethodIdTable methodIds;
    private int nextSectionStart;
    private final ProtoIdTable protoIds;
    private final StringTable strings;
    private final TableOfContents tableOfContents;
    private final TypeIndexToDescriptorIndexTable typeIds;
    private final TypeIndexToDescriptorTable typeNames;

    /* renamed from: com.android.dex.Dex$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public final class ClassDefIterable implements Iterable<ClassDef> {
        final /* synthetic */ Dex this$0;

        private ClassDefIterable(Dex r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ClassDefIterable(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.lang.Iterable
        public Iterator<ClassDef> iterator() {
            if (Dex.access$1000(this.this$0).classDefs.exists() == true) goto L7;
            return Collections.EMPTY_SET.iterator();
        L7:
            return new ClassDefIterator(this.this$0, null);
        }
    }

    public final class ClassDefIterator implements Iterator<ClassDef> {
        private int count;
        private final Section in;
        final /* synthetic */ Dex this$0;

        private ClassDefIterator(Dex r2) {
            this.this$0 = r2;
            this.in = r2.open(Dex.access$1000(r2).classDefs.off);
            this.count = 0;
        }

        public /* synthetic */ ClassDefIterator(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.count >= Dex.access$1000(this.this$0).classDefs.size) goto L6;
            return true;
        L6:
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ClassDef next() {
            if (hasNext() == false) goto L6;
            this.count++;
            return this.in.readClassDef();
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ ClassDef next() {
            return next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final class FieldIdTable extends AbstractList<FieldId> implements RandomAccess {
        final /* synthetic */ Dex this$0;

        private FieldIdTable(Dex r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ FieldIdTable(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public FieldId get(int r2) {
            Dex.access$1100(r2, Dex.access$1000(this.this$0).fieldIds.size);
            Dex r1 = this.this$0;
            return r1.open(Dex.access$1000(r1).fieldIds.off + (r2 * 8)).readFieldId();
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object get(int r1) {
            return get(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.access$1000(this.this$0).fieldIds.size;
        }
    }

    public final class MethodIdTable extends AbstractList<MethodId> implements RandomAccess {
        final /* synthetic */ Dex this$0;

        private MethodIdTable(Dex r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MethodIdTable(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public MethodId get(int r2) {
            Dex.access$1100(r2, Dex.access$1000(this.this$0).methodIds.size);
            Dex r1 = this.this$0;
            return r1.open(Dex.access$1000(r1).methodIds.off + (r2 * 8)).readMethodId();
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object get(int r1) {
            return get(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.access$1000(this.this$0).methodIds.size;
        }
    }

    public final class ProtoIdTable extends AbstractList<ProtoId> implements RandomAccess {
        final /* synthetic */ Dex this$0;

        private ProtoIdTable(Dex r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ProtoIdTable(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public ProtoId get(int r2) {
            Dex.access$1100(r2, Dex.access$1000(this.this$0).protoIds.size);
            Dex r1 = this.this$0;
            return r1.open(Dex.access$1000(r1).protoIds.off + (r2 * 12)).readProtoId();
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object get(int r1) {
            return get(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.access$1000(this.this$0).protoIds.size;
        }
    }

    public final class Section implements ByteInput, ByteOutput {
        private final ByteBuffer data;
        private final int initialPosition;
        private final String name;
        final /* synthetic */ Dex this$0;

        private Section(Dex r1, String r2, ByteBuffer r3) {
            this.this$0 = r1;
            this.name = r2;
            this.data = r3;
            this.initialPosition = r3.position();
        }

        public /* synthetic */ Section(Dex r1, String r2, ByteBuffer r3, AnonymousClass1 r4) {
            this(r1, r2, r3);
        }

        public static /* synthetic */ ClassData access$800(Section r0) {
            return r0.readClassData();
        }

        public static /* synthetic */ Code access$900(Section r0) {
            return r0.readCode();
        }

        private int findCatchHandlerIndex(Code.CatchHandler[] r3, int r4) {
            int r0 = 0;
        L4:
            if (r0 >= r3.length) goto L9;
            if (r3[r0].getOffset() == r4) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r0;
        L9:
            Segment$$ExternalSyntheticBUOutline0.m();
            return 0;
        }

        private byte[] getBytesFrom(int r3) {
            byte[] r0 = new byte[this.data.position() - r3];
            this.data.position(r3);
            this.data.get(r0);
            return r0;
        }

        private Code.CatchHandler readCatchHandler(int r7) {
            int r0 = readSleb128();
            int r1 = Math.abs(r0);
            int[] r2 = new int[r1];
            int[] r3 = new int[r1];
            int r4 = 0;
        L3:
            if (r4 >= r1) goto L5;
            r2[r4] = readUleb128();
            r3[r4] = readUleb128();
            r4 = r4 + 1;
            goto L3
        L5:
            if (r0 > 0) goto L7;
            int r6 = readUleb128();
        L9:
            return new Code.CatchHandler(r2, r3, r6, r7);
        L7:
            r6 = -1;
            goto L9
        }

        private Code.CatchHandler[] readCatchHandlers() {
            int r0 = this.data.position();
            int r1 = readUleb128();
            Code.CatchHandler[] r2 = new Code.CatchHandler[r1];
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            r2[r3] = readCatchHandler(this.data.position() - r0);
            r3 = r3 + 1;
            goto L3
        L5:
            return r2;
        }

        private ClassData readClassData() {
            int r0 = readUleb128();
            int r1 = readUleb128();
            int r2 = readUleb128();
            int r3 = readUleb128();
            return new ClassData(readFields(r0), readFields(r1), readMethods(r2), readMethods(r3));
        }

        private Code readCode() {
            int r1 = readUnsignedShort();
            int r2 = readUnsignedShort();
            int r3 = readUnsignedShort();
            int r0 = readUnsignedShort();
            int r4 = readInt();
            short[] r5 = readShortArray(readInt());
            if (r0 > 0) goto L5;
            Code.Try[] r02 = new Code.Try[0];
            Code.CatchHandler[] r8 = new Code.CatchHandler[0];
        L11:
            return new Code(r1, r2, r3, r4, r5, r02, r8);
        L5:
            if ((r5.length % 2) != 1) goto L7;
            readShort();
        L7:
            Section r6 = this.this$0.open(this.data.position());
            skip(r0 * 8);
            r8 = readCatchHandlers();
            r02 = r6.readTries(r0, r8);
            goto L11
        }

        private ClassData.Field[] readFields(int r6) {
            ClassData.Field[] r0 = new ClassData.Field[r6];
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r1 >= r6) goto L5;
            r2 = r2 + readUleb128();
            r0[r1] = new ClassData.Field(r2, readUleb128());
            r1 = r1 + 1;
            goto L3
        L5:
            return r0;
        }

        private ClassData.Method[] readMethods(int r7) {
            ClassData.Method[] r0 = new ClassData.Method[r7];
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r1 >= r7) goto L5;
            r2 = r2 + readUleb128();
            r0[r1] = new ClassData.Method(r2, readUleb128(), readUleb128());
            r1 = r1 + 1;
            goto L3
        L5:
            return r0;
        }

        private Code.Try[] readTries(int r7, Code.CatchHandler[] r8) {
            Code.Try[] r0 = new Code.Try[r7];
            int r1 = 0;
        L3:
            if (r1 >= r7) goto L5;
            r0[r1] = new Code.Try(readInt(), readUnsignedShort(), findCatchHandlerIndex(r8, readUnsignedShort()));
            r1 = r1 + 1;
            goto L3
        L5:
            return r0;
        }

        public void alignToFourBytes() {
            ByteBuffer r1 = this.data;
            r1.position((r1.position() + 3) & (-4));
        }

        public void alignToFourBytesWithZeroFill() {
        L3:
            if ((this.data.position() & 3) == 0) goto L5;
            this.data.put((byte) 0);
            goto L3
        }

        public void assertFourByteAligned() {
            if ((this.data.position() & 3) != 0) goto L5;
            return;
        L5:
            Segment$$ExternalSyntheticBUOutline1.m("Not four byte aligned!");
        }

        public int getPosition() {
            return this.data.position();
        }

        public Annotation readAnnotation() {
            byte r0 = readByte();
            int r1 = this.data.position();
            new EncodedValueReader(this, 29).skipValue();
            return new Annotation(this.this$0, r0, new EncodedValue(getBytesFrom(r1)));
        }

        @Override // com.android.dex.util.ByteInput
        public byte readByte() {
            return this.data.get();
        }

        public byte[] readByteArray(int r1) {
            byte[] r12 = new byte[r1];
            this.data.get(r12);
            return r12;
        }

        public CallSiteId readCallSiteId() {
            int r0 = readInt();
            return new CallSiteId(this.this$0, r0);
        }

        public ClassDef readClassDef() {
            int r2 = getPosition();
            int r3 = readInt();
            int r4 = readInt();
            int r5 = readInt();
            int r6 = readInt();
            int r7 = readInt();
            int r8 = readInt();
            int r9 = readInt();
            int r10 = readInt();
            return new ClassDef(this.this$0, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        }

        public EncodedValue readEncodedArray() {
            int r0 = this.data.position();
            new EncodedValueReader(this, 28).skipValue();
            return new EncodedValue(getBytesFrom(r0));
        }

        public FieldId readFieldId() {
            int r0 = readUnsignedShort();
            int r1 = readUnsignedShort();
            int r2 = readInt();
            return new FieldId(this.this$0, r0, r1, r2);
        }

        public int readInt() {
            return this.data.getInt();
        }

        public MethodHandle readMethodHandle() {
            MethodHandle.MethodHandleType r3 = MethodHandle.MethodHandleType.fromValue(readUnsignedShort());
            int r4 = readUnsignedShort();
            int r5 = readUnsignedShort();
            int r6 = readUnsignedShort();
            return new MethodHandle(this.this$0, r3, r4, r5, r6);
        }

        public MethodId readMethodId() {
            int r0 = readUnsignedShort();
            int r1 = readUnsignedShort();
            int r2 = readInt();
            return new MethodId(this.this$0, r0, r1, r2);
        }

        public ProtoId readProtoId() {
            int r0 = readInt();
            int r1 = readInt();
            int r2 = readInt();
            return new ProtoId(this.this$0, r0, r1, r2);
        }

        public short readShort() {
            return this.data.getShort();
        }

        public short[] readShortArray(int r4) {
            if (r4 == 0) goto L4;
            short[] r0 = new short[r4];
            int r1 = 0;
        L6:
            if (r1 >= r4) goto L8;
            r0[r1] = readShort();
            r1 = r1 + 1;
            goto L6
        L8:
            return r0;
        L4:
            return Dex.EMPTY_SHORT_ARRAY;
        }

        public int readSleb128() {
            return Leb128.readSignedLeb128(this);
        }

        public String readString() {
            int r1 = readInt();
            int r2 = this.data.position();
            int r3 = this.data.limit();
            this.data.position(r1);
            ByteBuffer r12 = this.data;
            r12.limit(r12.capacity());
            int r13 = readUleb128();     // Catch: Throwable -> L9 UTFDataFormatException -> L11
            String r4 = Mutf8.decode(this, new char[r13]);     // Catch: Throwable -> L9 UTFDataFormatException -> L11
            if (r4.length() != r13) goto L8;
            this.data.position(r2);
            this.data.limit(r3);
            return r4;
        L8:
            throw new DexException("Declared length " + r13 + " doesn't match decoded length of " + r4.length());     // Catch: Throwable -> L9 UTFDataFormatException -> L11
        L9:
            th = move-exception;
            this.data.position(r2);
            this.data.limit(r3);
            throw th;
        L11:
            e = move-exception;
            throw new DexException(e);     // Catch: Throwable -> L9
        }

        public TypeList readTypeList() {
            short[] r0 = readShortArray(readInt());
            alignToFourBytes();
            return new TypeList(this.this$0, r0);
        }

        public int readUleb128() {
            return Leb128.readUnsignedLeb128(this);
        }

        public int readUleb128p1() {
            return Leb128.readUnsignedLeb128(this) - 1;
        }

        public int readUnsignedShort() {
            return readShort() & UShort.MAX_VALUE;
        }

        public void skip(int r2) {
            if (r2 < 0) goto L5;
            ByteBuffer r1 = this.data;
            r1.position(r1.position() + r2);
            return;
        L5:
            Segment$$ExternalSyntheticBUOutline0.m();
        }

        public int used() {
            return this.data.position() - this.initialPosition;
        }

        public void write(byte[] r1) {
            this.data.put(r1);
        }

        public void write(short[] r4) {
            int r0 = r4.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            writeShort(r4[r1]);
            r1 = r1 + 1;
            goto L3
        }

        @Override // com.android.dex.util.ByteOutput
        public void writeByte(int r1) {
            this.data.put((byte) r1);
        }

        public void writeInt(int r1) {
            this.data.putInt(r1);
        }

        public void writeShort(short r1) {
            this.data.putShort(r1);
        }

        public void writeSleb128(int r4) {
            Leb128.writeSignedLeb128(this, r4);     // Catch: ArrayIndexOutOfBoundsException -> L4
            return;
        L5:
            throw new DexException("Section limit " + this.data.limit() + " exceeded by " + this.name);
        }

        public void writeStringData(String r2) {
            writeUleb128(r2.length());     // Catch: UTFDataFormatException -> L4
            write(Mutf8.encode(r2));     // Catch: UTFDataFormatException -> L4
            writeByte(0);     // Catch: UTFDataFormatException -> L4
            return;
        L4:
            AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        }

        public void writeTypeList(TypeList r4) {
            short[] r42 = r4.getTypes();
            writeInt(r42.length);
            int r0 = r42.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            writeShort(r42[r1]);
            r1 = r1 + 1;
            goto L3
        L5:
            alignToFourBytesWithZeroFill();
        }

        public void writeUleb128(int r4) {
            Leb128.writeUnsignedLeb128(this, r4);     // Catch: ArrayIndexOutOfBoundsException -> L4
            return;
        L5:
            throw new DexException("Section limit " + this.data.limit() + " exceeded by " + this.name);
        }

        public void writeUleb128p1(int r1) {
            writeUleb128(r1 + 1);
        }

        public void writeUnsignedShort(int r3) {
            short r0 = (short) r3;
            if (r3 != (65535 & r0)) goto L6;
            writeShort(r0);
            return;
        L6:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Expected an unsigned short: ", r3);
        }
    }

    public final class StringTable extends AbstractList<String> implements RandomAccess {
        final /* synthetic */ Dex this$0;

        private StringTable(Dex r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ StringTable(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object get(int r1) {
            return get(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public String get(int r2) {
            Dex.access$1100(r2, Dex.access$1000(this.this$0).stringIds.size);
            Dex r1 = this.this$0;
            return r1.open(Dex.access$1000(r1).stringIds.off + (r2 * 4)).readString();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.access$1000(this.this$0).stringIds.size;
        }
    }

    public final class TypeIndexToDescriptorIndexTable extends AbstractList<Integer> implements RandomAccess {
        final /* synthetic */ Dex this$0;

        private TypeIndexToDescriptorIndexTable(Dex r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TypeIndexToDescriptorIndexTable(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int r1) {
            return Integer.valueOf(this.this$0.descriptorIndexFromTypeIndex(r1));
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object get(int r1) {
            return get(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.access$1000(this.this$0).typeIds.size;
        }
    }

    public final class TypeIndexToDescriptorTable extends AbstractList<String> implements RandomAccess {
        final /* synthetic */ Dex this$0;

        private TypeIndexToDescriptorTable(Dex r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TypeIndexToDescriptorTable(Dex r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public /* bridge */ /* synthetic */ Object get(int r1) {
            return get(r1);
        }

        @Override // java.util.AbstractList, java.util.List
        public String get(int r2) {
            return Dex.access$1200(this.this$0).get(this.this$0.descriptorIndexFromTypeIndex(r2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return Dex.access$1000(this.this$0).typeIds.size;
        }
    }

    static {
        EMPTY_SHORT_ARRAY = new short[0];
    }

    public Dex(int r3) {
        this.tableOfContents = new TableOfContents();
        this.nextSectionStart = 0;
        this.strings = new StringTable(this, null);
        this.typeIds = new TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new TypeIndexToDescriptorTable(this, null);
        this.protoIds = new ProtoIdTable(this, null);
        this.fieldIds = new FieldIdTable(this, null);
        this.methodIds = new MethodIdTable(this, null);
        ByteBuffer r32 = ByteBuffer.wrap(new byte[r3]);
        this.data = r32;
        r32.order(ByteOrder.LITTLE_ENDIAN);
    }

    public Dex(File r4) {
        this.tableOfContents = new TableOfContents();
        this.nextSectionStart = 0;
        this.strings = new StringTable(this, null);
        this.typeIds = new TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new TypeIndexToDescriptorTable(this, null);
        this.protoIds = new ProtoIdTable(this, null);
        this.fieldIds = new FieldIdTable(this, null);
        this.methodIds = new MethodIdTable(this, null);
        if (FileUtils.hasArchiveSuffix(r4.getName()) == false) goto L24;
        ZipFile r0 = new ZipFile(r4);
        ZipEntry r2 = r0.getEntry("classes.dex");
        if (r2 == null) goto L21;
        InputStream r42 = r0.getInputStream(r2);
        loadFrom(r42);     // Catch: Throwable -> L12
        if (r42 == null) goto L10;
        r42.close();
    L10:
        r0.close();
        return;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        if (r42 != null) goto L43;
    L20:
        throw th;
    L43:
        r42.close();     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        th.addSuppressed(th);
        goto L20
    L21:
        Dex$$ExternalSyntheticBUOutline2.m("Expected classes.dex in ", r4);
        throw null;
    L24:
        if (r4.getName().endsWith(".dex") == false) goto L37;
        FileInputStream r02 = new FileInputStream(r4);
        loadFrom(r02);     // Catch: Throwable -> L29
        r02.close();
        return;
    L29:
        th = move-exception;
        throw th;     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        r02.close();     // Catch: Throwable -> L34
    L36:
        throw th;
    L34:
        th = move-exception;
        th.addSuppressed(th);
        goto L36
    L37:
        Dex$$ExternalSyntheticBUOutline2.m("unknown output extension: ", r4);
        throw null;
    }

    private Dex(ByteBuffer r4) {
        TableOfContents r0 = new TableOfContents();
        this.tableOfContents = r0;
        this.nextSectionStart = 0;
        this.strings = new StringTable(this, null);
        this.typeIds = new TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new TypeIndexToDescriptorTable(this, null);
        this.protoIds = new ProtoIdTable(this, null);
        this.fieldIds = new FieldIdTable(this, null);
        this.methodIds = new MethodIdTable(this, null);
        this.data = r4;
        r4.order(ByteOrder.LITTLE_ENDIAN);
        r0.readFrom(this);
    }

    public Dex(byte[] r1) {
        this(ByteBuffer.wrap(r1));
    }

    public static /* synthetic */ TableOfContents access$1000(Dex r0) {
        return r0.tableOfContents;
    }

    public static /* synthetic */ void access$1100(int r0, int r1) {
        checkBounds(r0, r1);
    }

    public static /* synthetic */ StringTable access$1200(Dex r0) {
        return r0.strings;
    }

    private static void checkBounds(int r2, int r3) {
        if (r2 < 0) goto L5;
        if (r2 >= r3) goto L5;
        return;
    L5:
        Dex$$ExternalSyntheticBUOutline0.m("index:", r2, ", length=", r3);
    }

    private void loadFrom(InputStream r5) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        byte[] r1 = new byte[8192];
    L3:
        int r2 = r5.read(r1);
        if (r2 == (-1)) goto L6;
        r0.write(r1, 0, r2);
        goto L3
    L6:
        ByteBuffer r52 = ByteBuffer.wrap(r0.toByteArray());
        this.data = r52;
        r52.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.readFrom(this);
    }

    public Section appendSection(int r4, String r5) {
        if ((r4 & 3) != 0) goto L6;
        int r0 = this.nextSectionStart + r4;
        ByteBuffer r42 = this.data.duplicate();
        r42.order(ByteOrder.LITTLE_ENDIAN);
        r42.position(this.nextSectionStart);
        r42.limit(r0);
        Section r2 = new Section(this, r5, r42, null);
        this.nextSectionStart = r0;
        return r2;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Not four byte aligned!");
        return null;
    }

    public Iterable<ClassDef> classDefs() {
        return new ClassDefIterable(this, null);
    }

    public int computeChecksum() {
        Adler32 r0 = new Adler32();
        byte[] r2 = new byte[8192];
        ByteBuffer r5 = this.data.duplicate();
        r5.limit(r5.capacity());
        r5.position(12);
    L4:
        if (r5.hasRemaining() == false) goto L7;
        int r3 = Math.min(8192, r5.remaining());
        r5.get(r2, 0, r3);
        r0.update(r2, 0, r3);
        goto L4
    L7:
        return (int) r0.getValue();
    }

    public byte[] computeSignature() {
        MessageDigest r0 = MessageDigest.getInstance("SHA-1");     // Catch: NoSuchAlgorithmException -> L9
        byte[] r2 = new byte[8192];
        ByteBuffer r5 = this.data.duplicate();
        r5.limit(r5.capacity());
        r5.position(32);
    L5:
        if (r5.hasRemaining() == false) goto L8;
        int r3 = Math.min(8192, r5.remaining());
        r5.get(r2, 0, r3);
        r0.update(r2, 0, r3);
        goto L5
    L8:
        return r0.digest();
    L9:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public int descriptorIndexFromTypeIndex(int r2) {
        checkBounds(r2, this.tableOfContents.typeIds.size);
        int r0 = this.tableOfContents.typeIds.off + (r2 * 4);
        return this.data.getInt(r0);
    }

    public List<FieldId> fieldIds() {
        return this.fieldIds;
    }

    public byte[] getBytes() {
        ByteBuffer r2 = this.data.duplicate();
        byte[] r0 = new byte[r2.capacity()];
        r2.position(0);
        r2.get(r0);
        return r0;
    }

    public int getLength() {
        return this.data.capacity();
    }

    public int getNextSectionStart() {
        return this.nextSectionStart;
    }

    public TableOfContents getTableOfContents() {
        return this.tableOfContents;
    }

    public List<MethodId> methodIds() {
        return this.methodIds;
    }

    public Section open(int r4) {
        if (r4 >= 0) goto L5;
    L8:
        Dex$$ExternalSyntheticBUOutline1.m("position=", r4, " length=", this.data.capacity());
        return null;
    L5:
        if (r4 >= this.data.capacity()) goto L8;
        ByteBuffer r1 = this.data.duplicate();
        r1.order(ByteOrder.LITTLE_ENDIAN);
        r1.position(r4);
        r1.limit(this.data.capacity());
        return new Section(this, "section", r1, null);
    }

    public List<ProtoId> protoIds() {
        return this.protoIds;
    }

    public ClassData readClassData(ClassDef r1) {
        int r12 = r1.getClassDataOffset();
        if (r12 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("offset == 0");
        return null;
    L5:
        return Section.access$800(open(r12));
    }

    public Code readCode(ClassData.Method r1) {
        int r12 = r1.getCodeOffset();
        if (r12 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("offset == 0");
        return null;
    L5:
        return Section.access$900(open(r12));
    }

    public TypeList readTypeList(int r1) {
        if (r1 != 0) goto L6;
        return TypeList.EMPTY;
    L6:
        return open(r1).readTypeList();
    }

    public List<String> strings() {
        return this.strings;
    }

    public List<Integer> typeIds() {
        return this.typeIds;
    }

    public List<String> typeNames() {
        return this.typeNames;
    }

    public void writeHashes() {
        open(12).write(computeSignature());
        open(8).writeInt(computeChecksum());
    }

    public void writeTo(File r2) {
        FileOutputStream r0 = new FileOutputStream(r2);
        writeTo(r0);     // Catch: Throwable -> L6
        r0.close();
        return;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L11
    L13:
        throw th;
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }

    public void writeTo(OutputStream r5) {
        byte[] r1 = new byte[8192];
        ByteBuffer r4 = this.data.duplicate();
        r4.clear();
    L4:
        if (r4.hasRemaining() == false) goto L6;
        int r2 = Math.min(8192, r4.remaining());
        r4.get(r1, 0, r2);
        r5.write(r1, 0, r2);
        goto L4
    }
}
