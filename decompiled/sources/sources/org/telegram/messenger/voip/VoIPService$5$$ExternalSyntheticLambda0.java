package org.telegram.messenger.voip;

import org.telegram.messenger.voip.VoIPService;
import org.webrtc.VideoSink;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$5$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VoIPService.AnonymousClass5 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ VideoSink f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ VoIPService$5$$ExternalSyntheticLambda0(VoIPService.AnonymousClass5 r1, String r2, VideoSink r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.AnonymousClass5.m5371$r8$lambda$O8ceM1ykJXQCmJ7DpVdotMQkGk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
