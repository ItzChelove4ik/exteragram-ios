package com.exteragram.messenger.nowplaying.ui.components;

import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$2$drawable$1", "Landroid/graphics/drawable/GradientDrawable;", "onBoundsChange", _UrlKt.FRAGMENT_ENCODE_SET, "r", "Landroid/graphics/Rect;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingCard$2$drawable$1 extends GradientDrawable {
    public NowPlayingCard$2$drawable$1() {
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r2) {
        super.onBoundsChange(r2);
        setGradientRadius(r2.width() * 2.0f);
    }
}
