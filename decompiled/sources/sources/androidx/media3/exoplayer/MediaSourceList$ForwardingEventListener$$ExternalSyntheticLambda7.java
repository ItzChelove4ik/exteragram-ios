package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;
    public final /* synthetic */ LoadEventInfo f$2;
    public final /* synthetic */ MediaLoadData f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda7(MediaSourceList.ForwardingEventListener r1, Pair r2, LoadEventInfo r3, MediaLoadData r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSourceList.ForwardingEventListener.$r8$lambda$zhztQi2tdFJfXzsxdq2l1ej7BJE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
