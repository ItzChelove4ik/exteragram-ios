.class public abstract Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final BOUNDS_OVAL:Landroid/view/ViewOutlineProvider;

.field public static final BOUNDS_ROUND_RECT:Landroid/view/ViewOutlineProvider;

.field private static drawClipOutline:Landroid/graphics/Outline;

.field private static drawClipPath:Landroid/graphics/Path;

.field private static drawClipRect:Landroid/graphics/Rect;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 22
    new-instance v0, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$1;

    invoke-direct {v0}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$1;-><init>()V

    sput-object v0, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->BOUNDS_OVAL:Landroid/view/ViewOutlineProvider;

    .line 29
    new-instance v0, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$2;

    invoke-direct {v0}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$2;-><init>()V

    sput-object v0, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->BOUNDS_ROUND_RECT:Landroid/view/ViewOutlineProvider;

    return-void
.end method

.method public static boundsWithPaddingFromViewAndRoundRect(F)Landroid/view/ViewOutlineProvider;
    .locals 1

    .line 51
    new-instance v0, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$4;

    invoke-direct {v0, p0}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$4;-><init>(F)V

    return-object v0
.end method

.method public static boundsWithPaddingRoundRect(IF)Landroid/view/ViewOutlineProvider;
    .locals 1

    .line 60
    new-instance v0, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$5;

    invoke-direct {v0, p0, p1}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$5;-><init>(IF)V

    return-object v0
.end method

.method public static boundsWithRoundRect(F)Landroid/view/ViewOutlineProvider;
    .locals 1

    const/4 v0, 0x0

    .line 47
    invoke-static {v0, p0}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->boundsWithPaddingRoundRect(IF)Landroid/view/ViewOutlineProvider;

    move-result-object p0

    return-object p0
.end method

