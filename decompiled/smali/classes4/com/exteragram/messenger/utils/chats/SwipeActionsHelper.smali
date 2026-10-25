.class public Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final actions:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/utils/chats/SwipeAction;",
            ">;"
        }
    .end annotation
.end field

.field private anchorY:F

.field private animatedReaction:Lorg/telegram/ui/Components/AnimatedEmojiDrawable;

.field private animatedReactionId:J

.field private armed:Z

.field private beyondMax:Z

.field private direction:I

.field private driftX:F

.field private driftY:F

.field private final fill:Landroidx/dynamicanimation/animation/FloatValueHolder;

.field private final fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

.field private frameDark:Z

.field private frameGradient:Z

.field private framePaint:Landroid/graphics/Paint;

.field private iconFilter:Landroid/graphics/ColorFilter;

.field private iconFilterColor:I

.field private final iconFits:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/lang/Float;",
            ">;"
        }
    .end annotation
.end field

.field private final icons:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Landroid/graphics/drawable/Drawable;",
            ">;"
        }
    .end annotation
.end field

.field private lastX:F

.field private lastY:F

.field private final liveAlpha:Lorg/telegram/ui/Components/AnimatedFloat;

.field private looped:Z

.field private final outlineDarkenPaint:Landroid/graphics/Paint;

.field private final outlinePaint:Landroid/graphics/Paint;

.field private final parent:Landroid/view/View;

.field private final path:Landroid/graphics/Path;

.field private final position:Landroidx/dynamicanimation/animation/FloatValueHolder;

.field private final positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

.field private reactionDrawable:Landroid/graphics/drawable/Drawable;

.field private reactionEmojicon:Ljava/lang/String;

.field private reactionImage:Lorg/telegram/messenger/ImageReceiver;

.field private final rect:Landroid/graphics/RectF;

.field private final resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private reversed:Z

.field private final ring:Landroidx/dynamicanimation/animation/FloatValueHolder;

.field private final ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

.field private scrubbing:Z

.field private selected:I

.field private slot:I

.field private final spareIcons:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Landroid/graphics/drawable/Drawable;",
            ">;"
        }
    .end annotation
.end field

.field private stepped:Z

.field private final visibility:Landroidx/dynamicanimation/animation/FloatValueHolder;

.field private final visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;


