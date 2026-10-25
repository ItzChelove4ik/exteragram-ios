package com.sun.jna;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class ELFAnalyser {
    private static final int EF_ARM_ABI_FLOAT_HARD = 1024;
    private static final int EF_ARM_ABI_FLOAT_SOFT = 512;
    private static final int EI_CLASS_64BIT = 2;
    private static final int EI_DATA_BIG_ENDIAN = 2;
    private static final byte[] ELF_MAGIC = null;
    private static final int E_MACHINE_ARM = 40;
    private static final int SHN_UNDEF = 0;
    private static final int SHN_XINDEX = 65535;
    private boolean ELF;
    private boolean _64Bit;
    private boolean arm;
    private boolean armEabiAapcsVfp;
    private boolean armHardFloatFlag;
    private boolean armSoftFloatFlag;
    private boolean bigEndian;
    private final String filename;

    public static class ArmAeabiAttributesTag {
        public static final ArmAeabiAttributesTag ABI_FP_16bit_format = null;
        public static final ArmAeabiAttributesTag ABI_FP_denormal = null;
        public static final ArmAeabiAttributesTag ABI_FP_exceptions = null;
        public static final ArmAeabiAttributesTag ABI_FP_number_model = null;
        public static final ArmAeabiAttributesTag ABI_FP_optimization_goals = null;
        public static final ArmAeabiAttributesTag ABI_FP_rounding = null;
        public static final ArmAeabiAttributesTag ABI_FP_user_exceptions = null;
        public static final ArmAeabiAttributesTag ABI_HardFP_use = null;
        public static final ArmAeabiAttributesTag ABI_PCS_GOT_use = null;
        public static final ArmAeabiAttributesTag ABI_PCS_R9_use = null;
        public static final ArmAeabiAttributesTag ABI_PCS_RO_data = null;
        public static final ArmAeabiAttributesTag ABI_PCS_RW_data = null;
        public static final ArmAeabiAttributesTag ABI_PCS_wchar_t = null;
        public static final ArmAeabiAttributesTag ABI_VFP_args = null;
        public static final ArmAeabiAttributesTag ABI_WMMX_args = null;
        public static final ArmAeabiAttributesTag ABI_align8_preserved = null;
        public static final ArmAeabiAttributesTag ABI_align_needed = null;
        public static final ArmAeabiAttributesTag ABI_enum_size = null;
        public static final ArmAeabiAttributesTag ABI_optimization_goals = null;
        public static final ArmAeabiAttributesTag ARM_ISA_use = null;
        public static final ArmAeabiAttributesTag Advanced_SIMD_arch = null;
        public static final ArmAeabiAttributesTag CPU_arch = null;
        public static final ArmAeabiAttributesTag CPU_arch_profile = null;
        public static final ArmAeabiAttributesTag CPU_name = null;
        public static final ArmAeabiAttributesTag CPU_raw_name = null;
        public static final ArmAeabiAttributesTag CPU_unaligned_access = null;
        public static final ArmAeabiAttributesTag DIV_use = null;
        public static final ArmAeabiAttributesTag FP_HP_extension = null;
        public static final ArmAeabiAttributesTag FP_arch = null;
        public static final ArmAeabiAttributesTag File = null;
        public static final ArmAeabiAttributesTag MPextension_use = null;
        public static final ArmAeabiAttributesTag MPextension_use2 = null;
        public static final ArmAeabiAttributesTag PCS_config = null;
        public static final ArmAeabiAttributesTag Section = null;
        public static final ArmAeabiAttributesTag Symbol = null;
        public static final ArmAeabiAttributesTag T2EE_use = null;
        public static final ArmAeabiAttributesTag THUMB_ISA_use = null;
        public static final ArmAeabiAttributesTag Virtualization_use = null;
        public static final ArmAeabiAttributesTag WMMX_arch = null;
        public static final ArmAeabiAttributesTag also_compatible_with = null;
        public static final ArmAeabiAttributesTag compatibility = null;
        public static final ArmAeabiAttributesTag conformance = null;
        private static final Map<String, ArmAeabiAttributesTag> nameMap = null;
        public static final ArmAeabiAttributesTag nodefaults = null;
        private static final List<ArmAeabiAttributesTag> tags = null;
        private static final Map<Integer, ArmAeabiAttributesTag> valueMap = null;
        private final String name;
        private final ParameterType parameterType;
        private final int value;

        public enum ParameterType extends Enum<ParameterType> {
            private static final /* synthetic */ ParameterType[] $VALUES = null;
            public static final ParameterType NTBS = null;
            public static final ParameterType UINT32 = null;
            public static final ParameterType ULEB128 = null;

            private static /* synthetic */ ParameterType[] $values() {
                return new ParameterType[]{UINT32, NTBS, ULEB128};
            }

            static {
                UINT32 = new ParameterType("UINT32", 0);
                NTBS = new ParameterType("NTBS", 1);
                ULEB128 = new ParameterType("ULEB128", 2);
                $VALUES = $values();
            }

            ParameterType(String r1, int r2) {
            }

            public static ParameterType valueOf(String r1) {
                return (ParameterType) Enum.valueOf(ParameterType.class, r1);
            }

            public static ParameterType[] values() {
                return (ParameterType[]) $VALUES.clone();
            }
        }

        static {
            tags = new LinkedList();
            valueMap = new HashMap();
            nameMap = new HashMap();
            ParameterType r0 = ParameterType.UINT32;
            File = addTag(1, "File", r0);
            Section = addTag(2, "Section", r0);
            Symbol = addTag(3, "Symbol", r0);
            ParameterType r02 = ParameterType.NTBS;
            CPU_raw_name = addTag(4, "CPU_raw_name", r02);
            CPU_name = addTag(5, "CPU_name", r02);
            ParameterType r1 = ParameterType.ULEB128;
            CPU_arch = addTag(6, "CPU_arch", r1);
            CPU_arch_profile = addTag(7, "CPU_arch_profile", r1);
            ARM_ISA_use = addTag(8, "ARM_ISA_use", r1);
            THUMB_ISA_use = addTag(9, "THUMB_ISA_use", r1);
            FP_arch = addTag(10, "FP_arch", r1);
            WMMX_arch = addTag(11, "WMMX_arch", r1);
            Advanced_SIMD_arch = addTag(12, "Advanced_SIMD_arch", r1);
            PCS_config = addTag(13, "PCS_config", r1);
            ABI_PCS_R9_use = addTag(14, "ABI_PCS_R9_use", r1);
            ABI_PCS_RW_data = addTag(15, "ABI_PCS_RW_data", r1);
            ABI_PCS_RO_data = addTag(16, "ABI_PCS_RO_data", r1);
            ABI_PCS_GOT_use = addTag(17, "ABI_PCS_GOT_use", r1);
            ABI_PCS_wchar_t = addTag(18, "ABI_PCS_wchar_t", r1);
            ABI_FP_rounding = addTag(19, "ABI_FP_rounding", r1);
            ABI_FP_denormal = addTag(20, "ABI_FP_denormal", r1);
            ABI_FP_exceptions = addTag(21, "ABI_FP_exceptions", r1);
            ABI_FP_user_exceptions = addTag(22, "ABI_FP_user_exceptions", r1);
            ABI_FP_number_model = addTag(23, "ABI_FP_number_model", r1);
            ABI_align_needed = addTag(24, "ABI_align_needed", r1);
            ABI_align8_preserved = addTag(25, "ABI_align8_preserved", r1);
            ABI_enum_size = addTag(26, "ABI_enum_size", r1);
            ABI_HardFP_use = addTag(27, "ABI_HardFP_use", r1);
            ABI_VFP_args = addTag(28, "ABI_VFP_args", r1);
            ABI_WMMX_args = addTag(29, "ABI_WMMX_args", r1);
            ABI_optimization_goals = addTag(30, "ABI_optimization_goals", r1);
            ABI_FP_optimization_goals = addTag(31, "ABI_FP_optimization_goals", r1);
            compatibility = addTag(32, "compatibility", r02);
            CPU_unaligned_access = addTag(34, "CPU_unaligned_access", r1);
            FP_HP_extension = addTag(36, "FP_HP_extension", r1);
            ABI_FP_16bit_format = addTag(38, "ABI_FP_16bit_format", r1);
            MPextension_use = addTag(42, "MPextension_use", r1);
            DIV_use = addTag(44, "DIV_use", r1);
            nodefaults = addTag(64, "nodefaults", r1);
            also_compatible_with = addTag(65, "also_compatible_with", r02);
            conformance = addTag(67, "conformance", r02);
            T2EE_use = addTag(66, "T2EE_use", r1);
            Virtualization_use = addTag(68, "Virtualization_use", r1);
            MPextension_use2 = addTag(70, "MPextension_use", r1);
        }

        public ArmAeabiAttributesTag(int r1, String r2, ParameterType r3) {
            this.value = r1;
            this.name = r2;
            this.parameterType = r3;
        }

        private static ArmAeabiAttributesTag addTag(int r1, String r2, ParameterType r3) {
            ArmAeabiAttributesTag r0 = new ArmAeabiAttributesTag(r1, r2, r3);
            Map<Integer, ArmAeabiAttributesTag> r12 = valueMap;
            if (r12.containsKey(Integer.valueOf(r0.getValue())) == true) goto L5;
            r12.put(Integer.valueOf(r0.getValue()), r0);
        L5:
            Map<String, ArmAeabiAttributesTag> r13 = nameMap;
            if (r13.containsKey(r0.getName()) == true) goto L8;
            r13.put(r0.getName(), r0);
        L8:
            tags.add(r0);
            return r0;
        }

        public static ArmAeabiAttributesTag getByName(String r1) {
            return nameMap.get(r1);
        }

        public static ArmAeabiAttributesTag getByValue(int r3) {
            Map<Integer, ArmAeabiAttributesTag> r0 = valueMap;
            if (r0.containsKey(Integer.valueOf(r3)) == false) goto L7;
            return r0.get(Integer.valueOf(r3));
        L7:
            return new ArmAeabiAttributesTag(r3, "Unknown " + r3, getParameterType(r3));
        }

        private static ParameterType getParameterType(int r1) {
            ArmAeabiAttributesTag r0 = getByValue(r1);
            if (r0 != null) goto L11;
            if ((r1 % 2) != 0) goto L9;
            return ParameterType.ULEB128;
        L9:
            return ParameterType.NTBS;
        L11:
            return r0.getParameterType();
        }

        public static List<ArmAeabiAttributesTag> getTags() {
            return Collections.unmodifiableList(tags);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L9;
            return false;
        L9:
            if (getClass() == r5.getClass()) goto L12;
            return false;
        L12:
            if (this.value == ((ArmAeabiAttributesTag) r5).value) goto L14;
            return false;
        L14:
            return true;
        }

        public String getName() {
            return this.name;
        }

        public ParameterType getParameterType() {
            return this.parameterType;
        }

        public int getValue() {
            return this.value;
        }

        public int hashCode() {
            return 469 + this.value;
        }

        public String toString() {
            return this.name + " (" + this.value + ")";
        }
    }

    public static class ELFSectionHeaderEntry {
        private final long addr;
        private final long flags;
        private final int link;
        private String name;
        private final int nameOffset;
        private final long offset;
        private final long size;
        private final int type;

        public ELFSectionHeaderEntry(boolean r5, ByteBuffer r6) {
            this.nameOffset = r6.getInt(0);
            this.type = r6.getInt(4);
            if (r5 == false) goto L5;
            long r0 = r6.getLong(8);
        L6:
            this.flags = r0;
            if (r5 == false) goto L9;
            long r1 = r6.getLong(16);
        L10:
            this.addr = r1;
            int r12 = 24;
            if (r5 == false) goto L13;
            long r2 = r6.getLong(24);
        L14:
            this.offset = r2;
            if (r5 == false) goto L17;
            long r22 = r6.getLong(32);
        L18:
            this.size = r22;
            if (r5 == false) goto L21;
            r12 = 40;
        L21:
            this.link = r6.getInt(r12);
            return;
        L17:
            r22 = r6.getInt(20);
            goto L18
        L13:
            r2 = r6.getInt(16);
            goto L14
        L9:
            r1 = r6.getInt(12);
            goto L10
        L5:
            r0 = r6.getInt(8);
            goto L6
        }

        public long getAddr() {
            return this.addr;
        }

        public long getFlags() {
            return this.flags;
        }

        public int getLink() {
            return this.link;
        }

        public String getName() {
            return this.name;
        }

        public int getNameOffset() {
            return this.nameOffset;
        }

        public long getOffset() {
            return this.offset;
        }

        public long getSize() {
            return this.size;
        }

        public int getType() {
            return this.type;
        }

        public void setName(String r1) {
            this.name = r1;
        }

        public String toString() {
            return String.format("ELFSectionHeaderEntry{nameOffset=%1$d (0x%1$x), name=%2$s, type=%3$d (0x%3$x), flags=%4$d (0x%4$x), addr=%5$d (0x%5$x), offset=%6$d (0x%6$x), size=%7$d (0x%7$x), link=%8$d (0x%8$x)}", new Object[]{Integer.valueOf(this.nameOffset), this.name, Integer.valueOf(this.type), Long.valueOf(this.flags), Long.valueOf(this.addr), Long.valueOf(this.offset), Long.valueOf(this.size), Integer.valueOf(this.link)});
        }
    }

    public static class ELFSectionHeaders {
        private final List<ELFSectionHeaderEntry> entries;

        public ELFSectionHeaders(boolean r9, boolean r10, ByteBuffer r11, RandomAccessFile r12) {
            this.entries = new ArrayList();
            if (r9 == false) goto L5;
            long r0 = r11.getLong(40);
            short r2 = r11.getShort(58);
            short r3 = r11.getShort(60);
            int r112 = r11.getShort(62);
        L6:
            ByteBuffer r4 = ByteBuffer.allocate(r2);
            r12.getChannel().read(r4, r0);
            ELFSectionHeaderEntry r5 = new ELFSectionHeaderEntry(r9, r4);
            if (r3 == 0) goto L9;
        L11:
            int r42 = r3;
        L13:
            if (r112 != 65535) goto L15;
            r112 = r5.getLink();
        L15:
            int r43 = r42 * r2;
            if (r43 == 0) goto L41;
            if (r112 == 0) goto L46;
            ByteBuffer r44 = ByteBuffer.allocate(r43);
            if (r10 == false) goto L22;
            ByteOrder r52 = ByteOrder.BIG_ENDIAN;
        L23:
            r44.order(r52);
            r12.getChannel().read(r44, r0);
            int r02 = 0;
        L24:
            if (r02 >= r3) goto L26;
            r44.position(r02 * r2);
            ByteBuffer r1 = r44.slice();
            r1.order(r44.order());
            r1.limit(r2);
            this.entries.add(new ELFSectionHeaderEntry(r9, r1));
            r02 = r02 + 1;
            goto L24
        L26:
            ELFSectionHeaderEntry r92 = this.entries.get(r112);
            ByteBuffer r113 = ByteBuffer.allocate((int) r92.getSize());
            if (r10 == false) goto L29;
            ByteOrder r102 = ByteOrder.BIG_ENDIAN;
        L30:
            r113.order(r102);
            r12.getChannel().read(r113, r92.getOffset());
            r113.rewind();
            ByteArrayOutputStream r93 = new ByteArrayOutputStream(20);
            Iterator<ELFSectionHeaderEntry> r8 = this.entries.iterator();
        L32:
            if (r8.hasNext() == false) goto L47;
            ELFSectionHeaderEntry r103 = r8.next();
            r93.reset();
            r113.position(r103.getNameOffset());
        L35:
            if (r113.position() >= r113.limit()) goto L40;
            byte r122 = r113.get();
            if (r122 == 0) goto L40;
            r93.write(r122);
        L40:
            r103.setName(r93.toString("ASCII"));
            goto L32
        L47:
            return;
        L29:
            r102 = ByteOrder.LITTLE_ENDIAN;
            goto L30
        L22:
            r52 = ByteOrder.LITTLE_ENDIAN;
            goto L23
        L46:
            return;
        L41:
            return;
        L9:
            if (r0 == 0) goto L11;
            r42 = (int) r5.getSize();
            goto L13
        L5:
            r0 = r11.getInt(32);
            r2 = r11.getShort(46);
            r3 = r11.getShort(48);
            r112 = r11.getShort(50);
            goto L6
        }

        public List<ELFSectionHeaderEntry> getEntries() {
            return this.entries;
        }
    }

    static {
        ELF_MAGIC = new byte[]{ByteCompanionObject.MAX_VALUE, 69, 76, 70};
    }

    private ELFAnalyser(String r2) {
        this.ELF = false;
        this._64Bit = false;
        this.bigEndian = false;
        this.armHardFloatFlag = false;
        this.armSoftFloatFlag = false;
        this.armEabiAapcsVfp = false;
        this.arm = false;
        this.filename = r2;
    }

    public static ELFAnalyser analyse(String r1) {
        ELFAnalyser r0 = new ELFAnalyser(r1);
        r0.runDetection();
        return r0;
    }

    private static Map<Integer, Map<ArmAeabiAttributesTag, Object>> parseAEABI(ByteBuffer r5) {
        HashMap r0 = new HashMap();
    L4:
        if (r5.position() >= r5.limit()) goto L9;
        int r1 = r5.position();
        int r2 = readULEB128(r5).intValue();
        int r3 = r5.getInt();
        if (r2 != 1) goto L8;
        r0.put(Integer.valueOf(r2), parseFileAttribute(r5));
    L8:
        r5.position(r1 + r3);
        goto L4
    L9:
        return r0;
    }

    private static Map<Integer, Map<ArmAeabiAttributesTag, Object>> parseArmAttributes(ByteBuffer r4) {
        if (r4.get() == 65) goto L7;
        return Collections.EMPTY_MAP;
    L7:
        if (r4.position() >= r4.limit()) goto L17;
        int r0 = r4.position();
        int r1 = r4.getInt();
        if (r1 <= 0) goto L17;
        if ("aeabi".equals(readNTBS(r4, null)) == true) goto L14;
        r4.position(r0 + r1);
        goto L7
    L14:
        return parseAEABI(r4);
    L17:
        return Collections.EMPTY_MAP;
    }

    private void parseEabiAapcsVfp(ByteBuffer r8, RandomAccessFile r9) {
        Iterator<ELFSectionHeaderEntry> r82 = new ELFSectionHeaders(this._64Bit, this.bigEndian, r8, r9).getEntries().iterator();
    L4:
        if (r82.hasNext() == false) goto L24;
        ELFSectionHeaderEntry r2 = r82.next();
        if (".ARM.attributes".equals(r2.getName()) == false) goto L4;
        ByteBuffer r3 = ByteBuffer.allocate((int) r2.getSize());
        if (this.bigEndian == false) goto L10;
        ByteOrder r4 = ByteOrder.BIG_ENDIAN;
    L11:
        r3.order(r4);
        r9.getChannel().read(r3, r2.getOffset());
        r3.rewind();
        Map<ArmAeabiAttributesTag, Object> r22 = parseArmAttributes(r3).get(1);
        if (r22 == null) goto L4;
        Object r23 = r22.get(ArmAeabiAttributesTag.ABI_VFP_args);
        if ((r23 instanceof Integer) == false) goto L20;
        if (((Integer) r23).equals(1) == false) goto L20;
        this.armEabiAapcsVfp = true;
    L20:
        if ((r23 instanceof BigInteger) == false) goto L4;
        if (((BigInteger) r23).intValue() != 1) goto L4;
        this.armEabiAapcsVfp = true;
        goto L4
    L10:
        r4 = ByteOrder.LITTLE_ENDIAN;
        goto L11
    }

    private static Map<ArmAeabiAttributesTag, Object> parseFileAttribute(ByteBuffer r4) {
        HashMap r0 = new HashMap();
    L4:
        if (r4.position() >= r4.limit()) goto L15;
        ArmAeabiAttributesTag r1 = ArmAeabiAttributesTag.getByValue(readULEB128(r4).intValue());
        int r2 = r1.getParameterType().ordinal();
        if (r2 != 0) goto L8;
        r0.put(r1, Integer.valueOf(r4.getInt()));
        goto L4
    L8:
        if (r2 != 1) goto L10;
        r0.put(r1, readNTBS(r4, null));
        goto L4
    L10:
        if (r2 != 2) goto L4;
        r0.put(r1, readULEB128(r4));
        goto L4
    L15:
        return r0;
    }

    private static String readNTBS(ByteBuffer r2, Integer r3) {
        if (r3 == null) goto L4;
        r2.position(r3.intValue());
    L4:
        int r32 = r2.position();
    L6:
        if (r2.get() == 0) goto L9;
        if (r2.position() <= r2.limit()) goto L6;
    L9:
        byte[] r0 = new byte[(r2.position() - r32) - 1];
        r2.position(r32);
        r2.get(r0);
        r2.position(r2.position() + 1);
        return new String(r0, "ASCII");
    L12:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private static BigInteger readULEB128(ByteBuffer r5) {
        BigInteger r0 = BigInteger.ZERO;
        int r1 = 0;
    L3:
        byte r2 = r5.get();
        r0 = r0.or(BigInteger.valueOf(r2 & ByteCompanionObject.MAX_VALUE).shiftLeft(r1));
        if ((r2 & ByteCompanionObject.MIN_VALUE) == 0) goto L5;
        r1 = r1 + 7;
        goto L3
    L5:
        return r0;
    }

    private void runDetection() {
        RandomAccessFile r0 = new RandomAccessFile(this.filename, "r");
        boolean r2 = true;
        if (r0.length() <= 4) goto L11;
        byte[] r1 = new byte[4];     // Catch: Throwable -> L8
        r0.seek(0);     // Catch: Throwable -> L8
        r0.read(r1);     // Catch: Throwable -> L8
        if (Arrays.equals(r1, ELF_MAGIC) == false) goto L11;
        this.ELF = true;     // Catch: Throwable -> L8
    L11:
        if (this.ELF == false) goto L54;
        r0.seek(4);     // Catch: Throwable -> L8
        byte r12 = r0.readByte();     // Catch: Throwable -> L8
        byte r3 = r0.readByte();     // Catch: Throwable -> L8
        if (r12 != 2) goto L16;
        boolean r13 = true;
    L17:
        this._64Bit = r13;     // Catch: Throwable -> L8
        if (r3 != 2) goto L20;
        boolean r14 = true;
    L21:
        this.bigEndian = r14;     // Catch: Throwable -> L8
        r0.seek(0);     // Catch: Throwable -> L8
        if (this._64Bit == false) goto L24;
        int r15 = 64;
    L25:
        ByteBuffer r16 = ByteBuffer.allocate(r15);     // Catch: Throwable -> L8
        r0.getChannel().read(r16, 0);     // Catch: Throwable -> L8
        if (this.bigEndian == false) goto L28;
        ByteOrder r32 = ByteOrder.BIG_ENDIAN;     // Catch: Throwable -> L8
    L29:
        r16.order(r32);     // Catch: Throwable -> L8
        if (r16.get(18) != 40) goto L32;
        boolean r33 = true;
    L33:
        this.arm = r33;     // Catch: Throwable -> L8
        if (r33 == false) goto L54;
        if (this._64Bit == false) goto L38;
        int r34 = 48;
    L39:
        int r35 = r16.getInt(r34);     // Catch: Throwable -> L8
        if ((r35 & 1024) != 1024) goto L42;
        boolean r4 = true;
    L43:
        this.armHardFloatFlag = r4;     // Catch: Throwable -> L8
        if ((r35 & 512) == 512) goto L47;
        r2 = false;
    L47:
        this.armSoftFloatFlag = r2;     // Catch: Throwable -> L8
        parseEabiAapcsVfp(r16, r0);     // Catch: Throwable -> L8
        goto L54
    L42:
        r4 = false;
        goto L43
    L38:
        r34 = 36;
        goto L39
    L32:
        r33 = false;
        goto L33
    L28:
        r32 = ByteOrder.LITTLE_ENDIAN;     // Catch: Throwable -> L8
        goto L29
    L24:
        r15 = 52;
        goto L25
    L20:
        r14 = false;
        goto L21
    L16:
        r13 = false;
    L54:
        r0.close();     // Catch: IOException -> L52
        return;
    L60:
        return;
    L8:
        th = move-exception;
        r0.close();     // Catch: IOException -> L53
    L51:
        throw th;
    }

    public String getFilename() {
        return this.filename;
    }

    public boolean is64Bit() {
        return this._64Bit;
    }

    public boolean isArm() {
        return this.arm;
    }

    public boolean isArmEabiAapcsVfp() {
        return this.armEabiAapcsVfp;
    }

    public boolean isArmHardFloat() {
        if (isArmEabiAapcsVfp() == false) goto L5;
        return true;
    L5:
        if (isArmHardFloatFlag() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isArmHardFloatFlag() {
        return this.armHardFloatFlag;
    }

    public boolean isArmSoftFloatFlag() {
        return this.armSoftFloatFlag;
    }

    public boolean isBigEndian() {
        return this.bigEndian;
    }

    public boolean isELF() {
        return this.ELF;
    }
}
