.class public Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;
.super Lorg/telegram/ui/ActionBar/BaseFragment;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;
    }
.end annotation


# instance fields
.field private appliedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

.field private backgroundColor:I

.field private bottomInset:I

.field private button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

.field private buttonContainer:Landroid/widget/FrameLayout;

.field private buttonFaded:Z

.field private collapse:F

.field private colorAnimator:Landroid/animation/ValueAnimator;

.field private contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

.field private cutout:Landroidx/core/graphics/Insets;

.field private glassSource:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

.field private heroHeight:I

.field private heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

.field private leftInset:I

.field private listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

.field private paneWidth:I

.field private previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

.field private rightInset:I

.field private scrolled:I

.field private topFade:F

.field private twoPane:Z


# direct methods
.method public static synthetic $r8$lambda$4dj22e82XBXlCeF6CGfYnsdFUWI(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->onItemClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V

    return-void
.end method

.method public static synthetic $r8$lambda$8m0fA0i-NYVZPB5jN0Cz6AN8qY0(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Landroid/view/View;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->lambda$createView$0(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$MFj9XffOvXPdaF8RIpzAFpVAHLY(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Landroid/view/View;IIIIIIII)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p9}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->lambda$createView$1(Landroid/view/View;IIIIIIII)V

    return-void
.end method

.method public static synthetic $r8$lambda$QrANRZKcT7nJzSaGvtR1GaLUQ_E(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->applyAccent()V

    return-void
.end method

.method public static synthetic $r8$lambda$esv9szpVo7ie9KUo8mcOorlUazk(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V

    return-void
.end method

.method public static synthetic $r8$lambda$mVqiqKsVPuA0GZXntromFEpd73o(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;IILandroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->lambda$animateBackgroundColor$2(IILandroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetbottomInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->bottomInset:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonContainer:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetbuttonFaded(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonFaded:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)F
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->collapse:F

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroHeight:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->leftInset:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetlistView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/Components/UniversalRecyclerView;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->paneWidth:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewIcon(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/AppIcon;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetrightInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->rightInset:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgettopFade(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)F
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->topFade:F

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->twoPane:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fputbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V
    .locals 0

    .line 0
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputbuttonFaded(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonFaded:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V
    .locals 0

    .line 0
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroHeight:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V
    .locals 0

    .line 0
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->paneWidth:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputtwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->twoPane:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$mgetBaseColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getBaseColor()I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mgetCollapseRange(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getCollapseRange()I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mgetGridWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;II)I
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getGridWidth(II)I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mgetPreviewSizeDp(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)I
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getPreviewSizeDp(I)I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mgetSpanCount(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)I
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getSpanCount(I)I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$minvalidateHeader(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->invalidateHeader()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mupdateCollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateCollapse(I)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mupdateListPadding(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateListPadding()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mupdateStatusBar(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateStatusBar()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$smisTwoPane(II)Z
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->isTwoPane(II)Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$smpaneWidthFor(I)I
    .locals 0

    .line 0
    invoke-static {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->paneWidthFor(I)I

    move-result p0

    return p0
.end method

.method public constructor <init>()V
    .locals 1

    .line 70
    invoke-direct {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;-><init>()V

    .line 101
    sget-object v0, Landroidx/core/graphics/Insets;->NONE:Landroidx/core/graphics/Insets;

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->cutout:Landroidx/core/graphics/Insets;

    const/4 v0, 0x1

    .line 102
    iput-boolean v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonFaded:Z

    return-void
.end method

.method public static synthetic access$000(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;
    .locals 0

    .line 70
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    return-object p0
.end method

.method public static synthetic access$100(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;
    .locals 0

    .line 70
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    return-object p0
.end method

.method public static synthetic access$200(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;
    .locals 0

    .line 70
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    return-object p0
.end method

.method public static synthetic access$300(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;
    .locals 0

    .line 70
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    return-object p0
.end method

.method public static synthetic access$400(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;
    .locals 0

    .line 70
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    return-object p0
.end method

.method public static synthetic access$500(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;
    .locals 0

    .line 70
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    return-object p0
.end method

.method private animateBackgroundColor(I)V
    .locals 4

    .line 268
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    if-ne v0, p1, :cond_0

    return-void

    .line 271
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_1

    .line 272
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    const/4 v0, 0x0

    .line 273
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    .line 275
    :cond_1
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    if-nez v0, :cond_2

    .line 276
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    .line 277
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->invalidateHeader()V

    .line 278
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateStatusBar()V

    return-void

    :cond_2
    const/4 v1, 0x2

    .line 282
    new-array v1, v1, [F

    fill-array-data v1, :array_0

    invoke-static {v1}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v1

    const-wide/16 v2, 0x118

    invoke-virtual {v1, v2, v3}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    move-result-object v1

    iput-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    .line 283
    sget-object v2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v1, v2}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 284
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    new-instance v2, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda5;

    invoke-direct {v2, p0, v0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;II)V

    invoke-virtual {v1, v2}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 288
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    new-instance v1, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 295
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {p0}, Landroid/animation/ValueAnimator;->start()V

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method

.method private applyAccent()V
    .locals 1

    .line 237
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-nez v0, :cond_0

    goto :goto_0

    .line 240
    :cond_0
    invoke-static {v0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAccentTint(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result v0

    if-eqz v0, :cond_1

    .line 242
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->animateBackgroundColor(I)V

    :cond_1
    :goto_0
    return-void
.end method

.method private fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation

    .line 214
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAvailableIcons()Ljava/util/List;

    move-result-object p2

    .line 215
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-eqz v0, :cond_0

    invoke-interface {p2, v0}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 216
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getSelectedIcon()Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->appliedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 217
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 220
    :cond_1
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/appicons/AppIcon;

    .line 221
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v0, v1, :cond_2

    const/4 v1, 0x1

    goto :goto_1

    :cond_2
    const/4 v1, 0x0

    :goto_1
    invoke-static {v0, v1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$Factory;->asAppIcon(Lcom/exteragram/messenger/appicons/AppIcon;Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_3
    return-void
.end method

.method private getBaseColor()I
    .locals 2

    .line 318
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v0

    .line 319
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    if-nez p0, :cond_0

    return v0

    :cond_0
    const/high16 v1, 0x3e800000    # 0.25f

    invoke-static {v0, p0, v1}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result p0

    return p0
.end method

.method private getCollapseRange()I
    .locals 2

    .line 377
    iget-boolean v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->twoPane:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    .line 380
    :cond_0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroHeight:I

    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result v0

    sub-int/2addr p0, v0

    sget v0, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    sub-int/2addr p0, v0

    invoke-static {v1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method private getGridWidth(II)I
    .locals 0

    .line 208
    invoke-static {p1, p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->isTwoPane(II)Z

    move-result p2

    if-eqz p2, :cond_0

    .line 209
    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->paneWidthFor(I)I

    move-result p2

    sub-int/2addr p1, p2

    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->rightInset:I

    :goto_0
    sub-int/2addr p1, p0

    return p1

    .line 210
    :cond_0
    iget p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->leftInset:I

    sub-int/2addr p1, p2

    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->rightInset:I

    goto :goto_0
.end method

.method private getPreviewSizeDp(I)I
    .locals 2

    .line 178
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    const/16 v1, 0x80

    if-nez v0, :cond_0

    return v1

    .line 181
    :cond_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->twoPane:Z

    if-eqz v0, :cond_1

    .line 183
    sget v0, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    sub-int/2addr p1, v0

    .line 182
    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result v0

    sub-int/2addr p1, v0

    const/high16 v0, 0x42900000    # 72.0f

    .line 183
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    sub-int/2addr p1, v0

    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->bottomInset:I

    :goto_0
    sub-int/2addr p1, v0

    goto :goto_1

    :cond_1
    int-to-float p1, p1

    const v0, 0x3ee66666    # 0.45f

    mul-float/2addr p1, v0

    float-to-int p1, p1

    .line 184
    sget v0, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    sub-int/2addr p1, v0

    const/high16 v0, 0x42880000    # 68.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    goto :goto_0

    .line 185
    :goto_1
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->getTextBlockHeight()I

    move-result p0

    sub-int/2addr p1, p0

    int-to-float p0, p1

    .line 186
    sget p1, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr p0, p1

    float-to-int p0, p0

    .line 187
    invoke-static {v1, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    const/16 p1, 0x40

    .line 186
    invoke-static {p1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method private getSpanCount(I)I
    .locals 2

    const/high16 p0, 0x41000000    # 8.0f

    .line 199
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    mul-int/lit8 p0, p0, 0x2

    sub-int/2addr p1, p0

    int-to-float p0, p1

    const/high16 p1, 0x42c80000    # 100.0f

    .line 200
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    div-float p1, p0, p1

    invoke-static {p1}, Ljava/lang/Math;->round(F)I

    move-result p1

    const/16 v0, 0x8

    invoke-static {v0, p1}, Ljava/lang/Math;->min(II)I

    move-result p1

    const/4 v1, 0x3

    invoke-static {v1, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    if-ge p1, v0, :cond_0

    int-to-float v0, p1

    div-float/2addr p0, v0

    const/high16 v0, 0x42e00000    # 112.0f

    .line 201
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    cmpl-float p0, p0, v0

    if-lez p0, :cond_0

    add-int/lit8 p1, p1, 0x1

    :cond_0
    return p1
.end method

.method private getTopColor()I
    .locals 2

    .line 323
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    if-nez v0, :cond_0

    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getBaseColor()I

    move-result p0

    return p0

    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getBaseColor()I

    move-result v0

    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    const v1, 0x3f333333    # 0.7f

    invoke-static {v0, p0, v1}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result p0

    return p0
.end method

.method private invalidateHeader()V
    .locals 2

    .line 299
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    .line 300
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->glassSource:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    if-eqz v0, :cond_0

    .line 301
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getTopColor()I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;->setColor(I)V

    .line 302
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_0
    return-void
.end method

.method private static isTwoPane(II)Z
    .locals 0

    if-le p0, p1, :cond_0

    const/high16 p1, 0x440c0000    # 560.0f

    .line 191
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    if-lt p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic lambda$animateBackgroundColor$2(IILandroid/animation/ValueAnimator;)V
    .locals 0

    .line 285
    invoke-virtual {p3}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Float;

    invoke-virtual {p3}, Ljava/lang/Float;->floatValue()F

    move-result p3

    invoke-static {p1, p2, p3}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    .line 286
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->invalidateHeader()V

    return-void
.end method

.method private synthetic lambda$createView$0(Landroid/view/View;)V
    .locals 0

    .line 153
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->onButtonClick()V

    return-void
.end method

.method private synthetic lambda$createView$1(Landroid/view/View;IIIIIIII)V
    .locals 0

    .line 159
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateListPadding()V

    return-void
.end method

.method private onButtonClick()V
    .locals 4

    .line 247
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-nez v0, :cond_0

    return-void

    .line 250
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->appliedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v0, v1, :cond_1

    .line 251
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    return-void

    .line 254
    :cond_1
    invoke-static {v0}, Lcom/exteragram/messenger/appicons/AppIconController;->setIcon(Lcom/exteragram/messenger/appicons/AppIcon;)V

    .line 255
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->appliedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    const/4 v0, 0x1

    .line 256
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateButton(Z)V

    .line 257
    new-instance v0, Lcom/exteragram/messenger/appicons/ui/components/AppIconBulletinLayout;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->appliedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v3

    invoke-direct {v0, v1, v2, v3}, Lcom/exteragram/messenger/appicons/ui/components/AppIconBulletinLayout;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/appicons/AppIcon;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    const/16 v1, 0x5dc

    invoke-static {p0, v0, v1}, Lorg/telegram/ui/Components/Bulletin;->make(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/Bulletin$Layout;I)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private onItemClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 226
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p2, p1, Lcom/exteragram/messenger/appicons/AppIcon;

    if-eqz p2, :cond_1

    check-cast p1, Lcom/exteragram/messenger/appicons/AppIcon;

    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne p1, p2, :cond_0

    goto :goto_0

    .line 229
    :cond_0
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 230
    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    invoke-virtual {p2, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->set(Lcom/exteragram/messenger/appicons/AppIcon;)V

    .line 231
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->applyAccent()V

    const/4 p1, 0x1

    .line 232
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateButton(Z)V

    .line 233
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method private static paneWidthFor(I)I
    .locals 3

    const/high16 v0, 0x438c0000    # 280.0f

    .line 195
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    const/high16 v1, 0x43c80000    # 400.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float p0, p0

    const v2, 0x3ed70a3d    # 0.42f

    mul-float/2addr p0, v2

    float-to-int p0, p0

    invoke-static {v1, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    invoke-static {v0, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method private updateButton(Z)V
    .locals 2

    .line 261
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    if-nez v0, :cond_0

    return-void

    .line 264
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->appliedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v1, p0, :cond_1

    sget p0, Lorg/telegram/messenger/R$string;->AppIconsKeep:I

    goto :goto_0

    :cond_1
    sget p0, Lorg/telegram/messenger/R$string;->AppIconsApply:I

    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0, p1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setText(Ljava/lang/CharSequence;Z)V

    return-void
.end method

.method private updateCollapse(I)V
    .locals 4

    .line 384
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    if-eqz v0, :cond_5

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    if-nez v0, :cond_0

    goto/16 :goto_3

    .line 387
    :cond_0
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->scrolled:I

    add-int/2addr v0, p1

    const/4 p1, 0x0

    move v1, p1

    .line 388
    :goto_0
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v2

    if-ge v1, v2, :cond_2

    .line 389
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v2, v1}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 390
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v3, v2}, Landroidx/recyclerview/widget/RecyclerView;->getChildAdapterPosition(Landroid/view/View;)I

    move-result v3

    if-nez v3, :cond_1

    .line 391
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v0}, Landroid/view/View;->getPaddingTop()I

    move-result v0

    invoke-virtual {v2}, Landroid/view/View;->getTop()I

    move-result v1

    sub-int/2addr v0, v1

    goto :goto_1

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 395
    :cond_2
    :goto_1
    invoke-static {p1, v0}, Ljava/lang/Math;->max(II)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->scrolled:I

    .line 397
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getCollapseRange()I

    move-result p1

    const/high16 v0, 0x3f800000    # 1.0f

    if-gtz p1, :cond_3

    const/4 p1, 0x0

    goto :goto_2

    .line 398
    :cond_3
    iget v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->scrolled:I

    int-to-float v1, v1

    int-to-float p1, p1

    div-float/2addr v1, p1

    invoke-static {v0, v1}, Ljava/lang/Math;->min(FF)F

    move-result p1

    .line 399
    :goto_2
    iget v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->scrolled:I

    int-to-float v1, v1

    const/high16 v2, 0x41800000    # 16.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v1, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->min(FF)F

    move-result v0

    .line 400
    iget v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->collapse:F

    sub-float v1, p1, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    const v2, 0x3a03126f    # 5.0E-4f

    cmpg-float v1, v1, v2

    if-gez v1, :cond_4

    iget v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->topFade:F

    sub-float v1, v0, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    cmpg-float v1, v1, v2

    if-gez v1, :cond_4

    goto :goto_3

    .line 403
    :cond_4
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->collapse:F

    .line 404
    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->topFade:F

    .line 405
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->setCollapse(F)V

    .line 406
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_5
    :goto_3
    return-void
.end method

.method private updateListPadding()V
    .locals 6

    .line 354
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/high16 v0, 0x41000000    # 8.0f

    .line 357
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    iget-boolean v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->twoPane:Z

    const/4 v3, 0x0

    if-eqz v2, :cond_1

    move v2, v3

    goto :goto_0

    :cond_1
    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->leftInset:I

    :goto_0
    add-int/2addr v1, v2

    .line 358
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->rightInset:I

    add-int/2addr v0, v2

    .line 360
    iget-boolean v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->twoPane:Z

    const/high16 v4, 0x41200000    # 10.0f

    if-eqz v2, :cond_2

    .line 361
    sget v2, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    add-int/2addr v2, v5

    .line 362
    iget v5, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->bottomInset:I

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    add-int/2addr v5, v4

    goto :goto_3

    .line 364
    :cond_2
    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroHeight:I

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    add-int/2addr v2, v4

    .line 365
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonContainer:Landroid/widget/FrameLayout;

    if-eqz v4, :cond_4

    invoke-virtual {v4}, Landroid/view/View;->getHeight()I

    move-result v4

    if-gtz v4, :cond_3

    goto :goto_2

    .line 367
    :cond_3
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonContainer:Landroid/widget/FrameLayout;

    invoke-virtual {v4}, Landroid/view/View;->getHeight()I

    move-result v4

    :goto_1
    move v5, v4

    goto :goto_3

    .line 366
    :cond_4
    :goto_2
    iget v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->bottomInset:I

    const/high16 v5, 0x42900000    # 72.0f

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    add-int/2addr v4, v5

    goto :goto_1

    .line 369
    :goto_3
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v4}, Landroid/view/View;->getPaddingTop()I

    move-result v4

    if-ne v4, v2, :cond_5

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v4}, Landroid/view/View;->getPaddingBottom()I

    move-result v4

    if-ne v4, v5, :cond_5

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    .line 370
    invoke-virtual {v4}, Landroid/view/View;->getPaddingLeft()I

    move-result v4

    if-ne v4, v1, :cond_5

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v4}, Landroid/view/View;->getPaddingRight()I

    move-result v4

    if-eq v4, v0, :cond_6

    .line 371
    :cond_5
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v4, v1, v2, v0, v5}, Landroid/view/View;->setPadding(IIII)V

    .line 373
    :cond_6
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateCollapse(I)V

    return-void
.end method

.method private updateStatusBar()V
    .locals 1

    .line 307
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 308
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->isLightStatusBar()Z

    move-result p0

    invoke-static {v0, p0}, Lorg/telegram/messenger/AndroidUtilities;->setLightStatusBar(Landroid/view/Window;Z)V

    :cond_0
    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 14

    .line 106
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getSelectedIcon()Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->appliedIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 107
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 108
    invoke-static {v0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAccentTint(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->backgroundColor:I

    .line 110
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v1, Lorg/telegram/messenger/R$drawable;->ic_ab_back:I

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setBackButtonImage(I)V

    .line 111
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setAllowOverlayTitle(Z)V

    .line 112
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setCastShadows(Z)V

    .line 113
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setAddToContainer(Z)V

    .line 114
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsColor(IZ)V

    .line 115
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_listSelector:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsBackgroundColor(IZ)V

    .line 116
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    new-instance v2, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$1;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$1;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    .line 123
    new-instance v0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    .line 125
    new-instance v0, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    invoke-direct {v0}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->glassSource:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    .line 126
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getTopColor()I

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;->setColor(I)V

    .line 127
    new-instance v0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->glassSource:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    invoke-direct {v0, v2}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;-><init>(Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    .line 128
    new-instance v2, Lorg/telegram/ui/Components/chat/ViewPositionWatcher;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    invoke-direct {v2, v3}, Lorg/telegram/ui/Components/chat/ViewPositionWatcher;-><init>(Landroid/view/View;)V

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    invoke-virtual {v0, v2, v3}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->setSourceRootView(Lorg/telegram/ui/Components/chat/ViewPositionWatcher;Landroid/view/ViewGroup;)V

    .line 129
    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/high16 v3, 0x41400000    # 12.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/ActionBar;->setGlassPadding(I)V

    .line 130
    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v3

    invoke-static {v3}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->topPanelChatActivity(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object v3

    invoke-virtual {v2, v0, v3}, Lorg/telegram/ui/ActionBar/ActionBar;->setupGlass(Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;)V

    .line 131
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/4 v2, 0x1

    invoke-static {v0, v2}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->applyChatHeaderGlassStyle(Lorg/telegram/ui/ActionBar/ActionBar;Z)V

    .line 133
    new-instance v0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    .line 134
    new-instance v3, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda0;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    invoke-virtual {v0, v3}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->setOnPreviewReady(Ljava/lang/Runnable;)V

    .line 135
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->previewIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    invoke-virtual {v0, v3}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->set(Lcom/exteragram/messenger/appicons/AppIcon;)V

    .line 136
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->heroView:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    const/16 v4, 0x30

    const/4 v5, -0x1

    const/4 v6, -0x2

    invoke-static {v5, v6, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v0, v3, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 138
    new-instance v0, Lorg/telegram/ui/Components/UniversalRecyclerView;

    new-instance v3, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda1;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    new-instance v4, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda2;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    const/4 v7, 0x0

    invoke-direct {v0, p0, v3, v4, v7}, Lorg/telegram/ui/Components/UniversalRecyclerView;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/messenger/Utilities$Callback2;Lorg/telegram/messenger/Utilities$Callback5;Lorg/telegram/messenger/Utilities$Callback5Return;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    .line 139
    sget-object v3, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v4, v3, Landroid/graphics/Point;->x:I

    iget v3, v3, Landroid/graphics/Point;->y:I

    invoke-direct {p0, v4, v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getGridWidth(II)I

    move-result v3

    invoke-direct {p0, v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getSpanCount(I)I

    move-result v3

    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/UniversalRecyclerView;->setSpanCount(I)V

    .line 140
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    iget-object v0, v0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->setApplyBackground(Z)V

    .line 141
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/RecyclerListView;->setDrawSelection(Z)V

    .line 142
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setClipToPadding(Z)V

    .line 143
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    new-instance v3, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$2;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$2;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    invoke-virtual {v0, v3}, Landroidx/recyclerview/widget/RecyclerView;->addOnScrollListener(Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V

    .line 149
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    const/high16 v4, -0x40800000    # -1.0f

    invoke-static {v5, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v0, v3, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 151
    new-instance v0, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v3

    invoke-direct {v0, p1, v2, v3}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;-><init>(Landroid/content/Context;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    .line 152
    invoke-virtual {v0}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setRound()Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    .line 153
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    new-instance v2, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda3;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    invoke-virtual {v0, v2}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 154
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateButton(Z)V

    .line 156
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-direct {v0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonContainer:Landroid/widget/FrameLayout;

    .line 157
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result p1

    invoke-static {p1}, Lcom/exteragram/messenger/utils/ui/UIUtil;->createBottomFade(I)Landroid/graphics/drawable/GradientDrawable;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 158
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonContainer:Landroid/widget/FrameLayout;

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    const/high16 v12, 0x41800000    # 16.0f

    const/high16 v13, 0x41400000    # 12.0f

    const/4 v7, -0x1

    const/high16 v8, 0x42400000    # 48.0f

    const/16 v9, 0x50

    const/high16 v10, 0x41800000    # 16.0f

    const/high16 v11, 0x41400000    # 12.0f

    invoke-static/range {v7 .. v13}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    invoke-virtual {p1, v0, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 159
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonContainer:Landroid/widget/FrameLayout;

    new-instance v0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    invoke-virtual {p1, v0}, Landroid/view/View;->addOnLayoutChangeListener(Landroid/view/View$OnLayoutChangeListener;)V

    .line 160
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->buttonContainer:Landroid/widget/FrameLayout;

    const/16 v1, 0x50

    invoke-static {v5, v6, v1}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    invoke-virtual {p1, v0, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 162
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 164
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    iput-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    return-object p1
.end method

.method public getEdgeToEdgeSupportMode()Lorg/telegram/ui/ActionBar/EdgeToEdgeSupportMode;
    .locals 0

    .line 333
    sget-object p0, Lorg/telegram/ui/ActionBar/EdgeToEdgeSupportMode;->FULL:Lorg/telegram/ui/ActionBar/EdgeToEdgeSupportMode;

    return-object p0
.end method

.method public isLightStatusBar()Z
    .locals 0

    .line 314
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->getTopColor()I

    move-result p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->isDarkColor(I)Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    return p0
.end method

.method public isSupportEdgeToEdge()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 1

    .line 169
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    .line 170
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    const/4 v0, 0x0

    .line 171
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->colorAnimator:Landroid/animation/ValueAnimator;

    :cond_0
    const/high16 v0, 0x42880000    # 68.0f

    .line 173
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->trimAbove(I)V

    .line 174
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method

.method public onInsets(IIII)V
    .locals 0

    .line 344
    iput p4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->bottomInset:I

    .line 345
    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->cutout:Landroidx/core/graphics/Insets;

    iget p2, p2, Landroidx/core/graphics/Insets;->left:I

    invoke-static {p1, p2}, Ljava/lang/Math;->max(II)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->leftInset:I

    .line 346
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->cutout:Landroidx/core/graphics/Insets;

    iget p1, p1, Landroidx/core/graphics/Insets;->right:I

    invoke-static {p3, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->rightInset:I

    .line 347
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->contentView:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;

    if-eqz p1, :cond_0

    .line 348
    invoke-virtual {p1}, Landroid/view/View;->requestLayout()V

    .line 350
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->updateListPadding()V

    return-void
.end method

.method public onInsetsInternal(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;
    .locals 1

    .line 338
    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->displayCutout()I

    move-result v0

    invoke-virtual {p2, v0}, Landroidx/core/view/WindowInsetsCompat;->getInsets(I)Landroidx/core/graphics/Insets;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->cutout:Landroidx/core/graphics/Insets;

    .line 339
    invoke-super {p0, p1, p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->onInsetsInternal(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;

    move-result-object p0

    return-object p0
.end method
