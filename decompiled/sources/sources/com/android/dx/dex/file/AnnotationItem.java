package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.AnnotationVisibility;
import com.android.dx.rop.annotation.NameValuePair;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ByteArrayAnnotatedOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class AnnotationItem extends OffsettedItem {
    private static final int ALIGNMENT = 1;
    private static final TypeIdSorter TYPE_ID_SORTER = null;
    private static final int VISIBILITY_BUILD = 0;
    private static final int VISIBILITY_RUNTIME = 1;
    private static final int VISIBILITY_SYSTEM = 2;
    private final Annotation annotation;
    private byte[] encodedForm;
    private TypeIdItem type;

    /* renamed from: com.android.dx.dex.file.AnnotationItem$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$dx$rop$annotation$AnnotationVisibility = null;

        static {
            int[] r0 = new int[AnnotationVisibility.values().length];
            $SwitchMap$com$android$dx$rop$annotation$AnnotationVisibility = r0;
            r0[AnnotationVisibility.BUILD.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$com$android$dx$rop$annotation$AnnotationVisibility[AnnotationVisibility.RUNTIME.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$android$dx$rop$annotation$AnnotationVisibility[AnnotationVisibility.SYSTEM.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public static class TypeIdSorter implements Comparator<AnnotationItem> {
        private TypeIdSorter() {
        }

        public /* synthetic */ TypeIdSorter(AnonymousClass1 r1) {
            this();
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(AnnotationItem r1, AnnotationItem r2) {
            int r0 = AnnotationItem.access$100(r1).getIndex();
            int r12 = AnnotationItem.access$100(r2).getIndex();
            if (r0 >= r12) goto L6;
            return -1;
        L6:
            if (r0 <= r12) goto L9;
            return 1;
        L9:
            return 0;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(AnnotationItem r1, AnnotationItem r2) {
            return compare2(r1, r2);
        }
    }

    static {
        TYPE_ID_SORTER = new TypeIdSorter(null);
    }

    public AnnotationItem(Annotation r3, DexFile r4) {
        super(1, -1);
        if (r3 == null) goto L6;
        this.annotation = r3;
        this.type = null;
        this.encodedForm = null;
        addContents(r4);
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("annotation == null");
        throw null;
    }

    public static /* synthetic */ TypeIdItem access$100(AnnotationItem r0) {
        return r0.type;
    }

    public static void sortByTypeIdIndex(AnnotationItem[] r1) {
        Arrays.sort(r1, TYPE_ID_SORTER);
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r3) {
        this.type = r3.getTypeIds().intern(this.annotation.getType());
        ValueEncoder.addContents(r3, this.annotation);
    }

    public void annotateTo(AnnotatedOutput r5, String r6) {
        r5.annotate(0, r6 + "visibility: " + this.annotation.getVisibility().toHuman());
        r5.annotate(0, r6 + "type: " + this.annotation.getType().toHuman());
        Iterator<NameValuePair> r4 = this.annotation.getNameValuePairs().iterator();
    L4:
        if (r4.hasNext() == false) goto L6;
        NameValuePair r0 = r4.next();
        r5.annotate(0, r6 + r0.getName().toHuman() + ": " + ValueEncoder.constantToHuman(r0.getValue()));
        goto L4
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem r1) {
        return this.annotation.compareTo2(((AnnotationItem) r1).annotation);
    }

    public int hashCode() {
        return this.annotation.hashCode();
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATION_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r3, int r4) {
        ByteArrayAnnotatedOutput r42 = new ByteArrayAnnotatedOutput();
        new ValueEncoder(r3.getFile(), r42).writeAnnotation(this.annotation, false);
        byte[] r32 = r42.toByteArray();
        this.encodedForm = r32;
        setWriteSize(r32.length + 1);
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.annotation.toHuman();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r7, AnnotatedOutput r8) {
        boolean r0 = r8.annotates();
        AnnotationVisibility r1 = this.annotation.getVisibility();
        if (r0 == false) goto L5;
        r8.annotate(0, offsetString() + " annotation");
        StringBuilder r4 = new StringBuilder("  visibility: VISBILITY_");
        r4.append(r1);
        r8.annotate(1, r4.toString());
    L5:
        int r12 = AnonymousClass1.$SwitchMap$com$android$dx$rop$annotation$AnnotationVisibility[r1.ordinal()];
        if (r12 != 1) goto L8;
        r8.writeByte(0);
    L16:
        if (r0 == false) goto L19;
        new ValueEncoder(r7, r8).writeAnnotation(this.annotation, true);
        return;
    L19:
        r8.write(this.encodedForm);
        return;
    L8:
        if (r12 != 2) goto L10;
        r8.writeByte(1);
        goto L16
    L10:
        if (r12 != 3) goto L12;
        r8.writeByte(2);
        goto L16
    L12:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
    }
}
