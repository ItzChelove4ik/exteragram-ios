package org.telegram.ui;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.LanguageDetector;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda458 implements LanguageDetector.StringCallback {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ ActionBarMenuSubItem f$4;
    public final /* synthetic */ AtomicBoolean f$5;
    public final /* synthetic */ AtomicReference f$6;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda458(ChatActivity r1, String[] r2, String r3, boolean r4, ActionBarMenuSubItem r5, AtomicBoolean r6, AtomicReference r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.messenger.LanguageDetector.StringCallback
    public final void run(String r9) {
        ChatActivity.m7505$r8$lambda$fMUUH66ENe8LLV5XTT52J1JAw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r9);
    }
}
