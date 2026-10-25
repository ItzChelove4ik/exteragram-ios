package org.telegram.ui.community;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.community.sheet.CommunityAddOptionsSheet;

/* loaded from: classes7.dex */
public class CommunityCreateActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private CommunityHeaderView communityHeaderView;
    private FrameLayout containerView;
    private TLRPC.Chat currentChat;
    private TLRPC.User currentUser;
    private long dialogId;
    private ArrayList<TLRPC.Chat> joinedCommunities;
    private UniversalRecyclerView listView;
    private NotificationCenter.ObserversGroup observersGroup;

    /* renamed from: org.telegram.ui.community.CommunityCreateActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CommunityCreateActivity this$0;

        public AnonymousClass1(CommunityCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class CommunityHeaderView extends FrameLayout implements Theme.Colorable {
        public final BackupImageView avatarView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView subtitleView;
        private final TextView titleView;

        public CommunityHeaderView(Context r10, Theme.ResourcesProvider r11) {
            super(r10);
            this.resourcesProvider = r11;
            BackupImageView r112 = new BackupImageView(r10);
            this.avatarView = r112;
            r112.setRoundRadius(ExteraConfig.getAvatarCorners(72.0f, false, AvatarCornerType.COMMUNITY));
            addView(r112, LayoutHelper.createFrame(72, 72.0f, 49, 0.0f, 36.0f, 0.0f, 0.0f));
            TextView r113 = new TextView(r10);
            this.titleView = r113;
            r113.setTypeface(AndroidUtilities.bold());
            r113.setTextSize(1, 20.0f);
            r113.setGravity(17);
            addView(r113, LayoutHelper.createFrame(-1, -2.0f, 49, 24.0f, 123.0f, 24.0f, 0.0f));
            TextView r114 = new TextView(r10);
            this.subtitleView = r114;
            r114.setTextSize(1, 14.0f);
            r114.setGravity(17);
            r114.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(r114, LayoutHelper.createFrame(-1, -2.0f, 49, 32.0f, 157.0f, 32.0f, 0.0f));
            updateColors();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            DrawableUtils.drawCommunityCardDrawable(r6, Theme.dialogs_communityCardsDrawable, this.avatarView.getLeft() + (this.avatarView.getWidth() / 2.0f), this.avatarView.getTop() + (this.avatarView.getHeight() / 2.0f), this.avatarView.getHeight());
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(218.0f), TLObject.FLAG_30));
        }

        public void setSubtitle(CharSequence r1) {
            this.subtitleView.setText(r1);
        }

        public void setTitle(CharSequence r1) {
            this.titleView.setText(r1);
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider));
        }
    }

    /* renamed from: $r8$lambda$3lvb-WA7TkaQ0J3HyuD_ubgxY7s, reason: not valid java name */
    public static /* synthetic */ void m21490$r8$lambda$3lvbWA7TkaQ0J3HyuD_ubgxY7s(CommunityCreateActivity r0, AlertDialog r1, String r2, boolean r3, long r4) {
        r0.lambda$createNewCommunity$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$6S6udrUVfGKfAkYkJe4r65zZ7Cs(CommunityCreateActivity r0, ArrayList r1) {
        r0.lambda$onFragmentCreate$0(r1);
    }

    /* renamed from: $r8$lambda$N8lLae2Q4VdZq7lvht4AD-X9g4Y, reason: not valid java name */
    public static /* synthetic */ void m21491$r8$lambda$N8lLae2Q4VdZq7lvht4ADX9g4Y(CommunityCreateActivity r0, TLRPC.Bool r1, TLRPC.TL_error r2) {
        r0.lambda$createNewCommunity$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$U5948WejS7b8vQyctof8ET5rXf8(CommunityCreateActivity r0, AlertDialog r1, long r2, boolean r4, long r5) {
        r0.lambda$linkToCommunity$6(r1, r2, r4, r5);
    }

    /* renamed from: $r8$lambda$XNviqqTi9ksgBtBQPwF1-XGyYYo, reason: not valid java name */
    public static /* synthetic */ boolean m21492$r8$lambda$XNviqqTi9ksgBtBQPwF1XGyYYo(CommunityCreateActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$caipaB10BjCTpOqB7_HYu35pq10(CommunityCreateActivity r0, String r1, Boolean r2) {
        r0.lambda$onClick$1(r1, r2);
    }

    /* renamed from: $r8$lambda$hWCjeFSCgBZahgrTRA-W4kXc8Mc, reason: not valid java name */
    public static /* synthetic */ void m21493$r8$lambda$hWCjeFSCgBZahgrTRAW4kXc8Mc(CommunityCreateActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$iAHWFUcfWLoAgkPy8W9TjpdlgsE(CommunityCreateActivity r0, TLRPC.Chat r1, Boolean r2) {
        r0.lambda$onClick$3(r1, r2);
    }

    /* renamed from: $r8$lambda$q8Xv1PFP-6ou6QSsfisEMP3hN5Q, reason: not valid java name */
    public static /* synthetic */ void m21494$r8$lambda$q8Xv1PFP6ou6QSsfisEMP3hN5Q(CommunityCreateActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$yWKdX_Xhh8fx5FCteZ8o9tzcExE(CommunityCreateActivity r0, String r1) {
        r0.lambda$onClick$2(r1);
    }

    public CommunityCreateActivity(Bundle r1) {
        super(r1);
    }

    private void createNewCommunity(String r10, boolean r11) {
        if (ChatObject.isChannel(this.currentChat) == false) goto L5;
    L8:
        getMessagesController().createCommunity(r10, this.dialogId, r11, new CommunityCreateActivity$$ExternalSyntheticLambda9(this));
        return;
    L5:
        if (this.currentUser != null) goto L8;
        AlertDialog r0 = new AlertDialog(getContext(), 3);
        r0.showDelayed(250);
        getMessagesController().convertToMegaGroup(getParentActivity(), -this.dialogId, this, new CommunityCreateActivity$$ExternalSyntheticLambda8(this, r0, r10, r11));
    }

    private void fillItems(ArrayList<UItem> r9, UniversalAdapter r10) {
        r9.add(UItem.asCustomShadow(0, this.communityHeaderView));
        r9.add(UItem.asButton(1, R.drawable.msg_groups_create, LocaleController.getString(R.string.CommunityCreateCommunity)).accent());
        r9.add(UItem.asSpace(2, AndroidUtilities.dp(14.0f)));
        ArrayList<TLRPC.Chat> r102 = this.joinedCommunities;
        if (r102 != null) goto L5;
        return;
    L5:
        if (r102.isEmpty() == true) goto L21;
        r9.add(UItem.asHeader(3, LocaleController.getString(R.string.CommunityAddToExistingCommunity)));
        ArrayList<TLRPC.Chat> r103 = this.joinedCommunities;
        int r1 = r103.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L22;
        TLRPC.Chat r3 = r103.get(r2);
        r2 = r2 + 1;
        TLRPC.Chat r32 = r3;
        TLRPC.ChatFull r4 = getMessagesController().getChatFull(r32.id);
        UItem r5 = UItem.asProfileCell(r32);
        r5.id = Long.hashCode(r32.id);
        if (r4 == null) goto L15;
        ArrayList<TL_communities.CommunityPeer> r33 = r4.linked_peers;
        if (r33 == null) goto L13;
        int r34 = r33.size();
    L14:
        String r35 = LocaleController.formatPluralString("Chats", r34, new Object[0]);
    L16:
        r5.subtext = r35;
        r9.add(r5);
        goto L7
    L13:
        r34 = 0;
        goto L14
    L15:
        r35 = LocaleController.getString(R.string.Loading);
        goto L16
    L22:
        return;
    }

    private /* synthetic */ void lambda$createNewCommunity$4(AlertDialog r3, String r4, boolean r5, long r6) {
        r3.dismiss();
        if (r6 != 0) goto L5;
        return;
    L5:
        this.dialogId = -r6;
        this.currentChat = getMessagesController().getChat(Long.valueOf(r6));
        createNewCommunity(r4, r5);
    }

    private /* synthetic */ void lambda$createNewCommunity$5(TLRPC.Bool r2, TLRPC.TL_error r3) {
        if (r3 == null) goto L5;
        BulletinFactory.of(this).showForError(r3);
        return;
    L5:
        CommunityUtils.onCommunityLinkSuccess(this, this.dialogId, 0);
    }

    private /* synthetic */ void lambda$linkToCommunity$6(AlertDialog r3, long r4, boolean r6, long r7) {
        r3.dismiss();
        if (r7 != 0) goto L5;
        return;
    L5:
        this.dialogId = -r7;
        this.currentChat = getMessagesController().getChat(Long.valueOf(r7));
        linkToCommunity(r4, r6);
    }

    private /* synthetic */ void lambda$onClick$1(String r1, Boolean r2) {
        createNewCommunity(r1, r2.booleanValue());
    }

    private /* synthetic */ void lambda$onClick$2(String r9) {
        getMessagesController().getChat(Long.valueOf(-this.dialogId));
        showDialog(new CommunityAddOptionsSheet(getContext(), null, this.dialogId, new CommunityCreateActivity$$ExternalSyntheticLambda6(this, r9)));
    }

    private /* synthetic */ void lambda$onClick$3(TLRPC.Chat r3, Boolean r4) {
        linkToCommunity(r3.id, r4.booleanValue());
    }

    private /* synthetic */ void lambda$onFragmentCreate$0(ArrayList r2) {
        ArrayList<TLRPC.Chat> r0 = this.joinedCommunities;
        if (r0 != null) goto L5;
    L8:
        boolean r02 = true;
    L9:
        this.joinedCommunities = r2;
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L13;
        r1.adapter.update(r02);
        return;
    L13:
        return;
    L5:
        if (r0.isEmpty() == true) goto L8;
        r02 = false;
        goto L9
    }

    private void linkToCommunity(long r11, boolean r13) {
        if (ChatObject.isChannel(this.currentChat) == false) goto L5;
    L8:
        CommunityUtils.linkToCommunityWithoutConvert(this, this.currentAccount, -this.dialogId, r11, r13);
        return;
    L5:
        if (this.currentUser != null) goto L8;
        AlertDialog r2 = new AlertDialog(getContext(), 3);
        r2.showDelayed(250);
        getMessagesController().convertToMegaGroup(getParentActivity(), -this.dialogId, this, new CommunityCreateActivity$$ExternalSyntheticLambda7(this, r2, r11, r13));
    }

    private void onClick(UItem r11, View r12, int r13, float r14, float r15) {
        if (r11.id != 1) goto L5;
        CommunityCreateActivity r1 = this;
        AlertsCreator.createSimpleTextInputAlert(getContext(), r1, LocaleController.getString(R.string.CommunityNewCommunityTitle), null, LocaleController.getString(R.string.CommunityNewCommunityNameHint), null, Integer.MAX_VALUE, LocaleController.getString(R.string.Create), this.resourceProvider, new CommunityCreateActivity$$ExternalSyntheticLambda4(this));
    L6:
        Object r10 = r11.object;
        if ((r10 instanceof TLRPC.Chat) == false) goto L10;
        TLRPC.Chat r4 = (TLRPC.Chat) r10;
        r1.getMessagesController().getChat(Long.valueOf(-r1.dialogId));
        r1.showDialog(new CommunityAddOptionsSheet(r1.getContext(), r4, r1.dialogId, new CommunityCreateActivity$$ExternalSyntheticLambda5(r1, r4)));
        return;
    L10:
        return;
    L5:
        r1 = this;
        goto L6
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        setHasOwnBackground(true);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        BlurredBackgroundSourceColor r0 = new BlurredBackgroundSourceColor();
        r0.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        BlurredBackgroundDrawableViewFactory r1 = new BlurredBackgroundDrawableViewFactory(r0);
        this.actionBar.setBackground(null);
        this.actionBar.setupGlass(r1, BlurredBackgroundProviderImpl.topPanelChatActivity(this.resourceProvider));
        this.actionBar.setGlassOnlyBack();
        FrameLayout r02 = new FrameLayout(r5);
        this.containerView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        CommunityHeaderView r03 = new CommunityHeaderView(r5, this.resourceProvider);
        this.communityHeaderView = r03;
        r03.setTitle(LocaleController.getString(R.string.CommunityTitle));
        CommunityHeaderView r52 = this.communityHeaderView;
        if (this.currentUser == null) goto L6;
        int r04 = R.string.CommunityDescriptionBot;
    L9:
        r52.setSubtitle(LocaleController.getString(r04));
        this.communityHeaderView.setTag(-33024);
        TLRPC.User r53 = this.currentUser;
        if (r53 == null) goto L12;
        this.communityHeaderView.avatarView.setForUserOrChat(r53, new AvatarDrawable(this.currentUser));
    L15:
        UniversalRecyclerView r54 = new UniversalRecyclerView(this, new CommunityCreateActivity$$ExternalSyntheticLambda0(this), new CommunityCreateActivity$$ExternalSyntheticLambda1(this), new CommunityCreateActivity$$ExternalSyntheticLambda2(this));
        this.listView = r54;
        r54.setClipToPadding(false);
        this.listView.adapter.setApplyBackground(false);
        this.listView.setSections();
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.containerView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2, 48));
        FrameLayout r55 = this.containerView;
        this.fragmentView = r55;
        return r55;
    L12:
        TLRPC.Chat r56 = this.currentChat;
        if (r56 == null) goto L15;
        this.communityHeaderView.avatarView.setForUserOrChat(r56, new AvatarDrawable(this.currentChat));
        goto L15
    L6:
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == false) goto L8;
        r04 = R.string.CommunityDescriptionChannel;
        goto L9
    L8:
        r04 = R.string.CommunityDescriptionGroup;
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.chatInfoDidLoad) goto L14;
        TLRPC.ChatFull r42 = (TLRPC.ChatFull) r5[0];
        View r52 = this.listView.findViewByItemId(Long.hashCode(r42.id));
        if ((r52 instanceof ProfileSearchCell) == false) goto L12;
        ProfileSearchCell r53 = (ProfileSearchCell) r52;
        ArrayList<TL_communities.CommunityPeer> r2 = r42.linked_peers;
        if (r2 == null) goto L9;
        int r22 = r2.size();
    L10:
        r53.setSubLabel(LocaleController.formatPluralString("Chats", r22, new Object[0]));
        return;
    L9:
        r22 = 0;
        goto L10
    L12:
        this.listView.adapter.update(false);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.dialogId = this.arguments.getLong("dialog_id", 0);
        this.currentChat = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        this.currentUser = getMessagesController().getUser(Long.valueOf(this.dialogId));
        this.joinedCommunities = getMessagesController().getJoinedCommunities();
        getMessagesController().fetchJoinedCommunities(new CommunityCreateActivity$$ExternalSyntheticLambda3(this), this.classGuid);
        this.observersGroup = getNotificationCenter().createObserversGroup(this).add(NotificationCenter.chatInfoDidLoad);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.ObserversGroup r0 = this.observersGroup;
        if (r0 == null) goto L5;
        r0.removeAllObservers();
        this.observersGroup = null;
    L5:
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        super.onInsets(r1, r2, r3, r4);
        this.listView.setPadding(0, r2, 0, r4);
    }
}
