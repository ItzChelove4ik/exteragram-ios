package androidx.profileinstaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
abstract class ProfileTranscoder {
    static final byte[] MAGIC_PROF = null;
    static final byte[] MAGIC_PROFM = null;

    static {
        MAGIC_PROF = new byte[]{112, 114, 111, 0};
        MAGIC_PROFM = new byte[]{112, 114, 109, 0};
    }

    private static int computeMethodFlags(DexProfileData r2) {
        Iterator<Map.Entry<Integer, Integer>> r22 = r2.methods.entrySet().iterator();
        int r0 = 0;
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = r0 | r22.next().getValue().intValue();
        goto L4
    L6:
        return r0;
    }

    private static byte[] createCompressibleBody(DexProfileData[] r8, byte[] r9) {
        int r0 = r8.length;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L5;
        DexProfileData r4 = r8[r2];
        r3 = r3 + ((((Encoding.utf8Length(generateDexKey(r4.apkName, r4.dexName, r9)) + 16) + (r4.classSetSize * 2)) + r4.hotMethodRegionSize) + getMethodBitmapStorageSize(r4.numMethodIds));
        r2 = r2 + 1;
        goto L3
    L5:
        ByteArrayOutputStream r02 = new ByteArrayOutputStream(r3);
        if (Arrays.equals(r9, ProfileVersion.V009_O_MR1) == false) goto L10;
        int r22 = r8.length;
    L8:
        if (r1 >= r22) goto L17;
        DexProfileData r42 = r8[r1];
        writeLineHeader(r02, r42, generateDexKey(r42.apkName, r42.dexName, r9));
        writeLineData(r02, r42);
        r1 = r1 + 1;
    L17:
        if (r02.size() != r3) goto L21;
        return r02.toByteArray();
    L21:
        throw Encoding.error("The bytes saved do not match expectation. actual=" + r02.size() + " expected=" + r3);
    L10:
        int r23 = r8.length;
        int r43 = 0;
    L11:
        if (r43 >= r23) goto L13;
        DexProfileData r5 = r8[r43];
        writeLineHeader(r02, r5, generateDexKey(r5.apkName, r5.dexName, r9));
        r43 = r43 + 1;
        goto L11
    L13:
        int r92 = r8.length;
    L14:
        if (r1 >= r92) goto L17;
        writeLineData(r02, r8[r1]);
        r1 = r1 + 1;
        goto L14
    }

