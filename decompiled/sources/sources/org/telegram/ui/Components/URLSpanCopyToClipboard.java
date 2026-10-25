package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public class URLSpanCopyToClipboard extends URLSpanNoUnderline {
    private BaseFragment fragment;

    public URLSpanCopyToClipboard(String r1, BaseFragment r2) {
        super(r1);
        this.fragment = r2;
    }

    @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View r1) {
        AndroidUtilities.addToClipboard(getURL());
        BulletinFactory.of(this.fragment).createCopyLinkBulletin().show();
    }
}
