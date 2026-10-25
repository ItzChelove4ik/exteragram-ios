.class public final Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private account:I

.field private allowed:Z

.field private animateFromX:F

.field private animateLayout:Landroid/text/StaticLayout;

.field private animating:Z

.field private final colorFilters:[Landroid/graphics/PorterDuffColorFilter;

.field private final colors:[I

.field private count:I

.field private dialogId:J

.field private drawable:Landroid/graphics/drawable/Drawable;

.field private lastLayout:Landroid/text/StaticLayout;

.field private lastX:F

.field private layout:Landroid/text/StaticLayout;

.field private messageId:I

.field private textSize:F

.field private typeface:Landroid/graphics/Typeface;

.field private width:I


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 39
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x2

    .line 42
    new-array v1, v0, [Landroid/graphics/PorterDuffColorFilter;

    iput-object v1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->colorFilters:[Landroid/graphics/PorterDuffColorFilter;

    .line 43
    new-array v0, v0, [I

    iput-object v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->colors:[I

    const/4 v0, 0x1

    .line 54
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->allowed:Z

    const/4 v0, -0x1

    .line 55
    iput v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->account:I

    return-void
.end method

.method private static getCount(Lorg/telegram/messenger/MessageObject;)I
    .locals 2

    .line 60
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowForwardsCount()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    if-eqz p0, :cond_1

    iget-object v0, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    if-eqz v0, :cond_1

    iget v0, v0, Lorg/telegram/tgnet/TLRPC$Message;->flags:I

    and-int/lit16 v0, v0, 0x400

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/messenger/MessageObject;->scheduled:Z

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/messenger/MessageObject;->notime:Z

    if-nez v0, :cond_1

    .line 62
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isSponsored()Z

    move-result v0

    if-nez v0, :cond_1

    .line 63
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isQuickReply()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isWelcomeMessage()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 66
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget p0, p0, Lorg/telegram/tgnet/TLRPC$Message;->forwards:I

    invoke-static {v1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0

    :cond_1
    :goto_0
    return v1
.end method


# virtual methods
.method public animateChange()Z
    .locals 2

    .line 119
    iget v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->lastX:F

    iput v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateFromX:F

    .line 120
    iget-object v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    iget-object v1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->lastLayout:Landroid/text/StaticLayout;

    if-eq v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animating:Z

    if-eqz v0, :cond_1

    goto :goto_1

    :cond_1
    const/4 v1, 0x0

    .line 121
    :goto_1
    iput-object v1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateLayout:Landroid/text/StaticLayout;

    return v0
.end method

.method public appendAccessibilityText(Landroid/text/SpannableStringBuilder;)V
    .locals 2

    .line 198
    iget v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->count:I

    if-lez v0, :cond_0

    .line 199
    const-string v0, "\n"

    invoke-virtual {p1, v0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object p1

    const-string v0, "Shares"

    iget v1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->count:I

    invoke-static {v0, v1}, Lorg/telegram/messenger/LocaleController;->getPluralString(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    iget p0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->count:I

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->formatCount(I)Ljava/lang/String;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {v0, p0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    :cond_0
    return-void
.end method

.method public bind(Lorg/telegram/messenger/MessageObject;)V
    .locals 4

    if-eqz p1, :cond_1

    .line 74
    iget v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->account:I

    iget v1, p1, Lorg/telegram/messenger/MessageObject;->currentAccount:I

    if-ne v0, v1, :cond_0

    iget-wide v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->dialogId:J

    .line 75
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    iget v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->messageId:I

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v1

    if-eq v0, v1, :cond_1

    .line 76
    :cond_0
    iget v0, p1, Lorg/telegram/messenger/MessageObject;->currentAccount:I

    iput v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->account:I

    .line 77
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->dialogId:J

    .line 78
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->messageId:I

    const/4 p1, 0x0

    .line 79
    iput-object p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    .line 80
    iput-object p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->lastLayout:Landroid/text/StaticLayout;

    const/4 p1, 0x0

    .line 81
    iput p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->count:I

    .line 82
    iput p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->width:I

    const/4 p1, 0x0

    .line 83
    iput p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->lastX:F

    const/4 p1, 0x1

    .line 84
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->allowed:Z

    .line 85
    invoke-virtual {p0}, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->resetAnimation()V

    :cond_1
    return-void
.end method

.method public draw(Landroid/graphics/Canvas;Lorg/telegram/ui/Cells/ChatMessageCell;FFFFFZ)F
    .locals 16

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move/from16 v3, p4

    .line 131
    iget-object v4, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    const/4 v5, 0x0

    if-nez v4, :cond_0

    iget-object v6, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateLayout:Landroid/text/StaticLayout;

    if-nez v6, :cond_0

    return v5

    .line 134
    :cond_0
    iget-object v6, v2, Lorg/telegram/ui/Cells/ChatMessageCell;->transitionParams:Lorg/telegram/ui/Cells/ChatMessageCell$TransitionParams;

    .line 135
    iget v7, v6, Lorg/telegram/ui/Cells/ChatMessageCell$TransitionParams;->animateChangeProgress:F

    .line 136
    iget-boolean v8, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animating:Z

    const/4 v9, 0x0

    const/4 v10, 0x1

    if-eqz v8, :cond_1

    iget-object v11, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateLayout:Landroid/text/StaticLayout;

    if-nez v11, :cond_1

    move v11, v10

    goto :goto_0

    :cond_1
    move v11, v9

    :goto_0
    if-eqz v8, :cond_2

    if-nez v4, :cond_2

    move v9, v10

    .line 138
    :cond_2
    iget-boolean v4, v6, Lorg/telegram/ui/Cells/ChatMessageCell$TransitionParams;->shouldAnimateTimeX:Z

    if-eqz v4, :cond_3

    iget v8, v2, Lorg/telegram/ui/Cells/ChatMessageCell;->timeX:I

    int-to-float v8, v8

    goto :goto_1

    :cond_3
    move/from16 v8, p3

    :goto_1
    add-float v8, v8, p5

    if-eqz v4, :cond_6

    if-nez v11, :cond_6

    .line 140
    invoke-virtual {v2}, Lorg/telegram/ui/Cells/ChatMessageCell;->getMessageObject()Lorg/telegram/messenger/MessageObject;

    move-result-object v4

    invoke-virtual {v4}, Lorg/telegram/messenger/MessageObject;->isRoundVideo()Z

    move-result v4

    if-eqz v4, :cond_4

    iget-boolean v4, v6, Lorg/telegram/ui/Cells/ChatMessageCell$TransitionParams;->animateDrawBackground:Z

    if-nez v4, :cond_6

    .line 141
    :cond_4
    iget v4, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateFromX:F

    if-eqz v9, :cond_5

    :goto_2
    move v8, v4

    goto :goto_3

    :cond_5
    invoke-static {v4, v8, v7}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v4

    goto :goto_2

    .line 143
    :cond_6
    :goto_3
    invoke-virtual {v2}, Lorg/telegram/ui/Cells/ChatMessageCell;->getCurrentMessagesGroup()Lorg/telegram/messenger/MessageObject$GroupedMessages;

    move-result-object v4

    if-eqz v4, :cond_7

    .line 144
    iget-object v4, v4, Lorg/telegram/messenger/MessageObject$GroupedMessages;->transitionParams:Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;

    iget-boolean v10, v4, Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;->backgroundChangeBounds:Z

    if-eqz v10, :cond_7

    .line 145
    iget v4, v4, Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;->offsetRight:F

    add-float/2addr v8, v4

    .line 147
    :cond_7
    iget-boolean v4, v6, Lorg/telegram/ui/Cells/ChatMessageCell$TransitionParams;->animateBackgroundBoundsInner:Z

    if-eqz v4, :cond_8

    .line 148
    invoke-virtual {v2}, Lorg/telegram/ui/Cells/ChatMessageCell;->getAnimationOffsetX()F

    move-result v4

    add-float/2addr v8, v4

    .line 150
    :cond_8
    iput v8, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->lastX:F

    .line 152
    sget-object v4, Lorg/telegram/ui/ActionBar/Theme;->chat_timePaint:Lcom/exteragram/messenger/utils/ui/TextPaint;

    .line 153
    invoke-virtual {v4}, Landroid/graphics/Paint;->getAlpha()I

    move-result v6

    int-to-float v10, v6

    mul-float v10, v10, p7

    float-to-int v10, v10

    .line 156
    invoke-virtual {v2}, Lorg/telegram/ui/Cells/ChatMessageCell;->shouldDrawTimeOnMedia()Z

    move-result v12

    if-eqz v12, :cond_a

    .line 157
    invoke-virtual {v2}, Lorg/telegram/ui/Cells/ChatMessageCell;->getMessageObject()Lorg/telegram/messenger/MessageObject;

    move-result-object v12

    invoke-virtual {v12}, Lorg/telegram/messenger/MessageObject;->shouldDrawWithoutBackground()Z

    move-result v12

    if-eqz v12, :cond_9

    sget v12, Lorg/telegram/ui/ActionBar/Theme;->key_chat_serviceText:I

    goto :goto_4

    :cond_9
    sget v12, Lorg/telegram/ui/ActionBar/Theme;->key_chat_mediaViews:I

    goto :goto_4

    .line 158
    :cond_a
    invoke-virtual {v2}, Lorg/telegram/ui/Cells/ChatMessageCell;->getMessageObject()Lorg/telegram/messenger/MessageObject;

    move-result-object v12

    invoke-virtual {v12}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result v12

    if-eqz v12, :cond_c

    if-eqz p8, :cond_b

    .line 159
    sget v12, Lorg/telegram/ui/ActionBar/Theme;->key_chat_outViewsSelected:I

    goto :goto_4

    :cond_b
    sget v12, Lorg/telegram/ui/ActionBar/Theme;->key_chat_outViews:I

    goto :goto_4

    :cond_c
    if-eqz p8, :cond_d

    .line 161
    sget v12, Lorg/telegram/ui/ActionBar/Theme;->key_chat_inViewsSelected:I

    goto :goto_4

    :cond_d
    sget v12, Lorg/telegram/ui/ActionBar/Theme;->key_chat_inViews:I

    .line 163
    :goto_4
    invoke-virtual {v2, v12}, Lorg/telegram/ui/Cells/ChatMessageCell;->getThemedColor(I)I

    move-result v2

    .line 165
    iget-object v12, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->colorFilters:[Landroid/graphics/PorterDuffColorFilter;

    aget-object v13, v12, p8

    if-eqz v13, :cond_e

    iget-object v13, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->colors:[I

    aget v13, v13, p8

    if-eq v13, v2, :cond_f

    .line 166
    :cond_e
    iget-object v13, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->colors:[I

    aput v2, v13, p8

    .line 167
    new-instance v13, Landroid/graphics/PorterDuffColorFilter;

    sget-object v14, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v13, v2, v14}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    aput-object v13, v12, p8

    .line 169
    :cond_f
    iget-object v2, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    iget-object v12, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->colorFilters:[Landroid/graphics/PorterDuffColorFilter;

    aget-object v12, v12, p8

    invoke-virtual {v2, v12}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 170
    iget-object v2, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v4}, Landroid/graphics/Paint;->getTextSize()F

    move-result v12

    invoke-static {v2, v8, v3, v12}, Lorg/telegram/ui/Cells/BaseCell;->setDrawableBounds(Landroid/graphics/drawable/Drawable;FFF)F

    move-result v2

    .line 171
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    const/high16 v12, 0x3f800000    # 1.0f

    cmpl-float v13, p7, v12

    if-eqz v13, :cond_11

    const/high16 v13, 0x3f000000    # 0.5f

    mul-float v15, p7, v13

    add-float/2addr v15, v13

    .line 174
    iget-object v13, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    if-eqz v13, :cond_10

    :goto_5
    invoke-virtual {v13}, Landroid/text/Layout;->getWidth()I

    move-result v13

    const/high16 p2, 0x40400000    # 3.0f

    goto :goto_6

    :cond_10
    iget-object v13, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateLayout:Landroid/text/StaticLayout;

    goto :goto_5

    .line 175
    :goto_6
    invoke-static/range {p2 .. p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v14

    int-to-float v14, v14

    add-float/2addr v14, v2

    int-to-float v13, v13

    add-float/2addr v14, v13

    const/high16 v13, 0x40000000    # 2.0f

    div-float/2addr v14, v13

    add-float/2addr v14, v8

    iget-object v13, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v13}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v13

    invoke-virtual {v13}, Landroid/graphics/Rect;->centerY()I

    move-result v13

    int-to-float v13, v13

    invoke-virtual {v1, v15, v15, v14, v13}, Landroid/graphics/Canvas;->scale(FFFF)V

    goto :goto_7

    :cond_11
    const/high16 p2, 0x40400000    # 3.0f

    :goto_7
    if-eqz v11, :cond_12

    move v9, v7

    goto :goto_8

    :cond_12
    if-eqz v9, :cond_13

    sub-float v9, v12, v7

    goto :goto_8

    :cond_13
    move v9, v12

    .line 178
    :goto_8
    iget-object v11, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    const/high16 v13, 0x437f0000    # 255.0f

    mul-float v13, v13, p6

    mul-float/2addr v13, v9

    float-to-int v9, v13

    invoke-virtual {v11, v9}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 179
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 180
    iget-object v9, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v9}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v9

    invoke-virtual {v9}, Landroid/graphics/Rect;->exactCenterX()F

    move-result v9

    const/high16 v11, -0x40800000    # -1.0f

    invoke-virtual {v1, v11, v12, v9, v5}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 181
    iget-object v5, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v5, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 182
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    add-float/2addr v8, v2

    .line 183
    invoke-static/range {p2 .. p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    add-float/2addr v8, v2

    invoke-virtual {v1, v8, v3}, Landroid/graphics/Canvas;->translate(FF)V

    .line 184
    iget-object v2, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateLayout:Landroid/text/StaticLayout;

    if-eqz v2, :cond_14

    int-to-float v2, v10

    sub-float v3, v12, v7

    mul-float/2addr v2, v3

    float-to-int v2, v2

    .line 185
    invoke-virtual {v4, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 186
    iget-object v2, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateLayout:Landroid/text/StaticLayout;

    invoke-virtual {v2, v1}, Landroid/text/Layout;->draw(Landroid/graphics/Canvas;)V

    .line 188
    :cond_14
    iget-object v2, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    if-eqz v2, :cond_16

    int-to-float v2, v10

    .line 189
    iget-boolean v3, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animating:Z

    if-eqz v3, :cond_15

    goto :goto_9

    :cond_15
    move v7, v12

    :goto_9
    mul-float/2addr v2, v7

    float-to-int v2, v2

    invoke-virtual {v4, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 190
    iget-object v2, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    invoke-virtual {v2, v1}, Landroid/text/Layout;->draw(Landroid/graphics/Canvas;)V

    .line 192
    :cond_16
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 193
    invoke-virtual {v4, v6}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 194
    iget v0, v0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->width:I

    int-to-float v0, v0

    return v0
.end method

.method public isChanged(Lorg/telegram/messenger/MessageObject;)Z
    .locals 2

    .line 70
    iget v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->count:I

    iget-boolean p0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->allowed:Z

    const/4 v1, 0x0

    if-eqz p0, :cond_0

    invoke-static {p1}, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->getCount(Lorg/telegram/messenger/MessageObject;)I

    move-result p0

    goto :goto_0

    :cond_0
    move p0, v1

    :goto_0
    if-eq v0, p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public measure(Landroid/content/Context;Lorg/telegram/messenger/MessageObject;Z)I
    .locals 8

    .line 90
    iput-boolean p3, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->allowed:Z

    const/4 v0, 0x0

    if-eqz p3, :cond_0

    .line 91
    invoke-static {p2}, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->getCount(Lorg/telegram/messenger/MessageObject;)I

    move-result p2

    goto :goto_0

    :cond_0
    move p2, v0

    :goto_0
    iput p2, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->count:I

    const/4 p3, 0x0

    if-nez p2, :cond_1

    .line 93
    iput-object p3, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    .line 94
    iput v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->width:I

    return v0

    .line 97
    :cond_1
    iget-object p2, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    if-nez p2, :cond_2

    .line 98
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/R$drawable;->msg_reply_small:I

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    .line 100
    :cond_2
    sget-object v2, Lorg/telegram/ui/ActionBar/Theme;->chat_timePaint:Lcom/exteragram/messenger/utils/ui/TextPaint;

    .line 101
    iget p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->count:I

    invoke-static {p1, p3}, Lorg/telegram/messenger/LocaleController;->formatShortNumber(I[I)Ljava/lang/String;

    move-result-object v1

    .line 102
    iget-object p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Landroid/text/Layout;->getText()Ljava/lang/CharSequence;

    move-result-object p1

    invoke-static {p1, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_3

    iget p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->textSize:F

    .line 103
    invoke-virtual {v2}, Landroid/graphics/Paint;->getTextSize()F

    move-result p2

    cmpl-float p1, p1, p2

    if-nez p1, :cond_3

    iget-object p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->typeface:Landroid/graphics/Typeface;

    invoke-virtual {v2}, Landroid/graphics/Paint;->getTypeface()Landroid/graphics/Typeface;

    move-result-object p2

    if-eq p1, p2, :cond_4

    .line 104
    :cond_3
    invoke-virtual {v2}, Landroid/graphics/Paint;->getTextSize()F

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->textSize:F

    .line 105
    invoke-virtual {v2}, Landroid/graphics/Paint;->getTypeface()Landroid/graphics/Typeface;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->typeface:Landroid/graphics/Typeface;

    .line 106
    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result p1

    float-to-double p1, p1

    invoke-static {p1, p2}, Ljava/lang/Math;->ceil(D)D

    move-result-wide p1

    double-to-int p1, p1

    const/4 p2, 0x1

    invoke-static {p2, p1}, Ljava/lang/Math;->max(II)I

    move-result v3

    .line 107
    new-instance v0, Landroid/text/StaticLayout;

    sget-object v4, Landroid/text/Layout$Alignment;->ALIGN_NORMAL:Landroid/text/Layout$Alignment;

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/high16 v5, 0x3f800000    # 1.0f

    invoke-direct/range {v0 .. v7}, Landroid/text/StaticLayout;-><init>(Ljava/lang/CharSequence;Landroid/text/TextPaint;ILandroid/text/Layout$Alignment;FFZ)V

    iput-object v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    .line 109
    :cond_4
    iget-object p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result p1

    int-to-float p1, p1

    invoke-virtual {v2}, Landroid/graphics/Paint;->getTextSize()F

    move-result p2

    mul-float/2addr p1, p2

    iget-object p2, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->drawable:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result p2

    int-to-float p2, p2

    div-float/2addr p1, p2

    .line 110
    iget-object p2, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    invoke-virtual {p2}, Landroid/text/Layout;->getWidth()I

    move-result p2

    int-to-float p2, p2

    add-float/2addr p2, p1

    const/high16 p1, 0x41200000    # 10.0f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    add-float/2addr p2, p1

    float-to-double p1, p2

    invoke-static {p1, p2}, Ljava/lang/Math;->ceil(D)D

    move-result-wide p1

    double-to-int p1, p1

    iput p1, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->width:I

    return p1
.end method

.method public recordDrawingState()V
    .locals 1

    .line 115
    iget-object v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->layout:Landroid/text/StaticLayout;

    iput-object v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->lastLayout:Landroid/text/StaticLayout;

    return-void
.end method

.method public resetAnimation()V
    .locals 1

    const/4 v0, 0x0

    .line 126
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animating:Z

    const/4 v0, 0x0

    .line 127
    iput-object v0, p0, Lcom/exteragram/messenger/utils/ui/ForwardsCountUiHelper;->animateLayout:Landroid/text/StaticLayout;

    return-void
.end method
