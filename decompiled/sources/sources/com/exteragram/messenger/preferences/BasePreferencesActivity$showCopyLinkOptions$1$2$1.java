package com.exteragram.messenger.preferences;

import android.content.Context;
import androidx.collection.LongSparseArray;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ShareAlert;

@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"com/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1", "Lorg/telegram/ui/Components/ShareAlert;", "onSend", _UrlKt.FRAGMENT_ENCODE_SET, "dialogIds", "Landroidx/collection/LongSparseArray;", "Lorg/telegram/tgnet/TLRPC$Dialog;", NotificationBadge.NewHtcHomeBadger.COUNT, _UrlKt.FRAGMENT_ENCODE_SET, "topic", "Lorg/telegram/tgnet/TLRPC$TL_forumTopic;", "showToast", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BasePreferencesActivity$showCopyLinkOptions$1$2$1 extends ShareAlert {
    final /* synthetic */ BasePreferencesActivity this$0;

    public BasePreferencesActivity$showCopyLinkOptions$1$2$1(String r9, BasePreferencesActivity r10, Context r11, Theme.ResourcesProvider r12) {
        this.this$0 = r10;
        super(r11, null, r9, false, r9, false, r12);
    }

    @Override // org.telegram.ui.Components.ShareAlert
    public void onSend(LongSparseArray<TLRPC.Dialog> r3, int r4, TLRPC.TL_forumTopic r5, boolean r6) {
        if (r6 == true) goto L5;
        return;
    L5:
        if (r3 != null) goto L7;
    L15:
        String r32 = LocaleController.formatString(R.string.SettingLinkToChats, new Object[]{LocaleController.formatPluralString("Chats", r4, new Object[0])});
    L16:
        Bulletin r2 = BulletinFactory.of(this.this$0).createSimpleBulletin(R.raw.forward, r32);
        r2.hideAfterBottomSheet = false;
        r2.show(true);
        return;
    L7:
        if (r3.size() != 1) goto L15;
        long r33 = r3.valueAt(0).id;
        if (r33 != 0) goto L11;
    L14:
        r32 = LocaleController.getString(R.string.SettingLinkToSavedMessages);
        goto L16
    L11:
        if (r33 == this.this$0.getUserConfig().clientUserId) goto L14;
        r32 = LocaleController.formatString(R.string.SettingLinkToUser, new Object[]{this.this$0.getMessagesController().getPeerName(r33, true)});
        goto L16
    }
}
