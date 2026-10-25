package com.exteragram.messenger.plugins.ui.components;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J(\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"com/exteragram/messenger/plugins/ui/components/PluginEditTextCell$saveTextWatcher$1", "Landroid/text/TextWatcher;", "beforeTextChanged", _UrlKt.FRAGMENT_ENCODE_SET, "s", _UrlKt.FRAGMENT_ENCODE_SET, "start", _UrlKt.FRAGMENT_ENCODE_SET, NotificationBadge.NewHtcHomeBadger.COUNT, "after", "onTextChanged", "newText", "before", "afterTextChanged", "Landroid/text/Editable;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PluginEditTextCell$saveTextWatcher$1 implements TextWatcher {
    final /* synthetic */ PluginEditTextCell this$0;

    public PluginEditTextCell$saveTextWatcher$1(PluginEditTextCell r1) {
        this.this$0 = r1;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable r1) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        PluginEditTextCell.access$setValueToSave$p(this.this$0, r1.toString());
        PluginEditTextCell.access$scheduleSave(this.this$0);
    }
}
