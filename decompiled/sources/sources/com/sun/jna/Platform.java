package com.sun.jna;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public final class Platform {
    public static final int AIX = 7;
    public static final int ANDROID = 8;
    public static final String ARCH = null;
    public static final String C_LIBRARY_NAME = null;
    public static final int DRAGONFLYBSD = 12;
    public static final int FREEBSD = 4;
    public static final int GNU = 9;
    public static final boolean HAS_AWT = false;
    public static final boolean HAS_BUFFERS = false;
    public static final boolean HAS_DLL_CALLBACKS = false;
    public static final boolean HAS_JAWT = false;
    public static final int KFREEBSD = 10;
    public static final int LINUX = 1;
    public static final int MAC = 0;
    public static final String MATH_LIBRARY_NAME = null;
    public static final int NETBSD = 11;
    public static final int OPENBSD = 5;
    public static final String RESOURCE_PREFIX = null;
    public static final boolean RO_FIELDS = false;
    public static final int SOLARIS = 3;
    public static final int UNSPECIFIED = -1;
    public static final int WINDOWS = 2;
    public static final int WINDOWSCE = 6;
    private static final int osType = 0;

    static {
        String r0 = System.getProperty("os.name");
        boolean r6 = false;
        if (r0.startsWith("Linux") == false) goto L9;
        if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase()) == false) goto L7;
        osType = 8;
        System.setProperty("jna.nounpack", "true");
    L85:
        Class.forName("java.nio.Buffer");     // Catch: ClassNotFoundException -> L50
        boolean r02 = true;
    L51:
        int r1 = osType;
        if (r1 == 6) goto L56;
        if (r1 == 8) goto L56;
        if (r1 == 7) goto L56;
        boolean r2 = true;
    L57:
        HAS_AWT = r2;
        if (r2 == false) goto L61;
        if (r1 == 0) goto L61;
        boolean r22 = true;
    L62:
        HAS_JAWT = r22;
        HAS_BUFFERS = r02;
        if (r1 == 6) goto L65;
        boolean r03 = true;
    L66:
        RO_FIELDS = r03;
        String r04 = "coredll";
        if (r1 != 2) goto L69;
        String r3 = "msvcrt";
    L72:
        C_LIBRARY_NAME = r3;
        if (r1 != 2) goto L75;
        r04 = "msvcrt";
    L78:
        MATH_LIBRARY_NAME = r04;
        String r05 = getCanonicalArchitecture(System.getProperty("os.arch"), r1);
        ARCH = r05;
        if (r1 == 2) goto L81;
    L83:
        HAS_DLL_CALLBACKS = r6;
        RESOURCE_PREFIX = getNativeLibraryResourcePrefix();
        return;
    L81:
        if (r05.startsWith("aarch") == true) goto L83;
        r6 = true;
        goto L83
    L75:
        if (r1 == 6) goto L78;
        r04 = "m";
        goto L78
    L69:
        if (r1 != 6) goto L71;
        r3 = "coredll";
        goto L72
    L71:
        r3 = "c";
        goto L72
    L65:
        r03 = false;
    L61:
        r22 = false;
    L56:
        r2 = false;
    L50:
        r02 = false;
        goto L51
    L7:
        osType = 1;
        goto L85
    L9:
        if (r0.startsWith("AIX") == false) goto L12;
        osType = 7;
        goto L85
    L12:
        if (r0.startsWith("Mac") == false) goto L14;
    L47:
        osType = 0;
        goto L85
    L14:
        if (r0.startsWith("Darwin") == true) goto L47;
        if (r0.startsWith("Windows CE") == false) goto L20;
        osType = 6;
        goto L85
    L20:
        if (r0.startsWith("Windows") == false) goto L23;
        osType = 2;
        goto L85
    L23:
        if (r0.startsWith("Solaris") == false) goto L25;
    L46:
        osType = 3;
        goto L85
    L25:
        if (r0.startsWith("SunOS") == true) goto L46;
        if (r0.startsWith("FreeBSD") == false) goto L31;
        osType = 4;
        goto L85
    L31:
        if (r0.startsWith("OpenBSD") == false) goto L34;
        osType = 5;
        goto L85
    L34:
        if (r0.equalsIgnoreCase("gnu") == false) goto L37;
        osType = 9;
        goto L85
    L37:
        if (r0.equalsIgnoreCase("gnu/kfreebsd") == false) goto L40;
        osType = 10;
        goto L85
    L40:
        if (r0.equalsIgnoreCase("netbsd") == false) goto L43;
        osType = 11;
        goto L85
    L43:
        if (r0.equalsIgnoreCase("dragonflybsd") == false) goto L45;
        osType = 12;
        goto L85
    L45:
        osType = -1;
        goto L85
    }

    private Platform() {
    }

    public static String getCanonicalArchitecture(String r2, int r3) {
        String r22 = r2.toLowerCase().trim();
        if ("powerpc".equals(r22) == false) goto L6;
        r22 = "ppc";
    L24:
        if ("ppc64".equals(r22) == false) goto L29;
        if ("little".equals(System.getProperty("sun.cpu.endian")) == false) goto L29;
        r22 = "ppc64le";
    L29:
        if ("arm".equals(r22) == true) goto L31;
        return r22;
    L31:
        if (r3 == 1) goto L33;
        return r22;
    L33:
        if (isSoftFloat() == false) goto L38;
        return "armel";
    L38:
        return r22;
    L6:
        if ("powerpc64".equals(r22) == false) goto L9;
        r22 = "ppc64";
        goto L24
    L9:
        if ("i386".equals(r22) == false) goto L11;
    L22:
        r22 = "x86";
        goto L24
    L11:
        if ("i686".equals(r22) == true) goto L22;
        if ("x86_64".equals(r22) == false) goto L16;
    L21:
        r22 = "x86-64";
        goto L24
    L16:
        if ("amd64".equals(r22) == true) goto L21;
        if ("zarch_64".equals(r22) == false) goto L24;
        r22 = "s390x";
        goto L24
    }

    public static String getNativeLibraryResourcePrefix() {
        String r0 = System.getProperty("jna.prefix");
        if (r0 == null) goto L6;
        return r0;
    L6:
        return getNativeLibraryResourcePrefix(getOSType(), System.getProperty("os.arch"), System.getProperty("os.name"));
    }

    public static String getNativeLibraryResourcePrefix(int r1, String r2, String r3) {
        String r22 = getCanonicalArchitecture(r2, r1);
        switch(r1) {
            case 0: goto L33;
            case 1: goto L31;
            case 2: goto L29;
            case 3: goto L27;
            case 4: goto L25;
            case 5: goto L23;
            case 6: goto L21;
            case 7: goto L4;
            case 8: goto L16;
            case 9: goto L4;
            case 10: goto L14;
            case 11: goto L12;
            case 12: goto L10;
            default: goto L4;
        };
    L4:
        String r12 = r3.toLowerCase();
        int r32 = r12.indexOf(" ");
        if (r32 == (-1)) goto L8;
        r12 = r12.substring(0, r32);
    L8:
        return r12 + "-" + r22;
    L10:
        return "dragonflybsd-" + r22;
    L12:
        return "netbsd-" + r22;
    L14:
        return "kfreebsd-" + r22;
    L16:
        if (r22.startsWith("arm") == false) goto L19;
        r22 = "arm";
    L19:
        return "android-".concat(r22);
    L21:
        return "w32ce-" + r22;
    L23:
        return "openbsd-" + r22;
    L25:
        return "freebsd-" + r22;
    L27:
        return "sunos-" + r22;
    L29:
        return "win32-" + r22;
    L31:
        return "linux-" + r22;
    L33:
        return "darwin-" + r22;
    }

    public static final int getOSType() {
        return osType;
    }

    public static final boolean hasRuntimeExec() {
        if (isWindowsCE() == true) goto L5;
        return true;
    L5:
        if ("J9".equals(System.getProperty("java.vm.name")) == false) goto L10;
        return false;
    L10:
        return true;
    }

    public static final boolean is64Bit() {
        String r0 = System.getProperty("sun.arch.data.model", System.getProperty("com.ibm.vm.bitmode"));
        if (r0 != null) goto L5;
        String r02 = ARCH;
        if ("x86-64".equals(r02) == false) goto L9;
    L32:
        return true;
    L9:
        if ("ia64".equals(r02) == true) goto L32;
        if ("ppc64".equals(r02) == true) goto L32;
        if ("ppc64le".equals(r02) == true) goto L32;
        if ("sparcv9".equals(r02) == true) goto L32;
        if ("mips64".equals(r02) == true) goto L32;
        if ("mips64el".equals(r02) == true) goto L32;
        if ("loongarch64".equals(r02) == true) goto L32;
        if ("amd64".equals(r02) == true) goto L32;
        if ("aarch64".equals(r02) == true) goto L32;
        if (Native.POINTER_SIZE != 8) goto L30;
        return true;
    L30:
        return false;
    L5:
        return "64".equals(r0);
    }

    public static final boolean isAIX() {
        if (osType != 7) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isARM() {
        String r0 = ARCH;
        if (r0.startsWith("arm") == false) goto L5;
        return true;
    L5:
        if (r0.startsWith("aarch") == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static final boolean isAndroid() {
        if (osType != 8) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isDragonFlyBSD() {
        if (osType != 12) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isFreeBSD() {
        if (osType != 4) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isGNU() {
        if (osType != 9) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isIntel() {
        if (ARCH.startsWith("x86") == false) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isLinux() {
        if (osType != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public static final boolean isLoongArch() {
        return ARCH.startsWith("loongarch");
    }

    public static final boolean isMIPS() {
        String r0 = ARCH;
        if (r0.equals("mips") == false) goto L5;
        return true;
    L5:
        if (r0.equals("mips64") == false) goto L7;
        return true;
    L7:
        if (r0.equals("mipsel") == false) goto L9;
        return true;
    L9:
        if (r0.equals("mips64el") == true) goto L17;
        return false;
    L17:
        return true;
    }

    public static final boolean isMac() {
        if (osType != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isNetBSD() {
        if (osType != 11) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isOpenBSD() {
        if (osType != 5) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isPPC() {
        if (ARCH.startsWith("ppc") == false) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isRISCV() {
        return ARCH.startsWith("riscv");
    }

    public static final boolean isSPARC() {
        return ARCH.startsWith("sparc");
    }

    public static boolean isSoftFloat() {
    L10:
        e = move-exception;
        Logger.getLogger(Platform.class.getName()).log(Level.INFO, "Failed to read '/proc/self/exe' or the target binary.", e);
        return false;
    L8:
        e = move-exception;
        Logger.getLogger(Platform.class.getName()).log(Level.INFO, "SecurityException while analysing '/proc/self/exe' or the target binary.", e);
        return false;
    L4:
        if (new File("/proc/self/exe").exists() == false) goto L18;
    L7:
        return !ELFAnalyser.analyse(r1.getCanonicalPath()).isArmHardFloat();
    L18:
        return false;
    }

    public static final boolean isSolaris() {
        if (osType != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isWindows() {
        int r0 = osType;
        if (r0 != 2) goto L5;
        return true;
    L5:
        if (r0 == 6) goto L11;
        return false;
    L11:
        return true;
    }

    public static final boolean isWindowsCE() {
        if (osType != 6) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isX11() {
        if (isWindows() == false) goto L5;
        return false;
    L5:
        if (isMac() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static final boolean iskFreeBSD() {
        if (osType != 10) goto L6;
        return true;
    L6:
        return false;
    }
}
