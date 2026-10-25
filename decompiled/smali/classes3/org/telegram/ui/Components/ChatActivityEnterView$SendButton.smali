.class public abstract Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;
.super Landroid/view/View;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/Components/ItemOptions$ScrimView;


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "ViewConstructor"
    }
.end annotation

.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/ui/Components/ChatActivityEnterView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "SendButton"
.end annotation


# instance fields
.field private final animatedPriceVisible:Lorg/telegram/ui/Components/AnimatedFloat;

.field private final appear:Lorg/telegram/ui/Components/AnimatedFloat;

.field private final backgroundPaint:Landroid/graphics/Paint;

.field private final backgroundRect:Landroid/graphics/RectF;

.field private blurredBackgroundDrawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

.field public final bounce:Lorg/telegram/ui/Components/ButtonBounce;

.field private bounceCountAnimator:Landroid/animation/ValueAnimator;

.field public center:Z

.field public centeredBackground:Z

.field private circleHeight:I

.field private circlePadX:F

.field private circlePadY:F

.field private circleWidth:I

.field private final count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

.field private countBounceScale:F

.field private countColor:I

.field private drawable:Landroid/graphics/drawable/Drawable;

.field private drawableColor:I

.field private drawableInverse:Landroid/graphics/drawable/Drawable;

.field private final emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

.field private ephemeralFactor:F

.field private ephemeralOutlineDrawable:Landroid/graphics/drawable/Drawable;

.field private hidePrice:Z

.field private inactiveDrawable:Landroid/graphics/drawable/Drawable;

.field private infiniteLoading:Z

.field private isNewDesignSendButton:Z

.field private final loadingAnimatedProgress:Lorg/telegram/ui/Components/AnimatedFloat;

.field private final loadingAnimatedShown:Lorg/telegram/ui/Components/AnimatedFloat;

.field private final loadingInterpolator:Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;

.field private final loadingPaint:Landroid/graphics/Paint;

.field private loadingProgress:F

.field private loadingShown:Z

.field private lockIcon:Landroid/graphics/drawable/Drawable;

.field private lockIconColor:I

.field private locked:Z

.field private messagesCount:I

.field public newCounterPos:Z

.field public final open:Lorg/telegram/ui/Components/AnimatedFloat;

.field private final path:Landroid/graphics/Path;

.field private final priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

.field public resId:I

.field public final resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private sameWidthFactor:F

.field private scrimViewBackgroundColor:I

.field private final scrimViewBackgroundPaint:Landroid/graphics/Paint;

