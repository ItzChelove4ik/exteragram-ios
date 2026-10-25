package com.android.dx;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* loaded from: classes4.dex */
class AppDataDirGuesser {
    public static final int PER_USER_RANGE = 100000;

    public AppDataDirGuesser() {
    }

    private String getPathFromThisClassLoader(ClassLoader r1, Class<?> r2) {
        Field r0 = r2.getDeclaredField("path");     // Catch: Throwable -> L4
        r0.setAccessible(true);     // Catch: Throwable -> L4
        return (String) r0.get(r1);
    L5:
        return processClassLoaderString(r1.toString());
    }

    private File getWriteableDirectory(String r2) {
        File r0 = new File(r2);
        if (isWriteableDirectory(r0) == false) goto L5;
        return r0;
    L5:
        return null;
    }

    private ClassLoader guessSuitableClassLoader() {
        return AppDataDirGuesser.class.getClassLoader();
    }

    public static String processClassLoaderString(String r1) {
        if (r1.contains("DexPathList") == false) goto L7;
        return processClassLoaderString43OrLater(r1);
    L7:
        return processClassLoaderString42OrEarlier(r1);
    }

    private static String processClassLoaderString42OrEarlier(String r2) {
        int r0 = r2.lastIndexOf(91);
        if (r0 == (-1)) goto L6;
        r2 = r2.substring(r0 + 1);
    L6:
        int r02 = r2.indexOf(93);
        if (r02 != (-1)) goto L10;
        return r2;
    L10:
        return r2.substring(0, r02);
    }

    private static String processClassLoaderString43OrLater(String r5) {
        int r0 = r5.indexOf("DexPathList");
        int r1 = r0 + 11;
        if (r5.length() <= (r0 + 15)) goto L32;
        String r02 = r5.substring(r1);
        int r12 = r02.indexOf(93);
        int r2 = 0;
        if (r02.charAt(0) == '[') goto L7;
        return r5;
    L7:
        if (r02.charAt(1) != '[') goto L34;
        if (r12 < 0) goto L35;
        String[] r52 = r02.substring(2, r12).split(",");
        int r03 = 0;
    L11:
        if (r03 >= r52.length) goto L17;
        int r13 = r52[r03].indexOf(34);
        int r3 = r52[r03].lastIndexOf(34);
        if (r13 <= 0) goto L16;
        if (r13 >= r3) goto L16;
        r52[r03] = r52[r03].substring(r13 + 1, r3);
    L16:
        r03 = r03 + 1;
        goto L11
    L17:
        StringBuilder r04 = new StringBuilder();
        int r14 = r52.length;
    L18:
        if (r2 >= r14) goto L24;
        String r32 = r52[r2];
        if (r04.length() <= 0) goto L22;
        r04.append(':');
    L22:
        r04.append(r32);
        r2 = r2 + 1;
        goto L18
    L24:
        return r04.toString();
    L35:
        return r5;
    L34:
        return r5;
    L32:
        return r5;
    }

    public static String[] splitPathList(String r3) {
        if (r3.startsWith("dexPath=") == false) goto L9;
        int r0 = r3.indexOf(44);
        if (r0 != (-1)) goto L7;
        r3 = r3.substring(8);
        goto L9
    L7:
        r3 = r3.substring(8, r0);
    L9:
        return r3.split(":");
    }

    public boolean fileOrDirExists(File r1) {
        return r1.exists();
    }

    public Integer getProcessUid() {
        return (Integer) Class.forName("android.os.Process").getMethod("myUid", null).invoke(null, null);
    L5:
        return null;
    }

    public File guess() {
        ClassLoader r0 = guessSuitableClassLoader();     // Catch: Throwable -> L8
        Class<?> r1 = Class.forName("dalvik.system.PathClassLoader");     // Catch: Throwable -> L8
        r1.cast(r0);     // Catch: Throwable -> L8
        File[] r2 = guessPath(getPathFromThisClassLoader(r0, r1));     // Catch: Throwable -> L8
        if (r2.length <= 0) goto L6;
        return r2[0];
    L6:
        return null;
    L11:
        return null;
    }

    public File[] guessPath(String r8) {
        ArrayList r0 = new ArrayList();
        String[] r82 = splitPathList(r8);
        int r1 = r82.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L32;
        String r3 = r82[r2];
        if (r3.startsWith("/data/app/") == false) goto L30;
        int r4 = r3.lastIndexOf(".apk");
        if (r4 != (r3.length() - 4)) goto L30;
        int r42 = r3.lastIndexOf("/", r4);
        if (r42 == 9) goto L30;
        int r43 = r3.lastIndexOf("/", r42 - 1);
        if (r43 == (-1)) goto L30;
        int r6 = r3.indexOf("-", r43);
        if (r6 == (-1)) goto L30;
        String r32 = r3.substring(r43 + 1, r6);
        File r44 = getWriteableDirectory("/data/data/".concat(r32));
        if (r44 != null) goto L22;
        r44 = guessUserDataDirectory(r32);
    L22:
        if (r44 == null) goto L30;
        File r33 = new File(r44, "cache");
        if (fileOrDirExists(r33) == true) goto L28;
        if (r33.mkdir() == false) goto L30;
    L28:
        if (isWriteableDirectory(r33) == false) goto L30;
        r0.add(r33);
    L30:
        r2 = r2 + 1;
        goto L3
    L32:
        return (File[]) r0.toArray(new File[r0.size()]);
    }

    public File guessUserDataDirectory(String r3) {
        Integer r0 = getProcessUid();
        if (r0 != null) goto L7;
        return null;
    L7:
        return getWriteableDirectory(String.format("/data/user/%d/%s", new Object[]{Integer.valueOf(r0.intValue() / PER_USER_RANGE), r3}));
    }

    public boolean isWriteableDirectory(File r1) {
        if (r1.isDirectory() == true) goto L5;
        return false;
    L5:
        if (r1.canWrite() == false) goto L10;
        return true;
    L10:
        return false;
    }
}
