.class Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;
.super Landroid/widget/FrameLayout;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/ActionBar/Theme$Colorable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "ContentView"
.end annotation


# instance fields
.field private glowColor:I

.field private glowCx:F

.field private glowCy:F

.field private glowHeight:I

.field private final glowPaint:Landroid/graphics/Paint;

.field private glowWidth:I

.field private heroLinkPressed:Z

.field private final panelPaint:Landroid/graphics/Paint;

.field private final panelPath:Landroid/graphics/Path;

.field private final panelRadii:[F

.field final synthetic this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

.field private topFadeColor:I

.field private topFadeDrawable:Landroid/graphics/drawable/Drawable;


# direct methods
.method public static synthetic $r8$lambda$VxbAo0vmS5dtJ4IaA9UxvSTsBHw(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V
    .locals 0

    .line 0
    invoke-static {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mupdateListPadding(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Landroid/content/Context;)V
    .locals 0

    .line 425
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    .line 426
    invoke-direct {p0, p2}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 411
    new-instance p1, Landroid/graphics/Paint;

    const/4 p2, 0x1

    invoke-direct {p1, p2}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowPaint:Landroid/graphics/Paint;

    .line 412
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1, p2}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPaint:Landroid/graphics/Paint;

    .line 413
    new-instance p1, Landroid/graphics/Path;

    invoke-direct {p1}, Landroid/graphics/Path;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPath:Landroid/graphics/Path;

    const/16 p1, 0x8

    .line 414
    new-array p1, p1, [F

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelRadii:[F

    const/4 p1, 0x0

    .line 427
    invoke-virtual {p0, p1}, Landroid/view/View;->setWillNotDraw(Z)V

    return-void
.end method


# virtual methods
.method public dispatchTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 3

    .line 514
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 515
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    const/4 v1, 0x1

    if-nez v0, :cond_0

    .line 517
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->dispatchLinkTouch(Landroid/view/MotionEvent;)Z

    move-result v0

    iput-boolean v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->heroLinkPressed:Z

    if-eqz v0, :cond_3

    return v1

    .line 521
    :cond_0
    iget-boolean v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->heroLinkPressed:Z

    if-eqz v2, :cond_3

    if-eq v0, v1, :cond_1

    const/4 v2, 0x3

    if-ne v0, v2, :cond_2

    :cond_1
    const/4 v0, 0x0

    .line 523
    iput-boolean v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->heroLinkPressed:Z

    .line 525
    :cond_2
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->dispatchLinkTouch(Landroid/view/MotionEvent;)Z

    return v1

    .line 529
    :cond_3
    invoke-super {p0, p1}, Landroid/view/View;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    move-result p0

    return p0
.end method

.method public drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z
    .locals 6

    .line 561
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetlistView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    if-eq p2, v0, :cond_0

    .line 562
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z

    move-result p0

    return p0

    :cond_0
    const/high16 v0, 0x41a00000    # 20.0f

    .line 565
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    .line 566
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelRadii:[F

    const/4 v2, 0x0

    invoke-static {v1, v2}, Ljava/util/Arrays;->fill([FF)V

    .line 567
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v1

    .line 571
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelRadii:[F

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v1, :cond_1

    const/4 v1, 0x7

    .line 568
    aput v0, v3, v1

    const/4 v1, 0x6

    aput v0, v3, v1

    aput v0, v3, v4

    aput v0, v3, v5

    .line 569
    sget-object v0, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v0, v1, v2, v3, v4}, Landroid/graphics/RectF;->set(FFFF)V

    goto :goto_0

    :cond_1
    const/4 v1, 0x3

    .line 571
    aput v0, v3, v1

    const/4 v1, 0x2

    aput v0, v3, v1

    aput v0, v3, v4

    aput v0, v3, v5

    .line 572
    sget-object v0, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetlistView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getY()F

    move-result v1

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v3

    int-to-float v3, v3

    add-float/2addr v1, v3

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mgetCollapseRange(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v3

    int-to-float v3, v3

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetcollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)F

    move-result v4

    mul-float/2addr v3, v4

    sub-float/2addr v1, v3

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v0, v2, v1, v3, v4}, Landroid/graphics/RectF;->set(FFFF)V

    .line 574
    :goto_0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPath:Landroid/graphics/Path;

    invoke-virtual {v0}, Landroid/graphics/Path;->rewind()V

    .line 575
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPath:Landroid/graphics/Path;

    sget-object v1, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelRadii:[F

    sget-object v4, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v0, v1, v3, v4}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;[FLandroid/graphics/Path$Direction;)V

    .line 577
    iget v0, v1, Landroid/graphics/RectF;->top:F

    .line 578
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPaint:Landroid/graphics/Paint;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {v3, v4}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v3

    invoke-virtual {v1, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 579
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPath:Landroid/graphics/Path;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v1, v3}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 581
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 582
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->panelPath:Landroid/graphics/Path;

    invoke-virtual {p1, v1}, Landroid/graphics/Canvas;->clipPath(Landroid/graphics/Path;)Z

    .line 583
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z

    move-result p2

    .line 584
    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettopFade(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)F

    move-result p3

    cmpl-float p3, p3, v2

    if-lez p3, :cond_4

    .line 585
    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-virtual {p3, v4}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result p3

    .line 586
    iget-object p4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->topFadeDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz p4, :cond_2

    iget p4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->topFadeColor:I

    if-eq p4, p3, :cond_3

    .line 587
    :cond_2
    iput p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->topFadeColor:I

    .line 588
    invoke-static {p3}, Lcom/exteragram/messenger/utils/ui/UIUtil;->createTopFade(I)Landroid/graphics/drawable/GradientDrawable;

    move-result-object p3

    iput-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->topFadeDrawable:Landroid/graphics/drawable/Drawable;

    .line 590
    :cond_3
    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->topFadeDrawable:Landroid/graphics/drawable/Drawable;

    float-to-int p4, v0

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    const/high16 v1, 0x41c00000    # 24.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    add-int/2addr v1, p4

    invoke-virtual {p3, v5, p4, v0, v1}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 591
    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->topFadeDrawable:Landroid/graphics/drawable/Drawable;

    iget-object p4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettopFade(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)F

    move-result p4

    const/high16 v0, 0x437f0000    # 255.0f

    mul-float/2addr p4, v0

    float-to-int p4, p4

    invoke-virtual {p3, p4}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 592
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->topFadeDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 594
    :cond_4
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    return p2
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 10

    .line 534
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mgetBaseColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/graphics/Canvas;->drawColor(I)V

    .line 535
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v0

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetcollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)F

    move-result v0

    const/high16 v1, 0x3f800000    # 1.0f

    cmpl-float v0, v0, v1

    if-ltz v0, :cond_0

    goto/16 :goto_3

    .line 538
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v0

    const/high16 v2, 0x40000000    # 2.0f

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v0

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v3

    add-int/2addr v0, v3

    :goto_0
    int-to-float v0, v0

    div-float/2addr v0, v2

    move v4, v0

    goto :goto_1

    :cond_1
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    goto :goto_0

    .line 539
    :goto_1
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v0

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getPaddingTop()I

    move-result v3

    add-int/2addr v0, v3

    int-to-float v0, v0

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v3

    invoke-virtual {v3}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->getPreviewSize()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v3, v2

    add-float v5, v0, v3

    .line 540
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowColor:I

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v2

    if-ne v0, v2, :cond_2

    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowWidth:I

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v2

    if-ne v0, v2, :cond_2

    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowHeight:I

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v2

    if-ne v0, v2, :cond_2

    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowCx:F

    cmpl-float v0, v0, v4

    if-nez v0, :cond_2

    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowCy:F

    cmpl-float v0, v0, v5

    if-eqz v0, :cond_5

    .line 542
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v0

    goto :goto_2

    :cond_3
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    :goto_2
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v2

    invoke-static {v0, v2}, Ljava/lang/Math;->max(II)I

    move-result v0

    int-to-float v0, v0

    const v2, 0x3f666666    # 0.9f

    mul-float v6, v0, v2

    const/4 v0, 0x0

    cmpg-float v0, v6, v0

    if-gtz v0, :cond_4

    goto :goto_3

    .line 546
    :cond_4
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowColor:I

    .line 547
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowWidth:I

    .line 548
    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowHeight:I

    .line 549
    iput v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowCx:F

    .line 550
    iput v5, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowCy:F

    .line 551
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowPaint:Landroid/graphics/Paint;

    new-instance v3, Landroid/graphics/RadialGradient;

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v2

    const/16 v7, 0xc8

    .line 552
    invoke-static {v2, v7}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v2

    iget-object v7, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v7}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v7

    const/4 v8, 0x0

    invoke-static {v7, v8}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v7

    filled-new-array {v2, v7}, [I

    move-result-object v7

    const/4 v2, 0x2

    new-array v8, v2, [F

    fill-array-data v8, :array_0

    sget-object v9, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    invoke-direct/range {v3 .. v9}, Landroid/graphics/RadialGradient;-><init>(FFF[I[FLandroid/graphics/Shader$TileMode;)V

    .line 551
    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 555
    :cond_5
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowPaint:Landroid/graphics/Paint;

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetcollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)F

    move-result v2

    sub-float/2addr v1, v2

    const/high16 v2, 0x437f0000    # 255.0f

    mul-float/2addr v1, v2

    float-to-int v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 556
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    int-to-float v4, v0

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v0

    int-to-float v5, v0

    iget-object v6, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->glowPaint:Landroid/graphics/Paint;

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    :cond_6
    :goto_3
    return-void

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method

.method public onLayout(ZIIII)V
    .locals 2

    .line 491
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 492
    invoke-super/range {p0 .. p5}, Landroid/widget/FrameLayout;->onLayout(ZIIII)V

    .line 493
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p0, v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mupdateCollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    return-void

    :cond_0
    sub-int/2addr p4, p2

    sub-int/2addr p5, p3

    .line 499
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->access$500(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object p1

    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p2

    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p3

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->access$300(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    add-int/2addr p3, v0

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->access$400(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    invoke-virtual {p1, p2, v1, p3, v0}, Landroid/view/View;->layout(IIII)V

    .line 500
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetlistView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p2

    invoke-virtual {p1, p2, v1, p4, p5}, Landroid/view/View;->layout(IIII)V

    .line 502
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/View;->getMeasuredHeight()I

    move-result p1

    .line 503
    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;

    move-result-object p2

    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p3

    sub-int p1, p5, p1

    iget-object p4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p4

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    add-int/2addr p4, v0

    invoke-virtual {p2, p3, p1, p4, p5}, Landroid/view/View;->layout(IIII)V

    .line 505
    sget p2, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result p3

    add-int/2addr p2, p3

    sub-int/2addr p1, p2

    .line 506
    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p3

    sub-int/2addr p1, p3

    div-int/lit8 p1, p1, 0x2

    invoke-static {v1, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    add-int/2addr p2, p1

    .line 507
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object p1

    iget-object p3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p3

    iget-object p4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p4

    iget-object p5, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p5}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object p5

    invoke-virtual {p5}, Landroid/view/View;->getMeasuredWidth()I

    move-result p5

    add-int/2addr p4, p5

    iget-object p5, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p5}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p5

    add-int/2addr p5, p2

    invoke-virtual {p1, p3, p2, p4, p5}, Landroid/view/View;->layout(IIII)V

    .line 509
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p0, v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mupdateCollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    return-void
.end method

.method public onMeasure(II)V
    .locals 8

    .line 432
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v0

    .line 433
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v1

    .line 435
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v2

    .line 436
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0, v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$smisTwoPane(II)Z

    move-result v4

    invoke-static {v3, v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fputtwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Z)V

    .line 437
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$smpaneWidthFor(I)I

    move-result v4

    goto :goto_0

    :cond_0
    move v4, v0

    :goto_0
    invoke-static {v3, v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fputpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    .line 439
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v3

    .line 442
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    const/4 v5, 0x0

    if-eqz v3, :cond_1

    .line 440
    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v3

    invoke-virtual {v3, v5, v5, v5, v5}, Landroid/view/View;->setPadding(IIII)V

    goto :goto_1

    .line 442
    :cond_1
    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    const/high16 v6, 0x41e00000    # 28.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    add-int/2addr v4, v6

    const/high16 v6, 0x42200000    # 40.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    invoke-virtual {v3, v5, v4, v5, v6}, Landroid/view/View;->setPadding(IIII)V

    .line 444
    :goto_1
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v3

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4, v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mgetPreviewSizeDp(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)I

    move-result v4

    invoke-virtual {v3, v4}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->setPreviewSizeDp(I)V

    .line 445
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;

    move-result-object v3

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v4

    if-eqz v4, :cond_2

    move v4, v5

    goto :goto_2

    :cond_2
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v4

    :goto_2
    iget-object v6, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v6}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v6

    if-eqz v6, :cond_3

    move v6, v5

    goto :goto_3

    :cond_3
    iget-object v6, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v6}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetrightInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v6

    :goto_3
    iget-object v7, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v7}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbottomInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v7

    invoke-virtual {v3, v4, v5, v6, v7}, Landroid/view/View;->setPadding(IIII)V

    .line 446
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonFaded(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v3

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v4

    xor-int/lit8 v4, v4, 0x1

    if-eq v3, v4, :cond_5

    .line 447
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v4

    xor-int/lit8 v4, v4, 0x1

    invoke-static {v3, v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fputbuttonFaded(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;Z)V

    .line 448
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;

    move-result-object v3

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonFaded(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v4

    if-eqz v4, :cond_4

    .line 449
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {v4, v5}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v4

    invoke-static {v4}, Lcom/exteragram/messenger/utils/ui/UIUtil;->createBottomFade(I)Landroid/graphics/drawable/GradientDrawable;

    move-result-object v4

    goto :goto_4

    :cond_4
    const/4 v4, 0x0

    .line 448
    :goto_4
    invoke-virtual {v3, v4}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 452
    :cond_5
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3, v0, v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mgetGridWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;II)I

    move-result v4

    invoke-static {v3, v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mgetSpanCount(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)I

    move-result v3

    .line 453
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetlistView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v4

    invoke-virtual {v4}, Lorg/telegram/ui/Components/UniversalRecyclerView;->getSpanCount()I

    move-result v4

    if-eq v4, v3, :cond_6

    .line 454
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetlistView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v4

    invoke-virtual {v4, v3}, Lorg/telegram/ui/Components/UniversalRecyclerView;->setSpanCount(I)V

    .line 457
    :cond_6
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v3

    if-nez v3, :cond_7

    .line 458
    invoke-super {p0, p1, p2}, Landroid/widget/FrameLayout;->onMeasure(II)V

    goto :goto_5

    .line 460
    :cond_7
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p1

    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetleftInset(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p2

    sub-int/2addr p1, p2

    const/high16 p2, 0x40000000    # 2.0f

    invoke-static {p1, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    const/high16 v3, -0x80000000

    .line 461
    invoke-static {v1, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v3

    .line 462
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->access$000(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v4

    invoke-virtual {v4, p1, v3}, Landroid/view/View;->measure(II)V

    .line 463
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;

    move-result-object v4

    invoke-virtual {v4, p1, v3}, Landroid/view/View;->measure(II)V

    .line 464
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v4}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object v4

    invoke-virtual {v4, p1, v3}, Landroid/view/View;->measure(II)V

    .line 465
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetlistView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetpaneWidth(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result v3

    sub-int v3, v0, v3

    .line 466
    invoke-static {v3, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v3

    .line 467
    invoke-static {v1, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    .line 465
    invoke-virtual {p1, v3, p2}, Landroid/view/View;->measure(II)V

    .line 468
    invoke-virtual {p0, v0, v1}, Landroid/view/View;->setMeasuredDimension(II)V

    .line 471
    :goto_5
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroView(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/View;->getMeasuredHeight()I

    move-result p1

    if-lez p1, :cond_9

    .line 472
    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)I

    move-result p2

    if-ne p1, p2, :cond_8

    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgettwoPane(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result p2

    if-eq v2, p2, :cond_9

    .line 473
    :cond_8
    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p2, p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fputheroHeight(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    .line 474
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    new-instance p1, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_9
    return-void
.end method

.method public updateColors()V
    .locals 4

    .line 480
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetpreviewIcon(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v1

    invoke-static {v1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAccentTint(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result v1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fputbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    .line 481
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->access$100(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsColor(IZ)V

    .line 482
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->access$200(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Lorg/telegram/ui/ActionBar/ActionBar;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_listSelector:I

    invoke-virtual {v1, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v1

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsBackgroundColor(IZ)V

    .line 483
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonFaded(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 484
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fgetbuttonContainer(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)Landroid/widget/FrameLayout;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v1

    invoke-static {v1}, Lcom/exteragram/messenger/utils/ui/UIUtil;->createBottomFade(I)Landroid/graphics/drawable/GradientDrawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 486
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$ContentView;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$minvalidateHeader(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    return-void
.end method
