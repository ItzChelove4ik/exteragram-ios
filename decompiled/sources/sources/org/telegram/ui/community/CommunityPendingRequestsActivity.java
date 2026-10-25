package org.telegram.ui.community;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.chat.layouts.ChatActivityFadeView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.community.CommunityUtils;
import org.telegram.ui.community.cells.CommunityPendingRequestCell;
import org.telegram.ui.community.sheet.CommunityInviteOnlySheet;

/* loaded from: classes7.dex */
public class CommunityPendingRequestsActivity extends BaseFragment implements FactorAnimator.Target {
    private final BoolAnimator animatorIsRequestsEmpty;
    private ButtonWithCounterView buttonAddAllView;
    private ButtonWithCounterView buttonDeclineAllView;
    private LinearLayout buttonsLayout;
    private long communityId;
    private FrameLayout containerView;
    private TLRPC.Chat currentChat;
    private StickerEmptyView emptyView;
    private ChatActivityFadeView fadeView;
    private TLRPC.ChatFull info;
    private UniversalRecyclerView listView;
    private CommunityUtils.PendingRequests pendingRequestsList;

    /* renamed from: org.telegram.ui.community.CommunityPendingRequestsActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements CommunityUtils.PendingRequests.Delegate {
        final /* synthetic */ CommunityPendingRequestsActivity this$0;

