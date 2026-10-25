package com.android.dex;

import com.android.dex.Dex;
import com.android.dx.merge.DexMerger$$ExternalSyntheticBUOutline0;
import java.util.Arrays;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class TableOfContents {
    public final Section annotationSetRefLists;
    public final Section annotationSets;
    public final Section annotations;
    public final Section annotationsDirectories;
    public int apiLevel;
    public final Section callSiteIds;
    public int checksum;
    public final Section classDatas;
    public final Section classDefs;
    public final Section codes;
    public int dataOff;
    public int dataSize;
    public final Section debugInfos;
    public final Section encodedArrays;
    public final Section fieldIds;
    public int fileSize;
    public final Section header;
    public int linkOff;
    public int linkSize;
    public final Section mapList;
    public final Section methodHandles;
    public final Section methodIds;
    public final Section protoIds;
    public final Section[] sections;
    public byte[] signature;
    public final Section stringDatas;
    public final Section stringIds;
    public final Section typeIds;
    public final Section typeLists;

    public static class Section implements Comparable<Section> {
        public int byteCount;
        public int off;
        public int size;
        public final short type;

        public Section(int r3) {
            this.size = 0;
            this.off = -1;
            this.byteCount = 0;
            this.type = (short) r3;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Section r1) {
            int r0 = this.off;
            int r12 = r1.off;
            if (r0 == r12) goto L9;
            if (r0 >= r12) goto L7;
            return -1;
        L7:
            return 1;
        L9:
            return 0;
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Section r1) {
            return compareTo2(r1);
        }

        public boolean exists() {
            if (this.size <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        public String toString() {
            return String.format("Section[type=%#x,off=%#x,size=%#x]", new Object[]{Short.valueOf(this.type), Integer.valueOf(this.off), Integer.valueOf(this.size)});
        }
    }

    public TableOfContents() {
        Section r1 = new Section(0);
        this.header = r1;
        Section r2 = new Section(1);
        this.stringIds = r2;
        Section r3 = new Section(2);
        this.typeIds = r3;
        Section r4 = new Section(3);
        this.protoIds = r4;
        Section r5 = new Section(4);
        this.fieldIds = r5;
        Section r6 = new Section(5);
        this.methodIds = r6;
        Section r7 = new Section(6);
        this.classDefs = r7;
        Section r9 = new Section(7);
        this.callSiteIds = r9;
        Section r10 = new Section(8);
        this.methodHandles = r10;
        Section r8 = new Section(4096);
        this.mapList = r8;
        Section r11 = new Section(4097);
        this.typeLists = r11;
        Section r12 = new Section(4098);
        this.annotationSetRefLists = r12;
        Section r13 = new Section(4099);
        this.annotationSets = r13;
        Section r14 = new Section(8192);
        this.classDatas = r14;
        Section r15 = new Section(8193);
        this.codes = r15;
        Section r16 = new Section(8194);
        this.stringDatas = r16;
        Section r22 = new Section(8195);
        this.debugInfos = r22;
        Section r17 = new Section(8196);
        this.annotations = r17;
        Section r23 = new Section(8197);
        this.encodedArrays = r23;
        Section r18 = new Section(8198);
        this.annotationsDirectories = r18;
        this.sections = new Section[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r22, r17, r23, r18};
        this.signature = new byte[20];
    }

    private Section getSection(short r5) {
        Section[] r4 = this.sections;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        Section r2 = r4[r1];
        if (r2.type == r5) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("No such map item: ", r5);
        return null;
    }

    private void readHeader(Dex.Section r10) {
        byte[] r0 = r10.readByteArray(8);
        if (DexFormat.isSupportedDexMagic(r0) == false) goto L19;
        this.apiLevel = DexFormat.magicToApi(r0);
        this.checksum = r10.readInt();
        this.signature = r10.readByteArray(20);
        this.fileSize = r10.readInt();
        int r02 = r10.readInt();
        if (r02 != 112) goto L16;
        int r03 = r10.readInt();
        if (r03 != 305419896) goto L14;
        this.linkSize = r10.readInt();
        this.linkOff = r10.readInt();
        Section r04 = this.mapList;
        r04.off = r10.readInt();
        if (this.mapList.off == 0) goto L13;
        Section r05 = this.stringIds;
        r05.size = r10.readInt();
        Section r06 = this.stringIds;
        r06.off = r10.readInt();
        Section r07 = this.typeIds;
        r07.size = r10.readInt();
        Section r08 = this.typeIds;
        r08.off = r10.readInt();
        Section r09 = this.protoIds;
        r09.size = r10.readInt();
        Section r010 = this.protoIds;
        r010.off = r10.readInt();
        Section r011 = this.fieldIds;
        r011.size = r10.readInt();
        Section r012 = this.fieldIds;
        r012.off = r10.readInt();
        Section r013 = this.methodIds;
        r013.size = r10.readInt();
        Section r014 = this.methodIds;
        r014.off = r10.readInt();
        Section r015 = this.classDefs;
        r015.size = r10.readInt();
        Section r016 = this.classDefs;
        r016.off = r10.readInt();
        this.dataSize = r10.readInt();
        this.dataOff = r10.readInt();
        return;
    L13:
        throw new DexException("Cannot merge dex files that do not contain a map");
    L14:
        DexMerger$$ExternalSyntheticBUOutline0.m("Unexpected endian tag: 0x", Integer.toHexString(r03));
        return;
    L16:
        DexMerger$$ExternalSyntheticBUOutline0.m("Unexpected header: 0x", Integer.toHexString(r02));
        return;
    L19:
        throw new DexException(String.format("Unexpected magic: [0x%02x, 0x%02x, 0x%02x, 0x%02x, 0x%02x, 0x%02x, 0x%02x, 0x%02x]", new Object[]{Byte.valueOf(r0[0]), Byte.valueOf(r0[1]), Byte.valueOf(r0[2]), Byte.valueOf(r0[3]), Byte.valueOf(r0[4]), Byte.valueOf(r0[5]), Byte.valueOf(r0[6]), Byte.valueOf(r0[7])}));
    }

    private void readMap(Dex.Section r10) {
        int r0 = r10.readInt();
        Section r1 = null;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L21;
        short r3 = r10.readShort();
        r10.readShort();
        Section r4 = getSection(r3);
        int r5 = r10.readInt();
        int r6 = r10.readInt();
        int r7 = r4.size;
        if (r7 == 0) goto L7;
        if (r7 == r5) goto L7;
    L11:
        DexMerger$$ExternalSyntheticBUOutline0.m("Unexpected map value for 0x", Integer.toHexString(r3));
        return;
    L7:
        int r72 = r4.off;
        if (r72 == (-1)) goto L13;
        if (r72 != r6) goto L11;
    L13:
        r4.size = r5;
        r4.off = r6;
        if (r1 == null) goto L20;
        if (r1.off <= r6) goto L20;
        throw new DexException("Map is unsorted at " + r1 + ", " + r4);
    L20:
        r2 = r2 + 1;
        r1 = r4;
        goto L3
    L21:
        Arrays.sort(this.sections);
    }

    public void computeSizesFromOffsets() {
        int r0 = this.dataOff + this.dataSize;
        int r1 = this.sections.length - 1;
    L3:
        if (r1 < 0) goto L16;
        Section r2 = this.sections[r1];
        int r3 = r2.off;
        if (r3 == (-1)) goto L9;
        if (r3 > r0) goto L10;
        r2.byteCount = r0 - r3;
        r0 = r3;
        goto L9
    L10:
        Dex$$ExternalSyntheticBUOutline2.m("Map is unsorted at ", r2);
        return;
    L9:
        r1 = r1 - 1;
        goto L3
    }

    public void readFrom(Dex r2) {
        readHeader(r2.open(0));
        readMap(r2.open(this.mapList.off));
        computeSizesFromOffsets();
    }

    public void writeHeader(Dex.Section r2, int r3) {
        r2.write(DexFormat.apiToMagic(r3).getBytes("UTF-8"));
        r2.writeInt(this.checksum);
        r2.write(this.signature);
        r2.writeInt(this.fileSize);
        r2.writeInt(112);
        r2.writeInt(305419896);
        r2.writeInt(this.linkSize);
        r2.writeInt(this.linkOff);
        r2.writeInt(this.mapList.off);
        r2.writeInt(this.stringIds.size);
        r2.writeInt(this.stringIds.off);
        r2.writeInt(this.typeIds.size);
        r2.writeInt(this.typeIds.off);
        r2.writeInt(this.protoIds.size);
        r2.writeInt(this.protoIds.off);
        r2.writeInt(this.fieldIds.size);
        r2.writeInt(this.fieldIds.off);
        r2.writeInt(this.methodIds.size);
        r2.writeInt(this.methodIds.off);
        r2.writeInt(this.classDefs.size);
        r2.writeInt(this.classDefs.off);
        r2.writeInt(this.dataSize);
        r2.writeInt(this.dataOff);
    }

    public void writeMap(Dex.Section r7) {
        Section[] r0 = this.sections;
        int r1 = r0.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r1) goto L8;
        if (r0[r3].exists() == false) goto L7;
        r4 = r4 + 1;
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        r7.writeInt(r4);
        Section[] r6 = this.sections;
        int r02 = r6.length;
        int r12 = 0;
    L9:
        if (r12 >= r02) goto L14;
        Section r32 = r6[r12];
        if (r32.exists() == false) goto L13;
        r7.writeShort(r32.type);
        r7.writeShort(0);
        r7.writeInt(r32.size);
        r7.writeInt(r32.off);
    L13:
        r12 = r12 + 1;
        goto L9
    }
}
