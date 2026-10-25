package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK = null;
    private static ArrayList<WeakReference<TintContextWrapper>> sCache;
    private final Resources mResources;
    private final Resources.Theme mTheme;

    static {
        CACHE_LOCK = new Object();
    }

    private TintContextWrapper(Context r3) {
        super(r3);
        if (VectorEnabledTintResources.shouldBeUsed() == false) goto L6;
        VectorEnabledTintResources r0 = new VectorEnabledTintResources(this, r3.getResources());
        this.mResources = r0;
        Resources.Theme r02 = r0.newTheme();
        this.mTheme = r02;
        r02.setTo(r3.getTheme());
        return;
    L6:
        this.mResources = new TintResources(this, r3.getResources());
        this.mTheme = null;
    }

    private static boolean shouldWrap(Context r1) {
        if ((r1 instanceof TintContextWrapper) == false) goto L5;
        return false;
    L5:
        if ((r1.getResources() instanceof TintResources) == false) goto L7;
        return false;
    L7:
        if ((r1.getResources() instanceof VectorEnabledTintResources) == false) goto L10;
        return false;
    L10:
        return VectorEnabledTintResources.shouldBeUsed();
    }

    public static Context wrap(Context r4) {
        if (shouldWrap(r4) == false) goto L36;
        Object r0 = CACHE_LOCK;
        monitor-enter(r0);
        ArrayList<WeakReference<TintContextWrapper>> r1 = sCache;     // Catch: Throwable -> L9
        if (r1 != null) goto L11;
        sCache = new ArrayList();     // Catch: Throwable -> L9
    L31:
        TintContextWrapper r12 = new TintContextWrapper(r4);     // Catch: Throwable -> L9
        sCache.add(new WeakReference(r12));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L11:
        int r13 = r1.size() - 1;
    L12:
        if (r13 < 0) goto L19;
        WeakReference<TintContextWrapper> r2 = sCache.get(r13);     // Catch: Throwable -> L9
        if (r2 != null) goto L16;
    L17:
        sCache.remove(r13);     // Catch: Throwable -> L9
    L18:
        r13 = r13 - 1;
        goto L12
    L16:
        if (r2.get() != null) goto L18;
    L19:
        int r14 = sCache.size() - 1;
    L20:
        if (r14 < 0) goto L31;
        WeakReference<TintContextWrapper> r22 = sCache.get(r14);     // Catch: Throwable -> L9
        if (r22 == null) goto L24;
        TintContextWrapper r23 = r22.get();     // Catch: Throwable -> L9
    L25:
        if (r23 == null) goto L30;
        if (r23.getBaseContext() != r4) goto L30;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r23;
    L30:
        r14 = r14 - 1;
        goto L20
    L24:
        r23 = null;
    L9:
        th = move-exception;
        throw th;
    L36:
        return r4;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.mResources;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme r0 = this.mTheme;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return super.getTheme();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int r2) {
        Resources.Theme r0 = this.mTheme;
        if (r0 != null) goto L6;
        super.setTheme(r2);
        return;
    L6:
        r0.applyStyle(r2, true);
    }
}