# direct methods
.method public static synthetic $r8$lambda$6rKofKNhh3SqEEW6TezwfYSOqJM(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lambda$new$3(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V

    return-void
.end method

.method public static synthetic $r8$lambda$JsLMCmaHE9vX3wrjbliyiSM3az8(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lambda$new$1(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V

    return-void
.end method

.method public static synthetic $r8$lambda$Qe38TqhTYtY1s_7QmxUEWXLcMa8(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    return-void
.end method

.method public static synthetic $r8$lambda$QrpxjI26AgLoqmKWeow1pyk3nhY(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lambda$new$2(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V

    return-void
.end method

.method public static synthetic $r8$lambda$v3p5mZqsBD9EHenLBH23tcOcdHU(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lambda$new$0(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V

    return-void
.end method

.method public constructor <init>(Landroid/view/View;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 10

    .line 146
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 74
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    .line 75
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->icons:Landroid/util/SparseArray;

    .line 76
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->spareIcons:Landroid/util/SparseArray;

    .line 77
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFits:Landroid/util/SparseArray;

    .line 79
    new-instance v0, Landroid/graphics/Paint;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    .line 80
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    .line 81
    new-instance v1, Landroid/graphics/Path;

    invoke-direct {v1}, Landroid/graphics/Path;-><init>()V

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    .line 82
    new-instance v1, Landroid/graphics/RectF;

    invoke-direct {v1}, Landroid/graphics/RectF;-><init>()V

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    .line 97
    new-instance v3, Lorg/telegram/ui/Components/AnimatedFloat;

    new-instance v4, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda0;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;)V

    const-wide/16 v7, 0x118

    sget-object v9, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const-wide/16 v5, 0x0

    invoke-direct/range {v3 .. v9}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Ljava/lang/Runnable;JJLandroid/animation/TimeInterpolator;)V

    iput-object v3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->liveAlpha:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 99
    new-instance v1, Landroidx/dynamicanimation/animation/FloatValueHolder;

    const/4 v3, 0x0

    invoke-direct {v1, v3}, Landroidx/dynamicanimation/animation/FloatValueHolder;-><init>(F)V

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->position:Landroidx/dynamicanimation/animation/FloatValueHolder;

    .line 100
    new-instance v4, Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-direct {v4, v1}, Landroidx/dynamicanimation/animation/SpringAnimation;-><init>(Landroidx/dynamicanimation/animation/FloatValueHolder;)V

    new-instance v1, Landroidx/dynamicanimation/animation/SpringForce;

    invoke-direct {v1, v3}, Landroidx/dynamicanimation/animation/SpringForce;-><init>(F)V

    const/high16 v5, 0x43d70000    # 430.0f

    .line 102
    invoke-virtual {v1, v5}, Landroidx/dynamicanimation/animation/SpringForce;->setStiffness(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v1

    const v5, 0x3f0ccccd    # 0.55f

    .line 103
    invoke-virtual {v1, v5}, Landroidx/dynamicanimation/animation/SpringForce;->setDampingRatio(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v1

    .line 101
    invoke-virtual {v4, v1}, Landroidx/dynamicanimation/animation/SpringAnimation;->setSpring(Landroidx/dynamicanimation/animation/SpringForce;)Landroidx/dynamicanimation/animation/SpringAnimation;

    move-result-object v1

    new-instance v4, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda1;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;)V

    .line 104
    invoke-virtual {v1, v4}, Landroidx/dynamicanimation/animation/DynamicAnimation;->addUpdateListener(Landroidx/dynamicanimation/animation/DynamicAnimation$OnAnimationUpdateListener;)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    .line 106
    new-instance v1, Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-direct {v1, v3}, Landroidx/dynamicanimation/animation/FloatValueHolder;-><init>(F)V

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibility:Landroidx/dynamicanimation/animation/FloatValueHolder;

    .line 107
    new-instance v4, Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-direct {v4, v1}, Landroidx/dynamicanimation/animation/SpringAnimation;-><init>(Landroidx/dynamicanimation/animation/FloatValueHolder;)V

    .line 108
    invoke-virtual {v4, v3}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setMinValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    const/high16 v4, 0x44fa0000    # 2000.0f

    .line 109
    invoke-virtual {v1, v4}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setMaxValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    new-instance v4, Landroidx/dynamicanimation/animation/SpringForce;

    invoke-direct {v4, v3}, Landroidx/dynamicanimation/animation/SpringForce;-><init>(F)V

    const v5, 0x44bb8000    # 1500.0f

    .line 111
    invoke-virtual {v4, v5}, Landroidx/dynamicanimation/animation/SpringForce;->setStiffness(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v4

    const/high16 v5, 0x3f800000    # 1.0f

    .line 112
    invoke-virtual {v4, v5}, Landroidx/dynamicanimation/animation/SpringForce;->setDampingRatio(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v4

    .line 110
    invoke-virtual {v1, v4}, Landroidx/dynamicanimation/animation/SpringAnimation;->setSpring(Landroidx/dynamicanimation/animation/SpringForce;)Landroidx/dynamicanimation/animation/SpringAnimation;

    move-result-object v1

    new-instance v4, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda2;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;)V

    .line 113
    invoke-virtual {v1, v4}, Landroidx/dynamicanimation/animation/DynamicAnimation;->addUpdateListener(Landroidx/dynamicanimation/animation/DynamicAnimation$OnAnimationUpdateListener;)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    .line 115
    new-instance v1, Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-direct {v1, v3}, Landroidx/dynamicanimation/animation/FloatValueHolder;-><init>(F)V

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fill:Landroidx/dynamicanimation/animation/FloatValueHolder;

    .line 116
    new-instance v4, Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-direct {v4, v1}, Landroidx/dynamicanimation/animation/SpringAnimation;-><init>(Landroidx/dynamicanimation/animation/FloatValueHolder;)V

    .line 117
    invoke-virtual {v4, v3}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setMinValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    new-instance v4, Landroidx/dynamicanimation/animation/SpringForce;

    invoke-direct {v4, v3}, Landroidx/dynamicanimation/animation/SpringForce;-><init>(F)V

    const/high16 v6, 0x43c80000    # 400.0f

    .line 119
    invoke-virtual {v4, v6}, Landroidx/dynamicanimation/animation/SpringForce;->setStiffness(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v4

    const/high16 v6, 0x3f000000    # 0.5f

    .line 120
    invoke-virtual {v4, v6}, Landroidx/dynamicanimation/animation/SpringForce;->setDampingRatio(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v4

    .line 118
    invoke-virtual {v1, v4}, Landroidx/dynamicanimation/animation/SpringAnimation;->setSpring(Landroidx/dynamicanimation/animation/SpringForce;)Landroidx/dynamicanimation/animation/SpringAnimation;

    move-result-object v1

    new-instance v4, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda3;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;)V

    .line 121
    invoke-virtual {v1, v4}, Landroidx/dynamicanimation/animation/DynamicAnimation;->addUpdateListener(Landroidx/dynamicanimation/animation/DynamicAnimation$OnAnimationUpdateListener;)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    .line 123
    new-instance v1, Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-direct {v1, v3}, Landroidx/dynamicanimation/animation/FloatValueHolder;-><init>(F)V

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ring:Landroidx/dynamicanimation/animation/FloatValueHolder;

    .line 124
    new-instance v4, Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-direct {v4, v1}, Landroidx/dynamicanimation/animation/SpringAnimation;-><init>(Landroidx/dynamicanimation/animation/FloatValueHolder;)V

    .line 125
    invoke-virtual {v4, v3}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setMinValue(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    new-instance v4, Landroidx/dynamicanimation/animation/SpringForce;

    invoke-direct {v4, v3}, Landroidx/dynamicanimation/animation/SpringForce;-><init>(F)V

    const/high16 v3, 0x43480000    # 200.0f

    .line 127
    invoke-virtual {v4, v3}, Landroidx/dynamicanimation/animation/SpringForce;->setStiffness(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v3

    .line 128
    invoke-virtual {v3, v5}, Landroidx/dynamicanimation/animation/SpringForce;->setDampingRatio(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v3

    .line 126
    invoke-virtual {v1, v3}, Landroidx/dynamicanimation/animation/SpringAnimation;->setSpring(Landroidx/dynamicanimation/animation/SpringForce;)Landroidx/dynamicanimation/animation/SpringAnimation;

    move-result-object v1

    new-instance v3, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda4;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;)V

    .line 129
    invoke-virtual {v1, v3}, Landroidx/dynamicanimation/animation/DynamicAnimation;->addUpdateListener(Landroidx/dynamicanimation/animation/DynamicAnimation$OnAnimationUpdateListener;)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object v1

    check-cast v1, Landroidx/dynamicanimation/animation/SpringAnimation;

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    .line 147
    iput-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    .line 148
    iput-object p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 149
    sget-object p0, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, p0}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 150
    sget-object p1, Landroid/graphics/Paint$Cap;->ROUND:Landroid/graphics/Paint$Cap;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setStrokeCap(Landroid/graphics/Paint$Cap;)V

    const/high16 p2, 0x40000000    # 2.0f

    .line 151
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 152
    invoke-virtual {v2, p0}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 153
    invoke-virtual {v2, p1}, Landroid/graphics/Paint;->setStrokeCap(Landroid/graphics/Paint$Cap;)V

    .line 154
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    int-to-float p0, p0

    invoke-virtual {v2, p0}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    return-void
.end method

.method private applyServiceShaderMatrix()V
    .locals 4

    .line 593
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    .line 594
    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getY()F

    move-result v1

    iget-object v2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    iget v2, v2, Landroid/graphics/RectF;->top:F

    add-float/2addr v1, v2

    .line 595
    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v2, 0x0

    if-eqz p0, :cond_0

    .line 596
    sget-object v3, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v3, v3, Landroid/graphics/Point;->y:I

    invoke-interface {p0, v0, v3, v2, v1}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->applyServiceShaderMatrix(IIFF)V

    return-void

    .line 598
    :cond_0
    sget-object p0, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget p0, p0, Landroid/graphics/Point;->y:I

    invoke-static {v0, p0, v2, v1}, Lorg/telegram/ui/ActionBar/Theme;->applyServiceShaderMatrix(IIFF)V

    return-void
.end method

.method private chooseDirection(F)I
    .locals 3

    const/high16 v0, 0x42300000    # 44.0f

    .line 293
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x2

    const/high16 v2, 0x42100000    # 36.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    mul-int/2addr v1, v2

    add-int/2addr v0, v1

    int-to-float v0, v0

    .line 294
    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result p0

    int-to-float p0, p0

    sub-float/2addr p0, p1

    cmpl-float v0, p0, v0

    if-gez v0, :cond_1

    cmpl-float p0, p0, p1

    if-ltz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, -0x1

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private drawBubble(Landroid/graphics/Canvas;FFFFFFFFIZ)V
    .locals 21

    move-object/from16 v0, p0

    move/from16 v7, p2

    move/from16 v8, p3

    move/from16 v1, p9

    move/from16 v9, p10

    const/high16 v10, 0x40000000    # 2.0f

    cmpl-float v2, p8, v10

    if-ltz v2, :cond_0

    goto/16 :goto_4

    :cond_0
    const/high16 v11, 0x3f800000    # 1.0f

    sub-float v2, v11, p8

    const/4 v12, 0x0

    .line 438
    invoke-static {v12, v2}, Ljava/lang/Math;->max(FF)F

    move-result v2

    sub-float v3, v10, p8

    const v4, 0x3ee66666    # 0.45f

    mul-float/2addr v3, v4

    .line 439
    invoke-static {v2, v3}, Ljava/lang/Math;->max(FF)F

    move-result v3

    const v4, 0x3e99999a    # 0.3f

    mul-float/2addr v2, v4

    const v4, 0x3f333333    # 0.7f

    add-float/2addr v2, v4

    .line 440
    sget-object v4, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_BACK:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v4, v1}, Lorg/telegram/ui/Components/CubicBezierInterpolator;->getInterpolation(F)F

    move-result v4

    const v5, 0x3e19999a    # 0.15f

    mul-float/2addr v4, v5

    const v5, 0x3f59999a    # 0.85f

    add-float/2addr v4, v5

    mul-float/2addr v2, v4

    mul-float v13, p4, v2

    mul-float v14, p5, v2

    mul-float/2addr v3, v1

    mul-float v15, p6, v3

    cmpg-float v1, v15, v12

    if-gtz v1, :cond_1

    goto/16 :goto_4

    .line 448
    :cond_1
    iget-object v1, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->framePaint:Landroid/graphics/Paint;

    .line 449
    sget-object v2, Lorg/telegram/ui/ActionBar/Theme;->chat_actionBackgroundGradientDarkenPaint:Landroid/graphics/Paint;

    .line 450
    iget-boolean v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->frameGradient:Z

    .line 451
    iget-boolean v4, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->frameDark:Z

    const/high16 v5, 0x41800000    # 16.0f

    .line 452
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    int-to-float v5, v5

    if-eqz p11, :cond_4

    cmpl-float v16, p7, v12

    if-lez v16, :cond_4

    .line 454
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fill:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v6}, Landroidx/dynamicanimation/animation/FloatValueHolder;->getValue()F

    move-result v6

    cmpl-float v6, v6, v12

    if-nez v6, :cond_4

    .line 455
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v6}, Landroid/graphics/Paint;->getStrokeWidth()F

    move-result v6

    div-float/2addr v6, v10

    mul-float v16, v5, v13

    sub-float v6, v16, v6

    .line 456
    invoke-direct {v0, v7, v8, v6}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->setRect(FFF)V

    .line 457
    invoke-direct {v0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->applyServiceShaderMatrix()V

    .line 458
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v6}, Landroid/graphics/Paint;->getAlpha()I

    move-result v6

    move/from16 v16, v10

    .line 459
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    move/from16 v17, v11

    int-to-float v11, v6

    mul-float/2addr v11, v15

    float-to-int v11, v11

    invoke-virtual {v10, v11}, Landroid/graphics/Paint;->setAlpha(I)V

    move-object v10, v2

    .line 460
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    const/high16 v11, 0x43b40000    # 360.0f

    mul-float v11, v11, p7

    move/from16 v18, v5

    const/4 v5, 0x0

    move/from16 v19, v6

    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    move/from16 v20, v3

    const/high16 v3, -0x3d4c0000    # -90.0f

    move/from16 p4, v13

    move/from16 p5, v14

    move/from16 v13, v19

    const/4 v14, -0x1

    move/from16 v19, v12

    move/from16 v12, v18

    move/from16 v18, v4

    move v4, v11

    move-object v11, v10

    move-object v10, v1

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 461
    iget-object v1, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v13}, Landroid/graphics/Paint;->setAlpha(I)V

    if-eqz v20, :cond_3

    .line 463
    iget-object v1, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v1}, Landroid/graphics/Paint;->getAlpha()I

    move-result v1

    if-eqz v18, :cond_2

    .line 465
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v14}, Landroid/graphics/Paint;->setColor(I)V

    .line 467
    :cond_2
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    int-to-float v1, v1

    mul-float/2addr v1, v15

    float-to-int v1, v1

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 468
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    const/4 v5, 0x0

    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    const/high16 v3, -0x3d4c0000    # -90.0f

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    goto :goto_1

    :cond_3
    :goto_0
    move-object/from16 v1, p1

    goto :goto_1

    :cond_4
    move/from16 v20, v3

    move/from16 v18, v4

    move/from16 v16, v10

    move/from16 v17, v11

    move/from16 v19, v12

    move/from16 p4, v13

    move/from16 p5, v14

    const/4 v14, -0x1

    move-object v10, v1

    move-object v11, v2

    move v12, v5

    goto :goto_0

    :goto_1
    mul-float v5, v12, p4

    .line 472
    invoke-direct {v0, v7, v8, v5}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->setRect(FFF)V

    .line 473
    invoke-direct {v0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->applyServiceShaderMatrix()V

    .line 474
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v2}, Landroid/graphics/Path;->rewind()V

    .line 475
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    sget-object v4, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v2, v3, v5, v5, v4}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 477
    invoke-virtual {v10}, Landroid/graphics/Paint;->getAlpha()I

    move-result v2

    const v3, 0x3f19999a    # 0.6f

    mul-float/2addr v3, v15

    mul-float v3, v3, p7

    int-to-float v6, v2

    mul-float/2addr v6, v3

    float-to-int v6, v6

    .line 478
    invoke-virtual {v10, v6}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 479
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v1, v6, v10}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 480
    invoke-virtual {v10, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    if-eqz v20, :cond_6

    .line 483
    invoke-virtual {v11}, Landroid/graphics/Paint;->getAlpha()I

    move-result v2

    if-eqz v18, :cond_5

    .line 485
    invoke-virtual {v11, v14}, Landroid/graphics/Paint;->setColor(I)V

    :cond_5
    int-to-float v6, v2

    mul-float/2addr v3, v6

    float-to-int v3, v3

    .line 487
    invoke-virtual {v11, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 488
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v1, v3, v11}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 489
    invoke-virtual {v11, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    :cond_6
    cmpl-float v2, p5, v19

    if-eqz v2, :cond_7

    mul-float v3, v12, p5

    .line 493
    invoke-direct {v0, v7, v8, v3}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->setRect(FFF)V

    .line 494
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v3}, Landroid/graphics/Path;->rewind()V

    .line 495
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    invoke-virtual {v3, v6, v12, v12, v4}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 496
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 497
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    sget-object v6, Landroid/graphics/Region$Op;->DIFFERENCE:Landroid/graphics/Region$Op;

    invoke-virtual {v1, v3, v6}, Landroid/graphics/Canvas;->clipPath(Landroid/graphics/Path;Landroid/graphics/Region$Op;)Z

    .line 500
    :cond_7
    invoke-direct {v0, v7, v8, v5}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->setRect(FFF)V

    .line 501
    invoke-direct {v0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->applyServiceShaderMatrix()V

    .line 502
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v3}, Landroid/graphics/Path;->rewind()V

    .line 503
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    invoke-virtual {v3, v6, v5, v5, v4}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 505
    invoke-virtual {v10}, Landroid/graphics/Paint;->getAlpha()I

    move-result v3

    const v5, 0x3ecccccd    # 0.4f

    mul-float/2addr v5, v15

    int-to-float v6, v3

    mul-float/2addr v6, v5

    float-to-int v6, v6

    .line 506
    invoke-virtual {v10, v6}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 507
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v1, v6, v10}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 508
    invoke-virtual {v10, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    if-eqz v20, :cond_9

    .line 511
    invoke-virtual {v11}, Landroid/graphics/Paint;->getAlpha()I

    move-result v3

    if-eqz v18, :cond_8

    .line 513
    invoke-virtual {v11, v14}, Landroid/graphics/Paint;->setColor(I)V

    :cond_8
    int-to-float v6, v3

    mul-float/2addr v5, v6

    float-to-int v5, v5

    .line 515
    invoke-virtual {v11, v5}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 516
    iget-object v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v1, v5, v11}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 517
    invoke-virtual {v11, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    :cond_9
    if-eqz v2, :cond_a

    .line 521
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 524
    :cond_a
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ring:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v2}, Landroidx/dynamicanimation/animation/FloatValueHolder;->getValue()F

    move-result v2

    const/high16 v3, 0x44fa0000    # 2000.0f

    div-float/2addr v2, v3

    if-eqz p11, :cond_c

    cmpl-float v3, v2, v19

    if-eqz v3, :cond_c

    cmpl-float v3, v2, v17

    if-eqz v3, :cond_c

    add-float v11, v2, v17

    .line 527
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v3}, Landroid/graphics/Paint;->getStrokeWidth()F

    move-result v3

    sub-float v2, v17, v2

    mul-float/2addr v2, v3

    cmpl-float v5, v2, v19

    if-eqz v5, :cond_c

    mul-float v5, v12, v11

    sub-float v6, v5, v2

    .line 530
    invoke-direct {v0, v7, v8, v6}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->setRect(FFF)V

    .line 531
    invoke-direct {v0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->applyServiceShaderMatrix()V

    .line 532
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v6}, Landroid/graphics/Paint;->getAlpha()I

    move-result v6

    .line 533
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    int-to-float v11, v6

    mul-float/2addr v11, v15

    float-to-int v11, v11

    invoke-virtual {v10, v11}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 534
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v10, v2}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 535
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    iget-object v11, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v10, v5, v5, v11}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 536
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v10, v3}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 537
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v10, v6}, Landroid/graphics/Paint;->setAlpha(I)V

    if-eqz v20, :cond_c

    .line 539
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v6}, Landroid/graphics/Paint;->getAlpha()I

    move-result v6

    if-eqz v18, :cond_b

    .line 541
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v10, v14}, Landroid/graphics/Paint;->setColor(I)V

    .line 543
    :cond_b
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    int-to-float v6, v6

    mul-float/2addr v6, v15

    float-to-int v6, v6

    invoke-virtual {v10, v6}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 544
    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v6, v2}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 545
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v5, v5, v6}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 546
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 551
    :cond_c
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v3

    invoke-static {v9, v3}, Ljava/lang/Math;->floorMod(II)I

    move-result v3

    invoke-interface {v2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/utils/chats/SwipeAction;

    .line 552
    sget-object v3, Lcom/exteragram/messenger/utils/chats/SwipeAction;->REACTION:Lcom/exteragram/messenger/utils/chats/SwipeAction;

    const/high16 v5, 0x41a00000    # 20.0f

    if-ne v2, v3, :cond_d

    iget-object v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    if-eqz v6, :cond_d

    .line 553
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    div-float v2, v2, v16

    mul-float v2, v2, p4

    .line 554
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    sub-float v4, v7, v2

    sub-float v5, v8, v2

    mul-float v2, v2, v16

    invoke-virtual {v3, v4, v5, v2, v2}, Lorg/telegram/messenger/ImageReceiver;->setImageCoords(FFFF)V

    .line 555
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v2, v15}, Lorg/telegram/messenger/ImageReceiver;->setAlpha(F)V

    .line 556
    iget-object v0, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/ImageReceiver;->draw(Landroid/graphics/Canvas;)Z

    return-void

    :cond_d
    const/high16 v6, 0x437f0000    # 255.0f

    if-ne v2, v3, :cond_e

    .line 559
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v3, :cond_e

    .line 560
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    div-float v2, v2, v16

    mul-float v2, v2, p4

    .line 561
    invoke-direct {v0, v7, v8, v2}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->setRect(FFF)V

    .line 562
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v3}, Landroid/graphics/Path;->rewind()V

    .line 563
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    iget-object v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    const v9, 0x3eb33333    # 0.35f

    mul-float/2addr v9, v2

    invoke-virtual {v3, v5, v9, v9, v4}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 564
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 565
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->path:Landroid/graphics/Path;

    invoke-virtual {v1, v3}, Landroid/graphics/Canvas;->clipPath(Landroid/graphics/Path;)Z

    .line 566
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    mul-float/2addr v15, v6

    float-to-int v4, v15

    invoke-virtual {v3, v4}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 567
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    sub-float v4, v7, v2

    float-to-int v4, v4

    sub-float v5, v8, v2

    float-to-int v5, v5

    add-float v6, v7, v2

    float-to-int v6, v6

    add-float/2addr v2, v8

    float-to-int v2, v2

    invoke-virtual {v3, v4, v5, v6, v2}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 568
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 569
    iget-object v0, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    const/16 v2, 0xff

    invoke-virtual {v0, v2}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 570
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    return-void

    .line 573
    :cond_e
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result v3

    const/4 v4, 0x0

    const/4 v5, 0x1

    if-ne v3, v5, :cond_f

    sget-object v3, Lcom/exteragram/messenger/utils/chats/SwipeAction;->REPLY:Lcom/exteragram/messenger/utils/chats/SwipeAction;

    if-ne v2, v3, :cond_f

    move v3, v5

    goto :goto_2

    :cond_f
    move v3, v4

    :goto_2
    if-eqz v3, :cond_10

    .line 574
    const-string v4, "drawableReplyIcon"

    iget-object v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v4, v5}, Lorg/telegram/ui/ActionBar/Theme;->getThemeDrawable(Ljava/lang/String;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    goto :goto_3

    :cond_10
    iget-object v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v10}, Ljava/util/List;->size()I

    move-result v10

    invoke-static {v9, v10}, Ljava/lang/Math;->floorDiv(II)I

    move-result v9

    and-int/2addr v9, v5

    if-eqz v9, :cond_11

    move v4, v5

    :cond_11
    invoke-direct {v0, v2, v4}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->getIcon(Lcom/exteragram/messenger/utils/chats/SwipeAction;Z)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    :goto_3
    if-nez v4, :cond_12

    :goto_4
    return-void

    .line 578
    :cond_12
    iget-object v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFits:Landroid/util/SparseArray;

    iget v9, v2, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconRes:I

    invoke-virtual {v5, v9}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Float;

    if-eqz v3, :cond_13

    move/from16 v13, p4

    goto :goto_6

    .line 579
    :cond_13
    iget v2, v2, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconTrim:F

    mul-float v13, p4, v2

    if-eqz v5, :cond_14

    invoke-virtual {v5}, Ljava/lang/Float;->floatValue()F

    move-result v11

    goto :goto_5

    :cond_14
    move/from16 v11, v17

    :goto_5
    mul-float/2addr v13, v11

    .line 580
    :goto_6
    invoke-virtual {v4}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    int-to-float v2, v2

    div-float v2, v2, v16

    mul-float/2addr v2, v13

    .line 581
    invoke-virtual {v4}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v3

    int-to-float v3, v3

    div-float v3, v3, v16

    mul-float/2addr v3, v13

    .line 582
    invoke-direct {v0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->getIconFilter()Landroid/graphics/ColorFilter;

    move-result-object v0

    invoke-virtual {v4, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    mul-float/2addr v15, v6

    float-to-int v0, v15

    .line 583
    invoke-virtual {v4, v0}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    sub-float v0, v7, v2

    float-to-int v0, v0

    sub-float v5, v8, v3

    float-to-int v5, v5

    add-float/2addr v2, v7

    float-to-int v2, v2

    add-float/2addr v3, v8

    float-to-int v3, v3

    .line 584
    invoke-virtual {v4, v0, v5, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 585
    invoke-virtual {v4, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    return-void
.end method

.method private getIcon(Lcom/exteragram/messenger/utils/chats/SwipeAction;Z)Landroid/graphics/drawable/Drawable;
    .locals 3

    if-eqz p2, :cond_0

    .line 645
    iget-object p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->spareIcons:Landroid/util/SparseArray;

    goto :goto_0

    :cond_0
    iget-object p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->icons:Landroid/util/SparseArray;

    .line 646
    :goto_0
    iget v0, p1, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconRes:I

    invoke-virtual {p2, v0}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_3

    .line 648
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    iget v1, p1, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconRes:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    if-nez v0, :cond_1

    const/4 p0, 0x0

    return-object p0

    .line 652
    :cond_1
    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFits:Landroid/util/SparseArray;

    iget v2, p1, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconRes:I

    invoke-virtual {v1, v2}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v1

    if-nez v1, :cond_2

    .line 653
    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFits:Landroid/util/SparseArray;

    iget v2, p1, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconRes:I

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->measureIconFit(Landroid/graphics/drawable/Drawable;)F

    move-result p0

    invoke-static {p0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    invoke-virtual {v1, v2, p0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 655
    :cond_2
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p0

    .line 656
    iget p1, p1, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconRes:I

    invoke-virtual {p2, p1, p0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    return-object p0

    :cond_3
    return-object v0
.end method

.method private getIconFilter()Landroid/graphics/ColorFilter;
    .locals 3

    .line 603
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_serviceIcon:I

    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 604
    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFilter:Landroid/graphics/ColorFilter;

    if-eqz v1, :cond_0

    iget v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFilterColor:I

    if-eq v1, v0, :cond_1

    .line 605
    :cond_0
    iput v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFilterColor:I

    .line 606
    new-instance v1, Landroid/graphics/PorterDuffColorFilter;

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v0, v2}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFilter:Landroid/graphics/ColorFilter;

    .line 608
    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->iconFilter:Landroid/graphics/ColorFilter;

    return-object p0
.end method

.method private invalidate()V
    .locals 0

    .line 158
    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method private synthetic lambda$new$0(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 104
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    return-void
.end method

.method private synthetic lambda$new$1(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 113
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    return-void
.end method

.method private synthetic lambda$new$2(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 121
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    return-void
.end method

.method private synthetic lambda$new$3(Landroidx/dynamicanimation/animation/DynamicAnimation;FF)V
    .locals 0

    .line 129
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    return-void
.end method

.method private measureIconFit(Landroid/graphics/drawable/Drawable;)F
    .locals 11

    .line 612
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v3

    .line 613
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v7

    const/high16 p0, 0x3f800000    # 1.0f

    if-lez v3, :cond_9

    if-gtz v7, :cond_0

    goto/16 :goto_3

    .line 617
    :cond_0
    sget-object v0, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v3, v7, v0}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    const/4 v8, 0x0

    .line 618
    invoke-virtual {p1, v8, v8, v3, v7}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 619
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    invoke-virtual {p1, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    mul-int p1, v3, v7

    .line 621
    new-array v1, p1, [I

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v2, 0x0

    move v6, v3

    .line 622
    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Bitmap;->getPixels([IIIIIII)V

    .line 623
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->recycle()V

    const/4 p1, -0x1

    move v0, p1

    move v2, v3

    move v4, v7

    move v5, v8

    :goto_0
    if-ge v5, v7, :cond_7

    move v6, v8

    :goto_1
    if-ge v6, v3, :cond_6

    mul-int v9, v5, v3

    add-int/2addr v9, v6

    .line 628
    aget v9, v1, v9

    ushr-int/lit8 v9, v9, 0x18

    const/16 v10, 0x8

    if-ge v9, v10, :cond_1

    goto :goto_2

    :cond_1
    if-ge v6, v2, :cond_2

    move v2, v6

    :cond_2
    if-le v6, p1, :cond_3

    move p1, v6

    :cond_3
    if-ge v5, v4, :cond_4

    move v4, v5

    :cond_4
    if-le v5, v0, :cond_5

    move v0, v5

    :cond_5
    :goto_2
    add-int/lit8 v6, v6, 0x1

    goto :goto_1

    :cond_6
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    :cond_7
    if-lt p1, v2, :cond_9

    if-ge v0, v4, :cond_8

    goto :goto_3

    :cond_8
    const/high16 p0, 0x41700000    # 15.0f

    .line 640
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result p0

    sub-int/2addr p1, v2

    add-int/lit8 p1, p1, 0x1

    int-to-float p1, p1

    div-float/2addr p0, p1

    const/high16 p1, 0x41840000    # 16.5f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result p1

    sub-int/2addr v0, v4

    add-int/lit8 v0, v0, 0x1

    int-to-float v0, v0

    div-float/2addr p1, v0

    invoke-static {p0, p1}, Ljava/lang/Math;->min(FF)F

    move-result p0

    :cond_9
    :goto_3
    return p0
.end method

.method private move(I)V
    .locals 2

    .line 337
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    add-int/2addr v0, p1

    iput v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    .line 338
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p1

    invoke-static {v0, p1}, Ljava/lang/Math;->floorMod(II)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    .line 340
    :try_start_0
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    const/4 v0, 0x4

    const/4 v1, 0x2

    invoke-virtual {p1, v0, v1}, Landroid/view/View;->performHapticFeedback(II)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 342
    :catch_0
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {p1}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object p1

    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    int-to-float v0, v0

    const/high16 v1, 0x44fa0000    # 2000.0f

    mul-float/2addr v0, v1

    invoke-virtual {p1, v0}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 343
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {p1}, Landroidx/dynamicanimation/animation/SpringAnimation;->start()V

    .line 344
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    return-void
.end method

.method private setRect(FFF)V
    .locals 2

    .line 589
    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->rect:Landroid/graphics/RectF;

    sub-float v0, p1, p3

    sub-float v1, p2, p3

    add-float/2addr p1, p3

    add-float/2addr p2, p3

    invoke-virtual {p0, v0, v1, p1, p2}, Landroid/graphics/RectF;->set(FFFF)V

    return-void
.end method


# virtual methods
.method public detach()V
    .locals 4

    .line 192
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->animatedReaction:Lorg/telegram/ui/Components/AnimatedEmojiDrawable;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 193
    iget-object v2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable;->removeView(Landroid/view/View;)V

    .line 194
    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->animatedReaction:Lorg/telegram/ui/Components/AnimatedEmojiDrawable;

    const-wide/16 v2, 0x0

    .line 195
    iput-wide v2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->animatedReactionId:J

    .line 197
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    if-eqz v0, :cond_1

    .line 198
    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->onDetachedFromWindow()V

    .line 199
    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    .line 200
    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionEmojicon:Ljava/lang/String;

    .line 202
    :cond_1
    iput-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    return-void
.end method

.method public draw(Landroid/graphics/Canvas;FZFF)V
    .locals 17

    move-object/from16 v0, p0

    move/from16 v1, p2

    move/from16 v12, p4

    .line 348
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_16

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v3

    invoke-virtual {v3}, Landroid/os/Looper;->getThread()Ljava/lang/Thread;

    move-result-object v3

    if-eq v2, v3, :cond_0

    goto/16 :goto_c

    .line 351
    :cond_0
    iget v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->direction:I

    if-nez v2, :cond_1

    .line 352
    invoke-direct {v0, v12}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->chooseDirection(F)I

    move-result v2

    iput v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->direction:I

    .line 354
    :cond_1
    const-string v2, "paintChatActionBackground"

    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getThemePaint(Ljava/lang/String;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Landroid/graphics/Paint;

    move-result-object v2

    .line 355
    sget-object v13, Lorg/telegram/ui/ActionBar/Theme;->chat_actionBackgroundGradientDarkenPaint:Landroid/graphics/Paint;

    .line 356
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v3}, Landroid/graphics/Paint;->getColor()I

    move-result v3

    invoke-virtual {v2}, Landroid/graphics/Paint;->getColor()I

    move-result v4

    if-eq v3, v4, :cond_2

    .line 357
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v2}, Landroid/graphics/Paint;->getColor()I

    move-result v4

    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setColor(I)V

    .line 359
    :cond_2
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v3}, Landroid/graphics/Paint;->getColor()I

    move-result v3

    invoke-virtual {v13}, Landroid/graphics/Paint;->getColor()I

    move-result v4

    if-eq v3, v4, :cond_3

    .line 360
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v13}, Landroid/graphics/Paint;->getColor()I

    move-result v4

    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setColor(I)V

    .line 362
    :cond_3
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v3}, Landroid/graphics/Paint;->getShader()Landroid/graphics/Shader;

    move-result-object v3

    invoke-virtual {v2}, Landroid/graphics/Paint;->getShader()Landroid/graphics/Shader;

    move-result-object v4

    if-eq v3, v4, :cond_4

    .line 363
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlinePaint:Landroid/graphics/Paint;

    invoke-virtual {v2}, Landroid/graphics/Paint;->getShader()Landroid/graphics/Shader;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 365
    :cond_4
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v3}, Landroid/graphics/Paint;->getShader()Landroid/graphics/Shader;

    move-result-object v3

    invoke-virtual {v13}, Landroid/graphics/Paint;->getShader()Landroid/graphics/Shader;

    move-result-object v4

    if-eq v3, v4, :cond_5

    .line 366
    iget-object v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v13}, Landroid/graphics/Paint;->getShader()Landroid/graphics/Shader;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 369
    :cond_5
    iput-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->framePaint:Landroid/graphics/Paint;

    .line 370
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eqz v2, :cond_6

    invoke-interface {v2}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->hasGradientService()Z

    move-result v2

    if-eqz v2, :cond_6

    move v2, v4

    goto :goto_0

    :cond_6
    move v2, v3

    :goto_0
    iput-boolean v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->frameGradient:Z

    .line 371
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    iget-object v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v5}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    invoke-static {v2}, Landroidx/core/graphics/ColorUtils;->calculateLuminance(I)D

    move-result-wide v5

    const-wide/high16 v7, 0x3fe0000000000000L    # 0.5

    cmpg-double v2, v5, v7

    if-gtz v2, :cond_7

    move v2, v4

    goto :goto_1

    :cond_7
    move v2, v3

    :goto_1
    iput-boolean v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->frameDark:Z

    .line 373
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v2}, Landroid/graphics/Paint;->getColor()I

    move-result v14

    .line 374
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fill:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v2}, Landroidx/dynamicanimation/animation/FloatValueHolder;->getValue()F

    move-result v2

    const/high16 v5, 0x44fa0000    # 2000.0f

    div-float/2addr v2, v5

    const/high16 v6, 0x3f800000    # 1.0f

    cmpl-float v7, v2, v6

    if-lez v7, :cond_8

    .line 376
    iput-boolean v4, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->beyondMax:Z

    .line 378
    :cond_8
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibility:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/FloatValueHolder;->getValue()F

    move-result v7

    const/4 v8, 0x0

    cmpl-float v7, v7, v8

    if-nez v7, :cond_9

    .line 379
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->cancel()V

    .line 380
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v7

    invoke-virtual {v7, v8}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 381
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fill:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v7, v8}, Landroidx/dynamicanimation/animation/FloatValueHolder;->setValue(F)V

    .line 382
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->cancel()V

    .line 383
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v7

    invoke-virtual {v7, v8}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 384
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ring:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v7, v8}, Landroidx/dynamicanimation/animation/FloatValueHolder;->setValue(F)V

    .line 385
    iput-boolean v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->beyondMax:Z

    .line 388
    :cond_9
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v7

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringForce;->getFinalPosition()F

    move-result v7

    cmpl-float v7, v7, v5

    if-nez v7, :cond_a

    move v7, v4

    goto :goto_2

    :cond_a
    move v7, v3

    :goto_2
    const/high16 v9, 0x41a00000    # 20.0f

    if-eqz v7, :cond_b

    move v10, v6

    goto :goto_3

    :cond_b
    neg-float v10, v1

    .line 390
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v11

    int-to-float v11, v11

    sub-float/2addr v10, v11

    const/high16 v11, 0x41f00000    # 30.0f

    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v11

    int-to-float v11, v11

    div-float/2addr v10, v11

    invoke-static {v10, v8, v6}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v10

    :goto_3
    cmpl-float v11, v10, v6

    if-nez v11, :cond_c

    if-nez v7, :cond_c

    .line 392
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v7

    invoke-virtual {v7, v5}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 393
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->start()V

    .line 394
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v7

    invoke-virtual {v7, v5}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 395
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->start()V

    .line 398
    :cond_c
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    neg-int v7, v7

    int-to-float v7, v7

    cmpg-float v7, v1, v7

    if-gtz v7, :cond_d

    move v7, v5

    goto :goto_4

    :cond_d
    move v7, v8

    .line 399
    :goto_4
    iget-object v9, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v9}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v9

    invoke-virtual {v9}, Landroidx/dynamicanimation/animation/SpringForce;->getFinalPosition()F

    move-result v9

    cmpl-float v9, v7, v9

    if-eqz v9, :cond_e

    .line 400
    iget-object v9, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v9}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v9

    invoke-virtual {v9, v7}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 401
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/DynamicAnimation;->isRunning()Z

    move-result v7

    if-nez v7, :cond_e

    .line 402
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/SpringAnimation;->start()V

    .line 406
    :cond_e
    iget-object v7, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibility:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v7}, Landroidx/dynamicanimation/animation/FloatValueHolder;->getValue()F

    move-result v7

    div-float/2addr v7, v5

    .line 407
    iget-object v9, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {v9}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    int-to-float v9, v9

    if-eqz p3, :cond_f

    const/high16 v11, 0x3f000000    # 0.5f

    goto :goto_5

    :cond_f
    move v11, v6

    :goto_5
    mul-float/2addr v1, v11

    add-float/2addr v1, v9

    add-float v11, p5, v9

    const/high16 v15, 0x40000000    # 2.0f

    div-float/2addr v11, v15

    .line 409
    invoke-static {v11, v9}, Ljava/lang/Math;->min(FF)F

    move-result v9

    .line 408
    invoke-static {v1, v9}, Ljava/lang/Math;->max(FF)F

    move-result v1

    .line 410
    iget-boolean v9, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->beyondMax:Z

    move v11, v4

    if-eqz v9, :cond_10

    move v4, v2

    goto :goto_6

    :cond_10
    move v4, v7

    :goto_6
    if-eqz v9, :cond_11

    goto :goto_7

    :cond_11
    sub-float v8, v6, v2

    .line 412
    :goto_7
    iget-object v2, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->position:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v2}, Landroidx/dynamicanimation/animation/FloatValueHolder;->getValue()F

    move-result v2

    div-float v16, v2, v5

    const/high16 v2, 0x42100000    # 36.0f

    .line 413
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    iget v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->direction:I

    mul-int/2addr v2, v5

    int-to-float v2, v2

    .line 414
    iget-object v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->liveAlpha:Lorg/telegram/ui/Components/AnimatedFloat;

    iget-boolean v6, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->scrubbing:Z

    invoke-virtual {v5, v6}, Lorg/telegram/ui/Components/AnimatedFloat;->set(Z)F

    move-result v9

    .line 416
    iget-object v5, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    sub-float v6, v16, v15

    move/from16 p2, v11

    float-to-double v11, v6

    .line 417
    invoke-static {v11, v12}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v11

    double-to-int v6, v11

    add-float v15, v16, v15

    float-to-double v11, v15

    .line 418
    invoke-static {v11, v12}, Ljava/lang/Math;->floor(D)D

    move-result-wide v11

    double-to-int v11, v11

    .line 419
    iget-boolean v12, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->looped:Z

    if-eqz v12, :cond_13

    const/4 v12, 0x2

    if-ge v5, v12, :cond_12

    goto :goto_9

    :cond_12
    :goto_8
    move v12, v11

    goto :goto_a

    .line 420
    :cond_13
    :goto_9
    invoke-static {v6, v3}, Ljava/lang/Math;->max(II)I

    move-result v6

    add-int/lit8 v5, v5, -0x1

    .line 421
    invoke-static {v11, v5}, Ljava/lang/Math;->min(II)I

    move-result v11

    goto :goto_8

    .line 428
    :goto_a
    iget v3, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    if-gt v6, v12, :cond_15

    if-eq v6, v3, :cond_14

    int-to-float v3, v6

    sub-float v3, v3, v16

    mul-float v5, v3, v2

    add-float v5, p4, v5

    .line 425
    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v3

    const/4 v11, 0x0

    move v15, v8

    move v8, v3

    move v3, v5

    move v5, v15

    move v15, v10

    move v10, v6

    move v6, v7

    move v7, v15

    move v15, v2

    move v2, v1

    move-object/from16 v1, p1

    invoke-direct/range {v0 .. v11}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->drawBubble(Landroid/graphics/Canvas;FFFFFFFFIZ)V

    goto :goto_b

    :cond_14
    move v5, v10

    move v10, v6

    move v6, v7

    move v7, v5

    move v15, v2

    move v5, v8

    move v2, v1

    :goto_b
    add-int/lit8 v1, v10, 0x1

    move v8, v5

    move v10, v7

    move v7, v6

    move v6, v1

    move v1, v2

    move v2, v15

    goto :goto_a

    :cond_15
    move v15, v2

    move v6, v7

    move v5, v8

    move v7, v10

    move v2, v1

    int-to-float v1, v3

    sub-float v1, v1, v16

    mul-float/2addr v1, v15

    add-float v1, p4, v1

    int-to-float v3, v3

    sub-float v3, v3, v16

    .line 428
    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v8

    iget v10, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    const/4 v11, 0x1

    const/high16 v9, 0x3f800000    # 1.0f

    move v3, v1

    move-object/from16 v1, p1

    invoke-direct/range {v0 .. v11}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->drawBubble(Landroid/graphics/Canvas;FFFFFFFFIZ)V

    .line 430
    iget-object v0, v0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->outlineDarkenPaint:Landroid/graphics/Paint;

    invoke-virtual {v0, v14}, Landroid/graphics/Paint;->setColor(I)V

    .line 431
    invoke-virtual {v13, v14}, Landroid/graphics/Paint;->setColor(I)V

    :cond_16
    :goto_c
    return-void
