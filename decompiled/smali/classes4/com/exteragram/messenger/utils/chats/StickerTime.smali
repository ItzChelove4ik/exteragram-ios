.class public abstract Lcom/exteragram/messenger/utils/chats/StickerTime;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/utils/chats/StickerTime$Row;
    }
.end annotation


# direct methods
.method public static getSideButtonTouchLeft(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;F)F
    .locals 1

    .line 75
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->isBeside(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->message:Lorg/telegram/messenger/MessageObject;

    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result p0

    if-nez p0, :cond_0

    const/high16 p0, 0x41000000    # 8.0f

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    int-to-float p0, p0

    return p0

    :cond_0
    return p1
.end method

.method public static getSideButtonTouchRight(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;F)F
    .locals 1

    .line 79
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->isBeside(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->message:Lorg/telegram/messenger/MessageObject;

    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result p0

    if-eqz p0, :cond_0

    const/high16 p0, 0x41000000    # 8.0f

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    const/high16 p1, 0x42000000    # 32.0f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    add-int/2addr p0, p1

    int-to-float p0, p0

    return p0

    :cond_0
    return p1
.end method

.method public static getSideButtonX(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;F)F
    .locals 3

    .line 64
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->isBeside(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)Z

    move-result v0

    if-nez v0, :cond_0

    return p1

    :cond_0
    const/high16 v0, 0x41000000    # 8.0f

    .line 67
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    const/high16 v2, 0x42000000    # 32.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    add-int/2addr v1, v2

    int-to-float v1, v1

    .line 68
    iget-object v2, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->message:Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v2}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 69
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->getTimeLeft(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F

    move-result p0

    sub-float/2addr p0, v1

    invoke-static {p1, p0}, Ljava/lang/Math;->min(FF)F

    move-result p0

    return p0

    .line 71
    :cond_1
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->getTimeLeft(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F

    move-result v1

    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->getTimeWidth(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F

    move-result p0

    add-float/2addr v1, p0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    int-to-float p0, p0

    add-float/2addr v1, p0

    invoke-static {p1, v1}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0
.end method

.method private static getTimeLeft(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F
    .locals 3

    const/high16 v0, 0x41000000    # 8.0f

    .line 109
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    .line 110
    iget-boolean v1, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->hasName:Z

    if-eqz v1, :cond_0

    iget v1, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->nameTop:F

    iget v2, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->nameHeight:F

    add-float/2addr v2, v1

    invoke-static {p0, v1, v2}, Lcom/exteragram/messenger/utils/chats/StickerTime;->overlapsRow(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;FF)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x1

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    .line 111
    :goto_0
    iget-object v2, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->message:Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v2}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 112
    iget v2, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->stickerLeft:F

    sub-float/2addr v2, v0

    if-eqz v1, :cond_1

    .line 114
    iget v1, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->nameLeft:F

    sub-float/2addr v1, v0

    invoke-static {v2, v1}, Ljava/lang/Math;->min(FF)F

    move-result v2

    .line 116
    :cond_1
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->getTimeWidth(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F

    move-result p0

    sub-float/2addr v2, p0

    return v2

    .line 118
    :cond_2
    iget v2, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->stickerRight:F

    add-float/2addr v2, v0

    if-eqz v1, :cond_3

    .line 120
    iget v1, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->nameLeft:F

    iget p0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->nameWidth:F

    add-float/2addr v1, p0

    add-float/2addr v1, v0

    invoke-static {v2, v1}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0

    :cond_3
    return v2
.end method

.method public static getTimeOffsetX(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;F)F
    .locals 1

    .line 54
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->message:Lorg/telegram/messenger/MessageObject;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->isAnyKindOfSticker()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 57
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->isBeside(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 58
    iget p0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->overStickerOffsetX:F

    return p0

    .line 60
    :cond_1
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->getTimeLeft(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F

    move-result p0

    const/high16 v0, 0x40c00000    # 6.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    add-float/2addr p0, v0

    sub-float/2addr p0, p1

    return p0

    :cond_2
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method private static getTimeWidth(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F
    .locals 2

    .line 105
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->timeWidth:F

    const/high16 v1, 0x41400000    # 12.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->message:Lorg/telegram/messenger/MessageObject;

    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result p0

    if-eqz p0, :cond_0

    const/high16 p0, 0x41a00000    # 20.0f

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    int-to-float p0, p0

    add-float/2addr v0, p0

    return v0
.end method

.method private static isBeside(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)Z
    .locals 6

    .line 83
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->message:Lorg/telegram/messenger/MessageObject;

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    .line 85
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->isAnyKindOfSticker()Z

    move-result v2

    if-eqz v2, :cond_3

    .line 86
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerTimeMode()Lcom/exteragram/messenger/StickerTimeMode;

    move-result-object v2

    sget-object v3, Lcom/exteragram/messenger/StickerTimeMode;->SIDE:Lcom/exteragram/messenger/StickerTimeMode;

    if-ne v2, v3, :cond_3

    iget v2, v0, Lorg/telegram/messenger/MessageObject;->type:I

    const/16 v3, 0x13

    if-eq v2, v3, :cond_3

    iget v2, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->stickerWidth:F

    const/4 v3, 0x0

    cmpg-float v2, v2, v3

    if-gtz v2, :cond_0

    goto :goto_1

    :cond_0
    const/high16 v2, 0x41000000    # 8.0f

    .line 91
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    .line 92
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->getTimeLeft(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F

    move-result v3

    .line 93
    invoke-static {p0}, Lcom/exteragram/messenger/utils/chats/StickerTime;->getTimeWidth(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;)F

    move-result v4

    add-float/2addr v4, v3

    .line 94
    iget-boolean v5, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->sideButton:Z

    if-eqz v5, :cond_2

    .line 95
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result v0

    const/high16 v5, 0x42000000    # 32.0f

    if-eqz v0, :cond_1

    .line 96
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    add-float/2addr v0, v2

    sub-float/2addr v3, v0

    goto :goto_0

    .line 98
    :cond_1
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    add-float/2addr v0, v2

    add-float/2addr v4, v0

    :cond_2
    :goto_0
    cmpl-float v0, v3, v2

    if-ltz v0, :cond_3

    .line 101
    iget p0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->cellWidth:F

    sub-float/2addr p0, v2

    cmpg-float p0, v4, p0

    if-gtz p0, :cond_3

    const/4 p0, 0x1

    return p0

    :cond_3
    :goto_1
    return v1
.end method

.method public static isHidden(Lorg/telegram/messenger/MessageObject;)Z
    .locals 2

    .line 39
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerTimeMode()Lcom/exteragram/messenger/StickerTimeMode;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/StickerTimeMode;->HIDDEN:Lcom/exteragram/messenger/StickerTimeMode;

    if-ne v0, v1, :cond_0

    if-eqz p0, :cond_0

    .line 41
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isAnyKindOfSticker()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static overlapsRow(Lcom/exteragram/messenger/utils/chats/StickerTime$Row;FF)Z
    .locals 3

    .line 126
    iget p0, p0, Lcom/exteragram/messenger/utils/chats/StickerTime$Row;->anchorY:F

    const/high16 v0, 0x41b80000    # 23.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    sub-float/2addr p0, v0

    const/high16 v0, 0x41880000    # 17.0f

    .line 127
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    sget-object v1, Lorg/telegram/ui/ActionBar/Theme;->chat_timePaint:Lcom/exteragram/messenger/utils/ui/TextPaint;

    invoke-virtual {v1}, Landroid/graphics/Paint;->getTextSize()F

    move-result v1

    const/high16 v2, 0x40a00000    # 5.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    add-float/2addr v1, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->max(FF)F

    move-result v0

    add-float/2addr v0, p0

    cmpg-float p1, p1, v0

    if-gez p1, :cond_0

    cmpl-float p0, p2, p0

    if-lez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static shouldPreserveOnPreview(Lorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    if-eqz p0, :cond_0

    if-eqz p1, :cond_0

    .line 47
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result p0

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v0

    if-ne p0, v0, :cond_0

    iget-boolean p0, p1, Lorg/telegram/messenger/MessageObject;->preview:Z

    if-eqz p0, :cond_0

    .line 49
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->isAnyKindOfSticker()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 50
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerTimeMode()Lcom/exteragram/messenger/StickerTimeMode;

    move-result-object p0

    sget-object p1, Lcom/exteragram/messenger/StickerTimeMode;->HIDDEN:Lcom/exteragram/messenger/StickerTimeMode;

    if-eq p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
