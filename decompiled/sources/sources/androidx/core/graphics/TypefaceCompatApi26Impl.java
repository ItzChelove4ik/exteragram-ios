package androidx.core.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class<?> mFontFamily;
    protected final Constructor<?> mFontFamilyCtor;
    protected final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        Class<?> r0 = obtainFontFamily();     // Catch: NoSuchMethodException -> L5 Throwable -> L7
        Constructor<?> r1 = obtainFontFamilyCtor(r0);     // Catch: NoSuchMethodException -> L5 Throwable -> L7
        Method r2 = obtainAddFontFromAssetManagerMethod(r0);     // Catch: NoSuchMethodException -> L5 Throwable -> L7
        Method r3 = obtainAddFontFromBufferMethod(r0);     // Catch: NoSuchMethodException -> L5 Throwable -> L7
        Method r4 = obtainFreezeMethod(r0);     // Catch: NoSuchMethodException -> L5 Throwable -> L7
        Method r5 = obtainAbortCreationMethod(r0);     // Catch: NoSuchMethodException -> L5 Throwable -> L7
        Method r6 = obtainCreateFromFamiliesWithDefaultMethod(r0);     // Catch: NoSuchMethodException -> L5 Throwable -> L7
    L9:
        this.mFontFamily = r0;
        this.mFontFamilyCtor = r1;
        this.mAddFontFromAssetManager = r2;
        this.mAddFontFromBuffer = r3;
        this.mFreeze = r4;
        this.mAbortCreation = r5;
        this.mCreateFromFamiliesWithDefault = r6;
        return;
    L7:
        e = move-exception;
        Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e.getClass().getName()), e);
        r0 = null;
        r1 = null;
        r2 = null;
        r3 = null;
        r4 = null;
        r5 = null;
        r6 = null;
        goto L9
    }

    private void abortCreation(Object r2) {
        this.mAbortCreation.invoke(r2, null);     // Catch: Throwable -> L4
        return;
    }

    private boolean addFontFromAssetManager(Context r10, Object r11, String r12, int r13, int r14, int r15, FontVariationAxis[] r16) {
        return ((Boolean) this.mAddFontFromAssetManager.invoke(r11, new Object[]{r10.getAssets(), r12, 0, Boolean.FALSE, Integer.valueOf(r13), Integer.valueOf(r14), Integer.valueOf(r15), r16})).booleanValue();
    L5:
        return false;
    }

    private boolean addFontFromBuffer(Object r2, ByteBuffer r3, int r4, int r5, int r6) {
        return ((Boolean) this.mAddFontFromBuffer.invoke(r2, new Object[]{r3, Integer.valueOf(r4), null, Integer.valueOf(r5), Integer.valueOf(r6)})).booleanValue();
    L4:
        return false;
    }

    private boolean freeze(Object r2) {
        return ((Boolean) this.mFreeze.invoke(r2, null)).booleanValue();
    L4:
        return false;
    }

    private boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager != null) goto L6;
        Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
    L6:
        if (this.mAddFontFromAssetManager == null) goto L9;
        return true;
    L9:
        return false;
    }

    private Object newFamily() {
        return this.mFontFamilyCtor.newInstance(null);
    L5:
        return null;
    }

    public Typeface createFromFamiliesWithDefault(Object r5) {
        Object r2 = Array.newInstance(this.mFontFamily, 1);     // Catch: Throwable -> L5
        Array.set(r2, 0, r5);     // Catch: Throwable -> L5
        return (Typeface) this.mCreateFromFamiliesWithDefault.invoke(null, new Object[]{r2, -1, -1});
    L5:
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context r10, FontResourcesParserCompat.FontFamilyFilesResourceEntry r11, Resources r12, int r13) {
        if (isFontFamilyPrivateAPIAvailable() == false) goto L5;
        Object r2 = newFamily();
        if (r2 != null) goto L9;
        return null;
    L9:
        FontResourcesParserCompat.FontFileResourceEntry[] r112 = r11.getEntries();
        int r132 = r112.length;
        int r8 = 0;
    L10:
        if (r8 >= r132) goto L16;
        FontResourcesParserCompat.FontFileResourceEntry r0 = r112[r8];
        String r3 = r0.getFileName();
        int r4 = r0.getTtcIndex();
        int r5 = r0.getWeight();
        boolean r6 = r0.isItalic();
        FontVariationAxis[] r7 = FontVariationAxis.fromFontVariationSettings(r0.getVariationSettings());
        TypefaceCompatApi26Impl r02 = this;
        Context r1 = r10;
        if (r02.addFontFromAssetManager(r1, r2, r3, r4, r5, r6 ? 1 : 0, r7) == false) goto L13;
        r8 = r8 + 1;
        TypefaceCompatApi26Impl this = r02;
        r10 = r1;
        goto L10
    L13:
        r02.abortCreation(r2);
        return null;
    L16:
        TypefaceCompatApi26Impl r03 = this;
        if (r03.freeze(r2) == true) goto L20;
        return null;
    L20:
        return r03.createFromFamiliesWithDefault(r2);
    L5:
        return super.createFromFontFamilyFilesResourceEntry(r10, r11, r12, r13);
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context r10, CancellationSignal r11, FontsContractCompat.FontInfo[] r12, int r13) {
        if (r12.length >= 1) goto L6;
        return null;
    L6:
        if (isFontFamilyPrivateAPIAvailable() == true) goto L25;
        FontsContractCompat.FontInfo r9 = findBestInfo(r12, r13);
        ParcelFileDescriptor r102 = r10.getContentResolver().openFileDescriptor(r9.getUri(), "r", r11);     // Catch: IOException -> L24
        if (r102 != null) goto L52;
        if (r102 == null) goto L12;
        r102.close();     // Catch: IOException -> L24
    L12:
        return null;
    L52:
        Typeface r92 = new Typeface.Builder(r102.getFileDescriptor()).setWeight(r9.getWeight()).setItalic(r9.isItalic()).build();     // Catch: Throwable -> L16
        r102.close();     // Catch: IOException -> L24
        return r92;
    L16:
        th = move-exception;
        r102.close();     // Catch: Throwable -> L20
    L60:
        throw th;     // Catch: IOException -> L24
    L20:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L24
        throw th;     // Catch: IOException -> L24
    L24:
        return null;
    L25:
        Map<Uri, ByteBuffer> r103 = TypefaceCompatUtil.readFontInfoIntoByteBuffer(r10, r12, r11);
        Object r4 = newFamily();
        if (r4 != null) goto L28;
        return null;
    L28:
        int r112 = r12.length;
        int r0 = 0;
        boolean r3 = false;
    L29:
        if (r0 >= r112) goto L39;
        FontsContractCompat.FontInfo r5 = r12[r0];
        ByteBuffer r6 = r103.get(r5.getUri());
        if (r6 != null) goto L33;
        Object r52 = r4;
        TypefaceCompatApi26Impl r42 = this;
    L38:
        r0 = r0 + 1;
        TypefaceCompatApi26Impl this = r42;
        r4 = r52;
        r3 = r3;
        goto L29
    L33:
        TypefaceCompatApi26Impl r32 = this;
        boolean r93 = r32.addFontFromBuffer(r4, r6, r5.getTtcIndex(), r5.getWeight(), r5.isItalic() ? 1 : 0);
        r52 = r4;
        r42 = r32;
        if (r93 == false) goto L35;
        r3 = true;
        goto L38
    L35:
        r42.abortCreation(r52);
        return null;
    L39:
        Object r53 = r4;
        TypefaceCompatApi26Impl r43 = this;
        if (r3 == true) goto L44;
        r43.abortCreation(r53);
        return null;
    L44:
        if (r43.freeze(r53) == true) goto L46;
        return null;
    L46:
        Typeface r94 = r43.createFromFamiliesWithDefault(r53);
        if (r94 != null) goto L50;
        return null;
    L50:
        return Typeface.create(r94, r13);
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public /* bridge */ /* synthetic */ Typeface createFromFontInfoWithFallback(Context r1, CancellationSignal r2, List r3, int r4) {
        return super.createFromFontInfoWithFallback(r1, r2, r3, r4);
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromResourcesFontFile(Context r10, Resources r11, int r12, String r13, int r14) {
        if (isFontFamilyPrivateAPIAvailable() == false) goto L5;
        Object r3 = newFamily();
        if (r3 != null) goto L10;
        return null;
    L10:
        if (addFontFromAssetManager(r10, r3, r13, 0, -1, -1, null) == true) goto L14;
        abortCreation(r3);
        return null;
    L14:
        if (freeze(r3) == true) goto L17;
        return null;
    L17:
        return createFromFamiliesWithDefault(r3);
    L5:
        return super.createFromResourcesFontFile(r10, r11, r12, r13, r14);
    }

    public Method obtainAbortCreationMethod(Class<?> r2) {
        return r2.getMethod("abortCreation", null);
    }

    public Method obtainAddFontFromAssetManagerMethod(Class<?> r9) {
        Class r3 = Boolean.TYPE;
        Class r2 = Integer.TYPE;
        return r9.getMethod("addFontFromAssetManager", new Class[]{AssetManager.class, String.class, r2, r3, r2, r2, r2, FontVariationAxis[].class});
    }

    public Method obtainAddFontFromBufferMethod(Class<?> r3) {
        Class r2 = Integer.TYPE;
        return r3.getMethod("addFontFromBuffer", new Class[]{ByteBuffer.class, r2, FontVariationAxis[].class, r2, r2});
    }

    public Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> r3) {
        Class<?> r32 = Array.newInstance(r3, 1).getClass();
        Class r0 = Integer.TYPE;
        Method r33 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{r32, r0, r0});
        r33.setAccessible(true);
        return r33;
    }

    public Class<?> obtainFontFamily() {
        return Class.forName("android.graphics.FontFamily");
    }

    public Constructor<?> obtainFontFamilyCtor(Class<?> r1) {
        return r1.getConstructor(null);
    }

    public Method obtainFreezeMethod(Class<?> r2) {
        return r2.getMethod("freeze", null);
    }
}
