.class public Lorg/telegram/utils/glass/GlassEngine;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/glass/GlassEngine$GlassInvalidationListener;,
        Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;,
        Lorg/telegram/utils/glass/GlassEngine$ThemedSourceColorState;,
        Lorg/telegram/utils/glass/GlassEngine$ThemedColorDelegate;
    }
.end annotation


# instance fields
.field private final additionalInvalidationCondition:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private final allVisibleDrawablesWithDisplayListPositions:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

.field private final allVisibleDrawablesWithDisplayListPositionsMerged:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

.field private glassInvalidationListener:Lorg/telegram/utils/glass/GlassEngine$GlassInvalidationListener;

.field private lastOtherGenerationId:J

.field private lastRootHeight:I

.field private lastRootWidth:I

.field private lastScrollEdgesGenerationId:J

.field private lastScrollGenerationId:J

.field private final onPostDrawListener:Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;

.field private otherGenerationId:J

.field private final output:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

.field private positionsMerger:Lorg/telegram/utils/glass/positions/GlassPositionsMerger;

.field private rootView:Landroid/view/View;

.field private scrollEdgesGenerationId:J

.field private scrollGenerationId:J

.field private final themedSourceColorStates:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/utils/glass/GlassEngine$ThemedSourceColorState;",
            ">;"
        }
    .end annotation
.end field

.field private final tmpPositionRect:Landroid/graphics/RectF;

.field private final viewPositionStates:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$0m73wZXsogvcbP9WC5xSwi_Dm-M(Lorg/telegram/utils/glass/GlassEngine;IZ)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/glass/GlassEngine;->lambda$addScrolledView$0(IZ)V

    return-void
.end method

