.class public abstract Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;
.super Lorg/telegram/ui/ActionBar/BottomSheet;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;,
        Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;,
        Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$PaddingView;
    }
.end annotation


# instance fields
.field protected actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

.field protected actionBarIgnoreTouchEvents:Z

.field protected actionBarSlideProgress:Lorg/telegram/ui/Components/AnimatedFloat;

.field private actionBarType:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

.field protected additionalTitleX:I

.field private baseFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

.field protected centerTitle:Z

.field protected clipToActionBar:Z

.field protected contentHeight:I

.field editTextEmoji:Lorg/telegram/ui/Components/EditTextEmoji;

.field protected handleOffset:Z

.field private handleRect:Landroid/graphics/RectF;

.field public final hasFixedSize:Z

.field protected headerHeight:I

.field protected headerMoveTop:I

.field protected headerPaddingBottom:I

.field protected headerPaddingTop:I

.field private final headerShadowDrawable:Landroid/graphics/drawable/Drawable;

.field protected headerTotalHeight:I

.field protected ignoreTouchActionBar:Z

.field private lastTop:F

.field protected layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

.field public nestedSizeNotifierLayout:Lorg/telegram/ui/Components/NestedSizeNotifierLayout;

.field protected recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

.field private restore:Z

.field public reverseLayout:Z

.field private savedScrollOffset:I

.field private savedScrollPosition:I

.field private shadowAlpha:F

.field private showHandle:Z

.field protected showShadow:Z

.field public final stackFromEnd:Z

.field protected takeTranslationIntoAccount:Z

.field public topPadding:F

.field wasDrawn:Z


