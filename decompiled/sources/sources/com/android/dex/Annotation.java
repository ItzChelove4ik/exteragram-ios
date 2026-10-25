package com.android.dex;

import com.android.dex.Dex;

/* loaded from: classes4.dex */
public final class Annotation implements Comparable<Annotation> {
    private final Dex dex;
    private final EncodedValue encodedAnnotation;
    private final byte visibility;

    public Annotation(Dex r1, byte r2, EncodedValue r3) {
        this.dex = r1;
        this.visibility = r2;
        this.encodedAnnotation = r3;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Annotation r1) {
        return this.encodedAnnotation.compareTo2(r1.encodedAnnotation);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Annotation r1) {
        return compareTo2(r1);
    }

    public EncodedValueReader getReader() {
        return new EncodedValueReader(this.encodedAnnotation, 29);
    }

    public int getTypeIndex() {
        EncodedValueReader r0 = getReader();
        r0.readAnnotation();
        return r0.getAnnotationType();
    }

    public byte getVisibility() {
        return this.visibility;
    }

    public String toString() {
        Dex r0 = this.dex;
        byte r1 = this.visibility;
        if (r0 != null) goto L7;
        return r1 + " " + getTypeIndex();
    L7:
        return r1 + " " + this.dex.typeNames().get(getTypeIndex());
    }

    public void writeTo(Dex.Section r2) {
        r2.writeByte(this.visibility);
        this.encodedAnnotation.writeTo(r2);
    }
}