.method public static synthetic $r8$lambda$7hSYliEEzzJXg6qDwZ-uD6LziUo(Lorg/telegram/utils/glass/GlassEngine;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/glass/GlassEngine;->onPostDraw()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetscrollGenerationId(Lorg/telegram/utils/glass/GlassEngine;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/glass/GlassEngine;->scrollGenerationId:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fputscrollGenerationId(Lorg/telegram/utils/glass/GlassEngine;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/glass/GlassEngine;->scrollGenerationId:J

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 23
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 44
    new-instance v0, Lorg/telegram/utils/glass/GlassEngine$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lorg/telegram/utils/glass/GlassEngine$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/glass/GlassEngine;)V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->onPostDrawListener:Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;

    .line 46
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->additionalInvalidationCondition:Ljava/util/ArrayList;

    .line 48
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->viewPositionStates:Ljava/util/ArrayList;

    .line 50
    sget-object v0, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->DEFAULT:Lorg/telegram/utils/glass/positions/GlassPositionsMerger;

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->positionsMerger:Lorg/telegram/utils/glass/positions/GlassPositionsMerger;

    .line 189
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    .line 193
    new-instance v0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    invoke-direct {v0}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->allVisibleDrawablesWithDisplayListPositions:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    .line 195
    new-instance v0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    invoke-direct {v0}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->allVisibleDrawablesWithDisplayListPositionsMerged:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    .line 197
    new-instance v0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    invoke-direct {v0}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->output:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    .line 306
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->themedSourceColorStates:Ljava/util/ArrayList;

    return-void
.end method

.method private checkColorSources()Z
    .locals 2

    .line 314
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->themedSourceColorStates:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    if-gtz v0, :cond_0

    return v1

    .line 315
    :cond_0
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine;->themedSourceColorStates:Ljava/util/ArrayList;

    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    .line 316
    invoke-static {p0}, Lorg/telegram/utils/glass/GlassEngine$ThemedSourceColorState;->-$$Nest$fgetcolorDelegate(Lorg/telegram/utils/glass/GlassEngine$ThemedSourceColorState;)Lorg/telegram/utils/glass/GlassEngine$ThemedColorDelegate;

    throw p0
.end method

.method private checkDrawablePositionsIfNeeded()Z
    .locals 14

    .line 202
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->rootView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v0

    .line 203
    iget-object v1, p0, Lorg/telegram/utils/glass/GlassEngine;->rootView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getHeight()I

    move-result v1

    .line 204
    iget v2, p0, Lorg/telegram/utils/glass/GlassEngine;->lastRootWidth:I

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-ne v2, v0, :cond_1

    iget v2, p0, Lorg/telegram/utils/glass/GlassEngine;->lastRootHeight:I

    if-eq v2, v1, :cond_0

    goto :goto_0

    :cond_0
    move v2, v4

    goto :goto_1

    .line 205
    :cond_1
    :goto_0
    iput v0, p0, Lorg/telegram/utils/glass/GlassEngine;->lastRootWidth:I

    .line 206
    iput v1, p0, Lorg/telegram/utils/glass/GlassEngine;->lastRootHeight:I

    move v2, v3

    .line 210
    :goto_1
    iget-object v5, p0, Lorg/telegram/utils/glass/GlassEngine;->viewPositionStates:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v6

    move v7, v4

    :cond_2
    :goto_2
    if-ge v7, v6, :cond_b

    invoke-virtual {v5, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    add-int/lit8 v7, v7, 0x1

    check-cast v8, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;

    .line 211
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetview(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/view/View;

    move-result-object v9

    iget-object v10, p0, Lorg/telegram/utils/glass/GlassEngine;->rootView:Landroid/view/View;

    iget-object v11, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-static {v9, v10, v11}, Lorg/telegram/ui/Components/chat/ViewPositionWatcher;->computeRectInParent(Landroid/view/View;Landroid/view/View;Landroid/graphics/RectF;)Z

    move-result v9

    if-nez v9, :cond_3

    goto :goto_2

    .line 215
    :cond_3
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetviewPositionRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v9

    iget-object v10, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v9, v10}, Landroid/graphics/RectF;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_4

    .line 216
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetviewPositionRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v2

    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v2, v9}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    .line 217
    invoke-static {v8, v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fputinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V

    .line 218
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v2

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetviewPositionRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v9

    iget v9, v9, Landroid/graphics/RectF;->left:F

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetviewPositionRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v10

    iget v10, v10, Landroid/graphics/RectF;->top:F

    invoke-virtual {v2, v9, v10}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setSourceOffset(FF)V

    move v2, v3

    .line 222
    :cond_4
    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v10

    invoke-virtual {v10}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v10

    invoke-virtual {v9, v10}, Landroid/graphics/RectF;->set(Landroid/graphics/Rect;)V

    .line 223
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeView(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v9

    iget-object v10, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v9, v10}, Landroid/graphics/RectF;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_5

    .line 224
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeView(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v2

    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v2, v9}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    .line 225
    invoke-static {v8, v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fputinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V

    move v2, v3

    .line 229
    :cond_5
    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetviewPositionRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v10

    iget v10, v10, Landroid/graphics/RectF;->left:F

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetviewPositionRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v11

    iget v11, v11, Landroid/graphics/RectF;->top:F

    invoke-virtual {v9, v10, v11}, Landroid/graphics/RectF;->offset(FF)V

    .line 230
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v9

    iget-object v10, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v9, v10}, Landroid/graphics/RectF;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_6

    .line 231
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v2

    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v2, v9}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    .line 232
    invoke-static {v8, v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fputinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V

    move v2, v3

    .line 236
    :cond_6
    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v10

    invoke-virtual {v9, v10}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    .line 237
    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v10

    invoke-virtual {v10}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->getOutsetX()I

    move-result v10

    neg-int v10, v10

    int-to-float v10, v10

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v11

    invoke-virtual {v11}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->getOutsetY()I

    move-result v11

    neg-int v11, v11

    int-to-float v11, v11

    invoke-virtual {v9, v10, v11}, Landroid/graphics/RectF;->inset(FF)V

    .line 238
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRootWithOutset(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v9

    iget-object v10, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v9, v10}, Landroid/graphics/RectF;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_7

    .line 239
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRootWithOutset(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v2

    iget-object v9, p0, Lorg/telegram/utils/glass/GlassEngine;->tmpPositionRect:Landroid/graphics/RectF;

    invoke-virtual {v2, v9}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    .line 240
    invoke-static {v8, v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fputinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V

    move v2, v3

    .line 244
    :cond_7
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetview(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/view/View;

    move-result-object v9

    .line 245
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v10

    invoke-virtual {v10}, Landroid/graphics/RectF;->isEmpty()Z

    move-result v10

    if-nez v10, :cond_8

    .line 246
    invoke-virtual {v9}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v10

    if-eqz v10, :cond_8

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v10

    int-to-float v11, v0

    int-to-float v12, v1

    const/4 v13, 0x0

    .line 247
    invoke-virtual {v10, v13, v13, v11, v12}, Landroid/graphics/RectF;->intersects(FFFF)Z

    move-result v10

    if-eqz v10, :cond_8

    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v10

    .line 248
    invoke-virtual {v10}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->getAlpha()I

    move-result v10

    if-lez v10, :cond_8

    .line 249
    invoke-virtual {v9}, Landroid/view/View;->getVisibility()I

    move-result v10

    if-nez v10, :cond_8

    .line 250
    invoke-virtual {v9}, Landroid/view/View;->getAlpha()F

    move-result v10

    cmpl-float v10, v10, v13

    if-lez v10, :cond_8

    .line 251
    invoke-virtual {v9}, Landroid/view/View;->getScaleX()F

    move-result v10

    cmpl-float v10, v10, v13

    if-eqz v10, :cond_8

    .line 252
    invoke-virtual {v9}, Landroid/view/View;->getScaleY()F

    move-result v9

    cmpl-float v9, v9, v13

    if-eqz v9, :cond_8

    move v9, v3

    goto :goto_3

    :cond_8
    move v9, v4

    :goto_3
    if-eqz v9, :cond_9

    .line 254
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v10

    invoke-virtual {v10}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->hasDisplayList()Z

    move-result v10

    if-eqz v10, :cond_9

    move v10, v3

    goto :goto_4

    :cond_9
    move v10, v4

    .line 255
    :goto_4
    invoke-static {v8}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetvisible(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Z

    move-result v11

    if-ne v11, v9, :cond_a

    if-eqz v9, :cond_2

    if-nez v10, :cond_2

    .line 256
    :cond_a
    invoke-static {v8, v9}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fputvisible(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V

    .line 257
    invoke-static {v8, v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fputinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V

    move v2, v3

    goto/16 :goto_2

    :cond_b
    if-eqz v2, :cond_e

    .line 263
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->allVisibleDrawablesWithDisplayListPositions:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    invoke-virtual {v0}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->clear()V

    .line 264
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->viewPositionStates:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    :cond_c
    :goto_5
    if-ge v4, v1, :cond_d

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v4, v4, 0x1

    check-cast v3, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;

    .line 265
    invoke-static {v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetvisible(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Z

    move-result v5

    if-eqz v5, :cond_c

    .line 266
    iget-object v5, p0, Lorg/telegram/utils/glass/GlassEngine;->allVisibleDrawablesWithDisplayListPositions:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    invoke-static {v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawableBoundsRelativeRootWithOutset(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;

    move-result-object v3

    invoke-virtual {v5, v3}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->add(Landroid/graphics/RectF;)Landroid/graphics/RectF;

    goto :goto_5

    .line 269
    :cond_d
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->positionsMerger:Lorg/telegram/utils/glass/positions/GlassPositionsMerger;

    iget-object v1, p0, Lorg/telegram/utils/glass/GlassEngine;->allVisibleDrawablesWithDisplayListPositions:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine;->allVisibleDrawablesWithDisplayListPositionsMerged:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    invoke-virtual {v0, v1, p0}, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->merge(Lorg/telegram/utils/glass/positions/GlassPositionsArray;Lorg/telegram/utils/glass/positions/GlassPositionsArray;)V

    :cond_e
    return v2
.end method

.method private synthetic lambda$addScrolledView$0(IZ)V
    .locals 2

    .line 104
    iget-wide p1, p0, Lorg/telegram/utils/glass/GlassEngine;->scrollEdgesGenerationId:J

    const-wide/16 v0, 0x1

    add-long/2addr p1, v0

    iput-wide p1, p0, Lorg/telegram/utils/glass/GlassEngine;->scrollEdgesGenerationId:J

    return-void
.end method

.method private onPostDraw()V
    .locals 7

    .line 133
    invoke-direct {p0}, Lorg/telegram/utils/glass/GlassEngine;->checkDrawablePositionsIfNeeded()Z

    move-result v0

    .line 134
    invoke-direct {p0}, Lorg/telegram/utils/glass/GlassEngine;->checkColorSources()Z

    move-result v1

    .line 136
    iget-object v2, p0, Lorg/telegram/utils/glass/GlassEngine;->additionalInvalidationCondition:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-nez v3, :cond_9

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    const/4 v0, 0x4

    goto :goto_0

    :cond_0
    move v0, v2

    :goto_0
    if-eqz v1, :cond_1

    or-int/lit8 v0, v0, 0x10

    .line 152
    :cond_1
    iget-wide v3, p0, Lorg/telegram/utils/glass/GlassEngine;->lastOtherGenerationId:J

    iget-wide v5, p0, Lorg/telegram/utils/glass/GlassEngine;->otherGenerationId:J

    cmp-long v1, v3, v5

    if-eqz v1, :cond_2

    .line 153
    iput-wide v5, p0, Lorg/telegram/utils/glass/GlassEngine;->lastOtherGenerationId:J

    or-int/lit8 v0, v0, 0x8

    .line 157
    :cond_2
    iget-wide v3, p0, Lorg/telegram/utils/glass/GlassEngine;->lastScrollEdgesGenerationId:J

    iget-wide v5, p0, Lorg/telegram/utils/glass/GlassEngine;->scrollEdgesGenerationId:J

    cmp-long v1, v3, v5

    if-eqz v1, :cond_3

    .line 158
    iput-wide v5, p0, Lorg/telegram/utils/glass/GlassEngine;->lastScrollEdgesGenerationId:J

    or-int/lit8 v0, v0, 0x2

    .line 162
    :cond_3
    iget-wide v3, p0, Lorg/telegram/utils/glass/GlassEngine;->lastScrollGenerationId:J

    iget-wide v5, p0, Lorg/telegram/utils/glass/GlassEngine;->scrollGenerationId:J

    cmp-long v1, v3, v5

    if-eqz v1, :cond_4

    .line 163
    iput-wide v5, p0, Lorg/telegram/utils/glass/GlassEngine;->lastScrollGenerationId:J

    or-int/lit8 v0, v0, 0x1

    :cond_4
    if-eqz v0, :cond_8

    .line 168
    iget-object v1, p0, Lorg/telegram/utils/glass/GlassEngine;->glassInvalidationListener:Lorg/telegram/utils/glass/GlassEngine$GlassInvalidationListener;

    if-eqz v1, :cond_5

    .line 169
    invoke-interface {v1, v0}, Lorg/telegram/utils/glass/GlassEngine$GlassInvalidationListener;->onGlassInvalidated(I)V

    .line 172
    :cond_5
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine;->viewPositionStates:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    move v1, v2

    :cond_6
    :goto_1
    if-ge v1, v0, :cond_8

    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v1, v1, 0x1

    check-cast v3, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;

    .line 173
    invoke-static {v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetvisible(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Z

    move-result v4

    if-eqz v4, :cond_6

    invoke-static {v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Z

    move-result v4

    if-eqz v4, :cond_6

    .line 174
    invoke-static {v3, v2}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fputinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V

    .line 175
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v5, 0x1d

    if-lt v4, v5, :cond_7

    invoke-static {v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v4

    instance-of v4, v4, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawableRenderNode;

    if-eqz v4, :cond_7

    .line 176
    invoke-static {v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v3

    invoke-virtual {v3}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->updateDisplayList()V

    goto :goto_1

    .line 178
    :cond_7
    invoke-static {v3}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->-$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v3

    invoke-virtual {v3}, Landroid/graphics/drawable/Drawable;->invalidateSelf()V

    goto :goto_1

    :cond_8
    return-void

    .line 136
    :cond_9
    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    .line 137
    throw p0
.end method


# virtual methods
.method public addScrolledView(Landroidx/viewpager/widget/ViewPager;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    .line 91
    :cond_0
    new-instance v0, Lorg/telegram/utils/glass/GlassEngine$1;

    invoke-direct {v0, p0}, Lorg/telegram/utils/glass/GlassEngine$1;-><init>(Lorg/telegram/utils/glass/GlassEngine;)V

    invoke-virtual {p1, v0}, Landroidx/viewpager/widget/ViewPager;->addOnPageChangeListener(Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;)V

    return-void
.end method

.method public addScrolledView(Lorg/telegram/ui/Components/RecyclerListView;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    .line 104
    :cond_0
    new-instance v0, Lorg/telegram/utils/glass/GlassEngine$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0}, Lorg/telegram/utils/glass/GlassEngine$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/glass/GlassEngine;)V

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/RecyclerListView;->addEdgeEffectListener(Lorg/telegram/ui/Components/EdgeEffectTrackerFactory$OnEdgeEffectListener;)V

    .line 105
    new-instance v0, Lorg/telegram/utils/glass/GlassEngine$2;

    invoke-direct {v0, p0}, Lorg/telegram/utils/glass/GlassEngine$2;-><init>(Lorg/telegram/utils/glass/GlassEngine;)V

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->addOnScrollListener(Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V

    return-void
.end method

.method public getAllVisibleDrawablesWithDisplayListPositionsMerged()Lorg/telegram/utils/glass/positions/GlassPositionsArray;
    .locals 2

    .line 278
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->output:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    iget-object v1, p0, Lorg/telegram/utils/glass/GlassEngine;->allVisibleDrawablesWithDisplayListPositionsMerged:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    invoke-virtual {v0, v1}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->set(Lorg/telegram/utils/glass/positions/GlassPositionsArray;)V

    .line 279
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine;->output:Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    return-object p0
.end method

.method public invalidate()V
    .locals 4

    .line 123
    iget-wide v0, p0, Lorg/telegram/utils/glass/GlassEngine;->otherGenerationId:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/utils/glass/GlassEngine;->otherGenerationId:J

    return-void
.end method

.method public registerDrawable(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;)V
    .locals 2

    .line 57
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine;->viewPositionStates:Ljava/util/ArrayList;

    new-instance v0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;

    const/4 v1, 0x0

    invoke-direct {v0, p1, p2, v1}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;-><init>(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;Lorg/telegram/utils/glass/GlassEngine-IA;)V

    invoke-virtual {p0, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public setGlassInvalidationListener(Lorg/telegram/utils/glass/GlassEngine$GlassInvalidationListener;)V
    .locals 0

    .line 39
    iput-object p1, p0, Lorg/telegram/utils/glass/GlassEngine;->glassInvalidationListener:Lorg/telegram/utils/glass/GlassEngine$GlassInvalidationListener;

    return-void
.end method

.method public setPositionsMerger(Lorg/telegram/utils/glass/positions/GlassPositionsMerger;)V
    .locals 0

    .line 53
    iput-object p1, p0, Lorg/telegram/utils/glass/GlassEngine;->positionsMerger:Lorg/telegram/utils/glass/positions/GlassPositionsMerger;

    return-void
.end method

.method public setRoot(Landroid/view/View;)V
    .locals 2

    .line 75
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->rootView:Landroid/view/View;

    if-eq v0, p1, :cond_2

    if-eqz v0, :cond_0

    .line 77
    iget-object v1, p0, Lorg/telegram/utils/glass/GlassEngine;->onPostDrawListener:Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;

    invoke-static {v0, v1}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->removeListener(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V

    :cond_0
    if-eqz p1, :cond_1

    .line 80
    iget-object v0, p0, Lorg/telegram/utils/glass/GlassEngine;->onPostDrawListener:Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;

    invoke-static {p1, v0}, Lorg/telegram/messenger/postdrawcompat/ViewOnPostDrawListener;->addListener(Landroid/view/View;Lorg/telegram/messenger/postdrawcompat/OnPostDrawListener;)V

    .line 82
    :cond_1
    iput-object p1, p0, Lorg/telegram/utils/glass/GlassEngine;->rootView:Landroid/view/View;

    :cond_2
    return-void
.end method
