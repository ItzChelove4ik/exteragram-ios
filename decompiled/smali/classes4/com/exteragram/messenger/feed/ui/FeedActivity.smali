.class public Lcom/exteragram/messenger/feed/ui/FeedActivity;
.super Lorg/telegram/ui/ActionBar/BaseFragment;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;
.implements Lorg/telegram/ui/MainTabsActivity$TabFragmentDelegate;


# instance fields
.field private chatContainer:Lorg/telegram/ui/ChatActivityContainer;

.field private embeddedChatCreated:Z

.field private hasMainTabs:Z

.field private lastConfigGeneration:I

.field private lastWindowInsets:Landroidx/core/view/WindowInsetsCompat;

.field private final loadNewPosts:Ljava/lang/Runnable;

.field private mainTabsActivityController:Lorg/telegram/ui/MainTabsActivityController;

.field private parentTabsGlassInvalidationCallback:Ljava/lang/Runnable;

.field private resumedOnce:Z

.field private uiActiveHeld:Z

.field private uiResumedHeld:Z

.field private viewportFullyVisible:Z


# direct methods
.method public static synthetic $r8$lambda$63QiKWB4EbT5xfnKOFnv2HEQpT0(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->invalidateParentTabsGlass()V

    return-void
.end method

.method public static synthetic $r8$lambda$BQx5dDcNKjVdfkt4KT8kdJ7nHnU(Lcom/exteragram/messenger/feed/ui/FeedActivity;Lcom/exteragram/messenger/feed/FeedController;Ljava/util/ArrayList;IZI)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lambda$updateFeedSubtitle$3(Lcom/exteragram/messenger/feed/FeedController;Ljava/util/ArrayList;IZI)V

    return-void
.end method

.method public static synthetic $r8$lambda$DsFGn63YKZEXBGVoGXEcs9WlGdQ(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedSubtitle()V

    return-void
.end method

.method public static synthetic $r8$lambda$F4eiAaxMg9yP4rxSsmOYk0jMYMY(Lcom/exteragram/messenger/feed/ui/FeedActivity;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lambda$showMarkAllReadDialog$2(Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$IFofORnwThMWquUZdOrDF3V1IUU(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lambda$new$0()V

    return-void
.end method

.method public static synthetic $r8$lambda$O4hi8xMAqrBXSj3kcI5sPjcQPLY(Lcom/exteragram/messenger/feed/ui/FeedActivity;Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lambda$createView$1(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetchatContainer(Lcom/exteragram/messenger/feed/ui/FeedActivity;)Lorg/telegram/ui/ChatActivityContainer;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgethasMainTabs(Lcom/exteragram/messenger/feed/ui/FeedActivity;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetlastWindowInsets(Lcom/exteragram/messenger/feed/ui/FeedActivity;)Landroidx/core/view/WindowInsetsCompat;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lastWindowInsets:Landroidx/core/view/WindowInsetsCompat;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fputembeddedChatCreated(Lcom/exteragram/messenger/feed/ui/FeedActivity;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->embeddedChatCreated:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$mapplyFloatingWindowLayout(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->applyFloatingWindowLayout()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$minvalidateParentTabsGlass(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->invalidateParentTabsGlass()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$msetupChatActionBar(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->setupChatActionBar()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$msetupChatTitle(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->setupChatTitle()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mshowMarkAllReadDialog(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->showMarkAllReadDialog()V

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    .line 64
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;-><init>(Landroid/os/Bundle;)V

    return-void
.end method

.method public constructor <init>(Landroid/os/Bundle;)V
    .locals 0

    .line 68
    invoke-direct {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;-><init>(Landroid/os/Bundle;)V

    .line 101
    new-instance p1, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda1;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V

    iput-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->loadNewPosts:Ljava/lang/Runnable;

    return-void
.end method

.method private static addBottomInset(Landroidx/core/graphics/Insets;I)Landroidx/core/graphics/Insets;
    .locals 3

    .line 298
    iget v0, p0, Landroidx/core/graphics/Insets;->left:I

    iget v1, p0, Landroidx/core/graphics/Insets;->top:I

    iget v2, p0, Landroidx/core/graphics/Insets;->right:I

    iget p0, p0, Landroidx/core/graphics/Insets;->bottom:I

    add-int/2addr p0, p1

    invoke-static {v0, v1, v2, p0}, Landroidx/core/graphics/Insets;->of(IIII)Landroidx/core/graphics/Insets;

    move-result-object p0

    return-object p0
.end method

.method private static addTabsBottomInset(Landroidx/core/view/WindowInsetsCompat;I)Landroidx/core/view/WindowInsetsCompat;
    .locals 4

    .line 286
    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->systemBars()I

    move-result v0

    .line 287
    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->navigationBars()I

    move-result v1

    .line 288
    new-instance v2, Landroidx/core/view/WindowInsetsCompat$Builder;

    invoke-direct {v2, p0}, Landroidx/core/view/WindowInsetsCompat$Builder;-><init>(Landroidx/core/view/WindowInsetsCompat;)V

    .line 289
    invoke-virtual {p0, v0}, Landroidx/core/view/WindowInsetsCompat;->getInsetsIgnoringVisibility(I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-static {v3, p1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->addBottomInset(Landroidx/core/graphics/Insets;I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroidx/core/view/WindowInsetsCompat$Builder;->setStableInsets(Landroidx/core/graphics/Insets;)Landroidx/core/view/WindowInsetsCompat$Builder;

    move-result-object v2

    .line 290
    invoke-virtual {p0, v0}, Landroidx/core/view/WindowInsetsCompat;->getInsets(I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-static {v3, p1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->addBottomInset(Landroidx/core/graphics/Insets;I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-virtual {v2, v0, v3}, Landroidx/core/view/WindowInsetsCompat$Builder;->setInsets(ILandroidx/core/graphics/Insets;)Landroidx/core/view/WindowInsetsCompat$Builder;

    move-result-object v2

    .line 291
    invoke-virtual {p0, v1}, Landroidx/core/view/WindowInsetsCompat;->getInsets(I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-static {v3, p1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->addBottomInset(Landroidx/core/graphics/Insets;I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-virtual {v2, v1, v3}, Landroidx/core/view/WindowInsetsCompat$Builder;->setInsets(ILandroidx/core/graphics/Insets;)Landroidx/core/view/WindowInsetsCompat$Builder;

    move-result-object v2

    .line 292
    invoke-virtual {p0, v0}, Landroidx/core/view/WindowInsetsCompat;->getInsetsIgnoringVisibility(I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-static {v3, p1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->addBottomInset(Landroidx/core/graphics/Insets;I)Landroidx/core/graphics/Insets;

    move-result-object v3

    invoke-virtual {v2, v0, v3}, Landroidx/core/view/WindowInsetsCompat$Builder;->setInsetsIgnoringVisibility(ILandroidx/core/graphics/Insets;)Landroidx/core/view/WindowInsetsCompat$Builder;

    move-result-object v0

    .line 293
    invoke-virtual {p0, v1}, Landroidx/core/view/WindowInsetsCompat;->getInsetsIgnoringVisibility(I)Landroidx/core/graphics/Insets;

    move-result-object p0

    invoke-static {p0, p1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->addBottomInset(Landroidx/core/graphics/Insets;I)Landroidx/core/graphics/Insets;

    move-result-object p0

    invoke-virtual {v0, v1, p0}, Landroidx/core/view/WindowInsetsCompat$Builder;->setInsetsIgnoringVisibility(ILandroidx/core/graphics/Insets;)Landroidx/core/view/WindowInsetsCompat$Builder;

    move-result-object p0

    .line 294
    invoke-virtual {p0}, Landroidx/core/view/WindowInsetsCompat$Builder;->build()Landroidx/core/view/WindowInsetsCompat;

    move-result-object p0

    return-object p0
.end method

.method private applyFloatingWindowLayout()V
    .locals 2

    .line 474
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v0

    if-eqz v0, :cond_4

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v0

    invoke-interface {v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->isLayersLayout()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 477
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p0, :cond_4

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-nez p0, :cond_1

    goto :goto_0

    .line 481
    :cond_1
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getActionBar()Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    .line 482
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getActionBar()Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setOccupyStatusBar(Z)V

    .line 484
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/ChatActivity;->avatarContainer:Lorg/telegram/ui/Components/ChatAvatarContainer;

    if-eqz v0, :cond_3

    .line 485
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ChatAvatarContainer;->setOccupyStatusBar(Z)V

    .line 487
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/ChatActivity;->contentView:Lorg/telegram/ui/ChatActivity$ChatActivityFragmentView;

    if-eqz p0, :cond_4

    .line 488
    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/SizeNotifierFrameLayout;->setOccupyStatusBar(Z)V

    :cond_4
    :goto_0
    return-void
.end method

.method private applyMainTabsHeaderLayout()V
    .locals 3

    .line 493
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_2

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_2

    iget-object v0, v0, Lorg/telegram/ui/ChatActivity;->avatarContainer:Lorg/telegram/ui/Components/ChatAvatarContainer;

    if-nez v0, :cond_0

    goto :goto_1

    .line 496
    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 497
    instance-of v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;

    if-eqz v1, :cond_2

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 500
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getNewChatHeaderStyle()Z

    move-result v1

    if-eqz v1, :cond_1

    const/high16 v1, 0x41400000    # 12.0f

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    :goto_0
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    .line 501
    iget v2, v0, Landroid/view/ViewGroup$MarginLayoutParams;->leftMargin:I

    if-eq v2, v1, :cond_2

    .line 502
    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->leftMargin:I

    .line 503
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    iget-object p0, p0, Lorg/telegram/ui/ChatActivity;->avatarContainer:Lorg/telegram/ui/Components/ChatAvatarContainer;

    invoke-virtual {p0, v0}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :cond_2
    :goto_1
    return-void
.end method

.method private destroyEmbeddedChat()V
    .locals 3

    .line 140
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_1

    .line 141
    iget-boolean v2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    if-nez v2, :cond_0

    iget-boolean v2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->embeddedChatCreated:Z

    if-eqz v2, :cond_0

    .line 142
    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivity;->saveFeedScrollPosition()V

    .line 144
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ChatActivity;->setFeedChannelsChangedCallback(Ljava/lang/Runnable;)V

    .line 145
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ChatActivity;->setGlassSourceInvalidationCallback(Ljava/lang/Runnable;)V

    .line 146
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->embeddedChatCreated:Z

    if-eqz v0, :cond_1

    .line 147
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivity;->onFragmentDestroy()V

    :cond_1
    const/4 v0, 0x0

    .line 150
    iput-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->embeddedChatCreated:Z

    .line 151
    iput-object v1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    return-void
.end method

.method private invalidateParentTabsGlass()V
    .locals 0

    .line 580
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->parentTabsGlassInvalidationCallback:Ljava/lang/Runnable;

    if-eqz p0, :cond_0

    .line 581
    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    :cond_0
    return-void
.end method

.method private synthetic lambda$createView$1(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;
    .locals 0

    .line 202
    iput-object p2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lastWindowInsets:Landroidx/core/view/WindowInsetsCompat;

    .line 203
    invoke-static {}, Lcom/exteragram/messenger/config/BottomNavigationBar;->visible()Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/utils/ui/MainTabsUiHelper;->getTabsViewHeightDp()I

    move-result p0

    int-to-float p0, p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    if-nez p0, :cond_1

    return-object p2

    .line 207
    :cond_1
    invoke-static {p2, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->addTabsBottomInset(Landroidx/core/view/WindowInsetsCompat;I)Landroidx/core/view/WindowInsetsCompat;

    move-result-object p0

    return-object p0
.end method

.method private synthetic lambda$new$0()V
    .locals 1

    .line 102
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_2

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-nez v0, :cond_0

    goto :goto_0

    .line 105
    :cond_0
    iget-boolean p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    if-nez p0, :cond_1

    goto :goto_0

    :cond_1
    const/4 p0, 0x1

    .line 108
    invoke-virtual {v0, p0}, Lorg/telegram/ui/ChatActivity;->loadNewerFeed(Z)V

    :cond_2
    :goto_0
    return-void
.end method

.method private synthetic lambda$showMarkAllReadDialog$2(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 515
    invoke-virtual {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->markAllRead()V

    .line 516
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$raw;->contact_check:I

    sget p2, Lorg/telegram/messenger/R$string;->FeedMarkAllReadDone:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private synthetic lambda$updateFeedSubtitle$3(Lcom/exteragram/messenger/feed/FeedController;Ljava/util/ArrayList;IZI)V
    .locals 0

    if-eqz p4, :cond_0

    .line 550
    invoke-virtual {p1}, Lcom/exteragram/messenger/feed/FeedController;->getIncludedChannelCount()I

    move-result p3

    :cond_0
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->setFeedSubtitle(I)V

    return-void
.end method

.method public static presentFeed(Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 3

    .line 83
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result v0

    if-eqz v0, :cond_3

    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Lorg/telegram/ui/LaunchActivity;->getRightActionBarLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 84
    sget-object p0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    invoke-virtual {p0}, Lorg/telegram/ui/LaunchActivity;->getRightActionBarLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object p0

    .line 85
    invoke-interface {p0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->getLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    instance-of v0, v0, Lcom/exteragram/messenger/feed/ui/FeedActivity;

    if-eqz v0, :cond_0

    goto :goto_1

    .line 88
    :cond_0
    invoke-interface {p0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->getFragmentStack()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    const/4 v1, 0x1

    if-nez v0, :cond_2

    .line 89
    :goto_0
    invoke-interface {p0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->getFragmentStack()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    sub-int/2addr v0, v1

    const/4 v2, 0x0

    if-lez v0, :cond_1

    .line 90
    invoke-interface {p0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->getFragmentStack()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->removeFragmentFromStack(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    goto :goto_0

    .line 93
    :cond_1
    invoke-interface {p0, v2}, Lorg/telegram/ui/ActionBar/INavigationLayout;->closeLastFragment(Z)V

    .line 95
    :cond_2
    new-instance v0, Lorg/telegram/ui/ActionBar/INavigationLayout$NavigationParams;

    new-instance v2, Lcom/exteragram/messenger/feed/ui/FeedActivity;

    invoke-direct {v2}, Lcom/exteragram/messenger/feed/ui/FeedActivity;-><init>()V

    invoke-direct {v0, v2}, Lorg/telegram/ui/ActionBar/INavigationLayout$NavigationParams;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/INavigationLayout$NavigationParams;->setNoAnimation(Z)Lorg/telegram/ui/ActionBar/INavigationLayout$NavigationParams;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/INavigationLayout$NavigationParams;->forceRightLayout()Lorg/telegram/ui/ActionBar/INavigationLayout$NavigationParams;

    move-result-object v0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->presentFragment(Lorg/telegram/ui/ActionBar/INavigationLayout$NavigationParams;)Z

    return-void

    :cond_3
    if-eqz p0, :cond_4

    .line 97
    new-instance v0, Lcom/exteragram/messenger/feed/ui/FeedActivity;

    invoke-direct {v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;-><init>()V

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    :cond_4
    :goto_1
    return-void
.end method

.method private reattachCurrentFeedVideoTexture()V
    .locals 0

    .line 421
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p0, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz p0, :cond_0

    .line 422
    invoke-virtual {p0}, Lorg/telegram/ui/ChatActivity;->reattachCurrentFeedVideoTexture()V

    :cond_0
    return-void
.end method

.method private setFeedSubtitle(I)V
    .locals 4

    .line 554
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_2

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_2

    iget-object v0, v0, Lorg/telegram/ui/ChatActivity;->avatarContainer:Lorg/telegram/ui/Components/ChatAvatarContainer;

    if-nez v0, :cond_0

    goto :goto_1

    :cond_0
    const/4 v1, 0x0

    if-gez p1, :cond_1

    .line 558
    sget p1, Lorg/telegram/messenger/R$string;->Loading:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    .line 559
    :cond_1
    const-string v2, "Channels"

    new-array v3, v1, [Ljava/lang/Object;

    invoke-static {v2, p1, v3}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    .line 557
    :goto_0
    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/ChatAvatarContainer;->setSubtitle(Ljava/lang/CharSequence;)V

    .line 560
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    iget-object p0, p0, Lorg/telegram/ui/ChatActivity;->avatarContainer:Lorg/telegram/ui/Components/ChatAvatarContainer;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatAvatarContainer;->getSubtitleTextView()Landroid/view/View;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 562
    invoke-virtual {p0, v1}, Landroid/view/View;->setVisibility(I)V

    :cond_2
    :goto_1
    return-void
.end method

.method private setupChatActionBar()V
    .locals 5

    .line 427
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_5

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-nez v0, :cond_0

    goto :goto_0

    .line 430
    :cond_0
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getActionBar()Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 434
    :cond_1
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object v1

    const/16 v2, 0x4c

    .line 435
    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->getItem(I)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v3

    if-nez v3, :cond_2

    .line 436
    sget v3, Lorg/telegram/messenger/R$drawable;->msg_markread:I

    iget-object v4, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v4, v4, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    iget-object v4, v4, Lorg/telegram/ui/ChatActivity;->themeDelegate:Lorg/telegram/ui/ChatActivity$ThemeDelegate;

    invoke-virtual {v1, v2, v3, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v2

    .line 437
    sget v3, Lorg/telegram/messenger/R$string;->FeedMarkAllRead:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    :cond_2
    const/16 v2, 0x4b

    .line 439
    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->getItem(I)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v3

    if-nez v3, :cond_3

    .line 440
    sget v3, Lorg/telegram/messenger/R$drawable;->msg_settings:I

    iget-object v4, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v4, v4, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    iget-object v4, v4, Lorg/telegram/ui/ChatActivity;->themeDelegate:Lorg/telegram/ui/ChatActivity$ThemeDelegate;

    invoke-virtual {v1, v2, v3, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v1

    .line 441
    sget v2, Lorg/telegram/messenger/R$string;->FeedSettings:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 443
    :cond_3
    iget-boolean v1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    if-eqz v1, :cond_4

    .line 444
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->applyMainTabsHeaderLayout()V

    .line 446
    :cond_4
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/ActionBar;->getActionBarMenuOnItemClick()Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;

    move-result-object v1

    .line 447
    new-instance v2, Lcom/exteragram/messenger/feed/ui/FeedActivity$4;

    invoke-direct {v2, p0, v0, v1}, Lcom/exteragram/messenger/feed/ui/FeedActivity$4;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;Lorg/telegram/ui/ActionBar/ActionBar;Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    :cond_5
    :goto_0
    return-void
.end method

.method private setupChatTitle()V
    .locals 2

    .line 538
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_1

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_1

    iget-object v0, v0, Lorg/telegram/ui/ChatActivity;->avatarContainer:Lorg/telegram/ui/Components/ChatAvatarContainer;

    if-nez v0, :cond_0

    goto :goto_0

    .line 541
    :cond_0
    sget v1, Lorg/telegram/messenger/R$string;->Feed:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ChatAvatarContainer;->setTitle(Ljava/lang/CharSequence;)V

    .line 542
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivity;->avatarContainer:Lorg/telegram/ui/Components/ChatAvatarContainer;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatAvatarContainer;->setFeedAvatar()V

    .line 543
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedSubtitle()V

    :cond_1
    :goto_0
    return-void
.end method

.method private showMarkAllReadDialog()V
    .locals 3

    .line 508
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 511
    :cond_0
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 512
    sget v1, Lorg/telegram/messenger/R$string;->FeedMarkAllRead:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 513
    sget v1, Lorg/telegram/messenger/R$string;->FeedMarkAllReadConfirm:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 514
    sget v1, Lorg/telegram/messenger/R$string;->MarkAsRead:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda5;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 518
    sget v1, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 519
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method private updateFeedSubtitle()V
    .locals 2

    .line 547
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    .line 548
    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/FeedController;->getIncludedChannelCount()I

    move-result v1

    invoke-direct {p0, v1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->setFeedSubtitle(I)V

    .line 549
    new-instance v1, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;Lcom/exteragram/messenger/feed/FeedController;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/feed/FeedController;->loadChannels(Lcom/exteragram/messenger/feed/FeedController$ChannelsCallback;)V

    return-void
.end method

.method private updateFeedViewportActive(Z)V
    .locals 0

    .line 387
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p0, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz p0, :cond_0

    .line 388
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ChatActivity;->setFeedViewportActive(Z)V

    :cond_0
    return-void
.end method


# virtual methods
.method public canParentTabsSlide(Landroid/view/MotionEvent;Z)Z
    .locals 0

    .line 394
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p1, :cond_0

    iget-object p1, p1, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz p1, :cond_0

    .line 395
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getActionBar()Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    .line 396
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getActionBar()Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->isActionModeShowed()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const/4 p0, 0x1

    return p0
.end method

.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 7

    .line 190
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->destroyEmbeddedChat()V

    const/4 v0, 0x0

    .line 191
    iput-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lastWindowInsets:Landroidx/core/view/WindowInsetsCompat;

    .line 193
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setAddToContainer(Z)V

    .line 194
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/16 v2, 0x8

    invoke-virtual {v0, v2}, Landroid/view/View;->setVisibility(I)V

    .line 196
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-direct {v0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    .line 198
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v0, v2}, Landroid/view/View;->setBackgroundColor(I)V

    .line 200
    iget-boolean v2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    if-eqz v2, :cond_0

    .line 201
    new-instance v2, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda2;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V

    invoke-static {v0, v2}, Landroidx/core/view/ViewCompat;->setOnApplyWindowInsetsListener(Landroid/view/View;Landroidx/core/view/OnApplyWindowInsetsListener;)V

    .line 209
    new-instance v2, Lcom/exteragram/messenger/feed/ui/FeedActivity$1;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$1;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V

    invoke-virtual {v0, v2}, Landroid/view/View;->addOnAttachStateChangeListener(Landroid/view/View$OnAttachStateChangeListener;)V

    .line 225
    :cond_0
    new-instance v2, Landroid/widget/FrameLayout;

    invoke-direct {v2, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    const/4 v3, -0x1

    const/16 v4, 0x77

    .line 226
    invoke-static {v3, v3, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v5

    invoke-virtual {v0, v2, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 228
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    .line 229
    const-string v5, "chatMode"

    const/4 v6, 0x7

    invoke-virtual {v0, v5, v6}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 230
    const-string v5, "searchType"

    const/4 v6, 0x4

    invoke-virtual {v0, v5, v6}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 231
    const-string v5, "hasMainTabs"

    iget-boolean v6, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    invoke-virtual {v0, v5, v6}, Landroid/os/BaseBundle;->putBoolean(Ljava/lang/String;Z)V

    .line 232
    new-instance v5, Lcom/exteragram/messenger/feed/ui/FeedActivity$2;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v6

    invoke-direct {v5, p0, p1, v6, v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$2;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;Landroid/content/Context;Lorg/telegram/ui/ActionBar/INavigationLayout;Landroid/os/Bundle;)V

    iput-object v5, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    .line 250
    iget-object p1, v5, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    iput-boolean v1, p1, Lorg/telegram/ui/ChatActivity;->isInsideContainer:Z

    .line 251
    new-instance v0, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V

    invoke-virtual {p1, v0}, Lorg/telegram/ui/ChatActivity;->setFeedChannelsChangedCallback(Ljava/lang/Runnable;)V

    .line 252
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object p1, p1, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    new-instance v0, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V

    invoke-virtual {p1, v0}, Lorg/telegram/ui/ChatActivity;->setGlassSourceInvalidationCallback(Ljava/lang/Runnable;)V

    .line 253
    iget-boolean p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->viewportFullyVisible:Z

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedViewportActive(Z)V

    .line 254
    iget-boolean p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    if-nez p1, :cond_1

    .line 255
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    invoke-virtual {p1}, Lorg/telegram/ui/ChatActivityContainer;->onPause()V

    .line 257
    :cond_1
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    invoke-static {v3, v3, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v0

    invoke-virtual {v2, p1, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 258
    iget-boolean p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiActiveHeld:Z

    if-nez p1, :cond_2

    const/4 p1, 0x1

    .line 259
    iput-boolean p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiActiveHeld:Z

    .line 260
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/feed/FeedController;->setUiActive(Z)V

    .line 263
    :cond_2
    new-instance p1, Lcom/exteragram/messenger/feed/ui/FeedActivity$3;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity$3;-><init>(Lcom/exteragram/messenger/feed/ui/FeedActivity;)V

    invoke-static {p0, p1}, Lorg/telegram/ui/Components/Bulletin;->addDelegate(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/Bulletin$Delegate;)V

    .line 281
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    return-object p0
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 1

    .line 169
    sget p2, Lorg/telegram/messenger/NotificationCenter;->didReceiveNewMessages:I

    const/4 v0, 0x0

    if-ne p1, p2, :cond_2

    const/4 p1, 0x2

    .line 170
    aget-object p1, p3, p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-nez p1, :cond_5

    .line 171
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-nez p1, :cond_0

    goto :goto_0

    .line 174
    :cond_0
    iget p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {p1}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object p1

    aget-object p2, p3, v0

    check-cast p2, Ljava/lang/Long;

    invoke-virtual {p2}, Ljava/lang/Long;->longValue()J

    move-result-wide p2

    invoke-virtual {p1, p2, p3}, Lcom/exteragram/messenger/feed/FeedController;->isIncludedChannelPost(J)Z

    move-result p1

    if-nez p1, :cond_1

    goto :goto_0

    .line 177
    :cond_1
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->loadNewPosts:Ljava/lang/Runnable;

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 178
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->loadNewPosts:Ljava/lang/Runnable;

    const-wide/16 p1, 0x3e8

    invoke-static {p0, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    .line 179
    :cond_2
    sget p2, Lorg/telegram/messenger/NotificationCenter;->feedNeedReload:I

    if-ne p1, p2, :cond_5

    .line 180
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p1, :cond_4

    iget-object p1, p1, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz p1, :cond_4

    .line 181
    array-length p1, p3

    if-lez p1, :cond_3

    sget-object p1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    aget-object p2, p3, v0

    invoke-virtual {p1, p2}, Ljava/lang/Boolean;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    const/4 v0, 0x1

    .line 182
    :cond_3
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object p1, p1, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    invoke-virtual {p1, v0}, Lorg/telegram/ui/ChatActivity;->onFeedChannelsChanged(Z)V

    .line 184
    :cond_4
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedSubtitle()V

    :cond_5
    :goto_0
    return-void
.end method

.method public drawEdgeNavigationBar()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public getGlassSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;
    .locals 0

    .line 568
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p0, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz p0, :cond_0

    .line 569
    invoke-virtual {p0}, Lorg/telegram/ui/ChatActivity;->getGlassSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public isLightStatusBar()Z
    .locals 0

    .line 404
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p0, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz p0, :cond_0

    .line 405
    invoke-virtual {p0}, Lorg/telegram/ui/ChatActivity;->isLightStatusBar()Z

    move-result p0

    return p0

    .line 407
    :cond_0
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    return p0
.end method

.method public isSupportEdgeToEdge()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public markAllRead()V
    .locals 1

    .line 523
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_0

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_0

    .line 524
    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivity;->markFeedAsRead()V

    return-void

    .line 526
    :cond_0
    iget p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/feed/FeedController;->markAllRead()V

    return-void
.end method

.method public onBackPressed(Z)Z
    .locals 1

    .line 156
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_1

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_1

    .line 157
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getActionBar()Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    .line 158
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getActionBar()Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/ActionBar;->isActionModeShowed()Z

    move-result v0

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    .line 160
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    invoke-virtual {p0}, Lorg/telegram/ui/ChatActivity;->clearSelectionMode()V

    :cond_0
    const/4 p0, 0x0

    return p0

    .line 164
    :cond_1
    invoke-super {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->onBackPressed(Z)Z

    move-result p0

    return p0
.end method

.method public onBecomeFullyHidden()V
    .locals 1

    const/4 v0, 0x0

    .line 345
    iput-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->viewportFullyVisible:Z

    .line 346
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedViewportActive(Z)V

    .line 347
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onBecomeFullyHidden()V

    return-void
.end method

.method public onBecomeFullyVisible()V
    .locals 1

    .line 336
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onBecomeFullyVisible()V

    const/4 v0, 0x1

    .line 337
    iput-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->viewportFullyVisible:Z

    .line 338
    invoke-virtual {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateMainTabsVisibility()V

    .line 339
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedViewportActive(Z)V

    .line 340
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->reattachCurrentFeedVideoTexture()V

    return-void
.end method

.method public onFragmentCreate()Z
    .locals 4

    .line 113
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->arguments:Landroid/os/Bundle;

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    const-string v3, "hasMainTabs"

    invoke-virtual {v0, v3, v2}, Landroid/os/BaseBundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    move v2, v1

    :cond_0
    iput-boolean v2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    xor-int/lit8 v0, v2, 0x1

    .line 114
    iput-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->viewportFullyVisible:Z

    .line 115
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->didReceiveNewMessages:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 116
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->feedNeedReload:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 117
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedConfig;->getInstance(I)Lcom/exteragram/messenger/feed/FeedConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/FeedConfig;->getGeneration()I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lastConfigGeneration:I

    .line 118
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 123
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->loadNewPosts:Ljava/lang/Runnable;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 124
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->destroyEmbeddedChat()V

    .line 125
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 126
    iput-boolean v1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    .line 127
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/feed/FeedController;->setUiResumed(Z)V

    .line 129
    :cond_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiActiveHeld:Z

    if-eqz v0, :cond_1

    .line 130
    iput-boolean v1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiActiveHeld:Z

    .line 131
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/feed/FeedController;->setUiActive(Z)V

    .line 133
    :cond_1
    invoke-static {p0}, Lorg/telegram/ui/Components/Bulletin;->removeDelegate(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    .line 134
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->didReceiveNewMessages:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 135
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->feedNeedReload:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 136
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method

.method public onParentBecomeFullyVisible()V
    .locals 0

    .line 370
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->reattachCurrentFeedVideoTexture()V

    return-void
.end method

.method public onParentScrollToTop()V
    .locals 1

    .line 532
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz p0, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz p0, :cond_0

    const/4 v0, 0x0

    .line 533
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ChatActivity;->onPageDownClicked(Z)V

    :cond_0
    return-void
.end method

.method public onPause()V
    .locals 2

    .line 375
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onPause()V

    .line 376
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 377
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedViewportActive(Z)V

    .line 378
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivityContainer;->onPause()V

    .line 380
    :cond_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    if-eqz v0, :cond_1

    .line 381
    iput-boolean v1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    .line 382
    iget p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object p0

    invoke-virtual {p0, v1}, Lcom/exteragram/messenger/feed/FeedController;->setUiResumed(Z)V

    :cond_1
    return-void
.end method

.method public onResume()V
    .locals 3

    .line 303
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onResume()V

    .line 304
    invoke-virtual {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateMainTabsVisibility()V

    .line 305
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_0

    .line 306
    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivityContainer;->onResume()V

    .line 307
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->viewportFullyVisible:Z

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedViewportActive(Z)V

    .line 309
    :cond_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    const/4 v1, 0x1

    if-nez v0, :cond_1

    .line 310
    iput-boolean v1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->uiResumedHeld:Z

    .line 311
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/feed/FeedController;->setUiResumed(Z)V

    .line 313
    :cond_1
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz v0, :cond_2

    iget-object v2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lastWindowInsets:Landroidx/core/view/WindowInsetsCompat;

    if-eqz v2, :cond_2

    .line 314
    invoke-static {v0, v2}, Landroidx/core/view/ViewCompat;->dispatchApplyWindowInsets(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;

    .line 316
    :cond_2
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->reattachCurrentFeedVideoTexture()V

    .line 317
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedConfig;->getInstance(I)Lcom/exteragram/messenger/feed/FeedConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/FeedConfig;->getGeneration()I

    move-result v0

    .line 318
    iget v2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lastConfigGeneration:I

    if-eq v0, v2, :cond_3

    .line 319
    iput v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->lastConfigGeneration:I

    .line 320
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_4

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_4

    .line 321
    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivity;->applyFeedConfigChange()V

    goto :goto_0

    .line 323
    :cond_3
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->resumedOnce:Z

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    if-eqz v0, :cond_4

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    if-eqz v0, :cond_4

    .line 324
    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivity;->reconcileFeedList()V

    .line 325
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    invoke-virtual {v0}, Lorg/telegram/ui/ChatActivity;->refreshFeedUnreadDivider()V

    .line 326
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/FeedController;->getMessages()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_4

    .line 327
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->chatContainer:Lorg/telegram/ui/ChatActivityContainer;

    iget-object v0, v0, Lorg/telegram/ui/ChatActivityContainer;->chatActivity:Lorg/telegram/ui/ChatActivity;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ChatActivity;->loadNewerFeed(Z)V

    .line 330
    :cond_4
    :goto_0
    iput-boolean v1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->resumedOnce:Z

    .line 331
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedSubtitle()V

    return-void
.end method

.method public onTransitionAnimationEnd(ZZ)V
    .locals 0

    .line 361
    invoke-super {p0, p1, p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->onTransitionAnimationEnd(ZZ)V

    .line 362
    iget-boolean p2, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    if-eqz p2, :cond_0

    .line 363
    iput-boolean p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->viewportFullyVisible:Z

    .line 364
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedViewportActive(Z)V

    :cond_0
    return-void
.end method

.method public onTransitionAnimationStart(ZZ)V
    .locals 1

    .line 352
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->hasMainTabs:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    .line 353
    iput-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->viewportFullyVisible:Z

    .line 354
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/feed/ui/FeedActivity;->updateFeedViewportActive(Z)V

    .line 356
    :cond_0
    invoke-super {p0, p1, p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->onTransitionAnimationStart(ZZ)V

    return-void
.end method

.method public setMainTabsActivityController(Lorg/telegram/ui/MainTabsActivityController;)V
    .locals 0

    .line 72
    iput-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->mainTabsActivityController:Lorg/telegram/ui/MainTabsActivityController;

    return-void
.end method

.method public setParentTabsGlassInvalidationCallback(Ljava/lang/Runnable;)V
    .locals 0

    .line 576
    iput-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->parentTabsGlassInvalidationCallback:Ljava/lang/Runnable;

    return-void
.end method

.method public updateMainTabsVisibility()V
    .locals 1

    .line 77
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedActivity;->mainTabsActivityController:Lorg/telegram/ui/MainTabsActivityController;

    if-eqz p0, :cond_0

    .line 78
    invoke-static {}, Lcom/exteragram/messenger/config/BottomNavigationBar;->visible()Z

    move-result v0

    invoke-interface {p0, v0}, Lorg/telegram/ui/MainTabsActivityController;->setTabsVisible(Z)V

    :cond_0
    return-void
.end method
