package org.telegram.ui.Components.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.chat.layouts.ChatActivityFadeView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatActivitySearchContainer extends FrameLayout {
    private final ChatActivityFadeView fadeView;

    public ChatActivitySearchContainer(Context r2) {
        super(r2);
        ChatActivityFadeView r0 = new ChatActivityFadeView(r2);
        this.fadeView = r0;
        addView(r0, LayoutHelper.createFrameMatchParent());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View r1) {
        super.onViewAdded(r1);
        bringChildToFront(this.fadeView);
    }

    public void setFade(int r2, int r3) {
        this.fadeView.setFadeZoneTop(r2);
        this.fadeView.setFadeZoneBottom(r3);
    }

    public void setup(BlurredBackgroundDrawableViewFactory r2, BlurredBackgroundColorProvider r3) {
        this.fadeView.setup(r2, r3);
        this.fadeView.setIgnoreFastWay(true);
        this.fadeView.setFadeHeightTop(AndroidUtilities.dp(48.0f));
        this.fadeView.setFadeHeightBottom(AndroidUtilities.dp(48.0f));
    }
}
