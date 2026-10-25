package org.telegram.messenger;

import android.content.Context;
import android.graphics.drawable.Drawable;
import org.telegram.ui.ArticleViewer;

/* loaded from: classes5.dex */
public class ChatMessageSharedResources {
    public Drawable chat_msgAvatarLiveLocationDrawable;
    public Drawable chat_redLocationIcon;
    public Context context;
    public ArticleViewer.Resources inRichMessageResources;
    public ArticleViewer.Resources outRichMessageResources;

    public ChatMessageSharedResources(Context r1) {
        this.context = r1;
    }

    public Drawable getAvatarLiveLocation() {
        if (this.chat_msgAvatarLiveLocationDrawable != null) goto L6;
        this.chat_msgAvatarLiveLocationDrawable = this.context.getResources().getDrawable(R.drawable.livepin).mutate();
    L6:
        return this.chat_msgAvatarLiveLocationDrawable;
    }

    public Drawable getRedLocationIcon() {
        if (this.chat_redLocationIcon != null) goto L6;
        this.chat_redLocationIcon = this.context.getResources().getDrawable(R.drawable.map_pin).mutate();
    L6:
        return this.chat_redLocationIcon;
    }
}
