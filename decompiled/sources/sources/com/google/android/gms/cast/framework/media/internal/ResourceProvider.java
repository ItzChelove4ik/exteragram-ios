package com.google.android.gms.cast.framework.media.internal;

import androidx.annotation.Keep;
import com.google.android.gms.cast.framework.R;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class ResourceProvider {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Map zza = null;

    static {
        HashMap r0 = new HashMap();
        r0.put("smallIconDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_small_icon));
        r0.put("stopLiveStreamDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_stop_live_stream));
        r0.put("pauseDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_pause));
        r0.put("playDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_play));
        r0.put("skipNextDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_skip_next));
        r0.put("skipPrevDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_skip_prev));
        r0.put("forwardDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_forward));
        r0.put("forward10DrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_forward10));
        r0.put("forward30DrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_forward30));
        r0.put("rewindDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_rewind));
        r0.put("rewind10DrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_rewind10));
        r0.put("rewind30DrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_rewind30));
        r0.put("disconnectDrawableResId", Integer.valueOf(R.drawable.cast_ic_notification_disconnect));
        r0.put("notificationImageSizeDimenResId", Integer.valueOf(R.dimen.cast_notification_image_size));
        r0.put("castingToDeviceStringResId", Integer.valueOf(R.string.cast_casting_to_device));
        r0.put("stopLiveStreamStringResId", Integer.valueOf(R.string.cast_stop_live_stream));
        r0.put("pauseStringResId", Integer.valueOf(R.string.cast_pause));
        r0.put("playStringResId", Integer.valueOf(R.string.cast_play));
        r0.put("skipNextStringResId", Integer.valueOf(R.string.cast_skip_next));
        r0.put("skipPrevStringResId", Integer.valueOf(R.string.cast_skip_prev));
        r0.put("forwardStringResId", Integer.valueOf(R.string.cast_forward));
        r0.put("forward10StringResId", Integer.valueOf(R.string.cast_forward_10));
        r0.put("forward30StringResId", Integer.valueOf(R.string.cast_forward_30));
        r0.put("rewindStringResId", Integer.valueOf(R.string.cast_rewind));
        r0.put("rewind10StringResId", Integer.valueOf(R.string.cast_rewind_10));
        r0.put("rewind30StringResId", Integer.valueOf(R.string.cast_rewind_30));
        r0.put("disconnectStringResId", Integer.valueOf(R.string.cast_disconnect));
        zza = Collections.unmodifiableMap(r0);
    }

    public ResourceProvider() {
    }

    @Keep
    public static Integer findResourceByName(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return (Integer) zza.get(r1);
    }
}
