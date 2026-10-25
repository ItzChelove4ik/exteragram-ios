package com.exteragram.messenger.icons.ui;

import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBarMenuItem;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;", "onSearchExpand", _UrlKt.FRAGMENT_ENCODE_SET, "onSearchCollapse", "onTextChanged", "editText", "Landroid/widget/EditText;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconPacksEditorActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,388:1\n1#2:389\n*E\n"})
/* loaded from: classes4.dex */
public final class IconPacksEditorActivity$createView$searchItem$1 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
    final /* synthetic */ IconPacksEditorActivity this$0;

    public static void $r8$lambda$UHgwOIcW448g3rraVlWtzY1_HCY(IconPacksEditorActivity r0, EditText r1) {
        IconPacksEditorActivity.access$setQuery$p(r0, r1.getText().toString());
        IconPacksEditorActivity.access$updateAdapter(r0);
    }

    public IconPacksEditorActivity$createView$searchItem$1(IconPacksEditorActivity r1) {
        this.this$0 = r1;
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
    public void onSearchCollapse() {
        IconPacksEditorActivity.access$setSearching$p(this.this$0, false);
        IconPacksEditorActivity.access$setQuery$p(this.this$0, null);
        ActionBarMenuItem r0 = IconPacksEditorActivity.access$getOtherItem$p(this.this$0);
        if (r0 == null) goto L5;
        r0.setVisibility(0);
    L5:
        IconPacksEditorActivity.access$updateAdapter(this.this$0);
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
    public void onSearchExpand() {
        IconPacksEditorActivity.access$setSearching$p(this.this$0, true);
        ActionBarMenuItem r2 = IconPacksEditorActivity.access$getOtherItem$p(this.this$0);
        if (r2 == null) goto L6;
        r2.setVisibility(8);
        return;
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
    public void onTextChanged(EditText r3) {
        Runnable r0 = IconPacksEditorActivity.access$getSearchRunnable$p(this.this$0);
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        IconPacksEditorActivity$createView$searchItem$1$$ExternalSyntheticLambda0 r1 = new IconPacksEditorActivity$createView$searchItem$1$$ExternalSyntheticLambda0(this.this$0, r3);
        IconPacksEditorActivity.access$setSearchRunnable$p(this.this$0, r1);
        AndroidUtilities.runOnUIThread(r1, 200);
    }
}
