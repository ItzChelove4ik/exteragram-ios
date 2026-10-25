.class public Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;
.super Landroid/widget/LinearLayout;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/ActionBar/Theme$Colorable;


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "ViewConstructor"
    }
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$Factory;
    }
.end annotation


# instance fields
.field private final checkBox:Lorg/telegram/ui/Components/CheckBox2;

.field private icon:Lcom/exteragram/messenger/appicons/AppIcon;

.field private final previewContainer:Landroid/widget/FrameLayout;

.field private final previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

.field private final resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private selection:F

.field private selectionAnimator:Landroid/animation/ValueAnimator;

.field private final selectionPaint:Landroid/graphics/Paint;

.field private final titleView:Landroid/widget/TextView;


# direct methods
.method public static synthetic $r8$lambda$AMSOy8IGUwrkkFcf7Pf71b4lJ1E(Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->lambda$setSelected$2(Landroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static synthetic $r8$lambda$gq6lU44ZBGP-3hgfP-Qptu0AVw4(Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;F)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->lambda$new$1(F)V

    return-void
.end method

.method public static synthetic $r8$lambda$ifd4l_PSgiwEjkHXYS77Zlt-VrQ(Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;I)I
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->lambda$new$0(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;I)I

    move-result p0

    return p0
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 11

    .line 64
    invoke-direct {p0, p1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 53
    new-instance v0, Landroid/graphics/Paint;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionPaint:Landroid/graphics/Paint;

    .line 65
    iput-object p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v0, 0x0

    .line 67
    invoke-virtual {p0, v0}, Landroid/view/View;->setWillNotDraw(Z)V

    .line 68
    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 69
    invoke-virtual {p0, v1}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 70
    invoke-virtual {p0, v1}, Landroid/widget/LinearLayout;->setGravity(I)V

    const/high16 v2, 0x40800000    # 4.0f

    .line 71
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    const/high16 v4, 0x41200000    # 10.0f

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    const/high16 v5, 0x41400000    # 12.0f

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    invoke-virtual {p0, v3, v4, v2, v5}, Landroid/view/View;->setPadding(IIII)V

    .line 73
    new-instance v2, Landroid/widget/FrameLayout;

    invoke-direct {v2, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->previewContainer:Landroid/widget/FrameLayout;

    .line 74
    invoke-virtual {v2, v0}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 75
    new-instance v3, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-direct {v3, p1, p2}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    const/4 v4, -0x1

    const/high16 v5, -0x40800000    # -1.0f

    .line 76
    invoke-static {v4, v5}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v2, v3, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 78
    new-instance v3, Lorg/telegram/ui/Components/CheckBox2;

    new-instance v4, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$$ExternalSyntheticLambda0;

    invoke-direct {v4, p0, p2}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    const/16 v5, 0x15

    invoke-direct {v3, p1, v5, v4}, Lorg/telegram/ui/Components/CheckBox2;-><init>(Landroid/content/Context;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->checkBox:Lorg/telegram/ui/Components/CheckBox2;

    .line 81
    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    sget v6, Lorg/telegram/ui/ActionBar/Theme;->key_checkboxCheck:I

    invoke-virtual {v3, v4, v5, v6}, Lorg/telegram/ui/Components/CheckBox2;->setColor(III)V

    .line 82
    invoke-virtual {v3, v0}, Lorg/telegram/ui/Components/CheckBox2;->setDrawUnchecked(Z)V

    const/4 v0, 0x4

    .line 83
    invoke-virtual {v3, v0}, Lorg/telegram/ui/Components/CheckBox2;->setDrawBackgroundAsArc(I)V

    .line 84
    new-instance v0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;)V

    invoke-virtual {v3, v0}, Lorg/telegram/ui/Components/CheckBox2;->setProgressDelegate(Lorg/telegram/ui/Components/CheckBoxBase$ProgressDelegate;)V

    const/high16 v9, 0x3f800000    # 1.0f

    const/high16 v10, 0x3f800000    # 1.0f

    const/16 v4, 0x18

    const/high16 v5, 0x41c00000    # 24.0f

    const/16 v6, 0x55

    const/4 v7, 0x0

    const/4 v8, 0x0

    .line 85
    invoke-static/range {v4 .. v10}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v0

    invoke-virtual {v2, v3, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const/16 v0, 0x44

    .line 87
    invoke-static {v0, v0, v1}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(III)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v0

    invoke-virtual {p0, v2, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 89
    new-instance v0, Landroid/widget/TextView;

    invoke-direct {v0, p1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->titleView:Landroid/widget/TextView;

    const/4 p1, 0x2

    .line 90
    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setMaxLines(I)V

    const/16 p1, 0x11

    .line 91
    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setGravity(I)V

    .line 92
    sget-object p1, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setEllipsize(Landroid/text/TextUtils$TruncateAt;)V

    .line 93
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    const/high16 p1, 0x41500000    # 13.0f

    .line 94
    invoke-virtual {v0, v1, p1}, Landroid/widget/TextView;->setTextSize(IF)V

    const/4 p1, 0x0

    const v1, 0x3f733333    # 0.95f

    .line 95
    invoke-virtual {v0, p1, v1}, Landroid/widget/TextView;->setLineSpacing(FF)V

    .line 96
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-static {p1, p2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setTextColor(I)V

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v1, -0x1

    const/4 v2, -0x2

    const/4 v3, 0x1

    const/4 v4, 0x0

    const/16 v5, 0x8

    .line 97
    invoke-static/range {v1 .. v7}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object p1

    invoke-virtual {p0, v0, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const p1, 0x3d4ccccd    # 0.05f

    const p2, 0x3f99999a    # 1.2f

    .line 99
    invoke-static {p0, p1, p2}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;FF)V

    return-void
.end method

.method private getSelectionColor()I
    .locals 3

    .line 120
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 121
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v1, v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v1

    const v2, 0x3d8f5c29    # 0.07f

    .line 122
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selection:F

    mul-float/2addr p0, v2

    invoke-static {v0, v1, p0}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result p0

    return p0
.end method

.method private synthetic lambda$new$0(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;I)I
    .locals 1

    .line 78
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    if-ne p2, v0, :cond_0

    .line 79
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->getSelectionColor()I

    move-result p0

    return p0

    .line 80
    :cond_0
    invoke-static {p2, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method private synthetic lambda$new$1(F)V
    .locals 1

    .line 84
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    const v0, 0x3da3d70a    # 0.08f

    mul-float/2addr p1, v0

    const/high16 v0, 0x3f800000    # 1.0f

    sub-float/2addr v0, p1

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setPreviewScale(F)V

    return-void
.end method

.method private synthetic lambda$setSelected$2(Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 165
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selection:F

    .line 166
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method private setSelected(ZZ)V
    .locals 2

    .line 147
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->checkBox:Lorg/telegram/ui/Components/CheckBox2;

    invoke-virtual {v0, p1, p2}, Lorg/telegram/ui/Components/CheckBox2;->setChecked(ZZ)V

    if-eqz p1, :cond_0

    const/high16 p1, 0x3f800000    # 1.0f

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    .line 150
    :goto_0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_1

    .line 151
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    const/4 v0, 0x0

    .line 152
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionAnimator:Landroid/animation/ValueAnimator;

    :cond_1
    if-nez p2, :cond_2

    .line 155
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selection:F

    .line 156
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void

    .line 159
    :cond_2
    iget p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selection:F

    cmpl-float v0, p2, p1

    if-nez v0, :cond_3

    return-void

    :cond_3
    const/4 v0, 0x2

    .line 162
    new-array v0, v0, [F

    const/4 v1, 0x0

    aput p2, v0, v1

    const/4 p2, 0x1

    aput p1, v0, p2

    invoke-static {v0}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object p1

    const-wide/16 v0, 0xdc

    invoke-virtual {p1, v0, v1}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionAnimator:Landroid/animation/ValueAnimator;

    .line 163
    sget-object p2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {p1, p2}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 164
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionAnimator:Landroid/animation/ValueAnimator;

    new-instance p2, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$$ExternalSyntheticLambda2;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;)V

    invoke-virtual {p1, p2}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 168
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {p0}, Landroid/animation/ValueAnimator;->start()V

    return-void
.end method


# virtual methods
.method public bridge synthetic getColorKeys()[I
    .locals 0

    .line 0
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/Theme$Colorable;->getColorKeys()[I

    move-result-object p0

    return-object p0
.end method

.method public getIcon()Lcom/exteragram/messenger/appicons/AppIcon;
    .locals 0

    .line 143
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    return-object p0
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 6

    .line 127
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selection:F

    const/4 v1, 0x0

    cmpg-float v0, v0, v1

    if-gtz v0, :cond_0

    return-void

    .line 130
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionPaint:Landroid/graphics/Paint;

    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->getSelectionColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 131
    sget-object v0, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    const/high16 v1, 0x40000000    # 2.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v4

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    sub-int/2addr v4, v5

    int-to-float v4, v4

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v5

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    sub-int/2addr v5, v1

    int-to-float v1, v5

    invoke-virtual {v0, v2, v3, v4, v1}, Landroid/graphics/RectF;->set(FFFF)V

    const/high16 v1, 0x41800000    # 16.0f

    .line 132
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->selectionPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v1, p0}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    return-void
.end method

.method public onMeasure(II)V
    .locals 3

    .line 110
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v1

    sub-int/2addr v0, v1

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v1

    sub-int/2addr v0, v1

    const/high16 v1, 0x42200000    # 40.0f

    .line 111
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    const/high16 v2, 0x42880000    # 68.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-static {v2, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v1, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 112
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->previewContainer:Landroid/widget/FrameLayout;

    invoke-virtual {v1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Landroid/widget/LinearLayout$LayoutParams;

    .line 113
    iget v2, v1, Landroid/widget/LinearLayout$LayoutParams;->width:I

    if-eq v2, v0, :cond_0

    .line 114
    iput v0, v1, Landroid/widget/LinearLayout$LayoutParams;->height:I

    iput v0, v1, Landroid/widget/LinearLayout$LayoutParams;->width:I

    .line 116
    :cond_0
    invoke-super {p0, p1, p2}, Landroid/widget/LinearLayout;->onMeasure(II)V

    return-void
.end method

.method public set(Lcom/exteragram/messenger/appicons/AppIcon;ZZ)V
    .locals 1

    .line 136
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 137
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setIcon(Lcom/exteragram/messenger/appicons/AppIcon;)V

    .line 138
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->titleView:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/exteragram/messenger/appicons/AppIcon;->getTitle()Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 139
    invoke-direct {p0, p2, p3}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->setSelected(ZZ)V

    return-void
.end method

.method public updateColors()V
    .locals 3

    .line 104
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->titleView:Landroid/widget/TextView;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v1, v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 105
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method
