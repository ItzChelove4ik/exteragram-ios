package org.telegram.ui;

import android.text.style.ClickableSpan;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$CaptionTextView$$ExternalSyntheticLambda0 implements LinkSpanDrawable.LinksTextView.OnLinkPress {
    public final /* synthetic */ PhotoViewer.CaptionTextView f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ PhotoViewer$CaptionTextView$$ExternalSyntheticLambda0(PhotoViewer.CaptionTextView r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView.OnLinkPress
    public final void run(ClickableSpan r2) {
        PhotoViewer.CaptionTextView.m17398$r8$lambda$SQ6Jh1TR2ZzQGmc2D4imlUHlKE(this.f$0, this.f$1, r2);
    }
}
