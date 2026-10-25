package com.google.android.gms.wearable;

import android.os.Parcelable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.wearable.Wearable;

/* loaded from: classes5.dex */
public abstract class ChannelClient extends GoogleApi<Wearable.WearableOptions> {

    public interface Channel extends Parcelable {
    }

    public static abstract class ChannelCallback {
        public ChannelCallback() {
        }

        public abstract void onChannelClosed(Channel r1, int r2, int r3);

        public abstract void onChannelOpened(Channel r1);

        public abstract void onInputClosed(Channel r1, int r2, int r3);

        public abstract void onOutputClosed(Channel r1, int r2, int r3);
    }
}
