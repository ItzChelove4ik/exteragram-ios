.class public abstract Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper$ProfileTransitionState;
    }
.end annotation


# direct methods
.method public static applyChatHeaderGlassStyle(Lorg/telegram/ui/ActionBar/ActionBar;)V
    .locals 1

    .line 99
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->applyChatHeaderGlassStyle(Lorg/telegram/ui/ActionBar/ActionBar;Z)V

    return-void
.end method

.method public static applyChatHeaderGlassStyle(Lorg/telegram/ui/ActionBar/ActionBar;Z)V
    .locals 0

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    .line 104
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/ActionBar;->setDrawGlassMiddlePill(Z)V

    const/4 p1, 0x0

    .line 105
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/ActionBar;->setGlassShadowAlpha(F)V

    :cond_0
    return-void
.end method

.method public static getAvatarContainerLeftMargin(Z)I
    .locals 0

    if-eqz p0, :cond_0

    const/4 p0, 0x4

    return p0

    .line 174
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result p0

    if-eqz p0, :cond_1

    const/16 p0, 0x39

    return p0

    :cond_1
    const/16 p0, 0x34

    return p0
.end method

.method public static getAvatarInsetPx()I
    .locals 1

    .line 69
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public static getAvatarRadius(IZ)I
    .locals 1

    .line 81
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    int-to-float p0, p0

    const/4 v0, 0x0

    .line 82
    invoke-static {p0, v0, p1}, Lcom/exteragram/messenger/ExteraConfig;->getAvatarCorners(FZZ)I

    move-result p0

    return p0

    .line 84
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->getAvatarSizePx(I)I

    move-result p0

    int-to-float p0, p0

    const/4 v0, 0x1

    invoke-static {p0, v0, p1}, Lcom/exteragram/messenger/ExteraConfig;->getAvatarCorners(FZZ)I

    move-result p0

    return p0
.end method

.method public static getAvatarSizePx(I)I
    .locals 1

    int-to-float p0, p0

    .line 77
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->getAvatarInsetPx()I

    move-result v0

    mul-int/lit8 v0, v0, 0x2

    sub-int/2addr p0, v0

    return p0
.end method

.method public static getChatAvatarSizeDp()I
    .locals 1

    .line 65
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x2e

    return v0

    :cond_0
    const/16 v0, 0x2a

    return v0
.end method

.method public static getChatFadeColorKey()I
    .locals 1

    .line 88
    invoke-static {}, Lcom/exteragram/messenger/debug/DebugConfig;->getChatFadeUseWhiteBackground()Z

    move-result v0

    if-eqz v0, :cond_0

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    return v0

    :cond_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    return v0
.end method

.method public static getChatTopFadeHeight(Z)I
    .locals 0

    if-eqz p0, :cond_0

    const/high16 p0, 0x429c0000    # 78.0f

    goto :goto_0

    :cond_0
    const/high16 p0, 0x42400000    # 48.0f

    .line 194
    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public static getChatTopFadeZone(IZ)I
    .locals 0

    if-eqz p1, :cond_0

    const/high16 p1, 0x42280000    # 42.0f

    .line 198
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    add-int/2addr p0, p1

    :cond_0
    return p0
.end method

.method public static getFinalTopPanelHeight(FLorg/telegram/ui/Components/ChatActivityTopPanelLayout;)F
    .locals 0

    .line 185
    invoke-static {p1}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->getTopPanelActionBarGapOffset(Lorg/telegram/ui/Components/ChatActivityTopPanelLayout;)F

    move-result p1

    add-float/2addr p0, p1

    return p0
.end method

.method public static getGlassPillExtraRightPaddingDp()F
    .locals 1

    .line 73
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    const/high16 v0, 0x40c00000    # 6.0f

    return v0
.end method