    private static WritableFileSection createCompressibleClassSection(DexProfileData[] r5) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        int r1 = 0;
        int r2 = 0;
    L20:
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L17
    L19:
        throw th;
    L17:
        th = move-exception;
        th.addSuppressed(th);
        goto L19
    L4:
        if (r1 >= r5.length) goto L8;
        DexProfileData r3 = r5[r1];     // Catch: Throwable -> L6
        Encoding.writeUInt16(r0, r1);     // Catch: Throwable -> L6
        Encoding.writeUInt16(r0, r3.classSetSize);     // Catch: Throwable -> L6
        r2 = (r2 + 4) + (r3.classSetSize * 2);     // Catch: Throwable -> L6
        writeClasses(r0, r3);     // Catch: Throwable -> L6
        r1 = r1 + 1;     // Catch: Throwable -> L6
        goto L20
    L8:
        byte[] r52 = r0.toByteArray();     // Catch: Throwable -> L6
        if (r2 != r52.length) goto L14;
        WritableFileSection r12 = new WritableFileSection(FileSectionType.CLASSES, r2, r52, true);     // Catch: Throwable -> L6
        r0.close();
        return r12;
    L14:
        throw Encoding.error("Expected size " + r2 + ", does not match actual size " + r52.length);     // Catch: Throwable -> L6
    }

    private static WritableFileSection createCompressibleMethodsSection(DexProfileData[] r9) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        int r1 = 0;
        int r2 = 0;
    L20:
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L17
    L19:
        throw th;
    L17:
        th = move-exception;
        th.addSuppressed(th);
        goto L19
    L4:
        if (r1 >= r9.length) goto L8;
        DexProfileData r3 = r9[r1];     // Catch: Throwable -> L6
        int r4 = computeMethodFlags(r3);     // Catch: Throwable -> L6
        byte[] r5 = createMethodBitmapRegionForS(r4, r3);     // Catch: Throwable -> L6
        byte[] r32 = createMethodsWithInlineCaches(r3);     // Catch: Throwable -> L6
        Encoding.writeUInt16(r0, r1);     // Catch: Throwable -> L6
        int r6 = (r5.length + 2) + r32.length;     // Catch: Throwable -> L6
        Encoding.writeUInt32(r0, r6);     // Catch: Throwable -> L6
        Encoding.writeUInt16(r0, r4);     // Catch: Throwable -> L6
        r0.write(r5);     // Catch: Throwable -> L6
        r0.write(r32);     // Catch: Throwable -> L6
        r2 = (r2 + 6) + r6;     // Catch: Throwable -> L6
        r1 = r1 + 1;     // Catch: Throwable -> L6
        goto L20
    L8:
        byte[] r92 = r0.toByteArray();     // Catch: Throwable -> L6
        if (r2 != r92.length) goto L14;
        WritableFileSection r12 = new WritableFileSection(FileSectionType.METHODS, r2, r92, true);     // Catch: Throwable -> L6
        r0.close();
        return r12;
    L14:
        throw Encoding.error("Expected size " + r2 + ", does not match actual size " + r92.length);     // Catch: Throwable -> L6
    }

    private static byte[] createMethodBitmapRegionForS(int r1, DexProfileData r2) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        writeMethodBitmapForS(r0, r1, r2);     // Catch: Throwable -> L6
        byte[] r12 = r0.toByteArray();     // Catch: Throwable -> L6
        r0.close();
        return r12;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }

    private static byte[] createMethodsWithInlineCaches(DexProfileData r1) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        writeMethodsWithInlineCaches(r0, r1);     // Catch: Throwable -> L6
        byte[] r12 = r0.toByteArray();     // Catch: Throwable -> L6
        r0.close();
        return r12;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }

    private static String enforceSeparator(String r3, String r4) {
        if ("!".equals(r4) == false) goto L7;
        return r3.replace(":", "!");
    L7:
        if (":".equals(r4) == true) goto L9;
        return r3;
    L9:
        return r3.replace("!", ":");
    }

    private static String extractKey(String r1) {
        int r0 = r1.indexOf("!");
        if (r0 >= 0) goto L5;
        r0 = r1.indexOf(":");
    L5:
        if (r0 > 0) goto L7;
        return r1;
    L7:
        return r1.substring(r0 + 1);
    }

    private static DexProfileData findByDexName(DexProfileData[] r3, String r4) {
        if (r3.length > 0) goto L5;
        return null;
    L5:
        String r42 = extractKey(r4);
        int r0 = 0;
    L7:
        if (r0 >= r3.length) goto L13;
        if (r3[r0].dexName.equals(r42) == true) goto L11;
        r0 = r0 + 1;
        goto L7
    L11:
        return r3[r0];
    L13:
        return null;
    }

    private static String generateDexKey(String r2, String r3, byte[] r4) {
        String r0 = ProfileVersion.dexKeySeparator(r4);
        if (r2.length() > 0) goto L7;
        return enforceSeparator(r3, r0);
    L7:
        if (r3.equals("classes.dex") == false) goto L10;
        return r2;
    L10:
        if (r3.contains("!") == true) goto L20;
        if (r3.contains(":") == true) goto L20;
        if (r3.endsWith(".apk") == false) goto L18;
        return r3;
    L18:
        return r2 + ProfileVersion.dexKeySeparator(r4) + r3;
    L20:
        return enforceSeparator(r3, r0);
    }

    private static int getMethodBitmapStorageSize(int r0) {
        return roundUpToByte(r0 * 2) / 8;
    }

    private static int getMethodBitmapStorageSizeForS(int r0, int r1) {
        return roundUpToByte(Integer.bitCount(r0 & (-2)) * r1) / 8;
    }

    private static int methodFlagBitmapIndex(int r1, int r2, int r3) {
        if (r1 == 1) goto L14;
        if (r1 != 2) goto L7;
        return r2;
    L7:
        if (r1 != 4) goto L11;
        return r2 + r3;
    L11:
        throw Encoding.error("Unexpected flag: " + r1);
    L14:
        throw Encoding.error("HOT methods are not stored in the bitmap");
    }

    private static int[] readClasses(InputStream r4, int r5) {
        int[] r0 = new int[r5];
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r5) goto L5;
        r2 = r2 + Encoding.readUInt16(r4);
        r0[r1] = r2;
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    private static int readFlagsFromBitmap(BitSet r2, int r3, int r4) {
        int r0 = 2;
        if (r2.get(methodFlagBitmapIndex(2, r3, r4)) == true) goto L7;
        r0 = 0;
    L7:
        if (r2.get(methodFlagBitmapIndex(4, r3, r4)) == true) goto L9;
        return r0;
    L9:
        return r0 | 4;
    }

    public static byte[] readHeader(InputStream r1, byte[] r2) {
        if (Arrays.equals(r2, Encoding.read(r1, r2.length)) == false) goto L7;
        return Encoding.read(r1, ProfileVersion.V010_P.length);
    L7:
        throw Encoding.error("Invalid magic");
    }

    private static void readHotMethodRegion(InputStream r5, DexProfileData r6) {
        int r0 = r5.available() - r6.hotMethodRegionSize;
        int r1 = 0;
    L4:
        if (r5.available() <= r0) goto L9;
        r1 = r1 + Encoding.readUInt16(r5);
        r6.methods.put(Integer.valueOf(r1), 1);
        int r2 = Encoding.readUInt16(r5);
    L6:
        if (r2 <= 0) goto L4;
        skipInlineCache(r5);
        r2 = r2 - 1;
        goto L6
    L9:
        if (r5.available() != r0) goto L12;
        return;
    L12:
        throw Encoding.error("Read too much data during profile line parse");
    }

    public static DexProfileData[] readMeta(InputStream r1, byte[] r2, byte[] r3, DexProfileData[] r4) {
        if (Arrays.equals(r2, ProfileVersion.METADATA_V001_N) == false) goto L11;
        if (Arrays.equals(ProfileVersion.V015_S, r3) == true) goto L9;
        return readMetadata001(r1, r2, r4);
    L9:
        throw Encoding.error("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
    L11:
        if (Arrays.equals(r2, ProfileVersion.METADATA_V002) == false) goto L15;
        return readMetadataV002(r1, r3, r4);
    L15:
        throw Encoding.error("Unsupported meta version");
    }

    public static DexProfileData[] readMetadata001(InputStream r4, byte[] r5, DexProfileData[] r6) {
        if (Arrays.equals(r5, ProfileVersion.METADATA_V001_N) == false) goto L19;
        int r52 = Encoding.readUInt8(r4);
        long r0 = Encoding.readUInt32(r4);
        byte[] r02 = Encoding.readCompressed(r4, (int) Encoding.readUInt32(r4), (int) r0);
        if (r4.read() > 0) goto L17;
        ByteArrayInputStream r42 = new ByteArrayInputStream(r02);
        DexProfileData[] r53 = readMetadataForNBody(r42, r52, r6);     // Catch: Throwable -> L10
        r42.close();
        return r53;
    L10:
        th = move-exception;
        r42.close();     // Catch: Throwable -> L13
    L15:
        throw th;
    L13:
        th = move-exception;
        th.addSuppressed(th);
        goto L15
    L17:
        throw Encoding.error("Content found after the end of file");
    L19:
        throw Encoding.error("Unsupported meta version");
    }

    private static DexProfileData[] readMetadataForNBody(InputStream r6, int r7, DexProfileData[] r8) {
        int r1 = 0;
        if (r6.available() != 0) goto L7;
        return new DexProfileData[0];
    L7:
        if (r7 != r8.length) goto L19;
        String[] r0 = new String[r7];
        int[] r2 = new int[r7];
        int r3 = 0;
    L9:
        if (r3 >= r7) goto L11;
        int r4 = Encoding.readUInt16(r6);
        r2[r3] = Encoding.readUInt16(r6);
        r0[r3] = Encoding.readString(r6, r4);
        r3 = r3 + 1;
    L11:
        if (r1 >= r7) goto L17;
        DexProfileData r32 = r8[r1];
        if (r32.dexName.equals(r0[r1]) == false) goto L16;
        int r42 = r2[r1];
        r32.classSetSize = r42;
        r32.classes = readClasses(r6, r42);
        r1 = r1 + 1;
        goto L11
    L16:
        throw Encoding.error("Order of dexfiles in metadata did not match baseline");
    L17:
        return r8;
    L19:
        throw Encoding.error("Mismatched number of dex files found in metadata");
    }

    public static DexProfileData[] readMetadataV002(InputStream r5, byte[] r6, DexProfileData[] r7) {
        int r0 = Encoding.readUInt16(r5);
        long r1 = Encoding.readUInt32(r5);
        byte[] r12 = Encoding.readCompressed(r5, (int) Encoding.readUInt32(r5), (int) r1);
        if (r5.read() > 0) goto L15;
        ByteArrayInputStream r52 = new ByteArrayInputStream(r12);
        DexProfileData[] r62 = readMetadataV002Body(r52, r6, r0, r7);     // Catch: Throwable -> L8
        r52.close();
        return r62;
    L8:
        th = move-exception;
        r52.close();     // Catch: Throwable -> L11
    L13:
        throw th;
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    L15:
        throw Encoding.error("Content found after the end of file");
    }

    private static DexProfileData[] readMetadataV002Body(InputStream r6, byte[] r7, int r8, DexProfileData[] r9) {
        int r1 = 0;
        if (r6.available() != 0) goto L7;
        return new DexProfileData[0];
    L7:
        if (r8 != r9.length) goto L19;
    L8:
        if (r1 >= r8) goto L17;
        Encoding.readUInt16(r6);
        String r0 = Encoding.readString(r6, Encoding.readUInt16(r6));
        long r2 = Encoding.readUInt32(r6);
        int r4 = Encoding.readUInt16(r6);
        DexProfileData r5 = findByDexName(r9, r0);
        if (r5 == null) goto L16;
        r5.mTypeIdCount = r2;
        int[] r02 = readClasses(r6, r4);
        if (Arrays.equals(r7, ProfileVersion.V001_N) == false) goto L14;
        r5.classSetSize = r4;
        r5.classes = r02;
    L14:
        r1 = r1 + 1;
        goto L8
    L16:
        throw Encoding.error("Missing profile key: " + r0);
    L17:
        return r9;
    L19:
        throw Encoding.error("Mismatched number of dex files found in metadata");
    }

    private static void readMethodBitmap(InputStream r6, DexProfileData r7) {
        BitSet r62 = BitSet.valueOf(Encoding.read(r6, Encoding.bitsToBytes(r7.numMethodIds * 2)));
        int r1 = 0;
    L3:
        int r2 = r7.numMethodIds;
        if (r1 >= r2) goto L12;
        int r22 = readFlagsFromBitmap(r62, r1, r2);
        if (r22 == 0) goto L11;
        Integer r3 = r7.methods.get(Integer.valueOf(r1));
        if (r3 != null) goto L10;
        r3 = 0;
    L10:
        r7.methods.put(Integer.valueOf(r1), Integer.valueOf(r22 | r3.intValue()));
    L11:
        r1 = r1 + 1;
        goto L3
    }

    public static DexProfileData[] readProfile(InputStream r4, byte[] r5, String r6) {
        if (Arrays.equals(r5, ProfileVersion.V010_P) == false) goto L19;
        int r52 = Encoding.readUInt8(r4);
        long r0 = Encoding.readUInt32(r4);
        byte[] r02 = Encoding.readCompressed(r4, (int) Encoding.readUInt32(r4), (int) r0);
        if (r4.read() > 0) goto L17;
        ByteArrayInputStream r42 = new ByteArrayInputStream(r02);
        DexProfileData[] r53 = readUncompressedBody(r42, r6, r52);     // Catch: Throwable -> L10
        r42.close();
        return r53;
    L10:
        th = move-exception;
        r42.close();     // Catch: Throwable -> L13
    L15:
        throw th;
    L13:
        th = move-exception;
        th.addSuppressed(th);
        goto L15
    L17:
        throw Encoding.error("Content found after the end of file");
    L19:
        throw Encoding.error("Unsupported version");
    }

    private static DexProfileData[] readUncompressedBody(InputStream r18, String r19, int r20) {
        int r3 = 0;
        if (r18.available() == 0) goto L5;
        DexProfileData[] r2 = new DexProfileData[r20];
        int r4 = 0;
    L7:
        if (r4 >= r20) goto L9;
        int r5 = Encoding.readUInt16(r18);
        int r13 = Encoding.readUInt16(r18);
        long r6 = Encoding.readUInt32(r18);
        r2[r4] = new DexProfileData(r19, Encoding.readString(r18, r5), Encoding.readUInt32(r18), 0, r13, (int) r6, (int) Encoding.readUInt32(r18), new int[r13], new TreeMap());
        r4 = r4 + 1;
    L9:
        if (r3 >= r20) goto L11;
        DexProfileData r42 = r2[r3];
        readHotMethodRegion(r18, r42);
        r42.classes = readClasses(r18, r42.classSetSize);
        readMethodBitmap(r18, r42);
        r3 = r3 + 1;
        goto L9
    L11:
        return r2;
    L5:
        return new DexProfileData[0];
    }

    private static int roundUpToByte(int r0) {
        return (r0 + 7) & (-8);
    }

    private static void setMethodBitmapBit(byte[] r1, int r2, int r3, DexProfileData r4) {
        int r22 = methodFlagBitmapIndex(r2, r3, r4.numMethodIds);
        int r32 = r22 / 8;
        int r23 = 1 << (r22 % 8);
        r1[r32] = (byte) (r23 | r1[r32]);
    }

    private static void skipInlineCache(InputStream r2) {
        Encoding.readUInt16(r2);
        int r0 = Encoding.readUInt8(r2);
        if (r0 != 6) goto L6;
        return;
    L6:
        if (r0 == 7) goto L16;
    L8:
        if (r0 <= 0) goto L17;
        Encoding.readUInt8(r2);
        int r1 = Encoding.readUInt8(r2);
    L10:
        if (r1 <= 0) goto L12;
        Encoding.readUInt16(r2);
        r1 = r1 - 1;
        goto L10
    L12:
        r0 = r0 - 1;
        goto L8
    L17:
        return;
    }

    public static boolean transcodeAndWriteBody(OutputStream r2, byte[] r3, DexProfileData[] r4) {
        if (Arrays.equals(r3, ProfileVersion.V015_S) == false) goto L7;
        writeProfileForS(r2, r4);
        return true;
    L7:
        if (Arrays.equals(r3, ProfileVersion.V010_P) == false) goto L11;
        writeProfileForP(r2, r4);
        return true;
    L11:
        if (Arrays.equals(r3, ProfileVersion.V005_O) == false) goto L15;
        writeProfileForO(r2, r4);
        return true;
    L15:
        if (Arrays.equals(r3, ProfileVersion.V009_O_MR1) == false) goto L19;
        writeProfileForO_MR1(r2, r4);
        return true;
    L19:
        if (Arrays.equals(r3, ProfileVersion.V001_N) == false) goto L22;
        writeProfileForN(r2, r4);
        return true;
    L22:
        return false;
    }

    private static void writeClasses(OutputStream r4, DexProfileData r5) {
        int[] r52 = r5.classes;
        int r0 = r52.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        int r3 = r52[r1];
        Encoding.writeUInt16(r4, r3 - r2);
        r1 = r1 + 1;
        r2 = r3;
        goto L3
    }

    private static WritableFileSection writeDexFileSection(DexProfileData[] r7) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        Encoding.writeUInt16(r0, r7.length);     // Catch: Throwable -> L7
        int r2 = 2;
        int r3 = 0;
    L5:
        if (r3 >= r7.length) goto L9;
        DexProfileData r4 = r7[r3];     // Catch: Throwable -> L7
        Encoding.writeUInt32(r0, r4.dexChecksum);     // Catch: Throwable -> L7
        Encoding.writeUInt32(r0, r4.mTypeIdCount);     // Catch: Throwable -> L7
        Encoding.writeUInt32(r0, r4.numMethodIds);     // Catch: Throwable -> L7
        String r42 = generateDexKey(r4.apkName, r4.dexName, ProfileVersion.V015_S);     // Catch: Throwable -> L7
        int r5 = Encoding.utf8Length(r42);     // Catch: Throwable -> L7
        Encoding.writeUInt16(r0, r5);     // Catch: Throwable -> L7
        r2 = (r2 + 14) + r5;     // Catch: Throwable -> L7
        Encoding.writeString(r0, r42);     // Catch: Throwable -> L7
        r3 = r3 + 1;     // Catch: Throwable -> L7
        goto L5
    L9:
        byte[] r72 = r0.toByteArray();     // Catch: Throwable -> L7
        if (r2 != r72.length) goto L15;
        WritableFileSection r32 = new WritableFileSection(FileSectionType.DEX_FILES, r2, r72, false);     // Catch: Throwable -> L7
        r0.close();
        return r32;
    L15:
        throw Encoding.error("Expected size " + r2 + ", does not match actual size " + r72.length);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L18
    L20:
        throw th;
    L18:
        th = move-exception;
        th.addSuppressed(th);
        goto L20
    }

    public static void writeHeader(OutputStream r1, byte[] r2) {
        r1.write(MAGIC_PROF);
        r1.write(r2);
    }

    private static void writeLineData(OutputStream r0, DexProfileData r1) {
        writeMethodsWithInlineCaches(r0, r1);
        writeClasses(r0, r1);
        writeMethodBitmap(r0, r1);
    }

    private static void writeLineHeader(OutputStream r2, DexProfileData r3, String r4) {
        Encoding.writeUInt16(r2, Encoding.utf8Length(r4));
        Encoding.writeUInt16(r2, r3.classSetSize);
        Encoding.writeUInt32(r2, r3.hotMethodRegionSize);
        Encoding.writeUInt32(r2, r3.dexChecksum);
        Encoding.writeUInt32(r2, r3.numMethodIds);
        Encoding.writeString(r2, r4);
    }

    private static void writeMethodBitmap(OutputStream r5, DexProfileData r6) {
        byte[] r0 = new byte[getMethodBitmapStorageSize(r6.numMethodIds)];
        Iterator<Map.Entry<Integer, Integer>> r1 = r6.methods.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        Map.Entry<Integer, Integer> r2 = r1.next();
        int r3 = r2.getKey().intValue();
        int r22 = r2.getValue().intValue();
        if ((r22 & 2) == 0) goto L9;
        setMethodBitmapBit(r0, 2, r3, r6);
    L9:
        if ((r22 & 4) == 0) goto L4;
        setMethodBitmapBit(r0, 4, r3, r6);
        goto L4
    L11:
        r5.write(r0);
    }

    private static void writeMethodBitmapForS(OutputStream r10, int r11, DexProfileData r12) {
        byte[] r0 = new byte[getMethodBitmapStorageSizeForS(r11, r12.numMethodIds)];
        Iterator<Map.Entry<Integer, Integer>> r1 = r12.methods.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L17;
        Map.Entry<Integer, Integer> r2 = r1.next();
        int r3 = r2.getKey().intValue();
        int r22 = r2.getValue().intValue();
        int r5 = 0;
        int r6 = 1;
    L7:
        if (r6 > 4) goto L4;
        if (r6 == 1) goto L9;
        if ((r6 & r11) == 0) goto L9;
        if ((r6 & r22) != r6) goto L16;
        int r7 = (r12.numMethodIds * r5) + r3;
        int r8 = r7 / 8;
        r0[r8] = (byte) ((1 << (r7 % 8)) | r0[r8]);
    L16:
        r5 = r5 + 1;
    L9:
        r6 = r6 << 1;
        goto L7
    L17:
        r10.write(r0);
    }

    private static void writeMethodsWithInlineCaches(OutputStream r4, DexProfileData r5) {
        Iterator<Map.Entry<Integer, Integer>> r52 = r5.methods.entrySet().iterator();
        int r1 = 0;
    L4:
        if (r52.hasNext() == false) goto L9;
        Map.Entry<Integer, Integer> r2 = r52.next();
        int r3 = r2.getKey().intValue();
        if ((r2.getValue().intValue() & 1) == 0) goto L4;
        Encoding.writeUInt16(r4, r3 - r1);
        Encoding.writeUInt16(r4, 0);
        r1 = r3;
        goto L4
    }

    private static void writeProfileForN(OutputStream r7, DexProfileData[] r8) {
        Encoding.writeUInt16(r7, r8.length);
        int r0 = r8.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        DexProfileData r3 = r8[r2];
        String r4 = generateDexKey(r3.apkName, r3.dexName, ProfileVersion.V001_N);
        Encoding.writeUInt16(r7, Encoding.utf8Length(r4));
        Encoding.writeUInt16(r7, r3.methods.size());
        Encoding.writeUInt16(r7, r3.classes.length);
        Encoding.writeUInt32(r7, r3.dexChecksum);
        Encoding.writeString(r7, r4);
        Iterator<Integer> r42 = r3.methods.keySet().iterator();
    L6:
        if (r42.hasNext() == false) goto L8;
        Encoding.writeUInt16(r7, r42.next().intValue());
        goto L6
    L8:
        int[] r32 = r3.classes;
        int r43 = r32.length;
        int r5 = 0;
    L9:
        if (r5 >= r43) goto L11;
        Encoding.writeUInt16(r7, r32[r5]);
        r5 = r5 + 1;
        goto L9
    L11:
        r2 = r2 + 1;
        goto L3
    }

    private static void writeProfileForO(OutputStream r8, DexProfileData[] r9) {
        Encoding.writeUInt8(r8, r9.length);
        int r0 = r9.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        DexProfileData r3 = r9[r2];
        int r4 = r3.methods.size() * 4;
        String r5 = generateDexKey(r3.apkName, r3.dexName, ProfileVersion.V005_O);
        Encoding.writeUInt16(r8, Encoding.utf8Length(r5));
        Encoding.writeUInt16(r8, r3.classes.length);
        Encoding.writeUInt32(r8, r4);
        Encoding.writeUInt32(r8, r3.dexChecksum);
        Encoding.writeString(r8, r5);
        Iterator<Integer> r42 = r3.methods.keySet().iterator();
    L6:
        if (r42.hasNext() == false) goto L8;
        Encoding.writeUInt16(r8, r42.next().intValue());
        Encoding.writeUInt16(r8, 0);
        goto L6
    L8:
        int[] r32 = r3.classes;
        int r43 = r32.length;
        int r52 = 0;
    L9:
        if (r52 >= r43) goto L11;
        Encoding.writeUInt16(r8, r32[r52]);
        r52 = r52 + 1;
        goto L9
    L11:
        r2 = r2 + 1;
        goto L3
    }

    private static void writeProfileForO_MR1(OutputStream r1, DexProfileData[] r2) {
        byte[] r0 = createCompressibleBody(r2, ProfileVersion.V009_O_MR1);
        Encoding.writeUInt8(r1, r2.length);
        Encoding.writeCompressed(r1, r0);
    }

    private static void writeProfileForP(OutputStream r1, DexProfileData[] r2) {
        byte[] r0 = createCompressibleBody(r2, ProfileVersion.V010_P);
        Encoding.writeUInt8(r1, r2.length);
        Encoding.writeCompressed(r1, r0);
    }

    private static void writeProfileForS(OutputStream r0, DexProfileData[] r1) {
        writeProfileSections(r0, r1);
    }

    private static void writeProfileSections(OutputStream r10, DexProfileData[] r11) {
        ArrayList r0 = new ArrayList(3);
        ArrayList r2 = new ArrayList(3);
        r0.add(writeDexFileSection(r11));
        r0.add(createCompressibleClassSection(r11));
        r0.add(createCompressibleMethodsSection(r11));
        long r3 = ((ProfileVersion.V015_S.length + MAGIC_PROF.length) + 4) + (r0.size() * 16);
        Encoding.writeUInt32(r10, r0.size());
        int r112 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r0.size()) goto L12;
        WritableFileSection r5 = (WritableFileSection) r0.get(r1);
        Encoding.writeUInt32(r10, r5.mType.getValue());
        Encoding.writeUInt32(r10, r3);
        boolean r6 = r5.mNeedsCompression;
        byte[] r7 = r5.mContents;
        if (r6 == false) goto L9;
        long r52 = r7.length;
        byte[] r72 = Encoding.compress(r7);
        r2.add(r72);
        Encoding.writeUInt32(r10, r72.length);
        Encoding.writeUInt32(r10, r52);
        int r53 = r72.length;
    L8:
        r3 = r3 + r53;
        r1 = r1 + 1;
        goto L4
    L9:
        r2.add(r7);
        Encoding.writeUInt32(r10, r5.mContents.length);
        Encoding.writeUInt32(r10, 0);
        r53 = r5.mContents.length;
    L12:
        if (r112 >= r2.size()) goto L14;
        r10.write((byte[]) r2.get(r112));
        r112 = r112 + 1;
        goto L12
    }
}
