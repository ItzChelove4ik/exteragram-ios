package com.exteragram.messenger.plugins.ui.components;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$3$1", "Landroidx/recyclerview/widget/DefaultItemAnimator;", "onMoveAnimationUpdate", _UrlKt.FRAGMENT_ENCODE_SET, "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class InstallPluginBottomSheet$3$1 extends DefaultItemAnimator {
    final /* synthetic */ InstallPluginBottomSheet this$0;

    public InstallPluginBottomSheet$3$1(InstallPluginBottomSheet r1) {
        this.this$0 = r1;
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator
    public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
        super.onMoveAnimationUpdate(r1);
        InstallPluginBottomSheet.access$getContainerView$p$s126290054(this.this$0).invalidate();
    }
}
