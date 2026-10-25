package com.google.android.material.color;

import android.content.Context;
import android.util.Pair;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import com.sun.jna.Union$$ExternalSyntheticBUOutline0;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.UByte;

/* loaded from: classes5.dex */
final class ColorResourcesTableCreator {
    private static final byte ANDROID_PACKAGE_ID = 1;
    private static final PackageInfo ANDROID_PACKAGE_INFO = null;
    private static final byte APPLICATION_PACKAGE_ID = Byte.MAX_VALUE;
    private static final Comparator<ColorResource> COLOR_RESOURCE_COMPARATOR = null;
    private static final short HEADER_TYPE_PACKAGE = 512;
    private static final short HEADER_TYPE_RES_TABLE = 2;
    private static final short HEADER_TYPE_STRING_POOL = 1;
    private static final short HEADER_TYPE_TYPE = 513;
    private static final short HEADER_TYPE_TYPE_SPEC = 514;
    private static final String RESOURCE_TYPE_NAME_COLOR = "color";
    private static byte typeIdColor;

    /* renamed from: com.google.android.material.color.ColorResourcesTableCreator$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<ColorResource> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(ColorResource r1, ColorResource r2) {
            return ColorResource.access$000(r1) - ColorResource.access$000(r2);
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(ColorResource r1, ColorResource r2) {
            return compare2(r1, r2);
        }
    }

    public static class ColorResource {
        private final short entryId;
        private final String name;
        private final byte packageId;
        private final byte typeId;
        private final int value;

        public ColorResource(int r1, String r2, int r3) {
            this.name = r2;
            this.value = r3;
            this.entryId = (short) (65535 & r1);
            this.typeId = (byte) ((r1 >> 16) & 255);
            this.packageId = (byte) ((r1 >> 24) & 255);
        }

        public static /* synthetic */ short access$000(ColorResource r0) {
            return r0.entryId;
        }

        public static /* synthetic */ String access$100(ColorResource r0) {
            return r0.name;
        }

        public static /* synthetic */ int access$1400(ColorResource r0) {
            return r0.value;
        }

        public static /* synthetic */ byte access$200(ColorResource r0) {
            return r0.typeId;
        }