.field private final spans:[Lorg/telegram/ui/Components/ColoredImageSpan;

.field private starsPrice:J


# direct methods
.method public static synthetic $r8$lambda$jgR4Yicf4KLBla0dxLmsFAN5Eo8(Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lambda$bounceCount$0(Landroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcountBounceScale(Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;F)V
    .locals 0

    .line 0
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputhidePrice(Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->hidePrice:Z

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 1

    const/4 v0, 0x0

    .line 15662
    invoke-direct {p0, p1, p2, p3, v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;-><init>(Landroid/content/Context;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)V
    .locals 10

    .line 15682
    invoke-direct {p0, p1}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 15655
    new-instance v0, Lorg/telegram/ui/Components/AnimatedFloat;

    sget-object v7, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const-wide/16 v2, 0x0

    const-wide/16 v4, 0x140

    move-object v1, p0

    move-object v6, v7

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Landroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    move-object v2, v1

    iput-object v0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->animatedPriceVisible:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 15659
    new-instance p0, Landroid/graphics/Paint;

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    const/4 p0, -0x1

    .line 15732
    iput p0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleWidth:I

    iput p0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleHeight:I

    .line 15765
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundPaint:Landroid/graphics/Paint;

    .line 15829
    new-array v1, v0, [Lorg/telegram/ui/Components/ColoredImageSpan;

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->spans:[Lorg/telegram/ui/Components/ColoredImageSpan;

    .line 15883
    new-instance v1, Lorg/telegram/ui/Components/AnimatedFloat;

    const-wide/16 v3, 0x0

    const-wide/16 v5, 0x1a4

    invoke-direct/range {v1 .. v7}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Landroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->open:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 15884
    new-instance v1, Lorg/telegram/ui/Components/ButtonBounce;

    invoke-direct {v1, v2}, Lorg/telegram/ui/Components/ButtonBounce;-><init>(Landroid/view/View;)V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounce:Lorg/telegram/ui/Components/ButtonBounce;

    .line 15887
    new-instance v1, Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;

    invoke-direct {v1}, Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;-><init>()V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingInterpolator:Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;

    .line 15891
    new-instance v1, Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-direct/range {v1 .. v7}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Landroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingAnimatedShown:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 15892
    new-instance v1, Lorg/telegram/ui/Components/AnimatedFloat;

    const-wide/16 v5, 0x1f4

    invoke-direct/range {v1 .. v7}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Landroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingAnimatedProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 15894
    new-instance v1, Landroid/graphics/Path;

    invoke-direct {v1}, Landroid/graphics/Path;-><init>()V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->path:Landroid/graphics/Path;

    .line 15895
    new-instance v8, Landroid/graphics/Paint;

    invoke-direct {v8, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v8, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingPaint:Landroid/graphics/Paint;

    .line 15897
    new-instance v9, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-direct {v9, v0, v0, v0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;-><init>(ZZZ)V

    iput-object v9, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    const/high16 v0, 0x3f800000    # 1.0f

    .line 15898
    iput v0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    .line 15900
    new-instance v1, Lorg/telegram/ui/Components/AnimatedFloat;

    const-wide/16 v5, 0x140

    invoke-direct/range {v1 .. v7}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Landroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    iput-object v1, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->appear:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 16270
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    .line 15684
    iput p2, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resId:I

    .line 15685
    iput-object p3, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 15686
    iput-boolean p4, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    .line 15688
    new-instance p3, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-direct {p3}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;-><init>()V

    iput-object p3, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    const/high16 p4, 0x41700000    # 15.0f

    .line 15689
    invoke-static {p4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p4

    int-to-float p4, p4

    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setTextSize(F)V

    .line 15690
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object p4

    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setTypeface(Landroid/graphics/Typeface;)V

    .line 15691
    invoke-virtual {p3, p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setTextColor(I)V

    const/4 p0, 0x3

    .line 15692
    invoke-virtual {p3, p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setGravity(I)V

    .line 15693
    invoke-virtual {p3, v2}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 15694
    sget-object p0, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget p0, p0, Landroid/graphics/Point;->x:I

    invoke-virtual {p3, p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setOverrideFullWidth(I)V

    .line 15696
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p0

    iput-object p0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawable:Landroid/graphics/drawable/Drawable;

    .line 15697
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p0

    iput-object p0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->inactiveDrawable:Landroid/graphics/drawable/Drawable;

    .line 15698
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p0

    iput-object p0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    .line 15699
    new-instance p0, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    const/high16 p1, 0x41600000    # 14.0f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    invoke-direct {p0, v2, p1}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;-><init>(Landroid/view/View;I)V

    iput-object p0, v2, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    .line 15701
    sget-object p0, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v8, p0}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    const/high16 p0, 0x40000000    # 2.0f

    .line 15702
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    int-to-float p0, p0

    invoke-virtual {v8, p0}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 15703
    sget-object p0, Landroid/graphics/Paint$Join;->ROUND:Landroid/graphics/Paint$Join;

    invoke-virtual {v8, p0}, Landroid/graphics/Paint;->setStrokeJoin(Landroid/graphics/Paint$Join;)V

    .line 15704
    sget-object p0, Landroid/graphics/Paint$Cap;->ROUND:Landroid/graphics/Paint$Cap;

    invoke-virtual {v8, p0}, Landroid/graphics/Paint;->setStrokeCap(Landroid/graphics/Paint$Cap;)V

    .line 15706
    invoke-virtual {v9, v2}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    const/high16 p0, 0x41400000    # 12.0f

    .line 15707
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    int-to-float p0, p0

    invoke-virtual {v9, p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setTextSize(F)V

    .line 15708
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object p0

    invoke-virtual {v9, p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setTypeface(Landroid/graphics/Typeface;)V

    const/16 p0, 0x11

    .line 15709
    invoke-virtual {v9, p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setGravity(I)V

    return-void
.end method

.method private checkBackgroundRect()V
    .locals 5

    const/high16 v0, 0x42200000    # 40.0f

    .line 16274
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const/high16 v1, 0x41a00000    # 20.0f

    .line 16276
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v2}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getCurrentWidth()F

    move-result v2

    add-float/2addr v1, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->max(FF)F

    move-result v1

    iget v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->sameWidthFactor:F

    .line 16275
    invoke-static {v1, v0, v2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v1

    .line 16279
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    int-to-float v2, v2

    sub-float/2addr v2, v0

    const/high16 v3, 0x40000000    # 2.0f

    div-float/2addr v2, v3

    .line 16280
    iget-boolean v4, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->centeredBackground:Z

    if-eqz v4, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v4

    int-to-float v4, v4

    add-float/2addr v4, v1

    div-float/2addr v4, v3

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    int-to-float v4, v3

    :goto_0
    sub-float v1, v4, v1

    .line 16282
    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    add-float/2addr v0, v2

    invoke-virtual {p0, v1, v2, v4, v0}, Landroid/graphics/RectF;->set(FFFF)V

    return-void
.end method

.method private synthetic lambda$bounceCount$0(Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 16255
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    return-void
.end method


# virtual methods
.method public appear()V
    .locals 2

    .line 15902
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->appear:Lorg/telegram/ui/Components/AnimatedFloat;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/AnimatedFloat;->force(F)V

    .line 15903
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public bounceCount()V
    .locals 3

    .line 16250
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounceCountAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    .line 16251
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    :cond_0
    const/4 v0, 0x2

    .line 16253
    new-array v0, v0, [F

    fill-array-data v0, :array_0

    invoke-static {v0}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounceCountAnimator:Landroid/animation/ValueAnimator;

    .line 16254
    new-instance v1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;)V

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 16257
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounceCountAnimator:Landroid/animation/ValueAnimator;

    new-instance v1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton$1;

    invoke-direct {v1, p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton$1;-><init>(Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;)V

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 16263
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounceCountAnimator:Landroid/animation/ValueAnimator;

    const-wide/16 v1, 0xb4

    invoke-virtual {v0, v1, v2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 16264
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounceCountAnimator:Landroid/animation/ValueAnimator;

    new-instance v1, Landroid/view/animation/OvershootInterpolator;

    invoke-direct {v1}, Landroid/view/animation/OvershootInterpolator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 16265
    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounceCountAnimator:Landroid/animation/ValueAnimator;

    invoke-virtual {p0}, Landroid/animation/ValueAnimator;->start()V

    return-void

    :array_0
    .array-data 4
        0x3f666666    # 0.9f
        0x3f800000    # 1.0f
    .end array-data
.end method

.method public copyTo(Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;)V
    .locals 3

    .line 16234
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    iput-boolean v0, p1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    .line 16235
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    iput-boolean v0, p1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    .line 16236
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->centeredBackground:Z

    iput-boolean v0, p1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->centeredBackground:Z

    .line 16237
    iget-object v0, p1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    iget-object v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getText()Ljava/lang/CharSequence;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setText(Ljava/lang/CharSequence;Z)V

    .line 16238
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    iput v0, p1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    .line 16239
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->setEmoji(Landroid/graphics/drawable/Drawable;)V

    .line 16240
    iget-wide v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    iget v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->messagesCount:I

    invoke-virtual {p1, v0, v1, v2}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->setStarsPrice(JI)V

    .line 16241
    iget-object v0, p1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->open:Lorg/telegram/ui/Components/AnimatedFloat;

    iget-object v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->open:Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/AnimatedFloat;->get()F

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/AnimatedFloat;->force(F)V

    .line 16242
    iget-object v0, p1, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->animatedPriceVisible:Lorg/telegram/ui/Components/AnimatedFloat;

    iget-object v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->animatedPriceVisible:Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/AnimatedFloat;->get()F

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/AnimatedFloat;->force(F)V

    .line 16243
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleWidth:I

    iget v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleHeight:I

    invoke-virtual {p1, v0, v1}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->setCircleSize(II)V

    .line 16244
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    iget p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    invoke-virtual {p1, v0, p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->setCirclePadding(FF)V

    return-void
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 9

    .line 16124
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->locked:Z

    if-nez v0, :cond_0

    .line 16125
    invoke-super {p0, p1}, Landroid/view/View;->draw(Landroid/graphics/Canvas;)V

    return-void

    :cond_0
    const/high16 v0, 0x40c00000    # 6.0f

    .line 16128
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    neg-int v1, v1

    int-to-float v3, v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    neg-int v1, v1

    int-to-float v4, v1

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v1

    int-to-float v5, v1

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v1

    int-to-float v6, v1

    const/16 v7, 0xff

    const/16 v8, 0x1f

    move-object v2, p1

    invoke-virtual/range {v2 .. v8}, Landroid/graphics/Canvas;->saveLayerAlpha(FFFFII)I

    .line 16129
    invoke-super {p0, v2}, Landroid/view/View;->draw(Landroid/graphics/Canvas;)V

    const/high16 p1, 0x41900000    # 18.0f

    .line 16131
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    .line 16132
    iget-object v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->left:F

    const/high16 v3, 0x40000000    # 2.0f

    div-float/2addr p1, v3

    add-float/2addr v1, p1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    sub-float/2addr v1, v4

    .line 16133
    iget-object v4, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    iget v4, v4, Landroid/graphics/RectF;->top:F

    add-float/2addr v4, p1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    sub-float/2addr v4, v0

    .line 16134
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    add-float/2addr v0, p1

    sget-object v3, Lorg/telegram/ui/ActionBar/Theme;->PAINT_CLEAR:Landroid/graphics/Paint;

    invoke-virtual {v2, v1, v4, v0, v3}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 16135
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1, v4, p1, v0}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 16136
    iget-object p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIcon:Landroid/graphics/drawable/Drawable;

    if-nez p1, :cond_1

    .line 16137
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/R$drawable;->mini_switch_lock:I

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIcon:Landroid/graphics/drawable/Drawable;

    .line 16138
    new-instance v0, Landroid/graphics/PorterDuffColorFilter;

    iget v3, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableColor:I

    iput v3, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIconColor:I

    sget-object v5, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, v3, v5}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {p1, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 16140
    :cond_1
    iget p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIconColor:I

    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableColor:I

    if-eq p1, v0, :cond_2

    .line 16141
    iget-object p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIcon:Landroid/graphics/drawable/Drawable;

    new-instance v0, Landroid/graphics/PorterDuffColorFilter;

    iget v3, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableColor:I

    iput v3, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIconColor:I

    sget-object v5, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, v3, v5}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {p1, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 16143
    :cond_2
    iget-object p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIcon:Landroid/graphics/drawable/Drawable;

    const/high16 v0, 0x41000000    # 8.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    sub-float v3, v1, v3

    float-to-int v3, v3

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v5, v5

    sub-float v5, v4, v5

    float-to-int v5, v5

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    add-float/2addr v1, v6

    float-to-int v1, v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    add-float/2addr v4, v0

    float-to-int v0, v4

    invoke-virtual {p1, v3, v5, v1, v0}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 16144
    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->lockIcon:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, v2}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 16146
    invoke-virtual {v2}, Landroid/graphics/Canvas;->restore()V

    return-void
.end method

.method public drawScrim(Landroid/graphics/Canvas;F)V
    .locals 12

    .line 15774
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundColor:I

    if-eqz v0, :cond_4

    .line 15775
    iget-object v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 15776
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundPaint:Landroid/graphics/Paint;

    iget v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundColor:I

    invoke-static {v1}, Landroid/graphics/Color;->alpha(I)I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v1, p2

    float-to-int p2, v1

    invoke-virtual {v0, p2}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 15778
    iget-object p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->open:Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-virtual {p2}, Lorg/telegram/ui/Components/AnimatedFloat;->get()F

    move-result p2

    .line 15779
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->animatedPriceVisible:Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/AnimatedFloat;->get()F

    move-result v0

    .line 15782
    iget-boolean v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    const/high16 v2, 0x41100000    # 9.0f

    const/high16 v3, 0x40800000    # 4.0f

    const/high16 v4, 0x40000000    # 2.0f

    if-eqz v1, :cond_0

    .line 15783
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v5, v4

    sub-float/2addr v1, v5

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    sub-int/2addr v5, v6

    int-to-float v5, v5

    invoke-static {v1, v5, p2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v1

    iget v5, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v1, v5

    .line 15784
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v5

    int-to-float v5, v5

    mul-float/2addr v5, p2

    .line 15785
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    int-to-float v6, v6

    iget v7, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v6, v7

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    int-to-float v7, v7

    sub-float/2addr v6, v7

    div-float v7, v5, v4

    sub-float/2addr v6, v7

    goto :goto_0

    .line 15788
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v5, v4

    sub-float/2addr v1, v5

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    sub-int/2addr v5, v6

    int-to-float v5, v5

    invoke-static {v1, v5, p2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v1

    iget v5, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v1, v5

    .line 15789
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    sub-int/2addr v5, v6

    int-to-float v5, v5

    .line 15787
    invoke-static {v1, v5, v0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v1

    .line 15792
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    int-to-float v5, v5

    iget v6, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v5, v6

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    sub-float/2addr v5, v6

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v6, v4

    sub-float/2addr v5, v6

    .line 15793
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    const/high16 v7, 0x41c00000    # 24.0f

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    sub-int/2addr v6, v7

    int-to-float v6, v6

    .line 15791
    invoke-static {v5, v6, v0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v6

    .line 15795
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v5

    const/high16 v7, 0x42000000    # 32.0f

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    invoke-static {v5, v7, v0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(IIF)I

    move-result v5

    int-to-float v5, v5

    mul-float/2addr v5, p2

    .line 15797
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleWidth()I

    move-result v7

    int-to-float v7, v7

    iget-boolean v8, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-eqz v8, :cond_1

    const/high16 v8, 0x41a00000    # 20.0f

    goto :goto_1

    :cond_1
    const/high16 v8, 0x41b00000    # 22.0f

    :goto_1
    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    int-to-float v8, v8

    iget-object v9, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v9}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getCurrentWidth()F

    move-result v9

    add-float/2addr v8, v9

    invoke-static {v7, v8, v0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v7

    mul-float/2addr v7, p2

    const/4 v8, 0x0

    cmpl-float p2, p2, v8

    if-lez p2, :cond_2

    cmpl-float p2, v7, v8

    if-lez p2, :cond_2

    cmpl-float p2, v5, v8

    if-lez p2, :cond_2

    .line 15800
    sget-object p2, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    sub-float v9, v1, v7

    div-float v10, v5, v4

    sub-float v11, v6, v10

    add-float/2addr v10, v6

    invoke-virtual {p2, v9, v11, v1, v10}, Landroid/graphics/RectF;->set(FFFF)V

    .line 15801
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v9

    neg-int v9, v9

    int-to-float v9, v9

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v10

    neg-int v10, v10

    int-to-float v10, v10

    invoke-virtual {p2, v9, v10}, Landroid/graphics/RectF;->inset(FF)V

    .line 15802
    invoke-static {v7, v5}, Ljava/lang/Math;->min(FF)F

    move-result v5

    div-float/2addr v5, v4

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    add-float/2addr v5, v3

    .line 15803
    iget-object v3, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, p2, v5, v5, v3}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 15806
    :cond_2
    iget-object p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {p2}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->isNotEmpty()F

    move-result p2

    const/high16 v3, 0x3f800000    # 1.0f

    sub-float/2addr v3, v0

    mul-float/2addr p2, v3

    cmpl-float v0, p2, v8

    if-lez v0, :cond_4

    .line 15808
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v2}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getCurrentWidth()F

    move-result v2

    add-float/2addr v0, v2

    const/high16 v2, 0x41900000    # 18.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    invoke-static {v0, v2}, Ljava/lang/Math;->max(FF)F

    move-result v0

    .line 15810
    iget-boolean v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    if-eqz v2, :cond_3

    const/high16 v2, 0x42480000    # 50.0f

    .line 15811
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    sub-float/2addr v1, v2

    .line 15812
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v2, v4

    sub-float/2addr v6, v2

    div-float v2, v0, v4

    add-float/2addr v6, v2

    goto :goto_2

    .line 15814
    :cond_3
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    int-to-float v1, v1

    iget v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v1, v2

    div-float v2, v0, v4

    sub-float/2addr v1, v2

    .line 15815
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    int-to-float v3, v3

    iget v5, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v3, v5

    sub-float v6, v3, v2

    :goto_2
    div-float/2addr v0, v4

    .line 15817
    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    add-float/2addr v0, v2

    mul-float/2addr v0, p2

    iget p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    mul-float/2addr v0, p2

    iget-object p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v1, v6, v0, p2}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 15820
    :cond_4
    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->draw(Landroid/graphics/Canvas;)V

    return-void
.end method

.method public getBounds(Landroid/graphics/RectF;)V
    .locals 5

    .line 15757
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleWidth()I

    move-result v0

    int-to-float v0, v0

    .line 15758
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v1

    int-to-float v1, v1

    .line 15759
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    const/high16 v3, 0x40800000    # 4.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int/2addr v2, v4

    int-to-float v2, v2

    iget v4, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v2, v4

    .line 15760
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v4

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sub-int/2addr v4, v3

    int-to-float v3, v4

    iget p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v3, p0

    sub-float p0, v2, v0

    sub-float v0, v3, v1

    .line 15761
    invoke-virtual {p1, p0, v0, v2, v3}, Landroid/graphics/RectF;->set(FFFF)V

    return-void
.end method

.method public getCircleHeight()I
    .locals 1

    .line 15750
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleHeight:I

    if-ltz v0, :cond_0

    return v0

    .line 15752
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result p0

    const/high16 v0, 0x41000000    # 8.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    sub-int/2addr p0, v0

    return p0
.end method

.method public getCircleWidth()I
    .locals 1

    .line 15744
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleWidth:I

    if-ltz v0, :cond_0

    return v0

    .line 15746
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result p0

    const/high16 v0, 0x41000000    # 8.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    sub-int/2addr p0, v0

    return p0
.end method

.method public getFillColor()I
    .locals 1

    .line 15880
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelSend:I

    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method public height()I
    .locals 1

    .line 16171
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->height(I)I

    move-result p0

    return p0
.end method

.method public height(I)I
    .locals 4

    .line 16175
    iget-wide v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    const-wide/16 v2, 0x0

    cmp-long p1, v0, v2

    if-lez p1, :cond_0

    const/high16 p1, 0x3f800000    # 1.0f

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    .line 16176
    :goto_0
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    iget p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    add-float/2addr v0, p0

    const/high16 p0, 0x42000000    # 32.0f

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    int-to-float p0, p0

    invoke-static {v0, p0, p1}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result p0

    float-to-int p0, p0

    return p0
.end method

.method public isInScheduleMode()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public isInactive()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public isOpen()Z
    .locals 4

    .line 15864
    iget-wide v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    const-wide/16 v2, 0x0

    cmp-long p0, v0, v2

    if-lez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 33

    move-object/from16 v0, p0

    .line 15916
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Canvas;->save()I

    move-result v8

    .line 15918
    iget-boolean v1, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-nez v1, :cond_0

    .line 15919
    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v1

    int-to-float v4, v1

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v1

    int-to-float v5, v1

    const/16 v6, 0xff

    const/16 v7, 0x1f

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v7}, Landroid/graphics/Canvas;->saveLayerAlpha(FFFFII)I

    goto :goto_0

    :cond_0
    move-object/from16 v1, p1

    .line 15921
    :goto_0
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->updateColors()V

    .line 15922
    invoke-direct {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->checkBackgroundRect()V

    .line 15923
    iget-boolean v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    const/high16 v7, 0x41a00000    # 20.0f

    if-eqz v2, :cond_1

    .line 15924
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget-object v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v3, v4, v5}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 15927
    :cond_1
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isInactive()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 15928
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->inactiveDrawable:Landroid/graphics/drawable/Drawable;

    :goto_1
    move-object v9, v2

    goto :goto_2

    :cond_2
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawable:Landroid/graphics/drawable/Drawable;

    goto :goto_1

    .line 15931
    :goto_2
    iget-boolean v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    const/high16 v10, 0x3f800000    # 1.0f

    const/high16 v11, 0x40000000    # 2.0f

    if-eqz v2, :cond_3

    .line 15932
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    iget v3, v2, Landroid/graphics/RectF;->right:F

    invoke-virtual {v2}, Landroid/graphics/RectF;->height()F

    move-result v2

    div-float/2addr v2, v11

    sub-float/2addr v3, v2

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v2, v11

    sub-float/2addr v3, v2

    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v2

    .line 15933
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    iget v4, v3, Landroid/graphics/RectF;->top:F

    invoke-virtual {v3}, Landroid/graphics/RectF;->height()F

    move-result v3

    div-float/2addr v3, v11

    add-float/2addr v4, v3

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v3, v11

    sub-float/2addr v4, v3

    invoke-static {v4}, Ljava/lang/Math;->round(F)I

    move-result v3

    :goto_3
    move v12, v2

    move v13, v3

    goto :goto_4

    .line 15935
    :cond_3
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    sub-int/2addr v2, v3

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    sub-int/2addr v2, v3

    .line 15936
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v4

    sub-int/2addr v3, v4

    div-int/lit8 v3, v3, 0x2

    .line 15937
    iget-boolean v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->center:Z

    if-eqz v4, :cond_4

    goto :goto_3

    .line 15939
    :cond_4
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isInScheduleMode()Z

    move-result v4

    if-eqz v4, :cond_5

    .line 15940
    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int/2addr v3, v4

    goto :goto_3

    .line 15942
    :cond_5
    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    add-int/2addr v2, v4

    goto :goto_3

    .line 15946
    :goto_4
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingAnimatedShown:Lorg/telegram/ui/Components/AnimatedFloat;

    iget-boolean v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingShown:Z

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Components/AnimatedFloat;->set(Z)F

    move-result v14

    .line 15948
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->open:Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isOpen()Z

    move-result v3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Components/AnimatedFloat;->set(Z)F

    move-result v2

    .line 15949
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->animatedPriceVisible:Lorg/telegram/ui/Components/AnimatedFloat;

    iget-wide v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    const-wide/16 v15, 0x0

    cmp-long v4, v4, v15

    if-lez v4, :cond_6

    iget-boolean v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->hidePrice:Z

    if-nez v4, :cond_6

    const/4 v4, 0x1

    goto :goto_5

    :cond_6
    const/4 v4, 0x0

    :goto_5
    invoke-virtual {v3, v4}, Lorg/telegram/ui/Components/AnimatedFloat;->set(Z)F

    move-result v3

    iget v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralFactor:F

    sub-float v4, v10, v4

    mul-float v15, v3, v4

    .line 15950
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->appear:Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-virtual {v3, v10}, Lorg/telegram/ui/Components/AnimatedFloat;->set(F)F

    move-result v3

    cmpg-float v4, v2, v10

    const/high16 v6, 0x41c00000    # 24.0f

    const/high16 v16, 0x437f0000    # 255.0f

    if-gez v4, :cond_7

    .line 15952
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 15953
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    neg-int v4, v4

    int-to-float v4, v4

    sub-float v17, v10, v3

    mul-float v4, v4, v17

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v5, v5

    mul-float v5, v5, v17

    invoke-virtual {v1, v4, v5}, Landroid/graphics/Canvas;->translate(FF)V

    const v4, 0x3eb33333    # 0.35f

    .line 15954
    invoke-static {v4, v10, v3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v3

    int-to-float v4, v12

    .line 15955
    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v5, v11

    add-float/2addr v5, v4

    move/from16 v19, v6

    int-to-float v6, v13

    move/from16 v20, v7

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v7

    int-to-float v7, v7

    div-float/2addr v7, v11

    add-float/2addr v7, v6

    invoke-virtual {v1, v3, v3, v5, v7}, Landroid/graphics/Canvas;->scale(FFFF)V

    const/high16 v3, 0x42700000    # 60.0f

    mul-float v3, v3, v17

    .line 15956
    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v5, v11

    add-float/2addr v4, v5

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v5, v11

    add-float/2addr v6, v5

    invoke-virtual {v1, v3, v4, v6}, Landroid/graphics/Canvas;->rotate(FFF)V

    .line 15957
    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v3

    add-int/2addr v3, v12

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v4

    add-int/2addr v4, v13

    invoke-virtual {v9, v12, v13, v3, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    sub-float v3, v10, v15

    mul-float v3, v3, v16

    float-to-int v3, v3

    .line 15958
    invoke-virtual {v9, v3}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 15959
    invoke-virtual {v9, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 15960
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    goto :goto_6

    :cond_7
    move/from16 v19, v6

    move/from16 v20, v7

    .line 15963
    :goto_6
    iget-boolean v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    const/high16 v4, 0x40800000    # 4.0f

    if-eqz v3, :cond_8

    .line 15964
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v3

    int-to-float v3, v3

    mul-float/2addr v3, v2

    .line 15965
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    int-to-float v5, v5

    iget v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v5, v6

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    sub-float/2addr v5, v6

    div-float v6, v3, v11

    sub-float/2addr v5, v6

    :goto_7
    move v7, v5

    goto :goto_8

    .line 15968
    :cond_8
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    int-to-float v3, v3

    iget v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v3, v5

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v5, v5

    sub-float/2addr v3, v5

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v5, v11

    sub-float/2addr v3, v5

    .line 15969
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    invoke-static/range {v19 .. v19}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    sub-int/2addr v5, v6

    int-to-float v5, v5

    .line 15967
    invoke-static {v3, v5, v15}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v5

    .line 15971
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v3

    const/high16 v6, 0x42000000    # 32.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    invoke-static {v3, v6, v15}, Lorg/telegram/messenger/AndroidUtilities;->lerp(IIF)I

    move-result v3

    int-to-float v3, v3

    mul-float/2addr v3, v2

    goto :goto_7

    .line 15974
    :goto_8
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleWidth()I

    move-result v5

    int-to-float v5, v5

    iget-boolean v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-eqz v6, :cond_9

    move/from16 v6, v20

    goto :goto_9

    :cond_9
    const/high16 v6, 0x41b00000    # 22.0f

    :goto_9
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    move/from16 v17, v4

    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getCurrentWidth()F

    move-result v4

    add-float/2addr v6, v4

    invoke-static {v5, v6, v15}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    .line 15975
    iget v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->sameWidthFactor:F

    invoke-static {v4, v3, v5}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v5

    mul-float/2addr v5, v2

    sub-float v19, v4, v5

    .line 15979
    iget-boolean v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    const/high16 v21, 0x41100000    # 9.0f

    if-eqz v4, :cond_a

    .line 15980
    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    invoke-virtual {v4}, Landroid/graphics/RectF;->centerX()F

    move-result v4

    div-float v6, v5, v11

    add-float/2addr v4, v6

    goto :goto_a

    .line 15981
    :cond_a
    iget-boolean v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    if-eqz v4, :cond_b

    .line 15982
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v6, v11

    sub-float/2addr v4, v6

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    invoke-static/range {v17 .. v17}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v17

    sub-int v6, v6, v17

    int-to-float v6, v6

    invoke-static {v4, v6, v2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    iget v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v4, v6

    goto :goto_a

    .line 15985
    :cond_b
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v6, v11

    sub-float/2addr v4, v6

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    invoke-static/range {v17 .. v17}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v17

    sub-int v6, v6, v17

    int-to-float v6, v6

    invoke-static {v4, v6, v2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    iget v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v4, v6

    .line 15986
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    invoke-static/range {v21 .. v21}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v17

    sub-int v6, v6, v17

    int-to-float v6, v6

    .line 15984
    invoke-static {v4, v6, v15}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    :goto_a
    div-float v6, v5, v11

    sub-float v6, v4, v6

    .line 15990
    invoke-virtual {v0, v6}, Landroid/view/View;->setPivotX(F)V

    .line 15991
    invoke-virtual {v0, v7}, Landroid/view/View;->setPivotY(F)V

    move/from16 v17, v11

    const v11, 0x3f4a3d71    # 0.79f

    move/from16 v22, v2

    .line 15993
    iget v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralFactor:F

    invoke-static {v10, v11, v2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v2

    const/4 v11, 0x0

    cmpl-float v22, v22, v11

    const/high16 v23, 0x41200000    # 10.0f

    if-lez v22, :cond_18

    .line 15995
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 15996
    iget-object v10, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->path:Landroid/graphics/Path;

    invoke-virtual {v10}, Landroid/graphics/Path;->rewind()V

    .line 15997
    invoke-static {v5, v3}, Ljava/lang/Math;->min(FF)F

    move-result v10

    div-float v10, v10, v17

    move/from16 v24, v11

    .line 15998
    sget-object v11, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    sub-float v5, v4, v5

    div-float v3, v3, v17

    move-object/from16 v25, v9

    sub-float v9, v7, v3

    add-float/2addr v3, v7

    invoke-virtual {v11, v5, v9, v4, v3}, Landroid/graphics/RectF;->set(FFFF)V

    .line 15999
    iget-object v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->path:Landroid/graphics/Path;

    move/from16 v26, v3

    sget-object v3, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v5, v11, v10, v10, v3}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 16001
    invoke-virtual {v11}, Landroid/graphics/RectF;->centerX()F

    move-result v3

    .line 16002
    invoke-virtual {v11}, Landroid/graphics/RectF;->centerY()F

    move-result v5

    .line 16004
    iget v10, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralFactor:F

    cmpl-float v10, v10, v24

    if-lez v10, :cond_d

    .line 16005
    iget-object v10, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralOutlineDrawable:Landroid/graphics/drawable/Drawable;

    if-nez v10, :cond_c

    .line 16006
    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v10

    invoke-virtual {v10}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v10

    move/from16 v27, v4

    sget v4, Lorg/telegram/messenger/R$drawable;->send_outline:I

    invoke-virtual {v10, v4}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    iput-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralOutlineDrawable:Landroid/graphics/drawable/Drawable;

    goto :goto_b

    :cond_c
    move/from16 v27, v4

    .line 16008
    :goto_b
    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralOutlineDrawable:Landroid/graphics/drawable/Drawable;

    iget-object v10, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v10}, Landroid/graphics/Paint;->getColor()I

    move-result v10

    move/from16 v28, v6

    sget-object v6, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v4, v10, v6}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 16009
    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralOutlineDrawable:Landroid/graphics/drawable/Drawable;

    const/16 v6, 0x11

    invoke-static {v4, v3, v5, v6}, Lorg/telegram/messenger/utils/DrawableUtils;->setBounds(Landroid/graphics/drawable/Drawable;FFI)V

    .line 16010
    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralOutlineDrawable:Landroid/graphics/drawable/Drawable;

    iget v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralFactor:F

    invoke-static {v1, v4, v6}, Lorg/telegram/messenger/utils/DrawableUtils;->drawWithScale(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;F)V

    goto :goto_c

    :cond_d
    move/from16 v27, v4

    move/from16 v28, v6

    .line 16013
    :goto_c
    invoke-virtual {v1, v2, v2, v3, v5}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 16015
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->blurredBackgroundDrawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    if-eqz v2, :cond_e

    .line 16016
    sget-object v2, Lorg/telegram/messenger/AndroidUtilities;->rectTmp2:Landroid/graphics/Rect;

    invoke-virtual {v11, v2}, Landroid/graphics/RectF;->round(Landroid/graphics/Rect;)V

    const/high16 v3, 0x40e00000    # 7.0f

    .line 16017
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    neg-int v4, v4

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    neg-int v3, v3

    invoke-virtual {v2, v4, v3}, Landroid/graphics/Rect;->inset(II)V

    .line 16018
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->blurredBackgroundDrawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    invoke-virtual {v3, v2}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 16019
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->blurredBackgroundDrawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    invoke-virtual {v2, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 16022
    :cond_e
    iget-boolean v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-nez v2, :cond_f

    .line 16023
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->path:Landroid/graphics/Path;

    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 16025
    :cond_f
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->path:Landroid/graphics/Path;

    invoke-virtual {v1, v2}, Landroid/graphics/Canvas;->clipPath(Landroid/graphics/Path;)Z

    cmpl-float v10, v14, v24

    if-lez v10, :cond_12

    .line 16027
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingPaint:Landroid/graphics/Paint;

    const/4 v3, -0x1

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 16028
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingPaint:Landroid/graphics/Paint;

    mul-float v3, v14, v16

    float-to-int v3, v3

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    const v2, 0x410a8f5c    # 8.66f

    .line 16029
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    sub-float v6, v28, v2

    sub-float v3, v7, v2

    add-float v4, v28, v2

    add-float/2addr v2, v7

    .line 16030
    invoke-virtual {v11, v6, v3, v4, v2}, Landroid/graphics/RectF;->set(FFFF)V

    .line 16031
    iget-boolean v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->infiniteLoading:Z

    if-eqz v2, :cond_11

    .line 16032
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    const-wide/16 v4, 0x1518

    rem-long/2addr v2, v4

    const-wide/16 v4, 0x5f0

    mul-long/2addr v4, v2

    long-to-float v4, v4

    const v5, 0x45a8c000    # 5400.0f

    div-float/2addr v4, v5

    sub-float v5, v4, v20

    move/from16 v6, v24

    .line 16033
    invoke-static {v6, v5}, Ljava/lang/Math;->max(FF)F

    move-result v5

    move-wide/from16 v29, v2

    move v3, v5

    const/4 v5, 0x0

    :goto_d
    const/4 v2, 0x4

    if-ge v5, v2, :cond_10

    .line 16036
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingInterpolator:Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;

    mul-int/lit16 v6, v5, 0x546

    move/from16 v18, v3

    move v11, v4

    int-to-long v3, v6

    sub-long v3, v29, v3

    long-to-float v3, v3

    const v4, 0x4426c000    # 667.0f

    div-float/2addr v3, v4

    invoke-virtual {v2, v3}, Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;->getInterpolation(F)F

    move-result v2

    const/high16 v3, 0x437a0000    # 250.0f

    mul-float/2addr v2, v3

    add-float/2addr v2, v11

    .line 16037
    iget-object v11, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingInterpolator:Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;

    add-int/lit16 v6, v6, 0x29b

    move/from16 v32, v3

    move/from16 v31, v4

    int-to-long v3, v6

    sub-long v3, v29, v3

    long-to-float v3, v3

    div-float v3, v3, v31

    invoke-virtual {v11, v3}, Landroidx/interpolator/view/animation/FastOutSlowInInterpolator;->getInterpolation(F)F

    move-result v3

    mul-float v3, v3, v32

    add-float v3, v18, v3

    add-int/lit8 v5, v5, 0x1

    move v4, v2

    goto :goto_d

    :cond_10
    move/from16 v18, v3

    move v11, v4

    .line 16039
    sget-object v2, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    sub-float v4, v11, v18

    const/4 v5, 0x0

    iget-object v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingPaint:Landroid/graphics/Paint;

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    move-object/from16 v1, p1

    move/from16 v18, v10

    move/from16 v10, v26

    move/from16 v11, v28

    goto :goto_e

    .line 16042
    :cond_11
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    const-wide/16 v3, 0xbb8

    rem-long/2addr v1, v3

    long-to-float v1, v1

    const/high16 v2, 0x447a0000    # 1000.0f

    div-float/2addr v1, v2

    const/high16 v2, 0x42f00000    # 120.0f

    mul-float/2addr v1, v2

    const/high16 v2, 0x43b40000    # 360.0f

    rem-float/2addr v1, v2

    const/high16 v3, -0x3d4c0000    # -90.0f

    add-float/2addr v3, v1

    .line 16043
    iget-object v1, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingAnimatedProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    iget v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingProgress:F

    invoke-virtual {v1, v4}, Lorg/telegram/ui/Components/AnimatedFloat;->set(F)F

    move-result v1

    mul-float v4, v1, v2

    const/4 v5, 0x0

    iget-object v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingPaint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    move/from16 v18, v10

    move-object v2, v11

    move/from16 v10, v26

    move/from16 v11, v28

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 16045
    :goto_e
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    const v2, 0x3f19999a    # 0.6f

    const/high16 v3, 0x3f800000    # 1.0f

    .line 16046
    invoke-static {v3, v2, v14}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v2

    .line 16047
    invoke-virtual {v1, v2, v2, v11, v7}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 16048
    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    :goto_f
    const/16 v24, 0x0

    goto :goto_10

    :cond_12
    move/from16 v18, v10

    move/from16 v10, v26

    move/from16 v11, v28

    goto :goto_f

    :goto_10
    cmpl-float v2, v15, v24

    if-lez v2, :cond_15

    .line 16051
    iget-boolean v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    if-eqz v2, :cond_13

    .line 16052
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v2}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getAnimateToWidth()F

    move-result v3

    sub-float v4, v27, v3

    const/high16 v3, 0x41300000    # 11.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v5, v5

    sub-float/2addr v4, v5

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    sub-float v3, v27, v3

    invoke-virtual {v2, v4, v9, v3, v10}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setBounds(FFFF)V

    goto :goto_11

    .line 16053
    :cond_13
    iget-boolean v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    .line 16056
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    if-eqz v2, :cond_14

    .line 16054
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    iget v2, v2, Landroid/graphics/RectF;->left:F

    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    add-float/2addr v2, v4

    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundRect:Landroid/graphics/RectF;

    iget v5, v4, Landroid/graphics/RectF;->top:F

    iget v6, v4, Landroid/graphics/RectF;->right:F

    iget v4, v4, Landroid/graphics/RectF;->bottom:F

    invoke-virtual {v3, v2, v5, v6, v4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setBounds(FFFF)V

    goto :goto_11

    .line 16056
    :cond_14
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    int-to-float v2, v2

    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getAnimateToWidth()F

    move-result v4

    sub-float/2addr v2, v4

    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    sub-float/2addr v2, v4

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v4

    const/high16 v5, 0x42400000    # 48.0f

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    sub-int/2addr v4, v5

    int-to-float v4, v4

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    sub-int/2addr v5, v6

    int-to-float v5, v5

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {v3, v2, v4, v5, v6}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setBounds(FFFF)V

    .line 16058
    :goto_11
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    mul-float v3, v15, v16

    const/high16 v22, 0x3f800000    # 1.0f

    sub-float v10, v22, v14

    mul-float/2addr v3, v10

    float-to-int v3, v3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setAlpha(I)V

    .line 16059
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v2, v1}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_12

    :cond_15
    const/high16 v22, 0x3f800000    # 1.0f

    .line 16061
    :goto_12
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    sub-float v10, v22, v14

    mul-float v10, v10, v16

    sub-float v3, v22, v15

    mul-float/2addr v10, v3

    float-to-int v3, v10

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 16062
    iget v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleWidth:I

    .line 16065
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    if-lez v2, :cond_16

    .line 16063
    invoke-virtual {v3}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    int-to-float v2, v2

    div-float v2, v2, v17

    sub-float v6, v11, v2

    float-to-int v2, v6

    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v4}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v4

    int-to-float v4, v4

    div-float v4, v4, v17

    sub-float v4, v7, v4

    float-to-int v4, v4

    iget-object v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v5}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v5

    int-to-float v5, v5

    div-float v5, v5, v17

    add-float v6, v11, v5

    float-to-int v5, v6

    iget-object v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v6}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v6

    int-to-float v6, v6

    div-float v6, v6, v17

    add-float/2addr v6, v7

    float-to-int v6, v6

    invoke-virtual {v3, v2, v4, v5, v6}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    goto :goto_13

    .line 16065
    :cond_16
    invoke-virtual/range {v25 .. v25}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    add-int/2addr v2, v12

    invoke-virtual/range {v25 .. v25}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v4

    add-int/2addr v4, v13

    invoke-virtual {v3, v12, v13, v2, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 16067
    :goto_13
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    if-lez v18, :cond_17

    .line 16069
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 16071
    :cond_17
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    goto :goto_14

    :cond_18
    move/from16 v27, v4

    .line 16074
    :goto_14
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v2}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->isNotEmpty()F

    move-result v2

    const/high16 v22, 0x3f800000    # 1.0f

    sub-float v10, v22, v15

    mul-float/2addr v2, v10

    .line 16076
    iget-boolean v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->locked:Z

    if-nez v3, :cond_1b

    .line 16077
    invoke-static/range {v21 .. v21}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    iget-object v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getCurrentWidth()F

    move-result v4

    add-float/2addr v3, v4

    const/high16 v4, 0x41900000    # 18.0f

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    invoke-static {v3, v4}, Ljava/lang/Math;->max(FF)F

    move-result v3

    .line 16079
    iget-boolean v4, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->newCounterPos:Z

    if-eqz v4, :cond_19

    const/high16 v4, 0x42480000    # 50.0f

    .line 16080
    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    sub-float v4, v27, v4

    add-float v4, v4, v19

    .line 16081
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v5

    int-to-float v5, v5

    div-float v5, v5, v17

    sub-float/2addr v7, v5

    div-float v5, v3, v17

    add-float/2addr v7, v5

    const v5, 0x3f28f5c3    # 0.66f

    .line 16082
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v6, v5

    goto :goto_15

    .line 16084
    :cond_19
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v4

    int-to-float v4, v4

    iget v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v4, v5

    div-float v5, v3, v17

    sub-float/2addr v4, v5

    .line 16085
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    int-to-float v6, v6

    iget v7, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v6, v7

    sub-float v7, v6, v5

    const/4 v6, 0x0

    .line 16089
    :goto_15
    iget-object v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    div-float v3, v3, v17

    sub-float v9, v4, v3

    float-to-int v9, v9

    sub-float v10, v7, v3

    sub-float/2addr v10, v6

    float-to-int v10, v10

    add-float v11, v4, v3

    float-to-int v11, v11

    add-float v12, v7, v3

    sub-float/2addr v12, v6

    float-to-int v6, v12

    invoke-virtual {v5, v9, v10, v11, v6}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setBounds(IIII)V

    const/16 v24, 0x0

    cmpl-float v5, v2, v24

    if-lez v5, :cond_1b

    const v5, 0x3f59999a    # 0.85f

    .line 16091
    iget v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralFactor:F

    const/high16 v9, 0x3f800000    # 1.0f

    invoke-static {v9, v5, v6}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v5

    .line 16092
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 16093
    invoke-virtual {v1, v5, v5, v4, v7}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 16094
    iget-boolean v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-nez v5, :cond_1a

    .line 16095
    invoke-static/range {v17 .. v17}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v5, v5

    add-float/2addr v5, v3

    mul-float/2addr v5, v2

    iget v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    mul-float/2addr v5, v6

    sget-object v6, Lorg/telegram/ui/ActionBar/Theme;->PAINT_CLEAR:Landroid/graphics/Paint;

    invoke-virtual {v1, v4, v7, v5, v6}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    mul-float/2addr v3, v2

    .line 16096
    iget v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countBounceScale:F

    mul-float/2addr v3, v5

    iget-object v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v4, v7, v3, v5}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 16098
    :cond_1a
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    mul-float v4, v2, v16

    float-to-int v4, v4

    invoke-virtual {v3, v4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setAlpha(I)V

    .line 16099
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {v3, v1}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 16100
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    :cond_1b
    const/high16 v22, 0x3f800000    # 1.0f

    cmpg-float v3, v2, v22

    if-gez v3, :cond_1c

    const/high16 v3, 0x41000000    # 8.0f

    .line 16105
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    .line 16106
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleWidth()I

    move-result v5

    int-to-float v5, v5

    div-float v5, v5, v17

    sub-float/2addr v4, v5

    iget v5, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    sub-float/2addr v4, v5

    const/high16 v5, 0x41400000    # 12.0f

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    add-float/2addr v4, v6

    invoke-static/range {v17 .. v17}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    sub-float v6, v27, v6

    invoke-static {v4, v6, v15}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    float-to-int v4, v4

    .line 16107
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    int-to-float v6, v6

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleHeight()I

    move-result v7

    int-to-float v7, v7

    div-float v7, v7, v17

    sub-float/2addr v6, v7

    iget v7, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    sub-float/2addr v6, v7

    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    int-to-float v7, v7

    add-float/2addr v6, v7

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v7

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    sub-int/2addr v7, v5

    int-to-float v5, v7

    invoke-static {v6, v5, v15}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v5

    float-to-int v5, v5

    .line 16109
    iget-object v6, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    sub-int v7, v4, v3

    sub-int v9, v5, v3

    add-int/2addr v4, v3

    add-int/2addr v5, v3

    invoke-virtual {v6, v7, v9, v4, v5}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 16110
    iget-object v3, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    const/high16 v22, 0x3f800000    # 1.0f

    sub-float v10, v22, v2

    mul-float v10, v10, v16

    float-to-int v2, v10

    invoke-virtual {v3, v2}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;->setAlpha(I)V

    .line 16111
    iget-object v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    invoke-virtual {v2, v1}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 16114
    :cond_1c
    iget-boolean v2, v0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-nez v2, :cond_1d

    .line 16115
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 16118
    :cond_1d
    invoke-virtual {v1, v8}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 16119
    invoke-super/range {p0 .. p1}, Landroid/view/View;->onDraw(Landroid/graphics/Canvas;)V

    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 4

    .line 16181
    invoke-virtual {p0}, Landroid/view/View;->getAlpha()F

    move-result v0

    const/4 v1, 0x0

    cmpg-float v0, v0, v1

    const/4 v1, 0x0

    if-gtz v0, :cond_0

    return v1

    .line 16184
    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    if-nez v0, :cond_2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v2

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->width()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    cmpg-float v0, v0, v2

    if-ltz v0, :cond_1

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v2

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->height()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    cmpg-float v0, v0, v2

    if-gez v0, :cond_2

    :cond_1
    return v1

    .line 16187
    :cond_2
    invoke-super {p0, p1}, Landroid/view/View;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result p0

    return p0
.end method

.method public setBlurredBackgroundDrawable(Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;)V
    .locals 1

    .line 16152
    iput-object p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->blurredBackgroundDrawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    const/high16 v0, 0x41b00000    # 22.0f

    .line 16153
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setRadius(F)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    .line 16154
    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->blurredBackgroundDrawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    const/high16 p1, 0x40800000    # 4.0f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setPadding(I)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    return-void
.end method

.method public setCirclePadding(FF)V
    .locals 0

    .line 15825
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    .line 15826
    iput p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadY:F

    return-void
.end method

.method public setCircleSize(I)V
    .locals 0

    .line 15734
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleWidth:I

    .line 15735
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleHeight:I

    return-void
.end method

.method public setCircleSize(II)V
    .locals 0

    .line 15739
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleWidth:I

    .line 15740
    iput p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circleHeight:I

    return-void
.end method

.method public setCount(IZ)V
    .locals 3

    .line 16229
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    const-string v1, ""

    if-gtz p1, :cond_0

    goto :goto_0

    :cond_0
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    :goto_0
    invoke-virtual {v0, v1, p2}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setText(Ljava/lang/CharSequence;Z)V

    .line 16230
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public setEffect(J)V
    .locals 1

    .line 15855
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Lorg/telegram/messenger/MessagesController;->getEffect(J)Lorg/telegram/tgnet/TLRPC$TL_availableEffect;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 15856
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$TL_availableEffect;->emoticon:Ljava/lang/String;

    invoke-static {p1}, Lorg/telegram/messenger/Emoji;->getEmojiDrawable(Ljava/lang/CharSequence;)Lorg/telegram/messenger/Emoji$EmojiDrawable;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->setEmoji(Landroid/graphics/drawable/Drawable;)V

    return-void
.end method

.method public setEmoji(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .line 15860
    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    const/4 v0, 0x1

    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;->set(Landroid/graphics/drawable/Drawable;Z)V

    return-void
.end method

.method public setEphemeralFactor(F)V
    .locals 1

    .line 15666
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralFactor:F

    cmpl-float v0, v0, p1

    if-eqz v0, :cond_0

    .line 15667
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->ephemeralFactor:F

    .line 15668
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_0
    return-void
.end method

.method public setLoading(ZF)V
    .locals 6

    .line 16216
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingShown:Z

    const/4 v1, 0x0

    const/high16 v2, -0x3fc00000    # -3.0f

    const/4 v3, 0x1

    const v4, 0x3c23d70a    # 0.01f

    if-ne v0, p1, :cond_2

    if-eqz p1, :cond_0

    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingProgress:F

    sub-float/2addr v0, p2

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    cmpg-float v0, v0, v4

    if-gez v0, :cond_2

    :cond_0
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->infiniteLoading:Z

    sub-float v5, p2, v2

    invoke-static {v5}, Ljava/lang/Math;->abs(F)F

    move-result v5

    cmpg-float v5, v5, v4

    if-gez v5, :cond_1

    move v5, v3

    goto :goto_0

    :cond_1
    move v5, v1

    :goto_0
    if-ne v0, v5, :cond_2

    return-void

    :cond_2
    sub-float v0, p2, v2

    .line 16218
    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    cmpg-float v0, v0, v4

    if-gez v0, :cond_3

    move v1, v3

    :cond_3
    iput-boolean v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->infiniteLoading:Z

    .line 16219
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingShown:Z

    if-nez v0, :cond_4

    if-eqz p1, :cond_4

    .line 16220
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingAnimatedProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    const/4 v1, 0x0

    invoke-virtual {v0, v1, v3}, Lorg/telegram/ui/Components/AnimatedFloat;->set(FZ)F

    .line 16222
    :cond_4
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingAnimatedShown:Lorg/telegram/ui/Components/AnimatedFloat;

    const/high16 v1, 0x3f800000    # 1.0f

    if-eqz p1, :cond_5

    invoke-virtual {v0}, Lorg/telegram/ui/Components/AnimatedFloat;->get()F

    move-result v2

    cmpl-float v2, v2, v1

    if-ltz v2, :cond_5

    const-wide/16 v2, 0x28a

    goto :goto_1

    :cond_5
    const-wide/16 v2, 0x0

    :goto_1
    invoke-virtual {v0, v2, v3}, Lorg/telegram/ui/Components/AnimatedFloat;->setDelay(J)V

    .line 16223
    iput-boolean p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingShown:Z

    if-nez p1, :cond_6

    move p2, v1

    .line 16224
    :cond_6
    iput p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->loadingProgress:F

    .line 16225
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public setLocked(Z)V
    .locals 1

    .line 15716
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->locked:Z

    if-ne v0, p1, :cond_0

    return-void

    .line 15717
    :cond_0
    iput-boolean p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->locked:Z

    .line 15718
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public setPressed(Z)V
    .locals 0

    .line 15908
    invoke-super {p0, p1}, Landroid/view/View;->setPressed(Z)V

    .line 15909
    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->bounce:Lorg/telegram/ui/Components/ButtonBounce;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/ButtonBounce;->setPressed(Z)V

    return-void
.end method

.method public setResourceId(I)V
    .locals 1

    .line 15722
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resId:I

    if-eq v0, p1, :cond_0

    .line 15723
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resId:I

    .line 15724
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawable:Landroid/graphics/drawable/Drawable;

    .line 15725
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->inactiveDrawable:Landroid/graphics/drawable/Drawable;

    .line 15726
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    const/4 p1, 0x0

    .line 15727
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableColor:I

    .line 15728
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_0
    return-void
.end method

.method public setSameWidthFactor(F)V
    .locals 1

    .line 15673
    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->sameWidthFactor:F

    cmpl-float v0, v0, p1

    if-eqz v0, :cond_0

    .line 15674
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->sameWidthFactor:F

    .line 15675
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_0
    return-void
.end method

.method public setScrimViewBackgroundColor(I)V
    .locals 0

    .line 15768
    iput p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundColor:I

    .line 15769
    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->scrimViewBackgroundPaint:Landroid/graphics/Paint;

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method

.method public setStarsPrice(JI)V
    .locals 1

    const/4 v0, 0x1

    .line 15831
    invoke-virtual {p0, p1, p2, p3, v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->setStarsPrice(JIZ)V

    return-void
.end method

.method public setStarsPrice(JIZ)V
    .locals 6

    .line 15834
    iget-wide v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    cmp-long v0, v0, p1

    if-nez v0, :cond_0

    iget v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->messagesCount:I

    if-ne v0, p3, :cond_0

    return-void

    .line 15835
    :cond_0
    iput-wide p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    .line 15836
    iput p3, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->messagesCount:I

    const-wide/16 v0, 0x0

    cmp-long p3, p1, v0

    .line 15840
    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    const/4 v3, 0x1

    if-lez p3, :cond_1

    .line 15838
    new-instance p3, Ljava/lang/StringBuilder;

    const-string/jumbo v4, "\u2b50\ufe0f"

    invoke-direct {p3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v4, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->messagesCount:I

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v4

    int-to-long v4, v4

    mul-long/2addr p1, v4

    const/16 v4, 0x2c

    invoke-static {p1, p2, v4}, Lorg/telegram/messenger/LocaleController;->formatNumber(JC)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iget-object p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->spans:[Lorg/telegram/ui/Components/ColoredImageSpan;

    invoke-static {p1, p2}, Lorg/telegram/ui/Stars/StarsIntroActivity;->replaceStars(Ljava/lang/CharSequence;[Lorg/telegram/ui/Components/ColoredImageSpan;)Landroid/text/SpannableStringBuilder;

    move-result-object p1

    invoke-virtual {v2, p1, p4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setText(Ljava/lang/CharSequence;Z)V

    goto :goto_0

    .line 15840
    :cond_1
    const-string p1, ""

    invoke-virtual {v2, p1, p4}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setText(Ljava/lang/CharSequence;Z)V

    :goto_0
    if-nez p4, :cond_3

    .line 15843
    iget-object p1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->animatedPriceVisible:Lorg/telegram/ui/Components/AnimatedFloat;

    iget-wide p2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    cmp-long p0, p2, v0

    if-lez p0, :cond_2

    goto :goto_1

    :cond_2
    const/4 v3, 0x0

    :goto_1
    invoke-virtual {p1, v3}, Lorg/telegram/ui/Components/AnimatedFloat;->force(Z)V

    return-void

    .line 15845
    :cond_3
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public shouldDrawBackground()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public updateColors()V
    .locals 8

    .line 16194
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    const/4 v1, -0x1

    if-eqz v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeMonet(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chats_actionIcon:I

    :goto_0
    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    goto :goto_1

    :cond_0
    move v0, v1

    goto :goto_1

    :cond_1
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelSend:I

    goto :goto_0

    .line 16195
    :goto_1
    iget v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableColor:I

    if-eq v0, v2, :cond_2

    .line 16196
    iput v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableColor:I

    .line 16197
    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawable:Landroid/graphics/drawable/Drawable;

    new-instance v3, Landroid/graphics/PorterDuffColorFilter;

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v3, v0, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 16198
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_glass_defaultIcon:I

    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 16199
    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->inactiveDrawable:Landroid/graphics/drawable/Drawable;

    new-instance v3, Landroid/graphics/PorterDuffColorFilter;

    invoke-static {v0}, Landroid/graphics/Color;->red(I)I

    move-result v5

    invoke-static {v0}, Landroid/graphics/Color;->green(I)I

    move-result v6

    invoke-static {v0}, Landroid/graphics/Color;->blue(I)I

    move-result v0

    const/16 v7, 0xb4

    invoke-static {v7, v5, v6, v0}, Landroid/graphics/Color;->argb(IIII)I

    move-result v0

    invoke-direct {v3, v0, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 16200
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->drawableInverse:Landroid/graphics/drawable/Drawable;

    new-instance v2, Landroid/graphics/PorterDuffColorFilter;

    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelVoicePressed:I

    iget-object v5, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v3, v5}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v3

    invoke-direct {v2, v3, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v2}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 16202
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeMonet(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result v0

    if-eqz v0, :cond_3

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelBackground:I

    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    goto :goto_2

    :cond_3
    move v0, v1

    .line 16203
    :goto_2
    iget v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countColor:I

    if-eq v0, v2, :cond_4

    .line 16204
    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    iput v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->countColor:I

    invoke-virtual {v2, v0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->setTextColor(I)V

    .line 16206
    :cond_4
    iget-boolean v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-eqz v0, :cond_5

    .line 16207
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelSend:I

    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    invoke-virtual {v0, p0}, Landroid/graphics/Paint;->setColor(I)V

    return-void

    .line 16208
    :cond_5
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->shouldDrawBackground()Z

    move-result v0

    .line 16211
    iget-object v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->backgroundPaint:Landroid/graphics/Paint;

    if-eqz v0, :cond_6

    .line 16209
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getFillColor()I

    move-result p0

    invoke-virtual {v2, p0}, Landroid/graphics/Paint;->setColor(I)V

    return-void

    :cond_6
    const/16 p0, 0x4b

    .line 16211
    invoke-static {v1, p0}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result p0

    invoke-virtual {v2, p0}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method

.method public verifyDrawable(Landroid/graphics/drawable/Drawable;)Z
    .locals 1

    .line 15851
    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->count:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    if-eq p1, v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->emojiDrawable:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    if-eq p1, v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    if-eq p1, v0, :cond_1

    invoke-super {p0, p1}, Landroid/view/View;->verifyDrawable(Landroid/graphics/drawable/Drawable;)Z

    move-result p0

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

.method public width()I
    .locals 1

    .line 16161
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->width(I)I

    move-result p0

    return p0
.end method

.method public width(I)I
    .locals 6

    .line 16165
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isOpen()Z

    move-result p1

    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    if-eqz p1, :cond_0

    move p1, v1

    goto :goto_0

    :cond_0
    move p1, v0

    .line 16166
    :goto_0
    iget-wide v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->starsPrice:J

    const-wide/16 v4, 0x0

    cmp-long v2, v2, v4

    if-lez v2, :cond_1

    move v0, v1

    .line 16167
    :cond_1
    iget v1, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->getCircleWidth()I

    move-result v2

    int-to-float v2, v2

    add-float/2addr v1, v2

    iget v2, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->circlePadX:F

    add-float/2addr v1, v2

    const/high16 v2, 0x41900000    # 18.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    iget-boolean v3, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->isNewDesignSendButton:Z

    if-eqz v3, :cond_2

    const/high16 v3, 0x41a00000    # 20.0f

    goto :goto_1

    :cond_2
    const/high16 v3, 0x41b00000    # 22.0f

    :goto_1
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    add-int/2addr v2, v3

    int-to-float v2, v2

    iget-object p0, p0, Lorg/telegram/ui/Components/ChatActivityEnterView$SendButton;->priceText:Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/AnimatedTextView$AnimatedTextDrawable;->getAnimateToWidth()F

    move-result p0

    add-float/2addr v2, p0

    mul-float/2addr v0, p1

    invoke-static {v1, v2, v0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result p0

    float-to-int p0, p0

    return p0
.end method
