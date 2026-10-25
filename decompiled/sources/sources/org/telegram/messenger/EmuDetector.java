package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class EmuDetector {
    private static final String[] ANDY_FILES = null;
    private static final String[] BLUE_FILES = null;
    private static final String[] DEVICE_IDS = null;
    private static final String[] GENY_FILES = null;
    private static final String[] IMSI_IDS = null;
    private static final String IP = "10.0.2.15";
    private static final int MIN_PROPERTIES_THRESHOLD = 5;
    private static final String[] NOX_FILES = null;
    private static final String[] PHONE_NUMBERS = null;
    private static final String[] PIPES = null;
    private static final Property[] PROPERTIES = null;
    private static final String[] QEMU_DRIVERS = null;
    private static final String[] X86_FILES = null;

    @SuppressLint({"StaticFieldLeak"})
    private static EmuDetector mEmulatorDetector;
    private boolean detectResult;
    private boolean detected;
    private boolean isCheckPackage;
    private boolean isTelephony;
    private final Context mContext;
    private List<String> mListPackageName;

    public enum EmulatorTypes extends Enum<EmulatorTypes> {
        private static final /* synthetic */ EmulatorTypes[] $VALUES = null;
        public static final EmulatorTypes ANDY = null;
        public static final EmulatorTypes BLUE = null;
        public static final EmulatorTypes GENY = null;
        public static final EmulatorTypes NOX = null;
        public static final EmulatorTypes PIPES = null;
        public static final EmulatorTypes X86 = null;

        private static /* synthetic */ EmulatorTypes[] $values() {
            return new EmulatorTypes[]{GENY, ANDY, NOX, BLUE, PIPES, X86};
        }

        static {
            GENY = new EmulatorTypes("GENY", 0);
            ANDY = new EmulatorTypes("ANDY", 1);
            NOX = new EmulatorTypes("NOX", 2);
            BLUE = new EmulatorTypes("BLUE", 3);
            PIPES = new EmulatorTypes("PIPES", 4);
            X86 = new EmulatorTypes("X86", 5);
            $VALUES = $values();
        }

        EmulatorTypes(String r1, int r2) {
        }

        public static EmulatorTypes valueOf(String r1) {
            return (EmulatorTypes) Enum.valueOf(EmulatorTypes.class, r1);
        }

        public static EmulatorTypes[] values() {
            return (EmulatorTypes[]) $VALUES.clone();
        }
    }

    public interface OnEmulatorDetectorListener {
        void onResult(boolean r1);
    }

    public static class Property {
        public String name;
        public String seek_value;

        public Property(String r1, String r2) {
            this.name = r1;
            this.seek_value = r2;
        }
    }

    static {
        PHONE_NUMBERS = new String[]{"15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584"};
        DEVICE_IDS = new String[]{"000000000000000", "e21833235b6eef10", "012345678912345"};
        IMSI_IDS = new String[]{"310260000000000"};
        GENY_FILES = new String[]{"/dev/socket/genyd", "/dev/socket/baseband_genyd"};
        QEMU_DRIVERS = new String[]{"goldfish"};
        PIPES = new String[]{"/dev/socket/qemud", "/dev/qemu_pipe"};
        X86_FILES = new String[]{"ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc"};
        ANDY_FILES = new String[]{"fstab.andy", "ueventd.andy.rc"};
        NOX_FILES = new String[]{"fstab.nox", "init.nox.rc", "ueventd.nox.rc", "/BigNoxGameHD", "/YSLauncher"};
        BLUE_FILES = new String[]{"/Android/data/com.bluestacks.home", "/Android/data/com.bluestacks.settings"};
        PROPERTIES = new Property[]{new Property("init.svc.qemud", null), new Property("init.svc.qemu-props", null), new Property("qemu.hw.mainkeys", null), new Property("qemu.sf.fake_camera", null), new Property("qemu.sf.lcd_density", null), new Property("ro.bootloader", "unknown"), new Property("ro.bootmode", "unknown"), new Property("ro.hardware", "goldfish"), new Property("ro.kernel.android.qemud", null), new Property("ro.kernel.qemu.gles", null), new Property("ro.kernel.qemu", "1"), new Property("ro.product.device", "generic"), new Property("ro.product.model", "sdk"), new Property("ro.product.name", "sdk"), new Property("ro.serialno", null)};
    }

    private EmuDetector(Context r2) {
        this.isTelephony = false;
        this.isCheckPackage = true;
        ArrayList r0 = new ArrayList();
        this.mListPackageName = r0;
        this.mContext = r2;
        r0.add("com.google.android.launcher.layouts.genymotion");
        this.mListPackageName.add("com.bluestacks");
        this.mListPackageName.add("com.bignox.app");
        this.mListPackageName.add("com.vphone.launcher");
    }

    private boolean checkAdvanced() {
        if (checkTelephony() == false) goto L5;
        return true;
    L5:
        if (checkFiles(GENY_FILES, EmulatorTypes.GENY) == false) goto L7;
        return true;
    L7:
        if (checkFiles(ANDY_FILES, EmulatorTypes.ANDY) == false) goto L9;
        return true;
    L9:
        if (checkFiles(NOX_FILES, EmulatorTypes.NOX) == false) goto L11;
        return true;
    L11:
        if (checkFiles(BLUE_FILES, EmulatorTypes.BLUE) == false) goto L13;
        return true;
    L13:
        if (checkQEmuDrivers() == false) goto L15;
        return true;
    L15:
        if (checkFiles(PIPES, EmulatorTypes.PIPES) == false) goto L17;
        return true;
    L17:
        if (checkIp() == false) goto L19;
        return true;
    L19:
        if (checkQEmuProps() == true) goto L21;
        return false;
    L21:
        if (checkFiles(X86_FILES, EmulatorTypes.X86) == true) goto L35;
        return false;
    L35:
        return true;
    }

    private boolean checkBasic() {
        boolean r1 = false;
        if (Build.BOARD.toLowerCase().contains("nox") == false) goto L5;
    L42:
        boolean r7 = true;
    L43:
        if (r7 == false) goto L46;
        return true;
    L46:
        if (Build.BRAND.startsWith("generic") == true) goto L48;
    L50:
        boolean r72 = r7 | r1;
        if (r72 == false) goto L54;
        return true;
    L54:
        return r72 | "google_sdk".equals(Build.PRODUCT);
    L48:
        if (Build.DEVICE.startsWith("generic") == false) goto L50;
        r1 = true;
        goto L50
    L5:
        if (Build.BOOTLOADER.toLowerCase().contains("nox") == true) goto L42;
        if (Build.FINGERPRINT.startsWith("generic") == true) goto L42;
        String r73 = Build.MODEL;
        if (r73.toLowerCase().contains("google_sdk") == true) goto L42;
        if (r73.toLowerCase().contains("droid4x") == true) goto L42;
        if (r73.toLowerCase().contains("emulator") == true) goto L42;
        if (r73.contains("Android SDK built for x86") == true) goto L42;
        if (Build.MANUFACTURER.toLowerCase().contains("genymotion") == true) goto L42;
        String r74 = Build.HARDWARE;
        if (r74.toLowerCase().contains("goldfish") == true) goto L42;
        if (r74.toLowerCase().contains("vbox86") == true) goto L42;
        if (r74.toLowerCase().contains("android_x86") == true) goto L42;
        if (r74.toLowerCase().contains("nox") == true) goto L42;
        if (r74.toLowerCase().contains("ranchu") == true) goto L42;
        String r75 = Build.PRODUCT;
        if (r75.equals("sdk") == true) goto L42;
        if (r75.equals("google_sdk") == true) goto L42;
        if (r75.equals("sdk_x86") == true) goto L42;
        if (r75.equals("vbox86p") == true) goto L42;
        if (r75.toLowerCase().contains("nox") == true) goto L42;
        if (Build.SERIAL.toLowerCase().contains("nox") == true) goto L42;
        r7 = false;
        goto L43
    }

    private boolean checkDeviceId() {
        String r5 = ((TelephonyManager) this.mContext.getSystemService("phone")).getDeviceId();
        String[] r0 = DEVICE_IDS;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L9;
        if (r0[r3].equalsIgnoreCase(r5) == true) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private boolean checkFiles(String[] r8, EmulatorTypes r9) {
        int r0 = r8.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L20;
        String r3 = r8[r2];
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_EXTERNAL_STORAGE") == 0) goto L7;
        File r4 = new File(r3);
    L16:
        if (r4.exists() == true) goto L17;
        r2 = r2 + 1;
        goto L3
    L17:
        return true;
    L7:
        if (r3.contains("/") == false) goto L11;
        if (r9 != EmulatorTypes.NOX) goto L11;
    L12:
        r4 = new File(Environment.getExternalStorageDirectory() + r3);
    L11:
        if (r9 == EmulatorTypes.BLUE) goto L12;
        r4 = new File(r3);
        goto L16
    L20:
        return false;
    }

    private boolean checkImsi() {
        String r5 = ((TelephonyManager) this.mContext.getSystemService("phone")).getSubscriberId();
        String[] r0 = IMSI_IDS;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L9;
        if (r0[r3].equalsIgnoreCase(r5) == true) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private boolean checkIp() {
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.INTERNET") != 0) goto L24;
        String[] r6 = {"/system/bin/netcfg"};
        StringBuilder r1 = new StringBuilder();
        ProcessBuilder r3 = new ProcessBuilder(r6);     // Catch: Exception -> L25
        r3.directory(new File("/system/bin/"));     // Catch: Exception -> L25
        r3.redirectErrorStream(true);     // Catch: Exception -> L25
        InputStream r62 = r3.start().getInputStream();     // Catch: Exception -> L25
        byte[] r32 = new byte[1024];     // Catch: Exception -> L25
    L7:
        if (r62.read(r32) == (-1)) goto L9;
        r1.append(new String(r32));     // Catch: Exception -> L25
        goto L7
    L9:
        r62.close();     // Catch: Exception -> L25
    L10:
        String r63 = r1.toString();
        if (TextUtils.isEmpty(r63) == true) goto L33;
        String[] r64 = r63.split("\n");
        int r12 = r64.length;
        int r33 = 0;
    L13:
        if (r33 >= r12) goto L35;
        String r4 = r64[r33];
        if (r4.contains("wlan0") == true) goto L21;
        if (r4.contains("tunl0") == true) goto L21;
        if (r4.contains("eth0") == true) goto L21;
    L23:
        r33 = r33 + 1;
    L21:
        if (r4.contains(IP) == false) goto L23;
        return true;
    L35:
        return false;
    L33:
        return false;
    L24:
        return false;
    }

    private boolean checkOperatorNameAndroid() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getNetworkOperatorName().equalsIgnoreCase("android");
    }

    private boolean checkPackageName() {
        if (this.isCheckPackage == true) goto L5;
    L16:
        return false;
    L5:
        if (this.mListPackageName.isEmpty() == true) goto L16;
        PackageManager r0 = this.mContext.getPackageManager();
        Iterator<String> r4 = this.mListPackageName.iterator();
    L9:
        if (r4.hasNext() == false) goto L16;
        Intent r2 = r0.getLaunchIntentForPackage(r4.next());
        if (r2 == null) goto L9;
        if (r0.queryIntentActivities(r2, 65536).isEmpty() == true) goto L9;
        return true;
    }

    private boolean checkPhoneNumber() {
        String r5 = ((TelephonyManager) this.mContext.getSystemService("phone")).getLine1Number();
        String[] r0 = PHONE_NUMBERS;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L9;
        if (r0[r3].equalsIgnoreCase(r5) == true) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private boolean checkQEmuDrivers() {
        File[] r7 = {new File("/proc/tty/drivers"), new File("/proc/cpuinfo")};
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L22;
        File r2 = r7[r1];
        if (r2.exists() == false) goto L21;
        if (r2.canRead() == false) goto L21;
        byte[] r3 = new byte[1024];
        FileInputStream r4 = new FileInputStream(r2);     // Catch: Exception -> L12
        r4.read(r3);     // Catch: Exception -> L12
        r4.close();     // Catch: Exception -> L12
    L14:
        String r22 = new String(r3);
        String[] r32 = QEMU_DRIVERS;
        int r42 = r32.length;
        int r5 = 0;
    L15:
        if (r5 >= r42) goto L21;
        if (r22.contains(r32[r5]) == true) goto L18;
        r5 = r5 + 1;
        goto L15
    L18:
        return true;
    L12:
        e = move-exception;
        e.printStackTrace();
    L21:
        r1 = r1 + 1;
        goto L4
    L22:
        return false;
    }

    private boolean checkQEmuProps() {
        Property[] r0 = PROPERTIES;
        int r1 = r0.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r1) goto L14;
        Property r5 = r0[r3];
        String r6 = getProp(this.mContext, r5.name);
        String r52 = r5.seek_value;
        if (r52 != null) goto L8;
        if (r6 == null) goto L8;
        r4 = r4 + 1;
    L8:
        if (r52 == null) goto L12;
        if (r6.contains(r52) == false) goto L12;
        r4 = r4 + 1;
    L12:
        r3 = r3 + 1;
        goto L3
    L14:
        if (r4 < 5) goto L17;
        return true;
    L17:
        return false;
    }

    private boolean checkTelephony() {
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_PHONE_STATE") == 0) goto L5;
        return false;
    L5:
        if (this.isTelephony == true) goto L7;
        return false;
    L7:
        if (isSupportTelePhony() == true) goto L9;
        return false;
    L9:
        if (checkPhoneNumber() == false) goto L11;
        return true;
    L11:
        if (checkDeviceId() == false) goto L13;
        return true;
    L13:
        if (checkImsi() == false) goto L15;
        return true;
    L15:
        if (checkOperatorNameAndroid() == false) goto L25;
        return true;
    L25:
        return false;
    }

    private String getProp(Context r2, String r3) {
        Class<?> r1 = r2.getClassLoader().loadClass("android.os.SystemProperties");     // Catch: Exception -> L4
        return (String) r1.getMethod("get", new Class[]{String.class}).invoke(r1, new Object[]{r3});
    L4:
        return null;
    }

    private boolean isSupportTelePhony() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    public static EmuDetector with(Context r1) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("Context must not be null.");
        return null;
    L4:
        if (mEmulatorDetector != null) goto L7;
        mEmulatorDetector = new EmuDetector(r1.getApplicationContext());
    L7:
        return mEmulatorDetector;
    }

    public EmuDetector addPackageName(String r2) {
        this.mListPackageName.add(r2);
        return this;
    }

    public EmuDetector addPackageName(List<String> r2) {
        this.mListPackageName.addAll(r2);
        return this;
    }

    public boolean detect() {
        if (this.detected == false) goto L23;
        return this.detectResult;
    L23:
        this.detected = true;     // Catch: Exception -> L21
        if (this.detectResult == true) goto L11;
        this.detectResult = checkBasic();     // Catch: Exception -> L21
    L11:
        if (this.detectResult == true) goto L14;
        this.detectResult = checkAdvanced();     // Catch: Exception -> L21
    L14:
        if (this.detectResult == true) goto L17;
        this.detectResult = checkPackageName();     // Catch: Exception -> L21
    L17:
        if (this.detectResult == true) goto L19;
        this.detectResult = EmuInputDevicesDetector.detect();     // Catch: Exception -> L21
    L19:
        return this.detectResult;
    L21:
        return false;
    }

    public boolean isCheckPackage() {
        return this.isCheckPackage;
    }

    public boolean isCheckTelephony() {
        return this.isTelephony;
    }

    public EmuDetector setCheckPackage(boolean r1) {
        this.isCheckPackage = r1;
        return this;
    }

    public EmuDetector setCheckTelephony(boolean r1) {
        this.isTelephony = r1;
        return this;
    }
}
