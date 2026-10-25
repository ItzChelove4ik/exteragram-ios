package com.exteragram.messenger.nowplaying.ui.components;

import android.graphics.Bitmap;
import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Ref;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingCardData$Companion$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ NowPlayingDTO f$0;
    public final /* synthetic */ Integer f$1;
    public final /* synthetic */ Integer f$2;
    public final /* synthetic */ Bitmap f$3;
    public final /* synthetic */ ImageLocation f$4;
    public final /* synthetic */ AtomicBoolean f$5;
    public final /* synthetic */ Ref.ObjectRef f$6;
    public final /* synthetic */ ImageReceiver f$7;
    public final /* synthetic */ NowPlayingCardData.Callback f$8;

    public /* synthetic */ NowPlayingCardData$Companion$$ExternalSyntheticLambda5(NowPlayingDTO r1, Integer r2, Integer r3, Bitmap r4, ImageLocation r5, AtomicBoolean r6, Ref.ObjectRef r7, ImageReceiver r8, NowPlayingCardData.Callback r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NowPlayingCardData.Companion.$r8$lambda$2u7j2oskFsvnNiuPoirbvlLqfvc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