        public AnonymousClass1(CommunityPendingRequestsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.community.CommunityUtils.PendingRequests.Delegate
        public void close() {
            this.this$0.finishFragment();
        }

        @Override // org.telegram.ui.community.CommunityUtils.PendingRequests.Delegate
        public void onClickGroupOwner(long r1) {
            this.this$0.presentFragment(ChatActivity.of(r1));
        }

        @Override // org.telegram.ui.community.CommunityUtils.PendingRequests.Delegate
        public void updateAdapter() {
            BoolAnimator r0 = CommunityPendingRequestsActivity.m21510$$Nest$fgetanimatorIsRequestsEmpty(this.this$0);
            if (CommunityPendingRequestsActivity.m21512$$Nest$fgetpendingRequestsList(this.this$0).isFinished() == true) goto L5;
        L7:
            boolean r1 = false;
        L8:
            r0.setValue(r1, true);
            CommunityPendingRequestsActivity.m21511$$Nest$fgetlistView(this.this$0).adapter.update(true);
            return;
        L5:
            if (CommunityPendingRequestsActivity.m21512$$Nest$fgetpendingRequestsList(this.this$0).getTotalCount() != 0) goto L7;
            r1 = true;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityPendingRequestsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CommunityPendingRequestsActivity this$0;

        public AnonymousClass2(CommunityPendingRequestsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityPendingRequestsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ CommunityPendingRequestsActivity this$0;

        public AnonymousClass3(CommunityPendingRequestsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            CommunityPendingRequestsActivity.m21512$$Nest$fgetpendingRequestsList(this.this$0).checkLoadNext(CommunityPendingRequestsActivity.m21511$$Nest$fgetlistView(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityPendingRequestsActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements Bulletin.Delegate {
        final /* synthetic */ CommunityPendingRequestsActivity this$0;

        public AnonymousClass4(CommunityPendingRequestsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(64.0f);
        }
    }

    public static /* synthetic */ void $r8$lambda$3pHA4dLuMTc9q6r5K9yE3TKhQUc(CommunityPendingRequestsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$9Z0_qNsLChlq_fvR6rw7mtmpGLk(CommunityPendingRequestsActivity r0, CommunityPendingRequestCell.Data r1) {
        r0.lambda$onClick$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$GDxZX9qsaVGxb2QC5e904jZQKeQ(CommunityPendingRequestsActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$P3862IBXP3iAI2bn1MlVkxpcwRc(CommunityPendingRequestsActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    /* renamed from: $r8$lambda$S5WYV1r7-fymWnuAx-swMDZ7ZcY, reason: not valid java name */
    public static /* synthetic */ void m21509$r8$lambda$S5WYV1r7fymWnuAxswMDZ7ZcY(CommunityPendingRequestsActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$nDoi4FUFycp3AdCyp0QEUnaUaQM(CommunityPendingRequestsActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$o2Y5cbgPpz6qWqT4S9JjY1QVy9s(CommunityPendingRequestsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetanimatorIsRequestsEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m21510$$Nest$fgetanimatorIsRequestsEmpty(CommunityPendingRequestsActivity r0) {
        return r0.animatorIsRequestsEmpty;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m21511$$Nest$fgetlistView(CommunityPendingRequestsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetpendingRequestsList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CommunityUtils.PendingRequests m21512$$Nest$fgetpendingRequestsList(CommunityPendingRequestsActivity r0) {
        return r0.pendingRequestsList;
    }

    public CommunityPendingRequestsActivity(Bundle r7) {
        super(r7);
        this.animatorIsRequestsEmpty = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 320);
    }

    private void checkPaddings(int r6) {
        this.listView.setPadding(0, 0, 0, AndroidUtilities.dp(60.0f) + r6);
        this.buttonsLayout.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), AndroidUtilities.dp(12.0f) + r6);
        this.emptyView.setTranslationY((this.listView.getPaddingTop() - this.listView.getPaddingBottom()) / 2.0f);
        this.fadeView.setFadeZoneBottom(AndroidUtilities.dp(72.0f) + r6);
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        this.pendingRequestsList.fillItems(r1);
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        this.pendingRequestsList.onResolveAllJoinRequests(false);
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        this.pendingRequestsList.onResolveAllJoinRequests(true);
    }

    private /* synthetic */ void lambda$onClick$2(CommunityPendingRequestCell.Data r3) {
        presentFragment(ChatActivity.of(r3.requestFromUser.id));
    }

    private WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        checkPaddings(r2.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    private void onClick(UItem r2, View r3, int r4, float r5, float r6) {
        Object r22 = r2.object;
        if ((r22 instanceof CommunityPendingRequestCell.Data) == false) goto L17;
        CommunityPendingRequestCell.Data r23 = (CommunityPendingRequestCell.Data) r22;
        TLRPC.Chat r32 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r23.dialogToAdd));
        TLRPC.User r42 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r23.dialogToAdd));
        if (r42 == null) goto L9;
        presentFragment(ChatActivity.of(r42.id));
        return;
    L9:
        if (ChatObject.isPublic(r32) == false) goto L11;
    L15:
        presentFragment(ChatActivity.of(-r32.id));
        return;
    L11:
        if (ChatObject.isInChat(r32) == true) goto L15;
        new CommunityInviteOnlySheet(getContext(), r32, r23.requestFromUser, new CommunityPendingRequestsActivity$$ExternalSyntheticLambda6(this, r23)).show();
        return;
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r18) {
        boolean r2 = true;
        setHasOwnBackground(true);
        CommunityUtils.PendingRequests r3 = new CommunityUtils.PendingRequests(getContext(), this.resourceProvider, BulletinFactory.of(this), this.currentAccount, this.communityId);
        this.pendingRequestsList = r3;
        r3.setDelegate(new AnonymousClass1(this));
        this.pendingRequestsList.loadNext();
        this.pendingRequestsList.markAsViewed();
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        this.actionBar.setTitle(LocaleController.getString(R.string.CommunityPendingRequests));
        FrameLayout r32 = new FrameLayout(r18);
        this.containerView = r32;
        int r4 = Theme.key_windowBackgroundGray;
        r32.setBackgroundColor(Theme.getColor(r4));
        UniversalRecyclerView r33 = new UniversalRecyclerView(this, new CommunityPendingRequestsActivity$$ExternalSyntheticLambda0(this), new CommunityPendingRequestsActivity$$ExternalSyntheticLambda1(this), new CommunityPendingRequestsActivity$$ExternalSyntheticLambda2(this));
        this.listView = r33;
        r33.setClipToPadding(false);
        this.listView.adapter.setApplyBackground(false);
        this.listView.setSections();
        this.listView.addOnScrollListener(new AnonymousClass3(this));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        ChatActivityFadeView r34 = new ChatActivityFadeView(r18);
        this.fadeView = r34;
        r34.setupColorKey(r4);
        this.fadeView.setFadeZoneBottom(AndroidUtilities.dp(72.0f));
        this.fadeView.setFadeHeightBottom(AndroidUtilities.dp(24.0f));
        this.containerView.addView(this.fadeView, LayoutHelper.createFrameMatchParent());
        this.containerView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2, 48));
        LinearLayout r35 = new LinearLayout(r18);
        this.buttonsLayout = r35;
        r35.setOrientation(0);
        this.buttonsLayout.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), AndroidUtilities.dp(12.0f));
        ButtonWithCounterView r36 = new ButtonWithCounterView(r18, this.resourceProvider);
        this.buttonDeclineAllView = r36;
        r36.setNeutral();
        this.buttonDeclineAllView.setColor(ColorUtils.blendARGB(getThemedColor(Theme.key_windowBackgroundWhite), getThemedColor(Theme.key_windowBackgroundWhiteBlackText), 0.125f));
        this.buttonDeclineAllView.setText(LocaleController.getString(R.string.CommunityPendingRequestDeclineAll));
        this.buttonDeclineAllView.setRound();
        this.buttonDeclineAllView.setOnClickListener(new CommunityPendingRequestsActivity$$ExternalSyntheticLambda3(this));
        this.buttonsLayout.addView(this.buttonDeclineAllView, LayoutHelper.createLinear(0, 48, 1.0f, 0, 4, 0, 4, 0));
        ButtonWithCounterView r37 = new ButtonWithCounterView(r18, this.resourceProvider);
        this.buttonAddAllView = r37;
        r37.setText(LocaleController.getString(R.string.CommunityPendingRequestAddAll));
        this.buttonAddAllView.setRound();
        this.buttonAddAllView.setOnClickListener(new CommunityPendingRequestsActivity$$ExternalSyntheticLambda4(this));
        this.buttonsLayout.addView(this.buttonAddAllView, LayoutHelper.createLinear(0, 48, 1.0f, 0, 4, 0, 4, 0));
        this.containerView.addView(this.buttonsLayout, LayoutHelper.createFrame(-1, -2, 80));
        StickerEmptyView r1 = new StickerEmptyView(getContext(), null, 16, this.resourceProvider);
        this.emptyView = r1;
        r1.title.setText(LocaleController.getString(R.string.NoCommunityJoinRequests));
        this.emptyView.subtitle.setText(LocaleController.getString(R.string.NoCommunityJoinRequestsDescription));
        this.emptyView.setAnimateLayoutChange(true);
        this.emptyView.setVisibility(8);
        this.containerView.addView(this.emptyView, LayoutHelper.createFrame(-2, -2, 17));
        BoolAnimator r12 = this.animatorIsRequestsEmpty;
        TLRPC.ChatFull r38 = this.info;
        if (r38 != null) goto L5;
    L8:
        r12.setValue(r2, false);
        checkPaddings(0);
        ViewCompat.setOnApplyWindowInsetsListener(this.containerView, new CommunityPendingRequestsActivity$$ExternalSyntheticLambda5(this));
        Bulletin.addDelegate(this, new AnonymousClass4(this));
        FrameLayout r13 = this.containerView;
        this.fragmentView = r13;
        return r13;
    L5:
        if (r38.requests_pending == 0) goto L8;
        r2 = false;
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r3, float r4, float r5, FactorAnimator r6) {
        float r52 = 1.0f - r4;
        this.buttonsLayout.setAlpha(r52);
        LinearLayout r32 = this.buttonsLayout;
        int r0 = 8;
        if (r52 <= 0.0f) goto L5;
        int r53 = 0;
    L6:
        r32.setVisibility(r53);
        this.emptyView.setAlpha(r4);
        StickerEmptyView r2 = this.emptyView;
        if (r4 <= 0.0f) goto L9;
        r0 = 0;
    L9:
        r2.setVisibility(r0);
        return;
    L5:
        r53 = 8;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.communityId = this.arguments.getLong("community_id", 0);
        this.currentChat = getMessagesController().getChat(Long.valueOf(this.communityId));
        this.info = getMessagesController().getChatFull(this.communityId);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        this.pendingRequestsList.commit();
    }
}
