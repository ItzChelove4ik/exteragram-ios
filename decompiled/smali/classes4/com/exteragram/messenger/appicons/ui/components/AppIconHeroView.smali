.class public Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;
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


# instance fields
.field private collapse:F

.field private final fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

.field private icon:Lcom/exteragram/messenger/appicons/AppIcon;

.field private linkColor:I

.field private linkColorAnimator:Landroid/animation/ValueAnimator;

.field private pressedLink:Landroid/text/style/ClickableSpan;

.field private previewReadyListener:Ljava/lang/Runnable;

.field private previewSizeDp:I

.field private final previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

.field private final resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private subtitleLinks:Ljava/lang/CharSequence;

.field private final subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

.field private final titleView:Lorg/telegram/ui/Components/AnimatedTextView;


# direct methods
.method public static synthetic $r8$lambda$9K0gJ7jOfMPSZITWc4TGIn0joxA(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->onPreviewReady()V

    return-void
.end method

.method public static synthetic $r8$lambda$tbNgnbraldDO241-lnwtdjJUujg(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;IILandroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->lambda$updateLinkColor$0(IILandroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetlinkColor(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColor:I

    return p0
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 4

    .line 82
    invoke-direct {p0, p1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/16 v0, 0x80

    .line 64
    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewSizeDp:I

    .line 83
    iput-object p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    .line 84
    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object p2

    iput-object p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v1, 0x1

    .line 86
    invoke-virtual {p0, v1}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 87
    invoke-virtual {p0, v1}, Landroid/widget/LinearLayout;->setGravity(I)V

    const/4 v2, 0x0

    .line 88
    invoke-virtual {p0, v2}, Landroid/view/ViewGroup;->setClipToPadding(Z)V

    .line 89
    invoke-virtual {p0, v2}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 91
    new-instance v3, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-direct {v3, p1, p2}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    .line 92
    invoke-virtual {v3, v1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setCrossfade(Z)V

    .line 93
    new-instance p1, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$$ExternalSyntheticLambda1;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;)V

    invoke-virtual {v3, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setOnPreviewReady(Ljava/lang/Runnable;)V

    .line 94
    invoke-static {v0, v0, v1}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(III)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object p1

    invoke-virtual {p0, v3, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 96
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object p1

    const/16 p2, 0x1c

    const/16 v0, 0x12

    const/16 v3, 0x16

    invoke-direct {p0, v3, p1, p2, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->addText(ILandroid/graphics/Typeface;II)Lorg/telegram/ui/Components/AnimatedTextView;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->titleView:Lorg/telegram/ui/Components/AnimatedTextView;

    .line 97
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object p1

    const/16 p2, 0x14

    const/4 v0, 0x6

    const/16 v3, 0xe

    invoke-direct {p0, v3, p1, p2, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->addText(ILandroid/graphics/Typeface;II)Lorg/telegram/ui/Components/AnimatedTextView;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    .line 98
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->updateColors()V

    .line 101
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getAvailableIcons()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    move p2, v2

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/appicons/AppIcon;

    .line 102
    invoke-static {v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleOf(Lcom/exteragram/messenger/appicons/AppIcon;)Ljava/lang/CharSequence;

    move-result-object v0

    if-eqz v0, :cond_0

    move v0, v1

    goto :goto_1

    :cond_0
    move v0, v2

    :goto_1
    or-int/2addr p2, v0

    goto :goto_0

    .line 104
    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    if-eqz p2, :cond_2

    goto :goto_2

    :cond_2
    const/16 v2, 0x8

    :goto_2
    invoke-virtual {p0, v2}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method

.method private addText(ILandroid/graphics/Typeface;II)Lorg/telegram/ui/Components/AnimatedTextView;
    .locals 8

    .line 148
    new-instance v0, Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    invoke-direct {v0, v1, v2, v2, v3}, Lorg/telegram/ui/Components/AnimatedTextView;-><init>(Landroid/content/Context;ZZZ)V

    const/16 v1, 0x11

    .line 149
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/AnimatedTextView;->setGravity(I)V

    .line 150
    invoke-virtual {v0, p2}, Lorg/telegram/ui/Components/AnimatedTextView;->setTypeface(Landroid/graphics/Typeface;)V

    int-to-float p1, p1

    .line 151
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextSize(F)V

    .line 152
    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/AnimatedTextView;->setIncludeFontPadding(Z)V

    .line 153
    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/AnimatedTextView;->setAllowCancel(Z)V

    const-wide/16 v4, 0x104

    .line 154
    sget-object v6, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const v1, 0x3eb33333    # 0.35f

    const-wide/16 v2, 0x0

    invoke-virtual/range {v0 .. v6}, Lorg/telegram/ui/Components/AnimatedTextView;->setAnimationProperties(FJJLandroid/animation/TimeInterpolator;)V

    const/16 v6, 0x18

    const/4 v7, 0x0

    const/4 v1, -0x1

    const/4 v3, 0x1

    const/16 v4, 0x18

    move v2, p3

    move v5, p4

    .line 155
    invoke-static/range {v1 .. v7}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object p1

    invoke-virtual {p0, v0, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    return-object v0
.end method

.method private applyCollapse()V
    .locals 7

    .line 263
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->collapse:F

    const v1, 0x3eb33333    # 0.35f

    div-float/2addr v0, v1

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-static {v1, v0}, Ljava/lang/Math;->min(FF)F

    move-result v0

    const/high16 v2, 0x41400000    # 12.0f

    .line 264
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    neg-int v2, v2

    int-to-float v2, v2

    mul-float/2addr v2, v0

    .line 265
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->titleView:Lorg/telegram/ui/Components/AnimatedTextView;

    sub-float v0, v1, v0

    invoke-virtual {v3, v0}, Landroid/view/View;->setAlpha(F)V

    .line 266
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->titleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v3, v2}, Landroid/view/View;->setTranslationY(F)V

    .line 267
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v3, v0}, Landroid/view/View;->setAlpha(F)V

    .line 268
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v0, v2}, Landroid/view/View;->setTranslationY(F)V

    .line 270
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    if-gtz v0, :cond_0

    return-void

    .line 274
    :cond_0
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v2}, Landroid/view/View;->getTop()I

    move-result v2

    int-to-float v2, v2

    int-to-float v0, v0

    const/high16 v3, 0x40000000    # 2.0f

    div-float v4, v0, v3

    add-float/2addr v2, v4

    .line 275
    sget v5, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    int-to-float v5, v5

    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v6, v3

    add-float/2addr v5, v6

    invoke-virtual {p0}, Landroid/view/View;->getTop()I

    move-result v6

    int-to-float v6, v6

    sub-float/2addr v5, v6

    const/high16 v6, 0x42200000    # 40.0f

    .line 276
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v6, v0

    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->collapse:F

    invoke-static {v1, v6, v0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v0

    .line 277
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v6, v3

    invoke-virtual {v1, v6}, Landroid/view/View;->setPivotX(F)V

    .line 278
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v1, v4}, Landroid/view/View;->setPivotY(F)V

    .line 279
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v1, v0}, Landroid/view/View;->setScaleX(F)V

    .line 280
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v1, v0}, Landroid/view/View;->setScaleY(F)V

    .line 281
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    sub-float/2addr v5, v2

    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->collapse:F

    mul-float/2addr v5, p0

    invoke-virtual {v0, v5}, Landroid/view/View;->setTranslationY(F)V

    return-void
.end method

.method private colorizeLinks(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
    .locals 7

    .line 191
    instance-of v0, p1, Landroid/text/Spanned;

    if-nez v0, :cond_0

    return-object p1

    .line 194
    :cond_0
    new-instance v0, Landroid/text/SpannableStringBuilder;

    invoke-direct {v0, p1}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    .line 195
    invoke-virtual {v0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result p1

    const-class v1, Landroid/text/style/ClickableSpan;

    const/4 v2, 0x0

    invoke-virtual {v0, v2, p1, v1}, Landroid/text/SpannableStringBuilder;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object p1

    check-cast p1, [Landroid/text/style/ClickableSpan;

    array-length v1, p1

    :goto_0
    if-ge v2, v1, :cond_1

    aget-object v3, p1, v2

    .line 196
    invoke-virtual {v0, v3}, Landroid/text/SpannableStringBuilder;->getSpanStart(Ljava/lang/Object;)I

    move-result v4

    .line 197
    invoke-virtual {v0, v3}, Landroid/text/SpannableStringBuilder;->getSpanEnd(Ljava/lang/Object;)I

    move-result v5

    .line 198
    invoke-virtual {v0, v3}, Landroid/text/SpannableStringBuilder;->removeSpan(Ljava/lang/Object;)V

    .line 199
    new-instance v3, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$1;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$1;-><init>(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;)V

    const/16 v6, 0x21

    invoke-virtual {v0, v3, v4, v5, v6}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    return-object v0
.end method

.method private hitLink(FF)Landroid/text/style/ClickableSpan;
    .locals 12

    .line 230
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v0}, Landroid/view/View;->getAlpha()F

    move-result v0

    const/4 v2, 0x0

    cmpg-float v0, v0, v2

    if-lez v0, :cond_1

    cmpg-float v0, p2, v2

    if-ltz v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    .line 231
    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v0

    int-to-float v0, v0

    cmpl-float p2, p2, v0

    if-gtz p2, :cond_1

    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleLinks:Ljava/lang/CharSequence;

    instance-of v0, p2, Landroid/text/Spanned;

    if-eqz v0, :cond_1

    check-cast p2, Landroid/text/Spanned;

    .line 234
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/AnimatedTextView;->getPaint()Landroid/text/TextPaint;

    move-result-object v0

    .line 235
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v2

    int-to-float v2, v2

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/AnimatedTextView;->getDrawable()Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getCurrentWidth()F

    move-result p0

    sub-float/2addr v2, p0

    const/high16 p0, 0x40000000    # 2.0f

    div-float/2addr v2, p0

    .line 236
    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result p0

    const-class v3, Landroid/text/style/ClickableSpan;

    const/4 v4, 0x0

    invoke-interface {p2, v4, p0, v3}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object p0

    check-cast p0, [Landroid/text/style/ClickableSpan;

    array-length v3, p0

    move v5, v4

    :goto_0
    if-ge v5, v3, :cond_1

    aget-object v6, p0, v5

    .line 237
    invoke-interface {p2, v6}, Landroid/text/Spanned;->getSpanStart(Ljava/lang/Object;)I

    move-result v7

    .line 238
    invoke-interface {p2, v6}, Landroid/text/Spanned;->getSpanEnd(Ljava/lang/Object;)I

    move-result v8

    .line 239
    invoke-virtual {v0, p2, v4, v7}, Landroid/graphics/Paint;->measureText(Ljava/lang/CharSequence;II)F

    move-result v9

    add-float/2addr v9, v2

    const/high16 v10, 0x40800000    # 4.0f

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v11

    int-to-float v11, v11

    sub-float/2addr v9, v11

    .line 240
    invoke-virtual {v0, p2, v7, v8}, Landroid/graphics/Paint;->measureText(Ljava/lang/CharSequence;II)F

    move-result v7

    add-float/2addr v7, v9

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    mul-int/lit8 v8, v8, 0x2

    int-to-float v8, v8

    add-float/2addr v7, v8

    cmpl-float v8, p1, v9

    if-ltz v8, :cond_0

    cmpg-float v7, p1, v7

    if-gtz v7, :cond_0

    return-object v6

    :cond_0
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    :cond_1
    return-object v1
.end method

.method private synthetic lambda$updateLinkColor$0(IILandroid/animation/ValueAnimator;)V
    .locals 0

    .line 141
    invoke-virtual {p3}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Float;

    invoke-virtual {p3}, Ljava/lang/Float;->floatValue()F

    move-result p3

    invoke-static {p1, p2, p3}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColor:I

    .line 142
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method private onPreviewReady()V
    .locals 1

    const/4 v0, 0x1

    .line 169
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->updateLinkColor(Z)V

    .line 170
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewReadyListener:Ljava/lang/Runnable;

    if-eqz p0, :cond_0

    .line 171
    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    :cond_0
    return-void
.end method

.method private static subtitleOf(Lcom/exteragram/messenger/appicons/AppIcon;)Ljava/lang/CharSequence;
    .locals 2

    .line 108
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->getDescription()Ljava/lang/CharSequence;

    move-result-object v0

    .line 109
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    return-object v0

    .line 112
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->getAuthor()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private updateLinkColor(Z)V
    .locals 4

    .line 124
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteLinkText:I

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v1, v2}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAccentTextColor(Lcom/exteragram/messenger/appicons/AppIcon;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 125
    iget v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColor:I

    if-ne v1, v0, :cond_0

    return-void

    .line 128
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColorAnimator:Landroid/animation/ValueAnimator;

    if-eqz v1, :cond_1

    .line 129
    invoke-virtual {v1}, Landroid/animation/ValueAnimator;->cancel()V

    const/4 v1, 0x0

    .line 130
    iput-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColorAnimator:Landroid/animation/ValueAnimator;

    :cond_1
    if-eqz p1, :cond_3

    .line 132
    iget p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColor:I

    if-nez p1, :cond_2

    goto :goto_0

    :cond_2
    const/4 v1, 0x2

    .line 138
    new-array v1, v1, [F

    fill-array-data v1, :array_0

    invoke-static {v1}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v1

    const-wide/16 v2, 0x118

    invoke-virtual {v1, v2, v3}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    move-result-object v1

    iput-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColorAnimator:Landroid/animation/ValueAnimator;

    .line 139
    sget-object v2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v1, v2}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 140
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColorAnimator:Landroid/animation/ValueAnimator;

    new-instance v2, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0, p1, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;II)V

    invoke-virtual {v1, v2}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 144
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColorAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {p0}, Landroid/animation/ValueAnimator;->start()V

    return-void

    .line 133
    :cond_3
    :goto_0
    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->linkColor:I

    .line 134
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method


# virtual methods
.method public dispatchLinkTouch(Landroid/view/MotionEvent;)Z
    .locals 5

    .line 210
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getX()F

    move-result v1

    sub-float/2addr v0, v1

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v1}, Landroid/view/View;->getX()F

    move-result v1

    sub-float/2addr v0, v1

    .line 211
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v1

    invoke-virtual {p0}, Landroid/view/View;->getY()F

    move-result v2

    sub-float/2addr v1, v2

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v2}, Landroid/view/View;->getY()F

    move-result v2

    sub-float/2addr v1, v2

    .line 212
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result p1

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-nez p1, :cond_1

    .line 214
    invoke-direct {p0, v0, v1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->hitLink(FF)Landroid/text/style/ClickableSpan;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->pressedLink:Landroid/text/style/ClickableSpan;

    if-eqz p1, :cond_0

    return v3

    :cond_0
    return v2

    .line 217
    :cond_1
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->pressedLink:Landroid/text/style/ClickableSpan;

    if-nez v4, :cond_2

    return v2

    :cond_2
    if-ne p1, v3, :cond_3

    .line 220
    invoke-direct {p0, v0, v1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->hitLink(FF)Landroid/text/style/ClickableSpan;

    move-result-object v0

    if-ne v4, v0, :cond_3

    .line 221
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->pressedLink:Landroid/text/style/ClickableSpan;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v0, v1}, Landroid/text/style/ClickableSpan;->onClick(Landroid/view/View;)V

    :cond_3
    if-eq p1, v3, :cond_4

    const/4 v0, 0x3

    if-ne p1, v0, :cond_5

    :cond_4
    const/4 p1, 0x0

    .line 224
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->pressedLink:Landroid/text/style/ClickableSpan;

    :cond_5
    return v3
.end method

.method public bridge synthetic getColorKeys()[I
    .locals 0

    .line 0
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/Theme$Colorable;->getColorKeys()[I

    move-result-object p0

    return-object p0
.end method

.method public getPreviewSize()I
    .locals 0

    .line 303
    iget p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewSizeDp:I

    int-to-float p0, p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getTextBlockHeight()I
    .locals 1

    const/high16 v0, 0x42380000    # 46.0f

    .line 285
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    .line 286
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {p0}, Landroid/view/View;->getVisibility()I

    move-result p0

    if-nez p0, :cond_0

    const/high16 p0, 0x41d00000    # 26.0f

    .line 287
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    add-int/2addr v0, p0

    :cond_0
    return v0
.end method

.method public hasOverlappingRendering()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public onLayout(ZIIII)V
    .locals 0

    .line 258
    invoke-super/range {p0 .. p5}, Landroid/widget/LinearLayout;->onLayout(ZIIII)V

    .line 259
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->applyCollapse()V

    return-void
.end method

.method public set(Lcom/exteragram/messenger/appicons/AppIcon;)V
    .locals 3

    .line 176
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    if-eqz v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 180
    :goto_0
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 181
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v1, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setIcon(Lcom/exteragram/messenger/appicons/AppIcon;)V

    .line 182
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->updateLinkColor(Z)V

    .line 184
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->titleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {p1}, Lcom/exteragram/messenger/appicons/AppIcon;->getTitle()Ljava/lang/CharSequence;

    move-result-object v2

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;Z)V

    .line 185
    invoke-static {p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleOf(Lcom/exteragram/messenger/appicons/AppIcon;)Ljava/lang/CharSequence;

    move-result-object p1

    if-nez p1, :cond_2

    .line 186
    const-string p1, ""

    goto :goto_1

    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-static {p1, v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->formatWithUsernames(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/BaseFragment;)Ljava/lang/CharSequence;

    move-result-object p1

    :goto_1
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleLinks:Ljava/lang/CharSequence;

    .line 187
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->colorizeLinks(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {v1, p0, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setText(Ljava/lang/CharSequence;Z)V

    return-void
.end method

.method public setCollapse(F)V
    .locals 1

    .line 249
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->collapse:F

    cmpl-float v0, v0, p1

    if-nez v0, :cond_0

    return-void

    .line 252
    :cond_0
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->collapse:F

    .line 253
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->applyCollapse()V

    return-void
.end method

.method public setOnPreviewReady(Ljava/lang/Runnable;)V
    .locals 0

    .line 165
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewReadyListener:Ljava/lang/Runnable;

    return-void
.end method

.method public setPreviewSizeDp(I)V
    .locals 1

    .line 293
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewSizeDp:I

    if-ne v0, p1, :cond_0

    return-void

    .line 296
    :cond_0
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewSizeDp:I

    .line 297
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout$LayoutParams;

    int-to-float p1, p1

    .line 298
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    iput p1, v0, Landroid/widget/LinearLayout$LayoutParams;->height:I

    iput p1, v0, Landroid/widget/LinearLayout$LayoutParams;->width:I

    .line 299
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->previewView:Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;

    invoke-virtual {p0}, Landroid/view/View;->requestLayout()V

    return-void
.end method

.method public updateColors()V
    .locals 3

    .line 117
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 118
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->titleView:Lorg/telegram/ui/Components/AnimatedTextView;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextColor(I)V

    .line 119
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->subtitleView:Lorg/telegram/ui/Components/AnimatedTextView;

    const/16 v2, 0xb3

    invoke-static {v0, v2}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/AnimatedTextView;->setTextColor(I)V

    const/4 v0, 0x0

    .line 120
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->updateLinkColor(Z)V

    return-void
.end method
