package org.telegram.utils.camera.roundvideo;

import org.telegram.utils.camera.roundvideo.RoundVideoMp4Writer;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes6.dex */
public final /* synthetic */ class RoundVideoSession$$ExternalSyntheticLambda13 implements RoundVideoMp4Writer.Listener {
    public final /* synthetic */ RoundVideoSession f$0;
    public final /* synthetic */ RoundVideoSession.OutputGeneration f$1;

    public /* synthetic */ RoundVideoSession$$ExternalSyntheticLambda13(RoundVideoSession r1, RoundVideoSession.OutputGeneration r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoMp4Writer.Listener
    public final void onBytesAvailable(long r2) {
        RoundVideoSession.m22182$r8$lambda$wwoBaFBoOXSIzBqMsXt5hoPsc(this.f$0, this.f$1, r2);
    }
}
