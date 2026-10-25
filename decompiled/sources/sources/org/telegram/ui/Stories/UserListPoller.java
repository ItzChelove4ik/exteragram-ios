package org.telegram.ui.Stories;

import android.view.View;
import java.util.ArrayList;
import kotlin.time.DurationKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.support.LongSparseLongArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes3.dex */
public class UserListPoller {
    private static UserListPoller[] istances;
    ArrayList<Long> collectedDialogIds;
    final int currentAccount;
    ArrayList<Long> dialogIds;
    Runnable requestCollectedRunnables;
    ArrayList<Integer> runningRequests;
    LongSparseLongArray userPollLastTime;

    /* renamed from: org.telegram.ui.Stories.UserListPoller$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ UserListPoller this$0;

        public static /* synthetic */ void $r8$lambda$61rVoe1u7Mevb7ZKAMfCZuQ1ADw(AnonymousClass1 r0, ArrayList r1, Vector r2, TLRPC.TL_error r3) {
            r0.lambda$run$0(r1, r2, r3);
        }

        public AnonymousClass1(UserListPoller r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0(ArrayList r8, Vector r9, TLRPC.TL_error r10) {
            if (r9 == null) goto L32;
            ArrayList r102 = new ArrayList();
            ArrayList r0 = new ArrayList();
            int r2 = 0;
        L5:
            if (r2 >= r9.objects.size()) goto L25;
            long r3 = ((Long) r8.get(r2)).longValue();
            UserListPoller r4 = this.this$0;
            if (r3 <= 0) goto L16;
            TLRPC.User r32 = MessagesController.getInstance(r4.currentAccount).getUser((Long) r8.get(r2));
            if (r32 == null) goto L24;
            TLRPC.TL_recentStory r42 = (TLRPC.TL_recentStory) r9.objects.get(r2);
            r32.stories_max_id = r42;
            int r5 = r32.flags2;
            if (r42 == null) goto L14;
            r32.flags2 = r5 | 32;
        L15:
            r102.add(r32);
            goto L24
        L14:
            r32.flags2 = r5 & (-33);
        L24:
            r2 = r2 + 1;
            goto L5
        L16:
            TLRPC.Chat r33 = MessagesController.getInstance(r4.currentAccount).getChat((Long) r8.get(r2));
            if (r33 == null) goto L24;
            TLRPC.TL_recentStory r43 = (TLRPC.TL_recentStory) r9.objects.get(r2);
            r33.stories_max_id = r43;
            int r52 = r33.flags2;
            if (r43 == null) goto L22;
            r33.flags2 = r52 | 16;
        L23:
            r0.add(r33);
            goto L24
        L22:
            r33.flags2 = r52 & (-17);
            goto L23
        L25:
            MessagesStorage.getInstance(this.this$0.currentAccount).putUsersAndChats(r102, r0, true, true);
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{0});
            return;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.this$0.collectedDialogIds.isEmpty() == true) goto L11;
            ArrayList r0 = new ArrayList(this.this$0.collectedDialogIds);
            this.this$0.collectedDialogIds.clear();
            TL_stories.TL_stories_getPeerMaxIDs r1 = new TL_stories.TL_stories_getPeerMaxIDs();
            int r2 = 0;
        L6:
            if (r2 >= r0.size()) goto L8;
            r1.id.add(MessagesController.getInstance(this.this$0.currentAccount).getInputPeer(((Long) r0.get(r2)).longValue()));
            r2 = r2 + 1;
            goto L6
        L8:
            ConnectionsManager.getInstance(this.this$0.currentAccount).sendRequestTyped(r1, new AiTonesController$$ExternalSyntheticLambda0(), new UserListPoller$1$$ExternalSyntheticLambda0(this, r0));
            return;
        }
    }

    static {
        istances = new UserListPoller[16];
    }

    private UserListPoller(int r2) {
        this.userPollLastTime = new LongSparseLongArray();
        this.dialogIds = new ArrayList();
        this.collectedDialogIds = new ArrayList();
        this.runningRequests = new ArrayList();
        this.requestCollectedRunnables = new AnonymousClass1(this);
        this.currentAccount = r2;
    }

    public static UserListPoller getInstance(int r2) {
        UserListPoller[] r0 = istances;
        if (r0[r2] != null) goto L6;
        r0[r2] = new UserListPoller(r2);
    L6:
        return istances[r2];
    }

    public void checkList(RecyclerListView r14) {
        long r0 = System.currentTimeMillis();
        this.dialogIds.clear();
        int r2 = 0;
    L4:
        if (r2 >= r14.getChildCount()) goto L38;
        View r3 = r14.getChildAt(r2);
        if ((r3 instanceof DialogCell) == false) goto L9;
        long r32 = ((DialogCell) r3).getDialogId();
    L12:
        int r8 = this.currentAccount;
        if (r32 <= 0) goto L29;
        TLRPC.User r7 = MessagesController.getInstance(r8).getUser(Long.valueOf(r32));
        if (r7 == null) goto L36;
        if (r7.bot == true) goto L36;
        if (r7.self == true) goto L36;
        if (r7.contact == true) goto L36;
        TLRPC.UserStatus r72 = r7.status;
        if (r72 == null) goto L36;
        if ((r72 instanceof TLRPC.TL_userStatusEmpty) == true) goto L36;
        if ((r0 - this.userPollLastTime.get(r32, 0)) <= DurationKt.MILLIS_IN_HOUR) goto L36;
        this.userPollLastTime.put(r32, r0);
        this.dialogIds.add(Long.valueOf(r32));
    L36:
        r2 = r2 + 1;
        goto L4
    L29:
        TLRPC.Chat r73 = MessagesController.getInstance(r8).getChat(Long.valueOf(-r32));
        if (ChatObject.isChannel(r73) == false) goto L36;
        if (ChatObject.isMonoForum(r73) == true) goto L36;
        if ((r0 - this.userPollLastTime.get(r32, 0)) <= DurationKt.MILLIS_IN_HOUR) goto L36;
        this.userPollLastTime.put(r32, r0);
        this.dialogIds.add(Long.valueOf(r32));
        goto L36
    L9:
        if ((r3 instanceof UserCell) == false) goto L11;
        r32 = ((UserCell) r3).getDialogId();
        goto L12
    L11:
        r32 = 0;
        goto L12
    L38:
        if (this.dialogIds.isEmpty() == true) goto L54;
        this.collectedDialogIds.addAll(this.dialogIds);
        AndroidUtilities.cancelRunOnUIThread(this.requestCollectedRunnables);
        AndroidUtilities.runOnUIThread(this.requestCollectedRunnables, 300);
        return;
    }
}
