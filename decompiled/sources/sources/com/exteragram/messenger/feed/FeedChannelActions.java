package com.exteragram.messenger.feed;

import java.util.ArrayList;
import java.util.function.Consumer;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ItemOptions;

/* loaded from: classes4.dex */
public abstract class FeedChannelActions {
    /* renamed from: $r8$lambda$93MDwBmj6nCXiUj2TGXsm-0wccI, reason: not valid java name */
    public static /* synthetic */ void m1408$r8$lambda$93MDwBmj6nCXiUj2TGXsm0wccI(TLRPC.Chat r10, BaseFragment r11, Consumer r12, Runnable r13, boolean r14) {
        long r0 = -r10.id;
        if (ChatObject.isNotInChat(r10) == false) goto L5;
        r11.getMessagesController().deleteDialog(r0, 0, r14);
    L6:
        deleteFeedRows(r11, r0, r12);
        if (r13 == null) goto L10;
        r13.run();
        return;
    L10:
        return;
    L5:
        r11.getMessagesController().deleteParticipantFromChat(r10.id, r11.getMessagesController().getUser(Long.valueOf(r11.getUserConfig().getClientUserId())), null, r14, r14);
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$EHE5Su0wUEoEcAcmBHxtia6M4Vc(ChatActivity r0, TLRPC.Chat r1, Runnable r2, Consumer r3) {
        leaveChannel(r0, r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$oaBdlXDX2OHj536CCY2KV2GDUSY(ChatActivity r2, TLRPC.Chat r3) {
        r2.hideFeedChannelWithUndo(-r3.id, r3.title);
    }

    public static boolean canLeave(TLRPC.Chat r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.creator == false) goto L6;
        return false;
    L6:
        if (ChatObject.isNotInChat(r1) == true) goto L12;
        return true;
    L12:
        return false;
    }

    private static void deleteFeedRows(BaseFragment r1, long r2, Consumer<ArrayList<Integer>> r4) {
        ArrayList<Integer> r12 = FeedController.getInstance(r1.getCurrentAccount()).deleteHistory(r2, Integer.MAX_VALUE);
        if (r4 == null) goto L6;
        r4.accept(r12);
        return;
    }

    public static void leaveChannel(BaseFragment r10, TLRPC.Chat r11, Runnable r12, Consumer<ArrayList<Integer>> r13) {
        if (r10 == null) goto L9;
        if (r11 != null) goto L5;
        return;
    L5:
        if (r10.getParentActivity() == null) goto L11;
        AlertsCreator.createClearOrDeleteDialogAlert(r10, false, r11, null, false, true, false, false, new FeedChannelActions$$ExternalSyntheticLambda0(r11, r10, r13, r12));
        return;
    L11:
        return;
    }

    public static void showAvatarMenu(ChatActivity r8, ChatMessageCell r9, TLRPC.Chat r10, Runnable r11, Runnable r12, Consumer<ArrayList<Integer>> r13) {
        if (r8 == null) goto L19;
        if (r9 == null) goto L20;
        if (r10 == null) goto L21;
        ItemOptions r92 = ItemOptions.makeOptions(r8, r9);
        boolean r0 = r10.broadcast;
        if (r0 == false) goto L9;
        int r1 = R.drawable.msg_channel;
    L10:
        if (r0 == false) goto L12;
        int r02 = R.string.OpenChannel2;
    L13:
        ItemOptions r2 = r92.add(r1, LocaleController.getString(r02), r11).add(R.drawable.menu_hide_gift, LocaleController.getString(R.string.FeedHideChannel), new FeedChannelActions$$ExternalSyntheticLambda1(r8, r10));
        boolean r3 = canLeave(r10);
        int r4 = R.drawable.msg_leave;
        if (r10.broadcast == false) goto L16;
        int r93 = R.string.LeaveChannelMenu;
    L17:
        r2.addIf(r3, r4, LocaleController.getString(r93), true, new FeedChannelActions$$ExternalSyntheticLambda2(r8, r10, r12, r13)).setDrawScrim(false).setGravity(3).forceBottom(true).show();
        return;
    L16:
        r93 = R.string.LeaveMegaMenu;
        goto L17
    L12:
        r02 = R.string.OpenGroup2;
        goto L13
    L9:
        r1 = R.drawable.msg_discussion;
        goto L10
    L21:
        return;
    L20:
        return;
    }
}
