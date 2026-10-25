package com.exteragram.messenger.feed.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.feed.FeedConfig;
import com.exteragram.messenger.feed.FeedController;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatActivityContainer;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.MainTabsActivityController;

/* loaded from: classes4.dex */
public class FeedActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, MainTabsActivity.TabFragmentDelegate {
    private ChatActivityContainer chatContainer;
    private boolean embeddedChatCreated;
    private boolean hasMainTabs;
    private int lastConfigGeneration;
    private WindowInsetsCompat lastWindowInsets;
    private final Runnable loadNewPosts;
    private MainTabsActivityController mainTabsActivityController;
    private Runnable parentTabsGlassInvalidationCallback;
    private boolean resumedOnce;
    private boolean uiActiveHeld;
    private boolean uiResumedHeld;
    private boolean viewportFullyVisible;

    /* renamed from: com.exteragram.messenger.feed.ui.FeedActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ FeedActivity this$0;

        public AnonymousClass1(FeedActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r2) {
            if (FeedActivity.m1429$$Nest$fgetlastWindowInsets(this.this$0) == null) goto L6;
            ViewCompat.dispatchApplyWindowInsets(r2, FeedActivity.m1429$$Nest$fgetlastWindowInsets(this.this$0));
            return;
        L6:
            r2.requestApplyInsets();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
        }
    }

    /* renamed from: com.exteragram.messenger.feed.ui.FeedActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ChatActivityContainer {
        boolean activityCreated;
        final /* synthetic */ FeedActivity this$0;

        public AnonymousClass2(FeedActivity r1, Context r2, INavigationLayout r3, Bundle r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.activityCreated = false;
        }

