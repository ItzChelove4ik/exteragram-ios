package com.android.dx.merge;

import com.android.dex.Annotation;
import com.android.dex.CallSiteId;
import com.android.dex.ClassData;
import com.android.dex.ClassDef;
import com.android.dex.Code;
import com.android.dex.Dex;
import com.android.dex.FieldId;
import com.android.dex.MethodHandle;
import com.android.dex.MethodId;
import com.android.dex.ProtoId;
import com.android.dex.TableOfContents;
import com.android.dex.TypeList;
import com.android.dx.command.dexer.DxContext;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public final class DexMerger {
    private static final byte DBG_ADVANCE_LINE = 2;
    private static final byte DBG_ADVANCE_PC = 1;
    private static final byte DBG_END_LOCAL = 5;
    private static final byte DBG_END_SEQUENCE = 0;
    private static final byte DBG_RESTART_LOCAL = 6;
    private static final byte DBG_SET_EPILOGUE_BEGIN = 8;
    private static final byte DBG_SET_FILE = 9;
    private static final byte DBG_SET_PROLOGUE_END = 7;
    private static final byte DBG_START_LOCAL = 3;
    private static final byte DBG_START_LOCAL_EXTENDED = 4;
    private final Dex.Section annotationOut;
    private final Dex.Section annotationSetOut;
    private final Dex.Section annotationSetRefListOut;
    private final Dex.Section annotationsDirectoryOut;
    private final Dex.Section classDataOut;
    private final Dex.Section codeOut;
    private final CollisionPolicy collisionPolicy;
    private int compactWasteThreshold;
    private final TableOfContents contentsOut;
    private final DxContext context;
    private final Dex.Section debugInfoOut;
    private final Dex dexOut;
    private final Dex[] dexes;
    private final Dex.Section encodedArrayOut;
    private final Dex.Section headerOut;
    private final Dex.Section idsDefsOut;
    private final IndexMap[] indexMaps;
    private final InstructionTransformer instructionTransformer;
    private final Dex.Section mapListOut;
    private final Dex.Section stringDataOut;
    private final Dex.Section typeListOut;
    private final WriterSizes writerSizes;

    /* renamed from: com.android.dx.merge.DexMerger$1, reason: invalid class name */
    public class AnonymousClass1 extends IdMerger<String> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass1(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.stringIds;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public String read(Dex.Section r1, IndexMap r2, int r3) {
            return r1.readString();
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            r2.stringIds[r3] = r4;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((String) r1);
        }

        public void write(String r3) {
            DexMerger.access$1500(this.this$0).stringDatas.size++;
            DexMerger.access$1700(this.this$0).writeInt(DexMerger.access$1600(this.this$0).getPosition());
            DexMerger.access$1600(this.this$0).writeStringData(r3);
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$2, reason: invalid class name */
    public class AnonymousClass2 extends IdMerger<Integer> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass2(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.typeIds;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public Integer read(Dex.Section r1, IndexMap r2, int r3) {
            return Integer.valueOf(r2.adjustString(r1.readInt()));
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            if (r4 >= 0) goto L4;
        L7:
            DexMerger$2$$ExternalSyntheticBUOutline0.m("type ID not in [0, 0xffff]: ", r4);
            return;
        L4:
            if (r4 > 65535) goto L7;
            r2.typeIds[r3] = (short) r4;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((Integer) r1);
        }

        public void write(Integer r1) {
            DexMerger.access$1700(this.this$0).writeInt(r1.intValue());
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$3, reason: invalid class name */
    public class AnonymousClass3 extends IdMerger<TypeList> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass3(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.typeLists;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TypeList read(Dex.Section r1, IndexMap r2, int r3) {
            return r2.adjustTypeList(r1.readTypeList());
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            r2.putTypeListOffset(r1, DexMerger.access$1800(this.this$0).getPosition());
        }

        public void write(TypeList r1) {
            DexMerger.access$1800(this.this$0).writeTypeList(r1);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((TypeList) r1);
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$4, reason: invalid class name */
    public class AnonymousClass4 extends IdMerger<ProtoId> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass4(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.protoIds;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public ProtoId read(Dex.Section r1, IndexMap r2, int r3) {
            return r2.adjust(r1.readProtoId());
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            if (r4 >= 0) goto L4;
        L7:
            DexMerger$2$$ExternalSyntheticBUOutline0.m("proto ID not in [0, 0xffff]: ", r4);
            return;
        L4:
            if (r4 > 65535) goto L7;
            r2.protoIds[r3] = (short) r4;
        }

        public void write(ProtoId r1) {
            r1.writeTo(DexMerger.access$1700(this.this$0));
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((ProtoId) r1);
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$5, reason: invalid class name */
    public class AnonymousClass5 extends IdMerger<CallSiteId> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass5(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.callSiteIds;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public CallSiteId read(Dex.Section r1, IndexMap r2, int r3) {
            return r2.adjust(r1.readCallSiteId());
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            r2.callSiteIds[r3] = r4;
        }

        public void write(CallSiteId r1) {
            r1.writeTo(DexMerger.access$1700(this.this$0));
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((CallSiteId) r1);
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$6, reason: invalid class name */
    public class AnonymousClass6 extends IdMerger<MethodHandle> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass6(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.methodHandles;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public MethodHandle read(Dex.Section r1, IndexMap r2, int r3) {
            return r2.adjust(r1.readMethodHandle());
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            r2.methodHandleIds.put(Integer.valueOf(r3), Integer.valueOf(r2.methodHandleIds.size()));
        }

        public void write(MethodHandle r1) {
            r1.writeTo(DexMerger.access$1700(this.this$0));
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((MethodHandle) r1);
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$7, reason: invalid class name */
    public class AnonymousClass7 extends IdMerger<FieldId> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass7(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.fieldIds;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public FieldId read(Dex.Section r1, IndexMap r2, int r3) {
            return r2.adjust(r1.readFieldId());
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            if (r4 >= 0) goto L4;
        L7:
            DexMerger$2$$ExternalSyntheticBUOutline0.m("field ID not in [0, 0xffff]: ", r4);
            return;
        L4:
            if (r4 > 65535) goto L7;
            r2.fieldIds[r3] = (short) r4;
        }

        public void write(FieldId r1) {
            r1.writeTo(DexMerger.access$1700(this.this$0));
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((FieldId) r1);
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$8, reason: invalid class name */
    public class AnonymousClass8 extends IdMerger<MethodId> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass8(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.methodIds;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public MethodId read(Dex.Section r1, IndexMap r2, int r3) {
            return r2.adjust(r1.readMethodId());
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            if (r4 >= 0) goto L4;
        L7:
            DexMerger$2$$ExternalSyntheticBUOutline0.m("method ID not in [0, 0xffff]: ", r4);
            return;
        L4:
            if (r4 > 65535) goto L7;
            r2.methodIds[r3] = (short) r4;
        }

        public void write(MethodId r1) {
            r1.writeTo(DexMerger.access$1700(this.this$0));
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((MethodId) r1);
        }
    }

    /* renamed from: com.android.dx.merge.DexMerger$9, reason: invalid class name */
    public class AnonymousClass9 extends IdMerger<Annotation> {
        final /* synthetic */ DexMerger this$0;

        public AnonymousClass9(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public TableOfContents.Section getSection(TableOfContents r1) {
            return r1.annotations;
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public Annotation read(Dex.Section r1, IndexMap r2, int r3) {
            return r2.adjust(r1.readAnnotation());
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ Comparable read(Dex.Section r1, IndexMap r2, int r3) {
            return read(r1, r2, r3);
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public void updateIndex(int r1, IndexMap r2, int r3, int r4) {
            r2.putAnnotationOffset(r1, DexMerger.access$1900(this.this$0).getPosition());
        }

        public void write(Annotation r1) {
            r1.writeTo(DexMerger.access$1900(this.this$0));
        }

        @Override // com.android.dx.merge.DexMerger.IdMerger
        public /* bridge */ /* synthetic */ void write(Comparable r1) {
            write((Annotation) r1);
        }
    }

    public abstract class IdMerger<T extends Comparable<T>> {
        private final Dex.Section out;
        final /* synthetic */ DexMerger this$0;

        public class UnsortedValue implements Comparable<IdMerger<T>.UnsortedValue> {
            final int index;
            final IndexMap indexMap;
            final int offset;
            final Dex source;
            final /* synthetic */ IdMerger this$1;
            final T value;

            public UnsortedValue(IdMerger r1, Dex r2, IndexMap r3, T r4, int r5, int r6) {
                this.this$1 = r1;
                this.source = r2;
                this.indexMap = r3;
                this.value = r4;
                this.index = r5;
                this.offset = r6;
            }

            public int compareTo(IdMerger<T>.UnsortedValue r1) {
                return this.value.compareTo(r1.value);
            }

            @Override // java.lang.Comparable
            public /* bridge */ /* synthetic */ int compareTo(Object r1) {
                return compareTo((UnsortedValue) r1);
            }
        }

        public IdMerger(DexMerger r1, Dex.Section r2) {
            this.this$0 = r1;
            this.out = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private int readIntoMap(Dex.Section r2, TableOfContents.Section r3, IndexMap r4, int r5, TreeMap<T, List<Integer>> r6, int r7) {
            if (r2 == null) goto L4;
            int r0 = r2.getPosition();
        L6:
            if (r5 >= r3.size) goto L11;
            Comparable r1 = read(r2, r4, r5);
            List r22 = (List) r6.get(r1);
            if (r22 != null) goto L10;
            r22 = new ArrayList();
            r6.put(r1, r22);
        L10:
            r22.add(Integer.valueOf(r7));
        L11:
            return r0;
        L4:
            r0 = -1;
            goto L6
        }

        private List<IdMerger<T>.UnsortedValue> readUnsortedValues(Dex r12, IndexMap r13) {
            TableOfContents.Section r0 = getSection(r12.getTableOfContents());
            if (r0.exists() == false) goto L5;
            ArrayList r1 = new ArrayList();
            Dex.Section r2 = r12.open(r0.off);
            int r9 = 0;
        L8:
            if (r9 >= r0.size) goto L10;
            r1.add(new UnsortedValue(this, r12, r13, read(r2, r13, 0), r9, r2.getPosition()));
            r9 = r9 + 1;
            goto L8
        L10:
            return r1;
        L5:
            return Collections.EMPTY_LIST;
        }

        public abstract TableOfContents.Section getSection(TableOfContents r1);

        public final void mergeSorted() {
            TableOfContents.Section[] r0 = new TableOfContents.Section[DexMerger.access$1300(this.this$0).length];
            Dex.Section[] r1 = new Dex.Section[DexMerger.access$1300(this.this$0).length];
            int[] r2 = new int[DexMerger.access$1300(this.this$0).length];
            int[] r3 = new int[DexMerger.access$1300(this.this$0).length];
            TreeMap<T, List<Integer>> r9 = new TreeMap();
            int r11 = 0;
            int r10 = 0;
        L4:
            if (r10 >= DexMerger.access$1300(this.this$0).length) goto L11;
            TableOfContents.Section r4 = this.getSection(DexMerger.access$1300(this.this$0)[r10].getTableOfContents());
            r0[r10] = r4;
            if (r4.exists() == false) goto L9;
            Dex.Section r42 = DexMerger.access$1300(this.this$0)[r10].open(r0[r10].off);
        L8:
            Dex.Section r5 = r42;
            r1[r10] = r5;
            IdMerger<T> r43 = this;
            r2[r10] = r43.readIntoMap(r5, r0[r10], DexMerger.access$1400(this.this$0)[r10], r3[r10], r9, r10);
            r10 = r10 + 1;
            IdMerger<T> this = r43;
            goto L4
        L9:
            r42 = null;
            goto L8
        L11:
            IdMerger<T> r44 = this;
            boolean r15 = r9.isEmpty();
            DexMerger r52 = r44.this$0;
            if (r15 == false) goto L15;
            r44.getSection(DexMerger.access$1500(r52)).off = 0;
            r44.getSection(DexMerger.access$1500(r44.this$0)).size = 0;
            return;
        L15:
            r44.getSection(DexMerger.access$1500(r52)).off = r44.out.getPosition();
        L17:
            if (r9.isEmpty() == true) goto L23;
            Map.Entry<T, List<Integer>> r152 = r9.pollFirstEntry();
            Iterator<Integer> r12 = r152.getValue().iterator();
        L20:
            if (r12.hasNext() == false) goto L22;
            Integer r53 = r12.next();
            int r6 = r2[r53.intValue()];
            IndexMap r7 = DexMerger.access$1400(r44.this$0)[r53.intValue()];
            int r8 = r53.intValue();
            int r102 = r3[r8];
            r3[r8] = r102 + 1;
            r44.updateIndex(r6, r7, r102, r11);
            r2[r53.intValue()] = r44.readIntoMap(r1[r53.intValue()], r0[r53.intValue()], DexMerger.access$1400(r44.this$0)[r53.intValue()], r3[r53.intValue()], r9, r53.intValue());
            goto L20
        L22:
            r44.write(r152.getKey());
            r11 = r11 + 1;
            goto L17
        L23:
            r44.getSection(DexMerger.access$1500(r44.this$0)).size = r11;
        }

        public final void mergeUnsorted() {
            getSection(DexMerger.access$1500(this.this$0)).off = this.out.getPosition();
            ArrayList r0 = new ArrayList();
            int r1 = 0;
            int r2 = 0;
        L4:
            if (r2 >= DexMerger.access$1300(this.this$0).length) goto L7;
            r0.addAll(readUnsortedValues(DexMerger.access$1300(this.this$0)[r2], DexMerger.access$1400(this.this$0)[r2]));
            r2 = r2 + 1;
            goto L4
        L7:
            if (r0.isEmpty() == false) goto L10;
            getSection(DexMerger.access$1500(this.this$0)).off = 0;
            getSection(DexMerger.access$1500(this.this$0)).size = 0;
            return;
        L10:
            Collections.sort(r0);
            int r22 = 0;
        L12:
            if (r1 >= r0.size()) goto L20;
            int r3 = r1 + 1;
            UnsortedValue r12 = (UnsortedValue) r0.get(r1);
            int r7 = r22 - 1;
            updateIndex(r12.offset, r12.indexMap, r12.index, r7);
        L15:
            if (r3 >= r0.size()) goto L19;
            if (r12.compareTo((UnsortedValue) r0.get(r3)) != 0) goto L19;
            int r4 = r3 + 1;
            UnsortedValue r32 = (UnsortedValue) r0.get(r3);
            updateIndex(r32.offset, r32.indexMap, r32.index, r7);
            r3 = r4;
        L19:
            write(r12.value);
            r22 = r22 + 1;
            r1 = r3;
            goto L12
        L20:
            getSection(DexMerger.access$1500(this.this$0)).size = r22;
        }

        public abstract T read(Dex.Section r1, IndexMap r2, int r3);

        public abstract void updateIndex(int r1, IndexMap r2, int r3, int r4);

        public abstract void write(T r1);
    }

    public static class WriterSizes {
        private int annotation;
        private int annotationsDirectory;
        private int annotationsSet;
        private int annotationsSetRefList;
        private int classData;
        private int code;
        private int debugInfo;
        private int encodedArray;
        private int header;
        private int idsDefs;
        private int mapList;
        private int stringData;
        private int typeList;

        public WriterSizes(DexMerger r2) {
            this.header = 112;
            this.header = DexMerger.access$2000(r2).used();
            this.idsDefs = DexMerger.access$1700(r2).used();
            this.mapList = DexMerger.access$2100(r2).used();
            this.typeList = DexMerger.access$1800(r2).used();
            this.classData = DexMerger.access$2200(r2).used();
            this.code = DexMerger.access$2300(r2).used();
            this.stringData = DexMerger.access$1600(r2).used();
            this.debugInfo = DexMerger.access$2400(r2).used();
            this.encodedArray = DexMerger.access$2500(r2).used();
            this.annotationsDirectory = DexMerger.access$2600(r2).used();
            this.annotationsSet = DexMerger.access$2700(r2).used();
            this.annotationsSetRefList = DexMerger.access$2800(r2).used();
            this.annotation = DexMerger.access$1900(r2).used();
            fourByteAlign();
        }

        public WriterSizes(Dex[] r4) {
            this.header = 112;
            int r1 = 0;
        L4:
            if (r1 >= r4.length) goto L6;
            plus(r4[r1].getTableOfContents(), false);
            r1 = r1 + 1;
            goto L4
        L6:
            fourByteAlign();
        }

        public static /* synthetic */ int access$000(WriterSizes r0) {
            return r0.header;
        }

        public static /* synthetic */ int access$100(WriterSizes r0) {
            return r0.idsDefs;
        }

        public static /* synthetic */ int access$1000(WriterSizes r0) {
            return r0.annotation;
        }

        public static /* synthetic */ int access$1100(WriterSizes r0) {
            return r0.encodedArray;
        }

        public static /* synthetic */ int access$1200(WriterSizes r0) {
            return r0.annotationsDirectory;
        }

        public static /* synthetic */ int access$200(WriterSizes r0) {
            return r0.mapList;
        }

        public static /* synthetic */ int access$300(WriterSizes r0) {
            return r0.typeList;
        }

        public static /* synthetic */ int access$400(WriterSizes r0) {
            return r0.annotationsSetRefList;
        }

        public static /* synthetic */ int access$500(WriterSizes r0) {
            return r0.annotationsSet;
        }

        public static /* synthetic */ int access$600(WriterSizes r0) {
            return r0.classData;
        }

        public static /* synthetic */ int access$700(WriterSizes r0) {
            return r0.code;
        }

        public static /* synthetic */ int access$800(WriterSizes r0) {
            return r0.stringData;
        }

        public static /* synthetic */ int access$900(WriterSizes r0) {
            return r0.debugInfo;
        }

        private static int fourByteAlign(int r0) {
            return (r0 + 3) & (-4);
        }

        private void fourByteAlign() {
            this.header = fourByteAlign(this.header);
            this.idsDefs = fourByteAlign(this.idsDefs);
            this.mapList = fourByteAlign(this.mapList);
            this.typeList = fourByteAlign(this.typeList);
            this.classData = fourByteAlign(this.classData);
            this.code = fourByteAlign(this.code);
            this.stringData = fourByteAlign(this.stringData);
            this.debugInfo = fourByteAlign(this.debugInfo);
            this.encodedArray = fourByteAlign(this.encodedArray);
            this.annotationsDirectory = fourByteAlign(this.annotationsDirectory);
            this.annotationsSet = fourByteAlign(this.annotationsSet);
            this.annotationsSetRefList = fourByteAlign(this.annotationsSetRefList);
            this.annotation = fourByteAlign(this.annotation);
        }

        private void plus(TableOfContents r6, boolean r7) {
            this.idsDefs += (((((r6.stringIds.size * 4) + (r6.typeIds.size * 4)) + (r6.protoIds.size * 12)) + (r6.fieldIds.size * 8)) + (r6.methodIds.size * 8)) + (r6.classDefs.size * 32);
            this.mapList = (r6.sections.length * 12) + 4;
            this.typeList += fourByteAlign(r6.typeLists.byteCount);
            this.stringData += r6.stringDatas.byteCount;
            this.annotationsDirectory += r6.annotationsDirectories.byteCount;
            this.annotationsSet += r6.annotationSets.byteCount;
            this.annotationsSetRefList += r6.annotationSetRefLists.byteCount;
            int r0 = this.code;
            if (r7 == false) goto L6;
            this.code = r0 + r6.codes.byteCount;
            this.classData += r6.classDatas.byteCount;
            this.encodedArray += r6.encodedArrays.byteCount;
            this.annotation += r6.annotations.byteCount;
            this.debugInfo += r6.debugInfos.byteCount;
            return;
        L6:
            this.code = r0 + ((int) Math.ceil(r6.codes.byteCount * 1.25d));
            this.classData += (int) Math.ceil(r6.classDatas.byteCount * 1.67d);
            this.encodedArray += r6.encodedArrays.byteCount * 2;
            this.annotation += (int) Math.ceil(r6.annotations.byteCount * 2);
            this.debugInfo += (r6.debugInfos.byteCount * 2) + 8;
        }

        public int size() {
            return (((((((((((this.header + this.idsDefs) + this.mapList) + this.typeList) + this.classData) + this.code) + this.stringData) + this.debugInfo) + this.encodedArray) + this.annotationsDirectory) + this.annotationsSet) + this.annotationsSetRefList) + this.annotation;
        }
    }

    public DexMerger(Dex[] r2, CollisionPolicy r3, DxContext r4) {
        this(r2, r3, r4, new WriterSizes(r2));
    }

    private DexMerger(Dex[] r4, CollisionPolicy r5, DxContext r6, WriterSizes r7) {
        this.compactWasteThreshold = 1048576;
        this.dexes = r4;
        this.collisionPolicy = r5;
        this.context = r6;
        this.writerSizes = r7;
        this.dexOut = new Dex(r7.size());
        this.indexMaps = new IndexMap[r4.length];
        int r52 = 0;
    L4:
        if (r52 >= r4.length) goto L6;
        this.indexMaps[r52] = new IndexMap(this.dexOut, r4[r52].getTableOfContents());
        r52 = r52 + 1;
        goto L4
    L6:
        this.instructionTransformer = new InstructionTransformer();
        this.headerOut = this.dexOut.appendSection(WriterSizes.access$000(r7), PluginsConstants.Settings.TYPE_HEADER);
        this.idsDefsOut = this.dexOut.appendSection(WriterSizes.access$100(r7), "ids defs");
        TableOfContents r42 = this.dexOut.getTableOfContents();
        this.contentsOut = r42;
        r42.dataOff = this.dexOut.getNextSectionStart();
        TableOfContents.Section r53 = r42.mapList;
        r53.off = this.dexOut.getNextSectionStart();
        r42.mapList.size = 1;
        this.mapListOut = this.dexOut.appendSection(WriterSizes.access$200(r7), "map list");
        TableOfContents.Section r54 = r42.typeLists;
        r54.off = this.dexOut.getNextSectionStart();
        this.typeListOut = this.dexOut.appendSection(WriterSizes.access$300(r7), "type list");
        TableOfContents.Section r55 = r42.annotationSetRefLists;
        r55.off = this.dexOut.getNextSectionStart();
        this.annotationSetRefListOut = this.dexOut.appendSection(WriterSizes.access$400(r7), "annotation set ref list");
        TableOfContents.Section r56 = r42.annotationSets;
        r56.off = this.dexOut.getNextSectionStart();
        this.annotationSetOut = this.dexOut.appendSection(WriterSizes.access$500(r7), "annotation sets");
        TableOfContents.Section r57 = r42.classDatas;
        r57.off = this.dexOut.getNextSectionStart();
        this.classDataOut = this.dexOut.appendSection(WriterSizes.access$600(r7), "class data");
        TableOfContents.Section r58 = r42.codes;
        r58.off = this.dexOut.getNextSectionStart();
        this.codeOut = this.dexOut.appendSection(WriterSizes.access$700(r7), "code");
        TableOfContents.Section r59 = r42.stringDatas;
        r59.off = this.dexOut.getNextSectionStart();
        this.stringDataOut = this.dexOut.appendSection(WriterSizes.access$800(r7), "string data");
        TableOfContents.Section r510 = r42.debugInfos;
        r510.off = this.dexOut.getNextSectionStart();
        this.debugInfoOut = this.dexOut.appendSection(WriterSizes.access$900(r7), "debug info");
        TableOfContents.Section r511 = r42.annotations;
        r511.off = this.dexOut.getNextSectionStart();
        this.annotationOut = this.dexOut.appendSection(WriterSizes.access$1000(r7), "annotation");
        TableOfContents.Section r512 = r42.encodedArrays;
        r512.off = this.dexOut.getNextSectionStart();
        this.encodedArrayOut = this.dexOut.appendSection(WriterSizes.access$1100(r7), "encoded array");
        TableOfContents.Section r513 = r42.annotationsDirectories;
        r513.off = this.dexOut.getNextSectionStart();
        this.annotationsDirectoryOut = this.dexOut.appendSection(WriterSizes.access$1200(r7), "annotations directory");
        r42.dataSize = this.dexOut.getNextSectionStart() - r42.dataOff;
    }

    public static /* synthetic */ Dex[] access$1300(DexMerger r0) {
        return r0.dexes;
    }

    public static /* synthetic */ IndexMap[] access$1400(DexMerger r0) {
        return r0.indexMaps;
    }

    public static /* synthetic */ TableOfContents access$1500(DexMerger r0) {
        return r0.contentsOut;
    }

    public static /* synthetic */ Dex.Section access$1600(DexMerger r0) {
        return r0.stringDataOut;
    }

    public static /* synthetic */ Dex.Section access$1700(DexMerger r0) {
        return r0.idsDefsOut;
    }

    public static /* synthetic */ Dex.Section access$1800(DexMerger r0) {
        return r0.typeListOut;
    }

    public static /* synthetic */ Dex.Section access$1900(DexMerger r0) {
        return r0.annotationOut;
    }

    public static /* synthetic */ Dex.Section access$2000(DexMerger r0) {
        return r0.headerOut;
    }

    public static /* synthetic */ Dex.Section access$2100(DexMerger r0) {
        return r0.mapListOut;
    }

    public static /* synthetic */ Dex.Section access$2200(DexMerger r0) {
        return r0.classDataOut;
    }

    public static /* synthetic */ Dex.Section access$2300(DexMerger r0) {
        return r0.codeOut;
    }

    public static /* synthetic */ Dex.Section access$2400(DexMerger r0) {
        return r0.debugInfoOut;
    }

    public static /* synthetic */ Dex.Section access$2500(DexMerger r0) {
        return r0.encodedArrayOut;
    }

    public static /* synthetic */ Dex.Section access$2600(DexMerger r0) {
        return r0.annotationsDirectoryOut;
    }

    public static /* synthetic */ Dex.Section access$2700(DexMerger r0) {
        return r0.annotationSetOut;
    }

    public static /* synthetic */ Dex.Section access$2800(DexMerger r0) {
        return r0.annotationSetRefListOut;
    }

    private SortableType[] getSortedTypes() {
        int r0 = this.contentsOut.typeIds.size;
        SortableType[] r1 = new SortableType[r0];
        int r3 = 0;
    L3:
        Dex[] r4 = this.dexes;
        if (r3 >= r4.length) goto L6;
        readSortableTypes(r1, r4[r3], this.indexMaps[r3]);
        r3 = r3 + 1;
    L6:
        boolean r6 = true;
        int r32 = 0;
    L7:
        if (r32 >= r0) goto L14;
        SortableType r42 = r1[r32];
        if (r42 == null) goto L13;
        if (r42.isDepthAssigned() == true) goto L13;
        r6 = r6 & r42.tryAssignDepth(r1);
    L13:
        r32 = r32 + 1;
        goto L7
    L14:
        if (r6 == false) goto L6;
        Arrays.sort(r1, SortableType.NULLS_LAST_ORDER);
        int r62 = Arrays.asList(r1).indexOf(null);
        if (r62 != (-1)) goto L18;
        return r1;
    L18:
        return (SortableType[]) Arrays.copyOfRange(r1, 0, r62);
    }

    public static void main(String[] r6) {
        if (r6.length >= 2) goto L6;
        printUsage();
        return;
    L6:
        int r1 = 1;
        Dex[] r0 = new Dex[r6.length - 1];
    L8:
        if (r1 >= r6.length) goto L10;
        r0[r1 - 1] = new Dex(new File(r6[r1]));
        r1 = r1 + 1;
        goto L8
    L10:
        new DexMerger(r0, CollisionPolicy.KEEP_FIRST, new DxContext()).merge().writeTo(new File(r6[0]));
    }

    private void mergeAnnotations() {
        new AnonymousClass9(this, this.annotationOut).mergeUnsorted();
    }

    private int mergeApiLevels() {
        int r0 = -1;
        int r1 = 0;
    L3:
        Dex[] r2 = this.dexes;
        if (r1 >= r2.length) goto L9;
        int r22 = r2[r1].getTableOfContents().apiLevel;
        if (r0 >= r22) goto L8;
        r0 = r22;
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        return r0;
    }

    private void mergeCallSiteIds() {
        new AnonymousClass5(this, this.idsDefsOut).mergeSorted();
    }

    private void mergeClassDefs() {
        SortableType[] r0 = getSortedTypes();
        TableOfContents.Section r1 = this.contentsOut.classDefs;
        r1.off = this.idsDefsOut.getPosition();
        TableOfContents.Section r12 = this.contentsOut.classDefs;
        r12.size = r0.length;
        int r13 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r13) goto L5;
        SortableType r3 = r0[r2];
        transformClassDef(r3.getDex(), r3.getClassDef(), r3.getIndexMap());
        r2 = r2 + 1;
        goto L3
    }

    private Dex mergeDexes() {
        mergeStringIds();
        mergeTypeIds();
        mergeTypeLists();
        mergeProtoIds();
        mergeFieldIds();
        mergeMethodIds();
        mergeMethodHandles();
        mergeAnnotations();
        unionAnnotationSetsAndDirectories();
        mergeCallSiteIds();
        mergeClassDefs();
        Arrays.sort(this.contentsOut.sections);
        TableOfContents r0 = this.contentsOut;
        TableOfContents.Section r1 = r0.header;
        r1.off = 0;
        r1.size = 1;
        r0.fileSize = this.dexOut.getLength();
        this.contentsOut.computeSizesFromOffsets();
        this.contentsOut.writeHeader(this.headerOut, mergeApiLevels());
        this.contentsOut.writeMap(this.mapListOut);
        this.dexOut.writeHashes();
        return this.dexOut;
    }

    private void mergeFieldIds() {
        new AnonymousClass7(this, this.idsDefsOut).mergeSorted();
    }

    private void mergeMethodHandles() {
        new AnonymousClass6(this, this.idsDefsOut).mergeUnsorted();
    }

    private void mergeMethodIds() {
        new AnonymousClass8(this, this.idsDefsOut).mergeSorted();
    }

    private void mergeProtoIds() {
        new AnonymousClass4(this, this.idsDefsOut).mergeSorted();
    }

    private void mergeStringIds() {
        new AnonymousClass1(this, this.idsDefsOut).mergeSorted();
    }

    private void mergeTypeIds() {
        new AnonymousClass2(this, this.idsDefsOut).mergeSorted();
    }

    private void mergeTypeLists() {
        new AnonymousClass3(this, this.typeListOut).mergeUnsorted();
    }

    private static void printUsage() {
        System.out.println("Usage: DexMerger <out.dex> <a.dex> <b.dex> ...");
        System.out.println();
        System.out.println("If a class is defined in several dex, the class found in the first dex will be used.");
    }

    private void readSortableTypes(SortableType[] r6, Dex r7, IndexMap r8) {
        Iterator<ClassDef> r0 = r7.classDefs().iterator();
    L4:
        if (r0.hasNext() == false) goto L20;
        ClassDef r1 = r0.next();
        SortableType r2 = r8.adjust(new SortableType(r7, r8, r1));
        int r3 = r2.getTypeIndex();
        if (r6[r3] == null) goto L7;
        if (this.collisionPolicy == CollisionPolicy.KEEP_FIRST) goto L4;
        DexMerger$$ExternalSyntheticBUOutline0.m("Multiple dex files define ", r7.typeNames().get(r1.getTypeIndex()));
        return;
    L7:
        r6[r3] = r2;
        goto L4
    }

    private void transformAnnotationDirectories(Dex r4, IndexMap r5) {
        TableOfContents.Section r0 = r4.getTableOfContents().annotationsDirectories;
        if (r0.exists() == false) goto L8;
        Dex.Section r42 = r4.open(r0.off);
        int r1 = 0;
    L6:
        if (r1 >= r0.size) goto L10;
        transformAnnotationDirectory(r42, r5);
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    private void transformAnnotationDirectory(Dex.Section r8, IndexMap r9) {
        this.contentsOut.annotationsDirectories.size++;
        this.annotationsDirectoryOut.assertFourByteAligned();
        r9.putAnnotationDirectoryOffset(r8.getPosition(), this.annotationsDirectoryOut.getPosition());
        this.annotationsDirectoryOut.writeInt(r9.adjustAnnotationSet(r8.readInt()));
        int r0 = r8.readInt();
        this.annotationsDirectoryOut.writeInt(r0);
        int r1 = r8.readInt();
        this.annotationsDirectoryOut.writeInt(r1);
        int r2 = r8.readInt();
        this.annotationsDirectoryOut.writeInt(r2);
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L5;
        this.annotationsDirectoryOut.writeInt(r9.adjustField(r8.readInt()));
        this.annotationsDirectoryOut.writeInt(r9.adjustAnnotationSet(r8.readInt()));
        r4 = r4 + 1;
        goto L3
    L5:
        int r02 = 0;
    L6:
        if (r02 >= r1) goto L8;
        this.annotationsDirectoryOut.writeInt(r9.adjustMethod(r8.readInt()));
        this.annotationsDirectoryOut.writeInt(r9.adjustAnnotationSet(r8.readInt()));
        r02 = r02 + 1;
    L8:
        if (r3 >= r2) goto L10;
        this.annotationsDirectoryOut.writeInt(r9.adjustMethod(r8.readInt()));
        this.annotationsDirectoryOut.writeInt(r9.adjustAnnotationSetRefList(r8.readInt()));
        r3 = r3 + 1;
        goto L8
    }

    private void transformAnnotationSet(IndexMap r5, Dex.Section r6) {
        this.contentsOut.annotationSets.size++;
        this.annotationSetOut.assertFourByteAligned();
        r5.putAnnotationSetOffset(r6.getPosition(), this.annotationSetOut.getPosition());
        int r0 = r6.readInt();
        this.annotationSetOut.writeInt(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.annotationSetOut.writeInt(r5.adjustAnnotation(r6.readInt()));
        r1 = r1 + 1;
        goto L3
    }

    private void transformAnnotationSetRefList(IndexMap r5, Dex.Section r6) {
        this.contentsOut.annotationSetRefLists.size++;
        this.annotationSetRefListOut.assertFourByteAligned();
        r5.putAnnotationSetRefListOffset(r6.getPosition(), this.annotationSetRefListOut.getPosition());
        int r0 = r6.readInt();
        this.annotationSetRefListOut.writeInt(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.annotationSetRefListOut.writeInt(r5.adjustAnnotationSet(r6.readInt()));
        r1 = r1 + 1;
        goto L3
    }

    private void transformAnnotationSetRefLists(Dex r4, IndexMap r5) {
        TableOfContents.Section r0 = r4.getTableOfContents().annotationSetRefLists;
        if (r0.exists() == false) goto L8;
        Dex.Section r42 = r4.open(r0.off);
        int r1 = 0;
    L6:
        if (r1 >= r0.size) goto L10;
        transformAnnotationSetRefList(r5, r42);
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    private void transformAnnotationSets(Dex r4, IndexMap r5) {
        TableOfContents.Section r0 = r4.getTableOfContents().annotationSets;
        if (r0.exists() == false) goto L8;
        Dex.Section r42 = r4.open(r0.off);
        int r1 = 0;
    L6:
        if (r1 >= r0.size) goto L10;
        transformAnnotationSet(r5, r42);
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    private int[] transformCatchHandlers(IndexMap r5, Code.CatchHandler[] r6) {
        int r0 = this.codeOut.getPosition();
        this.codeOut.writeUleb128(r6.length);
        int[] r1 = new int[r6.length];
        int r2 = 0;
    L4:
        if (r2 >= r6.length) goto L6;
        r1[r2] = this.codeOut.getPosition() - r0;
        transformEncodedCatchHandler(r6[r2], r5);
        r2 = r2 + 1;
        goto L4
    L6:
        return r1;
    }

    private void transformClassData(Dex r6, ClassData r7, IndexMap r8) {
        this.contentsOut.classDatas.size++;
        ClassData.Field[] r0 = r7.getStaticFields();
        ClassData.Field[] r1 = r7.getInstanceFields();
        ClassData.Method[] r2 = r7.getDirectMethods();
        ClassData.Method[] r72 = r7.getVirtualMethods();
        this.classDataOut.writeUleb128(r0.length);
        this.classDataOut.writeUleb128(r1.length);
        this.classDataOut.writeUleb128(r2.length);
        this.classDataOut.writeUleb128(r72.length);
        transformFields(r8, r0);
        transformFields(r8, r1);
        transformMethods(r6, r8, r2);
        transformMethods(r6, r8, r72);
    }

    private void transformClassDef(Dex r3, ClassDef r4, IndexMap r5) {
        this.idsDefsOut.assertFourByteAligned();
        this.idsDefsOut.writeInt(r4.getTypeIndex());
        this.idsDefsOut.writeInt(r4.getAccessFlags());
        this.idsDefsOut.writeInt(r4.getSupertypeIndex());
        this.idsDefsOut.writeInt(r4.getInterfacesOffset());
        int r0 = r5.adjustString(r4.getSourceFileIndex());
        this.idsDefsOut.writeInt(r0);
        int r02 = r4.getAnnotationsOffset();
        this.idsDefsOut.writeInt(r5.adjustAnnotationDirectory(r02));
        int r03 = r4.getClassDataOffset();
        Dex.Section r1 = this.idsDefsOut;
        if (r03 != 0) goto L5;
        r1.writeInt(0);
    L6:
        int r32 = r4.getStaticValuesOffset();
        this.idsDefsOut.writeInt(r5.adjustEncodedArray(r32));
        return;
    L5:
        r1.writeInt(this.classDataOut.getPosition());
        transformClassData(r3, r3.readClassData(r4), r5);
        goto L6
    }

    private void transformCode(Dex r8, Code r9, IndexMap r10) {
        this.contentsOut.codes.size++;
        this.codeOut.assertFourByteAligned();
        this.codeOut.writeUnsignedShort(r9.getRegistersSize());
        this.codeOut.writeUnsignedShort(r9.getInsSize());
        this.codeOut.writeUnsignedShort(r9.getOutsSize());
        Code.Try[] r0 = r9.getTries();
        Code.CatchHandler[] r1 = r9.getCatchHandlers();
        this.codeOut.writeUnsignedShort(r0.length);
        int r3 = r9.getDebugInfoOffset();
        Dex.Section r4 = this.codeOut;
        if (r3 == 0) goto L5;
        r4.writeInt(this.debugInfoOut.getPosition());
        transformDebugInfoItem(r8.open(r3), r10);
    L6:
        short[] r82 = this.instructionTransformer.transform(r10, r9.getInstructions());
        this.codeOut.writeInt(r82.length);
        this.codeOut.write(r82);
        if (r0.length > 0) goto L9;
        return;
    L9:
        if ((r82.length % 2) != 1) goto L11;
        this.codeOut.writeShort(0);
    L11:
        Dex.Section r83 = this.dexOut.open(this.codeOut.getPosition());
        this.codeOut.skip(r0.length * 8);
        transformTries(r83, r0, transformCatchHandlers(r10, r1));
        return;
    L5:
        r4.writeInt(0);
        goto L6
    }

    private void transformDebugInfoItem(Dex.Section r5, IndexMap r6) {
        this.contentsOut.debugInfos.size++;
        this.debugInfoOut.writeUleb128(r5.readUleb128());
        int r0 = r5.readUleb128();
        this.debugInfoOut.writeUleb128(r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.debugInfoOut.writeUleb128p1(r6.adjustString(r5.readUleb128p1()));
        r1 = r1 + 1;
    L5:
        byte r02 = r5.readByte();
        this.debugInfoOut.writeByte(r02);
        if (r02 != 9) goto L7;
        this.debugInfoOut.writeUleb128p1(r6.adjustString(r5.readUleb128p1()));
        goto L5
    L7:
        switch(r02) {
            case 0: goto L15;
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L10;
            case 4: goto L10;
            case 5: goto L9;
            case 6: goto L9;
            default: goto L5;
        };
    L9:
        this.debugInfoOut.writeUleb128(r5.readUleb128());
        goto L5
    L10:
        this.debugInfoOut.writeUleb128(r5.readUleb128());
        this.debugInfoOut.writeUleb128p1(r6.adjustString(r5.readUleb128p1()));
        this.debugInfoOut.writeUleb128p1(r6.adjustType(r5.readUleb128p1()));
        if (r02 != 4) goto L5;
        this.debugInfoOut.writeUleb128p1(r6.adjustString(r5.readUleb128p1()));
        goto L5
    L13:
        this.debugInfoOut.writeSleb128(r5.readSleb128());
        goto L5
    L14:
        this.debugInfoOut.writeUleb128(r5.readUleb128());
        goto L5
    }

    private void transformEncodedCatchHandler(Code.CatchHandler r7, IndexMap r8) {
        int r0 = r7.getCatchAllAddress();
        int[] r1 = r7.getTypeIndexes();
        int[] r72 = r7.getAddresses();
        Dex.Section r2 = this.codeOut;
        if (r0 == (-1)) goto L5;
        r2.writeSleb128(-r1.length);
    L6:
        int r22 = 0;
    L8:
        if (r22 >= r1.length) goto L10;
        this.codeOut.writeUleb128(r8.adjustType(r1[r22]));
        this.codeOut.writeUleb128(r72[r22]);
        r22 = r22 + 1;
        goto L8
    L10:
        if (r0 == (-1)) goto L14;
        this.codeOut.writeUleb128(r0);
        return;
    L14:
        return;
    L5:
        r2.writeSleb128(r1.length);
        goto L6
    }

    private void transformFields(IndexMap r7, ClassData.Field[] r8) {
        int r0 = r8.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        ClassData.Field r3 = r8[r1];
        int r4 = r7.adjustField(r3.getFieldIndex());
        this.classDataOut.writeUleb128(r4 - r2);
        this.classDataOut.writeUleb128(r3.getAccessFlags());
        r1 = r1 + 1;
        r2 = r4;
        goto L3
    }

    private void transformMethods(Dex r8, IndexMap r9, ClassData.Method[] r10) {
        int r0 = r10.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L9;
        ClassData.Method r4 = r10[r2];
        int r5 = r9.adjustMethod(r4.getMethodIndex());
        this.classDataOut.writeUleb128(r5 - r3);
        this.classDataOut.writeUleb128(r4.getAccessFlags());
        if (r4.getCodeOffset() != 0) goto L7;
        this.classDataOut.writeUleb128(0);
    L8:
        r2 = r2 + 1;
        r3 = r5;
        goto L3
    L7:
        this.codeOut.alignToFourBytesWithZeroFill();
        this.classDataOut.writeUleb128(this.codeOut.getPosition());
        transformCode(r8, r8.readCode(r4), r9);
        goto L8
    }

    private void transformStaticValues(Dex.Section r3, IndexMap r4) {
        this.contentsOut.encodedArrays.size++;
        r4.putEncodedArrayValueOffset(r3.getPosition(), this.encodedArrayOut.getPosition());
        r4.adjustEncodedArray(r3.readEncodedArray()).writeTo(this.encodedArrayOut);
    }

    private void transformStaticValues(Dex r4, IndexMap r5) {
        TableOfContents.Section r0 = r4.getTableOfContents().encodedArrays;
        if (r0.exists() == false) goto L8;
        Dex.Section r42 = r4.open(r0.off);
        int r1 = 0;
    L6:
        if (r1 >= r0.size) goto L10;
        transformStaticValues(r42, r5);
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    private void transformTries(Dex.Section r4, Code.Try[] r5, int[] r6) {
        int r3 = r5.length;
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L5;
        Code.Try r1 = r5[r0];
        r4.writeInt(r1.getStartAddress());
        r4.writeUnsignedShort(r1.getInstructionCount());
        r4.writeUnsignedShort(r6[r1.getCatchHandlerIndex()]);
        r0 = r0 + 1;
        goto L3
    }

    private void unionAnnotationSetsAndDirectories() {
        int r0 = 0;
        int r1 = 0;
    L3:
        Dex[] r2 = this.dexes;
        if (r1 >= r2.length) goto L6;
        transformAnnotationSets(r2[r1], this.indexMaps[r1]);
        r1 = r1 + 1;
        goto L3
    L6:
        int r12 = 0;
    L7:
        Dex[] r22 = this.dexes;
        if (r12 >= r22.length) goto L10;
        transformAnnotationSetRefLists(r22[r12], this.indexMaps[r12]);
        r12 = r12 + 1;
        goto L7
    L10:
        int r13 = 0;
    L11:
        Dex[] r23 = this.dexes;
        if (r13 >= r23.length) goto L14;
        transformAnnotationDirectories(r23[r13], this.indexMaps[r13]);
        r13 = r13 + 1;
    L14:
        Dex[] r14 = this.dexes;
        if (r0 >= r14.length) goto L17;
        transformStaticValues(r14[r0], this.indexMaps[r0]);
        r0 = r0 + 1;
        goto L14
    }

    public Dex merge() {
        Dex[] r0 = this.dexes;
        int r3 = 0;
        if (r0.length != 1) goto L7;
        return r0[0];
    L7:
        if (r0.length != 0) goto L10;
        return null;
    L10:
        long r02 = System.nanoTime();
        Dex r2 = mergeDexes();
        WriterSizes r4 = new WriterSizes(this);
        int r5 = this.writerSizes.size() - r4.size();
        if (r5 <= this.compactWasteThreshold) goto L13;
        r2 = new DexMerger(new Dex[]{this.dexOut, new Dex(0)}, CollisionPolicy.FAIL, this.context, r4).mergeDexes();
        this.context.out.printf("Result compacted from %.1fKiB to %.1fKiB to save %.1fKiB%n", new Object[]{Float.valueOf(this.dexOut.getLength() / 1024.0f), Float.valueOf(r2.getLength() / 1024.0f), Float.valueOf(r5 / 1024.0f)});
    L13:
        long r42 = System.nanoTime() - r02;
    L14:
        int r03 = this.dexes.length;
        DxContext r1 = this.context;
        if (r3 >= r03) goto L17;
        PrintStream r04 = r1.out;
        int r12 = r3 + 1;
        r04.printf("Merged dex #%d (%d defs/%.1fKiB)%n", new Object[]{Integer.valueOf(r12), Integer.valueOf(this.dexes[r3].getTableOfContents().classDefs.size), Float.valueOf(this.dexes[r3].getLength() / 1024.0f)});
        r3 = r12;
        goto L14
    L17:
        r1.out.printf("Result is %d defs/%.1fKiB. Took %.1fs%n", new Object[]{Integer.valueOf(r2.getTableOfContents().classDefs.size), Float.valueOf(r2.getLength() / 1024.0f), Float.valueOf(r42 / 1.0E9f)});
        return r2;
    }

    public void setCompactWasteThreshold(int r1) {
        this.compactWasteThreshold = r1;
    }
}
