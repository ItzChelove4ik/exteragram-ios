package org.telegram.ui.Cells;

import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public interface IMessageCell {
    void didPressReactionFromLayout(TLRPC.ReactionCount r1, boolean r2, float r3, float r4);

    default boolean drawPinnedBottom() {
        return false;
    }

    default boolean drawPinnedTop() {
        return false;
    }

    float getAlpha();

    default ImageReceiver getAvatarImage() {
        return null;
    }

    default float getCheckBoxTranslation() {
        return 0.0f;
    }

    default MessageObject.GroupedMessagePosition getCurrentPosition() {
        return null;
    }

    float getDeltaBottom();

    int getHeight();

    default int getLayoutHeight() {
        return getMeasuredHeight();
    }

    int getMeasuredHeight();

    MessageObject getMessageObject();

    float getPivotX();

    float getScaleX();

    float getScaleY();

    default float getSlidingOffsetX() {
        return 0.0f;
    }

    float getX();

    float getY();

    default void setAnimationRunning(boolean r1, boolean r2) {
    }

    default boolean shouldDrawAlphaLayer() {
        return false;
    }

    default boolean willRemovedAfterAnimation() {
        return false;
    }
}
