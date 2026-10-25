package org.telegram.ui;

import android.text.style.ClickableSpan;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$CaptionTextView$$ExternalSyntheticLambda1 implements LinkSpanDrawable.LinksTextView.OnLinkPress {
    public final /* synthetic */ PhotoViewer.CaptionTextView f$0;
    public final /* synthetic */ Utilities.Callback3 f$1;

    public /* synthetic */ PhotoViewer$CaptionTextView$$ExternalSyntheticLambda1(PhotoViewer.CaptionTextView r1, Utilities.Callback3 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView.OnLinkPress
    public final void run(ClickableSpan r2) {
        PhotoViewer.CaptionTextView.$r8$lambda$S3wTOxIcLltYn7fa03hYj78wWTA(this.f$0, this.f$1, r2);
    }
}
