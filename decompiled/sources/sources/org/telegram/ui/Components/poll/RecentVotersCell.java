package org.telegram.ui.Components.poll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarsListDrawable;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.MessageSeenView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class RecentVotersCell extends FrameLayout {
    public final AvatarsListDrawable avatarsListDrawable;
    private UniversalRecyclerView listView;
    public final TextView textView;

    /* renamed from: org.telegram.ui.Components.poll.RecentVotersCell$1, reason: invalid class name */
    public class AnonymousClass1 extends UniversalRecyclerView {
        final /* synthetic */ RecentVotersCell this$0;
        final /* synthetic */ int val$estimated;

        public AnonymousClass1(RecentVotersCell r1, BaseFragment r2, Utilities.Callback2 r3, Utilities.Callback5 r4, Utilities.Callback5Return r5, int r6) {
            this.this$0 = r1;
            this.val$estimated = r6;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r3, int r4) {
            int r32 = Math.min(AndroidUtilities.dp(220.0f), View.MeasureSpec.getSize(r3));
            View.MeasureSpec.getSize(r4);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(MathUtils.clamp(this.val$estimated, 1, 5) * 48), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.RecentVotersCell$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ RecentVotersCell this$0;
        final /* synthetic */ VotesList val$list;

        public AnonymousClass2(RecentVotersCell r1, VotesList r2) {
            this.this$0 = r1;
            this.val$list = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (VotesList.m13733$$Nest$fgetcompleted(this.val$list) == false) goto L5;
            return;
        L5:
            if (VotesList.m13734$$Nest$fgetloading(this.val$list) == false) goto L7;
            return;
        L7:
            if (((RecentVotersCell.m13732$$Nest$fgetlistView(this.this$0).adapter.getItemCount() - 1) - RecentVotersCell.m13732$$Nest$fgetlistView(this.this$0).layoutManager.findLastCompletelyVisibleItemPosition()) >= 5) goto L12;
            this.val$list.load();
            return;
        }
    }

    public static class Factory extends UItem.UItemFactory<MessageSeenView.UserCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(TLObject r1, long r2, int r4, View.OnClickListener r5) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.longValue = r2;
            r0.intValue = r4;
            r0.clickCallback = r5;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            MessageSeenView.UserCell r12 = (MessageSeenView.UserCell) r1;
            r12.setUser((TLObject) r2.object, r2.intValue, true);
            r12.setOnClickListener(r2.clickCallback);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r3, UItem r4) {
            if (r3.longValue != r4.longValue) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public MessageSeenView.UserCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            MessageSeenView.UserCell r0 = new MessageSeenView.UserCell(r1);
            r0.setBackground(Theme.getSelectorDrawable(false));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r3, UItem r4) {
            if (r3.longValue != r4.longValue) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class FlickerFactory extends UItem.UItemFactory<FlickerLoadingView> {
        static {
            UItem.UItemFactory.setup(new FlickerFactory());
        }

        public FlickerFactory() {
        }

        public static UItem of() {
            return UItem.ofFactory(FlickerFactory.class);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public FlickerLoadingView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            FlickerLoadingView r0 = new FlickerLoadingView(r1);
            r0.setViewType(16);
            r0.setMinimumHeight(AndroidUtilities.dp(48.0f));
            return r0;
        }
    }

    public static class FlickerFactory2 extends UItem.UItemFactory<FlickerLoadingView> {
        static {
            UItem.UItemFactory.setup(new FlickerFactory2());
        }

        public FlickerFactory2() {
        }

        public static UItem of() {
            return UItem.ofFactory(FlickerFactory2.class);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public FlickerLoadingView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            FlickerLoadingView r0 = new FlickerLoadingView(r1);
            r0.setViewType(16);
            r0.setMinimumHeight(AndroidUtilities.dp(48.0f));
            return r0;
        }
    }

    public static class VotesList {
        private boolean completed;
        private int count;
        public final int currentAccount;
        private boolean loading;
        public final int msgId;
        private String nextOffset;
        private final Utilities.Callback<Long> onClick;
        private final Runnable onUpdate;
        public final byte[] option;
        public final TLRPC.InputPeer peer;
        private ArrayList<TLRPC.MessagePeerVote> votes;

        public static /* synthetic */ void $r8$lambda$4OJByseeIpYcoIIDtI20t_oAxtM(VotesList r0, TLRPC.TL_messages_votesList r1, TLRPC.TL_error r2) {
            r0.lambda$load$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$jgZNv5jcs1q_UX1s1Mowdo2dnLI(VotesList r0, long r1, View r3) {
            r0.lambda$fillItems$1(r1, r3);
        }

        /* renamed from: -$$Nest$fgetcompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13733$$Nest$fgetcompleted(VotesList r0) {
            return r0.completed;
        }

        /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13734$$Nest$fgetloading(VotesList r0) {
            return r0.loading;
        }

        private VotesList(int r2, TLRPC.InputPeer r3, int r4, byte[] r5, Runnable r6, Utilities.Callback<Long> r7) {
            this.count = -1;
            this.votes = new ArrayList();
            this.currentAccount = r2;
            this.peer = r3;
            this.msgId = r4;
            this.option = r5;
            this.onUpdate = r6;
            this.onClick = r7;
        }

        public /* synthetic */ VotesList(int r1, TLRPC.InputPeer r2, int r3, byte[] r4, Runnable r5, Utilities.Callback r6, RecentVotersCellIA r7) {
            this(r1, r2, r3, r4, r5, r6);
        }

        private /* synthetic */ void lambda$fillItems$1(long r1, View r3) {
            Utilities.Callback<Long> r0 = this.onClick;
            if (r0 == null) goto L6;
            r0.run(Long.valueOf(r1));
            return;
        }

        private /* synthetic */ void lambda$load$0(TLRPC.TL_messages_votesList r4, TLRPC.TL_error r5) {
            boolean r52 = false;
            this.loading = false;
            if (r4 == null) goto L11;
            MessagesController.getInstance(this.currentAccount).putUsers(r4.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r4.chats, false);
            String r1 = r4.next_offset;
            this.nextOffset = r1;
            if (r1 != null) goto L7;
            r52 = true;
        L7:
            this.completed = r52;
            this.count = r4.count;
            this.votes.addAll(r4.votes);
            Runnable r3 = this.onUpdate;
            if (r3 == null) goto L13;
            r3.run();
            return;
        L13:
            return;
        L11:
            this.nextOffset = null;
            this.completed = true;
        }

        public void fillItems(ArrayList<UItem> r8, UniversalAdapter r9) {
            r8.clear();
            ArrayList<TLRPC.MessagePeerVote> r92 = this.votes;
            int r0 = r92.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L6;
            TLRPC.MessagePeerVote r2 = r92.get(r1);
            r1 = r1 + 1;
            TLRPC.MessagePeerVote r22 = r2;
            long r3 = DialogObject.getPeerDialogId(r22.peer);
            r8.add(Factory.of(MessagesController.getInstance(this.currentAccount).getUserOrChat(r3), r3, r22.date, new RecentVotersCell$VotesList$$ExternalSyntheticLambda0(this, r3)));
            goto L3
        L6:
            if (this.completed == false) goto L8;
            return;
        L8:
            if (this.votes.isEmpty() == false) goto L11;
            r8.add(FlickerFactory2.of());
            r8.add(FlickerFactory2.of());
            r8.add(FlickerFactory2.of());
            r8.add(FlickerFactory2.of());
            r8.add(FlickerFactory2.of());
            return;
        L11:
            r8.add(FlickerFactory.of());
        }

        public void load() {
            if (this.completed == false) goto L5;
            return;
        L5:
            if (this.loading == true) goto L14;
            this.loading = true;
            TLRPC.TL_messages_getPollVotes r0 = new TLRPC.TL_messages_getPollVotes();
            String r1 = this.nextOffset;
            if (r1 == null) goto L10;
            int r2 = 10;
        L11:
            r0.limit = r2;
            r0.peer = this.peer;
            r0.id = this.msgId;
            r0.option = this.option;
            r0.offset = r1;
            ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new RecentVotersCell$VotesList$$ExternalSyntheticLambda1(this));
            return;
        L10:
            r2 = 15;
            goto L11
        }
    }

    public static /* synthetic */ void $r8$lambda$TwU5a5_0nL6Rl0hX3ceeJJLTbMw(RecentVotersCell r0) {
        r0.lambda$createListView$0();
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m13732$$Nest$fgetlistView(RecentVotersCell r0) {
        return r0.listView;
    }

    public RecentVotersCell(Context r7, int r8, Theme.ResourcesProvider r9) {
        super(r7);
        this.avatarsListDrawable = new AvatarsListDrawable(r8, this, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dpf2(1.0f));
        TextView r6 = new TextView(r7);
        this.textView = r6;
        r6.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r9));
        r6.setLines(1);
        r6.setSingleLine(true);
        r6.setGravity(19);
        r6.setEllipsize(TextUtils.TruncateAt.END);
        r6.setTextSize(1, 16.0f);
        setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(68.0f), 0);
        addView(r6, LayoutHelper.createFrameMatchParent());
    }

    private /* synthetic */ void lambda$createListView$0() {
        this.listView.adapter.update(true);
    }

    public RecyclerListView createListView(BaseFragment r10, long r11, int r13, byte[] r14, int r15, Utilities.Callback<Long> r16) {
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L5;
        return r0;
    L5:
        VotesList r1 = new VotesList(r10.getCurrentAccount(), r10.getMessagesController().getInputPeer(r11), r13, r14, new RecentVotersCell$$ExternalSyntheticLambda0(this), r16, null);
        AndroidUtilities.runOnUIThread(new RecentVotersCell$$ExternalSyntheticLambda1(r1), 1000);
        AnonymousClass1 r2 = new AnonymousClass1(this, r10, new RecentVotersCell$$ExternalSyntheticLambda2(r1), null, null, r15);
        this.listView = r2;
        r2.adapter.setApplyBackground(false);
        this.listView.addOnScrollListener(new AnonymousClass2(this, r1));
        return this.listView;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r7) {
        super.dispatchDraw(r7);
        this.avatarsListDrawable.setBounds((getWidth() - AndroidUtilities.dp(11.0f)) - ((int) this.avatarsListDrawable.getAnimatedWidth()), AndroidUtilities.dp(12.0f), getWidth() - AndroidUtilities.dp(11.0f), AndroidUtilities.dp(12.0f) + AndroidUtilities.dp(24.0f));
        this.avatarsListDrawable.draw(r7);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.avatarsListDrawable.attach();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.avatarsListDrawable.detach();
    }

    public void setRecentVoters(List<TLRPC.Peer> r1, boolean r2) {
        this.avatarsListDrawable.set(r1, r2);
    }

    public void setText(String r1) {
        this.textView.setText(r1);
    }
}
