package androidx.media3.session;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Consumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda47 implements Consumer {
    public final /* synthetic */ AudioAttributes f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda47(AudioAttributes r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r2) {
        MediaSessionStub.m926$r8$lambda$voxCYz7Fjco7fKBPtN_S3Exok(this.f$0, this.f$1, (PlayerWrapper) r2);
    }
}
