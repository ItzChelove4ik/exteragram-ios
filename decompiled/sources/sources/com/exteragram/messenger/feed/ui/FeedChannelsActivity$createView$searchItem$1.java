package com.exteragram.messenger.feed.ui;

import android.widget.EditText;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;", "onSearchExpand", _UrlKt.FRAGMENT_ENCODE_SET, "onSearchCollapse", "onTextChanged", "editText", "Landroid/widget/EditText;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class FeedChannelsActivity$createView$searchItem$1 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
    final /* synthetic */ FeedChannelsActivity this$0;

    public FeedChannelsActivity$createView$searchItem$1(FeedChannelsActivity r1) {
        this.this$0 = r1;
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
    public void onSearchCollapse() {
        FeedChannelsActivity.access$setSearching$p(this.this$0, false);
        FeedChannelsActivity.access$setQuery$p(this.this$0, null);
        ActionBarMenuItem r0 = FeedChannelsActivity.access$getOtherItem$p(this.this$0);
        if (r0 == null) goto L5;
        r0.setVisibility(0);
    L5:
        UniversalAdapter r3 = this.this$0.getListView$TMessagesProj().adapter;
        if (r3 == null) goto L9;
        r3.update(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
    public void onSearchExpand() {
        FeedChannelsActivity.access$setSearching$p(this.this$0, true);
        ActionBarMenuItem r2 = FeedChannelsActivity.access$getOtherItem$p(this.this$0);
        if (r2 == null) goto L6;
        r2.setVisibility(8);
        return;
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
    public void onTextChanged(EditText r3) {
        FeedChannelsActivity.access$setQuery$p(this.this$0, StringsKt.trim(r3.getText().toString()).toString().toLowerCase(Locale.getDefault()));
        UniversalAdapter r2 = this.this$0.getListView$TMessagesProj().adapter;
        if (r2 == null) goto L6;
        r2.update(true);
        return;
    }
}
