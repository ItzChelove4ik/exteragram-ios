package androidx.media3.session;

import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.Consumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ TrackSelectionParameters f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda1(MediaSessionStub r1, TrackSelectionParameters r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r2) {
        MediaSessionStub.$r8$lambda$wfDxSfPfVlMZB5zb3giyUnuOv18(this.f$0, this.f$1, (PlayerWrapper) r2);
    }
}
