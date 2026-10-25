package com.exteragram.messenger.preferences;

import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrolled", _UrlKt.FRAGMENT_ENCODE_SET, "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", _UrlKt.FRAGMENT_ENCODE_SET, "dy", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BasePreferencesActivity$createView$6$3 extends RecyclerView.OnScrollListener {
    final /* synthetic */ BasePreferencesActivity this$0;

    public BasePreferencesActivity$createView$6$3(BasePreferencesActivity r1) {
        this.this$0 = r1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView r2, int r3, int r4) {
        BasePreferencesActivity.access$updateHeaderFade(this.this$0, true);
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (BasePreferencesActivity.access$getScrollableViewNoiseSuppressor$p(this.this$0) == null) goto L9;
        BasePreferencesActivity.access$getScrollableViewNoiseSuppressor$p(this.this$0).onScrolled(r3, r4);
        BasePreferencesActivity.access$blur3_InvalidateBlur(this.this$0);
        return;
    }
}
