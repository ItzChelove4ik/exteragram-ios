package com.google.android.gms.wearable;

@Deprecated
/* loaded from: classes5.dex */
public interface ChannelApi {

    @Deprecated
    public interface ChannelListener {
        void onChannelClosed(Channel r1, int r2, int r3);

        void onChannelOpened(Channel r1);

        void onInputClosed(Channel r1, int r2, int r3);

        void onOutputClosed(Channel r1, int r2, int r3);
    }
}
