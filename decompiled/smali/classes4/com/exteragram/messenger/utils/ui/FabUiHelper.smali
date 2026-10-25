.class public abstract Lcom/exteragram/messenger/utils/ui/FabUiHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static getFabBottomMarginDp()I
    .locals 1

    .line 50
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x10

    return v0

    :cond_0
    const/16 v0, 0xe

    return v0
.end method

.method public static getFabCornerRadiusDp()F
    .locals 2

    .line 78
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSquareFab()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 79
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->getFabSizeDp()I

    move-result v0

    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/UIUtil;->getFabSquareCornerRadiusDp(I)F

    move-result v0

    return v0

    .line 80
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->getFabSizeDp()I

    move-result v0

    int-to-float v0, v0

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v0, v1

    return v0
.end method

.method public static getFabElevationDp()F
    .locals 1

    .line 62
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x40c00000    # 6.0f

    return v0

    :cond_0
    const/high16 v0, 0x3f000000    # 0.5f

    return v0
.end method

.method public static getFabSizeDp()I
    .locals 1

    .line 46
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x38

    return v0

    :cond_0
    const/16 v0, 0x30

    return v0
.end method

.method public static getSubFabBackgroundRadiusDp()F
    .locals 3

    .line 84
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSquareFab()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 85
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x41400000    # 12.0f

    return v0

    :cond_0
    const/high16 v0, 0x41200000    # 10.0f

    return v0

    .line 87
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->getFabSizeDp()I

    move-result v0

    int-to-float v0, v0

    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->getSubFabPaddingDp()F

    move-result v1

    const/high16 v2, 0x40000000    # 2.0f

    mul-float/2addr v1, v2

    sub-float/2addr v0, v1

    div-float/2addr v0, v2

    return v0
.end method

.method public static getSubFabBottomMarginDp()I
    .locals 1

    .line 54
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x18

    return v0

    :cond_0
    const/16 v0, 0xe

    return v0
.end method

.method public static getSubFabPaddingDp()F
    .locals 1

    .line 58
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x41000000    # 8.0f

    return v0

    :cond_0
    const v0, 0x40b51eb8    # 5.66f

    return v0
.end method

.method public static getSubFabShadowColor()I
    .locals 1

    .line 74
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x30000000

    return v0

    :cond_0
    const/high16 v0, 0x20000000

    return v0
.end method

.method public static getSubFabShadowDyDp()F
    .locals 1

    .line 70
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x40000000    # 2.0f

    return v0

    :cond_0
    const v0, 0x3f59999a    # 0.85f

    return v0
.end method

.method public static getSubFabShadowRadiusDp()F
    .locals 1

    .line 66
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->isMaterial3Fab()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x40c00000    # 6.0f

    return v0

    :cond_0
    const v0, 0x402ab021    # 2.667f

    return v0
.end method

.method public static isMaterial3Fab()Z
    .locals 1

    .line 42
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getNewFabStyle()Z

    move-result v0

    return v0
.end method
