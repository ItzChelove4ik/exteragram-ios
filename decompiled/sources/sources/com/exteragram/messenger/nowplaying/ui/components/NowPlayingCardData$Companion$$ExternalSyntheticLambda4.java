package com.exteragram.messenger.nowplaying.ui.components;

import android.graphics.Bitmap;
import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Ref;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingCardData$Companion$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Bitmap f$0;
    public final /* synthetic */ NowPlayingDTO f$1;
    public final /* synthetic */ ImageLocation f$2;
    public final /* synthetic */ AtomicBoolean f$3;
    public final /* synthetic */ Ref.ObjectRef f$4;
    public final /* synthetic */ ImageReceiver f$5;
    public final /* synthetic */ NowPlayingCardData.Callback f$6;

    public /* synthetic */ NowPlayingCardData$Companion$$ExternalSyntheticLambda4(Bitmap r1, NowPlayingDTO r2, ImageLocation r3, AtomicBoolean r4, Ref.ObjectRef r5, ImageReceiver r6, NowPlayingCardData.Callback r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NowPlayingCardData.Companion.$r8$lambda$6P9lS5wgcdlqEm8f33JBkYmM3QQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
