.class public final Lorg/telegram/utils/glass/positions/GlassPositionsMerger;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final DEFAULT:Lorg/telegram/utils/glass/positions/GlassPositionsMerger;


# instance fields
.field private final epsX:F

.field private final epsY:F


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 13
    new-instance v0, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;

    const/high16 v1, 0x7f800000    # Float.POSITIVE_INFINITY

    invoke-direct {v0, v1, v1}, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;-><init>(FF)V

    sput-object v0, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->DEFAULT:Lorg/telegram/utils/glass/positions/GlassPositionsMerger;

    return-void
.end method

.method public constructor <init>(FF)V
    .locals 2

    .line 42
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 43
    invoke-static {p1}, Ljava/lang/Float;->isNaN(F)Z

    move-result v0

    if-nez v0, :cond_1

    const/4 v0, 0x0

    cmpg-float v1, p1, v0

    if-ltz v1, :cond_1

    .line 46
    invoke-static {p2}, Ljava/lang/Float;->isNaN(F)Z

    move-result v1

    if-nez v1, :cond_0

    cmpg-float v0, p2, v0

    if-ltz v0, :cond_0

    .line 50
    iput p1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->epsX:F

    .line 51
    iput p2, p0, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->epsY:F

    return-void

    .line 47
    :cond_0
    const-string p0, "epsY must be >= 0"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0

    .line 44
    :cond_1
    const-string p0, "epsX must be >= 0"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method private static compare(FFFF)I
    .locals 0

    .line 209
    invoke-static {p0, p2}, Ljava/lang/Float;->compare(FF)I

    move-result p0

    if-nez p0, :cond_0

    .line 212
    invoke-static {p1, p3}, Ljava/lang/Float;->compare(FF)I

    move-result p0

    :cond_0
    return p0
.end method

.method private intersectsOrNear(FFFFLandroid/graphics/RectF;)Z
    .locals 3

    .line 133
    iget v0, p5, Landroid/graphics/RectF;->left:F

    iget v1, p5, Landroid/graphics/RectF;->right:F

    iget v2, p0, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->epsX:F

    invoke-static {p1, p3, v0, v1, v2}, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->intersectsOrNear1D(FFFFF)Z

    move-result p1

    if-eqz p1, :cond_0

    iget p1, p5, Landroid/graphics/RectF;->top:F

    iget p3, p5, Landroid/graphics/RectF;->bottom:F

    iget p0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->epsY:F

    .line 139
    invoke-static {p2, p4, p1, p3, p0}, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->intersectsOrNear1D(FFFFF)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static intersectsOrNear1D(FFFFF)Z
    .locals 3

    cmpg-float v0, p1, p2

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-gez v0, :cond_1

    sub-float/2addr p2, p1

    cmpg-float p0, p2, p4

    if-gtz p0, :cond_0

    return v2

    :cond_0
    return v1

    :cond_1
    cmpg-float p1, p3, p0

    if-gez p1, :cond_3

    sub-float/2addr p0, p3

    cmpg-float p0, p0, p4

    if-gtz p0, :cond_2

    return v2

    :cond_2
    return v1

    :cond_3
    return v2
.end method

