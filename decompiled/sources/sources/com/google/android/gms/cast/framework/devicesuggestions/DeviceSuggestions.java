package com.google.android.gms.cast.framework.devicesuggestions;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.internal.cast.zzav;

/* loaded from: classes4.dex */
public final class DeviceSuggestions {
    private DeviceSuggestions() {
    }

    public static DeviceSuggestionsClient getClient(Activity r1) {
        return new zzav(r1);
    }

    public static DeviceSuggestionsClient getClient(Context r1) {
        return new zzav(r1);
    }
}
