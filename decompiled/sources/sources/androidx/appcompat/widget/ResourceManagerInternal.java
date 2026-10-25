package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.resources.R$drawable;
import androidx.collection.LongSparseArray;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public final class ResourceManagerInternal {
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = null;
    private static final PorterDuff.Mode DEFAULT_MODE = null;
    private static ResourceManagerInternal INSTANCE;
    private SimpleArrayMap<String, Object> mDelegates;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches;
    private boolean mHasCheckedVectorDrawableSetup;
    private ResourceManagerHooks mHooks;
    private SparseArrayCompat<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;

    public static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int r1) {
            super(r1);
        }

        private static int generateCacheKey(int r1, PorterDuff.Mode r2) {
            return ((r1 + 31) * 31) + r2.hashCode();
        }

        public PorterDuffColorFilter get(int r1, PorterDuff.Mode r2) {
            return get(Integer.valueOf(generateCacheKey(r1, r2)));
        }

        public PorterDuffColorFilter put(int r1, PorterDuff.Mode r2, PorterDuffColorFilter r3) {
            return put(Integer.valueOf(generateCacheKey(r1, r2)), r3);
        }
    }

    public interface ResourceManagerHooks {
        Drawable createDrawableFor(ResourceManagerInternal r1, Context r2, int r3);

        ColorStateList getTintListForDrawableRes(Context r1, int r2);

        PorterDuff.Mode getTintModeForDrawableRes(int r1);

        boolean tintDrawable(Context r1, int r2, Drawable r3);

        boolean tintDrawableUsingColorFilter(Context r1, int r2, Drawable r3);
    }

    static {
        DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
        COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    }

    public ResourceManagerInternal() {
        this.mDrawableCaches = new WeakHashMap(0);
    }

    private synchronized boolean addDrawableToCache(Context r3, long r4, Drawable r6) {
        monitor-enter(this);
        Drawable.ConstantState r62 = r6.getConstantState();     // Catch: Throwable -> L8
        if (r62 == null) goto L14;
        LongSparseArray<WeakReference<Drawable.ConstantState>> r0 = this.mDrawableCaches.get(r3);     // Catch: Throwable -> L8
        if (r0 != null) goto L10;
        r0 = new LongSparseArray();     // Catch: Throwable -> L8
        this.mDrawableCaches.put(r3, r0);     // Catch: Throwable -> L8
    L10:
        r0.put(r4, new WeakReference(r62));     // Catch: Throwable -> L8
        monitor-exit(this);
        return true;
    L14:
        monitor-exit(this);
        return false;
    L8:
        th = move-exception;
        throw th;
    }

    private void addTintListToCache(Context r2, int r3, ColorStateList r4) {
        if (this.mTintLists != null) goto L5;
        this.mTintLists = new WeakHashMap();
    L5:
        SparseArrayCompat<ColorStateList> r0 = this.mTintLists.get(r2);
        if (r0 != null) goto L8;
        r0 = new SparseArrayCompat();
        this.mTintLists.put(r2, r0);
    L8:
        r0.append(r3, r4);
    }

    private void checkVectorDrawableSetup(Context r2) {
        if (this.mHasCheckedVectorDrawableSetup == true) goto L12;
        this.mHasCheckedVectorDrawableSetup = true;
        Drawable r22 = getDrawable(r2, R$drawable.abc_vector_test);
        if (r22 != null) goto L8;
    L10:
        this.mHasCheckedVectorDrawableSetup = false;
        Segment$$ExternalSyntheticBUOutline1.m("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        return;
    L8:
        if (isVectorDrawable(r22) == false) goto L10;
        return;
    }

    private static long createCacheKey(TypedValue r4) {
        return (r4.assetCookie << 32) | r4.data;
    }

    private Drawable createDrawableIfNeeded(Context r5, int r6) {
        if (this.mTypedValue != null) goto L5;
        this.mTypedValue = new TypedValue();
    L5:
        TypedValue r0 = this.mTypedValue;
        r5.getResources().getValue(r6, r0, true);
        long r1 = createCacheKey(r0);
        Drawable r3 = getCachedDrawable(r5, r1);
        if (r3 == null) goto L8;
        return r3;
    L8:
        ResourceManagerHooks r32 = this.mHooks;
        if (r32 != null) goto L11;
        Drawable r62 = null;
    L12:
        if (r62 == null) goto L14;
        r62.setChangingConfigurations(r0.changingConfigurations);
        addDrawableToCache(r5, r1, r62);
    L14:
        return r62;
    L11:
        r62 = r32.createDrawableFor(this, r5, r6);
        goto L12
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList r1, PorterDuff.Mode r2, int[] r3) {
        if (r1 == null) goto L7;
        if (r2 != null) goto L6;
        return null;
    L6:
        return getPorterDuffColorFilter(r1.getColorForState(r3, 0), r2);
    L7:
        return null;
    }

    public static synchronized ResourceManagerInternal get() {
        monitor-enter(ResourceManagerInternal.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (INSTANCE != null) goto L9;
        ResourceManagerInternal r1 = new ResourceManagerInternal();     // Catch: Throwable -> L7
        INSTANCE = r1;     // Catch: Throwable -> L7
        installDefaultInflateDelegates(r1);     // Catch: Throwable -> L7
    L9:
        ResourceManagerInternal r12 = INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(ResourceManagerInternal.class);
        return r12;
    }

    private synchronized Drawable getCachedDrawable(Context r4, long r5) {
        monitor-enter(this);
        LongSparseArray<WeakReference<Drawable.ConstantState>> r0 = this.mDrawableCaches.get(r4);     // Catch: Throwable -> L15
        if (r0 != null) goto L8;
        monitor-exit(this);
        return null;
    L8:
        WeakReference<Drawable.ConstantState> r2 = r0.get(r5);     // Catch: Throwable -> L15
        if (r2 == null) goto L18;
        Drawable.ConstantState r22 = r2.get();     // Catch: Throwable -> L15
        if (r22 == null) goto L17;
        Drawable r42 = r22.newDrawable(r4.getResources());     // Catch: Throwable -> L15
        monitor-exit(this);
        return r42;
    L17:
        r0.remove(r5);     // Catch: Throwable -> L15
    L18:
        monitor-exit(this);
        return null;
    L15:
        th = move-exception;
        throw th;
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int r3, PorterDuff.Mode r4) {
        monitor-enter(ResourceManagerInternal.class);
        ColorFilterLruCache r1 = COLOR_FILTER_CACHE;     // Catch: Throwable -> L8
        PorterDuffColorFilter r2 = r1.get(r3, r4);     // Catch: Throwable -> L8
        if (r2 != null) goto L10;
        r2 = new PorterDuffColorFilter(r3, r4);     // Catch: Throwable -> L8
        r1.put(r3, r4, r2);     // Catch: Throwable -> L8
    L10:
        monitor-exit(ResourceManagerInternal.class);
        return r2;
    L8:
        th = move-exception;
        throw th;
    }

    private ColorStateList getTintListFromCache(Context r2, int r3) {
        WeakHashMap<Context, SparseArrayCompat<ColorStateList>> r1 = this.mTintLists;
        if (r1 == null) goto L8;
        SparseArrayCompat<ColorStateList> r12 = r1.get(r2);
        if (r12 == null) goto L8;
        return r12.get(r3);
    L8:
        return null;
    }

    private static void installDefaultInflateDelegates(ResourceManagerInternal r0) {
    }

    private static boolean isVectorDrawable(Drawable r1) {
        if ((r1 instanceof VectorDrawableCompat) == false) goto L5;
        return true;
    L5:
        if ("android.graphics.drawable.VectorDrawable".equals(r1.getClass().getName()) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private Drawable loadDrawableFromDelegates(Context r10, int r11) {
        SimpleArrayMap<String, Object> r0 = this.mDelegates;
        if (r0 != null) goto L5;
    L42:
        return null;
    L5:
        if (r0.isEmpty() == true) goto L42;
        SparseArrayCompat<String> r02 = this.mKnownDrawableIdTags;
        if (r02 == null) goto L14;
        String r03 = r02.get(r11);
        if ("appcompat_skip_skip".equals(r03) == true) goto L13;
        if (r03 == null) goto L16;
        if (this.mDelegates.get(r03) == null) goto L13;
    L16:
        if (this.mTypedValue != null) goto L18;
        this.mTypedValue = new TypedValue();
    L18:
        TypedValue r04 = this.mTypedValue;
        Resources r1 = r10.getResources();
        r1.getValue(r11, r04, true);
        long r4 = createCacheKey(r04);
        Drawable r6 = getCachedDrawable(r10, r4);
        if (r6 == null) goto L21;
        return r6;
    L21:
        CharSequence r7 = r04.string;
        if (r7 != null) goto L24;
    L39:
        if (r6 != null) goto L41;
        this.mKnownDrawableIdTags.append(r11, "appcompat_skip_skip");
    L41:
        return r6;
    L24:
        if (r7.toString().endsWith(".xml") == false) goto L39;
        XmlResourceParser r12 = r1.getXml(r11);     // Catch: Exception -> L34
        Xml.asAttributeSet(r12);     // Catch: Exception -> L34
    L26:
        int r72 = r12.next();     // Catch: Exception -> L34
        if (r72 == 2) goto L30;
        if (r72 != 1) goto L26;
    L30:
        if (r72 != 2) goto L37;
        String r13 = r12.getName();     // Catch: Exception -> L34
        this.mKnownDrawableIdTags.append(r11, r13);     // Catch: Exception -> L34
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mDelegates.get(r13));     // Catch: Exception -> L34
        if (r6 == null) goto L39;
        r6.setChangingConfigurations(r04.changingConfigurations);     // Catch: Exception -> L34
        addDrawableToCache(r10, r4, r6);     // Catch: Exception -> L34
        goto L39
    L37:
        throw new XmlPullParserException("No start tag found");     // Catch: Exception -> L34
    L34:
        e = move-exception;
        Log.e("ResourceManagerInternal", "Exception while inflating drawable", e);
    L13:
        return null;
    L14:
        this.mKnownDrawableIdTags = new SparseArrayCompat();
        goto L16
    }

    private Drawable tintDrawable(Context r2, int r3, boolean r4, Drawable r5) {
        ColorStateList r0 = getTintList(r2, r3);
        if (r0 == null) goto L8;
        Drawable r22 = DrawableCompat.wrap(r5.mutate());
        DrawableCompat.setTintList(r22, r0);
        PorterDuff.Mode r1 = getTintMode(r3);
        if (r1 == null) goto L7;
        DrawableCompat.setTintMode(r22, r1);
    L7:
        return r22;
    L8:
        ResourceManagerHooks r02 = this.mHooks;
        if (r02 == null) goto L14;
        if (r02.tintDrawable(r2, r3, r5) == false) goto L14;
    L18:
        return r5;
    L14:
        if (tintDrawableUsingColorFilter(r2, r3, r5) == true) goto L18;
        if (r4 == false) goto L18;
        return null;
    }

    public static void tintDrawable(Drawable r2, TintInfo r3, int[] r4) {
        int[] r0 = r2.getState();
        if (r2.mutate() == r2) goto L5;
        Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
        return;
    L5:
        if ((r2 instanceof LayerDrawable) == true) goto L7;
    L9:
        boolean r02 = r3.mHasTintList;
        if (r02 == false) goto L12;
    L16:
        if (r02 == false) goto L18;
        ColorStateList r03 = r3.mTintList;
    L20:
        if (r3.mHasTintMode == false) goto L22;
        PorterDuff.Mode r32 = r3.mTintMode;
    L23:
        r2.setColorFilter(createTintFilter(r03, r32, r4));
        return;
    L22:
        r32 = DEFAULT_MODE;
        goto L23
    L18:
        r03 = null;
        goto L20
    L12:
        if (r3.mHasTintMode == true) goto L16;
        r2.clearColorFilter();
        return;
    L7:
        if (r2.isStateful() == false) goto L9;
        r2.setState(new int[0]);
        r2.setState(r0);
        goto L9
    }

    public synchronized Drawable getDrawable(Context r2, int r3) {
        monitor-enter(this);
        Drawable r22 = getDrawable(r2, r3, false);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Drawable getDrawable(Context r2, int r3, boolean r4) {
        monitor-enter(this);
        checkVectorDrawableSetup(r2);     // Catch: Throwable -> L6
        Drawable r0 = loadDrawableFromDelegates(r2, r3);     // Catch: Throwable -> L6
        if (r0 != null) goto L8;
        r0 = createDrawableIfNeeded(r2, r3);     // Catch: Throwable -> L6
    L8:
        if (r0 != null) goto L10;
        r0 = ContextCompat.getDrawable(r2, r3);     // Catch: Throwable -> L6
    L10:
        if (r0 == null) goto L12;
        r0 = tintDrawable(r2, r3, r4, r0);     // Catch: Throwable -> L6
    L12:
        if (r0 == null) goto L14;
        DrawableUtils.fixDrawable(r0);     // Catch: Throwable -> L6
    L14:
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized ColorStateList getTintList(Context r2, int r3) {
        monitor-enter(this);
        ColorStateList r0 = getTintListFromCache(r2, r3);     // Catch: Throwable -> L12
        if (r0 != null) goto L14;
        ResourceManagerHooks r02 = this.mHooks;     // Catch: Throwable -> L12
        if (r02 != null) goto L8;
        r0 = null;
    L9:
        if (r0 == null) goto L14;
        addTintListToCache(r2, r3, r0);     // Catch: Throwable -> L12
        goto L14
    L8:
        r0 = r02.getTintListForDrawableRes(r2, r3);     // Catch: Throwable -> L12
    L14:
        monitor-exit(this);
        return r0;
    L12:
        th = move-exception;
        throw th;
    }

    public PorterDuff.Mode getTintMode(int r1) {
        ResourceManagerHooks r0 = this.mHooks;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getTintModeForDrawableRes(r1);
    }

    public synchronized void onConfigurationChanged(Context r2) {
        monitor-enter(this);
        LongSparseArray<WeakReference<Drawable.ConstantState>> r22 = this.mDrawableCaches.get(r2);     // Catch: Throwable -> L7
        if (r22 == null) goto L9;
        r22.clear();     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Drawable onDrawableLoadedFromResources(Context r2, VectorEnabledTintResources r3, int r4) {
        monitor-enter(this);
        Drawable r0 = loadDrawableFromDelegates(r2, r4);     // Catch: Throwable -> L6
        if (r0 != null) goto L8;
        r0 = r3.getDrawableCanonical(r4);     // Catch: Throwable -> L6
    L8:
        if (r0 == null) goto L12;
        Drawable r22 = tintDrawable(r2, r4, false, r0);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L12:
        monitor-exit(this);
        return null;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void setHooks(ResourceManagerHooks r1) {
        monitor-enter(this);
        this.mHooks = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public boolean tintDrawableUsingColorFilter(Context r1, int r2, Drawable r3) {
        ResourceManagerHooks r0 = this.mHooks;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.tintDrawableUsingColorFilter(r1, r2, r3) == false) goto L10;
        return true;
    L10:
        return false;
    }
}