        public static /* synthetic */ byte access$300(ColorResource r0) {
            return r0.packageId;
        }
    }

    public static class PackageChunk {
        private static final short HEADER_SIZE = 288;
        private static final int PACKAGE_NAME_MAX_LENGTH = 128;
        private final ResChunkHeader header;
        private final StringPoolChunk keyStrings;
        private final PackageInfo packageInfo;
        private final TypeSpecChunk typeSpecChunk;
        private final StringPoolChunk typeStrings;

        public PackageChunk(PackageInfo r3, List<ColorResource> r4) {
            this.packageInfo = r3;
            this.typeStrings = new StringPoolChunk(false, generateTypeStrings(r4));
            this.keyStrings = new StringPoolChunk(true, generateKeyStrings(r4));
            this.typeSpecChunk = new TypeSpecChunk(r4);
            this.header = new ResChunkHeader(ColorResourcesTableCreator.HEADER_TYPE_PACKAGE, HEADER_SIZE, getChunkSize());
        }

        private String[] generateKeyStrings(List<ColorResource> r3) {
            String[] r2 = new String[r3.size()];
            int r0 = 0;
        L4:
            if (r0 >= r3.size()) goto L6;
            r2[r0] = ColorResource.access$100(r3.get(r0));
            r0 = r0 + 1;
            goto L4
        L6:
            return r2;
        }

        private String[] generateTypeStrings(List<ColorResource> r4) {
            int r0 = 0;
            if (r4.isEmpty() == true) goto L11;
            int r3 = ColorResource.access$200(r4.get(0));
            String[] r42 = new String[r3];
        L5:
            int r1 = r3 - 1;
            if (r0 >= r1) goto L8;
            StringBuilder r12 = new StringBuilder("?");
            int r2 = r0 + 1;
            r12.append(r2);
            r42[r0] = r12.toString();
            r0 = r2;
            goto L5
        L8:
            r42[r1] = ColorResourcesTableCreator.RESOURCE_TYPE_NAME_COLOR;
            return r42;
        L11:
            return new String[0];
        }

        public int getChunkSize() {
            return ((this.typeStrings.getChunkSize() + 288) + this.keyStrings.getChunkSize()) + this.typeSpecChunk.getChunkSizeWithTypeChunk();
        }

        public void writeTo(ByteArrayOutputStream r5) {
            this.header.writeTo(r5);
            r5.write(ColorResourcesTableCreator.access$500(PackageInfo.access$1000(this.packageInfo)));
            char[] r0 = PackageInfo.access$1100(this.packageInfo).toCharArray();
            int r2 = 0;
        L4:
            if (r2 >= 128) goto L10;
            if (r2 >= r0.length) goto L8;
            r5.write(ColorResourcesTableCreator.access$1200(r0[r2]));
        L9:
            r2 = r2 + 1;
            goto L4
        L8:
            r5.write(ColorResourcesTableCreator.access$1200(0));
            goto L9
        L10:
            r5.write(ColorResourcesTableCreator.access$500(288));
            r5.write(ColorResourcesTableCreator.access$500(0));
            r5.write(ColorResourcesTableCreator.access$500(this.typeStrings.getChunkSize() + 288));
            r5.write(ColorResourcesTableCreator.access$500(0));
            r5.write(ColorResourcesTableCreator.access$500(0));
            this.typeStrings.writeTo(r5);
            this.keyStrings.writeTo(r5);
            this.typeSpecChunk.writeTo(r5);
        }
    }

    public static class PackageInfo {
        private final int id;
        private final String name;

        public PackageInfo(int r1, String r2) {
            this.id = r1;
            this.name = r2;
        }

        public static /* synthetic */ int access$1000(PackageInfo r0) {
            return r0.id;
        }

        public static /* synthetic */ String access$1100(PackageInfo r0) {
            return r0.name;
        }
    }

    public static class ResChunkHeader {
        private final int chunkSize;
        private final short headerSize;
        private final short type;

        public ResChunkHeader(short r1, short r2, int r3) {
            this.type = r1;
            this.headerSize = r2;
            this.chunkSize = r3;
        }

        public void writeTo(ByteArrayOutputStream r2) {
            r2.write(ColorResourcesTableCreator.access$600(this.type));
            r2.write(ColorResourcesTableCreator.access$600(this.headerSize));
            r2.write(ColorResourcesTableCreator.access$500(this.chunkSize));
        }
    }

    public static class ResEntry {
        private static final byte DATA_TYPE_AARRGGBB = 28;
        private static final short ENTRY_SIZE = 8;
        private static final short FLAG_PUBLIC = 2;
        private static final int SIZE = 16;
        private static final short VALUE_SIZE = 8;
        private final int data;
        private final int keyStringIndex;

        public ResEntry(int r1, int r2) {
            this.keyStringIndex = r1;
            this.data = r2;
        }

        public void writeTo(ByteArrayOutputStream r4) {
            r4.write(ColorResourcesTableCreator.access$600(8));
            r4.write(ColorResourcesTableCreator.access$600(FLAG_PUBLIC));
            r4.write(ColorResourcesTableCreator.access$500(this.keyStringIndex));
            r4.write(ColorResourcesTableCreator.access$600(8));
            r4.write(new byte[]{0, DATA_TYPE_AARRGGBB});
            r4.write(ColorResourcesTableCreator.access$500(this.data));
        }
    }

    public static class ResTable {
        private static final short HEADER_SIZE = 12;
        private final ResChunkHeader header;
        private final List<PackageChunk> packageChunks;
        private final int packageCount;
        private final StringPoolChunk stringPool;

        public ResTable(Map<PackageInfo, List<ColorResource>> r5) {
            this.packageChunks = new ArrayList();
            this.packageCount = r5.size();
            this.stringPool = new StringPoolChunk(new String[0]);
            Iterator<Map.Entry<PackageInfo, List<ColorResource>>> r52 = r5.entrySet().iterator();
        L4:
            if (r52.hasNext() == false) goto L6;
            Map.Entry<PackageInfo, List<ColorResource>> r0 = r52.next();
            List<ColorResource> r1 = r0.getValue();
            Collections.sort(r1, ColorResourcesTableCreator.access$400());
            this.packageChunks.add(new PackageChunk(r0.getKey(), r1));
            goto L4
        L6:
            this.header = new ResChunkHeader(ColorResourcesTableCreator.HEADER_TYPE_RES_TABLE, HEADER_SIZE, getOverallSize());
        }

        private int getOverallSize() {
            Iterator<PackageChunk> r0 = this.packageChunks.iterator();
            int r1 = 0;
        L4:
            if (r0.hasNext() == false) goto L7;
            r1 = r1 + r0.next().getChunkSize();
            goto L4
        L7:
            return (this.stringPool.getChunkSize() + 12) + r1;
        }

        public void writeTo(ByteArrayOutputStream r2) {
            this.header.writeTo(r2);
            r2.write(ColorResourcesTableCreator.access$500(this.packageCount));
            this.stringPool.writeTo(r2);
            Iterator<PackageChunk> r1 = this.packageChunks.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().writeTo(r2);
            goto L4
        }
    }

    public static class StringPoolChunk {
        private static final int FLAG_UTF8 = 256;
        private static final short HEADER_SIZE = 28;
        private static final int STYLED_SPAN_LIST_END = -1;
        private final int chunkSize;
        private final ResChunkHeader header;
        private final int stringCount;
        private final List<Integer> stringIndex;
        private final List<byte[]> strings;
        private final int stringsPaddingSize;
        private final int stringsStart;
        private final int styledSpanCount;
        private final List<Integer> styledSpanIndex;
        private final List<List<StringStyledSpan>> styledSpans;
        private final int styledSpansStart;
        private final boolean utf8Encode;

        public StringPoolChunk(boolean r9, String... r10) {
            this.stringIndex = new ArrayList();
            this.styledSpanIndex = new ArrayList();
            this.strings = new ArrayList();
            this.styledSpans = new ArrayList();
            this.utf8Encode = r9;
            int r92 = r10.length;
            int r0 = 0;
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r1 >= r92) goto L5;
            Pair<byte[], List<StringStyledSpan>> r3 = processString(r10[r1]);
            this.stringIndex.add(Integer.valueOf(r2));
            Object r4 = r3.first;
            r2 = r2 + ((byte[]) r4).length;
            this.strings.add((byte[]) r4);
            this.styledSpans.add((List) r3.second);
            r1 = r1 + 1;
            goto L3
        L5:
            Iterator<List<StringStyledSpan>> r93 = this.styledSpans.iterator();
            int r12 = 0;
        L7:
            if (r93.hasNext() == false) goto L13;
            List<StringStyledSpan> r32 = r93.next();
            Iterator<StringStyledSpan> r42 = r32.iterator();
        L10:
            if (r42.hasNext() == false) goto L12;
            StringStyledSpan r5 = r42.next();
            this.stringIndex.add(Integer.valueOf(r2));
            r2 = r2 + StringStyledSpan.access$700(r5).length;
            this.strings.add(StringStyledSpan.access$700(r5));
            goto L10
        L12:
            this.styledSpanIndex.add(Integer.valueOf(r12));
            r12 = r12 + ((r32.size() * 12) + 4);
            goto L7
        L13:
            int r94 = r2 % 4;
            if (r94 != 0) goto L16;
            int r95 = 0;
        L17:
            this.stringsPaddingSize = r95;
            int r33 = this.strings.size();
            this.stringCount = r33;
            this.styledSpanCount = this.strings.size() - r10.length;
            if ((this.strings.size() - r10.length) <= 0) goto L20;
            boolean r43 = true;
        L21:
            if (r43 == true) goto L23;
            this.styledSpanIndex.clear();
            this.styledSpans.clear();
        L23:
            int r34 = ((r33 * 4) + 28) + (this.styledSpanIndex.size() * 4);
            this.stringsStart = r34;
            int r22 = r2 + r95;
            if (r43 == false) goto L26;
            int r96 = r34 + r22;
        L27:
            this.styledSpansStart = r96;
            int r35 = r34 + r22;
            if (r43 == false) goto L30;
            r0 = r12;
        L30:
            int r36 = r35 + r0;
            this.chunkSize = r36;
            this.header = new ResChunkHeader(ColorResourcesTableCreator.HEADER_TYPE_STRING_POOL, HEADER_SIZE, r36);
            return;
        L26:
            r96 = 0;
            goto L27
        L20:
            r43 = false;
            goto L21
        L16:
            r95 = 4 - r94;
            goto L17
        }

        public StringPoolChunk(String... r2) {
            this(false, r2);
        }

        private Pair<byte[], List<StringStyledSpan>> processString(String r2) {
            if (this.utf8Encode == false) goto L5;
            byte[] r1 = ColorResourcesTableCreator.access$800(r2);
        L7:
            return new Pair(r1, Collections.EMPTY_LIST);
        L5:
            r1 = ColorResourcesTableCreator.access$900(r2);
            goto L7
        }

        public int getChunkSize() {
            return this.chunkSize;
        }

        public void writeTo(ByteArrayOutputStream r3) {
            this.header.writeTo(r3);
            r3.write(ColorResourcesTableCreator.access$500(this.stringCount));
            r3.write(ColorResourcesTableCreator.access$500(this.styledSpanCount));
            if (this.utf8Encode == false) goto L5;
            int r0 = 256;
        L6:
            r3.write(ColorResourcesTableCreator.access$500(r0));
            r3.write(ColorResourcesTableCreator.access$500(this.stringsStart));
            r3.write(ColorResourcesTableCreator.access$500(this.styledSpansStart));
            Iterator<Integer> r02 = this.stringIndex.iterator();
        L8:
            if (r02.hasNext() == false) goto L10;
            r3.write(ColorResourcesTableCreator.access$500(r02.next().intValue()));
            goto L8
        L10:
            Iterator<Integer> r03 = this.styledSpanIndex.iterator();
        L12:
            if (r03.hasNext() == false) goto L14;
            r3.write(ColorResourcesTableCreator.access$500(r03.next().intValue()));
            goto L12
        L14:
            Iterator<byte[]> r04 = this.strings.iterator();
        L16:
            if (r04.hasNext() == false) goto L18;
            r3.write(r04.next());
            goto L16
        L18:
            int r05 = this.stringsPaddingSize;
            if (r05 <= 0) goto L21;
            r3.write(new byte[r05]);
        L21:
            Iterator<List<StringStyledSpan>> r2 = this.styledSpans.iterator();
        L23:
            if (r2.hasNext() == false) goto L29;
            Iterator<StringStyledSpan> r06 = r2.next().iterator();
        L26:
            if (r06.hasNext() == false) goto L28;
            r06.next().writeTo(r3);
            goto L26
        L28:
            r3.write(ColorResourcesTableCreator.access$500(-1));
            goto L23
        L29:
            return;
        L5:
            r0 = 0;
            goto L6
        }
    }

    public static class StringStyledSpan {
        private int firstCharacterIndex;
        private int lastCharacterIndex;
        private int nameReference;
        private byte[] styleString;

        private StringStyledSpan() {
        }

        public static /* synthetic */ byte[] access$700(StringStyledSpan r0) {
            return r0.styleString;
        }

        public void writeTo(ByteArrayOutputStream r2) {
            r2.write(ColorResourcesTableCreator.access$500(this.nameReference));
            r2.write(ColorResourcesTableCreator.access$500(this.firstCharacterIndex));
            r2.write(ColorResourcesTableCreator.access$500(this.lastCharacterIndex));
        }
    }

    public static class TypeChunk {
        private static final byte CONFIG_SIZE = 64;
        private static final short HEADER_SIZE = 84;
        private static final int OFFSET_NO_ENTRY = -1;
        private final byte[] config;
        private final int entryCount;
        private final ResChunkHeader header;
        private final int[] offsetTable;
        private final ResEntry[] resEntries;

        public TypeChunk(List<ColorResource> r6, Set<Short> r7, int r8) {
            byte[] r1 = new byte[64];
            this.config = r1;
            this.entryCount = r8;
            short r2 = 0;
            r1[0] = CONFIG_SIZE;
            this.resEntries = new ResEntry[r6.size()];
            int r0 = 0;
        L4:
            if (r0 >= r6.size()) goto L6;
            this.resEntries[r0] = new ResEntry(r0, ColorResource.access$1400(r6.get(r0)));
            r0 = r0 + 1;
            goto L4
        L6:
            this.offsetTable = new int[r8];
            int r62 = 0;
        L7:
            if (r2 >= r8) goto L13;
            boolean r02 = r7.contains(Short.valueOf(r2));
            int[] r12 = this.offsetTable;
            if (r02 == false) goto L11;
            r12[r2] = r62;
            r62 = r62 + 16;
        L12:
            r2 = (short) (r2 + ColorResourcesTableCreator.HEADER_TYPE_STRING_POOL);
            goto L7
        L11:
            r12[r2] = -1;
            goto L12
        L13:
            this.header = new ResChunkHeader(ColorResourcesTableCreator.HEADER_TYPE_TYPE, HEADER_SIZE, getChunkSize());
        }

        private int getEntryStart() {
            return getOffsetTableSize() + 84;
        }

        private int getOffsetTableSize() {
            return this.offsetTable.length * 4;
        }

        public int getChunkSize() {
            return getEntryStart() + (this.resEntries.length * 16);
        }

        public void writeTo(ByteArrayOutputStream r6) {
            this.header.writeTo(r6);
            int r2 = 0;
            r6.write(new byte[]{ColorResourcesTableCreator.access$1300(), 0, 0, 0});
            r6.write(ColorResourcesTableCreator.access$500(this.entryCount));
            r6.write(ColorResourcesTableCreator.access$500(getEntryStart()));
            r6.write(this.config);
            int[] r0 = this.offsetTable;
            int r1 = r0.length;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            r6.write(ColorResourcesTableCreator.access$500(r0[r3]));
            r3 = r3 + 1;
            goto L3
        L5:
            ResEntry[] r5 = this.resEntries;
            int r02 = r5.length;
        L6:
            if (r2 >= r02) goto L8;
            r5[r2].writeTo(r6);
            r2 = r2 + 1;
            goto L6
        }
    }

    public static class TypeSpecChunk {
        private static final short HEADER_SIZE = 16;
        private static final int SPEC_PUBLIC = 1073741824;
        private final int entryCount;
        private final int[] entryFlags;
        private final ResChunkHeader header;
        private final TypeChunk typeChunk;

        public TypeSpecChunk(List<ColorResource> r6) {
            this.entryCount = ColorResource.access$000(r6.get(r6.size() - 1)) + ColorResourcesTableCreator.HEADER_TYPE_STRING_POOL;
            HashSet r0 = new HashSet();
            Iterator<ColorResource> r1 = r6.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r0.add(Short.valueOf(ColorResource.access$000(r1.next())));
            goto L4
        L6:
            this.entryFlags = new int[this.entryCount];
            short r12 = 0;
        L8:
            if (r12 >= this.entryCount) goto L13;
            if (r0.contains(Short.valueOf(r12)) == false) goto L12;
            this.entryFlags[r12] = 1073741824;
        L12:
            r12 = (short) (r12 + ColorResourcesTableCreator.HEADER_TYPE_STRING_POOL);
            goto L8
        L13:
            this.header = new ResChunkHeader(ColorResourcesTableCreator.HEADER_TYPE_TYPE_SPEC, HEADER_SIZE, getChunkSize());
            this.typeChunk = new TypeChunk(r6, r0, this.entryCount);
        }

        private int getChunkSize() {
            return (this.entryCount * 4) + 16;
        }

        public int getChunkSizeWithTypeChunk() {
            return getChunkSize() + this.typeChunk.getChunkSize();
        }

        public void writeTo(ByteArrayOutputStream r5) {
            this.header.writeTo(r5);
            int r2 = 0;
            r5.write(new byte[]{ColorResourcesTableCreator.access$1300(), 0, 0, 0});
            r5.write(ColorResourcesTableCreator.access$500(this.entryCount));
            int[] r0 = this.entryFlags;
            int r1 = r0.length;
        L3:
            if (r2 >= r1) goto L5;
            r5.write(ColorResourcesTableCreator.access$500(r0[r2]));
            r2 = r2 + 1;
            goto L3
        L5:
            this.typeChunk.writeTo(r5);
        }
    }

    static {
        ANDROID_PACKAGE_INFO = new PackageInfo(1, "android");
        COLOR_RESOURCE_COMPARATOR = new AnonymousClass1();
    }

    private ColorResourcesTableCreator() {
    }

    public static /* synthetic */ byte[] access$1200(char r0) {
        return charToByteArray(r0);
    }

    public static /* synthetic */ byte access$1300() {
        return typeIdColor;
    }

    public static /* synthetic */ Comparator access$400() {
        return COLOR_RESOURCE_COMPARATOR;
    }

    public static /* synthetic */ byte[] access$500(int r0) {
        return intToByteArray(r0);
    }

    public static /* synthetic */ byte[] access$600(short r0) {
        return shortToByteArray(r0);
    }

    public static /* synthetic */ byte[] access$800(String r0) {
        return stringToByteArrayUtf8(r0);
    }

    public static /* synthetic */ byte[] access$900(String r0) {
        return stringToByteArray(r0);
    }

    private static byte[] charToByteArray(char r3) {
        return new byte[]{(byte) (r3 & 255), (byte) ((r3 >> '\b') & 255)};
    }

    private static byte[] concat(byte[]... r7) {
        int r0 = r7.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r3 = r3 + r7[r2].length;
        r2 = r2 + 1;
        goto L3
    L5:
        byte[] r02 = new byte[r3];
        int r22 = r7.length;
        int r32 = 0;
        int r4 = 0;
    L6:
        if (r32 >= r22) goto L8;
        byte[] r5 = r7[r32];
        System.arraycopy(r5, 0, r02, r4, r5.length);
        r4 = r4 + r5.length;
        r32 = r32 + 1;
        goto L6
    L8:
        return r02;
    }

    public static byte[] create(Context r9, Map<Integer, Integer> r10) {
        if (r10.entrySet().isEmpty() == true) goto L29;
        PackageInfo r0 = new PackageInfo(127, r9.getPackageName());
        HashMap r2 = new HashMap();
        Iterator<Map.Entry<Integer, Integer>> r102 = r10.entrySet().iterator();
        ColorResource r4 = null;
    L6:
        if (r102.hasNext() == false) goto L23;
        Map.Entry<Integer, Integer> r42 = r102.next();
        ColorResource r5 = new ColorResource(r42.getKey().intValue(), r9.getResources().getResourceEntryName(r42.getKey().intValue()), r42.getValue().intValue());
        if (r9.getResources().getResourceTypeName(r42.getKey().intValue()).equals(RESOURCE_TYPE_NAME_COLOR) == false) goto L21;
        if (ColorResource.access$300(r5) != 1) goto L13;
        PackageInfo r43 = ANDROID_PACKAGE_INFO;
    L16:
        if (r2.containsKey(r43) == true) goto L18;
        r2.put(r43, new ArrayList());
    L18:
        ((List) r2.get(r43)).add(r5);
        r4 = r5;
        goto L6
    L13:
        if (ColorResource.access$300(r5) != Byte.MAX_VALUE) goto L19;
        r43 = r0;
        goto L16
    L19:
        IntegerType$$ExternalSyntheticBUOutline0.m("Not supported with unknown package id: ", ColorResource.access$300(r5));
        return null;
    L21:
        Union$$ExternalSyntheticBUOutline0.m("Non color resource found: name=", ColorResource.access$100(r5), ", typeId=", Integer.toHexString(ColorResource.access$200(r5) & UByte.MAX_VALUE));
        return null;
    L23:
        byte r92 = ColorResource.access$200(r4);
        typeIdColor = r92;
        if (r92 == 0) goto L27;
        ByteArrayOutputStream r93 = new ByteArrayOutputStream();
        new ResTable(r2).writeTo(r93);
        return r93.toByteArray();
    L27:
        f$$ExternalSyntheticBUOutline1.m("No color resources found for harmonization.");
        return null;
    L29:
        f$$ExternalSyntheticBUOutline1.m("No color resources provided for harmonization.");
        return null;
    }

    private static byte[] encodeLengthUtf8(short r5) {
        if (r5 <= 127) goto L7;
        return new byte[]{(byte) ((127 & (r5 >> 8)) | 128), (byte) (r5 & 255)};
    L7:
        return new byte[]{(byte) (r5 & 255)};
    }

    private static byte[] intToByteArray(int r5) {
        return new byte[]{(byte) (r5 & 255), (byte) ((r5 >> 8) & 255), (byte) ((r5 >> 16) & 255), (byte) ((r5 >> 24) & 255)};
    }

    private static byte[] shortToByteArray(short r3) {
        return new byte[]{(byte) (r3 & 255), (byte) ((r3 >> 8) & 255)};
    }

    private static byte[] stringToByteArray(String r9) {
        char[] r92 = r9.toCharArray();
        int r0 = r92.length * 2;
        byte[] r1 = new byte[r0 + 4];
        byte[] r2 = shortToByteArray((short) r92.length);
        r1[0] = r2[0];
        r1[1] = r2[1];
        int r22 = 0;
    L4:
        if (r22 >= r92.length) goto L6;
        byte[] r5 = charToByteArray(r92[r22]);
        int r6 = r22 * 2;
        r1[r6 + 2] = r5[0];
        r1[r6 + 3] = r5[1];
        r22 = r22 + 1;
        goto L4
    L6:
        r1[r0 + 2] = 0;
        r1[r0 + 3] = 0;
        return r1;
    }

    private static byte[] stringToByteArrayUtf8(String r4) {
        byte[] r0 = r4.getBytes(StandardCharsets.UTF_8);
        return concat(new byte[][]{encodeLengthUtf8((short) r4.length()), encodeLengthUtf8((short) r0.length), r0, new byte[]{0}});
    }
}
