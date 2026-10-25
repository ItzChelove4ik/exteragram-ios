package com.exteragram.messenger.plugins.ui.components;

import com.exteragram.messenger.plugins.Plugin;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public final /* synthetic */ class InstallPluginBottomSheet$$ExternalSyntheticLambda32 implements Utilities.Callback {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Plugin f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ InstallPluginBottomSheet f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ BulletinFactory f$5;

    public /* synthetic */ InstallPluginBottomSheet$$ExternalSyntheticLambda32(AtomicBoolean r1, Plugin r2, Runnable r3, InstallPluginBottomSheet r4, String r5, BulletinFactory r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        InstallPluginBottomSheet.$r8$lambda$qWRuZ2J8_oT7EeUQTT2otMF0ew0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TLRPC.TL_messages_stickerSet) r8);
    }
}
