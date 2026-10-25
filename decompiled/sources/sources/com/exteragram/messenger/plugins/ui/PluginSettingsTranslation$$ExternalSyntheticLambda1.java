package com.exteragram.messenger.plugins.ui;

import java.util.List;
import kotlin.jvm.functions.Function0;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginSettingsTranslation$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Function0 f$2;
    public final /* synthetic */ PluginSettingsTranslation f$3;
    public final /* synthetic */ List f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ String[] f$6;

    public /* synthetic */ PluginSettingsTranslation$$ExternalSyntheticLambda1(List r1, boolean r2, Function0 r3, PluginSettingsTranslation r4, List r5, String r6, String[] r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        PluginSettingsTranslation.$r8$lambda$6sDJjyrsuB15zfDuAj74Z4Tce9A(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