# direct methods
.method public static bridge synthetic -$$Nest$mupdateStatusBar(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->updateStatusBar()V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V
    .locals 7
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "AppCompatCustomView"
        }
    .end annotation

    .line 201
    iget-boolean v0, p3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;->needFocus:Z

    iget-object v1, p3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;->edgeToEdge:Lorg/telegram/ui/ActionBar/BottomSheet$EdgeToEdge;

    iget-object v2, p3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {p0, p1, v0, v1, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;-><init>(Landroid/content/Context;ZLorg/telegram/ui/ActionBar/BottomSheet$EdgeToEdge;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    const v0, 0x3ecccccd    # 0.4f

    .line 54
    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->topPadding:F

    const/4 v0, 0x1

    .line 55
    iput-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showShadow:Z

    const/high16 v1, 0x3f800000    # 1.0f

    .line 56
    iput v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    const/4 v1, 0x0

    .line 58
    iput-boolean v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showHandle:Z

    .line 60
    new-instance v2, Landroid/graphics/RectF;

    invoke-direct {v2}, Landroid/graphics/RectF;-><init>()V

    iput-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->handleRect:Landroid/graphics/RectF;

    .line 62
    sget-object v2, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->FADING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    iput-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarType:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    .line 63
    iput v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerTotalHeight:I

    .line 64
    iput v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerHeight:I

    .line 65
    iput v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingTop:I

    .line 66
    iput v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingBottom:I

    .line 67
    iput v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerMoveTop:I

    .line 68
    iput-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->ignoreTouchActionBar:Z

    .line 69
    iput-boolean v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarIgnoreTouchEvents:Z

    .line 76
    iput-boolean v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->takeTranslationIntoAccount:Z

    const/4 v2, -0x1

    .line 929
    iput v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->savedScrollPosition:I

    .line 202
    iget-boolean v3, p3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;->hasFixedSize:Z

    .line 203
    iget-boolean v4, p3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;->useNested:Z

    .line 204
    iget-boolean v5, p3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;->stackFromEnd:Z

    .line 205
    iget-object p3, p3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;->actionBarType:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    .line 207
    iput-object p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->baseFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    .line 208
    iput-boolean v3, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->hasFixedSize:Z

    .line 209
    iput-boolean v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->stackFromEnd:Z

    .line 210
    sget p2, Lorg/telegram/messenger/R$drawable;->header_shadow:I

    invoke-static {p1, p2}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object p2

    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v4, :cond_0

    .line 213
    new-instance p2, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$1;

    invoke-direct {p2, p0, p1, v5, v3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$1;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;Landroid/content/Context;ZZ)V

    iput-object p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->nestedSizeNotifierLayout:Lorg/telegram/ui/Components/NestedSizeNotifierLayout;

    goto :goto_0

    .line 253
    :cond_0
    new-instance p2, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$2;

    invoke-direct {p2, p0, p1, v5, v3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$2;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;Landroid/content/Context;ZZ)V

    .line 426
    :goto_0
    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->createRecyclerView(Landroid/content/Context;)Lorg/telegram/ui/Components/RecyclerListView;

    move-result-object v4

    iput-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    .line 427
    new-instance v4, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$3;

    invoke-direct {v4, p0, p1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$3;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;Landroid/content/Context;)V

    iput-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    if-eqz v5, :cond_1

    .line 449
    invoke-virtual {v4, v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->setStackFromEnd(Z)V

    .line 451
    :cond_1
    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v4, v5}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    .line 452
    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->nestedSizeNotifierLayout:Lorg/telegram/ui/Components/NestedSizeNotifierLayout;

    if-eqz v4, :cond_2

    .line 453
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->getContainer()Lorg/telegram/ui/ActionBar/BottomSheet$ContainerView;

    move-result-object v5

    invoke-virtual {v4, v5}, Lorg/telegram/ui/Components/NestedSizeNotifierLayout;->setBottomSheetContainerView(Lorg/telegram/ui/ActionBar/BottomSheet$ContainerView;)V

    .line 454
    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->nestedSizeNotifierLayout:Lorg/telegram/ui/Components/NestedSizeNotifierLayout;

    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v4, v5}, Lorg/telegram/ui/Components/NestedSizeNotifierLayout;->setTargetListView(Landroid/view/View;)V

    :cond_2
    if-eqz v3, :cond_3

    .line 458
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->setHasFixedSize(Z)V

    .line 459
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->createAdapter(Lorg/telegram/ui/Components/RecyclerListView;)Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;

    move-result-object v0

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/RecyclerListView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    .line 460
    invoke-virtual {p0, p2}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCustomView(Landroid/view/View;)V

    .line 461
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    const/high16 v0, -0x40000000    # -2.0f

    invoke-static {v2, v0}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v0

    invoke-virtual {p2, p1, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_1

    .line 463
    :cond_3
    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->resetAdapter(Landroid/content/Context;)V

    .line 464
    iput-object p2, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    .line 465
    new-instance v2, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$4;

    invoke-direct {v2, p0, p1, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$4;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;Landroid/content/Context;Lorg/telegram/ui/Components/SizeNotifierFrameLayout;)V

    iput-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    .line 488
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_dialogBackground:I

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result p1

    invoke-virtual {v2, p1}, Lorg/telegram/ui/ActionBar/ActionBar;->setBackgroundColor(I)V

    .line 489
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v2

    invoke-virtual {p1, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitleColor(I)V

    .line 490
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarActionModeDefaultSelector:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v2

    invoke-virtual {p1, v2, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsBackgroundColor(IZ)V

    .line 491
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v2, Lorg/telegram/messenger/R$drawable;->ic_ab_back:I

    invoke-virtual {p1, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setBackButtonImage(I)V

    .line 492
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarActionModeDefaultIcon:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v2

    invoke-virtual {p1, v2, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsColor(IZ)V

    .line 494
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p1, v0}, Lorg/telegram/ui/ActionBar/ActionBar;->setCastShadows(Z)V

    .line 495
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->getTitle()Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {p1, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 496
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p1, v0}, Lorg/telegram/ui/ActionBar/ActionBar;->setForceDisableCenterTitle(Z)V

    .line 497
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    new-instance v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$5;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$5;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)V

    invoke-virtual {p1, v0}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    .line 506
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p2, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 507
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/high16 v5, 0x40c00000    # 6.0f

    const/4 v6, 0x0

    const/4 v0, -0x1

    const/high16 v1, -0x40000000    # -2.0f

    const/4 v2, 0x0

    const/high16 v3, 0x40c00000    # 6.0f

    const/4 v4, 0x0

    invoke-static/range {v0 .. v6}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v0

    invoke-virtual {p2, p1, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 509
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    new-instance v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$6;

    invoke-direct {v0, p0, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$6;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;Lorg/telegram/ui/Components/SizeNotifierFrameLayout;)V

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->addOnScrollListener(Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V

    .line 517
    :goto_1
    sget-object p1, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->SLIDING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    if-ne p3, p1, :cond_4

    .line 518
    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->setSlidingActionBar()V

    .line 520
    :cond_4
    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->onViewCreated(Landroid/widget/FrameLayout;)V

    .line 521
    invoke-direct {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->updateStatusBar()V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;ZZZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 1

    .line 127
    new-instance v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    invoke-direct {v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;-><init>()V

    .line 128
    invoke-virtual {v0, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->needFocus(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 129
    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->hasFixedSize(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 130
    invoke-virtual {p3, p5}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->useNested(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 131
    invoke-virtual {p3, p6}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->resourcesProvider(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 132
    invoke-virtual {p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->build()Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;

    move-result-object p3

    .line 127
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;ZZZLorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "AppCompatCustomView"
        }
    .end annotation

    .line 145
    new-instance v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    invoke-direct {v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;-><init>()V

    .line 146
    invoke-virtual {v0, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->needFocus(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 147
    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->hasFixedSize(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 148
    invoke-virtual {p3, p5}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->useNested(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 149
    invoke-virtual {p3, p6}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->actionBarType(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 150
    invoke-virtual {p3, p7}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->resourcesProvider(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 151
    invoke-virtual {p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->build()Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;

    move-result-object p3

    .line 145
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;ZZZZLorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "AppCompatCustomView"
        }
    .end annotation

    .line 165
    new-instance v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    invoke-direct {v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;-><init>()V

    .line 166
    invoke-virtual {v0, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->needFocus(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 167
    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->hasFixedSize(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 168
    invoke-virtual {p3, p5}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->useNested(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 169
    invoke-virtual {p3, p6}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->stackFromEnd(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 170
    invoke-virtual {p3, p7}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->actionBarType(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 171
    invoke-virtual {p3, p8}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->resourcesProvider(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 172
    invoke-virtual {p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->build()Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;

    move-result-object p3

    .line 165
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;ZZZZZLorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "AppCompatCustomView"
        }
    .end annotation

    .line 188
    new-instance v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    invoke-direct {v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;-><init>()V

    .line 189
    invoke-virtual {v0, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->needFocus(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    if-eqz p4, :cond_0

    .line 190
    sget-object p4, Lorg/telegram/ui/ActionBar/BottomSheet$EdgeToEdge;->V1:Lorg/telegram/ui/ActionBar/BottomSheet$EdgeToEdge;

    goto :goto_0

    :cond_0
    sget-object p4, Lorg/telegram/ui/ActionBar/BottomSheet$EdgeToEdge;->NONE:Lorg/telegram/ui/ActionBar/BottomSheet$EdgeToEdge;

    :goto_0
    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->edgeToEdge(Lorg/telegram/ui/ActionBar/BottomSheet$EdgeToEdge;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 191
    invoke-virtual {p3, p5}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->hasFixedSize(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 192
    invoke-virtual {p3, p6}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->useNested(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 193
    invoke-virtual {p3, p7}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->stackFromEnd(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 194
    invoke-virtual {p3, p8}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->actionBarType(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 195
    invoke-virtual {p3, p9}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->resourcesProvider(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p3

    .line 196
    invoke-virtual {p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->build()Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;

    move-result-object p3

    .line 188
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/ActionBar/BaseFragment;ZZ)V
    .locals 2

    .line 83
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    invoke-direct {v1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;-><init>()V

    .line 84
    invoke-virtual {v1, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->needFocus(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 85
    invoke-virtual {p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->hasFixedSize(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 86
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object p3

    invoke-virtual {p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->resourcesProvider(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 87
    invoke-virtual {p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->build()Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;

    move-result-object p2

    .line 83
    invoke-direct {p0, v0, p1, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/ActionBar/BaseFragment;ZZLorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;)V
    .locals 2

    .line 111
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    invoke-direct {v1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;-><init>()V

    .line 112
    invoke-virtual {v1, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->needFocus(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 113
    invoke-virtual {p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->hasFixedSize(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 114
    invoke-virtual {p2, p4}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->actionBarType(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 115
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object p3

    invoke-virtual {p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->resourcesProvider(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 116
    invoke-virtual {p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->build()Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;

    move-result-object p2

    .line 111
    invoke-direct {p0, v0, p1, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/ActionBar/BaseFragment;ZZZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 2

    .line 97
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    invoke-direct {v1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;-><init>()V

    .line 98
    invoke-virtual {v1, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->needFocus(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 99
    invoke-virtual {p2, p3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->hasFixedSize(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 100
    invoke-virtual {p2, p4}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->useNested(Z)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 101
    invoke-virtual {p2, p5}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->resourcesProvider(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;

    move-result-object p2

    .line 102
    invoke-virtual {p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params$Builder;->build()Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;

    move-result-object p2

    .line 97
    invoke-direct {p0, v0, p1, p2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$Params;)V

    return-void
.end method

.method public static synthetic access$000(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)Landroid/graphics/drawable/Drawable;
    .locals 0

    .line 35
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->shadowDrawable:Landroid/graphics/drawable/Drawable;

    return-object p0
.end method

.method public static synthetic access$100(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)Z
    .locals 0

    .line 35
    iget-boolean p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->keyboardVisible:Z

    return p0
.end method

.method public static synthetic access$200(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)Landroid/graphics/drawable/Drawable;
    .locals 0

    .line 35
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->shadowDrawable:Landroid/graphics/drawable/Drawable;

    return-object p0
.end method

.method public static synthetic access$300(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)Z
    .locals 0

    .line 35
    iget-boolean p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->keyboardVisible:Z

    return p0
.end method

.method public static synthetic access$400(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)I
    .locals 0

    .line 35
    iget p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    return p0
.end method

.method public static synthetic access$500(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;)Landroid/view/ViewGroup;
    .locals 0

    .line 35
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    return-object p0
.end method

.method private checkBackDrawableInsets()V
    .locals 5

    .line 809
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backDrawable:Lorg/telegram/ui/ActionBar/BottomSheet$SheetBackDrawable;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->shadowDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shouldDrawBackground()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->hasFixedSize:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 813
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->shadowDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    .line 814
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->container:Lorg/telegram/ui/ActionBar/BottomSheet$ContainerView;

    invoke-virtual {v2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    .line 817
    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backDrawable:Lorg/telegram/ui/ActionBar/BottomSheet$SheetBackDrawable;

    const/4 v4, 0x0

    if-lt v1, v2, :cond_1

    .line 815
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    iget v0, v0, Landroid/graphics/Rect;->top:I

    sub-int/2addr v1, v0

    const/high16 v0, 0x41f00000    # 30.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    sub-int/2addr v1, v0

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    invoke-virtual {p0}, Landroid/view/View;->getTranslationY()F

    move-result p0

    float-to-int p0, p0

    sub-int/2addr v1, p0

    invoke-virtual {v3, v4, v4, v4, v1}, Lorg/telegram/ui/ActionBar/BottomSheet$SheetBackDrawable;->setBackgroundInsets(IIII)V

    return-void

    .line 817
    :cond_1
    invoke-virtual {v3, v4, v4, v4, v4}, Lorg/telegram/ui/ActionBar/BottomSheet$SheetBackDrawable;->setBackgroundInsets(IIII)V

    :cond_2
    :goto_0
    return-void
.end method

.method private isLightStatusBar()Z
    .locals 4

    .line 861
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_dialogBackground:I

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    invoke-static {p0}, Landroidx/core/graphics/ColorUtils;->calculateLuminance(I)D

    move-result-wide v0

    const-wide v2, 0x3fe6666660000000L    # 0.699999988079071

    cmpl-double p0, v0, v2

    if-lez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private updateStatusBar()V
    .locals 1

    .line 890
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->attachedFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    if-eqz v0, :cond_0

    .line 891
    sget-object p0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    const/4 v0, 0x1

    invoke-virtual {p0, v0, v0, v0}, Lorg/telegram/ui/LaunchActivity;->checkSystemBarColors(ZZZ)V

    return-void

    .line 892
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 893
    invoke-direct {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->isLightStatusBar()Z

    move-result v0

    invoke-static {p0, v0}, Lorg/telegram/messenger/AndroidUtilities;->setLightStatusBar(Landroid/app/Dialog;Z)V

    return-void

    .line 894
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->baseFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    if-eqz v0, :cond_2

    .line 895
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->isLightStatusBar()Z

    move-result v0

    invoke-static {p0, v0}, Lorg/telegram/messenger/AndroidUtilities;->setLightStatusBar(Landroid/app/Dialog;Z)V

    :cond_2
    return-void
.end method


# virtual methods
.method public applyScrolledPosition()V
    .locals 1

    const/4 v0, 0x0

    .line 954
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->applyScrolledPosition(Z)V

    return-void
.end method

.method public applyScrolledPosition(Z)V
    .locals 2

    .line 958
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutManager()Landroidx/recyclerview/widget/RecyclerView$LayoutManager;

    move-result-object p1

    if-eqz p1, :cond_1

    iget p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->savedScrollPosition:I

    if-ltz p1, :cond_1

    .line 959
    iget p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->savedScrollOffset:I

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v0

    sub-int/2addr p1, v0

    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v0}, Landroid/view/View;->getPaddingTop()I

    move-result v0

    sub-int/2addr p1, v0

    .line 965
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutManager()Landroidx/recyclerview/widget/RecyclerView$LayoutManager;

    move-result-object v0

    instance-of v0, v0, Landroidx/recyclerview/widget/LinearLayoutManager;

    if-eqz v0, :cond_0

    .line 966
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutManager()Landroidx/recyclerview/widget/RecyclerView$LayoutManager;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/LinearLayoutManager;

    iget v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->savedScrollPosition:I

    invoke-virtual {v0, v1, p1}, Landroidx/recyclerview/widget/LinearLayoutManager;->scrollToPositionWithOffset(II)V

    :cond_0
    const/4 p1, -0x1

    .line 968
    iput p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->savedScrollPosition:I

    :cond_1
    return-void
.end method

.method public canDismissWithSwipe()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public canHighlightChildAt(Landroid/view/View;FF)Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public abstract createAdapter(Lorg/telegram/ui/Components/RecyclerListView;)Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;
.end method

.method public createRecyclerView(Landroid/content/Context;)Lorg/telegram/ui/Components/RecyclerListView;
    .locals 2

    .line 541
    new-instance v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$7;

    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v0, p0, p1, v1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$7;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-object v0
.end method

.method public getActionBarProgressHeight()I
    .locals 0

    const/high16 p0, 0x42600000    # 56.0f

    .line 700
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getBaseFragment()Lorg/telegram/ui/ActionBar/BaseFragment;
    .locals 0

    .line 886
    iget-object p0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->baseFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    return-object p0
.end method

.method public abstract getTitle()Ljava/lang/CharSequence;
.end method

.method public isAttachedLightStatusBar()Z
    .locals 1

    .line 852
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 853
    invoke-direct {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->isLightStatusBar()Z

    move-result p0

    return p0

    .line 854
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->baseFragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    if-eqz v0, :cond_1

    .line 855
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->isLightStatusBar()Z

    move-result p0

    return p0

    .line 857
    :cond_1
    invoke-direct {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->isLightStatusBar()Z

    move-result p0

    return p0
.end method

.method public needPaddingShadow()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public notifyDataSetChanged()V
    .locals 0

    .line 877
    iget-object p0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p0}, Landroidx/recyclerview/widget/RecyclerView;->getAdapter()Landroidx/recyclerview/widget/RecyclerView$Adapter;

    move-result-object p0

    invoke-virtual {p0}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->notifyDataSetChanged()V

    return-void
.end method

.method public onActionBarAlpha(F)V
    .locals 0

    return-void
.end method

.method public onContainerViewTranslation()V
    .locals 1

    .line 825
    iget v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->lastTop:F

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->onSheetTop(F)V

    .line 826
    invoke-direct {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->checkBackDrawableInsets()V

    return-void
.end method

.method public onPreDraw(Landroid/graphics/Canvas;IF)V
    .locals 0

    return-void
.end method

.method public onPreMeasure(II)V
    .locals 0

    return-void
.end method

.method public onSheetTop(F)V
    .locals 0

    return-void
.end method

.method public onViewCreated(Landroid/widget/FrameLayout;)V
    .locals 0

    return-void
.end method

.method public postDrawInternal(Landroid/graphics/Canvas;Landroid/view/View;)V
    .locals 7

    .line 666
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarType:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    sget-object v1, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->FADING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    const/high16 v2, 0x437f0000    # 255.0f

    if-ne v0, v1, :cond_3

    .line 667
    iget-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showShadow:Z

    const v1, 0x3dda740e

    const/high16 v3, 0x3f800000    # 1.0f

    const/4 v4, 0x0

    if-eqz v0, :cond_0

    iget v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    cmpl-float v6, v5, v3

    if-eqz v6, :cond_0

    add-float/2addr v5, v1

    .line 668
    iput v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    .line 669
    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    goto :goto_0

    :cond_0
    if-nez v0, :cond_1

    .line 670
    iget v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    cmpl-float v5, v0, v4

    if-eqz v5, :cond_1

    sub-float/2addr v0, v1

    .line 671
    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    .line 672
    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    .line 674
    :cond_1
    :goto_0
    iget v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    invoke-static {v0, v3, v4}, Lorg/telegram/messenger/Utilities;->clamp(FFF)F

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    .line 675
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v0}, Landroid/view/View;->getAlpha()F

    move-result v0

    cmpl-float v0, v0, v4

    if-eqz v0, :cond_2

    iget v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    cmpl-float v0, v0, v4

    if-eqz v0, :cond_2

    .line 676
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    iget v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    iget-object v3, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v3}, Landroid/view/View;->getBottom()I

    move-result v3

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v4

    iget v5, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    sub-int/2addr v4, v5

    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v5}, Landroid/view/View;->getBottom()I

    move-result v5

    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v6}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v6

    add-int/2addr v5, v6

    invoke-virtual {v0, v1, v3, v4, v5}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 677
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    iget-object v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v1}, Landroid/view/View;->getAlpha()F

    move-result v1

    mul-float/2addr v1, v2

    iget v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    mul-float/2addr v1, v2

    float-to-int v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 678
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 679
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getAlpha()I

    move-result v0

    const/16 v1, 0xff

    if-ge v0, v1, :cond_2

    .line 680
    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    :cond_2
    const/4 p2, 0x1

    .line 683
    iput-boolean p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->wasDrawn:Z

    goto :goto_1

    .line 684
    :cond_3
    sget-object v1, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->SLIDING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    if-ne v0, v1, :cond_4

    .line 685
    iget v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    mul-float/2addr v0, v2

    float-to-int v0, v0

    if-eqz v0, :cond_4

    iget-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showShadow:Z

    if-eqz v0, :cond_4

    .line 686
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    iget v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    iget-object v3, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v3}, Landroid/view/View;->getBottom()I

    move-result v3

    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v4}, Landroid/view/View;->getTranslationY()F

    move-result v4

    float-to-int v4, v4

    add-int/2addr v3, v4

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result p2

    iget v4, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    sub-int/2addr p2, v4

    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v4}, Landroid/view/View;->getBottom()I

    move-result v4

    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v5}, Landroid/view/View;->getTranslationY()F

    move-result v5

    float-to-int v5, v5

    add-int/2addr v4, v5

    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v5}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v5

    add-int/2addr v4, v5

    invoke-virtual {v0, v1, v3, p2, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 687
    iget-object p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    iget v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    mul-float/2addr v0, v2

    float-to-int v0, v0

    invoke-virtual {p2, v0}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 688
    iget-object p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerShadowDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 691
    :cond_4
    :goto_1
    iget-boolean p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->restore:Z

    if-eqz p2, :cond_5

    .line 692
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    const/4 p1, 0x0

    .line 693
    iput-boolean p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->restore:Z

    :cond_5
    return-void
.end method

.method public preDrawInternal(Landroid/graphics/Canvas;Landroid/view/View;)V
    .locals 12

    const/4 v0, 0x0

    .line 706
    iput-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->restore:Z

    .line 707
    iget-boolean v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->hasFixedSize:Z

    if-nez v1, :cond_12

    .line 709
    iget-boolean v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->reverseLayout:Z

    .line 720
    iget-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    const/high16 v3, 0x41800000    # 16.0f

    const/4 v4, 0x1

    if-eqz v1, :cond_3

    .line 710
    invoke-virtual {v2}, Landroid/view/View;->getHeight()I

    move-result v1

    move v2, v0

    .line 711
    :goto_0
    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v5}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v5

    if-ge v2, v5, :cond_2

    .line 712
    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v5, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    .line 713
    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v6, v5}, Landroidx/recyclerview/widget/RecyclerView;->getChildAdapterPosition(Landroid/view/View;)I

    move-result v6

    const/4 v7, -0x1

    if-eq v6, v7, :cond_1

    .line 714
    iget-object v7, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v7}, Landroidx/recyclerview/widget/RecyclerView;->getAdapter()Landroidx/recyclerview/widget/RecyclerView$Adapter;

    move-result-object v7

    invoke-virtual {v7}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->getItemCount()I

    move-result v7

    sub-int/2addr v7, v4

    if-eq v6, v7, :cond_1

    .line 715
    invoke-virtual {v5}, Landroid/view/View;->getTop()I

    move-result v6

    iget-boolean v7, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->takeTranslationIntoAccount:Z

    if-eqz v7, :cond_0

    invoke-virtual {v5}, Landroid/view/View;->getTranslationY()F

    move-result v5

    float-to-int v5, v5

    goto :goto_1

    :cond_0
    move v5, v0

    :goto_1
    add-int/2addr v6, v5

    invoke-static {v1, v6}, Ljava/lang/Math;->min(II)I

    move-result v1

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 718
    :cond_2
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    sub-int/2addr v1, v2

    goto :goto_2

    .line 720
    :cond_3
    invoke-virtual {v2, v0}, Landroidx/recyclerview/widget/RecyclerView;->findViewHolderForAdapterPosition(I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v1

    .line 721
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    neg-int v2, v2

    if-eqz v1, :cond_4

    .line 723
    iget-object v2, v1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getBottom()I

    move-result v2

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    sub-int/2addr v2, v5

    .line 724
    iget-boolean v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->takeTranslationIntoAccount:Z

    if-eqz v5, :cond_4

    .line 725
    iget-object v1, v1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getTranslationY()F

    move-result v1

    float-to-int v1, v1

    add-int/2addr v1, v2

    goto :goto_2

    :cond_4
    move v1, v2

    .line 729
    :goto_2
    iget v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerHeight:I

    iget v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingTop:I

    add-int/2addr v2, v5

    iget v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingBottom:I

    add-int/2addr v2, v5

    sub-int/2addr v1, v2

    .line 730
    iget v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerMoveTop:I

    add-int/2addr v1, v2

    .line 731
    iget-boolean v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showHandle:Z

    const/high16 v5, 0x41000000    # 8.0f

    if-eqz v2, :cond_6

    iget-boolean v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->handleOffset:Z

    if-eqz v2, :cond_6

    .line 732
    iget-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarType:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    sget-object v6, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->SLIDING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    if-ne v2, v6, :cond_5

    move v2, v5

    goto :goto_3

    :cond_5
    move v2, v3

    :goto_3
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    sub-int/2addr v1, v2

    :cond_6
    int-to-float v2, v1

    .line 734
    iput v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->lastTop:F

    .line 735
    invoke-virtual {p0, v2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->onSheetTop(F)V

    .line 739
    iget-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarType:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    sget-object v6, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->FADING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    const/high16 v7, 0x40000000    # 2.0f

    const/high16 v8, 0x3f800000    # 1.0f

    const/4 v9, 0x0

    if-ne v2, v6, :cond_9

    .line 740
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    add-int/2addr v2, v1

    int-to-float v2, v2

    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->getActionBarProgressHeight()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v2, v3

    sub-float v2, v8, v2

    cmpg-float v3, v2, v9

    if-gez v3, :cond_7

    move v2, v9

    .line 744
    :cond_7
    iget-object v3, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    cmpl-float v5, v2, v9

    if-eqz v5, :cond_8

    goto :goto_4

    :cond_8
    move v4, v0

    :goto_4
    iget-boolean v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->wasDrawn:Z

    invoke-static {v3, v4, v8, v5}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZ)V

    goto/16 :goto_7

    .line 745
    :cond_9
    sget-object v3, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->SLIDING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    if-ne v2, v3, :cond_f

    .line 746
    iget v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerMoveTop:I

    sub-int v2, v1, v2

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    add-int/2addr v2, v3

    iget v3, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingTop:I

    add-int/2addr v2, v3

    sget v3, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    sub-int/2addr v2, v3

    int-to-float v2, v2

    invoke-static {v2, v9}, Ljava/lang/Math;->max(FF)F

    move-result v2

    .line 747
    iget-object v3, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarSlideProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    cmpl-float v5, v2, v9

    if-nez v5, :cond_a

    move v5, v8

    goto :goto_5

    :cond_a
    move v5, v9

    :goto_5
    invoke-virtual {v3, v5}, Lorg/telegram/ui/Components/AnimatedFloat;->set(F)F

    move-result v3

    cmpl-float v5, v3, v9

    if-eqz v5, :cond_b

    cmpl-float v5, v3, v8

    if-eqz v5, :cond_b

    .line 750
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 751
    iget-object v5, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    int-to-float v5, v5

    iget-object v6, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    invoke-virtual {v6}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {p1, v9, v2, v5, v6}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    .line 752
    iput-boolean v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->restore:Z

    .line 755
    :cond_b
    iput v3, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shadowAlpha:F

    const/high16 v5, 0x3f000000    # 0.5f

    .line 756
    invoke-static {v8, v5, v3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v8

    .line 757
    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    iget-object v6, v6, Lorg/telegram/ui/ActionBar/ActionBar;->backButtonImageView:Landroid/widget/ImageView;

    invoke-virtual {v6, v3}, Landroid/view/View;->setAlpha(F)V

    .line 758
    invoke-virtual {p0, v3}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->onActionBarAlpha(F)V

    .line 759
    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    iget-object v6, v6, Lorg/telegram/ui/ActionBar/ActionBar;->backButtonImageView:Landroid/widget/ImageView;

    invoke-virtual {v6, v3}, Landroid/view/View;->setScaleX(F)V

    .line 760
    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    iget-object v6, v6, Lorg/telegram/ui/ActionBar/ActionBar;->backButtonImageView:Landroid/widget/ImageView;

    invoke-virtual {v6}, Landroid/view/View;->getMeasuredHeight()I

    move-result v10

    int-to-float v10, v10

    div-float/2addr v10, v7

    invoke-virtual {v6, v10}, Landroid/view/View;->setPivotY(F)V

    .line 761
    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    iget-object v6, v6, Lorg/telegram/ui/ActionBar/ActionBar;->backButtonImageView:Landroid/widget/ImageView;

    invoke-virtual {v6, v3}, Landroid/view/View;->setScaleY(F)V

    .line 762
    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v6}, Lorg/telegram/ui/ActionBar/ActionBar;->getTitleTextView()Lorg/telegram/ui/ActionBar/SimpleTextView;

    move-result-object v6

    const/high16 v10, 0x41a80000    # 21.0f

    .line 763
    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v10

    invoke-virtual {v6}, Landroid/view/View;->getLeft()I

    move-result v11

    sub-int/2addr v10, v11

    int-to-float v10, v10

    invoke-static {v10, v9, v3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v10

    iget v11, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->additionalTitleX:I

    int-to-float v11, v11

    add-float/2addr v10, v11

    invoke-virtual {v6, v10}, Landroid/view/View;->setTranslationX(F)V

    .line 764
    iget-boolean v10, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->centerTitle:Z

    if-eqz v10, :cond_c

    .line 765
    iget-object v10, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v10}, Landroid/view/View;->getMeasuredWidth()I

    move-result v10

    invoke-virtual {v6}, Lorg/telegram/ui/ActionBar/SimpleTextView;->getTextWidth()I

    move-result v11

    sub-int/2addr v10, v11

    int-to-float v10, v10

    div-float/2addr v10, v7

    invoke-virtual {v6}, Landroid/view/View;->getLeft()I

    move-result v11

    int-to-float v11, v11

    sub-float/2addr v10, v11

    invoke-virtual {v6, v10}, Landroid/view/View;->setTranslationX(F)V

    .line 767
    :cond_c
    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v6, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setTranslationY(F)V

    .line 768
    iget v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerTotalHeight:I

    iget v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerHeight:I

    sub-int/2addr v2, v6

    iget v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingTop:I

    sub-int/2addr v2, v6

    iget v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingBottom:I

    sub-int/2addr v2, v6

    const/high16 v6, 0x41500000    # 13.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    add-int/2addr v2, v6

    invoke-static {v0, v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(IIF)I

    move-result v2

    sub-int/2addr v1, v2

    .line 769
    iget-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v2}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    iget-object v6, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v6}, Landroid/view/View;->getHeight()I

    move-result v6

    invoke-static {v6, v0, v3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(IIF)I

    move-result v6

    iget-object v10, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v10}, Landroid/view/View;->getWidth()I

    move-result v10

    iget-object v11, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v11}, Landroid/view/View;->getHeight()I

    move-result v11

    invoke-virtual {v2, v0, v6, v10, v11}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    cmpl-float v2, v3, v5

    .line 777
    iget-boolean v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarIgnoreTouchEvents:Z

    if-lez v2, :cond_d

    if-eqz v5, :cond_e

    .line 773
    iput-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarIgnoreTouchEvents:Z

    .line 774
    iget-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v2, v4}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    goto :goto_6

    :cond_d
    if-nez v5, :cond_e

    .line 778
    iput-boolean v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarIgnoreTouchEvents:Z

    .line 779
    iget-object v2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/4 v4, 0x0

    invoke-virtual {v2, v4}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    :cond_e
    :goto_6
    move v2, v3

    goto :goto_7

    :cond_f
    move v2, v9

    .line 784
    :goto_7
    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->shouldDrawBackground()Z

    move-result v3

    if-eqz v3, :cond_11

    .line 785
    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->needPaddingShadow()Z

    move-result v3

    .line 788
    iget-object v4, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->shadowDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v3, :cond_10

    .line 786
    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    invoke-virtual {v4, v0, v1, v3, v5}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    goto :goto_8

    :cond_10
    const/high16 v0, 0x40c00000    # 6.0f

    .line 788
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    neg-int v3, v3

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    add-int/2addr v5, v0

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    invoke-virtual {v4, v3, v1, v5, v0}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 790
    :goto_8
    invoke-direct {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->checkBackDrawableInsets()V

    .line 791
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->shadowDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 793
    iget-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showHandle:Z

    if-eqz v0, :cond_11

    cmpl-float v0, v8, v9

    if-lez v0, :cond_11

    const/high16 v0, 0x42100000    # 36.0f

    .line 794
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    const/high16 v3, 0x41a00000    # 20.0f

    .line 795
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    add-int/2addr v3, v1

    .line 796
    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->handleRect:Landroid/graphics/RectF;

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    sub-int/2addr v5, v0

    int-to-float v5, v5

    div-float/2addr v5, v7

    int-to-float v6, v3

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result p2

    add-int/2addr p2, v0

    int-to-float p2, p2

    div-float/2addr p2, v7

    const/high16 v0, 0x40800000    # 4.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    add-int/2addr v3, v0

    int-to-float v0, v3

    invoke-virtual {v4, v5, v6, p2, v0}, Landroid/graphics/RectF;->set(FFFF)V

    .line 797
    sget p2, Lorg/telegram/ui/ActionBar/Theme;->key_sheet_scrollUp:I

    invoke-virtual {p0, p2}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result p2

    .line 798
    sget-object v0, Lorg/telegram/ui/ActionBar/Theme;->dialogs_onlineCirclePaint:Landroid/graphics/Paint;

    invoke-virtual {v0, p2}, Landroid/graphics/Paint;->setColor(I)V

    .line 799
    sget-object p2, Lorg/telegram/ui/ActionBar/Theme;->dialogs_onlineCirclePaint:Landroid/graphics/Paint;

    invoke-virtual {p2}, Landroid/graphics/Paint;->getAlpha()I

    move-result v0

    int-to-float v0, v0

    mul-float/2addr v0, v8

    float-to-int v0, v0

    invoke-virtual {p2, v0}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 800
    iget-object p2, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->handleRect:Landroid/graphics/RectF;

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    sget-object v4, Lorg/telegram/ui/ActionBar/Theme;->dialogs_onlineCirclePaint:Landroid/graphics/Paint;

    invoke-virtual {p1, p2, v0, v3, v4}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 804
    :cond_11
    invoke-virtual {p0, p1, v1, v2}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->onPreDraw(Landroid/graphics/Canvas;IF)V

    :cond_12
    return-void
.end method

.method public resetAdapter(Landroid/content/Context;)V
    .locals 2

    .line 590
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->createAdapter(Lorg/telegram/ui/Components/RecyclerListView;)Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;

    move-result-object v0

    .line 591
    new-instance v1, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$8;

    invoke-direct {v1, p0, v0, p1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$8;-><init>(Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;Landroid/content/Context;)V

    .line 662
    iget-object p0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/RecyclerListView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    return-void
.end method

.method public saveScrollPosition()V
    .locals 7

    .line 932
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    if-eqz v0, :cond_2

    iget-object v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    if-eqz v1, :cond_2

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    if-lez v0, :cond_2

    const/4 v0, 0x0

    const/4 v1, -0x1

    const v2, 0x7fffffff

    const/4 v3, 0x0

    .line 936
    :goto_0
    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v4}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v4

    if-ge v3, v4, :cond_1

    .line 937
    iget-object v4, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v4, v3}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 938
    iget-object v5, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v5, v4}, Landroidx/recyclerview/widget/RecyclerView;->getChildAdapterPosition(Landroid/view/View;)I

    move-result v5

    if-ltz v5, :cond_0

    .line 939
    invoke-virtual {v4}, Landroid/view/View;->getTop()I

    move-result v6

    if-ge v6, v2, :cond_0

    .line 942
    invoke-virtual {v4}, Landroid/view/View;->getTop()I

    move-result v0

    move v2, v0

    move-object v0, v4

    move v1, v5

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    if-eqz v0, :cond_2

    .line 946
    iput v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->savedScrollPosition:I

    .line 947
    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v0

    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->savedScrollOffset:I

    .line 948
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->smoothContainerViewLayout()V

    :cond_2
    return-void
.end method

.method public setEditTextEmoji(Lorg/telegram/ui/Components/EditTextEmoji;)V
    .locals 0

    .line 557
    iput-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->editTextEmoji:Lorg/telegram/ui/Components/EditTextEmoji;

    return-void
.end method

.method public setShowHandle(Z)V
    .locals 0

    .line 925
    iput-boolean p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showHandle:Z

    return-void
.end method

.method public setShowShadow(Z)V
    .locals 0

    .line 920
    iput-boolean p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showShadow:Z

    .line 921
    iget-object p0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->nestedSizeNotifierLayout:Lorg/telegram/ui/Components/NestedSizeNotifierLayout;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public setSlidingActionBar()V
    .locals 8

    .line 525
    iget-boolean v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->hasFixedSize:Z

    if-eqz v0, :cond_0

    return-void

    .line 528
    :cond_0
    sget-object v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->SLIDING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    iput-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarType:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    .line 529
    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerHeight:I

    .line 530
    sget v1, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    add-int/2addr v0, v1

    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerTotalHeight:I

    const/high16 v0, 0x41800000    # 16.0f

    .line 531
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingTop:I

    const/high16 v0, -0x3e600000    # -20.0f

    .line 532
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->headerPaddingBottom:I

    .line 533
    new-instance v1, Lorg/telegram/ui/Components/AnimatedFloat;

    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    const-wide/16 v5, 0x15e

    sget-object v7, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const-wide/16 v3, 0x0

    invoke-direct/range {v1 .. v7}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Landroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    iput-object v1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBarSlideProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 534
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    iget-object v0, v0, Lorg/telegram/ui/ActionBar/ActionBar;->backButtonImageView:Landroid/widget/ImageView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setPivotX(F)V

    .line 535
    iget-object p0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Landroidx/recyclerview/widget/RecyclerView;->setClipToPadding(Z)V

    return-void
.end method

.method public shouldDrawBackground()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public updateTitle()V
    .locals 1

    .line 900
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz v0, :cond_0

    .line 901
    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->getTitle()Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    :cond_0
    return-void
.end method

.method public updateTitleAnimated()V
    .locals 1

    const/4 v0, 0x1

    .line 906
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->updateTitleAnimated(Z)V

    return-void
.end method

.method public updateTitleAnimated(Z)V
    .locals 6

    .line 910
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz v0, :cond_1

    .line 911
    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    if-eqz p1, :cond_0

    .line 912
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/ActionBar;->getTitle()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    .line 915
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->getTitle()Ljava/lang/CharSequence;

    move-result-object v1

    const-wide/16 v3, 0x15e

    sget-object v5, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const/4 v2, 0x0

    invoke-virtual/range {v0 .. v5}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitleAnimated(Ljava/lang/CharSequence;ZJLandroid/view/animation/Interpolator;)V

    :cond_1
    :goto_0
    return-void
.end method
