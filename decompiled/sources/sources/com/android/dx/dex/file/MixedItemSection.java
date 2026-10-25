package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class MixedItemSection extends Section {
    private static final Comparator<OffsettedItem> TYPE_SORTER = null;
    private final HashMap<OffsettedItem, OffsettedItem> interns;
    private final ArrayList<OffsettedItem> items;
    private final SortType sort;
    private int writeSize;

    /* renamed from: com.android.dx.dex.file.MixedItemSection$1, reason: invalid class name */
    public static class AnonymousClass1 implements Comparator<OffsettedItem> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(OffsettedItem r1, OffsettedItem r2) {
            return r1.itemType().compareTo(r2.itemType());
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(OffsettedItem r1, OffsettedItem r2) {
            return compare2(r1, r2);
        }
    }

    /* renamed from: com.android.dx.dex.file.MixedItemSection$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$dx$dex$file$MixedItemSection$SortType = null;

        static {
            int[] r0 = new int[SortType.values().length];
            $SwitchMap$com$android$dx$dex$file$MixedItemSection$SortType = r0;
            r0[SortType.INSTANCE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$com$android$dx$dex$file$MixedItemSection$SortType[SortType.TYPE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    public enum SortType extends Enum<SortType> {
        private static final /* synthetic */ SortType[] $VALUES = null;
        public static final SortType INSTANCE = null;
        public static final SortType NONE = null;
        public static final SortType TYPE = null;

        static {
            SortType r0 = new SortType("NONE", 0);
            NONE = r0;
            SortType r1 = new SortType("TYPE", 1);
            TYPE = r1;
            SortType r2 = new SortType("INSTANCE", 2);
            INSTANCE = r2;
            $VALUES = new SortType[]{r0, r1, r2};
        }

        SortType(String r1, int r2) {
        }

        public static SortType valueOf(String r1) {
            return (SortType) Enum.valueOf(SortType.class, r1);
        }

        public static SortType[] values() {
            return (SortType[]) $VALUES.clone();
        }
    }

    static {
        TYPE_SORTER = new AnonymousClass1();
    }

    public MixedItemSection(String r1, DexFile r2, int r3, SortType r4) {
        super(r1, r2, r3);
        this.items = new ArrayList(100);
        this.interns = new HashMap(100);
        this.sort = r4;
        this.writeSize = -1;
    }

    public void add(OffsettedItem r3) {
        throwIfPrepared();
    L9:
        f$$ExternalSyntheticBUOutline2.m("item == null");
        return;
    L4:
        if (r3.getAlignment() > getAlignment()) goto L8;
        this.items.add(r3);
        return;
    L8:
        throw new IllegalArgumentException("incompatible item alignment");     // Catch: NullPointerException -> L9
    }

    public <T extends OffsettedItem> T get(T r1) {
        throwIfNotPrepared();
        T r0 = (T) this.interns.get(r1);
        if (r0 == null) goto L6;
        return r0;
    L6:
        throw new NoSuchElementException(r1.toString());
    }

    @Override // com.android.dx.dex.file.Section
    public int getAbsoluteItemOffset(Item r1) {
        return ((OffsettedItem) r1).getAbsoluteOffset();
    }

    public synchronized <T extends OffsettedItem> T intern(T r2) {
        monitor-enter(this);
        throwIfPrepared();     // Catch: Throwable -> L10
        T r0 = (T) this.interns.get(r2);     // Catch: Throwable -> L10
        if (r0 == null) goto L7;
        monitor-exit(this);
        return r0;
    L7:
        add(r2);     // Catch: Throwable -> L10
        this.interns.put(r2, r2);     // Catch: Throwable -> L10
        monitor-exit(this);
        return r2;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.items;
    }

    public void placeItems() {
        throwIfNotPrepared();
        int r0 = AnonymousClass2.$SwitchMap$com$android$dx$dex$file$MixedItemSection$SortType[this.sort.ordinal()];
        if (r0 != 1) goto L5;
        Collections.sort(this.items);
    L9:
        int r02 = this.items.size();
        int r1 = 0;
        int r2 = 0;
    L10:
        if (r1 >= r02) goto L21;
        OffsettedItem r3 = this.items.get(r1);
        int r4 = r3.place(this, r2);     // Catch: RuntimeException -> L15
        if (r4 < r2) goto L18;
        r2 = r3.writeSize() + r4;     // Catch: RuntimeException -> L15
        r1 = r1 + 1;     // Catch: RuntimeException -> L15
        goto L10
    L18:
        throw new RuntimeException("bogus place() result for " + r3);     // Catch: RuntimeException -> L15
    L15:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while placing " + r3);
    L21:
        this.writeSize = r2;
        return;
    L5:
        if (r0 != 2) goto L9;
        Collections.sort(this.items, TYPE_SORTER);
        goto L9
    }

    @Override // com.android.dx.dex.file.Section
    public void prepare0() {
        DexFile r0 = getFile();
        int r1 = 0;
    L3:
        int r2 = this.items.size();
        if (r1 < r2) goto L6;
        return;
    L6:
        if (r1 >= r2) goto L3;
        this.items.get(r1).addContents(r0);
        r1 = r1 + 1;
        goto L6
    }

    public int size() {
        return this.items.size();
    }

    public void writeHeaderPart(AnnotatedOutput r6) {
        throwIfNotPrepared();
        int r0 = this.writeSize;
        if (r0 == (-1)) goto L15;
        if (r0 != 0) goto L6;
        int r1 = 0;
    L7:
        String r5 = getName();
        if (r5 != null) goto L10;
        r5 = "<unnamed>";
    L10:
        char[] r2 = new char[15 - r5.length()];
        Arrays.fill(r2, ' ');
        String r3 = new String(r2);
        if (r6.annotates() == false) goto L13;
        r6.annotate(4, r5 + "_size:" + r3 + Hex.u4(r0));
        r6.annotate(4, r5 + "_off: " + r3 + Hex.u4(r1));
    L13:
        r6.writeInt(r0);
        r6.writeInt(r1);
        return;
    L6:
        r1 = getFileOffset();
        goto L7
    L15:
        GlShader$$ExternalSyntheticBUOutline1.m("write size not yet set");
    }

    public void writeIndexAnnotation(AnnotatedOutput r7, ItemType r8, String r9) {
        throwIfNotPrepared();
        TreeMap r0 = new TreeMap();
        ArrayList<OffsettedItem> r6 = this.items;
        int r1 = r6.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        OffsettedItem r4 = r6.get(r3);
        r3 = r3 + 1;
        OffsettedItem r42 = r4;
        if (r42.itemType() != r8) goto L3;
        r0.put(r42.toHuman(), r42);
        goto L3
    L8:
        if (r0.size() == 0) goto L14;
        r7.annotate(0, r9);
        Iterator r62 = r0.entrySet().iterator();
    L12:
        if (r62.hasNext() == false) goto L21;
        Map.Entry r82 = (Map.Entry) r62.next();
        r7.annotate(0, ((OffsettedItem) r82.getValue()).offsetString() + ' ' + ((String) r82.getKey()) + '\n');
        goto L12
    L21:
        return;
    }

    @Override // com.android.dx.dex.file.Section
    public int writeSize() {
        throwIfNotPrepared();
        return this.writeSize;
    }

    @Override // com.android.dx.dex.file.Section
    public void writeTo0(AnnotatedOutput r13) {
        boolean r0 = r13.annotates();
        DexFile r1 = getFile();
        ArrayList<OffsettedItem> r2 = this.items;
        int r3 = r2.size();
        boolean r7 = true;
        int r6 = 0;
        int r8 = 0;
    L3:
        if (r8 >= r3) goto L14;
        OffsettedItem r9 = r2.get(r8);
        r8 = r8 + 1;
        OffsettedItem r92 = r9;
        if (r0 == false) goto L9;
        if (r7 == false) goto L8;
        r7 = false;
        goto L9
    L8:
        r13.annotate(0, "\n");
    L9:
        int r10 = r92.getAlignment() - 1;
        int r102 = (~r10) & (r6 + r10);
        if (r6 == r102) goto L12;
        r13.writeZeroes(r102 - r6);
        r6 = r102;
    L12:
        r92.writeTo(r1, r13);
        r6 = r6 + r92.writeSize();
        goto L3
    L14:
        if (r6 != this.writeSize) goto L16;
        return;
    L16:
        GlShader$$ExternalSyntheticBUOutline1.m("output size mismatch");
    }
}
