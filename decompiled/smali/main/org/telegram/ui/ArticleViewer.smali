.class public Lorg/telegram/ui/ArticleViewer;
.super Lorg/telegram/ui/IArticleViewer;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/ui/ArticleViewer$CheckForLongPress;,
        Lorg/telegram/ui/ArticleViewer$CheckForTap;,
        Lorg/telegram/ui/ArticleViewer$FontCell;,
        Lorg/telegram/ui/ArticleViewer$Sheet;,
        Lorg/telegram/ui/ArticleViewer$DrawingText;,
        Lorg/telegram/ui/ArticleViewer$WebpageAdapter;,
        Lorg/telegram/ui/ArticleViewer$BlockListItemCell;,
        Lorg/telegram/ui/ArticleViewer$BlockOrderedListItemCell;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockListParent;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListParent;,
        Lorg/telegram/ui/ArticleViewer$PageLayout;,
        Lorg/telegram/ui/ArticleViewer$CachedWeb;,
        Lorg/telegram/ui/ArticleViewer$WindowView;,
        Lorg/telegram/ui/ArticleViewer$Resources;,
        Lorg/telegram/ui/ArticleViewer$WebPageUtils;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;,
        Lorg/telegram/ui/ArticleViewer$BlockDetailsCell;,
        Lorg/telegram/ui/ArticleViewer$BlockAudioCell;,
        Lorg/telegram/ui/ArticleViewer$BlockTableCell;,
        Lorg/telegram/ui/ArticleViewer$BlockVideoCell;,
        Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;,
        Lorg/telegram/ui/ArticleViewer$SearchResult;,
        Lorg/telegram/ui/ArticleViewer$BlockEmbedCell;,
        Lorg/telegram/ui/ArticleViewer$BlockChannelCell;,
        Lorg/telegram/ui/ArticleViewer$RealPageBlocksAdapter;,
        Lorg/telegram/ui/ArticleViewer$PageBlocksPhotoViewerProvider;,
        Lorg/telegram/ui/ArticleViewer$TextSizeCell;,
        Lorg/telegram/ui/ArticleViewer$ReportCell;,
        Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesCell;,
        Lorg/telegram/ui/ArticleViewer$ErrorContainer;,
        Lorg/telegram/ui/ArticleViewer$WebpageListView;,
        Lorg/telegram/ui/ArticleViewer$BlockMathCell;,
        Lorg/telegram/ui/ArticleViewer$BlockSubheaderCell;,
        Lorg/telegram/ui/ArticleViewer$BlockPreformattedCell;,
        Lorg/telegram/ui/ArticleViewer$BlockFooterCell;,
        Lorg/telegram/ui/ArticleViewer$BlockKickerCell;,
        Lorg/telegram/ui/ArticleViewer$BlockTitleCell;,
        Lorg/telegram/ui/ArticleViewer$BlockAuthorDateCell;,
        Lorg/telegram/ui/ArticleViewer$BlockMapCell;,
        Lorg/telegram/ui/ArticleViewer$BlockPhotoCell;,
        Lorg/telegram/ui/ArticleViewer$BlockBlockquoteCell;,
        Lorg/telegram/ui/ArticleViewer$BlockPullquoteCell;,
        Lorg/telegram/ui/ArticleViewer$BlockSubtitleCell;,
        Lorg/telegram/ui/ArticleViewer$BlockDividerCell;,
        Lorg/telegram/ui/ArticleViewer$BlockHeaderCell;,
        Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesHeaderCell;,
        Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesShadowCell;,
        Lorg/telegram/ui/ArticleViewer$BlockDetailsBottomCell;,
        Lorg/telegram/ui/ArticleViewer$BlockSlideshowCell;,
        Lorg/telegram/ui/ArticleViewer$BlockCollageCell;,
        Lorg/telegram/ui/ArticleViewer$BlockParagraphCell;,
        Lorg/telegram/ui/ArticleViewer$LatexScrollHelper;,
        Lorg/telegram/ui/ArticleViewer$BlockEmbedPostCell;,
        Lorg/telegram/ui/ArticleViewer$IBlock;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockEmbedPostCaption;,
        Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesShadow;
    }
.end annotation


# static fields
.field public static final ARTICLE_VIEWER_INNER_TRANSLATION_X:Landroid/util/Property;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/Property<",
            "Lorg/telegram/ui/ArticleViewer$WindowView;",
            "Ljava/lang/Float;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile Instance:Lorg/telegram/ui/ArticleViewer;
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "StaticFieldLeak"
        }
    .end annotation
.end field

.field public static activeSheets:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet<",
            "Lorg/telegram/ui/ArticleViewer;",
            ">;"
        }
    .end annotation
.end field

.field private static final audioTimePaint:Landroid/text/TextPaint;

.field private static channelNamePaint:Landroid/text/TextPaint;

.field private static channelNamePhotoPaint:Landroid/text/TextPaint;

.field public static debugCopiedRichMessageWebPage:Lorg/telegram/tgnet/TLRPC$WebPage;

.field private static dividerPaint:Landroid/graphics/Paint;

.field private static dotsPaint:Landroid/graphics/Paint;

.field private static embedPostAuthorPaint:Landroid/text/TextPaint;

.field private static embedPostDatePaint:Landroid/text/TextPaint;

.field private static listTextNumPaint:Landroid/text/TextPaint;

.field private static listTextPointerPaint:Landroid/text/TextPaint;

.field private static final liveDrawingTexts:Ljava/util/WeakHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/WeakHashMap<",
            "Landroid/view/View;",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/ArticleViewer$DrawingText;",
            ">;>;"
        }
    .end annotation
.end field

.field private static photoBackgroundPaint:Landroid/graphics/Paint;

.field private static preformattedBackgroundPaint:Landroid/graphics/Paint;

.field private static quoteLinePaint:Landroid/graphics/Paint;

.field private static relatedArticleHeaderPaint:Landroid/text/TextPaint;

.field private static relatedArticleTextPaint:Landroid/text/TextPaint;

.field private static final resources:Lorg/telegram/ui/ArticleViewer$Resources;

.field public static tableHalfLinePaint:Landroid/graphics/Paint;

.field public static tableHeaderPaint:Landroid/graphics/Paint;

.field public static tableLinePaint:Landroid/graphics/Paint;

.field public static tableStripPaint:Landroid/graphics/Paint;

.field private static urlPaint:Landroid/graphics/Paint;

.field private static webpageMarkPaint:Landroid/graphics/Paint;

.field private static webpageSearchPaint:Landroid/graphics/Paint;

.field private static webpageUrlPaint:Landroid/graphics/Paint;


# instance fields
.field private final BOTTOM_SHEET_VIEW_TAG:Ljava/lang/String;

.field private actionBar:Lorg/telegram/ui/web/WebActionBar;

.field private activityVisibilityController:Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

.field private addressBarList:Lorg/telegram/ui/web/AddressBarList;

.field private anchorsOffsetMeasuredWidth:I

.field private animationEndRunnable:Ljava/lang/Runnable;

.field private animationInProgress:I

.field private attachedToWindow:Z

.field private backgroundPaint:Landroid/graphics/Paint;

.field private bulletinContainer:Landroid/widget/FrameLayout;

.field private checkingForLongPress:Z

.field private closeAnimationInProgress:Z

.field private collapsed:Z

.field private containerView:Landroid/widget/FrameLayout;

.field private createdWebViews:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/ArticleViewer$BlockEmbedCell;",
            ">;"
        }
    .end annotation
.end field

.field private currentAccount:I

.field private currentHeaderHeight:I

.field private currentPlayingVideo:Lorg/telegram/ui/Components/WebPlayerView;

.field private customView:Landroid/view/View;

.field private customViewCallback:Landroid/webkit/WebChromeClient$CustomViewCallback;

.field private deleteView:Landroid/widget/TextView;

.field private fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

.field private fullscreenAspectRatioView:Lorg/telegram/ui/AspectRatioFrameLayout;

.field private fullscreenTextureView:Landroid/view/TextureView;

.field private fullscreenVideoContainer:Landroid/widget/FrameLayout;

.field private fullscreenedVideo:Lorg/telegram/ui/Components/WebPlayerView;

.field private hasCutout:Z

.field private headerPaint:Landroid/graphics/Paint;

.field private headerProgressPaint:Landroid/graphics/Paint;

.field private interpolator:Landroid/view/animation/DecelerateInterpolator;

.field public final isSheet:Z

.field private isVisible:Z

.field private keyboardVisible:Z

.field private lastBlockNum:I

.field private lastInsets:Ljava/lang/Object;

.field private lastReqId:I

.field private lastSearchIndex:I

.field private lineProgressTickRunnable:Ljava/lang/Runnable;

.field private loadingProgress:Lorg/telegram/messenger/browser/Browser$Progress;

.field private navigationBarPaint:Landroid/graphics/Paint;

.field private final notificationsLocker:Lorg/telegram/messenger/AnimationNotificationsLocker;

.field private openUrlReqId:I

.field private final page0Background:Lorg/telegram/ui/Components/AnimatedColor;

.field private final page1Background:Lorg/telegram/ui/Components/AnimatedColor;

.field private pageSwitchAnimation:Landroid/animation/AnimatorSet;

.field public pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

.field public final pagesStack:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private parentActivity:Landroid/app/Activity;

.field private parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

.field private pendingCheckForLongPress:Lorg/telegram/ui/ArticleViewer$CheckForLongPress;

.field private pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

.field pinchToZoomHelper:Lorg/telegram/ui/PinchToZoomHelper;

.field private popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

.field private popupRect:Landroid/graphics/Rect;

.field private pressCount:I

.field private previewsReqId:I

.field private progressView:Lorg/telegram/ui/Components/ContextProgressView;

.field private progressViewAnimation:Landroid/animation/AnimatorSet;

.field private runAfterKeyboardClose:Landroid/animation/AnimatorSet;

.field private scrimPaint:Landroid/graphics/Paint;

.field private searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

.field private searchDownButton:Landroid/widget/ImageView;

.field private searchPanel:Landroid/widget/FrameLayout;

.field private searchPanelAlpha:F

.field private searchPanelAnimator:Landroid/animation/ValueAnimator;

.field private searchPanelTranslation:F

.field private searchRunnable:Ljava/lang/Runnable;

.field private searchUpButton:Landroid/widget/ImageView;

.field public final sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

.field private showRestrictedToastOnResume:Z

.field private statusBarPaint:Landroid/graphics/Paint;

.field textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

.field textSelectionHelperBottomSheet:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

.field private transitionAnimationStartTime:J

.field private visibleDialog:Landroid/app/Dialog;

.field private windowLayoutParams:Landroid/view/WindowManager$LayoutParams;

.field private windowView:Lorg/telegram/ui/ArticleViewer$WindowView;


# direct methods
.method public static synthetic $r8$lambda$-3Y4umc6RBPtLBCujcMqrVkhQLY(Lorg/telegram/ui/ArticleViewer;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$28(I)V

    return-void
.end method

.method public static synthetic $r8$lambda$-asnsn5Zl721mOeX3wdnskZlF2w(ILorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 3

    .line 6195
    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    iget-wide v0, p1, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    const/4 p1, 0x0

    const/4 v2, 0x1

    invoke-virtual {p0, v0, v1, p1, v2}, Lorg/telegram/messenger/MessagesController;->loadFullChat(JIZ)V

    return-void
.end method

.method public static synthetic $r8$lambda$02t8gsijKZQPo0K20QMKmCKdZZQ(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$close$57()V

    return-void
.end method

.method public static synthetic $r8$lambda$0ILYrdYxkacA8Lknyzdw02WSNy8(Lorg/telegram/ui/ArticleViewer$BlockChannelCell;)V
    .locals 2

    const/4 v0, 0x2

    const/4 v1, 0x0

    .line 6194
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/ArticleViewer$BlockChannelCell;->setState(IZ)V

    return-void
.end method

.method public static synthetic $r8$lambda$0VE4t69k1_YadRNPawfS3ppWslo(IILorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V
    .locals 2

    const/4 v0, 0x0

    :goto_0
    sub-int v1, p0, p1

    if-ge v0, v1, :cond_0

    .line 4611
    invoke-virtual {p2}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->goBack()V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public static synthetic $r8$lambda$1AlziumK-N6mMX1QQQ6YeLwNTpQ(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/TLObject;IJ)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$16(Lorg/telegram/tgnet/TLObject;IJ)V

    return-void
.end method

.method public static synthetic $r8$lambda$1FnldbrHw9FLSFZef5-f7vYhtUo(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/messenger/browser/Browser$Progress;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$openWebpageUrlInternal$9(Lorg/telegram/messenger/browser/Browser$Progress;)V

    return-void
.end method

.method public static synthetic $r8$lambda$2rhW2LfNfDa61aBbL2Y3O-1tacw(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/tgnet/TLObject;ILorg/telegram/ui/ArticleViewer$BlockChannelCell;)V
    .locals 3

    const/4 v0, 0x0

    .line 6133
    iput-boolean v0, p0, Lorg/telegram/ui/IArticleViewer;->loadingChannel:Z

    .line 6134
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetblocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_0

    return-void

    :cond_0
    const/4 p1, 0x4

    if-nez p2, :cond_3

    .line 6136
    check-cast p3, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;

    .line 6137
    iget-object p2, p3, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->chats:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p2

    if-nez p2, :cond_2

    .line 6138
    invoke-static {p4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p2

    iget-object v1, p3, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->users:Ljava/util/ArrayList;

    invoke-virtual {p2, v1, v0}, Lorg/telegram/messenger/MessagesController;->putUsers(Ljava/util/ArrayList;Z)V

    .line 6139
    invoke-static {p4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p2

    iget-object v1, p3, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->chats:Ljava/util/ArrayList;

    invoke-virtual {p2, v1, v0}, Lorg/telegram/messenger/MessagesController;->putChats(Ljava/util/ArrayList;Z)V

    .line 6140
    invoke-static {p4}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object p2

    iget-object p4, p3, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->users:Ljava/util/ArrayList;

    iget-object v1, p3, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->chats:Ljava/util/ArrayList;

    const/4 v2, 0x1

    invoke-virtual {p2, p4, v1, v0, v2}, Lorg/telegram/messenger/MessagesStorage;->putUsersAndChats(Ljava/util/List;Ljava/util/List;ZZ)V

    .line 6141
    iget-object p2, p3, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->chats:Ljava/util/ArrayList;

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lorg/telegram/tgnet/TLRPC$Chat;

    iput-object p2, p0, Lorg/telegram/ui/IArticleViewer;->loadedChannel:Lorg/telegram/tgnet/TLRPC$Chat;

    .line 6142
    iget-boolean p0, p2, Lorg/telegram/tgnet/TLRPC$Chat;->left:Z

    if-eqz p0, :cond_1

    iget-boolean p0, p2, Lorg/telegram/tgnet/TLRPC$Chat;->kicked:Z

    if-nez p0, :cond_1

    .line 6143
    invoke-virtual {p5, v0, v0}, Lorg/telegram/ui/ArticleViewer$BlockChannelCell;->setState(IZ)V

    return-void

    .line 6145
    :cond_1
    invoke-virtual {p5, p1, v0}, Lorg/telegram/ui/ArticleViewer$BlockChannelCell;->setState(IZ)V

    return-void

    .line 6148
    :cond_2
    invoke-virtual {p5, p1, v0}, Lorg/telegram/ui/ArticleViewer$BlockChannelCell;->setState(IZ)V

    return-void

    .line 6151
    :cond_3
    invoke-virtual {p5, p1, v0}, Lorg/telegram/ui/ArticleViewer$BlockChannelCell;->setState(IZ)V

    return-void
.end method

.method public static synthetic $r8$lambda$36JU3CvhHIYg5FLcslC9g93KyeQ(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->lambda$processSearch$50(Ljava/lang/String;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$3JzqyA-pL7fDkmI5BvLTETBm1gQ(Lorg/telegram/ui/ArticleViewer;Ljava/lang/Integer;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$42(Ljava/lang/Integer;)V

    return-void
.end method

.method public static synthetic $r8$lambda$4Gj7v6vLif4aGWMc2BeO_72wI6o(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$new$67()V

    return-void
.end method

.method public static synthetic $r8$lambda$6JYXlKhgO0ydcDzIbh9vRHkJhbk(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$26(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$6RceP80-ceRWcdOvUlvuC-0xFT8(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/messenger/browser/Browser$Progress;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p7}, Lorg/telegram/ui/ArticleViewer;->lambda$openWebpageUrlInternal$12(ILorg/telegram/messenger/browser/Browser$Progress;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$74RSsw0yybn2MfWSYAcftwLHfzs(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$showPopup$5()V

    return-void
.end method

.method public static synthetic $r8$lambda$7Ow6upENl44LN33FFQBmMRW5X0I(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;ILorg/telegram/ui/ArticleViewer$BlockChannelCell;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 7

    .line 6132
    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda56;

    move-object v1, p0

    move-object v2, p1

    move v5, p2

    move-object v6, p3

    move-object v4, p4

    move-object v3, p5

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda56;-><init>(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/tgnet/TLObject;ILorg/telegram/ui/ArticleViewer$BlockChannelCell;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$8C1j81eT1F-90rB_o-MzEI4lk-8(ILorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultWebView;Lorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 10

    .line 6184
    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    iget-object v1, p1, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultWebView;->users:Ljava/util/ArrayList;

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/messenger/MessagesController;->putUsers(Ljava/util/ArrayList;Z)V

    .line 6185
    invoke-static {p0}, Lorg/telegram/messenger/BotGuardHelper;->getInstance(I)Lorg/telegram/messenger/BotGuardHelper;

    move-result-object v3

    iget-wide v0, p2, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v4, v0

    iget-wide v6, p1, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultWebView;->bot_id:J

    iget-wide v8, p1, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultWebView;->query_id:J

    invoke-virtual/range {v3 .. v9}, Lorg/telegram/messenger/BotGuardHelper;->openGuardBotWebApp(JJJ)V

    return-void
.end method

.method public static synthetic $r8$lambda$8oJ6Pw52FvMD3ySGSPDBo6sf_kw(Lorg/telegram/ui/ArticleViewer;Landroid/animation/AnimatorSet;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$open$56(Landroid/animation/AnimatorSet;)V

    return-void
.end method

.method public static synthetic $r8$lambda$8otrYD4s0DPQGWr5Yyto0sRbppQ(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/view/View;IFF)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$18(Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/view/View;IFF)V

    return-void
.end method

.method public static synthetic $r8$lambda$9jKEg86GRoz1EeYJ8534CkRhF1w(Landroid/view/View;Landroid/view/WindowInsets;)Landroid/view/WindowInsets;
    .locals 1

    .line 4245
    sget p0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v0, 0x1e

    if-lt p0, v0, :cond_0

    .line 4246
    sget-object p0, Landroid/view/WindowInsets;->CONSUMED:Landroid/view/WindowInsets;

    return-object p0

    .line 4248
    :cond_0
    invoke-virtual {p1}, Landroid/view/WindowInsets;->consumeSystemWindowInsets()Landroid/view/WindowInsets;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$BLjSHLAI0LaPdxxpRUoJJXkp2ok(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$onClosed$58()V

    return-void
.end method

.method public static synthetic $r8$lambda$BNj_d_tCBhjveAXYrXic510GX3g(Lorg/telegram/ui/ArticleViewer;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$showSearchPanel$51(Landroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static synthetic $r8$lambda$CKMBC-jm1JGMV6wnFpcuAiERrl0(Lorg/telegram/ui/ArticleViewer;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$29(I)V

    return-void
.end method

.method public static synthetic $r8$lambda$CUeBBA7rex7MM_OPN2B9KTZggek(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/ArticleViewer;->lambda$openWebpageUrl$8(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$D3c0nSuNaaJAHfSY4aTWgYy6Zxc(Lorg/telegram/ui/ArticleViewer;[F)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$46([F)V

    return-void
.end method

.method public static synthetic $r8$lambda$ElJDfEpMbLO4rYoPvduwzF0iG6g(Lorg/telegram/ui/ArticleViewer;Landroid/app/Activity;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$24(Landroid/app/Activity;Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ErZmxaCY5Sg7SiFZ1r6wurONDzA(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->showRestrictedWebsiteToast()V

    return-void
.end method

.method public static synthetic $r8$lambda$IAn0dmuVq1jazh-0ytybrvo7A84(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;[ZLorg/telegram/messenger/browser/Browser$Progress;)Ljava/lang/Boolean;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->lambda$openWebpageUrlInternal$10(Ljava/lang/String;[ZLorg/telegram/messenger/browser/Browser$Progress;)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$JAz11XDsZfWwUh4q3w0tb4Znjnw(Lorg/telegram/ui/ArticleViewer;ILjava/util/ArrayList;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->lambda$processSearch$48(ILjava/util/ArrayList;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Ks2Cyb79Lr1QJeHp-g-ta-Cfems(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->lambda$showPopup$2(Landroid/view/View;Landroid/view/MotionEvent;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$L5i5IBNeZkt3_FHAdkzBPkcEhss(Lorg/telegram/ui/ArticleViewer;Landroid/content/DialogInterface;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$showCopyPopup$1(Landroid/content/DialogInterface;)V

    return-void
.end method

.method public static synthetic $r8$lambda$MAMR32BGK5w8Zxks4jF0qzyzMkI(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$36(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Mw6jU7MdCnYhrTErivWQygxJQLc(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$38(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$OjOvKf9K2XbeUwjcccuNtJtbK8M(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/app/Activity;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$20(Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/app/Activity;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$OvEL1p7qCbu4yMW7WKcqgoQRWvU(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$34()V

    return-void
.end method

.method public static synthetic $r8$lambda$PS164Vcw9q1zP9LT8ZZOb2zLS8A(Lorg/telegram/ui/ArticleViewer;Ljava/util/ArrayList;Ljava/util/HashMap;Ljava/lang/String;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/ArticleViewer;->lambda$processSearch$49(Ljava/util/ArrayList;Ljava/util/HashMap;Ljava/lang/String;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$PUGGK0ZB0DrUTVW53DgerWIIjZM(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/Components/ItemOptions;F)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$31(Lorg/telegram/ui/Components/ItemOptions;F)V

    return-void
.end method

.method public static synthetic $r8$lambda$PZJ2WZeK73MBHrUqQZnsOrz89gI(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->updateSearchButtons()V

    return-void
.end method

.method public static synthetic $r8$lambda$RYC3ysSAvYmU-l4WmMfR7yjiIGg(Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/app/Activity;Ljava/lang/String;)V
    .locals 6

    .line 4502
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_1

    .line 4503
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_1

    goto :goto_1

    .line 4504
    :cond_1
    new-instance v0, Landroid/text/SpannableStringBuilder;

    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 4505
    invoke-static {v0, v1, v2, v1}, Lorg/telegram/messenger/AndroidUtilities;->addLinksSafe(Landroid/text/Spannable;IZZ)Z

    .line 4506
    invoke-virtual {v0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    const-class v3, Landroid/text/style/URLSpan;

    invoke-virtual {v0, v2, v1, v3}, Landroid/text/SpannableStringBuilder;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/text/style/URLSpan;

    .line 4507
    invoke-virtual {v0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v3

    move v4, v3

    move v3, v2

    .line 4508
    :goto_0
    array-length v5, v1

    if-ge v2, v5, :cond_2

    .line 4509
    aget-object v5, v1, v2

    invoke-virtual {v0, v5}, Landroid/text/SpannableStringBuilder;->getSpanStart(Ljava/lang/Object;)I

    move-result v5

    invoke-static {v5, v4}, Ljava/lang/Math;->min(II)I

    move-result v4

    .line 4510
    aget-object v5, v1, v2

    invoke-virtual {v0, v5}, Landroid/text/SpannableStringBuilder;->getSpanEnd(Ljava/lang/Object;)I

    move-result v5

    invoke-static {v5, v3}, Ljava/lang/Math;->max(II)I

    move-result v3

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 4512
    :cond_2
    invoke-static {p2}, Lorg/telegram/messenger/Utilities;->uriParseSafe(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 4513
    invoke-virtual {v0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v2

    const-string/jumbo v5, "javascript"

    invoke-static {v2, v5}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_3

    :goto_1
    return-void

    .line 4514
    :cond_3
    array-length v1, v1

    if-lez v1, :cond_4

    if-nez v4, :cond_4

    if-gtz v3, :cond_5

    :cond_4
    if-eqz v0, :cond_7

    invoke-virtual {v0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_7

    :cond_5
    if-eqz v0, :cond_6

    .line 4515
    invoke-virtual {v0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_6

    invoke-virtual {v0}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_6

    invoke-virtual {v0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_6

    .line 4516
    invoke-virtual {v0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p1

    const-string p2, "/"

    const-string/jumbo v1, "https"

    const/4 v2, 0x0

    invoke-static {v0, v1, v2, p1, p2}, Lorg/telegram/messenger/browser/Browser;->replace(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    .line 4518
    :cond_6
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->loadUrl(Ljava/lang/String;)V

    return-void

    .line 4520
    :cond_7
    invoke-static {p1, p2}, Lorg/telegram/ui/web/AddressBarList;->pushRecentSearch(Landroid/content/Context;Ljava/lang/String;)V

    .line 4521
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-static {}, Lorg/telegram/ui/web/SearchEngine;->getCurrent()Lorg/telegram/ui/web/SearchEngine;

    move-result-object p1

    invoke-virtual {p1, p2}, Lorg/telegram/ui/web/SearchEngine;->getSearchURL(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->loadUrl(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$RqE9MlU3HD4RuNBz1jK0fZCoTs0(Lorg/telegram/ui/ArticleViewer$DrawingText;Landroid/view/View;)V
    .locals 1

    .line 3796
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilers:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 3797
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilersPatchedLayout:Ljava/util/concurrent/atomic/AtomicReference;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V

    .line 3798
    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public static synthetic $r8$lambda$Stx_b7xHEknyC63zc_pg5GKJ9cQ(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;Landroid/content/DialogInterface;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->lambda$showCopyPopup$0(Ljava/lang/String;Landroid/content/DialogInterface;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$V4ax3i4tny42HjOHE16pQ7ndU5M(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$25()V

    return-void
.end method

.method public static synthetic $r8$lambda$VXtfEI-lBXzqpN1-Lz-N82g3pIM(Lorg/telegram/ui/ArticleViewer;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$45(Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$VhRtpTRaoBu9huoetluRq2zy4bc(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$33()V

    return-void
.end method

.method public static synthetic $r8$lambda$W5eQdg7rYkYpmsa-an-edKbyLnw(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$22(Ljava/lang/String;Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$YlQoHlm_1GH2g95Zfz0bUBOw1bw(Lorg/telegram/ui/ArticleViewer;F)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$30(F)V

    return-void
.end method

.method public static synthetic $r8$lambda$ZBx4VumPMIvQtsuvWEYwtGMUvBc(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p7}, Lorg/telegram/ui/ArticleViewer;->lambda$open$54(ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ZoymgkU5M0EdB0B1XJazO7r2mOM(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$showPopup$4(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$aQ8sRKaQB_Wa760bRPk_fb89_P4(Lorg/telegram/ui/ArticleViewer$BlockChannelCell;ILorg/telegram/tgnet/TLRPC$TL_channels_joinChannel;Lorg/telegram/tgnet/TLRPC$Chat;Lorg/telegram/tgnet/TLRPC$ChatInviteJoinResult;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 7

    if-eqz p5, :cond_0

    .line 6161
    new-instance p3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda67;

    invoke-direct {p3, p0, p1, p5, p2}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda67;-><init>(Lorg/telegram/ui/ArticleViewer$BlockChannelCell;ILorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/tgnet/TLRPC$TL_channels_joinChannel;)V

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 6169
    :cond_0
    instance-of p2, p4, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultOk;

    const/4 p5, 0x0

    const/4 v0, 0x1

    if-eqz p2, :cond_3

    .line 6170
    check-cast p4, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultOk;

    iget-object p2, p4, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultOk;->updates:Lorg/telegram/tgnet/TLRPC$Updates;

    move p4, p5

    .line 6171
    :goto_0
    iget-object v1, p2, Lorg/telegram/tgnet/TLRPC$Updates;->updates:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge p4, v1, :cond_2

    .line 6172
    iget-object v1, p2, Lorg/telegram/tgnet/TLRPC$Updates;->updates:Ljava/util/ArrayList;

    invoke-virtual {v1, p4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/tgnet/TLRPC$Update;

    .line 6173
    instance-of v2, v1, Lorg/telegram/tgnet/tl/TL_update$TL_updateNewChannelMessage;

    if-eqz v2, :cond_1

    .line 6174
    check-cast v1, Lorg/telegram/tgnet/tl/TL_update$TL_updateNewChannelMessage;

    iget-object v1, v1, Lorg/telegram/tgnet/tl/TL_update$TL_updateNewChannelMessage;->message:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$Message;->action:Lorg/telegram/tgnet/TLRPC$MessageAction;

    instance-of v1, v1, Lorg/telegram/tgnet/TLRPC$TL_messageActionChatAddUser;

    if-eqz v1, :cond_1

    move p4, v0

    goto :goto_1

    :cond_1
    add-int/lit8 p4, p4, 0x1

    goto :goto_0

    :cond_2
    move p4, p5

    .line 6180
    :goto_1
    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    invoke-virtual {v1, p2, p5}, Lorg/telegram/messenger/MessagesController;->processUpdates(Lorg/telegram/tgnet/TLRPC$Updates;Z)V

    move p5, p4

    goto :goto_2

    .line 6181
    :cond_3
    instance-of p2, p4, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultWebView;

    if-eqz p2, :cond_4

    .line 6182
    check-cast p4, Lorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultWebView;

    .line 6183
    new-instance p2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda68;

    invoke-direct {p2, p1, p4, p3}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda68;-><init>(ILorg/telegram/tgnet/TLRPC$TL_chatInviteJoinResultWebView;Lorg/telegram/tgnet/TLRPC$Chat;)V

    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    move p5, v0

    :cond_4
    :goto_2
    if-nez p5, :cond_5

    .line 6192
    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p2

    iget-wide p4, p3, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    invoke-virtual {p2, p4, p5, v0}, Lorg/telegram/messenger/MessagesController;->generateJoinMessage(JZ)V

    .line 6194
    :cond_5
    new-instance p2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda69;

    invoke-direct {p2, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda69;-><init>(Lorg/telegram/ui/ArticleViewer$BlockChannelCell;)V

    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 6195
    new-instance p0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda70;

    invoke-direct {p0, p1, p3}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda70;-><init>(ILorg/telegram/tgnet/TLRPC$Chat;)V

    const-wide/16 p4, 0x3e8

    invoke-static {p0, p4, p5}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 6196
    invoke-static {p1}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v0

    iget-wide v3, p3, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v1, v3

    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    const/4 v6, 0x0

    invoke-virtual/range {v0 .. v6}, Lorg/telegram/messenger/MessagesStorage;->updateDialogsWithDeletedMessages(JJLjava/util/ArrayList;Ljava/util/ArrayList;)V

    return-void
.end method

.method public static synthetic $r8$lambda$c115wOXW2vyXtcC8QVe1soi0X8A(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$19(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$eJoH4Go_P2YMBnrA8ksbtPHL2qE(Lorg/telegram/ui/ArticleViewer$Sheet;J)V
    .locals 2

    if-eqz p0, :cond_0

    const/4 v0, 0x1

    .line 5151
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    .line 5153
    :cond_0
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 5155
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    .line 5156
    const-string/jumbo v1, "user_id"

    invoke-virtual {v0, v1, p1, p2}, Landroid/os/BaseBundle;->putLong(Ljava/lang/String;J)V

    .line 5157
    new-instance p1, Lorg/telegram/ui/ChatActivity;

    invoke-direct {p1, v0}, Lorg/telegram/ui/ChatActivity;-><init>(Landroid/os/Bundle;)V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    :cond_1
    return-void
.end method

.method public static synthetic $r8$lambda$etba9kMpaU29M8-UAz-XcbBOgRs(Lorg/telegram/ui/ArticleViewer;Landroid/content/DialogInterface;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$showDialog$66(Landroid/content/DialogInterface;)V

    return-void
.end method

.method public static synthetic $r8$lambda$f1t7jRQL28c8BgvOibY2uoPr0wg(Landroid/view/View;Lorg/telegram/ui/ArticleViewer$DrawingText;)V
    .locals 1

    .line 3795
    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda54;

    invoke-direct {v0, p1, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda54;-><init>(Lorg/telegram/ui/ArticleViewer$DrawingText;Landroid/view/View;)V

    invoke-virtual {p0, v0}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public static synthetic $r8$lambda$fGSPV9IHSMvckY-iwtkwkhU7WA4(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/TLObject;ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/ArticleViewer;->lambda$open$53(Lorg/telegram/tgnet/TLObject;ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$lWTJQh2Et8vwZXpC0EAUgAccDpg(Lorg/telegram/ui/ArticleViewer;Landroid/app/Activity;Ljava/lang/Integer;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$39(Landroid/app/Activity;Ljava/lang/Integer;)V

    return-void
.end method

.method public static synthetic $r8$lambda$mWVAwKAXqqOxH02umC1hnLESVjA(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$new$68()V

    return-void
.end method

.method public static synthetic $r8$lambda$pvNB9NMrhvt9Jg5Pn1TIZafi_UM(Lorg/telegram/messenger/Utilities$Callback;Ljava/lang/Runnable;Ljava/lang/Boolean;Ljava/lang/Boolean;)V
    .locals 0

    .line 4912
    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_1

    .line 4913
    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_0

    sget-object p2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-interface {p0, p2}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    .line 4914
    :cond_0
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    :cond_1
    return-void
.end method

.method public static synthetic $r8$lambda$qINZc_YlqC4PucGeWrKkAmP_wXQ(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$35(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$q_-7GFU0rtEsI4dIXs6DriNrD24(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$43(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$qfmxxSOzhF3WMAziu--m3L4VZ28(Lorg/telegram/ui/ArticleViewer;IJLorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$17(IJLorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$rPVruKw-omVEjF07Lh_TBifSPf4(Lorg/telegram/ui/ArticleViewer;Landroid/view/KeyEvent;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$showPopup$3(Landroid/view/KeyEvent;)V

    return-void
.end method

.method public static synthetic $r8$lambda$sPMqcBlnW6H0S2eyXKpMfRHw4T0(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->lambda$open$55()V

    return-void
.end method

.method public static synthetic $r8$lambda$sozvfbYwel_rKPApn-7AqBEVlq0(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$44(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$t2-Va2L0bHoCB20nvz418GhiKTU(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$21(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$tm6Nu892jcSAwWeLEyMROp56UWo(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/tgnet/TLObject;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/ArticleViewer;->lambda$openWebpageUrlInternal$11(ILorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/tgnet/TLObject;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;)V

    return-void
.end method

.method public static synthetic $r8$lambda$u1pXPc9qq4vI26sB2cbPydNv3QE(Lorg/telegram/ui/ArticleViewer;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$checkScrollAnimated$52(Landroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static synthetic $r8$lambda$uh92QrckDDOQZkeElt5yQvemKDg(Lorg/telegram/ui/ArticleViewer$BlockChannelCell;ILorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/tgnet/TLRPC$TL_channels_joinChannel;)V
    .locals 1

    const/4 v0, 0x0

    .line 6162
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/ArticleViewer$BlockChannelCell;->setState(IZ)V

    .line 6163
    sget-object p0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const/4 v0, 0x0

    invoke-static {p1, p2, v0, p3, p0}, Lorg/telegram/ui/Components/AlertsCreator;->processError(ILorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/tgnet/TLObject;[Ljava/lang/Object;)Landroid/app/Dialog;

    return-void
.end method

.method public static synthetic $r8$lambda$uyUCZ8W12UTcQDqANA8QhMsLzVI(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)Z
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$32(Landroid/view/View;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$wvLryOZDsACKYshrLxdsS5lSLsE(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;I)Z
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$15(Landroid/view/View;I)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$xzBAUvRoPwkdP1Bw_gViZ2JP4ug(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->lambda$setParentActivity$40(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$y4pZLAj8ZxXueI7mQQ1oilqNUWU(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 0

    .line 5003
    const/4 p0, 0x1

    return p0
.end method

.method public static synthetic $r8$lambda$zGqM8RR8yykY_9OsQm5IgspI6KY(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/messenger/browser/Browser$Progress;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->lambda$openWebpageUrlInternal$13(ILorg/telegram/messenger/browser/Browser$Progress;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetactionBar(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/web/WebActionBar;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetactivityVisibilityController(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->activityVisibilityController:Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetaddressBarList(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/web/AddressBarList;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->addressBarList:Lorg/telegram/ui/web/AddressBarList;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetanchorsOffsetMeasuredWidth(Lorg/telegram/ui/ArticleViewer;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->anchorsOffsetMeasuredWidth:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetanimationEndRunnable(Lorg/telegram/ui/ArticleViewer;)Ljava/lang/Runnable;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->animationEndRunnable:Ljava/lang/Runnable;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetbackgroundPaint(Lorg/telegram/ui/ArticleViewer;)Landroid/graphics/Paint;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->backgroundPaint:Landroid/graphics/Paint;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcheckingForLongPress(Lorg/telegram/ui/ArticleViewer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/ArticleViewer;->checkingForLongPress:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcloseAnimationInProgress(Lorg/telegram/ui/ArticleViewer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/ArticleViewer;->closeAnimationInProgress:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcollapsed(Lorg/telegram/ui/ArticleViewer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/ArticleViewer;->collapsed:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcontainerView(Lorg/telegram/ui/ArticleViewer;)Landroid/widget/FrameLayout;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcreatedWebViews(Lorg/telegram/ui/ArticleViewer;)Ljava/util/ArrayList;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->createdWebViews:Ljava/util/ArrayList;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcurrentAccount(Lorg/telegram/ui/ArticleViewer;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcurrentHeaderHeight(Lorg/telegram/ui/ArticleViewer;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcurrentPlayingVideo(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/Components/WebPlayerView;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->currentPlayingVideo:Lorg/telegram/ui/Components/WebPlayerView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcustomView(Lorg/telegram/ui/ArticleViewer;)Landroid/view/View;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->customView:Landroid/view/View;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcustomViewCallback(Lorg/telegram/ui/ArticleViewer;)Landroid/webkit/WebChromeClient$CustomViewCallback;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->customViewCallback:Landroid/webkit/WebChromeClient$CustomViewCallback;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetfullscreenAspectRatioView(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/AspectRatioFrameLayout;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenAspectRatioView:Lorg/telegram/ui/AspectRatioFrameLayout;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetfullscreenTextureView(Lorg/telegram/ui/ArticleViewer;)Landroid/view/TextureView;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenTextureView:Landroid/view/TextureView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetfullscreenVideoContainer(Lorg/telegram/ui/ArticleViewer;)Landroid/widget/FrameLayout;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgethasCutout(Lorg/telegram/ui/ArticleViewer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/ArticleViewer;->hasCutout:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetisVisible(Lorg/telegram/ui/ArticleViewer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetkeyboardVisible(Lorg/telegram/ui/ArticleViewer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/ArticleViewer;->keyboardVisible:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetlastBlockNum(Lorg/telegram/ui/ArticleViewer;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->lastBlockNum:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetlastInsets(Lorg/telegram/ui/ArticleViewer;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->lastInsets:Ljava/lang/Object;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetnavigationBarPaint(Lorg/telegram/ui/ArticleViewer;)Landroid/graphics/Paint;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->navigationBarPaint:Landroid/graphics/Paint;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetnotificationsLocker(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/messenger/AnimationNotificationsLocker;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->notificationsLocker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpage0Background(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/Components/AnimatedColor;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->page0Background:Lorg/telegram/ui/Components/AnimatedColor;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpage1Background(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/Components/AnimatedColor;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->page1Background:Lorg/telegram/ui/Components/AnimatedColor;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpageSwitchAnimation(Lorg/telegram/ui/ArticleViewer;)Landroid/animation/AnimatorSet;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetparentActivity(Lorg/telegram/ui/ArticleViewer;)Landroid/app/Activity;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpendingCheckForLongPress(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/ArticleViewer$CheckForLongPress;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForLongPress:Lorg/telegram/ui/ArticleViewer$CheckForLongPress;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpressCount(Lorg/telegram/ui/ArticleViewer;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->pressCount:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetprogressView(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/Components/ContextProgressView;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->progressView:Lorg/telegram/ui/Components/ContextProgressView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetprogressViewAnimation(Lorg/telegram/ui/ArticleViewer;)Landroid/animation/AnimatorSet;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetrunAfterKeyboardClose(Lorg/telegram/ui/ArticleViewer;)Landroid/animation/AnimatorSet;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->runAfterKeyboardClose:Landroid/animation/AnimatorSet;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetscrimPaint(Lorg/telegram/ui/ArticleViewer;)Landroid/graphics/Paint;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->scrimPaint:Landroid/graphics/Paint;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetsearchPanel(Lorg/telegram/ui/ArticleViewer;)Landroid/widget/FrameLayout;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetsearchPanelAlpha(Lorg/telegram/ui/ArticleViewer;)F
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAlpha:F

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetsearchPanelTranslation(Lorg/telegram/ui/ArticleViewer;)F
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelTranslation:F

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetstatusBarPaint(Lorg/telegram/ui/ArticleViewer;)Landroid/graphics/Paint;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->statusBarPaint:Landroid/graphics/Paint;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetwindowView(Lorg/telegram/ui/ArticleViewer;)Lorg/telegram/ui/ArticleViewer$WindowView;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fputanchorsOffsetMeasuredWidth(Lorg/telegram/ui/ArticleViewer;I)V
    .locals 0

    .line 0
    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->anchorsOffsetMeasuredWidth:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputanimationEndRunnable(Lorg/telegram/ui/ArticleViewer;Ljava/lang/Runnable;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->animationEndRunnable:Ljava/lang/Runnable;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputattachedToWindow(Lorg/telegram/ui/ArticleViewer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/ArticleViewer;->attachedToWindow:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcheckingForLongPress(Lorg/telegram/ui/ArticleViewer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/ArticleViewer;->checkingForLongPress:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcloseAnimationInProgress(Lorg/telegram/ui/ArticleViewer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/ArticleViewer;->closeAnimationInProgress:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcurrentPlayingVideo(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/Components/WebPlayerView;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->currentPlayingVideo:Lorg/telegram/ui/Components/WebPlayerView;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcustomView(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->customView:Landroid/view/View;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcustomViewCallback(Lorg/telegram/ui/ArticleViewer;Landroid/webkit/WebChromeClient$CustomViewCallback;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->customViewCallback:Landroid/webkit/WebChromeClient$CustomViewCallback;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputfullscreenedVideo(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/Components/WebPlayerView;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenedVideo:Lorg/telegram/ui/Components/WebPlayerView;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputhasCutout(Lorg/telegram/ui/ArticleViewer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/ArticleViewer;->hasCutout:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputkeyboardVisible(Lorg/telegram/ui/ArticleViewer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/ArticleViewer;->keyboardVisible:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputlastBlockNum(Lorg/telegram/ui/ArticleViewer;I)V
    .locals 0

    .line 0
    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->lastBlockNum:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputlastInsets(Lorg/telegram/ui/ArticleViewer;Ljava/lang/Object;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->lastInsets:Ljava/lang/Object;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpageSwitchAnimation(Lorg/telegram/ui/ArticleViewer;Landroid/animation/AnimatorSet;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpendingCheckForLongPress(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ArticleViewer$CheckForLongPress;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForLongPress:Lorg/telegram/ui/ArticleViewer$CheckForLongPress;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpressCount(Lorg/telegram/ui/ArticleViewer;I)V
    .locals 0

    .line 0
    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->pressCount:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputprogressViewAnimation(Lorg/telegram/ui/ArticleViewer;Landroid/animation/AnimatorSet;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputrunAfterKeyboardClose(Lorg/telegram/ui/ArticleViewer;Landroid/animation/AnimatorSet;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->runAfterKeyboardClose:Landroid/animation/AnimatorSet;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputsearchPanelAlpha(Lorg/telegram/ui/ArticleViewer;F)V
    .locals 0

    .line 0
    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAlpha:F

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcheckLayoutForLinks(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/MotionEvent;Landroid/view/View;Lorg/telegram/ui/ArticleViewer$DrawingText;II)Z
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/ArticleViewer;->checkLayoutForLinks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/MotionEvent;Landroid/view/View;Lorg/telegram/ui/ArticleViewer$DrawingText;II)Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mcheckScroll(Lorg/telegram/ui/ArticleViewer;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->checkScroll(I)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcheckVideoPlayer(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->checkVideoPlayer()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcreateLayoutForText(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p8}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mcreateLayoutForText(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p7}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mdrawTextSelection(Lorg/telegram/ui/ArticleViewer;Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->drawTextSelection(Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;I)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mfixListBlock(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->fixListBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mgetBlockCaption(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->getBlockCaption(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mgetChromeFallbackColor(Lorg/telegram/ui/ArticleViewer;)I
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mgetLastNonListPageBlock(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mgetText(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mgoBack(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->goBack()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mmakeProgress(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/Components/LinkSpanDrawable;Lorg/telegram/ui/ArticleViewer$DrawingText;)Lorg/telegram/messenger/browser/Browser$Progress;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->makeProgress(Lorg/telegram/ui/Components/LinkSpanDrawable;Lorg/telegram/ui/ArticleViewer$DrawingText;)Lorg/telegram/messenger/browser/Browser$Progress;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$monClosed(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->onClosed()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mprocessSearch(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->processSearch(Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$msaveCurrentPagePosition(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->saveCurrentPagePosition()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$msetCurrentHeaderHeight(Lorg/telegram/ui/ArticleViewer;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->setCurrentHeaderHeight(I)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mshowCopyPopup(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->showCopyPopup(Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mshowLineProgressView(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->showLineProgressView()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mshowPopup(Lorg/telegram/ui/ArticleViewer;Landroid/view/View;III)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/ArticleViewer;->showPopup(Landroid/view/View;III)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mupdateInterfaceForCurrentPage(Lorg/telegram/ui/ArticleViewer;Ljava/lang/Object;ZI)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mupdatePaintSize(Lorg/telegram/ui/ArticleViewer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->updatePaintSize()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mwrapInTableBlock(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->wrapInTableBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$sfgetaudioTimePaint()Landroid/text/TextPaint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->audioTimePaint:Landroid/text/TextPaint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetdividerPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->dividerPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetdotsPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->dotsPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetlistTextNumPaint()Landroid/text/TextPaint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->listTextNumPaint:Landroid/text/TextPaint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetphotoBackgroundPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->photoBackgroundPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetpreformattedBackgroundPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->preformattedBackgroundPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetquoteLinePaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->quoteLinePaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgeturlPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->urlPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetwebpageMarkPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->webpageMarkPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetwebpageSearchPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->webpageSearchPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfgetwebpageUrlPaint()Landroid/graphics/Paint;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->webpageUrlPaint:Landroid/graphics/Paint;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$sfputdotsPaint(Landroid/graphics/Paint;)V
    .locals 0

    .line 0
    sput-object p0, Lorg/telegram/ui/ArticleViewer;->dotsPaint:Landroid/graphics/Paint;

    return-void
.end method

.method public static bridge synthetic -$$Nest$smcreateLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 0

    .line 0
    invoke-static/range {p0 .. p8}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$smisInsideGestureExclusionEdge(Landroid/view/MotionEvent;Landroid/view/View;)Z
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/telegram/ui/ArticleViewer;->isInsideGestureExclusionEdge(Landroid/view/MotionEvent;Landroid/view/View;)Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$smremovePressedLink(Lorg/telegram/ui/IArticleViewer;)V
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->removePressedLink(Lorg/telegram/ui/IArticleViewer;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    .line 245
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    sput-object v0, Lorg/telegram/ui/ArticleViewer;->activeSheets:Ljava/util/HashSet;

    const/4 v0, 0x0

    .line 392
    sput-object v0, Lorg/telegram/ui/ArticleViewer;->Instance:Lorg/telegram/ui/ArticleViewer;

    .line 922
    new-instance v0, Lorg/telegram/ui/ArticleViewer$2;

    const-string/jumbo v1, "innerTranslationX"

    invoke-direct {v0, v1}, Lorg/telegram/ui/ArticleViewer$2;-><init>(Ljava/lang/String;)V

    sput-object v0, Lorg/telegram/ui/ArticleViewer;->ARTICLE_VIEWER_INNER_TRANSLATION_X:Landroid/util/Property;

    .line 2613
    new-instance v0, Landroid/text/TextPaint;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v0, Lorg/telegram/ui/ArticleViewer;->audioTimePaint:Landroid/text/TextPaint;

    .line 2614
    new-instance v0, Lorg/telegram/ui/ArticleViewer$Resources;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/telegram/ui/ArticleViewer$Resources;-><init>(Z)V

    sput-object v0, Lorg/telegram/ui/ArticleViewer;->resources:Lorg/telegram/ui/ArticleViewer$Resources;

    .line 3456
    new-instance v0, Ljava/util/WeakHashMap;

    invoke-direct {v0}, Ljava/util/WeakHashMap;-><init>()V

    sput-object v0, Lorg/telegram/ui/ArticleViewer;->liveDrawingTexts:Ljava/util/WeakHashMap;

    return-void
.end method

.method public constructor <init>()V
    .locals 7

    .line 253
    invoke-direct {p0}, Lorg/telegram/ui/IArticleViewer;-><init>()V

    .line 266
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->createdWebViews:Ljava/util/ArrayList;

    const/4 v0, 0x1

    .line 286
    iput v0, p0, Lorg/telegram/ui/ArticleViewer;->lastBlockNum:I

    .line 291
    new-instance v0, Landroid/view/animation/DecelerateInterpolator;

    const/high16 v1, 0x3fc00000    # 1.5f

    invoke-direct {v0, v1}, Landroid/view/animation/DecelerateInterpolator;-><init>(F)V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->interpolator:Landroid/view/animation/DecelerateInterpolator;

    .line 293
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    .line 349
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->headerPaint:Landroid/graphics/Paint;

    .line 350
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->statusBarPaint:Landroid/graphics/Paint;

    .line 351
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->navigationBarPaint:Landroid/graphics/Paint;

    .line 352
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->headerProgressPaint:Landroid/graphics/Paint;

    const/4 v0, 0x0

    .line 368
    iput-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->checkingForLongPress:Z

    const/4 v1, 0x0

    .line 369
    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForLongPress:Lorg/telegram/ui/ArticleViewer$CheckForLongPress;

    .line 370
    iput v0, p0, Lorg/telegram/ui/ArticleViewer;->pressCount:I

    .line 371
    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

    .line 382
    new-instance v2, Lorg/telegram/messenger/AnimationNotificationsLocker;

    sget v3, Lorg/telegram/messenger/NotificationCenter;->dialogsNeedReload:I

    sget v4, Lorg/telegram/messenger/NotificationCenter;->closeChats:I

    filled-new-array {v3, v4}, [I

    move-result-object v3

    invoke-direct {v2, v3}, Lorg/telegram/messenger/AnimationNotificationsLocker;-><init>([I)V

    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->notificationsLocker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    .line 387
    const-string v2, "bottomSheet"

    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->BOTTOM_SHEET_VIEW_TAG:Ljava/lang/String;

    const/4 v2, 0x2

    .line 416
    new-array v2, v2, [Lorg/telegram/ui/ArticleViewer$FontCell;

    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    const/4 v2, -0x1

    .line 5299
    iput v2, p0, Lorg/telegram/ui/ArticleViewer;->lastSearchIndex:I

    .line 14918
    new-instance v2, Lorg/telegram/ui/Components/AnimatedColor;

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda0;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    sget-object v4, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const-wide/16 v5, 0x140

    invoke-direct {v2, v3, v5, v6, v4}, Lorg/telegram/ui/Components/AnimatedColor;-><init>(Ljava/lang/Runnable;JLandroid/animation/TimeInterpolator;)V

    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->page0Background:Lorg/telegram/ui/Components/AnimatedColor;

    .line 14919
    new-instance v2, Lorg/telegram/ui/Components/AnimatedColor;

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda1;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-direct {v2, v3, v5, v6, v4}, Lorg/telegram/ui/Components/AnimatedColor;-><init>(Ljava/lang/Runnable;JLandroid/animation/TimeInterpolator;)V

    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->page1Background:Lorg/telegram/ui/Components/AnimatedColor;

    .line 254
    iput-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->isSheet:Z

    .line 255
    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 6

    .line 258
    invoke-direct {p0}, Lorg/telegram/ui/IArticleViewer;-><init>()V

    .line 266
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->createdWebViews:Ljava/util/ArrayList;

    const/4 v0, 0x1

    .line 286
    iput v0, p0, Lorg/telegram/ui/ArticleViewer;->lastBlockNum:I

    .line 291
    new-instance v1, Landroid/view/animation/DecelerateInterpolator;

    const/high16 v2, 0x3fc00000    # 1.5f

    invoke-direct {v1, v2}, Landroid/view/animation/DecelerateInterpolator;-><init>(F)V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->interpolator:Landroid/view/animation/DecelerateInterpolator;

    .line 293
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    .line 349
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->headerPaint:Landroid/graphics/Paint;

    .line 350
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->statusBarPaint:Landroid/graphics/Paint;

    .line 351
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->navigationBarPaint:Landroid/graphics/Paint;

    .line 352
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->headerProgressPaint:Landroid/graphics/Paint;

    const/4 v1, 0x0

    .line 368
    iput-boolean v1, p0, Lorg/telegram/ui/ArticleViewer;->checkingForLongPress:Z

    const/4 v2, 0x0

    .line 369
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForLongPress:Lorg/telegram/ui/ArticleViewer$CheckForLongPress;

    .line 370
    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->pressCount:I

    .line 371
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

    .line 382
    new-instance v1, Lorg/telegram/messenger/AnimationNotificationsLocker;

    sget v2, Lorg/telegram/messenger/NotificationCenter;->dialogsNeedReload:I

    sget v3, Lorg/telegram/messenger/NotificationCenter;->closeChats:I

    filled-new-array {v2, v3}, [I

    move-result-object v2

    invoke-direct {v1, v2}, Lorg/telegram/messenger/AnimationNotificationsLocker;-><init>([I)V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->notificationsLocker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    .line 387
    const-string v1, "bottomSheet"

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->BOTTOM_SHEET_VIEW_TAG:Ljava/lang/String;

    const/4 v1, 0x2

    .line 416
    new-array v1, v1, [Lorg/telegram/ui/ArticleViewer$FontCell;

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    const/4 v1, -0x1

    .line 5299
    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->lastSearchIndex:I

    .line 14918
    new-instance v1, Lorg/telegram/ui/Components/AnimatedColor;

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    sget-object v3, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const-wide/16 v4, 0x140

    invoke-direct {v1, v2, v4, v5, v3}, Lorg/telegram/ui/Components/AnimatedColor;-><init>(Ljava/lang/Runnable;JLandroid/animation/TimeInterpolator;)V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->page0Background:Lorg/telegram/ui/Components/AnimatedColor;

    .line 14919
    new-instance v1, Lorg/telegram/ui/Components/AnimatedColor;

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda1;

    invoke-direct {v2, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-direct {v1, v2, v4, v5, v3}, Lorg/telegram/ui/Components/AnimatedColor;-><init>(Ljava/lang/Runnable;JLandroid/animation/TimeInterpolator;)V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->page1Background:Lorg/telegram/ui/Components/AnimatedColor;

    .line 259
    iput-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->isSheet:Z

    .line 260
    new-instance v0, Lorg/telegram/ui/ArticleViewer$Sheet;

    invoke-direct {v0, p0, p1}, Lorg/telegram/ui/ArticleViewer$Sheet;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    .line 261
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {p0, v0, p1}, Lorg/telegram/ui/ArticleViewer;->setParentActivity(Landroid/app/Activity;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-void
.end method

.method public static addBookmark(Ljava/lang/String;ILandroid/widget/FrameLayout;Lorg/telegram/ui/ArticleViewer$Sheet;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 6

    .line 5135
    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->magic2tonsite(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 5136
    invoke-static {p1}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v0

    .line 5137
    invoke-static {p1}, Lorg/telegram/messenger/SendMessagesHelper;->getInstance(I)Lorg/telegram/messenger/SendMessagesHelper;

    move-result-object v2

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;->of(Ljava/lang/String;J)Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;

    move-result-object v3

    invoke-virtual {v2, v3}, Lorg/telegram/messenger/SendMessagesHelper;->sendMessage(Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;)V

    .line 5138
    new-instance v2, Lorg/telegram/tgnet/TLRPC$TL_message;

    invoke-direct {v2}, Lorg/telegram/tgnet/TLRPC$TL_message;-><init>()V

    .line 5139
    new-instance v3, Lorg/telegram/tgnet/TLRPC$TL_peerUser;

    invoke-direct {v3}, Lorg/telegram/tgnet/TLRPC$TL_peerUser;-><init>()V

    iput-object v3, v2, Lorg/telegram/tgnet/TLRPC$Message;->peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 5140
    iput-wide v0, v3, Lorg/telegram/tgnet/TLRPC$Peer;->user_id:J

    .line 5141
    new-instance v3, Lorg/telegram/tgnet/TLRPC$TL_peerUser;

    invoke-direct {v3}, Lorg/telegram/tgnet/TLRPC$TL_peerUser;-><init>()V

    iput-object v3, v2, Lorg/telegram/tgnet/TLRPC$Message;->from_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 5142
    iput-wide v0, v3, Lorg/telegram/tgnet/TLRPC$Peer;->user_id:J

    .line 5143
    iput-object p0, v2, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    .line 5144
    new-instance v3, Lorg/telegram/tgnet/TLRPC$TL_messageMediaWebPage;

    invoke-direct {v3}, Lorg/telegram/tgnet/TLRPC$TL_messageMediaWebPage;-><init>()V

    iput-object v3, v2, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    .line 5145
    new-instance v4, Lorg/telegram/tgnet/TLRPC$TL_webPage;

    invoke-direct {v4}, Lorg/telegram/tgnet/TLRPC$TL_webPage;-><init>()V

    iput-object v4, v3, Lorg/telegram/tgnet/TLRPC$MessageMedia;->webpage:Lorg/telegram/tgnet/TLRPC$WebPage;

    .line 5146
    iget-object v3, v2, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    iget-object v3, v3, Lorg/telegram/tgnet/TLRPC$MessageMedia;->webpage:Lorg/telegram/tgnet/TLRPC$WebPage;

    iput-object p0, v3, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    .line 5147
    iput-object p0, v3, Lorg/telegram/tgnet/TLRPC$WebPage;->display_url:Ljava/lang/String;

    .line 5148
    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v3, Lorg/telegram/messenger/NotificationCenter;->bookmarkAdded:I

    new-instance v4, Lorg/telegram/messenger/MessageObject;

    const/4 v5, 0x0

    invoke-direct {v4, p1, v2, v5, v5}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;ZZ)V

    filled-new-array {v4}, [Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0, v3, p1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 5149
    invoke-static {p2, p4}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$raw;->saved_messages:I

    sget p2, Lorg/telegram/messenger/R$string;->WebBookmarkedToast:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    new-instance p4, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda29;

    invoke-direct {p4, p3, v0, v1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda29;-><init>(Lorg/telegram/ui/ArticleViewer$Sheet;J)V

    invoke-static {p2, p4}, Lorg/telegram/messenger/AndroidUtilities;->replaceSingleTag(Ljava/lang/String;Ljava/lang/Runnable;)Landroid/text/SpannableStringBuilder;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    const/4 p1, 0x1

    .line 5159
    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/Bulletin;->show(Z)Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private addPageToStack(Ljava/lang/String;I)Z
    .locals 3

    .line 2068
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->saveCurrentPagePosition()V

    .line 2069
    new-instance v0, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    invoke-direct {v0, p0, p1}, Lorg/telegram/ui/ArticleViewer$CachedWeb;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V

    .line 2070
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 2071
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {p1, v2, v1}, Lorg/telegram/ui/web/WebActionBar;->showSearch(ZZ)V

    .line 2072
    invoke-direct {p0, v0, v2, p2}, Lorg/telegram/ui/ArticleViewer;->updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V

    return v2
.end method

.method private addPageToStack(Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;I)Z
    .locals 3

    .line 2060
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->saveCurrentPagePosition()V

    .line 2061
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 2062
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/web/WebActionBar;->showSearch(ZZ)V

    .line 2063
    invoke-direct {p0, p1, v2, p3}, Lorg/telegram/ui/ArticleViewer;->updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V

    .line 2064
    invoke-virtual {p0, p2, v2}, Lorg/telegram/ui/ArticleViewer;->scrollToAnchor(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public static appendA11yLabel(Ljava/lang/CharSequence;I)Ljava/lang/CharSequence;
    .locals 1

    .line 777
    new-instance v0, Landroid/text/SpannableStringBuilder;

    invoke-direct {v0}, Landroid/text/SpannableStringBuilder;-><init>()V

    if-eqz p0, :cond_0

    .line 778
    invoke-virtual {v0, p0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 779
    :cond_0
    invoke-virtual {v0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result p0

    if-lez p0, :cond_1

    const-string p0, ", "

    invoke-virtual {v0, p0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 780
    :cond_1
    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    return-object v0
.end method

.method public static buildAccessibilityText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/ui/ArticleViewer$DrawingText;)Ljava/lang/CharSequence;
    .locals 8

    if-eqz p2, :cond_8

    .line 708
    iget-object v0, p2, Lorg/telegram/ui/ArticleViewer$DrawingText;->textLayout:Landroid/text/StaticLayout;

    if-nez v0, :cond_0

    goto :goto_3

    .line 709
    :cond_0
    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$DrawingText;->-$$Nest$fgetaccessibilityText(Lorg/telegram/ui/ArticleViewer$DrawingText;)Ljava/lang/CharSequence;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$DrawingText;->-$$Nest$fgetaccessibilityText(Lorg/telegram/ui/ArticleViewer$DrawingText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 710
    :cond_1
    iget-object v0, p2, Lorg/telegram/ui/ArticleViewer$DrawingText;->textLayout:Landroid/text/StaticLayout;

    invoke-virtual {v0}, Landroid/text/Layout;->getText()Ljava/lang/CharSequence;

    move-result-object v0

    .line 711
    instance-of v1, v0, Landroid/text/Spannable;

    if-nez v1, :cond_2

    return-object v0

    .line 714
    :cond_2
    move-object v1, v0

    check-cast v1, Landroid/text/Spannable;

    .line 715
    invoke-interface {v1}, Ljava/lang/CharSequence;->length()I

    move-result v2

    const-class v3, Lorg/telegram/ui/Components/TextPaintUrlSpan;

    const/4 v4, 0x0

    invoke-interface {v1, v4, v2, v3}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [Lorg/telegram/ui/Components/TextPaintUrlSpan;

    if-eqz v2, :cond_7

    .line 716
    array-length v3, v2

    if-nez v3, :cond_3

    goto :goto_2

    .line 719
    :cond_3
    new-instance v0, Landroid/text/SpannableStringBuilder;

    invoke-direct {v0, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 720
    array-length v1, v2

    :goto_0
    if-ge v4, v1, :cond_6

    aget-object v3, v2, v4

    .line 721
    invoke-virtual {v0, v3}, Landroid/text/SpannableStringBuilder;->getSpanStart(Ljava/lang/Object;)I

    move-result v5

    .line 722
    invoke-virtual {v0, v3}, Landroid/text/SpannableStringBuilder;->getSpanEnd(Ljava/lang/Object;)I

    move-result v6

    if-ltz v5, :cond_5

    if-gt v6, v5, :cond_4

    goto :goto_1

    .line 725
    :cond_4
    new-instance v7, Lorg/telegram/ui/ArticleViewer$1;

    invoke-direct {v7, p0, p1, v3}, Lorg/telegram/ui/ArticleViewer$1;-><init>(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/ui/Components/TextPaintUrlSpan;)V

    const/16 v3, 0x21

    invoke-virtual {v0, v7, v5, v6, v3}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_5
    :goto_1
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 732
    :cond_6
    invoke-static {p2, v0}, Lorg/telegram/ui/ArticleViewer$DrawingText;->-$$Nest$fputaccessibilityText(Lorg/telegram/ui/ArticleViewer$DrawingText;Ljava/lang/CharSequence;)V

    :cond_7
    :goto_2
    return-object v0

    :cond_8
    :goto_3
    const/4 p0, 0x0

    return-object p0
.end method

.method private checkAnimation()Z
    .locals 6

    .line 6201
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->animationInProgress:I

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 6202
    iget-wide v2, p0, Lorg/telegram/ui/ArticleViewer;->transitionAnimationStartTime:J

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    sub-long/2addr v2, v4

    invoke-static {v2, v3}, Ljava/lang/Math;->abs(J)J

    move-result-wide v2

    const-wide/16 v4, 0x1f4

    cmp-long v0, v2, v4

    if-ltz v0, :cond_1

    .line 6203
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->animationEndRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 6204
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    const/4 v0, 0x0

    .line 6205
    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->animationEndRunnable:Ljava/lang/Runnable;

    .line 6207
    :cond_0
    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->animationInProgress:I

    .line 6210
    :cond_1
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->animationInProgress:I

    if-eqz p0, :cond_2

    const/4 p0, 0x1

    return p0

    :cond_2
    return v1
.end method

.method private checkLayoutForLinks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/MotionEvent;Landroid/view/View;Lorg/telegram/ui/ArticleViewer$DrawingText;II)Z
    .locals 0

    .line 3702
    invoke-static/range {p0 .. p6}, Lorg/telegram/ui/ArticleViewer;->checkLayoutForLinks(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/MotionEvent;Landroid/view/View;Lorg/telegram/ui/ArticleViewer$DrawingText;II)Z

    move-result p0

    return p0
.end method

.method public static checkLayoutForLinks(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/MotionEvent;Landroid/view/View;Lorg/telegram/ui/ArticleViewer$DrawingText;II)Z
    .locals 18

    move-object/from16 v1, p0

    move-object/from16 v2, p3

    move-object/from16 v3, p4

    move/from16 v0, p5

    move/from16 v4, p6

    .line 3705
    invoke-virtual {v1}, Lorg/telegram/ui/IArticleViewer;->allowTouches()Z

    move-result v5

    const/4 v6, 0x0

    if-eqz v5, :cond_0

    if-nez v2, :cond_1

    :cond_0
    move/from16 v17, v6

    goto/16 :goto_11

    .line 3708
    :cond_1
    invoke-virtual {v1, v2}, Lorg/telegram/ui/IArticleViewer;->getTextSelectionHelper(Landroid/view/View;)Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    move-result-object v5

    if-eqz v5, :cond_2

    .line 3709
    invoke-virtual {v5, v2}, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;->isSelectable(Landroid/view/View;)Z

    move-result v5

    if-nez v5, :cond_2

    return v6

    .line 3712
    :cond_2
    iput-object v2, v1, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerView:Landroid/view/View;

    if-eqz v3, :cond_14

    .line 3714
    iget-object v7, v3, Lorg/telegram/ui/ArticleViewer$DrawingText;->textLayout:Landroid/text/StaticLayout;

    .line 3715
    invoke-virtual/range {p2 .. p2}, Landroid/view/MotionEvent;->getX()F

    move-result v8

    float-to-int v8, v8

    .line 3716
    invoke-virtual/range {p2 .. p2}, Landroid/view/MotionEvent;->getY()F

    move-result v9

    float-to-int v9, v9

    .line 3718
    invoke-virtual/range {p2 .. p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v10

    if-nez v10, :cond_10

    .line 3721
    invoke-virtual {v7}, Landroid/text/StaticLayout;->getLineCount()I

    move-result v10

    const/high16 v12, 0x4f000000

    move v13, v6

    const/4 v14, 0x0

    :goto_0
    if-ge v13, v10, :cond_3

    .line 3722
    invoke-virtual {v7, v13}, Landroid/text/Layout;->getLineWidth(I)F

    move-result v15

    invoke-static {v15, v14}, Ljava/lang/Math;->max(FF)F

    move-result v14

    .line 3723
    invoke-virtual {v7, v13}, Landroid/text/Layout;->getLineLeft(I)F

    move-result v15

    invoke-static {v15, v12}, Ljava/lang/Math;->min(FF)F

    move-result v12

    add-int/lit8 v13, v13, 0x1

    goto :goto_0

    :cond_3
    int-to-float v10, v8

    int-to-float v13, v0

    add-float/2addr v13, v12

    cmpl-float v12, v10, v13

    if-ltz v12, :cond_14

    add-float/2addr v13, v14

    cmpg-float v12, v10, v13

    if-gtz v12, :cond_14

    if-lt v9, v4, :cond_14

    .line 3725
    invoke-virtual {v7}, Landroid/text/Layout;->getHeight()I

    move-result v12

    add-int/2addr v12, v4

    if-gt v9, v12, :cond_14

    .line 3726
    iput-object v3, v1, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    .line 3727
    iput v4, v1, Lorg/telegram/ui/IArticleViewer;->pressedLayoutY:I

    .line 3728
    invoke-virtual {v7}, Landroid/text/Layout;->getText()Ljava/lang/CharSequence;

    move-result-object v12

    .line 3729
    instance-of v12, v12, Landroid/text/Spannable;

    if-eqz v12, :cond_14

    sub-int/2addr v8, v0

    sub-int v4, v9, v4

    .line 3733
    :try_start_0
    invoke-virtual {v7, v4}, Landroid/text/StaticLayout;->getLineForVertical(I)I

    move-result v0

    int-to-float v8, v8

    .line 3734
    invoke-virtual {v7, v0, v8}, Landroid/text/Layout;->getOffsetForHorizontal(IF)I

    move-result v12

    .line 3735
    invoke-virtual {v7, v0}, Landroid/text/Layout;->getLineLeft(I)F

    move-result v13

    cmpg-float v14, v13, v8

    if-gtz v14, :cond_14

    .line 3736
    invoke-virtual {v7, v0}, Landroid/text/Layout;->getLineWidth(I)F

    move-result v0

    add-float/2addr v13, v0

    cmpl-float v0, v13, v8

    if-ltz v0, :cond_14

    .line 3737
    invoke-virtual {v7}, Landroid/text/Layout;->getText()Ljava/lang/CharSequence;

    move-result-object v0

    move-object v13, v0

    check-cast v13, Landroid/text/Spannable;

    .line 3738
    const-class v0, Lorg/telegram/ui/Components/TextPaintUrlSpan;

    invoke-interface {v13, v12, v12, v0}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/ui/Components/TextPaintUrlSpan;

    if-eqz v0, :cond_c

    .line 3739
    array-length v14, v0

    if-lez v14, :cond_c

    .line 3740
    aget-object v14, v0, v6

    .line 3741
    invoke-interface {v13, v14}, Landroid/text/Spanned;->getSpanStart(Ljava/lang/Object;)I

    move-result v15

    .line 3742
    invoke-interface {v13, v14}, Landroid/text/Spanned;->getSpanEnd(Ljava/lang/Object;)I

    move-result v16

    move/from16 v5, v16

    const/4 v6, 0x1

    .line 3743
    :goto_1
    array-length v11, v0

    if-ge v6, v11, :cond_6

    .line 3744
    aget-object v11, v0, v6

    move-object/from16 p5, v0

    .line 3745
    invoke-interface {v13, v11}, Landroid/text/Spanned;->getSpanStart(Ljava/lang/Object;)I

    move-result v0

    move/from16 p6, v6

    .line 3746
    invoke-interface {v13, v11}, Landroid/text/Spanned;->getSpanEnd(Ljava/lang/Object;)I

    move-result v6

    if-gt v15, v0, :cond_4

    if-le v6, v5, :cond_5

    :cond_4
    move v15, v0

    move v5, v6

    move-object v14, v11

    :cond_5
    add-int/lit8 v6, p6, 0x1

    move-object/from16 v0, p5

    goto :goto_1

    :catch_0
    move-exception v0

    goto/16 :goto_a

    .line 3753
    :cond_6
    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    if-eqz v0, :cond_7

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LinkSpanDrawable;->getSpan()Landroid/text/style/CharacterStyle;

    move-result-object v0

    if-eq v0, v14, :cond_c

    .line 3754
    :cond_7
    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    if-eqz v0, :cond_8

    .line 3755
    iget-object v6, v1, Lorg/telegram/ui/IArticleViewer;->links:Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;

    invoke-virtual {v6, v0}, Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;->removeLink(Lorg/telegram/ui/Components/LinkSpanDrawable;)V

    .line 3757
    :cond_8
    new-instance v0, Lorg/telegram/ui/Components/LinkSpanDrawable;

    const/4 v6, 0x0

    int-to-float v9, v9

    invoke-direct {v0, v14, v6, v10, v9}, Lorg/telegram/ui/Components/LinkSpanDrawable;-><init>(Landroid/text/style/CharacterStyle;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FF)V

    iput-object v0, v1, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    .line 3758
    sget v6, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteLinkSelection:I

    invoke-virtual {v1, v6}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v6

    const v9, 0x33ffffff

    and-int/2addr v6, v9

    invoke-virtual {v0, v6}, Lorg/telegram/ui/Components/LinkSpanDrawable;->setColor(I)V

    .line 3759
    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->links:Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;

    iget-object v6, v1, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    iget-object v9, v1, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    invoke-virtual {v0, v6, v9}, Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;->addLink(Lorg/telegram/ui/Components/LinkSpanDrawable;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 3761
    :try_start_1
    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LinkSpanDrawable;->obtainNewPath()Lorg/telegram/ui/Components/LinkPath;

    move-result-object v0

    const/4 v6, 0x0

    .line 3762
    invoke-virtual {v0, v7, v15, v6}, Lorg/telegram/ui/Components/LinkPath;->setCurrentLayout(Landroid/text/Layout;IF)V

    .line 3763
    invoke-virtual {v14}, Lorg/telegram/ui/Components/TextPaintUrlSpan;->getTextPaint()Landroid/text/TextPaint;

    move-result-object v6

    if-eqz v6, :cond_9

    .line 3764
    iget v6, v6, Landroid/text/TextPaint;->baselineShift:I

    goto :goto_2

    :catch_1
    move-exception v0

    goto :goto_5

    :cond_9
    const/4 v6, 0x0

    :goto_2
    if-eqz v6, :cond_b

    if-lez v6, :cond_a

    const/high16 v9, 0x40a00000    # 5.0f

    goto :goto_3

    :cond_a
    const/high16 v9, -0x40000000    # -2.0f

    .line 3765
    :goto_3
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v9

    add-int/2addr v6, v9

    goto :goto_4

    :cond_b
    const/4 v6, 0x0

    :goto_4
    invoke-virtual {v0, v6}, Lorg/telegram/ui/Components/LinkPath;->setBaselineShift(I)V

    .line 3766
    invoke-virtual {v7, v15, v5, v0}, Landroid/text/Layout;->getSelectionPath(IILandroid/graphics/Path;)V

    .line 3767
    invoke-virtual {v2}, Landroid/view/View;->invalidate()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_6

    .line 3769
    :goto_5
    :try_start_2
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3773
    :cond_c
    :goto_6
    iget-object v0, v3, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilers:Ljava/util/List;

    if-eqz v0, :cond_14

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_14

    .line 3774
    const-class v0, Lorg/telegram/ui/Components/TextStyleSpan;

    invoke-interface {v13, v12, v12, v0}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/ui/Components/TextStyleSpan;

    if-eqz v0, :cond_14

    const/4 v5, 0x0

    .line 3777
    :goto_7
    array-length v6, v0

    if-ge v5, v6, :cond_14

    .line 3778
    aget-object v6, v0, v5

    invoke-virtual {v6}, Lorg/telegram/ui/Components/TextStyleSpan;->isSpoiler()Z

    move-result v6

    if-eqz v6, :cond_f

    .line 3785
    new-instance v9, Landroid/graphics/Path;

    invoke-direct {v9}, Landroid/graphics/Path;-><init>()V

    .line 3786
    iget-object v0, v3, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilers:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_8
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_d

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/Components/spoilers/SpoilerEffect;

    .line 3787
    invoke-virtual {v5}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v5

    .line 3788
    iget v6, v5, Landroid/graphics/Rect;->left:I

    int-to-float v10, v6

    iget v6, v5, Landroid/graphics/Rect;->top:I

    int-to-float v11, v6

    iget v6, v5, Landroid/graphics/Rect;->right:I

    int-to-float v12, v6

    iget v5, v5, Landroid/graphics/Rect;->bottom:I

    int-to-float v13, v5

    sget-object v14, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual/range {v9 .. v14}, Landroid/graphics/Path;->addRect(FFFFLandroid/graphics/Path$Direction;)V

    goto :goto_8

    .line 3790
    :cond_d
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    const/4 v5, 0x0

    .line 3791
    invoke-virtual {v9, v0, v5}, Landroid/graphics/Path;->computeBounds(Landroid/graphics/RectF;Z)V

    .line 3792
    invoke-virtual {v0}, Landroid/graphics/RectF;->width()F

    move-result v5

    invoke-virtual {v0}, Landroid/graphics/RectF;->width()F

    move-result v6

    mul-float/2addr v5, v6

    invoke-virtual {v0}, Landroid/graphics/RectF;->height()F

    move-result v6

    invoke-virtual {v0}, Landroid/graphics/RectF;->height()F

    move-result v0

    mul-float/2addr v6, v0

    add-float/2addr v5, v6

    float-to-double v5, v5

    invoke-static {v5, v6}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v5

    double-to-float v0, v5

    .line 3795
    iget-object v5, v3, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilers:Ljava/util/List;

    const/4 v6, 0x0

    invoke-interface {v5, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/Components/spoilers/SpoilerEffect;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda53;

    invoke-direct {v6, v2, v3}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda53;-><init>(Landroid/view/View;Lorg/telegram/ui/ArticleViewer$DrawingText;)V

    invoke-virtual {v5, v6}, Lorg/telegram/ui/Components/spoilers/SpoilerEffect;->setOnRippleEndCallback(Ljava/lang/Runnable;)V

    .line 3800
    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilers:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_9
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_e

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/Components/spoilers/SpoilerEffect;

    int-to-float v6, v4

    .line 3801
    invoke-virtual {v5, v8, v6, v0}, Lorg/telegram/ui/Components/spoilers/SpoilerEffect;->startRipple(FFF)V

    goto :goto_9

    .line 3803
    :cond_e
    invoke-virtual {v2}, Landroid/view/View;->invalidate()V

    .line 3804
    invoke-virtual {v2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_14

    .line 3805
    invoke-virtual {v2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    const/4 v3, 0x1

    invoke-interface {v0, v3}, Landroid/view/ViewParent;->requestDisallowInterceptTouchEvent(Z)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_d

    :cond_f
    add-int/lit8 v5, v5, 0x1

    goto/16 :goto_7

    .line 3811
    :goto_a
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto :goto_d

    .line 3815
    :cond_10
    invoke-virtual/range {p2 .. p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const/4 v3, 0x1

    if-ne v0, v3, :cond_11

    .line 3816
    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    if-eqz v0, :cond_13

    .line 3818
    invoke-virtual {v0}, Lorg/telegram/ui/Components/LinkSpanDrawable;->getSpan()Landroid/text/style/CharacterStyle;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/Components/TextPaintUrlSpan;

    move-object/from16 v4, p1

    invoke-virtual {v1, v4, v0}, Lorg/telegram/ui/IArticleViewer;->handleLinkClick(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/ui/Components/TextPaintUrlSpan;)V

    goto :goto_b

    .line 3820
    :cond_11
    invoke-virtual/range {p2 .. p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const/4 v4, 0x3

    if-ne v0, v4, :cond_13

    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    if-eqz v0, :cond_12

    invoke-virtual {v0}, Landroid/widget/PopupWindow;->isShowing()Z

    move-result v0

    if-nez v0, :cond_13

    .line 3824
    :cond_12
    :goto_b
    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer;->removePressedLink(Lorg/telegram/ui/IArticleViewer;)V

    :cond_13
    :goto_c
    move-object/from16 v4, p2

    goto :goto_e

    :cond_14
    :goto_d
    const/4 v3, 0x1

    goto :goto_c

    .line 3827
    :goto_e
    invoke-virtual {v1, v4, v2}, Lorg/telegram/ui/IArticleViewer;->checkLayoutForLinks(Landroid/view/MotionEvent;Landroid/view/View;)V

    .line 3828
    instance-of v0, v2, Lorg/telegram/ui/ArticleViewer$BlockDetailsCell;

    if-eqz v0, :cond_16

    .line 3829
    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    if-eqz v0, :cond_15

    move v6, v3

    goto :goto_f

    :cond_15
    const/4 v6, 0x0

    :goto_f
    return v6

    .line 3831
    :cond_16
    iget-object v0, v1, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    if-eqz v0, :cond_17

    move v6, v3

    goto :goto_10

    :cond_17
    const/4 v6, 0x0

    :goto_10
    return v6

    :goto_11
    return v17
.end method

.method private checkScroll(I)V
    .locals 3

    .line 5540
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v0, :cond_0

    iget-boolean v1, v0, Lorg/telegram/ui/ArticleViewer$Sheet;->attachedToActionBar:Z

    if-nez v1, :cond_0

    return-void

    :cond_0
    if-eqz v0, :cond_1

    .line 5541
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetlocalFileBottomPaddingHeight(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)I

    move-result v0

    if-lez v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->findLastVisibleItemPosition()I

    move-result v0

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v2, v1

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->getItemCount()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    if-ne v0, v1, :cond_1

    const/high16 p1, 0x42600000    # 56.0f

    .line 5542
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->setCurrentHeaderHeight(I)V

    return-void

    .line 5545
    :cond_1
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    sub-int/2addr v0, p1

    invoke-direct {p0, v0}, Lorg/telegram/ui/ArticleViewer;->setCurrentHeaderHeight(I)V

    return-void
.end method

.method private checkScrollAnimated()V
    .locals 1

    const/4 v0, 0x0

    .line 5499
    invoke-direct {p0, v0}, Lorg/telegram/ui/ArticleViewer;->checkScrollAnimated(Ljava/lang/Runnable;)V

    return-void
.end method

.method private checkScrollAnimated(Ljava/lang/Runnable;)V
    .locals 3

    const/high16 v0, 0x42600000    # 56.0f

    .line 5502
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    .line 5503
    iget v2, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    if-ne v2, v1, :cond_1

    if-eqz p1, :cond_0

    .line 5505
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    :cond_0
    return-void

    .line 5509
    :cond_1
    new-instance v1, Landroid/animation/IntEvaluator;

    invoke-direct {v1}, Landroid/animation/IntEvaluator;-><init>()V

    iget v2, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    filled-new-array {v2, v0}, [Ljava/lang/Object;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/animation/ValueAnimator;->ofObject(Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/ValueAnimator;

    move-result-object v0

    const-wide/16 v1, 0xb4

    invoke-virtual {v0, v1, v2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    move-result-object v0

    .line 5510
    new-instance v1, Landroid/view/animation/DecelerateInterpolator;

    invoke-direct {v1}, Landroid/view/animation/DecelerateInterpolator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 5511
    new-instance v1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda36;

    invoke-direct {v1, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda36;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 5512
    new-instance v1, Lorg/telegram/ui/ArticleViewer$25;

    invoke-direct {v1, p0, p1}, Lorg/telegram/ui/ArticleViewer$25;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/Runnable;)V

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    if-eqz p1, :cond_2

    .line 5522
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->getDuration()J

    move-result-wide p0

    const-wide/16 v1, 0x2

    div-long/2addr p0, v1

    invoke-virtual {v0, p0, p1}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 5524
    :cond_2
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->start()V

    return-void
.end method

.method private checkVideoPlayer()V
    .locals 12

    .line 5217
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    if-eqz v0, :cond_9

    .line 5218
    iget-boolean v2, p0, Lorg/telegram/ui/ArticleViewer;->attachedToWindow:Z

    if-nez v2, :cond_0

    goto/16 :goto_1

    .line 5223
    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    int-to-float v2, v2

    const/high16 v3, 0x40000000    # 2.0f

    div-float/2addr v2, v3

    const/4 v4, 0x0

    const/4 v5, 0x0

    move v6, v1

    move-object v7, v4

    .line 5224
    :goto_0
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v8

    if-ge v6, v8, :cond_3

    .line 5225
    invoke-virtual {v0, v6}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v8

    .line 5226
    instance-of v9, v8, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    if-eqz v9, :cond_2

    .line 5227
    invoke-virtual {v8}, Landroid/view/View;->getTop()I

    move-result v9

    int-to-float v9, v9

    invoke-virtual {v8}, Landroid/view/View;->getMeasuredHeight()I

    move-result v10

    int-to-float v10, v10

    div-float/2addr v10, v3

    add-float/2addr v9, v10

    if-eqz v7, :cond_1

    sub-float v10, v2, v9

    .line 5228
    invoke-static {v10}, Ljava/lang/Math;->abs(F)F

    move-result v10

    sub-float v11, v2, v5

    invoke-static {v11}, Ljava/lang/Math;->abs(F)F

    move-result v11

    cmpg-float v10, v10, v11

    if-gez v10, :cond_2

    .line 5229
    :cond_1
    move-object v7, v8

    check-cast v7, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    move v5, v9

    :cond_2
    add-int/lit8 v6, v6, 0x1

    goto :goto_0

    .line 5234
    :cond_3
    invoke-static {}, Lorg/telegram/ui/PhotoViewer;->getInstance()Lorg/telegram/ui/PhotoViewer;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/PhotoViewer;->isVisibleOrAnimating()Z

    move-result v0

    if-nez v0, :cond_4

    .line 5235
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    if-eqz v2, :cond_8

    if-eq v2, v7, :cond_8

    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->videoPlayer:Lorg/telegram/messenger/video/VideoPlayerHolderBase;

    if-eqz v2, :cond_8

    .line 5236
    :cond_4
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->videoPlayer:Lorg/telegram/messenger/video/VideoPlayerHolderBase;

    if-eqz v2, :cond_7

    .line 5237
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->videoStates:Landroidx/collection/LongSparseArray;

    iget-object v3, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->-$$Nest$fgetcurrentBlock(Lorg/telegram/ui/ArticleViewer$BlockVideoCell;)Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    move-result-object v3

    iget-wide v5, v3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;->video_id:J

    iget-object v3, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    iget-object v8, p0, Lorg/telegram/ui/IArticleViewer;->videoPlayer:Lorg/telegram/messenger/video/VideoPlayerHolderBase;

    invoke-static {v8, v3}, Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;->fromPlayer(Lorg/telegram/messenger/video/VideoPlayerHolderBase;Lorg/telegram/ui/ArticleViewer$BlockVideoCell;)Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;

    move-result-object v8

    invoke-virtual {v3, v8}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->setState(Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;)Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;

    move-result-object v3

    invoke-virtual {v2, v5, v6, v3}, Landroidx/collection/LongSparseArray;->put(JLjava/lang/Object;)V

    .line 5238
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    invoke-static {v2}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->-$$Nest$fgetvideoState(Lorg/telegram/ui/ArticleViewer$BlockVideoCell;)Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;

    move-result-object v2

    if-eqz v2, :cond_6

    .line 5239
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    invoke-static {v2}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->-$$Nest$fgetvideoState(Lorg/telegram/ui/ArticleViewer$BlockVideoCell;)Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;

    move-result-object v2

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;->lastFrameBitmap:Landroid/graphics/Bitmap;

    if-eqz v2, :cond_5

    .line 5240
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    invoke-static {v2}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->-$$Nest$fgetimageView(Lorg/telegram/ui/ArticleViewer$BlockVideoCell;)Lorg/telegram/messenger/ImageReceiver;

    move-result-object v2

    iget-object v3, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->-$$Nest$fgetvideoState(Lorg/telegram/ui/ArticleViewer$BlockVideoCell;)Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;

    move-result-object v3

    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;->lastFrameBitmap:Landroid/graphics/Bitmap;

    invoke-virtual {v2, v3}, Lorg/telegram/messenger/ImageReceiver;->setImageBitmap(Landroid/graphics/Bitmap;)V

    .line 5242
    :cond_5
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    invoke-virtual {v2, v1}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->updateButtonState(Z)V

    .line 5244
    :cond_6
    iget-object v1, p0, Lorg/telegram/ui/IArticleViewer;->videoPlayer:Lorg/telegram/messenger/video/VideoPlayerHolderBase;

    invoke-virtual {v1, v4}, Lorg/telegram/messenger/video/VideoPlayerHolderBase;->release(Ljava/lang/Runnable;)Z

    .line 5246
    :cond_7
    iput-object v4, p0, Lorg/telegram/ui/IArticleViewer;->videoPlayer:Lorg/telegram/messenger/video/VideoPlayerHolderBase;

    .line 5247
    iput-object v4, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    :cond_8
    if-nez v0, :cond_9

    if-eqz v7, :cond_9

    .line 5250
    invoke-static {v7}, Lorg/telegram/ui/ArticleViewer$BlockVideoCell;->-$$Nest$mstartVideoPlayer(Lorg/telegram/ui/ArticleViewer$BlockVideoCell;)V

    .line 5251
    iput-object v7, p0, Lorg/telegram/ui/IArticleViewer;->currentPlayer:Lorg/telegram/ui/ArticleViewer$BlockVideoCell;

    :cond_9
    :goto_1
    return-void
.end method

.method private createLayoutForText(Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;ILorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 0

    .line 3473
    invoke-static/range {p0 .. p9}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;ILorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method private createLayoutForText(Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 10

    const/4 v5, 0x0

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move v4, p4

    move-object/from16 v6, p6

    move-object/from16 v7, p7

    move-object/from16 v9, p8

    .line 3459
    invoke-direct/range {v0 .. v9}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;ILorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method private createLayoutForText(Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 10

    .line 3466
    sget-object v7, Landroid/text/Layout$Alignment;->ALIGN_NORMAL:Landroid/text/Layout$Alignment;

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move v4, p4

    move v5, p5

    move-object/from16 v6, p6

    move-object/from16 v9, p7

    invoke-direct/range {v0 .. v9}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;ILorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method public static createLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;ILorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 19

    move-object/from16 v7, p2

    move-object/from16 v3, p3

    const/4 v8, 0x0

    if-nez v7, :cond_1

    if-eqz v3, :cond_0

    .line 3476
    instance-of v0, v3, Lorg/telegram/tgnet/tl/TL_iv$textEmpty;

    if-eqz v0, :cond_1

    :cond_0
    return-object v8

    :cond_1
    if-gez p4, :cond_2

    const/high16 v0, 0x41200000    # 10.0f

    .line 3480
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    move v11, v0

    goto :goto_0

    :cond_2
    move/from16 v11, p4

    :goto_0
    if-eqz v7, :cond_3

    move-object/from16 v0, p0

    move-object/from16 v2, p1

    move-object/from16 v5, p6

    move-object v4, v7

    goto :goto_1

    :cond_3
    move-object/from16 v4, p3

    move-object/from16 v0, p0

    move-object/from16 v2, p1

    move-object/from16 v5, p6

    move-object/from16 v1, p9

    move v6, v11

    .line 3487
    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v4

    .line 3489
    :goto_1
    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_4

    return-object v8

    .line 3493
    :cond_4
    sget v1, Lorg/telegram/messenger/SharedConfig;->ivFontSize:I

    add-int/lit8 v1, v1, -0x10

    int-to-float v1, v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    .line 3496
    instance-of v6, v5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbedPost;

    const/high16 v9, 0x41600000    # 14.0f

    const/high16 v10, 0x41700000    # 15.0f

    const/4 v12, 0x1

    if-eqz v6, :cond_8

    if-nez v3, :cond_8

    .line 3497
    move-object v6, v5

    check-cast v6, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbedPost;

    .line 3498
    iget-object v6, v6, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbedPost;->author:Ljava/lang/String;

    if-ne v6, v7, :cond_6

    .line 3499
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->embedPostAuthorPaint:Landroid/text/TextPaint;

    if-nez v6, :cond_5

    .line 3500
    new-instance v6, Landroid/text/TextPaint;

    invoke-direct {v6, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v6, Lorg/telegram/ui/ArticleViewer;->embedPostAuthorPaint:Landroid/text/TextPaint;

    .line 3501
    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 3503
    :cond_5
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->embedPostAuthorPaint:Landroid/text/TextPaint;

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    add-int/2addr v7, v1

    int-to-float v1, v7

    invoke-virtual {v6, v1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3504
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->embedPostAuthorPaint:Landroid/text/TextPaint;

    goto :goto_2

    .line 3506
    :cond_6
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->embedPostDatePaint:Landroid/text/TextPaint;

    if-nez v6, :cond_7

    .line 3507
    new-instance v6, Landroid/text/TextPaint;

    invoke-direct {v6, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v6, Lorg/telegram/ui/ArticleViewer;->embedPostDatePaint:Landroid/text/TextPaint;

    .line 3508
    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 3510
    :cond_7
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->embedPostDatePaint:Landroid/text/TextPaint;

    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    add-int/2addr v7, v1

    int-to-float v1, v7

    invoke-virtual {v6, v1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3511
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->embedPostDatePaint:Landroid/text/TextPaint;

    :goto_2
    move-object v10, v1

    goto/16 :goto_3

    .line 3513
    :cond_8
    instance-of v6, v5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;

    if-eqz v6, :cond_b

    .line 3514
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePaint:Landroid/text/TextPaint;

    if-nez v1, :cond_9

    .line 3515
    new-instance v1, Landroid/text/TextPaint;

    invoke-direct {v1, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePaint:Landroid/text/TextPaint;

    .line 3516
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v6

    invoke-virtual {v1, v6}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    .line 3518
    new-instance v1, Landroid/text/TextPaint;

    invoke-direct {v1, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePhotoPaint:Landroid/text/TextPaint;

    .line 3519
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v6

    invoke-virtual {v1, v6}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    .line 3521
    :cond_9
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePaint:Landroid/text/TextPaint;

    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v6

    invoke-virtual {v1, v6}, Landroid/graphics/Paint;->setColor(I)V

    .line 3522
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePaint:Landroid/text/TextPaint;

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {v1, v6}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3524
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePhotoPaint:Landroid/text/TextPaint;

    const/4 v6, -0x1

    invoke-virtual {v1, v6}, Landroid/graphics/Paint;->setColor(I)V

    .line 3525
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePhotoPaint:Landroid/text/TextPaint;

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {v1, v6}, Landroid/graphics/Paint;->setTextSize(F)V

    if-eqz p9, :cond_a

    .line 3527
    invoke-static/range {p9 .. p9}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetchannelBlock(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;

    move-result-object v1

    if-eqz v1, :cond_a

    sget-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePhotoPaint:Landroid/text/TextPaint;

    goto :goto_2

    :cond_a
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->channelNamePaint:Landroid/text/TextPaint;

    goto :goto_2

    .line 3528
    :cond_b
    instance-of v6, v5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;

    if-eqz v6, :cond_f

    .line 3529
    move-object v6, v5

    check-cast v6, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;

    .line 3530
    iget-object v13, v6, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;->parent:Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;

    iget-object v13, v13, Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;->articles:Ljava/util/ArrayList;

    iget v6, v6, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;->num:I

    invoke-virtual {v13, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lorg/telegram/tgnet/tl/TL_iv$pageRelatedArticle;

    iget-object v6, v6, Lorg/telegram/tgnet/tl/TL_iv$pageRelatedArticle;->title:Ljava/lang/String;

    if-ne v7, v6, :cond_d

    .line 3531
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleHeaderPaint:Landroid/text/TextPaint;

    if-nez v6, :cond_c

    .line 3532
    new-instance v6, Landroid/text/TextPaint;

    invoke-direct {v6, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleHeaderPaint:Landroid/text/TextPaint;

    .line 3533
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    .line 3535
    :cond_c
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleHeaderPaint:Landroid/text/TextPaint;

    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 3536
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleHeaderPaint:Landroid/text/TextPaint;

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    add-int/2addr v7, v1

    int-to-float v1, v7

    invoke-virtual {v6, v1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3537
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->relatedArticleHeaderPaint:Landroid/text/TextPaint;

    goto/16 :goto_2

    .line 3539
    :cond_d
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleTextPaint:Landroid/text/TextPaint;

    if-nez v6, :cond_e

    .line 3540
    new-instance v6, Landroid/text/TextPaint;

    invoke-direct {v6, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleTextPaint:Landroid/text/TextPaint;

    .line 3542
    :cond_e
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleTextPaint:Landroid/text/TextPaint;

    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 3543
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->relatedArticleTextPaint:Landroid/text/TextPaint;

    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    add-int/2addr v7, v1

    int-to-float v1, v7

    invoke-virtual {v6, v1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3544
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->relatedArticleTextPaint:Landroid/text/TextPaint;

    goto/16 :goto_2

    .line 3546
    :cond_f
    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer;->isListItemBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    move-result v6

    if-eqz v6, :cond_13

    if-eqz v7, :cond_13

    .line 3547
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->listTextPointerPaint:Landroid/text/TextPaint;

    if-nez v6, :cond_10

    .line 3548
    new-instance v6, Landroid/text/TextPaint;

    invoke-direct {v6, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v6, Lorg/telegram/ui/ArticleViewer;->listTextPointerPaint:Landroid/text/TextPaint;

    .line 3549
    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 3551
    :cond_10
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->listTextNumPaint:Landroid/text/TextPaint;

    if-nez v6, :cond_11

    .line 3552
    new-instance v6, Landroid/text/TextPaint;

    invoke-direct {v6, v12}, Landroid/text/TextPaint;-><init>(I)V

    sput-object v6, Lorg/telegram/ui/ArticleViewer;->listTextNumPaint:Landroid/text/TextPaint;

    .line 3553
    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 3555
    :cond_11
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->listTextPointerPaint:Landroid/text/TextPaint;

    const/high16 v7, 0x41980000    # 19.0f

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    add-int/2addr v7, v1

    int-to-float v7, v7

    invoke-virtual {v6, v7}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3556
    sget-object v6, Lorg/telegram/ui/ArticleViewer;->listTextNumPaint:Landroid/text/TextPaint;

    const/high16 v7, 0x41800000    # 16.0f

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    add-int/2addr v7, v1

    int-to-float v1, v7

    invoke-virtual {v6, v1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3557
    instance-of v1, v5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    if-eqz v1, :cond_12

    move-object v1, v5

    check-cast v1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->parent:Lorg/telegram/ui/ArticleViewer$TL_pageBlockListParent;

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListParent;->pageBlockList:Lorg/telegram/tgnet/tl/TL_iv$pageBlockList;

    iget-boolean v1, v1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockList;->ordered:Z

    if-nez v1, :cond_12

    .line 3558
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->listTextPointerPaint:Landroid/text/TextPaint;

    goto/16 :goto_2

    .line 3560
    :cond_12
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->listTextNumPaint:Landroid/text/TextPaint;

    goto/16 :goto_2

    .line 3563
    :cond_13
    invoke-static {v0, v3, v3, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v1

    goto/16 :goto_2

    .line 3565
    :goto_3
    invoke-virtual {v10}, Landroid/graphics/Paint;->getFontMetricsInt()Landroid/graphics/Paint$FontMetricsInt;

    move-result-object v1

    const/4 v6, 0x2

    const/4 v7, 0x0

    invoke-static {v4, v1, v7, v8, v6}, Lorg/telegram/messenger/Emoji;->replaceEmoji(Ljava/lang/CharSequence;Landroid/graphics/Paint$FontMetricsInt;Z[II)Ljava/lang/CharSequence;

    move-result-object v9

    const/high16 v1, 0x40800000    # 4.0f

    if-eqz p8, :cond_15

    .line 3568
    instance-of v4, v5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;

    if-eqz v4, :cond_14

    move v4, v12

    .line 3569
    sget-object v12, Landroid/text/Layout$Alignment;->ALIGN_CENTER:Landroid/text/Layout$Alignment;

    const/4 v15, 0x0

    sget-object v16, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    const/high16 v13, 0x3f800000    # 1.0f

    const/4 v14, 0x0

    move/from16 v17, v11

    move/from16 v18, p8

    invoke-static/range {v9 .. v18}, Lorg/telegram/ui/Components/StaticLayoutEx;->createStaticLayout(Ljava/lang/CharSequence;Landroid/text/TextPaint;ILandroid/text/Layout$Alignment;FFZLandroid/text/TextUtils$TruncateAt;II)Landroid/text/StaticLayout;

    move-result-object v1

    goto :goto_5

    :cond_14
    move v4, v12

    .line 3571
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v14, v1

    const/4 v15, 0x0

    sget-object v16, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    const/high16 v13, 0x3f800000    # 1.0f

    move/from16 v17, v11

    move-object/from16 v12, p7

    move/from16 v18, p8

    invoke-static/range {v9 .. v18}, Lorg/telegram/ui/Components/StaticLayoutEx;->createStaticLayout(Ljava/lang/CharSequence;Landroid/text/TextPaint;ILandroid/text/Layout$Alignment;FFZLandroid/text/TextUtils$TruncateAt;II)Landroid/text/StaticLayout;

    move-result-object v1

    goto :goto_5

    :cond_15
    move v4, v12

    .line 3574
    invoke-interface {v9}, Ljava/lang/CharSequence;->length()I

    move-result v6

    sub-int/2addr v6, v4

    invoke-interface {v9, v6}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v6

    const/16 v12, 0xa

    if-ne v6, v12, :cond_16

    .line 3575
    invoke-interface {v9}, Ljava/lang/CharSequence;->length()I

    move-result v6

    sub-int/2addr v6, v4

    invoke-interface {v9, v7, v6}, Ljava/lang/CharSequence;->subSequence(II)Ljava/lang/CharSequence;

    move-result-object v9

    .line 3577
    :cond_16
    instance-of v6, v5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;

    if-eqz v6, :cond_17

    move v12, v11

    move-object v11, v10

    move-object v10, v9

    .line 3578
    new-instance v9, Landroid/text/StaticLayout;

    sget-object v13, Landroid/text/Layout$Alignment;->ALIGN_CENTER:Landroid/text/Layout$Alignment;

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/high16 v14, 0x3f800000    # 1.0f

    invoke-direct/range {v9 .. v16}, Landroid/text/StaticLayout;-><init>(Ljava/lang/CharSequence;Landroid/text/TextPaint;ILandroid/text/Layout$Alignment;FFZ)V

    :goto_4
    move-object v1, v9

    move-object v9, v10

    goto :goto_5

    :cond_17
    move-object v6, v10

    move-object v10, v9

    .line 3580
    new-instance v9, Landroid/text/StaticLayout;

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v15, v1

    const/16 v16, 0x0

    const/high16 v14, 0x3f800000    # 1.0f

    move-object/from16 v13, p7

    move v12, v11

    move-object v11, v6

    invoke-direct/range {v9 .. v16}, Landroid/text/StaticLayout;-><init>(Ljava/lang/CharSequence;Landroid/text/TextPaint;ILandroid/text/Layout$Alignment;FFZ)V

    goto :goto_4

    :goto_5
    if-nez v1, :cond_18

    return-object v8

    .line 3586
    :cond_18
    invoke-virtual {v1}, Landroid/text/Layout;->getText()Ljava/lang/CharSequence;

    move-result-object v6

    if-ltz p5, :cond_1b

    .line 3590
    iget-object v10, v0, Lorg/telegram/ui/IArticleViewer;->searchResults:Ljava/util/ArrayList;

    invoke-virtual {v10}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v10

    if-nez v10, :cond_1b

    iget-object v10, v0, Lorg/telegram/ui/IArticleViewer;->searchText:Ljava/lang/String;

    if-eqz v10, :cond_1b

    .line 3591
    invoke-virtual {v0}, Lorg/telegram/ui/IArticleViewer;->getAdapter()Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    move-result-object v10

    if-eqz v10, :cond_1b

    .line 3593
    invoke-interface {v9}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v9

    move v11, v7

    .line 3596
    :goto_6
    iget-object v12, v0, Lorg/telegram/ui/IArticleViewer;->searchText:Ljava/lang/String;

    invoke-virtual {v9, v12, v11}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I

    move-result v11

    if-ltz v11, :cond_1b

    .line 3597
    iget-object v12, v0, Lorg/telegram/ui/IArticleViewer;->searchText:Ljava/lang/String;

    invoke-virtual {v12}, Ljava/lang/String;->length()I

    move-result v12

    add-int/2addr v12, v11

    if-eqz v11, :cond_19

    add-int/lit8 v13, v11, -0x1

    .line 3598
    invoke-virtual {v9, v13}, Ljava/lang/String;->charAt(I)C

    move-result v13

    invoke-static {v13}, Lorg/telegram/messenger/AndroidUtilities;->isPunctuationCharacter(C)Z

    move-result v13

    if-eqz v13, :cond_1a

    .line 3599
    :cond_19
    invoke-static {v10}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetsearchTextOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v13

    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v15, v0, Lorg/telegram/ui/IArticleViewer;->searchText:Ljava/lang/String;

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v14, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v14, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v14, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v1, v11}, Landroid/text/Layout;->getLineForOffset(I)I

    move-result v11

    invoke-virtual {v1, v11}, Landroid/text/StaticLayout;->getLineTop(I)I

    move-result v11

    add-int v11, p5, v11

    invoke-static {v11}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v11

    invoke-virtual {v13, v14, v11}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1a
    move v11, v12

    goto :goto_6

    .line 3605
    :cond_1b
    instance-of v9, v6, Landroid/text/Spanned;

    if-eqz v9, :cond_28

    .line 3606
    move-object v9, v6

    check-cast v9, Landroid/text/Spanned;

    .line 3608
    :try_start_0
    invoke-interface {v9}, Ljava/lang/CharSequence;->length()I

    move-result v10

    const-class v11, Lorg/telegram/ui/Components/AnchorSpan;

    invoke-interface {v9, v7, v10, v11}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v10

    check-cast v10, [Lorg/telegram/ui/Components/AnchorSpan;

    .line 3609
    invoke-virtual {v1}, Landroid/text/StaticLayout;->getLineCount()I

    move-result v11

    if-eqz v10, :cond_1d

    .line 3610
    array-length v12, v10

    if-lez v12, :cond_1d

    move v12, v7

    .line 3611
    :goto_7
    array-length v13, v10

    if-ge v12, v13, :cond_1d

    if-gt v11, v4, :cond_1c

    .line 3613
    invoke-static/range {p9 .. p9}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetanchorsOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v13

    aget-object v14, v10, v12

    invoke-virtual {v14}, Lorg/telegram/ui/Components/AnchorSpan;->getName()Ljava/lang/String;

    move-result-object v14

    invoke-static/range {p5 .. p5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v15

    invoke-virtual {v13, v14, v15}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_8

    .line 3615
    :cond_1c
    invoke-static/range {p9 .. p9}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetanchorsOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v13

    aget-object v14, v10, v12

    invoke-virtual {v14}, Lorg/telegram/ui/Components/AnchorSpan;->getName()Ljava/lang/String;

    move-result-object v14

    aget-object v15, v10, v12

    invoke-interface {v9, v15}, Landroid/text/Spanned;->getSpanStart(Ljava/lang/Object;)I

    move-result v15

    invoke-virtual {v1, v15}, Landroid/text/Layout;->getLineForOffset(I)I

    move-result v15

    invoke-virtual {v1, v15}, Landroid/text/StaticLayout;->getLineTop(I)I

    move-result v15

    add-int v15, p5, v15

    invoke-static {v15}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v15

    invoke-virtual {v13, v14, v15}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :goto_8
    add-int/lit8 v12, v12, 0x1

    goto :goto_7

    :catch_0
    :cond_1d
    const/4 v12, 0x0

    .line 3623
    :try_start_1
    invoke-interface {v9}, Ljava/lang/CharSequence;->length()I

    move-result v13

    const-class v14, Lorg/telegram/ui/Components/TextPaintWebpageUrlSpan;

    invoke-interface {v9, v7, v13, v14}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v13

    check-cast v13, [Lorg/telegram/ui/Components/TextPaintWebpageUrlSpan;

    if-eqz v13, :cond_22

    .line 3624
    array-length v14, v13

    if-lez v14, :cond_22

    .line 3625
    new-instance v14, Lorg/telegram/ui/Components/LinkPath;

    invoke-direct {v14, v4}, Lorg/telegram/ui/Components/LinkPath;-><init>(Z)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    .line 3626
    :try_start_2
    invoke-virtual {v14, v7}, Lorg/telegram/ui/Components/LinkPath;->setAllowReset(Z)V

    move v15, v7

    .line 3627
    :goto_9
    array-length v8, v13

    if-ge v15, v8, :cond_21

    .line 3628
    aget-object v8, v13, v15

    invoke-interface {v9, v8}, Landroid/text/Spanned;->getSpanStart(Ljava/lang/Object;)I

    move-result v8

    .line 3629
    aget-object v10, v13, v15

    invoke-interface {v9, v10}, Landroid/text/Spanned;->getSpanEnd(Ljava/lang/Object;)I

    move-result v10

    .line 3630
    invoke-virtual {v14, v1, v8, v12}, Lorg/telegram/ui/Components/LinkPath;->setCurrentLayout(Landroid/text/Layout;IF)V

    .line 3631
    aget-object v17, v13, v15

    invoke-virtual/range {v17 .. v17}, Lorg/telegram/ui/Components/TextPaintUrlSpan;->getTextPaint()Landroid/text/TextPaint;

    move-result-object v17

    if-eqz v17, :cond_1e

    aget-object v17, v13, v15

    invoke-virtual/range {v17 .. v17}, Lorg/telegram/ui/Components/TextPaintUrlSpan;->getTextPaint()Landroid/text/TextPaint;

    move-result-object v11

    iget v11, v11, Landroid/text/TextPaint;->baselineShift:I

    goto :goto_a

    :cond_1e
    move v11, v7

    :goto_a
    if-eqz v11, :cond_20

    if-lez v11, :cond_1f

    const/high16 v17, 0x40a00000    # 5.0f

    goto :goto_b

    :cond_1f
    const/high16 v17, -0x40000000    # -2.0f

    .line 3632
    :goto_b
    invoke-static/range {v17 .. v17}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v17

    add-int v11, v11, v17

    goto :goto_c

    :cond_20
    move v11, v7

    :goto_c
    invoke-virtual {v14, v11}, Lorg/telegram/ui/Components/LinkPath;->setBaselineShift(I)V

    .line 3633
    invoke-virtual {v1, v8, v10, v14}, Landroid/text/Layout;->getSelectionPath(IILandroid/graphics/Path;)V

    add-int/lit8 v15, v15, 0x1

    goto :goto_9

    .line 3635
    :cond_21
    invoke-virtual {v14, v4}, Lorg/telegram/ui/Components/LinkPath;->setAllowReset(Z)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    goto :goto_d

    :catch_1
    :cond_22
    const/4 v14, 0x0

    .line 3641
    :catch_2
    :goto_d
    :try_start_3
    invoke-interface {v9}, Ljava/lang/CharSequence;->length()I

    move-result v8

    const-class v10, Lorg/telegram/ui/Components/TextPaintMarkSpan;

    invoke-interface {v9, v7, v8, v10}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v8

    check-cast v8, [Lorg/telegram/ui/Components/TextPaintMarkSpan;

    if-eqz v8, :cond_27

    .line 3642
    array-length v10, v8

    if-lez v10, :cond_27

    .line 3643
    new-instance v10, Lorg/telegram/ui/Components/LinkPath;

    invoke-direct {v10, v4}, Lorg/telegram/ui/Components/LinkPath;-><init>(Z)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_4

    .line 3644
    :try_start_4
    invoke-virtual {v10, v7}, Lorg/telegram/ui/Components/LinkPath;->setAllowReset(Z)V

    move v11, v7

    .line 3645
    :goto_e
    array-length v13, v8

    if-ge v11, v13, :cond_26

    .line 3646
    aget-object v13, v8, v11

    invoke-interface {v9, v13}, Landroid/text/Spanned;->getSpanStart(Ljava/lang/Object;)I

    move-result v13

    .line 3647
    aget-object v15, v8, v11

    invoke-interface {v9, v15}, Landroid/text/Spanned;->getSpanEnd(Ljava/lang/Object;)I

    move-result v15

    .line 3648
    invoke-virtual {v10, v1, v13, v12}, Lorg/telegram/ui/Components/LinkPath;->setCurrentLayout(Landroid/text/Layout;IF)V

    .line 3649
    aget-object v16, v8, v11

    invoke-virtual/range {v16 .. v16}, Lorg/telegram/ui/Components/TextPaintMarkSpan;->getTextPaint()Landroid/text/TextPaint;

    move-result-object v16

    if-eqz v16, :cond_23

    aget-object v16, v8, v11

    invoke-virtual/range {v16 .. v16}, Lorg/telegram/ui/Components/TextPaintMarkSpan;->getTextPaint()Landroid/text/TextPaint;

    move-result-object v7

    iget v7, v7, Landroid/text/TextPaint;->baselineShift:I

    goto :goto_f

    :cond_23
    const/4 v7, 0x0

    :goto_f
    if-eqz v7, :cond_25

    if-lez v7, :cond_24

    const/high16 v16, 0x40a00000    # 5.0f

    goto :goto_10

    :cond_24
    const/high16 v16, -0x40000000    # -2.0f

    .line 3650
    :goto_10
    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v16

    add-int v7, v7, v16

    goto :goto_11

    :cond_25
    const/4 v7, 0x0

    :goto_11
    invoke-virtual {v10, v7}, Lorg/telegram/ui/Components/LinkPath;->setBaselineShift(I)V

    .line 3651
    invoke-virtual {v1, v13, v15, v10}, Landroid/text/Layout;->getSelectionPath(IILandroid/graphics/Path;)V

    add-int/lit8 v11, v11, 0x1

    const/4 v7, 0x0

    goto :goto_e

    .line 3653
    :cond_26
    invoke-virtual {v10, v4}, Lorg/telegram/ui/Components/LinkPath;->setAllowReset(Z)V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_3

    :catch_3
    move-object v8, v10

    goto :goto_12

    :catch_4
    :cond_27
    const/4 v8, 0x0

    :goto_12
    move-object v7, v8

    move-object v8, v14

    goto :goto_13

    :cond_28
    const/4 v7, 0x0

    const/4 v8, 0x0

    .line 3659
    :goto_13
    new-instance v9, Lorg/telegram/ui/ArticleViewer$DrawingText;

    invoke-direct {v9, v0}, Lorg/telegram/ui/ArticleViewer$DrawingText;-><init>(Lorg/telegram/ui/IArticleViewer;)V

    .line 3660
    iput-object v1, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->textLayout:Landroid/text/StaticLayout;

    .line 3661
    iput-object v8, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->textPath:Lorg/telegram/ui/Components/LinkPath;

    .line 3662
    iput-object v7, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->markPath:Lorg/telegram/ui/Components/LinkPath;

    .line 3663
    iput-object v5, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->parentBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 3664
    iput-object v3, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->parentText:Ljava/lang/Object;

    .line 3665
    new-instance v0, Ljava/util/Stack;

    invoke-direct {v0}, Ljava/util/Stack;-><init>()V

    iput-object v0, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilersPool:Ljava/util/Stack;

    .line 3666
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilers:Ljava/util/List;

    .line 3667
    new-instance v0, Ljava/util/concurrent/atomic/AtomicReference;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicReference;-><init>()V

    iput-object v0, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilersPatchedLayout:Ljava/util/concurrent/atomic/AtomicReference;

    .line 3668
    instance-of v0, v6, Landroid/text/Spanned;

    if-eqz v0, :cond_29

    .line 3669
    check-cast v6, Landroid/text/Spanned;

    iget-object v0, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilersPool:Ljava/util/Stack;

    iget-object v7, v9, Lorg/telegram/ui/ArticleViewer$DrawingText;->spoilers:Ljava/util/List;

    invoke-static {v2, v1, v6, v0, v7}, Lorg/telegram/ui/Components/spoilers/SpoilerEffect;->addSpoilers(Landroid/view/View;Landroid/text/Layout;Landroid/text/Spanned;Ljava/util/Stack;Ljava/util/List;)V

    :cond_29
    if-eqz v2, :cond_2f

    .line 3672
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->liveDrawingTexts:Ljava/util/WeakHashMap;

    invoke-virtual {v0, v2}, Ljava/util/WeakHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    if-eqz v0, :cond_2c

    const/4 v7, 0x0

    .line 3674
    :goto_14
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge v7, v1, :cond_2c

    .line 3675
    invoke-virtual {v0, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/ui/ArticleViewer$DrawingText;

    .line 3676
    iget-object v6, v1, Lorg/telegram/ui/ArticleViewer$DrawingText;->parentBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    if-ne v6, v5, :cond_2a

    if-eqz v3, :cond_2b

    iget-object v6, v1, Lorg/telegram/ui/ArticleViewer$DrawingText;->parentText:Ljava/lang/Object;

    if-ne v6, v3, :cond_2b

    .line 3677
    :cond_2a
    invoke-virtual {v1, v2}, Lorg/telegram/ui/ArticleViewer$DrawingText;->detach(Landroid/view/View;)V

    .line 3678
    invoke-virtual {v0, v7}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    add-int/lit8 v7, v7, -0x1

    :cond_2b
    add-int/2addr v7, v4

    goto :goto_14

    :cond_2c
    if-eqz v3, :cond_2e

    if-nez v0, :cond_2d

    .line 3685
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->liveDrawingTexts:Ljava/util/WeakHashMap;

    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {v0, v2, v1}, Ljava/util/WeakHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-object v0, v1

    .line 3687
    :cond_2d
    invoke-virtual {v0, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 3689
    :cond_2e
    invoke-virtual {v2}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_2f

    .line 3690
    invoke-virtual {v9, v2}, Lorg/telegram/ui/ArticleViewer$DrawingText;->attach(Landroid/view/View;)V

    :cond_2f
    return-object v9
.end method

.method private static createLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 10

    const/4 v5, 0x0

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move v4, p4

    move-object/from16 v6, p6

    move-object/from16 v7, p7

    move-object/from16 v9, p8

    .line 3462
    invoke-static/range {v0 .. v9}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;ILorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method public static createLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;
    .locals 10

    .line 3469
    sget-object v7, Landroid/text/Layout$Alignment;->ALIGN_NORMAL:Landroid/text/Layout$Alignment;

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move v4, p4

    move v5, p5

    move-object/from16 v6, p6

    move-object/from16 v9, p7

    invoke-static/range {v0 .. v9}, Lorg/telegram/ui/ArticleViewer;->createLayoutForText(Lorg/telegram/ui/IArticleViewer;Landroid/view/View;Ljava/lang/CharSequence;Lorg/telegram/tgnet/tl/TL_iv$RichText;IILorg/telegram/tgnet/tl/TL_iv$PageBlock;Landroid/text/Layout$Alignment;ILorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/ui/ArticleViewer$DrawingText;

    move-result-object p0

    return-object p0
.end method

.method public static createPaint(Lorg/telegram/ui/IArticleViewer;Z)V
    .locals 6

    .line 1487
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->quoteLinePaint:Landroid/graphics/Paint;

    if-nez v0, :cond_0

    .line 1488
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->quoteLinePaint:Landroid/graphics/Paint;

    .line 1490
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->preformattedBackgroundPaint:Landroid/graphics/Paint;

    .line 1492
    new-instance p1, Landroid/graphics/Paint;

    const/4 v0, 0x1

    invoke-direct {p1, v0}, Landroid/graphics/Paint;-><init>(I)V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->tableLinePaint:Landroid/graphics/Paint;

    .line 1493
    sget-object v1, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 1494
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->tableLinePaint:Landroid/graphics/Paint;

    const/high16 v2, 0x3f800000    # 1.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p1, v3}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 1496
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->tableHalfLinePaint:Landroid/graphics/Paint;

    .line 1497
    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 1498
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->tableHalfLinePaint:Landroid/graphics/Paint;

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    const/high16 v2, 0x40000000    # 2.0f

    div-float/2addr v1, v2

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 1500
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->tableHeaderPaint:Landroid/graphics/Paint;

    .line 1501
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->tableStripPaint:Landroid/graphics/Paint;

    .line 1503
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->urlPaint:Landroid/graphics/Paint;

    .line 1504
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1, v0}, Landroid/graphics/Paint;-><init>(I)V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->webpageUrlPaint:Landroid/graphics/Paint;

    .line 1505
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1, v0}, Landroid/graphics/Paint;-><init>(I)V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->webpageSearchPaint:Landroid/graphics/Paint;

    .line 1506
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->photoBackgroundPaint:Landroid/graphics/Paint;

    .line 1507
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->dividerPaint:Landroid/graphics/Paint;

    .line 1508
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1, v0}, Landroid/graphics/Paint;-><init>(I)V

    sput-object p1, Lorg/telegram/ui/ArticleViewer;->webpageMarkPaint:Landroid/graphics/Paint;

    goto :goto_0

    :cond_0
    if-nez p1, :cond_1

    return-void

    .line 1513
    :cond_1
    :goto_0
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p0, p1}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result p1

    .line 1514
    invoke-static {p1}, Landroid/graphics/Color;->red(I)I

    move-result v0

    int-to-float v0, v0

    const v1, 0x3e59b3d0    # 0.2126f

    mul-float/2addr v0, v1

    invoke-static {p1}, Landroid/graphics/Color;->green(I)I

    move-result v1

    int-to-float v1, v1

    const v2, 0x3f371759    # 0.7152f

    mul-float/2addr v1, v2

    add-float/2addr v0, v1

    invoke-static {p1}, Landroid/graphics/Color;->blue(I)I

    move-result p1

    int-to-float p1, p1

    const v1, 0x3d93dd98    # 0.0722f

    mul-float/2addr p1, v1

    add-float/2addr v0, p1

    const/high16 p1, 0x437f0000    # 255.0f

    div-float/2addr v0, p1

    .line 1515
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->webpageSearchPaint:Landroid/graphics/Paint;

    const v1, 0x3f347ae1    # 0.705f

    cmpg-float v0, v0, v1

    if-gtz v0, :cond_2

    const v0, -0x2e67d2

    goto :goto_1

    :cond_2
    const/16 v0, -0x1997

    :goto_1
    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 1516
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->webpageUrlPaint:Landroid/graphics/Paint;

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteLinkSelection:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v1

    const v2, 0x33ffffff

    and-int/2addr v1, v2

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1517
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->webpageUrlPaint:Landroid/graphics/Paint;

    invoke-static {}, Lorg/telegram/ui/Components/LinkPath;->getRoundedEffect()Landroid/graphics/CornerPathEffect;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setPathEffect(Landroid/graphics/PathEffect;)Landroid/graphics/PathEffect;

    .line 1518
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->urlPaint:Landroid/graphics/Paint;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v1

    and-int/2addr v1, v2

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1519
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->urlPaint:Landroid/graphics/Paint;

    invoke-static {}, Lorg/telegram/ui/Components/LinkPath;->getRoundedEffect()Landroid/graphics/CornerPathEffect;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setPathEffect(Landroid/graphics/PathEffect;)Landroid/graphics/PathEffect;

    .line 1520
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->tableHalfLinePaint:Landroid/graphics/Paint;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteInputField:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v3

    invoke-virtual {p1, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 1521
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->tableLinePaint:Landroid/graphics/Paint;

    invoke-virtual {p0, v1}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v1

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1523
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->photoBackgroundPaint:Landroid/graphics/Paint;

    const/high16 v1, 0xf000000

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1524
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->dividerPaint:Landroid/graphics/Paint;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_divider:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v1

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1525
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->webpageMarkPaint:Landroid/graphics/Paint;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v1

    and-int/2addr v1, v2

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1526
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->webpageMarkPaint:Landroid/graphics/Paint;

    invoke-static {}, Lorg/telegram/ui/Components/LinkPath;->getRoundedEffect()Landroid/graphics/CornerPathEffect;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/graphics/Paint;->setPathEffect(Landroid/graphics/PathEffect;)Landroid/graphics/PathEffect;

    .line 1528
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_switchTrack:I

    invoke-virtual {p0, p1}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result p1

    .line 1529
    invoke-static {p1}, Landroid/graphics/Color;->red(I)I

    move-result v1

    .line 1530
    invoke-static {p1}, Landroid/graphics/Color;->green(I)I

    move-result v2

    .line 1531
    invoke-static {p1}, Landroid/graphics/Color;->blue(I)I

    move-result p1

    .line 1532
    sget-object v3, Lorg/telegram/ui/ArticleViewer;->tableStripPaint:Landroid/graphics/Paint;

    const/16 v4, 0x14

    invoke-static {v4, v1, v2, p1}, Landroid/graphics/Color;->argb(IIII)I

    move-result v5

    invoke-virtual {v3, v5}, Landroid/graphics/Paint;->setColor(I)V

    .line 1533
    sget-object v3, Lorg/telegram/ui/ArticleViewer;->tableHeaderPaint:Landroid/graphics/Paint;

    const/16 v5, 0x22

    invoke-static {v5, v1, v2, p1}, Landroid/graphics/Color;->argb(IIII)I

    move-result p1

    invoke-virtual {v3, p1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1535
    invoke-virtual {p0, v0}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result p1

    .line 1536
    invoke-static {p1}, Landroid/graphics/Color;->red(I)I

    move-result v0

    .line 1537
    invoke-static {p1}, Landroid/graphics/Color;->green(I)I

    move-result v1

    .line 1538
    invoke-static {p1}, Landroid/graphics/Color;->blue(I)I

    move-result p1

    .line 1539
    sget-object v2, Lorg/telegram/ui/ArticleViewer;->preformattedBackgroundPaint:Landroid/graphics/Paint;

    invoke-static {v4, v0, v1, p1}, Landroid/graphics/Color;->argb(IIII)I

    move-result p1

    invoke-virtual {v2, p1}, Landroid/graphics/Paint;->setColor(I)V

    .line 1541
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->quoteLinePaint:Landroid/graphics/Paint;

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_inReplyLine:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result p0

    invoke-virtual {p1, p0}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method

.method public static drawQuoteLines(Landroid/graphics/Canvas;Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)V
    .locals 18

    move-object/from16 v0, p2

    if-eqz v0, :cond_4

    if-eqz p1, :cond_4

    .line 2786
    sget-object v1, Lorg/telegram/ui/ArticleViewer;->quoteLinePaint:Landroid/graphics/Paint;

    if-nez v1, :cond_0

    goto :goto_2

    .line 2787
    :cond_0
    invoke-virtual/range {p1 .. p1}, Lorg/telegram/ui/IArticleViewer;->padx()I

    move-result v1

    .line 2788
    iget-boolean v2, v0, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->bottom:Z

    const/4 v3, 0x0

    if-eqz v2, :cond_1

    const/high16 v2, 0x40c00000    # 6.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    goto :goto_0

    :cond_1
    move v2, v3

    :goto_0
    sub-int v2, p3, v2

    .line 2790
    iget v4, v0, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->quoteLevels:I

    const/high16 v5, 0x40000000    # 2.0f

    if-nez v4, :cond_2

    .line 2792
    iget v0, v0, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->level:I

    if-lez v0, :cond_4

    int-to-float v0, v1

    .line 2793
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v7, v0

    .line 2794
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    add-int/2addr v0, v1

    int-to-float v9, v0

    int-to-float v10, v2

    sget-object v11, Lorg/telegram/ui/ArticleViewer;->quoteLinePaint:Landroid/graphics/Paint;

    const/4 v8, 0x0

    move-object/from16 v6, p0

    invoke-virtual/range {v6 .. v11}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    return-void

    :cond_2
    :goto_1
    if-eqz v4, :cond_4

    and-int/lit8 v0, v4, 0x1

    if-eqz v0, :cond_3

    mul-int/lit8 v0, v3, 0xe

    add-int/2addr v0, v1

    int-to-float v0, v0

    .line 2801
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v13, v0

    .line 2802
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    add-int/2addr v0, v6

    int-to-float v15, v0

    int-to-float v0, v2

    sget-object v17, Lorg/telegram/ui/ArticleViewer;->quoteLinePaint:Landroid/graphics/Paint;

    const/4 v14, 0x0

    move-object/from16 v12, p0

    move/from16 v16, v0

    invoke-virtual/range {v12 .. v17}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    :cond_3
    ushr-int/lit8 v4, v4, 0x1

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    :cond_4
    :goto_2
    return-void
.end method

.method private drawTextSelection(Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;I)V
    .locals 0

    .line 14569
    invoke-static {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->drawTextSelection(Lorg/telegram/ui/IArticleViewer;Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;I)V

    return-void
.end method

.method public static drawTextSelection(Lorg/telegram/ui/IArticleViewer;Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;)V
    .locals 1

    const/4 v0, 0x0

    .line 14565
    invoke-static {p0, p1, p2, v0}, Lorg/telegram/ui/ArticleViewer;->drawTextSelection(Lorg/telegram/ui/IArticleViewer;Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;I)V

    return-void
.end method

.method public static drawTextSelection(Lorg/telegram/ui/IArticleViewer;Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;I)V
    .locals 1

    .line 14572
    move-object v0, p2

    check-cast v0, Landroid/view/View;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/IArticleViewer;->getTextSelectionHelper(Landroid/view/View;)Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 14574
    invoke-virtual {p0, p1, p2, p3}, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;->draw(Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleSelectableView;I)V

    :cond_0
    return-void
.end method

.method private fixListBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;
    .locals 0

    .line 1857
    instance-of p0, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    if-eqz p0, :cond_0

    .line 1858
    move-object p0, p1

    check-cast p0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    .line 1859
    iput-object p2, p0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    return-object p1

    .line 1861
    :cond_0
    instance-of p0, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    if-eqz p0, :cond_1

    .line 1862
    move-object p0, p1

    check-cast p0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    .line 1863
    iput-object p2, p0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    return-object p1

    :cond_1
    return-object p2
.end method

.method private getBlockCaption(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;
    .locals 3

    const/4 v0, 0x2

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-ne p2, v0, :cond_5

    const/4 p2, 0x0

    .line 1688
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->getBlockCaption(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p2

    .line 1689
    instance-of v0, p2, Lorg/telegram/tgnet/tl/TL_iv$textEmpty;

    if-eqz v0, :cond_0

    move-object p2, v1

    .line 1692
    :cond_0
    invoke-direct {p0, p1, v2}, Lorg/telegram/ui/ArticleViewer;->getBlockCaption(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    .line 1693
    instance-of p1, p0, Lorg/telegram/tgnet/tl/TL_iv$textEmpty;

    if-eqz p1, :cond_1

    move-object p0, v1

    :cond_1
    if-eqz p2, :cond_2

    if-nez p0, :cond_2

    return-object p2

    :cond_2
    if-nez p2, :cond_3

    if-eqz p0, :cond_3

    return-object p0

    :cond_3
    if-eqz p2, :cond_4

    if-eqz p0, :cond_4

    .line 1701
    new-instance p1, Lorg/telegram/tgnet/tl/TL_iv$textPlain;

    invoke-direct {p1}, Lorg/telegram/tgnet/tl/TL_iv$textPlain;-><init>()V

    .line 1702
    const-string v0, " "

    iput-object v0, p1, Lorg/telegram/tgnet/tl/TL_iv$textPlain;->text:Ljava/lang/String;

    .line 1704
    new-instance v0, Lorg/telegram/tgnet/tl/TL_iv$textConcat;

    invoke-direct {v0}, Lorg/telegram/tgnet/tl/TL_iv$textConcat;-><init>()V

    .line 1705
    iget-object v1, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->texts:Ljava/util/ArrayList;

    invoke-virtual {v1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1706
    iget-object p2, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->texts:Ljava/util/ArrayList;

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1707
    iget-object p1, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->texts:Ljava/util/ArrayList;

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_4
    return-object v1

    .line 1713
    :cond_5
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbedPost;

    if-eqz v0, :cond_7

    .line 1714
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbedPost;

    if-nez p2, :cond_6

    .line 1716
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_6
    if-ne p2, v2, :cond_18

    .line 1718
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1720
    :cond_7
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSlideshow;

    if-eqz v0, :cond_9

    .line 1721
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSlideshow;

    if-nez p2, :cond_8

    .line 1723
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_8
    if-ne p2, v2, :cond_18

    .line 1725
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1727
    :cond_9
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    if-eqz v0, :cond_b

    .line 1728
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    if-nez p2, :cond_a

    .line 1730
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_a
    if-ne p2, v2, :cond_18

    .line 1732
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1734
    :cond_b
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCollage;

    if-eqz v0, :cond_d

    .line 1735
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCollage;

    if-nez p2, :cond_c

    .line 1737
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_c
    if-ne p2, v2, :cond_18

    .line 1739
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1741
    :cond_d
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbed;

    if-eqz v0, :cond_f

    .line 1742
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbed;

    if-nez p2, :cond_e

    .line 1744
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_e
    if-ne p2, v2, :cond_18

    .line 1746
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1748
    :cond_f
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;

    if-eqz v0, :cond_10

    .line 1749
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;

    .line 1750
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;->caption:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1751
    :cond_10
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    if-eqz v0, :cond_12

    .line 1752
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    if-nez p2, :cond_11

    .line 1754
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_11
    if-ne p2, v2, :cond_18

    .line 1756
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1758
    :cond_12
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;

    if-eqz v0, :cond_13

    .line 1759
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;

    .line 1760
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;->caption:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1761
    :cond_13
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockAudio;

    if-eqz v0, :cond_15

    .line 1762
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockAudio;

    if-nez p2, :cond_14

    .line 1764
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_14
    if-ne p2, v2, :cond_18

    .line 1766
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 1768
    :cond_15
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCover;

    if-eqz v0, :cond_16

    .line 1769
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCover;

    .line 1770
    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCover;->cover:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->getBlockCaption(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 1771
    :cond_16
    instance-of p0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockMap;

    if-eqz p0, :cond_18

    .line 1772
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockMap;

    if-nez p2, :cond_17

    .line 1774
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_17
    if-ne p2, v2, :cond_18

    .line 1776
    iget-object p0, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    :cond_18
    return-object v1
.end method

.method private getChromeFallbackColor()I
    .locals 1

    .line 14908
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeMonet()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 14909
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result p0

    return p0

    .line 14911
    :cond_0
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result p0

    if-eqz p0, :cond_1

    const p0, -0xe0e0e1

    return p0

    :cond_1
    const/4 p0, -0x1

    return p0
.end method

.method public static getInstance()Lorg/telegram/ui/ArticleViewer;
    .locals 2

    .line 396
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->Instance:Lorg/telegram/ui/ArticleViewer;

    if-nez v0, :cond_1

    .line 398
    const-class v1, Lorg/telegram/ui/ArticleViewer;

    monitor-enter v1

    .line 399
    :try_start_0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->Instance:Lorg/telegram/ui/ArticleViewer;

    if-nez v0, :cond_0

    .line 401
    new-instance v0, Lorg/telegram/ui/ArticleViewer;

    invoke-direct {v0}, Lorg/telegram/ui/ArticleViewer;-><init>()V

    sput-object v0, Lorg/telegram/ui/ArticleViewer;->Instance:Lorg/telegram/ui/ArticleViewer;

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 403
    :cond_0
    :goto_0
    monitor-exit v1

    return-object v0

    :goto_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_1
    return-object v0
.end method

.method private getLastNonListCell(Landroid/view/View;)Landroid/view/View;
    .locals 2

    .line 1783
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$BlockListItemCell;

    if-eqz v0, :cond_0

    .line 1784
    move-object v0, p1

    check-cast v0, Lorg/telegram/ui/ArticleViewer$BlockListItemCell;

    .line 1785
    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$BlockListItemCell;->-$$Nest$fgetblockLayout(Lorg/telegram/ui/ArticleViewer$BlockListItemCell;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 1786
    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$BlockListItemCell;->-$$Nest$fgetblockLayout(Lorg/telegram/ui/ArticleViewer$BlockListItemCell;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object p1

    iget-object p1, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->getLastNonListCell(Landroid/view/View;)Landroid/view/View;

    move-result-object p0

    return-object p0

    .line 1788
    :cond_0
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$BlockOrderedListItemCell;

    if-eqz v0, :cond_1

    .line 1789
    move-object v0, p1

    check-cast v0, Lorg/telegram/ui/ArticleViewer$BlockOrderedListItemCell;

    .line 1790
    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$BlockOrderedListItemCell;->-$$Nest$fgetblockLayout(Lorg/telegram/ui/ArticleViewer$BlockOrderedListItemCell;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 1791
    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$BlockOrderedListItemCell;->-$$Nest$fgetblockLayout(Lorg/telegram/ui/ArticleViewer$BlockOrderedListItemCell;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object p1

    iget-object p1, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->getLastNonListCell(Landroid/view/View;)Landroid/view/View;

    move-result-object p0

    return-object p0

    :cond_1
    return-object p1
.end method

.method private getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;
    .locals 1

    .line 1813
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    if-eqz v0, :cond_1

    .line 1814
    check-cast p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    .line 1815
    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    if-eqz p1, :cond_0

    .line 1816
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p0

    return-object p0

    :cond_0
    return-object p1

    .line 1820
    :cond_1
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    if-eqz v0, :cond_2

    .line 1821
    check-cast p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    .line 1822
    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    if-eqz p1, :cond_2

    .line 1823
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p0

    return-object p0

    :cond_2
    return-object p1
.end method

.method public static getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;
    .locals 1

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 2857
    :cond_0
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    if-eqz v0, :cond_1

    .line 2858
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2859
    :cond_1
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    if-eqz v0, :cond_2

    .line 2860
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2861
    :cond_2
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    if-eqz v0, :cond_3

    .line 2862
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2863
    :cond_3
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    if-eqz v0, :cond_4

    .line 2864
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2865
    :cond_4
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    if-eqz v0, :cond_5

    .line 2866
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2867
    :cond_5
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    if-eqz v0, :cond_6

    .line 2868
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2869
    :cond_6
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    if-eqz v0, :cond_7

    .line 2870
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2871
    :cond_7
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;

    if-eqz v0, :cond_8

    .line 2872
    move-object v0, p0

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;

    iget-object v0, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    return-object p0

    .line 2873
    :cond_8
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textSubscript;

    if-eqz v0, :cond_9

    .line 2874
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textSubscript;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2875
    :cond_9
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textSuperscript;

    if-eqz v0, :cond_a

    .line 2876
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textSuperscript;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2877
    :cond_a
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textMarked;

    if-eqz v0, :cond_b

    .line 2878
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textMarked;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2879
    :cond_b
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    if-eqz v0, :cond_c

    .line 2880
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    return-object p0

    .line 2881
    :cond_c
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textSpoiler;

    if-eqz v0, :cond_d

    .line 2882
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textSpoiler;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p0

    :cond_d
    return-object p0
.end method

.method public static getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;
    .locals 4

    .line 3089
    const-string v0, ""

    if-nez p0, :cond_0

    return-object v0

    .line 3092
    :cond_0
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    if-eqz v1, :cond_1

    .line 3093
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3094
    :cond_1
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    if-eqz v1, :cond_2

    .line 3095
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3096
    :cond_2
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    if-eqz v1, :cond_3

    .line 3097
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3098
    :cond_3
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    if-eqz v1, :cond_4

    .line 3099
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3100
    :cond_4
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    if-eqz v1, :cond_5

    .line 3101
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3102
    :cond_5
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    if-eqz v1, :cond_6

    .line 3103
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3104
    :cond_6
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    if-eqz v1, :cond_7

    .line 3105
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3106
    :cond_7
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textPlain;

    if-eqz v1, :cond_8

    .line 3107
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textPlain;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$textPlain;->text:Ljava/lang/String;

    return-object p0

    .line 3108
    :cond_8
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;

    if-eqz v1, :cond_9

    .line 3109
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3110
    :cond_9
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textEmpty;

    if-eqz v1, :cond_a

    return-object v0

    .line 3112
    :cond_a
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textConcat;

    if-eqz v1, :cond_c

    .line 3113
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 3114
    iget-object v1, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->texts:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_b

    .line 3116
    iget-object v3, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->texts:Ljava/util/ArrayList;

    invoke-virtual {v3, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_b
    return-object v0

    .line 3119
    :cond_c
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textSubscript;

    if-eqz v1, :cond_d

    .line 3120
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textSubscript;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3121
    :cond_d
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textSuperscript;

    if-eqz v1, :cond_e

    .line 3122
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textSuperscript;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3123
    :cond_e
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textMarked;

    if-eqz v1, :cond_f

    .line 3124
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textMarked;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 3125
    :cond_f
    instance-of v1, p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    if-eqz v1, :cond_10

    .line 3126
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    :cond_10
    return-object v0
.end method

.method private getText(Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;
    .locals 0

    .line 2895
    invoke-static/range {p0 .. p6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method private getText(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;
    .locals 0

    .line 2888
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p1

    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method public static getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;
    .locals 18

    move-object/from16 v7, p4

    const/4 v8, 0x0

    if-nez v7, :cond_0

    return-object v8

    .line 2909
    :cond_0
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    if-eqz v0, :cond_1

    .line 2910
    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0

    .line 2911
    :cond_1
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    if-eqz v0, :cond_2

    .line 2912
    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0

    .line 2913
    :cond_2
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    if-eqz v0, :cond_3

    .line 2914
    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0

    .line 2915
    :cond_3
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    if-eqz v0, :cond_4

    .line 2916
    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0

    .line 2917
    :cond_4
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    if-eqz v0, :cond_5

    .line 2918
    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0

    .line 2919
    :cond_5
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    const-class v9, Landroid/text/style/MetricAffectingSpan;

    const/16 v10, 0x21

    const/4 v11, 0x0

    if-eqz v0, :cond_9

    .line 2920
    new-instance v12, Landroid/text/SpannableStringBuilder;

    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-direct {v12, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 2921
    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v12, v11, v1, v9}, Landroid/text/SpannableStringBuilder;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/text/style/MetricAffectingSpan;

    .line 2922
    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v2

    if-eqz v2, :cond_8

    .line 2923
    new-instance v2, Lorg/telegram/ui/Components/TextPaintUrlSpan;

    if-eqz v1, :cond_6

    array-length v1, v1

    if-nez v1, :cond_7

    :cond_6
    invoke-static {v0, v3, v7, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v8

    :cond_7
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "mailto:"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-static {v7}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v8, v0}, Lorg/telegram/ui/Components/TextPaintUrlSpan;-><init>(Landroid/text/TextPaint;Ljava/lang/String;)V

    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    invoke-virtual {v12, v2, v11, v0, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_8
    return-object v12

    :cond_9
    move-object/from16 v0, p0

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    .line 2926
    instance-of v1, v7, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    const-wide/16 v12, 0x0

    if-eqz v1, :cond_e

    .line 2927
    move-object v14, v7

    check-cast v14, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    .line 2928
    new-instance v15, Landroid/text/SpannableStringBuilder;

    iget-object v4, v14, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-direct {v15, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 2929
    invoke-virtual {v15}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v15, v11, v1, v9}, Landroid/text/SpannableStringBuilder;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/text/style/MetricAffectingSpan;

    if-eqz v1, :cond_a

    .line 2930
    array-length v1, v1

    if-nez v1, :cond_b

    :cond_a
    invoke-static {v0, v3, v7, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v8

    .line 2932
    :cond_b
    iget-wide v0, v14, Lorg/telegram/tgnet/tl/TL_iv$RichText;->webpage_id:J

    cmp-long v0, v0, v12

    if-eqz v0, :cond_c

    .line 2933
    new-instance v0, Lorg/telegram/ui/Components/TextPaintWebpageUrlSpan;

    invoke-static {v7}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v8, v1}, Lorg/telegram/ui/Components/TextPaintWebpageUrlSpan;-><init>(Landroid/text/TextPaint;Ljava/lang/String;)V

    goto :goto_0

    .line 2935
    :cond_c
    new-instance v0, Lorg/telegram/ui/Components/TextPaintUrlSpan;

    invoke-static {v7}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v8, v1}, Lorg/telegram/ui/Components/TextPaintUrlSpan;-><init>(Landroid/text/TextPaint;Ljava/lang/String;)V

    .line 2937
    :goto_0
    invoke-virtual {v15}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    if-eqz v1, :cond_d

    .line 2938
    invoke-virtual {v15}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v15, v0, v11, v1, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_d
    return-object v15

    .line 2941
    :cond_e
    instance-of v1, v7, Lorg/telegram/tgnet/tl/TL_iv$textPlain;

    if-eqz v1, :cond_f

    .line 2942
    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textPlain;

    iget-object v0, v0, Lorg/telegram/tgnet/tl/TL_iv$textPlain;->text:Ljava/lang/String;

    return-object v0

    .line 2943
    :cond_f
    instance-of v1, v7, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;

    if-eqz v1, :cond_10

    .line 2944
    check-cast v7, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;

    .line 2945
    new-instance v8, Landroid/text/SpannableStringBuilder;

    iget-object v4, v7, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-direct {v8, v0}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 2946
    new-instance v0, Lorg/telegram/ui/Components/AnchorSpan;

    iget-object v1, v7, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;->name:Ljava/lang/String;

    invoke-direct {v0, v1}, Lorg/telegram/ui/Components/AnchorSpan;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    const/16 v2, 0x11

    invoke-virtual {v8, v0, v11, v1, v2}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    return-object v8

    .line 2948
    :cond_10
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textEmpty;

    const-string v1, ""

    if-eqz v0, :cond_11

    return-object v1

    .line 2950
    :cond_11
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textConcat;

    const/4 v14, 0x1

    if-eqz v0, :cond_1b

    .line 2951
    new-instance v8, Landroid/text/SpannableStringBuilder;

    invoke-direct {v8}, Landroid/text/SpannableStringBuilder;-><init>()V

    .line 2952
    iget-object v0, v7, Lorg/telegram/tgnet/tl/TL_iv$RichText;->texts:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v9

    move v15, v11

    :goto_1
    if-ge v15, v9, :cond_1a

    .line 2954
    iget-object v0, v7, Lorg/telegram/tgnet/tl/TL_iv$RichText;->texts:Ljava/util/ArrayList;

    invoke-virtual {v0, v15}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    move-object v4, v0

    check-cast v4, Lorg/telegram/tgnet/tl/TL_iv$RichText;

    .line 2955
    invoke-static {v4}, Lorg/telegram/ui/ArticleViewer;->getLastRichText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object v0

    if-ltz p6, :cond_12

    .line 2956
    instance-of v1, v4, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    if-eqz v1, :cond_12

    move-object v1, v4

    check-cast v1, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    iget-wide v1, v1, Lorg/telegram/tgnet/tl/TL_iv$RichText;->webpage_id:J

    cmp-long v1, v1, v12

    if-eqz v1, :cond_12

    move/from16 v16, v14

    goto :goto_2

    :cond_12
    move/from16 v16, v11

    .line 2957
    :goto_2
    const-string v1, " "

    if-eqz v16, :cond_13

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v2

    if-eqz v2, :cond_13

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v2

    sub-int/2addr v2, v14

    invoke-virtual {v8, v2}, Landroid/text/SpannableStringBuilder;->charAt(I)C

    move-result v2

    const/16 v3, 0xa

    if-eq v2, v3, :cond_13

    .line 2958
    invoke-virtual {v8, v1}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 2959
    new-instance v2, Lorg/telegram/ui/Cells/TextSelectionHelper$IgnoreCopySpannable;

    invoke-direct {v2}, Lorg/telegram/ui/Cells/TextSelectionHelper$IgnoreCopySpannable;-><init>()V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v3

    sub-int/2addr v3, v14

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v5

    invoke-virtual {v8, v2, v3, v5, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_13
    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    move-object/from16 v17, v0

    move-object v12, v1

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 2962
    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v13

    .line 2963
    invoke-static/range {v17 .. v17}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result v1

    .line 2964
    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v2

    .line 2965
    invoke-virtual {v8, v13}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    if-eqz v1, :cond_18

    .line 2966
    instance-of v6, v13, Landroid/text/SpannableStringBuilder;

    if-nez v6, :cond_18

    and-int/lit8 v6, v1, 0x8

    if-nez v6, :cond_14

    and-int/lit16 v6, v1, 0x200

    if-eqz v6, :cond_15

    :cond_14
    move-object/from16 v6, v17

    goto :goto_3

    .line 2982
    :cond_15
    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    if-eq v2, v1, :cond_18

    .line 2983
    new-instance v1, Lorg/telegram/ui/Components/TextPaintSpan;

    move-object/from16 v6, v17

    invoke-static {v0, v3, v6, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v4

    invoke-direct {v1, v4}, Lorg/telegram/ui/Components/TextPaintSpan;-><init>(Landroid/text/TextPaint;)V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v4

    invoke-virtual {v8, v1, v2, v4, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    goto :goto_5

    .line 2968
    :goto_3
    invoke-static {v4}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object v4

    if-nez v4, :cond_16

    .line 2970
    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object v4

    :cond_16
    and-int/lit16 v1, v1, 0x200

    if-eqz v1, :cond_17

    .line 2974
    new-instance v1, Lorg/telegram/ui/Components/TextPaintWebpageUrlSpan;

    invoke-static {v0, v3, v6, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v6

    invoke-direct {v1, v6, v4}, Lorg/telegram/ui/Components/TextPaintWebpageUrlSpan;-><init>(Landroid/text/TextPaint;Ljava/lang/String;)V

    goto :goto_4

    .line 2976
    :cond_17
    new-instance v1, Lorg/telegram/ui/Components/TextPaintUrlSpan;

    invoke-static {v0, v3, v6, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v6

    invoke-direct {v1, v6, v4}, Lorg/telegram/ui/Components/TextPaintUrlSpan;-><init>(Landroid/text/TextPaint;Ljava/lang/String;)V

    .line 2978
    :goto_4
    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v4

    if-eq v2, v4, :cond_18

    .line 2979
    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v4

    invoke-virtual {v8, v1, v2, v4, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_18
    :goto_5
    if-eqz v16, :cond_19

    add-int/lit8 v1, v9, -0x1

    if-eq v15, v1, :cond_19

    .line 2988
    invoke-virtual {v8, v12}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 2989
    new-instance v1, Lorg/telegram/ui/Cells/TextSelectionHelper$IgnoreCopySpannable;

    invoke-direct {v1}, Lorg/telegram/ui/Cells/TextSelectionHelper$IgnoreCopySpannable;-><init>()V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v2

    sub-int/2addr v2, v14

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v4

    invoke-virtual {v8, v1, v2, v4, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_19
    add-int/lit8 v15, v15, 0x1

    const-wide/16 v12, 0x0

    goto/16 :goto_1

    :cond_1a
    return-object v8

    :cond_1b
    move-object/from16 v0, p0

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    .line 2993
    instance-of v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textSubscript;

    if-eqz v2, :cond_1c

    .line 2994
    move-object v1, v7

    check-cast v1, Lorg/telegram/tgnet/tl/TL_iv$textSubscript;

    iget-object v4, v1, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0

    .line 2995
    :cond_1c
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textSuperscript;

    if-eqz v0, :cond_1d

    .line 2996
    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textSuperscript;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0

    .line 2997
    :cond_1d
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textMarked;

    if-eqz v0, :cond_21

    .line 2998
    new-instance v12, Landroid/text/SpannableStringBuilder;

    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textMarked;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-direct {v12, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 2999
    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v12, v11, v1, v9}, Landroid/text/SpannableStringBuilder;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/text/style/MetricAffectingSpan;

    .line 3000
    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v2

    if-eqz v2, :cond_20

    .line 3001
    new-instance v2, Lorg/telegram/ui/Components/TextPaintMarkSpan;

    if-eqz v1, :cond_1e

    array-length v1, v1

    if-nez v1, :cond_1f

    :cond_1e
    invoke-static {v0, v3, v7, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v8

    :cond_1f
    invoke-direct {v2, v8}, Lorg/telegram/ui/Components/TextPaintMarkSpan;-><init>(Landroid/text/TextPaint;)V

    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    invoke-virtual {v12, v2, v11, v0, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_20
    return-object v12

    :cond_21
    move-object/from16 v0, p0

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    .line 3004
    instance-of v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textSpoiler;

    if-eqz v2, :cond_23

    .line 3005
    new-instance v8, Landroid/text/SpannableStringBuilder;

    move-object v1, v7

    check-cast v1, Lorg/telegram/tgnet/tl/TL_iv$textSpoiler;

    iget-object v4, v1, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-direct {v8, v0}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 3006
    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    if-eqz v0, :cond_22

    .line 3007
    new-instance v0, Lorg/telegram/ui/Components/TextStyleSpan$TextStyleRun;

    invoke-direct {v0}, Lorg/telegram/ui/Components/TextStyleSpan$TextStyleRun;-><init>()V

    .line 3008
    iget v1, v0, Lorg/telegram/ui/Components/TextStyleSpan$TextStyleRun;->flags:I

    or-int/lit16 v1, v1, 0x100

    iput v1, v0, Lorg/telegram/ui/Components/TextStyleSpan$TextStyleRun;->flags:I

    .line 3009
    new-instance v1, Lorg/telegram/ui/Components/TextStyleSpan;

    invoke-direct {v1, v0}, Lorg/telegram/ui/Components/TextStyleSpan;-><init>(Lorg/telegram/ui/Components/TextStyleSpan$TextStyleRun;)V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    invoke-virtual {v8, v1, v11, v0, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_22
    return-object v8

    .line 3012
    :cond_23
    instance-of v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    if-eqz v0, :cond_27

    .line 3013
    new-instance v12, Landroid/text/SpannableStringBuilder;

    move-object v0, v7

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    iget-object v4, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v5, p5

    move/from16 v6, p6

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-direct {v12, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 3014
    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v12, v11, v1, v9}, Landroid/text/SpannableStringBuilder;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/text/style/MetricAffectingSpan;

    .line 3015
    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v2

    if-eqz v2, :cond_26

    .line 3016
    new-instance v2, Lorg/telegram/ui/Components/TextPaintUrlSpan;

    if-eqz v1, :cond_24

    array-length v1, v1

    if-nez v1, :cond_25

    :cond_24
    invoke-static {v0, v3, v7, v5}, Lorg/telegram/ui/ArticleViewer;->getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;

    move-result-object v8

    :cond_25
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "tel:"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-static {v7}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v8, v0}, Lorg/telegram/ui/Components/TextPaintUrlSpan;-><init>(Landroid/text/TextPaint;Ljava/lang/String;)V

    invoke-virtual {v12}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    invoke-virtual {v12, v2, v11, v0, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_26
    return-object v12

    :cond_27
    move-object/from16 v0, p0

    move-object/from16 v3, p1

    .line 3019
    instance-of v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textImage;

    if-eqz v2, :cond_2f

    .line 3020
    move-object v2, v7

    check-cast v2, Lorg/telegram/tgnet/tl/TL_iv$textImage;

    .line 3021
    iget-wide v4, v2, Lorg/telegram/tgnet/tl/TL_iv$textImage;->document_id:J

    invoke-static {v3, v4, v5}, Lorg/telegram/ui/ArticleViewer$WebPageUtils;->getDocumentWithId(Lorg/telegram/tgnet/TLRPC$WebPage;J)Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v4

    .line 3022
    iget-wide v5, v2, Lorg/telegram/tgnet/tl/TL_iv$textImage;->photo_id:J

    invoke-static {v3, v5, v6}, Lorg/telegram/ui/ArticleViewer$WebPageUtils;->getPhotoWithId(Lorg/telegram/tgnet/TLRPC$WebPage;J)Lorg/telegram/tgnet/TLRPC$Photo;

    move-result-object v5

    .line 3023
    const-string v6, "*"

    if-eqz v4, :cond_2b

    .line 3024
    new-instance v8, Landroid/text/SpannableStringBuilder;

    invoke-direct {v8, v6}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 3025
    iget v1, v2, Lorg/telegram/tgnet/tl/TL_iv$textImage;->w:I

    int-to-float v1, v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    .line 3026
    iget v2, v2, Lorg/telegram/tgnet/tl/TL_iv$textImage;->h:I

    int-to-float v2, v2

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3027
    invoke-static/range {p6 .. p6}, Ljava/lang/Math;->abs(I)I

    move-result v5

    if-le v1, v5, :cond_28

    int-to-float v6, v5

    int-to-float v1, v1

    div-float/2addr v6, v1

    int-to-float v1, v2

    mul-float/2addr v1, v6

    float-to-int v2, v1

    move v1, v5

    :cond_28
    move v5, v2

    if-eqz p2, :cond_2a

    .line 3034
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_iv_background:I

    invoke-virtual {v0, v2}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->computePerceivedBrightness(I)F

    move-result v0

    const v2, 0x3f347ae1    # 0.705f

    cmpg-float v0, v0, v2

    if-gtz v0, :cond_29

    move v7, v14

    goto :goto_6

    :cond_29
    move v7, v11

    .line 3035
    :goto_6
    new-instance v0, Lorg/telegram/ui/Components/TextPaintImageReceiverSpan;

    const/4 v6, 0x0

    move-object v2, v4

    move v4, v1

    move-object/from16 v1, p2

    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/Components/TextPaintImageReceiverSpan;-><init>(Landroid/view/View;Lorg/telegram/tgnet/TLRPC$Document;Ljava/lang/Object;IIZZ)V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v8, v0, v11, v1, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_2a
    return-object v8

    .line 3038
    :cond_2b
    instance-of v0, v5, Lorg/telegram/ui/web/WebInstantView$WebPhoto;

    if-eqz v0, :cond_2e

    .line 3039
    check-cast v5, Lorg/telegram/ui/web/WebInstantView$WebPhoto;

    .line 3040
    new-instance v8, Landroid/text/SpannableStringBuilder;

    invoke-direct {v8, v6}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 3041
    iget v0, v2, Lorg/telegram/tgnet/tl/TL_iv$textImage;->w:I

    int-to-float v0, v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    .line 3042
    iget v1, v2, Lorg/telegram/tgnet/tl/TL_iv$textImage;->h:I

    int-to-float v1, v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    .line 3043
    invoke-static/range {p6 .. p6}, Ljava/lang/Math;->abs(I)I

    move-result v2

    if-le v0, v2, :cond_2c

    int-to-float v3, v2

    int-to-float v0, v0

    div-float/2addr v3, v0

    int-to-float v0, v1

    mul-float/2addr v0, v3

    float-to-int v1, v0

    move v4, v2

    goto :goto_7

    :cond_2c
    move v4, v0

    :goto_7
    if-eqz p2, :cond_2d

    .line 3050
    new-instance v0, Lorg/telegram/ui/Components/TextPaintImageReceiverSpan;

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object/from16 v3, p1

    move-object v2, v5

    move v5, v1

    move-object/from16 v1, p2

    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/Components/TextPaintImageReceiverSpan;-><init>(Landroid/view/View;Lorg/telegram/ui/web/WebInstantView$WebPhoto;Ljava/lang/Object;IIZZ)V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v8, v0, v11, v1, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_2d
    return-object v8

    :cond_2e
    return-object v1

    .line 3056
    :cond_2f
    instance-of v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;

    if-eqz v2, :cond_37

    .line 3057
    check-cast v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;

    .line 3058
    iget-object v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->bitmap:Landroid/graphics/Bitmap;

    if-nez v2, :cond_30

    iget-boolean v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->tried:Z

    if-nez v2, :cond_30

    .line 3059
    iput-boolean v14, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->tried:Z

    .line 3060
    iget-object v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->source:Ljava/lang/String;

    const/high16 v3, 0x41a00000    # 20.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-static {v2, v3, v14}, Lorg/telegram/ui/iv/Latex;->render(Ljava/lang/String;FZ)Lorg/telegram/ui/iv/Latex;

    move-result-object v2

    if-eqz v2, :cond_30

    .line 3062
    iget v3, v2, Lorg/telegram/ui/iv/Latex;->width:I

    iput v3, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->w:I

    .line 3063
    iget v3, v2, Lorg/telegram/ui/iv/Latex;->height:I

    iput v3, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->h:I

    .line 3064
    iget v3, v2, Lorg/telegram/ui/iv/Latex;->depth:I

    iput v3, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->depth:I

    .line 3065
    iget-object v2, v2, Lorg/telegram/ui/iv/Latex;->bitmap:Landroid/graphics/Bitmap;

    iput-object v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->bitmap:Landroid/graphics/Bitmap;

    .line 3068
    :cond_30
    iget-object v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->bitmap:Landroid/graphics/Bitmap;

    if-nez v2, :cond_32

    .line 3069
    iget-object v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->source:Ljava/lang/String;

    if-nez v0, :cond_31

    return-object v1

    :cond_31
    return-object v0

    .line 3071
    :cond_32
    new-instance v8, Landroid/text/SpannableStringBuilder;

    const-string/jumbo v1, "\ufffc"

    invoke-direct {v8, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 3072
    invoke-static/range {p6 .. p6}, Ljava/lang/Math;->abs(I)I

    move-result v1

    if-lez v1, :cond_33

    .line 3073
    iget v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->w:I

    if-le v2, v1, :cond_33

    goto :goto_8

    :cond_33
    move v14, v11

    :goto_8
    if-eqz v14, :cond_34

    :goto_9
    move v3, v1

    goto :goto_a

    .line 3074
    :cond_34
    iget v1, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->w:I

    goto :goto_9

    .line 3075
    :goto_a
    new-instance v1, Lorg/telegram/ui/Components/TextPaintImageReceiverSpan;

    iget-object v2, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->bitmap:Landroid/graphics/Bitmap;

    iget v4, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->h:I

    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {v0, v5}, Lorg/telegram/ui/IArticleViewer;->getThemedColor(I)I

    move-result v5

    iget v6, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->depth:I

    move-object v0, v1

    move-object/from16 v1, p2

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/Components/TextPaintImageReceiverSpan;-><init>(Landroid/view/View;Landroid/graphics/Bitmap;IIII)V

    if-eqz v14, :cond_35

    .line 3077
    iget v1, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->w:I

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/TextPaintImageReceiverSpan;->setScrollableContentWidth(I)V

    .line 3079
    :cond_35
    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v8, v0, v11, v1, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    .line 3080
    iget-object v0, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->source:Ljava/lang/String;

    if-eqz v0, :cond_36

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_36

    .line 3081
    new-instance v0, Lorg/telegram/ui/Cells/TextSelectionHelper$ReplaceCopyTextSpannable;

    iget-object v1, v7, Lorg/telegram/tgnet/tl/TL_iv$textMath;->source:Ljava/lang/String;

    invoke-direct {v0, v1}, Lorg/telegram/ui/Cells/TextSelectionHelper$ReplaceCopyTextSpannable;-><init>(Ljava/lang/CharSequence;)V

    invoke-virtual {v8}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {v8, v0, v11, v1, v10}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :cond_36
    return-object v8

    .line 3085
    :cond_37
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "not supported "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;
    .locals 7

    if-eqz p1, :cond_0

    .line 2891
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p1

    :goto_0
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move v6, p6

    goto :goto_1

    :cond_0
    const/4 p1, 0x0

    goto :goto_0

    :goto_1
    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method public static getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I
    .locals 4

    .line 2820
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    if-eqz v0, :cond_0

    .line 2821
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x4

    return p0

    .line 2822
    :cond_0
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    if-eqz v0, :cond_1

    .line 2823
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x2

    return p0

    .line 2824
    :cond_1
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    if-eqz v0, :cond_2

    .line 2825
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x1

    return p0

    .line 2826
    :cond_2
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    if-eqz v0, :cond_3

    .line 2827
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x10

    return p0

    .line 2828
    :cond_3
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    if-eqz v0, :cond_4

    .line 2829
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x20

    return p0

    .line 2830
    :cond_4
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    if-eqz v0, :cond_5

    .line 2831
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x8

    return p0

    .line 2832
    :cond_5
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    if-eqz v0, :cond_6

    .line 2833
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x8

    return p0

    .line 2834
    :cond_6
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    if-eqz v0, :cond_8

    .line 2835
    move-object v0, p0

    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    .line 2836
    iget-wide v0, v0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->webpage_id:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    .line 2839
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-eqz v0, :cond_7

    .line 2837
    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit16 p0, p0, 0x200

    return p0

    .line 2839
    :cond_7
    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x8

    return p0

    .line 2841
    :cond_8
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textSubscript;

    if-eqz v0, :cond_9

    .line 2842
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit16 p0, p0, 0x80

    return p0

    .line 2843
    :cond_9
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textSuperscript;

    if-eqz v0, :cond_a

    .line 2844
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit16 p0, p0, 0x100

    return p0

    .line 2845
    :cond_a
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textMarked;

    if-eqz v0, :cond_b

    .line 2846
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    or-int/lit8 p0, p0, 0x40

    return p0

    :cond_b
    if-eqz p0, :cond_c

    .line 2848
    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->parentRichText:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result p0

    return p0

    :cond_c
    const/4 p0, 0x0

    return p0
.end method

.method public static getTextPaint(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Landroid/text/TextPaint;
    .locals 11

    .line 3190
    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer;->getTextFlags(Lorg/telegram/tgnet/tl/TL_iv$RichText;)I

    move-result v0

    const/high16 v1, 0x41600000    # 14.0f

    .line 3192
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3194
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getResources()Lorg/telegram/ui/ArticleViewer$Resources;

    move-result-object v3

    .line 3196
    iget-boolean v4, v3, Lorg/telegram/ui/ArticleViewer$Resources;->isRichMessage:Z

    if-eqz v4, :cond_0

    sget v4, Lorg/telegram/messenger/SharedConfig;->fontSize:I

    goto :goto_0

    :cond_0
    sget v4, Lorg/telegram/messenger/SharedConfig;->ivFontSize:I

    :goto_0
    add-int/lit8 v4, v4, -0x10

    int-to-float v4, v4

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    .line 3198
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    const/high16 v6, 0x41400000    # 12.0f

    const/high16 v7, -0x10000

    if-eqz v5, :cond_3

    .line 3199
    move-object v2, p3

    check-cast v2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    .line 3200
    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-eq v2, p2, :cond_2

    if-ne v2, p1, :cond_1

    goto :goto_2

    .line 3204
    :cond_1
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCreditTextPaints:Landroid/util/SparseArray;

    .line 3205
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    :goto_1
    move v2, p2

    goto :goto_3

    .line 3201
    :cond_2
    :goto_2
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3202
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    goto :goto_1

    .line 3207
    :goto_3
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3208
    :cond_3
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockMap;

    if-eqz v5, :cond_6

    .line 3209
    move-object v2, p3

    check-cast v2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockMap;

    .line 3210
    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-eq v2, p2, :cond_5

    if-ne v2, p1, :cond_4

    goto :goto_5

    .line 3214
    :cond_4
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCreditTextPaints:Landroid/util/SparseArray;

    .line 3215
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    :goto_4
    move v2, p2

    goto :goto_6

    .line 3211
    :cond_5
    :goto_5
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3212
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    goto :goto_4

    .line 3217
    :goto_6
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3218
    :cond_6
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockTitle;

    if-eqz v5, :cond_7

    .line 3219
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->titleTextPaints:Landroid/util/SparseArray;

    const/high16 p2, 0x41b80000    # 23.0f

    .line 3220
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3221
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3222
    :cond_7
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockKicker;

    if-eqz v5, :cond_8

    .line 3223
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->kickerTextPaints:Landroid/util/SparseArray;

    .line 3224
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3225
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3226
    :cond_8
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockAuthorDate;

    if-eqz v5, :cond_9

    .line 3227
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->authorTextPaints:Landroid/util/SparseArray;

    .line 3228
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3229
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3230
    :cond_9
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockFooter;

    if-eqz v5, :cond_a

    .line 3231
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->footerTextPaints:Landroid/util/SparseArray;

    .line 3232
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3233
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3234
    :cond_a
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSubtitle;

    const/high16 v8, 0x41a00000    # 20.0f

    if-eqz v5, :cond_b

    .line 3235
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->subtitleTextPaints:Landroid/util/SparseArray;

    .line 3236
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3237
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3238
    :cond_b
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeader;

    if-eqz v5, :cond_c

    .line 3239
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->headerTextPaints:Landroid/util/SparseArray;

    .line 3240
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3241
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3242
    :cond_c
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSubheader;

    if-eqz v5, :cond_d

    .line 3243
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->subheaderTextPaints:Landroid/util/SparseArray;

    const/high16 p2, 0x41880000    # 17.0f

    .line 3244
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3245
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3246
    :cond_d
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading1;

    if-eqz v5, :cond_e

    .line 3247
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->heading1TextPaints:Landroid/util/SparseArray;

    const/high16 p2, 0x41900000    # 18.0f

    .line 3248
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3249
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3250
    :cond_e
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading2;

    const/high16 v8, 0x41800000    # 16.0f

    if-eqz v5, :cond_f

    .line 3251
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->heading2TextPaints:Landroid/util/SparseArray;

    .line 3252
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3253
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3254
    :cond_f
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading3;

    const/high16 v9, 0x41700000    # 15.0f

    if-eqz v5, :cond_10

    .line 3255
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->heading3TextPaints:Landroid/util/SparseArray;

    .line 3256
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3257
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3258
    :cond_10
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading4;

    if-eqz v5, :cond_11

    .line 3259
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->heading4TextPaints:Landroid/util/SparseArray;

    .line 3260
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3261
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3262
    :cond_11
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading5;

    if-eqz v5, :cond_12

    .line 3263
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->heading5TextPaints:Landroid/util/SparseArray;

    const/high16 p2, 0x41500000    # 13.0f

    .line 3264
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3265
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3266
    :cond_12
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading6;

    if-eqz v5, :cond_13

    .line 3267
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->heading6TextPaints:Landroid/util/SparseArray;

    .line 3268
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3269
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3270
    :cond_13
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;

    const/4 v10, 0x0

    if-eqz v5, :cond_15

    .line 3271
    move-object p2, p3

    check-cast p2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;

    .line 3272
    iget-object v5, p2, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne v5, p1, :cond_14

    .line 3273
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->quoteTextPaints:Landroid/util/SparseArray;

    .line 3274
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3275
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3276
    :cond_14
    iget-object p2, p2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;->caption:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne p2, p1, :cond_2d

    .line 3277
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3278
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3279
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3281
    :cond_15
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;

    if-eqz v5, :cond_17

    .line 3282
    move-object p2, p3

    check-cast p2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;

    .line 3283
    iget-object v5, p2, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne v5, p1, :cond_16

    .line 3284
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->quoteTextPaints:Landroid/util/SparseArray;

    .line 3285
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3286
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3287
    :cond_16
    iget-object p2, p2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPullquote;->caption:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne p2, p1, :cond_2d

    .line 3288
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3289
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3290
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3292
    :cond_17
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPreformatted;

    if-eqz v5, :cond_18

    .line 3293
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->preformattedTextPaints:Landroid/util/SparseArray;

    .line 3294
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3295
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    or-int/lit8 v0, v0, 0x4

    goto/16 :goto_14

    .line 3297
    :cond_18
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockParagraph;

    if-eqz v5, :cond_19

    .line 3298
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->paragraphTextPaints:Landroid/util/SparseArray;

    .line 3299
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3300
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3301
    :cond_19
    invoke-static {p3}, Lorg/telegram/ui/ArticleViewer;->isListItemBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    move-result v5

    if-eqz v5, :cond_1a

    .line 3302
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->listTextPaints:Landroid/util/SparseArray;

    .line 3303
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3304
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3305
    :cond_1a
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbed;

    if-eqz v5, :cond_1d

    .line 3306
    move-object v2, p3

    check-cast v2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbed;

    .line 3307
    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-eq v2, p2, :cond_1c

    if-ne v2, p1, :cond_1b

    goto :goto_8

    .line 3311
    :cond_1b
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCreditTextPaints:Landroid/util/SparseArray;

    .line 3312
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    :goto_7
    move v2, p2

    goto :goto_9

    .line 3308
    :cond_1c
    :goto_8
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3309
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    goto :goto_7

    .line 3314
    :goto_9
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3315
    :cond_1d
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSlideshow;

    if-eqz v5, :cond_20

    .line 3316
    move-object v2, p3

    check-cast v2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSlideshow;

    .line 3317
    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-eq v2, p2, :cond_1f

    if-ne v2, p1, :cond_1e

    goto :goto_b

    .line 3321
    :cond_1e
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCreditTextPaints:Landroid/util/SparseArray;

    .line 3322
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    :goto_a
    move v2, p2

    goto :goto_c

    .line 3318
    :cond_1f
    :goto_b
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3319
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    goto :goto_a

    .line 3324
    :goto_c
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3325
    :cond_20
    instance-of v5, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCollage;

    if-eqz v5, :cond_23

    .line 3326
    move-object v2, p3

    check-cast v2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCollage;

    .line 3327
    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-eq v2, p2, :cond_22

    if-ne v2, p1, :cond_21

    goto :goto_e

    .line 3331
    :cond_21
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCreditTextPaints:Landroid/util/SparseArray;

    .line 3332
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    :goto_d
    move v2, p2

    goto :goto_f

    .line 3328
    :cond_22
    :goto_e
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3329
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    goto :goto_d

    .line 3334
    :goto_f
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3335
    :cond_23
    instance-of p1, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbedPost;

    if-eqz p1, :cond_26

    .line 3336
    move-object p1, p3

    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockEmbedPost;

    .line 3337
    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object v5, p1, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne p2, v5, :cond_24

    .line 3338
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCaptionTextPaints:Landroid/util/SparseArray;

    .line 3339
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3340
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3341
    :cond_24
    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->credit:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne p2, p1, :cond_25

    .line 3342
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->photoCreditTextPaints:Landroid/util/SparseArray;

    .line 3343
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3344
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto/16 :goto_14

    :cond_25
    if-eqz p2, :cond_2d

    .line 3346
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->embedPostTextPaints:Landroid/util/SparseArray;

    .line 3347
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3348
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto/16 :goto_14

    .line 3350
    :cond_26
    instance-of p1, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    if-eqz p1, :cond_28

    .line 3351
    move-object p1, p3

    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    .line 3352
    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne p2, p1, :cond_27

    .line 3353
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->mediaCaptionTextPaints:Landroid/util/SparseArray;

    .line 3354
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    :goto_10
    move v2, p2

    goto :goto_11

    .line 3356
    :cond_27
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->mediaCreditTextPaints:Landroid/util/SparseArray;

    .line 3357
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    goto :goto_10

    .line 3359
    :goto_11
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto :goto_14

    .line 3360
    :cond_28
    instance-of p1, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockAudio;

    if-eqz p1, :cond_2a

    .line 3361
    move-object p1, p3

    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockAudio;

    .line 3362
    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->caption:Lorg/telegram/tgnet/tl/TL_iv$PageCaption;

    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$PageCaption;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    if-ne p2, p1, :cond_29

    .line 3363
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->mediaCaptionTextPaints:Landroid/util/SparseArray;

    .line 3364
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    :goto_12
    move v2, p2

    goto :goto_13

    .line 3366
    :cond_29
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->mediaCreditTextPaints:Landroid/util/SparseArray;

    .line 3367
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    goto :goto_12

    .line 3369
    :goto_13
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto :goto_14

    .line 3370
    :cond_2a
    instance-of p1, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;

    if-eqz p1, :cond_2b

    .line 3371
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->relatedArticleTextPaints:Landroid/util/SparseArray;

    .line 3372
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3373
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result p2

    goto :goto_14

    .line 3374
    :cond_2b
    instance-of p1, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;

    if-eqz p1, :cond_2c

    .line 3375
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->detailsTextPaints:Landroid/util/SparseArray;

    .line 3376
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3377
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto :goto_14

    .line 3378
    :cond_2c
    instance-of p1, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockTable;

    if-eqz p1, :cond_2d

    .line 3379
    iget-object p1, v3, Lorg/telegram/ui/ArticleViewer$Resources;->tableTextPaints:Landroid/util/SparseArray;

    .line 3380
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 3381
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result p2

    goto :goto_14

    :cond_2d
    move p2, v7

    move-object p1, v10

    :goto_14
    and-int/lit16 v5, v0, 0x100

    if-nez v5, :cond_2e

    and-int/lit16 v6, v0, 0x80

    if-eqz v6, :cond_2f

    :cond_2e
    const/high16 v6, 0x40800000    # 4.0f

    .line 3384
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    sub-int/2addr v2, v6

    :cond_2f
    const/4 v6, 0x1

    if-nez p1, :cond_31

    .line 3387
    iget-object p0, v3, Lorg/telegram/ui/ArticleViewer$Resources;->errorTextPaint:Landroid/text/TextPaint;

    if-nez p0, :cond_30

    .line 3388
    new-instance p0, Landroid/text/TextPaint;

    invoke-direct {p0, v6}, Landroid/text/TextPaint;-><init>(I)V

    iput-object p0, v3, Lorg/telegram/ui/ArticleViewer$Resources;->errorTextPaint:Landroid/text/TextPaint;

    .line 3389
    invoke-virtual {p0, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 3391
    :cond_30
    iget-object p0, v3, Lorg/telegram/ui/ArticleViewer$Resources;->errorTextPaint:Landroid/text/TextPaint;

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 3392
    iget-object p0, v3, Lorg/telegram/ui/ArticleViewer$Resources;->errorTextPaint:Landroid/text/TextPaint;

    return-object p0

    .line 3394
    :cond_31
    invoke-virtual {p1, v0}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/text/TextPaint;

    if-nez v1, :cond_48

    .line 3396
    new-instance v1, Landroid/text/TextPaint;

    invoke-direct {v1, v6}, Landroid/text/TextPaint;-><init>(I)V

    and-int/lit8 v7, v0, 0x4

    if-eqz v7, :cond_33

    .line 3398
    iget-boolean p3, v3, Lorg/telegram/ui/ArticleViewer$Resources;->isRichMessage:Z

    if-eqz p3, :cond_32

    sget-object p3, Landroid/graphics/Typeface;->MONOSPACE:Landroid/graphics/Typeface;

    goto :goto_15

    :cond_32
    const-string p3, "fonts/rmono.ttf"

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->getTypeface(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p3

    :goto_15
    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto/16 :goto_18

    .line 3400
    :cond_33
    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;

    if-eqz v7, :cond_34

    .line 3401
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto/16 :goto_18

    .line 3402
    :cond_34
    iget v7, p0, Lorg/telegram/ui/IArticleViewer;->selectedFont:I

    const-string v8, "fonts/rmediumitalic.ttf"

    if-eq v7, v6, :cond_39

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockTitle;

    if-nez v7, :cond_39

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockKicker;

    if-nez v7, :cond_39

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeader;

    if-nez v7, :cond_39

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSubtitle;

    if-nez v7, :cond_39

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSubheader;

    if-nez v7, :cond_39

    invoke-static {p3}, Lorg/telegram/ui/ArticleViewer;->isHeadingBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    move-result v7

    if-eqz v7, :cond_35

    goto :goto_16

    :cond_35
    and-int/lit8 p3, v0, 0x1

    if-eqz p3, :cond_36

    and-int/lit8 v6, v0, 0x2

    if-eqz v6, :cond_36

    .line 3424
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->getTypeface(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto/16 :goto_18

    :cond_36
    if-eqz p3, :cond_37

    .line 3426
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto/16 :goto_18

    :cond_37
    and-int/lit8 p3, v0, 0x2

    if-eqz p3, :cond_38

    .line 3428
    const-string p3, "fonts/ritalic.ttf"

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->getTypeface(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto/16 :goto_18

    .line 3429
    :cond_38
    iget-boolean p3, v3, Lorg/telegram/ui/ArticleViewer$Resources;->isRichMessage:Z

    if-eqz p3, :cond_41

    .line 3430
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto/16 :goto_18

    .line 3403
    :cond_39
    :goto_16
    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockTitle;

    if-nez v7, :cond_3e

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeader;

    if-nez v7, :cond_3e

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSubtitle;

    if-nez v7, :cond_3e

    instance-of v7, p3, Lorg/telegram/tgnet/tl/TL_iv$pageBlockSubheader;

    if-nez v7, :cond_3e

    invoke-static {p3}, Lorg/telegram/ui/ArticleViewer;->isHeadingBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    move-result p3

    if-eqz p3, :cond_3a

    goto :goto_17

    :cond_3a
    and-int/lit8 p3, v0, 0x1

    .line 3412
    const-string/jumbo v3, "serif"

    if-eqz p3, :cond_3b

    and-int/lit8 v7, v0, 0x2

    if-eqz v7, :cond_3b

    const/4 p3, 0x3

    .line 3413
    invoke-static {v3, p3}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto :goto_18

    :cond_3b
    if-eqz p3, :cond_3c

    .line 3415
    invoke-static {v3, v6}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto :goto_18

    :cond_3c
    and-int/lit8 p3, v0, 0x2

    if-eqz p3, :cond_3d

    const/4 p3, 0x2

    .line 3417
    invoke-static {v3, p3}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto :goto_18

    :cond_3d
    const/4 p3, 0x0

    .line 3419
    invoke-static {v3, p3}, Landroid/graphics/Typeface;->create(Ljava/lang/String;I)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto :goto_18

    .line 3404
    :cond_3e
    :goto_17
    iget-boolean p3, v3, Lorg/telegram/ui/ArticleViewer$Resources;->isRichMessage:Z

    if-nez p3, :cond_3f

    .line 3405
    const-string p3, "fonts/mw_bold.ttf"

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->getTypeface(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto :goto_18

    :cond_3f
    and-int/lit8 p3, v0, 0x2

    if-eqz p3, :cond_40

    .line 3407
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->getTypeface(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    goto :goto_18

    .line 3409
    :cond_40
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object p3

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    :cond_41
    :goto_18
    and-int/lit8 p3, v0, 0x20

    if-eqz p3, :cond_42

    .line 3435
    invoke-virtual {v1}, Landroid/graphics/Paint;->getFlags()I

    move-result p3

    or-int/lit8 p3, p3, 0x10

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setFlags(I)V

    :cond_42
    and-int/lit8 p3, v0, 0x10

    if-eqz p3, :cond_43

    .line 3438
    invoke-virtual {v1}, Landroid/graphics/Paint;->getFlags()I

    move-result p3

    or-int/lit8 p3, p3, 0x8

    invoke-virtual {v1, p3}, Landroid/graphics/Paint;->setFlags(I)V

    :cond_43
    and-int/lit8 p3, v0, 0x8

    if-nez p3, :cond_44

    and-int/lit16 p3, v0, 0x200

    if-eqz p3, :cond_45

    .line 3441
    :cond_44
    invoke-virtual {v1}, Landroid/graphics/Paint;->getFlags()I

    move-result p2

    invoke-virtual {v1, p2}, Landroid/graphics/Paint;->setFlags(I)V

    .line 3442
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getLinkTextColor()I

    move-result p2

    :cond_45
    if-eqz v5, :cond_46

    .line 3445
    iget p0, v1, Landroid/text/TextPaint;->baselineShift:I

    const/high16 p3, 0x40c00000    # 6.0f

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p3

    sub-int/2addr p0, p3

    iput p0, v1, Landroid/text/TextPaint;->baselineShift:I

    goto :goto_19

    :cond_46
    and-int/lit16 p0, v0, 0x80

    if-eqz p0, :cond_47

    .line 3447
    iget p0, v1, Landroid/text/TextPaint;->baselineShift:I

    const/high16 p3, 0x40000000    # 2.0f

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p3

    add-int/2addr p0, p3

    iput p0, v1, Landroid/text/TextPaint;->baselineShift:I

    .line 3449
    :cond_47
    :goto_19
    invoke-virtual {v1, p2}, Landroid/graphics/Paint;->setColor(I)V

    .line 3450
    invoke-virtual {p1, v0, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    :cond_48
    add-int/2addr v2, v4

    int-to-float p0, v2

    .line 3452
    invoke-virtual {v1, p0}, Landroid/graphics/Paint;->setTextSize(F)V

    return-object v1
.end method

.method public static getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;
    .locals 1

    .line 3146
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    if-eqz v0, :cond_0

    .line 3147
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textFixed;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 3148
    :cond_0
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    if-eqz v0, :cond_1

    .line 3149
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textItalic;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 3150
    :cond_1
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    if-eqz v0, :cond_2

    .line 3151
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textBold;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 3152
    :cond_2
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    if-eqz v0, :cond_3

    .line 3153
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textUnderline;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 3154
    :cond_3
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    if-eqz v0, :cond_4

    .line 3155
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textStrike;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getUrl(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 3156
    :cond_4
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    if-eqz v0, :cond_5

    .line 3157
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textEmail;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->email:Ljava/lang/String;

    return-object p0

    .line 3158
    :cond_5
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    if-eqz v0, :cond_6

    .line 3159
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textUrl;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$RichText;->url:Ljava/lang/String;

    return-object p0

    .line 3160
    :cond_6
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    if-eqz v0, :cond_7

    .line 3161
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$textPhone;->phone:Ljava/lang/String;

    return-object p0

    :cond_7
    const/4 p0, 0x0

    return-object p0
.end method

.method private goBack()V
    .locals 11

    .line 2077
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    .line 2151
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    const/4 v2, 0x2

    const/high16 v3, 0x3f800000    # 1.0f

    const/4 v4, 0x0

    const/4 v5, 0x1

    if-gt v0, v5, :cond_2

    .line 2078
    invoke-static {v1, v4}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputmovingPage(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 2079
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v0, v4}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputstartedTracking(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 2080
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    .line 2081
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$Sheet;->getBackProgress()F

    move-result v1

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    iget-object v6, v6, Lorg/telegram/ui/ArticleViewer$Sheet;->windowView:Lorg/telegram/ui/ArticleViewer$Sheet$WindowView;

    invoke-virtual {v6}, Landroid/view/View;->getWidth()I

    move-result v6

    int-to-float v6, v6

    mul-float/2addr v1, v6

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->getX()F

    move-result v1

    .line 2084
    :goto_0
    new-instance v6, Landroid/animation/AnimatorSet;

    invoke-direct {v6}, Landroid/animation/AnimatorSet;-><init>()V

    .line 2085
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v7

    int-to-float v7, v7

    sub-float/2addr v7, v1

    .line 2086
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v1, :cond_1

    .line 2087
    invoke-virtual {v1, v3}, Lorg/telegram/ui/ArticleViewer$Sheet;->animateBackProgressTo(F)Landroid/animation/ValueAnimator;

    move-result-object v1

    new-array v2, v5, [Landroid/animation/Animator;

    aput-object v1, v2, v4

    invoke-virtual {v6, v2}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    goto :goto_1

    .line 2089
    :cond_1
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    sget-object v3, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    .line 2090
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    int-to-float v8, v8

    new-array v9, v5, [F

    aput v8, v9, v4

    invoke-static {v1, v3, v9}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v1

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    sget-object v8, Lorg/telegram/ui/ArticleViewer;->ARTICLE_VIEWER_INNER_TRANSLATION_X:Landroid/util/Property;

    .line 2091
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    int-to-float v9, v9

    new-array v10, v5, [F

    aput v9, v10, v4

    invoke-static {v3, v8, v10}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    new-array v2, v2, [Landroid/animation/Animator;

    aput-object v1, v2, v4

    aput-object v3, v2, v5

    .line 2089
    invoke-virtual {v6, v2}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 2095
    :goto_1
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    const/high16 v1, 0x43d20000    # 420.0f

    div-float/2addr v1, v0

    mul-float/2addr v1, v7

    float-to-int v0, v1

    const/16 v1, 0xfa

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    int-to-long v0, v0

    invoke-virtual {v6, v0, v1}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 2096
    sget-object v0, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v6, v0}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 2097
    new-instance v0, Lorg/telegram/ui/ArticleViewer$4;

    invoke-direct {v0, p0}, Lorg/telegram/ui/ArticleViewer$4;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v6, v0}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 2147
    invoke-virtual {v6}, Landroid/animation/AnimatorSet;->start()V

    .line 2148
    iput-boolean v5, p0, Lorg/telegram/ui/ArticleViewer;->closeAnimationInProgress:Z

    return-void

    .line 2151
    :cond_2
    invoke-static {v1, v5}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputopeningPage(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 2152
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v0, v5}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputmovingPage(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 2153
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    iget v1, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    invoke-static {v0, v1}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputstartMovingHeaderHeight(Lorg/telegram/ui/ArticleViewer$WindowView;I)V

    .line 2154
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v5

    invoke-virtual {v0, v4}, Landroid/view/View;->setVisibility(I)V

    .line 2155
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v5

    invoke-virtual {v0, v3}, Landroid/view/View;->setAlpha(F)V

    .line 2156
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v5

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setTranslationX(F)V

    .line 2157
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v4

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v1, :cond_3

    move v1, v4

    goto :goto_2

    :cond_3
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v1}, Landroid/graphics/Paint;->getColor()I

    move-result v1

    :goto_2
    invoke-virtual {v0, v1}, Landroid/view/View;->setBackgroundColor(I)V

    .line 2158
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    sub-int/2addr v1, v2

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    const/4 v1, -0x1

    invoke-direct {p0, v0, v5, v1}, Lorg/telegram/ui/ArticleViewer;->updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V

    .line 2160
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v4

    .line 2161
    invoke-virtual {v0}, Landroid/view/View;->getX()F

    .line 2164
    new-instance v1, Landroid/animation/AnimatorSet;

    invoke-direct {v1}, Landroid/animation/AnimatorSet;-><init>()V

    .line 2166
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    .line 2167
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v4

    sget-object v3, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    .line 2168
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    new-array v6, v5, [F

    aput v0, v6, v4

    invoke-static {v2, v3, v6}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    new-array v2, v5, [Landroid/animation/Animator;

    aput-object v0, v2, v4

    .line 2167
    invoke-virtual {v1, v2}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    const-wide/16 v2, 0x1a4

    .line 2177
    invoke-virtual {v1, v2, v3}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 2178
    sget-object v0, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v1, v0}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 2179
    new-instance v0, Lorg/telegram/ui/ArticleViewer$5;

    invoke-direct {v0, p0}, Lorg/telegram/ui/ArticleViewer$5;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v1, v0}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 2224
    invoke-virtual {v1}, Landroid/animation/AnimatorSet;->start()V

    .line 2225
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v4

    if-eqz v1, :cond_4

    sget-boolean v2, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v2, :cond_4

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v1

    goto :goto_3

    :cond_4
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v1

    :goto_3
    invoke-virtual {v0, v1}, Lorg/telegram/ui/web/WebActionBar;->setMenuColors(I)V

    .line 2226
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v4

    if-eqz v1, :cond_5

    sget-boolean v2, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v2, :cond_5

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v1

    goto :goto_4

    :cond_5
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v1

    :goto_4
    invoke-virtual {v0, v1, v5}, Lorg/telegram/ui/web/WebActionBar;->setColors(IZ)V

    .line 2227
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v4

    if-eqz v1, :cond_6

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isTonsite()Z

    move-result v1

    if-eqz v1, :cond_6

    move v1, v5

    goto :goto_5

    :cond_6
    move v1, v4

    :goto_5
    invoke-virtual {v0, v1}, Lorg/telegram/ui/web/WebActionBar;->setIsTonsite(Z)V

    .line 2228
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v4

    if-eqz v1, :cond_7

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v1

    if-eqz v1, :cond_7

    move v4, v5

    :cond_7
    invoke-virtual {v0, v4}, Lorg/telegram/ui/web/WebActionBar;->setIsLocal(Z)V

    .line 2229
    iput-boolean v5, p0, Lorg/telegram/ui/ArticleViewer;->closeAnimationInProgress:Z

    return-void
.end method

.method private goBack(I)V
    .locals 9

    .line 2233
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    .line 2310
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    const/high16 v2, 0x3f800000    # 1.0f

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-gt v0, v4, :cond_2

    .line 2234
    invoke-static {v1, v3}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputmovingPage(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 2235
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {p1, v3}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputstartedTracking(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 2236
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    .line 2237
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$Sheet;->getBackProgress()F

    move-result v0

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$Sheet;->windowView:Lorg/telegram/ui/ArticleViewer$Sheet$WindowView;

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v0, v1

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getX()F

    move-result v0

    .line 2240
    :goto_0
    new-instance v1, Landroid/animation/AnimatorSet;

    invoke-direct {v1}, Landroid/animation/AnimatorSet;-><init>()V

    .line 2241
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    int-to-float v5, v5

    sub-float/2addr v5, v0

    .line 2242
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v0, :cond_1

    .line 2244
    invoke-virtual {v0, v2}, Lorg/telegram/ui/ArticleViewer$Sheet;->animateBackProgressTo(F)Landroid/animation/ValueAnimator;

    move-result-object v0

    new-array v2, v4, [Landroid/animation/Animator;

    aput-object v0, v2, v3

    .line 2243
    invoke-virtual {v1, v2}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    goto :goto_1

    .line 2247
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    sget-object v2, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    .line 2248
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    int-to-float v6, v6

    new-array v7, v4, [F

    aput v6, v7, v3

    invoke-static {v0, v2, v7}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    sget-object v6, Lorg/telegram/ui/ArticleViewer;->ARTICLE_VIEWER_INNER_TRANSLATION_X:Landroid/util/Property;

    .line 2249
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v7

    int-to-float v7, v7

    new-array v8, v4, [F

    aput v7, v8, v3

    invoke-static {v2, v6, v8}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v2

    const/4 v6, 0x2

    new-array v6, v6, [Landroid/animation/Animator;

    aput-object v0, v6, v3

    aput-object v2, v6, v4

    .line 2247
    invoke-virtual {v1, v6}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 2253
    :goto_1
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result p1

    int-to-float p1, p1

    const/high16 v0, 0x43d20000    # 420.0f

    div-float/2addr v0, p1

    mul-float/2addr v0, v5

    float-to-int p1, v0

    const/16 v0, 0xfa

    invoke-static {p1, v0}, Ljava/lang/Math;->max(II)I

    move-result p1

    int-to-long v2, p1

    invoke-virtual {v1, v2, v3}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 2254
    sget-object p1, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v1, p1}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 2255
    new-instance p1, Lorg/telegram/ui/ArticleViewer$6;

    invoke-direct {p1, p0}, Lorg/telegram/ui/ArticleViewer$6;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v1, p1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 2305
    invoke-virtual {v1}, Landroid/animation/AnimatorSet;->start()V

    .line 2306
    iput-boolean v4, p0, Lorg/telegram/ui/ArticleViewer;->closeAnimationInProgress:Z

    return-void

    .line 2310
    :cond_2
    invoke-static {v1, v4}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputopeningPage(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 2311
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v4

    invoke-virtual {v0, v3}, Landroid/view/View;->setVisibility(I)V

    .line 2312
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v4

    invoke-virtual {v0, v2}, Landroid/view/View;->setAlpha(F)V

    .line 2313
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v4

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setTranslationX(F)V

    .line 2314
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v1, :cond_3

    move v1, v3

    goto :goto_2

    :cond_3
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v1}, Landroid/graphics/Paint;->getColor()I

    move-result v1

    :goto_2
    invoke-virtual {v0, v1}, Landroid/view/View;->setBackgroundColor(I)V

    .line 2315
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    const/4 v1, -0x1

    invoke-direct {p0, v0, v4, v1}, Lorg/telegram/ui/ArticleViewer;->updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V

    .line 2317
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    .line 2318
    invoke-virtual {v0}, Landroid/view/View;->getX()F

    .line 2321
    new-instance v1, Landroid/animation/AnimatorSet;

    invoke-direct {v1}, Landroid/animation/AnimatorSet;-><init>()V

    .line 2323
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    .line 2324
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v3

    sget-object v5, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    .line 2325
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    new-array v6, v4, [F

    aput v0, v6, v3

    invoke-static {v2, v5, v6}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    new-array v2, v4, [Landroid/animation/Animator;

    aput-object v0, v2, v3

    .line 2324
    invoke-virtual {v1, v2}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    const-wide/16 v5, 0x1a4

    .line 2334
    invoke-virtual {v1, v5, v6}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 2335
    sget-object v0, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v1, v0}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 2336
    new-instance v0, Lorg/telegram/ui/ArticleViewer$7;

    invoke-direct {v0, p0, p1}, Lorg/telegram/ui/ArticleViewer$7;-><init>(Lorg/telegram/ui/ArticleViewer;I)V

    invoke-virtual {v1, v0}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 2385
    invoke-virtual {v1}, Landroid/animation/AnimatorSet;->start()V

    .line 2386
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    if-eqz v0, :cond_4

    sget-boolean v1, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v1, :cond_4

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v0

    goto :goto_3

    :cond_4
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v0

    :goto_3
    invoke-virtual {p1, v0}, Lorg/telegram/ui/web/WebActionBar;->setMenuColors(I)V

    .line 2387
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    if-eqz v0, :cond_5

    sget-boolean v1, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v1, :cond_5

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v0

    goto :goto_4

    :cond_5
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v0

    :goto_4
    invoke-virtual {p1, v0, v4}, Lorg/telegram/ui/web/WebActionBar;->setColors(IZ)V

    .line 2388
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    if-eqz v0, :cond_6

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isTonsite()Z

    move-result v0

    if-eqz v0, :cond_6

    move v0, v4

    goto :goto_5

    :cond_6
    move v0, v3

    :goto_5
    invoke-virtual {p1, v0}, Lorg/telegram/ui/web/WebActionBar;->setIsTonsite(Z)V

    .line 2389
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    if-eqz v0, :cond_7

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v0

    if-eqz v0, :cond_7

    move v3, v4

    :cond_7
    invoke-virtual {p1, v3}, Lorg/telegram/ui/web/WebActionBar;->setIsLocal(Z)V

    .line 2390
    iput-boolean v4, p0, Lorg/telegram/ui/ArticleViewer;->closeAnimationInProgress:Z

    return-void
.end method

.method public static hasInstance()Z
    .locals 1

    .line 413
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->Instance:Lorg/telegram/ui/ArticleViewer;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method private hideLineProgressView()V
    .locals 3

    .line 5933
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->lineProgressTickRunnable:Ljava/lang/Runnable;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 5934
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    if-nez v0, :cond_0

    return-void

    .line 5937
    :cond_0
    iget-object v0, v0, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/LineProgressView;->setProgress(FZ)V

    .line 5938
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p0, p0, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    const/4 v0, 0x4

    invoke-virtual {p0, v0}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method

.method private static isCocoonAiSummaryBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z
    .locals 1

    .line 3135
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;

    if-eqz v0, :cond_0

    .line 3136
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquote;->caption:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    goto :goto_0

    .line 3137
    :cond_0
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquoteBlocks;

    if-eqz v0, :cond_1

    .line 3138
    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquoteBlocks;

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockBlockquoteBlocks;->caption:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    .line 3142
    :goto_0
    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->getPlainText(Lorg/telegram/tgnet/tl/TL_iv$RichText;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-interface {p0}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    const-string v0, "Cocoon AI Summary"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p0

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public static isHeadingBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z
    .locals 1

    .line 1802
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading1;

    if-nez v0, :cond_1

    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading2;

    if-nez v0, :cond_1

    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading3;

    if-nez v0, :cond_1

    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading4;

    if-nez v0, :cond_1

    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading5;

    if-nez v0, :cond_1

    instance-of p0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockHeading6;

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private static isInsideGestureExclusionEdge(Landroid/view/MotionEvent;Landroid/view/View;)Z
    .locals 3

    .line 3848
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1d

    const/4 v2, 0x0

    if-lt v0, v1, :cond_4

    if-eqz p1, :cond_4

    invoke-virtual {p1}, Landroid/view/View;->getRootWindowInsets()Landroid/view/WindowInsets;

    move-result-object v1

    if-nez v1, :cond_0

    goto :goto_2

    .line 3851
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getRootWindowInsets()Landroid/view/WindowInsets;

    move-result-object p1

    const/16 v1, 0x1e

    if-lt v0, v1, :cond_1

    .line 3853
    invoke-static {}, Landroid/view/WindowInsets$Type;->systemGestures()I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/WindowInsets;->getInsets(I)Landroid/graphics/Insets;

    move-result-object p1

    goto :goto_0

    .line 3854
    :cond_1
    invoke-virtual {p1}, Landroid/view/WindowInsets;->getSystemGestureInsets()Landroid/graphics/Insets;

    move-result-object p1

    .line 3855
    :goto_0
    invoke-virtual {p0}, Landroid/view/MotionEvent;->getRawX()F

    move-result p0

    .line 3856
    invoke-static {p1}, Landroidx/appcompat/widget/DrawableUtils$$ExternalSyntheticApiModelOutline0;->m(Landroid/graphics/Insets;)I

    move-result v0

    int-to-float v0, v0

    cmpg-float v0, p0, v0

    if-lez v0, :cond_3

    sget-object v0, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v0, v0, Landroid/graphics/Point;->x:I

    invoke-static {p1}, Landroidx/appcompat/widget/DrawableUtils$$ExternalSyntheticApiModelOutline2;->m(Landroid/graphics/Insets;)I

    move-result p1

    sub-int/2addr v0, p1

    int-to-float p1, v0

    cmpl-float p0, p0, p1

    if-ltz p0, :cond_2

    goto :goto_1

    :cond_2
    return v2

    :cond_3
    :goto_1
    const/4 p0, 0x1

    return p0

    :cond_4
    :goto_2
    return v2
.end method

.method public static isListItemBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z
    .locals 1

    .line 1798
    instance-of v0, p0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    if-nez v0, :cond_1

    instance-of p0, p0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public static joinChannel(ILorg/telegram/ui/ArticleViewer$BlockChannelCell;Lorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 3

    .line 6157
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_channels_joinChannel;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_channels_joinChannel;-><init>()V

    .line 6158
    invoke-static {p2}, Lorg/telegram/messenger/MessagesController;->getInputChannel(Lorg/telegram/tgnet/TLRPC$Chat;)Lorg/telegram/tgnet/TLRPC$InputChannel;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$TL_channels_joinChannel;->channel:Lorg/telegram/tgnet/TLRPC$InputChannel;

    .line 6159
    invoke-static {p0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v1

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda55;

    invoke-direct {v2, p1, p0, v0, p2}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda55;-><init>(Lorg/telegram/ui/ArticleViewer$BlockChannelCell;ILorg/telegram/tgnet/TLRPC$TL_channels_joinChannel;Lorg/telegram/tgnet/TLRPC$Chat;)V

    invoke-virtual {v1, v0, v2}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequestTyped(Lorg/telegram/tgnet/TLMethod;Lorg/telegram/messenger/Utilities$Callback2;)I

    return-void
.end method

.method private synthetic lambda$checkScrollAnimated$52(Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 5511
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->setCurrentHeaderHeight(I)V

    return-void
.end method

.method private synthetic lambda$close$57()V
    .locals 3

    .line 6065
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v1, 0x0

    const/4 v2, 0x0

    .line 6068
    invoke-virtual {v0, v2, v1}, Landroid/view/View;->setLayerType(ILandroid/graphics/Paint;)V

    .line 6069
    iput v2, p0, Lorg/telegram/ui/ArticleViewer;->animationInProgress:I

    .line 6070
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->onClosed()V

    return-void
.end method

.method private synthetic lambda$new$67()V
    .locals 1

    .line 14918
    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda7;

    invoke-direct {v0, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$new$68()V
    .locals 1

    .line 14919
    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda7;

    invoke-direct {v0, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onClosed$58()V
    .locals 2

    .line 6113
    :try_start_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 6114
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    const-string/jumbo v1, "window"

    invoke-virtual {v0, v1}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/WindowManager;

    .line 6115
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-interface {v0, p0}, Landroid/view/ViewManager;->removeView(Landroid/view/View;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_0
    return-void

    :catch_0
    move-exception p0

    .line 6118
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$open$53(Lorg/telegram/tgnet/TLObject;ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;)V
    .locals 19

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p3

    move-object/from16 v3, p4

    move/from16 v4, p5

    move-object/from16 v5, p6

    .line 5674
    instance-of v6, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;

    const/4 v7, 0x0

    if-eqz v6, :cond_0

    .line 5675
    check-cast v1, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;

    .line 5676
    invoke-static/range {p2 .. p2}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v6

    iget-object v8, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;->users:Ljava/util/ArrayList;

    invoke-virtual {v6, v8, v7}, Lorg/telegram/messenger/MessagesController;->putUsers(Ljava/util/ArrayList;Z)V

    .line 5677
    invoke-static/range {p2 .. p2}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v6

    iget-object v8, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;->chats:Ljava/util/ArrayList;

    invoke-virtual {v6, v8, v7}, Lorg/telegram/messenger/MessagesController;->putChats(Ljava/util/ArrayList;Z)V

    .line 5678
    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;->webpage:Lorg/telegram/tgnet/TLRPC$WebPage;

    .line 5680
    :cond_0
    instance-of v6, v1, Lorg/telegram/tgnet/TLRPC$TL_webPage;

    const/4 v8, 0x1

    if-eqz v6, :cond_5

    .line 5681
    check-cast v1, Lorg/telegram/tgnet/TLRPC$TL_webPage;

    .line 5682
    iget-object v6, v1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    if-nez v6, :cond_1

    goto/16 :goto_2

    .line 5686
    :cond_1
    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v6

    if-nez v6, :cond_4

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v6, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    if-ne v6, v2, :cond_4

    if-eqz v3, :cond_2

    .line 5688
    iget-object v2, v3, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object v2, v2, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    iput-object v1, v2, Lorg/telegram/tgnet/TLRPC$MessageMedia;->webpage:Lorg/telegram/tgnet/TLRPC$WebPage;

    .line 5689
    new-instance v10, Lorg/telegram/tgnet/TLRPC$TL_messages_messages;

    invoke-direct {v10}, Lorg/telegram/tgnet/TLRPC$TL_messages_messages;-><init>()V

    .line 5690
    iget-object v2, v10, Lorg/telegram/tgnet/TLRPC$messages_Messages;->messages:Ljava/util/ArrayList;

    iget-object v6, v3, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    invoke-virtual {v2, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 5691
    invoke-static/range {p2 .. p2}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v9

    invoke-virtual {v3}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v11

    iget-boolean v2, v3, Lorg/telegram/messenger/MessageObject;->scheduled:Z

    const-wide/16 v17, 0x0

    const/4 v13, -0x2

    const/4 v14, 0x0

    const/4 v15, 0x0

    move/from16 v16, v2

    invoke-virtual/range {v9 .. v18}, Lorg/telegram/messenger/MessagesStorage;->putMessages(Lorg/telegram/tgnet/TLRPC$messages_Messages;JIIZIJ)V

    .line 5696
    :cond_2
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    if-eqz v4, :cond_3

    .line 5694
    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 5696
    :cond_3
    invoke-virtual {v2, v7, v1}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 5698
    :goto_0
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ne v2, v8, :cond_4

    .line 5699
    sget-object v2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v3, "articles"

    invoke-virtual {v2, v3, v7}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v2

    invoke-interface {v2}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v6, "article"

    invoke-direct {v3, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v9, v1, Lorg/telegram/tgnet/TLRPC$WebPage;->id:J

    invoke-virtual {v3, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v2, v3}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    invoke-interface {v2}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 5700
    invoke-direct {v0, v1, v7, v4}, Lorg/telegram/ui/ArticleViewer;->updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V

    if-eqz v5, :cond_4

    .line 5702
    invoke-virtual {v0, v5, v7}, Lorg/telegram/ui/ArticleViewer;->scrollToAnchor(Ljava/lang/String;Z)Z

    .line 5707
    :cond_4
    new-instance v0, Landroidx/collection/LongSparseArray;

    invoke-direct {v0, v8}, Landroidx/collection/LongSparseArray;-><init>(I)V

    .line 5708
    iget-wide v2, v1, Lorg/telegram/tgnet/TLRPC$WebPage;->id:J

    invoke-virtual {v0, v2, v3, v1}, Landroidx/collection/LongSparseArray;->put(JLjava/lang/Object;)V

    .line 5709
    invoke-static/range {p2 .. p2}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v1

    invoke-virtual {v1, v0}, Lorg/telegram/messenger/MessagesStorage;->putWebPages(Landroidx/collection/LongSparseArray;)V

    return-void

    .line 5710
    :cond_5
    instance-of v4, v1, Lorg/telegram/tgnet/TLRPC$TL_webPageNotModified;

    if-eqz v4, :cond_8

    .line 5711
    check-cast v1, Lorg/telegram/tgnet/TLRPC$TL_webPageNotModified;

    if-eqz v2, :cond_8

    .line 5712
    iget-object v4, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    if-eqz v4, :cond_8

    .line 5713
    iget v5, v4, Lorg/telegram/tgnet/tl/TL_iv$Page;->views:I

    iget v1, v1, Lorg/telegram/tgnet/TLRPC$TL_webPageNotModified;->cached_page_views:I

    if-eq v5, v1, :cond_8

    .line 5714
    iput v1, v4, Lorg/telegram/tgnet/tl/TL_iv$Page;->views:I

    .line 5715
    iget v1, v4, Lorg/telegram/tgnet/tl/TL_iv$Page;->flags:I

    or-int/lit8 v1, v1, 0x8

    iput v1, v4, Lorg/telegram/tgnet/tl/TL_iv$Page;->flags:I

    .line 5716
    :goto_1
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length v4, v1

    if-ge v7, v4, :cond_7

    .line 5717
    aget-object v1, v1, v7

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v1

    if-ne v1, v2, :cond_6

    .line 5718
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v7

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->getItemCount()I

    move-result v1

    sub-int/2addr v1, v8

    .line 5719
    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v7

    iget-object v4, v4, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v4, v1}, Landroidx/recyclerview/widget/RecyclerView;->findViewHolderForAdapterPosition(I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v1

    if-eqz v1, :cond_6

    .line 5721
    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v7

    iget-object v4, v4, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v4, v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->onViewAttachedToWindow(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V

    :cond_6
    add-int/lit8 v7, v7, 0x1

    goto :goto_1

    :cond_7
    if-eqz v3, :cond_8

    .line 5726
    new-instance v10, Lorg/telegram/tgnet/TLRPC$TL_messages_messages;

    invoke-direct {v10}, Lorg/telegram/tgnet/TLRPC$TL_messages_messages;-><init>()V

    .line 5727
    iget-object v0, v10, Lorg/telegram/tgnet/TLRPC$messages_Messages;->messages:Ljava/util/ArrayList;

    iget-object v1, v3, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 5728
    invoke-static/range {p2 .. p2}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v9

    invoke-virtual {v3}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v11

    iget-boolean v0, v3, Lorg/telegram/messenger/MessageObject;->scheduled:Z

    const-wide/16 v17, 0x0

    const/4 v13, -0x2

    const/4 v14, 0x0

    const/4 v15, 0x0

    move/from16 v16, v0

    invoke-virtual/range {v9 .. v18}, Lorg/telegram/messenger/MessagesStorage;->putMessages(Lorg/telegram/tgnet/TLRPC$messages_Messages;JIIZIJ)V

    :cond_8
    :goto_2
    return-void
.end method

.method private synthetic lambda$open$54(ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 1

    move-object p7, p5

    move-object p5, p3

    move p3, p1

    move-object p1, p0

    .line 5672
    new-instance p0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda6;

    move v0, p4

    move-object p4, p2

    move-object p2, p6

    move p6, v0

    invoke-direct/range {p0 .. p7}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/TLObject;ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$open$55()V
    .locals 3

    .line 5822
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    if-eqz v0, :cond_1

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    const/4 v2, 0x0

    .line 5825
    invoke-virtual {v0, v2, v1}, Landroid/view/View;->setLayerType(ILandroid/graphics/Paint;)V

    .line 5826
    iput v2, p0, Lorg/telegram/ui/ArticleViewer;->animationInProgress:I

    .line 5827
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {p0}, Landroid/app/Activity;->getCurrentFocus()Landroid/view/View;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->hideKeyboard(Landroid/view/View;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private synthetic lambda$open$56(Landroid/animation/AnimatorSet;)V
    .locals 0

    .line 5846
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->notificationsLocker:Lorg/telegram/messenger/AnimationNotificationsLocker;

    invoke-virtual {p0}, Lorg/telegram/messenger/AnimationNotificationsLocker;->lock()V

    .line 5847
    invoke-virtual {p1}, Landroid/animation/AnimatorSet;->start()V

    return-void
.end method

.method private synthetic lambda$openWebpageUrl$8(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 3936
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->openWebpageUrlInternal(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)V

    return-void
.end method

.method private synthetic lambda$openWebpageUrlInternal$10(Ljava/lang/String;[ZLorg/telegram/messenger/browser/Browser$Progress;)Ljava/lang/Boolean;
    .locals 11

    .line 3963
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    invoke-static {v0, p2}, Lorg/telegram/messenger/browser/Browser;->isInternalUri(Landroid/net/Uri;[Z)Z

    move-result p2

    if-nez p2, :cond_0

    .line 3964
    sget-object p0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    return-object p0

    :cond_0
    if-eqz p3, :cond_1

    .line 3967
    new-instance p2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda63;

    invoke-direct {p2, p0, p3}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda63;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/messenger/browser/Browser$Progress;)V

    invoke-virtual {p3, p2}, Lorg/telegram/messenger/browser/Browser$Progress;->onEnd(Ljava/lang/Runnable;)Lorg/telegram/messenger/browser/Browser$Progress;

    goto :goto_0

    .line 3976
    :cond_1
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p2, :cond_2

    const/4 v0, 0x1

    .line 3977
    invoke-virtual {p2, v0}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    .line 3980
    :cond_2
    :goto_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v2

    const/4 v9, 0x1

    const/4 v10, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x1

    const/4 v5, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x1

    move-object v6, p3

    invoke-static/range {v1 .. v10}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Landroid/net/Uri;ZZZLorg/telegram/messenger/browser/Browser$Progress;Ljava/lang/String;ZZZ)V

    .line 3981
    sget-object p0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    return-object p0
.end method

.method private synthetic lambda$openWebpageUrlInternal$11(ILorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/tgnet/TLObject;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;)V
    .locals 2

    .line 3990
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    if-eqz v0, :cond_7

    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->lastReqId:I

    if-eq p1, v0, :cond_0

    goto/16 :goto_0

    :cond_0
    if-eqz p2, :cond_1

    .line 3994
    invoke-virtual {p2}, Lorg/telegram/messenger/browser/Browser$Progress;->end()V

    :cond_1
    const/4 p1, 0x0

    .line 3996
    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    const/4 p2, 0x1

    .line 3997
    invoke-direct {p0, p2, p1}, Lorg/telegram/ui/ArticleViewer;->showProgressView(ZZ)V

    .line 3998
    iget-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    if-eqz v0, :cond_7

    .line 3999
    instance-of v0, p3, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;

    if-eqz v0, :cond_4

    .line 4000
    check-cast p3, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;

    .line 4001
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    iget-object v1, p3, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;->users:Ljava/util/ArrayList;

    invoke-virtual {v0, v1, p1}, Lorg/telegram/messenger/MessagesController;->putUsers(Ljava/util/ArrayList;Z)V

    .line 4002
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    iget-object v1, p3, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;->chats:Ljava/util/ArrayList;

    invoke-virtual {v0, v1, p1}, Lorg/telegram/messenger/MessagesController;->putChats(Ljava/util/ArrayList;Z)V

    .line 4003
    iget-object p1, p3, Lorg/telegram/tgnet/TLRPC$TL_messages_webPage;->webpage:Lorg/telegram/tgnet/TLRPC$WebPage;

    if-eqz p1, :cond_2

    iget-object p3, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    instance-of p3, p3, Lorg/telegram/tgnet/tl/TL_iv$TL_page;

    if-eqz p3, :cond_2

    .line 4004
    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/ArticleViewer;->addPageToStack(Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;I)Z

    return-void

    .line 4005
    :cond_2
    invoke-interface {p5}, Lorg/telegram/messenger/Utilities$Callback0Return;->run()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-nez p1, :cond_7

    .line 4006
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    iget-object p3, p6, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    invoke-virtual {p1, p3}, Lorg/telegram/messenger/MessagesController;->isWebBrowserOpenInApp(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 4007
    iget-object p1, p6, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->addPageToStack(Ljava/lang/String;I)Z

    return-void

    .line 4009
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    iget-object p1, p6, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    .line 4012
    :cond_4
    instance-of p1, p3, Lorg/telegram/tgnet/TLRPC$TL_webPage;

    if-eqz p1, :cond_5

    check-cast p3, Lorg/telegram/tgnet/TLRPC$TL_webPage;

    iget-object p1, p3, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    instance-of p1, p1, Lorg/telegram/tgnet/tl/TL_iv$TL_page;

    if-eqz p1, :cond_5

    .line 4013
    invoke-direct {p0, p3, p4, p2}, Lorg/telegram/ui/ArticleViewer;->addPageToStack(Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;I)Z

    return-void

    .line 4014
    :cond_5
    invoke-interface {p5}, Lorg/telegram/messenger/Utilities$Callback0Return;->run()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-nez p1, :cond_7

    .line 4015
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    iget-object p3, p6, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    invoke-virtual {p1, p3}, Lorg/telegram/messenger/MessagesController;->isWebBrowserOpenInApp(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_6

    .line 4016
    iget-object p1, p6, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->addPageToStack(Ljava/lang/String;I)Z

    return-void

    .line 4018
    :cond_6
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    iget-object p1, p6, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    :cond_7
    :goto_0
    return-void
.end method

.method private synthetic lambda$openWebpageUrlInternal$12(ILorg/telegram/messenger/browser/Browser$Progress;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 1

    move-object p7, p5

    move-object p5, p3

    move-object p3, p2

    move p2, p1

    move-object p1, p0

    .line 3989
    new-instance p0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda64;

    move-object v0, p6

    move-object p6, p4

    move-object p4, v0

    invoke-direct/range {p0 .. p7}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda64;-><init>(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/tgnet/TLObject;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$openWebpageUrlInternal$13(ILorg/telegram/messenger/browser/Browser$Progress;)V
    .locals 2

    .line 4025
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->lastReqId:I

    if-ne v0, p1, :cond_0

    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    if-eqz p1, :cond_0

    .line 4026
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p1

    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Lorg/telegram/tgnet/ConnectionsManager;->cancelRequest(IZ)V

    .line 4027
    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    .line 4029
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->loadingProgress:Lorg/telegram/messenger/browser/Browser$Progress;

    if-ne p1, p2, :cond_1

    const/4 p1, 0x0

    .line 4030
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->loadingProgress:Lorg/telegram/messenger/browser/Browser$Progress;

    :cond_1
    return-void
.end method

.method private synthetic lambda$openWebpageUrlInternal$9(Lorg/telegram/messenger/browser/Browser$Progress;)V
    .locals 2

    .line 3968
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    .line 3969
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    .line 3971
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->loadingProgress:Lorg/telegram/messenger/browser/Browser$Progress;

    if-ne v0, p1, :cond_1

    const/4 p1, 0x0

    .line 3972
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->loadingProgress:Lorg/telegram/messenger/browser/Browser$Progress;

    :cond_1
    return-void
.end method

.method private synthetic lambda$processSearch$48(ILjava/util/ArrayList;Ljava/lang/String;)V
    .locals 1

    .line 5372
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->lastSearchIndex:I

    if-ne p1, v0, :cond_0

    const/4 p1, 0x1

    .line 5373
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ArticleViewer;->showSearchPanel(Z)V

    .line 5374
    iput-object p2, p0, Lorg/telegram/ui/IArticleViewer;->searchResults:Ljava/util/ArrayList;

    .line 5375
    iput-object p3, p0, Lorg/telegram/ui/IArticleViewer;->searchText:Ljava/lang/String;

    .line 5376
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 p2, 0x0

    aget-object p1, p1, p2

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetsearchTextOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/HashMap;->clear()V

    .line 5377
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, p2

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p1}, Lorg/telegram/ui/Components/RecyclerListView;->invalidateViews()V

    .line 5378
    invoke-direct {p0, p2}, Lorg/telegram/ui/ArticleViewer;->scrollToSearchIndex(I)V

    :cond_0
    return-void
.end method

.method private synthetic lambda$processSearch$49(Ljava/util/ArrayList;Ljava/util/HashMap;Ljava/lang/String;I)V
    .locals 16

    move-object/from16 v0, p0

    move-object/from16 v7, p3

    .line 5342
    new-instance v8, Ljava/util/ArrayList;

    invoke-direct {v8}, Ljava/util/ArrayList;-><init>()V

    .line 5343
    invoke-virtual/range {p1 .. p1}, Ljava/util/ArrayList;->size()I

    move-result v9

    const/4 v10, 0x0

    move v11, v10

    :goto_0
    if-ge v11, v9, :cond_5

    move-object/from16 v12, p1

    .line 5344
    invoke-virtual {v12, v11}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v13

    move-object/from16 v14, p2

    .line 5345
    invoke-virtual {v14, v13}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    move-object v5, v1

    check-cast v5, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 5347
    instance-of v1, v13, Lorg/telegram/tgnet/tl/TL_iv$RichText;

    const/4 v15, 0x0

    if-eqz v1, :cond_0

    .line 5348
    move-object v3, v13

    check-cast v3, Lorg/telegram/tgnet/tl/TL_iv$RichText;

    .line 5349
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v10

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    const/4 v2, 0x0

    const/16 v6, 0x3e8

    move-object v4, v3

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer;->getText(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Landroid/view/View;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$RichText;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Ljava/lang/CharSequence;

    move-result-object v1

    .line 5350
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_1

    .line 5351
    invoke-interface {v1}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v15

    goto :goto_1

    .line 5353
    :cond_0
    instance-of v1, v13, Ljava/lang/String;

    if-eqz v1, :cond_1

    .line 5354
    move-object v1, v13

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v15

    :cond_1
    :goto_1
    if-eqz v15, :cond_4

    move v1, v10

    .line 5359
    :goto_2
    invoke-virtual {v15, v7, v1}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I

    move-result v1

    if-ltz v1, :cond_4

    .line 5360
    invoke-virtual {v7}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v2, v1

    if-eqz v1, :cond_2

    add-int/lit8 v3, v1, -0x1

    .line 5361
    invoke-virtual {v15, v3}, Ljava/lang/String;->charAt(I)C

    move-result v3

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->isPunctuationCharacter(C)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 5362
    :cond_2
    new-instance v3, Lorg/telegram/ui/ArticleViewer$SearchResult;

    invoke-direct {v3}, Lorg/telegram/ui/ArticleViewer$SearchResult;-><init>()V

    .line 5363
    invoke-static {v3, v1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fputindex(Lorg/telegram/ui/ArticleViewer$SearchResult;I)V

    .line 5364
    invoke-static {v3, v5}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fputblock(Lorg/telegram/ui/ArticleViewer$SearchResult;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)V

    .line 5365
    invoke-static {v3, v13}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fputtext(Lorg/telegram/ui/ArticleViewer$SearchResult;Ljava/lang/Object;)V

    .line 5366
    invoke-virtual {v8, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_3
    move v1, v2

    goto :goto_2

    :cond_4
    add-int/lit8 v11, v11, 0x1

    goto :goto_0

    .line 5371
    :cond_5
    new-instance v1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda75;

    move/from16 v2, p4

    invoke-direct {v1, v0, v2, v8, v7}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda75;-><init>(Lorg/telegram/ui/ArticleViewer;ILjava/util/ArrayList;Ljava/lang/String;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$processSearch$50(Ljava/lang/String;I)V
    .locals 7

    .line 5338
    new-instance v3, Ljava/util/HashMap;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgettextToBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v0

    invoke-direct {v3, v0}, Ljava/util/HashMap;-><init>(Ljava/util/Map;)V

    .line 5339
    new-instance v2, Ljava/util/ArrayList;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgettextBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    const/4 v0, 0x0

    .line 5340
    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchRunnable:Ljava/lang/Runnable;

    .line 5341
    sget-object v6, Lorg/telegram/messenger/Utilities;->searchQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda66;

    move-object v1, p0

    move-object v4, p1

    move v5, p2

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda66;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/util/ArrayList;Ljava/util/HashMap;Ljava/lang/String;I)V

    invoke-virtual {v6, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private synthetic lambda$setParentActivity$15(Landroid/view/View;I)Z
    .locals 0

    .line 4271
    instance-of p2, p1, Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesCell;

    if-eqz p2, :cond_0

    .line 4272
    check-cast p1, Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesCell;

    .line 4273
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesCell;->-$$Nest$fgetcurrentBlock(Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesCell;)Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;

    move-result-object p2

    iget-object p2, p2, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;->parent:Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;

    iget-object p2, p2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;->articles:Ljava/util/ArrayList;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesCell;->-$$Nest$fgetcurrentBlock(Lorg/telegram/ui/ArticleViewer$BlockRelatedArticlesCell;)Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;

    move-result-object p1

    iget p1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;->num:I

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageRelatedArticle;

    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$pageRelatedArticle;->url:Ljava/lang/String;

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->showCopyPopup(Ljava/lang/String;)V

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic lambda$setParentActivity$16(Lorg/telegram/tgnet/TLObject;IJ)V
    .locals 4

    .line 4306
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->previewsReqId:I

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 4309
    iput v0, p0, Lorg/telegram/ui/ArticleViewer;->previewsReqId:I

    const/4 v1, 0x1

    .line 4310
    invoke-direct {p0, v1, v0}, Lorg/telegram/ui/ArticleViewer;->showProgressView(ZZ)V

    if-eqz p1, :cond_1

    .line 4312
    check-cast p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;

    .line 4313
    invoke-static {p2}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v2

    iget-object v3, p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->users:Ljava/util/ArrayList;

    invoke-virtual {v2, v3, v0}, Lorg/telegram/messenger/MessagesController;->putUsers(Ljava/util/ArrayList;Z)V

    .line 4314
    invoke-static {p2}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object p2

    iget-object v2, p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->users:Ljava/util/ArrayList;

    iget-object v3, p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->chats:Ljava/util/ArrayList;

    invoke-virtual {p2, v2, v3, v0, v1}, Lorg/telegram/messenger/MessagesStorage;->putUsersAndChats(Ljava/util/List;Ljava/util/List;ZZ)V

    .line 4315
    iget-object p2, p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->users:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p2

    if-nez p2, :cond_1

    .line 4316
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolvedPeer;->users:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/tgnet/TLRPC$User;

    invoke-direct {p0, p1, p3, p4}, Lorg/telegram/ui/ArticleViewer;->openPreviewsChat(Lorg/telegram/tgnet/TLRPC$User;J)V

    :cond_1
    :goto_0
    return-void
.end method

.method private synthetic lambda$setParentActivity$17(IJLorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 2

    move-wide v0, p2

    move-object p2, p4

    move-wide p4, v0

    move p3, p1

    move-object p1, p0

    .line 4305
    new-instance p0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda46;

    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda46;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/TLObject;IJ)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$18(Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/view/View;IFF)V
    .locals 3

    .line 4279
    iget-object p5, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p5, :cond_0

    add-int/lit8 p3, p3, -0x1

    if-gez p3, :cond_0

    goto/16 :goto_1

    .line 4283
    :cond_0
    iget-object p5, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    if-eqz p5, :cond_2

    .line 4284
    invoke-virtual {p5}, Lorg/telegram/ui/Cells/TextSelectionHelper;->isInSelectionMode()Z

    move-result p5

    .line 4288
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    if-eqz p5, :cond_1

    .line 4285
    invoke-virtual {v0}, Lorg/telegram/ui/Cells/TextSelectionHelper;->clear()V

    return-void

    .line 4288
    :cond_1
    invoke-virtual {v0}, Lorg/telegram/ui/Cells/TextSelectionHelper;->clear()V

    .line 4290
    :cond_2
    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getAdapter()Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    move-result-object p1

    .line 4291
    instance-of p5, p2, Lorg/telegram/ui/ArticleViewer$ReportCell;

    const/4 v0, 0x2

    const/4 v1, 0x1

    if-eqz p5, :cond_6

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p5

    if-eqz p5, :cond_6

    .line 4292
    move-object p3, p2

    check-cast p3, Lorg/telegram/ui/ArticleViewer$ReportCell;

    .line 4293
    iget p5, p0, Lorg/telegram/ui/ArticleViewer;->previewsReqId:I

    if-nez p5, :cond_e

    invoke-static {p3}, Lorg/telegram/ui/ArticleViewer$ReportCell;->-$$Nest$fgethasViews(Lorg/telegram/ui/ArticleViewer$ReportCell;)Z

    move-result p5

    if-eqz p5, :cond_3

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result p2

    div-int/2addr p2, v0

    int-to-float p2, p2

    cmpg-float p2, p4, p2

    if-ltz p2, :cond_e

    :cond_3
    iget-boolean p2, p3, Lorg/telegram/ui/ArticleViewer$ReportCell;->web:Z

    if-eqz p2, :cond_4

    goto/16 :goto_1

    .line 4296
    :cond_4
    iget p2, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p2}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p2

    const-string/jumbo p3, "previews"

    invoke-virtual {p2, p3}, Lorg/telegram/messenger/MessagesController;->getUserOrChat(Ljava/lang/String;)Lorg/telegram/tgnet/TLObject;

    move-result-object p2

    .line 4297
    instance-of p4, p2, Lorg/telegram/tgnet/TLRPC$TL_user;

    if-eqz p4, :cond_5

    .line 4298
    check-cast p2, Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p1

    iget-wide p3, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->id:J

    invoke-direct {p0, p2, p3, p4}, Lorg/telegram/ui/ArticleViewer;->openPreviewsChat(Lorg/telegram/tgnet/TLRPC$User;J)V

    return-void

    .line 4300
    :cond_5
    sget p2, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    .line 4301
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p1

    iget-wide p4, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->id:J

    .line 4302
    invoke-direct {p0, v1, v1}, Lorg/telegram/ui/ArticleViewer;->showProgressView(ZZ)V

    .line 4303
    new-instance p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolveUsername;

    invoke-direct {p1}, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolveUsername;-><init>()V

    .line 4304
    iput-object p3, p1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolveUsername;->username:Ljava/lang/String;

    .line 4305
    invoke-static {p2}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p3

    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda37;

    invoke-direct {v0, p0, p2, p4, p5}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda37;-><init>(Lorg/telegram/ui/ArticleViewer;IJ)V

    invoke-virtual {p3, p1, v0}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    move-result p1

    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->previewsReqId:I

    return-void

    :cond_6
    if-ltz p3, :cond_e

    .line 4321
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetlocalBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object p4

    invoke-virtual {p4}, Ljava/util/ArrayList;->size()I

    move-result p4

    if-ge p3, p4, :cond_e

    .line 4322
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetlocalBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object p4

    invoke-virtual {p4, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p4

    check-cast p4, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 4324
    invoke-direct {p0, p4}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p5

    .line 4325
    instance-of v2, p5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    if-eqz v2, :cond_7

    .line 4326
    check-cast p5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    .line 4327
    invoke-static {p5}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p5

    .line 4329
    :cond_7
    instance-of v2, p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;

    if-eqz v2, :cond_8

    .line 4330
    check-cast p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;

    .line 4331
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    iget-object p2, p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;->channel:Lorg/telegram/tgnet/TLRPC$Chat;

    invoke-static {p2}, Lorg/telegram/messenger/ChatObject;->getPublicUsername(Lorg/telegram/tgnet/TLRPC$Chat;)Ljava/lang/String;

    move-result-object p2

    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-virtual {p1, p2, p3, v0}, Lorg/telegram/messenger/MessagesController;->openByUserName(Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;I)V

    const/4 p1, 0x0

    .line 4332
    invoke-virtual {p0, p1, v1}, Lorg/telegram/ui/ArticleViewer;->close(ZZ)V

    return-void

    .line 4333
    :cond_8
    instance-of v0, p5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;

    const/4 v2, 0x0

    if-eqz v0, :cond_9

    .line 4334
    check-cast p5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;

    .line 4335
    iget-object p1, p5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;->parent:Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;

    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockRelatedArticles;->articles:Ljava/util/ArrayList;

    iget p2, p5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockRelatedArticlesChild;->num:I

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageRelatedArticle;

    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$pageRelatedArticle;->url:Ljava/lang/String;

    invoke-virtual {p0, p1, v2, v2}, Lorg/telegram/ui/ArticleViewer;->openWebpageUrl(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)V

    return-void

    .line 4336
    :cond_9
    instance-of v0, p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;

    if-eqz v0, :cond_e

    .line 4337
    invoke-direct {p0, p2}, Lorg/telegram/ui/ArticleViewer;->getLastNonListCell(Landroid/view/View;)Landroid/view/View;

    move-result-object p2

    .line 4338
    instance-of v0, p2, Lorg/telegram/ui/ArticleViewer$BlockDetailsCell;

    if-nez v0, :cond_a

    goto :goto_1

    .line 4342
    :cond_a
    iput-object v2, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    .line 4343
    iput-object v2, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerView:Landroid/view/View;

    .line 4344
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetblocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object p0

    invoke-virtual {p0, p4}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result p0

    if-gez p0, :cond_b

    goto :goto_1

    .line 4348
    :cond_b
    check-cast p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;

    .line 4349
    iget-boolean p0, p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    xor-int/2addr p0, v1

    iput-boolean p0, p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    .line 4351
    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->getItemCount()I

    move-result p0

    .line 4352
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mupdateRows(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)V

    .line 4353
    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->getItemCount()I

    move-result p4

    sub-int/2addr p4, p0

    .line 4354
    invoke-static {p4}, Ljava/lang/Math;->abs(I)I

    move-result p0

    .line 4356
    check-cast p2, Lorg/telegram/ui/ArticleViewer$BlockDetailsCell;

    .line 4357
    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$BlockDetailsCell;->-$$Nest$fgetarrow(Lorg/telegram/ui/ArticleViewer$BlockDetailsCell;)Lorg/telegram/ui/Components/AnimatedArrowDrawable;

    move-result-object p4

    iget-boolean v0, p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    if-eqz v0, :cond_c

    const/4 v0, 0x0

    goto :goto_0

    :cond_c
    const/high16 v0, 0x3f800000    # 1.0f

    :goto_0
    invoke-virtual {p4, v0}, Lorg/telegram/ui/Components/AnimatedArrowDrawable;->setAnimationProgressAnimated(F)V

    .line 4358
    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    if-eqz p0, :cond_e

    .line 4360
    iget-boolean p2, p5, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    if-eqz p2, :cond_d

    add-int/2addr p3, v1

    .line 4361
    invoke-virtual {p1, p3, p0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyItemRangeInserted(II)V

    return-void

    :cond_d
    add-int/2addr p3, v1

    .line 4363
    invoke-virtual {p1, p3, p0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyItemRangeRemoved(II)V

    :cond_e
    :goto_1
    return-void
.end method

.method private synthetic lambda$setParentActivity$19(Ljava/lang/String;)V
    .locals 2

    .line 4458
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, v0, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    const-string p1, "about:blank"

    :cond_0
    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 4459
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p1, p1, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-virtual {p1}, Lcom/exteragram/messenger/components/ReceiveContentEditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setSelection(I)V

    .line 4460
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p0, p0, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method

.method private synthetic lambda$setParentActivity$20(Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/app/Activity;Ljava/lang/String;)V
    .locals 8

    .line 4463
    invoke-static {p3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 4464
    :cond_0
    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_1

    :goto_0
    return-void

    .line 4465
    :cond_1
    new-instance v0, Landroid/text/SpannableStringBuilder;

    invoke-virtual {p3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 4466
    invoke-static {v0, v1, v2, v1}, Lorg/telegram/messenger/AndroidUtilities;->addLinksSafe(Landroid/text/Spannable;IZZ)Z

    .line 4467
    invoke-virtual {v0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v3

    const-class v4, Landroid/text/style/URLSpan;

    invoke-virtual {v0, v2, v3, v4}, Landroid/text/SpannableStringBuilder;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v3

    check-cast v3, [Landroid/text/style/URLSpan;

    .line 4468
    invoke-virtual {v0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v4

    move v5, v2

    move v6, v5

    .line 4469
    :goto_1
    array-length v7, v3

    if-ge v5, v7, :cond_2

    .line 4470
    aget-object v7, v3, v5

    invoke-virtual {v0, v7}, Landroid/text/SpannableStringBuilder;->getSpanStart(Ljava/lang/Object;)I

    move-result v7

    invoke-static {v7, v4}, Ljava/lang/Math;->min(II)I

    move-result v4

    .line 4471
    aget-object v7, v3, v5

    invoke-virtual {v0, v7}, Landroid/text/SpannableStringBuilder;->getSpanEnd(Ljava/lang/Object;)I

    move-result v7

    invoke-static {v7, v6}, Ljava/lang/Math;->max(II)I

    move-result v6

    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    .line 4473
    :cond_2
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {p0, v2, v1}, Lorg/telegram/ui/web/WebActionBar;->showAddress(ZZ)V

    .line 4474
    invoke-static {p3}, Lorg/telegram/messenger/Utilities;->uriParseSafe(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    .line 4475
    array-length v0, v3

    if-lez v0, :cond_3

    if-nez v4, :cond_3

    if-gtz v6, :cond_4

    :cond_3
    if-eqz p0, :cond_6

    invoke-virtual {p0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_6

    :cond_4
    if-eqz p0, :cond_5

    .line 4476
    invoke-virtual {p0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object p2

    if-nez p2, :cond_5

    invoke-virtual {p0}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object p2

    if-nez p2, :cond_5

    invoke-virtual {p0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p2

    if-eqz p2, :cond_5

    .line 4477
    invoke-virtual {p0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p2

    const-string p3, "/"

    const-string/jumbo v0, "https"

    const/4 v1, 0x0

    invoke-static {p0, v0, v1, p2, p3}, Lorg/telegram/messenger/browser/Browser;->replace(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    .line 4479
    :cond_5
    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0, p3}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->loadUrl(Ljava/lang/String;)V

    return-void

    .line 4481
    :cond_6
    invoke-static {p2, p3}, Lorg/telegram/ui/web/AddressBarList;->pushRecentSearch(Landroid/content/Context;Ljava/lang/String;)V

    .line 4482
    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-static {}, Lorg/telegram/ui/web/SearchEngine;->getCurrent()Lorg/telegram/ui/web/SearchEngine;

    move-result-object p1

    invoke-virtual {p1, p3}, Lorg/telegram/ui/web/SearchEngine;->getSearchURL(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->loadUrl(Ljava/lang/String;)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$21(Ljava/lang/String;)V
    .locals 1

    .line 4486
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 4487
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, v0, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 4488
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p1, p1, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-virtual {p1}, Lcom/exteragram/messenger/components/ReceiveContentEditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setSelection(I)V

    .line 4489
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p0, p0, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method

.method private synthetic lambda$setParentActivity$22(Ljava/lang/String;Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/view/View;)V
    .locals 2

    .line 4493
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-virtual {p3, v0, v1}, Lorg/telegram/ui/web/WebActionBar;->showAddress(ZZ)V

    .line 4494
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p3, p3, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->hideKeyboard(Landroid/view/View;)V

    .line 4495
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p3

    if-eqz p3, :cond_0

    const-string p1, "about:blank"

    .line 4496
    :cond_0
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    .line 4497
    iget-object p1, p2, Lorg/telegram/ui/ArticleViewer$PageLayout;->webViewContainer:Lorg/telegram/ui/web/BotWebViewContainer;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object p0

    invoke-static {p1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyLinkBulletin()Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/Bulletin;->show(Z)Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private synthetic lambda$setParentActivity$24(Landroid/app/Activity;Landroid/view/View;)V
    .locals 13

    .line 4444
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-boolean p2, p2, Lorg/telegram/ui/web/WebActionBar;->longClicked:Z

    if-eqz p2, :cond_0

    goto/16 :goto_3

    .line 4445
    :cond_0
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v0, 0x0

    aget-object p2, p2, v0

    .line 4446
    invoke-virtual {p2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v1

    if-eqz v1, :cond_8

    .line 4447
    invoke-virtual {p2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-eqz v0, :cond_7

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v0}, Lorg/telegram/ui/web/WebActionBar;->isAddressing()Z

    move-result v0

    if-nez v0, :cond_7

    .line 4448
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->addressBarList:Lorg/telegram/ui/web/AddressBarList;

    if-eqz v0, :cond_6

    .line 4449
    invoke-virtual {p2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 4450
    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getTitle()Ljava/lang/String;

    move-result-object v2

    goto :goto_0

    :cond_1
    move-object v2, v1

    :goto_0
    if-eqz v0, :cond_2

    .line 4451
    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getUrl()Ljava/lang/String;

    move-result-object v3

    goto :goto_1

    :cond_2
    move-object v3, v1

    :goto_1
    invoke-static {v3}, Lorg/telegram/ui/web/BotWebViewContainer;->magic2tonsite(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 4452
    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->addressBarList:Lorg/telegram/ui/web/AddressBarList;

    if-eqz v0, :cond_3

    .line 4453
    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getFavicon()Landroid/graphics/Bitmap;

    move-result-object v1

    :cond_3
    move-object v5, v1

    .line 4454
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_4

    sget v0, Lorg/telegram/messenger/R$string;->WebEmpty:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    :cond_4
    move-object v6, v2

    .line 4455
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_5

    const-string v0, "about:blank"

    move-object v7, v0

    goto :goto_2

    :cond_5
    move-object v7, v3

    :goto_2
    new-instance v8, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda30;

    invoke-direct {v8, p0, v3}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda30;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V

    new-instance v9, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda31;

    invoke-direct {v9, p0, p2, p1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda31;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/app/Activity;)V

    new-instance v10, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda32;

    invoke-direct {v10, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda32;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    new-instance v11, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda33;

    invoke-direct {v11, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda33;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    new-instance v12, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda34;

    invoke-direct {v12, p0, v3, p2}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda34;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;Lorg/telegram/ui/ArticleViewer$PageLayout;)V

    .line 4452
    invoke-virtual/range {v4 .. v12}, Lorg/telegram/ui/web/AddressBarList;->setCurrent(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Runnable;Lorg/telegram/messenger/Utilities$Callback;Lorg/telegram/messenger/Utilities$Callback;Lorg/telegram/messenger/Utilities$Callback;Landroid/view/View$OnClickListener;)V

    .line 4501
    :cond_6
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda35;

    invoke-direct {v0, p2, p1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda35;-><init>(Lorg/telegram/ui/ArticleViewer$PageLayout;Landroid/app/Activity;)V

    const-string p1, ""

    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/web/WebActionBar;->showAddress(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    :cond_7
    :goto_3
    return-void

    .line 4525
    :cond_8
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v1, :cond_a

    .line 4526
    new-instance v1, Lorg/telegram/ui/Components/SmoothScroller;

    invoke-direct {v1, p1}, Lorg/telegram/ui/Components/SmoothScroller;-><init>(Landroid/content/Context;)V

    .line 4527
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$Sheet;->halfSize()Z

    move-result p0

    if-eqz p0, :cond_9

    const/4 p0, 0x1

    .line 4528
    invoke-virtual {v1, p0}, Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;->setTargetPosition(I)V

    const/high16 p0, 0x42000000    # 32.0f

    .line 4529
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    neg-int p0, p0

    invoke-virtual {v1, p0}, Lorg/telegram/ui/Components/SmoothScroller;->setOffset(I)V

    goto :goto_4

    .line 4531
    :cond_9
    invoke-virtual {v1, v0}, Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;->setTargetPosition(I)V

    .line 4533
    :goto_4
    iget-object p0, p2, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {p0, v1}, Landroidx/recyclerview/widget/RecyclerView$LayoutManager;->startSmoothScroll(Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;)V

    return-void

    .line 4535
    :cond_a
    iget-object p0, p2, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p0, v0}, Landroidx/recyclerview/widget/RecyclerView;->smoothScrollToPosition(I)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$25()V
    .locals 3

    .line 4564
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, v0, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LineProgressView;->getCurrentProgress()F

    move-result v0

    const v1, 0x3f333333    # 0.7f

    sub-float/2addr v1, v0

    const/4 v0, 0x0

    cmpl-float v0, v1, v0

    if-lez v0, :cond_2

    const/high16 v0, 0x3e800000    # 0.25f

    cmpg-float v0, v1, v0

    if-gez v0, :cond_0

    const v0, 0x3c23d70a    # 0.01f

    goto :goto_0

    :cond_0
    const v0, 0x3ca3d70a    # 0.02f

    .line 4572
    :goto_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, v1, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    invoke-virtual {v1}, Landroid/view/View;->getVisibility()I

    move-result v1

    if-eqz v1, :cond_1

    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->showLineProgressView()V

    .line 4573
    :cond_1
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, v1, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/LineProgressView;->getCurrentProgress()F

    move-result v2

    add-float/2addr v2, v0

    const/4 v0, 0x1

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/LineProgressView;->setProgress(FZ)V

    .line 4574
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->lineProgressTickRunnable:Ljava/lang/Runnable;

    const-wide/16 v0, 0x64

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    :cond_2
    return-void
.end method

.method private synthetic lambda$setParentActivity$26(Landroid/view/View;)V
    .locals 3

    .line 4578
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {p1}, Lorg/telegram/ui/web/WebActionBar;->isSearching()Z

    move-result p1

    .line 4580
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz p1, :cond_0

    .line 4579
    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/web/WebActionBar;->showSearch(ZZ)V

    return-void

    .line 4580
    :cond_0
    invoke-virtual {v0}, Lorg/telegram/ui/web/WebActionBar;->isAddressing()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 4581
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {p0, v2, v1}, Lorg/telegram/ui/web/WebActionBar;->showAddress(ZZ)V

    return-void

    .line 4582
    :cond_1
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v2

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->hasBackButton()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 4583
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v2

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->back()V

    return-void

    .line 4584
    :cond_2
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-le p1, v1, :cond_3

    .line 4585
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->goBack()V

    return-void

    .line 4586
    :cond_3
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p1, :cond_4

    .line 4587
    invoke-virtual {p1, v2}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    return-void

    .line 4589
    :cond_4
    invoke-virtual {p0, v1, v1}, Lorg/telegram/ui/ArticleViewer;->close(ZZ)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$28(I)V
    .locals 0

    .line 4671
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->goBack(I)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$29(I)V
    .locals 0

    .line 4727
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->goBack(I)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$30(F)V
    .locals 1

    .line 4751
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p0, p0, Lorg/telegram/ui/web/WebActionBar;->backButtonDrawable:Lorg/telegram/ui/ActionBar/BackDrawable;

    const/4 v0, 0x1

    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/ActionBar/BackDrawable;->setRotation(FZ)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$31(Lorg/telegram/ui/Components/ItemOptions;F)V
    .locals 3

    .line 4749
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, v0, Lorg/telegram/ui/web/WebActionBar;->backButtonDrawable:Lorg/telegram/ui/ActionBar/BackDrawable;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/BackDrawable;->setRotation(FZ)V

    .line 4750
    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda49;

    invoke-direct {v0, p0, p2}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda49;-><init>(Lorg/telegram/ui/ArticleViewer;F)V

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/ItemOptions;->setOnDismiss(Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 4753
    invoke-virtual {p1}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return-void
.end method

.method private synthetic lambda$setParentActivity$32(Landroid/view/View;)Z
    .locals 16

    move-object/from16 v0, p0

    .line 4593
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v2, 0x0

    aget-object v1, v1, v2

    if-nez v1, :cond_0

    return v2

    .line 4595
    :cond_0
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, v1, Lorg/telegram/ui/web/WebActionBar;->backButtonDrawable:Lorg/telegram/ui/ActionBar/BackDrawable;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/BackDrawable;->getRotation()F

    move-result v1

    .line 4596
    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v3, :cond_1

    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$Sheet;->windowView:Lorg/telegram/ui/ArticleViewer$Sheet$WindowView;

    :goto_0
    move-object/from16 v4, p1

    goto :goto_1

    :cond_1
    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    goto :goto_0

    :goto_1
    invoke-static {v3, v4}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Landroid/view/ViewGroup;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v3

    .line 4598
    sget-boolean v4, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v4, :cond_2

    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_iv_background:I

    invoke-static {v4}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v4

    goto :goto_2

    :cond_2
    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v2

    invoke-virtual {v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v4

    .line 4599
    :goto_2
    sget-boolean v5, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v5, :cond_3

    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-static {v5}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v5

    goto :goto_3

    :cond_3
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v2

    invoke-virtual {v5}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v5

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->computePerceivedBrightness(I)F

    move-result v5

    const v6, 0x3f389375    # 0.721f

    cmpl-float v5, v5, v6

    if-ltz v5, :cond_4

    const/high16 v5, -0x1000000

    goto :goto_3

    :cond_4
    const/4 v5, -0x1

    :goto_3
    const v6, 0x3f266666    # 0.65f

    .line 4600
    invoke-static {v5, v6}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v6

    .line 4602
    iget-object v7, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v2

    invoke-virtual {v7}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v7

    const/4 v8, 0x3

    if-eqz v7, :cond_7

    .line 4604
    invoke-virtual {v7}, Landroid/webkit/WebView;->copyBackForwardList()Landroid/webkit/WebBackForwardList;

    move-result-object v9

    .line 4605
    invoke-virtual {v9}, Landroid/webkit/WebBackForwardList;->getCurrentIndex()I

    move-result v10

    .line 4606
    invoke-virtual {v9}, Landroid/webkit/WebBackForwardList;->getCurrentIndex()I

    move-result v11

    if-lez v11, :cond_7

    move v11, v2

    :goto_4
    if-ge v11, v10, :cond_7

    .line 4608
    invoke-virtual {v9, v11}, Landroid/webkit/WebBackForwardList;->getItemAtIndex(I)Landroid/webkit/WebHistoryItem;

    move-result-object v12

    .line 4610
    invoke-virtual {v12}, Landroid/webkit/WebHistoryItem;->getTitle()Ljava/lang/String;

    move-result-object v13

    new-instance v14, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda25;

    invoke-direct {v14, v10, v11, v7}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda25;-><init>(IILorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V

    invoke-virtual {v3, v13, v14}, Lorg/telegram/ui/Components/ItemOptions;->add(Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 4613
    invoke-virtual {v3}, Lorg/telegram/ui/Components/ItemOptions;->getLast()Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    move-result-object v13

    if-eqz v13, :cond_6

    .line 4615
    invoke-virtual {v12}, Landroid/webkit/WebHistoryItem;->getUrl()Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v13, v14}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtext(Ljava/lang/CharSequence;)V

    .line 4616
    invoke-virtual {v12}, Landroid/webkit/WebHistoryItem;->getUrl()Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v7, v14}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getFavicon(Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v14

    if-nez v14, :cond_5

    .line 4618
    invoke-virtual {v12}, Landroid/webkit/WebHistoryItem;->getFavicon()Landroid/graphics/Bitmap;

    move-result-object v14

    .line 4621
    :cond_5
    new-instance v15, Landroid/graphics/Paint;

    invoke-direct {v15, v8}, Landroid/graphics/Paint;-><init>(I)V

    .line 4622
    invoke-virtual {v12}, Landroid/webkit/WebHistoryItem;->getTitle()Ljava/lang/String;

    move-result-object v12

    new-instance v8, Lorg/telegram/ui/ArticleViewer$18;

    invoke-direct {v8, v0, v14, v15}, Lorg/telegram/ui/ArticleViewer$18;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/graphics/Bitmap;Landroid/graphics/Paint;)V

    invoke-virtual {v13, v12, v2, v8}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextAndIcon(Ljava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;)V

    .line 4659
    invoke-virtual {v13, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextColor(I)V

    .line 4660
    invoke-virtual {v13, v6}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtextColor(I)V

    :cond_6
    add-int/lit8 v11, v11, 0x1

    const/4 v8, 0x3

    goto :goto_4

    .line 4665
    :cond_7
    iget-object v8, v0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v8}, Ljava/util/ArrayList;->size()I

    move-result v8

    add-int/lit8 v8, v8, -0x2

    :goto_5
    if-ltz v8, :cond_e

    .line 4666
    iget-object v9, v0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v9, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v9

    .line 4667
    instance-of v10, v9, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    if-eqz v10, :cond_b

    .line 4668
    check-cast v9, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    .line 4670
    invoke-virtual {v9}, Lorg/telegram/ui/ArticleViewer$CachedWeb;->getTitle()Ljava/lang/String;

    move-result-object v10

    new-instance v11, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda26;

    invoke-direct {v11, v0, v8}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda26;-><init>(Lorg/telegram/ui/ArticleViewer;I)V

    invoke-virtual {v3, v10, v11}, Lorg/telegram/ui/Components/ItemOptions;->add(Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 4673
    invoke-virtual {v3}, Lorg/telegram/ui/Components/ItemOptions;->getLast()Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    move-result-object v10

    if-eqz v10, :cond_a

    .line 4675
    iget-object v11, v9, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;->lastUrl:Ljava/lang/String;

    invoke-virtual {v10, v11}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtext(Ljava/lang/CharSequence;)V

    if-eqz v7, :cond_8

    .line 4676
    iget-object v11, v9, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;->lastUrl:Ljava/lang/String;

    invoke-virtual {v7, v11}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getFavicon(Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v11

    goto :goto_6

    :cond_8
    const/4 v11, 0x0

    :goto_6
    if-nez v11, :cond_9

    .line 4678
    iget-object v11, v9, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;->favicon:Landroid/graphics/Bitmap;

    .line 4681
    :cond_9
    new-instance v12, Landroid/graphics/Paint;

    const/4 v13, 0x3

    invoke-direct {v12, v13}, Landroid/graphics/Paint;-><init>(I)V

    .line 4682
    invoke-virtual {v9}, Lorg/telegram/ui/ArticleViewer$CachedWeb;->getTitle()Ljava/lang/String;

    move-result-object v9

    new-instance v14, Lorg/telegram/ui/ArticleViewer$19;

    invoke-direct {v14, v0, v11, v12}, Lorg/telegram/ui/ArticleViewer$19;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/graphics/Bitmap;Landroid/graphics/Paint;)V

    invoke-virtual {v10, v9, v2, v14}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextAndIcon(Ljava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;)V

    .line 4719
    invoke-virtual {v10, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextColor(I)V

    .line 4720
    invoke-virtual {v10, v6}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtextColor(I)V

    .line 4721
    invoke-virtual {v10, v5, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setColors(II)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    goto :goto_7

    :cond_a
    const/4 v13, 0x3

    goto :goto_7

    :cond_b
    const/4 v13, 0x3

    .line 4723
    instance-of v10, v9, Lorg/telegram/tgnet/TLRPC$WebPage;

    if-eqz v10, :cond_d

    .line 4724
    check-cast v9, Lorg/telegram/tgnet/TLRPC$WebPage;

    .line 4726
    iget-object v10, v9, Lorg/telegram/tgnet/TLRPC$WebPage;->title:Ljava/lang/String;

    new-instance v11, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda27;

    invoke-direct {v11, v0, v8}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda27;-><init>(Lorg/telegram/ui/ArticleViewer;I)V

    invoke-virtual {v3, v10, v11}, Lorg/telegram/ui/Components/ItemOptions;->add(Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 4729
    invoke-virtual {v3}, Lorg/telegram/ui/Components/ItemOptions;->getLast()Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    move-result-object v10

    if-eqz v10, :cond_d

    .line 4731
    iget-object v11, v9, Lorg/telegram/tgnet/TLRPC$WebPage;->title:Ljava/lang/String;

    sget v12, Lorg/telegram/messenger/R$drawable;->msg_instant:I

    invoke-virtual {v10, v11, v12}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextAndIcon(Ljava/lang/CharSequence;I)V

    .line 4732
    invoke-virtual {v10, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextColor(I)V

    .line 4733
    iget-object v11, v9, Lorg/telegram/tgnet/TLRPC$WebPage;->site_name:Ljava/lang/String;

    invoke-static {v11}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v11

    if-nez v11, :cond_c

    .line 4734
    iget-object v9, v9, Lorg/telegram/tgnet/TLRPC$WebPage;->site_name:Ljava/lang/String;

    invoke-virtual {v10, v9}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtext(Ljava/lang/CharSequence;)V

    .line 4736
    :cond_c
    invoke-virtual {v10, v6}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setSubtextColor(I)V

    .line 4737
    iget-object v9, v10, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->imageView:Lorg/telegram/ui/Components/RLottieImageView;

    invoke-virtual {v9}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v9

    const/high16 v11, 0x41c00000    # 24.0f

    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v11

    iput v11, v9, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 4738
    iget-object v9, v10, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->imageView:Lorg/telegram/ui/Components/RLottieImageView;

    const v11, 0x3fb9999a    # 1.45f

    invoke-virtual {v9, v11}, Landroid/view/View;->setScaleX(F)V

    .line 4739
    iget-object v9, v10, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->imageView:Lorg/telegram/ui/Components/RLottieImageView;

    invoke-virtual {v9, v11}, Landroid/view/View;->setScaleY(F)V

    .line 4740
    invoke-virtual {v10, v5, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setColors(II)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    :cond_d
    :goto_7
    add-int/lit8 v8, v8, -0x1

    goto/16 :goto_5

    :cond_e
    const/high16 v5, 0x42200000    # 40.0f

    .line 4744
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v6}, Lorg/telegram/ui/web/WebActionBar;->getBackgroundColor()I

    move-result v6

    invoke-static {v5, v6}, Lorg/telegram/ui/ActionBar/Theme;->createCircleDrawable(II)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v5

    invoke-virtual {v3, v5}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 4745
    invoke-virtual {v3, v4}, Lorg/telegram/ui/Components/ItemOptions;->setBackgroundColor(I)Lorg/telegram/ui/Components/ItemOptions;

    .line 4746
    invoke-virtual {v3}, Lorg/telegram/ui/Components/ItemOptions;->updateColors()V

    .line 4747
    invoke-virtual {v3}, Lorg/telegram/ui/Components/ItemOptions;->getItemsCount()I

    move-result v4

    if-gtz v4, :cond_f

    return v2

    .line 4748
    :cond_f
    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda28;

    invoke-direct {v2, v0, v3, v1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda28;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/Components/ItemOptions;F)V

    invoke-direct {v0, v2}, Lorg/telegram/ui/ArticleViewer;->checkScrollAnimated(Ljava/lang/Runnable;)V

    const/4 v0, 0x1

    return v0
.end method

.method private synthetic lambda$setParentActivity$33()V
    .locals 1

    .line 4860
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$34()V
    .locals 1

    .line 4867
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$35(Ljava/lang/String;)V
    .locals 1

    .line 4897
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    const/4 v0, 0x0

    invoke-static {p0, p1, v0}, Lorg/telegram/messenger/browser/Browser;->openInExternalBrowser(Landroid/content/Context;Ljava/lang/String;Z)Z

    return-void
.end method

.method private synthetic lambda$setParentActivity$36(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V
    .locals 2

    .line 4900
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, p1, v1}, Lorg/telegram/messenger/MessagesController;->addWebBrowserException(Ljava/lang/String;Z)V

    .line 4901
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {p2, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    .line 4902
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    invoke-virtual {p1, p2, v1}, Lorg/telegram/messenger/MessagesController;->addWebBrowserException(Ljava/lang/String;Z)V

    .line 4904
    :cond_0
    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-nez p1, :cond_1

    .line 4905
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->showRestrictedWebsiteToast()V

    return-void

    .line 4907
    :cond_1
    new-instance p1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda50;

    invoke-direct {p1, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda50;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    sput-object p1, Lorg/telegram/ui/LaunchActivity;->whenResumed:Ljava/lang/Runnable;

    return-void
.end method

.method private synthetic lambda$setParentActivity$38(Landroid/view/View;)V
    .locals 5

    .line 4954
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 4955
    iput p1, p0, Lorg/telegram/ui/IArticleViewer;->selectedFont:I

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    const/4 v2, 0x2

    if-ge v1, v2, :cond_1

    .line 4957
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    aget-object v2, v2, v1

    const/4 v3, 0x1

    if-ne v1, p1, :cond_0

    move v4, v3

    goto :goto_1

    :cond_0
    move v4, v0

    :goto_1
    invoke-virtual {v2, v4, v3}, Lorg/telegram/ui/ArticleViewer$FontCell;->select(ZZ)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 4959
    :cond_1
    sget-object p1, Lorg/telegram/ui/ArticleViewer;->resources:Lorg/telegram/ui/ArticleViewer$Resources;

    iget v1, p0, Lorg/telegram/ui/IArticleViewer;->selectedFont:I

    invoke-virtual {p1, v1}, Lorg/telegram/ui/ArticleViewer$Resources;->updatePaintFonts(I)V

    .line 4960
    :goto_2
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length v1, p1

    if-ge v0, v1, :cond_2

    .line 4961
    aget-object p1, p1, v0

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_2
    return-void
.end method

.method private synthetic lambda$setParentActivity$39(Landroid/app/Activity;Ljava/lang/Integer;)V
    .locals 14

    .line 4825
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isArticle()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    if-eqz v0, :cond_21

    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-nez v0, :cond_1

    goto/16 :goto_8

    .line 4828
    :cond_1
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_2

    .line 4829
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/high16 v1, 0x42600000    # 56.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    invoke-virtual {v0, v1}, Lorg/telegram/ui/web/WebActionBar;->setHeight(I)V

    .line 4830
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {p0, v2, v2}, Lorg/telegram/ui/web/WebActionBar;->showSearch(ZZ)V

    return-void

    .line 4831
    :cond_2
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v3, 0x2

    const/4 v4, 0x0

    if-ne v0, v3, :cond_7

    .line 4833
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    .line 4837
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz v0, :cond_4

    .line 4834
    aget-object v0, v2, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_3

    goto/16 :goto_8

    .line 4835
    :cond_3
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getUrl()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 4837
    :cond_4
    aget-object v0, v2, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    if-nez v0, :cond_5

    goto/16 :goto_8

    .line 4838
    :cond_5
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    .line 4840
    :goto_0
    invoke-static {v0}, Lorg/telegram/ui/web/BotWebViewContainer;->magic2tonsite(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 4841
    new-instance v5, Lorg/telegram/ui/Components/ShareAlert;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v0}, Lorg/telegram/ui/web/WebActionBar;->getBackgroundColor()I

    move-result v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->computePerceivedBrightness(I)F

    move-result v0

    const v1, 0x3f389375    # 0.721f

    cmpg-float v0, v0, v1

    if-gez v0, :cond_6

    new-instance v4, Lorg/telegram/ui/Stories/DarkThemeResourceProvider;

    invoke-direct {v4}, Lorg/telegram/ui/Stories/DarkThemeResourceProvider;-><init>()V

    :cond_6
    move-object v12, v4

    const/4 v7, 0x0

    const/4 v9, 0x0

    const/4 v11, 0x0

    move-object v10, v8

    invoke-direct/range {v5 .. v12}, Lorg/telegram/ui/Components/ShareAlert;-><init>(Landroid/content/Context;Ljava/util/ArrayList;Ljava/lang/String;ZLjava/lang/String;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    invoke-virtual {p0, v5}, Lorg/telegram/ui/ArticleViewer;->showDialog(Landroid/app/Dialog;)V

    return-void

    .line 4842
    :cond_7
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v5, 0x6

    if-ne v0, v5, :cond_b

    .line 4845
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    .line 4850
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz v0, :cond_9

    .line 4846
    aget-object v0, v2, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_8

    goto/16 :goto_8

    .line 4847
    :cond_8
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getUrl()Ljava/lang/String;

    move-result-object v0

    .line 4848
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v2, v1

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->webViewContainer:Lorg/telegram/ui/web/BotWebViewContainer;

    goto :goto_1

    .line 4850
    :cond_9
    aget-object v0, v2, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    if-nez v0, :cond_a

    goto/16 :goto_8

    .line 4851
    :cond_a
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    .line 4852
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v2, v1

    .line 4854
    :goto_1
    iget v2, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object p0

    invoke-static {v0, v2, v1, v3, p0}, Lorg/telegram/ui/ArticleViewer;->addBookmark(Ljava/lang/String;ILandroid/widget/FrameLayout;Lorg/telegram/ui/ArticleViewer$Sheet;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-void

    .line 4855
    :cond_b
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v5, 0x7

    if-ne v0, v5, :cond_d

    .line 4856
    new-instance v0, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;

    invoke-direct {v0}, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;-><init>()V

    .line 4857
    iput-boolean v2, v0, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;->transitionFromLeft:Z

    .line 4858
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 4860
    new-instance v2, Lorg/telegram/ui/web/BookmarksFragment;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v3, :cond_c

    goto :goto_2

    :cond_c
    new-instance v4, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda38;

    invoke-direct {v4, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda38;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    :goto_2
    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda33;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda33;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-direct {v2, v4, v3}, Lorg/telegram/ui/web/BookmarksFragment;-><init>(Ljava/lang/Runnable;Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showAsSheet(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;)[Lorg/telegram/ui/ActionBar/INavigationLayout;

    return-void

    .line 4862
    :cond_d
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/16 v5, 0x8

    if-ne v0, v5, :cond_f

    .line 4863
    new-instance v0, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;

    invoke-direct {v0}, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;-><init>()V

    .line 4864
    iput-boolean v2, v0, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;->transitionFromLeft:Z

    .line 4865
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 4867
    new-instance v2, Lorg/telegram/ui/web/HistoryFragment;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v3, :cond_e

    goto :goto_3

    :cond_e
    new-instance v4, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda39;

    invoke-direct {v4, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda39;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    :goto_3
    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda40;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda40;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-direct {v2, v4, v3}, Lorg/telegram/ui/web/HistoryFragment;-><init>(Ljava/lang/Runnable;Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showAsSheet(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;)[Lorg/telegram/ui/ActionBar/INavigationLayout;

    return-void

    .line 4869
    :cond_f
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/16 v5, 0x9

    if-ne v0, v5, :cond_10

    .line 4870
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-eqz v0, :cond_21

    .line 4871
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v1

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->goForward()V

    return-void

    .line 4873
    :cond_10
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v5, 0x3

    if-ne v0, v5, :cond_19

    .line 4876
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    .line 4881
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz v0, :cond_12

    .line 4877
    aget-object v0, v3, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_11

    goto/16 :goto_8

    .line 4878
    :cond_11
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getUrl()Ljava/lang/String;

    move-result-object v0

    .line 4879
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v1

    invoke-virtual {v3}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v3

    invoke-virtual {v3}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getOpenURL()Ljava/lang/String;

    move-result-object v3

    move-object v7, v0

    move-object v8, v4

    move-object v4, v3

    goto :goto_4

    .line 4881
    :cond_12
    aget-object v0, v3, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    if-nez v0, :cond_13

    goto/16 :goto_8

    .line 4882
    :cond_13
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    .line 4884
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v1

    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v3

    iget-object v3, v3, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    if-eqz v3, :cond_14

    .line 4885
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v1

    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v3

    iget-object v3, v3, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v3, v3, Lorg/telegram/tgnet/tl/TL_iv$Page;->local:Ljava/io/File;

    move-object v7, v0

    move-object v8, v3

    goto :goto_4

    :cond_14
    move-object v7, v0

    move-object v8, v4

    .line 4888
    :goto_4
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-eqz v0, :cond_21

    invoke-virtual {v0}, Landroid/app/Activity;->isFinishing()Z

    move-result v0

    if-eqz v0, :cond_15

    goto/16 :goto_8

    :cond_15
    if-eqz v8, :cond_16

    .line 4890
    iget-object v11, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v12

    const/4 v13, 0x1

    const/4 v9, 0x0

    const-string/jumbo v10, "text/markdown"

    invoke-static/range {v8 .. v13}, Lorg/telegram/messenger/AndroidUtilities;->openForView(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Landroid/app/Activity;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)Z

    return-void

    :cond_16
    if-nez v7, :cond_17

    goto/16 :goto_8

    .line 4894
    :cond_17
    invoke-static {v4, v2}, Lorg/telegram/messenger/AndroidUtilities;->getHostAuthority(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    .line 4895
    invoke-static {v7, v2}, Lorg/telegram/messenger/AndroidUtilities;->getHostAuthority(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v3

    .line 4896
    new-instance v4, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda41;

    invoke-direct {v4, p0, v7}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda41;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V

    .line 4899
    new-instance v5, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda42;

    invoke-direct {v5, p0, v3, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda42;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;Ljava/lang/String;)V

    .line 4910
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    if-eqz v0, :cond_18

    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    invoke-virtual {v0, v3}, Lorg/telegram/messenger/MessagesController;->isWebBrowserOpenInApp(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_18

    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    invoke-virtual {v0, v2}, Lorg/telegram/messenger/MessagesController;->isWebBrowserExceptionsLimitReached(Z)Z

    move-result v0

    if-nez v0, :cond_18

    .line 4911
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v6

    new-instance v10, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda43;

    invoke-direct {v10, v5, v4}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda43;-><init>(Lorg/telegram/messenger/Utilities$Callback;Ljava/lang/Runnable;)V

    const/4 v8, 0x1

    const/4 v9, 0x1

    move-object v5, p1

    invoke-static/range {v5 .. v10}, Lorg/telegram/ui/Components/AlertsCreator;->showOpenExternalBrowserAlert(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Ljava/lang/String;ZZLorg/telegram/messenger/Utilities$Callback2;)V

    return-void

    .line 4917
    :cond_18
    invoke-interface {v4}, Ljava/lang/Runnable;->run()V

    return-void

    .line 4918
    :cond_19
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v5, 0x4

    if-ne v0, v5, :cond_1f

    .line 4919
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    if-eqz v0, :cond_1a

    .line 4920
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->openWebSettings()V

    return-void

    .line 4923
    :cond_1a
    new-instance v0, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v0, v4}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;-><init>(Landroid/content/Context;)V

    .line 4924
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setApplyTopPadding(Z)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 4926
    new-instance v4, Landroid/widget/LinearLayout;

    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v4, v5}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/high16 v5, 0x40800000    # 4.0f

    .line 4927
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    invoke-virtual {v4, v1, v1, v1, v5}, Landroid/view/View;->setPadding(IIII)V

    .line 4928
    invoke-virtual {v4, v2}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 4930
    new-instance v5, Lorg/telegram/ui/Cells/HeaderCell;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v7

    invoke-direct {v5, v6, v7}, Lorg/telegram/ui/Cells/HeaderCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 4931
    sget v6, Lorg/telegram/messenger/R$string;->FontSize:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Lorg/telegram/ui/Cells/HeaderCell;->setText(Ljava/lang/CharSequence;)V

    const/4 v12, 0x3

    const/4 v13, 0x0

    const/4 v7, -0x2

    const/4 v8, -0x2

    const/16 v9, 0x33

    const/4 v10, 0x3

    const/4 v11, 0x1

    .line 4932
    invoke-static/range {v7 .. v13}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v6

    invoke-virtual {v4, v5, v6}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4934
    new-instance v5, Lorg/telegram/ui/ArticleViewer$TextSizeCell;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v5, p0, v6}, Lorg/telegram/ui/ArticleViewer$TextSizeCell;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    const/4 v7, -0x1

    const/4 v11, 0x0

    .line 4935
    invoke-static/range {v7 .. v13}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v6

    invoke-virtual {v4, v5, v6}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4937
    new-instance v5, Lorg/telegram/ui/Cells/HeaderCell;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v7

    invoke-direct {v5, v6, v7}, Lorg/telegram/ui/Cells/HeaderCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 4938
    sget v6, Lorg/telegram/messenger/R$string;->FontType:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Lorg/telegram/ui/Cells/HeaderCell;->setText(Ljava/lang/CharSequence;)V

    const/4 v13, 0x2

    const/4 v7, -0x2

    const/4 v11, 0x4

    .line 4939
    invoke-static/range {v7 .. v13}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v6

    invoke-virtual {v4, v5, v6}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    move v5, v1

    :goto_5
    if-ge v5, v3, :cond_1e

    .line 4942
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    new-instance v7, Lorg/telegram/ui/ArticleViewer$FontCell;

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v7, p0, v8}, Lorg/telegram/ui/ArticleViewer$FontCell;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    aput-object v7, v6, v5

    if-eqz v5, :cond_1c

    if-eq v5, v2, :cond_1b

    goto :goto_6

    .line 4948
    :cond_1b
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    aget-object v6, v6, v5

    const-string v7, "Serif"

    sget-object v8, Landroid/graphics/Typeface;->SERIF:Landroid/graphics/Typeface;

    invoke-virtual {v6, v7, v8}, Lorg/telegram/ui/ArticleViewer$FontCell;->setTextAndTypeface(Ljava/lang/String;Landroid/graphics/Typeface;)V

    goto :goto_6

    .line 4945
    :cond_1c
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    aget-object v6, v6, v5

    sget v7, Lorg/telegram/messenger/R$string;->Default:I

    invoke-static {v7}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    sget-object v8, Landroid/graphics/Typeface;->DEFAULT:Landroid/graphics/Typeface;

    invoke-virtual {v6, v7, v8}, Lorg/telegram/ui/ArticleViewer$FontCell;->setTextAndTypeface(Ljava/lang/String;Landroid/graphics/Typeface;)V

    .line 4951
    :goto_6
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    aget-object v6, v6, v5

    iget v7, p0, Lorg/telegram/ui/IArticleViewer;->selectedFont:I

    if-ne v5, v7, :cond_1d

    move v7, v2

    goto :goto_7

    :cond_1d
    move v7, v1

    :goto_7
    invoke-virtual {v6, v7, v1}, Lorg/telegram/ui/ArticleViewer$FontCell;->select(ZZ)V

    .line 4952
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    aget-object v6, v6, v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v6, v7}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    .line 4953
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    aget-object v6, v6, v5

    new-instance v7, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda44;

    invoke-direct {v7, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda44;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v6, v7}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 4964
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->fontCells:[Lorg/telegram/ui/ArticleViewer$FontCell;

    aget-object v6, v6, v5

    const/4 v7, -0x1

    const/16 v8, 0x32

    invoke-static {v7, v8}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(II)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v7

    invoke-virtual {v4, v6, v7}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    add-int/lit8 v5, v5, 0x1

    goto :goto_5

    .line 4967
    :cond_1e
    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setCustomView(Landroid/view/View;)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 4968
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->create()Lorg/telegram/ui/ActionBar/BottomSheet;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/IArticleViewer;->linkSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer;->showDialog(Landroid/app/Dialog;)V

    return-void

    .line 4970
    :cond_1f
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v3, 0x5

    if-ne v0, v3, :cond_20

    .line 4971
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    if-eqz v0, :cond_21

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-eqz v0, :cond_21

    .line 4972
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v1

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->reload()V

    return-void

    .line 4974
    :cond_20
    invoke-virtual/range {p2 .. p2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/16 v3, 0xa

    if-ne v0, v3, :cond_21

    .line 4975
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->currentInstantLoader:Lorg/telegram/ui/web/WebInstantView$Loader;

    if-eqz v0, :cond_21

    .line 4976
    invoke-virtual {v0}, Lorg/telegram/ui/web/WebInstantView$Loader;->getWebPage()Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 4977
    invoke-virtual {v0}, Lorg/telegram/ui/web/WebInstantView$Loader;->getWebPage()Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    invoke-direct {p0, v0, v4, v2}, Lorg/telegram/ui/ArticleViewer;->addPageToStack(Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;I)Z

    :cond_21
    :goto_8
    return-void
.end method

.method private synthetic lambda$setParentActivity$40(Landroid/view/View;)V
    .locals 0

    .line 4983
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    .line 4989
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    :cond_0
    return-void
.end method

.method private synthetic lambda$setParentActivity$42(Ljava/lang/Integer;)V
    .locals 3

    .line 5014
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    neg-int p1, p1

    int-to-float p1, p1

    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelTranslation:F

    const/high16 v1, 0x424c0000    # 51.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    const/high16 v2, 0x3f800000    # 1.0f

    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAlpha:F

    sub-float/2addr v2, p0

    mul-float/2addr v1, v2

    add-float/2addr p1, v1

    invoke-virtual {v0, p1}, Landroid/view/View;->setTranslationY(F)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$43(Landroid/view/View;)V
    .locals 1

    .line 5024
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v0, 0x0

    aget-object p1, p1, v0

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 5025
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v0

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 5026
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v0

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0, v0}, Landroid/webkit/WebView;->findNext(Z)V

    :cond_0
    return-void

    .line 5029
    :cond_1
    iget p1, p0, Lorg/telegram/ui/IArticleViewer;->currentSearchIndex:I

    add-int/lit8 p1, p1, -0x1

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->scrollToSearchIndex(I)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$44(Landroid/view/View;)V
    .locals 2

    .line 5041
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v0, 0x0

    aget-object p1, p1, v0

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result p1

    const/4 v1, 0x1

    if-eqz p1, :cond_1

    .line 5042
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v0

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 5043
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v0

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0, v1}, Landroid/webkit/WebView;->findNext(Z)V

    :cond_0
    return-void

    .line 5046
    :cond_1
    iget p1, p0, Lorg/telegram/ui/IArticleViewer;->currentSearchIndex:I

    add-int/2addr p1, v1

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->scrollToSearchIndex(I)V

    return-void
.end method

.method private synthetic lambda$setParentActivity$45(Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 10

    .line 5091
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    iget v2, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v6, 0x0

    move-object v5, p1

    move-object v3, p2

    move-object v4, p3

    move-object v9, p4

    invoke-static/range {v0 .. v9}, Lorg/telegram/ui/Components/TranslateAlert2;->showAlert(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;ILjava/lang/String;Ljava/lang/String;Ljava/lang/CharSequence;Ljava/util/ArrayList;ZLorg/telegram/messenger/Utilities$CallbackReturn;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/TranslateAlert2;

    return-void
.end method

.method private synthetic lambda$setParentActivity$46([F)V
    .locals 2

    .line 5119
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    int-to-float v0, v0

    const/4 v1, 0x0

    aput v0, p1, v1

    .line 5120
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v1

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result p0

    int-to-float p0, p0

    const/4 v0, 0x1

    aput p0, p1, v0

    return-void
.end method

.method private synthetic lambda$showCopyPopup$0(Ljava/lang/String;Landroid/content/DialogInterface;I)V
    .locals 3

    .line 1563
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-eqz p2, :cond_a

    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v0, 0x0

    aget-object p2, p2, v0

    iget-object p2, p2, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p2

    if-nez p2, :cond_0

    goto/16 :goto_4

    :cond_0
    const/4 p2, 0x1

    if-nez p3, :cond_6

    const/16 p3, 0x23

    .line 1568
    invoke-virtual {p1, p3}, Ljava/lang/String;->lastIndexOf(I)I

    move-result p3

    const/4 v1, -0x1

    if-eq p3, v1, :cond_5

    .line 1570
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v0

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v1

    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v1, v1, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    .line 1573
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-nez v1, :cond_1

    .line 1571
    aget-object v1, v2, v0

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v1

    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v1, v1, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    .line 1573
    :cond_1
    aget-object v1, v2, v0

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v1

    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    :goto_0
    add-int/2addr p3, p2

    .line 1577
    :try_start_0
    invoke-virtual {p1, p3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p3

    const-string v2, "UTF-8"

    invoke-static {p3, v2}, Lj$/net/URLDecoder;->decode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 1579
    :catch_0
    const-string p3, ""

    .line 1581
    :goto_1
    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_5

    .line 1582
    invoke-static {p3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 1583
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v0

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p3, :cond_2

    invoke-virtual {p3}, Lorg/telegram/ui/ArticleViewer$Sheet;->halfSize()Z

    move-result p3

    if-eqz p3, :cond_2

    goto :goto_2

    :cond_2
    move p2, v0

    :goto_2
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p3, :cond_3

    const/high16 p3, 0x42000000    # 32.0f

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    :cond_3
    invoke-virtual {p1, p2, v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->scrollToPositionWithOffset(II)V

    .line 1584
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->checkScrollAnimated()V

    goto :goto_4

    .line 1586
    :cond_4
    invoke-virtual {p0, p3, p2}, Lorg/telegram/ui/ArticleViewer;->scrollToAnchor(Ljava/lang/String;Z)Z

    goto :goto_4

    .line 1591
    :cond_5
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    :cond_6
    if-ne p3, p2, :cond_a

    if-nez p1, :cond_7

    goto :goto_4

    .line 1595
    :cond_7
    const-string/jumbo p0, "mailto:"

    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_8

    const/4 p0, 0x7

    .line 1596
    invoke-virtual {p1, p0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_3

    .line 1597
    :cond_8
    const-string/jumbo p0, "tel:"

    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_9

    const/4 p0, 0x4

    .line 1598
    invoke-virtual {p1, p0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    .line 1600
    :cond_9
    :goto_3
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    :cond_a
    :goto_4
    return-void
.end method

.method private synthetic lambda$showCopyPopup$1(Landroid/content/DialogInterface;)V
    .locals 0

    .line 1603
    iget-object p0, p0, Lorg/telegram/ui/IArticleViewer;->links:Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;->clear()V

    return-void
.end method

.method private synthetic lambda$showDialog$66(Landroid/content/DialogInterface;)V
    .locals 0

    const/4 p1, 0x0

    .line 6291
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->visibleDialog:Landroid/app/Dialog;

    return-void
.end method

.method private synthetic lambda$showPopup$2(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 1

    .line 1621
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    if-nez v0, :cond_0

    .line 1622
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/widget/PopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1623
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupRect:Landroid/graphics/Rect;

    invoke-virtual {p1, v0}, Landroid/view/View;->getHitRect(Landroid/graphics/Rect;)V

    .line 1624
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->popupRect:Landroid/graphics/Rect;

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    float-to-int v0, v0

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result p2

    float-to-int p2, p2

    invoke-virtual {p1, v0, p2}, Landroid/graphics/Rect;->contains(II)Z

    move-result p1

    if-nez p1, :cond_0

    .line 1625
    iget-object p0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;->dismiss()V

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic lambda$showPopup$3(Landroid/view/KeyEvent;)V
    .locals 2

    .line 1632
    invoke-virtual {p1}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    invoke-virtual {p1}, Landroid/view/KeyEvent;->getRepeatCount()I

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/widget/PopupWindow;->isShowing()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 1633
    iget-object p0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;->dismiss()V

    :cond_0
    return-void
.end method

.method private synthetic lambda$showPopup$4(Landroid/view/View;)V
    .locals 2

    .line 1646
    iget-object p1, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    if-eqz p1, :cond_0

    .line 1647
    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$DrawingText;->getText()Ljava/lang/CharSequence;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    .line 1648
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->shouldShowClipboardToast()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 1649
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    sget v0, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-static {p1, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 1652
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/widget/PopupWindow;->isShowing()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 1653
    iget-object p0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;->dismiss(Z)V

    :cond_1
    return-void
.end method

.method private synthetic lambda$showPopup$5()V
    .locals 2

    .line 1667
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerView:Landroid/view/View;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    .line 1668
    iput-object v1, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    .line 1669
    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    .line 1670
    iput-object v1, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerView:Landroid/view/View;

    :cond_0
    return-void
.end method

.method private synthetic lambda$showSearchPanel$51(Landroid/animation/ValueAnimator;)V
    .locals 2

    .line 5395
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAlpha:F

    .line 5396
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelTranslation:F

    const/high16 v1, 0x3f800000    # 1.0f

    sub-float/2addr v1, p1

    const/high16 p1, 0x424c0000    # 51.0f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    mul-float/2addr v1, p1

    add-float/2addr p0, v1

    invoke-virtual {v0, p0}, Landroid/view/View;->setTranslationY(F)V

    return-void
.end method

.method public static loadChannel(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$BlockChannelCell;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 3

    .line 6125
    iget-boolean v0, p0, Lorg/telegram/ui/IArticleViewer;->loadingChannel:Z

    if-nez v0, :cond_1

    invoke-static {p3}, Lorg/telegram/messenger/ChatObject;->isPublic(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 6128
    iput-boolean v0, p0, Lorg/telegram/ui/IArticleViewer;->loadingChannel:Z

    .line 6129
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getCurrentAccount()I

    move-result v0

    .line 6130
    new-instance v1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolveUsername;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolveUsername;-><init>()V

    .line 6131
    iget-object p3, p3, Lorg/telegram/tgnet/TLRPC$Chat;->username:Ljava/lang/String;

    iput-object p3, v1, Lorg/telegram/tgnet/TLRPC$TL_contacts_resolveUsername;->username:Ljava/lang/String;

    .line 6132
    invoke-static {v0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p3

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda52;

    invoke-direct {v2, p0, p2, v0, p1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda52;-><init>(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;ILorg/telegram/ui/ArticleViewer$BlockChannelCell;)V

    invoke-virtual {p3, v1, v2}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    :cond_1
    :goto_0
    return-void
.end method

.method private makeProgress(Lorg/telegram/ui/Components/LinkSpanDrawable;Lorg/telegram/ui/ArticleViewer$DrawingText;)Lorg/telegram/messenger/browser/Browser$Progress;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/ui/Components/LinkSpanDrawable<",
            "Lorg/telegram/ui/Components/TextPaintUrlSpan;",
            ">;",
            "Lorg/telegram/ui/ArticleViewer$DrawingText;",
            ")",
            "Lorg/telegram/messenger/browser/Browser$Progress;"
        }
    .end annotation

    .line 3860
    invoke-static {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->makeProgress(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/Components/LinkSpanDrawable;Lorg/telegram/ui/ArticleViewer$DrawingText;)Lorg/telegram/messenger/browser/Browser$Progress;

    move-result-object p0

    return-object p0
.end method

.method private static makeProgress(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/Components/LinkSpanDrawable;Lorg/telegram/ui/ArticleViewer$DrawingText;)Lorg/telegram/messenger/browser/Browser$Progress;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/ui/IArticleViewer;",
            "Lorg/telegram/ui/Components/LinkSpanDrawable<",
            "Lorg/telegram/ui/Components/TextPaintUrlSpan;",
            ">;",
            "Lorg/telegram/ui/ArticleViewer$DrawingText;",
            ")",
            "Lorg/telegram/messenger/browser/Browser$Progress;"
        }
    .end annotation

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 3864
    :cond_0
    new-instance v0, Lorg/telegram/ui/ArticleViewer$13;

    invoke-direct {v0, p0, p2, p1}, Lorg/telegram/ui/ArticleViewer$13;-><init>(Lorg/telegram/ui/IArticleViewer;Lorg/telegram/ui/ArticleViewer$DrawingText;Lorg/telegram/ui/Components/LinkSpanDrawable;)V

    return-object v0
.end method

.method public static makeSheet(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/ArticleViewer;
    .locals 1

    .line 409
    new-instance v0, Lorg/telegram/ui/ArticleViewer;

    invoke-direct {v0, p0}, Lorg/telegram/ui/ArticleViewer;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-object v0
.end method

.method private onClosed()V
    .locals 4

    const/4 v0, 0x0

    .line 6098
    iput-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    move v1, v0

    .line 6099
    :goto_0
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length v3, v2

    if-ge v1, v3, :cond_0

    .line 6100
    aget-object v2, v2, v1

    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->cleanup()V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 6103
    :cond_0
    :try_start_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {v1}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v1

    const/16 v2, 0x80

    invoke-virtual {v1, v2}, Landroid/view/Window;->clearFlags(I)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception v1

    .line 6105
    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :goto_1
    move v1, v0

    .line 6107
    :goto_2
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->createdWebViews:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_1

    .line 6108
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->createdWebViews:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/ui/ArticleViewer$BlockEmbedCell;

    .line 6109
    invoke-virtual {v2, v0}, Lorg/telegram/ui/ArticleViewer$BlockEmbedCell;->destroyWebView(Z)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    .line 6111
    :cond_1
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda8;

    invoke-direct {v2, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda8;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v1, v2}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    .line 6121
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->articleClosed:I

    new-array v0, v0, [Ljava/lang/Object;

    invoke-virtual {p0, v1, v0}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method private open(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z
    .locals 15

    move-object/from16 v4, p1

    move-object/from16 v0, p3

    .line 5580
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    const/4 v7, 0x0

    if-eqz v1, :cond_1e

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v1, :cond_0

    iget-boolean v1, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    if-eqz v1, :cond_0

    iget-boolean v1, p0, Lorg/telegram/ui/ArticleViewer;->collapsed:Z

    if-nez v1, :cond_0

    goto/16 :goto_13

    .line 5584
    :cond_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v1

    instance-of v1, v1, Lorg/telegram/ui/ActionBar/ActionBarLayout;

    if-eqz v1, :cond_1

    .line 5585
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v1

    check-cast v1, Lorg/telegram/ui/ActionBar/ActionBarLayout;

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->hideKeyboard(Landroid/view/View;)V

    :cond_1
    const/16 v1, 0x23

    const/4 v8, -0x1

    const/4 v9, 0x0

    const/4 v10, 0x1

    if-eqz v4, :cond_8

    if-nez p2, :cond_2

    .line 5592
    iget-object v0, v4, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$MessageMedia;->webpage:Lorg/telegram/tgnet/TLRPC$WebPage;

    move-object v2, v0

    goto :goto_0

    :cond_2
    move-object/from16 v2, p2

    :goto_0
    if-eqz v2, :cond_3

    .line 5594
    iget-object v0, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    if-eqz v0, :cond_3

    iget-object v0, v0, Lorg/telegram/tgnet/tl/TL_iv$Page;->local:Ljava/io/File;

    if-eqz v0, :cond_3

    move v3, v10

    goto :goto_1

    :cond_3
    move v3, v7

    :goto_1
    move v5, v7

    .line 5598
    :goto_2
    iget-object v0, v4, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$Message;->entities:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v5, v0, :cond_7

    .line 5599
    iget-object v0, v4, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$Message;->entities:Ljava/util/ArrayList;

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/tgnet/TLRPC$MessageEntity;

    .line 5600
    instance-of v6, v0, Lorg/telegram/tgnet/TLRPC$TL_messageEntityUrl;

    if-eqz v6, :cond_6

    .line 5602
    :try_start_0
    iget-object v6, v4, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object v6, v6, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    iget v11, v0, Lorg/telegram/tgnet/TLRPC$MessageEntity;->offset:I

    iget v0, v0, Lorg/telegram/tgnet/TLRPC$MessageEntity;->length:I

    add-int/2addr v0, v11

    invoke-virtual {v6, v11, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    .line 5604
    iget-object v6, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v6, v6, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-nez v6, :cond_4

    .line 5605
    iget-object v6, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v6, v6, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-virtual {v6}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v6

    goto :goto_3

    :catch_0
    move-exception v0

    goto :goto_4

    .line 5607
    :cond_4
    iget-object v6, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    invoke-virtual {v6}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v6

    .line 5609
    :goto_3
    invoke-virtual {v0, v6}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v11

    if-nez v11, :cond_5

    invoke-virtual {v6, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_6

    .line 5610
    :cond_5
    invoke-virtual {v0, v1}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v6

    if-eq v6, v8, :cond_7

    add-int/lit8 v6, v6, 0x1

    .line 5611
    invoke-virtual {v0, v6}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_5

    .line 5616
    :goto_4
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_6
    add-int/lit8 v5, v5, 0x1

    goto :goto_2

    :cond_7
    move-object v0, v9

    :goto_5
    move v1, v3

    move-object v3, v2

    goto :goto_6

    :cond_8
    if-eqz v0, :cond_9

    .line 5622
    invoke-virtual {v0, v1}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v1

    if-eq v1, v8, :cond_9

    add-int/2addr v1, v10

    .line 5623
    invoke-virtual {v0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    move-object/from16 v3, p2

    move v1, v7

    goto :goto_6

    :cond_9
    move-object/from16 v3, p2

    move v1, v7

    move-object v0, v9

    .line 5627
    :goto_6
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v2, :cond_a

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_a

    move v5, v10

    goto :goto_7

    :cond_a
    move v5, v7

    .line 5629
    :goto_7
    iput-boolean v7, p0, Lorg/telegram/ui/ArticleViewer;->collapsed:Z

    const/high16 v11, 0x42600000    # 56.0f

    const/4 v12, 0x0

    if-eqz v5, :cond_b

    goto :goto_8

    .line 5633
    :cond_b
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->clear()V

    .line 5634
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v12}, Landroid/view/View;->setTranslationX(F)V

    .line 5635
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v2, :cond_c

    .line 5636
    invoke-virtual {v2, v12}, Lorg/telegram/ui/ArticleViewer$Sheet;->setBackProgress(F)V

    .line 5638
    :cond_c
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v12}, Landroid/view/View;->setTranslationY(F)V

    .line 5639
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v7

    invoke-virtual {v2, v12}, Landroid/view/View;->setTranslationY(F)V

    .line 5640
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v7

    invoke-virtual {v2, v12}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setTranslationX(F)V

    .line 5641
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v10

    invoke-virtual {v2, v12}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setTranslationX(F)V

    .line 5642
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v7

    const/high16 v6, 0x3f800000    # 1.0f

    invoke-virtual {v2, v6}, Landroid/view/View;->setAlpha(F)V

    .line 5643
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v2, v12}, Lorg/telegram/ui/ArticleViewer$WindowView;->setInnerTranslationX(F)V

    .line 5645
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v7

    invoke-virtual {v2, v7}, Lorg/telegram/ui/ArticleViewer$PageLayout;->scrollToTop(Z)V

    .line 5647
    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-direct {p0, v2}, Lorg/telegram/ui/ArticleViewer;->setCurrentHeaderHeight(I)V

    .line 5655
    :goto_8
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v2, :cond_d

    sget-boolean v6, Lorg/telegram/ui/web/BotWebViewContainer;->firstWebView:Z

    if-eqz v6, :cond_d

    .line 5656
    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$Sheet;->animationsLock:Lorg/telegram/messenger/AnimationNotificationsLocker;

    invoke-virtual {v2}, Lorg/telegram/messenger/AnimationNotificationsLocker;->lock()V

    :cond_d
    if-eqz v3, :cond_12

    .line 5660
    invoke-direct {p0, v3, v0, v5}, Lorg/telegram/ui/ArticleViewer;->addPageToStack(Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;I)Z

    move-result v2

    if-nez v1, :cond_13

    if-nez v2, :cond_e

    if-eqz v0, :cond_e

    move-object v6, v0

    goto :goto_9

    :cond_e
    move-object v6, v9

    .line 5663
    :goto_9
    new-instance v13, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;

    invoke-direct {v13}, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;-><init>()V

    .line 5664
    iget-object v0, v3, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    iput-object v0, v13, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    .line 5665
    iget-object v0, v3, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    instance-of v1, v0, Lorg/telegram/tgnet/tl/TL_iv$TL_pagePart_layer82;

    if-nez v1, :cond_10

    iget-boolean v0, v0, Lorg/telegram/tgnet/tl/TL_iv$Page;->part:Z

    if-eqz v0, :cond_f

    goto :goto_a

    .line 5668
    :cond_f
    iget v0, v3, Lorg/telegram/tgnet/TLRPC$WebPage;->hash:I

    iput v0, v13, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->hash:I

    goto :goto_b

    .line 5666
    :cond_10
    :goto_a
    iput v7, v13, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->hash:I

    :goto_b
    if-eqz v4, :cond_11

    .line 5671
    iget v0, v4, Lorg/telegram/messenger/MessageObject;->currentAccount:I

    :goto_c
    move v2, v0

    goto :goto_d

    :cond_11
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    goto :goto_c

    .line 5672
    :goto_d
    invoke-static {v2}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v14

    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda2;

    move-object v1, p0

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/messenger/MessageObject;ZLjava/lang/String;)V

    invoke-virtual {v14, v13, v0}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    goto :goto_e

    :cond_12
    move-object/from16 v2, p4

    .line 5736
    invoke-direct {p0, v2, v5}, Lorg/telegram/ui/ArticleViewer;->addPageToStack(Ljava/lang/String;I)Z

    .line 5738
    :cond_13
    :goto_e
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    if-eqz v0, :cond_14

    if-nez v5, :cond_14

    .line 5739
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v7

    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/web/WebActionBar;->setIsLocal(Z)V

    .line 5742
    :cond_14
    iput-object v9, p0, Lorg/telegram/ui/ArticleViewer;->lastInsets:Ljava/lang/Object;

    .line 5743
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v0, :cond_15

    if-nez v5, :cond_19

    .line 5745
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->removeFromParent(Landroid/view/View;)V

    .line 5746
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ArticleViewer$Sheet;->setContainerView(Landroid/view/View;)V

    .line 5747
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$Sheet;->windowView:Lorg/telegram/ui/ArticleViewer$Sheet$WindowView;

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    const/high16 v3, -0x40800000    # -1.0f

    invoke-static {v8, v3}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_11

    .line 5749
    :cond_15
    iget-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    const-string/jumbo v2, "window"

    if-nez v0, :cond_18

    .line 5750
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {v0, v2}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/WindowManager;

    .line 5751
    iget-boolean v2, p0, Lorg/telegram/ui/ArticleViewer;->attachedToWindow:Z

    if-eqz v2, :cond_16

    .line 5753
    :try_start_1
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-interface {v0, v2}, Landroid/view/ViewManager;->removeView(Landroid/view/View;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    .line 5759
    :catch_1
    :cond_16
    :try_start_2
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowLayoutParams:Landroid/view/WindowManager$LayoutParams;

    const/high16 v3, -0x77ff0000

    iput v3, v2, Landroid/view/WindowManager$LayoutParams;->flags:I

    .line 5762
    sget v3, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v4, 0x1c

    if-lt v3, v4, :cond_17

    .line 5763
    invoke-static {v2, v10}, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticApiModelOutline1;->m(Landroid/view/WindowManager$LayoutParams;I)V

    goto :goto_f

    :catch_2
    move-exception v0

    move-object p0, v0

    goto :goto_10

    .line 5766
    :cond_17
    :goto_f
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v2, v7}, Landroid/view/View;->setFocusable(Z)V

    .line 5767
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v7}, Landroid/view/View;->setFocusable(Z)V

    .line 5768
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->windowLayoutParams:Landroid/view/WindowManager$LayoutParams;

    invoke-interface {v0, v2, v3}, Landroid/view/ViewManager;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    goto :goto_11

    .line 5770
    :goto_10
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return v7

    .line 5774
    :cond_18
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowLayoutParams:Landroid/view/WindowManager$LayoutParams;

    iget v3, v0, Landroid/view/WindowManager$LayoutParams;->flags:I

    and-int/lit8 v3, v3, -0x11

    iput v3, v0, Landroid/view/WindowManager$LayoutParams;->flags:I

    .line 5775
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {v0, v2}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/WindowManager;

    .line 5776
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->windowLayoutParams:Landroid/view/WindowManager$LayoutParams;

    invoke-interface {v0, v2, v3}, Landroid/view/ViewManager;->updateViewLayout(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 5778
    :cond_19
    :goto_11
    iput-boolean v10, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    .line 5779
    iput v10, p0, Lorg/telegram/ui/ArticleViewer;->animationInProgress:I

    const/4 v0, 0x2

    if-eqz v5, :cond_1a

    goto/16 :goto_12

    .line 5783
    :cond_1a
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v2, :cond_1c

    if-eqz v5, :cond_1b

    if-eqz v2, :cond_1d

    .line 5786
    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$Sheet;->animationsLock:Lorg/telegram/messenger/AnimationNotificationsLocker;

    invoke-virtual {v2}, Lorg/telegram/messenger/AnimationNotificationsLocker;->unlock()V

    goto :goto_12

    .line 5808
    :cond_1b
    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$Sheet;->show()V

    goto :goto_12

    .line 5811
    :cond_1c
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v2, v12}, Lorg/telegram/ui/ArticleViewer$WindowView;->setAlpha(F)V

    .line 5812
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v12}, Landroid/view/View;->setAlpha(F)V

    .line 5814
    new-instance v2, Landroid/animation/AnimatorSet;

    invoke-direct {v2}, Landroid/animation/AnimatorSet;-><init>()V

    .line 5815
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    sget-object v4, Landroid/view/View;->ALPHA:Landroid/util/Property;

    new-array v5, v0, [F

    fill-array-data v5, :array_0

    .line 5816
    invoke-static {v3, v4, v5}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    new-array v6, v0, [F

    fill-array-data v6, :array_1

    .line 5817
    invoke-static {v5, v4, v6}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v4

    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    sget-object v6, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    .line 5818
    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    int-to-float v8, v8

    new-array v11, v0, [F

    aput v8, v11, v7

    aput v12, v11, v10

    invoke-static {v5, v6, v11}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v5

    const/4 v6, 0x3

    new-array v6, v6, [Landroid/animation/Animator;

    aput-object v3, v6, v7

    aput-object v4, v6, v10

    aput-object v5, v6, v0

    .line 5815
    invoke-virtual {v2, v6}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 5821
    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda3;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    iput-object v3, p0, Lorg/telegram/ui/ArticleViewer;->animationEndRunnable:Ljava/lang/Runnable;

    const-wide/16 v3, 0x96

    .line 5830
    invoke-virtual {v2, v3, v4}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 5831
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->interpolator:Landroid/view/animation/DecelerateInterpolator;

    invoke-virtual {v2, v3}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 5832
    new-instance v3, Lorg/telegram/ui/ArticleViewer$26;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$26;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v3}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 5844
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iput-wide v3, p0, Lorg/telegram/ui/ArticleViewer;->transitionAnimationStartTime:J

    .line 5845
    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda4;

    invoke-direct {v3, p0, v2}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/animation/AnimatorSet;)V

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 5850
    :cond_1d
    :goto_12
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    invoke-virtual {p0, v0, v9}, Landroid/view/View;->setLayerType(ILandroid/graphics/Paint;)V

    return v10

    :cond_1e
    :goto_13
    return v7

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data

    :array_1
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method

.method private openAllParentBlocks(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Z
    .locals 4

    .line 1832
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;->-$$Nest$fgetparent(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p1

    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p1

    .line 1833
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    .line 1834
    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;

    .line 1835
    iget-boolean p0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    if-nez p0, :cond_0

    .line 1836
    iput-boolean v2, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    return v2

    :cond_0
    return v1

    .line 1840
    :cond_1
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    if-eqz v0, :cond_5

    .line 1841
    check-cast p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    .line 1842
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v0

    invoke-direct {p0, v0}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v0

    .line 1844
    instance-of v3, v0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;

    if-eqz v3, :cond_2

    .line 1845
    check-cast v0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;

    .line 1846
    iget-boolean v3, v0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    if-nez v3, :cond_2

    .line 1847
    iput-boolean v2, v0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockDetails;->open:Z

    move v0, v2

    goto :goto_0

    :cond_2
    move v0, v1

    .line 1851
    :goto_0
    invoke-direct {p0, p1}, Lorg/telegram/ui/ArticleViewer;->openAllParentBlocks(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Z

    move-result p0

    if-nez p0, :cond_4

    if-eqz v0, :cond_3

    goto :goto_1

    :cond_3
    return v1

    :cond_4
    :goto_1
    return v2

    :cond_5
    return v1
.end method

.method private openPreviewsChat(Lorg/telegram/tgnet/TLRPC$User;J)V
    .locals 4

    if-eqz p1, :cond_1

    .line 5549
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    instance-of v0, v0, Lorg/telegram/ui/LaunchActivity;

    if-nez v0, :cond_0

    goto :goto_0

    .line 5552
    :cond_0
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    .line 5553
    const-string/jumbo v1, "user_id"

    iget-wide v2, p1, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/BaseBundle;->putLong(Ljava/lang/String;J)V

    .line 5554
    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "webpage"

    invoke-direct {p1, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "botUser"

    invoke-virtual {v0, p2, p1}, Landroid/os/BaseBundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 5555
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    check-cast p1, Lorg/telegram/ui/LaunchActivity;

    new-instance p2, Lorg/telegram/ui/ChatActivity;

    invoke-direct {p2, v0}, Lorg/telegram/ui/ChatActivity;-><init>(Landroid/os/Bundle;)V

    const/4 p3, 0x0

    const/4 v0, 0x1

    invoke-virtual {p1, p2, p3, v0}, Lorg/telegram/ui/LaunchActivity;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;ZZ)Z

    .line 5556
    invoke-virtual {p0, p3, v0}, Lorg/telegram/ui/ArticleViewer;->close(ZZ)V

    :cond_1
    :goto_0
    return-void
.end method

.method private openWebpageUrlInternal(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)V
    .locals 9

    .line 3944
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->loadingProgress:Lorg/telegram/messenger/browser/Browser$Progress;

    if-eqz v0, :cond_0

    .line 3945
    invoke-virtual {v0}, Lorg/telegram/messenger/browser/Browser$Progress;->cancel()V

    .line 3947
    :cond_0
    iput-object p3, p0, Lorg/telegram/ui/ArticleViewer;->loadingProgress:Lorg/telegram/messenger/browser/Browser$Progress;

    .line 3948
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 3949
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v0

    iget v2, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    invoke-virtual {v0, v2, v1}, Lorg/telegram/tgnet/ConnectionsManager;->cancelRequest(IZ)V

    .line 3950
    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    :cond_1
    const/4 v0, 0x1

    .line 3953
    new-array v2, v0, [Z

    .line 3954
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-static {v3, p1, v1}, Lorg/telegram/messenger/browser/Browser;->openInExternalApp(Landroid/content/Context;Ljava/lang/String;Z)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 3955
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_3

    .line 3956
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p0, :cond_3

    .line 3957
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    return-void

    .line 3962
    :cond_2
    new-instance v7, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda59;

    invoke-direct {v7, p0, p1, v2, p3}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda59;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;[ZLorg/telegram/messenger/browser/Browser$Progress;)V

    .line 3984
    iget v2, p0, Lorg/telegram/ui/ArticleViewer;->lastReqId:I

    add-int/lit8 v4, v2, 0x1

    iput v4, p0, Lorg/telegram/ui/ArticleViewer;->lastReqId:I

    .line 3985
    invoke-direct {p0, v0, v0}, Lorg/telegram/ui/ArticleViewer;->showProgressView(ZZ)V

    .line 3986
    new-instance v8, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;

    invoke-direct {v8}, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;-><init>()V

    .line 3987
    iput-object p1, v8, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->url:Ljava/lang/String;

    .line 3988
    iput v1, v8, Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;->hash:I

    .line 3989
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p1

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda60;

    move-object v3, p0

    move-object v6, p2

    move-object v5, p3

    invoke-direct/range {v2 .. v8}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda60;-><init>(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/messenger/browser/Browser$Progress;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback0Return;Lorg/telegram/tgnet/TLRPC$TL_messages_getWebPage;)V

    invoke-virtual {p1, v8, v2}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    move-result p0

    iput p0, v3, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    if-eqz v5, :cond_3

    .line 4024
    new-instance p0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda61;

    invoke-direct {p0, v3, v4, v5}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda61;-><init>(Lorg/telegram/ui/ArticleViewer;ILorg/telegram/messenger/browser/Browser$Progress;)V

    invoke-virtual {v5, p0}, Lorg/telegram/messenger/browser/Browser$Progress;->onCancel(Ljava/lang/Runnable;)Lorg/telegram/messenger/browser/Browser$Progress;

    .line 4033
    invoke-virtual {v5}, Lorg/telegram/messenger/browser/Browser$Progress;->init()V

    :cond_3
    return-void
.end method

.method private processSearch(Ljava/lang/String;)V
    .locals 4

    .line 5308
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 5309
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    const/4 v0, 0x0

    .line 5310
    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchRunnable:Ljava/lang/Runnable;

    .line 5312
    :cond_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    .line 5313
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->searchResults:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 5314
    iput-object p1, p0, Lorg/telegram/ui/IArticleViewer;->searchText:Ljava/lang/String;

    .line 5315
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v1

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetsearchTextOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/HashMap;->clear()V

    .line 5316
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ArticleViewer;->showSearchPanel(Z)V

    .line 5317
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v1

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result p1

    .line 5323
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz p1, :cond_1

    .line 5318
    aget-object p1, v0, v1

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 5319
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v1

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p1

    new-instance v0, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda57;

    invoke-direct {v0, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda57;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    const-string v1, ""

    invoke-virtual {p1, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->search(Ljava/lang/String;Ljava/lang/Runnable;)V

    .line 5320
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->updateSearchButtons()V

    goto :goto_0

    .line 5323
    :cond_1
    aget-object p1, v0, v1

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p1}, Lorg/telegram/ui/Components/RecyclerListView;->invalidateViews()V

    .line 5324
    invoke-direct {p0, v1}, Lorg/telegram/ui/ArticleViewer;->scrollToSearchIndex(I)V

    :cond_2
    :goto_0
    const/4 p1, -0x1

    .line 5326
    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->lastSearchIndex:I

    return-void

    .line 5329
    :cond_3
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->lastSearchIndex:I

    const/4 v2, 0x1

    add-int/2addr v0, v2

    iput v0, p0, Lorg/telegram/ui/ArticleViewer;->lastSearchIndex:I

    .line 5330
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v1

    invoke-virtual {v3}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v3

    if-eqz v3, :cond_5

    .line 5331
    invoke-virtual {p0, v2}, Lorg/telegram/ui/ArticleViewer;->showSearchPanel(Z)V

    .line 5332
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 5333
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda57;

    invoke-direct {v1, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda57;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, p1, v1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->search(Ljava/lang/String;Ljava/lang/Runnable;)V

    .line 5334
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->updateSearchButtons()V

    :cond_4
    return-void

    .line 5337
    :cond_5
    new-instance v1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda58;

    invoke-direct {v1, p0, p1, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda58;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;I)V

    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->searchRunnable:Ljava/lang/Runnable;

    const-wide/16 p0, 0x190

    invoke-static {v1, p0, p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method private refreshThemeColors()V
    .locals 5

    .line 5961
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 5962
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_listSelector:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v1

    const/4 v2, 0x2

    invoke-static {v1, v2}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 5963
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuItem:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 5965
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    if-eqz v0, :cond_1

    .line 5966
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuBackground:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->setBackgroundColor(I)V

    .line 5968
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    .line 5969
    new-instance v2, Landroid/graphics/PorterDuffColorFilter;

    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v3

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v2, v3, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 5970
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarActionModeDefaultSelector:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v2

    invoke-static {v2, v1}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/view/View;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 5972
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    if-eqz v0, :cond_3

    .line 5973
    new-instance v2, Landroid/graphics/PorterDuffColorFilter;

    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v3

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v2, v3, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 5974
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarActionModeDefaultSelector:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v2

    invoke-static {v2, v1}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/view/View;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 5976
    :cond_3
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    if-eqz v0, :cond_4

    .line 5977
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextColor(I)V

    .line 5979
    :cond_4
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    if-eqz v0, :cond_7

    .line 5980
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v3, 0x0

    aget-object v2, v2, v3

    if-eqz v2, :cond_5

    sget-boolean v4, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v4, :cond_5

    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v2

    goto :goto_0

    :cond_5
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v2

    :goto_0
    invoke-virtual {v0, v2}, Lorg/telegram/ui/web/WebActionBar;->setMenuColors(I)V

    .line 5981
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v3

    if-eqz v2, :cond_6

    sget-boolean v3, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v3, :cond_6

    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v2

    goto :goto_1

    :cond_6
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v2

    :goto_1
    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/web/WebActionBar;->setColors(IZ)V

    .line 5983
    :cond_7
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->backgroundPaint:Landroid/graphics/Paint;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_iv_background:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result p0

    invoke-virtual {v0, p0}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method

.method private releaseLiveDrawingTexts()V
    .locals 9

    .line 6253
    new-instance v0, Ljava/util/ArrayList;

    sget-object v1, Lorg/telegram/ui/ArticleViewer;->liveDrawingTexts:Ljava/util/WeakHashMap;

    invoke-virtual {v1}, Ljava/util/WeakHashMap;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    move v3, v2

    :cond_0
    :goto_0
    if-ge v3, v1, :cond_4

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Landroid/view/View;

    .line 6254
    sget-object v5, Lorg/telegram/ui/ArticleViewer;->liveDrawingTexts:Ljava/util/WeakHashMap;

    invoke-virtual {v5, v4}, Ljava/util/WeakHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/ArrayList;

    if-nez v5, :cond_1

    goto :goto_0

    :cond_1
    move v6, v2

    .line 6258
    :goto_1
    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v7

    if-ge v6, v7, :cond_3

    .line 6259
    invoke-virtual {v5, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/telegram/ui/ArticleViewer$DrawingText;

    .line 6260
    invoke-static {v7}, Lorg/telegram/ui/ArticleViewer$DrawingText;->-$$Nest$fgetparent(Lorg/telegram/ui/ArticleViewer$DrawingText;)Lorg/telegram/ui/IArticleViewer;

    move-result-object v8

    if-ne v8, p0, :cond_2

    .line 6261
    invoke-virtual {v7, v4}, Lorg/telegram/ui/ArticleViewer$DrawingText;->detach(Landroid/view/View;)V

    .line 6262
    invoke-virtual {v5, v6}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    add-int/lit8 v6, v6, -0x1

    :cond_2
    add-int/lit8 v6, v6, 0x1

    goto :goto_1

    .line 6266
    :cond_3
    invoke-virtual {v5}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_0

    .line 6267
    sget-object v5, Lorg/telegram/ui/ArticleViewer;->liveDrawingTexts:Ljava/util/WeakHashMap;

    invoke-virtual {v5, v4}, Ljava/util/WeakHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    :cond_4
    return-void
.end method

.method private removeLastPageFromStack()Z
    .locals 4

    .line 2559
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-ge v0, v1, :cond_0

    return v2

    .line 2562
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v3, 0x1

    sub-int/2addr v1, v3

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    move-result-object v0

    .line 2563
    instance-of v1, v0, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    if-eqz v1, :cond_1

    .line 2564
    move-object v1, v0

    check-cast v1, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;->destroy()V

    .line 2566
    :cond_1
    instance-of v1, v0, Lorg/telegram/tgnet/TLRPC$WebPage;

    if-eqz v1, :cond_2

    .line 2567
    check-cast v0, Lorg/telegram/tgnet/TLRPC$WebPage;

    invoke-static {v0}, Lorg/telegram/ui/web/WebInstantView;->recycle(Lorg/telegram/tgnet/TLRPC$WebPage;)V

    .line 2569
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    sub-int/2addr v1, v3

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    const/4 v1, -0x1

    invoke-direct {p0, v0, v2, v1}, Lorg/telegram/ui/ArticleViewer;->updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V

    return v3
.end method

.method private static removePressedLink(Lorg/telegram/ui/IArticleViewer;)V
    .locals 2

    .line 3907
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    if-nez v0, :cond_0

    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerView:Landroid/view/View;

    if-nez v0, :cond_0

    goto :goto_0

    .line 3910
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerView:Landroid/view/View;

    .line 3911
    iget-object v1, p0, Lorg/telegram/ui/IArticleViewer;->links:Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/LinkSpanDrawable$LinkCollector;->clear()V

    const/4 v1, 0x0

    .line 3912
    iput-object v1, p0, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    .line 3913
    iput-object v1, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    .line 3914
    iput-object v1, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerView:Landroid/view/View;

    if-eqz v0, :cond_1

    .line 3916
    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    :cond_1
    :goto_0
    return-void
.end method

.method private saveCurrentPagePosition()V
    .locals 7

    .line 5942
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 5945
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->findFirstVisibleItemPosition()I

    move-result v0

    const/4 v2, -0x1

    if-eq v0, v2, :cond_3

    .line 5948
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v2, v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->findViewByPosition(I)Landroid/view/View;

    move-result-object v2

    if-eqz v2, :cond_1

    .line 5950
    invoke-virtual {v2}, Landroid/view/View;->getTop()I

    move-result v2

    goto :goto_0

    :cond_1
    move v2, v1

    .line 5954
    :goto_0
    sget-object v3, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v4, "articles"

    invoke-virtual {v3, v4, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v3

    invoke-interface {v3}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v3

    .line 5955
    new-instance v4, Ljava/lang/StringBuilder;

    const-string v5, "article"

    invoke-direct {v4, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v1

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p0

    iget-wide v5, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->id:J

    invoke-virtual {v4, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 5956
    invoke-interface {v3, p0, v0}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string/jumbo v3, "o"

    invoke-virtual {p0, v3}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v3, v2}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string/jumbo v2, "r"

    invoke-virtual {p0, v2}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    sget-object v2, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v3, v2, Landroid/graphics/Point;->x:I

    iget v2, v2, Landroid/graphics/Point;->y:I

    if-le v3, v2, :cond_2

    const/4 v1, 0x1

    :cond_2
    invoke-interface {v0, p0, v1}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    :cond_3
    :goto_1
    return-void
.end method

.method private scrollToSearchIndex(I)V
    .locals 12

    if-ltz p1, :cond_c

    .line 5414
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->searchResults:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lt p1, v0, :cond_0

    goto/16 :goto_4

    .line 5418
    :cond_0
    iput p1, p0, Lorg/telegram/ui/IArticleViewer;->currentSearchIndex:I

    .line 5419
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->updateSearchButtons()V

    .line 5420
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->searchResults:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/ui/ArticleViewer$SearchResult;

    .line 5421
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$SearchResult;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v0

    invoke-direct {p0, v0}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v0

    .line 5424
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v2, 0x0

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetblocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_3

    .line 5425
    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v2

    iget-object v4, v4, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v4}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetblocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v4

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 5426
    instance-of v5, v4, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    if-eqz v5, :cond_2

    .line 5427
    check-cast v4, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    .line 5428
    invoke-static {v4}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v5

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$SearchResult;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v6

    if-eq v5, v6, :cond_1

    invoke-static {v4}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v5

    if-ne v5, v0, :cond_2

    .line 5429
    :cond_1
    invoke-direct {p0, v4}, Lorg/telegram/ui/ArticleViewer;->openAllParentBlocks(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 5430
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mupdateRows(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)V

    .line 5431
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    goto :goto_1

    :cond_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 5437
    :cond_3
    :goto_1
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetlocalBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    move v3, v2

    :goto_2
    const/4 v4, -0x1

    if-ge v3, v1, :cond_6

    .line 5438
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v2

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetlocalBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 5439
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$SearchResult;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v6

    if-eq v5, v6, :cond_7

    if-ne v5, v0, :cond_4

    goto :goto_3

    .line 5442
    :cond_4
    instance-of v6, v5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    if-eqz v6, :cond_5

    .line 5443
    check-cast v5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    .line 5444
    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v6

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$SearchResult;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v7

    if-eq v6, v7, :cond_7

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v5

    if-ne v5, v0, :cond_5

    goto :goto_3

    :cond_5
    add-int/lit8 v3, v3, 0x1

    goto :goto_2

    :cond_6
    move v3, v4

    :cond_7
    :goto_3
    if-ne v3, v4, :cond_8

    return-void

    .line 5455
    :cond_8
    instance-of v1, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    if-eqz v1, :cond_9

    .line 5456
    check-cast v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    invoke-direct {p0, v0}, Lorg/telegram/ui/ArticleViewer;->openAllParentBlocks(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Z

    move-result v0

    if-eqz v0, :cond_9

    .line 5457
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v2

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mupdateRows(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)V

    .line 5458
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v2

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    .line 5462
    :cond_9
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Lorg/telegram/ui/IArticleViewer;->searchText:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$SearchResult;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgettext(Lorg/telegram/ui/ArticleViewer$SearchResult;)Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetindex(Lorg/telegram/ui/ArticleViewer$SearchResult;)I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 5463
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetsearchTextOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    if-nez v1, :cond_a

    .line 5465
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$SearchResult;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v4

    invoke-static {v1, v4}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mgetTypeForBlock(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)I

    move-result v6

    .line 5466
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    const/4 v4, 0x0

    invoke-virtual {v1, v4, v6}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v7

    .line 5467
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v5, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$SearchResult;->-$$Nest$fgetblock(Lorg/telegram/ui/ArticleViewer$SearchResult;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v8

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v9, 0x0

    invoke-static/range {v5 .. v11}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mbindBlockToHolder(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;ILandroidx/recyclerview/widget/RecyclerView$ViewHolder;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;IIZ)V

    .line 5468
    iget-object p1, v7, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    const/high16 v4, 0x40000000    # 2.0f

    invoke-static {v1, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    invoke-static {v2, v2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v4

    invoke-virtual {p1, v1, v4}, Landroid/view/View;->measure(II)V

    .line 5469
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v2

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetsearchTextOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    move-object v1, p1

    check-cast v1, Ljava/lang/Integer;

    if-nez v1, :cond_a

    .line 5471
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    .line 5474
    :cond_a
    new-instance p1, Lorg/telegram/ui/ArticleViewer$24;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v2

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-direct {p1, p0, v0}, Lorg/telegram/ui/ArticleViewer$24;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    .line 5480
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v2

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetpadding(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Z

    move-result v0

    if-eqz v0, :cond_b

    add-int/lit8 v3, v3, 0x1

    .line 5483
    :cond_b
    invoke-virtual {p1, v3}, Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;->setTargetPosition(I)V

    .line 5484
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    const/high16 v3, 0x42600000    # 56.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sub-int/2addr v0, v3

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    sub-int/2addr v0, v1

    const/high16 v1, 0x42c80000    # 100.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    add-int/2addr v0, v1

    neg-int v0, v0

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/SmoothScroller;->setOffset(I)V

    const v0, 0x3f99999a    # 1.2f

    .line 5485
    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/SmoothScroller;->setDurationScale(F)V

    .line 5486
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v2

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v0, p1}, Landroidx/recyclerview/widget/RecyclerView$LayoutManager;->startSmoothScroll(Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;)V

    .line 5487
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v2

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/RecyclerListView;->invalidateViews()V

    return-void

    .line 5415
    :cond_c
    :goto_4
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->updateSearchButtons()V

    return-void
.end method

.method private setCurrentHeaderHeight(I)V
    .locals 5

    .line 5528
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/telegram/ui/web/WebActionBar;->isSearching()Z

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v0}, Lorg/telegram/ui/web/WebActionBar;->isAddressing()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    :cond_0
    const/high16 v0, 0x42600000    # 56.0f

    .line 5531
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    const/high16 v1, 0x41c00000    # 24.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    invoke-static {p1, v0, v1}, Lorg/telegram/messenger/Utilities;->clamp(III)I

    move-result p1

    iput p1, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    .line 5532
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v0, p1}, Lorg/telegram/ui/web/WebActionBar;->setHeight(I)V

    .line 5533
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Cells/TextSelectionHelper;->setTopOffset(I)V

    const/4 p1, 0x0

    move v0, p1

    .line 5534
    :goto_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length v2, v1

    if-ge v0, v2, :cond_2

    .line 5535
    aget-object v1, v1, v0

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    iget v2, p0, Lorg/telegram/ui/ArticleViewer;->currentHeaderHeight:I

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v3, :cond_1

    sget v3, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    const/high16 v4, 0x42000000    # 32.0f

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int/2addr v3, v4

    invoke-static {p1, v3}, Ljava/lang/Math;->max(II)I

    move-result v3

    goto :goto_1

    :cond_1
    move v3, p1

    :goto_1
    add-int/2addr v2, v3

    invoke-virtual {v1, v2}, Landroidx/recyclerview/widget/RecyclerView;->setTopGlowOffset(I)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    :goto_2
    return-void
.end method

.method private showCopyPopup(Ljava/lang/String;)V
    .locals 6

    .line 1545
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-nez v0, :cond_0

    return-void

    .line 1548
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->linkSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    if-eqz v0, :cond_1

    .line 1549
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BottomSheet;->dismiss()V

    const/4 v0, 0x0

    .line 1550
    iput-object v0, p0, Lorg/telegram/ui/IArticleViewer;->linkSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    .line 1553
    :cond_1
    new-instance v0, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;-><init>(Landroid/content/Context;)V

    .line 1556
    :try_start_0
    const-string v1, "\\+"

    const-string v2, "%2b"

    invoke-virtual {p1, v1, v2}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "UTF-8"

    invoke-static {v1, v2}, Lj$/net/URLDecoder;->decode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v1

    .line 1558
    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    move-object v1, p1

    .line 1560
    :goto_0
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    const/4 v1, 0x1

    .line 1561
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setTitleMultipleLines(Z)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 1562
    sget v2, Lorg/telegram/messenger/R$string;->Open:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$string;->Copy:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/CharSequence;

    const/4 v5, 0x0

    aput-object v2, v4, v5

    aput-object v3, v4, v1

    new-instance v1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda47;

    invoke-direct {v1, p0, p1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda47;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;)V

    invoke-virtual {v0, v4, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setItems([Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 1603
    new-instance p1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda48;

    invoke-direct {p1, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda48;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, p1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setOnPreDismissListener(Landroid/content/DialogInterface$OnDismissListener;)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 1604
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->create()Lorg/telegram/ui/ActionBar/BottomSheet;

    move-result-object p1

    .line 1605
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ArticleViewer;->showDialog(Landroid/app/Dialog;)V

    return-void
.end method

.method private showLineProgressView()V
    .locals 2

    .line 5925
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    if-nez v0, :cond_0

    return-void

    .line 5928
    :cond_0
    iget-object v0, v0, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 5929
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p0, p0, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    invoke-virtual {p0}, Lcom/google/android/material/progressindicator/BaseProgressIndicator;->show()V

    return-void
.end method

.method private showPopup(Landroid/view/View;III)V
    .locals 7

    .line 1609
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/widget/PopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1610
    iget-object p0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;->dismiss()V

    return-void

    .line 1614
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    const/4 v1, 0x1

    if-nez v0, :cond_1

    .line 1615
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupRect:Landroid/graphics/Rect;

    .line 1616
    new-instance v0, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    const/high16 v2, 0x3f800000    # 1.0f

    .line 1617
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-virtual {v0, v3, v4, v5, v2}, Landroid/view/View;->setPadding(IIII)V

    .line 1618
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->menu_copy:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 1619
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->setAnimationEnabled(Z)V

    .line 1620
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda71;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda71;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, v3}, Landroid/view/View;->setOnTouchListener(Landroid/view/View$OnTouchListener;)V

    .line 1631
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda72;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda72;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->setDispatchKeyEventListener(Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$OnDispatchKeyEventListener;)V

    .line 1636
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->setShownFromBottom(Z)V

    .line 1638
    new-instance v0, Landroid/widget/TextView;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v0, v3}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    .line 1639
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_listSelector:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v3

    const/4 v4, 0x2

    invoke-static {v3, v4}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/view/View;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 1640
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    const/16 v3, 0x10

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setGravity(I)V

    .line 1641
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    const/high16 v3, 0x41a00000    # 20.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-virtual {v0, v5, v2, v3, v2}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 1642
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    const/high16 v3, 0x41700000    # 15.0f

    invoke-virtual {v0, v1, v3}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 1643
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 1644
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    sget v3, Lorg/telegram/messenger/R$string;->Copy:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 1645
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda73;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda73;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, v3}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 1656
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    const/high16 v5, 0x42400000    # 48.0f

    const/4 v6, -0x2

    invoke-static {v6, v5}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v5

    invoke-virtual {v0, v3, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 1658
    new-instance v0, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    invoke-direct {v0, v3, v6, v6}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;-><init>(Landroid/view/View;II)V

    iput-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    .line 1659
    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;->setAnimationEnabled(Z)V

    .line 1660
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    sget v3, Lorg/telegram/messenger/R$style;->PopupContextAnimation:I

    invoke-virtual {v0, v3}, Landroid/widget/PopupWindow;->setAnimationStyle(I)V

    .line 1661
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {v0, v1}, Landroid/widget/PopupWindow;->setOutsideTouchable(Z)V

    .line 1662
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {v0, v1}, Landroid/widget/PopupWindow;->setClippingEnabled(Z)V

    .line 1663
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {v0, v4}, Landroid/widget/PopupWindow;->setInputMethodMode(I)V

    .line 1664
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {v0, v2}, Landroid/widget/PopupWindow;->setSoftInputMode(I)V

    .line 1665
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {v0}, Landroid/widget/PopupWindow;->getContentView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/view/View;->setFocusableInTouchMode(Z)V

    .line 1666
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda74;

    invoke-direct {v2, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda74;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, v2}, Landroid/widget/PopupWindow;->setOnDismissListener(Landroid/widget/PopupWindow$OnDismissListener;)V

    .line 1675
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->deleteView:Landroid/widget/TextView;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuItem:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setTextColor(I)V

    .line 1676
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    if-eqz v0, :cond_2

    .line 1677
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuBackground:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->setBackgroundColor(I)V

    .line 1680
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->popupLayout:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    const/high16 v2, 0x447a0000    # 1000.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    const/high16 v4, -0x80000000

    invoke-static {v3, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v3

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-static {v2, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    invoke-virtual {v0, v3, v2}, Landroid/view/View;->measure(II)V

    .line 1681
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {v0, v1}, Landroid/widget/PopupWindow;->setFocusable(Z)V

    .line 1682
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {v0, p1, p2, p3, p4}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;->showAtLocation(Landroid/view/View;III)V

    .line 1683
    iget-object p0, p0, Lorg/telegram/ui/IArticleViewer;->popupWindow:Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow;->startAnimation()V

    return-void
.end method

.method private showProgressView(ZZ)V
    .locals 10

    const/4 v0, 0x1

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/high16 v3, 0x3f800000    # 1.0f

    if-eqz p1, :cond_2

    .line 5856
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->lineProgressTickRunnable:Ljava/lang/Runnable;

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 5863
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    if-eqz p2, :cond_1

    .line 5858
    iget-object p1, p1, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    invoke-virtual {p1, v2, v1}, Lorg/telegram/ui/Components/LineProgressView;->setProgress(FZ)V

    .line 5859
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p1, p1, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    invoke-virtual {p1}, Landroid/view/View;->getVisibility()I

    move-result p1

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->showLineProgressView()V

    .line 5860
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object p1, p1, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    const p2, 0x3e99999a    # 0.3f

    invoke-virtual {p1, p2, v0}, Lorg/telegram/ui/Components/LineProgressView;->setProgress(FZ)V

    .line 5861
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->lineProgressTickRunnable:Ljava/lang/Runnable;

    const-wide/16 p1, 0x64

    invoke-static {p0, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    .line 5863
    :cond_1
    iget-object p0, p1, Lorg/telegram/ui/web/WebActionBar;->lineProgressView:Lorg/telegram/ui/Components/LineProgressView;

    invoke-virtual {p0, v3, v0}, Lorg/telegram/ui/Components/LineProgressView;->setProgress(FZ)V

    return-void

    .line 5866
    :cond_2
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    if-eqz p1, :cond_3

    .line 5867
    invoke-virtual {p1}, Landroid/animation/AnimatorSet;->cancel()V

    .line 5869
    :cond_3
    new-instance p1, Landroid/animation/AnimatorSet;

    invoke-direct {p1}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    .line 5887
    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->progressView:Lorg/telegram/ui/Components/ContextProgressView;

    const/4 v5, 0x2

    const/4 v6, 0x3

    if-eqz p2, :cond_4

    .line 5871
    invoke-virtual {v4, v1}, Lorg/telegram/ui/Components/ContextProgressView;->setVisibility(I)V

    .line 5873
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->progressView:Lorg/telegram/ui/Components/ContextProgressView;

    sget-object v4, Landroid/view/View;->SCALE_X:Landroid/util/Property;

    new-array v7, v0, [F

    aput v3, v7, v1

    .line 5880
    invoke-static {v2, v4, v7}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v2

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->progressView:Lorg/telegram/ui/Components/ContextProgressView;

    sget-object v7, Landroid/view/View;->SCALE_Y:Landroid/util/Property;

    new-array v8, v0, [F

    aput v3, v8, v1

    .line 5881
    invoke-static {v4, v7, v8}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v4

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->progressView:Lorg/telegram/ui/Components/ContextProgressView;

    sget-object v8, Landroid/view/View;->ALPHA:Landroid/util/Property;

    new-array v9, v0, [F

    aput v3, v9, v1

    .line 5882
    invoke-static {v7, v8, v9}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    new-array v6, v6, [Landroid/animation/Animator;

    aput-object v2, v6, v1

    aput-object v4, v6, v0

    aput-object v3, v6, v5

    .line 5873
    invoke-virtual {p1, v6}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    goto :goto_0

    .line 5887
    :cond_4
    sget-object v3, Landroid/view/View;->SCALE_X:Landroid/util/Property;

    new-array v7, v0, [F

    const v8, 0x3dcccccd    # 0.1f

    aput v8, v7, v1

    .line 5888
    invoke-static {v4, v3, v7}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->progressView:Lorg/telegram/ui/Components/ContextProgressView;

    sget-object v7, Landroid/view/View;->SCALE_Y:Landroid/util/Property;

    new-array v9, v0, [F

    aput v8, v9, v1

    .line 5889
    invoke-static {v4, v7, v9}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v4

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->progressView:Lorg/telegram/ui/Components/ContextProgressView;

    sget-object v8, Landroid/view/View;->ALPHA:Landroid/util/Property;

    new-array v9, v0, [F

    aput v2, v9, v1

    .line 5890
    invoke-static {v7, v8, v9}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v2

    new-array v6, v6, [Landroid/animation/Animator;

    aput-object v3, v6, v1

    aput-object v4, v6, v0

    aput-object v2, v6, v5

    .line 5887
    invoke-virtual {p1, v6}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 5899
    :goto_0
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    new-instance v0, Lorg/telegram/ui/ArticleViewer$27;

    invoke-direct {v0, p0, p2}, Lorg/telegram/ui/ArticleViewer$27;-><init>(Lorg/telegram/ui/ArticleViewer;Z)V

    invoke-virtual {p1, v0}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 5919
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    const-wide/16 v0, 0x96

    invoke-virtual {p1, v0, v1}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 5920
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->progressViewAnimation:Landroid/animation/AnimatorSet;

    invoke-virtual {p0}, Landroid/animation/AnimatorSet;->start()V

    return-void
.end method

.method private showRestrictedWebsiteToast()V
    .locals 4

    const/4 v0, 0x0

    .line 5164
    iput-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->showRestrictedToastOnResume:Z

    .line 5165
    iget-boolean v1, p0, Lorg/telegram/ui/ArticleViewer;->attachedToWindow:Z

    if-eqz v1, :cond_4

    sget-object v1, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    if-eqz v1, :cond_4

    invoke-virtual {v1}, Landroid/app/Activity;->isFinishing()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_1

    .line 5167
    :cond_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v0

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v1

    .line 5171
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz v1, :cond_2

    .line 5168
    aget-object v1, v2, v0

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v1

    if-nez v1, :cond_1

    goto :goto_1

    .line 5169
    :cond_1
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v1, v0

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->webViewContainer:Lorg/telegram/ui/web/BotWebViewContainer;

    goto :goto_0

    .line 5171
    :cond_2
    aget-object v1, v2, v0

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v1

    if-nez v1, :cond_3

    goto :goto_1

    .line 5172
    :cond_3
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v1, v0

    .line 5174
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v1

    invoke-static {v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/R$raw;->chats_infotip:I

    sget v2, Lorg/telegram/messenger/R$string;->BrowserExternalRestricted:I

    .line 5175
    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda62;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda62;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-static {v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->replaceSingleTag(Ljava/lang/String;Ljava/lang/Runnable;)Landroid/text/SpannableStringBuilder;

    move-result-object p0

    const/4 v2, 0x4

    invoke-virtual {v0, v1, p0, v2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;I)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    const/4 v0, 0x1

    .line 5176
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/Bulletin;->show(Z)Lorg/telegram/ui/Components/Bulletin;

    :cond_4
    :goto_1
    return-void
.end method

.method private updateInterfaceForCurrentPage(Ljava/lang/Object;ZI)V
    .locals 12

    if-eqz p1, :cond_22

    .line 1897
    instance-of v0, p1, Lorg/telegram/tgnet/TLRPC$WebPage;

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Lorg/telegram/tgnet/TLRPC$WebPage;

    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    if-nez v0, :cond_1

    :cond_0
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    if-nez v0, :cond_1

    goto/16 :goto_14

    :cond_1
    const/4 v0, 0x0

    const/4 v1, 0x2

    const/4 v2, -0x1

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-nez p2, :cond_d

    if-eqz p3, :cond_d

    .line 1904
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v5, v3

    .line 1905
    aget-object v7, v5, v4

    aput-object v7, v5, v3

    .line 1906
    aput-object v6, v5, v4

    .line 1907
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v5}, Lorg/telegram/ui/web/WebActionBar;->swap()V

    .line 1908
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->page0Background:Lorg/telegram/ui/Components/AnimatedColor;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v4

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v6

    invoke-virtual {v5, v6, v3}, Lorg/telegram/ui/Components/AnimatedColor;->set(IZ)I

    .line 1909
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->page1Background:Lorg/telegram/ui/Components/AnimatedColor;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v3

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v6

    invoke-virtual {v5, v6, v3}, Lorg/telegram/ui/Components/AnimatedColor;->set(IZ)I

    .line 1910
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v5, :cond_2

    .line 1911
    invoke-virtual {v5}, Lorg/telegram/ui/ArticleViewer$Sheet;->updateLastVisible()V

    .line 1914
    :cond_2
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v4

    invoke-virtual {v5, v6}, Landroid/view/ViewGroup;->indexOfChild(Landroid/view/View;)I

    move-result v5

    .line 1915
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v3

    invoke-virtual {v6, v7}, Landroid/view/ViewGroup;->indexOfChild(Landroid/view/View;)I

    move-result v6

    if-ne p3, v3, :cond_3

    if-ge v5, v6, :cond_4

    .line 1918
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v4

    invoke-virtual {v5, v7}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 1919
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v4

    invoke-virtual {v5, v7, v6}, Landroid/view/ViewGroup;->addView(Landroid/view/View;I)V

    goto :goto_0

    :cond_3
    if-ge v6, v5, :cond_4

    .line 1923
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v4

    invoke-virtual {v6, v7}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 1924
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v4

    invoke-virtual {v6, v7, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;I)V

    .line 1928
    :cond_4
    :goto_0
    new-instance v5, Landroid/animation/AnimatorSet;

    invoke-direct {v5}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    .line 1929
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v4

    invoke-virtual {v5, v4}, Landroid/view/View;->setVisibility(I)V

    if-ne p3, v3, :cond_5

    move v5, v4

    goto :goto_1

    :cond_5
    move v5, v3

    .line 1931
    :goto_1
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v5

    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v7, :cond_6

    move v7, v4

    goto :goto_2

    :cond_6
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v7}, Landroid/graphics/Paint;->getColor()I

    move-result v7

    :goto_2
    invoke-virtual {v6, v7}, Landroid/view/View;->setBackgroundColor(I)V

    .line 1932
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v5

    invoke-virtual {v6, v1, v0}, Landroid/view/View;->setLayerType(ILandroid/graphics/Paint;)V

    const/4 v6, 0x0

    if-ne p3, v3, :cond_7

    .line 1934
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v4

    sget-object v8, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v8, v8, Landroid/graphics/Point;->x:I

    int-to-float v8, v8

    invoke-virtual {v7, v8}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setTranslationX(F)V

    .line 1935
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v8, v8, v4

    sget-object v9, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    sget-object v10, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v10, v10, Landroid/graphics/Point;->x:I

    int-to-float v10, v10

    new-array v11, v1, [F

    aput v10, v11, v4

    aput v6, v11, v3

    .line 1936
    invoke-static {v8, v9, v11}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v6

    new-array v8, v3, [Landroid/animation/Animator;

    aput-object v6, v8, v4

    .line 1935
    invoke-virtual {v7, v8}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    goto :goto_3

    :cond_7
    if-ne p3, v2, :cond_8

    .line 1940
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v7, v7, v4

    invoke-virtual {v7, v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setTranslationX(F)V

    .line 1941
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v8, v8, v3

    sget-object v9, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    sget-object v10, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v10, v10, Landroid/graphics/Point;->x:I

    int-to-float v10, v10

    new-array v11, v1, [F

    aput v6, v11, v4

    aput v10, v11, v3

    .line 1942
    invoke-static {v8, v9, v11}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v6

    new-array v8, v3, [Landroid/animation/Animator;

    aput-object v6, v8, v4

    .line 1941
    invoke-virtual {v7, v8}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 1946
    :cond_8
    :goto_3
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    const-wide/16 v7, 0x140

    invoke-virtual {v6, v7, v8}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 1947
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    sget-object v7, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v6, v7}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 1948
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    new-instance v7, Lorg/telegram/ui/ArticleViewer$3;

    invoke-direct {v7, p0, v5}, Lorg/telegram/ui/ArticleViewer$3;-><init>(Lorg/telegram/ui/ArticleViewer;I)V

    invoke-virtual {v6, v7}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 1961
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v5, v3}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fputopeningPage(Lorg/telegram/ui/ArticleViewer$WindowView;Z)V

    .line 1962
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v4

    if-eqz v6, :cond_9

    sget-boolean v7, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v7, :cond_9

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getBackgroundColor()I

    move-result v6

    goto :goto_4

    :cond_9
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v6

    :goto_4
    invoke-virtual {v5, v6}, Lorg/telegram/ui/web/WebActionBar;->setMenuColors(I)V

    .line 1963
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v4

    if-eqz v6, :cond_a

    sget-boolean v7, Lorg/telegram/messenger/SharedConfig;->adaptableColorInBrowser:Z

    if-eqz v7, :cond_a

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v6

    goto :goto_5

    :cond_a
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->getChromeFallbackColor()I

    move-result v6

    :goto_5
    invoke-virtual {v5, v6, v3}, Lorg/telegram/ui/web/WebActionBar;->setColors(IZ)V

    .line 1964
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v4

    if-eqz v6, :cond_b

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isTonsite()Z

    move-result v6

    if-eqz v6, :cond_b

    move v6, v3

    goto :goto_6

    :cond_b
    move v6, v4

    :goto_6
    invoke-virtual {v5, v6}, Lorg/telegram/ui/web/WebActionBar;->setIsTonsite(Z)V

    .line 1965
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v4

    if-eqz v6, :cond_c

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v6

    if-eqz v6, :cond_c

    move v6, v3

    goto :goto_7

    :cond_c
    move v6, v4

    :goto_7
    invoke-virtual {v5, v6}, Lorg/telegram/ui/web/WebActionBar;->setIsLocal(Z)V

    .line 1966
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    invoke-static {v5}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda24;

    invoke-direct {v6, v5}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda24;-><init>(Landroid/animation/AnimatorSet;)V

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_d
    if-nez p2, :cond_e

    .line 1971
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-virtual {v5, v3}, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;->clear(Z)V

    .line 1973
    :cond_e
    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, p2

    iget-object v6, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    if-eqz p2, :cond_f

    .line 1975
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v5

    sub-int/2addr v5, v1

    invoke-virtual {p1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    .line 1976
    :cond_f
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, p2

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->cleanup()V

    .line 1977
    instance-of v1, p1, Lorg/telegram/tgnet/TLRPC$WebPage;

    if-eqz v1, :cond_1f

    .line 1978
    check-cast p1, Lorg/telegram/tgnet/TLRPC$WebPage;

    .line 1980
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, p2

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setWeb(Lorg/telegram/ui/ArticleViewer$CachedWeb;)V

    .line 1981
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, p2

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setType(I)V

    .line 1982
    iget-object v0, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-boolean v0, v0, Lorg/telegram/tgnet/tl/TL_iv$Page;->rtl:Z

    invoke-static {v6, v0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fputisRtl(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Z)V

    .line 1983
    invoke-static {v6, p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fputcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/TLRPC$WebPage;)V

    .line 1985
    iget-object v0, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v0, v0, Lorg/telegram/tgnet/tl/TL_iv$Page;->blocks:Ljava/util/ArrayList;

    .line 1986
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTelegramAiInstantViewSummaries()Z

    move-result v1

    if-nez v1, :cond_11

    .line 1987
    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v1, v1, Lorg/telegram/tgnet/tl/TL_iv$Page;->blocks:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 1988
    iget-object v1, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v1, v1, Lorg/telegram/tgnet/tl/TL_iv$Page;->blocks:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v5

    move v7, v4

    :cond_10
    :goto_8
    if-ge v7, v5, :cond_11

    invoke-virtual {v1, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    add-int/lit8 v7, v7, 0x1

    check-cast v8, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 1989
    invoke-static {v8}, Lorg/telegram/ui/ArticleViewer;->isCocoonAiSummaryBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    move-result v9

    if-nez v9, :cond_10

    .line 1990
    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_8

    .line 1996
    :cond_11
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    move v5, v4

    :goto_9
    if-ge v5, v1, :cond_18

    .line 1998
    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    move-object v8, v7

    check-cast v8, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    if-nez v5, :cond_12

    move v7, v3

    goto :goto_a

    :cond_12
    move v7, v4

    .line 1999
    :goto_a
    iput-boolean v7, v8, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->first:Z

    if-nez v5, :cond_15

    .line 2001
    instance-of v7, v8, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCover;

    if-eqz v7, :cond_16

    .line 2002
    move-object v7, v8

    check-cast v7, Lorg/telegram/tgnet/tl/TL_iv$pageBlockCover;

    .line 2003
    invoke-direct {p0, v7, v4}, Lorg/telegram/ui/ArticleViewer;->getBlockCaption(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object v9

    .line 2004
    invoke-direct {p0, v7, v3}, Lorg/telegram/ui/ArticleViewer;->getBlockCaption(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;I)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object v7

    if-eqz v9, :cond_13

    .line 2005
    instance-of v9, v9, Lorg/telegram/tgnet/tl/TL_iv$textEmpty;

    if-eqz v9, :cond_14

    :cond_13
    if-eqz v7, :cond_16

    instance-of v7, v7, Lorg/telegram/tgnet/tl/TL_iv$textEmpty;

    if-nez v7, :cond_16

    :cond_14
    if-le v1, v3, :cond_16

    .line 2006
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 2007
    instance-of v9, v7, Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;

    if-eqz v9, :cond_16

    .line 2008
    check-cast v7, Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;

    invoke-static {v6, v7}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fputchannelBlock(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;)V

    goto :goto_b

    :cond_15
    if-ne v5, v3, :cond_16

    .line 2012
    invoke-static {v6}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetchannelBlock(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/tl/TL_iv$pageBlockChannel;

    move-result-object v7

    if-eqz v7, :cond_16

    goto :goto_d

    :cond_16
    :goto_b
    add-int/lit8 v7, v1, -0x1

    if-ne v5, v7, :cond_17

    move v11, v5

    goto :goto_c

    :cond_17
    move v11, v4

    :goto_c
    const/4 v9, 0x0

    const/4 v10, 0x0

    move-object v7, v6

    .line 2015
    invoke-static/range {v6 .. v11}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$maddBlock(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;III)V

    :goto_d
    add-int/lit8 v5, v5, 0x1

    goto :goto_9

    .line 2018
    :cond_18
    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    .line 2020
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-eq v0, v3, :cond_1c

    if-ne p3, v2, :cond_19

    goto :goto_10

    .line 2034
    :cond_19
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, p2

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p3, :cond_1a

    invoke-virtual {p3}, Lorg/telegram/ui/ArticleViewer$Sheet;->halfSize()Z

    move-result p3

    if-eqz p3, :cond_1a

    goto :goto_e

    :cond_1a
    move v3, v4

    :goto_e
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz p3, :cond_1b

    const/high16 p3, 0x42000000    # 32.0f

    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p3

    goto :goto_f

    :cond_1b
    move p3, v4

    :goto_f
    invoke-virtual {p1, v3, p3}, Landroidx/recyclerview/widget/LinearLayoutManager;->scrollToPositionWithOffset(II)V

    goto/16 :goto_13

    .line 2021
    :cond_1c
    :goto_10
    sget-object p3, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v0, "articles"

    invoke-virtual {p3, v0, v4}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p3

    .line 2022
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "article"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v5, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->id:J

    invoke-virtual {v0, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 2023
    invoke-interface {p3, p1, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    .line 2025
    const-string/jumbo v1, "r"

    invoke-virtual {p1, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-interface {p3, v1, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    sget-object v5, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v6, v5, Landroid/graphics/Point;->x:I

    iget v5, v5, Landroid/graphics/Point;->y:I

    if-le v6, v5, :cond_1d

    goto :goto_11

    :cond_1d
    move v3, v4

    :goto_11
    if-ne v1, v3, :cond_1e

    .line 2026
    const-string/jumbo v1, "o"

    invoke-virtual {p1, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-interface {p3, p1, v4}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result p1

    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p3, p3, p2

    iget-object p3, p3, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p3}, Landroid/view/View;->getPaddingTop()I

    move-result p3

    sub-int/2addr p1, p3

    goto :goto_12

    :cond_1e
    const/high16 p1, 0x41200000    # 10.0f

    .line 2028
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    :goto_12
    if-eq v0, v2, :cond_20

    .line 2031
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p3, p3, p2

    iget-object p3, p3, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {p3, v0, p1}, Landroidx/recyclerview/widget/LinearLayoutManager;->scrollToPositionWithOffset(II)V

    goto :goto_13

    .line 2036
    :cond_1f
    instance-of p3, p1, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    if-eqz p3, :cond_20

    .line 2037
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p3, p3, p2

    invoke-virtual {p3, v3}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setType(I)V

    .line 2038
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p3, p3, p2

    invoke-virtual {p3, v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->scrollToTop(Z)V

    .line 2039
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p3, p3, p2

    check-cast p1, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    invoke-virtual {p3, p1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->setWeb(Lorg/telegram/ui/ArticleViewer$CachedWeb;)V

    :cond_20
    :goto_13
    if-nez p2, :cond_21

    .line 2042
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->checkScrollAnimated()V

    .line 2045
    :cond_21
    invoke-virtual {p0, v4}, Lorg/telegram/ui/ArticleViewer;->updateTitle(Z)V

    .line 2046
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->updatePages()V

    :cond_22
    :goto_14
    return-void
.end method

.method public static updatePaintColors(Lorg/telegram/ui/IArticleViewer;)V
    .locals 2

    .line 4134
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->listTextPointerPaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_0

    .line 4135
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 4137
    :cond_0
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->listTextNumPaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_1

    .line 4138
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 4140
    :cond_1
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->embedPostAuthorPaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_2

    .line 4141
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 4143
    :cond_2
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->channelNamePaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_3

    .line 4144
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 4146
    :cond_3
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->channelNamePhotoPaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_4

    const/4 v1, -0x1

    .line 4147
    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 4149
    :cond_4
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->relatedArticleHeaderPaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_5

    .line 4150
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getTextColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 4152
    :cond_5
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->relatedArticleTextPaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_6

    .line 4153
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 4156
    :cond_6
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->embedPostDatePaint:Landroid/text/TextPaint;

    if-eqz v0, :cond_7

    .line 4157
    invoke-virtual {p0}, Lorg/telegram/ui/IArticleViewer;->getGrayTextColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    :cond_7
    const/4 v0, 0x1

    .line 4160
    invoke-static {p0, v0}, Lorg/telegram/ui/ArticleViewer;->createPaint(Lorg/telegram/ui/IArticleViewer;Z)V

    .line 4161
    sget-object v0, Lorg/telegram/ui/ArticleViewer;->resources:Lorg/telegram/ui/ArticleViewer$Resources;

    invoke-virtual {v0, p0}, Lorg/telegram/ui/ArticleViewer$Resources;->updatePaintColors(Lorg/telegram/ui/IArticleViewer;)V

    return-void
.end method

.method private updatePaintSize()V
    .locals 2

    const/4 v0, 0x0

    :goto_0
    const/4 v1, 0x2

    if-ge v0, v1, :cond_0

    .line 4128
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v0

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    .line 4129
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v0

    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->resetCachedHeights()V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method private updateSearchButtons()V
    .locals 7

    .line 5271
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->searchResults:Ljava/util/ArrayList;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 5275
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 5276
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_1

    move v0, v1

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getSearchIndex()I

    move-result v0

    .line 5277
    :goto_0
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v1

    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v2

    if-nez v2, :cond_2

    move v2, v1

    goto :goto_1

    :cond_2
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v1

    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v2

    invoke-virtual {v2}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getSearchCount()I

    move-result v2

    goto :goto_1

    .line 5279
    :cond_3
    iget v0, p0, Lorg/telegram/ui/IArticleViewer;->currentSearchIndex:I

    .line 5280
    iget-object v2, p0, Lorg/telegram/ui/IArticleViewer;->searchResults:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    .line 5282
    :goto_1
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    const/4 v4, 0x1

    if-lez v2, :cond_4

    if-eqz v0, :cond_4

    move v5, v4

    goto :goto_2

    :cond_4
    move v5, v1

    :goto_2
    invoke-virtual {v3, v5}, Landroid/view/View;->setEnabled(Z)V

    .line 5283
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    if-lez v2, :cond_5

    add-int/lit8 v5, v2, -0x1

    if-eq v0, v5, :cond_5

    move v1, v4

    :cond_5
    invoke-virtual {v3, v1}, Landroid/view/View;->setEnabled(Z)V

    .line 5284
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    invoke-virtual {v1}, Landroid/view/View;->isEnabled()Z

    move-result v3

    const/high16 v5, 0x3f000000    # 0.5f

    const/high16 v6, 0x3f800000    # 1.0f

    if-eqz v3, :cond_6

    move v3, v6

    goto :goto_3

    :cond_6
    move v3, v5

    :goto_3
    invoke-virtual {v1, v3}, Landroid/view/View;->setAlpha(F)V

    .line 5285
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    invoke-virtual {v1}, Landroid/view/View;->isEnabled()Z

    move-result v3

    if-eqz v3, :cond_7

    move v5, v6

    :cond_7
    invoke-virtual {v1, v5}, Landroid/view/View;->setAlpha(F)V

    .line 5286
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/AnimatedTextView;->cancelAnimation()V

    if-gez v2, :cond_8

    .line 5288
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    const-string v0, ""

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;)V

    return-void

    :cond_8
    if-nez v2, :cond_9

    .line 5290
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    sget v0, Lorg/telegram/messenger/R$string;->NoResult:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;)V

    return-void

    .line 5294
    :cond_9
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    if-ne v2, v4, :cond_a

    .line 5292
    sget v0, Lorg/telegram/messenger/R$string;->OneResult:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;)V

    return-void

    .line 5294
    :cond_a
    const-string v1, "CountOfResults"

    invoke-static {v1, v2}, Lorg/telegram/messenger/LocaleController;->getPluralString(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v1

    add-int/2addr v0, v4

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    filled-new-array {v0, v2}, [Ljava/lang/Object;

    move-result-object v0

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method private wrapInTableBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;
    .locals 2

    .line 1870
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    if-eqz v0, :cond_0

    .line 1871
    check-cast p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    .line 1873
    new-instance v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;

    invoke-direct {v0}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;-><init>()V

    .line 1874
    iget-boolean v1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->isCheckbox:Z

    iput-boolean v1, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->isCheckbox:Z

    .line 1875
    iget-boolean v1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->checked:Z

    iput-boolean v1, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->checked:Z

    .line 1876
    iget-object v1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->parent:Lorg/telegram/ui/ArticleViewer$TL_pageBlockListParent;

    iput-object v1, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->parent:Lorg/telegram/ui/ArticleViewer$TL_pageBlockListParent;

    .line 1877
    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->wrapInTableBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p0

    iput-object p0, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    return-object v0

    .line 1879
    :cond_0
    instance-of v0, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    if-eqz v0, :cond_1

    .line 1880
    check-cast p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    .line 1882
    new-instance v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;

    invoke-direct {v0}, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;-><init>()V

    .line 1883
    iget-boolean v1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->isCheckbox:Z

    iput-boolean v1, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->isCheckbox:Z

    .line 1884
    iget-boolean v1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->checked:Z

    iput-boolean v1, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->checked:Z

    .line 1885
    iget-object v1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->parent:Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListParent;

    iput-object v1, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->parent:Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListParent;

    .line 1886
    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/ArticleViewer;->wrapInTableBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object p0

    iput-object p0, v0, Lorg/telegram/ui/ArticleViewer$TL_pageBlockOrderedListItem;->blockItem:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    return-object v0

    :cond_1
    return-object p2
.end method


# virtual methods
.method public allowTouches()Z
    .locals 0

    .line 3698
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pageSwitchAnimation:Landroid/animation/AnimatorSet;

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public cancelCheckLongPress()V
    .locals 3

    const/4 v0, 0x0

    .line 2590
    iput-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->checkingForLongPress:Z

    .line 2591
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForLongPress:Lorg/telegram/ui/ArticleViewer$CheckForLongPress;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 2592
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v2, v0}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 2593
    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForLongPress:Lorg/telegram/ui/ArticleViewer$CheckForLongPress;

    .line 2595
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

    if-eqz v0, :cond_1

    .line 2596
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v2, v0}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 2597
    iput-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

    :cond_1
    return-void
.end method

.method public checkLayoutForLinks(Landroid/view/MotionEvent;Landroid/view/View;)V
    .locals 2

    .line 3837
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    if-nez v0, :cond_0

    .line 3838
    invoke-static {p1, p2}, Lorg/telegram/ui/ArticleViewer;->isInsideGestureExclusionEdge(Landroid/view/MotionEvent;Landroid/view/View;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 3839
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v1

    invoke-virtual {p0, v0, v1, p2}, Lorg/telegram/ui/ArticleViewer;->startCheckLongPress(FFLandroid/view/View;)V

    .line 3842
    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result p2

    if-eqz p2, :cond_1

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result p1

    const/4 p2, 0x2

    if-eq p1, p2, :cond_1

    .line 3843
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->cancelCheckLongPress()V

    :cond_1
    return-void
.end method

.method public close(ZZ)V
    .locals 10

    .line 5987
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-eqz v0, :cond_f

    iget-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->closeAnimationInProgress:Z

    if-nez v0, :cond_f

    iget-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    if-eqz v0, :cond_f

    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->checkAnimation()Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_3

    .line 5990
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 5991
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    return-void

    .line 5994
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/4 v2, 0x0

    if-nez v0, :cond_4

    .line 5995
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->customView:Landroid/view/View;

    if-eqz v0, :cond_2

    .line 5996
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    const/4 v3, 0x4

    invoke-virtual {v0, v3}, Landroid/view/View;->setVisibility(I)V

    .line 5997
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->customViewCallback:Landroid/webkit/WebChromeClient$CustomViewCallback;

    invoke-interface {v0}, Landroid/webkit/WebChromeClient$CustomViewCallback;->onCustomViewHidden()V

    .line 5998
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->customView:Landroid/view/View;

    invoke-virtual {v0, v3}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 5999
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->customView:Landroid/view/View;

    goto :goto_0

    .line 6000
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->fullscreenedVideo:Lorg/telegram/ui/Components/WebPlayerView;

    if-eqz v0, :cond_3

    .line 6001
    invoke-virtual {v0}, Lorg/telegram/ui/Components/WebPlayerView;->exitFullscreen()V

    :cond_3
    :goto_0
    if-nez p2, :cond_4

    goto/16 :goto_3

    .line 6007
    :cond_4
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-virtual {v0}, Lorg/telegram/ui/Cells/TextSelectionHelper;->isInSelectionMode()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 6008
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-virtual {p0}, Lorg/telegram/ui/Cells/TextSelectionHelper;->clear()V

    return-void

    .line 6011
    :cond_5
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v0}, Lorg/telegram/ui/web/WebActionBar;->isSearching()Z

    move-result v0

    .line 6015
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v4, 0x1

    if-eqz v0, :cond_6

    .line 6012
    invoke-virtual {v3, v1, v4}, Lorg/telegram/ui/web/WebActionBar;->showSearch(ZZ)V

    return-void

    .line 6015
    :cond_6
    invoke-virtual {v3}, Lorg/telegram/ui/web/WebActionBar;->isAddressing()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 6016
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {p0, v1, v4}, Lorg/telegram/ui/web/WebActionBar;->showAddress(ZZ)V

    return-void

    .line 6019
    :cond_7
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    if-eqz v0, :cond_8

    .line 6020
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v0

    iget v3, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    invoke-virtual {v0, v3, v4}, Lorg/telegram/tgnet/ConnectionsManager;->cancelRequest(IZ)V

    .line 6021
    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->openUrlReqId:I

    .line 6022
    invoke-direct {p0, v4, v1}, Lorg/telegram/ui/ArticleViewer;->showProgressView(ZZ)V

    .line 6024
    :cond_8
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->previewsReqId:I

    if-eqz v0, :cond_9

    .line 6025
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v0

    iget v3, p0, Lorg/telegram/ui/ArticleViewer;->previewsReqId:I

    invoke-virtual {v0, v3, v4}, Lorg/telegram/tgnet/ConnectionsManager;->cancelRequest(IZ)V

    .line 6026
    iput v1, p0, Lorg/telegram/ui/ArticleViewer;->previewsReqId:I

    .line 6027
    invoke-direct {p0, v4, v1}, Lorg/telegram/ui/ArticleViewer;->showProgressView(ZZ)V

    .line 6029
    :cond_9
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->saveCurrentPagePosition()V

    if-eqz p1, :cond_a

    if-nez p2, :cond_a

    .line 6031
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->removeLastPageFromStack()Z

    move-result p1

    if-eqz p1, :cond_a

    goto/16 :goto_3

    .line 6036
    :cond_a
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingProgressDidChanged:I

    invoke-virtual {p1, p0, p2}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6037
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidReset:I

    invoke-virtual {p1, p0, p2}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6038
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingPlayStateChanged:I

    invoke-virtual {p1, p0, p2}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6039
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidStart:I

    invoke-virtual {p1, p0, p2}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6040
    iget p1, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->emojiLoaded:I

    invoke-virtual {p1, p0, p2}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6042
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->activityVisibilityController:Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

    if-eqz p1, :cond_b

    .line 6043
    invoke-interface {p1}, Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;->destroy()V

    .line 6044
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->activityVisibilityController:Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

    .line 6047
    :cond_b
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    .line 6049
    :try_start_0
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->visibleDialog:Landroid/app/Dialog;

    if-eqz p1, :cond_c

    .line 6050
    invoke-virtual {p1}, Landroid/app/Dialog;->dismiss()V

    .line 6051
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->visibleDialog:Landroid/app/Dialog;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception p1

    .line 6054
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 6057
    :cond_c
    :goto_1
    new-instance p1, Landroid/animation/AnimatorSet;

    invoke-direct {p1}, Landroid/animation/AnimatorSet;-><init>()V

    .line 6058
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    sget-object v0, Landroid/view/View;->ALPHA:Landroid/util/Property;

    new-array v3, v4, [F

    const/4 v5, 0x0

    aput v5, v3, v1

    .line 6059
    invoke-static {p2, v0, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object p2

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    new-array v6, v4, [F

    aput v5, v6, v1

    .line 6060
    invoke-static {v3, v0, v6}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    sget-object v6, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    const/high16 v7, 0x42600000    # 56.0f

    .line 6061
    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    int-to-float v7, v7

    const/4 v8, 0x2

    new-array v9, v8, [F

    aput v5, v9, v1

    aput v7, v9, v4

    invoke-static {v3, v6, v9}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    const/4 v5, 0x3

    new-array v5, v5, [Landroid/animation/Animator;

    aput-object p2, v5, v1

    aput-object v0, v5, v4

    aput-object v3, v5, v8

    .line 6058
    invoke-virtual {p1, v5}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 6063
    iput v8, p0, Lorg/telegram/ui/ArticleViewer;->animationInProgress:I

    .line 6064
    new-instance p2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda5;

    invoke-direct {p2, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    iput-object p2, p0, Lorg/telegram/ui/ArticleViewer;->animationEndRunnable:Ljava/lang/Runnable;

    const-wide/16 v3, 0x96

    .line 6072
    invoke-virtual {p1, v3, v4}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 6073
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->interpolator:Landroid/view/animation/DecelerateInterpolator;

    invoke-virtual {p1, p2}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 6074
    new-instance p2, Lorg/telegram/ui/ArticleViewer$28;

    invoke-direct {p2, p0}, Lorg/telegram/ui/ArticleViewer$28;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {p1, p2}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 6083
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iput-wide v3, p0, Lorg/telegram/ui/ArticleViewer;->transitionAnimationStartTime:J

    .line 6084
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    invoke-virtual {p2, v8, v2}, Landroid/view/View;->setLayerType(ILandroid/graphics/Paint;)V

    .line 6085
    invoke-virtual {p1}, Landroid/animation/AnimatorSet;->start()V

    .line 6087
    :goto_2
    iget-object p1, p0, Lorg/telegram/ui/IArticleViewer;->videoStates:Landroidx/collection/LongSparseArray;

    invoke-virtual {p1}, Landroidx/collection/LongSparseArray;->size()I

    move-result p1

    .line 6094
    iget-object p2, p0, Lorg/telegram/ui/IArticleViewer;->videoStates:Landroidx/collection/LongSparseArray;

    if-ge v1, p1, :cond_e

    .line 6088
    invoke-virtual {p2, v1}, Landroidx/collection/LongSparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;

    .line 6089
    iget-object p2, p1, Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;->lastFrameBitmap:Landroid/graphics/Bitmap;

    if-eqz p2, :cond_d

    .line 6090
    invoke-virtual {p2}, Landroid/graphics/Bitmap;->recycle()V

    .line 6091
    iput-object v2, p1, Lorg/telegram/ui/ArticleViewer$BlockVideoCellState;->lastFrameBitmap:Landroid/graphics/Bitmap;

    :cond_d
    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    .line 6094
    :cond_e
    invoke-virtual {p2}, Landroidx/collection/LongSparseArray;->clear()V

    :cond_f
    :goto_3
    return-void
.end method

.method public destroy()V
    .locals 6

    const/4 v0, 0x0

    move v1, v0

    .line 16770
    :goto_0
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    .line 16784
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    if-ge v1, v2, :cond_4

    .line 16771
    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    .line 16772
    instance-of v3, v2, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    if-eqz v3, :cond_2

    .line 16773
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v0

    if-eqz v3, :cond_0

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer$PageLayout;->-$$Nest$fgetweb(Lorg/telegram/ui/ArticleViewer$PageLayout;)Lorg/telegram/ui/ArticleViewer$CachedWeb;

    move-result-object v3

    if-ne v3, v2, :cond_0

    .line 16774
    move-object v3, v2

    check-cast v3, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v0

    invoke-virtual {v3, v4}, Lorg/telegram/ui/ArticleViewer$CachedWeb;->detach(Lorg/telegram/ui/ArticleViewer$PageLayout;)V

    .line 16776
    :cond_0
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v4, 0x1

    aget-object v3, v3, v4

    if-eqz v3, :cond_1

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer$PageLayout;->-$$Nest$fgetweb(Lorg/telegram/ui/ArticleViewer$PageLayout;)Lorg/telegram/ui/ArticleViewer$CachedWeb;

    move-result-object v3

    if-ne v3, v2, :cond_1

    .line 16777
    move-object v3, v2

    check-cast v3, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v5, v4

    invoke-virtual {v3, v4}, Lorg/telegram/ui/ArticleViewer$CachedWeb;->detach(Lorg/telegram/ui/ArticleViewer$PageLayout;)V

    .line 16779
    :cond_1
    check-cast v2, Lorg/telegram/ui/ArticleViewer$CachedWeb;

    invoke-virtual {v2}, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;->destroy()V

    goto :goto_1

    .line 16780
    :cond_2
    instance-of v3, v2, Lorg/telegram/tgnet/TLRPC$WebPage;

    if-eqz v3, :cond_3

    .line 16781
    check-cast v2, Lorg/telegram/tgnet/TLRPC$WebPage;

    invoke-static {v2}, Lorg/telegram/ui/web/WebInstantView;->recycle(Lorg/telegram/tgnet/TLRPC$WebPage;)V

    :cond_3
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 16784
    :cond_4
    invoke-virtual {v3}, Ljava/util/ArrayList;->clear()V

    .line 16785
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->destroyArticleViewer()V

    return-void
.end method

.method public destroyArticleViewer()V
    .locals 4

    .line 6214
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    if-nez v0, :cond_0

    goto/16 :goto_5

    .line 6217
    :cond_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    const/4 v2, 0x0

    if-nez v1, :cond_2

    .line 6219
    :try_start_0
    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 6220
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    const-string/jumbo v1, "window"

    invoke-virtual {v0, v1}, Landroid/app/Activity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/WindowManager;

    .line 6221
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-interface {v0, v1}, Landroid/view/WindowManager;->removeViewImmediate(Landroid/view/View;)V

    goto :goto_0

    :catch_0
    move-exception v0

    goto :goto_1

    .line 6223
    :cond_1
    :goto_0
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    .line 6225
    :goto_1
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_2
    :goto_2
    const/4 v0, 0x0

    .line 6228
    :goto_3
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->createdWebViews:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    .line 6232
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->createdWebViews:Ljava/util/ArrayList;

    if-ge v0, v1, :cond_3

    .line 6229
    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/ui/ArticleViewer$BlockEmbedCell;

    const/4 v3, 0x1

    .line 6230
    invoke-virtual {v1, v3}, Lorg/telegram/ui/ArticleViewer$BlockEmbedCell;->destroyWebView(Z)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    .line 6232
    :cond_3
    invoke-virtual {v3}, Ljava/util/ArrayList;->clear()V

    .line 6234
    :try_start_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/16 v1, 0x80

    invoke-virtual {v0, v1}, Landroid/view/Window;->clearFlags(I)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_4

    :catch_1
    move-exception v0

    .line 6236
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 6238
    :goto_4
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    .line 6239
    iput-object v2, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    .line 6240
    sput-object v2, Lorg/telegram/ui/ArticleViewer;->Instance:Lorg/telegram/ui/ArticleViewer;

    .line 6243
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingProgressDidChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6244
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidReset:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6245
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingPlayStateChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6246
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidStart:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6247
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->emojiLoaded:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 6249
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->releaseLiveDrawingTexts()V

    :cond_4
    :goto_5
    return-void
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 6

    .line 4040
    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidStart:I

    const/4 v0, 0x1

    const/4 v1, 0x0

    if-ne p1, p2, :cond_2

    .line 4041
    aget-object p1, p3, v1

    check-cast p1, Lorg/telegram/messenger/MessageObject;

    .line 4042
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz p1, :cond_c

    move p1, v1

    .line 4043
    :goto_0
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length p3, p2

    if-ge p1, p3, :cond_c

    .line 4044
    aget-object p2, p2, p1

    iget-object p2, p2, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result p2

    move p3, v1

    :goto_1
    if-ge p3, p2, :cond_1

    .line 4046
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, p1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v2, p3}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 4047
    instance-of v3, v2, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;

    if-eqz v3, :cond_0

    .line 4048
    check-cast v2, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;

    .line 4049
    invoke-virtual {v2, v0}, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;->updateButtonState(Z)V

    :cond_0
    add-int/lit8 p3, p3, 0x1

    goto :goto_1

    :cond_1
    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 4054
    :cond_2
    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidReset:I

    if-eq p1, p2, :cond_9

    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingPlayStateChanged:I

    if-ne p1, p2, :cond_3

    goto/16 :goto_8

    .line 4070
    :cond_3
    sget p2, Lorg/telegram/messenger/NotificationCenter;->messagePlayingProgressDidChanged:I

    if-ne p1, p2, :cond_6

    .line 4071
    aget-object p1, p3, v1

    check-cast p1, Ljava/lang/Integer;

    .line 4072
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz p2, :cond_c

    move p2, v1

    .line 4073
    :goto_2
    iget-object p3, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length v0, p3

    if-ge p2, v0, :cond_c

    .line 4074
    aget-object p3, p3, p2

    iget-object p3, p3, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p3}, Landroid/view/ViewGroup;->getChildCount()I

    move-result p3

    move v0, v1

    :goto_3
    if-ge v0, p3, :cond_5

    .line 4076
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, p2

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v2, v0}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 4077
    instance-of v3, v2, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;

    if-eqz v3, :cond_4

    .line 4078
    check-cast v2, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;

    .line 4079
    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;->getMessageObject()Lorg/telegram/messenger/MessageObject;

    move-result-object v3

    if-eqz v3, :cond_4

    .line 4080
    invoke-virtual {v3}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v4

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v5

    if-ne v4, v5, :cond_4

    .line 4081
    invoke-static {}, Lorg/telegram/messenger/MediaController;->getInstance()Lorg/telegram/messenger/MediaController;

    move-result-object p3

    invoke-virtual {p3}, Lorg/telegram/messenger/MediaController;->getPlayingMessageObject()Lorg/telegram/messenger/MessageObject;

    move-result-object p3

    if-eqz p3, :cond_5

    .line 4083
    iget v0, p3, Lorg/telegram/messenger/MessageObject;->audioProgress:F

    iput v0, v3, Lorg/telegram/messenger/MessageObject;->audioProgress:F

    .line 4084
    iget v0, p3, Lorg/telegram/messenger/MessageObject;->audioProgressSec:I

    iput v0, v3, Lorg/telegram/messenger/MessageObject;->audioProgressSec:I

    .line 4085
    iget p3, p3, Lorg/telegram/messenger/MessageObject;->audioPlayerDuration:I

    iput p3, v3, Lorg/telegram/messenger/MessageObject;->audioPlayerDuration:I

    .line 4086
    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;->updatePlayingMessageProgress()V

    goto :goto_4

    :cond_4
    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    :cond_5
    :goto_4
    add-int/lit8 p2, p2, 0x1

    goto :goto_2

    .line 4094
    :cond_6
    sget p2, Lorg/telegram/messenger/NotificationCenter;->emojiLoaded:I

    if-ne p1, p2, :cond_c

    .line 4095
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz p1, :cond_c

    move p1, v1

    .line 4096
    :goto_5
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length p3, p2

    if-ge p1, p3, :cond_c

    .line 4097
    aget-object p2, p2, p1

    iget-object p2, p2, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result p2

    move p3, v1

    :goto_6
    if-ge p3, p2, :cond_8

    .line 4099
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, p1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v0, p3}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 4100
    instance-of v2, v0, Lorg/telegram/ui/ArticleViewer$BlockTableCell;

    if-eqz v2, :cond_7

    .line 4101
    check-cast v0, Lorg/telegram/ui/ArticleViewer$BlockTableCell;

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$BlockTableCell;->tableLayout:Lorg/telegram/ui/Components/TableLayout;

    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    goto :goto_7

    .line 4103
    :cond_7
    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    :goto_7
    add-int/lit8 p3, p3, 0x1

    goto :goto_6

    :cond_8
    add-int/lit8 p1, p1, 0x1

    goto :goto_5

    .line 4055
    :cond_9
    :goto_8
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-eqz p1, :cond_c

    move p1, v1

    .line 4056
    :goto_9
    iget-object p2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length p3, p2

    if-ge p1, p3, :cond_c

    .line 4057
    aget-object p2, p2, p1

    iget-object p2, p2, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result p2

    move p3, v1

    :goto_a
    if-ge p3, p2, :cond_b

    .line 4059
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, p1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v2, p3}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 4060
    instance-of v3, v2, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;

    if-eqz v3, :cond_a

    .line 4061
    check-cast v2, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;

    .line 4062
    invoke-virtual {v2}, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;->getMessageObject()Lorg/telegram/messenger/MessageObject;

    move-result-object v3

    if-eqz v3, :cond_a

    .line 4064
    invoke-virtual {v2, v0}, Lorg/telegram/ui/ArticleViewer$BlockAudioCell;->updateButtonState(Z)V

    :cond_a
    add-int/lit8 p3, p3, 0x1

    goto :goto_a

    :cond_b
    add-int/lit8 p1, p1, 0x1

    goto :goto_9

    :cond_c
    return-void
.end method

.method public getAdapter()Lorg/telegram/ui/ArticleViewer$WebpageAdapter;
    .locals 1

    .line 6486
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v0, 0x0

    aget-object p0, p0, v0

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    return-object p0
.end method

.method public getCurrentAccount()I
    .locals 0

    .line 283
    iget p0, p0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    return p0
.end method

.method public getGrayTextColor()I
    .locals 1

    .line 3178
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayText:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result p0

    return p0
.end method

.method public getLastWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;
    .locals 2

    .line 2050
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2051
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_0

    .line 2052
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->webViewContainer:Lorg/telegram/ui/web/BotWebViewContainer;

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer;->checkCreateWebView()V

    .line 2054
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v1

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public getLinkTextColor()I
    .locals 1

    .line 3173
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteLinkText:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result p0

    return p0
.end method

.method public getResources()Lorg/telegram/ui/ArticleViewer$Resources;
    .locals 0

    .line 2618
    sget-object p0, Lorg/telegram/ui/ArticleViewer;->resources:Lorg/telegram/ui/ArticleViewer$Resources;

    return-object p0
.end method

.method public getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public getTextColor()I
    .locals 1

    .line 3168
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result p0

    return p0
.end method

.method public getTextSelectionHelper(Landroid/view/View;)Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;
    .locals 1

    if-eqz p1, :cond_0

    .line 14554
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object p1

    const-string v0, "bottomSheet"

    if-ne p1, v0, :cond_0

    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelperBottomSheet:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    if-eqz p1, :cond_0

    return-object p1

    .line 14557
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    return-object p0
.end method

.method public getThemedColor(I)I
    .locals 0

    .line 14899
    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object p0

    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getNonAnimatedColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method public handleLinkClick(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/ui/Components/TextPaintUrlSpan;)V
    .locals 5

    if-nez p2, :cond_0

    goto/16 :goto_4

    .line 738
    :cond_0
    invoke-virtual {p2}, Lorg/telegram/ui/Components/TextPaintUrlSpan;->getUrl()Ljava/lang/String;

    move-result-object p2

    if-nez p2, :cond_1

    goto :goto_4

    .line 740
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->linkSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    .line 741
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BottomSheet;->dismiss()V

    .line 742
    iput-object v1, p0, Lorg/telegram/ui/IArticleViewer;->linkSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    :cond_2
    const/16 v0, 0x23

    .line 747
    invoke-virtual {p2, v0}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v0

    const/4 v2, -0x1

    const/4 v3, 0x0

    if-eq v0, v2, :cond_5

    .line 749
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v2

    iget-object v2, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_3

    .line 750
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object p1, p1, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    .line 752
    :cond_3
    invoke-static {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p1

    :goto_0
    add-int/lit8 v2, v0, 0x1

    .line 755
    :try_start_0
    invoke-virtual {p2, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    const-string v4, "UTF-8"

    invoke-static {v2, v4}, Lj$/net/URLDecoder;->decode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 757
    :catch_0
    const-string v2, ""

    :goto_1
    if-eqz v0, :cond_4

    .line 759
    invoke-virtual {p2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_6

    :cond_4
    const/4 v3, 0x1

    .line 764
    invoke-virtual {p0, v2, v3}, Lorg/telegram/ui/ArticleViewer;->scrollToAnchor(Ljava/lang/String;Z)Z

    goto :goto_2

    :cond_5
    move-object v2, v1

    :cond_6
    :goto_2
    if-nez v3, :cond_8

    .line 772
    iget-object p1, p0, Lorg/telegram/ui/IArticleViewer;->pressedLinkOwnerLayout:Lorg/telegram/ui/ArticleViewer$DrawingText;

    if-nez p1, :cond_7

    goto :goto_3

    :cond_7
    iget-object v0, p0, Lorg/telegram/ui/IArticleViewer;->pressedLink:Lorg/telegram/ui/Components/LinkSpanDrawable;

    invoke-direct {p0, v0, p1}, Lorg/telegram/ui/ArticleViewer;->makeProgress(Lorg/telegram/ui/Components/LinkSpanDrawable;Lorg/telegram/ui/ArticleViewer$DrawingText;)Lorg/telegram/messenger/browser/Browser$Progress;

    move-result-object v1

    :goto_3
    invoke-virtual {p0, p2, v2, v1}, Lorg/telegram/ui/ArticleViewer;->openWebpageUrl(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)V

    :cond_8
    :goto_4
    return-void
.end method

.method public isLastArticle()Z
    .locals 3

    .line 295
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    .line 296
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v2, 0x1

    sub-int/2addr v0, v2

    invoke-virtual {p0, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p0

    .line 297
    instance-of v0, p0, Lorg/telegram/tgnet/TLRPC$WebPage;

    if-eqz v0, :cond_2

    .line 298
    check-cast p0, Lorg/telegram/tgnet/TLRPC$WebPage;

    .line 299
    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    if-eqz p0, :cond_1

    iget-object p0, p0, Lorg/telegram/tgnet/tl/TL_iv$Page;->local:Ljava/io/File;

    if-eqz p0, :cond_1

    return v1

    :cond_1
    return v2

    :cond_2
    return v1
.end method

.method public isVisible()Z
    .locals 0

    .line 6273
    iget-boolean p0, p0, Lorg/telegram/ui/ArticleViewer;->isVisible:Z

    return p0
.end method

.method public open(Ljava/lang/String;)Z
    .locals 6

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    move-object v0, p0

    move-object v4, p1

    .line 5572
    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/ArticleViewer;->open(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z

    move-result p0

    return p0
.end method

.method public open(Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z
    .locals 6

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v1, 0x0

    move-object v0, p0

    move-object v4, p1

    move-object v5, p2

    .line 5576
    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/ArticleViewer;->open(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z

    move-result p0

    return p0
.end method

.method public open(Lorg/telegram/messenger/MessageObject;)Z
    .locals 6

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    .line 5560
    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/ArticleViewer;->open(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z

    move-result p0

    return p0
.end method

.method public open(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;)Z
    .locals 6

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    .line 5564
    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/ArticleViewer;->open(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z

    move-result p0

    return p0
.end method

.method public open(Lorg/telegram/tgnet/TLRPC$TL_webPage;Ljava/lang/String;)Z
    .locals 6

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v1, 0x0

    move-object v0, p0

    move-object v2, p1

    move-object v3, p2

    .line 5568
    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/ArticleViewer;->open(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z

    move-result p0

    return p0
.end method

.method public openBookmark(Ljava/lang/String;)V
    .locals 4

    .line 5180
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-nez v0, :cond_0

    goto :goto_1

    :cond_0
    if-nez p1, :cond_1

    goto :goto_1

    .line 5182
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/web/WebActionBar;->showAddress(ZZ)V

    .line 5183
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    const/4 v3, 0x0

    invoke-static {v0, v3}, Lorg/telegram/messenger/browser/Browser;->isInternalUri(Landroid/net/Uri;[Z)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 5184
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v0, :cond_2

    .line 5185
    invoke-virtual {v0, v2}, Lorg/telegram/ui/ArticleViewer$Sheet;->dismiss(Z)V

    .line 5187
    :cond_2
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openAsInternalIntent(Landroid/content/Context;Ljava/lang/String;)Z

    return-void

    .line 5188
    :cond_3
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-static {v0, p1, v1}, Lorg/telegram/messenger/browser/Browser;->openInExternalApp(Landroid/content/Context;Ljava/lang/String;Z)Z

    move-result v0

    if-nez v0, :cond_6

    .line 5189
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    if-eqz v0, :cond_5

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_4

    goto :goto_0

    .line 5192
    :cond_4
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v1

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->loadUrl(Ljava/lang/String;)V

    return-void

    .line 5190
    :cond_5
    :goto_0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-static {p0, p1, v3}, Lorg/telegram/messenger/browser/Browser;->openInTelegramBrowser(Landroid/content/Context;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z

    :cond_6
    :goto_1
    return-void
.end method

.method public openHistoryEntry(Lorg/telegram/ui/web/BrowserHistory$Entry;)V
    .locals 3

    .line 5198
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-eqz v0, :cond_3

    if-nez p1, :cond_0

    goto :goto_1

    .line 5199
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/web/WebActionBar;->showAddress(ZZ)V

    .line 5200
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v2

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 5203
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v2

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    iget-object v0, p1, Lorg/telegram/ui/web/BrowserHistory$Entry;->url:Ljava/lang/String;

    iget-object p1, p1, Lorg/telegram/ui/web/BrowserHistory$Entry;->meta:Lorg/telegram/ui/web/WebMetadataCache$WebMetadata;

    invoke-virtual {p0, v0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->loadUrl(Ljava/lang/String;Lorg/telegram/ui/web/WebMetadataCache$WebMetadata;)V

    return-void

    .line 5201
    :cond_2
    :goto_0
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    iget-object p1, p1, Lorg/telegram/ui/web/BrowserHistory$Entry;->url:Ljava/lang/String;

    const/4 v0, 0x0

    invoke-static {p0, p1, v0}, Lorg/telegram/messenger/browser/Browser;->openInTelegramBrowser(Landroid/content/Context;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)Z

    :cond_3
    :goto_1
    return-void
.end method

.method public openPhoto(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Z
    .locals 5

    .line 14580
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 14585
    :cond_0
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    if-eqz v0, :cond_2

    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v0

    invoke-static {v0, p1}, Lorg/telegram/ui/ArticleViewer$WebPageUtils;->isVideo(Lorg/telegram/tgnet/TLRPC$WebPage;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_0

    .line 14589
    :cond_1
    invoke-static {p1}, Ljava/util/Collections;->singletonList(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    move-object v0, p1

    move p1, v1

    goto :goto_1

    .line 14586
    :cond_2
    :goto_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetphotoBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v2

    invoke-direct {v0, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 14587
    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetphotoBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result p1

    .line 14592
    :goto_1
    invoke-static {}, Lorg/telegram/ui/PhotoViewer;->getInstance()Lorg/telegram/ui/PhotoViewer;

    move-result-object v2

    .line 14593
    iget-object v3, p0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-virtual {v2, v3}, Lorg/telegram/ui/PhotoViewer;->setParentActivity(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    .line 14594
    new-instance v3, Lorg/telegram/ui/ArticleViewer$RealPageBlocksAdapter;

    invoke-static {p2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object p2

    const/4 v4, 0x0

    invoke-direct {v3, p0, p2, v0, v4}, Lorg/telegram/ui/ArticleViewer$RealPageBlocksAdapter;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/tgnet/TLRPC$WebPage;Ljava/util/List;Lorg/telegram/ui/ArticleViewer-IA;)V

    new-instance p2, Lorg/telegram/ui/ArticleViewer$PageBlocksPhotoViewerProvider;

    invoke-direct {p2, p0, v0}, Lorg/telegram/ui/ArticleViewer$PageBlocksPhotoViewerProvider;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/util/List;)V

    invoke-virtual {v2, p1, v3, p2}, Lorg/telegram/ui/PhotoViewer;->openPhoto(ILorg/telegram/ui/PhotoViewer$PageBlocksAdapter;Lorg/telegram/ui/PhotoViewer$PhotoViewerProvider;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 14595
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->checkVideoPlayer()V

    const/4 p0, 0x1

    return p0

    :cond_3
    :goto_2
    return v1
.end method

.method public openWebSettings()V
    .locals 4

    .line 5208
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 5210
    new-instance v1, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;

    invoke-direct {v1}, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;-><init>()V

    const/4 v2, 0x1

    .line 5211
    iput-boolean v2, v1, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;->transitionFromLeft:Z

    .line 5212
    new-instance v2, Lorg/telegram/ui/web/WebBrowserSettings;

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda40;

    invoke-direct {v3, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda40;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-direct {v2, v3}, Lorg/telegram/ui/web/WebBrowserSettings;-><init>(Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->showAsSheet(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;)[Lorg/telegram/ui/ActionBar/INavigationLayout;

    :cond_0
    return-void
.end method

.method public openWebpageUrl(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)V
    .locals 6

    .line 3922
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/app/Activity;->isFinishing()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 3923
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 3924
    sget v0, Lorg/telegram/messenger/R$string;->OpenUrlAlert2:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 3925
    const-string v2, "%"

    invoke-virtual {v0, v2}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v2

    .line 3926
    new-instance v3, Landroid/text/SpannableStringBuilder;

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object v4

    invoke-static {v0, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v3, v0}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    if-ltz v2, :cond_1

    .line 3928
    new-instance v0, Landroid/text/style/URLSpan;

    invoke-direct {v0, p1}, Landroid/text/style/URLSpan;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v4

    add-int/2addr v4, v2

    const/16 v5, 0x21

    invoke-virtual {v3, v0, v2, v4, v5}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    .line 3930
    :cond_1
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v4

    invoke-direct {v0, v2, v4}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    sget v2, Lorg/telegram/messenger/R$string;->OpenUrlTitle:I

    .line 3931
    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 3932
    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 3933
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessageTextViewClickable(Z)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/R$string;->Cancel:I

    .line 3934
    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/R$string;->Open:I

    .line 3935
    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda51;

    invoke-direct {v2, p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda51;-><init>(Lorg/telegram/ui/ArticleViewer;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 3938
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->show()Lorg/telegram/ui/ActionBar/AlertDialog;

    return-void

    .line 3941
    :cond_2
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/ArticleViewer;->openWebpageUrlInternal(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/browser/Browser$Progress;)V

    :cond_3
    :goto_0
    return-void
.end method

.method public scrollToAnchor(Ljava/lang/String;Z)Z
    .locals 16

    move-object/from16 v0, p0

    const/4 v1, 0x0

    .line 2530
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    .line 2395
    invoke-static/range {p1 .. p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_0

    return v1

    .line 2398
    :cond_0
    invoke-virtual/range {p1 .. p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v3

    .line 2399
    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v1

    iget-object v4, v4, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v4}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetanchors(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v4

    invoke-virtual {v4, v3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    if-eqz v4, :cond_d

    .line 2401
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetanchorsParent(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/tgnet/tl/TL_iv$textAnchor;

    const/4 v6, 0x0

    const/4 v7, 0x1

    const/4 v8, -0x1

    if-eqz v5, :cond_4

    .line 2403
    new-instance v12, Lorg/telegram/tgnet/tl/TL_iv$pageBlockParagraph;

    invoke-direct {v12}, Lorg/telegram/tgnet/tl/TL_iv$pageBlockParagraph;-><init>()V

    .line 2405
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v2

    iget-object v2, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    .line 2408
    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-nez v2, :cond_1

    .line 2406
    aget-object v2, v4, v1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v2

    iget-object v2, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->cached_page:Lorg/telegram/tgnet/tl/TL_iv$Page;

    iget-object v2, v2, Lorg/telegram/tgnet/tl/TL_iv$Page;->url:Ljava/lang/String;

    invoke-virtual {v2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v2

    goto :goto_0

    .line 2408
    :cond_1
    aget-object v2, v4, v1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v2}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetcurrentPage(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Lorg/telegram/tgnet/TLRPC$WebPage;

    move-result-object v2

    iget-object v2, v2, Lorg/telegram/tgnet/TLRPC$WebPage;->url:Ljava/lang/String;

    invoke-virtual {v2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v2

    .line 2410
    :goto_0
    iget-object v4, v5, Lorg/telegram/tgnet/tl/TL_iv$RichText;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    invoke-static {v4, v2, v3}, Lorg/telegram/ui/web/WebInstantView;->filterRecursiveAnchorLinks(Lorg/telegram/tgnet/tl/TL_iv$RichText;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object v2

    iput-object v2, v12, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    .line 2412
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v2, v12}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mgetTypeForBlock(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)I

    move-result v10

    .line 2413
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v1

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v2, v6, v10}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v11

    .line 2414
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v2, v2, v1

    iget-object v9, v2, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/4 v13, 0x0

    invoke-static/range {v9 .. v15}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mbindBlockToHolder(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;ILandroidx/recyclerview/widget/RecyclerView$ViewHolder;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;IIZ)V

    .line 2416
    new-instance v2, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v2, v3}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;-><init>(Landroid/content/Context;)V

    .line 2417
    invoke-virtual {v2, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setApplyTopPadding(Z)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 2418
    invoke-virtual {v2, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setApplyBottomPadding(Z)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 2419
    new-instance v3, Landroid/widget/LinearLayout;

    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v3, v4}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 2420
    invoke-virtual {v3, v7}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 2422
    new-instance v4, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-direct {v4}, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;-><init>()V

    iput-object v4, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelperBottomSheet:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    .line 2423
    invoke-virtual {v4, v3}, Lorg/telegram/ui/Cells/TextSelectionHelper;->setParentView(Landroid/view/ViewGroup;)V

    .line 2424
    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelperBottomSheet:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    new-instance v5, Lorg/telegram/ui/ArticleViewer$8;

    invoke-direct {v5, v0}, Lorg/telegram/ui/ArticleViewer$8;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v4, v5}, Lorg/telegram/ui/Cells/TextSelectionHelper;->setCallback(Lorg/telegram/ui/Cells/TextSelectionHelper$Callback;)V

    .line 2432
    new-instance v4, Lorg/telegram/ui/ArticleViewer$9;

    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v4, v0, v5}, Lorg/telegram/ui/ArticleViewer$9;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    const/high16 v5, 0x41800000    # 16.0f

    .line 2439
    invoke-virtual {v4, v7, v5}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 2440
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v5

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 2441
    sget v5, Lorg/telegram/messenger/R$string;->InstantViewReference:I

    invoke-static {v5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 2442
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetisRtl(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Z

    move-result v5

    if-eqz v5, :cond_2

    const/4 v5, 0x5

    goto :goto_1

    :cond_2
    const/4 v5, 0x3

    :goto_1
    or-int/lit8 v5, v5, 0x10

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 2443
    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer;->getTextColor()I

    move-result v5

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v5, 0x41900000    # 18.0f

    .line 2444
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    invoke-virtual {v4, v6, v1, v5, v1}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 2445
    new-instance v1, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v5, 0x42400000    # 48.0f

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    add-int/2addr v5, v7

    invoke-direct {v1, v8, v5}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v3, v4, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 2447
    iget-object v1, v11, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    const-string v4, "bottomSheet"

    invoke-virtual {v1, v4}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    .line 2448
    iget-object v1, v11, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v9, -0x1

    const/4 v10, -0x2

    const/4 v11, 0x0

    const/high16 v12, 0x40e00000    # 7.0f

    invoke-static/range {v9 .. v14}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v3, v1, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 2450
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelperBottomSheet:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    iget-object v4, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-virtual {v1, v4}, Lorg/telegram/ui/Cells/TextSelectionHelper;->getOverlayView(Landroid/content/Context;)Lorg/telegram/ui/Cells/TextSelectionHelper$TextSelectionOverlay;

    move-result-object v1

    .line 2451
    new-instance v4, Lorg/telegram/ui/ArticleViewer$10;

    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v4, v0, v5, v3}, Lorg/telegram/ui/ArticleViewer$10;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;Landroid/widget/LinearLayout;)V

    .line 2485
    new-instance v5, Lorg/telegram/ui/ArticleViewer$11;

    invoke-direct {v5, v0}, Lorg/telegram/ui/ArticleViewer$11;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v5}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setDelegate(Lorg/telegram/ui/ActionBar/BottomSheet$BottomSheetDelegate;)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    const/4 v5, -0x2

    .line 2496
    invoke-virtual {v4, v3, v8, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;II)V

    .line 2497
    invoke-virtual {v4, v1, v8, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;II)V

    .line 2498
    invoke-virtual {v2, v4}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->setCustomView(Landroid/view/View;)Lorg/telegram/ui/ActionBar/BottomSheet$Builder;

    .line 2499
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-virtual {v1}, Lorg/telegram/ui/Cells/TextSelectionHelper;->isInSelectionMode()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 2500
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-virtual {v1}, Lorg/telegram/ui/Cells/TextSelectionHelper;->clear()V

    .line 2502
    :cond_3
    invoke-virtual {v2}, Lorg/telegram/ui/ActionBar/BottomSheet$Builder;->create()Lorg/telegram/ui/ActionBar/BottomSheet;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/ui/IArticleViewer;->linkSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ArticleViewer;->showDialog(Landroid/app/Dialog;)V

    goto/16 :goto_3

    .line 2504
    :cond_4
    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v5

    if-ltz v5, :cond_d

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v5

    iget-object v9, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v9, v9, v1

    iget-object v9, v9, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v9}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetblocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v9

    invoke-virtual {v9}, Ljava/util/ArrayList;->size()I

    move-result v9

    if-lt v5, v9, :cond_5

    goto/16 :goto_4

    .line 2507
    :cond_5
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetblocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v5

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v9

    invoke-virtual {v5, v9}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    move-object v12, v5

    check-cast v12, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 2508
    invoke-direct {v0, v12}, Lorg/telegram/ui/ArticleViewer;->getLastNonListPageBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    move-result-object v5

    .line 2510
    instance-of v9, v5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    if-eqz v9, :cond_6

    .line 2511
    check-cast v5, Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;

    invoke-direct {v0, v5}, Lorg/telegram/ui/ArticleViewer;->openAllParentBlocks(Lorg/telegram/ui/ArticleViewer$TL_pageBlockDetailsChild;)Z

    move-result v5

    if-eqz v5, :cond_6

    .line 2512
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mupdateRows(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)V

    .line 2513
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    .line 2516
    :cond_6
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetlocalBlocks(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/ArrayList;

    move-result-object v5

    invoke-virtual {v5, v12}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result v5

    if-eq v5, v8, :cond_7

    .line 2518
    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    .line 2521
    :cond_7
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetanchorsOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    if-eqz v5, :cond_a

    .line 2523
    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v9

    if-ne v9, v8, :cond_9

    .line 2524
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5, v12}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mgetTypeForBlock(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)I

    move-result v10

    .line 2525
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {v5, v6, v10}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v11

    .line 2526
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v9, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/4 v13, 0x0

    invoke-static/range {v9 .. v15}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$mbindBlockToHolder(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;ILandroidx/recyclerview/widget/RecyclerView$ViewHolder;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;IIZ)V

    .line 2527
    iget-object v5, v11, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v1

    iget-object v6, v6, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v6}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    const/high16 v9, 0x40000000    # 2.0f

    invoke-static {v6, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    invoke-static {v1, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v9

    invoke-virtual {v5, v6, v9}, Landroid/view/View;->measure(II)V

    .line 2528
    iget-object v5, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    iget-object v5, v5, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v5}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetanchorsOffset(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Ljava/util/HashMap;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Integer;

    .line 2529
    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v5

    if-ne v5, v8, :cond_8

    goto :goto_2

    :cond_8
    move-object v2, v3

    goto :goto_2

    :cond_9
    move-object v2, v5

    .line 2536
    :cond_a
    :goto_2
    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v1

    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-static {v3}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->-$$Nest$fgetpadding(Lorg/telegram/ui/ArticleViewer$WebpageAdapter;)Z

    move-result v3

    if-eqz v3, :cond_b

    .line 2537
    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v3

    add-int/2addr v3, v7

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    :cond_b
    const/high16 v3, 0x42600000    # 56.0f

    if-eqz p2, :cond_c

    .line 2540
    new-instance v5, Lorg/telegram/ui/ArticleViewer$12;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v1

    invoke-virtual {v6}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v6

    invoke-direct {v5, v0, v6}, Lorg/telegram/ui/ArticleViewer$12;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    .line 2546
    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    invoke-virtual {v5, v4}, Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;->setTargetPosition(I)V

    .line 2547
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    neg-int v3, v3

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    sub-int/2addr v3, v2

    invoke-virtual {v5, v3}, Lorg/telegram/ui/Components/SmoothScroller;->setOffset(I)V

    .line 2548
    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v0, v5}, Landroidx/recyclerview/widget/RecyclerView$LayoutManager;->startSmoothScroll(Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;)V

    goto :goto_3

    .line 2550
    :cond_c
    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    neg-int v3, v3

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    sub-int/2addr v3, v2

    invoke-virtual {v0, v1, v3}, Landroidx/recyclerview/widget/LinearLayoutManager;->scrollToPositionWithOffset(II)V

    :goto_3
    return v7

    :cond_d
    :goto_4
    return v1
.end method

.method public setOpener(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V
    .locals 3

    .line 14971
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-nez v0, :cond_0

    goto :goto_2

    :cond_0
    const/4 v0, 0x0

    .line 14972
    :goto_0
    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length v2, v1

    if-ge v0, v2, :cond_2

    .line 14973
    aget-object v1, v1, v0

    if-nez v1, :cond_1

    goto :goto_1

    .line 14974
    :cond_1
    iget-object v1, v1, Lorg/telegram/ui/ArticleViewer$PageLayout;->webViewContainer:Lorg/telegram/ui/web/BotWebViewContainer;

    invoke-virtual {v1, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->setOpener(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V

    :goto_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    :goto_2
    return-void
.end method

.method public setParentActivity(Landroid/app/Activity;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 21

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    .line 4167
    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->activityVisibilityController:Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

    const/4 v4, 0x0

    if-eqz v3, :cond_0

    .line 4168
    invoke-interface {v3}, Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;->destroy()V

    .line 4169
    iput-object v4, v0, Lorg/telegram/ui/ArticleViewer;->activityVisibilityController:Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

    .line 4171
    :cond_0
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->obtainActivityVisibilityController()Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

    move-result-object v3

    iput-object v3, v0, Lorg/telegram/ui/ArticleViewer;->activityVisibilityController:Lorg/telegram/messenger/utils/WindowVisibilityManager$Controller;

    .line 4173
    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->parentFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    if-eqz v2, :cond_1

    .line 4174
    instance-of v3, v2, Lorg/telegram/ui/EmptyBaseFragment;

    if-nez v3, :cond_1

    invoke-virtual {v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getCurrentAccount()I

    move-result v2

    goto :goto_0

    :cond_1
    sget v2, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    :goto_0
    iput v2, v0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    .line 4175
    invoke-static {v2}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/NotificationCenter;->messagePlayingProgressDidChanged:I

    invoke-virtual {v2, v0, v3}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 4176
    iget v2, v0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v2}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidReset:I

    invoke-virtual {v2, v0, v3}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 4177
    iget v2, v0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v2}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/NotificationCenter;->messagePlayingPlayStateChanged:I

    invoke-virtual {v2, v0, v3}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 4178
    iget v2, v0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v2}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidStart:I

    invoke-virtual {v2, v0, v3}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 4179
    iget v2, v0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v2}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/NotificationCenter;->emojiLoaded:I

    invoke-virtual {v2, v0, v3}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 4180
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-eq v2, v1, :cond_c

    if-eqz v2, :cond_2

    iget-boolean v2, v0, Lorg/telegram/ui/ArticleViewer;->isSheet:Z

    if-eqz v2, :cond_2

    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v2, :cond_2

    iget-object v2, v2, Lorg/telegram/ui/ArticleViewer$Sheet;->dialog:Lorg/telegram/ui/ActionBar/BottomSheetTabDialog;

    if-eqz v2, :cond_2

    goto/16 :goto_7

    .line 4185
    :cond_2
    iput-object v1, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    .line 4187
    sget-object v2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v3, "articles"

    const/4 v5, 0x0

    invoke-virtual {v2, v3, v5}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v2

    .line 4188
    const-string v3, "font_type"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v2

    iput v2, v0, Lorg/telegram/ui/IArticleViewer;->selectedFont:I

    .line 4189
    invoke-static {v0, v5}, Lorg/telegram/ui/ArticleViewer;->createPaint(Lorg/telegram/ui/IArticleViewer;Z)V

    .line 4190
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2}, Landroid/graphics/Paint;-><init>()V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->backgroundPaint:Landroid/graphics/Paint;

    .line 4192
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2}, Landroid/graphics/Paint;-><init>()V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->scrimPaint:Landroid/graphics/Paint;

    .line 4194
    new-instance v2, Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-direct {v2, v0, v1}, Lorg/telegram/ui/ArticleViewer$WindowView;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    .line 4195
    invoke-virtual {v2, v5}, Landroid/view/View;->setWillNotDraw(Z)V

    .line 4196
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 4197
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v2, v5}, Landroid/view/View;->setFocusable(Z)V

    .line 4198
    new-instance v2, Lorg/telegram/ui/ArticleViewer$14;

    invoke-direct {v2, v0, v1}, Lorg/telegram/ui/ArticleViewer$14;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    .line 4241
    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    const/4 v7, -0x1

    const/16 v8, 0x33

    invoke-static {v7, v7, v8}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v9

    invoke-virtual {v6, v2, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4242
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v2, :cond_3

    .line 4243
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v2, v3}, Landroid/view/View;->setFitsSystemWindows(Z)V

    .line 4244
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda9;

    invoke-direct {v6}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda9;-><init>()V

    invoke-virtual {v2, v6}, Landroid/view/View;->setOnApplyWindowInsetsListener(Landroid/view/View$OnApplyWindowInsetsListener;)V

    .line 4253
    :cond_3
    new-instance v2, Landroid/widget/FrameLayout;

    invoke-direct {v2, v1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    const/high16 v6, -0x1000000

    .line 4254
    invoke-virtual {v2, v6}, Landroid/view/View;->setBackgroundColor(I)V

    .line 4255
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    const/4 v9, 0x4

    invoke-virtual {v2, v9}, Landroid/view/View;->setVisibility(I)V

    .line 4256
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    iget-object v10, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    const/high16 v11, -0x40800000    # -1.0f

    invoke-static {v7, v11}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v12

    invoke-virtual {v2, v10, v12}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4258
    new-instance v2, Lorg/telegram/ui/AspectRatioFrameLayout;

    invoke-direct {v2, v1}, Lorg/telegram/ui/AspectRatioFrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenAspectRatioView:Lorg/telegram/ui/AspectRatioFrameLayout;

    .line 4259
    invoke-virtual {v2, v5}, Landroid/view/View;->setVisibility(I)V

    .line 4260
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenAspectRatioView:Lorg/telegram/ui/AspectRatioFrameLayout;

    invoke-virtual {v2, v6}, Landroid/view/View;->setBackgroundColor(I)V

    .line 4261
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenVideoContainer:Landroid/widget/FrameLayout;

    iget-object v10, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenAspectRatioView:Lorg/telegram/ui/AspectRatioFrameLayout;

    const/16 v12, 0x11

    invoke-static {v7, v7, v12}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v12

    invoke-virtual {v2, v10, v12}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4263
    new-instance v2, Landroid/view/TextureView;

    invoke-direct {v2, v1}, Landroid/view/TextureView;-><init>(Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->fullscreenTextureView:Landroid/view/TextureView;

    const/4 v2, 0x2

    .line 4265
    new-array v2, v2, [Lorg/telegram/ui/ArticleViewer$PageLayout;

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    move v2, v5

    .line 4266
    :goto_1
    iget-object v10, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    array-length v12, v10

    if-ge v2, v12, :cond_5

    .line 4267
    new-instance v12, Lorg/telegram/ui/ArticleViewer$PageLayout;

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v13

    invoke-direct {v12, v0, v1, v13}, Lorg/telegram/ui/ArticleViewer$PageLayout;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    aput-object v12, v10, v2

    if-nez v2, :cond_4

    move v10, v5

    goto :goto_2

    :cond_4
    const/16 v10, 0x8

    .line 4268
    :goto_2
    invoke-virtual {v12, v10}, Landroid/view/View;->setVisibility(I)V

    .line 4269
    iget-object v10, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/4 v13, -0x1

    const/high16 v14, -0x40800000    # -1.0f

    const/16 v15, 0x77

    const/16 v16, 0x0

    const/16 v17, 0x0

    invoke-static/range {v13 .. v19}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v13

    invoke-virtual {v10, v12, v13}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4270
    iget-object v10, v12, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    new-instance v13, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda15;

    invoke-direct {v13, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda15;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v10, v13}, Lorg/telegram/ui/Components/RecyclerListView;->setOnItemLongClickListener(Lorg/telegram/ui/Components/RecyclerListView$OnItemLongClickListener;)V

    .line 4278
    iget-object v10, v12, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    new-instance v13, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda16;

    invoke-direct {v13, v0, v12}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda16;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ArticleViewer$PageLayout;)V

    invoke-virtual {v10, v13}, Lorg/telegram/ui/Components/RecyclerListView;->setOnItemClickListener(Lorg/telegram/ui/Components/RecyclerListView$OnItemClickListenerExtended;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 4370
    :cond_5
    new-instance v2, Landroid/widget/FrameLayout;

    invoke-direct {v2, v1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->bulletinContainer:Landroid/widget/FrameLayout;

    .line 4371
    iget-object v10, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v12, v0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    const/4 v13, 0x0

    if-eqz v12, :cond_6

    invoke-virtual {v12}, Lorg/telegram/ui/ArticleViewer$Sheet;->halfSize()Z

    move-result v12

    if-nez v12, :cond_6

    const/high16 v12, 0x42600000    # 56.0f

    move/from16 v18, v12

    goto :goto_3

    :cond_6
    move/from16 v18, v13

    :goto_3
    const/16 v19, 0x0

    const/16 v20, 0x0

    const/4 v14, -0x1

    const/high16 v15, -0x40800000    # -1.0f

    const/16 v16, 0x77

    const/16 v17, 0x0

    invoke-static/range {v14 .. v20}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v12

    invoke-virtual {v10, v2, v12}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4373
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->headerPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v6}, Landroid/graphics/Paint;->setColor(I)V

    .line 4374
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->statusBarPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v6}, Landroid/graphics/Paint;->setColor(I)V

    .line 4375
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->headerProgressPaint:Landroid/graphics/Paint;

    const v10, -0xdbdbda

    invoke-virtual {v2, v10}, Landroid/graphics/Paint;->setColor(I)V

    .line 4376
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->navigationBarPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v6}, Landroid/graphics/Paint;->setColor(I)V

    .line 4377
    new-instance v2, Lorg/telegram/ui/ArticleViewer$15;

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer;->getResourcesProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v6

    invoke-direct {v2, v0, v1, v6}, Lorg/telegram/ui/ArticleViewer$15;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    .line 4441
    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v6, :cond_7

    move v6, v3

    goto :goto_4

    :cond_7
    move v6, v5

    :goto_4
    invoke-virtual {v2, v6}, Lorg/telegram/ui/web/WebActionBar;->occupyStatusBar(Z)V

    .line 4442
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    const/4 v10, -0x2

    const/16 v12, 0x30

    invoke-static {v7, v10, v12}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v10

    invoke-virtual {v2, v6, v10}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4443
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda17;

    invoke-direct {v6, v0, v1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda17;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/app/Activity;)V

    invoke-virtual {v2, v6}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 4538
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v2, v2, Lorg/telegram/ui/web/WebActionBar;->addressEditText:Lorg/telegram/ui/Components/EditTextBoldCursor;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$16;

    invoke-direct {v6, v0}, Lorg/telegram/ui/ArticleViewer$16;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v6}, Lorg/telegram/ui/Components/EditTextBoldCursor;->addTextChangedListener(Landroid/text/TextWatcher;)V

    .line 4551
    new-instance v2, Lorg/telegram/ui/web/AddressBarList;

    invoke-direct {v2, v1}, Lorg/telegram/ui/web/AddressBarList;-><init>(Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->addressBarList:Lorg/telegram/ui/web/AddressBarList;

    .line 4552
    invoke-virtual {v2, v13}, Lorg/telegram/ui/web/AddressBarList;->setOpenProgress(F)V

    .line 4553
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->addressBarList:Lorg/telegram/ui/web/AddressBarList;

    iget-object v2, v2, Lorg/telegram/ui/web/AddressBarList;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$17;

    invoke-direct {v6, v0}, Lorg/telegram/ui/ArticleViewer$17;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v6}, Landroidx/recyclerview/widget/RecyclerView;->addOnScrollListener(Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V

    .line 4561
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->addressBarList:Lorg/telegram/ui/web/AddressBarList;

    invoke-static {v7, v11}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v10

    invoke-virtual {v2, v6, v10}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 4563
    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda18;

    invoke-direct {v2, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda18;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->lineProgressTickRunnable:Ljava/lang/Runnable;

    .line 4577
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v2, v2, Lorg/telegram/ui/web/WebActionBar;->backButton:Landroid/widget/ImageView;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda19;

    invoke-direct {v6, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda19;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v6}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 4592
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v2, v2, Lorg/telegram/ui/web/WebActionBar;->backButton:Landroid/widget/ImageView;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda20;

    invoke-direct {v6, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda20;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v6}, Landroid/view/View;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    .line 4824
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda21;

    invoke-direct {v6, v0, v1}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda21;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/app/Activity;)V

    invoke-virtual {v2, v6}, Lorg/telegram/ui/web/WebActionBar;->setMenuListener(Lorg/telegram/messenger/Utilities$Callback;)V

    .line 4982
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v2, v2, Lorg/telegram/ui/web/WebActionBar;->forwardButton:Landroid/widget/ImageView;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda22;

    invoke-direct {v6, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda22;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v6}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 4994
    new-instance v2, Lorg/telegram/ui/ArticleViewer$20;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v2, v0, v6}, Lorg/telegram/ui/ArticleViewer$20;-><init>(Lorg/telegram/ui/ArticleViewer;Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    .line 5003
    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda23;

    invoke-direct {v6}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda23;-><init>()V

    invoke-virtual {v2, v6}, Landroid/view/View;->setOnTouchListener(Landroid/view/View$OnTouchListener;)V

    .line 5004
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v5}, Landroid/view/View;->setWillNotDraw(Z)V

    .line 5005
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    const/high16 v6, 0x424c0000    # 51.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {v2, v6}, Landroid/view/View;->setTranslationY(F)V

    .line 5006
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v9}, Landroid/view/View;->setVisibility(I)V

    .line 5007
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v3}, Landroid/view/View;->setFocusable(Z)V

    .line 5008
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v3}, Landroid/view/View;->setFocusableInTouchMode(Z)V

    .line 5009
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v3}, Landroid/view/View;->setClickable(Z)V

    .line 5010
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    const/high16 v6, 0x40400000    # 3.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    invoke-virtual {v2, v5, v6, v5, v5}, Landroid/view/View;->setPadding(IIII)V

    .line 5011
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    const/16 v9, 0x50

    invoke-static {v7, v8, v9}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v9

    invoke-virtual {v2, v6, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 5013
    new-instance v2, Lorg/telegram/ui/Stories/recorder/KeyboardNotifier;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    new-instance v9, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda10;

    invoke-direct {v9, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda10;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-direct {v2, v6, v9}, Lorg/telegram/ui/Stories/recorder/KeyboardNotifier;-><init>(Landroid/view/View;Lorg/telegram/messenger/Utilities$Callback;)V

    .line 5017
    new-instance v2, Landroid/widget/ImageView;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v2, v6}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    .line 5018
    sget-object v6, Landroid/widget/ImageView$ScaleType;->CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v2, v6}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 5019
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    sget v9, Lorg/telegram/messenger/R$drawable;->msg_go_up:I

    invoke-virtual {v2, v9}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 5020
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    new-instance v9, Landroid/graphics/PorterDuffColorFilter;

    sget v10, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {v0, v10}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v11

    sget-object v13, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v9, v11, v13}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v2, v9}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 5021
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarActionModeDefaultSelector:I

    invoke-virtual {v0, v9}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v11

    invoke-static {v11, v3}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object v11

    invoke-virtual {v2, v11}, Landroid/view/View;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 5022
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    iget-object v11, v0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    const/high16 v19, 0x42400000    # 48.0f

    const/16 v20, 0x0

    const/16 v14, 0x30

    const/high16 v15, 0x42400000    # 48.0f

    const/16 v16, 0x35

    const/16 v17, 0x0

    const/16 v18, 0x0

    invoke-static/range {v14 .. v20}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v14

    invoke-virtual {v2, v11, v14}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 5023
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    new-instance v11, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda11;

    invoke-direct {v11, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda11;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v11}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 5032
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchUpButton:Landroid/widget/ImageView;

    sget v11, Lorg/telegram/messenger/R$string;->AccDescrSearchNext:I

    invoke-static {v11}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v2, v11}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 5034
    new-instance v2, Landroid/widget/ImageView;

    iget-object v11, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v2, v11}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    .line 5035
    invoke-virtual {v2, v6}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 5036
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    sget v6, Lorg/telegram/messenger/R$drawable;->msg_go_down:I

    invoke-virtual {v2, v6}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 5037
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    new-instance v6, Landroid/graphics/PorterDuffColorFilter;

    invoke-virtual {v0, v10}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v11

    invoke-direct {v6, v11, v13}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v2, v6}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 5038
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    invoke-virtual {v0, v9}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v6

    invoke-static {v6, v3}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object v6

    invoke-virtual {v2, v6}, Landroid/view/View;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 5039
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    const/16 v19, 0x0

    const/16 v13, 0x30

    const/high16 v14, 0x42400000    # 48.0f

    const/16 v15, 0x35

    const/16 v16, 0x0

    invoke-static/range {v13 .. v19}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v9

    invoke-virtual {v2, v6, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 5040
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    new-instance v6, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda12;

    invoke-direct {v6, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda12;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v6}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 5049
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchDownButton:Landroid/widget/ImageView;

    sget v6, Lorg/telegram/messenger/R$string;->AccDescrSearchPrev:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v6}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 5051
    new-instance v2, Lorg/telegram/ui/Components/AnimatedTextView;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    invoke-direct {v2, v6, v3, v3, v3}, Lorg/telegram/ui/Components/AnimatedTextView;-><init>(Landroid/content/Context;ZZZ)V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    const v6, 0x3f19999a    # 0.6f

    .line 5052
    invoke-virtual {v2, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setScaleProperty(F)V

    .line 5053
    iget-object v13, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    const-wide/16 v17, 0x15e

    sget-object v19, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const v14, 0x3ecccccd    # 0.4f

    const-wide/16 v15, 0x0

    invoke-virtual/range {v13 .. v19}, Lorg/telegram/ui/Components/AnimatedTextView;->setAnimationProperties(FJJLandroid/animation/TimeInterpolator;)V

    .line 5054
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v0, v10}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v6

    invoke-virtual {v2, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextColor(I)V

    .line 5055
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    const/high16 v6, 0x41700000    # 15.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {v2, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextSize(F)V

    .line 5056
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v6

    invoke-virtual {v2, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 5057
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    const/4 v6, 0x3

    invoke-virtual {v2, v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setGravity(I)V

    .line 5058
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v2}, Lorg/telegram/ui/Components/AnimatedTextView;->getDrawable()Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    move-result-object v2

    sget-object v6, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v6, v6, Landroid/graphics/Point;->x:I

    invoke-virtual {v2, v6}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setOverrideFullWidth(I)V

    .line 5059
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->searchCountText:Lorg/telegram/ui/Components/AnimatedTextView;

    const/high16 v18, 0x42d80000    # 108.0f

    const/16 v19, 0x0

    const/4 v13, -0x2

    const/high16 v14, -0x40000000    # -2.0f

    const/16 v15, 0x13

    const/high16 v16, 0x41900000    # 18.0f

    const/16 v17, 0x0

    invoke-static/range {v13 .. v19}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v9

    invoke-virtual {v2, v6, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 5061
    new-instance v2, Landroid/view/WindowManager$LayoutParams;

    invoke-direct {v2}, Landroid/view/WindowManager$LayoutParams;-><init>()V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->windowLayoutParams:Landroid/view/WindowManager$LayoutParams;

    .line 5062
    iput v7, v2, Landroid/view/WindowManager$LayoutParams;->height:I

    const/4 v6, -0x3

    .line 5063
    iput v6, v2, Landroid/view/WindowManager$LayoutParams;->format:I

    .line 5064
    iput v7, v2, Landroid/view/WindowManager$LayoutParams;->width:I

    .line 5065
    iput v8, v2, Landroid/view/WindowManager$LayoutParams;->gravity:I

    const/16 v6, 0x62

    .line 5066
    iput v6, v2, Landroid/view/WindowManager$LayoutParams;->type:I

    .line 5067
    iput v12, v2, Landroid/view/WindowManager$LayoutParams;->softInputMode:I

    const/high16 v6, 0x20000

    .line 5068
    iput v6, v2, Landroid/view/WindowManager$LayoutParams;->flags:I

    .line 5071
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-nez v2, :cond_8

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-static {v2, v4, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I[ZZ)I

    move-result v2

    goto :goto_5

    :cond_8
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v2

    .line 5072
    :goto_5
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->computePerceivedBrightness(I)F

    move-result v4

    const v6, 0x3f389375    # 0.721f

    cmpl-float v4, v4, v6

    if-ltz v4, :cond_9

    .line 5074
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v6, 0x1a

    if-lt v4, v6, :cond_9

    const/16 v4, 0x710

    goto :goto_6

    :cond_9
    const/16 v4, 0x700

    .line 5077
    :goto_6
    iget-object v6, v0, Lorg/telegram/ui/ArticleViewer;->navigationBarPaint:Landroid/graphics/Paint;

    invoke-virtual {v6, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 5078
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->windowLayoutParams:Landroid/view/WindowManager$LayoutParams;

    iput v4, v2, Landroid/view/WindowManager$LayoutParams;->systemUiVisibility:I

    .line 5080
    iget v4, v2, Landroid/view/WindowManager$LayoutParams;->flags:I

    const v6, -0x7ffeff00

    or-int/2addr v4, v6

    iput v4, v2, Landroid/view/WindowManager$LayoutParams;->flags:I

    .line 5083
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v6, 0x1c

    if-lt v4, v6, :cond_a

    .line 5084
    invoke-static {v2, v3}, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticApiModelOutline1;->m(Landroid/view/WindowManager$LayoutParams;I)V

    .line 5087
    :cond_a
    new-instance v2, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-direct {v2}, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;-><init>()V

    iput-object v2, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    .line 5088
    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v5

    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Cells/TextSelectionHelper;->setParentView(Landroid/view/ViewGroup;)V

    .line 5089
    iget v2, v0, Lorg/telegram/ui/ArticleViewer;->currentAccount:I

    invoke-static {v2}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v2

    invoke-virtual {v2}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object v2

    invoke-virtual {v2}, Lorg/telegram/messenger/TranslateController;->isContextTranslateEnabled()Z

    move-result v2

    if-eqz v2, :cond_b

    .line 5090
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    new-instance v3, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda13;

    invoke-direct {v3, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda13;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Cells/TextSelectionHelper;->setOnTranslate(Lorg/telegram/ui/Cells/TextSelectionHelper$OnTranslateListener;)V

    .line 5099
    :cond_b
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v3, v3, v5

    iget-object v3, v3, Lorg/telegram/ui/ArticleViewer$PageLayout;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    iput-object v3, v2, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    .line 5100
    new-instance v3, Lorg/telegram/ui/ArticleViewer$21;

    invoke-direct {v3, v0}, Lorg/telegram/ui/ArticleViewer$21;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Cells/TextSelectionHelper;->setCallback(Lorg/telegram/ui/Cells/TextSelectionHelper$Callback;)V

    .line 5115
    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    iget-object v3, v0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    invoke-virtual {v3, v1}, Lorg/telegram/ui/Cells/TextSelectionHelper;->getOverlayView(Landroid/content/Context;)Lorg/telegram/ui/Cells/TextSelectionHelper$TextSelectionOverlay;

    move-result-object v1

    invoke-virtual {v2, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 5117
    new-instance v1, Lorg/telegram/ui/PinchToZoomHelper;

    iget-object v2, v0, Lorg/telegram/ui/ArticleViewer;->containerView:Landroid/widget/FrameLayout;

    invoke-direct {v1, v2, v2}, Lorg/telegram/ui/PinchToZoomHelper;-><init>(Landroid/view/ViewGroup;Landroid/view/ViewGroup;)V

    iput-object v1, v0, Lorg/telegram/ui/ArticleViewer;->pinchToZoomHelper:Lorg/telegram/ui/PinchToZoomHelper;

    .line 5118
    new-instance v2, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda14;

    invoke-direct {v2, v0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda14;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v1, v2}, Lorg/telegram/ui/PinchToZoomHelper;->setClipBoundsListener(Lorg/telegram/ui/PinchToZoomHelper$ClipBoundsListener;)V

    .line 5122
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->pinchToZoomHelper:Lorg/telegram/ui/PinchToZoomHelper;

    new-instance v2, Lorg/telegram/ui/ArticleViewer$22;

    invoke-direct {v2, v0}, Lorg/telegram/ui/ArticleViewer$22;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v1, v2}, Lorg/telegram/ui/PinchToZoomHelper;->setCallback(Lorg/telegram/ui/PinchToZoomHelper$Callback;)V

    .line 5130
    iget-object v1, v0, Lorg/telegram/ui/ArticleViewer;->backgroundPaint:Landroid/graphics/Paint;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_iv_background:I

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ArticleViewer;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 5131
    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer;->updatePaintColors(Lorg/telegram/ui/IArticleViewer;)V

    return-void

    .line 4181
    :cond_c
    :goto_7
    invoke-static {v0}, Lorg/telegram/ui/ArticleViewer;->updatePaintColors(Lorg/telegram/ui/IArticleViewer;)V

    .line 4182
    invoke-direct {v0}, Lorg/telegram/ui/ArticleViewer;->refreshThemeColors()V

    return-void
.end method

.method public showDialog(Landroid/app/Dialog;)V
    .locals 2

    .line 6277
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->parentActivity:Landroid/app/Activity;

    if-nez v0, :cond_0

    goto :goto_1

    .line 6281
    :cond_0
    :try_start_0
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->visibleDialog:Landroid/app/Dialog;

    if-eqz v0, :cond_1

    .line 6282
    invoke-virtual {v0}, Landroid/app/Dialog;->dismiss()V

    const/4 v0, 0x0

    .line 6283
    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->visibleDialog:Landroid/app/Dialog;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 6286
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 6289
    :cond_1
    :goto_0
    :try_start_1
    iput-object p1, p0, Lorg/telegram/ui/ArticleViewer;->visibleDialog:Landroid/app/Dialog;

    const/4 v0, 0x1

    .line 6290
    invoke-virtual {p1, v0}, Landroid/app/Dialog;->setCanceledOnTouchOutside(Z)V

    .line 6291
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->visibleDialog:Landroid/app/Dialog;

    new-instance v1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda45;

    invoke-direct {v1, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda45;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, v1}, Landroid/app/Dialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    .line 6292
    invoke-virtual {p1}, Landroid/app/Dialog;->show()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    :catch_1
    move-exception p0

    .line 6294
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :goto_1
    return-void
.end method

.method public showSearchPanel(Z)V
    .locals 4

    .line 5389
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 5390
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    .line 5391
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    .line 5393
    :cond_0
    iget v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAlpha:F

    if-eqz p1, :cond_1

    const/high16 v2, 0x3f800000    # 1.0f

    goto :goto_0

    :cond_1
    const/4 v2, 0x0

    :goto_0
    const/4 v3, 0x2

    new-array v3, v3, [F

    aput v0, v3, v1

    const/4 v0, 0x1

    aput v2, v3, v0

    invoke-static {v3}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAnimator:Landroid/animation/ValueAnimator;

    .line 5394
    new-instance v1, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda65;

    invoke-direct {v1, p0}, Lorg/telegram/ui/ArticleViewer$$ExternalSyntheticLambda65;-><init>(Lorg/telegram/ui/ArticleViewer;)V

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 5398
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAnimator:Landroid/animation/ValueAnimator;

    new-instance v1, Lorg/telegram/ui/ArticleViewer$23;

    invoke-direct {v1, p0, p1}, Lorg/telegram/ui/ArticleViewer$23;-><init>(Lorg/telegram/ui/ArticleViewer;Z)V

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 5408
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAnimator:Landroid/animation/ValueAnimator;

    const-wide/16 v0, 0x140

    invoke-virtual {p1, v0, v1}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 5409
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAnimator:Landroid/animation/ValueAnimator;

    sget-object v0, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {p1, v0}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 5410
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanelAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {p0}, Landroid/animation/ValueAnimator;->start()V

    return-void
.end method

.method public startCheckLongPress(FFLandroid/view/View;)V
    .locals 2

    .line 2574
    iget-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->checkingForLongPress:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 2577
    iput-boolean v0, p0, Lorg/telegram/ui/ArticleViewer;->checkingForLongPress:Z

    .line 2578
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

    if-nez v0, :cond_1

    .line 2579
    new-instance v0, Lorg/telegram/ui/ArticleViewer$CheckForTap;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/telegram/ui/ArticleViewer$CheckForTap;-><init>(Lorg/telegram/ui/ArticleViewer;Lorg/telegram/ui/ArticleViewer-IA;)V

    iput-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

    .line 2581
    :cond_1
    invoke-virtual {p3}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {p3}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    const-string v1, "bottomSheet"

    if-ne v0, v1, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelperBottomSheet:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    if-eqz v0, :cond_2

    float-to-int p1, p1

    float-to-int p2, p2

    .line 2582
    invoke-virtual {v0, p1, p2, p3}, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;->setMaybeView(IILandroid/view/View;)V

    goto :goto_0

    .line 2584
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->textSelectionHelper:Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;

    float-to-int p1, p1

    float-to-int p2, p2

    invoke-virtual {v0, p1, p2, p3}, Lorg/telegram/ui/Cells/TextSelectionHelper$ArticleTextSelectionHelper;->setMaybeView(IILandroid/view/View;)V

    .line 2586
    :goto_0
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pendingCheckForTap:Lorg/telegram/ui/ArticleViewer$CheckForTap;

    invoke-static {}, Landroid/view/ViewConfiguration;->getTapTimeout()I

    move-result p2

    int-to-long p2, p2

    invoke-virtual {p1, p0, p2, p3}, Landroid/view/View;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method public updatePages()V
    .locals 10

    .line 14922
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    if-eqz v0, :cond_11

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v2, v0, v1

    if-eqz v2, :cond_11

    const/4 v3, 0x1

    aget-object v0, v0, v3

    if-nez v0, :cond_0

    goto/16 :goto_a

    .line 14926
    :cond_0
    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/4 v2, 0x0

    const/high16 v4, 0x3f800000    # 1.0f

    if-eqz v0, :cond_1

    move v0, v2

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v1

    invoke-virtual {v0}, Landroid/view/View;->getTranslationX()F

    move-result v0

    iget-object v5, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v5, v5, v1

    invoke-virtual {v5}, Landroid/view/View;->getWidth()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v0, v5

    sub-float v0, v4, v0

    :goto_0
    sub-float v5, v4, v0

    .line 14929
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v1

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v6

    .line 14930
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v8, v8, v1

    invoke-virtual {v8}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getProgress()F

    move-result v8

    invoke-virtual {v7, v1, v8}, Lorg/telegram/ui/web/WebActionBar;->setProgress(IF)V

    .line 14931
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v8, v8, v3

    invoke-virtual {v8}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getProgress()F

    move-result v8

    invoke-virtual {v7, v3, v8}, Lorg/telegram/ui/web/WebActionBar;->setProgress(IF)V

    .line 14932
    iget-object v7, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v7, v5}, Lorg/telegram/ui/web/WebActionBar;->setTransitionProgress(F)V

    if-eqz v6, :cond_2

    .line 14934
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->hideLineProgressView()V

    .line 14936
    :cond_2
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v6}, Lorg/telegram/ui/web/WebActionBar;->isAddressing()Z

    move-result v6

    const/high16 v7, 0x3f000000    # 0.5f

    if-nez v6, :cond_a

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    invoke-virtual {v6}, Lorg/telegram/ui/web/WebActionBar;->isSearching()Z

    move-result v6

    if-nez v6, :cond_a

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v6}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fgetmovingPage(Lorg/telegram/ui/ArticleViewer$WindowView;)Z

    move-result v6

    if-nez v6, :cond_3

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v6}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fgetopeningPage(Lorg/telegram/ui/ArticleViewer$WindowView;)Z

    move-result v6

    if-eqz v6, :cond_a

    .line 14937
    :cond_3
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v1

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->hasBackButton()Z

    move-result v6

    if-nez v6, :cond_5

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v6

    if-le v6, v3, :cond_4

    goto :goto_1

    :cond_4
    move v6, v2

    goto :goto_2

    :cond_5
    :goto_1
    move v6, v4

    :goto_2
    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v8, v8, v3

    invoke-virtual {v8}, Lorg/telegram/ui/ArticleViewer$PageLayout;->hasBackButton()Z

    move-result v8

    if-nez v8, :cond_6

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->pagesStack:Ljava/util/ArrayList;

    invoke-virtual {v8}, Ljava/util/ArrayList;->size()I

    move-result v8

    const/4 v9, 0x2

    if-le v8, v9, :cond_7

    :cond_6
    move v2, v4

    :cond_7
    invoke-static {v6, v2, v5}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v2

    .line 14938
    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v6, v6, Lorg/telegram/ui/web/WebActionBar;->backButtonDrawable:Lorg/telegram/ui/ActionBar/BackDrawable;

    sub-float/2addr v4, v2

    invoke-virtual {v6, v4, v1}, Lorg/telegram/ui/ActionBar/BackDrawable;->setRotation(FZ)V

    .line 14939
    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v4, v4, Lorg/telegram/ui/web/WebActionBar;->forwardButtonDrawable:Lorg/telegram/ui/web/WebActionBar$ForwardDrawable;

    invoke-virtual {v4, v1}, Lorg/telegram/ui/web/WebActionBar$ForwardDrawable;->setState(Z)V

    .line 14940
    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    cmpl-float v2, v2, v7

    if-lez v2, :cond_8

    move v2, v3

    goto :goto_3

    :cond_8
    move v2, v1

    :goto_3
    invoke-virtual {v4, v2}, Lorg/telegram/ui/web/WebActionBar;->setBackButtonCached(Z)V

    .line 14941
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v1

    invoke-virtual {v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->hasForwardButton()Z

    move-result v4

    invoke-virtual {v2, v4}, Lorg/telegram/ui/web/WebActionBar;->setHasForward(Z)V

    .line 14942
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v1

    invoke-virtual {v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isLocal()Z

    move-result v4

    invoke-virtual {v2, v4}, Lorg/telegram/ui/web/WebActionBar;->setIsLocal(Z)V

    .line 14943
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v1

    invoke-virtual {v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v4

    if-eqz v4, :cond_9

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v1

    invoke-virtual {v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v4

    invoke-virtual {v4}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->isPageLoaded()Z

    move-result v4

    if-eqz v4, :cond_9

    move v4, v3

    goto :goto_4

    :cond_9
    move v4, v1

    :goto_4
    invoke-virtual {v2, v4}, Lorg/telegram/ui/web/WebActionBar;->setIsLoaded(Z)V

    .line 14946
    :cond_a
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->page0Background:Lorg/telegram/ui/Components/AnimatedColor;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v1

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v6

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v8}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fgetmovingPage(Lorg/telegram/ui/ArticleViewer$WindowView;)Z

    move-result v8

    if-nez v8, :cond_c

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v8}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fgetopeningPage(Lorg/telegram/ui/ArticleViewer$WindowView;)Z

    move-result v8

    if-eqz v8, :cond_b

    goto :goto_5

    :cond_b
    move v8, v1

    goto :goto_6

    :cond_c
    :goto_5
    move v8, v3

    :goto_6
    invoke-virtual {v4, v6, v8}, Lorg/telegram/ui/Components/AnimatedColor;->set(IZ)I

    move-result v4

    invoke-virtual {v2, v1, v4}, Lorg/telegram/ui/web/WebActionBar;->setBackgroundColor(II)V

    .line 14947
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->page1Background:Lorg/telegram/ui/Components/AnimatedColor;

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v3

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v6

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v8}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fgetmovingPage(Lorg/telegram/ui/ArticleViewer$WindowView;)Z

    move-result v8

    if-nez v8, :cond_e

    iget-object v8, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-static {v8}, Lorg/telegram/ui/ArticleViewer$WindowView;->-$$Nest$fgetopeningPage(Lorg/telegram/ui/ArticleViewer$WindowView;)Z

    move-result v8

    if-eqz v8, :cond_d

    goto :goto_7

    :cond_d
    move v8, v1

    goto :goto_8

    :cond_e
    :goto_7
    move v8, v3

    :goto_8
    invoke-virtual {v4, v6, v8}, Lorg/telegram/ui/Components/AnimatedColor;->set(IZ)I

    move-result v4

    invoke-virtual {v2, v3, v4}, Lorg/telegram/ui/web/WebActionBar;->setBackgroundColor(II)V

    .line 14949
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v4, v4, v1

    invoke-virtual {v4}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v4

    iget-object v6, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v6, v6, v3

    invoke-virtual {v6}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getActionBarColor()I

    move-result v6

    invoke-static {v4, v6, v5}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result v4

    invoke-virtual {v2, v4, v1}, Lorg/telegram/ui/web/WebActionBar;->setColors(IZ)V

    .line 14951
    iget-object v2, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    cmpl-float v0, v0, v7

    iget-object v4, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    if-lez v0, :cond_f

    aget-object v0, v4, v1

    goto :goto_9

    :cond_f
    aget-object v0, v4, v3

    :goto_9
    iget v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->type:I

    invoke-virtual {v2, v0}, Lorg/telegram/ui/web/WebActionBar;->setMenuType(I)V

    .line 14953
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->sheet:Lorg/telegram/ui/ArticleViewer$Sheet;

    if-eqz v0, :cond_10

    .line 14954
    iget-object p0, v0, Lorg/telegram/ui/ArticleViewer$Sheet;->windowView:Lorg/telegram/ui/ArticleViewer$Sheet$WindowView;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void

    .line 14955
    :cond_10
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    if-eqz p0, :cond_11

    .line 14956
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_11
    :goto_a
    return-void
.end method

.method public updateThemeColors(F)V
    .locals 3

    .line 4112
    invoke-direct {p0}, Lorg/telegram/ui/ArticleViewer;->refreshThemeColors()V

    .line 4113
    invoke-static {p0}, Lorg/telegram/ui/ArticleViewer;->updatePaintColors(Lorg/telegram/ui/IArticleViewer;)V

    .line 4114
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    if-eqz v0, :cond_0

    .line 4115
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v1, 0x0

    aget-object v0, v0, v1

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/RecyclerListView;->invalidateViews()V

    .line 4116
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v2, 0x1

    aget-object v0, v0, v2

    iget-object v0, v0, Lorg/telegram/ui/ArticleViewer$PageLayout;->listView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/RecyclerListView;->invalidateViews()V

    .line 4117
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->windowView:Lorg/telegram/ui/ArticleViewer$WindowView;

    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    .line 4118
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->searchPanel:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    const/high16 v0, 0x3f800000    # 1.0f

    cmpl-float p1, p1, v0

    if-nez p1, :cond_0

    .line 4120
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p1, p1, v1

    iget-object p1, p1, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {p1}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    .line 4121
    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v2

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer$PageLayout;->adapter:Lorg/telegram/ui/ArticleViewer$WebpageAdapter;

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$WebpageAdapter;->notifyDataSetChanged()V

    :cond_0
    return-void
.end method

.method public updateTitle(Z)V
    .locals 4

    .line 14961
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    const/4 v2, 0x0

    aget-object v1, v1, v2

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getTitle()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v2, v1, p1}, Lorg/telegram/ui/web/WebActionBar;->setTitle(ILjava/lang/String;Z)V

    .line 14962
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getSubtitle()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v2, v1, v2}, Lorg/telegram/ui/web/WebActionBar;->setSubtitle(ILjava/lang/String;Z)V

    .line 14963
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v1

    const/4 v3, 0x1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v1

    invoke-virtual {v1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->isUrlDangerous()Z

    move-result v1

    if-eqz v1, :cond_0

    move v1, v3

    goto :goto_0

    :cond_0
    move v1, v2

    :goto_0
    invoke-virtual {v0, v2, v1, v2}, Lorg/telegram/ui/web/WebActionBar;->setIsDangerous(IZZ)V

    .line 14965
    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v1, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v1, v1, v3

    invoke-virtual {v1}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getTitle()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v3, v1, p1}, Lorg/telegram/ui/web/WebActionBar;->setTitle(ILjava/lang/String;Z)V

    .line 14966
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getSubtitle()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v3, v0, v2}, Lorg/telegram/ui/web/WebActionBar;->setSubtitle(ILjava/lang/String;Z)V

    .line 14967
    iget-object p1, p0, Lorg/telegram/ui/ArticleViewer;->actionBar:Lorg/telegram/ui/web/WebActionBar;

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->isWeb()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object v0, v0, v3

    invoke-virtual {v0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object p0, p0, Lorg/telegram/ui/ArticleViewer;->pages:[Lorg/telegram/ui/ArticleViewer$PageLayout;

    aget-object p0, p0, v3

    invoke-virtual {p0}, Lorg/telegram/ui/ArticleViewer$PageLayout;->getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->isUrlDangerous()Z

    move-result p0

    if-eqz p0, :cond_1

    move p0, v3

    goto :goto_1

    :cond_1
    move p0, v2

    :goto_1
    invoke-virtual {p1, v3, p0, v2}, Lorg/telegram/ui/web/WebActionBar;->setIsDangerous(IZZ)V

    return-void
.end method
