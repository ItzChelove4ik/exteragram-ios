package org.telegram.ui.iv;

import android.view.View;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.iv.ChatAttachAlertRichLayout;
import org.telegram.ui.iv.RichCommandSuggestions;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertRichLayout$1$$ExternalSyntheticLambda0 implements RichCommandSuggestions.MenuFactory {
    public final /* synthetic */ ChatAttachAlertRichLayout.AnonymousClass1 f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;

    public /* synthetic */ ChatAttachAlertRichLayout$1$$ExternalSyntheticLambda0(ChatAttachAlertRichLayout.AnonymousClass1 r1, Theme.ResourcesProvider r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.iv.RichCommandSuggestions.MenuFactory
    public final ItemOptions make(View r2) {
        return ChatAttachAlertRichLayout.AnonymousClass1.m21619$r8$lambda$1xYkbPAGsPtNY3omagHk4veYNM(this.f$0, this.f$1, r2);
    }
}
