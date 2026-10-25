package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;
    public final /* synthetic */ LoadEventInfo f$2;
    public final /* synthetic */ MediaLoadData f$3;
    public final /* synthetic */ IOException f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda8(MediaSourceList.ForwardingEventListener r1, Pair r2, LoadEventInfo r3, MediaLoadData r4, IOException r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSourceList.ForwardingEventListener.$r8$lambda$d_kLqle5eJxc6odA4dxFL6P9kJU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
