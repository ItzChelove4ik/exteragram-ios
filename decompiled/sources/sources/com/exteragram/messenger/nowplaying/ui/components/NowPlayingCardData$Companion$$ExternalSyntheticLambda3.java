package com.exteragram.messenger.nowplaying.ui.components;

import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Ref;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingCardData$Companion$$ExternalSyntheticLambda3 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ NowPlayingDTO f$0;
    public final /* synthetic */ ImageLocation f$1;
    public final /* synthetic */ AtomicBoolean f$2;
    public final /* synthetic */ Ref.ObjectRef f$3;
    public final /* synthetic */ ImageReceiver f$4;
    public final /* synthetic */ NowPlayingCardData.Callback f$5;

    public /* synthetic */ NowPlayingCardData$Companion$$ExternalSyntheticLambda3(NowPlayingDTO r1, ImageLocation r2, AtomicBoolean r3, Ref.ObjectRef r4, ImageReceiver r5, NowPlayingCardData.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r11, boolean r12, boolean r13, boolean r14) {
        NowPlayingCardData.Companion.m1509$r8$lambda$BLCb8OxhFAr78ldS5qeufW04M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r11, r12, r13, r14);
    }
}
