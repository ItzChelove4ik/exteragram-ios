package com.exteragram.messenger.plugins.ui.components;

import com.exteragram.messenger.plugins.Plugin;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public final /* synthetic */ class InstallPluginBottomSheet$$ExternalSyntheticLambda28 implements Runnable {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$1;
    public final /* synthetic */ Plugin f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ InstallPluginBottomSheet f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ BulletinFactory f$6;

    public /* synthetic */ InstallPluginBottomSheet$$ExternalSyntheticLambda28(AtomicBoolean r1, TLRPC.TL_messages_stickerSet r2, Plugin r3, Runnable r4, InstallPluginBottomSheet r5, String r6, BulletinFactory r7) {
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
        InstallPluginBottomSheet.$r8$lambda$PcrlDgT4JGXCfZRinXFErAkdLs0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