.method private static getScaledChatTopFadeZone(Lorg/telegram/ui/ActionBar/ActionBar;IZ)I
    .locals 0

    .line 151
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result p0

    .line 152
    invoke-static {p0, p2}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->getChatTopFadeZone(IZ)I

    move-result p2

    if-eqz p0, :cond_1

    if-lt p2, p1, :cond_0

    goto :goto_0

    :cond_0
    sub-int/2addr p1, p2

    int-to-float p0, p1

    const/high16 p1, 0x3f000000    # 0.5f

    mul-float/2addr p0, p1

    .line 156
    invoke-static {p0}, Ljava/lang/Math;->round(F)I

    move-result p0

    add-int/2addr p2, p0

    return p2

    :cond_1
    :goto_0
    return p1
.end method

.method public static getTopPanelActionBarGapOffset(Lorg/telegram/ui/Components/ChatActivityTopPanelLayout;)F
    .locals 1

    .line 178
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    if-eqz v0, :cond_1

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    const/high16 v0, 0x40800000    # 4.0f

    .line 181
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/AnimatedLinearLayout;->getMetadata()Lme/vkryl/android/animator/ListAnimator$Metadata;

    move-result-object p0

    invoke-virtual {p0}, Lme/vkryl/android/animator/ListAnimator$Metadata;->getTotalVisibility()F

    move-result p0

    mul-float/2addr v0, p0

    return v0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public static getTopPanelTranslationY(FFF)F
    .locals 1

    .line 189
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, -0x1

    goto :goto_0

    :cond_0
    const/4 v0, -0x5

    :goto_0
    int-to-float v0, v0

    .line 190
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    add-float/2addr p0, v0

    mul-float/2addr p1, p2

    sub-float/2addr p0, p1

    return p0
.end method

.method public static isLightChatStatusBar(Lorg/telegram/ui/ActionBar/ActionBar;I)Z
    .locals 1

    .line 160
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    invoke-static {p0, p1, v0}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isLightChatStatusBar(Lorg/telegram/ui/ActionBar/ActionBar;IZ)Z

    move-result p0

    return p0
.end method

.method public static isLightChatStatusBar(Lorg/telegram/ui/ActionBar/ActionBar;IZ)Z
    .locals 0

    if-eqz p2, :cond_0

    .line 164
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->isActionModeShowed()Z

    move-result p2

    if-nez p2, :cond_0

    goto :goto_0

    .line 166
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->getBackgroundColor()I

    move-result p1

    .line 167
    :goto_0
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->computePerceivedBrightness(I)F

    move-result p0

    const p1, 0x3f389375    # 0.721f

    cmpl-float p0, p0, p1

    if-lez p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public static isMaterial3ChatHeaderStyle()Z
    .locals 1

    .line 61
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getNewChatHeaderStyle()Z

    move-result v0

    return v0
.end method