.method public static drawViewWithOutline(Landroid/graphics/Canvas;Landroid/view/View;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/graphics/Canvas;",
            "Landroid/view/View;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Landroid/graphics/Canvas;",
            ">;)V"
        }
    .end annotation

    if-eqz p0, :cond_6

    if-nez p1, :cond_0

    goto :goto_2

    .line 95
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getOutlineProvider()Landroid/view/ViewOutlineProvider;

    move-result-object v0

    .line 97
    invoke-virtual {p0}, Landroid/graphics/Canvas;->isHardwareAccelerated()Z

    move-result v1

    if-nez v1, :cond_5

    .line 99
    invoke-virtual {p1}, Landroid/view/View;->getClipToOutline()Z

    move-result v1

    if-eqz v1, :cond_5

    if-nez v0, :cond_1

    goto :goto_1

    .line 110
    :cond_1
    invoke-static {}, Landroid/os/Looper;->myLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v2

    if-ne v1, v2, :cond_3

    .line 111
    sget-object v1, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->drawClipPath:Landroid/graphics/Path;

    if-nez v1, :cond_2

    .line 112
    new-instance v1, Landroid/graphics/Path;

    invoke-direct {v1}, Landroid/graphics/Path;-><init>()V

    sput-object v1, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->drawClipPath:Landroid/graphics/Path;

    .line 113
    new-instance v1, Landroid/graphics/Outline;

    invoke-direct {v1}, Landroid/graphics/Outline;-><init>()V

    sput-object v1, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->drawClipOutline:Landroid/graphics/Outline;

    .line 114
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    sput-object v1, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->drawClipRect:Landroid/graphics/Rect;

    .line 117
    :cond_2
    sget-object v1, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->drawClipPath:Landroid/graphics/Path;

    .line 118
    sget-object v2, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->drawClipOutline:Landroid/graphics/Outline;

    .line 119
    sget-object v3, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->drawClipRect:Landroid/graphics/Rect;

    .line 121
    invoke-virtual {v2}, Landroid/graphics/Outline;->setEmpty()V

    .line 122
    invoke-virtual {v3}, Landroid/graphics/Rect;->setEmpty()V

    goto :goto_0

    .line 124
    :cond_3
    new-instance v1, Landroid/graphics/Path;

    invoke-direct {v1}, Landroid/graphics/Path;-><init>()V

    .line 125
    new-instance v2, Landroid/graphics/Outline;

    invoke-direct {v2}, Landroid/graphics/Outline;-><init>()V

    .line 126
    new-instance v3, Landroid/graphics/Rect;

    invoke-direct {v3}, Landroid/graphics/Rect;-><init>()V

    .line 129
    :goto_0
    invoke-virtual {v0, p1, v2}, Landroid/view/ViewOutlineProvider;->getOutline(Landroid/view/View;Landroid/graphics/Outline;)V

    .line 131
    invoke-static {v2, v3, v1}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->outlineToPath(Landroid/graphics/Outline;Landroid/graphics/Rect;Landroid/graphics/Path;)Z

    move-result p1

    if-nez p1, :cond_4

    .line 132
    invoke-interface {p2, p0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void

    .line 136
    :cond_4
    invoke-virtual {p0}, Landroid/graphics/Canvas;->save()I

    move-result p1

    .line 137
    invoke-virtual {p0, v1}, Landroid/graphics/Canvas;->clipPath(Landroid/graphics/Path;)Z

    .line 138
    invoke-interface {p2, p0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    .line 139
    invoke-virtual {p0, p1}, Landroid/graphics/Canvas;->restoreToCount(I)V

    return-void

    .line 102
    :cond_5
    :goto_1
    invoke-interface {p2, p0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    :cond_6
    :goto_2
    return-void
.end method

.method public static fromDrawable(Landroid/graphics/drawable/Drawable;)Landroid/view/ViewOutlineProvider;
    .locals 1

    .line 38
    new-instance v0, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$3;

    invoke-direct {v0, p0}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl$3;-><init>(Landroid/graphics/drawable/Drawable;)V

    return-object v0
.end method

.method private static outlineToPath(Landroid/graphics/Outline;Landroid/graphics/Rect;Landroid/graphics/Path;)Z
    .locals 16

    move-object/from16 v0, p1

    .line 148
    invoke-virtual/range {p2 .. p2}, Landroid/graphics/Path;->rewind()V

    .line 150
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Outline;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_2

    invoke-virtual/range {p0 .. p1}, Landroid/graphics/Outline;->getRect(Landroid/graphics/Rect;)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_1

    .line 154
    :cond_0
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Outline;->getRadius()F

    move-result v7

    const/4 v1, 0x0

    cmpl-float v1, v7, v1

    if-lez v1, :cond_1

    .line 157
    iget v1, v0, Landroid/graphics/Rect;->left:I

    int-to-float v3, v1

    iget v1, v0, Landroid/graphics/Rect;->top:I

    int-to-float v4, v1

    iget v1, v0, Landroid/graphics/Rect;->right:I

    int-to-float v5, v1

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    int-to-float v6, v0

    sget-object v9, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    move v8, v7

    move-object/from16 v2, p2

    invoke-virtual/range {v2 .. v9}, Landroid/graphics/Path;->addRoundRect(FFFFFFLandroid/graphics/Path$Direction;)V

    goto :goto_0

    .line 167
    :cond_1
    iget v1, v0, Landroid/graphics/Rect;->left:I

    int-to-float v11, v1

    iget v1, v0, Landroid/graphics/Rect;->top:I

    int-to-float v12, v1

    iget v1, v0, Landroid/graphics/Rect;->right:I

    int-to-float v13, v1

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    int-to-float v14, v0

    sget-object v15, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    move-object/from16 v10, p2

    invoke-virtual/range {v10 .. v15}, Landroid/graphics/Path;->addRect(FFFFLandroid/graphics/Path$Direction;)V

    :goto_0
    const/4 v0, 0x1

    return v0

    :cond_2
    :goto_1
    const/4 v0, 0x0

    return v0
.end method
