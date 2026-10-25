package com.exteragram.messenger.icons;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.exteragram.messenger.icons.ui.picker.IconObserver;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0018\u00010\u000fR\u00020\u0001H\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\b\u0001\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/exteragram/messenger/icons/ExteraResources;", "Landroid/content/res/Resources;", "mResources", "<init>", "(Landroid/content/res/Resources;)V", "sourceAssets", "Landroid/content/res/AssetManager;", "getSourceAssets", "()Landroid/content/res/AssetManager;", "getDrawableForDensity", "Landroid/graphics/drawable/Drawable;", "id", _UrlKt.FRAGMENT_ENCODE_SET, "density", "theme", "Landroid/content/res/Resources$Theme;", "getOriginalDrawable", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExteraResources extends Resources {
    private final Resources mResources;
    private final AssetManager sourceAssets;

    public ExteraResources(Resources r4) {
        super(r4.getAssets(), r4.getDisplayMetrics(), r4.getConfiguration());
        this.mResources = r4;
        this.sourceAssets = r4.getAssets();
        IconManager.initialize$default(IconManager.INSTANCE, false, 1, null);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int r3, int r4, Resources.Theme r5) {
        IconObserver.INSTANCE.log(r3);
        IconManager r0 = IconManager.INSTANCE;
        Drawable r1 = r0.getDrawable(r3, r4, r5);
        if (r1 == null) goto L11;
        return r1;
    L11:
        r3 = r0.getIcon(r3);     // Catch: Throwable -> L7
    L10:
        return this.mResources.getDrawableForDensity(r3, r4, r5);
    L7:
        th = move-exception;
        FileLog.e(th);
        goto L10
    }

    public final Drawable getOriginalDrawable(int r2) {
        return this.mResources.getDrawable(r2, null);
    L5:
        return null;
    }

    public final AssetManager getSourceAssets() {
        return this.sourceAssets;
    }
}