.end method

.method public getSelected()Lcom/exteragram/messenger/utils/chats/SwipeAction;
    .locals 2

    .line 241
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    if-ltz v0, :cond_0

    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    iget p0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    invoke-interface {v0, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/utils/chats/SwipeAction;

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public remove(Lcom/exteragram/messenger/utils/chats/SwipeAction;)Z
    .locals 3

    .line 317
    iget-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->scrubbing:Z

    const/4 v1, 0x0

    if-nez v0, :cond_3

    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v2, 0x2

    if-ge v0, v2, :cond_0

    goto :goto_0

    .line 320
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result p1

    if-ltz p1, :cond_3

    .line 321
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    if-ne p1, v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibility:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/FloatValueHolder;->getValue()F

    move-result v0

    const/4 v2, 0x0

    cmpl-float v0, v0, v2

    if-eqz v0, :cond_1

    goto :goto_0

    .line 324
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(I)Ljava/lang/Object;

    .line 325
    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v1, 0x1

    if-lt p1, v0, :cond_2

    .line 326
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p1

    sub-int/2addr p1, v1

    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    .line 327
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    .line 328
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {p1}, Landroidx/dynamicanimation/animation/SpringAnimation;->cancel()V

    .line 329
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {p1}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object p1

    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    int-to-float v0, v0

    const/high16 v2, 0x44fa0000    # 2000.0f

    mul-float/2addr v0, v2

    invoke-virtual {p1, v0}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 330
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->position:Landroidx/dynamicanimation/animation/FloatValueHolder;

    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    int-to-float v0, v0

    mul-float/2addr v0, v2

    invoke-virtual {p1, v0}, Landroidx/dynamicanimation/animation/FloatValueHolder;->setValue(F)V

    .line 332
    :cond_2
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    :cond_3
    :goto_0
    return v1
.end method

.method public select(I)V
    .locals 2

    const/4 v0, 0x1

    .line 299
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->scrubbing:Z

    .line 300
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    if-eq p1, v0, :cond_1

    if-ltz p1, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_1

    .line 301
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    sub-int v0, p1, v0

    .line 302
    iget-boolean v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->looped:Z

    if-eqz v1, :cond_0

    .line 303
    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->floorMod(II)I

    move-result v0

    .line 304
    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    if-le v0, v1, :cond_0

    .line 305
    iget-object v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    sub-int/2addr v0, v1

    .line 308
    :cond_0
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    .line 309
    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    add-int/2addr p1, v0

    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    .line 310
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {p1}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object p1

    iget v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    int-to-float v0, v0

    const/high16 v1, 0x44fa0000    # 2000.0f

    mul-float/2addr v0, v1

    invoke-virtual {p1, v0}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 311
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {p1}, Landroidx/dynamicanimation/animation/SpringAnimation;->start()V

    .line 312
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->invalidate()V

    :cond_1
    return-void
.end method

.method public setReaction(ILjava/lang/String;J)V
    .locals 7

    const-wide/16 v0, 0x0

    cmp-long v0, p3, v0

    if-eqz v0, :cond_1

    .line 163
    iget-wide v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->animatedReactionId:J

    cmp-long p2, v0, p3

    if-eqz p2, :cond_0

    .line 164
    invoke-virtual {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->detach()V

    const/4 p2, 0x0

    .line 165
    invoke-static {p1, p2, p3, p4}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable;->make(IIJ)Lorg/telegram/ui/Components/AnimatedEmojiDrawable;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->animatedReaction:Lorg/telegram/ui/Components/AnimatedEmojiDrawable;

    .line 166
    iget-object p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-virtual {p1, p2}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable;->addView(Landroid/view/View;)V

    .line 167
    iput-wide p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->animatedReactionId:J

    .line 169
    :cond_0
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->animatedReaction:Lorg/telegram/ui/Components/AnimatedEmojiDrawable;

    iput-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    return-void

    :cond_1
    const/4 p3, 0x0

    if-eqz p2, :cond_2

    .line 173
    invoke-static {p1}, Lorg/telegram/messenger/MediaDataController;->getInstance(I)Lorg/telegram/messenger/MediaDataController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/MediaDataController;->getReactionsMap()Ljava/util/HashMap;

    move-result-object p1

    invoke-virtual {p1, p2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/tgnet/TLRPC$TL_availableReaction;

    move-object v5, p1

    goto :goto_0

    :cond_2
    move-object v5, p3

    :goto_0
    if-eqz v5, :cond_5

    .line 175
    iget-object p1, v5, Lorg/telegram/tgnet/TLRPC$TL_availableReaction;->center_icon:Lorg/telegram/tgnet/TLRPC$Document;

    if-eqz p1, :cond_5

    .line 176
    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionEmojicon:Ljava/lang/String;

    invoke-static {p1, p2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_3

    goto :goto_1

    :cond_3
    return-void

    .line 177
    :cond_4
    :goto_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->detach()V

    .line 178
    new-instance p1, Lorg/telegram/messenger/ImageReceiver;

    iget-object p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->parent:Landroid/view/View;

    invoke-direct {p1, p3}, Lorg/telegram/messenger/ImageReceiver;-><init>(Landroid/view/View;)V

    iput-object p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    const/4 p3, 0x1

    .line 179
    iput-boolean p3, p1, Lorg/telegram/messenger/ImageReceiver;->ignoreNotifications:Z

    .line 180
    invoke-virtual {p1}, Lorg/telegram/messenger/ImageReceiver;->onAttachedToWindow()Z

    .line 181
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionImage:Lorg/telegram/messenger/ImageReceiver;

    iget-object p1, v5, Lorg/telegram/tgnet/TLRPC$TL_availableReaction;->center_icon:Lorg/telegram/tgnet/TLRPC$Document;

    invoke-static {p1}, Lorg/telegram/messenger/ImageLocation;->getForDocument(Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/ImageLocation;

    move-result-object v1

    iget-object p1, v5, Lorg/telegram/tgnet/TLRPC$TL_availableReaction;->static_icon:Lorg/telegram/tgnet/TLRPC$Document;

    sget p3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    const/high16 p4, 0x3f800000    # 1.0f

    .line 182
    invoke-static {p1, p3, p4}, Lorg/telegram/messenger/DocumentObject;->getSvgThumb(Lorg/telegram/tgnet/TLRPC$Document;IF)Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    move-result-object v3

    const-string v4, "webp"

    const/4 v6, 0x1

    .line 181
    const-string v2, "40_40_lastreactframe"

    invoke-virtual/range {v0 .. v6}, Lorg/telegram/messenger/ImageReceiver;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/Object;I)V

    .line 183
    iput-object p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionEmojicon:Ljava/lang/String;

    return-void

    .line 187
    :cond_5
    invoke-virtual {p0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->detach()V

    if-eqz p2, :cond_6

    .line 188
    invoke-static {p2}, Lorg/telegram/messenger/Emoji;->getEmojiDrawable(Ljava/lang/CharSequence;)Lorg/telegram/messenger/Emoji$EmojiDrawable;

    move-result-object p3

    :cond_6
    iput-object p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reactionDrawable:Landroid/graphics/drawable/Drawable;

    return-void
.end method

.method public size()I
    .locals 0

    .line 236
    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p0

    return p0
.end method

.method public start(Ljava/util/List;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/utils/chats/SwipeAction;",
            ">;)V"
        }
    .end annotation

    .line 206
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 207
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    .line 208
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSwipeActionsLoop()Z

    move-result p1

    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->looped:Z

    .line 209
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSwipeActionsReversed()Z

    move-result p1

    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reversed:Z

    const/4 p1, 0x0

    .line 210
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    .line 211
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->slot:I

    .line 212
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->armed:Z

    .line 213
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->stepped:Z

    .line 214
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->scrubbing:Z

    .line 215
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->liveAlpha:Lorg/telegram/ui/Components/AnimatedFloat;

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/Components/AnimatedFloat;->set(FZ)F

    .line 216
    iput v2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->anchorY:F

    .line 217
    iput v2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftX:F

    .line 218
    iput v2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftY:F

    .line 219
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->direction:I

    .line 220
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->cancel()V

    .line 221
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->positionSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v0

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 222
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->position:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/FloatValueHolder;->setValue(F)V

    .line 223
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->cancel()V

    .line 224
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibilitySpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v0

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 225
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->visibility:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/FloatValueHolder;->setValue(F)V

    .line 226
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->cancel()V

    .line 227
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fillSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v0

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 228
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->fill:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/FloatValueHolder;->setValue(F)V

    .line 229
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->cancel()V

    .line 230
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ringSpring:Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-virtual {v0}, Landroidx/dynamicanimation/animation/SpringAnimation;->getSpring()Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object v0

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/SpringForce;->setFinalPosition(F)Landroidx/dynamicanimation/animation/SpringForce;

    .line 231
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->ring:Landroidx/dynamicanimation/animation/FloatValueHolder;

    invoke-virtual {v0, v2}, Landroidx/dynamicanimation/animation/FloatValueHolder;->setValue(F)V

    .line 232
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->beyondMax:Z

    return-void
.end method

.method public update(FFF)V
    .locals 4

    .line 245
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v1, 0x2

    if-ge v0, v1, :cond_0

    goto/16 :goto_3

    .line 248
    :cond_0
    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result p1

    const/high16 v0, 0x42480000    # 50.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    cmpg-float p1, p1, v0

    if-gez p1, :cond_1

    const/4 p1, 0x0

    .line 249
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->armed:Z

    .line 250
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->scrubbing:Z

    return-void

    .line 253
    :cond_1
    iget-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->armed:Z

    const/4 v0, 0x1

    if-nez p1, :cond_2

    .line 254
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->armed:Z

    .line 255
    iput p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lastX:F

    .line 256
    iput p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lastY:F

    const/4 p1, 0x0

    .line 257
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftX:F

    .line 258
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftY:F

    return-void

    .line 261
    :cond_2
    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lastX:F

    sub-float p1, p2, p1

    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result p1

    .line 262
    iget v1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lastY:F

    sub-float v1, p3, v1

    .line 263
    iput p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lastX:F

    .line 264
    iput p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->lastY:F

    .line 265
    iget-boolean p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->scrubbing:Z

    if-nez p2, :cond_4

    .line 266
    iget p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftX:F

    const v2, 0x3f333333    # 0.7f

    mul-float/2addr p2, v2

    const v3, 0x3e99999a    # 0.3f

    mul-float/2addr p1, v3

    add-float/2addr p2, p1

    iput p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftX:F

    .line 267
    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftY:F

    mul-float/2addr p1, v2

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result p2

    mul-float/2addr p2, v3

    add-float/2addr p1, p2

    iput p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftY:F

    const/high16 p2, 0x3f000000    # 0.5f

    .line 268
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    int-to-float p2, p2

    cmpg-float p1, p1, p2

    if-ltz p1, :cond_b

    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftY:F

    iget p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->driftX:F

    const/high16 v1, 0x3fc00000    # 1.5f

    mul-float/2addr p2, v1

    cmpg-float p1, p1, p2

    if-gtz p1, :cond_3

    goto :goto_3

    .line 271
    :cond_3
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->scrubbing:Z

    .line 272
    iput p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->anchorY:F

    return-void

    .line 275
    :cond_4
    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->anchorY:F

    sub-float p1, p3, p1

    iget p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->direction:I

    const/4 v1, -0x1

    if-gez p2, :cond_5

    move p2, v1

    goto :goto_0

    :cond_5
    move p2, v0

    :goto_0
    int-to-float p2, p2

    mul-float/2addr p1, p2

    iget-boolean p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->reversed:Z

    if-eqz p2, :cond_6

    move p2, v1

    goto :goto_1

    :cond_6
    move p2, v0

    :goto_1
    int-to-float p2, p2

    mul-float/2addr p1, p2

    .line 276
    iget-boolean p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->stepped:Z

    if-eqz p2, :cond_7

    const/high16 p2, 0x42100000    # 36.0f

    goto :goto_2

    :cond_7
    const/high16 p2, 0x42300000    # 44.0f

    :goto_2
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    int-to-float p2, p2

    cmpl-float v2, p1, p2

    if-ltz v2, :cond_9

    .line 278
    iput p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->anchorY:F

    .line 279
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->stepped:Z

    .line 280
    iget-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->looped:Z

    if-nez p1, :cond_8

    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    iget-object p2, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->actions:Ljava/util/List;

    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p2

    sub-int/2addr p2, v0

    if-ge p1, p2, :cond_b

    .line 281
    :cond_8
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->move(I)V

    return-void

    :cond_9
    neg-float p2, p2

    cmpg-float p1, p1, p2

    if-gtz p1, :cond_b

    .line 284
    iput p3, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->anchorY:F

    .line 285
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->stepped:Z

    .line 286
    iget-boolean p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->looped:Z

    if-nez p1, :cond_a

    iget p1, p0, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->selected:I

    if-lez p1, :cond_b

    .line 287
    :cond_a
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/utils/chats/SwipeActionsHelper;->move(I)V

    :cond_b
    :goto_3
    return-void
.end method
