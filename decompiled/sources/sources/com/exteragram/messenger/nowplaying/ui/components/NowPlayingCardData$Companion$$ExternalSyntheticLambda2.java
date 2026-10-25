package com.exteragram.messenger.nowplaying.ui.components;

import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Ref;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingCardData$Companion$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ImageReceiver f$0;
    public final /* synthetic */ Ref.ObjectRef f$1;
    public final /* synthetic */ ImageLocation f$2;
    public final /* synthetic */ NowPlayingDTO f$3;
    public final /* synthetic */ AtomicBoolean f$4;
    public final /* synthetic */ NowPlayingCardData.Callback f$5;

    public /* synthetic */ NowPlayingCardData$Companion$$ExternalSyntheticLambda2(ImageReceiver r1, Ref.ObjectRef r2, ImageLocation r3, NowPlayingDTO r4, AtomicBoolean r5, NowPlayingCardData.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NowPlayingCardData.Companion.$r8$lambda$jgxY1JuRy8LY2jKCm85wpHU4Lj4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