        @Override // org.telegram.ui.ChatActivityContainer
        public void initChatActivity() {
            if (this.activityCreated == true) goto L11;
            this.activityCreated = true;
            FeedActivity.m1430$$Nest$fputembeddedChatCreated(this.this$0, true);
            super.initChatActivity();
            FeedActivity.m1431$$Nest$mapplyFloatingWindowLayout(this.this$0);
            FeedActivity.m1433$$Nest$msetupChatActionBar(this.this$0);
            FeedActivity.m1434$$Nest$msetupChatTitle(this.this$0);
            if (FeedActivity.m1429$$Nest$fgetlastWindowInsets(this.this$0) == null) goto L9;
            FeedActivity r0 = this.this$0;
            View r1 = r0.fragmentView;
            if (r1 == null) goto L9;
            ViewCompat.dispatchApplyWindowInsets(r1, FeedActivity.m1429$$Nest$fgetlastWindowInsets(r0));
        L9:
            FeedActivity.m1432$$Nest$minvalidateParentTabsGlass(this.this$0);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.feed.ui.FeedActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements Bulletin.Delegate {
        final /* synthetic */ FeedActivity this$0;

        public AnonymousClass3(FeedActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            if (FeedActivity.m1427$$Nest$fgetchatContainer(this.this$0) != null) goto L5;
            return 0;
        L5:
            if (FeedActivity.m1427$$Nest$fgetchatContainer(this.this$0).chatActivity != null) goto L7;
            return 0;
        L7:
            return FeedActivity.m1427$$Nest$fgetchatContainer(this.this$0).chatActivity.getBulletinBottomOffset();
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            if (FeedActivity.m1427$$Nest$fgetchatContainer(this.this$0) == null) goto L9;
            if (FeedActivity.m1427$$Nest$fgetchatContainer(this.this$0).chatActivity == null) goto L9;
            return FeedActivity.m1427$$Nest$fgetchatContainer(this.this$0).chatActivity.getBulletinTopOffset();
        L9:
            return AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight();
        }
    }

    /* renamed from: com.exteragram.messenger.feed.ui.FeedActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ FeedActivity this$0;
        final /* synthetic */ ActionBar val$chatActionBar;
        final /* synthetic */ ActionBar.ActionBarMenuOnItemClick val$chatClickListener;

        public AnonymousClass4(FeedActivity r1, ActionBar r2, ActionBar.ActionBarMenuOnItemClick r3) {
            this.this$0 = r1;
            this.val$chatActionBar = r2;
            this.val$chatClickListener = r3;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public boolean canOpenMenu() {
            ActionBar.ActionBarMenuOnItemClick r0 = this.val$chatClickListener;
            if (r0 != null) goto L5;
            return true;
        L5:
            if (r0.canOpenMenu() == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L10;
            if (FeedActivity.m1428$$Nest$fgethasMainTabs(this.this$0) == false) goto L10;
            if (this.val$chatActionBar.isActionModeShowed() == true) goto L10;
            return;
        L10:
            if (r2 != 76) goto L14;
            FeedActivity.m1435$$Nest$mshowMarkAllReadDialog(this.this$0);
            return;
        L14:
            if (r2 != 75) goto L17;
            this.this$0.presentFragment(new FeedChannelsActivity());
            return;
        L17:
            ActionBar.ActionBarMenuOnItemClick r1 = this.val$chatClickListener;
            if (r1 == null) goto L21;
            r1.onItemClick(r2);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$63QiKWB4EbT5xfnKOFnv2HEQpT0(FeedActivity r0) {
        r0.invalidateParentTabsGlass();
    }

    public static /* synthetic */ void $r8$lambda$BQx5dDcNKjVdfkt4KT8kdJ7nHnU(FeedActivity r0, FeedController r1, ArrayList r2, int r3, boolean r4, int r5) {
        r0.lambda$updateFeedSubtitle$3(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$DsFGn63YKZEXBGVoGXEcs9WlGdQ(FeedActivity r0) {
        r0.updateFeedSubtitle();
    }

    public static /* synthetic */ void $r8$lambda$F4eiAaxMg9yP4rxSsmOYk0jMYMY(FeedActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showMarkAllReadDialog$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IFofORnwThMWquUZdOrDF3V1IUU(FeedActivity r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$O4hi8xMAqrBXSj3kcI5sPjcQPLY(FeedActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.lambda$createView$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetchatContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityContainer m1427$$Nest$fgetchatContainer(FeedActivity r0) {
        return r0.chatContainer;
    }

    /* renamed from: -$$Nest$fgethasMainTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1428$$Nest$fgethasMainTabs(FeedActivity r0) {
        return r0.hasMainTabs;
    }

    /* renamed from: -$$Nest$fgetlastWindowInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowInsetsCompat m1429$$Nest$fgetlastWindowInsets(FeedActivity r0) {
        return r0.lastWindowInsets;
    }

    /* renamed from: -$$Nest$fputembeddedChatCreated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1430$$Nest$fputembeddedChatCreated(FeedActivity r0, boolean r1) {
        r0.embeddedChatCreated = r1;
    }

    /* renamed from: -$$Nest$mapplyFloatingWindowLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1431$$Nest$mapplyFloatingWindowLayout(FeedActivity r0) {
        r0.applyFloatingWindowLayout();
    }

    /* renamed from: -$$Nest$minvalidateParentTabsGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1432$$Nest$minvalidateParentTabsGlass(FeedActivity r0) {
        r0.invalidateParentTabsGlass();
    }

    /* renamed from: -$$Nest$msetupChatActionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1433$$Nest$msetupChatActionBar(FeedActivity r0) {
        r0.setupChatActionBar();
    }

    /* renamed from: -$$Nest$msetupChatTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1434$$Nest$msetupChatTitle(FeedActivity r0) {
        r0.setupChatTitle();
    }

    /* renamed from: -$$Nest$mshowMarkAllReadDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1435$$Nest$mshowMarkAllReadDialog(FeedActivity r0) {
        r0.showMarkAllReadDialog();
    }

    public FeedActivity() {
        this(null);
    }

    public FeedActivity(Bundle r1) {
        super(r1);
        this.loadNewPosts = new FeedActivity$$ExternalSyntheticLambda1(this);
    }

    private static Insets addBottomInset(Insets r3, int r4) {
        return Insets.of(r3.left, r3.top, r3.right, r3.bottom + r4);
    }

    private static WindowInsetsCompat addTabsBottomInset(WindowInsetsCompat r4, int r5) {
        int r0 = WindowInsetsCompat.Type.systemBars();
        int r1 = WindowInsetsCompat.Type.navigationBars();
        return new WindowInsetsCompat.Builder(r4).setStableInsets(addBottomInset(r4.getInsetsIgnoringVisibility(r0), r5)).setInsets(r0, addBottomInset(r4.getInsets(r0), r5)).setInsets(r1, addBottomInset(r4.getInsets(r1), r5)).setInsetsIgnoringVisibility(r0, addBottomInset(r4.getInsetsIgnoringVisibility(r0), r5)).setInsetsIgnoringVisibility(r1, addBottomInset(r4.getInsetsIgnoringVisibility(r1), r5)).build();
    }

    private void applyFloatingWindowLayout() {
        if (getParentLayout() != null) goto L5;
        return;
    L5:
        if (getParentLayout().isLayersLayout() == false) goto L25;
        ChatActivityContainer r2 = this.chatContainer;
        if (r2 == null) goto L23;
        ChatActivity r22 = r2.chatActivity;
        if (r22 != null) goto L13;
        return;
    L13:
        if (r22.getActionBar() == null) goto L15;
        r22.getActionBar().setOccupyStatusBar(false);
    L15:
        ChatAvatarContainer r0 = r22.avatarContainer;
        if (r0 == null) goto L18;
        r0.setOccupyStatusBar(false);
    L18:
        ChatActivity.ChatActivityFragmentView r23 = r22.contentView;
        if (r23 == null) goto L24;
        r23.setOccupyStatusBar(false);
        return;
    L24:
        return;
    L23:
        return;
    }

    private void applyMainTabsHeaderLayout() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L19;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L20;
        ChatAvatarContainer r03 = r02.avatarContainer;
        if (r03 == null) goto L23;
        ViewGroup.LayoutParams r04 = r03.getLayoutParams();
        if ((r04 instanceof ViewGroup.MarginLayoutParams) == false) goto L21;
        ViewGroup.MarginLayoutParams r05 = (ViewGroup.MarginLayoutParams) r04;
        if (ExteraConfig.getNewChatHeaderStyle() == false) goto L14;
        float r1 = 12.0f;
    L15:
        int r12 = AndroidUtilities.dp(r1);
        if (r05.leftMargin == r12) goto L22;
        r05.leftMargin = r12;
        this.chatContainer.chatActivity.avatarContainer.setLayoutParams(r05);
        return;
    L22:
        return;
    L14:
        r1 = 0.0f;
        goto L15
    L21:
        return;
    L23:
        return;
    L20:
        return;
    }

    private void destroyEmbeddedChat() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L14;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L14;
        if (this.hasMainTabs == false) goto L9;
    L11:
        this.chatContainer.chatActivity.setFeedChannelsChangedCallback(null);
        this.chatContainer.chatActivity.setGlassSourceInvalidationCallback(null);
        if (this.embeddedChatCreated == false) goto L14;
        this.chatContainer.chatActivity.onFragmentDestroy();
        goto L14
    L9:
        if (this.embeddedChatCreated == false) goto L11;
        r02.saveFeedScrollPosition();
    L14:
        this.embeddedChatCreated = false;
        this.chatContainer = null;
    }

    private void invalidateParentTabsGlass() {
        Runnable r0 = this.parentTabsGlassInvalidationCallback;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ WindowInsetsCompat lambda$createView$1(View r1, WindowInsetsCompat r2) {
        this.lastWindowInsets = r2;
        if (BottomNavigationBar.visible() == false) goto L5;
        int r0 = AndroidUtilities.dp(MainTabsUiHelper.getTabsViewHeightDp());
    L6:
        if (r0 != 0) goto L9;
        return r2;
    L9:
        return addTabsBottomInset(r2, r0);
    L5:
        r0 = 0;
        goto L6
    }

    private /* synthetic */ void lambda$new$0() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L12;
        ChatActivity r02 = r0.chatActivity;
        if (r02 != null) goto L8;
        return;
    L8:
        if (this.uiResumedHeld == false) goto L14;
        r02.loadNewerFeed(true);
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$showMarkAllReadDialog$2(AlertDialog r1, int r2) {
        markAllRead();
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.FeedMarkAllReadDone)).show();
    }

    private /* synthetic */ void lambda$updateFeedSubtitle$3(FeedController r1, ArrayList r2, int r3, boolean r4, int r5) {
        if (r4 == false) goto L4;
        r3 = r1.getIncludedChannelCount();
    L4:
        setFeedSubtitle(r3);
    }

    public static void presentFeed(BaseFragment r3) {
        if (AndroidUtilities.isTablet() == false) goto L19;
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L19;
        if (r0.getRightActionBarLayout() == null) goto L19;
        INavigationLayout r32 = LaunchActivity.instance.getRightActionBarLayout();
        if ((r32.getLastFragment() instanceof FeedActivity) == false) goto L12;
        return;
    L12:
        if (r32.getFragmentStack().isEmpty() == false) goto L14;
    L17:
        r32.presentFragment(new INavigationLayout.NavigationParams(new FeedActivity()).setNoAnimation(true).forceRightLayout());
        return;
    L14:
        if ((r32.getFragmentStack().size() - 1) <= 0) goto L16;
        r32.removeFragmentFromStack(r32.getFragmentStack().get(0));
        goto L14
    L16:
        r32.closeLastFragment(false);
    L19:
        if (r3 == null) goto L23;
        r3.presentFragment(new FeedActivity());
        return;
    }

    private void reattachCurrentFeedVideoTexture() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L8;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L9;
        r02.reattachCurrentFeedVideoTexture();
        return;
    L9:
        return;
    }

    private void setFeedSubtitle(int r5) {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L17;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L18;
        ChatAvatarContainer r03 = r02.avatarContainer;
        if (r03 != null) goto L10;
        return;
    L10:
        if (r5 >= 0) goto L12;
        String r52 = LocaleController.getString(R.string.Loading);
    L13:
        r03.setSubtitle(r52);
        View r4 = this.chatContainer.chatActivity.avatarContainer.getSubtitleTextView();
        if (r4 == null) goto L19;
        r4.setVisibility(0);
        return;
    L19:
        return;
    L12:
        r52 = LocaleController.formatPluralString("Channels", r5, new Object[0]);
        goto L13
    L18:
        return;
    }

    private void setupChatActionBar() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L21;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L22;
        ActionBar r03 = r02.getActionBar();
        if (r03 == null) goto L23;
        ActionBarMenu r1 = r03.createMenu();
        if (r1.getItem(76) != null) goto L14;
        r1.addItem(76, R.drawable.msg_markread, this.chatContainer.chatActivity.themeDelegate).setContentDescription(LocaleController.getString(R.string.FeedMarkAllRead));
    L14:
        if (r1.getItem(75) != null) goto L17;
        r1.addItem(75, R.drawable.msg_settings, this.chatContainer.chatActivity.themeDelegate).setContentDescription(LocaleController.getString(R.string.FeedSettings));
    L17:
        if (this.hasMainTabs == false) goto L19;
        applyMainTabsHeaderLayout();
    L19:
        r03.setActionBarMenuOnItemClick(new AnonymousClass4(this, r03, r03.getActionBarMenuOnItemClick()));
        return;
    L23:
        return;
    L22:
        return;
    }

    private void setupChatTitle() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L11;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L12;
        ChatAvatarContainer r03 = r02.avatarContainer;
        if (r03 == null) goto L13;
        r03.setTitle(LocaleController.getString(R.string.Feed));
        this.chatContainer.chatActivity.avatarContainer.setFeedAvatar();
        updateFeedSubtitle();
        return;
    L13:
        return;
    L12:
        return;
    }

    private void showMarkAllReadDialog() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity(), getResourceProvider());
        r0.setTitle(LocaleController.getString(R.string.FeedMarkAllRead));
        r0.setMessage(LocaleController.getString(R.string.FeedMarkAllReadConfirm));
        r0.setPositiveButton(LocaleController.getString(R.string.MarkAsRead), new FeedActivity$$ExternalSyntheticLambda5(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r0.create());
    }

    private void updateFeedSubtitle() {
        FeedController r0 = FeedController.getInstance(this.currentAccount);
        setFeedSubtitle(r0.getIncludedChannelCount());
        r0.loadChannels(new FeedActivity$$ExternalSyntheticLambda0(this, r0));
    }

    private void updateFeedViewportActive(boolean r1) {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L8;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L9;
        r02.setFeedViewportActive(r1);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public boolean canParentTabsSlide(MotionEvent r1, boolean r2) {
        ChatActivityContainer r12 = this.chatContainer;
        if (r12 == null) goto L12;
        ChatActivity r13 = r12.chatActivity;
        if (r13 != null) goto L7;
        return true;
    L7:
        if (r13.getActionBar() != null) goto L9;
        return true;
    L9:
        if (this.chatContainer.chatActivity.getActionBar().isActionModeShowed() == false) goto L16;
        return false;
    L16:
        return true;
    L12:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        destroyEmbeddedChat();
        this.lastWindowInsets = null;
        this.actionBar.setAddToContainer(false);
        this.actionBar.setVisibility(8);
        FrameLayout r0 = new FrameLayout(r8);
        this.fragmentView = r0;
        r0.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        if (this.hasMainTabs == false) goto L5;
        ViewCompat.setOnApplyWindowInsetsListener(r0, new FeedActivity$$ExternalSyntheticLambda2(this));
        r0.addOnAttachStateChangeListener(new AnonymousClass1(this));
    L5:
        FrameLayout r2 = new FrameLayout(r8);
        r0.addView(r2, LayoutHelper.createFrame(-1, -1, 119));
        Bundle r02 = new Bundle();
        r02.putInt("chatMode", 7);
        r02.putInt("searchType", 4);
        r02.putBoolean("hasMainTabs", this.hasMainTabs);
        AnonymousClass2 r5 = new AnonymousClass2(this, r8, getParentLayout(), r02);
        this.chatContainer = r5;
        ChatActivity r82 = r5.chatActivity;
        r82.isInsideContainer = false;
        r82.setFeedChannelsChangedCallback(new FeedActivity$$ExternalSyntheticLambda3(this));
        this.chatContainer.chatActivity.setGlassSourceInvalidationCallback(new FeedActivity$$ExternalSyntheticLambda4(this));
        updateFeedViewportActive(this.viewportFullyVisible);
        if (this.uiResumedHeld == true) goto L8;
        this.chatContainer.onPause();
    L8:
        r2.addView(this.chatContainer, LayoutHelper.createFrame(-1, -1, 119));
        if (this.uiActiveHeld == true) goto L11;
        this.uiActiveHeld = true;
        FeedController.getInstance(this.currentAccount).setUiActive(true);
    L11:
        Bulletin.addDelegate(this, new AnonymousClass3(this));
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        boolean r0 = false;
        if (r2 != NotificationCenter.didReceiveNewMessages) goto L15;
        if (((Boolean) r4[2]).booleanValue() == false) goto L7;
        return;
    L7:
        if (this.chatContainer != null) goto L10;
        return;
    L10:
        if (FeedController.getInstance(this.currentAccount).isIncludedChannelPost(((Long) r4[0]).longValue()) == false) goto L31;
        AndroidUtilities.cancelRunOnUIThread(this.loadNewPosts);
        AndroidUtilities.runOnUIThread(this.loadNewPosts, 1000);
        return;
    L31:
        return;
    L15:
        if (r2 != NotificationCenter.feedNeedReload) goto L29;
        ChatActivityContainer r22 = this.chatContainer;
        if (r22 != null) goto L19;
    L26:
        updateFeedSubtitle();
        return;
    L19:
        if (r22.chatActivity == null) goto L26;
        if (r4.length > 0) goto L23;
    L25:
        this.chatContainer.chatActivity.onFeedChannelsChanged(r0);
        goto L26
    L23:
        if (Boolean.TRUE.equals(r4[0]) == false) goto L25;
        r0 = true;
        goto L25
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public BlurredBackgroundSourceRenderNode getGlassSource() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L8;
        ChatActivity r02 = r0.chatActivity;
        if (r02 != null) goto L7;
        return null;
    L7:
        return r02.getGlassSource();
    L8:
        return null;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L9;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L9;
        return r02.isLightStatusBar();
    L9:
        return !Theme.isCurrentThemeDark();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public void markAllRead() {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L8;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L8;
        r02.markFeedAsRead();
        return;
    L8:
        FeedController.getInstance(this.currentAccount).markAllRead();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L15;
        ChatActivity r02 = r0.chatActivity;
        if (r02 == null) goto L15;
        if (r02.getActionBar() == null) goto L15;
        if (this.chatContainer.chatActivity.getActionBar().isActionModeShowed() == false) goto L15;
        if (r2 == false) goto L16;
        this.chatContainer.chatActivity.clearSelectionMode();
        return false;
    L16:
        return false;
    L15:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        this.viewportFullyVisible = false;
        updateFeedViewportActive(false);
        super.onBecomeFullyHidden();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        this.viewportFullyVisible = true;
        updateMainTabsVisibility();
        updateFeedViewportActive(true);
        reattachCurrentFeedVideoTexture();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        Bundle r0 = this.arguments;
        boolean r2 = false;
        if (r0 != null) goto L5;
    L7:
        this.hasMainTabs = r2;
        this.viewportFullyVisible = !r2;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didReceiveNewMessages);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.feedNeedReload);
        this.lastConfigGeneration = FeedConfig.getInstance(this.currentAccount).getGeneration();
        return super.onFragmentCreate();
    L5:
        if (r0.getBoolean("hasMainTabs", false) == false) goto L7;
        r2 = true;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        AndroidUtilities.cancelRunOnUIThread(this.loadNewPosts);
        destroyEmbeddedChat();
        if (this.uiResumedHeld == false) goto L6;
        this.uiResumedHeld = false;
        FeedController.getInstance(this.currentAccount).setUiResumed(false);
    L6:
        if (this.uiActiveHeld == false) goto L8;
        this.uiActiveHeld = false;
        FeedController.getInstance(this.currentAccount).setUiActive(false);
    L8:
        Bulletin.removeDelegate(this);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didReceiveNewMessages);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.feedNeedReload);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void onParentBecomeFullyVisible() {
        reattachCurrentFeedVideoTexture();
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void onParentScrollToTop() {
        ChatActivityContainer r1 = this.chatContainer;
        if (r1 == null) goto L8;
        ChatActivity r12 = r1.chatActivity;
        if (r12 == null) goto L9;
        r12.onPageDownClicked(false);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        if (this.chatContainer == null) goto L6;
        updateFeedViewportActive(false);
        this.chatContainer.onPause();
    L6:
        if (this.uiResumedHeld == false) goto L9;
        this.uiResumedHeld = false;
        FeedController.getInstance(this.currentAccount).setUiResumed(false);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        updateMainTabsVisibility();
        ChatActivityContainer r0 = this.chatContainer;
        if (r0 == null) goto L6;
        r0.onResume();
        updateFeedViewportActive(this.viewportFullyVisible);
    L6:
        if (this.uiResumedHeld == true) goto L9;
        this.uiResumedHeld = true;
        FeedController.getInstance(this.currentAccount).setUiResumed(true);
    L9:
        if (this.hasMainTabs == false) goto L15;
        View r02 = this.fragmentView;
        if (r02 == null) goto L15;
        WindowInsetsCompat r2 = this.lastWindowInsets;
        if (r2 == null) goto L15;
        ViewCompat.dispatchApplyWindowInsets(r02, r2);
    L15:
        reattachCurrentFeedVideoTexture();
        int r03 = FeedConfig.getInstance(this.currentAccount).getGeneration();
        if (r03 == this.lastConfigGeneration) goto L23;
        this.lastConfigGeneration = r03;
        ChatActivityContainer r04 = this.chatContainer;
        if (r04 == null) goto L31;
        ChatActivity r05 = r04.chatActivity;
        if (r05 == null) goto L31;
        r05.applyFeedConfigChange();
    L31:
        this.resumedOnce = true;
        updateFeedSubtitle();
        return;
    L23:
        if (this.resumedOnce == false) goto L31;
        ChatActivityContainer r06 = this.chatContainer;
        if (r06 == null) goto L31;
        ChatActivity r07 = r06.chatActivity;
        if (r07 == null) goto L31;
        r07.reconcileFeedList();
        this.chatContainer.chatActivity.refreshFeedUnreadDivider();
        if (FeedController.getInstance(this.currentAccount).getMessages().isEmpty() == true) goto L31;
        this.chatContainer.chatActivity.loadNewerFeed(true);
        goto L31
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        super.onTransitionAnimationEnd(r1, r2);
        if (this.hasMainTabs == false) goto L6;
        this.viewportFullyVisible = r1;
        updateFeedViewportActive(r1);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r2, boolean r3) {
        if (this.hasMainTabs == false) goto L5;
        this.viewportFullyVisible = false;
        updateFeedViewportActive(false);
    L5:
        super.onTransitionAnimationStart(r2, r3);
    }

    public void setMainTabsActivityController(MainTabsActivityController r1) {
        this.mainTabsActivityController = r1;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void setParentTabsGlassInvalidationCallback(Runnable r1) {
        this.parentTabsGlassInvalidationCallback = r1;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void updateMainTabsVisibility() {
        MainTabsActivityController r1 = this.mainTabsActivityController;
        if (r1 == null) goto L6;
        r1.setTabsVisible(BottomNavigationBar.visible());
        return;
    }
}