.method public static scrimReachesTopFade(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;Landroid/view/ViewGroup;Landroid/view/View;Lorg/telegram/messenger/MessageObject$GroupedMessages;F)Z
    .locals 7

    .line 126
    invoke-virtual {p0}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->getFadeZoneTop()I

    move-result p0

    int-to-float p0, p0

    cmpl-float p4, p4, p0

    const/4 v0, 0x0

    if-ltz p4, :cond_0

    return v0

    .line 130
    :cond_0
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result p4

    move v1, v0

    :goto_0
    const/4 v2, 0x1

    if-ge v1, p4, :cond_7

    .line 131
    invoke-virtual {p1, v1}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 132
    instance-of v4, v3, Lorg/telegram/ui/Cells/ChatMessageCell;

    if-eqz v4, :cond_1

    move-object v4, v3

    check-cast v4, Lorg/telegram/ui/Cells/ChatMessageCell;

    goto :goto_1

    :cond_1
    const/4 v4, 0x0

    :goto_1
    if-eq v3, p2, :cond_2

    if-eqz p3, :cond_6

    if-eqz v4, :cond_6

    .line 133
    invoke-virtual {v4}, Lorg/telegram/ui/Cells/ChatMessageCell;->getCurrentMessagesGroup()Lorg/telegram/messenger/MessageObject$GroupedMessages;

    move-result-object v5

    if-ne v5, p3, :cond_6

    :cond_2
    invoke-virtual {v3}, Landroid/view/View;->getAlpha()F

    move-result v5

    const/4 v6, 0x0

    cmpl-float v5, v5, v6

    if-nez v5, :cond_3

    goto :goto_2

    :cond_3
    if-eqz v4, :cond_4

    .line 136
    invoke-virtual {v4}, Lorg/telegram/ui/Cells/ChatMessageCell;->getTransitionParams()Lorg/telegram/ui/Cells/ChatMessageCell$TransitionParams;

    move-result-object v4

    iget-boolean v4, v4, Lorg/telegram/ui/Cells/ChatMessageCell$TransitionParams;->animateBackgroundBoundsInner:Z

    if-nez v4, :cond_5

    :cond_4
    invoke-virtual {p1}, Landroid/view/View;->getY()F

    move-result v4

    invoke-virtual {v3}, Landroid/view/View;->getY()F

    move-result v3

    add-float/2addr v4, v3

    cmpg-float v3, v4, p0

    if-gez v3, :cond_6

    :cond_5
    return v2

    :cond_6
    :goto_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_7
    if-eqz p3, :cond_9

    .line 140
    iget-object p2, p3, Lorg/telegram/messenger/MessageObject$GroupedMessages;->transitionParams:Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;

    iget-object p3, p2, Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;->cell:Lorg/telegram/ui/Cells/ChatMessageCell;

    if-eqz p3, :cond_9

    .line 141
    iget p4, p2, Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;->top:I

    int-to-float p4, p4

    iget v1, p2, Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;->offsetTop:F

    add-float/2addr p4, v1

    .line 142
    iget-boolean p2, p2, Lorg/telegram/messenger/MessageObject$GroupedMessages$TransitionParams;->backgroundChangeBounds:Z

    if-nez p2, :cond_8

    .line 143
    invoke-virtual {p3}, Landroid/view/View;->getTranslationY()F

    move-result p2

    add-float/2addr p4, p2

    .line 145
    :cond_8
    invoke-virtual {p1}, Landroid/view/View;->getY()F

    move-result p1

    add-float/2addr p1, p4

    cmpg-float p0, p1, p0

    if-gez p0, :cond_9

    return v2

    :cond_9
    return v0
.end method

.method public static setupChatTopFade(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;Lorg/telegram/ui/ActionBar/ActionBar;II)V
    .locals 1

    .line 110
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    invoke-static {p0, p1, p2, p3, v0}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->setupChatTopFade(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;Lorg/telegram/ui/ActionBar/ActionBar;IIZ)V

    return-void
.end method

.method public static setupChatTopFade(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;Lorg/telegram/ui/ActionBar/ActionBar;IIZ)V
    .locals 1

    .line 114
    invoke-virtual {p1}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    const/16 v0, 0xff

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setFadeTopAlpha(I)V

    .line 115
    invoke-static {p3, p4}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->getChatTopFadeZone(IZ)I

    move-result p3

    if-eqz p4, :cond_1

    .line 117
    invoke-static {p1, p3, p4}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->getScaledChatTopFadeZone(Lorg/telegram/ui/ActionBar/ActionBar;IZ)I

    move-result p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setFadeZoneTop(I)V

    .line 118
    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setTopFadeColor(I)V

    goto :goto_1

    .line 120
    :cond_1
    invoke-virtual {p0, p3}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setFadeZoneTop(I)V

    .line 122
    :goto_1
    invoke-static {p4}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->getChatTopFadeHeight(Z)I

    move-result p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setFadeHeightTop(I)V

    return-void
.end method

.method public static setupGlassAvatarContainer(Lorg/telegram/ui/Components/ChatAvatarContainer;)V
    .locals 1

    .line 92
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ChatAvatarContainer;->setGlassMode()V

    .line 93
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x2e

    .line 94
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/ChatAvatarContainer;->setAvatarSizeInDp(I)V

    :cond_0
    return-void
.end method
