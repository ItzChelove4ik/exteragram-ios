package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ReactedUserHolderView;
import org.telegram.ui.Components.ReactedHeaderView;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class ReactedUsersListView extends FrameLayout {
    private RecyclerView.Adapter adapter;
    public boolean canLoadMore;
    private int currentAccount;
    ArrayList<TLRPC.InputStickerSet> customEmojiStickerSets;
    ArrayList<ReactionsLayoutInBubble.VisibleReaction> customReactionsEmoji;
    private TLRPC.Reaction filter;
    public boolean isLoaded;
    public boolean isLoading;
    public RecyclerListView listView;
    private FlickerLoadingView loadingView;
    private MessageObject message;
    MessageContainsEmojiButton messageContainsEmojiButton;
    private String offset;
    private OnCustomEmojiSelectedListener onCustomEmojiSelectedListener;
    private OnHeightChangedListener onHeightChangedListener;
    private OnProfileSelectedListener onProfileSelectedListener;
    private OnProfileSelectedListener onProfileSelectedLongListener;
    private boolean onlySeenNow;
    private LongSparseArray<ArrayList<TLRPC.MessagePeerReaction>> peerReactionMap;
    private int predictiveCount;
    Theme.ResourcesProvider resourcesProvider;
    private boolean showReactionPreview;
    private List<TLRPC.MessagePeerReaction> userReactions;

    /* renamed from: org.telegram.ui.Components.ReactedUsersListView$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ ReactedUsersListView this$0;

        public AnonymousClass1(ReactedUsersListView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r4, int r5) {
            MessageContainsEmojiButton r0 = this.this$0.messageContainsEmojiButton;
            if (r0 == null) goto L5;
            r0.measure(r4, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), 0));
        L5:
            super.onMeasure(r4, r5);
            ReactedUsersListView.m12304$$Nest$mupdateHeight(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactedUsersListView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.Adapter {
        final /* synthetic */ ReactedUsersListView this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ boolean val$showReactionPreview;

        public AnonymousClass2(ReactedUsersListView r1, int r2, Context r3, Theme.ResourcesProvider r4, boolean r5) {
            this.this$0 = r1;
            this.val$currentAccount = r2;
            this.val$context = r3;
            this.val$resourcesProvider = r4;
            this.val$showReactionPreview = r5;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = ReactedUsersListView.m12300$$Nest$fgetuserReactions(this.this$0).size();
            if (this.this$0.customReactionsEmoji.isEmpty() == false) goto L5;
        L7:
            int r2 = 0;
        L9:
            return r0 + r2;
        L5:
            if (MessagesController.getInstance(this.val$currentAccount).premiumFeaturesBlocked() == true) goto L7;
            r2 = 1;
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 >= ReactedUsersListView.m12300$$Nest$fgetuserReactions(this.this$0).size()) goto L6;
            return 0;
        L6:
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            if (r2.getItemViewType() != 0) goto L6;
            ((ReactedUserHolderView) r2.itemView).setUserReaction((TLRPC.MessagePeerReaction) ReactedUsersListView.m12300$$Nest$fgetuserReactions(this.this$0).get(r3));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
            if (r9 == 0) goto L10;
            ReactedUsersListView r82 = this.this$0;
            MessageContainsEmojiButton r92 = r82.messageContainsEmojiButton;
            if (r92 != null) goto L6;
            ReactedUsersListView.m12303$$Nest$mupdateCustomReactionsButton(r82);
        L9:
            FrameLayout r83 = new FrameLayout(this.val$context);
            View r93 = new View(this.val$context);
            r93.setBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.val$resourcesProvider), 0.06f));
            r83.addView(r93, LayoutHelper.createFrame(-1, 8.0f));
            r83.addView(this.this$0.messageContainsEmojiButton, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 8.0f, 0.0f, 0.0f));
        L12:
            return new RecyclerListView.Holder(r83);
        L6:
            if (r92.getParent() == null) goto L9;
            ((ViewGroup) this.this$0.messageContainsEmojiButton.getParent()).removeView(this.this$0.messageContainsEmojiButton);
            goto L9
        L10:
            r83 = new ReactedUserHolderView(ReactedUserHolderView.STYLE_DEFAULT, this.val$currentAccount, this.val$context, this.val$resourcesProvider, true, this.val$showReactionPreview);
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactedUsersListView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ReactedUsersListView this$0;
        final /* synthetic */ LinearLayoutManager val$llm;

        public AnonymousClass3(ReactedUsersListView r1, LinearLayoutManager r2) {
            this.this$0 = r1;
            this.val$llm = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            ReactedUsersListView r12 = this.this$0;
            if (r12.isLoaded == true) goto L5;
            return;
        L5:
            if (r12.canLoadMore == true) goto L7;
            return;
        L7:
            if (r12.isLoading == false) goto L9;
            return;
        L9:
            if (this.val$llm.findLastVisibleItemPosition() < ((ReactedUsersListView.m12298$$Nest$fgetadapter(this.this$0).getItemCount() - 1) - ReactedUsersListView.m12301$$Nest$mgetLoadCount(this.this$0))) goto L15;
            ReactedUsersListView.m12302$$Nest$mload(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactedUsersListView$4, reason: invalid class name */
    public class AnonymousClass4 extends FlickerLoadingView {
        final /* synthetic */ ReactedUsersListView this$0;

        public AnonymousClass4(ReactedUsersListView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView
        public int getAdditionalHeight() {
            if (this.this$0.customReactionsEmoji.isEmpty() == true) goto L8;
            MessageContainsEmojiButton r1 = this.this$0.messageContainsEmojiButton;
            if (r1 != null) goto L7;
            return 0;
        L7:
            return r1.getMeasuredHeight() + AndroidUtilities.dp(8.0f);
        L8:
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactedUsersListView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ ReactedUsersListView this$0;

        public AnonymousClass5(ReactedUsersListView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ReactedUsersListView.m12299$$Nest$fgetloadingView(this.this$0).setVisibility(8);
        }
    }

    public static class ContainerLinerLayout extends LinearLayout {
        public boolean hasHeader;

        public ContainerLinerLayout(Context r1) {
            super(r1);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r1 = 0;
            RecyclerListView r2 = null;
            if (this.hasHeader == true) goto L19;
            int r0 = 0;
            int r3 = 0;
        L6:
            if (r0 >= getChildCount()) goto L20;
            if ((getChildAt(r0) instanceof ReactedUsersListView) == false) goto L18;
            r2 = ((ReactedUsersListView) getChildAt(r0)).listView;
            if (r2.getAdapter().getItemCount() != r2.getChildCount()) goto L18;
            int r4 = r2.getChildCount();
            int r5 = 0;
        L12:
            if (r5 >= r4) goto L17;
            r2.getChildAt(r5).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), 0), r10);
            if (r2.getChildAt(r5).getMeasuredWidth() <= r3) goto L16;
            r3 = r2.getChildAt(r5).getMeasuredWidth();
        L16:
            r5 = r5 + 1;
            goto L12
        L17:
            r3 = r3 + AndroidUtilities.dp(16.0f);
        L18:
            r0 = r0 + 1;
        L20:
            int r92 = View.MeasureSpec.getSize(r9);
            if (r92 >= AndroidUtilities.dp(240.0f)) goto L24;
            r92 = AndroidUtilities.dp(240.0f);
        L24:
            if (r92 <= AndroidUtilities.dp(280.0f)) goto L26;
            r92 = AndroidUtilities.dp(280.0f);
        L26:
            if (r92 >= 0) goto L28;
            r92 = 0;
        L28:
            if (r3 == 0) goto L31;
            if (r3 >= r92) goto L31;
        L33:
            if (r2 != null) goto L35;
        L37:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30), r10);
            return;
        L35:
            if (r1 >= r2.getChildCount()) goto L37;
            r2.getChildAt(r1).measure(View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30), r10);
            r1 = r1 + 1;
        L31:
            r3 = r92;
            goto L33
        L19:
            r3 = 0;
            goto L20
        }
    }

    public interface OnCustomEmojiSelectedListener {
        void showCustomEmojiAlert(ReactedUsersListView r1, ArrayList<TLRPC.InputStickerSet> r2);
    }

    public interface OnHeightChangedListener {
        void onHeightChanged(ReactedUsersListView r1, int r2);
    }

    public interface OnProfileSelectedListener {
        void onProfileSelected(ReactedUsersListView r1, long r2, TLRPC.MessagePeerReaction r4);
    }

    /* renamed from: $r8$lambda$C-dvtuBtO2AQTL87-B-PwQ1yaYY, reason: not valid java name */
    public static /* synthetic */ int m12295$r8$lambda$CdvtuBtO2AQTL87BPwQ1yaYY(TLRPC.MessagePeerReaction r1) {
        int r0 = r1.date;
        if (r0 > 0) goto L5;
        return Integer.MIN_VALUE;
    L5:
        if (r1.reaction == null) goto L8;
        return Integer.MIN_VALUE;
    L8:
        return -r0;
    }

    public static /* synthetic */ void $r8$lambda$Fv78N3DZdD9zLKOt03aIMXaG2Wc(ReactedUsersListView r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$load$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HZRLcHhF4ig2Wt7Xsau6Fk07Pm4(ReactedUsersListView r0, ValueAnimator r1) {
        r0.lambda$load$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$XNn1s9KIJfLwdGSNM8Vi_HhaiMQ(ReactedUsersListView r0, TLObject r1) {
        r0.lambda$load$6(r1);
    }

    /* renamed from: $r8$lambda$lO_ysVHfhrTbpX_q1mojz2-ITZg, reason: not valid java name */
    public static /* synthetic */ void m12296$r8$lambda$lO_ysVHfhrTbpX_q1mojz2ITZg(ReactedUsersListView r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ubHj4YOrmINuuktnoc78y1SP0qs(ReactedUsersListView r0, TLObject r1) {
        r0.lambda$load$5(r1);
    }

    public static /* synthetic */ int $r8$lambda$unLT4OaqL4UEFS31xTXl82zjVas(TLRPC.MessagePeerReaction r1) {
        int r0 = r1.date;
        if (r0 > 0) goto L5;
        return Integer.MIN_VALUE;
    L5:
        if (r1.reaction == null) goto L8;
        return Integer.MIN_VALUE;
    L8:
        return -r0;
    }

    /* renamed from: $r8$lambda$v5caB-538S40zafLfHDCyjW4br8, reason: not valid java name */
    public static /* synthetic */ boolean m12297$r8$lambda$v5caB538S40zafLfHDCyjW4br8(ReactedUsersListView r0, View r1, int r2) {
        return r0.lambda$new$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView.Adapter m12298$$Nest$fgetadapter(ReactedUsersListView r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetloadingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FlickerLoadingView m12299$$Nest$fgetloadingView(ReactedUsersListView r0) {
        return r0.loadingView;
    }

    /* renamed from: -$$Nest$fgetuserReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m12300$$Nest$fgetuserReactions(ReactedUsersListView r0) {
        return r0.userReactions;
    }

    /* renamed from: -$$Nest$mgetLoadCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12301$$Nest$mgetLoadCount(ReactedUsersListView r0) {
        return r0.getLoadCount();
    }

    /* renamed from: -$$Nest$mload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12302$$Nest$mload(ReactedUsersListView r0) {
        r0.load();
    }

    /* renamed from: -$$Nest$mupdateCustomReactionsButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12303$$Nest$mupdateCustomReactionsButton(ReactedUsersListView r0) {
        r0.updateCustomReactionsButton();
    }

    /* renamed from: -$$Nest$mupdateHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12304$$Nest$mupdateHeight(ReactedUsersListView r0) {
        r0.updateHeight();
    }

    public ReactedUsersListView(Context r8, Theme.ResourcesProvider r9, int r10, MessageObject r11, TLRPC.ReactionCount r12, boolean r13, boolean r14) {
        super(r8);
        this.userReactions = new ArrayList();
        this.peerReactionMap = new LongSparseArray();
        this.canLoadMore = true;
        this.customReactionsEmoji = new ArrayList();
        this.customEmojiStickerSets = new ArrayList();
        this.currentAccount = r10;
        this.message = r11;
        if (r12 != null) goto L5;
        TLRPC.Reaction r112 = null;
    L6:
        this.filter = r112;
        this.resourcesProvider = r9;
        this.showReactionPreview = r14;
        if (r12 != null) goto L9;
        int r113 = 6;
    L10:
        this.predictiveCount = r113;
        this.listView = new AnonymousClass1(this, r8, r9);
        LinearLayoutManager r114 = new LinearLayoutManager(r8);
        this.listView.setLayoutManager(r114);
        if (r13 == false) goto L14;
        this.listView.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
        this.listView.setClipToPadding(false);
    L14:
        if (Build.VERSION.SDK_INT < 29) goto L16;
        this.listView.setVerticalScrollbarThumbDrawable(new ColorDrawable(Theme.getColor(Theme.key_listSelector)));
    L16:
        RecyclerListView r122 = this.listView;
        AnonymousClass2 r1 = new AnonymousClass2(this, r10, r8, r9, r14);
        this.adapter = r1;
        r122.setAdapter(r1);
        this.listView.setOnItemClickListener(new ReactedUsersListView$$ExternalSyntheticLambda0(this));
        this.listView.setOnItemLongClickListener(new ReactedUsersListView$$ExternalSyntheticLambda1(this));
        this.listView.addOnScrollListener(new AnonymousClass3(this, r114));
        this.listView.setVerticalScrollBarEnabled(true);
        this.listView.setAlpha(0.0f);
        addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass4 r7 = new AnonymousClass4(this, r8, r9);
        this.loadingView = r7;
        r7.setColors(Theme.key_actionBarDefaultSubmenuBackground, Theme.key_listSelector, -1);
        this.loadingView.setIsSingleCell(true);
        this.loadingView.setItemsCount(this.predictiveCount);
        addView(this.loadingView, LayoutHelper.createFrame(-1, -1.0f));
        if (r13 == true) goto L25;
        TLRPC.Reaction r72 = this.filter;
        if (r72 == null) goto L25;
        if ((r72 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L25;
        if (MessagesController.getInstance(r10).premiumFeaturesBlocked() == true) goto L25;
        this.customReactionsEmoji.clear();
        this.customReactionsEmoji.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(this.filter));
        updateCustomReactionsButton();
    L25:
        FlickerLoadingView r73 = this.loadingView;
        if (this.customReactionsEmoji.isEmpty() == false) goto L28;
        int r82 = 16;
    L29:
        r73.setViewType(r82);
        return;
    L28:
        r82 = 23;
        goto L29
    L9:
        r113 = r12.count;
        goto L10
    L5:
        r112 = r12.reaction;
        goto L6
    }

    private int getLoadCount() {
        if (this.filter != null) goto L6;
        return 100;
    L6:
        return 50;
    }

    private /* synthetic */ void lambda$load$4(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.listView.setAlpha(r22);
        this.loadingView.setAlpha(1.0f - r22);
    }

    private /* synthetic */ void lambda$load$5(TLObject r12) {
        if ((r12 instanceof TLRPC.TL_messages_messageReactionsList) == false) goto L32;
        TLRPC.TL_messages_messageReactionsList r122 = (TLRPC.TL_messages_messageReactionsList) r12;
        MessagesController.getInstance(this.currentAccount).putUsers(r122.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r122.chats, false);
        HashSet r0 = new HashSet();
        int r2 = 0;
    L6:
        if (r2 >= r122.reactions.size()) goto L22;
        this.userReactions.add(r122.reactions.get(r2));
        long r5 = MessageObject.getPeerId(r122.reactions.get(r2).peer_id);
        ArrayList<TLRPC.MessagePeerReaction> r3 = this.peerReactionMap.get(r5);
        if (r3 != null) goto L10;
        r3 = new ArrayList();
    L10:
        int r7 = 0;
    L12:
        if (r7 >= r3.size()) goto L17;
        if (r3.get(r7).reaction != null) goto L16;
        r3.remove(r7);
        r7 = r7 - 1;
    L16:
        r7 = r7 + 1;
        goto L12
    L17:
        ReactionsLayoutInBubble.VisibleReaction r4 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r122.reactions.get(r2).reaction);
        if (r4.documentId == 0) goto L20;
        r0.add(r4);
    L20:
        r3.add(r122.reactions.get(r2));
        this.peerReactionMap.put(r5, r3);
        r2 = r2 + 1;
        goto L6
    L22:
        if (this.filter != null) goto L24;
        this.customReactionsEmoji.clear();
        this.customReactionsEmoji.addAll(r0);
        updateCustomReactionsButton();
    L24:
        Collections.sort(this.userReactions, Comparator.comparingInt(new ReactedUsersListView$$ExternalSyntheticLambda6()));
        this.adapter.notifyDataSetChanged();
        if (this.isLoaded == true) goto L27;
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        r02.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r02.addUpdateListener(new ReactedUsersListView$$ExternalSyntheticLambda7(this));
        r02.addListener(new AnonymousClass5(this));
        r02.start();
        updateHeight();
        this.isLoaded = true;
    L27:
        String r123 = r122.next_offset;
        this.offset = r123;
        if (r123 != null) goto L30;
        this.canLoadMore = false;
    L30:
        this.isLoading = false;
        return;
    L32:
        this.isLoading = false;
    }

    private /* synthetic */ void lambda$load$6(TLObject r3) {
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(new ReactedUsersListView$$ExternalSyntheticLambda5(this, r3));
    }

    private /* synthetic */ void lambda$load$7(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ReactedUsersListView$$ExternalSyntheticLambda3(this, r1));
    }

    private /* synthetic */ void lambda$new$0(View r4, int r5) {
        int r42 = this.adapter.getItemViewType(r5);
        if (r42 != 0) goto L9;
        OnProfileSelectedListener r43 = this.onProfileSelectedListener;
        if (r43 == null) goto L14;
        r43.onProfileSelected(this, MessageObject.getPeerId(this.userReactions.get(r5).peer_id), this.userReactions.get(r5));
        return;
    L14:
        return;
    L9:
        if (r42 != 1) goto L15;
        OnCustomEmojiSelectedListener r44 = this.onCustomEmojiSelectedListener;
        if (r44 == null) goto L16;
        r44.showCustomEmojiAlert(this, this.customEmojiStickerSets);
        return;
    L16:
        return;
    }

    private /* synthetic */ boolean lambda$new$1(View r4, int r5) {
        if (this.adapter.getItemViewType(r5) != 0) goto L9;
        OnProfileSelectedListener r42 = this.onProfileSelectedLongListener;
        if (r42 == null) goto L10;
        r42.onProfileSelected(this, MessageObject.getPeerId(this.userReactions.get(r5).peer_id), this.userReactions.get(r5));
        return true;
    L10:
        return true;
    L9:
        return true;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void load() {
        this.isLoading = true;
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);
        TLRPC.TL_messages_getMessageReactionsList r2 = new TLRPC.TL_messages_getMessageReactionsList();
        r2.peer = r1.getInputPeer(this.message.getDialogId());
        r2.id = this.message.getId();
        r2.limit = getLoadCount();
        TLRPC.Reaction r12 = this.filter;
        r2.reaction = r12;
        String r3 = this.offset;
        r2.offset = r3;
        if (r12 == null) goto L5;
        r2.flags = 1 | r2.flags;
    L5:
        if (r3 == null) goto L7;
        r2.flags |= 2;
    L7:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new ReactedUsersListView$$ExternalSyntheticLambda2(this), 64);
    }

    private void updateCustomReactionsButton() {
        this.customEmojiStickerSets.clear();
        ArrayList r5 = new ArrayList();
        HashSet r0 = new HashSet();
        int r1 = 0;
    L3:
        int r2 = this.customReactionsEmoji.size();
        int r3 = this.currentAccount;
        if (r1 >= r2) goto L12;
        TLRPC.InputStickerSet r22 = MessageObject.getInputStickerSet(AnimatedEmojiDrawable.findDocument(r3, this.customReactionsEmoji.get(r1).documentId));
        if (r22 == null) goto L10;
        if (r0.contains(Long.valueOf(r22.id)) == true) goto L10;
        r5.add(r22);
        r0.add(Long.valueOf(r22.id));
    L10:
        r1 = r1 + 1;
        goto L3
    L12:
        if (MessagesController.getInstance(r3).premiumFeaturesBlocked() == false) goto L14;
        return;
    L14:
        this.customEmojiStickerSets.addAll(r5);
        MessageContainsEmojiButton r12 = new MessageContainsEmojiButton(this.currentAccount, getContext(), this.resourcesProvider, r5, 1);
        this.messageContainsEmojiButton = r12;
        r12.checkWidth = false;
    }

    private void updateHeight() {
        if (this.onHeightChangedListener == null) goto L15;
        int r0 = this.userReactions.size();
        if (r0 != 0) goto L7;
        r0 = this.predictiveCount;
    L7:
        int r02 = AndroidUtilities.dp(r0 * 50);
        MessageContainsEmojiButton r1 = this.messageContainsEmojiButton;
        if (r1 == null) goto L11;
        r02 = r02 + (r1.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
    L11:
        if (this.listView.getMeasuredHeight() == 0) goto L13;
        r02 = Math.min(this.listView.getMeasuredHeight(), r02);
    L13:
        this.onHeightChangedListener.onHeightChanged(this, r02);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isLoaded == false) goto L5;
        return;
    L5:
        if (this.isLoading == true) goto L9;
        load();
        return;
    }

    public ReactedUsersListView setOnCustomEmojiSelectedListener(OnCustomEmojiSelectedListener r1) {
        this.onCustomEmojiSelectedListener = r1;
        return this;
    }

    public ReactedUsersListView setOnHeightChangedListener(OnHeightChangedListener r1) {
        this.onHeightChangedListener = r1;
        return this;
    }

    public ReactedUsersListView setOnProfileLongSelectedListener(OnProfileSelectedListener r1) {
        this.onProfileSelectedLongListener = r1;
        return this;
    }

    public ReactedUsersListView setOnProfileSelectedListener(OnProfileSelectedListener r1) {
        this.onProfileSelectedListener = r1;
        return this;
    }

    public void setPredictiveCount(int r1) {
        this.predictiveCount = r1;
        this.loadingView.setItemsCount(r1);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public ReactedUsersListView setSeenUsers(List<ReactedHeaderView.UserSeen> r10) {
        List<TLRPC.MessagePeerReaction> r0 = this.userReactions;
        if (r0 != null) goto L5;
    L24:
        ArrayList r02 = new ArrayList(r10.size());
        Iterator<ReactedHeaderView.UserSeen> r102 = r10.iterator();
    L26:
        if (r102.hasNext() == false) goto L38;
        ReactedHeaderView.UserSeen r2 = r102.next();
        if (this.peerReactionMap.get(r2.dialogId) != null) goto L26;
        TLRPC.TL_messagePeerReaction r3 = new TLRPC.TL_messagePeerReaction();
        r3.reaction = null;
        TLObject r4 = r2.user;
        if ((r4 instanceof TLRPC.User) == false) goto L34;
        TLRPC.TL_peerUser r42 = new TLRPC.TL_peerUser();
        r3.peer_id = r42;
        r42.user_id = ((TLRPC.User) r2.user).id;
    L36:
        r3.date = r2.date;
        r3.dateIsSeen = true;
        ArrayList<TLRPC.MessagePeerReaction> r22 = new ArrayList();
        r22.add(r3);
        this.peerReactionMap.put(MessageObject.getPeerId(r3.peer_id), r22);
        r02.add(r3);
        goto L26
    L34:
        if ((r4 instanceof TLRPC.Chat) == false) goto L36;
        TLRPC.TL_peerChat r43 = new TLRPC.TL_peerChat();
        r3.peer_id = r43;
        r43.chat_id = ((TLRPC.Chat) r2.user).id;
        goto L36
    L38:
        if (this.userReactions.isEmpty() == false) goto L40;
        this.onlySeenNow = true;
    L40:
        this.userReactions.addAll(r02);
        Collections.sort(this.userReactions, Comparator.comparingInt(new ReactedUsersListView$$ExternalSyntheticLambda4()));
        this.adapter.notifyDataSetChanged();
        updateHeight();
        return this;
    L5:
        if (r0.isEmpty() == true) goto L24;
        Iterator<ReactedHeaderView.UserSeen> r03 = r10.iterator();
    L8:
        if (r03.hasNext() == false) goto L24;
        ReactedHeaderView.UserSeen r23 = r03.next();
        if (r23.user == null) goto L8;
        if (r23.date <= 0) goto L8;
        int r32 = 0;
    L15:
        if (r32 >= this.userReactions.size()) goto L8;
        TLRPC.MessagePeerReaction r44 = this.userReactions.get(r32);
        if (r44 == null) goto L23;
        if (r44.date > 0) goto L23;
        if (MessageObject.getPeerId(r44.peer_id) != r23.dialogId) goto L23;
        r44.date = r23.date;
        r44.dateIsSeen = true;
    L23:
        r32 = r32 + 1;
        goto L15
    }
}
