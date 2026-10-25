.class public Lorg/telegram/utils/glass/positions/GlassPositionsArray;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final positions:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Landroid/graphics/RectF;",
            ">;"
        }
    .end annotation
.end field

.field private size:I


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 14
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 15
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    return-void
.end method


# virtual methods
.method public add(FFFF)Landroid/graphics/RectF;
    .locals 2

    .line 34
    iget v0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    iget-object v1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge v0, v1, :cond_0

    .line 35
    iget-object v0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    iget v1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/graphics/RectF;

    .line 36
    invoke-virtual {v0, p1, p2, p3, p4}, Landroid/graphics/RectF;->set(FFFF)V

    goto :goto_0

    .line 38
    :cond_0
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0, p1, p2, p3, p4}, Landroid/graphics/RectF;-><init>(FFFF)V

    .line 39
    iget-object p1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 42
    :goto_0
    iget p1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    return-object v0
.end method

.method public add(Landroid/graphics/RectF;)Landroid/graphics/RectF;
    .locals 3

    .line 27
    iget v0, p1, Landroid/graphics/RectF;->left:F

    iget v1, p1, Landroid/graphics/RectF;->top:F

    iget v2, p1, Landroid/graphics/RectF;->right:F

    iget p1, p1, Landroid/graphics/RectF;->bottom:F

    invoke-virtual {p0, v0, v1, v2, p1}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->add(FFFF)Landroid/graphics/RectF;

    move-result-object p0

    return-object p0
.end method

.method public clear()V
    .locals 1

    const/4 v0, 0x0

    .line 57
    iput v0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    return-void
.end method

.method public clip(FFFF)V
    .locals 7

    const/4 v0, 0x0

    .line 108
    :goto_0
    iget v1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    if-ge v0, v1, :cond_6

    .line 109
    iget-object v1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/graphics/RectF;

    .line 111
    iget v2, v1, Landroid/graphics/RectF;->right:F

    cmpg-float v3, v2, p1

    if-lez v3, :cond_5

    iget v3, v1, Landroid/graphics/RectF;->bottom:F

    cmpg-float v4, v3, p2

    if-lez v4, :cond_5

    iget v4, v1, Landroid/graphics/RectF;->left:F

    cmpl-float v5, v4, p3

    if-gez v5, :cond_5

    iget v5, v1, Landroid/graphics/RectF;->top:F

    cmpl-float v6, v5, p4

    if-ltz v6, :cond_0

    goto :goto_1

    :cond_0
    cmpg-float v4, v4, p1

    if-gez v4, :cond_1

    .line 120
    iput p1, v1, Landroid/graphics/RectF;->left:F

    :cond_1
    cmpg-float v4, v5, p2

    if-gez v4, :cond_2

    .line 123
    iput p2, v1, Landroid/graphics/RectF;->top:F

    :cond_2
    cmpl-float v2, v2, p3

    if-lez v2, :cond_3

    .line 126
    iput p3, v1, Landroid/graphics/RectF;->right:F

    :cond_3
    cmpl-float v2, v3, p4

    if-lez v2, :cond_4

    .line 129
    iput p4, v1, Landroid/graphics/RectF;->bottom:F

    :cond_4
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 115
    :cond_5
    :goto_1
    invoke-virtual {p0, v0}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->removeUnordered(I)V

    goto :goto_0

    :cond_6
    return-void
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 5

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    .line 153
    :cond_0
    instance-of v1, p1, Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    .line 157
    :cond_1
    check-cast p1, Lorg/telegram/utils/glass/positions/GlassPositionsArray;

    .line 158
    iget v1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    iget v3, p1, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    move v1, v2

    .line 162
    :goto_0
    iget v3, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    if-ge v1, v3, :cond_4

    .line 163
    iget-object v3, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/graphics/RectF;

    iget-object v4, p1, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v4, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/graphics/RectF;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    return v2

    :cond_3
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_4
    return v0
.end method

.method public get(I)Landroid/graphics/RectF;
    .locals 2

    if-ltz p1, :cond_0

    .line 48
    iget v0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    if-ge p1, v0, :cond_0

    .line 53
    iget-object p0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/graphics/RectF;

    return-object p0

    .line 49
    :cond_0
    const-string v0, ", size="

    iget p0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    const-string v1, "index="

    invoke-static {v1, p1, v0, p0}, Lkotlin/text/Regex$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;ILjava/lang/Object;I)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public hashCode()I
    .locals 3

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 175
    :goto_0
    iget v2, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    if-ge v1, v2, :cond_0

    mul-int/lit8 v0, v0, 0x1f

    .line 176
    iget-object v2, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/graphics/RectF;

    invoke-virtual {v2}, Landroid/graphics/RectF;->hashCode()I

    move-result v2

    add-int/2addr v0, v2

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return v0
.end method

.method public removeUnordered(I)V
    .locals 4

    if-ltz p1, :cond_1

    .line 73
    iget v0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    if-ge p1, v0, :cond_1

    add-int/lit8 v0, v0, -0x1

    .line 80
    iget-object v1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/graphics/RectF;

    if-eq p1, v0, :cond_0

    .line 83
    iget-object v2, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/graphics/RectF;

    invoke-virtual {v2, p1, v3}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 84
    iget-object p1, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {p1, v0, v1}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 87
    :cond_0
    iput v0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    return-void

    .line 74
    :cond_1
    const-string v0, ", size="

    iget p0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    const-string v1, "index="

    invoke-static {v1, p1, v0, p0}, Lkotlin/text/Regex$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;ILjava/lang/Object;I)V

    return-void
.end method

.method public set(Lorg/telegram/utils/glass/positions/GlassPositionsArray;)V
    .locals 3

    if-ne p0, p1, :cond_0

    goto :goto_1

    .line 141
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->clear()V

    .line 142
    iget v0, p1, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_1

    .line 144
    iget-object v2, p1, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->positions:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/graphics/RectF;

    invoke-virtual {p0, v2}, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->add(Landroid/graphics/RectF;)Landroid/graphics/RectF;

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    :goto_1
    return-void
.end method

.method public size()I
    .locals 0

    .line 61
    iget p0, p0, Lorg/telegram/utils/glass/positions/GlassPositionsArray;->size:I

    return p0
.end method
