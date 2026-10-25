package org.telegram.ui.Components;

import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;

/* loaded from: classes3.dex */
public interface ChatActivityInterface {
    default void checkAndUpdateAvatar() {
    }

    ActionBar getActionBar();

    ChatAvatarContainer getAvatarContainer();

    SizeNotifierFrameLayout getContentView();

    TLRPC.Chat getCurrentChat();

    default TLRPC.User getCurrentUser() {
        return null;
    }

    long getDialogId();

    ChatObject.Call getGroupCall();

    default long getMergeDialogId() {
        return 0;
    }

    default long getTopicId() {
        return 0;
    }

    default boolean openedWithLivestream() {
        return false;
    }

    default void scrollToMessageId(int r1, int r2, boolean r3, int r4, boolean r5, int r6) {
    }

    default boolean shouldShowImport() {
        return false;
    }
}
