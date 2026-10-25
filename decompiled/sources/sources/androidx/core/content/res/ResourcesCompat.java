package androidx.core.content.res;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public abstract class ResourcesCompat {
    private static final Object sColorStateCacheLock = null;
    private static final WeakHashMap<ColorStateListCacheKey, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = null;
    private static final ThreadLocal<TypedValue> sTempTypedValue = null;

    /* loaded from: classes3.dex */
    public static class ColorStateListCacheEntry {
        final Configuration mConfiguration;
        final int mThemeHash;
        final ColorStateList mValue;

        public ColorStateListCacheEntry(ColorStateList r1, Configuration r2, Resources.Theme r3) {
            this.mValue = r1;
            this.mConfiguration = r2;
            if (r3 != null) goto L5;
            int r12 = 0;
        L6:
            this.mThemeHash = r12;
            return;
        L5:
            r12 = r3.hashCode();
            goto L6
        }
    }

    /* loaded from: classes3.dex */
    public static final class ColorStateListCacheKey {
        final Resources mResources;
        final Resources.Theme mTheme;

        public ColorStateListCacheKey(Resources r1, Resources.Theme r2) {
            this.mResources = r1;
            this.mTheme = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (ColorStateListCacheKey.class != r5.getClass()) goto L15;
            ColorStateListCacheKey r52 = (ColorStateListCacheKey) r5;
            if (this.mResources.equals(r52.mResources) == false) goto L15;
            if (ObjectsCompat.equals(this.mTheme, r52.mTheme) == false) goto L15;
            return true;
        }

        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{this.mResources, this.mTheme});
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class FontCallback {
        public static /* synthetic */ void $r8$lambda$RNHJCidnOgHeog0l_qKz9BOOvF4(FontCallback r0, Typeface r1) {
            r0.onFontRetrieved(r1);
        }

        /* renamed from: $r8$lambda$iq3V68-a5RqNx7LeKJ2odF7JKlo, reason: not valid java name */
        public static /* synthetic */ void m669$r8$lambda$iq3V68a5RqNx7LeKJ2odF7JKlo(FontCallback r0, int r1) {
            r0.onFontRetrievalFailed(r1);
        }

        public FontCallback() {
        }

        public static Handler getHandler(Handler r1) {
            if (r1 == null) goto L4;
            return r1;
        L4:
            return new Handler(Looper.getMainLooper());
        }

        public final void callbackFailAsync(int r2, Handler r3) {
            getHandler(r3).post(new ResourcesCompat$FontCallback$$ExternalSyntheticLambda1(this, r2));
        }

        public final void callbackSuccessAsync(Typeface r2, Handler r3) {
            getHandler(r3).post(new ResourcesCompat$FontCallback$$ExternalSyntheticLambda0(this, r2));
        }

        public abstract void onFontRetrievalFailed(int r1);

        public abstract void onFontRetrieved(Typeface r1);
    }

    /* loaded from: classes3.dex */
    public static final class ThemeCompat {

        public static class Api23Impl {
            private static Method sRebaseMethod;
            private static boolean sRebaseMethodFetched;
            private static final Object sRebaseMethodLock = null;

            static {
                sRebaseMethodLock = new Object();
            }

            @SuppressLint({"BanUncheckedReflection"})
            public static void rebase(Resources.Theme r6) {
                Object r0 = sRebaseMethodLock;
                monitor-enter(r0);
            L10:
                th = move-exception;
                throw th;
            L6:
                if (sRebaseMethodFetched == false) goto L27;
            L15:
                Method r1 = sRebaseMethod;     // Catch: Throwable -> L10
                if (r1 != null) goto L31;
            L23:
                monitor-exit(r0);     // Catch: Throwable -> L10
                return;
            L31:
                r1.invoke(r6, null);     // Catch: Throwable -> L10 Throwable -> L19 IllegalAccessException -> L21
            L19:
                e = move-exception;
                Log.i("ResourcesCompat", "Failed to invoke rebase() method via reflection", e);     // Catch: Throwable -> L10
                sRebaseMethod = null;     // Catch: Throwable -> L10
                goto L23
            L27:
                Method r3 = Resources.Theme.class.getDeclaredMethod("rebase", null);     // Catch: Throwable -> L10 NoSuchMethodException -> L12
                sRebaseMethod = r3;     // Catch: Throwable -> L10 NoSuchMethodException -> L12
                r3.setAccessible(true);     // Catch: Throwable -> L10 NoSuchMethodException -> L12
            L14:
                sRebaseMethodFetched = true;     // Catch: Throwable -> L10
                goto L15
            L12:
                e = move-exception;
                Log.i("ResourcesCompat", "Failed to retrieve rebase() method", e);     // Catch: Throwable -> L10
                goto L14
            }
        }

        public static class Api29Impl {
            public static void rebase(Resources.Theme r0) {
                r0.rebase();
            }
        }

        public static void rebase(Resources.Theme r2) {
            if (Build.VERSION.SDK_INT < 29) goto L6;
            Api29Impl.rebase(r2);
            return;
        L6:
            Api23Impl.rebase(r2);
        }
    }

    static {
        sTempTypedValue = new ThreadLocal();
        sColorStateCaches = new WeakHashMap(0);
        sColorStateCacheLock = new Object();
    }

    private static void addColorStateListToCache(ColorStateListCacheKey r3, int r4, ColorStateList r5, Resources.Theme r6) {
        Object r0 = sColorStateCacheLock;
        monitor-enter(r0);
        WeakHashMap<ColorStateListCacheKey, SparseArray<ColorStateListCacheEntry>> r1 = sColorStateCaches;     // Catch: Throwable -> L7
        SparseArray<ColorStateListCacheEntry> r2 = r1.get(r3);     // Catch: Throwable -> L7
        if (r2 != null) goto L9;
        r2 = new SparseArray();     // Catch: Throwable -> L7
        r1.put(r3, r2);     // Catch: Throwable -> L7
    L9:
        r2.append(r4, new ColorStateListCacheEntry(r5, r3.mResources.getConfiguration(), r6));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static ColorStateList getCachedColorStateList(ColorStateListCacheKey r5, int r6) {
        Object r0 = sColorStateCacheLock;
        monitor-enter(r0);
        SparseArray<ColorStateListCacheEntry> r1 = sColorStateCaches.get(r5);     // Catch: Throwable -> L17
        if (r1 != null) goto L7;
    L26:
        monitor-exit(r0);     // Catch: Throwable -> L17
        return null;
    L7:
        if (r1.size() <= 0) goto L26;
        ColorStateListCacheEntry r2 = r1.get(r6);     // Catch: Throwable -> L17
        if (r2 == null) goto L26;
        if (r2.mConfiguration.equals(r5.mResources.getConfiguration()) == false) goto L25;
        Resources.Theme r52 = r5.mTheme;     // Catch: Throwable -> L17
        if (r52 == null) goto L15;
    L19:
        if (r52 == null) goto L25;
        if (r2.mThemeHash != r52.hashCode()) goto L25;
    L22:
        ColorStateList r53 = r2.mValue;     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
        return r53;
    L15:
        if (r2.mThemeHash == 0) goto L22;
    L25:
        r1.remove(r6);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        throw th;
    }

    public static Typeface getCachedFont(Context r8, int r9) {
        if (r8.isRestricted() == false) goto L7;
        return null;
    L7:
        return loadFont(r8, r9, new TypedValue(), 0, null, null, false, true);
    }

    public static ColorStateList getColorStateList(Resources r2, int r3, Resources.Theme r4) {
        ColorStateListCacheKey r0 = new ColorStateListCacheKey(r2, r4);
        ColorStateList r1 = getCachedColorStateList(r0, r3);
        if (r1 == null) goto L5;
        return r1;
    L5:
        ColorStateList r12 = inflateColorStateList(r2, r3, r4);
        if (r12 == null) goto L10;
        addColorStateListToCache(r0, r3, r12, r4);
        return r12;
    L10:
        return r2.getColorStateList(r3, r4);
    }

    public static Drawable getDrawable(Resources r0, int r1, Resources.Theme r2) {
        return r0.getDrawable(r1, r2);
    }

    public static Drawable getDrawableForDensity(Resources r0, int r1, int r2, Resources.Theme r3) {
        return r0.getDrawableForDensity(r1, r2, r3);
    }

    public static Typeface getFont(Context r8, int r9) {
        if (r8.isRestricted() == false) goto L7;
        return null;
    L7:
        return loadFont(r8, r9, new TypedValue(), 0, null, null, false, false);
    }

    public static Typeface getFont(Context r8, int r9, TypedValue r10, int r11, FontCallback r12) {
        if (r8.isRestricted() == false) goto L7;
        return null;
    L7:
        return loadFont(r8, r9, r10, r11, r12, null, true, false);
    }

    public static void getFont(Context r8, int r9, FontCallback r10, Handler r11) {
        Preconditions.checkNotNull(r10);
        if (r8.isRestricted() == false) goto L6;
        r10.callbackFailAsync(-4, r11);
        return;
    L6:
        loadFont(r8, r9, new TypedValue(), 0, r10, r11, false, false);
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> r0 = sTempTypedValue;
        TypedValue r1 = r0.get();
        if (r1 != null) goto L6;
        TypedValue r12 = new TypedValue();
        r0.set(r12);
        return r12;
    L6:
        return r1;
    }

    private static ColorStateList inflateColorStateList(Resources r2, int r3, Resources.Theme r4) {
        if (isColorInt(r2, r3) == false) goto L11;
        return null;
    L11:
        return ColorStateListInflaterCompat.createFromXml(r2, r2.getXml(r3), r4);
    L8:
        e = move-exception;
        Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e);
        return null;
    }

    private static boolean isColorInt(Resources r2, int r3) {
        TypedValue r0 = getTypedValue();
        r2.getValue(r3, r0, true);
        int r22 = r0.type;
        if (r22 >= 28) goto L5;
        return false;
    L5:
        if (r22 > 31) goto L9;
        return true;
    L9:
        return false;
    }

    private static Typeface loadFont(Context r9, int r10, TypedValue r11, int r12, FontCallback r13, Handler r14, boolean r15, boolean r16) {
        Resources r1 = r9.getResources();
        r1.getValue(r10, r11, true);
        Typeface r92 = loadFont(r9, r1, r11, r10, r12, r13, r14, r15, r16);
        if (r92 != null) goto L9;
        if (r13 != null) goto L9;
        if (r16 == true) goto L9;
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(r10) + " could not be retrieved.");
    L9:
        return r92;
    }

    private static Typeface loadFont(Context r13, Resources r14, TypedValue r15, int r16, int r17, FontCallback r18, Handler r19, boolean r20, boolean r21) {
        CharSequence r0 = r15.string;
        if (r0 == null) goto L49;
        String r3 = r0.toString();
        if (r3.startsWith("res/") == true) goto L9;
        if (r18 == null) goto L8;
        r18.callbackFailAsync(-3, r19);
    L8:
        return null;
    L9:
        Typeface r02 = TypefaceCompat.findFromCache(r14, r16, r3, r15.assetCookie, r17);
        if (r02 == null) goto L14;
        if (r18 == null) goto L13;
        r18.callbackSuccessAsync(r02, r19);
    L13:
        return r02;
    L14:
        if (r21 == false) goto L52;
        return null;
    L52:
    L23:
        e = e;
    L43:
        Log.e("ResourcesCompat", "Failed to read xml resource ".concat(r3), e);
    L45:
        if (r18 == null) goto L47;
        r18.callbackFailAsync(-3, r19);
    L47:
        return null;
    L25:
        e = e;
    L44:
        Log.e("ResourcesCompat", "Failed to parse xml resource ".concat(r3), e);
        goto L45
    L17:
        if (r3.toLowerCase().endsWith(".xml") == false) goto L36;
        FontResourcesParserCompat.FamilyResourceEntry r1 = FontResourcesParserCompat.parse(r14.getXml(r16), r14);     // Catch: IOException -> L23 XmlPullParserException -> L25
        if (r1 != null) goto L50;
        Log.e("ResourcesCompat", "Failed to find font-family tag");     // Catch: IOException -> L23 XmlPullParserException -> L25
        if (r18 == null) goto L27;
        r18.callbackFailAsync(-3, r19);     // Catch: IOException -> L23 XmlPullParserException -> L25
    L27:
        return null;
    L50:
        return TypefaceCompat.createFromResourcesFamilyXml(r13, r1, r14, r16, r3, r15.assetCookie, r17, r18, r19, r20);
    L32:
        e = e;
        r3 = r3;
    L34:
        e = e;
        r3 = r3;
        goto L44
    L36:
        Typeface r132 = TypefaceCompat.createFromResourcesFontFile(r13, r14, r16, r3, r15.assetCookie, r17);     // Catch: IOException -> L23 XmlPullParserException -> L25
        if (r18 == null) goto L42;
        if (r132 == null) goto L41;
        r18.callbackSuccessAsync(r132, r19);     // Catch: IOException -> L23 XmlPullParserException -> L25
        return r132;
    L41:
        r18.callbackFailAsync(-3, r19);     // Catch: IOException -> L23 XmlPullParserException -> L25
    L42:
        return r132;
    L49:
        throw new Resources.NotFoundException("Resource \"" + r14.getResourceName(r16) + "\" (" + Integer.toHexString(r16) + ") is not a Font: " + r15);
    }
}
