package org.telegram.ui.Stars;

import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda202 implements Runnable {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ CharSequence f$7;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda202(StarGiftSheet r1, Browser.Progress r2, AlertDialog r3, TLObject r4, TL_stars.TL_starGiftUnique r5, TLRPC.TL_error r6, long r7, CharSequence r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarGiftSheet.m18997$r8$lambda$rR2OIeat9eQsteJOofNc2bJgZY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
