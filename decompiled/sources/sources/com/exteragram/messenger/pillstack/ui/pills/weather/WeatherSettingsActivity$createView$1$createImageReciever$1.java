package com.exteragram.messenger.pillstack.ui.pills.weather;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.exteragram.messenger.pillstack.ui.pills.weather.WeatherSettingsActivity;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ImageReceiver;
import org.telegram.ui.Components.CubicBezierInterpolator;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\tH\u0014¨\u0006\f"}, d2 = {"com/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1$createImageReciever$1", "Lorg/telegram/messenger/ImageReceiver;", "setImageBitmapByKey", _UrlKt.FRAGMENT_ENCODE_SET, "drawable", "Landroid/graphics/drawable/Drawable;", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "type", _UrlKt.FRAGMENT_ENCODE_SET, "memCache", "guid", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WeatherSettingsActivity$createView$1$createImageReciever$1 extends ImageReceiver {
    final /* synthetic */ WeatherSettingsActivity this$0;

    public WeatherSettingsActivity$createView$1$createImageReciever$1(WeatherSettingsActivity.AnonymousClass1 r1, WeatherSettingsActivity r2) {
        this.this$0 = r2;
        super(r1);
    }

    @Override // org.telegram.messenger.ImageReceiver
    public boolean setImageBitmapByKey(Drawable r4, String r5, int r6, boolean r7, int r8) {
        if (r4 == null) goto L10;
        if (r6 == 1) goto L10;
        View r0 = WeatherSettingsActivity.access$getMapMarker$p(this.this$0);
        if (r0 != null) goto L8;
        r0 = null;
    L8:
        r0.animate().alpha(1.0f).translationY(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_BACK).setDuration(250).start();
    L10:
        return super.setImageBitmapByKey(r4, r5, r6, r7, r8);
    }
}
