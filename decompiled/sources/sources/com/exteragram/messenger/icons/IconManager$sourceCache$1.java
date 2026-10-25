package com.exteragram.messenger.icons;

import android.graphics.Bitmap;
import androidx.collection.LruCache;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0014¨\u0006\b"}, d2 = {"com/exteragram/messenger/icons/IconManager$sourceCache$1", "Landroidx/collection/LruCache;", "Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", "Landroid/graphics/Bitmap;", "sizeOf", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "value", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IconManager$sourceCache$1 extends LruCache<IconManager.SourceCacheKey, Bitmap> {
    public IconManager$sourceCache$1(int r1) {
        super(r1);
    }

    /* renamed from: sizeOf, reason: avoid collision after fix types in other method */
    public int sizeOf2(IconManager.SourceCacheKey r1, Bitmap r2) {
        return r2.getByteCount() / 1024;
    }

    @Override // androidx.collection.LruCache
    public /* bridge */ /* synthetic */ int sizeOf(IconManager.SourceCacheKey r1, Bitmap r2) {
        return sizeOf2(r1, r2);
    }
}
