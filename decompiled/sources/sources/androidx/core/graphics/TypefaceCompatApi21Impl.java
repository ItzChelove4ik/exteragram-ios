package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static Method sAddFontWeightStyle = null;
    private static Method sCreateFromFamiliesWithDefault = null;
    private static Class<?> sFontFamily = null;
    private static Constructor<?> sFontFamilyCtor = null;
    private static boolean sHasInitBeenCalled = false;

    static {
    }

    public TypefaceCompatApi21Impl() {
    }

    private static boolean addFontWeightStyle(Object r1, String r2, int r3, boolean r4) {
        init();
        return ((Boolean) sAddFontWeightStyle.invoke(r1, new Object[]{r2, Integer.valueOf(r3), Boolean.valueOf(r4)})).booleanValue();
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return false;
    }

    private static Typeface createFromFamiliesWithDefault(Object r2) {
        init();
        Object r0 = Array.newInstance(sFontFamily, 1);     // Catch: Throwable -> L5
        Array.set(r0, 0, r2);     // Catch: Throwable -> L5
        return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, new Object[]{r0});
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private File getFile(ParcelFileDescriptor r3) {
        String r2 = Os.readlink("/proc/self/fd/" + r3.getFd());     // Catch: ErrnoException -> L8
        if (OsConstants.S_ISREG(Os.stat(r2).st_mode) == false) goto L7;
        return new File(r2);
    L7:
        return null;
    }

    private static void init() {
        if (sHasInitBeenCalled == false) goto L5;
        return;
    L5:
        sHasInitBeenCalled = true;
        Constructor<?> r1 = null;
        Class<?> r2 = Class.forName("android.graphics.FontFamily");     // Catch: Throwable -> L8 ClassNotFoundException -> L10
        Constructor<?> r3 = r2.getConstructor(null);     // Catch: Throwable -> L8 ClassNotFoundException -> L10
        Method r4 = r2.getMethod("addFontWeightStyle", new Class[]{String.class, Integer.TYPE, Boolean.TYPE});     // Catch: Throwable -> L8 ClassNotFoundException -> L10
        Method r0 = Typeface.class.getMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(r2, 1).getClass()});     // Catch: Throwable -> L8 ClassNotFoundException -> L10
        r1 = r3;
    L12:
        sFontFamilyCtor = r1;
        sFontFamily = r2;
        sAddFontWeightStyle = r4;
        sCreateFromFamiliesWithDefault = r0;
        return;
    L8:
        e = move-exception;
        Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
        r0 = null;
        r2 = null;
        r4 = null;
        goto L12
    }

    private static Object newFamily() {
        init();
        return sFontFamilyCtor.newInstance(null);
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context r7, FontResourcesParserCompat.FontFamilyFilesResourceEntry r8, Resources r9, int r10) {
        Object r6 = newFamily();
        FontResourcesParserCompat.FontFileResourceEntry[] r82 = r8.getEntries();
        int r102 = r82.length;
        int r0 = 0;
    L3:
        if (r0 >= r102) goto L22;
        FontResourcesParserCompat.FontFileResourceEntry r1 = r82[r0];
        File r2 = TypefaceCompatUtil.getTempFile(r7);
        if (r2 == null) goto L6;
        if (TypefaceCompatUtil.copyToFile(r2, r9, r1.getResourceId()) == false) goto L9;
        if (addFontWeightStyle(r6, r2.getPath(), r1.getWeight(), r1.isItalic()) == false) goto L13;
        r2.delete();
        r0 = r0 + 1;
        goto L3
    L13:
        r2.delete();
        return null;
    L9:
        r2.delete();
        return null;
    L19:
        r2.delete();
        return null;
    L16:
        th = move-exception;
        r2.delete();
        throw th;
    L6:
        return null;
    L22:
        return createFromFamiliesWithDefault(r6);
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context r4, CancellationSignal r5, FontsContractCompat.FontInfo[] r6, int r7) {
        if (r6.length >= 1) goto L5;
        return null;
    L5:
        FontsContractCompat.FontInfo r62 = findBestInfo(r6, r7);
        ContentResolver r72 = r4.getContentResolver();
        ParcelFileDescriptor r52 = r72.openFileDescriptor(r62.getUri(), "r", r5);     // Catch: IOException -> L36
        if (r52 != null) goto L37;
        if (r52 == null) goto L10;
        r52.close();     // Catch: IOException -> L36
    L10:
        return null;
    L37:
        File r63 = getFile(r52);     // Catch: Throwable -> L19
        if (r63 != null) goto L14;
    L21:
        FileInputStream r64 = new FileInputStream(r52.getFileDescriptor());     // Catch: Throwable -> L19
        Typeface r3 = super.createFromInputStream(r4, r64);     // Catch: Throwable -> L25
        r64.close();     // Catch: Throwable -> L19
    L17:
        r52.close();     // Catch: IOException -> L36
        return r3;
    L25:
        th = move-exception;
        r64.close();     // Catch: Throwable -> L28
    L30:
        throw th;     // Catch: Throwable -> L19
    L28:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L19
        goto L30
    L14:
        if (r63.canRead() == false) goto L21;
        r3 = Typeface.createFromFile(r63);     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        r52.close();     // Catch: Throwable -> L33
    L35:
        throw th;     // Catch: IOException -> L36
    L33:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L36
    L36:
        return null;
    }
}