.method private static sort(Lorg/telegram/utils/glass/positions/GlassPositionsArray;)V
    .locals 10

    .line 176
    invoke-virtual {p0}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size()I

    move-result v0

    const/4 v1, 0x1

    :goto_0
    if-ge v1, v0, :cond_2

    .line 179
    invoke-virtual {p0, v1}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->get(I)Landroid/graphics/RectF;

    move-result-object v2

    .line 181
    iget v3, v2, Landroid/graphics/RectF;->left:F

    .line 182
    iget v4, v2, Landroid/graphics/RectF;->top:F

    .line 183
    iget v5, v2, Landroid/graphics/RectF;->right:F

    .line 184
    iget v2, v2, Landroid/graphics/RectF;->bottom:F

    add-int/lit8 v6, v1, -0x1

    :goto_1
    if-ltz v6, :cond_1

    .line 189
    invoke-virtual {p0, v6}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->get(I)Landroid/graphics/RectF;

    move-result-object v7

    .line 191
    iget v8, v7, Landroid/graphics/RectF;->top:F

    iget v9, v7, Landroid/graphics/RectF;->left:F

    invoke-static {v8, v9, v4, v3}, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->compare(FFFF)I

    move-result v8

    if-gtz v8, :cond_0

    goto :goto_2

    :cond_0
    add-int/lit8 v8, v6, 0x1

    .line 195
    invoke-virtual {p0, v8}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->get(I)Landroid/graphics/RectF;

    move-result-object v8

    invoke-virtual {v8, v7}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    add-int/lit8 v6, v6, -0x1

    goto :goto_1

    :cond_1
    :goto_2
    add-int/lit8 v6, v6, 0x1

    .line 199
    invoke-virtual {p0, v6}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->get(I)Landroid/graphics/RectF;

    move-result-object v6

    invoke-virtual {v6, v3, v4, v5, v2}, Landroid/graphics/RectF;->set(FFFF)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    return-void
.end method


# virtual methods
.method public merge(Lorg/telegram/utils/glass/positions/GlassPositionsArray;Lorg/telegram/utils/glass/positions/GlassPositionsArray;)V
    .locals 13

    if-eq p1, p2, :cond_7

    .line 71
    invoke-virtual {p2}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->clear()V

    .line 73
    invoke-virtual {p1}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size()I

    move-result v0

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_6

    .line 76
    invoke-virtual {p1, v2}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->get(I)Landroid/graphics/RectF;

    move-result-object v3

    .line 78
    iget v4, v3, Landroid/graphics/RectF;->left:F

    .line 79
    iget v5, v3, Landroid/graphics/RectF;->top:F

    .line 80
    iget v6, v3, Landroid/graphics/RectF;->right:F

    .line 81
    iget v3, v3, Landroid/graphics/RectF;->bottom:F

    move v11, v3

    move v8, v4

    move v9, v5

    move v10, v6

    move v3, v1

    .line 85
    :goto_1
    invoke-virtual {p2}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size()I

    move-result v4

    if-ge v3, v4, :cond_5

    .line 86
    invoke-virtual {p2, v3}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->get(I)Landroid/graphics/RectF;

    move-result-object v12

    move-object v7, p0

    .line 88
    invoke-direct/range {v7 .. v12}, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->intersectsOrNear(FFFFLandroid/graphics/RectF;)Z

    move-result p0

    if-eqz p0, :cond_4

    .line 95
    iget p0, v12, Landroid/graphics/RectF;->left:F

    cmpg-float v4, p0, v8

    if-gez v4, :cond_0

    move v8, p0

    .line 98
    :cond_0
    iget p0, v12, Landroid/graphics/RectF;->top:F

    cmpg-float v4, p0, v9

    if-gez v4, :cond_1

    move v9, p0

    .line 101
    :cond_1
    iget p0, v12, Landroid/graphics/RectF;->right:F

    cmpl-float v4, p0, v10

    if-lez v4, :cond_2

    move v10, p0

    .line 104
    :cond_2
    iget p0, v12, Landroid/graphics/RectF;->bottom:F

    cmpl-float v4, p0, v11

    if-lez v4, :cond_3

    move v11, p0

    .line 113
    :cond_3
    invoke-virtual {p2, v3}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->removeUnordered(I)V

    move v3, v1

    goto :goto_2

    :cond_4
    add-int/lit8 v3, v3, 0x1

    :goto_2
    move-object p0, v7

    goto :goto_1

    :cond_5
    move-object v7, p0

    .line 120
    invoke-virtual {p2, v8, v9, v10, v11}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->add(FFFF)Landroid/graphics/RectF;

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 123
    :cond_6
    invoke-static {p2}, Lorg/telegram/utils/glass/positions/GlassPositionsMerger;->sort(Lorg/telegram/utils/glass/positions/GlassPositionsArray;)V

    return-void

    .line 66
    :cond_7
    const-string p0, "positions and output must be different arrays"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
