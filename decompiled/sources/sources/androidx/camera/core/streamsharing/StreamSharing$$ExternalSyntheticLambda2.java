package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;

/* loaded from: classes3.dex */
public final /* synthetic */ class StreamSharing$$ExternalSyntheticLambda2 implements SessionConfig.ErrorListener {
    public final /* synthetic */ StreamSharing f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ UseCaseConfig f$3;
    public final /* synthetic */ StreamSpec f$4;
    public final /* synthetic */ StreamSpec f$5;

    public /* synthetic */ StreamSharing$$ExternalSyntheticLambda2(StreamSharing r1, String r2, String r3, UseCaseConfig r4, StreamSpec r5, StreamSpec r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // androidx.camera.core.impl.SessionConfig.ErrorListener
    public final void onError(SessionConfig r9, SessionConfig.SessionError r10) {
        StreamSharing.$r8$lambda$ii4n5MMe9ie3N3t9WQpFcwenlq4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
